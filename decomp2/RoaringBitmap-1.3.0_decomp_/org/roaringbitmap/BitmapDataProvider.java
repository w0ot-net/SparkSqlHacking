package org.roaringbitmap;

public interface BitmapDataProvider extends ImmutableBitmapDataProvider {
   void add(int var1);

   void add(long var1, long var3);

   void remove(int var1);

   void trim();
}
