package org.apache.spark.broadcast;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SparkEnv$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.util.KeyLock;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

public final class TorrentBroadcast$ implements Logging, Serializable {
   public static final TorrentBroadcast$ MODULE$ = new TorrentBroadcast$();
   private static final KeyLock org$apache$spark$broadcast$TorrentBroadcast$$torrentBroadcastLock;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      org$apache$spark$broadcast$TorrentBroadcast$$torrentBroadcastLock = new KeyLock();
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

   public KeyLock org$apache$spark$broadcast$TorrentBroadcast$$torrentBroadcastLock() {
      return org$apache$spark$broadcast$TorrentBroadcast$$torrentBroadcastLock;
   }

   public ByteBuffer[] blockifyObject(final Object obj, final int blockSize, final Serializer serializer, final Option compressionCodec, final ClassTag evidence$2) {
      ChunkedByteBufferOutputStream cbbos = new ChunkedByteBufferOutputStream(blockSize, (x$1) -> $anonfun$blockifyObject$1(BoxesRunTime.unboxToInt(x$1)));
      OutputStream out = (OutputStream)compressionCodec.map((c) -> c.compressedOutputStream(cbbos)).getOrElse(() -> cbbos);
      SerializerInstance ser = serializer.newInstance();
      SerializationStream serOut = ser.serializeStream(out);
      Utils$.MODULE$.tryWithSafeFinally(() -> serOut.writeObject(obj, evidence$2), (JFunction0.mcV.sp)() -> serOut.close());
      return cbbos.toChunkedByteBuffer().getChunks();
   }

   public Object unBlockifyObject(final InputStream[] blocks, final Serializer serializer, final Option compressionCodec, final ClassTag evidence$3) {
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps((Object[])blocks)), () -> "Cannot unblockify an empty array of blocks");
      SequenceInputStream is = new SequenceInputStream(scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(scala.collection.ArrayOps..MODULE$.iterator$extension(.MODULE$.refArrayOps((Object[])blocks))).asJavaEnumeration());
      InputStream in = (InputStream)compressionCodec.map((c) -> c.compressedInputStream(is)).getOrElse(() -> is);
      SerializerInstance ser = serializer.newInstance();
      DeserializationStream serIn = ser.deserializeStream(in);
      Object obj = Utils$.MODULE$.tryWithSafeFinally(() -> serIn.readObject(evidence$3), (JFunction0.mcV.sp)() -> serIn.close());
      return obj;
   }

   public void unpersist(final long id, final boolean removeFromDriver, final boolean blocking) {
      this.logDebug((Function0)(() -> "Unpersisting TorrentBroadcast " + id));
      SparkEnv$.MODULE$.get().blockManager().master().removeBroadcast(id, removeFromDriver, blocking);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TorrentBroadcast$.class);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$blockifyObject$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   private TorrentBroadcast$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
