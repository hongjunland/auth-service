package com.authmodule.user.adapter.in.web;

import com.authmodule.user.adapter.in.web.reqeust.LoginRequest;
import com.authmodule.user.adapter.out.persistence.SpringDataUserRepository;
import com.authmodule.user.adapter.out.persistence.UserJpaEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest(properties = "spring.config.location=classpath:application-test.yml")
@Transactional
class AuthApiDocumentation {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SpringDataUserRepository springDataUserRepository;
    @BeforeEach
    public void setup(){
        String rawPassword = "testPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        UserJpaEntity user = UserJpaEntity.builder()
                .email("example@example.com")
                .password(encodedPassword)
                .name("홍길동")
                .nickname("홍길동12")
                .build();
        springDataUserRepository.save(user);
    }
    @Test
    @Transactional
    public void loginUser() throws Exception {
        LoginRequest request = new LoginRequest("example@example.com", "testPassword");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("auth-login",
                        PayloadDocumentation.requestFields(
                                fieldWithPath("email").description("이메일 주소12"),
                                fieldWithPath("password").description("비밀번호")
                        )
                        ,PayloadDocumentation.responseFields(
                                fieldWithPath("status").description("응답 상태 코드"),
                                fieldWithPath("message").description("응답 메시지"),
                                fieldWithPath("data").description("응답 데이터 객체"),
                                fieldWithPath("data.accessToken").description("접근 토큰"),
                                fieldWithPath("data.refreshToken").description("새로 고침 토큰"),
                                fieldWithPath("data.expiration").description("토큰의 만료 시간")
                        )
                ));
    }
}
