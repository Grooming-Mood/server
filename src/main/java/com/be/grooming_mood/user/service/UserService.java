package com.be.grooming_mood.user.service;

import static com.be.grooming_mood.exception.ErrorCode.INVALID_EMAIL;
import static com.be.grooming_mood.exception.ErrorCode.INVALID_PASSWORD;
import static com.be.grooming_mood.exception.ErrorCode.USER_NOT_FOUND;

import com.be.grooming_mood.exception.BadRequestException;
import com.be.grooming_mood.exception.NotFoundException;
import com.be.grooming_mood.user.domain.Role;
import com.be.grooming_mood.user.domain.User;
import com.be.grooming_mood.user.domain.UserRepository;
import com.be.grooming_mood.user.dto.PostLoginRes;
import com.be.grooming_mood.user.dto.UserCreateDto;
import com.be.grooming_mood.user.dto.UserLoginDto;
import com.be.grooming_mood.user.dto.UserUpdateDto;
import com.be.grooming_mood.utils.jwt.JwtService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Long signUpUser(UserCreateDto userCreateDto) {

        Optional<User> emailCheck = userRepository.findByEmail(userCreateDto.getEmail());

        if(emailCheck.isPresent()){
            throw new NotFoundException(INVALID_EMAIL);
        }

        User user = User.builder()
                        .email(userCreateDto.getEmail())
                        .password(passwordEncoder.encode(userCreateDto.getPassword()))
                        .name(userCreateDto.getName())
                        .role(Role.USER)
                        .createdDate(LocalDateTime.now())
                        .build();
        userRepository.save(user);
        return user.getId();
    }


    @Transactional
    public PostLoginRes loginUser(UserLoginDto userLoginDto) {
        Optional<User> emailCheck = userRepository.findByEmail(userLoginDto.getEmail());
        System.out.println(emailCheck);

        User user = emailCheck.orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND));


        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new BadRequestException(INVALID_PASSWORD);
        }

        String jwt = jwtService.createJwtToken(user.getEmail());

        return PostLoginRes.builder()
                .jwt(jwt)
                .id(user.getId())
                .build();

    }

    @Transactional
    public void updateUser(Long userId, UserUpdateDto userUpdateDto) {
        Optional<User> userCheck = userRepository.findById(userId);

        User user = userCheck.orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND));

        userRepository.save(user.update(userUpdateDto.getName(), userUpdateDto.getProfileImg()));
    }

    @Transactional
    public User getUserInfo(Long userId) {
        Optional<User> userCheck = userRepository.findById(userId);

        User user = userCheck.orElseThrow(() ->
                new NotFoundException(USER_NOT_FOUND));

        return user;
    }

}
