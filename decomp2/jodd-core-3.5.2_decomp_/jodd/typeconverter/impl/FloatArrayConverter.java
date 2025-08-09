package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;
import jodd.util.CsvUtil;

public class FloatArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public FloatArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public float[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected float convertType(Object value) {
      return (Float)this.typeConverterManagerBean.convertType(value, Float.TYPE);
   }

   protected float[] convertToSingleElementArray(Object value) {
      return new float[]{this.convertType(value)};
   }

   protected float[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         float[] target = new float[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         float[] target = new float[collection.size()];
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

         float[] target = new float[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      }
   }

   protected float[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Float.TYPE) {
         return (float[])value;
      } else {
         float[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected float[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      float[] result = null;
      if (primitiveComponentType == float[].class) {
         return (float[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (float)array[i];
            }
         } else if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (float)array[i];
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (float)array[i];
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (float)array[i];
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (float)array[i];
            }
         } else if (primitiveComponentType == Character.TYPE) {
            char[] array = (char[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (float)array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new float[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = array[i] ? 1.0F : 0.0F;
            }
         }

         return result;
      }
   }
}
