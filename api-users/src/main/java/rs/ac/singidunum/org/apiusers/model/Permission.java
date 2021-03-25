package rs.ac.singidunum.org.apiusers.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Permission {
    @Id
    @Column(name = "permission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany()
    @JoinTable(joinColumns = @JoinColumn(name = "permission_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
