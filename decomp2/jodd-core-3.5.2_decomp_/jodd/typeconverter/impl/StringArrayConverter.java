package jodd.typeconverter.impl;

import jodd.typeconverter.TypeConverterManagerBean;

public class StringArrayConverter extends ArrayConverter {
   public StringArrayConverter(TypeConverterManagerBean typeConverterManagerBean) {
      super(typeConverterManagerBean, String.class);
   }

   protected String[] createArray(int length) {
      return new String[length];
   }

   protected String[] convertPrimitiveArrayToArray(Object value, Class primitiveComponentType) {
      String[] result = null;
      if (primitiveComponentType == Integer.TYPE) {
         int[] array = (int[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Long.TYPE) {
         long[] array = (long[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Float.TYPE) {
         float[] array = (float[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Double.TYPE) {
         double[] array = (double[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Short.TYPE) {
         short[] array = (short[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Byte.TYPE) {
         byte[] array = (byte[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Character.TYPE) {
         char[] array = (char[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      } else if (primitiveComponentType == Boolean.TYPE) {
         boolean[] array = (boolean[])value;
         result = this.createArray(array.length);

         for(int i = 0; i < array.length; ++i) {
            result[i] = String.valueOf(array[i]);
         }
      }

      return result;
   }
}
