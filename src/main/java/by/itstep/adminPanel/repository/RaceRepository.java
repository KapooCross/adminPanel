package by.itstep.adminPanel.repository;

import by.itstep.adminPanel.model.Profession;
import by.itstep.adminPanel.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Race, Long> {
}
