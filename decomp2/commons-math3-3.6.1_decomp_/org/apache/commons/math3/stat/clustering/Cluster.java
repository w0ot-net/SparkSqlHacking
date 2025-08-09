package org.apache.commons.math3.stat.clustering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** @deprecated */
@Deprecated
public class Cluster implements Serializable {
   private static final long serialVersionUID = -3442297081515880464L;
   private final List points;
   private final Clusterable center;

   public Cluster(Clusterable center) {
      this.center = center;
      this.points = new ArrayList();
   }

   public void addPoint(Clusterable point) {
      this.points.add(point);
   }

   public List getPoints() {
      return this.points;
   }

   public Clusterable getCenter() {
      return this.center;
   }
}
