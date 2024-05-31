package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.eteration.simplebanking.model.Dtos.Responses.AccountResponse;
import com.eteration.simplebanking.model.Dtos.Responses.TransactionDetailResponse;
import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.Exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.services.AccountService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountService service;





    @Test
    public void givenId_Credit_thenReturnJson()
            throws Throwable {

        AccountResponse account = new AccountResponse("17892", "Kerem Karaca",0,null,null);

        doReturn(account).when(service).getAccountDetails( "17892");
        ResponseEntity<TransactionDetailResponse> result = controller.credit( "17892", new DepositTransaction(1000.0));
       // verify(service, times(2)).getAccountDetails("17892");
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
            throws Throwable {

        AccountResponse account = new AccountResponse("Kerem Karaca", "17892",0,null,null);

        doReturn(account).when(service).getAccountDetails( "17892");
        ResponseEntity<TransactionDetailResponse> result = controller.credit( "17892", new DepositTransaction(1000.0));
        ResponseEntity<TransactionDetailResponse> result2 = controller.debit( "17892", new WithdrawalTransaction(50.0));
        //verify(service, times(2)).getAccountDetails("17892");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(HttpStatus.OK, result2.getStatusCode());
        //assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            AccountResponse account = new AccountResponse("Kerem Karaca", "17892",1000,null,null);

            doReturn(account).when(service).getAccountDetails( "17892");
            ResponseEntity<TransactionDetailResponse> result = controller.credit( "17892", new DepositTransaction(1000.0));
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertEquals(1000.0, account.getBalance(),0.001);
            //verify(service, times(1)).getAccountDetails("17892");

            ResponseEntity<TransactionDetailResponse> result2 = controller.debit( "17892", new WithdrawalTransaction(5000.0));
        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
            throws Throwable {

        AccountResponse account = new AccountResponse("Kerem Karaca", "17892",0,null,null);

        doReturn(account).when(service).getAccountDetails( "17892");
        ResponseEntity<AccountResponse> result = controller.getAccount( "17892");
        verify(service, times(1)).getAccountDetails("17892");
        assertEquals(account, result.getBody());
    }

}
