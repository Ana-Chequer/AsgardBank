package br.com.zup.asgard.asgardbank.exception;

public class CustomerNotDeletedException extends BaseException {

    public CustomerNotDeletedException() {
        super("Customer must not be removed");
    }
}
