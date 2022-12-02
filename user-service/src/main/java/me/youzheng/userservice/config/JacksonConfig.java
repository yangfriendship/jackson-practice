package me.youzheng.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.youzheng.userservice.converter.ResponseConverter;
import me.youzheng.userservice.converter.ResponseConverterImpl;
import me.youzheng.userservice.mapper.CommonMapper;
import me.youzheng.userservice.mixin.BaseRequestMixin;
import me.youzheng.userservice.mixin.CmsResponseMixin;
import me.youzheng.userservice.model.request.BaseRequest;
import me.youzheng.userservice.model.response.CmsResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setMixIns(this.mixins());
        return objectMapper;
    }

    @Bean
    public Map<Class<?>, Class<?>> mixins() {
        final Map<Class<?>, Class<?>> result = new HashMap<>();
        result.put(BaseRequest.class, BaseRequestMixin.class);
        result.put(CmsResponse.class, CmsResponseMixin.class);
        return result;
    }

    @Bean
    public ResponseConverter responseConverter() {
        return new ResponseConverterImpl(CommonMapper.INSTANCE, objectMapper());
    }

}