package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;

public interface Transform {
   Point apply(Point var1);

   Hyperplane apply(Hyperplane var1);

   SubHyperplane apply(SubHyperplane var1, Hyperplane var2, Hyperplane var3);
}
