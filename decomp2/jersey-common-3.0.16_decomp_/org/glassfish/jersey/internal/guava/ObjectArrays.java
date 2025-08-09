package org.glassfish.jersey.internal.guava;

final class ObjectArrays {
   static final Object[] EMPTY_ARRAY = new Object[0];

   private ObjectArrays() {
   }

   public static Object[] newArray(Object[] reference, int length) {
      return Platform.newArray(reference, length);
   }

   static Object[] arraysCopyOf(Object[] original, int newLength) {
      T[] copy = (T[])newArray(original, newLength);
      System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
      return copy;
   }
}
