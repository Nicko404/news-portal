package ru.skillbox.rest.newsportal.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Objects;

@UtilityClass
public class BeanUtils {

    @SneakyThrows
    public void copyNonNullProperties(Object source, Object destination) {
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);

            if (Objects.nonNull(value)) {
                field.set(destination, value);
            }
        }
    }
}
