package org.roaringbitmap.buffer;

import org.roaringbitmap.PeekableCharIterator;
import org.roaringbitmap.PeekableIntIterator;

public class BufferIntIteratorFlyweight implements PeekableIntIterator {
   private int hs;
   private PeekableCharIterator iter;
   private MappeableArrayContainerCharIterator arrIter = new MappeableArrayContainerCharIterator();
   private MappeableBitmapContainerCharIterator bitmapIter = new MappeableBitmapContainerCharIterator();
   private MappeableRunContainerCharIterator runIter = new MappeableRunContainerCharIterator();
   private int pos;
   private ImmutableRoaringBitmap roaringBitmap = null;

   public BufferIntIteratorFlyweight() {
   }

   public BufferIntIteratorFlyweight(ImmutableRoaringBitmap r) {
      this.wrap(r);
   }

   public PeekableIntIterator clone() {
      try {
         BufferIntIteratorFlyweight x = (BufferIntIteratorFlyweight)super.clone();
         if (this.iter != null) {
            x.iter = this.iter.clone();
         }

         return x;
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.pos < this.roaringBitmap.highLowContainer.size();
   }

   public int next() {
      int x = this.iter.nextAsInt() | this.hs;
      if (!this.iter.hasNext()) {
         ++this.pos;
         this.nextContainer();
      }

      return x;
   }

   private void nextContainer() {
      if (this.pos < this.roaringBitmap.highLowContainer.size()) {
         MappeableContainer container = this.roaringBitmap.highLowContainer.getContainerAtIndex(this.pos);
         if (container instanceof MappeableBitmapContainer) {
            this.bitmapIter.wrap((MappeableBitmapContainer)container);
            this.iter = this.bitmapIter;
         } else if (container instanceof MappeableRunContainer) {
            this.runIter.wrap((MappeableRunContainer)container);
            this.iter = this.runIter;
         } else {
            this.arrIter.wrap((MappeableArrayContainer)container);
            this.iter = this.arrIter;
         }

         this.hs = this.roaringBitmap.highLowContainer.getKeyAtIndex(this.pos) << 16;
      }

   }

   public void wrap(ImmutableRoaringBitmap r) {
      this.hs = 0;
      this.pos = 0;
      this.roaringBitmap = r;
      this.nextContainer();
   }

   public void advanceIfNeeded(int minval) {
      while(this.hasNext() && this.hs >>> 16 < minval >>> 16) {
         ++this.pos;
         this.nextContainer();
      }

      if (this.hasNext() && this.hs >>> 16 == minval >>> 16) {
         this.iter.advanceIfNeeded(BufferUtil.lowbits(minval));
         if (!this.iter.hasNext()) {
            ++this.pos;
            this.nextContainer();
         }
      }

   }

   public int peekNext() {
      return this.iter.peekNext() | this.hs;
   }
}
