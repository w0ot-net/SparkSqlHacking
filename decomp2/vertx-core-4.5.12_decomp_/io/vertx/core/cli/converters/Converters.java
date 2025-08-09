package io.vertx.core.cli.converters;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Converters {
   private static final Map PRIMITIVE_TO_WRAPPER_TYPE;
   private static final Map WELL_KNOWN_CONVERTERS;

   public static Object create(Class type, String value) {
      if (type.isPrimitive()) {
         type = wrap(type);
      }

      return getConverter(type).fromString(value);
   }

   public static Object create(String value, Converter converter) {
      return converter.fromString(value);
   }

   private static Class wrap(Class type) {
      Class<T> wrapped = (Class)PRIMITIVE_TO_WRAPPER_TYPE.get(type);
      return wrapped == null ? type : wrapped;
   }

   private static Converter getConverter(Class type) {
      if (WELL_KNOWN_CONVERTERS.containsKey(type)) {
         return (Converter)WELL_KNOWN_CONVERTERS.get(type);
      } else {
         Converter<T> converter = ConstructorBasedConverter.getIfEligible(type);
         if (converter != null) {
            return converter;
         } else {
            converter = ValueOfBasedConverter.getIfEligible(type);
            if (converter != null) {
               return converter;
            } else {
               converter = FromBasedConverter.getIfEligible(type);
               if (converter != null) {
                  return converter;
               } else {
                  converter = FromStringBasedConverter.getIfEligible(type);
                  if (converter != null) {
                     return converter;
                  } else {
                     throw new NoSuchElementException("Cannot find a converter able to create instance of " + type.getName());
                  }
               }
            }
         }
      }
   }

   public static Converter newInstance(Class type) throws IllegalArgumentException {
      try {
         return (Converter)type.getDeclaredConstructor().newInstance();
      } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
         throw new IllegalArgumentException("Cannot create a new instance of " + type.getName() + " - it requires an public constructor without argument", e);
      }
   }

   static {
      Map<Class<?>, Class<?>> primToWrap = new HashMap(16);
      primToWrap.put(Boolean.TYPE, Boolean.class);
      primToWrap.put(Byte.TYPE, Byte.class);
      primToWrap.put(Character.TYPE, Character.class);
      primToWrap.put(Double.TYPE, Double.class);
      primToWrap.put(Float.TYPE, Float.class);
      primToWrap.put(Integer.TYPE, Integer.class);
      primToWrap.put(Long.TYPE, Long.class);
      primToWrap.put(Short.TYPE, Short.class);
      primToWrap.put(Void.TYPE, Void.class);
      PRIMITIVE_TO_WRAPPER_TYPE = Collections.unmodifiableMap(primToWrap);
      Map<Class<?>, Converter<?>> wellKnown = new HashMap(16);
      wellKnown.put(Boolean.class, BooleanConverter.INSTANCE);
      wellKnown.put(Byte.class, Byte::parseByte);
      wellKnown.put(Character.class, CharacterConverter.INSTANCE);
      wellKnown.put(Double.class, Double::parseDouble);
      wellKnown.put(Float.class, Float::parseFloat);
      wellKnown.put(Integer.class, Integer::parseInt);
      wellKnown.put(Long.class, Long::parseLong);
      wellKnown.put(Short.class, Short::parseShort);
      wellKnown.put(String.class, (Converter)(value) -> value);
      WELL_KNOWN_CONVERTERS = Collections.unmodifiableMap(wellKnown);
   }
}
