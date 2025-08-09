package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.Partition;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.storage.RDDBlockId;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.StringContext;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q!\u0004\b\u0001!YA\u0001b\u0004\u0001\u0003\u0006\u0004%I!\r\u0005\tk\u0001\u0011\t\u0011)A\u0005e!A!\b\u0001B\u0002B\u0003-1\bC\u0003B\u0001\u0011\u0005!\tC\u0003H\u0001\u0011E\u0003j\u0002\u0004M\u001d!\u0005\u0001#\u0014\u0004\u0007\u001b9A\t\u0001\u0005(\t\u000b\u0005;A\u0011\u0001.\t\u000fm;!\u0019!C\u00019\"11m\u0002Q\u0001\nuCQ\u0001Z\u0004\u0005\u0002\u0015Dq\u0001[\u0004\u0002\u0002\u0013%\u0011N\u0001\fM_\u000e\fGN\u0015#E\u0007\",7m\u001b9pS:$H)\u0019;b\u0015\ty\u0001#A\u0002sI\u0012T!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\u000b\u0003/y\u00192\u0001\u0001\r,!\rI\"\u0004H\u0007\u0002\u001d%\u00111D\u0004\u0002\u0012%\u0012#5\t[3dWB|\u0017N\u001c;ECR\f\u0007CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0005\u0012\u0011\u0001V\u0002\u0001#\t\u0011\u0003\u0006\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CEA\u0004O_RD\u0017N\\4\u0011\u0005\rJ\u0013B\u0001\u0016%\u0005\r\te.\u001f\t\u0003Y=j\u0011!\f\u0006\u0003]A\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003a5\u0012q\u0001T8hO&tw-F\u00013!\rI2\u0007H\u0005\u0003i9\u00111A\u0015#E\u0003\u0011\u0011H\r\u001a\u0011)\u0005\t9\u0004CA\u00129\u0013\tIDEA\u0005ue\u0006t7/[3oi\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007qzD$D\u0001>\u0015\tqD%A\u0004sK\u001adWm\u0019;\n\u0005\u0001k$\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\t\u0019e\t\u0006\u0002E\u000bB\u0019\u0011\u0004\u0001\u000f\t\u000bi\"\u00019A\u001e\t\u000b=!\u0001\u0019\u0001\u001a\u0002\u0019\u0011|7\t[3dWB|\u0017N\u001c;\u0015\u0003%\u00032!\u0007&\u001d\u0013\tYeBA\u0007DQ\u0016\u001c7\u000e]8j]R\u0014F\tR\u0001\u0017\u0019>\u001c\u0017\r\u001c*E\t\u000eCWmY6q_&tG\u000fR1uCB\u0011\u0011dB\n\u0004\u000f=\u0013\u0006CA\u0012Q\u0013\t\tFE\u0001\u0004B]f\u0014VM\u001a\t\u0003'bk\u0011\u0001\u0016\u0006\u0003+Z\u000b!![8\u000b\u0003]\u000bAA[1wC&\u0011\u0011\f\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0002\u001b\u0006)B)\u0012$B+2#vl\u0015+P%\u0006;Ui\u0018'F-\u0016cU#A/\u0011\u0005y\u000bW\"A0\u000b\u0005\u0001\u0004\u0012aB:u_J\fw-Z\u0005\u0003E~\u0013Ab\u0015;pe\u0006<W\rT3wK2\fa\u0003R#G\u0003VcEkX*U\u001fJ\u000bu)R0M\u000bZ+E\nI\u0001\u0016iJ\fgn\u001d4pe6\u001cFo\u001c:bO\u0016dUM^3m)\tif\rC\u0003h\u0017\u0001\u0007Q,A\u0003mKZ,G.\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001k!\tYg.D\u0001m\u0015\tig+\u0001\u0003mC:<\u0017BA8m\u0005\u0019y%M[3di\u0002"
)
public class LocalRDDCheckpointData extends RDDCheckpointData implements Logging {
   private final transient RDD rdd;
   private final ClassTag evidence$1;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static StorageLevel transformStorageLevel(final StorageLevel level) {
      return LocalRDDCheckpointData$.MODULE$.transformStorageLevel(level);
   }

   public static StorageLevel DEFAULT_STORAGE_LEVEL() {
      return LocalRDDCheckpointData$.MODULE$.DEFAULT_STORAGE_LEVEL();
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

   private RDD rdd() {
      return this.rdd;
   }

   public CheckpointRDD doCheckpoint() {
      StorageLevel level = this.rdd().getStorageLevel();
      .MODULE$.assume(level.useDisk(), () -> "Storage level " + level + " is not appropriate for local checkpointing");
      Function2 action = (tc, iterator) -> BoxesRunTime.boxToLong($anonfun$doCheckpoint$2(tc, iterator));
      int[] missingPartitionIndices = (int[])scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.intArrayOps((int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(this.rdd().partitions()), (x$1) -> BoxesRunTime.boxToInteger($anonfun$doCheckpoint$3(x$1)), scala.reflect.ClassTag..MODULE$.Int())), (JFunction1.mcZI.sp)(i) -> !SparkEnv$.MODULE$.get().blockManager().master().contains(new RDDBlockId(this.rdd().id(), i)));
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.intArrayOps(missingPartitionIndices))) {
         this.rdd().sparkContext().runJob(this.rdd(), (Function2)action, (Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(missingPartitionIndices).toImmutableArraySeq(), scala.reflect.ClassTag..MODULE$.Long());
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return new LocalCheckpointRDD(this.rdd(), this.evidence$1);
   }

   // $FF: synthetic method
   public static final long $anonfun$doCheckpoint$2(final TaskContext tc, final Iterator iterator) {
      return Utils$.MODULE$.getIteratorSize(iterator);
   }

   // $FF: synthetic method
   public static final int $anonfun$doCheckpoint$3(final Partition x$1) {
      return x$1.index();
   }

   public LocalRDDCheckpointData(final RDD rdd, final ClassTag evidence$1) {
      super(rdd, evidence$1);
      this.rdd = rdd;
      this.evidence$1 = evidence$1;
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
