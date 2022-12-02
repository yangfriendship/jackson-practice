package me.youzheng.userservice.converter;

import me.youzheng.userservice.model.ClientType;

public interface ResponseConverter {

    <T> T convert(T source, ClientType clientType);
}
