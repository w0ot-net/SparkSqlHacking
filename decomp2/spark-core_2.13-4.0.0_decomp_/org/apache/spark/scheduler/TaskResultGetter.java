package org.apache.spark.scheduler;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.SparkEnv;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.TaskKilled;
import org.apache.spark.TaskKilled$;
import org.apache.spark.TaskResultLost$;
import org.apache.spark.TaskState$;
import org.apache.spark.UnknownReason$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.serializer.SerializerHelper$;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.storage.BlockId;
import org.apache.spark.util.LongAccumulator;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua!\u0002\t\u0012\u0001MI\u0002\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011I\u0001!\u0011!Q\u0001\n1BQ\u0001\r\u0001\u0005\u0002EBq!\u000e\u0001C\u0002\u0013%a\u0007\u0003\u0004;\u0001\u0001\u0006Ia\u000e\u0005\bw\u0001\u0011\r\u0011\"\u0005=\u0011\u00199\u0005\u0001)A\u0005{!9\u0001\n\u0001b\u0001\n#I\u0005BB+\u0001A\u0003%!\nC\u0004W\u0001\t\u0007I\u0011C%\t\r]\u0003\u0001\u0015!\u0003K\u0011\u0015A\u0006\u0001\"\u0001Z\u0011\u0015y\u0007\u0001\"\u0001q\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001fAq!!\u0007\u0001\t\u0003\tYB\u0001\tUCN\\'+Z:vYR<U\r\u001e;fe*\u0011!cE\u0001\ng\u000eDW\rZ;mKJT!\u0001F\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005Y9\u0012AB1qC\u000eDWMC\u0001\u0019\u0003\ry'oZ\n\u0004\u0001i\u0001\u0003CA\u000e\u001f\u001b\u0005a\"\"A\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005}a\"AB!osJ+g\r\u0005\u0002\"I5\t!E\u0003\u0002$'\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002&E\t9Aj\\4hS:<\u0017\u0001C:qCJ\\WI\u001c<\u0004\u0001A\u0011\u0011FK\u0007\u0002'%\u00111f\u0005\u0002\t'B\f'o[#omB\u0011QFL\u0007\u0002#%\u0011q&\u0005\u0002\u0012)\u0006\u001c8nU2iK\u0012,H.\u001a:J[Bd\u0017A\u0002\u001fj]&$h\bF\u00023gQ\u0002\"!\f\u0001\t\u000b\u0019\u001a\u0001\u0019\u0001\u0015\t\u000bI\u0019\u0001\u0019\u0001\u0017\u0002\u000fQC%+R!E'V\tq\u0007\u0005\u0002\u001cq%\u0011\u0011\b\b\u0002\u0004\u0013:$\u0018\u0001\u0003+I%\u0016\u000bEi\u0015\u0011\u0002+\u001d,G\u000fV1tWJ+7/\u001e7u\u000bb,7-\u001e;peV\tQ\b\u0005\u0002?\u000b6\tqH\u0003\u0002A\u0003\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\t\u001b\u0015\u0001B;uS2T\u0011\u0001R\u0001\u0005U\u00064\u0018-\u0003\u0002G\u007f\tyQ\t_3dkR|'oU3sm&\u001cW-\u0001\fhKR$\u0016m]6SKN,H\u000e^#yK\u000e,Ho\u001c:!\u0003)\u0019XM]5bY&TXM]\u000b\u0002\u0015B\u00191J\u0014)\u000e\u00031S!!T\"\u0002\t1\fgnZ\u0005\u0003\u001f2\u00131\u0002\u00165sK\u0006$Gj\\2bYB\u0011\u0011kU\u0007\u0002%*\u0011\u0001jE\u0005\u0003)J\u0013!cU3sS\u0006d\u0017N_3s\u0013:\u001cH/\u00198dK\u0006Y1/\u001a:jC2L'0\u001a:!\u0003Q!\u0018m]6SKN,H\u000e^*fe&\fG.\u001b>fe\u0006)B/Y:l%\u0016\u001cX\u000f\u001c;TKJL\u0017\r\\5{KJ\u0004\u0013!F3ocV,W/Z*vG\u000e,7o\u001d4vYR\u000b7o\u001b\u000b\u00055v\u0013w\r\u0005\u0002\u001c7&\u0011A\f\b\u0002\u0005+:LG\u000fC\u0003_\u0019\u0001\u0007q,\u0001\buCN\\7+\u001a;NC:\fw-\u001a:\u0011\u00055\u0002\u0017BA1\u0012\u00059!\u0016m]6TKRl\u0015M\\1hKJDQa\u0019\u0007A\u0002\u0011\f1\u0001^5e!\tYR-\u0003\u0002g9\t!Aj\u001c8h\u0011\u0015AG\u00021\u0001j\u00039\u0019XM]5bY&TX\r\u001a#bi\u0006\u0004\"A[7\u000e\u0003-T!\u0001\\\"\u0002\u00079Lw.\u0003\u0002oW\nQ!)\u001f;f\u0005V4g-\u001a:\u0002#\u0015t\u0017/^3vK\u001a\u000b\u0017\u000e\\3e)\u0006\u001c8\u000e\u0006\u0004[cJ\u001c\u00181\u0002\u0005\u0006=6\u0001\ra\u0018\u0005\u0006G6\u0001\r\u0001\u001a\u0005\u0006i6\u0001\r!^\u0001\ni\u0006\u001c8n\u0015;bi\u0016\u00042A^A\u0003\u001d\r9\u0018\u0011\u0001\b\u0003q~t!!\u001f@\u000f\u0005ilX\"A>\u000b\u0005q<\u0013A\u0002\u001fs_>$h(C\u0001\u0019\u0013\t1r#\u0003\u0002\u0015+%\u0019\u00111A\n\u0002\u0013Q\u000b7o[*uCR,\u0017\u0002BA\u0004\u0003\u0013\u0011\u0011\u0002V1tWN#\u0018\r^3\u000b\u0007\u0005\r1\u0003C\u0003i\u001b\u0001\u0007\u0011.\u0001\u0014f]F,X-^3QCJ$\u0018\u000e^5p]\u000e{W\u000e\u001d7fi&|gNT8uS\u001aL7-\u0019;j_:$RAWA\t\u0003+Aa!a\u0005\u000f\u0001\u00049\u0014aB:uC\u001e,\u0017\n\u001a\u0005\u0007\u0003/q\u0001\u0019A\u001c\u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u0001\u0005gR|\u0007\u000fF\u0001[\u0001"
)
public class TaskResultGetter implements Logging {
   public final SparkEnv org$apache$spark$scheduler$TaskResultGetter$$sparkEnv;
   public final TaskSchedulerImpl org$apache$spark$scheduler$TaskResultGetter$$scheduler;
   private final int THREADS;
   private final ExecutorService getTaskResultExecutor;
   private final ThreadLocal serializer;
   private final ThreadLocal taskResultSerializer;
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

   private int THREADS() {
      return this.THREADS;
   }

   public ExecutorService getTaskResultExecutor() {
      return this.getTaskResultExecutor;
   }

   public ThreadLocal serializer() {
      return this.serializer;
   }

   public ThreadLocal taskResultSerializer() {
      return this.taskResultSerializer;
   }

   public void enqueueSuccessfulTask(final TaskSetManager taskSetManager, final long tid, final ByteBuffer serializedData) {
      this.getTaskResultExecutor().execute(new Runnable(serializedData, taskSetManager, tid) {
         // $FF: synthetic field
         private final TaskResultGetter $outer;
         private final ByteBuffer serializedData$1;
         private final TaskSetManager taskSetManager$1;
         private final long tid$1;

         public void run() {
            Object var1 = new Object();

            try {
               Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> {
                  try {
                     TaskResult var7 = (TaskResult)((SerializerInstance)this.$outer.serializer().get()).deserialize(this.serializedData$1, .MODULE$.apply(TaskResult.class));
                     Tuple2 var27;
                     if (var7 instanceof DirectTaskResult var8) {
                        if (!this.taskSetManager$1.canFetchMoreResults(var8.valueByteBuffer().size())) {
                           this.$outer.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handleFailedTask(this.taskSetManager$1, this.tid$1, TaskState$.MODULE$.KILLED(), new TaskKilled("Tasks result size has exceeded maxResultSize", TaskKilled$.MODULE$.apply$default$2(), TaskKilled$.MODULE$.apply$default$3(), TaskKilled$.MODULE$.apply$default$4()));
                           throw new NonLocalReturnControl.mcV.sp(var1, BoxedUnit.UNIT);
                        }

                        var8.value((SerializerInstance)this.$outer.taskResultSerializer().get());
                        var27 = new Tuple2(var8, BoxesRunTime.boxToLong((long)this.serializedData$1.limit()));
                     } else {
                        if (!(var7 instanceof IndirectTaskResult)) {
                           throw new MatchError(var7);
                        }

                        IndirectTaskResult var9 = (IndirectTaskResult)var7;
                        BlockId blockId = var9.blockId();
                        long size = var9.size();
                        if (!this.taskSetManager$1.canFetchMoreResults(size)) {
                           this.$outer.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv.blockManager().master().removeBlock(blockId);
                           this.$outer.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handleFailedTask(this.taskSetManager$1, this.tid$1, TaskState$.MODULE$.KILLED(), new TaskKilled("Tasks result size has exceeded maxResultSize", TaskKilled$.MODULE$.apply$default$2(), TaskKilled$.MODULE$.apply$default$3(), TaskKilled$.MODULE$.apply$default$4()));
                           throw new NonLocalReturnControl.mcV.sp(var1, BoxedUnit.UNIT);
                        }

                        this.$outer.logDebug((Function0)(() -> "Fetching indirect task result for " + this.taskSetManager$1.taskName(this.tid$1)));
                        this.$outer.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handleTaskGettingResult(this.taskSetManager$1, this.tid$1);
                        Option serializedTaskResult = this.$outer.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv.blockManager().getRemoteBytes(blockId);
                        if (serializedTaskResult.isEmpty()) {
                           this.$outer.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handleFailedTask(this.taskSetManager$1, this.tid$1, TaskState$.MODULE$.FINISHED(), TaskResultLost$.MODULE$);
                           throw new NonLocalReturnControl.mcV.sp(var1, BoxedUnit.UNIT);
                        }

                        DirectTaskResult deserializedResult = (DirectTaskResult)SerializerHelper$.MODULE$.deserializeFromChunkedBuffer((SerializerInstance)this.$outer.serializer().get(), (ChunkedByteBuffer)serializedTaskResult.get(), .MODULE$.apply(DirectTaskResult.class));
                        deserializedResult.value((SerializerInstance)this.$outer.taskResultSerializer().get());
                        this.$outer.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv.blockManager().master().removeBlock(blockId);
                        var27 = new Tuple2(deserializedResult, BoxesRunTime.boxToLong(size));
                     }

                     Tuple2 var6 = var27;
                     if (var6 == null) {
                        throw new MatchError(var6);
                     }

                     DirectTaskResult result = (DirectTaskResult)var6._1();
                     long size = var6._2$mcJ$sp();
                     Tuple2 var5 = new Tuple2(result, BoxesRunTime.boxToLong(size));
                     DirectTaskResult resultx = (DirectTaskResult)var5._1();
                     long sizexx = var5._2$mcJ$sp();
                     resultx.accumUpdates_$eq((Seq)resultx.accumUpdates().map((a) -> {
                        Option var10000 = a.name();
                        Some var3 = new Some(InternalAccumulator$.MODULE$.RESULT_SIZE());
                        if (var10000 == null) {
                           if (var3 != null) {
                              return a;
                           }
                        } else if (!var10000.equals(var3)) {
                           return a;
                        }

                        LongAccumulator acc = (LongAccumulator)a;
                        scala.Predef..MODULE$.assert(acc.sum() == 0L, () -> "task result size should not have been set on the executors");
                        acc.setValue(sizexx);
                        return acc;
                     }));
                     this.$outer.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handleSuccessfulTask(this.taskSetManager$1, this.tid$1, resultx);
                  } catch (Throwable var25) {
                     if (var25 instanceof ClassNotFoundException) {
                        ClassLoader loader = Thread.currentThread().getContextClassLoader();
                        this.taskSetManager$1.abort("ClassNotFound with classloader: " + loader, this.taskSetManager$1.abort$default$2());
                        BoxedUnit var26 = BoxedUnit.UNIT;
                     } else {
                        if (var25 == null || !scala.util.control.NonFatal..MODULE$.apply(var25)) {
                           throw var25;
                        }

                        this.$outer.logError((Function0)(() -> "Exception while getting task result"), var25);
                        this.taskSetManager$1.abort(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Exception while getting task result: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{var25})), this.taskSetManager$1.abort$default$2());
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     }
                  }

               });
            } catch (NonLocalReturnControl var3) {
               if (var3.key() != var1) {
                  throw var3;
               }

               var3.value$mcV$sp();
            }

         }

         public {
            if (TaskResultGetter.this == null) {
               throw null;
            } else {
               this.$outer = TaskResultGetter.this;
               this.serializedData$1 = serializedData$1;
               this.taskSetManager$1 = taskSetManager$1;
               this.tid$1 = tid$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public void enqueueFailedTask(final TaskSetManager taskSetManager, final long tid, final Enumeration.Value taskState, final ByteBuffer serializedData) {
      ObjectRef reason = ObjectRef.create(UnknownReason$.MODULE$);

      try {
         this.getTaskResultExecutor().execute(() -> Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> {
               ClassLoader loader = Utils$.MODULE$.getContextOrSparkClassLoader();

               try {
                  if (serializedData != null && serializedData.limit() > 0) {
                     reason.elem = (TaskFailedReason)((SerializerInstance)this.serializer().get()).deserialize(serializedData, loader, .MODULE$.apply(TaskFailedReason.class));
                  }
               } catch (ClassNotFoundException var12) {
                  this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not deserialize TaskEndReason: ClassNotFound with classloader "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_LOADER..MODULE$, loader)}))))));
               } catch (Exception var13) {
               } finally {
                  this.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handleFailedTask(taskSetManager, tid, taskState, (TaskFailedReason)reason.elem);
               }

            }));
      } catch (Throwable var10) {
         if (!(var10 instanceof RejectedExecutionException) || !this.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv.isStopped()) {
            throw var10;
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public void enqueuePartitionCompletionNotification(final int stageId, final int partitionId) {
      this.getTaskResultExecutor().execute(() -> Utils$.MODULE$.logUncaughtExceptions((JFunction0.mcV.sp)() -> this.org$apache$spark$scheduler$TaskResultGetter$$scheduler.handlePartitionCompleted(stageId, partitionId)));
   }

   public void stop() {
      this.getTaskResultExecutor().shutdownNow();
   }

   public TaskResultGetter(final SparkEnv sparkEnv, final TaskSchedulerImpl scheduler) {
      this.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv = sparkEnv;
      this.org$apache$spark$scheduler$TaskResultGetter$$scheduler = scheduler;
      Logging.$init$(this);
      this.THREADS = sparkEnv.conf().getInt("spark.resultGetter.threads", 4);
      this.getTaskResultExecutor = ThreadUtils$.MODULE$.newDaemonFixedThreadPool(this.THREADS(), "task-result-getter");
      this.serializer = new ThreadLocal() {
         // $FF: synthetic field
         private final TaskResultGetter $outer;

         public SerializerInstance initialValue() {
            return this.$outer.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv.closureSerializer().newInstance();
         }

         public {
            if (TaskResultGetter.this == null) {
               throw null;
            } else {
               this.$outer = TaskResultGetter.this;
            }
         }
      };
      this.taskResultSerializer = new ThreadLocal() {
         // $FF: synthetic field
         private final TaskResultGetter $outer;

         public SerializerInstance initialValue() {
            return this.$outer.org$apache$spark$scheduler$TaskResultGetter$$sparkEnv.serializer().newInstance();
         }

         public {
            if (TaskResultGetter.this == null) {
               throw null;
            } else {
               this.$outer = TaskResultGetter.this;
            }
         }
      };
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
