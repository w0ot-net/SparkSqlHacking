package org.roaringbitmap.insights;

import java.util.Collection;
import org.roaringbitmap.ContainerPointer;
import org.roaringbitmap.RoaringBitmap;

public class BitmapAnalyser {
   public static BitmapStatistics analyse(RoaringBitmap r) {
      int acCount = 0;
      int acCardinalitySum = 0;
      int bcCount = 0;
      int rcCount = 0;

      for(ContainerPointer cp = r.getContainerPointer(); cp.getContainer() != null; cp.advance()) {
         if (cp.isBitmapContainer()) {
            ++bcCount;
         } else if (cp.isRunContainer()) {
            ++rcCount;
         } else {
            ++acCount;
            acCardinalitySum += cp.getCardinality();
         }
      }

      BitmapStatistics.ArrayContainersStats acStats = new BitmapStatistics.ArrayContainersStats((long)acCount, (long)acCardinalitySum);
      return new BitmapStatistics(acStats, (long)bcCount, (long)rcCount);
   }

   public static BitmapStatistics analyse(Collection bitmaps) {
      return (BitmapStatistics)bitmaps.stream().reduce(BitmapStatistics.empty, (acc, r) -> acc.merge(analyse(r)), BitmapStatistics::merge);
   }
}
