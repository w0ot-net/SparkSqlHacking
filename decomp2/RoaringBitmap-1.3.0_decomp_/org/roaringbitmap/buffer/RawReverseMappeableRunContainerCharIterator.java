package org.roaringbitmap.buffer;

import org.roaringbitmap.CharIterator;

final class RawReverseMappeableRunContainerCharIterator implements CharIterator {
   private int pos;
   private int le;
   private int maxlength;
   private int base;
   private char[] vl;

   RawReverseMappeableRunContainerCharIterator(MappeableRunContainer p) {
      this.wrap(p);
   }

   public CharIterator clone() {
      try {
         return (CharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   private char getLength(int index) {
      return this.vl[2 * index + 1];
   }

   private char getValue(int index) {
      return this.vl[2 * index];
   }

   public boolean hasNext() {
      return this.pos >= 0;
   }

   public char next() {
      char ans = (char)(this.base + this.maxlength - this.le);
      ++this.le;
      if (this.le > this.maxlength) {
         --this.pos;
         this.le = 0;
         if (this.pos >= 0) {
            this.maxlength = this.getLength(this.pos);
            this.base = this.getValue(this.pos);
         }
      }

      return ans;
   }

   public int nextAsInt() {
      int ans = this.base + this.maxlength - this.le;
      ++this.le;
      if (this.le > this.maxlength) {
         --this.pos;
         this.le = 0;
         if (this.pos >= 0) {
            this.maxlength = this.getLength(this.pos);
            this.base = this.getValue(this.pos);
         }
      }

      return ans;
   }

   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   private void wrap(MappeableRunContainer p) {
      if (!p.isArrayBacked()) {
         throw new RuntimeException("internal error");
      } else {
         this.vl = p.valueslength.array();
         this.pos = p.nbrruns - 1;
         this.le = 0;
         if (this.pos >= 0) {
            this.maxlength = this.getLength(this.pos);
            this.base = this.getValue(this.pos);
         }

      }
   }
}
