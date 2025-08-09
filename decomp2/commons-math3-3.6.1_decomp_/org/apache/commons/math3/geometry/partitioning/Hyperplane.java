package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;

public interface Hyperplane {
   Hyperplane copySelf();

   double getOffset(Point var1);

   Point project(Point var1);

   double getTolerance();

   boolean sameOrientationAs(Hyperplane var1);

   SubHyperplane wholeHyperplane();

   Region wholeSpace();
}
