package bg.softuni.funfit.service;

import bg.softuni.funfit.model.User;
import bg.softuni.funfit.model.dto.UserRegistrationDTO;
import bg.softuni.funfit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

        private UserRepository userRepository;
        private PasswordEncoder passwordEncoder;

        @Autowired
        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
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

        }
        public User getUser(String username){

            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
        }

}
