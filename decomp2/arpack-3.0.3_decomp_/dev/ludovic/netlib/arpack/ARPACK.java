package dev.ludovic.netlib.arpack;

import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

public interface ARPACK {
   static ARPACK getInstance() {
      return InstanceBuilder.arpack();
   }

   void dmout(int var1, int var2, int var3, double[] var4, int var5, int var6, String var7);

   void dmout(int var1, int var2, int var3, double[] var4, int var5, int var6, int var7, String var8);

   void smout(int var1, int var2, int var3, float[] var4, int var5, int var6, String var7);

   void smout(int var1, int var2, int var3, float[] var4, int var5, int var6, int var7, String var8);

   void dvout(int var1, int var2, double[] var3, int var4, String var5);

   void dvout(int var1, int var2, double[] var3, int var4, int var5, String var6);

   void svout(int var1, int var2, float[] var3, int var4, String var5);

   void svout(int var1, int var2, float[] var3, int var4, int var5, String var6);

   void ivout(int var1, int var2, int[] var3, int var4, String var5);

   void ivout(int var1, int var2, int[] var3, int var4, int var5, String var6);

   void dgetv0(intW var1, String var2, int var3, boolean var4, int var5, int var6, double[] var7, int var8, double[] var9, doubleW var10, int[] var11, double[] var12, intW var13);

   void dgetv0(intW var1, String var2, int var3, boolean var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, doubleW var12, int[] var13, int var14, double[] var15, int var16, intW var17);

   void sgetv0(intW var1, String var2, int var3, boolean var4, int var5, int var6, float[] var7, int var8, float[] var9, floatW var10, int[] var11, float[] var12, intW var13);

   void sgetv0(intW var1, String var2, int var3, boolean var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, floatW var12, int[] var13, int var14, float[] var15, int var16, intW var17);

   void dlaqrb(boolean var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, double[] var9, intW var10);

   void dlaqrb(boolean var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void slaqrb(boolean var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, float[] var9, intW var10);

   void slaqrb(boolean var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void dnaitr(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, doubleW var8, double[] var9, int var10, double[] var11, int var12, int[] var13, double[] var14, intW var15);

   void dnaitr(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, doubleW var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   void snaitr(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, floatW var8, float[] var9, int var10, float[] var11, int var12, int[] var13, float[] var14, intW var15);

   void snaitr(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, floatW var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   void dnapps(int var1, intW var2, int var3, double[] var4, double[] var5, double[] var6, int var7, double[] var8, int var9, double[] var10, double[] var11, int var12, double[] var13, double[] var14);

   void dnapps(int var1, intW var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, double[] var21, int var22);

   void snapps(int var1, intW var2, int var3, float[] var4, float[] var5, float[] var6, int var7, float[] var8, int var9, float[] var10, float[] var11, int var12, float[] var13, float[] var14);

   void snapps(int var1, intW var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, float[] var21, int var22);

   void dnaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, intW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, double[] var19, double[] var20, double[] var21, int var22, double[] var23, int[] var24, double[] var25, intW var26);

   void dnaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, int var13, intW var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int var29, double[] var30, int var31, int[] var32, int var33, double[] var34, int var35, intW var36);

   void snaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, float[] var17, float[] var18, float[] var19, float[] var20, int var21, float[] var22, int[] var23, float[] var24, intW var25);

   void snaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, int var12, intW var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, float[] var26, int var27, int var28, float[] var29, int var30, int[] var31, int var32, float[] var33, int var34, intW var35);

   void dnaupd(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int[] var12, double[] var13, double[] var14, int var15, intW var16);

   void dnaupd(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, intW var22);

   void snaupd(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int[] var12, float[] var13, float[] var14, int var15, intW var16);

   void snaupd(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, intW var22);

   void dnconv(int var1, double[] var2, double[] var3, double[] var4, double var5, intW var7);

   void dnconv(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, intW var10);

   void snconv(int var1, float[] var2, float[] var3, float[] var4, float var5, intW var6);

   void snconv(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, intW var9);

   void dsconv(int var1, double[] var2, double[] var3, double var4, intW var6);

   void dsconv(int var1, double[] var2, int var3, double[] var4, int var5, double var6, intW var8);

   void ssconv(int var1, float[] var2, float[] var3, float var4, intW var5);

   void ssconv(int var1, float[] var2, int var3, float[] var4, int var5, float var6, intW var7);

   void dneigh(double var1, intW var3, double[] var4, int var5, double[] var6, double[] var7, double[] var8, double[] var9, int var10, double[] var11, intW var12);

   void dneigh(double var1, intW var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, intW var18);

   void sneigh(float var1, intW var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, intW var11);

   void sneigh(float var1, intW var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   void dneupd(boolean var1, String var2, boolean[] var3, double[] var4, double[] var5, double[] var6, int var7, double var8, double var10, double[] var12, String var13, int var14, String var15, intW var16, double var17, double[] var19, int var20, double[] var21, int var22, int[] var23, int[] var24, double[] var25, double[] var26, int var27, intW var28);

   void dneupd(boolean var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double var12, double var14, double[] var16, int var17, String var18, int var19, String var20, intW var21, double var22, double[] var24, int var25, int var26, double[] var27, int var28, int var29, int[] var30, int var31, int[] var32, int var33, double[] var34, int var35, double[] var36, int var37, int var38, intW var39);

   void sneupd(boolean var1, String var2, boolean[] var3, float[] var4, float[] var5, float[] var6, int var7, float var8, float var9, float[] var10, String var11, int var12, String var13, intW var14, float var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int[] var21, float[] var22, float[] var23, int var24, intW var25);

   void sneupd(boolean var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float var12, float var13, float[] var14, int var15, String var16, int var17, String var18, intW var19, float var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, int[] var27, int var28, int[] var29, int var30, float[] var31, int var32, float[] var33, int var34, int var35, intW var36);

   void dngets(int var1, String var2, intW var3, intW var4, double[] var5, double[] var6, double[] var7, double[] var8, double[] var9);

   void dngets(int var1, String var2, intW var3, intW var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14);

   void sngets(int var1, String var2, intW var3, intW var4, float[] var5, float[] var6, float[] var7, float[] var8, float[] var9);

   void sngets(int var1, String var2, intW var3, intW var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14);

   void dsaitr(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, doubleW var8, double[] var9, int var10, double[] var11, int var12, int[] var13, double[] var14, intW var15);

   void dsaitr(intW var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, doubleW var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   void ssaitr(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, floatW var8, float[] var9, int var10, float[] var11, int var12, int[] var13, float[] var14, intW var15);

   void ssaitr(intW var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, floatW var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   void dsapps(int var1, int var2, int var3, double[] var4, double[] var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, double[] var12);

   void dsapps(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18);

   void ssapps(int var1, int var2, int var3, float[] var4, float[] var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, float[] var12);

   void ssapps(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18);

   void dsaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, intW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, double[] var19, double[] var20, int var21, double[] var22, int[] var23, double[] var24, intW var25);

   void dsaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, double var7, double[] var9, int var10, int var11, int var12, int var13, intW var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int var27, double[] var28, int var29, int[] var30, int var31, double[] var32, int var33, intW var34);

   void ssaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, float[] var17, float[] var18, float[] var19, int var20, float[] var21, int[] var22, float[] var23, intW var24);

   void ssaup2(intW var1, String var2, int var3, String var4, intW var5, intW var6, float var7, float[] var8, int var9, int var10, int var11, int var12, intW var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int[] var29, int var30, float[] var31, int var32, intW var33);

   void dseigt(double var1, int var3, double[] var4, int var5, double[] var6, double[] var7, double[] var8, intW var9);

   void dseigt(double var1, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   void sseigt(float var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, intW var8);

   void sseigt(float var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   void dsesrt(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7);

   void dsesrt(String var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   void ssesrt(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7);

   void ssesrt(String var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   void dsaupd(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int[] var12, double[] var13, double[] var14, int var15, intW var16);

   void dsaupd(intW var1, String var2, int var3, String var4, int var5, doubleW var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, intW var22);

   void ssaupd(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int[] var12, float[] var13, float[] var14, int var15, intW var16);

   void ssaupd(intW var1, String var2, int var3, String var4, int var5, floatW var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, intW var22);

   void dseupd(boolean var1, String var2, boolean[] var3, double[] var4, double[] var5, int var6, double var7, String var9, int var10, String var11, intW var12, double var13, double[] var15, int var16, double[] var17, int var18, int[] var19, int[] var20, double[] var21, double[] var22, int var23, intW var24);

   void dseupd(boolean var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double var10, String var12, int var13, String var14, intW var15, double var16, double[] var18, int var19, int var20, double[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int var32, intW var33);

   void sseupd(boolean var1, String var2, boolean[] var3, float[] var4, float[] var5, int var6, float var7, String var8, int var9, String var10, intW var11, float var12, float[] var13, int var14, float[] var15, int var16, int[] var17, int[] var18, float[] var19, float[] var20, int var21, intW var22);

   void sseupd(boolean var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float var10, String var11, int var12, String var13, intW var14, float var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, int[] var22, int var23, int[] var24, int var25, float[] var26, int var27, float[] var28, int var29, int var30, intW var31);

   void dsgets(int var1, String var2, intW var3, intW var4, double[] var5, double[] var6, double[] var7);

   void dsgets(int var1, String var2, intW var3, intW var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10);

   void ssgets(int var1, String var2, intW var3, intW var4, float[] var5, float[] var6, float[] var7);

   void ssgets(int var1, String var2, intW var3, intW var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10);

   void dsortc(String var1, boolean var2, int var3, double[] var4, double[] var5, double[] var6);

   void dsortc(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9);

   void ssortc(String var1, boolean var2, int var3, float[] var4, float[] var5, float[] var6);

   void ssortc(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9);

   void dsortr(String var1, boolean var2, int var3, double[] var4, double[] var5);

   void dsortr(String var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7);

   void ssortr(String var1, boolean var2, int var3, float[] var4, float[] var5);

   void ssortr(String var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7);

   void dstatn();

   void sstatn();

   void dstats();

   void sstats();

   void dstqrb(int var1, double[] var2, double[] var3, double[] var4, double[] var5, intW var6);

   void dstqrb(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void sstqrb(int var1, float[] var2, float[] var3, float[] var4, float[] var5, intW var6);

   void sstqrb(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   int icnteq(int var1, int[] var2, int var3);

   int icnteq(int var1, int[] var2, int var3, int var4);

   void icopy(int var1, int[] var2, int var3, int[] var4, int var5);

   void icopy(int var1, int[] var2, int var3, int var4, int[] var5, int var6, int var7);

   void iset(int var1, int var2, int[] var3, int var4);

   void iset(int var1, int var2, int[] var3, int var4, int var5);

   void iswap(int var1, int[] var2, int var3, int[] var4, int var5);

   void iswap(int var1, int[] var2, int var3, int var4, int[] var5, int var6, int var7);

   void second(floatW var1);
}
