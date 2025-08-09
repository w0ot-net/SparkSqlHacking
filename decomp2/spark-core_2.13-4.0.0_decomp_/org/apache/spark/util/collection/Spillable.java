package org.apache.spark.util.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.memory.MemoryConsumer;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.memory.TaskMemoryManager;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]4aAE\n\u0002\u0002]i\u0002\u0002C\u0016\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u000bA\u0002A\u0011A\u0019\t\u000b\r\u0003a\u0011\u0003#\t\u000b%\u0003a\u0011\u0003&\t\u000b9\u0003A\u0011C(\t\u000bM\u0003A\u0011\u0003+\t\rU\u0003\u0001\u0015!\u0003W\u0011\u0019I\u0006\u0001)A\u0005!\"1!\f\u0001Q!\nYCaa\u0018\u0001!B\u0013\u0001\u0006B\u00021\u0001A\u0003&a\u000b\u0003\u0007c\u0001\u0011\u0005\tQ!A\u0001B\u0003&\u0001\u000bC\u0003d\u0001\u0011EA\rC\u0003D\u0001\u0011\u0005\u0003\u000eC\u0003n\u0001\u0011\u0005a\u000eC\u0003p\u0001\u0011\u0005A\u000bC\u0003q\u0001\u0011%\u0011OA\u0005Ta&dG.\u00192mK*\u0011A#F\u0001\u000bG>dG.Z2uS>t'B\u0001\f\u0018\u0003\u0011)H/\u001b7\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e,\"A\b\u001c\u0014\u0007\u0001yR\u0005\u0005\u0002!G5\t\u0011E\u0003\u0002#/\u00051Q.Z7pefL!\u0001J\u0011\u0003\u001d5+Wn\u001c:z\u0007>t7/^7feB\u0011a%K\u0007\u0002O)\u0011\u0001fF\u0001\tS:$XM\u001d8bY&\u0011!f\n\u0002\b\u0019><w-\u001b8h\u0003E!\u0018m]6NK6|'/_'b]\u0006<WM]\u0002\u0001!\t\u0001c&\u0003\u00020C\t\tB+Y:l\u001b\u0016lwN]=NC:\fw-\u001a:\u0002\rqJg.\u001b;?)\t\u0011$\tE\u00024\u0001Qj\u0011a\u0005\t\u0003kYb\u0001\u0001B\u00038\u0001\t\u0007\u0001HA\u0001D#\tIt\b\u0005\u0002;{5\t1HC\u0001=\u0003\u0015\u00198-\u00197b\u0013\tq4HA\u0004O_RD\u0017N\\4\u0011\u0005i\u0002\u0015BA!<\u0005\r\te.\u001f\u0005\u0006W\t\u0001\r!L\u0001\u0006gBLG\u000e\u001c\u000b\u0003\u000b\"\u0003\"A\u000f$\n\u0005\u001d[$\u0001B+oSRDQ\u0001F\u0002A\u0002Q\n!BZ8sG\u0016\u001c\u0006/\u001b7m)\u0005Y\u0005C\u0001\u001eM\u0013\ti5HA\u0004C_>dW-\u00198\u0002\u0019\u0015dW-\\3oiN\u0014V-\u00193\u0016\u0003A\u0003\"AO)\n\u0005I[$aA%oi\u0006y\u0011\r\u001a3FY\u0016lWM\u001c;t%\u0016\fG\rF\u0001F\u0003YIg.\u001b;jC2lU-\\8ssRC'/Z:i_2$\u0007C\u0001\u001eX\u0013\tA6H\u0001\u0003M_:<\u0017A\b8v[\u0016cW-\\3oiN4uN]2f'BLG\u000e\u001c+ie\u0016\u001c\bn\u001c7e\u0003Ei\u00170T3n_JLH\u000b\u001b:fg\"|G\u000e\u001a\u0015\u0003\u0013q\u0003\"AO/\n\u0005y[$\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u001b}+G.Z7f]R\u001c(+Z1e\u0003MyV.Z7pef\u0014\u0015\u0010^3t'BLG\u000e\\3eQ\tYA,A\u001cpe\u001e$\u0013\r]1dQ\u0016$3\u000f]1sW\u0012*H/\u001b7%G>dG.Z2uS>tGe\u00159jY2\f'\r\\3%I}\u001b\b/\u001b7m\u0007>,h\u000e^\u0001\u000b[\u0006L(-Z*qS2dGcA&fM\")A#\u0004a\u0001i!)q-\u0004a\u0001-\u0006i1-\u001e:sK:$X*Z7pef$2AV5l\u0011\u0015Qg\u00021\u0001W\u0003\u0011\u0019\u0018N_3\t\u000b1t\u0001\u0019A\u0010\u0002\u000fQ\u0014\u0018nZ4fe\u0006\u0011R.Z7pef\u0014\u0015\u0010^3t'BLG\u000e\\3e+\u00051\u0016!\u0004:fY\u0016\f7/Z'f[>\u0014\u00180A\u0006m_\u001e\u001c\u0006/\u001b7mC\u001e,GCA#s\u0011\u0015Q\u0017\u00031\u0001WQ\t\tB\u000f\u0005\u0002;k&\u0011ao\u000f\u0002\u0007S:d\u0017N\\3"
)
public abstract class Spillable extends MemoryConsumer implements Logging {
   private final TaskMemoryManager taskMemoryManager;
   private final long initialMemoryThreshold;
   private final int numElementsForceSpillThreshold;
   private volatile long myMemoryThreshold;
   private int _elementsRead;
   private volatile long _memoryBytesSpilled;
   public int org$apache$spark$util$collection$Spillable$$_spillCount;
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

   public abstract void spill(final Object collection);

   public abstract boolean forceSpill();

   public int elementsRead() {
      return this._elementsRead;
   }

   public void addElementsRead() {
      ++this._elementsRead;
   }

   public boolean maybeSpill(final Object collection, final long currentMemory) {
      boolean shouldSpill = false;
      if (this.elementsRead() % 32 == 0 && currentMemory >= this.myMemoryThreshold) {
         long amountToRequest = 2L * currentMemory - this.myMemoryThreshold;
         long granted = this.acquireMemory(amountToRequest);
         this.myMemoryThreshold += granted;
         shouldSpill = currentMemory >= this.myMemoryThreshold;
      }

      shouldSpill = shouldSpill || this._elementsRead > this.numElementsForceSpillThreshold;
      if (shouldSpill) {
         ++this.org$apache$spark$util$collection$Spillable$$_spillCount;
         this.logSpillage(currentMemory);
         this.spill(collection);
         this._elementsRead = 0;
         this._memoryBytesSpilled += currentMemory;
         this.releaseMemory();
      }

      return shouldSpill;
   }

   public long spill(final long size, final MemoryConsumer trigger) {
      if (trigger == null) {
         if (this == null) {
            return 0L;
         }
      } else if (trigger.equals(this)) {
         return 0L;
      }

      MemoryMode var10000 = this.taskMemoryManager.getTungstenMemoryMode();
      MemoryMode var5 = MemoryMode.ON_HEAP;
      if (var10000 == null) {
         if (var5 != null) {
            return 0L;
         }
      } else if (!var10000.equals(var5)) {
         return 0L;
      }

      boolean isSpilled = this.forceSpill();
      if (!isSpilled) {
         return 0L;
      } else {
         long freeMemory = this.myMemoryThreshold - this.initialMemoryThreshold;
         this._memoryBytesSpilled += freeMemory;
         this.releaseMemory();
         return freeMemory;
      }
   }

   public long memoryBytesSpilled() {
      return this._memoryBytesSpilled;
   }

   public void releaseMemory() {
      this.freeMemory(this.myMemoryThreshold - this.initialMemoryThreshold);
      this.myMemoryThreshold = this.initialMemoryThreshold;
   }

   private void logSpillage(final long size) {
      long threadId = Thread.currentThread().getId();
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Thread ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THREAD_ID..MODULE$, BoxesRunTime.boxToLong(threadId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"spilling in-memory map of ", " to disk "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BYTE_SIZE..MODULE$, org.apache.spark.util.Utils$.MODULE$.bytesToString(size))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " times so far)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_SPILLS..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$util$collection$Spillable$$_spillCount))}))))));
   }

   public Spillable(final TaskMemoryManager taskMemoryManager) {
      super(taskMemoryManager, MemoryMode.ON_HEAP);
      this.taskMemoryManager = taskMemoryManager;
      Logging.$init$(this);
      this.initialMemoryThreshold = BoxesRunTime.unboxToLong(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_SPILL_INITIAL_MEM_THRESHOLD()));
      this.numElementsForceSpillThreshold = BoxesRunTime.unboxToInt(SparkEnv$.MODULE$.get().conf().get(package$.MODULE$.SHUFFLE_SPILL_NUM_ELEMENTS_FORCE_SPILL_THRESHOLD()));
      this.myMemoryThreshold = this.initialMemoryThreshold;
      this._elementsRead = 0;
      this._memoryBytesSpilled = 0L;
      this.org$apache$spark$util$collection$Spillable$$_spillCount = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
