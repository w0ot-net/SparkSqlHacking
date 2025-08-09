package dev.ludovic.netlib.arpack;

import org.netlib.arpack.Dgetv0;
import org.netlib.arpack.Dlaqrb;
import org.netlib.arpack.Dmout;
import org.netlib.arpack.Dnaitr;
import org.netlib.arpack.Dnapps;
import org.netlib.arpack.Dnaup2;
import org.netlib.arpack.Dnaupd;
import org.netlib.arpack.Dnconv;
import org.netlib.arpack.Dneigh;
import org.netlib.arpack.Dneupd;
import org.netlib.arpack.Dngets;
import org.netlib.arpack.Dsaitr;
import org.netlib.arpack.Dsapps;
import org.netlib.arpack.Dsaup2;
import org.netlib.arpack.Dsaupd;
import org.netlib.arpack.Dsconv;
import org.netlib.arpack.Dseigt;
import org.netlib.arpack.Dsesrt;
import org.netlib.arpack.Dseupd;
import org.netlib.arpack.Dsgets;
import org.netlib.arpack.Dsortc;
import org.netlib.arpack.Dsortr;
import org.netlib.arpack.Dstatn;
import org.netlib.arpack.Dstats;
import org.netlib.arpack.Dstqrb;
import org.netlib.arpack.Dvout;
import org.netlib.arpack.Icnteq;
import org.netlib.arpack.Icopy;
import org.netlib.arpack.Iset;
import org.netlib.arpack.Iswap;
import org.netlib.arpack.Ivout;
import org.netlib.arpack.Second;
import org.netlib.arpack.Sgetv0;
import org.netlib.arpack.Slaqrb;
import org.netlib.arpack.Smout;
import org.netlib.arpack.Snaitr;
import org.netlib.arpack.Snapps;
import org.netlib.arpack.Snaup2;
import org.netlib.arpack.Snaupd;
import org.netlib.arpack.Snconv;
import org.netlib.arpack.Sneigh;
import org.netlib.arpack.Sneupd;
import org.netlib.arpack.Sngets;
import org.netlib.arpack.Ssaitr;
import org.netlib.arpack.Ssapps;
import org.netlib.arpack.Ssaup2;
import org.netlib.arpack.Ssaupd;
import org.netlib.arpack.Ssconv;
import org.netlib.arpack.Sseigt;
import org.netlib.arpack.Ssesrt;
import org.netlib.arpack.Sseupd;
import org.netlib.arpack.Ssgets;
import org.netlib.arpack.Ssortc;
import org.netlib.arpack.Ssortr;
import org.netlib.arpack.Sstatn;
import org.netlib.arpack.Sstats;
import org.netlib.arpack.Sstqrb;
import org.netlib.arpack.Svout;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

final class F2jARPACK extends AbstractARPACK implements JavaARPACK {
   private static final F2jARPACK instance = new F2jARPACK();

   protected F2jARPACK() {
   }

   public static JavaARPACK getInstance() {
      return instance;
   }

   protected void dmoutK(int lout, int m, int n, double[] a, int offseta, int lda, int idigit, String ifmt) {
      Dmout.dmout(lout, m, n, a, offseta, lda, idigit, ifmt);
   }

   protected void smoutK(int lout, int m, int n, float[] a, int offseta, int lda, int idigit, String ifmt) {
      Smout.smout(lout, m, n, a, offseta, lda, idigit, ifmt);
   }

   protected void dvoutK(int lout, int n, double[] sx, int offsetsx, int idigit, String ifmt) {
      Dvout.dvout(lout, n, sx, offsetsx, idigit, ifmt);
   }

   protected void svoutK(int lout, int n, float[] sx, int offsetsx, int idigit, String ifmt) {
      Svout.svout(lout, n, sx, offsetsx, idigit, ifmt);
   }

   protected void ivoutK(int lout, int n, int[] ix, int offsetix, int idigit, String ifmt) {
      Ivout.ivout(lout, n, ix, offsetix, idigit, ifmt);
   }

   protected void dgetv0K(intW ido, String bmat, int itry, boolean initv, int n, int j, double[] v, int offsetv, int ldv, double[] resid, int offsetresid, doubleW rnorm, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW ierr) {
      Dgetv0.dgetv0(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
   }

   protected void sgetv0K(intW ido, String bmat, int itry, boolean initv, int n, int j, float[] v, int offsetv, int ldv, float[] resid, int offsetresid, floatW rnorm, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW ierr) {
      Sgetv0.sgetv0(ido, bmat, itry, initv, n, j, v, offsetv, ldv, resid, offsetresid, rnorm, ipntr, offsetipntr, workd, offsetworkd, ierr);
   }

   protected void dlaqrbK(boolean wantt, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, intW info) {
      Dlaqrb.dlaqrb(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
   }

   protected void slaqrbK(boolean wantt, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, intW info) {
      Slaqrb.slaqrb(wantt, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, info);
   }

   protected void dnaitrK(intW ido, String bmat, int n, int k, int np, int nb, double[] resid, int offsetresid, doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      Dnaitr.dnaitr(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void snaitrK(intW ido, String bmat, int n, int k, int np, int nb, float[] resid, int offsetresid, floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      Snaitr.snaitr(ido, bmat, n, k, np, nb, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void dnappsK(int n, intW kev, int np, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, double[] workd, int offsetworkd) {
      Dnapps.dnapps(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
   }

   protected void snappsK(int n, intW kev, int np, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, float[] workd, int offsetworkd) {
      Snapps.snapps(n, kev, np, shiftr, offsetshiftr, shifti, offsetshifti, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workl, offsetworkl, workd, offsetworkd);
   }

   protected void dnaup2K(intW ido, String bmat, int n, String which, intW nev, intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      Dnaup2.dnaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void snaup2K(intW ido, String bmat, int n, String which, intW nev, intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      Snaup2.snaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void dnaupdK(intW ido, String bmat, int n, String which, int nev, doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      Dnaupd.dnaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void snaupdK(intW ido, String bmat, int n, String which, int nev, floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      Snaupd.snaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void dnconvK(int n, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double tol, intW nconv) {
      Dnconv.dnconv(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
   }

   protected void snconvK(int n, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float tol, intW nconv) {
      Snconv.snconv(n, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, tol, nconv);
   }

   protected void dsconvK(int n, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double tol, intW nconv) {
      Dsconv.dsconv(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
   }

   protected void ssconvK(int n, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float tol, intW nconv) {
      Ssconv.ssconv(n, ritz, offsetritz, bounds, offsetbounds, tol, nconv);
   }

   protected void dneighK(double rnorm, intW n, double[] h, int offseth, int ldh, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, intW ierr) {
      Dneigh.dneigh(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
   }

   protected void sneighK(float rnorm, intW n, float[] h, int offseth, int ldh, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, intW ierr) {
      Sneigh.sneigh(rnorm, n, h, offseth, ldh, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ierr);
   }

   protected void dneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] dr, int offsetdr, double[] di, int offsetdi, double[] z, int offsetz, int ldz, double sigmar, double sigmai, double[] workev, int offsetworkev, String bmat, int n, String which, intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      Dneupd.dneupd(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void sneupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] dr, int offsetdr, float[] di, int offsetdi, float[] z, int offsetz, int ldz, float sigmar, float sigmai, float[] workev, int offsetworkev, String bmat, int n, String which, intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      Sneupd.sneupd(rvec, howmny, select, offsetselect, dr, offsetdr, di, offsetdi, z, offsetz, ldz, sigmar, sigmai, workev, offsetworkev, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void dngetsK(int ishift, String which, intW kev, intW np, double[] ritzr, int offsetritzr, double[] ritzi, int offsetritzi, double[] bounds, int offsetbounds, double[] shiftr, int offsetshiftr, double[] shifti, int offsetshifti) {
      Dngets.dngets(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
   }

   protected void sngetsK(int ishift, String which, intW kev, intW np, float[] ritzr, int offsetritzr, float[] ritzi, int offsetritzi, float[] bounds, int offsetbounds, float[] shiftr, int offsetshiftr, float[] shifti, int offsetshifti) {
      Sngets.sngets(ishift, which, kev, np, ritzr, offsetritzr, ritzi, offsetritzi, bounds, offsetbounds, shiftr, offsetshiftr, shifti, offsetshifti);
   }

   protected void dsaitrK(intW ido, String bmat, int n, int k, int np, int mode, double[] resid, int offsetresid, doubleW rnorm, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      Dsaitr.dsaitr(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void ssaitrK(intW ido, String bmat, int n, int k, int np, int mode, float[] resid, int offsetresid, floatW rnorm, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      Ssaitr.ssaitr(ido, bmat, n, k, np, mode, resid, offsetresid, rnorm, v, offsetv, ldv, h, offseth, ldh, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void dsappsK(int n, int kev, int np, double[] shift, int offsetshift, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] resid, int offsetresid, double[] q, int offsetq, int ldq, double[] workd, int offsetworkd) {
      Dsapps.dsapps(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
   }

   protected void ssappsK(int n, int kev, int np, float[] shift, int offsetshift, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] resid, int offsetresid, float[] q, int offsetq, int ldq, float[] workd, int offsetworkd) {
      Ssapps.ssapps(n, kev, np, shift, offsetshift, v, offsetv, ldv, h, offseth, ldh, resid, offsetresid, q, offsetq, ldq, workd, offsetworkd);
   }

   protected void dsaup2K(intW ido, String bmat, int n, String which, intW nev, intW np, double tol, double[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, double[] v, int offsetv, int ldv, double[] h, int offseth, int ldh, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] q, int offsetq, int ldq, double[] workl, int offsetworkl, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, intW info) {
      Dsaup2.dsaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void ssaup2K(intW ido, String bmat, int n, String which, intW nev, intW np, float tol, float[] resid, int offsetresid, int mode, int iupd, int ishift, intW mxiter, float[] v, int offsetv, int ldv, float[] h, int offseth, int ldh, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] q, int offsetq, int ldq, float[] workl, int offsetworkl, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, intW info) {
      Ssaup2.ssaup2(ido, bmat, n, which, nev, np, tol, resid, offsetresid, mode, iupd, ishift, mxiter, v, offsetv, ldv, h, offseth, ldh, ritz, offsetritz, bounds, offsetbounds, q, offsetq, ldq, workl, offsetworkl, ipntr, offsetipntr, workd, offsetworkd, info);
   }

   protected void dseigtK(double rnorm, int n, double[] h, int offseth, int ldh, double[] eig, int offseteig, double[] bounds, int offsetbounds, double[] workl, int offsetworkl, intW ierr) {
      Dseigt.dseigt(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
   }

   protected void sseigtK(float rnorm, int n, float[] h, int offseth, int ldh, float[] eig, int offseteig, float[] bounds, int offsetbounds, float[] workl, int offsetworkl, intW ierr) {
      Sseigt.sseigt(rnorm, n, h, offseth, ldh, eig, offseteig, bounds, offsetbounds, workl, offsetworkl, ierr);
   }

   protected void dsesrtK(String which, boolean apply, int n, double[] x, int offsetx, int na, double[] a, int offseta, int lda) {
      Dsesrt.dsesrt(which, apply, n, x, offsetx, na, a, offseta, lda);
   }

   protected void ssesrtK(String which, boolean apply, int n, float[] x, int offsetx, int na, float[] a, int offseta, int lda) {
      Ssesrt.ssesrt(which, apply, n, x, offsetx, na, a, offseta, lda);
   }

   protected void dsaupdK(intW ido, String bmat, int n, String which, int nev, doubleW tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      Dsaupd.dsaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void ssaupdK(intW ido, String bmat, int n, String which, int nev, floatW tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      Ssaupd.ssaupd(ido, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void dseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, double[] d, int offsetd, double[] z, int offsetz, int ldz, double sigma, String bmat, int n, String which, intW nev, double tol, double[] resid, int offsetresid, int ncv, double[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, double[] workd, int offsetworkd, double[] workl, int offsetworkl, int lworkl, intW info) {
      Dseupd.dseupd(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void sseupdK(boolean rvec, String howmny, boolean[] select, int offsetselect, float[] d, int offsetd, float[] z, int offsetz, int ldz, float sigma, String bmat, int n, String which, intW nev, float tol, float[] resid, int offsetresid, int ncv, float[] v, int offsetv, int ldv, int[] iparam, int offsetiparam, int[] ipntr, int offsetipntr, float[] workd, int offsetworkd, float[] workl, int offsetworkl, int lworkl, intW info) {
      Sseupd.sseupd(rvec, howmny, select, offsetselect, d, offsetd, z, offsetz, ldz, sigma, bmat, n, which, nev, tol, resid, offsetresid, ncv, v, offsetv, ldv, iparam, offsetiparam, ipntr, offsetipntr, workd, offsetworkd, workl, offsetworkl, lworkl, info);
   }

   protected void dsgetsK(int ishift, String which, intW kev, intW np, double[] ritz, int offsetritz, double[] bounds, int offsetbounds, double[] shifts, int offsetshifts) {
      Dsgets.dsgets(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
   }

   protected void ssgetsK(int ishift, String which, intW kev, intW np, float[] ritz, int offsetritz, float[] bounds, int offsetbounds, float[] shifts, int offsetshifts) {
      Ssgets.ssgets(ishift, which, kev, np, ritz, offsetritz, bounds, offsetbounds, shifts, offsetshifts);
   }

   protected void dsortcK(String which, boolean apply, int n, double[] xreal, int offsetxreal, double[] ximag, int offsetximag, double[] y, int offsety) {
      Dsortc.dsortc(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
   }

   protected void ssortcK(String which, boolean apply, int n, float[] xreal, int offsetxreal, float[] ximag, int offsetximag, float[] y, int offsety) {
      Ssortc.ssortc(which, apply, n, xreal, offsetxreal, ximag, offsetximag, y, offsety);
   }

   protected void dsortrK(String which, boolean apply, int n, double[] x1, int offsetx1, double[] x2, int offsetx2) {
      Dsortr.dsortr(which, apply, n, x1, offsetx1, x2, offsetx2);
   }

   protected void ssortrK(String which, boolean apply, int n, float[] x1, int offsetx1, float[] x2, int offsetx2) {
      Ssortr.ssortr(which, apply, n, x1, offsetx1, x2, offsetx2);
   }

   protected void dstatnK() {
      Dstatn.dstatn();
   }

   protected void sstatnK() {
      Sstatn.sstatn();
   }

   protected void dstatsK() {
      Dstats.dstats();
   }

   protected void sstatsK() {
      Sstats.sstats();
   }

   protected void dstqrbK(int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, double[] work, int offsetwork, intW info) {
      Dstqrb.dstqrb(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
   }

   protected void sstqrbK(int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, float[] work, int offsetwork, intW info) {
      Sstqrb.sstqrb(n, d, offsetd, e, offsete, z, offsetz, work, offsetwork, info);
   }

   protected int icnteqK(int n, int[] array, int offsetarray, int value) {
      return Icnteq.icnteq(n, array, offsetarray, value);
   }

   protected void icopyK(int n, int[] lx, int offsetlx, int incx, int[] ly, int offsetly, int incy) {
      Icopy.icopy(n, lx, offsetlx, incx, ly, offsetly, incy);
   }

   protected void isetK(int n, int value, int[] array, int offsetarray, int inc) {
      Iset.iset(n, value, array, offsetarray, inc);
   }

   protected void iswapK(int n, int[] sx, int offsetsx, int incx, int[] sy, int offsetsy, int incy) {
      Iswap.iswap(n, sx, offsetsx, incx, sy, offsetsy, incy);
   }

   protected void secondK(floatW t) {
      Second.second(t);
   }
}
