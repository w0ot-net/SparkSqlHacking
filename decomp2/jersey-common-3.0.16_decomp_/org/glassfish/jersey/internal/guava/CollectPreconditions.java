package org.glassfish.jersey.internal.guava;

final class CollectPreconditions {
   static int checkNonnegative(int value, String name) {
      if (value < 0) {
         throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
      } else {
         return value;
      }
   }

   static void checkRemove(boolean canRemove) {
      Preconditions.checkState(canRemove, "no calls to next() since the last call to remove()");
   }
}
