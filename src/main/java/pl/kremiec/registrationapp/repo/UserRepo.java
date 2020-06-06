package pl.kremiec.registrationapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kremiec.registrationapp.model.User;

public interface UserRepo extends JpaRepository<User, String> {

    User findByUsername(String s);
}
