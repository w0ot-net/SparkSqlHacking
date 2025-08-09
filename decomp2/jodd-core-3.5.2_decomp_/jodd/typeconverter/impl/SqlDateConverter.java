package jodd.typeconverter.impl;

import java.sql.Date;
import java.util.Calendar;
import jodd.datetime.JDateTime;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class SqlDateConverter implements TypeConverter {
   public Date convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof Date) {
         return (Date)value;
      } else if (value instanceof Calendar) {
         return new Date(((Calendar)value).getTimeInMillis());
      } else if (value instanceof java.util.Date) {
         return new Date(((java.util.Date)value).getTime());
      } else if (value instanceof JDateTime) {
         return ((JDateTime)value).convertToSqlDate();
      } else if (value instanceof Number) {
         return new Date(((Number)value).longValue());
      } else {
         String stringValue = value.toString().trim();
         if (!StringUtil.containsOnlyDigits(stringValue)) {
            try {
               return Date.valueOf(stringValue);
            } catch (IllegalArgumentException iaex) {
               throw new TypeConversionException(value, iaex);
            }
         } else {
            try {
               long milliseconds = Long.parseLong(stringValue);
               return new Date(milliseconds);
            } catch (NumberFormatException nfex) {
               throw new TypeConversionException(value, nfex);
            }
         }
      }
   }
}
