package scala.collection.immutable;

import scala.collection.generic.BitOperations;

public final class LongMapUtils$ implements BitOperations.Long {
   public static final LongMapUtils$ MODULE$ = new LongMapUtils$();

   static {
      LongMapUtils$ var10000 = MODULE$;
   }

   public boolean zero(final long i, final long mask) {
      return BitOperations.Long.zero$(this, i, mask);
   }

   public long mask(final long i, final long mask) {
      return BitOperations.Long.mask$(this, i, mask);
   }

   public boolean hasMatch(final long key, final long prefix, final long m) {
      return BitOperations.Long.hasMatch$(this, key, prefix, m);
   }

   public boolean unsignedCompare(final long i, final long j) {
      return BitOperations.Long.unsignedCompare$(this, i, j);
   }

   public boolean shorter(final long m1, final long m2) {
      return BitOperations.Long.shorter$(this, m1, m2);
   }

   public long complement(final long i) {
      return BitOperations.Long.complement$(this, i);
   }

   public IndexedSeq bits(final long num) {
      return BitOperations.Long.bits$(this, num);
   }

   public String bitString(final long num, final String sep) {
      return BitOperations.Long.bitString$(this, num, sep);
   }

   public String bitString$default$2() {
      return BitOperations.Long.bitString$default$2$(this);
   }

   public long highestOneBit(final long j) {
      return BitOperations.Long.highestOneBit$(this, j);
   }

   public long branchMask(final long i, final long j) {
      return Long.highestOneBit(i ^ j);
   }

   public LongMap join(final long p1, final LongMap t1, final long p2, final LongMap t2) {
      long m = Long.highestOneBit(p1 ^ p2);
      long complement_i = m - 1L;
      long p = p1 & (~complement_i ^ m);
      return BitOperations.Long.zero$(this, p1, m) ? new LongMap.Bin(p, m, t1, t2) : new LongMap.Bin(p, m, t2, t1);
   }

   public LongMap bin(final long prefix, final long mask, final LongMap left, final LongMap right) {
      if (LongMap.Nil$.MODULE$.equals(right)) {
         return left;
      } else {
         return (LongMap)(LongMap.Nil$.MODULE$.equals(left) ? right : new LongMap.Bin(prefix, mask, left, right));
      }
   }

   private LongMapUtils$() {
   }
}
