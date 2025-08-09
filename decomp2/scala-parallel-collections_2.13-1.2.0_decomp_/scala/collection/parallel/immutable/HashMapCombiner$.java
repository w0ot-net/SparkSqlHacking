package scala.collection.parallel.immutable;

public final class HashMapCombiner$ {
   public static final HashMapCombiner$ MODULE$ = new HashMapCombiner$();
   private static final int rootbits = 5;
   private static final int rootsize = 32;

   public HashMapCombiner apply() {
      return new HashMapCombiner() {
      };
   }

   public int rootbits() {
      return rootbits;
   }

   public int rootsize() {
      return rootsize;
   }

   private HashMapCombiner$() {
   }
}
