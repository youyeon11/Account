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
    private Long userId;
    private String accountNumber;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;


    // 특정 type에서 특정 type으로 바꿔줄때..
    public static AccountDto fromEntity(Account account) { // 굳이 this.userId = ... 이런 방식 사용하지 않아도 됨
        // 생성자를 사용하지 않고 바로 사용가능한 깔끔방법
        return AccountDto.builder() // account에서의 특징들 전부 가져오기 가능
                .userId(account.getAccountUser().getId())
                .accountNumber(account.getAccountNumber())
                .registeredAt(account.getRegisteredAt())
                .unRegisteredAt(account.getUnregisteredAt())
                .build(); // Account -> AccountDto로 변경하고 싶을 때 이 방식을 사용
    }
}
