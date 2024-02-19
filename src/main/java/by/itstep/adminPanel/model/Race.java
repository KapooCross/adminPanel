package by.itstep.adminPanel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "race")
public class Race {

    @Id
    private int race_id;

    @Column(name="race_name", length = 60)
    private String raceName;
}
