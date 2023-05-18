package br.com.zup.asgard.asgardbank.exception;

public class AccountAlreadyExistsException extends BaseException {

    public AccountAlreadyExistsException() {
        super("Account already exists");
    }
}
