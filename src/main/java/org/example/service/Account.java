package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Account {
    private String accountId;
    private long balance;

    public void debit(long amount){
        balance -= amount;
    }

    public void credit(long amount){
        balance += amount;
    }
}
