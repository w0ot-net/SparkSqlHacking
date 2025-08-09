package org.apache.spark.sql.hive.orc;

import java.lang.reflect.Field;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat;
import org.apache.hadoop.hive.ql.io.orc.Writer;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.catalyst.InternalRow;
import org.apache.spark.sql.execution.datasources.OutputWriter;
import org.apache.spark.sql.execution.datasources.orc.OrcUtils.;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra!\u0002\u0007\u000e\u00015I\u0002\u0002\u0003\u0015\u0001\u0005\u000b\u0007I\u0011\u0001\u0016\t\u0011a\u0002!\u0011!Q\u0001\n-B\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006IA\u000f\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005\u0003\")\u0011\n\u0001C\u0001\u0015\"1\u0001\u000b\u0001Q\u0001\nECq\u0001\u0016\u0001C\u0002\u0013%Q\u000b\u0003\u0004f\u0001\u0001\u0006IA\u0016\u0005\u0006M\u0002!\te\u001a\u0005\u0006i\u0002!\t%\u001e\u0005\u0006m\u0002!Ia\u001e\u0002\u0010\u001fJ\u001cw*\u001e;qkR<&/\u001b;fe*\u0011abD\u0001\u0004_J\u001c'B\u0001\t\u0012\u0003\u0011A\u0017N^3\u000b\u0005I\u0019\u0012aA:rY*\u0011A#F\u0001\u0006gB\f'o\u001b\u0006\u0003-]\ta!\u00199bG\",'\"\u0001\r\u0002\u0007=\u0014xmE\u0002\u00015\t\u0002\"a\u0007\u0011\u000e\u0003qQ!!\b\u0010\u0002\u0017\u0011\fG/Y:pkJ\u001cWm\u001d\u0006\u0003?E\t\u0011\"\u001a=fGV$\u0018n\u001c8\n\u0005\u0005b\"\u0001D(viB,Ho\u0016:ji\u0016\u0014\bCA\u0012'\u001b\u0005!#BA\u0013\u0014\u0003!Ig\u000e^3s]\u0006d\u0017BA\u0014%\u0005\u001daunZ4j]\u001e\fA\u0001]1uQ\u000e\u0001Q#A\u0016\u0011\u00051*dBA\u00174!\tq\u0013'D\u00010\u0015\t\u0001\u0014&\u0001\u0004=e>|GO\u0010\u0006\u0002e\u0005)1oY1mC&\u0011A'M\u0001\u0007!J,G-\u001a4\n\u0005Y:$AB*ue&twM\u0003\u00025c\u0005)\u0001/\u0019;iA\u0005QA-\u0019;b'\u000eDW-\\1\u0011\u0005mrT\"\u0001\u001f\u000b\u0005u\n\u0012!\u0002;za\u0016\u001c\u0018BA =\u0005)\u0019FO];diRK\b/Z\u0001\bG>tG/\u001a=u!\t\u0011u)D\u0001D\u0015\t!U)A\u0005nCB\u0014X\rZ;dK*\u0011a)F\u0001\u0007Q\u0006$wn\u001c9\n\u0005!\u001b%A\u0005+bg.\fE\u000f^3naR\u001cuN\u001c;fqR\fa\u0001P5oSRtD\u0003B&N\u001d>\u0003\"\u0001\u0014\u0001\u000e\u00035AQ\u0001K\u0003A\u0002-BQ!O\u0003A\u0002iBQ\u0001Q\u0003A\u0002\u0005\u000b!b]3sS\u0006d\u0017N_3s!\ta%+\u0003\u0002T\u001b\tiqJ]2TKJL\u0017\r\\5{KJ\fAB]3d_J$wK]5uKJ,\u0012A\u0016\t\u0005/jc&-D\u0001Y\u0015\tIV)\u0001\u0004nCB\u0014X\rZ\u0005\u00037b\u0013ABU3d_J$wK]5uKJ\u0004\"!\u00181\u000e\u0003yS!aX#\u0002\u0005%|\u0017BA1_\u00051qU\u000f\u001c7Xe&$\u0018M\u00197f!\ti6-\u0003\u0002e=\nAqK]5uC\ndW-A\u0007sK\u000e|'\u000fZ,sSR,'\u000fI\u0001\u0006oJLG/\u001a\u000b\u0003Q2\u0004\"!\u001b6\u000e\u0003EJ!a[\u0019\u0003\tUs\u0017\u000e\u001e\u0005\u0006[&\u0001\rA\\\u0001\u0004e><\bCA8s\u001b\u0005\u0001(BA9\u0012\u0003!\u0019\u0017\r^1msN$\u0018BA:q\u0005-Ie\u000e^3s]\u0006d'k\\<\u0002\u000b\rdwn]3\u0015\u0003!\f\u0011dZ3u\u001fJ\u001c%/Z1uK&sG/\u001a:oC2<&/\u001b;feR\t\u0001\u0010\u0005\u0002z\u007f6\t!P\u0003\u0002\u000fw*\u0011q\f \u0006\u0003{z\f!!\u001d7\u000b\u0005A)\u0015bAA\u0001u\n1qK]5uKJ\u0004"
)
public class OrcOutputWriter extends OutputWriter implements Logging {
   private final String path;
   private final TaskAttemptContext context;
   private final OrcSerializer serializer;
   private final RecordWriter recordWriter;
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

   public String path() {
      return this.path;
   }

   private RecordWriter recordWriter() {
      return this.recordWriter;
   }

   public void write(final InternalRow row) {
      this.recordWriter().write(NullWritable.get(), this.serializer.serialize(row));
   }

   public void close() {
      try {
         .MODULE$.addSparkVersionMetadata(this.getOrCreateInternalWriter());
      } catch (Throwable var5) {
         if (var5 == null || !scala.util.control.NonFatal..MODULE$.apply(var5)) {
            throw var5;
         }

         this.log().warn(var5.toString(), var5);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.recordWriter().close(Reporter.NULL);
   }

   private Writer getOrCreateInternalWriter() {
      Field writerField = this.recordWriter().getClass().getDeclaredField("writer");
      writerField.setAccessible(true);
      Writer writer = (Writer)writerField.get(this.recordWriter());
      if (writer == null) {
         OrcFile.WriterOptions options = OrcFile.writerOptions(this.context.getConfiguration());
         options.inspector(this.serializer.structOI());
         writer = OrcFile.createWriter(new Path(this.path()), options);
         writerField.set(this.recordWriter(), writer);
      }

      return writer;
   }

   public OrcOutputWriter(final String path, final StructType dataSchema, final TaskAttemptContext context) {
      this.path = path;
      this.context = context;
      Logging.$init$(this);
      this.serializer = new OrcSerializer(dataSchema, context.getConfiguration());
      this.recordWriter = (new OrcOutputFormat()).getRecordWriter((new Path(path)).getFileSystem(context.getConfiguration()), (JobConf)context.getConfiguration(), path, Reporter.NULL);
   }
}
