package breeze.stats.mcmc;

import breeze.linalg.DenseVector;
import breeze.stats.distributions.Process;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.RandBasis;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005d!\u0002\u0011\"\u0003\u0003A\u0003\u0002\u0003%\u0001\u0005\u0003\u0005\u000b\u0011B%\t\u0011=\u0003!\u0011!Q\u0001\nQB\u0001\u0002\u0015\u0001\u0003\u0002\u0003\u0006I!\u0015\u0005\t)\u0002\u0011\t\u0011)A\u0005#\"AQ\u000b\u0001BC\u0002\u0013\ra\u000b\u0003\u0005[\u0001\t\u0005\t\u0015!\u0003X\u0011\u0015Y\u0006\u0001\"\u0001]\u0011\u001d!\u0007\u00011A\u0005\n\u0015DqA\u001a\u0001A\u0002\u0013%q\r\u0003\u0004n\u0001\u0001\u0006K\u0001\u000e\u0005\b]\u0002\u0001\r\u0011\"\u0003p\u0011\u001d\u0019\b\u00011A\u0005\nQDaA\u001e\u0001!B\u0013\u0001\bbB<\u0001\u0001\u0004%Ia\u001c\u0005\bq\u0002\u0001\r\u0011\"\u0003z\u0011\u0019Y\b\u0001)Q\u0005a\"9A\u0010\u0001a\u0001\n\u0013y\u0007bB?\u0001\u0001\u0004%IA \u0005\b\u0003\u0003\u0001\u0001\u0015)\u0003q\u0011\u001d\t\u0019\u0001\u0001C\u0001\u0003\u000bAa!a\u0003\u0001\t\u0003y\u0007BBA\u0007\u0001\u0011\u0005q\u000e\u0003\u0004\u0002\u0010\u0001!\ta\u001c\u0005\b\u0003#\u0001A\u0011BA\n\u0011\u001d\t)\u0002\u0001C\u0001\u0003'9\u0011\"a\u0006\"\u0003\u0003E\t!!\u0007\u0007\u0011\u0001\n\u0013\u0011!E\u0001\u00037AaaW\u000e\u0005\u0002\u00055\u0002\"CA\u00187E\u0005I\u0011AA\u0019\u0011%\tYeGI\u0001\n\u0003\ti\u0005C\u0005\u0002Rm\t\t\u0011\"\u0003\u0002T\t1\")Y:f\u001b\u0016$(o\u001c9pY&\u001c\b*Y:uS:<7O\u0003\u0002#G\u0005!QnY7d\u0015\t!S%A\u0003ti\u0006$8OC\u0001'\u0003\u0019\u0011'/Z3{K\u000e\u0001QCA\u00157'\u0015\u0001!\u0006M F!\tYc&D\u0001-\u0015\u0005i\u0013!B:dC2\f\u0017BA\u0018-\u0005\u0019\te.\u001f*fMB\u0019\u0011G\r\u001b\u000e\u0003\u0005J!aM\u0011\u0003%5+GO]8q_2L7\u000fS1ti&twm\u001d\t\u0003kYb\u0001\u0001B\u00038\u0001\t\u0007\u0001HA\u0001U#\tID\b\u0005\u0002,u%\u00111\b\f\u0002\b\u001d>$\b.\u001b8h!\tYS(\u0003\u0002?Y\t\u0019\u0011I\\=\u0011\u0007\u0001\u001bE'D\u0001B\u0015\t\u00115%A\u0007eSN$(/\u001b2vi&|gn]\u0005\u0003\t\u0006\u0013q\u0001\u0015:pG\u0016\u001c8\u000f\u0005\u00022\r&\u0011q)\t\u0002\u0011)J\f7m[:Ti\u0006$\u0018n\u001d;jGN\f\u0011\u0003\\8h\u0019&\\W\r\\5i_>$g)\u001e8d!\u0011Y#\n\u000e'\n\u0005-c#!\u0003$v]\u000e$\u0018n\u001c82!\tYS*\u0003\u0002OY\t1Ai\\;cY\u0016\fA!\u001b8ji\u00061!-\u001e:o\u0013:\u0004\"a\u000b*\n\u0005Mc#aA%oi\u0006IAM]8q\u0007>,h\u000e^\u0001\u0005e\u0006tG-F\u0001X!\t\u0001\u0005,\u0003\u0002Z\u0003\nI!+\u00198e\u0005\u0006\u001c\u0018n]\u0001\u0006e\u0006tG\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bu\u0003\u0017MY2\u0015\u0005y{\u0006cA\u0019\u0001i!)Qk\u0002a\u0002/\")\u0001j\u0002a\u0001\u0013\")qj\u0002a\u0001i!9\u0001k\u0002I\u0001\u0002\u0004\t\u0006b\u0002+\b!\u0003\u0005\r!U\u0001\u0005Y\u0006\u001cH/F\u00015\u0003!a\u0017m\u001d;`I\u0015\fHC\u00015l!\tY\u0013.\u0003\u0002kY\t!QK\\5u\u0011\u001da\u0017\"!AA\u0002Q\n1\u0001\u001f\u00132\u0003\u0015a\u0017m\u001d;!\u0003-\t7mY3qi\u0006t7-Z:\u0016\u0003A\u0004\"aK9\n\u0005Id#\u0001\u0002'p]\u001e\fq\"Y2dKB$\u0018M\\2fg~#S-\u001d\u000b\u0003QVDq\u0001\u001c\u0007\u0002\u0002\u0003\u0007\u0001/\u0001\u0007bG\u000e,\u0007\u000f^1oG\u0016\u001c\b%\u0001\u0006u_R\fGnQ8v]R\fa\u0002^8uC2\u001cu.\u001e8u?\u0012*\u0017\u000f\u0006\u0002iu\"9AnDA\u0001\u0002\u0004\u0001\u0018a\u0003;pi\u0006d7i\\;oi\u0002\n!#Y2dKB$\u0018M\\2f\u0003\n|g/Z(oK\u00061\u0012mY2faR\fgnY3BE>4Xm\u00148f?\u0012*\u0017\u000f\u0006\u0002i\u007f\"9ANEA\u0001\u0002\u0004\u0001\u0018aE1dG\u0016\u0004H/\u00198dK\u0006\u0013wN^3P]\u0016\u0004\u0013!\u00047pO2K7.\u001a7jQ>|G\rF\u0002M\u0003\u000fAa!!\u0003\u0015\u0001\u0004!\u0014!\u0001=\u0002\u001b\u0005\u0014wN^3P]\u0016\u001cu.\u001e8u\u0003\u0015!x\u000e^1m\u0003=\t7mY3qi\u0006t7-Z\"pk:$\u0018aB4fi:+\u0007\u0010\u001e\u000b\u0002i\u0005!AM]1x\u0003Y\u0011\u0015m]3NKR\u0014x\u000e]8mSND\u0015m\u001d;j]\u001e\u001c\bCA\u0019\u001c'\u0011Y\"&!\b\u0011\t\u0005}\u0011\u0011F\u0007\u0003\u0003CQA!a\t\u0002&\u0005\u0011\u0011n\u001c\u0006\u0003\u0003O\tAA[1wC&!\u00111FA\u0011\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tI\"A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeM\u000b\u0005\u0003g\tI%\u0006\u0002\u00026)\u001a\u0011+a\u000e,\u0005\u0005e\u0002\u0003BA\u001e\u0003\u000bj!!!\u0010\u000b\t\u0005}\u0012\u0011I\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u0011-\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000f\niDA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$QaN\u000fC\u0002a\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"T\u0003BA\u001a\u0003\u001f\"Qa\u000e\u0010C\u0002a\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u0016\u0011\t\u0005]\u0013QL\u0007\u0003\u00033RA!a\u0017\u0002&\u0005!A.\u00198h\u0013\u0011\ty&!\u0017\u0003\r=\u0013'.Z2u\u0001"
)
public abstract class BaseMetropolisHastings implements MetropolisHastings, Process, TracksStatistics {
   private final Function1 logLikelihoodFunc;
   private final int dropCount;
   private final RandBasis rand;
   private Object last;
   private long acceptances;
   private long totalCount;
   private long acceptanceAboveOne;

   public static int $lessinit$greater$default$4() {
      return BaseMetropolisHastings$.MODULE$.$lessinit$greater$default$4();
   }

   public static int $lessinit$greater$default$3() {
      return BaseMetropolisHastings$.MODULE$.$lessinit$greater$default$3();
   }

   public long rejectionCount() {
      return TracksStatistics.rejectionCount$(this);
   }

   public double aboveOneFrac() {
      return TracksStatistics.aboveOneFrac$(this);
   }

   public double rejectionFrac() {
      return TracksStatistics.rejectionFrac$(this);
   }

   public Tuple2 step() {
      return Process.step$(this);
   }

   public Iterator steps() {
      return Process.steps$(this);
   }

   public double likelihood(final Object x) {
      return MetropolisHastings.likelihood$(this, x);
   }

   public double likelihoodRatio(final Object start, final Object end) {
      return MetropolisHastings.likelihoodRatio$(this, start, end);
   }

   public double logLikelihoodRatio(final Object start, final Object end) {
      return MetropolisHastings.logLikelihoodRatio$(this, start, end);
   }

   public double nextDouble() {
      return MetropolisHastings.nextDouble$(this);
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

   public RandBasis rand() {
      return this.rand;
   }

   private Object last() {
      return this.last;
   }

   private void last_$eq(final Object x$1) {
      this.last = x$1;
   }

   private long acceptances() {
      return this.acceptances;
   }

   private void acceptances_$eq(final long x$1) {
      this.acceptances = x$1;
   }

   private long totalCount() {
      return this.totalCount;
   }

   private void totalCount_$eq(final long x$1) {
      this.totalCount = x$1;
   }

   private long acceptanceAboveOne() {
      return this.acceptanceAboveOne;
   }

   private void acceptanceAboveOne_$eq(final long x$1) {
      this.acceptanceAboveOne = x$1;
   }

   public double logLikelihood(final Object x) {
      return BoxesRunTime.unboxToDouble(this.logLikelihoodFunc.apply(x));
   }

   public long aboveOneCount() {
      return this.acceptanceAboveOne();
   }

   public long total() {
      return this.totalCount();
   }

   public long acceptanceCount() {
      return this.acceptances();
   }

   private Object getNext() {
      this.totalCount_$eq(this.totalCount() + 1L);
      Object maybeNext = this.proposalDraw(this.last());
      double logAcceptanceRatio = this.logLikelihoodRatio(this.last(), maybeNext);
      Object var10000;
      if (logAcceptanceRatio > (double)0.0F) {
         this.last_$eq(maybeNext);
         this.acceptanceAboveOne_$eq(this.acceptanceAboveOne() + 1L);
         var10000 = maybeNext;
      } else if (.MODULE$.log(this.nextDouble()) < logAcceptanceRatio) {
         this.last_$eq(maybeNext);
         this.acceptances_$eq(this.acceptances() + 1L);
         var10000 = maybeNext;
      } else {
         var10000 = this.last();
      }

      return var10000;
   }

   public Object draw() {
      Object var10000;
      if (this.dropCount == 0) {
         var10000 = this.getNext();
      } else {
         int index$macro$2 = 0;

         for(int limit$macro$4 = this.dropCount; index$macro$2 < limit$macro$4; ++index$macro$2) {
            this.getNext();
         }

         var10000 = this.getNext();
      }

      return var10000;
   }

   public BaseMetropolisHastings(final Function1 logLikelihoodFunc, final Object init, final int burnIn, final int dropCount, final RandBasis rand) {
      this.logLikelihoodFunc = logLikelihoodFunc;
      this.dropCount = dropCount;
      this.rand = rand;
      Rand.$init$(this);
      MetropolisHastings.$init$(this);
      Process.$init$(this);
      TracksStatistics.$init$(this);
      this.last = init;
      this.acceptances = 0L;
      this.totalCount = 0L;
      this.acceptanceAboveOne = 0L;
      int index$macro$2 = 0;

      for(int limit$macro$4 = burnIn; index$macro$2 < limit$macro$4; ++index$macro$2) {
         this.getNext();
      }

   }
}
