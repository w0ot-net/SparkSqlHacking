package org.roaringbitmap;

import java.util.BitSet;
import java.util.stream.IntStream;

public class RoaringBitSet extends BitSet {
   private static final long serialVersionUID = 1L;
   private final RoaringBitmap roaringBitmap;

   public RoaringBitSet() {
      super(0);
      this.roaringBitmap = new RoaringBitmap();
   }

   private RoaringBitSet(RoaringBitmap roaringBitmap) {
      super(0);
      this.roaringBitmap = roaringBitmap;
   }

   public void set(int bitIndex) {
      this.roaringBitmap.add(bitIndex);
   }

   public void set(int bitIndex, boolean value) {
      if (value) {
         this.roaringBitmap.add(bitIndex);
      } else {
         this.roaringBitmap.remove(bitIndex);
      }

   }

   public void set(int fromIndex, int toIndex) {
      this.roaringBitmap.add((long)fromIndex, (long)toIndex);
   }

   public void set(int fromIndex, int toIndex, boolean value) {
      if (value) {
         this.roaringBitmap.add((long)fromIndex, (long)toIndex);
      } else {
         this.roaringBitmap.remove((long)fromIndex, (long)toIndex);
      }

   }

   public void clear(int bitIndex) {
      this.roaringBitmap.remove(bitIndex);
   }

   public void clear(int fromIndex, int toIndex) {
      this.roaringBitmap.remove((long)fromIndex, (long)toIndex);
   }

   public void clear() {
      this.roaringBitmap.clear();
   }

   public boolean get(int bitIndex) {
      return this.roaringBitmap.contains(bitIndex);
   }

   public BitSet get(int fromIndex, int toIndex) {
      RoaringBitmap newBitmap = this.roaringBitmap.selectRange((long)fromIndex, (long)toIndex);
      newBitmap = RoaringBitmap.addOffset(newBitmap, (long)(-fromIndex));
      return new RoaringBitSet(newBitmap);
   }

   public int nextSetBit(int fromIndex) {
      return (int)this.roaringBitmap.nextValue(fromIndex);
   }

   public int nextClearBit(int fromIndex) {
      return (int)this.roaringBitmap.nextAbsentValue(fromIndex);
   }

   public int previousSetBit(int fromIndex) {
      return (int)this.roaringBitmap.previousValue(fromIndex);
   }

   public int previousClearBit(int fromIndex) {
      return (int)this.roaringBitmap.previousAbsentValue(fromIndex);
   }

   public int length() {
      return this.roaringBitmap.isEmpty() ? 0 : this.roaringBitmap.last() + 1;
   }

   public boolean isEmpty() {
      return this.roaringBitmap.isEmpty();
   }

   public boolean intersects(BitSet set) {
      return set instanceof RoaringBitSet ? RoaringBitmap.intersects(this.roaringBitmap, ((RoaringBitSet)set).roaringBitmap) : RoaringBitmap.intersects(this.roaringBitmap, fromBitSet(set));
   }

   public int cardinality() {
      return this.roaringBitmap.getCardinality();
   }

   public void and(BitSet set) {
      if (set instanceof RoaringBitSet) {
         this.roaringBitmap.and(((RoaringBitSet)set).roaringBitmap);
      } else {
         this.roaringBitmap.and(fromBitSet(set));
      }

   }

   public void or(BitSet set) {
      if (set instanceof RoaringBitSet) {
         this.roaringBitmap.or(((RoaringBitSet)set).roaringBitmap);
      } else {
         this.roaringBitmap.or(fromBitSet(set));
      }

   }

   public void xor(BitSet set) {
      if (set instanceof RoaringBitSet) {
         this.roaringBitmap.xor(((RoaringBitSet)set).roaringBitmap);
      } else {
         this.roaringBitmap.xor(fromBitSet(set));
      }

   }

   public void andNot(BitSet set) {
      if (set instanceof RoaringBitSet) {
         this.roaringBitmap.andNot(((RoaringBitSet)set).roaringBitmap);
      } else {
         this.roaringBitmap.andNot(fromBitSet(set));
      }

   }

   public int hashCode() {
      return this.roaringBitmap.hashCode();
   }

   public int size() {
      if (this.roaringBitmap.isEmpty()) {
         return 0;
      } else {
         int lastBit = Math.max(this.length(), 64);
         int remainder = lastBit % 64;
         return remainder == 0 ? lastBit : lastBit + 64 - remainder;
      }
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else {
         return obj instanceof RoaringBitSet ? this.roaringBitmap.equals(((RoaringBitSet)obj).roaringBitmap) : false;
      }
   }

   public Object clone() {
      return new RoaringBitSet(this.roaringBitmap.clone());
   }

   public IntStream stream() {
      return this.roaringBitmap.stream();
   }

   public String toString() {
      return this.roaringBitmap.toString();
   }

   public void flip(int bitIndex) {
      this.roaringBitmap.flip((long)bitIndex, (long)bitIndex + 1L);
   }

   public void flip(int fromIndex, int toIndex) {
      this.roaringBitmap.flip((long)fromIndex, (long)toIndex);
   }

   public long[] toLongArray() {
      return BitSetUtil.toLongArray(this.roaringBitmap);
   }

   public byte[] toByteArray() {
      return BitSetUtil.toByteArray(this.roaringBitmap);
   }

   private static RoaringBitmap fromBitSet(BitSet bitSet) {
      return BitSetUtil.bitmapOf(bitSet);
   }
}
