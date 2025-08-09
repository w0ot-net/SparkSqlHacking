package scala.collection.immutable;

public final class IndexedSeqDefaults$ {
   public static final IndexedSeqDefaults$ MODULE$ = new IndexedSeqDefaults$();
   private static final int defaultApplyPreferredMaxLength = liftedTree1$1();

   public int defaultApplyPreferredMaxLength() {
      return defaultApplyPreferredMaxLength;
   }

   // $FF: synthetic method
   private static final int liftedTree1$1() {
      int var10000;
      try {
         var10000 = Integer.parseInt(System.getProperty("scala.collection.immutable.IndexedSeq.defaultApplyPreferredMaxLength", "64"));
      } catch (SecurityException var0) {
         var10000 = 64;
      }

      return var10000;
   }

   private IndexedSeqDefaults$() {
   }
}
