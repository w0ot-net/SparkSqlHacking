package org.apache.spark.storage;

import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Uc!\u0002\u0011\"\u0001\rJ\u0003\u0002\u0003\u001c\u0001\u0005\u000b\u0007I\u0011\u0001\u001d\t\u0011u\u0002!\u0011!Q\u0001\neB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0005\u0002\u0011)\u0019!C\u0001\u0007\"AA\t\u0001B\u0001B\u0003%q\b\u0003\u0005F\u0001\t\u0015\r\u0011\"\u0001D\u0011!1\u0005A!A!\u0002\u0013y\u0004\u0002C$\u0001\u0005\u000b\u0007I\u0011\u0001%\t\u0011=\u0003!\u0011!Q\u0001\n%C\u0001\u0002\u0015\u0001\u0003\u0006\u0004%\t!\u0015\u0005\t1\u0002\u0011\t\u0011)A\u0005%\")\u0011\f\u0001C\u00015\"9!\r\u0001b\u0001\n\u0003\u0019\u0005BB2\u0001A\u0003%q\bC\u0004e\u0001\u0001\u0007I\u0011B\"\t\u000f\u0015\u0004\u0001\u0019!C\u0005M\"1A\u000e\u0001Q!\n}Bq!\u001c\u0001A\u0002\u0013%1\tC\u0004o\u0001\u0001\u0007I\u0011B8\t\rE\u0004\u0001\u0015)\u0003@\u0011\u001d\u0011\bA1A\u0005\nMDq!!\u0002\u0001A\u0003%A\u000fC\u0004\u0002\b\u0001!\t!!\u0003\t\u000f\u0005E\u0001\u0001\"\u0001\u0002\u0014!9\u0011Q\u0003\u0001\u0005\u0002\u0005]\u0001bBA\u0017\u0001\u0011\u0005\u0011q\u0006\u0005\u0007\u0003g\u0001A\u0011A\"\t\r\u0005U\u0002\u0001\"\u0001D\u0011\u0019\t9\u0004\u0001C\u0001g\"9\u0011\u0011\b\u0001\u0005B\u0005m\u0002bBA*\u0001\u0011\u0005\u00111\u0003\u0002\u0011\u00052|7m['b]\u0006<WM]%oM>T!AI\u0012\u0002\u000fM$xN]1hK*\u0011A%J\u0001\u0006gB\f'o\u001b\u0006\u0003M\u001d\na!\u00199bG\",'\"\u0001\u0015\u0002\u0007=\u0014xmE\u0002\u0001UA\u0002\"a\u000b\u0018\u000e\u00031R\u0011!L\u0001\u0006g\u000e\fG.Y\u0005\u0003_1\u0012a!\u00118z%\u00164\u0007CA\u00195\u001b\u0005\u0011$BA\u001a$\u0003!Ig\u000e^3s]\u0006d\u0017BA\u001b3\u0005\u001daunZ4j]\u001e\faB\u00197pG.l\u0015M\\1hKJLEm\u0001\u0001\u0016\u0003e\u0002\"AO\u001e\u000e\u0003\u0005J!\u0001P\u0011\u0003\u001d\tcwnY6NC:\fw-\u001a:JI\u0006y!\r\\8dW6\u000bg.Y4fe&#\u0007%\u0001\u0004uS6,Wj\u001d\t\u0003W\u0001K!!\u0011\u0017\u0003\t1{gnZ\u0001\r[\u0006DxJ\u001c%fCBlU-\\\u000b\u0002\u007f\u0005iQ.\u0019=P]\"+\u0017\r]'f[\u0002\nQ\"\\1y\u001f\u001a4\u0007*Z1q\u001b\u0016l\u0017AD7bq>3g\rS3ba6+W\u000eI\u0001\u0010gR|'/Y4f\u000b:$\u0007o\\5oiV\t\u0011\n\u0005\u0002K\u001b6\t1J\u0003\u0002MG\u0005\u0019!\u000f]2\n\u00059[%A\u0004*qG\u0016sG\r]8j]R\u0014VMZ\u0001\u0011gR|'/Y4f\u000b:$\u0007o\\5oi\u0002\n\u0011%\u001a=uKJt\u0017\r\\*ik\u001a4G.Z*feZL7-\u001a\"m_\u000e\\7\u000b^1ukN,\u0012A\u0015\t\u0004WM+\u0016B\u0001+-\u0005\u0019y\u0005\u000f^5p]B\u0011!HV\u0005\u0003/\u0006\u0012QC\u00117pG.\u001cF/\u0019;vgB+'O\u00117pG.LE-\u0001\u0012fqR,'O\\1m'\",hM\u001a7f'\u0016\u0014h/[2f\u00052|7m[*uCR,8\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000fmcVLX0aCB\u0011!\b\u0001\u0005\u0006m1\u0001\r!\u000f\u0005\u0006}1\u0001\ra\u0010\u0005\u0006\u00052\u0001\ra\u0010\u0005\u0006\u000b2\u0001\ra\u0010\u0005\u0006\u000f2\u0001\r!\u0013\u0005\u0006!2\u0001\rAU\u0001\u0007[\u0006DX*Z7\u0002\u000f5\f\u00070T3nA\u0005Yq\f\\1tiN+WM\\'t\u0003=yF.Y:u'\u0016,g.T:`I\u0015\fHCA4k!\tY\u0003.\u0003\u0002jY\t!QK\\5u\u0011\u001dY\u0007#!AA\u0002}\n1\u0001\u001f\u00132\u00031yF.Y:u'\u0016,g.T:!\u00035y&/Z7bS:LgnZ'f[\u0006\trL]3nC&t\u0017N\\4NK6|F%Z9\u0015\u0005\u001d\u0004\bbB6\u0014\u0003\u0003\u0005\raP\u0001\u000f?J,W.Y5oS:<W*Z7!\u0003\u001dy&\r\\8dWN,\u0012\u0001\u001e\t\u0005kjdx0D\u0001w\u0015\t9\b0\u0001\u0003vi&d'\"A=\u0002\t)\fg/Y\u0005\u0003wZ\u0014q\u0001S1tQ6\u000b\u0007\u000f\u0005\u0002;{&\u0011a0\t\u0002\b\u00052|7m[%e!\rQ\u0014\u0011A\u0005\u0004\u0003\u0007\t#a\u0003\"m_\u000e\\7\u000b^1ukN\f\u0001b\u00182m_\u000e\\7\u000fI\u0001\nO\u0016$8\u000b^1ukN$B!a\u0003\u0002\u000eA\u00191fU@\t\r\u0005=q\u00031\u0001}\u0003\u001d\u0011Gn\\2l\u0013\u0012\f\u0001#\u001e9eCR,G*Y:u'\u0016,g.T:\u0015\u0003\u001d\fq\"\u001e9eCR,'\t\\8dW&sgm\u001c\u000b\nO\u0006e\u00111DA\u0013\u0003SAa!a\u0004\u001a\u0001\u0004a\bbBA\u000f3\u0001\u0007\u0011qD\u0001\rgR|'/Y4f\u0019\u00164X\r\u001c\t\u0004u\u0005\u0005\u0012bAA\u0012C\ta1\u000b^8sC\u001e,G*\u001a<fY\"1\u0011qE\rA\u0002}\nq!\\3n'&TX\r\u0003\u0004\u0002,e\u0001\raP\u0001\tI&\u001c8nU5{K\u0006Y!/Z7pm\u0016\u0014En\\2l)\r9\u0017\u0011\u0007\u0005\u0007\u0003\u001fQ\u0002\u0019\u0001?\u0002\u0019I,W.Y5oS:<W*Z7\u0002\u00151\f7\u000f^*fK:l5/\u0001\u0004cY>\u001c7n]\u0001\ti>\u001cFO]5oOR\u0011\u0011Q\b\t\u0005\u0003\u007f\tiE\u0004\u0003\u0002B\u0005%\u0003cAA\"Y5\u0011\u0011Q\t\u0006\u0004\u0003\u000f:\u0014A\u0002\u001fs_>$h(C\u0002\u0002L1\na\u0001\u0015:fI\u00164\u0017\u0002BA(\u0003#\u0012aa\u0015;sS:<'bAA&Y\u0005)1\r\\3be\u0002"
)
public class BlockManagerInfo implements Logging {
   private final BlockManagerId blockManagerId;
   private final long timeMs;
   private final long maxOnHeapMem;
   private final long maxOffHeapMem;
   private final RpcEndpointRef storageEndpoint;
   private final Option externalShuffleServiceBlockStatus;
   private final long maxMem;
   private long _lastSeenMs;
   private long _remainingMem;
   private final HashMap _blocks;
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

   public BlockManagerId blockManagerId() {
      return this.blockManagerId;
   }

   public long maxOnHeapMem() {
      return this.maxOnHeapMem;
   }

   public long maxOffHeapMem() {
      return this.maxOffHeapMem;
   }

   public RpcEndpointRef storageEndpoint() {
      return this.storageEndpoint;
   }

   public Option externalShuffleServiceBlockStatus() {
      return this.externalShuffleServiceBlockStatus;
   }

   public long maxMem() {
      return this.maxMem;
   }

   private long _lastSeenMs() {
      return this._lastSeenMs;
   }

   private void _lastSeenMs_$eq(final long x$1) {
      this._lastSeenMs = x$1;
   }

   private long _remainingMem() {
      return this._remainingMem;
   }

   private void _remainingMem_$eq(final long x$1) {
      this._remainingMem = x$1;
   }

   private HashMap _blocks() {
      return this._blocks;
   }

   public Option getStatus(final BlockId blockId) {
      return .MODULE$.apply(this._blocks().get(blockId));
   }

   public void updateLastSeenMs() {
      this._lastSeenMs_$eq(System.currentTimeMillis());
   }

   public void updateBlockInfo(final BlockId blockId, final StorageLevel storageLevel, final long memSize, final long diskSize) {
      this.updateLastSeenMs();
      boolean blockExists = this._blocks().containsKey(blockId);
      LongRef originalMemSize = LongRef.create(0L);
      LongRef originalDiskSize = LongRef.create(0L);
      StorageLevel originalLevel = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
      if (blockExists) {
         BlockStatus blockStatus = (BlockStatus)this._blocks().get(blockId);
         originalLevel = blockStatus.storageLevel();
         originalMemSize.elem = blockStatus.memSize();
         originalDiskSize.elem = blockStatus.diskSize();
         if (originalLevel.useMemory()) {
            this._remainingMem_$eq(this._remainingMem() + originalMemSize.elem);
         }
      }

      if (storageLevel.isValid()) {
         ObjectRef blockStatus = ObjectRef.create((Object)null);
         if (storageLevel.useMemory()) {
            blockStatus.elem = new BlockStatus(storageLevel, memSize, 0L);
            this._blocks().put(blockId, (BlockStatus)blockStatus.elem);
            this._remainingMem_$eq(this._remainingMem() - memSize);
            if (blockExists) {
               this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updated ", " in memory on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "  (current size: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.blockManagerId().hostPort())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", original "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(memSize))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"size: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ORIGINAL_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(originalMemSize.elem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"free: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FREE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this._remainingMem()))}))))));
            } else {
               this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added ", " in memory on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.blockManagerId().hostPort())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(size: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(memSize))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"free: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FREE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this._remainingMem()))}))))));
            }
         }

         if (storageLevel.useDisk()) {
            blockStatus.elem = new BlockStatus(storageLevel, 0L, diskSize);
            this._blocks().put(blockId, (BlockStatus)blockStatus.elem);
            if (blockExists) {
               this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Updated ", " on disk on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.blockManagerId().hostPort())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(current size: ", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_DISK_SIZE..MODULE$, Utils$.MODULE$.bytesToString(diskSize))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" original size: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ORIGINAL_DISK_SIZE..MODULE$, Utils$.MODULE$.bytesToString(originalDiskSize.elem))}))))));
            } else {
               this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Added ", " on disk on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " (size: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.blockManagerId().hostPort())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CURRENT_DISK_SIZE..MODULE$, Utils$.MODULE$.bytesToString(diskSize))}))))));
            }
         }

         this.externalShuffleServiceBlockStatus().foreach((shuffleServiceBlocks) -> {
            $anonfun$updateBlockInfo$7(blockId, blockStatus, shuffleServiceBlocks);
            return BoxedUnit.UNIT;
         });
      } else if (blockExists) {
         this._blocks().remove(blockId);
         this.externalShuffleServiceBlockStatus().foreach((blockStatusx) -> {
            $anonfun$updateBlockInfo$8(blockId, blockStatusx);
            return BoxedUnit.UNIT;
         });
         if (originalLevel.useMemory()) {
            this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removed ", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " in memory "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.blockManagerId().hostPort())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(size: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ORIGINAL_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(originalMemSize.elem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"free: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FREE_MEMORY_SIZE..MODULE$, Utils$.MODULE$.bytesToString(this._remainingMem()))}))))));
         }

         if (originalLevel.useDisk()) {
            this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removed ", " on "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " on disk"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.blockManagerId().hostPort())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (size: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ORIGINAL_DISK_SIZE..MODULE$, Utils$.MODULE$.bytesToString(originalDiskSize.elem))}))))));
         }
      }
   }

   public void removeBlock(final BlockId blockId) {
      if (this._blocks().containsKey(blockId)) {
         this._remainingMem_$eq(this._remainingMem() + ((BlockStatus)this._blocks().get(blockId)).memSize());
         this._blocks().remove(blockId);
         this.externalShuffleServiceBlockStatus().foreach((blockStatus) -> {
            $anonfun$removeBlock$1(blockId, blockStatus);
            return BoxedUnit.UNIT;
         });
      }
   }

   public long remainingMem() {
      return this._remainingMem();
   }

   public long lastSeenMs() {
      return this._lastSeenMs();
   }

   public HashMap blocks() {
      return this._blocks();
   }

   public String toString() {
      long var10000 = this.timeMs;
      return "BlockManagerInfo " + var10000 + " " + this._remainingMem();
   }

   public void clear() {
      this._blocks().clear();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateBlockInfo$7(final BlockId blockId$9, final ObjectRef blockStatus$1, final BlockStatusPerBlockId shuffleServiceBlocks) {
      if (!blockId$9.isBroadcast() && ((BlockStatus)blockStatus$1.elem).diskSize() > 0L) {
         shuffleServiceBlocks.put(blockId$9, (BlockStatus)blockStatus$1.elem);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$updateBlockInfo$8(final BlockId blockId$9, final BlockStatusPerBlockId blockStatus) {
      blockStatus.remove(blockId$9);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeBlock$1(final BlockId blockId$10, final BlockStatusPerBlockId blockStatus) {
      blockStatus.remove(blockId$10);
   }

   public BlockManagerInfo(final BlockManagerId blockManagerId, final long timeMs, final long maxOnHeapMem, final long maxOffHeapMem, final RpcEndpointRef storageEndpoint, final Option externalShuffleServiceBlockStatus) {
      this.blockManagerId = blockManagerId;
      this.timeMs = timeMs;
      this.maxOnHeapMem = maxOnHeapMem;
      this.maxOffHeapMem = maxOffHeapMem;
      this.storageEndpoint = storageEndpoint;
      this.externalShuffleServiceBlockStatus = externalShuffleServiceBlockStatus;
      Logging.$init$(this);
      this.maxMem = maxOnHeapMem + maxOffHeapMem;
      this._lastSeenMs = timeMs;
      this._remainingMem = this.maxMem();
      this._blocks = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
