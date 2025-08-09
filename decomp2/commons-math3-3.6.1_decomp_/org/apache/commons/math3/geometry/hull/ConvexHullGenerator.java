package org.apache.commons.math3.geometry.hull;

import java.util.Collection;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;

public interface ConvexHullGenerator {
   ConvexHull generate(Collection var1) throws NullArgumentException, ConvergenceException;
}
