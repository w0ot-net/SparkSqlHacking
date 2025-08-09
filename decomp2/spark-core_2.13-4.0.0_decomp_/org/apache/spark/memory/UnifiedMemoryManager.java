package org.apache.spark.memory;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.MDC;
import org.apache.spark.storage.BlockId;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple3;
import scala.Tuple4;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005Q4Q\u0001F\u000b\u0001/uA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tQ\u0001\u0011)\u0019!C\u0001S!A\u0001\u0007\u0001B\u0001B\u0003%!\u0006\u0003\u00052\u0001\t\u0005\t\u0015!\u0003+\u0011!\u0011\u0004A!A!\u0002\u0013\u0019\u0004\"\u0002\u001c\u0001\t\u00039\u0004\"B\u001f\u0001\t\u0013q\u0004\"\u0002\"\u0001\t\u0003J\u0003\"B\"\u0001\t\u0003J\u0003B\u0002#\u0001\t\u0003*R\tC\u0003P\u0001\u0011\u0005\u0003\u000bC\u0003_\u0001\u0011\u0005slB\u0003d+!\u0005AMB\u0003\u0015+!\u0005Q\rC\u00037\u001d\u0011\u0005\u0011\u000eC\u0004k\u001d\t\u0007I\u0011B6\t\r1t\u0001\u0015!\u00034\u0011\u0015ig\u0002\"\u0001o\u0011\u0015\th\u0002\"\u0003s\u0005Q)f.\u001b4jK\u0012lU-\\8ss6\u000bg.Y4fe*\u0011acF\u0001\u0007[\u0016lwN]=\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e\u001c\"\u0001\u0001\u0010\u0011\u0005}\u0001S\"A\u000b\n\u0005\u0005*\"!D'f[>\u0014\u00180T1oC\u001e,'/\u0001\u0003d_:47\u0001\u0001\t\u0003K\u0019j\u0011aF\u0005\u0003O]\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\u001b5\f\u0007\u0010S3ba6+Wn\u001c:z+\u0005Q\u0003CA\u0016/\u001b\u0005a#\"A\u0017\u0002\u000bM\u001c\u0017\r\\1\n\u0005=b#\u0001\u0002'p]\u001e\fa\"\\1y\u0011\u0016\f\u0007/T3n_JL\b%A\fp]\"+\u0017\r]*u_J\fw-\u001a*fO&|gnU5{K\u0006Aa.^7D_J,7\u000f\u0005\u0002,i%\u0011Q\u0007\f\u0002\u0004\u0013:$\u0018A\u0002\u001fj]&$h\bF\u00039siZD\b\u0005\u0002 \u0001!)!E\u0002a\u0001I!)\u0001F\u0002a\u0001U!)\u0011G\u0002a\u0001U!)!G\u0002a\u0001g\u0005\u0001\u0012m]:feRLeN^1sS\u0006tGo\u001d\u000b\u0002\u007fA\u00111\u0006Q\u0005\u0003\u00032\u0012A!\u00168ji\u00061R.\u0019=P]\"+\u0017\r]*u_J\fw-Z'f[>\u0014\u00180A\fnCb|eM\u001a%fCB\u001cFo\u001c:bO\u0016lU-\\8ss\u00061\u0012mY9vSJ,W\t_3dkRLwN\\'f[>\u0014\u0018\u0010\u0006\u0003+\r\"S\u0005\"B$\u000b\u0001\u0004Q\u0013\u0001\u00038v[\nKH/Z:\t\u000b%S\u0001\u0019\u0001\u0016\u0002\u001bQ\f7o[!ui\u0016l\u0007\u000f^%e\u0011\u0015Y%\u00021\u0001M\u0003)iW-\\8ss6{G-\u001a\t\u0003?5K!AT\u000b\u0003\u00155+Wn\u001c:z\u001b>$W-\u0001\u000bbGF,\u0018N]3Ti>\u0014\u0018mZ3NK6|'/\u001f\u000b\u0005#RcV\f\u0005\u0002,%&\u00111\u000b\f\u0002\b\u0005>|G.Z1o\u0011\u0015)6\u00021\u0001W\u0003\u001d\u0011Gn\\2l\u0013\u0012\u0004\"a\u0016.\u000e\u0003aS!!W\f\u0002\u000fM$xN]1hK&\u00111\f\u0017\u0002\b\u00052|7m[%e\u0011\u001595\u00021\u0001+\u0011\u0015Y5\u00021\u0001M\u0003M\t7-];je\u0016,fN]8mY6+Wn\u001c:z)\u0011\t\u0006-\u00192\t\u000bUc\u0001\u0019\u0001,\t\u000b\u001dc\u0001\u0019\u0001\u0016\t\u000b-c\u0001\u0019\u0001'\u0002)Us\u0017NZ5fI6+Wn\u001c:z\u001b\u0006t\u0017mZ3s!\tybb\u0005\u0002\u000fMB\u00111fZ\u0005\u0003Q2\u0012a!\u00118z%\u00164G#\u00013\u00029I+5+\u0012*W\u000b\u0012{6+W*U\u000b6{V*R'P%f{&)\u0017+F'V\t1'A\u000fS\u000bN+%KV#E?NK6\u000bV#N?6+Uj\u0014*Z?\nKF+R*!\u0003\u0015\t\u0007\u000f\u001d7z)\rAt\u000e\u001d\u0005\u0006EI\u0001\r\u0001\n\u0005\u0006eI\u0001\raM\u0001\rO\u0016$X*\u0019=NK6|'/\u001f\u000b\u0003UMDQAI\nA\u0002\u0011\u0002"
)
public class UnifiedMemoryManager extends MemoryManager {
   private final long maxHeapMemory;
   private final long onHeapStorageRegionSize;

   public static UnifiedMemoryManager apply(final SparkConf conf, final int numCores) {
      return UnifiedMemoryManager$.MODULE$.apply(conf, numCores);
   }

   public long maxHeapMemory() {
      return this.maxHeapMemory;
   }

   private void assertInvariants() {
      .MODULE$.assert(this.onHeapExecutionMemoryPool().poolSize() + this.onHeapStorageMemoryPool().poolSize() == this.maxHeapMemory());
      .MODULE$.assert(this.offHeapExecutionMemoryPool().poolSize() + this.offHeapStorageMemoryPool().poolSize() == this.maxOffHeapMemory());
   }

   public synchronized long maxOnHeapStorageMemory() {
      return this.maxHeapMemory() - this.onHeapExecutionMemoryPool().memoryUsed();
   }

   public synchronized long maxOffHeapStorageMemory() {
      return this.maxOffHeapMemory() - this.offHeapExecutionMemoryPool().memoryUsed();
   }

   public synchronized long acquireExecutionMemory(final long numBytes, final long taskAttemptId, final MemoryMode memoryMode) {
      this.assertInvariants();
      .MODULE$.assert(numBytes >= 0L);
      Tuple4 var10000;
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         var10000 = new Tuple4(this.onHeapExecutionMemoryPool(), this.onHeapStorageMemoryPool(), BoxesRunTime.boxToLong(this.onHeapStorageRegionSize), BoxesRunTime.boxToLong(this.maxHeapMemory()));
      } else {
         if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
            throw new MatchError(memoryMode);
         }

         var10000 = new Tuple4(this.offHeapExecutionMemoryPool(), this.offHeapStorageMemoryPool(), BoxesRunTime.boxToLong(this.offHeapStorageMemory()), BoxesRunTime.boxToLong(this.maxOffHeapMemory()));
      }

      Tuple4 var9 = var10000;
      if (var9 != null) {
         ExecutionMemoryPool executionPool = (ExecutionMemoryPool)var9._1();
         StorageMemoryPool storagePool = (StorageMemoryPool)var9._2();
         long storageRegionSize = BoxesRunTime.unboxToLong(var9._3());
         long maxMemory = BoxesRunTime.unboxToLong(var9._4());
         Tuple4 var8 = new Tuple4(executionPool, storagePool, BoxesRunTime.boxToLong(storageRegionSize), BoxesRunTime.boxToLong(maxMemory));
         ExecutionMemoryPool executionPool = (ExecutionMemoryPool)var8._1();
         StorageMemoryPool storagePool = (StorageMemoryPool)var8._2();
         long storageRegionSize = BoxesRunTime.unboxToLong(var8._3());
         long maxMemory = BoxesRunTime.unboxToLong(var8._4());
         return executionPool.acquireMemory(numBytes, taskAttemptId, (JFunction1.mcVJ.sp)(extraMemoryNeeded) -> maybeGrowExecutionPool$1(extraMemoryNeeded, storagePool, storageRegionSize, executionPool), (JFunction0.mcJ.sp)() -> computeMaxExecutionPoolSize$1(maxMemory, storagePool, storageRegionSize));
      } else {
         throw new MatchError(var9);
      }
   }

   public synchronized boolean acquireStorageMemory(final BlockId blockId, final long numBytes, final MemoryMode memoryMode) {
      this.assertInvariants();
      .MODULE$.assert(numBytes >= 0L);
      Tuple3 var10000;
      if (MemoryMode.ON_HEAP.equals(memoryMode)) {
         var10000 = new Tuple3(this.onHeapExecutionMemoryPool(), this.onHeapStorageMemoryPool(), BoxesRunTime.boxToLong(this.maxOnHeapStorageMemory()));
      } else {
         if (!MemoryMode.OFF_HEAP.equals(memoryMode)) {
            throw new MatchError(memoryMode);
         }

         var10000 = new Tuple3(this.offHeapExecutionMemoryPool(), this.offHeapStorageMemoryPool(), BoxesRunTime.boxToLong(this.maxOffHeapStorageMemory()));
      }

      Tuple3 var8 = var10000;
      if (var8 != null) {
         ExecutionMemoryPool executionPool = (ExecutionMemoryPool)var8._1();
         StorageMemoryPool storagePool = (StorageMemoryPool)var8._2();
         long maxMemory = BoxesRunTime.unboxToLong(var8._3());
         Tuple3 var7 = new Tuple3(executionPool, storagePool, BoxesRunTime.boxToLong(maxMemory));
         ExecutionMemoryPool executionPool = (ExecutionMemoryPool)var7._1();
         StorageMemoryPool storagePool = (StorageMemoryPool)var7._2();
         long maxMemory = BoxesRunTime.unboxToLong(var7._3());
         if (numBytes > maxMemory) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Will not store ", " as the required space"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (", " bytes) exceeds our"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToLong(numBytes))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" memory limit (", " bytes)"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_MAX..MODULE$, BoxesRunTime.boxToLong(maxMemory))}))))));
            return false;
         } else {
            if (numBytes > storagePool.memoryFree()) {
               long memoryBorrowedFromExecution = Math.min(executionPool.memoryFree(), numBytes - storagePool.memoryFree());
               executionPool.decrementPoolSize(memoryBorrowedFromExecution);
               storagePool.incrementPoolSize(memoryBorrowedFromExecution);
            }

            return storagePool.acquireMemory(blockId, numBytes);
         }
      } else {
         throw new MatchError(var8);
      }
   }

   public synchronized boolean acquireUnrollMemory(final BlockId blockId, final long numBytes, final MemoryMode memoryMode) {
      return this.acquireStorageMemory(blockId, numBytes, memoryMode);
   }

   private static final void maybeGrowExecutionPool$1(final long extraMemoryNeeded, final StorageMemoryPool storagePool$1, final long storageRegionSize$1, final ExecutionMemoryPool executionPool$1) {
      if (extraMemoryNeeded > 0L) {
         long memoryReclaimableFromStorage = scala.math.package..MODULE$.max(storagePool$1.memoryFree(), storagePool$1.poolSize() - storageRegionSize$1);
         if (memoryReclaimableFromStorage > 0L) {
            long spaceToReclaim = storagePool$1.freeSpaceToShrinkPool(scala.math.package..MODULE$.min(extraMemoryNeeded, memoryReclaimableFromStorage));
            storagePool$1.decrementPoolSize(spaceToReclaim);
            executionPool$1.incrementPoolSize(spaceToReclaim);
         }
      }
   }

   private static final long computeMaxExecutionPoolSize$1(final long maxMemory$1, final StorageMemoryPool storagePool$1, final long storageRegionSize$1) {
      return maxMemory$1 - scala.math.package..MODULE$.min(storagePool$1.memoryUsed(), storageRegionSize$1);
   }

   public UnifiedMemoryManager(final SparkConf conf, final long maxHeapMemory, final long onHeapStorageRegionSize, final int numCores) {
      super(conf, numCores, onHeapStorageRegionSize, maxHeapMemory - onHeapStorageRegionSize);
      this.maxHeapMemory = maxHeapMemory;
      this.onHeapStorageRegionSize = onHeapStorageRegionSize;
      this.assertInvariants();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
