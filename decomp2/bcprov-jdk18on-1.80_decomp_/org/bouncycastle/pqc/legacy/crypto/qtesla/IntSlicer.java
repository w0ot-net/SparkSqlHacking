package org.bouncycastle.pqc.legacy.crypto.qtesla;

final class IntSlicer {
   private final int[] values;
   private int base;

   IntSlicer(int[] var1, int var2) {
      this.values = var1;
      this.base = var2;
   }

   final int at(int var1) {
      return this.values[this.base + var1];
   }

   final int at(int var1, int var2) {
      return this.values[this.base + var1] = var2;
   }

   final int at(int var1, long var2) {
      return this.values[this.base + var1] = (int)var2;
   }

   final IntSlicer from(int var1) {
      return new IntSlicer(this.values, this.base + var1);
   }

   final void incBase(int var1) {
      this.base += var1;
   }

   final IntSlicer copy() {
      return new IntSlicer(this.values, this.base);
   }
}
