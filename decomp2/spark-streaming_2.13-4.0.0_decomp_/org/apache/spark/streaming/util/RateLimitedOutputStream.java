package org.apache.spark.streaming.util;

import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005Y4Q\u0001F\u000b\u0001/}A\u0001B\f\u0001\u0003\u0002\u0003\u0006I\u0001\t\u0005\ta\u0001\u0011\t\u0011)A\u0005c!)q\u0007\u0001C\u0001q!9Q\b\u0001b\u0001\n\u0013q\u0004B\u0002\"\u0001A\u0003%q\bC\u0004D\u0001\t\u0007I\u0011\u0002#\t\r\u0015\u0003\u0001\u0015!\u00032\u0011\u001d1\u0005\u00011A\u0005\nyBqa\u0012\u0001A\u0002\u0013%\u0001\n\u0003\u0004O\u0001\u0001\u0006Ka\u0010\u0005\b\u001f\u0002\u0001\r\u0011\"\u0003?\u0011\u001d\u0001\u0006\u00011A\u0005\nECaa\u0015\u0001!B\u0013y\u0004\"\u0002+\u0001\t\u0003*\u0006\"\u0002+\u0001\t\u0003B\u0006\"\u0002+\u0001\t\u000b\n\u0007\"\u00028\u0001\t\u0003z\u0007\"\u00029\u0001\t\u0003z\u0007\"B9\u0001\t\u0013\u0011(a\u0006*bi\u0016d\u0015.\\5uK\u0012|U\u000f\u001e9viN#(/Z1n\u0015\t1r#\u0001\u0003vi&d'B\u0001\r\u001a\u0003%\u0019HO]3b[&twM\u0003\u0002\u001b7\u0005)1\u000f]1sW*\u0011A$H\u0001\u0007CB\f7\r[3\u000b\u0003y\t1a\u001c:h'\r\u0001\u0001\u0005\u000b\t\u0003C\u0019j\u0011A\t\u0006\u0003G\u0011\n!![8\u000b\u0003\u0015\nAA[1wC&\u0011qE\t\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\u001c\t\u0003S1j\u0011A\u000b\u0006\u0003We\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003[)\u0012q\u0001T8hO&tw-A\u0002pkR\u001c\u0001!\u0001\neKNL'/\u001a3CsR,7\u000fU3s'\u0016\u001c\u0007C\u0001\u001a6\u001b\u0005\u0019$\"\u0001\u001b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001a$aA%oi\u00061A(\u001b8jiz\"2!O\u001e=!\tQ\u0004!D\u0001\u0016\u0011\u0015q3\u00011\u0001!\u0011\u0015\u00014\u00011\u00012\u00035\u0019\u0016LT\"`\u0013:#VI\u0015,B\u0019V\tq\b\u0005\u00023\u0001&\u0011\u0011i\r\u0002\u0005\u0019>tw-\u0001\bT3:\u001bu,\u0013(U\u000bJ3\u0016\t\u0014\u0011\u0002\u0015\rCUKT&`'&SV)F\u00012\u0003-\u0019\u0005*\u0016(L?NK%,\u0012\u0011\u0002\u00191\f7\u000f^*z]\u000e$\u0016.\\3\u0002!1\f7\u000f^*z]\u000e$\u0016.\\3`I\u0015\fHCA%M!\t\u0011$*\u0003\u0002Lg\t!QK\\5u\u0011\u001di\u0015\"!AA\u0002}\n1\u0001\u001f\u00132\u00035a\u0017m\u001d;Ts:\u001cG+[7fA\u0005)\"-\u001f;fg^\u0013\u0018\u000e\u001e;f]NKgnY3Ts:\u001c\u0017!\u00072zi\u0016\u001cxK]5ui\u0016t7+\u001b8dKNKhnY0%KF$\"!\u0013*\t\u000f5c\u0011\u0011!a\u0001\u007f\u00051\"-\u001f;fg^\u0013\u0018\u000e\u001e;f]NKgnY3Ts:\u001c\u0007%A\u0003xe&$X\r\u0006\u0002J-\")qK\u0004a\u0001c\u0005\t!\r\u0006\u0002J3\")!l\u0004a\u00017\u0006)!-\u001f;fgB\u0019!\u0007\u00180\n\u0005u\u001b$!B!se\u0006L\bC\u0001\u001a`\u0013\t\u00017G\u0001\u0003CsR,G\u0003B%cG\u0016DQA\u0017\tA\u0002mCQ\u0001\u001a\tA\u0002E\naa\u001c4gg\u0016$\b\"\u00024\u0011\u0001\u0004\t\u0014A\u00027f]\u001e$\b\u000e\u000b\u0002\u0011QB\u0011\u0011\u000e\\\u0007\u0002U*\u00111nM\u0001\u000bC:tw\u000e^1uS>t\u0017BA7k\u0005\u001d!\u0018-\u001b7sK\u000e\fQA\u001a7vg\"$\u0012!S\u0001\u0006G2|7/Z\u0001\fo\u0006LG\u000fV8Xe&$X\r\u0006\u0002Jg\")Ao\u0005a\u0001c\u0005Aa.^7CsR,7\u000f\u000b\u0002\u0014Q\u0002"
)
public class RateLimitedOutputStream extends OutputStream implements Logging {
   private final OutputStream out;
   private final int desiredBytesPerSec;
   private final long SYNC_INTERVAL;
   private final int CHUNK_SIZE;
   private long lastSyncTime;
   private long bytesWrittenSinceSync;
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

   private long SYNC_INTERVAL() {
      return this.SYNC_INTERVAL;
   }

   private int CHUNK_SIZE() {
      return this.CHUNK_SIZE;
   }

   private long lastSyncTime() {
      return this.lastSyncTime;
   }

   private void lastSyncTime_$eq(final long x$1) {
      this.lastSyncTime = x$1;
   }

   private long bytesWrittenSinceSync() {
      return this.bytesWrittenSinceSync;
   }

   private void bytesWrittenSinceSync_$eq(final long x$1) {
      this.bytesWrittenSinceSync = x$1;
   }

   public void write(final int b) {
      this.waitToWrite(1);
      this.out.write(b);
   }

   public void write(final byte[] bytes) {
      this.write(bytes, 0, bytes.length);
   }

   public final void write(final byte[] bytes, final int offset, final int length) {
      while(true) {
         int writeSize = .MODULE$.min(length - offset, this.CHUNK_SIZE());
         if (writeSize <= 0) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }

         this.waitToWrite(writeSize);
         this.out.write(bytes, offset, writeSize);
         int var10001 = offset + writeSize;
         length = length;
         offset = var10001;
         bytes = bytes;
      }
   }

   public void flush() {
      this.out.flush();
   }

   public void close() {
      this.out.close();
   }

   private void waitToWrite(final int numBytes) {
      while(true) {
         long now = System.nanoTime();
         long elapsedNanosecs = .MODULE$.max(now - this.lastSyncTime(), 1L);
         double rate = (double)this.bytesWrittenSinceSync() * (double)1000000000 / (double)elapsedNanosecs;
         if (rate < (double)this.desiredBytesPerSec) {
            this.bytesWrittenSinceSync_$eq(this.bytesWrittenSinceSync() + (long)numBytes);
            if (now > this.lastSyncTime() + this.SYNC_INTERVAL()) {
               this.lastSyncTime_$eq(now);
               this.bytesWrittenSinceSync_$eq((long)numBytes);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var15 = BoxedUnit.UNIT;
            }

            return;
         }

         long targetTimeInMillis = this.bytesWrittenSinceSync() * 1000L / (long)this.desiredBytesPerSec;
         long elapsedTimeInMillis = TimeUnit.NANOSECONDS.toMillis(elapsedNanosecs);
         long sleepTimeInMillis = targetTimeInMillis - elapsedTimeInMillis;
         if (sleepTimeInMillis > 0L) {
            this.logTrace((Function0)(() -> "Natural rate is " + rate + " per second but desired rate is " + this.desiredBytesPerSec + ", sleeping for " + sleepTimeInMillis + " ms to compensate."));
            Thread.sleep(sleepTimeInMillis);
         }

         numBytes = numBytes;
      }
   }

   public RateLimitedOutputStream(final OutputStream out, final int desiredBytesPerSec) {
      this.out = out;
      this.desiredBytesPerSec = desiredBytesPerSec;
      Logging.$init$(this);
      scala.Predef..MODULE$.require(desiredBytesPerSec > 0);
      this.SYNC_INTERVAL = TimeUnit.NANOSECONDS.convert(10L, TimeUnit.SECONDS);
      this.CHUNK_SIZE = 8192;
      this.lastSyncTime = System.nanoTime();
      this.bytesWrittenSinceSync = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
