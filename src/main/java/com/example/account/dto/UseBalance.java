package com.example.account.dto;

import com.example.account.type.TransactionResultType;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class UseBalance { // CreatAccount와 상당히 비슷한 구조로 작성됨
    // 요청
    // 요청 인자 : userId, accountNumber, amount
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Request {
        @NotNull
        @Min(1)
        private Long userId;

        @NotBlank
        @Size(min = 10, max =10)
        private String accountNumber;

        @NotNull
        @Min(0)
        @Max(1000_000_000)
        private Long amount;
    }

    // 응답
    // 응답params: accountNumber, transactionResultType, ....
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String accountNumber;
        private TransactionResultType transactionType;
        private String TransactionId;
        private Long amount;
        private LocalDateTime transactedAt;

        public static UseBalance.Response from(AccountDto accountDto) {
            }
    }

}
