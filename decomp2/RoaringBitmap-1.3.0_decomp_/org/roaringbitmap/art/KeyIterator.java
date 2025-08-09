package org.roaringbitmap.art;

import java.util.Iterator;

public class KeyIterator implements Iterator {
   private LeafNode current;
   private LeafNodeIterator leafNodeIterator;

   public KeyIterator(Art art, Containers containers) {
      this.leafNodeIterator = new LeafNodeIterator(art, containers);
      this.current = null;
   }

   public boolean hasNext() {
      boolean hasNext = this.leafNodeIterator.hasNext();
      if (hasNext) {
         this.current = this.leafNodeIterator.next();
      }

      return hasNext;
   }

   public byte[] next() {
      return this.current.getKeyBytes();
   }

   public byte[] peekNext() {
      return this.leafNodeIterator.peekNext().getKeyBytes();
   }

   public long nextKey() {
      return this.current.getKey();
   }

   public long currentContainerIdx() {
      return this.current.getContainerIdx();
   }

   public void remove() {
      this.leafNodeIterator.remove();
   }
}
