package rs.ac.singidunum.org.apiusers.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)",unique = true)
    private String username;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

    private boolean isDeleted = false;

    @ManyToMany()
    @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> users;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void replace(Administrator newUser) {
        this.username = newUser.getUsername();
        this.password = newUser.getPassword();
        this.email = newUser.getEmail();
    };
}
