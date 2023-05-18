package br.com.zup.asgard.asgardbank.exception;

public class CustomerNotFoundException extends BaseException {

    public CustomerNotFoundException() {
        super ("Customer not found");
    }

}
