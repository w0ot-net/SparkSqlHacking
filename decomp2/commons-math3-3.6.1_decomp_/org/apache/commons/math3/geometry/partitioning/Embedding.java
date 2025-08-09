package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Point;

public interface Embedding {
   Point toSubSpace(Point var1);

   Point toSpace(Point var1);
}
