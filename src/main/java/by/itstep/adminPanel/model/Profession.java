package by.itstep.adminPanel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profession")
public class Profession {

    @Id
    private int profession_id;

    @Column(name="profession_name", length = 60)
    private String professionName;
}
