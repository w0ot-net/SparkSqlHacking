package org.apache.arrow.vector.util;

import java.util.Iterator;
import org.apache.arrow.memory.util.ArrowBufPointer;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.memory.util.hash.SimpleHasher;
import org.apache.arrow.vector.ElementAddressableVector;

public class ElementAddressableVectorIterator implements Iterator {
   private final ElementAddressableVector vector;
   private int index;
   private final ArrowBufPointer reusablePointer;

   public ElementAddressableVectorIterator(ElementAddressableVector vector) {
      this(vector, SimpleHasher.INSTANCE);
   }

   public ElementAddressableVectorIterator(ElementAddressableVector vector, ArrowBufHasher hasher) {
      this.index = 0;
      this.vector = vector;
      this.reusablePointer = new ArrowBufPointer(hasher);
   }

   public boolean hasNext() {
      return this.index < this.vector.getValueCount();
   }

   public ArrowBufPointer next() {
      this.vector.getDataPointer(this.index, this.reusablePointer);
      ++this.index;
      return this.reusablePointer;
   }

   public void next(ArrowBufPointer outPointer) {
      this.vector.getDataPointer(this.index, outPointer);
      ++this.index;
   }
}
