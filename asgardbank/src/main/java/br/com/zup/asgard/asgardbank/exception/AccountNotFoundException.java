package br.com.zup.asgard.asgardbank.exception;

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
        super("Account not found");
    }
    
}

