package com.andreafilice.lavorami;

public class EmptyDialogParameterException extends RuntimeException {
    public EmptyDialogParameterException() {
        super("The variable passed as a parameter is empty. Check the titleText and depsText parameters in your call.");
    }
}
