package org.apache.spark.internal.io;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.UUID;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskID;
import org.apache.hadoop.mapreduce.TaskType;
import org.apache.hadoop.mapreduce.JobStatus.State;
import org.apache.hadoop.mapreduce.lib.output.FileOutputCommitter;
import org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl;
import org.apache.spark.internal.MDC;
import org.apache.spark.mapred.SparkHadoopMapRedUtil$;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005a\u0001\u0002\u0012$\u00019B\u0001\"\u0012\u0001\u0003\u0002\u0003\u0006IA\u0012\u0005\t\u001d\u0002\u0011\t\u0011)A\u0005\r\"Aq\n\u0001B\u0001B\u0003%\u0001\u000bC\u0003U\u0001\u0011\u0005Q\u000bC\u0005[\u0001\u0001\u0007\t\u0019!C\u00057\"IA\r\u0001a\u0001\u0002\u0004%I!\u001a\u0005\nW\u0002\u0001\r\u0011!Q!\nqCq\u0001\u001d\u0001C\u0002\u0013%\u0011\u000f\u0003\u0004s\u0001\u0001\u0006I\u0001\u0015\u0005\bg\u0002\u0001\r\u0011\"\u0003u\u0011\u001di\b\u00011A\u0005\nyDq!!\u0001\u0001A\u0003&Q\u000fC\u0005\u0002\u0006\u0001\u0001\r\u0011\"\u0003\u0002\b!I\u0011q\u0002\u0001A\u0002\u0013%\u0011\u0011\u0003\u0005\t\u0003+\u0001\u0001\u0015)\u0003\u0002\n!Q\u0011\u0011\u0004\u0001\t\u0006\u0004%\t\"a\u0007\t\u000f\u0005-\u0002\u0001\"\u0005\u0002.!9\u0011\u0011\b\u0001\u0005B\u0005m\u0002bBA\u001d\u0001\u0011\u0005\u0013q\n\u0005\b\u0003?\u0002A\u0011IA1\u0011\u001d\ty\u0006\u0001C!\u0003WBq!a\u001d\u0001\t#\t)\bC\u0004\u0002|\u0001!\t%! \t\u000f\u0005%\u0005\u0001\"\u0011\u0002\f\"9\u0011q\u0015\u0001\u0005B\u0005%\u0006bBAW\u0001\u0011\u0005\u0013q\u0016\u0005\b\u0003g\u0003A\u0011IA[\u0011\u001d\tI\f\u0001C!\u0003w;\u0011\"a0$\u0003\u0003E\t!!1\u0007\u0011\t\u001a\u0013\u0011!E\u0001\u0003\u0007Da\u0001\u0016\u0010\u0005\u0002\u0005]\u0007\"CAm=E\u0005I\u0011AAn\u0011%\t\tPHA\u0001\n\u0013\t\u0019PA\u000fIC\u0012|w\u000e]'baJ+G-^2f\u0007>lW.\u001b;Qe>$xnY8m\u0015\t!S%\u0001\u0002j_*\u0011aeJ\u0001\tS:$XM\u001d8bY*\u0011\u0001&K\u0001\u0006gB\f'o\u001b\u0006\u0003U-\na!\u00199bG\",'\"\u0001\u0017\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001y3'\u0011\t\u0003aEj\u0011aI\u0005\u0003e\r\u0012!CR5mK\u000e{W.\\5u!J|Go\\2pYB\u0011AG\u0010\b\u0003kmr!AN\u001d\u000e\u0003]R!\u0001O\u0017\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0014!B:dC2\f\u0017B\u0001\u001f>\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AO\u0005\u0003\u007f\u0001\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001P\u001f\u0011\u0005\t\u001bU\"A\u0013\n\u0005\u0011+#a\u0002'pO\u001eLgnZ\u0001\u0006U>\u0014\u0017\n\u001a\t\u0003\u000f.s!\u0001S%\u0011\u0005Yj\u0014B\u0001&>\u0003\u0019\u0001&/\u001a3fM&\u0011A*\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005)k\u0014\u0001\u00029bi\"\f\u0011\u0004Z=oC6L7\rU1si&$\u0018n\u001c8Pm\u0016\u0014xO]5uKB\u0011\u0011KU\u0007\u0002{%\u00111+\u0010\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q!ak\u0016-Z!\t\u0001\u0004\u0001C\u0003F\t\u0001\u0007a\tC\u0003O\t\u0001\u0007a\tC\u0004P\tA\u0005\t\u0019\u0001)\u0002\u0013\r|W.\\5ui\u0016\u0014X#\u0001/\u0011\u0005u\u0013W\"\u00010\u000b\u0005}\u0003\u0017!C7baJ,G-^2f\u0015\t\t\u0017&\u0001\u0004iC\u0012|w\u000e]\u0005\u0003Gz\u0013qbT;uaV$8i\\7nSR$XM]\u0001\u000eG>lW.\u001b;uKJ|F%Z9\u0015\u0005\u0019L\u0007CA)h\u0013\tAWH\u0001\u0003V]&$\bb\u00026\u0007\u0003\u0003\u0005\r\u0001X\u0001\u0004q\u0012\n\u0014AC2p[6LG\u000f^3sA!\u0012q!\u001c\t\u0003#:L!a\\\u001f\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018\u0001\u00045bgZ\u000bG.\u001b3QCRDW#\u0001)\u0002\u001b!\f7OV1mS\u0012\u0004\u0016\r\u001e5!\u0003E\tG\rZ3e\u0003\n\u001c\b+\u0019;i\r&dWm]\u000b\u0002kB!ao\u001f$G\u001b\u00059(B\u0001=z\u0003\u001diW\u000f^1cY\u0016T!A_\u001f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002}o\n\u0019Q*\u00199\u0002+\u0005$G-\u001a3BEN\u0004\u0016\r\u001e5GS2,7o\u0018\u0013fcR\u0011am \u0005\bU.\t\t\u00111\u0001v\u0003I\tG\rZ3e\u0003\n\u001c\b+\u0019;i\r&dWm\u001d\u0011)\u00051i\u0017A\u00049beRLG/[8o!\u0006$\bn]\u000b\u0003\u0003\u0013\u0001BA^A\u0006\r&\u0019\u0011QB<\u0003\u0007M+G/\u0001\nqCJ$\u0018\u000e^5p]B\u000bG\u000f[:`I\u0015\fHc\u00014\u0002\u0014!A!NDA\u0001\u0002\u0004\tI!A\bqCJ$\u0018\u000e^5p]B\u000bG\u000f[:!Q\tyQ.\u0001\u0006ti\u0006<\u0017N\\4ESJ,\"!!\b\u0011\t\u0005}\u0011QE\u0007\u0003\u0003CQ1!a\ta\u0003\t17/\u0003\u0003\u0002(\u0005\u0005\"\u0001\u0002)bi\"D#\u0001E7\u0002\u001dM,G/\u001e9D_6l\u0017\u000e\u001e;feR\u0019A,a\f\t\u000f\u0005E\u0012\u00031\u0001\u00024\u000591m\u001c8uKb$\bcA/\u00026%\u0019\u0011q\u00070\u0003%Q\u000b7o[!ui\u0016l\u0007\u000f^\"p]R,\u0007\u0010^\u0001\u0010]\u0016<H+Y:l)\u0016l\u0007OR5mKR9a)!\u0010\u0002B\u0005-\u0003bBA %\u0001\u0007\u00111G\u0001\fi\u0006\u001c8nQ8oi\u0016DH\u000fC\u0004\u0002DI\u0001\r!!\u0012\u0002\u0007\u0011L'\u000f\u0005\u0003R\u0003\u000f2\u0015bAA%{\t1q\n\u001d;j_:Da!!\u0014\u0013\u0001\u00041\u0015aA3yiR9a)!\u0015\u0002T\u0005U\u0003bBA '\u0001\u0007\u00111\u0007\u0005\b\u0003\u0007\u001a\u0002\u0019AA#\u0011\u001d\t9f\u0005a\u0001\u00033\nAa\u001d9fGB\u0019\u0001'a\u0017\n\u0007\u0005u3E\u0001\u0007GS2,g*Y7f'B,7-\u0001\foK^$\u0016m]6UK6\u0004h)\u001b7f\u0003\n\u001c\b+\u0019;i)\u001d1\u00151MA3\u0003SBq!a\u0010\u0015\u0001\u0004\t\u0019\u0004\u0003\u0004\u0002hQ\u0001\rAR\u0001\fC\n\u001cx\u000e\\;uK\u0012K'\u000f\u0003\u0004\u0002NQ\u0001\rA\u0012\u000b\b\r\u00065\u0014qNA9\u0011\u001d\ty$\u0006a\u0001\u0003gAa!a\u001a\u0016\u0001\u00041\u0005bBA,+\u0001\u0007\u0011\u0011L\u0001\fO\u0016$h)\u001b7f]\u0006lW\rF\u0003G\u0003o\nI\bC\u0004\u0002@Y\u0001\r!a\r\t\u000f\u0005]c\u00031\u0001\u0002Z\u0005A1/\u001a;va*{'\rF\u0002g\u0003\u007fBq!!!\u0018\u0001\u0004\t\u0019)\u0001\u0006k_\n\u001cuN\u001c;fqR\u00042!XAC\u0013\r\t9I\u0018\u0002\u000b\u0015>\u00147i\u001c8uKb$\u0018!C2p[6LGOS8c)\u00151\u0017QRAH\u0011\u001d\t\t\t\u0007a\u0001\u0003\u0007Cq!!%\u0019\u0001\u0004\t\u0019*A\u0006uCN\\7i\\7nSR\u001c\b#\u0002\u001b\u0002\u0016\u0006e\u0015bAAL\u0001\n\u00191+Z9\u0011\t\u0005m\u0015\u0011\u0015\b\u0004a\u0005u\u0015bAAPG\u0005\u0011b)\u001b7f\u0007>lW.\u001b;Qe>$xnY8m\u0013\u0011\t\u0019+!*\u0003#Q\u000b7o[\"p[6LG/T3tg\u0006<WMC\u0002\u0002 \u000e\n\u0001\"\u00192peRTuN\u0019\u000b\u0004M\u0006-\u0006bBAA3\u0001\u0007\u00111Q\u0001\ng\u0016$X\u000f\u001d+bg.$2AZAY\u0011\u001d\tyD\u0007a\u0001\u0003g\t!bY8n[&$H+Y:l)\u0011\tI*a.\t\u000f\u0005}2\u00041\u0001\u00024\u0005I\u0011MY8siR\u000b7o\u001b\u000b\u0004M\u0006u\u0006bBA 9\u0001\u0007\u00111G\u0001\u001e\u0011\u0006$wn\u001c9NCB\u0014V\rZ;dK\u000e{W.\\5u!J|Go\\2pYB\u0011\u0001GH\n\u0006=\u0005\u0015\u00171\u001a\t\u0004#\u0006\u001d\u0017bAAe{\t1\u0011I\\=SK\u001a\u0004B!!4\u0002V6\u0011\u0011q\u001a\u0006\u0004I\u0005E'BAAj\u0003\u0011Q\u0017M^1\n\u0007}\ny\r\u0006\u0002\u0002B\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*\"!!8+\u0007A\u000byn\u000b\u0002\u0002bB!\u00111]Aw\u001b\t\t)O\u0003\u0003\u0002h\u0006%\u0018!C;oG\",7m[3e\u0015\r\tY/P\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAx\u0003K\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\u0010\u0005\u0003\u0002x\u0006uXBAA}\u0015\u0011\tY0!5\u0002\t1\fgnZ\u0005\u0005\u0003\u007f\fIP\u0001\u0004PE*,7\r\u001e"
)
public class HadoopMapReduceCommitProtocol extends FileCommitProtocol implements Serializable {
   private transient Path stagingDir;
   private final String jobId;
   private final String path;
   private final boolean dynamicPartitionOverwrite;
   private transient OutputCommitter committer;
   private final boolean hasValidPath;
   private transient Map addedAbsPathFiles;
   private transient Set partitionPaths;
   private transient volatile boolean bitmap$trans$0;

   public static boolean $lessinit$greater$default$3() {
      return HadoopMapReduceCommitProtocol$.MODULE$.$lessinit$greater$default$3();
   }

   private OutputCommitter committer() {
      return this.committer;
   }

   private void committer_$eq(final OutputCommitter x$1) {
      this.committer = x$1;
   }

   private boolean hasValidPath() {
      return this.hasValidPath;
   }

   private Map addedAbsPathFiles() {
      return this.addedAbsPathFiles;
   }

   private void addedAbsPathFiles_$eq(final Map x$1) {
      this.addedAbsPathFiles = x$1;
   }

   private Set partitionPaths() {
      return this.partitionPaths;
   }

   private void partitionPaths_$eq(final Set x$1) {
      this.partitionPaths = x$1;
   }

   private Path stagingDir$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.stagingDir = FileCommitProtocol$.MODULE$.getStagingDir(this.path, this.jobId);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.stagingDir;
   }

   public Path stagingDir() {
      return !this.bitmap$trans$0 ? this.stagingDir$lzycompute() : this.stagingDir;
   }

   public OutputCommitter setupCommitter(final TaskAttemptContext context) {
      OutputFormat format = (OutputFormat)context.getOutputFormatClass().getConstructor().newInstance();
      if (format instanceof Configurable) {
         ((Configurable)format).setConf(context.getConfiguration());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      return format.getOutputCommitter(context);
   }

   public String newTaskTempFile(final TaskAttemptContext taskContext, final Option dir, final String ext) {
      return this.newTaskTempFile(taskContext, dir, new FileNameSpec("", ext));
   }

   public String newTaskTempFile(final TaskAttemptContext taskContext, final Option dir, final FileNameSpec spec) {
      String filename = this.getFilename(taskContext, spec);
      OutputCommitter var7 = this.committer();
      Path var9;
      if (var7 instanceof FileOutputCommitter var8) {
         if (this.dynamicPartitionOverwrite) {
            .MODULE$.assert(dir.isDefined(), () -> "The dataset to be written must be partitioned when dynamicPartitionOverwrite is true.");
            this.partitionPaths().$plus$eq(dir.get());
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         var9 = new Path((String)scala.Option..MODULE$.apply(var8.getWorkPath()).map((x$2) -> x$2.toString()).getOrElse(() -> this.path));
      } else {
         var9 = new Path(this.path);
      }

      Path stagingDir = var9;
      return (String)dir.map((d) -> (new Path(new Path(stagingDir, d), filename)).toString()).getOrElse(() -> (new Path(stagingDir, filename)).toString());
   }

   public String newTaskTempFileAbsPath(final TaskAttemptContext taskContext, final String absoluteDir, final String ext) {
      return this.newTaskTempFileAbsPath(taskContext, absoluteDir, new FileNameSpec("", ext));
   }

   public String newTaskTempFileAbsPath(final TaskAttemptContext taskContext, final String absoluteDir, final FileNameSpec spec) {
      String filename = this.getFilename(taskContext, spec);
      String absOutputPath = (new Path(absoluteDir, filename)).toString();
      Path var10002 = this.stagingDir();
      String var10003 = UUID.randomUUID().toString();
      String tmpOutputPath = (new Path(var10002, var10003 + "-" + filename)).toString();
      this.addedAbsPathFiles().update(tmpOutputPath, absOutputPath);
      return tmpOutputPath;
   }

   public String getFilename(final TaskAttemptContext taskContext, final FileNameSpec spec) {
      int split = taskContext.getTaskAttemptID().getTaskID().getId();
      String basename = taskContext.getConfiguration().get("mapreduce.output.basename", "part");
      return scala.collection.StringOps..MODULE$.format$extension("%s%s-%05d-%s%s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{spec.prefix(), basename, BoxesRunTime.boxToInteger(split), this.jobId, spec.suffix()}));
   }

   public void setupJob(final JobContext jobContext) {
      JobID jobId = SparkHadoopWriterUtils$.MODULE$.createJobID((Date)(new Date()), 0);
      TaskID taskId = new TaskID(jobId, TaskType.MAP, 0);
      TaskAttemptID taskAttemptId = new TaskAttemptID(taskId, 0);
      jobContext.getConfiguration().set("mapreduce.job.id", jobId.toString());
      jobContext.getConfiguration().set("mapreduce.task.id", taskAttemptId.getTaskID().toString());
      jobContext.getConfiguration().set("mapreduce.task.attempt.id", taskAttemptId.toString());
      jobContext.getConfiguration().setBoolean("mapreduce.task.ismap", true);
      jobContext.getConfiguration().setInt("mapreduce.task.partition", 0);
      TaskAttemptContextImpl taskAttemptContext = new TaskAttemptContextImpl(jobContext.getConfiguration(), taskAttemptId);
      this.committer_$eq(this.setupCommitter(taskAttemptContext));
      this.committer().setupJob(jobContext);
   }

   public void commitJob(final JobContext jobContext, final Seq taskCommits) {
      this.committer().commitJob(jobContext);
      if (this.hasValidPath()) {
         Tuple2 var5 = ((IterableOps)taskCommits.map((x$3) -> (Tuple2)x$3.obj())).unzip(.MODULE$.$conforms());
         if (var5 != null) {
            Seq allAbsPathFiles = (Seq)var5._1();
            Seq allPartitionPaths = (Seq)var5._2();
            Tuple2 var4 = new Tuple2(allAbsPathFiles, allPartitionPaths);
            Seq allAbsPathFiles = (Seq)var4._1();
            Seq allPartitionPaths = (Seq)var4._2();
            FileSystem fs = this.stagingDir().getFileSystem(jobContext.getConfiguration());
            scala.collection.immutable.Map filesToMove = (scala.collection.immutable.Map)allAbsPathFiles.foldLeft(.MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$), (x$5, x$6) -> (scala.collection.immutable.Map)x$5.$plus$plus(x$6));
            this.logDebug(() -> "Committing files staged for absolute locations " + filesToMove);
            scala.collection.immutable.Set absParentPaths = ((IterableOnceOps)filesToMove.values().map((x$7) -> (new Path(x$7)).getParent())).toSet();
            if (this.dynamicPartitionOverwrite) {
               this.logDebug(() -> "Clean up absolute partition directories for overwriting: " + absParentPaths);
               absParentPaths.foreach((x$8) -> BoxesRunTime.boxToBoolean($anonfun$commitJob$6(fs, x$8)));
            }

            this.logDebug(() -> "Create absolute parent directories: " + absParentPaths);
            absParentPaths.foreach((x$1) -> BoxesRunTime.boxToBoolean($anonfun$commitJob$8(fs, x$1)));
            filesToMove.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$commitJob$9(check$ifrefutable$1))).foreach((x$9) -> {
               $anonfun$commitJob$10(fs, x$9);
               return BoxedUnit.UNIT;
            });
            if (this.dynamicPartitionOverwrite) {
               scala.collection.immutable.Set partitionPaths = (scala.collection.immutable.Set)allPartitionPaths.foldLeft(.MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$), (x$10, x$11) -> (scala.collection.immutable.Set)x$10.$plus$plus(x$11));
               this.logDebug(() -> "Clean up default partition directories for overwriting: " + partitionPaths);
               partitionPaths.foreach((part) -> {
                  $anonfun$commitJob$13(this, fs, part);
                  return BoxedUnit.UNIT;
               });
            }

            fs.delete(this.stagingDir(), true);
         } else {
            throw new MatchError(var5);
         }
      }
   }

   public void abortJob(final JobContext jobContext) {
      try {
         this.committer().abortJob(jobContext, State.FAILED);
      } catch (IOException var6) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while aborting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, jobContext.getJobID())})))), var6);
      }

      try {
         if (this.hasValidPath()) {
            FileSystem fs = this.stagingDir().getFileSystem(jobContext.getConfiguration());
            fs.delete(this.stagingDir(), true);
         }
      } catch (IOException var5) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while aborting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JOB_ID..MODULE$, jobContext.getJobID())})))), var5);
      }

   }

   public void setupTask(final TaskAttemptContext taskContext) {
      this.committer_$eq(this.setupCommitter(taskContext));
      this.committer().setupTask(taskContext);
      this.addedAbsPathFiles_$eq((Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
      this.partitionPaths_$eq((Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   public FileCommitProtocol.TaskCommitMessage commitTask(final TaskAttemptContext taskContext) {
      TaskAttemptID attemptId = taskContext.getTaskAttemptID();
      this.logTrace(() -> "Commit task " + attemptId);
      SparkHadoopMapRedUtil$.MODULE$.commitTask(this.committer(), taskContext, attemptId.getJobID().getId(), attemptId.getTaskID().getId());
      return new FileCommitProtocol.TaskCommitMessage(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(this.addedAbsPathFiles().toMap(scala..less.colon.less..MODULE$.refl())), this.partitionPaths().toSet()));
   }

   public void abortTask(final TaskAttemptContext taskContext) {
      try {
         this.committer().abortTask(taskContext);
      } catch (IOException var5) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while aborting "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, taskContext.getTaskAttemptID())}))))), var5);
      }

      try {
         this.addedAbsPathFiles().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$abortTask$2(check$ifrefutable$2))).foreach((x$12) -> BoxesRunTime.boxToBoolean($anonfun$abortTask$3(taskContext, x$12)));
      } catch (IOException var4) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while aborting "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, taskContext.getTaskAttemptID())}))))), var4);
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$commitJob$6(final FileSystem fs$1, final Path x$8) {
      return fs$1.delete(x$8, true);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$commitJob$8(final FileSystem fs$1, final Path x$1) {
      return fs$1.mkdirs(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$commitJob$9(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$commitJob$10(final FileSystem fs$1, final Tuple2 x$9) {
      if (x$9 != null) {
         String src = (String)x$9._1();
         String dst = (String)x$9._2();
         if (!fs$1.rename(new Path(src), new Path(dst))) {
            throw new IOException("Failed to rename " + src + " to " + dst + " when committing files staged for absolute locations");
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x$9);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$commitJob$13(final HadoopMapReduceCommitProtocol $this, final FileSystem fs$1, final String part) {
      Path finalPartPath = new Path($this.path, part);
      if (!fs$1.delete(finalPartPath, true) && !fs$1.exists(finalPartPath.getParent())) {
         BoxesRunTime.boxToBoolean(fs$1.mkdirs(finalPartPath.getParent()));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      Path stagingPartPath = new Path($this.stagingDir(), part);
      if (!fs$1.rename(stagingPartPath, finalPartPath)) {
         throw new IOException("Failed to rename " + stagingPartPath + " to " + finalPartPath + " when committing files staged for overwriting dynamic partitions");
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$abortTask$2(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$abortTask$3(final TaskAttemptContext taskContext$1, final Tuple2 x$12) {
      if (x$12 != null) {
         String src = (String)x$12._1();
         Path tmp = new Path(src);
         return tmp.getFileSystem(taskContext$1.getConfiguration()).delete(tmp, false);
      } else {
         throw new MatchError(x$12);
      }
   }

   public HadoopMapReduceCommitProtocol(final String jobId, final String path, final boolean dynamicPartitionOverwrite) {
      this.jobId = jobId;
      this.path = path;
      this.dynamicPartitionOverwrite = dynamicPartitionOverwrite;
      this.hasValidPath = scala.util.Try..MODULE$.apply(() -> new Path(this.path)).isSuccess();
      this.addedAbsPathFiles = null;
      this.partitionPaths = null;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
