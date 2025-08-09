package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class MersenneTwister64$ implements GeneratorCompanion {
   public static final MersenneTwister64$ MODULE$ = new MersenneTwister64$();
   private static final long spire$random$rng$MersenneTwister64$$UpperMask;
   private static final long spire$random$rng$MersenneTwister64$$LowerMask;
   private static final int spire$random$rng$MersenneTwister64$$N;
   private static final int spire$random$rng$MersenneTwister64$$M;
   private static final int spire$random$rng$MersenneTwister64$$N_M;
   private static final int spire$random$rng$MersenneTwister64$$N_1;
   private static final int spire$random$rng$MersenneTwister64$$M_N;
   private static final int spire$random$rng$MersenneTwister64$$M_1;
   private static final int spire$random$rng$MersenneTwister64$$BYTES;

   static {
      GeneratorCompanion.$init$(MODULE$);
      spire$random$rng$MersenneTwister64$$UpperMask = -2147483648L;
      spire$random$rng$MersenneTwister64$$LowerMask = 2147483647L;
      spire$random$rng$MersenneTwister64$$N = 312;
      spire$random$rng$MersenneTwister64$$M = 156;
      spire$random$rng$MersenneTwister64$$N_M = MODULE$.spire$random$rng$MersenneTwister64$$N() - MODULE$.spire$random$rng$MersenneTwister64$$M();
      spire$random$rng$MersenneTwister64$$N_1 = MODULE$.spire$random$rng$MersenneTwister64$$N() - 1;
      spire$random$rng$MersenneTwister64$$M_N = MODULE$.spire$random$rng$MersenneTwister64$$M() - MODULE$.spire$random$rng$MersenneTwister64$$N();
      spire$random$rng$MersenneTwister64$$M_1 = MODULE$.spire$random$rng$MersenneTwister64$$M() - 1;
      spire$random$rng$MersenneTwister64$$BYTES = MODULE$.spire$random$rng$MersenneTwister64$$N() * 8 + 4;
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

   public int $lessinit$greater$default$2() {
      return 313;
   }

   public long spire$random$rng$MersenneTwister64$$UpperMask() {
      return spire$random$rng$MersenneTwister64$$UpperMask;
   }

   public long spire$random$rng$MersenneTwister64$$LowerMask() {
      return spire$random$rng$MersenneTwister64$$LowerMask;
   }

   public int spire$random$rng$MersenneTwister64$$N() {
      return spire$random$rng$MersenneTwister64$$N;
   }

   public int spire$random$rng$MersenneTwister64$$M() {
      return spire$random$rng$MersenneTwister64$$M;
   }

   public int spire$random$rng$MersenneTwister64$$N_M() {
      return spire$random$rng$MersenneTwister64$$N_M;
   }

   public int spire$random$rng$MersenneTwister64$$N_1() {
      return spire$random$rng$MersenneTwister64$$N_1;
   }

   public int spire$random$rng$MersenneTwister64$$M_N() {
      return spire$random$rng$MersenneTwister64$$M_N;
   }

   public int spire$random$rng$MersenneTwister64$$M_1() {
      return spire$random$rng$MersenneTwister64$$M_1;
   }

   public int spire$random$rng$MersenneTwister64$$BYTES() {
      return spire$random$rng$MersenneTwister64$$BYTES;
   }

   public long spire$random$rng$MersenneTwister64$$mag01(final long x) {
      return (x & 1L) == 0L ? 0L : 815194369124884894L;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromLong(this.spire$random$rng$MersenneTwister64$$N(), Utils$.MODULE$.longFromTime(Utils$.MODULE$.longFromTime$default$1())), BoxesRunTime.boxToInteger(this.spire$random$rng$MersenneTwister64$$N() + 1));
   }

   public MersenneTwister64 fromSeed(final Tuple2 seed) {
      if (seed != null) {
         long[] mt = (long[])seed._1();
         int mti = seed._2$mcI$sp();
         .MODULE$.assert(mt.length == this.spire$random$rng$MersenneTwister64$$N());
         MersenneTwister64 var2 = new MersenneTwister64(mt, mti);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public MersenneTwister64 fromArray(final long[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$MersenneTwister64$$N(), arr), BoxesRunTime.boxToInteger(this.spire$random$rng$MersenneTwister64$$N() + 1)));
   }

   public MersenneTwister64 fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.longsFromBytes(bytes, bytes.length / 8));
   }

   public MersenneTwister64 fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromLong(this.spire$random$rng$MersenneTwister64$$N(), Utils$.MODULE$.longFromTime(time)), BoxesRunTime.boxToInteger(this.spire$random$rng$MersenneTwister64$$N() + 1)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private MersenneTwister64$() {
   }
}
