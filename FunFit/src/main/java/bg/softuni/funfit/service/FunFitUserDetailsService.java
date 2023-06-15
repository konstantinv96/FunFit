package bg.softuni.funfit.service;


import bg.softuni.funfit.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


public class FunFitUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public FunFitUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(u -> new User(
                u.getUsername(),
                u.getPassword(),
                u.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
                        .collect(Collectors.toList())
        )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
