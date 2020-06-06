package pl.kremiec.registrationapp.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kremiec.registrationapp.model.Token;
import pl.kremiec.registrationapp.model.User;
import pl.kremiec.registrationapp.repo.TokenRepo;
import pl.kremiec.registrationapp.repo.UserRepo;
import pl.kremiec.registrationapp.service.UserRepoSave;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RestControler {

    UserRepoSave userRepoSave;
    TokenRepo tokenRepo;
    UserRepo userRepo;

    public RestControler(UserRepoSave userRepoSave, TokenRepo tokenRepo, UserRepo userRepo) {
        this.userRepoSave = userRepoSave;
        this.tokenRepo = tokenRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public String welcome(){
        return "welcomePage";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/registerDone")
    public String submitForm(@ModelAttribute("user") User user) {
        userRepoSave.saveUserToRepo(user);
        return "emailSent";
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value) {
        Token byValue = tokenRepo.findByToken(value);
        User user = byValue.getUser();
        user.setEmailConfirmed(true);
        userRepo.save(user);
        return "confirmed";
    }

    @GetMapping("/users")
    public String users(Model model){

        List<User> arrayList = new ArrayList<>();

        userRepo.findAll().forEach(s->arrayList.add(s));

        model.addAttribute("list", arrayList);

        return "users";
    }


}
