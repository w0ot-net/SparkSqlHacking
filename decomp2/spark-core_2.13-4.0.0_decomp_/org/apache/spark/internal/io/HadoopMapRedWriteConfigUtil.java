package org.apache.spark.internal.io;

import java.lang.invoke.SerializedLambda;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobContextImpl;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TaskAttemptContextImpl;
import org.apache.hadoop.mapred.TaskAttemptID;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.spark.SerializableWritable;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.HadoopRDD$;
import org.apache.spark.util.SerializableJobConf;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!\u0002\u000b\u0016\u0001ey\u0002\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u0011\t\u0003!1!Q\u0001\f\rCQ!\u0013\u0001\u0005\u0002)Cqa\u0014\u0001A\u0002\u0013%\u0001\u000bC\u0004k\u0001\u0001\u0007I\u0011B6\t\r\u0001\u0004\u0001\u0015)\u0003R\u0011\u001d)\b\u00011A\u0005\nYDqA\u001f\u0001A\u0002\u0013%1\u0010\u0003\u0004~\u0001\u0001\u0006Ka\u001e\u0005\u0006}\u0002!Ia \u0005\b\u0003\u000f\u0001A\u0011IA\u0005\u0011\u001d\tY\u0003\u0001C!\u0003[Aq!!\u0011\u0001\t\u0003\n\u0019\u0005C\u0004\u0002N\u0001!\t%a\u0014\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z!9\u0011Q\r\u0001\u0005B\u0005\u001d\u0004bBA6\u0001\u0011\u0005\u0013Q\u000e\u0005\b\u0003g\u0002A\u0011BA;\u0011\u001d\t9\b\u0001C!\u0003s\u00121\u0004S1e_>\u0004X*\u00199SK\u0012<&/\u001b;f\u0007>tg-[4Vi&d'B\u0001\f\u0018\u0003\tIwN\u0003\u0002\u00193\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h+\r\u0001s%N\n\u0004\u0001\u0005:\u0004\u0003\u0002\u0012$KQj\u0011!F\u0005\u0003IU\u0011Q\u0003S1e_>\u0004xK]5uK\u000e{gNZ5h+RLG\u000e\u0005\u0002'O1\u0001A!\u0002\u0015\u0001\u0005\u0004Q#!A&\u0004\u0001E\u00111&\r\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\b\u001d>$\b.\u001b8h!\ta#'\u0003\u00024[\t\u0019\u0011I\\=\u0011\u0005\u0019*D!\u0002\u001c\u0001\u0005\u0004Q#!\u0001,\u0011\u0005aJT\"A\f\n\u0005i:\"a\u0002'pO\u001eLgnZ\u0001\u0005G>tg\r\u0005\u0002>\u00016\taH\u0003\u0002@3\u0005!Q\u000f^5m\u0013\t\teHA\nTKJL\u0017\r\\5{C\ndWMS8c\u0007>tg-\u0001\u0006fm&$WM\\2fIM\u00022\u0001R$5\u001b\u0005)%B\u0001$.\u0003\u001d\u0011XM\u001a7fGRL!\u0001S#\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDCA&O)\taU\n\u0005\u0003#\u0001\u0015\"\u0004\"\u0002\"\u0004\u0001\b\u0019\u0005\"B\u001e\u0004\u0001\u0004a\u0014\u0001D8viB,HOR8s[\u0006$X#A)1\u0005Is\u0006cA*[;:\u0011A\u000b\u0017\t\u0003+6j\u0011A\u0016\u0006\u0003/&\na\u0001\u0010:p_Rt\u0014BA-.\u0003\u0019\u0001&/\u001a3fM&\u00111\f\u0018\u0002\u0006\u00072\f7o\u001d\u0006\u000336\u0002\"A\n0\u0005\u0013}3\u0011\u0011!A\u0001\u0006\u0003\t'aA0%c\u0005iq.\u001e;qkR4uN]7bi\u0002\n\"a\u000b2\u0011\t\rDW\u0005N\u0007\u0002I*\u0011QMZ\u0001\u0007[\u0006\u0004(/\u001a3\u000b\u0005\u001d\\\u0012A\u00025bI>|\u0007/\u0003\u0002jI\naq*\u001e;qkR4uN]7bi\u0006\u0001r.\u001e;qkR4uN]7bi~#S-\u001d\u000b\u0003Y>\u0004\"\u0001L7\n\u00059l#\u0001B+oSRDq\u0001]\u0003\u0002\u0002\u0003\u0007\u0011/A\u0002yIE\u0002$A\u001d;\u0011\u0007MS6\u000f\u0005\u0002'i\u0012Iql\\A\u0001\u0002\u0003\u0015\t!Y\u0001\u0007oJLG/\u001a:\u0016\u0003]\u0004Ba\u0019=&i%\u0011\u0011\u0010\u001a\u0002\r%\u0016\u001cwN\u001d3Xe&$XM]\u0001\u000boJLG/\u001a:`I\u0015\fHC\u00017}\u0011\u001d\u0001\b\"!AA\u0002]\fqa\u001e:ji\u0016\u0014\b%A\u0004hKR\u001cuN\u001c4\u0016\u0005\u0005\u0005\u0001cA2\u0002\u0004%\u0019\u0011Q\u00013\u0003\u000f){'mQ8oM\u0006\u00012M]3bi\u0016TuNY\"p]R,\u0007\u0010\u001e\u000b\u0007\u0003\u0017\t9\"!\t\u0011\t\u00055\u00111C\u0007\u0003\u0003\u001fQ1!!\u0005g\u0003%i\u0017\r\u001d:fIV\u001cW-\u0003\u0003\u0002\u0016\u0005=!A\u0003&pE\u000e{g\u000e^3yi\"9\u0011\u0011D\u0006A\u0002\u0005m\u0011\u0001\u00046pER\u0013\u0018mY6fe&#\u0007cA*\u0002\u001e%\u0019\u0011q\u0004/\u0003\rM#(/\u001b8h\u0011\u001d\t\u0019c\u0003a\u0001\u0003K\tQA[8c\u0013\u0012\u00042\u0001LA\u0014\u0013\r\tI#\f\u0002\u0004\u0013:$\u0018\u0001G2sK\u0006$X\rV1tW\u0006#H/Z7qi\u000e{g\u000e^3yiRQ\u0011qFA\u001b\u0003o\tI$!\u0010\u0011\t\u00055\u0011\u0011G\u0005\u0005\u0003g\tyA\u0001\nUCN\\\u0017\t\u001e;f[B$8i\u001c8uKb$\bbBA\r\u0019\u0001\u0007\u00111\u0004\u0005\b\u0003Ga\u0001\u0019AA\u0013\u0011\u001d\tY\u0004\u0004a\u0001\u0003K\tqa\u001d9mSRLE\rC\u0004\u0002@1\u0001\r!!\n\u0002\u001bQ\f7o[!ui\u0016l\u0007\u000f^%e\u0003=\u0019'/Z1uK\u000e{W.\\5ui\u0016\u0014H\u0003BA#\u0003\u0017\u00022AIA$\u0013\r\tI%\u0006\u0002\u001e\u0011\u0006$wn\u001c9NCB\u0014V\rZ;dK\u000e{W.\\5u!J|Go\\2pY\"9\u00111E\u0007A\u0002\u0005\u0015\u0012AC5oSR<&/\u001b;feR)A.!\u0015\u0002V!9\u00111\u000b\bA\u0002\u0005=\u0012a\u0003;bg.\u001cuN\u001c;fqRDq!a\u000f\u000f\u0001\u0004\t)#A\u0003xe&$X\rF\u0002m\u00037Bq!!\u0018\u0010\u0001\u0004\ty&\u0001\u0003qC&\u0014\b#\u0002\u0017\u0002b\u0015\"\u0014bAA2[\t1A+\u001e9mKJ\n1b\u00197pg\u0016<&/\u001b;feR\u0019A.!\u001b\t\u000f\u0005M\u0003\u00031\u0001\u00020\u0005\u0001\u0012N\\5u\u001fV$\b/\u001e;G_Jl\u0017\r\u001e\u000b\u0004Y\u0006=\u0004bBA9#\u0001\u0007\u00111B\u0001\u000bU>\u00147i\u001c8uKb$\u0018aD4fi>+H\u000f];u\r>\u0014X.\u0019;\u0015\u0003\t\f!\"Y:tKJ$8i\u001c8g)\u0015a\u00171PA?\u0011\u001d\t\th\u0005a\u0001\u0003\u0017AaaO\nA\u0002\u0005}\u0004\u0003BAA\u0003\u0007k\u0011!G\u0005\u0004\u0003\u000bK\"!C*qCJ\\7i\u001c8g\u0001"
)
public class HadoopMapRedWriteConfigUtil extends HadoopWriteConfigUtil implements Logging {
   private final SerializableJobConf conf;
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

   private JobConf getConf() {
      return this.conf.value();
   }

   public JobContext createJobContext(final String jobTrackerId, final int jobId) {
      SerializableWritable jobAttemptId = new SerializableWritable(new JobID(jobTrackerId, jobId));
      return new JobContextImpl(this.getConf(), (org.apache.hadoop.mapreduce.JobID)jobAttemptId.value());
   }

   public TaskAttemptContext createTaskAttemptContext(final String jobTrackerId, final int jobId, final int splitId, final int taskAttemptId) {
      HadoopRDD$.MODULE$.addLocalConfiguration(jobTrackerId, jobId, splitId, taskAttemptId, this.conf.value());
      TaskAttemptID attemptId = new TaskAttemptID(jobTrackerId, jobId, TaskType.MAP, splitId, taskAttemptId);
      return new TaskAttemptContextImpl(this.getConf(), attemptId);
   }

   public HadoopMapReduceCommitProtocol createCommitter(final int jobId) {
      HadoopRDD$.MODULE$.addLocalConfiguration("", 0, 0, 0, this.getConf());
      return (HadoopMapReduceCommitProtocol)FileCommitProtocol$.MODULE$.instantiate(HadoopMapRedCommitProtocol.class.getName(), Integer.toString(jobId), this.getConf().get("mapred.output.dir"), FileCommitProtocol$.MODULE$.instantiate$default$4());
   }

   public void initWriter(final TaskAttemptContext taskContext, final int splitId) {
      NumberFormat numfmt = NumberFormat.getInstance(Locale.US);
      numfmt.setMinimumIntegerDigits(5);
      numfmt.setGroupingUsed(false);
      String var10000 = numfmt.format((long)splitId);
      String outputName = "part-" + var10000;
      Path path = FileOutputFormat.getOutputPath(this.getConf());
      FileSystem fs = path != null ? path.getFileSystem(this.getConf()) : FileSystem.get(this.getConf());
      this.writer_$eq(this.getConf().getOutputFormat().getRecordWriter(fs, this.getConf(), outputName, Reporter.NULL));
      .MODULE$.require(this.writer() != null, () -> "Unable to obtain RecordWriter");
   }

   public void write(final Tuple2 pair) {
      .MODULE$.require(this.writer() != null, () -> "Must call createWriter before write.");
      this.writer().write(pair._1(), pair._2());
   }

   public void closeWriter(final TaskAttemptContext taskContext) {
      if (this.writer() != null) {
         this.writer().close(Reporter.NULL);
      }
   }

   public void initOutputFormat(final JobContext jobContext) {
      if (this.outputFormat() == null) {
         this.outputFormat_$eq(this.getConf().getOutputFormat().getClass());
      }
   }

   private OutputFormat getOutputFormat() {
      .MODULE$.require(this.outputFormat() != null, () -> "Must call initOutputFormat first.");
      return (OutputFormat)this.outputFormat().getConstructor().newInstance();
   }

   public void assertConf(final JobContext jobContext, final SparkConf conf) {
      OutputFormat outputFormatInstance = this.getOutputFormat();
      Class keyClass = this.getConf().getOutputKeyClass();
      Class valueClass = this.getConf().getOutputValueClass();
      if (outputFormatInstance == null) {
         throw new SparkException("Output format class not set");
      } else if (keyClass == null) {
         throw new SparkException("Output key class not set");
      } else if (valueClass == null) {
         throw new SparkException("Output value class not set");
      } else {
         SparkHadoopUtil$.MODULE$.get().addCredentials(this.getConf());
         this.logDebug((Function0)(() -> {
            String var10000 = keyClass.getSimpleName();
            return "Saving as hadoop file of type (" + var10000 + ", " + valueClass.getSimpleName() + ")";
         }));
         if (SparkHadoopWriterUtils$.MODULE$.isOutputSpecValidationEnabled(conf)) {
            FileSystem ignoredFs = FileSystem.get(this.getConf());
            this.getOutputFormat().checkOutputSpecs(ignoredFs, this.getConf());
         }
      }
   }

   public HadoopMapRedWriteConfigUtil(final SerializableJobConf conf, final ClassTag evidence$3) {
      super(evidence$3);
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
