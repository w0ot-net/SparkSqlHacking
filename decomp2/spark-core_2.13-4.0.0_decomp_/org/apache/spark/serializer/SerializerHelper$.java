package org.apache.spark.serializer;

import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.util.io.ChunkedByteBuffer;
import org.apache.spark.util.io.ChunkedByteBuffer$;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;

public final class SerializerHelper$ implements Logging {
   public static final SerializerHelper$ MODULE$ = new SerializerHelper$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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

   public ChunkedByteBuffer serializeToChunkedBuffer(final SerializerInstance serializerInstance, final Object objectToSerialize, final long estimatedSize, final ClassTag evidence$1) {
      int chunkSize = ChunkedByteBuffer$.MODULE$.estimateBufferChunkSize(estimatedSize);
      ChunkedByteBufferOutputStream cbbos = new ChunkedByteBufferOutputStream(chunkSize, (x$1) -> $anonfun$serializeToChunkedBuffer$1(BoxesRunTime.unboxToInt(x$1)));
      SerializationStream out = serializerInstance.serializeStream(cbbos);
      out.writeObject(objectToSerialize, evidence$1);
      out.close();
      cbbos.close();
      return cbbos.toChunkedByteBuffer();
   }

   public long serializeToChunkedBuffer$default$3() {
      return -1L;
   }

   public Object deserializeFromChunkedBuffer(final SerializerInstance serializerInstance, final ChunkedByteBuffer bytes, final ClassTag evidence$2) {
      DeserializationStream in = serializerInstance.deserializeStream(bytes.toInputStream(bytes.toInputStream$default$1()));
      Object res = in.readObject(evidence$2);
      in.close();
      return res;
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$serializeToChunkedBuffer$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   private SerializerHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
