package by.itstep.adminPanel.service;

import by.itstep.adminPanel.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
    Page<User> findPaginatedAndSorted(int pageNumber, int pageSize, String sortField, String sortDirection);


}
