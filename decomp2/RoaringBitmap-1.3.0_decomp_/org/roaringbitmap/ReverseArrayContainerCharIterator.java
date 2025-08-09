package org.roaringbitmap;

final class ReverseArrayContainerCharIterator implements PeekableCharIterator {
   int pos;
   private ArrayContainer parent;

   ReverseArrayContainerCharIterator() {
   }

   ReverseArrayContainerCharIterator(ArrayContainer p) {
      this.wrap(p);
   }

   public void advanceIfNeeded(char maxval) {
      this.pos = Util.reverseUntil(this.parent.content, this.pos + 1, this.parent.cardinality, maxval);
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
      return this.parent.content[this.pos--];
   }

   public int nextAsInt() {
      return this.parent.content[this.pos--];
   }

   public char peekNext() {
      return this.parent.content[this.pos];
   }

   public void remove() {
      this.parent.removeAtIndex(this.pos + 1);
      ++this.pos;
   }

   void wrap(ArrayContainer p) {
      this.parent = p;
      this.pos = this.parent.cardinality - 1;
   }
}
