package itmo.ru.infosec.configuration;

import itmo.ru.infosec.filter.AuthorizationFilter;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @SuppressFBWarnings(value = "SPRING_CSRF_PROTECTION_DISABLED",
            justification = "JWT API does not use cookies, CSRF protection not needed")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthorizationFilter authFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class); // добавляем наш фильтр

        return http.build();
    }
}
