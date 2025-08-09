package org.sparkproject.guava.base;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class Defaults {
   private static final Double DOUBLE_DEFAULT = (double)0.0F;
   private static final Float FLOAT_DEFAULT = 0.0F;

   private Defaults() {
   }

   @CheckForNull
   public static Object defaultValue(Class type) {
      Preconditions.checkNotNull(type);
      if (type.isPrimitive()) {
         if (type == Boolean.TYPE) {
            return Boolean.FALSE;
         }

         if (type == Character.TYPE) {
            return '\u0000';
         }

         if (type == Byte.TYPE) {
            return 0;
         }

         if (type == Short.TYPE) {
            return Short.valueOf((short)0);
         }

         if (type == Integer.TYPE) {
            return 0;
         }

         if (type == Long.TYPE) {
            return 0L;
         }

         if (type == Float.TYPE) {
            return FLOAT_DEFAULT;
         }

         if (type == Double.TYPE) {
            return DOUBLE_DEFAULT;
         }
      }

      return null;
   }
}
