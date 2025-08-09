package org.roaringbitmap;

final class ArrayContainerCharIterator implements PeekableCharRankIterator {
   int pos;
   private ArrayContainer parent;

   ArrayContainerCharIterator() {
   }

   ArrayContainerCharIterator(ArrayContainer p) {
      this.wrap(p);
   }

   public void advanceIfNeeded(char minval) {
      this.pos = Util.advanceUntil(this.parent.content, this.pos - 1, this.parent.cardinality, minval);
   }

   public int peekNextRank() {
      return this.pos + 1;
   }

   public PeekableCharRankIterator clone() {
      try {
         return (PeekableCharRankIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.pos < this.parent.cardinality;
   }

   public char next() {
      return this.parent.content[this.pos++];
   }

   public int nextAsInt() {
      return this.parent.content[this.pos++];
   }

   public char peekNext() {
      return this.parent.content[this.pos];
   }

   public void remove() {
      this.parent.removeAtIndex(this.pos - 1);
      --this.pos;
   }

   void wrap(ArrayContainer p) {
      this.parent = p;
      this.pos = 0;
   }
}
