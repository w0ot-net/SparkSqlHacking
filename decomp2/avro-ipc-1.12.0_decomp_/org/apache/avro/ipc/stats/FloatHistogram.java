package org.apache.avro.ipc.stats;

class FloatHistogram extends Histogram {
   private float runningSum;
   private float runningSumOfSquares;

   public FloatHistogram(Histogram.Segmenter segmenter) {
      super(segmenter);
   }

   public void add(Float value) {
      super.add(value);
      this.runningSum += value;
      this.runningSumOfSquares += value * value;
   }

   public float getMean() {
      return this.totalCount == 0 ? Float.NaN : this.runningSum / (float)this.totalCount;
   }

   public float getUnbiasedStdDev() {
      if (this.totalCount <= 1) {
         return Float.NaN;
      } else {
         float mean = this.getMean();
         return (float)Math.sqrt((double)((this.runningSumOfSquares - (float)this.totalCount * mean * mean) / (float)(this.totalCount - 1)));
      }
   }
}
