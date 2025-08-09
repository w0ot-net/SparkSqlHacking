package org.roaringbitmap.buffer;

import org.roaringbitmap.PeekableCharIterator;

final class MappeableArrayContainerCharIterator implements PeekableCharIterator {
   int pos;
   private MappeableArrayContainer parent;

   MappeableArrayContainerCharIterator() {
   }

   MappeableArrayContainerCharIterator(MappeableArrayContainer p) {
      this.wrap(p);
   }

   public void advanceIfNeeded(char minval) {
      this.pos = BufferUtil.advanceUntil(this.parent.content, this.pos - 1, this.parent.cardinality, minval);
   }

   public PeekableCharIterator clone() {
      try {
         return (PeekableCharIterator)super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.pos < this.parent.cardinality;
   }

   public char next() {
      return this.parent.content.get(this.pos++);
   }

   public int nextAsInt() {
      return this.parent.content.get(this.pos++);
   }

   public char peekNext() {
      return this.parent.content.get(this.pos);
   }

   public void remove() {
      this.parent.removeAtIndex(this.pos - 1);
      --this.pos;
   }

   void wrap(MappeableArrayContainer p) {
      this.parent = p;
      this.pos = 0;
   }
}
