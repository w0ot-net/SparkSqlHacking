package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.Util;
import org.apache.datasketches.thetacommon.HashOperations;

class HashTables {
   private long[] hashTable = null;
   private double[][] valueTable = (double[][])null;
   private int numValues = 0;
   private int lgTableSize = 0;
   private int numKeys = 0;

   HashTables(ArrayOfDoublesSketch sketchIn) {
      this.numKeys = sketchIn.getRetainedEntries();
      this.numValues = sketchIn.getNumValues();
      this.lgTableSize = getLgTableSize(this.numKeys);
      int tableSize = 1 << this.lgTableSize;
      this.hashTable = new long[tableSize];
      this.valueTable = new double[tableSize][];
      ArrayOfDoublesSketchIterator it = sketchIn.iterator();

      while(it.next()) {
         long hash = it.getKey();
         int index = HashOperations.hashInsertOnly(this.hashTable, this.lgTableSize, hash);
         this.valueTable[index] = new double[this.numValues];
         System.arraycopy(it.getValues(), 0, this.valueTable[index], 0, this.numValues);
      }

   }

   private HashTables(long[] hashArr, double[][] valuesArr, int numKeys, int numValues) {
      this.numValues = numValues;
      this.numKeys = numKeys;
      this.lgTableSize = getLgTableSize(numKeys);
      int tableSize = 1 << this.lgTableSize;
      this.hashTable = new long[tableSize];
      this.valueTable = new double[tableSize][];

      for(int i = 0; i < numKeys; ++i) {
         long hash = hashArr[i];
         int index = HashOperations.hashInsertOnly(this.hashTable, this.lgTableSize, hash);
         this.valueTable[index] = new double[numValues];
         System.arraycopy(valuesArr[i], 0, this.valueTable[index], 0, numValues);
      }

   }

   HashTables getIntersectHashTables(ArrayOfDoublesSketch nextTupleSketch, long thetaLong, ArrayOfDoublesCombiner combiner) {
      int maxMatchSize = Math.min(this.numKeys, nextTupleSketch.getRetainedEntries());

      assert this.numValues == nextTupleSketch.numValues_;

      long[] matchHashArr = new long[maxMatchSize];
      double[][] matchValuesArr = new double[maxMatchSize][];
      int matchCount = 0;
      ArrayOfDoublesSketchIterator it = nextTupleSketch.iterator();

      while(it.next()) {
         long hash = it.getKey();
         if (hash < thetaLong) {
            int index = HashOperations.hashSearch(this.hashTable, this.lgTableSize, hash);
            if (index >= 0) {
               matchHashArr[matchCount] = hash;
               matchValuesArr[matchCount] = combiner.combine(this.valueTable[index], it.getValues());
               ++matchCount;
            }
         }
      }

      return new HashTables(matchHashArr, matchValuesArr, matchCount, this.numValues);
   }

   int getNumKeys() {
      return this.numKeys;
   }

   int getNumValues() {
      return this.numValues;
   }

   long[] getHashTable() {
      return this.hashTable;
   }

   double[][] getValueTable() {
      return this.valueTable;
   }

   void clear() {
      this.hashTable = null;
      this.valueTable = (double[][])null;
      this.numValues = 0;
      this.lgTableSize = 0;
      this.numKeys = 0;
   }

   static int getLgTableSize(int numKeys) {
      int tableSize = Math.max(Util.ceilingPowerOf2((int)Math.ceil((double)numKeys / (double)0.75F)), 16);
      return Integer.numberOfTrailingZeros(tableSize);
   }
}
