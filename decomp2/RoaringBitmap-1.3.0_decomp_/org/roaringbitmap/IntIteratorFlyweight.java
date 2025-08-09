package org.roaringbitmap;

public class IntIteratorFlyweight implements PeekableIntIterator {
   private int hs;
   private PeekableCharIterator iter;
   private ArrayContainerCharIterator arrIter = new ArrayContainerCharIterator();
   private BitmapContainerCharIterator bitmapIter = new BitmapContainerCharIterator();
   private RunContainerCharIterator runIter = new RunContainerCharIterator();
   private int pos;
   private RoaringBitmap roaringBitmap = null;

   public IntIteratorFlyweight() {
   }

   public IntIteratorFlyweight(RoaringBitmap r) {
      this.wrap(r);
   }

   public PeekableIntIterator clone() {
      try {
         IntIteratorFlyweight x = (IntIteratorFlyweight)super.clone();
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
         this.iter.advanceIfNeeded(Util.lowbits(minval));
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
