package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

public abstract class Clusterer {
   private DistanceMeasure measure;

   protected Clusterer(DistanceMeasure measure) {
      this.measure = measure;
   }

   public abstract List cluster(Collection var1) throws MathIllegalArgumentException, ConvergenceException;

   public DistanceMeasure getDistanceMeasure() {
      return this.measure;
   }

   protected double distance(Clusterable p1, Clusterable p2) {
      return this.measure.compute(p1.getPoint(), p2.getPoint());
   }
}
