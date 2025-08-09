package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.None.;
import scala.runtime.BoxesRunTime;

public final class AccumulatorContext$ implements Logging {
   public static final AccumulatorContext$ MODULE$ = new AccumulatorContext$();
   private static final ConcurrentHashMap originals;
   private static final AtomicLong nextId;
   private static final Some someOfMinusOne;
   private static final Some someOfZero;
   private static final String SQL_ACCUM_IDENTIFIER;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      originals = new ConcurrentHashMap();
      nextId = new AtomicLong(0L);
      someOfMinusOne = new Some(BoxesRunTime.boxToLong(-1L));
      someOfZero = new Some(BoxesRunTime.boxToLong(0L));
      SQL_ACCUM_IDENTIFIER = "sql";
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

   private ConcurrentHashMap originals() {
      return originals;
   }

   public long newId() {
      return nextId.getAndIncrement();
   }

   public int numAccums() {
      return this.originals().size();
   }

   public void register(final AccumulatorV2 a) {
      this.originals().putIfAbsent(BoxesRunTime.boxToLong(a.id()), new WeakReference(a));
   }

   public void remove(final long id) {
      this.originals().remove(BoxesRunTime.boxToLong(id));
   }

   public Option get(final long id) {
      WeakReference ref = (WeakReference)this.originals().get(BoxesRunTime.boxToLong(id));
      if (ref == null) {
         return .MODULE$;
      } else {
         AccumulatorV2 acc = (AccumulatorV2)ref.get();
         if (acc == null) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to access garbage collected accumulator "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ACCUMULATOR_ID..MODULE$, BoxesRunTime.boxToLong(id))}))))));
         }

         return scala.Option..MODULE$.apply(acc);
      }
   }

   public void clear() {
      this.originals().clear();
   }

   public Option internOption(final Option value) {
      boolean var3 = false;
      Some var4 = null;
      if (value instanceof Some) {
         var3 = true;
         var4 = (Some)value;
         Object var6 = var4.value();
         if (BoxesRunTime.equals(BoxesRunTime.boxToLong(0L), var6)) {
            return someOfZero;
         }
      }

      if (var3) {
         Object var7 = var4.value();
         if (BoxesRunTime.equals(BoxesRunTime.boxToLong(-1L), var7)) {
            return someOfMinusOne;
         }
      }

      return value;
   }

   public String SQL_ACCUM_IDENTIFIER() {
      return SQL_ACCUM_IDENTIFIER;
   }

   private AccumulatorContext$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
