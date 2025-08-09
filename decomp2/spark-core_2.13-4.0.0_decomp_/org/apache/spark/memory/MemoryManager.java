package org.apache.spark.memory;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.memory.MemoryStore;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.array.ByteArrayMethods;
import org.apache.spark.unsafe.memory.MemoryAllocator;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055fAB\u0016-\u0003\u0003qC\u0007\u0003\u0005B\u0001\t\u0005\t\u0015!\u0003D\u0011!9\u0005A!A!\u0002\u0013A\u0005\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011\u0002'\t\u0011=\u0003!\u0011!Q\u0001\n1CQ\u0001\u0015\u0001\u0005\u0002ECq\u0001\u0017\u0001C\u0002\u0013E\u0011\f\u0003\u0004^\u0001\u0001\u0006IA\u0017\u0005\bY\u0002\u0011\r\u0011\"\u0005Z\u0011\u0019i\u0007\u0001)A\u00055\"9q\u000e\u0001b\u0001\n#\u0001\bB\u0002;\u0001A\u0003%\u0011\u000fC\u0004w\u0001\t\u0007I\u0011\u00039\t\r]\u0004\u0001\u0015!\u0003r\u0011\u001dI\bA1Q\u0005\u0012iDaa\u001f\u0001!\u0002\u0013a\u0005b\u0002?\u0001\u0005\u0004&\tB\u001f\u0005\u0007{\u0002\u0001\u000b\u0011\u0002'\t\u000by\u0004a\u0011\u0001>\t\u000b}\u0004a\u0011\u0001>\t\u000f\u0005\u0005\u0001\u0001\"\u0002\u0002\u0004!9\u0011Q\u0004\u0001\u0007\u0002\u0005}\u0001bBA!\u0001\u0019\u0005\u00111\t\u0005\t\u0003\u0017\u0002a\u0011\u0001\u0017\u0002N!A\u0011q\u000b\u0001\u0005\u00021\nI\u0006\u0003\u0005\u0002b\u0001!\t\u0001LA2\u0011\u001d\t9\u0007\u0001C\u0001\u0003SBq!a\u001c\u0001\t\u000b\t\t\bC\u0004\u0002t\u0001!)!!\u001e\t\r\u0005m\u0004\u0001\"\u0002{\u0011\u0019\ti\b\u0001C\u0003u\"1\u0011q\u0010\u0001\u0005\u0006iDa!!!\u0001\t\u000bQ\bBBAB\u0001\u0011\u0015!\u0010\u0003\u0004\u0002\u0006\u0002!)A\u001f\u0005\t\u0003\u000f\u0003A\u0011\u0001\u0017\u0002\n\"I\u0011Q\u0012\u0001C\u0002\u0013\u0015\u0011q\u0012\u0005\t\u0003#\u0003\u0001\u0015!\u0004\u0002<!I\u00111\u0013\u0001\t\u0006\u0004%IA\u001f\u0005\t\u0003+\u0003!\u0019!C\u0001u\"9\u0011q\u0013\u0001!\u0002\u0013a\u0005BCAM\u0001\t\u0007IQ\u0001\u0017\u0002\u001c\"A\u00111\u0016\u0001!\u0002\u001b\tiJA\u0007NK6|'/_'b]\u0006<WM\u001d\u0006\u0003[9\na!\\3n_JL(BA\u00181\u0003\u0015\u0019\b/\u0019:l\u0015\t\t$'\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002g\u0005\u0019qN]4\u0014\u0007\u0001)4\b\u0005\u00027s5\tqGC\u00019\u0003\u0015\u00198-\u00197b\u0013\tQtG\u0001\u0004B]f\u0014VM\u001a\t\u0003y}j\u0011!\u0010\u0006\u0003}9\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003\u0001v\u0012q\u0001T8hO&tw-\u0001\u0003d_:47\u0001\u0001\t\u0003\t\u0016k\u0011AL\u0005\u0003\r:\u0012\u0011b\u00159be.\u001cuN\u001c4\u0002\u00119,XnQ8sKN\u0004\"AN%\n\u0005);$aA%oi\u0006\u0019rN\u001c%fCB\u001cFo\u001c:bO\u0016lU-\\8ssB\u0011a'T\u0005\u0003\u001d^\u0012A\u0001T8oO\u0006)rN\u001c%fCB,\u00050Z2vi&|g.T3n_JL\u0018A\u0002\u001fj]&$h\bF\u0003S)V3v\u000b\u0005\u0002T\u00015\tA\u0006C\u0003B\u000b\u0001\u00071\tC\u0003H\u000b\u0001\u0007\u0001\nC\u0003L\u000b\u0001\u0007A\nC\u0003P\u000b\u0001\u0007A*A\fp]\"+\u0017\r]*u_J\fw-Z'f[>\u0014\u0018\u0010U8pYV\t!\f\u0005\u0002T7&\u0011A\f\f\u0002\u0012'R|'/Y4f\u001b\u0016lwN]=Q_>d\u0017\u0001G8o\u0011\u0016\f\u0007o\u0015;pe\u0006<W-T3n_JL\bk\\8mA!\"qaX5k!\t\u0001w-D\u0001b\u0015\t\u00117-\u0001\u0006d_:\u001cWO\u001d:f]RT!\u0001Z3\u0002\u0015\u0005tgn\u001c;bi&|gNC\u0001g\u0003\u0015Q\u0017M^1y\u0013\tA\u0017MA\u0005Hk\u0006\u0014H-\u001a3Cs\u0006)a/\u00197vK\u0006\n1.\u0001\u0003uQ&\u001c\u0018\u0001G8gM\"+\u0017\r]*u_J\fw-Z'f[>\u0014\u0018\u0010U8pY\u0006IrN\u001a4IK\u0006\u00048\u000b^8sC\u001e,W*Z7pef\u0004vn\u001c7!Q\u0011Iq,\u001b6\u00023=t\u0007*Z1q\u000bb,7-\u001e;j_:lU-\\8ssB{w\u000e\\\u000b\u0002cB\u00111K]\u0005\u0003g2\u00121#\u0012=fGV$\u0018n\u001c8NK6|'/\u001f)p_2\f!d\u001c8IK\u0006\u0004X\t_3dkRLwN\\'f[>\u0014\u0018\u0010U8pY\u0002BCaC0jU\u0006QrN\u001a4IK\u0006\u0004X\t_3dkRLwN\\'f[>\u0014\u0018\u0010U8pY\u0006YrN\u001a4IK\u0006\u0004X\t_3dkRLwN\\'f[>\u0014\u0018\u0010U8pY\u0002BC!D0jU\u0006\u0001R.\u0019=PM\u001aDU-\u00199NK6|'/_\u000b\u0002\u0019\u0006\tR.\u0019=PM\u001aDU-\u00199NK6|'/\u001f\u0011\u0002)=4g\rS3baN#xN]1hK6+Wn\u001c:z\u0003UygM\u001a%fCB\u001cFo\u001c:bO\u0016lU-\\8ss\u0002\na#\\1y\u001f:DU-\u00199Ti>\u0014\u0018mZ3NK6|'/_\u0001\u0018[\u0006DxJ\u001a4IK\u0006\u00048\u000b^8sC\u001e,W*Z7pef\fab]3u\u001b\u0016lwN]=Ti>\u0014X\r\u0006\u0003\u0002\u0006\u0005-\u0001c\u0001\u001c\u0002\b%\u0019\u0011\u0011B\u001c\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003\u001b!\u0002\u0019AA\b\u0003\u0015\u0019Ho\u001c:f!\u0011\t\t\"!\u0007\u000e\u0005\u0005M!bA\u0017\u0002\u0016)\u0019\u0011q\u0003\u0018\u0002\u000fM$xN]1hK&!\u00111DA\n\u0005-iU-\\8ssN#xN]3\u0002)\u0005\u001c\u0017/^5sKN#xN]1hK6+Wn\u001c:z)!\t\t#a\n\u00024\u0005]\u0002c\u0001\u001c\u0002$%\u0019\u0011QE\u001c\u0003\u000f\t{w\u000e\\3b]\"9\u0011\u0011F\u000bA\u0002\u0005-\u0012a\u00022m_\u000e\\\u0017\n\u001a\t\u0005\u0003[\ty#\u0004\u0002\u0002\u0016%!\u0011\u0011GA\u000b\u0005\u001d\u0011En\\2l\u0013\u0012Da!!\u000e\u0016\u0001\u0004a\u0015\u0001\u00038v[\nKH/Z:\t\u000f\u0005eR\u00031\u0001\u0002<\u0005QQ.Z7peflu\u000eZ3\u0011\u0007M\u000bi$C\u0002\u0002@1\u0012!\"T3n_JLXj\u001c3f\u0003M\t7-];je\u0016,fN]8mY6+Wn\u001c:z)!\t\t#!\u0012\u0002H\u0005%\u0003bBA\u0015-\u0001\u0007\u00111\u0006\u0005\u0007\u0003k1\u0002\u0019\u0001'\t\u000f\u0005eb\u00031\u0001\u0002<\u00051\u0012mY9vSJ,W\t_3dkRLwN\\'f[>\u0014\u0018\u0010F\u0004M\u0003\u001f\n\t&!\u0016\t\r\u0005Ur\u00031\u0001M\u0011\u0019\t\u0019f\u0006a\u0001\u0019\u0006iA/Y:l\u0003R$X-\u001c9u\u0013\u0012Dq!!\u000f\u0018\u0001\u0004\tY$\u0001\fsK2,\u0017m]3Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:z)!\t)!a\u0017\u0002^\u0005}\u0003BBA\u001b1\u0001\u0007A\n\u0003\u0004\u0002Ta\u0001\r\u0001\u0014\u0005\b\u0003sA\u0002\u0019AA\u001e\u0003\u0001\u0012X\r\\3bg\u0016\fE\u000e\\#yK\u000e,H/[8o\u001b\u0016lwN]=G_J$\u0016m]6\u0015\u00071\u000b)\u0007\u0003\u0004\u0002Te\u0001\r\u0001T\u0001\u0015e\u0016dW-Y:f'R|'/Y4f\u001b\u0016lwN]=\u0015\r\u0005\u0015\u00111NA7\u0011\u0019\t)D\u0007a\u0001\u0019\"9\u0011\u0011\b\u000eA\u0002\u0005m\u0012a\u0006:fY\u0016\f7/Z!mYN#xN]1hK6+Wn\u001c:z)\t\t)!A\nsK2,\u0017m]3V]J|G\u000e\\'f[>\u0014\u0018\u0010\u0006\u0004\u0002\u0006\u0005]\u0014\u0011\u0010\u0005\u0007\u0003ka\u0002\u0019\u0001'\t\u000f\u0005eB\u00041\u0001\u0002<\u0005\u0019R\r_3dkRLwN\\'f[>\u0014\u00180V:fI\u0006\t2\u000f^8sC\u001e,W*Z7pef,6/\u001a3\u00023=t\u0007*Z1q\u000bb,7-\u001e;j_:lU-\\8ssV\u001bX\rZ\u0001\u001b_\u001a4\u0007*Z1q\u000bb,7-\u001e;j_:lU-\\8ssV\u001bX\rZ\u0001\u0018_:DU-\u00199Ti>\u0014\u0018mZ3NK6|'/_+tK\u0012\f\u0001d\u001c4g\u0011\u0016\f\u0007o\u0015;pe\u0006<W-T3n_JLXk]3e\u0003y9W\r^#yK\u000e,H/[8o\u001b\u0016lwN]=Vg\u0006<WMR8s)\u0006\u001c8\u000eF\u0002M\u0003\u0017Ca!a\u0015$\u0001\u0004a\u0015A\u0005;v]\u001e\u001cH/\u001a8NK6|'/_'pI\u0016,\"!a\u000f\u0002'Q,hnZ:uK:lU-\\8ss6{G-\u001a\u0011\u0002)\u0011,g-Y;miB\u000bw-Z*ju\u0016\u0014\u0015\u0010^3t\u00035\u0001\u0018mZ3TSj,')\u001f;fg\u0006q\u0001/Y4f'&TXMQ=uKN\u0004\u0013a\u0006;v]\u001e\u001cH/\u001a8NK6|'/_!mY>\u001c\u0017\r^8s+\t\ti\n\u0005\u0003\u0002 \u0006\u001dVBAAQ\u0015\ri\u00131\u0015\u0006\u0004\u0003Ks\u0013AB;og\u00064W-\u0003\u0003\u0002*\u0006\u0005&aD'f[>\u0014\u00180\u00117m_\u000e\fGo\u001c:\u00021Q,hnZ:uK:lU-\\8ss\u0006cGn\\2bi>\u0014\b\u0005"
)
public abstract class MemoryManager implements Logging {
   private long defaultPageSizeBytes;
   private final int numCores;
   @GuardedBy("this")
   private final StorageMemoryPool onHeapStorageMemoryPool;
   @GuardedBy("this")
   private final StorageMemoryPool offHeapStorageMemoryPool;
   @GuardedBy("this")
   private final ExecutionMemoryPool onHeapExecutionMemoryPool;
   @GuardedBy("this")
   private final ExecutionMemoryPool offHeapExecutionMemoryPool;
   private final long maxOffHeapMemory;
   private final long offHeapStorageMemory;
   private final MemoryMode tungstenMemoryMode;
   private final long pageSizeBytes;
   private final MemoryAllocator tungstenMemoryAllocator;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   public StorageMemoryPool onHeapStorageMemoryPool() {
      return this.onHeapStorageMemoryPool;
   }

   public StorageMemoryPool offHeapStorageMemoryPool() {
      return this.offHeapStorageMemoryPool;
   }

   public ExecutionMemoryPool onHeapExecutionMemoryPool() {
      return this.onHeapExecutionMemoryPool;
   }

   public ExecutionMemoryPool offHeapExecutionMemoryPool() {
      return this.offHeapExecutionMemoryPool;
   }

   public long maxOffHeapMemory() {
      return this.maxOffHeapMemory;
   }

   public long offHeapStorageMemory() {
      return this.offHeapStorageMemory;
   }

   public abstract long maxOnHeapStorageMemory();

   public abstract long maxOffHeapStorageMemory();

   public final synchronized void setMemoryStore(final MemoryStore store) {
      this.onHeapStorageMemoryPool().setMemoryStore(store);
      this.offHeapStorageMemoryPool().setMemoryStore(store);
   }

   public abstract boolean acquireStorageMemory(final BlockId blockId, final long numBytes, final MemoryMode memoryMode);

   public abstract boolean acquireUnrollMemory(final BlockId blockId, final long numBytes, final MemoryMode memoryMode);

   public abstract long acquireExecutionMemory(final long numBytes, final long taskAttemptId, final MemoryMode memoryMode);

   public synchronized void releaseExecutionMemory(final long numBytes, final long taskAttemptId, final MemoryMode memoryMode) {
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         this.onHeapExecutionMemoryPool().releaseMemory(numBytes, taskAttemptId);
         BoxedUnit var8 = BoxedUnit.UNIT;
      } else if (MemoryMode.OFF_HEAP.equals(memoryMode)) {
         this.offHeapExecutionMemoryPool().releaseMemory(numBytes, taskAttemptId);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(memoryMode);
      }
   }

   public synchronized long releaseAllExecutionMemoryForTask(final long taskAttemptId) {
      return this.onHeapExecutionMemoryPool().releaseAllMemoryForTask(taskAttemptId) + this.offHeapExecutionMemoryPool().releaseAllMemoryForTask(taskAttemptId);
   }

   public synchronized void releaseStorageMemory(final long numBytes, final MemoryMode memoryMode) {
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         this.onHeapStorageMemoryPool().releaseMemory(numBytes);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (MemoryMode.OFF_HEAP.equals(memoryMode)) {
         this.offHeapStorageMemoryPool().releaseMemory(numBytes);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(memoryMode);
      }
   }

   public final synchronized void releaseAllStorageMemory() {
      this.onHeapStorageMemoryPool().releaseAllMemory();
      this.offHeapStorageMemoryPool().releaseAllMemory();
   }

   public final synchronized void releaseUnrollMemory(final long numBytes, final MemoryMode memoryMode) {
      this.releaseStorageMemory(numBytes, memoryMode);
   }

   public final synchronized long executionMemoryUsed() {
      return this.onHeapExecutionMemoryPool().memoryUsed() + this.offHeapExecutionMemoryPool().memoryUsed();
   }

   public final synchronized long storageMemoryUsed() {
      return this.onHeapStorageMemoryPool().memoryUsed() + this.offHeapStorageMemoryPool().memoryUsed();
   }

   public final synchronized long onHeapExecutionMemoryUsed() {
      return this.onHeapExecutionMemoryPool().memoryUsed();
   }

   public final synchronized long offHeapExecutionMemoryUsed() {
      return this.offHeapExecutionMemoryPool().memoryUsed();
   }

   public final synchronized long onHeapStorageMemoryUsed() {
      return this.onHeapStorageMemoryPool().memoryUsed();
   }

   public final synchronized long offHeapStorageMemoryUsed() {
      return this.offHeapStorageMemoryPool().memoryUsed();
   }

   public synchronized long getExecutionMemoryUsageForTask(final long taskAttemptId) {
      return this.onHeapExecutionMemoryPool().getMemoryUsageForTask(taskAttemptId) + this.offHeapExecutionMemoryPool().getMemoryUsageForTask(taskAttemptId);
   }

   public final MemoryMode tungstenMemoryMode() {
      return this.tungstenMemoryMode;
   }

   private long defaultPageSizeBytes$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            long minPageSize = 1048576L;
            long maxPageSize = 64L * minPageSize;
            int cores = this.numCores > 0 ? this.numCores : Runtime.getRuntime().availableProcessors();
            int safetyFactor = 16;
            MemoryMode var12 = this.tungstenMemoryMode();
            long var10001;
            if (MemoryMode.ON_HEAP.equals(var12)) {
               var10001 = this.onHeapExecutionMemoryPool().poolSize();
            } else {
               if (!MemoryMode.OFF_HEAP.equals(var12)) {
                  throw new MatchError(var12);
               }

               var10001 = this.offHeapExecutionMemoryPool().poolSize();
            }

            label86: {
               long chosenPageSize;
               label85: {
                  long maxTungstenMemory = var10001;
                  long size = ByteArrayMethods.nextPowerOf2(maxTungstenMemory / (long)cores / (long)safetyFactor);
                  chosenPageSize = .MODULE$.min(maxPageSize, .MODULE$.max(minPageSize, size));
                  if (Utils$.MODULE$.isG1GC()) {
                     MemoryMode var20 = this.tungstenMemoryMode();
                     MemoryMode var17 = MemoryMode.ON_HEAP;
                     if (var20 == null) {
                        if (var17 == null) {
                           break label85;
                        }
                     } else if (var20.equals(var17)) {
                        break label85;
                     }
                  }

                  var10001 = chosenPageSize;
                  break label86;
               }

               var10001 = chosenPageSize - (long)Platform.LONG_ARRAY_OFFSET;
            }

            this.defaultPageSizeBytes = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var19) {
         throw var19;
      }

      return this.defaultPageSizeBytes;
   }

   private long defaultPageSizeBytes() {
      return !this.bitmap$0 ? this.defaultPageSizeBytes$lzycompute() : this.defaultPageSizeBytes;
   }

   public long pageSizeBytes() {
      return this.pageSizeBytes;
   }

   public final MemoryAllocator tungstenMemoryAllocator() {
      return this.tungstenMemoryAllocator;
   }

   public MemoryManager(final SparkConf conf, final int numCores, final long onHeapStorageMemory, final long onHeapExecutionMemory) {
      this.numCores = numCores;
      Logging.$init$(this);
      scala.Predef..MODULE$.require(onHeapExecutionMemory > 0L, () -> "onHeapExecutionMemory must be > 0");
      this.onHeapStorageMemoryPool = new StorageMemoryPool(this, MemoryMode.ON_HEAP);
      this.offHeapStorageMemoryPool = new StorageMemoryPool(this, MemoryMode.OFF_HEAP);
      this.onHeapExecutionMemoryPool = new ExecutionMemoryPool(this, MemoryMode.ON_HEAP);
      this.offHeapExecutionMemoryPool = new ExecutionMemoryPool(this, MemoryMode.OFF_HEAP);
      this.onHeapStorageMemoryPool().incrementPoolSize(onHeapStorageMemory);
      this.onHeapExecutionMemoryPool().incrementPoolSize(onHeapExecutionMemory);
      this.maxOffHeapMemory = BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_SIZE()));
      this.offHeapStorageMemory = (long)((double)this.maxOffHeapMemory() * BoxesRunTime.unboxToDouble(conf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_STORAGE_FRACTION())));
      this.offHeapExecutionMemoryPool().incrementPoolSize(this.maxOffHeapMemory() - this.offHeapStorageMemory());
      this.offHeapStorageMemoryPool().incrementPoolSize(this.offHeapStorageMemory());
      MemoryMode var10001;
      if (BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_ENABLED()))) {
         scala.Predef..MODULE$.require(BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_SIZE())) > 0L, () -> "spark.memory.offHeap.size must be > 0 when spark.memory.offHeap.enabled == true");
         scala.Predef..MODULE$.require(Platform.unaligned(), () -> "No support for unaligned Unsafe. Set spark.memory.offHeap.enabled to false.");
         var10001 = MemoryMode.OFF_HEAP;
      } else {
         var10001 = MemoryMode.ON_HEAP;
      }

      this.tungstenMemoryMode = var10001;
      this.pageSizeBytes = BoxesRunTime.unboxToLong(((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.BUFFER_PAGESIZE())).getOrElse((JFunction0.mcJ.sp)() -> this.defaultPageSizeBytes()));
      MemoryMode var8 = this.tungstenMemoryMode();
      MemoryAllocator var9;
      if (MemoryMode.ON_HEAP.equals(var8)) {
         var9 = MemoryAllocator.HEAP;
      } else {
         if (!MemoryMode.OFF_HEAP.equals(var8)) {
            throw new MatchError(var8);
         }

         var9 = MemoryAllocator.UNSAFE;
      }

      this.tungstenMemoryAllocator = var9;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
