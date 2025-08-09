package jodd.typeconverter.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import jodd.datetime.JDateTime;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.StringUtil;

public class SqlTimestampConverter implements TypeConverter {
   public Timestamp convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof Timestamp) {
         return (Timestamp)value;
      } else if (value instanceof Calendar) {
         Calendar calendar = (Calendar)value;
         return new Timestamp(calendar.getTimeInMillis());
      } else if (value instanceof Date) {
         Date date = (Date)value;
         return new Timestamp(date.getTime());
      } else if (value instanceof JDateTime) {
         return ((JDateTime)value).convertToSqlTimestamp();
      } else if (value instanceof Number) {
         return new Timestamp(((Number)value).longValue());
      } else {
         String stringValue = value.toString().trim();
         if (!StringUtil.containsOnlyDigits(stringValue)) {
            try {
               return Timestamp.valueOf(stringValue);
            } catch (IllegalArgumentException iaex) {
               throw new TypeConversionException(value, iaex);
            }
         } else {
            try {
               long milliseconds = Long.parseLong(stringValue);
               return new Timestamp(milliseconds);
            } catch (NumberFormatException nfex) {
               throw new TypeConversionException(value, nfex);
            }
         }
      }
   }
}
