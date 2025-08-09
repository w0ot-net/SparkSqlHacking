package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class IntegerArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public IntegerArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public int[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected int convertType(Object value) {
      return (Integer)this.typeConverterManagerBean.convertType(value, Integer.TYPE);
   }

   protected int[] convertToSingleElementArray(Object value) {
      return new int[]{this.convertType(value)};
   }

   protected int[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         int[] target = new int[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         int[] target = new int[collection.size()];
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

         for(Iterator iterator = iterable.iterator(); iterator.hasNext(); ++count) {
            iterator.next();
         }

         int[] target = new int[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected int[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Integer.TYPE) {
         return (int[])value;
      } else {
         int[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected int[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      int[] result = null;
      if (primitiveComponentType == int[].class) {
         return (int[])value;
      } else {
         if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (int)array[i];
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (int)array[i];
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (int)array[i];
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i];
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i];
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new int[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] ? 1 : 0;
            }
         }

         return result;
      }
   }
}
