package org.example;

import org.example.service.Account;

public interface AccountManager {

    public void addAccount(String userId, Account account);
    public Account findAccountForUser(String userId);
    public void updateAccount(Account account);
}
