package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$exp$expDoubleImpl$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.numerics.package$pow$powDoubleDoubleImpl$;
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
   bytes = "\u0006\u0005\u0005\u0005f\u0001B\u0012%\u0001.B\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t!\u0002\u0011\t\u0012)A\u0005m!A\u0011\u000b\u0001BK\u0002\u0013\u0005q\n\u0003\u0005S\u0001\tE\t\u0015!\u00037\u0011!\u0019\u0006A!A!\u0002\u0017!\u0006\"B,\u0001\t\u0003A\u0006\"\u00020\u0001\t\u0003y\u0005\"B0\u0001\t\u0003y\u0005\"\u00021\u0001\t\u0003y\u0005\"B1\u0001\t\u0003y\u0005\u0002\u00032\u0001\u0011\u000b\u0007I\u0011A(\t\u000b\r\u0004A\u0011\u00013\t\u000b\u0015\u0004A\u0011\u00014\t\u000b%\u0004A\u0011\u00016\t\u000b9\u0004A\u0011A8\t\u000fE\u0004\u0011\u0011!C\u0001e\"9q\u000fAI\u0001\n\u0003A\b\u0002CA\u0004\u0001E\u0005I\u0011\u0001=\t\u0013\u0005%\u0001!!A\u0005B\u0005-\u0001\"CA\u000f\u0001\u0005\u0005I\u0011AA\u0010\u0011%\t9\u0003AA\u0001\n\u0003\tI\u0003C\u0005\u00026\u0001\t\t\u0011\"\u0011\u00028!I\u0011Q\t\u0001\u0002\u0002\u0013\u0005\u0011q\t\u0005\n\u0003#\u0002\u0011\u0011!C!\u0003'B\u0011\"a\u0016\u0001\u0003\u0003%\t%!\u0017\t\u0013\u0005m\u0003!!A\u0005B\u0005u\u0003\"CA0\u0001\u0005\u0005I\u0011IA1\u000f%\t)\u0007JA\u0001\u0012\u0003\t9G\u0002\u0005$I\u0005\u0005\t\u0012AA5\u0011\u00199V\u0004\"\u0001\u0002v!I\u00111L\u000f\u0002\u0002\u0013\u0015\u0013Q\f\u0005\n\u0003oj\u0012\u0011!CA\u0003sB\u0011\"a!\u001e\u0003\u0003%\t)!\"\t\u0013\u0005]U$!A\u0005\n\u0005e%A\u0002)be\u0016$xN\u0003\u0002&M\u0005iA-[:ue&\u0014W\u000f^5p]NT!a\n\u0015\u0002\u000bM$\u0018\r^:\u000b\u0003%\naA\u0019:fKj,7\u0001A\n\b\u00011\u0012\u0014\bP C!\ti\u0003'D\u0001/\u0015\u0005y\u0013!B:dC2\f\u0017BA\u0019/\u0005\u0019\te.\u001f*fMB\u00191\u0007\u000e\u001c\u000e\u0003\u0011J!!\u000e\u0013\u0003\u001f\r{g\u000e^5ok>,8\u000fR5tiJ\u0004\"!L\u001c\n\u0005ar#A\u0002#pk\ndW\r\u0005\u00034uY2\u0014BA\u001e%\u0005\u001diu.\\3oiN\u0004\"aM\u001f\n\u0005y\"#A\u0002%bg\u000e#g\r\u0005\u0002.\u0001&\u0011\u0011I\f\u0002\b!J|G-^2u!\t\u00195J\u0004\u0002E\u0013:\u0011Q\tS\u0007\u0002\r*\u0011qIK\u0001\u0007yI|w\u000e\u001e \n\u0003=J!A\u0013\u0018\u0002\u000fA\f7m[1hK&\u0011A*\u0014\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0015:\nQa]2bY\u0016,\u0012AN\u0001\u0007g\u000e\fG.\u001a\u0011\u0002\u000bMD\u0017\r]3\u0002\rMD\u0017\r]3!\u0003\u0011\u0011\u0018M\u001c3\u0011\u0005M*\u0016B\u0001,%\u0005%\u0011\u0016M\u001c3CCNL7/\u0001\u0004=S:LGO\u0010\u000b\u00043rkFC\u0001.\\!\t\u0019\u0004\u0001C\u0003T\r\u0001\u000fA\u000bC\u0003O\r\u0001\u0007a\u0007C\u0003R\r\u0001\u0007a'\u0001\u0003nK\u0006t\u0017\u0001B7pI\u0016\f\u0001B^1sS\u0006t7-Z\u0001\bK:$(o\u001c9z\u00035awn\u001a(pe6\fG.\u001b>fe\u0006!AM]1x)\u00051\u0014AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$\"AN4\t\u000b!l\u0001\u0019\u0001\u001c\u0002\u0003a\f1\u0002\u001d:pE\u0006\u0014\u0017\u000e\\5usR\u0019ag\u001b7\t\u000b!t\u0001\u0019\u0001\u001c\t\u000b5t\u0001\u0019\u0001\u001c\u0002\u0003e\f1a\u00193g)\t1\u0004\u000fC\u0003i\u001f\u0001\u0007a'\u0001\u0003d_BLHcA:vmR\u0011!\f\u001e\u0005\u0006'B\u0001\u001d\u0001\u0016\u0005\b\u001dB\u0001\n\u00111\u00017\u0011\u001d\t\u0006\u0003%AA\u0002Y\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001zU\t1$pK\u0001|!\ra\u00181A\u0007\u0002{*\u0011ap`\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0001/\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u000bi(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002\u000eA!\u0011qBA\r\u001b\t\t\tB\u0003\u0003\u0002\u0014\u0005U\u0011\u0001\u00027b]\u001eT!!a\u0006\u0002\t)\fg/Y\u0005\u0005\u00037\t\tB\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003C\u00012!LA\u0012\u0013\r\t)C\f\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003W\t\t\u0004E\u0002.\u0003[I1!a\f/\u0005\r\te.\u001f\u0005\n\u0003g)\u0012\u0011!a\u0001\u0003C\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u001d!\u0019\tY$!\u0011\u0002,5\u0011\u0011Q\b\u0006\u0004\u0003\u007fq\u0013AC2pY2,7\r^5p]&!\u00111IA\u001f\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005%\u0013q\n\t\u0004[\u0005-\u0013bAA']\t9!i\\8mK\u0006t\u0007\"CA\u001a/\u0005\u0005\t\u0019AA\u0016\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u00055\u0011Q\u000b\u0005\n\u0003gA\u0012\u0011!a\u0001\u0003C\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003C\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003\u001b\ta!Z9vC2\u001cH\u0003BA%\u0003GB\u0011\"a\r\u001c\u0003\u0003\u0005\r!a\u000b\u0002\rA\u000b'/\u001a;p!\t\u0019Td\u0005\u0003\u001eY\u0005-\u0004\u0003BA7\u0003gj!!a\u001c\u000b\t\u0005E\u0014QC\u0001\u0003S>L1\u0001TA8)\t\t9'A\u0003baBd\u0017\u0010\u0006\u0004\u0002|\u0005}\u0014\u0011\u0011\u000b\u00045\u0006u\u0004\"B*!\u0001\b!\u0006\"\u0002(!\u0001\u00041\u0004\"B)!\u0001\u00041\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u000f\u000b\u0019\nE\u0003.\u0003\u0013\u000bi)C\u0002\u0002\f:\u0012aa\u00149uS>t\u0007#B\u0017\u0002\u0010Z2\u0014bAAI]\t1A+\u001e9mKJB\u0001\"!&\"\u0003\u0003\u0005\rAW\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAN!\u0011\ty!!(\n\t\u0005}\u0015\u0011\u0003\u0002\u0007\u001f\nTWm\u0019;"
)
public class Pareto implements ContinuousDistr, Moments, HasCdf, Product {
   private double logNormalizer;
   private final double scale;
   private final double shape;
   public final RandBasis breeze$stats$distributions$Pareto$$rand;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Pareto x$0) {
      return Pareto$.MODULE$.unapply(x$0);
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

   public double scale() {
      return this.scale;
   }

   public double shape() {
      return this.shape;
   }

   public double mean() {
      return this.shape() <= (double)1 ? Double.POSITIVE_INFINITY : this.scale() * this.shape() / (this.shape() - (double)1);
   }

   public double mode() {
      return this.scale();
   }

   public double variance() {
      if (this.shape() <= (double)1) {
         throw new IllegalArgumentException("undefined variance for shape < 1");
      } else {
         return this.shape() <= (double)2 ? Double.POSITIVE_INFINITY : package.pow$.MODULE$.apply$mDDDc$sp(this.scale() / (this.shape() - (double)1.0F), (double)2.0F, package$pow$powDoubleDoubleImpl$.MODULE$) * this.shape() / (this.shape() - (double)2.0F);
      }
   }

   public double entropy() {
      return package.log$.MODULE$.apply$mDDc$sp(this.scale() / this.shape(), package$log$logDoubleImpl$.MODULE$) + (double)1.0F / this.shape() + (double)1.0F;
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = -.MODULE$.log(this.shape()) - this.shape() * .MODULE$.log(this.scale());
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

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double unnormalizedLogPdf(final double x) {
      return -(this.shape() + (double)1) * package.log$.MODULE$.apply$mDDc$sp(x, package$log$logDoubleImpl$.MODULE$);
   }

   public double probability(final double x, final double y) {
      return this.cdf(y) - this.cdf(x);
   }

   public double cdf(final double x) {
      double var3;
      if (x < this.scale()) {
         var3 = (double)0.0F;
      } else if (Double.POSITIVE_INFINITY == x) {
         var3 = (double)1.0F;
      } else {
         var3 = (double)1 - .MODULE$.pow(this.scale() / x, this.shape());
      }

      return var3;
   }

   public Pareto copy(final double scale, final double shape, final RandBasis rand) {
      return new Pareto(scale, shape, rand);
   }

   public double copy$default$1() {
      return this.scale();
   }

   public double copy$default$2() {
      return this.shape();
   }

   public String productPrefix() {
      return "Pareto";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.scale());
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
      return x$1 instanceof Pareto;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "scale";
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
      var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
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
            if (x$1 instanceof Pareto) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Pareto var4 = (Pareto)x$1;
               if (this.scale() == var4.scale() && this.shape() == var4.shape() && var4.canEqual(this)) {
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
      Exponential e = new Exponential(this.shape(), this.breeze$stats$distributions$Pareto$$rand);
      return package.exp$.MODULE$.apply$mDDc$sp(e.draw$mcD$sp(), package$exp$expDoubleImpl$.MODULE$) * this.scale();
   }

   public Pareto(final double scale, final double shape, final RandBasis rand) {
      this.scale = scale;
      this.shape = shape;
      this.breeze$stats$distributions$Pareto$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
   }
}
