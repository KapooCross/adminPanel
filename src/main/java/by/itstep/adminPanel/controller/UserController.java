package by.itstep.adminPanel.controller;

import by.itstep.adminPanel.model.User;
import by.itstep.adminPanel.repository.ProfessionRepository;
import by.itstep.adminPanel.repository.RaceRepository;
import by.itstep.adminPanel.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final ProfessionRepository professionRepository;
    private final RaceRepository raceRepository;
    private final int PAGE_SIZE = 5;

    @GetMapping("/")
    public String showMenu() {
        return "index";
    }

    @GetMapping("/users")
    public String showUsersList(Model model) {
        return showUserListPaginatedAndSorted(1, "name", "asc", model);
//        return "users";
    }
    /////

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
        //для сортировки
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
}

