package com.example.account.exception;

import com.example.account.domain.Account;
import com.example.account.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountException extends RuntimeException { // 그대로 RuntimeException을 상속, 메소드 사용
    private ErrorCode errorCode;
    private String errorMessage;

    public AccountException(ErrorCode errorCode) { // service에서의 코드 오류 해결을 위하여 메소드 구현
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
