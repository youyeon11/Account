package com.example.account.dto;

import com.example.account.domain.Account;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    // response에 필요한 내용들
    private Long userId;
    private String accountNumber;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;


    // Entity를 Dto로 변환해주는 static 메소드를 작성
    public static AccountDto fromEntity(Account account) {
        return AccountDto.builder() // account에서의 특징들 전부 가져오기 가능
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .registeredAt(account.getRegisteredAt())
                .unRegisteredAt(account.getUnRegisteredAt())
                .build(); // Account -> AccountDto로 변경하고 싶을 때 이 방식을 사용
    }
}
