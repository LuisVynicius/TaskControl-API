package com.mevy.taskcontrolapi.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserProfileEnum {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");
    
    private int code;
    private String description;

    public static UserProfileEnum valueOf(int code) {
        for (UserProfileEnum profile : UserProfileEnum.values()) {
            if (profile.getCode() == code) {
                return profile;
            }
        }
        throw new IllegalArgumentException(code + " is not valid code. ");
    }

}
