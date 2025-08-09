package spire.random.rng;

import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.runtime.BoxesRunTime;
import spire.random.GeneratorCompanion;

public final class Well1024a$ implements GeneratorCompanion {
   public static final Well1024a$ MODULE$ = new Well1024a$();
   private static final int K;
   private static final int spire$random$rng$Well1024a$$R;
   private static final int spire$random$rng$Well1024a$$R_1;
   private static final int spire$random$rng$Well1024a$$BYTES;
   private static final int spire$random$rng$Well1024a$$M1;
   private static final int spire$random$rng$Well1024a$$M2;
   private static final int spire$random$rng$Well1024a$$M3;

   static {
      GeneratorCompanion.$init$(MODULE$);
      K = 1024;
      spire$random$rng$Well1024a$$R = MODULE$.K() / 32;
      spire$random$rng$Well1024a$$R_1 = MODULE$.spire$random$rng$Well1024a$$R() - 1;
      spire$random$rng$Well1024a$$BYTES = MODULE$.spire$random$rng$Well1024a$$R() * 4 + 4;
      spire$random$rng$Well1024a$$M1 = 3;
      spire$random$rng$Well1024a$$M2 = 24;
      spire$random$rng$Well1024a$$M3 = 10;
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

   public final int spire$random$rng$Well1024a$$R() {
      return spire$random$rng$Well1024a$$R;
   }

   public final int spire$random$rng$Well1024a$$R_1() {
      return spire$random$rng$Well1024a$$R_1;
   }

   public final int spire$random$rng$Well1024a$$BYTES() {
      return spire$random$rng$Well1024a$$BYTES;
   }

   public final int spire$random$rng$Well1024a$$M1() {
      return spire$random$rng$Well1024a$$M1;
   }

   public final int spire$random$rng$Well1024a$$M2() {
      return spire$random$rng$Well1024a$$M2;
   }

   public final int spire$random$rng$Well1024a$$M3() {
      return spire$random$rng$Well1024a$$M3;
   }

   public final int spire$random$rng$Well1024a$$mat0pos(final int t, final int v) {
      return v ^ v >>> t;
   }

   public final int spire$random$rng$Well1024a$$mat0neg(final int t, final int v) {
      return v ^ v << -t;
   }

   public Tuple2 randomSeed() {
      return new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well1024a$$R(), Utils$.MODULE$.intFromTime(Utils$.MODULE$.intFromTime$default$1())), BoxesRunTime.boxToInteger(0));
   }

   public Well1024a fromSeed(final Tuple2 seed) {
      if (seed != null) {
         int[] state = (int[])seed._1();
         int stateIndex = seed._2$mcI$sp();
         .MODULE$.assert(state.length == this.spire$random$rng$Well1024a$$R());
         Well1024a var2 = new Well1024a(state, stateIndex);
         return var2;
      } else {
         throw new MatchError(seed);
      }
   }

   public Well1024a fromArray(final int[] arr) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromArray(this.spire$random$rng$Well1024a$$R(), arr), BoxesRunTime.boxToInteger(0)));
   }

   public Well1024a fromBytes(final byte[] bytes) {
      return this.fromArray(spire.util.Pack..MODULE$.intsFromBytes(bytes, bytes.length / 4));
   }

   public Well1024a fromTime(final long time) {
      return this.fromSeed(new Tuple2(Utils$.MODULE$.seedFromInt(this.spire$random$rng$Well1024a$$R(), Utils$.MODULE$.intFromTime(time)), BoxesRunTime.boxToInteger(0)));
   }

   public long fromTime$default$1() {
      return System.nanoTime();
   }

   private Well1024a$() {
   }
}
