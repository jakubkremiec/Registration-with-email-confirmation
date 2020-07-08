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
import pl.kremiec.registrationapp.service.RestService;
import pl.kremiec.registrationapp.service.TokenService;

@Controller
public class RestControler {

    RestService restService;
    UserRepo userRepo;

    public RestControler(RestService restService, UserRepo userRepo) {
        this.restService = restService;
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public String welcome(){
        return "welcomePage";
    }

    @GetMapping("/user/create")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "userCreate";
    }

    @PostMapping("/user/created")
    public String submitForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        if(restService.emailCheck(user.getEmail())){
            restService.saveUserToRepo(user);
            return "emailSent";
        } else {
            return "redirect:/user/create";
        }
    }

    @GetMapping("/token")
    public String singup(@RequestParam String value, Model model) {
        try {
            restService.activateUser(value);
        }catch (NullPointerException ex){
            model.addAttribute("value", value);
            return "tokenNotFound";
        }
        return "userConfirmed";
    }

    @GetMapping("/user/getall")
    public String users(Model model){
        model.addAttribute("list", userRepo.findAll());
        return "usersGetAll";
    }


}
