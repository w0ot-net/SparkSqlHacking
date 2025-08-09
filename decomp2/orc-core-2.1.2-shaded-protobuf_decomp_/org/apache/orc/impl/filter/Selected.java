package org.apache.orc.impl.filter;

import org.apache.orc.OrcFilterContext;

public class Selected {
   int[] sel;
   int selSize;

   Selected(int[] sel) {
      this.sel = sel;
      this.selSize = 0;
   }

   Selected() {
      this(new int[1024]);
   }

   void clear() {
      this.selSize = 0;
   }

   void selectAll(Selected src) {
      System.arraycopy(src.sel, 0, this.sel, 0, src.selSize);
      this.selSize = src.selSize;
   }

   void initialize(OrcFilterContext fc) {
      this.ensureSize(fc.getSelectedSize());
      this.selSize = fc.getSelectedSize();
      if (fc.isSelectedInUse()) {
         System.arraycopy(fc.getSelected(), 0, this.sel, 0, this.selSize);
      } else {
         for(int i = 0; i < this.selSize; this.sel[i] = i++) {
         }
      }

   }

   void ensureSize(int size) {
      if (size > this.sel.length) {
         this.sel = new int[size];
         this.selSize = 0;
      }

   }

   void set(Selected inBound) {
      this.ensureSize(inBound.selSize);
      System.arraycopy(inBound.sel, 0, this.sel, 0, inBound.selSize);
      this.selSize = inBound.selSize;
   }

   void unionDisjoint(Selected src) {
      int writeIdx = src.selSize + this.selSize - 1;
      int srcIdx = src.selSize - 1;
      int thisIdx = this.selSize - 1;

      while(thisIdx >= 0 || srcIdx >= 0) {
         if (srcIdx >= 0 && (thisIdx < 0 || src.sel[srcIdx] >= this.sel[thisIdx])) {
            this.sel[writeIdx--] = src.sel[srcIdx--];
         } else {
            this.sel[writeIdx--] = this.sel[thisIdx--];
         }
      }

      this.selSize += src.selSize;
   }

   void minus(Selected src) {
      int writeidx = 0;
      int evalIdx = 0;
      int srcIdx = 0;

      while(srcIdx < src.selSize && evalIdx < this.selSize) {
         if (this.sel[evalIdx] < src.sel[srcIdx]) {
            this.sel[writeidx] = this.sel[evalIdx];
            ++evalIdx;
            ++writeidx;
         } else if (this.sel[evalIdx] > src.sel[srcIdx]) {
            ++srcIdx;
         } else {
            ++evalIdx;
            ++srcIdx;
         }
      }

      if (evalIdx < this.selSize) {
         System.arraycopy(this.sel, evalIdx, this.sel, writeidx, this.selSize - evalIdx);
         writeidx += this.selSize - evalIdx;
      }

      this.selSize = writeidx;
   }
}
