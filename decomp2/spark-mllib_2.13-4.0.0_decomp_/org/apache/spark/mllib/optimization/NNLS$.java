package org.apache.spark.mllib.optimization;

import scala.math.package.;

public final class NNLS$ {
   public static final NNLS$ MODULE$ = new NNLS$();

   public NNLS.Workspace createWorkspace(final int n) {
      return new NNLS.Workspace(n);
   }

   public double[] solve(final double[] ata, final double[] atb, final NNLS.Workspace ws) {
      ws.wipe();
      int n = atb.length;
      double[] scratch = ws.scratch();
      double[] grad = ws.grad();
      double[] x = ws.x();
      double[] dir = ws.dir();
      double[] lastDir = ws.lastDir();
      double[] res = ws.res();
      int iterMax = .MODULE$.max(400, 20 * n);
      double lastNorm = (double)0.0F;
      int iterno = 0;
      int lastWall = 0;

      double ngrad;
      for(int i = 0; iterno < iterMax; lastNorm = ngrad) {
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dgemv("N", n, n, (double)1.0F, ata, n, x, 1, (double)0.0F, res, 1);
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(n, (double)-1.0F, atb, 1, res, 1);
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dcopy(n, res, 1, grad, 1);

         for(int var30 = 0; var30 < n; ++var30) {
            if (grad[var30] > (double)0.0F && x[var30] == (double)0.0F) {
               grad[var30] = (double)0.0F;
            }
         }

         ngrad = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n, grad, 1, grad, 1);
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dcopy(n, grad, 1, dir, 1);
         double step = steplen$1(grad, res, n, ata, scratch);
         double ndir = (double)0.0F;
         double nx = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n, x, 1, x, 1);
         if (iterno > lastWall + 1) {
            double alpha = ngrad / lastNorm;
            org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().daxpy(n, alpha, lastDir, 1, dir, 1);
            double dstep = steplen$1(dir, res, n, ata, scratch);
            ndir = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n, dir, 1, dir, 1);
            if (stop$1(dstep, ndir, nx)) {
               org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dcopy(n, grad, 1, dir, 1);
               ndir = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n, dir, 1, dir, 1);
            } else {
               step = dstep;
            }
         } else {
            ndir = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n, dir, 1, dir, 1);
         }

         if (stop$1(step, ndir, nx)) {
            return (double[])(([D)x).clone();
         }

         for(int var31 = 0; var31 < n; ++var31) {
            if (step * dir[var31] > x[var31]) {
               step = x[var31] / dir[var31];
            }
         }

         for(int var32 = 0; var32 < n; ++var32) {
            if (step * dir[var32] > x[var32] * 0.99999999999999) {
               x[var32] = (double)0.0F;
               lastWall = iterno;
            } else {
               x[var32] -= step * dir[var32];
            }
         }

         ++iterno;
         org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dcopy(n, dir, 1, lastDir, 1);
      }

      return (double[])(([D)x).clone();
   }

   private static final double steplen$1(final double[] dir, final double[] res, final int n$1, final double[] ata$1, final double[] scratch$1) {
      double top = org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n$1, dir, 1, res, 1);
      org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().dgemv("N", n$1, n$1, (double)1.0F, ata$1, n$1, dir, 1, (double)0.0F, scratch$1, 1);
      return top / (org.apache.spark.ml.linalg.BLAS..MODULE$.nativeBLAS().ddot(n$1, scratch$1, 1, dir, 1) + 1.0E-20);
   }

   private static final boolean stop$1(final double step, final double ndir, final double nx) {
      return Double.isNaN(step) || step < 1.0E-7 || step > 1.0E40 || ndir < 1.0E-12 * nx || ndir < 1.0E-32;
   }

   private NNLS$() {
   }
}
