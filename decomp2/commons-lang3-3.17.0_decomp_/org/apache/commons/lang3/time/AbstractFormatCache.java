package org.apache.commons.lang3.time;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.LocaleUtils;

abstract class AbstractFormatCache {
   static final int NONE = -1;
   private static final ConcurrentMap cDateTimeInstanceCache = new ConcurrentHashMap(7);
   private final ConcurrentMap cInstanceCache = new ConcurrentHashMap(7);

   static String getPatternForStyle(Integer dateStyle, Integer timeStyle, Locale locale) {
      Locale safeLocale = LocaleUtils.toLocale(locale);
      ArrayKey key = new ArrayKey(new Object[]{dateStyle, timeStyle, safeLocale});
      return (String)cDateTimeInstanceCache.computeIfAbsent(key, (k) -> {
         try {
            DateFormat formatter;
            if (dateStyle == null) {
               formatter = DateFormat.getTimeInstance(timeStyle, safeLocale);
            } else if (timeStyle == null) {
               formatter = DateFormat.getDateInstance(dateStyle, safeLocale);
            } else {
               formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle, safeLocale);
            }

            return ((SimpleDateFormat)formatter).toPattern();
         } catch (ClassCastException var5) {
            throw new IllegalArgumentException("No date time pattern for locale: " + safeLocale);
         }
      });
   }

   protected abstract Format createInstance(String var1, TimeZone var2, Locale var3);

   Format getDateInstance(int dateStyle, TimeZone timeZone, Locale locale) {
      return this.getDateTimeInstance(dateStyle, (Integer)null, timeZone, locale);
   }

   Format getDateTimeInstance(int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
      return this.getDateTimeInstance(dateStyle, timeStyle, timeZone, locale);
   }

   private Format getDateTimeInstance(Integer dateStyle, Integer timeStyle, TimeZone timeZone, Locale locale) {
      locale = LocaleUtils.toLocale(locale);
      String pattern = getPatternForStyle(dateStyle, timeStyle, locale);
      return this.getInstance(pattern, timeZone, locale);
   }

   public Format getInstance() {
      return this.getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
   }

   public Format getInstance(String pattern, TimeZone timeZone, Locale locale) {
      Objects.requireNonNull(pattern, "pattern");
      TimeZone actualTimeZone = TimeZones.toTimeZone(timeZone);
      Locale actualLocale = LocaleUtils.toLocale(locale);
      ArrayKey key = new ArrayKey(new Object[]{pattern, actualTimeZone, actualLocale});
      return (Format)this.cInstanceCache.computeIfAbsent(key, (k) -> this.createInstance(pattern, actualTimeZone, actualLocale));
   }

   Format getTimeInstance(int timeStyle, TimeZone timeZone, Locale locale) {
      return this.getDateTimeInstance((Integer)null, timeStyle, timeZone, locale);
   }

   private static final class ArrayKey {
      private final Object[] keys;
      private final int hashCode;

      private static int computeHashCode(Object[] keys) {
         int prime = 31;
         int result = 1;
         result = 31 * result + Arrays.hashCode(keys);
         return result;
      }

      ArrayKey(Object... keys) {
         this.keys = keys;
         this.hashCode = computeHashCode(keys);
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            ArrayKey other = (ArrayKey)obj;
            return Arrays.deepEquals(this.keys, other.keys);
         }
      }

      public int hashCode() {
         return this.hashCode;
      }
   }
}
