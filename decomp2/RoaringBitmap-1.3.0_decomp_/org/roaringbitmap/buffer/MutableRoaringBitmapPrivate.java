package org.roaringbitmap.buffer;

/** @deprecated */
@Deprecated
public class MutableRoaringBitmapPrivate {
   public static void naivelazyor(MutableRoaringBitmap x1, MutableRoaringBitmap x2) {
      x1.naivelazyor(x2);
   }

   public static void repairAfterLazy(MutableRoaringBitmap r) {
      r.repairAfterLazy();
   }
}
