package org.apache.spark.ml.ann;

import breeze.linalg.DenseMatrix;
import scala.Function1;
import scala.Function2;

public final class ApplyInPlace$ {
   public static final ApplyInPlace$ MODULE$ = new ApplyInPlace$();

   public void apply(final DenseMatrix x, final DenseMatrix y, final Function1 func) {
      for(int i = 0; i < x.rows(); ++i) {
         for(int j = 0; j < x.cols(); ++j) {
            y.update$mcD$sp(i, j, func.apply$mcDD$sp(x.apply$mcD$sp(i, j)));
         }
      }

   }

   public void apply(final DenseMatrix x1, final DenseMatrix x2, final DenseMatrix y, final Function2 func) {
      for(int i = 0; i < x1.rows(); ++i) {
         for(int j = 0; j < x1.cols(); ++j) {
            y.update$mcD$sp(i, j, func.apply$mcDDD$sp(x1.apply$mcD$sp(i, j), x2.apply$mcD$sp(i, j)));
         }
      }

   }

   private ApplyInPlace$() {
   }
}
