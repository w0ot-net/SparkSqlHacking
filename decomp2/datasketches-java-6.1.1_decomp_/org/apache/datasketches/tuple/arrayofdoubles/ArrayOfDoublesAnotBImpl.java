package org.apache.datasketches.tuple.arrayofdoubles;

import java.util.Arrays;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.common.SuppressFBWarnings;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.HashOperations;
import org.apache.datasketches.thetacommon.SetOperationCornerCases;
import org.apache.datasketches.tuple.Util;

public class ArrayOfDoublesAnotBImpl extends ArrayOfDoublesAnotB {
   private int numValues_;
   private short seedHash_;
   private long thetaLong_ = Long.MAX_VALUE;
   private boolean empty_ = true;
   private long[] keys_;
   private double[] values_;
   private int count_;

   ArrayOfDoublesAnotBImpl(int numValues, long seed) {
      this.numValues_ = numValues;
      this.seedHash_ = Util.computeSeedHash(seed);
   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP2"},
      justification = "This is OK here"
   )
   public void update(ArrayOfDoublesSketch skA, ArrayOfDoublesSketch skB) {
      if (skA != null && skB != null) {
         this.numValues_ = skA.getNumValues();
         this.seedHash_ = skA.getSeedHash();
         if (this.numValues_ != skB.getNumValues()) {
            throw new SketchesArgumentException("Inputs cannot have different numValues");
         } else if (this.seedHash_ != skB.getSeedHash()) {
            throw new SketchesArgumentException("Inputs cannot have different seedHashes");
         } else {
            long thetaLongA = skA.getThetaLong();
            int countA = skA.getRetainedEntries();
            boolean emptyA = skA.isEmpty();
            long thetaLongB = skB.getThetaLong();
            int countB = skB.getRetainedEntries();
            boolean emptyB = skB.isEmpty();
            int id = SetOperationCornerCases.createCornerCaseId(thetaLongA, countA, emptyA, thetaLongB, countB, emptyB);
            SetOperationCornerCases.CornerCase cCase = SetOperationCornerCases.CornerCase.caseIdToCornerCase(id);
            SetOperationCornerCases.AnotbAction anotbAction = cCase.getAnotbAction();
            long minThetaLong = Math.min(thetaLongA, thetaLongB);
            switch (anotbAction) {
               case EMPTY_1_0_T:
                  this.reset();
                  break;
               case DEGEN_MIN_0_F:
                  this.keys_ = null;
                  this.values_ = null;
                  this.thetaLong_ = minThetaLong;
                  this.empty_ = false;
                  this.count_ = 0;
                  break;
               case DEGEN_THA_0_F:
                  this.keys_ = null;
                  this.values_ = null;
                  this.thetaLong_ = thetaLongA;
                  this.empty_ = false;
                  this.count_ = 0;
                  break;
               case TRIM_A:
                  DataArrays daA = new DataArrays(skA.getKeys(), skA.getValuesAsOneDimension(), countA);
                  DataArrays da = trimDataArrays(daA, minThetaLong, this.numValues_);
                  this.keys_ = da.hashArr;
                  this.values_ = da.valuesArr;
                  this.thetaLong_ = minThetaLong;
                  this.empty_ = skA.isEmpty();
                  this.count_ = da.count;
                  break;
               case SKETCH_A:
                  ArrayOfDoublesCompactSketch csk = skA.compact();
                  this.keys_ = csk.getKeys();
                  this.values_ = csk.getValuesAsOneDimension();
                  this.thetaLong_ = csk.thetaLong_;
                  this.empty_ = csk.isEmpty();
                  this.count_ = csk.getRetainedEntries();
                  break;
               case FULL_ANOTB:
                  long[] keysA = skA.getKeys();
                  double[] valuesA = skA.getValuesAsOneDimension();
                  DataArrays daR = getResultArrays(minThetaLong, countA, keysA, valuesA, skB);
                  this.count_ = daR.count;
                  this.keys_ = this.count_ == 0 ? null : daR.hashArr;
                  this.values_ = this.count_ == 0 ? null : daR.valuesArr;
                  this.thetaLong_ = minThetaLong;
                  this.empty_ = minThetaLong == Long.MAX_VALUE && this.count_ == 0;
            }

         }
      } else {
         throw new SketchesArgumentException("Neither argument may be null.");
      }
   }

   public ArrayOfDoublesCompactSketch getResult() {
      return new HeapArrayOfDoublesCompactSketch(this.keys_, this.values_, this.thetaLong_, this.empty_, this.numValues_, this.seedHash_);
   }

   public ArrayOfDoublesCompactSketch getResult(WritableMemory dstMem) {
      return new DirectArrayOfDoublesCompactSketch(this.keys_, this.values_, this.thetaLong_, this.empty_, this.numValues_, this.seedHash_, dstMem);
   }

   private static DataArrays getResultArrays(long minThetaLong, int countA, long[] hashArrA, double[] valuesArrA, ArrayOfDoublesSketch skB) {
      int numValues = skB.numValues_;
      long[] hashTableB = HashOperations.convertToHashTable(skB.getKeys(), skB.getRetainedEntries(), minThetaLong, (double)0.9375F);
      long[] tmpHashArrA = new long[countA];
      double[] tmpValuesArrA = new double[countA * numValues];
      int lgHTBLen = org.apache.datasketches.common.Util.exactLog2OfLong((long)hashTableB.length);
      int nonMatches = 0;

      for(int i = 0; i < countA; ++i) {
         long hash = hashArrA[i];
         if (!HashOperations.continueCondition(minThetaLong, hash)) {
            int index = HashOperations.hashSearch(hashTableB, lgHTBLen, hash);
            if (index == -1) {
               tmpHashArrA[nonMatches] = hash;
               System.arraycopy(valuesArrA, i * numValues, tmpValuesArrA, nonMatches * numValues, numValues);
               ++nonMatches;
            }
         }
      }

      tmpHashArrA = Arrays.copyOf(tmpHashArrA, nonMatches);
      tmpValuesArrA = Arrays.copyOf(tmpValuesArrA, nonMatches * numValues);
      DataArrays daR = new DataArrays(tmpHashArrA, tmpValuesArrA, nonMatches);
      return daR;
   }

   private static DataArrays trimDataArrays(DataArrays da, long thetaLong, int numValues) {
      long[] hashArrIn = da.hashArr;
      double[] valuesArrIn = da.valuesArr;
      int count = HashOperations.count(hashArrIn, thetaLong);
      long[] hashArrOut = new long[count];
      double[] valuesArrOut = new double[count * numValues];
      int vaInIdx = 0;
      int haOutIdx = 0;
      int vaOutIdx = 0;

      for(int haInIdx = 0; haInIdx < count; vaInIdx += numValues) {
         long hash = hashArrIn[haInIdx];
         if (!HashOperations.continueCondition(thetaLong, hash)) {
            hashArrOut[haOutIdx] = hashArrIn[haInIdx];
            System.arraycopy(valuesArrIn, vaInIdx, valuesArrOut, vaOutIdx, numValues);
            ++haOutIdx;
            vaOutIdx += numValues;
         }

         ++haInIdx;
      }

      return new DataArrays(hashArrOut, valuesArrOut, count);
   }

   private void reset() {
      this.empty_ = true;
      this.thetaLong_ = Long.MAX_VALUE;
      this.keys_ = null;
      this.values_ = null;
      this.count_ = 0;
   }

   private static class DataArrays {
      long[] hashArr;
      double[] valuesArr;
      int count;

      DataArrays(long[] hashArr, double[] valuesArr, int count) {
         this.hashArr = hashArr;
         this.valuesArr = valuesArr;
         this.count = count;
      }
   }
}
