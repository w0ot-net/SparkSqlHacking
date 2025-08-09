package org.sparkproject.jetty.util;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil implements Cloneable, Serializable {
   public static Object[] removeFromArray(Object[] array, Object item) {
      if (item != null && array != null) {
         int i = array.length;

         while(i-- > 0) {
            if (item.equals(array[i])) {
               Class<?> c = array == null ? item.getClass() : array.getClass().getComponentType();
               T[] na = (T[])((Object[])Array.newInstance(c, Array.getLength(array) - 1));
               if (i > 0) {
                  System.arraycopy(array, 0, na, 0, i);
               }

               if (i + 1 < array.length) {
                  System.arraycopy(array, i + 1, na, i, array.length - (i + 1));
               }

               return na;
            }
         }

         return array;
      } else {
         return array;
      }
   }

   public static Object[] add(Object[] array1, Object[] array2) {
      if (array1 != null && array1.length != 0) {
         if (array2 != null && array2.length != 0) {
            T[] na = (T[])Arrays.copyOf(array1, array1.length + array2.length);
            System.arraycopy(array2, 0, na, array1.length, array2.length);
            return na;
         } else {
            return array1;
         }
      } else {
         return array2;
      }
   }

   public static Object[] addToArray(Object[] array, Object item, Class type) {
      if (array == null) {
         if (type == null && item != null) {
            type = item.getClass();
         }

         T[] na = (T[])((Object[])Array.newInstance(type, 1));
         na[0] = item;
         return na;
      } else {
         T[] na = (T[])Arrays.copyOf(array, array.length + 1);
         na[array.length] = item;
         return na;
      }
   }

   public static Object[] prependToArray(Object item, Object[] array, Class type) {
      if (array == null) {
         if (type == null && item != null) {
            type = item.getClass();
         }

         T[] na = (T[])((Object[])Array.newInstance(type, 1));
         na[0] = item;
         return na;
      } else {
         Class<?> c = array.getClass().getComponentType();
         T[] na = (T[])((Object[])Array.newInstance(c, Array.getLength(array) + 1));
         System.arraycopy(array, 0, na, 1, array.length);
         na[0] = item;
         return na;
      }
   }

   public static List asMutableList(Object[] array) {
      return array != null && array.length != 0 ? new ArrayList(Arrays.asList(array)) : new ArrayList();
   }

   public static Object[] removeNulls(Object[] array) {
      for(Object t : array) {
         if (t == null) {
            List<T> list = new ArrayList();

            for(Object t2 : array) {
               if (t2 != null) {
                  list.add(t2);
               }
            }

            return list.toArray(Arrays.copyOf(array, list.size()));
         }
      }

      return array;
   }
}
