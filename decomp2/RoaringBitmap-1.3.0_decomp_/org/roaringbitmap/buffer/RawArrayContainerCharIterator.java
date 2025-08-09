package org.roaringbitmap.buffer;

import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.Util;

final class RawArrayContainerCharIterator implements PeekableCharIterator {
   int pos;
   private MappeableArrayContainer parent;
   char[] content;

   RawArrayContainerCharIterator(MappeableArrayContainer p) {
      this.parent = p;
      if (!p.isArrayBacked()) {
         throw new RuntimeException("internal bug");
      } else {
         this.content = p.content.array();
         this.pos = 0;
      }
   }

   public void advanceIfNeeded(char minval) {
      this.pos = Util.advanceUntil(this.content, this.pos - 1, this.parent.cardinality, minval);
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
      return this.content[this.pos++];
   }

   public int nextAsInt() {
      return this.content[this.pos++];
   }

   public char peekNext() {
      return this.content[this.pos];
   }

   public void remove() {
      this.parent.removeAtIndex(this.pos - 1);
      --this.pos;
   }
}
