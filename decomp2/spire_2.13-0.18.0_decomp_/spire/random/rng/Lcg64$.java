package spire.random.rng;

import spire.random.GeneratorCompanion;
import spire.random.GeneratorCompanion$mcJ$sp;
import spire.util.Pack.;

public final class Lcg64$ implements GeneratorCompanion$mcJ$sp {
   public static final Lcg64$ MODULE$ = new Lcg64$();

   static {
      GeneratorCompanion.$init$(MODULE$);
   }

   public final Object apply(final long seed) {
      return GeneratorCompanion$mcJ$sp.apply$(this, seed);
   }

   public final Object apply$mcJ$sp(final long seed) {
      return GeneratorCompanion$mcJ$sp.apply$mcJ$sp$(this, seed);
   }

   public int randomSeed$mcI$sp() {
      return GeneratorCompanion.randomSeed$mcI$sp$(this);
   }

   public Object fromSeed$mcI$sp(final int seed) {
      return GeneratorCompanion.fromSeed$mcI$sp$(this, seed);
   }

   public final Object apply() {
      return GeneratorCompanion.apply$(this);
   }

   public Object apply$mcI$sp(final int seed) {
      return GeneratorCompanion.apply$mcI$sp$(this, seed);
   }

   public long randomSeed() {
      return this.randomSeed$mcJ$sp();
   }

   public Lcg64 fromBytes(final byte[] bytes) {
      return new Lcg64(.MODULE$.longFromBytes(bytes));
   }

   public Lcg64 fromSeed(final long seed) {
      return this.fromSeed$mcJ$sp(seed);
   }

   public Lcg64 fromTime(final long time) {
      return new Lcg64(time);
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   public long step(final long n) {
      return 6364136223846793005L * n + 1442695040888963407L;
   }

   public long randomSeed$mcJ$sp() {
      return System.nanoTime();
   }

   public Lcg64 fromSeed$mcJ$sp(final long seed) {
      return new Lcg64(seed);
   }

   private Lcg64$() {
   }
}
