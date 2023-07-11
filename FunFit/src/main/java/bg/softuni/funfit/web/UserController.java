package bg.softuni.funfit.web;

import bg.softuni.funfit.model.User;
import bg.softuni.funfit.model.views.UserProfileView;
import bg.softuni.funfit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import bg.softuni.funfit.model.dto.UserRegistrationDTO;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

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

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @PostMapping("/login/error")
    public String onFailLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.
            SPRING_SECURITY_FORM_USERNAME_KEY) String userName , RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY , userName);
        redirectAttributes.addFlashAttribute("bad_credentials" , true);

        return"redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model){
        String username = principal.getName();
        User user = userService.getUser(username);
        //TODO This has to be in model mapper !
        UserProfileView userProfileView = new UserProfileView(user.getUsername()
                ,user.getEmail()
                ,user.getFullName()
                ,user.getAge()
        );
        model.addAttribute("user",userProfileView);
        //TODO Have to fix the view on profile page
        return "profile";
    }

}
