package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class Well512a$ implements GeneratorCompanion {
   public static final Well512a$ MODULE$ = new Well512a$();
   private static final int K;
   private static final int spire$random$rng$Well512a$$R;
   private static final int spire$random$rng$Well512a$$R_1;
   private static final int spire$random$rng$Well512a$$BYTES;
   private static final int spire$random$rng$Well512a$$M1;
   private static final int spire$random$rng$Well512a$$M2;

   static {
      GeneratorCompanion.$init$(MODULE$);
      K = 512;
      spire$random$rng$Well512a$$R = MODULE$.K() / 32;
      spire$random$rng$Well512a$$R_1 = MODULE$.spire$random$rng$Well512a$$R() - 1;
      spire$random$rng$Well512a$$BYTES = MODULE$.spire$random$rng$Well512a$$R() * 4 + 4;
      spire$random$rng$Well512a$$M1 = 13;
      spire$random$rng$Well512a$$M2 = 9;
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

   private final int K() {
      return K;
   }

   public final int spire$random$rng$Well512a$$R() {
      return spire$random$rng$Well512a$$R;
   }

   public final int spire$random$rng$Well512a$$R_1() {
      return spire$random$rng$Well512a$$R_1;
   }

   public final int spire$random$rng$Well512a$$BYTES() {
      return spire$random$rng$Well512a$$BYTES;
   }

   public final int spire$random$rng$Well512a$$M1() {
      return spire$random$rng$Well512a$$M1;
   }

   public final int spire$random$rng$Well512a$$M2() {
      return spire$random$rng$Well512a$$M2;
   }

   public final int spire$random$rng$Well512a$$mat0pos(final int t, final int v) {
      return v ^ v >>> t;
   }

   public final int spire$random$rng$Well512a$$mat0neg(final int t, final int v) {
      return v ^ v << -t;
   }

   public final int spire$random$rng$Well512a$$mat3neg(final int t, final int v) {
      return v << -t;
   }

   public final int spire$random$rng$Well512a$$mat4neg(final int t, final int b, final int v) {
      return v ^ v << -t & b;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well512a$$R(), Utils$.MODULE$.intFromTime(Utils$.MODULE$.intFromTime$default$1())), BoxesRunTime.boxToInteger(0));
   }

   public Well512a fromSeed(final Tuple2 seed) {
      if (seed != null) {
         int[] state = (int[])seed._1();
         int stateIndex = seed._2$mcI$sp();
         .MODULE$.assert(state.length == this.spire$random$rng$Well512a$$R());
         Well512a var2 = new Well512a(state, stateIndex);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public Well512a fromArray(final int[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$Well512a$$R(), arr), BoxesRunTime.boxToInteger(0)));
   }

   public Well512a fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.intsFromBytes(bytes, bytes.length / 4));
   }

   public Well512a fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well512a$$R(), Utils$.MODULE$.intFromTime(time)), BoxesRunTime.boxToInteger(0)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private Well512a$() {
   }
}
