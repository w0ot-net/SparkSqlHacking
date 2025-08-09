package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

public class ConstructorUtils {
   public static Constructor getAccessibleConstructor(Class cls, Class... parameterTypes) {
      Objects.requireNonNull(cls, "cls");

      try {
         return getAccessibleConstructor(cls.getConstructor(parameterTypes));
      } catch (NoSuchMethodException var3) {
         return null;
      }
   }

   public static Constructor getAccessibleConstructor(Constructor ctor) {
      Objects.requireNonNull(ctor, "ctor");
      return MemberUtils.isAccessible(ctor) && isAccessible(ctor.getDeclaringClass()) ? ctor : null;
   }

   public static Constructor getMatchingAccessibleConstructor(Class cls, Class... parameterTypes) {
      Objects.requireNonNull(cls, "cls");

      try {
         return (Constructor)MemberUtils.setAccessibleWorkaround(cls.getConstructor(parameterTypes));
      } catch (NoSuchMethodException var9) {
         Constructor<T> result = null;
         Constructor<?>[] ctors = cls.getConstructors();

         for(Constructor ctor : ctors) {
            if (MemberUtils.isMatchingConstructor(ctor, parameterTypes)) {
               ctor = getAccessibleConstructor(ctor);
               if (ctor != null) {
                  MemberUtils.setAccessibleWorkaround(ctor);
                  if (result == null || MemberUtils.compareConstructorFit(ctor, result, parameterTypes) < 0) {
                     result = ctor;
                  }
               }
            }
         }

         return result;
      }
   }

   public static Object invokeConstructor(Class cls, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
      args = ArrayUtils.nullToEmpty(args);
      return invokeConstructor(cls, args, ClassUtils.toClass(args));
   }

   public static Object invokeConstructor(Class cls, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
      args = ArrayUtils.nullToEmpty(args);
      parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
      Constructor<T> ctor = getMatchingAccessibleConstructor(cls, parameterTypes);
      if (ctor == null) {
         throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
      } else {
         if (ctor.isVarArgs()) {
            Class<?>[] methodParameterTypes = ctor.getParameterTypes();
            args = MethodUtils.getVarArgs(args, methodParameterTypes);
         }

         return ctor.newInstance(args);
      }
   }

   public static Object invokeExactConstructor(Class cls, Object... args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
      args = ArrayUtils.nullToEmpty(args);
      return invokeExactConstructor(cls, args, ClassUtils.toClass(args));
   }

   public static Object invokeExactConstructor(Class cls, Object[] args, Class[] parameterTypes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
      args = ArrayUtils.nullToEmpty(args);
      parameterTypes = ArrayUtils.nullToEmpty(parameterTypes);
      Constructor<T> ctor = getAccessibleConstructor(cls, parameterTypes);
      if (ctor == null) {
         throw new NoSuchMethodException("No such accessible constructor on object: " + cls.getName());
      } else {
         return ctor.newInstance(args);
      }
   }

   private static boolean isAccessible(Class type) {
      for(Class<?> cls = type; cls != null; cls = cls.getEnclosingClass()) {
         if (!ClassUtils.isPublic(cls)) {
            return false;
         }
      }

      return true;
   }
}
