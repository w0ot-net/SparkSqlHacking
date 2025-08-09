package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.memory.WritableMemory;

public class ArrayOfDoublesSetOperationBuilder {
   private int nomEntries_ = 4096;
   private int numValues_ = 1;
   private long seed_ = 9001L;
   public static final int DEFAULT_NOMINAL_ENTRIES = 4096;
   public static final int DEFAULT_NUMBER_OF_VALUES = 1;

   public ArrayOfDoublesSetOperationBuilder setNominalEntries(int nomEntries) {
      this.nomEntries_ = nomEntries;
      return this;
   }

   public ArrayOfDoublesSetOperationBuilder setNumberOfValues(int numValues) {
      this.numValues_ = numValues;
      return this;
   }

   public ArrayOfDoublesSetOperationBuilder setSeed(long seed) {
      this.seed_ = seed;
      return this;
   }

   public ArrayOfDoublesUnion buildUnion() {
      return new HeapArrayOfDoublesUnion(this.nomEntries_, this.numValues_, this.seed_);
   }

   public ArrayOfDoublesUnion buildUnion(WritableMemory dstMem) {
      return new DirectArrayOfDoublesUnion(this.nomEntries_, this.numValues_, this.seed_, dstMem);
   }

   public ArrayOfDoublesIntersection buildIntersection() {
      return new HeapArrayOfDoublesIntersection(this.numValues_, this.seed_);
   }

   public ArrayOfDoublesIntersection buildIntersection(WritableMemory dstMem) {
      return new DirectArrayOfDoublesIntersection(this.numValues_, this.seed_, dstMem);
   }

   public ArrayOfDoublesAnotB buildAnotB() {
      return new ArrayOfDoublesAnotBImpl(this.numValues_, this.seed_);
   }
}
