package me.youzheng.userservice.converter;

import lombok.RequiredArgsConstructor;
import me.youzheng.userservice.model.ClientType;
import org.springframework.core.convert.converter.Converter;

@RequiredArgsConstructor
public class ClientTypeEnumConverter implements Converter<String, ClientType> {

    @Override
    public ClientType convert(final String source) {
        return ClientType.findBy(source.toLowerCase());
    }

}