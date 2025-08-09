package breeze.stats.distributions;

import breeze.linalg.DenseVector;
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
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tEf\u0001\u0002\"D\u0001*C\u0001\"\u001c\u0001\u0003\u0016\u0004%\tA\u001c\u0005\t_\u0002\u0011\t\u0012)A\u00057\"A\u0001\u000f\u0001B\u0001B\u0003-\u0011\u000fC\u0003u\u0001\u0011\u0005Q\u000fC\u0003{\u0001\u0011\u00051\u0010C\u0003}\u0001\u0011\u0005Q\u0010\u0003\u0004\u0002\u0002\u0001!\tA\u001c\u0005\u0007\u0003\u0007\u0001A\u0011\u00018\t\r\u0005\u0015\u0001\u0001\"\u0001o\u0011\u0019\t9\u0001\u0001C\u0001]\"9\u0011\u0011\u0002\u0001\u0005B\u0005-\u0001\"CA\u000f\u0001\u0005\u0005I\u0011AA\u0010\u0011%\t9\u0003AI\u0001\n\u0003\tI\u0003C\u0005\u0002@\u0001\t\t\u0011\"\u0011\u0002B!I\u0011\u0011\u000b\u0001\u0002\u0002\u0013\u0005\u00111\u000b\u0005\n\u0003+\u0002\u0011\u0011!C\u0001\u0003/B\u0011\"a\u0019\u0001\u0003\u0003%\t%!\u001a\t\u0013\u0005M\u0004!!A\u0005\u0002\u0005U\u0004\"CA@\u0001\u0005\u0005I\u0011IAA\u0011!\t)\tAA\u0001\n\u0003Z\b\"CAD\u0001\u0005\u0005I\u0011IAE\u000f\u001d\tii\u0011E\u0001\u0003\u001f3aAQ\"\t\u0002\u0005E\u0005B\u0002;\u0018\t\u0003\tI+B\u0003\u0002,^\u00011L\u0002\u0004\u0002.^\u0001\u0015q\u0016\u0005\n\u0003sS\"Q3A\u0005\u00029D\u0011\"a/\u001b\u0005#\u0005\u000b\u0011B.\t\u0013\u0005u&D!f\u0001\n\u0003q\u0007\"CA`5\tE\t\u0015!\u0003\\\u0011\u0019!(\u0004\"\u0001\u0002B\"9\u0011q\u0019\u000e\u0005\u0002\u0005%\u0007bBAh5\u0011\u0005\u0011\u0011\u001b\u0005\n\u0003;Q\u0012\u0011!C\u0001\u0003/D\u0011\"a\n\u001b#\u0003%\t!!\u000b\t\u0013\u0005u'$%A\u0005\u0002\u0005%\u0002\"CA 5\u0005\u0005I\u0011IA!\u0011%\t\tFGA\u0001\n\u0003\t\u0019\u0006C\u0005\u0002Vi\t\t\u0011\"\u0001\u0002`\"I\u00111\r\u000e\u0002\u0002\u0013\u0005\u0013Q\r\u0005\n\u0003gR\u0012\u0011!C\u0001\u0003GD\u0011\"a \u001b\u0003\u0003%\t%a:\t\u0011\u0005\u0015%$!A\u0005BmD\u0011\"!\u0003\u001b\u0003\u0003%\t%a;\t\u0013\u0005\u001d%$!A\u0005B\u00055x!CAy/\u0005\u0005\t\u0012AAz\r%\tikFA\u0001\u0012\u0003\t)\u0010\u0003\u0004u_\u0011\u0005!1\u0001\u0005\n\u0003\u0013y\u0013\u0011!C#\u0003WD\u0011B!\u00020\u0003\u0003%\tIa\u0002\t\u0013\t5q&!A\u0005\u0002\n=\u0001\"\u0003B\u0011_\u0005\u0005I\u0011\u0002B\u0012\u0011\u001d\u0011Yc\u0006C\u0001\u0005[AqAa\f\u0018\t\u0003\u0011\t\u0004C\u0004\u00036]!\tAa\u000e\t\u000f\tmr\u0003\"\u0001\u0003>!9!qK\f\u0005B\teSA\u0002B1/\u0001\u0011\u0019\u0007C\u0005\u0003j]\u0011\r\u0011\"\u0001\u0003l!A!1O\f!\u0002\u0013\u0011i\u0007C\u0004\u0003v]!\tAa\u001e\t\u000f\tEu\u0003\"\u0001\u0003\u0014\"I!QA\f\u0002\u0002\u0013\u0005%1\u0015\u0005\n\u0005\u001b9\u0012\u0011!CA\u0005WC\u0011B!\t\u0018\u0003\u0003%IAa\t\u0003\u0013\u001d+w.\\3ue&\u001c'B\u0001#F\u00035!\u0017n\u001d;sS\n,H/[8og*\u0011aiR\u0001\u0006gR\fGo\u001d\u0006\u0002\u0011\u00061!M]3fu\u0016\u001c\u0001a\u0005\u0004\u0001\u0017FCf,\u0019\t\u0003\u0019>k\u0011!\u0014\u0006\u0002\u001d\u0006)1oY1mC&\u0011\u0001+\u0014\u0002\u0007\u0003:L(+\u001a4\u0011\u0007I\u001bV+D\u0001D\u0013\t!6IA\u0007ESN\u001c'/\u001a;f\t&\u001cHO\u001d\t\u0003\u0019ZK!aV'\u0003\u0007%sG\u000f\u0005\u0003S3n[\u0016B\u0001.D\u0005\u001diu.\\3oiN\u0004\"\u0001\u0014/\n\u0005uk%A\u0002#pk\ndW\r\u0005\u0002M?&\u0011\u0001-\u0014\u0002\b!J|G-^2u!\t\u0011'N\u0004\u0002dQ:\u0011AmZ\u0007\u0002K*\u0011a-S\u0001\u0007yI|w\u000e\u001e \n\u00039K!!['\u0002\u000fA\f7m[1hK&\u00111\u000e\u001c\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003S6\u000b\u0011\u0001]\u000b\u00027\u0006\u0011\u0001\u000fI\u0001\u0005e\u0006tG\r\u0005\u0002Se&\u00111o\u0011\u0002\n%\u0006tGMQ1tSN\fa\u0001P5oSRtDC\u0001<z)\t9\b\u0010\u0005\u0002S\u0001!)\u0001\u000f\u0002a\u0002c\")Q\u000e\u0002a\u00017\u0006!AM]1x)\u0005)\u0016!\u00049s_\n\f'-\u001b7jif|e\r\u0006\u0002\\}\")qP\u0002a\u0001+\u0006\t\u00010\u0001\u0003nK\u0006t\u0017\u0001\u0003<be&\fgnY3\u0002\t5|G-Z\u0001\bK:$(o\u001c9z\u0003!!xn\u0015;sS:<GCAA\u0007!\u0011\ty!a\u0006\u000f\t\u0005E\u00111\u0003\t\u0003I6K1!!\u0006N\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011DA\u000e\u0005\u0019\u0019FO]5oO*\u0019\u0011QC'\u0002\t\r|\u0007/\u001f\u000b\u0005\u0003C\t)\u0003F\u0002x\u0003GAQ\u0001\u001d\u0007A\u0004EDq!\u001c\u0007\u0011\u0002\u0003\u00071,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005-\"fA.\u0002.-\u0012\u0011q\u0006\t\u0005\u0003c\tY$\u0004\u0002\u00024)!\u0011QGA\u001c\u0003%)hn\u00195fG.,GMC\u0002\u0002:5\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\ti$a\r\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0007\u0002B!!\u0012\u0002P5\u0011\u0011q\t\u0006\u0005\u0003\u0013\nY%\u0001\u0003mC:<'BAA'\u0003\u0011Q\u0017M^1\n\t\u0005e\u0011qI\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002+\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA-\u0003?\u00022\u0001TA.\u0013\r\ti&\u0014\u0002\u0004\u0003:L\b\u0002CA1!\u0005\u0005\t\u0019A+\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\u0007\u0005\u0004\u0002j\u0005=\u0014\u0011L\u0007\u0003\u0003WR1!!\u001cN\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003c\nYG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA<\u0003{\u00022\u0001TA=\u0013\r\tY(\u0014\u0002\b\u0005>|G.Z1o\u0011%\t\tGEA\u0001\u0002\u0004\tI&\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\"\u0003\u0007C\u0001\"!\u0019\u0014\u0003\u0003\u0005\r!V\u0001\tQ\u0006\u001c\bnQ8eK\u00061Q-];bYN$B!a\u001e\u0002\f\"I\u0011\u0011M\u000b\u0002\u0002\u0003\u0007\u0011\u0011L\u0001\n\u000f\u0016|W.\u001a;sS\u000e\u0004\"AU\f\u0014\u0011]Y\u00151SAM\u0003?\u0003RAUAKoVK1!a&D\u0005E)\u0005\u0010]8oK:$\u0018.\u00197GC6LG.\u001f\t\u0006%\u0006mu/V\u0005\u0004\u0003;\u001b%!\u0005%bg\u000e{gN[;hCR,\u0007K]5peB!\u0011\u0011UAT\u001b\t\t\u0019K\u0003\u0003\u0002&\u0006-\u0013AA5p\u0013\rY\u00171\u0015\u000b\u0003\u0003\u001f\u0013\u0011\u0002U1sC6,G/\u001a:\u0003'M+hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2\u0014\riY\u0015\u0011\u00170b!\u0015\u0011\u00161WA[\u0013\r\tik\u0011\t\u0004\u0003oSR\"A\f\u0002\u0007M,X.\u0001\u0003tk6\u0004\u0013!\u00018\u0002\u00059\u0004CCBA[\u0003\u0007\f)\r\u0003\u0004\u0002:~\u0001\ra\u0017\u0005\u0007\u0003{{\u0002\u0019A.\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005U\u00161\u001a\u0005\b\u0003\u001b\u0004\u0003\u0019AA[\u0003\u0005!\u0018A\u0002\u0013uS6,7\u000f\u0006\u0003\u00026\u0006M\u0007BBAkC\u0001\u00071,\u0001\u0004xK&<\u0007\u000e\u001e\u000b\u0007\u0003k\u000bI.a7\t\u0011\u0005e&\u0005%AA\u0002mC\u0001\"!0#!\u0003\u0005\raW\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133)\u0011\tI&!9\t\u0011\u0005\u0005t%!AA\u0002U#B!a\u001e\u0002f\"I\u0011\u0011M\u0015\u0002\u0002\u0003\u0007\u0011\u0011\f\u000b\u0005\u0003\u0007\nI\u000f\u0003\u0005\u0002b)\n\t\u00111\u0001V)\t\t\u0019\u0005\u0006\u0003\u0002x\u0005=\b\"CA1[\u0005\u0005\t\u0019AA-\u0003M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d!\r\t9lL\n\u0006_\u0005]\u0018q\u0014\t\t\u0003s\fypW.\u000266\u0011\u00111 \u0006\u0004\u0003{l\u0015a\u0002:v]RLW.Z\u0005\u0005\u0005\u0003\tYPA\tBEN$(/Y2u\rVt7\r^5p]J\"\"!a=\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\r\u0005U&\u0011\u0002B\u0006\u0011\u0019\tIL\ra\u00017\"1\u0011Q\u0018\u001aA\u0002m\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003\u0012\tu\u0001#\u0002'\u0003\u0014\t]\u0011b\u0001B\u000b\u001b\n1q\n\u001d;j_:\u0004R\u0001\u0014B\r7nK1Aa\u0007N\u0005\u0019!V\u000f\u001d7fe!I!qD\u001a\u0002\u0002\u0003\u0007\u0011QW\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u0013!\u0011\t)Ea\n\n\t\t%\u0012q\t\u0002\u0007\u001f\nTWm\u0019;\u00021\u0015l\u0007\u000f^=Tk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7-\u0006\u0002\u00026\u000612/\u001e4gS\u000eLWM\u001c;Ti\u0006$\u0018n\u001d;jG\u001a{'\u000f\u0006\u0003\u00026\nM\u0002BBAgm\u0001\u0007Q+A\u0002nY\u0016$2a\u0017B\u001d\u0011\u00191u\u00071\u0001\u00026\u0006\u0011B.[6fY&Dwn\u001c3Gk:\u001cG/[8o)\u0011\u0011yD!\u0016\u0013\u000b\t\u00053J!\u0012\u0007\r\t\r\u0003\b\u0001B \u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0019\u00119E!\u0014\u0003R5\u0011!\u0011\n\u0006\u0004\u0005\u0017:\u0015\u0001C8qi&l\u0017N_3\n\t\t=#\u0011\n\u0002\r\t&4gMR;oGRLwN\u001c\t\u0004\u0005'JbB\u0001*\u0017\u0011\u00191\u0005\b1\u0001\u00026\u0006aA-[:ue&\u0014W\u000f^5p]R!!1\fB0)\r9(Q\f\u0005\u0006af\u0002\u001d!\u001d\u0005\u0007[f\u0002\rA!\u0015\u0003\u001d\r{gN[;hCR,\u0007K]5peB\u0019!K!\u001a\n\u0007\t\u001d4I\u0001\u0003CKR\f\u0017aD2p]*,x-\u0019;f\r\u0006l\u0017\u000e\\=\u0016\u0005\t5db\u0001*\u0003p%\u0019!\u0011O\"\u0002\t\t+G/Y\u0001\u0011G>t'.^4bi\u00164\u0015-\\5ms\u0002\n!\u0002\u001d:fI&\u001cG/\u001b<f)\u0011\u0011IH!\"\u0015\t\tm$\u0011\u0011\t\u0004\u0019\nu\u0014b\u0001B@\u001b\n9aj\u001c;iS:<\u0007B\u0002BB{\u0001\u000f\u0011/A\u0003cCNL7\u000fC\u0004\u0003\bv\u0002\rA!#\u0002\u0013A\f'/Y7fi\u0016\u0014\b\u0003\u0002BF\u0005\u001bs1!a.<\u0013\u0011\tYKa$\u000b\u0007\tE4)A\u0005q_N$XM]5peR1!\u0011\u0012BK\u00053CqAa&?\u0001\u0004\u0011I)A\u0003qe&|'\u000fC\u0004\u0003\u001cz\u0002\rA!(\u0002\u0011\u00154\u0018\u000eZ3oG\u0016\u0004BA\u0019BP+&\u0019!\u0011\u00157\u0003\u001fQ\u0013\u0018M^3sg\u0006\u0014G.Z(oG\u0016$BA!*\u0003*R\u0019qOa*\t\u000bA|\u00049A9\t\u000b5|\u0004\u0019A.\u0015\t\t5&q\u0016\t\u0005\u0019\nM1\f\u0003\u0005\u0003 \u0001\u000b\t\u00111\u0001x\u0001"
)
public class Geometric implements DiscreteDistr, Moments, Product {
   private final double p;
   public final RandBasis breeze$stats$distributions$Geometric$$rand;

   public static Option unapply(final Geometric x$0) {
      return Geometric$.MODULE$.unapply(x$0);
   }

   public static Tuple2 posterior(final Tuple2 prior, final IterableOnce evidence) {
      return Geometric$.MODULE$.posterior(prior, evidence);
   }

   public static Nothing predictive(final Tuple2 parameter, final RandBasis basis) {
      return Geometric$.MODULE$.predictive(parameter, basis);
   }

   public static Beta$ conjugateFamily() {
      return Geometric$.MODULE$.conjugateFamily();
   }

   public static Geometric distribution(final double p, final RandBasis rand) {
      return Geometric$.MODULE$.distribution(p, rand);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Geometric$.MODULE$.likelihoodFunction(stats);
   }

   public static double mle(final SufficientStatistic stats) {
      return Geometric$.MODULE$.mle(stats);
   }

   public static SufficientStatistic sufficientStatisticFor(final int t) {
      return Geometric$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Geometric$.MODULE$.emptySufficientStatistic();
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

   public int draw() {
      return this.draw$mcI$sp();
   }

   public double probabilityOf(final int x) {
      return .MODULE$.pow((double)1 - this.p(), (double)x) * this.p();
   }

   public double mean() {
      return (double)1 / this.p();
   }

   public double variance() {
      return ((double)1 - this.p()) / (this.p() * this.p());
   }

   public double mode() {
      return (double)1.0F;
   }

   public double entropy() {
      return (-((double)1 - this.p()) * .MODULE$.log((double)1 - this.p()) - this.p() * .MODULE$.log(this.p())) / this.p();
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public Geometric copy(final double p, final RandBasis rand) {
      return new Geometric(p, rand);
   }

   public double copy$default$1() {
      return this.p();
   }

   public String productPrefix() {
      return "Geometric";
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
      return x$1 instanceof Geometric;
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
            if (x$1 instanceof Geometric) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Geometric var4 = (Geometric)x$1;
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
      int var10000;
      if (this.p() < 0.3333333333333333) {
         var10000 = (int).MODULE$.ceil(.MODULE$.log(this.breeze$stats$distributions$Geometric$$rand.uniform().draw$mcD$sp()) / .MODULE$.log((double)1 - this.p()));
      } else {
         int i;
         for(i = 1; this.breeze$stats$distributions$Geometric$$rand.uniform().draw$mcD$sp() > this.p(); ++i) {
         }

         var10000 = i;
      }

      return var10000;
   }

   public Geometric(final double p, final RandBasis rand) {
      this.p = p;
      this.breeze$stats$distributions$Geometric$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      DiscreteDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(p >= (double)0);
      scala.Predef..MODULE$.require(p <= (double)1);
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
         return new SufficientStatistic(this.sum() + t.sum(), this.n() + t.n());
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
