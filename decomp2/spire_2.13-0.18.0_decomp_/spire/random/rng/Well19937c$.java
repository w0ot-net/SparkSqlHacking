package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class Well19937c$ implements GeneratorCompanion {
   public static final Well19937c$ MODULE$ = new Well19937c$();
   private static final int spire$random$rng$Well19937c$$UpperMask;
   private static final int spire$random$rng$Well19937c$$LowerMask;
   private static final int spire$random$rng$Well19937c$$TemperB;
   private static final int spire$random$rng$Well19937c$$TemperC;
   private static final int K;
   private static final int spire$random$rng$Well19937c$$R;
   private static final int spire$random$rng$Well19937c$$BYTES;

   static {
      GeneratorCompanion.$init$(MODULE$);
      spire$random$rng$Well19937c$$UpperMask = Integer.MAX_VALUE;
      spire$random$rng$Well19937c$$LowerMask = Integer.MIN_VALUE;
      spire$random$rng$Well19937c$$TemperB = -462547200;
      spire$random$rng$Well19937c$$TemperC = -1685684224;
      K = 19937;
      spire$random$rng$Well19937c$$R = (MODULE$.K() + 31) / 32;
      spire$random$rng$Well19937c$$BYTES = MODULE$.spire$random$rng$Well19937c$$R() * 4 + 4;
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

   public int spire$random$rng$Well19937c$$UpperMask() {
      return spire$random$rng$Well19937c$$UpperMask;
   }

   public int spire$random$rng$Well19937c$$LowerMask() {
      return spire$random$rng$Well19937c$$LowerMask;
   }

   public int spire$random$rng$Well19937c$$TemperB() {
      return spire$random$rng$Well19937c$$TemperB;
   }

   public int spire$random$rng$Well19937c$$TemperC() {
      return spire$random$rng$Well19937c$$TemperC;
   }

   private final int K() {
      return K;
   }

   public final int spire$random$rng$Well19937c$$R() {
      return spire$random$rng$Well19937c$$R;
   }

   public final int spire$random$rng$Well19937c$$BYTES() {
      return spire$random$rng$Well19937c$$BYTES;
   }

   public final int spire$random$rng$Well19937c$$mat0pos(final int t, final int v) {
      return v ^ v >>> t;
   }

   public final int spire$random$rng$Well19937c$$mat0neg(final int t, final int v) {
      return v ^ v << -t;
   }

   public final int spire$random$rng$Well19937c$$mat1(final int v) {
      return v;
   }

   public final int spire$random$rng$Well19937c$$mat3pos(final int t, final int v) {
      return v >>> t;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well19937c$$R(), Utils$.MODULE$.intFromTime(Utils$.MODULE$.intFromTime$default$1())), BoxesRunTime.boxToInteger(0));
   }

   public Well19937c fromSeed(final Tuple2 seed) {
      if (seed != null) {
         int[] state = (int[])seed._1();
         int stateIndex = seed._2$mcI$sp();
         .MODULE$.assert(state.length == this.spire$random$rng$Well19937c$$R());
         Well19937c var2 = new Well19937c(state, stateIndex);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public Well19937c fromArray(final int[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$Well19937c$$R(), arr), BoxesRunTime.boxToInteger(0)));
   }

   public Well19937c fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.intsFromBytes(bytes, bytes.length / 4));
   }

   public Well19937c fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well19937c$$R(), Utils$.MODULE$.intFromTime(time)), BoxesRunTime.boxToInteger(0)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private Well19937c$() {
   }
}
