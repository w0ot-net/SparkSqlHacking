package breeze.stats.distributions;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$;
import breeze.linalg.NumericOps;
import breeze.linalg.cholesky;
import breeze.linalg.cholesky$;
import breeze.linalg.diag$;
import breeze.linalg.sum$;
import breeze.linalg.operators.HasOps$;
import breeze.numerics.package;
import breeze.numerics.package$log$logDoubleImpl$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uf\u0001\u0002\u0012$\u0001*B\u0001b\u0015\u0001\u0003\u0016\u0004%\t\u0001\u0016\u0005\t+\u0002\u0011\t\u0012)A\u0005k!Aa\u000b\u0001BK\u0002\u0013\u0005q\u000b\u0003\u0005Y\u0001\tE\t\u0015!\u0003B\u0011!I\u0006A!A!\u0002\u0017Q\u0006\"B/\u0001\t\u0003q\u0006\"\u00023\u0001\t\u0003)\u0007b\u00024\u0001\u0005\u0004%Ia\u0016\u0005\u0007O\u0002\u0001\u000b\u0011B!\t\u000b!\u0004A\u0011I5\t\u000bI\u0004A\u0011I:\t\u0011Y\u0004\u0001R1A\u0005B]DQ\u0001\u001f\u0001\u0005\u0002]CQ!\u001f\u0001\u0005\u0002QC\u0001B\u001f\u0001\t\u0006\u0004%\ta\u001e\u0005\bw\u0002\t\t\u0011\"\u0001}\u0011%\t\u0019\u0001AI\u0001\n\u0003\t)\u0001C\u0005\u0002\u001c\u0001\t\n\u0011\"\u0001\u0002\u001e!I\u0011\u0011\u0005\u0001\u0002\u0002\u0013\u0005\u00131\u0005\u0005\n\u0003g\u0001\u0011\u0011!C\u0001\u0003kA\u0011\"!\u0010\u0001\u0003\u0003%\t!a\u0010\t\u0013\u0005-\u0003!!A\u0005B\u00055\u0003\"CA.\u0001\u0005\u0005I\u0011AA/\u0011%\t9\u0007AA\u0001\n\u0003\nI\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0011\u0002p!I\u0011\u0011\u000f\u0001\u0002\u0002\u0013\u0005\u00131O\u0004\n\u0003o\u001a\u0013\u0011!E\u0001\u0003s2\u0001BI\u0012\u0002\u0002#\u0005\u00111\u0010\u0005\u0007;r!\t!a\"\t\u0011!d\u0012\u0011!C#\u0003\u0013C\u0011\"a#\u001d\u0003\u0003%\t)!$\t\u0013\u0005]E$!A\u0005\u0002\u0006e\u0005\"CAV9\u0005\u0005I\u0011BAW\u0005QiU\u000f\u001c;jm\u0006\u0014\u0018.\u0019;f\u000f\u0006,8o]5b]*\u0011A%J\u0001\u000eI&\u001cHO]5ckRLwN\\:\u000b\u0005\u0019:\u0013!B:uCR\u001c(\"\u0001\u0015\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019b\u0001A\u00162}\u0011;\u0005C\u0001\u00170\u001b\u0005i#\"\u0001\u0018\u0002\u000bM\u001c\u0017\r\\1\n\u0005Aj#AB!osJ+g\rE\u00023gUj\u0011aI\u0005\u0003i\r\u0012qbQ8oi&tWo\\;t\t&\u001cHO\u001d\t\u0004meZT\"A\u001c\u000b\u0005a:\u0013A\u00027j]\u0006dw-\u0003\u0002;o\tYA)\u001a8tKZ+7\r^8s!\taC(\u0003\u0002>[\t1Ai\\;cY\u0016\u0004BAM 6\u0003&\u0011\u0001i\t\u0002\b\u001b>lWM\u001c;t!\r1$iO\u0005\u0003\u0007^\u00121\u0002R3og\u0016l\u0015\r\u001e:jqB\u0011A&R\u0005\u0003\r6\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002I!:\u0011\u0011J\u0014\b\u0003\u00156k\u0011a\u0013\u0006\u0003\u0019&\na\u0001\u0010:p_Rt\u0014\"\u0001\u0018\n\u0005=k\u0013a\u00029bG.\fw-Z\u0005\u0003#J\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!aT\u0017\u0002\t5,\u0017M\\\u000b\u0002k\u0005)Q.Z1oA\u0005Q1m\u001c<be&\fgnY3\u0016\u0003\u0005\u000b1bY8wCJL\u0017M\\2fA\u0005!!/\u00198e!\t\u00114,\u0003\u0002]G\tI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007}\u00137\r\u0006\u0002aCB\u0011!\u0007\u0001\u0005\u00063\u001a\u0001\u001dA\u0017\u0005\u0006'\u001a\u0001\r!\u000e\u0005\u0006-\u001a\u0001\r!Q\u0001\u0005IJ\fw\u000fF\u00016\u0003\u0011\u0011xn\u001c;\u0002\u000bI|w\u000e\u001e\u0011\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A\u001b\t\u0003W>t!\u0001\\7\u0011\u0005)k\u0013B\u00018.\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001/\u001d\u0002\u0007'R\u0014\u0018N\\4\u000b\u00059l\u0013AE;o]>\u0014X.\u00197ju\u0016$Gj\\4QI\u001a$\"a\u000f;\t\u000bU\\\u0001\u0019A\u001b\u0002\u0003Q\fQ\u0002\\8h\u001d>\u0014X.\u00197ju\u0016\u0014X#A\u001e\u0002\u0011Y\f'/[1oG\u0016\fA!\\8eK\u00069QM\u001c;s_BL\u0018\u0001B2paf$B!`@\u0002\u0002Q\u0011\u0001M \u0005\u00063B\u0001\u001dA\u0017\u0005\b'B\u0001\n\u00111\u00016\u0011\u001d1\u0006\u0003%AA\u0002\u0005\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\b)\u001aQ'!\u0003,\u0005\u0005-\u0001\u0003BA\u0007\u0003/i!!a\u0004\u000b\t\u0005E\u00111C\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0006.\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00033\tyAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002 )\u001a\u0011)!\u0003\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t)\u0003\u0005\u0003\u0002(\u0005ERBAA\u0015\u0015\u0011\tY#!\f\u0002\t1\fgn\u001a\u0006\u0003\u0003_\tAA[1wC&\u0019\u0001/!\u000b\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005]\u0002c\u0001\u0017\u0002:%\u0019\u00111H\u0017\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0005\u0013q\t\t\u0004Y\u0005\r\u0013bAA#[\t\u0019\u0011I\\=\t\u0013\u0005%S#!AA\u0002\u0005]\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002PA1\u0011\u0011KA,\u0003\u0003j!!a\u0015\u000b\u0007\u0005US&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0017\u0002T\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty&!\u001a\u0011\u00071\n\t'C\u0002\u0002d5\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002J]\t\t\u00111\u0001\u0002B\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t)#a\u001b\t\u0013\u0005%\u0003$!AA\u0002\u0005]\u0012\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005]\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0002`\u0005U\u0004\"CA%5\u0005\u0005\t\u0019AA!\u0003QiU\u000f\u001c;jm\u0006\u0014\u0018.\u0019;f\u000f\u0006,8o]5b]B\u0011!\u0007H\n\u00059-\ni\b\u0005\u0003\u0002\u0000\u0005\u0015UBAAA\u0015\u0011\t\u0019)!\f\u0002\u0005%|\u0017bA)\u0002\u0002R\u0011\u0011\u0011\u0010\u000b\u0003\u0003K\tQ!\u00199qYf$b!a$\u0002\u0014\u0006UEc\u00011\u0002\u0012\")\u0011l\ba\u00025\")1k\ba\u0001k!)ak\ba\u0001\u0003\u00069QO\\1qa2LH\u0003BAN\u0003O\u0003R\u0001LAO\u0003CK1!a(.\u0005\u0019y\u0005\u000f^5p]B)A&a)6\u0003&\u0019\u0011QU\u0017\u0003\rQ+\b\u000f\\33\u0011!\tI\u000bIA\u0001\u0002\u0004\u0001\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\u0016\t\u0005\u0003O\t\t,\u0003\u0003\u00024\u0006%\"AB(cU\u0016\u001cG\u000f"
)
public class MultivariateGaussian implements ContinuousDistr, Moments, Product {
   private double logNormalizer;
   private double entropy;
   private final DenseVector mean;
   private final DenseMatrix covariance;
   private final RandBasis rand;
   private final DenseMatrix root;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final MultivariateGaussian x$0) {
      return MultivariateGaussian$.MODULE$.unapply(x$0);
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

   public double draw$mcD$sp() {
      return Rand.draw$mcD$sp$(this);
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
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.normalizer = ContinuousDistr.normalizer$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.normalizer;
   }

   public double normalizer() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.normalizer$lzycompute() : this.normalizer;
   }

   public DenseVector mean() {
      return this.mean;
   }

   public DenseMatrix covariance() {
      return this.covariance;
   }

   public DenseVector draw() {
      DenseVector z = (DenseVector)DenseVector$.MODULE$.rand(this.mean().length(), this.rand.gaussian((double)0.0F, (double)1.0F), .MODULE$.Double());
      return (DenseVector)((NumericOps)this.root().$times(z, HasOps$.MODULE$.impl_OpMulMatrix_DMD_DVD_eq_DVD())).$plus$eq(this.mean(), HasOps$.MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
   }

   private DenseMatrix root() {
      return this.root;
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public double unnormalizedLogPdf(final DenseVector t) {
      DenseVector centered = (DenseVector)t.$minus(this.mean(), HasOps$.MODULE$.impl_OpSub_DV_DV_eq_DV_Double());
      DenseVector slv = (DenseVector)this.covariance().$bslash(centered, HasOps$.MODULE$.impl_OpSolveMatrixBy_DMD_DVD_eq_DVD());
      return -BoxesRunTime.unboxToDouble(slv.dot(centered, HasOps$.MODULE$.canDotD())) / (double)2.0F;
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            double det = BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(package.log$.MODULE$.apply(diag$.MODULE$.apply(this.root(), diag$.MODULE$.diagDMDVImpl()), HasOps$.MODULE$.fromLowOrderCanMapValues(DenseVector$.MODULE$.DV_scalarOf(), package$log$logDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()))), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())));
            this.logNormalizer = (double)this.mean().length() / (double)2.0F * package.log$.MODULE$.apply$mDDc$sp((Math.PI * 2D), package$log$logDoubleImpl$.MODULE$) + det;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var5) {
         throw var5;
      }

      return this.logNormalizer;
   }

   public double logNormalizer() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.logNormalizer$lzycompute() : this.logNormalizer;
   }

   public DenseMatrix variance() {
      return this.covariance();
   }

   public DenseVector mode() {
      return this.mean();
   }

   private double entropy$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.entropy = (double)this.mean().length() * scala.math.package..MODULE$.log1p((Math.PI * 2D)) + BoxesRunTime.unboxToDouble(sum$.MODULE$.apply(package.log$.MODULE$.apply(diag$.MODULE$.apply(this.root(), diag$.MODULE$.diagDMDVImpl()), HasOps$.MODULE$.fromLowOrderCanMapValues(DenseVector$.MODULE$.DV_scalarOf(), package$log$logDoubleImpl$.MODULE$, DenseVector$.MODULE$.DV_canMapValues$mDDc$sp(.MODULE$.Double()))), sum$.MODULE$.reduce_Double(HasOps$.MODULE$.DV_canIterateValues())));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.entropy;
   }

   public double entropy() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.entropy$lzycompute() : this.entropy;
   }

   public MultivariateGaussian copy(final DenseVector mean, final DenseMatrix covariance, final RandBasis rand) {
      return new MultivariateGaussian(mean, covariance, rand);
   }

   public DenseVector copy$default$1() {
      return this.mean();
   }

   public DenseMatrix copy$default$2() {
      return this.covariance();
   }

   public String productPrefix() {
      return "MultivariateGaussian";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.mean();
            break;
         case 1:
            var10000 = this.covariance();
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
      return x$1 instanceof MultivariateGaussian;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mean";
            break;
         case 1:
            var10000 = "covariance";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof MultivariateGaussian) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     MultivariateGaussian var4 = (MultivariateGaussian)x$1;
                     DenseVector var10000 = this.mean();
                     DenseVector var5 = var4.mean();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     DenseMatrix var7 = this.covariance();
                     DenseMatrix var6 = var4.covariance();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public MultivariateGaussian(final DenseVector mean, final DenseMatrix covariance, final RandBasis rand) {
      this.mean = mean;
      this.covariance = covariance;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      this.root = (DenseMatrix)cholesky$.MODULE$.apply(covariance, cholesky.ImplCholesky_DM$.MODULE$);
   }
}
