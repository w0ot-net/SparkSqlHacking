package org.apache.spark.internal.io;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.SerializableConfiguration;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e!\u0002\u000b\u0016\u0001ey\u0002\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u0011\t\u0003!1!Q\u0001\f\rCQ!\u0013\u0001\u0005\u0002)Cqa\u0014\u0001A\u0002\u0013%\u0001\u000bC\u0004k\u0001\u0001\u0007I\u0011B6\t\r\u0001\u0004\u0001\u0015)\u0003R\u0011\u001d)\b\u00011A\u0005\nYDqA\u001f\u0001A\u0002\u0013%1\u0010\u0003\u0004~\u0001\u0001\u0006Ka\u001e\u0005\u0006}\u0002!Ia \u0005\b\u0003\u0017\u0001A\u0011IA\u0007\u0011\u001d\tI\u0003\u0001C!\u0003WAq!a\u0010\u0001\t\u0003\n\t\u0005C\u0004\u0002L\u0001!\t%!\u0014\t\u000f\u0005U\u0003\u0001\"\u0011\u0002X!9\u00111\r\u0001\u0005B\u0005\u0015\u0004bBA5\u0001\u0011\u0005\u00131\u000e\u0005\b\u0003c\u0002A\u0011BA:\u0011\u001d\t)\b\u0001C!\u0003o\u0012a\u0004S1e_>\u0004X*\u00199SK\u0012,8-Z,sSR,7i\u001c8gS\u001e,F/\u001b7\u000b\u0005Y9\u0012AA5p\u0015\tA\u0012$\u0001\u0005j]R,'O\\1m\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<Wc\u0001\u0011(kM\u0019\u0001!I\u001c\u0011\t\t\u001aS\u0005N\u0007\u0002+%\u0011A%\u0006\u0002\u0016\u0011\u0006$wn\u001c9Xe&$XmQ8oM&<W\u000b^5m!\t1s\u0005\u0004\u0001\u0005\u000b!\u0002!\u0019\u0001\u0016\u0003\u0003-\u001b\u0001!\u0005\u0002,cA\u0011AfL\u0007\u0002[)\ta&A\u0003tG\u0006d\u0017-\u0003\u00021[\t9aj\u001c;iS:<\u0007C\u0001\u00173\u0013\t\u0019TFA\u0002B]f\u0004\"AJ\u001b\u0005\u000bY\u0002!\u0019\u0001\u0016\u0003\u0003Y\u0003\"\u0001O\u001d\u000e\u0003]I!AO\f\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g!\ti\u0004)D\u0001?\u0015\ty\u0014$\u0001\u0003vi&d\u0017BA!?\u0005e\u0019VM]5bY&T\u0018M\u00197f\u0007>tg-[4ve\u0006$\u0018n\u001c8\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007E\u0002E\u000fRj\u0011!\u0012\u0006\u0003\r6\nqA]3gY\u0016\u001cG/\u0003\u0002I\u000b\nA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0003\u0017:#\"\u0001T'\u0011\t\t\u0002Q\u0005\u000e\u0005\u0006\u0005\u000e\u0001\u001da\u0011\u0005\u0006w\r\u0001\r\u0001P\u0001\r_V$\b/\u001e;G_Jl\u0017\r^\u000b\u0002#B\u0012!K\u0018\t\u0004'jkfB\u0001+Y!\t)V&D\u0001W\u0015\t9\u0016&\u0001\u0004=e>|GOP\u0005\u000336\na\u0001\u0015:fI\u00164\u0017BA.]\u0005\u0015\u0019E.Y:t\u0015\tIV\u0006\u0005\u0002'=\u0012IqLBA\u0001\u0002\u0003\u0015\t!\u0019\u0002\u0004?\u0012\u001a\u0014!D8viB,HOR8s[\u0006$\b%\u0005\u0002,EB!1\r[\u00135\u001b\u0005!'BA3g\u0003%i\u0017\r\u001d:fIV\u001cWM\u0003\u0002h7\u00051\u0001.\u00193p_BL!!\u001b3\u0003\u0019=+H\u000f];u\r>\u0014X.\u0019;\u0002!=,H\u000f];u\r>\u0014X.\u0019;`I\u0015\fHC\u00017p!\taS.\u0003\u0002o[\t!QK\\5u\u0011\u001d\u0001X!!AA\u0002E\f1\u0001\u001f\u00132a\t\u0011H\u000fE\u0002T5N\u0004\"A\n;\u0005\u0013}{\u0017\u0011!A\u0001\u0006\u0003\t\u0017AB<sSR,'/F\u0001x!\u0011\u0019\u00070\n\u001b\n\u0005e$'\u0001\u0004*fG>\u0014Hm\u0016:ji\u0016\u0014\u0018AC<sSR,'o\u0018\u0013fcR\u0011A\u000e \u0005\ba\"\t\t\u00111\u0001x\u0003\u001d9(/\u001b;fe\u0002\nqaZ3u\u0007>tg-\u0006\u0002\u0002\u0002A!\u00111AA\u0004\u001b\t\t)A\u0003\u0002<M&!\u0011\u0011BA\u0003\u00055\u0019uN\u001c4jOV\u0014\u0018\r^5p]\u0006\u00012M]3bi\u0016TuNY\"p]R,\u0007\u0010\u001e\u000b\u0007\u0003\u001f\t)\"a\b\u0011\u0007\r\f\t\"C\u0002\u0002\u0014\u0011\u0014!BS8c\u0007>tG/\u001a=u\u0011\u001d\t9b\u0003a\u0001\u00033\tAB[8c)J\f7m[3s\u0013\u0012\u00042aUA\u000e\u0013\r\ti\u0002\u0018\u0002\u0007'R\u0014\u0018N\\4\t\u000f\u0005\u00052\u00021\u0001\u0002$\u0005)!n\u001c2JIB\u0019A&!\n\n\u0007\u0005\u001dRFA\u0002J]R\f\u0001d\u0019:fCR,G+Y:l\u0003R$X-\u001c9u\u0007>tG/\u001a=u))\ti#a\r\u00026\u0005]\u00121\b\t\u0004G\u0006=\u0012bAA\u0019I\n\u0011B+Y:l\u0003R$X-\u001c9u\u0007>tG/\u001a=u\u0011\u001d\t9\u0002\u0004a\u0001\u00033Aq!!\t\r\u0001\u0004\t\u0019\u0003C\u0004\u0002:1\u0001\r!a\t\u0002\u000fM\u0004H.\u001b;JI\"9\u0011Q\b\u0007A\u0002\u0005\r\u0012!\u0004;bg.\fE\u000f^3naRLE-A\bde\u0016\fG/Z\"p[6LG\u000f^3s)\u0011\t\u0019%!\u0013\u0011\u0007\t\n)%C\u0002\u0002HU\u0011Q\u0004S1e_>\u0004X*\u00199SK\u0012,8-Z\"p[6LG\u000f\u0015:pi>\u001cw\u000e\u001c\u0005\b\u0003Ci\u0001\u0019AA\u0012\u0003)Ig.\u001b;Xe&$XM\u001d\u000b\u0006Y\u0006=\u00131\u000b\u0005\b\u0003#r\u0001\u0019AA\u0017\u0003-!\u0018m]6D_:$X\r\u001f;\t\u000f\u0005eb\u00021\u0001\u0002$\u0005)qO]5uKR\u0019A.!\u0017\t\u000f\u0005ms\u00021\u0001\u0002^\u0005!\u0001/Y5s!\u0015a\u0013qL\u00135\u0013\r\t\t'\f\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u0017\rdwn]3Xe&$XM\u001d\u000b\u0004Y\u0006\u001d\u0004bBA)!\u0001\u0007\u0011QF\u0001\u0011S:LGoT;uaV$hi\u001c:nCR$2\u0001\\A7\u0011\u001d\ty'\u0005a\u0001\u0003\u001f\t!B[8c\u0007>tG/\u001a=u\u0003=9W\r^(viB,HOR8s[\u0006$H#\u00012\u0002\u0015\u0005\u001c8/\u001a:u\u0007>tg\rF\u0003m\u0003s\nY\bC\u0004\u0002pM\u0001\r!a\u0004\t\rm\u001a\u0002\u0019AA?!\u0011\ty(!!\u000e\u0003eI1!a!\u001a\u0005%\u0019\u0006/\u0019:l\u0007>tg\r"
)
public class HadoopMapReduceWriteConfigUtil extends HadoopWriteConfigUtil implements Logging {
   private final SerializableConfiguration conf;
   private Class outputFormat;
   private RecordWriter writer;
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

   private Class outputFormat() {
      return this.outputFormat;
   }

   private void outputFormat_$eq(final Class x$1) {
      this.outputFormat = x$1;
   }

   private RecordWriter writer() {
      return this.writer;
   }

   private void writer_$eq(final RecordWriter x$1) {
      this.writer = x$1;
   }

   private Configuration getConf() {
      return this.conf.value();
   }

   public JobContext createJobContext(final String jobTrackerId, final int jobId) {
      TaskAttemptID jobAttemptId = new TaskAttemptID(jobTrackerId, jobId, TaskType.MAP, 0, 0);
      return new TaskAttemptContextImpl(this.getConf(), jobAttemptId);
   }

   public TaskAttemptContext createTaskAttemptContext(final String jobTrackerId, final int jobId, final int splitId, final int taskAttemptId) {
      TaskAttemptID attemptId = new TaskAttemptID(jobTrackerId, jobId, TaskType.REDUCE, splitId, taskAttemptId);
      return new TaskAttemptContextImpl(this.getConf(), attemptId);
   }

   public HadoopMapReduceCommitProtocol createCommitter(final int jobId) {
      return (HadoopMapReduceCommitProtocol)FileCommitProtocol$.MODULE$.instantiate(HadoopMapReduceCommitProtocol.class.getName(), Integer.toString(jobId), this.getConf().get("mapreduce.output.fileoutputformat.outputdir"), FileCommitProtocol$.MODULE$.instantiate$default$4());
   }

   public void initWriter(final TaskAttemptContext taskContext, final int splitId) {
      OutputFormat taskFormat = this.getOutputFormat();
      if (taskFormat instanceof Configurable) {
         ((Configurable)taskFormat).setConf(this.getConf());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var7 = BoxedUnit.UNIT;
      }

      this.writer_$eq(taskFormat.getRecordWriter(taskContext));
      .MODULE$.require(this.writer() != null, () -> "Unable to obtain RecordWriter");
   }

   public void write(final Tuple2 pair) {
      .MODULE$.require(this.writer() != null, () -> "Must call createWriter before write.");
      this.writer().write(pair._1(), pair._2());
   }

   public void closeWriter(final TaskAttemptContext taskContext) {
      if (this.writer() != null) {
         this.writer().close(taskContext);
         this.writer_$eq((RecordWriter)null);
      } else {
         this.logWarning((Function0)(() -> "Writer has been closed."));
      }
   }

   public void initOutputFormat(final JobContext jobContext) {
      if (this.outputFormat() == null) {
         this.outputFormat_$eq(jobContext.getOutputFormatClass());
      }
   }

   private OutputFormat getOutputFormat() {
      .MODULE$.require(this.outputFormat() != null, () -> "Must call initOutputFormat first.");
      return (OutputFormat)this.outputFormat().getConstructor().newInstance();
   }

   public void assertConf(final JobContext jobContext, final SparkConf conf) {
      if (SparkHadoopWriterUtils$.MODULE$.isOutputSpecValidationEnabled(conf)) {
         this.getOutputFormat().checkOutputSpecs(jobContext);
      }
   }

   public HadoopMapReduceWriteConfigUtil(final SerializableConfiguration conf, final ClassTag evidence$4) {
      super(evidence$4);
      this.conf = conf;
      Logging.$init$(this);
      this.outputFormat = null;
      this.writer = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
