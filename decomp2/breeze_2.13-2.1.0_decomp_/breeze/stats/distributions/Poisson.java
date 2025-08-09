package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$exp$expDoubleImpl$;
import breeze.numerics.package$gammp$gammpImplDoubleDouble$;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015d\u0001\u0002!B\u0001\"C\u0001b\u001b\u0001\u0003\u0016\u0004%\t\u0001\u001c\u0005\t[\u0002\u0011\t\u0012)A\u00053\"Aa\u000e\u0001B\u0001B\u0003-q\u000eC\u0003s\u0001\u0011\u00051\u000fC\u0004y\u0001\t\u0007I\u0011\u00027\t\re\u0004\u0001\u0015!\u0003Z\u0011\u0015Q\b\u0001\"\u0011|\u0011\u001d\tI\u0001\u0001C\u0001\u0003\u0017Aq!!\u0004\u0001\t\u0003\ty\u0001C\u0004\u0002\u0016\u0001!\t%a\u0006\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e!1\u0011\u0011\u0005\u0001\u0005\u00021Da!a\t\u0001\t\u0003a\u0007BBA\u0013\u0001\u0011\u0005A\u000eC\u0005\u0002(\u0001\t\t\u0011\"\u0001\u0002*!I\u0011\u0011\u0007\u0001\u0012\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003\u0013\u0002\u0011\u0011!C!\u0003\u0017B\u0011\"!\u0014\u0001\u0003\u0003%\t!a\u0014\t\u0013\u0005E\u0003!!A\u0005\u0002\u0005M\u0003\"CA0\u0001\u0005\u0005I\u0011IA1\u0011%\ty\u0007AA\u0001\n\u0003\t\t\bC\u0005\u0002|\u0001\t\t\u0011\"\u0011\u0002~!I\u0011\u0011\u0011\u0001\u0002\u0002\u0013\u0005\u00131\u0002\u0005\n\u0003\u0007\u0003\u0011\u0011!C!\u0003\u000b;q!!#B\u0011\u0003\tYI\u0002\u0004A\u0003\"\u0005\u0011Q\u0012\u0005\u0007ej!\t!a(\u0006\u000b\u0005\u0005&\u0004A-\u0007\r\u0005\r&\u0004QAS\u0011%\ty+\bBK\u0002\u0013\u0005A\u000eC\u0005\u00022v\u0011\t\u0012)A\u00053\"I\u00111W\u000f\u0003\u0016\u0004%\t\u0001\u001c\u0005\n\u0003kk\"\u0011#Q\u0001\neCaA]\u000f\u0005\u0002\u0005]\u0006bBA_;\u0011\u0005\u0011q\u0018\u0005\b\u0003\u000blB\u0011AAd\u0011%\t9#HA\u0001\n\u0003\ti\rC\u0005\u00022u\t\n\u0011\"\u0001\u00024!I\u00111[\u000f\u0012\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003\u0013j\u0012\u0011!C!\u0003\u0017B\u0011\"!\u0014\u001e\u0003\u0003%\t!a\u0014\t\u0013\u0005ES$!A\u0005\u0002\u0005U\u0007\"CA0;\u0005\u0005I\u0011IA1\u0011%\ty'HA\u0001\n\u0003\tI\u000eC\u0005\u0002|u\t\t\u0011\"\u0011\u0002^\"I\u0011\u0011Q\u000f\u0002\u0002\u0013\u0005\u00131\u0002\u0005\buv\t\t\u0011\"\u0011|\u0011%\t\u0019)HA\u0001\n\u0003\n\toB\u0005\u0002fj\t\t\u0011#\u0001\u0002h\u001aI\u00111\u0015\u000e\u0002\u0002#\u0005\u0011\u0011\u001e\u0005\u0007eJ\"\t!a>\t\u000fi\u0014\u0014\u0011!C#w\"I\u0011\u0011 \u001a\u0002\u0002\u0013\u0005\u00151 \u0005\n\u0005\u0003\u0011\u0014\u0011!CA\u0005\u0007A\u0011B!\u00063\u0003\u0003%IAa\u0006\t\u000f\t}!\u0004\"\u0001\u0003\"!9!1\u0005\u000e\u0005\u0002\t\u0015\u0002b\u0002B\u00155\u0011\u0005!1\u0006\u0005\b\u0005_QB\u0011\u0001B\u0019\u0011\u001d\u00119E\u0007C!\u0005\u0013B\u0011\"!?\u001b\u0003\u0003%\tIa\u0016\t\u0013\t\u0005!$!A\u0005\u0002\n}\u0003\"\u0003B\u000b5\u0005\u0005I\u0011\u0002B\f\u0005\u001d\u0001v.[:t_:T!AQ\"\u0002\u001b\u0011L7\u000f\u001e:jEV$\u0018n\u001c8t\u0015\t!U)A\u0003ti\u0006$8OC\u0001G\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0002\u0001J\u001fZcv\f\u0005\u0002K\u001b6\t1JC\u0001M\u0003\u0015\u00198-\u00197b\u0013\tq5J\u0001\u0004B]f\u0014VM\u001a\t\u0004!F\u001bV\"A!\n\u0005I\u000b%!\u0004#jg\u000e\u0014X\r^3ESN$(\u000f\u0005\u0002K)&\u0011Qk\u0013\u0002\u0004\u0013:$\b\u0003\u0002)X3fK!\u0001W!\u0003\u000f5{W.\u001a8ugB\u0011!JW\u0005\u00037.\u0013a\u0001R8vE2,\u0007C\u0001&^\u0013\tq6JA\u0004Qe>$Wo\u0019;\u0011\u0005\u0001DgBA1g\u001d\t\u0011W-D\u0001d\u0015\t!w)\u0001\u0004=e>|GOP\u0005\u0002\u0019&\u0011qmS\u0001\ba\u0006\u001c7.Y4f\u0013\tI'N\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002h\u0017\u0006!Q.Z1o+\u0005I\u0016!B7fC:\u0004\u0013\u0001\u0002:b]\u0012\u0004\"\u0001\u00159\n\u0005E\f%!\u0003*b]\u0012\u0014\u0015m]5t\u0003\u0019a\u0014N\\5u}Q\u0011Ao\u001e\u000b\u0003kZ\u0004\"\u0001\u0015\u0001\t\u000b9$\u00019A8\t\u000b-$\u0001\u0019A-\u0002\u0007\u0015dG.\u0001\u0003fY2\u0004\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003q\u00042!`A\u0003\u001b\u0005q(bA@\u0002\u0002\u0005!A.\u00198h\u0015\t\t\u0019!\u0001\u0003kCZ\f\u0017bAA\u0004}\n11\u000b\u001e:j]\u001e\fA\u0001\u001a:boR\t1+A\u0007qe>\u0014\u0017MY5mSRLxJ\u001a\u000b\u00043\u0006E\u0001BBA\n\u0013\u0001\u00071+A\u0001l\u0003Aawn\u001a)s_\n\f'-\u001b7jif|e\rF\u0002Z\u00033Aa!a\u0005\u000b\u0001\u0004\u0019\u0016aA2eMR\u0019\u0011,a\b\t\r\u0005M1\u00021\u0001T\u0003!1\u0018M]5b]\u000e,\u0017\u0001B7pI\u0016\fq!\u001a8ue>\u0004\u00180\u0001\u0003d_BLH\u0003BA\u0016\u0003_!2!^A\u0017\u0011\u0015qw\u0002q\u0001p\u0011\u001dYw\u0002%AA\u0002e\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u00026)\u001a\u0011,a\u000e,\u0005\u0005e\u0002\u0003BA\u001e\u0003\u000bj!!!\u0010\u000b\t\u0005}\u0012\u0011I\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u0011L\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000f\niDA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u0001?\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003M\u000ba\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002V\u0005m\u0003c\u0001&\u0002X%\u0019\u0011\u0011L&\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002^M\t\t\u00111\u0001T\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\r\t\u0007\u0003K\nY'!\u0016\u000e\u0005\u0005\u001d$bAA5\u0017\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u00055\u0014q\r\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002t\u0005e\u0004c\u0001&\u0002v%\u0019\u0011qO&\u0003\u000f\t{w\u000e\\3b]\"I\u0011QL\u000b\u0002\u0002\u0003\u0007\u0011QK\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002}\u0003\u007fB\u0001\"!\u0018\u0017\u0003\u0003\u0005\raU\u0001\tQ\u0006\u001c\bnQ8eK\u00061Q-];bYN$B!a\u001d\u0002\b\"I\u0011Q\f\r\u0002\u0002\u0003\u0007\u0011QK\u0001\b!>L7o]8o!\t\u0001&d\u0005\u0004\u001b\u0013\u0006=\u0015Q\u0013\t\u0006!\u0006EUoU\u0005\u0004\u0003'\u000b%!E#ya>tWM\u001c;jC24\u0015-\\5msB!\u0011qSAO\u001b\t\tIJ\u0003\u0003\u0002\u001c\u0006\u0005\u0011AA5p\u0013\rI\u0017\u0011\u0014\u000b\u0003\u0003\u0017\u0013\u0011\u0002U1sC6,G/\u001a:\u0003'M+hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2\u0014\ruI\u0015q\u0015/`!\u0015\u0001\u0016\u0011VAV\u0013\r\t\u0019+\u0011\t\u0004\u0003[kR\"\u0001\u000e\u0002\u0007M,X.\u0001\u0003tk6\u0004\u0013!\u00018\u0002\u00059\u0004CCBAV\u0003s\u000bY\f\u0003\u0004\u00020\n\u0002\r!\u0017\u0005\u0007\u0003g\u0013\u0003\u0019A-\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005-\u0016\u0011\u0019\u0005\b\u0003\u0007\u001c\u0003\u0019AAV\u0003\u0005!\u0018A\u0002\u0013uS6,7\u000f\u0006\u0003\u0002,\u0006%\u0007BBAfI\u0001\u0007\u0011,\u0001\u0004xK&<\u0007\u000e\u001e\u000b\u0007\u0003W\u000by-!5\t\u0011\u0005=V\u0005%AA\u0002eC\u0001\"a-&!\u0003\u0005\r!W\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133)\u0011\t)&a6\t\u0011\u0005u#&!AA\u0002M#B!a\u001d\u0002\\\"I\u0011Q\f\u0017\u0002\u0002\u0003\u0007\u0011Q\u000b\u000b\u0004y\u0006}\u0007\u0002CA/[\u0005\u0005\t\u0019A*\u0015\t\u0005M\u00141\u001d\u0005\n\u0003;\u0002\u0014\u0011!a\u0001\u0003+\n1cU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e\u00042!!,3'\u0015\u0011\u00141^AK!!\ti/a=Z3\u0006-VBAAx\u0015\r\t\tpS\u0001\beVtG/[7f\u0013\u0011\t)0a<\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0002h\u0006)\u0011\r\u001d9msR1\u00111VA\u007f\u0003\u007fDa!a,6\u0001\u0004I\u0006BBAZk\u0001\u0007\u0011,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t\u0015!\u0011\u0003\t\u0006\u0015\n\u001d!1B\u0005\u0004\u0005\u0013Y%AB(qi&|g\u000eE\u0003K\u0005\u001bI\u0016,C\u0002\u0003\u0010-\u0013a\u0001V;qY\u0016\u0014\u0004\"\u0003B\nm\u0005\u0005\t\u0019AAV\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u00053\u00012! B\u000e\u0013\r\u0011iB \u0002\u0007\u001f\nTWm\u0019;\u00021\u0015l\u0007\u000f^=Tk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7-\u0006\u0002\u0002,\u000612/\u001e4gS\u000eLWM\u001c;Ti\u0006$\u0018n\u001d;jG\u001a{'\u000f\u0006\u0003\u0002,\n\u001d\u0002BBAbs\u0001\u00071+A\u0002nY\u0016$2!\u0017B\u0017\u0011\u0019!%\b1\u0001\u0002,\u0006\u0011B.[6fY&Dwn\u001c3Gk:\u001cG/[8o)\u0011\u0011\u0019D!\u0012\u0013\u000b\tU\u0012J!\u000f\u0007\r\t]2\b\u0001B\u001a\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0015\u0011YD!\u0011Z\u001b\t\u0011iDC\u0002\u0003@\u0015\u000b\u0001b\u001c9uS6L'0Z\u0005\u0005\u0005\u0007\u0012iD\u0001\u0007ES\u001a4g)\u001e8di&|g\u000e\u0003\u0004Ew\u0001\u0007\u00111V\u0001\rI&\u001cHO]5ckRLwN\u001c\u000b\u0005\u0005\u0017\u0012y\u0005F\u0002v\u0005\u001bBQA\u001c\u001fA\u0004=DqA!\u0015=\u0001\u0004\u0011\u0019&A\u0001q!\r\u0011)\u0006\b\b\u0003!f!BA!\u0017\u0003^Q\u0019QOa\u0017\t\u000b9l\u00049A8\t\u000b-l\u0004\u0019A-\u0015\t\t\u0005$1\r\t\u0005\u0015\n\u001d\u0011\f\u0003\u0005\u0003\u0014y\n\t\u00111\u0001v\u0001"
)
public class Poisson implements DiscreteDistr, Moments, Product {
   private final double mean;
   public final RandBasis breeze$stats$distributions$Poisson$$rand;
   private final double breeze$stats$distributions$Poisson$$ell;

   public static Option unapply(final Poisson x$0) {
      return Poisson$.MODULE$.unapply(x$0);
   }

   public static Poisson distribution(final double p, final RandBasis rand) {
      return Poisson$.MODULE$.distribution(p, rand);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Poisson$.MODULE$.likelihoodFunction(stats);
   }

   public static double mle(final SufficientStatistic stats) {
      return Poisson$.MODULE$.mle(stats);
   }

   public static SufficientStatistic sufficientStatisticFor(final int t) {
      return Poisson$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Poisson$.MODULE$.emptySufficientStatistic();
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

   public double mean() {
      return this.mean;
   }

   public double breeze$stats$distributions$Poisson$$ell() {
      return this.breeze$stats$distributions$Poisson$$ell;
   }

   public String toString() {
      return (new StringBuilder(9)).append("Poisson(").append(this.mean()).append(")").toString();
   }

   public int draw() {
      return this.draw$mcI$sp();
   }

   public double probabilityOf(final int k) {
      return .MODULE$.exp(this.logProbabilityOf(k));
   }

   public double logProbabilityOf(final int k) {
      return -this.mean() + (double)k * package.log$.MODULE$.apply$mDDc$sp(this.mean(), package$log$logDoubleImpl$.MODULE$) - package.lgamma$.MODULE$.apply$mDDc$sp((double)k + (double)1.0F, package$lgamma$lgammaImplDouble$.MODULE$);
   }

   public double cdf(final int k) {
      return (double)1 - package.gammp$.MODULE$.apply$mDDDc$sp((double)k + (double)1.0F, this.mean(), package$gammp$gammpImplDoubleDouble$.MODULE$);
   }

   public double variance() {
      return this.mean();
   }

   public double mode() {
      return .MODULE$.ceil(this.mean()) - (double)1;
   }

   public double entropy() {
      double entr = this.mean() * ((double)1 - package.log$.MODULE$.apply$mDDc$sp(this.mean(), package$log$logDoubleImpl$.MODULE$));
      double extra = (double)0.0F;
      double correction = (double)10000.0F;
      int k = 0;

      for(double meanmean = (double)1.0F / this.mean(); correction > 1.0E-6; ++k) {
         meanmean *= this.mean();
         double ln_k_$bang = package.lgamma$.MODULE$.apply$mDDc$sp((double)k + (double)1, package$lgamma$lgammaImplDouble$.MODULE$);
         correction = meanmean * ln_k_$bang / package.exp$.MODULE$.apply$mDDc$sp(ln_k_$bang, package$exp$expDoubleImpl$.MODULE$);
         extra += correction;
      }

      return entr + package.exp$.MODULE$.apply$mDDc$sp(-this.mean(), package$exp$expDoubleImpl$.MODULE$) * extra;
   }

   public Poisson copy(final double mean, final RandBasis rand) {
      return new Poisson(mean, rand);
   }

   public double copy$default$1() {
      return this.mean();
   }

   public String productPrefix() {
      return "Poisson";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.mean());
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
      return x$1 instanceof Poisson;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mean";
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
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Poisson) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Poisson var4 = (Poisson)x$1;
               if (this.mean() == var4.mean() && var4.canEqual(this)) {
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
      int var10000;
      if (this.mean() == (double)0) {
         var10000 = 0;
      } else if (this.mean() < (double)10.0F) {
         double t = this.breeze$stats$distributions$Poisson$$ell();
         int k = 0;
         double u = this.breeze$stats$distributions$Poisson$$rand.uniform().draw$mcD$sp();

         for(double s = t; s < u; s += t) {
            ++k;
            t *= this.mean() / (double)k;
         }

         var10000 = k;
      } else {
         int k_start = (int)this.mean();
         double u = this.breeze$stats$distributions$Poisson$$rand.uniform().draw$mcD$sp();
         double t1 = package.exp$.MODULE$.apply$mDDc$sp((double)k_start * package.log$.MODULE$.apply$mDDc$sp(this.mean(), package$log$logDoubleImpl$.MODULE$) - this.mean() - package.lgamma$.MODULE$.apply$mDDc$sp((double)k_start + (double)1, package$lgamma$lgammaImplDouble$.MODULE$), package$exp$expDoubleImpl$.MODULE$);
         if (!(t1 > u)) {
            int k1 = k_start;
            int k2 = k_start;
            double t2 = t1;
            double s = t1;

            while(true) {
               ++k1;
               t1 *= this.mean() / (double)k1;
               s += t1;
               if (s > u) {
                  return k1;
               }

               if (k2 > 0) {
                  t2 *= (double)k2 / this.mean();
                  --k2;
                  s += t2;
                  if (s > u) {
                     return k2;
                  }
               }
            }
         }

         var10000 = k_start;
      }

      return var10000;
   }

   public Poisson(final double mean, final RandBasis rand) {
      this.mean = mean;
      this.breeze$stats$distributions$Poisson$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(mean >= (double)0, () -> (new StringBuilder(43)).append("Poisson mean must be non-negative, but got ").append(this.mean()).toString());
      scala.Predef..MODULE$.require(!Double.isInfinite(mean), () -> (new StringBuilder(37)).append("Poisson mean must be finite, but got ").append(this.mean()).toString());
      this.breeze$stats$distributions$Poisson$$ell = .MODULE$.exp(-mean);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double sum;
      private final double n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double sum() {
         return this.sum;
      }

      public double n() {
         return this.n;
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         return new SufficientStatistic(t.sum() + this.sum(), t.n() + this.n());
      }

      public SufficientStatistic $times(final double weight) {
         return new SufficientStatistic(this.sum() * weight, this.n() * weight);
      }

      public SufficientStatistic copy(final double sum, final double n) {
         return new SufficientStatistic(sum, n);
      }

      public double copy$default$1() {
         return this.sum();
      }

      public double copy$default$2() {
         return this.n();
      }

      public String productPrefix() {
         return "SufficientStatistic";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.sum());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.n());
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
         return x$1 instanceof SufficientStatistic;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "sum";
               break;
            case 1:
               var10000 = "n";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.sum()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.n()));
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
               if (x$1 instanceof SufficientStatistic) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SufficientStatistic var4 = (SufficientStatistic)x$1;
                  if (this.sum() == var4.sum() && this.n() == var4.n() && var4.canEqual(this)) {
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

      public SufficientStatistic(final double sum, final double n) {
         this.sum = sum;
         this.n = n;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction2 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double sum, final double n) {
         return new SufficientStatistic(sum, n);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.sum(), x$0.n())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
