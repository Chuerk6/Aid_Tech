package GRUPO1.TP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private Boolean enabled;
    private Date passwordLastUpdate;


    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Student student;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = {
                    @JoinColumn (
                            name="user_id",
                            referencedColumnName = "id",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "authority_id",
                            referencedColumnName = "id",
                            nullable = false
                    )
            }
    )
    private List<Authority> authorities;





    public User(String userName, String password, boolean enabled, Date passwordLastUpdate, List<Authority> authorities, Student student) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.passwordLastUpdate = passwordLastUpdate;
        this.authorities = authorities;
        this.student=student;
    }


}
