package org.roaringbitmap.buffer;

import org.roaringbitmap.CharIterator;

final class ReverseMappeableArrayContainerCharIterator implements CharIterator {
   int pos;
   private MappeableArrayContainer parent;

   ReverseMappeableArrayContainerCharIterator() {
   }

   ReverseMappeableArrayContainerCharIterator(MappeableArrayContainer p) {
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
      return this.parent.content.get(this.pos--);
   }

   public int nextAsInt() {
      return this.parent.content.get(this.pos--);
   }

   public void remove() {
      this.parent.removeAtIndex(this.pos + 1);
      ++this.pos;
   }

   void wrap(MappeableArrayContainer p) {
      this.parent = p;
      this.pos = this.parent.cardinality - 1;
   }
}
