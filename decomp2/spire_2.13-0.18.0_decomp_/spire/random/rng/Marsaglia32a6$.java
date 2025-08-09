package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import spire.random.GeneratorCompanion;
import spire.random.GlobalRng$;

public final class Marsaglia32a6$ implements GeneratorCompanion {
   public static final Marsaglia32a6$ MODULE$ = new Marsaglia32a6$();

   static {
      GeneratorCompanion.$init$(MODULE$);
   }

   public int randomSeed$mcI$sp() {
      return GeneratorCompanion.randomSeed$mcI$sp$(this);
   }

   public long randomSeed$mcJ$sp() {
      return GeneratorCompanion.randomSeed$mcJ$sp$(this);
   }

   public Object fromSeed$mcI$sp(final int seed) {
      return GeneratorCompanion.fromSeed$mcI$sp$(this, seed);
   }

   public Object fromSeed$mcJ$sp(final long seed) {
      return GeneratorCompanion.fromSeed$mcJ$sp$(this, seed);
   }

   public final Object apply() {
      return GeneratorCompanion.apply$(this);
   }

   public Object apply(final Object seed) {
      return GeneratorCompanion.apply$(this, seed);
   }

   public Object apply$mcI$sp(final int seed) {
      return GeneratorCompanion.apply$mcI$sp$(this, seed);
   }

   public Object apply$mcJ$sp(final long seed) {
      return GeneratorCompanion.apply$mcJ$sp$(this, seed);
   }

   public Marsaglia32a6 fromBytes(final byte[] bytes) {
      ByteBuffer bb = ByteBuffer.wrap(bytes);
      int x = bb.getInt();
      int y = bb.getInt();
      int z = bb.getInt();
      int w = bb.getInt();
      int v = bb.getInt();
      int d = bb.getInt();
      return new Marsaglia32a6(x, y, z, w, v, d);
   }

   public Marsaglia32a6 fromSeed(final int[] ints) {
      int[] zs = ints.length < 6 ? Arrays.copyOf(ints, 6) : ints;
      return new Marsaglia32a6(zs[0], zs[1], zs[2], zs[3], zs[4], zs[5]);
   }

   public Marsaglia32a6 fromTime(final long time) {
      Lcg64 lcg = Lcg64$.MODULE$.fromTime(time);
      int x = lcg.nextInt();
      int y = lcg.nextInt();
      int z = lcg.nextInt();
      int w = lcg.nextInt();
      int v = lcg.nextInt();
      int d = lcg.nextInt();
      return new Marsaglia32a6(x, y, z, w, v, d);
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   public int[] randomSeed() {
      return GlobalRng$.MODULE$.generateInts(6);
   }

   private Marsaglia32a6$() {
   }
}
