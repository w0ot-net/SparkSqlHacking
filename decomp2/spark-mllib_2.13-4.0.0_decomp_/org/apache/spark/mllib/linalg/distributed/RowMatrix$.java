package org.apache.spark.mllib.linalg.distributed;

import breeze.linalg.DenseMatrix;
import java.io.Serializable;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import scala.reflect.ClassTag.;
import scala.runtime.ModuleSerializationProxy;

public final class RowMatrix$ implements Serializable {
   public static final RowMatrix$ MODULE$ = new RowMatrix$();

   public Matrix org$apache$spark$mllib$linalg$distributed$RowMatrix$$triuToFull(final int n, final double[] U) {
      DenseMatrix G = new DenseMatrix.mcD.sp(n, n, .MODULE$.Double());
      int row = 0;
      int col = 0;
      int idx = 0;

      for(double value = (double)0.0F; col < n; ++col) {
         for(int var9 = 0; var9 < col; ++var9) {
            value = U[idx];
            G.update$mcD$sp(var9, col, value);
            G.update$mcD$sp(col, var9, value);
            ++idx;
         }

         G.update$mcD$sp(col, col, U[idx]);
         ++idx;
      }

      return Matrices$.MODULE$.dense(n, n, G.data$mcD$sp());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RowMatrix$.class);
   }

   private RowMatrix$() {
   }
}
