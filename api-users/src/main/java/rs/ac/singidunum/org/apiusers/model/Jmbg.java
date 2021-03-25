package rs.ac.singidunum.org.apiusers.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Jmbg {

    @OneToOne(optional = true)
    @JoinColumn(name = "professor_id", unique = true)
    private Professor professor;

    @OneToOne(optional = true)
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    @Id
    @Column(columnDefinition = "CHAR(16)")
    private String jmbg;


}
