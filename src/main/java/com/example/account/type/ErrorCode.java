package com.example.account.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode { // 에러에 대한 유형들
    // 열거형 클래스로 정의
    USER_NOT_FOUND("사용자가 없습니다."), // 해당 부분이 description
    ACCOUNT_NOT_FOUND("사용자가 없습니다."),
    MAX_ACCOUNT_PER_USER_10("사용자 최대 계좌는 10개입니다."),
    USER_ACCOUNT_UN_MATCH("사용자와 계좌의 소유주가 다릅니다."),
    ACCOUNT_ALREADY_UNREGISTERED("계좌가 이미 해지되었습니다."),
    ACCOUNT_HAS_BALANCE("잔액이 있는 계좌는 해지할 수 없습니다."),
    AMOUNT_EXCEED_BALANCE("거래금액이 계좌잔액보다 큽니다.")

    ;

    private final String description;
}
