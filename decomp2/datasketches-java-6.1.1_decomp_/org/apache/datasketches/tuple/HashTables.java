package org.apache.datasketches.tuple;

import java.lang.reflect.Array;
import org.apache.datasketches.theta.HashIterator;
import org.apache.datasketches.thetacommon.HashOperations;

class HashTables {
   long[] hashTable = null;
   Summary[] summaryTable = null;
   int lgTableSize = 0;
   int numKeys = 0;

   void fromSketch(Sketch sketch) {
      this.numKeys = sketch.getRetainedEntries();
      this.lgTableSize = getLgTableSize(this.numKeys);
      this.hashTable = new long[1 << this.lgTableSize];

      int index;
      S mySummary;
      for(TupleSketchIterator<S> it = sketch.iterator(); it.next(); this.summaryTable[index] = mySummary) {
         long hash = it.getHash();
         index = HashOperations.hashInsertOnly(this.hashTable, this.lgTableSize, hash);
         mySummary = (S)it.getSummary().copy();
         if (this.summaryTable == null) {
            this.summaryTable = (Summary[])Array.newInstance(mySummary.getClass(), 1 << this.lgTableSize);
         }
      }

   }

   void fromSketch(org.apache.datasketches.theta.Sketch sketch, Summary summary) {
      this.numKeys = sketch.getRetainedEntries(true);
      this.lgTableSize = getLgTableSize(this.numKeys);
      this.hashTable = new long[1 << this.lgTableSize];

      int index;
      S mySummary;
      for(HashIterator it = sketch.iterator(); it.next(); this.summaryTable[index] = mySummary) {
         long hash = it.get();
         index = HashOperations.hashInsertOnly(this.hashTable, this.lgTableSize, hash);
         mySummary = (S)summary.copy();
         if (this.summaryTable == null) {
            this.summaryTable = (Summary[])Array.newInstance(mySummary.getClass(), 1 << this.lgTableSize);
         }
      }

   }

   private void fromArrays(long[] hashArr, Summary[] summaryArr, int count) {
      this.numKeys = count;
      this.lgTableSize = getLgTableSize(count);
      this.summaryTable = null;
      this.hashTable = new long[1 << this.lgTableSize];

      for(int i = 0; i < count; ++i) {
         long hash = hashArr[i];
         int index = HashOperations.hashInsertOnly(this.hashTable, this.lgTableSize, hash);
         S mySummary = (S)summaryArr[i];
         if (this.summaryTable == null) {
            this.summaryTable = (Summary[])Array.newInstance(mySummary.getClass(), 1 << this.lgTableSize);
         }

         this.summaryTable[index] = summaryArr[i];
      }

   }

   HashTables getIntersectHashTables(Sketch nextTupleSketch, long thetaLong, SummarySetOperations summarySetOps) {
      int maxMatchSize = Math.min(this.numKeys, nextTupleSketch.getRetainedEntries());
      long[] matchHashArr = new long[maxMatchSize];
      S[] matchSummariesArr = (S[])Util.newSummaryArray(this.summaryTable, maxMatchSize);
      int matchCount = 0;
      TupleSketchIterator<S> it = nextTupleSketch.iterator();

      while(it.next()) {
         long hash = it.getHash();
         if (hash < thetaLong) {
            int index = HashOperations.hashSearch(this.hashTable, this.lgTableSize, hash);
            if (index >= 0) {
               matchHashArr[matchCount] = hash;
               matchSummariesArr[matchCount] = summarySetOps.intersection(this.summaryTable[index], it.getSummary());
               ++matchCount;
            }
         }
      }

      HashTables<S> resultHT = new HashTables();
      resultHT.fromArrays(matchHashArr, matchSummariesArr, matchCount);
      return resultHT;
   }

   HashTables getIntersectHashTables(org.apache.datasketches.theta.Sketch nextThetaSketch, long thetaLong, SummarySetOperations summarySetOps, Summary summary) {
      Class<S> summaryType = summary.getClass();
      int maxMatchSize = Math.min(this.numKeys, nextThetaSketch.getRetainedEntries());
      long[] matchHashArr = new long[maxMatchSize];
      S[] matchSummariesArr = (S[])((Summary[])((Summary[])Array.newInstance(summaryType, maxMatchSize)));
      int matchCount = 0;
      HashIterator it = nextThetaSketch.iterator();

      while(it.next()) {
         long hash = it.get();
         if (hash < thetaLong) {
            int index = HashOperations.hashSearch(this.hashTable, this.lgTableSize, hash);
            if (index >= 0) {
               matchHashArr[matchCount] = hash;
               matchSummariesArr[matchCount] = summarySetOps.intersection(this.summaryTable[index], summary);
               ++matchCount;
            }
         }
      }

      HashTables<S> resultHT = new HashTables();
      resultHT.fromArrays(matchHashArr, matchSummariesArr, matchCount);
      return resultHT;
   }

   void clear() {
      this.hashTable = null;
      this.summaryTable = null;
      this.lgTableSize = 0;
      this.numKeys = 0;
   }

   static int getLgTableSize(int count) {
      int tableSize = Math.max(org.apache.datasketches.common.Util.ceilingPowerOf2((int)Math.ceil((double)count / (double)0.75F)), 16);
      return Integer.numberOfTrailingZeros(tableSize);
   }
}
