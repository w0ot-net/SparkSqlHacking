package scala.collection.parallel.immutable;

public final class HashSetCombiner$ {
   public static final HashSetCombiner$ MODULE$ = new HashSetCombiner$();
   private static final int rootbits = 5;
   private static final int rootsize = 32;

   public HashSetCombiner apply() {
      return new HashSetCombiner() {
      };
   }

   public int rootbits() {
      return rootbits;
   }

   public int rootsize() {
      return rootsize;
   }

   private HashSetCombiner$() {
   }
}
