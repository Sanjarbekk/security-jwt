package paybek.uz.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static paybek.uz.demo.security.ApplicationUserPermission.*;
import static paybek.uz.demo.security.ApplicationUserRole.*;
import static paybek.uz.demo.security.ApplicationUserRole.ADMIN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
               /* .antMatchers(HttpMethod.DELETE, "/api/province/**").hasAuthority(PROVINCE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/api/province/**").hasAuthority(PROVINCE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/province/**").hasAuthority(PROVINCE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/province/**").hasAnyRole(ADMIN.name(), TRAINER.name())*/
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails userS = User.builder()
                .username("student")
                .password(passwordEncoder.encode("password"))
              //  .roles(STUDENT.name())   //ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails userA = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
            //    .roles(ADMIN.name())     //ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails userSA = User.builder()
                .username("trainer")
                .password(passwordEncoder.encode("password"))
              //  .roles(SUPERADMIN.name())     //ROLE_ADMIN
                .authorities(TRAINER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                userA,
                userSA,
                userS
        );
    }
}
