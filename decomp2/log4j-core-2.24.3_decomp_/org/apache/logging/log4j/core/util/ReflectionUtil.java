package org.apache.logging.log4j.core.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Objects;

public final class ReflectionUtil {
   private ReflectionUtil() {
   }

   public static boolean isAccessible(final AccessibleObject member) {
      Objects.requireNonNull(member, "No member provided");
      return Modifier.isPublic(((Member)member).getModifiers()) && Modifier.isPublic(((Member)member).getDeclaringClass().getModifiers());
   }

   public static void makeAccessible(final AccessibleObject member) {
      if (!isAccessible(member) && !member.isAccessible()) {
         member.setAccessible(true);
      }

   }

   public static void makeAccessible(final Field field) {
      Objects.requireNonNull(field, "No field provided");
      if ((!isAccessible(field) || Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
         field.setAccessible(true);
      }

   }

   public static Object getFieldValue(final Field field, final Object instance) {
      makeAccessible(field);
      if (!Modifier.isStatic(field.getModifiers())) {
         Objects.requireNonNull(instance, "No instance given for non-static field");
      }

      try {
         return field.get(instance);
      } catch (IllegalAccessException e) {
         throw new UnsupportedOperationException(e);
      }
   }

   public static Object getStaticFieldValue(final Field field) {
      return getFieldValue(field, (Object)null);
   }

   public static void setFieldValue(final Field field, final Object instance, final Object value) {
      makeAccessible(field);
      if (!Modifier.isStatic(field.getModifiers())) {
         Objects.requireNonNull(instance, "No instance given for non-static field");
      }

      try {
         field.set(instance, value);
      } catch (IllegalAccessException e) {
         throw new UnsupportedOperationException(e);
      }
   }

   public static void setStaticFieldValue(final Field field, final Object value) {
      setFieldValue(field, (Object)null, value);
   }

   public static Constructor getDefaultConstructor(final Class clazz) {
      Objects.requireNonNull(clazz, "No class provided");

      try {
         Constructor<T> constructor = clazz.getDeclaredConstructor();
         makeAccessible((AccessibleObject)constructor);
         return constructor;
      } catch (NoSuchMethodException var4) {
         try {
            Constructor<T> constructor = clazz.getConstructor();
            makeAccessible((AccessibleObject)constructor);
            return constructor;
         } catch (NoSuchMethodException e) {
            throw new IllegalStateException(e);
         }
      }
   }

   public static Object instantiate(final Class clazz) {
      Objects.requireNonNull(clazz, "No class provided");
      Constructor<T> constructor = getDefaultConstructor(clazz);

      try {
         return constructor.newInstance();
      } catch (InstantiationException | LinkageError e) {
         throw new IllegalArgumentException(e);
      } catch (IllegalAccessException e) {
         throw new IllegalStateException(e);
      } catch (InvocationTargetException e) {
         Throwables.rethrow(e.getCause());
         throw new InternalError("Unreachable");
      }
   }
}
