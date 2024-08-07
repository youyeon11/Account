package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.dto.*;
import com.example.account.exception.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Error;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static com.example.account.type.AccountStatus.IN_USE;

@Service
@RequiredArgsConstructor
public class AccountService { // AccountService에 서비스에 대한 클래스 생성
    // repository 패키지에 있는 것들 import
    private final AccountRepository accountRepository; // 생성자를 통해 값을 참조할 때 private final 사용(초기화 가능)
    private final AccountUserRepository accountUserRepository;

    /**
     * 사용자가 있는지 조회
     * 계좌의 번호를 생성
     * 계좌 저장 하고 정보 넘김
     */


    @Transactional
    public AccountDto createAccount(Long userId, Long initialBalance) { // void: return 값 없이 쓰이는 것
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(ErrorCode.USER_NOT_FOUND)); // 예외 및 오류 처리
        // 원래 직접 string을 작성해도 되지만, errorcode의 클래스를 이용하기
        String newAccountNumber = accountRepository.findFirstByOrderByIdDesc()
                .map(account -> (Integer.parseInt(account.getAccountNumber())) + 1 + "")
                .orElse("1000000000"); // 예외 시 계좌번호에 대하여 다음과 같이 저장

        // 예외를 처리한 이후에 저장할 내용들 저장
        Account account = accountRepository.save( // account에 필요한 정보들 전부 저장
                Account.builder()
                        .accountUser(accountUser)
                        .accountStatus(IN_USE)
                        .accountNumber(newAccountNumber)
                        .balance(initialBalance)
                        .registeredAt(LocalDateTime.now())
                        .build()
        );

        return AccountDto.fromEntity(account); // 앞의 account에서의 속성들을 전부 그대로 사용(생성자 대신)
    }

    @Transactional
    public Account getAccount(Long id) {
        if(id < 0){
            throw new RuntimeException("Minus");
        }
        return accountRepository.findById(id).get();
    }
}
