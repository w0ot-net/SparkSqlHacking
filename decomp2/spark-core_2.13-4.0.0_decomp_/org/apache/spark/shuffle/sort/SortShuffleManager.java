package org.apache.spark.shuffle.sort;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.spark.MapOutputTracker;
import org.apache.spark.MapSizesByExecutorId;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.BaseShuffleHandle;
import org.apache.spark.shuffle.BlockStoreShuffleReader;
import org.apache.spark.shuffle.BlockStoreShuffleReader$;
import org.apache.spark.shuffle.IndexShuffleBlockResolver;
import org.apache.spark.shuffle.ShuffleHandle;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.shuffle.ShuffleReadMetricsReporter;
import org.apache.spark.shuffle.ShuffleReader;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.shuffle.ShuffleWriter;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.util.collection.OpenHashSet;
import org.apache.spark.util.collection.OpenHashSet$mcJ$sp;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g!\u0002\f\u0018\u0001m\t\u0003\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u000ba\u0002A\u0011A\u001d\t\ru\u0002\u0001\u0015!\u0003?\u0011!)\u0006\u0001#b\u0001\n\u00131\u0006bB/\u0001\u0005\u0004%\tE\u0018\u0005\u0007E\u0002\u0001\u000b\u0011B0\t\u000b\r\u0004A\u0011\t3\t\u000f\u0005\r\u0001\u0001\"\u0011\u0002\u0006!9\u0011q\b\u0001\u0005B\u0005\u0005\u0003bBA2\u0001\u0011\u0005\u0013Q\r\u0005\b\u0003_\u0002A\u0011IA9\u000f!\tIh\u0006E\u00017\u0005mda\u0002\f\u0018\u0011\u0003Y\u0012Q\u0010\u0005\u0007q5!\t!a \t\u0013\u0005\u0005UB1A\u0005\u0002\u0005\r\u0005bBAC\u001b\u0001\u0006I\u0001\u0013\u0005\n\u0003\u000fk!\u0019!C\u0001\u0003\u0013C\u0001\"a&\u000eA\u0003%\u00111\u0012\u0005\b\u00033kA\u0011AAN\u0011\u001d\t\u0019+\u0004C\u0001\u0003KCq!a0\u000e\t\u0013\t\tM\u0001\nT_J$8\u000b[;gM2,W*\u00198bO\u0016\u0014(B\u0001\r\u001a\u0003\u0011\u0019xN\u001d;\u000b\u0005iY\u0012aB:ik\u001a4G.\u001a\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sON!\u0001A\t\u0015-!\t\u0019c%D\u0001%\u0015\u0005)\u0013!B:dC2\f\u0017BA\u0014%\u0005\u0019\te.\u001f*fMB\u0011\u0011FK\u0007\u00023%\u00111&\u0007\u0002\u000f'\",hM\u001a7f\u001b\u0006t\u0017mZ3s!\ti\u0003'D\u0001/\u0015\ty3$\u0001\u0005j]R,'O\\1m\u0013\t\tdFA\u0004M_\u001e<\u0017N\\4\u0002\t\r|gNZ\u0002\u0001!\t)d'D\u0001\u001c\u0013\t94DA\u0005Ta\u0006\u00148nQ8oM\u00061A(\u001b8jiz\"\"A\u000f\u001f\u0011\u0005m\u0002Q\"A\f\t\u000bI\u0012\u0001\u0019\u0001\u001b\u0002)Q\f7o[%e\u001b\u0006\u00048OR8s'\",hM\u001a7f!\u0011yd\tS&\u000e\u0003\u0001S!!\u0011\"\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002D\t\u0006!Q\u000f^5m\u0015\u0005)\u0015\u0001\u00026bm\u0006L!a\u0012!\u0003#\r{gnY;se\u0016tG\u000fS1tQ6\u000b\u0007\u000f\u0005\u0002$\u0013&\u0011!\n\n\u0002\u0004\u0013:$\bc\u0001'Q%6\tQJ\u0003\u0002O\u001f\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005\r[\u0012BA)N\u0005-y\u0005/\u001a8ICND7+\u001a;\u0011\u0005\r\u001a\u0016B\u0001+%\u0005\u0011auN\\4\u00023MDWO\u001a4mK\u0016CXmY;u_J\u001cu.\u001c9p]\u0016tGo]\u000b\u0002/B\u0011\u0001lW\u0007\u00023*\u0011!,G\u0001\u0004CBL\u0017B\u0001/Z\u0005e\u0019\u0006.\u001e4gY\u0016,\u00050Z2vi>\u00148i\\7q_:,g\u000e^:\u0002)MDWO\u001a4mK\ncwnY6SKN|GN^3s+\u0005y\u0006CA\u0015a\u0013\t\t\u0017DA\rJ]\u0012,\u0007p\u00155vM\u001adWM\u00117pG.\u0014Vm]8mm\u0016\u0014\u0018!F:ik\u001a4G.\u001a\"m_\u000e\\'+Z:pYZ,'\u000fI\u0001\u0010e\u0016<\u0017n\u001d;feNCWO\u001a4mKV!QM\u001d?\u0000)\r1\u0017n\u001b\t\u0003S\u001dL!\u0001[\r\u0003\u001bMCWO\u001a4mK\"\u000bg\u000e\u001a7f\u0011\u0015Qw\u00011\u0001I\u0003%\u0019\b.\u001e4gY\u0016LE\rC\u0003m\u000f\u0001\u0007Q.\u0001\u0006eKB,g\u000eZ3oGf\u0004R!\u000e8qwzL!a\\\u000e\u0003#MCWO\u001a4mK\u0012+\u0007/\u001a8eK:\u001c\u0017\u0010\u0005\u0002re2\u0001A!B:\b\u0005\u0004!(!A&\u0012\u0005UD\bCA\u0012w\u0013\t9HEA\u0004O_RD\u0017N\\4\u0011\u0005\rJ\u0018B\u0001>%\u0005\r\te.\u001f\t\u0003cr$Q!`\u0004C\u0002Q\u0014\u0011A\u0016\t\u0003c~$a!!\u0001\b\u0005\u0004!(!A\"\u0002\u0013\u001d,GOU3bI\u0016\u0014XCBA\u0004\u0003#\t)\u0002\u0006\t\u0002\n\u0005]\u00111DA\u0010\u0003G\t9#a\u000b\u00026A9\u0011&a\u0003\u0002\u0010\u0005M\u0011bAA\u00073\ti1\u000b[;gM2,'+Z1eKJ\u00042!]A\t\t\u0015\u0019\bB1\u0001u!\r\t\u0018Q\u0003\u0003\u0007\u0003\u0003A!\u0019\u0001;\t\r\u0005e\u0001\u00021\u0001g\u0003\u0019A\u0017M\u001c3mK\"1\u0011Q\u0004\u0005A\u0002!\u000bQb\u001d;beRl\u0015\r]%oI\u0016D\bBBA\u0011\u0011\u0001\u0007\u0001*A\u0006f]\u0012l\u0015\r]%oI\u0016D\bBBA\u0013\u0011\u0001\u0007\u0001*\u0001\bti\u0006\u0014H\u000fU1si&$\u0018n\u001c8\t\r\u0005%\u0002\u00021\u0001I\u00031)g\u000e\u001a)beRLG/[8o\u0011\u001d\ti\u0003\u0003a\u0001\u0003_\tqaY8oi\u0016DH\u000fE\u00026\u0003cI1!a\r\u001c\u0005-!\u0016m]6D_:$X\r\u001f;\t\u000f\u0005]\u0002\u00021\u0001\u0002:\u00059Q.\u001a;sS\u000e\u001c\bcA\u0015\u0002<%\u0019\u0011QH\r\u00035MCWO\u001a4mKJ+\u0017\rZ'fiJL7m\u001d*fa>\u0014H/\u001a:\u0002\u0013\u001d,Go\u0016:ji\u0016\u0014XCBA\"\u0003\u001b\n\t\u0006\u0006\u0006\u0002F\u0005M\u0013QKA-\u00037\u0002r!KA$\u0003\u0017\ny%C\u0002\u0002Je\u0011Qb\u00155vM\u001adWm\u0016:ji\u0016\u0014\bcA9\u0002N\u0011)1/\u0003b\u0001iB\u0019\u0011/!\u0015\u0005\u000buL!\u0019\u0001;\t\r\u0005e\u0011\u00021\u0001g\u0011\u0019\t9&\u0003a\u0001%\u0006)Q.\u00199JI\"9\u0011QF\u0005A\u0002\u0005=\u0002bBA\u001c\u0013\u0001\u0007\u0011Q\f\t\u0004S\u0005}\u0013bAA13\tY2\u000b[;gM2,wK]5uK6+GO]5dgJ+\u0007o\u001c:uKJ\f\u0011#\u001e8sK\u001eL7\u000f^3s'\",hM\u001a7f)\u0011\t9'!\u001c\u0011\u0007\r\nI'C\u0002\u0002l\u0011\u0012qAQ8pY\u0016\fg\u000eC\u0003k\u0015\u0001\u0007\u0001*\u0001\u0003ti>\u0004HCAA:!\r\u0019\u0013QO\u0005\u0004\u0003o\"#\u0001B+oSR\f!cU8siNCWO\u001a4mK6\u000bg.Y4feB\u00111(D\n\u0004\u001b\tbCCAA>\u0003Ej\u0015\tW0T\u0011V3e\tT#`\u001fV#\u0006+\u0016+`!\u0006\u0013F+\u0013+J\u001f:\u001bvLR(S?N+%+S!M\u0013j+EiX'P\t\u0016+\u0012\u0001S\u00013\u001b\u0006Cvl\u0015%V\r\u001acUiX(V)B+Fk\u0018)B%RKE+S(O'~3uJU0T\u000bJK\u0015\tT%[\u000b\u0012{Vj\u0014#FA\u0005Ic)\u0012+D\u0011~\u001b\u0006*\u0016$G\u0019\u0016{&\tT(D\u0017N{\u0016JT0C\u0003R\u001b\u0005jX#O\u0003\ncU\tR0L\u000bf+\"!a#\u0011\t\u00055\u00151S\u0007\u0003\u0003\u001fS1!!%E\u0003\u0011a\u0017M\\4\n\t\u0005U\u0015q\u0012\u0002\u0007'R\u0014\u0018N\\4\u0002U\u0019+Ek\u0011%`'\"+fI\u0012'F?\ncujQ&T?&suLQ!U\u0007\"{VIT!C\u0019\u0016#ulS#ZA\u0005\u00012-\u00198Vg\u0016\u0014\u0015\r^2i\r\u0016$8\r\u001b\u000b\t\u0003O\ni*a(\u0002\"\"1\u0011QE\nA\u0002!Ca!!\u000b\u0014\u0001\u0004A\u0005bBA\u0017'\u0001\u0007\u0011qF\u0001\u0018G\u0006tWk]3TKJL\u0017\r\\5{K\u0012\u001c\u0006.\u001e4gY\u0016$B!a\u001a\u0002(\"1A\u000e\u0006a\u0001\u0003S\u0003\u0004\"a+\u00020\u0006U\u00161\u0018\t\tk9\fi+a-\u0002:B\u0019\u0011/a,\u0005\u0017\u0005E\u0016qUA\u0001\u0002\u0003\u0015\t\u0001\u001e\u0002\u0004?\u0012\u001a\u0004cA9\u00026\u0012Y\u0011qWAT\u0003\u0003\u0005\tQ!\u0001u\u0005\ryF\u0005\u000e\t\u0004c\u0006mFaCA_\u0003O\u000b\t\u0011!A\u0003\u0002Q\u00141a\u0018\u00136\u0003uaw.\u00193TQV4g\r\\3Fq\u0016\u001cW\u000f^8s\u0007>l\u0007o\u001c8f]R\u001cHcA,\u0002D\")!'\u0006a\u0001i\u0001"
)
public class SortShuffleManager implements ShuffleManager, Logging {
   private ShuffleExecutorComponents shuffleExecutorComponents;
   private final SparkConf conf;
   private final ConcurrentHashMap taskIdMapsForShuffle;
   private final IndexShuffleBlockResolver shuffleBlockResolver;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static boolean canUseSerializedShuffle(final ShuffleDependency dependency) {
      return SortShuffleManager$.MODULE$.canUseSerializedShuffle(dependency);
   }

   public static boolean canUseBatchFetch(final int startPartition, final int endPartition, final TaskContext context) {
      return SortShuffleManager$.MODULE$.canUseBatchFetch(startPartition, endPartition, context);
   }

   public static String FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY() {
      return SortShuffleManager$.MODULE$.FETCH_SHUFFLE_BLOCKS_IN_BATCH_ENABLED_KEY();
   }

   public static int MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE() {
      return SortShuffleManager$.MODULE$.MAX_SHUFFLE_OUTPUT_PARTITIONS_FOR_SERIALIZED_MODE();
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

   public final ShuffleReader getReader(final ShuffleHandle handle, final int startPartition, final int endPartition, final TaskContext context, final ShuffleReadMetricsReporter metrics) {
      return ShuffleManager.getReader$(this, handle, startPartition, endPartition, context, metrics);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ShuffleExecutorComponents shuffleExecutorComponents$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.shuffleExecutorComponents = SortShuffleManager$.MODULE$.org$apache$spark$shuffle$sort$SortShuffleManager$$loadShuffleExecutorComponents(this.conf);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.shuffleExecutorComponents;
   }

   private ShuffleExecutorComponents shuffleExecutorComponents() {
      return !this.bitmap$0 ? this.shuffleExecutorComponents$lzycompute() : this.shuffleExecutorComponents;
   }

   public IndexShuffleBlockResolver shuffleBlockResolver() {
      return this.shuffleBlockResolver;
   }

   public ShuffleHandle registerShuffle(final int shuffleId, final ShuffleDependency dependency) {
      if (SortShuffleWriter$.MODULE$.shouldBypassMergeSort(this.conf, dependency)) {
         return new BypassMergeSortShuffleHandle(shuffleId, dependency);
      } else {
         return (ShuffleHandle)(SortShuffleManager$.MODULE$.canUseSerializedShuffle(dependency) ? new SerializedShuffleHandle(shuffleId, dependency) : new BaseShuffleHandle(shuffleId, dependency));
      }
   }

   public ShuffleReader getReader(final ShuffleHandle handle, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition, final TaskContext context, final ShuffleReadMetricsReporter metrics) {
      BaseShuffleHandle baseShuffleHandle = (BaseShuffleHandle)handle;
      Tuple2 var10000;
      if (baseShuffleHandle.dependency().isShuffleMergeFinalizedMarked()) {
         MapSizesByExecutorId res = SparkEnv$.MODULE$.get().mapOutputTracker().getPushBasedShuffleMapSizesByExecutorId(handle.shuffleId(), startMapIndex, endMapIndex, startPartition, endPartition);
         var10000 = new Tuple2(res.iter(), BoxesRunTime.boxToBoolean(res.enableBatchFetch()));
      } else {
         Iterator address = SparkEnv$.MODULE$.get().mapOutputTracker().getMapSizesByExecutorId(handle.shuffleId(), startMapIndex, endMapIndex, startPartition, endPartition);
         var10000 = new Tuple2(address, BoxesRunTime.boxToBoolean(true));
      }

      Tuple2 var11 = var10000;
      if (var11 == null) {
         throw new MatchError(var11);
      } else {
         Iterator blocksByAddress = (Iterator)var11._1();
         boolean canEnableBatchFetch = var11._2$mcZ$sp();
         Tuple2 var10 = new Tuple2(blocksByAddress, BoxesRunTime.boxToBoolean(canEnableBatchFetch));
         Iterator blocksByAddress = (Iterator)var10._1();
         boolean canEnableBatchFetch = var10._2$mcZ$sp();
         BaseShuffleHandle x$1 = (BaseShuffleHandle)handle;
         boolean x$5 = canEnableBatchFetch && SortShuffleManager$.MODULE$.canUseBatchFetch(startPartition, endPartition, context);
         SerializerManager x$6 = BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$5();
         BlockManager x$7 = BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$6();
         MapOutputTracker x$8 = BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$7();
         return new BlockStoreShuffleReader(x$1, blocksByAddress, context, metrics, x$6, x$7, x$8, x$5);
      }
   }

   public ShuffleWriter getWriter(final ShuffleHandle handle, final long mapId, final TaskContext context, final ShuffleWriteMetricsReporter metrics) {
      OpenHashSet mapTaskIds = (OpenHashSet)this.taskIdMapsForShuffle.computeIfAbsent(BoxesRunTime.boxToInteger(handle.shuffleId()), (x$2) -> $anonfun$getWriter$1(BoxesRunTime.unboxToInt(x$2)));
      synchronized(mapTaskIds){}

      try {
         mapTaskIds.add$mcJ$sp(mapId);
      } catch (Throwable var15) {
         throw var15;
      }

      SparkEnv env = SparkEnv$.MODULE$.get();
      if (handle instanceof SerializedShuffleHandle var11) {
         return new UnsafeShuffleWriter(env.blockManager(), context.taskMemoryManager(), var11, mapId, context, env.conf(), metrics, this.shuffleExecutorComponents());
      } else if (handle instanceof BypassMergeSortShuffleHandle var12) {
         return new BypassMergeSortShuffleWriter(env.blockManager(), var12, mapId, env.conf(), metrics, this.shuffleExecutorComponents());
      } else if (handle instanceof BaseShuffleHandle var13) {
         return new SortShuffleWriter(var13, mapId, context, metrics, this.shuffleExecutorComponents());
      } else {
         throw new MatchError(handle);
      }
   }

   public boolean unregisterShuffle(final int shuffleId) {
      .MODULE$.apply(this.taskIdMapsForShuffle.remove(BoxesRunTime.boxToInteger(shuffleId))).foreach((mapTaskIds) -> {
         $anonfun$unregisterShuffle$1(this, shuffleId, mapTaskIds);
         return BoxedUnit.UNIT;
      });
      return true;
   }

   public void stop() {
      this.shuffleBlockResolver().stop();
   }

   // $FF: synthetic method
   public static final OpenHashSet $anonfun$getWriter$1(final int x$2) {
      return new OpenHashSet$mcJ$sp(16, scala.reflect.ClassTag..MODULE$.Long());
   }

   // $FF: synthetic method
   public static final void $anonfun$unregisterShuffle$1(final SortShuffleManager $this, final int shuffleId$1, final OpenHashSet mapTaskIds) {
      mapTaskIds.iterator().foreach((JFunction1.mcVJ.sp)(mapTaskId) -> $this.shuffleBlockResolver().removeDataByMap(shuffleId$1, mapTaskId));
   }

   public SortShuffleManager(final SparkConf conf) {
      this.conf = conf;
      ShuffleManager.$init$(this);
      Logging.$init$(this);
      this.taskIdMapsForShuffle = new ConcurrentHashMap();
      this.shuffleBlockResolver = new IndexShuffleBlockResolver(conf, this.taskIdMapsForShuffle);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
