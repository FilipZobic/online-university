package rs.ac.singidunum.org.apiusers.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Professor extends User{

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String biography;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "jmbg")
    private Jmbg jmbg;

}
