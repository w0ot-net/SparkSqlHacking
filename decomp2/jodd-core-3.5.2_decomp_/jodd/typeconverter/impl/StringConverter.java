package jodd.typeconverter.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Clob;
import java.sql.SQLException;
import jodd.JoddCore;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.CsvUtil;

public class StringConverter implements TypeConverter {
   public String convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof CharSequence) {
         return value.toString();
      } else {
         Class type = value.getClass();
         if (type == Class.class) {
            return ((Class)value).getName();
         } else if (type.isArray()) {
            if (type == byte[].class) {
               byte[] valueArray = (byte[])value;

               try {
                  return new String(valueArray, 0, valueArray.length, JoddCore.encoding);
               } catch (UnsupportedEncodingException ueex) {
                  throw new TypeConversionException(ueex);
               }
            } else if (type == char[].class) {
               char[] charArray = (char[])value;
               return new String(charArray);
            } else {
               return CsvUtil.toCsvString(value);
            }
         } else if (value instanceof Clob) {
            Clob clob = (Clob)value;

            try {
               long length = clob.length();
               if (length > 2147483647L) {
                  throw new TypeConversionException("Clob is too big.");
               } else {
                  return clob.getSubString(1L, (int)length);
               }
            } catch (SQLException sex) {
               throw new TypeConversionException(value, sex);
            }
         } else {
            return value.toString();
         }
      }
   }
}
