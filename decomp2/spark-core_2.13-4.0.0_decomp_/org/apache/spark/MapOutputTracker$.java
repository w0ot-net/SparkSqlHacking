package org.apache.spark;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.io.CompressionCodec;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MergeStatus;
import org.apache.spark.scheduler.ShuffleOutputStatus;
import org.apache.spark.shuffle.MetadataFetchFailedException;
import org.apache.spark.storage.ShuffleBlockId;
import org.apache.spark.storage.ShuffleMergedBlockId;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import org.roaringbitmap.RoaringBitmap;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class MapOutputTracker$ implements Logging {
   public static final MapOutputTracker$ MODULE$ = new MapOutputTracker$();
   private static final String ENDPOINT_NAME;
   private static final int DIRECT;
   private static final int BROADCAST;
   private static final int SHUFFLE_PUSH_MAP_ID;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      ENDPOINT_NAME = "MapOutputTracker";
      DIRECT = 0;
      BROADCAST = 1;
      SHUFFLE_PUSH_MAP_ID = -1;
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String ENDPOINT_NAME() {
      return ENDPOINT_NAME;
   }

   private int DIRECT() {
      return DIRECT;
   }

   private int BROADCAST() {
      return BROADCAST;
   }

   public int SHUFFLE_PUSH_MAP_ID() {
      return SHUFFLE_PUSH_MAP_ID;
   }

   public Tuple2 serializeOutputStatuses(final ShuffleOutputStatus[] statuses, final BroadcastManager broadcastManager, final boolean isLocal, final int minBroadcastSize, final SparkConf conf) {
      ChunkedByteBufferOutputStream out = new ChunkedByteBufferOutputStream(1048576, (x$1) -> $anonfun$serializeOutputStatuses$1(BoxesRunTime.unboxToInt(x$1)));
      out.write(this.DIRECT());
      CompressionCodec codec = CompressionCodec$.MODULE$.createCodec(conf, (String)conf.get(org.apache.spark.internal.config.package$.MODULE$.MAP_STATUS_COMPRESSION_CODEC()));
      ObjectOutputStream objOut = new ObjectOutputStream(codec.compressedOutputStream(out));
      Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
         synchronized(statuses){}

         try {
            objOut.writeObject(statuses);
         } catch (Throwable var4) {
            throw var4;
         }

      }, (JFunction0.mcV.sp)() -> objOut.close());
      ChunkedByteBuffer chunkedByteBuf = out.toChunkedByteBuffer();
      long arrSize = out.size();
      if (arrSize >= (long)minBroadcastSize) {
         byte[][] arr = (byte[][]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])chunkedByteBuf.getChunks()), (x$26) -> x$26.array(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
         Broadcast bcast = broadcastManager.newBroadcast(arr, isLocal, broadcastManager.newBroadcast$default$3(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE))));
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         out.write(this.BROADCAST());
         ObjectOutputStream oos = new ObjectOutputStream(codec.compressedOutputStream(out));
         Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> oos.writeObject(bcast), (JFunction0.mcV.sp)() -> oos.close());
         byte[] outArr = out.toByteArray();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Broadcast outputstatuses size = "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_OUTPUT_STATUS_SIZE..MODULE$, BoxesRunTime.boxToInteger(outArr.length))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" actual size = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_OUTPUT_STATUS_SIZE..MODULE$, BoxesRunTime.boxToLong(arrSize))}))))));
         return new Tuple2(outArr, bcast);
      } else {
         return new Tuple2(chunkedByteBuf.toArray(), (Object)null);
      }
   }

   public ShuffleOutputStatus[] deserializeOutputStatuses(final byte[] bytes, final SparkConf conf) {
      scala.Predef..MODULE$.assert(bytes.length > 0);
      ByteArrayInputStream in = new ByteArrayInputStream(bytes, 1, bytes.length - 1);
      byte var5 = bytes[0];
      if (this.DIRECT() == var5) {
         return (ShuffleOutputStatus[])deserializeObject$1(in, conf);
      } else if (this.BROADCAST() == var5) {
         try {
            Broadcast bcast = (Broadcast)deserializeObject$1(in, conf);
            long actualSize = BoxesRunTime.unboxToLong(.MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps(bcast.value()), BoxesRunTime.boxToLong(0L), (x$27, x$28) -> BoxesRunTime.boxToLong($anonfun$deserializeOutputStatuses$3(BoxesRunTime.unboxToLong(x$27), x$28))));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Broadcast outputstatuses size ="})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_OUTPUT_STATUS_SIZE..MODULE$, BoxesRunTime.boxToInteger(bytes.length))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{", actual size = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BROADCAST_OUTPUT_STATUS_SIZE..MODULE$, BoxesRunTime.boxToLong(actualSize))}))))));
            ChunkedByteBuffer qual$1 = new ChunkedByteBuffer((ByteBuffer[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(bcast.value()), (x$1x) -> ByteBuffer.wrap(x$1x), scala.reflect.ClassTag..MODULE$.apply(ByteBuffer.class)));
            boolean x$1 = qual$1.toInputStream$default$1();
            InputStream bcastIn = qual$1.toInputStream(x$1);
            bcastIn.skip(1L);
            return (ShuffleOutputStatus[])deserializeObject$1(bcastIn, conf);
         } catch (IOException var13) {
            this.logWarning((Function0)(() -> "Exception encountered during deserializing broadcasted output statuses: "), var13);
            throw new SparkException("Unable to deserialize broadcasted output statuses", var13);
         }
      } else {
         throw new IllegalArgumentException("Unexpected byte tag = " + bytes[0]);
      }
   }

   public MapSizesByExecutorId convertMapStatuses(final int shuffleId, final int startPartition, final int endPartition, final MapStatus[] mapStatuses, final int startMapIndex, final int endMapIndex, final Option mergeStatusesOpt) {
      scala.Predef..MODULE$.assert(mapStatuses != null);
      HashMap splitsByAddress = new HashMap();
      boolean enableBatchFetch = true;
      if (mergeStatusesOpt.exists((x$29) -> BoxesRunTime.boxToBoolean($anonfun$convertMapStatuses$1(x$29))) && startMapIndex == 0 && endMapIndex == mapStatuses.length) {
         enableBatchFetch = false;
         this.logDebug((Function0)(() -> "Disable shuffle batch fetch as Push based shuffle is enabled for " + shuffleId + "."));
         MergeStatus[] mergeStatuses = (MergeStatus[])mergeStatusesOpt.get();
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(startPartition), endPartition).foreach((partId) -> $anonfun$convertMapStatuses$4(mergeStatuses, splitsByAddress, shuffleId, BoxesRunTime.unboxToInt(partId)));
         .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(mapStatuses)).zipWithIndex().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$convertMapStatuses$6(check$ifrefutable$1))).foreach((x$31) -> {
            $anonfun$convertMapStatuses$7(shuffleId, startPartition, endPartition, mergeStatuses, splitsByAddress, x$31);
            return BoxedUnit.UNIT;
         });
      } else {
         Iterator iter = .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(mapStatuses)).zipWithIndex();
         iter.slice(startMapIndex, endMapIndex).withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$convertMapStatuses$10(check$ifrefutable$2))).foreach((x$32) -> {
            $anonfun$convertMapStatuses$11(shuffleId, startPartition, endPartition, splitsByAddress, x$32);
            return BoxedUnit.UNIT;
         });
      }

      return new MapSizesByExecutorId(splitsByAddress.iterator(), enableBatchFetch);
   }

   public Option convertMapStatuses$default$7() {
      return scala.None..MODULE$;
   }

   public Iterator getMapStatusesForMergeStatus(final int shuffleId, final int partitionId, final MapStatus[] mapStatuses, final RoaringBitmap tracker) {
      scala.Predef..MODULE$.assert(mapStatuses != null && tracker != null);
      HashMap splitsByAddress = new HashMap();
      .MODULE$.withFilter$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(mapStatuses))), (check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$getMapStatusesForMergeStatus$1(check$ifrefutable$3))).foreach((x$33) -> {
         if (x$33 != null) {
            MapStatus status = (MapStatus)x$33._1();
            int mapIndex = x$33._2$mcI$sp();
            if (tracker.contains(mapIndex)) {
               MODULE$.validateStatus(status, shuffleId, partitionId);
               return ((Growable)splitsByAddress.getOrElseUpdate(status.location(), () -> (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))).$plus$eq(new Tuple3(new ShuffleBlockId(shuffleId, status.mapId(), partitionId), BoxesRunTime.boxToLong(status.getSizeForBlock(partitionId)), BoxesRunTime.boxToInteger(mapIndex)));
            } else {
               return BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x$33);
         }
      });
      return splitsByAddress.iterator();
   }

   public void validateStatus(final ShuffleOutputStatus status, final int shuffleId, final int partition) {
      if (status == null) {
         MessageWithContext errorMessage = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Missing an output location for shuffle ", " partition ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId)), new MDC(org.apache.spark.internal.LogKeys.PARTITION_ID..MODULE$, BoxesRunTime.boxToInteger(partition))})));
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> errorMessage));
         throw new MetadataFetchFailedException(shuffleId, partition, errorMessage.message());
      }
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$serializeOutputStatuses$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   private static final Object deserializeObject$1(final InputStream in, final SparkConf conf$4) {
      CompressionCodec codec = CompressionCodec$.MODULE$.createCodec(conf$4, (String)conf$4.get(org.apache.spark.internal.config.package$.MODULE$.MAP_STATUS_COMPRESSION_CODEC()));
      ObjectInputStream objIn = new ObjectInputStream(codec.compressedInputStream(in));
      return Utils$.MODULE$.tryWithSafeFinally(() -> objIn.readObject(), (JFunction0.mcV.sp)() -> objIn.close());
   }

   // $FF: synthetic method
   public static final long $anonfun$deserializeOutputStatuses$3(final long x$27, final byte[] x$28) {
      return x$27 + (long)x$28.length;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertMapStatuses$2(final MergeStatus x$30) {
      return x$30 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertMapStatuses$1(final MergeStatus[] x$29) {
      return .MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps(x$29), (x$30) -> BoxesRunTime.boxToBoolean($anonfun$convertMapStatuses$2(x$30)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$convertMapStatuses$4(final MergeStatus[] mergeStatuses$1, final HashMap splitsByAddress$1, final int shuffleId$13, final int partId) {
      MergeStatus mergeStatus = mergeStatuses$1[partId];
      return mergeStatus != null && mergeStatus.totalSize() > 0L ? ((Growable)splitsByAddress$1.getOrElseUpdate(mergeStatus.location(), () -> (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))).$plus$eq(new Tuple3(new ShuffleMergedBlockId(shuffleId$13, mergeStatus.shuffleMergeId(), partId), BoxesRunTime.boxToLong(mergeStatus.totalSize()), BoxesRunTime.boxToInteger(MODULE$.SHUFFLE_PUSH_MAP_ID()))) : BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertMapStatuses$6(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final Object $anonfun$convertMapStatuses$8(final MergeStatus[] mergeStatuses$1, final int mapIndex$3, final MapStatus mapStatus$2, final HashMap splitsByAddress$1, final int shuffleId$13, final int partId) {
      MergeStatus mergeStatus = mergeStatuses$1[partId];
      if (mergeStatus != null && mergeStatus.totalSize() != 0L && mergeStatus.tracker().contains(mapIndex$3)) {
         return BoxedUnit.UNIT;
      } else {
         long size = mapStatus$2.getSizeForBlock(partId);
         return size != 0L ? ((Growable)splitsByAddress$1.getOrElseUpdate(mapStatus$2.location(), () -> (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))).$plus$eq(new Tuple3(new ShuffleBlockId(shuffleId$13, mapStatus$2.mapId(), partId), BoxesRunTime.boxToLong(size), BoxesRunTime.boxToInteger(mapIndex$3))) : BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$convertMapStatuses$7(final int shuffleId$13, final int startPartition$3, final int endPartition$3, final MergeStatus[] mergeStatuses$1, final HashMap splitsByAddress$1, final Tuple2 x$31) {
      if (x$31 != null) {
         MapStatus mapStatus = (MapStatus)x$31._1();
         int mapIndex = x$31._2$mcI$sp();
         MODULE$.validateStatus(mapStatus, shuffleId$13, startPartition$3);
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(startPartition$3), endPartition$3).foreach((partId) -> $anonfun$convertMapStatuses$8(mergeStatuses$1, mapIndex, mapStatus, splitsByAddress$1, shuffleId$13, BoxesRunTime.unboxToInt(partId)));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$31);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertMapStatuses$10(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final Object $anonfun$convertMapStatuses$12(final MapStatus status$3, final HashMap splitsByAddress$1, final int shuffleId$13, final int mapIndex$4, final int part) {
      long size = status$3.getSizeForBlock(part);
      return size != 0L ? ((Growable)splitsByAddress$1.getOrElseUpdate(status$3.location(), () -> (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))).$plus$eq(new Tuple3(new ShuffleBlockId(shuffleId$13, status$3.mapId(), part), BoxesRunTime.boxToLong(size), BoxesRunTime.boxToInteger(mapIndex$4))) : BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   public static final void $anonfun$convertMapStatuses$11(final int shuffleId$13, final int startPartition$3, final int endPartition$3, final HashMap splitsByAddress$1, final Tuple2 x$32) {
      if (x$32 != null) {
         MapStatus status = (MapStatus)x$32._1();
         int mapIndex = x$32._2$mcI$sp();
         MODULE$.validateStatus(status, shuffleId$13, startPartition$3);
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(startPartition$3), endPartition$3).foreach((part) -> $anonfun$convertMapStatuses$12(status, splitsByAddress$1, shuffleId$13, mapIndex, BoxesRunTime.unboxToInt(part)));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$32);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getMapStatusesForMergeStatus$1(final Tuple2 check$ifrefutable$3) {
      return check$ifrefutable$3 != null;
   }

   private MapOutputTracker$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
