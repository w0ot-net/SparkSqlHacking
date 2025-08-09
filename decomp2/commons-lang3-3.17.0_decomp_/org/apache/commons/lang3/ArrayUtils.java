package org.apache.commons.lang3;

import [Ljava.lang.Object;;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.mutable.MutableInt;

public class ArrayUtils {
   public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
   public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
   public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
   public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
   public static final char[] EMPTY_CHAR_ARRAY = new char[0];
   public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
   public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
   public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
   public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
   public static final Field[] EMPTY_FIELD_ARRAY = new Field[0];
   public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
   public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
   public static final int[] EMPTY_INT_ARRAY = new int[0];
   public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
   public static final long[] EMPTY_LONG_ARRAY = new long[0];
   public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
   public static final Method[] EMPTY_METHOD_ARRAY = new Method[0];
   public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
   public static final short[] EMPTY_SHORT_ARRAY = new short[0];
   public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
   public static final String[] EMPTY_STRING_ARRAY = new String[0];
   public static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];
   public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];
   public static final int INDEX_NOT_FOUND = -1;

   public static boolean[] add(boolean[] array, boolean element) {
      boolean[] newArray = (boolean[])copyArrayGrow1(array, Boolean.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static boolean[] add(boolean[] array, int index, boolean element) {
      return (boolean[])add(array, index, element, Boolean.TYPE);
   }

   public static byte[] add(byte[] array, byte element) {
      byte[] newArray = (byte[])copyArrayGrow1(array, Byte.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static byte[] add(byte[] array, int index, byte element) {
      return (byte[])add(array, index, element, Byte.TYPE);
   }

   public static char[] add(char[] array, char element) {
      char[] newArray = (char[])copyArrayGrow1(array, Character.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static char[] add(char[] array, int index, char element) {
      return (char[])add(array, index, element, Character.TYPE);
   }

   public static double[] add(double[] array, double element) {
      double[] newArray = (double[])copyArrayGrow1(array, Double.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static double[] add(double[] array, int index, double element) {
      return (double[])add(array, index, element, Double.TYPE);
   }

   public static float[] add(float[] array, float element) {
      float[] newArray = (float[])copyArrayGrow1(array, Float.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static float[] add(float[] array, int index, float element) {
      return (float[])add(array, index, element, Float.TYPE);
   }

   public static int[] add(int[] array, int element) {
      int[] newArray = (int[])copyArrayGrow1(array, Integer.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static int[] add(int[] array, int index, int element) {
      return (int[])add(array, index, element, Integer.TYPE);
   }

   /** @deprecated */
   @Deprecated
   public static long[] add(long[] array, int index, long element) {
      return (long[])add(array, index, element, Long.TYPE);
   }

   public static long[] add(long[] array, long element) {
      long[] newArray = (long[])copyArrayGrow1(array, Long.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   private static Object add(Object array, int index, Object element, Class clazz) {
      if (array == null) {
         if (index != 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
         } else {
            Object joinedArray = Array.newInstance(clazz, 1);
            Array.set(joinedArray, 0, element);
            return joinedArray;
         }
      } else {
         int length = Array.getLength(array);
         if (index <= length && index >= 0) {
            Object result = arraycopy(array, 0, 0, index, (Supplier)(() -> Array.newInstance(clazz, length + 1)));
            Array.set(result, index, element);
            if (index < length) {
               System.arraycopy(array, index, result, index + 1, length - index);
            }

            return result;
         } else {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static short[] add(short[] array, int index, short element) {
      return (short[])add(array, index, element, Short.TYPE);
   }

   public static short[] add(short[] array, short element) {
      short[] newArray = (short[])copyArrayGrow1(array, Short.TYPE);
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   /** @deprecated */
   @Deprecated
   public static Object[] add(Object[] array, int index, Object element) {
      Class<T> clazz;
      if (array != null) {
         clazz = getComponentType(array);
      } else {
         if (element == null) {
            throw new IllegalArgumentException("Array and element cannot both be null");
         }

         clazz = ObjectUtils.getClass(element);
      }

      return add(array, index, element, clazz);
   }

   public static Object[] add(Object[] array, Object element) {
      Class<?> type;
      if (array != null) {
         type = array.getClass().getComponentType();
      } else {
         if (element == null) {
            throw new IllegalArgumentException("Arguments cannot both be null");
         }

         type = element.getClass();
      }

      T[] newArray = (T[])((Object[])copyArrayGrow1(array, type));
      newArray[newArray.length - 1] = element;
      return newArray;
   }

   public static boolean[] addAll(boolean[] array1, boolean... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         boolean[] joinedArray = new boolean[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static byte[] addAll(byte[] array1, byte... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         byte[] joinedArray = new byte[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static char[] addAll(char[] array1, char... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         char[] joinedArray = new char[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static double[] addAll(double[] array1, double... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         double[] joinedArray = new double[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static float[] addAll(float[] array1, float... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         float[] joinedArray = new float[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static int[] addAll(int[] array1, int... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         int[] joinedArray = new int[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static long[] addAll(long[] array1, long... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         long[] joinedArray = new long[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static short[] addAll(short[] array1, short... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         short[] joinedArray = new short[array1.length + array2.length];
         System.arraycopy(array1, 0, joinedArray, 0, array1.length);
         System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
         return joinedArray;
      }
   }

   public static Object[] addAll(Object[] array1, Object... array2) {
      if (array1 == null) {
         return clone(array2);
      } else if (array2 == null) {
         return clone(array1);
      } else {
         Class<T> type1 = getComponentType(array1);
         T[] joinedArray = (T[])((Object[])arraycopy(array1, 0, 0, array1.length, (Supplier)(() -> newInstance(type1, array1.length + array2.length))));

         try {
            System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
            return joinedArray;
         } catch (ArrayStoreException ase) {
            Class<?> type2 = array2.getClass().getComponentType();
            if (!type1.isAssignableFrom(type2)) {
               throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), ase);
            } else {
               throw ase;
            }
         }
      }
   }

   public static boolean[] addFirst(boolean[] array, boolean element) {
      return array == null ? add(array, element) : insert(0, (boolean[])array, (boolean[])(element));
   }

   public static byte[] addFirst(byte[] array, byte element) {
      return array == null ? add(array, element) : insert(0, (byte[])array, (byte[])(element));
   }

   public static char[] addFirst(char[] array, char element) {
      return array == null ? add(array, element) : insert(0, (char[])array, (char[])(element));
   }

   public static double[] addFirst(double[] array, double element) {
      return array == null ? add(array, element) : insert(0, (double[])array, (double[])(element));
   }

   public static float[] addFirst(float[] array, float element) {
      return array == null ? add(array, element) : insert(0, (float[])array, (float[])(element));
   }

   public static int[] addFirst(int[] array, int element) {
      return array == null ? add(array, element) : insert(0, (int[])array, (int[])(element));
   }

   public static long[] addFirst(long[] array, long element) {
      return array == null ? add(array, element) : insert(0, (long[])array, (long[])(element));
   }

   public static short[] addFirst(short[] array, short element) {
      return array == null ? add(array, element) : insert(0, (short[])array, (short[])(element));
   }

   public static Object[] addFirst(Object[] array, Object element) {
      return array == null ? add(array, element) : insert(0, (Object[])array, (Object[])(element));
   }

   public static Object arraycopy(Object source, int sourcePos, int destPos, int length, Function allocator) {
      return arraycopy(source, sourcePos, allocator.apply(length), destPos, length);
   }

   public static Object arraycopy(Object source, int sourcePos, int destPos, int length, Supplier allocator) {
      return arraycopy(source, sourcePos, allocator.get(), destPos, length);
   }

   public static Object arraycopy(Object source, int sourcePos, Object dest, int destPos, int length) {
      System.arraycopy(source, sourcePos, dest, destPos, length);
      return dest;
   }

   public static boolean[] clone(boolean[] array) {
      return array != null ? (boolean[])(([Z)array).clone() : null;
   }

   public static byte[] clone(byte[] array) {
      return array != null ? (byte[])(([B)array).clone() : null;
   }

   public static char[] clone(char[] array) {
      return array != null ? (char[])(([C)array).clone() : null;
   }

   public static double[] clone(double[] array) {
      return array != null ? (double[])(([D)array).clone() : null;
   }

   public static float[] clone(float[] array) {
      return array != null ? (float[])(([F)array).clone() : null;
   }

   public static int[] clone(int[] array) {
      return array != null ? (int[])(([I)array).clone() : null;
   }

   public static long[] clone(long[] array) {
      return array != null ? (long[])(([J)array).clone() : null;
   }

   public static short[] clone(short[] array) {
      return array != null ? (short[])(([S)array).clone() : null;
   }

   public static Object[] clone(Object[] array) {
      return array != null ? (Object[])((Object;)array).clone() : null;
   }

   public static boolean contains(boolean[] array, boolean valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(byte[] array, byte valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(char[] array, char valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(double[] array, double valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(double[] array, double valueToFind, double tolerance) {
      return indexOf(array, valueToFind, 0, tolerance) != -1;
   }

   public static boolean contains(float[] array, float valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(int[] array, int valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(long[] array, long valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean contains(Object[] array, Object objectToFind) {
      return indexOf(array, objectToFind) != -1;
   }

   public static boolean contains(short[] array, short valueToFind) {
      return indexOf(array, valueToFind) != -1;
   }

   public static boolean containsAny(Object[] array, Object... objectsToFind) {
      return org.apache.commons.lang3.stream.Streams.of(objectsToFind).anyMatch((e) -> contains(array, e));
   }

   private static Object copyArrayGrow1(Object array, Class newArrayComponentType) {
      if (array != null) {
         int arrayLength = Array.getLength(array);
         Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
         System.arraycopy(array, 0, newArray, 0, arrayLength);
         return newArray;
      } else {
         return Array.newInstance(newArrayComponentType, 1);
      }
   }

   public static Object get(Object[] array, int index) {
      return get(array, index, (Object)null);
   }

   public static Object get(Object[] array, int index, Object defaultValue) {
      return isArrayIndexValid(array, index) ? array[index] : defaultValue;
   }

   public static Class getComponentType(Object[] array) {
      return ClassUtils.getComponentType(ObjectUtils.getClass(array));
   }

   public static int getLength(Object array) {
      return array != null ? Array.getLength(array) : 0;
   }

   public static int hashCode(Object array) {
      return (new HashCodeBuilder()).append(array).toHashCode();
   }

   public static BitSet indexesOf(boolean[] array, boolean valueToFind) {
      return indexesOf(array, valueToFind, 0);
   }

   public static BitSet indexesOf(boolean[] param0, boolean param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(byte[] array, byte valueToFind) {
      return indexesOf((byte[])array, (byte)valueToFind, 0);
   }

   public static BitSet indexesOf(byte[] param0, byte param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(char[] array, char valueToFind) {
      return indexesOf((char[])array, (char)valueToFind, 0);
   }

   public static BitSet indexesOf(char[] param0, char param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(double[] array, double valueToFind) {
      return indexesOf(array, valueToFind, 0);
   }

   public static BitSet indexesOf(double[] array, double valueToFind, double tolerance) {
      return indexesOf(array, valueToFind, 0, tolerance);
   }

   public static BitSet indexesOf(double[] param0, double param1, int param3) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(double[] param0, double param1, int param3, double param4) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(float[] array, float valueToFind) {
      return indexesOf(array, valueToFind, 0);
   }

   public static BitSet indexesOf(float[] param0, float param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(int[] array, int valueToFind) {
      return indexesOf((int[])array, (int)valueToFind, 0);
   }

   public static BitSet indexesOf(int[] param0, int param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(long[] array, long valueToFind) {
      return indexesOf(array, valueToFind, 0);
   }

   public static BitSet indexesOf(long[] param0, long param1, int param3) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(Object[] array, Object objectToFind) {
      return indexesOf(array, objectToFind, 0);
   }

   public static BitSet indexesOf(Object[] param0, Object param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static BitSet indexesOf(short[] array, short valueToFind) {
      return indexesOf((short[])array, (short)valueToFind, 0);
   }

   public static BitSet indexesOf(short[] param0, short param1, int param2) {
      // $FF: Couldn't be decompiled
   }

   public static int indexOf(boolean[] array, boolean valueToFind) {
      return indexOf(array, valueToFind, 0);
   }

   public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
      if (isEmpty(array)) {
         return -1;
      } else {
         for(int i = max0(startIndex); i < array.length; ++i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(byte[] array, byte valueToFind) {
      return indexOf((byte[])array, (byte)valueToFind, 0);
   }

   public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
      if (array == null) {
         return -1;
      } else {
         for(int i = max0(startIndex); i < array.length; ++i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(char[] array, char valueToFind) {
      return indexOf((char[])array, (char)valueToFind, 0);
   }

   public static int indexOf(char[] array, char valueToFind, int startIndex) {
      if (array == null) {
         return -1;
      } else {
         for(int i = max0(startIndex); i < array.length; ++i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(double[] array, double valueToFind) {
      return indexOf(array, valueToFind, 0);
   }

   public static int indexOf(double[] array, double valueToFind, double tolerance) {
      return indexOf(array, valueToFind, 0, tolerance);
   }

   public static int indexOf(double[] array, double valueToFind, int startIndex) {
      if (isEmpty(array)) {
         return -1;
      } else {
         boolean searchNaN = Double.isNaN(valueToFind);

         for(int i = max0(startIndex); i < array.length; ++i) {
            double element = array[i];
            if (valueToFind == element || searchNaN && Double.isNaN(element)) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
      if (isEmpty(array)) {
         return -1;
      } else {
         double min = valueToFind - tolerance;
         double max = valueToFind + tolerance;

         for(int i = max0(startIndex); i < array.length; ++i) {
            if (array[i] >= min && array[i] <= max) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(float[] array, float valueToFind) {
      return indexOf(array, valueToFind, 0);
   }

   public static int indexOf(float[] array, float valueToFind, int startIndex) {
      if (isEmpty(array)) {
         return -1;
      } else {
         boolean searchNaN = Float.isNaN(valueToFind);

         for(int i = max0(startIndex); i < array.length; ++i) {
            float element = array[i];
            if (valueToFind == element || searchNaN && Float.isNaN(element)) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(int[] array, int valueToFind) {
      return indexOf((int[])array, (int)valueToFind, 0);
   }

   public static int indexOf(int[] array, int valueToFind, int startIndex) {
      if (array == null) {
         return -1;
      } else {
         for(int i = max0(startIndex); i < array.length; ++i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(long[] array, long valueToFind) {
      return indexOf(array, valueToFind, 0);
   }

   public static int indexOf(long[] array, long valueToFind, int startIndex) {
      if (array == null) {
         return -1;
      } else {
         for(int i = max0(startIndex); i < array.length; ++i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      }
   }

   public static int indexOf(Object[] array, Object objectToFind) {
      return indexOf(array, objectToFind, 0);
   }

   public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
      if (array == null) {
         return -1;
      } else {
         startIndex = max0(startIndex);
         if (objectToFind == null) {
            for(int i = startIndex; i < array.length; ++i) {
               if (array[i] == null) {
                  return i;
               }
            }
         } else {
            for(int i = startIndex; i < array.length; ++i) {
               if (objectToFind.equals(array[i])) {
                  return i;
               }
            }
         }

         return -1;
      }
   }

   public static int indexOf(short[] array, short valueToFind) {
      return indexOf((short[])array, (short)valueToFind, 0);
   }

   public static int indexOf(short[] array, short valueToFind, int startIndex) {
      if (array == null) {
         return -1;
      } else {
         for(int i = max0(startIndex); i < array.length; ++i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      }
   }

   public static boolean[] insert(int index, boolean[] array, boolean... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         boolean[] result = new boolean[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static byte[] insert(int index, byte[] array, byte... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         byte[] result = new byte[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static char[] insert(int index, char[] array, char... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         char[] result = new char[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static double[] insert(int index, double[] array, double... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         double[] result = new double[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static float[] insert(int index, float[] array, float... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         float[] result = new float[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static int[] insert(int index, int[] array, int... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         int[] result = new int[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static long[] insert(int index, long[] array, long... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         long[] result = new long[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   public static short[] insert(int index, short[] array, short... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         short[] result = new short[array.length + values.length];
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   @SafeVarargs
   public static Object[] insert(int index, Object[] array, Object... values) {
      if (array == null) {
         return null;
      } else if (isEmpty(values)) {
         return clone(array);
      } else if (index >= 0 && index <= array.length) {
         Class<T> type = getComponentType(array);
         int length = array.length + values.length;
         T[] result = (T[])newInstance(type, length);
         System.arraycopy(values, 0, result, index, values.length);
         if (index > 0) {
            System.arraycopy(array, 0, result, 0, index);
         }

         if (index < array.length) {
            System.arraycopy(array, index, result, index + values.length, array.length - index);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + array.length);
      }
   }

   private static boolean isArrayEmpty(Object array) {
      return getLength(array) == 0;
   }

   public static boolean isArrayIndexValid(Object[] array, int index) {
      return index >= 0 && getLength(array) > index;
   }

   public static boolean isEmpty(boolean[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(byte[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(char[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(double[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(float[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(int[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(long[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(Object[] array) {
      return isArrayEmpty(array);
   }

   public static boolean isEmpty(short[] array) {
      return isArrayEmpty(array);
   }

   /** @deprecated */
   @Deprecated
   public static boolean isEquals(Object array1, Object array2) {
      return (new EqualsBuilder()).append(array1, array2).isEquals();
   }

   public static boolean isNotEmpty(boolean[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(byte[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(char[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(double[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(float[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(int[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(long[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(short[] array) {
      return !isEmpty(array);
   }

   public static boolean isNotEmpty(Object[] array) {
      return !isEmpty(array);
   }

   public static boolean isSameLength(boolean[] array1, boolean[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(byte[] array1, byte[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(char[] array1, char[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(double[] array1, double[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(float[] array1, float[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(int[] array1, int[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(long[] array1, long[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(Object array1, Object array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(Object[] array1, Object[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameLength(short[] array1, short[] array2) {
      return getLength(array1) == getLength(array2);
   }

   public static boolean isSameType(Object array1, Object array2) {
      if (array1 != null && array2 != null) {
         return array1.getClass().getName().equals(array2.getClass().getName());
      } else {
         throw new IllegalArgumentException("The Array must not be null");
      }
   }

   public static boolean isSorted(boolean[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         boolean previous = array[0];

         for(boolean current : array) {
            if (BooleanUtils.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(byte[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         byte previous = array[0];

         for(byte current : array) {
            if (NumberUtils.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(char[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         char previous = array[0];

         for(char current : array) {
            if (CharUtils.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(double[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         double previous = array[0];

         for(double current : array) {
            if (Double.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(float[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         float previous = array[0];

         for(float current : array) {
            if (Float.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(int[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         int previous = array[0];

         for(int current : array) {
            if (NumberUtils.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(long[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         long previous = array[0];

         for(long current : array) {
            if (NumberUtils.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(short[] array) {
      if (getLength(array) < 2) {
         return true;
      } else {
         short previous = array[0];

         for(short current : array) {
            if (NumberUtils.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static boolean isSorted(Comparable[] array) {
      return isSorted(array, Comparable::compareTo);
   }

   public static boolean isSorted(Object[] array, Comparator comparator) {
      Objects.requireNonNull(comparator, "comparator");
      if (getLength(array) < 2) {
         return true;
      } else {
         T previous = (T)array[0];

         for(Object current : array) {
            if (comparator.compare(previous, current) > 0) {
               return false;
            }

            previous = current;
         }

         return true;
      }
   }

   public static int lastIndexOf(boolean[] array, boolean valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
      if (!isEmpty(array) && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(byte[] array, byte valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
      if (array != null && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(char[] array, char valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
      if (array != null && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(double[] array, double valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE, tolerance);
   }

   public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
      if (!isEmpty(array) && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
      if (!isEmpty(array) && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         double min = valueToFind - tolerance;
         double max = valueToFind + tolerance;

         for(int i = startIndex; i >= 0; --i) {
            if (array[i] >= min && array[i] <= max) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(float[] array, float valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
      if (!isEmpty(array) && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(int[] array, int valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
      if (array != null && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(long[] array, long valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
      if (array != null && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(Object[] array, Object objectToFind) {
      return lastIndexOf(array, objectToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
      if (array != null && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         if (objectToFind == null) {
            for(int i = startIndex; i >= 0; --i) {
               if (array[i] == null) {
                  return i;
               }
            }
         } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
            for(int i = startIndex; i >= 0; --i) {
               if (objectToFind.equals(array[i])) {
                  return i;
               }
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static int lastIndexOf(short[] array, short valueToFind) {
      return lastIndexOf(array, valueToFind, Integer.MAX_VALUE);
   }

   public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
      if (array != null && startIndex >= 0) {
         if (startIndex >= array.length) {
            startIndex = array.length - 1;
         }

         for(int i = startIndex; i >= 0; --i) {
            if (valueToFind == array[i]) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   private static int max0(int other) {
      return Math.max(0, other);
   }

   public static Object[] newInstance(Class componentType, int length) {
      return Array.newInstance(componentType, length);
   }

   public static Object[] nullTo(Object[] array, Object[] defaultArray) {
      return isEmpty(array) ? defaultArray : array;
   }

   public static boolean[] nullToEmpty(boolean[] array) {
      return isEmpty(array) ? EMPTY_BOOLEAN_ARRAY : array;
   }

   public static Boolean[] nullToEmpty(Boolean[] array) {
      return (Boolean[])nullTo(array, EMPTY_BOOLEAN_OBJECT_ARRAY);
   }

   public static byte[] nullToEmpty(byte[] array) {
      return isEmpty(array) ? EMPTY_BYTE_ARRAY : array;
   }

   public static Byte[] nullToEmpty(Byte[] array) {
      return (Byte[])nullTo(array, EMPTY_BYTE_OBJECT_ARRAY);
   }

   public static char[] nullToEmpty(char[] array) {
      return isEmpty(array) ? EMPTY_CHAR_ARRAY : array;
   }

   public static Character[] nullToEmpty(Character[] array) {
      return (Character[])nullTo(array, EMPTY_CHARACTER_OBJECT_ARRAY);
   }

   public static Class[] nullToEmpty(Class[] array) {
      return (Class[])nullTo(array, EMPTY_CLASS_ARRAY);
   }

   public static double[] nullToEmpty(double[] array) {
      return isEmpty(array) ? EMPTY_DOUBLE_ARRAY : array;
   }

   public static Double[] nullToEmpty(Double[] array) {
      return (Double[])nullTo(array, EMPTY_DOUBLE_OBJECT_ARRAY);
   }

   public static float[] nullToEmpty(float[] array) {
      return isEmpty(array) ? EMPTY_FLOAT_ARRAY : array;
   }

   public static Float[] nullToEmpty(Float[] array) {
      return (Float[])nullTo(array, EMPTY_FLOAT_OBJECT_ARRAY);
   }

   public static int[] nullToEmpty(int[] array) {
      return isEmpty(array) ? EMPTY_INT_ARRAY : array;
   }

   public static Integer[] nullToEmpty(Integer[] array) {
      return (Integer[])nullTo(array, EMPTY_INTEGER_OBJECT_ARRAY);
   }

   public static long[] nullToEmpty(long[] array) {
      return isEmpty(array) ? EMPTY_LONG_ARRAY : array;
   }

   public static Long[] nullToEmpty(Long[] array) {
      return (Long[])nullTo(array, EMPTY_LONG_OBJECT_ARRAY);
   }

   public static Object[] nullToEmpty(Object[] array) {
      return nullTo(array, EMPTY_OBJECT_ARRAY);
   }

   public static short[] nullToEmpty(short[] array) {
      return isEmpty(array) ? EMPTY_SHORT_ARRAY : array;
   }

   public static Short[] nullToEmpty(Short[] array) {
      return (Short[])nullTo(array, EMPTY_SHORT_OBJECT_ARRAY);
   }

   public static String[] nullToEmpty(String[] array) {
      return (String[])nullTo(array, EMPTY_STRING_ARRAY);
   }

   public static Object[] nullToEmpty(Object[] array, Class type) {
      if (type == null) {
         throw new IllegalArgumentException("The type must not be null");
      } else {
         return array == null ? (Object[])type.cast(Array.newInstance(type.getComponentType(), 0)) : array;
      }
   }

   private static ThreadLocalRandom random() {
      return ThreadLocalRandom.current();
   }

   public static boolean[] remove(boolean[] array, int index) {
      return (boolean[])remove((Object)array, index);
   }

   public static byte[] remove(byte[] array, int index) {
      return (byte[])remove((Object)array, index);
   }

   public static char[] remove(char[] array, int index) {
      return (char[])remove((Object)array, index);
   }

   public static double[] remove(double[] array, int index) {
      return (double[])remove((Object)array, index);
   }

   public static float[] remove(float[] array, int index) {
      return (float[])remove((Object)array, index);
   }

   public static int[] remove(int[] array, int index) {
      return (int[])remove((Object)array, index);
   }

   public static long[] remove(long[] array, int index) {
      return (long[])remove((Object)array, index);
   }

   private static Object remove(Object array, int index) {
      int length = getLength(array);
      if (index >= 0 && index < length) {
         Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
         System.arraycopy(array, 0, result, 0, index);
         if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, length - index - 1);
         }

         return result;
      } else {
         throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
      }
   }

   public static short[] remove(short[] array, int index) {
      return (short[])remove((Object)array, index);
   }

   public static Object[] remove(Object[] array, int index) {
      return remove((Object)array, index);
   }

   public static boolean[] removeAll(boolean[] array, int... indices) {
      return (boolean[])removeAll((Object)array, (int[])indices);
   }

   public static byte[] removeAll(byte[] array, int... indices) {
      return (byte[])removeAll((Object)array, (int[])indices);
   }

   public static char[] removeAll(char[] array, int... indices) {
      return (char[])removeAll((Object)array, (int[])indices);
   }

   public static double[] removeAll(double[] array, int... indices) {
      return (double[])removeAll((Object)array, (int[])indices);
   }

   public static float[] removeAll(float[] array, int... indices) {
      return (float[])removeAll((Object)array, (int[])indices);
   }

   public static int[] removeAll(int[] array, int... indices) {
      return (int[])removeAll((Object)array, (int[])indices);
   }

   public static long[] removeAll(long[] array, int... indices) {
      return (long[])removeAll((Object)array, (int[])indices);
   }

   static Object removeAll(Object array, BitSet indices) {
      if (array == null) {
         return null;
      } else {
         int srcLength = getLength(array);
         int removals = indices.cardinality();
         Object result = Array.newInstance(array.getClass().getComponentType(), srcLength - removals);
         int srcIndex = 0;

         int destIndex;
         int set;
         for(destIndex = 0; (set = indices.nextSetBit(srcIndex)) != -1; srcIndex = indices.nextClearBit(set)) {
            int count = set - srcIndex;
            if (count > 0) {
               System.arraycopy(array, srcIndex, result, destIndex, count);
               destIndex += count;
            }
         }

         int count = srcLength - srcIndex;
         if (count > 0) {
            System.arraycopy(array, srcIndex, result, destIndex, count);
         }

         return result;
      }
   }

   static Object removeAll(Object array, int... indices) {
      if (array == null) {
         return null;
      } else {
         int length = getLength(array);
         int diff = 0;
         int[] clonedIndices = ArraySorter.sort(clone(indices));
         if (isNotEmpty(clonedIndices)) {
            int i = clonedIndices.length;
            int prevIndex = length;

            while(true) {
               --i;
               if (i < 0) {
                  break;
               }

               int index = clonedIndices[i];
               if (index < 0 || index >= length) {
                  throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
               }

               if (index < prevIndex) {
                  ++diff;
                  prevIndex = index;
               }
            }
         }

         Object result = Array.newInstance(array.getClass().getComponentType(), length - diff);
         if (diff < length && clonedIndices != null) {
            int end = length;
            int dest = length - diff;

            for(int i = clonedIndices.length - 1; i >= 0; --i) {
               int index = clonedIndices[i];
               if (end - index > 1) {
                  int cp = end - index - 1;
                  dest -= cp;
                  System.arraycopy(array, index + 1, result, dest, cp);
               }

               end = index;
            }

            if (end > 0) {
               System.arraycopy(array, 0, result, 0, end);
            }
         }

         return result;
      }
   }

   public static short[] removeAll(short[] array, int... indices) {
      return (short[])removeAll((Object)array, (int[])indices);
   }

   public static Object[] removeAll(Object[] array, int... indices) {
      return removeAll((Object)array, (int[])indices);
   }

   /** @deprecated */
   @Deprecated
   public static boolean[] removeAllOccurences(boolean[] array, boolean element) {
      return (boolean[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static byte[] removeAllOccurences(byte[] array, byte element) {
      return (byte[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static char[] removeAllOccurences(char[] array, char element) {
      return (char[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static double[] removeAllOccurences(double[] array, double element) {
      return (double[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static float[] removeAllOccurences(float[] array, float element) {
      return (float[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static int[] removeAllOccurences(int[] array, int element) {
      return (int[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static long[] removeAllOccurences(long[] array, long element) {
      return (long[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static short[] removeAllOccurences(short[] array, short element) {
      return (short[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   /** @deprecated */
   @Deprecated
   public static Object[] removeAllOccurences(Object[] array, Object element) {
      return removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static boolean[] removeAllOccurrences(boolean[] array, boolean element) {
      return (boolean[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static byte[] removeAllOccurrences(byte[] array, byte element) {
      return (byte[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static char[] removeAllOccurrences(char[] array, char element) {
      return (char[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static double[] removeAllOccurrences(double[] array, double element) {
      return (double[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static float[] removeAllOccurrences(float[] array, float element) {
      return (float[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static int[] removeAllOccurrences(int[] array, int element) {
      return (int[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static long[] removeAllOccurrences(long[] array, long element) {
      return (long[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static short[] removeAllOccurrences(short[] array, short element) {
      return (short[])removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static Object[] removeAllOccurrences(Object[] array, Object element) {
      return removeAll((Object)array, (BitSet)indexesOf(array, element));
   }

   public static boolean[] removeElement(boolean[] array, boolean element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static byte[] removeElement(byte[] array, byte element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static char[] removeElement(char[] array, char element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static double[] removeElement(double[] array, double element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static float[] removeElement(float[] array, float element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static int[] removeElement(int[] array, int element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static long[] removeElement(long[] array, long element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static short[] removeElement(short[] array, short element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static Object[] removeElement(Object[] array, Object element) {
      int index = indexOf(array, element);
      return index == -1 ? clone(array) : remove(array, index);
   }

   public static boolean[] removeElements(boolean[] array, boolean... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Boolean, MutableInt> occurrences = new HashMap(2);

         for(boolean v : values) {
            Boolean boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            boolean key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (boolean[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static byte[] removeElements(byte[] array, byte... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         Map<Byte, MutableInt> occurrences = new HashMap(values.length);

         for(byte v : values) {
            Byte boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            byte key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (byte[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static char[] removeElements(char[] array, char... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Character, MutableInt> occurrences = new HashMap(values.length);

         for(char v : values) {
            Character boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            char key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (char[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static double[] removeElements(double[] array, double... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Double, MutableInt> occurrences = new HashMap(values.length);

         for(double v : values) {
            Double boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            double key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (double[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static float[] removeElements(float[] array, float... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Float, MutableInt> occurrences = new HashMap(values.length);

         for(float v : values) {
            Float boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            float key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (float[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static int[] removeElements(int[] array, int... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Integer, MutableInt> occurrences = new HashMap(values.length);

         for(int v : values) {
            Integer boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            int key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (int[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static long[] removeElements(long[] array, long... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Long, MutableInt> occurrences = new HashMap(values.length);

         for(long v : values) {
            Long boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            long key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (long[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   public static short[] removeElements(short[] array, short... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<Short, MutableInt> occurrences = new HashMap(values.length);

         for(short v : values) {
            Short boxed = v;
            MutableInt count = (MutableInt)occurrences.get(boxed);
            if (count == null) {
               occurrences.put(boxed, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            short key = array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         return (short[])removeAll((Object)array, (BitSet)toRemove);
      } else {
         return clone(array);
      }
   }

   @SafeVarargs
   public static Object[] removeElements(Object[] array, Object... values) {
      if (!isEmpty(array) && !isEmpty(values)) {
         HashMap<T, MutableInt> occurrences = new HashMap(values.length);

         for(Object v : values) {
            MutableInt count = (MutableInt)occurrences.get(v);
            if (count == null) {
               occurrences.put(v, new MutableInt(1));
            } else {
               count.increment();
            }
         }

         BitSet toRemove = new BitSet();

         for(int i = 0; i < array.length; ++i) {
            T key = (T)array[i];
            MutableInt count = (MutableInt)occurrences.get(key);
            if (count != null) {
               if (count.decrementAndGet() == 0) {
                  occurrences.remove(key);
               }

               toRemove.set(i);
            }
         }

         T[] result = (T[])((Object[])removeAll((Object)array, (BitSet)toRemove));
         return result;
      } else {
         return clone(array);
      }
   }

   public static void reverse(boolean[] array) {
      if (array != null) {
         reverse((boolean[])array, 0, array.length);
      }
   }

   public static void reverse(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            boolean tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(byte[] array) {
      if (array != null) {
         reverse((byte[])array, 0, array.length);
      }

   }

   public static void reverse(byte[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            byte tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(char[] array) {
      if (array != null) {
         reverse((char[])array, 0, array.length);
      }

   }

   public static void reverse(char[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            char tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(double[] array) {
      if (array != null) {
         reverse((double[])array, 0, array.length);
      }

   }

   public static void reverse(double[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            double tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(float[] array) {
      if (array != null) {
         reverse((float[])array, 0, array.length);
      }

   }

   public static void reverse(float[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            float tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(int[] array) {
      if (array != null) {
         reverse((int[])array, 0, array.length);
      }

   }

   public static void reverse(int[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            int tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(long[] array) {
      if (array != null) {
         reverse((long[])array, 0, array.length);
      }

   }

   public static void reverse(long[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            long tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(Object[] array) {
      if (array != null) {
         reverse((Object[])array, 0, array.length);
      }

   }

   public static void reverse(Object[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            Object tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static void reverse(short[] array) {
      if (array != null) {
         reverse((short[])array, 0, array.length);
      }

   }

   public static void reverse(short[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array != null) {
         int i = Math.max(startIndexInclusive, 0);

         for(int j = Math.min(array.length, endIndexExclusive) - 1; j > i; ++i) {
            short tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            --j;
         }

      }
   }

   public static Object[] setAll(Object[] array, IntFunction generator) {
      if (array != null && generator != null) {
         Arrays.setAll(array, generator);
      }

      return array;
   }

   public static Object[] setAll(Object[] array, Supplier generator) {
      if (array != null && generator != null) {
         for(int i = 0; i < array.length; ++i) {
            array[i] = generator.get();
         }
      }

      return array;
   }

   public static void shift(boolean[] array, int offset) {
      if (array != null) {
         shift((boolean[])array, 0, array.length, offset);
      }

   }

   public static void shift(boolean[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(byte[] array, int offset) {
      if (array != null) {
         shift((byte[])array, 0, array.length, offset);
      }

   }

   public static void shift(byte[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(char[] array, int offset) {
      if (array != null) {
         shift((char[])array, 0, array.length, offset);
      }

   }

   public static void shift(char[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(double[] array, int offset) {
      if (array != null) {
         shift((double[])array, 0, array.length, offset);
      }

   }

   public static void shift(double[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(float[] array, int offset) {
      if (array != null) {
         shift((float[])array, 0, array.length, offset);
      }

   }

   public static void shift(float[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(int[] array, int offset) {
      if (array != null) {
         shift((int[])array, 0, array.length, offset);
      }

   }

   public static void shift(int[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(long[] array, int offset) {
      if (array != null) {
         shift((long[])array, 0, array.length, offset);
      }

   }

   public static void shift(long[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(Object[] array, int offset) {
      if (array != null) {
         shift((Object[])array, 0, array.length, offset);
      }

   }

   public static void shift(Object[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shift(short[] array, int offset) {
      if (array != null) {
         shift((short[])array, 0, array.length, offset);
      }

   }

   public static void shift(short[] array, int startIndexInclusive, int endIndexExclusive, int offset) {
      if (array != null && startIndexInclusive < array.length - 1 && endIndexExclusive > 0) {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int n = endIndexExclusive - startIndexInclusive;
         if (n > 1) {
            offset %= n;
            if (offset < 0) {
               offset += n;
            }

            while(n > 1 && offset > 0) {
               int nOffset = n - offset;
               if (offset > nOffset) {
                  swap(array, startIndexInclusive, startIndexInclusive + n - nOffset, nOffset);
                  n = offset;
                  offset -= nOffset;
               } else {
                  if (offset >= nOffset) {
                     swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                     break;
                  }

                  swap(array, startIndexInclusive, startIndexInclusive + nOffset, offset);
                  startIndexInclusive += offset;
                  n = nOffset;
               }
            }

         }
      }
   }

   public static void shuffle(boolean[] array) {
      shuffle((boolean[])array, random());
   }

   public static void shuffle(boolean[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((boolean[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(byte[] array) {
      shuffle((byte[])array, random());
   }

   public static void shuffle(byte[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((byte[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(char[] array) {
      shuffle((char[])array, random());
   }

   public static void shuffle(char[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((char[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(double[] array) {
      shuffle((double[])array, random());
   }

   public static void shuffle(double[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((double[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(float[] array) {
      shuffle((float[])array, random());
   }

   public static void shuffle(float[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((float[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(int[] array) {
      shuffle((int[])array, random());
   }

   public static void shuffle(int[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((int[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(long[] array) {
      shuffle((long[])array, random());
   }

   public static void shuffle(long[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((long[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(Object[] array) {
      shuffle((Object[])array, random());
   }

   public static void shuffle(Object[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((Object[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static void shuffle(short[] array) {
      shuffle((short[])array, random());
   }

   public static void shuffle(short[] array, Random random) {
      for(int i = array.length; i > 1; --i) {
         swap((short[])array, i - 1, random.nextInt(i), 1);
      }

   }

   public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_BOOLEAN_ARRAY : (boolean[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new boolean[x$0]));
      }
   }

   public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_BYTE_ARRAY : (byte[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new byte[x$0]));
      }
   }

   public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_CHAR_ARRAY : (char[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new char[x$0]));
      }
   }

   public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_DOUBLE_ARRAY : (double[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new double[x$0]));
      }
   }

   public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_FLOAT_ARRAY : (float[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new float[x$0]));
      }
   }

   public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_INT_ARRAY : (int[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new int[x$0]));
      }
   }

   public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_LONG_ARRAY : (long[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new long[x$0]));
      }
   }

   public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         return newSize <= 0 ? EMPTY_SHORT_ARRAY : (short[])arraycopy(array, startIndexInclusive, 0, newSize, (Function)((x$0) -> new short[x$0]));
      }
   }

   public static Object[] subarray(Object[] array, int startIndexInclusive, int endIndexExclusive) {
      if (array == null) {
         return null;
      } else {
         startIndexInclusive = max0(startIndexInclusive);
         endIndexExclusive = Math.min(endIndexExclusive, array.length);
         int newSize = endIndexExclusive - startIndexInclusive;
         Class<T> type = getComponentType(array);
         return newSize <= 0 ? newInstance(type, 0) : (Object[])arraycopy(array, startIndexInclusive, 0, newSize, (Supplier)(() -> newInstance(type, newSize)));
      }
   }

   public static void swap(boolean[] array, int offset1, int offset2) {
      swap((boolean[])array, offset1, offset2, 1);
   }

   public static void swap(boolean[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            boolean aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(byte[] array, int offset1, int offset2) {
      swap((byte[])array, offset1, offset2, 1);
   }

   public static void swap(byte[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            byte aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(char[] array, int offset1, int offset2) {
      swap((char[])array, offset1, offset2, 1);
   }

   public static void swap(char[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            char aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(double[] array, int offset1, int offset2) {
      swap((double[])array, offset1, offset2, 1);
   }

   public static void swap(double[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            double aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(float[] array, int offset1, int offset2) {
      swap((float[])array, offset1, offset2, 1);
   }

   public static void swap(float[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            float aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(int[] array, int offset1, int offset2) {
      swap((int[])array, offset1, offset2, 1);
   }

   public static void swap(int[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            int aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(long[] array, int offset1, int offset2) {
      swap((long[])array, offset1, offset2, 1);
   }

   public static void swap(long[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            long aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(Object[] array, int offset1, int offset2) {
      swap((Object[])array, offset1, offset2, 1);
   }

   public static void swap(Object[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

         for(int i = 0; i < len; ++offset2) {
            Object aux = array[offset1];
            array[offset1] = array[offset2];
            array[offset2] = aux;
            ++i;
            ++offset1;
         }

      }
   }

   public static void swap(short[] array, int offset1, int offset2) {
      swap((short[])array, offset1, offset2, 1);
   }

   public static void swap(short[] array, int offset1, int offset2, int len) {
      if (!isEmpty(array) && offset1 < array.length && offset2 < array.length) {
         offset1 = max0(offset1);
         offset2 = max0(offset2);
         if (offset1 != offset2) {
            len = Math.min(Math.min(len, array.length - offset1), array.length - offset2);

            for(int i = 0; i < len; ++offset2) {
               short aux = array[offset1];
               array[offset1] = array[offset2];
               array[offset2] = aux;
               ++i;
               ++offset1;
            }

         }
      }
   }

   public static Object[] toArray(Object... items) {
      return items;
   }

   public static Map toMap(Object[] array) {
      if (array == null) {
         return null;
      } else {
         Map<Object, Object> map = new HashMap((int)((double)array.length * (double)1.5F));

         for(int i = 0; i < array.length; ++i) {
            Object object = array[i];
            if (object instanceof Map.Entry) {
               Map.Entry<?, ?> entry = (Map.Entry)object;
               map.put(entry.getKey(), entry.getValue());
            } else {
               if (!(object instanceof Object[])) {
                  throw new IllegalArgumentException("Array element " + i + ", '" + object + "', is neither of type Map.Entry nor an Array");
               }

               Object[] entry = object;
               if (entry.length < 2) {
                  throw new IllegalArgumentException("Array element " + i + ", '" + object + "', has a length less than 2");
               }

               map.put(entry[0], entry[1]);
            }
         }

         return map;
      }
   }

   public static Boolean[] toObject(boolean[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_BOOLEAN_OBJECT_ARRAY;
      } else {
         Boolean[] result = new Boolean[array.length];
         return (Boolean[])setAll(result, (IntFunction)((i) -> array[i] ? Boolean.TRUE : Boolean.FALSE));
      }
   }

   public static Byte[] toObject(byte[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_BYTE_OBJECT_ARRAY : (Byte[])setAll(new Byte[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static Character[] toObject(char[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_CHARACTER_OBJECT_ARRAY : (Character[])setAll(new Character[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static Double[] toObject(double[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_DOUBLE_OBJECT_ARRAY : (Double[])setAll(new Double[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static Float[] toObject(float[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_FLOAT_OBJECT_ARRAY : (Float[])setAll(new Float[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static Integer[] toObject(int[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_INTEGER_OBJECT_ARRAY : (Integer[])setAll(new Integer[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static Long[] toObject(long[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_LONG_OBJECT_ARRAY : (Long[])setAll(new Long[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static Short[] toObject(short[] array) {
      if (array == null) {
         return null;
      } else {
         return array.length == 0 ? EMPTY_SHORT_OBJECT_ARRAY : (Short[])setAll(new Short[array.length], (IntFunction)((i) -> array[i]));
      }
   }

   public static boolean[] toPrimitive(Boolean[] array) {
      return toPrimitive(array, false);
   }

   public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_BOOLEAN_ARRAY;
      } else {
         boolean[] result = new boolean[array.length];

         for(int i = 0; i < array.length; ++i) {
            Boolean b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static byte[] toPrimitive(Byte[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_BYTE_ARRAY;
      } else {
         byte[] result = new byte[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_BYTE_ARRAY;
      } else {
         byte[] result = new byte[array.length];

         for(int i = 0; i < array.length; ++i) {
            Byte b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static char[] toPrimitive(Character[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_CHAR_ARRAY;
      } else {
         char[] result = new char[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static char[] toPrimitive(Character[] array, char valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_CHAR_ARRAY;
      } else {
         char[] result = new char[array.length];

         for(int i = 0; i < array.length; ++i) {
            Character b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static double[] toPrimitive(Double[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_DOUBLE_ARRAY;
      } else {
         double[] result = new double[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static double[] toPrimitive(Double[] array, double valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_DOUBLE_ARRAY;
      } else {
         double[] result = new double[array.length];

         for(int i = 0; i < array.length; ++i) {
            Double b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static float[] toPrimitive(Float[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_FLOAT_ARRAY;
      } else {
         float[] result = new float[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static float[] toPrimitive(Float[] array, float valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_FLOAT_ARRAY;
      } else {
         float[] result = new float[array.length];

         for(int i = 0; i < array.length; ++i) {
            Float b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static int[] toPrimitive(Integer[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_INT_ARRAY;
      } else {
         int[] result = new int[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static int[] toPrimitive(Integer[] array, int valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_INT_ARRAY;
      } else {
         int[] result = new int[array.length];

         for(int i = 0; i < array.length; ++i) {
            Integer b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static long[] toPrimitive(Long[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_LONG_ARRAY;
      } else {
         long[] result = new long[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static long[] toPrimitive(Long[] array, long valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_LONG_ARRAY;
      } else {
         long[] result = new long[array.length];

         for(int i = 0; i < array.length; ++i) {
            Long b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static Object toPrimitive(Object array) {
      if (array == null) {
         return null;
      } else {
         Class<?> ct = array.getClass().getComponentType();
         Class<?> pt = ClassUtils.wrapperToPrimitive(ct);
         if (Boolean.TYPE.equals(pt)) {
            return toPrimitive((Boolean[])array);
         } else if (Character.TYPE.equals(pt)) {
            return toPrimitive((Character[])array);
         } else if (Byte.TYPE.equals(pt)) {
            return toPrimitive((Byte[])array);
         } else if (Integer.TYPE.equals(pt)) {
            return toPrimitive((Integer[])array);
         } else if (Long.TYPE.equals(pt)) {
            return toPrimitive((Long[])array);
         } else if (Short.TYPE.equals(pt)) {
            return toPrimitive((Short[])array);
         } else if (Double.TYPE.equals(pt)) {
            return toPrimitive((Double[])array);
         } else {
            return Float.TYPE.equals(pt) ? toPrimitive((Float[])array) : array;
         }
      }
   }

   public static short[] toPrimitive(Short[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_SHORT_ARRAY;
      } else {
         short[] result = new short[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i];
         }

         return result;
      }
   }

   public static short[] toPrimitive(Short[] array, short valueForNull) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_SHORT_ARRAY;
      } else {
         short[] result = new short[array.length];

         for(int i = 0; i < array.length; ++i) {
            Short b = array[i];
            result[i] = b == null ? valueForNull : b;
         }

         return result;
      }
   }

   public static String toString(Object array) {
      return toString(array, "{}");
   }

   public static String toString(Object array, String stringIfNull) {
      return array == null ? stringIfNull : (new ToStringBuilder(array, ToStringStyle.SIMPLE_STYLE)).append(array).toString();
   }

   public static String[] toStringArray(Object[] array) {
      if (array == null) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_STRING_ARRAY;
      } else {
         String[] result = new String[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = array[i].toString();
         }

         return result;
      }
   }

   public static String[] toStringArray(Object[] array, String valueForNullElements) {
      if (null == array) {
         return null;
      } else if (array.length == 0) {
         return EMPTY_STRING_ARRAY;
      } else {
         String[] result = new String[array.length];

         for(int i = 0; i < array.length; ++i) {
            result[i] = Objects.toString(array[i], valueForNullElements);
         }

         return result;
      }
   }
}
