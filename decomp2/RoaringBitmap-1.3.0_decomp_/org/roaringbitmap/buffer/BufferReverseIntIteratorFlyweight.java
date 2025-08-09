package org.roaringbitmap.buffer;

import org.roaringbitmap.CharIterator;
import org.roaringbitmap.IntIterator;

public class BufferReverseIntIteratorFlyweight implements IntIterator {
   private int hs;
   private CharIterator iter;
   private ReverseMappeableArrayContainerCharIterator arrIter = new ReverseMappeableArrayContainerCharIterator();
   private ReverseMappeableBitmapContainerCharIterator bitmapIter = new ReverseMappeableBitmapContainerCharIterator();
   private ReverseMappeableRunContainerCharIterator runIter = new ReverseMappeableRunContainerCharIterator();
   private short pos;
   private ImmutableRoaringBitmap roaringBitmap = null;

   public BufferReverseIntIteratorFlyweight() {
   }

   public BufferReverseIntIteratorFlyweight(ImmutableRoaringBitmap r) {
      this.wrap(r);
   }

   public IntIterator clone() {
      try {
         BufferReverseIntIteratorFlyweight x = (BufferReverseIntIteratorFlyweight)super.clone();
         if (this.iter != null) {
            x.iter = this.iter.clone();
         }

         return x;
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public boolean hasNext() {
      return this.pos >= 0;
   }

   public int next() {
      int x = this.iter.nextAsInt() | this.hs;
      if (!this.iter.hasNext()) {
         --this.pos;
         this.nextContainer();
      }

      return x;
   }

   private void nextContainer() {
      if (this.pos >= 0) {
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
      this.roaringBitmap = r;
      this.hs = 0;
      this.pos = (short)(this.roaringBitmap.highLowContainer.size() - 1);
      this.nextContainer();
   }
}
