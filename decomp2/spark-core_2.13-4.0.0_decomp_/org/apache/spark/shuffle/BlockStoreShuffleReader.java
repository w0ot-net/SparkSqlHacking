package org.apache.spark.shuffle;

import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.Aggregator;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.MapOutputTracker;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.package$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.ShuffleBlockFetcherIterator;
import org.apache.spark.storage.ShuffleBlockFetcherIterator$;
import org.apache.spark.util.CompletionIterator;
import org.apache.spark.util.CompletionIterator$;
import org.apache.spark.util.collection.ExternalSorter;
import org.apache.spark.util.collection.ExternalSorter$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Option.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f!B\u000b\u0017\u0001aq\u0002\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u0011\u001d\u0003!\u0011!Q\u0001\n!C\u0001b\u001c\u0001\u0003\u0002\u0003\u0006I\u0001\u001d\u0005\ti\u0002\u0011\t\u0011)A\u0005k\"A\u0001\u0010\u0001B\u0001B\u0003%\u0011\u0010C\u0005\u0000\u0001\t\u0005\t\u0015!\u0003\u0002\u0002!Q\u0011q\u0001\u0001\u0003\u0002\u0003\u0006I!!\u0003\t\u0015\u0005=\u0001A!A!\u0002\u0013\t\t\u0002C\u0004\u0002\u0018\u0001!\t!!\u0007\t\u0013\u0005U\u0002A1A\u0005\n\u0005]\u0002\u0002CA#\u0001\u0001\u0006I!!\u000f\t\u000f\u0005\u001d\u0003\u0001\"\u0003\u0002J!9\u00111\n\u0001\u0005B\u00055sACA,-\u0005\u0005\t\u0012\u0001\r\u0002Z\u0019IQCFA\u0001\u0012\u0003A\u00121\f\u0005\b\u0003/yA\u0011AA/\u0011%\tyfDI\u0001\n\u0003\t\t\u0007C\u0005\u0002~=\t\n\u0011\"\u0001\u0002\u0000!I\u0011\u0011R\b\u0012\u0002\u0013\u0005\u00111\u0012\u0005\n\u0003+{\u0011\u0013!C\u0001\u0003/\u0013qC\u00117pG.\u001cFo\u001c:f'\",hM\u001a7f%\u0016\fG-\u001a:\u000b\u0005]A\u0012aB:ik\u001a4G.\u001a\u0006\u00033i\tQa\u001d9be.T!a\u0007\u000f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005i\u0012aA8sOV\u0019q\u0004L\u001c\u0014\t\u0001\u0001c%\u000f\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0011\t\u001dB#FN\u0007\u0002-%\u0011\u0011F\u0006\u0002\u000e'\",hM\u001a7f%\u0016\fG-\u001a:\u0011\u0005-bC\u0002\u0001\u0003\u0006[\u0001\u0011\ra\f\u0002\u0002\u0017\u000e\u0001\u0011C\u0001\u00194!\t\t\u0013'\u0003\u00023E\t9aj\u001c;iS:<\u0007CA\u00115\u0013\t)$EA\u0002B]f\u0004\"aK\u001c\u0005\u000ba\u0002!\u0019A\u0018\u0003\u0003\r\u0003\"AO\u001f\u000e\u0003mR!\u0001\u0010\r\u0002\u0011%tG/\u001a:oC2L!AP\u001e\u0003\u000f1{wmZ5oO\u00061\u0001.\u00198eY\u0016\u0004$!Q#\u0011\u000b\u001d\u0012%\u0006\u0012\u001c\n\u0005\r3\"!\u0005\"bg\u0016\u001c\u0006.\u001e4gY\u0016D\u0015M\u001c3mKB\u00111&\u0012\u0003\n\r\u0006\t\t\u0011!A\u0003\u0002=\u00121a\u0018\u00132\u0003=\u0011Gn\\2lg\nK\u0018\t\u001a3sKN\u001c\bcA%R):\u0011!j\u0014\b\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001b:\na\u0001\u0010:p_Rt\u0014\"A\u0012\n\u0005A\u0013\u0013a\u00029bG.\fw-Z\u0005\u0003%N\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003!\n\u0002B!I+X;&\u0011aK\t\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0005a[V\"A-\u000b\u0005iC\u0012aB:u_J\fw-Z\u0005\u00039f\u0013aB\u00117pG.l\u0015M\\1hKJLE\rE\u0002_C\u000el\u0011a\u0018\u0006\u0003A\n\n!bY8mY\u0016\u001cG/[8o\u0013\t\u0011wLA\u0002TKF\u0004R!\t3gS2L!!\u001a\u0012\u0003\rQ+\b\u000f\\34!\tAv-\u0003\u0002i3\n9!\t\\8dW&#\u0007CA\u0011k\u0013\tY'E\u0001\u0003M_:<\u0007CA\u0011n\u0013\tq'EA\u0002J]R\fqaY8oi\u0016DH\u000f\u0005\u0002re6\t\u0001$\u0003\u0002t1\tYA+Y:l\u0007>tG/\u001a=u\u0003-\u0011X-\u00193NKR\u0014\u0018nY:\u0011\u0005\u001d2\u0018BA<\u0017\u0005i\u0019\u0006.\u001e4gY\u0016\u0014V-\u00193NKR\u0014\u0018nY:SKB|'\u000f^3s\u0003E\u0019XM]5bY&TXM]'b]\u0006<WM\u001d\t\u0003uvl\u0011a\u001f\u0006\u0003yb\t!b]3sS\u0006d\u0017N_3s\u0013\tq8PA\tTKJL\u0017\r\\5{KJl\u0015M\\1hKJ\fAB\u00197pG.l\u0015M\\1hKJ\u00042\u0001WA\u0002\u0013\r\t)!\u0017\u0002\r\u00052|7m['b]\u0006<WM]\u0001\u0011[\u0006\u0004x*\u001e;qkR$&/Y2lKJ\u00042!]A\u0006\u0013\r\ti\u0001\u0007\u0002\u0011\u001b\u0006\u0004x*\u001e;qkR$&/Y2lKJ\f\u0001c\u001d5pk2$')\u0019;dQ\u001a+Go\u00195\u0011\u0007\u0005\n\u0019\"C\u0002\u0002\u0016\t\u0012qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0013\u00037\ti\"a\n\u0002*\u0005-\u0012QFA\u0018\u0003c\t\u0019\u0004\u0005\u0003(\u0001)2\u0004BB \n\u0001\u0004\ty\u0002\r\u0003\u0002\"\u0005\u0015\u0002CB\u0014CU\u0005\rb\u0007E\u0002,\u0003K!!BRA\u000f\u0003\u0003\u0005\tQ!\u00010\u0011\u00159\u0015\u00021\u0001I\u0011\u0015y\u0017\u00021\u0001q\u0011\u0015!\u0018\u00021\u0001v\u0011\u001dA\u0018\u0002%AA\u0002eD\u0001b`\u0005\u0011\u0002\u0003\u0007\u0011\u0011\u0001\u0005\n\u0003\u000fI\u0001\u0013!a\u0001\u0003\u0013A\u0011\"a\u0004\n!\u0003\u0005\r!!\u0005\u0002\u0007\u0011,\u0007/\u0006\u0002\u0002:A\"\u00111HA\"!\u001d\t\u0018Q\b\u0016\u0002BYJ1!a\u0010\u0019\u0005E\u0019\u0006.\u001e4gY\u0016$U\r]3oI\u0016t7-\u001f\t\u0004W\u0005\rC!\u0003$\u0002\u0003\u0003\u0005\tQ!\u00010\u0003\u0011!W\r\u001d\u0011\u00029\u0019,Go\u00195D_:$\u0018N\\;pkN\u0014En\\2lg&s')\u0019;dQV\u0011\u0011\u0011C\u0001\u0005e\u0016\fG\r\u0006\u0002\u0002PA!\u0011*UA)!\u0015\t\u00131\u000b\u00167\u0013\r\t)F\t\u0002\t!J|G-^2ue\u00059\"\t\\8dWN#xN]3TQV4g\r\\3SK\u0006$WM\u001d\t\u0003O=\u0019\"a\u0004\u0011\u0015\u0005\u0005e\u0013a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0004\u0002d\u0005e\u00141P\u000b\u0003\u0003KR3!_A4W\t\tI\u0007\u0005\u0003\u0002l\u0005UTBAA7\u0015\u0011\ty'!\u001d\u0002\u0013Ut7\r[3dW\u0016$'bAA:E\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005]\u0014Q\u000e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!B\u0017\u0012\u0005\u0004yC!\u0002\u001d\u0012\u0005\u0004y\u0013a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$c'\u0006\u0004\u0002\u0002\u0006\u0015\u0015qQ\u000b\u0003\u0003\u0007SC!!\u0001\u0002h\u0011)QF\u0005b\u0001_\u0011)\u0001H\u0005b\u0001_\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uI]*b!!$\u0002\u0012\u0006MUCAAHU\u0011\tI!a\u001a\u0005\u000b5\u001a\"\u0019A\u0018\u0005\u000ba\u001a\"\u0019A\u0018\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00139+\u0019\tI*!(\u0002 V\u0011\u00111\u0014\u0016\u0005\u0003#\t9\u0007B\u0003.)\t\u0007q\u0006B\u00039)\t\u0007q\u0006"
)
public class BlockStoreShuffleReader implements ShuffleReader, Logging {
   private final Iterator blocksByAddress;
   private final TaskContext context;
   private final ShuffleReadMetricsReporter readMetrics;
   private final SerializerManager serializerManager;
   private final BlockManager blockManager;
   private final MapOutputTracker mapOutputTracker;
   private final boolean shouldBatchFetch;
   private final ShuffleDependency dep;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$8() {
      return BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$8();
   }

   public static MapOutputTracker $lessinit$greater$default$7() {
      return BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$7();
   }

   public static BlockManager $lessinit$greater$default$6() {
      return BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$6();
   }

   public static SerializerManager $lessinit$greater$default$5() {
      return BlockStoreShuffleReader$.MODULE$.$lessinit$greater$default$5();
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

   private ShuffleDependency dep() {
      return this.dep;
   }

   private boolean fetchContinuousBlocksInBatch() {
      SparkConf conf = SparkEnv$.MODULE$.get().conf();
      boolean serializerRelocatable = this.dep().serializer().supportsRelocationOfSerializedObjects();
      boolean compressed = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_COMPRESS()));
      boolean codecConcatenation = compressed ? CompressionCodec$.MODULE$.supportsConcatenationOfSerializedStreams(CompressionCodec$.MODULE$.createCodec(conf)) : true;
      boolean useOldFetchProtocol = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.SHUFFLE_USE_OLD_FETCH_PROTOCOL()));
      boolean ioEncryption = BoxesRunTime.unboxToBoolean(conf.get(package$.MODULE$.IO_ENCRYPTION_ENABLED()));
      boolean doBatchFetch = this.shouldBatchFetch && serializerRelocatable && (!compressed || codecConcatenation) && !useOldFetchProtocol && !ioEncryption;
      if (this.shouldBatchFetch && !doBatchFetch) {
         this.logDebug((Function0)(() -> "The feature tag of continuous shuffle block fetching is set to true, but we can not enable the feature because other conditions are not satisfied. Shuffle compress: " + compressed + ", serializer relocatable: " + serializerRelocatable + ", codec concatenation: " + codecConcatenation + ", use old shuffle fetch protocol: " + useOldFetchProtocol + ", io encryption: " + ioEncryption + "."));
      }

      return doBatchFetch;
   }

   public Iterator read() {
      Iterator wrappedStreams = (new ShuffleBlockFetcherIterator(this.context, this.blockManager.blockStoreClient(), this.blockManager, this.mapOutputTracker, this.blocksByAddress, (blockId, s) -> this.serializerManager.wrapStream(blockId, s), BoxesRunTime.unboxToLong(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.REDUCER_MAX_SIZE_IN_FLIGHT())) * 1024L * 1024L, BoxesRunTime.unboxToInt(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.REDUCER_MAX_REQS_IN_FLIGHT())), BoxesRunTime.unboxToInt(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.REDUCER_MAX_BLOCKS_IN_FLIGHT_PER_ADDRESS())), BoxesRunTime.unboxToLong(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM())), BoxesRunTime.unboxToInt(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_MAX_ATTEMPTS_ON_NETTY_OOM())), BoxesRunTime.unboxToBoolean(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_DETECT_CORRUPT())), BoxesRunTime.unboxToBoolean(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_DETECT_CORRUPT_MEMORY())), BoxesRunTime.unboxToBoolean(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_CHECKSUM_ENABLED())), (String)SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_CHECKSUM_ALGORITHM()), this.readMetrics, this.fetchContinuousBlocksInBatch(), ShuffleBlockFetcherIterator$.MODULE$.$lessinit$greater$default$18())).toCompletionIterator();
      SerializerInstance serializerInstance = this.dep().serializer().newInstance();
      Iterator recordIter = wrappedStreams.flatMap((x0$1) -> {
         if (x0$1 != null) {
            InputStream wrappedStream = (InputStream)x0$1._2();
            return serializerInstance.deserializeStream(wrappedStream).asKeyValueIterator();
         } else {
            throw new MatchError(x0$1);
         }
      });
      CompletionIterator metricIter = CompletionIterator$.MODULE$.apply(recordIter.map((record) -> {
         this.readMetrics.incRecordsRead(1L);
         return record;
      }), (JFunction0.mcV.sp)() -> this.context.taskMetrics().mergeShuffleReadMetrics());
      InterruptibleIterator interruptibleIter = new InterruptibleIterator(this.context, metricIter);
      Object var27;
      if (this.dep().keyOrdering().isDefined()) {
         ExternalSorter var10000;
         if (this.dep().aggregator().isDefined()) {
            if (this.dep().mapSideCombine()) {
               TaskContext x$1 = this.context;
               Option x$2 = .MODULE$.apply(new Aggregator((x) -> scala.Predef..MODULE$.identity(x), ((Aggregator)this.dep().aggregator().get()).mergeCombiners(), ((Aggregator)this.dep().aggregator().get()).mergeCombiners()));
               Some x$3 = new Some(this.dep().keyOrdering().get());
               Serializer x$4 = this.dep().serializer();
               Option x$5 = ExternalSorter$.MODULE$.$lessinit$greater$default$3();
               var10000 = new ExternalSorter(x$1, x$2, x$5, x$3, x$4);
            } else {
               TaskContext x$6 = this.context;
               Option x$7 = this.dep().aggregator();
               Some x$8 = new Some(this.dep().keyOrdering().get());
               Serializer x$9 = this.dep().serializer();
               Option x$10 = ExternalSorter$.MODULE$.$lessinit$greater$default$3();
               var10000 = new ExternalSorter(x$6, x$7, x$10, x$8, x$9);
            }
         } else {
            TaskContext x$11 = this.context;
            Some x$12 = new Some(this.dep().keyOrdering().get());
            Serializer x$13 = this.dep().serializer();
            None x$14 = ExternalSorter$.MODULE$.$lessinit$greater$default$2();
            Option x$15 = ExternalSorter$.MODULE$.$lessinit$greater$default$3();
            var10000 = new ExternalSorter(x$11, x$14, x$15, x$12, x$13);
         }

         ExternalSorter sorter = var10000;
         var27 = sorter.insertAllAndUpdateMetrics(interruptibleIter);
      } else {
         var27 = this.dep().aggregator().isDefined() ? (this.dep().mapSideCombine() ? ((Aggregator)this.dep().aggregator().get()).combineCombinersByKey(interruptibleIter, this.context) : ((Aggregator)this.dep().aggregator().get()).combineValuesByKey(interruptibleIter, this.context)) : interruptibleIter;
      }

      Iterator resultIter = (Iterator)var27;
      return (Iterator)(resultIter instanceof InterruptibleIterator ? resultIter : new InterruptibleIterator(this.context, resultIter));
   }

   public BlockStoreShuffleReader(final BaseShuffleHandle handle, final Iterator blocksByAddress, final TaskContext context, final ShuffleReadMetricsReporter readMetrics, final SerializerManager serializerManager, final BlockManager blockManager, final MapOutputTracker mapOutputTracker, final boolean shouldBatchFetch) {
      this.blocksByAddress = blocksByAddress;
      this.context = context;
      this.readMetrics = readMetrics;
      this.serializerManager = serializerManager;
      this.blockManager = blockManager;
      this.mapOutputTracker = mapOutputTracker;
      this.shouldBatchFetch = shouldBatchFetch;
      Logging.$init$(this);
      this.dep = handle.dependency();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
