package by.itstep.adminPanel.controller;

import by.itstep.adminPanel.model.Profession;
import by.itstep.adminPanel.model.Race;
import by.itstep.adminPanel.model.User;
import by.itstep.adminPanel.repository.ProfessionRepository;
import by.itstep.adminPanel.repository.RaceRepository;
import by.itstep.adminPanel.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final ProfessionRepository professionRepository;
    private final RaceRepository raceRepository;
    private final int PAGE_SIZE = 10;

    @GetMapping("/")
    public String showMenu() {
        return "index";
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        return showUserListPaginatedAndSorted(1, "name", "asc", model);
    }


    @GetMapping("/add_user")
    public String showInputForm(@ModelAttribute("user") User user) {
        return "add_user";
    }

//    @PostMapping("add_user")
//    public RedirectView addUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return new RedirectView("add_success", true);
//    }

    @PostMapping("/add_user")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "add_user";
        }
        userService.save(user);
        return "/add_user_success";
    }


    @GetMapping("/page/{pageNumber}")
    public String showUserListPaginatedAndSorted(@PathVariable(value = "pageNumber") int pageNumber,
                                                     @RequestParam("sortField") String sortField,
                                                     @RequestParam("sortDir") String sortDir,
                                                     Model model) {
        Page<User> page = userService.findPaginatedAndSorted(pageNumber, PAGE_SIZE, sortField, sortDir);
        var userList = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("userList", userList);
        //sort
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "users";

    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id){
        this.userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model){
        Optional<User> user = userService.findById(id);
        model.addAttribute("user", user.get());
        return "update";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "update";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @ModelAttribute("professionList")
    public List<Profession> populateProfession() {
        Iterable<Profession> professions = professionRepository.findAll();
        return StreamSupport.stream(professions.spliterator(), false)
                .collect(Collectors.toList());
    }

    @ModelAttribute("raceList")
    public List<Race> populateRace() {
        Iterable<Race> professions = raceRepository.findAll();
        return StreamSupport.stream(professions.spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public String searchUserById(@RequestParam("id") Long id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("userList", Collections.singletonList(user.get()));
        } else {
            model.addAttribute("userList", Collections.emptyList());
        }
        return "users";
    }



}

