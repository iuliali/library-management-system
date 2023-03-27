package com.labs.librarymanagementsystemapi.services;

import com.labs.librarymanagementsystemapi.dtos.CustomUserDetails;
import com.labs.librarymanagementsystemapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow();
        return new CustomUserDetails(user);
    }

}
