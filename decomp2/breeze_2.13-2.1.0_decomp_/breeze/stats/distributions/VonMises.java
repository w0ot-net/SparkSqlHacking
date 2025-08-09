package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import breeze.numerics.Bessel;
import breeze.numerics.Bessel$i0$ImplDouble$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple5;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tUe\u0001\u0002%J\u0001BC\u0001\u0002\u001d\u0001\u0003\u0016\u0004%\t!\u001d\u0005\te\u0002\u0011\t\u0012)A\u00057\"A1\u000f\u0001BK\u0002\u0013\u0005\u0011\u000f\u0003\u0005u\u0001\tE\t\u0015!\u0003\\\u0011!)\bA!A!\u0002\u00171\b\"B=\u0001\t\u0003Q\bbBA\u0001\u0001\u0011\u0005\u00131\u0001\u0005\n\u0003\u0013\u0001\u0001R1A\u0005\u0002ED\u0001\"a\u0003\u0001\u0005\u0004%I!\u001d\u0005\b\u0003\u001b\u0001\u0001\u0015!\u0003\\\u0011%\ty\u0001\u0001b\u0001\n\u0013\t\t\u0002\u0003\u0005\u0002\u001a\u0001\u0001\u000b\u0011BA\n\u0011\u001d\tY\u0002\u0001C\u0001\u0003;A!\"a\b\u0001\u0011\u000b\u0007I\u0011IA\u0011\u0011\u0019\t\u0019\u0004\u0001C\u0001c\"1\u0011Q\u0007\u0001\u0005\u0002EDa!a\u000e\u0001\t\u0003\t\bBBA\u001d\u0001\u0011\u0005\u0011\u000fC\u0005\u0002<\u0001\t\t\u0011\"\u0001\u0002>!I\u0011q\t\u0001\u0012\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003?\u0002\u0011\u0013!C\u0001\u0003\u0013B\u0011\"!\u0019\u0001\u0003\u0003%\t%!\t\t\u0013\u0005\r\u0004!!A\u0005\u0002\u0005\u0015\u0004\"CA7\u0001\u0005\u0005I\u0011AA8\u0011%\tY\bAA\u0001\n\u0003\ni\bC\u0005\u0002\f\u0002\t\t\u0011\"\u0001\u0002\u000e\"I\u0011q\u0013\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0014\u0005\n\u0003;\u0003\u0011\u0011!C!\u0003?C\u0011\"!)\u0001\u0003\u0003%\t%a)\b\u000f\u0005\u001d\u0016\n#\u0001\u0002*\u001a1\u0001*\u0013E\u0001\u0003WCa!_\u0010\u0005\u0002\u0005uVABA`?\u0001\t\tM\u0002\u0004\u0002H~\u0001\u0015\u0011\u001a\u0005\n\u0003'\u0014#Q3A\u0005\u0002ED\u0011\"!6#\u0005#\u0005\u000b\u0011B.\t\u0013\u0005]'E!f\u0001\n\u0003\t\b\"CAmE\tE\t\u0015!\u0003\\\u0011%\tYN\tBK\u0002\u0013\u0005\u0011\u000fC\u0005\u0002^\n\u0012\t\u0012)A\u00057\"1\u0011P\tC\u0001\u0003?Dq!a:#\t\u0003\tI\u000fC\u0004\u0002p\n\"\t!!=\t\u0013\u0005m\"%!A\u0005\u0002\u0005]\b\"CA$EE\u0005I\u0011AA%\u0011%\tyFII\u0001\n\u0003\tI\u0005C\u0005\u0002\u0000\n\n\n\u0011\"\u0001\u0002J!I\u0011\u0011\r\u0012\u0002\u0002\u0013\u0005\u0013\u0011\u0005\u0005\n\u0003G\u0012\u0013\u0011!C\u0001\u0003KB\u0011\"!\u001c#\u0003\u0003%\tA!\u0001\t\u0013\u0005m$%!A\u0005B\u0005u\u0004\"CAFE\u0005\u0005I\u0011\u0001B\u0003\u0011%\t9JIA\u0001\n\u0003\u0012I\u0001C\u0005\u0002\u001e\n\n\t\u0011\"\u0011\u0002 \"I\u0011q\u0004\u0012\u0002\u0002\u0013\u0005#Q\u0002\u0005\n\u0003C\u0013\u0013\u0011!C!\u0005\u001f9\u0011Ba\u0005 \u0003\u0003E\tA!\u0006\u0007\u0013\u0005\u001dw$!A\t\u0002\t]\u0001BB=;\t\u0003\u0011)\u0003C\u0005\u0002 i\n\t\u0011\"\u0012\u0003\u000e!I!q\u0005\u001e\u0002\u0002\u0013\u0005%\u0011\u0006\u0005\n\u0005cQ\u0014\u0011!CA\u0005gA\u0011B!\u0012;\u0003\u0003%IAa\u0012\t\u000f\t=s\u0004\"\u0001\u0003R!9!1K\u0010\u0005\u0002\tU\u0003b\u0002B-?\u0011\u0005#1\f\u0005\b\u0005OzB\u0011\u0001B5\u0011\u001d\u0011ig\bC\u0001\u0005_B\u0011Ba\n \u0003\u0003%\tI!\"\t\u0013\tEr$!A\u0005\u0002\n=\u0005\"\u0003B#?\u0005\u0005I\u0011\u0002B$\u0005!1vN\\'jg\u0016\u001c(B\u0001&L\u00035!\u0017n\u001d;sS\n,H/[8og*\u0011A*T\u0001\u0006gR\fGo\u001d\u0006\u0002\u001d\u00061!M]3fu\u0016\u001c\u0001a\u0005\u0004\u0001#^s\u0016\r\u001a\t\u0003%Vk\u0011a\u0015\u0006\u0002)\u0006)1oY1mC&\u0011ak\u0015\u0002\u0007\u0003:L(+\u001a4\u0011\u0007aK6,D\u0001J\u0013\tQ\u0016JA\bD_:$\u0018N\\;pkN$\u0015n\u001d;s!\t\u0011F,\u0003\u0002^'\n1Ai\\;cY\u0016\u0004B\u0001W0\\7&\u0011\u0001-\u0013\u0002\b\u001b>lWM\u001c;t!\t\u0011&-\u0003\u0002d'\n9\u0001K]8ek\u000e$\bCA3n\u001d\t17N\u0004\u0002hU6\t\u0001N\u0003\u0002j\u001f\u00061AH]8pizJ\u0011\u0001V\u0005\u0003YN\u000bq\u0001]1dW\u0006<W-\u0003\u0002o_\na1+\u001a:jC2L'0\u00192mK*\u0011AnU\u0001\u0003[V,\u0012aW\u0001\u0004[V\u0004\u0013!A6\u0002\u0005-\u0004\u0013\u0001\u0002:b]\u0012\u0004\"\u0001W<\n\u0005aL%!\u0003*b]\u0012\u0014\u0015m]5t\u0003\u0019a\u0014N\\5u}Q\u00191P`@\u0015\u0005ql\bC\u0001-\u0001\u0011\u0015)h\u0001q\u0001w\u0011\u0015\u0001h\u00011\u0001\\\u0011\u0015\u0019h\u00011\u0001\\\u0003I)hN\\8s[\u0006d\u0017N_3e\u0019><\u0007\u000b\u001a4\u0015\u0007m\u000b)\u0001\u0003\u0004\u0002\b\u001d\u0001\raW\u0001\u0006i\",G/Y\u0001\u000eY><gj\u001c:nC2L'0\u001a:\u0002\u0003I\f!A\u001d\u0011\u0002\u00115L(+\u00198e_6,\"!a\u0005\u0011\ta\u000b)bW\u0005\u0004\u0003/I%\u0001\u0002*b]\u0012\f\u0011\"\\=SC:$w.\u001c\u0011\u0002\t\u0011\u0014\u0018m\u001e\u000b\u00027\u0006AAo\\*ue&tw-\u0006\u0002\u0002$A!\u0011QEA\u0018\u001b\t\t9C\u0003\u0003\u0002*\u0005-\u0012\u0001\u00027b]\u001eT!!!\f\u0002\t)\fg/Y\u0005\u0005\u0003c\t9C\u0001\u0004TiJLgnZ\u0001\u0005[\u0016\fg.\u0001\u0003n_\u0012,\u0017\u0001\u0003<be&\fgnY3\u0002\u000f\u0015tGO]8qs\u0006!1m\u001c9z)\u0019\ty$a\u0011\u0002FQ\u0019A0!\u0011\t\u000bU\u001c\u00029\u0001<\t\u000fA\u001c\u0002\u0013!a\u00017\"91o\u0005I\u0001\u0002\u0004Y\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u0017R3aWA'W\t\ty\u0005\u0005\u0003\u0002R\u0005mSBAA*\u0015\u0011\t)&a\u0016\u0002\u0013Ut7\r[3dW\u0016$'bAA-'\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005u\u00131\u000b\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u001d\u0004c\u0001*\u0002j%\u0019\u00111N*\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005E\u0014q\u000f\t\u0004%\u0006M\u0014bAA;'\n\u0019\u0011I\\=\t\u0013\u0005e\u0004$!AA\u0002\u0005\u001d\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0000A1\u0011\u0011QAD\u0003cj!!a!\u000b\u0007\u0005\u00155+\u0001\u0006d_2dWm\u0019;j_:LA!!#\u0002\u0004\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty)!&\u0011\u0007I\u000b\t*C\u0002\u0002\u0014N\u0013qAQ8pY\u0016\fg\u000eC\u0005\u0002zi\t\t\u00111\u0001\u0002r\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\u0019#a'\t\u0013\u0005e4$!AA\u0002\u0005\u001d\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0014AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0010\u0006\u0015\u0006\"CA=;\u0005\u0005\t\u0019AA9\u0003!1vN\\'jg\u0016\u001c\bC\u0001- '\u0019y\u0012+!,\u00024B)\u0001,a,}7&\u0019\u0011\u0011W%\u0003#\u0015C\bo\u001c8f]RL\u0017\r\u001c$b[&d\u0017\u0010\u0005\u0003\u00026\u0006mVBAA\\\u0015\u0011\tI,a\u000b\u0002\u0005%|\u0017b\u00018\u00028R\u0011\u0011\u0011\u0016\u0002\n!\u0006\u0014\u0018-\\3uKJ\u0004RAUAb7nK1!!2T\u0005\u0019!V\u000f\u001d7fe\t\u00192+\u001e4gS\u000eLWM\u001c;Ti\u0006$\u0018n\u001d;jGN1!%UAfC\u0012\u0004R\u0001WAg\u0003\u001fL1!a2J!\r\t\tNI\u0007\u0002?\u0005\ta.\u0001\u0002oA\u0005)1/\u001b8fg\u000611/\u001b8fg\u0002\nqaY8tS:,7/\u0001\u0005d_NLg.Z:!)!\ty-!9\u0002d\u0006\u0015\bBBAjS\u0001\u00071\f\u0003\u0004\u0002X&\u0002\ra\u0017\u0005\u0007\u00037L\u0003\u0019A.\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005=\u00171\u001e\u0005\b\u0003[T\u0003\u0019AAh\u0003\u0005!\u0018A\u0002\u0013uS6,7\u000f\u0006\u0003\u0002P\u0006M\bBBA{W\u0001\u00071,\u0001\u0004xK&<\u0007\u000e\u001e\u000b\t\u0003\u001f\fI0a?\u0002~\"A\u00111\u001b\u0017\u0011\u0002\u0003\u00071\f\u0003\u0005\u0002X2\u0002\n\u00111\u0001\\\u0011!\tY\u000e\fI\u0001\u0002\u0004Y\u0016AD2paf$C-\u001a4bk2$He\r\u000b\u0005\u0003c\u0012\u0019\u0001C\u0005\u0002zI\n\t\u00111\u0001\u0002hQ!\u0011q\u0012B\u0004\u0011%\tI\bNA\u0001\u0002\u0004\t\t\b\u0006\u0003\u0002$\t-\u0001\"CA=k\u0005\u0005\t\u0019AA4)\t\t\u0019\u0003\u0006\u0003\u0002\u0010\nE\u0001\"CA=q\u0005\u0005\t\u0019AA9\u0003M\u0019VO\u001a4jG&,g\u000e^*uCRL7\u000f^5d!\r\t\tNO\n\u0006u\te\u00111\u0017\t\n\u00057\u0011\tcW.\\\u0003\u001fl!A!\b\u000b\u0007\t}1+A\u0004sk:$\u0018.\\3\n\t\t\r\"Q\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cDC\u0001B\u000b\u0003\u0015\t\u0007\u000f\u001d7z)!\tyMa\u000b\u0003.\t=\u0002BBAj{\u0001\u00071\f\u0003\u0004\u0002Xv\u0002\ra\u0017\u0005\u0007\u00037l\u0004\u0019A.\u0002\u000fUt\u0017\r\u001d9msR!!Q\u0007B!!\u0015\u0011&q\u0007B\u001e\u0013\r\u0011Id\u0015\u0002\u0007\u001fB$\u0018n\u001c8\u0011\rI\u0013idW.\\\u0013\r\u0011yd\u0015\u0002\u0007)V\u0004H.Z\u001a\t\u0013\t\rc(!AA\u0002\u0005=\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011!\u0011\n\t\u0005\u0003K\u0011Y%\u0003\u0003\u0003N\u0005\u001d\"AB(cU\u0016\u001cG/\u0001\rf[B$\u0018pU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e,\"!a4\u0002-M,hMZ5dS\u0016tGo\u0015;bi&\u001cH/[2G_J$B!a4\u0003X!1\u0011Q^!A\u0002m\u000bA\u0002Z5tiJL'-\u001e;j_:$BA!\u0018\u0003bQ\u0019APa\u0018\t\u000bU\u0014\u00059\u0001<\t\u000f\t\r$\t1\u0001\u0003f\u0005\t\u0001\u000fE\u0002\u0002R\u0006\n1!\u001c7f)\u0011\t\tMa\u001b\t\r1\u001b\u0005\u0019AAh\u0003Ia\u0017n[3mS\"|w\u000e\u001a$v]\u000e$\u0018n\u001c8\u0015\t\tE$1\u0011\n\u0006\u0005g\n&q\u000f\u0004\u0007\u0005k\"\u0005A!\u001d\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\r\te$qPAa\u001b\t\u0011YHC\u0002\u0003~5\u000b\u0001b\u001c9uS6L'0Z\u0005\u0005\u0005\u0003\u0013YH\u0001\u0007ES\u001a4g)\u001e8di&|g\u000e\u0003\u0004M\t\u0002\u0007\u0011q\u001a\u000b\u0007\u0005\u000f\u0013YI!$\u0015\u0007q\u0014I\tC\u0003v\u000b\u0002\u000fa\u000fC\u0003q\u000b\u0002\u00071\fC\u0003t\u000b\u0002\u00071\f\u0006\u0003\u0003\u0012\nM\u0005#\u0002*\u00038\u0005\u0005\u0007\u0002\u0003B\"\r\u0006\u0005\t\u0019\u0001?"
)
public class VonMises implements ContinuousDistr, Moments, Product {
   private double logNormalizer;
   private String toString;
   private final double mu;
   private final double k;
   private final RandBasis rand;
   private final double r;
   private final Rand breeze$stats$distributions$VonMises$$myRandom;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final VonMises x$0) {
      return VonMises$.MODULE$.unapply(x$0);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return VonMises$.MODULE$.likelihoodFunction(stats);
   }

   public static Tuple2 mle(final SufficientStatistic stats) {
      return VonMises$.MODULE$.mle(stats);
   }

   public static VonMises distribution(final Tuple2 p, final RandBasis rand) {
      return VonMises$.MODULE$.distribution(p, rand);
   }

   public static SufficientStatistic sufficientStatisticFor(final double t) {
      return VonMises$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return VonMises$.MODULE$.emptySufficientStatistic();
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

   public double mu() {
      return this.mu;
   }

   public double k() {
      return this.k;
   }

   public double unnormalizedLogPdf(final double theta) {
      return .MODULE$.cos(theta - this.mu()) * this.k();
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = .MODULE$.log(Bessel.i0$.MODULE$.apply$mDDc$sp(this.k(), Bessel$i0$ImplDouble$.MODULE$) * (double)2 * Math.PI);
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

   private double r() {
      return this.r;
   }

   public Rand breeze$stats$distributions$VonMises$$myRandom() {
      return this.breeze$stats$distributions$VonMises$$myRandom;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   private String toString$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.toString = (new StringBuilder(17)).append("VonMises(mu=").append(this.mu()).append(", k=").append(this.k()).append(")").toString();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.toString;
   }

   public String toString() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.toString$lzycompute() : this.toString;
   }

   public double mean() {
      return this.mu();
   }

   public double mode() {
      return this.mean();
   }

   public double variance() {
      return (double)1 - Bessel.i1$.MODULE$.apply(this.k()) / Bessel.i0$.MODULE$.apply$mDDc$sp(this.k(), Bessel$i0$ImplDouble$.MODULE$);
   }

   public double entropy() {
      return -this.k() * Bessel.i1$.MODULE$.apply(this.k()) / Bessel.i0$.MODULE$.apply$mDDc$sp(this.k(), Bessel$i0$ImplDouble$.MODULE$) + .MODULE$.log((Math.PI * 2D) * Bessel.i0$.MODULE$.apply$mDDc$sp(this.k(), Bessel$i0$ImplDouble$.MODULE$));
   }

   public VonMises copy(final double mu, final double k, final RandBasis rand) {
      return new VonMises(mu, k, rand);
   }

   public double copy$default$1() {
      return this.mu();
   }

   public double copy$default$2() {
      return this.k();
   }

   public String productPrefix() {
      return "VonMises";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.mu());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.k());
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
      return x$1 instanceof VonMises;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "mu";
            break;
         case 1:
            var10000 = "k";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.mu()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.k()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label51: {
            boolean var2;
            if (x$1 instanceof VonMises) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               VonMises var4 = (VonMises)x$1;
               if (this.mu() == var4.mu() && this.k() == var4.k() && var4.canEqual(this)) {
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
      return this.breeze$stats$distributions$VonMises$$myRandom().draw$mcD$sp();
   }

   // $FF: synthetic method
   public static final Tuple5 $anonfun$myRandom$2(final VonMises $this, final double v$1, final double u) {
      double z = .MODULE$.cos(Math.PI * u);
      double w = ((double)1.0F + $this.r() * z) / ($this.r() + z);
      double c = $this.k() * ($this.r() - w);
      boolean accept = v$1 < c * ((double)2.0F - c) || v$1 <= c * .MODULE$.exp((double)1.0F - c);
      return new Tuple5(BoxesRunTime.boxToDouble(u), BoxesRunTime.boxToDouble(z), BoxesRunTime.boxToDouble(w), BoxesRunTime.boxToDouble(c), BoxesRunTime.boxToBoolean(accept));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$myRandom$3(final Tuple5 x$1) {
      if (x$1 != null) {
         boolean accept = BoxesRunTime.unboxToBoolean(x$1._5());
         return accept;
      } else {
         throw new MatchError(x$1);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$myRandom$5(final VonMises $this, final double w$1, final double choice) {
      double theta = choice > (double)0.5F ? $this.mu() + .MODULE$.acos(w$1) : $this.mu() - .MODULE$.acos(w$1);
      return new Tuple2.mcDD.sp(choice, theta);
   }

   // $FF: synthetic method
   public static final double $anonfun$myRandom$6(final Tuple2 x$2) {
      if (x$2 != null) {
         double theta = x$2._2$mcD$sp();
         return theta;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final Rand $anonfun$myRandom$1(final VonMises $this, final double v) {
      return $this.rand.uniform().map$mcD$sp((u) -> $anonfun$myRandom$2($this, v, BoxesRunTime.unboxToDouble(u))).withFilter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$myRandom$3(x$1))).flatMap((x$3) -> {
         if (x$3 != null) {
            double w = BoxesRunTime.unboxToDouble(x$3._3());
            Rand var2 = $this.rand.uniform().map$mcD$sp((choice) -> $anonfun$myRandom$5($this, w, BoxesRunTime.unboxToDouble(choice))).map((x$2) -> BoxesRunTime.boxToDouble($anonfun$myRandom$6(x$2)));
            return var2;
         } else {
            throw new MatchError(x$3);
         }
      });
   }

   public VonMises(final double mu, final double k, final RandBasis rand) {
      this.mu = mu;
      this.k = k;
      this.rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      scala.Predef..MODULE$.require(k >= (double)0, () -> "K must be positive");
      scala.Predef..MODULE$.require(mu <= (Math.PI * 2D) && mu >= (double)0, () -> "Mu must be in the range [0,2pi]");
      double tau = (double)1.0F + .MODULE$.sqrt((double)1.0F + (double)4.0F * k * k);
      double rho = (tau - .MODULE$.sqrt((double)2.0F * tau)) / ((double)2.0F * k);
      this.r = ((double)1.0F + rho * rho) / ((double)2 * rho);
      this.breeze$stats$distributions$VonMises$$myRandom = rand.uniform().flatMap$mcD$sp((v) -> $anonfun$myRandom$1(this, BoxesRunTime.unboxToDouble(v)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double n;
      private final double sines;
      private final double cosines;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double n() {
         return this.n;
      }

      public double sines() {
         return this.sines;
      }

      public double cosines() {
         return this.cosines;
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         return new SufficientStatistic(this.n() + t.n(), this.sines() + t.sines(), this.cosines() + t.cosines());
      }

      public SufficientStatistic $times(final double weight) {
         return new SufficientStatistic(weight * this.n(), weight * this.sines(), weight * this.cosines());
      }

      public SufficientStatistic copy(final double n, final double sines, final double cosines) {
         return new SufficientStatistic(n, sines, cosines);
      }

      public double copy$default$1() {
         return this.n();
      }

      public double copy$default$2() {
         return this.sines();
      }

      public double copy$default$3() {
         return this.cosines();
      }

      public String productPrefix() {
         return "SufficientStatistic";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToDouble(this.n());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.sines());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.cosines());
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
               var10000 = "n";
               break;
            case 1:
               var10000 = "sines";
               break;
            case 2:
               var10000 = "cosines";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.sines()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.cosines()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof SufficientStatistic) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  SufficientStatistic var4 = (SufficientStatistic)x$1;
                  if (this.n() == var4.n() && this.sines() == var4.sines() && this.cosines() == var4.cosines() && var4.canEqual(this)) {
                     break label53;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public SufficientStatistic(final double n, final double sines, final double cosines) {
         this.n = n;
         this.sines = sines;
         this.cosines = cosines;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction3 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double n, final double sines, final double cosines) {
         return new SufficientStatistic(n, sines, cosines);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.n()), BoxesRunTime.boxToDouble(x$0.sines()), BoxesRunTime.boxToDouble(x$0.cosines()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
