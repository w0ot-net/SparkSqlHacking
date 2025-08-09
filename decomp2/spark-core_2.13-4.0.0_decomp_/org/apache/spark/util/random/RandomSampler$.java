package org.apache.spark.util.random;

import java.io.Serializable;
import java.util.Random;
import scala.runtime.ModuleSerializationProxy;

public final class RandomSampler$ implements Serializable {
   public static final RandomSampler$ MODULE$ = new RandomSampler$();
   private static final double defaultMaxGapSamplingFraction = 0.4;
   private static final double rngEpsilon = 5.0E-11;
   private static final double roundingEpsilon = 1.0E-6;

   public Random newDefaultRNG() {
      return new XORShiftRandom();
   }

   public double defaultMaxGapSamplingFraction() {
      return defaultMaxGapSamplingFraction;
   }

   public double rngEpsilon() {
      return rngEpsilon;
   }

   public double roundingEpsilon() {
      return roundingEpsilon;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RandomSampler$.class);
   }

   private RandomSampler$() {
   }
}
