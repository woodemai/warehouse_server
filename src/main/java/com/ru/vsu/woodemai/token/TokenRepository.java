package com.ru.vsu.woodemai.token;

import com.ru.vsu.woodemai.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> getByUser(User user);

    Optional<Token> getByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
