package spire.random.rng;

import java.util.concurrent.atomic.AtomicLong;
import spire.random.GeneratorCompanion;
import spire.util.Pack.;

public final class PcgXshRr64_32$ implements GeneratorCompanion {
   public static final PcgXshRr64_32$ MODULE$ = new PcgXshRr64_32$();
   private static final AtomicLong streamUniquifier;

   static {
      GeneratorCompanion.$init$(MODULE$);
      streamUniquifier = new AtomicLong((long)System.identityHashCode(MODULE$));
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

   public PcgSeed64 randomSeed() {
      return new PcgSeed64(System.nanoTime(), this.nextStreamId());
   }

   public PcgXshRr64_32 fromTime(final long time) {
      return this.fromSeed(new PcgSeed64(time, this.nextStreamId()));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   public PcgXshRr64_32 fromSeed(final PcgSeed64 seed) {
      PcgXshRr64_32 gen = new PcgXshRr64_32(0L, 0L);
      gen.seed(seed);
      return gen;
   }

   public PcgXshRr64_32 fromBytes(final byte[] bytes) {
      long[] longs = .MODULE$.longsFromBytes(bytes, 2);
      return this.fromSeed(new PcgSeed64(longs[0], longs[1]));
   }

   private long nextStreamId() {
      long current;
      long next;
      do {
         current = streamUniquifier.get();
         next = current * 181783497276652981L;
      } while(!streamUniquifier.compareAndSet(current, next));

      return next;
   }

   private PcgXshRr64_32$() {
   }
}
