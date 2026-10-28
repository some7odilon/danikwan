package com.danikwan.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }


    public ResourceNotFoundException(String ressource,  Object id) {
        super(ressource + "introuvable" + id);
    }
    
}
