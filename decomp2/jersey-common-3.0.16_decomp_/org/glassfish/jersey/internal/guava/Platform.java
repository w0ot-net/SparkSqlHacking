package org.glassfish.jersey.internal.guava;

import java.lang.reflect.Array;

final class Platform {
   private Platform() {
   }

   static long systemNanoTime() {
      return System.nanoTime();
   }

   static Object[] newArray(Object[] reference, int length) {
      Class<?> type = reference.getClass().getComponentType();
      T[] result = (T[])((Object[])((Object[])Array.newInstance(type, length)));
      return result;
   }
}
