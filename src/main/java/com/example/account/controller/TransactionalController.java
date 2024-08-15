package com.example.account.controller;

/*
잔액 관련 컨트롤러
1. 잔액 사용
2. 잔액 사용 취소
3. 거래 확인
 */

import com.example.account.dto.CancelBalance;
import com.example.account.dto.QueryTransactionResponse;
import com.example.account.dto.TransactionDto;
import com.example.account.dto.UseBalance;
import com.example.account.exception.AccountException;
import com.example.account.repository.TransactionRepository;
import com.example.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TransactionalController {

    private final TransactionService transactionService;
    private final TransactionRepository transactionRepository;

    @PostMapping("/transaction/use")
    public UseBalance.Response useBalance(
            @Valid @RequestBody UseBalance.Request request // 최초 validation을 실시
    ) {
        try {
            return UseBalance.Response.from(transactionService.useBalance(request.getUserId(),
                    request.getAccountNumber(), request.getAmount())
            );
        } catch (AccountException e) {
            // 비즈니스적으로 발생한 에러에 대하여 처리하기 위한 코드
            log.error("Failed to use balance. "); // 에러에 대한 로그(기록)

            transactionService.saveFailedUseTransaction(
                    request.getAccountNumber(),
                    request.getAmount()
            );

            throw e; // 밖으로 던져주기
        }
    }

    public CancelBalance.Response cancelBalance(
            @Valid @RequestBody CancelBalance.Request request // 최초 validation을 실시
    ) {
        try {
            return CancelBalance.Response.from(
                    transactionService.cancelBalance(
                    request.getTransactionId(),
                    request.getAccountNumber(), request.getAmount()
                    )
            );
        } catch (AccountException e) {
            // 비즈니스적으로 발생한 에러에 대하여 처리하기 위한 코드
            log.error("Failed to cancel balance. "); // 에러에 대한 로그(기록)

            transactionService.saveFailedCancelTransaction(
                    request.getAccountNumber(),
                    request.getAmount()
            );

            throw e; // 밖으로 던져주기
        }
    }

    @GetMapping("/transaction/{transactionId}")
    public QueryTransactionResponse queryTransactionResponse(
            @PathVariable String transactionId
    ) {
        return QueryTransactionResponse.from(
                transactionService.queryTransaction(transactionId)
        );

    }
}
