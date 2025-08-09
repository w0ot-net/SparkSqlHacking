package org.apache.spark.streaming;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.streaming.scheduler.JobGenerator;
import org.slf4j.Logger;
import scala.Function0;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed!B\u000e\u001d\u0001q!\u0003\u0002C\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u0011e\u0002!\u0011!Q\u0001\niB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u0017\")!\u000b\u0001C\u0001'\"9!\f\u0001b\u0001\n\u0003Y\u0006BB0\u0001A\u0003%A\fC\u0004a\u0001\t\u0007I\u0011A1\t\r1\u0004\u0001\u0015!\u0003c\u0011\u001di\u0007A1A\u0005\u00029Da!\u001e\u0001!\u0002\u0013y\u0007b\u0002<\u0001\u0001\u0004%Ia\u001e\u0005\bw\u0002\u0001\r\u0011\"\u0003}\u0011\u001d\t)\u0001\u0001Q!\naD\u0001\"a\u0002\u0001A\u0003&\u0011\u0011\u0002\u0005\n\u00037\u0001\u0001\u0019!C\u0005\u0003;A\u0011\"!\n\u0001\u0001\u0004%I!a\n\t\u0011\u0005-\u0002\u0001)Q\u0005\u0003?1a!a\f\u0001\u0001\u0005E\u0002BCA#'\t\u0005\t\u0015!\u0003\u0002 !Q\u0011qI\n\u0003\u0002\u0003\u0006I!!\u0013\t\u0013\u0005U3C!A!\u0002\u0013A\bB\u0002*\u0014\t\u0003\t9\u0006C\u0004\u0002dM!\t!!\u001a\t\u000f\u0005\u001d\u0004\u0001\"\u0001\u0002j!9\u0011q\u000f\u0001\u0005\u0002\u0005\u0015$\u0001E\"iK\u000e\\\u0007o\\5oi^\u0013\u0018\u000e^3s\u0015\tib$A\u0005tiJ,\u0017-\\5oO*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xmE\u0002\u0001K-\u0002\"AJ\u0015\u000e\u0003\u001dR\u0011\u0001K\u0001\u0006g\u000e\fG.Y\u0005\u0003U\u001d\u0012a!\u00118z%\u00164\u0007C\u0001\u00170\u001b\u0005i#B\u0001\u0018\u001f\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0019.\u0005\u001daunZ4j]\u001e\fAB[8c\u000f\u0016tWM]1u_J\u001c\u0001\u0001\u0005\u00025o5\tQG\u0003\u000279\u0005I1o\u00195fIVdWM]\u0005\u0003qU\u0012ABS8c\u000f\u0016tWM]1u_J\fAaY8oMB\u00111\bP\u0007\u0002=%\u0011QH\b\u0002\n'B\f'o[\"p]\u001a\fQb\u00195fG.\u0004x.\u001b8u\t&\u0014\bC\u0001!H\u001d\t\tU\t\u0005\u0002CO5\t1I\u0003\u0002Ee\u00051AH]8pizJ!AR\u0014\u0002\rA\u0013X\rZ3g\u0013\tA\u0015J\u0001\u0004TiJLgn\u001a\u0006\u0003\r\u001e\n!\u0002[1e_>\u00048i\u001c8g!\ta\u0005+D\u0001N\u0015\tIdJ\u0003\u0002PA\u00051\u0001.\u00193p_BL!!U'\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0019a\u0014N\\5u}Q)AKV,Y3B\u0011Q\u000bA\u0007\u00029!)\u0011'\u0002a\u0001g!)\u0011(\u0002a\u0001u!)a(\u0002a\u0001\u007f!)!*\u0002a\u0001\u0017\u0006aQ*\u0011-`\u0003R#V)\u0014)U'V\tA\f\u0005\u0002';&\u0011al\n\u0002\u0004\u0013:$\u0018!D'B1~\u000bE\u000bV#N!R\u001b\u0006%\u0001\u0005fq\u0016\u001cW\u000f^8s+\u0005\u0011\u0007CA2k\u001b\u0005!'BA3g\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003O\"\fA!\u001e;jY*\t\u0011.\u0001\u0003kCZ\f\u0017BA6e\u0005I!\u0006N]3bIB{w\u000e\\#yK\u000e,Ho\u001c:\u0002\u0013\u0015DXmY;u_J\u0004\u0013\u0001E2p[B\u0014Xm]:j_:\u001cu\u000eZ3d+\u0005y\u0007C\u00019t\u001b\u0005\t(B\u0001:\u001f\u0003\tIw.\u0003\u0002uc\n\u00012i\\7qe\u0016\u001c8/[8o\u0007>$WmY\u0001\u0012G>l\u0007O]3tg&|gnQ8eK\u000e\u0004\u0013aB:u_B\u0004X\rZ\u000b\u0002qB\u0011a%_\u0005\u0003u\u001e\u0012qAQ8pY\u0016\fg.A\u0006ti>\u0004\b/\u001a3`I\u0015\fHcA?\u0002\u0002A\u0011aE`\u0005\u0003\u007f\u001e\u0012A!\u00168ji\"A\u00111A\u0007\u0002\u0002\u0003\u0007\u00010A\u0002yIE\n\u0001b\u001d;paB,G\rI\u0001\u0003MN\u0004B!a\u0003\u0002\u00105\u0011\u0011Q\u0002\u0006\u0004\u0003\u000fq\u0015\u0002BA\t\u0003\u001b\u0011!BR5mKNK8\u000f^3nQ\ry\u0011Q\u0003\t\u0004M\u0005]\u0011bAA\rO\tAao\u001c7bi&dW-\u0001\u000bmCR,7\u000f^\"iK\u000e\\\u0007o\\5oiRKW.Z\u000b\u0003\u0003?\u00012!VA\u0011\u0013\r\t\u0019\u0003\b\u0002\u0005)&lW-\u0001\rmCR,7\u000f^\"iK\u000e\\\u0007o\\5oiRKW.Z0%KF$2!`A\u0015\u0011%\t\u0019!EA\u0001\u0002\u0004\ty\"A\u000bmCR,7\u000f^\"iK\u000e\\\u0007o\\5oiRKW.\u001a\u0011)\u0007I\t)B\u0001\fDQ\u0016\u001c7\u000e]8j]R<&/\u001b;f\u0011\u0006tG\r\\3s'\u0015\u0019\u00121GA !\u0011\t)$a\u000f\u000e\u0005\u0005]\"bAA\u001dQ\u0006!A.\u00198h\u0013\u0011\ti$a\u000e\u0003\r=\u0013'.Z2u!\u0011\t)$!\u0011\n\t\u0005\r\u0013q\u0007\u0002\t%Vtg.\u00192mK\u0006q1\r[3dWB|\u0017N\u001c;US6,\u0017!\u00022zi\u0016\u001c\b#\u0002\u0014\u0002L\u0005=\u0013bAA'O\t)\u0011I\u001d:bsB\u0019a%!\u0015\n\u0007\u0005MsE\u0001\u0003CsR,\u0017\u0001G2mK\u0006\u00148\t[3dWB|\u0017N\u001c;ECR\fG*\u0019;feRA\u0011\u0011LA/\u0003?\n\t\u0007E\u0002\u0002\\Mi\u0011\u0001\u0001\u0005\b\u0003\u000b:\u0002\u0019AA\u0010\u0011\u001d\t9e\u0006a\u0001\u0003\u0013Ba!!\u0016\u0018\u0001\u0004A\u0018a\u0001:v]R\tQ0A\u0003xe&$X\rF\u0003~\u0003W\n)\bC\u0004\u0002ne\u0001\r!a\u001c\u0002\u0015\rDWmY6q_&tG\u000fE\u0002V\u0003cJ1!a\u001d\u001d\u0005)\u0019\u0005.Z2la>Lg\u000e\u001e\u0005\u0007\u0003+J\u0002\u0019\u0001=\u0002\tM$x\u000e\u001d"
)
public class CheckpointWriter implements Logging {
   public final JobGenerator org$apache$spark$streaming$CheckpointWriter$$jobGenerator;
   private final SparkConf conf;
   public final String org$apache$spark$streaming$CheckpointWriter$$checkpointDir;
   public final Configuration org$apache$spark$streaming$CheckpointWriter$$hadoopConf;
   private final int MAX_ATTEMPTS;
   private final ThreadPoolExecutor executor;
   private final CompressionCodec compressionCodec;
   private boolean org$apache$spark$streaming$CheckpointWriter$$stopped;
   public volatile FileSystem org$apache$spark$streaming$CheckpointWriter$$fs;
   private volatile Time org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime;
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

   public int MAX_ATTEMPTS() {
      return this.MAX_ATTEMPTS;
   }

   public ThreadPoolExecutor executor() {
      return this.executor;
   }

   public CompressionCodec compressionCodec() {
      return this.compressionCodec;
   }

   public boolean org$apache$spark$streaming$CheckpointWriter$$stopped() {
      return this.org$apache$spark$streaming$CheckpointWriter$$stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.org$apache$spark$streaming$CheckpointWriter$$stopped = x$1;
   }

   public Time org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime() {
      return this.org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime;
   }

   public void org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime_$eq(final Time x$1) {
      this.org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime = x$1;
   }

   public void write(final Checkpoint checkpoint, final boolean clearCheckpointDataLater) {
      try {
         byte[] bytes = Checkpoint$.MODULE$.serialize(checkpoint, this.conf);
         this.executor().execute(new CheckpointWriteHandler(checkpoint.checkpointTime(), bytes, clearCheckpointDataLater));
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Submitted checkpoint of time ", " to writer queue"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_TIME..MODULE$, checkpoint.checkpointTime())})))));
      } catch (RejectedExecutionException var5) {
         this.logError((Function0)(() -> "Could not submit checkpoint task to the thread pool executor"), var5);
      }

   }

   public synchronized void stop() {
      if (!this.org$apache$spark$streaming$CheckpointWriter$$stopped()) {
         long startTimeNs = System.nanoTime();
         org.apache.spark.util.ThreadUtils..MODULE$.shutdown(this.executor(), scala.concurrent.duration.FiniteDuration..MODULE$.apply(10L, TimeUnit.SECONDS));
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"CheckpointWriter executor terminated? "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", waited for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_STATE..MODULE$, BoxesRunTime.boxToBoolean(this.executor().isTerminated()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, BoxesRunTime.boxToLong(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs)))}))))));
         this.stopped_$eq(true);
      }
   }

   public CheckpointWriter(final JobGenerator jobGenerator, final SparkConf conf, final String checkpointDir, final Configuration hadoopConf) {
      this.org$apache$spark$streaming$CheckpointWriter$$jobGenerator = jobGenerator;
      this.conf = conf;
      this.org$apache$spark$streaming$CheckpointWriter$$checkpointDir = checkpointDir;
      this.org$apache$spark$streaming$CheckpointWriter$$hadoopConf = hadoopConf;
      Logging.$init$(this);
      this.MAX_ATTEMPTS = 3;
      this.executor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(1000));
      this.compressionCodec = org.apache.spark.io.CompressionCodec..MODULE$.createCodec(conf);
      this.org$apache$spark$streaming$CheckpointWriter$$stopped = false;
      this.org$apache$spark$streaming$CheckpointWriter$$fs = null;
      this.org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class CheckpointWriteHandler implements Runnable {
      private final Time checkpointTime;
      private final byte[] bytes;
      private final boolean clearCheckpointDataLater;
      // $FF: synthetic field
      public final CheckpointWriter $outer;

      public void run() {
         if (this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime() == null || this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime().$less(this.checkpointTime)) {
            this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime_$eq(this.checkpointTime);
         }

         int attempts = 0;
         long startTimeNs = System.nanoTime();
         Path tempFile = new Path(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$checkpointDir, "temp");
         Path checkpointFile = Checkpoint$.MODULE$.checkpointFile(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$checkpointDir, this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime());

         for(Path backupFile = Checkpoint$.MODULE$.checkpointBackupFile(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$checkpointDir, this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$latestCheckpointTime()); attempts < this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().MAX_ATTEMPTS() && !this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$stopped(); this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs = null) {
            ++attempts;

            try {
               this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logInfo(.MODULE$.from(() -> this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Saving checkpoint for time ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_TIME..MODULE$, this.checkpointTime)}))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to file '", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_FILE..MODULE$, checkpointFile)}))))));
               if (this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs == null) {
                  this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs = (new Path(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$checkpointDir)).getFileSystem(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$hadoopConf);
               }

               this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.delete(tempFile, true);
               FSDataOutputStream fos = this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.create(tempFile);
               org.apache.spark.util.Utils..MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> fos.write(this.bytes), (JFunction0.mcV.sp)() -> fos.close());
               if (this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.exists(checkpointFile)) {
                  this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.delete(backupFile, true);
                  if (!this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.rename(checkpointFile, backupFile)) {
                     this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logWarning(.MODULE$.from(() -> this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not rename ", " to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_FILE..MODULE$, checkpointFile)}))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BACKUP_FILE..MODULE$, backupFile)}))))));
                  }
               }

               if (!this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.rename(tempFile, checkpointFile)) {
                  this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logWarning(.MODULE$.from(() -> this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not rename ", " to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TEMP_FILE..MODULE$, tempFile)}))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_FILE..MODULE$, checkpointFile)}))))));
               }

               Seq allCheckpointFiles = Checkpoint$.MODULE$.getCheckpointFiles(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$checkpointDir, new Some(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs));
               if (allCheckpointFiles.size() > 10) {
                  ((IterableOnceOps)allCheckpointFiles.take(allCheckpointFiles.size() - 10)).foreach((file) -> BoxesRunTime.boxToBoolean($anonfun$run$6(this, file)));
               }

               this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logInfo(.MODULE$.from(() -> this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpoint for time ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_TIME..MODULE$, this.checkpointTime)}))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"saved to file "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"'", "', took "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_FILE..MODULE$, checkpointFile)})))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " bytes and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BYTE_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.bytes.length))})))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, BoxesRunTime.boxToLong(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs)))}))))));
               this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$jobGenerator.onCheckpointCompletion(this.checkpointTime, this.clearCheckpointDataLater);
               return;
            } catch (IOException var11) {
               MessageWithContext msg = this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error in attempt ", " of writing checkpoint "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, BoxesRunTime.boxToInteger(attempts))}))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to '", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_FILE..MODULE$, checkpointFile)}))));
               this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logWarning((LogEntry).MODULE$.from(() -> msg), var11);
            }
         }

         this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logWarning(.MODULE$.from(() -> this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not write checkpoint for time ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_TIME..MODULE$, this.checkpointTime)}))).$plus(this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to file '", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CHECKPOINT_FILE..MODULE$, checkpointFile)}))))));
      }

      // $FF: synthetic method
      public CheckpointWriter org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$run$6(final CheckpointWriteHandler $this, final Path file) {
         $this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().logInfo(.MODULE$.from(() -> $this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, file)})))));
         return $this.org$apache$spark$streaming$CheckpointWriter$CheckpointWriteHandler$$$outer().org$apache$spark$streaming$CheckpointWriter$$fs.delete(file, true);
      }

      public CheckpointWriteHandler(final Time checkpointTime, final byte[] bytes, final boolean clearCheckpointDataLater) {
         this.checkpointTime = checkpointTime;
         this.bytes = bytes;
         this.clearCheckpointDataLater = clearCheckpointDataLater;
         if (CheckpointWriter.this == null) {
            throw null;
         } else {
            this.$outer = CheckpointWriter.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
