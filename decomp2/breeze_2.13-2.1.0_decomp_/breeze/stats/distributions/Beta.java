package breeze.stats.distributions;

import breeze.generic.UFunc;
import breeze.linalg.DenseVector;
import breeze.linalg.softmax;
import breeze.linalg.softmax$;
import breeze.numerics.package;
import breeze.numerics.package$digamma$digammaImplDouble$;
import breeze.numerics.package$lgamma$lgammaImplDouble$;
import breeze.optimize.DiffFunction;
import java.io.Serializable;
import org.apache.commons.math3.distribution.BetaDistribution;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dg\u0001\u0002'N\u0001RC\u0001B\u001f\u0001\u0003\u0016\u0004%\ta\u001f\u0005\ty\u0002\u0011\t\u0012)A\u0005?\"AQ\u0010\u0001BK\u0002\u0013\u00051\u0010\u0003\u0005\u007f\u0001\tE\t\u0015!\u0003`\u0011%y\bA!A!\u0002\u0017\t\t\u0001C\u0004\u0002\b\u0001!\t!!\u0003\t\u000f\u0005U\u0001\u0001\"\u0011\u0002\u0018!9\u0011Q\u0004\u0001\u0005B\u0005}\u0001bBA\u0012\u0001\u0011\u0005\u0013Q\u0005\u0005\n\u0003[\u0001\u0001R1A\u0005\u0002mD\u0011\"a\f\u0001\u0005\u0004%I!!\r\t\u0011\u0005e\u0002\u0001)A\u0005\u0003gA\u0011\"a\u000f\u0001\u0005\u0004%I!!\r\t\u0011\u0005u\u0002\u0001)A\u0005\u0003gAq!a\u0010\u0001\t\u0003\n\t\u0005\u0003\u0004\u0002D\u0001!\ta\u001f\u0005\u0007\u0003\u000b\u0002A\u0011A>\t\r\u0005\u001d\u0003\u0001\"\u0001|\u0011\u0019\tI\u0005\u0001C\u0001w\"9\u00111\n\u0001\u0005B\u00055\u0003bBA)\u0001\u0011\u0005\u00131\u000b\u0005\n\u00033\u0002\u0011\u0011!C\u0001\u00037B\u0011\"!\u001a\u0001#\u0003%\t!a\u001a\t\u0013\u0005u\u0004!%A\u0005\u0002\u0005\u001d\u0004\"CA@\u0001\u0005\u0005I\u0011IAA\u0011%\t\u0019\nAA\u0001\n\u0003\t)\nC\u0005\u0002\u001e\u0002\t\t\u0011\"\u0001\u0002 \"I\u00111\u0016\u0001\u0002\u0002\u0013\u0005\u0013Q\u0016\u0005\n\u0003w\u0003\u0011\u0011!C\u0001\u0003{C\u0011\"a2\u0001\u0003\u0003%\t%!3\t\u0013\u00055\u0007!!A\u0005B\u0005=\u0007\"CAi\u0001\u0005\u0005I\u0011IAj\u0011%\t)\u000eAA\u0001\n\u0003\n9nB\u0004\u0002\\6C\t!!8\u0007\r1k\u0005\u0012AAp\u0011\u001d\t9a\tC\u0001\u0003o,a!!?$\u0001\u0005mhA\u0002B\u0001G\u0001\u0013\u0019\u0001C\u0005\u0003\u000e\u0019\u0012)\u001a!C\u0001w\"I!q\u0002\u0014\u0003\u0012\u0003\u0006Ia\u0018\u0005\n\u0005#1#Q3A\u0005\u0002mD\u0011Ba\u0005'\u0005#\u0005\u000b\u0011B0\t\u0013\tUaE!f\u0001\n\u0003Y\b\"\u0003B\fM\tE\t\u0015!\u0003`\u0011\u001d\t9A\nC\u0001\u00053AqA!\t'\t\u0003\u0011\u0019\u0003C\u0004\u0003*\u0019\"\tAa\u000b\t\u0013\u0005ec%!A\u0005\u0002\tE\u0002\"CA3ME\u0005I\u0011AA4\u0011%\tiHJI\u0001\n\u0003\t9\u0007C\u0005\u0003:\u0019\n\n\u0011\"\u0001\u0002h!I\u0011q\u0010\u0014\u0002\u0002\u0013\u0005\u0013\u0011\u0011\u0005\n\u0003'3\u0013\u0011!C\u0001\u0003+C\u0011\"!('\u0003\u0003%\tAa\u000f\t\u0013\u0005-f%!A\u0005B\u00055\u0006\"CA^M\u0005\u0005I\u0011\u0001B \u0011%\t9MJA\u0001\n\u0003\u0012\u0019\u0005C\u0005\u0002N\u001a\n\t\u0011\"\u0011\u0002P\"I\u0011\u0011\u001b\u0014\u0002\u0002\u0013\u0005\u00131\u001b\u0005\n\u0003+4\u0013\u0011!C!\u0005\u000f:\u0011Ba\u0013$\u0003\u0003E\tA!\u0014\u0007\u0013\t\u00051%!A\t\u0002\t=\u0003bBA\u0004}\u0011\u0005!Q\f\u0005\n\u0003#t\u0014\u0011!C#\u0003'D\u0011Ba\u0018?\u0003\u0003%\tI!\u0019\t\u0013\t%d(!A\u0005\u0002\n-\u0004\"\u0003B?}\u0005\u0005I\u0011\u0002B@\u0011\u001d\u00119i\tC\u0001\u0005\u0013CqAa#$\t\u0003\u0011i\tC\u0004\u0003\u0012\u000e\"\tAa%\t\u000f\t]5\u0005\"\u0011\u0003\u001a\"9!QU\u0012\u0005\u0002\t\u001d\u0006\"\u0003B0G\u0005\u0005I\u0011\u0011B\\\u0011%\u0011IgIA\u0001\n\u0003\u0013\t\rC\u0005\u0003~\r\n\t\u0011\"\u0003\u0003\u0000\t!!)\u001a;b\u0015\tqu*A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003!F\u000bQa\u001d;biNT\u0011AU\u0001\u0007EJ,WM_3\u0004\u0001MA\u0001!V.cK\"\\g\u000e\u0005\u0002W36\tqKC\u0001Y\u0003\u0015\u00198-\u00197b\u0013\tQvK\u0001\u0004B]f\u0014VM\u001a\t\u00049v{V\"A'\n\u0005yk%aD\"p]RLg.^8vg\u0012K7\u000f\u001e:\u0011\u0005Y\u0003\u0017BA1X\u0005\u0019!u.\u001e2mKB!AlY0`\u0013\t!WJA\u0004N_6,g\u000e^:\u0011\u0005q3\u0017BA4N\u0005\u0019A\u0015m]\"eMB\u0011A,[\u0005\u0003U6\u0013Q\u0002S1t\u0013:4XM]:f\u0007\u00124\u0007C\u0001,m\u0013\tiwKA\u0004Qe>$Wo\u0019;\u0011\u0005=<hB\u00019v\u001d\t\tH/D\u0001s\u0015\t\u00198+\u0001\u0004=e>|GOP\u0005\u00021&\u0011aoV\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0018P\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002w/\u0006\t\u0011-F\u0001`\u0003\t\t\u0007%A\u0001c\u0003\t\u0011\u0007%\u0001\u0003sC:$\u0007c\u0001/\u0002\u0004%\u0019\u0011QA'\u0003\u0013I\u000bg\u000e\u001a\"bg&\u001c\u0018A\u0002\u001fj]&$h\b\u0006\u0004\u0002\f\u0005E\u00111\u0003\u000b\u0005\u0003\u001b\ty\u0001\u0005\u0002]\u0001!1qP\u0002a\u0002\u0003\u0003AQA\u001f\u0004A\u0002}CQ! \u0004A\u0002}\u000b!#\u001e8o_Jl\u0017\r\\5{K\u0012dun\u001a)eMR\u0019q,!\u0007\t\r\u0005mq\u00011\u0001`\u0003\u0005A\u0018a\u00019eMR\u0019q,!\t\t\r\u0005m\u0001\u00021\u0001`\u0003-\u0001(o\u001c2bE&d\u0017\u000e^=\u0015\u000b}\u000b9#!\u000b\t\r\u0005m\u0011\u00021\u0001`\u0011\u0019\tY#\u0003a\u0001?\u0006\t\u00110A\u0007m_\u001etuN]7bY&TXM]\u0001\u0007C\u001e\u000bW.\\1\u0016\u0005\u0005M\u0002c\u0001/\u00026%\u0019\u0011qG'\u0003\u000b\u001d\u000bW.\\1\u0002\u000f\u0005<\u0015-\\7bA\u00051!mR1n[\u0006\fqAY$b[6\f\u0007%\u0001\u0003ee\u0006<H#A0\u0002\t5,\u0017M\\\u0001\tm\u0006\u0014\u0018.\u00198dK\u0006!Qn\u001c3f\u0003\u001d)g\u000e\u001e:paf\f1a\u00193g)\ry\u0016q\n\u0005\u0007\u00037!\u0002\u0019A0\u0002\u0015%tg/\u001a:tK\u000e#g\rF\u0002`\u0003+Ba!a\u0016\u0016\u0001\u0004y\u0016!\u00019\u0002\t\r|\u0007/\u001f\u000b\u0007\u0003;\n\t'a\u0019\u0015\t\u00055\u0011q\f\u0005\u0007\u007fZ\u0001\u001d!!\u0001\t\u000fi4\u0002\u0013!a\u0001?\"9QP\u0006I\u0001\u0002\u0004y\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003SR3aXA6W\t\ti\u0007\u0005\u0003\u0002p\u0005eTBAA9\u0015\u0011\t\u0019(!\u001e\u0002\u0013Ut7\r[3dW\u0016$'bAA</\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005m\u0014\u0011\u000f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005\r\u0005\u0003BAC\u0003\u001fk!!a\"\u000b\t\u0005%\u00151R\u0001\u0005Y\u0006twM\u0003\u0002\u0002\u000e\u0006!!.\u0019<b\u0013\u0011\t\t*a\"\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t9\nE\u0002W\u00033K1!a'X\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\t+a*\u0011\u0007Y\u000b\u0019+C\u0002\u0002&^\u00131!\u00118z\u0011%\tIkGA\u0001\u0002\u0004\t9*A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003_\u0003b!!-\u00028\u0006\u0005VBAAZ\u0015\r\t)lV\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA]\u0003g\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011qXAc!\r1\u0016\u0011Y\u0005\u0004\u0003\u0007<&a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003Sk\u0012\u0011!a\u0001\u0003C\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111QAf\u0011%\tIKHA\u0001\u0002\u0004\t9*\u0001\u0005iCND7i\u001c3f)\t\t9*\u0001\u0005u_N#(/\u001b8h)\t\t\u0019)\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u007f\u000bI\u000eC\u0005\u0002*\u0006\n\t\u00111\u0001\u0002\"\u0006!!)\u001a;b!\ta6e\u0005\u0005$+\u0006\u0005\u0018q]Aw!\u0019a\u00161]A\u0007?&\u0019\u0011Q]'\u0003#\u0015C\bo\u001c8f]RL\u0017\r\u001c$b[&d\u0017\u0010\u0005\u0004]\u0003S|\u0016QB\u0005\u0004\u0003Wl%aI\"p]RLg.^8vg\u0012K7\u000f\u001e:jEV$\u0018n\u001c8V\rVt7\r\u0015:pm&$WM\u001d\t\u0005\u0003_\f)0\u0004\u0002\u0002r*!\u00111_AF\u0003\tIw.C\u0002y\u0003c$\"!!8\u0003\u0013A\u000b'/Y7fi\u0016\u0014\b#\u0002,\u0002~~{\u0016bAA\u0000/\n1A+\u001e9mKJ\u00121cU;gM&\u001c\u0017.\u001a8u'R\fG/[:uS\u000e\u001cbAJ+\u0003\u0006-t\u0007#\u0002/\u0003\b\t%\u0011b\u0001B\u0001\u001bB\u0019!1\u0002\u0014\u000e\u0003\r\n\u0011A\\\u0001\u0003]\u0002\nq!\\3b]2{w-\u0001\u0005nK\u0006tGj\\4!\u0003%iW-\u00198M_\u001e\fT*\u0001\u0006nK\u0006tGj\\42\u001b\u0002\"\u0002B!\u0003\u0003\u001c\tu!q\u0004\u0005\u0007\u0005\u001bi\u0003\u0019A0\t\r\tEQ\u00061\u0001`\u0011\u0019\u0011)\"\fa\u0001?\u00061A\u0005^5nKN$BA!\u0003\u0003&!1!q\u0005\u0018A\u0002}\u000baa^3jO\"$\u0018!\u0002\u0013qYV\u001cH\u0003\u0002B\u0005\u0005[AqAa\f0\u0001\u0004\u0011I!A\u0001u)!\u0011IAa\r\u00036\t]\u0002\u0002\u0003B\u0007aA\u0005\t\u0019A0\t\u0011\tE\u0001\u0007%AA\u0002}C\u0001B!\u00061!\u0003\u0005\raX\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134)\u0011\t\tK!\u0010\t\u0013\u0005%f'!AA\u0002\u0005]E\u0003BA`\u0005\u0003B\u0011\"!+9\u0003\u0003\u0005\r!!)\u0015\t\u0005\r%Q\t\u0005\n\u0003SK\u0014\u0011!a\u0001\u0003/#B!a0\u0003J!I\u0011\u0011\u0016\u001f\u0002\u0002\u0003\u0007\u0011\u0011U\u0001\u0014'V4g-[2jK:$8\u000b^1uSN$\u0018n\u0019\t\u0004\u0005\u0017q4#\u0002 \u0003R\u00055\b#\u0003B*\u00053zvl\u0018B\u0005\u001b\t\u0011)FC\u0002\u0003X]\u000bqA];oi&lW-\u0003\u0003\u0003\\\tU#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogQ\u0011!QJ\u0001\u0006CB\u0004H.\u001f\u000b\t\u0005\u0013\u0011\u0019G!\u001a\u0003h!1!QB!A\u0002}CaA!\u0005B\u0001\u0004y\u0006B\u0002B\u000b\u0003\u0002\u0007q,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t5$\u0011\u0010\t\u0006-\n=$1O\u0005\u0004\u0005c:&AB(qi&|g\u000e\u0005\u0004W\u0005kzvlX\u0005\u0004\u0005o:&A\u0002+va2,7\u0007C\u0005\u0003|\t\u000b\t\u00111\u0001\u0003\n\u0005\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t\u0005\u0005\u0003BAC\u0005\u0007KAA!\"\u0002\b\n1qJ\u00196fGR\f\u0001$Z7qif\u001cVO\u001a4jG&,g\u000e^*uCRL7\u000f^5d+\t\u0011I!\u0001\ftk\u001a4\u0017nY5f]R\u001cF/\u0019;jgRL7MR8s)\u0011\u0011IAa$\t\r\t=R\t1\u0001`\u0003\riG.\u001a\u000b\u0005\u0003w\u0014)\n\u0003\u0004Q\r\u0002\u0007!\u0011B\u0001\rI&\u001cHO]5ckRLwN\u001c\u000b\u0005\u00057\u0013y\n\u0006\u0003\u0002\u000e\tu\u0005BB@H\u0001\b\t\t\u0001C\u0004\u0003\"\u001e\u0003\rAa)\u0002\u0005\u0005\u0014\u0007c\u0001B\u0006K\u0005\u0011B.[6fY&Dwn\u001c3Gk:\u001cG/[8o)\u0011\u0011IK!.\u0011\r\t-&\u0011WA~\u001b\t\u0011iKC\u0002\u00030F\u000b\u0001b\u001c9uS6L'0Z\u0005\u0005\u0005g\u0013iK\u0001\u0007ES\u001a4g)\u001e8di&|g\u000e\u0003\u0004Q\u0011\u0002\u0007!\u0011\u0002\u000b\u0007\u0005s\u0013iLa0\u0015\t\u00055!1\u0018\u0005\u0007\u007f&\u0003\u001d!!\u0001\t\u000biL\u0005\u0019A0\t\u000buL\u0005\u0019A0\u0015\t\t\r'Q\u0019\t\u0006-\n=\u00141 \u0005\n\u0005wR\u0015\u0011!a\u0001\u0003\u001b\u0001"
)
public class Beta implements ContinuousDistr, Moments, HasCdf, HasInverseCdf, Product {
   private double logNormalizer;
   private final double a;
   private final double b;
   public final RandBasis breeze$stats$distributions$Beta$$rand;
   private final Gamma breeze$stats$distributions$Beta$$aGamma;
   private final Gamma breeze$stats$distributions$Beta$$bGamma;
   private double normalizer;
   private volatile byte bitmap$0;

   public static Option unapply(final Beta x$0) {
      return Beta$.MODULE$.unapply(x$0);
   }

   public static DiffFunction likelihoodFunction(final SufficientStatistic stats) {
      return Beta$.MODULE$.likelihoodFunction(stats);
   }

   public static Beta distribution(final Tuple2 ab, final RandBasis rand) {
      return Beta$.MODULE$.distribution(ab, rand);
   }

   public static Tuple2 mle(final SufficientStatistic stats) {
      return Beta$.MODULE$.mle(stats);
   }

   public static SufficientStatistic sufficientStatisticFor(final double t) {
      return Beta$.MODULE$.sufficientStatisticFor(t);
   }

   public static SufficientStatistic emptySufficientStatistic() {
      return Beta$.MODULE$.emptySufficientStatistic();
   }

   public static ContinuousDistributionUFuncProvider.ContinuousDistrUFuncWrapper ContinuousDistrUFuncWrapper(final ContinuousDistr dist) {
      return Beta$.MODULE$.ContinuousDistrUFuncWrapper(dist);
   }

   public static ContinuousDistributionUFuncProvider.basicImpl$ basicImpl() {
      return Beta$.MODULE$.basicImpl();
   }

   public static Object withSink(final Object s) {
      return Beta$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return Beta$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return Beta$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return Beta$.MODULE$.inPlace(v, impl);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
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

   public double a() {
      return this.a;
   }

   public double b() {
      return this.b;
   }

   public double unnormalizedLogPdf(final double x) {
      .MODULE$.require(x >= (double)0);
      .MODULE$.require(x <= (double)1);
      return (this.a() - (double)1) * scala.math.package..MODULE$.log(x) + (this.b() - (double)1) * scala.math.package..MODULE$.log((double)1 - x);
   }

   public double pdf(final double x) {
      .MODULE$.require(x >= (double)0);
      .MODULE$.require(x <= (double)1);
      double var3;
      if ((double)0.0F == x) {
         var3 = this.a() > (double)1 ? (double)0.0F : (this.a() == (double)1 ? this.normalizer() : Double.POSITIVE_INFINITY);
      } else if ((double)1.0F == x) {
         var3 = this.b() > (double)1 ? (double)0.0F : (this.b() == (double)1 ? this.normalizer() : Double.POSITIVE_INFINITY);
      } else {
         var3 = scala.math.package..MODULE$.exp(this.logPdf(BoxesRunTime.boxToDouble(x)));
      }

      return var3;
   }

   public double probability(final double x, final double y) {
      return (new BetaDistribution(this.a(), this.b())).probability(x, y);
   }

   private double logNormalizer$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.logNormalizer = package.lgamma$.MODULE$.apply$mDDc$sp(this.a(), package$lgamma$lgammaImplDouble$.MODULE$) + package.lgamma$.MODULE$.apply$mDDc$sp(this.b(), package$lgamma$lgammaImplDouble$.MODULE$) - package.lgamma$.MODULE$.apply$mDDc$sp(this.a() + this.b(), package$lgamma$lgammaImplDouble$.MODULE$);
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

   public Gamma breeze$stats$distributions$Beta$$aGamma() {
      return this.breeze$stats$distributions$Beta$$aGamma;
   }

   public Gamma breeze$stats$distributions$Beta$$bGamma() {
      return this.breeze$stats$distributions$Beta$$bGamma;
   }

   public double draw() {
      return this.draw$mcD$sp();
   }

   public double mean() {
      return this.a() / (this.a() + this.b());
   }

   public double variance() {
      return this.a() * this.b() / ((this.a() + this.b()) * (this.a() + this.b()) * (this.a() + this.b() + (double)1));
   }

   public double mode() {
      return (this.a() - (double)1) / (this.a() + this.b() - (double)2);
   }

   public double entropy() {
      return this.logNormalizer() - (this.a() - (double)1) * package.digamma$.MODULE$.apply$mDDc$sp(this.a(), package$digamma$digammaImplDouble$.MODULE$) - (this.b() - (double)1) * package.digamma$.MODULE$.apply$mDDc$sp(this.b(), package$digamma$digammaImplDouble$.MODULE$) + (this.a() + this.b() - (double)2) * package.digamma$.MODULE$.apply$mDDc$sp(this.a() + this.b(), package$digamma$digammaImplDouble$.MODULE$);
   }

   public double cdf(final double x) {
      return (new BetaDistribution(this.a(), this.b())).cumulativeProbability(x);
   }

   public double inverseCdf(final double p) {
      return (new BetaDistribution(this.a(), this.b())).inverseCumulativeProbability(p);
   }

   public Beta copy(final double a, final double b, final RandBasis rand) {
      return new Beta(a, b, rand);
   }

   public double copy$default$1() {
      return this.a();
   }

   public double copy$default$2() {
      return this.b();
   }

   public String productPrefix() {
      return "Beta";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.a());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.b());
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
      return x$1 instanceof Beta;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "a";
            break;
         case 1:
            var10000 = "b";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.a()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.b()));
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
            if (x$1 instanceof Beta) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Beta var4 = (Beta)x$1;
               if (this.a() == var4.a() && this.b() == var4.b() && var4.canEqual(this)) {
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
      if (this.a() <= (double)0.5F && this.b() <= (double)0.5F) {
         double logX;
         double logSum;
         do {
            double U = this.breeze$stats$distributions$Beta$$rand.uniform().draw$mcD$sp();
            double V = this.breeze$stats$distributions$Beta$$rand.uniform().draw$mcD$sp();
            if (!(U > (double)0) || !(V > (double)0)) {
               throw new RuntimeException("Underflow!");
            }

            logX = scala.math.package..MODULE$.log(U) / this.a();
            double logY = scala.math.package..MODULE$.log(V) / this.b();
            logSum = softmax$.MODULE$.apply$mDDDc$sp(logX, logY, softmax.implDoubleDouble$.MODULE$);
         } while(!(logSum <= (double)0.0F));

         return scala.math.package..MODULE$.exp(logX - logSum);
      } else if (this.a() <= (double)1 && this.b() <= (double)1) {
         double X;
         double sum;
         do {
            double U = this.breeze$stats$distributions$Beta$$rand.uniform().draw$mcD$sp();
            double V = this.breeze$stats$distributions$Beta$$rand.uniform().draw$mcD$sp();
            if (!(U > (double)0) || !(V > (double)0)) {
               throw new RuntimeException("Underflow!");
            }

            X = scala.math.package..MODULE$.pow(U, (double)1.0F / this.a());
            double Y = scala.math.package..MODULE$.pow(V, (double)1.0F / this.b());
            sum = X + Y;
         } while(!(sum <= (double)1.0F));

         return X / sum;
      } else {
         double ad = this.breeze$stats$distributions$Beta$$aGamma().draw();
         double bd = this.breeze$stats$distributions$Beta$$bGamma().draw();
         return ad / (ad + bd);
      }
   }

   public Beta(final double a, final double b, final RandBasis rand) {
      this.a = a;
      this.b = b;
      this.breeze$stats$distributions$Beta$$rand = rand;
      Density.$init$(this);
      Rand.$init$(this);
      ContinuousDistr.$init$(this);
      Product.$init$(this);
      .MODULE$.require(a > (double)0.0F);
      .MODULE$.require(b > (double)0.0F);
      this.breeze$stats$distributions$Beta$$aGamma = new Gamma(a, (double)1.0F, rand);
      this.breeze$stats$distributions$Beta$$bGamma = new Gamma(b, (double)1.0F, rand);
   }

   public static class SufficientStatistic implements breeze.stats.distributions.SufficientStatistic, Product, Serializable {
      private final double n;
      private final double meanLog;
      private final double meanLog1M;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public double n() {
         return this.n;
      }

      public double meanLog() {
         return this.meanLog;
      }

      public double meanLog1M() {
         return this.meanLog1M;
      }

      public SufficientStatistic $times(final double weight) {
         return new SufficientStatistic(this.n() * weight, this.meanLog(), this.meanLog1M());
      }

      public SufficientStatistic $plus(final SufficientStatistic t) {
         double delta = t.meanLog() - this.meanLog();
         double newMeanLog = this.meanLog() + delta * (t.n() / (t.n() + this.n()));
         double logDelta = t.meanLog1M() - this.meanLog1M();
         double newMeanLog1M = this.meanLog1M() + logDelta * (t.n() / (t.n() + this.n()));
         return new SufficientStatistic(this.n() + t.n(), newMeanLog, newMeanLog1M);
      }

      public SufficientStatistic copy(final double n, final double meanLog, final double meanLog1M) {
         return new SufficientStatistic(n, meanLog, meanLog1M);
      }

      public double copy$default$1() {
         return this.n();
      }

      public double copy$default$2() {
         return this.meanLog();
      }

      public double copy$default$3() {
         return this.meanLog1M();
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
               var10000 = BoxesRunTime.boxToDouble(this.meanLog());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.meanLog1M());
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
               var10000 = "meanLog";
               break;
            case 2:
               var10000 = "meanLog1M";
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
         var1 = Statics.mix(var1, Statics.doubleHash(this.meanLog()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.meanLog1M()));
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
                  if (this.n() == var4.n() && this.meanLog() == var4.meanLog() && this.meanLog1M() == var4.meanLog1M() && var4.canEqual(this)) {
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

      public SufficientStatistic(final double n, final double meanLog, final double meanLog1M) {
         this.n = n;
         this.meanLog = meanLog;
         this.meanLog1M = meanLog1M;
         Product.$init$(this);
      }
   }

   public static class SufficientStatistic$ extends AbstractFunction3 implements Serializable {
      public static final SufficientStatistic$ MODULE$ = new SufficientStatistic$();

      public final String toString() {
         return "SufficientStatistic";
      }

      public SufficientStatistic apply(final double n, final double meanLog, final double meanLog1M) {
         return new SufficientStatistic(n, meanLog, meanLog1M);
      }

      public Option unapply(final SufficientStatistic x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.n()), BoxesRunTime.boxToDouble(x$0.meanLog()), BoxesRunTime.boxToDouble(x$0.meanLog1M()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SufficientStatistic$.class);
      }
   }
}
