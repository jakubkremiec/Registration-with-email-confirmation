package pl.kremiec.registrationapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kremiec.registrationapp.model.Token;
import pl.kremiec.registrationapp.model.User;
import pl.kremiec.registrationapp.repo.TokenRepo;
import pl.kremiec.registrationapp.repo.UserRepo;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class RestService {

    private PasswordEncoder passwordEncoder;
    private UserRepo userRepo;
    private TokenRepo tokenRepo;
    private TokenService tokenService;

    private Pattern mailPattern;

    protected RestService(PasswordEncoder passwordEncoder, UserRepo userRepo, TokenRepo tokenRepo, TokenService tokenService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.tokenService = tokenService;
        mailPattern = Pattern.compile(".+@.+\\..+");
    }

    public void saveUserToRepo(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepo.save(user);
        tokenService.sendTokenToUser(user);
    }

    public void activateUser(String value){

        Token byValue = tokenRepo.findByToken(value);

        Optional.ofNullable(tokenRepo.findByToken(value)).ifPresentOrElse(
                token -> {
                    User user = byValue.getUser();
                    user.setEmailConfirmed(true);
                    userRepo.save(user);
                },
        () -> {
                throw new NullPointerException("Token " + value + " not found in database!");
        }
        );

    }
    public boolean emailCheck(String email){
        return mailPattern.matcher(email).matches();
    }
}
