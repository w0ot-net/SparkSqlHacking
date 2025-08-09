package org.apache.commons.math3.ml.clustering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cluster implements Serializable {
   private static final long serialVersionUID = -3442297081515880464L;
   private final List points = new ArrayList();

   public void addPoint(Clusterable point) {
      this.points.add(point);
   }

   public List getPoints() {
      return this.points;
   }
}
