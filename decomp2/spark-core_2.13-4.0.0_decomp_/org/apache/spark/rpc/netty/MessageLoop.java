package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
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
   bytes = "\u0006\u0005\u0005\u001db!B\u000b\u0017\u0003S\t\u0003\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u000bM\u0002A\u0011\u0001\u001b\t\u000f]\u0002!\u0019!C\u0005q!1a\t\u0001Q\u0001\neBqa\u0012\u0001C\u0002\u0013E\u0001\n\u0003\u0004M\u0001\u0001\u0006I!\u0013\u0005\b-\u0002\u0011\rQ\"\u0005X\u0011\u001dY\u0006\u00011A\u0005\nqCq\u0001\u0019\u0001A\u0002\u0013%\u0011\r\u0003\u0004h\u0001\u0001\u0006K!\u0018\u0005\u0006Q\u00021\t!\u001b\u0005\u0006y\u00021\t! \u0005\b\u0003\u0003\u0001A\u0011AA\u0002\u0011\u001d\t)\u0001\u0001C\u000b\u0003\u000fAq!!\u0004\u0001\t\u0013\t\u0019aB\u0004\u0002\u001aYAI!a\u0007\u0007\rU1\u0002\u0012BA\u000f\u0011\u0019\u0019\u0014\u0003\"\u0001\u0002 !I\u0011\u0011E\tC\u0002\u0013\u0005\u00111\u0005\u0005\b\u0003K\t\u0002\u0015!\u0003D\u0005-iUm]:bO\u0016dun\u001c9\u000b\u0005]A\u0012!\u00028fiRL(BA\r\u001b\u0003\r\u0011\bo\u0019\u0006\u00037q\tQa\u001d9be.T!!\b\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0012aA8sO\u000e\u00011c\u0001\u0001#QA\u00111EJ\u0007\u0002I)\tQ%A\u0003tG\u0006d\u0017-\u0003\u0002(I\t1\u0011I\\=SK\u001a\u0004\"!\u000b\u0017\u000e\u0003)R!a\u000b\u000e\u0002\u0011%tG/\u001a:oC2L!!\f\u0016\u0003\u000f1{wmZ5oO\u0006QA-[:qCR\u001c\u0007.\u001a:\u0011\u0005A\nT\"\u0001\f\n\u0005I2\"A\u0003#jgB\fGo\u00195fe\u00061A(\u001b8jiz\"\"!\u000e\u001c\u0011\u0005A\u0002\u0001\"\u0002\u0018\u0003\u0001\u0004y\u0013AB1di&4X-F\u0001:!\rQ\u0014iQ\u0007\u0002w)\u0011A(P\u0001\u000bG>t7-\u001e:sK:$(B\u0001 @\u0003\u0011)H/\u001b7\u000b\u0003\u0001\u000bAA[1wC&\u0011!i\u000f\u0002\u0014\u0019&t7.\u001a3CY>\u001c7.\u001b8h#V,W/\u001a\t\u0003a\u0011K!!\u0012\f\u0003\u000b%s'm\u001c=\u0002\u000f\u0005\u001cG/\u001b<fA\u0005\u0019\"/Z2fSZ,Gj\\8q%Vtg.\u00192mKV\t\u0011JE\u0002K\u001bN3Aa\u0013\u0004\u0001\u0013\naAH]3gS:,W.\u001a8u}\u0005!\"/Z2fSZ,Gj\\8q%Vtg.\u00192mK\u0002\u0002\"AT)\u000e\u0003=S!\u0001U \u0002\t1\fgnZ\u0005\u0003%>\u0013aa\u00142kK\u000e$\bC\u0001(U\u0013\t)vJ\u0001\u0005Sk:t\u0017M\u00197f\u0003)!\bN]3bIB|w\u000e\\\u000b\u00021B\u0011!(W\u0005\u00035n\u0012q\"\u0012=fGV$xN]*feZL7-Z\u0001\bgR|\u0007\u000f]3e+\u0005i\u0006CA\u0012_\u0013\tyFEA\u0004C_>dW-\u00198\u0002\u0017M$x\u000e\u001d9fI~#S-\u001d\u000b\u0003E\u0016\u0004\"aI2\n\u0005\u0011$#\u0001B+oSRDqAZ\u0005\u0002\u0002\u0003\u0007Q,A\u0002yIE\n\u0001b\u001d;paB,G\rI\u0001\u0005a>\u001cH\u000fF\u0002cU^DQa[\u0006A\u00021\fA\"\u001a8ea>Lg\u000e\u001e(b[\u0016\u0004\"!\u001c;\u000f\u00059\u0014\bCA8%\u001b\u0005\u0001(BA9!\u0003\u0019a$o\\8u}%\u00111\u000fJ\u0001\u0007!J,G-\u001a4\n\u0005U4(AB*ue&twM\u0003\u0002tI!)\u0001p\u0003a\u0001s\u00069Q.Z:tC\u001e,\u0007C\u0001\u0019{\u0013\tYhC\u0001\u0007J]\n|\u00070T3tg\u0006<W-\u0001\u0006v]J,w-[:uKJ$\"A\u0019@\t\u000b}d\u0001\u0019\u00017\u0002\t9\fW.Z\u0001\u0005gR|\u0007\u000fF\u0001c\u0003%\u0019X\r^!di&4X\rF\u0002c\u0003\u0013Aa!a\u0003\u000f\u0001\u0004\u0019\u0015!B5oE>D\u0018a\u0003:fG\u0016Lg/\u001a'p_BLS\u0001AA\t\u0003+I1!a\u0005\u0017\u0005Q!U\rZ5dCR,G-T3tg\u0006<W\rT8pa&\u0019\u0011q\u0003\f\u0003#MC\u0017M]3e\u001b\u0016\u001c8/Y4f\u0019>|\u0007/A\u0006NKN\u001c\u0018mZ3M_>\u0004\bC\u0001\u0019\u0012'\t\t\"\u0005\u0006\u0002\u0002\u001c\u0005Q\u0001k\\5t_:\u0004\u0016\u000e\u001c7\u0016\u0003\r\u000b1\u0002U8jg>t\u0007+\u001b7mA\u0001"
)
public abstract class MessageLoop implements Logging {
   private final Dispatcher dispatcher;
   private final LinkedBlockingQueue active;
   private final Runnable receiveLoopRunnable;
   private boolean stopped;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Inbox PoisonPill() {
      return MessageLoop$.MODULE$.PoisonPill();
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

   private LinkedBlockingQueue active() {
      return this.active;
   }

   public Runnable receiveLoopRunnable() {
      return this.receiveLoopRunnable;
   }

   public abstract ExecutorService threadpool();

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   public abstract void post(final String endpointName, final InboxMessage message);

   public abstract void unregister(final String name);

   public void stop() {
      synchronized(this){}

      try {
         if (!this.stopped()) {
            this.setActive(MessageLoop$.MODULE$.PoisonPill());
            this.threadpool().shutdown();
            this.stopped_$eq(true);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      this.threadpool().awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
   }

   public final void setActive(final Inbox inbox) {
      this.active().offer(inbox);
   }

   public void org$apache$spark$rpc$netty$MessageLoop$$receiveLoop() {
      try {
         while(true) {
            try {
               Inbox inbox;
               label81: {
                  inbox = (Inbox)this.active().take();
                  Inbox var3 = MessageLoop$.MODULE$.PoisonPill();
                  if (inbox == null) {
                     if (var3 != null) {
                        break label81;
                     }
                  } else if (!inbox.equals(var3)) {
                     break label81;
                  }

                  this.setActive(MessageLoop$.MODULE$.PoisonPill());
                  return;
               }

               inbox.process(this.dispatcher);
            } catch (Throwable var14) {
               if (var14 == null || !.MODULE$.apply(var14)) {
                  throw var14;
               }

               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var14.getMessage())})))), var14);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }
      } catch (InterruptedException var15) {
      } catch (Throwable var16) {
         try {
            this.threadpool().execute(this.receiveLoopRunnable());
         } finally {
            ;
         }

         throw var16;
      }
   }

   public MessageLoop(final Dispatcher dispatcher) {
      this.dispatcher = dispatcher;
      Logging.$init$(this);
      this.active = new LinkedBlockingQueue();
      this.receiveLoopRunnable = new Runnable() {
         // $FF: synthetic field
         private final MessageLoop $outer;

         public void run() {
            this.$outer.org$apache$spark$rpc$netty$MessageLoop$$receiveLoop();
         }

         public {
            if (MessageLoop.this == null) {
               throw null;
            } else {
               this.$outer = MessageLoop.this;
            }
         }
      };
      this.stopped = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
