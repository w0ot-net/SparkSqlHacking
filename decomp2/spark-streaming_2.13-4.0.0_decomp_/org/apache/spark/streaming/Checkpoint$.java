package org.apache.spark.streaming;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.io.CompressionCodec;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;

public final class Checkpoint$ implements Logging, Serializable {
   public static final Checkpoint$ MODULE$ = new Checkpoint$();
   private static final String PREFIX;
   private static final Regex REGEX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      PREFIX = "checkpoint-";
      REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString(MODULE$.PREFIX() + "([\\d]+)([\\w\\.]*)"));
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String PREFIX() {
      return PREFIX;
   }

   public Regex REGEX() {
      return REGEX;
   }

   public Path checkpointFile(final String checkpointDir, final Time checkpointTime) {
      String var10003 = this.PREFIX();
      return new Path(checkpointDir, var10003 + checkpointTime.milliseconds());
   }

   public Path checkpointBackupFile(final String checkpointDir, final Time checkpointTime) {
      String var10003 = this.PREFIX();
      return new Path(checkpointDir, var10003 + checkpointTime.milliseconds() + ".bk");
   }

   public Seq getCheckpointFiles(final String checkpointDir, final Option fsOption) {
      Path path = new Path(checkpointDir);
      FileSystem fs = (FileSystem)fsOption.getOrElse(() -> path.getFileSystem(org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().conf()));

      Object var10000;
      try {
         FileStatus[] statuses = fs.listStatus(path);
         if (statuses != null) {
            Path[] paths = (Path[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filterNot$extension(scala.Predef..MODULE$.refArrayOps((Object[])statuses), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$getCheckpointFiles$2(x$3)))), (x$4) -> x$4.getPath(), scala.reflect.ClassTag..MODULE$.apply(Path.class));
            Path[] filtered = (Path[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])paths), (p) -> BoxesRunTime.boxToBoolean($anonfun$getCheckpointFiles$4(p)));
            var10000 = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.sortWith$extension(scala.Predef..MODULE$.refArrayOps((Object[])filtered), (path1, path2) -> BoxesRunTime.boxToBoolean($anonfun$getCheckpointFiles$5(this, path1, path2)))).toImmutableArraySeq();
         } else {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Listing ", " returned null"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))));
            var10000 = (Seq)scala.package..MODULE$.Seq().empty();
         }
      } catch (FileNotFoundException var8) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Checkpoint directory ", " does not exist"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))));
         var10000 = (Seq)scala.package..MODULE$.Seq().empty();
      }

      return (Seq)var10000;
   }

   public Option getCheckpointFiles$default$2() {
      return scala.None..MODULE$;
   }

   public byte[] serialize(final Checkpoint checkpoint, final SparkConf conf) {
      CompressionCodec compressionCodec = org.apache.spark.io.CompressionCodec..MODULE$.createCodec(conf);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      OutputStream zos = compressionCodec.compressedOutputStream(bos);
      ObjectOutputStream oos = new ObjectOutputStream(zos);
      org.apache.spark.util.Utils..MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> oos.writeObject(checkpoint), (JFunction0.mcV.sp)() -> oos.close());
      return bos.toByteArray();
   }

   public Checkpoint deserialize(final InputStream inputStream, final SparkConf conf) {
      CompressionCodec compressionCodec = org.apache.spark.io.CompressionCodec..MODULE$.createCodec(conf);
      ObjectRef ois = ObjectRef.create((Object)null);
      return (Checkpoint)org.apache.spark.util.Utils..MODULE$.tryWithSafeFinally(() -> {
         InputStream zis = compressionCodec.compressedInputStream(inputStream);
         ois.elem = new ObjectInputStreamWithLoader(zis, Thread.currentThread().getContextClassLoader());
         Checkpoint cp = (Checkpoint)((ObjectInputStreamWithLoader)ois.elem).readObject();
         cp.validate();
         return cp;
      }, (JFunction0.mcV.sp)() -> {
         if ((ObjectInputStreamWithLoader)ois.elem != null) {
            ((ObjectInputStreamWithLoader)ois.elem).close();
         }
      });
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Checkpoint$.class);
   }

   private final boolean sortFunc$1(final Path path1, final Path path2) {
      String var9 = path1.getName();
      if (var9 != null) {
         Option var10 = this.REGEX().unapplySeq(var9);
         if (!var10.isEmpty() && var10.get() != null && ((List)var10.get()).lengthCompare(2) == 0) {
            String x = (String)((LinearSeqOps)var10.get()).apply(0);
            String y = (String)((LinearSeqOps)var10.get()).apply(1);
            Tuple2.mcJZ.sp var8 = new Tuple2.mcJZ.sp(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x)), !y.isEmpty());
            if (var8 == null) {
               throw new MatchError(var8);
            }

            long time1 = ((Tuple2)var8)._1$mcJ$sp();
            boolean bk1 = ((Tuple2)var8)._2$mcZ$sp();
            Tuple2.mcJZ.sp var7 = new Tuple2.mcJZ.sp(time1, bk1);
            long time1 = ((Tuple2)var7)._1$mcJ$sp();
            boolean bk1 = ((Tuple2)var7)._2$mcZ$sp();
            String var21 = path2.getName();
            if (var21 != null) {
               Option var22 = this.REGEX().unapplySeq(var21);
               if (!var22.isEmpty() && var22.get() != null && ((List)var22.get()).lengthCompare(2) == 0) {
                  String x = (String)((LinearSeqOps)var22.get()).apply(0);
                  String y = (String)((LinearSeqOps)var22.get()).apply(1);
                  Tuple2.mcJZ.sp var20 = new Tuple2.mcJZ.sp(.MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x)), !y.isEmpty());
                  if (var20 == null) {
                     throw new MatchError(var20);
                  }

                  long time2 = ((Tuple2)var20)._1$mcJ$sp();
                  boolean bk2 = ((Tuple2)var20)._2$mcZ$sp();
                  Tuple2.mcJZ.sp var19 = new Tuple2.mcJZ.sp(time2, bk2);
                  long time2 = ((Tuple2)var19)._1$mcJ$sp();
                  boolean var30 = ((Tuple2)var19)._2$mcZ$sp();
                  return time1 < time2 || time1 == time2 && bk1;
               }
            }

            throw new MatchError(var21);
         }
      }

      throw new MatchError(var9);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCheckpointFiles$2(final FileStatus x$3) {
      return x$3.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCheckpointFiles$4(final Path p) {
      return MODULE$.REGEX().findFirstIn(p.getName()).nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCheckpointFiles$5(final Checkpoint$ $this, final Path path1, final Path path2) {
      return $this.sortFunc$1(path1, path2);
   }

   private Checkpoint$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
