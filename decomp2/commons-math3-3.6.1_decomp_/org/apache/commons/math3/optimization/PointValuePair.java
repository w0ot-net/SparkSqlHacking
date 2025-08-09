package org.apache.commons.math3.optimization;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

/** @deprecated */
@Deprecated
public class PointValuePair extends Pair implements Serializable {
   private static final long serialVersionUID = 20120513L;

   public PointValuePair(double[] point, double value) {
      this(point, value, true);
   }

   public PointValuePair(double[] point, double value, boolean copyArray) {
      super(copyArray ? (point == null ? null : (double[])(([D)point).clone()) : point, value);
   }

   public double[] getPoint() {
      double[] p = (double[])this.getKey();
      return p == null ? null : (double[])(([D)p).clone();
   }

   public double[] getPointRef() {
      return (double[])this.getKey();
   }

   private Object writeReplace() {
      return new DataTransferObject((double[])this.getKey(), (Double)this.getValue());
   }

   private static class DataTransferObject implements Serializable {
      private static final long serialVersionUID = 20120513L;
      private final double[] point;
      private final double value;

      DataTransferObject(double[] point, double value) {
         this.point = (double[])(([D)point).clone();
         this.value = value;
      }

      private Object readResolve() {
         return new PointValuePair(this.point, this.value, false);
      }
   }
}
