package dev.ludovic.netlib.blas;

import org.netlib.blas.Dasum;
import org.netlib.blas.Daxpy;
import org.netlib.blas.Dcopy;
import org.netlib.blas.Ddot;
import org.netlib.blas.Dgbmv;
import org.netlib.blas.Dgemm;
import org.netlib.blas.Dgemv;
import org.netlib.blas.Dger;
import org.netlib.blas.Dnrm2;
import org.netlib.blas.Drot;
import org.netlib.blas.Drotm;
import org.netlib.blas.Drotmg;
import org.netlib.blas.Dsbmv;
import org.netlib.blas.Dscal;
import org.netlib.blas.Dspmv;
import org.netlib.blas.Dspr;
import org.netlib.blas.Dspr2;
import org.netlib.blas.Dswap;
import org.netlib.blas.Dsymm;
import org.netlib.blas.Dsymv;
import org.netlib.blas.Dsyr;
import org.netlib.blas.Dsyr2;
import org.netlib.blas.Dsyr2k;
import org.netlib.blas.Dsyrk;
import org.netlib.blas.Dtbmv;
import org.netlib.blas.Dtbsv;
import org.netlib.blas.Dtpmv;
import org.netlib.blas.Dtpsv;
import org.netlib.blas.Dtrmm;
import org.netlib.blas.Dtrmv;
import org.netlib.blas.Dtrsm;
import org.netlib.blas.Dtrsv;
import org.netlib.blas.Idamax;
import org.netlib.blas.Isamax;
import org.netlib.blas.Sasum;
import org.netlib.blas.Saxpy;
import org.netlib.blas.Scopy;
import org.netlib.blas.Sdot;
import org.netlib.blas.Sdsdot;
import org.netlib.blas.Sgbmv;
import org.netlib.blas.Sgemm;
import org.netlib.blas.Sgemv;
import org.netlib.blas.Sger;
import org.netlib.blas.Snrm2;
import org.netlib.blas.Srot;
import org.netlib.blas.Srotm;
import org.netlib.blas.Srotmg;
import org.netlib.blas.Ssbmv;
import org.netlib.blas.Sscal;
import org.netlib.blas.Sspmv;
import org.netlib.blas.Sspr;
import org.netlib.blas.Sspr2;
import org.netlib.blas.Sswap;
import org.netlib.blas.Ssymm;
import org.netlib.blas.Ssymv;
import org.netlib.blas.Ssyr;
import org.netlib.blas.Ssyr2;
import org.netlib.blas.Ssyr2k;
import org.netlib.blas.Ssyrk;
import org.netlib.blas.Stbmv;
import org.netlib.blas.Stbsv;
import org.netlib.blas.Stpmv;
import org.netlib.blas.Stpsv;
import org.netlib.blas.Strmm;
import org.netlib.blas.Strmv;
import org.netlib.blas.Strsm;
import org.netlib.blas.Strsv;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;

final class F2jBLAS extends AbstractBLAS implements JavaBLAS {
   private static final F2jBLAS instance = new F2jBLAS();

   protected F2jBLAS() {
   }

   public static JavaBLAS getInstance() {
      return instance;
   }

   protected double dasumK(int n, double[] x, int offsetx, int incx) {
      return Dasum.dasum(n, x, offsetx, incx);
   }

   protected float sasumK(int n, float[] x, int offsetx, int incx) {
      return Sasum.sasum(n, x, offsetx, incx);
   }

   protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      Daxpy.daxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
   }

   protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      Saxpy.saxpy(n, alpha, x, offsetx, incx, y, offsety, incy);
   }

   protected void dcopyK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      Dcopy.dcopy(n, x, offsetx, incx, y, offsety, incy);
   }

   protected void scopyK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      Scopy.scopy(n, x, offsetx, incx, y, offsety, incy);
   }

   protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      return Ddot.ddot(n, x, offsetx, incx, y, offsety, incy);
   }

   protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      return Sdot.sdot(n, x, offsetx, incx, y, offsety, incy);
   }

   protected float sdsdotK(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      return Sdsdot.sdsdot(n, sb, x, offsetx, incx, y, offsety, incy);
   }

   protected void dgbmvK(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      Dgbmv.dgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void sgbmvK(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      Sgbmv.sgbmv(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void dgemmK(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      Dgemm.dgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
   }

   protected void sgemmK(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      Sgemm.sgemm(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
   }

   protected void dgemvK(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      Dgemv.dgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void sgemvK(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      Sgemv.sgemv(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
      Dger.dger(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
   }

   protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
      Sger.sger(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
   }

   protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
      return Dnrm2.dnrm2(n, x, offsetx, incx);
   }

   protected float snrm2K(int n, float[] x, int offsetx, int incx) {
      return Snrm2.snrm2(n, x, offsetx, incx);
   }

   protected void drotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
      Drot.drot(n, x, offsetx, incx, y, offsety, incy, c, s);
   }

   protected void srotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
      Srot.srot(n, x, offsetx, incx, y, offsety, incy, c, s);
   }

   protected void drotmK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam) {
      Drotm.drotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
   }

   protected void srotmK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
      Srotm.srotm(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
   }

   protected void drotmgK(doubleW dd1, doubleW dd2, doubleW dx1, double dy1, double[] param, int offsetparam) {
      Drotmg.drotmg(dd1, dd2, dx1, dy1, param, offsetparam);
   }

   protected void srotmgK(floatW sd1, floatW sd2, floatW sx1, float sy1, float[] param, int offsetparam) {
      Srotmg.srotmg(sd1, sd2, sx1, sy1, param, offsetparam);
   }

   protected void dsbmvK(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      Dsbmv.dsbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void ssbmvK(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      Ssbmv.ssbmv(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void dscalK(int n, double alpha, double[] x, int offsetx, int incx) {
      Dscal.dscal(n, alpha, x, offsetx, incx);
   }

   protected void sscalK(int n, float alpha, float[] x, int offsetx, int incx) {
      Sscal.sscal(n, alpha, x, offsetx, incx);
   }

   protected void dspmvK(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      Dspmv.dspmv(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void sspmvK(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      Sspmv.sspmv(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void dsprK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
      Dspr.dspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
   }

   protected void ssprK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
      Sspr.sspr(uplo, n, alpha, x, offsetx, incx, a, offseta);
   }

   protected void dspr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
      Dspr2.dspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
   }

   protected void sspr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
      Sspr2.sspr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
   }

   protected void dswapK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      Dswap.dswap(n, x, offsetx, incx, y, offsety, incy);
   }

   protected void sswapK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      Sswap.sswap(n, x, offsetx, incx, y, offsety, incy);
   }

   protected void dsymmK(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      Dsymm.dsymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
   }

   protected void ssymmK(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      Ssymm.ssymm(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
   }

   protected void dsymvK(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      Dsymv.dsymv(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void ssymvK(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      Ssymv.ssymv(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected void dsyrK(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
      Dsyr.dsyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
   }

   protected void ssyrK(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
      Ssyr.ssyr(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
   }

   protected void dsyr2K(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
      Dsyr2.dsyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
   }

   protected void ssyr2K(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
      Ssyr2.ssyr2(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
   }

   protected void dsyr2kK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      Dsyr2k.dsyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
   }

   protected void ssyr2kK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      Ssyr2k.ssyr2k(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
   }

   protected void dsyrkK(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
      Dsyrk.dsyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
   }

   protected void ssyrkK(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
      Ssyrk.ssyrk(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
   }

   protected void dtbmvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      Dtbmv.dtbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
   }

   protected void stbmvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      Stbmv.stbmv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
   }

   protected void dtbsvK(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      Dtbsv.dtbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
   }

   protected void stbsvK(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      Stbsv.stbsv(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
   }

   protected void dtpmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
      Dtpmv.dtpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
   }

   protected void stpmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
      Stpmv.stpmv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
   }

   protected void dtpsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
      Dtpsv.dtpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
   }

   protected void stpsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
      Stpsv.stpsv(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
   }

   protected void dtrmmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
      Dtrmm.dtrmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
   }

   protected void strmmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
      Strmm.strmm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
   }

   protected void dtrmvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      Dtrmv.dtrmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
   }

   protected void strmvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      Strmv.strmv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
   }

   protected void dtrsmK(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
      Dtrsm.dtrsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
   }

   protected void strsmK(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
      Strsm.strsm(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
   }

   protected void dtrsvK(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      Dtrsv.dtrsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
   }

   protected void strsvK(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      Strsv.strsv(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
   }

   protected int idamaxK(int n, double[] x, int offsetx, int incx) {
      return Idamax.idamax(n, x, offsetx, incx);
   }

   protected int isamaxK(int n, float[] x, int offsetx, int incx) {
      return Isamax.isamax(n, x, offsetx, incx);
   }
}
