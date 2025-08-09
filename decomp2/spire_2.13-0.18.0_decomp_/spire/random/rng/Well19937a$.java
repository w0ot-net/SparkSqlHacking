package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class Well19937a$ implements GeneratorCompanion {
   public static final Well19937a$ MODULE$ = new Well19937a$();
   private static final int spire$random$rng$Well19937a$$UpperMask;
   private static final int spire$random$rng$Well19937a$$LowerMask;
   private static final int K;
   private static final int spire$random$rng$Well19937a$$R;
   private static final int spire$random$rng$Well19937a$$BYTES;

   static {
      GeneratorCompanion.$init$(MODULE$);
      spire$random$rng$Well19937a$$UpperMask = Integer.MAX_VALUE;
      spire$random$rng$Well19937a$$LowerMask = Integer.MIN_VALUE;
      K = 19937;
      spire$random$rng$Well19937a$$R = (MODULE$.K() + 31) / 32;
      spire$random$rng$Well19937a$$BYTES = MODULE$.spire$random$rng$Well19937a$$R() * 4 + 4;
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

   public int spire$random$rng$Well19937a$$UpperMask() {
      return spire$random$rng$Well19937a$$UpperMask;
   }

   public int spire$random$rng$Well19937a$$LowerMask() {
      return spire$random$rng$Well19937a$$LowerMask;
   }

   private final int K() {
      return K;
   }

   public final int spire$random$rng$Well19937a$$R() {
      return spire$random$rng$Well19937a$$R;
   }

   public final int spire$random$rng$Well19937a$$BYTES() {
      return spire$random$rng$Well19937a$$BYTES;
   }

   public final int spire$random$rng$Well19937a$$mat0pos(final int t, final int v) {
      return v ^ v >>> t;
   }

   public final int spire$random$rng$Well19937a$$mat0neg(final int t, final int v) {
      return v ^ v << -t;
   }

   public final int spire$random$rng$Well19937a$$mat1(final int v) {
      return v;
   }

   public final int spire$random$rng$Well19937a$$mat3pos(final int t, final int v) {
      return v >>> t;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well19937a$$R(), Utils$.MODULE$.intFromTime(Utils$.MODULE$.intFromTime$default$1())), BoxesRunTime.boxToInteger(0));
   }

   public Well19937a fromSeed(final Tuple2 seed) {
      if (seed != null) {
         int[] state = (int[])seed._1();
         int stateIndex = seed._2$mcI$sp();
         .MODULE$.assert(state.length == this.spire$random$rng$Well19937a$$R());
         Well19937a var2 = new Well19937a(state, stateIndex);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public Well19937a fromArray(final int[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$Well19937a$$R(), arr), BoxesRunTime.boxToInteger(0)));
   }

   public Well19937a fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.intsFromBytes(bytes, bytes.length / 4));
   }

   public Well19937a fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well19937a$$R(), Utils$.MODULE$.intFromTime(time)), BoxesRunTime.boxToInteger(0)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private Well19937a$() {
   }
}
