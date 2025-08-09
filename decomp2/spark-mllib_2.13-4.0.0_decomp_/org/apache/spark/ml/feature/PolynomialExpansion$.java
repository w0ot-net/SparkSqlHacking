package org.apache.spark.ml.feature;

import java.io.Serializable;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.util.DefaultParamsReadable;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Predef.;
import scala.collection.mutable.ArrayBuilder;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class PolynomialExpansion$ implements DefaultParamsReadable, Serializable {
   public static final PolynomialExpansion$ MODULE$ = new PolynomialExpansion$();

   static {
      MLReadable.$init$(MODULE$);
      DefaultParamsReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return DefaultParamsReadable.read$(this);
   }

   private int getPolySize(final int numFeatures, final int degree) {
      long n = CombinatoricsUtils.binomialCoefficient(numFeatures + degree, degree);
      .MODULE$.require(n <= 2147483647L);
      return (int)n;
   }

   private int expandDense(final double[] values, final int lastIdx, final int degree, final double multiplier, final double[] polyValues, final int curPolyIdx) {
      if (multiplier != (double)0.0F) {
         if (degree != 0 && lastIdx >= 0) {
            double v = values[lastIdx];
            int lastIdx1 = lastIdx - 1;
            double alpha = multiplier;
            int i = 0;

            for(int curStart = curPolyIdx; i <= degree && alpha != (double)0.0F; alpha *= v) {
               curStart = this.expandDense(values, lastIdx1, degree - i, alpha, polyValues, curStart);
               ++i;
            }
         } else if (curPolyIdx >= 0) {
            polyValues[curPolyIdx] = multiplier;
         }
      }

      return curPolyIdx + this.getPolySize(lastIdx + 1, degree);
   }

   private int expandSparse(final int[] indices, final double[] values, final int lastIdx, final int lastFeatureIdx, final int degree, final double multiplier, final ArrayBuilder polyIndices, final ArrayBuilder polyValues, final int curPolyIdx) {
      if (multiplier == (double)0.0F) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (degree != 0 && lastIdx >= 0) {
         double v = values[lastIdx];
         int lastIdx1 = lastIdx - 1;
         int lastFeatureIdx1 = indices[lastIdx] - 1;
         double alpha = multiplier;
         int curStart = curPolyIdx;

         for(int i = 0; i <= degree && alpha != (double)0.0F; alpha *= v) {
            curStart = this.expandSparse(indices, values, lastIdx1, lastFeatureIdx1, degree - i, alpha, polyIndices, polyValues, curStart);
            ++i;
         }

         BoxedUnit var20 = BoxedUnit.UNIT;
      } else if (curPolyIdx >= 0) {
         polyIndices.$plus$eq(BoxesRunTime.boxToInteger(curPolyIdx));
         polyValues.$plus$eq(BoxesRunTime.boxToDouble(multiplier));
      } else {
         BoxedUnit var19 = BoxedUnit.UNIT;
      }

      return curPolyIdx + this.getPolySize(lastFeatureIdx + 1, degree);
   }

   private DenseVector expand(final DenseVector dv, final int degree) {
      int n = dv.size();
      int polySize = this.getPolySize(n, degree);
      double[] polyValues = new double[polySize - 1];
      this.expandDense(dv.values(), n - 1, degree, (double)1.0F, polyValues, -1);
      return new DenseVector(polyValues);
   }

   private SparseVector expand(final SparseVector sv, final int degree) {
      int polySize = this.getPolySize(sv.size(), degree);
      int nnz = sv.values().length;
      int nnzPolySize = this.getPolySize(nnz, degree);
      ArrayBuilder polyIndices = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      polyIndices.sizeHint(nnzPolySize - 1);
      ArrayBuilder polyValues = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      polyValues.sizeHint(nnzPolySize - 1);
      this.expandSparse(sv.indices(), sv.values(), nnz - 1, sv.size() - 1, degree, (double)1.0F, polyIndices, polyValues, -1);
      return new SparseVector(polySize - 1, (int[])polyIndices.result(), (double[])polyValues.result());
   }

   public Vector expand(final Vector v, final int degree) {
      if (v instanceof DenseVector var5) {
         return this.expand(var5, degree);
      } else if (v instanceof SparseVector var6) {
         return this.expand(var6, degree);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public PolynomialExpansion load(final String path) {
      return (PolynomialExpansion)MLReadable.load$(this, path);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PolynomialExpansion$.class);
   }

   private PolynomialExpansion$() {
   }
}
