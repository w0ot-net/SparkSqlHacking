package org.roaringbitmap;

final class ReverseRunContainerCharIterator implements PeekableCharIterator {
   int pos;
   private int le;
   private RunContainer parent;
   private int maxlength;
   private int base;

   ReverseRunContainerCharIterator() {
   }

   ReverseRunContainerCharIterator(RunContainer p) {
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

   public void advanceIfNeeded(char maxval) {
      while(true) {
         if (this.base > maxval) {
            --this.pos;
            this.le = 0;
            if (this.pos >= 0) {
               this.maxlength = this.parent.getLength(this.pos);
               this.base = this.parent.getValue(this.pos);
               continue;
            }

            return;
         }

         if (this.base + this.maxlength < maxval) {
            return;
         }

         this.le = this.maxlength + this.base - maxval;
         return;
      }
   }

   public char peekNext() {
      return (char)(this.base + this.maxlength - this.le);
   }

   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   void wrap(RunContainer p) {
      this.parent = p;
      this.pos = this.parent.nbrruns - 1;
      this.le = 0;
      if (this.pos >= 0) {
         this.maxlength = this.parent.getLength(this.pos);
         this.base = this.parent.getValue(this.pos);
      }

   }
}
