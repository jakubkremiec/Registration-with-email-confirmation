package pl.kremiec.registrationapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kremiec.registrationapp.service.UserDetailsFromRepo;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encodePassword(){
        return new BCryptPasswordEncoder();
    }

    UserDetailsFromRepo userDetailsFromRepo;

    public SecurityConfiguration(UserDetailsFromRepo userDetailsFromRepo) {
        this.userDetailsFromRepo = userDetailsFromRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsFromRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();

        http.authorizeRequests()
                .antMatchers("/user/getall").authenticated()
                .and()
                .formLogin();
    }
}
