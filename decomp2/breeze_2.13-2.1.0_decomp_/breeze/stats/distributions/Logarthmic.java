package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$expm1$expm1DoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$log1p$log1pDoubleImpl$;
import breeze.numerics.package$round$roundDoubleImpl$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e\u0001B\u0010!\u0001\u001eB\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005q!AQ\n\u0001B\u0001B\u0003-a\nC\u0003R\u0001\u0011\u0005!\u000bC\u0004X\u0001\t\u0007I\u0011B&\t\ra\u0003\u0001\u0015!\u00039\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u0015y\u0006\u0001\"\u0001L\u0011\u0015\u0001\u0007\u0001\"\u0001L\u0011\u0015\t\u0007\u0001\"\u0001L\u0011\u0015\u0011\u0007\u0001\"\u0001d\u0011\u00159\u0007\u0001\"\u0011i\u0011\u001d\t\b!!A\u0005\u0002IDqA\u001e\u0001\u0012\u0002\u0013\u0005q\u000fC\u0005\u0002\u0006\u0001\t\t\u0011\"\u0011\u0002\b!I\u0011q\u0003\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0004\u0005\n\u00037\u0001\u0011\u0011!C\u0001\u0003;A\u0011\"!\u000b\u0001\u0003\u0003%\t%a\u000b\t\u0013\u0005e\u0002!!A\u0005\u0002\u0005m\u0002\"CA#\u0001\u0005\u0005I\u0011IA$\u0011!\tY\u0005AA\u0001\n\u0003R\u0006\"CA'\u0001\u0005\u0005I\u0011IA(\u000f%\t\u0019\u0006IA\u0001\u0012\u0003\t)F\u0002\u0005 A\u0005\u0005\t\u0012AA,\u0011\u0019\t\u0016\u0004\"\u0001\u0002d!Aq-GA\u0001\n\u000b\n)\u0007C\u0005\u0002he\t\t\u0011\"!\u0002j!I\u0011\u0011O\r\u0002\u0002\u0013\u0005\u00151\u000f\u0005\n\u0003\u007fJ\u0012\u0011!C\u0005\u0003\u0003\u0013!\u0002T8hCJ$\b.\\5d\u0015\t\t#%A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003G\u0011\nQa\u001d;biNT\u0011!J\u0001\u0007EJ,WM_3\u0004\u0001M1\u0001\u0001\u000b\u00186wy\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007cA\u00181e5\t\u0001%\u0003\u00022A\tiA)[:de\u0016$X\rR5tiJ\u0004\"!K\u001a\n\u0005QR#aA%oiB!qF\u000e\u001d9\u0013\t9\u0004EA\u0004N_6,g\u000e^:\u0011\u0005%J\u0014B\u0001\u001e+\u0005\u0019!u.\u001e2mKB\u0011\u0011\u0006P\u0005\u0003{)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002@\u000f:\u0011\u0001)\u0012\b\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007\u001a\na\u0001\u0010:p_Rt\u0014\"A\u0016\n\u0005\u0019S\u0013a\u00029bG.\fw-Z\u0005\u0003\u0011&\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!A\u0012\u0016\u0002\u0003A,\u0012\u0001O\u0001\u0003a\u0002\nAA]1oIB\u0011qfT\u0005\u0003!\u0002\u0012\u0011BU1oI\n\u000b7/[:\u0002\rqJg.\u001b;?)\t\u0019f\u000b\u0006\u0002U+B\u0011q\u0006\u0001\u0005\u0006\u001b\u0012\u0001\u001dA\u0014\u0005\u0006\u0015\u0012\u0001\r\u0001O\u0001\u0002Q\u0006\u0011\u0001\u000eI\u0001\u0005IJ\fw\u000fF\u00013\u00035\u0001(o\u001c2bE&d\u0017\u000e^=PMR\u0011\u0001(\u0018\u0005\u0006=\"\u0001\rAM\u0001\u0002q\u0006!Q.Z1o\u0003!1\u0018M]5b]\u000e,\u0017\u0001B7pI\u0016\fq!\u001a8ue>\u0004\u00180F\u0001e!\tIS-\u0003\u0002gU\t9aj\u001c;iS:<\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003%\u0004\"A\u001b8\u000f\u0005-d\u0007CA!+\u0013\ti'&\u0001\u0004Qe\u0016$WMZ\u0005\u0003_B\u0014aa\u0015;sS:<'BA7+\u0003\u0011\u0019w\u000e]=\u0015\u0005M,HC\u0001+u\u0011\u0015ie\u0002q\u0001O\u0011\u001dQe\u0002%AA\u0002a\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001yU\tA\u0014pK\u0001{!\rY\u0018\u0011A\u0007\u0002y*\u0011QP`\u0001\nk:\u001c\u0007.Z2lK\u0012T!a \u0016\u0002\u0015\u0005tgn\u001c;bi&|g.C\u0002\u0002\u0004q\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011\u0011\u0002\t\u0005\u0003\u0017\t)\"\u0004\u0002\u0002\u000e)!\u0011qBA\t\u0003\u0011a\u0017M\\4\u000b\u0005\u0005M\u0011\u0001\u00026bm\u0006L1a\\A\u0007\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0011\u0014A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003?\t)\u0003E\u0002*\u0003CI1!a\t+\u0005\r\te.\u001f\u0005\t\u0003O\u0011\u0012\u0011!a\u0001e\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\f\u0011\r\u0005=\u0012QGA\u0010\u001b\t\t\tDC\u0002\u00024)\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9$!\r\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003{\t\u0019\u0005E\u0002*\u0003\u007fI1!!\u0011+\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\n\u0015\u0003\u0003\u0005\r!a\b\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0013\tI\u0005\u0003\u0005\u0002(U\t\t\u00111\u00013\u0003!A\u0017m\u001d5D_\u0012,\u0017AB3rk\u0006d7\u000f\u0006\u0003\u0002>\u0005E\u0003\"CA\u0014/\u0005\u0005\t\u0019AA\u0010\u0003)aunZ1si\"l\u0017n\u0019\t\u0003_e\u0019B!\u0007\u0015\u0002ZA!\u00111LA1\u001b\t\tiF\u0003\u0003\u0002`\u0005E\u0011AA5p\u0013\rA\u0015Q\f\u000b\u0003\u0003+\"\"!!\u0003\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005-\u0014q\u000e\u000b\u0004)\u00065\u0004\"B'\u001d\u0001\bq\u0005\"\u0002&\u001d\u0001\u0004A\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003k\nY\b\u0005\u0003*\u0003oB\u0014bAA=U\t1q\n\u001d;j_:D\u0001\"! \u001e\u0003\u0003\u0005\r\u0001V\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAB!\u0011\tY!!\"\n\t\u0005\u001d\u0015Q\u0002\u0002\u0007\u001f\nTWm\u0019;"
)
public class Logarthmic implements DiscreteDistr, Moments, Product {
   private final double p;
   public final RandBasis breeze$stats$distributions$Logarthmic$$rand;
   private final double breeze$stats$distributions$Logarthmic$$h;

   public static Option unapply(final Logarthmic x$0) {
      return Logarthmic$.MODULE$.unapply(x$0);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public double p() {
      return this.p;
   }

   public double breeze$stats$distributions$Logarthmic$$h() {
      return this.breeze$stats$distributions$Logarthmic$$h;
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public double probabilityOf(final int x) {
      return (double)-1.0F / package.log1p$.MODULE$.apply$mDDc$sp(-this.p(), package$log1p$log1pDoubleImpl$.MODULE$) * .MODULE$.pow(this.p(), (double)x) / (double)x;
   }

   public double mean() {
      return (double)-1.0F / package.log1p$.MODULE$.apply$mDDc$sp(-this.p(), package$log1p$log1pDoubleImpl$.MODULE$) * (this.p() / ((double)1 - this.p()));
   }

   public double variance() {
      double l1p = package.log1p$.MODULE$.apply$mDDc$sp(-this.p(), package$log1p$log1pDoubleImpl$.MODULE$);
      double onemp = (double)1 - this.p();
      double denompart = onemp * l1p;
      return -this.p() * (this.p() + l1p) / (denompart * denompart);
   }

   public double mode() {
      return (double)1.0F;
   }

   public Nothing entropy() {
      return scala.Predef..MODULE$.$qmark$qmark$qmark();
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public Logarthmic copy(final double p, final RandBasis rand) {
      return new Logarthmic(p, rand);
   }

   public double copy$default$1() {
      return this.p();
   }

   public String productPrefix() {
      return "Logarthmic";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
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
      return x$1 instanceof Logarthmic;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
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
      var1 = Statics.mix(var1, Statics.doubleHash(this.p()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Logarthmic) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Logarthmic var4 = (Logarthmic)x$1;
               if (this.p() == var4.p() && var4.canEqual(this)) {
                  break label49;
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
      double u2 = this.breeze$stats$distributions$Logarthmic$$rand.uniform().draw$mcD$sp();
      int var10000;
      if (u2 > this.p()) {
         var10000 = 1;
      } else {
         double u1 = this.breeze$stats$distributions$Logarthmic$$rand.uniform().draw$mcD$sp();
         double q = -package.expm1$.MODULE$.apply$mDDc$sp(u1 * this.breeze$stats$distributions$Logarthmic$$h(), package$expm1$expm1DoubleImpl$.MODULE$);
         var10000 = u2 < q * q ? (int)BoxesRunTime.unboxToLong(package.round$.MODULE$.apply(BoxesRunTime.boxToDouble((double)1.0F + package.log$.MODULE$.apply$mDDc$sp(u2, package$log$logDoubleImpl$.MODULE$) / package.log$.MODULE$.apply$mDDc$sp(q, package$log$logDoubleImpl$.MODULE$)), package$round$roundDoubleImpl$.MODULE$)) : (u2 > q ? 1 : 2);
      }

      return var10000;
   }

   public Logarthmic(final double p, final RandBasis rand) {
      this.p = p;
      this.breeze$stats$distributions$Logarthmic$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(p >= (double)0);
      scala.Predef..MODULE$.require(p <= (double)1);
      this.breeze$stats$distributions$Logarthmic$$h = package.log1p$.MODULE$.apply$mDDc$sp(-p, package$log1p$log1pDoubleImpl$.MODULE$);
   }
}
