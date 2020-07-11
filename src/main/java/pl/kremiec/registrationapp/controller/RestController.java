package pl.kremiec.registrationapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kremiec.registrationapp.model.User;
import pl.kremiec.registrationapp.repo.UserRepo;
import pl.kremiec.registrationapp.service.RestService;

@Controller
public class RestController {

    private RestService restService;
    private UserRepo userRepo;

    public RestController(RestService restService, UserRepo userRepo) {
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

    @PostMapping("/user/create")
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
        restService.activateUser(value);
        return "userConfirmed";
    }

    @GetMapping("/users/all")
    public String users(Model model){
        model.addAttribute("list", userRepo.findAll());
        return "usersGetAll";
    }


}
