package com.project.modac.service;

import com.project.modac.apiPayload.GeneralException;
import com.project.modac.apiPayload.code.status.ErrorStatus;
import com.project.modac.domain.Address;
import com.project.modac.domain.User;
import com.project.modac.dto.RegisterRequestDto;
import com.project.modac.repository.UserRepository;
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
        String nickname = registerRequest.getNickname();
        String email = registerRequest.getEmail();
        String address = registerRequest.getAddress();

        String[] addressString = address.split(" ");

        if (addressString.length < 3) {
            throw new GeneralException(ErrorStatus.ADDRESS_NOMATCH);
        }

        Address realAddress = new Address(addressString[0], addressString[1], addressString[2]);

        //중복 회원일 경우 예외 발생
        userRepository.findOptionByUsername(username)
                .ifPresent(user -> {
                throw new GeneralException(ErrorStatus.DUPLICATE_USERNAME);
                });

        User user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .nickname(nickname)
                .email(email)
                .address(realAddress)
                .build();

        userRepository.save(user);

        return username;
    }

}
