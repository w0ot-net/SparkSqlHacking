package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.ClassLoaderUtil;

public class ClassConverter implements TypeConverter {
   public Class convert(Object value) {
      if (value == null) {
         return null;
      } else if (value.getClass() == Class.class) {
         return (Class)value;
      } else {
         try {
            return ClassLoaderUtil.loadClass(value.toString().trim());
         } catch (ClassNotFoundException cnfex) {
            throw new TypeConversionException(value, cnfex);
         }
      }
   }
}
