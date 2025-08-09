package org.roaringbitmap.buffer;

import org.roaringbitmap.CharIterator;

final class ReverseMappeableRunContainerCharIterator implements CharIterator {
   private int pos;
   private int le;
   private int maxlength;
   private int base;
   private MappeableRunContainer parent;

   ReverseMappeableRunContainerCharIterator() {
   }

   ReverseMappeableRunContainerCharIterator(MappeableRunContainer p) {
      this.wrap(p);
   }

   public CharIterator clone() {
      try {
         return (CharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
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
            this.maxlength = this.parent.getLength(this.pos);
            this.base = this.parent.getValue(this.pos);
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
            this.maxlength = this.parent.getLength(this.pos);
            this.base = this.parent.getValue(this.pos);
         }
      }

      return ans;
   }

   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   void wrap(MappeableRunContainer p) {
      this.parent = p;
      this.pos = this.parent.nbrruns - 1;
      this.le = 0;
      if (this.pos >= 0) {
         this.maxlength = this.parent.getLength(this.pos);
         this.base = this.parent.getValue(this.pos);
      }

   }
}
