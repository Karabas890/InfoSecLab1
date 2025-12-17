package itmo.ru.infosec.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(nullable = false)
    String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}