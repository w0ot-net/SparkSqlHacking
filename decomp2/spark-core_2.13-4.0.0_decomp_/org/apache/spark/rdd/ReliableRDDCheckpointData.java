package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.ContextCleaner;
import org.apache.spark.SparkContext;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b!B\b\u0011\u0001IA\u0002\u0002C\t\u0001\u0005\u000b\u0007I\u0011B\u001a\t\u0011]\u0002!\u0011!Q\u0001\nQB\u0001\u0002\u0010\u0001\u0003\u0004\u0003\u0006Y!\u0010\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\b\u0013\u0002\u0011\r\u0011\"\u0003K\u0011\u00191\u0006\u0001)A\u0005\u0017\")q\u000b\u0001C\u00011\")A\f\u0001C);\u001e1\u0011\r\u0005E\u0001%\t4aa\u0004\t\t\u0002I\u0019\u0007\"B\"\u000b\t\u0003y\u0007\"\u00029\u000b\t\u0003\t\bbBA\u0007\u0015\u0011\u0005\u0011q\u0002\u0005\n\u00037Q\u0011\u0011!C\u0005\u0003;\u0011\u0011DU3mS\u0006\u0014G.\u001a*E\t\u000eCWmY6q_&tG\u000fR1uC*\u0011\u0011CE\u0001\u0004e\u0012$'BA\n\u0015\u0003\u0015\u0019\b/\u0019:l\u0015\t)b#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002/\u0005\u0019qN]4\u0016\u0005e\u00013c\u0001\u0001\u001b[A\u00191\u0004\b\u0010\u000e\u0003AI!!\b\t\u0003#I#Ei\u00115fG.\u0004x.\u001b8u\t\u0006$\u0018\r\u0005\u0002 A1\u0001A!B\u0011\u0001\u0005\u0004\u0019#!\u0001+\u0004\u0001E\u0011AE\u000b\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\b\u001d>$\b.\u001b8h!\t)3&\u0003\u0002-M\t\u0019\u0011I\\=\u0011\u00059\nT\"A\u0018\u000b\u0005A\u0012\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005Iz#a\u0002'pO\u001eLgnZ\u000b\u0002iA\u00191$\u000e\u0010\n\u0005Y\u0002\"a\u0001*E\t\u0006!!\u000f\u001a3!Q\t\u0011\u0011\b\u0005\u0002&u%\u00111H\n\u0002\niJ\fgn]5f]R\f!\"\u001a<jI\u0016t7-\u001a\u00132!\rq\u0014IH\u0007\u0002\u007f)\u0011\u0001IJ\u0001\be\u00164G.Z2u\u0013\t\u0011uH\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q\u0011Q\t\u0013\u000b\u0003\r\u001e\u00032a\u0007\u0001\u001f\u0011\u0015aD\u0001q\u0001>\u0011\u0015\tB\u00011\u00015\u0003\u0015\u0019\u0007\u000fR5s+\u0005Y\u0005C\u0001'T\u001d\ti\u0015\u000b\u0005\u0002OM5\tqJ\u0003\u0002QE\u00051AH]8pizJ!A\u0015\u0014\u0002\rA\u0013X\rZ3g\u0013\t!VK\u0001\u0004TiJLgn\u001a\u0006\u0003%\u001a\naa\u00199ESJ\u0004\u0013\u0001E4fi\u000eCWmY6q_&tG\u000fR5s+\u0005I\u0006cA\u0013[\u0017&\u00111L\n\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u0019\u0011|7\t[3dWB|\u0017N\u001c;\u0015\u0003y\u00032aG0\u001f\u0013\t\u0001\u0007CA\u0007DQ\u0016\u001c7\u000e]8j]R\u0014F\tR\u0001\u001a%\u0016d\u0017.\u00192mKJ#Ei\u00115fG.\u0004x.\u001b8u\t\u0006$\u0018\r\u0005\u0002\u001c\u0015M!!\u0002Z\u0017h!\t)S-\u0003\u0002gM\t1\u0011I\\=SK\u001a\u0004\"\u0001[7\u000e\u0003%T!A[6\u0002\u0005%|'\"\u00017\u0002\t)\fg/Y\u0005\u0003]&\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\u0012AY\u0001\u000fG\",7m\u001b9pS:$\b+\u0019;i)\u0011\u001180a\u0001\u0011\u0007\u0015R6\u000f\u0005\u0002us6\tQO\u0003\u0002wo\u0006\u0011am\u001d\u0006\u0003qR\ta\u0001[1e_>\u0004\u0018B\u0001>v\u0005\u0011\u0001\u0016\r\u001e5\t\u000bqd\u0001\u0019A?\u0002\u0005M\u001c\u0007C\u0001@\u0000\u001b\u0005\u0011\u0012bAA\u0001%\ta1\u000b]1sW\u000e{g\u000e^3yi\"9\u0011Q\u0001\u0007A\u0002\u0005\u001d\u0011!\u0002:eI&#\u0007cA\u0013\u0002\n%\u0019\u00111\u0002\u0014\u0003\u0007%sG/A\bdY\u0016\fgn\u00115fG.\u0004x.\u001b8u)\u0019\t\t\"a\u0006\u0002\u001aA\u0019Q%a\u0005\n\u0007\u0005UaE\u0001\u0003V]&$\b\"\u0002?\u000e\u0001\u0004i\bbBA\u0003\u001b\u0001\u0007\u0011qA\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003?\u0001B!!\t\u0002(5\u0011\u00111\u0005\u0006\u0004\u0003KY\u0017\u0001\u00027b]\u001eLA!!\u000b\u0002$\t1qJ\u00196fGR\u0004"
)
public class ReliableRDDCheckpointData extends RDDCheckpointData implements Logging {
   private final transient RDD rdd;
   private final ClassTag evidence$1;
   private final String cpDir;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static void cleanCheckpoint(final SparkContext sc, final int rddId) {
      ReliableRDDCheckpointData$.MODULE$.cleanCheckpoint(sc, rddId);
   }

   public static Option checkpointPath(final SparkContext sc, final int rddId) {
      return ReliableRDDCheckpointData$.MODULE$.checkpointPath(sc, rddId);
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

   private String cpDir() {
      return this.cpDir;
   }

   public Option getCheckpointDir() {
      synchronized(RDDCheckpointData$.MODULE$){}

      Object var2;
      try {
         var2 = this.isCheckpointed() ? new Some(this.cpDir()) : .MODULE$;
      } catch (Throwable var4) {
         throw var4;
      }

      return (Option)var2;
   }

   public CheckpointRDD doCheckpoint() {
      ReliableCheckpointRDD newRDD = ReliableCheckpointRDD$.MODULE$.writeRDDToCheckpointDirectory(this.rdd(), this.cpDir(), ReliableCheckpointRDD$.MODULE$.writeRDDToCheckpointDirectory$default$3(), this.evidence$1);
      if (BoxesRunTime.unboxToBoolean(this.rdd().conf().get(org.apache.spark.internal.config.package$.MODULE$.CLEANER_REFERENCE_TRACKING_CLEAN_CHECKPOINTS()))) {
         this.rdd().context().cleaner().foreach((cleaner) -> {
            $anonfun$doCheckpoint$1(this, newRDD, cleaner);
            return BoxedUnit.UNIT;
         });
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Done checkpointing RDD ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_ID..MODULE$, BoxesRunTime.boxToInteger(this.rdd().id()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ", new parent is RDD ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RDD_CHECKPOINT_DIR..MODULE$, this.cpDir()), new MDC(org.apache.spark.internal.LogKeys.NEW_RDD_ID..MODULE$, BoxesRunTime.boxToInteger(newRDD.id()))}))))));
      return newRDD;
   }

   // $FF: synthetic method
   public static final void $anonfun$doCheckpoint$1(final ReliableRDDCheckpointData $this, final ReliableCheckpointRDD newRDD$1, final ContextCleaner cleaner) {
      cleaner.registerRDDCheckpointDataForCleanup(newRDD$1, $this.rdd().id());
   }

   public ReliableRDDCheckpointData(final RDD rdd, final ClassTag evidence$1) {
      super(rdd, evidence$1);
      this.rdd = rdd;
      this.evidence$1 = evidence$1;
      Logging.$init$(this);
      this.cpDir = (String)ReliableRDDCheckpointData$.MODULE$.checkpointPath(rdd.context(), rdd.id()).map((x$1) -> x$1.toString()).getOrElse(() -> {
         throw SparkCoreErrors$.MODULE$.mustSpecifyCheckpointDirError();
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
