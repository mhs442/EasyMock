package org.example;


import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.service.Account;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Builder
public class MockAccountManager implements AccountManager {
    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(String userId, Account account){
        accounts.put(userId, account);
    }

    public Account findAccountForUser(String userId){
        return accounts.get("userId");
    }

    public void updateAccount(Account account){
    }
}
