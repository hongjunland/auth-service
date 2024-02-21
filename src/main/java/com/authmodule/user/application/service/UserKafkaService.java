package com.authmodule.user.application.service;

import com.authmodule.common.annotaion.UseCase;
import com.authmodule.common.utils.TokenProvider;
import com.authmodule.user.adapter.in.web.reqeust.UserInfoRequestMessage;
import com.authmodule.user.adapter.in.web.response.UserInfoMessage;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@UseCase
public class UserKafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;
    private final LoadUserPort loadUserPort;
    private static final String AUTH_RESPONSE_TOPIC = "auth.user.info.response";
    @KafkaListener(topics = "auth.user.info.request")
    public void handleAuthenticationRequest(String message) throws JsonProcessingException {
        UserInfoRequestMessage userInfoRequestMessage = objectMapper.readValue(message, UserInfoRequestMessage.class);
        String token = userInfoRequestMessage.getToken().substring(7);
        Claims claims = tokenProvider.parseClaims(token);
        Long userId = Long.valueOf(claims.getSubject());
        User user = loadUserPort.loadById(userId);
        UserInfoMessage userInfoMessage = UserInfoMessage.builder()
                .requestId(userInfoRequestMessage.getRequestId())
                .nickname(user.getNickname())
                .userId(user.getId().getValue())
                .build();
        String requestMessage = objectMapper.writeValueAsString(userInfoMessage);
        kafkaTemplate.send(AUTH_RESPONSE_TOPIC, requestMessage);
    }
}
