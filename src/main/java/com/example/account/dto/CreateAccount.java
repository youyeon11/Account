package com.example.account.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class CreateAccount { // java class 생성해 controller에서 클래스 사용

    // 요청
    // 요청 인자 : userId, initialBalance
    @Getter
    @Setter
    public static class Request {
        @NotNull
        @Min(1)
        private Long userId;

        // annotaion을 통하여 데이터 칼럼에 대한 기본적인 설정 완료
        @NotNull
        @Min(100)
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
