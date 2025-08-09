package org.apache.spark.streaming.receiver;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.streaming.util.WriteAheadLog;
import org.apache.spark.streaming.util.WriteAheadLogRecordHandle;
import org.apache.spark.streaming.util.WriteAheadLogUtils$;
import org.apache.spark.util.Clock;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import scala.concurrent.duration.package;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e!\u0002\u000e\u001c\u0001u)\u0003\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d\t\u0011y\u0002!\u0011!Q\u0001\n}B\u0001\"\u0012\u0001\u0003\u0002\u0003\u0006IA\u0012\u0005\t\u0013\u0002\u0011\t\u0011)A\u0005\u0015\"AQ\n\u0001B\u0001B\u0003%a\n\u0003\u0005S\u0001\t\u0005\t\u0015!\u0003T\u0011!Q\u0006A!A!\u0002\u0013Y\u0006\u0002\u00034\u0001\u0005\u0003\u0005\u000b\u0011B4\t\u000b5\u0004A\u0011\u00018\t\u000fa\u0004!\u0019!C\u0005s\"9\u0011Q\u0001\u0001!\u0002\u0013Q\b\"CA\u0004\u0001\t\u0007I\u0011BA\u0005\u0011\u001d\tY\u0001\u0001Q\u0001\n)C\u0011\"!\u0004\u0001\u0005\u0004%I!a\u0004\t\u0011\u0005m\u0001\u0001)A\u0005\u0003#A\u0011\"!\b\u0001\u0005\u0004%Y!a\b\t\u0011\u0005%\u0002\u0001)A\u0005\u0003CAq!a\u000b\u0001\t\u0003\ti\u0003C\u0004\u0002J\u0001!\t!a\u0013\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`\u001dA\u0011\u0011M\u000e\t\u0002u\t\u0019GB\u0004\u001b7!\u0005Q$!\u001a\t\r54B\u0011AA4\u0011\u001d\tIG\u0006C\u0001\u0003WB\u0011\"!\u001d\u0017#\u0003%\t!a\u001d\u0003=]\u0013\u0018\u000e^3BQ\u0016\fG\rT8h\u0005\u0006\u001cX\r\u001a\"m_\u000e\\\u0007*\u00198eY\u0016\u0014(B\u0001\u000f\u001e\u0003!\u0011XmY3jm\u0016\u0014(B\u0001\u0010 \u0003%\u0019HO]3b[&twM\u0003\u0002!C\u0005)1\u000f]1sW*\u0011!eI\u0001\u0007CB\f7\r[3\u000b\u0003\u0011\n1a\u001c:h'\u0011\u0001a\u0005\f\u0019\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0003%\nQa]2bY\u0006L!a\u000b\u0015\u0003\r\u0005s\u0017PU3g!\tic&D\u0001\u001c\u0013\ty3D\u0001\u000bSK\u000e,\u0017N^3e\u00052|7m\u001b%b]\u0012dWM\u001d\t\u0003cQj\u0011A\r\u0006\u0003g}\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003kI\u0012q\u0001T8hO&tw-\u0001\u0007cY>\u001c7.T1oC\u001e,'o\u0001\u0001\u0011\u0005ebT\"\u0001\u001e\u000b\u0005mz\u0012aB:u_J\fw-Z\u0005\u0003{i\u0012AB\u00117pG.l\u0015M\\1hKJ\f\u0011c]3sS\u0006d\u0017N_3s\u001b\u0006t\u0017mZ3s!\t\u00015)D\u0001B\u0015\t\u0011u$\u0001\u0006tKJL\u0017\r\\5{KJL!\u0001R!\u0003#M+'/[1mSj,'/T1oC\u001e,'/\u0001\u0005tiJ,\u0017-\\%e!\t9s)\u0003\u0002IQ\t\u0019\u0011J\u001c;\u0002\u0019M$xN]1hK2+g/\u001a7\u0011\u0005eZ\u0015B\u0001';\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0003\u0011\u0019wN\u001c4\u0011\u0005=\u0003V\"A\u0010\n\u0005E{\"!C*qCJ\\7i\u001c8g\u0003)A\u0017\rZ8pa\u000e{gN\u001a\t\u0003)bk\u0011!\u0016\u0006\u0003\u001bZS!aV\u0011\u0002\r!\fGm\\8q\u0013\tIVKA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u000eG\",7m\u001b9pS:$H)\u001b:\u0011\u0005q\u001bgBA/b!\tq\u0006&D\u0001`\u0015\t\u0001w'\u0001\u0004=e>|GOP\u0005\u0003E\"\na\u0001\u0015:fI\u00164\u0017B\u00013f\u0005\u0019\u0019FO]5oO*\u0011!\rK\u0001\u0006G2|7m\u001b\t\u0003Q.l\u0011!\u001b\u0006\u0003U~\tA!\u001e;jY&\u0011A.\u001b\u0002\u0006\u00072|7m[\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0013=\u0004\u0018O]:ukZ<\bCA\u0017\u0001\u0011\u00151\u0014\u00021\u00019\u0011\u0015q\u0014\u00021\u0001@\u0011\u0015)\u0015\u00021\u0001G\u0011\u0015I\u0015\u00021\u0001K\u0011\u0015i\u0015\u00021\u0001O\u0011\u0015\u0011\u0016\u00021\u0001T\u0011\u0015Q\u0016\u00021\u0001\\\u0011\u001d1\u0017\u0002%AA\u0002\u001d\f\u0011C\u00197pG.\u001cFo\u001c:f)&lWm\\;u+\u0005Q\bcA>\u0002\u00025\tAP\u0003\u0002~}\u0006AA-\u001e:bi&|gN\u0003\u0002\u0000Q\u0005Q1m\u001c8dkJ\u0014XM\u001c;\n\u0007\u0005\rAP\u0001\bGS:LG/\u001a#ve\u0006$\u0018n\u001c8\u0002%\tdwnY6Ti>\u0014X\rV5nK>,H\u000fI\u0001\u0016K\u001a4Wm\u0019;jm\u0016\u001cFo\u001c:bO\u0016dUM^3m+\u0005Q\u0015AF3gM\u0016\u001cG/\u001b<f'R|'/Y4f\u0019\u00164X\r\u001c\u0011\u0002\u001b]\u0014\u0018\u000e^3BQ\u0016\fG\rT8h+\t\t\t\u0002\u0005\u0003\u0002\u0014\u0005]QBAA\u000b\u0015\tQW$\u0003\u0003\u0002\u001a\u0005U!!D,sSR,\u0017\t[3bI2{w-\u0001\bxe&$X-\u00115fC\u0012dun\u001a\u0011\u0002!\u0015DXmY;uS>t7i\u001c8uKb$XCAA\u0011!\u0011\t\u0019#!\n\u000e\u0003yL1!a\n\u007f\u0005})\u00050Z2vi&|gnQ8oi\u0016DH/\u0012=fGV$xN]*feZL7-Z\u0001\u0012Kb,7-\u001e;j_:\u001cuN\u001c;fqR\u0004\u0013AC:u_J,'\t\\8dWR1\u0011qFA\u001b\u0003\u007f\u00012!LA\u0019\u0013\r\t\u0019d\u0007\u0002\u0019%\u0016\u001cW-\u001b<fI\ncwnY6Ti>\u0014XMU3tk2$\bbBA\u001c%\u0001\u0007\u0011\u0011H\u0001\bE2|7m[%e!\rI\u00141H\u0005\u0004\u0003{Q$!D*ue\u0016\fWN\u00117pG.LE\rC\u0004\u0002BI\u0001\r!a\u0011\u0002\u000b\tdwnY6\u0011\u00075\n)%C\u0002\u0002Hm\u0011QBU3dK&4X\r\u001a\"m_\u000e\\\u0017\u0001E2mK\u0006tW\u000f](mI\ncwnY6t)\u0011\ti%a\u0015\u0011\u0007\u001d\ny%C\u0002\u0002R!\u0012A!\u00168ji\"9\u0011QK\nA\u0002\u0005]\u0013A\u0003;ie\u0016\u001c\b\u000eV5nKB\u0019q%!\u0017\n\u0007\u0005m\u0003F\u0001\u0003M_:<\u0017\u0001B:u_B$\"!!\u0014\u0002=]\u0013\u0018\u000e^3BQ\u0016\fG\rT8h\u0005\u0006\u001cX\r\u001a\"m_\u000e\\\u0007*\u00198eY\u0016\u0014\bCA\u0017\u0017'\t1b\u0005\u0006\u0002\u0002d\u0005)2\r[3dWB|\u0017N\u001c;ESJ$v\u000eT8h\t&\u0014H#B.\u0002n\u0005=\u0004\"\u0002.\u0019\u0001\u0004Y\u0006\"B#\u0019\u0001\u00041\u0015a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0003(\u0006\u0002\u0002v)\u001aq-a\u001e,\u0005\u0005e\u0004\u0003BA>\u0003\u000bk!!! \u000b\t\u0005}\u0014\u0011Q\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a!)\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003\u000f\u000biHA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\u0004"
)
public class WriteAheadLogBasedBlockHandler implements ReceivedBlockHandler, Logging {
   private final BlockManager blockManager;
   private final SerializerManager serializerManager;
   private final StorageLevel storageLevel;
   private final Clock clock;
   private final FiniteDuration blockStoreTimeout;
   private final StorageLevel effectiveStorageLevel;
   private final WriteAheadLog writeAheadLog;
   private final ExecutionContextExecutorService executionContext;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Clock $lessinit$greater$default$8() {
      return WriteAheadLogBasedBlockHandler$.MODULE$.$lessinit$greater$default$8();
   }

   public static String checkpointDirToLogDir(final String checkpointDir, final int streamId) {
      return WriteAheadLogBasedBlockHandler$.MODULE$.checkpointDirToLogDir(checkpointDir, streamId);
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

   private FiniteDuration blockStoreTimeout() {
      return this.blockStoreTimeout;
   }

   private StorageLevel effectiveStorageLevel() {
      return this.effectiveStorageLevel;
   }

   private WriteAheadLog writeAheadLog() {
      return this.writeAheadLog;
   }

   private ExecutionContextExecutorService executionContext() {
      return this.executionContext;
   }

   public ReceivedBlockStoreResult storeBlock(final StreamBlockId blockId, final ReceivedBlock block) {
      Option numRecords = .MODULE$.empty();
      ChunkedByteBuffer var10000;
      if (block instanceof ArrayBufferBlock var7) {
         ArrayBuffer arrayBuffer = var7.arrayBuffer();
         numRecords = new Some(BoxesRunTime.boxToLong((long)arrayBuffer.size()));
         var10000 = this.serializerManager.dataSerialize(blockId, arrayBuffer.iterator(), scala.reflect.ClassTag..MODULE$.apply(Object.class));
      } else if (block instanceof IteratorBlock var9) {
         Iterator iterator = var9.iterator();
         CountingIterator countIterator = new CountingIterator(iterator);
         ChunkedByteBuffer serializedBlock = this.serializerManager.dataSerialize(blockId, countIterator, scala.reflect.ClassTag..MODULE$.Any());
         numRecords = countIterator.count();
         var10000 = serializedBlock;
      } else {
         if (!(block instanceof ByteBufferBlock)) {
            throw new Exception("Could not push " + blockId + " to block manager, unexpected block type");
         }

         ByteBufferBlock var13 = (ByteBufferBlock)block;
         ByteBuffer byteBuffer = var13.byteBuffer();
         var10000 = new ChunkedByteBuffer(byteBuffer.duplicate());
      }

      ChunkedByteBuffer serializedBlock = var10000;
      Future storeInBlockManagerFuture = scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> {
         boolean putSucceeded = this.blockManager.putBytes(blockId, serializedBlock, this.effectiveStorageLevel(), true, scala.reflect.ClassTag..MODULE$.Nothing());
         if (!putSucceeded) {
            throw new SparkException("Could not store " + blockId + " to block manager with storage level " + this.storageLevel);
         }
      }, this.executionContext());
      Future storeInWriteAheadLogFuture = scala.concurrent.Future..MODULE$.apply(() -> this.writeAheadLog().write(serializedBlock.toByteBuffer(), this.clock.getTimeMillis()), this.executionContext());
      Future combinedFuture = storeInBlockManagerFuture.zip(storeInWriteAheadLogFuture).map((x$1) -> (WriteAheadLogRecordHandle)x$1._2(), this.executionContext());
      WriteAheadLogRecordHandle walRecordHandle = (WriteAheadLogRecordHandle)org.apache.spark.util.ThreadUtils..MODULE$.awaitResult(combinedFuture, this.blockStoreTimeout());
      return new WriteAheadLogBasedStoreResult(blockId, numRecords, walRecordHandle);
   }

   public void cleanupOldBlocks(final long threshTime) {
      this.writeAheadLog().clean(threshTime, false);
   }

   public void stop() {
      this.writeAheadLog().close();
      this.executionContext().shutdown();
   }

   public WriteAheadLogBasedBlockHandler(final BlockManager blockManager, final SerializerManager serializerManager, final int streamId, final StorageLevel storageLevel, final SparkConf conf, final Configuration hadoopConf, final String checkpointDir, final Clock clock) {
      this.blockManager = blockManager;
      this.serializerManager = serializerManager;
      this.storageLevel = storageLevel;
      this.clock = clock;
      Logging.$init$(this);
      this.blockStoreTimeout = (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(conf.getInt("spark.streaming.receiver.blockStoreTimeout", 30)))).seconds();
      if (storageLevel.deserialized()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Storage level serialization "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is not "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STORAGE_LEVEL_DESERIALIZED..MODULE$, BoxesRunTime.boxToBoolean(this.storageLevel.deserialized()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"supported when write ahead log is enabled, change to serialization false"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      if (storageLevel.replication() > 1) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Storage level replication "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is unnecessary when "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STORAGE_LEVEL_REPLICATION..MODULE$, BoxesRunTime.boxToInteger(this.storageLevel.replication()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"write ahead log is enabled, change to replication 1"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      label19: {
         this.effectiveStorageLevel = org.apache.spark.storage.StorageLevel..MODULE$.apply(storageLevel.useDisk(), storageLevel.useMemory(), storageLevel.useOffHeap(), false, 1);
         StorageLevel var9 = this.effectiveStorageLevel();
         if (storageLevel == null) {
            if (var9 == null) {
               break label19;
            }
         } else if (storageLevel.equals(var9)) {
            break label19;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"User defined storage level ", " is changed to "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STORAGE_LEVEL..MODULE$, this.storageLevel)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"effective storage level ", " when "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EFFECTIVE_STORAGE_LEVEL..MODULE$, this.effectiveStorageLevel())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"write ahead log is enabled"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      this.writeAheadLog = WriteAheadLogUtils$.MODULE$.createLogForReceiver(conf, WriteAheadLogBasedBlockHandler$.MODULE$.checkpointDirToLogDir(checkpointDir, streamId), hadoopConf);
      this.executionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(org.apache.spark.util.ThreadUtils..MODULE$.newDaemonFixedThreadPool(2, this.getClass().getSimpleName()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
