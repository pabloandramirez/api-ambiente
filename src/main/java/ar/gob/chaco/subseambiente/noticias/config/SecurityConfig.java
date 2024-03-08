package ar.gob.chaco.subseambiente.noticias.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .requestMatchers("/api/user/**").hasRole("USER")
                    .requestMatchers("/noticia/").permitAll()
                    .requestMatchers("/noticia/noticiaPorLong/**").permitAll()
                    .requestMatchers("/noticia/paginado**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/noticia/nuevaNoticia").hasAnyRole("ADMIN","USER")
                    .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

}


