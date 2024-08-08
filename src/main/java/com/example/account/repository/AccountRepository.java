package com.example.account.repository;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findFirstByOrderByIdDesc(); // 내림차순정렬과 비슷

    // 계좌번호를 통하여 사용자를 찾기
    Integer countByAccountUser(AccountUser accountUser);

    Optional<Account> findByAccountNumber(String AccountNumber);
}
