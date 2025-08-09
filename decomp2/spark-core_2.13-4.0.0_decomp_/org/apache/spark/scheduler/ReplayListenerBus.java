package org.apache.spark.scheduler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.spark.SparkEnv;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.JsonProtocol$;
import org.apache.spark.util.ListenerBus;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashSet;
import scala.io.Source.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a!B\u0007\u000f\u0001A1\u0002\"B\u0014\u0001\t\u0003I\u0003\"B\u0016\u0001\t\u0003a\u0003b\u00022\u0001#\u0003%\ta\u0019\u0005\b]\u0002\t\n\u0011\"\u0001p\u0011\u0015Y\u0003\u0001\"\u0001r\u0011\u001d\t\t\u0001\u0001C)\u0003\u00079a!\u0016\b\t\u0002A1fAB\u0007\u000f\u0011\u0003\u0001r\u000bC\u0003(\u0011\u0011\u0005\u0001,\u0002\u0003Z\u0011\u0001Q\u0006bB/\t\u0005\u0004%\tA\u0018\u0005\u0007C\"\u0001\u000b\u0011B0\u0003#I+\u0007\u000f\\1z\u0019&\u001cH/\u001a8fe\n+8O\u0003\u0002\u0010!\u0005I1o\u00195fIVdWM\u001d\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sON!\u0001aF\u000f\"!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fMB\u0011adH\u0007\u0002\u001d%\u0011\u0001E\u0004\u0002\u0011'B\f'o\u001b'jgR,g.\u001a:CkN\u0004\"AI\u0013\u000e\u0003\rR!\u0001\n\t\u0002\u0011%tG/\u001a:oC2L!AJ\u0012\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001+!\tq\u0002!\u0001\u0004sKBd\u0017-\u001f\u000b\u0006[ARt)\u0013\t\u000319J!aL\r\u0003\u000f\t{w\u000e\\3b]\")\u0011G\u0001a\u0001e\u00059An\\4ECR\f\u0007CA\u001a9\u001b\u0005!$BA\u001b7\u0003\tIwNC\u00018\u0003\u0011Q\u0017M^1\n\u0005e\"$aC%oaV$8\u000b\u001e:fC6DQa\u000f\u0002A\u0002q\n!b]8ve\u000e,g*Y7f!\tiDI\u0004\u0002?\u0005B\u0011q(G\u0007\u0002\u0001*\u0011\u0011\tK\u0001\u0007yI|w\u000e\u001e \n\u0005\rK\u0012A\u0002)sK\u0012,g-\u0003\u0002F\r\n11\u000b\u001e:j]\u001eT!aQ\r\t\u000f!\u0013\u0001\u0013!a\u0001[\u0005qQ.Y=cKR\u0013XO\\2bi\u0016$\u0007b\u0002&\u0003!\u0003\u0005\raS\u0001\rKZ,g\u000e^:GS2$XM\u001d\t\u0003\u0019*q!!T\u0004\u000f\u00059#fBA(T\u001d\t\u0001&K\u0004\u0002@#&\tQ#\u0003\u0002\u0014)%\u0011\u0011CE\u0005\u0003\u001fA\t\u0011CU3qY\u0006LH*[:uK:,'OQ;t!\tq\u0002b\u0005\u0002\t/Q\taK\u0001\nSKBd\u0017-_#wK:$8OR5mi\u0016\u0014\b\u0003\u0002\r\\y5J!\u0001X\r\u0003\u0013\u0019+hn\u0019;j_:\f\u0014!E*F\u0019\u0016\u001bEkX!M\u0019~3\u0015\n\u0014+F%V\tq\f\u0005\u0002a\u00155\t\u0001\"\u0001\nT\u000b2+5\tV0B\u00192{f)\u0013'U\u000bJ\u0003\u0013\u0001\u0005:fa2\f\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00134+\u0005!'FA\u0017fW\u00051\u0007CA4m\u001b\u0005A'BA5k\u0003%)hn\u00195fG.,GM\u0003\u0002l3\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00055D'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0001\"/\u001a9mCf$C-\u001a4bk2$H\u0005N\u000b\u0002a*\u00121*\u001a\u000b\u0006[Ilhp \u0005\u0006g\u0016\u0001\r\u0001^\u0001\u0006Y&tWm\u001d\t\u0004kjddB\u0001<y\u001d\tyt/C\u0001\u001b\u0013\tI\u0018$A\u0004qC\u000e\\\u0017mZ3\n\u0005md(\u0001C%uKJ\fGo\u001c:\u000b\u0005eL\u0002\"B\u001e\u0006\u0001\u0004a\u0004\"\u0002%\u0006\u0001\u0004i\u0003\"\u0002&\u0006\u0001\u0004Y\u0015\u0001F5t\u0013\u001etwN]1cY\u0016,\u0005pY3qi&|g\u000eF\u0002.\u0003\u000bAq!a\u0002\u0007\u0001\u0004\tI!A\u0001f!\r)\u00181B\u0005\u0004\u0003\u001ba(!\u0003+ie><\u0018M\u00197f\u0001"
)
public class ReplayListenerBus implements SparkListenerBus {
   private CopyOnWriteArrayList org$apache$spark$util$ListenerBus$$listenersPlusTimers;
   private SparkEnv org$apache$spark$util$ListenerBus$$env;
   private boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled;
   private long org$apache$spark$util$ListenerBus$$logSlowEventThreshold;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

   public static Function1 SELECT_ALL_FILTER() {
      return ReplayListenerBus$.MODULE$.SELECT_ALL_FILTER();
   }

   public void doPostEvent(final SparkListenerInterface listener, final SparkListenerEvent event) {
      SparkListenerBus.doPostEvent$(this, listener, event);
   }

   public List listeners() {
      return ListenerBus.listeners$(this);
   }

   public Option getTimer(final Object listener) {
      return ListenerBus.getTimer$(this, listener);
   }

   public final void addListener(final Object listener) {
      ListenerBus.addListener$(this, listener);
   }

   public final void removeListener(final Object listener) {
      ListenerBus.removeListener$(this, listener);
   }

   public final void removeAllListeners() {
      ListenerBus.removeAllListeners$(this);
   }

   public void removeListenerOnError(final Object listener) {
      ListenerBus.removeListenerOnError$(this, listener);
   }

   public void postToAll(final Object event) {
      ListenerBus.postToAll$(this, event);
   }

   public Seq findListenersByClass(final ClassTag evidence$1) {
      return ListenerBus.findListenersByClass$(this, evidence$1);
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

   public CopyOnWriteArrayList org$apache$spark$util$ListenerBus$$listenersPlusTimers() {
      return this.org$apache$spark$util$ListenerBus$$listenersPlusTimers;
   }

   private SparkEnv org$apache$spark$util$ListenerBus$$env$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.org$apache$spark$util$ListenerBus$$env = ListenerBus.org$apache$spark$util$ListenerBus$$env$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$util$ListenerBus$$env;
   }

   public SparkEnv org$apache$spark$util$ListenerBus$$env() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.org$apache$spark$util$ListenerBus$$env$lzycompute() : this.org$apache$spark$util$ListenerBus$$env;
   }

   private boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.org$apache$spark$util$ListenerBus$$logSlowEventEnabled = ListenerBus.org$apache$spark$util$ListenerBus$$logSlowEventEnabled$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$util$ListenerBus$$logSlowEventEnabled;
   }

   public boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.org$apache$spark$util$ListenerBus$$logSlowEventEnabled$lzycompute() : this.org$apache$spark$util$ListenerBus$$logSlowEventEnabled;
   }

   private long org$apache$spark$util$ListenerBus$$logSlowEventThreshold$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.org$apache$spark$util$ListenerBus$$logSlowEventThreshold = ListenerBus.org$apache$spark$util$ListenerBus$$logSlowEventThreshold$(this);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$util$ListenerBus$$logSlowEventThreshold;
   }

   public long org$apache$spark$util$ListenerBus$$logSlowEventThreshold() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.org$apache$spark$util$ListenerBus$$logSlowEventThreshold$lzycompute() : this.org$apache$spark$util$ListenerBus$$logSlowEventThreshold;
   }

   public final void org$apache$spark$util$ListenerBus$_setter_$org$apache$spark$util$ListenerBus$$listenersPlusTimers_$eq(final CopyOnWriteArrayList x$1) {
      this.org$apache$spark$util$ListenerBus$$listenersPlusTimers = x$1;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public boolean replay(final InputStream logData, final String sourceName, final boolean maybeTruncated, final Function1 eventsFilter) {
      Iterator lines = .MODULE$.fromInputStream(logData, scala.io.Codec..MODULE$.UTF8()).getLines();
      return this.replay(lines, sourceName, maybeTruncated, eventsFilter);
   }

   public boolean replay(final Iterator lines, final String sourceName, final boolean maybeTruncated, final Function1 eventsFilter) {
      ObjectRef currentLine = ObjectRef.create((Object)null);
      IntRef lineNumber = IntRef.create(0);
      HashSet unrecognizedEvents = new HashSet();
      HashSet unrecognizedProperties = new HashSet();

      boolean var10000;
      try {
         Iterator lineEntries = lines.zipWithIndex().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$replay$1(eventsFilter, x0$1)));

         while(lineEntries.hasNext()) {
            try {
               Tuple2 entry = (Tuple2)lineEntries.next();
               currentLine.elem = (String)entry._1();
               lineNumber.elem = entry._2$mcI$sp() + 1;
               this.postToAll(JsonProtocol$.MODULE$.sparkEventFromJson((String)currentLine.elem));
            } catch (ClassNotFoundException var19) {
               if (!unrecognizedEvents.contains(var19.getMessage())) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Drop unrecognized event: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var19.getMessage())})))));
                  BoxesRunTime.boxToBoolean(unrecognizedEvents.add(var19.getMessage()));
               } else {
                  BoxedUnit var24 = BoxedUnit.UNIT;
               }

               this.logDebug((Function0)(() -> "Drop incompatible event log: " + (String)currentLine.elem));
            } catch (UnrecognizedPropertyException var20) {
               if (!unrecognizedProperties.contains(var20.getMessage())) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Drop unrecognized property: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var20.getMessage())})))));
                  BoxesRunTime.boxToBoolean(unrecognizedProperties.add(var20.getMessage()));
               } else {
                  BoxedUnit var23 = BoxedUnit.UNIT;
               }

               this.logDebug((Function0)(() -> "Drop incompatible event log: " + (String)currentLine.elem));
            } catch (JsonParseException var21) {
               if (!maybeTruncated || lineEntries.hasNext()) {
                  throw var21;
               }

               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got JsonParseException from log file ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, sourceName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" at line ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LINE_NUM..MODULE$, BoxesRunTime.boxToInteger(lineNumber.elem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the file might not have finished writing cleanly."})))).log(scala.collection.immutable.Nil..MODULE$))));
            }
         }

         var10000 = true;
      } catch (Throwable var22) {
         if (var22 instanceof HaltReplayException) {
            var10000 = false;
         } else if (var22 instanceof EOFException && maybeTruncated) {
            var10000 = false;
         } else {
            if (var22 instanceof IOException) {
               IOException var17 = (IOException)var22;
               throw var17;
            }

            if (!(var22 instanceof Exception)) {
               throw var22;
            }

            Exception var18 = (Exception)var22;
            this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception parsing Spark event log: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, sourceName)})))), var18);
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Malformed line #", ": ", "\\n"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LINE_NUM..MODULE$, BoxesRunTime.boxToInteger(lineNumber.elem)), new MDC(org.apache.spark.internal.LogKeys.LINE..MODULE$, (String)currentLine.elem)})))));
            var10000 = false;
         }
      }

      return var10000;
   }

   public boolean replay$default$3() {
      return false;
   }

   public Function1 replay$default$4() {
      return ReplayListenerBus$.MODULE$.SELECT_ALL_FILTER();
   }

   public boolean isIgnorableException(final Throwable e) {
      return e instanceof HaltReplayException;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$replay$1(final Function1 eventsFilter$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String line = (String)x0$1._1();
         return BoxesRunTime.unboxToBoolean(eventsFilter$1.apply(line));
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ReplayListenerBus() {
      Logging.$init$(this);
      ListenerBus.$init$(this);
      SparkListenerBus.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
