package com.huma.common.utils;

import lombok.NonNull;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Oliver
 * @date 2021/4/26
 */
public class BeanCopierUtil {
    private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new ConcurrentHashMap<>();

    public static <S, T> T copy(@NonNull S source, @NonNull Class<T> targetClass) {
        T target;
        try {
            target = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        Class<?> sourceClass = source.getClass();
        String key = genKey(sourceClass, targetClass);
        BeanCopier copier = BEAN_COPIER_MAP.computeIfAbsent(key, k -> createBeanCopier(sourceClass, targetClass));
        copier.copy(source, target, null);
        return target;
    }

    public static void copy(@NonNull Object source, @NonNull Object target) {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();
        String key = genKey(sourceClass, targetClass);
        BeanCopier copier = BEAN_COPIER_MAP.computeIfAbsent(key, k -> createBeanCopier(sourceClass, targetClass));
        copier.copy(source, target, null);
    }

    private static String genKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.getName() + ":" + targetClass.getName();
    }

    private static BeanCopier createBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        return BeanCopier.create(sourceClass, targetClass, false);
    }
}
