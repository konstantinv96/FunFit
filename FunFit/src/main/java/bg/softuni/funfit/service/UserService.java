package bg.softuni.funfit.service;

import bg.softuni.funfit.model.User;
import bg.softuni.funfit.model.dto.UserRegistrationDTO;
import bg.softuni.funfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final EmailService emailService;
        private final UserDetailsService userDetailsService;

    @Autowired
        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService,
                           UserDetailsService userDetailsService) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.emailService = emailService;
            this.userDetailsService = userDetailsService;
    }

        public void register(UserRegistrationDTO registrationDTO){
            if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){

                throw new RuntimeException("passwords.match");
            }

            Optional<User> byEmail = this.userRepository.findByEmail(registrationDTO.getEmail());

            if(byEmail.isPresent()){
                throw new RuntimeException("email.used");
            }

            User user = new User(
                    registrationDTO.getUsername(),
                    passwordEncoder.encode(registrationDTO.getPassword()),
                    registrationDTO.getEmail(),
                    registrationDTO.getFullName(),
                    registrationDTO.getAge()
            );

            this.userRepository.save(user);
            login(user);
            emailService.sendRegistrationEmail(user.getEmail(),user.getUsername());

        }
        public User getUser(String username){

            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
        }

        private void login(User user){
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    userDetails,userDetails.getPassword(),userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

        }

}
