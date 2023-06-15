package bg.softuni.funfit.web;

import bg.softuni.funfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import bg.softuni.funfit.model.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm(){
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register(){

        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

//        System.out.println(userRegistrationDTO.toString());

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("userRegistrationDTO",userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/register";
        }

        this.userService.register(userRegistrationDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

}
