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
@RequestMapping("/registration")
public class RegisterC {
 

    @PostMapping("/saveRegister")
    public String saveRegisterPage(/*@ModelAttribute("user") User user, BindingResult result, Model model, RedirectAttributes attributes*/) {
        /*model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "register";
        } else {
            userServiceImpl.save(user);
        }*/
        return "home";
    }
}