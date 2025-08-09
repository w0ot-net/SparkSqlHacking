package spire.random.rng;

import spire.random.GeneratorCompanion;
import spire.random.GeneratorCompanion$mcI$sp;
import spire.util.Pack.;

public final class Lcg32$ implements GeneratorCompanion$mcI$sp {
   public static final Lcg32$ MODULE$ = new Lcg32$();

   static {
      GeneratorCompanion.$init$(MODULE$);
   }

   public final Object apply(final int seed) {
      return GeneratorCompanion$mcI$sp.apply$(this, seed);
   }

   public final Object apply$mcI$sp(final int seed) {
      return GeneratorCompanion$mcI$sp.apply$mcI$sp$(this, seed);
   }

   public long randomSeed$mcJ$sp() {
      return GeneratorCompanion.randomSeed$mcJ$sp$(this);
   }

   public Object fromSeed$mcJ$sp(final long seed) {
      return GeneratorCompanion.fromSeed$mcJ$sp$(this, seed);
   }

   public final Object apply() {
      return GeneratorCompanion.apply$(this);
   }

   public Object apply$mcJ$sp(final long seed) {
      return GeneratorCompanion.apply$mcJ$sp$(this, seed);
   }

   public int randomSeed() {
      return this.randomSeed$mcI$sp();
   }

   public Lcg32 fromBytes(final byte[] bytes) {
      return new Lcg32(.MODULE$.intFromBytes(bytes));
   }

   public Lcg32 fromSeed(final int seed) {
      return this.fromSeed$mcI$sp(seed);
   }

   public Lcg32 fromTime(final long time) {
      return new Lcg32((int)time);
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   public int step(final int n) {
      return 1664525 * n + 1013904223;
   }

   public int randomSeed$mcI$sp() {
      return (int)System.nanoTime();
   }

   public Lcg32 fromSeed$mcI$sp(final int seed) {
      return new Lcg32(seed);
   }

   private Lcg32$() {
   }
}
