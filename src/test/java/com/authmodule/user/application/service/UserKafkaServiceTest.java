package com.authmodule.user.application.service;

import com.authmodule.common.utils.TokenProvider;
import com.authmodule.user.adapter.in.web.reqeust.UserInfoRequestMessage;
import com.authmodule.user.application.port.out.LoadUserPort;
import com.authmodule.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@DisplayName("UserKafkaServiceTest 테스트")
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class UserKafkaServiceTest {
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Mock
    private TokenProvider tokenProvider;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private LoadUserPort loadUserPort;
    @InjectMocks
    private UserKafkaService userKafkaService;
    @BeforeEach
    void setUp() throws JsonProcessingException {
        // ObjectMapper의 동작을 Mock 처리
        when(objectMapper.readValue(anyString(), eq(UserInfoRequestMessage.class)))
                .thenReturn(new UserInfoRequestMessage("asdsadsadsadsad", "Bearer mockedToken"));
        Claims mockedClaims = mock(Claims.class);
        // TokenProvider의 동작을 Mock 처리
        when(mockedClaims.getSubject()).thenReturn("1");
        when(tokenProvider.parseClaims(anyString())).thenReturn(mockedClaims);
        when(loadUserPort.loadById(anyLong())).thenReturn(User.builder()
                .id(new User.UserId(1L))
                .nickname("nickname1")
                .email("email@example.com")
                .name("name1")
                .password("password1")
                .build());
        when(objectMapper.writeValueAsString(any())).thenReturn("mocked JSON");
    }

    @Test
    void handleAuthenticationRequestTest() throws JsonProcessingException {
        // Given
        String mockMessage = "{\"token\":\"Bearer mockedToken\", \"requestId\":\"req123\"}";

        // When
        userKafkaService.handleAuthenticationRequest(mockMessage);

        // Then
        verify(kafkaTemplate).send(anyString(), anyString());
        // 추가적으로, objectMapper, tokenProvider, loadUserPort가 예상대로 호출되었는지 검증
        verify(objectMapper).readValue(anyString(), eq(UserInfoRequestMessage.class));
        verify(tokenProvider).parseClaims(anyString());
        verify(loadUserPort).loadById(anyLong());
    }

}
