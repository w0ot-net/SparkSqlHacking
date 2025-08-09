package org.apache.spark.mllib.evaluation;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.evaluation.binary.BinaryClassificationMetricComputer;
import org.apache.spark.mllib.evaluation.binary.BinaryConfusionMatrix;
import org.apache.spark.mllib.evaluation.binary.BinaryConfusionMatrixImpl;
import org.apache.spark.mllib.evaluation.binary.BinaryLabelCounter;
import org.apache.spark.mllib.evaluation.binary.BinaryLabelCounter$;
import org.apache.spark.mllib.evaluation.binary.FMeasure;
import org.apache.spark.mllib.evaluation.binary.FalsePositiveRate$;
import org.apache.spark.mllib.evaluation.binary.Precision$;
import org.apache.spark.mllib.evaluation.binary.Recall$;
import org.apache.spark.rdd.OrderedRDDFunctions;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005g\u0001B\u000f\u001f\u0001%B\u0001B\u000e\u0001\u0003\u0006\u0004%\ta\u000e\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005q!AQ\u000b\u0001BC\u0002\u0013\u0005a\u000b\u0003\u0005\\\u0001\t\u0005\t\u0015!\u0003X\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u001da\u0007A1A\u0005\n5DaA\u001e\u0001!\u0002\u0013q\u0007\"B/\u0001\t\u00039\bBB/\u0001\t\u0003\u0001S\u0010C\u0004\u0002(\u0001!\t!!\u000b\t\u000f\u0005M\u0002\u0001\"\u0001\u00026!9\u00111\b\u0001\u0005\u0002\u0005u\u0002bBA!\u0001\u0011\u0005\u00111\t\u0005\b\u0003\u000f\u0002A\u0011AA\u001f\u0011\u001d\tY\u0005\u0001C\u0001\u0003\u0007Bq!a\u0014\u0001\t\u0003\t\t\u0006C\u0004\u0002P\u0001!\t!!\u0010\t\u000f\u0005m\u0003\u0001\"\u0001\u0002>!9\u0011q\f\u0001\u0005\u0002\u0005u\u0002\u0002DA2\u0001A\u0005\tr1Q\u0005\n\u0005\u0015\u0004BCAB\u0001!\u0015\r\u0011\"\u0003\u0002\u0006\"Q\u0011q\u0011\u0001\t\u0006\u0004%I!!#\t\u000f\u0005-\u0005\u0001\"\u0003\u0002\u000e\"9\u00111\u0012\u0001\u0005\n\u0005eu!CAR=\u0005\u0005\t\u0012AAS\r!ib$!A\t\u0002\u0005\u001d\u0006BB/\u001b\t\u0003\tI\u000bC\u0005\u0002,j\t\n\u0011\"\u0001\u0002.\nY\")\u001b8bef\u001cE.Y:tS\u001aL7-\u0019;j_:lU\r\u001e:jGNT!a\b\u0011\u0002\u0015\u00154\u0018\r\\;bi&|gN\u0003\u0002\"E\u0005)Q\u000e\u001c7jE*\u00111\u0005J\u0001\u0006gB\f'o\u001b\u0006\u0003K\u0019\na!\u00199bG\",'\"A\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001Q\u0003\u0007\u0005\u0002,]5\tAFC\u0001.\u0003\u0015\u00198-\u00197b\u0013\tyCF\u0001\u0004B]f\u0014VM\u001a\t\u0003cQj\u0011A\r\u0006\u0003g\t\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003kI\u0012q\u0001T8hO&tw-\u0001\btG>\u0014X-\u00118e\u0019\u0006\u0014W\r\\:\u0016\u0003a\u0002$!O!\u0011\u0007ijt(D\u0001<\u0015\ta$%A\u0002sI\u0012L!AP\u001e\u0003\u0007I#E\t\u0005\u0002A\u00032\u0001A!\u0003\"\u0003\u0003\u0003\u0005\tQ!\u0001N\u0005\ryF%M\u0001\u0010g\u000e|'/Z!oI2\u000b'-\u001a7tA!\u001a!!R&\u0011\u0005\u0019KU\"A$\u000b\u0005!\u0013\u0013AC1o]>$\u0018\r^5p]&\u0011!j\u0012\u0002\u0006'&t7-Z\u0011\u0002\u0019\u0006)\u0011GL\u001a/aE\u0011a*\u0015\t\u0003W=K!\u0001\u0015\u0017\u0003\u000f9{G\u000f[5oOB\u00111FU\u0005\u0003'2\u0012q\u0001\u0015:pIV\u001cG\u000fK\u0002\u0002\u000b.\u000bqA\\;n\u0005&t7/F\u0001X!\tY\u0003,\u0003\u0002ZY\t\u0019\u0011J\u001c;)\u0007\r)5*\u0001\u0005ok6\u0014\u0015N\\:!Q\r!QiS\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007}\u000bw\r\u0005\u0002a\u00015\ta\u0004C\u00037\u000b\u0001\u0007!\r\r\u0002dKB\u0019!(\u00103\u0011\u0005\u0001+G!\u0003\"b\u0003\u0003\u0005\tQ!\u0001NQ\r\tWi\u0013\u0005\b+\u0016\u0001\n\u00111\u0001XQ\r9Wi\u0013\u0015\u0004\u000b\u0015S\u0017%A6\u0002\u000bMr\u0003G\f\u0019\u0002#M\u001cwN]3MC\n,Gn],fS\u001eDG/F\u0001o!\rQTh\u001c\t\u0005WA\u0014X/\u0003\u0002rY\t1A+\u001e9mKJ\u0002\"aK:\n\u0005Qd#A\u0002#pk\ndW\r\u0005\u0003,aJ\u0014\u0018AE:d_J,G*\u00192fYN<V-[4ii\u0002\"\"a\u0018=\t\u000bYB\u0001\u0019A=\u0011\u0007ijT\u000fK\u0002\t\u000bn\f\u0013\u0001`\u0001\u0006c9\u0002d\u0006\r\u000b\u0003?zDQAN\u0005A\u0002}\u0004B!!\u0001\u0002\"9!\u00111AA\u000e\u001d\u0011\t)!a\u0006\u000f\t\u0005\u001d\u0011Q\u0003\b\u0005\u0003\u0013\t\u0019B\u0004\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\ty\u0001K\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dJ!!\n\u0014\n\u0005\r\"\u0013bAA\rE\u0005\u00191/\u001d7\n\t\u0005u\u0011qD\u0001\ba\u0006\u001c7.Y4f\u0015\r\tIBI\u0005\u0005\u0003G\t)CA\u0005ECR\fgI]1nK*!\u0011QDA\u0010\u0003%)h\u000e]3sg&\u001cH\u000f\u0006\u0002\u0002,A\u00191&!\f\n\u0007\u0005=BF\u0001\u0003V]&$\bf\u0001\u0006Fw\u0006QA\u000f\u001b:fg\"|G\u000eZ:\u0015\u0005\u0005]\u0002c\u0001\u001e>e\"\u001a1\"R>\u0002\u0007I|7\rF\u0001zQ\raQi_\u0001\rCJ,\u0017-\u00168eKJ\u0014vj\u0011\u000b\u0002e\"\u001aQ\"R>\u0002\u0005A\u0014\bf\u0001\bFw\u0006Y\u0011M]3b+:$WM\u001d)SQ\ryQi_\u0001\u0014M6+\u0017m];sK\nKH\u000b\u001b:fg\"|G\u000e\u001a\u000b\u0004s\u0006M\u0003BBA+!\u0001\u0007!/\u0001\u0003cKR\f\u0007f\u0001\tFw\"\u001a\u0011#R>\u0002)A\u0014XmY5tS>t')\u001f+ie\u0016\u001c\bn\u001c7eQ\r\u0011Ri_\u0001\u0012e\u0016\u001c\u0017\r\u001c7CsRC'/Z:i_2$\u0007fA\nFw\u0006\u0019\u0001\u0010J\u001a\u0016\u0005\u0005\u001d\u0004CB\u0016q\u0003S\nI\b\u0005\u0003;{\u0005-\u0004#B\u0016qe\u00065\u0004\u0003BA8\u0003kj!!!\u001d\u000b\u0007\u0005Md$\u0001\u0004cS:\f'/_\u0005\u0005\u0003o\n\tH\u0001\nCS:\f'/\u001f'bE\u0016d7i\\;oi\u0016\u0014\b\u0003\u0002\u001e>\u0003w\u0002Ra\u000b9s\u0003{\u0002B!a\u001c\u0002\u0000%!\u0011\u0011QA9\u0005U\u0011\u0015N\\1ss\u000e{gNZ;tS>tW*\u0019;sSb\f\u0001cY;nk2\fG/\u001b<f\u0007>,h\u000e^:\u0016\u0005\u0005%\u0014AC2p]\u001a,8/[8ogV\u0011\u0011\u0011P\u0001\fGJ,\u0017\r^3DkJ4X\rF\u0002z\u0003\u001fCq!!%\u0018\u0001\u0004\t\u0019*A\u0001z!\u0011\ty'!&\n\t\u0005]\u0015\u0011\u000f\u0002#\u0005&t\u0017M]=DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8NKR\u0014\u0018nY\"p[B,H/\u001a:\u0015\u000be\fY*a(\t\u000f\u0005u\u0005\u00041\u0001\u0002\u0014\u0006\t\u0001\u0010C\u0004\u0002\u0012b\u0001\r!a%)\u0007\u0001)50A\u000eCS:\f'/_\"mCN\u001c\u0018NZ5dCRLwN\\'fiJL7m\u001d\t\u0003Aj\u0019\"A\u0007\u0016\u0015\u0005\u0005\u0015\u0016a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0006\u0002\u00020*\u001aq+!-,\u0005\u0005M\u0006\u0003BA[\u0003{k!!a.\u000b\t\u0005e\u00161X\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u0013\u0017\n\t\u0005}\u0016q\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class BinaryClassificationMetrics implements Logging {
   private Tuple2 x$3;
   private RDD cumulativeCounts;
   private RDD confusions;
   private final RDD scoreAndLabels;
   private final int numBins;
   private final RDD scoreLabelsWeight;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

   public static int $lessinit$greater$default$2() {
      return BinaryClassificationMetrics$.MODULE$.$lessinit$greater$default$2();
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public RDD scoreAndLabels() {
      return this.scoreAndLabels;
   }

   public int numBins() {
      return this.numBins;
   }

   private RDD scoreLabelsWeight() {
      return this.scoreLabelsWeight;
   }

   public void unpersist() {
      this.cumulativeCounts().unpersist(this.cumulativeCounts().unpersist$default$1());
   }

   public RDD thresholds() {
      return this.cumulativeCounts().map((x$1) -> BoxesRunTime.boxToDouble($anonfun$thresholds$1(x$1)), .MODULE$.Double());
   }

   public RDD roc() {
      RDD rocCurve = this.createCurve(FalsePositiveRate$.MODULE$, Recall$.MODULE$);
      int numParts = rocCurve.getNumPartitions();
      return rocCurve.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$roc$1(numParts, BoxesRunTime.unboxToInt(x0$1), x1$1), rocCurve.mapPartitionsWithIndex$default$2(), .MODULE$.apply(Tuple2.class));
   }

   public double areaUnderROC() {
      return AreaUnderCurve$.MODULE$.of(this.roc());
   }

   public RDD pr() {
      RDD prCurve = this.createCurve(Recall$.MODULE$, Precision$.MODULE$);
      Tuple2 var6 = (Tuple2)prCurve.first();
      if (var6 != null) {
         double firstPrecision = var6._2$mcD$sp();
         return prCurve.mapPartitionsWithIndex((x0$1, x1$1) -> $anonfun$pr$1(firstPrecision, BoxesRunTime.unboxToInt(x0$1), x1$1), prCurve.mapPartitionsWithIndex$default$2(), .MODULE$.apply(Tuple2.class));
      } else {
         throw new MatchError(var6);
      }
   }

   public double areaUnderPR() {
      return AreaUnderCurve$.MODULE$.of(this.pr());
   }

   public RDD fMeasureByThreshold(final double beta) {
      return this.createCurve(new FMeasure(beta));
   }

   public RDD fMeasureByThreshold() {
      return this.fMeasureByThreshold((double)1.0F);
   }

   public RDD precisionByThreshold() {
      return this.createCurve(Precision$.MODULE$);
   }

   public RDD recallByThreshold() {
      return this.createCurve(Recall$.MODULE$);
   }

   private Tuple2 x$3$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            OrderedRDDFunctions qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToOrderedRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.scoreLabelsWeight(), .MODULE$.Double(), .MODULE$.apply(Tuple2.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).combineByKey((labelAndWeight) -> (new BinaryLabelCounter((double)0.0F, (double)0.0F)).$plus$eq(labelAndWeight._1$mcD$sp(), labelAndWeight._2$mcD$sp()), (c, labelAndWeight) -> c.$plus$eq(labelAndWeight._1$mcD$sp(), labelAndWeight._2$mcD$sp()), (c1, c2) -> c1.$plus$eq(c2)), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$, .MODULE$.Double(), .MODULE$.apply(BinaryLabelCounter.class));
            boolean x$1 = false;
            int x$2 = qual$1.sortByKey$default$2();
            RDD counts = qual$1.sortByKey(false, x$2);
            RDD var10001;
            if (this.numBins() == 0) {
               var10001 = counts;
            } else {
               long countsSize = counts.count();
               long grouping = countsSize / (long)this.numBins();
               if (grouping < 2L) {
                  this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Curve is too small (", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToLong(countsSize))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"for ", " bins to be useful"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BIN..MODULE$, BoxesRunTime.boxToInteger(this.numBins()))}))))));
                  var10001 = counts;
               } else {
                  var10001 = counts.mapPartitions((iter) -> {
                     if (iter.hasNext()) {
                        DoubleRef score = DoubleRef.create(Double.NaN);
                        ObjectRef agg = ObjectRef.create(new BinaryLabelCounter(BinaryLabelCounter$.MODULE$.$lessinit$greater$default$1(), BinaryLabelCounter$.MODULE$.$lessinit$greater$default$2()));
                        LongRef cnt = LongRef.create(0L);
                        return iter.flatMap((pair) -> {
                           score.elem = pair._1$mcD$sp();
                           ((BinaryLabelCounter)agg.elem).$plus$eq((BinaryLabelCounter)pair._2());
                           ++cnt.elem;
                           if (cnt.elem == grouping) {
                              Tuple2 ret = new Tuple2(BoxesRunTime.boxToDouble(score.elem), (BinaryLabelCounter)agg.elem);
                              agg.elem = new BinaryLabelCounter(BinaryLabelCounter$.MODULE$.$lessinit$greater$default$1(), BinaryLabelCounter$.MODULE$.$lessinit$greater$default$2());
                              cnt.elem = 0L;
                              return new Some(ret);
                           } else {
                              return scala.None..MODULE$;
                           }
                        }).$plus$plus(() -> cnt.elem > 0L ? scala.package..MODULE$.Iterator().single(new Tuple2(BoxesRunTime.boxToDouble(score.elem), (BinaryLabelCounter)agg.elem)) : scala.package..MODULE$.Iterator().empty());
                     } else {
                        return scala.package..MODULE$.Iterator().empty();
                     }
                  }, counts.mapPartitions$default$2(), .MODULE$.apply(Tuple2.class));
               }
            }

            RDD binnedCounts = var10001;
            RDD qual$2 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(binnedCounts, .MODULE$.Double(), .MODULE$.apply(BinaryLabelCounter.class), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).values();
            Function1 x$3 = (iter) -> {
               BinaryLabelCounter agg = new BinaryLabelCounter(BinaryLabelCounter$.MODULE$.$lessinit$greater$default$1(), BinaryLabelCounter$.MODULE$.$lessinit$greater$default$2());
               iter.foreach((x$2) -> agg.$plus$eq(x$2));
               return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new BinaryLabelCounter[]{agg}));
            };
            boolean x$4 = qual$2.mapPartitions$default$2();
            BinaryLabelCounter[] agg = (BinaryLabelCounter[])qual$2.mapPartitions(x$3, x$4, .MODULE$.apply(BinaryLabelCounter.class)).collect();
            BinaryLabelCounter[] partitionwiseCumulativeCounts = (BinaryLabelCounter[])scala.collection.ArrayOps..MODULE$.scanLeft$extension(scala.Predef..MODULE$.refArrayOps(agg), new BinaryLabelCounter(BinaryLabelCounter$.MODULE$.$lessinit$greater$default$1(), BinaryLabelCounter$.MODULE$.$lessinit$greater$default$2()), (aggx, c) -> aggx.clone().$plus$eq(c), .MODULE$.apply(BinaryLabelCounter.class));
            BinaryLabelCounter totalCount = (BinaryLabelCounter)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps(partitionwiseCumulativeCounts));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Total counts: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, totalCount)})))));
            RDD cumulativeCounts = binnedCounts.mapPartitionsWithIndex((index, iter) -> $anonfun$x$3$12(partitionwiseCumulativeCounts, BoxesRunTime.unboxToInt(index), iter), true, .MODULE$.apply(Tuple2.class));
            cumulativeCounts.persist();
            RDD confusions = cumulativeCounts.map((x0$2) -> {
               if (x0$2 != null) {
                  double score = x0$2._1$mcD$sp();
                  BinaryLabelCounter cumCount = (BinaryLabelCounter)x0$2._2();
                  return new Tuple2(BoxesRunTime.boxToDouble(score), new BinaryConfusionMatrixImpl(cumCount, totalCount));
               } else {
                  throw new MatchError(x0$2);
               }
            }, .MODULE$.apply(Tuple2.class));
            Tuple2 var3 = new Tuple2(cumulativeCounts, confusions);
            if (var3 != null) {
               RDD cumulativeCounts = (RDD)var3._1();
               RDD confusions = (RDD)var3._2();
               if (cumulativeCounts != null && confusions != null) {
                  this.x$3 = new Tuple2(cumulativeCounts, confusions);
                  this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                  return this.x$3;
               }
            }

            throw new MatchError(var3);
         }
      } catch (Throwable var26) {
         throw var26;
      }

      return this.x$3;
   }

   // $FF: synthetic method
   private Tuple2 x$3() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.x$3$lzycompute() : this.x$3;
   }

   private RDD cumulativeCounts$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.cumulativeCounts = (RDD)this.x$3()._1();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cumulativeCounts;
   }

   private RDD cumulativeCounts() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.cumulativeCounts$lzycompute() : this.cumulativeCounts;
   }

   private RDD confusions$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.confusions = (RDD)this.x$3()._2();
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.confusions;
   }

   private RDD confusions() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.confusions$lzycompute() : this.confusions;
   }

   private RDD createCurve(final BinaryClassificationMetricComputer y) {
      return this.confusions().map((x0$1) -> {
         if (x0$1 != null) {
            double s = x0$1._1$mcD$sp();
            BinaryConfusionMatrix c = (BinaryConfusionMatrix)x0$1._2();
            return new Tuple2.mcDD.sp(s, y.apply(c));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class));
   }

   private RDD createCurve(final BinaryClassificationMetricComputer x, final BinaryClassificationMetricComputer y) {
      return this.confusions().map((x0$1) -> {
         if (x0$1 != null) {
            BinaryConfusionMatrix c = (BinaryConfusionMatrix)x0$1._2();
            return new Tuple2.mcDD.sp(x.apply(c), y.apply(c));
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class));
   }

   // $FF: synthetic method
   public static final double $anonfun$thresholds$1(final Tuple2 x$1) {
      return x$1._1$mcD$sp();
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$roc$1(final int numParts$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var4 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var4 != null) {
         int pid = var4._1$mcI$sp();
         Iterator iter = (Iterator)var4._2();
         if (numParts$1 == 1) {
            scala.Predef..MODULE$.require(pid == 0);
            return scala.package..MODULE$.Iterator().single(new Tuple2.mcDD.sp((double)0.0F, (double)0.0F)).$plus$plus(() -> iter).$plus$plus(() -> scala.package..MODULE$.Iterator().single(new Tuple2.mcDD.sp((double)1.0F, (double)1.0F)));
         } else if (pid == 0) {
            return scala.package..MODULE$.Iterator().single(new Tuple2.mcDD.sp((double)0.0F, (double)0.0F)).$plus$plus(() -> iter);
         } else {
            return pid == numParts$1 - 1 ? iter.$plus$plus(() -> scala.package..MODULE$.Iterator().single(new Tuple2.mcDD.sp((double)1.0F, (double)1.0F))) : iter;
         }
      } else {
         throw new MatchError(var4);
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$pr$1(final double firstPrecision$1, final int x0$1, final Iterator x1$1) {
      Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(x0$1), x1$1);
      if (var5 != null) {
         int pid = var5._1$mcI$sp();
         Iterator iter = (Iterator)var5._2();
         return pid == 0 ? scala.package..MODULE$.Iterator().single(new Tuple2.mcDD.sp((double)0.0F, firstPrecision$1)).$plus$plus(() -> iter) : iter;
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$x$3$12(final BinaryLabelCounter[] partitionwiseCumulativeCounts$1, final int index, final Iterator iter) {
      BinaryLabelCounter cumCount = partitionwiseCumulativeCounts$1[index];
      return iter.map((x0$1) -> {
         if (x0$1 != null) {
            double score = x0$1._1$mcD$sp();
            BinaryLabelCounter c = (BinaryLabelCounter)x0$1._2();
            cumCount.$plus$eq(c);
            return new Tuple2(BoxesRunTime.boxToDouble(score), cumCount.clone());
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public BinaryClassificationMetrics(final RDD scoreAndLabels, final int numBins) {
      this.scoreAndLabels = scoreAndLabels;
      this.numBins = numBins;
      Logging.$init$(this);
      this.scoreLabelsWeight = scoreAndLabels.map((x0$1) -> {
         if (x0$1 instanceof Tuple3 var3) {
            Object prediction = var3._1();
            Object label = var3._2();
            Object weight = var3._3();
            if (prediction instanceof Double) {
               double var7 = BoxesRunTime.unboxToDouble(prediction);
               if (label instanceof Double) {
                  double var9 = BoxesRunTime.unboxToDouble(label);
                  if (weight instanceof Double) {
                     double var11 = BoxesRunTime.unboxToDouble(weight);
                     scala.Predef..MODULE$.require(var11 >= (double)0.0F, () -> "instance weight, " + var11 + " has to be >= 0.0");
                     return new Tuple2(BoxesRunTime.boxToDouble(var7), new Tuple2.mcDD.sp(var9, var11));
                  }
               }
            }
         }

         if (x0$1 instanceof Tuple2 var13) {
            Object prediction = var13._1();
            Object label = var13._2();
            if (prediction instanceof Double) {
               double var16 = BoxesRunTime.unboxToDouble(prediction);
               if (label instanceof Double) {
                  double var18 = BoxesRunTime.unboxToDouble(label);
                  return new Tuple2(BoxesRunTime.boxToDouble(var16), new Tuple2.mcDD.sp(var18, (double)1.0F));
               }
            }
         }

         throw new IllegalArgumentException("Expected tuples, got " + x0$1);
      }, .MODULE$.apply(Tuple2.class));
      scala.Predef..MODULE$.require(numBins >= 0, () -> "numBins must be nonnegative");
   }

   public BinaryClassificationMetrics(final RDD scoreAndLabels) {
      this(scoreAndLabels, 0);
   }

   public BinaryClassificationMetrics(final Dataset scoreAndLabels) {
      this(scoreAndLabels.rdd().map(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Tuple3 apply(final Row x0$1) {
            if (x0$1 != null) {
               Some var4 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var4.isEmpty() && var4.get() != null && ((SeqOps)var4.get()).lengthCompare(3) == 0) {
                  Object prediction = ((SeqOps)var4.get()).apply(0);
                  Object label = ((SeqOps)var4.get()).apply(1);
                  Object weight = ((SeqOps)var4.get()).apply(2);
                  if (prediction instanceof Double) {
                     double var8 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var10 = BoxesRunTime.unboxToDouble(label);
                        if (weight instanceof Double) {
                           double var12 = BoxesRunTime.unboxToDouble(weight);
                           return new Tuple3(BoxesRunTime.boxToDouble(var8), BoxesRunTime.boxToDouble(var10), BoxesRunTime.boxToDouble(var12));
                        }
                     }
                  }
               }
            }

            if (x0$1 != null) {
               Some var14 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
               if (!var14.isEmpty() && var14.get() != null && ((SeqOps)var14.get()).lengthCompare(2) == 0) {
                  Object prediction = ((SeqOps)var14.get()).apply(0);
                  Object label = ((SeqOps)var14.get()).apply(1);
                  if (prediction instanceof Double) {
                     double var17 = BoxesRunTime.unboxToDouble(prediction);
                     if (label instanceof Double) {
                        double var19 = BoxesRunTime.unboxToDouble(label);
                        return new Tuple3(BoxesRunTime.boxToDouble(var17), BoxesRunTime.boxToDouble(var19), BoxesRunTime.boxToDouble((double)1.0F));
                     }
                  }
               }
            }

            throw new IllegalArgumentException("Expected Row of tuples, got " + x0$1);
         }
      }, .MODULE$.apply(Tuple3.class)), BinaryClassificationMetrics$.MODULE$.$lessinit$greater$default$2());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
