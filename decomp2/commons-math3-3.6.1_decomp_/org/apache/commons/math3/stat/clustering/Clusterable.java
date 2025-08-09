package org.apache.commons.math3.stat.clustering;

import java.util.Collection;

/** @deprecated */
@Deprecated
public interface Clusterable {
   double distanceFrom(Object var1);

   Object centroidOf(Collection var1);
}
