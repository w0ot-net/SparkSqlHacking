package spire.random.rng;

import spire.random.GeneratorCompanion;
import spire.random.GeneratorCompanion$mcJ$sp;
import spire.util.Pack.;

public final class Serial$ implements GeneratorCompanion$mcJ$sp {
   public static final Serial$ MODULE$ = new Serial$();

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

   public Serial fromBytes(final byte[] bytes) {
      return new Serial(.MODULE$.longFromBytes(bytes));
   }

   public Serial fromSeed(final long seed) {
      return this.fromSeed$mcJ$sp(seed);
   }

   public Serial fromTime(final long time) {
      return new Serial(time);
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   public long randomSeed$mcJ$sp() {
      return System.nanoTime();
   }

   public Serial fromSeed$mcJ$sp(final long seed) {
      return new Serial(seed);
   }

   private Serial$() {
   }
}
