package io.vertx.core.cli.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionUtils {
   public static Object newInstance(Class clazz) {
      try {
         return clazz.getDeclaredConstructor().newInstance();
      } catch (Exception e) {
         throw new IllegalArgumentException("Cannot instantiate " + clazz.getName(), e);
      }
   }

   public static boolean isSetter(Method method) {
      return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
   }

   public static List getSetterMethods(Class clazz) {
      return (List)Arrays.stream(clazz.getMethods()).filter(ReflectionUtils::isSetter).collect(Collectors.toList());
   }

   public static boolean isMultiple(Method setter) {
      Class<?> type = setter.getParameterTypes()[0];
      return type.isArray() || List.class.isAssignableFrom(type) || Set.class.isAssignableFrom(type) || Collection.class.isAssignableFrom(type);
   }

   public static Class getComponentType(Parameter parameter) {
      Class<?> type = parameter.getType();
      if (type.isArray()) {
         return type.getComponentType();
      } else if (parameter.getParameterizedType() != null) {
         return (Class)((ParameterizedType)parameter.getParameterizedType()).getActualTypeArguments()[0];
      } else {
         return parameter.getType().getGenericSuperclass() instanceof ParameterizedType ? (Class)((ParameterizedType)parameter.getType().getGenericSuperclass()).getActualTypeArguments()[0] : null;
      }
   }
}
