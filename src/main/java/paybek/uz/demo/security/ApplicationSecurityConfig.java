package paybek.uz.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static paybek.uz.demo.security.ApplicationUserRole.*;
import static paybek.uz.demo.security.ApplicationUserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index/**", "/index.html").permitAll()
                .antMatchers("/api/student/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userS = User.builder()
                .username("userS")
                .password(passwordEncoder.encode("password1"))
                .roles(STUDENT.name())   //ROLE_STUDENT
                .build();

        UserDetails userA = User.builder()
                .username("userA")
                .password(passwordEncoder.encode("password2"))
                .roles(ADMIN.name())     //ROLE_ADMIN
                .build();

        UserDetails userSA = User.builder()
                .username("userSA")
                .password(passwordEncoder.encode("password3"))
                .roles(SUPERADMIN.name())     //ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                userA,
                userSA,
                userS
        );
    }
}
