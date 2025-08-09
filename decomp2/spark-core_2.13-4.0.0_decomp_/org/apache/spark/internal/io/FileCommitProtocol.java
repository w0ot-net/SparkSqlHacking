package org.apache.spark.internal.io;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dh!\u0002\u000f\u001e\u0003\u0003A\u0003\"B\u001a\u0001\t\u0003!\u0004\"B\u001c\u0001\r\u0003A\u0004\"\u0002$\u0001\r\u00039\u0005bBA(\u0001\u0019\u0005\u0011\u0011\u000b\u0005\b\u0003+\u0002a\u0011AA,\u0011\u001d\t\u0019\u0007\u0001D\u0001\u0003KBq!a\u0019\u0001\t\u0003\tY\tC\u0004\u0002\u001c\u00021\t!!(\t\u000f\u0005m\u0005\u0001\"\u0001\u0002.\"9\u0011Q\u0017\u0001\u0007\u0002\u0005]\u0006bBA^\u0001\u0019\u0005\u0011Q\u0018\u0005\b\u0003\u0003\u0004A\u0011AAb\u0011\u001d\t\u0019\u000e\u0001C\u0001\u0003+<Q!W\u000f\t\u0002i3Q\u0001H\u000f\t\u0002mCQaM\b\u0005\u0002q3A!X\b\u0001=\"A!-\u0005BC\u0002\u0013\u00051\r\u0003\u0005h#\t\u0005\t\u0015!\u0003e\u0011\u0015\u0019\u0014\u0003\"\u0001i\u000f\u0015aw\u0002#\u0001n\r\u0015qw\u0002#\u0001p\u0011\u0015\u0019d\u0003\"\u0001q\u0011\u001d\th#!A\u0005\nIDQa_\b\u0005\u0002qD\u0011\"!\t\u0010#\u0003%\t!a\t\t\u000f\u0005er\u0002\"\u0001\u0002<\t\u0011b)\u001b7f\u0007>lW.\u001b;Qe>$xnY8m\u0015\tqr$\u0001\u0002j_*\u0011\u0001%I\u0001\tS:$XM\u001d8bY*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001Is\u0006\u0005\u0002+[5\t1FC\u0001-\u0003\u0015\u00198-\u00197b\u0013\tq3F\u0001\u0004B]f\u0014VM\u001a\t\u0003aEj\u0011aH\u0005\u0003e}\u0011q\u0001T8hO&tw-\u0001\u0004=S:LGO\u0010\u000b\u0002kA\u0011a\u0007A\u0007\u0002;\u0005A1/\u001a;va*{'\r\u0006\u0002:yA\u0011!FO\u0005\u0003w-\u0012A!\u00168ji\")QH\u0001a\u0001}\u0005Q!n\u001c2D_:$X\r\u001f;\u0011\u0005}\"U\"\u0001!\u000b\u0005\u0005\u0013\u0015!C7baJ,G-^2f\u0015\t\u00195%\u0001\u0004iC\u0012|w\u000e]\u0005\u0003\u000b\u0002\u0013!BS8c\u0007>tG/\u001a=u\u0003%\u0019w.\\7ji*{'\rF\u0002:\u0011&CQ!P\u0002A\u0002yBQAS\u0002A\u0002-\u000b1\u0002^1tW\u000e{W.\\5ugB\u0019A\nV,\u000f\u00055\u0013fB\u0001(R\u001b\u0005y%B\u0001)(\u0003\u0019a$o\\8u}%\tA&\u0003\u0002TW\u00059\u0001/Y2lC\u001e,\u0017BA+W\u0005\r\u0019V-\u001d\u0006\u0003'.\u0002\"\u0001W\t\u000f\u0005Yr\u0011A\u0005$jY\u0016\u001cu.\\7jiB\u0013x\u000e^8d_2\u0004\"AN\b\u0014\u0007=Is\u0006F\u0001[\u0005E!\u0016m]6D_6l\u0017\u000e^'fgN\fw-Z\n\u0004#%z\u0006C\u0001'a\u0013\t\tgK\u0001\u0007TKJL\u0017\r\\5{C\ndW-A\u0002pE*,\u0012\u0001\u001a\t\u0003U\u0015L!AZ\u0016\u0003\u0007\u0005s\u00170\u0001\u0003pE*\u0004CCA5l!\tQ\u0017#D\u0001\u0010\u0011\u0015\u0011G\u00031\u0001e\u0003Y)U\u000e\u001d;z)\u0006\u001c8nQ8n[&$X*Z:tC\u001e,\u0007C\u00016\u0017\u0005Y)U\u000e\u001d;z)\u0006\u001c8nQ8n[&$X*Z:tC\u001e,7C\u0001\fj)\u0005i\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#A:\u0011\u0005QLX\"A;\u000b\u0005Y<\u0018\u0001\u00027b]\u001eT\u0011\u0001_\u0001\u0005U\u00064\u0018-\u0003\u0002{k\n1qJ\u00196fGR\f1\"\u001b8ti\u0006tG/[1uKRAQ'`A\b\u0003'\t9\u0002C\u0003\u007f3\u0001\u0007q0A\u0005dY\u0006\u001c8OT1nKB!\u0011\u0011AA\u0005\u001d\u0011\t\u0019!!\u0002\u0011\u00059[\u0013bAA\u0004W\u00051\u0001K]3eK\u001aLA!a\u0003\u0002\u000e\t11\u000b\u001e:j]\u001eT1!a\u0002,\u0011\u0019\t\t\"\u0007a\u0001\u007f\u0006)!n\u001c2JI\"1\u0011QC\rA\u0002}\f!b\\;uaV$\b+\u0019;i\u0011%\tI\"\u0007I\u0001\u0002\u0004\tY\"A\res:\fW.[2QCJ$\u0018\u000e^5p]>3XM]<sSR,\u0007c\u0001\u0016\u0002\u001e%\u0019\u0011qD\u0016\u0003\u000f\t{w\u000e\\3b]\u0006)\u0012N\\:uC:$\u0018.\u0019;fI\u0011,g-Y;mi\u0012\"TCAA\u0013U\u0011\tY\"a\n,\u0005\u0005%\u0002\u0003BA\u0016\u0003ki!!!\f\u000b\t\u0005=\u0012\u0011G\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\r,\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003o\tiCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQbZ3u'R\fw-\u001b8h\t&\u0014HCBA\u001f\u0003\u0013\ni\u0005\u0005\u0003\u0002@\u0005\u0015SBAA!\u0015\r\t\u0019EQ\u0001\u0003MNLA!a\u0012\u0002B\t!\u0001+\u0019;i\u0011\u0019\tYe\u0007a\u0001\u007f\u0006!\u0001/\u0019;i\u0011\u0019\t\tb\u0007a\u0001\u007f\u0006A\u0011MY8si*{'\rF\u0002:\u0003'BQ!\u0010\u0003A\u0002y\n\u0011b]3ukB$\u0016m]6\u0015\u0007e\nI\u0006C\u0004\u0002\\\u0015\u0001\r!!\u0018\u0002\u0017Q\f7o[\"p]R,\u0007\u0010\u001e\t\u0004\u007f\u0005}\u0013bAA1\u0001\n\u0011B+Y:l\u0003R$X-\u001c9u\u0007>tG/\u001a=u\u0003=qWm\u001e+bg.$V-\u001c9GS2,GcB@\u0002h\u0005%\u00141\u000f\u0005\b\u000372\u0001\u0019AA/\u0011\u001d\tYG\u0002a\u0001\u0003[\n1\u0001Z5s!\u0011Q\u0013qN@\n\u0007\u0005E4F\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003k2\u0001\u0019A@\u0002\u0007\u0015DH\u000fK\u0006\u0007\u0003s\ny(!!\u0002\u0006\u0006\u001d\u0005c\u0001\u0016\u0002|%\u0019\u0011QP\u0016\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005\r\u0015\u0001N;tK\u0002rWm\u001e+bg.$V-\u001c9GS2,\u0007F\f\u0018/Y\u0001\u001a\b/Z2;A\u0019KG.\u001a(b[\u0016\u001c\u0006/Z2*A%t7\u000f^3bI\u0006)1/\u001b8dK\u0006\u0012\u0011\u0011R\u0001\u0006g9\u001ad\u0006\r\u000b\b\u007f\u00065\u0015qRAI\u0011\u001d\tYf\u0002a\u0001\u0003;Bq!a\u001b\b\u0001\u0004\ti\u0007C\u0004\u0002\u0014\u001e\u0001\r!!&\u0002\tM\u0004Xm\u0019\t\u0004m\u0005]\u0015bAAM;\taa)\u001b7f\u001d\u0006lWm\u00159fG\u00061b.Z<UCN\\G+Z7q\r&dW-\u00112t!\u0006$\b\u000eF\u0004\u0000\u0003?\u000b\t+!*\t\u000f\u0005m\u0003\u00021\u0001\u0002^!1\u00111\u0015\u0005A\u0002}\f1\"\u00192t_2,H/\u001a#je\"1\u0011Q\u000f\u0005A\u0002}D3\u0002CA=\u0003\u007f\nI+!\"\u0002\b\u0006\u0012\u00111V\u0001<kN,\u0007E\\3x)\u0006\u001c8\u000eV3na\u001aKG.Z!cgB\u000bG\u000f\u001b\u0015/]9b\u0003e\u001d9fGj\u0002c)\u001b7f\u001d\u0006lWm\u00159fG&\u0002\u0013N\\:uK\u0006$GcB@\u00020\u0006E\u00161\u0017\u0005\b\u00037J\u0001\u0019AA/\u0011\u0019\t\u0019+\u0003a\u0001\u007f\"9\u00111S\u0005A\u0002\u0005U\u0015AC2p[6LG\u000fV1tWR\u0019q+!/\t\u000f\u0005m#\u00021\u0001\u0002^\u0005I\u0011MY8siR\u000b7o\u001b\u000b\u0004s\u0005}\u0006bBA.\u0017\u0001\u0007\u0011QL\u0001\u000eI\u0016dW\r^3XSRD'j\u001c2\u0015\u0011\u0005m\u0011QYAg\u0003\u001fDq!a\u0011\r\u0001\u0004\t9\r\u0005\u0003\u0002@\u0005%\u0017\u0002BAf\u0003\u0003\u0012!BR5mKNK8\u000f^3n\u0011\u001d\tY\u0005\u0004a\u0001\u0003{Aq!!5\r\u0001\u0004\tY\"A\u0005sK\u000e,(o]5wK\u0006aqN\u001c+bg.\u001cu.\\7jiR\u0019\u0011(a6\t\r\u0005eW\u00021\u0001X\u0003)!\u0018m]6D_6l\u0017\u000e\u001e\u0015\u0004\u0001\u0005u\u0007\u0003BAp\u0003Gl!!!9\u000b\u0007\u0005M\u0012%\u0003\u0003\u0002f\u0006\u0005(\u0001C+ogR\f'\r\\3"
)
public abstract class FileCommitProtocol implements Logging {
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Path getStagingDir(final String path, final String jobId) {
      return FileCommitProtocol$.MODULE$.getStagingDir(path, jobId);
   }

   public static boolean instantiate$default$4() {
      return FileCommitProtocol$.MODULE$.instantiate$default$4();
   }

   public static FileCommitProtocol instantiate(final String className, final String jobId, final String outputPath, final boolean dynamicPartitionOverwrite) {
      return FileCommitProtocol$.MODULE$.instantiate(className, jobId, outputPath, dynamicPartitionOverwrite);
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

   public abstract void setupJob(final JobContext jobContext);

   public abstract void commitJob(final JobContext jobContext, final Seq taskCommits);

   public abstract void abortJob(final JobContext jobContext);

   public abstract void setupTask(final TaskAttemptContext taskContext);

   /** @deprecated */
   public abstract String newTaskTempFile(final TaskAttemptContext taskContext, final Option dir, final String ext);

   public String newTaskTempFile(final TaskAttemptContext taskContext, final Option dir, final FileNameSpec spec) {
      if (spec.prefix().isEmpty()) {
         return this.newTaskTempFile(taskContext, dir, spec.suffix());
      } else {
         String var10002 = this.getClass().getSimpleName();
         throw new UnsupportedOperationException(var10002 + ".newTaskTempFile does not support file name prefix: " + spec.prefix());
      }
   }

   /** @deprecated */
   public abstract String newTaskTempFileAbsPath(final TaskAttemptContext taskContext, final String absoluteDir, final String ext);

   public String newTaskTempFileAbsPath(final TaskAttemptContext taskContext, final String absoluteDir, final FileNameSpec spec) {
      if (spec.prefix().isEmpty()) {
         return this.newTaskTempFileAbsPath(taskContext, absoluteDir, spec.suffix());
      } else {
         String var10002 = this.getClass().getSimpleName();
         throw new UnsupportedOperationException(var10002 + ".newTaskTempFileAbsPath does not support file name prefix: " + spec.prefix());
      }
   }

   public abstract TaskCommitMessage commitTask(final TaskAttemptContext taskContext);

   public abstract void abortTask(final TaskAttemptContext taskContext);

   public boolean deleteWithJob(final FileSystem fs, final Path path, final boolean recursive) {
      return fs.delete(path, recursive);
   }

   public void onTaskCommit(final TaskCommitMessage taskCommit) {
      this.logDebug((Function0)(() -> "onTaskCommit(" + taskCommit + ")"));
   }

   public FileCommitProtocol() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class TaskCommitMessage implements Serializable {
      private final Object obj;

      public Object obj() {
         return this.obj;
      }

      public TaskCommitMessage(final Object obj) {
         this.obj = obj;
      }
   }

   public static class EmptyTaskCommitMessage$ extends TaskCommitMessage {
      public static final EmptyTaskCommitMessage$ MODULE$ = new EmptyTaskCommitMessage$();

      private Object writeReplace() {
         return new ModuleSerializationProxy(EmptyTaskCommitMessage$.class);
      }

      public EmptyTaskCommitMessage$() {
         super((Object)null);
      }
   }
}
