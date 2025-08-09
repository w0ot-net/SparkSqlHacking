package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%aAB\t\u0013\u0003\u0003!\"\u0004\u0003\u0005)\u0001\t\u0005\t\u0015!\u0003+\u0011\u0015)\u0004\u0001\"\u00017\u0011\u001d)\u0005A1A\u0005\n\u0019Ca\u0001\u0015\u0001!\u0002\u00139\u0005bB)\u0001\u0005\u0004%IA\u0015\u0005\u00073\u0002\u0001\u000b\u0011B*\t\u0011i\u0003!\u0019!C\u0001)mCaA\u0019\u0001!\u0002\u0013a\u0006\"B2\u0001\t\u0003!\u0007\"\u00025\u0001\t\u0003!\u0007\"B5\u0001\t\u0003Q\u0007\"B7\u0001\t\u0003q\u0007\"\u0002:\u0001\t#!\u0007\"B:\u0001\t#!\u0007\"\u0002;\u0001\r#)\b\"B<\u0001\r#A(!C#wK:$Hj\\8q\u0015\t\u0019B#\u0001\u0003vi&d'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0016\u0005mY4c\u0001\u0001\u001dEA\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\u0004\"a\t\u0014\u000e\u0003\u0011R!!\n\u000b\u0002\u0011%tG/\u001a:oC2L!a\n\u0013\u0003\u000f1{wmZ5oO\u0006!a.Y7f\u0007\u0001\u0001\"a\u000b\u001a\u000f\u00051\u0002\u0004CA\u0017\u001f\u001b\u0005q#BA\u0018*\u0003\u0019a$o\\8u}%\u0011\u0011GH\u0001\u0007!J,G-\u001a4\n\u0005M\"$AB*ue&twM\u0003\u00022=\u00051A(\u001b8jiz\"\"a\u000e#\u0011\u0007a\u0002\u0011(D\u0001\u0013!\tQ4\b\u0004\u0001\u0005\u000bq\u0002!\u0019A\u001f\u0003\u0003\u0015\u000b\"AP!\u0011\u0005uy\u0014B\u0001!\u001f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\b\"\n\u0005\rs\"aA!os\")\u0001F\u0001a\u0001U\u0005QQM^3oiF+X-^3\u0016\u0003\u001d\u00032\u0001\u0013(:\u001b\u0005I%B\u0001&L\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003'1S\u0011!T\u0001\u0005U\u00064\u0018-\u0003\u0002P\u0013\ni!\t\\8dW&tw-U;fk\u0016\f1\"\u001a<f]R\fV/Z;fA\u000591\u000f^8qa\u0016$W#A*\u0011\u0005Q;V\"A+\u000b\u0005YK\u0015AB1u_6L7-\u0003\u0002Y+\ni\u0011\t^8nS\u000e\u0014un\u001c7fC:\f\u0001b\u001d;paB,G\rI\u0001\fKZ,g\u000e\u001e+ie\u0016\fG-F\u0001]!\ti\u0006-D\u0001_\u0015\tyF*\u0001\u0003mC:<\u0017BA1_\u0005\u0019!\u0006N]3bI\u0006aQM^3oiRC'/Z1eA\u0005)1\u000f^1siR\tQ\r\u0005\u0002\u001eM&\u0011qM\b\u0002\u0005+:LG/\u0001\u0003ti>\u0004\u0018\u0001\u00029pgR$\"!Z6\t\u000b1\\\u0001\u0019A\u001d\u0002\u000b\u00154XM\u001c;\u0002\u0011%\u001c\u0018i\u0019;jm\u0016,\u0012a\u001c\t\u0003;AL!!\u001d\u0010\u0003\u000f\t{w\u000e\\3b]\u00069qN\\*uCJ$\u0018AB8o'R|\u0007/A\u0005p]J+7-Z5wKR\u0011QM\u001e\u0005\u0006Y>\u0001\r!O\u0001\b_:,%O]8s)\t)\u0017\u0010C\u0003{!\u0001\u000710A\u0001f!\ra\u00181\u0001\b\u0003{~t!!\f@\n\u0003}I1!!\u0001\u001f\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0002\u0002\b\tIA\u000b\u001b:po\u0006\u0014G.\u001a\u0006\u0004\u0003\u0003q\u0002"
)
public abstract class EventLoop implements Logging {
   public final String org$apache$spark$util$EventLoop$$name;
   private final BlockingQueue org$apache$spark$util$EventLoop$$eventQueue;
   private final AtomicBoolean org$apache$spark$util$EventLoop$$stopped;
   private final Thread eventThread;
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

   public BlockingQueue org$apache$spark$util$EventLoop$$eventQueue() {
      return this.org$apache$spark$util$EventLoop$$eventQueue;
   }

   public AtomicBoolean org$apache$spark$util$EventLoop$$stopped() {
      return this.org$apache$spark$util$EventLoop$$stopped;
   }

   public Thread eventThread() {
      return this.eventThread;
   }

   public void start() {
      if (this.org$apache$spark$util$EventLoop$$stopped().get()) {
         throw new IllegalStateException(this.org$apache$spark$util$EventLoop$$name + " has already been stopped");
      } else {
         this.onStart();
         this.eventThread().start();
      }
   }

   public void stop() {
      if (this.org$apache$spark$util$EventLoop$$stopped().compareAndSet(false, true)) {
         this.eventThread().interrupt();
         boolean onStopCalled = false;

         try {
            this.eventThread().join();
            onStopCalled = true;
            this.onStop();
         } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
            if (!onStopCalled) {
               this.onStop();
            }
         }

      }
   }

   public void post(final Object event) {
      if (!this.org$apache$spark$util$EventLoop$$stopped().get()) {
         if (this.eventThread().isAlive()) {
            this.org$apache$spark$util$EventLoop$$eventQueue().put(event);
         } else {
            this.onError(new IllegalStateException(this.org$apache$spark$util$EventLoop$$name + " has already been stopped accidentally."));
         }
      }
   }

   public boolean isActive() {
      return this.eventThread().isAlive();
   }

   public void onStart() {
   }

   public void onStop() {
   }

   public abstract void onReceive(final Object event);

   public abstract void onError(final Throwable e);

   public EventLoop(final String name) {
      this.org$apache$spark$util$EventLoop$$name = name;
      Logging.$init$(this);
      this.org$apache$spark$util$EventLoop$$eventQueue = new LinkedBlockingDeque();
      this.org$apache$spark$util$EventLoop$$stopped = new AtomicBoolean(false);
      this.eventThread = new Thread() {
         // $FF: synthetic field
         private final EventLoop $outer;

         public void run() {
            try {
               while(!this.$outer.org$apache$spark$util$EventLoop$$stopped().get()) {
                  Object event = this.$outer.org$apache$spark$util$EventLoop$$eventQueue().take();

                  try {
                     this.$outer.onReceive(event);
                  } catch (Throwable var15) {
                     if (var15 != null) {
                        Throwable var7 = var15;
                        if (.MODULE$.apply(var15)) {
                           try {
                              this.$outer.onError(var7);
                              BoxedUnit var20 = BoxedUnit.UNIT;
                           } catch (Throwable var14) {
                              if (var14 == null || !.MODULE$.apply(var14)) {
                                 throw var14;
                              }

                              this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unexpected error in ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT_LOOP..MODULE$, this.$outer.org$apache$spark$util$EventLoop$$name)})))), var14);
                              BoxedUnit var18 = BoxedUnit.UNIT;
                              var18 = BoxedUnit.UNIT;
                           }
                           continue;
                        }
                     }

                     throw var15;
                  }
               }
            } catch (Throwable var16) {
               if (var16 instanceof InterruptedException) {
                  BoxedUnit var17 = BoxedUnit.UNIT;
               } else {
                  if (var16 == null || !.MODULE$.apply(var16)) {
                     throw var16;
                  }

                  this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unexpected error in ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT_LOOP..MODULE$, this.$outer.org$apache$spark$util$EventLoop$$name)})))), var16);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }

         }

         public {
            if (EventLoop.this == null) {
               throw null;
            } else {
               this.$outer = EventLoop.this;
               this.setDaemon(true);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }
}
