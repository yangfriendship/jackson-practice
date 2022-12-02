package me.youzheng.userservice.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.youzheng.userservice.mapper.CommonMapper;
import me.youzheng.userservice.model.ClientType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ResponseConverterImpl implements ResponseConverter {

    private final Map<Class<?>, Method> store;
    private final CommonMapper commonMapper;

    public ResponseConverterImpl(final CommonMapper commonMapper, final ObjectMapper objectMapper) {
        this.commonMapper = commonMapper;
        this.store = createMap(commonMapper);
        validateMixin(this.store, objectMapper);
    }

    private void validateMixin(final Map<Class<?>, Method> store, final ObjectMapper objectMapper) {
        final StringBuilder sb = new StringBuilder();
        store.values().stream()
                .map(Method::getReturnType)
                .forEach(m -> {
                    if (objectMapper.findMixInClassFor(m) == null) {
                        sb.append(m.getClass()).append("의 Mixin 존재하지 않습니다.");
                    }
                });
        if (0 != sb.length()) {
            log.error(sb.toString());
        }
    }

    private Map<Class<?>, Method> createMap(final CommonMapper commonMapper) {
        final Map<Class<?>, Method> result = new HashMap<>();
        final Method[] methods = commonMapper.getClass().getDeclaredMethods();
        Arrays.stream(methods)
                .filter(this::requireOneParameter)
                .filter(this::hasReturnValue)
                .peek(this::checkInheritanceRelation)
                .forEach(method -> result.put(method.getParameters()[0].getType(), method));
        return result;
    }

    private void checkInheritanceRelation(final Method method) {

    }

    private boolean hasReturnValue(final Method method) {
        return method.getReturnType() != Void.class;
    }

    private boolean requireOneParameter(final Method m) {
        return m.getParameterCount() == 1;
    }

    @Override
    public <T> T convert(final T source, final ClientType clientType) {
        if (ClientType.HOMEPAGE == clientType || source == null) {
            return source;
        }
        final Method method = store.get(source.getClass());
        if (method == null) {
            return source;
        }
        try {
            return (T) method.invoke(this.commonMapper, source);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
