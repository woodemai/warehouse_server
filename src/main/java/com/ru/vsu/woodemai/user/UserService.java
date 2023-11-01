package com.ru.vsu.woodemai.user;

import com.ru.vsu.woodemai.security.auth.RegisterRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public boolean isRoleValid(String role) {
        return role.equals("EMPLOYEE") || role.equals("CLIENT");
    }

    public User getUser(String email) {
        return repository.getByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + "not found"));
    }

    public User setUser(RegisterRequest request) {
        Optional<User> optUser = repository.getByEmail(request.getEmail());
        if (optUser.isPresent()) {
            throw new EntityExistsException("User with email: " + request.getEmail() + " already exists");
        }
        var user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();
        return repository.save(user);
    }
}
