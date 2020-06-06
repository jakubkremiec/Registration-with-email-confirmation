package pl.kremiec.registrationapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kremiec.registrationapp.model.Token;

public interface TokenRepo extends JpaRepository<Token, String> {

    Token findByToken(String token);

}
