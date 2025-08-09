package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;

public interface Region {
   Region buildNew(BSPTree var1);

   Region copySelf();

   boolean isEmpty();

   boolean isEmpty(BSPTree var1);

   boolean isFull();

   boolean isFull(BSPTree var1);

   boolean contains(Region var1);

   Location checkPoint(Point var1);

   BoundaryProjection projectToBoundary(Point var1);

   BSPTree getTree(boolean var1);

   double getBoundarySize();

   double getSize();

   Point getBarycenter();

   /** @deprecated */
   @Deprecated
   Side side(Hyperplane var1);

   SubHyperplane intersection(SubHyperplane var1);

   public static enum Location {
      INSIDE,
      OUTSIDE,
      BOUNDARY;
   }
}
