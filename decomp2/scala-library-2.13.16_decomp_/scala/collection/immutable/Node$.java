package scala.collection.immutable;

public final class Node$ {
   public static final Node$ MODULE$ = new Node$();
   private static final int MaxDepth = (int)Math.ceil(6.4);

   public final int HashCodeLength() {
      return 32;
   }

   public final int BitPartitionSize() {
      return 5;
   }

   public final int BitPartitionMask() {
      return 31;
   }

   public final int MaxDepth() {
      return MaxDepth;
   }

   public final int BranchingFactor() {
      return 32;
   }

   public final int maskFrom(final int hash, final int shift) {
      return hash >>> shift & 31;
   }

   public final int bitposFrom(final int mask) {
      return 1 << mask;
   }

   public final int indexFrom(final int bitmap, final int bitpos) {
      return Integer.bitCount(bitmap & bitpos - 1);
   }

   public final int indexFrom(final int bitmap, final int mask, final int bitpos) {
      return bitmap == -1 ? mask : Integer.bitCount(bitmap & bitpos - 1);
   }

   private Node$() {
   }
}
