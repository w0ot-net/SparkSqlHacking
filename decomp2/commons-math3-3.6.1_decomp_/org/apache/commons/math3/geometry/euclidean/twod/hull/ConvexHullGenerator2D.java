package org.apache.commons.math3.geometry.euclidean.twod.hull;

import java.util.Collection;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.geometry.hull.ConvexHullGenerator;

public interface ConvexHullGenerator2D extends ConvexHullGenerator {
   ConvexHull2D generate(Collection var1) throws NullArgumentException, ConvergenceException;
}
