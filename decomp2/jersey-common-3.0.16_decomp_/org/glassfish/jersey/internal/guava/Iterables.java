package org.glassfish.jersey.internal.guava;

final class Iterables {
   private Iterables() {
   }

   public static Object getFirst(Iterable iterable, Object defaultValue) {
      return Iterators.getNext(iterable.iterator(), defaultValue);
   }
}
