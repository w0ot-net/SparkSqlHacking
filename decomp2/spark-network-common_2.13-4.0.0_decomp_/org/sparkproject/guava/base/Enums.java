package org.sparkproject.guava.base;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
@J2ktIncompatible
public final class Enums {
   @GwtIncompatible
   private static final Map enumConstantCache = new WeakHashMap();

   private Enums() {
   }

   @GwtIncompatible
   public static Field getField(Enum enumValue) {
      Class<?> clazz = enumValue.getDeclaringClass();

      try {
         return clazz.getDeclaredField(enumValue.name());
      } catch (NoSuchFieldException impossible) {
         throw new AssertionError(impossible);
      }
   }

   public static Optional getIfPresent(Class enumClass, String value) {
      Preconditions.checkNotNull(enumClass);
      Preconditions.checkNotNull(value);
      return Platform.getEnumIfPresent(enumClass, value);
   }

   @GwtIncompatible
   private static Map populateCache(Class enumClass) {
      Map<String, WeakReference<? extends Enum<?>>> result = new HashMap();

      for(Enum enumInstance : EnumSet.allOf(enumClass)) {
         result.put(enumInstance.name(), new WeakReference(enumInstance));
      }

      enumConstantCache.put(enumClass, result);
      return result;
   }

   @GwtIncompatible
   static Map getEnumConstants(Class enumClass) {
      synchronized(enumConstantCache) {
         Map<String, WeakReference<? extends Enum<?>>> constants = (Map)enumConstantCache.get(enumClass);
         if (constants == null) {
            constants = populateCache(enumClass);
         }

         return constants;
      }
   }

   @GwtIncompatible
   public static Converter stringConverter(Class enumClass) {
      return new StringConverter(enumClass);
   }

   @GwtIncompatible
   private static final class StringConverter extends Converter implements Serializable {
      private final Class enumClass;
      private static final long serialVersionUID = 0L;

      StringConverter(Class enumClass) {
         this.enumClass = (Class)Preconditions.checkNotNull(enumClass);
      }

      protected Enum doForward(String value) {
         return Enum.valueOf(this.enumClass, value);
      }

      protected String doBackward(Enum enumValue) {
         return enumValue.name();
      }

      public boolean equals(@CheckForNull Object object) {
         if (object instanceof StringConverter) {
            StringConverter<?> that = (StringConverter)object;
            return this.enumClass.equals(that.enumClass);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.enumClass.hashCode();
      }

      public String toString() {
         return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
      }
   }
}
