package pl.kremiec.registrationapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kremiec.registrationapp.model.User;
import pl.kremiec.registrationapp.repo.UserRepo;

@Configuration
public class BasicUsers {

UserRepo userRepo;
PasswordEncoder passwordEncoder;

    public BasicUsers(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;


        User user1 = new User();
        user1.setEmail("JakubKremiec99@icloud.com");
        user1.setUsername("Jakub");
        user1.setPassword(passwordEncoder.encode("Jakub123"));
        user1.setRole("ROLE_ADMIN");
        user1.setEmailConfirmed(true);
        user1.setLocalDate("1999-07-21");
        user1.setMale(true);
        user1.setName("Jakub");
        user1.setSurname("Kremiec");


        User user2 = new User();
        user2.setEmail("zagrodnikuwna@icloud.com");
        user2.setUsername("Kasia");
        user2.setPassword(passwordEncoder.encode("Kasia123"));
        user2.setRole("ROLE_USER");
        user2.setEmailConfirmed(true);
        user2.setLocalDate("2000-01-22");
        user2.setMale(false);
        user2.setName("Kasia");
        user2.setSurname("Zagrodnik");

        User user3 = new User();
        user3.setEmail("kapusniak@icloud.com");
        user3.setUsername("Bogdan");
        user3.setPassword(passwordEncoder.encode("Bogdan123"));
        user3.setRole("ROLE_USER");
        user3.setEmailConfirmed(false);
        user3.setLocalDate("2020-01-01");
        user3.setMale(true);
        user3.setName("Kapu");
        user3.setSurname("sniak");

        userRepo.save(user1);
        userRepo.save(user2);
        userRepo.save(user3);

    }




}
