package org.apache.spark.mllib.feature;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class IDFModel$ implements Serializable {
   public static final IDFModel$ MODULE$ = new IDFModel$();

   public Vector transform(final Vector idf, final Vector v) {
      if (v instanceof SparseVector var6) {
         Option var7 = SparseVector$.MODULE$.unapply(var6);
         if (!var7.isEmpty()) {
            int size = BoxesRunTime.unboxToInt(((Tuple3)var7.get())._1());
            int[] indices = (int[])((Tuple3)var7.get())._2();
            double[] values = (double[])((Tuple3)var7.get())._3();
            Tuple2 var12 = this.transformSparse(idf, indices, values);
            if (var12 != null) {
               int[] newIndices = (int[])var12._1();
               double[] newValues = (double[])var12._2();
               Tuple2 var11 = new Tuple2(newIndices, newValues);
               int[] newIndices = (int[])var11._1();
               double[] newValues = (double[])var11._2();
               return Vectors$.MODULE$.sparse(size, newIndices, newValues);
            }

            throw new MatchError(var12);
         }
      }

      if (v instanceof DenseVector var17) {
         Option var18 = DenseVector$.MODULE$.unapply(var17);
         if (!var18.isEmpty()) {
            double[] values = (double[])var18.get();
            double[] newValues = this.transformDense(idf, values);
            return Vectors$.MODULE$.dense(newValues);
         }
      }

      throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + v.getClass() + ".");
   }

   public double[] transformDense(final Vector idf, final double[] values) {
      int n = values.length;
      double[] newValues = new double[n];

      for(int j = 0; j < n; ++j) {
         newValues[j] = values[j] * idf.apply(j);
      }

      return newValues;
   }

   public Tuple2 transformSparse(final Vector idf, final int[] indices, final double[] values) {
      int nnz = indices.length;
      double[] newValues = new double[nnz];

      for(int k = 0; k < nnz; ++k) {
         newValues[k] = values[k] * idf.apply(indices[k]);
      }

      return new Tuple2(indices, newValues);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(IDFModel$.class);
   }

   private IDFModel$() {
   }
}
