package org.apache.commons.math3.random;

import org.apache.commons.math3.util.FastMath;

public class UniformRandomGenerator implements NormalizedRandomGenerator {
   private static final double SQRT3 = FastMath.sqrt((double)3.0F);
   private final RandomGenerator generator;

   public UniformRandomGenerator(RandomGenerator generator) {
      this.generator = generator;
   }

   public double nextNormalizedDouble() {
      return SQRT3 * ((double)2.0F * this.generator.nextDouble() - (double)1.0F);
   }
}
