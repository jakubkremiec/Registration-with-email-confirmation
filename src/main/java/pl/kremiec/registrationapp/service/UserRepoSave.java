package pl.kremiec.registrationapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kremiec.registrationapp.model.Token;
import pl.kremiec.registrationapp.model.User;
import pl.kremiec.registrationapp.repo.TokenRepo;
import pl.kremiec.registrationapp.repo.UserRepo;

import java.util.UUID;

@Service
public class UserRepoSave {

    PasswordEncoder passwordEncoder;
    UserRepo userRepo;
    MailService mailService;
    TokenRepo tokenRepo;

    public UserRepoSave(PasswordEncoder passwordEncoder, UserRepo userRepo, MailService mailService, TokenRepo tokenRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.mailService = mailService;
        this.tokenRepo = tokenRepo;
    }

    public void saveUserToRepo(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
        sendTokenToUser(user);
    }

    public void sendTokenToUser(User user){

        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        tokenRepo.save(token);

        String tokenUrl = "http://localhost:8080/token?value=" + token.getToken();

        mailService.sendConfirmationToken(user, "Confirm your account!", "Hello " + user.getName() + "!\nPlease confirm your email adress: " + tokenUrl);
    }




}
