package org.roaringbitmap.art;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LeafNodeIterator implements Iterator {
   private Shuttle shuttle;
   private boolean hasCurrent;
   private LeafNode current;
   private boolean calledHasNext;
   private boolean isEmpty;

   public LeafNodeIterator(Art art, Containers containers) {
      this(art, false, containers);
   }

   public LeafNodeIterator(Art art, boolean reverse, Containers containers) {
      this.isEmpty = art.isEmpty();
      if (!this.isEmpty) {
         if (!reverse) {
            this.shuttle = new ForwardShuttle(art, containers);
         } else {
            this.shuttle = new BackwardShuttle(art, containers);
         }

         this.shuttle.initShuttle();
         this.calledHasNext = false;
      }
   }

   public LeafNodeIterator(Art art, boolean reverse, Containers containers, long from) {
      this.isEmpty = art.isEmpty();
      if (!this.isEmpty) {
         if (!reverse) {
            this.shuttle = new ForwardShuttle(art, containers);
         } else {
            this.shuttle = new BackwardShuttle(art, containers);
         }

         this.shuttle.initShuttleFrom(from);
         this.calledHasNext = false;
      }
   }

   private boolean advance() {
      boolean hasLeafNode = this.shuttle.moveToNextLeaf();
      if (hasLeafNode) {
         this.hasCurrent = true;
         this.current = this.shuttle.getCurrentLeafNode();
      } else {
         this.hasCurrent = false;
         this.current = null;
      }

      return hasLeafNode;
   }

   public boolean hasNext() {
      if (this.isEmpty) {
         return false;
      } else if (!this.calledHasNext) {
         this.calledHasNext = true;
         return this.advance();
      } else {
         return this.hasCurrent;
      }
   }

   public LeafNode next() {
      if (!this.calledHasNext) {
         this.hasNext();
      }

      if (!this.hasCurrent) {
         throw new NoSuchElementException();
      } else {
         this.calledHasNext = false;
         return this.current;
      }
   }

   public void remove() {
      this.shuttle.remove();
   }

   public void seek(long boundval) {
      this.shuttle.initShuttleFrom(boundval);
      this.calledHasNext = false;
   }

   public LeafNode peekNext() {
      if (!this.calledHasNext) {
         this.hasNext();
      }

      if (!this.hasCurrent) {
         throw new NoSuchElementException();
      } else {
         return this.current;
      }
   }
}
