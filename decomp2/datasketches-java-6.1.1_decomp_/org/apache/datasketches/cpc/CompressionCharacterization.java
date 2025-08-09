package org.apache.datasketches.cpc;

import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.common.Util;
import org.apache.datasketches.memory.Memory;
import org.apache.datasketches.memory.WritableMemory;

public class CompressionCharacterization {
   private String hfmt;
   private String dfmt;
   private String[] hStrArr;
   private long vIn = 0L;
   private int lgMinK;
   private int lgMaxK;
   private int lgMinT;
   private int lgMaxT;
   private int lgMulK;
   private int uPPO;
   private int incLgK;
   private PrintStream ps;
   private PrintWriter pw;
   private CpcSketch[] streamSketches;
   private CompressedState[] compressedStates1;
   private WritableMemory[] memoryArr;
   private CompressedState[] compressedStates2;
   private CpcSketch[] unCompressedSketches;

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "This is OK here"
   )
   public CompressionCharacterization(int lgMinK, int lgMaxK, int lgMinT, int lgMaxT, int lgMulK, int uPPO, int incLgK, PrintStream pS, PrintWriter pW) {
      this.lgMinK = lgMinK;
      this.lgMaxK = lgMaxK;
      this.lgMinT = lgMinT;
      this.lgMaxT = lgMaxT;
      this.lgMulK = lgMulK;
      this.uPPO = Math.max(uPPO, 1);
      this.incLgK = Math.max(incLgK, 1);
      this.ps = pS;
      this.pw = pW;
      this.assembleFormats();
   }

   public void start() {
      this.printf(this.hfmt, (Object[])this.hStrArr);
      this.doRangeOfLgK();
   }

   private void doRangeOfLgK() {
      for(int lgK = this.lgMinK; lgK <= this.lgMaxK; lgK += this.incLgK) {
         this.doRangeOfNAtLgK(lgK);
      }

   }

   private void doRangeOfNAtLgK(int lgK) {
      long n = 1L;
      int lgMaxN = lgK + this.lgMulK;
      long maxN = 1L << lgMaxN;

      for(double slope = -((double)(this.lgMaxT - this.lgMinT)) / (double)lgMaxN; n <= maxN; n = Math.round(Util.powerSeriesNextDouble(this.uPPO, (double)n, true, (double)2.0F))) {
         double lgT = slope * Util.log2((double)n) + (double)this.lgMaxT;
         int totTrials = Math.max(Util.ceilingPowerOf2((int)Math.pow((double)2.0F, lgT)), 1 << this.lgMinT);
         this.doTrialsAtLgKAtN(lgK, n, totTrials);
      }

   }

   private void doTrialsAtLgKAtN(int lgK, long n, int totalTrials) {
      int k = 1 << lgK;
      int minNK = (int)((long)k < n ? (long)k : n);
      double nOverK = (double)n / (double)k;
      int lgTotTrials = Integer.numberOfTrailingZeros(totalTrials);
      int lgWaves = Math.max(lgTotTrials - 10, 0);
      int trialsPerWave = 1 << lgTotTrials - lgWaves;
      this.streamSketches = new CpcSketch[trialsPerWave];
      this.compressedStates1 = new CompressedState[trialsPerWave];
      this.memoryArr = new WritableMemory[trialsPerWave];
      this.compressedStates2 = new CompressedState[trialsPerWave];
      this.unCompressedSketches = new CpcSketch[trialsPerWave];
      long totalC = 0L;
      long totalW = 0L;
      long sumCtor_nS = 0L;
      long sumUpd_nS = 0L;
      long sumCom_nS = 0L;
      long sumSer_nS = 0L;
      long sumDes_nS = 0L;
      long sumUnc_nS = 0L;
      long sumEqu_nS = 0L;
      long start = System.currentTimeMillis();

      for(int w = 0; w < 1 << lgWaves; ++w) {
         long nanoStart = System.nanoTime();

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            CpcSketch sketch = new CpcSketch(lgK);
            this.streamSketches[trial] = sketch;
         }

         long nanoEnd = System.nanoTime();
         sumCtor_nS += nanoEnd - nanoStart;

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            CpcSketch sketch = this.streamSketches[trial];

            for(long i = 0L; i < n; ++i) {
               sketch.update(this.vIn += -7046029254386353133L);
            }
         }

         long var86 = System.nanoTime();
         sumUpd_nS += var86 - nanoEnd;

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            CpcSketch sketch = this.streamSketches[trial];
            CompressedState state = CompressedState.compress(sketch);
            this.compressedStates1[trial] = state;
            totalC += sketch.numCoupons;
            totalW += (long)(state.csvLengthInts + state.cwLengthInts);
         }

         nanoEnd = System.nanoTime();
         sumCom_nS += nanoEnd - var86;

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            CompressedState state = this.compressedStates1[trial];
            long cap = state.getRequiredSerializedBytes();
            WritableMemory wmem = WritableMemory.allocate((int)cap);
            state.exportToMemory(wmem);
            this.memoryArr[trial] = wmem;
         }

         long var88 = System.nanoTime();
         sumSer_nS += var88 - nanoEnd;

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            Memory mem = this.memoryArr[trial];
            CompressedState state = CompressedState.importFromMemory(mem);
            this.compressedStates2[trial] = state;
         }

         nanoEnd = System.nanoTime();
         sumDes_nS += nanoEnd - var88;

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            CompressedState state = this.compressedStates2[trial];
            CpcSketch uncSk = null;
            uncSk = CpcSketch.uncompress(state, 9001L);
            this.unCompressedSketches[trial] = uncSk;
         }

         long var90 = System.nanoTime();
         sumUnc_nS += var90 - nanoEnd;

         for(int trial = 0; trial < trialsPerWave; ++trial) {
            RuntimeAsserts.rtAssert(TestUtil.specialEquals(this.streamSketches[trial], this.unCompressedSketches[trial], false, false));
         }

         nanoEnd = System.nanoTime();
         sumEqu_nS += nanoEnd - var90;
      }

      double total_S = (double)(System.currentTimeMillis() - start) / (double)1000.0F;
      double avgC = (double)1.0F * (double)totalC / (double)totalTrials;
      double avgCoK = avgC / (double)k;
      double avgWords = (double)1.0F * (double)totalW / (double)totalTrials;
      double avgBytes = (double)4.0F * (double)totalW / (double)totalTrials;
      double avgCtor_nS = (double)Math.round((double)sumCtor_nS / (double)totalTrials);
      double avgUpd_nS = (double)Math.round((double)sumUpd_nS / (double)totalTrials);
      double avgUpd_nSperN = avgUpd_nS / (double)n;
      double avgCom_nS = (double)Math.round((double)sumCom_nS / (double)totalTrials);
      double avgCom_nSper2C = avgCom_nS / ((double)2.0F * avgC);
      double avgCom_nSperK = avgCom_nS / (double)k;
      double avgSer_nS = (double)Math.round((double)sumSer_nS / (double)totalTrials);
      double avgSer_nSperW = avgSer_nS / avgWords;
      double avgDes_nS = (double)Math.round((double)sumDes_nS / (double)totalTrials);
      double avgDes_nSperW = avgDes_nS / avgWords;
      double avgUnc_nS = (double)Math.round((double)sumUnc_nS / (double)totalTrials);
      double avgUnc_nSper2C = avgUnc_nS / ((double)2.0F * avgC);
      double avgUnc_nSperK = avgUnc_nS / (double)k;
      double avgEqu_nS = (double)Math.round((double)sumEqu_nS / (double)totalTrials);
      double avgEqu_nSperMinNK = avgEqu_nS / (double)minNK;
      int len = this.unCompressedSketches.length;
      Flavor finFlavor = this.unCompressedSketches[len - 1].getFlavor();
      String offStr = Integer.toString(this.unCompressedSketches[len - 1].windowOffset);
      String flavorOff = finFlavor.toString() + String.format("%2s", offStr);
      this.printf(this.dfmt, lgK, totalTrials, n, minNK, avgCoK, flavorOff, nOverK, avgBytes, avgCtor_nS, avgUpd_nS, avgCom_nS, avgSer_nS, avgDes_nS, avgUnc_nS, avgEqu_nS, avgUpd_nSperN, avgCom_nSper2C, avgCom_nSperK, avgSer_nSperW, avgDes_nSperW, avgUnc_nSper2C, avgUnc_nSperK, avgEqu_nSperMinNK, total_S);
   }

   private void printf(String format, Object... args) {
      if (this.ps != null) {
         this.ps.printf(format, args);
      }

      if (this.pw != null) {
         this.pw.printf(format, args);
      }

   }

   private void assembleFormats() {
      String[][] assy = new String[][]{{"lgK", "%3s", "%3d"}, {"Trials", "%9s", "%9d"}, {"n", "%12s", "%12d"}, {"MinKN", "%9s", "%9d"}, {"AvgC/K", "%9s", "%9.4g"}, {"FinFlavor", "%11s", "%11s"}, {"N/K", "%9s", "%9.4g"}, {"AvgBytes", "%9s", "%9.0f"}, {"AvgCtor_nS", "%11s", "%11.0f"}, {"AvgUpd_nS", "%10s", "%10.4e"}, {"AvgCom_nS", "%10s", "%10.0f"}, {"AvgSer_nS", "%10s", "%10.2f"}, {"AvgDes_nS", "%10s", "%10.2f"}, {"AvgUnc_nS", "%10s", "%10.0f"}, {"AvgEqu_nS", "%10s", "%10.0f"}, {"AvgUpd_nSperN", "%14s", "%14.2f"}, {"AvgCom_nSper2C", "%15s", "%15.4g"}, {"AvgCom_nSperK", "%14s", "%14.4g"}, {"AvgSer_nSperW", "%14s", "%14.2f"}, {"AvgDes_nSperW", "%14s", "%14.2f"}, {"AvgUnc_nSper2C", "%15s", "%15.4g"}, {"AvgUnc_nSperK", "%14s", "%14.4g"}, {"AvgEqu_nSperMinNK", "%18s", "%18.4g"}, {"Total_S", "%8s", "%8.3f"}};
      int cols = assy.length;
      this.hStrArr = new String[cols];
      StringBuilder headerFmt = new StringBuilder();
      StringBuilder dataFmt = new StringBuilder();
      headerFmt.append(Util.LS + "Compression Characterization" + Util.LS);

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
