package org.roaringbitmap.buffer;

import org.roaringbitmap.BitmapDataProvider;
import org.roaringbitmap.BitmapDataProviderSupplier;

public class MutableRoaringBitmapSupplier implements BitmapDataProviderSupplier {
   public BitmapDataProvider newEmpty() {
      return new MutableRoaringBitmap();
   }
}
