package org.example.service;


import org.example.AccountManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountServiceEasyMock {
    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp(){
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk(){
        Account senderAccount = Account.builder()
                .accountId("1")
                .balance(200)
                .build();

        Account beneficiaryAccount = Account.builder()
                .accountId("2")
                .balance(100)
                .build();

        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

        // userId가 1인 Account 객체를 반환 하기를 기대한다.
        // 기대값으로는 이전에 userId를 1로 선언해둔 senderAccount이다.
        // andReturn을 호출하게 되면 ~ 값을 리턴하게 될 것이라는 것을 기대한다
        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        // userId가 2인 Account 객체를 반환하기를 기대한다.
        // 기대값으로는 이전에 userId를 2로 선언해둔 senderAccount이다.
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

        // 기대 효과를 정의하고 나면 replay를 호출한다. 해당 메서드를 호출하게 되면 모의 객체가 기대한대로 동작한다.
        replay(mockAccountManager);

        AccountService accountService = AccountService.builder()
                .accountManager(mockAccountManager)
                .build();

        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    @AfterEach
    public void tearDown(){
        // easymock 을 사용하면 어떠한 모의 객체든 verify 메서드를 호출하여 이전에 선언했던 메서드 호출에 대한 기대가 충족되었는지 검증할 수 있다.
        verify(mockAccountManager);
    }
}
