package com.ru.vsu.woodemai.security.auth;


import com.ru.vsu.woodemai.token.TokenService;
import com.ru.vsu.woodemai.user.User;
import com.ru.vsu.woodemai.user.UserDto;
import com.ru.vsu.woodemai.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;

    public AuthenticationResponse registration(RegisterRequest request, HttpServletResponse httpServletResponse) {
        User user = userService.setUser(request);
        login(request);
        String refreshToken = tokenService.generateAccessToken(user);
        String accessToken = tokenService.generateRefreshToken(user);
        tokenService.setTokenToRepository(refreshToken, user);
        setTokenToCookie(httpServletResponse, refreshToken);
        return buildResponse(refreshToken, accessToken, user);
    }

    public AuthenticationResponse login(AuthenticationRequest request, HttpServletResponse httpServletResponse) {
        User user = userService.getUser(request.getEmail());
        login(request);
        String refreshToken = tokenService.generateAccessToken(user);
        String accessToken = tokenService.generateRefreshToken(user);
        tokenService.setTokenToRepository(refreshToken, user);
        setTokenToCookie(httpServletResponse, refreshToken);
        return buildResponse(refreshToken, accessToken, user);
    }

    public AuthenticationResponse refresh(String requestToken, HttpServletResponse httpServletResponse) {
        tokenService.checkEquals(requestToken);
        String email = tokenService.getUsername(requestToken);
        User user = userService.getUser(email);
        String refreshToken = tokenService.generateAccessToken(user);
        String accessToken = tokenService.generateRefreshToken(user);
        tokenService.setTokenToRepository(refreshToken, user);
        setTokenToCookie(httpServletResponse, refreshToken);
        return buildResponse(refreshToken, accessToken, user);
    }

    public void logout(String refreshToken) {
        tokenService.deleteToken(refreshToken);

    }


    private void setTokenToCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("refreshToken", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    private AuthenticationResponse buildResponse(String refreshToken, String accessToken, User user) {
        return AuthenticationResponse.builder()
                .refreshToken(refreshToken)
                .accessToken(accessToken)
                .user(new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getRole().toString()))
                .build();
    }

    private void login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }

    private void login(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
    }


}