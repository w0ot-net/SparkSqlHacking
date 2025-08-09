package org.roaringbitmap.buffer;

import org.roaringbitmap.PeekableCharIterator;

final class MappeableRunContainerCharIterator implements PeekableCharIterator {
   private int pos;
   private int le = 0;
   private int maxlength;
   private int base;
   private MappeableRunContainer parent;

   MappeableRunContainerCharIterator() {
   }

   MappeableRunContainerCharIterator(MappeableRunContainer p) {
      this.wrap(p);
   }

   public PeekableCharIterator clone() {
      try {
         return (PeekableCharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.pos < this.parent.nbrruns;
   }

   public char next() {
      char ans = (char)(this.base + this.le);
      ++this.le;
      if (this.le > this.maxlength) {
         ++this.pos;
         this.le = 0;
         if (this.pos < this.parent.nbrruns) {
            this.maxlength = this.parent.getLength(this.pos);
            this.base = this.parent.getValue(this.pos);
         }
      }

      return ans;
   }

   public int nextAsInt() {
      int ans = this.base + this.le;
      ++this.le;
      if (this.le > this.maxlength) {
         ++this.pos;
         this.le = 0;
         if (this.pos < this.parent.nbrruns) {
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
      this.pos = 0;
      this.le = 0;
      if (this.pos < this.parent.nbrruns) {
         this.maxlength = this.parent.getLength(this.pos);
         this.base = this.parent.getValue(this.pos);
      }

   }

   public void advanceIfNeeded(char minval) {
      while(true) {
         if (this.base + this.maxlength < minval) {
            ++this.pos;
            this.le = 0;
            if (this.pos < this.parent.nbrruns) {
               this.maxlength = this.parent.getLength(this.pos);
               this.base = this.parent.getValue(this.pos);
               continue;
            }

            return;
         }

         if (this.base > minval) {
            return;
         }

         this.le = minval - this.base;
         return;
      }
   }

   public char peekNext() {
      return (char)(this.base + this.le);
   }
}
