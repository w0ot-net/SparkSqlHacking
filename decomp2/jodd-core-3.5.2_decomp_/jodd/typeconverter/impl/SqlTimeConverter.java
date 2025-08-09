package jodd.typeconverter.impl;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import jodd.datetime.JDateTime;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class SqlTimeConverter implements TypeConverter {
   public Time convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof Time) {
         return (Time)value;
      } else if (value instanceof Calendar) {
         return new Time(((Calendar)value).getTimeInMillis());
      } else if (value instanceof Date) {
         return new Time(((Date)value).getTime());
      } else if (value instanceof JDateTime) {
         return ((JDateTime)value).convertToSqlTime();
      } else if (value instanceof Number) {
         return new Time(((Number)value).longValue());
      } else {
         String stringValue = value.toString().trim();
         if (!StringUtil.containsOnlyDigits(stringValue)) {
            try {
               return Time.valueOf(stringValue);
            } catch (IllegalArgumentException iaex) {
               throw new TypeConversionException(value, iaex);
            }
         } else {
            try {
               long milliseconds = Long.parseLong(stringValue);
               return new Time(milliseconds);
            } catch (NumberFormatException nfex) {
               throw new TypeConversionException(value, nfex);
            }
         }
      }
   }
}
