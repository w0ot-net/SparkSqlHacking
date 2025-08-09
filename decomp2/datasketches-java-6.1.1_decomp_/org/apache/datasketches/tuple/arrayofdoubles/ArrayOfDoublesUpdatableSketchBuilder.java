package org.apache.datasketches.tuple.arrayofdoubles;

import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.memory.WritableMemory;
import org.apache.datasketches.thetacommon.ThetaUtil;

public class ArrayOfDoublesUpdatableSketchBuilder {
   private int nomEntries_ = 4096;
   private ResizeFactor resizeFactor_;
   private int numValues_;
   private float samplingProbability_;
   private long seed_;
   private static final int DEFAULT_NUMBER_OF_VALUES = 1;
   private static final float DEFAULT_SAMPLING_PROBABILITY = 1.0F;
   private static final ResizeFactor DEFAULT_RESIZE_FACTOR;

   public ArrayOfDoublesUpdatableSketchBuilder() {
      this.resizeFactor_ = DEFAULT_RESIZE_FACTOR;
      this.numValues_ = 1;
      this.samplingProbability_ = 1.0F;
      this.seed_ = 9001L;
   }

   public ArrayOfDoublesUpdatableSketchBuilder setNominalEntries(int nomEntries) {
      this.nomEntries_ = 1 << ThetaUtil.checkNomLongs(nomEntries);
      return this;
   }

   public ArrayOfDoublesUpdatableSketchBuilder setResizeFactor(ResizeFactor resizeFactor) {
      this.resizeFactor_ = resizeFactor;
      return this;
   }

   public ArrayOfDoublesUpdatableSketchBuilder setSamplingProbability(float samplingProbability) {
      if (!(samplingProbability < 0.0F) && !(samplingProbability > 1.0F)) {
         this.samplingProbability_ = samplingProbability;
         return this;
      } else {
         throw new SketchesArgumentException("sampling probability must be between 0 and 1");
      }
   }

   public ArrayOfDoublesUpdatableSketchBuilder setNumberOfValues(int numValues) {
      this.numValues_ = numValues;
      return this;
   }

   public ArrayOfDoublesUpdatableSketchBuilder setSeed(long seed) {
      this.seed_ = seed;
      return this;
   }

   public ArrayOfDoublesUpdatableSketch build() {
      return new HeapArrayOfDoublesQuickSelectSketch(this.nomEntries_, this.resizeFactor_.lg(), this.samplingProbability_, this.numValues_, this.seed_);
   }

   public ArrayOfDoublesUpdatableSketch build(WritableMemory dstMem) {
      return new DirectArrayOfDoublesQuickSelectSketch(this.nomEntries_, this.resizeFactor_.lg(), this.samplingProbability_, this.numValues_, this.seed_, dstMem);
   }

   static {
      DEFAULT_RESIZE_FACTOR = ResizeFactor.X8;
   }
}
