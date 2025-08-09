package org.checkerframework.checker.formatter.qual;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;

public enum ConversionCategory {
   GENERAL("bBhHsS", (Class[])null),
   CHAR("cC", new Class[]{Character.class, Byte.class, Short.class, Integer.class}),
   INT("doxX", new Class[]{Byte.class, Short.class, Integer.class, Long.class, BigInteger.class}),
   FLOAT("eEfgGaA", new Class[]{Float.class, Double.class, BigDecimal.class}),
   TIME("tT", new Class[]{Long.class, Calendar.class, Date.class}),
   CHAR_AND_INT((String)null, new Class[]{Byte.class, Short.class, Integer.class}),
   INT_AND_TIME((String)null, new Class[]{Long.class}),
   NULL((String)null, new Class[0]),
   UNUSED((String)null, (Class[])null);

   public final Class @Nullable [] types;
   public final @Nullable String chars;
   private static final ConversionCategory[] conversionCategoriesWithChar = new ConversionCategory[]{GENERAL, CHAR, INT, FLOAT, TIME};
   private static final ConversionCategory[] conversionCategoriesForIntersect = new ConversionCategory[]{CHAR, INT, FLOAT, TIME, CHAR_AND_INT, INT_AND_TIME, NULL};
   private static final ConversionCategory[] conversionCategoriesForUnion = new ConversionCategory[]{NULL, CHAR_AND_INT, INT_AND_TIME, CHAR, INT, FLOAT, TIME};

   private ConversionCategory(String chars, Class... types) {
      this.chars = chars;
      if (types == null) {
         this.types = types;
      } else {
         List<Class<?>> typesWithPrimitives = new ArrayList(types.length);

         for(Class type : types) {
            typesWithPrimitives.add(type);
            Class<?> unwrapped = unwrapPrimitive(type);
            if (unwrapped != null) {
               typesWithPrimitives.add(unwrapped);
            }
         }

         this.types = (Class[])typesWithPrimitives.toArray(new Class[0]);
      }

   }

   private static @Nullable Class unwrapPrimitive(Class c) {
      if (c == Byte.class) {
         return Byte.TYPE;
      } else if (c == Character.class) {
         return Character.TYPE;
      } else if (c == Short.class) {
         return Short.TYPE;
      } else if (c == Integer.class) {
         return Integer.TYPE;
      } else if (c == Long.class) {
         return Long.TYPE;
      } else if (c == Float.class) {
         return Float.TYPE;
      } else if (c == Double.class) {
         return Double.TYPE;
      } else {
         return c == Boolean.class ? Boolean.TYPE : null;
      }
   }

   public static ConversionCategory fromConversionChar(char c) {
      for(ConversionCategory v : conversionCategoriesWithChar) {
         if (v.chars.contains(String.valueOf(c))) {
            return v;
         }
      }

      throw new IllegalArgumentException("Bad conversion character " + c);
   }

   private static Set arrayToSet(Object[] a) {
      return new HashSet(Arrays.asList(a));
   }

   public static boolean isSubsetOf(ConversionCategory a, ConversionCategory b) {
      return intersect(a, b) == a;
   }

   public static ConversionCategory intersect(ConversionCategory a, ConversionCategory b) {
      if (a == UNUSED) {
         return b;
      } else if (b == UNUSED) {
         return a;
      } else if (a == GENERAL) {
         return b;
      } else if (b == GENERAL) {
         return a;
      } else {
         Set<Class<?>> as = arrayToSet(a.types);
         Set<Class<?>> bs = arrayToSet(b.types);
         as.retainAll(bs);

         for(ConversionCategory v : conversionCategoriesForIntersect) {
            Set<Class<?>> vs = arrayToSet(v.types);
            if (vs.equals(as)) {
               return v;
            }
         }

         throw new RuntimeException();
      }
   }

   public static ConversionCategory union(ConversionCategory a, ConversionCategory b) {
      if (a != UNUSED && b != UNUSED) {
         if (a != GENERAL && b != GENERAL) {
            if ((a != CHAR_AND_INT || b != INT_AND_TIME) && (a != INT_AND_TIME || b != CHAR_AND_INT)) {
               Set<Class<?>> as = arrayToSet(a.types);
               Set<Class<?>> bs = arrayToSet(b.types);
               as.addAll(bs);

               for(ConversionCategory v : conversionCategoriesForUnion) {
                  Set<Class<?>> vs = arrayToSet(v.types);
                  if (vs.equals(as)) {
                     return v;
                  }
               }

               return GENERAL;
            } else {
               return INT;
            }
         } else {
            return GENERAL;
         }
      } else {
         return UNUSED;
      }
   }

   public boolean isAssignableFrom(Class argType) {
      if (this.types == null) {
         return true;
      } else if (argType == Void.TYPE) {
         return true;
      } else {
         for(Class c : this.types) {
            if (c.isAssignableFrom(argType)) {
               return true;
            }
         }

         return false;
      }
   }

   @Pure
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.name());
      sb.append(" conversion category");
      if (this.types != null && this.types.length != 0) {
         StringJoiner sj = new StringJoiner(", ", "(one of: ", ")");

         for(Class cls : this.types) {
            sj.add(cls.getSimpleName());
         }

         sb.append(" ");
         sb.append(sj);
         return sb.toString();
      } else {
         return sb.toString();
      }
   }

   // $FF: synthetic method
   private static ConversionCategory[] $values() {
      return new ConversionCategory[]{GENERAL, CHAR, INT, FLOAT, TIME, CHAR_AND_INT, INT_AND_TIME, NULL, UNUSED};
   }
}
