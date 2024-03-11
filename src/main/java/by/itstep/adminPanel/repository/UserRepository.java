package by.itstep.adminPanel.repository;

import by.itstep.adminPanel.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        CrudRepository<User, Long> {
}
