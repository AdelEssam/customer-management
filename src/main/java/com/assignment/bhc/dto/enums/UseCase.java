package com.assignment.bhc.dto.enums;

public enum UseCase {

    OPEN_NEW_ACCOUNT("openAccount");

    private final String name;

    UseCase(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}