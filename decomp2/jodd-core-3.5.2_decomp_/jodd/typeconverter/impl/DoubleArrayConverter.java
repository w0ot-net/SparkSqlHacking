package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class DoubleArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public DoubleArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public double[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected double convertType(Object value) {
      return (Double)this.typeConverterManagerBean.convertType(value, Double.TYPE);
   }

   protected double[] convertToSingleElementArray(Object value) {
      return new double[]{this.convertType(value)};
   }

   protected double[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         double[] target = new double[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         double[] target = new double[collection.size()];
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

         double[] target = new double[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected double[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Double.TYPE) {
         return (double[])value;
      } else {
         double[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected double[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      double[] result = null;
      if (primitiveComponentType == double[].class) {
         return (double[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (double)array[i];
            }
         } else if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (double)array[i];
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (double)array[i];
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (double)array[i];
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (double)array[i];
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (double)array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new double[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] ? (double)1.0F : (double)0.0F;
            }
         }

         return result;
      }
   }
}
