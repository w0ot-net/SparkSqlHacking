package jodd.typeconverter.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class ArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;
   protected final Class targetComponentType;

   public ArrayConverter(TypeConverterManagerBean typeConverterManagerBean, Class targetComponentType) {
      this.typeConverterManagerBean = typeConverterManagerBean;
      this.targetComponentType = targetComponentType;
   }

   public Object[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected Object convertType(Object value) {
      return this.typeConverterManagerBean.convertType(value, this.targetComponentType);
   }

   protected Object[] createArray(int length) {
      return Array.newInstance(this.targetComponentType, length);
   }

   protected Object[] convertToSingleElementArray(Object value) {
      T[] singleElementArray = (T[])this.createArray(1);
      singleElementArray[0] = this.convertType(value);
      return singleElementArray;
   }

   protected Object[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         T[] target = (T[])this.createArray(list.size());

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         T[] target = (T[])this.createArray(collection.size());
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
         List<T> list = new ArrayList();

         for(Object element : iterable) {
            list.add(this.convertType(element));
         }

         T[] target = (T[])this.createArray(list.size());
         return list.toArray(target);
      }
   }

   protected Object[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == this.targetComponentType) {
         return value;
      } else {
         T[] result;
         if (valueComponentType.isPrimitive()) {
            result = (T[])this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = (T[])this.createArray(array.length);

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected Object[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      T[] result = null;
      if (primitiveComponentType == Integer.TYPE) {
         int[] array = (int[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Long.TYPE) {
         long[] array = (long[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Float.TYPE) {
         float[] array = (float[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Double.TYPE) {
         double[] array = (double[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Short.TYPE) {
         short[] array = (short[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Byte.TYPE) {
         byte[] array = (byte[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Character.TYPE) {
         char[] array = (char[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      } else if (primitiveComponentType == Boolean.TYPE) {
         boolean[] array = (boolean[])value;
         result = (T[])this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = this.convertType(array[i]);
         }
      }

      return result;
   }
}
