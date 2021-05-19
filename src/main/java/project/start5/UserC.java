package project.start5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acc")
public class UserC {

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new Fragment3());
        return "register";
    }
    @GetMapping //main page
    public String index() {
        return "login";
    }
    @GetMapping("/login") //logging out from home
    public String logout() {
        return "login";
    }
    
    @PostMapping("/home")
    public String home() {
        return "home";
    }
}