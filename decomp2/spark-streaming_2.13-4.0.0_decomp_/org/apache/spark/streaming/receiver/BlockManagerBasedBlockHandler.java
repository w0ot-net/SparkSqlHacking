package org.apache.spark.streaming.receiver;

import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u000513QAB\u0004\u0001\u0013EA\u0001B\t\u0001\u0003\u0002\u0003\u0006I\u0001\n\u0005\tU\u0001\u0011\t\u0011)A\u0005W!)a\u0006\u0001C\u0001_!)1\u0007\u0001C\u0001i!)!\t\u0001C\u0001\u0007\ni\"\t\\8dW6\u000bg.Y4fe\n\u000b7/\u001a3CY>\u001c7\u000eS1oI2,'O\u0003\u0002\t\u0013\u0005A!/Z2fSZ,'O\u0003\u0002\u000b\u0017\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON!\u0001A\u0005\r\u001d!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011\u0011DG\u0007\u0002\u000f%\u00111d\u0002\u0002\u0015%\u0016\u001cW-\u001b<fI\ncwnY6IC:$G.\u001a:\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0005}Y\u0011\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0005r\"a\u0002'pO\u001eLgnZ\u0001\rE2|7m['b]\u0006<WM]\u0002\u0001!\t)\u0003&D\u0001'\u0015\t93\"A\u0004ti>\u0014\u0018mZ3\n\u0005%2#\u0001\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\u0001D:u_J\fw-\u001a'fm\u0016d\u0007CA\u0013-\u0013\ticE\u0001\u0007Ti>\u0014\u0018mZ3MKZ,G.\u0001\u0004=S:LGO\u0010\u000b\u0004aE\u0012\u0004CA\r\u0001\u0011\u0015\u00113\u00011\u0001%\u0011\u0015Q3\u00011\u0001,\u0003)\u0019Ho\u001c:f\u00052|7m\u001b\u000b\u0004kaj\u0004CA\r7\u0013\t9tA\u0001\rSK\u000e,\u0017N^3e\u00052|7m[*u_J,'+Z:vYRDQ!\u000f\u0003A\u0002i\nqA\u00197pG.LE\r\u0005\u0002&w%\u0011AH\n\u0002\u000e'R\u0014X-Y7CY>\u001c7.\u00133\t\u000by\"\u0001\u0019A \u0002\u000b\tdwnY6\u0011\u0005e\u0001\u0015BA!\b\u00055\u0011VmY3jm\u0016$'\t\\8dW\u0006\u00012\r\\3b]V\u0004x\n\u001c3CY>\u001c7n\u001d\u000b\u0003\t\u001e\u0003\"aE#\n\u0005\u0019#\"\u0001B+oSRDQ\u0001S\u0003A\u0002%\u000b!\u0002\u001e5sKNDG+[7f!\t\u0019\"*\u0003\u0002L)\t!Aj\u001c8h\u0001"
)
public class BlockManagerBasedBlockHandler implements ReceivedBlockHandler, Logging {
   private final BlockManager blockManager;
   private final StorageLevel storageLevel;
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

   public ReceivedBlockStoreResult storeBlock(final StreamBlockId blockId, final ReceivedBlock block) {
      Option numRecords = .MODULE$;
      boolean var10000;
      if (block instanceof ArrayBufferBlock var7) {
         ArrayBuffer arrayBuffer = var7.arrayBuffer();
         numRecords = new Some(BoxesRunTime.boxToLong((long)arrayBuffer.size()));
         var10000 = this.blockManager.putIterator(blockId, arrayBuffer.iterator(), this.storageLevel, true, scala.reflect.ClassTag..MODULE$.apply(Object.class));
      } else if (block instanceof IteratorBlock var9) {
         Iterator iterator = var9.iterator();
         CountingIterator countIterator = new CountingIterator(iterator);
         boolean putResult = this.blockManager.putIterator(blockId, countIterator, this.storageLevel, true, scala.reflect.ClassTag..MODULE$.Any());
         numRecords = countIterator.count();
         var10000 = putResult;
      } else {
         if (!(block instanceof ByteBufferBlock)) {
            throw new SparkException("Could not store " + blockId + " to block manager, unexpected block type " + block.getClass().getName());
         }

         ByteBufferBlock var13 = (ByteBufferBlock)block;
         ByteBuffer byteBuffer = var13.byteBuffer();
         var10000 = this.blockManager.putBytes(blockId, new ChunkedByteBuffer(byteBuffer.duplicate()), this.storageLevel, true, scala.reflect.ClassTag..MODULE$.Nothing());
      }

      boolean putSucceeded = var10000;
      if (!putSucceeded) {
         throw new SparkException("Could not store " + blockId + " to block manager with storage level " + this.storageLevel);
      } else {
         return new BlockManagerBasedStoreResult(blockId, numRecords);
      }
   }

   public void cleanupOldBlocks(final long threshTime) {
   }

   public BlockManagerBasedBlockHandler(final BlockManager blockManager, final StorageLevel storageLevel) {
      this.blockManager = blockManager;
      this.storageLevel = storageLevel;
      Logging.$init$(this);
   }
}
