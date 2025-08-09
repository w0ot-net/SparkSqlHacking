package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import org.apache.commons.math3.random.RandomGenerator;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001B\u0010!\u0001\u001eB\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\r\u0002\u0011\t\u0012)A\u0005\u0007\"Aq\t\u0001BK\u0002\u0013\u0005!\t\u0003\u0005I\u0001\tE\t\u0015!\u0003D\u0011!I\u0005A!f\u0001\n\u0003Q\u0005\u0002C-\u0001\u0005#\u0005\u000b\u0011B&\t\u000bi\u0003A\u0011A.\t\u000f\u0001\u0004!\u0019!C\u000bC\"1q\r\u0001Q\u0001\u000e\tDq\u0001\u001b\u0001\u0002\u0002\u0013\u0005\u0011\u000eC\u0004n\u0001E\u0005I\u0011\u00018\t\u000fe\u0004\u0011\u0013!C\u0001]\"9!\u0010AI\u0001\n\u0003Y\bbB?\u0001\u0003\u0003%\tE \u0005\n\u0003\u001f\u0001\u0011\u0011!C\u0001\u0003#A\u0011\"!\u0007\u0001\u0003\u0003%\t!a\u0007\t\u0013\u0005\u001d\u0002!!A\u0005B\u0005%\u0002\"CA\u001c\u0001\u0005\u0005I\u0011AA\u001d\u0011%\t\u0019\u0005AA\u0001\n\u0003\n)\u0005C\u0005\u0002J\u0001\t\t\u0011\"\u0011\u0002L!I\u0011Q\n\u0001\u0002\u0002\u0013\u0005\u0013q\n\u0005\n\u0003#\u0002\u0011\u0011!C!\u0003':q!a\u0016!\u0011\u0003\tIF\u0002\u0004 A!\u0005\u00111\f\u0005\u00075b!\t!!\u001c\t\u0013\u0005=\u0004$!A\u0005\u0002\u0006E\u0004\u0002CA=1E\u0005I\u0011A>\t\u0013\u0005m\u0004$!A\u0005\u0002\u0006u\u0004\u0002CAH1E\u0005I\u0011A>\t\u0013\u0005E\u0005$!A\u0005\n\u0005M%\u0001\u0005'fmf$\u0015n\u001d;sS\n,H/[8o\u0015\t\t#%A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003G\u0011\nQa\u001d;biNT\u0011!J\u0001\u0007EJ,WM_3\u0004\u0001M)\u0001\u0001\u000b\u00183kA\u0011\u0011\u0006L\u0007\u0002U)\t1&A\u0003tG\u0006d\u0017-\u0003\u0002.U\t1\u0011I\\=SK\u001a\u0004\"a\f\u0019\u000e\u0003\u0001J!!\r\u0011\u00039\u0005\u0003\u0018m\u00195f\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]B\u0011\u0011fM\u0005\u0003i)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00027}9\u0011q\u0007\u0010\b\u0003qmj\u0011!\u000f\u0006\u0003u\u0019\na\u0001\u0010:p_Rt\u0014\"A\u0016\n\u0005uR\u0013a\u00029bG.\fw-Z\u0005\u0003\u007f\u0001\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0010\u0016\u0002\u00055,X#A\"\u0011\u0005%\"\u0015BA#+\u0005\u0019!u.\u001e2mK\u0006\u0019Q.\u001e\u0011\u0002\u0003\r\f!a\u0019\u0011\u0002\u0013\u001d,g.\u001a:bi>\u0014X#A&\u0011\u00051;V\"A'\u000b\u00059{\u0015A\u0002:b]\u0012|WN\u0003\u0002Q#\u0006)Q.\u0019;ig)\u0011!kU\u0001\bG>lWn\u001c8t\u0015\t!V+\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0006\u0019qN]4\n\u0005ak%a\u0004*b]\u0012|WnR3oKJ\fGo\u001c:\u0002\u0015\u001d,g.\u001a:bi>\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u00059vsv\f\u0005\u00020\u0001!)\u0011i\u0002a\u0001\u0007\")qi\u0002a\u0001\u0007\"9\u0011j\u0002I\u0001\u0002\u0004Y\u0015!B5o]\u0016\u0014X#\u00012\u0011\u0005\r4W\"\u00013\u000b\u0005\u0015|\u0015\u0001\u00043jgR\u0014\u0018NY;uS>t\u0017BA\u0010e\u0003\u0019IgN\\3sA\u0005!1m\u001c9z)\u0011a&n\u001b7\t\u000f\u0005S\u0001\u0013!a\u0001\u0007\"9qI\u0003I\u0001\u0002\u0004\u0019\u0005bB%\u000b!\u0003\u0005\raS\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005y'FA\"qW\u0005\t\bC\u0001:x\u001b\u0005\u0019(B\u0001;v\u0003%)hn\u00195fG.,GM\u0003\u0002wU\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005a\u001c(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0002y*\u00121\n]\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003}\u0004B!!\u0001\u0002\f5\u0011\u00111\u0001\u0006\u0005\u0003\u000b\t9!\u0001\u0003mC:<'BAA\u0005\u0003\u0011Q\u0017M^1\n\t\u00055\u00111\u0001\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005M\u0001cA\u0015\u0002\u0016%\u0019\u0011q\u0003\u0016\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005u\u00111\u0005\t\u0004S\u0005}\u0011bAA\u0011U\t\u0019\u0011I\\=\t\u0013\u0005\u0015\u0002#!AA\u0002\u0005M\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002,A1\u0011QFA\u001a\u0003;i!!a\f\u000b\u0007\u0005E\"&\u0001\u0006d_2dWm\u0019;j_:LA!!\u000e\u00020\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tY$!\u0011\u0011\u0007%\ni$C\u0002\u0002@)\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002&I\t\t\u00111\u0001\u0002\u001e\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ry\u0018q\t\u0005\n\u0003K\u0019\u0012\u0011!a\u0001\u0003'\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003'\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u007f\u00061Q-];bYN$B!a\u000f\u0002V!I\u0011Q\u0005\f\u0002\u0002\u0003\u0007\u0011QD\u0001\u0011\u0019\u00164\u0018\u0010R5tiJL'-\u001e;j_:\u0004\"a\f\r\u0014\raA\u0013QLA2!\u0015y\u0013qL\"]\u0013\r\t\t\u0007\t\u0002$\u0007>tG/\u001b8v_V\u001cH)[:ue&\u0014W\u000f^5p]V3UO\\2Qe>4\u0018\u000eZ3s!\u0011\t)'a\u001b\u000e\u0005\u0005\u001d$\u0002BA5\u0003\u000f\t!![8\n\u0007}\n9\u0007\u0006\u0002\u0002Z\u0005)\u0011\r\u001d9msR9A,a\u001d\u0002v\u0005]\u0004\"B!\u001b\u0001\u0004\u0019\u0005\"B$\u001b\u0001\u0004\u0019\u0005bB%\u001b!\u0003\u0005\raS\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u00059QO\\1qa2LH\u0003BA@\u0003\u0017\u0003R!KAA\u0003\u000bK1!a!+\u0005\u0019y\u0005\u000f^5p]B1\u0011&a\"D\u0007.K1!!#+\u0005\u0019!V\u000f\u001d7fg!A\u0011Q\u0012\u000f\u0002\u0002\u0003\u0007A,A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAK!\u0011\t\t!a&\n\t\u0005e\u00151\u0001\u0002\u0007\u001f\nTWm\u0019;"
)
public class LevyDistribution implements ApacheContinuousDistribution, Product {
   private final double mu;
   private final double c;
   private final RandomGenerator generator;
   private final org.apache.commons.math3.distribution.LevyDistribution inner;
   private double logNormalizer;
   private double normalizer;
   private volatile byte bitmap$0;

   public static RandomGenerator $lessinit$greater$default$3() {
      return LevyDistribution$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final LevyDistribution x$0) {
      return LevyDistribution$.MODULE$.unapply(x$0);
   }

   public static RandomGenerator apply$default$3() {
      return LevyDistribution$.MODULE$.apply$default$3();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return LevyDistribution$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return LevyDistribution$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return LevyDistribution$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return LevyDistribution$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return LevyDistribution$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return LevyDistribution$.MODULE$.inPlace(v, impl);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double unnormalizedLogPdf(final double x) {
      return ApacheContinuousDistribution.unnormalizedLogPdf$(this, x);
   }

   public double pdf(final double x) {
      return ApacheContinuousDistribution.pdf$(this, x);
   }

   public double draw() {
      return ApacheContinuousDistribution.draw$(this);
   }

   public double[] drawMany(final int n) {
      return ApacheContinuousDistribution.drawMany$(this, n);
   }

   public double probability(final double x, final double y) {
      return ApacheContinuousDistribution.probability$(this, x, y);
   }

   public double inverseCdf(final double p) {
      return ApacheContinuousDistribution.inverseCdf$(this, p);
   }

   public double mean() {
      return ApacheContinuousDistribution.mean$(this);
   }

   public double variance() {
      return ApacheContinuousDistribution.variance$(this);
   }

   public double cdf(final double x) {
      return ApacheContinuousDistribution.cdf$(this, x);
   }

   public double draw$mcD$sp() {
      return ApacheContinuousDistribution.draw$mcD$sp$(this);
   }

   public double logPdf(final Object x) {
      return ContinuousDistr.logPdf$(this, x);
   }

   public double unnormalizedPdf(final Object x) {
      return ContinuousDistr.unnormalizedPdf$(this, x);
   }

   public double apply(final Object x) {
      return ContinuousDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return ContinuousDistr.logApply$(this, x);
   }

   public int draw$mcI$sp() {
      return Rand.draw$mcI$sp$(this);
   }

   public Object get() {
      return Rand.get$(this);
   }

   public double get$mcD$sp() {
      return Rand.get$mcD$sp$(this);
   }

   public int get$mcI$sp() {
      return Rand.get$mcI$sp$(this);
   }

   public Option drawOpt() {
      return Rand.drawOpt$(this);
   }

   public Object sample() {
      return Rand.sample$(this);
   }

   public double sample$mcD$sp() {
      return Rand.sample$mcD$sp$(this);
   }

   public int sample$mcI$sp() {
      return Rand.sample$mcI$sp$(this);
   }

   public IndexedSeq sample(final int n) {
      return Rand.sample$(this, n);
   }

   public Iterator samples() {
      return Rand.samples$(this);
   }

   public DenseVector samplesVector(final int size, final ClassTag m) {
      return Rand.samplesVector$(this, size, m);
   }

   public DenseVector samplesVector$mcD$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcD$sp$(this, size, m);
   }

   public DenseVector samplesVector$mcI$sp(final int size, final ClassTag m) {
      return Rand.samplesVector$mcI$sp$(this, size, m);
   }

   public Rand flatMap(final Function1 f) {
      return Rand.flatMap$(this, f);
   }

   public Rand flatMap$mcD$sp(final Function1 f) {
      return Rand.flatMap$mcD$sp$(this, f);
   }

   public Rand flatMap$mcI$sp(final Function1 f) {
      return Rand.flatMap$mcI$sp$(this, f);
   }

   public Rand map(final Function1 f) {
      return Rand.map$(this, f);
   }

   public Rand map$mcD$sp(final Function1 f) {
      return Rand.map$mcD$sp$(this, f);
   }

   public Rand map$mcI$sp(final Function1 f) {
      return Rand.map$mcI$sp$(this, f);
   }

   public void foreach(final Function1 f) {
      Rand.foreach$(this, f);
   }

   public void foreach$mcD$sp(final Function1 f) {
      Rand.foreach$mcD$sp$(this, f);
   }

   public void foreach$mcI$sp(final Function1 f) {
      Rand.foreach$mcI$sp$(this, f);
   }

   public Rand filter(final Function1 p) {
      return Rand.filter$(this, p);
   }

   public Rand filter$mcD$sp(final Function1 p) {
      return Rand.filter$mcD$sp$(this, p);
   }

   public Rand filter$mcI$sp(final Function1 p) {
      return Rand.filter$mcI$sp$(this, p);
   }

   public Rand withFilter(final Function1 p) {
      return Rand.withFilter$(this, p);
   }

   public Rand withFilter$mcD$sp(final Function1 p) {
      return Rand.withFilter$mcD$sp$(this, p);
   }

   public Rand withFilter$mcI$sp(final Function1 p) {
      return Rand.withFilter$mcI$sp$(this, p);
   }

   public Rand condition(final Function1 p) {
      return Rand.condition$(this, p);
   }

   public Rand condition$mcD$sp(final Function1 p) {
      return Rand.condition$mcD$sp$(this, p);
   }

   public Rand condition$mcI$sp(final Function1 p) {
      return Rand.condition$mcI$sp$(this, p);
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = ApacheContinuousDistribution.logNormalizer$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.logNormalizer;
   }

   public double logNormalizer() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.logNormalizer$lzycompute() : this.logNormalizer;
   }

   private double normalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public double mu() {
      return this.mu;
   }

   public double c() {
      return this.c;
   }

   public RandomGenerator generator() {
      return this.generator;
   }

   public final org.apache.commons.math3.distribution.LevyDistribution inner() {
      return this.inner;
   }

   public LevyDistribution copy(final double mu, final double c, final RandomGenerator generator) {
      return new LevyDistribution(mu, c, generator);
   }

   public double copy$default$1() {
      return this.mu();
   }

   public double copy$default$2() {
      return this.c();
   }

   public RandomGenerator copy$default$3() {
      return this.generator();
   }

   public String productPrefix() {
      return "LevyDistribution";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.mu());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.c());
            break;
         case 2:
            var10000 = this.generator();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof LevyDistribution;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mu";
            break;
         case 1:
            var10000 = "c";
            break;
         case 2:
            var10000 = "generator";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.mu()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.c()));
      var1 = Statics.mix(var1, Statics.anyHash(this.generator()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label57: {
            boolean var2;
            if (x$1 instanceof LevyDistribution) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label40: {
                  LevyDistribution var4 = (LevyDistribution)x$1;
                  if (this.mu() == var4.mu() && this.c() == var4.c()) {
                     label37: {
                        RandomGenerator var10000 = this.generator();
                        RandomGenerator var5 = var4.generator();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label37;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label37;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label40;
                        }
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label57;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public LevyDistribution(final double mu, final double c, final RandomGenerator generator) {
      this.mu = mu;
      this.c = c;
      this.generator = generator;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      ApacheContinuousDistribution.$init$(this);
      Product.$init$(this);
      this.inner = new org.apache.commons.math3.distribution.LevyDistribution(generator, mu, c);
   }
}
