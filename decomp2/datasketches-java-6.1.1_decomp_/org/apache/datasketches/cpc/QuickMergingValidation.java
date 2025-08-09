package org.apache.datasketches.cpc;

import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.common.Util;

public class QuickMergingValidation {
   private String hfmt;
   private String dfmt;
   private String[] hStrArr;
   private long vIn = 0L;
   private int lgMinK;
   private int lgMaxK;
   private int incLgK;
   private PrintStream printStream;
   private PrintWriter printWriter;

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "This is OK here"
   )
   public QuickMergingValidation(int lgMinK, int lgMaxK, int incLgK, PrintStream ps, PrintWriter pw) {
      this.lgMinK = lgMinK;
      this.lgMaxK = lgMaxK;
      this.incLgK = incLgK;
      this.printStream = ps;
      this.printWriter = pw;
      this.assembleFormats();
   }

   public void start() {
      this.printf(this.hfmt, (Object[])this.hStrArr);
      this.doRangeOfLgK();
   }

   private void doRangeOfLgK() {
      for(int lgK = this.lgMinK; lgK <= this.lgMaxK; lgK += this.incLgK) {
         this.multiQuickTest(lgK);
      }

   }

   private void multiQuickTest(int lgK) {
      int k = 1 << lgK;
      int[] targetC = new int[]{0, 1, 3 * k / 32 - 1, k / 3, k, 7 * k / 2};
      int len = targetC.length;

      for(int i = 0; i < len; ++i) {
         for(int j = 0; j < len; ++j) {
            this.quickTest(lgK, (long)targetC[i], (long)targetC[j]);
         }
      }

   }

   void quickTest(int lgK, long cA, long cB) {
      CpcSketch skA = new CpcSketch(lgK);
      CpcSketch skB = new CpcSketch(lgK);
      CpcSketch skD = new CpcSketch(lgK);
      long t0 = System.nanoTime();

      while(skA.numCoupons < cA) {
         long in = this.vIn += -7046029254386353133L;
         skA.update(in);
         skD.update(in);
      }

      long t1 = System.nanoTime();

      while(skB.numCoupons < cB) {
         long in = this.vIn += -7046029254386353133L;
         skB.update(in);
         skD.update(in);
      }

      long t2 = System.nanoTime();
      CpcUnion ugM = new CpcUnion(lgK);
      ugM.update(skA);
      long t3 = System.nanoTime();
      ugM.update(skB);
      long t4 = System.nanoTime();
      CpcSketch skR = ugM.getResult();
      long t5 = System.nanoTime();
      RuntimeAsserts.rtAssert(TestUtil.specialEquals(skD, skR, false, true));
      Flavor fA = skA.getFlavor();
      Flavor fB = skB.getFlavor();
      Flavor fR = skR.getFlavor();
      String aOff = Integer.toString(skA.windowOffset);
      String bOff = Integer.toString(skB.windowOffset);
      String rOff = Integer.toString(skR.windowOffset);
      String fAoff = fA + String.format("%2s", aOff);
      String fBoff = fB + String.format("%2s", bOff);
      String fRoff = fR + String.format("%2s", rOff);
      double updA_mS = (double)(t1 - t0) / (double)2000000.0F;
      double updB_mS = (double)(t2 - t1) / (double)2000000.0F;
      double mrgA_mS = (double)(t3 - t2) / (double)1000000.0F;
      double mrgB_mS = (double)(t4 - t3) / (double)1000000.0F;
      double rslt_mS = (double)(t5 - t4) / (double)1000000.0F;
      this.printf(this.dfmt, lgK, cA, cB, fAoff, fBoff, fRoff, updA_mS, updB_mS, mrgA_mS, mrgB_mS, rslt_mS);
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
      String[][] assy = new String[][]{{"lgK", "%3s", "%3d"}, {"Ca", "%10s", "%10d"}, {"Cb", "%10s", "%10d"}, {"Flavor_a", "%10s", "%10s"}, {"Flavor_b", "%10s", "%10s"}, {"Flavor_m", "%10s", "%10s"}, {"updA_mS", "%9s", "%9.3f"}, {"updB_mS", "%9s", "%9.3f"}, {"mrgA_mS", "%9s", "%9.3f"}, {"mrgB_mS", "%9s", "%9.3f"}, {"rslt_mS", "%9s", "%9.3f"}};
      int cols = assy.length;
      this.hStrArr = new String[cols];
      StringBuilder headerFmt = new StringBuilder();
      StringBuilder dataFmt = new StringBuilder();
      headerFmt.append(Util.LS + "Quick Merging Validation" + Util.LS);

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
