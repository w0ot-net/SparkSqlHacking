package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$exp$expDoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.constants.package$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f\u0001B\u0012%\u0001.B\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t!\u0002\u0011\t\u0012)A\u0005m!A\u0011\u000b\u0001BK\u0002\u0013\u0005q\n\u0003\u0005S\u0001\tE\t\u0015!\u00037\u0011!\u0019\u0006A!A!\u0002\u0017!\u0006\"B,\u0001\t\u0003A\u0006\"\u00020\u0001\t\u0003y\u0005\"B0\u0001\t\u0003y\u0005\"\u00021\u0001\t\u0003y\u0005\"B1\u0001\t\u0003y\u0005\"\u00022\u0001\t\u0003y\u0005\"B2\u0001\t\u0003!\u0007\"B3\u0001\t\u00031\u0007\"B5\u0001\t\u0003Q\u0007\"\u00027\u0001\t\u0003j\u0007bB9\u0001\u0003\u0003%\tA\u001d\u0005\bo\u0002\t\n\u0011\"\u0001y\u0011!\t9\u0001AI\u0001\n\u0003A\b\"CA\u0005\u0001\u0005\u0005I\u0011IA\u0006\u0011%\ti\u0002AA\u0001\n\u0003\ty\u0002C\u0005\u0002(\u0001\t\t\u0011\"\u0001\u0002*!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003\u000b\u0002\u0011\u0011!C\u0001\u0003\u000fB\u0011\"!\u0015\u0001\u0003\u0003%\t%a\u0015\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u0011%\ty\u0006AA\u0001\n\u0003\n\tgB\u0005\u0002f\u0011\n\t\u0011#\u0001\u0002h\u0019A1\u0005JA\u0001\u0012\u0003\tI\u0007\u0003\u0004X;\u0011\u0005\u0011Q\u000f\u0005\n\u00037j\u0012\u0011!C#\u0003;B\u0011\"a\u001e\u001e\u0003\u0003%\t)!\u001f\t\u0013\u0005\rU$!A\u0005\u0002\u0006\u0015\u0005\"CAL;\u0005\u0005I\u0011BAM\u0005\u00199U/\u001c2fY*\u0011QEJ\u0001\u000eI&\u001cHO]5ckRLwN\\:\u000b\u0005\u001dB\u0013!B:uCR\u001c(\"A\u0015\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019r\u0001\u0001\u00173sqz$\t\u0005\u0002.a5\taFC\u00010\u0003\u0015\u00198-\u00197b\u0013\t\tdF\u0001\u0004B]f\u0014VM\u001a\t\u0004gQ2T\"\u0001\u0013\n\u0005U\"#aD\"p]RLg.^8vg\u0012K7\u000f\u001e:\u0011\u00055:\u0014B\u0001\u001d/\u0005\u0019!u.\u001e2mKB!1G\u000f\u001c7\u0013\tYDEA\u0004N_6,g\u000e^:\u0011\u0005Mj\u0014B\u0001 %\u0005\u0019A\u0015m]\"eMB\u0011Q\u0006Q\u0005\u0003\u0003:\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002D\u0017:\u0011A)\u0013\b\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000f*\na\u0001\u0010:p_Rt\u0014\"A\u0018\n\u0005)s\u0013a\u00029bG.\fw-Z\u0005\u0003\u00196\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0013\u0018\u0002\u00111|7-\u0019;j_:,\u0012AN\u0001\nY>\u001c\u0017\r^5p]\u0002\nQa]2bY\u0016\faa]2bY\u0016\u0004\u0013\u0001\u0002:b]\u0012\u0004\"aM+\n\u0005Y##!\u0003*b]\u0012\u0014\u0015m]5t\u0003\u0019a\u0014N\\5u}Q\u0019\u0011\fX/\u0015\u0005i[\u0006CA\u001a\u0001\u0011\u0015\u0019f\u0001q\u0001U\u0011\u0015qe\u00011\u00017\u0011\u0015\tf\u00011\u00017\u0003\u0011iW-\u00198\u0002\t5|G-Z\u0001\tm\u0006\u0014\u0018.\u00198dK\u00069QM\u001c;s_BL\u0018!\u00047pO:{'/\\1mSj,'/\u0001\u0003ee\u0006<H#\u0001\u001c\u0002%Utgn\u001c:nC2L'0\u001a3M_\u001e\u0004FM\u001a\u000b\u0003m\u001dDQ\u0001[\u0007A\u0002Y\n\u0011\u0001_\u0001\u0004G\u00124GC\u0001\u001cl\u0011\u0015Ag\u00021\u00017\u0003-\u0001(o\u001c2bE&d\u0017\u000e^=\u0015\u0007Yrw\u000eC\u0003i\u001f\u0001\u0007a\u0007C\u0003q\u001f\u0001\u0007a'A\u0001z\u0003\u0011\u0019w\u000e]=\u0015\u0007M,h\u000f\u0006\u0002[i\")1\u000b\u0005a\u0002)\"9a\n\u0005I\u0001\u0002\u00041\u0004bB)\u0011!\u0003\u0005\rAN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005I(F\u0001\u001c{W\u0005Y\bc\u0001?\u0002\u00045\tQP\u0003\u0002\u007f\u007f\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u0003q\u0013AC1o]>$\u0018\r^5p]&\u0019\u0011QA?\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\u0001\u0005\u0003\u0002\u0010\u0005eQBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\t1\fgn\u001a\u0006\u0003\u0003/\tAA[1wC&!\u00111DA\t\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011\u0011\u0005\t\u0004[\u0005\r\u0012bAA\u0013]\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u00111FA\u0019!\ri\u0013QF\u0005\u0004\u0003_q#aA!os\"I\u00111G\u000b\u0002\u0002\u0003\u0007\u0011\u0011E\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005e\u0002CBA\u001e\u0003\u0003\nY#\u0004\u0002\u0002>)\u0019\u0011q\b\u0018\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002D\u0005u\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0013\u0002PA\u0019Q&a\u0013\n\u0007\u00055cFA\u0004C_>dW-\u00198\t\u0013\u0005Mr#!AA\u0002\u0005-\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0004\u0002V!I\u00111\u0007\r\u0002\u0002\u0003\u0007\u0011\u0011E\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011E\u0001\ti>\u001cFO]5oOR\u0011\u0011QB\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005%\u00131\r\u0005\n\u0003gY\u0012\u0011!a\u0001\u0003W\taaR;nE\u0016d\u0007CA\u001a\u001e'\u0011iB&a\u001b\u0011\t\u00055\u00141O\u0007\u0003\u0003_RA!!\u001d\u0002\u0016\u0005\u0011\u0011n\\\u0005\u0004\u0019\u0006=DCAA4\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\tY(a \u0002\u0002R\u0019!,! \t\u000bM\u0003\u00039\u0001+\t\u000b9\u0003\u0003\u0019\u0001\u001c\t\u000bE\u0003\u0003\u0019\u0001\u001c\u0002\u000fUt\u0017\r\u001d9msR!\u0011qQAJ!\u0015i\u0013\u0011RAG\u0013\r\tYI\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b5\nyI\u000e\u001c\n\u0007\u0005EeF\u0001\u0004UkBdWM\r\u0005\t\u0003+\u000b\u0013\u0011!a\u00015\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005m\u0005\u0003BA\b\u0003;KA!a(\u0002\u0012\t1qJ\u00196fGR\u0004"
)
public class Gumbel implements ContinuousDistr, Moments, HasCdf, Product {
   private final double location;
   private final double scale;
   public final RandBasis breeze$stats$distributions$Gumbel$$rand;
   private double normalizer;
   private volatile boolean bitmap$0;

   public static Option unapply(final Gumbel x$0) {
      return Gumbel$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double pdf(final Object x) {
      return ContinuousDistr.pdf$(this, x);
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

   private double normalizer$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return !this.bitmap$0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public double location() {
      return this.location;
   }

   public double scale() {
      return this.scale;
   }

   public double mean() {
      return this.location() + this.scale() * package$.MODULE$.γ();
   }

   public double mode() {
      return this.location();
   }

   public double variance() {
      return package$.MODULE$.Pi() * package$.MODULE$.Pi() / (double)6 * this.scale() * this.scale();
   }

   public double entropy() {
      return package.log$.MODULE$.apply$mDDc$sp(this.scale(), package$log$logDoubleImpl$.MODULE$) + package$.MODULE$.γ() + (double)1;
   }

   public double logNormalizer() {
      return .MODULE$.log(this.scale());
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      double z = (x - this.location()) / this.scale();
      return -(z + package.exp$.MODULE$.apply$mDDc$sp(-z, package$exp$expDoubleImpl$.MODULE$));
   }

   public double cdf(final double x) {
      return .MODULE$.exp(-.MODULE$.exp(-(x - this.location()) / this.scale()));
   }

   public double probability(final double x, final double y) {
      return this.cdf(y) - this.cdf(x);
   }

   public Gumbel copy(final double location, final double scale, final RandBasis rand) {
      return new Gumbel(location, scale, rand);
   }

   public double copy$default$1() {
      return this.location();
   }

   public double copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "Gumbel";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.location());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.scale());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Gumbel;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "location";
            break;
         case 1:
            var10000 = "scale";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.location()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Gumbel) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Gumbel var4 = (Gumbel)x$1;
               if (this.location() == var4.location() && this.scale() == var4.scale() && var4.canEqual(this)) {
                  break label51;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public double draw$mcD$sp() {
      double u = this.breeze$stats$distributions$Gumbel$$rand.uniform().draw$mcD$sp();
      return this.location() - this.scale() * package.log$.MODULE$.apply$mDDc$sp(-package.log$.MODULE$.apply$mDDc$sp(u, package$log$logDoubleImpl$.MODULE$), package$log$logDoubleImpl$.MODULE$);
   }

   public Gumbel(final double location, final double scale, final RandBasis rand) {
      this.location = location;
      this.scale = scale;
      this.breeze$stats$distributions$Gumbel$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
   }
}
