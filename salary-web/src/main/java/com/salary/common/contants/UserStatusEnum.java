package com.salary.common.contants;

public enum UserStatusEnum {

    IENABLE("1","可用"),
    UNENABLE("2","不可用"),
    ;
    private String code;
    private String name;

    UserStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
