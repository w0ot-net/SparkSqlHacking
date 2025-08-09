package org.apache.commons.math3.geometry;

import java.io.Serializable;

public interface Point extends Serializable {
   Space getSpace();

   boolean isNaN();

   double distance(Point var1);
}
