package org.apache.derby.iapi.util;

import java.util.BitSet;

public final class JBitSet {
   private final BitSet bitSet;
   private int size;

   public JBitSet(int var1) {
      this.bitSet = new BitSet(var1);
      this.size = var1;
   }

   private JBitSet(BitSet var1, int var2) {
      this.bitSet = var1;
      this.size = var2;
   }

   public void setTo(JBitSet var1) {
      this.and(var1);
      this.or(var1);
   }

   public boolean contains(JBitSet var1) {
      for(int var2 = 0; var2 < this.size; ++var2) {
         if (var1.bitSet.get(var2) && !this.bitSet.get(var2)) {
            return false;
         }
      }

      return true;
   }

   public boolean hasSingleBitSet() {
      boolean var1 = false;

      for(int var2 = 0; var2 < this.size; ++var2) {
         if (this.bitSet.get(var2)) {
            if (var1) {
               return false;
            }

            var1 = true;
         }
      }

      return var1;
   }

   public int getFirstSetBit() {
      for(int var1 = 0; var1 < this.size; ++var1) {
         if (this.bitSet.get(var1)) {
            return var1;
         }
      }

      return -1;
   }

   public void grow(int var1) {
      this.size = var1;
   }

   public void clearAll() {
      for(int var1 = 0; var1 < this.size; ++var1) {
         if (this.bitSet.get(var1)) {
            this.bitSet.clear(var1);
         }
      }

   }

   public String toString() {
      return this.bitSet.toString();
   }

   public boolean equals(Object var1) {
      return this.bitSet.equals(((JBitSet)var1).bitSet);
   }

   public int hashCode() {
      return this.bitSet.hashCode();
   }

   public Object clone() {
      return new JBitSet((BitSet)this.bitSet.clone(), this.size);
   }

   public boolean get(int var1) {
      return this.bitSet.get(var1);
   }

   public void set(int var1) {
      this.bitSet.set(var1);
   }

   public void clear(int var1) {
      this.bitSet.clear(var1);
   }

   public void and(JBitSet var1) {
      this.bitSet.and(var1.bitSet);
   }

   public void or(JBitSet var1) {
      this.bitSet.or(var1.bitSet);
   }

   public void xor(JBitSet var1) {
      this.bitSet.xor(var1.bitSet);
   }

   public int size() {
      return this.size;
   }
}
