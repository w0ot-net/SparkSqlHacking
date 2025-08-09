package io.jsonwebtoken.lang;

import java.lang.reflect.Array;
import java.util.List;

public final class Arrays {
   private Arrays() {
   }

   public static int length(Object[] a) {
      return a == null ? 0 : a.length;
   }

   public static List asList(Object[] a) {
      return Objects.isEmpty(a) ? Collections.emptyList() : java.util.Arrays.asList(a);
   }

   public static int length(byte[] bytes) {
      return bytes != null ? bytes.length : 0;
   }

   public static byte[] clean(byte[] bytes) {
      return length(bytes) > 0 ? bytes : null;
   }

   public static Object copy(Object obj) {
      if (obj == null) {
         return null;
      } else {
         Assert.isTrue(Objects.isArray(obj), "Argument must be an array.");
         if (obj instanceof Object[]) {
            return ((Object[])((Object[])obj)).clone();
         } else if (obj instanceof boolean[]) {
            return ((boolean[])((boolean[])obj)).clone();
         } else if (obj instanceof byte[]) {
            return ((byte[])((byte[])obj)).clone();
         } else if (obj instanceof char[]) {
            return ((char[])((char[])obj)).clone();
         } else if (obj instanceof double[]) {
            return ((double[])((double[])obj)).clone();
         } else if (obj instanceof float[]) {
            return ((float[])((float[])obj)).clone();
         } else if (obj instanceof int[]) {
            return ((int[])((int[])obj)).clone();
         } else if (obj instanceof long[]) {
            return ((long[])((long[])obj)).clone();
         } else if (obj instanceof short[]) {
            return ((short[])((short[])obj)).clone();
         } else {
            Class<?> componentType = obj.getClass().getComponentType();
            int length = Array.getLength(obj);
            Object[] copy = Array.newInstance(componentType, length);

            for(int i = 0; i < length; ++i) {
               copy[i] = Array.get(obj, i);
            }

            return copy;
         }
      }
   }
}
