package me.youzheng.userservice.model;

import java.util.Arrays;

public enum ClientType {

    HOMEPAGE, CMS;

    public static ClientType findBy(final String key) {
        return Arrays.stream(ClientType.values())
                .filter(t -> t.name().toLowerCase().equals(key))
                .findFirst()
                .orElseThrow();
    }

}
