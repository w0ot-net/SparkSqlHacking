package org.apache.spark.util.random;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001da\u0001\u0002\b\u0010\u0001iA\u0001\"\r\u0001\u0003\u0002\u0003\u0006IA\r\u0005\tk\u0001\u0011\t\u0011)A\u0005m!)\u0011\b\u0001C\u0001u!)\u0011\b\u0001C\u0001}!9\u0001\t\u0001b\u0001\n\u0013\t\u0005B\u0002'\u0001A\u0003%!\tC\u0004N\u0001\t\u0007I\u0011\u0002(\t\rY\u0003\u0001\u0015!\u0003P\u0011\u00159\u0006\u0001\"\u0011Y\u0011!\t\u0007\u0001#b\u0001\n\u0013\u0011\u0007\"\u00024\u0001\t\u0003:\u0007\"\u00024\u0001\t\u0003Z\u0007\"\u0002>\u0001\t\u0003Z(A\u0004)pSN\u001cxN\\*b[BdWM\u001d\u0006\u0003!E\taA]1oI>l'B\u0001\n\u0014\u0003\u0011)H/\u001b7\u000b\u0005Q)\u0012!B:qCJ\\'B\u0001\f\u0018\u0003\u0019\t\u0007/Y2iK*\t\u0001$A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u001cQM\u0019\u0001\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0005s\u0017PU3g!\u0011\u0019CE\n\u0014\u000e\u0003=I!!J\b\u0003\u001bI\u000bg\u000eZ8n'\u0006l\u0007\u000f\\3s!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u0002!\u0019\u0001\u0016\u0003\u0003Q\u000b\"a\u000b\u0018\u0011\u0005ua\u0013BA\u0017\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!H\u0018\n\u0005Ar\"aA!os\u0006AaM]1di&|g\u000e\u0005\u0002\u001eg%\u0011AG\b\u0002\u0007\t>,(\r\\3\u00021U\u001cXmR1q'\u0006l\u0007\u000f\\5oO&3\u0007k\\:tS\ndW\r\u0005\u0002\u001eo%\u0011\u0001H\b\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q\u00191\bP\u001f\u0011\u0007\r\u0002a\u0005C\u00032\u0007\u0001\u0007!\u0007C\u00036\u0007\u0001\u0007a\u0007\u0006\u0002<\u007f!)\u0011\u0007\u0002a\u0001e\u0005\u0019!O\\4\u0016\u0003\t\u0003\"a\u0011&\u000e\u0003\u0011S!!\u0012$\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005\u001dC\u0015!B7bi\"\u001c$BA%\u0016\u0003\u001d\u0019w.\\7p]NL!a\u0013#\u0003'A{\u0017n]:p]\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u0002\tItw\rI\u0001\u0007e:<w)\u00199\u0016\u0003=\u0003\"\u0001\u0015+\u000e\u0003ES!A\u0005*\u000b\u0003M\u000bAA[1wC&\u0011Q+\u0015\u0002\u0007%\u0006tGm\\7\u0002\u000fItwmR1qA\u000591/\u001a;TK\u0016$GCA-]!\ti\",\u0003\u0002\\=\t!QK\\5u\u0011\u0015i\u0016\u00021\u0001_\u0003\u0011\u0019X-\u001a3\u0011\u0005uy\u0016B\u00011\u001f\u0005\u0011auN\\4\u0002-\u001d\f\u0007oU1na2Lgn\u001a*fa2\f7-Z7f]R,\u0012a\u0019\t\u0003G\u0011L!!Z\b\u0003-\u001d\u000b\u0007oU1na2Lgn\u001a*fa2\f7-Z7f]R\faa]1na2,G#\u00015\u0011\u0005uI\u0017B\u00016\u001f\u0005\rIe\u000e\u001e\u000b\u0003Yb\u00042!\\;'\u001d\tq7O\u0004\u0002pe6\t\u0001O\u0003\u0002r3\u00051AH]8pizJ\u0011aH\u0005\u0003iz\tq\u0001]1dW\u0006<W-\u0003\u0002wo\nA\u0011\n^3sCR|'O\u0003\u0002u=!)\u0011\u0010\u0004a\u0001Y\u0006)\u0011\u000e^3ng\u0006)1\r\\8oKR\t1\b\u000b\u0002\u0001{B\u0019a0a\u0001\u000e\u0003}T1!!\u0001\u0014\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u000by(\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class PoissonSampler implements RandomSampler {
   private GapSamplingReplacement gapSamplingReplacement;
   private final double fraction;
   private final boolean useGapSamplingIfPossible;
   private final PoissonDistribution rng;
   private final Random rngGap;
   private volatile boolean bitmap$0;

   private PoissonDistribution rng() {
      return this.rng;
   }

   private Random rngGap() {
      return this.rngGap;
   }

   public void setSeed(final long seed) {
      this.rng().reseedRandomGenerator(seed);
      this.rngGap().setSeed(seed);
   }

   private GapSamplingReplacement gapSamplingReplacement$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.gapSamplingReplacement = new GapSamplingReplacement(this.fraction, this.rngGap(), RandomSampler$.MODULE$.rngEpsilon());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.gapSamplingReplacement;
   }

   private GapSamplingReplacement gapSamplingReplacement() {
      return !this.bitmap$0 ? this.gapSamplingReplacement$lzycompute() : this.gapSamplingReplacement;
   }

   public int sample() {
      if (this.fraction <= (double)0.0F) {
         return 0;
      } else {
         return this.useGapSamplingIfPossible && this.fraction <= RandomSampler$.MODULE$.defaultMaxGapSamplingFraction() ? this.gapSamplingReplacement().sample() : this.rng().sample();
      }
   }

   public Iterator sample(final Iterator items) {
      if (this.fraction <= (double)0.0F) {
         return .MODULE$.Iterator().empty();
      } else {
         boolean useGapSampling = this.useGapSamplingIfPossible && this.fraction <= RandomSampler$.MODULE$.defaultMaxGapSamplingFraction();
         return items.flatMap((item) -> {
            int count = useGapSampling ? this.gapSamplingReplacement().sample() : this.rng().sample();
            return count == 0 ? .MODULE$.Iterator().empty() : .MODULE$.Iterator().fill(count, () -> item);
         });
      }
   }

   public PoissonSampler clone() {
      return new PoissonSampler(this.fraction, this.useGapSamplingIfPossible);
   }

   public PoissonSampler(final double fraction, final boolean useGapSamplingIfPossible) {
      this.fraction = fraction;
      this.useGapSamplingIfPossible = useGapSamplingIfPossible;
      RandomSampler.$init$(this);
      scala.Predef..MODULE$.require(fraction >= (double)0.0F - RandomSampler$.MODULE$.roundingEpsilon(), () -> "Sampling fraction (" + this.fraction + ") must be >= 0");
      this.rng = new PoissonDistribution(fraction > (double)0.0F ? fraction : (double)1.0F);
      this.rngGap = RandomSampler$.MODULE$.newDefaultRNG();
   }

   public PoissonSampler(final double fraction) {
      this(fraction, true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
