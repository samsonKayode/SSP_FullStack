package com.ssp.backend.enums;

public enum RoleTypes {

    USER("User"),
    ADMIN("Administrator");

    public final String label;

    RoleTypes(String label) {
        this.label = label;
    }
}
