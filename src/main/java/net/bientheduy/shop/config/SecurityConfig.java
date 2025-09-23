package net.bientheduy.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import net.bientheduy.shop.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
UserDetailsService userDetailsService(PasswordEncoder encoder) {
    return new UserService();
}

@Bean
 PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         return http
         .csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(authorize -> authorize
                   .requestMatchers("/css/**", "/js/**","/fonts/**","/images/**", "/vendor/**", "/kaiadmin/**").permitAll()
                   .requestMatchers("/" ,"/register").permitAll()
                   .requestMatchers("/admin/**").hasRole("ADMIN")
         )
         .formLogin(form->form
               .loginPage("/login")
               .defaultSuccessUrl("/")
               .permitAll()
         )
         .logout((logout) -> logout 
                .logoutSuccessUrl("/")
                .permitAll()
         )
         .httpBasic(Customizer.withDefaults())
         .build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
     }
}
