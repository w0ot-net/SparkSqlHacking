package org.apache.spark.mllib.fpm;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.Map;
import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkException;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.ArrayOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t}d\u0001\u0002\u000f\u001e\u0001!B\u0001\"\u0011\u0001\u0003\u0002\u0004%IA\u0011\u0005\t\r\u0002\u0011\t\u0019!C\u0005\u000f\"AQ\n\u0001B\u0001B\u0003&1\t\u0003\u0005O\u0001\t\u0005\r\u0011\"\u0003P\u0011!\u0019\u0006A!a\u0001\n\u0013!\u0006\u0002\u0003,\u0001\u0005\u0003\u0005\u000b\u0015\u0002)\t\r]\u0003A\u0011A\u0011Y\u0011\u00159\u0006\u0001\"\u0001^\u0011\u00159\u0007\u0001\"\u0001i\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u0015\u0001\b\u0001\"\u0001r\u0011\u0019\u0001\b\u0001\"\u0001\u0002.!9\u0011\u0011\r\u0001\u0005\n\u0005\r\u0004bBAL\u0001\u0011%\u0011\u0011\u0014\u0005\b\u0005\u0013\u0002A\u0011\u0002B&\u000f\u001d\tI,\bE\u0001\u0003w3a\u0001H\u000f\t\u0002\u0005u\u0006BB,\u0012\t\u0003\tIM\u0002\u0004\u0002LF\u0001\u0011Q\u001a\u0005\u000b\u0003#\u001c\"Q1A\u0005\u0002\u0005M\u0007BCAo'\t\u0005\t\u0015!\u0003\u0002V\"Q\u0011\u0011]\n\u0003\u0006\u0004%\t!a9\t\u0015\u0005\u001d8C!A!\u0002\u0013\t)\b\u0003\u0004X'\u0011\u0005\u00111\u001e\u0005\b\u0003w\u001cB\u0011AA\u007f\u0011\u001d\u0011ia\u0005C!\u0005\u001fA\u0011Ba\t\u0012\u0003\u0003%IA!\n\u0003\u0011\u0019\u0003vI]8xi\"T!AH\u0010\u0002\u0007\u0019\u0004XN\u0003\u0002!C\u0005)Q\u000e\u001c7jE*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001Is&\u000e\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005A\u001aT\"A\u0019\u000b\u0005I\n\u0013\u0001C5oi\u0016\u0014h.\u00197\n\u0005Q\n$a\u0002'pO\u001eLgn\u001a\t\u0003myr!a\u000e\u001f\u000f\u0005aZT\"A\u001d\u000b\u0005i:\u0013A\u0002\u001fs_>$h(C\u0001-\u0013\ti4&A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0002%\u0001D*fe&\fG.\u001b>bE2,'BA\u001f,\u0003)i\u0017N\\*vaB|'\u000f^\u000b\u0002\u0007B\u0011!\u0006R\u0005\u0003\u000b.\u0012a\u0001R8vE2,\u0017AD7j]N+\b\u000f]8si~#S-\u001d\u000b\u0003\u0011.\u0003\"AK%\n\u0005)[#\u0001B+oSRDq\u0001\u0014\u0002\u0002\u0002\u0003\u00071)A\u0002yIE\n1\"\\5o'V\u0004\bo\u001c:uA\u0005ia.^7QCJ$\u0018\u000e^5p]N,\u0012\u0001\u0015\t\u0003UEK!AU\u0016\u0003\u0007%sG/A\tok6\u0004\u0016M\u001d;ji&|gn]0%KF$\"\u0001S+\t\u000f1+\u0011\u0011!a\u0001!\u0006qa.^7QCJ$\u0018\u000e^5p]N\u0004\u0013A\u0002\u001fj]&$h\bF\u0002Z7r\u0003\"A\u0017\u0001\u000e\u0003uAQ!Q\u0004A\u0002\rCQAT\u0004A\u0002A#\u0012!\u0017\u0015\u0004\u0011}+\u0007C\u00011d\u001b\u0005\t'B\u00012\"\u0003)\tgN\\8uCRLwN\\\u0005\u0003I\u0006\u0014QaU5oG\u0016\f\u0013AZ\u0001\u0006c9\u001ad\u0006M\u0001\u000eg\u0016$X*\u001b8TkB\u0004xN\u001d;\u0015\u0005%TW\"\u0001\u0001\t\u000b\u0005K\u0001\u0019A\")\u0007%yV-\u0001\ttKRtU/\u001c)beRLG/[8ogR\u0011\u0011N\u001c\u0005\u0006\u001d*\u0001\r\u0001\u0015\u0015\u0004\u0015}+\u0017a\u0001:v]V\u0011!/\u001f\u000b\u0004g\u0006UAc\u0001;\u0002\u0006A\u0019!,^<\n\u0005Yl\"!\u0004$Q\u000fJ|w\u000f\u001e5N_\u0012,G\u000e\u0005\u0002ys2\u0001A!\u0002>\f\u0005\u0004Y(\u0001B%uK6\f\"\u0001`@\u0011\u0005)j\u0018B\u0001@,\u0005\u001dqu\u000e\u001e5j]\u001e\u00042AKA\u0001\u0013\r\t\u0019a\u000b\u0002\u0004\u0003:L\b\"CA\u0004\u0017\u0005\u0005\t9AA\u0005\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0006\u0003\u0017\t\tb^\u0007\u0003\u0003\u001bQ1!a\u0004,\u0003\u001d\u0011XM\u001a7fGRLA!a\u0005\u0002\u000e\tA1\t\\1tgR\u000bw\rC\u0004\u0002\u0018-\u0001\r!!\u0007\u0002\t\u0011\fG/\u0019\t\u0007\u00037\t\t#!\n\u000e\u0005\u0005u!bAA\u0010C\u0005\u0019!\u000f\u001a3\n\t\u0005\r\u0012Q\u0004\u0002\u0004%\u0012#\u0005\u0003\u0002\u0016\u0002(]L1!!\u000b,\u0005\u0015\t%O]1zQ\rYq,Z\u000b\u0007\u0003_\t)$a\u0013\u0015\t\u0005E\u0012q\u0007\t\u00055V\f\u0019\u0004E\u0002y\u0003k!QA\u001f\u0007C\u0002mDq!a\u0006\r\u0001\u0004\tI\u0004\u0005\u0004\u0002<\u0005\u0015\u0013\u0011J\u0007\u0003\u0003{QA!a\u0010\u0002B\u0005!!.\u0019<b\u0015\r\t\u0019%I\u0001\u0004CBL\u0017\u0002BA$\u0003{\u0011qAS1wCJ#E\tE\u0002y\u0003\u0017\"q!!\u0014\r\u0005\u0004\tyE\u0001\u0004CCN\\W\r^\t\u0004y\u0006E\u0003CBA*\u00037\n\u0019$\u0004\u0002\u0002V)!\u0011qKA-\u0003\u0011a\u0017M\\4\u000b\u0005\u0005}\u0012\u0002BA/\u0003+\u0012\u0001\"\u0013;fe\u0006\u0014G.\u001a\u0015\u0004\u0019}+\u0017\u0001D4f]\u001a\u0013X-]%uK6\u001cX\u0003BA3\u0003g\"\u0002\"a\u001a\u0002\u0002\u0006\u001d\u00151\u0012\u000b\u0005\u0003S\nY\bE\u0003+\u0003O\tY\u0007E\u0004+\u0003[\n\t(!\u001e\n\u0007\u0005=4F\u0001\u0004UkBdWM\r\t\u0004q\u0006MD!\u0002>\u000e\u0005\u0004Y\bc\u0001\u0016\u0002x%\u0019\u0011\u0011P\u0016\u0003\t1{gn\u001a\u0005\n\u0003{j\u0011\u0011!a\u0002\u0003\u007f\n!\"\u001a<jI\u0016t7-\u001a\u00136!\u0019\tY!!\u0005\u0002r!9\u0011qC\u0007A\u0002\u0005\r\u0005CBA\u000e\u0003C\t)\tE\u0003+\u0003O\t\t\bC\u0004\u0002\n6\u0001\r!!\u001e\u0002\u00115LgnQ8v]RDq!!$\u000e\u0001\u0004\ty)A\u0006qCJ$\u0018\u000e^5p]\u0016\u0014\b\u0003BAI\u0003'k\u0011!I\u0005\u0004\u0003+\u000b#a\u0003)beRLG/[8oKJ\fqbZ3o\rJ,\u0017/\u0013;f[N,Go]\u000b\u0005\u00037\u0013\u0019\u0004\u0006\u0006\u0002\u001e\nm\"\u0011\tB\"\u0005\u000f\"B!a(\u00036A1\u00111DA\u0011\u0003C\u0003R!a)\u0014\u0005cq1!!*\u0011\u001d\u0011\t9+a.\u000f\t\u0005%\u0016Q\u0017\b\u0005\u0003W\u000b\u0019L\u0004\u0003\u0002.\u0006Efb\u0001\u001d\u00020&\ta%\u0003\u0002%K%\u0011!eI\u0005\u0003A\u0005J!AH\u0010\u0002\u0011\u0019\u0003vI]8xi\"\u0004\"AW\t\u0014\tEI\u0013q\u0018\t\u0005\u0003\u0003\f9-\u0004\u0002\u0002D*!\u0011QYA-\u0003\tIw.C\u0002@\u0003\u0007$\"!a/\u0003\u0017\u0019\u0013X-]%uK6\u001cX\r^\u000b\u0005\u0003\u001f\fInE\u0002\u0014SU\nQ!\u001b;f[N,\"!!6\u0011\u000b)\n9#a6\u0011\u0007a\fI\u000eB\u0003{'\t\u00071\u0010K\u0002\u0015?\u0016\fa!\u001b;f[N\u0004\u0003fA\u000b`K\u0006!aM]3r+\t\t)\bK\u0002\u0017?\u0016\fQA\u001a:fc\u0002B3aF0f)\u0019\ti/!=\u0002vB)\u0011q^\n\u0002X6\t\u0011\u0003C\u0004\u0002Rb\u0001\r!!6)\t\u0005Ex,\u001a\u0005\b\u0003CD\u0002\u0019AA;Q\u0011\t)pX3)\u0007ayV-A\u0005kCZ\f\u0017\n^3ngV\u0011\u0011q \t\u0007\u0005\u0003\u00119!a6\u000e\u0005\t\r!\u0002\u0002B\u0003\u00033\nA!\u001e;jY&!!\u0011\u0002B\u0002\u0005\u0011a\u0015n\u001d;)\u0007eyV-\u0001\u0005u_N#(/\u001b8h)\t\u0011\t\u0002\u0005\u0003\u0003\u0014\tma\u0002\u0002B\u000b\u0005/\u0001\"\u0001O\u0016\n\u0007\te1&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0005;\u0011yB\u0001\u0004TiJLgn\u001a\u0006\u0004\u00053Y\u0003fA\n`K\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!q\u0005\t\u0005\u0003'\u0012I#\u0003\u0003\u0003,\u0005U#AB(cU\u0016\u001cG\u000fK\u0002\u0012?\u0016D3\u0001E0f!\rA(1\u0007\u0003\u0006u:\u0011\ra\u001f\u0005\n\u0005oq\u0011\u0011!a\u0002\u0005s\t!\"\u001a<jI\u0016t7-\u001a\u00137!\u0019\tY!!\u0005\u00032!9\u0011q\u0003\bA\u0002\tu\u0002CBA\u000e\u0003C\u0011y\u0004E\u0003+\u0003O\u0011\t\u0004C\u0004\u0002\n:\u0001\r!!\u001e\t\u000f\t\u0015c\u00021\u0001\u0003@\u0005IaM]3r\u0013R,Wn\u001d\u0005\b\u0003\u001bs\u0001\u0019AAH\u0003M9WM\\\"p]\u0012$&/\u00198tC\u000e$\u0018n\u001c8t+\u0011\u0011iEa\u001b\u0015\u0011\t=#Q\u000eB:\u0005w\"BA!\u0015\u0003dA9!1\u000bB/!\n\u0005TB\u0001B+\u0015\u0011\u00119F!\u0017\u0002\u000f5,H/\u00192mK*\u0019!1L\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003`\tU#aA'baB!!&a\nQ\u0011%\u0011)gDA\u0001\u0002\b\u00119'\u0001\u0006fm&$WM\\2fI]\u0002b!a\u0003\u0002\u0012\t%\u0004c\u0001=\u0003l\u0011)!p\u0004b\u0001w\"9!qN\bA\u0002\tE\u0014a\u0003;sC:\u001c\u0018m\u0019;j_:\u0004RAKA\u0014\u0005SBqA!\u001e\u0010\u0001\u0004\u00119(\u0001\u0006ji\u0016lGk\u001c*b].\u0004rAa\u0005\u0003z\t%\u0004+\u0003\u0003\u0003`\t}\u0001bBAG\u001f\u0001\u0007\u0011q\u0012\u0015\u0004\u0001}+\u0007"
)
public class FPGrowth implements Logging, Serializable {
   private double minSupport;
   private int numPartitions;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private double minSupport() {
      return this.minSupport;
   }

   private void minSupport_$eq(final double x$1) {
      this.minSupport = x$1;
   }

   private int numPartitions() {
      return this.numPartitions;
   }

   private void numPartitions_$eq(final int x$1) {
      this.numPartitions = x$1;
   }

   public FPGrowth setMinSupport(final double minSupport) {
      .MODULE$.require(minSupport >= (double)0.0F && minSupport <= (double)1.0F, () -> "Minimal support level must be in range [0, 1] but got " + minSupport);
      this.minSupport_$eq(minSupport);
      return this;
   }

   public FPGrowth setNumPartitions(final int numPartitions) {
      .MODULE$.require(numPartitions > 0, () -> "Number of partitions must be positive but got " + numPartitions);
      this.numPartitions_$eq(numPartitions);
      return this;
   }

   public FPGrowthModel run(final RDD data, final ClassTag evidence$4) {
      label19: {
         StorageLevel var10000 = data.getStorageLevel();
         StorageLevel var3 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var3 != null) {
               break label19;
            }
         } else if (!var10000.equals(var3)) {
            break label19;
         }

         this.logWarning((Function0)(() -> "Input data is not cached."));
      }

      long count = data.count();
      long minCount = (long)scala.math.package..MODULE$.ceil(this.minSupport() * (double)count);
      int numParts = this.numPartitions() > 0 ? this.numPartitions() : data.partitions().length;
      HashPartitioner partitioner = new HashPartitioner(numParts);
      Tuple2[] freqItemsCount = this.genFreqItems(data, minCount, partitioner, evidence$4);
      RDD freqItemsets = this.genFreqItemsets(data, minCount, scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])freqItemsCount), (x$2) -> x$2._1(), evidence$4), partitioner, evidence$4);
      scala.collection.immutable.Map itemSupport = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])freqItemsCount), (x0$1) -> {
         if (x0$1 != null) {
            Object item = x0$1._1();
            long cnt = x0$1._2$mcJ$sp();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(item), BoxesRunTime.boxToDouble((double)cnt / (double)count));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      return new FPGrowthModel(freqItemsets, itemSupport, evidence$4);
   }

   public FPGrowthModel run(final JavaRDD data) {
      ClassTag tag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      return this.run(data.rdd().map((x$3) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(x$3).asScala().toArray(tag), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(tag.runtimeClass()))), tag);
   }

   private Tuple2[] genFreqItems(final RDD data, final long minCount, final Partitioner partitioner, final ClassTag evidence$5) {
      ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
      Predef var10001 = .MODULE$;
      RDD x$1 = data.flatMap((t) -> {
         Set uniq = .MODULE$.genericWrapArray(t).toSet();
         if (scala.runtime.ScalaRunTime..MODULE$.array_length(t) != uniq.size()) {
            throw new SparkException("Items in a transaction must be unique but got " + org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(t).toImmutableArraySeq() + ".");
         } else {
            return .MODULE$.genericWrapArray(t);
         }
      }, evidence$5).map((v) -> new Tuple2(v, BoxesRunTime.boxToLong(1L)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ClassTag x$3 = scala.reflect.ClassTag..MODULE$.Long();
      Null x$4 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions$default$4(x$1);
      return (Tuple2[])var10000.sortBy$extension(var10001.refArrayOps(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(x$1, evidence$5, x$3, (Ordering)null).reduceByKey(partitioner, (JFunction2.mcJJJ.sp)(x$4x, x$5) -> x$4x + x$5).filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$genFreqItems$4(minCount, x$6))).collect()), (x$7) -> BoxesRunTime.boxToLong($anonfun$genFreqItems$5(x$7)), scala.math.Ordering.Long..MODULE$);
   }

   private RDD genFreqItemsets(final RDD data, final long minCount, final Object freqItems, final Partitioner partitioner, final ClassTag evidence$6) {
      scala.collection.immutable.Map itemToRank = org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(.MODULE$.genericWrapArray(freqItems));
      return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(data.flatMap((transaction) -> this.genCondTransactions(transaction, itemToRank, partitioner, evidence$6), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Integer.TYPE)), scala.math.Ordering.Int..MODULE$).aggregateByKey(new FPTree(), partitioner.numPartitions(), (tree, transaction) -> tree.add(.MODULE$.wrapIntArray(transaction), 1L), (tree1, tree2) -> tree1.merge(tree2), scala.reflect.ClassTag..MODULE$.apply(FPTree.class)).flatMap((x0$1) -> {
         if (x0$1 != null) {
            int part = x0$1._1$mcI$sp();
            FPTree tree = (FPTree)x0$1._2();
            return tree.extract(minCount, (JFunction1.mcZI.sp)(x) -> partitioner.getPartition(BoxesRunTime.boxToInteger(x)) == part);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).map((x0$2) -> {
         if (x0$2 != null) {
            List ranks = (List)x0$2._1();
            long count = x0$2._2$mcJ$sp();
            return new FreqItemset(ranks.map((i) -> $anonfun$genFreqItemsets$7(freqItems, BoxesRunTime.unboxToInt(i))).toArray(evidence$6), count);
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(FreqItemset.class));
   }

   private scala.collection.mutable.Map genCondTransactions(final Object transaction, final scala.collection.immutable.Map itemToRank, final Partitioner partitioner, final ClassTag evidence$7) {
      scala.collection.mutable.Map output = (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty();
      int[] filtered = (int[])scala.collection.ArrayOps..MODULE$.flatMap$extension(.MODULE$.genericArrayOps(transaction), (key) -> itemToRank.get(key), scala.reflect.ClassTag..MODULE$.Int());
      Arrays.sort(filtered);
      int n = filtered.length;

      for(int i = n - 1; i >= 0; --i) {
         int item = filtered[i];
         int part = partitioner.getPartition(BoxesRunTime.boxToInteger(item));
         if (!output.contains(BoxesRunTime.boxToInteger(part))) {
            output.update(BoxesRunTime.boxToInteger(part), scala.collection.ArrayOps..MODULE$.slice$extension(.MODULE$.intArrayOps(filtered), 0, i + 1));
         }
      }

      return output;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$genFreqItems$4(final long minCount$1, final Tuple2 x$6) {
      return x$6._2$mcJ$sp() >= minCount$1;
   }

   // $FF: synthetic method
   public static final long $anonfun$genFreqItems$5(final Tuple2 x$7) {
      return -x$7._2$mcJ$sp();
   }

   // $FF: synthetic method
   public static final Object $anonfun$genFreqItemsets$7(final Object freqItems$1, final int i) {
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(freqItems$1, i);
   }

   public FPGrowth(final double minSupport, final int numPartitions) {
      this.minSupport = minSupport;
      this.numPartitions = numPartitions;
      super();
      Logging.$init$(this);
   }

   public FPGrowth() {
      this(0.3, -1);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class FreqItemset implements Serializable {
      private final Object items;
      private final long freq;

      public Object items() {
         return this.items;
      }

      public long freq() {
         return this.freq;
      }

      public java.util.List javaItems() {
         return scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(.MODULE$.genericWrapArray(this.items()).toList()).asJava();
      }

      public String toString() {
         String var10000 = .MODULE$.genericWrapArray(this.items()).mkString("{", ",", "}");
         return var10000 + ": " + this.freq();
      }

      public FreqItemset(final Object items, final long freq) {
         this.items = items;
         this.freq = freq;
      }
   }
}
