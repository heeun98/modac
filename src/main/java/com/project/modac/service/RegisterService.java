package com.project.modac.service;

import com.project.modac.domain.User;
import com.project.modac.exception.CustomException;
import com.project.modac.exception.ErrorCode;
import com.project.modac.repository.UserRepository;
import com.project.modac.web.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public String register(RegisterRequestDto registerRequest) {

        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        //중복 회원일 경우 예외 발생
        userRepository.findOptionByUsername(username)
                .ifPresent(user -> {
                throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
                });

        User user = User.builder()
                .username(username)
                .password(password)
                .build();

        userRepository.save(user);

        return username;
    }

}
