package com.example.account.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

// CreatAccount 클래스에는 요청->응답의 형식으로 생성하고, Controller에서 요청하는 형식
// 이 코드에는 Response로 요청에 응답할 때의 코드들을 작성해주면 됨
public class CreateAccount {
    // 계좌 생성에 필요한 요청과 응답을 작성

    // 요청
    // 요청 인자 : userId, initialBalance
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @Min(1)
        private Long userId;

        @NotNull
        @Min(0)
        private Long initialBalance;
    }

    // 응답
    // 응답 인자 : userId, accountNumber, registeredAt
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long userId;
        private String accountNumber;
        private LocalDateTime registeredAt;

        public static Response from(AccountDto accountDto) {
            return Response.builder()
                    .userId(accountDto.getUserId())
                    .accountNumber(accountDto.getAccountNumber())
                    .registeredAt(accountDto.getRegisteredAt())
                    .build(); // 위의 Response 응답에 필요한 것들 return을 위하여 Dto의 get이용
        }
    }
}
