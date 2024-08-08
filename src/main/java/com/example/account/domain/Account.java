package com.example.account.domain;

import com.example.account.type.AccountStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter // 이거 쓰면 굳이 this.... 이런 거 안 해도 됨
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account {
    /*
    Account에 필요한 것들 : id, 소유자 정보, 계좌번호, 계좌상태(inUse or unregistered), 계좌잔액, 계좌등록일시, 계좌해지일시, 생성일시, 최종수정일시
     */
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private AccountUser accountUser;
    private String accountNumber;

    @Enumerated(EnumType.STRING) // 열거형 클래스를 사용하기 위하여 설정한 어노테이션
    private AccountStatus accountStatus;
    private Long balance;

    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
