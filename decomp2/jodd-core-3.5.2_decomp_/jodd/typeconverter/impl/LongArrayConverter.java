package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class LongArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public LongArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public long[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected long convertType(Object value) {
      return (Long)this.typeConverterManagerBean.convertType(value, Long.TYPE);
   }

   protected long[] convertToSingleElementArray(Object value) {
      return new long[]{this.convertType(value)};
   }

   protected long[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         long[] target = new long[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         long[] target = new long[collection.size()];
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

         long[] target = new long[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected long[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Long.TYPE) {
         return (long[])value;
      } else {
         long[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected long[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      long[] result = null;
      if (primitiveComponentType == long[].class) {
         return (long[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (long)array[i];
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (long)array[i];
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (long)array[i];
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (long)array[i];
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (long)array[i];
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (long)array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new long[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] ? 1L : 0L;
            }
         }

         return result;
      }
   }
}
