package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.DateFormats;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class JwtDateConverter implements Converter {
   public static final JwtDateConverter INSTANCE = new JwtDateConverter();

   public Object applyTo(Date date) {
      return date == null ? null : date.getTime() / 1000L;
   }

   public Date applyFrom(Object o) {
      return toSpecDate(o);
   }

   public static Date toSpecDate(Object value) {
      if (value == null) {
         return null;
      } else {
         if (value instanceof String) {
            try {
               value = Long.parseLong((String)value);
            } catch (NumberFormatException var3) {
            }
         }

         if (value instanceof Number) {
            long seconds = ((Number)value).longValue();
            value = seconds * 1000L;
         }

         return toDate(value);
      }
   }

   public static Date toDate(Object v) {
      if (v == null) {
         return null;
      } else if (v instanceof Date) {
         return (Date)v;
      } else if (v instanceof Calendar) {
         return ((Calendar)v).getTime();
      } else if (v instanceof Number) {
         long millis = ((Number)v).longValue();
         return new Date(millis);
      } else if (v instanceof String) {
         return parseIso8601Date((String)v);
      } else {
         String msg = "Cannot create Date from object of type " + v.getClass().getName() + ".";
         throw new IllegalArgumentException(msg);
      }
   }

   private static Date parseIso8601Date(String value) throws IllegalArgumentException {
      try {
         return DateFormats.parseIso8601Date(value);
      } catch (ParseException e) {
         String msg = "String value is not a JWT NumericDate, nor is it ISO-8601-formatted. All heuristics exhausted. Cause: " + e.getMessage();
         throw new IllegalArgumentException(msg, e);
      }
   }
}
