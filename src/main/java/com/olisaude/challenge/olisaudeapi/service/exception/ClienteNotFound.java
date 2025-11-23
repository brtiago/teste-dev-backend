package com.olisaude.challenge.olisaudeapi.service.exception;

public class ClienteNotFound extends RuntimeException {
    public ClienteNotFound(String message) {
        super(message);
    }
}
