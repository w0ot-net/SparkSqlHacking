package org.checkerframework.checker.i18nformatter.qual;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import org.checkerframework.checker.nullness.qual.Nullable;

public enum I18nConversionCategory {
   UNUSED((Class[])null, (String[])null),
   GENERAL((Class[])null, (String[])null),
   DATE(new Class[]{Date.class, Number.class}, new String[]{"date", "time"}),
   NUMBER(new Class[]{Number.class}, new String[]{"number", "choice"});

   public final Class @Nullable [] types;
   public final String @Nullable [] strings;
   private static final I18nConversionCategory[] namedCategories = new I18nConversionCategory[]{DATE, NUMBER};
   private static final I18nConversionCategory[] conversionCategoriesForIntersect = new I18nConversionCategory[]{DATE, NUMBER};

   private I18nConversionCategory(Class[] types, String[] strings) {
      this.types = types;
      this.strings = strings;
   }

   public static I18nConversionCategory stringToI18nConversionCategory(String string) {
      string = string.toLowerCase();

      for(I18nConversionCategory v : namedCategories) {
         for(String s : v.strings) {
            if (s.equals(string)) {
               return v;
            }
         }
      }

      throw new IllegalArgumentException("Invalid format type " + string);
   }

   private static Set arrayToSet(Object[] a) {
      return new HashSet(Arrays.asList(a));
   }

   public static boolean isSubsetOf(I18nConversionCategory a, I18nConversionCategory b) {
      return intersect(a, b) == a;
   }

   public static I18nConversionCategory intersect(I18nConversionCategory a, I18nConversionCategory b) {
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

         for(I18nConversionCategory v : conversionCategoriesForIntersect) {
            Set<Class<?>> vs = arrayToSet(v.types);
            if (vs.equals(as)) {
               return v;
            }
         }

         throw new RuntimeException();
      }
   }

   public static I18nConversionCategory union(I18nConversionCategory a, I18nConversionCategory b) {
      if (a != UNUSED && b != UNUSED) {
         if (a != GENERAL && b != GENERAL) {
            return a != DATE && b != DATE ? NUMBER : DATE;
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

   public String toString() {
      StringBuilder sb = new StringBuilder(this.name());
      if (this.types == null) {
         sb.append(" conversion category (all types)");
      } else {
         StringJoiner sj = new StringJoiner(", ", " conversion category (one of: ", ")");

         for(Class cls : this.types) {
            sj.add(cls.getCanonicalName());
         }

         sb.append(sj);
      }

      return sb.toString();
   }

   // $FF: synthetic method
   private static I18nConversionCategory[] $values() {
      return new I18nConversionCategory[]{UNUSED, GENERAL, DATE, NUMBER};
   }
}
