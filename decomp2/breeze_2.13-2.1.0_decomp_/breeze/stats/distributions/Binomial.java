package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.numerics.package$lgamma$lgammaImplInt$;
import breeze.numerics.package$logI$logIBoolImpl$;
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

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001\u0002\u00180\u0001ZB\u0001\"\u0017\u0001\u0003\u0016\u0004%\tA\u0017\u0005\t7\u0002\u0011\t\u0012)A\u0005\u0003\"AA\f\u0001BK\u0002\u0013\u0005Q\f\u0003\u0005_\u0001\tE\t\u0015!\u0003H\u0011!y\u0006A!A!\u0002\u0017\u0001\u0007\"B2\u0001\t\u0003!W\u0001\u00026\u0001\u0001-DQA\u001c\u0001\u0005\u0002=DQA\u001d\u0001\u0005BMDQ\u0001 \u0001\u0005BuDaa \u0001\u0005B\u0005\u0005\u0001\u0002CA\u0002\u0001\t\u0007I\u0011B/\t\u000f\u0005\u0015\u0001\u0001)A\u0005\u000f\"A\u0011q\u0001\u0001C\u0002\u0013%Q\fC\u0004\u0002\n\u0001\u0001\u000b\u0011B$\t\u0011\u0005-\u0001A1A\u0005\nuCq!!\u0004\u0001A\u0003%q\t\u0003\u0005\u0002\u0010\u0001\u0011\r\u0011\"\u0003^\u0011\u001d\t\t\u0002\u0001Q\u0001\n\u001dC\u0001\"a\u0005\u0001\u0005\u0004%I!\u0018\u0005\b\u0003+\u0001\u0001\u0015!\u0003H\u0011!\t9\u0002\u0001b\u0001\n\u0013i\u0006bBA\r\u0001\u0001\u0006Ia\u0012\u0005\u0007\u00037\u0001A\u0011A/\t\r\u0005u\u0001\u0001\"\u0001^\u0011\u0019\ty\u0002\u0001C\u0001;\"1\u0011\u0011\u0005\u0001\u0005\u0002uC\u0011\"a\t\u0001\u0003\u0003%\t!!\n\t\u0013\u0005=\u0002!%A\u0005\u0002\u0005E\u0002\"CA$\u0001E\u0005I\u0011AA%\u0011%\ti\u0005AA\u0001\n\u0003\ny\u0005\u0003\u0005\u0002R\u0001\t\t\u0011\"\u0001[\u0011%\t\u0019\u0006AA\u0001\n\u0003\t)\u0006C\u0005\u0002b\u0001\t\t\u0011\"\u0011\u0002d!I\u0011\u0011\u000f\u0001\u0002\u0002\u0013\u0005\u00111\u000f\u0005\n\u0003{\u0002\u0011\u0011!C!\u0003\u007fB\u0011\"a!\u0001\u0003\u0003%\t%!\u0001\t\u0013\u0005\u0015\u0005!!A\u0005B\u0005\u001du!CAF_\u0005\u0005\t\u0012AAG\r!qs&!A\t\u0002\u0005=\u0005BB2)\t\u0003\tY\nC\u0004sQ\u0005\u0005IQI:\t\u0013\u0005u\u0005&!A\u0005\u0002\u0006}\u0005\"CAUQ\u0005\u0005I\u0011QAV\u0011%\ti\fKA\u0001\n\u0013\tyL\u0001\u0005CS:|W.[1m\u0015\t\u0001\u0014'A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003eM\nQa\u001d;biNT\u0011\u0001N\u0001\u0007EJ,WM_3\u0004\u0001M1\u0001aN\u001fE\u00156\u0003\"\u0001O\u001e\u000e\u0003eR\u0011AO\u0001\u0006g\u000e\fG.Y\u0005\u0003ye\u0012a!\u00118z%\u00164\u0007c\u0001 @\u00036\tq&\u0003\u0002A_\tiA)[:de\u0016$X\rR5tiJ\u0004\"\u0001\u000f\"\n\u0005\rK$aA%oiB!a(R$H\u0013\t1uFA\u0004N_6,g\u000e^:\u0011\u0005aB\u0015BA%:\u0005\u0019!u.\u001e2mKB\u0011\u0001hS\u0005\u0003\u0019f\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002O-:\u0011q\n\u0016\b\u0003!Nk\u0011!\u0015\u0006\u0003%V\na\u0001\u0010:p_Rt\u0014\"\u0001\u001e\n\u0005UK\u0014a\u00029bG.\fw-Z\u0005\u0003/b\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!V\u001d\u0002\u00039,\u0012!Q\u0001\u0003]\u0002\n\u0011\u0001]\u000b\u0002\u000f\u0006\u0011\u0001\u000fI\u0001\u0005e\u0006tG\r\u0005\u0002?C&\u0011!m\f\u0002\n%\u0006tGMQ1tSN\fa\u0001P5oSRtDcA3iSR\u0011am\u001a\t\u0003}\u0001AQa\u0018\u0004A\u0004\u0001DQ!\u0017\u0004A\u0002\u0005CQ\u0001\u0018\u0004A\u0002\u001d\u0013Q\u0001R5tiJ\u0004\"A\u00107\n\u00055|#!B$b[6\f\u0017!\u00049s_\n\f'-\u001b7jif|e\r\u0006\u0002Ha\")\u0011\u000f\u0003a\u0001\u0003\u0006\t1.\u0001\u0005u_N#(/\u001b8h)\u0005!\bCA;{\u001b\u00051(BA<y\u0003\u0011a\u0017M\\4\u000b\u0003e\fAA[1wC&\u00111P\u001e\u0002\u0007'R\u0014\u0018N\\4\u0002!1|w\r\u0015:pE\u0006\u0014\u0017\u000e\\5us>3GCA$\u007f\u0011\u0015\t(\u00021\u0001B\u0003\u0011!'/Y<\u0015\u0003\u0005\u000b!\u0001\u001d9\u0002\u0007A\u0004\b%A\u0003oM\u0006\u001cG/\u0001\u0004oM\u0006\u001cG\u000fI\u0001\u0003a\u000e\f1\u0001]2!\u0003\u0011\u0001Hn\\4\u0002\u000bAdwn\u001a\u0011\u0002\u000bA\u001cGn\\4\u0002\rA\u001cGn\\4!\u0003\t\u0019\u0018/A\u0002tc\u0002\nA!\\3b]\u0006Aa/\u0019:jC:\u001cW-\u0001\u0003n_\u0012,\u0017aB3oiJ|\u0007/_\u0001\u0005G>\u0004\u0018\u0010\u0006\u0004\u0002(\u0005-\u0012Q\u0006\u000b\u0004M\u0006%\u0002\"B0\u001d\u0001\b\u0001\u0007bB-\u001d!\u0003\u0005\r!\u0011\u0005\b9r\u0001\n\u00111\u0001H\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!a\r+\u0007\u0005\u000b)d\u000b\u0002\u00028A!\u0011\u0011HA\"\u001b\t\tYD\u0003\u0003\u0002>\u0005}\u0012!C;oG\",7m[3e\u0015\r\t\t%O\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA#\u0003w\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!a\u0013+\u0007\u001d\u000b)$A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002i\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA,\u0003;\u00022\u0001OA-\u0013\r\tY&\u000f\u0002\u0004\u0003:L\b\u0002CA0C\u0005\u0005\t\u0019A!\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t)\u0007\u0005\u0004\u0002h\u00055\u0014qK\u0007\u0003\u0003SR1!a\u001b:\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003_\nIG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA;\u0003w\u00022\u0001OA<\u0013\r\tI(\u000f\u0002\b\u0005>|G.Z1o\u0011%\tyfIA\u0001\u0002\u0004\t9&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,Gc\u0001;\u0002\u0002\"A\u0011q\f\u0013\u0002\u0002\u0003\u0007\u0011)\u0001\u0005iCND7i\u001c3f\u0003\u0019)\u0017/^1mgR!\u0011QOAE\u0011%\tyFJA\u0001\u0002\u0004\t9&\u0001\u0005CS:|W.[1m!\tq\u0004f\u0005\u0003)o\u0005E\u0005\u0003BAJ\u00033k!!!&\u000b\u0007\u0005]\u00050\u0001\u0002j_&\u0019q+!&\u0015\u0005\u00055\u0015!B1qa2LHCBAQ\u0003K\u000b9\u000bF\u0002g\u0003GCQaX\u0016A\u0004\u0001DQ!W\u0016A\u0002\u0005CQ\u0001X\u0016A\u0002\u001d\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002.\u0006e\u0006#\u0002\u001d\u00020\u0006M\u0016bAAYs\t1q\n\u001d;j_:\u0004R\u0001OA[\u0003\u001eK1!a.:\u0005\u0019!V\u000f\u001d7fe!A\u00111\u0018\u0017\u0002\u0002\u0003\u0007a-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!1\u0011\u0007U\f\u0019-C\u0002\u0002FZ\u0014aa\u00142kK\u000e$\b"
)
public class Binomial implements DiscreteDistr, Moments, Product {
   private final int n;
   private final double p;
   public final RandBasis breeze$stats$distributions$Binomial$$rand;
   private final double breeze$stats$distributions$Binomial$$pp;
   private final double breeze$stats$distributions$Binomial$$nfact;
   private final double pc;
   private final double breeze$stats$distributions$Binomial$$plog;
   private final double breeze$stats$distributions$Binomial$$pclog;
   private final double breeze$stats$distributions$Binomial$$sq;

   public static Option unapply(final Binomial x$0) {
      return Binomial$.MODULE$.unapply(x$0);
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

   public int n() {
      return this.n;
   }

   public double p() {
      return this.p;
   }

   public double probabilityOf(final int k) {
      return .MODULE$.exp(this.logProbabilityOf(k));
   }

   public String toString() {
      return (new StringBuilder(12)).append("Binomial(").append(this.n()).append(", ").append(this.p()).append(")").toString();
   }

   public double logProbabilityOf(final int k) {
      scala.Predef..MODULE$.require(this.n() >= k);
      scala.Predef..MODULE$.require(k >= 0);
      return this.p() == (double)0 ? BoxesRunTime.unboxToDouble(package.logI$.MODULE$.apply(BoxesRunTime.boxToBoolean(k == 0), package$logI$logIBoolImpl$.MODULE$)) : (this.p() == (double)1 ? BoxesRunTime.unboxToDouble(package.logI$.MODULE$.apply(BoxesRunTime.boxToBoolean(k == this.n()), package$logI$logIBoolImpl$.MODULE$)) : package.lgamma$.MODULE$.apply$mIDc$sp(this.n() + 1, package$lgamma$lgammaImplInt$.MODULE$) - package.lgamma$.MODULE$.apply$mIDc$sp(k + 1, package$lgamma$lgammaImplInt$.MODULE$) - package.lgamma$.MODULE$.apply$mIDc$sp(this.n() - k + 1, package$lgamma$lgammaImplInt$.MODULE$) + (double)k * .MODULE$.log(this.p()) + (double)(this.n() - k) * .MODULE$.log((double)1 - this.p()));
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public double breeze$stats$distributions$Binomial$$pp() {
      return this.breeze$stats$distributions$Binomial$$pp;
   }

   public double breeze$stats$distributions$Binomial$$nfact() {
      return this.breeze$stats$distributions$Binomial$$nfact;
   }

   private double pc() {
      return this.pc;
   }

   public double breeze$stats$distributions$Binomial$$plog() {
      return this.breeze$stats$distributions$Binomial$$plog;
   }

   public double breeze$stats$distributions$Binomial$$pclog() {
      return this.breeze$stats$distributions$Binomial$$pclog;
   }

   public double breeze$stats$distributions$Binomial$$sq() {
      return this.breeze$stats$distributions$Binomial$$sq;
   }

   public double mean() {
      return (double)this.n() * this.p();
   }

   public double variance() {
      return this.mean() * ((double)1 - this.p());
   }

   public double mode() {
      return .MODULE$.floor((double)(this.n() + 1) * this.p());
   }

   public double entropy() {
      return (double)0.5F * .MODULE$.log((Math.PI * 2D) * this.variance());
   }

   public Binomial copy(final int n, final double p, final RandBasis rand) {
      return new Binomial(n, p, rand);
   }

   public int copy$default$1() {
      return this.n();
   }

   public double copy$default$2() {
      return this.p();
   }

   public String productPrefix() {
      return "Binomial";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.n());
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
      return x$1 instanceof Binomial;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "n";
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
      var1 = Statics.mix(var1, this.n());
      var1 = Statics.mix(var1, Statics.doubleHash(this.p()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof Binomial) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Binomial var4 = (Binomial)x$1;
               if (this.n() == var4.n() && this.p() == var4.p() && var4.canEqual(this)) {
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
      double bnl = (double)0.0F;
      if (this.n() < 25) {
         for(int j = 0; j < this.n(); ++j) {
            if (this.breeze$stats$distributions$Binomial$$rand.uniform().draw$mcD$sp() < this.breeze$stats$distributions$Binomial$$pp()) {
               bnl += (double)1;
            }
         }
      } else if ((double)this.n() * this.breeze$stats$distributions$Binomial$$pp() < (double)1.0F) {
         double g = .MODULE$.exp((double)(-this.n()) * this.breeze$stats$distributions$Binomial$$pp());
         double t = (double)1.0F;
         int j = 0;
         boolean ok = true;

         while(j < this.n() && ok) {
            t *= this.breeze$stats$distributions$Binomial$$rand.uniform().draw$mcD$sp();
            if (t < g) {
               ok = false;
            } else {
               ++j;
            }
         }

         bnl = j <= this.n() ? (double)j : (double)this.n();
      } else {
         double y = (double)1.0F;
         double t = (double)1.0F;

         for(boolean continueOuter = true; continueOuter; continueOuter = this.breeze$stats$distributions$Binomial$$rand.uniform().draw$mcD$sp() > t) {
            for(boolean continueInner = true; continueInner; continueInner = bnl < (double)0.0F || bnl >= (double)this.n() + (double)1.0F) {
               double angle = Math.PI * this.breeze$stats$distributions$Binomial$$rand.uniform().draw$mcD$sp();
               y = .MODULE$.tan(angle);
               bnl = this.breeze$stats$distributions$Binomial$$sq() * y + (double)this.n() * this.breeze$stats$distributions$Binomial$$pp();
            }

            bnl = .MODULE$.floor(bnl);
            t = 1.2 * this.breeze$stats$distributions$Binomial$$sq() * ((double)1.0F + y * y) * .MODULE$.exp(this.breeze$stats$distributions$Binomial$$nfact() - package.lgamma$.MODULE$.apply$mDDc$sp(bnl + (double)1.0F, package$lgamma$lgammaImplDouble$.MODULE$) - package.lgamma$.MODULE$.apply$mDDc$sp((double)this.n() - bnl + (double)1.0F, package$lgamma$lgammaImplDouble$.MODULE$) + bnl * this.breeze$stats$distributions$Binomial$$plog() + ((double)this.n() - bnl) * this.breeze$stats$distributions$Binomial$$pclog());
         }
      }

      if (this.p() != this.breeze$stats$distributions$Binomial$$pp()) {
         bnl = (double)this.n() - bnl;
      }

      return (int)bnl;
   }

   public Binomial(final int n, final double p, final RandBasis rand) {
      this.n = n;
      this.p = p;
      this.breeze$stats$distributions$Binomial$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(n > 0, () -> "n must be positive!");
      scala.Predef..MODULE$.require(p >= (double)0.0F, () -> "p must be non-negative!");
      this.breeze$stats$distributions$Binomial$$pp = p <= (double)0.5F ? p : (double)1.0F - p;
      this.breeze$stats$distributions$Binomial$$nfact = package.lgamma$.MODULE$.apply$mDDc$sp((double)n + (double)1.0F, package$lgamma$lgammaImplDouble$.MODULE$);
      this.pc = (double)1.0F - this.breeze$stats$distributions$Binomial$$pp();
      this.breeze$stats$distributions$Binomial$$plog = .MODULE$.log(this.breeze$stats$distributions$Binomial$$pp());
      this.breeze$stats$distributions$Binomial$$pclog = .MODULE$.log(this.pc());
      this.breeze$stats$distributions$Binomial$$sq = .MODULE$.sqrt((double)2.0F * (double)n * this.breeze$stats$distributions$Binomial$$pp() * this.pc());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
