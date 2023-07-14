package com.authmodule.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User createUser(RequestUserCreate requestUserCreate) {
        User user = User.builder()
                .email(requestUserCreate.getEmail())
                .nickname(requestUserCreate.getNickname())
                .password(requestUserCreate.getPassword())
                .build();
        return userRepository.save(user);
    }
}
