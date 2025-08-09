package org.apache.spark.util.random;

import java.lang.invoke.SerializedLambda;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import scala.Function2;
import scala.Option;
import scala.StringContext;
import scala.collection.Map;
import scala.collection.mutable.Map.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mxAB\u000b\u0017\u0011\u0003Q\u0002E\u0002\u0004#-!\u0005!d\t\u0005\u0006a\u0005!\tA\r\u0005\u0006g\u0005!\t\u0001\u000e\u0005\u0006c\u0006!\tA\u001d\u0005\b\u0003S\nA\u0011AA6\u0011\u001d\t9(\u0001C\u0001\u0003sBq!a#\u0002\t\u0003\ti\tC\u0004\u0002D\u0006!\t!!2\u0007\r\u0005%\u0011\u0001BA\u0006\u0011\u0019\u0001\u0014\u0002\"\u0001\u0002\u000e!I\u0011qB\u0005C\u0002\u0013\u0005\u0011\u0011\u0003\u0005\t\u00033I\u0001\u0015!\u0003\u0002\u0014!I\u00111D\u0005C\u0002\u0013\u0005\u0011Q\u0004\u0005\t\u0003kI\u0001\u0015!\u0003\u0002 !I\u0011qG\u0005A\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003wI\u0001\u0019!C\u0001\u0003{Aq!!\u0013\nA\u0003&A\u000eC\u0004\u0002L%!\t!!\u0014\t\u000f\u0005E\u0013\u0002\"\u0001\u0002T!9\u0011qL\u0005\u0005\u0002\u0005\u0005\u0014aF*ue\u0006$\u0018NZ5fIN\u000bW\u000e\u001d7j]\u001e,F/\u001b7t\u0015\t9\u0002$\u0001\u0004sC:$w.\u001c\u0006\u00033i\tA!\u001e;jY*\u00111\u0004H\u0001\u0006gB\f'o\u001b\u0006\u0003;y\ta!\u00199bG\",'\"A\u0010\u0002\u0007=\u0014x\r\u0005\u0002\"\u00035\taCA\fTiJ\fG/\u001b4jK\u0012\u001c\u0016-\u001c9mS:<W\u000b^5mgN\u0019\u0011\u0001\n\u0016\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g!\tYc&D\u0001-\u0015\ti#$\u0001\u0005j]R,'O\\1m\u0013\tyCFA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001I\u0001\u0015O\u0016$\u0018iY2faR\fgnY3SKN,H\u000e^:\u0016\u0007U\u0002u\u000b\u0006\u00047\u0019fsfm\u001c\t\u0005oqr\u0014*D\u00019\u0015\tI$(A\u0004nkR\f'\r\\3\u000b\u0005m2\u0013AC2pY2,7\r^5p]&\u0011Q\b\u000f\u0002\u0004\u001b\u0006\u0004\bCA A\u0019\u0001!Q!Q\u0002C\u0002\t\u0013\u0011aS\t\u0003\u0007\u001a\u0003\"!\n#\n\u0005\u00153#a\u0002(pi\"Lgn\u001a\t\u0003K\u001dK!\u0001\u0013\u0014\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\"\u0015&\u00111J\u0006\u0002\u0011\u0003\u000e\u001cW\r\u001d;b]\u000e,'+Z:vYRDQ!T\u0002A\u00029\u000b1A\u001d3e!\ry\u0015kU\u0007\u0002!*\u0011QJG\u0005\u0003%B\u00131A\u0015#E!\u0011)CK\u0010,\n\u0005U3#A\u0002+va2,'\u0007\u0005\u0002@/\u0012)\u0001l\u0001b\u0001\u0005\n\ta\u000bC\u0003[\u0007\u0001\u00071,A\bxSRD'+\u001a9mC\u000e,W.\u001a8u!\t)C,\u0003\u0002^M\t9!i\\8mK\u0006t\u0007\"B0\u0004\u0001\u0004\u0001\u0017!\u00034sC\u000e$\u0018n\u001c8t!\u0011\t'MP2\u000e\u0003iJ!!\u0010\u001e\u0011\u0005\u0015\"\u0017BA3'\u0005\u0019!u.\u001e2mK\")qm\u0001a\u0001Q\u000611m\\;oiN\u00042!J5l\u0013\tQgE\u0001\u0004PaRLwN\u001c\t\u0005C\ntD\u000e\u0005\u0002&[&\u0011aN\n\u0002\u0005\u0019>tw\rC\u0003q\u0007\u0001\u0007A.\u0001\u0003tK\u0016$\u0017\u0001C4fiN+\u0017o\u00149\u0016\u0007MLH\u0010F\u0004u{z\f\t!a\u0019\u0011\u000b\u0015*xO_<\n\u0005Y4#!\u0003$v]\u000e$\u0018n\u001c83!\u00119D\b_%\u0011\u0005}JH!B!\u0005\u0005\u0004\u0011\u0005\u0003B\u0013Uqn\u0004\"a\u0010?\u0005\u000ba#!\u0019\u0001\"\t\u000bi#\u0001\u0019A.\t\u000b}#\u0001\u0019A@\u0011\t\u0005\u0014\u0007p\u0019\u0005\b\u0003\u0007!\u0001\u0019AA\u0003\u0003\r\u0011hn\u001a\t\u0004\u0003\u000fIQ\"A\u0001\u0003'I\u000bg\u000eZ8n\t\u0006$\u0018mR3oKJ\fGo\u001c:\u0014\u0005%!CCAA\u0003\u0003\u001d)h.\u001b4pe6,\"!a\u0005\u0011\u0007\u0005\n)\"C\u0002\u0002\u0018Y\u0011a\u0002W(S'\"Lg\r\u001e*b]\u0012|W.\u0001\u0005v]&4wN]7!\u00031\u0001x.[:t_:\u001c\u0015m\u00195f+\t\ty\u0002E\u00038y\r\f\t\u0003\u0005\u0003\u0002$\u0005ERBAA\u0013\u0015\u0011\t9#!\u000b\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\t\u0005-\u0012QF\u0001\u0006[\u0006$\bn\r\u0006\u0004\u0003_a\u0012aB2p[6|gn]\u0005\u0005\u0003g\t)CA\nQ_&\u001c8o\u001c8ESN$(/\u001b2vi&|g.A\u0007q_&\u001c8o\u001c8DC\u000eDW\rI\u0001\fa>L7o]8o'\u0016,G-F\u0001m\u0003=\u0001x.[:t_:\u001cV-\u001a3`I\u0015\fH\u0003BA \u0003\u000b\u00022!JA!\u0013\r\t\u0019E\n\u0002\u0005+:LG\u000f\u0003\u0005\u0002HA\t\t\u00111\u0001m\u0003\rAH%M\u0001\ra>L7o]8o'\u0016,G\rI\u0001\u0007e\u0016\u001cV-\u001a3\u0015\t\u0005}\u0012q\n\u0005\u0006aJ\u0001\r\u0001\\\u0001\f]\u0016DH\u000fU8jgN|g\u000e\u0006\u0003\u0002V\u0005m\u0003cA\u0013\u0002X%\u0019\u0011\u0011\f\u0014\u0003\u0007%sG\u000f\u0003\u0004\u0002^M\u0001\raY\u0001\u0005[\u0016\fg.A\u0006oKb$XK\\5g_JlG#A2\t\r\u001d$\u0001\u0019AA3!\u0011)\u0013.a\u001a\u0011\t\u0005\u0014\u0007\u0010\\\u0001\nO\u0016$8i\\7c\u001fB,B!!\u001c\u0002vU\u0011\u0011q\u000e\t\tKU\f\t(!\u001d\u0002rA)q\u0007PA:\u0013B\u0019q(!\u001e\u0005\u000b\u0005+!\u0019\u0001\"\u0002+\r|W\u000e];uKRC'/Z:i_2$')_&fsV!\u00111PAA)\u0019\ti(a!\u0002\nB)\u0011MYA@GB\u0019q(!!\u0005\u000b\u00053!\u0019\u0001\"\t\u000f\u0005\u0015e\u00011\u0001\u0002\b\u0006Ya-\u001b8bYJ+7/\u001e7u!\u0015\t'-a J\u0011\u0019yf\u00011\u0001\u0002~\u0005ar-\u001a;CKJtw.\u001e7mSN\u000bW\u000e\u001d7j]\u001e4UO\\2uS>tWCBAH\u0003_\u000b\u0019\f\u0006\u0006\u0002\u0012\u0006U\u0016\u0011XA_\u0003\u0003\u0004\u0002\"J;\u0002V\u0005M\u00151\u0013\t\u0007\u0003+\u000b)+a+\u000f\t\u0005]\u0015\u0011\u0015\b\u0005\u00033\u000by*\u0004\u0002\u0002\u001c*\u0019\u0011QT\u0019\u0002\rq\u0012xn\u001c;?\u0013\u00059\u0013bAARM\u00059\u0001/Y2lC\u001e,\u0017\u0002BAT\u0003S\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003G3\u0003CB\u0013U\u0003[\u000b\t\fE\u0002@\u0003_#Q!Q\u0004C\u0002\t\u00032aPAZ\t\u0015AvA1\u0001C\u0011\u0019iu\u00011\u0001\u00028B!q*UAV\u0011\u0019yv\u00011\u0001\u0002<B)\u0011MYAWG\"1\u0011qX\u0004A\u0002m\u000bQ!\u001a=bGRDQ\u0001]\u0004A\u00021\f!dZ3u!>L7o]8o'\u0006l\u0007\u000f\\5oO\u001a+hn\u0019;j_:,b!a2\u0002T\u0006]GCCAe\u0003_\f\u00190a>\u0002zR1\u00111ZAm\u0003S\u0004\u0002\"J;\u0002V\u00055\u0017Q\u001a\t\u0007\u0003+\u000b)+a4\u0011\r\u0015\"\u0016\u0011[Ak!\ry\u00141\u001b\u0003\u0006\u0003\"\u0011\rA\u0011\t\u0004\u007f\u0005]G!\u0002-\t\u0005\u0004\u0011\u0005\"CAn\u0011\u0005\u0005\t9AAo\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003?\f)/!5\u000e\u0005\u0005\u0005(bAArM\u00059!/\u001a4mK\u000e$\u0018\u0002BAt\u0003C\u0014\u0001b\u00117bgN$\u0016m\u001a\u0005\n\u0003WD\u0011\u0011!a\u0002\u0003[\f!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\ty.!:\u0002V\"1Q\n\u0003a\u0001\u0003c\u0004BaT)\u0002P\"1q\f\u0003a\u0001\u0003k\u0004R!\u00192\u0002R\u000eDa!a0\t\u0001\u0004Y\u0006\"\u00029\t\u0001\u0004a\u0007"
)
public final class StratifiedSamplingUtils {
   public static Function2 getPoissonSamplingFunction(final RDD rdd, final Map fractions, final boolean exact, final long seed, final ClassTag evidence$1, final ClassTag evidence$2) {
      return StratifiedSamplingUtils$.MODULE$.getPoissonSamplingFunction(rdd, fractions, exact, seed, evidence$1, evidence$2);
   }

   public static Function2 getBernoulliSamplingFunction(final RDD rdd, final Map fractions, final boolean exact, final long seed) {
      return StratifiedSamplingUtils$.MODULE$.getBernoulliSamplingFunction(rdd, fractions, exact, seed);
   }

   public static Map computeThresholdByKey(final Map finalResult, final Map fractions) {
      return StratifiedSamplingUtils$.MODULE$.computeThresholdByKey(finalResult, fractions);
   }

   public static Function2 getCombOp() {
      return StratifiedSamplingUtils$.MODULE$.getCombOp();
   }

   public static Function2 getSeqOp(final boolean withReplacement, final Map fractions, final RandomDataGenerator rng, final Option counts) {
      return StratifiedSamplingUtils$.MODULE$.getSeqOp(withReplacement, fractions, rng, counts);
   }

   public static scala.collection.mutable.Map getAcceptanceResults(final RDD rdd, final boolean withReplacement, final Map fractions, final Option counts, final long seed) {
      return StratifiedSamplingUtils$.MODULE$.getAcceptanceResults(rdd, withReplacement, fractions, counts, seed);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return StratifiedSamplingUtils$.MODULE$.LogStringContext(sc);
   }

   private static class RandomDataGenerator {
      private final XORShiftRandom uniform = new XORShiftRandom();
      private final scala.collection.mutable.Map poissonCache;
      private long poissonSeed;

      public XORShiftRandom uniform() {
         return this.uniform;
      }

      public scala.collection.mutable.Map poissonCache() {
         return this.poissonCache;
      }

      public long poissonSeed() {
         return this.poissonSeed;
      }

      public void poissonSeed_$eq(final long x$1) {
         this.poissonSeed = x$1;
      }

      public void reSeed(final long seed) {
         this.uniform().setSeed(seed);
         this.poissonSeed_$eq(seed);
         this.poissonCache().clear();
      }

      public int nextPoisson(final double mean) {
         PoissonDistribution poisson = (PoissonDistribution)this.poissonCache().getOrElseUpdate(BoxesRunTime.boxToDouble(mean), () -> {
            PoissonDistribution newPoisson = new PoissonDistribution(mean);
            newPoisson.reseedRandomGenerator(this.poissonSeed());
            return newPoisson;
         });
         return poisson.sample();
      }

      public double nextUniform() {
         return this.uniform().nextDouble();
      }

      public RandomDataGenerator() {
         this.poissonCache = (scala.collection.mutable.Map).MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         this.poissonSeed = 0L;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
