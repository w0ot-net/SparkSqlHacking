package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class ShortArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public ShortArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public short[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected short convertType(Object value) {
      return (Short)this.typeConverterManagerBean.convertType(value, Short.TYPE);
   }

   protected short[] convertToSingleElementArray(Object value) {
      return new short[]{this.convertType(value)};
   }

   protected short[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         short[] target = new short[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         short[] target = new short[collection.size()];
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

         short[] target = new short[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected short[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Short.TYPE) {
         return (short[])value;
      } else {
         short[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected short[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      short[] result = null;
      if (primitiveComponentType == short[].class) {
         return (short[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)array[i];
            }
         } else if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)((int)array[i]);
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)((int)array[i]);
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)((int)array[i]);
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)array[i];
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new short[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (short)(array[i] ? 1 : 0);
            }
         }

         return result;
      }
   }
}
