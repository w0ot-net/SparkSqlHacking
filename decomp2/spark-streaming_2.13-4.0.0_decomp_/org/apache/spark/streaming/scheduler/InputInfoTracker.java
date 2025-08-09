package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala..less.colon.less.;
import scala.collection.Iterable;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m3Q\u0001C\u0005\u0001\u0017MA\u0001\u0002\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\u0006M\u0001!\ta\n\u0005\bW\u0001\u0011\r\u0011\"\u0003-\u0011\u0019y\u0004\u0001)A\u0005[!)\u0001\t\u0001C\u0001\u0003\")\u0011\n\u0001C\u0001\u0015\")q\u000b\u0001C\u00011\n\u0001\u0012J\u001c9vi&sgm\u001c+sC\u000e\\WM\u001d\u0006\u0003\u0015-\t\u0011b]2iK\u0012,H.\u001a:\u000b\u00051i\u0011!C:ue\u0016\fW.\u001b8h\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7c\u0001\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u0004\"a\u0007\u0010\u000e\u0003qQ!!H\u0007\u0002\u0011%tG/\u001a:oC2L!a\b\u000f\u0003\u000f1{wmZ5oO\u0006\u00191o]2\u0004\u0001A\u00111\u0005J\u0007\u0002\u0017%\u0011Qe\u0003\u0002\u0011'R\u0014X-Y7j]\u001e\u001cuN\u001c;fqR\fa\u0001P5oSRtDC\u0001\u0015+!\tI\u0003!D\u0001\n\u0011\u0015\u0001#\u00011\u0001#\u0003U\u0011\u0017\r^2i)&lW\rV8J]B,H/\u00138g_N,\u0012!\f\t\u0005]M*\u0004(D\u00010\u0015\t\u0001\u0014'A\u0004nkR\f'\r\\3\u000b\u0005I2\u0012AC2pY2,7\r^5p]&\u0011Ag\f\u0002\b\u0011\u0006\u001c\b.T1q!\t\u0019c'\u0003\u00028\u0017\t!A+[7f!\u0011q3'\u000f\u001f\u0011\u0005UQ\u0014BA\u001e\u0017\u0005\rIe\u000e\u001e\t\u0003SuJ!AP\u0005\u0003\u001fM#(/Z1n\u0013:\u0004X\u000f^%oM>\faCY1uG\"$\u0016.\\3U_&s\u0007/\u001e;J]\u001a|7\u000fI\u0001\u000be\u0016\u0004xN\u001d;J]\u001a|Gc\u0001\"F\u000fB\u0011QcQ\u0005\u0003\tZ\u0011A!\u00168ji\")a)\u0002a\u0001k\u0005I!-\u0019;dQRKW.\u001a\u0005\u0006\u0011\u0016\u0001\r\u0001P\u0001\nS:\u0004X\u000f^%oM>\fqaZ3u\u0013:4w\u000e\u0006\u0002L-B!AjU\u001d=\u001d\ti\u0015\u000b\u0005\u0002O-5\tqJ\u0003\u0002QC\u00051AH]8pizJ!A\u0015\f\u0002\rA\u0013X\rZ3g\u0013\t!VKA\u0002NCBT!A\u0015\f\t\u000b\u00193\u0001\u0019A\u001b\u0002\u000f\rdW-\u00198vaR\u0011!)\u0017\u0005\u00065\u001e\u0001\r!N\u0001\u0010E\u0006$8\r\u001b+ie\u0016\u001c\b\u000eV5nK\u0002"
)
public class InputInfoTracker implements Logging {
   private final HashMap batchTimeToInputInfos;
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

   private HashMap batchTimeToInputInfos() {
      return this.batchTimeToInputInfos;
   }

   public void reportInfo(final Time batchTime, final StreamInputInfo inputInfo) {
      synchronized(this){}

      try {
         HashMap inputInfos = (HashMap)this.batchTimeToInputInfos().getOrElseUpdate(batchTime, () -> new HashMap());
         if (inputInfos.contains(BoxesRunTime.boxToInteger(inputInfo.inputStreamId()))) {
            int var10002 = inputInfo.inputStreamId();
            throw new IllegalStateException("Input stream " + var10002 + " for batch " + batchTime + " is already added into InputInfoTracker, this is an illegal state");
         }

         HashMap var10000 = (HashMap)inputInfos.$plus$eq(new Tuple2(BoxesRunTime.boxToInteger(inputInfo.inputStreamId()), inputInfo));
      } catch (Throwable var6) {
         throw var6;
      }

   }

   public synchronized scala.collection.immutable.Map getInfo(final Time batchTime) {
      Option inputInfos = this.batchTimeToInputInfos().get(batchTime);
      return (scala.collection.immutable.Map)inputInfos.map((x$2) -> x$2.toMap(.MODULE$.refl())).getOrElse(() -> (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$));
   }

   public void cleanup(final Time batchThreshTime) {
      synchronized(this){}

      try {
         Iterable timesToCleanup = (Iterable)this.batchTimeToInputInfos().keys().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$cleanup$1(batchThreshTime, x$3)));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"remove old batch metadata: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DURATION..MODULE$, timesToCleanup.mkString(" "))}))))));
         HashMap var10000 = (HashMap)this.batchTimeToInputInfos().$minus$minus$eq(timesToCleanup);
      } catch (Throwable var5) {
         throw var5;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$cleanup$1(final Time batchThreshTime$1, final Time x$3) {
      return x$3.$less(batchThreshTime$1);
   }

   public InputInfoTracker(final StreamingContext ssc) {
      Logging.$init$(this);
      this.batchTimeToInputInfos = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
