package itmo.ru.infosec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import itmo.ru.infosec.dto.CredentialsDto;
import itmo.ru.infosec.entity.AuthModel;
import itmo.ru.infosec.exceptions.IncorrectPasswordException;
import itmo.ru.infosec.exceptions.UserAlreadyExistsException;
import itmo.ru.infosec.exceptions.UserNotFoundException;
import itmo.ru.infosec.repository.AuthRepository;
import itmo.ru.infosec.utils.JwtUtils;

import java.util.Optional;

@Service
public class AuthService {
    public AuthService(AuthRepository authRepository,PasswordEncoder passwordEncoder,JwtUtils jwtUtils) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String register(CredentialsDto credentialsDto) {
        Optional<AuthModel> user = authRepository.findById(credentialsDto.login());
        if (user.isPresent())
            throw new UserAlreadyExistsException("User with name " + credentialsDto.login() + " already exists!");

        AuthModel authModel = new AuthModel(
                HtmlUtils.htmlEscape(credentialsDto.login()),
                passwordEncoder.encode(credentialsDto.password())
        );
        authRepository.save(authModel);
        return jwtUtils.generateJwtToken(authModel.getLogin());
    }

    public String login(CredentialsDto credentialsDto) {
        AuthModel user = authRepository.findById(credentialsDto.login())
                .orElseThrow(() -> new UserNotFoundException("User with name " + credentialsDto.login() + " not found!"));
        if (!passwordEncoder.matches(credentialsDto.password(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        return jwtUtils.generateJwtToken(user.getLogin());
    }
}