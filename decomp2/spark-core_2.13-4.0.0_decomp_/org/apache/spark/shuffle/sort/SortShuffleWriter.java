package org.apache.spark.shuffle.sort;

import java.util.Map;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MapStatus$;
import org.apache.spark.shuffle.BaseShuffleHandle;
import org.apache.spark.shuffle.ShuffleWriteMetricsReporter;
import org.apache.spark.shuffle.ShuffleWriter;
import org.apache.spark.shuffle.api.ShuffleExecutorComponents;
import org.apache.spark.shuffle.api.ShuffleMapOutputWriter;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.util.collection.ExternalSorter;
import org.slf4j.Logger;
import scala.Function0;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg!\u0002\u0010 \u0001\rJ\u0003\u0002C$\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u00119\u0003!\u0011!Q\u0001\n=C\u0001B\u0015\u0001\u0003\u0002\u0003\u0006Ia\u0015\u0005\t/\u0002\u0011\t\u0011)A\u00051\"A1\f\u0001B\u0001B\u0003%A\fC\u0003c\u0001\u0011\u00051\rC\u0004l\u0001\t\u0007I\u0011\u00027\t\rA\u0004\u0001\u0015!\u0003n\u0011\u001d\t\bA1A\u0005\nIDa!\u001f\u0001!\u0002\u0013\u0019\bb\u0002>\u0001\u0001\u0004%Ia\u001f\u0005\n\u0003'\u0001\u0001\u0019!C\u0005\u0003+Aq!!\u0005\u0001A\u0003&A\u0010C\u0005\u0002*\u0001\u0001\r\u0011\"\u0003\u0002,!I\u00111\u0007\u0001A\u0002\u0013%\u0011Q\u0007\u0005\t\u0003s\u0001\u0001\u0015)\u0003\u0002.!I\u00111\b\u0001A\u0002\u0013%\u0011Q\b\u0005\n\u0003\u0017\u0002\u0001\u0019!C\u0005\u0003\u001bB\u0001\"!\u0015\u0001A\u0003&\u0011q\b\u0005\f\u0003'\u0002\u0001\u0019!a\u0001\n\u0013\t)\u0006C\u0006\u0002^\u0001\u0001\r\u00111A\u0005\n\u0005}\u0003bCA2\u0001\u0001\u0007\t\u0011)Q\u0005\u0003/Bq!!\u001a\u0001\t\u0003\n9\u0007C\u0004\u0002\f\u0002!\t%!$\t\u000f\u0005e\u0005\u0001\"\u0011\u0002\u001c\u001eA\u0011QT\u0010\t\u0002\r\nyJB\u0004\u001f?!\u00051%!)\t\r\t\\B\u0011AAU\u0011\u001d\tYk\u0007C\u0001\u0003[\u0013\u0011cU8siNCWO\u001a4mK^\u0013\u0018\u000e^3s\u0015\t\u0001\u0013%\u0001\u0003t_J$(B\u0001\u0012$\u0003\u001d\u0019\b.\u001e4gY\u0016T!\u0001J\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0019:\u0013AB1qC\u000eDWMC\u0001)\u0003\ry'oZ\u000b\u0005UEzDjE\u0002\u0001W\u0005\u0003B\u0001L\u00170}5\t\u0011%\u0003\u0002/C\ti1\u000b[;gM2,wK]5uKJ\u0004\"\u0001M\u0019\r\u0001\u0011)!\u0007\u0001b\u0001i\t\t1j\u0001\u0001\u0012\u0005UZ\u0004C\u0001\u001c:\u001b\u00059$\"\u0001\u001d\u0002\u000bM\u001c\u0017\r\\1\n\u0005i:$a\u0002(pi\"Lgn\u001a\t\u0003mqJ!!P\u001c\u0003\u0007\u0005s\u0017\u0010\u0005\u00021\u007f\u0011)\u0001\t\u0001b\u0001i\t\ta\u000b\u0005\u0002C\u000b6\t1I\u0003\u0002EG\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002G\u0007\n9Aj\\4hS:<\u0017A\u00025b]\u0012dW\rE\u0003-\u0013>r4*\u0003\u0002KC\t\t\")Y:f'\",hM\u001a7f\u0011\u0006tG\r\\3\u0011\u0005AbE!B'\u0001\u0005\u0004!$!A\"\u0002\u000b5\f\u0007/\u00133\u0011\u0005Y\u0002\u0016BA)8\u0005\u0011auN\\4\u0002\u000f\r|g\u000e^3yiB\u0011A+V\u0007\u0002G%\u0011ak\t\u0002\f)\u0006\u001c8nQ8oi\u0016DH/\u0001\u0007xe&$X-T3ue&\u001c7\u000f\u0005\u0002-3&\u0011!,\t\u0002\u001c'\",hM\u001a7f/JLG/Z'fiJL7m\u001d*fa>\u0014H/\u001a:\u00023MDWO\u001a4mK\u0016CXmY;u_J\u001cu.\u001c9p]\u0016tGo\u001d\t\u0003;\u0002l\u0011A\u0018\u0006\u0003?\u0006\n1!\u00199j\u0013\t\tgLA\rTQV4g\r\\3Fq\u0016\u001cW\u000f^8s\u0007>l\u0007o\u001c8f]R\u001c\u0018A\u0002\u001fj]&$h\b\u0006\u0004eM\u001eD\u0017N\u001b\t\u0006K\u0002ychS\u0007\u0002?!)qI\u0002a\u0001\u0011\")aJ\u0002a\u0001\u001f\")!K\u0002a\u0001'\")qK\u0002a\u00011\")1L\u0002a\u00019\u0006\u0019A-\u001a9\u0016\u00035\u0004R\u0001\u001680}-K!a\\\u0012\u0003#MCWO\u001a4mK\u0012+\u0007/\u001a8eK:\u001c\u00170\u0001\u0003eKB\u0004\u0013\u0001\u00042m_\u000e\\W*\u00198bO\u0016\u0014X#A:\u0011\u0005Q<X\"A;\u000b\u0005Y\u001c\u0013aB:u_J\fw-Z\u0005\u0003qV\u0014AB\u00117pG.l\u0015M\\1hKJ\fQB\u00197pG.l\u0015M\\1hKJ\u0004\u0013AB:peR,'/F\u0001}a\ri\u0018Q\u0002\t\b}\u0006\u001dqFPA\u0006\u001b\u0005y(\u0002BA\u0001\u0003\u0007\t!bY8mY\u0016\u001cG/[8o\u0015\r\t)aI\u0001\u0005kRLG.C\u0002\u0002\n}\u0014a\"\u0012=uKJt\u0017\r\\*peR,'\u000fE\u00021\u0003\u001b!!\"a\u0004\u000e\u0003\u0003\u0005\tQ!\u00015\u0005\ryF%M\u0001\bg>\u0014H/\u001a:!\u0003)\u0019xN\u001d;fe~#S-\u001d\u000b\u0005\u0003/\ti\u0002E\u00027\u00033I1!a\u00078\u0005\u0011)f.\u001b;\t\u0013\u0005}A\"!AA\u0002\u0005\u0005\u0012a\u0001=%cA\"\u00111EA\u0014!\u001dq\u0018qA\u0018?\u0003K\u00012\u0001MA\u0014\t-\ty!!\b\u0002\u0002\u0003\u0005)\u0011\u0001\u001b\u0002\u0011M$x\u000e\u001d9j]\u001e,\"!!\f\u0011\u0007Y\ny#C\u0002\u00022]\u0012qAQ8pY\u0016\fg.\u0001\u0007ti>\u0004\b/\u001b8h?\u0012*\u0017\u000f\u0006\u0003\u0002\u0018\u0005]\u0002\"CA\u0010\u001f\u0005\u0005\t\u0019AA\u0017\u0003%\u0019Ho\u001c9qS:<\u0007%A\u0005nCB\u001cF/\u0019;vgV\u0011\u0011q\b\t\u0005\u0003\u0003\n9%\u0004\u0002\u0002D)\u0019\u0011QI\u0012\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018\u0002BA%\u0003\u0007\u0012\u0011\"T1q'R\fG/^:\u0002\u001b5\f\u0007o\u0015;biV\u001cx\fJ3r)\u0011\t9\"a\u0014\t\u0013\u0005}!#!AA\u0002\u0005}\u0012AC7baN#\u0018\r^;tA\u0005\u0001\u0002/\u0019:uSRLwN\u001c'f]\u001e$\bn]\u000b\u0003\u0003/\u0002BANA-\u001f&\u0019\u00111L\u001c\u0003\u000b\u0005\u0013(/Y=\u0002)A\f'\u000f^5uS>tG*\u001a8hi\"\u001cx\fJ3r)\u0011\t9\"!\u0019\t\u0013\u0005}Q#!AA\u0002\u0005]\u0013!\u00059beRLG/[8o\u0019\u0016tw\r\u001e5tA\u0005)qO]5uKR!\u0011qCA5\u0011\u001d\tYg\u0006a\u0001\u0003[\nqA]3d_J$7\u000f\u0005\u0004\u0002p\u0005}\u0014Q\u0011\b\u0005\u0003c\nYH\u0004\u0003\u0002t\u0005eTBAA;\u0015\r\t9hM\u0001\u0007yI|w\u000e\u001e \n\u0003aJ1!! 8\u0003\u001d\u0001\u0018mY6bO\u0016LA!!!\u0002\u0004\nA\u0011\n^3sCR|'OC\u0002\u0002~]\u0002RANAD_yJ1!!#8\u0005!\u0001&o\u001c3vGR\u0014\u0014\u0001B:u_B$B!a$\u0002\u0016B)a'!%\u0002@%\u0019\u00111S\u001c\u0003\r=\u0003H/[8o\u0011\u001d\t9\n\u0007a\u0001\u0003[\tqa];dG\u0016\u001c8/A\nhKR\u0004\u0016M\u001d;ji&|g\u000eT3oORD7\u000f\u0006\u0002\u0002X\u0005\t2k\u001c:u'\",hM\u001a7f/JLG/\u001a:\u0011\u0005\u0015\\2cA\u000e\u0002$B\u0019a'!*\n\u0007\u0005\u001dvG\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003?\u000bQc\u001d5pk2$')\u001f9bgNlUM]4f'>\u0014H\u000f\u0006\u0004\u0002.\u0005=\u0016\u0011\u0018\u0005\b\u0003ck\u0002\u0019AAZ\u0003\u0011\u0019wN\u001c4\u0011\u0007Q\u000b),C\u0002\u00028\u000e\u0012\u0011b\u00159be.\u001cuN\u001c4\t\r-l\u0002\u0019AA^a!\ti,!1\u0002H\u00065\u0007\u0003\u0003+o\u0003\u007f\u000b)-a3\u0011\u0007A\n\t\rB\u0006\u0002D\u0006e\u0016\u0011!A\u0001\u0006\u0003!$aA0%eA\u0019\u0001'a2\u0005\u0017\u0005%\u0017\u0011XA\u0001\u0002\u0003\u0015\t\u0001\u000e\u0002\u0004?\u0012\u001a\u0004c\u0001\u0019\u0002N\u0012Y\u0011qZA]\u0003\u0003\u0005\tQ!\u00015\u0005\ryF\u0005\u000e"
)
public class SortShuffleWriter extends ShuffleWriter implements Logging {
   private final long mapId;
   private final TaskContext context;
   private final ShuffleWriteMetricsReporter writeMetrics;
   private final ShuffleExecutorComponents shuffleExecutorComponents;
   private final ShuffleDependency dep;
   private final BlockManager blockManager;
   private ExternalSorter sorter;
   private boolean stopping;
   private MapStatus mapStatus;
   private long[] partitionLengths;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean shouldBypassMergeSort(final SparkConf conf, final ShuffleDependency dep) {
      return SortShuffleWriter$.MODULE$.shouldBypassMergeSort(conf, dep);
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

   private BlockManager blockManager() {
      return this.blockManager;
   }

   private ExternalSorter sorter() {
      return this.sorter;
   }

   private void sorter_$eq(final ExternalSorter x$1) {
      this.sorter = x$1;
   }

   private boolean stopping() {
      return this.stopping;
   }

   private void stopping_$eq(final boolean x$1) {
      this.stopping = x$1;
   }

   private MapStatus mapStatus() {
      return this.mapStatus;
   }

   private void mapStatus_$eq(final MapStatus x$1) {
      this.mapStatus = x$1;
   }

   private long[] partitionLengths() {
      return this.partitionLengths;
   }

   private void partitionLengths_$eq(final long[] x$1) {
      this.partitionLengths = x$1;
   }

   public void write(final Iterator records) {
      this.sorter_$eq(this.dep().mapSideCombine() ? new ExternalSorter(this.context, this.dep().aggregator(), new Some(this.dep().partitioner()), this.dep().keyOrdering(), this.dep().serializer()) : new ExternalSorter(this.context, .MODULE$, new Some(this.dep().partitioner()), .MODULE$, this.dep().serializer()));
      this.sorter().insertAll(records);
      ShuffleMapOutputWriter mapOutputWriter = this.shuffleExecutorComponents.createMapOutputWriter(this.dep().shuffleId(), this.mapId, this.dep().partitioner().numPartitions());
      this.sorter().writePartitionedMapOutput(this.dep().shuffleId(), this.mapId, mapOutputWriter, this.writeMetrics);
      this.partitionLengths_$eq(mapOutputWriter.commitAllPartitions(this.sorter().getChecksums()).getPartitionLengths());
      this.mapStatus_$eq(MapStatus$.MODULE$.apply(this.blockManager().shuffleServerId(), this.partitionLengths(), this.mapId));
   }

   public Option stop(final boolean success) {
      None var2;
      try {
         if (!this.stopping()) {
            this.stopping_$eq(true);
            Object var10000 = success ? scala.Option..MODULE$.apply(this.mapStatus()) : .MODULE$;
            return (Option)var10000;
         }

         var2 = .MODULE$;
      } finally {
         if (this.sorter() != null) {
            long startTime = System.nanoTime();
            this.sorter().stop();
            this.writeMetrics.incWriteTime(System.nanoTime() - startTime);
            this.sorter_$eq((ExternalSorter)null);
         }

      }

      return var2;
   }

   public long[] getPartitionLengths() {
      return this.partitionLengths();
   }

   public SortShuffleWriter(final BaseShuffleHandle handle, final long mapId, final TaskContext context, final ShuffleWriteMetricsReporter writeMetrics, final ShuffleExecutorComponents shuffleExecutorComponents) {
      this.mapId = mapId;
      this.context = context;
      this.writeMetrics = writeMetrics;
      this.shuffleExecutorComponents = shuffleExecutorComponents;
      Logging.$init$(this);
      this.dep = handle.dependency();
      this.blockManager = SparkEnv$.MODULE$.get().blockManager();
      this.sorter = null;
      this.stopping = false;
      this.mapStatus = null;
   }
}
