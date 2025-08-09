package org.apache.commons.math3.optimization;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

/** @deprecated */
@Deprecated
public class PointVectorValuePair extends Pair implements Serializable {
   private static final long serialVersionUID = 20120513L;

   public PointVectorValuePair(double[] point, double[] value) {
      this(point, value, true);
   }

   public PointVectorValuePair(double[] point, double[] value, boolean copyArray) {
      super(copyArray ? (point == null ? null : (double[])(([D)point).clone()) : point, copyArray ? (value == null ? null : (double[])(([D)value).clone()) : value);
   }

   public double[] getPoint() {
      double[] p = (double[])this.getKey();
      return p == null ? null : (double[])(([D)p).clone();
   }

   public double[] getPointRef() {
      return (double[])this.getKey();
   }

   public double[] getValue() {
      double[] v = (double[])super.getValue();
      return v == null ? null : (double[])(([D)v).clone();
   }

   public double[] getValueRef() {
      return (double[])super.getValue();
   }

   private Object writeReplace() {
      return new DataTransferObject((double[])this.getKey(), this.getValue());
   }

   private static class DataTransferObject implements Serializable {
      private static final long serialVersionUID = 20120513L;
      private final double[] point;
      private final double[] value;

      DataTransferObject(double[] point, double[] value) {
         this.point = (double[])(([D)point).clone();
         this.value = (double[])(([D)value).clone();
      }

      private Object readResolve() {
         return new PointVectorValuePair(this.point, this.value, false);
      }
   }
}
