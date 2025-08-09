package dev.ludovic.netlib.lapack;

import java.util.Objects;
import org.netlib.util.StringW;
import org.netlib.util.booleanW;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

abstract class AbstractLAPACK implements LAPACK {
   private static final boolean debug = System.getProperty("dev.ludovic.netlib.lapack.debug", "false").equals("true");

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

   public void dbdsdc(String uplo, String compq, int n, double[] d, double[] e, double[] u, int ldu, double[] vt, int ldvt, double[] q, int[] iq, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dbdsdc");
      }

      this.dbdsdc(uplo, compq, n, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, q, 0, iq, 0, work, 0, iwork, 0, info);
   }

   public void dbdsdc(String uplo, String compq, int n, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] q, int offsetq, int[] iq, int offsetiq, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dbdsdc");
      }

      this.dbdsdcK(uplo, compq, n, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, q, offsetq, iq, offsetiq, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dbdsdcK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   public void dbdsqr(String uplo, int n, int ncvt, int nru, int ncc, double[] d, double[] e, double[] vt, int ldvt, double[] u, int ldu, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dbdsqr");
      }

      this.dbdsqr(uplo, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
   }

   public void dbdsqr(String uplo, int n, int ncvt, int nru, int ncc, double[] d, int offsetd, double[] e, int offsete, double[] vt, int offsetvt, int ldvt, double[] u, int offsetu, int ldu, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dbdsqr");
      }

      this.dbdsqrK(uplo, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dbdsqrK(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, intW var21);

   public void ddisna(String job, int m, int n, double[] d, double[] sep, intW info) {
      if (debug) {
         System.err.println("ddisna");
      }

      this.ddisna(job, m, n, d, 0, sep, 0, info);
   }

   public void ddisna(String job, int m, int n, double[] d, int offsetd, double[] sep, int offsetsep, intW info) {
      if (debug) {
         System.err.println("ddisna");
      }

      this.ddisnaK(job, m, n, d, offsetd, sep, offsetsep, info);
   }

   protected abstract void ddisnaK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   public void dgbbrd(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int ldab, double[] d, double[] e, double[] q, int ldq, double[] pt, int ldpt, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dgbbrd");
      }

      this.dgbbrd(vect, m, n, ncc, kl, ku, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, pt, 0, ldpt, c, 0, Ldc, work, 0, info);
   }

   public void dgbbrd(String vect, int m, int n, int ncc, int kl, int ku, double[] ab, int offsetab, int ldab, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] pt, int offsetpt, int ldpt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgbbrd");
      }

      this.dgbbrdK(vect, m, n, ncc, kl, ku, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, pt, offsetpt, ldpt, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dgbbrdK(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, intW var25);

   public void dgbcon(String norm, int n, int kl, int ku, double[] ab, int ldab, int[] ipiv, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgbcon");
      }

      this.dgbcon(norm, n, kl, ku, ab, 0, ldab, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dgbcon(String norm, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgbcon");
      }

      this.dgbconK(norm, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgbconK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, double var10, doubleW var12, double[] var13, int var14, int[] var15, int var16, intW var17);

   public void dgbequ(int m, int n, int kl, int ku, double[] ab, int ldab, double[] r, double[] c, doubleW rowcnd, doubleW colcnd, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dgbequ");
      }

      this.dgbequ(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, info);
   }

   public void dgbequ(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] r, int offsetr, double[] c, int offsetc, doubleW rowcnd, doubleW colcnd, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dgbequ");
      }

      this.dgbequK(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
   }

   protected abstract void dgbequK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, doubleW var13, doubleW var14, intW var15);

   public void dgbrfs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgbrfs");
      }

      this.dgbrfs(trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dgbrfs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgbrfs");
      }

      this.dgbrfsK(trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgbrfsK(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int[] var26, int var27, intW var28);

   public void dgbsv(int n, int kl, int ku, int nrhs, double[] ab, int ldab, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dgbsv");
      }

      this.dgbsv(n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
   }

   public void dgbsv(int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dgbsv");
      }

      this.dgbsvK(n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dgbsvK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   public void dgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, int[] ipiv, StringW equed, double[] r, double[] c, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgbsvx");
      }

      this.dgbsvx(fact, trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, StringW equed, double[] r, int offsetr, double[] c, int offsetc, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgbsvx");
      }

      this.dgbsvxK(fact, trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgbsvxK(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, StringW var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, doubleW var26, double[] var27, int var28, double[] var29, int var30, double[] var31, int var32, int[] var33, int var34, intW var35);

   public void dgbtf2(int m, int n, int kl, int ku, double[] ab, int ldab, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dgbtf2");
      }

      this.dgbtf2(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
   }

   public void dgbtf2(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dgbtf2");
      }

      this.dgbtf2K(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
   }

   protected abstract void dgbtf2K(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   public void dgbtrf(int m, int n, int kl, int ku, double[] ab, int ldab, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dgbtrf");
      }

      this.dgbtrf(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
   }

   public void dgbtrf(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dgbtrf");
      }

      this.dgbtrfK(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
   }

   protected abstract void dgbtrfK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   public void dgbtrs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int ldab, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dgbtrs");
      }

      this.dgbtrs(trans, n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
   }

   public void dgbtrs(String trans, int n, int kl, int ku, int nrhs, double[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dgbtrs");
      }

      this.dgbtrsK(trans, n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dgbtrsK(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, int[] var9, int var10, double[] var11, int var12, int var13, intW var14);

   public void dgebak(String job, String side, int n, int ilo, int ihi, double[] scale, int m, double[] v, int ldv, intW info) {
      if (debug) {
         System.err.println("dgebak");
      }

      this.dgebak(job, side, n, ilo, ihi, scale, 0, m, v, 0, ldv, info);
   }

   public void dgebak(String job, String side, int n, int ilo, int ihi, double[] scale, int offsetscale, int m, double[] v, int offsetv, int ldv, intW info) {
      if (debug) {
         System.err.println("dgebak");
      }

      this.dgebakK(job, side, n, ilo, ihi, scale, offsetscale, m, v, offsetv, ldv, info);
   }

   protected abstract void dgebakK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dgebal(String job, int n, double[] a, int lda, intW ilo, intW ihi, double[] scale, intW info) {
      if (debug) {
         System.err.println("dgebal");
      }

      this.dgebal(job, n, a, 0, lda, ilo, ihi, scale, 0, info);
   }

   public void dgebal(String job, int n, double[] a, int offseta, int lda, intW ilo, intW ihi, double[] scale, int offsetscale, intW info) {
      if (debug) {
         System.err.println("dgebal");
      }

      this.dgebalK(job, n, a, offseta, lda, ilo, ihi, scale, offsetscale, info);
   }

   protected abstract void dgebalK(String var1, int var2, double[] var3, int var4, int var5, intW var6, intW var7, double[] var8, int var9, intW var10);

   public void dgebd2(int m, int n, double[] a, int lda, double[] d, double[] e, double[] tauq, double[] taup, double[] work, intW info) {
      if (debug) {
         System.err.println("dgebd2");
      }

      this.dgebd2(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, info);
   }

   public void dgebd2(int m, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgebd2");
      }

      this.dgebd2K(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, info);
   }

   protected abstract void dgebd2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   public void dgebrd(int m, int n, double[] a, int lda, double[] d, double[] e, double[] tauq, double[] taup, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgebrd");
      }

      this.dgebrd(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, lwork, info);
   }

   public void dgebrd(int m, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgebrd");
      }

      this.dgebrdK(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, lwork, info);
   }

   protected abstract void dgebrdK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dgecon(String norm, int n, double[] a, int lda, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgecon");
      }

      this.dgecon(norm, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dgecon(String norm, int n, double[] a, int offseta, int lda, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgecon");
      }

      this.dgeconK(norm, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgeconK(String var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   public void dgeequ(int m, int n, double[] a, int lda, double[] r, double[] c, doubleW rowcnd, doubleW colcnd, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dgeequ");
      }

      this.dgeequ(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, info);
   }

   public void dgeequ(int m, int n, double[] a, int offseta, int lda, double[] r, int offsetr, double[] c, int offsetc, doubleW rowcnd, doubleW colcnd, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dgeequ");
      }

      this.dgeequK(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
   }

   protected abstract void dgeequK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, doubleW var10, doubleW var11, doubleW var12, intW var13);

   public void dgees(String jobvs, String sort, Object select, int n, double[] a, int lda, intW sdim, double[] wr, double[] wi, double[] vs, int ldvs, double[] work, int lwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("dgees");
      }

      this.dgees(jobvs, sort, select, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, work, 0, lwork, bwork, 0, info);
   }

   public void dgees(String jobvs, String sort, Object select, int n, double[] a, int offseta, int lda, intW sdim, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vs, int offsetvs, int ldvs, double[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("dgees");
      }

      this.dgeesK(jobvs, sort, select, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, work, offsetwork, lwork, bwork, offsetbwork, info);
   }

   protected abstract void dgeesK(String var1, String var2, Object var3, int var4, double[] var5, int var6, int var7, intW var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, boolean[] var19, int var20, intW var21);

   public void dgeesx(String jobvs, String sort, Object select, String sense, int n, double[] a, int lda, intW sdim, double[] wr, double[] wi, double[] vs, int ldvs, doubleW rconde, doubleW rcondv, double[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("dgeesx");
      }

      this.dgeesx(jobvs, sort, select, sense, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, rconde, rcondv, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
   }

   public void dgeesx(String jobvs, String sort, Object select, String sense, int n, double[] a, int offseta, int lda, intW sdim, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vs, int offsetvs, int ldvs, doubleW rconde, doubleW rcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("dgeesx");
      }

      this.dgeesxK(jobvs, sort, select, sense, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, rconde, rcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
   }

   protected abstract void dgeesxK(String var1, String var2, Object var3, String var4, int var5, double[] var6, int var7, int var8, intW var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, doubleW var17, doubleW var18, double[] var19, int var20, int var21, int[] var22, int var23, int var24, boolean[] var25, int var26, intW var27);

   public void dgeev(String jobvl, String jobvr, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeev");
      }

      this.dgeev(jobvl, jobvr, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
   }

   public void dgeev(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeev");
      }

      this.checkArgument("DGEEV", 1, this.lsame("N", jobvl) || this.lsame("V", jobvl));
      this.checkArgument("DGEEV", 2, this.lsame("N", jobvr) || this.lsame("V", jobvr));
      this.checkArgument("DGEEV", 3, n >= 0);
      this.checkArgument("DGEEV", 5, lda >= Math.max(1, n));
      this.checkArgument("DGEEV", 9, ldvl >= Math.max(1, this.lsame("V", jobvl) ? n : 1));
      this.checkArgument("DGEEV", 11, ldvr >= Math.max(1, this.lsame("V", jobvr) ? n : 1));
      this.checkArgument("DGEEV", 11, lwork == -1 || lwork >= Math.max(1, !this.lsame("V", jobvl) && !this.lsame("V", jobvr) ? 3 * n : 4 * n));
      this.requireNonNull(a);
      this.requireNonNull(wr);
      this.requireNonNull(wi);
      if (this.lsame("V", jobvl)) {
         this.requireNonNull(vl);
      }

      if (this.lsame("V", jobvr)) {
         this.requireNonNull(vr);
      }

      this.requireNonNull(work);
      this.requireNonNull(info);
      if (lwork != -1) {
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetwr + n - 1, wr.length);
         this.checkIndex(offsetwi + n - 1, wi.length);
         if (this.lsame("V", jobvl)) {
            this.checkIndex(offsetvl + n * ldvl - 1, vl.length);
         }

         if (this.lsame("V", jobvr)) {
            this.checkIndex(offsetvr + n * ldvr - 1, vr.length);
         }
      }

      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.dgeevK(jobvl, jobvr, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
   }

   protected abstract void dgeevK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, intW var20);

   public void dgeevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, intW ilo, intW ihi, double[] scale, doubleW abnrm, double[] rconde, double[] rcondv, double[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgeevx");
      }

      this.dgeevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, scale, 0, abnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, info);
   }

   public void dgeevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int offseta, int lda, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, intW ilo, intW ihi, double[] scale, int offsetscale, doubleW abnrm, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgeevx");
      }

      this.dgeevxK(balanc, jobvl, jobvr, sense, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, scale, offsetscale, abnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void dgeevxK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19, intW var20, double[] var21, int var22, doubleW var23, double[] var24, int var25, double[] var26, int var27, double[] var28, int var29, int var30, int[] var31, int var32, intW var33);

   public void dgegs(String jobvsl, String jobvsr, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vsl, int ldvsl, double[] vsr, int ldvsr, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgegs");
      }

      this.dgegs(jobvsl, jobvsr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, info);
   }

   public void dgegs(String jobvsl, String jobvsr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgegs");
      }

      this.dgegsK(jobvsl, jobvsr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, info);
   }

   protected abstract void dgegsK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   public void dgegv(String jobvl, String jobvr, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgegv");
      }

      this.dgegv(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
   }

   public void dgegv(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgegv");
      }

      this.dgegvK(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
   }

   protected abstract void dgegvK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   public void dgehd2(int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dgehd2");
      }

      this.dgehd2(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dgehd2(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgehd2");
      }

      this.dgehd2K(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dgehd2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   public void dgehrd(int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgehrd");
      }

      this.dgehrd(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dgehrd(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgehrd");
      }

      this.dgehrdK(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dgehrdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dgelq2(int m, int n, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dgelq2");
      }

      this.dgelq2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dgelq2(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgelq2");
      }

      this.dgelq2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dgelq2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   public void dgelqf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgelqf");
      }

      this.dgelqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dgelqf(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgelqf");
      }

      this.dgelqfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dgelqfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dgels(String trans, int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgels");
      }

      this.dgels(trans, m, n, nrhs, a, 0, lda, b, 0, ldb, work, 0, lwork, info);
   }

   public void dgels(String trans, int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgels");
      }

      this.checkArgument("DGELS", 1, this.lsame("N", trans) || this.lsame("T", trans));
      this.checkArgument("DGELS", 2, m >= 0);
      this.checkArgument("DGELS", 3, n >= 0);
      this.checkArgument("DGELS", 4, nrhs >= 0);
      this.checkArgument("DGELS", 6, lda >= Math.max(1, m));
      this.checkArgument("DGELS", 8, ldb >= Math.max(1, Math.max(m, n)));
      this.checkArgument("DGELS", 10, lwork == -1 || lwork >= Math.max(1, Math.min(m, n) + Math.max(Math.min(m, n), nrhs)));
      this.requireNonNull(a);
      this.requireNonNull(b);
      this.requireNonNull(work);
      this.requireNonNull(info);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetb + nrhs * (this.lsame("N", trans) ? m : n) - 1, b.length);
      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.dgelsK(trans, m, n, nrhs, a, offseta, lda, b, offsetb, ldb, work, offsetwork, lwork, info);
   }

   protected abstract void dgelsK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, intW var14);

   public void dgelsd(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] s, double rcond, intW rank, double[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgelsd");
      }

      this.dgelsd(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, iwork, 0, info);
   }

   public void dgelsd(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] s, int offsets, double rcond, intW rank, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgelsd");
      }

      this.dgelsdK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void dgelsdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   public void dgelss(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] s, double rcond, intW rank, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgelss");
      }

      this.dgelss(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, info);
   }

   public void dgelss(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] s, int offsets, double rcond, intW rank, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgelss");
      }

      this.dgelssK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, info);
   }

   protected abstract void dgelssK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, intW var18);

   public void dgelsx(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] jpvt, double rcond, intW rank, double[] work, intW info) {
      if (debug) {
         System.err.println("dgelsx");
      }

      this.dgelsx(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, info);
   }

   public void dgelsx(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, double rcond, intW rank, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgelsx");
      }

      this.dgelsxK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, info);
   }

   protected abstract void dgelsxK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double var12, intW var14, double[] var15, int var16, intW var17);

   public void dgelsy(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] jpvt, double rcond, intW rank, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgelsy");
      }

      this.dgelsy(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, lwork, info);
   }

   public void dgelsy(int m, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, double rcond, intW rank, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgelsy");
      }

      this.dgelsyK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, lwork, info);
   }

   protected abstract void dgelsyK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, intW var18);

   public void dgeql2(int m, int n, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dgeql2");
      }

      this.dgeql2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dgeql2(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgeql2");
      }

      this.dgeql2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dgeql2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   public void dgeqlf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeqlf");
      }

      this.dgeqlf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dgeqlf(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeqlf");
      }

      this.dgeqlfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dgeqlfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dgeqp3(int m, int n, double[] a, int lda, int[] jpvt, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeqp3");
      }

      this.dgeqp3(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, lwork, info);
   }

   public void dgeqp3(int m, int n, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeqp3");
      }

      this.checkArgument("DGEQP3", 1, m >= 0);
      this.checkArgument("DGEQP3", 2, n >= 0);
      this.checkArgument("DGEQP3", 4, lda >= Math.max(1, m));
      this.checkArgument("DGEQP3", 8, lwork == -1 || lwork >= Math.max(1, 3 * n + 1));
      this.requireNonNull(a);
      this.requireNonNull(jpvt);
      this.requireNonNull(tau);
      this.requireNonNull(work);
      this.requireNonNull(info);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetjpvt + n - 1, jpvt.length);
      this.checkIndex(offsettau + Math.min(m, n) - 1, tau.length);
      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.dgeqp3K(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dgeqp3K(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   public void dgeqpf(int m, int n, double[] a, int lda, int[] jpvt, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dgeqpf");
      }

      this.dgeqpf(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, info);
   }

   public void dgeqpf(int m, int n, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgeqpf");
      }

      this.dgeqpfK(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dgeqpfK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12);

   public void dgeqr2(int m, int n, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dgeqr2");
      }

      this.dgeqr2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dgeqr2(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgeqr2");
      }

      this.dgeqr2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dgeqr2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   public void dgeqrf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeqrf");
      }

      this.dgeqrf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dgeqrf(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgeqrf");
      }

      this.checkArgument("DGEQRF", 1, m >= 0);
      this.checkArgument("DGEQRF", 2, n >= 0);
      this.checkArgument("DGEQRF", 4, lda >= Math.max(1, m));
      this.checkArgument("DGEQRF", 7, lwork == -1 || lwork >= Math.max(1, n));
      this.requireNonNull(a);
      this.requireNonNull(tau);
      this.requireNonNull(work);
      this.requireNonNull(info);
      if (lwork != -1) {
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsettau + Math.min(m, n) - 1, tau.length);
      }

      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.dgeqrfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dgeqrfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dgerfs(String trans, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgerfs");
      }

      this.dgerfs(trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dgerfs(String trans, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgerfs");
      }

      this.dgerfsK(trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgerfsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   public void dgerq2(int m, int n, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dgerq2");
      }

      this.dgerq2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dgerq2(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dgerq2");
      }

      this.dgerq2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dgerq2K(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   public void dgerqf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgerqf");
      }

      this.dgerqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dgerqf(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgerqf");
      }

      this.dgerqfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dgerqfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dgesc2(int n, double[] a, int lda, double[] rhs, int[] ipiv, int[] jpiv, doubleW scale) {
      if (debug) {
         System.err.println("dgesc2");
      }

      this.dgesc2(n, a, 0, lda, rhs, 0, ipiv, 0, jpiv, 0, scale);
   }

   public void dgesc2(int n, double[] a, int offseta, int lda, double[] rhs, int offsetrhs, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, doubleW scale) {
      if (debug) {
         System.err.println("dgesc2");
      }

      this.dgesc2K(n, a, offseta, lda, rhs, offsetrhs, ipiv, offsetipiv, jpiv, offsetjpiv, scale);
   }

   protected abstract void dgesc2K(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int[] var7, int var8, int[] var9, int var10, doubleW var11);

   public void dgesdd(String jobz, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgesdd");
      }

      this.dgesdd(jobz, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, iwork, 0, info);
   }

   public void dgesdd(String jobz, int m, int n, double[] a, int offseta, int lda, double[] s, int offsets, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgesdd");
      }

      this.dgesddK(jobz, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void dgesddK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   public void dgesv(int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dgesv");
      }

      this.dgesv(n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
   }

   public void dgesv(int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dgesv");
      }

      this.dgesvK(n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dgesvK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dgesvd(String jobu, String jobvt, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgesvd");
      }

      this.dgesvd(jobu, jobvt, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, info);
   }

   public void dgesvd(String jobu, String jobvt, int m, int n, double[] a, int offseta, int lda, double[] s, int offsets, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgesvd");
      }

      this.dgesvdK(jobu, jobvt, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, info);
   }

   protected abstract void dgesvdK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19);

   public void dgesvx(String fact, String trans, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, StringW equed, double[] r, double[] c, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgesvx");
      }

      this.dgesvx(fact, trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dgesvx(String fact, String trans, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, StringW equed, double[] r, int offsetr, double[] c, int offsetc, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgesvx");
      }

      this.dgesvxK(fact, trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgesvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, int[] var11, int var12, StringW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, doubleW var24, double[] var25, int var26, double[] var27, int var28, double[] var29, int var30, int[] var31, int var32, intW var33);

   public void dgetc2(int n, double[] a, int lda, int[] ipiv, int[] jpiv, intW info) {
      if (debug) {
         System.err.println("dgetc2");
      }

      this.dgetc2(n, a, 0, lda, ipiv, 0, jpiv, 0, info);
   }

   public void dgetc2(int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, intW info) {
      if (debug) {
         System.err.println("dgetc2");
      }

      this.dgetc2K(n, a, offseta, lda, ipiv, offsetipiv, jpiv, offsetjpiv, info);
   }

   protected abstract void dgetc2K(int var1, double[] var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, intW var9);

   public void dgetf2(int m, int n, double[] a, int lda, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dgetf2");
      }

      this.dgetf2(m, n, a, 0, lda, ipiv, 0, info);
   }

   public void dgetf2(int m, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dgetf2");
      }

      this.dgetf2K(m, n, a, offseta, lda, ipiv, offsetipiv, info);
   }

   protected abstract void dgetf2K(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   public void dgetrf(int m, int n, double[] a, int lda, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dgetrf");
      }

      this.dgetrf(m, n, a, 0, lda, ipiv, 0, info);
   }

   public void dgetrf(int m, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dgetrf");
      }

      this.checkArgument("DGETRF", 1, m >= 0);
      this.checkArgument("DGETRF", 2, n >= 0);
      this.checkArgument("DGETRF", 4, lda >= Math.max(1, m));
      this.requireNonNull(a);
      this.requireNonNull(ipiv);
      this.requireNonNull(info);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetipiv + Math.min(m, n) - 1, ipiv.length);
      this.dgetrfK(m, n, a, offseta, lda, ipiv, offsetipiv, info);
   }

   protected abstract void dgetrfK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   public void dgetri(int n, double[] a, int lda, int[] ipiv, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgetri");
      }

      this.dgetri(n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
   }

   public void dgetri(int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgetri");
      }

      this.dgetriK(n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
   }

   protected abstract void dgetriK(int var1, double[] var2, int var3, int var4, int[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dgetrs(String trans, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dgetrs");
      }

      this.dgetrs(trans, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
   }

   public void dgetrs(String trans, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dgetrs");
      }

      this.dgetrsK(trans, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dgetrsK(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dggbak(String job, String side, int n, int ilo, int ihi, double[] lscale, double[] rscale, int m, double[] v, int ldv, intW info) {
      if (debug) {
         System.err.println("dggbak");
      }

      this.dggbak(job, side, n, ilo, ihi, lscale, 0, rscale, 0, m, v, 0, ldv, info);
   }

   public void dggbak(String job, String side, int n, int ilo, int ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, int m, double[] v, int offsetv, int ldv, intW info) {
      if (debug) {
         System.err.println("dggbak");
      }

      this.dggbakK(job, side, n, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, m, v, offsetv, ldv, info);
   }

   protected abstract void dggbakK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, intW var14);

   public void dggbal(String job, int n, double[] a, int lda, double[] b, int ldb, intW ilo, intW ihi, double[] lscale, double[] rscale, double[] work, intW info) {
      if (debug) {
         System.err.println("dggbal");
      }

      this.dggbal(job, n, a, 0, lda, b, 0, ldb, ilo, ihi, lscale, 0, rscale, 0, work, 0, info);
   }

   public void dggbal(String job, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW ilo, intW ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dggbal");
      }

      this.dggbalK(job, n, a, offseta, lda, b, offsetb, ldb, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, work, offsetwork, info);
   }

   protected abstract void dggbalK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9, intW var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, intW var17);

   public void dgges(String jobvsl, String jobvsr, String sort, Object selctg, int n, double[] a, int lda, double[] b, int ldb, intW sdim, double[] alphar, double[] alphai, double[] beta, double[] vsl, int ldvsl, double[] vsr, int ldvsr, double[] work, int lwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("dgges");
      }

      this.dgges(jobvsl, jobvsr, sort, selctg, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, bwork, 0, info);
   }

   public void dgges(String jobvsl, String jobvsr, String sort, Object selctg, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW sdim, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("dgges");
      }

      this.dggesK(jobvsl, jobvsr, sort, selctg, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, bwork, offsetbwork, info);
   }

   protected abstract void dggesK(String var1, String var2, String var3, Object var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, boolean[] var28, int var29, intW var30);

   public void dggesx(String jobvsl, String jobvsr, String sort, Object selctg, String sense, int n, double[] a, int lda, double[] b, int ldb, intW sdim, double[] alphar, double[] alphai, double[] beta, double[] vsl, int ldvsl, double[] vsr, int ldvsr, double[] rconde, double[] rcondv, double[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("dggesx");
      }

      this.dggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
   }

   public void dggesx(String jobvsl, String jobvsr, String sort, Object selctg, String sense, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW sdim, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vsl, int offsetvsl, int ldvsl, double[] vsr, int offsetvsr, int ldvsr, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("dggesx");
      }

      this.dggesxK(jobvsl, jobvsr, sort, selctg, sense, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
   }

   protected abstract void dggesxK(String var1, String var2, String var3, Object var4, String var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, intW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int var32, int[] var33, int var34, int var35, boolean[] var36, int var37, intW var38);

   public void dggev(String jobvl, String jobvr, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dggev");
      }

      this.dggev(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
   }

   public void dggev(String jobvl, String jobvr, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dggev");
      }

      this.dggevK(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
   }

   protected abstract void dggevK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   public void dggevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] vl, int ldvl, double[] vr, int ldvr, intW ilo, intW ihi, double[] lscale, double[] rscale, doubleW abnrm, doubleW bbnrm, double[] rconde, double[] rcondv, double[] work, int lwork, int[] iwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("dggevx");
      }

      this.dggevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, lscale, 0, rscale, 0, abnrm, bbnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, bwork, 0, info);
   }

   public void dggevx(String balanc, String jobvl, String jobvr, String sense, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, intW ilo, intW ihi, double[] lscale, int offsetlscale, double[] rscale, int offsetrscale, doubleW abnrm, doubleW bbnrm, double[] rconde, int offsetrconde, double[] rcondv, int offsetrcondv, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("dggevx");
      }

      this.dggevxK(balanc, jobvl, jobvr, sense, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, abnrm, bbnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, bwork, offsetbwork, info);
   }

   protected abstract void dggevxK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, intW var24, intW var25, double[] var26, int var27, double[] var28, int var29, doubleW var30, doubleW var31, double[] var32, int var33, double[] var34, int var35, double[] var36, int var37, int var38, int[] var39, int var40, boolean[] var41, int var42, intW var43);

   public void dggglm(int n, int m, int p, double[] a, int lda, double[] b, int ldb, double[] d, double[] x, double[] y, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dggglm");
      }

      this.dggglm(n, m, p, a, 0, lda, b, 0, ldb, d, 0, x, 0, y, 0, work, 0, lwork, info);
   }

   public void dggglm(int n, int m, int p, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] d, int offsetd, double[] x, int offsetx, double[] y, int offsety, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dggglm");
      }

      this.dggglmK(n, m, p, a, offseta, lda, b, offsetb, ldb, d, offsetd, x, offsetx, y, offsety, work, offsetwork, lwork, info);
   }

   protected abstract void dggglmK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   public void dgghrd(String compq, String compz, int n, int ilo, int ihi, double[] a, int lda, double[] b, int ldb, double[] q, int ldq, double[] z, int ldz, intW info) {
      if (debug) {
         System.err.println("dgghrd");
      }

      this.dgghrd(compq, compz, n, ilo, ihi, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, info);
   }

   public void dgghrd(String compq, String compz, int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, intW info) {
      if (debug) {
         System.err.println("dgghrd");
      }

      this.dgghrdK(compq, compz, n, ilo, ihi, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, info);
   }

   protected abstract void dgghrdK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   public void dgglse(int m, int n, int p, double[] a, int lda, double[] b, int ldb, double[] c, double[] d, double[] x, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dgglse");
      }

      this.dgglse(m, n, p, a, 0, lda, b, 0, ldb, c, 0, d, 0, x, 0, work, 0, lwork, info);
   }

   public void dgglse(int m, int n, int p, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, double[] d, int offsetd, double[] x, int offsetx, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dgglse");
      }

      this.dgglseK(m, n, p, a, offseta, lda, b, offsetb, ldb, c, offsetc, d, offsetd, x, offsetx, work, offsetwork, lwork, info);
   }

   protected abstract void dgglseK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   public void dggqrf(int n, int m, int p, double[] a, int lda, double[] taua, double[] b, int ldb, double[] taub, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dggqrf");
      }

      this.dggqrf(n, m, p, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
   }

   public void dggqrf(int n, int m, int p, double[] a, int offseta, int lda, double[] taua, int offsettaua, double[] b, int offsetb, int ldb, double[] taub, int offsettaub, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dggqrf");
      }

      this.dggqrfK(n, m, p, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
   }

   protected abstract void dggqrfK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dggrqf(int m, int p, int n, double[] a, int lda, double[] taua, double[] b, int ldb, double[] taub, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dggrqf");
      }

      this.dggrqf(m, p, n, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
   }

   public void dggrqf(int m, int p, int n, double[] a, int offseta, int lda, double[] taua, int offsettaua, double[] b, int offsetb, int ldb, double[] taub, int offsettaub, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dggrqf");
      }

      this.dggrqfK(m, p, n, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
   }

   protected abstract void dggrqfK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dggsvd(String jobu, String jobv, String jobq, int m, int n, int p, intW k, intW l, double[] a, int lda, double[] b, int ldb, double[] alpha, double[] beta, double[] u, int ldu, double[] v, int ldv, double[] q, int ldq, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dggsvd");
      }

      this.dggsvd(jobu, jobv, jobq, m, n, p, k, l, a, 0, lda, b, 0, ldb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, iwork, 0, info);
   }

   public void dggsvd(String jobu, String jobv, String jobq, int m, int n, int p, intW k, intW l, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alpha, int offsetalpha, double[] beta, int offsetbeta, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dggsvd");
      }

      this.dggsvdK(jobu, jobv, jobq, m, n, p, k, l, a, offseta, lda, b, offsetb, ldb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dggsvdK(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, double[] var28, int var29, int[] var30, int var31, intW var32);

   public void dggsvp(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int lda, double[] b, int ldb, double tola, double tolb, intW k, intW l, double[] u, int ldu, double[] v, int ldv, double[] q, int ldq, int[] iwork, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dggsvp");
      }

      this.dggsvp(jobu, jobv, jobq, m, p, n, a, 0, lda, b, 0, ldb, tola, tolb, k, l, u, 0, ldu, v, 0, ldv, q, 0, ldq, iwork, 0, tau, 0, work, 0, info);
   }

   public void dggsvp(String jobu, String jobv, String jobq, int m, int p, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double tola, double tolb, intW k, intW l, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, int[] iwork, int offsetiwork, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dggsvp");
      }

      this.dggsvpK(jobu, jobv, jobq, m, p, n, a, offseta, lda, b, offsetb, ldb, tola, tolb, k, l, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, iwork, offsetiwork, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dggsvpK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double var15, intW var17, intW var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, int[] var28, int var29, double[] var30, int var31, double[] var32, int var33, intW var34);

   public void dgtcon(String norm, int n, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgtcon");
      }

      this.dgtcon(norm, n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dgtcon(String norm, int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgtcon");
      }

      this.dgtconK(norm, n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgtconK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int var12, double var13, doubleW var15, double[] var16, int var17, int[] var18, int var19, intW var20);

   public void dgtrfs(String trans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] dlf, double[] df, double[] duf, double[] du2, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgtrfs");
      }

      this.dgtrfs(trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dgtrfs(String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] dlf, int offsetdlf, double[] df, int offsetdf, double[] duf, int offsetduf, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgtrfs");
      }

      this.dgtrfsK(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgtrfsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int[] var32, int var33, intW var34);

   public void dgtsv(int n, int nrhs, double[] dl, double[] d, double[] du, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dgtsv");
      }

      this.dgtsv(n, nrhs, dl, 0, d, 0, du, 0, b, 0, ldb, info);
   }

   public void dgtsv(int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dgtsv");
      }

      this.dgtsvK(n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, b, offsetb, ldb, info);
   }

   protected abstract void dgtsvK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dgtsvx(String fact, String trans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] dlf, double[] df, double[] duf, double[] du2, int[] ipiv, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dgtsvx");
      }

      this.dgtsvx(fact, trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dgtsvx(String fact, String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] dlf, int offsetdlf, double[] df, int offsetdf, double[] duf, int offsetduf, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dgtsvx");
      }

      this.dgtsvxK(fact, trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dgtsvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, int[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int var26, doubleW var27, double[] var28, int var29, double[] var30, int var31, double[] var32, int var33, int[] var34, int var35, intW var36);

   public void dgttrf(int n, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dgttrf");
      }

      this.dgttrf(n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, info);
   }

   public void dgttrf(int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dgttrf");
      }

      this.dgttrfK(n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, info);
   }

   protected abstract void dgttrfK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   public void dgttrs(String trans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dgttrs");
      }

      this.dgttrs(trans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb, info);
   }

   public void dgttrs(String trans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dgttrs");
      }

      this.dgttrsK(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dgttrsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dgtts2(int itrans, int n, int nrhs, double[] dl, double[] d, double[] du, double[] du2, int[] ipiv, double[] b, int ldb) {
      if (debug) {
         System.err.println("dgtts2");
      }

      this.dgtts2(itrans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb);
   }

   public void dgtts2(int itrans, int n, int nrhs, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] du2, int offsetdu2, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("dgtts2");
      }

      this.dgtts2K(itrans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb);
   }

   protected abstract void dgtts2K(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16);

   public void dhgeqz(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int ldh, double[] t, int ldt, double[] alphar, double[] alphai, double[] beta, double[] q, int ldq, double[] z, int ldz, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dhgeqz");
      }

      this.dhgeqz(job, compq, compz, n, ilo, ihi, h, 0, ldh, t, 0, ldt, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, work, 0, lwork, info);
   }

   public void dhgeqz(String job, String compq, String compz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] t, int offsett, int ldt, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dhgeqz");
      }

      this.dhgeqzK(job, compq, compz, n, ilo, ihi, h, offseth, ldh, t, offsett, ldt, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void dhgeqzK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, intW var28);

   public void dhsein(String side, String eigsrc, String initv, boolean[] select, int n, double[] h, int ldh, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, int mm, intW m, double[] work, int[] ifaill, int[] ifailr, intW info) {
      if (debug) {
         System.err.println("dhsein");
      }

      this.dhsein(side, eigsrc, initv, select, 0, n, h, 0, ldh, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, ifaill, 0, ifailr, 0, info);
   }

   public void dhsein(String side, String eigsrc, String initv, boolean[] select, int offsetselect, int n, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, intW m, double[] work, int offsetwork, int[] ifaill, int offsetifaill, int[] ifailr, int offsetifailr, intW info) {
      if (debug) {
         System.err.println("dhsein");
      }

      this.dhseinK(side, eigsrc, initv, select, offsetselect, n, h, offseth, ldh, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, ifaill, offsetifaill, ifailr, offsetifailr, info);
   }

   protected abstract void dhseinK(String var1, String var2, String var3, boolean[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, int var20, intW var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   public void dhseqr(String job, String compz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, double[] z, int ldz, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dhseqr");
      }

      this.dhseqr(job, compz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, ldz, work, 0, lwork, info);
   }

   public void dhseqr(String job, String compz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dhseqr");
      }

      this.dhseqrK(job, compz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void dhseqrK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19);

   public boolean disnan(double din) {
      if (debug) {
         System.err.println("disnan");
      }

      return this.disnanK(din);
   }

   protected abstract boolean disnanK(double var1);

   public void dlabad(doubleW small, doubleW large) {
      if (debug) {
         System.err.println("dlabad");
      }

      this.dlabadK(small, large);
   }

   protected abstract void dlabadK(doubleW var1, doubleW var2);

   public void dlabrd(int m, int n, int nb, double[] a, int lda, double[] d, double[] e, double[] tauq, double[] taup, double[] x, int ldx, double[] y, int ldy) {
      if (debug) {
         System.err.println("dlabrd");
      }

      this.dlabrd(m, n, nb, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, x, 0, ldx, y, 0, ldy);
   }

   public void dlabrd(int m, int n, int nb, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tauq, int offsettauq, double[] taup, int offsettaup, double[] x, int offsetx, int ldx, double[] y, int offsety, int ldy) {
      if (debug) {
         System.err.println("dlabrd");
      }

      this.dlabrdK(m, n, nb, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, x, offsetx, ldx, y, offsety, ldy);
   }

   protected abstract void dlabrdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20);

   public void dlacn2(int n, double[] v, double[] x, int[] isgn, doubleW est, intW kase, int[] isave) {
      if (debug) {
         System.err.println("dlacn2");
      }

      this.dlacn2(n, v, 0, x, 0, isgn, 0, est, kase, isave, 0);
   }

   public void dlacn2(int n, double[] v, int offsetv, double[] x, int offsetx, int[] isgn, int offsetisgn, doubleW est, intW kase, int[] isave, int offsetisave) {
      if (debug) {
         System.err.println("dlacn2");
      }

      this.dlacn2K(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase, isave, offsetisave);
   }

   protected abstract void dlacn2K(int var1, double[] var2, int var3, double[] var4, int var5, int[] var6, int var7, doubleW var8, intW var9, int[] var10, int var11);

   public void dlacon(int n, double[] v, double[] x, int[] isgn, doubleW est, intW kase) {
      if (debug) {
         System.err.println("dlacon");
      }

      this.dlacon(n, v, 0, x, 0, isgn, 0, est, kase);
   }

   public void dlacon(int n, double[] v, int offsetv, double[] x, int offsetx, int[] isgn, int offsetisgn, doubleW est, intW kase) {
      if (debug) {
         System.err.println("dlacon");
      }

      this.dlaconK(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase);
   }

   protected abstract void dlaconK(int var1, double[] var2, int var3, double[] var4, int var5, int[] var6, int var7, doubleW var8, intW var9);

   public void dlacpy(String uplo, int m, int n, double[] a, int lda, double[] b, int ldb) {
      if (debug) {
         System.err.println("dlacpy");
      }

      this.dlacpy(uplo, m, n, a, 0, lda, b, 0, ldb);
   }

   public void dlacpy(String uplo, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("dlacpy");
      }

      this.dlacpyK(uplo, m, n, a, offseta, lda, b, offsetb, ldb);
   }

   protected abstract void dlacpyK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   public void dladiv(double a, double b, double c, double d, doubleW p, doubleW q) {
      if (debug) {
         System.err.println("dladiv");
      }

      this.dladivK(a, b, c, d, p, q);
   }

   protected abstract void dladivK(double var1, double var3, double var5, double var7, doubleW var9, doubleW var10);

   public void dlae2(double a, double b, double c, doubleW rt1, doubleW rt2) {
      if (debug) {
         System.err.println("dlae2");
      }

      this.dlae2K(a, b, c, rt1, rt2);
   }

   protected abstract void dlae2K(double var1, double var3, double var5, doubleW var7, doubleW var8);

   public void dlaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, double[] e, double[] e2, int[] nval, double[] ab, double[] c, intW mout, int[] nab, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlaebz");
      }

      this.dlaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, 0, e, 0, e2, 0, nval, 0, ab, 0, c, 0, mout, nab, 0, work, 0, iwork, 0, info);
   }

   public void dlaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, double abstol, double reltol, double pivmin, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, int[] nval, int offsetnval, double[] ab, int offsetab, double[] c, int offsetc, intW mout, int[] nab, int offsetnab, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlaebz");
      }

      this.dlaebzK(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, offsetd, e, offsete, e2, offsete2, nval, offsetnval, ab, offsetab, c, offsetc, mout, nab, offsetnab, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlaebzK(int var1, int var2, int var3, int var4, int var5, int var6, double var7, double var9, double var11, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, int[] var19, int var20, double[] var21, int var22, double[] var23, int var24, intW var25, int[] var26, int var27, double[] var28, int var29, int[] var30, int var31, intW var32);

   public void dlaed0(int icompq, int qsiz, int n, double[] d, double[] e, double[] q, int ldq, double[] qstore, int ldqs, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlaed0");
      }

      this.dlaed0(icompq, qsiz, n, d, 0, e, 0, q, 0, ldq, qstore, 0, ldqs, work, 0, iwork, 0, info);
   }

   public void dlaed0(int icompq, int qsiz, int n, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] qstore, int offsetqstore, int ldqs, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlaed0");
      }

      this.dlaed0K(icompq, qsiz, n, d, offsetd, e, offsete, q, offsetq, ldq, qstore, offsetqstore, ldqs, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlaed0K(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, intW var18);

   public void dlaed1(int n, double[] d, double[] q, int ldq, int[] indxq, doubleW rho, int cutpnt, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlaed1");
      }

      this.dlaed1(n, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, work, 0, iwork, 0, info);
   }

   public void dlaed1(int n, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, doubleW rho, int cutpnt, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlaed1");
      }

      this.dlaed1K(n, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlaed1K(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, doubleW var9, int var10, double[] var11, int var12, int[] var13, int var14, intW var15);

   public void dlaed2(intW k, int n, int n1, double[] d, double[] q, int ldq, int[] indxq, doubleW rho, double[] z, double[] dlamda, double[] w, double[] q2, int[] indx, int[] indxc, int[] indxp, int[] coltyp, intW info) {
      if (debug) {
         System.err.println("dlaed2");
      }

      this.dlaed2(k, n, n1, d, 0, q, 0, ldq, indxq, 0, rho, z, 0, dlamda, 0, w, 0, q2, 0, indx, 0, indxc, 0, indxp, 0, coltyp, 0, info);
   }

   public void dlaed2(intW k, int n, int n1, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, doubleW rho, double[] z, int offsetz, double[] dlamda, int offsetdlamda, double[] w, int offsetw, double[] q2, int offsetq2, int[] indx, int offsetindx, int[] indxc, int offsetindxc, int[] indxp, int offsetindxp, int[] coltyp, int offsetcoltyp, intW info) {
      if (debug) {
         System.err.println("dlaed2");
      }

      this.dlaed2K(k, n, n1, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, z, offsetz, dlamda, offsetdlamda, w, offsetw, q2, offsetq2, indx, offsetindx, indxc, offsetindxc, indxp, offsetindxp, coltyp, offsetcoltyp, info);
   }

   protected abstract void dlaed2K(intW var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, int[] var9, int var10, doubleW var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   public void dlaed3(int k, int n, int n1, double[] d, double[] q, int ldq, double rho, double[] dlamda, double[] q2, int[] indx, int[] ctot, double[] w, double[] s, intW info) {
      if (debug) {
         System.err.println("dlaed3");
      }

      this.dlaed3(k, n, n1, d, 0, q, 0, ldq, rho, dlamda, 0, q2, 0, indx, 0, ctot, 0, w, 0, s, 0, info);
   }

   public void dlaed3(int k, int n, int n1, double[] d, int offsetd, double[] q, int offsetq, int ldq, double rho, double[] dlamda, int offsetdlamda, double[] q2, int offsetq2, int[] indx, int offsetindx, int[] ctot, int offsetctot, double[] w, int offsetw, double[] s, int offsets, intW info) {
      if (debug) {
         System.err.println("dlaed3");
      }

      this.dlaed3K(k, n, n1, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, q2, offsetq2, indx, offsetindx, ctot, offsetctot, w, offsetw, s, offsets, info);
   }

   protected abstract void dlaed3K(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double var9, double[] var11, int var12, double[] var13, int var14, int[] var15, int var16, int[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   public void dlaed4(int n, int i, double[] d, double[] z, double[] delta, double rho, doubleW dlam, intW info) {
      if (debug) {
         System.err.println("dlaed4");
      }

      this.dlaed4(n, i, d, 0, z, 0, delta, 0, rho, dlam, info);
   }

   public void dlaed4(int n, int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, doubleW dlam, intW info) {
      if (debug) {
         System.err.println("dlaed4");
      }

      this.dlaed4K(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam, info);
   }

   protected abstract void dlaed4K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, intW var12);

   public void dlaed5(int i, double[] d, double[] z, double[] delta, double rho, doubleW dlam) {
      if (debug) {
         System.err.println("dlaed5");
      }

      this.dlaed5(i, d, 0, z, 0, delta, 0, rho, dlam);
   }

   public void dlaed5(int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, doubleW dlam) {
      if (debug) {
         System.err.println("dlaed5");
      }

      this.dlaed5K(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam);
   }

   protected abstract void dlaed5K(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, doubleW var10);

   public void dlaed6(int kniter, boolean orgati, double rho, double[] d, double[] z, double finit, doubleW tau, intW info) {
      if (debug) {
         System.err.println("dlaed6");
      }

      this.dlaed6(kniter, orgati, rho, d, 0, z, 0, finit, tau, info);
   }

   public void dlaed6(int kniter, boolean orgati, double rho, double[] d, int offsetd, double[] z, int offsetz, double finit, doubleW tau, intW info) {
      if (debug) {
         System.err.println("dlaed6");
      }

      this.dlaed6K(kniter, orgati, rho, d, offsetd, z, offsetz, finit, tau, info);
   }

   protected abstract void dlaed6K(int var1, boolean var2, double var3, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, intW var12);

   public void dlaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, double[] q, int ldq, int[] indxq, doubleW rho, int cutpnt, double[] qstore, int[] qptr, int[] prmptr, int[] perm, int[] givptr, int[] givcol, double[] givnum, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlaed7");
      }

      this.dlaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, qstore, 0, qptr, 0, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, work, 0, iwork, 0, info);
   }

   public void dlaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, doubleW rho, int cutpnt, double[] qstore, int offsetqstore, int[] qptr, int offsetqptr, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlaed7");
      }

      this.dlaed7K(icompq, n, qsiz, tlvls, curlvl, curpbm, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, qstore, offsetqstore, qptr, offsetqptr, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlaed7K(int var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, doubleW var14, int var15, double[] var16, int var17, int[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int[] var32, int var33, intW var34);

   public void dlaed8(int icompq, intW k, int n, int qsiz, double[] d, double[] q, int ldq, int[] indxq, doubleW rho, int cutpnt, double[] z, double[] dlamda, double[] q2, int ldq2, double[] w, int[] perm, intW givptr, int[] givcol, double[] givnum, int[] indxp, int[] indx, intW info) {
      if (debug) {
         System.err.println("dlaed8");
      }

      this.dlaed8(icompq, k, n, qsiz, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, z, 0, dlamda, 0, q2, 0, ldq2, w, 0, perm, 0, givptr, givcol, 0, givnum, 0, indxp, 0, indx, 0, info);
   }

   public void dlaed8(int icompq, intW k, int n, int qsiz, double[] d, int offsetd, double[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, doubleW rho, int cutpnt, double[] z, int offsetz, double[] dlamda, int offsetdlamda, double[] q2, int offsetq2, int ldq2, double[] w, int offsetw, int[] perm, int offsetperm, intW givptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, int[] indxp, int offsetindxp, int[] indx, int offsetindx, intW info) {
      if (debug) {
         System.err.println("dlaed8");
      }

      this.dlaed8K(icompq, k, n, qsiz, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, z, offsetz, dlamda, offsetdlamda, q2, offsetq2, ldq2, w, offsetw, perm, offsetperm, givptr, givcol, offsetgivcol, givnum, offsetgivnum, indxp, offsetindxp, indx, offsetindx, info);
   }

   protected abstract void dlaed8K(int var1, intW var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, doubleW var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int[] var23, int var24, intW var25, int[] var26, int var27, double[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34);

   public void dlaed9(int k, int kstart, int kstop, int n, double[] d, double[] q, int ldq, double rho, double[] dlamda, double[] w, double[] s, int lds, intW info) {
      if (debug) {
         System.err.println("dlaed9");
      }

      this.dlaed9(k, kstart, kstop, n, d, 0, q, 0, ldq, rho, dlamda, 0, w, 0, s, 0, lds, info);
   }

   public void dlaed9(int k, int kstart, int kstop, int n, double[] d, int offsetd, double[] q, int offsetq, int ldq, double rho, double[] dlamda, int offsetdlamda, double[] w, int offsetw, double[] s, int offsets, int lds, intW info) {
      if (debug) {
         System.err.println("dlaed9");
      }

      this.dlaed9K(k, kstart, kstop, n, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, w, offsetw, s, offsets, lds, info);
   }

   protected abstract void dlaed9K(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double var10, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   public void dlaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int[] perm, int[] givptr, int[] givcol, double[] givnum, double[] q, int[] qptr, double[] z, double[] ztemp, intW info) {
      if (debug) {
         System.err.println("dlaeda");
      }

      this.dlaeda(n, tlvls, curlvl, curpbm, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, q, 0, qptr, 0, z, 0, ztemp, 0, info);
   }

   public void dlaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, double[] givnum, int offsetgivnum, double[] q, int offsetq, int[] qptr, int offsetqptr, double[] z, int offsetz, double[] ztemp, int offsetztemp, intW info) {
      if (debug) {
         System.err.println("dlaeda");
      }

      this.dlaedaK(n, tlvls, curlvl, curpbm, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, q, offsetq, qptr, offsetqptr, z, offsetz, ztemp, offsetztemp, info);
   }

   protected abstract void dlaedaK(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, int[] var9, int var10, int[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   public void dlaein(boolean rightv, boolean noinit, int n, double[] h, int ldh, double wr, double wi, double[] vr, double[] vi, double[] b, int ldb, double[] work, double eps3, double smlnum, double bignum, intW info) {
      if (debug) {
         System.err.println("dlaein");
      }

      this.dlaein(rightv, noinit, n, h, 0, ldh, wr, wi, vr, 0, vi, 0, b, 0, ldb, work, 0, eps3, smlnum, bignum, info);
   }

   public void dlaein(boolean rightv, boolean noinit, int n, double[] h, int offseth, int ldh, double wr, double wi, double[] vr, int offsetvr, double[] vi, int offsetvi, double[] b, int offsetb, int ldb, double[] work, int offsetwork, double eps3, double smlnum, double bignum, intW info) {
      if (debug) {
         System.err.println("dlaein");
      }

      this.dlaeinK(rightv, noinit, n, h, offseth, ldh, wr, wi, vr, offsetvr, vi, offsetvi, b, offsetb, ldb, work, offsetwork, eps3, smlnum, bignum, info);
   }

   protected abstract void dlaeinK(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double var7, double var9, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double var20, double var22, double var24, intW var26);

   public void dlaev2(double a, double b, double c, doubleW rt1, doubleW rt2, doubleW cs1, doubleW sn1) {
      if (debug) {
         System.err.println("dlaev2");
      }

      this.dlaev2K(a, b, c, rt1, rt2, cs1, sn1);
   }

   protected abstract void dlaev2K(double var1, double var3, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   public void dlaexc(boolean wantq, int n, double[] t, int ldt, double[] q, int ldq, int j1, int n1, int n2, double[] work, intW info) {
      if (debug) {
         System.err.println("dlaexc");
      }

      this.dlaexc(wantq, n, t, 0, ldt, q, 0, ldq, j1, n1, n2, work, 0, info);
   }

   public void dlaexc(boolean wantq, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, int j1, int n1, int n2, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlaexc");
      }

      this.dlaexcK(wantq, n, t, offsett, ldt, q, offsetq, ldq, j1, n1, n2, work, offsetwork, info);
   }

   protected abstract void dlaexcK(boolean var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, int var9, int var10, int var11, double[] var12, int var13, intW var14);

   public void dlag2(double[] a, int lda, double[] b, int ldb, double safmin, doubleW scale1, doubleW scale2, doubleW wr1, doubleW wr2, doubleW wi) {
      if (debug) {
         System.err.println("dlag2");
      }

      this.dlag2(a, 0, lda, b, 0, ldb, safmin, scale1, scale2, wr1, wr2, wi);
   }

   public void dlag2(double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double safmin, doubleW scale1, doubleW scale2, doubleW wr1, doubleW wr2, doubleW wi) {
      if (debug) {
         System.err.println("dlag2");
      }

      this.dlag2K(a, offseta, lda, b, offsetb, ldb, safmin, scale1, scale2, wr1, wr2, wi);
   }

   protected abstract void dlag2K(double[] var1, int var2, int var3, double[] var4, int var5, int var6, double var7, doubleW var9, doubleW var10, doubleW var11, doubleW var12, doubleW var13);

   public void dlag2s(int m, int n, double[] a, int lda, float[] sa, int ldsa, intW info) {
      if (debug) {
         System.err.println("dlag2s");
      }

      this.dlag2s(m, n, a, 0, lda, sa, 0, ldsa, info);
   }

   public void dlag2s(int m, int n, double[] a, int offseta, int lda, float[] sa, int offsetsa, int ldsa, intW info) {
      if (debug) {
         System.err.println("dlag2s");
      }

      this.dlag2sK(m, n, a, offseta, lda, sa, offsetsa, ldsa, info);
   }

   protected abstract void dlag2sK(int var1, int var2, double[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9);

   public void dlags2(boolean upper, double a1, double a2, double a3, double b1, double b2, double b3, doubleW csu, doubleW snu, doubleW csv, doubleW snv, doubleW csq, doubleW snq) {
      if (debug) {
         System.err.println("dlags2");
      }

      this.dlags2K(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
   }

   protected abstract void dlags2K(boolean var1, double var2, double var4, double var6, double var8, double var10, double var12, doubleW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19);

   public void dlagtf(int n, double[] a, double lambda, double[] b, double[] c, double tol, double[] d, int[] in, intW info) {
      if (debug) {
         System.err.println("dlagtf");
      }

      this.dlagtf(n, a, 0, lambda, b, 0, c, 0, tol, d, 0, in, 0, info);
   }

   public void dlagtf(int n, double[] a, int offseta, double lambda, double[] b, int offsetb, double[] c, int offsetc, double tol, double[] d, int offsetd, int[] in, int offsetin, intW info) {
      if (debug) {
         System.err.println("dlagtf");
      }

      this.dlagtfK(n, a, offseta, lambda, b, offsetb, c, offsetc, tol, d, offsetd, in, offsetin, info);
   }

   protected abstract void dlagtfK(int var1, double[] var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double var10, double[] var12, int var13, int[] var14, int var15, intW var16);

   public void dlagtm(String trans, int n, int nrhs, double alpha, double[] dl, double[] d, double[] du, double[] x, int ldx, double beta, double[] b, int ldb) {
      if (debug) {
         System.err.println("dlagtm");
      }

      this.dlagtm(trans, n, nrhs, alpha, dl, 0, d, 0, du, 0, x, 0, ldx, beta, b, 0, ldb);
   }

   public void dlagtm(String trans, int n, int nrhs, double alpha, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu, double[] x, int offsetx, int ldx, double beta, double[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("dlagtm");
      }

      this.dlagtmK(trans, n, nrhs, alpha, dl, offsetdl, d, offsetd, du, offsetdu, x, offsetx, ldx, beta, b, offsetb, ldb);
   }

   protected abstract void dlagtmK(String var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double var15, double[] var17, int var18, int var19);

   public void dlagts(int job, int n, double[] a, double[] b, double[] c, double[] d, int[] in, double[] y, doubleW tol, intW info) {
      if (debug) {
         System.err.println("dlagts");
      }

      this.dlagts(job, n, a, 0, b, 0, c, 0, d, 0, in, 0, y, 0, tol, info);
   }

   public void dlagts(int job, int n, double[] a, int offseta, double[] b, int offsetb, double[] c, int offsetc, double[] d, int offsetd, int[] in, int offsetin, double[] y, int offsety, doubleW tol, intW info) {
      if (debug) {
         System.err.println("dlagts");
      }

      this.dlagtsK(job, n, a, offseta, b, offsetb, c, offsetc, d, offsetd, in, offsetin, y, offsety, tol, info);
   }

   protected abstract void dlagtsK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int var12, double[] var13, int var14, doubleW var15, intW var16);

   public void dlagv2(double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, doubleW csl, doubleW snl, doubleW csr, doubleW snr) {
      if (debug) {
         System.err.println("dlagv2");
      }

      this.dlagv2(a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, csl, snl, csr, snr);
   }

   public void dlagv2(double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, doubleW csl, doubleW snl, doubleW csr, doubleW snr) {
      if (debug) {
         System.err.println("dlagv2");
      }

      this.dlagv2K(a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, csl, snl, csr, snr);
   }

   protected abstract void dlagv2K(double[] var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, doubleW var13, doubleW var14, doubleW var15, doubleW var16);

   public void dlahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, int iloz, int ihiz, double[] z, int ldz, intW info) {
      if (debug) {
         System.err.println("dlahqr");
      }

      this.dlahqr(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, info);
   }

   public void dlahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, intW info) {
      if (debug) {
         System.err.println("dlahqr");
      }

      this.dlahqrK(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, info);
   }

   protected abstract void dlahqrK(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   public void dlahr2(int n, int k, int nb, double[] a, int lda, double[] tau, double[] t, int ldt, double[] y, int ldy) {
      if (debug) {
         System.err.println("dlahr2");
      }

      this.dlahr2(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
   }

   public void dlahr2(int n, int k, int nb, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] t, int offsett, int ldt, double[] y, int offsety, int ldy) {
      if (debug) {
         System.err.println("dlahr2");
      }

      this.dlahr2K(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
   }

   protected abstract void dlahr2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   public void dlahrd(int n, int k, int nb, double[] a, int lda, double[] tau, double[] t, int ldt, double[] y, int ldy) {
      if (debug) {
         System.err.println("dlahrd");
      }

      this.dlahrd(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
   }

   public void dlahrd(int n, int k, int nb, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] t, int offsett, int ldt, double[] y, int offsety, int ldy) {
      if (debug) {
         System.err.println("dlahrd");
      }

      this.dlahrdK(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
   }

   protected abstract void dlahrdK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   public void dlaic1(int job, int j, double[] x, double sest, double[] w, double gamma, doubleW sestpr, doubleW s, doubleW c) {
      if (debug) {
         System.err.println("dlaic1");
      }

      this.dlaic1(job, j, x, 0, sest, w, 0, gamma, sestpr, s, c);
   }

   public void dlaic1(int job, int j, double[] x, int offsetx, double sest, double[] w, int offsetw, double gamma, doubleW sestpr, doubleW s, doubleW c) {
      if (debug) {
         System.err.println("dlaic1");
      }

      this.dlaic1K(job, j, x, offsetx, sest, w, offsetw, gamma, sestpr, s, c);
   }

   protected abstract void dlaic1K(int var1, int var2, double[] var3, int var4, double var5, double[] var7, int var8, double var9, doubleW var11, doubleW var12, doubleW var13);

   public boolean dlaisnan(double din1, double din2) {
      if (debug) {
         System.err.println("dlaisnan");
      }

      return this.dlaisnanK(din1, din2);
   }

   protected abstract boolean dlaisnanK(double var1, double var3);

   public void dlaln2(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int lda, double d1, double d2, double[] b, int ldb, double wr, double wi, double[] x, int ldx, doubleW scale, doubleW xnorm, intW info) {
      if (debug) {
         System.err.println("dlaln2");
      }

      this.dlaln2(ltrans, na, nw, smin, ca, a, 0, lda, d1, d2, b, 0, ldb, wr, wi, x, 0, ldx, scale, xnorm, info);
   }

   public void dlaln2(boolean ltrans, int na, int nw, double smin, double ca, double[] a, int offseta, int lda, double d1, double d2, double[] b, int offsetb, int ldb, double wr, double wi, double[] x, int offsetx, int ldx, doubleW scale, doubleW xnorm, intW info) {
      if (debug) {
         System.err.println("dlaln2");
      }

      this.dlaln2K(ltrans, na, nw, smin, ca, a, offseta, lda, d1, d2, b, offsetb, ldb, wr, wi, x, offsetx, ldx, scale, xnorm, info);
   }

   protected abstract void dlaln2K(boolean var1, int var2, int var3, double var4, double var6, double[] var8, int var9, int var10, double var11, double var13, double[] var15, int var16, int var17, double var18, double var20, double[] var22, int var23, int var24, doubleW var25, doubleW var26, intW var27);

   public void dlals0(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int ldb, double[] bx, int ldbx, int[] perm, int givptr, int[] givcol, int ldgcol, double[] givnum, int ldgnum, double[] poles, double[] difl, double[] difr, double[] z, int k, double c, double s, double[] work, intW info) {
      if (debug) {
         System.err.println("dlals0");
      }

      this.dlals0(icompq, nl, nr, sqre, nrhs, b, 0, ldb, bx, 0, ldbx, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, info);
   }

   public void dlals0(int icompq, int nl, int nr, int sqre, int nrhs, double[] b, int offsetb, int ldb, double[] bx, int offsetbx, int ldbx, int[] perm, int offsetperm, int givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, double[] poles, int offsetpoles, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, int k, double c, double s, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlals0");
      }

      this.dlals0K(icompq, nl, nr, sqre, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, info);
   }

   protected abstract void dlals0K(int var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, int var14, int[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int var29, double var30, double var32, double[] var34, int var35, intW var36);

   public void dlalsa(int icompq, int smlsiz, int n, int nrhs, double[] b, int ldb, double[] bx, int ldbx, double[] u, int ldu, double[] vt, int[] k, double[] difl, double[] difr, double[] z, double[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, double[] givnum, double[] c, double[] s, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlalsa");
      }

      this.dlalsa(icompq, smlsiz, n, nrhs, b, 0, ldb, bx, 0, ldbx, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
   }

   public void dlalsa(int icompq, int smlsiz, int n, int nrhs, double[] b, int offsetb, int ldb, double[] bx, int offsetbx, int ldbx, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int[] k, int offsetk, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, double[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, double[] givnum, int offsetgivnum, double[] c, int offsetc, double[] s, int offsets, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlalsa");
      }

      this.dlalsaK(icompq, smlsiz, n, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlalsaK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int var30, int[] var31, int var32, double[] var33, int var34, double[] var35, int var36, double[] var37, int var38, double[] var39, int var40, int[] var41, int var42, intW var43);

   public void dlalsd(String uplo, int smlsiz, int n, int nrhs, double[] d, double[] e, double[] b, int ldb, double rcond, intW rank, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlalsd");
      }

      this.dlalsd(uplo, smlsiz, n, nrhs, d, 0, e, 0, b, 0, ldb, rcond, rank, work, 0, iwork, 0, info);
   }

   public void dlalsd(String uplo, int smlsiz, int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, double rcond, intW rank, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlalsd");
      }

      this.dlalsdK(uplo, smlsiz, n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, rcond, rank, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlalsdK(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double var12, intW var14, double[] var15, int var16, int[] var17, int var18, intW var19);

   public void dlamrg(int n1, int n2, double[] a, int dtrd1, int dtrd2, int[] index) {
      if (debug) {
         System.err.println("dlamrg");
      }

      this.dlamrg(n1, n2, a, 0, dtrd1, dtrd2, index, 0);
   }

   public void dlamrg(int n1, int n2, double[] a, int offseta, int dtrd1, int dtrd2, int[] index, int offsetindex) {
      if (debug) {
         System.err.println("dlamrg");
      }

      this.dlamrgK(n1, n2, a, offseta, dtrd1, dtrd2, index, offsetindex);
   }

   protected abstract void dlamrgK(int var1, int var2, double[] var3, int var4, int var5, int var6, int[] var7, int var8);

   public int dlaneg(int n, double[] d, double[] lld, double sigma, double pivmin, int r) {
      if (debug) {
         System.err.println("dlaneg");
      }

      return this.dlaneg(n, d, 0, lld, 0, sigma, pivmin, r);
   }

   public int dlaneg(int n, double[] d, int offsetd, double[] lld, int offsetlld, double sigma, double pivmin, int r) {
      if (debug) {
         System.err.println("dlaneg");
      }

      return this.dlanegK(n, d, offsetd, lld, offsetlld, sigma, pivmin, r);
   }

   protected abstract int dlanegK(int var1, double[] var2, int var3, double[] var4, int var5, double var6, double var8, int var10);

   public double dlangb(String norm, int n, int kl, int ku, double[] ab, int ldab, double[] work) {
      if (debug) {
         System.err.println("dlangb");
      }

      return this.dlangb(norm, n, kl, ku, ab, 0, ldab, work, 0);
   }

   public double dlangb(String norm, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlangb");
      }

      return this.dlangbK(norm, n, kl, ku, ab, offsetab, ldab, work, offsetwork);
   }

   protected abstract double dlangbK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   public double dlange(String norm, int m, int n, double[] a, int lda, double[] work) {
      if (debug) {
         System.err.println("dlange");
      }

      return this.dlange(norm, m, n, a, 0, lda, work, 0);
   }

   public double dlange(String norm, int m, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlange");
      }

      return this.dlangeK(norm, m, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract double dlangeK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8);

   public double dlangt(String norm, int n, double[] dl, double[] d, double[] du) {
      if (debug) {
         System.err.println("dlangt");
      }

      return this.dlangt(norm, n, dl, 0, d, 0, du, 0);
   }

   public double dlangt(String norm, int n, double[] dl, int offsetdl, double[] d, int offsetd, double[] du, int offsetdu) {
      if (debug) {
         System.err.println("dlangt");
      }

      return this.dlangtK(norm, n, dl, offsetdl, d, offsetd, du, offsetdu);
   }

   protected abstract double dlangtK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8);

   public double dlanhs(String norm, int n, double[] a, int lda, double[] work) {
      if (debug) {
         System.err.println("dlanhs");
      }

      return this.dlanhs(norm, n, a, 0, lda, work, 0);
   }

   public double dlanhs(String norm, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlanhs");
      }

      return this.dlanhsK(norm, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract double dlanhsK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7);

   public double dlansb(String norm, String uplo, int n, int k, double[] ab, int ldab, double[] work) {
      if (debug) {
         System.err.println("dlansb");
      }

      return this.dlansb(norm, uplo, n, k, ab, 0, ldab, work, 0);
   }

   public double dlansb(String norm, String uplo, int n, int k, double[] ab, int offsetab, int ldab, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlansb");
      }

      return this.dlansbK(norm, uplo, n, k, ab, offsetab, ldab, work, offsetwork);
   }

   protected abstract double dlansbK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   public double dlansp(String norm, String uplo, int n, double[] ap, double[] work) {
      if (debug) {
         System.err.println("dlansp");
      }

      return this.dlansp(norm, uplo, n, ap, 0, work, 0);
   }

   public double dlansp(String norm, String uplo, int n, double[] ap, int offsetap, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlansp");
      }

      return this.dlanspK(norm, uplo, n, ap, offsetap, work, offsetwork);
   }

   protected abstract double dlanspK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7);

   public double dlanst(String norm, int n, double[] d, double[] e) {
      if (debug) {
         System.err.println("dlanst");
      }

      return this.dlanst(norm, n, d, 0, e, 0);
   }

   public double dlanst(String norm, int n, double[] d, int offsetd, double[] e, int offsete) {
      if (debug) {
         System.err.println("dlanst");
      }

      return this.dlanstK(norm, n, d, offsetd, e, offsete);
   }

   protected abstract double dlanstK(String var1, int var2, double[] var3, int var4, double[] var5, int var6);

   public double dlansy(String norm, String uplo, int n, double[] a, int lda, double[] work) {
      if (debug) {
         System.err.println("dlansy");
      }

      return this.dlansy(norm, uplo, n, a, 0, lda, work, 0);
   }

   public double dlansy(String norm, String uplo, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlansy");
      }

      return this.dlansyK(norm, uplo, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract double dlansyK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8);

   public double dlantb(String norm, String uplo, String diag, int n, int k, double[] ab, int ldab, double[] work) {
      if (debug) {
         System.err.println("dlantb");
      }

      return this.dlantb(norm, uplo, diag, n, k, ab, 0, ldab, work, 0);
   }

   public double dlantb(String norm, String uplo, String diag, int n, int k, double[] ab, int offsetab, int ldab, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlantb");
      }

      return this.dlantbK(norm, uplo, diag, n, k, ab, offsetab, ldab, work, offsetwork);
   }

   protected abstract double dlantbK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10);

   public double dlantp(String norm, String uplo, String diag, int n, double[] ap, double[] work) {
      if (debug) {
         System.err.println("dlantp");
      }

      return this.dlantp(norm, uplo, diag, n, ap, 0, work, 0);
   }

   public double dlantp(String norm, String uplo, String diag, int n, double[] ap, int offsetap, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlantp");
      }

      return this.dlantpK(norm, uplo, diag, n, ap, offsetap, work, offsetwork);
   }

   protected abstract double dlantpK(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8);

   public double dlantr(String norm, String uplo, String diag, int m, int n, double[] a, int lda, double[] work) {
      if (debug) {
         System.err.println("dlantr");
      }

      return this.dlantr(norm, uplo, diag, m, n, a, 0, lda, work, 0);
   }

   public double dlantr(String norm, String uplo, String diag, int m, int n, double[] a, int offseta, int lda, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlantr");
      }

      return this.dlantrK(norm, uplo, diag, m, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract double dlantrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10);

   public void dlanv2(doubleW a, doubleW b, doubleW c, doubleW d, doubleW rt1r, doubleW rt1i, doubleW rt2r, doubleW rt2i, doubleW cs, doubleW sn) {
      if (debug) {
         System.err.println("dlanv2");
      }

      this.dlanv2K(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
   }

   protected abstract void dlanv2K(doubleW var1, doubleW var2, doubleW var3, doubleW var4, doubleW var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   public void dlapll(int n, double[] x, int incx, double[] y, int incy, doubleW ssmin) {
      if (debug) {
         System.err.println("dlapll");
      }

      this.dlapll(n, x, 0, incx, y, 0, incy, ssmin);
   }

   public void dlapll(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, doubleW ssmin) {
      if (debug) {
         System.err.println("dlapll");
      }

      this.dlapllK(n, x, offsetx, incx, y, offsety, incy, ssmin);
   }

   protected abstract void dlapllK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, doubleW var8);

   public void dlapmt(boolean forwrd, int m, int n, double[] x, int ldx, int[] k) {
      if (debug) {
         System.err.println("dlapmt");
      }

      this.dlapmt(forwrd, m, n, x, 0, ldx, k, 0);
   }

   public void dlapmt(boolean forwrd, int m, int n, double[] x, int offsetx, int ldx, int[] k, int offsetk) {
      if (debug) {
         System.err.println("dlapmt");
      }

      this.dlapmtK(forwrd, m, n, x, offsetx, ldx, k, offsetk);
   }

   protected abstract void dlapmtK(boolean var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8);

   public double dlapy2(double x, double y) {
      if (debug) {
         System.err.println("dlapy2");
      }

      return this.dlapy2K(x, y);
   }

   protected abstract double dlapy2K(double var1, double var3);

   public double dlapy3(double x, double y, double z) {
      if (debug) {
         System.err.println("dlapy3");
      }

      return this.dlapy3K(x, y, z);
   }

   protected abstract double dlapy3K(double var1, double var3, double var5);

   public void dlaqgb(int m, int n, int kl, int ku, double[] ab, int ldab, double[] r, double[] c, double rowcnd, double colcnd, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqgb");
      }

      this.dlaqgb(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, equed);
   }

   public void dlaqgb(int m, int n, int kl, int ku, double[] ab, int offsetab, int ldab, double[] r, int offsetr, double[] c, int offsetc, double rowcnd, double colcnd, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqgb");
      }

      this.dlaqgbK(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
   }

   protected abstract void dlaqgbK(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double var12, double var14, double var16, StringW var18);

   public void dlaqge(int m, int n, double[] a, int lda, double[] r, double[] c, double rowcnd, double colcnd, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqge");
      }

      this.dlaqge(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, equed);
   }

   public void dlaqge(int m, int n, double[] a, int offseta, int lda, double[] r, int offsetr, double[] c, int offsetc, double rowcnd, double colcnd, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqge");
      }

      this.dlaqgeK(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
   }

   protected abstract void dlaqgeK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, double var14, StringW var16);

   public void dlaqp2(int m, int n, int offset, double[] a, int lda, int[] jpvt, double[] tau, double[] vn1, double[] vn2, double[] work) {
      if (debug) {
         System.err.println("dlaqp2");
      }

      this.dlaqp2(m, n, offset, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, work, 0);
   }

   public void dlaqp2(int m, int n, int offset, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] vn1, int offsetvn1, double[] vn2, int offsetvn2, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlaqp2");
      }

      this.dlaqp2K(m, n, offset, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, work, offsetwork);
   }

   protected abstract void dlaqp2K(int var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16);

   public void dlaqps(int m, int n, int offset, int nb, intW kb, double[] a, int lda, int[] jpvt, double[] tau, double[] vn1, double[] vn2, double[] auxv, double[] f, int ldf) {
      if (debug) {
         System.err.println("dlaqps");
      }

      this.dlaqps(m, n, offset, nb, kb, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, auxv, 0, f, 0, ldf);
   }

   public void dlaqps(int m, int n, int offset, int nb, intW kb, double[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, double[] tau, int offsettau, double[] vn1, int offsetvn1, double[] vn2, int offsetvn2, double[] auxv, int offsetauxv, double[] f, int offsetf, int ldf) {
      if (debug) {
         System.err.println("dlaqps");
      }

      this.dlaqpsK(m, n, offset, nb, kb, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, auxv, offsetauxv, f, offsetf, ldf);
   }

   protected abstract void dlaqpsK(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, int var8, int[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21);

   public void dlaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, int iloz, int ihiz, double[] z, int ldz, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dlaqr0");
      }

      this.dlaqr0(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
   }

   public void dlaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dlaqr0");
      }

      this.dlaqr0K(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void dlaqr0K(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, intW var21);

   public void dlaqr1(int n, double[] h, int ldh, double sr1, double si1, double sr2, double si2, double[] v) {
      if (debug) {
         System.err.println("dlaqr1");
      }

      this.dlaqr1(n, h, 0, ldh, sr1, si1, sr2, si2, v, 0);
   }

   public void dlaqr1(int n, double[] h, int offseth, int ldh, double sr1, double si1, double sr2, double si2, double[] v, int offsetv) {
      if (debug) {
         System.err.println("dlaqr1");
      }

      this.dlaqr1K(n, h, offseth, ldh, sr1, si1, sr2, si2, v, offsetv);
   }

   protected abstract void dlaqr1K(int var1, double[] var2, int var3, int var4, double var5, double var7, double var9, double var11, double[] var13, int var14);

   public void dlaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int ldh, int iloz, int ihiz, double[] z, int ldz, intW ns, intW nd, double[] sr, double[] si, double[] v, int ldv, int nh, double[] t, int ldt, int nv, double[] wv, int ldwv, double[] work, int lwork) {
      if (debug) {
         System.err.println("dlaqr2");
      }

      this.dlaqr2(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
   }

   public void dlaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, intW ns, intW nd, double[] sr, int offsetsr, double[] si, int offsetsi, double[] v, int offsetv, int ldv, int nh, double[] t, int offsett, int ldt, int nv, double[] wv, int offsetwv, int ldwv, double[] work, int offsetwork, int lwork) {
      if (debug) {
         System.err.println("dlaqr2");
      }

      this.dlaqr2K(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
   }

   protected abstract void dlaqr2K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, int var11, double[] var12, int var13, int var14, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, int var24, double[] var25, int var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, int var34);

   public void dlaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int ldh, int iloz, int ihiz, double[] z, int ldz, intW ns, intW nd, double[] sr, double[] si, double[] v, int ldv, int nh, double[] t, int ldt, int nv, double[] wv, int ldwv, double[] work, int lwork) {
      if (debug) {
         System.err.println("dlaqr3");
      }

      this.dlaqr3(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
   }

   public void dlaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, intW ns, intW nd, double[] sr, int offsetsr, double[] si, int offsetsi, double[] v, int offsetv, int ldv, int nh, double[] t, int offsett, int ldt, int nv, double[] wv, int offsetwv, int ldwv, double[] work, int offsetwork, int lwork) {
      if (debug) {
         System.err.println("dlaqr3");
      }

      this.dlaqr3K(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
   }

   protected abstract void dlaqr3K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, int var11, double[] var12, int var13, int var14, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, int var24, double[] var25, int var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, int var34);

   public void dlaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int ldh, double[] wr, double[] wi, int iloz, int ihiz, double[] z, int ldz, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dlaqr4");
      }

      this.dlaqr4(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
   }

   public void dlaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, double[] h, int offseth, int ldh, double[] wr, int offsetwr, double[] wi, int offsetwi, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dlaqr4");
      }

      this.dlaqr4K(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void dlaqr4K(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, intW var21);

   public void dlaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, double[] si, double[] h, int ldh, int iloz, int ihiz, double[] z, int ldz, double[] v, int ldv, double[] u, int ldu, int nv, double[] wv, int ldwv, int nh, double[] wh, int ldwh) {
      if (debug) {
         System.err.println("dlaqr5");
      }

      this.dlaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, 0, si, 0, h, 0, ldh, iloz, ihiz, z, 0, ldz, v, 0, ldv, u, 0, ldu, nv, wv, 0, ldwv, nh, wh, 0, ldwh);
   }

   public void dlaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, double[] sr, int offsetsr, double[] si, int offsetsi, double[] h, int offseth, int ldh, int iloz, int ihiz, double[] z, int offsetz, int ldz, double[] v, int offsetv, int ldv, double[] u, int offsetu, int ldu, int nv, double[] wv, int offsetwv, int ldwv, int nh, double[] wh, int offsetwh, int ldwh) {
      if (debug) {
         System.err.println("dlaqr5");
      }

      this.dlaqr5K(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, offsetsr, si, offsetsi, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, v, offsetv, ldv, u, offsetu, ldu, nv, wv, offsetwv, ldwv, nh, wh, offsetwh, ldwh);
   }

   protected abstract void dlaqr5K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, int var26, double[] var27, int var28, int var29, int var30, double[] var31, int var32, int var33);

   public void dlaqsb(String uplo, int n, int kd, double[] ab, int ldab, double[] s, double scond, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqsb");
      }

      this.dlaqsb(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, equed);
   }

   public void dlaqsb(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] s, int offsets, double scond, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqsb");
      }

      this.dlaqsbK(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, equed);
   }

   protected abstract void dlaqsbK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double var9, double var11, StringW var13);

   public void dlaqsp(String uplo, int n, double[] ap, double[] s, double scond, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqsp");
      }

      this.dlaqsp(uplo, n, ap, 0, s, 0, scond, amax, equed);
   }

   public void dlaqsp(String uplo, int n, double[] ap, int offsetap, double[] s, int offsets, double scond, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqsp");
      }

      this.dlaqspK(uplo, n, ap, offsetap, s, offsets, scond, amax, equed);
   }

   protected abstract void dlaqspK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double var7, double var9, StringW var11);

   public void dlaqsy(String uplo, int n, double[] a, int lda, double[] s, double scond, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqsy");
      }

      this.dlaqsy(uplo, n, a, 0, lda, s, 0, scond, amax, equed);
   }

   public void dlaqsy(String uplo, int n, double[] a, int offseta, int lda, double[] s, int offsets, double scond, double amax, StringW equed) {
      if (debug) {
         System.err.println("dlaqsy");
      }

      this.dlaqsyK(uplo, n, a, offseta, lda, s, offsets, scond, amax, equed);
   }

   protected abstract void dlaqsyK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double var8, double var10, StringW var12);

   public void dlaqtr(boolean ltran, boolean lreal, int n, double[] t, int ldt, double[] b, double w, doubleW scale, double[] x, double[] work, intW info) {
      if (debug) {
         System.err.println("dlaqtr");
      }

      this.dlaqtr(ltran, lreal, n, t, 0, ldt, b, 0, w, scale, x, 0, work, 0, info);
   }

   public void dlaqtr(boolean ltran, boolean lreal, int n, double[] t, int offsett, int ldt, double[] b, int offsetb, double w, doubleW scale, double[] x, int offsetx, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlaqtr");
      }

      this.dlaqtrK(ltran, lreal, n, t, offsett, ldt, b, offsetb, w, scale, x, offsetx, work, offsetwork, info);
   }

   protected abstract void dlaqtrK(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double var9, doubleW var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   public void dlar1v(int n, int b1, int bn, double lambda, double[] d, double[] l, double[] ld, double[] lld, double pivmin, double gaptol, double[] z, boolean wantnc, intW negcnt, doubleW ztz, doubleW mingma, intW r, int[] isuppz, doubleW nrminv, doubleW resid, doubleW rqcorr, double[] work) {
      if (debug) {
         System.err.println("dlar1v");
      }

      this.dlar1v(n, b1, bn, lambda, d, 0, l, 0, ld, 0, lld, 0, pivmin, gaptol, z, 0, wantnc, negcnt, ztz, mingma, r, isuppz, 0, nrminv, resid, rqcorr, work, 0);
   }

   public void dlar1v(int n, int b1, int bn, double lambda, double[] d, int offsetd, double[] l, int offsetl, double[] ld, int offsetld, double[] lld, int offsetlld, double pivmin, double gaptol, double[] z, int offsetz, boolean wantnc, intW negcnt, doubleW ztz, doubleW mingma, intW r, int[] isuppz, int offsetisuppz, doubleW nrminv, doubleW resid, doubleW rqcorr, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlar1v");
      }

      this.dlar1vK(n, b1, bn, lambda, d, offsetd, l, offsetl, ld, offsetld, lld, offsetlld, pivmin, gaptol, z, offsetz, wantnc, negcnt, ztz, mingma, r, isuppz, offsetisuppz, nrminv, resid, rqcorr, work, offsetwork);
   }

   protected abstract void dlar1vK(int var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double var14, double var16, double[] var18, int var19, boolean var20, intW var21, doubleW var22, doubleW var23, intW var24, int[] var25, int var26, doubleW var27, doubleW var28, doubleW var29, double[] var30, int var31);

   public void dlar2v(int n, double[] x, double[] y, double[] z, int incx, double[] c, double[] s, int incc) {
      if (debug) {
         System.err.println("dlar2v");
      }

      this.dlar2v(n, x, 0, y, 0, z, 0, incx, c, 0, s, 0, incc);
   }

   public void dlar2v(int n, double[] x, int offsetx, double[] y, int offsety, double[] z, int offsetz, int incx, double[] c, int offsetc, double[] s, int offsets, int incc) {
      if (debug) {
         System.err.println("dlar2v");
      }

      this.dlar2vK(n, x, offsetx, y, offsety, z, offsetz, incx, c, offsetc, s, offsets, incc);
   }

   protected abstract void dlar2vK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13);

   public void dlarf(String side, int m, int n, double[] v, int incv, double tau, double[] c, int Ldc, double[] work) {
      if (debug) {
         System.err.println("dlarf");
      }

      this.dlarf(side, m, n, v, 0, incv, tau, c, 0, Ldc, work, 0);
   }

   public void dlarf(String side, int m, int n, double[] v, int offsetv, int incv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlarf");
      }

      this.dlarfK(side, m, n, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
   }

   protected abstract void dlarfK(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13);

   public void dlarfb(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int ldv, double[] t, int ldt, double[] c, int Ldc, double[] work, int ldwork) {
      if (debug) {
         System.err.println("dlarfb");
      }

      this.dlarfb(side, trans, direct, storev, m, n, k, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
   }

   public void dlarfb(String side, String trans, String direct, String storev, int m, int n, int k, double[] v, int offsetv, int ldv, double[] t, int offsett, int ldt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int ldwork) {
      if (debug) {
         System.err.println("dlarfb");
      }

      this.dlarfbK(side, trans, direct, storev, m, n, k, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
   }

   protected abstract void dlarfbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19);

   public void dlarfg(int n, doubleW alpha, double[] x, int incx, doubleW tau) {
      if (debug) {
         System.err.println("dlarfg");
      }

      this.dlarfg(n, alpha, x, 0, incx, tau);
   }

   public void dlarfg(int n, doubleW alpha, double[] x, int offsetx, int incx, doubleW tau) {
      if (debug) {
         System.err.println("dlarfg");
      }

      this.dlarfgK(n, alpha, x, offsetx, incx, tau);
   }

   protected abstract void dlarfgK(int var1, doubleW var2, double[] var3, int var4, int var5, doubleW var6);

   public void dlarft(String direct, String storev, int n, int k, double[] v, int ldv, double[] tau, double[] t, int ldt) {
      if (debug) {
         System.err.println("dlarft");
      }

      this.dlarft(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
   }

   public void dlarft(String direct, String storev, int n, int k, double[] v, int offsetv, int ldv, double[] tau, int offsettau, double[] t, int offsett, int ldt) {
      if (debug) {
         System.err.println("dlarft");
      }

      this.dlarftK(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
   }

   protected abstract void dlarftK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   public void dlarfx(String side, int m, int n, double[] v, double tau, double[] c, int Ldc, double[] work) {
      if (debug) {
         System.err.println("dlarfx");
      }

      this.dlarfx(side, m, n, v, 0, tau, c, 0, Ldc, work, 0);
   }

   public void dlarfx(String side, int m, int n, double[] v, int offsetv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlarfx");
      }

      this.dlarfxK(side, m, n, v, offsetv, tau, c, offsetc, Ldc, work, offsetwork);
   }

   protected abstract void dlarfxK(String var1, int var2, int var3, double[] var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12);

   public void dlargv(int n, double[] x, int incx, double[] y, int incy, double[] c, int incc) {
      if (debug) {
         System.err.println("dlargv");
      }

      this.dlargv(n, x, 0, incx, y, 0, incy, c, 0, incc);
   }

   public void dlargv(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] c, int offsetc, int incc) {
      if (debug) {
         System.err.println("dlargv");
      }

      this.dlargvK(n, x, offsetx, incx, y, offsety, incy, c, offsetc, incc);
   }

   protected abstract void dlargvK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   public void dlarnv(int idist, int[] iseed, int n, double[] x) {
      if (debug) {
         System.err.println("dlarnv");
      }

      this.dlarnv(idist, iseed, 0, n, x, 0);
   }

   public void dlarnv(int idist, int[] iseed, int offsetiseed, int n, double[] x, int offsetx) {
      if (debug) {
         System.err.println("dlarnv");
      }

      this.dlarnvK(idist, iseed, offsetiseed, n, x, offsetx);
   }

   protected abstract void dlarnvK(int var1, int[] var2, int var3, int var4, double[] var5, int var6);

   public void dlarra(int n, double[] d, double[] e, double[] e2, double spltol, double tnrm, intW nsplit, int[] isplit, intW info) {
      if (debug) {
         System.err.println("dlarra");
      }

      this.dlarra(n, d, 0, e, 0, e2, 0, spltol, tnrm, nsplit, isplit, 0, info);
   }

   public void dlarra(int n, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double spltol, double tnrm, intW nsplit, int[] isplit, int offsetisplit, intW info) {
      if (debug) {
         System.err.println("dlarra");
      }

      this.dlarraK(n, d, offsetd, e, offsete, e2, offsete2, spltol, tnrm, nsplit, isplit, offsetisplit, info);
   }

   protected abstract void dlarraK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, intW var12, int[] var13, int var14, intW var15);

   public void dlarrb(int n, double[] d, double[] lld, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, double[] wgap, double[] werr, double[] work, int[] iwork, double pivmin, double spdiam, int twist, intW info) {
      if (debug) {
         System.err.println("dlarrb");
      }

      this.dlarrb(n, d, 0, lld, 0, ifirst, ilast, rtol1, rtol2, offset, w, 0, wgap, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, twist, info);
   }

   public void dlarrb(int n, double[] d, int offsetd, double[] lld, int offsetlld, int ifirst, int ilast, double rtol1, double rtol2, int offset, double[] w, int offsetw, double[] wgap, int offsetwgap, double[] werr, int offsetwerr, double[] work, int offsetwork, int[] iwork, int offsetiwork, double pivmin, double spdiam, int twist, intW info) {
      if (debug) {
         System.err.println("dlarrb");
      }

      this.dlarrbK(n, d, offsetd, lld, offsetlld, ifirst, ilast, rtol1, rtol2, offset, w, offsetw, wgap, offsetwgap, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, twist, info);
   }

   protected abstract void dlarrbK(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int var7, double var8, double var10, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int var22, double var23, double var25, int var27, intW var28);

   public void dlarrc(String jobt, int n, double vl, double vu, double[] d, double[] e, double pivmin, intW eigcnt, intW lcnt, intW rcnt, intW info) {
      if (debug) {
         System.err.println("dlarrc");
      }

      this.dlarrc(jobt, n, vl, vu, d, 0, e, 0, pivmin, eigcnt, lcnt, rcnt, info);
   }

   public void dlarrc(String jobt, int n, double vl, double vu, double[] d, int offsetd, double[] e, int offsete, double pivmin, intW eigcnt, intW lcnt, intW rcnt, intW info) {
      if (debug) {
         System.err.println("dlarrc");
      }

      this.dlarrcK(jobt, n, vl, vu, d, offsetd, e, offsete, pivmin, eigcnt, lcnt, rcnt, info);
   }

   protected abstract void dlarrcK(String var1, int var2, double var3, double var5, double[] var7, int var8, double[] var9, int var10, double var11, intW var13, intW var14, intW var15, intW var16);

   public void dlarrd(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, double reltol, double[] d, double[] e, double[] e2, double pivmin, int nsplit, int[] isplit, intW m, double[] w, double[] werr, doubleW wl, doubleW wu, int[] iblock, int[] indexw, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlarrd");
      }

      this.dlarrd(range, order, n, vl, vu, il, iu, gers, 0, reltol, d, 0, e, 0, e2, 0, pivmin, nsplit, isplit, 0, m, w, 0, werr, 0, wl, wu, iblock, 0, indexw, 0, work, 0, iwork, 0, info);
   }

   public void dlarrd(String range, String order, int n, double vl, double vu, int il, int iu, double[] gers, int offsetgers, double reltol, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double pivmin, int nsplit, int[] isplit, int offsetisplit, intW m, double[] w, int offsetw, double[] werr, int offsetwerr, doubleW wl, doubleW wu, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlarrd");
      }

      this.dlarrdK(range, order, n, vl, vu, il, iu, gers, offsetgers, reltol, d, offsetd, e, offsete, e2, offsete2, pivmin, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wl, wu, iblock, offsetiblock, indexw, offsetindexw, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlarrdK(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, double var12, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double var20, int var22, int[] var23, int var24, intW var25, double[] var26, int var27, double[] var28, int var29, doubleW var30, doubleW var31, int[] var32, int var33, int[] var34, int var35, double[] var36, int var37, int[] var38, int var39, intW var40);

   public void dlarre(String range, int n, doubleW vl, doubleW vu, int il, int iu, double[] d, double[] e, double[] e2, double rtol1, double rtol2, double spltol, intW nsplit, int[] isplit, intW m, double[] w, double[] werr, double[] wgap, int[] iblock, int[] indexw, double[] gers, doubleW pivmin, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlarre");
      }

      this.dlarre(range, n, vl, vu, il, iu, d, 0, e, 0, e2, 0, rtol1, rtol2, spltol, nsplit, isplit, 0, m, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, pivmin, work, 0, iwork, 0, info);
   }

   public void dlarre(String range, int n, doubleW vl, doubleW vu, int il, int iu, double[] d, int offsetd, double[] e, int offsete, double[] e2, int offsete2, double rtol1, double rtol2, double spltol, intW nsplit, int[] isplit, int offsetisplit, intW m, double[] w, int offsetw, double[] werr, int offsetwerr, double[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] gers, int offsetgers, doubleW pivmin, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlarre");
      }

      this.dlarreK(range, n, vl, vu, il, iu, d, offsetd, e, offsete, e2, offsete2, rtol1, rtol2, spltol, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, pivmin, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlarreK(String var1, int var2, doubleW var3, doubleW var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double var13, double var15, double var17, intW var19, int[] var20, int var21, intW var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int[] var29, int var30, int[] var31, int var32, double[] var33, int var34, doubleW var35, double[] var36, int var37, int[] var38, int var39, intW var40);

   public void dlarrf(int n, double[] d, double[] l, double[] ld, int clstrt, int clend, double[] w, double[] wgap, double[] werr, double spdiam, double clgapl, double clgapr, double pivmin, doubleW sigma, double[] dplus, double[] lplus, double[] work, intW info) {
      if (debug) {
         System.err.println("dlarrf");
      }

      this.dlarrf(n, d, 0, l, 0, ld, 0, clstrt, clend, w, 0, wgap, 0, werr, 0, spdiam, clgapl, clgapr, pivmin, sigma, dplus, 0, lplus, 0, work, 0, info);
   }

   public void dlarrf(int n, double[] d, int offsetd, double[] l, int offsetl, double[] ld, int offsetld, int clstrt, int clend, double[] w, int offsetw, double[] wgap, int offsetwgap, double[] werr, int offsetwerr, double spdiam, double clgapl, double clgapr, double pivmin, doubleW sigma, double[] dplus, int offsetdplus, double[] lplus, int offsetlplus, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlarrf");
      }

      this.dlarrfK(n, d, offsetd, l, offsetl, ld, offsetld, clstrt, clend, w, offsetw, wgap, offsetwgap, werr, offsetwerr, spdiam, clgapl, clgapr, pivmin, sigma, dplus, offsetdplus, lplus, offsetlplus, work, offsetwork, info);
   }

   protected abstract void dlarrfK(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double var16, double var18, double var20, double var22, doubleW var24, double[] var25, int var26, double[] var27, int var28, double[] var29, int var30, intW var31);

   public void dlarrj(int n, double[] d, double[] e2, int ifirst, int ilast, double rtol, int offset, double[] w, double[] werr, double[] work, int[] iwork, double pivmin, double spdiam, intW info) {
      if (debug) {
         System.err.println("dlarrj");
      }

      this.dlarrj(n, d, 0, e2, 0, ifirst, ilast, rtol, offset, w, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, info);
   }

   public void dlarrj(int n, double[] d, int offsetd, double[] e2, int offsete2, int ifirst, int ilast, double rtol, int offset, double[] w, int offsetw, double[] werr, int offsetwerr, double[] work, int offsetwork, int[] iwork, int offsetiwork, double pivmin, double spdiam, intW info) {
      if (debug) {
         System.err.println("dlarrj");
      }

      this.dlarrjK(n, d, offsetd, e2, offsete2, ifirst, ilast, rtol, offset, w, offsetw, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, info);
   }

   protected abstract void dlarrjK(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int var7, double var8, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int var18, double var19, double var21, intW var23);

   public void dlarrk(int n, int iw, double gl, double gu, double[] d, double[] e2, double pivmin, double reltol, doubleW w, doubleW werr, intW info) {
      if (debug) {
         System.err.println("dlarrk");
      }

      this.dlarrk(n, iw, gl, gu, d, 0, e2, 0, pivmin, reltol, w, werr, info);
   }

   public void dlarrk(int n, int iw, double gl, double gu, double[] d, int offsetd, double[] e2, int offsete2, double pivmin, double reltol, doubleW w, doubleW werr, intW info) {
      if (debug) {
         System.err.println("dlarrk");
      }

      this.dlarrkK(n, iw, gl, gu, d, offsetd, e2, offsete2, pivmin, reltol, w, werr, info);
   }

   protected abstract void dlarrkK(int var1, int var2, double var3, double var5, double[] var7, int var8, double[] var9, int var10, double var11, double var13, doubleW var15, doubleW var16, intW var17);

   public void dlarrr(int n, double[] d, double[] e, intW info) {
      if (debug) {
         System.err.println("dlarrr");
      }

      this.dlarrr(n, d, 0, e, 0, info);
   }

   public void dlarrr(int n, double[] d, int offsetd, double[] e, int offsete, intW info) {
      if (debug) {
         System.err.println("dlarrr");
      }

      this.dlarrrK(n, d, offsetd, e, offsete, info);
   }

   protected abstract void dlarrrK(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   public void dlarrv(int n, double vl, double vu, double[] d, double[] l, double pivmin, int[] isplit, int m, int dol, int dou, double minrgp, doubleW rtol1, doubleW rtol2, double[] w, double[] werr, double[] wgap, int[] iblock, int[] indexw, double[] gers, double[] z, int ldz, int[] isuppz, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlarrv");
      }

      this.dlarrv(n, vl, vu, d, 0, l, 0, pivmin, isplit, 0, m, dol, dou, minrgp, rtol1, rtol2, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, z, 0, ldz, isuppz, 0, work, 0, iwork, 0, info);
   }

   public void dlarrv(int n, double vl, double vu, double[] d, int offsetd, double[] l, int offsetl, double pivmin, int[] isplit, int offsetisplit, int m, int dol, int dou, double minrgp, doubleW rtol1, doubleW rtol2, double[] w, int offsetw, double[] werr, int offsetwerr, double[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, double[] gers, int offsetgers, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlarrv");
      }

      this.dlarrvK(n, vl, vu, d, offsetd, l, offsetl, pivmin, isplit, offsetisplit, m, dol, dou, minrgp, rtol1, rtol2, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlarrvK(int var1, double var2, double var4, double[] var6, int var7, double[] var8, int var9, double var10, int[] var12, int var13, int var14, int var15, int var16, double var17, doubleW var19, doubleW var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int[] var27, int var28, int[] var29, int var30, double[] var31, int var32, double[] var33, int var34, int var35, int[] var36, int var37, double[] var38, int var39, int[] var40, int var41, intW var42);

   public void dlartg(double f, double g, doubleW cs, doubleW sn, doubleW r) {
      if (debug) {
         System.err.println("dlartg");
      }

      this.dlartgK(f, g, cs, sn, r);
   }

   protected abstract void dlartgK(double var1, double var3, doubleW var5, doubleW var6, doubleW var7);

   public void dlartv(int n, double[] x, int incx, double[] y, int incy, double[] c, double[] s, int incc) {
      if (debug) {
         System.err.println("dlartv");
      }

      this.dlartv(n, x, 0, incx, y, 0, incy, c, 0, s, 0, incc);
   }

   public void dlartv(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] c, int offsetc, double[] s, int offsets, int incc) {
      if (debug) {
         System.err.println("dlartv");
      }

      this.dlartvK(n, x, offsetx, incx, y, offsety, incy, c, offsetc, s, offsets, incc);
   }

   protected abstract void dlartvK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   public void dlaruv(int[] iseed, int n, double[] x) {
      if (debug) {
         System.err.println("dlaruv");
      }

      this.dlaruv(iseed, 0, n, x, 0);
   }

   public void dlaruv(int[] iseed, int offsetiseed, int n, double[] x, int offsetx) {
      if (debug) {
         System.err.println("dlaruv");
      }

      this.dlaruvK(iseed, offsetiseed, n, x, offsetx);
   }

   protected abstract void dlaruvK(int[] var1, int var2, int var3, double[] var4, int var5);

   public void dlarz(String side, int m, int n, int l, double[] v, int incv, double tau, double[] c, int Ldc, double[] work) {
      if (debug) {
         System.err.println("dlarz");
      }

      this.dlarz(side, m, n, l, v, 0, incv, tau, c, 0, Ldc, work, 0);
   }

   public void dlarz(String side, int m, int n, int l, double[] v, int offsetv, int incv, double tau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlarz");
      }

      this.dlarzK(side, m, n, l, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
   }

   protected abstract void dlarzK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double var8, double[] var10, int var11, int var12, double[] var13, int var14);

   public void dlarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int ldv, double[] t, int ldt, double[] c, int Ldc, double[] work, int ldwork) {
      if (debug) {
         System.err.println("dlarzb");
      }

      this.dlarzb(side, trans, direct, storev, m, n, k, l, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
   }

   public void dlarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, double[] v, int offsetv, int ldv, double[] t, int offsett, int ldt, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int ldwork) {
      if (debug) {
         System.err.println("dlarzb");
      }

      this.dlarzbK(side, trans, direct, storev, m, n, k, l, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
   }

   protected abstract void dlarzbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20);

   public void dlarzt(String direct, String storev, int n, int k, double[] v, int ldv, double[] tau, double[] t, int ldt) {
      if (debug) {
         System.err.println("dlarzt");
      }

      this.dlarzt(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
   }

   public void dlarzt(String direct, String storev, int n, int k, double[] v, int offsetv, int ldv, double[] tau, int offsettau, double[] t, int offsett, int ldt) {
      if (debug) {
         System.err.println("dlarzt");
      }

      this.dlarztK(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
   }

   protected abstract void dlarztK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   public void dlas2(double f, double g, double h, doubleW ssmin, doubleW ssmax) {
      if (debug) {
         System.err.println("dlas2");
      }

      this.dlas2K(f, g, h, ssmin, ssmax);
   }

   protected abstract void dlas2K(double var1, double var3, double var5, doubleW var7, doubleW var8);

   public void dlascl(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dlascl");
      }

      this.dlascl(type, kl, ku, cfrom, cto, m, n, a, 0, lda, info);
   }

   public void dlascl(String type, int kl, int ku, double cfrom, double cto, int m, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dlascl");
      }

      this.dlasclK(type, kl, ku, cfrom, cto, m, n, a, offseta, lda, info);
   }

   protected abstract void dlasclK(String var1, int var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, int var12, intW var13);

   public void dlasd0(int n, int sqre, double[] d, double[] e, double[] u, int ldu, double[] vt, int ldvt, int smlsiz, int[] iwork, double[] work, intW info) {
      if (debug) {
         System.err.println("dlasd0");
      }

      this.dlasd0(n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, smlsiz, iwork, 0, work, 0, info);
   }

   public void dlasd0(int n, int sqre, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, int smlsiz, int[] iwork, int offsetiwork, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlasd0");
      }

      this.dlasd0K(n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, smlsiz, iwork, offsetiwork, work, offsetwork, info);
   }

   protected abstract void dlasd0K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int var13, int[] var14, int var15, double[] var16, int var17, intW var18);

   public void dlasd1(int nl, int nr, int sqre, double[] d, doubleW alpha, doubleW beta, double[] u, int ldu, double[] vt, int ldvt, int[] idxq, int[] iwork, double[] work, intW info) {
      if (debug) {
         System.err.println("dlasd1");
      }

      this.dlasd1(nl, nr, sqre, d, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, idxq, 0, iwork, 0, work, 0, info);
   }

   public void dlasd1(int nl, int nr, int sqre, double[] d, int offsetd, doubleW alpha, doubleW beta, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, int[] idxq, int offsetidxq, int[] iwork, int offsetiwork, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlasd1");
      }

      this.dlasd1K(nl, nr, sqre, d, offsetd, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, idxq, offsetidxq, iwork, offsetiwork, work, offsetwork, info);
   }

   protected abstract void dlasd1K(int var1, int var2, int var3, double[] var4, int var5, doubleW var6, doubleW var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, int[] var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   public void dlasd2(int nl, int nr, int sqre, intW k, double[] d, double[] z, double alpha, double beta, double[] u, int ldu, double[] vt, int ldvt, double[] dsigma, double[] u2, int ldu2, double[] vt2, int ldvt2, int[] idxp, int[] idx, int[] idxc, int[] idxq, int[] coltyp, intW info) {
      if (debug) {
         System.err.println("dlasd2");
      }

      this.dlasd2(nl, nr, sqre, k, d, 0, z, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, dsigma, 0, u2, 0, ldu2, vt2, 0, ldvt2, idxp, 0, idx, 0, idxc, 0, idxq, 0, coltyp, 0, info);
   }

   public void dlasd2(int nl, int nr, int sqre, intW k, double[] d, int offsetd, double[] z, int offsetz, double alpha, double beta, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int ldvt, double[] dsigma, int offsetdsigma, double[] u2, int offsetu2, int ldu2, double[] vt2, int offsetvt2, int ldvt2, int[] idxp, int offsetidxp, int[] idx, int offsetidx, int[] idxc, int offsetidxc, int[] idxq, int offsetidxq, int[] coltyp, int offsetcoltyp, intW info) {
      if (debug) {
         System.err.println("dlasd2");
      }

      this.dlasd2K(nl, nr, sqre, k, d, offsetd, z, offsetz, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, dsigma, offsetdsigma, u2, offsetu2, ldu2, vt2, offsetvt2, ldvt2, idxp, offsetidxp, idx, offsetidx, idxc, offsetidxc, idxq, offsetidxq, coltyp, offsetcoltyp, info);
   }

   protected abstract void dlasd2K(int var1, int var2, int var3, intW var4, double[] var5, int var6, double[] var7, int var8, double var9, double var11, double[] var13, int var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int[] var29, int var30, int[] var31, int var32, int[] var33, int var34, int[] var35, int var36, intW var37);

   public void dlasd3(int nl, int nr, int sqre, int k, double[] d, double[] q, int ldq, double[] dsigma, double[] u, int ldu, double[] u2, int ldu2, double[] vt, int ldvt, double[] vt2, int ldvt2, int[] idxc, int[] ctot, double[] z, intW info) {
      if (debug) {
         System.err.println("dlasd3");
      }

      this.dlasd3(nl, nr, sqre, k, d, 0, q, 0, ldq, dsigma, 0, u, 0, ldu, u2, 0, ldu2, vt, 0, ldvt, vt2, 0, ldvt2, idxc, 0, ctot, 0, z, 0, info);
   }

   public void dlasd3(int nl, int nr, int sqre, int k, double[] d, int offsetd, double[] q, int offsetq, int ldq, double[] dsigma, int offsetdsigma, double[] u, int offsetu, int ldu, double[] u2, int offsetu2, int ldu2, double[] vt, int offsetvt, int ldvt, double[] vt2, int offsetvt2, int ldvt2, int[] idxc, int offsetidxc, int[] ctot, int offsetctot, double[] z, int offsetz, intW info) {
      if (debug) {
         System.err.println("dlasd3");
      }

      this.dlasd3K(nl, nr, sqre, k, d, offsetd, q, offsetq, ldq, dsigma, offsetdsigma, u, offsetu, ldu, u2, offsetu2, ldu2, vt, offsetvt, ldvt, vt2, offsetvt2, ldvt2, idxc, offsetidxc, ctot, offsetctot, z, offsetz, info);
   }

   protected abstract void dlasd3K(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, intW var30);

   public void dlasd4(int n, int i, double[] d, double[] z, double[] delta, double rho, doubleW sigma, double[] work, intW info) {
      if (debug) {
         System.err.println("dlasd4");
      }

      this.dlasd4(n, i, d, 0, z, 0, delta, 0, rho, sigma, work, 0, info);
   }

   public void dlasd4(int n, int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, doubleW sigma, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlasd4");
      }

      this.dlasd4K(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, sigma, work, offsetwork, info);
   }

   protected abstract void dlasd4K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, double[] var12, int var13, intW var14);

   public void dlasd5(int i, double[] d, double[] z, double[] delta, double rho, doubleW dsigma, double[] work) {
      if (debug) {
         System.err.println("dlasd5");
      }

      this.dlasd5(i, d, 0, z, 0, delta, 0, rho, dsigma, work, 0);
   }

   public void dlasd5(int i, double[] d, int offsetd, double[] z, int offsetz, double[] delta, int offsetdelta, double rho, doubleW dsigma, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlasd5");
      }

      this.dlasd5K(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dsigma, work, offsetwork);
   }

   protected abstract void dlasd5K(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, doubleW var10, double[] var11, int var12);

   public void dlasd6(int icompq, int nl, int nr, int sqre, double[] d, double[] vf, double[] vl, doubleW alpha, doubleW beta, int[] idxq, int[] perm, intW givptr, int[] givcol, int ldgcol, double[] givnum, int ldgnum, double[] poles, double[] difl, double[] difr, double[] z, intW k, doubleW c, doubleW s, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlasd6");
      }

      this.dlasd6(icompq, nl, nr, sqre, d, 0, vf, 0, vl, 0, alpha, beta, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, iwork, 0, info);
   }

   public void dlasd6(int icompq, int nl, int nr, int sqre, double[] d, int offsetd, double[] vf, int offsetvf, double[] vl, int offsetvl, doubleW alpha, doubleW beta, int[] idxq, int offsetidxq, int[] perm, int offsetperm, intW givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, double[] poles, int offsetpoles, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, intW k, doubleW c, doubleW s, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlasd6");
      }

      this.dlasd6K(icompq, nl, nr, sqre, d, offsetd, vf, offsetvf, vl, offsetvl, alpha, beta, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlasd6K(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, doubleW var11, doubleW var12, int[] var13, int var14, int[] var15, int var16, intW var17, int[] var18, int var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, intW var32, doubleW var33, doubleW var34, double[] var35, int var36, int[] var37, int var38, intW var39);

   public void dlasd7(int icompq, int nl, int nr, int sqre, intW k, double[] d, double[] z, double[] zw, double[] vf, double[] vfw, double[] vl, double[] vlw, double alpha, double beta, double[] dsigma, int[] idx, int[] idxp, int[] idxq, int[] perm, intW givptr, int[] givcol, int ldgcol, double[] givnum, int ldgnum, doubleW c, doubleW s, intW info) {
      if (debug) {
         System.err.println("dlasd7");
      }

      this.dlasd7(icompq, nl, nr, sqre, k, d, 0, z, 0, zw, 0, vf, 0, vfw, 0, vl, 0, vlw, 0, alpha, beta, dsigma, 0, idx, 0, idxp, 0, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, c, s, info);
   }

   public void dlasd7(int icompq, int nl, int nr, int sqre, intW k, double[] d, int offsetd, double[] z, int offsetz, double[] zw, int offsetzw, double[] vf, int offsetvf, double[] vfw, int offsetvfw, double[] vl, int offsetvl, double[] vlw, int offsetvlw, double alpha, double beta, double[] dsigma, int offsetdsigma, int[] idx, int offsetidx, int[] idxp, int offsetidxp, int[] idxq, int offsetidxq, int[] perm, int offsetperm, intW givptr, int[] givcol, int offsetgivcol, int ldgcol, double[] givnum, int offsetgivnum, int ldgnum, doubleW c, doubleW s, intW info) {
      if (debug) {
         System.err.println("dlasd7");
      }

      this.dlasd7K(icompq, nl, nr, sqre, k, d, offsetd, z, offsetz, zw, offsetzw, vf, offsetvf, vfw, offsetvfw, vl, offsetvl, vlw, offsetvlw, alpha, beta, dsigma, offsetdsigma, idx, offsetidx, idxp, offsetidxp, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, c, s, info);
   }

   protected abstract void dlasd7K(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double var20, double var22, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34, int[] var35, int var36, int var37, double[] var38, int var39, int var40, doubleW var41, doubleW var42, intW var43);

   public void dlasd8(int icompq, int k, double[] d, double[] z, double[] vf, double[] vl, double[] difl, double[] difr, int lddifr, double[] dsigma, double[] work, intW info) {
      if (debug) {
         System.err.println("dlasd8");
      }

      this.dlasd8(icompq, k, d, 0, z, 0, vf, 0, vl, 0, difl, 0, difr, 0, lddifr, dsigma, 0, work, 0, info);
   }

   public void dlasd8(int icompq, int k, double[] d, int offsetd, double[] z, int offsetz, double[] vf, int offsetvf, double[] vl, int offsetvl, double[] difl, int offsetdifl, double[] difr, int offsetdifr, int lddifr, double[] dsigma, int offsetdsigma, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlasd8");
      }

      this.dlasd8K(icompq, k, d, offsetd, z, offsetz, vf, offsetvf, vl, offsetvl, difl, offsetdifl, difr, offsetdifr, lddifr, dsigma, offsetdsigma, work, offsetwork, info);
   }

   protected abstract void dlasd8K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, intW var20);

   public void dlasda(int icompq, int smlsiz, int n, int sqre, double[] d, double[] e, double[] u, int ldu, double[] vt, int[] k, double[] difl, double[] difr, double[] z, double[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, double[] givnum, double[] c, double[] s, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dlasda");
      }

      this.dlasda(icompq, smlsiz, n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
   }

   public void dlasda(int icompq, int smlsiz, int n, int sqre, double[] d, int offsetd, double[] e, int offsete, double[] u, int offsetu, int ldu, double[] vt, int offsetvt, int[] k, int offsetk, double[] difl, int offsetdifl, double[] difr, int offsetdifr, double[] z, int offsetz, double[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, double[] givnum, int offsetgivnum, double[] c, int offsetc, double[] s, int offsets, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dlasda");
      }

      this.dlasdaK(icompq, smlsiz, n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dlasdaK(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int var28, int[] var29, int var30, double[] var31, int var32, double[] var33, int var34, double[] var35, int var36, double[] var37, int var38, int[] var39, int var40, intW var41);

   public void dlasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, double[] e, double[] vt, int ldvt, double[] u, int ldu, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dlasdq");
      }

      this.dlasdq(uplo, sqre, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
   }

   public void dlasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, double[] d, int offsetd, double[] e, int offsete, double[] vt, int offsetvt, int ldvt, double[] u, int offsetu, int ldu, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlasdq");
      }

      this.dlasdqK(uplo, sqre, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dlasdqK(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, intW var22);

   public void dlasdt(int n, intW lvl, intW nd, int[] inode, int[] ndiml, int[] ndimr, int msub) {
      if (debug) {
         System.err.println("dlasdt");
      }

      this.dlasdt(n, lvl, nd, inode, 0, ndiml, 0, ndimr, 0, msub);
   }

   public void dlasdt(int n, intW lvl, intW nd, int[] inode, int offsetinode, int[] ndiml, int offsetndiml, int[] ndimr, int offsetndimr, int msub) {
      if (debug) {
         System.err.println("dlasdt");
      }

      this.dlasdtK(n, lvl, nd, inode, offsetinode, ndiml, offsetndiml, ndimr, offsetndimr, msub);
   }

   protected abstract void dlasdtK(int var1, intW var2, intW var3, int[] var4, int var5, int[] var6, int var7, int[] var8, int var9, int var10);

   public void dlaset(String uplo, int m, int n, double alpha, double beta, double[] a, int lda) {
      if (debug) {
         System.err.println("dlaset");
      }

      this.dlaset(uplo, m, n, alpha, beta, a, 0, lda);
   }

   public void dlaset(String uplo, int m, int n, double alpha, double beta, double[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("dlaset");
      }

      this.dlasetK(uplo, m, n, alpha, beta, a, offseta, lda);
   }

   protected abstract void dlasetK(String var1, int var2, int var3, double var4, double var6, double[] var8, int var9, int var10);

   public void dlasq1(int n, double[] d, double[] e, double[] work, intW info) {
      if (debug) {
         System.err.println("dlasq1");
      }

      this.dlasq1(n, d, 0, e, 0, work, 0, info);
   }

   public void dlasq1(int n, double[] d, int offsetd, double[] e, int offsete, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dlasq1");
      }

      this.dlasq1K(n, d, offsetd, e, offsete, work, offsetwork, info);
   }

   protected abstract void dlasq1K(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   public void dlasq2(int n, double[] z, intW info) {
      if (debug) {
         System.err.println("dlasq2");
      }

      this.dlasq2(n, z, 0, info);
   }

   public void dlasq2(int n, double[] z, int offsetz, intW info) {
      if (debug) {
         System.err.println("dlasq2");
      }

      this.dlasq2K(n, z, offsetz, info);
   }

   protected abstract void dlasq2K(int var1, double[] var2, int var3, intW var4);

   public void dlasq3(int i0, intW n0, double[] z, int pp, doubleW dmin, doubleW sigma, doubleW desig, doubleW qmax, intW nfail, intW iter, intW ndiv, boolean ieee) {
      if (debug) {
         System.err.println("dlasq3");
      }

      this.dlasq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
   }

   public void dlasq3(int i0, intW n0, double[] z, int offsetz, int pp, doubleW dmin, doubleW sigma, doubleW desig, doubleW qmax, intW nfail, intW iter, intW ndiv, boolean ieee) {
      if (debug) {
         System.err.println("dlasq3");
      }

      this.dlasq3K(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
   }

   protected abstract void dlasq3K(int var1, intW var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, intW var10, intW var11, intW var12, boolean var13);

   public void dlasq4(int i0, int n0, double[] z, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, doubleW tau, intW ttype) {
      if (debug) {
         System.err.println("dlasq4");
      }

      this.dlasq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
   }

   public void dlasq4(int i0, int n0, double[] z, int offsetz, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, doubleW tau, intW ttype) {
      if (debug) {
         System.err.println("dlasq4");
      }

      this.dlasq4K(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
   }

   protected abstract void dlasq4K(int var1, int var2, double[] var3, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, double var17, doubleW var19, intW var20);

   public void dlasq5(int i0, int n0, double[] z, int pp, double tau, doubleW dmin, doubleW dmin1, doubleW dmin2, doubleW dn, doubleW dnm1, doubleW dnm2, boolean ieee) {
      if (debug) {
         System.err.println("dlasq5");
      }

      this.dlasq5(i0, n0, z, 0, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
   }

   public void dlasq5(int i0, int n0, double[] z, int offsetz, int pp, double tau, doubleW dmin, doubleW dmin1, doubleW dmin2, doubleW dn, doubleW dnm1, doubleW dnm2, boolean ieee) {
      if (debug) {
         System.err.println("dlasq5");
      }

      this.dlasq5K(i0, n0, z, offsetz, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
   }

   protected abstract void dlasq5K(int var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12, doubleW var13, boolean var14);

   public void dlasq6(int i0, int n0, double[] z, int pp, doubleW dmin, doubleW dmin1, doubleW dmin2, doubleW dn, doubleW dnm1, doubleW dnm2) {
      if (debug) {
         System.err.println("dlasq6");
      }

      this.dlasq6(i0, n0, z, 0, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
   }

   public void dlasq6(int i0, int n0, double[] z, int offsetz, int pp, doubleW dmin, doubleW dmin1, doubleW dmin2, doubleW dn, doubleW dnm1, doubleW dnm2) {
      if (debug) {
         System.err.println("dlasq6");
      }

      this.dlasq6K(i0, n0, z, offsetz, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
   }

   protected abstract void dlasq6K(int var1, int var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11);

   public void dlasr(String side, String pivot, String direct, int m, int n, double[] c, double[] s, double[] a, int lda) {
      if (debug) {
         System.err.println("dlasr");
      }

      this.dlasr(side, pivot, direct, m, n, c, 0, s, 0, a, 0, lda);
   }

   public void dlasr(String side, String pivot, String direct, int m, int n, double[] c, int offsetc, double[] s, int offsets, double[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("dlasr");
      }

      this.dlasrK(side, pivot, direct, m, n, c, offsetc, s, offsets, a, offseta, lda);
   }

   protected abstract void dlasrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   public void dlasrt(String id, int n, double[] d, intW info) {
      if (debug) {
         System.err.println("dlasrt");
      }

      this.dlasrt(id, n, d, 0, info);
   }

   public void dlasrt(String id, int n, double[] d, int offsetd, intW info) {
      if (debug) {
         System.err.println("dlasrt");
      }

      this.dlasrtK(id, n, d, offsetd, info);
   }

   protected abstract void dlasrtK(String var1, int var2, double[] var3, int var4, intW var5);

   public void dlassq(int n, double[] x, int incx, doubleW scale, doubleW sumsq) {
      if (debug) {
         System.err.println("dlassq");
      }

      this.dlassq(n, x, 0, incx, scale, sumsq);
   }

   public void dlassq(int n, double[] x, int offsetx, int incx, doubleW scale, doubleW sumsq) {
      if (debug) {
         System.err.println("dlassq");
      }

      this.dlassqK(n, x, offsetx, incx, scale, sumsq);
   }

   protected abstract void dlassqK(int var1, double[] var2, int var3, int var4, doubleW var5, doubleW var6);

   public void dlasv2(double f, double g, double h, doubleW ssmin, doubleW ssmax, doubleW snr, doubleW csr, doubleW snl, doubleW csl) {
      if (debug) {
         System.err.println("dlasv2");
      }

      this.dlasv2K(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
   }

   protected abstract void dlasv2K(double var1, double var3, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12);

   public void dlaswp(int n, double[] a, int lda, int k1, int k2, int[] ipiv, int incx) {
      if (debug) {
         System.err.println("dlaswp");
      }

      this.dlaswp(n, a, 0, lda, k1, k2, ipiv, 0, incx);
   }

   public void dlaswp(int n, double[] a, int offseta, int lda, int k1, int k2, int[] ipiv, int offsetipiv, int incx) {
      if (debug) {
         System.err.println("dlaswp");
      }

      this.dlaswpK(n, a, offseta, lda, k1, k2, ipiv, offsetipiv, incx);
   }

   protected abstract void dlaswpK(int var1, double[] var2, int var3, int var4, int var5, int var6, int[] var7, int var8, int var9);

   public void dlasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int ldtl, double[] tr, int ldtr, double[] b, int ldb, doubleW scale, double[] x, int ldx, doubleW xnorm, intW info) {
      if (debug) {
         System.err.println("dlasy2");
      }

      this.dlasy2(ltranl, ltranr, isgn, n1, n2, tl, 0, ldtl, tr, 0, ldtr, b, 0, ldb, scale, x, 0, ldx, xnorm, info);
   }

   public void dlasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, double[] tl, int offsettl, int ldtl, double[] tr, int offsettr, int ldtr, double[] b, int offsetb, int ldb, doubleW scale, double[] x, int offsetx, int ldx, doubleW xnorm, intW info) {
      if (debug) {
         System.err.println("dlasy2");
      }

      this.dlasy2K(ltranl, ltranr, isgn, n1, n2, tl, offsettl, ldtl, tr, offsettr, ldtr, b, offsetb, ldb, scale, x, offsetx, ldx, xnorm, info);
   }

   protected abstract void dlasy2K(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, doubleW var15, double[] var16, int var17, int var18, doubleW var19, intW var20);

   public void dlasyf(String uplo, int n, int nb, intW kb, double[] a, int lda, int[] ipiv, double[] w, int ldw, intW info) {
      if (debug) {
         System.err.println("dlasyf");
      }

      this.dlasyf(uplo, n, nb, kb, a, 0, lda, ipiv, 0, w, 0, ldw, info);
   }

   public void dlasyf(String uplo, int n, int nb, intW kb, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] w, int offsetw, int ldw, intW info) {
      if (debug) {
         System.err.println("dlasyf");
      }

      this.dlasyfK(uplo, n, nb, kb, a, offseta, lda, ipiv, offsetipiv, w, offsetw, ldw, info);
   }

   protected abstract void dlasyfK(String var1, int var2, int var3, intW var4, double[] var5, int var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   public void dlatbs(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int ldab, double[] x, doubleW scale, double[] cnorm, intW info) {
      if (debug) {
         System.err.println("dlatbs");
      }

      this.dlatbs(uplo, trans, diag, normin, n, kd, ab, 0, ldab, x, 0, scale, cnorm, 0, info);
   }

   public void dlatbs(String uplo, String trans, String diag, String normin, int n, int kd, double[] ab, int offsetab, int ldab, double[] x, int offsetx, doubleW scale, double[] cnorm, int offsetcnorm, intW info) {
      if (debug) {
         System.err.println("dlatbs");
      }

      this.dlatbsK(uplo, trans, diag, normin, n, kd, ab, offsetab, ldab, x, offsetx, scale, cnorm, offsetcnorm, info);
   }

   protected abstract void dlatbsK(String var1, String var2, String var3, String var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, doubleW var12, double[] var13, int var14, intW var15);

   public void dlatdf(int ijob, int n, double[] z, int ldz, double[] rhs, doubleW rdsum, doubleW rdscal, int[] ipiv, int[] jpiv) {
      if (debug) {
         System.err.println("dlatdf");
      }

      this.dlatdf(ijob, n, z, 0, ldz, rhs, 0, rdsum, rdscal, ipiv, 0, jpiv, 0);
   }

   public void dlatdf(int ijob, int n, double[] z, int offsetz, int ldz, double[] rhs, int offsetrhs, doubleW rdsum, doubleW rdscal, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv) {
      if (debug) {
         System.err.println("dlatdf");
      }

      this.dlatdfK(ijob, n, z, offsetz, ldz, rhs, offsetrhs, rdsum, rdscal, ipiv, offsetipiv, jpiv, offsetjpiv);
   }

   protected abstract void dlatdfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, doubleW var8, doubleW var9, int[] var10, int var11, int[] var12, int var13);

   public void dlatps(String uplo, String trans, String diag, String normin, int n, double[] ap, double[] x, doubleW scale, double[] cnorm, intW info) {
      if (debug) {
         System.err.println("dlatps");
      }

      this.dlatps(uplo, trans, diag, normin, n, ap, 0, x, 0, scale, cnorm, 0, info);
   }

   public void dlatps(String uplo, String trans, String diag, String normin, int n, double[] ap, int offsetap, double[] x, int offsetx, doubleW scale, double[] cnorm, int offsetcnorm, intW info) {
      if (debug) {
         System.err.println("dlatps");
      }

      this.dlatpsK(uplo, trans, diag, normin, n, ap, offsetap, x, offsetx, scale, cnorm, offsetcnorm, info);
   }

   protected abstract void dlatpsK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, doubleW var10, double[] var11, int var12, intW var13);

   public void dlatrd(String uplo, int n, int nb, double[] a, int lda, double[] e, double[] tau, double[] w, int ldw) {
      if (debug) {
         System.err.println("dlatrd");
      }

      this.dlatrd(uplo, n, nb, a, 0, lda, e, 0, tau, 0, w, 0, ldw);
   }

   public void dlatrd(String uplo, int n, int nb, double[] a, int offseta, int lda, double[] e, int offsete, double[] tau, int offsettau, double[] w, int offsetw, int ldw) {
      if (debug) {
         System.err.println("dlatrd");
      }

      this.dlatrdK(uplo, n, nb, a, offseta, lda, e, offsete, tau, offsettau, w, offsetw, ldw);
   }

   protected abstract void dlatrdK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13);

   public void dlatrs(String uplo, String trans, String diag, String normin, int n, double[] a, int lda, double[] x, doubleW scale, double[] cnorm, intW info) {
      if (debug) {
         System.err.println("dlatrs");
      }

      this.dlatrs(uplo, trans, diag, normin, n, a, 0, lda, x, 0, scale, cnorm, 0, info);
   }

   public void dlatrs(String uplo, String trans, String diag, String normin, int n, double[] a, int offseta, int lda, double[] x, int offsetx, doubleW scale, double[] cnorm, int offsetcnorm, intW info) {
      if (debug) {
         System.err.println("dlatrs");
      }

      this.dlatrsK(uplo, trans, diag, normin, n, a, offseta, lda, x, offsetx, scale, cnorm, offsetcnorm, info);
   }

   protected abstract void dlatrsK(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, doubleW var11, double[] var12, int var13, intW var14);

   public void dlatrz(int m, int n, int l, double[] a, int lda, double[] tau, double[] work) {
      if (debug) {
         System.err.println("dlatrz");
      }

      this.dlatrz(m, n, l, a, 0, lda, tau, 0, work, 0);
   }

   public void dlatrz(int m, int n, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlatrz");
      }

      this.dlatrzK(m, n, l, a, offseta, lda, tau, offsettau, work, offsetwork);
   }

   protected abstract void dlatrzK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10);

   public void dlatzm(String side, int m, int n, double[] v, int incv, double tau, double[] c1, double[] c2, int Ldc, double[] work) {
      if (debug) {
         System.err.println("dlatzm");
      }

      this.dlatzm(side, m, n, v, 0, incv, tau, c1, 0, c2, 0, Ldc, work, 0);
   }

   public void dlatzm(String side, int m, int n, double[] v, int offsetv, int incv, double tau, double[] c1, int offsetc1, double[] c2, int offsetc2, int Ldc, double[] work, int offsetwork) {
      if (debug) {
         System.err.println("dlatzm");
      }

      this.dlatzmK(side, m, n, v, offsetv, incv, tau, c1, offsetc1, c2, offsetc2, Ldc, work, offsetwork);
   }

   protected abstract void dlatzmK(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15);

   public void dlauu2(String uplo, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dlauu2");
      }

      this.dlauu2(uplo, n, a, 0, lda, info);
   }

   public void dlauu2(String uplo, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dlauu2");
      }

      this.dlauu2K(uplo, n, a, offseta, lda, info);
   }

   protected abstract void dlauu2K(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   public void dlauum(String uplo, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dlauum");
      }

      this.dlauum(uplo, n, a, 0, lda, info);
   }

   public void dlauum(String uplo, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dlauum");
      }

      this.dlauumK(uplo, n, a, offseta, lda, info);
   }

   protected abstract void dlauumK(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   public void dlazq3(int i0, intW n0, double[] z, int pp, doubleW dmin, doubleW sigma, doubleW desig, doubleW qmax, intW nfail, intW iter, intW ndiv, boolean ieee, intW ttype, doubleW dmin1, doubleW dmin2, doubleW dn, doubleW dn1, doubleW dn2, doubleW tau) {
      if (debug) {
         System.err.println("dlazq3");
      }

      this.dlazq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
   }

   public void dlazq3(int i0, intW n0, double[] z, int offsetz, int pp, doubleW dmin, doubleW sigma, doubleW desig, doubleW qmax, intW nfail, intW iter, intW ndiv, boolean ieee, intW ttype, doubleW dmin1, doubleW dmin2, doubleW dn, doubleW dn1, doubleW dn2, doubleW tau) {
      if (debug) {
         System.err.println("dlazq3");
      }

      this.dlazq3K(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
   }

   protected abstract void dlazq3K(int var1, intW var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, intW var10, intW var11, intW var12, boolean var13, intW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19, doubleW var20);

   public void dlazq4(int i0, int n0, double[] z, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, doubleW tau, intW ttype, doubleW g) {
      if (debug) {
         System.err.println("dlazq4");
      }

      this.dlazq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
   }

   public void dlazq4(int i0, int n0, double[] z, int offsetz, int pp, int n0in, double dmin, double dmin1, double dmin2, double dn, double dn1, double dn2, doubleW tau, intW ttype, doubleW g) {
      if (debug) {
         System.err.println("dlazq4");
      }

      this.dlazq4K(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
   }

   protected abstract void dlazq4K(int var1, int var2, double[] var3, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, double var17, doubleW var19, intW var20, doubleW var21);

   public void dopgtr(String uplo, int n, double[] ap, double[] tau, double[] q, int ldq, double[] work, intW info) {
      if (debug) {
         System.err.println("dopgtr");
      }

      this.dopgtr(uplo, n, ap, 0, tau, 0, q, 0, ldq, work, 0, info);
   }

   public void dopgtr(String uplo, int n, double[] ap, int offsetap, double[] tau, int offsettau, double[] q, int offsetq, int ldq, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dopgtr");
      }

      this.dopgtrK(uplo, n, ap, offsetap, tau, offsettau, q, offsetq, ldq, work, offsetwork, info);
   }

   protected abstract void dopgtrK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   public void dopmtr(String side, String uplo, String trans, int m, int n, double[] ap, double[] tau, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dopmtr");
      }

      this.dopmtr(side, uplo, trans, m, n, ap, 0, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void dopmtr(String side, String uplo, String trans, int m, int n, double[] ap, int offsetap, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dopmtr");
      }

      this.dopmtrK(side, uplo, trans, m, n, ap, offsetap, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dopmtrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, intW var15);

   public void dorg2l(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dorg2l");
      }

      this.dorg2l(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dorg2l(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorg2l");
      }

      this.dorg2lK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dorg2lK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   public void dorg2r(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dorg2r");
      }

      this.dorg2r(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dorg2r(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorg2r");
      }

      this.dorg2rK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dorg2rK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   public void dorgbr(String vect, int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgbr");
      }

      this.dorgbr(vect, m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorgbr(String vect, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgbr");
      }

      this.dorgbrK(vect, m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorgbrK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   public void dorghr(int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorghr");
      }

      this.dorghr(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorghr(int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorghr");
      }

      this.dorghrK(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorghrK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dorgl2(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dorgl2");
      }

      this.dorgl2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dorgl2(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorgl2");
      }

      this.dorgl2K(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dorgl2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   public void dorglq(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorglq");
      }

      this.dorglq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorglq(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorglq");
      }

      this.dorglqK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorglqK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dorgql(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgql");
      }

      this.dorgql(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorgql(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgql");
      }

      this.dorgqlK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorgqlK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dorgqr(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgqr");
      }

      this.dorgqr(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorgqr(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgqr");
      }

      this.dorgqrK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorgqrK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dorgr2(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, intW info) {
      if (debug) {
         System.err.println("dorgr2");
      }

      this.dorgr2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void dorgr2(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorgr2");
      }

      this.dorgr2K(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void dorgr2K(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   public void dorgrq(int m, int n, int k, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgrq");
      }

      this.dorgrq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorgrq(int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgrq");
      }

      this.dorgrqK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorgrqK(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dorgtr(String uplo, int n, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgtr");
      }

      this.dorgtr(uplo, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dorgtr(String uplo, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dorgtr");
      }

      this.dorgtrK(uplo, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dorgtrK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dorm2l(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dorm2l");
      }

      this.dorm2l(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void dorm2l(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorm2l");
      }

      this.dorm2lK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dorm2lK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   public void dorm2r(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dorm2r");
      }

      this.dorm2r(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void dorm2r(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorm2r");
      }

      this.dorm2rK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dorm2rK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   public void dormbr(String vect, String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormbr");
      }

      this.dormbr(vect, side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormbr(String vect, String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormbr");
      }

      this.dormbrK(vect, side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormbrK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   public void dormhr(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormhr");
      }

      this.dormhr(side, trans, m, n, ilo, ihi, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormhr(String side, String trans, int m, int n, int ilo, int ihi, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormhr");
      }

      this.dormhrK(side, trans, m, n, ilo, ihi, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormhrK(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   public void dorml2(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dorml2");
      }

      this.dorml2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void dorml2(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dorml2");
      }

      this.dorml2K(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dorml2K(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   public void dormlq(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormlq");
      }

      this.dormlq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormlq(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormlq");
      }

      this.dormlqK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormlqK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dormql(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormql");
      }

      this.dormql(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormql(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormql");
      }

      this.dormqlK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormqlK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dormqr(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormqr");
      }

      this.dormqr(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormqr(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormqr");
      }

      this.dormqrK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormqrK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dormr2(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dormr2");
      }

      this.dormr2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void dormr2(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dormr2");
      }

      this.dormr2K(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dormr2K(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   public void dormr3(String side, String trans, int m, int n, int k, int l, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, intW info) {
      if (debug) {
         System.err.println("dormr3");
      }

      this.dormr3(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void dormr3(String side, String trans, int m, int n, int k, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dormr3");
      }

      this.dormr3K(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void dormr3K(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   public void dormrq(String side, String trans, int m, int n, int k, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormrq");
      }

      this.dormrq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormrq(String side, String trans, int m, int n, int k, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormrq");
      }

      this.dormrqK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormrqK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dormrz(String side, String trans, int m, int n, int k, int l, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormrz");
      }

      this.dormrz(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormrz(String side, String trans, int m, int n, int k, int l, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormrz");
      }

      this.dormrzK(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormrzK(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   public void dormtr(String side, String uplo, String trans, int m, int n, double[] a, int lda, double[] tau, double[] c, int Ldc, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dormtr");
      }

      this.dormtr(side, uplo, trans, m, n, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void dormtr(String side, String uplo, String trans, int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] c, int offsetc, int Ldc, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dormtr");
      }

      this.dormtrK(side, uplo, trans, m, n, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void dormtrK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   public void dpbcon(String uplo, int n, int kd, double[] ab, int ldab, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dpbcon");
      }

      this.dpbcon(uplo, n, kd, ab, 0, ldab, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dpbcon(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dpbcon");
      }

      this.dpbconK(uplo, n, kd, ab, offsetab, ldab, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dpbconK(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   public void dpbequ(String uplo, int n, int kd, double[] ab, int ldab, double[] s, doubleW scond, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dpbequ");
      }

      this.dpbequ(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, info);
   }

   public void dpbequ(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] s, int offsets, doubleW scond, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dpbequ");
      }

      this.dpbequK(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, info);
   }

   protected abstract void dpbequK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, doubleW var9, doubleW var10, intW var11);

   public void dpbrfs(String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dpbrfs");
      }

      this.dpbrfs(uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dpbrfs(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dpbrfs");
      }

      this.dpbrfsK(uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dpbrfsK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int[] var23, int var24, intW var25);

   public void dpbstf(String uplo, int n, int kd, double[] ab, int ldab, intW info) {
      if (debug) {
         System.err.println("dpbstf");
      }

      this.dpbstf(uplo, n, kd, ab, 0, ldab, info);
   }

   public void dpbstf(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, intW info) {
      if (debug) {
         System.err.println("dpbstf");
      }

      this.dpbstfK(uplo, n, kd, ab, offsetab, ldab, info);
   }

   protected abstract void dpbstfK(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   public void dpbsv(String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dpbsv");
      }

      this.dpbsv(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
   }

   public void dpbsv(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dpbsv");
      }

      this.dpbsvK(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
   }

   protected abstract void dpbsvK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dpbsvx(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] afb, int ldafb, StringW equed, double[] s, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dpbsvx");
      }

      this.dpbsvx(fact, uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dpbsvx(String fact, String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] afb, int offsetafb, int ldafb, StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dpbsvx");
      }

      this.dpbsvxK(fact, uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dpbsvxK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, StringW var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, doubleW var21, double[] var22, int var23, double[] var24, int var25, double[] var26, int var27, int[] var28, int var29, intW var30);

   public void dpbtf2(String uplo, int n, int kd, double[] ab, int ldab, intW info) {
      if (debug) {
         System.err.println("dpbtf2");
      }

      this.dpbtf2(uplo, n, kd, ab, 0, ldab, info);
   }

   public void dpbtf2(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, intW info) {
      if (debug) {
         System.err.println("dpbtf2");
      }

      this.dpbtf2K(uplo, n, kd, ab, offsetab, ldab, info);
   }

   protected abstract void dpbtf2K(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   public void dpbtrf(String uplo, int n, int kd, double[] ab, int ldab, intW info) {
      if (debug) {
         System.err.println("dpbtrf");
      }

      this.dpbtrf(uplo, n, kd, ab, 0, ldab, info);
   }

   public void dpbtrf(String uplo, int n, int kd, double[] ab, int offsetab, int ldab, intW info) {
      if (debug) {
         System.err.println("dpbtrf");
      }

      this.dpbtrfK(uplo, n, kd, ab, offsetab, ldab, info);
   }

   protected abstract void dpbtrfK(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   public void dpbtrs(String uplo, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dpbtrs");
      }

      this.dpbtrs(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
   }

   public void dpbtrs(String uplo, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dpbtrs");
      }

      this.dpbtrsK(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
   }

   protected abstract void dpbtrsK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dpocon(String uplo, int n, double[] a, int lda, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dpocon");
      }

      this.dpocon(uplo, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dpocon(String uplo, int n, double[] a, int offseta, int lda, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dpocon");
      }

      this.dpoconK(uplo, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dpoconK(String var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   public void dpoequ(int n, double[] a, int lda, double[] s, doubleW scond, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dpoequ");
      }

      this.dpoequ(n, a, 0, lda, s, 0, scond, amax, info);
   }

   public void dpoequ(int n, double[] a, int offseta, int lda, double[] s, int offsets, doubleW scond, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dpoequ");
      }

      this.dpoequK(n, a, offseta, lda, s, offsets, scond, amax, info);
   }

   protected abstract void dpoequK(int var1, double[] var2, int var3, int var4, double[] var5, int var6, doubleW var7, doubleW var8, intW var9);

   public void dporfs(String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dporfs");
      }

      this.dporfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dporfs(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dporfs");
      }

      this.dporfsK(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dporfsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   public void dposv(String uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dposv");
      }

      this.dposv(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
   }

   public void dposv(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dposv");
      }

      this.dposvK(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void dposvK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dposvx(String fact, String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, StringW equed, double[] s, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dposvx");
      }

      this.dposvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dposvx(String fact, String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dposvx");
      }

      this.dposvxK(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dposvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, StringW var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, doubleW var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int[] var27, int var28, intW var29);

   public void dpotf2(String uplo, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dpotf2");
      }

      this.dpotf2(uplo, n, a, 0, lda, info);
   }

   public void dpotf2(String uplo, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dpotf2");
      }

      this.dpotf2K(uplo, n, a, offseta, lda, info);
   }

   protected abstract void dpotf2K(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   public void dpotrf(String uplo, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dpotrf");
      }

      this.dpotrf(uplo, n, a, 0, lda, info);
   }

   public void dpotrf(String uplo, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dpotrf");
      }

      this.dpotrfK(uplo, n, a, offseta, lda, info);
   }

   protected abstract void dpotrfK(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   public void dpotri(String uplo, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dpotri");
      }

      this.dpotri(uplo, n, a, 0, lda, info);
   }

   public void dpotri(String uplo, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dpotri");
      }

      this.dpotriK(uplo, n, a, offseta, lda, info);
   }

   protected abstract void dpotriK(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   public void dpotrs(String uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dpotrs");
      }

      this.dpotrs(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
   }

   public void dpotrs(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dpotrs");
      }

      this.dpotrsK(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void dpotrsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dppcon(String uplo, int n, double[] ap, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dppcon");
      }

      this.dppcon(uplo, n, ap, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dppcon(String uplo, int n, double[] ap, int offsetap, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dppcon");
      }

      this.dppconK(uplo, n, ap, offsetap, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dppconK(String var1, int var2, double[] var3, int var4, double var5, doubleW var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   public void dppequ(String uplo, int n, double[] ap, double[] s, doubleW scond, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dppequ");
      }

      this.dppequ(uplo, n, ap, 0, s, 0, scond, amax, info);
   }

   public void dppequ(String uplo, int n, double[] ap, int offsetap, double[] s, int offsets, doubleW scond, doubleW amax, intW info) {
      if (debug) {
         System.err.println("dppequ");
      }

      this.dppequK(uplo, n, ap, offsetap, s, offsets, scond, amax, info);
   }

   protected abstract void dppequK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, doubleW var7, doubleW var8, intW var9);

   public void dpprfs(String uplo, int n, int nrhs, double[] ap, double[] afp, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dpprfs");
      }

      this.dpprfs(uplo, n, nrhs, ap, 0, afp, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dpprfs(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dpprfs");
      }

      this.dpprfsK(uplo, n, nrhs, ap, offsetap, afp, offsetafp, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dpprfsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   public void dppsv(String uplo, int n, int nrhs, double[] ap, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dppsv");
      }

      this.dppsv(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
   }

   public void dppsv(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dppsv");
      }

      this.dppsvK(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
   }

   protected abstract void dppsvK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, intW var9);

   public void dppsvx(String fact, String uplo, int n, int nrhs, double[] ap, double[] afp, StringW equed, double[] s, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dppsvx");
      }

      this.dppsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dppsvx(String fact, String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, StringW equed, double[] s, int offsets, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dppsvx");
      }

      this.dppsvxK(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dppsvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, StringW var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, doubleW var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, int[] var25, int var26, intW var27);

   public void dpptrf(String uplo, int n, double[] ap, intW info) {
      if (debug) {
         System.err.println("dpptrf");
      }

      this.dpptrf(uplo, n, ap, 0, info);
   }

   public void dpptrf(String uplo, int n, double[] ap, int offsetap, intW info) {
      if (debug) {
         System.err.println("dpptrf");
      }

      this.dpptrfK(uplo, n, ap, offsetap, info);
   }

   protected abstract void dpptrfK(String var1, int var2, double[] var3, int var4, intW var5);

   public void dpptri(String uplo, int n, double[] ap, intW info) {
      if (debug) {
         System.err.println("dpptri");
      }

      this.dpptri(uplo, n, ap, 0, info);
   }

   public void dpptri(String uplo, int n, double[] ap, int offsetap, intW info) {
      if (debug) {
         System.err.println("dpptri");
      }

      this.dpptriK(uplo, n, ap, offsetap, info);
   }

   protected abstract void dpptriK(String var1, int var2, double[] var3, int var4, intW var5);

   public void dpptrs(String uplo, int n, int nrhs, double[] ap, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dpptrs");
      }

      this.dpptrs(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
   }

   public void dpptrs(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dpptrs");
      }

      this.dpptrsK(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
   }

   protected abstract void dpptrsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, intW var9);

   public void dptcon(int n, double[] d, double[] e, double anorm, doubleW rcond, double[] work, intW info) {
      if (debug) {
         System.err.println("dptcon");
      }

      this.dptcon(n, d, 0, e, 0, anorm, rcond, work, 0, info);
   }

   public void dptcon(int n, double[] d, int offsetd, double[] e, int offsete, double anorm, doubleW rcond, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dptcon");
      }

      this.dptconK(n, d, offsetd, e, offsete, anorm, rcond, work, offsetwork, info);
   }

   protected abstract void dptconK(int var1, double[] var2, int var3, double[] var4, int var5, double var6, doubleW var8, double[] var9, int var10, intW var11);

   public void dpteqr(String compz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dpteqr");
      }

      this.dpteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
   }

   public void dpteqr(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dpteqr");
      }

      this.dpteqrK(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dpteqrK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   public void dptrfs(int n, int nrhs, double[] d, double[] e, double[] df, double[] ef, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, intW info) {
      if (debug) {
         System.err.println("dptrfs");
      }

      this.dptrfs(n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, info);
   }

   public void dptrfs(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] df, int offsetdf, double[] ef, int offsetef, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dptrfs");
      }

      this.dptrfsK(n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
   }

   protected abstract void dptrfsK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   public void dptsv(int n, int nrhs, double[] d, double[] e, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dptsv");
      }

      this.dptsv(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
   }

   public void dptsv(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dptsv");
      }

      this.dptsvK(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
   }

   protected abstract void dptsvK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dptsvx(String fact, int n, int nrhs, double[] d, double[] e, double[] df, double[] ef, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, intW info) {
      if (debug) {
         System.err.println("dptsvx");
      }

      this.dptsvx(fact, n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, info);
   }

   public void dptsvx(String fact, int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] df, int offsetdf, double[] ef, int offsetef, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dptsvx");
      }

      this.dptsvxK(fact, n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
   }

   protected abstract void dptsvxK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, doubleW var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, intW var25);

   public void dpttrf(int n, double[] d, double[] e, intW info) {
      if (debug) {
         System.err.println("dpttrf");
      }

      this.dpttrf(n, d, 0, e, 0, info);
   }

   public void dpttrf(int n, double[] d, int offsetd, double[] e, int offsete, intW info) {
      if (debug) {
         System.err.println("dpttrf");
      }

      this.dpttrfK(n, d, offsetd, e, offsete, info);
   }

   protected abstract void dpttrfK(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   public void dpttrs(int n, int nrhs, double[] d, double[] e, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dpttrs");
      }

      this.dpttrs(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
   }

   public void dpttrs(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dpttrs");
      }

      this.dpttrsK(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
   }

   protected abstract void dpttrsK(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dptts2(int n, int nrhs, double[] d, double[] e, double[] b, int ldb) {
      if (debug) {
         System.err.println("dptts2");
      }

      this.dptts2(n, nrhs, d, 0, e, 0, b, 0, ldb);
   }

   public void dptts2(int n, int nrhs, double[] d, int offsetd, double[] e, int offsete, double[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("dptts2");
      }

      this.dptts2K(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb);
   }

   protected abstract void dptts2K(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   public void drscl(int n, double sa, double[] sx, int incx) {
      if (debug) {
         System.err.println("drscl");
      }

      this.drscl(n, sa, sx, 0, incx);
   }

   public void drscl(int n, double sa, double[] sx, int offsetsx, int incx) {
      if (debug) {
         System.err.println("drscl");
      }

      this.drsclK(n, sa, sx, offsetsx, incx);
   }

   protected abstract void drsclK(int var1, double var2, double[] var4, int var5, int var6);

   public void dsbev(String jobz, String uplo, int n, int kd, double[] ab, int ldab, double[] w, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dsbev");
      }

      this.dsbev(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, info);
   }

   public void dsbev(String jobz, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsbev");
      }

      this.dsbevK(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dsbevK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, intW var15);

   public void dsbevd(String jobz, String uplo, int n, int kd, double[] ab, int ldab, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsbevd");
      }

      this.dsbevd(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dsbevd(String jobz, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsbevd");
      }

      this.dsbevdK(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dsbevdK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   public void dsbevx(String jobz, String range, String uplo, int n, int kd, double[] ab, int ldab, double[] q, int ldq, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dsbevx");
      }

      this.dsbevx(jobz, range, uplo, n, kd, ab, 0, ldab, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void dsbevx(String jobz, String range, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] q, int offsetq, int ldq, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dsbevx");
      }

      this.dsbevxK(jobz, range, uplo, n, kd, ab, offsetab, ldab, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dsbevxK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double var14, int var16, int var17, double var18, intW var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int[] var28, int var29, int[] var30, int var31, intW var32);

   public void dsbgst(String vect, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] x, int ldx, double[] work, intW info) {
      if (debug) {
         System.err.println("dsbgst");
      }

      this.dsbgst(vect, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, x, 0, ldx, work, 0, info);
   }

   public void dsbgst(String vect, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] x, int offsetx, int ldx, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsbgst");
      }

      this.dsbgstK(vect, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, x, offsetx, ldx, work, offsetwork, info);
   }

   protected abstract void dsbgstK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   public void dsbgv(String jobz, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] w, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dsbgv");
      }

      this.dsbgv(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, info);
   }

   public void dsbgv(String jobz, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsbgv");
      }

      this.dsbgvK(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dsbgvK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, intW var19);

   public void dsbgvd(String jobz, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsbgvd");
      }

      this.dsbgvd(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dsbgvd(String jobz, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsbgvd");
      }

      this.dsbgvdK(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dsbgvdK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, int[] var20, int var21, int var22, intW var23);

   public void dsbgvx(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int ldab, double[] bb, int ldbb, double[] q, int ldq, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dsbgvx");
      }

      this.dsbgvx(jobz, range, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void dsbgvx(String jobz, String range, String uplo, int n, int ka, int kb, double[] ab, int offsetab, int ldab, double[] bb, int offsetbb, int ldbb, double[] q, int offsetq, int ldq, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dsbgvx");
      }

      this.dsbgvxK(jobz, range, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dsbgvxK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double var16, double var18, int var20, int var21, double var22, intW var24, double[] var25, int var26, double[] var27, int var28, int var29, double[] var30, int var31, int[] var32, int var33, int[] var34, int var35, intW var36);

   public void dsbtrd(String vect, String uplo, int n, int kd, double[] ab, int ldab, double[] d, double[] e, double[] q, int ldq, double[] work, intW info) {
      if (debug) {
         System.err.println("dsbtrd");
      }

      this.dsbtrd(vect, uplo, n, kd, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, work, 0, info);
   }

   public void dsbtrd(String vect, String uplo, int n, int kd, double[] ab, int offsetab, int ldab, double[] d, int offsetd, double[] e, int offsete, double[] q, int offsetq, int ldq, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsbtrd");
      }

      this.dsbtrdK(vect, uplo, n, kd, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, work, offsetwork, info);
   }

   protected abstract void dsbtrdK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   public void dsgesv(int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] work, float[] swork, intW iter, intW info) {
      if (debug) {
         System.err.println("dsgesv");
      }

      this.dsgesv(n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, x, 0, ldx, work, 0, swork, 0, iter, info);
   }

   public void dsgesv(int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] work, int offsetwork, float[] swork, int offsetswork, intW iter, intW info) {
      if (debug) {
         System.err.println("dsgesv");
      }

      this.dsgesvK(n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, work, offsetwork, swork, offsetswork, iter, info);
   }

   protected abstract void dsgesvK(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, float[] var16, int var17, intW var18, intW var19);

   public void dspcon(String uplo, int n, double[] ap, int[] ipiv, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dspcon");
      }

      this.dspcon(uplo, n, ap, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dspcon(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dspcon");
      }

      this.dspconK(uplo, n, ap, offsetap, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dspconK(String var1, int var2, double[] var3, int var4, int[] var5, int var6, double var7, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   public void dspev(String jobz, String uplo, int n, double[] ap, double[] w, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dspev");
      }

      this.dspev(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, info);
   }

   public void dspev(String jobz, String uplo, int n, double[] ap, int offsetap, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dspev");
      }

      this.dspevK(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dspevK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, intW var13);

   public void dspevd(String jobz, String uplo, int n, double[] ap, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dspevd");
      }

      this.dspevd(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dspevd(String jobz, String uplo, int n, double[] ap, int offsetap, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dspevd");
      }

      this.dspevdK(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dspevdK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, int[] var14, int var15, int var16, intW var17);

   public void dspevx(String jobz, String range, String uplo, int n, double[] ap, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dspevx");
      }

      this.dspevx(jobz, range, uplo, n, ap, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void dspevx(String jobz, String range, String uplo, int n, double[] ap, int offsetap, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dspevx");
      }

      this.dspevxK(jobz, range, uplo, n, ap, offsetap, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dspevxK(String var1, String var2, String var3, int var4, double[] var5, int var6, double var7, double var9, int var11, int var12, double var13, intW var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int[] var23, int var24, int[] var25, int var26, intW var27);

   public void dspgst(int itype, String uplo, int n, double[] ap, double[] bp, intW info) {
      if (debug) {
         System.err.println("dspgst");
      }

      this.dspgst(itype, uplo, n, ap, 0, bp, 0, info);
   }

   public void dspgst(int itype, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, intW info) {
      if (debug) {
         System.err.println("dspgst");
      }

      this.dspgstK(itype, uplo, n, ap, offsetap, bp, offsetbp, info);
   }

   protected abstract void dspgstK(int var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   public void dspgv(int itype, String jobz, String uplo, int n, double[] ap, double[] bp, double[] w, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dspgv");
      }

      this.dspgv(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, info);
   }

   public void dspgv(int itype, String jobz, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dspgv");
      }

      this.dspgvK(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dspgvK(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   public void dspgvd(int itype, String jobz, String uplo, int n, double[] ap, double[] bp, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dspgvd");
      }

      this.dspgvd(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dspgvd(int itype, String jobz, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dspgvd");
      }

      this.dspgvdK(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dspgvdK(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, int[] var17, int var18, int var19, intW var20);

   public void dspgvx(int itype, String jobz, String range, String uplo, int n, double[] ap, double[] bp, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dspgvx");
      }

      this.dspgvx(itype, jobz, range, uplo, n, ap, 0, bp, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void dspgvx(int itype, String jobz, String range, String uplo, int n, double[] ap, int offsetap, double[] bp, int offsetbp, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dspgvx");
      }

      this.dspgvxK(itype, jobz, range, uplo, n, ap, offsetap, bp, offsetbp, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dspgvxK(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, int var14, int var15, double var16, intW var18, double[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, intW var30);

   public void dsprfs(String uplo, int n, int nrhs, double[] ap, double[] afp, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dsprfs");
      }

      this.dsprfs(uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dsprfs(String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dsprfs");
      }

      this.dsprfsK(uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dsprfsK(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   public void dspsv(String uplo, int n, int nrhs, double[] ap, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dspsv");
      }

      this.dspsv(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
   }

   public void dspsv(String uplo, int n, int nrhs, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dspsv");
      }

      this.dspsvK(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dspsvK(String var1, int var2, int var3, double[] var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dspsvx(String fact, String uplo, int n, int nrhs, double[] ap, double[] afp, int[] ipiv, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dspsvx");
      }

      this.dspsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dspsvx(String fact, String uplo, int n, int nrhs, double[] ap, int offsetap, double[] afp, int offsetafp, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dspsvx");
      }

      this.dspsvxK(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dspsvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, doubleW var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   public void dsptrd(String uplo, int n, double[] ap, double[] d, double[] e, double[] tau, intW info) {
      if (debug) {
         System.err.println("dsptrd");
      }

      this.dsptrd(uplo, n, ap, 0, d, 0, e, 0, tau, 0, info);
   }

   public void dsptrd(String uplo, int n, double[] ap, int offsetap, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, intW info) {
      if (debug) {
         System.err.println("dsptrd");
      }

      this.dsptrdK(uplo, n, ap, offsetap, d, offsetd, e, offsete, tau, offsettau, info);
   }

   protected abstract void dsptrdK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   public void dsptrf(String uplo, int n, double[] ap, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dsptrf");
      }

      this.dsptrf(uplo, n, ap, 0, ipiv, 0, info);
   }

   public void dsptrf(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dsptrf");
      }

      this.dsptrfK(uplo, n, ap, offsetap, ipiv, offsetipiv, info);
   }

   protected abstract void dsptrfK(String var1, int var2, double[] var3, int var4, int[] var5, int var6, intW var7);

   public void dsptri(String uplo, int n, double[] ap, int[] ipiv, double[] work, intW info) {
      if (debug) {
         System.err.println("dsptri");
      }

      this.dsptri(uplo, n, ap, 0, ipiv, 0, work, 0, info);
   }

   public void dsptri(String uplo, int n, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsptri");
      }

      this.dsptriK(uplo, n, ap, offsetap, ipiv, offsetipiv, work, offsetwork, info);
   }

   protected abstract void dsptriK(String var1, int var2, double[] var3, int var4, int[] var5, int var6, double[] var7, int var8, intW var9);

   public void dsptrs(String uplo, int n, int nrhs, double[] ap, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dsptrs");
      }

      this.dsptrs(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
   }

   public void dsptrs(String uplo, int n, int nrhs, double[] ap, int offsetap, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dsptrs");
      }

      this.dsptrsK(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dsptrsK(String var1, int var2, int var3, double[] var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dstebz(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, double[] e, intW m, intW nsplit, double[] w, int[] iblock, int[] isplit, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dstebz");
      }

      this.dstebz(range, order, n, vl, vu, il, iu, abstol, d, 0, e, 0, m, nsplit, w, 0, iblock, 0, isplit, 0, work, 0, iwork, 0, info);
   }

   public void dstebz(String range, String order, int n, double vl, double vu, int il, int iu, double abstol, double[] d, int offsetd, double[] e, int offsete, intW m, intW nsplit, double[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dstebz");
      }

      this.dstebzK(range, order, n, vl, vu, il, iu, abstol, d, offsetd, e, offsete, m, nsplit, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dstebzK(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double var10, double[] var12, int var13, double[] var14, int var15, intW var16, intW var17, double[] var18, int var19, int[] var20, int var21, int[] var22, int var23, double[] var24, int var25, int[] var26, int var27, intW var28);

   public void dstedc(String compz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstedc");
      }

      this.dstedc(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dstedc(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstedc");
      }

      this.dstedcK(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dstedcK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   public void dstegr(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, int[] isuppz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstegr");
      }

      this.dstegr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dstegr(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstegr");
      }

      this.dstegrK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dstegrK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   public void dstein(int n, double[] d, double[] e, int m, double[] w, int[] iblock, int[] isplit, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dstein");
      }

      this.dstein(n, d, 0, e, 0, m, w, 0, iblock, 0, isplit, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void dstein(int n, double[] d, int offsetd, double[] e, int offsete, int m, double[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dstein");
      }

      this.dsteinK(n, d, offsetd, e, offsete, m, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dsteinK(int var1, double[] var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int[] var9, int var10, int[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int[] var18, int var19, int[] var20, int var21, intW var22);

   public void dstemr(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, intW m, double[] w, double[] z, int ldz, int nzc, int[] isuppz, booleanW tryrac, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstemr");
      }

      this.dstemr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, m, w, 0, z, 0, ldz, nzc, isuppz, 0, tryrac, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dstemr(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int nzc, int[] isuppz, int offsetisuppz, booleanW tryrac, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstemr");
      }

      this.dstemrK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, m, w, offsetw, z, offsetz, ldz, nzc, isuppz, offsetisuppz, tryrac, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dstemrK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, intW var14, double[] var15, int var16, double[] var17, int var18, int var19, int var20, int[] var21, int var22, booleanW var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   public void dsteqr(String compz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dsteqr");
      }

      this.dsteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
   }

   public void dsteqr(String compz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsteqr");
      }

      this.dsteqrK(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dsteqrK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   public void dsterf(int n, double[] d, double[] e, intW info) {
      if (debug) {
         System.err.println("dsterf");
      }

      this.dsterf(n, d, 0, e, 0, info);
   }

   public void dsterf(int n, double[] d, int offsetd, double[] e, int offsete, intW info) {
      if (debug) {
         System.err.println("dsterf");
      }

      this.dsterfK(n, d, offsetd, e, offsete, info);
   }

   protected abstract void dsterfK(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   public void dstev(String jobz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, intW info) {
      if (debug) {
         System.err.println("dstev");
      }

      this.dstev(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
   }

   public void dstev(String jobz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dstev");
      }

      this.dstevK(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void dstevK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   public void dstevd(String jobz, int n, double[] d, double[] e, double[] z, int ldz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstevd");
      }

      this.dstevd(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dstevd(String jobz, int n, double[] d, int offsetd, double[] e, int offsete, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstevd");
      }

      this.dstevdK(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dstevdK(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   public void dstevr(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, int[] isuppz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstevr");
      }

      this.dstevr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dstevr(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dstevr");
      }

      this.dstevrK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dstevrK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   public void dstevx(String jobz, String range, int n, double[] d, double[] e, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dstevx");
      }

      this.dstevx(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void dstevx(String jobz, String range, int n, double[] d, int offsetd, double[] e, int offsete, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dstevx");
      }

      this.dstevxK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dstevxK(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   public void dsycon(String uplo, int n, double[] a, int lda, int[] ipiv, double anorm, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dsycon");
      }

      this.dsycon(uplo, n, a, 0, lda, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void dsycon(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double anorm, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dsycon");
      }

      this.dsyconK(uplo, n, a, offseta, lda, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dsyconK(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double var8, doubleW var10, double[] var11, int var12, int[] var13, int var14, intW var15);

   public void dsyev(String jobz, String uplo, int n, double[] a, int lda, double[] w, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dsyev");
      }

      this.dsyev(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, info);
   }

   public void dsyev(String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] w, int offsetw, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dsyev");
      }

      this.dsyevK(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, info);
   }

   protected abstract void dsyevK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dsyevd(String jobz, String uplo, int n, double[] a, int lda, double[] w, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsyevd");
      }

      this.dsyevd(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dsyevd(String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] w, int offsetw, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsyevd");
      }

      this.dsyevdK(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dsyevdK(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, int var14, intW var15);

   public void dsyevr(String jobz, String range, String uplo, int n, double[] a, int lda, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, int[] isuppz, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsyevr");
      }

      this.dsyevr(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dsyevr(String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsyevr");
      }

      this.checkArgument("DSYEVR", 1, this.lsame("N", jobz) || this.lsame("V", jobz));
      this.checkArgument("DSYEVR", 2, this.lsame("A", range) || this.lsame("V", range) || this.lsame("I", range));
      this.checkArgument("DSYEVR", 3, this.lsame("U", uplo) || this.lsame("L", uplo));
      this.checkArgument("DSYEVR", 4, n >= 0);
      this.checkArgument("DSYEVR", 6, lda >= Math.max(1, n));
      if ((lwork != -1 || liwork != -1) && this.lsame("V", range)) {
         this.checkArgument("DSYEVR", 6, vl < vu);
         this.checkArgument("DSYEVR", 8, n > 0 && 1 <= il && il <= iu || n == 0 && il == 0);
         this.checkArgument("DSYEVR", 9, n > 0 && 1 <= iu && iu <= n || n == 0 && iu == 0);
      }

      this.checkArgument("DSYEVR", 15, this.lsame("V", jobz) && ldz >= Math.max(1, n) || this.lsame("N", jobz) && ldz >= 1);
      this.checkArgument("DSYEVR", 18, lwork == -1 || lwork >= Math.max(1, 26 * n));
      this.checkArgument("DSYEVR", 20, liwork == -1 || liwork >= Math.max(1, 10 * n));
      this.requireNonNull(a);
      this.requireNonNull(m);
      this.requireNonNull(w);
      if (this.lsame("N", jobz)) {
         this.requireNonNull(z);
      }

      this.requireNonNull(isuppz);
      this.requireNonNull(work);
      this.requireNonNull(iwork);
      this.requireNonNull(info);
      if (lwork != -1 || liwork != -1) {
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetw + n - 1, w.length);
         if (this.lsame("N", jobz)) {
            this.checkIndex(offsetz + Math.max(1, n) * ldz - 1, z.length);
         }

         this.checkIndex(offsetisuppz + 2 * Math.max(1, n) - 1, isuppz.length);
      }

      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.checkIndex(offsetiwork + Math.max(1, liwork) - 1, iwork.length);
      this.dsyevrK(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dsyevrK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   public void dsyevx(String jobz, String range, String uplo, int n, double[] a, int lda, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dsyevx");
      }

      this.dsyevx(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
   }

   public void dsyevx(String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dsyevx");
      }

      this.dsyevxK(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dsyevxK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, int[] var25, int var26, int[] var27, int var28, intW var29);

   public void dsygs2(int itype, String uplo, int n, double[] a, int lda, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dsygs2");
      }

      this.dsygs2(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
   }

   public void dsygs2(int itype, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dsygs2");
      }

      this.dsygs2K(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void dsygs2K(int var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dsygst(int itype, String uplo, int n, double[] a, int lda, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dsygst");
      }

      this.dsygst(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
   }

   public void dsygst(int itype, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dsygst");
      }

      this.dsygstK(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void dsygstK(int var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   public void dsygv(int itype, String jobz, String uplo, int n, double[] a, int lda, double[] b, int ldb, double[] w, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dsygv");
      }

      this.dsygv(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, info);
   }

   public void dsygv(int itype, String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] w, int offsetw, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dsygv");
      }

      this.dsygvK(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, info);
   }

   protected abstract void dsygvK(int var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, intW var16);

   public void dsygvd(int itype, String jobz, String uplo, int n, double[] a, int lda, double[] b, int ldb, double[] w, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsygvd");
      }

      this.dsygvd(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dsygvd(int itype, String jobz, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] w, int offsetw, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dsygvd");
      }

      this.dsygvdK(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dsygvdK(int var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   public void dsygvx(int itype, String jobz, String range, String uplo, int n, double[] a, int lda, double[] b, int ldb, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, double[] z, int ldz, double[] work, int lwork, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("dsygvx");
      }

      this.dsygvx(itype, jobz, range, uplo, n, a, 0, lda, b, 0, ldb, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
   }

   public void dsygvx(int itype, String jobz, String range, String uplo, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double vl, double vu, int il, int iu, double abstol, intW m, double[] w, int offsetw, double[] z, int offsetz, int ldz, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("dsygvx");
      }

      this.dsygvxK(itype, jobz, range, uplo, n, a, offseta, lda, b, offsetb, ldb, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void dsygvxK(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double var14, int var16, int var17, double var18, intW var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int var28, int[] var29, int var30, int[] var31, int var32, intW var33);

   public void dsyrfs(String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dsyrfs");
      }

      this.dsyrfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dsyrfs(String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dsyrfs");
      }

      this.dsyrfsK(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dsyrfsK(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   public void dsysv(String uplo, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dsysv");
      }

      this.dsysv(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, work, 0, lwork, info);
   }

   public void dsysv(String uplo, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dsysv");
      }

      this.dsysvK(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, work, offsetwork, lwork, info);
   }

   protected abstract void dsysvK(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, intW var15);

   public void dsysvx(String fact, String uplo, int n, int nrhs, double[] a, int lda, double[] af, int ldaf, int[] ipiv, double[] b, int ldb, double[] x, int ldx, doubleW rcond, double[] ferr, double[] berr, double[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dsysvx");
      }

      this.dsysvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, lwork, iwork, 0, info);
   }

   public void dsysvx(String fact, String uplo, int n, int nrhs, double[] a, int offseta, int lda, double[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, doubleW rcond, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dsysvx");
      }

      this.dsysvxK(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void dsysvxK(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, int[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, doubleW var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   public void dsytd2(String uplo, int n, double[] a, int lda, double[] d, double[] e, double[] tau, intW info) {
      if (debug) {
         System.err.println("dsytd2");
      }

      this.dsytd2(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, info);
   }

   public void dsytd2(String uplo, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, intW info) {
      if (debug) {
         System.err.println("dsytd2");
      }

      this.dsytd2K(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, info);
   }

   protected abstract void dsytd2K(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12);

   public void dsytf2(String uplo, int n, double[] a, int lda, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("dsytf2");
      }

      this.dsytf2(uplo, n, a, 0, lda, ipiv, 0, info);
   }

   public void dsytf2(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("dsytf2");
      }

      this.dsytf2K(uplo, n, a, offseta, lda, ipiv, offsetipiv, info);
   }

   protected abstract void dsytf2K(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   public void dsytrd(String uplo, int n, double[] a, int lda, double[] d, double[] e, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dsytrd");
      }

      this.dsytrd(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, work, 0, lwork, info);
   }

   public void dsytrd(String uplo, int n, double[] a, int offseta, int lda, double[] d, int offsetd, double[] e, int offsete, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dsytrd");
      }

      this.dsytrdK(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dsytrdK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, intW var15);

   public void dsytrf(String uplo, int n, double[] a, int lda, int[] ipiv, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dsytrf");
      }

      this.dsytrf(uplo, n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
   }

   public void dsytrf(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dsytrf");
      }

      this.dsytrfK(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
   }

   protected abstract void dsytrfK(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dsytri(String uplo, int n, double[] a, int lda, int[] ipiv, double[] work, intW info) {
      if (debug) {
         System.err.println("dsytri");
      }

      this.dsytri(uplo, n, a, 0, lda, ipiv, 0, work, 0, info);
   }

   public void dsytri(String uplo, int n, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dsytri");
      }

      this.dsytriK(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, info);
   }

   protected abstract void dsytriK(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, intW var10);

   public void dsytrs(String uplo, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dsytrs");
      }

      this.dsytrs(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
   }

   public void dsytrs(String uplo, int n, int nrhs, double[] a, int offseta, int lda, int[] ipiv, int offsetipiv, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dsytrs");
      }

      this.dsytrsK(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void dsytrsK(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dtbcon(String norm, String uplo, String diag, int n, int kd, double[] ab, int ldab, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtbcon");
      }

      this.dtbcon(norm, uplo, diag, n, kd, ab, 0, ldab, rcond, work, 0, iwork, 0, info);
   }

   public void dtbcon(String norm, String uplo, String diag, int n, int kd, double[] ab, int offsetab, int ldab, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtbcon");
      }

      this.dtbconK(norm, uplo, diag, n, kd, ab, offsetab, ldab, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dtbconK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   public void dtbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtbrfs");
      }

      this.dtbrfs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dtbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtbrfs");
      }

      this.dtbrfsK(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dtbrfsK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   public void dtbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int ldab, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dtbtrs");
      }

      this.dtbtrs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
   }

   public void dtbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, double[] ab, int offsetab, int ldab, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dtbtrs");
      }

      this.dtbtrsK(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
   }

   protected abstract void dtbtrsK(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, intW var13);

   public void dtgevc(String side, String howmny, boolean[] select, int n, double[] s, int lds, double[] p, int ldp, double[] vl, int ldvl, double[] vr, int ldvr, int mm, intW m, double[] work, intW info) {
      if (debug) {
         System.err.println("dtgevc");
      }

      this.dtgevc(side, howmny, select, 0, n, s, 0, lds, p, 0, ldp, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
   }

   public void dtgevc(String side, String howmny, boolean[] select, int offsetselect, int n, double[] s, int offsets, int lds, double[] p, int offsetp, int ldp, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, intW m, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dtgevc");
      }

      this.dtgevcK(side, howmny, select, offsetselect, n, s, offsets, lds, p, offsetp, ldp, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
   }

   protected abstract void dtgevcK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, int var18, intW var19, double[] var20, int var21, intW var22);

   public void dtgex2(boolean wantq, boolean wantz, int n, double[] a, int lda, double[] b, int ldb, double[] q, int ldq, double[] z, int ldz, int j1, int n1, int n2, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dtgex2");
      }

      this.dtgex2(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, j1, n1, n2, work, 0, lwork, info);
   }

   public void dtgex2(boolean wantq, boolean wantz, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, int j1, int n1, int n2, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dtgex2");
      }

      this.dtgex2K(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, j1, n1, n2, work, offsetwork, lwork, info);
   }

   protected abstract void dtgex2K(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int var16, int var17, int var18, double[] var19, int var20, int var21, intW var22);

   public void dtgexc(boolean wantq, boolean wantz, int n, double[] a, int lda, double[] b, int ldb, double[] q, int ldq, double[] z, int ldz, intW ifst, intW ilst, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dtgexc");
      }

      this.dtgexc(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, ifst, ilst, work, 0, lwork, info);
   }

   public void dtgexc(boolean wantq, boolean wantz, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, intW ifst, intW ilst, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dtgexc");
      }

      this.dtgexcK(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, ifst, ilst, work, offsetwork, lwork, info);
   }

   protected abstract void dtgexcK(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, intW var16, intW var17, double[] var18, int var19, int var20, intW var21);

   public void dtgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int n, double[] a, int lda, double[] b, int ldb, double[] alphar, double[] alphai, double[] beta, double[] q, int ldq, double[] z, int ldz, intW m, doubleW pl, doubleW pr, double[] dif, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dtgsen");
      }

      this.dtgsen(ijob, wantq, wantz, select, 0, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, m, pl, pr, dif, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dtgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int offsetselect, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] alphar, int offsetalphar, double[] alphai, int offsetalphai, double[] beta, int offsetbeta, double[] q, int offsetq, int ldq, double[] z, int offsetz, int ldz, intW m, doubleW pl, doubleW pr, double[] dif, int offsetdif, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dtgsen");
      }

      this.dtgsenK(ijob, wantq, wantz, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, m, pl, pr, dif, offsetdif, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dtgsenK(int var1, boolean var2, boolean var3, boolean[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25, doubleW var26, doubleW var27, double[] var28, int var29, double[] var30, int var31, int var32, int[] var33, int var34, int var35, intW var36);

   public void dtgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int lda, double[] b, int ldb, double tola, double tolb, double[] alpha, double[] beta, double[] u, int ldu, double[] v, int ldv, double[] q, int ldq, double[] work, intW ncycle, intW info) {
      if (debug) {
         System.err.println("dtgsja");
      }

      this.dtgsja(jobu, jobv, jobq, m, p, n, k, l, a, 0, lda, b, 0, ldb, tola, tolb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, ncycle, info);
   }

   public void dtgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double tola, double tolb, double[] alpha, int offsetalpha, double[] beta, int offsetbeta, double[] u, int offsetu, int ldu, double[] v, int offsetv, int ldv, double[] q, int offsetq, int ldq, double[] work, int offsetwork, intW ncycle, intW info) {
      if (debug) {
         System.err.println("dtgsja");
      }

      this.dtgsjaK(jobu, jobv, jobq, m, p, n, k, l, a, offseta, lda, b, offsetb, ldb, tola, tolb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, ncycle, info);
   }

   protected abstract void dtgsjaK(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double var15, double var17, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, intW var34, intW var35);

   public void dtgsna(String job, String howmny, boolean[] select, int n, double[] a, int lda, double[] b, int ldb, double[] vl, int ldvl, double[] vr, int ldvr, double[] s, double[] dif, int mm, intW m, double[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtgsna");
      }

      this.dtgsna(job, howmny, select, 0, n, a, 0, lda, b, 0, ldb, vl, 0, ldvl, vr, 0, ldvr, s, 0, dif, 0, mm, m, work, 0, lwork, iwork, 0, info);
   }

   public void dtgsna(String job, String howmny, boolean[] select, int offsetselect, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] s, int offsets, double[] dif, int offsetdif, int mm, intW m, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtgsna");
      }

      this.dtgsnaK(job, howmny, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, dif, offsetdif, mm, m, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void dtgsnaK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, intW var23, double[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   public void dtgsy2(String trans, int ijob, int m, int n, double[] a, int lda, double[] b, int ldb, double[] c, int Ldc, double[] d, int ldd, double[] e, int lde, double[] f, int ldf, doubleW scale, doubleW rdsum, doubleW rdscal, int[] iwork, intW pq, intW info) {
      if (debug) {
         System.err.println("dtgsy2");
      }

      this.dtgsy2(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, rdsum, rdscal, iwork, 0, pq, info);
   }

   public void dtgsy2(String trans, int ijob, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, double[] d, int offsetd, int ldd, double[] e, int offsete, int lde, double[] f, int offsetf, int ldf, doubleW scale, doubleW rdsum, doubleW rdscal, int[] iwork, int offsetiwork, intW pq, intW info) {
      if (debug) {
         System.err.println("dtgsy2");
      }

      this.dtgsy2K(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, rdsum, rdscal, iwork, offsetiwork, pq, info);
   }

   protected abstract void dtgsy2K(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, doubleW var23, doubleW var24, doubleW var25, int[] var26, int var27, intW var28, intW var29);

   public void dtgsyl(String trans, int ijob, int m, int n, double[] a, int lda, double[] b, int ldb, double[] c, int Ldc, double[] d, int ldd, double[] e, int lde, double[] f, int ldf, doubleW scale, doubleW dif, double[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtgsyl");
      }

      this.dtgsyl(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, dif, work, 0, lwork, iwork, 0, info);
   }

   public void dtgsyl(String trans, int ijob, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, double[] d, int offsetd, int ldd, double[] e, int offsete, int lde, double[] f, int offsetf, int ldf, doubleW scale, doubleW dif, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtgsyl");
      }

      this.dtgsylK(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, dif, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void dtgsylK(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, doubleW var23, doubleW var24, double[] var25, int var26, int var27, int[] var28, int var29, intW var30);

   public void dtpcon(String norm, String uplo, String diag, int n, double[] ap, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtpcon");
      }

      this.dtpcon(norm, uplo, diag, n, ap, 0, rcond, work, 0, iwork, 0, info);
   }

   public void dtpcon(String norm, String uplo, String diag, int n, double[] ap, int offsetap, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtpcon");
      }

      this.dtpconK(norm, uplo, diag, n, ap, offsetap, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dtpconK(String var1, String var2, String var3, int var4, double[] var5, int var6, doubleW var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   public void dtprfs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtprfs");
      }

      this.dtprfs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dtprfs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtprfs");
      }

      this.dtprfsK(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dtprfsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   public void dtptri(String uplo, String diag, int n, double[] ap, intW info) {
      if (debug) {
         System.err.println("dtptri");
      }

      this.dtptri(uplo, diag, n, ap, 0, info);
   }

   public void dtptri(String uplo, String diag, int n, double[] ap, int offsetap, intW info) {
      if (debug) {
         System.err.println("dtptri");
      }

      this.dtptriK(uplo, diag, n, ap, offsetap, info);
   }

   protected abstract void dtptriK(String var1, String var2, int var3, double[] var4, int var5, intW var6);

   public void dtptrs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dtptrs");
      }

      this.dtptrs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, info);
   }

   public void dtptrs(String uplo, String trans, String diag, int n, int nrhs, double[] ap, int offsetap, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dtptrs");
      }

      this.dtptrsK(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
   }

   protected abstract void dtptrsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public void dtrcon(String norm, String uplo, String diag, int n, double[] a, int lda, doubleW rcond, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtrcon");
      }

      this.dtrcon(norm, uplo, diag, n, a, 0, lda, rcond, work, 0, iwork, 0, info);
   }

   public void dtrcon(String norm, String uplo, String diag, int n, double[] a, int offseta, int lda, doubleW rcond, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtrcon");
      }

      this.dtrconK(norm, uplo, diag, n, a, offseta, lda, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dtrconK(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   public void dtrevc(String side, String howmny, boolean[] select, int n, double[] t, int ldt, double[] vl, int ldvl, double[] vr, int ldvr, int mm, intW m, double[] work, intW info) {
      if (debug) {
         System.err.println("dtrevc");
      }

      this.dtrevc(side, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
   }

   public void dtrevc(String side, String howmny, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, int mm, intW m, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dtrevc");
      }

      this.dtrevcK(side, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
   }

   protected abstract void dtrevcK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, int var15, intW var16, double[] var17, int var18, intW var19);

   public void dtrexc(String compq, int n, double[] t, int ldt, double[] q, int ldq, intW ifst, intW ilst, double[] work, intW info) {
      if (debug) {
         System.err.println("dtrexc");
      }

      this.dtrexc(compq, n, t, 0, ldt, q, 0, ldq, ifst, ilst, work, 0, info);
   }

   public void dtrexc(String compq, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, intW ifst, intW ilst, double[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("dtrexc");
      }

      this.dtrexcK(compq, n, t, offsett, ldt, q, offsetq, ldq, ifst, ilst, work, offsetwork, info);
   }

   protected abstract void dtrexcK(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9, intW var10, double[] var11, int var12, intW var13);

   public void dtrrfs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] x, int ldx, double[] ferr, double[] berr, double[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtrrfs");
      }

      this.dtrrfs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void dtrrfs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] x, int offsetx, int ldx, double[] ferr, int offsetferr, double[] berr, int offsetberr, double[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtrrfs");
      }

      this.dtrrfsK(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void dtrrfsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int var22, intW var23);

   public void dtrsen(String job, String compq, boolean[] select, int n, double[] t, int ldt, double[] q, int ldq, double[] wr, double[] wi, intW m, doubleW s, doubleW sep, double[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dtrsen");
      }

      this.dtrsen(job, compq, select, 0, n, t, 0, ldt, q, 0, ldq, wr, 0, wi, 0, m, s, sep, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void dtrsen(String job, String compq, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] q, int offsetq, int ldq, double[] wr, int offsetwr, double[] wi, int offsetwi, intW m, doubleW s, doubleW sep, double[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("dtrsen");
      }

      this.dtrsenK(job, compq, select, offsetselect, n, t, offsett, ldt, q, offsetq, ldq, wr, offsetwr, wi, offsetwi, m, s, sep, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void dtrsenK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16, doubleW var17, doubleW var18, double[] var19, int var20, int var21, int[] var22, int var23, int var24, intW var25);

   public void dtrsna(String job, String howmny, boolean[] select, int n, double[] t, int ldt, double[] vl, int ldvl, double[] vr, int ldvr, double[] s, double[] sep, int mm, intW m, double[] work, int ldwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("dtrsna");
      }

      this.dtrsna(job, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, s, 0, sep, 0, mm, m, work, 0, ldwork, iwork, 0, info);
   }

   public void dtrsna(String job, String howmny, boolean[] select, int offsetselect, int n, double[] t, int offsett, int ldt, double[] vl, int offsetvl, int ldvl, double[] vr, int offsetvr, int ldvr, double[] s, int offsets, double[] sep, int offsetsep, int mm, intW m, double[] work, int offsetwork, int ldwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("dtrsna");
      }

      this.dtrsnaK(job, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, sep, offsetsep, mm, m, work, offsetwork, ldwork, iwork, offsetiwork, info);
   }

   protected abstract void dtrsnaK(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, int var19, intW var20, double[] var21, int var22, int var23, int[] var24, int var25, intW var26);

   public void dtrsyl(String trana, String tranb, int isgn, int m, int n, double[] a, int lda, double[] b, int ldb, double[] c, int Ldc, doubleW scale, intW info) {
      if (debug) {
         System.err.println("dtrsyl");
      }

      this.dtrsyl(trana, tranb, isgn, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, scale, info);
   }

   public void dtrsyl(String trana, String tranb, int isgn, int m, int n, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double[] c, int offsetc, int Ldc, doubleW scale, intW info) {
      if (debug) {
         System.err.println("dtrsyl");
      }

      this.dtrsylK(trana, tranb, isgn, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, scale, info);
   }

   protected abstract void dtrsylK(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, doubleW var15, intW var16);

   public void dtrti2(String uplo, String diag, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dtrti2");
      }

      this.dtrti2(uplo, diag, n, a, 0, lda, info);
   }

   public void dtrti2(String uplo, String diag, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dtrti2");
      }

      this.dtrti2K(uplo, diag, n, a, offseta, lda, info);
   }

   protected abstract void dtrti2K(String var1, String var2, int var3, double[] var4, int var5, int var6, intW var7);

   public void dtrtri(String uplo, String diag, int n, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("dtrtri");
      }

      this.dtrtri(uplo, diag, n, a, 0, lda, info);
   }

   public void dtrtri(String uplo, String diag, int n, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("dtrtri");
      }

      this.dtrtriK(uplo, diag, n, a, offseta, lda, info);
   }

   protected abstract void dtrtriK(String var1, String var2, int var3, double[] var4, int var5, int var6, intW var7);

   public void dtrtrs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("dtrtrs");
      }

      this.dtrtrs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, info);
   }

   public void dtrtrs(String uplo, String trans, String diag, int n, int nrhs, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("dtrtrs");
      }

      this.dtrtrsK(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void dtrtrsK(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12);

   public void dtzrqf(int m, int n, double[] a, int lda, double[] tau, intW info) {
      if (debug) {
         System.err.println("dtzrqf");
      }

      this.dtzrqf(m, n, a, 0, lda, tau, 0, info);
   }

   public void dtzrqf(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, intW info) {
      if (debug) {
         System.err.println("dtzrqf");
      }

      this.dtzrqfK(m, n, a, offseta, lda, tau, offsettau, info);
   }

   protected abstract void dtzrqfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, intW var8);

   public void dtzrzf(int m, int n, double[] a, int lda, double[] tau, double[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("dtzrzf");
      }

      this.dtzrzf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void dtzrzf(int m, int n, double[] a, int offseta, int lda, double[] tau, int offsettau, double[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("dtzrzf");
      }

      this.dtzrzfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void dtzrzfK(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   public int ieeeck(int ispec, float zero, float one) {
      if (debug) {
         System.err.println("ieeeck");
      }

      return this.ieeeckK(ispec, zero, one);
   }

   protected abstract int ieeeckK(int var1, float var2, float var3);

   public int ilaenv(int ispec, String name, String opts, int n1, int n2, int n3, int n4) {
      if (debug) {
         System.err.println("ilaenv");
      }

      return this.ilaenvK(ispec, name, opts, n1, n2, n3, n4);
   }

   protected abstract int ilaenvK(int var1, String var2, String var3, int var4, int var5, int var6, int var7);

   public void ilaver(intW vers_major, intW vers_minor, intW vers_patch) {
      if (debug) {
         System.err.println("ilaver");
      }

      this.ilaverK(vers_major, vers_minor, vers_patch);
   }

   protected abstract void ilaverK(intW var1, intW var2, intW var3);

   public int iparmq(int ispec, String name, String opts, int n, int ilo, int ihi, int lwork) {
      if (debug) {
         System.err.println("iparmq");
      }

      return this.iparmqK(ispec, name, opts, n, ilo, ihi, lwork);
   }

   protected abstract int iparmqK(int var1, String var2, String var3, int var4, int var5, int var6, int var7);

   public boolean lsamen(int n, String ca, String cb) {
      if (debug) {
         System.err.println("lsamen");
      }

      return this.lsamenK(n, ca, cb);
   }

   protected abstract boolean lsamenK(int var1, String var2, String var3);

   public void sbdsdc(String uplo, String compq, int n, float[] d, float[] e, float[] u, int ldu, float[] vt, int ldvt, float[] q, int[] iq, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sbdsdc");
      }

      this.sbdsdc(uplo, compq, n, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, q, 0, iq, 0, work, 0, iwork, 0, info);
   }

   public void sbdsdc(String uplo, String compq, int n, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] q, int offsetq, int[] iq, int offsetiq, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sbdsdc");
      }

      this.sbdsdcK(uplo, compq, n, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, q, offsetq, iq, offsetiq, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sbdsdcK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   public void sbdsqr(String uplo, int n, int ncvt, int nru, int ncc, float[] d, float[] e, float[] vt, int ldvt, float[] u, int ldu, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sbdsqr");
      }

      this.sbdsqr(uplo, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
   }

   public void sbdsqr(String uplo, int n, int ncvt, int nru, int ncc, float[] d, int offsetd, float[] e, int offsete, float[] vt, int offsetvt, int ldvt, float[] u, int offsetu, int ldu, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sbdsqr");
      }

      this.sbdsqrK(uplo, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sbdsqrK(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, intW var21);

   public void sdisna(String job, int m, int n, float[] d, float[] sep, intW info) {
      if (debug) {
         System.err.println("sdisna");
      }

      this.sdisna(job, m, n, d, 0, sep, 0, info);
   }

   public void sdisna(String job, int m, int n, float[] d, int offsetd, float[] sep, int offsetsep, intW info) {
      if (debug) {
         System.err.println("sdisna");
      }

      this.sdisnaK(job, m, n, d, offsetd, sep, offsetsep, info);
   }

   protected abstract void sdisnaK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   public void sgbbrd(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int ldab, float[] d, float[] e, float[] q, int ldq, float[] pt, int ldpt, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sgbbrd");
      }

      this.sgbbrd(vect, m, n, ncc, kl, ku, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, pt, 0, ldpt, c, 0, Ldc, work, 0, info);
   }

   public void sgbbrd(String vect, int m, int n, int ncc, int kl, int ku, float[] ab, int offsetab, int ldab, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] pt, int offsetpt, int ldpt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgbbrd");
      }

      this.sgbbrdK(vect, m, n, ncc, kl, ku, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, pt, offsetpt, ldpt, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sgbbrdK(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, intW var25);

   public void sgbcon(String norm, int n, int kl, int ku, float[] ab, int ldab, int[] ipiv, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgbcon");
      }

      this.sgbcon(norm, n, kl, ku, ab, 0, ldab, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void sgbcon(String norm, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgbcon");
      }

      this.sgbconK(norm, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgbconK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, float var10, floatW var11, float[] var12, int var13, int[] var14, int var15, intW var16);

   public void sgbequ(int m, int n, int kl, int ku, float[] ab, int ldab, float[] r, float[] c, floatW rowcnd, floatW colcnd, floatW amax, intW info) {
      if (debug) {
         System.err.println("sgbequ");
      }

      this.sgbequ(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, info);
   }

   public void sgbequ(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] r, int offsetr, float[] c, int offsetc, floatW rowcnd, floatW colcnd, floatW amax, intW info) {
      if (debug) {
         System.err.println("sgbequ");
      }

      this.sgbequK(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
   }

   protected abstract void sgbequK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, floatW var12, floatW var13, floatW var14, intW var15);

   public void sgbrfs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgbrfs");
      }

      this.sgbrfs(trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sgbrfs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgbrfs");
      }

      this.sgbrfsK(trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgbrfsK(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, intW var28);

   public void sgbsv(int n, int kl, int ku, int nrhs, float[] ab, int ldab, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sgbsv");
      }

      this.sgbsv(n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
   }

   public void sgbsv(int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sgbsv");
      }

      this.sgbsvK(n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void sgbsvK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   public void sgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, int[] ipiv, StringW equed, float[] r, float[] c, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgbsvx");
      }

      this.sgbsvx(fact, trans, n, kl, ku, nrhs, ab, 0, ldab, afb, 0, ldafb, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sgbsvx(String fact, String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, int[] ipiv, int offsetipiv, StringW equed, float[] r, int offsetr, float[] c, int offsetc, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgbsvx");
      }

      this.sgbsvxK(fact, trans, n, kl, ku, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgbsvxK(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, StringW var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, floatW var26, float[] var27, int var28, float[] var29, int var30, float[] var31, int var32, int[] var33, int var34, intW var35);

   public void sgbtf2(int m, int n, int kl, int ku, float[] ab, int ldab, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("sgbtf2");
      }

      this.sgbtf2(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
   }

   public void sgbtf2(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("sgbtf2");
      }

      this.sgbtf2K(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
   }

   protected abstract void sgbtf2K(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   public void sgbtrf(int m, int n, int kl, int ku, float[] ab, int ldab, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("sgbtrf");
      }

      this.sgbtrf(m, n, kl, ku, ab, 0, ldab, ipiv, 0, info);
   }

   public void sgbtrf(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("sgbtrf");
      }

      this.sgbtrfK(m, n, kl, ku, ab, offsetab, ldab, ipiv, offsetipiv, info);
   }

   protected abstract void sgbtrfK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   public void sgbtrs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int ldab, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sgbtrs");
      }

      this.sgbtrs(trans, n, kl, ku, nrhs, ab, 0, ldab, ipiv, 0, b, 0, ldb, info);
   }

   public void sgbtrs(String trans, int n, int kl, int ku, int nrhs, float[] ab, int offsetab, int ldab, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sgbtrs");
      }

      this.sgbtrsK(trans, n, kl, ku, nrhs, ab, offsetab, ldab, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void sgbtrsK(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, int[] var9, int var10, float[] var11, int var12, int var13, intW var14);

   public void sgebak(String job, String side, int n, int ilo, int ihi, float[] scale, int m, float[] v, int ldv, intW info) {
      if (debug) {
         System.err.println("sgebak");
      }

      this.sgebak(job, side, n, ilo, ihi, scale, 0, m, v, 0, ldv, info);
   }

   public void sgebak(String job, String side, int n, int ilo, int ihi, float[] scale, int offsetscale, int m, float[] v, int offsetv, int ldv, intW info) {
      if (debug) {
         System.err.println("sgebak");
      }

      this.sgebakK(job, side, n, ilo, ihi, scale, offsetscale, m, v, offsetv, ldv, info);
   }

   protected abstract void sgebakK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sgebal(String job, int n, float[] a, int lda, intW ilo, intW ihi, float[] scale, intW info) {
      if (debug) {
         System.err.println("sgebal");
      }

      this.sgebal(job, n, a, 0, lda, ilo, ihi, scale, 0, info);
   }

   public void sgebal(String job, int n, float[] a, int offseta, int lda, intW ilo, intW ihi, float[] scale, int offsetscale, intW info) {
      if (debug) {
         System.err.println("sgebal");
      }

      this.sgebalK(job, n, a, offseta, lda, ilo, ihi, scale, offsetscale, info);
   }

   protected abstract void sgebalK(String var1, int var2, float[] var3, int var4, int var5, intW var6, intW var7, float[] var8, int var9, intW var10);

   public void sgebd2(int m, int n, float[] a, int lda, float[] d, float[] e, float[] tauq, float[] taup, float[] work, intW info) {
      if (debug) {
         System.err.println("sgebd2");
      }

      this.sgebd2(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, info);
   }

   public void sgebd2(int m, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgebd2");
      }

      this.sgebd2K(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, info);
   }

   protected abstract void sgebd2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16);

   public void sgebrd(int m, int n, float[] a, int lda, float[] d, float[] e, float[] tauq, float[] taup, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgebrd");
      }

      this.sgebrd(m, n, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, work, 0, lwork, info);
   }

   public void sgebrd(int m, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgebrd");
      }

      this.sgebrdK(m, n, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, work, offsetwork, lwork, info);
   }

   protected abstract void sgebrdK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sgecon(String norm, int n, float[] a, int lda, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgecon");
      }

      this.sgecon(norm, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void sgecon(String norm, int n, float[] a, int offseta, int lda, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgecon");
      }

      this.sgeconK(norm, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgeconK(String var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   public void sgeequ(int m, int n, float[] a, int lda, float[] r, float[] c, floatW rowcnd, floatW colcnd, floatW amax, intW info) {
      if (debug) {
         System.err.println("sgeequ");
      }

      this.sgeequ(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, info);
   }

   public void sgeequ(int m, int n, float[] a, int offseta, int lda, float[] r, int offsetr, float[] c, int offsetc, floatW rowcnd, floatW colcnd, floatW amax, intW info) {
      if (debug) {
         System.err.println("sgeequ");
      }

      this.sgeequK(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, info);
   }

   protected abstract void sgeequK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, floatW var10, floatW var11, floatW var12, intW var13);

   public void sgees(String jobvs, String sort, Object select, int n, float[] a, int lda, intW sdim, float[] wr, float[] wi, float[] vs, int ldvs, float[] work, int lwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("sgees");
      }

      this.sgees(jobvs, sort, select, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, work, 0, lwork, bwork, 0, info);
   }

   public void sgees(String jobvs, String sort, Object select, int n, float[] a, int offseta, int lda, intW sdim, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vs, int offsetvs, int ldvs, float[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("sgees");
      }

      this.sgeesK(jobvs, sort, select, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, work, offsetwork, lwork, bwork, offsetbwork, info);
   }

   protected abstract void sgeesK(String var1, String var2, Object var3, int var4, float[] var5, int var6, int var7, intW var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, boolean[] var19, int var20, intW var21);

   public void sgeesx(String jobvs, String sort, Object select, String sense, int n, float[] a, int lda, intW sdim, float[] wr, float[] wi, float[] vs, int ldvs, floatW rconde, floatW rcondv, float[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("sgeesx");
      }

      this.sgeesx(jobvs, sort, select, sense, n, a, 0, lda, sdim, wr, 0, wi, 0, vs, 0, ldvs, rconde, rcondv, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
   }

   public void sgeesx(String jobvs, String sort, Object select, String sense, int n, float[] a, int offseta, int lda, intW sdim, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vs, int offsetvs, int ldvs, floatW rconde, floatW rcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("sgeesx");
      }

      this.sgeesxK(jobvs, sort, select, sense, n, a, offseta, lda, sdim, wr, offsetwr, wi, offsetwi, vs, offsetvs, ldvs, rconde, rcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
   }

   protected abstract void sgeesxK(String var1, String var2, Object var3, String var4, int var5, float[] var6, int var7, int var8, intW var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, floatW var17, floatW var18, float[] var19, int var20, int var21, int[] var22, int var23, int var24, boolean[] var25, int var26, intW var27);

   public void sgeev(String jobvl, String jobvr, int n, float[] a, int lda, float[] wr, float[] wi, float[] vl, int ldvl, float[] vr, int ldvr, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeev");
      }

      this.sgeev(jobvl, jobvr, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
   }

   public void sgeev(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeev");
      }

      this.checkArgument("SGEEV", 1, this.lsame("N", jobvl) || this.lsame("V", jobvl));
      this.checkArgument("SGEEV", 2, this.lsame("N", jobvr) || this.lsame("V", jobvr));
      this.checkArgument("SGEEV", 3, n >= 0);
      this.checkArgument("SGEEV", 5, lda >= Math.max(1, n));
      this.checkArgument("SGEEV", 9, ldvl >= Math.max(1, this.lsame("V", jobvl) ? n : 1));
      this.checkArgument("SGEEV", 11, ldvr >= Math.max(1, this.lsame("V", jobvr) ? n : 1));
      this.checkArgument("SGEEV", 11, lwork == -1 || lwork >= Math.max(1, !this.lsame("V", jobvl) && !this.lsame("V", jobvr) ? 3 * n : 4 * n));
      this.requireNonNull(a);
      this.requireNonNull(wr);
      this.requireNonNull(wi);
      if (this.lsame("V", jobvl)) {
         this.requireNonNull(vl);
      }

      if (this.lsame("V", jobvr)) {
         this.requireNonNull(vr);
      }

      this.requireNonNull(work);
      this.requireNonNull(info);
      if (lwork != -1) {
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsetwr + n - 1, wr.length);
         this.checkIndex(offsetwi + n - 1, wi.length);
         if (this.lsame("V", jobvl)) {
            this.checkIndex(offsetvl + n * ldvl - 1, vl.length);
         }

         if (this.lsame("V", jobvr)) {
            this.checkIndex(offsetvr + n * ldvr - 1, vr.length);
         }
      }

      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.sgeevK(jobvl, jobvr, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
   }

   protected abstract void sgeevK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, intW var20);

   public void sgeevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int lda, float[] wr, float[] wi, float[] vl, int ldvl, float[] vr, int ldvr, intW ilo, intW ihi, float[] scale, floatW abnrm, float[] rconde, float[] rcondv, float[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgeevx");
      }

      this.sgeevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, scale, 0, abnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, info);
   }

   public void sgeevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int offseta, int lda, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, intW ilo, intW ihi, float[] scale, int offsetscale, floatW abnrm, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgeevx");
      }

      this.sgeevxK(balanc, jobvl, jobvr, sense, n, a, offseta, lda, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, scale, offsetscale, abnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void sgeevxK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19, intW var20, float[] var21, int var22, floatW var23, float[] var24, int var25, float[] var26, int var27, float[] var28, int var29, int var30, int[] var31, int var32, intW var33);

   public void sgegs(String jobvsl, String jobvsr, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vsl, int ldvsl, float[] vsr, int ldvsr, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgegs");
      }

      this.sgegs(jobvsl, jobvsr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, info);
   }

   public void sgegs(String jobvsl, String jobvsr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgegs");
      }

      this.sgegsK(jobvsl, jobvsr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, info);
   }

   protected abstract void sgegsK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   public void sgegv(String jobvl, String jobvr, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vl, int ldvl, float[] vr, int ldvr, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgegv");
      }

      this.sgegv(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
   }

   public void sgegv(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgegv");
      }

      this.sgegvK(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
   }

   protected abstract void sgegvK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   public void sgehd2(int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sgehd2");
      }

      this.sgehd2(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sgehd2(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgehd2");
      }

      this.sgehd2K(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sgehd2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   public void sgehrd(int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgehrd");
      }

      this.sgehrd(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sgehrd(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgehrd");
      }

      this.sgehrdK(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sgehrdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sgelq2(int m, int n, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sgelq2");
      }

      this.sgelq2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sgelq2(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgelq2");
      }

      this.sgelq2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sgelq2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   public void sgelqf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgelqf");
      }

      this.sgelqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sgelqf(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgelqf");
      }

      this.sgelqfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sgelqfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sgels(String trans, int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgels");
      }

      this.sgels(trans, m, n, nrhs, a, 0, lda, b, 0, ldb, work, 0, lwork, info);
   }

   public void sgels(String trans, int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgels");
      }

      this.checkArgument("SGELS", 1, this.lsame("N", trans) || this.lsame("T", trans));
      this.checkArgument("SGELS", 2, m >= 0);
      this.checkArgument("SGELS", 3, n >= 0);
      this.checkArgument("SGELS", 4, nrhs >= 0);
      this.checkArgument("SGELS", 6, lda >= Math.max(1, m));
      this.checkArgument("SGELS", 8, ldb >= Math.max(1, Math.max(m, n)));
      this.checkArgument("SGELS", 10, lwork == -1 || lwork >= Math.max(1, Math.min(m, n) + Math.max(Math.min(m, n), nrhs)));
      this.requireNonNull(a);
      this.requireNonNull(b);
      this.requireNonNull(work);
      this.requireNonNull(info);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetb + nrhs * (this.lsame("N", trans) ? m : n) - 1, b.length);
      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.sgelsK(trans, m, n, nrhs, a, offseta, lda, b, offsetb, ldb, work, offsetwork, lwork, info);
   }

   protected abstract void sgelsK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, intW var14);

   public void sgelsd(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] s, float rcond, intW rank, float[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgelsd");
      }

      this.sgelsd(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, iwork, 0, info);
   }

   public void sgelsd(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] s, int offsets, float rcond, intW rank, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgelsd");
      }

      this.sgelsdK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void sgelsdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, int[] var17, int var18, intW var19);

   public void sgelss(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] s, float rcond, intW rank, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgelss");
      }

      this.sgelss(m, n, nrhs, a, 0, lda, b, 0, ldb, s, 0, rcond, rank, work, 0, lwork, info);
   }

   public void sgelss(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] s, int offsets, float rcond, intW rank, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgelss");
      }

      this.sgelssK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, s, offsets, rcond, rank, work, offsetwork, lwork, info);
   }

   protected abstract void sgelssK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, intW var17);

   public void sgelsx(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, int[] jpvt, float rcond, intW rank, float[] work, intW info) {
      if (debug) {
         System.err.println("sgelsx");
      }

      this.sgelsx(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, info);
   }

   public void sgelsx(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, float rcond, intW rank, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgelsx");
      }

      this.sgelsxK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, info);
   }

   protected abstract void sgelsxK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float var12, intW var13, float[] var14, int var15, intW var16);

   public void sgelsy(int m, int n, int nrhs, float[] a, int lda, float[] b, int ldb, int[] jpvt, float rcond, intW rank, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgelsy");
      }

      this.sgelsy(m, n, nrhs, a, 0, lda, b, 0, ldb, jpvt, 0, rcond, rank, work, 0, lwork, info);
   }

   public void sgelsy(int m, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, int[] jpvt, int offsetjpvt, float rcond, intW rank, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgelsy");
      }

      this.sgelsyK(m, n, nrhs, a, offseta, lda, b, offsetb, ldb, jpvt, offsetjpvt, rcond, rank, work, offsetwork, lwork, info);
   }

   protected abstract void sgelsyK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, intW var17);

   public void sgeql2(int m, int n, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sgeql2");
      }

      this.sgeql2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sgeql2(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgeql2");
      }

      this.sgeql2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sgeql2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   public void sgeqlf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeqlf");
      }

      this.sgeqlf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sgeqlf(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeqlf");
      }

      this.sgeqlfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sgeqlfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sgeqp3(int m, int n, float[] a, int lda, int[] jpvt, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeqp3");
      }

      this.sgeqp3(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, lwork, info);
   }

   public void sgeqp3(int m, int n, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeqp3");
      }

      this.checkArgument("SGEQP3", 1, m >= 0);
      this.checkArgument("SGEQP3", 2, n >= 0);
      this.checkArgument("SGEQP3", 4, lda >= Math.max(1, m));
      this.checkArgument("SGEQP3", 8, lwork == -1 || lwork >= Math.max(1, 3 * n + 1));
      this.requireNonNull(a);
      this.requireNonNull(jpvt);
      this.requireNonNull(tau);
      this.requireNonNull(work);
      this.requireNonNull(info);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetjpvt + n - 1, jpvt.length);
      this.checkIndex(offsettau + Math.min(m, n) - 1, tau.length);
      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.sgeqp3K(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sgeqp3K(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   public void sgeqpf(int m, int n, float[] a, int lda, int[] jpvt, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sgeqpf");
      }

      this.sgeqpf(m, n, a, 0, lda, jpvt, 0, tau, 0, work, 0, info);
   }

   public void sgeqpf(int m, int n, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgeqpf");
      }

      this.sgeqpfK(m, n, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sgeqpfK(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   public void sgeqr2(int m, int n, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sgeqr2");
      }

      this.sgeqr2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sgeqr2(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgeqr2");
      }

      this.sgeqr2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sgeqr2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   public void sgeqrf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeqrf");
      }

      this.sgeqrf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sgeqrf(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgeqrf");
      }

      this.checkArgument("SGEQRF", 1, m >= 0);
      this.checkArgument("SGEQRF", 2, n >= 0);
      this.checkArgument("SGEQRF", 4, lda >= Math.max(1, m));
      this.checkArgument("SGEQRF", 7, lwork == -1 || lwork >= Math.max(1, n));
      this.requireNonNull(a);
      this.requireNonNull(tau);
      this.requireNonNull(work);
      this.requireNonNull(info);
      if (lwork != -1) {
         this.checkIndex(offseta + n * lda - 1, a.length);
         this.checkIndex(offsettau + Math.min(m, n) - 1, tau.length);
      }

      this.checkIndex(offsetwork + Math.max(1, lwork) - 1, work.length);
      this.sgeqrfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sgeqrfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sgerfs(String trans, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgerfs");
      }

      this.sgerfs(trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sgerfs(String trans, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgerfs");
      }

      this.sgerfsK(trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgerfsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   public void sgerq2(int m, int n, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sgerq2");
      }

      this.sgerq2(m, n, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sgerq2(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sgerq2");
      }

      this.sgerq2K(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sgerq2K(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   public void sgerqf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgerqf");
      }

      this.sgerqf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sgerqf(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgerqf");
      }

      this.sgerqfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sgerqfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sgesc2(int n, float[] a, int lda, float[] rhs, int[] ipiv, int[] jpiv, floatW scale) {
      if (debug) {
         System.err.println("sgesc2");
      }

      this.sgesc2(n, a, 0, lda, rhs, 0, ipiv, 0, jpiv, 0, scale);
   }

   public void sgesc2(int n, float[] a, int offseta, int lda, float[] rhs, int offsetrhs, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, floatW scale) {
      if (debug) {
         System.err.println("sgesc2");
      }

      this.sgesc2K(n, a, offseta, lda, rhs, offsetrhs, ipiv, offsetipiv, jpiv, offsetjpiv, scale);
   }

   protected abstract void sgesc2K(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int[] var7, int var8, int[] var9, int var10, floatW var11);

   public void sgesdd(String jobz, int m, int n, float[] a, int lda, float[] s, float[] u, int ldu, float[] vt, int ldvt, float[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgesdd");
      }

      this.sgesdd(jobz, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, iwork, 0, info);
   }

   public void sgesdd(String jobz, int m, int n, float[] a, int offseta, int lda, float[] s, int offsets, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgesdd");
      }

      this.sgesddK(jobz, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void sgesddK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   public void sgesv(int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sgesv");
      }

      this.sgesv(n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
   }

   public void sgesv(int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sgesv");
      }

      this.sgesvK(n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void sgesvK(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sgesvd(String jobu, String jobvt, int m, int n, float[] a, int lda, float[] s, float[] u, int ldu, float[] vt, int ldvt, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgesvd");
      }

      this.sgesvd(jobu, jobvt, m, n, a, 0, lda, s, 0, u, 0, ldu, vt, 0, ldvt, work, 0, lwork, info);
   }

   public void sgesvd(String jobu, String jobvt, int m, int n, float[] a, int offseta, int lda, float[] s, int offsets, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgesvd");
      }

      this.sgesvdK(jobu, jobvt, m, n, a, offseta, lda, s, offsets, u, offsetu, ldu, vt, offsetvt, ldvt, work, offsetwork, lwork, info);
   }

   protected abstract void sgesvdK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19);

   public void sgesvx(String fact, String trans, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, StringW equed, float[] r, float[] c, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgesvx");
      }

      this.sgesvx(fact, trans, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, equed, r, 0, c, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sgesvx(String fact, String trans, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, StringW equed, float[] r, int offsetr, float[] c, int offsetc, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgesvx");
      }

      this.sgesvxK(fact, trans, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, equed, r, offsetr, c, offsetc, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgesvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, int[] var11, int var12, StringW var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, floatW var24, float[] var25, int var26, float[] var27, int var28, float[] var29, int var30, int[] var31, int var32, intW var33);

   public void sgetc2(int n, float[] a, int lda, int[] ipiv, int[] jpiv, intW info) {
      if (debug) {
         System.err.println("sgetc2");
      }

      this.sgetc2(n, a, 0, lda, ipiv, 0, jpiv, 0, info);
   }

   public void sgetc2(int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv, intW info) {
      if (debug) {
         System.err.println("sgetc2");
      }

      this.sgetc2K(n, a, offseta, lda, ipiv, offsetipiv, jpiv, offsetjpiv, info);
   }

   protected abstract void sgetc2K(int var1, float[] var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, intW var9);

   public void sgetf2(int m, int n, float[] a, int lda, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("sgetf2");
      }

      this.sgetf2(m, n, a, 0, lda, ipiv, 0, info);
   }

   public void sgetf2(int m, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("sgetf2");
      }

      this.sgetf2K(m, n, a, offseta, lda, ipiv, offsetipiv, info);
   }

   protected abstract void sgetf2K(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   public void sgetrf(int m, int n, float[] a, int lda, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("sgetrf");
      }

      this.sgetrf(m, n, a, 0, lda, ipiv, 0, info);
   }

   public void sgetrf(int m, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("sgetrf");
      }

      this.checkArgument("SGETRF", 1, m >= 0);
      this.checkArgument("SGETRF", 2, n >= 0);
      this.checkArgument("SGETRF", 4, lda >= Math.max(1, m));
      this.requireNonNull(a);
      this.requireNonNull(ipiv);
      this.requireNonNull(info);
      this.checkIndex(offseta + n * lda - 1, a.length);
      this.checkIndex(offsetipiv + Math.min(m, n) - 1, ipiv.length);
      this.sgetrfK(m, n, a, offseta, lda, ipiv, offsetipiv, info);
   }

   protected abstract void sgetrfK(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   public void sgetri(int n, float[] a, int lda, int[] ipiv, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgetri");
      }

      this.sgetri(n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
   }

   public void sgetri(int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgetri");
      }

      this.sgetriK(n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
   }

   protected abstract void sgetriK(int var1, float[] var2, int var3, int var4, int[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void sgetrs(String trans, int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sgetrs");
      }

      this.sgetrs(trans, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
   }

   public void sgetrs(String trans, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sgetrs");
      }

      this.sgetrsK(trans, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void sgetrsK(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sggbak(String job, String side, int n, int ilo, int ihi, float[] lscale, float[] rscale, int m, float[] v, int ldv, intW info) {
      if (debug) {
         System.err.println("sggbak");
      }

      this.sggbak(job, side, n, ilo, ihi, lscale, 0, rscale, 0, m, v, 0, ldv, info);
   }

   public void sggbak(String job, String side, int n, int ilo, int ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, int m, float[] v, int offsetv, int ldv, intW info) {
      if (debug) {
         System.err.println("sggbak");
      }

      this.sggbakK(job, side, n, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, m, v, offsetv, ldv, info);
   }

   protected abstract void sggbakK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, intW var14);

   public void sggbal(String job, int n, float[] a, int lda, float[] b, int ldb, intW ilo, intW ihi, float[] lscale, float[] rscale, float[] work, intW info) {
      if (debug) {
         System.err.println("sggbal");
      }

      this.sggbal(job, n, a, 0, lda, b, 0, ldb, ilo, ihi, lscale, 0, rscale, 0, work, 0, info);
   }

   public void sggbal(String job, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW ilo, intW ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sggbal");
      }

      this.sggbalK(job, n, a, offseta, lda, b, offsetb, ldb, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, work, offsetwork, info);
   }

   protected abstract void sggbalK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9, intW var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, intW var17);

   public void sgges(String jobvsl, String jobvsr, String sort, Object selctg, int n, float[] a, int lda, float[] b, int ldb, intW sdim, float[] alphar, float[] alphai, float[] beta, float[] vsl, int ldvsl, float[] vsr, int ldvsr, float[] work, int lwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("sgges");
      }

      this.sgges(jobvsl, jobvsr, sort, selctg, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, work, 0, lwork, bwork, 0, info);
   }

   public void sgges(String jobvsl, String jobvsr, String sort, Object selctg, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW sdim, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] work, int offsetwork, int lwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("sgges");
      }

      this.sggesK(jobvsl, jobvsr, sort, selctg, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, work, offsetwork, lwork, bwork, offsetbwork, info);
   }

   protected abstract void sggesK(String var1, String var2, String var3, Object var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, boolean[] var28, int var29, intW var30);

   public void sggesx(String jobvsl, String jobvsr, String sort, Object selctg, String sense, int n, float[] a, int lda, float[] b, int ldb, intW sdim, float[] alphar, float[] alphai, float[] beta, float[] vsl, int ldvsl, float[] vsr, int ldvsr, float[] rconde, float[] rcondv, float[] work, int lwork, int[] iwork, int liwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("sggesx");
      }

      this.sggesx(jobvsl, jobvsr, sort, selctg, sense, n, a, 0, lda, b, 0, ldb, sdim, alphar, 0, alphai, 0, beta, 0, vsl, 0, ldvsl, vsr, 0, ldvsr, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, liwork, bwork, 0, info);
   }

   public void sggesx(String jobvsl, String jobvsr, String sort, Object selctg, String sense, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW sdim, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vsl, int offsetvsl, int ldvsl, float[] vsr, int offsetvsr, int ldvsr, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("sggesx");
      }

      this.sggesxK(jobvsl, jobvsr, sort, selctg, sense, n, a, offseta, lda, b, offsetb, ldb, sdim, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vsl, offsetvsl, ldvsl, vsr, offsetvsr, ldvsr, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, liwork, bwork, offsetbwork, info);
   }

   protected abstract void sggesxK(String var1, String var2, String var3, Object var4, String var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, intW var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int var32, int[] var33, int var34, int var35, boolean[] var36, int var37, intW var38);

   public void sggev(String jobvl, String jobvr, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vl, int ldvl, float[] vr, int ldvr, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sggev");
      }

      this.sggev(jobvl, jobvr, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, info);
   }

   public void sggev(String jobvl, String jobvr, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sggev");
      }

      this.sggevK(jobvl, jobvr, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, work, offsetwork, lwork, info);
   }

   protected abstract void sggevK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   public void sggevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] vl, int ldvl, float[] vr, int ldvr, intW ilo, intW ihi, float[] lscale, float[] rscale, floatW abnrm, floatW bbnrm, float[] rconde, float[] rcondv, float[] work, int lwork, int[] iwork, boolean[] bwork, intW info) {
      if (debug) {
         System.err.println("sggevx");
      }

      this.sggevx(balanc, jobvl, jobvr, sense, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, vl, 0, ldvl, vr, 0, ldvr, ilo, ihi, lscale, 0, rscale, 0, abnrm, bbnrm, rconde, 0, rcondv, 0, work, 0, lwork, iwork, 0, bwork, 0, info);
   }

   public void sggevx(String balanc, String jobvl, String jobvr, String sense, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, intW ilo, intW ihi, float[] lscale, int offsetlscale, float[] rscale, int offsetrscale, floatW abnrm, floatW bbnrm, float[] rconde, int offsetrconde, float[] rcondv, int offsetrcondv, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, boolean[] bwork, int offsetbwork, intW info) {
      if (debug) {
         System.err.println("sggevx");
      }

      this.sggevxK(balanc, jobvl, jobvr, sense, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, vl, offsetvl, ldvl, vr, offsetvr, ldvr, ilo, ihi, lscale, offsetlscale, rscale, offsetrscale, abnrm, bbnrm, rconde, offsetrconde, rcondv, offsetrcondv, work, offsetwork, lwork, iwork, offsetiwork, bwork, offsetbwork, info);
   }

   protected abstract void sggevxK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, intW var24, intW var25, float[] var26, int var27, float[] var28, int var29, floatW var30, floatW var31, float[] var32, int var33, float[] var34, int var35, float[] var36, int var37, int var38, int[] var39, int var40, boolean[] var41, int var42, intW var43);

   public void sggglm(int n, int m, int p, float[] a, int lda, float[] b, int ldb, float[] d, float[] x, float[] y, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sggglm");
      }

      this.sggglm(n, m, p, a, 0, lda, b, 0, ldb, d, 0, x, 0, y, 0, work, 0, lwork, info);
   }

   public void sggglm(int n, int m, int p, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] d, int offsetd, float[] x, int offsetx, float[] y, int offsety, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sggglm");
      }

      this.sggglmK(n, m, p, a, offseta, lda, b, offsetb, ldb, d, offsetd, x, offsetx, y, offsety, work, offsetwork, lwork, info);
   }

   protected abstract void sggglmK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, intW var19);

   public void sgghrd(String compq, String compz, int n, int ilo, int ihi, float[] a, int lda, float[] b, int ldb, float[] q, int ldq, float[] z, int ldz, intW info) {
      if (debug) {
         System.err.println("sgghrd");
      }

      this.sgghrd(compq, compz, n, ilo, ihi, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, info);
   }

   public void sgghrd(String compq, String compz, int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, intW info) {
      if (debug) {
         System.err.println("sgghrd");
      }

      this.sgghrdK(compq, compz, n, ilo, ihi, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, info);
   }

   protected abstract void sgghrdK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   public void sgglse(int m, int n, int p, float[] a, int lda, float[] b, int ldb, float[] c, float[] d, float[] x, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sgglse");
      }

      this.sgglse(m, n, p, a, 0, lda, b, 0, ldb, c, 0, d, 0, x, 0, work, 0, lwork, info);
   }

   public void sgglse(int m, int n, int p, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, float[] d, int offsetd, float[] x, int offsetx, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sgglse");
      }

      this.sgglseK(m, n, p, a, offseta, lda, b, offsetb, ldb, c, offsetc, d, offsetd, x, offsetx, work, offsetwork, lwork, info);
   }

   protected abstract void sgglseK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, intW var19);

   public void sggqrf(int n, int m, int p, float[] a, int lda, float[] taua, float[] b, int ldb, float[] taub, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sggqrf");
      }

      this.sggqrf(n, m, p, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
   }

   public void sggqrf(int n, int m, int p, float[] a, int offseta, int lda, float[] taua, int offsettaua, float[] b, int offsetb, int ldb, float[] taub, int offsettaub, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sggqrf");
      }

      this.sggqrfK(n, m, p, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
   }

   protected abstract void sggqrfK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sggrqf(int m, int p, int n, float[] a, int lda, float[] taua, float[] b, int ldb, float[] taub, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sggrqf");
      }

      this.sggrqf(m, p, n, a, 0, lda, taua, 0, b, 0, ldb, taub, 0, work, 0, lwork, info);
   }

   public void sggrqf(int m, int p, int n, float[] a, int offseta, int lda, float[] taua, int offsettaua, float[] b, int offsetb, int ldb, float[] taub, int offsettaub, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sggrqf");
      }

      this.sggrqfK(m, p, n, a, offseta, lda, taua, offsettaua, b, offsetb, ldb, taub, offsettaub, work, offsetwork, lwork, info);
   }

   protected abstract void sggrqfK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sggsvd(String jobu, String jobv, String jobq, int m, int n, int p, intW k, intW l, float[] a, int lda, float[] b, int ldb, float[] alpha, float[] beta, float[] u, int ldu, float[] v, int ldv, float[] q, int ldq, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sggsvd");
      }

      this.sggsvd(jobu, jobv, jobq, m, n, p, k, l, a, 0, lda, b, 0, ldb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, iwork, 0, info);
   }

   public void sggsvd(String jobu, String jobv, String jobq, int m, int n, int p, intW k, intW l, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alpha, int offsetalpha, float[] beta, int offsetbeta, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sggsvd");
      }

      this.sggsvdK(jobu, jobv, jobq, m, n, p, k, l, a, offseta, lda, b, offsetb, ldb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sggsvdK(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, float[] var28, int var29, int[] var30, int var31, intW var32);

   public void sggsvp(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int lda, float[] b, int ldb, float tola, float tolb, intW k, intW l, float[] u, int ldu, float[] v, int ldv, float[] q, int ldq, int[] iwork, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sggsvp");
      }

      this.sggsvp(jobu, jobv, jobq, m, p, n, a, 0, lda, b, 0, ldb, tola, tolb, k, l, u, 0, ldu, v, 0, ldv, q, 0, ldq, iwork, 0, tau, 0, work, 0, info);
   }

   public void sggsvp(String jobu, String jobv, String jobq, int m, int p, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float tola, float tolb, intW k, intW l, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, int[] iwork, int offsetiwork, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sggsvp");
      }

      this.sggsvpK(jobu, jobv, jobq, m, p, n, a, offseta, lda, b, offsetb, ldb, tola, tolb, k, l, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, iwork, offsetiwork, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sggsvpK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float var14, intW var15, intW var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int[] var26, int var27, float[] var28, int var29, float[] var30, int var31, intW var32);

   public void sgtcon(String norm, int n, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgtcon");
      }

      this.sgtcon(norm, n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void sgtcon(String norm, int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgtcon");
      }

      this.sgtconK(norm, n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgtconK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int var12, float var13, floatW var14, float[] var15, int var16, int[] var17, int var18, intW var19);

   public void sgtrfs(String trans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] dlf, float[] df, float[] duf, float[] du2, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgtrfs");
      }

      this.sgtrfs(trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sgtrfs(String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] dlf, int offsetdlf, float[] df, int offsetdf, float[] duf, int offsetduf, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgtrfs");
      }

      this.sgtrfsK(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgtrfsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int[] var32, int var33, intW var34);

   public void sgtsv(int n, int nrhs, float[] dl, float[] d, float[] du, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sgtsv");
      }

      this.sgtsv(n, nrhs, dl, 0, d, 0, du, 0, b, 0, ldb, info);
   }

   public void sgtsv(int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sgtsv");
      }

      this.sgtsvK(n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, b, offsetb, ldb, info);
   }

   protected abstract void sgtsvK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sgtsvx(String fact, String trans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] dlf, float[] df, float[] duf, float[] du2, int[] ipiv, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sgtsvx");
      }

      this.sgtsvx(fact, trans, n, nrhs, dl, 0, d, 0, du, 0, dlf, 0, df, 0, duf, 0, du2, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sgtsvx(String fact, String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] dlf, int offsetdlf, float[] df, int offsetdf, float[] duf, int offsetduf, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sgtsvx");
      }

      this.sgtsvxK(fact, trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, dlf, offsetdlf, df, offsetdf, duf, offsetduf, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sgtsvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, floatW var27, float[] var28, int var29, float[] var30, int var31, float[] var32, int var33, int[] var34, int var35, intW var36);

   public void sgttrf(int n, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("sgttrf");
      }

      this.sgttrf(n, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, info);
   }

   public void sgttrf(int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("sgttrf");
      }

      this.sgttrfK(n, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, info);
   }

   protected abstract void sgttrfK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   public void sgttrs(String trans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sgttrs");
      }

      this.sgttrs(trans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb, info);
   }

   public void sgttrs(String trans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sgttrs");
      }

      this.sgttrsK(trans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void sgttrsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sgtts2(int itrans, int n, int nrhs, float[] dl, float[] d, float[] du, float[] du2, int[] ipiv, float[] b, int ldb) {
      if (debug) {
         System.err.println("sgtts2");
      }

      this.sgtts2(itrans, n, nrhs, dl, 0, d, 0, du, 0, du2, 0, ipiv, 0, b, 0, ldb);
   }

   public void sgtts2(int itrans, int n, int nrhs, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] du2, int offsetdu2, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("sgtts2");
      }

      this.sgtts2K(itrans, n, nrhs, dl, offsetdl, d, offsetd, du, offsetdu, du2, offsetdu2, ipiv, offsetipiv, b, offsetb, ldb);
   }

   protected abstract void sgtts2K(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16);

   public void shgeqz(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int ldh, float[] t, int ldt, float[] alphar, float[] alphai, float[] beta, float[] q, int ldq, float[] z, int ldz, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("shgeqz");
      }

      this.shgeqz(job, compq, compz, n, ilo, ihi, h, 0, ldh, t, 0, ldt, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, work, 0, lwork, info);
   }

   public void shgeqz(String job, String compq, String compz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] t, int offsett, int ldt, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("shgeqz");
      }

      this.shgeqzK(job, compq, compz, n, ilo, ihi, h, offseth, ldh, t, offsett, ldt, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void shgeqzK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, intW var28);

   public void shsein(String side, String eigsrc, String initv, boolean[] select, int n, float[] h, int ldh, float[] wr, float[] wi, float[] vl, int ldvl, float[] vr, int ldvr, int mm, intW m, float[] work, int[] ifaill, int[] ifailr, intW info) {
      if (debug) {
         System.err.println("shsein");
      }

      this.shsein(side, eigsrc, initv, select, 0, n, h, 0, ldh, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, ifaill, 0, ifailr, 0, info);
   }

   public void shsein(String side, String eigsrc, String initv, boolean[] select, int offsetselect, int n, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, intW m, float[] work, int offsetwork, int[] ifaill, int offsetifaill, int[] ifailr, int offsetifailr, intW info) {
      if (debug) {
         System.err.println("shsein");
      }

      this.shseinK(side, eigsrc, initv, select, offsetselect, n, h, offseth, ldh, wr, offsetwr, wi, offsetwi, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, ifaill, offsetifaill, ifailr, offsetifailr, info);
   }

   protected abstract void shseinK(String var1, String var2, String var3, boolean[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, int var20, intW var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   public void shseqr(String job, String compz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, float[] z, int ldz, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("shseqr");
      }

      this.shseqr(job, compz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, z, 0, ldz, work, 0, lwork, info);
   }

   public void shseqr(String job, String compz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("shseqr");
      }

      this.shseqrK(job, compz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void shseqrK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19);

   public boolean sisnan(float sin) {
      if (debug) {
         System.err.println("sisnan");
      }

      return this.sisnanK(sin);
   }

   protected abstract boolean sisnanK(float var1);

   public void slabad(floatW small, floatW large) {
      if (debug) {
         System.err.println("slabad");
      }

      this.slabadK(small, large);
   }

   protected abstract void slabadK(floatW var1, floatW var2);

   public void slabrd(int m, int n, int nb, float[] a, int lda, float[] d, float[] e, float[] tauq, float[] taup, float[] x, int ldx, float[] y, int ldy) {
      if (debug) {
         System.err.println("slabrd");
      }

      this.slabrd(m, n, nb, a, 0, lda, d, 0, e, 0, tauq, 0, taup, 0, x, 0, ldx, y, 0, ldy);
   }

   public void slabrd(int m, int n, int nb, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tauq, int offsettauq, float[] taup, int offsettaup, float[] x, int offsetx, int ldx, float[] y, int offsety, int ldy) {
      if (debug) {
         System.err.println("slabrd");
      }

      this.slabrdK(m, n, nb, a, offseta, lda, d, offsetd, e, offsete, tauq, offsettauq, taup, offsettaup, x, offsetx, ldx, y, offsety, ldy);
   }

   protected abstract void slabrdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20);

   public void slacn2(int n, float[] v, float[] x, int[] isgn, floatW est, intW kase, int[] isave) {
      if (debug) {
         System.err.println("slacn2");
      }

      this.slacn2(n, v, 0, x, 0, isgn, 0, est, kase, isave, 0);
   }

   public void slacn2(int n, float[] v, int offsetv, float[] x, int offsetx, int[] isgn, int offsetisgn, floatW est, intW kase, int[] isave, int offsetisave) {
      if (debug) {
         System.err.println("slacn2");
      }

      this.slacn2K(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase, isave, offsetisave);
   }

   protected abstract void slacn2K(int var1, float[] var2, int var3, float[] var4, int var5, int[] var6, int var7, floatW var8, intW var9, int[] var10, int var11);

   public void slacon(int n, float[] v, float[] x, int[] isgn, floatW est, intW kase) {
      if (debug) {
         System.err.println("slacon");
      }

      this.slacon(n, v, 0, x, 0, isgn, 0, est, kase);
   }

   public void slacon(int n, float[] v, int offsetv, float[] x, int offsetx, int[] isgn, int offsetisgn, floatW est, intW kase) {
      if (debug) {
         System.err.println("slacon");
      }

      this.slaconK(n, v, offsetv, x, offsetx, isgn, offsetisgn, est, kase);
   }

   protected abstract void slaconK(int var1, float[] var2, int var3, float[] var4, int var5, int[] var6, int var7, floatW var8, intW var9);

   public void slacpy(String uplo, int m, int n, float[] a, int lda, float[] b, int ldb) {
      if (debug) {
         System.err.println("slacpy");
      }

      this.slacpy(uplo, m, n, a, 0, lda, b, 0, ldb);
   }

   public void slacpy(String uplo, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("slacpy");
      }

      this.slacpyK(uplo, m, n, a, offseta, lda, b, offsetb, ldb);
   }

   protected abstract void slacpyK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   public void sladiv(float a, float b, float c, float d, floatW p, floatW q) {
      if (debug) {
         System.err.println("sladiv");
      }

      this.sladivK(a, b, c, d, p, q);
   }

   protected abstract void sladivK(float var1, float var2, float var3, float var4, floatW var5, floatW var6);

   public void slae2(float a, float b, float c, floatW rt1, floatW rt2) {
      if (debug) {
         System.err.println("slae2");
      }

      this.slae2K(a, b, c, rt1, rt2);
   }

   protected abstract void slae2K(float var1, float var2, float var3, floatW var4, floatW var5);

   public void slaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, float[] e, float[] e2, int[] nval, float[] ab, float[] c, intW mout, int[] nab, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slaebz");
      }

      this.slaebz(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, 0, e, 0, e2, 0, nval, 0, ab, 0, c, 0, mout, nab, 0, work, 0, iwork, 0, info);
   }

   public void slaebz(int ijob, int nitmax, int n, int mmax, int minp, int nbmin, float abstol, float reltol, float pivmin, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, int[] nval, int offsetnval, float[] ab, int offsetab, float[] c, int offsetc, intW mout, int[] nab, int offsetnab, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slaebz");
      }

      this.slaebzK(ijob, nitmax, n, mmax, minp, nbmin, abstol, reltol, pivmin, d, offsetd, e, offsete, e2, offsete2, nval, offsetnval, ab, offsetab, c, offsetc, mout, nab, offsetnab, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slaebzK(int var1, int var2, int var3, int var4, int var5, int var6, float var7, float var8, float var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, intW var22, int[] var23, int var24, float[] var25, int var26, int[] var27, int var28, intW var29);

   public void slaed0(int icompq, int qsiz, int n, float[] d, float[] e, float[] q, int ldq, float[] qstore, int ldqs, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slaed0");
      }

      this.slaed0(icompq, qsiz, n, d, 0, e, 0, q, 0, ldq, qstore, 0, ldqs, work, 0, iwork, 0, info);
   }

   public void slaed0(int icompq, int qsiz, int n, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] qstore, int offsetqstore, int ldqs, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slaed0");
      }

      this.slaed0K(icompq, qsiz, n, d, offsetd, e, offsete, q, offsetq, ldq, qstore, offsetqstore, ldqs, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slaed0K(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   public void slaed1(int n, float[] d, float[] q, int ldq, int[] indxq, floatW rho, int cutpnt, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slaed1");
      }

      this.slaed1(n, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, work, 0, iwork, 0, info);
   }

   public void slaed1(int n, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, floatW rho, int cutpnt, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slaed1");
      }

      this.slaed1K(n, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slaed1K(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, floatW var9, int var10, float[] var11, int var12, int[] var13, int var14, intW var15);

   public void slaed2(intW k, int n, int n1, float[] d, float[] q, int ldq, int[] indxq, floatW rho, float[] z, float[] dlamda, float[] w, float[] q2, int[] indx, int[] indxc, int[] indxp, int[] coltyp, intW info) {
      if (debug) {
         System.err.println("slaed2");
      }

      this.slaed2(k, n, n1, d, 0, q, 0, ldq, indxq, 0, rho, z, 0, dlamda, 0, w, 0, q2, 0, indx, 0, indxc, 0, indxp, 0, coltyp, 0, info);
   }

   public void slaed2(intW k, int n, int n1, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, floatW rho, float[] z, int offsetz, float[] dlamda, int offsetdlamda, float[] w, int offsetw, float[] q2, int offsetq2, int[] indx, int offsetindx, int[] indxc, int offsetindxc, int[] indxp, int offsetindxp, int[] coltyp, int offsetcoltyp, intW info) {
      if (debug) {
         System.err.println("slaed2");
      }

      this.slaed2K(k, n, n1, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, z, offsetz, dlamda, offsetdlamda, w, offsetw, q2, offsetq2, indx, offsetindx, indxc, offsetindxc, indxp, offsetindxp, coltyp, offsetcoltyp, info);
   }

   protected abstract void slaed2K(intW var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, int[] var9, int var10, floatW var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   public void slaed3(int k, int n, int n1, float[] d, float[] q, int ldq, float rho, float[] dlamda, float[] q2, int[] indx, int[] ctot, float[] w, float[] s, intW info) {
      if (debug) {
         System.err.println("slaed3");
      }

      this.slaed3(k, n, n1, d, 0, q, 0, ldq, rho, dlamda, 0, q2, 0, indx, 0, ctot, 0, w, 0, s, 0, info);
   }

   public void slaed3(int k, int n, int n1, float[] d, int offsetd, float[] q, int offsetq, int ldq, float rho, float[] dlamda, int offsetdlamda, float[] q2, int offsetq2, int[] indx, int offsetindx, int[] ctot, int offsetctot, float[] w, int offsetw, float[] s, int offsets, intW info) {
      if (debug) {
         System.err.println("slaed3");
      }

      this.slaed3K(k, n, n1, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, q2, offsetq2, indx, offsetindx, ctot, offsetctot, w, offsetw, s, offsets, info);
   }

   protected abstract void slaed3K(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, float[] var12, int var13, int[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, intW var22);

   public void slaed4(int n, int i, float[] d, float[] z, float[] delta, float rho, floatW dlam, intW info) {
      if (debug) {
         System.err.println("slaed4");
      }

      this.slaed4(n, i, d, 0, z, 0, delta, 0, rho, dlam, info);
   }

   public void slaed4(int n, int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, floatW dlam, intW info) {
      if (debug) {
         System.err.println("slaed4");
      }

      this.slaed4K(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam, info);
   }

   protected abstract void slaed4K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float var9, floatW var10, intW var11);

   public void slaed5(int i, float[] d, float[] z, float[] delta, float rho, floatW dlam) {
      if (debug) {
         System.err.println("slaed5");
      }

      this.slaed5(i, d, 0, z, 0, delta, 0, rho, dlam);
   }

   public void slaed5(int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, floatW dlam) {
      if (debug) {
         System.err.println("slaed5");
      }

      this.slaed5K(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dlam);
   }

   protected abstract void slaed5K(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9);

   public void slaed6(int kniter, boolean orgati, float rho, float[] d, float[] z, float finit, floatW tau, intW info) {
      if (debug) {
         System.err.println("slaed6");
      }

      this.slaed6(kniter, orgati, rho, d, 0, z, 0, finit, tau, info);
   }

   public void slaed6(int kniter, boolean orgati, float rho, float[] d, int offsetd, float[] z, int offsetz, float finit, floatW tau, intW info) {
      if (debug) {
         System.err.println("slaed6");
      }

      this.slaed6K(kniter, orgati, rho, d, offsetd, z, offsetz, finit, tau, info);
   }

   protected abstract void slaed6K(int var1, boolean var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9, intW var10);

   public void slaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, float[] q, int ldq, int[] indxq, floatW rho, int cutpnt, float[] qstore, int[] qptr, int[] prmptr, int[] perm, int[] givptr, int[] givcol, float[] givnum, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slaed7");
      }

      this.slaed7(icompq, n, qsiz, tlvls, curlvl, curpbm, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, qstore, 0, qptr, 0, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, work, 0, iwork, 0, info);
   }

   public void slaed7(int icompq, int n, int qsiz, int tlvls, int curlvl, int curpbm, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, floatW rho, int cutpnt, float[] qstore, int offsetqstore, int[] qptr, int offsetqptr, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slaed7");
      }

      this.slaed7K(icompq, n, qsiz, tlvls, curlvl, curpbm, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, qstore, offsetqstore, qptr, offsetqptr, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slaed7K(int var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, floatW var14, int var15, float[] var16, int var17, int[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int[] var32, int var33, intW var34);

   public void slaed8(int icompq, intW k, int n, int qsiz, float[] d, float[] q, int ldq, int[] indxq, floatW rho, int cutpnt, float[] z, float[] dlamda, float[] q2, int ldq2, float[] w, int[] perm, intW givptr, int[] givcol, float[] givnum, int[] indxp, int[] indx, intW info) {
      if (debug) {
         System.err.println("slaed8");
      }

      this.slaed8(icompq, k, n, qsiz, d, 0, q, 0, ldq, indxq, 0, rho, cutpnt, z, 0, dlamda, 0, q2, 0, ldq2, w, 0, perm, 0, givptr, givcol, 0, givnum, 0, indxp, 0, indx, 0, info);
   }

   public void slaed8(int icompq, intW k, int n, int qsiz, float[] d, int offsetd, float[] q, int offsetq, int ldq, int[] indxq, int offsetindxq, floatW rho, int cutpnt, float[] z, int offsetz, float[] dlamda, int offsetdlamda, float[] q2, int offsetq2, int ldq2, float[] w, int offsetw, int[] perm, int offsetperm, intW givptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, int[] indxp, int offsetindxp, int[] indx, int offsetindx, intW info) {
      if (debug) {
         System.err.println("slaed8");
      }

      this.slaed8K(icompq, k, n, qsiz, d, offsetd, q, offsetq, ldq, indxq, offsetindxq, rho, cutpnt, z, offsetz, dlamda, offsetdlamda, q2, offsetq2, ldq2, w, offsetw, perm, offsetperm, givptr, givcol, offsetgivcol, givnum, offsetgivnum, indxp, offsetindxp, indx, offsetindx, info);
   }

   protected abstract void slaed8K(int var1, intW var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, floatW var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25, int[] var26, int var27, float[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34);

   public void slaed9(int k, int kstart, int kstop, int n, float[] d, float[] q, int ldq, float rho, float[] dlamda, float[] w, float[] s, int lds, intW info) {
      if (debug) {
         System.err.println("slaed9");
      }

      this.slaed9(k, kstart, kstop, n, d, 0, q, 0, ldq, rho, dlamda, 0, w, 0, s, 0, lds, info);
   }

   public void slaed9(int k, int kstart, int kstop, int n, float[] d, int offsetd, float[] q, int offsetq, int ldq, float rho, float[] dlamda, int offsetdlamda, float[] w, int offsetw, float[] s, int offsets, int lds, intW info) {
      if (debug) {
         System.err.println("slaed9");
      }

      this.slaed9K(k, kstart, kstop, n, d, offsetd, q, offsetq, ldq, rho, dlamda, offsetdlamda, w, offsetw, s, offsets, lds, info);
   }

   protected abstract void slaed9K(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int var17, intW var18);

   public void slaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int[] perm, int[] givptr, int[] givcol, float[] givnum, float[] q, int[] qptr, float[] z, float[] ztemp, intW info) {
      if (debug) {
         System.err.println("slaeda");
      }

      this.slaeda(n, tlvls, curlvl, curpbm, prmptr, 0, perm, 0, givptr, 0, givcol, 0, givnum, 0, q, 0, qptr, 0, z, 0, ztemp, 0, info);
   }

   public void slaeda(int n, int tlvls, int curlvl, int curpbm, int[] prmptr, int offsetprmptr, int[] perm, int offsetperm, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, float[] givnum, int offsetgivnum, float[] q, int offsetq, int[] qptr, int offsetqptr, float[] z, int offsetz, float[] ztemp, int offsetztemp, intW info) {
      if (debug) {
         System.err.println("slaeda");
      }

      this.slaedaK(n, tlvls, curlvl, curpbm, prmptr, offsetprmptr, perm, offsetperm, givptr, offsetgivptr, givcol, offsetgivcol, givnum, offsetgivnum, q, offsetq, qptr, offsetqptr, z, offsetz, ztemp, offsetztemp, info);
   }

   protected abstract void slaedaK(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, int[] var9, int var10, int[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int[] var17, int var18, float[] var19, int var20, float[] var21, int var22, intW var23);

   public void slaein(boolean rightv, boolean noinit, int n, float[] h, int ldh, float wr, float wi, float[] vr, float[] vi, float[] b, int ldb, float[] work, float eps3, float smlnum, float bignum, intW info) {
      if (debug) {
         System.err.println("slaein");
      }

      this.slaein(rightv, noinit, n, h, 0, ldh, wr, wi, vr, 0, vi, 0, b, 0, ldb, work, 0, eps3, smlnum, bignum, info);
   }

   public void slaein(boolean rightv, boolean noinit, int n, float[] h, int offseth, int ldh, float wr, float wi, float[] vr, int offsetvr, float[] vi, int offsetvi, float[] b, int offsetb, int ldb, float[] work, int offsetwork, float eps3, float smlnum, float bignum, intW info) {
      if (debug) {
         System.err.println("slaein");
      }

      this.slaeinK(rightv, noinit, n, h, offseth, ldh, wr, wi, vr, offsetvr, vi, offsetvi, b, offsetb, ldb, work, offsetwork, eps3, smlnum, bignum, info);
   }

   protected abstract void slaeinK(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float var7, float var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float var18, float var19, float var20, intW var21);

   public void slaev2(float a, float b, float c, floatW rt1, floatW rt2, floatW cs1, floatW sn1) {
      if (debug) {
         System.err.println("slaev2");
      }

      this.slaev2K(a, b, c, rt1, rt2, cs1, sn1);
   }

   protected abstract void slaev2K(float var1, float var2, float var3, floatW var4, floatW var5, floatW var6, floatW var7);

   public void slaexc(boolean wantq, int n, float[] t, int ldt, float[] q, int ldq, int j1, int n1, int n2, float[] work, intW info) {
      if (debug) {
         System.err.println("slaexc");
      }

      this.slaexc(wantq, n, t, 0, ldt, q, 0, ldq, j1, n1, n2, work, 0, info);
   }

   public void slaexc(boolean wantq, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, int j1, int n1, int n2, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slaexc");
      }

      this.slaexcK(wantq, n, t, offsett, ldt, q, offsetq, ldq, j1, n1, n2, work, offsetwork, info);
   }

   protected abstract void slaexcK(boolean var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, int var9, int var10, int var11, float[] var12, int var13, intW var14);

   public void slag2(float[] a, int lda, float[] b, int ldb, float safmin, floatW scale1, floatW scale2, floatW wr1, floatW wr2, floatW wi) {
      if (debug) {
         System.err.println("slag2");
      }

      this.slag2(a, 0, lda, b, 0, ldb, safmin, scale1, scale2, wr1, wr2, wi);
   }

   public void slag2(float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float safmin, floatW scale1, floatW scale2, floatW wr1, floatW wr2, floatW wi) {
      if (debug) {
         System.err.println("slag2");
      }

      this.slag2K(a, offseta, lda, b, offsetb, ldb, safmin, scale1, scale2, wr1, wr2, wi);
   }

   protected abstract void slag2K(float[] var1, int var2, int var3, float[] var4, int var5, int var6, float var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12);

   public void slag2d(int m, int n, float[] sa, int ldsa, double[] a, int lda, intW info) {
      if (debug) {
         System.err.println("slag2d");
      }

      this.slag2d(m, n, sa, 0, ldsa, a, 0, lda, info);
   }

   public void slag2d(int m, int n, float[] sa, int offsetsa, int ldsa, double[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("slag2d");
      }

      this.slag2dK(m, n, sa, offsetsa, ldsa, a, offseta, lda, info);
   }

   protected abstract void slag2dK(int var1, int var2, float[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9);

   public void slags2(boolean upper, float a1, float a2, float a3, float b1, float b2, float b3, floatW csu, floatW snu, floatW csv, floatW snv, floatW csq, floatW snq) {
      if (debug) {
         System.err.println("slags2");
      }

      this.slags2K(upper, a1, a2, a3, b1, b2, b3, csu, snu, csv, snv, csq, snq);
   }

   protected abstract void slags2K(boolean var1, float var2, float var3, float var4, float var5, float var6, float var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12, floatW var13);

   public void slagtf(int n, float[] a, float lambda, float[] b, float[] c, float tol, float[] d, int[] in, intW info) {
      if (debug) {
         System.err.println("slagtf");
      }

      this.slagtf(n, a, 0, lambda, b, 0, c, 0, tol, d, 0, in, 0, info);
   }

   public void slagtf(int n, float[] a, int offseta, float lambda, float[] b, int offsetb, float[] c, int offsetc, float tol, float[] d, int offsetd, int[] in, int offsetin, intW info) {
      if (debug) {
         System.err.println("slagtf");
      }

      this.slagtfK(n, a, offseta, lambda, b, offsetb, c, offsetc, tol, d, offsetd, in, offsetin, info);
   }

   protected abstract void slagtfK(int var1, float[] var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   public void slagtm(String trans, int n, int nrhs, float alpha, float[] dl, float[] d, float[] du, float[] x, int ldx, float beta, float[] b, int ldb) {
      if (debug) {
         System.err.println("slagtm");
      }

      this.slagtm(trans, n, nrhs, alpha, dl, 0, d, 0, du, 0, x, 0, ldx, beta, b, 0, ldb);
   }

   public void slagtm(String trans, int n, int nrhs, float alpha, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu, float[] x, int offsetx, int ldx, float beta, float[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("slagtm");
      }

      this.slagtmK(trans, n, nrhs, alpha, dl, offsetdl, d, offsetd, du, offsetdu, x, offsetx, ldx, beta, b, offsetb, ldb);
   }

   protected abstract void slagtmK(String var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float var14, float[] var15, int var16, int var17);

   public void slagts(int job, int n, float[] a, float[] b, float[] c, float[] d, int[] in, float[] y, floatW tol, intW info) {
      if (debug) {
         System.err.println("slagts");
      }

      this.slagts(job, n, a, 0, b, 0, c, 0, d, 0, in, 0, y, 0, tol, info);
   }

   public void slagts(int job, int n, float[] a, int offseta, float[] b, int offsetb, float[] c, int offsetc, float[] d, int offsetd, int[] in, int offsetin, float[] y, int offsety, floatW tol, intW info) {
      if (debug) {
         System.err.println("slagts");
      }

      this.slagtsK(job, n, a, offseta, b, offsetb, c, offsetc, d, offsetd, in, offsetin, y, offsety, tol, info);
   }

   protected abstract void slagtsK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int var12, float[] var13, int var14, floatW var15, intW var16);

   public void slagv2(float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, floatW csl, floatW snl, floatW csr, floatW snr) {
      if (debug) {
         System.err.println("slagv2");
      }

      this.slagv2(a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, csl, snl, csr, snr);
   }

   public void slagv2(float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, floatW csl, floatW snl, floatW csr, floatW snr) {
      if (debug) {
         System.err.println("slagv2");
      }

      this.slagv2K(a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, csl, snl, csr, snr);
   }

   protected abstract void slagv2K(float[] var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, floatW var13, floatW var14, floatW var15, floatW var16);

   public void slahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, int iloz, int ihiz, float[] z, int ldz, intW info) {
      if (debug) {
         System.err.println("slahqr");
      }

      this.slahqr(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, info);
   }

   public void slahqr(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, intW info) {
      if (debug) {
         System.err.println("slahqr");
      }

      this.slahqrK(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, info);
   }

   protected abstract void slahqrK(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   public void slahr2(int n, int k, int nb, float[] a, int lda, float[] tau, float[] t, int ldt, float[] y, int ldy) {
      if (debug) {
         System.err.println("slahr2");
      }

      this.slahr2(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
   }

   public void slahr2(int n, int k, int nb, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] t, int offsett, int ldt, float[] y, int offsety, int ldy) {
      if (debug) {
         System.err.println("slahr2");
      }

      this.slahr2K(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
   }

   protected abstract void slahr2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14);

   public void slahrd(int n, int k, int nb, float[] a, int lda, float[] tau, float[] t, int ldt, float[] y, int ldy) {
      if (debug) {
         System.err.println("slahrd");
      }

      this.slahrd(n, k, nb, a, 0, lda, tau, 0, t, 0, ldt, y, 0, ldy);
   }

   public void slahrd(int n, int k, int nb, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] t, int offsett, int ldt, float[] y, int offsety, int ldy) {
      if (debug) {
         System.err.println("slahrd");
      }

      this.slahrdK(n, k, nb, a, offseta, lda, tau, offsettau, t, offsett, ldt, y, offsety, ldy);
   }

   protected abstract void slahrdK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14);

   public void slaic1(int job, int j, float[] x, float sest, float[] w, float gamma, floatW sestpr, floatW s, floatW c) {
      if (debug) {
         System.err.println("slaic1");
      }

      this.slaic1(job, j, x, 0, sest, w, 0, gamma, sestpr, s, c);
   }

   public void slaic1(int job, int j, float[] x, int offsetx, float sest, float[] w, int offsetw, float gamma, floatW sestpr, floatW s, floatW c) {
      if (debug) {
         System.err.println("slaic1");
      }

      this.slaic1K(job, j, x, offsetx, sest, w, offsetw, gamma, sestpr, s, c);
   }

   protected abstract void slaic1K(int var1, int var2, float[] var3, int var4, float var5, float[] var6, int var7, float var8, floatW var9, floatW var10, floatW var11);

   public boolean slaisnan(float sin1, float sin2) {
      if (debug) {
         System.err.println("slaisnan");
      }

      return this.slaisnanK(sin1, sin2);
   }

   protected abstract boolean slaisnanK(float var1, float var2);

   public void slaln2(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int lda, float d1, float d2, float[] b, int ldb, float wr, float wi, float[] x, int ldx, floatW scale, floatW xnorm, intW info) {
      if (debug) {
         System.err.println("slaln2");
      }

      this.slaln2(ltrans, na, nw, smin, ca, a, 0, lda, d1, d2, b, 0, ldb, wr, wi, x, 0, ldx, scale, xnorm, info);
   }

   public void slaln2(boolean ltrans, int na, int nw, float smin, float ca, float[] a, int offseta, int lda, float d1, float d2, float[] b, int offsetb, int ldb, float wr, float wi, float[] x, int offsetx, int ldx, floatW scale, floatW xnorm, intW info) {
      if (debug) {
         System.err.println("slaln2");
      }

      this.slaln2K(ltrans, na, nw, smin, ca, a, offseta, lda, d1, d2, b, offsetb, ldb, wr, wi, x, offsetx, ldx, scale, xnorm, info);
   }

   protected abstract void slaln2K(boolean var1, int var2, int var3, float var4, float var5, float[] var6, int var7, int var8, float var9, float var10, float[] var11, int var12, int var13, float var14, float var15, float[] var16, int var17, int var18, floatW var19, floatW var20, intW var21);

   public void slals0(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int ldb, float[] bx, int ldbx, int[] perm, int givptr, int[] givcol, int ldgcol, float[] givnum, int ldgnum, float[] poles, float[] difl, float[] difr, float[] z, int k, float c, float s, float[] work, intW info) {
      if (debug) {
         System.err.println("slals0");
      }

      this.slals0(icompq, nl, nr, sqre, nrhs, b, 0, ldb, bx, 0, ldbx, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, info);
   }

   public void slals0(int icompq, int nl, int nr, int sqre, int nrhs, float[] b, int offsetb, int ldb, float[] bx, int offsetbx, int ldbx, int[] perm, int offsetperm, int givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, float[] poles, int offsetpoles, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, int k, float c, float s, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slals0");
      }

      this.slals0K(icompq, nl, nr, sqre, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, info);
   }

   protected abstract void slals0K(int var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, int var14, int[] var15, int var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, float[] var27, int var28, int var29, float var30, float var31, float[] var32, int var33, intW var34);

   public void slalsa(int icompq, int smlsiz, int n, int nrhs, float[] b, int ldb, float[] bx, int ldbx, float[] u, int ldu, float[] vt, int[] k, float[] difl, float[] difr, float[] z, float[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, float[] givnum, float[] c, float[] s, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slalsa");
      }

      this.slalsa(icompq, smlsiz, n, nrhs, b, 0, ldb, bx, 0, ldbx, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
   }

   public void slalsa(int icompq, int smlsiz, int n, int nrhs, float[] b, int offsetb, int ldb, float[] bx, int offsetbx, int ldbx, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int[] k, int offsetk, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, float[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, float[] givnum, int offsetgivnum, float[] c, int offsetc, float[] s, int offsets, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slalsa");
      }

      this.slalsaK(icompq, smlsiz, n, nrhs, b, offsetb, ldb, bx, offsetbx, ldbx, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slalsaK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int var30, int[] var31, int var32, float[] var33, int var34, float[] var35, int var36, float[] var37, int var38, float[] var39, int var40, int[] var41, int var42, intW var43);

   public void slalsd(String uplo, int smlsiz, int n, int nrhs, float[] d, float[] e, float[] b, int ldb, float rcond, intW rank, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slalsd");
      }

      this.slalsd(uplo, smlsiz, n, nrhs, d, 0, e, 0, b, 0, ldb, rcond, rank, work, 0, iwork, 0, info);
   }

   public void slalsd(String uplo, int smlsiz, int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, float rcond, intW rank, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slalsd");
      }

      this.slalsdK(uplo, smlsiz, n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, rcond, rank, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slalsdK(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   public void slamrg(int n1, int n2, float[] a, int strd1, int strd2, int[] index) {
      if (debug) {
         System.err.println("slamrg");
      }

      this.slamrg(n1, n2, a, 0, strd1, strd2, index, 0);
   }

   public void slamrg(int n1, int n2, float[] a, int offseta, int strd1, int strd2, int[] index, int offsetindex) {
      if (debug) {
         System.err.println("slamrg");
      }

      this.slamrgK(n1, n2, a, offseta, strd1, strd2, index, offsetindex);
   }

   protected abstract void slamrgK(int var1, int var2, float[] var3, int var4, int var5, int var6, int[] var7, int var8);

   public int slaneg(int n, float[] d, float[] lld, float sigma, float pivmin, int r) {
      if (debug) {
         System.err.println("slaneg");
      }

      return this.slaneg(n, d, 0, lld, 0, sigma, pivmin, r);
   }

   public int slaneg(int n, float[] d, int offsetd, float[] lld, int offsetlld, float sigma, float pivmin, int r) {
      if (debug) {
         System.err.println("slaneg");
      }

      return this.slanegK(n, d, offsetd, lld, offsetlld, sigma, pivmin, r);
   }

   protected abstract int slanegK(int var1, float[] var2, int var3, float[] var4, int var5, float var6, float var7, int var8);

   public float slangb(String norm, int n, int kl, int ku, float[] ab, int ldab, float[] work) {
      if (debug) {
         System.err.println("slangb");
      }

      return this.slangb(norm, n, kl, ku, ab, 0, ldab, work, 0);
   }

   public float slangb(String norm, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slangb");
      }

      return this.slangbK(norm, n, kl, ku, ab, offsetab, ldab, work, offsetwork);
   }

   protected abstract float slangbK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   public float slange(String norm, int m, int n, float[] a, int lda, float[] work) {
      if (debug) {
         System.err.println("slange");
      }

      return this.slange(norm, m, n, a, 0, lda, work, 0);
   }

   public float slange(String norm, int m, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slange");
      }

      return this.slangeK(norm, m, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract float slangeK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8);

   public float slangt(String norm, int n, float[] dl, float[] d, float[] du) {
      if (debug) {
         System.err.println("slangt");
      }

      return this.slangt(norm, n, dl, 0, d, 0, du, 0);
   }

   public float slangt(String norm, int n, float[] dl, int offsetdl, float[] d, int offsetd, float[] du, int offsetdu) {
      if (debug) {
         System.err.println("slangt");
      }

      return this.slangtK(norm, n, dl, offsetdl, d, offsetd, du, offsetdu);
   }

   protected abstract float slangtK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8);

   public float slanhs(String norm, int n, float[] a, int lda, float[] work) {
      if (debug) {
         System.err.println("slanhs");
      }

      return this.slanhs(norm, n, a, 0, lda, work, 0);
   }

   public float slanhs(String norm, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slanhs");
      }

      return this.slanhsK(norm, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract float slanhsK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7);

   public float slansb(String norm, String uplo, int n, int k, float[] ab, int ldab, float[] work) {
      if (debug) {
         System.err.println("slansb");
      }

      return this.slansb(norm, uplo, n, k, ab, 0, ldab, work, 0);
   }

   public float slansb(String norm, String uplo, int n, int k, float[] ab, int offsetab, int ldab, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slansb");
      }

      return this.slansbK(norm, uplo, n, k, ab, offsetab, ldab, work, offsetwork);
   }

   protected abstract float slansbK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   public float slansp(String norm, String uplo, int n, float[] ap, float[] work) {
      if (debug) {
         System.err.println("slansp");
      }

      return this.slansp(norm, uplo, n, ap, 0, work, 0);
   }

   public float slansp(String norm, String uplo, int n, float[] ap, int offsetap, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slansp");
      }

      return this.slanspK(norm, uplo, n, ap, offsetap, work, offsetwork);
   }

   protected abstract float slanspK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7);

   public float slanst(String norm, int n, float[] d, float[] e) {
      if (debug) {
         System.err.println("slanst");
      }

      return this.slanst(norm, n, d, 0, e, 0);
   }

   public float slanst(String norm, int n, float[] d, int offsetd, float[] e, int offsete) {
      if (debug) {
         System.err.println("slanst");
      }

      return this.slanstK(norm, n, d, offsetd, e, offsete);
   }

   protected abstract float slanstK(String var1, int var2, float[] var3, int var4, float[] var5, int var6);

   public float slansy(String norm, String uplo, int n, float[] a, int lda, float[] work) {
      if (debug) {
         System.err.println("slansy");
      }

      return this.slansy(norm, uplo, n, a, 0, lda, work, 0);
   }

   public float slansy(String norm, String uplo, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slansy");
      }

      return this.slansyK(norm, uplo, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract float slansyK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8);

   public float slantb(String norm, String uplo, String diag, int n, int k, float[] ab, int ldab, float[] work) {
      if (debug) {
         System.err.println("slantb");
      }

      return this.slantb(norm, uplo, diag, n, k, ab, 0, ldab, work, 0);
   }

   public float slantb(String norm, String uplo, String diag, int n, int k, float[] ab, int offsetab, int ldab, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slantb");
      }

      return this.slantbK(norm, uplo, diag, n, k, ab, offsetab, ldab, work, offsetwork);
   }

   protected abstract float slantbK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10);

   public float slantp(String norm, String uplo, String diag, int n, float[] ap, float[] work) {
      if (debug) {
         System.err.println("slantp");
      }

      return this.slantp(norm, uplo, diag, n, ap, 0, work, 0);
   }

   public float slantp(String norm, String uplo, String diag, int n, float[] ap, int offsetap, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slantp");
      }

      return this.slantpK(norm, uplo, diag, n, ap, offsetap, work, offsetwork);
   }

   protected abstract float slantpK(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8);

   public float slantr(String norm, String uplo, String diag, int m, int n, float[] a, int lda, float[] work) {
      if (debug) {
         System.err.println("slantr");
      }

      return this.slantr(norm, uplo, diag, m, n, a, 0, lda, work, 0);
   }

   public float slantr(String norm, String uplo, String diag, int m, int n, float[] a, int offseta, int lda, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slantr");
      }

      return this.slantrK(norm, uplo, diag, m, n, a, offseta, lda, work, offsetwork);
   }

   protected abstract float slantrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10);

   public void slanv2(floatW a, floatW b, floatW c, floatW d, floatW rt1r, floatW rt1i, floatW rt2r, floatW rt2i, floatW cs, floatW sn) {
      if (debug) {
         System.err.println("slanv2");
      }

      this.slanv2K(a, b, c, d, rt1r, rt1i, rt2r, rt2i, cs, sn);
   }

   protected abstract void slanv2K(floatW var1, floatW var2, floatW var3, floatW var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10);

   public void slapll(int n, float[] x, int incx, float[] y, int incy, floatW ssmin) {
      if (debug) {
         System.err.println("slapll");
      }

      this.slapll(n, x, 0, incx, y, 0, incy, ssmin);
   }

   public void slapll(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, floatW ssmin) {
      if (debug) {
         System.err.println("slapll");
      }

      this.slapllK(n, x, offsetx, incx, y, offsety, incy, ssmin);
   }

   protected abstract void slapllK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, floatW var8);

   public void slapmt(boolean forwrd, int m, int n, float[] x, int ldx, int[] k) {
      if (debug) {
         System.err.println("slapmt");
      }

      this.slapmt(forwrd, m, n, x, 0, ldx, k, 0);
   }

   public void slapmt(boolean forwrd, int m, int n, float[] x, int offsetx, int ldx, int[] k, int offsetk) {
      if (debug) {
         System.err.println("slapmt");
      }

      this.slapmtK(forwrd, m, n, x, offsetx, ldx, k, offsetk);
   }

   protected abstract void slapmtK(boolean var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8);

   public float slapy2(float x, float y) {
      if (debug) {
         System.err.println("slapy2");
      }

      return this.slapy2K(x, y);
   }

   protected abstract float slapy2K(float var1, float var2);

   public float slapy3(float x, float y, float z) {
      if (debug) {
         System.err.println("slapy3");
      }

      return this.slapy3K(x, y, z);
   }

   protected abstract float slapy3K(float var1, float var2, float var3);

   public void slaqgb(int m, int n, int kl, int ku, float[] ab, int ldab, float[] r, float[] c, float rowcnd, float colcnd, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqgb");
      }

      this.slaqgb(m, n, kl, ku, ab, 0, ldab, r, 0, c, 0, rowcnd, colcnd, amax, equed);
   }

   public void slaqgb(int m, int n, int kl, int ku, float[] ab, int offsetab, int ldab, float[] r, int offsetr, float[] c, int offsetc, float rowcnd, float colcnd, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqgb");
      }

      this.slaqgbK(m, n, kl, ku, ab, offsetab, ldab, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
   }

   protected abstract void slaqgbK(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float var12, float var13, float var14, StringW var15);

   public void slaqge(int m, int n, float[] a, int lda, float[] r, float[] c, float rowcnd, float colcnd, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqge");
      }

      this.slaqge(m, n, a, 0, lda, r, 0, c, 0, rowcnd, colcnd, amax, equed);
   }

   public void slaqge(int m, int n, float[] a, int offseta, int lda, float[] r, int offsetr, float[] c, int offsetc, float rowcnd, float colcnd, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqge");
      }

      this.slaqgeK(m, n, a, offseta, lda, r, offsetr, c, offsetc, rowcnd, colcnd, amax, equed);
   }

   protected abstract void slaqgeK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, float var12, StringW var13);

   public void slaqp2(int m, int n, int offset, float[] a, int lda, int[] jpvt, float[] tau, float[] vn1, float[] vn2, float[] work) {
      if (debug) {
         System.err.println("slaqp2");
      }

      this.slaqp2(m, n, offset, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, work, 0);
   }

   public void slaqp2(int m, int n, int offset, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] vn1, int offsetvn1, float[] vn2, int offsetvn2, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slaqp2");
      }

      this.slaqp2K(m, n, offset, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, work, offsetwork);
   }

   protected abstract void slaqp2K(int var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16);

   public void slaqps(int m, int n, int offset, int nb, intW kb, float[] a, int lda, int[] jpvt, float[] tau, float[] vn1, float[] vn2, float[] auxv, float[] f, int ldf) {
      if (debug) {
         System.err.println("slaqps");
      }

      this.slaqps(m, n, offset, nb, kb, a, 0, lda, jpvt, 0, tau, 0, vn1, 0, vn2, 0, auxv, 0, f, 0, ldf);
   }

   public void slaqps(int m, int n, int offset, int nb, intW kb, float[] a, int offseta, int lda, int[] jpvt, int offsetjpvt, float[] tau, int offsettau, float[] vn1, int offsetvn1, float[] vn2, int offsetvn2, float[] auxv, int offsetauxv, float[] f, int offsetf, int ldf) {
      if (debug) {
         System.err.println("slaqps");
      }

      this.slaqpsK(m, n, offset, nb, kb, a, offseta, lda, jpvt, offsetjpvt, tau, offsettau, vn1, offsetvn1, vn2, offsetvn2, auxv, offsetauxv, f, offsetf, ldf);
   }

   protected abstract void slaqpsK(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, int var8, int[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21);

   public void slaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, int iloz, int ihiz, float[] z, int ldz, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("slaqr0");
      }

      this.slaqr0(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
   }

   public void slaqr0(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("slaqr0");
      }

      this.slaqr0K(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void slaqr0K(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, intW var21);

   public void slaqr1(int n, float[] h, int ldh, float sr1, float si1, float sr2, float si2, float[] v) {
      if (debug) {
         System.err.println("slaqr1");
      }

      this.slaqr1(n, h, 0, ldh, sr1, si1, sr2, si2, v, 0);
   }

   public void slaqr1(int n, float[] h, int offseth, int ldh, float sr1, float si1, float sr2, float si2, float[] v, int offsetv) {
      if (debug) {
         System.err.println("slaqr1");
      }

      this.slaqr1K(n, h, offseth, ldh, sr1, si1, sr2, si2, v, offsetv);
   }

   protected abstract void slaqr1K(int var1, float[] var2, int var3, int var4, float var5, float var6, float var7, float var8, float[] var9, int var10);

   public void slaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int ldh, int iloz, int ihiz, float[] z, int ldz, intW ns, intW nd, float[] sr, float[] si, float[] v, int ldv, int nh, float[] t, int ldt, int nv, float[] wv, int ldwv, float[] work, int lwork) {
      if (debug) {
         System.err.println("slaqr2");
      }

      this.slaqr2(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
   }

   public void slaqr2(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, intW ns, intW nd, float[] sr, int offsetsr, float[] si, int offsetsi, float[] v, int offsetv, int ldv, int nh, float[] t, int offsett, int ldt, int nv, float[] wv, int offsetwv, int ldwv, float[] work, int offsetwork, int lwork) {
      if (debug) {
         System.err.println("slaqr2");
      }

      this.slaqr2K(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
   }

   protected abstract void slaqr2K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, int var11, float[] var12, int var13, int var14, intW var15, intW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, int var24, float[] var25, int var26, int var27, int var28, float[] var29, int var30, int var31, float[] var32, int var33, int var34);

   public void slaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int ldh, int iloz, int ihiz, float[] z, int ldz, intW ns, intW nd, float[] sr, float[] si, float[] v, int ldv, int nh, float[] t, int ldt, int nv, float[] wv, int ldwv, float[] work, int lwork) {
      if (debug) {
         System.err.println("slaqr3");
      }

      this.slaqr3(wantt, wantz, n, ktop, kbot, nw, h, 0, ldh, iloz, ihiz, z, 0, ldz, ns, nd, sr, 0, si, 0, v, 0, ldv, nh, t, 0, ldt, nv, wv, 0, ldwv, work, 0, lwork);
   }

   public void slaqr3(boolean wantt, boolean wantz, int n, int ktop, int kbot, int nw, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, intW ns, intW nd, float[] sr, int offsetsr, float[] si, int offsetsi, float[] v, int offsetv, int ldv, int nh, float[] t, int offsett, int ldt, int nv, float[] wv, int offsetwv, int ldwv, float[] work, int offsetwork, int lwork) {
      if (debug) {
         System.err.println("slaqr3");
      }

      this.slaqr3K(wantt, wantz, n, ktop, kbot, nw, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, ns, nd, sr, offsetsr, si, offsetsi, v, offsetv, ldv, nh, t, offsett, ldt, nv, wv, offsetwv, ldwv, work, offsetwork, lwork);
   }

   protected abstract void slaqr3K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, int var11, float[] var12, int var13, int var14, intW var15, intW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, int var24, float[] var25, int var26, int var27, int var28, float[] var29, int var30, int var31, float[] var32, int var33, int var34);

   public void slaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int ldh, float[] wr, float[] wi, int iloz, int ihiz, float[] z, int ldz, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("slaqr4");
      }

      this.slaqr4(wantt, wantz, n, ilo, ihi, h, 0, ldh, wr, 0, wi, 0, iloz, ihiz, z, 0, ldz, work, 0, lwork, info);
   }

   public void slaqr4(boolean wantt, boolean wantz, int n, int ilo, int ihi, float[] h, int offseth, int ldh, float[] wr, int offsetwr, float[] wi, int offsetwi, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("slaqr4");
      }

      this.slaqr4K(wantt, wantz, n, ilo, ihi, h, offseth, ldh, wr, offsetwr, wi, offsetwi, iloz, ihiz, z, offsetz, ldz, work, offsetwork, lwork, info);
   }

   protected abstract void slaqr4K(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, intW var21);

   public void slaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, float[] si, float[] h, int ldh, int iloz, int ihiz, float[] z, int ldz, float[] v, int ldv, float[] u, int ldu, int nv, float[] wv, int ldwv, int nh, float[] wh, int ldwh) {
      if (debug) {
         System.err.println("slaqr5");
      }

      this.slaqr5(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, 0, si, 0, h, 0, ldh, iloz, ihiz, z, 0, ldz, v, 0, ldv, u, 0, ldu, nv, wv, 0, ldwv, nh, wh, 0, ldwh);
   }

   public void slaqr5(boolean wantt, boolean wantz, int kacc22, int n, int ktop, int kbot, int nshfts, float[] sr, int offsetsr, float[] si, int offsetsi, float[] h, int offseth, int ldh, int iloz, int ihiz, float[] z, int offsetz, int ldz, float[] v, int offsetv, int ldv, float[] u, int offsetu, int ldu, int nv, float[] wv, int offsetwv, int ldwv, int nh, float[] wh, int offsetwh, int ldwh) {
      if (debug) {
         System.err.println("slaqr5");
      }

      this.slaqr5K(wantt, wantz, kacc22, n, ktop, kbot, nshfts, sr, offsetsr, si, offsetsi, h, offseth, ldh, iloz, ihiz, z, offsetz, ldz, v, offsetv, ldv, u, offsetu, ldu, nv, wv, offsetwv, ldwv, nh, wh, offsetwh, ldwh);
   }

   protected abstract void slaqr5K(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int var26, float[] var27, int var28, int var29, int var30, float[] var31, int var32, int var33);

   public void slaqsb(String uplo, int n, int kd, float[] ab, int ldab, float[] s, float scond, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqsb");
      }

      this.slaqsb(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, equed);
   }

   public void slaqsb(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] s, int offsets, float scond, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqsb");
      }

      this.slaqsbK(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, equed);
   }

   protected abstract void slaqsbK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float var9, float var10, StringW var11);

   public void slaqsp(String uplo, int n, float[] ap, float[] s, float scond, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqsp");
      }

      this.slaqsp(uplo, n, ap, 0, s, 0, scond, amax, equed);
   }

   public void slaqsp(String uplo, int n, float[] ap, int offsetap, float[] s, int offsets, float scond, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqsp");
      }

      this.slaqspK(uplo, n, ap, offsetap, s, offsets, scond, amax, equed);
   }

   protected abstract void slaqspK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float var7, float var8, StringW var9);

   public void slaqsy(String uplo, int n, float[] a, int lda, float[] s, float scond, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqsy");
      }

      this.slaqsy(uplo, n, a, 0, lda, s, 0, scond, amax, equed);
   }

   public void slaqsy(String uplo, int n, float[] a, int offseta, int lda, float[] s, int offsets, float scond, float amax, StringW equed) {
      if (debug) {
         System.err.println("slaqsy");
      }

      this.slaqsyK(uplo, n, a, offseta, lda, s, offsets, scond, amax, equed);
   }

   protected abstract void slaqsyK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float var8, float var9, StringW var10);

   public void slaqtr(boolean ltran, boolean lreal, int n, float[] t, int ldt, float[] b, float w, floatW scale, float[] x, float[] work, intW info) {
      if (debug) {
         System.err.println("slaqtr");
      }

      this.slaqtr(ltran, lreal, n, t, 0, ldt, b, 0, w, scale, x, 0, work, 0, info);
   }

   public void slaqtr(boolean ltran, boolean lreal, int n, float[] t, int offsett, int ldt, float[] b, int offsetb, float w, floatW scale, float[] x, int offsetx, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slaqtr");
      }

      this.slaqtrK(ltran, lreal, n, t, offsett, ldt, b, offsetb, w, scale, x, offsetx, work, offsetwork, info);
   }

   protected abstract void slaqtrK(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float var9, floatW var10, float[] var11, int var12, float[] var13, int var14, intW var15);

   public void slar1v(int n, int b1, int bn, float lambda, float[] d, float[] l, float[] ld, float[] lld, float pivmin, float gaptol, float[] z, boolean wantnc, intW negcnt, floatW ztz, floatW mingma, intW r, int[] isuppz, floatW nrminv, floatW resid, floatW rqcorr, float[] work) {
      if (debug) {
         System.err.println("slar1v");
      }

      this.slar1v(n, b1, bn, lambda, d, 0, l, 0, ld, 0, lld, 0, pivmin, gaptol, z, 0, wantnc, negcnt, ztz, mingma, r, isuppz, 0, nrminv, resid, rqcorr, work, 0);
   }

   public void slar1v(int n, int b1, int bn, float lambda, float[] d, int offsetd, float[] l, int offsetl, float[] ld, int offsetld, float[] lld, int offsetlld, float pivmin, float gaptol, float[] z, int offsetz, boolean wantnc, intW negcnt, floatW ztz, floatW mingma, intW r, int[] isuppz, int offsetisuppz, floatW nrminv, floatW resid, floatW rqcorr, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slar1v");
      }

      this.slar1vK(n, b1, bn, lambda, d, offsetd, l, offsetl, ld, offsetld, lld, offsetlld, pivmin, gaptol, z, offsetz, wantnc, negcnt, ztz, mingma, r, isuppz, offsetisuppz, nrminv, resid, rqcorr, work, offsetwork);
   }

   protected abstract void slar1vK(int var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float[] var15, int var16, boolean var17, intW var18, floatW var19, floatW var20, intW var21, int[] var22, int var23, floatW var24, floatW var25, floatW var26, float[] var27, int var28);

   public void slar2v(int n, float[] x, float[] y, float[] z, int incx, float[] c, float[] s, int incc) {
      if (debug) {
         System.err.println("slar2v");
      }

      this.slar2v(n, x, 0, y, 0, z, 0, incx, c, 0, s, 0, incc);
   }

   public void slar2v(int n, float[] x, int offsetx, float[] y, int offsety, float[] z, int offsetz, int incx, float[] c, int offsetc, float[] s, int offsets, int incc) {
      if (debug) {
         System.err.println("slar2v");
      }

      this.slar2vK(n, x, offsetx, y, offsety, z, offsetz, incx, c, offsetc, s, offsets, incc);
   }

   protected abstract void slar2vK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13);

   public void slarf(String side, int m, int n, float[] v, int incv, float tau, float[] c, int Ldc, float[] work) {
      if (debug) {
         System.err.println("slarf");
      }

      this.slarf(side, m, n, v, 0, incv, tau, c, 0, Ldc, work, 0);
   }

   public void slarf(String side, int m, int n, float[] v, int offsetv, int incv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slarf");
      }

      this.slarfK(side, m, n, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
   }

   protected abstract void slarfK(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12);

   public void slarfb(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int ldv, float[] t, int ldt, float[] c, int Ldc, float[] work, int ldwork) {
      if (debug) {
         System.err.println("slarfb");
      }

      this.slarfb(side, trans, direct, storev, m, n, k, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
   }

   public void slarfb(String side, String trans, String direct, String storev, int m, int n, int k, float[] v, int offsetv, int ldv, float[] t, int offsett, int ldt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int ldwork) {
      if (debug) {
         System.err.println("slarfb");
      }

      this.slarfbK(side, trans, direct, storev, m, n, k, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
   }

   protected abstract void slarfbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19);

   public void slarfg(int n, floatW alpha, float[] x, int incx, floatW tau) {
      if (debug) {
         System.err.println("slarfg");
      }

      this.slarfg(n, alpha, x, 0, incx, tau);
   }

   public void slarfg(int n, floatW alpha, float[] x, int offsetx, int incx, floatW tau) {
      if (debug) {
         System.err.println("slarfg");
      }

      this.slarfgK(n, alpha, x, offsetx, incx, tau);
   }

   protected abstract void slarfgK(int var1, floatW var2, float[] var3, int var4, int var5, floatW var6);

   public void slarft(String direct, String storev, int n, int k, float[] v, int ldv, float[] tau, float[] t, int ldt) {
      if (debug) {
         System.err.println("slarft");
      }

      this.slarft(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
   }

   public void slarft(String direct, String storev, int n, int k, float[] v, int offsetv, int ldv, float[] tau, int offsettau, float[] t, int offsett, int ldt) {
      if (debug) {
         System.err.println("slarft");
      }

      this.slarftK(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
   }

   protected abstract void slarftK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   public void slarfx(String side, int m, int n, float[] v, float tau, float[] c, int Ldc, float[] work) {
      if (debug) {
         System.err.println("slarfx");
      }

      this.slarfx(side, m, n, v, 0, tau, c, 0, Ldc, work, 0);
   }

   public void slarfx(String side, int m, int n, float[] v, int offsetv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slarfx");
      }

      this.slarfxK(side, m, n, v, offsetv, tau, c, offsetc, Ldc, work, offsetwork);
   }

   protected abstract void slarfxK(String var1, int var2, int var3, float[] var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11);

   public void slargv(int n, float[] x, int incx, float[] y, int incy, float[] c, int incc) {
      if (debug) {
         System.err.println("slargv");
      }

      this.slargv(n, x, 0, incx, y, 0, incy, c, 0, incc);
   }

   public void slargv(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] c, int offsetc, int incc) {
      if (debug) {
         System.err.println("slargv");
      }

      this.slargvK(n, x, offsetx, incx, y, offsety, incy, c, offsetc, incc);
   }

   protected abstract void slargvK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   public void slarnv(int idist, int[] iseed, int n, float[] x) {
      if (debug) {
         System.err.println("slarnv");
      }

      this.slarnv(idist, iseed, 0, n, x, 0);
   }

   public void slarnv(int idist, int[] iseed, int offsetiseed, int n, float[] x, int offsetx) {
      if (debug) {
         System.err.println("slarnv");
      }

      this.slarnvK(idist, iseed, offsetiseed, n, x, offsetx);
   }

   protected abstract void slarnvK(int var1, int[] var2, int var3, int var4, float[] var5, int var6);

   public void slarra(int n, float[] d, float[] e, float[] e2, float spltol, float tnrm, intW nsplit, int[] isplit, intW info) {
      if (debug) {
         System.err.println("slarra");
      }

      this.slarra(n, d, 0, e, 0, e2, 0, spltol, tnrm, nsplit, isplit, 0, info);
   }

   public void slarra(int n, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float spltol, float tnrm, intW nsplit, int[] isplit, int offsetisplit, intW info) {
      if (debug) {
         System.err.println("slarra");
      }

      this.slarraK(n, d, offsetd, e, offsete, e2, offsete2, spltol, tnrm, nsplit, isplit, offsetisplit, info);
   }

   protected abstract void slarraK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, intW var10, int[] var11, int var12, intW var13);

   public void slarrb(int n, float[] d, float[] lld, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, float[] wgap, float[] werr, float[] work, int[] iwork, float pivmin, float spdiam, int twist, intW info) {
      if (debug) {
         System.err.println("slarrb");
      }

      this.slarrb(n, d, 0, lld, 0, ifirst, ilast, rtol1, rtol2, offset, w, 0, wgap, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, twist, info);
   }

   public void slarrb(int n, float[] d, int offsetd, float[] lld, int offsetlld, int ifirst, int ilast, float rtol1, float rtol2, int offset, float[] w, int offsetw, float[] wgap, int offsetwgap, float[] werr, int offsetwerr, float[] work, int offsetwork, int[] iwork, int offsetiwork, float pivmin, float spdiam, int twist, intW info) {
      if (debug) {
         System.err.println("slarrb");
      }

      this.slarrbK(n, d, offsetd, lld, offsetlld, ifirst, ilast, rtol1, rtol2, offset, w, offsetw, wgap, offsetwgap, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, twist, info);
   }

   protected abstract void slarrbK(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int var7, float var8, float var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, int[] var19, int var20, float var21, float var22, int var23, intW var24);

   public void slarrc(String jobt, int n, float vl, float vu, float[] d, float[] e, float pivmin, intW eigcnt, intW lcnt, intW rcnt, intW info) {
      if (debug) {
         System.err.println("slarrc");
      }

      this.slarrc(jobt, n, vl, vu, d, 0, e, 0, pivmin, eigcnt, lcnt, rcnt, info);
   }

   public void slarrc(String jobt, int n, float vl, float vu, float[] d, int offsetd, float[] e, int offsete, float pivmin, intW eigcnt, intW lcnt, intW rcnt, intW info) {
      if (debug) {
         System.err.println("slarrc");
      }

      this.slarrcK(jobt, n, vl, vu, d, offsetd, e, offsete, pivmin, eigcnt, lcnt, rcnt, info);
   }

   protected abstract void slarrcK(String var1, int var2, float var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, intW var10, intW var11, intW var12, intW var13);

   public void slarrd(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, float reltol, float[] d, float[] e, float[] e2, float pivmin, int nsplit, int[] isplit, intW m, float[] w, float[] werr, floatW wl, floatW wu, int[] iblock, int[] indexw, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slarrd");
      }

      this.slarrd(range, order, n, vl, vu, il, iu, gers, 0, reltol, d, 0, e, 0, e2, 0, pivmin, nsplit, isplit, 0, m, w, 0, werr, 0, wl, wu, iblock, 0, indexw, 0, work, 0, iwork, 0, info);
   }

   public void slarrd(String range, String order, int n, float vl, float vu, int il, int iu, float[] gers, int offsetgers, float reltol, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float pivmin, int nsplit, int[] isplit, int offsetisplit, intW m, float[] w, int offsetw, float[] werr, int offsetwerr, floatW wl, floatW wu, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slarrd");
      }

      this.slarrdK(range, order, n, vl, vu, il, iu, gers, offsetgers, reltol, d, offsetd, e, offsete, e2, offsete2, pivmin, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wl, wu, iblock, offsetiblock, indexw, offsetindexw, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slarrdK(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, float var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float var17, int var18, int[] var19, int var20, intW var21, float[] var22, int var23, float[] var24, int var25, floatW var26, floatW var27, int[] var28, int var29, int[] var30, int var31, float[] var32, int var33, int[] var34, int var35, intW var36);

   public void slarre(String range, int n, floatW vl, floatW vu, int il, int iu, float[] d, float[] e, float[] e2, float rtol1, float rtol2, float spltol, intW nsplit, int[] isplit, intW m, float[] w, float[] werr, float[] wgap, int[] iblock, int[] indexw, float[] gers, floatW pivmin, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slarre");
      }

      this.slarre(range, n, vl, vu, il, iu, d, 0, e, 0, e2, 0, rtol1, rtol2, spltol, nsplit, isplit, 0, m, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, pivmin, work, 0, iwork, 0, info);
   }

   public void slarre(String range, int n, floatW vl, floatW vu, int il, int iu, float[] d, int offsetd, float[] e, int offsete, float[] e2, int offsete2, float rtol1, float rtol2, float spltol, intW nsplit, int[] isplit, int offsetisplit, intW m, float[] w, int offsetw, float[] werr, int offsetwerr, float[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] gers, int offsetgers, floatW pivmin, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slarre");
      }

      this.slarreK(range, n, vl, vu, il, iu, d, offsetd, e, offsete, e2, offsete2, rtol1, rtol2, spltol, nsplit, isplit, offsetisplit, m, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, pivmin, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slarreK(String var1, int var2, floatW var3, floatW var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float var15, intW var16, int[] var17, int var18, intW var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, int[] var28, int var29, float[] var30, int var31, floatW var32, float[] var33, int var34, int[] var35, int var36, intW var37);

   public void slarrf(int n, float[] d, float[] l, float[] ld, int clstrt, int clend, float[] w, float[] wgap, float[] werr, float spdiam, float clgapl, float clgapr, float pivmin, floatW sigma, float[] dplus, float[] lplus, float[] work, intW info) {
      if (debug) {
         System.err.println("slarrf");
      }

      this.slarrf(n, d, 0, l, 0, ld, 0, clstrt, clend, w, 0, wgap, 0, werr, 0, spdiam, clgapl, clgapr, pivmin, sigma, dplus, 0, lplus, 0, work, 0, info);
   }

   public void slarrf(int n, float[] d, int offsetd, float[] l, int offsetl, float[] ld, int offsetld, int clstrt, int clend, float[] w, int offsetw, float[] wgap, int offsetwgap, float[] werr, int offsetwerr, float spdiam, float clgapl, float clgapr, float pivmin, floatW sigma, float[] dplus, int offsetdplus, float[] lplus, int offsetlplus, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slarrf");
      }

      this.slarrfK(n, d, offsetd, l, offsetl, ld, offsetld, clstrt, clend, w, offsetw, wgap, offsetwgap, werr, offsetwerr, spdiam, clgapl, clgapr, pivmin, sigma, dplus, offsetdplus, lplus, offsetlplus, work, offsetwork, info);
   }

   protected abstract void slarrfK(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float var16, float var17, float var18, float var19, floatW var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, intW var27);

   public void slarrj(int n, float[] d, float[] e2, int ifirst, int ilast, float rtol, int offset, float[] w, float[] werr, float[] work, int[] iwork, float pivmin, float spdiam, intW info) {
      if (debug) {
         System.err.println("slarrj");
      }

      this.slarrj(n, d, 0, e2, 0, ifirst, ilast, rtol, offset, w, 0, werr, 0, work, 0, iwork, 0, pivmin, spdiam, info);
   }

   public void slarrj(int n, float[] d, int offsetd, float[] e2, int offsete2, int ifirst, int ilast, float rtol, int offset, float[] w, int offsetw, float[] werr, int offsetwerr, float[] work, int offsetwork, int[] iwork, int offsetiwork, float pivmin, float spdiam, intW info) {
      if (debug) {
         System.err.println("slarrj");
      }

      this.slarrjK(n, d, offsetd, e2, offsete2, ifirst, ilast, rtol, offset, w, offsetw, werr, offsetwerr, work, offsetwork, iwork, offsetiwork, pivmin, spdiam, info);
   }

   protected abstract void slarrjK(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int var7, float var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int[] var16, int var17, float var18, float var19, intW var20);

   public void slarrk(int n, int iw, float gl, float gu, float[] d, float[] e2, float pivmin, float reltol, floatW w, floatW werr, intW info) {
      if (debug) {
         System.err.println("slarrk");
      }

      this.slarrk(n, iw, gl, gu, d, 0, e2, 0, pivmin, reltol, w, werr, info);
   }

   public void slarrk(int n, int iw, float gl, float gu, float[] d, int offsetd, float[] e2, int offsete2, float pivmin, float reltol, floatW w, floatW werr, intW info) {
      if (debug) {
         System.err.println("slarrk");
      }

      this.slarrkK(n, iw, gl, gu, d, offsetd, e2, offsete2, pivmin, reltol, w, werr, info);
   }

   protected abstract void slarrkK(int var1, int var2, float var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float var10, floatW var11, floatW var12, intW var13);

   public void slarrr(int n, float[] d, float[] e, intW info) {
      if (debug) {
         System.err.println("slarrr");
      }

      this.slarrr(n, d, 0, e, 0, info);
   }

   public void slarrr(int n, float[] d, int offsetd, float[] e, int offsete, intW info) {
      if (debug) {
         System.err.println("slarrr");
      }

      this.slarrrK(n, d, offsetd, e, offsete, info);
   }

   protected abstract void slarrrK(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   public void slarrv(int n, float vl, float vu, float[] d, float[] l, float pivmin, int[] isplit, int m, int dol, int dou, float minrgp, floatW rtol1, floatW rtol2, float[] w, float[] werr, float[] wgap, int[] iblock, int[] indexw, float[] gers, float[] z, int ldz, int[] isuppz, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slarrv");
      }

      this.slarrv(n, vl, vu, d, 0, l, 0, pivmin, isplit, 0, m, dol, dou, minrgp, rtol1, rtol2, w, 0, werr, 0, wgap, 0, iblock, 0, indexw, 0, gers, 0, z, 0, ldz, isuppz, 0, work, 0, iwork, 0, info);
   }

   public void slarrv(int n, float vl, float vu, float[] d, int offsetd, float[] l, int offsetl, float pivmin, int[] isplit, int offsetisplit, int m, int dol, int dou, float minrgp, floatW rtol1, floatW rtol2, float[] w, int offsetw, float[] werr, int offsetwerr, float[] wgap, int offsetwgap, int[] iblock, int offsetiblock, int[] indexw, int offsetindexw, float[] gers, int offsetgers, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slarrv");
      }

      this.slarrvK(n, vl, vu, d, offsetd, l, offsetl, pivmin, isplit, offsetisplit, m, dol, dou, minrgp, rtol1, rtol2, w, offsetw, werr, offsetwerr, wgap, offsetwgap, iblock, offsetiblock, indexw, offsetindexw, gers, offsetgers, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slarrvK(int var1, float var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, int[] var9, int var10, int var11, int var12, int var13, float var14, floatW var15, floatW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int[] var23, int var24, int[] var25, int var26, float[] var27, int var28, float[] var29, int var30, int var31, int[] var32, int var33, float[] var34, int var35, int[] var36, int var37, intW var38);

   public void slartg(float f, float g, floatW cs, floatW sn, floatW r) {
      if (debug) {
         System.err.println("slartg");
      }

      this.slartgK(f, g, cs, sn, r);
   }

   protected abstract void slartgK(float var1, float var2, floatW var3, floatW var4, floatW var5);

   public void slartv(int n, float[] x, int incx, float[] y, int incy, float[] c, float[] s, int incc) {
      if (debug) {
         System.err.println("slartv");
      }

      this.slartv(n, x, 0, incx, y, 0, incy, c, 0, s, 0, incc);
   }

   public void slartv(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] c, int offsetc, float[] s, int offsets, int incc) {
      if (debug) {
         System.err.println("slartv");
      }

      this.slartvK(n, x, offsetx, incx, y, offsety, incy, c, offsetc, s, offsets, incc);
   }

   protected abstract void slartvK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   public void slaruv(int[] iseed, int n, float[] x) {
      if (debug) {
         System.err.println("slaruv");
      }

      this.slaruv(iseed, 0, n, x, 0);
   }

   public void slaruv(int[] iseed, int offsetiseed, int n, float[] x, int offsetx) {
      if (debug) {
         System.err.println("slaruv");
      }

      this.slaruvK(iseed, offsetiseed, n, x, offsetx);
   }

   protected abstract void slaruvK(int[] var1, int var2, int var3, float[] var4, int var5);

   public void slarz(String side, int m, int n, int l, float[] v, int incv, float tau, float[] c, int Ldc, float[] work) {
      if (debug) {
         System.err.println("slarz");
      }

      this.slarz(side, m, n, l, v, 0, incv, tau, c, 0, Ldc, work, 0);
   }

   public void slarz(String side, int m, int n, int l, float[] v, int offsetv, int incv, float tau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slarz");
      }

      this.slarzK(side, m, n, l, v, offsetv, incv, tau, c, offsetc, Ldc, work, offsetwork);
   }

   protected abstract void slarzK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float var8, float[] var9, int var10, int var11, float[] var12, int var13);

   public void slarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int ldv, float[] t, int ldt, float[] c, int Ldc, float[] work, int ldwork) {
      if (debug) {
         System.err.println("slarzb");
      }

      this.slarzb(side, trans, direct, storev, m, n, k, l, v, 0, ldv, t, 0, ldt, c, 0, Ldc, work, 0, ldwork);
   }

   public void slarzb(String side, String trans, String direct, String storev, int m, int n, int k, int l, float[] v, int offsetv, int ldv, float[] t, int offsett, int ldt, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int ldwork) {
      if (debug) {
         System.err.println("slarzb");
      }

      this.slarzbK(side, trans, direct, storev, m, n, k, l, v, offsetv, ldv, t, offsett, ldt, c, offsetc, Ldc, work, offsetwork, ldwork);
   }

   protected abstract void slarzbK(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20);

   public void slarzt(String direct, String storev, int n, int k, float[] v, int ldv, float[] tau, float[] t, int ldt) {
      if (debug) {
         System.err.println("slarzt");
      }

      this.slarzt(direct, storev, n, k, v, 0, ldv, tau, 0, t, 0, ldt);
   }

   public void slarzt(String direct, String storev, int n, int k, float[] v, int offsetv, int ldv, float[] tau, int offsettau, float[] t, int offsett, int ldt) {
      if (debug) {
         System.err.println("slarzt");
      }

      this.slarztK(direct, storev, n, k, v, offsetv, ldv, tau, offsettau, t, offsett, ldt);
   }

   protected abstract void slarztK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   public void slas2(float f, float g, float h, floatW ssmin, floatW ssmax) {
      if (debug) {
         System.err.println("slas2");
      }

      this.slas2K(f, g, h, ssmin, ssmax);
   }

   protected abstract void slas2K(float var1, float var2, float var3, floatW var4, floatW var5);

   public void slascl(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("slascl");
      }

      this.slascl(type, kl, ku, cfrom, cto, m, n, a, 0, lda, info);
   }

   public void slascl(String type, int kl, int ku, float cfrom, float cto, int m, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("slascl");
      }

      this.slasclK(type, kl, ku, cfrom, cto, m, n, a, offseta, lda, info);
   }

   protected abstract void slasclK(String var1, int var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void slasd0(int n, int sqre, float[] d, float[] e, float[] u, int ldu, float[] vt, int ldvt, int smlsiz, int[] iwork, float[] work, intW info) {
      if (debug) {
         System.err.println("slasd0");
      }

      this.slasd0(n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, ldvt, smlsiz, iwork, 0, work, 0, info);
   }

   public void slasd0(int n, int sqre, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, int smlsiz, int[] iwork, int offsetiwork, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slasd0");
      }

      this.slasd0K(n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, ldvt, smlsiz, iwork, offsetiwork, work, offsetwork, info);
   }

   protected abstract void slasd0K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int var13, int[] var14, int var15, float[] var16, int var17, intW var18);

   public void slasd1(int nl, int nr, int sqre, float[] d, floatW alpha, floatW beta, float[] u, int ldu, float[] vt, int ldvt, int[] idxq, int[] iwork, float[] work, intW info) {
      if (debug) {
         System.err.println("slasd1");
      }

      this.slasd1(nl, nr, sqre, d, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, idxq, 0, iwork, 0, work, 0, info);
   }

   public void slasd1(int nl, int nr, int sqre, float[] d, int offsetd, floatW alpha, floatW beta, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, int[] idxq, int offsetidxq, int[] iwork, int offsetiwork, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slasd1");
      }

      this.slasd1K(nl, nr, sqre, d, offsetd, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, idxq, offsetidxq, iwork, offsetiwork, work, offsetwork, info);
   }

   protected abstract void slasd1K(int var1, int var2, int var3, float[] var4, int var5, floatW var6, floatW var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, int[] var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   public void slasd2(int nl, int nr, int sqre, intW k, float[] d, float[] z, float alpha, float beta, float[] u, int ldu, float[] vt, int ldvt, float[] dsigma, float[] u2, int ldu2, float[] vt2, int ldvt2, int[] idxp, int[] idx, int[] idxc, int[] idxq, int[] coltyp, intW info) {
      if (debug) {
         System.err.println("slasd2");
      }

      this.slasd2(nl, nr, sqre, k, d, 0, z, 0, alpha, beta, u, 0, ldu, vt, 0, ldvt, dsigma, 0, u2, 0, ldu2, vt2, 0, ldvt2, idxp, 0, idx, 0, idxc, 0, idxq, 0, coltyp, 0, info);
   }

   public void slasd2(int nl, int nr, int sqre, intW k, float[] d, int offsetd, float[] z, int offsetz, float alpha, float beta, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int ldvt, float[] dsigma, int offsetdsigma, float[] u2, int offsetu2, int ldu2, float[] vt2, int offsetvt2, int ldvt2, int[] idxp, int offsetidxp, int[] idx, int offsetidx, int[] idxc, int offsetidxc, int[] idxq, int offsetidxq, int[] coltyp, int offsetcoltyp, intW info) {
      if (debug) {
         System.err.println("slasd2");
      }

      this.slasd2K(nl, nr, sqre, k, d, offsetd, z, offsetz, alpha, beta, u, offsetu, ldu, vt, offsetvt, ldvt, dsigma, offsetdsigma, u2, offsetu2, ldu2, vt2, offsetvt2, ldvt2, idxp, offsetidxp, idx, offsetidx, idxc, offsetidxc, idxq, offsetidxq, coltyp, offsetcoltyp, info);
   }

   protected abstract void slasd2K(int var1, int var2, int var3, intW var4, float[] var5, int var6, float[] var7, int var8, float var9, float var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, int[] var25, int var26, int[] var27, int var28, int[] var29, int var30, int[] var31, int var32, int[] var33, int var34, intW var35);

   public void slasd3(int nl, int nr, int sqre, int k, float[] d, float[] q, int ldq, float[] dsigma, float[] u, int ldu, float[] u2, int ldu2, float[] vt, int ldvt, float[] vt2, int ldvt2, int[] idxc, int[] ctot, float[] z, intW info) {
      if (debug) {
         System.err.println("slasd3");
      }

      this.slasd3(nl, nr, sqre, k, d, 0, q, 0, ldq, dsigma, 0, u, 0, ldu, u2, 0, ldu2, vt, 0, ldvt, vt2, 0, ldvt2, idxc, 0, ctot, 0, z, 0, info);
   }

   public void slasd3(int nl, int nr, int sqre, int k, float[] d, int offsetd, float[] q, int offsetq, int ldq, float[] dsigma, int offsetdsigma, float[] u, int offsetu, int ldu, float[] u2, int offsetu2, int ldu2, float[] vt, int offsetvt, int ldvt, float[] vt2, int offsetvt2, int ldvt2, int[] idxc, int offsetidxc, int[] ctot, int offsetctot, float[] z, int offsetz, intW info) {
      if (debug) {
         System.err.println("slasd3");
      }

      this.slasd3K(nl, nr, sqre, k, d, offsetd, q, offsetq, ldq, dsigma, offsetdsigma, u, offsetu, ldu, u2, offsetu2, ldu2, vt, offsetvt, ldvt, vt2, offsetvt2, ldvt2, idxc, offsetidxc, ctot, offsetctot, z, offsetz, info);
   }

   protected abstract void slasd3K(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, float[] var28, int var29, intW var30);

   public void slasd4(int n, int i, float[] d, float[] z, float[] delta, float rho, floatW sigma, float[] work, intW info) {
      if (debug) {
         System.err.println("slasd4");
      }

      this.slasd4(n, i, d, 0, z, 0, delta, 0, rho, sigma, work, 0, info);
   }

   public void slasd4(int n, int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, floatW sigma, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slasd4");
      }

      this.slasd4K(n, i, d, offsetd, z, offsetz, delta, offsetdelta, rho, sigma, work, offsetwork, info);
   }

   protected abstract void slasd4K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float var9, floatW var10, float[] var11, int var12, intW var13);

   public void slasd5(int i, float[] d, float[] z, float[] delta, float rho, floatW dsigma, float[] work) {
      if (debug) {
         System.err.println("slasd5");
      }

      this.slasd5(i, d, 0, z, 0, delta, 0, rho, dsigma, work, 0);
   }

   public void slasd5(int i, float[] d, int offsetd, float[] z, int offsetz, float[] delta, int offsetdelta, float rho, floatW dsigma, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slasd5");
      }

      this.slasd5K(i, d, offsetd, z, offsetz, delta, offsetdelta, rho, dsigma, work, offsetwork);
   }

   protected abstract void slasd5K(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9, float[] var10, int var11);

   public void slasd6(int icompq, int nl, int nr, int sqre, float[] d, float[] vf, float[] vl, floatW alpha, floatW beta, int[] idxq, int[] perm, intW givptr, int[] givcol, int ldgcol, float[] givnum, int ldgnum, float[] poles, float[] difl, float[] difr, float[] z, intW k, floatW c, floatW s, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slasd6");
      }

      this.slasd6(icompq, nl, nr, sqre, d, 0, vf, 0, vl, 0, alpha, beta, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, poles, 0, difl, 0, difr, 0, z, 0, k, c, s, work, 0, iwork, 0, info);
   }

   public void slasd6(int icompq, int nl, int nr, int sqre, float[] d, int offsetd, float[] vf, int offsetvf, float[] vl, int offsetvl, floatW alpha, floatW beta, int[] idxq, int offsetidxq, int[] perm, int offsetperm, intW givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, float[] poles, int offsetpoles, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, intW k, floatW c, floatW s, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slasd6");
      }

      this.slasd6K(icompq, nl, nr, sqre, d, offsetd, vf, offsetvf, vl, offsetvl, alpha, beta, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, poles, offsetpoles, difl, offsetdifl, difr, offsetdifr, z, offsetz, k, c, s, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slasd6K(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, floatW var11, floatW var12, int[] var13, int var14, int[] var15, int var16, intW var17, int[] var18, int var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, intW var32, floatW var33, floatW var34, float[] var35, int var36, int[] var37, int var38, intW var39);

   public void slasd7(int icompq, int nl, int nr, int sqre, intW k, float[] d, float[] z, float[] zw, float[] vf, float[] vfw, float[] vl, float[] vlw, float alpha, float beta, float[] dsigma, int[] idx, int[] idxp, int[] idxq, int[] perm, intW givptr, int[] givcol, int ldgcol, float[] givnum, int ldgnum, floatW c, floatW s, intW info) {
      if (debug) {
         System.err.println("slasd7");
      }

      this.slasd7(icompq, nl, nr, sqre, k, d, 0, z, 0, zw, 0, vf, 0, vfw, 0, vl, 0, vlw, 0, alpha, beta, dsigma, 0, idx, 0, idxp, 0, idxq, 0, perm, 0, givptr, givcol, 0, ldgcol, givnum, 0, ldgnum, c, s, info);
   }

   public void slasd7(int icompq, int nl, int nr, int sqre, intW k, float[] d, int offsetd, float[] z, int offsetz, float[] zw, int offsetzw, float[] vf, int offsetvf, float[] vfw, int offsetvfw, float[] vl, int offsetvl, float[] vlw, int offsetvlw, float alpha, float beta, float[] dsigma, int offsetdsigma, int[] idx, int offsetidx, int[] idxp, int offsetidxp, int[] idxq, int offsetidxq, int[] perm, int offsetperm, intW givptr, int[] givcol, int offsetgivcol, int ldgcol, float[] givnum, int offsetgivnum, int ldgnum, floatW c, floatW s, intW info) {
      if (debug) {
         System.err.println("slasd7");
      }

      this.slasd7K(icompq, nl, nr, sqre, k, d, offsetd, z, offsetz, zw, offsetzw, vf, offsetvf, vfw, offsetvfw, vl, offsetvl, vlw, offsetvlw, alpha, beta, dsigma, offsetdsigma, idx, offsetidx, idxp, offsetidxp, idxq, offsetidxq, perm, offsetperm, givptr, givcol, offsetgivcol, ldgcol, givnum, offsetgivnum, ldgnum, c, s, info);
   }

   protected abstract void slasd7K(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float var20, float var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int[] var30, int var31, intW var32, int[] var33, int var34, int var35, float[] var36, int var37, int var38, floatW var39, floatW var40, intW var41);

   public void slasd8(int icompq, int k, float[] d, float[] z, float[] vf, float[] vl, float[] difl, float[] difr, int lddifr, float[] dsigma, float[] work, intW info) {
      if (debug) {
         System.err.println("slasd8");
      }

      this.slasd8(icompq, k, d, 0, z, 0, vf, 0, vl, 0, difl, 0, difr, 0, lddifr, dsigma, 0, work, 0, info);
   }

   public void slasd8(int icompq, int k, float[] d, int offsetd, float[] z, int offsetz, float[] vf, int offsetvf, float[] vl, int offsetvl, float[] difl, int offsetdifl, float[] difr, int offsetdifr, int lddifr, float[] dsigma, int offsetdsigma, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slasd8");
      }

      this.slasd8K(icompq, k, d, offsetd, z, offsetz, vf, offsetvf, vl, offsetvl, difl, offsetdifl, difr, offsetdifr, lddifr, dsigma, offsetdsigma, work, offsetwork, info);
   }

   protected abstract void slasd8K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, intW var20);

   public void slasda(int icompq, int smlsiz, int n, int sqre, float[] d, float[] e, float[] u, int ldu, float[] vt, int[] k, float[] difl, float[] difr, float[] z, float[] poles, int[] givptr, int[] givcol, int ldgcol, int[] perm, float[] givnum, float[] c, float[] s, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("slasda");
      }

      this.slasda(icompq, smlsiz, n, sqre, d, 0, e, 0, u, 0, ldu, vt, 0, k, 0, difl, 0, difr, 0, z, 0, poles, 0, givptr, 0, givcol, 0, ldgcol, perm, 0, givnum, 0, c, 0, s, 0, work, 0, iwork, 0, info);
   }

   public void slasda(int icompq, int smlsiz, int n, int sqre, float[] d, int offsetd, float[] e, int offsete, float[] u, int offsetu, int ldu, float[] vt, int offsetvt, int[] k, int offsetk, float[] difl, int offsetdifl, float[] difr, int offsetdifr, float[] z, int offsetz, float[] poles, int offsetpoles, int[] givptr, int offsetgivptr, int[] givcol, int offsetgivcol, int ldgcol, int[] perm, int offsetperm, float[] givnum, int offsetgivnum, float[] c, int offsetc, float[] s, int offsets, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("slasda");
      }

      this.slasdaK(icompq, smlsiz, n, sqre, d, offsetd, e, offsete, u, offsetu, ldu, vt, offsetvt, k, offsetk, difl, offsetdifl, difr, offsetdifr, z, offsetz, poles, offsetpoles, givptr, offsetgivptr, givcol, offsetgivcol, ldgcol, perm, offsetperm, givnum, offsetgivnum, c, offsetc, s, offsets, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void slasdaK(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int var28, int[] var29, int var30, float[] var31, int var32, float[] var33, int var34, float[] var35, int var36, float[] var37, int var38, int[] var39, int var40, intW var41);

   public void slasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, float[] e, float[] vt, int ldvt, float[] u, int ldu, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("slasdq");
      }

      this.slasdq(uplo, sqre, n, ncvt, nru, ncc, d, 0, e, 0, vt, 0, ldvt, u, 0, ldu, c, 0, Ldc, work, 0, info);
   }

   public void slasdq(String uplo, int sqre, int n, int ncvt, int nru, int ncc, float[] d, int offsetd, float[] e, int offsete, float[] vt, int offsetvt, int ldvt, float[] u, int offsetu, int ldu, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slasdq");
      }

      this.slasdqK(uplo, sqre, n, ncvt, nru, ncc, d, offsetd, e, offsete, vt, offsetvt, ldvt, u, offsetu, ldu, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void slasdqK(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, intW var22);

   public void slasdt(int n, intW lvl, intW nd, int[] inode, int[] ndiml, int[] ndimr, int msub) {
      if (debug) {
         System.err.println("slasdt");
      }

      this.slasdt(n, lvl, nd, inode, 0, ndiml, 0, ndimr, 0, msub);
   }

   public void slasdt(int n, intW lvl, intW nd, int[] inode, int offsetinode, int[] ndiml, int offsetndiml, int[] ndimr, int offsetndimr, int msub) {
      if (debug) {
         System.err.println("slasdt");
      }

      this.slasdtK(n, lvl, nd, inode, offsetinode, ndiml, offsetndiml, ndimr, offsetndimr, msub);
   }

   protected abstract void slasdtK(int var1, intW var2, intW var3, int[] var4, int var5, int[] var6, int var7, int[] var8, int var9, int var10);

   public void slaset(String uplo, int m, int n, float alpha, float beta, float[] a, int lda) {
      if (debug) {
         System.err.println("slaset");
      }

      this.slaset(uplo, m, n, alpha, beta, a, 0, lda);
   }

   public void slaset(String uplo, int m, int n, float alpha, float beta, float[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("slaset");
      }

      this.slasetK(uplo, m, n, alpha, beta, a, offseta, lda);
   }

   protected abstract void slasetK(String var1, int var2, int var3, float var4, float var5, float[] var6, int var7, int var8);

   public void slasq1(int n, float[] d, float[] e, float[] work, intW info) {
      if (debug) {
         System.err.println("slasq1");
      }

      this.slasq1(n, d, 0, e, 0, work, 0, info);
   }

   public void slasq1(int n, float[] d, int offsetd, float[] e, int offsete, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("slasq1");
      }

      this.slasq1K(n, d, offsetd, e, offsete, work, offsetwork, info);
   }

   protected abstract void slasq1K(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   public void slasq2(int n, float[] z, intW info) {
      if (debug) {
         System.err.println("slasq2");
      }

      this.slasq2(n, z, 0, info);
   }

   public void slasq2(int n, float[] z, int offsetz, intW info) {
      if (debug) {
         System.err.println("slasq2");
      }

      this.slasq2K(n, z, offsetz, info);
   }

   protected abstract void slasq2K(int var1, float[] var2, int var3, intW var4);

   public void slasq3(int i0, intW n0, float[] z, int pp, floatW dmin, floatW sigma, floatW desig, floatW qmax, intW nfail, intW iter, intW ndiv, boolean ieee) {
      if (debug) {
         System.err.println("slasq3");
      }

      this.slasq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
   }

   public void slasq3(int i0, intW n0, float[] z, int offsetz, int pp, floatW dmin, floatW sigma, floatW desig, floatW qmax, intW nfail, intW iter, intW ndiv, boolean ieee) {
      if (debug) {
         System.err.println("slasq3");
      }

      this.slasq3K(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee);
   }

   protected abstract void slasq3K(int var1, intW var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, intW var10, intW var11, intW var12, boolean var13);

   public void slasq4(int i0, int n0, float[] z, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, floatW tau, intW ttype) {
      if (debug) {
         System.err.println("slasq4");
      }

      this.slasq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
   }

   public void slasq4(int i0, int n0, float[] z, int offsetz, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, floatW tau, intW ttype) {
      if (debug) {
         System.err.println("slasq4");
      }

      this.slasq4K(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype);
   }

   protected abstract void slasq4K(int var1, int var2, float[] var3, int var4, int var5, int var6, float var7, float var8, float var9, float var10, float var11, float var12, floatW var13, intW var14);

   public void slasq5(int i0, int n0, float[] z, int pp, float tau, floatW dmin, floatW dmin1, floatW dmin2, floatW dn, floatW dnm1, floatW dnm2, boolean ieee) {
      if (debug) {
         System.err.println("slasq5");
      }

      this.slasq5(i0, n0, z, 0, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
   }

   public void slasq5(int i0, int n0, float[] z, int offsetz, int pp, float tau, floatW dmin, floatW dmin1, floatW dmin2, floatW dn, floatW dnm1, floatW dnm2, boolean ieee) {
      if (debug) {
         System.err.println("slasq5");
      }

      this.slasq5K(i0, n0, z, offsetz, pp, tau, dmin, dmin1, dmin2, dn, dnm1, dnm2, ieee);
   }

   protected abstract void slasq5K(int var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12, boolean var13);

   public void slasq6(int i0, int n0, float[] z, int pp, floatW dmin, floatW dmin1, floatW dmin2, floatW dn, floatW dnm1, floatW dnm2) {
      if (debug) {
         System.err.println("slasq6");
      }

      this.slasq6(i0, n0, z, 0, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
   }

   public void slasq6(int i0, int n0, float[] z, int offsetz, int pp, floatW dmin, floatW dmin1, floatW dmin2, floatW dn, floatW dnm1, floatW dnm2) {
      if (debug) {
         System.err.println("slasq6");
      }

      this.slasq6K(i0, n0, z, offsetz, pp, dmin, dmin1, dmin2, dn, dnm1, dnm2);
   }

   protected abstract void slasq6K(int var1, int var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11);

   public void slasr(String side, String pivot, String direct, int m, int n, float[] c, float[] s, float[] a, int lda) {
      if (debug) {
         System.err.println("slasr");
      }

      this.slasr(side, pivot, direct, m, n, c, 0, s, 0, a, 0, lda);
   }

   public void slasr(String side, String pivot, String direct, int m, int n, float[] c, int offsetc, float[] s, int offsets, float[] a, int offseta, int lda) {
      if (debug) {
         System.err.println("slasr");
      }

      this.slasrK(side, pivot, direct, m, n, c, offsetc, s, offsets, a, offseta, lda);
   }

   protected abstract void slasrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   public void slasrt(String id, int n, float[] d, intW info) {
      if (debug) {
         System.err.println("slasrt");
      }

      this.slasrt(id, n, d, 0, info);
   }

   public void slasrt(String id, int n, float[] d, int offsetd, intW info) {
      if (debug) {
         System.err.println("slasrt");
      }

      this.slasrtK(id, n, d, offsetd, info);
   }

   protected abstract void slasrtK(String var1, int var2, float[] var3, int var4, intW var5);

   public void slassq(int n, float[] x, int incx, floatW scale, floatW sumsq) {
      if (debug) {
         System.err.println("slassq");
      }

      this.slassq(n, x, 0, incx, scale, sumsq);
   }

   public void slassq(int n, float[] x, int offsetx, int incx, floatW scale, floatW sumsq) {
      if (debug) {
         System.err.println("slassq");
      }

      this.slassqK(n, x, offsetx, incx, scale, sumsq);
   }

   protected abstract void slassqK(int var1, float[] var2, int var3, int var4, floatW var5, floatW var6);

   public void slasv2(float f, float g, float h, floatW ssmin, floatW ssmax, floatW snr, floatW csr, floatW snl, floatW csl) {
      if (debug) {
         System.err.println("slasv2");
      }

      this.slasv2K(f, g, h, ssmin, ssmax, snr, csr, snl, csl);
   }

   protected abstract void slasv2K(float var1, float var2, float var3, floatW var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9);

   public void slaswp(int n, float[] a, int lda, int k1, int k2, int[] ipiv, int incx) {
      if (debug) {
         System.err.println("slaswp");
      }

      this.slaswp(n, a, 0, lda, k1, k2, ipiv, 0, incx);
   }

   public void slaswp(int n, float[] a, int offseta, int lda, int k1, int k2, int[] ipiv, int offsetipiv, int incx) {
      if (debug) {
         System.err.println("slaswp");
      }

      this.slaswpK(n, a, offseta, lda, k1, k2, ipiv, offsetipiv, incx);
   }

   protected abstract void slaswpK(int var1, float[] var2, int var3, int var4, int var5, int var6, int[] var7, int var8, int var9);

   public void slasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int ldtl, float[] tr, int ldtr, float[] b, int ldb, floatW scale, float[] x, int ldx, floatW xnorm, intW info) {
      if (debug) {
         System.err.println("slasy2");
      }

      this.slasy2(ltranl, ltranr, isgn, n1, n2, tl, 0, ldtl, tr, 0, ldtr, b, 0, ldb, scale, x, 0, ldx, xnorm, info);
   }

   public void slasy2(boolean ltranl, boolean ltranr, int isgn, int n1, int n2, float[] tl, int offsettl, int ldtl, float[] tr, int offsettr, int ldtr, float[] b, int offsetb, int ldb, floatW scale, float[] x, int offsetx, int ldx, floatW xnorm, intW info) {
      if (debug) {
         System.err.println("slasy2");
      }

      this.slasy2K(ltranl, ltranr, isgn, n1, n2, tl, offsettl, ldtl, tr, offsettr, ldtr, b, offsetb, ldb, scale, x, offsetx, ldx, xnorm, info);
   }

   protected abstract void slasy2K(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, floatW var15, float[] var16, int var17, int var18, floatW var19, intW var20);

   public void slasyf(String uplo, int n, int nb, intW kb, float[] a, int lda, int[] ipiv, float[] w, int ldw, intW info) {
      if (debug) {
         System.err.println("slasyf");
      }

      this.slasyf(uplo, n, nb, kb, a, 0, lda, ipiv, 0, w, 0, ldw, info);
   }

   public void slasyf(String uplo, int n, int nb, intW kb, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] w, int offsetw, int ldw, intW info) {
      if (debug) {
         System.err.println("slasyf");
      }

      this.slasyfK(uplo, n, nb, kb, a, offseta, lda, ipiv, offsetipiv, w, offsetw, ldw, info);
   }

   protected abstract void slasyfK(String var1, int var2, int var3, intW var4, float[] var5, int var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   public void slatbs(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int ldab, float[] x, floatW scale, float[] cnorm, intW info) {
      if (debug) {
         System.err.println("slatbs");
      }

      this.slatbs(uplo, trans, diag, normin, n, kd, ab, 0, ldab, x, 0, scale, cnorm, 0, info);
   }

   public void slatbs(String uplo, String trans, String diag, String normin, int n, int kd, float[] ab, int offsetab, int ldab, float[] x, int offsetx, floatW scale, float[] cnorm, int offsetcnorm, intW info) {
      if (debug) {
         System.err.println("slatbs");
      }

      this.slatbsK(uplo, trans, diag, normin, n, kd, ab, offsetab, ldab, x, offsetx, scale, cnorm, offsetcnorm, info);
   }

   protected abstract void slatbsK(String var1, String var2, String var3, String var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, floatW var12, float[] var13, int var14, intW var15);

   public void slatdf(int ijob, int n, float[] z, int ldz, float[] rhs, floatW rdsum, floatW rdscal, int[] ipiv, int[] jpiv) {
      if (debug) {
         System.err.println("slatdf");
      }

      this.slatdf(ijob, n, z, 0, ldz, rhs, 0, rdsum, rdscal, ipiv, 0, jpiv, 0);
   }

   public void slatdf(int ijob, int n, float[] z, int offsetz, int ldz, float[] rhs, int offsetrhs, floatW rdsum, floatW rdscal, int[] ipiv, int offsetipiv, int[] jpiv, int offsetjpiv) {
      if (debug) {
         System.err.println("slatdf");
      }

      this.slatdfK(ijob, n, z, offsetz, ldz, rhs, offsetrhs, rdsum, rdscal, ipiv, offsetipiv, jpiv, offsetjpiv);
   }

   protected abstract void slatdfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, floatW var8, floatW var9, int[] var10, int var11, int[] var12, int var13);

   public void slatps(String uplo, String trans, String diag, String normin, int n, float[] ap, float[] x, floatW scale, float[] cnorm, intW info) {
      if (debug) {
         System.err.println("slatps");
      }

      this.slatps(uplo, trans, diag, normin, n, ap, 0, x, 0, scale, cnorm, 0, info);
   }

   public void slatps(String uplo, String trans, String diag, String normin, int n, float[] ap, int offsetap, float[] x, int offsetx, floatW scale, float[] cnorm, int offsetcnorm, intW info) {
      if (debug) {
         System.err.println("slatps");
      }

      this.slatpsK(uplo, trans, diag, normin, n, ap, offsetap, x, offsetx, scale, cnorm, offsetcnorm, info);
   }

   protected abstract void slatpsK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, floatW var10, float[] var11, int var12, intW var13);

   public void slatrd(String uplo, int n, int nb, float[] a, int lda, float[] e, float[] tau, float[] w, int ldw) {
      if (debug) {
         System.err.println("slatrd");
      }

      this.slatrd(uplo, n, nb, a, 0, lda, e, 0, tau, 0, w, 0, ldw);
   }

   public void slatrd(String uplo, int n, int nb, float[] a, int offseta, int lda, float[] e, int offsete, float[] tau, int offsettau, float[] w, int offsetw, int ldw) {
      if (debug) {
         System.err.println("slatrd");
      }

      this.slatrdK(uplo, n, nb, a, offseta, lda, e, offsete, tau, offsettau, w, offsetw, ldw);
   }

   protected abstract void slatrdK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13);

   public void slatrs(String uplo, String trans, String diag, String normin, int n, float[] a, int lda, float[] x, floatW scale, float[] cnorm, intW info) {
      if (debug) {
         System.err.println("slatrs");
      }

      this.slatrs(uplo, trans, diag, normin, n, a, 0, lda, x, 0, scale, cnorm, 0, info);
   }

   public void slatrs(String uplo, String trans, String diag, String normin, int n, float[] a, int offseta, int lda, float[] x, int offsetx, floatW scale, float[] cnorm, int offsetcnorm, intW info) {
      if (debug) {
         System.err.println("slatrs");
      }

      this.slatrsK(uplo, trans, diag, normin, n, a, offseta, lda, x, offsetx, scale, cnorm, offsetcnorm, info);
   }

   protected abstract void slatrsK(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, floatW var11, float[] var12, int var13, intW var14);

   public void slatrz(int m, int n, int l, float[] a, int lda, float[] tau, float[] work) {
      if (debug) {
         System.err.println("slatrz");
      }

      this.slatrz(m, n, l, a, 0, lda, tau, 0, work, 0);
   }

   public void slatrz(int m, int n, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slatrz");
      }

      this.slatrzK(m, n, l, a, offseta, lda, tau, offsettau, work, offsetwork);
   }

   protected abstract void slatrzK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10);

   public void slatzm(String side, int m, int n, float[] v, int incv, float tau, float[] c1, float[] c2, int Ldc, float[] work) {
      if (debug) {
         System.err.println("slatzm");
      }

      this.slatzm(side, m, n, v, 0, incv, tau, c1, 0, c2, 0, Ldc, work, 0);
   }

   public void slatzm(String side, int m, int n, float[] v, int offsetv, int incv, float tau, float[] c1, int offsetc1, float[] c2, int offsetc2, int Ldc, float[] work, int offsetwork) {
      if (debug) {
         System.err.println("slatzm");
      }

      this.slatzmK(side, m, n, v, offsetv, incv, tau, c1, offsetc1, c2, offsetc2, Ldc, work, offsetwork);
   }

   protected abstract void slatzmK(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14);

   public void slauu2(String uplo, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("slauu2");
      }

      this.slauu2(uplo, n, a, 0, lda, info);
   }

   public void slauu2(String uplo, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("slauu2");
      }

      this.slauu2K(uplo, n, a, offseta, lda, info);
   }

   protected abstract void slauu2K(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   public void slauum(String uplo, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("slauum");
      }

      this.slauum(uplo, n, a, 0, lda, info);
   }

   public void slauum(String uplo, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("slauum");
      }

      this.slauumK(uplo, n, a, offseta, lda, info);
   }

   protected abstract void slauumK(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   public void slazq3(int i0, intW n0, float[] z, int pp, floatW dmin, floatW sigma, floatW desig, floatW qmax, intW nfail, intW iter, intW ndiv, boolean ieee, intW ttype, floatW dmin1, floatW dmin2, floatW dn, floatW dn1, floatW dn2, floatW tau) {
      if (debug) {
         System.err.println("slazq3");
      }

      this.slazq3(i0, n0, z, 0, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
   }

   public void slazq3(int i0, intW n0, float[] z, int offsetz, int pp, floatW dmin, floatW sigma, floatW desig, floatW qmax, intW nfail, intW iter, intW ndiv, boolean ieee, intW ttype, floatW dmin1, floatW dmin2, floatW dn, floatW dn1, floatW dn2, floatW tau) {
      if (debug) {
         System.err.println("slazq3");
      }

      this.slazq3K(i0, n0, z, offsetz, pp, dmin, sigma, desig, qmax, nfail, iter, ndiv, ieee, ttype, dmin1, dmin2, dn, dn1, dn2, tau);
   }

   protected abstract void slazq3K(int var1, intW var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, intW var10, intW var11, intW var12, boolean var13, intW var14, floatW var15, floatW var16, floatW var17, floatW var18, floatW var19, floatW var20);

   public void slazq4(int i0, int n0, float[] z, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, floatW tau, intW ttype, floatW g) {
      if (debug) {
         System.err.println("slazq4");
      }

      this.slazq4(i0, n0, z, 0, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
   }

   public void slazq4(int i0, int n0, float[] z, int offsetz, int pp, int n0in, float dmin, float dmin1, float dmin2, float dn, float dn1, float dn2, floatW tau, intW ttype, floatW g) {
      if (debug) {
         System.err.println("slazq4");
      }

      this.slazq4K(i0, n0, z, offsetz, pp, n0in, dmin, dmin1, dmin2, dn, dn1, dn2, tau, ttype, g);
   }

   protected abstract void slazq4K(int var1, int var2, float[] var3, int var4, int var5, int var6, float var7, float var8, float var9, float var10, float var11, float var12, floatW var13, intW var14, floatW var15);

   public void sopgtr(String uplo, int n, float[] ap, float[] tau, float[] q, int ldq, float[] work, intW info) {
      if (debug) {
         System.err.println("sopgtr");
      }

      this.sopgtr(uplo, n, ap, 0, tau, 0, q, 0, ldq, work, 0, info);
   }

   public void sopgtr(String uplo, int n, float[] ap, int offsetap, float[] tau, int offsettau, float[] q, int offsetq, int ldq, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sopgtr");
      }

      this.sopgtrK(uplo, n, ap, offsetap, tau, offsettau, q, offsetq, ldq, work, offsetwork, info);
   }

   protected abstract void sopgtrK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   public void sopmtr(String side, String uplo, String trans, int m, int n, float[] ap, float[] tau, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sopmtr");
      }

      this.sopmtr(side, uplo, trans, m, n, ap, 0, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void sopmtr(String side, String uplo, String trans, int m, int n, float[] ap, int offsetap, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sopmtr");
      }

      this.sopmtrK(side, uplo, trans, m, n, ap, offsetap, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sopmtrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, intW var15);

   public void sorg2l(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sorg2l");
      }

      this.sorg2l(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sorg2l(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorg2l");
      }

      this.sorg2lK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sorg2lK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   public void sorg2r(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sorg2r");
      }

      this.sorg2r(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sorg2r(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorg2r");
      }

      this.sorg2rK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sorg2rK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   public void sorgbr(String vect, int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgbr");
      }

      this.sorgbr(vect, m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorgbr(String vect, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgbr");
      }

      this.sorgbrK(vect, m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorgbrK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   public void sorghr(int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorghr");
      }

      this.sorghr(n, ilo, ihi, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorghr(int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorghr");
      }

      this.sorghrK(n, ilo, ihi, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorghrK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sorgl2(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sorgl2");
      }

      this.sorgl2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sorgl2(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorgl2");
      }

      this.sorgl2K(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sorgl2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   public void sorglq(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorglq");
      }

      this.sorglq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorglq(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorglq");
      }

      this.sorglqK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorglqK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sorgql(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgql");
      }

      this.sorgql(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorgql(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgql");
      }

      this.sorgqlK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorgqlK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sorgqr(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgqr");
      }

      this.sorgqr(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorgqr(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgqr");
      }

      this.sorgqrK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorgqrK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sorgr2(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, intW info) {
      if (debug) {
         System.err.println("sorgr2");
      }

      this.sorgr2(m, n, k, a, 0, lda, tau, 0, work, 0, info);
   }

   public void sorgr2(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorgr2");
      }

      this.sorgr2K(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, info);
   }

   protected abstract void sorgr2K(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   public void sorgrq(int m, int n, int k, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgrq");
      }

      this.sorgrq(m, n, k, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorgrq(int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgrq");
      }

      this.sorgrqK(m, n, k, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorgrqK(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void sorgtr(String uplo, int n, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgtr");
      }

      this.sorgtr(uplo, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void sorgtr(String uplo, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sorgtr");
      }

      this.sorgtrK(uplo, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void sorgtrK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sorm2l(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sorm2l");
      }

      this.sorm2l(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void sorm2l(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorm2l");
      }

      this.sorm2lK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sorm2lK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   public void sorm2r(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sorm2r");
      }

      this.sorm2r(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void sorm2r(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorm2r");
      }

      this.sorm2rK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sorm2rK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   public void sormbr(String vect, String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormbr");
      }

      this.sormbr(vect, side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormbr(String vect, String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormbr");
      }

      this.sormbrK(vect, side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormbrK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   public void sormhr(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormhr");
      }

      this.sormhr(side, trans, m, n, ilo, ihi, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormhr(String side, String trans, int m, int n, int ilo, int ihi, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormhr");
      }

      this.sormhrK(side, trans, m, n, ilo, ihi, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormhrK(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   public void sorml2(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sorml2");
      }

      this.sorml2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void sorml2(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sorml2");
      }

      this.sorml2K(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sorml2K(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   public void sormlq(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormlq");
      }

      this.sormlq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormlq(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormlq");
      }

      this.sormlqK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormlqK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sormql(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormql");
      }

      this.sormql(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormql(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormql");
      }

      this.sormqlK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormqlK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sormqr(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormqr");
      }

      this.sormqr(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormqr(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormqr");
      }

      this.sormqrK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormqrK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sormr2(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sormr2");
      }

      this.sormr2(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void sormr2(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sormr2");
      }

      this.sormr2K(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sormr2K(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   public void sormr3(String side, String trans, int m, int n, int k, int l, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, intW info) {
      if (debug) {
         System.err.println("sormr3");
      }

      this.sormr3(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, info);
   }

   public void sormr3(String side, String trans, int m, int n, int k, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sormr3");
      }

      this.sormr3K(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, info);
   }

   protected abstract void sormr3K(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   public void sormrq(String side, String trans, int m, int n, int k, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormrq");
      }

      this.sormrq(side, trans, m, n, k, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormrq(String side, String trans, int m, int n, int k, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormrq");
      }

      this.sormrqK(side, trans, m, n, k, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormrqK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void sormrz(String side, String trans, int m, int n, int k, int l, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormrz");
      }

      this.sormrz(side, trans, m, n, k, l, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormrz(String side, String trans, int m, int n, int k, int l, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormrz");
      }

      this.sormrzK(side, trans, m, n, k, l, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormrzK(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   public void sormtr(String side, String uplo, String trans, int m, int n, float[] a, int lda, float[] tau, float[] c, int Ldc, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("sormtr");
      }

      this.sormtr(side, uplo, trans, m, n, a, 0, lda, tau, 0, c, 0, Ldc, work, 0, lwork, info);
   }

   public void sormtr(String side, String uplo, String trans, int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] c, int offsetc, int Ldc, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("sormtr");
      }

      this.sormtrK(side, uplo, trans, m, n, a, offseta, lda, tau, offsettau, c, offsetc, Ldc, work, offsetwork, lwork, info);
   }

   protected abstract void sormtrK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   public void spbcon(String uplo, int n, int kd, float[] ab, int ldab, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("spbcon");
      }

      this.spbcon(uplo, n, kd, ab, 0, ldab, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void spbcon(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("spbcon");
      }

      this.spbconK(uplo, n, kd, ab, offsetab, ldab, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void spbconK(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   public void spbequ(String uplo, int n, int kd, float[] ab, int ldab, float[] s, floatW scond, floatW amax, intW info) {
      if (debug) {
         System.err.println("spbequ");
      }

      this.spbequ(uplo, n, kd, ab, 0, ldab, s, 0, scond, amax, info);
   }

   public void spbequ(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] s, int offsets, floatW scond, floatW amax, intW info) {
      if (debug) {
         System.err.println("spbequ");
      }

      this.spbequK(uplo, n, kd, ab, offsetab, ldab, s, offsets, scond, amax, info);
   }

   protected abstract void spbequK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, floatW var9, floatW var10, intW var11);

   public void spbrfs(String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("spbrfs");
      }

      this.spbrfs(uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void spbrfs(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("spbrfs");
      }

      this.spbrfsK(uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void spbrfsK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   public void spbstf(String uplo, int n, int kd, float[] ab, int ldab, intW info) {
      if (debug) {
         System.err.println("spbstf");
      }

      this.spbstf(uplo, n, kd, ab, 0, ldab, info);
   }

   public void spbstf(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, intW info) {
      if (debug) {
         System.err.println("spbstf");
      }

      this.spbstfK(uplo, n, kd, ab, offsetab, ldab, info);
   }

   protected abstract void spbstfK(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   public void spbsv(String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("spbsv");
      }

      this.spbsv(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
   }

   public void spbsv(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("spbsv");
      }

      this.spbsvK(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
   }

   protected abstract void spbsvK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void spbsvx(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] afb, int ldafb, StringW equed, float[] s, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("spbsvx");
      }

      this.spbsvx(fact, uplo, n, kd, nrhs, ab, 0, ldab, afb, 0, ldafb, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void spbsvx(String fact, String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] afb, int offsetafb, int ldafb, StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("spbsvx");
      }

      this.spbsvxK(fact, uplo, n, kd, nrhs, ab, offsetab, ldab, afb, offsetafb, ldafb, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void spbsvxK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, StringW var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, floatW var21, float[] var22, int var23, float[] var24, int var25, float[] var26, int var27, int[] var28, int var29, intW var30);

   public void spbtf2(String uplo, int n, int kd, float[] ab, int ldab, intW info) {
      if (debug) {
         System.err.println("spbtf2");
      }

      this.spbtf2(uplo, n, kd, ab, 0, ldab, info);
   }

   public void spbtf2(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, intW info) {
      if (debug) {
         System.err.println("spbtf2");
      }

      this.spbtf2K(uplo, n, kd, ab, offsetab, ldab, info);
   }

   protected abstract void spbtf2K(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   public void spbtrf(String uplo, int n, int kd, float[] ab, int ldab, intW info) {
      if (debug) {
         System.err.println("spbtrf");
      }

      this.spbtrf(uplo, n, kd, ab, 0, ldab, info);
   }

   public void spbtrf(String uplo, int n, int kd, float[] ab, int offsetab, int ldab, intW info) {
      if (debug) {
         System.err.println("spbtrf");
      }

      this.spbtrfK(uplo, n, kd, ab, offsetab, ldab, info);
   }

   protected abstract void spbtrfK(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   public void spbtrs(String uplo, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("spbtrs");
      }

      this.spbtrs(uplo, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
   }

   public void spbtrs(String uplo, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("spbtrs");
      }

      this.spbtrsK(uplo, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
   }

   protected abstract void spbtrsK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void spocon(String uplo, int n, float[] a, int lda, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("spocon");
      }

      this.spocon(uplo, n, a, 0, lda, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void spocon(String uplo, int n, float[] a, int offseta, int lda, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("spocon");
      }

      this.spoconK(uplo, n, a, offseta, lda, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void spoconK(String var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   public void spoequ(int n, float[] a, int lda, float[] s, floatW scond, floatW amax, intW info) {
      if (debug) {
         System.err.println("spoequ");
      }

      this.spoequ(n, a, 0, lda, s, 0, scond, amax, info);
   }

   public void spoequ(int n, float[] a, int offseta, int lda, float[] s, int offsets, floatW scond, floatW amax, intW info) {
      if (debug) {
         System.err.println("spoequ");
      }

      this.spoequK(n, a, offseta, lda, s, offsets, scond, amax, info);
   }

   protected abstract void spoequK(int var1, float[] var2, int var3, int var4, float[] var5, int var6, floatW var7, floatW var8, intW var9);

   public void sporfs(String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sporfs");
      }

      this.sporfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sporfs(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sporfs");
      }

      this.sporfsK(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sporfsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   public void sposv(String uplo, int n, int nrhs, float[] a, int lda, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sposv");
      }

      this.sposv(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
   }

   public void sposv(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sposv");
      }

      this.sposvK(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void sposvK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void sposvx(String fact, String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, StringW equed, float[] s, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sposvx");
      }

      this.sposvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sposvx(String fact, String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sposvx");
      }

      this.sposvxK(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sposvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, StringW var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, floatW var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, int[] var27, int var28, intW var29);

   public void spotf2(String uplo, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("spotf2");
      }

      this.spotf2(uplo, n, a, 0, lda, info);
   }

   public void spotf2(String uplo, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("spotf2");
      }

      this.spotf2K(uplo, n, a, offseta, lda, info);
   }

   protected abstract void spotf2K(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   public void spotrf(String uplo, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("spotrf");
      }

      this.spotrf(uplo, n, a, 0, lda, info);
   }

   public void spotrf(String uplo, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("spotrf");
      }

      this.spotrfK(uplo, n, a, offseta, lda, info);
   }

   protected abstract void spotrfK(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   public void spotri(String uplo, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("spotri");
      }

      this.spotri(uplo, n, a, 0, lda, info);
   }

   public void spotri(String uplo, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("spotri");
      }

      this.spotriK(uplo, n, a, offseta, lda, info);
   }

   protected abstract void spotriK(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   public void spotrs(String uplo, int n, int nrhs, float[] a, int lda, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("spotrs");
      }

      this.spotrs(uplo, n, nrhs, a, 0, lda, b, 0, ldb, info);
   }

   public void spotrs(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("spotrs");
      }

      this.spotrsK(uplo, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void spotrsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void sppcon(String uplo, int n, float[] ap, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sppcon");
      }

      this.sppcon(uplo, n, ap, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void sppcon(String uplo, int n, float[] ap, int offsetap, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sppcon");
      }

      this.sppconK(uplo, n, ap, offsetap, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sppconK(String var1, int var2, float[] var3, int var4, float var5, floatW var6, float[] var7, int var8, int[] var9, int var10, intW var11);

   public void sppequ(String uplo, int n, float[] ap, float[] s, floatW scond, floatW amax, intW info) {
      if (debug) {
         System.err.println("sppequ");
      }

      this.sppequ(uplo, n, ap, 0, s, 0, scond, amax, info);
   }

   public void sppequ(String uplo, int n, float[] ap, int offsetap, float[] s, int offsets, floatW scond, floatW amax, intW info) {
      if (debug) {
         System.err.println("sppequ");
      }

      this.sppequK(uplo, n, ap, offsetap, s, offsets, scond, amax, info);
   }

   protected abstract void sppequK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, floatW var7, floatW var8, intW var9);

   public void spprfs(String uplo, int n, int nrhs, float[] ap, float[] afp, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("spprfs");
      }

      this.spprfs(uplo, n, nrhs, ap, 0, afp, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void spprfs(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("spprfs");
      }

      this.spprfsK(uplo, n, nrhs, ap, offsetap, afp, offsetafp, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void spprfsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   public void sppsv(String uplo, int n, int nrhs, float[] ap, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sppsv");
      }

      this.sppsv(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
   }

   public void sppsv(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sppsv");
      }

      this.sppsvK(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
   }

   protected abstract void sppsvK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, intW var9);

   public void sppsvx(String fact, String uplo, int n, int nrhs, float[] ap, float[] afp, StringW equed, float[] s, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sppsvx");
      }

      this.sppsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, equed, s, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sppsvx(String fact, String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, StringW equed, float[] s, int offsets, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sppsvx");
      }

      this.sppsvxK(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, equed, s, offsets, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sppsvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, StringW var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, floatW var18, float[] var19, int var20, float[] var21, int var22, float[] var23, int var24, int[] var25, int var26, intW var27);

   public void spptrf(String uplo, int n, float[] ap, intW info) {
      if (debug) {
         System.err.println("spptrf");
      }

      this.spptrf(uplo, n, ap, 0, info);
   }

   public void spptrf(String uplo, int n, float[] ap, int offsetap, intW info) {
      if (debug) {
         System.err.println("spptrf");
      }

      this.spptrfK(uplo, n, ap, offsetap, info);
   }

   protected abstract void spptrfK(String var1, int var2, float[] var3, int var4, intW var5);

   public void spptri(String uplo, int n, float[] ap, intW info) {
      if (debug) {
         System.err.println("spptri");
      }

      this.spptri(uplo, n, ap, 0, info);
   }

   public void spptri(String uplo, int n, float[] ap, int offsetap, intW info) {
      if (debug) {
         System.err.println("spptri");
      }

      this.spptriK(uplo, n, ap, offsetap, info);
   }

   protected abstract void spptriK(String var1, int var2, float[] var3, int var4, intW var5);

   public void spptrs(String uplo, int n, int nrhs, float[] ap, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("spptrs");
      }

      this.spptrs(uplo, n, nrhs, ap, 0, b, 0, ldb, info);
   }

   public void spptrs(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("spptrs");
      }

      this.spptrsK(uplo, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
   }

   protected abstract void spptrsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, intW var9);

   public void sptcon(int n, float[] d, float[] e, float anorm, floatW rcond, float[] work, intW info) {
      if (debug) {
         System.err.println("sptcon");
      }

      this.sptcon(n, d, 0, e, 0, anorm, rcond, work, 0, info);
   }

   public void sptcon(int n, float[] d, int offsetd, float[] e, int offsete, float anorm, floatW rcond, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sptcon");
      }

      this.sptconK(n, d, offsetd, e, offsete, anorm, rcond, work, offsetwork, info);
   }

   protected abstract void sptconK(int var1, float[] var2, int var3, float[] var4, int var5, float var6, floatW var7, float[] var8, int var9, intW var10);

   public void spteqr(String compz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("spteqr");
      }

      this.spteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
   }

   public void spteqr(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("spteqr");
      }

      this.spteqrK(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void spteqrK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   public void sptrfs(int n, int nrhs, float[] d, float[] e, float[] df, float[] ef, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, intW info) {
      if (debug) {
         System.err.println("sptrfs");
      }

      this.sptrfs(n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, info);
   }

   public void sptrfs(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] df, int offsetdf, float[] ef, int offsetef, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sptrfs");
      }

      this.sptrfsK(n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
   }

   protected abstract void sptrfsK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, intW var23);

   public void sptsv(int n, int nrhs, float[] d, float[] e, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sptsv");
      }

      this.sptsv(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
   }

   public void sptsv(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sptsv");
      }

      this.sptsvK(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
   }

   protected abstract void sptsvK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void sptsvx(String fact, int n, int nrhs, float[] d, float[] e, float[] df, float[] ef, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, intW info) {
      if (debug) {
         System.err.println("sptsvx");
      }

      this.sptsvx(fact, n, nrhs, d, 0, e, 0, df, 0, ef, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, info);
   }

   public void sptsvx(String fact, int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] df, int offsetdf, float[] ef, int offsetef, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sptsvx");
      }

      this.sptsvxK(fact, n, nrhs, d, offsetd, e, offsete, df, offsetdf, ef, offsetef, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, info);
   }

   protected abstract void sptsvxK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, floatW var18, float[] var19, int var20, float[] var21, int var22, float[] var23, int var24, intW var25);

   public void spttrf(int n, float[] d, float[] e, intW info) {
      if (debug) {
         System.err.println("spttrf");
      }

      this.spttrf(n, d, 0, e, 0, info);
   }

   public void spttrf(int n, float[] d, int offsetd, float[] e, int offsete, intW info) {
      if (debug) {
         System.err.println("spttrf");
      }

      this.spttrfK(n, d, offsetd, e, offsete, info);
   }

   protected abstract void spttrfK(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   public void spttrs(int n, int nrhs, float[] d, float[] e, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("spttrs");
      }

      this.spttrs(n, nrhs, d, 0, e, 0, b, 0, ldb, info);
   }

   public void spttrs(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("spttrs");
      }

      this.spttrsK(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb, info);
   }

   protected abstract void spttrsK(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void sptts2(int n, int nrhs, float[] d, float[] e, float[] b, int ldb) {
      if (debug) {
         System.err.println("sptts2");
      }

      this.sptts2(n, nrhs, d, 0, e, 0, b, 0, ldb);
   }

   public void sptts2(int n, int nrhs, float[] d, int offsetd, float[] e, int offsete, float[] b, int offsetb, int ldb) {
      if (debug) {
         System.err.println("sptts2");
      }

      this.sptts2K(n, nrhs, d, offsetd, e, offsete, b, offsetb, ldb);
   }

   protected abstract void sptts2K(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   public void srscl(int n, float sa, float[] sx, int incx) {
      if (debug) {
         System.err.println("srscl");
      }

      this.srscl(n, sa, sx, 0, incx);
   }

   public void srscl(int n, float sa, float[] sx, int offsetsx, int incx) {
      if (debug) {
         System.err.println("srscl");
      }

      this.srsclK(n, sa, sx, offsetsx, incx);
   }

   protected abstract void srsclK(int var1, float var2, float[] var3, int var4, int var5);

   public void ssbev(String jobz, String uplo, int n, int kd, float[] ab, int ldab, float[] w, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("ssbev");
      }

      this.ssbev(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, info);
   }

   public void ssbev(String jobz, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssbev");
      }

      this.ssbevK(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void ssbevK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, intW var15);

   public void ssbevd(String jobz, String uplo, int n, int kd, float[] ab, int ldab, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssbevd");
      }

      this.ssbevd(jobz, uplo, n, kd, ab, 0, ldab, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void ssbevd(String jobz, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssbevd");
      }

      this.ssbevdK(jobz, uplo, n, kd, ab, offsetab, ldab, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void ssbevdK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   public void ssbevx(String jobz, String range, String uplo, int n, int kd, float[] ab, int ldab, float[] q, int ldq, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("ssbevx");
      }

      this.ssbevx(jobz, range, uplo, n, kd, ab, 0, ldab, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void ssbevx(String jobz, String range, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] q, int offsetq, int ldq, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("ssbevx");
      }

      this.ssbevxK(jobz, range, uplo, n, kd, ab, offsetab, ldab, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void ssbevxK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float var13, int var14, int var15, float var16, intW var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int[] var25, int var26, int[] var27, int var28, intW var29);

   public void ssbgst(String vect, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] x, int ldx, float[] work, intW info) {
      if (debug) {
         System.err.println("ssbgst");
      }

      this.ssbgst(vect, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, x, 0, ldx, work, 0, info);
   }

   public void ssbgst(String vect, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] x, int offsetx, int ldx, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssbgst");
      }

      this.ssbgstK(vect, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, x, offsetx, ldx, work, offsetwork, info);
   }

   protected abstract void ssbgstK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   public void ssbgv(String jobz, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] w, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("ssbgv");
      }

      this.ssbgv(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, info);
   }

   public void ssbgv(String jobz, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssbgv");
      }

      this.ssbgvK(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void ssbgvK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, intW var19);

   public void ssbgvd(String jobz, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssbgvd");
      }

      this.ssbgvd(jobz, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void ssbgvd(String jobz, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssbgvd");
      }

      this.ssbgvdK(jobz, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void ssbgvdK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, int[] var20, int var21, int var22, intW var23);

   public void ssbgvx(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int ldab, float[] bb, int ldbb, float[] q, int ldq, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("ssbgvx");
      }

      this.ssbgvx(jobz, range, uplo, n, ka, kb, ab, 0, ldab, bb, 0, ldbb, q, 0, ldq, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void ssbgvx(String jobz, String range, String uplo, int n, int ka, int kb, float[] ab, int offsetab, int ldab, float[] bb, int offsetbb, int ldbb, float[] q, int offsetq, int ldq, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("ssbgvx");
      }

      this.ssbgvxK(jobz, range, uplo, n, ka, kb, ab, offsetab, ldab, bb, offsetbb, ldbb, q, offsetq, ldq, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void ssbgvxK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float var16, float var17, int var18, int var19, float var20, intW var21, float[] var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int[] var29, int var30, int[] var31, int var32, intW var33);

   public void ssbtrd(String vect, String uplo, int n, int kd, float[] ab, int ldab, float[] d, float[] e, float[] q, int ldq, float[] work, intW info) {
      if (debug) {
         System.err.println("ssbtrd");
      }

      this.ssbtrd(vect, uplo, n, kd, ab, 0, ldab, d, 0, e, 0, q, 0, ldq, work, 0, info);
   }

   public void ssbtrd(String vect, String uplo, int n, int kd, float[] ab, int offsetab, int ldab, float[] d, int offsetd, float[] e, int offsete, float[] q, int offsetq, int ldq, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssbtrd");
      }

      this.ssbtrdK(vect, uplo, n, kd, ab, offsetab, ldab, d, offsetd, e, offsete, q, offsetq, ldq, work, offsetwork, info);
   }

   protected abstract void ssbtrdK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   public void sspcon(String uplo, int n, float[] ap, int[] ipiv, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sspcon");
      }

      this.sspcon(uplo, n, ap, 0, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void sspcon(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sspcon");
      }

      this.sspconK(uplo, n, ap, offsetap, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sspconK(String var1, int var2, float[] var3, int var4, int[] var5, int var6, float var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   public void sspev(String jobz, String uplo, int n, float[] ap, float[] w, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("sspev");
      }

      this.sspev(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, info);
   }

   public void sspev(String jobz, String uplo, int n, float[] ap, int offsetap, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sspev");
      }

      this.sspevK(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void sspevK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, intW var13);

   public void sspevd(String jobz, String uplo, int n, float[] ap, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sspevd");
      }

      this.sspevd(jobz, uplo, n, ap, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sspevd(String jobz, String uplo, int n, float[] ap, int offsetap, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sspevd");
      }

      this.sspevdK(jobz, uplo, n, ap, offsetap, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sspevdK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, int[] var14, int var15, int var16, intW var17);

   public void sspevx(String jobz, String range, String uplo, int n, float[] ap, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("sspevx");
      }

      this.sspevx(jobz, range, uplo, n, ap, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void sspevx(String jobz, String range, String uplo, int n, float[] ap, int offsetap, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("sspevx");
      }

      this.sspevxK(jobz, range, uplo, n, ap, offsetap, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void sspevxK(String var1, String var2, String var3, int var4, float[] var5, int var6, float var7, float var8, int var9, int var10, float var11, intW var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int[] var20, int var21, int[] var22, int var23, intW var24);

   public void sspgst(int itype, String uplo, int n, float[] ap, float[] bp, intW info) {
      if (debug) {
         System.err.println("sspgst");
      }

      this.sspgst(itype, uplo, n, ap, 0, bp, 0, info);
   }

   public void sspgst(int itype, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, intW info) {
      if (debug) {
         System.err.println("sspgst");
      }

      this.sspgstK(itype, uplo, n, ap, offsetap, bp, offsetbp, info);
   }

   protected abstract void sspgstK(int var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   public void sspgv(int itype, String jobz, String uplo, int n, float[] ap, float[] bp, float[] w, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("sspgv");
      }

      this.sspgv(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, info);
   }

   public void sspgv(int itype, String jobz, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sspgv");
      }

      this.sspgvK(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void sspgvK(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   public void sspgvd(int itype, String jobz, String uplo, int n, float[] ap, float[] bp, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sspgvd");
      }

      this.sspgvd(itype, jobz, uplo, n, ap, 0, bp, 0, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sspgvd(int itype, String jobz, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sspgvd");
      }

      this.sspgvdK(itype, jobz, uplo, n, ap, offsetap, bp, offsetbp, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sspgvdK(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, int[] var17, int var18, int var19, intW var20);

   public void sspgvx(int itype, String jobz, String range, String uplo, int n, float[] ap, float[] bp, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("sspgvx");
      }

      this.sspgvx(itype, jobz, range, uplo, n, ap, 0, bp, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void sspgvx(int itype, String jobz, String range, String uplo, int n, float[] ap, int offsetap, float[] bp, int offsetbp, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("sspgvx");
      }

      this.sspgvxK(itype, jobz, range, uplo, n, ap, offsetap, bp, offsetbp, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void sspgvxK(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, int var12, int var13, float var14, intW var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int[] var23, int var24, int[] var25, int var26, intW var27);

   public void ssprfs(String uplo, int n, int nrhs, float[] ap, float[] afp, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("ssprfs");
      }

      this.ssprfs(uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void ssprfs(String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("ssprfs");
      }

      this.ssprfsK(uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void ssprfsK(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   public void sspsv(String uplo, int n, int nrhs, float[] ap, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("sspsv");
      }

      this.sspsv(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
   }

   public void sspsv(String uplo, int n, int nrhs, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("sspsv");
      }

      this.sspsvK(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void sspsvK(String var1, int var2, int var3, float[] var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sspsvx(String fact, String uplo, int n, int nrhs, float[] ap, float[] afp, int[] ipiv, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sspsvx");
      }

      this.sspsvx(fact, uplo, n, nrhs, ap, 0, afp, 0, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void sspsvx(String fact, String uplo, int n, int nrhs, float[] ap, int offsetap, float[] afp, int offsetafp, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sspsvx");
      }

      this.sspsvxK(fact, uplo, n, nrhs, ap, offsetap, afp, offsetafp, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sspsvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, floatW var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   public void ssptrd(String uplo, int n, float[] ap, float[] d, float[] e, float[] tau, intW info) {
      if (debug) {
         System.err.println("ssptrd");
      }

      this.ssptrd(uplo, n, ap, 0, d, 0, e, 0, tau, 0, info);
   }

   public void ssptrd(String uplo, int n, float[] ap, int offsetap, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, intW info) {
      if (debug) {
         System.err.println("ssptrd");
      }

      this.ssptrdK(uplo, n, ap, offsetap, d, offsetd, e, offsete, tau, offsettau, info);
   }

   protected abstract void ssptrdK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   public void ssptrf(String uplo, int n, float[] ap, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("ssptrf");
      }

      this.ssptrf(uplo, n, ap, 0, ipiv, 0, info);
   }

   public void ssptrf(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("ssptrf");
      }

      this.ssptrfK(uplo, n, ap, offsetap, ipiv, offsetipiv, info);
   }

   protected abstract void ssptrfK(String var1, int var2, float[] var3, int var4, int[] var5, int var6, intW var7);

   public void ssptri(String uplo, int n, float[] ap, int[] ipiv, float[] work, intW info) {
      if (debug) {
         System.err.println("ssptri");
      }

      this.ssptri(uplo, n, ap, 0, ipiv, 0, work, 0, info);
   }

   public void ssptri(String uplo, int n, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssptri");
      }

      this.ssptriK(uplo, n, ap, offsetap, ipiv, offsetipiv, work, offsetwork, info);
   }

   protected abstract void ssptriK(String var1, int var2, float[] var3, int var4, int[] var5, int var6, float[] var7, int var8, intW var9);

   public void ssptrs(String uplo, int n, int nrhs, float[] ap, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("ssptrs");
      }

      this.ssptrs(uplo, n, nrhs, ap, 0, ipiv, 0, b, 0, ldb, info);
   }

   public void ssptrs(String uplo, int n, int nrhs, float[] ap, int offsetap, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("ssptrs");
      }

      this.ssptrsK(uplo, n, nrhs, ap, offsetap, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void ssptrsK(String var1, int var2, int var3, float[] var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void sstebz(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, float[] e, intW m, intW nsplit, float[] w, int[] iblock, int[] isplit, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("sstebz");
      }

      this.sstebz(range, order, n, vl, vu, il, iu, abstol, d, 0, e, 0, m, nsplit, w, 0, iblock, 0, isplit, 0, work, 0, iwork, 0, info);
   }

   public void sstebz(String range, String order, int n, float vl, float vu, int il, int iu, float abstol, float[] d, int offsetd, float[] e, int offsete, intW m, intW nsplit, float[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("sstebz");
      }

      this.sstebzK(range, order, n, vl, vu, il, iu, abstol, d, offsetd, e, offsete, m, nsplit, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void sstebzK(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float var8, float[] var9, int var10, float[] var11, int var12, intW var13, intW var14, float[] var15, int var16, int[] var17, int var18, int[] var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   public void sstedc(String compz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstedc");
      }

      this.sstedc(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sstedc(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstedc");
      }

      this.sstedcK(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sstedcK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   public void sstegr(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, int[] isuppz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstegr");
      }

      this.sstegr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sstegr(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstegr");
      }

      this.sstegrK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sstegrK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   public void sstein(int n, float[] d, float[] e, int m, float[] w, int[] iblock, int[] isplit, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("sstein");
      }

      this.sstein(n, d, 0, e, 0, m, w, 0, iblock, 0, isplit, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void sstein(int n, float[] d, int offsetd, float[] e, int offsete, int m, float[] w, int offsetw, int[] iblock, int offsetiblock, int[] isplit, int offsetisplit, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("sstein");
      }

      this.ssteinK(n, d, offsetd, e, offsete, m, w, offsetw, iblock, offsetiblock, isplit, offsetisplit, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void ssteinK(int var1, float[] var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int[] var9, int var10, int[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int[] var18, int var19, int[] var20, int var21, intW var22);

   public void sstemr(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, intW m, float[] w, float[] z, int ldz, int nzc, int[] isuppz, booleanW tryrac, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstemr");
      }

      this.sstemr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, m, w, 0, z, 0, ldz, nzc, isuppz, 0, tryrac, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sstemr(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int nzc, int[] isuppz, int offsetisuppz, booleanW tryrac, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstemr");
      }

      this.sstemrK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, m, w, offsetw, z, offsetz, ldz, nzc, isuppz, offsetisuppz, tryrac, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sstemrK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, int var17, int var18, int[] var19, int var20, booleanW var21, float[] var22, int var23, int var24, int[] var25, int var26, int var27, intW var28);

   public void ssteqr(String compz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("ssteqr");
      }

      this.ssteqr(compz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
   }

   public void ssteqr(String compz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssteqr");
      }

      this.ssteqrK(compz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void ssteqrK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   public void ssterf(int n, float[] d, float[] e, intW info) {
      if (debug) {
         System.err.println("ssterf");
      }

      this.ssterf(n, d, 0, e, 0, info);
   }

   public void ssterf(int n, float[] d, int offsetd, float[] e, int offsete, intW info) {
      if (debug) {
         System.err.println("ssterf");
      }

      this.ssterfK(n, d, offsetd, e, offsete, info);
   }

   protected abstract void ssterfK(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   public void sstev(String jobz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, intW info) {
      if (debug) {
         System.err.println("sstev");
      }

      this.sstev(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, info);
   }

   public void sstev(String jobz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("sstev");
      }

      this.sstevK(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, info);
   }

   protected abstract void sstevK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   public void sstevd(String jobz, int n, float[] d, float[] e, float[] z, int ldz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstevd");
      }

      this.sstevd(jobz, n, d, 0, e, 0, z, 0, ldz, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sstevd(String jobz, int n, float[] d, int offsetd, float[] e, int offsete, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstevd");
      }

      this.sstevdK(jobz, n, d, offsetd, e, offsete, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sstevdK(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   public void sstevr(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, int[] isuppz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstevr");
      }

      this.sstevr(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void sstevr(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("sstevr");
      }

      this.sstevrK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void sstevrK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   public void sstevx(String jobz, String range, int n, float[] d, float[] e, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("sstevx");
      }

      this.sstevx(jobz, range, n, d, 0, e, 0, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, iwork, 0, ifail, 0, info);
   }

   public void sstevx(String jobz, String range, int n, float[] d, int offsetd, float[] e, int offsete, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("sstevx");
      }

      this.sstevxK(jobz, range, n, d, offsetd, e, offsete, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void sstevxK(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int[] var21, int var22, int[] var23, int var24, intW var25);

   public void ssycon(String uplo, int n, float[] a, int lda, int[] ipiv, float anorm, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("ssycon");
      }

      this.ssycon(uplo, n, a, 0, lda, ipiv, 0, anorm, rcond, work, 0, iwork, 0, info);
   }

   public void ssycon(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float anorm, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("ssycon");
      }

      this.ssyconK(uplo, n, a, offseta, lda, ipiv, offsetipiv, anorm, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void ssyconK(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float var8, floatW var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   public void ssyev(String jobz, String uplo, int n, float[] a, int lda, float[] w, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("ssyev");
      }

      this.ssyev(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, info);
   }

   public void ssyev(String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] w, int offsetw, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("ssyev");
      }

      this.ssyevK(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, info);
   }

   protected abstract void ssyevK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void ssyevd(String jobz, String uplo, int n, float[] a, int lda, float[] w, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssyevd");
      }

      this.ssyevd(jobz, uplo, n, a, 0, lda, w, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void ssyevd(String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] w, int offsetw, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssyevd");
      }

      this.ssyevdK(jobz, uplo, n, a, offseta, lda, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void ssyevdK(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, int var14, intW var15);

   public void ssyevr(String jobz, String range, String uplo, int n, float[] a, int lda, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, int[] isuppz, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssyevr");
      }

      this.ssyevr(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, isuppz, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void ssyevr(String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, int[] isuppz, int offsetisuppz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssyevr");
      }

      this.ssyevrK(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, isuppz, offsetisuppz, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void ssyevrK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   public void ssyevx(String jobz, String range, String uplo, int n, float[] a, int lda, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("ssyevx");
      }

      this.ssyevx(jobz, range, uplo, n, a, 0, lda, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
   }

   public void ssyevx(String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("ssyevx");
      }

      this.ssyevxK(jobz, range, uplo, n, a, offseta, lda, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void ssyevxK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, int[] var22, int var23, int[] var24, int var25, intW var26);

   public void ssygs2(int itype, String uplo, int n, float[] a, int lda, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("ssygs2");
      }

      this.ssygs2(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
   }

   public void ssygs2(int itype, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("ssygs2");
      }

      this.ssygs2K(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void ssygs2K(int var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void ssygst(int itype, String uplo, int n, float[] a, int lda, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("ssygst");
      }

      this.ssygst(itype, uplo, n, a, 0, lda, b, 0, ldb, info);
   }

   public void ssygst(int itype, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("ssygst");
      }

      this.ssygstK(itype, uplo, n, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void ssygstK(int var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   public void ssygv(int itype, String jobz, String uplo, int n, float[] a, int lda, float[] b, int ldb, float[] w, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("ssygv");
      }

      this.ssygv(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, info);
   }

   public void ssygv(int itype, String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] w, int offsetw, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("ssygv");
      }

      this.ssygvK(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, info);
   }

   protected abstract void ssygvK(int var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, intW var16);

   public void ssygvd(int itype, String jobz, String uplo, int n, float[] a, int lda, float[] b, int ldb, float[] w, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssygvd");
      }

      this.ssygvd(itype, jobz, uplo, n, a, 0, lda, b, 0, ldb, w, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void ssygvd(int itype, String jobz, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] w, int offsetw, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("ssygvd");
      }

      this.ssygvdK(itype, jobz, uplo, n, a, offseta, lda, b, offsetb, ldb, w, offsetw, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void ssygvdK(int var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   public void ssygvx(int itype, String jobz, String range, String uplo, int n, float[] a, int lda, float[] b, int ldb, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, float[] z, int ldz, float[] work, int lwork, int[] iwork, int[] ifail, intW info) {
      if (debug) {
         System.err.println("ssygvx");
      }

      this.ssygvx(itype, jobz, range, uplo, n, a, 0, lda, b, 0, ldb, vl, vu, il, iu, abstol, m, w, 0, z, 0, ldz, work, 0, lwork, iwork, 0, ifail, 0, info);
   }

   public void ssygvx(int itype, String jobz, String range, String uplo, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float vl, float vu, int il, int iu, float abstol, intW m, float[] w, int offsetw, float[] z, int offsetz, int ldz, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int[] ifail, int offsetifail, intW info) {
      if (debug) {
         System.err.println("ssygvx");
      }

      this.ssygvxK(itype, jobz, range, uplo, n, a, offseta, lda, b, offsetb, ldb, vl, vu, il, iu, abstol, m, w, offsetw, z, offsetz, ldz, work, offsetwork, lwork, iwork, offsetiwork, ifail, offsetifail, info);
   }

   protected abstract void ssygvxK(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float var13, int var14, int var15, float var16, intW var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int[] var26, int var27, int[] var28, int var29, intW var30);

   public void ssyrfs(String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("ssyrfs");
      }

      this.ssyrfs(uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void ssyrfs(String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("ssyrfs");
      }

      this.ssyrfsK(uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void ssyrfsK(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   public void ssysv(String uplo, int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("ssysv");
      }

      this.ssysv(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, work, 0, lwork, info);
   }

   public void ssysv(String uplo, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("ssysv");
      }

      this.ssysvK(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, work, offsetwork, lwork, info);
   }

   protected abstract void ssysvK(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, intW var15);

   public void ssysvx(String fact, String uplo, int n, int nrhs, float[] a, int lda, float[] af, int ldaf, int[] ipiv, float[] b, int ldb, float[] x, int ldx, floatW rcond, float[] ferr, float[] berr, float[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("ssysvx");
      }

      this.ssysvx(fact, uplo, n, nrhs, a, 0, lda, af, 0, ldaf, ipiv, 0, b, 0, ldb, x, 0, ldx, rcond, ferr, 0, berr, 0, work, 0, lwork, iwork, 0, info);
   }

   public void ssysvx(String fact, String uplo, int n, int nrhs, float[] a, int offseta, int lda, float[] af, int offsetaf, int ldaf, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, floatW rcond, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("ssysvx");
      }

      this.ssysvxK(fact, uplo, n, nrhs, a, offseta, lda, af, offsetaf, ldaf, ipiv, offsetipiv, b, offsetb, ldb, x, offsetx, ldx, rcond, ferr, offsetferr, berr, offsetberr, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void ssysvxK(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, int[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, floatW var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   public void ssytd2(String uplo, int n, float[] a, int lda, float[] d, float[] e, float[] tau, intW info) {
      if (debug) {
         System.err.println("ssytd2");
      }

      this.ssytd2(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, info);
   }

   public void ssytd2(String uplo, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, intW info) {
      if (debug) {
         System.err.println("ssytd2");
      }

      this.ssytd2K(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, info);
   }

   protected abstract void ssytd2K(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   public void ssytf2(String uplo, int n, float[] a, int lda, int[] ipiv, intW info) {
      if (debug) {
         System.err.println("ssytf2");
      }

      this.ssytf2(uplo, n, a, 0, lda, ipiv, 0, info);
   }

   public void ssytf2(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, intW info) {
      if (debug) {
         System.err.println("ssytf2");
      }

      this.ssytf2K(uplo, n, a, offseta, lda, ipiv, offsetipiv, info);
   }

   protected abstract void ssytf2K(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   public void ssytrd(String uplo, int n, float[] a, int lda, float[] d, float[] e, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("ssytrd");
      }

      this.ssytrd(uplo, n, a, 0, lda, d, 0, e, 0, tau, 0, work, 0, lwork, info);
   }

   public void ssytrd(String uplo, int n, float[] a, int offseta, int lda, float[] d, int offsetd, float[] e, int offsete, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("ssytrd");
      }

      this.ssytrdK(uplo, n, a, offseta, lda, d, offsetd, e, offsete, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void ssytrdK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, intW var15);

   public void ssytrf(String uplo, int n, float[] a, int lda, int[] ipiv, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("ssytrf");
      }

      this.ssytrf(uplo, n, a, 0, lda, ipiv, 0, work, 0, lwork, info);
   }

   public void ssytrf(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("ssytrf");
      }

      this.ssytrfK(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, lwork, info);
   }

   protected abstract void ssytrfK(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void ssytri(String uplo, int n, float[] a, int lda, int[] ipiv, float[] work, intW info) {
      if (debug) {
         System.err.println("ssytri");
      }

      this.ssytri(uplo, n, a, 0, lda, ipiv, 0, work, 0, info);
   }

   public void ssytri(String uplo, int n, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("ssytri");
      }

      this.ssytriK(uplo, n, a, offseta, lda, ipiv, offsetipiv, work, offsetwork, info);
   }

   protected abstract void ssytriK(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, intW var10);

   public void ssytrs(String uplo, int n, int nrhs, float[] a, int lda, int[] ipiv, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("ssytrs");
      }

      this.ssytrs(uplo, n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, info);
   }

   public void ssytrs(String uplo, int n, int nrhs, float[] a, int offseta, int lda, int[] ipiv, int offsetipiv, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("ssytrs");
      }

      this.ssytrsK(uplo, n, nrhs, a, offseta, lda, ipiv, offsetipiv, b, offsetb, ldb, info);
   }

   protected abstract void ssytrsK(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void stbcon(String norm, String uplo, String diag, int n, int kd, float[] ab, int ldab, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("stbcon");
      }

      this.stbcon(norm, uplo, diag, n, kd, ab, 0, ldab, rcond, work, 0, iwork, 0, info);
   }

   public void stbcon(String norm, String uplo, String diag, int n, int kd, float[] ab, int offsetab, int ldab, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("stbcon");
      }

      this.stbconK(norm, uplo, diag, n, kd, ab, offsetab, ldab, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void stbconK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, floatW var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   public void stbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("stbrfs");
      }

      this.stbrfs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void stbrfs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("stbrfs");
      }

      this.stbrfsK(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void stbrfsK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   public void stbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int ldab, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("stbtrs");
      }

      this.stbtrs(uplo, trans, diag, n, kd, nrhs, ab, 0, ldab, b, 0, ldb, info);
   }

   public void stbtrs(String uplo, String trans, String diag, int n, int kd, int nrhs, float[] ab, int offsetab, int ldab, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("stbtrs");
      }

      this.stbtrsK(uplo, trans, diag, n, kd, nrhs, ab, offsetab, ldab, b, offsetb, ldb, info);
   }

   protected abstract void stbtrsK(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, intW var13);

   public void stgevc(String side, String howmny, boolean[] select, int n, float[] s, int lds, float[] p, int ldp, float[] vl, int ldvl, float[] vr, int ldvr, int mm, intW m, float[] work, intW info) {
      if (debug) {
         System.err.println("stgevc");
      }

      this.stgevc(side, howmny, select, 0, n, s, 0, lds, p, 0, ldp, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
   }

   public void stgevc(String side, String howmny, boolean[] select, int offsetselect, int n, float[] s, int offsets, int lds, float[] p, int offsetp, int ldp, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, intW m, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("stgevc");
      }

      this.stgevcK(side, howmny, select, offsetselect, n, s, offsets, lds, p, offsetp, ldp, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
   }

   protected abstract void stgevcK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, int var18, intW var19, float[] var20, int var21, intW var22);

   public void stgex2(boolean wantq, boolean wantz, int n, float[] a, int lda, float[] b, int ldb, float[] q, int ldq, float[] z, int ldz, int j1, int n1, int n2, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("stgex2");
      }

      this.stgex2(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, j1, n1, n2, work, 0, lwork, info);
   }

   public void stgex2(boolean wantq, boolean wantz, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, int j1, int n1, int n2, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("stgex2");
      }

      this.stgex2K(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, j1, n1, n2, work, offsetwork, lwork, info);
   }

   protected abstract void stgex2K(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int var16, int var17, int var18, float[] var19, int var20, int var21, intW var22);

   public void stgexc(boolean wantq, boolean wantz, int n, float[] a, int lda, float[] b, int ldb, float[] q, int ldq, float[] z, int ldz, intW ifst, intW ilst, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("stgexc");
      }

      this.stgexc(wantq, wantz, n, a, 0, lda, b, 0, ldb, q, 0, ldq, z, 0, ldz, ifst, ilst, work, 0, lwork, info);
   }

   public void stgexc(boolean wantq, boolean wantz, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, intW ifst, intW ilst, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("stgexc");
      }

      this.stgexcK(wantq, wantz, n, a, offseta, lda, b, offsetb, ldb, q, offsetq, ldq, z, offsetz, ldz, ifst, ilst, work, offsetwork, lwork, info);
   }

   protected abstract void stgexcK(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, intW var16, intW var17, float[] var18, int var19, int var20, intW var21);

   public void stgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int n, float[] a, int lda, float[] b, int ldb, float[] alphar, float[] alphai, float[] beta, float[] q, int ldq, float[] z, int ldz, intW m, floatW pl, floatW pr, float[] dif, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("stgsen");
      }

      this.stgsen(ijob, wantq, wantz, select, 0, n, a, 0, lda, b, 0, ldb, alphar, 0, alphai, 0, beta, 0, q, 0, ldq, z, 0, ldz, m, pl, pr, dif, 0, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void stgsen(int ijob, boolean wantq, boolean wantz, boolean[] select, int offsetselect, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] alphar, int offsetalphar, float[] alphai, int offsetalphai, float[] beta, int offsetbeta, float[] q, int offsetq, int ldq, float[] z, int offsetz, int ldz, intW m, floatW pl, floatW pr, float[] dif, int offsetdif, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("stgsen");
      }

      this.stgsenK(ijob, wantq, wantz, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, alphar, offsetalphar, alphai, offsetalphai, beta, offsetbeta, q, offsetq, ldq, z, offsetz, ldz, m, pl, pr, dif, offsetdif, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void stgsenK(int var1, boolean var2, boolean var3, boolean[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25, floatW var26, floatW var27, float[] var28, int var29, float[] var30, int var31, int var32, int[] var33, int var34, int var35, intW var36);

   public void stgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int lda, float[] b, int ldb, float tola, float tolb, float[] alpha, float[] beta, float[] u, int ldu, float[] v, int ldv, float[] q, int ldq, float[] work, intW ncycle, intW info) {
      if (debug) {
         System.err.println("stgsja");
      }

      this.stgsja(jobu, jobv, jobq, m, p, n, k, l, a, 0, lda, b, 0, ldb, tola, tolb, alpha, 0, beta, 0, u, 0, ldu, v, 0, ldv, q, 0, ldq, work, 0, ncycle, info);
   }

   public void stgsja(String jobu, String jobv, String jobq, int m, int p, int n, int k, int l, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float tola, float tolb, float[] alpha, int offsetalpha, float[] beta, int offsetbeta, float[] u, int offsetu, int ldu, float[] v, int offsetv, int ldv, float[] q, int offsetq, int ldq, float[] work, int offsetwork, intW ncycle, intW info) {
      if (debug) {
         System.err.println("stgsja");
      }

      this.stgsjaK(jobu, jobv, jobq, m, p, n, k, l, a, offseta, lda, b, offsetb, ldb, tola, tolb, alpha, offsetalpha, beta, offsetbeta, u, offsetu, ldu, v, offsetv, ldv, q, offsetq, ldq, work, offsetwork, ncycle, info);
   }

   protected abstract void stgsjaK(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float var15, float var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int var29, float[] var30, int var31, intW var32, intW var33);

   public void stgsna(String job, String howmny, boolean[] select, int n, float[] a, int lda, float[] b, int ldb, float[] vl, int ldvl, float[] vr, int ldvr, float[] s, float[] dif, int mm, intW m, float[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("stgsna");
      }

      this.stgsna(job, howmny, select, 0, n, a, 0, lda, b, 0, ldb, vl, 0, ldvl, vr, 0, ldvr, s, 0, dif, 0, mm, m, work, 0, lwork, iwork, 0, info);
   }

   public void stgsna(String job, String howmny, boolean[] select, int offsetselect, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] s, int offsets, float[] dif, int offsetdif, int mm, intW m, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("stgsna");
      }

      this.stgsnaK(job, howmny, select, offsetselect, n, a, offseta, lda, b, offsetb, ldb, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, dif, offsetdif, mm, m, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void stgsnaK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, intW var23, float[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   public void stgsy2(String trans, int ijob, int m, int n, float[] a, int lda, float[] b, int ldb, float[] c, int Ldc, float[] d, int ldd, float[] e, int lde, float[] f, int ldf, floatW scale, floatW rdsum, floatW rdscal, int[] iwork, intW pq, intW info) {
      if (debug) {
         System.err.println("stgsy2");
      }

      this.stgsy2(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, rdsum, rdscal, iwork, 0, pq, info);
   }

   public void stgsy2(String trans, int ijob, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, float[] d, int offsetd, int ldd, float[] e, int offsete, int lde, float[] f, int offsetf, int ldf, floatW scale, floatW rdsum, floatW rdscal, int[] iwork, int offsetiwork, intW pq, intW info) {
      if (debug) {
         System.err.println("stgsy2");
      }

      this.stgsy2K(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, rdsum, rdscal, iwork, offsetiwork, pq, info);
   }

   protected abstract void stgsy2K(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, floatW var23, floatW var24, floatW var25, int[] var26, int var27, intW var28, intW var29);

   public void stgsyl(String trans, int ijob, int m, int n, float[] a, int lda, float[] b, int ldb, float[] c, int Ldc, float[] d, int ldd, float[] e, int lde, float[] f, int ldf, floatW scale, floatW dif, float[] work, int lwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("stgsyl");
      }

      this.stgsyl(trans, ijob, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, d, 0, ldd, e, 0, lde, f, 0, ldf, scale, dif, work, 0, lwork, iwork, 0, info);
   }

   public void stgsyl(String trans, int ijob, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, float[] d, int offsetd, int ldd, float[] e, int offsete, int lde, float[] f, int offsetf, int ldf, floatW scale, floatW dif, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("stgsyl");
      }

      this.stgsylK(trans, ijob, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, d, offsetd, ldd, e, offsete, lde, f, offsetf, ldf, scale, dif, work, offsetwork, lwork, iwork, offsetiwork, info);
   }

   protected abstract void stgsylK(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, floatW var23, floatW var24, float[] var25, int var26, int var27, int[] var28, int var29, intW var30);

   public void stpcon(String norm, String uplo, String diag, int n, float[] ap, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("stpcon");
      }

      this.stpcon(norm, uplo, diag, n, ap, 0, rcond, work, 0, iwork, 0, info);
   }

   public void stpcon(String norm, String uplo, String diag, int n, float[] ap, int offsetap, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("stpcon");
      }

      this.stpconK(norm, uplo, diag, n, ap, offsetap, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void stpconK(String var1, String var2, String var3, int var4, float[] var5, int var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   public void stprfs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("stprfs");
      }

      this.stprfs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void stprfs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("stprfs");
      }

      this.stprfsK(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void stprfsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   public void stptri(String uplo, String diag, int n, float[] ap, intW info) {
      if (debug) {
         System.err.println("stptri");
      }

      this.stptri(uplo, diag, n, ap, 0, info);
   }

   public void stptri(String uplo, String diag, int n, float[] ap, int offsetap, intW info) {
      if (debug) {
         System.err.println("stptri");
      }

      this.stptriK(uplo, diag, n, ap, offsetap, info);
   }

   protected abstract void stptriK(String var1, String var2, int var3, float[] var4, int var5, intW var6);

   public void stptrs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("stptrs");
      }

      this.stptrs(uplo, trans, diag, n, nrhs, ap, 0, b, 0, ldb, info);
   }

   public void stptrs(String uplo, String trans, String diag, int n, int nrhs, float[] ap, int offsetap, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("stptrs");
      }

      this.stptrsK(uplo, trans, diag, n, nrhs, ap, offsetap, b, offsetb, ldb, info);
   }

   protected abstract void stptrsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public void strcon(String norm, String uplo, String diag, int n, float[] a, int lda, floatW rcond, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("strcon");
      }

      this.strcon(norm, uplo, diag, n, a, 0, lda, rcond, work, 0, iwork, 0, info);
   }

   public void strcon(String norm, String uplo, String diag, int n, float[] a, int offseta, int lda, floatW rcond, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("strcon");
      }

      this.strconK(norm, uplo, diag, n, a, offseta, lda, rcond, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void strconK(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   public void strevc(String side, String howmny, boolean[] select, int n, float[] t, int ldt, float[] vl, int ldvl, float[] vr, int ldvr, int mm, intW m, float[] work, intW info) {
      if (debug) {
         System.err.println("strevc");
      }

      this.strevc(side, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, mm, m, work, 0, info);
   }

   public void strevc(String side, String howmny, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, int mm, intW m, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("strevc");
      }

      this.strevcK(side, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, mm, m, work, offsetwork, info);
   }

   protected abstract void strevcK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, int var15, intW var16, float[] var17, int var18, intW var19);

   public void strexc(String compq, int n, float[] t, int ldt, float[] q, int ldq, intW ifst, intW ilst, float[] work, intW info) {
      if (debug) {
         System.err.println("strexc");
      }

      this.strexc(compq, n, t, 0, ldt, q, 0, ldq, ifst, ilst, work, 0, info);
   }

   public void strexc(String compq, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, intW ifst, intW ilst, float[] work, int offsetwork, intW info) {
      if (debug) {
         System.err.println("strexc");
      }

      this.strexcK(compq, n, t, offsett, ldt, q, offsetq, ldq, ifst, ilst, work, offsetwork, info);
   }

   protected abstract void strexcK(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9, intW var10, float[] var11, int var12, intW var13);

   public void strrfs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int lda, float[] b, int ldb, float[] x, int ldx, float[] ferr, float[] berr, float[] work, int[] iwork, intW info) {
      if (debug) {
         System.err.println("strrfs");
      }

      this.strrfs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, x, 0, ldx, ferr, 0, berr, 0, work, 0, iwork, 0, info);
   }

   public void strrfs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] x, int offsetx, int ldx, float[] ferr, int offsetferr, float[] berr, int offsetberr, float[] work, int offsetwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("strrfs");
      }

      this.strrfsK(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, x, offsetx, ldx, ferr, offsetferr, berr, offsetberr, work, offsetwork, iwork, offsetiwork, info);
   }

   protected abstract void strrfsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int[] var21, int var22, intW var23);

   public void strsen(String job, String compq, boolean[] select, int n, float[] t, int ldt, float[] q, int ldq, float[] wr, float[] wi, intW m, floatW s, floatW sep, float[] work, int lwork, int[] iwork, int liwork, intW info) {
      if (debug) {
         System.err.println("strsen");
      }

      this.strsen(job, compq, select, 0, n, t, 0, ldt, q, 0, ldq, wr, 0, wi, 0, m, s, sep, work, 0, lwork, iwork, 0, liwork, info);
   }

   public void strsen(String job, String compq, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] q, int offsetq, int ldq, float[] wr, int offsetwr, float[] wi, int offsetwi, intW m, floatW s, floatW sep, float[] work, int offsetwork, int lwork, int[] iwork, int offsetiwork, int liwork, intW info) {
      if (debug) {
         System.err.println("strsen");
      }

      this.strsenK(job, compq, select, offsetselect, n, t, offsett, ldt, q, offsetq, ldq, wr, offsetwr, wi, offsetwi, m, s, sep, work, offsetwork, lwork, iwork, offsetiwork, liwork, info);
   }

   protected abstract void strsenK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16, floatW var17, floatW var18, float[] var19, int var20, int var21, int[] var22, int var23, int var24, intW var25);

   public void strsna(String job, String howmny, boolean[] select, int n, float[] t, int ldt, float[] vl, int ldvl, float[] vr, int ldvr, float[] s, float[] sep, int mm, intW m, float[] work, int ldwork, int[] iwork, intW info) {
      if (debug) {
         System.err.println("strsna");
      }

      this.strsna(job, howmny, select, 0, n, t, 0, ldt, vl, 0, ldvl, vr, 0, ldvr, s, 0, sep, 0, mm, m, work, 0, ldwork, iwork, 0, info);
   }

   public void strsna(String job, String howmny, boolean[] select, int offsetselect, int n, float[] t, int offsett, int ldt, float[] vl, int offsetvl, int ldvl, float[] vr, int offsetvr, int ldvr, float[] s, int offsets, float[] sep, int offsetsep, int mm, intW m, float[] work, int offsetwork, int ldwork, int[] iwork, int offsetiwork, intW info) {
      if (debug) {
         System.err.println("strsna");
      }

      this.strsnaK(job, howmny, select, offsetselect, n, t, offsett, ldt, vl, offsetvl, ldvl, vr, offsetvr, ldvr, s, offsets, sep, offsetsep, mm, m, work, offsetwork, ldwork, iwork, offsetiwork, info);
   }

   protected abstract void strsnaK(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, int var19, intW var20, float[] var21, int var22, int var23, int[] var24, int var25, intW var26);

   public void strsyl(String trana, String tranb, int isgn, int m, int n, float[] a, int lda, float[] b, int ldb, float[] c, int Ldc, floatW scale, intW info) {
      if (debug) {
         System.err.println("strsyl");
      }

      this.strsyl(trana, tranb, isgn, m, n, a, 0, lda, b, 0, ldb, c, 0, Ldc, scale, info);
   }

   public void strsyl(String trana, String tranb, int isgn, int m, int n, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float[] c, int offsetc, int Ldc, floatW scale, intW info) {
      if (debug) {
         System.err.println("strsyl");
      }

      this.strsylK(trana, tranb, isgn, m, n, a, offseta, lda, b, offsetb, ldb, c, offsetc, Ldc, scale, info);
   }

   protected abstract void strsylK(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, floatW var15, intW var16);

   public void strti2(String uplo, String diag, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("strti2");
      }

      this.strti2(uplo, diag, n, a, 0, lda, info);
   }

   public void strti2(String uplo, String diag, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("strti2");
      }

      this.strti2K(uplo, diag, n, a, offseta, lda, info);
   }

   protected abstract void strti2K(String var1, String var2, int var3, float[] var4, int var5, int var6, intW var7);

   public void strtri(String uplo, String diag, int n, float[] a, int lda, intW info) {
      if (debug) {
         System.err.println("strtri");
      }

      this.strtri(uplo, diag, n, a, 0, lda, info);
   }

   public void strtri(String uplo, String diag, int n, float[] a, int offseta, int lda, intW info) {
      if (debug) {
         System.err.println("strtri");
      }

      this.strtriK(uplo, diag, n, a, offseta, lda, info);
   }

   protected abstract void strtriK(String var1, String var2, int var3, float[] var4, int var5, int var6, intW var7);

   public void strtrs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int lda, float[] b, int ldb, intW info) {
      if (debug) {
         System.err.println("strtrs");
      }

      this.strtrs(uplo, trans, diag, n, nrhs, a, 0, lda, b, 0, ldb, info);
   }

   public void strtrs(String uplo, String trans, String diag, int n, int nrhs, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, intW info) {
      if (debug) {
         System.err.println("strtrs");
      }

      this.strtrsK(uplo, trans, diag, n, nrhs, a, offseta, lda, b, offsetb, ldb, info);
   }

   protected abstract void strtrsK(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12);

   public void stzrqf(int m, int n, float[] a, int lda, float[] tau, intW info) {
      if (debug) {
         System.err.println("stzrqf");
      }

      this.stzrqf(m, n, a, 0, lda, tau, 0, info);
   }

   public void stzrqf(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, intW info) {
      if (debug) {
         System.err.println("stzrqf");
      }

      this.stzrqfK(m, n, a, offseta, lda, tau, offsettau, info);
   }

   protected abstract void stzrqfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, intW var8);

   public void stzrzf(int m, int n, float[] a, int lda, float[] tau, float[] work, int lwork, intW info) {
      if (debug) {
         System.err.println("stzrzf");
      }

      this.stzrzf(m, n, a, 0, lda, tau, 0, work, 0, lwork, info);
   }

   public void stzrzf(int m, int n, float[] a, int offseta, int lda, float[] tau, int offsettau, float[] work, int offsetwork, int lwork, intW info) {
      if (debug) {
         System.err.println("stzrzf");
      }

      this.stzrzfK(m, n, a, offseta, lda, tau, offsettau, work, offsetwork, lwork, info);
   }

   protected abstract void stzrzfK(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   public double dlamch(String cmach) {
      if (debug) {
         System.err.println("dlamch");
      }

      this.checkArgument("DLAMCH", 1, this.lsame("E", cmach) || this.lsame("S", cmach) || this.lsame("B", cmach) || this.lsame("P", cmach) || this.lsame("N", cmach) || this.lsame("R", cmach) || this.lsame("M", cmach) || this.lsame("U", cmach) || this.lsame("L", cmach) || this.lsame("O", cmach));
      this.requireNonNull(cmach);
      return this.dlamchK(cmach);
   }

   protected abstract double dlamchK(String var1);

   public void dlamc1(intW beta, intW t, booleanW rnd, booleanW ieee1) {
      if (debug) {
         System.err.println("dlamc1");
      }

      this.dlamc1K(beta, t, rnd, ieee1);
   }

   protected abstract void dlamc1K(intW var1, intW var2, booleanW var3, booleanW var4);

   public void dlamc2(intW beta, intW t, booleanW rnd, doubleW eps, intW emin, doubleW rmin, intW emax, doubleW rmax) {
      if (debug) {
         System.err.println("dlamc2");
      }

      this.dlamc2K(beta, t, rnd, eps, emin, rmin, emax, rmax);
   }

   protected abstract void dlamc2K(intW var1, intW var2, booleanW var3, doubleW var4, intW var5, doubleW var6, intW var7, doubleW var8);

   public double dlamc3(double a, double b) {
      if (debug) {
         System.err.println("dlamc3");
      }

      return this.dlamc3K(a, b);
   }

   protected abstract double dlamc3K(double var1, double var3);

   public void dlamc4(intW emin, double start, int base) {
      if (debug) {
         System.err.println("dlamc4");
      }

      this.dlamc4K(emin, start, base);
   }

   protected abstract void dlamc4K(intW var1, double var2, int var4);

   public void dlamc5(int beta, int p, int emin, boolean ieee, intW emax, doubleW rmax) {
      if (debug) {
         System.err.println("dlamc5");
      }

      this.dlamc5K(beta, p, emin, ieee, emax, rmax);
   }

   protected abstract void dlamc5K(int var1, int var2, int var3, boolean var4, intW var5, doubleW var6);

   public double dsecnd() {
      if (debug) {
         System.err.println("dsecnd");
      }

      return this.dsecndK();
   }

   protected abstract double dsecndK();

   public float second() {
      if (debug) {
         System.err.println("second");
      }

      return this.secondK();
   }

   protected abstract float secondK();

   public float slamch(String cmach) {
      if (debug) {
         System.err.println("slamch");
      }

      this.requireNonNull(cmach);
      this.checkArgument("DLAMCH", 1, this.lsame("E", cmach) || this.lsame("S", cmach) || this.lsame("B", cmach) || this.lsame("P", cmach) || this.lsame("N", cmach) || this.lsame("R", cmach) || this.lsame("M", cmach) || this.lsame("U", cmach) || this.lsame("L", cmach) || this.lsame("O", cmach));
      return this.slamchK(cmach);
   }

   protected abstract float slamchK(String var1);

   public void slamc1(intW beta, intW t, booleanW rnd, booleanW ieee1) {
      if (debug) {
         System.err.println("slamc1");
      }

      this.slamc1K(beta, t, rnd, ieee1);
   }

   protected abstract void slamc1K(intW var1, intW var2, booleanW var3, booleanW var4);

   public void slamc2(intW beta, intW t, booleanW rnd, floatW eps, intW emin, floatW rmin, intW emax, floatW rmax) {
      if (debug) {
         System.err.println("slamc2");
      }

      this.slamc2K(beta, t, rnd, eps, emin, rmin, emax, rmax);
   }

   protected abstract void slamc2K(intW var1, intW var2, booleanW var3, floatW var4, intW var5, floatW var6, intW var7, floatW var8);

   public float slamc3(float a, float b) {
      if (debug) {
         System.err.println("slamc3");
      }

      return this.slamc3K(a, b);
   }

   protected abstract float slamc3K(float var1, float var2);

   public void slamc4(intW emin, float start, int base) {
      if (debug) {
         System.err.println("slamc4");
      }

      this.slamc4K(emin, start, base);
   }

   protected abstract void slamc4K(intW var1, float var2, int var3);

   public void slamc5(int beta, int p, int emin, boolean ieee, intW emax, floatW rmax) {
      if (debug) {
         System.err.println("slamc5");
      }

      this.slamc5K(beta, p, emin, ieee, emax, rmax);
   }

   protected abstract void slamc5K(int var1, int var2, int var3, boolean var4, intW var5, floatW var6);

   public boolean lsame(String ca, String cb) {
      if (debug) {
         System.err.println("lsame");
      }

      return ca != null && ca.regionMatches(true, 0, cb, 0, ca.length());
   }
}
