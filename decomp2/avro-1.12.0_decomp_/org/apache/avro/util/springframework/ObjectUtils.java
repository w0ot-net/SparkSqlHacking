package org.apache.avro.util.springframework;

import java.util.Arrays;
import org.apache.avro.reflect.Nullable;

class ObjectUtils {
   private static final int INITIAL_HASH = 7;
   private static final int MULTIPLIER = 31;

   private ObjectUtils() {
   }

   public static boolean isEmpty(@Nullable Object[] array) {
      return array == null || array.length == 0;
   }

   public static boolean nullSafeEquals(@Nullable Object o1, @Nullable Object o2) {
      if (o1 == o2) {
         return true;
      } else if (o1 != null && o2 != null) {
         if (o1.equals(o2)) {
            return true;
         } else {
            return o1.getClass().isArray() && o2.getClass().isArray() ? arrayEquals(o1, o2) : false;
         }
      } else {
         return false;
      }
   }

   private static boolean arrayEquals(Object o1, Object o2) {
      if (o1 instanceof Object[] && o2 instanceof Object[]) {
         return Arrays.equals(o1, o2);
      } else if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
         return Arrays.equals((boolean[])o1, (boolean[])o2);
      } else if (o1 instanceof byte[] && o2 instanceof byte[]) {
         return Arrays.equals((byte[])o1, (byte[])o2);
      } else if (o1 instanceof char[] && o2 instanceof char[]) {
         return Arrays.equals((char[])o1, (char[])o2);
      } else if (o1 instanceof double[] && o2 instanceof double[]) {
         return Arrays.equals((double[])o1, (double[])o2);
      } else if (o1 instanceof float[] && o2 instanceof float[]) {
         return Arrays.equals((float[])o1, (float[])o2);
      } else if (o1 instanceof int[] && o2 instanceof int[]) {
         return Arrays.equals((int[])o1, (int[])o2);
      } else if (o1 instanceof long[] && o2 instanceof long[]) {
         return Arrays.equals((long[])o1, (long[])o2);
      } else {
         return o1 instanceof short[] && o2 instanceof short[] ? Arrays.equals((short[])o1, (short[])o2) : false;
      }
   }

   public static int nullSafeHashCode(@Nullable Object obj) {
      if (obj == null) {
         return 0;
      } else {
         if (obj.getClass().isArray()) {
            if (obj instanceof Object[]) {
               return nullSafeHashCode(obj);
            }

            if (obj instanceof boolean[]) {
               return nullSafeHashCode((boolean[])obj);
            }

            if (obj instanceof byte[]) {
               return nullSafeHashCode((byte[])obj);
            }

            if (obj instanceof char[]) {
               return nullSafeHashCode((char[])obj);
            }

            if (obj instanceof double[]) {
               return nullSafeHashCode((double[])obj);
            }

            if (obj instanceof float[]) {
               return nullSafeHashCode((float[])obj);
            }

            if (obj instanceof int[]) {
               return nullSafeHashCode((int[])obj);
            }

            if (obj instanceof long[]) {
               return nullSafeHashCode((long[])obj);
            }

            if (obj instanceof short[]) {
               return nullSafeHashCode((short[])obj);
            }
         }

         return obj.hashCode();
      }
   }

   public static int nullSafeHashCode(@Nullable Object[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(Object element : array) {
            hash = 31 * hash + nullSafeHashCode(element);
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable boolean[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(boolean element : array) {
            hash = 31 * hash + Boolean.hashCode(element);
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable byte[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(byte element : array) {
            hash = 31 * hash + element;
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable char[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(char element : array) {
            hash = 31 * hash + element;
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable double[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(double element : array) {
            hash = 31 * hash + Double.hashCode(element);
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable float[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(float element : array) {
            hash = 31 * hash + Float.hashCode(element);
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable int[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(int element : array) {
            hash = 31 * hash + element;
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable long[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(long element : array) {
            hash = 31 * hash + Long.hashCode(element);
         }

         return hash;
      }
   }

   public static int nullSafeHashCode(@Nullable short[] array) {
      if (array == null) {
         return 0;
      } else {
         int hash = 7;

         for(short element : array) {
            hash = 31 * hash + element;
         }

         return hash;
      }
   }
}
