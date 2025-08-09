package org.apache.spark.memory;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.StringContext;
import scala.collection.mutable.HashMap;
import scala.math.Numeric.LongIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!\u0002\b\u0010\u0001=9\u0002\u0002\u0003\u0012\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0013\t\u00111\u0002!\u0011!Q\u0001\n5BQ\u0001\r\u0001\u0005\u0002EBa!\u000e\u0001!\u0002\u00131\u0004bB\"\u0001\u0005\u0004%I\u0001\u0012\u0005\u0007#\u0002\u0001\u000b\u0011B#\t\u000b}\u0003A\u0011\t1\t\u000b\u0005\u0004A\u0011\u00012\t\r\u0015\u0004A\u0011A\bg\u0011!9\b!%A\u0005\u0002=A\bBCA\u0003\u0001E\u0005I\u0011A\b\u0002\b!9\u00111\u0002\u0001\u0005\u0002\u00055\u0001bBA\n\u0001\u0011\u0005\u0011Q\u0003\u0002\u0014\u000bb,7-\u001e;j_:lU-\\8ssB{w\u000e\u001c\u0006\u0003!E\ta!\\3n_JL(B\u0001\n\u0014\u0003\u0015\u0019\b/\u0019:l\u0015\t!R#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0005\u0019qN]4\u0014\u0007\u0001AB\u0004\u0005\u0002\u001a55\tq\"\u0003\u0002\u001c\u001f\tQQ*Z7pef\u0004vn\u001c7\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0005}\t\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0005r\"a\u0002'pO\u001eLgnZ\u0001\u0005Y>\u001c7n\u0001\u0001\u0011\u0005\u0015RS\"\u0001\u0014\u000b\u0005\u001dB\u0013\u0001\u00027b]\u001eT\u0011!K\u0001\u0005U\u00064\u0018-\u0003\u0002,M\t1qJ\u00196fGR\f!\"\\3n_JLXj\u001c3f!\tIb&\u0003\u00020\u001f\tQQ*Z7peflu\u000eZ3\u0002\rqJg.\u001b;?)\r\u00114\u0007\u000e\t\u00033\u0001AQAI\u0002A\u0002\u0011BQ\u0001L\u0002A\u00025\n\u0001\u0002]8pY:\u000bW.\u001a\t\u0003o\u0001s!\u0001\u000f \u0011\u0005ebT\"\u0001\u001e\u000b\u0005m\u001a\u0013A\u0002\u001fs_>$hHC\u0001>\u0003\u0015\u00198-\u00197b\u0013\tyD(\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0003\n\u0013aa\u0015;sS:<'BA =\u00035iW-\\8ss\u001a{'\u000fV1tWV\tQ\t\u0005\u0003G\u00176kU\"A$\u000b\u0005!K\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003\u0015r\n!bY8mY\u0016\u001cG/[8o\u0013\tauIA\u0004ICNDW*\u00199\u0011\u00059{U\"\u0001\u001f\n\u0005Ac$\u0001\u0002'p]\u001e\fa\"\\3n_JLhi\u001c:UCN\\\u0007\u0005\u000b\u0003\u0007'vs\u0006C\u0001+\\\u001b\u0005)&B\u0001,X\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u00031f\u000b!\"\u00198o_R\fG/[8o\u0015\u0005Q\u0016!\u00026bm\u0006D\u0018B\u0001/V\u0005%9U/\u0019:eK\u0012\u0014\u00150A\u0003wC2,X-I\u0001#\u0003)iW-\\8ssV\u001bX\rZ\u000b\u0002\u001b\u0006)r-\u001a;NK6|'/_+tC\u001e,gi\u001c:UCN\\GCA'd\u0011\u0015!\u0007\u00021\u0001N\u00035!\u0018m]6BiR,W\u000e\u001d;JI\u0006i\u0011mY9vSJ,W*Z7pef$R!T4jUJDQ\u0001[\u0005A\u00025\u000b\u0001B\\;n\u0005f$Xm\u001d\u0005\u0006I&\u0001\r!\u0014\u0005\bW&\u0001\n\u00111\u0001m\u00035i\u0017-\u001f2f\u000fJ|w\u000fU8pYB!a*\\'p\u0013\tqGHA\u0005Gk:\u001cG/[8ocA\u0011a\n]\u0005\u0003cr\u0012A!\u00168ji\"91/\u0003I\u0001\u0002\u0004!\u0018AE2p[B,H/Z'bqB{w\u000e\\*ju\u0016\u00042AT;N\u0013\t1HHA\u0005Gk:\u001cG/[8oa\u00059\u0012mY9vSJ,W*Z7pef$C-\u001a4bk2$HeM\u000b\u0002s*\u0012AN_\u0016\u0002wB\u0019A0!\u0001\u000e\u0003uT!A`@\u0002\u0013Ut7\r[3dW\u0016$'B\u0001-=\u0013\r\t\u0019! \u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017aF1dcVL'/Z'f[>\u0014\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\tIA\u000b\u0002uu\u0006i!/\u001a7fCN,W*Z7pef$Ra\\A\b\u0003#AQ\u0001\u001b\u0007A\u00025CQ\u0001\u001a\u0007A\u00025\u000bqC]3mK\u0006\u001cX-\u00117m\u001b\u0016lwN]=G_J$\u0016m]6\u0015\u00075\u000b9\u0002C\u0003e\u001b\u0001\u0007Q\n"
)
public class ExecutionMemoryPool extends MemoryPool implements Logging {
   private final Object lock;
   private final String poolName;
   @GuardedBy("lock")
   private final HashMap memoryForTask;
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

   private HashMap memoryForTask() {
      return this.memoryForTask;
   }

   public long memoryUsed() {
      synchronized(this.lock){}

      long var2;
      try {
         var2 = BoxesRunTime.unboxToLong(this.memoryForTask().values().sum(.MODULE$));
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   public long getMemoryUsageForTask(final long taskAttemptId) {
      synchronized(this.lock){}

      long var4;
      try {
         var4 = BoxesRunTime.unboxToLong(this.memoryForTask().getOrElse(BoxesRunTime.boxToLong(taskAttemptId), (JFunction0.mcJ.sp)() -> 0L));
      } catch (Throwable var7) {
         throw var7;
      }

      return var4;
   }

   public long acquireMemory(final long numBytes, final long taskAttemptId, final Function1 maybeGrowPool, final Function0 computeMaxPoolSize) {
      synchronized(this.lock){}

      try {
         scala.Predef..MODULE$.assert(numBytes > 0L, () -> "invalid number of bytes requested: " + numBytes);
         if (!this.memoryForTask().contains(BoxesRunTime.boxToLong(taskAttemptId))) {
            this.memoryForTask().update(BoxesRunTime.boxToLong(taskAttemptId), BoxesRunTime.boxToLong(0L));
            this.lock.notifyAll();
         }

         while(true) {
            int numActiveTasks = this.memoryForTask().keys().size();
            long curMem = BoxesRunTime.unboxToLong(this.memoryForTask().apply(BoxesRunTime.boxToLong(taskAttemptId)));
            maybeGrowPool.apply$mcVJ$sp(numBytes - this.memoryFree());
            long maxPoolSize = computeMaxPoolSize.apply$mcJ$sp();
            long maxMemoryPerTask = maxPoolSize / (long)numActiveTasks;
            long minMemoryPerTask = this.poolSize() / (long)(2 * numActiveTasks);
            long maxToGrant = scala.math.package..MODULE$.min(numBytes, scala.math.package..MODULE$.max(0L, maxMemoryPerTask - curMem));
            long toGrant = scala.math.package..MODULE$.min(maxToGrant, this.memoryFree());
            if (toGrant >= numBytes || curMem + toGrant >= minMemoryPerTask) {
               this.memoryForTask().update(BoxesRunTime.boxToLong(taskAttemptId), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(this.memoryForTask().apply(BoxesRunTime.boxToLong(taskAttemptId))) + toGrant));
               long var23 = toGrant;
               return var23;
            }

            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"TID ", " waiting for at least 1/2N of"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToLong(taskAttemptId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " pool to be free"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.POOL_NAME..MODULE$, this.poolName)}))))));
            this.lock.wait();
         }
      } catch (Throwable var26) {
         throw var26;
      }
   }

   public Function1 acquireMemory$default$3() {
      return (JFunction1.mcVJ.sp)(additionalSpaceNeeded) -> {
      };
   }

   public Function0 acquireMemory$default$4() {
      return (JFunction0.mcJ.sp)() -> this.poolSize();
   }

   public void releaseMemory(final long numBytes, final long taskAttemptId) {
      synchronized(this.lock){}

      try {
         long curMem = BoxesRunTime.unboxToLong(this.memoryForTask().getOrElse(BoxesRunTime.boxToLong(taskAttemptId), (JFunction0.mcJ.sp)() -> 0L));
         long var10000;
         if (curMem < numBytes) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Internal error: release called on ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(numBytes))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"bytes but task only has ", " bytes "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToLong(curMem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"of memory from the ", " pool"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_POOL_NAME..MODULE$, this.poolName)}))))));
            var10000 = curMem;
         } else {
            var10000 = numBytes;
         }

         long memoryToFree = var10000;
         if (this.memoryForTask().contains(BoxesRunTime.boxToLong(taskAttemptId))) {
            this.memoryForTask().update(BoxesRunTime.boxToLong(taskAttemptId), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(this.memoryForTask().apply(BoxesRunTime.boxToLong(taskAttemptId))) - memoryToFree));
            if (BoxesRunTime.unboxToLong(this.memoryForTask().apply(BoxesRunTime.boxToLong(taskAttemptId))) <= 0L) {
               this.memoryForTask().remove(BoxesRunTime.boxToLong(taskAttemptId));
            } else {
               BoxedUnit var12 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var13 = BoxedUnit.UNIT;
         }

         this.lock.notifyAll();
      } catch (Throwable var11) {
         throw var11;
      }

   }

   public long releaseAllMemoryForTask(final long taskAttemptId) {
      synchronized(this.lock){}

      long var4;
      try {
         long numBytesToFree = this.getMemoryUsageForTask(taskAttemptId);
         this.releaseMemory(numBytesToFree, taskAttemptId);
         var4 = numBytesToFree;
      } catch (Throwable var9) {
         throw var9;
      }

      return var4;
   }

   public ExecutionMemoryPool(final Object lock, final MemoryMode memoryMode) {
      super(lock);
      this.lock = lock;
      Logging.$init$(this);
      String var10001;
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         var10001 = "on-heap execution";
      } else {
         if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
            throw new MatchError(memoryMode);
         }

         var10001 = "off-heap execution";
      }

      this.poolName = var10001;
      this.memoryForTask = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
