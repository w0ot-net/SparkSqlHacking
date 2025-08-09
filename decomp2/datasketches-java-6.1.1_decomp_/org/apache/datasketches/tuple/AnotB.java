package org.apache.datasketches.tuple;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SketchesStateException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.SetOperationCornerCases;

@SuppressFBWarnings(
   value = {"DP_DO_INSIDE_DO_PRIVILEGED"},
   justification = "Defer fix"
)
public final class AnotB {
   private boolean empty_ = true;
   private long thetaLong_ = Long.MAX_VALUE;
   private long[] hashArr_ = null;
   private Summary[] summaryArr_ = null;
   private int curCount_ = 0;
   private static final Method GET_CACHE;

   public void setA(Sketch skA) {
      if (skA == null) {
         this.reset();
         throw new SketchesArgumentException("The input argument <i>A</i> may not be null");
      } else {
         this.empty_ = skA.isEmpty();
         this.thetaLong_ = skA.getThetaLong();
         DataArrays<S> da = getCopyOfDataArraysTuple(skA);
         this.summaryArr_ = da.summaryArr;
         this.hashArr_ = da.hashArr;
         this.curCount_ = this.hashArr_ == null ? 0 : this.hashArr_.length;
      }
   }

   public void notB(Sketch skB) {
      if (skB != null) {
         long thetaLongB = skB.getThetaLong();
         int countB = skB.getRetainedEntries();
         boolean emptyB = skB.isEmpty();
         int id = SetOperationCornerCases.createCornerCaseId(this.thetaLong_, this.curCount_, this.empty_, thetaLongB, countB, emptyB);
         SetOperationCornerCases.CornerCase cCase = SetOperationCornerCases.CornerCase.caseIdToCornerCase(id);
         SetOperationCornerCases.AnotbAction anotbAction = cCase.getAnotbAction();
         switch (anotbAction) {
            case EMPTY_1_0_T:
               this.reset();
               break;
            case DEGEN_MIN_0_F:
               this.reset();
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongB);
               this.empty_ = false;
               break;
            case DEGEN_THA_0_F:
               this.empty_ = false;
               this.curCount_ = 0;
               break;
            case TRIM_A:
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongB);
               DataArrays<S> da = trimAndCopyDataArrays(this.hashArr_, this.summaryArr_, this.thetaLong_, true);
               this.hashArr_ = da.hashArr;
               this.curCount_ = this.hashArr_ == null ? 0 : this.hashArr_.length;
               this.summaryArr_ = da.summaryArr;
            case SKETCH_A:
            default:
               break;
            case FULL_ANOTB:
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongB);
               DataArrays<S> daR = getCopyOfResultArraysTuple(this.thetaLong_, this.curCount_, this.hashArr_, this.summaryArr_, skB);
               this.hashArr_ = daR.hashArr;
               this.curCount_ = this.hashArr_ == null ? 0 : this.hashArr_.length;
               this.summaryArr_ = daR.summaryArr;
         }

      }
   }

   public void notB(org.apache.datasketches.theta.Sketch skB) {
      if (skB != null) {
         long thetaLongB = skB.getThetaLong();
         int countB = skB.getRetainedEntries();
         boolean emptyB = skB.isEmpty();
         int id = SetOperationCornerCases.createCornerCaseId(this.thetaLong_, this.curCount_, this.empty_, thetaLongB, countB, emptyB);
         SetOperationCornerCases.CornerCase cCase = SetOperationCornerCases.CornerCase.caseIdToCornerCase(id);
         SetOperationCornerCases.AnotbAction anotbAction = cCase.getAnotbAction();
         switch (anotbAction) {
            case EMPTY_1_0_T:
               this.reset();
               break;
            case DEGEN_MIN_0_F:
               this.reset();
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongB);
               this.empty_ = false;
               break;
            case DEGEN_THA_0_F:
               this.empty_ = false;
               this.curCount_ = 0;
               break;
            case TRIM_A:
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongB);
               DataArrays<S> da = trimAndCopyDataArrays(this.hashArr_, this.summaryArr_, this.thetaLong_, true);
               this.hashArr_ = da.hashArr;
               this.curCount_ = this.hashArr_ == null ? 0 : this.hashArr_.length;
               this.summaryArr_ = da.summaryArr;
            case SKETCH_A:
            default:
               break;
            case FULL_ANOTB:
               this.thetaLong_ = Math.min(this.thetaLong_, thetaLongB);
               DataArrays<S> daB = getCopyOfResultArraysTheta(this.thetaLong_, this.curCount_, this.hashArr_, this.summaryArr_, skB);
               this.hashArr_ = daB.hashArr;
               this.curCount_ = this.hashArr_ == null ? 0 : this.hashArr_.length;
               this.summaryArr_ = daB.summaryArr;
         }

      }
   }

   public CompactSketch getResult(boolean reset) {
      CompactSketch<S> result;
      if (this.curCount_ == 0) {
         result = new CompactSketch((long[])null, (Summary[])null, this.thetaLong_, this.thetaLong_ == Long.MAX_VALUE);
      } else {
         result = new CompactSketch(this.hashArr_, Util.copySummaryArray(this.summaryArr_), this.thetaLong_, false);
      }

      if (reset) {
         this.reset();
      }

      return result;
   }

   @SuppressFBWarnings(
      value = {"UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR"},
      justification = "hashArr and summaryArr are guaranteed to be valid due to the switch on CornerCase"
   )
   public static CompactSketch aNotB(Sketch skA, Sketch skB) {
      if (skA != null && skB != null) {
         long thetaLongA = skA.getThetaLong();
         int countA = skA.getRetainedEntries();
         boolean emptyA = skA.isEmpty();
         long thetaLongB = skB.getThetaLong();
         int countB = skB.getRetainedEntries();
         boolean emptyB = skB.isEmpty();
         int id = SetOperationCornerCases.createCornerCaseId(thetaLongA, countA, emptyA, thetaLongB, countB, emptyB);
         SetOperationCornerCases.CornerCase cCase = SetOperationCornerCases.CornerCase.caseIdToCornerCase(id);
         SetOperationCornerCases.AnotbAction anotbAction = cCase.getAnotbAction();
         CompactSketch<S> result = null;
         switch (anotbAction) {
            case EMPTY_1_0_T:
               result = new CompactSketch((long[])null, (Summary[])null, Long.MAX_VALUE, true);
               break;
            case DEGEN_MIN_0_F:
               long thetaLong = Math.min(thetaLongA, thetaLongB);
               result = new CompactSketch((long[])null, (Summary[])null, thetaLong, false);
               break;
            case DEGEN_THA_0_F:
               result = new CompactSketch((long[])null, (Summary[])null, thetaLongA, false);
               break;
            case TRIM_A:
               DataArrays<S> daA = getCopyOfDataArraysTuple(skA);
               long[] hashArrA = daA.hashArr;
               S[] summaryArrA = (S[])daA.summaryArr;
               long minThetaLong = Math.min(thetaLongA, thetaLongB);
               DataArrays<S> da = trimAndCopyDataArrays(hashArrA, summaryArrA, minThetaLong, false);
               result = new CompactSketch(da.hashArr, da.summaryArr, minThetaLong, skA.empty_);
               break;
            case SKETCH_A:
               DataArrays<S> daA = getCopyOfDataArraysTuple(skA);
               result = new CompactSketch(daA.hashArr, daA.summaryArr, thetaLongA, skA.empty_);
               break;
            case FULL_ANOTB:
               DataArrays<S> daA = getCopyOfDataArraysTuple(skA);
               long minThetaLong = Math.min(thetaLongA, thetaLongB);
               DataArrays<S> daR = getCopyOfResultArraysTuple(minThetaLong, daA.hashArr.length, daA.hashArr, daA.summaryArr, skB);
               int countR = daR.hashArr == null ? 0 : daR.hashArr.length;
               if (countR == 0) {
                  result = new CompactSketch((long[])null, (Summary[])null, minThetaLong, minThetaLong == Long.MAX_VALUE);
               } else {
                  result = new CompactSketch(daR.hashArr, daR.summaryArr, minThetaLong, false);
               }
         }

         return result;
      } else {
         throw new SketchesArgumentException("Neither argument may be null for this stateless operation.");
      }
   }

   @SuppressFBWarnings(
      value = {"UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR"},
      justification = "hashArr and summaryArr are guaranteed to be valid due to the switch on CornerCase"
   )
   public static CompactSketch aNotB(Sketch skA, org.apache.datasketches.theta.Sketch skB) {
      if (skA != null && skB != null) {
         long thetaLongA = skA.getThetaLong();
         int countA = skA.getRetainedEntries();
         boolean emptyA = skA.isEmpty();
         long thetaLongB = skB.getThetaLong();
         int countB = skB.getRetainedEntries();
         boolean emptyB = skB.isEmpty();
         int id = SetOperationCornerCases.createCornerCaseId(thetaLongA, countA, emptyA, thetaLongB, countB, emptyB);
         SetOperationCornerCases.CornerCase cCase = SetOperationCornerCases.CornerCase.caseIdToCornerCase(id);
         SetOperationCornerCases.AnotbAction anotbAction = cCase.getAnotbAction();
         CompactSketch<S> result = null;
         switch (anotbAction) {
            case EMPTY_1_0_T:
               result = new CompactSketch((long[])null, (Summary[])null, Long.MAX_VALUE, true);
               break;
            case DEGEN_MIN_0_F:
               long thetaLong = Math.min(thetaLongA, thetaLongB);
               result = new CompactSketch((long[])null, (Summary[])null, thetaLong, false);
               break;
            case DEGEN_THA_0_F:
               result = new CompactSketch((long[])null, (Summary[])null, thetaLongA, false);
               break;
            case TRIM_A:
               DataArrays<S> daA = getCopyOfDataArraysTuple(skA);
               long[] hashArrA = daA.hashArr;
               S[] summaryArrA = (S[])daA.summaryArr;
               long minThetaLong = Math.min(thetaLongA, thetaLongB);
               DataArrays<S> da = trimAndCopyDataArrays(hashArrA, summaryArrA, minThetaLong, false);
               result = new CompactSketch(da.hashArr, da.summaryArr, minThetaLong, skA.empty_);
               break;
            case SKETCH_A:
               DataArrays<S> daA = getCopyOfDataArraysTuple(skA);
               result = new CompactSketch(daA.hashArr, daA.summaryArr, thetaLongA, skA.empty_);
               break;
            case FULL_ANOTB:
               DataArrays<S> daA = getCopyOfDataArraysTuple(skA);
               long minThetaLong = Math.min(thetaLongA, thetaLongB);
               DataArrays<S> daR = getCopyOfResultArraysTheta(minThetaLong, daA.hashArr.length, daA.hashArr, daA.summaryArr, skB);
               int countR = daR.hashArr == null ? 0 : daR.hashArr.length;
               if (countR == 0) {
                  result = new CompactSketch((long[])null, (Summary[])null, minThetaLong, minThetaLong == Long.MAX_VALUE);
               } else {
                  result = new CompactSketch(daR.hashArr, daR.summaryArr, minThetaLong, false);
               }
         }

         return result;
      } else {
         throw new SketchesArgumentException("Neither argument may be null for this stateless operation.");
      }
   }

   private static DataArrays getCopyOfDataArraysTuple(Sketch sk) {
      DataArrays<S> da = new DataArrays();
      CompactSketch<S> csk;
      if (sk instanceof CompactSketch) {
         csk = (CompactSketch)sk;
      } else {
         csk = ((QuickSelectSketch)sk).compact();
      }

      int count = csk.getRetainedEntries();
      if (count == 0) {
         da.hashArr = null;
         da.summaryArr = null;
      } else {
         da.hashArr = (long[])csk.getHashArr().clone();
         da.summaryArr = Util.copySummaryArray(csk.getSummaryArr());
      }

      return da;
   }

   private static DataArrays getCopyOfResultArraysTuple(long minThetaLong, int countA, long[] hashArrA, Summary[] summaryArrA, Sketch skB) {
      DataArrays<S> daR = new DataArrays();
      long[] hashTableB;
      if (skB instanceof CompactSketch) {
         CompactSketch<S> cskB = (CompactSketch)skB;
         int countB = skB.getRetainedEntries();
         hashTableB = HashOperations.convertToHashTable(cskB.getHashArr(), countB, minThetaLong, (double)0.9375F);
      } else {
         QuickSelectSketch<S> qskB = (QuickSelectSketch)skB;
         hashTableB = qskB.getHashTable();
      }

      long[] tmpHashArrA = new long[countA];
      S[] tmpSummaryArrA = (S[])Util.newSummaryArray(summaryArrA, countA);
      int lgHTBLen = org.apache.datasketches.common.Util.exactLog2OfLong((long)hashTableB.length);
      int nonMatches = 0;

      for(int i = 0; i < countA; ++i) {
         long hash = hashArrA[i];
         if (hash != 0L && hash < minThetaLong) {
            int index = HashOperations.hashSearch(hashTableB, lgHTBLen, hash);
            if (index == -1) {
               tmpHashArrA[nonMatches] = hash;
               tmpSummaryArrA[nonMatches] = summaryArrA[i].copy();
               ++nonMatches;
            }
         }
      }

      daR.hashArr = Arrays.copyOfRange(tmpHashArrA, 0, nonMatches);
      daR.summaryArr = (Summary[])Arrays.copyOfRange(tmpSummaryArrA, 0, nonMatches);
      return daR;
   }

   private static DataArrays getCopyOfResultArraysTheta(long minThetaLong, int countA, long[] hashArrA, Summary[] summaryArrA, org.apache.datasketches.theta.Sketch skB) {
      DataArrays<S> daB = new DataArrays();

      long[] hashCacheB;
      try {
         hashCacheB = (long[])GET_CACHE.invoke(skB);
      } catch (Exception e) {
         throw new SketchesStateException("Reflection Exception " + e);
      }

      long[] hashTableB;
      if (skB instanceof org.apache.datasketches.theta.CompactSketch) {
         int countB = skB.getRetainedEntries(true);
         hashTableB = HashOperations.convertToHashTable(hashCacheB, countB, minThetaLong, (double)0.9375F);
      } else {
         hashTableB = hashCacheB;
      }

      long[] tmpHashArrA = new long[countA];
      S[] tmpSummaryArrA = (S[])Util.newSummaryArray(summaryArrA, countA);
      int lgHTBLen = org.apache.datasketches.common.Util.exactLog2OfLong((long)hashTableB.length);
      int nonMatches = 0;

      for(int i = 0; i < countA; ++i) {
         long hash = hashArrA[i];
         if (hash != 0L && hash < minThetaLong) {
            int index = HashOperations.hashSearch(hashTableB, lgHTBLen, hash);
            if (index == -1) {
               tmpHashArrA[nonMatches] = hash;
               tmpSummaryArrA[nonMatches] = summaryArrA[i].copy();
               ++nonMatches;
            }
         }
      }

      daB.hashArr = Arrays.copyOfRange(tmpHashArrA, 0, nonMatches);
      daB.summaryArr = (Summary[])Arrays.copyOfRange(tmpSummaryArrA, 0, nonMatches);
      return daB;
   }

   private static DataArrays trimAndCopyDataArrays(long[] hashArr, Summary[] summaryArr, long minThetaLong, boolean copy) {
      int countIn = hashArr.length;
      long[] tmpHashArr = new long[countIn];
      S[] tmpSummaryArr = (S[])Util.newSummaryArray(summaryArr, countIn);
      int countResult = 0;

      for(int i = 0; i < countIn; ++i) {
         long hash = hashArr[i];
         if (hash < minThetaLong) {
            tmpHashArr[countResult] = hash;
            tmpSummaryArr[countResult] = copy ? summaryArr[i].copy() : summaryArr[i];
            ++countResult;
         }
      }

      DataArrays<S> da = new DataArrays();
      da.hashArr = Arrays.copyOfRange(tmpHashArr, 0, countResult);
      da.summaryArr = (Summary[])Arrays.copyOfRange(tmpSummaryArr, 0, countResult);
      return da;
   }

   public void reset() {
      this.empty_ = true;
      this.thetaLong_ = Long.MAX_VALUE;
      this.hashArr_ = null;
      this.summaryArr_ = null;
      this.curCount_ = 0;
   }

   static {
      try {
         GET_CACHE = org.apache.datasketches.theta.Sketch.class.getDeclaredMethod("getCache");
         GET_CACHE.setAccessible(true);
      } catch (Exception e) {
         throw new SketchesStateException("Could not reflect getCache(): " + e);
      }
   }

   static class DataArrays {
      long[] hashArr;
      Summary[] summaryArr;
   }
}
