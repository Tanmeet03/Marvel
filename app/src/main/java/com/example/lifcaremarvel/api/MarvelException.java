package com.example.lifcaremarvel.api;

/**
 * This class <i>MarvelException</i> is custom exception class
 * <p>
 *
 * @author Tanmeet Singh Bhalla
 * @version %1%, %09/04/19%
 * @since 1.0
 */
public class MarvelException extends Exception {

    private final int mCode;
    MarvelException(int code, String message) {
        super(message);
        mCode = code;
    }
}
