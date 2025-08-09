package dev.ludovic.netlib.lapack;

import org.netlib.util.StringW;
import org.netlib.util.booleanW;
import org.netlib.util.doubleW;
import org.netlib.util.floatW;
import org.netlib.util.intW;

public interface LAPACK {
   static LAPACK getInstance() {
      return InstanceBuilder.lapack();
   }

   void dbdsdc(String var1, String var2, int var3, double[] var4, double[] var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int[] var11, double[] var12, int[] var13, intW var14);

   void dbdsdc(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   void dbdsqr(String var1, int var2, int var3, int var4, int var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, intW var15);

   void dbdsqr(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, intW var21);

   void ddisna(String var1, int var2, int var3, double[] var4, double[] var5, intW var6);

   void ddisna(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dgbbrd(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, double[] var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, intW var18);

   void dgbbrd(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, intW var25);

   void dgbcon(String var1, int var2, int var3, int var4, double[] var5, int var6, int[] var7, double var8, doubleW var10, double[] var11, int[] var12, intW var13);

   void dgbcon(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, double var10, doubleW var12, double[] var13, int var14, int[] var15, int var16, intW var17);

   void dgbequ(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, doubleW var9, doubleW var10, doubleW var11, intW var12);

   void dgbequ(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, doubleW var13, doubleW var14, intW var15);

   void dgbrfs(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int[] var10, double[] var11, int var12, double[] var13, int var14, double[] var15, double[] var16, double[] var17, int[] var18, intW var19);

   void dgbrfs(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int[] var26, int var27, intW var28);

   void dgbsv(int var1, int var2, int var3, int var4, double[] var5, int var6, int[] var7, double[] var8, int var9, intW var10);

   void dgbsv(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   void dgbsvx(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, StringW var12, double[] var13, double[] var14, double[] var15, int var16, double[] var17, int var18, doubleW var19, double[] var20, double[] var21, double[] var22, int[] var23, intW var24);

   void dgbsvx(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, StringW var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, doubleW var26, double[] var27, int var28, double[] var29, int var30, double[] var31, int var32, int[] var33, int var34, intW var35);

   void dgbtf2(int var1, int var2, int var3, int var4, double[] var5, int var6, int[] var7, intW var8);

   void dgbtf2(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   void dgbtrf(int var1, int var2, int var3, int var4, double[] var5, int var6, int[] var7, intW var8);

   void dgbtrf(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   void dgbtrs(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int[] var8, double[] var9, int var10, intW var11);

   void dgbtrs(String var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, int[] var9, int var10, double[] var11, int var12, int var13, intW var14);

   void dgebak(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void dgebak(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dgebal(String var1, int var2, double[] var3, int var4, intW var5, intW var6, double[] var7, intW var8);

   void dgebal(String var1, int var2, double[] var3, int var4, int var5, intW var6, intW var7, double[] var8, int var9, intW var10);

   void dgebd2(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, double[] var7, double[] var8, double[] var9, intW var10);

   void dgebd2(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   void dgebrd(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, double[] var7, double[] var8, double[] var9, int var10, intW var11);

   void dgebrd(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dgecon(String var1, int var2, double[] var3, int var4, double var5, doubleW var7, double[] var8, int[] var9, intW var10);

   void dgecon(String var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   void dgeequ(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, doubleW var7, doubleW var8, doubleW var9, intW var10);

   void dgeequ(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, doubleW var10, doubleW var11, doubleW var12, intW var13);

   void dgees(String var1, String var2, Object var3, int var4, double[] var5, int var6, intW var7, double[] var8, double[] var9, double[] var10, int var11, double[] var12, int var13, boolean[] var14, intW var15);

   void dgees(String var1, String var2, Object var3, int var4, double[] var5, int var6, int var7, intW var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, boolean[] var19, int var20, intW var21);

   void dgeesx(String var1, String var2, Object var3, String var4, int var5, double[] var6, int var7, intW var8, double[] var9, double[] var10, double[] var11, int var12, doubleW var13, doubleW var14, double[] var15, int var16, int[] var17, int var18, boolean[] var19, intW var20);

   void dgeesx(String var1, String var2, Object var3, String var4, int var5, double[] var6, int var7, int var8, intW var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, doubleW var17, doubleW var18, double[] var19, int var20, int var21, int[] var22, int var23, int var24, boolean[] var25, int var26, intW var27);

   void dgeev(String var1, String var2, int var3, double[] var4, int var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dgeev(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, intW var20);

   void dgeevx(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, int var11, double[] var12, int var13, intW var14, intW var15, double[] var16, doubleW var17, double[] var18, double[] var19, double[] var20, int var21, int[] var22, intW var23);

   void dgeevx(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19, intW var20, double[] var21, int var22, doubleW var23, double[] var24, int var25, double[] var26, int var27, double[] var28, int var29, int var30, int[] var31, int var32, intW var33);

   void dgegs(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, intW var17);

   void dgegs(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   void dgegv(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, intW var17);

   void dgegv(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   void dgehd2(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, intW var8);

   void dgehd2(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dgehrd(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dgehrd(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dgelq2(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, intW var7);

   void dgelq2(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void dgelqf(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, int var7, intW var8);

   void dgelqf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dgels(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dgels(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, intW var14);

   void dgelsd(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double var9, intW var11, double[] var12, int var13, int[] var14, intW var15);

   void dgelsd(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   void dgelss(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double var9, intW var11, double[] var12, int var13, intW var14);

   void dgelss(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, intW var18);

   void dgelsx(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, double var9, intW var11, double[] var12, intW var13);

   void dgelsx(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double var12, intW var14, double[] var15, int var16, intW var17);

   void dgelsy(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, double var9, intW var11, double[] var12, int var13, intW var14);

   void dgelsy(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double var12, intW var14, double[] var15, int var16, int var17, intW var18);

   void dgeql2(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, intW var7);

   void dgeql2(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void dgeqlf(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, int var7, intW var8);

   void dgeqlf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dgeqp3(int var1, int var2, double[] var3, int var4, int[] var5, double[] var6, double[] var7, int var8, intW var9);

   void dgeqp3(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   void dgeqpf(int var1, int var2, double[] var3, int var4, int[] var5, double[] var6, double[] var7, intW var8);

   void dgeqpf(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12);

   void dgeqr2(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, intW var7);

   void dgeqr2(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void dgeqrf(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, int var7, intW var8);

   void dgeqrf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dgerfs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, double[] var9, int var10, double[] var11, int var12, double[] var13, double[] var14, double[] var15, int[] var16, intW var17);

   void dgerfs(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   void dgerq2(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, intW var7);

   void dgerq2(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void dgerqf(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, int var7, intW var8);

   void dgerqf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dgesc2(int var1, double[] var2, int var3, double[] var4, int[] var5, int[] var6, doubleW var7);

   void dgesc2(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int[] var7, int var8, int[] var9, int var10, doubleW var11);

   void dgesdd(String var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int[] var13, intW var14);

   void dgesdd(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   void dgesv(int var1, int var2, double[] var3, int var4, int[] var5, double[] var6, int var7, intW var8);

   void dgesv(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dgesvd(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dgesvd(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19);

   void dgesvx(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int[] var9, StringW var10, double[] var11, double[] var12, double[] var13, int var14, double[] var15, int var16, doubleW var17, double[] var18, double[] var19, double[] var20, int[] var21, intW var22);

   void dgesvx(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, int[] var11, int var12, StringW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, doubleW var24, double[] var25, int var26, double[] var27, int var28, double[] var29, int var30, int[] var31, int var32, intW var33);

   void dgetc2(int var1, double[] var2, int var3, int[] var4, int[] var5, intW var6);

   void dgetc2(int var1, double[] var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, intW var9);

   void dgetf2(int var1, int var2, double[] var3, int var4, int[] var5, intW var6);

   void dgetf2(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   void dgetrf(int var1, int var2, double[] var3, int var4, int[] var5, intW var6);

   void dgetrf(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   void dgetri(int var1, double[] var2, int var3, int[] var4, double[] var5, int var6, intW var7);

   void dgetri(int var1, double[] var2, int var3, int var4, int[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dgetrs(String var1, int var2, int var3, double[] var4, int var5, int[] var6, double[] var7, int var8, intW var9);

   void dgetrs(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dggbak(String var1, String var2, int var3, int var4, int var5, double[] var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dggbak(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, intW var14);

   void dggbal(String var1, int var2, double[] var3, int var4, double[] var5, int var6, intW var7, intW var8, double[] var9, double[] var10, double[] var11, intW var12);

   void dggbal(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9, intW var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, intW var17);

   void dgges(String var1, String var2, String var3, Object var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10, double[] var11, double[] var12, double[] var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, boolean[] var20, intW var21);

   void dgges(String var1, String var2, String var3, Object var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, boolean[] var28, int var29, intW var30);

   void dggesx(String var1, String var2, String var3, Object var4, String var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11, double[] var12, double[] var13, double[] var14, double[] var15, int var16, double[] var17, int var18, double[] var19, double[] var20, double[] var21, int var22, int[] var23, int var24, boolean[] var25, intW var26);

   void dggesx(String var1, String var2, String var3, Object var4, String var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, intW var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int var32, int[] var33, int var34, int var35, boolean[] var36, int var37, intW var38);

   void dggev(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, intW var17);

   void dggev(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25);

   void dggevx(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, double[] var11, double[] var12, double[] var13, int var14, double[] var15, int var16, intW var17, intW var18, double[] var19, double[] var20, doubleW var21, doubleW var22, double[] var23, double[] var24, double[] var25, int var26, int[] var27, boolean[] var28, intW var29);

   void dggevx(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, intW var24, intW var25, double[] var26, int var27, double[] var28, int var29, doubleW var30, doubleW var31, double[] var32, int var33, double[] var34, int var35, double[] var36, int var37, int var38, int[] var39, int var40, boolean[] var41, int var42, intW var43);

   void dggglm(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, double[] var11, int var12, intW var13);

   void dggglm(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   void dgghrd(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dgghrd(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   void dgglse(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, double[] var11, int var12, intW var13);

   void dgglse(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   void dggqrf(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, double[] var9, double[] var10, int var11, intW var12);

   void dggqrf(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dggrqf(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, double[] var9, double[] var10, int var11, intW var12);

   void dggrqf(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dggsvd(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, double[] var9, int var10, double[] var11, int var12, double[] var13, double[] var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int[] var22, intW var23);

   void dggsvd(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, double[] var28, int var29, int[] var30, int var31, intW var32);

   void dggsvp(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double var11, double var13, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int[] var23, double[] var24, double[] var25, intW var26);

   void dggsvp(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double var13, double var15, intW var17, intW var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, int[] var28, int var29, double[] var30, int var31, double[] var32, int var33, intW var34);

   void dgtcon(String var1, int var2, double[] var3, double[] var4, double[] var5, double[] var6, int[] var7, double var8, doubleW var10, double[] var11, int[] var12, intW var13);

   void dgtcon(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int var12, double var13, doubleW var15, double[] var16, int var17, int[] var18, int var19, intW var20);

   void dgtrfs(String var1, int var2, int var3, double[] var4, double[] var5, double[] var6, double[] var7, double[] var8, double[] var9, double[] var10, int[] var11, double[] var12, int var13, double[] var14, int var15, double[] var16, double[] var17, double[] var18, int[] var19, intW var20);

   void dgtrfs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int[] var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int[] var32, int var33, intW var34);

   void dgtsv(int var1, int var2, double[] var3, double[] var4, double[] var5, double[] var6, int var7, intW var8);

   void dgtsv(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dgtsvx(String var1, String var2, int var3, int var4, double[] var5, double[] var6, double[] var7, double[] var8, double[] var9, double[] var10, double[] var11, int[] var12, double[] var13, int var14, double[] var15, int var16, doubleW var17, double[] var18, double[] var19, double[] var20, int[] var21, intW var22);

   void dgtsvx(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, int[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int var26, doubleW var27, double[] var28, int var29, double[] var30, int var31, double[] var32, int var33, int[] var34, int var35, intW var36);

   void dgttrf(int var1, double[] var2, double[] var3, double[] var4, double[] var5, int[] var6, intW var7);

   void dgttrf(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   void dgttrs(String var1, int var2, int var3, double[] var4, double[] var5, double[] var6, double[] var7, int[] var8, double[] var9, int var10, intW var11);

   void dgttrs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dgtts2(int var1, int var2, int var3, double[] var4, double[] var5, double[] var6, double[] var7, int[] var8, double[] var9, int var10);

   void dgtts2(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, double[] var14, int var15, int var16);

   void dhgeqz(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, double[] var12, double[] var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, intW var20);

   void dhgeqz(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, double[] var25, int var26, int var27, intW var28);

   void dhsein(String var1, String var2, String var3, boolean[] var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, int var11, double[] var12, int var13, int var14, intW var15, double[] var16, int[] var17, int[] var18, intW var19);

   void dhsein(String var1, String var2, String var3, boolean[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, int var20, intW var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   void dhseqr(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dhseqr(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, intW var19);

   boolean disnan(double var1);

   void dlabad(doubleW var1, doubleW var2);

   void dlabrd(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, double[] var8, double[] var9, double[] var10, int var11, double[] var12, int var13);

   void dlabrd(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20);

   void dlacn2(int var1, double[] var2, double[] var3, int[] var4, doubleW var5, intW var6, int[] var7);

   void dlacn2(int var1, double[] var2, int var3, double[] var4, int var5, int[] var6, int var7, doubleW var8, intW var9, int[] var10, int var11);

   void dlacon(int var1, double[] var2, double[] var3, int[] var4, doubleW var5, intW var6);

   void dlacon(int var1, double[] var2, int var3, double[] var4, int var5, int[] var6, int var7, doubleW var8, intW var9);

   void dlacpy(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7);

   void dlacpy(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9);

   void dladiv(double var1, double var3, double var5, double var7, doubleW var9, doubleW var10);

   void dlae2(double var1, double var3, double var5, doubleW var7, doubleW var8);

   void dlaebz(int var1, int var2, int var3, int var4, int var5, int var6, double var7, double var9, double var11, double[] var13, double[] var14, double[] var15, int[] var16, double[] var17, double[] var18, intW var19, int[] var20, double[] var21, int[] var22, intW var23);

   void dlaebz(int var1, int var2, int var3, int var4, int var5, int var6, double var7, double var9, double var11, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, int[] var19, int var20, double[] var21, int var22, double[] var23, int var24, intW var25, int[] var26, int var27, double[] var28, int var29, int[] var30, int var31, intW var32);

   void dlaed0(int var1, int var2, int var3, double[] var4, double[] var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int[] var11, intW var12);

   void dlaed0(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, intW var18);

   void dlaed1(int var1, double[] var2, double[] var3, int var4, int[] var5, doubleW var6, int var7, double[] var8, int[] var9, intW var10);

   void dlaed1(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, doubleW var9, int var10, double[] var11, int var12, int[] var13, int var14, intW var15);

   void dlaed2(intW var1, int var2, int var3, double[] var4, double[] var5, int var6, int[] var7, doubleW var8, double[] var9, double[] var10, double[] var11, double[] var12, int[] var13, int[] var14, int[] var15, int[] var16, intW var17);

   void dlaed2(intW var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, int[] var9, int var10, doubleW var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   void dlaed3(int var1, int var2, int var3, double[] var4, double[] var5, int var6, double var7, double[] var9, double[] var10, int[] var11, int[] var12, double[] var13, double[] var14, intW var15);

   void dlaed3(int var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double var9, double[] var11, int var12, double[] var13, int var14, int[] var15, int var16, int[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   void dlaed4(int var1, int var2, double[] var3, double[] var4, double[] var5, double var6, doubleW var8, intW var9);

   void dlaed4(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, intW var12);

   void dlaed5(int var1, double[] var2, double[] var3, double[] var4, double var5, doubleW var7);

   void dlaed5(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, doubleW var10);

   void dlaed6(int var1, boolean var2, double var3, double[] var5, double[] var6, double var7, doubleW var9, intW var10);

   void dlaed6(int var1, boolean var2, double var3, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, intW var12);

   void dlaed7(int var1, int var2, int var3, int var4, int var5, int var6, double[] var7, double[] var8, int var9, int[] var10, doubleW var11, int var12, double[] var13, int[] var14, int[] var15, int[] var16, int[] var17, int[] var18, double[] var19, double[] var20, int[] var21, intW var22);

   void dlaed7(int var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, doubleW var14, int var15, double[] var16, int var17, int[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, double[] var30, int var31, int[] var32, int var33, intW var34);

   void dlaed8(int var1, intW var2, int var3, int var4, double[] var5, double[] var6, int var7, int[] var8, doubleW var9, int var10, double[] var11, double[] var12, double[] var13, int var14, double[] var15, int[] var16, intW var17, int[] var18, double[] var19, int[] var20, int[] var21, intW var22);

   void dlaed8(int var1, intW var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, doubleW var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int[] var23, int var24, intW var25, int[] var26, int var27, double[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34);

   void dlaed9(int var1, int var2, int var3, int var4, double[] var5, double[] var6, int var7, double var8, double[] var10, double[] var11, double[] var12, int var13, intW var14);

   void dlaed9(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double var10, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, int var18, intW var19);

   void dlaeda(int var1, int var2, int var3, int var4, int[] var5, int[] var6, int[] var7, int[] var8, double[] var9, double[] var10, int[] var11, double[] var12, double[] var13, intW var14);

   void dlaeda(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, int[] var9, int var10, int[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   void dlaein(boolean var1, boolean var2, int var3, double[] var4, int var5, double var6, double var8, double[] var10, double[] var11, double[] var12, int var13, double[] var14, double var15, double var17, double var19, intW var21);

   void dlaein(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double var7, double var9, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double var20, double var22, double var24, intW var26);

   void dlaev2(double var1, double var3, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   void dlaexc(boolean var1, int var2, double[] var3, int var4, double[] var5, int var6, int var7, int var8, int var9, double[] var10, intW var11);

   void dlaexc(boolean var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, int var9, int var10, int var11, double[] var12, int var13, intW var14);

   void dlag2(double[] var1, int var2, double[] var3, int var4, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11);

   void dlag2(double[] var1, int var2, int var3, double[] var4, int var5, int var6, double var7, doubleW var9, doubleW var10, doubleW var11, doubleW var12, doubleW var13);

   void dlag2s(int var1, int var2, double[] var3, int var4, float[] var5, int var6, intW var7);

   void dlag2s(int var1, int var2, double[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9);

   void dlags2(boolean var1, double var2, double var4, double var6, double var8, double var10, double var12, doubleW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19);

   void dlagtf(int var1, double[] var2, double var3, double[] var5, double[] var6, double var7, double[] var9, int[] var10, intW var11);

   void dlagtf(int var1, double[] var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double var10, double[] var12, int var13, int[] var14, int var15, intW var16);

   void dlagtm(String var1, int var2, int var3, double var4, double[] var6, double[] var7, double[] var8, double[] var9, int var10, double var11, double[] var13, int var14);

   void dlagtm(String var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double var15, double[] var17, int var18, int var19);

   void dlagts(int var1, int var2, double[] var3, double[] var4, double[] var5, double[] var6, int[] var7, double[] var8, doubleW var9, intW var10);

   void dlagts(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int var12, double[] var13, int var14, doubleW var15, intW var16);

   void dlagv2(double[] var1, int var2, double[] var3, int var4, double[] var5, double[] var6, double[] var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11);

   void dlagv2(double[] var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, doubleW var13, doubleW var14, doubleW var15, doubleW var16);

   void dlahqr(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, int var11, double[] var12, int var13, intW var14);

   void dlahqr(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   void dlahr2(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, double[] var9, int var10);

   void dlahr2(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   void dlahrd(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, double[] var9, int var10);

   void dlahrd(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14);

   void dlaic1(int var1, int var2, double[] var3, double var4, double[] var6, double var7, doubleW var9, doubleW var10, doubleW var11);

   void dlaic1(int var1, int var2, double[] var3, int var4, double var5, double[] var7, int var8, double var9, doubleW var11, doubleW var12, doubleW var13);

   boolean dlaisnan(double var1, double var3);

   void dlaln2(boolean var1, int var2, int var3, double var4, double var6, double[] var8, int var9, double var10, double var12, double[] var14, int var15, double var16, double var18, double[] var20, int var21, doubleW var22, doubleW var23, intW var24);

   void dlaln2(boolean var1, int var2, int var3, double var4, double var6, double[] var8, int var9, int var10, double var11, double var13, double[] var15, int var16, int var17, double var18, double var20, double[] var22, int var23, int var24, doubleW var25, doubleW var26, intW var27);

   void dlals0(int var1, int var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int[] var10, int var11, int[] var12, int var13, double[] var14, int var15, double[] var16, double[] var17, double[] var18, double[] var19, int var20, double var21, double var23, double[] var25, intW var26);

   void dlals0(int var1, int var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, int var14, int[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int var29, double var30, double var32, double[] var34, int var35, intW var36);

   void dlalsa(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int[] var12, double[] var13, double[] var14, double[] var15, double[] var16, int[] var17, int[] var18, int var19, int[] var20, double[] var21, double[] var22, double[] var23, double[] var24, int[] var25, intW var26);

   void dlalsa(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int[] var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int var30, int[] var31, int var32, double[] var33, int var34, double[] var35, int var36, double[] var37, int var38, double[] var39, int var40, int[] var41, int var42, intW var43);

   void dlalsd(String var1, int var2, int var3, int var4, double[] var5, double[] var6, double[] var7, int var8, double var9, intW var11, double[] var12, int[] var13, intW var14);

   void dlalsd(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double var12, intW var14, double[] var15, int var16, int[] var17, int var18, intW var19);

   void dlamrg(int var1, int var2, double[] var3, int var4, int var5, int[] var6);

   void dlamrg(int var1, int var2, double[] var3, int var4, int var5, int var6, int[] var7, int var8);

   int dlaneg(int var1, double[] var2, double[] var3, double var4, double var6, int var8);

   int dlaneg(int var1, double[] var2, int var3, double[] var4, int var5, double var6, double var8, int var10);

   double dlangb(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7);

   double dlangb(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   double dlange(String var1, int var2, int var3, double[] var4, int var5, double[] var6);

   double dlange(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8);

   double dlangt(String var1, int var2, double[] var3, double[] var4, double[] var5);

   double dlangt(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8);

   double dlanhs(String var1, int var2, double[] var3, int var4, double[] var5);

   double dlanhs(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7);

   double dlansb(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7);

   double dlansb(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9);

   double dlansp(String var1, String var2, int var3, double[] var4, double[] var5);

   double dlansp(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7);

   double dlanst(String var1, int var2, double[] var3, double[] var4);

   double dlanst(String var1, int var2, double[] var3, int var4, double[] var5, int var6);

   double dlansy(String var1, String var2, int var3, double[] var4, int var5, double[] var6);

   double dlansy(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8);

   double dlantb(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8);

   double dlantb(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10);

   double dlantp(String var1, String var2, String var3, int var4, double[] var5, double[] var6);

   double dlantp(String var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8);

   double dlantr(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8);

   double dlantr(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10);

   void dlanv2(doubleW var1, doubleW var2, doubleW var3, doubleW var4, doubleW var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   void dlapll(int var1, double[] var2, int var3, double[] var4, int var5, doubleW var6);

   void dlapll(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, doubleW var8);

   void dlapmt(boolean var1, int var2, int var3, double[] var4, int var5, int[] var6);

   void dlapmt(boolean var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8);

   double dlapy2(double var1, double var3);

   double dlapy3(double var1, double var3, double var5);

   void dlaqgb(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, double var9, double var11, double var13, StringW var15);

   void dlaqgb(int var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double var12, double var14, double var16, StringW var18);

   void dlaqge(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, double var7, double var9, double var11, StringW var13);

   void dlaqge(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, double var14, StringW var16);

   void dlaqp2(int var1, int var2, int var3, double[] var4, int var5, int[] var6, double[] var7, double[] var8, double[] var9, double[] var10);

   void dlaqp2(int var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16);

   void dlaqps(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, int[] var8, double[] var9, double[] var10, double[] var11, double[] var12, double[] var13, int var14);

   void dlaqps(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, int var8, int[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21);

   void dlaqr0(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   void dlaqr0(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, intW var21);

   void dlaqr1(int var1, double[] var2, int var3, double var4, double var6, double var8, double var10, double[] var12);

   void dlaqr1(int var1, double[] var2, int var3, int var4, double var5, double var7, double var9, double var11, double[] var13, int var14);

   void dlaqr2(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, double[] var11, int var12, intW var13, intW var14, double[] var15, double[] var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, double[] var25, int var26);

   void dlaqr2(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, int var11, double[] var12, int var13, int var14, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, int var24, double[] var25, int var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, int var34);

   void dlaqr3(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, double[] var11, int var12, intW var13, intW var14, double[] var15, double[] var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, double[] var25, int var26);

   void dlaqr3(boolean var1, boolean var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, int var10, int var11, double[] var12, int var13, int var14, intW var15, intW var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, int var24, double[] var25, int var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, int var34);

   void dlaqr4(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   void dlaqr4(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, intW var21);

   void dlaqr5(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double[] var8, double[] var9, double[] var10, int var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25);

   void dlaqr5(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, double[] var23, int var24, int var25, int var26, double[] var27, int var28, int var29, int var30, double[] var31, int var32, int var33);

   void dlaqsb(String var1, int var2, int var3, double[] var4, int var5, double[] var6, double var7, double var9, StringW var11);

   void dlaqsb(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double var9, double var11, StringW var13);

   void dlaqsp(String var1, int var2, double[] var3, double[] var4, double var5, double var7, StringW var9);

   void dlaqsp(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double var7, double var9, StringW var11);

   void dlaqsy(String var1, int var2, double[] var3, int var4, double[] var5, double var6, double var8, StringW var10);

   void dlaqsy(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double var8, double var10, StringW var12);

   void dlaqtr(boolean var1, boolean var2, int var3, double[] var4, int var5, double[] var6, double var7, doubleW var9, double[] var10, double[] var11, intW var12);

   void dlaqtr(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double var9, doubleW var11, double[] var12, int var13, double[] var14, int var15, intW var16);

   void dlar1v(int var1, int var2, int var3, double var4, double[] var6, double[] var7, double[] var8, double[] var9, double var10, double var12, double[] var14, boolean var15, intW var16, doubleW var17, doubleW var18, intW var19, int[] var20, doubleW var21, doubleW var22, doubleW var23, double[] var24);

   void dlar1v(int var1, int var2, int var3, double var4, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double var14, double var16, double[] var18, int var19, boolean var20, intW var21, doubleW var22, doubleW var23, intW var24, int[] var25, int var26, doubleW var27, doubleW var28, doubleW var29, double[] var30, int var31);

   void dlar2v(int var1, double[] var2, double[] var3, double[] var4, int var5, double[] var6, double[] var7, int var8);

   void dlar2v(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13);

   void dlarf(String var1, int var2, int var3, double[] var4, int var5, double var6, double[] var8, int var9, double[] var10);

   void dlarf(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, double[] var9, int var10, int var11, double[] var12, int var13);

   void dlarfb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15);

   void dlarfb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19);

   void dlarfg(int var1, doubleW var2, double[] var3, int var4, doubleW var5);

   void dlarfg(int var1, doubleW var2, double[] var3, int var4, int var5, doubleW var6);

   void dlarft(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, int var9);

   void dlarft(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   void dlarfx(String var1, int var2, int var3, double[] var4, double var5, double[] var7, int var8, double[] var9);

   void dlarfx(String var1, int var2, int var3, double[] var4, int var5, double var6, double[] var8, int var9, int var10, double[] var11, int var12);

   void dlargv(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7);

   void dlargv(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10);

   void dlarnv(int var1, int[] var2, int var3, double[] var4);

   void dlarnv(int var1, int[] var2, int var3, int var4, double[] var5, int var6);

   void dlarra(int var1, double[] var2, double[] var3, double[] var4, double var5, double var7, intW var9, int[] var10, intW var11);

   void dlarra(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, intW var12, int[] var13, int var14, intW var15);

   void dlarrb(int var1, double[] var2, double[] var3, int var4, int var5, double var6, double var8, int var10, double[] var11, double[] var12, double[] var13, double[] var14, int[] var15, double var16, double var18, int var20, intW var21);

   void dlarrb(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int var7, double var8, double var10, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int var22, double var23, double var25, int var27, intW var28);

   void dlarrc(String var1, int var2, double var3, double var5, double[] var7, double[] var8, double var9, intW var11, intW var12, intW var13, intW var14);

   void dlarrc(String var1, int var2, double var3, double var5, double[] var7, int var8, double[] var9, int var10, double var11, intW var13, intW var14, intW var15, intW var16);

   void dlarrd(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double[] var10, double var11, double[] var13, double[] var14, double[] var15, double var16, int var18, int[] var19, intW var20, double[] var21, double[] var22, doubleW var23, doubleW var24, int[] var25, int[] var26, double[] var27, int[] var28, intW var29);

   void dlarrd(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, double var12, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double var20, int var22, int[] var23, int var24, intW var25, double[] var26, int var27, double[] var28, int var29, doubleW var30, doubleW var31, int[] var32, int var33, int[] var34, int var35, double[] var36, int var37, int[] var38, int var39, intW var40);

   void dlarre(String var1, int var2, doubleW var3, doubleW var4, int var5, int var6, double[] var7, double[] var8, double[] var9, double var10, double var12, double var14, intW var16, int[] var17, intW var18, double[] var19, double[] var20, double[] var21, int[] var22, int[] var23, double[] var24, doubleW var25, double[] var26, int[] var27, intW var28);

   void dlarre(String var1, int var2, doubleW var3, doubleW var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double var13, double var15, double var17, intW var19, int[] var20, int var21, intW var22, double[] var23, int var24, double[] var25, int var26, double[] var27, int var28, int[] var29, int var30, int[] var31, int var32, double[] var33, int var34, doubleW var35, double[] var36, int var37, int[] var38, int var39, intW var40);

   void dlarrf(int var1, double[] var2, double[] var3, double[] var4, int var5, int var6, double[] var7, double[] var8, double[] var9, double var10, double var12, double var14, double var16, doubleW var18, double[] var19, double[] var20, double[] var21, intW var22);

   void dlarrf(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double var16, double var18, double var20, double var22, doubleW var24, double[] var25, int var26, double[] var27, int var28, double[] var29, int var30, intW var31);

   void dlarrj(int var1, double[] var2, double[] var3, int var4, int var5, double var6, int var8, double[] var9, double[] var10, double[] var11, int[] var12, double var13, double var15, intW var17);

   void dlarrj(int var1, double[] var2, int var3, double[] var4, int var5, int var6, int var7, double var8, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int var18, double var19, double var21, intW var23);

   void dlarrk(int var1, int var2, double var3, double var5, double[] var7, double[] var8, double var9, double var11, doubleW var13, doubleW var14, intW var15);

   void dlarrk(int var1, int var2, double var3, double var5, double[] var7, int var8, double[] var9, int var10, double var11, double var13, doubleW var15, doubleW var16, intW var17);

   void dlarrr(int var1, double[] var2, double[] var3, intW var4);

   void dlarrr(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   void dlarrv(int var1, double var2, double var4, double[] var6, double[] var7, double var8, int[] var10, int var11, int var12, int var13, double var14, doubleW var16, doubleW var17, double[] var18, double[] var19, double[] var20, int[] var21, int[] var22, double[] var23, double[] var24, int var25, int[] var26, double[] var27, int[] var28, intW var29);

   void dlarrv(int var1, double var2, double var4, double[] var6, int var7, double[] var8, int var9, double var10, int[] var12, int var13, int var14, int var15, int var16, double var17, doubleW var19, doubleW var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int[] var27, int var28, int[] var29, int var30, double[] var31, int var32, double[] var33, int var34, int var35, int[] var36, int var37, double[] var38, int var39, int[] var40, int var41, intW var42);

   void dlartg(double var1, double var3, doubleW var5, doubleW var6, doubleW var7);

   void dlartv(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8);

   void dlartv(int var1, double[] var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   void dlaruv(int[] var1, int var2, double[] var3);

   void dlaruv(int[] var1, int var2, int var3, double[] var4, int var5);

   void dlarz(String var1, int var2, int var3, int var4, double[] var5, int var6, double var7, double[] var9, int var10, double[] var11);

   void dlarz(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double var8, double[] var10, int var11, int var12, double[] var13, int var14);

   void dlarzb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16);

   void dlarzb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20);

   void dlarzt(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, int var9);

   void dlarzt(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   void dlas2(double var1, double var3, double var5, doubleW var7, doubleW var8);

   void dlascl(String var1, int var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, intW var12);

   void dlascl(String var1, int var2, int var3, double var4, double var6, int var8, int var9, double[] var10, int var11, int var12, intW var13);

   void dlasd0(int var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, int var8, int var9, int[] var10, double[] var11, intW var12);

   void dlasd0(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int var13, int[] var14, int var15, double[] var16, int var17, intW var18);

   void dlasd1(int var1, int var2, int var3, double[] var4, doubleW var5, doubleW var6, double[] var7, int var8, double[] var9, int var10, int[] var11, int[] var12, double[] var13, intW var14);

   void dlasd1(int var1, int var2, int var3, double[] var4, int var5, doubleW var6, doubleW var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, int[] var14, int var15, int[] var16, int var17, double[] var18, int var19, intW var20);

   void dlasd2(int var1, int var2, int var3, intW var4, double[] var5, double[] var6, double var7, double var9, double[] var11, int var12, double[] var13, int var14, double[] var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int[] var21, int[] var22, int[] var23, int[] var24, intW var25);

   void dlasd2(int var1, int var2, int var3, intW var4, double[] var5, int var6, double[] var7, int var8, double var9, double var11, double[] var13, int var14, int var15, double[] var16, int var17, int var18, double[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int[] var29, int var30, int[] var31, int var32, int[] var33, int var34, int[] var35, int var36, intW var37);

   void dlasd3(int var1, int var2, int var3, int var4, double[] var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, int[] var17, int[] var18, double[] var19, intW var20);

   void dlasd3(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, double[] var28, int var29, intW var30);

   void dlasd4(int var1, int var2, double[] var3, double[] var4, double[] var5, double var6, doubleW var8, double[] var9, intW var10);

   void dlasd4(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double var9, doubleW var11, double[] var12, int var13, intW var14);

   void dlasd5(int var1, double[] var2, double[] var3, double[] var4, double var5, doubleW var7, double[] var8);

   void dlasd5(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, doubleW var10, double[] var11, int var12);

   void dlasd6(int var1, int var2, int var3, int var4, double[] var5, double[] var6, double[] var7, doubleW var8, doubleW var9, int[] var10, int[] var11, intW var12, int[] var13, int var14, double[] var15, int var16, double[] var17, double[] var18, double[] var19, double[] var20, intW var21, doubleW var22, doubleW var23, double[] var24, int[] var25, intW var26);

   void dlasd6(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, doubleW var11, doubleW var12, int[] var13, int var14, int[] var15, int var16, intW var17, int[] var18, int var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, double[] var26, int var27, double[] var28, int var29, double[] var30, int var31, intW var32, doubleW var33, doubleW var34, double[] var35, int var36, int[] var37, int var38, intW var39);

   void dlasd7(int var1, int var2, int var3, int var4, intW var5, double[] var6, double[] var7, double[] var8, double[] var9, double[] var10, double[] var11, double[] var12, double var13, double var15, double[] var17, int[] var18, int[] var19, int[] var20, int[] var21, intW var22, int[] var23, int var24, double[] var25, int var26, doubleW var27, doubleW var28, intW var29);

   void dlasd7(int var1, int var2, int var3, int var4, intW var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double var20, double var22, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34, int[] var35, int var36, int var37, double[] var38, int var39, int var40, doubleW var41, doubleW var42, intW var43);

   void dlasd8(int var1, int var2, double[] var3, double[] var4, double[] var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, double[] var11, intW var12);

   void dlasd8(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, intW var20);

   void dlasda(int var1, int var2, int var3, int var4, double[] var5, double[] var6, double[] var7, int var8, double[] var9, int[] var10, double[] var11, double[] var12, double[] var13, double[] var14, int[] var15, int[] var16, int var17, int[] var18, double[] var19, double[] var20, double[] var21, double[] var22, int[] var23, intW var24);

   void dlasda(int var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int[] var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int var28, int[] var29, int var30, double[] var31, int var32, double[] var33, int var34, double[] var35, int var36, double[] var37, int var38, int[] var39, int var40, intW var41);

   void dlasdq(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, double[] var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, intW var16);

   void dlasdq(String var1, int var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, intW var22);

   void dlasdt(int var1, intW var2, intW var3, int[] var4, int[] var5, int[] var6, int var7);

   void dlasdt(int var1, intW var2, intW var3, int[] var4, int var5, int[] var6, int var7, int[] var8, int var9, int var10);

   void dlaset(String var1, int var2, int var3, double var4, double var6, double[] var8, int var9);

   void dlaset(String var1, int var2, int var3, double var4, double var6, double[] var8, int var9, int var10);

   void dlasq1(int var1, double[] var2, double[] var3, double[] var4, intW var5);

   void dlasq1(int var1, double[] var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dlasq2(int var1, double[] var2, intW var3);

   void dlasq2(int var1, double[] var2, int var3, intW var4);

   void dlasq3(int var1, intW var2, double[] var3, int var4, doubleW var5, doubleW var6, doubleW var7, doubleW var8, intW var9, intW var10, intW var11, boolean var12);

   void dlasq3(int var1, intW var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, intW var10, intW var11, intW var12, boolean var13);

   void dlasq4(int var1, int var2, double[] var3, int var4, int var5, double var6, double var8, double var10, double var12, double var14, double var16, doubleW var18, intW var19);

   void dlasq4(int var1, int var2, double[] var3, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, double var17, doubleW var19, intW var20);

   void dlasq5(int var1, int var2, double[] var3, int var4, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12, boolean var13);

   void dlasq5(int var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12, doubleW var13, boolean var14);

   void dlasq6(int var1, int var2, double[] var3, int var4, doubleW var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10);

   void dlasq6(int var1, int var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11);

   void dlasr(String var1, String var2, String var3, int var4, int var5, double[] var6, double[] var7, double[] var8, int var9);

   void dlasr(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12);

   void dlasrt(String var1, int var2, double[] var3, intW var4);

   void dlasrt(String var1, int var2, double[] var3, int var4, intW var5);

   void dlassq(int var1, double[] var2, int var3, doubleW var4, doubleW var5);

   void dlassq(int var1, double[] var2, int var3, int var4, doubleW var5, doubleW var6);

   void dlasv2(double var1, double var3, double var5, doubleW var7, doubleW var8, doubleW var9, doubleW var10, doubleW var11, doubleW var12);

   void dlaswp(int var1, double[] var2, int var3, int var4, int var5, int[] var6, int var7);

   void dlaswp(int var1, double[] var2, int var3, int var4, int var5, int var6, int[] var7, int var8, int var9);

   void dlasy2(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, double[] var13, int var14, doubleW var15, intW var16);

   void dlasy2(boolean var1, boolean var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, doubleW var15, double[] var16, int var17, int var18, doubleW var19, intW var20);

   void dlasyf(String var1, int var2, int var3, intW var4, double[] var5, int var6, int[] var7, double[] var8, int var9, intW var10);

   void dlasyf(String var1, int var2, int var3, intW var4, double[] var5, int var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   void dlatbs(String var1, String var2, String var3, String var4, int var5, int var6, double[] var7, int var8, double[] var9, doubleW var10, double[] var11, intW var12);

   void dlatbs(String var1, String var2, String var3, String var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, doubleW var12, double[] var13, int var14, intW var15);

   void dlatdf(int var1, int var2, double[] var3, int var4, double[] var5, doubleW var6, doubleW var7, int[] var8, int[] var9);

   void dlatdf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, doubleW var8, doubleW var9, int[] var10, int var11, int[] var12, int var13);

   void dlatps(String var1, String var2, String var3, String var4, int var5, double[] var6, double[] var7, doubleW var8, double[] var9, intW var10);

   void dlatps(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, doubleW var10, double[] var11, int var12, intW var13);

   void dlatrd(String var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, double[] var8, int var9);

   void dlatrd(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13);

   void dlatrs(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, doubleW var9, double[] var10, intW var11);

   void dlatrs(String var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, doubleW var11, double[] var12, int var13, intW var14);

   void dlatrz(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7);

   void dlatrz(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10);

   void dlatzm(String var1, int var2, int var3, double[] var4, int var5, double var6, double[] var8, double[] var9, int var10, double[] var11);

   void dlatzm(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15);

   void dlauu2(String var1, int var2, double[] var3, int var4, intW var5);

   void dlauu2(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   void dlauum(String var1, int var2, double[] var3, int var4, intW var5);

   void dlauum(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   void dlazq3(int var1, intW var2, double[] var3, int var4, doubleW var5, doubleW var6, doubleW var7, doubleW var8, intW var9, intW var10, intW var11, boolean var12, intW var13, doubleW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19);

   void dlazq3(int var1, intW var2, double[] var3, int var4, int var5, doubleW var6, doubleW var7, doubleW var8, doubleW var9, intW var10, intW var11, intW var12, boolean var13, intW var14, doubleW var15, doubleW var16, doubleW var17, doubleW var18, doubleW var19, doubleW var20);

   void dlazq4(int var1, int var2, double[] var3, int var4, int var5, double var6, double var8, double var10, double var12, double var14, double var16, doubleW var18, intW var19, doubleW var20);

   void dlazq4(int var1, int var2, double[] var3, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, double var17, doubleW var19, intW var20, doubleW var21);

   void dopgtr(String var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, intW var8);

   void dopgtr(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   void dopmtr(String var1, String var2, String var3, int var4, int var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, intW var11);

   void dopmtr(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, intW var15);

   void dorg2l(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, intW var8);

   void dorg2l(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dorg2r(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, intW var8);

   void dorg2r(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dorgbr(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, int var9, intW var10);

   void dorgbr(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, intW var13);

   void dorghr(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dorghr(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dorgl2(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, intW var8);

   void dorgl2(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dorglq(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dorglq(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dorgql(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dorgql(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dorgqr(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dorgqr(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dorgr2(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, intW var8);

   void dorgr2(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dorgrq(int var1, int var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dorgrq(int var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dorgtr(String var1, int var2, double[] var3, int var4, double[] var5, double[] var6, int var7, intW var8);

   void dorgtr(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dorm2l(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, intW var12);

   void dorm2l(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   void dorm2r(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, intW var12);

   void dorm2r(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   void dormbr(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dormbr(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   void dormhr(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dormhr(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   void dorml2(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, intW var12);

   void dorml2(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   void dormlq(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   void dormlq(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dormql(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   void dormql(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dormqr(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   void dormqr(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dormr2(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, intW var12);

   void dormr2(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   void dormr3(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, double[] var12, intW var13);

   void dormr3(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   void dormrq(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   void dormrq(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dormrz(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, double[] var12, int var13, intW var14);

   void dormrz(String var1, String var2, int var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, intW var18);

   void dormtr(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, double[] var9, int var10, double[] var11, int var12, intW var13);

   void dormtr(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, intW var17);

   void dpbcon(String var1, int var2, int var3, double[] var4, int var5, double var6, doubleW var8, double[] var9, int[] var10, intW var11);

   void dpbcon(String var1, int var2, int var3, double[] var4, int var5, int var6, double var7, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   void dpbequ(String var1, int var2, int var3, double[] var4, int var5, double[] var6, doubleW var7, doubleW var8, intW var9);

   void dpbequ(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, doubleW var9, doubleW var10, intW var11);

   void dpbrfs(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, double[] var14, double[] var15, int[] var16, intW var17);

   void dpbrfs(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, int[] var23, int var24, intW var25);

   void dpbstf(String var1, int var2, int var3, double[] var4, int var5, intW var6);

   void dpbstf(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   void dpbsv(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, intW var9);

   void dpbsv(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dpbsvx(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, StringW var10, double[] var11, double[] var12, int var13, double[] var14, int var15, doubleW var16, double[] var17, double[] var18, double[] var19, int[] var20, intW var21);

   void dpbsvx(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, StringW var12, double[] var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, int var20, doubleW var21, double[] var22, int var23, double[] var24, int var25, double[] var26, int var27, int[] var28, int var29, intW var30);

   void dpbtf2(String var1, int var2, int var3, double[] var4, int var5, intW var6);

   void dpbtf2(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   void dpbtrf(String var1, int var2, int var3, double[] var4, int var5, intW var6);

   void dpbtrf(String var1, int var2, int var3, double[] var4, int var5, int var6, intW var7);

   void dpbtrs(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, intW var9);

   void dpbtrs(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dpocon(String var1, int var2, double[] var3, int var4, double var5, doubleW var7, double[] var8, int[] var9, intW var10);

   void dpocon(String var1, int var2, double[] var3, int var4, int var5, double var6, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   void dpoequ(int var1, double[] var2, int var3, double[] var4, doubleW var5, doubleW var6, intW var7);

   void dpoequ(int var1, double[] var2, int var3, int var4, double[] var5, int var6, doubleW var7, doubleW var8, intW var9);

   void dporfs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, double[] var13, double[] var14, int[] var15, intW var16);

   void dporfs(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   void dposv(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dposv(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dposvx(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, StringW var9, double[] var10, double[] var11, int var12, double[] var13, int var14, doubleW var15, double[] var16, double[] var17, double[] var18, int[] var19, intW var20);

   void dposvx(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, StringW var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, doubleW var20, double[] var21, int var22, double[] var23, int var24, double[] var25, int var26, int[] var27, int var28, intW var29);

   void dpotf2(String var1, int var2, double[] var3, int var4, intW var5);

   void dpotf2(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   void dpotrf(String var1, int var2, double[] var3, int var4, intW var5);

   void dpotrf(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   void dpotri(String var1, int var2, double[] var3, int var4, intW var5);

   void dpotri(String var1, int var2, double[] var3, int var4, int var5, intW var6);

   void dpotrs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dpotrs(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dppcon(String var1, int var2, double[] var3, double var4, doubleW var6, double[] var7, int[] var8, intW var9);

   void dppcon(String var1, int var2, double[] var3, int var4, double var5, doubleW var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   void dppequ(String var1, int var2, double[] var3, double[] var4, doubleW var5, doubleW var6, intW var7);

   void dppequ(String var1, int var2, double[] var3, int var4, double[] var5, int var6, doubleW var7, doubleW var8, intW var9);

   void dpprfs(String var1, int var2, int var3, double[] var4, double[] var5, double[] var6, int var7, double[] var8, int var9, double[] var10, double[] var11, double[] var12, int[] var13, intW var14);

   void dpprfs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   void dppsv(String var1, int var2, int var3, double[] var4, double[] var5, int var6, intW var7);

   void dppsv(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, intW var9);

   void dppsvx(String var1, String var2, int var3, int var4, double[] var5, double[] var6, StringW var7, double[] var8, double[] var9, int var10, double[] var11, int var12, doubleW var13, double[] var14, double[] var15, double[] var16, int[] var17, intW var18);

   void dppsvx(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, StringW var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, doubleW var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, int[] var25, int var26, intW var27);

   void dpptrf(String var1, int var2, double[] var3, intW var4);

   void dpptrf(String var1, int var2, double[] var3, int var4, intW var5);

   void dpptri(String var1, int var2, double[] var3, intW var4);

   void dpptri(String var1, int var2, double[] var3, int var4, intW var5);

   void dpptrs(String var1, int var2, int var3, double[] var4, double[] var5, int var6, intW var7);

   void dpptrs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int var8, intW var9);

   void dptcon(int var1, double[] var2, double[] var3, double var4, doubleW var6, double[] var7, intW var8);

   void dptcon(int var1, double[] var2, int var3, double[] var4, int var5, double var6, doubleW var8, double[] var9, int var10, intW var11);

   void dpteqr(String var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, intW var8);

   void dpteqr(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   void dptrfs(int var1, int var2, double[] var3, double[] var4, double[] var5, double[] var6, double[] var7, int var8, double[] var9, int var10, double[] var11, double[] var12, double[] var13, intW var14);

   void dptrfs(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, double[] var19, int var20, double[] var21, int var22, intW var23);

   void dptsv(int var1, int var2, double[] var3, double[] var4, double[] var5, int var6, intW var7);

   void dptsv(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dptsvx(String var1, int var2, int var3, double[] var4, double[] var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, double[] var13, double[] var14, double[] var15, intW var16);

   void dptsvx(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, doubleW var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, intW var25);

   void dpttrf(int var1, double[] var2, double[] var3, intW var4);

   void dpttrf(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   void dpttrs(int var1, int var2, double[] var3, double[] var4, double[] var5, int var6, intW var7);

   void dpttrs(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dptts2(int var1, int var2, double[] var3, double[] var4, double[] var5, int var6);

   void dptts2(int var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9);

   void drscl(int var1, double var2, double[] var4, int var5);

   void drscl(int var1, double var2, double[] var4, int var5, int var6);

   void dsbev(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, int var9, double[] var10, intW var11);

   void dsbev(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, intW var15);

   void dsbevd(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   void dsbevd(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   void dsbevx(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, int var14, int var15, double var16, intW var18, double[] var19, double[] var20, int var21, double[] var22, int[] var23, int[] var24, intW var25);

   void dsbevx(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double var14, int var16, int var17, double var18, intW var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int[] var28, int var29, int[] var30, int var31, intW var32);

   void dsbgst(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, intW var13);

   void dsbgst(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   void dsbgv(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, double[] var11, int var12, double[] var13, intW var14);

   void dsbgv(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, intW var19);

   void dsbgvd(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, double[] var11, int var12, double[] var13, int var14, int[] var15, int var16, intW var17);

   void dsbgvd(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, int[] var20, int var21, int var22, intW var23);

   void dsbgvx(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double var13, double var15, int var17, int var18, double var19, intW var21, double[] var22, double[] var23, int var24, double[] var25, int[] var26, int[] var27, intW var28);

   void dsbgvx(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double var16, double var18, int var20, int var21, double var22, intW var24, double[] var25, int var26, double[] var27, int var28, int var29, double[] var30, int var31, int[] var32, int var33, int[] var34, int var35, intW var36);

   void dsbtrd(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, double[] var8, double[] var9, int var10, double[] var11, intW var12);

   void dsbtrd(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, intW var17);

   void dsgesv(int var1, int var2, double[] var3, int var4, int[] var5, double[] var6, int var7, double[] var8, int var9, double[] var10, float[] var11, intW var12, intW var13);

   void dsgesv(int var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, float[] var16, int var17, intW var18, intW var19);

   void dspcon(String var1, int var2, double[] var3, int[] var4, double var5, doubleW var7, double[] var8, int[] var9, intW var10);

   void dspcon(String var1, int var2, double[] var3, int var4, int[] var5, int var6, double var7, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   void dspev(String var1, String var2, int var3, double[] var4, double[] var5, double[] var6, int var7, double[] var8, intW var9);

   void dspev(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, intW var13);

   void dspevd(String var1, String var2, int var3, double[] var4, double[] var5, double[] var6, int var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   void dspevd(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, int[] var14, int var15, int var16, intW var17);

   void dspevx(String var1, String var2, String var3, int var4, double[] var5, double var6, double var8, int var10, int var11, double var12, intW var14, double[] var15, double[] var16, int var17, double[] var18, int[] var19, int[] var20, intW var21);

   void dspevx(String var1, String var2, String var3, int var4, double[] var5, int var6, double var7, double var9, int var11, int var12, double var13, intW var15, double[] var16, int var17, double[] var18, int var19, int var20, double[] var21, int var22, int[] var23, int var24, int[] var25, int var26, intW var27);

   void dspgst(int var1, String var2, int var3, double[] var4, double[] var5, intW var6);

   void dspgst(int var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dspgv(int var1, String var2, String var3, int var4, double[] var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, intW var11);

   void dspgv(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, intW var16);

   void dspgvd(int var1, String var2, String var3, int var4, double[] var5, double[] var6, double[] var7, double[] var8, int var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   void dspgvd(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, int[] var17, int var18, int var19, intW var20);

   void dspgvx(int var1, String var2, String var3, String var4, int var5, double[] var6, double[] var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, double[] var18, int var19, double[] var20, int[] var21, int[] var22, intW var23);

   void dspgvx(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, int var14, int var15, double var16, intW var18, double[] var19, int var20, double[] var21, int var22, int var23, double[] var24, int var25, int[] var26, int var27, int[] var28, int var29, intW var30);

   void dsprfs(String var1, int var2, int var3, double[] var4, double[] var5, int[] var6, double[] var7, int var8, double[] var9, int var10, double[] var11, double[] var12, double[] var13, int[] var14, intW var15);

   void dsprfs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   void dspsv(String var1, int var2, int var3, double[] var4, int[] var5, double[] var6, int var7, intW var8);

   void dspsv(String var1, int var2, int var3, double[] var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dspsvx(String var1, String var2, int var3, int var4, double[] var5, double[] var6, int[] var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, double[] var13, double[] var14, double[] var15, int[] var16, intW var17);

   void dspsvx(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int[] var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, doubleW var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   void dsptrd(String var1, int var2, double[] var3, double[] var4, double[] var5, double[] var6, intW var7);

   void dsptrd(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dsptrf(String var1, int var2, double[] var3, int[] var4, intW var5);

   void dsptrf(String var1, int var2, double[] var3, int var4, int[] var5, int var6, intW var7);

   void dsptri(String var1, int var2, double[] var3, int[] var4, double[] var5, intW var6);

   void dsptri(String var1, int var2, double[] var3, int var4, int[] var5, int var6, double[] var7, int var8, intW var9);

   void dsptrs(String var1, int var2, int var3, double[] var4, int[] var5, double[] var6, int var7, intW var8);

   void dsptrs(String var1, int var2, int var3, double[] var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dstebz(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double var10, double[] var12, double[] var13, intW var14, intW var15, double[] var16, int[] var17, int[] var18, double[] var19, int[] var20, intW var21);

   void dstebz(String var1, String var2, int var3, double var4, double var6, int var8, int var9, double var10, double[] var12, int var13, double[] var14, int var15, intW var16, intW var17, double[] var18, int var19, int[] var20, int var21, int[] var22, int var23, double[] var24, int var25, int[] var26, int var27, intW var28);

   void dstedc(String var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, int var8, int[] var9, int var10, intW var11);

   void dstedc(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   void dstegr(String var1, String var2, int var3, double[] var4, double[] var5, double var6, double var8, int var10, int var11, double var12, intW var14, double[] var15, double[] var16, int var17, int[] var18, double[] var19, int var20, int[] var21, int var22, intW var23);

   void dstegr(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   void dstein(int var1, double[] var2, double[] var3, int var4, double[] var5, int[] var6, int[] var7, double[] var8, int var9, double[] var10, int[] var11, int[] var12, intW var13);

   void dstein(int var1, double[] var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int[] var9, int var10, int[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int[] var18, int var19, int[] var20, int var21, intW var22);

   void dstemr(String var1, String var2, int var3, double[] var4, double[] var5, double var6, double var8, int var10, int var11, intW var12, double[] var13, double[] var14, int var15, int var16, int[] var17, booleanW var18, double[] var19, int var20, int[] var21, int var22, intW var23);

   void dstemr(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, intW var14, double[] var15, int var16, double[] var17, int var18, int var19, int var20, int[] var21, int var22, booleanW var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   void dsteqr(String var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, intW var8);

   void dsteqr(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   void dsterf(int var1, double[] var2, double[] var3, intW var4);

   void dsterf(int var1, double[] var2, int var3, double[] var4, int var5, intW var6);

   void dstev(String var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, intW var8);

   void dstev(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, intW var12);

   void dstevd(String var1, int var2, double[] var3, double[] var4, double[] var5, int var6, double[] var7, int var8, int[] var9, int var10, intW var11);

   void dstevd(String var1, int var2, double[] var3, int var4, double[] var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   void dstevr(String var1, String var2, int var3, double[] var4, double[] var5, double var6, double var8, int var10, int var11, double var12, intW var14, double[] var15, double[] var16, int var17, int[] var18, double[] var19, int var20, int[] var21, int var22, intW var23);

   void dstevr(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   void dstevx(String var1, String var2, int var3, double[] var4, double[] var5, double var6, double var8, int var10, int var11, double var12, intW var14, double[] var15, double[] var16, int var17, double[] var18, int[] var19, int[] var20, intW var21);

   void dstevx(String var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   void dsycon(String var1, int var2, double[] var3, int var4, int[] var5, double var6, doubleW var8, double[] var9, int[] var10, intW var11);

   void dsycon(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double var8, doubleW var10, double[] var11, int var12, int[] var13, int var14, intW var15);

   void dsyev(String var1, String var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dsyev(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dsyevd(String var1, String var2, int var3, double[] var4, int var5, double[] var6, double[] var7, int var8, int[] var9, int var10, intW var11);

   void dsyevd(String var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, int[] var12, int var13, int var14, intW var15);

   void dsyevr(String var1, String var2, String var3, int var4, double[] var5, int var6, double var7, double var9, int var11, int var12, double var13, intW var15, double[] var16, double[] var17, int var18, int[] var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   void dsyevr(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, int[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, int var29, intW var30);

   void dsyevx(String var1, String var2, String var3, int var4, double[] var5, int var6, double var7, double var9, int var11, int var12, double var13, intW var15, double[] var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int[] var22, intW var23);

   void dsyevx(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double var8, double var10, int var12, int var13, double var14, intW var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, int[] var25, int var26, int[] var27, int var28, intW var29);

   void dsygs2(int var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dsygs2(int var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dsygst(int var1, String var2, int var3, double[] var4, int var5, double[] var6, int var7, intW var8);

   void dsygst(int var1, String var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, intW var10);

   void dsygv(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, intW var12);

   void dsygv(int var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, intW var16);

   void dsygvd(int var1, String var2, String var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   void dsygvd(int var1, String var2, String var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, double[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   void dsygvx(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, double[] var8, int var9, double var10, double var12, int var14, int var15, double var16, intW var18, double[] var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int[] var25, intW var26);

   void dsygvx(int var1, String var2, String var3, String var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double var12, double var14, int var16, int var17, double var18, intW var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int var28, int[] var29, int var30, int[] var31, int var32, intW var33);

   void dsyrfs(String var1, int var2, int var3, double[] var4, int var5, double[] var6, int var7, int[] var8, double[] var9, int var10, double[] var11, int var12, double[] var13, double[] var14, double[] var15, int[] var16, intW var17);

   void dsyrfs(String var1, int var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, int[] var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, double[] var22, int var23, int[] var24, int var25, intW var26);

   void dsysv(String var1, int var2, int var3, double[] var4, int var5, int[] var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dsysv(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, intW var15);

   void dsysvx(String var1, String var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, int[] var9, double[] var10, int var11, double[] var12, int var13, doubleW var14, double[] var15, double[] var16, double[] var17, int var18, int[] var19, intW var20);

   void dsysvx(String var1, String var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, int[] var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, int var18, doubleW var19, double[] var20, int var21, double[] var22, int var23, double[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   void dsytd2(String var1, int var2, double[] var3, int var4, double[] var5, double[] var6, double[] var7, intW var8);

   void dsytd2(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12);

   void dsytf2(String var1, int var2, double[] var3, int var4, int[] var5, intW var6);

   void dsytf2(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   void dsytrd(String var1, int var2, double[] var3, int var4, double[] var5, double[] var6, double[] var7, double[] var8, int var9, intW var10);

   void dsytrd(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, int var13, int var14, intW var15);

   void dsytrf(String var1, int var2, double[] var3, int var4, int[] var5, double[] var6, int var7, intW var8);

   void dsytrf(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dsytri(String var1, int var2, double[] var3, int var4, int[] var5, double[] var6, intW var7);

   void dsytri(String var1, int var2, double[] var3, int var4, int var5, int[] var6, int var7, double[] var8, int var9, intW var10);

   void dsytrs(String var1, int var2, int var3, double[] var4, int var5, int[] var6, double[] var7, int var8, intW var9);

   void dsytrs(String var1, int var2, int var3, double[] var4, int var5, int var6, int[] var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dtbcon(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, doubleW var8, double[] var9, int[] var10, intW var11);

   void dtbcon(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, doubleW var9, double[] var10, int var11, int[] var12, int var13, intW var14);

   void dtbrfs(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, double[] var14, double[] var15, int[] var16, intW var17);

   void dtbrfs(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, double[] var16, int var17, double[] var18, int var19, double[] var20, int var21, int[] var22, int var23, intW var24);

   void dtbtrs(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, double[] var9, int var10, intW var11);

   void dtbtrs(String var1, String var2, String var3, int var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, intW var13);

   void dtgevc(String var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, int var13, intW var14, double[] var15, intW var16);

   void dtgevc(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, int var18, intW var19, double[] var20, int var21, intW var22);

   void dtgex2(boolean var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, int var12, int var13, int var14, double[] var15, int var16, intW var17);

   void dtgex2(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, int var16, int var17, int var18, double[] var19, int var20, int var21, intW var22);

   void dtgexc(boolean var1, boolean var2, int var3, double[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, intW var12, intW var13, double[] var14, int var15, intW var16);

   void dtgexc(boolean var1, boolean var2, int var3, double[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, int var15, intW var16, intW var17, double[] var18, int var19, int var20, intW var21);

   void dtgsen(int var1, boolean var2, boolean var3, boolean[] var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, double[] var11, double[] var12, double[] var13, int var14, double[] var15, int var16, intW var17, doubleW var18, doubleW var19, double[] var20, double[] var21, int var22, int[] var23, int var24, intW var25);

   void dtgsen(int var1, boolean var2, boolean var3, boolean[] var4, int var5, int var6, double[] var7, int var8, int var9, double[] var10, int var11, int var12, double[] var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int var21, double[] var22, int var23, int var24, intW var25, doubleW var26, doubleW var27, double[] var28, int var29, double[] var30, int var31, int var32, int[] var33, int var34, int var35, intW var36);

   void dtgsja(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, double[] var9, int var10, double[] var11, int var12, double var13, double var15, double[] var17, double[] var18, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, double[] var25, intW var26, intW var27);

   void dtgsja(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double var15, double var17, double[] var19, int var20, double[] var21, int var22, double[] var23, int var24, int var25, double[] var26, int var27, int var28, double[] var29, int var30, int var31, double[] var32, int var33, intW var34, intW var35);

   void dtgsna(String var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, double[] var14, int var15, intW var16, double[] var17, int var18, int[] var19, intW var20);

   void dtgsna(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, int var17, double[] var18, int var19, double[] var20, int var21, int var22, intW var23, double[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   void dtgsy2(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, doubleW var17, doubleW var18, doubleW var19, int[] var20, intW var21, intW var22);

   void dtgsy2(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, doubleW var23, doubleW var24, doubleW var25, int[] var26, int var27, intW var28, intW var29);

   void dtgsyl(String var1, int var2, int var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, int var12, double[] var13, int var14, double[] var15, int var16, doubleW var17, doubleW var18, double[] var19, int var20, int[] var21, intW var22);

   void dtgsyl(String var1, int var2, int var3, int var4, double[] var5, int var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, int var16, double[] var17, int var18, int var19, double[] var20, int var21, int var22, doubleW var23, doubleW var24, double[] var25, int var26, int var27, int[] var28, int var29, intW var30);

   void dtpcon(String var1, String var2, String var3, int var4, double[] var5, doubleW var6, double[] var7, int[] var8, intW var9);

   void dtpcon(String var1, String var2, String var3, int var4, double[] var5, int var6, doubleW var7, double[] var8, int var9, int[] var10, int var11, intW var12);

   void dtprfs(String var1, String var2, String var3, int var4, int var5, double[] var6, double[] var7, int var8, double[] var9, int var10, double[] var11, double[] var12, double[] var13, int[] var14, intW var15);

   void dtprfs(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, double[] var11, int var12, int var13, double[] var14, int var15, double[] var16, int var17, double[] var18, int var19, int[] var20, int var21, intW var22);

   void dtptri(String var1, String var2, int var3, double[] var4, intW var5);

   void dtptri(String var1, String var2, int var3, double[] var4, int var5, intW var6);

   void dtptrs(String var1, String var2, String var3, int var4, int var5, double[] var6, double[] var7, int var8, intW var9);

   void dtptrs(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   void dtrcon(String var1, String var2, String var3, int var4, double[] var5, int var6, doubleW var7, double[] var8, int[] var9, intW var10);

   void dtrcon(String var1, String var2, String var3, int var4, double[] var5, int var6, int var7, doubleW var8, double[] var9, int var10, int[] var11, int var12, intW var13);

   void dtrevc(String var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, int var11, intW var12, double[] var13, intW var14);

   void dtrevc(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, int var15, intW var16, double[] var17, int var18, intW var19);

   void dtrexc(String var1, int var2, double[] var3, int var4, double[] var5, int var6, intW var7, intW var8, double[] var9, intW var10);

   void dtrexc(String var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9, intW var10, double[] var11, int var12, intW var13);

   void dtrrfs(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, double[] var12, double[] var13, double[] var14, int[] var15, intW var16);

   void dtrrfs(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, double[] var19, int var20, int[] var21, int var22, intW var23);

   void dtrsen(String var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, double[] var10, intW var11, doubleW var12, doubleW var13, double[] var14, int var15, int[] var16, int var17, intW var18);

   void dtrsen(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, double[] var14, int var15, intW var16, doubleW var17, doubleW var18, double[] var19, int var20, int var21, int[] var22, int var23, int var24, intW var25);

   void dtrsna(String var1, String var2, boolean[] var3, int var4, double[] var5, int var6, double[] var7, int var8, double[] var9, int var10, double[] var11, double[] var12, int var13, intW var14, double[] var15, int var16, int[] var17, intW var18);

   void dtrsna(String var1, String var2, boolean[] var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, double[] var15, int var16, double[] var17, int var18, int var19, intW var20, double[] var21, int var22, int var23, int[] var24, int var25, intW var26);

   void dtrsyl(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, double[] var10, int var11, doubleW var12, intW var13);

   void dtrsyl(String var1, String var2, int var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, double[] var12, int var13, int var14, doubleW var15, intW var16);

   void dtrti2(String var1, String var2, int var3, double[] var4, int var5, intW var6);

   void dtrti2(String var1, String var2, int var3, double[] var4, int var5, int var6, intW var7);

   void dtrtri(String var1, String var2, int var3, double[] var4, int var5, intW var6);

   void dtrtri(String var1, String var2, int var3, double[] var4, int var5, int var6, intW var7);

   void dtrtrs(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, intW var10);

   void dtrtrs(String var1, String var2, String var3, int var4, int var5, double[] var6, int var7, int var8, double[] var9, int var10, int var11, intW var12);

   void dtzrqf(int var1, int var2, double[] var3, int var4, double[] var5, intW var6);

   void dtzrqf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, intW var8);

   void dtzrzf(int var1, int var2, double[] var3, int var4, double[] var5, double[] var6, int var7, intW var8);

   void dtzrzf(int var1, int var2, double[] var3, int var4, int var5, double[] var6, int var7, double[] var8, int var9, int var10, intW var11);

   int ieeeck(int var1, float var2, float var3);

   int ilaenv(int var1, String var2, String var3, int var4, int var5, int var6, int var7);

   void ilaver(intW var1, intW var2, intW var3);

   int iparmq(int var1, String var2, String var3, int var4, int var5, int var6, int var7);

   boolean lsamen(int var1, String var2, String var3);

   void sbdsdc(String var1, String var2, int var3, float[] var4, float[] var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int[] var11, float[] var12, int[] var13, intW var14);

   void sbdsdc(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   void sbdsqr(String var1, int var2, int var3, int var4, int var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, intW var15);

   void sbdsqr(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, intW var21);

   void sdisna(String var1, int var2, int var3, float[] var4, float[] var5, intW var6);

   void sdisna(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void sgbbrd(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, float[] var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, intW var18);

   void sgbbrd(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, intW var25);

   void sgbcon(String var1, int var2, int var3, int var4, float[] var5, int var6, int[] var7, float var8, floatW var9, float[] var10, int[] var11, intW var12);

   void sgbcon(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, float var10, floatW var11, float[] var12, int var13, int[] var14, int var15, intW var16);

   void sgbequ(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, floatW var9, floatW var10, floatW var11, intW var12);

   void sgbequ(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, floatW var12, floatW var13, floatW var14, intW var15);

   void sgbrfs(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int[] var10, float[] var11, int var12, float[] var13, int var14, float[] var15, float[] var16, float[] var17, int[] var18, intW var19);

   void sgbrfs(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, intW var28);

   void sgbsv(int var1, int var2, int var3, int var4, float[] var5, int var6, int[] var7, float[] var8, int var9, intW var10);

   void sgbsv(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   void sgbsvx(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, StringW var12, float[] var13, float[] var14, float[] var15, int var16, float[] var17, int var18, floatW var19, float[] var20, float[] var21, float[] var22, int[] var23, intW var24);

   void sgbsvx(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, StringW var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, floatW var26, float[] var27, int var28, float[] var29, int var30, float[] var31, int var32, int[] var33, int var34, intW var35);

   void sgbtf2(int var1, int var2, int var3, int var4, float[] var5, int var6, int[] var7, intW var8);

   void sgbtf2(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   void sgbtrf(int var1, int var2, int var3, int var4, float[] var5, int var6, int[] var7, intW var8);

   void sgbtrf(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, int[] var8, int var9, intW var10);

   void sgbtrs(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int[] var8, float[] var9, int var10, intW var11);

   void sgbtrs(String var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, int[] var9, int var10, float[] var11, int var12, int var13, intW var14);

   void sgebak(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   void sgebak(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sgebal(String var1, int var2, float[] var3, int var4, intW var5, intW var6, float[] var7, intW var8);

   void sgebal(String var1, int var2, float[] var3, int var4, int var5, intW var6, intW var7, float[] var8, int var9, intW var10);

   void sgebd2(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, float[] var9, intW var10);

   void sgebd2(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16);

   void sgebrd(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, float[] var9, int var10, intW var11);

   void sgebrd(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sgecon(String var1, int var2, float[] var3, int var4, float var5, floatW var6, float[] var7, int[] var8, intW var9);

   void sgecon(String var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   void sgeequ(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, floatW var7, floatW var8, floatW var9, intW var10);

   void sgeequ(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, floatW var10, floatW var11, floatW var12, intW var13);

   void sgees(String var1, String var2, Object var3, int var4, float[] var5, int var6, intW var7, float[] var8, float[] var9, float[] var10, int var11, float[] var12, int var13, boolean[] var14, intW var15);

   void sgees(String var1, String var2, Object var3, int var4, float[] var5, int var6, int var7, intW var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, boolean[] var19, int var20, intW var21);

   void sgeesx(String var1, String var2, Object var3, String var4, int var5, float[] var6, int var7, intW var8, float[] var9, float[] var10, float[] var11, int var12, floatW var13, floatW var14, float[] var15, int var16, int[] var17, int var18, boolean[] var19, intW var20);

   void sgeesx(String var1, String var2, Object var3, String var4, int var5, float[] var6, int var7, int var8, intW var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, floatW var17, floatW var18, float[] var19, int var20, int var21, int[] var22, int var23, int var24, boolean[] var25, int var26, intW var27);

   void sgeev(String var1, String var2, int var3, float[] var4, int var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void sgeev(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, intW var20);

   void sgeevx(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, int var11, float[] var12, int var13, intW var14, intW var15, float[] var16, floatW var17, float[] var18, float[] var19, float[] var20, int var21, int[] var22, intW var23);

   void sgeevx(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19, intW var20, float[] var21, int var22, floatW var23, float[] var24, int var25, float[] var26, int var27, float[] var28, int var29, int var30, int[] var31, int var32, intW var33);

   void sgegs(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, intW var17);

   void sgegs(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   void sgegv(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, intW var17);

   void sgegv(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   void sgehd2(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, intW var8);

   void sgehd2(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sgehrd(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void sgehrd(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sgelq2(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, intW var7);

   void sgelq2(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   void sgelqf(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, int var7, intW var8);

   void sgelqf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sgels(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sgels(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, intW var14);

   void sgelsd(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float var9, intW var10, float[] var11, int var12, int[] var13, intW var14);

   void sgelsd(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, int[] var17, int var18, intW var19);

   void sgelss(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float var9, intW var10, float[] var11, int var12, intW var13);

   void sgelss(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, intW var17);

   void sgelsx(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, float var9, intW var10, float[] var11, intW var12);

   void sgelsx(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float var12, intW var13, float[] var14, int var15, intW var16);

   void sgelsy(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, float var9, intW var10, float[] var11, int var12, intW var13);

   void sgelsy(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float var12, intW var13, float[] var14, int var15, int var16, intW var17);

   void sgeql2(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, intW var7);

   void sgeql2(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   void sgeqlf(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, int var7, intW var8);

   void sgeqlf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sgeqp3(int var1, int var2, float[] var3, int var4, int[] var5, float[] var6, float[] var7, int var8, intW var9);

   void sgeqp3(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   void sgeqpf(int var1, int var2, float[] var3, int var4, int[] var5, float[] var6, float[] var7, intW var8);

   void sgeqpf(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   void sgeqr2(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, intW var7);

   void sgeqr2(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   void sgeqrf(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, int var7, intW var8);

   void sgeqrf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sgerfs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, float[] var15, int[] var16, intW var17);

   void sgerfs(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   void sgerq2(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, intW var7);

   void sgerq2(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   void sgerqf(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, int var7, intW var8);

   void sgerqf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sgesc2(int var1, float[] var2, int var3, float[] var4, int[] var5, int[] var6, floatW var7);

   void sgesc2(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int[] var7, int var8, int[] var9, int var10, floatW var11);

   void sgesdd(String var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int[] var13, intW var14);

   void sgesdd(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, int[] var18, int var19, intW var20);

   void sgesv(int var1, int var2, float[] var3, int var4, int[] var5, float[] var6, int var7, intW var8);

   void sgesv(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sgesvd(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void sgesvd(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19);

   void sgesvx(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int[] var9, StringW var10, float[] var11, float[] var12, float[] var13, int var14, float[] var15, int var16, floatW var17, float[] var18, float[] var19, float[] var20, int[] var21, intW var22);

   void sgesvx(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, int[] var11, int var12, StringW var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, floatW var24, float[] var25, int var26, float[] var27, int var28, float[] var29, int var30, int[] var31, int var32, intW var33);

   void sgetc2(int var1, float[] var2, int var3, int[] var4, int[] var5, intW var6);

   void sgetc2(int var1, float[] var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, intW var9);

   void sgetf2(int var1, int var2, float[] var3, int var4, int[] var5, intW var6);

   void sgetf2(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   void sgetrf(int var1, int var2, float[] var3, int var4, int[] var5, intW var6);

   void sgetrf(int var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   void sgetri(int var1, float[] var2, int var3, int[] var4, float[] var5, int var6, intW var7);

   void sgetri(int var1, float[] var2, int var3, int var4, int[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   void sgetrs(String var1, int var2, int var3, float[] var4, int var5, int[] var6, float[] var7, int var8, intW var9);

   void sgetrs(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sggbak(String var1, String var2, int var3, int var4, int var5, float[] var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sggbak(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, intW var14);

   void sggbal(String var1, int var2, float[] var3, int var4, float[] var5, int var6, intW var7, intW var8, float[] var9, float[] var10, float[] var11, intW var12);

   void sggbal(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9, intW var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, intW var17);

   void sgges(String var1, String var2, String var3, Object var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10, float[] var11, float[] var12, float[] var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, boolean[] var20, intW var21);

   void sgges(String var1, String var2, String var3, Object var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, boolean[] var28, int var29, intW var30);

   void sggesx(String var1, String var2, String var3, Object var4, String var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11, float[] var12, float[] var13, float[] var14, float[] var15, int var16, float[] var17, int var18, float[] var19, float[] var20, float[] var21, int var22, int[] var23, int var24, boolean[] var25, intW var26);

   void sggesx(String var1, String var2, String var3, Object var4, String var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, intW var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int var32, int[] var33, int var34, int var35, boolean[] var36, int var37, intW var38);

   void sggev(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, intW var17);

   void sggev(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25);

   void sggevx(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, float[] var11, float[] var12, float[] var13, int var14, float[] var15, int var16, intW var17, intW var18, float[] var19, float[] var20, floatW var21, floatW var22, float[] var23, float[] var24, float[] var25, int var26, int[] var27, boolean[] var28, intW var29);

   void sggevx(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, intW var24, intW var25, float[] var26, int var27, float[] var28, int var29, floatW var30, floatW var31, float[] var32, int var33, float[] var34, int var35, float[] var36, int var37, int var38, int[] var39, int var40, boolean[] var41, int var42, intW var43);

   void sggglm(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, float[] var11, int var12, intW var13);

   void sggglm(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, intW var19);

   void sgghrd(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void sgghrd(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   void sgglse(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, float[] var11, int var12, intW var13);

   void sgglse(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int var18, intW var19);

   void sggqrf(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, float[] var9, float[] var10, int var11, intW var12);

   void sggqrf(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sggrqf(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, float[] var9, float[] var10, int var11, intW var12);

   void sggrqf(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sggsvd(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int[] var22, intW var23);

   void sggsvd(String var1, String var2, String var3, int var4, int var5, int var6, intW var7, intW var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, float[] var28, int var29, int[] var30, int var31, intW var32);

   void sggsvp(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float var11, float var12, intW var13, intW var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int[] var21, float[] var22, float[] var23, intW var24);

   void sggsvp(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float var13, float var14, intW var15, intW var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int[] var26, int var27, float[] var28, int var29, float[] var30, int var31, intW var32);

   void sgtcon(String var1, int var2, float[] var3, float[] var4, float[] var5, float[] var6, int[] var7, float var8, floatW var9, float[] var10, int[] var11, intW var12);

   void sgtcon(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int var12, float var13, floatW var14, float[] var15, int var16, int[] var17, int var18, intW var19);

   void sgtrfs(String var1, int var2, int var3, float[] var4, float[] var5, float[] var6, float[] var7, float[] var8, float[] var9, float[] var10, int[] var11, float[] var12, int var13, float[] var14, int var15, float[] var16, float[] var17, float[] var18, int[] var19, intW var20);

   void sgtrfs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, int[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int[] var32, int var33, intW var34);

   void sgtsv(int var1, int var2, float[] var3, float[] var4, float[] var5, float[] var6, int var7, intW var8);

   void sgtsv(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sgtsvx(String var1, String var2, int var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, float[] var9, float[] var10, float[] var11, int[] var12, float[] var13, int var14, float[] var15, int var16, floatW var17, float[] var18, float[] var19, float[] var20, int[] var21, intW var22);

   void sgtsvx(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, floatW var27, float[] var28, int var29, float[] var30, int var31, float[] var32, int var33, int[] var34, int var35, intW var36);

   void sgttrf(int var1, float[] var2, float[] var3, float[] var4, float[] var5, int[] var6, intW var7);

   void sgttrf(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   void sgttrs(String var1, int var2, int var3, float[] var4, float[] var5, float[] var6, float[] var7, int[] var8, float[] var9, int var10, intW var11);

   void sgttrs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sgtts2(int var1, int var2, int var3, float[] var4, float[] var5, float[] var6, float[] var7, int[] var8, float[] var9, int var10);

   void sgtts2(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, float[] var14, int var15, int var16);

   void shgeqz(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, float[] var12, float[] var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, intW var20);

   void shgeqz(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, float[] var25, int var26, int var27, intW var28);

   void shsein(String var1, String var2, String var3, boolean[] var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, int var11, float[] var12, int var13, int var14, intW var15, float[] var16, int[] var17, int[] var18, intW var19);

   void shsein(String var1, String var2, String var3, boolean[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, int var20, intW var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   void shseqr(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void shseqr(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, intW var19);

   boolean sisnan(float var1);

   void slabad(floatW var1, floatW var2);

   void slabrd(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, float[] var8, float[] var9, float[] var10, int var11, float[] var12, int var13);

   void slabrd(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20);

   void slacn2(int var1, float[] var2, float[] var3, int[] var4, floatW var5, intW var6, int[] var7);

   void slacn2(int var1, float[] var2, int var3, float[] var4, int var5, int[] var6, int var7, floatW var8, intW var9, int[] var10, int var11);

   void slacon(int var1, float[] var2, float[] var3, int[] var4, floatW var5, intW var6);

   void slacon(int var1, float[] var2, int var3, float[] var4, int var5, int[] var6, int var7, floatW var8, intW var9);

   void slacpy(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7);

   void slacpy(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9);

   void sladiv(float var1, float var2, float var3, float var4, floatW var5, floatW var6);

   void slae2(float var1, float var2, float var3, floatW var4, floatW var5);

   void slaebz(int var1, int var2, int var3, int var4, int var5, int var6, float var7, float var8, float var9, float[] var10, float[] var11, float[] var12, int[] var13, float[] var14, float[] var15, intW var16, int[] var17, float[] var18, int[] var19, intW var20);

   void slaebz(int var1, int var2, int var3, int var4, int var5, int var6, float var7, float var8, float var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, intW var22, int[] var23, int var24, float[] var25, int var26, int[] var27, int var28, intW var29);

   void slaed0(int var1, int var2, int var3, float[] var4, float[] var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int[] var11, intW var12);

   void slaed0(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   void slaed1(int var1, float[] var2, float[] var3, int var4, int[] var5, floatW var6, int var7, float[] var8, int[] var9, intW var10);

   void slaed1(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, floatW var9, int var10, float[] var11, int var12, int[] var13, int var14, intW var15);

   void slaed2(intW var1, int var2, int var3, float[] var4, float[] var5, int var6, int[] var7, floatW var8, float[] var9, float[] var10, float[] var11, float[] var12, int[] var13, int[] var14, int[] var15, int[] var16, intW var17);

   void slaed2(intW var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, int[] var9, int var10, floatW var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, intW var28);

   void slaed3(int var1, int var2, int var3, float[] var4, float[] var5, int var6, float var7, float[] var8, float[] var9, int[] var10, int[] var11, float[] var12, float[] var13, intW var14);

   void slaed3(int var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float var9, float[] var10, int var11, float[] var12, int var13, int[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, intW var22);

   void slaed4(int var1, int var2, float[] var3, float[] var4, float[] var5, float var6, floatW var7, intW var8);

   void slaed4(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float var9, floatW var10, intW var11);

   void slaed5(int var1, float[] var2, float[] var3, float[] var4, float var5, floatW var6);

   void slaed5(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9);

   void slaed6(int var1, boolean var2, float var3, float[] var4, float[] var5, float var6, floatW var7, intW var8);

   void slaed6(int var1, boolean var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9, intW var10);

   void slaed7(int var1, int var2, int var3, int var4, int var5, int var6, float[] var7, float[] var8, int var9, int[] var10, floatW var11, int var12, float[] var13, int[] var14, int[] var15, int[] var16, int[] var17, int[] var18, float[] var19, float[] var20, int[] var21, intW var22);

   void slaed7(int var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, floatW var14, int var15, float[] var16, int var17, int[] var18, int var19, int[] var20, int var21, int[] var22, int var23, int[] var24, int var25, int[] var26, int var27, float[] var28, int var29, float[] var30, int var31, int[] var32, int var33, intW var34);

   void slaed8(int var1, intW var2, int var3, int var4, float[] var5, float[] var6, int var7, int[] var8, floatW var9, int var10, float[] var11, float[] var12, float[] var13, int var14, float[] var15, int[] var16, intW var17, int[] var18, float[] var19, int[] var20, int[] var21, intW var22);

   void slaed8(int var1, intW var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, floatW var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25, int[] var26, int var27, float[] var28, int var29, int[] var30, int var31, int[] var32, int var33, intW var34);

   void slaed9(int var1, int var2, int var3, int var4, float[] var5, float[] var6, int var7, float var8, float[] var9, float[] var10, float[] var11, int var12, intW var13);

   void slaed9(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int var17, intW var18);

   void slaeda(int var1, int var2, int var3, int var4, int[] var5, int[] var6, int[] var7, int[] var8, float[] var9, float[] var10, int[] var11, float[] var12, float[] var13, intW var14);

   void slaeda(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7, int var8, int[] var9, int var10, int[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int[] var17, int var18, float[] var19, int var20, float[] var21, int var22, intW var23);

   void slaein(boolean var1, boolean var2, int var3, float[] var4, int var5, float var6, float var7, float[] var8, float[] var9, float[] var10, int var11, float[] var12, float var13, float var14, float var15, intW var16);

   void slaein(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float var7, float var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float var18, float var19, float var20, intW var21);

   void slaev2(float var1, float var2, float var3, floatW var4, floatW var5, floatW var6, floatW var7);

   void slaexc(boolean var1, int var2, float[] var3, int var4, float[] var5, int var6, int var7, int var8, int var9, float[] var10, intW var11);

   void slaexc(boolean var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, int var9, int var10, int var11, float[] var12, int var13, intW var14);

   void slag2(float[] var1, int var2, float[] var3, int var4, float var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10);

   void slag2(float[] var1, int var2, int var3, float[] var4, int var5, int var6, float var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12);

   void slag2d(int var1, int var2, float[] var3, int var4, double[] var5, int var6, intW var7);

   void slag2d(int var1, int var2, float[] var3, int var4, int var5, double[] var6, int var7, int var8, intW var9);

   void slags2(boolean var1, float var2, float var3, float var4, float var5, float var6, float var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12, floatW var13);

   void slagtf(int var1, float[] var2, float var3, float[] var4, float[] var5, float var6, float[] var7, int[] var8, intW var9);

   void slagtf(int var1, float[] var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   void slagtm(String var1, int var2, int var3, float var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, float var10, float[] var11, int var12);

   void slagtm(String var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float var14, float[] var15, int var16, int var17);

   void slagts(int var1, int var2, float[] var3, float[] var4, float[] var5, float[] var6, int[] var7, float[] var8, floatW var9, intW var10);

   void slagts(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int var12, float[] var13, int var14, floatW var15, intW var16);

   void slagv2(float[] var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, floatW var8, floatW var9, floatW var10, floatW var11);

   void slagv2(float[] var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, floatW var13, floatW var14, floatW var15, floatW var16);

   void slahqr(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, int var11, float[] var12, int var13, intW var14);

   void slahqr(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   void slahr2(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, float[] var9, int var10);

   void slahr2(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14);

   void slahrd(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, float[] var9, int var10);

   void slahrd(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14);

   void slaic1(int var1, int var2, float[] var3, float var4, float[] var5, float var6, floatW var7, floatW var8, floatW var9);

   void slaic1(int var1, int var2, float[] var3, int var4, float var5, float[] var6, int var7, float var8, floatW var9, floatW var10, floatW var11);

   boolean slaisnan(float var1, float var2);

   void slaln2(boolean var1, int var2, int var3, float var4, float var5, float[] var6, int var7, float var8, float var9, float[] var10, int var11, float var12, float var13, float[] var14, int var15, floatW var16, floatW var17, intW var18);

   void slaln2(boolean var1, int var2, int var3, float var4, float var5, float[] var6, int var7, int var8, float var9, float var10, float[] var11, int var12, int var13, float var14, float var15, float[] var16, int var17, int var18, floatW var19, floatW var20, intW var21);

   void slals0(int var1, int var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int[] var10, int var11, int[] var12, int var13, float[] var14, int var15, float[] var16, float[] var17, float[] var18, float[] var19, int var20, float var21, float var22, float[] var23, intW var24);

   void slals0(int var1, int var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, int var14, int[] var15, int var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, float[] var27, int var28, int var29, float var30, float var31, float[] var32, int var33, intW var34);

   void slalsa(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int[] var12, float[] var13, float[] var14, float[] var15, float[] var16, int[] var17, int[] var18, int var19, int[] var20, float[] var21, float[] var22, float[] var23, float[] var24, int[] var25, intW var26);

   void slalsa(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int[] var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int var30, int[] var31, int var32, float[] var33, int var34, float[] var35, int var36, float[] var37, int var38, float[] var39, int var40, int[] var41, int var42, intW var43);

   void slalsd(String var1, int var2, int var3, int var4, float[] var5, float[] var6, float[] var7, int var8, float var9, intW var10, float[] var11, int[] var12, intW var13);

   void slalsd(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   void slamrg(int var1, int var2, float[] var3, int var4, int var5, int[] var6);

   void slamrg(int var1, int var2, float[] var3, int var4, int var5, int var6, int[] var7, int var8);

   int slaneg(int var1, float[] var2, float[] var3, float var4, float var5, int var6);

   int slaneg(int var1, float[] var2, int var3, float[] var4, int var5, float var6, float var7, int var8);

   float slangb(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7);

   float slangb(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   float slange(String var1, int var2, int var3, float[] var4, int var5, float[] var6);

   float slange(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8);

   float slangt(String var1, int var2, float[] var3, float[] var4, float[] var5);

   float slangt(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8);

   float slanhs(String var1, int var2, float[] var3, int var4, float[] var5);

   float slanhs(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7);

   float slansb(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7);

   float slansb(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9);

   float slansp(String var1, String var2, int var3, float[] var4, float[] var5);

   float slansp(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7);

   float slanst(String var1, int var2, float[] var3, float[] var4);

   float slanst(String var1, int var2, float[] var3, int var4, float[] var5, int var6);

   float slansy(String var1, String var2, int var3, float[] var4, int var5, float[] var6);

   float slansy(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8);

   float slantb(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8);

   float slantb(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10);

   float slantp(String var1, String var2, String var3, int var4, float[] var5, float[] var6);

   float slantp(String var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8);

   float slantr(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8);

   float slantr(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10);

   void slanv2(floatW var1, floatW var2, floatW var3, floatW var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10);

   void slapll(int var1, float[] var2, int var3, float[] var4, int var5, floatW var6);

   void slapll(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, floatW var8);

   void slapmt(boolean var1, int var2, int var3, float[] var4, int var5, int[] var6);

   void slapmt(boolean var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8);

   float slapy2(float var1, float var2);

   float slapy3(float var1, float var2, float var3);

   void slaqgb(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, float var9, float var10, float var11, StringW var12);

   void slaqgb(int var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float var12, float var13, float var14, StringW var15);

   void slaqge(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float var7, float var8, float var9, StringW var10);

   void slaqge(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, float var12, StringW var13);

   void slaqp2(int var1, int var2, int var3, float[] var4, int var5, int[] var6, float[] var7, float[] var8, float[] var9, float[] var10);

   void slaqp2(int var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16);

   void slaqps(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, int[] var8, float[] var9, float[] var10, float[] var11, float[] var12, float[] var13, int var14);

   void slaqps(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, int var8, int[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21);

   void slaqr0(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16);

   void slaqr0(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, intW var21);

   void slaqr1(int var1, float[] var2, int var3, float var4, float var5, float var6, float var7, float[] var8);

   void slaqr1(int var1, float[] var2, int var3, int var4, float var5, float var6, float var7, float var8, float[] var9, int var10);

   void slaqr2(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, float[] var11, int var12, intW var13, intW var14, float[] var15, float[] var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, float[] var25, int var26);

   void slaqr2(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, int var11, float[] var12, int var13, int var14, intW var15, intW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, int var24, float[] var25, int var26, int var27, int var28, float[] var29, int var30, int var31, float[] var32, int var33, int var34);

   void slaqr3(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, float[] var11, int var12, intW var13, intW var14, float[] var15, float[] var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, float[] var25, int var26);

   void slaqr3(boolean var1, boolean var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, int var10, int var11, float[] var12, int var13, int var14, intW var15, intW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, int var24, float[] var25, int var26, int var27, int var28, float[] var29, int var30, int var31, float[] var32, int var33, int var34);

   void slaqr4(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16);

   void slaqr4(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, intW var21);

   void slaqr5(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, float[] var8, float[] var9, float[] var10, int var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25);

   void slaqr5(boolean var1, boolean var2, int var3, int var4, int var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int var26, float[] var27, int var28, int var29, int var30, float[] var31, int var32, int var33);

   void slaqsb(String var1, int var2, int var3, float[] var4, int var5, float[] var6, float var7, float var8, StringW var9);

   void slaqsb(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float var9, float var10, StringW var11);

   void slaqsp(String var1, int var2, float[] var3, float[] var4, float var5, float var6, StringW var7);

   void slaqsp(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float var7, float var8, StringW var9);

   void slaqsy(String var1, int var2, float[] var3, int var4, float[] var5, float var6, float var7, StringW var8);

   void slaqsy(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float var8, float var9, StringW var10);

   void slaqtr(boolean var1, boolean var2, int var3, float[] var4, int var5, float[] var6, float var7, floatW var8, float[] var9, float[] var10, intW var11);

   void slaqtr(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float var9, floatW var10, float[] var11, int var12, float[] var13, int var14, intW var15);

   void slar1v(int var1, int var2, int var3, float var4, float[] var5, float[] var6, float[] var7, float[] var8, float var9, float var10, float[] var11, boolean var12, intW var13, floatW var14, floatW var15, intW var16, int[] var17, floatW var18, floatW var19, floatW var20, float[] var21);

   void slar1v(int var1, int var2, int var3, float var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float[] var15, int var16, boolean var17, intW var18, floatW var19, floatW var20, intW var21, int[] var22, int var23, floatW var24, floatW var25, floatW var26, float[] var27, int var28);

   void slar2v(int var1, float[] var2, float[] var3, float[] var4, int var5, float[] var6, float[] var7, int var8);

   void slar2v(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13);

   void slarf(String var1, int var2, int var3, float[] var4, int var5, float var6, float[] var7, int var8, float[] var9);

   void slarf(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, float[] var8, int var9, int var10, float[] var11, int var12);

   void slarfb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15);

   void slarfb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19);

   void slarfg(int var1, floatW var2, float[] var3, int var4, floatW var5);

   void slarfg(int var1, floatW var2, float[] var3, int var4, int var5, floatW var6);

   void slarft(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, int var9);

   void slarft(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   void slarfx(String var1, int var2, int var3, float[] var4, float var5, float[] var6, int var7, float[] var8);

   void slarfx(String var1, int var2, int var3, float[] var4, int var5, float var6, float[] var7, int var8, int var9, float[] var10, int var11);

   void slargv(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7);

   void slargv(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10);

   void slarnv(int var1, int[] var2, int var3, float[] var4);

   void slarnv(int var1, int[] var2, int var3, int var4, float[] var5, int var6);

   void slarra(int var1, float[] var2, float[] var3, float[] var4, float var5, float var6, intW var7, int[] var8, intW var9);

   void slarra(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, intW var10, int[] var11, int var12, intW var13);

   void slarrb(int var1, float[] var2, float[] var3, int var4, int var5, float var6, float var7, int var8, float[] var9, float[] var10, float[] var11, float[] var12, int[] var13, float var14, float var15, int var16, intW var17);

   void slarrb(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int var7, float var8, float var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, int[] var19, int var20, float var21, float var22, int var23, intW var24);

   void slarrc(String var1, int var2, float var3, float var4, float[] var5, float[] var6, float var7, intW var8, intW var9, intW var10, intW var11);

   void slarrc(String var1, int var2, float var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, intW var10, intW var11, intW var12, intW var13);

   void slarrd(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float[] var8, float var9, float[] var10, float[] var11, float[] var12, float var13, int var14, int[] var15, intW var16, float[] var17, float[] var18, floatW var19, floatW var20, int[] var21, int[] var22, float[] var23, int[] var24, intW var25);

   void slarrd(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, float var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, float var17, int var18, int[] var19, int var20, intW var21, float[] var22, int var23, float[] var24, int var25, floatW var26, floatW var27, int[] var28, int var29, int[] var30, int var31, float[] var32, int var33, int[] var34, int var35, intW var36);

   void slarre(String var1, int var2, floatW var3, floatW var4, int var5, int var6, float[] var7, float[] var8, float[] var9, float var10, float var11, float var12, intW var13, int[] var14, intW var15, float[] var16, float[] var17, float[] var18, int[] var19, int[] var20, float[] var21, floatW var22, float[] var23, int[] var24, intW var25);

   void slarre(String var1, int var2, floatW var3, floatW var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float var15, intW var16, int[] var17, int var18, intW var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int[] var26, int var27, int[] var28, int var29, float[] var30, int var31, floatW var32, float[] var33, int var34, int[] var35, int var36, intW var37);

   void slarrf(int var1, float[] var2, float[] var3, float[] var4, int var5, int var6, float[] var7, float[] var8, float[] var9, float var10, float var11, float var12, float var13, floatW var14, float[] var15, float[] var16, float[] var17, intW var18);

   void slarrf(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float var16, float var17, float var18, float var19, floatW var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, intW var27);

   void slarrj(int var1, float[] var2, float[] var3, int var4, int var5, float var6, int var7, float[] var8, float[] var9, float[] var10, int[] var11, float var12, float var13, intW var14);

   void slarrj(int var1, float[] var2, int var3, float[] var4, int var5, int var6, int var7, float var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, int[] var16, int var17, float var18, float var19, intW var20);

   void slarrk(int var1, int var2, float var3, float var4, float[] var5, float[] var6, float var7, float var8, floatW var9, floatW var10, intW var11);

   void slarrk(int var1, int var2, float var3, float var4, float[] var5, int var6, float[] var7, int var8, float var9, float var10, floatW var11, floatW var12, intW var13);

   void slarrr(int var1, float[] var2, float[] var3, intW var4);

   void slarrr(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   void slarrv(int var1, float var2, float var3, float[] var4, float[] var5, float var6, int[] var7, int var8, int var9, int var10, float var11, floatW var12, floatW var13, float[] var14, float[] var15, float[] var16, int[] var17, int[] var18, float[] var19, float[] var20, int var21, int[] var22, float[] var23, int[] var24, intW var25);

   void slarrv(int var1, float var2, float var3, float[] var4, int var5, float[] var6, int var7, float var8, int[] var9, int var10, int var11, int var12, int var13, float var14, floatW var15, floatW var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int[] var23, int var24, int[] var25, int var26, float[] var27, int var28, float[] var29, int var30, int var31, int[] var32, int var33, float[] var34, int var35, int[] var36, int var37, intW var38);

   void slartg(float var1, float var2, floatW var3, floatW var4, floatW var5);

   void slartv(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8);

   void slartv(int var1, float[] var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   void slaruv(int[] var1, int var2, float[] var3);

   void slaruv(int[] var1, int var2, int var3, float[] var4, int var5);

   void slarz(String var1, int var2, int var3, int var4, float[] var5, int var6, float var7, float[] var8, int var9, float[] var10);

   void slarz(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float var8, float[] var9, int var10, int var11, float[] var12, int var13);

   void slarzb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16);

   void slarzb(String var1, String var2, String var3, String var4, int var5, int var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20);

   void slarzt(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, int var9);

   void slarzt(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   void slas2(float var1, float var2, float var3, floatW var4, floatW var5);

   void slascl(String var1, int var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, intW var10);

   void slascl(String var1, int var2, int var3, float var4, float var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   void slasd0(int var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, int var8, int var9, int[] var10, float[] var11, intW var12);

   void slasd0(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int var13, int[] var14, int var15, float[] var16, int var17, intW var18);

   void slasd1(int var1, int var2, int var3, float[] var4, floatW var5, floatW var6, float[] var7, int var8, float[] var9, int var10, int[] var11, int[] var12, float[] var13, intW var14);

   void slasd1(int var1, int var2, int var3, float[] var4, int var5, floatW var6, floatW var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, int[] var14, int var15, int[] var16, int var17, float[] var18, int var19, intW var20);

   void slasd2(int var1, int var2, int var3, intW var4, float[] var5, float[] var6, float var7, float var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, int var15, float[] var16, int var17, int[] var18, int[] var19, int[] var20, int[] var21, int[] var22, intW var23);

   void slasd2(int var1, int var2, int var3, intW var4, float[] var5, int var6, float[] var7, int var8, float var9, float var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, int[] var25, int var26, int[] var27, int var28, int[] var29, int var30, int[] var31, int var32, int[] var33, int var34, intW var35);

   void slasd3(int var1, int var2, int var3, int var4, float[] var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, int[] var17, int[] var18, float[] var19, intW var20);

   void slasd3(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int[] var26, int var27, float[] var28, int var29, intW var30);

   void slasd4(int var1, int var2, float[] var3, float[] var4, float[] var5, float var6, floatW var7, float[] var8, intW var9);

   void slasd4(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float var9, floatW var10, float[] var11, int var12, intW var13);

   void slasd5(int var1, float[] var2, float[] var3, float[] var4, float var5, floatW var6, float[] var7);

   void slasd5(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, floatW var9, float[] var10, int var11);

   void slasd6(int var1, int var2, int var3, int var4, float[] var5, float[] var6, float[] var7, floatW var8, floatW var9, int[] var10, int[] var11, intW var12, int[] var13, int var14, float[] var15, int var16, float[] var17, float[] var18, float[] var19, float[] var20, intW var21, floatW var22, floatW var23, float[] var24, int[] var25, intW var26);

   void slasd6(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, floatW var11, floatW var12, int[] var13, int var14, int[] var15, int var16, intW var17, int[] var18, int var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, float[] var26, int var27, float[] var28, int var29, float[] var30, int var31, intW var32, floatW var33, floatW var34, float[] var35, int var36, int[] var37, int var38, intW var39);

   void slasd7(int var1, int var2, int var3, int var4, intW var5, float[] var6, float[] var7, float[] var8, float[] var9, float[] var10, float[] var11, float[] var12, float var13, float var14, float[] var15, int[] var16, int[] var17, int[] var18, int[] var19, intW var20, int[] var21, int var22, float[] var23, int var24, floatW var25, floatW var26, intW var27);

   void slasd7(int var1, int var2, int var3, int var4, intW var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float var20, float var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int[] var28, int var29, int[] var30, int var31, intW var32, int[] var33, int var34, int var35, float[] var36, int var37, int var38, floatW var39, floatW var40, intW var41);

   void slasd8(int var1, int var2, float[] var3, float[] var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, float[] var11, intW var12);

   void slasd8(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, intW var20);

   void slasda(int var1, int var2, int var3, int var4, float[] var5, float[] var6, float[] var7, int var8, float[] var9, int[] var10, float[] var11, float[] var12, float[] var13, float[] var14, int[] var15, int[] var16, int var17, int[] var18, float[] var19, float[] var20, float[] var21, float[] var22, int[] var23, intW var24);

   void slasda(int var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int[] var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, int[] var26, int var27, int var28, int[] var29, int var30, float[] var31, int var32, float[] var33, int var34, float[] var35, int var36, float[] var37, int var38, int[] var39, int var40, intW var41);

   void slasdq(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, float[] var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, intW var16);

   void slasdq(String var1, int var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, intW var22);

   void slasdt(int var1, intW var2, intW var3, int[] var4, int[] var5, int[] var6, int var7);

   void slasdt(int var1, intW var2, intW var3, int[] var4, int var5, int[] var6, int var7, int[] var8, int var9, int var10);

   void slaset(String var1, int var2, int var3, float var4, float var5, float[] var6, int var7);

   void slaset(String var1, int var2, int var3, float var4, float var5, float[] var6, int var7, int var8);

   void slasq1(int var1, float[] var2, float[] var3, float[] var4, intW var5);

   void slasq1(int var1, float[] var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void slasq2(int var1, float[] var2, intW var3);

   void slasq2(int var1, float[] var2, int var3, intW var4);

   void slasq3(int var1, intW var2, float[] var3, int var4, floatW var5, floatW var6, floatW var7, floatW var8, intW var9, intW var10, intW var11, boolean var12);

   void slasq3(int var1, intW var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, intW var10, intW var11, intW var12, boolean var13);

   void slasq4(int var1, int var2, float[] var3, int var4, int var5, float var6, float var7, float var8, float var9, float var10, float var11, floatW var12, intW var13);

   void slasq4(int var1, int var2, float[] var3, int var4, int var5, int var6, float var7, float var8, float var9, float var10, float var11, float var12, floatW var13, intW var14);

   void slasq5(int var1, int var2, float[] var3, int var4, float var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11, boolean var12);

   void slasq5(int var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11, floatW var12, boolean var13);

   void slasq6(int var1, int var2, float[] var3, int var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10);

   void slasq6(int var1, int var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, floatW var10, floatW var11);

   void slasr(String var1, String var2, String var3, int var4, int var5, float[] var6, float[] var7, float[] var8, int var9);

   void slasr(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12);

   void slasrt(String var1, int var2, float[] var3, intW var4);

   void slasrt(String var1, int var2, float[] var3, int var4, intW var5);

   void slassq(int var1, float[] var2, int var3, floatW var4, floatW var5);

   void slassq(int var1, float[] var2, int var3, int var4, floatW var5, floatW var6);

   void slasv2(float var1, float var2, float var3, floatW var4, floatW var5, floatW var6, floatW var7, floatW var8, floatW var9);

   void slaswp(int var1, float[] var2, int var3, int var4, int var5, int[] var6, int var7);

   void slaswp(int var1, float[] var2, int var3, int var4, int var5, int var6, int[] var7, int var8, int var9);

   void slasy2(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, floatW var12, float[] var13, int var14, floatW var15, intW var16);

   void slasy2(boolean var1, boolean var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, floatW var15, float[] var16, int var17, int var18, floatW var19, intW var20);

   void slasyf(String var1, int var2, int var3, intW var4, float[] var5, int var6, int[] var7, float[] var8, int var9, intW var10);

   void slasyf(String var1, int var2, int var3, intW var4, float[] var5, int var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   void slatbs(String var1, String var2, String var3, String var4, int var5, int var6, float[] var7, int var8, float[] var9, floatW var10, float[] var11, intW var12);

   void slatbs(String var1, String var2, String var3, String var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, floatW var12, float[] var13, int var14, intW var15);

   void slatdf(int var1, int var2, float[] var3, int var4, float[] var5, floatW var6, floatW var7, int[] var8, int[] var9);

   void slatdf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, floatW var8, floatW var9, int[] var10, int var11, int[] var12, int var13);

   void slatps(String var1, String var2, String var3, String var4, int var5, float[] var6, float[] var7, floatW var8, float[] var9, intW var10);

   void slatps(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, floatW var10, float[] var11, int var12, intW var13);

   void slatrd(String var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, float[] var8, int var9);

   void slatrd(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13);

   void slatrs(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, floatW var9, float[] var10, intW var11);

   void slatrs(String var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, floatW var11, float[] var12, int var13, intW var14);

   void slatrz(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7);

   void slatrz(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10);

   void slatzm(String var1, int var2, int var3, float[] var4, int var5, float var6, float[] var7, float[] var8, int var9, float[] var10);

   void slatzm(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14);

   void slauu2(String var1, int var2, float[] var3, int var4, intW var5);

   void slauu2(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   void slauum(String var1, int var2, float[] var3, int var4, intW var5);

   void slauum(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   void slazq3(int var1, intW var2, float[] var3, int var4, floatW var5, floatW var6, floatW var7, floatW var8, intW var9, intW var10, intW var11, boolean var12, intW var13, floatW var14, floatW var15, floatW var16, floatW var17, floatW var18, floatW var19);

   void slazq3(int var1, intW var2, float[] var3, int var4, int var5, floatW var6, floatW var7, floatW var8, floatW var9, intW var10, intW var11, intW var12, boolean var13, intW var14, floatW var15, floatW var16, floatW var17, floatW var18, floatW var19, floatW var20);

   void slazq4(int var1, int var2, float[] var3, int var4, int var5, float var6, float var7, float var8, float var9, float var10, float var11, floatW var12, intW var13, floatW var14);

   void slazq4(int var1, int var2, float[] var3, int var4, int var5, int var6, float var7, float var8, float var9, float var10, float var11, float var12, floatW var13, intW var14, floatW var15);

   void sopgtr(String var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, intW var8);

   void sopgtr(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   void sopmtr(String var1, String var2, String var3, int var4, int var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, intW var11);

   void sopmtr(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, intW var15);

   void sorg2l(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, intW var8);

   void sorg2l(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sorg2r(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, intW var8);

   void sorg2r(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sorgbr(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, int var9, intW var10);

   void sorgbr(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, intW var13);

   void sorghr(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void sorghr(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sorgl2(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, intW var8);

   void sorgl2(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sorglq(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void sorglq(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sorgql(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void sorgql(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sorgqr(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void sorgqr(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sorgr2(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, intW var8);

   void sorgr2(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void sorgrq(int var1, int var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void sorgrq(int var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void sorgtr(String var1, int var2, float[] var3, int var4, float[] var5, float[] var6, int var7, intW var8);

   void sorgtr(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sorm2l(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, intW var12);

   void sorm2l(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   void sorm2r(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, intW var12);

   void sorm2r(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   void sormbr(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void sormbr(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   void sormhr(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void sormhr(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   void sorml2(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, intW var12);

   void sorml2(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   void sormlq(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, int var12, intW var13);

   void sormlq(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sormql(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, int var12, intW var13);

   void sormql(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sormqr(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, int var12, intW var13);

   void sormqr(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sormr2(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, intW var12);

   void sormr2(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   void sormr3(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, float[] var12, intW var13);

   void sormr3(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   void sormrq(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, int var12, intW var13);

   void sormrq(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   void sormrz(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, float[] var12, int var13, intW var14);

   void sormrz(String var1, String var2, int var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, intW var18);

   void sormtr(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, float[] var9, int var10, float[] var11, int var12, intW var13);

   void sormtr(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, intW var17);

   void spbcon(String var1, int var2, int var3, float[] var4, int var5, float var6, floatW var7, float[] var8, int[] var9, intW var10);

   void spbcon(String var1, int var2, int var3, float[] var4, int var5, int var6, float var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   void spbequ(String var1, int var2, int var3, float[] var4, int var5, float[] var6, floatW var7, floatW var8, intW var9);

   void spbequ(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, floatW var9, floatW var10, intW var11);

   void spbrfs(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, float[] var15, int[] var16, intW var17);

   void spbrfs(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   void spbstf(String var1, int var2, int var3, float[] var4, int var5, intW var6);

   void spbstf(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   void spbsv(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, intW var9);

   void spbsv(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   void spbsvx(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, StringW var10, float[] var11, float[] var12, int var13, float[] var14, int var15, floatW var16, float[] var17, float[] var18, float[] var19, int[] var20, intW var21);

   void spbsvx(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, StringW var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int var20, floatW var21, float[] var22, int var23, float[] var24, int var25, float[] var26, int var27, int[] var28, int var29, intW var30);

   void spbtf2(String var1, int var2, int var3, float[] var4, int var5, intW var6);

   void spbtf2(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   void spbtrf(String var1, int var2, int var3, float[] var4, int var5, intW var6);

   void spbtrf(String var1, int var2, int var3, float[] var4, int var5, int var6, intW var7);

   void spbtrs(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, intW var9);

   void spbtrs(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, intW var11);

   void spocon(String var1, int var2, float[] var3, int var4, float var5, floatW var6, float[] var7, int[] var8, intW var9);

   void spocon(String var1, int var2, float[] var3, int var4, int var5, float var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   void spoequ(int var1, float[] var2, int var3, float[] var4, floatW var5, floatW var6, intW var7);

   void spoequ(int var1, float[] var2, int var3, int var4, float[] var5, int var6, floatW var7, floatW var8, intW var9);

   void sporfs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, float[] var13, float[] var14, int[] var15, intW var16);

   void sporfs(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   void sposv(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void sposv(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   void sposvx(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, StringW var9, float[] var10, float[] var11, int var12, float[] var13, int var14, floatW var15, float[] var16, float[] var17, float[] var18, int[] var19, intW var20);

   void sposvx(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, StringW var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, floatW var20, float[] var21, int var22, float[] var23, int var24, float[] var25, int var26, int[] var27, int var28, intW var29);

   void spotf2(String var1, int var2, float[] var3, int var4, intW var5);

   void spotf2(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   void spotrf(String var1, int var2, float[] var3, int var4, intW var5);

   void spotrf(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   void spotri(String var1, int var2, float[] var3, int var4, intW var5);

   void spotri(String var1, int var2, float[] var3, int var4, int var5, intW var6);

   void spotrs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void spotrs(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   void sppcon(String var1, int var2, float[] var3, float var4, floatW var5, float[] var6, int[] var7, intW var8);

   void sppcon(String var1, int var2, float[] var3, int var4, float var5, floatW var6, float[] var7, int var8, int[] var9, int var10, intW var11);

   void sppequ(String var1, int var2, float[] var3, float[] var4, floatW var5, floatW var6, intW var7);

   void sppequ(String var1, int var2, float[] var3, int var4, float[] var5, int var6, floatW var7, floatW var8, intW var9);

   void spprfs(String var1, int var2, int var3, float[] var4, float[] var5, float[] var6, int var7, float[] var8, int var9, float[] var10, float[] var11, float[] var12, int[] var13, intW var14);

   void spprfs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   void sppsv(String var1, int var2, int var3, float[] var4, float[] var5, int var6, intW var7);

   void sppsv(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, intW var9);

   void sppsvx(String var1, String var2, int var3, int var4, float[] var5, float[] var6, StringW var7, float[] var8, float[] var9, int var10, float[] var11, int var12, floatW var13, float[] var14, float[] var15, float[] var16, int[] var17, intW var18);

   void sppsvx(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, StringW var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, floatW var18, float[] var19, int var20, float[] var21, int var22, float[] var23, int var24, int[] var25, int var26, intW var27);

   void spptrf(String var1, int var2, float[] var3, intW var4);

   void spptrf(String var1, int var2, float[] var3, int var4, intW var5);

   void spptri(String var1, int var2, float[] var3, intW var4);

   void spptri(String var1, int var2, float[] var3, int var4, intW var5);

   void spptrs(String var1, int var2, int var3, float[] var4, float[] var5, int var6, intW var7);

   void spptrs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int var8, intW var9);

   void sptcon(int var1, float[] var2, float[] var3, float var4, floatW var5, float[] var6, intW var7);

   void sptcon(int var1, float[] var2, int var3, float[] var4, int var5, float var6, floatW var7, float[] var8, int var9, intW var10);

   void spteqr(String var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, intW var8);

   void spteqr(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   void sptrfs(int var1, int var2, float[] var3, float[] var4, float[] var5, float[] var6, float[] var7, int var8, float[] var9, int var10, float[] var11, float[] var12, float[] var13, intW var14);

   void sptrfs(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, intW var23);

   void sptsv(int var1, int var2, float[] var3, float[] var4, float[] var5, int var6, intW var7);

   void sptsv(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   void sptsvx(String var1, int var2, int var3, float[] var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, int var11, floatW var12, float[] var13, float[] var14, float[] var15, intW var16);

   void sptsvx(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, floatW var18, float[] var19, int var20, float[] var21, int var22, float[] var23, int var24, intW var25);

   void spttrf(int var1, float[] var2, float[] var3, intW var4);

   void spttrf(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   void spttrs(int var1, int var2, float[] var3, float[] var4, float[] var5, int var6, intW var7);

   void spttrs(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, intW var10);

   void sptts2(int var1, int var2, float[] var3, float[] var4, float[] var5, int var6);

   void sptts2(int var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9);

   void srscl(int var1, float var2, float[] var3, int var4);

   void srscl(int var1, float var2, float[] var3, int var4, int var5);

   void ssbev(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, int var9, float[] var10, intW var11);

   void ssbev(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, intW var15);

   void ssbevd(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   void ssbevd(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   void ssbevx(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, int var12, int var13, float var14, intW var15, float[] var16, float[] var17, int var18, float[] var19, int[] var20, int[] var21, intW var22);

   void ssbevx(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float var13, int var14, int var15, float var16, intW var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int[] var25, int var26, int[] var27, int var28, intW var29);

   void ssbgst(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, intW var13);

   void ssbgst(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   void ssbgv(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, float[] var11, int var12, float[] var13, intW var14);

   void ssbgv(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, intW var19);

   void ssbgvd(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, float[] var11, int var12, float[] var13, int var14, int[] var15, int var16, intW var17);

   void ssbgvd(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, int[] var20, int var21, int var22, intW var23);

   void ssbgvx(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, int var15, int var16, float var17, intW var18, float[] var19, float[] var20, int var21, float[] var22, int[] var23, int[] var24, intW var25);

   void ssbgvx(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float var16, float var17, int var18, int var19, float var20, intW var21, float[] var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int[] var29, int var30, int[] var31, int var32, intW var33);

   void ssbtrd(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, float[] var8, float[] var9, int var10, float[] var11, intW var12);

   void ssbtrd(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, intW var17);

   void sspcon(String var1, int var2, float[] var3, int[] var4, float var5, floatW var6, float[] var7, int[] var8, intW var9);

   void sspcon(String var1, int var2, float[] var3, int var4, int[] var5, int var6, float var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   void sspev(String var1, String var2, int var3, float[] var4, float[] var5, float[] var6, int var7, float[] var8, intW var9);

   void sspev(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, intW var13);

   void sspevd(String var1, String var2, int var3, float[] var4, float[] var5, float[] var6, int var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   void sspevd(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, int[] var14, int var15, int var16, intW var17);

   void sspevx(String var1, String var2, String var3, int var4, float[] var5, float var6, float var7, int var8, int var9, float var10, intW var11, float[] var12, float[] var13, int var14, float[] var15, int[] var16, int[] var17, intW var18);

   void sspevx(String var1, String var2, String var3, int var4, float[] var5, int var6, float var7, float var8, int var9, int var10, float var11, intW var12, float[] var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, int[] var20, int var21, int[] var22, int var23, intW var24);

   void sspgst(int var1, String var2, int var3, float[] var4, float[] var5, intW var6);

   void sspgst(int var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void sspgv(int var1, String var2, String var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, intW var11);

   void sspgv(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, intW var16);

   void sspgvd(int var1, String var2, String var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   void sspgvd(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, int[] var17, int var18, int var19, intW var20);

   void sspgvx(int var1, String var2, String var3, String var4, int var5, float[] var6, float[] var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, float[] var15, int var16, float[] var17, int[] var18, int[] var19, intW var20);

   void sspgvx(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, int var12, int var13, float var14, intW var15, float[] var16, int var17, float[] var18, int var19, int var20, float[] var21, int var22, int[] var23, int var24, int[] var25, int var26, intW var27);

   void ssprfs(String var1, int var2, int var3, float[] var4, float[] var5, int[] var6, float[] var7, int var8, float[] var9, int var10, float[] var11, float[] var12, float[] var13, int[] var14, intW var15);

   void ssprfs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   void sspsv(String var1, int var2, int var3, float[] var4, int[] var5, float[] var6, int var7, intW var8);

   void sspsv(String var1, int var2, int var3, float[] var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sspsvx(String var1, String var2, int var3, int var4, float[] var5, float[] var6, int[] var7, float[] var8, int var9, float[] var10, int var11, floatW var12, float[] var13, float[] var14, float[] var15, int[] var16, intW var17);

   void sspsvx(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int[] var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, floatW var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   void ssptrd(String var1, int var2, float[] var3, float[] var4, float[] var5, float[] var6, intW var7);

   void ssptrd(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void ssptrf(String var1, int var2, float[] var3, int[] var4, intW var5);

   void ssptrf(String var1, int var2, float[] var3, int var4, int[] var5, int var6, intW var7);

   void ssptri(String var1, int var2, float[] var3, int[] var4, float[] var5, intW var6);

   void ssptri(String var1, int var2, float[] var3, int var4, int[] var5, int var6, float[] var7, int var8, intW var9);

   void ssptrs(String var1, int var2, int var3, float[] var4, int[] var5, float[] var6, int var7, intW var8);

   void ssptrs(String var1, int var2, int var3, float[] var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void sstebz(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float var8, float[] var9, float[] var10, intW var11, intW var12, float[] var13, int[] var14, int[] var15, float[] var16, int[] var17, intW var18);

   void sstebz(String var1, String var2, int var3, float var4, float var5, int var6, int var7, float var8, float[] var9, int var10, float[] var11, int var12, intW var13, intW var14, float[] var15, int var16, int[] var17, int var18, int[] var19, int var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   void sstedc(String var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, int var8, int[] var9, int var10, intW var11);

   void sstedc(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   void sstegr(String var1, String var2, int var3, float[] var4, float[] var5, float var6, float var7, int var8, int var9, float var10, intW var11, float[] var12, float[] var13, int var14, int[] var15, float[] var16, int var17, int[] var18, int var19, intW var20);

   void sstegr(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   void sstein(int var1, float[] var2, float[] var3, int var4, float[] var5, int[] var6, int[] var7, float[] var8, int var9, float[] var10, int[] var11, int[] var12, intW var13);

   void sstein(int var1, float[] var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int[] var9, int var10, int[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int[] var18, int var19, int[] var20, int var21, intW var22);

   void sstemr(String var1, String var2, int var3, float[] var4, float[] var5, float var6, float var7, int var8, int var9, intW var10, float[] var11, float[] var12, int var13, int var14, int[] var15, booleanW var16, float[] var17, int var18, int[] var19, int var20, intW var21);

   void sstemr(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, intW var12, float[] var13, int var14, float[] var15, int var16, int var17, int var18, int[] var19, int var20, booleanW var21, float[] var22, int var23, int var24, int[] var25, int var26, int var27, intW var28);

   void ssteqr(String var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, intW var8);

   void ssteqr(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   void ssterf(int var1, float[] var2, float[] var3, intW var4);

   void ssterf(int var1, float[] var2, int var3, float[] var4, int var5, intW var6);

   void sstev(String var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, intW var8);

   void sstev(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, intW var12);

   void sstevd(String var1, int var2, float[] var3, float[] var4, float[] var5, int var6, float[] var7, int var8, int[] var9, int var10, intW var11);

   void sstevd(String var1, int var2, float[] var3, int var4, float[] var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, int[] var13, int var14, int var15, intW var16);

   void sstevr(String var1, String var2, int var3, float[] var4, float[] var5, float var6, float var7, int var8, int var9, float var10, intW var11, float[] var12, float[] var13, int var14, int[] var15, float[] var16, int var17, int[] var18, int var19, intW var20);

   void sstevr(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   void sstevx(String var1, String var2, int var3, float[] var4, float[] var5, float var6, float var7, int var8, int var9, float var10, intW var11, float[] var12, float[] var13, int var14, float[] var15, int[] var16, int[] var17, intW var18);

   void sstevx(String var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int[] var21, int var22, int[] var23, int var24, intW var25);

   void ssycon(String var1, int var2, float[] var3, int var4, int[] var5, float var6, floatW var7, float[] var8, int[] var9, intW var10);

   void ssycon(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float var8, floatW var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   void ssyev(String var1, String var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void ssyev(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void ssyevd(String var1, String var2, int var3, float[] var4, int var5, float[] var6, float[] var7, int var8, int[] var9, int var10, intW var11);

   void ssyevd(String var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, int[] var12, int var13, int var14, intW var15);

   void ssyevr(String var1, String var2, String var3, int var4, float[] var5, int var6, float var7, float var8, int var9, int var10, float var11, intW var12, float[] var13, float[] var14, int var15, int[] var16, float[] var17, int var18, int[] var19, int var20, intW var21);

   void ssyevr(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, int[] var19, int var20, float[] var21, int var22, int var23, int[] var24, int var25, int var26, intW var27);

   void ssyevx(String var1, String var2, String var3, int var4, float[] var5, int var6, float var7, float var8, int var9, int var10, float var11, intW var12, float[] var13, float[] var14, int var15, float[] var16, int var17, int[] var18, int[] var19, intW var20);

   void ssyevx(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, intW var13, float[] var14, int var15, float[] var16, int var17, int var18, float[] var19, int var20, int var21, int[] var22, int var23, int[] var24, int var25, intW var26);

   void ssygs2(int var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void ssygs2(int var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   void ssygst(int var1, String var2, int var3, float[] var4, int var5, float[] var6, int var7, intW var8);

   void ssygst(int var1, String var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, intW var10);

   void ssygv(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, intW var12);

   void ssygv(int var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, intW var16);

   void ssygvd(int var1, String var2, String var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   void ssygvd(int var1, String var2, String var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, float[] var13, int var14, int var15, int[] var16, int var17, int var18, intW var19);

   void ssygvx(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, float[] var8, int var9, float var10, float var11, int var12, int var13, float var14, intW var15, float[] var16, float[] var17, int var18, float[] var19, int var20, int[] var21, int[] var22, intW var23);

   void ssygvx(int var1, String var2, String var3, String var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float var12, float var13, int var14, int var15, float var16, intW var17, float[] var18, int var19, float[] var20, int var21, int var22, float[] var23, int var24, int var25, int[] var26, int var27, int[] var28, int var29, intW var30);

   void ssyrfs(String var1, int var2, int var3, float[] var4, int var5, float[] var6, int var7, int[] var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, float[] var15, int[] var16, intW var17);

   void ssyrfs(String var1, int var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, int[] var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, float[] var22, int var23, int[] var24, int var25, intW var26);

   void ssysv(String var1, int var2, int var3, float[] var4, int var5, int[] var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void ssysv(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, intW var15);

   void ssysvx(String var1, String var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, int[] var9, float[] var10, int var11, float[] var12, int var13, floatW var14, float[] var15, float[] var16, float[] var17, int var18, int[] var19, intW var20);

   void ssysvx(String var1, String var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, int[] var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, int var18, floatW var19, float[] var20, int var21, float[] var22, int var23, float[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   void ssytd2(String var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, intW var8);

   void ssytd2(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12);

   void ssytf2(String var1, int var2, float[] var3, int var4, int[] var5, intW var6);

   void ssytf2(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, intW var8);

   void ssytrd(String var1, int var2, float[] var3, int var4, float[] var5, float[] var6, float[] var7, float[] var8, int var9, intW var10);

   void ssytrd(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, int var13, int var14, intW var15);

   void ssytrf(String var1, int var2, float[] var3, int var4, int[] var5, float[] var6, int var7, intW var8);

   void ssytrf(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void ssytri(String var1, int var2, float[] var3, int var4, int[] var5, float[] var6, intW var7);

   void ssytri(String var1, int var2, float[] var3, int var4, int var5, int[] var6, int var7, float[] var8, int var9, intW var10);

   void ssytrs(String var1, int var2, int var3, float[] var4, int var5, int[] var6, float[] var7, int var8, intW var9);

   void ssytrs(String var1, int var2, int var3, float[] var4, int var5, int var6, int[] var7, int var8, float[] var9, int var10, int var11, intW var12);

   void stbcon(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, floatW var8, float[] var9, int[] var10, intW var11);

   void stbcon(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, floatW var9, float[] var10, int var11, int[] var12, int var13, intW var14);

   void stbrfs(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, float[] var15, int[] var16, intW var17);

   void stbrfs(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, float[] var16, int var17, float[] var18, int var19, float[] var20, int var21, int[] var22, int var23, intW var24);

   void stbtrs(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, float[] var9, int var10, intW var11);

   void stbtrs(String var1, String var2, String var3, int var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, intW var13);

   void stgevc(String var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, int var13, intW var14, float[] var15, intW var16);

   void stgevc(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, int var18, intW var19, float[] var20, int var21, intW var22);

   void stgex2(boolean var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, int var12, int var13, int var14, float[] var15, int var16, intW var17);

   void stgex2(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, int var16, int var17, int var18, float[] var19, int var20, int var21, intW var22);

   void stgexc(boolean var1, boolean var2, int var3, float[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, intW var12, intW var13, float[] var14, int var15, intW var16);

   void stgexc(boolean var1, boolean var2, int var3, float[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, int var15, intW var16, intW var17, float[] var18, int var19, int var20, intW var21);

   void stgsen(int var1, boolean var2, boolean var3, boolean[] var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, float[] var11, float[] var12, float[] var13, int var14, float[] var15, int var16, intW var17, floatW var18, floatW var19, float[] var20, float[] var21, int var22, int[] var23, int var24, intW var25);

   void stgsen(int var1, boolean var2, boolean var3, boolean[] var4, int var5, int var6, float[] var7, int var8, int var9, float[] var10, int var11, int var12, float[] var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int var21, float[] var22, int var23, int var24, intW var25, floatW var26, floatW var27, float[] var28, int var29, float[] var30, int var31, int var32, int[] var33, int var34, int var35, intW var36);

   void stgsja(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, float[] var9, int var10, float[] var11, int var12, float var13, float var14, float[] var15, float[] var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, float[] var23, intW var24, intW var25);

   void stgsja(String var1, String var2, String var3, int var4, int var5, int var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float var15, float var16, float[] var17, int var18, float[] var19, int var20, float[] var21, int var22, int var23, float[] var24, int var25, int var26, float[] var27, int var28, int var29, float[] var30, int var31, intW var32, intW var33);

   void stgsna(String var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, float[] var14, int var15, intW var16, float[] var17, int var18, int[] var19, intW var20);

   void stgsna(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, int var17, float[] var18, int var19, float[] var20, int var21, int var22, intW var23, float[] var24, int var25, int var26, int[] var27, int var28, intW var29);

   void stgsy2(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, floatW var17, floatW var18, floatW var19, int[] var20, intW var21, intW var22);

   void stgsy2(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, floatW var23, floatW var24, floatW var25, int[] var26, int var27, intW var28, intW var29);

   void stgsyl(String var1, int var2, int var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, int var12, float[] var13, int var14, float[] var15, int var16, floatW var17, floatW var18, float[] var19, int var20, int[] var21, intW var22);

   void stgsyl(String var1, int var2, int var3, int var4, float[] var5, int var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, int var16, float[] var17, int var18, int var19, float[] var20, int var21, int var22, floatW var23, floatW var24, float[] var25, int var26, int var27, int[] var28, int var29, intW var30);

   void stpcon(String var1, String var2, String var3, int var4, float[] var5, floatW var6, float[] var7, int[] var8, intW var9);

   void stpcon(String var1, String var2, String var3, int var4, float[] var5, int var6, floatW var7, float[] var8, int var9, int[] var10, int var11, intW var12);

   void stprfs(String var1, String var2, String var3, int var4, int var5, float[] var6, float[] var7, int var8, float[] var9, int var10, float[] var11, float[] var12, float[] var13, int[] var14, intW var15);

   void stprfs(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, float[] var11, int var12, int var13, float[] var14, int var15, float[] var16, int var17, float[] var18, int var19, int[] var20, int var21, intW var22);

   void stptri(String var1, String var2, int var3, float[] var4, intW var5);

   void stptri(String var1, String var2, int var3, float[] var4, int var5, intW var6);

   void stptrs(String var1, String var2, String var3, int var4, int var5, float[] var6, float[] var7, int var8, intW var9);

   void stptrs(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   void strcon(String var1, String var2, String var3, int var4, float[] var5, int var6, floatW var7, float[] var8, int[] var9, intW var10);

   void strcon(String var1, String var2, String var3, int var4, float[] var5, int var6, int var7, floatW var8, float[] var9, int var10, int[] var11, int var12, intW var13);

   void strevc(String var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, int var11, intW var12, float[] var13, intW var14);

   void strevc(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, int var15, intW var16, float[] var17, int var18, intW var19);

   void strexc(String var1, int var2, float[] var3, int var4, float[] var5, int var6, intW var7, intW var8, float[] var9, intW var10);

   void strexc(String var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, int var8, intW var9, intW var10, float[] var11, int var12, intW var13);

   void strrfs(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, float[] var12, float[] var13, float[] var14, int[] var15, intW var16);

   void strrfs(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, float[] var19, int var20, int[] var21, int var22, intW var23);

   void strsen(String var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, float[] var10, intW var11, floatW var12, floatW var13, float[] var14, int var15, int[] var16, int var17, intW var18);

   void strsen(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, float[] var14, int var15, intW var16, floatW var17, floatW var18, float[] var19, int var20, int var21, int[] var22, int var23, int var24, intW var25);

   void strsna(String var1, String var2, boolean[] var3, int var4, float[] var5, int var6, float[] var7, int var8, float[] var9, int var10, float[] var11, float[] var12, int var13, intW var14, float[] var15, int var16, int[] var17, intW var18);

   void strsna(String var1, String var2, boolean[] var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, float[] var15, int var16, float[] var17, int var18, int var19, intW var20, float[] var21, int var22, int var23, int[] var24, int var25, intW var26);

   void strsyl(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, float[] var10, int var11, floatW var12, intW var13);

   void strsyl(String var1, String var2, int var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, float[] var12, int var13, int var14, floatW var15, intW var16);

   void strti2(String var1, String var2, int var3, float[] var4, int var5, intW var6);

   void strti2(String var1, String var2, int var3, float[] var4, int var5, int var6, intW var7);

   void strtri(String var1, String var2, int var3, float[] var4, int var5, intW var6);

   void strtri(String var1, String var2, int var3, float[] var4, int var5, int var6, intW var7);

   void strtrs(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, intW var10);

   void strtrs(String var1, String var2, String var3, int var4, int var5, float[] var6, int var7, int var8, float[] var9, int var10, int var11, intW var12);

   void stzrqf(int var1, int var2, float[] var3, int var4, float[] var5, intW var6);

   void stzrqf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, intW var8);

   void stzrzf(int var1, int var2, float[] var3, int var4, float[] var5, float[] var6, int var7, intW var8);

   void stzrzf(int var1, int var2, float[] var3, int var4, int var5, float[] var6, int var7, float[] var8, int var9, int var10, intW var11);

   double dlamch(String var1);

   void dlamc1(intW var1, intW var2, booleanW var3, booleanW var4);

   void dlamc2(intW var1, intW var2, booleanW var3, doubleW var4, intW var5, doubleW var6, intW var7, doubleW var8);

   double dlamc3(double var1, double var3);

   void dlamc4(intW var1, double var2, int var4);

   void dlamc5(int var1, int var2, int var3, boolean var4, intW var5, doubleW var6);

   double dsecnd();

   boolean lsame(String var1, String var2);

   float second();

   float slamch(String var1);

   void slamc1(intW var1, intW var2, booleanW var3, booleanW var4);

   void slamc2(intW var1, intW var2, booleanW var3, floatW var4, intW var5, floatW var6, intW var7, floatW var8);

   float slamc3(float var1, float var2);

   void slamc4(intW var1, float var2, int var3);

   void slamc5(int var1, int var2, int var3, boolean var4, intW var5, floatW var6);
}
