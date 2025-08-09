package org.apache.datasketches.cpc;

import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.common.Util;

public class MergingValidation {
   private String hfmt;
   private String dfmt;
   private String[] hStrArr;
   private long vIn = 0L;
   private int lgMinK;
   private int lgMaxK;
   private int lgMulK;
   private int uPPO;
   private int incLgK;
   private PrintStream printStream;
   private PrintWriter printWriter;

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "This is OK here"
   )
   public MergingValidation(int lgMinK, int lgMaxK, int lgMulK, int uPPO, int incLgK, PrintStream pS, PrintWriter pW) {
      this.lgMinK = lgMinK;
      this.lgMaxK = lgMaxK;
      this.lgMulK = lgMulK;
      this.uPPO = Math.max(uPPO, 1);
      this.incLgK = Math.max(incLgK, 1);
      this.printStream = pS;
      this.printWriter = pW;
      this.assembleFormats();
   }

   public void start() {
      this.printf(this.hfmt, (Object[])this.hStrArr);
      this.doRangeOfLgK();
   }

   private void doRangeOfLgK() {
      for(int lgK = this.lgMinK; lgK <= this.lgMaxK; lgK += this.incLgK) {
         this.multiTestMerging(lgK, lgK - 1, lgK - 1);
         this.multiTestMerging(lgK, lgK - 1, lgK + 0);
         this.multiTestMerging(lgK, lgK - 1, lgK + 1);
         this.multiTestMerging(lgK, lgK + 0, lgK - 1);
         this.multiTestMerging(lgK, lgK + 0, lgK + 0);
         this.multiTestMerging(lgK, lgK + 0, lgK + 1);
         this.multiTestMerging(lgK, lgK + 1, lgK - 1);
         this.multiTestMerging(lgK, lgK + 1, lgK + 0);
         this.multiTestMerging(lgK, lgK + 1, lgK + 1);
      }

   }

   private void multiTestMerging(int lgKm, int lgKa, int lgKb) {
      long limA = 1L << lgKa + this.lgMulK;
      long limB = 1L << lgKa + this.lgMulK;

      for(long nA = 0L; nA <= limA; nA = Math.round(Util.powerSeriesNextDouble(this.uPPO, (double)nA, true, (double)2.0F))) {
         for(long nB = 0L; nB <= limB; nB = Math.round(Util.powerSeriesNextDouble(this.uPPO, (double)nB, true, (double)2.0F))) {
            this.testMerging(lgKm, lgKa, lgKb, nA, nB);
         }
      }

   }

   private void testMerging(int lgKm, int lgKa, int lgKb, long nA, long nB) {
      CpcUnion ugM = new CpcUnion(lgKm);
      int lgKd = lgKm;
      if (lgKa < lgKm && nA != 0L) {
         lgKd = lgKa;
      }

      if (lgKb < lgKd && nB != 0L) {
         lgKd = lgKb;
      }

      CpcSketch skD = new CpcSketch(lgKd);
      CpcSketch skA = new CpcSketch(lgKa);
      CpcSketch skB = new CpcSketch(lgKb);

      for(long i = 0L; i < nA; ++i) {
         long in = this.vIn += -7046029254386353133L;
         skA.update(in);
         skD.update(in);
      }

      for(long i = 0L; i < nB; ++i) {
         long in = this.vIn += -7046029254386353133L;
         skB.update(in);
         skD.update(in);
      }

      ugM.update(skA);
      ugM.update(skB);
      int finalLgKm = ugM.getLgK();
      long[] matrixM = CpcUnion.getBitMatrix(ugM);
      long cM = ugM.getNumCoupons();
      long cD = skD.numCoupons;
      Flavor flavorD = skD.getFlavor();
      Flavor flavorA = skA.getFlavor();
      Flavor flavorB = skB.getFlavor();
      String dOff = Integer.toString(skD.windowOffset);
      String aOff = Integer.toString(skA.windowOffset);
      String bOff = Integer.toString(skB.windowOffset);
      String flavorDoff = flavorD + String.format("%2s", dOff);
      String flavorAoff = flavorA + String.format("%2s", aOff);
      String flavorBoff = flavorB + String.format("%2s", bOff);
      double iconEstD = IconEstimator.getIconEstimate(lgKd, cD);
      RuntimeAsserts.rtAssert(finalLgKm <= lgKm);
      RuntimeAsserts.rtAssert(cM <= skA.numCoupons + skB.numCoupons);
      RuntimeAsserts.rtAssertEquals(cM, cD);
      RuntimeAsserts.rtAssertEquals((long)finalLgKm, (long)lgKd);
      long[] matrixD = CpcUtil.bitMatrixOfSketch(skD);
      RuntimeAsserts.rtAssertEquals(matrixM, matrixD);
      CpcSketch skR = ugM.getResult();
      double iconEstR = IconEstimator.getIconEstimate(skR.lgK, skR.numCoupons);
      RuntimeAsserts.rtAssertEquals(iconEstD, iconEstR, (double)0.0F);
      RuntimeAsserts.rtAssert(TestUtil.specialEquals(skD, skR, false, true));
      this.printf(this.dfmt, lgKm, lgKa, lgKb, lgKd, nA, nB, nA + nB, flavorAoff, flavorBoff, flavorDoff, skA.numCoupons, skB.numCoupons, cD, iconEstR);
   }

   private void printf(String format, Object... args) {
      if (this.printStream != null) {
         this.printStream.printf(format, args);
      }

      if (this.printWriter != null) {
         this.printWriter.printf(format, args);
      }

   }

   private void assembleFormats() {
      String[][] assy = new String[][]{{"lgKm", "%4s", "%4d"}, {"lgKa", "%4s", "%4d"}, {"lgKb", "%4s", "%4d"}, {"lgKfd", "%6s", "%6d"}, {"nA", "%12s", "%12d"}, {"nB", "%12s", "%12d"}, {"nA+nB", "%12s", "%12d"}, {"Flavor_a", "%11s", "%11s"}, {"Flavor_b", "%11s", "%11s"}, {"Flavor_fd", "%11s", "%11s"}, {"Coupons_a", "%9s", "%9d"}, {"Coupons_b", "%9s", "%9d"}, {"Coupons_fd", "%9s", "%9d"}, {"IconEst_dr", "%12s", "%,12.0f"}};
      int cols = assy.length;
      this.hStrArr = new String[cols];
      StringBuilder headerFmt = new StringBuilder();
      StringBuilder dataFmt = new StringBuilder();
      headerFmt.append(Util.LS + "Merging Validation" + Util.LS);

      for(int i = 0; i < cols; ++i) {
         this.hStrArr[i] = assy[i][0];
         headerFmt.append(assy[i][1]);
         headerFmt.append(i < cols - 1 ? "\t" : Util.LS);
         dataFmt.append(assy[i][2]);
         dataFmt.append(i < cols - 1 ? "\t" : Util.LS);
      }

      this.hfmt = headerFmt.toString();
      this.dfmt = dataFmt.toString();
   }
}
