package dev.ludovic.netlib.blas;

import java.util.Objects;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;

abstract class AbstractBLAS implements BLAS {
   private static final boolean debug = System.getProperty("dev.ludovic.netlib.blas.debug", "false").equals("true");

   protected int loopAlign(int index, int max, int size) {
      return Math.min(this.loopBound(index + size - 1, size), max);
   }

   protected int loopBound(int index, int size) {
      return index - index % size;
   }

   private void checkArgument(String method, int arg, boolean check) {
      if (!check) {
         throw new IllegalArgumentException(String.format("** On entry to '%s' parameter number %d had an illegal value", method, arg));
      }
   }

   private void checkIndex(int index, int length) {
      if (index < 0 || index >= length) {
         throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", index, length));
      }
   }

   private void requireNonNull(Object obj) {
      Objects.requireNonNull(obj);
   }

   public double dasum(int n, double[] x, int incx) {
      if (debug) {
         System.err.println("dasum");
      }

      return this.dasum(n, x, 0, incx);
   }

   public double dasum(int n, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dasum");
      }

      if (n <= 0) {
         return (double)0.0F;
      } else {
         this.requireNonNull(x);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         return this.dasumK(n, x, offsetx, incx);
      }
   }

   protected abstract double dasumK(int var1, double[] var2, int var3, int var4);

   public float sasum(int n, float[] x, int incx) {
      if (debug) {
         System.err.println("sasum");
      }

      return this.sasum(n, x, 0, incx);
   }

   public float sasum(int n, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("sasum");
      }

      if (n <= 0) {
         return 0.0F;
      } else {
         this.requireNonNull(x);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         return this.sasumK(n, x, offsetx, incx);
      }
   }

   protected abstract float sasumK(int var1, float[] var2, int var3, int var4);

   public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
      if (debug) {
         System.err.println("daxpy");
      }

      this.daxpy(n, alpha, x, 0, incx, y, 0, incy);
   }

   public void daxpy(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("daxpy");
      }

      if (n > 0) {
         if (alpha != (double)0.0F) {
            this.requireNonNull(x);
            this.requireNonNull(y);
            this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
            this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
            this.daxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
         }
      }
   }

   protected abstract void daxpyK(int var1, double var2, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   public void saxpy(int n, float alpha, float[] x, int incx, float[] y, int incy) {
      if (debug) {
         System.err.println("saxpy");
      }

      this.saxpy(n, alpha, x, 0, incx, y, 0, incy);
   }

   public void saxpy(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("saxpy");
      }

      if (n > 0) {
         if (alpha != 0.0F) {
            this.requireNonNull(x);
            this.requireNonNull(y);
            this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
            this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
            this.saxpyK(n, alpha, x, offsetx, incx, y, offsety, incy);
         }
      }
   }

   protected abstract void saxpyK(int var1, float var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8);

   public void dcopy(int n, double[] x, int incx, double[] y, int incy) {
      if (debug) {
         System.err.println("dcopy");
      }

      this.dcopy(n, x, 0, incx, y, 0, incy);
   }

   public void dcopy(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dcopy");
      }

      if (n > 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.dcopyK(n, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract void dcopyK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   public void scopy(int n, float[] x, int incx, float[] y, int incy) {
      if (debug) {
         System.err.println("scopy");
      }

      this.scopy(n, x, 0, incx, y, 0, incy);
   }

   public void scopy(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("scopy");
      }

      if (n > 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.scopyK(n, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract void scopyK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   public double ddot(int n, double[] x, int incx, double[] y, int incy) {
      if (debug) {
         System.err.println("ddot");
      }

      return this.ddot(n, x, 0, incx, y, 0, incy);
   }

   public double ddot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("ddot");
      }

      if (n <= 0) {
         return (double)0.0F;
      } else {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         return this.ddotK(n, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract double ddotK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   public float sdot(int n, float[] x, int incx, float[] y, int incy) {
      if (debug) {
         System.err.println("sdot");
      }

      return this.sdot(n, x, 0, incx, y, 0, incy);
   }

   public float sdot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("sdot");
      }

      if (n <= 0) {
         return 0.0F;
      } else {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         return this.sdotK(n, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract float sdotK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   public float sdsdot(int n, float sb, float[] x, int incx, float[] y, int incy) {
      if (debug) {
         System.err.println("sdsdot");
      }

      return this.sdsdot(n, sb, x, 0, incx, y, 0, incy);
   }

   public float sdsdot(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("sdsdot");
      }

      if (n <= 0) {
         return 0.0F;
      } else {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         return this.sdsdotK(n, sb, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract float sdsdotK(int var1, float var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8);

   public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
      if (debug) {
         System.err.println("dgbmv");
      }

      this.dgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void dgbmv(String trans, int m, int n, int kl, int ku, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dgbmv");
      }

      this.requireNonNull(a);
      this.requireNonNull(x);
      this.requireNonNull(y);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetx + ((this.lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
      this.checkIndex(offsety + ((this.lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
      this.dgbmvK(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected abstract void dgbmvK(String var1, int var2, int var3, int var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double var14, double[] var16, int var17, int var18);

   public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
      if (debug) {
         System.err.println("sgbmv");
      }

      this.sgbmv(trans, m, n, kl, ku, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void sgbmv(String trans, int m, int n, int kl, int ku, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("sgbmv");
      }

      this.requireNonNull(a);
      this.requireNonNull(x);
      this.requireNonNull(y);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetx + ((this.lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
      this.checkIndex(offsety + ((this.lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
      this.sgbmvK(trans, m, n, kl, ku, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected abstract void sgbmvK(String var1, int var2, int var3, int var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float[] var14, int var15, int var16);

   public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
      if (debug) {
         System.err.println("dgemm");
      }

      this.dgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
   }

   public void dgemm(String transa, String transb, int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("dgemm");
      }

      this.checkArgument("DGEMM", 1, this.lsame("T", transa) || this.lsame("N", transa) || this.lsame("C", transa));
      this.checkArgument("DGEMM", 2, this.lsame("T", transb) || this.lsame("N", transb) || this.lsame("C", transb));
      this.checkArgument("DGEMM", 3, m >= 0);
      this.checkArgument("DGEMM", 4, n >= 0);
      this.checkArgument("DGEMM", 5, k >= 0);
      this.checkArgument("DGEMM", 8, lda >= Math.max(1, this.lsame("N", transa) ? m : k));
      this.checkArgument("DGEMM", 10, ldb >= Math.max(1, this.lsame("N", transb) ? k : n));
      this.checkArgument("DGEMM", 13, ldc >= Math.max(1, m));
      if (m != 0 && n != 0 && (alpha != (double)0.0F && k != 0 || beta != (double)1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("N", transa) ? k : m) * lda - 1, a.length);
         this.checkIndex(offsetb + (this.lsame("N", transb) ? n : k) * ldb - 1, b.length);
         this.checkIndex(offsetc + m * n - 1, c.length);
         this.dgemmK(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
   }

   protected abstract void dgemmK(String var1, String var2, int var3, int var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double var14, double[] var16, int var17, int var18);

   public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
      if (debug) {
         System.err.println("sgemm");
      }

      this.sgemm(transa, transb, m, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
   }

   public void sgemm(String transa, String transb, int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("sgemm");
      }

      this.checkArgument("SGEMM", 1, this.lsame("T", transa) || this.lsame("N", transa) || this.lsame("C", transa));
      this.checkArgument("SGEMM", 2, this.lsame("T", transb) || this.lsame("N", transb) || this.lsame("C", transb));
      this.checkArgument("SGEMM", 3, m >= 0);
      this.checkArgument("SGEMM", 4, n >= 0);
      this.checkArgument("SGEMM", 5, k >= 0);
      this.checkArgument("SGEMM", 8, lda >= Math.max(1, this.lsame("N", transa) ? m : k));
      this.checkArgument("SGEMM", 10, ldb >= Math.max(1, this.lsame("N", transb) ? k : n));
      this.checkArgument("SGEMM", 13, ldc >= Math.max(1, m));
      if (m != 0 && n != 0 && (alpha != 0.0F && k != 0 || beta != 1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("N", transa) ? k : m) * lda - 1, a.length);
         this.checkIndex(offsetb + (this.lsame("N", transb) ? n : k) * ldb - 1, b.length);
         this.checkIndex(offsetc + m * n - 1, c.length);
         this.sgemmK(transa, transb, m, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
   }

   protected abstract void sgemmK(String var1, String var2, int var3, int var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float[] var14, int var15, int var16);

   public void dgemv(String trans, int m, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
      if (debug) {
         System.err.println("dgemv");
      }

      this.dgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void dgemv(String trans, int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dgemv");
      }

      this.checkArgument("DGEMV", 1, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DGEMV", 2, m >= 0);
      this.checkArgument("DGEMV", 3, n >= 0);
      this.checkArgument("DGEMV", 6, lda >= Math.max(1, m));
      this.checkArgument("DGEMV", 8, incx != 0);
      this.checkArgument("DGEMV", 11, incy != 0);
      if (m != 0 && n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + ((this.lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + ((this.lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
         this.dgemvK(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
      }
   }

   protected abstract void dgemvK(String var1, int var2, int var3, double var4, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double[] var14, int var15, int var16);

   public void sgemv(String trans, int m, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
      if (debug) {
         System.err.println("sgemv");
      }

      this.sgemv(trans, m, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void sgemv(String trans, int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("sgemv");
      }

      this.checkArgument("SGEMV", 1, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("SGEMV", 2, m >= 0);
      this.checkArgument("SGEMV", 3, n >= 0);
      this.checkArgument("SGEMV", 6, lda >= Math.max(1, m));
      this.checkArgument("SGEMV", 8, incx != 0);
      this.checkArgument("SGEMV", 11, incy != 0);
      if (m != 0 && n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + ((this.lsame("N", trans) ? n : m) - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + ((this.lsame("N", trans) ? m : n) - 1) * Math.abs(incy), y.length);
         this.sgemvK(trans, m, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
      }
   }

   protected abstract void sgemvK(String var1, int var2, int var3, float var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float var11, float[] var12, int var13, int var14);

   public void dger(int m, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
      if (debug) {
         System.err.println("dger");
      }

      this.dger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
   }

   public void dger(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("dger");
      }

      this.checkArgument("DGER", 1, m >= 0);
      this.checkArgument("DGER", 2, n >= 0);
      this.checkArgument("DGER", 5, incx != 0);
      this.checkArgument("DGER", 7, incy != 0);
      this.checkArgument("DGER", 9, lda >= Math.max(1, m));
      if (m != 0 && n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (m - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.checkIndex(offseta + n * lda - 1, a.length);
         if (alpha != (double)0.0F) {
            this.dgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
         }

      }
   }

   protected abstract void dgerK(int var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13);

   public void sger(int m, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
      if (debug) {
         System.err.println("sger");
      }

      this.sger(m, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
   }

   public void sger(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("sger");
      }

      this.checkArgument("SGER", 1, m >= 0);
      this.checkArgument("SGER", 2, n >= 0);
      this.checkArgument("SGER", 5, incx != 0);
      this.checkArgument("SGER", 7, incy != 0);
      this.checkArgument("SGER", 9, lda >= Math.max(1, m));
      if (m != 0 && n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (m - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.checkIndex(offseta + n * lda - 1, a.length);
         if (alpha != 0.0F) {
            this.sgerK(m, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
         }

      }
   }

   protected abstract void sgerK(int var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12);

   public double dnrm2(int n, double[] x, int incx) {
      if (debug) {
         System.err.println("dnrm2");
      }

      return this.dnrm2(n, x, 0, incx);
   }

   public double dnrm2(int n, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dnrm2");
      }

      if (n <= 0) {
         return (double)0.0F;
      } else if (incx <= 0) {
         return (double)0.0F;
      } else if (n == 1) {
         return Math.abs(x[offsetx + 0]);
      } else {
         this.requireNonNull(x);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         return this.dnrm2K(n, x, offsetx, incx);
      }
   }

   protected abstract double dnrm2K(int var1, double[] var2, int var3, int var4);

   public float snrm2(int n, float[] x, int incx) {
      if (debug) {
         System.err.println("snrm2");
      }

      return this.snrm2(n, x, 0, incx);
   }

   public float snrm2(int n, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("snrm2");
      }

      if (n <= 0) {
         return 0.0F;
      } else if (incx <= 0) {
         return 0.0F;
      } else if (n == 1) {
         return Math.abs(x[offsetx + 0]);
      } else {
         this.requireNonNull(x);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         return this.snrm2K(n, x, offsetx, incx);
      }
   }

   protected abstract float snrm2K(int var1, float[] var2, int var3, int var4);

   public void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s) {
      if (debug) {
         System.err.println("drot");
      }

      this.drot(n, x, 0, incx, y, 0, incy, c, s);
   }

   public void drot(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double c, double s) {
      if (debug) {
         System.err.println("drot");
      }

      if (n > 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.drotK(n, x, offsetx, incx, y, offsety, incy, c, s);
      }
   }

   protected abstract void drotK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double var8, double var10);

   public void srot(int n, float[] x, int incx, float[] y, int incy, float c, float s) {
      if (debug) {
         System.err.println("srot");
      }

      this.srot(n, x, 0, incx, y, 0, incy, c, s);
   }

   public void srot(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float c, float s) {
      if (debug) {
         System.err.println("srot");
      }

      if (n > 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.srotK(n, x, offsetx, incx, y, offsety, incy, c, s);
      }
   }

   protected abstract void srotK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float var8, float var9);

   public void drotg(doubleW da, doubleW db, doubleW c, doubleW s) {
      if (debug) {
         System.err.println("drotg");
      }

      double scale = Math.abs(da.val) + Math.abs(db.val);
      if (scale == (double)0.0F) {
         c.val = (double)1.0F;
         s.val = (double)0.0F;
         da.val = (double)0.0F;
         db.val = (double)0.0F;
      } else {
         double r = scale * Math.sqrt(Math.pow(da.val / scale, (double)2.0F) + Math.pow(db.val / scale, (double)2.0F)) * ((Math.abs(da.val) > Math.abs(db.val) ? da.val : db.val) >= (double)0.0F ? (double)1.0F : (double)-1.0F);
         c.val = da.val / r;
         s.val = db.val / r;
         double z = (double)1.0F;
         if (Math.abs(da.val) > Math.abs(db.val)) {
            z = s.val;
         } else if (c.val != (double)0.0F) {
            z = (double)1.0F / c.val;
         }

         da.val = r;
         db.val = z;
      }

   }

   public void srotg(floatW sa, floatW sb, floatW c, floatW s) {
      if (debug) {
         System.err.println("srotg");
      }

      float scale = Math.abs(sa.val) + Math.abs(sb.val);
      if (scale == 0.0F) {
         c.val = 1.0F;
         s.val = 0.0F;
         sa.val = 0.0F;
         sb.val = 0.0F;
      } else {
         float r = (float)((double)scale * Math.sqrt(Math.pow((double)(sa.val / scale), (double)2.0F) + Math.pow((double)(sb.val / scale), (double)2.0F)) * ((Math.abs(sa.val) > Math.abs(sb.val) ? sa.val : sb.val) >= 0.0F ? (double)1.0F : (double)-1.0F));
         c.val = sa.val / r;
         s.val = sb.val / r;
         float z = 1.0F;
         if (Math.abs(sa.val) > Math.abs(sb.val)) {
            z = s.val;
         } else if (c.val != 0.0F) {
            z = 1.0F / c.val;
         }

         sa.val = r;
         sb.val = z;
      }

   }

   public void drotm(int n, double[] x, int incx, double[] y, int incy, double[] param) {
      if (debug) {
         System.err.println("drotm");
      }

      this.drotm(n, x, 0, incx, y, 0, incy, param, 0);
   }

   public void drotm(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] param, int offsetparam) {
      if (debug) {
         System.err.println("drotm");
      }

      this.requireNonNull(x);
      this.requireNonNull(y);
      this.requireNonNull(param);
      this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
      this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
      this.checkIndex(offsetparam + 4, param.length);
      this.drotmK(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
   }

   protected abstract void drotmK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   public void srotm(int n, float[] x, int incx, float[] y, int incy, float[] param) {
      if (debug) {
         System.err.println("srotm");
      }

      this.srotm(n, x, 0, incx, y, 0, incy, param, 0);
   }

   public void srotm(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] param, int offsetparam) {
      if (debug) {
         System.err.println("srotm");
      }

      this.requireNonNull(x);
      this.requireNonNull(y);
      this.requireNonNull(param);
      this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
      this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
      this.checkIndex(offsetparam + 4, param.length);
      this.srotmK(n, x, offsetx, incx, y, offsety, incy, param, offsetparam);
   }

   protected abstract void srotmK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   public void drotmg(doubleW dd1, doubleW dd2, doubleW dx1, double dy1, double[] param) {
      if (debug) {
         System.err.println("drotmg");
      }

      this.drotmg(dd1, dd2, dx1, dy1, param, 0);
   }

   public void drotmg(doubleW dd1, doubleW dd2, doubleW dx1, double dy1, double[] param, int offsetparam) {
      if (debug) {
         System.err.println("drotmg");
      }

      this.requireNonNull(dd1);
      this.requireNonNull(dd2);
      this.requireNonNull(dx1);
      this.requireNonNull(param);
      this.checkIndex(offsetparam + 4, param.length);
      this.drotmgK(dd1, dd2, dx1, dy1, param, offsetparam);
   }

   protected abstract void drotmgK(doubleW var1, doubleW var2, doubleW var3, double var4, double[] var6, int var7);

   public void srotmg(floatW sd1, floatW sd2, floatW sx1, float sy1, float[] param) {
      if (debug) {
         System.err.println("srotmg");
      }

      this.srotmg(sd1, sd2, sx1, sy1, param, 0);
   }

   public void srotmg(floatW sd1, floatW sd2, floatW sx1, float sy1, float[] param, int offsetparam) {
      if (debug) {
         System.err.println("srotmg");
      }

      this.requireNonNull(sd1);
      this.requireNonNull(sd2);
      this.requireNonNull(sx1);
      this.requireNonNull(param);
      this.checkIndex(offsetparam + 4, param.length);
      this.srotmgK(sd1, sd2, sx1, sy1, param, offsetparam);
   }

   protected abstract void srotmgK(floatW var1, floatW var2, floatW var3, float var4, float[] var5, int var6);

   public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
      if (debug) {
         System.err.println("dsbmv");
      }

      this.dsbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void dsbmv(String uplo, int n, int k, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dsbmv");
      }

      this.requireNonNull(a);
      this.requireNonNull(x);
      this.requireNonNull(y);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
      this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
      this.dsbmvK(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected abstract void dsbmvK(String var1, int var2, int var3, double var4, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double[] var14, int var15, int var16);

   public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
      if (debug) {
         System.err.println("ssbmv");
      }

      this.ssbmv(uplo, n, k, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void ssbmv(String uplo, int n, int k, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("ssbmv");
      }

      this.requireNonNull(a);
      this.requireNonNull(x);
      this.requireNonNull(y);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
      this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
      this.ssbmvK(uplo, n, k, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
   }

   protected abstract void ssbmvK(String var1, int var2, int var3, float var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float var11, float[] var12, int var13, int var14);

   public void dscal(int n, double alpha, double[] x, int incx) {
      if (debug) {
         System.err.println("dscal");
      }

      this.dscal(n, alpha, x, 0, incx);
   }

   public void dscal(int n, double alpha, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dscal");
      }

      if (n > 0) {
         if (incx > 0) {
            if (alpha != (double)1.0F) {
               this.requireNonNull(x);
               this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
               this.dscalK(n, alpha, x, offsetx, incx);
            }
         }
      }
   }

   protected abstract void dscalK(int var1, double var2, double[] var4, int var5, int var6);

   public void sscal(int n, float alpha, float[] x, int incx) {
      if (debug) {
         System.err.println("sscal");
      }

      this.sscal(n, alpha, x, 0, incx);
   }

   public void sscal(int n, float alpha, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("sscal");
      }

      if (n > 0) {
         if (incx > 0) {
            if (alpha != 1.0F) {
               this.requireNonNull(x);
               this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
               this.sscalK(n, alpha, x, offsetx, incx);
            }
         }
      }
   }

   protected abstract void sscalK(int var1, float var2, float[] var3, int var4, int var5);

   public void dspmv(String uplo, int n, double alpha, double[] a, double[] x, int incx, double beta, double[] y, int incy) {
      if (debug) {
         System.err.println("dspmv");
      }

      this.dspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
   }

   public void dspmv(String uplo, int n, double alpha, double[] a, int offseta, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dspmv");
      }

      this.checkArgument("DSPMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSPMV", 2, n >= 0);
      this.checkArgument("DSPMV", 6, incx != 0);
      this.checkArgument("DSPMV", 9, incy != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.dspmvK(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
      }
   }

   protected abstract void dspmvK(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, int var9, double var10, double[] var12, int var13, int var14);

   public void sspmv(String uplo, int n, float alpha, float[] a, float[] x, int incx, float beta, float[] y, int incy) {
      if (debug) {
         System.err.println("sspmv");
      }

      this.sspmv(uplo, n, alpha, a, 0, x, 0, incx, beta, y, 0, incy);
   }

   public void sspmv(String uplo, int n, float alpha, float[] a, int offseta, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("sspmv");
      }

      this.checkArgument("SSPMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSPMV", 2, n >= 0);
      this.checkArgument("SSPMV", 6, incx != 0);
      this.checkArgument("SSPMV", 9, incy != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.sspmvK(uplo, n, alpha, a, offseta, x, offsetx, incx, beta, y, offsety, incy);
      }
   }

   protected abstract void sspmvK(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, int var12);

   public void dspr(String uplo, int n, double alpha, double[] x, int incx, double[] a) {
      if (debug) {
         System.err.println("dspr");
      }

      this.dspr(uplo, n, alpha, x, 0, incx, a, 0);
   }

   public void dspr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta) {
      if (debug) {
         System.err.println("dspr");
      }

      this.checkArgument("DSPR", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSPR", 2, n >= 0);
      this.checkArgument("DSPR", 5, incx != 0);
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.dsprK(uplo, n, alpha, x, offsetx, incx, a, offseta);
      }
   }

   protected abstract void dsprK(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9);

   public void sspr(String uplo, int n, float alpha, float[] x, int incx, float[] a) {
      if (debug) {
         System.err.println("sspr");
      }

      this.sspr(uplo, n, alpha, x, 0, incx, a, 0);
   }

   public void sspr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta) {
      if (debug) {
         System.err.println("sspr");
      }

      this.checkArgument("SSPR", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSPR", 2, n >= 0);
      this.checkArgument("SSPR", 5, incx != 0);
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.ssprK(uplo, n, alpha, x, offsetx, incx, a, offseta);
      }
   }

   protected abstract void ssprK(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8);

   public void dspr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a) {
      if (debug) {
         System.err.println("dspr2");
      }

      this.dspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
   }

   public void dspr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta) {
      if (debug) {
         System.err.println("dspr2");
      }

      this.checkArgument("DSPR2", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSPR2", 2, n >= 0);
      this.checkArgument("DSPR2", 5, incx != 0);
      this.checkArgument("DSPR2", 7, incy != 0);
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.dspr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
      }
   }

   protected abstract void dspr2K(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12);

   public void sspr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a) {
      if (debug) {
         System.err.println("sspr2");
      }

      this.sspr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0);
   }

   public void sspr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta) {
      if (debug) {
         System.err.println("sspr2");
      }

      this.checkArgument("SSPR2", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSPR2", 2, n >= 0);
      this.checkArgument("SSPR2", 5, incx != 0);
      this.checkArgument("SSPR2", 7, incy != 0);
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.sspr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta);
      }
   }

   protected abstract void sspr2K(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11);

   public void dswap(int n, double[] x, int incx, double[] y, int incy) {
      if (debug) {
         System.err.println("dswap");
      }

      this.dswap(n, x, 0, incx, y, 0, incy);
   }

   public void dswap(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dswap");
      }

      if (n > 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.dswapK(n, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract void dswapK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   public void sswap(int n, float[] x, int incx, float[] y, int incy) {
      if (debug) {
         System.err.println("sswap");
      }

      this.sswap(n, x, 0, incx, y, 0, incy);
   }

   public void sswap(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("sswap");
      }

      if (n > 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.sswapK(n, x, offsetx, incx, y, offsety, incy);
      }
   }

   protected abstract void sswapK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
      if (debug) {
         System.err.println("dsymm");
      }

      this.dsymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
   }

   public void dsymm(String side, String uplo, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("dsymm");
      }

      this.checkArgument("DSYMM", 1, this.lsame("L", side) || this.lsame("R", side));
      this.checkArgument("DSYMM", 2, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYMM", 3, m >= 0);
      this.checkArgument("DSYMM", 4, n >= 0);
      this.checkArgument("DSYMM", 7, lda >= Math.max(1, this.lsame("L", side) ? m : n));
      this.checkArgument("DSYMM", 9, ldb >= Math.max(1, m));
      this.checkArgument("DSYMM", 12, ldc >= Math.max(1, m));
      if (m != 0 && n != 0 && (alpha != (double)0.0F || beta != (double)1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("L", side) ? m : n) * lda - 1, a.length);
         this.checkIndex(offsetb + n * ldb - 1, b.length);
         this.checkIndex(offsetc + n * ldc - 1, c.length);
         this.dsymmK(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
   }

   protected abstract void dsymmK(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double[] var15, int var16, int var17);

   public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
      if (debug) {
         System.err.println("ssymm");
      }

      this.ssymm(side, uplo, m, n, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
   }

   public void ssymm(String side, String uplo, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("ssymm");
      }

      this.checkArgument("SSYMM", 1, this.lsame("L", side) || this.lsame("R", side));
      this.checkArgument("SSYMM", 2, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSYMM", 3, m >= 0);
      this.checkArgument("SSYMM", 4, n >= 0);
      this.checkArgument("SSYMM", 7, lda >= Math.max(1, this.lsame("L", side) ? m : n));
      this.checkArgument("SSYMM", 9, ldb >= Math.max(1, m));
      this.checkArgument("SSYMM", 12, ldc >= Math.max(1, m));
      if (m != 0 && n != 0 && (alpha != 0.0F || beta != 1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("L", side) ? m : n) * lda - 1, a.length);
         this.checkIndex(offsetb + n * ldb - 1, b.length);
         this.checkIndex(offsetc + n * ldc - 1, c.length);
         this.ssymmK(side, uplo, m, n, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
   }

   protected abstract void ssymmK(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float[] var13, int var14, int var15);

   public void dsymv(String uplo, int n, double alpha, double[] a, int lda, double[] x, int incx, double beta, double[] y, int incy) {
      if (debug) {
         System.err.println("dsymv");
      }

      this.dsymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void dsymv(String uplo, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("dsymv");
      }

      this.checkArgument("DSYMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYMV", 2, n >= 0);
      this.checkArgument("DSYMV", 5, lda >= Math.max(1, n));
      this.checkArgument("DSYMV", 7, incx != 0);
      this.checkArgument("DSYMV", 10, incy != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.dsymvK(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
      }
   }

   protected abstract void dsymvK(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double var11, double[] var13, int var14, int var15);

   public void ssymv(String uplo, int n, float alpha, float[] a, int lda, float[] x, int incx, float beta, float[] y, int incy) {
      if (debug) {
         System.err.println("ssymv");
      }

      this.ssymv(uplo, n, alpha, a, 0, lda, x, 0, incx, beta, y, 0, incy);
   }

   public void ssymv(String uplo, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      if (debug) {
         System.err.println("ssymv");
      }

      this.checkArgument("SSYMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSYMV", 2, n >= 0);
      this.checkArgument("SSYMV", 5, lda >= Math.max(1, n));
      this.checkArgument("SSYMV", 7, incx != 0);
      this.checkArgument("SSYMV", 10, incy != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.ssymvK(uplo, n, alpha, a, offseta, lda, x, offsetx, incx, beta, y, offsety, incy);
      }
   }

   protected abstract void ssymvK(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float var10, float[] var11, int var12, int var13);

   public void dsyr(String uplo, int n, double alpha, double[] x, int incx, double[] a, int lda) {
      if (debug) {
         System.err.println("dsyr");
      }

      this.dsyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
   }

   public void dsyr(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("dsyr");
      }

      this.checkArgument("DSYR", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYR", 2, n >= 0);
      this.checkArgument("DSYR", 5, incx != 0);
      this.checkArgument("DSYR", 7, lda >= Math.max(1, n));
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.dsyrK(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
      }
   }

   protected abstract void dsyrK(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   public void ssyr(String uplo, int n, float alpha, float[] x, int incx, float[] a, int lda) {
      if (debug) {
         System.err.println("ssyr");
      }

      this.ssyr(uplo, n, alpha, x, 0, incx, a, 0, lda);
   }

   public void ssyr(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("ssyr");
      }

      this.checkArgument("SSYR", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSYR", 2, n >= 0);
      this.checkArgument("SSYR", 5, incx != 0);
      this.checkArgument("SSYR", 7, lda >= Math.max(1, n));
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.ssyrK(uplo, n, alpha, x, offsetx, incx, a, offseta, lda);
      }
   }

   protected abstract void ssyrK(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   public void dsyr2(String uplo, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] a, int lda) {
      if (debug) {
         System.err.println("dsyr2");
      }

      this.dsyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
   }

   public void dsyr2(String uplo, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("dsyr2");
      }

      this.checkArgument("DSYR2", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYR2", 2, n >= 0);
      this.checkArgument("DSYR2", 5, incx != 0);
      this.checkArgument("DSYR2", 7, incy != 0);
      this.checkArgument("DSYR2", 9, lda >= Math.max(1, n));
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.dsyr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
      }
   }

   protected abstract void dsyr2K(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13);

   public void ssyr2(String uplo, int n, float alpha, float[] x, int incx, float[] y, int incy, float[] a, int lda) {
      if (debug) {
         System.err.println("ssyr2");
      }

      this.ssyr2(uplo, n, alpha, x, 0, incx, y, 0, incy, a, 0, lda);
   }

   public void ssyr2(String uplo, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("ssyr2");
      }

      this.checkArgument("SSYR2", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSYR2", 2, n >= 0);
      this.checkArgument("SSYR2", 5, incx != 0);
      this.checkArgument("SSYR2", 7, incy != 0);
      this.checkArgument("SSYR2", 9, lda >= Math.max(1, n));
      if (n != 0) {
         this.requireNonNull(x);
         this.requireNonNull(y);
         this.requireNonNull(a);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.checkIndex(offsety + (n - 1) * Math.abs(incy), y.length);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.ssyr2K(uplo, n, alpha, x, offsetx, incx, y, offsety, incy, a, offseta, lda);
      }
   }

   protected abstract void ssyr2K(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12);

   public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double[] b, int ldb, double beta, double[] c, int ldc) {
      if (debug) {
         System.err.println("dsyr2k");
      }

      this.dsyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
   }

   public void dsyr2k(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("dsyr2k");
      }

      this.checkArgument("DSYR2K", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYR2K", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DSYR2K", 3, n >= 0);
      this.checkArgument("DSYR2K", 4, k >= 0);
      this.checkArgument("DSYR2K", 7, lda >= Math.max(1, this.lsame("N", trans) ? n : k));
      this.checkArgument("DSYR2K", 9, ldb >= Math.max(1, this.lsame("N", trans) ? n : k));
      this.checkArgument("DSYR2K", 12, ldc >= Math.max(1, n));
      if (n != 0 && (alpha != (double)0.0F && k != 0 || beta != (double)1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("N", trans) ? k : n) * lda - 1, a.length);
         this.checkIndex(offsetb + (this.lsame("N", trans) ? k : n) * ldb - 1, b.length);
         this.checkIndex(offsetc + n * ldc - 1, c.length);
         this.dsyr2kK(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
   }

   protected abstract void dsyr2kK(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double[] var15, int var16, int var17);

   public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float[] b, int ldb, float beta, float[] c, int ldc) {
      if (debug) {
         System.err.println("ssyr2k");
      }

      this.ssyr2k(uplo, trans, n, k, alpha, a, 0, lda, b, 0, ldb, beta, c, 0, ldc);
   }

   public void ssyr2k(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("ssyr2k");
      }

      this.checkArgument("SSYR2K", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSYR2K", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("SSYR2K", 3, n >= 0);
      this.checkArgument("SSYR2K", 4, k >= 0);
      this.checkArgument("SSYR2K", 7, lda >= Math.max(1, this.lsame("N", trans) ? n : k));
      this.checkArgument("SSYR2K", 9, ldb >= Math.max(1, this.lsame("N", trans) ? n : k));
      this.checkArgument("SSYR2K", 12, ldc >= Math.max(1, n));
      if (n != 0 && (alpha != 0.0F && k != 0 || beta != 1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("N", trans) ? k : n) * lda - 1, a.length);
         this.checkIndex(offsetb + (this.lsame("N", trans) ? k : n) * ldb - 1, b.length);
         this.checkIndex(offsetc + n * ldc - 1, c.length);
         this.ssyr2kK(uplo, trans, n, k, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
      }
   }

   protected abstract void ssyr2kK(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float[] var13, int var14, int var15);

   public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int lda, double beta, double[] c, int ldc) {
      if (debug) {
         System.err.println("dsyrk");
      }

      this.dsyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
   }

   public void dsyrk(String uplo, String trans, int n, int k, double alpha, double[] a, int offseta, int lda, double beta, double[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("dsyrk");
      }

      this.checkArgument("DSYRK", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYRK", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DSYRK", 3, n >= 0);
      this.checkArgument("DSYRK", 4, k >= 0);
      this.checkArgument("DSYRK", 7, lda >= Math.max(1, this.lsame("N", trans) ? n : k));
      this.checkArgument("DSYRK", 10, ldc >= Math.max(1, n));
      if (n != 0 && (alpha != (double)0.0F && k != 0 || beta != (double)1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("N", trans) ? k : n) * lda - 1, a.length);
         this.checkIndex(offsetc + n * ldc - 1, c.length);
         this.dsyrkK(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
      }
   }

   protected abstract void dsyrkK(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double var10, double[] var12, int var13, int var14);

   public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int lda, float beta, float[] c, int ldc) {
      if (debug) {
         System.err.println("ssyrk");
      }

      this.ssyrk(uplo, trans, n, k, alpha, a, 0, lda, beta, c, 0, ldc);
   }

   public void ssyrk(String uplo, String trans, int n, int k, float alpha, float[] a, int offseta, int lda, float beta, float[] c, int offsetc, int ldc) {
      if (debug) {
         System.err.println("ssyrk");
      }

      this.checkArgument("SSYRK", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("SSYRK", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("SSYRK", 3, n >= 0);
      this.checkArgument("SSYRK", 4, k >= 0);
      this.checkArgument("SSYRK", 7, lda >= Math.max(1, this.lsame("N", trans) ? n : k));
      this.checkArgument("SSYRK", 10, ldc >= Math.max(1, n));
      if (n != 0 && (alpha != 0.0F && k != 0 || beta != 1.0F)) {
         this.requireNonNull(a);
         this.requireNonNull(c);
         this.checkIndex(offseta + (this.lsame("N", trans) ? k : n) * lda - 1, a.length);
         this.checkIndex(offsetc + n * ldc - 1, c.length);
         this.ssyrkK(uplo, trans, n, k, alpha, a, offseta, lda, beta, c, offsetc, ldc);
      }
   }

   protected abstract void ssyrkK(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, int var12);

   public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
      if (debug) {
         System.err.println("dtbmv");
      }

      this.dtbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
   }

   public void dtbmv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dtbmv");
      }

      this.checkArgument("DTBMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTBMV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DTBMV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTBMV", 4, n >= 0);
      this.checkArgument("DTBMV", 5, k >= 0);
      this.checkArgument("DTBMV", 7, lda >= Math.max(1, k));
      this.checkArgument("DTBMV", 9, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.dtbmvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void dtbmvK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11);

   public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
      if (debug) {
         System.err.println("stbmv");
      }

      this.stbmv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
   }

   public void stbmv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("stbmv");
      }

      this.checkArgument("STBMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STBMV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("STBMV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STBMV", 4, n >= 0);
      this.checkArgument("STBMV", 5, k >= 0);
      this.checkArgument("STBMV", 7, lda >= Math.max(1, k));
      this.checkArgument("STBMV", 9, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.stbmvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void stbmvK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11);

   public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int lda, double[] x, int incx) {
      if (debug) {
         System.err.println("dtbsv");
      }

      this.dtbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
   }

   public void dtbsv(String uplo, String trans, String diag, int n, int k, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dtbsv");
      }

      this.checkArgument("DTBSV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTBSV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DTBSV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTBSV", 4, n >= 0);
      this.checkArgument("DTBSV", 5, k >= 0);
      this.checkArgument("DTBSV", 7, lda >= Math.max(1, k));
      this.checkArgument("DTBSV", 9, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.dtbsvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void dtbsvK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11);

   public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int lda, float[] x, int incx) {
      if (debug) {
         System.err.println("stbsv");
      }

      this.stbsv(uplo, trans, diag, n, k, a, 0, lda, x, 0, incx);
   }

   public void stbsv(String uplo, String trans, String diag, int n, int k, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("stbsv");
      }

      this.checkArgument("STBSV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STBSV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("STBSV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STBSV", 4, n >= 0);
      this.checkArgument("STBSV", 5, k >= 0);
      this.checkArgument("STBSV", 7, lda >= Math.max(1, k));
      this.checkArgument("STBSV", 9, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.stbsvK(uplo, trans, diag, n, k, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void stbsvK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11);

   public void dtpmv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
      if (debug) {
         System.err.println("dtpmv");
      }

      this.dtpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
   }

   public void dtpmv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dtpmv");
      }

      this.checkArgument("DTPMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTPMV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DTPMV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTPMV", 4, n >= 0);
      this.checkArgument("DTPMV", 7, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.dtpmvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
      }
   }

   protected abstract void dtpmvK(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   public void stpmv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
      if (debug) {
         System.err.println("stpmv");
      }

      this.stpmv(uplo, trans, diag, n, a, 0, x, 0, incx);
   }

   public void stpmv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("stpmv");
      }

      this.checkArgument("STPMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STPMV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("STPMV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STPMV", 4, n >= 0);
      this.checkArgument("STPMV", 7, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.stpmvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
      }
   }

   protected abstract void stpmvK(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   public void dtpsv(String uplo, String trans, String diag, int n, double[] a, double[] x, int incx) {
      if (debug) {
         System.err.println("dtpsv");
      }

      this.dtpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
   }

   public void dtpsv(String uplo, String trans, String diag, int n, double[] a, int offseta, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dtpsv");
      }

      this.checkArgument("DTPSV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTPSV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DTPSV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTPSV", 4, n >= 0);
      this.checkArgument("DTPSV", 7, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.dtpsvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
      }
   }

   protected abstract void dtpsvK(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   public void stpsv(String uplo, String trans, String diag, int n, float[] a, float[] x, int incx) {
      if (debug) {
         System.err.println("stpsv");
      }

      this.stpsv(uplo, trans, diag, n, a, 0, x, 0, incx);
   }

   public void stpsv(String uplo, String trans, String diag, int n, float[] a, int offseta, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("stpsv");
      }

      this.checkArgument("STPSV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STPSV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("STPSV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STPSV", 4, n >= 0);
      this.checkArgument("STPSV", 7, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * (n + 1) / 2 - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.stpsvK(uplo, trans, diag, n, a, offseta, x, offsetx, incx);
      }
   }

   protected abstract void stpsvK(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
      if (debug) {
         System.err.println("dtrmm");
      }

      this.dtrmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
   }

   public void dtrmm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("dtrmm");
      }

      this.checkArgument("DTRMM", 1, this.lsame("L", side) || this.lsame("R", side));
      this.checkArgument("DTRMM", 2, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTRMM", 3, this.lsame("N", transa) || this.lsame("T", transa) || this.lsame("C", transa));
      this.checkArgument("DTRMM", 4, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTRMM", 5, m >= 0);
      this.checkArgument("DTRMM", 6, n >= 0);
      this.checkArgument("DTRMM", 9, lda >= Math.max(1, this.lsame("L", side) ? m : n));
      this.checkArgument("DTRMM", 11, ldb >= Math.max(1, m));
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.checkIndex(offseta + (this.lsame("L", side) ? m : n) * lda - 1, a.length);
         this.checkIndex(offsetb + n * ldb - 1, b.length);
         this.dtrmmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
      }
   }

   protected abstract void dtrmmK(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
      if (debug) {
         System.err.println("strmm");
      }

      this.strmm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
   }

   public void strmm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("strmm");
      }

      this.checkArgument("STRMM", 1, this.lsame("L", side) || this.lsame("R", side));
      this.checkArgument("STRMM", 2, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STRMM", 3, this.lsame("N", transa) || this.lsame("T", transa) || this.lsame("C", transa));
      this.checkArgument("STRMM", 4, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STRMM", 5, m >= 0);
      this.checkArgument("STRMM", 6, n >= 0);
      this.checkArgument("STRMM", 9, lda >= Math.max(1, this.lsame("L", side) ? m : n));
      this.checkArgument("STRMM", 11, ldb >= Math.max(1, m));
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.checkIndex(offseta + (this.lsame("L", side) ? m : n) * lda - 1, a.length);
         this.checkIndex(offsetb + n * ldb - 1, b.length);
         this.strmmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
      }
   }

   protected abstract void strmmK(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13);

   public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
      if (debug) {
         System.err.println("dtrmv");
      }

      this.dtrmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
   }

   public void dtrmv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dtrmv");
      }

      this.checkArgument("DTRMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTRMV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DTRMV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTRMV", 4, n >= 0);
      this.checkArgument("DTRMV", 6, lda >= Math.max(1, n));
      this.checkArgument("DTRMV", 8, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.dtrmvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void dtrmvK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   public void strmv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
      if (debug) {
         System.err.println("strmv");
      }

      this.strmv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
   }

   public void strmv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("strmv");
      }

      this.checkArgument("STRMV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STRMV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("STRMV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STRMV", 4, n >= 0);
      this.checkArgument("STRMV", 6, lda >= Math.max(1, n));
      this.checkArgument("STRMV", 8, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.strmvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void strmvK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int lda, double[] b, int ldb) {
      if (debug) {
         System.err.println("dtrsm");
      }

      this.dtrsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
   }

   public void dtrsm(String side, String uplo, String transa, String diag, int m, int n, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("dtrsm");
      }

      this.checkArgument("DTRSM", 1, this.lsame("L", side) || this.lsame("R", side));
      this.checkArgument("DTRSM", 2, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTRSM", 3, this.lsame("N", transa) || this.lsame("T", transa) || this.lsame("C", transa));
      this.checkArgument("DTRSM", 4, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTRSM", 5, m >= 0);
      this.checkArgument("DTRSM", 6, n >= 0);
      this.checkArgument("DTRSM", 9, lda >= Math.max(1, this.lsame("L", side) ? m : n));
      this.checkArgument("DTRSM", 11, ldb >= Math.max(1, m));
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.checkIndex(offseta + (this.lsame("L", side) ? m : n) * lda - 1, a.length);
         this.checkIndex(offsetb + n * ldb - 1, b.length);
         this.dtrsmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
      }
   }

   protected abstract void dtrsmK(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int lda, float[] b, int ldb) {
      if (debug) {
         System.err.println("strsm");
      }

      this.strsm(side, uplo, transa, diag, m, n, alpha, a, 0, lda, b, 0, ldb);
   }

   public void strsm(String side, String uplo, String transa, String diag, int m, int n, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("strsm");
      }

      this.checkArgument("STRSM", 1, this.lsame("L", side) || this.lsame("R", side));
      this.checkArgument("STRSM", 2, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STRSM", 3, this.lsame("N", transa) || this.lsame("T", transa) || this.lsame("C", transa));
      this.checkArgument("STRSM", 4, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STRSM", 5, m >= 0);
      this.checkArgument("STRSM", 6, n >= 0);
      this.checkArgument("STRSM", 9, lda >= Math.max(1, this.lsame("L", side) ? m : n));
      this.checkArgument("STRSM", 11, ldb >= Math.max(1, m));
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(b);
         this.checkIndex(offseta + (this.lsame("L", side) ? m : n) * lda - 1, a.length);
         this.checkIndex(offsetb + n * ldb - 1, b.length);
         this.strsmK(side, uplo, transa, diag, m, n, alpha, a, offseta, lda, b, offsetb, ldb);
      }
   }

   protected abstract void strsmK(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13);

   public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int lda, double[] x, int incx) {
      if (debug) {
         System.err.println("dtrsv");
      }

      this.dtrsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
   }

   public void dtrsv(String uplo, String trans, String diag, int n, double[] a, int offseta, int lda, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("dtrsv");
      }

      this.checkArgument("DTRSV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DTRSV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("DTRSV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("DTRSV", 4, n >= 0);
      this.checkArgument("DTRSV", 6, lda >= Math.max(1, n));
      this.checkArgument("DTRSV", 8, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.dtrsvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void dtrsvK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   public void strsv(String uplo, String trans, String diag, int n, float[] a, int lda, float[] x, int incx) {
      if (debug) {
         System.err.println("strsv");
      }

      this.strsv(uplo, trans, diag, n, a, 0, lda, x, 0, incx);
   }

   public void strsv(String uplo, String trans, String diag, int n, float[] a, int offseta, int lda, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("strsv");
      }

      this.checkArgument("STRSV", 1, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("STRSV", 2, this.lsame("N", trans) || this.lsame("T", trans) || this.lsame("C", trans));
      this.checkArgument("STRSV", 3, this.lsame("U", diag) || this.lsame("N", diag));
      this.checkArgument("STRSV", 4, n >= 0);
      this.checkArgument("STRSV", 6, lda >= Math.max(1, n));
      this.checkArgument("STRSV", 8, incx != 0);
      if (n != 0) {
         this.requireNonNull(a);
         this.requireNonNull(x);
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         this.strsvK(uplo, trans, diag, n, a, offseta, lda, x, offsetx, incx);
      }
   }

   protected abstract void strsvK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   public int idamax(int n, double[] x, int incx) {
      if (debug) {
         System.err.println("idamax");
      }

      return this.idamax(n, x, 0, incx);
   }

   public int idamax(int n, double[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("idamax");
      }

      if (n <= 0) {
         return -1;
      } else if (incx <= 0) {
         return -1;
      } else if (n == 1) {
         return 0;
      } else {
         this.requireNonNull(x);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         return this.idamaxK(n, x, offsetx, incx) - 1;
      }
   }

   protected abstract int idamaxK(int var1, double[] var2, int var3, int var4);

   public int isamax(int n, float[] x, int incx) {
      if (debug) {
         System.err.println("isamax");
      }

      return this.isamax(n, x, 0, incx);
   }

   public int isamax(int n, float[] x, int offsetx, int incx) {
      if (debug) {
         System.err.println("isamax");
      }

      if (n <= 0) {
         return -1;
      } else if (incx <= 0) {
         return -1;
      } else if (n == 1) {
         return 0;
      } else {
         this.requireNonNull(x);
         this.checkIndex(offsetx + (n - 1) * Math.abs(incx), x.length);
         return this.isamaxK(n, x, offsetx, incx) - 1;
      }
   }

   protected abstract int isamaxK(int var1, float[] var2, int var3, int var4);

   public boolean lsame(String ca, String cb) {
      if (debug) {
         System.err.println("lsame");
      }

      return ca != null && ca.regionMatches(true, 0, cb, 0, ca.length());
   }
}
