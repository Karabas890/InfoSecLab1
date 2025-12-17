package itmo.ru.infosec.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import itmo.ru.infosec.dto.CredentialsDto;
import itmo.ru.infosec.entity.User;
import itmo.ru.infosec.exceptions.IncorrectPasswordException;
import itmo.ru.infosec.exceptions.UserAlreadyExistsException;
import itmo.ru.infosec.exceptions.UserNotFoundException;
import itmo.ru.infosec.repository.UserRepository;
import itmo.ru.infosec.utils.JwtUtils;

import java.util.Optional;

@Service
public class UserService {
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String register(CredentialsDto credentialsDto) {
        Optional<User> user = userRepository.findByLogin(credentialsDto.login());
        if (user.isPresent())
            throw new UserAlreadyExistsException("User with name " + credentialsDto.login() + " already exists!");

        User authModel = new User(
                HtmlUtils.htmlEscape(credentialsDto.login()),
                passwordEncoder.encode(credentialsDto.password())
        );
        userRepository.save(authModel);
        return jwtUtils.generateJwtToken(authModel.getLogin());
    }

    public String login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new UserNotFoundException("User with name " + credentialsDto.login() + " not found!"));
        if (!passwordEncoder.matches(credentialsDto.password(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }
        return jwtUtils.generateJwtToken(user.getLogin());
    }
}