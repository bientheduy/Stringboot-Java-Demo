package net.bientheduy.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         return http
         .csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(authorize -> authorize
                   .requestMatchers("/css/**", "/js/**","/fonts/**","/images/**", "/vendor/**").permitAll()  
                   .requestMatchers("/home").permitAll()
                   .requestMatchers("/admin/**").hasRole("ADMIN")
         )
         .formLogin(form->form
               .loginPage("/login")
               .permitAll()
         )
         .logout((logout) -> logout 
                .logoutSuccessUrl("/")
                .permitAll()
         )
         .httpBasic(Customizer.withDefaults())
         .build();
    }
}
