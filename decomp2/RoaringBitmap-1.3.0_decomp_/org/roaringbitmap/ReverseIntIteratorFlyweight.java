package org.roaringbitmap;

public class ReverseIntIteratorFlyweight implements IntIterator {
   private int hs;
   private CharIterator iter;
   private ReverseArrayContainerCharIterator arrIter = new ReverseArrayContainerCharIterator();
   private ReverseBitmapContainerCharIterator bitmapIter = new ReverseBitmapContainerCharIterator();
   private ReverseRunContainerCharIterator runIter = new ReverseRunContainerCharIterator();
   private short pos;
   private RoaringBitmap roaringBitmap = null;

   public ReverseIntIteratorFlyweight() {
   }

   public ReverseIntIteratorFlyweight(RoaringBitmap r) {
      this.wrap(r);
   }

   public IntIterator clone() {
      try {
         ReverseIntIteratorFlyweight x = (ReverseIntIteratorFlyweight)super.clone();
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
         Container container = this.roaringBitmap.highLowContainer.getContainerAtIndex(this.pos);
         if (container instanceof BitmapContainer) {
            this.bitmapIter.wrap(((BitmapContainer)container).bitmap);
            this.iter = this.bitmapIter;
         } else if (container instanceof ArrayContainer) {
            this.arrIter.wrap((ArrayContainer)container);
            this.iter = this.arrIter;
         } else {
            this.runIter.wrap((RunContainer)container);
            this.iter = this.runIter;
         }

         this.hs = this.roaringBitmap.highLowContainer.getKeyAtIndex(this.pos) << 16;
      }

   }

   public void wrap(RoaringBitmap r) {
      this.roaringBitmap = r;
      this.hs = 0;
      this.pos = (short)(this.roaringBitmap.highLowContainer.size() - 1);
      this.nextContainer();
   }
}
