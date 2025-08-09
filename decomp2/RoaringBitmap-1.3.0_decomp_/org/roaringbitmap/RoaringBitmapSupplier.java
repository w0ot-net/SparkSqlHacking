package org.roaringbitmap;

public class RoaringBitmapSupplier implements BitmapDataProviderSupplier {
   public BitmapDataProvider newEmpty() {
      return new RoaringBitmap();
   }
}
