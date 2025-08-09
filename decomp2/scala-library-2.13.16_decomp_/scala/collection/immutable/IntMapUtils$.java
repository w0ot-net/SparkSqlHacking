package scala.collection.immutable;

import scala.collection.generic.BitOperations;

public final class IntMapUtils$ implements BitOperations.Int {
   public static final IntMapUtils$ MODULE$ = new IntMapUtils$();

   static {
      IntMapUtils$ var10000 = MODULE$;
   }

   public boolean zero(final int i, final int mask) {
      return BitOperations.Int.zero$(this, i, mask);
   }

   public int mask(final int i, final int mask) {
      return BitOperations.Int.mask$(this, i, mask);
   }

   public boolean hasMatch(final int key, final int prefix, final int m) {
      return BitOperations.Int.hasMatch$(this, key, prefix, m);
   }

   public boolean unsignedCompare(final int i, final int j) {
      return BitOperations.Int.unsignedCompare$(this, i, j);
   }

   public boolean shorter(final int m1, final int m2) {
      return BitOperations.Int.shorter$(this, m1, m2);
   }

   public int complement(final int i) {
      return BitOperations.Int.complement$(this, i);
   }

   public IndexedSeq bits(final int num) {
      return BitOperations.Int.bits$(this, num);
   }

   public String bitString(final int num, final String sep) {
      return BitOperations.Int.bitString$(this, num, sep);
   }

   public String bitString$default$2() {
      return BitOperations.Int.bitString$default$2$(this);
   }

   public int highestOneBit(final int j) {
      return BitOperations.Int.highestOneBit$(this, j);
   }

   public int branchMask(final int i, final int j) {
      return Integer.highestOneBit(i ^ j);
   }

   public IntMap join(final int p1, final IntMap t1, final int p2, final IntMap t2) {
      int m = Integer.highestOneBit(p1 ^ p2);
      int complement_i = m - 1;
      int p = p1 & (~complement_i ^ m);
      return BitOperations.Int.zero$(this, p1, m) ? new IntMap.Bin(p, m, t1, t2) : new IntMap.Bin(p, m, t2, t1);
   }

   public IntMap bin(final int prefix, final int mask, final IntMap left, final IntMap right) {
      if (IntMap.Nil$.MODULE$.equals(right)) {
         return left;
      } else {
         return (IntMap)(IntMap.Nil$.MODULE$.equals(left) ? right : new IntMap.Bin(prefix, mask, left, right));
      }
   }

   private IntMapUtils$() {
   }
}
