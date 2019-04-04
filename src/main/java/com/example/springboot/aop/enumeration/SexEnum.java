package com.example.springboot.aop.enumeration;

public enum SexEnum {
    MALE(1, "MALE"),
    FEMALE(2, "FEMALE");

    public int getId() {
        return id;
    }

    private int id;

    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public static SexEnum getEnumById(int id)
    {
        for (SexEnum sex: SexEnum.values())
        {
            if (sex.id == id)
                return sex;
        }
        return null;
    }
}
