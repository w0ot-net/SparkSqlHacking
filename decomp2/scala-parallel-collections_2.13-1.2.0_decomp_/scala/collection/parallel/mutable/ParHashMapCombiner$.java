package scala.collection.parallel.mutable;

public final class ParHashMapCombiner$ {
   public static final ParHashMapCombiner$ MODULE$ = new ParHashMapCombiner$();
   private static final int discriminantbits = 5;
   private static final int numblocks;
   private static final int discriminantmask;
   private static final int nonmasklength;

   static {
      numblocks = 1 << MODULE$.discriminantbits();
      discriminantmask = (1 << MODULE$.discriminantbits()) - 1;
      nonmasklength = 32 - MODULE$.discriminantbits();
   }

   public int discriminantbits() {
      return discriminantbits;
   }

   public int numblocks() {
      return numblocks;
   }

   public int discriminantmask() {
      return discriminantmask;
   }

   public int nonmasklength() {
      return nonmasklength;
   }

   public ParHashMapCombiner apply() {
      return new ParHashMapCombiner() {
      };
   }

   private ParHashMapCombiner$() {
   }
}
