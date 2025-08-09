package dev.ludovic.netlib.arpack;

import java.util.Objects;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

abstract class AbstractARPACK implements ARPACK {
   private static final boolean debug = System.getProperty("dev.ludovic.netlib.arpack.debug", "false").equals("true");

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

   public void dmout(int lout, int m, int n, double[] a, int lda, int idigit, String ifmt) {
      if (debug) {
         System.err.println("dmout");
      }

      this.dmout(lout, m, n, a, 0, lda, idigit, ifmt);
   }

   public void dmout(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt) {
      if (debug) {
         System.err.println("dmout");
      }

      this.dmoutK(lout, m, n, a, offseta, lda, idigit, ifmt);
   }

   protected abstract void dmoutK(int var1, int var2, int var3, double[] var4, int var5, int var6, int var7, String var8);

   public void smout(int lout, int m, int n, float[] a, int lda, int idigit, String ifmt) {
      if (debug) {
         System.err.println("smout");
      }

      this.smout(lout, m, n, a, 0, lda, idigit, ifmt);
   }

   public void smout(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt) {
      if (debug) {
         System.err.println("smout");
      }

      this.smoutK(lout, m, n, a, offseta, lda, idigit, ifmt);
   }

   protected abstract void smoutK(int var1, int var2, int var3, float[] var4, int var5, int var6, int var7, String var8);

   public void dvout(int lout, int n, double[] sx, int idigit, String ifmt) {
      if (debug) {
         System.err.println("dvout");
      }

      this.dvout(lout, n, sx, 0, idigit, ifmt);
   }

   public void dvout(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt) {
      if (debug) {
         System.err.println("dvout");
      }

      this.dvoutK(lout, n, sx, offsetsx, idigit, ifmt);
   }

   protected abstract void dvoutK(int var1, int var2, double[] var3, int var4, int var5, String var6);

   public void svout(int lout, int n, float[] sx, int idigit, String ifmt) {
      if (debug) {
         System.err.println("svout");
      }

      this.svout(lout, n, sx, 0, idigit, ifmt);
   }

   public void svout(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt) {
      if (debug) {
         System.err.println("svout");
      }

      this.svoutK(lout, n, sx, offsetsx, idigit, ifmt);
   }

   protected abstract void svoutK(int var1, int var2, float[] var3, int var4, int var5, String var6);

   public void ivout(int lout, int n, int[] ix, int idigit, String ifmt) {
      if (debug) {
         System.err.println("ivout");
      }

      this.ivout(lout, n, ix, 0, idigit, ifmt);
   }

   public void ivout(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt) {
      if (debug) {
         System.err.println("ivout");
      }

      this.ivoutK(lout, n, ix, offsetix, idigit, ifmt);
   }

   protected abstract void ivoutK(int var1, int var2, int[] var3, int var4, int var5, String var6);

   public void dgetv0(intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int ldv, double[] resid, doubleW rnorm, int[] ipntr, double[] workd, intW ierr) {
      if (debug) {
         System.err.println("dgetv0");
      }

      this.dgetv0(ido, bmat, itry, initv, n, j, v, 0, ldv, resid, 0, rnorm, ipntr, 0, workd, 0, ierr);
   }

   public void dgetv0(intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW ierr) {
      if (debug) {
         System.err.println("dgetv0");
      }

      this.dgetv0K(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
   }

   protected abstract void dgetv0K(intW var1, String var2, int var3, boolean var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, doubleW var12, int[] var13, int var14, double[] var15, int var16, intW var17);

   public void sgetv0(intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int ldv, float[] resid, floatW rnorm, int[] ipntr, float[] workd, intW ierr) {
      if (debug) {
         System.err.println("sgetv0");
      }

      this.sgetv0(ido, bmat, itry, initv, n, j, v, 0, ldv, resid, 0, rnorm, ipntr, 0, workd, 0, ierr);
   }

   public void sgetv0(intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW ierr) {
      if (debug) {
         System.err.println("sgetv0");
      }

      this.sgetv0K(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
   }

   protected abstract void sgetv0K(intW var1, String var2, int var3, boolean var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, floatW var12, int[] var13, int var14, float[] var15, int var16, intW var17);

   public void dlaqrb(boolean wantt, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, double[] z, intW info) {
      if (debug) {
         System.err.println("dlaqrb");
      }

      this.dlaqrb(wantt, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, info);
   }

   public void dlaqrb(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, intW info) {
      if (debug) {
         System.err.println("dlaqrb");
      }

      this.dlaqrbK(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
   }

   protected abstract void dlaqrbK(boolean var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   public void slaqrb(boolean wantt, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, float[] z, intW info) {
      if (debug) {
         System.err.println("slaqrb");
      }

      this.slaqrb(wantt, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, info);
   }

   public void slaqrb(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, intW info) {
      if (debug) {
         System.err.println("slaqrb");
      }

      this.slaqrbK(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
   }

   protected abstract void slaqrbK(boolean var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   public void dnaitr(intW ido, String bmat, int n, int k, int np, int nb, double[] resid, doubleW rnorm, double[] v, int ldv, double[] h, int ldh, int[] ipntr, double[] workd, intW info) {
      if (debug) {
         System.err.println("dnaitr");
      }

      this.dnaitr(ido, bmat, n, k, np, nb, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
   }

   public void dnaitr(intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("dnaitr");
      }

      this.dnaitrK(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void dnaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, doubleW var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   public void snaitr(intW ido, String bmat, int n, int k, int np, int nb, float[] resid, floatW rnorm, float[] v, int ldv, float[] h, int ldh, int[] ipntr, float[] workd, intW info) {
      if (debug) {
         System.err.println("snaitr");
      }

      this.snaitr(ido, bmat, n, k, np, nb, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
   }

   public void snaitr(intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("snaitr");
      }

      this.snaitrK(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void snaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, floatW var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   public void dnapps(int n, intW kev, int np, double[] shiftr, double[] shifti, double[] v, int ldv, double[] h, int ldh, double[] resid, double[] q, int ldq, double[] workl, double[] workd) {
      if (debug) {
         System.err.println("dnapps");
      }

      this.dnapps(n, kev, np, shiftr, 0, shifti, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workl, 0, workd, 0);
   }

   public void dnapps(int n, intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd) {
      if (debug) {
         System.err.println("dnapps");
      }

      this.dnappsK(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
   }

   protected abstract void dnappsK(int var1, intW var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, double[] var21, int var22);

   public void snapps(int n, intW kev, int np, float[] shiftr, float[] shifti, float[] v, int ldv, float[] h, int ldh, float[] resid, float[] q, int ldq, float[] workl, float[] workd) {
      if (debug) {
         System.err.println("snapps");
      }

      this.snapps(n, kev, np, shiftr, 0, shifti, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workl, 0, workd, 0);
   }

   public void snapps(int n, intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd) {
      if (debug) {
         System.err.println("snapps");
      }

      this.snappsK(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
   }

   protected abstract void snappsK(int var1, intW var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, float[] var21, int var22);

   public void dnaup2(intW ido, String bmat, int n, String which, intW nev, intW np, double tol, double[] resid, int mode, int iupd, int ishift, intW mxiter, double[] v, int ldv, double[] h, int ldh, double[] ritzr, double[] ritzi, double[] bounds, double[] q, int ldq, double[] workl, int[] ipntr, double[] workd, intW info) {
      if (debug) {
         System.err.println("dnaup2");
      }

      this.dnaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
   }

   public void dnaup2(intW ido, String bmat, int n, String which, intW nev, intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("dnaup2");
      }

      this.dnaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void dnaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, int var13, intW var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int var29, double[] var30, int var31, int[] var32, int var33, double[] var34, int var35, intW var36);

   public void snaup2(intW ido, String bmat, int n, String which, intW nev, intW np, float tol, float[] resid, int mode, int iupd, int ishift, intW mxiter, float[] v, int ldv, float[] h, int ldh, float[] ritzr, float[] ritzi, float[] bounds, float[] q, int ldq, float[] workl, int[] ipntr, float[] workd, intW info) {
      if (debug) {
         System.err.println("snaup2");
      }

      this.snaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
   }

   public void snaup2(intW ido, String bmat, int n, String which, intW nev, intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("snaup2");
      }

      this.snaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void snaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, int var12, intW var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, float[] var26, int var27, int var28, float[] var29, int var30, int[] var31, int var32, float[] var33, int var34, intW var35);

   public void dnaupd(intW ido, String bmat, int n, String which, int nev, doubleW tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dnaupd");
      }

      this.dnaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void dnaupd(intW ido, String bmat, int n, String which, int nev, doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dnaupd");
      }

      this.dnaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void dnaupdK(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, intW var22);

   public void snaupd(intW ido, String bmat, int n, String which, int nev, floatW tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("snaupd");
      }

      this.snaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void snaupd(intW ido, String bmat, int n, String which, int nev, floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("snaupd");
      }

      this.snaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void snaupdK(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, intW var22);

   public void dnconv(int n, double[] ritzr, double[] ritzi, double[] bounds, double tol, intW nconv) {
      if (debug) {
         System.err.println("dnconv");
      }

      this.dnconv(n, ritzr, 0, ritzi, 0, bounds, 0, tol, nconv);
   }

   public void dnconv(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, intW nconv) {
      if (debug) {
         System.err.println("dnconv");
      }

      this.dnconvK(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
   }

   protected abstract void dnconvK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, intW var10);

   public void snconv(int n, float[] ritzr, float[] ritzi, float[] bounds, float tol, intW nconv) {
      if (debug) {
         System.err.println("snconv");
      }

      this.snconv(n, ritzr, 0, ritzi, 0, bounds, 0, tol, nconv);
   }

   public void snconv(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, intW nconv) {
      if (debug) {
         System.err.println("snconv");
      }

      this.snconvK(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
   }

   protected abstract void snconvK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, intW var9);

   public void dsconv(int n, double[] ritz, double[] bounds, double tol, intW nconv) {
      if (debug) {
         System.err.println("dsconv");
      }

      this.dsconv(n, ritz, 0, bounds, 0, tol, nconv);
   }

   public void dsconv(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, intW nconv) {
      if (debug) {
         System.err.println("dsconv");
      }

      this.dsconvK(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
   }

   protected abstract void dsconvK(int var1, double[] var2, int var3, double[] var4, int var5, double var6, intW var8);

   public void ssconv(int n, float[] ritz, float[] bounds, float tol, intW nconv) {
      if (debug) {
         System.err.println("ssconv");
      }

      this.ssconv(n, ritz, 0, bounds, 0, tol, nconv);
   }

   public void ssconv(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, intW nconv) {
      if (debug) {
         System.err.println("ssconv");
      }

      this.ssconvK(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
   }

   protected abstract void ssconvK(int var1, float[] var2, int var3, float[] var4, int var5, float var6, intW var7);

   public void dneigh(double rnorm, intW n, double[] h, int ldh, double[] ritzr, double[] ritzi, double[] bounds, double[] q, int ldq, double[] workl, intW ierr) {
      if (debug) {
         System.err.println("dneigh");
      }

      this.dneigh(rnorm, n, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ierr);
   }

   public void dneigh(double rnorm, intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, intW ierr) {
      if (debug) {
         System.err.println("dneigh");
      }

      this.dneighK(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
   }

   protected abstract void dneighK(double var1, intW var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, intW var18);

   public void sneigh(float rnorm, intW n, float[] h, int ldh, float[] ritzr, float[] ritzi, float[] bounds, float[] q, int ldq, float[] workl, intW ierr) {
      if (debug) {
         System.err.println("sneigh");
      }

      this.sneigh(rnorm, n, h, 0, ldh, ritzr, 0, ritzi, 0, bounds, 0, q, 0, ldq, workl, 0, ierr);
   }

   public void sneigh(float rnorm, intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, intW ierr) {
      if (debug) {
         System.err.println("sneigh");
      }

      this.sneighK(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
   }

   protected abstract void sneighK(float var1, intW var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   public void dneupd(boolean rvec, String howmny, boolean[] select, double[] dr, double[] di, double[] z, int ldz, double sigmar, double sigmai, double[] workev, String bmat, int n, String which, intW nev, double tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dneupd");
      }

      this.dneupd(rvec, howmny, select, 0, dr, 0, di, 0, z, 0, ldz, sigmar, sigmai, workev, 0, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void dneupd(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dneupd");
      }

      this.dneupdK(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void dneupdK(boolean var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double var12, double var14, double[] var16, int var17, String var18, int var19, String var20, intW var21, double var22, double[] var24, int var25, int var26, double[] var27, int var28, int var29, int[] var30, int var31, int[] var32, int var33, double[] var34, int var35, double[] var36, int var37, int var38, intW var39);

   public void sneupd(boolean rvec, String howmny, boolean[] select, float[] dr, float[] di, float[] z, int ldz, float sigmar, float sigmai, float[] workev, String bmat, int n, String which, intW nev, float tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("sneupd");
      }

      this.sneupd(rvec, howmny, select, 0, dr, 0, di, 0, z, 0, ldz, sigmar, sigmai, workev, 0, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void sneupd(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("sneupd");
      }

      this.sneupdK(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void sneupdK(boolean var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float var12, float var13, float[] var14, int var15, String var16, int var17, String var18, intW var19, float var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, int[] var27, int var28, int[] var29, int var30, float[] var31, int var32, float[] var33, int var34, int var35, intW var36);

   public void dngets(int ishift, String which, intW kev, intW np, double[] ritzr, double[] ritzi, double[] bounds, double[] shiftr, double[] shifti) {
      if (debug) {
         System.err.println("dngets");
      }

      this.dngets(ishift, which, kev, np, ritzr, 0, ritzi, 0, bounds, 0, shiftr, 0, shifti, 0);
   }

   public void dngets(int ishift, String which, intW kev, intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti) {
      if (debug) {
         System.err.println("dngets");
      }

      this.dngetsK(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
   }

   protected abstract void dngetsK(int var1, String var2, intW var3, intW var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14);

   public void sngets(int ishift, String which, intW kev, intW np, float[] ritzr, float[] ritzi, float[] bounds, float[] shiftr, float[] shifti) {
      if (debug) {
         System.err.println("sngets");
      }

      this.sngets(ishift, which, kev, np, ritzr, 0, ritzi, 0, bounds, 0, shiftr, 0, shifti, 0);
   }

   public void sngets(int ishift, String which, intW kev, intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti) {
      if (debug) {
         System.err.println("sngets");
      }

      this.sngetsK(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
   }

   protected abstract void sngetsK(int var1, String var2, intW var3, intW var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14);

   public void dsaitr(intW ido, String bmat, int n, int k, int np, int mode, double[] resid, doubleW rnorm, double[] v, int ldv, double[] h, int ldh, int[] ipntr, double[] workd, intW info) {
      if (debug) {
         System.err.println("dsaitr");
      }

      this.dsaitr(ido, bmat, n, k, np, mode, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
   }

   public void dsaitr(intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("dsaitr");
      }

      this.dsaitrK(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void dsaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, doubleW var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   public void ssaitr(intW ido, String bmat, int n, int k, int np, int mode, float[] resid, floatW rnorm, float[] v, int ldv, float[] h, int ldh, int[] ipntr, float[] workd, intW info) {
      if (debug) {
         System.err.println("ssaitr");
      }

      this.ssaitr(ido, bmat, n, k, np, mode, resid, 0, rnorm, v, 0, ldv, h, 0, ldh, ipntr, 0, workd, 0, info);
   }

   public void ssaitr(intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("ssaitr");
      }

      this.ssaitrK(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void ssaitrK(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, floatW var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   public void dsapps(int n, int kev, int np, double[] shift, double[] v, int ldv, double[] h, int ldh, double[] resid, double[] q, int ldq, double[] workd) {
      if (debug) {
         System.err.println("dsapps");
      }

      this.dsapps(n, kev, np, shift, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workd, 0);
   }

   public void dsapps(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd) {
      if (debug) {
         System.err.println("dsapps");
      }

      this.dsappsK(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
   }

   protected abstract void dsappsK(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18);

   public void ssapps(int n, int kev, int np, float[] shift, float[] v, int ldv, float[] h, int ldh, float[] resid, float[] q, int ldq, float[] workd) {
      if (debug) {
         System.err.println("ssapps");
      }

      this.ssapps(n, kev, np, shift, 0, v, 0, ldv, h, 0, ldh, resid, 0, q, 0, ldq, workd, 0);
   }

   public void ssapps(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd) {
      if (debug) {
         System.err.println("ssapps");
      }

      this.ssappsK(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
   }

   protected abstract void ssappsK(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18);

   public void dsaup2(intW ido, String bmat, int n, String which, intW nev, intW np, double tol, double[] resid, int mode, int iupd, int ishift, intW mxiter, double[] v, int ldv, double[] h, int ldh, double[] ritz, double[] bounds, double[] q, int ldq, double[] workl, int[] ipntr, double[] workd, intW info) {
      if (debug) {
         System.err.println("dsaup2");
      }

      this.dsaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritz, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
   }

   public void dsaup2(intW ido, String bmat, int n, String which, intW nev, intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("dsaup2");
      }

      this.dsaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void dsaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, int var13, intW var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int var27, double[] var28, int var29, int[] var30, int var31, double[] var32, int var33, intW var34);

   public void ssaup2(intW ido, String bmat, int n, String which, intW nev, intW np, float tol, float[] resid, int mode, int iupd, int ishift, intW mxiter, float[] v, int ldv, float[] h, int ldh, float[] ritz, float[] bounds, float[] q, int ldq, float[] workl, int[] ipntr, float[] workd, intW info) {
      if (debug) {
         System.err.println("ssaup2");
      }

      this.ssaup2(ido, bmat, n, which, nev, np, tol, resid, 0, mode, iupd, ishift, mxiter, v, 0, ldv, h, 0, ldh, ritz, 0, bounds, 0, q, 0, ldq, workl, 0, ipntr, 0, workd, 0, info);
   }

   public void ssaup2(intW ido, String bmat, int n, String which, intW nev, intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      if (debug) {
         System.err.println("ssaup2");
      }

      this.ssaup2K(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected abstract void ssaup2K(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, int var12, intW var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int[] var29, int var30, float[] var31, int var32, intW var33);

   public void dseigt(double rnorm, int n, double[] h, int ldh, double[] eig, double[] bounds, double[] workl, intW ierr) {
      if (debug) {
         System.err.println("dseigt");
      }

      this.dseigt(rnorm, n, h, 0, ldh, eig, 0, bounds, 0, workl, 0, ierr);
   }

   public void dseigt(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, intW ierr) {
      if (debug) {
         System.err.println("dseigt");
      }

      this.dseigtK(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
   }

   protected abstract void dseigtK(double var1, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   public void sseigt(float rnorm, int n, float[] h, int ldh, float[] eig, float[] bounds, float[] workl, intW ierr) {
      if (debug) {
         System.err.println("sseigt");
      }

      this.sseigt(rnorm, n, h, 0, ldh, eig, 0, bounds, 0, workl, 0, ierr);
   }

   public void sseigt(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, intW ierr) {
      if (debug) {
         System.err.println("sseigt");
      }

      this.sseigtK(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
   }

   protected abstract void sseigtK(float var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   public void dsesrt(String which, boolean apply, int n, double[] x, int na, double[] a, int lda) {
      if (debug) {
         System.err.println("dsesrt");
      }

      this.dsesrt(which, apply, n, x, 0, na, a, 0, lda);
   }

   public void dsesrt(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("dsesrt");
      }

      this.dsesrtK(which, apply, n, x, offsetx, na, a, offseta, lda);
   }

   protected abstract void dsesrtK(String var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   public void ssesrt(String which, boolean apply, int n, float[] x, int na, float[] a, int lda) {
      if (debug) {
         System.err.println("ssesrt");
      }

      this.ssesrt(which, apply, n, x, 0, na, a, 0, lda);
   }

   public void ssesrt(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("ssesrt");
      }

      this.ssesrtK(which, apply, n, x, offsetx, na, a, offseta, lda);
   }

   protected abstract void ssesrtK(String var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   public void dsaupd(intW ido, String bmat, int n, String which, int nev, doubleW tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dsaupd");
      }

      this.dsaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void dsaupd(intW ido, String bmat, int n, String which, int nev, doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dsaupd");
      }

      this.checkArgument("DSAUPD", 2, this.lsame("I", bmat) || this.lsame("G", bmat));
      this.checkArgument("DSAUPD", 3, n >= 0);
      this.checkArgument("DSAUPD", 4, this.lsame("LA", which) || this.lsame("SA", which) || this.lsame("LM", which) || this.lsame("SM", which) || this.lsame("BE", which));
      this.checkArgument("DSAUPD", 5, 0 < nev && nev < n);
      this.checkArgument("DSAUPD", 15, (double)lworkl >= Math.pow((double)ncv, (double)2.0F) + (double)(8 * ncv));
      this.requireNonNull(ido);
      this.requireNonNull(tol);
      this.requireNonNull(resid);
      this.requireNonNull(v);
      this.requireNonNull(iparam);
      this.requireNonNull(ipntr);
      this.requireNonNull(workd);
      this.requireNonNull(info);
      this.checkIndex(offsetresid + n - 1, resid.length);
      this.checkIndex(offsetv + n * ncv - 1, v.length);
      this.checkIndex(offsetiparam + 11 - 1, iparam.length);
      this.checkIndex(offsetipntr + 11 - 1, ipntr.length);
      this.checkIndex(offsetworkd + 3 * n - 1, workd.length);
      this.checkIndex(offsetworkl + lworkl - 1, workl.length);
      this.dsaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void dsaupdK(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, intW var22);

   public void ssaupd(intW ido, String bmat, int n, String which, int nev, floatW tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("ssaupd");
      }

      this.ssaupd(ido, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void ssaupd(intW ido, String bmat, int n, String which, int nev, floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("ssaupd");
      }

      this.checkArgument("SSAUPD", 2, this.lsame("I", bmat) || this.lsame("G", bmat));
      this.checkArgument("SSAUPD", 3, n >= 0);
      this.checkArgument("SSAUPD", 4, this.lsame("LA", which) || this.lsame("SA", which) || this.lsame("LM", which) || this.lsame("SM", which) || this.lsame("BE", which));
      this.checkArgument("SSAUPD", 5, 0 < nev && nev < n);
      this.checkArgument("SSAUPD", 15, (double)lworkl >= Math.pow((double)ncv, (double)2.0F) + (double)(8 * ncv));
      this.requireNonNull(ido);
      this.requireNonNull(tol);
      this.requireNonNull(resid);
      this.requireNonNull(v);
      this.requireNonNull(iparam);
      this.requireNonNull(ipntr);
      this.requireNonNull(workd);
      this.requireNonNull(info);
      this.checkIndex(offsetresid + n - 1, resid.length);
      this.checkIndex(offsetv + n * ncv - 1, v.length);
      this.checkIndex(offsetiparam + 11 - 1, iparam.length);
      this.checkIndex(offsetipntr + 11 - 1, ipntr.length);
      this.checkIndex(offsetworkd + 3 * n - 1, workd.length);
      this.checkIndex(offsetworkl + lworkl - 1, workl.length);
      this.ssaupdK(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void ssaupdK(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, intW var22);

   public void dseupd(boolean rvec, String howmny, boolean[] select, double[] d, double[] z, int ldz, double sigma, String bmat, int n, String which, intW nev, double tol, double[] resid, int ncv, double[] v, int ldv, int[] iparam, int[] ipntr, double[] workd, double[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dseupd");
      }

      this.dseupd(rvec, howmny, select, 0, d, 0, z, 0, ldz, sigma, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void dseupd(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("dseupd");
      }

      this.checkArgument("DSEUPD", 2, this.lsame("A", howmny) || this.lsame("S", howmny));
      this.checkArgument("DSEUPD", 2, ldz >= Math.max(1, n));
      this.checkArgument("DSEUPD", 8, this.lsame("I", bmat) || this.lsame("G", bmat));
      this.checkArgument("DSEUPD", 9, n >= 0);
      this.checkArgument("DSEUPD", 10, this.lsame("LA", which) || this.lsame("SA", which) || this.lsame("LM", which) || this.lsame("SM", which) || this.lsame("BE", which));
      this.checkArgument("DSEUPD", 11, 0 < nev.val && nev.val < n);
      this.checkArgument("DSEUPD", 21, (double)lworkl >= Math.pow((double)ncv, (double)2.0F) + (double)(8 * ncv));
      this.requireNonNull(select);
      this.requireNonNull(d);
      if (rvec) {
         this.requireNonNull(z);
      }

      this.requireNonNull(resid);
      this.requireNonNull(v);
      this.requireNonNull(iparam);
      this.requireNonNull(ipntr);
      this.requireNonNull(workd);
      this.requireNonNull(info);
      this.checkIndex(offsetselect + nev.val - 1, select.length);
      this.checkIndex(offsetd + nev.val - 1, d.length);
      if (rvec) {
         this.checkIndex(offsetz + n * nev.val - 1, z.length);
      }

      this.checkIndex(offsetresid + n - 1, resid.length);
      this.checkIndex(offsetv + n * ncv - 1, v.length);
      this.checkIndex(offsetiparam + 11 - 1, iparam.length);
      this.checkIndex(offsetipntr + 11 - 1, ipntr.length);
      this.checkIndex(offsetworkd + 3 * n - 1, workd.length);
      this.checkIndex(offsetworkl + lworkl - 1, workl.length);
      this.dseupdK(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void dseupdK(boolean var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double var10, String var12, int var13, String var14, intW var15, double var16, double[] var18, int var19, int var20, double[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int var32, intW var33);

   public void sseupd(boolean rvec, String howmny, boolean[] select, float[] d, float[] z, int ldz, float sigma, String bmat, int n, String which, intW nev, float tol, float[] resid, int ncv, float[] v, int ldv, int[] iparam, int[] ipntr, float[] workd, float[] workl, int lworkl, intW info) {
      if (debug) {
         System.err.println("sseupd");
      }

      this.sseupd(rvec, howmny, select, 0, d, 0, z, 0, ldz, sigma, bmat, n, which, nev, tol, resid, 0, ncv, v, 0, ldv, iparam, 0, ipntr, 0, workd, 0, workl, 0, lworkl, info);
   }

   public void sseupd(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      if (debug) {
         System.err.println("sseupd");
      }

      this.checkArgument("SSEUPD", 2, this.lsame("A", howmny) || this.lsame("S", howmny));
      this.checkArgument("SSEUPD", 2, ldz >= Math.max(1, n));
      this.checkArgument("SSEUPD", 8, this.lsame("I", bmat) || this.lsame("G", bmat));
      this.checkArgument("SSEUPD", 9, n >= 0);
      this.checkArgument("SSEUPD", 10, this.lsame("LA", which) || this.lsame("SA", which) || this.lsame("LM", which) || this.lsame("SM", which) || this.lsame("BE", which));
      this.checkArgument("SSEUPD", 11, 0 < nev.val && nev.val < n);
      this.checkArgument("SSEUPD", 21, (double)lworkl >= Math.pow((double)ncv, (double)2.0F) + (double)(8 * ncv));
      this.requireNonNull(select);
      this.requireNonNull(d);
      if (rvec) {
         this.requireNonNull(z);
      }

      this.requireNonNull(resid);
      this.requireNonNull(v);
      this.requireNonNull(iparam);
      this.requireNonNull(ipntr);
      this.requireNonNull(workd);
      this.requireNonNull(info);
      this.checkIndex(offsetselect + nev.val - 1, select.length);
      this.checkIndex(offsetd + nev.val - 1, d.length);
      if (rvec) {
         this.checkIndex(offsetz + n * nev.val - 1, z.length);
      }

      this.checkIndex(offsetresid + n - 1, resid.length);
      this.checkIndex(offsetv + n * ncv - 1, v.length);
      this.checkIndex(offsetiparam + 11 - 1, iparam.length);
      this.checkIndex(offsetipntr + 11 - 1, ipntr.length);
      this.checkIndex(offsetworkd + 3 * n - 1, workd.length);
      this.checkIndex(offsetworkl + lworkl - 1, workl.length);
      this.sseupdK(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected abstract void sseupdK(boolean var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float var10, String var11, int var12, String var13, intW var14, float var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, int[] var22, int var23, int[] var24, int var25, float[] var26, int var27, float[] var28, int var29, int var30, intW var31);

   public void dsgets(int ishift, String which, intW kev, intW np, double[] ritz, double[] bounds, double[] shifts) {
      if (debug) {
         System.err.println("dsgets");
      }

      this.dsgets(ishift, which, kev, np, ritz, 0, bounds, 0, shifts, 0);
   }

   public void dsgets(int ishift, String which, intW kev, intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts) {
      if (debug) {
         System.err.println("dsgets");
      }

      this.dsgetsK(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
   }

   protected abstract void dsgetsK(int var1, String var2, intW var3, intW var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10);

   public void ssgets(int ishift, String which, intW kev, intW np, float[] ritz, float[] bounds, float[] shifts) {
      if (debug) {
         System.err.println("ssgets");
      }

      this.ssgets(ishift, which, kev, np, ritz, 0, bounds, 0, shifts, 0);
   }

   public void ssgets(int ishift, String which, intW kev, intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts) {
      if (debug) {
         System.err.println("ssgets");
      }

      this.ssgetsK(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
   }

   protected abstract void ssgetsK(int var1, String var2, intW var3, intW var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10);

   public void dsortc(String which, boolean apply, int n, double[] xreal, double[] ximag, double[] y) {
      if (debug) {
         System.err.println("dsortc");
      }

      this.dsortc(which, apply, n, xreal, 0, ximag, 0, y, 0);
   }

   public void dsortc(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety) {
      if (debug) {
         System.err.println("dsortc");
      }

      this.dsortcK(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
   }

   protected abstract void dsortcK(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9);

   public void ssortc(String which, boolean apply, int n, float[] xreal, float[] ximag, float[] y) {
      if (debug) {
         System.err.println("ssortc");
      }

      this.ssortc(which, apply, n, xreal, 0, ximag, 0, y, 0);
   }

   public void ssortc(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety) {
      if (debug) {
         System.err.println("ssortc");
      }

      this.ssortcK(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
   }

   protected abstract void ssortcK(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9);

   public void dsortr(String which, boolean apply, int n, double[] x1, double[] x2) {
      if (debug) {
         System.err.println("dsortr");
      }

      this.dsortr(which, apply, n, x1, 1, x2, 2);
   }

   public void dsortr(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2) {
      if (debug) {
         System.err.println("dsortr");
      }

      this.dsortrK(which, apply, n, x1, offsetx1, x2, offsetx2);
   }

   protected abstract void dsortrK(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7);

   public void ssortr(String which, boolean apply, int n, float[] x1, float[] x2) {
      if (debug) {
         System.err.println("ssortr");
      }

      this.ssortr(which, apply, n, x1, 1, x2, 2);
   }

   public void ssortr(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2) {
      if (debug) {
         System.err.println("ssortr");
      }

      this.ssortrK(which, apply, n, x1, offsetx1, x2, offsetx2);
   }

   protected abstract void ssortrK(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7);

   public void dstatn() {
      if (debug) {
         System.err.println("dstatn");
      }

      this.dstatnK();
   }

   protected abstract void dstatnK();

   public void sstatn() {
      if (debug) {
         System.err.println("sstatn");
      }

      this.sstatnK();
   }

   protected abstract void sstatnK();

   public void dstats() {
      if (debug) {
         System.err.println("dstats");
      }

      this.dstatsK();
   }

   protected abstract void dstatsK();

   public void sstats() {
      if (debug) {
         System.err.println("sstats");
      }

      this.sstatsK();
   }

   protected abstract void sstatsK();

   public void dstqrb(int n, double[] d, double[] e, double[] z, double[] work, intW info) {
      if (debug) {
         System.err.println("dstqrb");
      }

      this.dstqrb(n, d, 0, e, 0, z, 0, work, 0, info);
   }

   public void dstqrb(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dstqrb");
      }

      this.dstqrbK(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
   }

   protected abstract void dstqrbK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   public void sstqrb(int n, float[] d, float[] e, float[] z, float[] work, intW info) {
      if (debug) {
         System.err.println("sstqrb");
      }

      this.sstqrb(n, d, 0, e, 0, z, 0, work, 0, info);
   }

   public void sstqrb(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sstqrb");
      }

      this.sstqrbK(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
   }

   protected abstract void sstqrbK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   public int icnteq(int n, int[] array, int value) {
      if (debug) {
         System.err.println("icnteq");
      }

      return this.icnteq(n, array, 0, value);
   }

   public int icnteq(int n, int[] array, int offsetarray, int value) {
      if (debug) {
         System.err.println("icnteq");
      }

      return this.icnteqK(n, array, offsetarray, value);
   }

   protected abstract int icnteqK(int var1, int[] var2, int var3, int var4);

   public void icopy(int n, int[] lx, int incx, int[] ly, int incy) {
      if (debug) {
         System.err.println("icopy");
      }

      this.icopy(n, lx, 0, incx, ly, 0, incy);
   }

   public void icopy(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy) {
      if (debug) {
         System.err.println("icopy");
      }

      this.icopyK(n, lx, offsetlx, incx, ly, offsetly, incy);
   }

   protected abstract void icopyK(int var1, int[] var2, int var3, int var4, int[] var5, int var6, int var7);

   public void iset(int n, int value, int[] array, int inc) {
      if (debug) {
         System.err.println("iset");
      }

      this.iset(n, value, array, 0, inc);
   }

   public void iset(int n, int value, int[] array, int offsetarray, int inc) {
      if (debug) {
         System.err.println("iset");
      }

      this.isetK(n, value, array, offsetarray, inc);
   }

   protected abstract void isetK(int var1, int var2, int[] var3, int var4, int var5);

   public void iswap(int n, int[] sx, int incx, int[] sy, int incy) {
      if (debug) {
         System.err.println("iswap");
      }

      this.iswap(n, sx, 0, incx, sy, 0, incy);
   }

   public void iswap(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy) {
      if (debug) {
         System.err.println("iswap");
      }

      this.iswapK(n, sx, offsetsx, incx, sy, offsetsy, incy);
   }

   protected abstract void iswapK(int var1, int[] var2, int var3, int var4, int[] var5, int var6, int var7);

   public void second(floatW t) {
      if (debug) {
         System.err.println("second");
      }

      this.secondK(t);
   }

   protected abstract void secondK(floatW var1);

   public boolean lsame(String ca, String cb) {
      if (debug) {
         System.err.println("lsame");
      }

      return ca != null && ca.regionMatches(true, 0, cb, 0, ca.length());
   }
}
