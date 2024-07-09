package com.mevy.taskcontrolapi.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(Class<?> resourceClass, Object identifier) {
        super(
            String.format("%s not found. Identifier: %s", resourceClass.getSimpleName(), identifier)
        );
    }

}
