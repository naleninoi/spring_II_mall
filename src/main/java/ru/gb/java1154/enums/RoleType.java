package ru.gb.java1154.enums;

public enum RoleType {
    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_ADMIN("ROLE_ADMIN")
    ;

    private final String roleName;

    RoleType(String roleName) { this.roleName = roleName; }

    @Override
    public String toString() {
        return roleName;
    }
}
