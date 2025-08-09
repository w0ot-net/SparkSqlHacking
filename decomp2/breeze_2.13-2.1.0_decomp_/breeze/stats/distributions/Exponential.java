package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.numerics.package;
import breeze.numerics.package$log$logDoubleImpl$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tMe\u0001B!C\u0001&C\u0001b\u001c\u0001\u0003\u0016\u0004%\t\u0001\u001d\u0005\tc\u0002\u0011\t\u0012)A\u0005)\"A!\u000f\u0001B\u0001B\u0003-1\u000fC\u0003w\u0001\u0011\u0005q\u000fC\u0003}\u0001\u0011\u0005S\u0010C\u0004\u0002\u000e\u0001!\t!a\u0004\t\u0013\u0005U\u0001\u0001#b\u0001\n\u0003\u0001\bbBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\b\u00037\u0001A\u0011IA\u000f\u0011\u001d\t)\u0003\u0001C!\u0003OAq!!\f\u0001\t\u0003\ny\u0003\u0003\u0004\u00024\u0001!\t\u0005\u001d\u0005\u0007\u0003k\u0001A\u0011\t9\t\r\u0005]\u0002\u0001\"\u0011q\u0011\u0019\tI\u0004\u0001C!a\"I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u000b\u0002\u0011\u0013!C\u0001\u0003\u000fB\u0011\"!\u0018\u0001\u0003\u0003%\t%a\u0018\t\u0013\u0005=\u0004!!A\u0005\u0002\u0005E\u0004\"CA=\u0001\u0005\u0005I\u0011AA>\u0011%\t9\tAA\u0001\n\u0003\nI\tC\u0005\u0002\u0018\u0002\t\t\u0011\"\u0001\u0002\u001a\"I\u00111\u0015\u0001\u0002\u0002\u0013\u0005\u0013Q\u0015\u0005\n\u0003S\u0003\u0011\u0011!C!\u0003WC\u0011\"!,\u0001\u0003\u0003%\t%a,\b\u000f\u0005M&\t#\u0001\u00026\u001a1\u0011I\u0011E\u0001\u0003oCaA^\u000e\u0005\u0002\u0005=W!BAi7\u0001!fABAj7\u0001\u000b)\u000eC\u0005\u0002`z\u0011)\u001a!C\u0001a\"I\u0011\u0011\u001d\u0010\u0003\u0012\u0003\u0006I\u0001\u0016\u0005\n\u0003Gt\"Q3A\u0005\u0002AD\u0011\"!:\u001f\u0005#\u0005\u000b\u0011\u0002+\t\rYtB\u0011AAt\u0011\u001d\tiO\bC\u0001\u0003_Dq!!>\u001f\t\u0003\t9\u0010C\u0005\u0002<y\t\t\u0011\"\u0001\u0002~\"I\u0011Q\t\u0010\u0012\u0002\u0013\u0005\u0011q\t\u0005\n\u0005\u0007q\u0012\u0013!C\u0001\u0003\u000fB\u0011\"!\u0018\u001f\u0003\u0003%\t%a\u0018\t\u0013\u0005=d$!A\u0005\u0002\u0005E\u0004\"CA==\u0005\u0005I\u0011\u0001B\u0003\u0011%\t9IHA\u0001\n\u0003\nI\tC\u0005\u0002\u0018z\t\t\u0011\"\u0001\u0003\n!I\u00111\u0015\u0010\u0002\u0002\u0013\u0005#Q\u0002\u0005\n\u0003Ss\u0012\u0011!C!\u0003WC\u0001\u0002 \u0010\u0002\u0002\u0013\u0005#\u0011\u0003\u0005\n\u0003[s\u0012\u0011!C!\u0005'9\u0011Ba\u0006\u001c\u0003\u0003E\tA!\u0007\u0007\u0013\u0005M7$!A\t\u0002\tm\u0001B\u0002<4\t\u0003\u0011I\u0003\u0003\u0005}g\u0005\u0005IQ\tB\t\u0011%\u0011YcMA\u0001\n\u0003\u0013i\u0003C\u0005\u00034M\n\t\u0011\"!\u00036!I!qI\u001a\u0002\u0002\u0013%!\u0011\n\u0005\b\u0005#ZB\u0011\u0001B*\u0011\u001d\u0011)f\u0007C\u0001\u0005/BqAa\u0017\u001c\t\u0003\u0011i\u0006C\u0004\u0003bm!\tAa\u0019\t\u000f\te4\u0004\"\u0011\u0003|!I!1F\u000e\u0002\u0002\u0013\u0005%Q\u0011\u0005\n\u0005gY\u0012\u0011!CA\u0005\u001bC\u0011Ba\u0012\u001c\u0003\u0003%IA!\u0013\u0003\u0017\u0015C\bo\u001c8f]RL\u0017\r\u001c\u0006\u0003\u0007\u0012\u000bQ\u0002Z5tiJL'-\u001e;j_:\u001c(BA#G\u0003\u0015\u0019H/\u0019;t\u0015\u00059\u0015A\u00022sK\u0016TXm\u0001\u0001\u0014\u0011\u0001Q\u0005k\u0016.^A\u000e\u0004\"a\u0013(\u000e\u00031S\u0011!T\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001f2\u0013a!\u00118z%\u00164\u0007cA)S)6\t!)\u0003\u0002T\u0005\ny1i\u001c8uS:,x.^:ESN$(\u000f\u0005\u0002L+&\u0011a\u000b\u0014\u0002\u0007\t>,(\r\\3\u0011\tECF\u000bV\u0005\u00033\n\u0013q!T8nK:$8\u000f\u0005\u0002R7&\u0011AL\u0011\u0002\u0007\u0011\u0006\u001c8\t\u001a4\u0011\u0005Es\u0016BA0C\u00055A\u0015m]%om\u0016\u00148/Z\"eMB\u00111*Y\u0005\u0003E2\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002eY:\u0011QM\u001b\b\u0003M&l\u0011a\u001a\u0006\u0003Q\"\u000ba\u0001\u0010:p_Rt\u0014\"A'\n\u0005-d\u0015a\u00029bG.\fw-Z\u0005\u0003[:\u0014AbU3sS\u0006d\u0017N_1cY\u0016T!a\u001b'\u0002\tI\fG/Z\u000b\u0002)\u0006)!/\u0019;fA\u0005)!-Y:jgB\u0011\u0011\u000b^\u0005\u0003k\n\u0013\u0011BU1oI\n\u000b7/[:\u0002\rqJg.\u001b;?)\tA8\u0010\u0006\u0002zuB\u0011\u0011\u000b\u0001\u0005\u0006e\u0012\u0001\u001da\u001d\u0005\u0006_\u0012\u0001\r\u0001V\u0001\ti>\u001cFO]5oOR\ta\u0010E\u0002\u0000\u0003\u000fqA!!\u0001\u0002\u0004A\u0011a\rT\u0005\u0004\u0003\u000ba\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002\n\u0005-!AB*ue&twMC\u0002\u0002\u00061\u000b!#\u001e8o_Jl\u0017\r\\5{K\u0012dun\u001a)eMR\u0019A+!\u0005\t\r\u0005Ma\u00011\u0001U\u0003\u0005A\u0018!\u00047pO:{'/\\1mSj,'/\u0001\u0003ee\u0006<H#\u0001+\u0002\u0017A\u0014xNY1cS2LG/\u001f\u000b\u0006)\u0006}\u0011\u0011\u0005\u0005\u0007\u0003'I\u0001\u0019\u0001+\t\r\u0005\r\u0012\u00021\u0001U\u0003\u0005I\u0018AC5om\u0016\u00148/Z\"eMR\u0019A+!\u000b\t\r\u0005-\"\u00021\u0001U\u0003\u0005\u0001\u0018aA2eMR\u0019A+!\r\t\r\u0005M1\u00021\u0001U\u0003\u0011iW-\u00198\u0002\u0011Y\f'/[1oG\u0016\fA!\\8eK\u00069QM\u001c;s_BL\u0018\u0001B2paf$B!a\u0010\u0002DQ\u0019\u00110!\u0011\t\u000bI\u0004\u00029A:\t\u000f=\u0004\u0002\u0013!a\u0001)\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA%U\r!\u00161J\u0016\u0003\u0003\u001b\u0002B!a\u0014\u0002Z5\u0011\u0011\u0011\u000b\u0006\u0005\u0003'\n)&A\u0005v]\u000eDWmY6fI*\u0019\u0011q\u000b'\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\\\u0005E#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!!\u0019\u0011\t\u0005\r\u0014QN\u0007\u0003\u0003KRA!a\u001a\u0002j\u0005!A.\u00198h\u0015\t\tY'\u0001\u0003kCZ\f\u0017\u0002BA\u0005\u0003K\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u001d\u0011\u0007-\u000b)(C\u0002\u0002x1\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!! \u0002\u0004B\u00191*a \n\u0007\u0005\u0005EJA\u0002B]fD\u0011\"!\"\u0015\u0003\u0003\u0005\r!a\u001d\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\t\u0005\u0004\u0002\u000e\u0006M\u0015QP\u0007\u0003\u0003\u001fS1!!%M\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003+\u000byI\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAN\u0003C\u00032aSAO\u0013\r\ty\n\u0014\u0002\b\u0005>|G.Z1o\u0011%\t)IFA\u0001\u0002\u0004\ti(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA1\u0003OC\u0011\"!\"\u0018\u0003\u0003\u0005\r!a\u001d\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u001d\u0002\r\u0015\fX/\u00197t)\u0011\tY*!-\t\u0013\u0005\u0015\u0015$!AA\u0002\u0005u\u0014aC#ya>tWM\u001c;jC2\u0004\"!U\u000e\u0014\u0011mQ\u0015\u0011XA`\u0003\u000b\u0004R!UA^sRK1!!0C\u0005E)\u0005\u0010]8oK:$\u0018.\u00197GC6LG.\u001f\t\u0006#\u0006\u0005G+_\u0005\u0004\u0003\u0007\u0014%aI\"p]RLg.^8vg\u0012K7\u000f\u001e:jEV$\u0018n\u001c8V\rVt7\r\u0015:pm&$WM\u001d\t\u0005\u0003\u000f\fi-\u0004\u0002\u0002J*!\u00111ZA5\u0003\tIw.C\u0002n\u0003\u0013$\"!!.\u0003\u0013A\u000b'/Y7fi\u0016\u0014(aE*vM\u001aL7-[3oiN#\u0018\r^5ti&\u001c7C\u0002\u0010K\u0003/\u00047\rE\u0003R\u00033\fY.C\u0002\u0002T\n\u00032!!8\u001f\u001b\u0005Y\u0012!\u00018\u0002\u00059\u0004\u0013!\u0001<\u0002\u0005Y\u0004CCBAn\u0003S\fY\u000f\u0003\u0004\u0002`\u000e\u0002\r\u0001\u0016\u0005\u0007\u0003G\u001c\u0003\u0019\u0001+\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005m\u0017\u0011\u001f\u0005\b\u0003g$\u0003\u0019AAn\u0003\u0005!\u0018A\u0002\u0013uS6,7\u000f\u0006\u0003\u0002\\\u0006e\bBBA~K\u0001\u0007A+\u0001\u0004xK&<\u0007\u000e\u001e\u000b\u0007\u00037\fyP!\u0001\t\u0011\u0005}g\u0005%AA\u0002QC\u0001\"a9'!\u0003\u0005\r\u0001V\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133)\u0011\tiHa\u0002\t\u0013\u0005\u00155&!AA\u0002\u0005MD\u0003BAN\u0005\u0017A\u0011\"!\".\u0003\u0003\u0005\r!! \u0015\t\u0005\u0005$q\u0002\u0005\n\u0003\u000bs\u0013\u0011!a\u0001\u0003g\"\"!!\u0019\u0015\t\u0005m%Q\u0003\u0005\n\u0003\u000b\u000b\u0014\u0011!a\u0001\u0003{\n1cU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e\u00042!!84'\u0015\u0019$QDAc!!\u0011yB!\nU)\u0006mWB\u0001B\u0011\u0015\r\u0011\u0019\u0003T\u0001\beVtG/[7f\u0013\u0011\u00119C!\t\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0003\u001a\u0005)\u0011\r\u001d9msR1\u00111\u001cB\u0018\u0005cAa!a87\u0001\u0004!\u0006BBArm\u0001\u0007A+A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t]\"1\t\t\u0006\u0017\ne\"QH\u0005\u0004\u0005wa%AB(qi&|g\u000eE\u0003L\u0005\u007f!F+C\u0002\u0003B1\u0013a\u0001V;qY\u0016\u0014\u0004\"\u0003B#o\u0005\u0005\t\u0019AAn\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u0017\u0002B!a\u0019\u0003N%!!qJA3\u0005\u0019y%M[3di\u0006AR-\u001c9usN+hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2\u0016\u0005\u0005m\u0017AF:vM\u001aL7-[3oiN#\u0018\r^5ti&\u001cgi\u001c:\u0015\t\u0005m'\u0011\f\u0005\u0007\u0003gT\u0004\u0019\u0001+\u0002\u00075dW\rF\u0002U\u0005?Ba!R\u001eA\u0002\u0005m\u0017A\u00057jW\u0016d\u0017\u000e[8pI\u001a+hn\u0019;j_:$BA!\u001a\u0003xI)!q\r&\u0003l\u00191!\u0011\u000e\u001f\u0001\u0005K\u0012A\u0002\u0010:fM&tW-\\3oiz\u0002RA!\u001c\u0003tQk!Aa\u001c\u000b\u0007\tEd)\u0001\u0005paRLW.\u001b>f\u0013\u0011\u0011)Ha\u001c\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\t\r\u0015c\u0004\u0019AAn\u00031!\u0017n\u001d;sS\n,H/[8o)\u0011\u0011iHa!\u0015\u0007e\u0014y\b\u0003\u0004\u0003\u0002v\u0002\u001da]\u0001\u0005e\u0006tG\r\u0003\u0004\u0002,u\u0002\r\u0001\u0016\u000b\u0005\u0005\u000f\u0013Y\tF\u0002z\u0005\u0013CQA\u001d A\u0004MDQa\u001c A\u0002Q#BAa$\u0003\u0012B!1J!\u000fU\u0011!\u0011)ePA\u0001\u0002\u0004I\b"
)
public class Exponential implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double logNormalizer;
   private final double rate;
   public final RandBasis breeze$stats$distributions$Exponential$$basis;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Exponential x$0) {
      return Exponential$.MODULE$.unapply(x$0);
   }

   public static Exponential distribution(final double p, final RandBasis rand) {
      return Exponential$.MODULE$.distribution(p, rand);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Exponential$.MODULE$.likelihoodFunction(stats);
   }

   public static double mle(final SufficientStatistic stats) {
      return Exponential$.MODULE$.mle(stats);
   }

   public static SufficientStatistic sufficientStatisticFor(final double t) {
      return Exponential$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Exponential$.MODULE$.emptySufficientStatistic();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return Exponential$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return Exponential$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return Exponential$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return Exponential$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return Exponential$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return Exponential$.MODULE$.inPlace(v, impl);
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

   public double rate() {
      return this.rate;
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public double unnormalizedLogPdf(final double x) {
      return -this.rate() * x;
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = -scala.math.package..MODULE$.log(this.rate());
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

   public double probability(final double x, final double y) {
      return (new ExponentialDistribution(this.mean())).probability(x, y);
   }

   public double inverseCdf(final double p) {
      return (new ExponentialDistribution(this.mean())).inverseCumulativeProbability(p);
   }

   public double cdf(final double x) {
      return (new ExponentialDistribution(this.mean())).cumulativeProbability(x);
   }

   public double mean() {
      return (double)1 / this.rate();
   }

   public double variance() {
      return (double)1 / (this.rate() * this.rate());
   }

   public double mode() {
      return (double)0.0F;
   }

   public double entropy() {
      return (double)1 - package.log$.MODULE$.apply$mDDc$sp(this.rate(), package$log$logDoubleImpl$.MODULE$);
   }

   public Exponential copy(final double rate, final RandBasis basis) {
      return new Exponential(rate, basis);
   }

   public double copy$default$1() {
      return this.rate();
   }

   public String productPrefix() {
      return "Exponential";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.rate());
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
      return x$1 instanceof Exponential;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "rate";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.rate()));
      return Statics.finalizeHash(var1, 1);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof Exponential) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Exponential var4 = (Exponential)x$1;
               if (this.rate() == var4.rate() && var4.canEqual(this)) {
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

   public double draw$mcD$sp() {
      return -scala.math.package..MODULE$.log(this.breeze$stats$distributions$Exponential$$basis.uniform().draw$mcD$sp()) / this.rate();
   }

   public Exponential(final double rate, final RandBasis basis) {
      this.rate = rate;
      this.breeze$stats$distributions$Exponential$$basis = basis;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(rate > (double)0);
   }

   public static class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double n;
      private final double v;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double n() {
         return this.n;
      }

      public double v() {
         return this.v;
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         return this.copy(this.n() + t.n(), this.v() + t.v());
      }

      public SufficientStatistic $times(final double weight) {
         return this.copy(this.n() * weight, this.v() * weight);
      }

      public SufficientStatistic copy(final double n, final double v) {
         return new SufficientStatistic(n, v);
      }

      public double copy$default$1() {
         return this.n();
      }

      public double copy$default$2() {
         return this.v();
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
               var10000 = BoxesRunTime.boxToDouble(this.n());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.v());
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
         return x$1 instanceof SufficientStatistic;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "n";
               break;
            case 1:
               var10000 = "v";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.doubleHash(this.n()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.v()));
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
               if (x$1 instanceof SufficientStatistic) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SufficientStatistic var4 = (SufficientStatistic)x$1;
                  if (this.n() == var4.n() && this.v() == var4.v() && var4.canEqual(this)) {
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

      public SufficientStatistic(final double n, final double v) {
         this.n = n;
         this.v = v;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction2 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double n, final double v) {
         return new SufficientStatistic(n, v);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcDD.sp(x$0.n(), x$0.v())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
