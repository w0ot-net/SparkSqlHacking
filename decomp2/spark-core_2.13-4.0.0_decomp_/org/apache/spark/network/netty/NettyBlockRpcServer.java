package org.apache.spark.network.netty;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.network.BlockDataManager;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.client.RpcResponseCallback;
import org.apache.spark.network.client.StreamCallbackWithID;
import org.apache.spark.network.client.TransportClient;
import org.apache.spark.network.server.OneForOneStreamManager;
import org.apache.spark.network.server.RpcHandler;
import org.apache.spark.network.server.StreamManager;
import org.apache.spark.network.shuffle.checksum.Cause;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage;
import org.apache.spark.network.shuffle.protocol.CorruptionCause;
import org.apache.spark.network.shuffle.protocol.DiagnoseCorruption;
import org.apache.spark.network.shuffle.protocol.FetchShuffleBlocks;
import org.apache.spark.network.shuffle.protocol.GetLocalDirsForExecutors;
import org.apache.spark.network.shuffle.protocol.LocalDirsForExecutors;
import org.apache.spark.network.shuffle.protocol.OpenBlocks;
import org.apache.spark.network.shuffle.protocol.StreamHandle;
import org.apache.spark.network.shuffle.protocol.UploadBlock;
import org.apache.spark.network.shuffle.protocol.UploadBlockStream;
import org.apache.spark.network.shuffle.protocol.BlockTransferMessage.Decoder;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockId$;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.ShuffleBlockBatchId;
import org.apache.spark.storage.ShuffleBlockId;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001B\u0006\r\u0001]A\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\te\u0001\u0011\t\u0011)A\u0005g!A\u0001\b\u0001B\u0001B\u0003%\u0011\bC\u0003>\u0001\u0011\u0005a\bC\u0004E\u0001\t\u0007I\u0011B#\t\r%\u0003\u0001\u0015!\u0003G\u0011\u0015Q\u0005\u0001\"\u0011L\u0011\u00151\u0007\u0001\"\u0011h\u0011\u0015y\u0007\u0001\"\u0003q\u0011\u001d\tI\u0003\u0001C!\u0003W\u00111CT3uif\u0014En\\2l%B\u001c7+\u001a:wKJT!!\u0004\b\u0002\u000b9,G\u000f^=\u000b\u0005=\u0001\u0012a\u00028fi^|'o\u001b\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sO\u000e\u00011c\u0001\u0001\u0019=A\u0011\u0011\u0004H\u0007\u00025)\u00111DD\u0001\u0007g\u0016\u0014h/\u001a:\n\u0005uQ\"A\u0003*qG\"\u000bg\u000e\u001a7feB\u0011qDI\u0007\u0002A)\u0011\u0011\u0005E\u0001\tS:$XM\u001d8bY&\u00111\u0005\t\u0002\b\u0019><w-\u001b8h\u0003\u0015\t\u0007\u000f]%e!\t1sF\u0004\u0002([A\u0011\u0001fK\u0007\u0002S)\u0011!FF\u0001\u0007yI|w\u000e\u001e \u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0014G\u0001\u0004TiJLgn\u001a\u0006\u0003]-\n!b]3sS\u0006d\u0017N_3s!\t!d'D\u00016\u0015\t\u0011\u0004#\u0003\u00028k\tQ1+\u001a:jC2L'0\u001a:\u0002\u0019\tdwnY6NC:\fw-\u001a:\u0011\u0005iZT\"\u0001\b\n\u0005qr!\u0001\u0005\"m_\u000e\\G)\u0019;b\u001b\u0006t\u0017mZ3s\u0003\u0019a\u0014N\\5u}Q!q(\u0011\"D!\t\u0001\u0005!D\u0001\r\u0011\u0015!C\u00011\u0001&\u0011\u0015\u0011D\u00011\u00014\u0011\u0015AD\u00011\u0001:\u00035\u0019HO]3b[6\u000bg.Y4feV\ta\t\u0005\u0002\u001a\u000f&\u0011\u0001J\u0007\u0002\u0017\u001f:,gi\u001c:P]\u0016\u001cFO]3b[6\u000bg.Y4fe\u0006q1\u000f\u001e:fC6l\u0015M\\1hKJ\u0004\u0013a\u0002:fG\u0016Lg/\u001a\u000b\u0005\u0019B;\u0016\r\u0005\u0002N\u001d6\t1&\u0003\u0002PW\t!QK\\5u\u0011\u0015\tv\u00011\u0001S\u0003\u0019\u0019G.[3oiB\u00111+V\u0007\u0002)*\u0011\u0011KD\u0005\u0003-R\u0013q\u0002\u0016:b]N\u0004xN\u001d;DY&,g\u000e\u001e\u0005\u00061\u001e\u0001\r!W\u0001\u000beB\u001cW*Z:tC\u001e,\u0007C\u0001.`\u001b\u0005Y&B\u0001/^\u0003\rq\u0017n\u001c\u0006\u0002=\u0006!!.\u0019<b\u0013\t\u00017L\u0001\u0006CsR,')\u001e4gKJDQAY\u0004A\u0002\r\fqB]3ta>t7/Z\"p]R,\u0007\u0010\u001e\t\u0003'\u0012L!!\u001a+\u0003'I\u00038MU3ta>t7/Z\"bY2\u0014\u0017mY6\u0002\u001bI,7-Z5wKN#(/Z1n)\u0011A7\u000e\u001c8\u0011\u0005MK\u0017B\u00016U\u0005Q\u0019FO]3b[\u000e\u000bG\u000e\u001c2bG.<\u0016\u000e\u001e5J\t\")\u0011\u000b\u0003a\u0001%\")Q\u000e\u0003a\u00013\u0006iQ.Z:tC\u001e,\u0007*Z1eKJDQA\u0019\u0005A\u0002\r\f1\u0003Z3tKJL\u0017\r\\5{K6+G/\u00193bi\u0006,2!]A\u0004)\r\u0011\u0018\u0011\u0004\t\u0005\u001bN,80\u0003\u0002uW\t1A+\u001e9mKJ\u0002\"A^=\u000e\u0003]T!\u0001\u001f\t\u0002\u000fM$xN]1hK&\u0011!p\u001e\u0002\r'R|'/Y4f\u0019\u00164X\r\u001c\t\u0005y~\f\u0019!D\u0001~\u0015\tq8&A\u0004sK\u001adWm\u0019;\n\u0007\u0005\u0005QP\u0001\u0005DY\u0006\u001c8\u000fV1h!\u0011\t)!a\u0002\r\u0001\u00119\u0011\u0011B\u0005C\u0002\u0005-!!\u0001+\u0012\t\u00055\u00111\u0003\t\u0004\u001b\u0006=\u0011bAA\tW\t9aj\u001c;iS:<\u0007cA'\u0002\u0016%\u0019\u0011qC\u0016\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002\u001c%\u0001\r!!\b\u0002\u00115,G/\u00193bi\u0006\u0004R!TA\u0010\u0003GI1!!\t,\u0005\u0015\t%O]1z!\ri\u0015QE\u0005\u0004\u0003OY#\u0001\u0002\"zi\u0016\f\u0001cZ3u'R\u0014X-Y7NC:\fw-\u001a:\u0015\u0005\u00055\u0002cA\r\u00020%\u0019\u0011\u0011\u0007\u000e\u0003\u001bM#(/Z1n\u001b\u0006t\u0017mZ3s\u0001"
)
public class NettyBlockRpcServer extends RpcHandler implements Logging {
   private final String appId;
   private final Serializer serializer;
   private final BlockDataManager blockManager;
   private final OneForOneStreamManager streamManager;
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

   private OneForOneStreamManager streamManager() {
      return this.streamManager;
   }

   public void receive(final TransportClient client, final ByteBuffer rpcMessage, final RpcResponseCallback responseContext) {
      BlockTransferMessage var10000;
      try {
         var10000 = Decoder.fromByteBuffer(rpcMessage);
      } catch (Throwable var44) {
         if (var44 instanceof IllegalArgumentException var11) {
            if (var11.getMessage().startsWith("Unknown message type")) {
               this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"This could be a corrupted RPC message (capacity: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_MESSAGE_CAPACITY..MODULE$, BoxesRunTime.boxToInteger(rpcMessage.capacity()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SOCKET_ADDRESS..MODULE$, client.getSocketAddress())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please use `spark.authenticate.*` configurations "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in case of security incidents."})))).log(scala.collection.immutable.Nil..MODULE$))));
               throw var11;
            }
         }

         if (var44 instanceof IndexOutOfBoundsException ? true : var44 instanceof NegativeArraySizeException) {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignored a corrupted RPC message (capacity: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_MESSAGE_CAPACITY..MODULE$, BoxesRunTime.boxToInteger(rpcMessage.capacity()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SOCKET_ADDRESS..MODULE$, client.getSocketAddress())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please use `spark.authenticate.*` configurations "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in case of security incidents."})))).log(scala.collection.immutable.Nil..MODULE$))));
            return;
         }

         throw var44;
      }

      BlockTransferMessage message = var10000;
      this.logTrace((Function0)(() -> "Received request: " + message));
      if (message instanceof OpenBlocks var13) {
         int blocksNum = var13.blockIds.length;
         IndexedSeq blocks = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), blocksNum).map((i) -> $anonfun$receive$4(this, var13, BoxesRunTime.unboxToInt(i)));
         long streamId = this.streamManager().registerStream(this.appId, scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(blocks.iterator()).asJava(), client.getChannel());
         this.logTrace((Function0)(() -> "Registered streamId " + streamId + " with " + blocksNum + " buffers"));
         responseContext.onSuccess((new StreamHandle(streamId, blocksNum)).toByteBuffer());
         BoxedUnit var55 = BoxedUnit.UNIT;
      } else if (message instanceof FetchShuffleBlocks var18) {
         ManagedBuffer[] blocks = (ManagedBuffer[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.longArrayOps(var18.mapIds))), (x0$1) -> {
            if (x0$1 != null) {
               long mapId = x0$1._1$mcJ$sp();
               int index = x0$1._2$mcI$sp();
               if (!var18.batchFetchEnabled) {
                  return (ManagedBuffer[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(var18.reduceIds[index]), (reduceId) -> $anonfun$receive$8(this, var18, mapId, BoxesRunTime.unboxToInt(reduceId)), scala.reflect.ClassTag..MODULE$.apply(ManagedBuffer.class));
               } else {
                  int[] startAndEndId = var18.reduceIds[index];
                  if (startAndEndId.length != 2) {
                     throw org.apache.spark.SparkException..MODULE$.internalError("Invalid shuffle fetch request when batch mode is enabled: " + var18, "NETWORK");
                  } else {
                     return (ManagedBuffer[])((Object[])(new ManagedBuffer[]{this.blockManager.getLocalBlockData(new ShuffleBlockBatchId(var18.shuffleId, mapId, startAndEndId[0], startAndEndId[1]))}));
                  }
               }
            } else {
               throw new MatchError(x0$1);
            }
         }, (xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs), scala.reflect.ClassTag..MODULE$.apply(ManagedBuffer.class));
         int numBlockIds = var18.batchFetchEnabled ? var18.mapIds.length : BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])var18.reduceIds), (x$1) -> BoxesRunTime.boxToInteger($anonfun$receive$10(x$1)), scala.reflect.ClassTag..MODULE$.Int())).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
         long streamId = this.streamManager().registerStream(this.appId, scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps((Object[])blocks))).asJava(), client.getChannel());
         this.logTrace((Function0)(() -> "Registered streamId " + streamId + " with " + numBlockIds + " buffers"));
         responseContext.onSuccess((new StreamHandle(streamId, numBlockIds)).toByteBuffer());
         BoxedUnit var54 = BoxedUnit.UNIT;
      } else if (message instanceof UploadBlock var23) {
         Tuple2 var25 = this.deserializeMetadata(var23.metadata);
         if (var25 != null) {
            StorageLevel level = (StorageLevel)var25._1();
            ClassTag classTag = (ClassTag)var25._2();
            Tuple2 var24 = new Tuple2(level, classTag);
            StorageLevel level = (StorageLevel)var24._1();
            ClassTag classTag = (ClassTag)var24._2();
            NioManagedBuffer data = new NioManagedBuffer(ByteBuffer.wrap(var23.blockData));
            BlockId blockId = BlockId$.MODULE$.apply(var23.blockId);
            this.logDebug((Function0)(() -> "Receiving replicated block " + blockId + " with level " + level + " from " + client.getSocketAddress()));
            boolean blockStored = this.blockManager.putBlockData(blockId, data, level, classTag);
            if (blockStored) {
               responseContext.onSuccess(ByteBuffer.allocate(0));
               BoxedUnit var53 = BoxedUnit.UNIT;
            } else {
               SparkException exception = org.apache.spark.SparkException..MODULE$.internalError("Upload block for " + blockId + " failed. This mostly happens when there is not sufficient space available to store the block.", "NETWORK");
               responseContext.onFailure(exception);
               BoxedUnit var52 = BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(var25);
         }
      } else if (!(message instanceof GetLocalDirsForExecutors var34)) {
         if (message instanceof DiagnoseCorruption var42) {
            Cause cause = this.blockManager.diagnoseShuffleBlockCorruption(new ShuffleBlockId(var42.shuffleId, var42.mapId, var42.reduceId), var42.checksum, var42.algorithm);
            responseContext.onSuccess((new CorruptionCause(cause)).toByteBuffer());
            BoxedUnit var51 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(message);
         }
      } else {
         label110: {
            label109: {
               String var45 = var34.appId;
               String var36 = this.appId;
               if (var45 == null) {
                  if (var36 != null) {
                     break label109;
                  }
               } else if (!var45.equals(var36)) {
                  break label109;
               }

               var46 = false;
               break label110;
            }

            var46 = true;
         }

         boolean isIncorrectAppId = var46;
         int execNum = var34.execIds.length;
         if (!isIncorrectAppId && execNum == 1) {
            String expectedExecId;
            String actualExecId;
            label99: {
               expectedExecId = ((BlockManager)this.blockManager).executorId();
               actualExecId = (String)scala.collection.ArrayOps..MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])var34.execIds));
               if (actualExecId == null) {
                  if (expectedExecId != null) {
                     break label99;
                  }
               } else if (!actualExecId.equals(expectedExecId)) {
                  break label99;
               }

               responseContext.onSuccess((new LocalDirsForExecutors(scala.jdk.CollectionConverters..MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(actualExecId), this.blockManager.getLocalDiskDirs())})))).asJava())).toByteBuffer());
               BoxedUnit var49 = BoxedUnit.UNIT;
               return;
            }

            responseContext.onFailure(org.apache.spark.SparkException..MODULE$.internalError("Invalid executor id: " + actualExecId + ", expected " + expectedExecId + ".", "NETWORK"));
            BoxedUnit var50 = BoxedUnit.UNIT;
         } else {
            Object var47 = isIncorrectAppId ? "incorrect application id: " + var34.appId + ";" : BoxedUnit.UNIT;
            String errorMsg = "Invalid GetLocalDirsForExecutors request: " + var47 + (execNum != 1 ? "incorrect executor number: " + execNum + " (expected 1);" : BoxedUnit.UNIT);
            responseContext.onFailure(org.apache.spark.SparkException..MODULE$.internalError(errorMsg, "NETWORK"));
            BoxedUnit var48 = BoxedUnit.UNIT;
         }
      }
   }

   public StreamCallbackWithID receiveStream(final TransportClient client, final ByteBuffer messageHeader, final RpcResponseCallback responseContext) {
      UploadBlockStream message = (UploadBlockStream)Decoder.fromByteBuffer(messageHeader);
      Tuple2 var7 = this.deserializeMetadata(message.metadata);
      if (var7 != null) {
         StorageLevel level = (StorageLevel)var7._1();
         ClassTag classTag = (ClassTag)var7._2();
         Tuple2 var6 = new Tuple2(level, classTag);
         StorageLevel level = (StorageLevel)var6._1();
         ClassTag classTag = (ClassTag)var6._2();
         BlockId blockId = BlockId$.MODULE$.apply(message.blockId);
         this.logDebug((Function0)(() -> "Receiving replicated block " + blockId + " with level " + level + " as stream from " + client.getSocketAddress()));
         return this.blockManager.putBlockDataAsStream(blockId, level, classTag);
      } else {
         throw new MatchError(var7);
      }
   }

   private Tuple2 deserializeMetadata(final byte[] metadata) {
      return (Tuple2)this.serializer.newInstance().deserialize(ByteBuffer.wrap(metadata), scala.reflect.ClassTag..MODULE$.Nothing());
   }

   public StreamManager getStreamManager() {
      return this.streamManager();
   }

   // $FF: synthetic method
   public static final ManagedBuffer $anonfun$receive$4(final NettyBlockRpcServer $this, final OpenBlocks x2$1, final int i) {
      BlockId blockId = BlockId$.MODULE$.apply(x2$1.blockIds[i]);
      scala.Predef..MODULE$.assert(!(blockId instanceof ShuffleBlockBatchId), () -> "Continuous shuffle block fetching only works for new fetch protocol.");
      return $this.blockManager.getLocalBlockData(blockId);
   }

   // $FF: synthetic method
   public static final ManagedBuffer $anonfun$receive$8(final NettyBlockRpcServer $this, final FetchShuffleBlocks x3$1, final long mapId$1, final int reduceId) {
      return $this.blockManager.getLocalBlockData(new ShuffleBlockId(x3$1.shuffleId, mapId$1, reduceId));
   }

   // $FF: synthetic method
   public static final int $anonfun$receive$10(final int[] x$1) {
      return x$1.length;
   }

   public NettyBlockRpcServer(final String appId, final Serializer serializer, final BlockDataManager blockManager) {
      this.appId = appId;
      this.serializer = serializer;
      this.blockManager = blockManager;
      Logging.$init$(this);
      this.streamManager = new OneForOneStreamManager();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
