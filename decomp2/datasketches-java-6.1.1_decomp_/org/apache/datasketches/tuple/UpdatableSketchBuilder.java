package org.apache.datasketches.tuple;

import org.apache.datasketches.common.ResizeFactor;
import org.apache.datasketches.common.SketchesArgumentException;
import org.apache.datasketches.thetacommon.ThetaUtil;

public class UpdatableSketchBuilder {
   private int nomEntries_ = 4096;
   private ResizeFactor resizeFactor_;
   private float samplingProbability_;
   private final SummaryFactory summaryFactory_;
   private static final float DEFAULT_SAMPLING_PROBABILITY = 1.0F;
   private static final ResizeFactor DEFAULT_RESIZE_FACTOR;

   public UpdatableSketchBuilder(SummaryFactory summaryFactory) {
      this.resizeFactor_ = DEFAULT_RESIZE_FACTOR;
      this.samplingProbability_ = 1.0F;
      this.summaryFactory_ = summaryFactory;
   }

   public UpdatableSketchBuilder setNominalEntries(int nomEntries) {
      this.nomEntries_ = 1 << ThetaUtil.checkNomLongs(nomEntries);
      return this;
   }

   public UpdatableSketchBuilder setResizeFactor(ResizeFactor resizeFactor) {
      this.resizeFactor_ = resizeFactor;
      return this;
   }

   public UpdatableSketchBuilder setSamplingProbability(float samplingProbability) {
      if (!(samplingProbability < 0.0F) && !(samplingProbability > 1.0F)) {
         this.samplingProbability_ = samplingProbability;
         return this;
      } else {
         throw new SketchesArgumentException("sampling probability must be between 0 and 1");
      }
   }

   public UpdatableSketch build() {
      return new UpdatableSketch(this.nomEntries_, this.resizeFactor_.lg(), this.samplingProbability_, this.summaryFactory_);
   }

   public void reset() {
      this.nomEntries_ = 4096;
      this.resizeFactor_ = DEFAULT_RESIZE_FACTOR;
      this.samplingProbability_ = 1.0F;
   }

   static {
      DEFAULT_RESIZE_FACTOR = ResizeFactor.X8;
   }
}
