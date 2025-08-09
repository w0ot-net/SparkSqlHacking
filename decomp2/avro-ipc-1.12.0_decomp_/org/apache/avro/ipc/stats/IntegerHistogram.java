package org.apache.avro.ipc.stats;

class IntegerHistogram extends Histogram {
   private float runningSum;
   private float runningSumOfSquares;

   public IntegerHistogram(Histogram.Segmenter segmenter) {
      super(segmenter);
   }

   public void add(Integer value) {
      super.add(value);
      this.runningSum += (float)value;
      this.runningSumOfSquares += (float)(value * value);
   }

   public float getMean() {
      return this.totalCount == 0 ? -1.0F : this.runningSum / (float)this.totalCount;
   }

   public float getUnbiasedStdDev() {
      if (this.totalCount <= 1) {
         return -1.0F;
      } else {
         float mean = this.getMean();
         return (float)Math.sqrt((double)((this.runningSumOfSquares - (float)this.totalCount * mean * mean) / (float)(this.totalCount - 1)));
      }
   }
}
