package org.apache.commons.math3.geometry.hull;

import java.io.Serializable;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.Region;

public interface ConvexHull extends Serializable {
   Point[] getVertices();

   Region createRegion() throws InsufficientDataException;
}
