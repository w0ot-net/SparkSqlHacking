package org.apache.spark.mllib.linalg;

import org.apache.spark.ml.optim.SingularMatrixException;
import org.netlib.util.intW;

public final class CholeskyDecomposition$ {
   public static final CholeskyDecomposition$ MODULE$ = new CholeskyDecomposition$();

   public double[] solve(final double[] A, final double[] bx) {
      int k = bx.length;
      intW info = new intW(0);
      LAPACK$.MODULE$.nativeLAPACK().dppsv("U", k, 1, A, bx, k, info);
      this.checkReturnValue(info, "dppsv");
      return bx;
   }

   public double[] inverse(final double[] UAi, final int k) {
      intW info = new intW(0);
      LAPACK$.MODULE$.nativeLAPACK().dpptri("U", k, UAi, info);
      this.checkReturnValue(info, "dpptri");
      return UAi;
   }

   private void checkReturnValue(final intW info, final String method) {
      int var3 = info.val;
      switch (var3) {
         default:
            if (var3 < 0) {
               throw new IllegalStateException("LAPACK." + method + " returned " + var3 + "; arg " + -var3 + " is illegal");
            } else if (var3 > 0) {
               throw new SingularMatrixException("LAPACK." + method + " returned " + var3 + " because A is not positive definite. Is A derived from a singular matrix (e.g. collinear column values)?");
            }
      }
   }

   private CholeskyDecomposition$() {
   }
}
