package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class BooleanArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public BooleanArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public boolean[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected boolean convertType(Object value) {
      return (Boolean)this.typeConverterManagerBean.convertType(value, Boolean.TYPE);
   }

   protected boolean[] convertToSingleElementArray(Object value) {
      return new boolean[]{this.convertType(value)};
   }

   protected boolean[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         boolean[] target = new boolean[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         boolean[] target = new boolean[collection.size()];
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

         boolean[] target = new boolean[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected boolean[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Boolean.TYPE) {
         return (boolean[])value;
      } else {
         boolean[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected boolean[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      boolean[] result = null;
      if (primitiveComponentType == boolean[].class) {
         return (boolean[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != 0;
            }
         } else if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != 0L;
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != 0.0F;
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != (double)0.0F;
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != 0;
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != 0;
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new boolean[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] != 0;
            }
         }

         return result;
      }
   }
}
