package com.example.account.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class DeleteAccount {

    // 요청
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @Min(1) // 이런것이 Validation
        private Long userId;

        // annotaion을 통하여 데이터 칼럼에 대한 기본적인 설정 완료 = Validation
        @NotBlank
        @Size(min =10, max =10) // 문자열의 길이 체크해주는 Size
        private String accountNumber; // create와는 다르게 이미 존재하는 계좌번호를 인자로 입력
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
        private LocalDateTime unRegisteredAt;

        public static Response from(AccountDto accountDto) {
            return Response.builder()
                    .userId(accountDto.getUserId())
                    .accountNumber(accountDto.getAccountNumber())
                    .unRegisteredAt(accountDto.getRegisteredAt())
                    .build(); // 위의 Response 응답에 필요한 것들 return을 위하여 Dto의 get이용
        } // 성공 응답 : 사용자 아이디, 계좌번호, 해지일시
    }
}
