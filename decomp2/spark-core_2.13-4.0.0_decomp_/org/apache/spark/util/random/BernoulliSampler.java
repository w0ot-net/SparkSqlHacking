package org.apache.spark.util.random;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.annotation.DeveloperApi;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00154AAC\u0006\u0001-!AQ\u0006\u0001B\u0001B\u0003%a\u0006\u0003\u00052\u0001\t\r\t\u0015a\u00033\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u001dq\u0004A1A\u0005\n}Baa\u0012\u0001!\u0002\u0013\u0001\u0005\"\u0002%\u0001\t\u0003J\u0005\u0002\u0003*\u0001\u0011\u000b\u0007I\u0011B*\t\u000b]\u0003A\u0011\t-\t\u000bq\u0003A\u0011I/\u0003!\t+'O\\8vY2L7+Y7qY\u0016\u0014(B\u0001\u0007\u000e\u0003\u0019\u0011\u0018M\u001c3p[*\u0011abD\u0001\u0005kRLGN\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h\u0007\u0001)\"a\u0006\u0013\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0005?\u0001\u0012#%D\u0001\f\u0013\t\t3BA\u0007SC:$w.\\*b[BdWM\u001d\t\u0003G\u0011b\u0001\u0001B\u0003&\u0001\t\u0007aEA\u0001U#\t9#\u0006\u0005\u0002\u001aQ%\u0011\u0011F\u0007\u0002\b\u001d>$\b.\u001b8h!\tI2&\u0003\u0002-5\t\u0019\u0011I\\=\u0002\u0011\u0019\u0014\u0018m\u0019;j_:\u0004\"!G\u0018\n\u0005AR\"A\u0002#pk\ndW-\u0001\u0006fm&$WM\\2fIE\u00022a\r\u001c#\u001b\u0005!$BA\u001b\u001b\u0003\u001d\u0011XM\u001a7fGRL!a\u000e\u001b\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDC\u0001\u001e>)\tYD\bE\u0002 \u0001\tBQ!M\u0002A\u0004IBQ!L\u0002A\u00029\n1A\u001d8h+\u0005\u0001\u0005CA!F\u001b\u0005\u0011%B\u0001\bD\u0015\u0005!\u0015\u0001\u00026bm\u0006L!A\u0012\"\u0003\rI\u000bg\u000eZ8n\u0003\u0011\u0011hn\u001a\u0011\u0002\u000fM,GoU3fIR\u0011!*\u0014\t\u00033-K!\u0001\u0014\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u001d\u001a\u0001\raT\u0001\u0005g\u0016,G\r\u0005\u0002\u001a!&\u0011\u0011K\u0007\u0002\u0005\u0019>tw-A\u0006hCB\u001c\u0016-\u001c9mS:<W#\u0001+\u0011\u0005})\u0016B\u0001,\f\u0005-9\u0015\r]*b[Bd\u0017N\\4\u0002\rM\fW\u000e\u001d7f)\u0005I\u0006CA\r[\u0013\tY&DA\u0002J]R\fQa\u00197p]\u0016$\u0012a\u000f\u0015\u0003\u0001}\u0003\"\u0001Y2\u000e\u0003\u0005T!AY\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002eC\naA)\u001a<fY>\u0004XM]!qS\u0002"
)
public class BernoulliSampler implements RandomSampler {
   private GapSampling gapSampling;
   private final double fraction;
   private final ClassTag evidence$1;
   private final Random rng;
   private volatile boolean bitmap$0;

   public Iterator sample(final Iterator items) {
      return RandomSampler.sample$(this, items);
   }

   private Random rng() {
      return this.rng;
   }

   public void setSeed(final long seed) {
      this.rng().setSeed(seed);
   }

   private GapSampling gapSampling$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.gapSampling = new GapSampling(this.fraction, this.rng(), RandomSampler$.MODULE$.rngEpsilon());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.gapSampling;
   }

   private GapSampling gapSampling() {
      return !this.bitmap$0 ? this.gapSampling$lzycompute() : this.gapSampling;
   }

   public int sample() {
      if (this.fraction <= (double)0.0F) {
         return 0;
      } else if (this.fraction >= (double)1.0F) {
         return 1;
      } else if (this.fraction <= RandomSampler$.MODULE$.defaultMaxGapSamplingFraction()) {
         return this.gapSampling().sample();
      } else {
         return this.rng().nextDouble() <= this.fraction ? 1 : 0;
      }
   }

   public BernoulliSampler clone() {
      return new BernoulliSampler(this.fraction, this.evidence$1);
   }

   public BernoulliSampler(final double fraction, final ClassTag evidence$1) {
      this.fraction = fraction;
      this.evidence$1 = evidence$1;
      RandomSampler.$init$(this);
      .MODULE$.require(fraction >= (double)0.0F - RandomSampler$.MODULE$.roundingEpsilon() && fraction <= (double)1.0F + RandomSampler$.MODULE$.roundingEpsilon(), () -> "Sampling fraction (" + this.fraction + ") must be on interval [0, 1]");
      this.rng = RandomSampler$.MODULE$.newDefaultRNG();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
