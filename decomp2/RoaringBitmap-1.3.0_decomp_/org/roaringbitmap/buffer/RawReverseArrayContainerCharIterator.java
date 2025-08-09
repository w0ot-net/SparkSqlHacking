package org.roaringbitmap.buffer;

import org.roaringbitmap.CharIterator;

final class RawReverseArrayContainerCharIterator implements CharIterator {
   int pos;
   private MappeableArrayContainer parent;
   char[] content;

   RawReverseArrayContainerCharIterator(MappeableArrayContainer p) {
      this.parent = p;
      if (!p.isArrayBacked()) {
         throw new RuntimeException("internal bug");
      } else {
         this.content = p.content.array();
         this.pos = this.parent.cardinality - 1;
      }
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
      return this.content[this.pos--];
   }

   public int nextAsInt() {
      return this.content[this.pos--];
   }

   public void remove() {
      this.parent.removeAtIndex(this.pos + 1);
      ++this.pos;
   }
}
