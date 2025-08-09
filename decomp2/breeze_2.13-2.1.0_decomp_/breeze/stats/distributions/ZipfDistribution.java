package breeze.stats.distributions;

import breeze.linalg.DenseVector;
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
   bytes = "\u0006\u0005\u0005md\u0001B\u000e\u001d\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005I\u0001\tE\t\u0015!\u0003F\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u001dq\u0005A1A\u0005\u0016=Ca!\u0018\u0001!\u0002\u001b\u0001\u0006b\u00020\u0001\u0003\u0003%\ta\u0018\u0005\bE\u0002\t\n\u0011\"\u0001d\u0011\u001dq\u0007!%A\u0005\u0002=Dq!\u001d\u0001\u0002\u0002\u0013\u0005#\u000fC\u0004|\u0001\u0005\u0005I\u0011\u0001 \t\u000fq\u0004\u0011\u0011!C\u0001{\"I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0002\u0005\n\u0003/\u0001\u0011\u0011!C\u0001\u00033A\u0011\"a\t\u0001\u0003\u0003%\t%!\n\t\u0013\u0005%\u0002!!A\u0005B\u0005-\u0002\"CA\u0017\u0001\u0005\u0005I\u0011IA\u0018\u0011%\t\t\u0004AA\u0001\n\u0003\n\u0019dB\u0005\u00028q\t\t\u0011#\u0001\u0002:\u0019A1\u0004HA\u0001\u0012\u0003\tY\u0004\u0003\u0004J+\u0011\u0005\u00111\u000b\u0005\n\u0003[)\u0012\u0011!C#\u0003_A\u0011\"!\u0016\u0016\u0003\u0003%\t)a\u0016\t\u0013\u0005uS#!A\u0005\u0002\u0006}\u0003\"CA9+\u0005\u0005I\u0011BA:\u0005AQ\u0016\u000e\u001d4ESN$(/\u001b2vi&|gN\u0003\u0002\u001e=\u0005iA-[:ue&\u0014W\u000f^5p]NT!a\b\u0011\u0002\u000bM$\u0018\r^:\u000b\u0003\u0005\naA\u0019:fKj,7\u0001A\n\u0006\u0001\u0011Rc&\r\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-bS\"\u0001\u000f\n\u00055b\"AG!qC\u000eDW\rR5tGJ,G/\u001a#jgR\u0014\u0018NY;uS>t\u0007CA\u00130\u0013\t\u0001dEA\u0004Qe>$Wo\u0019;\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1$%\u0001\u0004=e>|GOP\u0005\u0002O%\u0011\u0011HJ\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:M\u0005\u0001b.^7cKJ|e-\u00127f[\u0016tGo]\u000b\u0002\u007fA\u0011Q\u0005Q\u0005\u0003\u0003\u001a\u00121!\u00138u\u0003EqW/\u001c2fe>3W\t\\3nK:$8\u000fI\u0001\tKb\u0004xN\\3oiV\tQ\t\u0005\u0002&\r&\u0011qI\n\u0002\u0007\t>,(\r\\3\u0002\u0013\u0015D\bo\u001c8f]R\u0004\u0013A\u0002\u001fj]&$h\bF\u0002L\u00196\u0003\"a\u000b\u0001\t\u000bu*\u0001\u0019A \t\u000b\r+\u0001\u0019A#\u0002\u000b%tg.\u001a:\u0016\u0003A\u0003\"!\u0015/\u000e\u0003IS!a\u0015+\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0005U3\u0016!B7bi\"\u001c$BA,Y\u0003\u001d\u0019w.\\7p]NT!!\u0017.\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Y\u0016aA8sO&\u00111DU\u0001\u0007S:tWM\u001d\u0011\u0002\t\r|\u0007/\u001f\u000b\u0004\u0017\u0002\f\u0007bB\u001f\t!\u0003\u0005\ra\u0010\u0005\b\u0007\"\u0001\n\u00111\u0001F\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u001a\u0016\u0003\u007f\u0015\\\u0013A\u001a\t\u0003O2l\u0011\u0001\u001b\u0006\u0003S*\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005-4\u0013AC1o]>$\u0018\r^5p]&\u0011Q\u000e\u001b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002a*\u0012Q)Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003M\u0004\"\u0001^=\u000e\u0003UT!A^<\u0002\t1\fgn\u001a\u0006\u0002q\u0006!!.\u0019<b\u0013\tQXO\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rq\u00181\u0001\t\u0003K}L1!!\u0001'\u0005\r\te.\u001f\u0005\t\u0003\u000bi\u0011\u0011!a\u0001\u007f\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a\u0003\u0011\u000b\u00055\u00111\u0003@\u000e\u0005\u0005=!bAA\tM\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005U\u0011q\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u001c\u0005\u0005\u0002cA\u0013\u0002\u001e%\u0019\u0011q\u0004\u0014\u0003\u000f\t{w\u000e\\3b]\"A\u0011QA\b\u0002\u0002\u0003\u0007a0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GcA:\u0002(!A\u0011Q\u0001\t\u0002\u0002\u0003\u0007q(\u0001\u0005iCND7i\u001c3f)\u0005y\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003M\fa!Z9vC2\u001cH\u0003BA\u000e\u0003kA\u0001\"!\u0002\u0014\u0003\u0003\u0005\rA`\u0001\u00115&\u0004h\rR5tiJL'-\u001e;j_:\u0004\"aK\u000b\u0014\u000bU\ti$!\u0013\u0011\u000f\u0005}\u0012QI F\u00176\u0011\u0011\u0011\t\u0006\u0004\u0003\u00072\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003\u000f\n\tEA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a\u0013\u0002R5\u0011\u0011Q\n\u0006\u0004\u0003\u001f:\u0018AA5p\u0013\rY\u0014Q\n\u000b\u0003\u0003s\tQ!\u00199qYf$RaSA-\u00037BQ!\u0010\rA\u0002}BQa\u0011\rA\u0002\u0015\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002b\u00055\u0004#B\u0013\u0002d\u0005\u001d\u0014bAA3M\t1q\n\u001d;j_:\u0004R!JA5\u007f\u0015K1!a\u001b'\u0005\u0019!V\u000f\u001d7fe!A\u0011qN\r\u0002\u0002\u0003\u00071*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001e\u0011\u0007Q\f9(C\u0002\u0002zU\u0014aa\u00142kK\u000e$\b"
)
public class ZipfDistribution implements ApacheDiscreteDistribution, Product {
   private final int numberOfElements;
   private final double exponent;
   private final org.apache.commons.math3.distribution.ZipfDistribution inner;

   public static Option unapply(final ZipfDistribution x$0) {
      return ZipfDistribution$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return ZipfDistribution$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ZipfDistribution$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double probabilityOf(final int x) {
      return ApacheDiscreteDistribution.probabilityOf$(this, x);
   }

   public int draw() {
      return ApacheDiscreteDistribution.draw$(this);
   }

   public int[] drawMany(final int n) {
      return ApacheDiscreteDistribution.drawMany$(this, n);
   }

   public int draw$mcI$sp() {
      return ApacheDiscreteDistribution.draw$mcI$sp$(this);
   }

   public double logProbabilityOf(final Object x) {
      return DiscreteDistr.logProbabilityOf$(this, x);
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

   public int numberOfElements() {
      return this.numberOfElements;
   }

   public double exponent() {
      return this.exponent;
   }

   public final org.apache.commons.math3.distribution.ZipfDistribution inner() {
      return this.inner;
   }

   public ZipfDistribution copy(final int numberOfElements, final double exponent) {
      return new ZipfDistribution(numberOfElements, exponent);
   }

   public int copy$default$1() {
      return this.numberOfElements();
   }

   public double copy$default$2() {
      return this.exponent();
   }

   public String productPrefix() {
      return "ZipfDistribution";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.numberOfElements());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.exponent());
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
      return x$1 instanceof ZipfDistribution;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "numberOfElements";
            break;
         case 1:
            var10000 = "exponent";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.numberOfElements());
      var1 = Statics.mix(var1, Statics.doubleHash(this.exponent()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof ZipfDistribution) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ZipfDistribution var4 = (ZipfDistribution)x$1;
               if (this.numberOfElements() == var4.numberOfElements() && this.exponent() == var4.exponent() && var4.canEqual(this)) {
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

   public ZipfDistribution(final int numberOfElements, final double exponent) {
      this.numberOfElements = numberOfElements;
      this.exponent = exponent;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      ApacheDiscreteDistribution.$init$(this);
      Product.$init$(this);
      this.inner = new org.apache.commons.math3.distribution.ZipfDistribution(numberOfElements, exponent);
   }
}
