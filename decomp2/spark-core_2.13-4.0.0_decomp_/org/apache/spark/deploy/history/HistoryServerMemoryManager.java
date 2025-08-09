package org.apache.spark.deploy.history;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.History$;
import org.apache.spark.io.CompressionCodec$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005a4A!\u0004\b\u00053!Aa\u0005\u0001B\u0001B\u0003%q\u0005C\u0003,\u0001\u0011\u0005A\u0006C\u00041\u0001\t\u0007I\u0011B\u0019\t\rU\u0002\u0001\u0015!\u00033\u0011!1\u0004A1A\u0005\u000299\u0004B\u0002#\u0001A\u0003%\u0001\b\u0003\u0005F\u0001\t\u0007I\u0011\u0001\bG\u0011\u0019\u0001\u0007\u0001)A\u0005\u000f\")\u0011\r\u0001C\u0001E\")a\r\u0001C\u0001O\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0005k\nQ\u0002*[:u_JL8+\u001a:wKJlU-\\8ss6\u000bg.Y4fe*\u0011q\u0002E\u0001\bQ&\u001cHo\u001c:z\u0015\t\t\"#\u0001\u0004eKBdw.\u001f\u0006\u0003'Q\tQa\u001d9be.T!!\u0006\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0012aA8sO\u000e\u00011c\u0001\u0001\u001bAA\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t1\u0011I\\=SK\u001a\u0004\"!\t\u0013\u000e\u0003\tR!a\t\n\u0002\u0011%tG/\u001a:oC2L!!\n\u0012\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g!\tA\u0013&D\u0001\u0013\u0013\tQ#CA\u0005Ta\u0006\u00148nQ8oM\u00061A(\u001b8jiz\"\"!L\u0018\u0011\u00059\u0002Q\"\u0001\b\t\u000b\u0019\u0012\u0001\u0019A\u0014\u0002\u00115\f\u00070V:bO\u0016,\u0012A\r\t\u00037MJ!\u0001\u000e\u000f\u0003\t1{gnZ\u0001\n[\u0006DXk]1hK\u0002\nAbY;se\u0016tG/V:bO\u0016,\u0012\u0001\u000f\t\u0003s\tk\u0011A\u000f\u0006\u0003wq\na!\u0019;p[&\u001c'BA\u001f?\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003\u007f\u0001\u000bA!\u001e;jY*\t\u0011)\u0001\u0003kCZ\f\u0017BA\";\u0005)\tEo\\7jG2{gnZ\u0001\u000eGV\u0014(/\u001a8u+N\fw-\u001a\u0011\u0002\r\u0005\u001cG/\u001b<f+\u00059\u0005\u0003\u0002%N\u001fJj\u0011!\u0013\u0006\u0003\u0015.\u000bq!\\;uC\ndWM\u0003\u0002M9\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00059K%a\u0002%bg\"l\u0015\r\u001d\t\u00057A\u0013V,\u0003\u0002R9\t1A+\u001e9mKJ\u0002\"a\u0015.\u000f\u0005QC\u0006CA+\u001d\u001b\u00051&BA,\u0019\u0003\u0019a$o\\8u}%\u0011\u0011\fH\u0001\u0007!J,G-\u001a4\n\u0005mc&AB*ue&twM\u0003\u0002Z9A\u00191D\u0018*\n\u0005}c\"AB(qi&|g.A\u0004bGRLg/\u001a\u0011\u0002\u0015%t\u0017\u000e^5bY&TX\rF\u0001d!\tYB-\u0003\u0002f9\t!QK\\5u\u0003\u0015aW-Y:f)\u0015\u0019\u0007N\u001b7o\u0011\u0015I'\u00021\u0001S\u0003\u0015\t\u0007\u000f]%e\u0011\u0015Y'\u00021\u0001^\u0003%\tG\u000f^3naRLE\rC\u0003n\u0015\u0001\u0007!'\u0001\u0007fm\u0016tG\u000fT8h'&TX\rC\u0003p\u0015\u0001\u0007Q,A\u0003d_\u0012,7-A\u0004sK2,\u0017m]3\u0015\u0007\r\u00148\u000fC\u0003j\u0017\u0001\u0007!\u000bC\u0003l\u0017\u0001\u0007Q,\u0001\fbaB\u0014x\u000e_5nCR,W*Z7pef,6/Y4f)\r\u0011do\u001e\u0005\u0006[2\u0001\rA\r\u0005\u0006_2\u0001\r!\u0018"
)
public class HistoryServerMemoryManager implements Logging {
   private final long maxUsage;
   private final AtomicLong currentUsage;
   private final HashMap active;
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

   private long maxUsage() {
      return this.maxUsage;
   }

   public AtomicLong currentUsage() {
      return this.currentUsage;
   }

   public HashMap active() {
      return this.active;
   }

   public void initialize() {
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialized memory manager: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"current usage = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_CURRENT..MODULE$, Utils$.MODULE$.bytesToString(this.currentUsage().get()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"max usage = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES_MAX..MODULE$, Utils$.MODULE$.bytesToString(this.maxUsage()))}))))));
   }

   public void lease(final String appId, final Option attemptId, final long eventLogSize, final Option codec) {
      long memoryUsage = this.approximateMemoryUsage(eventLogSize, codec);
      if (memoryUsage + this.currentUsage().get() > this.maxUsage()) {
         throw new RuntimeException("Not enough memory to create hybrid store for app " + appId + " / " + attemptId + ".");
      } else {
         synchronized(this.active()){}

         try {
            this.active().update(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId), BoxesRunTime.boxToLong(memoryUsage));
         } catch (Throwable var10) {
            throw var10;
         }

         this.currentUsage().addAndGet(memoryUsage);
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Leasing ", " memory usage for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(memoryUsage))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"app ", " / ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attemptId)}))))));
      }
   }

   public void release(final String appId, final Option attemptId) {
      synchronized(this.active()){}

      Option var6;
      try {
         var6 = this.active().remove(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(appId), attemptId));
      } catch (Throwable var12) {
         throw var12;
      }

      if (var6 instanceof Some var8) {
         long m = BoxesRunTime.unboxToLong(var8.value());
         this.currentUsage().addAndGet(-m);
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Released ", " memory usage for "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, Utils$.MODULE$.bytesToString(m))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"app ", " / ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, attemptId)}))))));
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var6)) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var6);
      }
   }

   private long approximateMemoryUsage(final long eventLogSize, final Option codec) {
      boolean var6 = false;
      Object var7 = null;
      if (codec instanceof Some) {
         var6 = true;
         Some var11 = (Some)codec;
         String var9 = (String)var11.value();
         String var10000 = CompressionCodec$.MODULE$.ZSTD();
         if (var10000 == null) {
            if (var9 == null) {
               return eventLogSize * 10L;
            }
         } else if (var10000.equals(var9)) {
            return eventLogSize * 10L;
         }
      }

      if (var6) {
         return eventLogSize * 4L;
      } else if (scala.None..MODULE$.equals(codec)) {
         return eventLogSize / 2L;
      } else {
         throw new MatchError(codec);
      }
   }

   public HistoryServerMemoryManager(final SparkConf conf) {
      Logging.$init$(this);
      this.maxUsage = BoxesRunTime.unboxToLong(conf.get(History$.MODULE$.MAX_IN_MEMORY_STORE_USAGE()));
      this.currentUsage = new AtomicLong(0L);
      this.active = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
