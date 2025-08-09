package dev.ludovic.netlib.blas;

import org.netlib.util.doubleW;
import org.netlib.util.floatW;

public interface BLAS {
   static BLAS getInstance() {
      return InstanceBuilder.blas();
   }

   double dasum(int var1, double[] var2, int var3);

   double dasum(int var1, double[] var2, int var3, int var4);

   float sasum(int var1, float[] var2, int var3);

   float sasum(int var1, float[] var2, int var3, int var4);

   void daxpy(int var1, double var2, double[] var4, int var5, double[] var6, int var7);

   void daxpy(int var1, double var2, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   void saxpy(int var1, float var2, float[] var3, int var4, float[] var5, int var6);

   void saxpy(int var1, float var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8);

   void dcopy(int var1, double[] var2, int var3, double[] var4, int var5);

   void dcopy(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   void scopy(int var1, float[] var2, int var3, float[] var4, int var5);

   void scopy(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   double ddot(int var1, double[] var2, int var3, double[] var4, int var5);

   double ddot(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   float sdot(int var1, float[] var2, int var3, float[] var4, int var5);

   float sdot(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   float sdsdot(int var1, float var2, float[] var3, int var4, float[] var5, int var6);

   float sdsdot(int var1, float var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8);

   void dgbmv(String var1, int var2, int var3, int var4, int var5, double var6, double[] var8, int var9, double[] var10, int var11, double var12, double[] var14, int var15);

   void dgbmv(String var1, int var2, int var3, int var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double var14, double[] var16, int var17, int var18);

   void sgbmv(String var1, int var2, int var3, int var4, int var5, float var6, float[] var7, int var8, float[] var9, int var10, float var11, float[] var12, int var13);

   void sgbmv(String var1, int var2, int var3, int var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float[] var14, int var15, int var16);

   void dgemm(String var1, String var2, int var3, int var4, int var5, double var6, double[] var8, int var9, double[] var10, int var11, double var12, double[] var14, int var15);

   void dgemm(String var1, String var2, int var3, int var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double var14, double[] var16, int var17, int var18);

   void sgemm(String var1, String var2, int var3, int var4, int var5, float var6, float[] var7, int var8, float[] var9, int var10, float var11, float[] var12, int var13);

   void sgemm(String var1, String var2, int var3, int var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float[] var14, int var15, int var16);

   void dgemv(String var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double var10, double[] var12, int var13);

   void dgemv(String var1, int var2, int var3, double var4, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double[] var14, int var15, int var16);

   void sgemv(String var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float[] var10, int var11);

   void sgemv(String var1, int var2, int var3, float var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float var11, float[] var12, int var13, int var14);

   void dger(int var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10);

   void dger(int var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13);

   void sger(int var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9);

   void sger(int var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12);

   double dnrm2(int var1, double[] var2, int var3);

   double dnrm2(int var1, double[] var2, int var3, int var4);

   float snrm2(int var1, float[] var2, int var3);

   float snrm2(int var1, float[] var2, int var3, int var4);

   void drot(int var1, double[] var2, int var3, double[] var4, int var5, double var6, double var8);

   void drot(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double var8, double var10);

   void srot(int var1, float[] var2, int var3, float[] var4, int var5, float var6, float var7);

   void srot(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float var8, float var9);

   void drotg(doubleW var1, doubleW var2, doubleW var3, doubleW var4);

   void srotg(floatW var1, floatW var2, floatW var3, floatW var4);

   void drotm(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6);

   void drotm(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   void srotm(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6);

   void srotm(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   void drotmg(doubleW var1, doubleW var2, doubleW var3, double var4, double[] var6);

   void drotmg(doubleW var1, doubleW var2, doubleW var3, double var4, double[] var6, int var7);

   void srotmg(floatW var1, floatW var2, floatW var3, float var4, float[] var5);

   void srotmg(floatW var1, floatW var2, floatW var3, float var4, float[] var5, int var6);

   void dsbmv(String var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double var10, double[] var12, int var13);

   void dsbmv(String var1, int var2, int var3, double var4, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double[] var14, int var15, int var16);

   void ssbmv(String var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float[] var10, int var11);

   void ssbmv(String var1, int var2, int var3, float var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float var11, float[] var12, int var13, int var14);

   void dscal(int var1, double var2, double[] var4, int var5);

   void dscal(int var1, double var2, double[] var4, int var5, int var6);

   void sscal(int var1, float var2, float[] var3, int var4);

   void sscal(int var1, float var2, float[] var3, int var4, int var5);

   void dspmv(String var1, int var2, double var3, double[] var5, double[] var6, int var7, double var8, double[] var10, int var11);

   void dspmv(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, int var9, double var10, double[] var12, int var13, int var14);

   void sspmv(String var1, int var2, float var3, float[] var4, float[] var5, int var6, float var7, float[] var8, int var9);

   void sspmv(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, int var12);

   void dspr(String var1, int var2, double var3, double[] var5, int var6, double[] var7);

   void dspr(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9);

   void sspr(String var1, int var2, float var3, float[] var4, int var5, float[] var6);

   void sspr(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8);

   void dspr2(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, double[] var9);

   void dspr2(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12);

   void sspr2(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, float[] var8);

   void sspr2(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11);

   void dswap(int var1, double[] var2, int var3, double[] var4, int var5);

   void dswap(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7);

   void sswap(int var1, float[] var2, int var3, float[] var4, int var5);

   void sswap(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7);

   void dsymm(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, double[] var9, int var10, double var11, double[] var13, int var14);

   void dsymm(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double[] var15, int var16, int var17);

   void ssymm(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, float[] var8, int var9, float var10, float[] var11, int var12);

   void ssymm(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float[] var13, int var14, int var15);

   void dsymv(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, double var9, double[] var11, int var12);

   void dsymv(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double var11, double[] var13, int var14, int var15);

   void ssymv(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, float[] var9, int var10);

   void ssymv(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float var10, float[] var11, int var12, int var13);

   void dsyr(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8);

   void dsyr(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   void ssyr(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7);

   void ssyr(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   void dsyr2(String var1, int var2, double var3, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10);

   void dsyr2(String var1, int var2, double var3, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13);

   void ssyr2(String var1, int var2, float var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9);

   void ssyr2(String var1, int var2, float var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12);

   void dsyr2k(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, double[] var9, int var10, double var11, double[] var13, int var14);

   void dsyr2k(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double[] var15, int var16, int var17);

   void ssyr2k(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, float[] var8, int var9, float var10, float[] var11, int var12);

   void ssyr2k(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float[] var13, int var14, int var15);

   void dsyrk(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, double var9, double[] var11, int var12);

   void dsyrk(String var1, String var2, int var3, int var4, double var5, double[] var7, int var8, int var9, double var10, double[] var12, int var13, int var14);

   void ssyrk(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, float var8, float[] var9, int var10);

   void ssyrk(String var1, String var2, int var3, int var4, float var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, int var12);

   void dtbmv(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9);

   void dtbmv(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11);

   void stbmv(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9);

   void stbmv(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11);

   void dtbsv(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9);

   void dtbsv(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11);

   void stbsv(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9);

   void stbsv(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11);

   void dtpmv(String var1, String var2, String var3, int var4, double[] var5, double[] var6, int var7);

   void dtpmv(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   void stpmv(String var1, String var2, String var3, int var4, float[] var5, float[] var6, int var7);

   void stpmv(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   void dtpsv(String var1, String var2, String var3, int var4, double[] var5, double[] var6, int var7);

   void dtpsv(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   void stpsv(String var1, String var2, String var3, int var4, float[] var5, float[] var6, int var7);

   void stpsv(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   void dtrmm(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, double[] var11, int var12);

   void dtrmm(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   void strmm(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, float[] var10, int var11);

   void strmm(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13);

   void dtrmv(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8);

   void dtrmv(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   void strmv(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8);

   void strmv(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   void dtrsm(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, double[] var11, int var12);

   void dtrsm(String var1, String var2, String var3, String var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   void strsm(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, float[] var10, int var11);

   void strsm(String var1, String var2, String var3, String var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13);

   void dtrsv(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8);

   void dtrsv(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   void strsv(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8);

   void strsv(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   int idamax(int var1, double[] var2, int var3);

   int idamax(int var1, double[] var2, int var3, int var4);

   int isamax(int var1, float[] var2, int var3);

   int isamax(int var1, float[] var2, int var3, int var4);

   boolean lsame(String var1, String var2);
}
