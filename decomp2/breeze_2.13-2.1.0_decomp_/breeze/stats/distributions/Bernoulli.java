package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$I$iBoolImpl$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
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
   bytes = "\u0006\u0005\tmf\u0001\u0002\"D\u0001*C\u0001\"\u001c\u0001\u0003\u0016\u0004%\tA\u001c\u0005\t_\u0002\u0011\t\u0012)A\u00057\"A\u0001\u000f\u0001B\u0001B\u0003-\u0011\u000fC\u0003u\u0001\u0011\u0005Q\u000fC\u0003{\u0001\u0011\u00051\u0010C\u0003\u007f\u0001\u0011\u0005s\u0010C\u0004\u0002\u0002\u0001!\t%a\u0001\t\r\u0005U\u0001\u0001\"\u0001o\u0011\u0019\t9\u0002\u0001C\u0001]\"1\u0011\u0011\u0004\u0001\u0005\u00029Da!a\u0007\u0001\t\u0003q\u0007\"CA\u000f\u0001\u0005\u0005I\u0011AA\u0010\u0011%\t9\u0003AI\u0001\n\u0003\tI\u0003C\u0005\u0002@\u0001\t\t\u0011\"\u0011\u0002B!I\u00111\t\u0001\u0002\u0002\u0013\u0005\u0011Q\t\u0005\n\u0003\u001b\u0002\u0011\u0011!C\u0001\u0003\u001fB\u0011\"a\u0017\u0001\u0003\u0003%\t%!\u0018\t\u0013\u0005-\u0004!!A\u0005\u0002\u00055\u0004\"CA9\u0001\u0005\u0005I\u0011IA:\u0011%\t9\bAA\u0001\n\u0003\nI\bC\u0005\u0002|\u0001\t\t\u0011\"\u0011\u0002~\u001d9\u0011\u0011Q\"\t\u0002\u0005\reA\u0002\"D\u0011\u0003\t)\t\u0003\u0004u/\u0011\u0005\u0011QT\u0003\u0007\u0003?;\u0002!!)\t\u0013\u0005\u001dvC1A\u0005\u0002\u0005%\u0006\u0002CAY/\u0001\u0006I!a+\t\u000f\u0005Mv\u0003\"\u0011\u00026\"9\u00111\\\f\u0005B\u0005uW!BAl/\u0001YfABA\u0000/\u0001\u0013\t\u0001C\u0005\u0003\f}\u0011)\u001a!C\u0001]\"I!QB\u0010\u0003\u0012\u0003\u0006Ia\u0017\u0005\n\u0005\u001fy\"Q3A\u0005\u00029D\u0011B!\u0005 \u0005#\u0005\u000b\u0011B.\t\rQ|B\u0011\u0001B\n\u0011\u001d\u0011Ib\bC\u0001\u00057AqA!\t \t\u0003\u0011\u0019\u0003C\u0005\u0002\u001e}\t\t\u0011\"\u0001\u0003*!I\u0011qE\u0010\u0012\u0002\u0013\u0005\u0011\u0011\u0006\u0005\n\u0005_y\u0012\u0013!C\u0001\u0003SA\u0011\"a\u0010 \u0003\u0003%\t%!\u0011\t\u0013\u0005\rs$!A\u0005\u0002\u0005\u0015\u0003\"CA'?\u0005\u0005I\u0011\u0001B\u0019\u0011%\tYfHA\u0001\n\u0003\ni\u0006C\u0005\u0002l}\t\t\u0011\"\u0001\u00036!I\u0011\u0011O\u0010\u0002\u0002\u0013\u0005#\u0011\b\u0005\n\u0003oz\u0012\u0011!C!\u0003sB\u0011\"!\u0001 \u0003\u0003%\t%a\u0001\t\u0013\u0005mt$!A\u0005B\tur!\u0003B!/\u0005\u0005\t\u0012\u0001B\"\r%\typFA\u0001\u0012\u0003\u0011)\u0005\u0003\u0004ui\u0011\u0005!1\u000b\u0005\n\u0003\u0003!\u0014\u0011!C#\u0003\u0007A\u0011B!\u00165\u0003\u0003%\tIa\u0016\t\u0013\tuC'!A\u0005\u0002\n}\u0003\"\u0003B9i\u0005\u0005I\u0011\u0002B:\u0011\u001d\u0011Yh\u0006C\u0001\u0005{BqAa \u0018\t\u0003\u0011\t\tC\u0004\u0003\u0006^!\tAa\"\t\u000f\t-u\u0003\"\u0011\u0003\u000e\"9!QS\f\u0005\u0002\t]\u0005\"\u0003B+/\u0005\u0005I\u0011\u0011BW\u0011%\u0011ifFA\u0001\n\u0003\u0013)\fC\u0005\u0003r]\t\t\u0011\"\u0003\u0003t\tI!)\u001a:o_VdG.\u001b\u0006\u0003\t\u0016\u000bQ\u0002Z5tiJL'-\u001e;j_:\u001c(B\u0001$H\u0003\u0015\u0019H/\u0019;t\u0015\u0005A\u0015A\u00022sK\u0016TXm\u0001\u0001\u0014\r\u0001Y\u0015\u000b\u00170b!\tau*D\u0001N\u0015\u0005q\u0015!B:dC2\f\u0017B\u0001)N\u0005\u0019\te.\u001f*fMB\u0019!kU+\u000e\u0003\rK!\u0001V\"\u0003\u001b\u0011K7o\u0019:fi\u0016$\u0015n\u001d;s!\tae+\u0003\u0002X\u001b\n9!i\\8mK\u0006t\u0007\u0003\u0002*Z7nK!AW\"\u0003\u000f5{W.\u001a8ugB\u0011A\nX\u0005\u0003;6\u0013a\u0001R8vE2,\u0007C\u0001'`\u0013\t\u0001WJA\u0004Qe>$Wo\u0019;\u0011\u0005\tTgBA2i\u001d\t!w-D\u0001f\u0015\t1\u0017*\u0001\u0004=e>|GOP\u0005\u0002\u001d&\u0011\u0011.T\u0001\ba\u0006\u001c7.Y4f\u0013\tYGN\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002j\u001b\u0006\t\u0001/F\u0001\\\u0003\t\u0001\b%\u0001\u0003sC:$\u0007C\u0001*s\u0013\t\u00198IA\u0005SC:$')Y:jg\u00061A(\u001b8jiz\"\"A^=\u0015\u0005]D\bC\u0001*\u0001\u0011\u0015\u0001H\u0001q\u0001r\u0011\u0015iG\u00011\u0001\\\u00035\u0001(o\u001c2bE&d\u0017\u000e^=PMR\u00111\f \u0005\u0006{\u0016\u0001\r!V\u0001\u0002E\u0006!AM]1x)\u0005)\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u0015\u0001\u0003BA\u0004\u0003#i!!!\u0003\u000b\t\u0005-\u0011QB\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u0010\u0005!!.\u0019<b\u0013\u0011\t\u0019\"!\u0003\u0003\rM#(/\u001b8h\u0003\u0011iW-\u00198\u0002\u0011Y\f'/[1oG\u0016\fA!\\8eK\u00069QM\u001c;s_BL\u0018\u0001B2paf$B!!\t\u0002&Q\u0019q/a\t\t\u000bAd\u00019A9\t\u000f5d\u0001\u0013!a\u00017\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0016U\rY\u0016QF\u0016\u0003\u0003_\u0001B!!\r\u0002<5\u0011\u00111\u0007\u0006\u0005\u0003k\t9$A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011H'\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002>\u0005M\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0002\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0003c\u0001'\u0002J%\u0019\u00111J'\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0013q\u000b\t\u0004\u0019\u0006M\u0013bAA+\u001b\n\u0019\u0011I\\=\t\u0013\u0005e\u0003#!AA\u0002\u0005\u001d\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002`A1\u0011\u0011MA4\u0003#j!!a\u0019\u000b\u0007\u0005\u0015T*\u0001\u0006d_2dWm\u0019;j_:LA!!\u001b\u0002d\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r)\u0016q\u000e\u0005\n\u00033\u0012\u0012\u0011!a\u0001\u0003#\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QAA;\u0011%\tIfEA\u0001\u0002\u0004\t9%\u0001\u0005iCND7i\u001c3f)\t\t9%\u0001\u0004fcV\fGn\u001d\u000b\u0004+\u0006}\u0004\"CA-+\u0005\u0005\t\u0019AA)\u0003%\u0011UM\u001d8pk2d\u0017\u000e\u0005\u0002S/MAqcSAD\u0003\u001b\u000b\u0019\nE\u0003S\u0003\u0013;X+C\u0002\u0002\f\u000e\u0013\u0011#\u0012=q_:,g\u000e^5bY\u001a\u000bW.\u001b7z!\u0015\u0011\u0016qR<V\u0013\r\t\tj\u0011\u0002\u0012\u0011\u0006\u001c8i\u001c8kk\u001e\fG/\u001a)sS>\u0014\b\u0003BAK\u00037k!!a&\u000b\t\u0005e\u0015QB\u0001\u0003S>L1a[AL)\t\t\u0019I\u0001\bD_:TWoZ1uKB\u0013\u0018n\u001c:\u0011\u0007I\u000b\u0019+C\u0002\u0002&\u000e\u0013AAQ3uC\u0006y1m\u001c8kk\u001e\fG/\u001a$b[&d\u00170\u0006\u0002\u0002,:\u0019!+!,\n\u0007\u0005=6)\u0001\u0003CKR\f\u0017\u0001E2p]*,x-\u0019;f\r\u0006l\u0017\u000e\\=!\u0003)\u0001(/\u001a3jGRLg/\u001a\u000b\u0005\u0003o\u000by\r\u0006\u0003\u0002:\u0006-\u0007C\u0002*\u0002<\u0006}V+C\u0002\u0002>\u000e\u0013Q\u0001U8ms\u0006\u0004b!!1\u0002HV[VBAAb\u0015\r\t)mR\u0001\u0007Y&t\u0017\r\\4\n\t\u0005%\u00171\u0019\u0002\b\u0007>,h\u000e^3s\u0011\u0019\ti\r\ba\u0002c\u0006)!-Y:jg\"9\u0011\u0011\u001b\u000fA\u0002\u0005M\u0017!\u00039be\u0006lW\r^3s!\u0011\tY+!6\n\t\u0005]\u0017\u0011\u001c\u0002\n!\u0006\u0014\u0018-\\3uKJT1!a,D\u0003%\u0001xn\u001d;fe&|'\u000f\u0006\u0004\u0002T\u0006}\u00171\u001d\u0005\b\u0003Cl\u0002\u0019AAj\u0003\u0015\u0001(/[8s\u0011\u001d\t)/\ba\u0001\u0003O\f\u0001\"\u001a<jI\u0016t7-\u001a\t\u0006\u0003S\fI0\u0016\b\u0005\u0003W\f)P\u0004\u0003\u0002n\u0006EhbA2\u0002p&\u0019\u0011QM'\n\t\u0005M\u00181M\u0001\u0007G>l\u0007/\u0019;\n\u0007%\f9P\u0003\u0003\u0002t\u0006\r\u0014\u0002BA~\u0003{\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016T1![A|\u0005M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d'\u0019y2Ja\u0001_CB)!K!\u0002\u0003\b%\u0019\u0011q`\"\u0011\u0007\t%q$D\u0001\u0018\u0003\u0019qW/\\-fg\u00069a.^7ZKN\u0004\u0013!\u00018\u0002\u00059\u0004CC\u0002B\u0004\u0005+\u00119\u0002\u0003\u0004\u0003\f\u0011\u0002\ra\u0017\u0005\u0007\u0005\u001f!\u0003\u0019A.\u0002\r\u0011\"\u0018.\\3t)\u0011\u00119A!\b\t\r\t}Q\u00051\u0001\\\u0003\u00199X-[4ii\u0006)A\u0005\u001d7vgR!!q\u0001B\u0013\u0011\u001d\u00119C\na\u0001\u0005\u000f\t\u0011\u0001\u001e\u000b\u0007\u0005\u000f\u0011YC!\f\t\u0011\t-q\u0005%AA\u0002mC\u0001Ba\u0004(!\u0003\u0005\raW\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133)\u0011\t\tFa\r\t\u0013\u0005eC&!AA\u0002\u0005\u001dCcA+\u00038!I\u0011\u0011\f\u0018\u0002\u0002\u0003\u0007\u0011\u0011\u000b\u000b\u0005\u0003\u000b\u0011Y\u0004C\u0005\u0002Z=\n\t\u00111\u0001\u0002HQ\u0019QKa\u0010\t\u0013\u0005e#'!AA\u0002\u0005E\u0013aE*vM\u001aL7-[3oiN#\u0018\r^5ti&\u001c\u0007c\u0001B\u0005iM)AGa\u0012\u0002\u0014BA!\u0011\nB(7n\u00139!\u0004\u0002\u0003L)\u0019!QJ'\u0002\u000fI,h\u000e^5nK&!!\u0011\u000bB&\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\u000b\u0003\u0005\u0007\nQ!\u00199qYf$bAa\u0002\u0003Z\tm\u0003B\u0002B\u0006o\u0001\u00071\f\u0003\u0004\u0003\u0010]\u0002\raW\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tG!\u001c\u0011\u000b1\u0013\u0019Ga\u001a\n\u0007\t\u0015TJ\u0001\u0004PaRLwN\u001c\t\u0006\u0019\n%4lW\u0005\u0004\u0005Wj%A\u0002+va2,'\u0007C\u0005\u0003pa\n\t\u00111\u0001\u0003\b\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tU\u0004\u0003BA\u0004\u0005oJAA!\u001f\u0002\n\t1qJ\u00196fGR\f\u0001$Z7qif\u001cVO\u001a4jG&,g\u000e^*uCRL7\u000f^5d+\t\u00119!\u0001\ftk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7MR8s)\u0011\u00119Aa!\t\r\t\u001d2\b1\u0001V\u0003\riG.\u001a\u000b\u00047\n%\u0005B\u0002$=\u0001\u0004\u00119!\u0001\u0007eSN$(/\u001b2vi&|g\u000e\u0006\u0003\u0003\u0010\nMEcA<\u0003\u0012\")\u0001/\u0010a\u0002c\")Q.\u0010a\u00017\u0006\u0011B.[6fY&Dwn\u001c3Gk:\u001cG/[8o)\u0011\u0011IJa+\u0013\u000b\tm5Ja(\u0007\r\tue\b\u0001BM\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0015\u0011\tKa*\\\u001b\t\u0011\u0019KC\u0002\u0003&\u001e\u000b\u0001b\u001c9uS6L'0Z\u0005\u0005\u0005S\u0013\u0019K\u0001\u0007ES\u001a4g)\u001e8di&|g\u000e\u0003\u0004G}\u0001\u0007!q\u0001\u000b\u0005\u0005_\u0013\u0019\fF\u0002x\u0005cCQ\u0001] A\u0004EDQ!\\ A\u0002m#BAa.\u0003:B!AJa\u0019\\\u0011!\u0011y\u0007QA\u0001\u0002\u00049\b"
)
public class Bernoulli implements DiscreteDistr, Moments, Product {
   private final double p;
   private final RandBasis rand;

   public static Option unapply(final Bernoulli x$0) {
      return Bernoulli$.MODULE$.unapply(x$0);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Bernoulli$.MODULE$.likelihoodFunction(stats);
   }

   public static Bernoulli distribution(final double p, final RandBasis rand) {
      return Bernoulli$.MODULE$.distribution(p, rand);
   }

   public static double mle(final SufficientStatistic stats) {
      return Bernoulli$.MODULE$.mle(stats);
   }

   public static SufficientStatistic sufficientStatisticFor(final boolean t) {
      return Bernoulli$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Bernoulli$.MODULE$.emptySufficientStatistic();
   }

   public static Tuple2 posterior(final Tuple2 prior, final IterableOnce evidence) {
      return Bernoulli$.MODULE$.posterior(prior, evidence);
   }

   public static Polya predictive(final Tuple2 parameter, final RandBasis basis) {
      return Bernoulli$.MODULE$.predictive(parameter, basis);
   }

   public static Beta$ conjugateFamily() {
      return Bernoulli$.MODULE$.conjugateFamily();
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

   public double p() {
      return this.p;
   }

   public double probabilityOf(final boolean b) {
      return b ? this.p() : (double)1 - this.p();
   }

   public boolean draw() {
      return this.rand.uniform().draw$mcD$sp() < this.p();
   }

   public String toString() {
      return (new StringBuilder(11)).append("Bernoulli(").append(this.p()).append(")").toString();
   }

   public double mean() {
      return this.p();
   }

   public double variance() {
      return this.p() * ((double)1 - this.p());
   }

   public double mode() {
      return BoxesRunTime.unboxToDouble(package.I$.MODULE$.apply(BoxesRunTime.boxToBoolean(this.p() >= (double)0.5F), package$I$iBoolImpl$.MODULE$));
   }

   public double entropy() {
      return -this.p() * .MODULE$.log(this.p()) - ((double)1 - this.p()) * .MODULE$.log1p(-this.p());
   }

   public Bernoulli copy(final double p, final RandBasis rand) {
      return new Bernoulli(p, rand);
   }

   public double copy$default$1() {
      return this.p();
   }

   public String productPrefix() {
      return "Bernoulli";
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
      return x$1 instanceof Bernoulli;
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
            if (x$1 instanceof Bernoulli) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Bernoulli var4 = (Bernoulli)x$1;
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

   public Bernoulli(final double p, final RandBasis rand) {
      this.p = p;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(p >= (double)0.0F);
      scala.Predef..MODULE$.require(p <= (double)1.0F);
   }

   public static class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double numYes;
      private final double n;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double numYes() {
         return this.numYes;
      }

      public double n() {
         return this.n;
      }

      public SufficientStatistic $times(final double weight) {
         return new SufficientStatistic(this.numYes() * weight, this.n() * weight);
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         return new SufficientStatistic(this.numYes() + t.numYes(), this.n() + t.n());
      }

      public SufficientStatistic copy(final double numYes, final double n) {
         return new SufficientStatistic(numYes, n);
      }

      public double copy$default$1() {
         return this.numYes();
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
               var10000 = BoxesRunTime.boxToDouble(this.numYes());
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
               var10000 = "numYes";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.numYes()));
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
                  if (this.numYes() == var4.numYes() && this.n() == var4.n() && var4.canEqual(this)) {
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

      public SufficientStatistic(final double numYes, final double n) {
         this.numYes = numYes;
         this.n = n;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction2 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double numYes, final double n) {
         return new SufficientStatistic(numYes, n);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.numYes(), x$0.n())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
