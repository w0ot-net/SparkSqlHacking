package jodd.typeconverter.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import jodd.io.FileUtil;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class ByteArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public ByteArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public byte[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected byte convertType(Object value) {
      return (Byte)this.typeConverterManagerBean.convertType(value, Byte.TYPE);
   }

   protected byte[] convertToSingleElementArray(Object value) {
      return new byte[]{this.convertType(value)};
   }

   protected byte[] convertValueToArray(Object value) {
      if (value instanceof Blob) {
         Blob blob = (Blob)value;

         try {
            long length = blob.length();
            if (length > 2147483647L) {
               throw new TypeConversionException("Blob is too big.");
            } else {
               return blob.getBytes(1L, (int)length);
            }
         } catch (SQLException sex) {
            throw new TypeConversionException(value, sex);
         }
      } else if (value instanceof File) {
         try {
            return FileUtil.readBytes((File)value);
         } catch (IOException ioex) {
            throw new TypeConversionException(value, ioex);
         }
      } else if (value instanceof List) {
         List list = (List)value;
         byte[] target = new byte[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         byte[] target = new byte[collection.size()];
         int i = 0;

         for(Object element : collection) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      } else if (!(value instanceof Iterable)) {
         if (value instanceof CharSequence) {
            String[] strings = CsvUtil.toStringArray(value.toString());
            return this.convertArrayToArray(strings);
         } else {
            return this.convertToSingleElementArray(value);
         }
      } else {
         Iterable iterable = (Iterable)value;
         int count = 0;

         for(Object element : iterable) {
            ++count;
         }

         byte[] target = new byte[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected byte[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Byte.TYPE) {
         return (byte[])value;
      } else {
         byte[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected byte[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      byte[] result = null;
      if (primitiveComponentType == byte[].class) {
         return (byte[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)array[i];
            }
         } else if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)((int)array[i]);
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)((int)array[i]);
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)((int)array[i]);
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)array[i];
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new byte[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (byte)(array[i] ? 1 : 0);
            }
         }

         return result;
      }
   }
}
