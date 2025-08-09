package jodd.typeconverter.impl;

import java.util.Collection;
import java.util.List;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

public class CharacterArrayConverter implements TypeConverter {
   protected final TypeConverterManagerBean typeConverterManagerBean;

   public CharacterArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      this.typeConverterManagerBean = typeConverterManagerBean;
   }

   public char[] convert(Object value) {
      if (value == null) {
         return null;
      } else {
         Class valueClass = value.getClass();
         return !valueClass.isArray() ? this.convertValueToArray(value) : this.convertArrayToArray(value);
      }
   }

   protected char convertType(Object value) {
      return (Character)this.typeConverterManagerBean.convertType(value, Character.TYPE);
   }

   protected char[] convertToSingleElementArray(Object value) {
      return new char[]{this.convertType(value)};
   }

   protected char[] convertValueToArray(Object value) {
      if (value instanceof List) {
         List list = (List)value;
         char[] target = new char[list.size()];

         for(int i = 0; i < list.size(); ++i) {
            Object element = list.get(i);
            target[i] = this.convertType(element);
         }

         return target;
      } else if (value instanceof Collection) {
         Collection collection = (Collection)value;
         char[] target = new char[collection.size()];
         int i = 0;

         for(Object element : collection) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      } else if (value instanceof Iterable) {
         Iterable iterable = (Iterable)value;
         int count = 0;

         for(Object element : iterable) {
            ++count;
         }

         char[] target = new char[count];
         int i = 0;

         for(Object element : iterable) {
            target[i] = this.convertType(element);
            ++i;
         }

         return target;
      } else if (!(value instanceof CharSequence)) {
         return this.convertToSingleElementArray(value);
      } else {
         CharSequence charSequence = (CharSequence)value;
         char[] result = new char[charSequence.length()];

         for(int i = 0; i < result.length; ++i) {
            result[i] = charSequence.charAt(i);
         }

         return result;
      }
   }

   protected char[] convertArrayToArray(Object value) {
      Class valueComponentType = value.getClass().getComponentType();
      if (valueComponentType == Character.TYPE) {
         return (char[])value;
      } else {
         char[] result;
         if (valueComponentType.isPrimitive()) {
            result = this.convertPrimitiveArrayToArray(value, valueComponentType);
         } else {
            Object[] array = value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = this.convertType(array[i]);
            }
         }

         return result;
      }
   }

   protected char[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      char[] result = null;
      if (primitiveComponentType == char[].class) {
         return (char[])value;
      } else {
         if (primitiveComponentType == Integer.TYPE) {
            int[] array = (int[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)array[i];
            }
         } else if (primitiveComponentType == Long.TYPE) {
            long[] array = (long[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)((int)array[i]);
            }
         } else if (primitiveComponentType == Float.TYPE) {
            float[] array = (float[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)((int)array[i]);
            }
         } else if (primitiveComponentType == Double.TYPE) {
            double[] array = (double[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)((int)array[i]);
            }
         } else if (primitiveComponentType == Short.TYPE) {
            short[] array = (short[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)array[i];
            }
         } else if (primitiveComponentType == Byte.TYPE) {
            byte[] array = (byte[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)array[i];
            }
         } else if (primitiveComponentType == Boolean.TYPE) {
            boolean[] array = (boolean[])value;
            result = new char[array.length];

            for(int i = 0; i < array.length; ++i) {
               result[i] = (char)(array[i] ? 1 : 0);
            }
         }

         return result;
      }
   }
}
