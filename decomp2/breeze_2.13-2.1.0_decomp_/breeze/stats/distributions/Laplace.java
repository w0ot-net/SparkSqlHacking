package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$exp$expDoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
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
   bytes = "\u0006\u0005\u0005\u0005f\u0001B\u0012%\u0001.B\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t!\u0002\u0011\t\u0012)A\u0005m!A\u0011\u000b\u0001BK\u0002\u0013\u0005q\n\u0003\u0005S\u0001\tE\t\u0015!\u00037\u0011!\u0019\u0006A!A!\u0002\u0017!\u0006\"B,\u0001\t\u0003A\u0006\"\u00020\u0001\t\u0003y\u0005\"B0\u0001\t\u0003y\u0005\"\u00021\u0001\t\u0003y\u0005\"B1\u0001\t\u0003y\u0005\"\u00022\u0001\t\u0003y\u0005\"B2\u0001\t\u0003!\u0007\"B3\u0001\t\u00031\u0007\"B5\u0001\t\u0003Q\u0007\"\u00028\u0001\t\u0003y\u0007bB9\u0001\u0003\u0003%\tA\u001d\u0005\bo\u0002\t\n\u0011\"\u0001y\u0011!\t9\u0001AI\u0001\n\u0003A\b\"CA\u0005\u0001\u0005\u0005I\u0011IA\u0006\u0011%\ti\u0002AA\u0001\n\u0003\ty\u0002C\u0005\u0002(\u0001\t\t\u0011\"\u0001\u0002*!I\u0011Q\u0007\u0001\u0002\u0002\u0013\u0005\u0013q\u0007\u0005\n\u0003\u000b\u0002\u0011\u0011!C\u0001\u0003\u000fB\u0011\"!\u0015\u0001\u0003\u0003%\t%a\u0015\t\u0013\u0005]\u0003!!A\u0005B\u0005e\u0003\"CA.\u0001\u0005\u0005I\u0011IA/\u0011%\ty\u0006AA\u0001\n\u0003\n\tgB\u0005\u0002f\u0011\n\t\u0011#\u0001\u0002h\u0019A1\u0005JA\u0001\u0012\u0003\tI\u0007\u0003\u0004X;\u0011\u0005\u0011Q\u000f\u0005\n\u00037j\u0012\u0011!C#\u0003;B\u0011\"a\u001e\u001e\u0003\u0003%\t)!\u001f\t\u0013\u0005\rU$!A\u0005\u0002\u0006\u0015\u0005\"CAL;\u0005\u0005I\u0011BAM\u0005\u001da\u0015\r\u001d7bG\u0016T!!\n\u0014\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t9\u0003&A\u0003ti\u0006$8OC\u0001*\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0002\u0001-eebtH\u0011\t\u0003[Aj\u0011A\f\u0006\u0002_\u0005)1oY1mC&\u0011\u0011G\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M\"d'D\u0001%\u0013\t)DEA\bD_:$\u0018N\\;pkN$\u0015n\u001d;s!\tis'\u0003\u00029]\t1Ai\\;cY\u0016\u0004Ba\r\u001e7m%\u00111\b\n\u0002\b\u001b>lWM\u001c;t!\t\u0019T(\u0003\u0002?I\t1\u0001*Y:DI\u001a\u0004\"!\f!\n\u0005\u0005s#a\u0002)s_\u0012,8\r\u001e\t\u0003\u0007.s!\u0001R%\u000f\u0005\u0015CU\"\u0001$\u000b\u0005\u001dS\u0013A\u0002\u001fs_>$h(C\u00010\u0013\tQe&A\u0004qC\u000e\\\u0017mZ3\n\u00051k%\u0001D*fe&\fG.\u001b>bE2,'B\u0001&/\u0003!awnY1uS>tW#\u0001\u001c\u0002\u00131|7-\u0019;j_:\u0004\u0013!B:dC2,\u0017AB:dC2,\u0007%\u0001\u0003sC:$\u0007CA\u001aV\u0013\t1FEA\u0005SC:$')Y:jg\u00061A(\u001b8jiz\"2!\u0017/^)\tQ6\f\u0005\u00024\u0001!)1K\u0002a\u0002)\")aJ\u0002a\u0001m!)\u0011K\u0002a\u0001m\u0005!Q.Z1o\u0003\u0011iw\u000eZ3\u0002\u0011Y\f'/[1oG\u0016\fq!\u001a8ue>\u0004\u00180A\u0007m_\u001etuN]7bY&TXM]\u0001\u0005IJ\fw\u000fF\u00017\u0003I)hN\\8s[\u0006d\u0017N_3e\u0019><\u0007\u000b\u001a4\u0015\u0005Y:\u0007\"\u00025\u000e\u0001\u00041\u0014!\u0001=\u0002\u0017A\u0014xNY1cS2LG/\u001f\u000b\u0004m-d\u0007\"\u00025\u000f\u0001\u00041\u0004\"B7\u000f\u0001\u00041\u0014!A=\u0002\u0007\r$g\r\u0006\u00027a\")\u0001n\u0004a\u0001m\u0005!1m\u001c9z)\r\u0019XO\u001e\u000b\u00035RDQa\u0015\tA\u0004QCqA\u0014\t\u0011\u0002\u0003\u0007a\u0007C\u0004R!A\u0005\t\u0019\u0001\u001c\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t\u0011P\u000b\u00027u.\n1\u0010E\u0002}\u0003\u0007i\u0011! \u0006\u0003}~\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005a&\u0001\u0006b]:|G/\u0019;j_:L1!!\u0002~\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0002\t\u0005\u0003\u001f\tI\"\u0004\u0002\u0002\u0012)!\u00111CA\u000b\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0011\u0001\u00026bm\u0006LA!a\u0007\u0002\u0012\t11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\t\u0011\u00075\n\u0019#C\u0002\u0002&9\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u000b\u00022A\u0019Q&!\f\n\u0007\u0005=bFA\u0002B]fD\u0011\"a\r\u0016\u0003\u0003\u0005\r!!\t\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\u0004\u0005\u0004\u0002<\u0005\u0005\u00131F\u0007\u0003\u0003{Q1!a\u0010/\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u0007\niD\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA%\u0003\u001f\u00022!LA&\u0013\r\tiE\f\u0002\b\u0005>|G.Z1o\u0011%\t\u0019dFA\u0001\u0002\u0004\tY#\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0007\u0003+B\u0011\"a\r\u0019\u0003\u0003\u0005\r!!\t\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\t\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!\u0004\u0002\r\u0015\fX/\u00197t)\u0011\tI%a\u0019\t\u0013\u0005M2$!AA\u0002\u0005-\u0012a\u0002'ba2\f7-\u001a\t\u0003gu\u0019B!\b\u0017\u0002lA!\u0011QNA:\u001b\t\tyG\u0003\u0003\u0002r\u0005U\u0011AA5p\u0013\ra\u0015q\u000e\u000b\u0003\u0003O\nQ!\u00199qYf$b!a\u001f\u0002\u0000\u0005\u0005Ec\u0001.\u0002~!)1\u000b\ta\u0002)\")a\n\ta\u0001m!)\u0011\u000b\ta\u0001m\u00059QO\\1qa2LH\u0003BAD\u0003'\u0003R!LAE\u0003\u001bK1!a#/\u0005\u0019y\u0005\u000f^5p]B)Q&a$7m%\u0019\u0011\u0011\u0013\u0018\u0003\rQ+\b\u000f\\33\u0011!\t)*IA\u0001\u0002\u0004Q\u0016a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0014\t\u0005\u0003\u001f\ti*\u0003\u0003\u0002 \u0006E!AB(cU\u0016\u001cG\u000f"
)
public class Laplace implements ContinuousDistr, Moments, HasCdf, Product {
   private final double location;
   private final double scale;
   public final RandBasis breeze$stats$distributions$Laplace$$rand;
   private double normalizer;
   private volatile boolean bitmap$0;

   public static Option unapply(final Laplace x$0) {
      return Laplace$.MODULE$.unapply(x$0);
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
      return this.location();
   }

   public double mode() {
      return this.location();
   }

   public double variance() {
      return (double)2 * this.scale() * this.scale();
   }

   public double entropy() {
      return (double)1 + package.log$.MODULE$.apply$mDDc$sp((double)2 * this.scale(), package$log$logDoubleImpl$.MODULE$);
   }

   public double logNormalizer() {
      return .MODULE$.log((double)2 * this.scale());
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      return -.MODULE$.abs(x - this.location()) / this.scale();
   }

   public double probability(final double x, final double y) {
      return this.cdf(y) - this.cdf(x);
   }

   public double cdf(final double x) {
      double var3;
      if (Double.NEGATIVE_INFINITY == x) {
         var3 = (double)0.0F;
      } else if (Double.POSITIVE_INFINITY == x) {
         var3 = (double)1.0F;
      } else if (x < this.location()) {
         var3 = (double)0.5F * package.exp$.MODULE$.apply$mDDc$sp(this.unnormalizedLogPdf(x), package$exp$expDoubleImpl$.MODULE$);
      } else {
         var3 = (double)1 - (double)0.5F * package.exp$.MODULE$.apply$mDDc$sp(this.unnormalizedLogPdf(x), package$exp$expDoubleImpl$.MODULE$);
      }

      return var3;
   }

   public Laplace copy(final double location, final double scale, final RandBasis rand) {
      return new Laplace(location, scale, rand);
   }

   public double copy$default$1() {
      return this.location();
   }

   public double copy$default$2() {
      return this.scale();
   }

   public String productPrefix() {
      return "Laplace";
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
      return x$1 instanceof Laplace;
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
            if (x$1 instanceof Laplace) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Laplace var4 = (Laplace)x$1;
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
      double u = this.breeze$stats$distributions$Laplace$$rand.uniform().draw$mcD$sp();
      return u < (double)0.5F ? this.location() + this.scale() * package.log$.MODULE$.apply$mDDc$sp((double)2 * u, package$log$logDoubleImpl$.MODULE$) : this.location() - this.scale() * package.log$.MODULE$.apply$mDDc$sp((double)2 * ((double)1 - u), package$log$logDoubleImpl$.MODULE$);
   }

   public Laplace(final double location, final double scale, final RandBasis rand) {
      this.location = location;
      this.scale = scale;
      this.breeze$stats$distributions$Laplace$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
   }
}
