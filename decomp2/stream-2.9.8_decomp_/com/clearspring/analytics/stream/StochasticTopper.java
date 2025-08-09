package com.clearspring.analytics.stream;

import java.util.List;
import java.util.Random;

public class StochasticTopper implements ITopK {
   private int sampleSize;
   private ISampleSet sample;
   private Random random;
   private long count;

   public StochasticTopper(int sampleSize) {
      this(sampleSize, (Long)null);
   }

   public StochasticTopper(int sampleSize, Long seed) {
      this.sample = new SampleSet(sampleSize);
      this.sampleSize = sampleSize;
      if (seed != null) {
         this.random = new Random(seed);
      } else {
         this.random = new Random();
      }

   }

   public boolean offer(Object item, int incrementCount) {
      ++this.count;
      boolean taken = false;
      if (this.sample.count() < (long)this.sampleSize) {
         this.sample.put(item, incrementCount);
         taken = true;
      } else if (this.random.nextDouble() < (double)this.sampleSize / (double)this.count) {
         this.sample.removeRandom();
         this.sample.put(item, incrementCount);
         taken = true;
      }

      return taken;
   }

   public boolean offer(Object item) {
      return this.offer(item, 1);
   }

   public List peek(int k) {
      return this.sample.peek(k);
   }
}
