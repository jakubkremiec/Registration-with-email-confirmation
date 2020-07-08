package pl.kremiec.registrationapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kremiec.registrationapp.model.Token;
import pl.kremiec.registrationapp.model.User;
import pl.kremiec.registrationapp.repo.TokenRepo;
import pl.kremiec.registrationapp.repo.UserRepo;

@Service
public class RestService {

    PasswordEncoder passwordEncoder;
    UserRepo userRepo;
    TokenRepo tokenRepo;
    TokenService tokenService;

    public RestService(PasswordEncoder passwordEncoder, UserRepo userRepo, TokenRepo tokenRepo, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.tokenService = tokenService;
    }

    public void saveUserToRepo(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
        tokenService.sendTokenToUser(user);
    }

    public void activateUser(String value){
        Token byValue = tokenRepo.findByToken(value);
        User user = byValue.getUser();
        user.setEmailConfirmed(true);
        userRepo.save(user);
    }

    public boolean emailCheck(String email){
        if (email.matches(".+@.+\\..+")){
            return true;
        }else {
            return false;
        }
    }
}
