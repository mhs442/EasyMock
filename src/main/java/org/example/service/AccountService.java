package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.AccountManager;

@AllArgsConstructor
@Builder
public class AccountService {
    private AccountManager accountManager;

    public void transfer(String senderId, String beneficiaryId, long amount){
        Account senderAccount = accountManager.findAccountForUser(senderId);
        Account beneficiaryAccount = accountManager.findAccountForUser(beneficiaryId);

        senderAccount.debit(amount);
        beneficiaryAccount.credit(amount);

        accountManager.updateAccount(senderAccount);
        accountManager.updateAccount(beneficiaryAccount);
    }
}
