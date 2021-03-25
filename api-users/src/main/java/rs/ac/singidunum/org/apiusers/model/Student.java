package rs.ac.singidunum.org.apiusers.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Student extends User{

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "jmbg")
    private Jmbg jmbg;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;
}
