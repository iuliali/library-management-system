package com.labs.librarymanagementsystemapi.services;


import com.labs.librarymanagementsystemapi.dtos.AuthenticationRequest;
import com.labs.librarymanagementsystemapi.dtos.CreateUserDto;
import com.labs.librarymanagementsystemapi.dtos.CustomUserDetails;
import com.labs.librarymanagementsystemapi.dtos.UserDto;
import com.labs.librarymanagementsystemapi.repositories.UserRepository;
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
                Map.of("firstName", user.getFirstName()),
                new CustomUserDetails(user)
        );

    }

    public UserDto findById(BigInteger id) {
        return userRepository.findById(id)
                .map(UserDto::new)
                .orElseThrow();
    }

}
