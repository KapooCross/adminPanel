package by.itstep.adminPanel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private Race race;
    private Profession profession;
    private Integer experience;
    private Integer Level;
    private LocalDate dateOfBirth;
    private String banned;

}
