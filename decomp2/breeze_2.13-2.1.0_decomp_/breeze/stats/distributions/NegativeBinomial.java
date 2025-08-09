package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$exp$expDoubleImpl$;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.numerics.package$lgamma$lgammaImplInt$;
import java.lang.invoke.SerializedLambda;
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
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e\u0001B\u0010!\u0001\u001eB\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t\u0013\u0002\u0011\t\u0012)A\u0005\r\"A!\n\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005L\u0001\tE\t\u0015!\u0003G\u0011!a\u0005A!A!\u0002\u0017i\u0005\"\u0002)\u0001\t\u0003\t\u0006bB,\u0001\u0005\u0004%I\u0001\u0017\u0005\u00079\u0002\u0001\u000b\u0011B-\t\u000bu\u0003A\u0011\u00010\t\u000b}\u0003A\u0011\u00011\t\u000b\r\u0004A\u0011\t3\t\u000f\u001d\u0004\u0011\u0011!C\u0001Q\"9Q\u000eAI\u0001\n\u0003q\u0007bB=\u0001#\u0003%\tA\u001c\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011%\tI\u0001AA\u0001\n\u0003\tY\u0001C\u0005\u0002\u000e\u0001\t\t\u0011\"\u0001\u0002\u0010!I\u00111\u0004\u0001\u0002\u0002\u0013\u0005\u0013Q\u0004\u0005\n\u0003W\u0001\u0011\u0011!C\u0001\u0003[A\u0011\"a\u000e\u0001\u0003\u0003%\t%!\u000f\t\u0011\u0005u\u0002!!A\u0005ByC\u0011\"a\u0010\u0001\u0003\u0003%\t%!\u0011\t\u0013\u0005\r\u0003!!A\u0005B\u0005\u0015s!CA%A\u0005\u0005\t\u0012AA&\r!y\u0002%!A\t\u0002\u00055\u0003B\u0002)\u001a\t\u0003\tI\u0006C\u0005\u0002@e\t\t\u0011\"\u0012\u0002B!I\u00111L\r\u0002\u0002\u0013\u0005\u0015Q\f\u0005\n\u0003OJ\u0012\u0011!CA\u0003SB\u0011\"a\u001f\u001a\u0003\u0003%I!! \u0003!9+w-\u0019;jm\u0016\u0014\u0015N\\8nS\u0006d'BA\u0011#\u00035!\u0017n\u001d;sS\n,H/[8og*\u00111\u0005J\u0001\u0006gR\fGo\u001d\u0006\u0002K\u00051!M]3fu\u0016\u001c\u0001aE\u0003\u0001Q9*\u0004\b\u0005\u0002*Y5\t!FC\u0001,\u0003\u0015\u00198-\u00197b\u0013\ti#F\u0001\u0004B]f\u0014VM\u001a\t\u0004_A\u0012T\"\u0001\u0011\n\u0005E\u0002#!\u0004#jg\u000e\u0014X\r^3ESN$(\u000f\u0005\u0002*g%\u0011AG\u000b\u0002\u0004\u0013:$\bCA\u00157\u0013\t9$FA\u0004Qe>$Wo\u0019;\u0011\u0005e\neB\u0001\u001e@\u001d\tYd(D\u0001=\u0015\tid%\u0001\u0004=e>|GOP\u0005\u0002W%\u0011\u0001IK\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00115I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002AU\u0005\t!/F\u0001G!\tIs)\u0003\u0002IU\t1Ai\\;cY\u0016\f!A\u001d\u0011\u0002\u0003A\f!\u0001\u001d\u0011\u0002\tI\fg\u000e\u001a\t\u0003_9K!a\u0014\u0011\u0003\u0013I\u000bg\u000e\u001a\"bg&\u001c\u0018A\u0002\u001fj]&$h\bF\u0002S+Z#\"a\u0015+\u0011\u0005=\u0002\u0001\"\u0002'\u0007\u0001\bi\u0005\"\u0002#\u0007\u0001\u00041\u0005\"\u0002&\u0007\u0001\u00041\u0015aA4f]V\t\u0011\fE\u000205JJ!a\u0017\u0011\u0003\tI\u000bg\u000eZ\u0001\u0005O\u0016t\u0007%\u0001\u0003ee\u0006<H#\u0001\u001a\u0002\u001bA\u0014xNY1cS2LG/_(g)\t1\u0015\rC\u0003c\u0015\u0001\u0007!'A\u0001y\u0003Aawn\u001a)s_\n\f'-\u001b7jif|e\r\u0006\u0002GK\")am\u0003a\u0001e\u0005\t1.\u0001\u0003d_BLHcA5lYR\u00111K\u001b\u0005\u0006\u00192\u0001\u001d!\u0014\u0005\b\t2\u0001\n\u00111\u0001G\u0011\u001dQE\u0002%AA\u0002\u0019\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001pU\t1\u0005oK\u0001r!\t\u0011x/D\u0001t\u0015\t!X/A\u0005v]\u000eDWmY6fI*\u0011aOK\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001=t\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tA\u0010E\u0002~\u0003\u000bi\u0011A \u0006\u0004\u007f\u0006\u0005\u0011\u0001\u00027b]\u001eT!!a\u0001\u0002\t)\fg/Y\u0005\u0004\u0003\u000fq(AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u00013\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0005\u0002\u0018A\u0019\u0011&a\u0005\n\u0007\u0005U!FA\u0002B]fD\u0001\"!\u0007\u0012\u0003\u0003\u0005\rAM\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005}\u0001CBA\u0011\u0003O\t\t\"\u0004\u0002\u0002$)\u0019\u0011Q\u0005\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002*\u0005\r\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\f\u00026A\u0019\u0011&!\r\n\u0007\u0005M\"FA\u0004C_>dW-\u00198\t\u0013\u0005e1#!AA\u0002\u0005E\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2\u0001`A\u001e\u0011!\tI\u0002FA\u0001\u0002\u0004\u0011\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001`\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005=\u0012q\t\u0005\n\u000339\u0012\u0011!a\u0001\u0003#\t\u0001CT3hCRLg/\u001a\"j]>l\u0017.\u00197\u0011\u0005=J2\u0003B\r)\u0003\u001f\u0002B!!\u0015\u0002X5\u0011\u00111\u000b\u0006\u0005\u0003+\n\t!\u0001\u0002j_&\u0019!)a\u0015\u0015\u0005\u0005-\u0013!B1qa2LHCBA0\u0003G\n)\u0007F\u0002T\u0003CBQ\u0001\u0014\u000fA\u00045CQ\u0001\u0012\u000fA\u0002\u0019CQA\u0013\u000fA\u0002\u0019\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002l\u0005]\u0004#B\u0015\u0002n\u0005E\u0014bAA8U\t1q\n\u001d;j_:\u0004R!KA:\r\u001aK1!!\u001e+\u0005\u0019!V\u000f\u001d7fe!A\u0011\u0011P\u000f\u0002\u0002\u0003\u00071+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a \u0011\u0007u\f\t)C\u0002\u0002\u0004z\u0014aa\u00142kK\u000e$\b"
)
public class NegativeBinomial implements DiscreteDistr, Product {
   private final double r;
   private final double p;
   private final RandBasis rand;
   private final Rand breeze$stats$distributions$NegativeBinomial$$gen;

   public static Option unapply(final NegativeBinomial x$0) {
      return NegativeBinomial$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double unnormalizedProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedProbabilityOf$(this, x);
   }

   public double unnormalizedLogProbabilityOf(final Object x) {
      return DiscreteDistr.unnormalizedLogProbabilityOf$(this, x);
   }

   public double apply(final Object x) {
      return DiscreteDistr.apply$(this, x);
   }

   public double logApply(final Object x) {
      return DiscreteDistr.logApply$(this, x);
   }

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
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

   public double r() {
      return this.r;
   }

   public double p() {
      return this.p;
   }

   public Rand breeze$stats$distributions$NegativeBinomial$$gen() {
      return this.breeze$stats$distributions$NegativeBinomial$$gen;
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public double probabilityOf(final int x) {
      return package.exp$.MODULE$.apply$mDDc$sp(this.logProbabilityOf(x), package$exp$expDoubleImpl$.MODULE$);
   }

   public double logProbabilityOf(final int k) {
      return package.lgamma$.MODULE$.apply$mDDc$sp(this.r() + (double)k, package$lgamma$lgammaImplDouble$.MODULE$) - package.lgamma$.MODULE$.apply$mIDc$sp(k + 1, package$lgamma$lgammaImplInt$.MODULE$) - package.lgamma$.MODULE$.apply$mDDc$sp(this.r(), package$lgamma$lgammaImplDouble$.MODULE$) + this.r() * .MODULE$.log((double)1 - this.p()) + (double)k * .MODULE$.log(this.p());
   }

   public NegativeBinomial copy(final double r, final double p, final RandBasis rand) {
      return new NegativeBinomial(r, p, rand);
   }

   public double copy$default$1() {
      return this.r();
   }

   public double copy$default$2() {
      return this.p();
   }

   public String productPrefix() {
      return "NegativeBinomial";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.r());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.p());
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
      return x$1 instanceof NegativeBinomial;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "r";
            break;
         case 1:
            var10000 = "p";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.r()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.p()));
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
            if (x$1 instanceof NegativeBinomial) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               NegativeBinomial var4 = (NegativeBinomial)x$1;
               if (this.r() == var4.r() && this.p() == var4.p() && var4.canEqual(this)) {
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

   public int draw$mcI$sp() {
      return this.breeze$stats$distributions$NegativeBinomial$$gen().draw$mcI$sp();
   }

   // $FF: synthetic method
   public static final Rand $anonfun$gen$1(final NegativeBinomial $this, final double lambda) {
      return (new Poisson(lambda, $this.rand)).map$mcI$sp((JFunction1.mcII.sp)(i) -> i);
   }

   public NegativeBinomial(final double r, final double p, final RandBasis rand) {
      this.r = r;
      this.p = p;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      this.breeze$stats$distributions$NegativeBinomial$$gen = (new Gamma(r, p / ((double)1 - p), rand)).flatMap$mcD$sp((lambda) -> $anonfun$gen$1(this, BoxesRunTime.unboxToDouble(lambda)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
