package org.apache.logging.log4j.core.util.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

abstract class FormatCache {
   static final int NONE = -1;
   private final ConcurrentMap cInstanceCache = new ConcurrentHashMap(7);
   private static final ConcurrentMap cDateTimeInstanceCache = new ConcurrentHashMap(7);

   public Format getInstance() {
      return this.getDateTimeInstance(3, 3, TimeZone.getDefault(), Locale.getDefault());
   }

   public Format getInstance(final String pattern, TimeZone timeZone, Locale locale) {
      if (pattern == null) {
         throw new NullPointerException("pattern must not be null");
      } else {
         if (timeZone == null) {
            timeZone = TimeZone.getDefault();
         }

         if (locale == null) {
            locale = Locale.getDefault();
         }

         MultipartKey key = new MultipartKey(new Object[]{pattern, timeZone, locale});
         F format = (F)((Format)this.cInstanceCache.get(key));
         if (format == null) {
            format = (F)this.createInstance(pattern, timeZone, locale);
            F previousValue = (F)((Format)this.cInstanceCache.putIfAbsent(key, format));
            if (previousValue != null) {
               format = previousValue;
            }
         }

         return format;
      }
   }

   protected abstract Format createInstance(String pattern, TimeZone timeZone, Locale locale);

   private Format getDateTimeInstance(final Integer dateStyle, final Integer timeStyle, final TimeZone timeZone, Locale locale) {
      if (locale == null) {
         locale = Locale.getDefault();
      }

      String pattern = getPatternForStyle(dateStyle, timeStyle, locale);
      return this.getInstance(pattern, timeZone, locale);
   }

   Format getDateTimeInstance(final int dateStyle, final int timeStyle, final TimeZone timeZone, final Locale locale) {
      return this.getDateTimeInstance(dateStyle, timeStyle, timeZone, locale);
   }

   Format getDateInstance(final int dateStyle, final TimeZone timeZone, final Locale locale) {
      return this.getDateTimeInstance(dateStyle, (Integer)null, timeZone, locale);
   }

   Format getTimeInstance(final int timeStyle, final TimeZone timeZone, final Locale locale) {
      return this.getDateTimeInstance((Integer)null, timeStyle, timeZone, locale);
   }

   static String getPatternForStyle(final Integer dateStyle, final Integer timeStyle, final Locale locale) {
      MultipartKey key = new MultipartKey(new Object[]{dateStyle, timeStyle, locale});
      String pattern = (String)cDateTimeInstanceCache.get(key);
      if (pattern == null) {
         try {
            DateFormat formatter;
            if (dateStyle == null) {
               formatter = DateFormat.getTimeInstance(timeStyle, locale);
            } else if (timeStyle == null) {
               formatter = DateFormat.getDateInstance(dateStyle, locale);
            } else {
               formatter = DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale);
            }

            pattern = ((SimpleDateFormat)formatter).toPattern();
            String previous = (String)cDateTimeInstanceCache.putIfAbsent(key, pattern);
            if (previous != null) {
               pattern = previous;
            }
         } catch (ClassCastException var7) {
            throw new IllegalArgumentException("No date time pattern for locale: " + locale);
         }
      }

      return pattern;
   }

   private static class MultipartKey {
      private final Object[] keys;
      private int hashCode;

      public MultipartKey(final Object... keys) {
         this.keys = keys;
      }

      public boolean equals(final Object obj) {
         return Arrays.equals(this.keys, ((MultipartKey)obj).keys);
      }

      public int hashCode() {
         if (this.hashCode == 0) {
            int rc = 0;

            for(Object key : this.keys) {
               if (key != null) {
                  rc = rc * 7 + key.hashCode();
               }
            }

            this.hashCode = rc;
         }

         return this.hashCode;
      }
   }
}
