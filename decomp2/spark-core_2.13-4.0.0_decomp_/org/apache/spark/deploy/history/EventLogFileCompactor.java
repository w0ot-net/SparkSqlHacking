package org.apache.spark.deploy.history;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.ServiceLoader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.scheduler.ReplayListenerBus;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.scheduler.SparkListenerInterface;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001\u0002\b\u0010\u0001iA\u0001b\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tY\u0001\u0011\t\u0011)A\u0005[!AQ\u0007\u0001B\u0001B\u0003%a\u0007\u0003\u0005<\u0001\t\u0005\t\u0015!\u0003=\u0011!y\u0004A!A!\u0002\u0013\u0001\u0005\"B\"\u0001\t\u0003!\u0005\"\u0002'\u0001\t\u0003i\u0005\"\u00022\u0001\t\u0013\u0019\u0007\"\u00025\u0001\t\u0013I\u0007\"B;\u0001\t\u00131\b\u0002CA\u000b\u0001\u0011\u0005q\"a\u0006\t\u000f\u0005]\u0002\u0001\"\u0003\u0002:!9\u0011Q\b\u0001\u0005\n\u0005}\"!F#wK:$Hj\\4GS2,7i\\7qC\u000e$xN\u001d\u0006\u0003!E\tq\u0001[5ti>\u0014\u0018P\u0003\u0002\u0013'\u00051A-\u001a9m_fT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\u0002\u0001'\r\u00011$\t\t\u00039}i\u0011!\b\u0006\u0002=\u0005)1oY1mC&\u0011\u0001%\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\t*S\"A\u0012\u000b\u0005\u0011\u001a\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0019\u001a#a\u0002'pO\u001eLgnZ\u0001\ngB\f'o[\"p]\u001a\u0004\"!\u000b\u0016\u000e\u0003MI!aK\n\u0003\u0013M\u0003\u0018M]6D_:4\u0017A\u00035bI>|\u0007oQ8oMB\u0011afM\u0007\u0002_)\u0011\u0001'M\u0001\u0005G>tgM\u0003\u00023+\u00051\u0001.\u00193p_BL!\u0001N\u0018\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\t17\u000f\u0005\u00028s5\t\u0001H\u0003\u00026c%\u0011!\b\u000f\u0002\u000b\r&dWmU=ti\u0016l\u0017\u0001E7bq\u001aKG.Z:U_J+G/Y5o!\taR(\u0003\u0002?;\t\u0019\u0011J\u001c;\u00021\r|W\u000e]1di&|g\u000e\u00165sKNDw\u000e\u001c3TG>\u0014X\r\u0005\u0002\u001d\u0003&\u0011!)\b\u0002\u0007\t>,(\r\\3\u0002\rqJg.\u001b;?)\u0019)u\tS%K\u0017B\u0011a\tA\u0007\u0002\u001f!)qE\u0002a\u0001Q!)AF\u0002a\u0001[!)QG\u0002a\u0001m!)1H\u0002a\u0001y!)qH\u0002a\u0001\u0001\u000691m\\7qC\u000e$HC\u0001(R!\t1u*\u0003\u0002Q\u001f\t\u00012i\\7qC\u000e$\u0018n\u001c8SKN,H\u000e\u001e\u0005\u0006%\u001e\u0001\raU\u0001\u000eKZ,g\u000e\u001e'pO\u001aKG.Z:\u0011\u0007QcvL\u0004\u0002V5:\u0011a+W\u0007\u0002/*\u0011\u0001,G\u0001\u0007yI|w\u000e\u001e \n\u0003yI!aW\u000f\u0002\u000fA\f7m[1hK&\u0011QL\u0018\u0002\u0004'\u0016\f(BA.\u001e!\t9\u0004-\u0003\u0002bq\tQa)\u001b7f'R\fG/^:\u0002%\u0005\u001c8/\u001a:u!J,7m\u001c8eSRLwN\u001c\u000b\u0003I\u001e\u0004\"\u0001H3\n\u0005\u0019l\"\u0001B+oSRDQA\u0015\u0005A\u0002M\u000b!#\u001b8ji&\fG.\u001b>f\u0005VLG\u000eZ3sgR\u0019!N\\8\u0011\u0007Qc6\u000e\u0005\u0002GY&\u0011Qn\u0004\u0002\u0013\u000bZ,g\u000e\u001e$jYR,'OQ;jY\u0012,'\u000fC\u00036\u0013\u0001\u0007a\u0007C\u0003q\u0013\u0001\u0007\u0011/A\u0003gS2,7\u000fE\u0002U9J\u0004\"aN:\n\u0005QD$\u0001\u0002)bi\"\fabY1mGVd\u0017\r^3TG>\u0014X\r\u0006\u0002Ao\")\u0001P\u0003a\u0001s\u0006)1\u000f^1ugB\u0019!0a\u0004\u000f\u0007m\fYAD\u0002}\u0003\u0013q1!`A\u0004\u001d\rq\u0018Q\u0001\b\u0004\u007f\u0006\rab\u0001,\u0002\u0002%\t\u0001$\u0003\u0002\u0017/%\u0011A#F\u0005\u0003%MI!\u0001E\t\n\u0007\u00055q\"A\u0006Fm\u0016tGOR5mi\u0016\u0014\u0018\u0002BA\t\u0003'\u0011\u0001CR5mi\u0016\u00148\u000b^1uSN$\u0018nY:\u000b\u0007\u00055q\"A\u0004sK^\u0014\u0018\u000e^3\u0015\r\u0005e\u0011\u0011FA\u001b!\u0011\tY\"a\t\u000f\t\u0005u\u0011q\u0004\t\u0003-vI1!!\t\u001e\u0003\u0019\u0001&/\u001a3fM&!\u0011QEA\u0014\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011E\u000f\t\u000f\u0005-2\u00021\u0001\u0002.\u00059a-\u001b7uKJ\u001c\b\u0003\u0002+]\u0003_\u00012ARA\u0019\u0013\r\t\u0019d\u0004\u0002\f\u000bZ,g\u000e\u001e$jYR,'\u000fC\u0003S\u0017\u0001\u00071+A\u000bdY\u0016\fg.\u001e9D_6\u0004\u0018m\u0019;fI\u001aKG.Z:\u0015\u0007\u0011\fY\u0004C\u0003q\u0019\u0001\u00071+\u0001\ngS:$g)\u001b7fgR{7i\\7qC\u000e$HcA*\u0002B!)!+\u0004a\u0001'\u0002"
)
public class EventLogFileCompactor implements Logging {
   private final SparkConf sparkConf;
   private final Configuration hadoopConf;
   private final FileSystem fs;
   private final int maxFilesToRetain;
   private final double compactionThresholdScore;
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

   public CompactionResult compact(final Seq eventLogFiles) {
      this.assertPrecondition(eventLogFiles);
      if (eventLogFiles.length() < this.maxFilesToRetain) {
         return new CompactionResult(CompactionResultCode$.MODULE$.NOT_ENOUGH_FILES(), .MODULE$);
      } else {
         Seq filesToCompact = this.findFilesToCompact(eventLogFiles);
         if (filesToCompact.isEmpty()) {
            return new CompactionResult(CompactionResultCode$.MODULE$.NOT_ENOUGH_FILES(), .MODULE$);
         } else {
            Seq builders = this.initializeBuilders(this.fs, (Seq)filesToCompact.map((x$1) -> x$1.getPath()));
            Seq filters = (Seq)builders.map((x$2) -> x$2.createFilter());
            double minScore = BoxesRunTime.unboxToDouble(((IterableOnceOps)((IterableOps)filters.flatMap((x$3) -> x$3.statistics())).map((stats) -> BoxesRunTime.boxToDouble($anonfun$compact$4(this, stats)))).min(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
            if (minScore < this.compactionThresholdScore) {
               return new CompactionResult(CompactionResultCode$.MODULE$.LOW_SCORE_FOR_COMPACTION(), .MODULE$);
            } else {
               this.rewrite(filters, filesToCompact);
               this.cleanupCompactedFiles(filesToCompact);
               return new CompactionResult(CompactionResultCode$.MODULE$.SUCCESS(), new Some(BoxesRunTime.boxToLong(RollingEventLogFilesWriter$.MODULE$.getEventLogFileIndex(((FileStatus)filesToCompact.last()).getPath().getName()))));
            }
         }
      }
   }

   private void assertPrecondition(final Seq eventLogFiles) {
      Seq idxCompactedFiles = (Seq)((IterableOps)eventLogFiles.zipWithIndex()).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$assertPrecondition$1(x0$1)));
      scala.Predef..MODULE$.require(idxCompactedFiles.size() < 2 && idxCompactedFiles.headOption().forall((x$4) -> BoxesRunTime.boxToBoolean($anonfun$assertPrecondition$2(x$4))), () -> "The number of compact files should be at most 1, and should be placed first if exists.");
   }

   private Seq initializeBuilders(final FileSystem fs, final Seq files) {
      ReplayListenerBus bus = new ReplayListenerBus();
      Seq builders = scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(ServiceLoader.load(EventFilterBuilder.class, Utils$.MODULE$.getContextOrSparkClassLoader())).asScala().toSeq();
      builders.foreach((listener) -> {
         $anonfun$initializeBuilders$1(bus, listener);
         return BoxedUnit.UNIT;
      });
      files.foreach((log) -> BoxesRunTime.boxToBoolean($anonfun$initializeBuilders$2(fs, bus, log)));
      return builders;
   }

   private double calculateScore(final EventFilter.FilterStatistics stats) {
      return (double)(stats.totalTasks() - stats.liveTasks()) * (double)1.0F / (double)stats.totalTasks();
   }

   public String rewrite(final Seq filters, final Seq eventLogFiles) {
      scala.Predef..MODULE$.require(eventLogFiles.nonEmpty());
      Path lastIndexEventLogPath = ((FileStatus)eventLogFiles.last()).getPath();
      CompactedEventLogFileWriter logWriter = new CompactedEventLogFileWriter(lastIndexEventLogPath, "dummy", .MODULE$, lastIndexEventLogPath.getParent().toUri(), this.sparkConf, this.hadoopConf);
      long startTime = System.currentTimeMillis();
      logWriter.start();
      eventLogFiles.foreach((file) -> {
         $anonfun$rewrite$1(this, filters, logWriter, file);
         return BoxedUnit.UNIT;
      });
      logWriter.stop();
      long duration = System.currentTimeMillis() - startTime;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Finished rewriting eventLog files to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, logWriter.logPath())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" took ", " ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(duration))}))))));
      return logWriter.logPath();
   }

   private void cleanupCompactedFiles(final Seq files) {
      files.foreach((file) -> {
         $anonfun$cleanupCompactedFiles$1(this, file);
         return BoxedUnit.UNIT;
      });
   }

   private Seq findFilesToCompact(final Seq eventLogFiles) {
      int numNormalEventLogFiles = EventLogFileWriter$.MODULE$.isCompacted(((FileStatus)eventLogFiles.head()).getPath()) ? eventLogFiles.length() - 1 : eventLogFiles.length();
      return numNormalEventLogFiles > this.maxFilesToRetain ? (Seq)eventLogFiles.dropRight(this.maxFilesToRetain) : (Seq)scala.package..MODULE$.Seq().empty();
   }

   // $FF: synthetic method
   public static final double $anonfun$compact$4(final EventLogFileCompactor $this, final EventFilter.FilterStatistics stats) {
      return $this.calculateScore(stats);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertPrecondition$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         FileStatus file = (FileStatus)x0$1._1();
         return EventLogFileWriter$.MODULE$.isCompacted(file.getPath());
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertPrecondition$2(final Tuple2 x$4) {
      return x$4._2$mcI$sp() == 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$initializeBuilders$1(final ReplayListenerBus bus$1, final SparkListenerInterface listener) {
      bus$1.addListener(listener);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initializeBuilders$4(final ReplayListenerBus bus$1, final Path log$1, final InputStream in) {
      return bus$1.replay(in, log$1.getName(), bus$1.replay$default$3(), bus$1.replay$default$4());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initializeBuilders$2(final FileSystem fs$1, final ReplayListenerBus bus$1, final Path log) {
      return BoxesRunTime.unboxToBoolean(Utils$.MODULE$.tryWithResource(() -> EventLogFileReader$.MODULE$.openEventLog(log, fs$1), (in) -> BoxesRunTime.boxToBoolean($anonfun$initializeBuilders$4(bus$1, log, in))));
   }

   // $FF: synthetic method
   public static final void $anonfun$rewrite$2(final CompactedEventLogFileWriter logWriter$1, final String line, final SparkListenerEvent x$5) {
      logWriter$1.writeEvent(line, true);
   }

   // $FF: synthetic method
   public static final void $anonfun$rewrite$3(final String x$6, final SparkListenerEvent x$7) {
   }

   // $FF: synthetic method
   public static final void $anonfun$rewrite$4(final CompactedEventLogFileWriter logWriter$1, final String line) {
      logWriter$1.writeEvent(line, true);
   }

   // $FF: synthetic method
   public static final void $anonfun$rewrite$1(final EventLogFileCompactor $this, final Seq filters$1, final CompactedEventLogFileWriter logWriter$1, final FileStatus file) {
      EventFilter$.MODULE$.applyFilterToFile($this.fs, filters$1, file.getPath(), (line, x$5) -> {
         $anonfun$rewrite$2(logWriter$1, line, x$5);
         return BoxedUnit.UNIT;
      }, (x$6, x$7) -> {
         $anonfun$rewrite$3(x$6, x$7);
         return BoxedUnit.UNIT;
      }, (line) -> {
         $anonfun$rewrite$4(logWriter$1, line);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$cleanupCompactedFiles$1(final EventLogFileCompactor $this, final FileStatus file) {
      boolean deleted = false;

      try {
         deleted = $this.fs.delete(file.getPath(), true);
      } catch (IOException var3) {
      }

      if (!deleted) {
         $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to remove ", " / skip removing."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file.getPath())})))));
      }
   }

   public EventLogFileCompactor(final SparkConf sparkConf, final Configuration hadoopConf, final FileSystem fs, final int maxFilesToRetain, final double compactionThresholdScore) {
      this.sparkConf = sparkConf;
      this.hadoopConf = hadoopConf;
      this.fs = fs;
      this.maxFilesToRetain = maxFilesToRetain;
      this.compactionThresholdScore = compactionThresholdScore;
      Logging.$init$(this);
      scala.Predef..MODULE$.require(maxFilesToRetain > 0, () -> "Max event log files to retain should be higher than 0.");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
