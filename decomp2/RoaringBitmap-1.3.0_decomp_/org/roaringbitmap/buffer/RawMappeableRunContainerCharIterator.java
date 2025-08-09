package org.roaringbitmap.buffer;

import org.roaringbitmap.PeekableCharIterator;

final class RawMappeableRunContainerCharIterator implements PeekableCharIterator {
   private int pos;
   private int le = 0;
   private int maxlength;
   private int base;
   private MappeableRunContainer parent;
   private char[] vl;

   RawMappeableRunContainerCharIterator(MappeableRunContainer p) {
      this.wrap(p);
   }

   public PeekableCharIterator clone() {
      try {
         return (PeekableCharIterator)super.clone();
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
      return this.pos < this.parent.nbrruns;
   }

   public char next() {
      char ans = (char)(this.base + this.le);
      ++this.le;
      if (this.le > this.maxlength) {
         ++this.pos;
         this.le = 0;
         if (this.pos < this.parent.nbrruns) {
            this.maxlength = this.getLength(this.pos);
            this.base = this.getValue(this.pos);
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
      this.parent = p;
      if (!this.parent.isArrayBacked()) {
         throw new RuntimeException("internal error");
      } else {
         this.vl = this.parent.valueslength.array();
         this.pos = 0;
         this.le = 0;
         if (this.pos < this.parent.nbrruns) {
            this.maxlength = this.getLength(this.pos);
            this.base = this.getValue(this.pos);
         }

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
