package spire.random.rng;

import java.nio.ByteBuffer;
import java.util.Arrays;
import spire.random.GeneratorCompanion;
import spire.random.GlobalRng$;

public final class Cmwc5$ implements GeneratorCompanion {
   public static final Cmwc5$ MODULE$ = new Cmwc5$();

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

   public long[] randomSeed() {
      return GlobalRng$.MODULE$.generateLongs(5);
   }

   public Cmwc5 fromBytes(final byte[] bytes) {
      ByteBuffer bb = ByteBuffer.wrap(bytes);
      long x = bb.getLong();
      long y = bb.getLong();
      long z = bb.getLong();
      long w = bb.getLong();
      long v = bb.getLong();
      return new Cmwc5(x, y, z, w, v);
   }

   public Cmwc5 fromSeed(final long[] seed) {
      long[] zs = seed.length < 5 ? Arrays.copyOf(seed, 5) : seed;
      return new Cmwc5(zs[0], zs[1], zs[2], zs[3], zs[4]);
   }

   public Cmwc5 fromTime(final long time) {
      Lcg64 lcg = Lcg64$.MODULE$.fromTime(time);
      return new Cmwc5(lcg.nextLong(), lcg.nextLong(), lcg.nextLong(), lcg.nextLong(), lcg.nextLong());
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private Cmwc5$() {
   }
}
