package org.apache.spark.memory;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkException.;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.memory.MemoryStore;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma!B\t\u0013\u0001IQ\u0002\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011B\u0014\t\u0011=\u0002!\u0011!Q\u0001\nABQa\r\u0001\u0005\u0002QBa\u0001\u000f\u0001!\u0002\u0013I\u0004B\u0002$\u0001A\u0003&q\tC\u0003Y\u0001\u0011\u0005\u0013\fC\u0005[\u0001\u0001\u0007\t\u0019!C\u00057\"I1\r\u0001a\u0001\u0002\u0004%I\u0001\u001a\u0005\nU\u0002\u0001\r\u0011!Q!\nqCQa\u001b\u0001\u0005\u0002mCQ\u0001\u001c\u0001\u0005\u00065DQ\u0001\u001d\u0001\u0005\u0002EDQ\u0001\u001d\u0001\u0005\u0002uDq!a\u0002\u0001\t\u0003\tI\u0001C\u0004\u0002\u0010\u0001!\t!!\u0005\t\u000f\u0005M\u0001\u0001\"\u0001\u0002\u0016\t\t2\u000b^8sC\u001e,W*Z7pef\u0004vn\u001c7\u000b\u0005M!\u0012AB7f[>\u0014\u0018P\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h'\r\u00011d\b\t\u00039ui\u0011AE\u0005\u0003=I\u0011!\"T3n_JL\bk\\8m!\t\u00013%D\u0001\"\u0015\t\u0011C#\u0001\u0005j]R,'O\\1m\u0013\t!\u0013EA\u0004M_\u001e<\u0017N\\4\u0002\t1|7m[\u0002\u0001!\tAS&D\u0001*\u0015\tQ3&\u0001\u0003mC:<'\"\u0001\u0017\u0002\t)\fg/Y\u0005\u0003]%\u0012aa\u00142kK\u000e$\u0018AC7f[>\u0014\u00180T8eKB\u0011A$M\u0005\u0003eI\u0011!\"T3n_JLXj\u001c3f\u0003\u0019a\u0014N\\5u}Q\u0019QGN\u001c\u0011\u0005q\u0001\u0001\"B\u0013\u0004\u0001\u00049\u0003\"B\u0018\u0004\u0001\u0004\u0001\u0014\u0001\u00039p_2t\u0015-\\3\u0011\u0005i\u001aeBA\u001eB!\tat(D\u0001>\u0015\tqd%\u0001\u0004=e>|GO\u0010\u0006\u0002\u0001\u0006)1oY1mC&\u0011!iP\u0001\u0007!J,G-\u001a4\n\u0005\u0011+%AB*ue&twM\u0003\u0002C\u007f\u0005Yq,\\3n_JLXk]3e!\tA\u0015*D\u0001@\u0013\tQuH\u0001\u0003M_:<\u0007\u0006B\u0003M-^\u0003\"!\u0014+\u000e\u00039S!a\u0014)\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002R%\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0003M\u000bQA[1wCbL!!\u0016(\u0003\u0013\u001d+\u0018M\u001d3fI\nK\u0018!\u0002<bYV,\u0017%A\u0013\u0002\u00155,Wn\u001c:z+N,G-F\u0001H\u00031yV.Z7pef\u001cFo\u001c:f+\u0005a\u0006CA/b\u001b\u0005q&BA\n`\u0015\t\u0001G#A\u0004ti>\u0014\u0018mZ3\n\u0005\tt&aC'f[>\u0014\u0018p\u0015;pe\u0016\f\u0001cX7f[>\u0014\u0018p\u0015;pe\u0016|F%Z9\u0015\u0005\u0015D\u0007C\u0001%g\u0013\t9wH\u0001\u0003V]&$\bbB5\t\u0003\u0003\u0005\r\u0001X\u0001\u0004q\u0012\n\u0014!D0nK6|'/_*u_J,\u0007%A\u0006nK6|'/_*u_J,\u0017AD:fi6+Wn\u001c:z'R|'/\u001a\u000b\u0003K:DQa\\\u0006A\u0002q\u000bQa\u001d;pe\u0016\fQ\"Y2rk&\u0014X-T3n_JLHc\u0001:vwB\u0011\u0001j]\u0005\u0003i~\u0012qAQ8pY\u0016\fg\u000eC\u0003w\u0019\u0001\u0007q/A\u0004cY>\u001c7.\u00133\u0011\u0005aLX\"A0\n\u0005i|&a\u0002\"m_\u000e\\\u0017\n\u001a\u0005\u0006y2\u0001\raR\u0001\t]Vl')\u001f;fgR)!O`@\u0002\u0004!)a/\u0004a\u0001o\"1\u0011\u0011A\u0007A\u0002\u001d\u000b\u0011C\\;n\u0005f$Xm\u001d+p\u0003\u000e\fX/\u001b:f\u0011\u0019\t)!\u0004a\u0001\u000f\u0006qa.^7CsR,7\u000fV8Ge\u0016,\u0017!\u0004:fY\u0016\f7/Z'f[>\u0014\u0018\u0010F\u0002f\u0003\u0017Aa!!\u0004\u000f\u0001\u00049\u0015\u0001B:ju\u0016\f\u0001C]3mK\u0006\u001cX-\u00117m\u001b\u0016lwN]=\u0015\u0003\u0015\fQC\u001a:fKN\u0003\u0018mY3U_NC'/\u001b8l!>|G\u000eF\u0002H\u0003/Aa!!\u0007\u0011\u0001\u00049\u0015aC:qC\u000e,Gk\u001c$sK\u0016\u0004"
)
public class StorageMemoryPool extends MemoryPool implements Logging {
   private final Object lock;
   private final MemoryMode memoryMode;
   private final String poolName;
   @GuardedBy("lock")
   private long _memoryUsed;
   private MemoryStore _memoryStore;
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

   public long memoryUsed() {
      synchronized(this.lock){}

      long var2;
      try {
         var2 = this._memoryUsed;
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   private MemoryStore _memoryStore() {
      return this._memoryStore;
   }

   private void _memoryStore_$eq(final MemoryStore x$1) {
      this._memoryStore = x$1;
   }

   public MemoryStore memoryStore() {
      if (this._memoryStore() == null) {
         throw .MODULE$.internalError("memory store not initialized yet", "MEMORY");
      } else {
         return this._memoryStore();
      }
   }

   public final void setMemoryStore(final MemoryStore store) {
      this._memoryStore_$eq(store);
   }

   public boolean acquireMemory(final BlockId blockId, final long numBytes) {
      synchronized(this.lock){}

      boolean var5;
      try {
         long numBytesToFree = scala.math.package..MODULE$.max(0L, numBytes - this.memoryFree());
         var5 = this.acquireMemory(blockId, numBytes, numBytesToFree);
      } catch (Throwable var9) {
         throw var9;
      }

      return var5;
   }

   public boolean acquireMemory(final BlockId blockId, final long numBytesToAcquire, final long numBytesToFree) {
      synchronized(this.lock){}

      boolean var7;
      try {
         scala.Predef..MODULE$.assert(numBytesToAcquire >= 0L);
         scala.Predef..MODULE$.assert(numBytesToFree >= 0L);
         scala.Predef..MODULE$.assert(this.memoryUsed() <= this.poolSize());
         if (numBytesToFree > 0L) {
            BoxesRunTime.boxToLong(this.memoryStore().evictBlocksToFreeSpace(new Some(blockId), numBytesToFree, this.memoryMode));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         boolean enoughMemory = numBytesToAcquire <= this.memoryFree();
         if (enoughMemory) {
            this._memoryUsed += numBytesToAcquire;
         }

         var7 = enoughMemory;
      } catch (Throwable var10) {
         throw var10;
      }

      return var7;
   }

   public void releaseMemory(final long size) {
      synchronized(this.lock){}

      try {
         if (size > this._memoryUsed) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to release ", " bytes of storage "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(size))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"memory when we only have ", " bytes"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_USED..MODULE$, BoxesRunTime.boxToLong(this._memoryUsed))}))))));
            this._memoryUsed = 0L;
         } else {
            this._memoryUsed -= size;
         }
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public void releaseAllMemory() {
      synchronized(this.lock){}

      try {
         this._memoryUsed = 0L;
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public long freeSpaceToShrinkPool(final long spaceToFree) {
      synchronized(this.lock){}

      long var4;
      try {
         long spaceFreedByReleasingUnusedMemory = scala.math.package..MODULE$.min(spaceToFree, this.memoryFree());
         long remainingSpaceToFree = spaceToFree - spaceFreedByReleasingUnusedMemory;
         long var10000;
         if (remainingSpaceToFree > 0L) {
            long spaceFreedByEviction = this.memoryStore().evictBlocksToFreeSpace(scala.None..MODULE$, remainingSpaceToFree, this.memoryMode);
            var10000 = spaceFreedByReleasingUnusedMemory + spaceFreedByEviction;
         } else {
            var10000 = spaceFreedByReleasingUnusedMemory;
         }

         var4 = var10000;
      } catch (Throwable var13) {
         throw var13;
      }

      return var4;
   }

   public StorageMemoryPool(final Object lock, final MemoryMode memoryMode) {
      super(lock);
      this.lock = lock;
      this.memoryMode = memoryMode;
      Logging.$init$(this);
      String var10001;
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         var10001 = "on-heap storage";
      } else {
         if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
            throw new MatchError(memoryMode);
         }

         var10001 = "off-heap storage";
      }

      this.poolName = var10001;
      this._memoryUsed = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
