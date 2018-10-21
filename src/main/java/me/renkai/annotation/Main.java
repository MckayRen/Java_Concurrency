package me.renkai.annotation;

import java.lang.reflect.Field;

/**
 * Created by Mckay on 2018/5/30.
 */
public class Main {
    public static void main(String[] args) {
        RequestBody requestBody = new RequestBody();
        requestBody.setId("123");

        check(requestBody);
    }

    private static void check(Object body) {
        Class bodyClass = body.getClass();
        Field[] bodyFields = bodyClass.getDeclaredFields();
        for (Field field : bodyFields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = null;
                try {
                    fieldValue = field.get(body);
                } catch (IllegalAccessException i) {
                    i.printStackTrace();
                }

                if(fieldValue == null) {
                    throw new IllegalArgumentException(fieldName + "不允许空！");
                }
            }
        }
    }

}
