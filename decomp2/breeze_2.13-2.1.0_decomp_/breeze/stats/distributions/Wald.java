package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.constants.package$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Me\u0001\u0002\u0012$\u0001*B\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005k!AQ\n\u0001BK\u0002\u0013\u00051\n\u0003\u0005O\u0001\tE\t\u0015!\u00036\u0011!y\u0005A!A!\u0002\u0017\u0001\u0006\"B*\u0001\t\u0003!\u0006\u0002\u0003.\u0001\u0011\u000b\u0007I\u0011A&\t\u000bm\u0003A\u0011A&\t\u000bq\u0003A\u0011A&\t\u000bu\u0003A\u0011A&\t\u000by\u0003A\u0011A0\t\u000b\u0001\u0004A\u0011A1\t\u000f\u0011\u0004!\u0019!C\u0005K\"1\u0011\u000e\u0001Q\u0001\n\u0019DqA\u001b\u0001\u0002\u0002\u0013\u00051\u000eC\u0004q\u0001E\u0005I\u0011A9\t\u000fq\u0004\u0011\u0013!C\u0001c\"9Q\u0010AA\u0001\n\u0003r\b\"CA\b\u0001\u0005\u0005I\u0011AA\t\u0011%\tI\u0002AA\u0001\n\u0003\tY\u0002C\u0005\u0002(\u0001\t\t\u0011\"\u0011\u0002*!I\u0011q\u0007\u0001\u0002\u0002\u0013\u0005\u0011\u0011\b\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"!\u0013\u0001\u0003\u0003%\t%a\u0013\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA)\u0001\u0005\u0005I\u0011IA*\u000f%\t9fIA\u0001\u0012\u0003\tIF\u0002\u0005#G\u0005\u0005\t\u0012AA.\u0011\u0019\u0019F\u0004\"\u0001\u0002h!I\u0011Q\n\u000f\u0002\u0002\u0013\u0015\u0013q\n\u0005\n\u0003Sb\u0012\u0011!CA\u0003WB\u0011\"!\u001e\u001d\u0003\u0003%\t)a\u001e\t\u0013\u0005%E$!A\u0005\n\u0005-%\u0001B,bY\u0012T!\u0001J\u0013\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t1s%A\u0003ti\u0006$8OC\u0001)\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0002\u0001,caZd\b\u0005\u0002-_5\tQFC\u0001/\u0003\u0015\u00198-\u00197b\u0013\t\u0001TF\u0001\u0004B]f\u0014VM\u001a\t\u0004eM*T\"A\u0012\n\u0005Q\u001a#aD\"p]RLg.^8vg\u0012K7\u000f\u001e:\u0011\u000512\u0014BA\u001c.\u0005\u0019!u.\u001e2mKB!!'O\u001b6\u0013\tQ4EA\u0004N_6,g\u000e^:\u0011\u00051b\u0014BA\u001f.\u0005\u001d\u0001&o\u001c3vGR\u0004\"aP$\u000f\u0005\u0001+eBA!E\u001b\u0005\u0011%BA\"*\u0003\u0019a$o\\8u}%\ta&\u0003\u0002G[\u00059\u0001/Y2lC\u001e,\u0017B\u0001%J\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1U&\u0001\u0003nK\u0006tW#A\u001b\u0002\u000b5,\u0017M\u001c\u0011\u0002\u000bMD\u0017\r]3\u0002\rMD\u0017\r]3!\u0003\u0011\u0011\u0018M\u001c3\u0011\u0005I\n\u0016B\u0001*$\u0005%\u0011\u0016M\u001c3CCNL7/\u0001\u0004=S:LGO\u0010\u000b\u0004+bKFC\u0001,X!\t\u0011\u0004\u0001C\u0003P\r\u0001\u000f\u0001\u000bC\u0003K\r\u0001\u0007Q\u0007C\u0003N\r\u0001\u0007Q'\u0001\u0003n_\u0012,\u0017\u0001\u0003<be&\fgnY3\u0002\u000f\u0015tGO]8qs\u0006iAn\\4O_Jl\u0017\r\\5{KJ\fA\u0001\u001a:boR\tQ'\u0001\nv]:|'/\\1mSj,G\rT8h!\u00124GCA\u001bc\u0011\u0015\u0019G\u00021\u00016\u0003\u0005A\u0018aA4f]V\ta\rE\u00023OVJ!\u0001[\u0012\u0003\tI\u000bg\u000eZ\u0001\u0005O\u0016t\u0007%\u0001\u0003d_BLHc\u00017o_R\u0011a+\u001c\u0005\u0006\u001f>\u0001\u001d\u0001\u0015\u0005\b\u0015>\u0001\n\u00111\u00016\u0011\u001diu\u0002%AA\u0002U\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001sU\t)4oK\u0001u!\t)(0D\u0001w\u0015\t9\b0A\u0005v]\u000eDWmY6fI*\u0011\u00110L\u0001\u000bC:tw\u000e^1uS>t\u0017BA>w\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u0010\u0005\u0003\u0002\u0002\u0005-QBAA\u0002\u0015\u0011\t)!a\u0002\u0002\t1\fgn\u001a\u0006\u0003\u0003\u0013\tAA[1wC&!\u0011QBA\u0002\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u00111\u0003\t\u0004Y\u0005U\u0011bAA\f[\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011QDA\u0012!\ra\u0013qD\u0005\u0004\u0003Ci#aA!os\"I\u0011Q\u0005\u000b\u0002\u0002\u0003\u0007\u00111C\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005-\u0002CBA\u0017\u0003g\ti\"\u0004\u0002\u00020)\u0019\u0011\u0011G\u0017\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00026\u0005=\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u000f\u0002BA\u0019A&!\u0010\n\u0007\u0005}RFA\u0004C_>dW-\u00198\t\u0013\u0005\u0015b#!AA\u0002\u0005u\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2a`A$\u0011%\t)cFA\u0001\u0002\u0004\t\u0019\"\u0001\u0005iCND7i\u001c3f)\t\t\u0019\"\u0001\u0005u_N#(/\u001b8h)\u0005y\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002<\u0005U\u0003\"CA\u00135\u0005\u0005\t\u0019AA\u000f\u0003\u00119\u0016\r\u001c3\u0011\u0005Ib2\u0003\u0002\u000f,\u0003;\u0002B!a\u0018\u0002f5\u0011\u0011\u0011\r\u0006\u0005\u0003G\n9!\u0001\u0002j_&\u0019\u0001*!\u0019\u0015\u0005\u0005e\u0013!B1qa2LHCBA7\u0003c\n\u0019\bF\u0002W\u0003_BQaT\u0010A\u0004ACQAS\u0010A\u0002UBQ!T\u0010A\u0002U\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002z\u0005\u0015\u0005#\u0002\u0017\u0002|\u0005}\u0014bAA?[\t1q\n\u001d;j_:\u0004R\u0001LAAkUJ1!a!.\u0005\u0019!V\u000f\u001d7fe!A\u0011q\u0011\u0011\u0002\u0002\u0003\u0007a+A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!$\u0011\t\u0005\u0005\u0011qR\u0005\u0005\u0003#\u000b\u0019A\u0001\u0004PE*,7\r\u001e"
)
public class Wald implements ContinuousDistr, Moments, Product {
   private double mode;
   private final double mean;
   private final double shape;
   private final RandBasis rand;
   private final Rand breeze$stats$distributions$Wald$$gen;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Wald x$0) {
      return Wald$.MODULE$.unapply(x$0);
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

   public double mean() {
      return this.mean;
   }

   public double shape() {
      return this.shape;
   }

   private double mode$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            double x = this.mean() / this.shape();
            double adjustment = .MODULE$.sqrt((double)1 + (double)9 * x * x / (double)4) - (double)1.5F * x;
            this.mode = adjustment * this.mean();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this.mode;
   }

   public double mode() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.mode$lzycompute() : this.mode;
   }

   public double variance() {
      return this.mean() * this.mean() * this.mean() / this.shape();
   }

   public double entropy() {
      throw scala.Predef..MODULE$.$qmark$qmark$qmark();
   }

   public double logNormalizer() {
      return (double)0.5F * .MODULE$.log((double)2 * package$.MODULE$.Pi() / this.shape());
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      double z = (x - this.mean()) / this.mean();
      return (double)-1.5F * package.log$.MODULE$.apply$mDDc$sp(x, package$log$logDoubleImpl$.MODULE$) - (double)0.5F * this.shape() * z * z / x;
   }

   public Rand breeze$stats$distributions$Wald$$gen() {
      return this.breeze$stats$distributions$Wald$$gen;
   }

   public Wald copy(final double mean, final double shape, final RandBasis rand) {
      return new Wald(mean, shape, rand);
   }

   public double copy$default$1() {
      return this.mean();
   }

   public double copy$default$2() {
      return this.shape();
   }

   public String productPrefix() {
      return "Wald";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.mean());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.shape());
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
      return x$1 instanceof Wald;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mean";
            break;
         case 1:
            var10000 = "shape";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.mean()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.shape()));
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
            if (x$1 instanceof Wald) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Wald var4 = (Wald)x$1;
               if (this.mean() == var4.mean() && this.shape() == var4.shape() && var4.canEqual(this)) {
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
      return this.breeze$stats$distributions$Wald$$gen().draw$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple3 $anonfun$gen$1(final Wald $this, final double nu) {
      double y = nu * nu;
      double x = $this.mean() + $this.mean() * $this.mean() * y * (double)0.5F / $this.shape() - (double)0.5F * $this.mean() / $this.shape() * .MODULE$.sqrt((double)4 * $this.mean() * $this.shape() * y + $this.mean() * $this.mean() * y * y);
      return new Tuple3(BoxesRunTime.boxToDouble(nu), BoxesRunTime.boxToDouble(y), BoxesRunTime.boxToDouble(x));
   }

   public Wald(final double mean, final double shape, final RandBasis rand) {
      this.mean = mean;
      this.shape = shape;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.breeze$stats$distributions$Wald$$gen = rand.gaussian((double)0.0F, (double)1.0F).map$mcD$sp((nu) -> $anonfun$gen$1(this, BoxesRunTime.unboxToDouble(nu))).flatMap((x$1) -> {
         if (x$1 != null) {
            double x = BoxesRunTime.unboxToDouble(x$1._3());
            Rand var2 = this.rand.uniform().map$mcD$sp((JFunction1.mcDD.sp)(z) -> z <= this.mean() / (this.mean() + x) ? x : this.mean() * this.mean() / x);
            return var2;
         } else {
            throw new MatchError(x$1);
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
