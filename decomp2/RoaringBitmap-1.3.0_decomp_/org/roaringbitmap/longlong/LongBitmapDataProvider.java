package org.roaringbitmap.longlong;

public interface LongBitmapDataProvider extends ImmutableLongBitmapDataProvider {
   void addLong(long var1);

   void removeLong(long var1);

   void trim();
}
