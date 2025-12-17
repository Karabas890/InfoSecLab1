package itmo.ru.infosec.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "auth_table")
public class AuthModel {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;

    public AuthModel() {
    }

    public AuthModel(String login, String password) {
        this.login = login;
        this.password = password;
    }
}