package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class Well44497b$ implements GeneratorCompanion {
   public static final Well44497b$ MODULE$ = new Well44497b$();
   private static final int spire$random$rng$Well44497b$$UpperMask;
   private static final int spire$random$rng$Well44497b$$LowerMask;
   private static final int spire$random$rng$Well44497b$$TemperB;
   private static final int spire$random$rng$Well44497b$$TemperC;
   private static final int K;
   private static final int spire$random$rng$Well44497b$$R;
   private static final int spire$random$rng$Well44497b$$BYTES;

   static {
      GeneratorCompanion.$init$(MODULE$);
      spire$random$rng$Well44497b$$UpperMask = 32767;
      spire$random$rng$Well44497b$$LowerMask = ~MODULE$.spire$random$rng$Well44497b$$UpperMask();
      spire$random$rng$Well44497b$$TemperB = -1814227968;
      spire$random$rng$Well44497b$$TemperC = -99516416;
      K = 44497;
      spire$random$rng$Well44497b$$R = (MODULE$.K() + 31) / 32;
      spire$random$rng$Well44497b$$BYTES = MODULE$.spire$random$rng$Well44497b$$R() * 4 + 4;
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

   public int spire$random$rng$Well44497b$$UpperMask() {
      return spire$random$rng$Well44497b$$UpperMask;
   }

   public int spire$random$rng$Well44497b$$LowerMask() {
      return spire$random$rng$Well44497b$$LowerMask;
   }

   public int spire$random$rng$Well44497b$$TemperB() {
      return spire$random$rng$Well44497b$$TemperB;
   }

   public int spire$random$rng$Well44497b$$TemperC() {
      return spire$random$rng$Well44497b$$TemperC;
   }

   private final int K() {
      return K;
   }

   public final int spire$random$rng$Well44497b$$R() {
      return spire$random$rng$Well44497b$$R;
   }

   public final int spire$random$rng$Well44497b$$BYTES() {
      return spire$random$rng$Well44497b$$BYTES;
   }

   public final int spire$random$rng$Well44497b$$mat0pos(final int t, final int v) {
      return v ^ v >>> t;
   }

   public final int spire$random$rng$Well44497b$$mat0neg(final int t, final int v) {
      return v ^ v << -t;
   }

   public final int spire$random$rng$Well44497b$$mat1(final int v) {
      return v;
   }

   public final int spire$random$rng$Well44497b$$mat3neg(final int t, final int v) {
      return v << -t;
   }

   public final int spire$random$rng$Well44497b$$mat5(final int r, final int a, final int ds, final int dt, final int v) {
      return (v & dt) != 0 ? (v << r ^ v >>> 32 - r) & ds ^ a : (v << r ^ v >>> 32 - r) & ds;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well44497b$$R(), Utils$.MODULE$.intFromTime(Utils$.MODULE$.intFromTime$default$1())), BoxesRunTime.boxToInteger(0));
   }

   public Well44497b fromSeed(final Tuple2 seed) {
      if (seed != null) {
         int[] state = (int[])seed._1();
         int stateIndex = seed._2$mcI$sp();
         .MODULE$.assert(state.length == this.spire$random$rng$Well44497b$$R());
         Well44497b var2 = new Well44497b(state, stateIndex);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public Well44497b fromArray(final int[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$Well44497b$$R(), arr), BoxesRunTime.boxToInteger(0)));
   }

   public Well44497b fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.intsFromBytes(bytes, bytes.length / 4));
   }

   public Well44497b fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well44497b$$R(), Utils$.MODULE$.intFromTime(time)), BoxesRunTime.boxToInteger(0)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private Well44497b$() {
   }
}
