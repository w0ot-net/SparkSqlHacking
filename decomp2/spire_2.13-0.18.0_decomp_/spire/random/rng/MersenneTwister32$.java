package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class MersenneTwister32$ implements GeneratorCompanion {
   public static final MersenneTwister32$ MODULE$ = new MersenneTwister32$();
   private static final int spire$random$rng$MersenneTwister32$$UpperMask;
   private static final int spire$random$rng$MersenneTwister32$$LowerMask;
   private static final int spire$random$rng$MersenneTwister32$$N;
   private static final int spire$random$rng$MersenneTwister32$$M;
   private static final int spire$random$rng$MersenneTwister32$$N_M;
   private static final int spire$random$rng$MersenneTwister32$$N_1;
   private static final int spire$random$rng$MersenneTwister32$$M_N;
   private static final int spire$random$rng$MersenneTwister32$$M_1;
   private static final int spire$random$rng$MersenneTwister32$$BYTES;

   static {
      GeneratorCompanion.$init$(MODULE$);
      spire$random$rng$MersenneTwister32$$UpperMask = Integer.MIN_VALUE;
      spire$random$rng$MersenneTwister32$$LowerMask = Integer.MAX_VALUE;
      spire$random$rng$MersenneTwister32$$N = 624;
      spire$random$rng$MersenneTwister32$$M = 397;
      spire$random$rng$MersenneTwister32$$N_M = MODULE$.spire$random$rng$MersenneTwister32$$N() - MODULE$.spire$random$rng$MersenneTwister32$$M();
      spire$random$rng$MersenneTwister32$$N_1 = MODULE$.spire$random$rng$MersenneTwister32$$N() - 1;
      spire$random$rng$MersenneTwister32$$M_N = MODULE$.spire$random$rng$MersenneTwister32$$M() - MODULE$.spire$random$rng$MersenneTwister32$$N();
      spire$random$rng$MersenneTwister32$$M_1 = MODULE$.spire$random$rng$MersenneTwister32$$M() - 1;
      spire$random$rng$MersenneTwister32$$BYTES = MODULE$.spire$random$rng$MersenneTwister32$$N() * 4 + 4;
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
      return 625;
   }

   public int spire$random$rng$MersenneTwister32$$UpperMask() {
      return spire$random$rng$MersenneTwister32$$UpperMask;
   }

   public int spire$random$rng$MersenneTwister32$$LowerMask() {
      return spire$random$rng$MersenneTwister32$$LowerMask;
   }

   public int spire$random$rng$MersenneTwister32$$N() {
      return spire$random$rng$MersenneTwister32$$N;
   }

   public int spire$random$rng$MersenneTwister32$$M() {
      return spire$random$rng$MersenneTwister32$$M;
   }

   public int spire$random$rng$MersenneTwister32$$N_M() {
      return spire$random$rng$MersenneTwister32$$N_M;
   }

   public int spire$random$rng$MersenneTwister32$$N_1() {
      return spire$random$rng$MersenneTwister32$$N_1;
   }

   public int spire$random$rng$MersenneTwister32$$M_N() {
      return spire$random$rng$MersenneTwister32$$M_N;
   }

   public int spire$random$rng$MersenneTwister32$$M_1() {
      return spire$random$rng$MersenneTwister32$$M_1;
   }

   public int spire$random$rng$MersenneTwister32$$BYTES() {
      return spire$random$rng$MersenneTwister32$$BYTES;
   }

   public int spire$random$rng$MersenneTwister32$$mag01(final int x) {
      return (x & 1) == 0 ? 0 : -1727483681;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$MersenneTwister32$$N(), Utils$.MODULE$.intFromTime(Utils$.MODULE$.intFromTime$default$1())), BoxesRunTime.boxToInteger(this.spire$random$rng$MersenneTwister32$$N() + 1));
   }

   public MersenneTwister32 fromSeed(final Tuple2 seed) {
      if (seed != null) {
         int[] mt = (int[])seed._1();
         int mti = seed._2$mcI$sp();
         .MODULE$.assert(mt.length == this.spire$random$rng$MersenneTwister32$$N());
         MersenneTwister32 var2 = new MersenneTwister32(mt, mti);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public MersenneTwister32 fromArray(final int[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$MersenneTwister32$$N(), arr), BoxesRunTime.boxToInteger(this.spire$random$rng$MersenneTwister32$$N() + 1)));
   }

   public MersenneTwister32 fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.intsFromBytes(bytes, bytes.length / 4));
   }

   public MersenneTwister32 fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$MersenneTwister32$$N(), Utils$.MODULE$.intFromTime(time)), BoxesRunTime.boxToInteger(this.spire$random$rng$MersenneTwister32$$N() + 1)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private MersenneTwister32$() {
   }
}
