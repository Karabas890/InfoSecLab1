package itmo.ru.infosec.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import itmo.ru.infosec.dto.CredentialsDto;
import itmo.ru.infosec.service.UserService;

@RestController
public class UserController {
    public UserController(UserService authService) {
        this.authService = authService;
    }

    private final UserService authService;

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody @Valid CredentialsDto request) {
        return authService.register(request);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Valid CredentialsDto request) {
        return authService.login(request);
    }
}
