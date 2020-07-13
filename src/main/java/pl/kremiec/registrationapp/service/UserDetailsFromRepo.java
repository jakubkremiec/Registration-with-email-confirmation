package pl.kremiec.registrationapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kremiec.registrationapp.repo.UserRepo;

@Service
public class UserDetailsFromRepo implements UserDetailsService {

    private UserRepo userRepo;

    protected UserDetailsFromRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }
}
