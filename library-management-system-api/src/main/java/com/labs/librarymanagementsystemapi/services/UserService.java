package com.labs.librarymanagementsystemapi.services;


import com.labs.librarymanagementsystemapi.dtos.AuthenticationRequest;
import com.labs.librarymanagementsystemapi.dtos.CreateUserDto;
import com.labs.librarymanagementsystemapi.dtos.CustomUserDetails;
import com.labs.librarymanagementsystemapi.dtos.UserDto;
import com.labs.librarymanagementsystemapi.exceptions.UserDoesNotExistException;
import com.labs.librarymanagementsystemapi.models.User;
import com.labs.librarymanagementsystemapi.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private  final AuthenticationManager authenticationManager;

    private  final JWTService jwtService;

    public UserDto createUser(CreateUserDto createUserDto) {
        var user = CreateUserDto.toUser(createUserDto);
        user.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        user = userRepository.save(user);
        return new UserDto(user);
    }

    public String authenticate (AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findByUsername(
                authenticationRequest.getUsername()).orElseThrow();
        return jwtService.generateToken(
                Map.of("firstName", user.getFirstName(),
                        "lastName", user.getLastName(),
                        "role", user.getRole()),
                new CustomUserDetails(user)
        );
    }

    public UserDto findById(BigInteger id) {
        return userRepository.findById(id)
                .map(UserDto::new)
                .orElseThrow();
    }

    public User getUserFromHeader(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        var token = authorizationHeader.substring(7);
        var username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserDoesNotExistException(UserService.class)
        );
        return user;
    }
}
