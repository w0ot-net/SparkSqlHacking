package org.apache.datasketches.cpc;

import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.common.Util;

public class StreamingValidation {
   private String hfmt;
   private String dfmt;
   private String[] hStrArr;
   private long vIn = 0L;
   private int lgMinK;
   private int lgMaxK;
   private int trials;
   private int ppoN;
   private PrintStream printStream;
   private PrintWriter printWriter;
   private CpcSketch sketch = null;
   private BitMatrix matrix = null;

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "This is OK here"
   )
   public StreamingValidation(int lgMinK, int lgMaxK, int trials, int ppoN, PrintStream pS, PrintWriter pW) {
      this.lgMinK = lgMinK;
      this.lgMaxK = lgMaxK;
      this.trials = trials;
      this.ppoN = ppoN;
      this.printStream = pS;
      this.printWriter = pW;
      this.assembleStrings();
   }

   public void start() {
      this.printf(this.hfmt, (Object[])this.hStrArr);
      this.doRangeOfLgK();
   }

   private void doRangeOfLgK() {
      for(int lgK = this.lgMinK; lgK <= this.lgMaxK; ++lgK) {
         this.doRangeOfNAtLgK(lgK);
      }

   }

   private void doRangeOfNAtLgK(int lgK) {
      long n = 1L;

      for(long maxN = 64L * (1L << lgK); n < maxN; n = Math.round(Util.powerSeriesNextDouble(this.ppoN, (double)n, true, (double)2.0F))) {
         this.doTrialsAtLgKAtN(lgK, n);
      }

   }

   private void doTrialsAtLgKAtN(int lgK, long n) {
      double sumC = (double)0.0F;
      double sumIconEst = (double)0.0F;
      double sumHipEst = (double)0.0F;
      this.sketch = new CpcSketch(lgK);
      this.matrix = new BitMatrix(lgK);

      for(int t = 0; t < this.trials; ++t) {
         this.sketch.reset();
         this.matrix.reset();

         for(long i = 0L; i < n; ++i) {
            long in = this.vIn += -7046029254386353133L;
            this.sketch.update(in);
            this.matrix.update(in);
         }

         sumC += (double)this.sketch.numCoupons;
         sumIconEst += IconEstimator.getIconEstimate(lgK, this.sketch.numCoupons);
         sumHipEst += this.sketch.hipEstAccum;
         RuntimeAsserts.rtAssertEquals(this.sketch.numCoupons, this.matrix.getNumCoupons());
         long[] bitMatrix = CpcUtil.bitMatrixOfSketch(this.sketch);
         RuntimeAsserts.rtAssertEquals(bitMatrix, this.matrix.getMatrix());
      }

      long finC = this.sketch.numCoupons;
      Flavor finFlavor = this.sketch.getFlavor();
      int finOff = this.sketch.windowOffset;
      double avgC = sumC / (double)this.trials;
      double avgIconEst = sumIconEst / (double)this.trials;
      double avgHipEst = sumHipEst / (double)this.trials;
      this.printf(this.dfmt, lgK, this.trials, n, finC, finFlavor, finOff, avgC, avgIconEst, avgHipEst);
   }

   private void printf(String format, Object... args) {
      if (this.printStream != null) {
         this.printStream.printf(format, args);
      }

      if (this.printWriter != null) {
         this.printWriter.printf(format, args);
      }

   }

   private void assembleStrings() {
      String[][] assy = new String[][]{{"lgK", "%3s", "%3d"}, {"Trials", "%7s", "%7d"}, {"n", "%8s", "%8d"}, {"FinC", "%8s", "%8d"}, {"FinFlavor", "%10s", "%10s"}, {"FinOff", "%7s", "%7d"}, {"AvgC", "%12s", "%12.3f"}, {"AvgICON", "%12s", "%12.3f"}, {"AvgHIP", "%12s", "%12.3f"}};
      int cols = assy.length;
      this.hStrArr = new String[cols];
      StringBuilder headerFmt = new StringBuilder();
      StringBuilder dataFmt = new StringBuilder();
      headerFmt.append(Util.LS + "Streaming Validation" + Util.LS);

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
