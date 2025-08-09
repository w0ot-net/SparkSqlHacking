package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.spark.SparkEnv;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.scheduler.LiveListenerBus;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.scheduler.SparkListenerEvent;
import org.apache.spark.util.ListenerBus;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f!\u0002\u0010 \u0001\u0005J\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u000b\u0005\u0003A\u0011\u0001\"\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b=\u0003A\u0011\t)\t\u000bU\u0003A\u0011\u000b,\t\u000bi\u0003A\u0011A.\t\u000bq\u0003A\u0011A.\u0007\tu\u0003AI\u0018\u0005\tc\"\u0011)\u001a!C\u0001e\"A1\u000f\u0003B\tB\u0003%\u0011\bC\u0003B\u0011\u0011\u0005A\u000f\u0003\u0004y\u0011\u0011E3%\u001f\u0005\b{\"\t\t\u0011\"\u0001\u007f\u0011%\t\t\u0001CI\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\u001a!\t\t\u0011\"\u0011\u0002\u001c!I\u0011Q\u0006\u0005\u0002\u0002\u0013\u0005\u0011q\u0006\u0005\n\u0003oA\u0011\u0011!C\u0001\u0003sA\u0011\"!\u0012\t\u0003\u0003%\t%a\u0012\t\u0013\u0005U\u0003\"!A\u0005\u0002\u0005]\u0003\"CA.\u0011\u0005\u0005I\u0011IA/\u0011%\t\t\u0007CA\u0001\n\u0003\n\u0019\u0007C\u0005\u0002f!\t\t\u0011\"\u0011\u0002h!I\u0011\u0011\u000e\u0005\u0002\u0002\u0013\u0005\u00131N\u0004\n\u0003_\u0002\u0011\u0011!E\u0005\u0003c2\u0001\"\u0018\u0001\u0002\u0002#%\u00111\u000f\u0005\u0007\u0003f!\t!a#\t\u0013\u0005\u0015\u0014$!A\u0005F\u0005\u001d\u0004\"CAG3\u0005\u0005I\u0011QAH\u0011%\t\u0019*GA\u0001\n\u0003\u000b)J\u0001\u000bTiJ,\u0017-\\5oO2K7\u000f^3oKJ\u0014Uo\u001d\u0006\u0003A\u0005\n\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\t\u001a\u0013!C:ue\u0016\fW.\u001b8h\u0015\t!S%A\u0003ta\u0006\u00148N\u0003\u0002'O\u00051\u0011\r]1dQ\u0016T\u0011\u0001K\u0001\u0004_J<7c\u0001\u0001+_A\u00111&L\u0007\u0002Y)\u0011\u0001eI\u0005\u0003]1\u0012Qb\u00159be.d\u0015n\u001d;f]\u0016\u0014\b\u0003\u0002\u00194kej\u0011!\r\u0006\u0003e\r\nA!\u001e;jY&\u0011A'\r\u0002\f\u0019&\u001cH/\u001a8fe\n+8\u000f\u0005\u00027o5\tq$\u0003\u00029?\t\t2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:\u0011\u0005YR\u0014BA\u001e \u0005Y\u0019FO]3b[&tw\rT5ti\u0016tWM]#wK:$\u0018\u0001E:qCJ\\G*[:uK:,'OQ;t\u0007\u0001\u0001\"aK \n\u0005\u0001c#a\u0004'jm\u0016d\u0015n\u001d;f]\u0016\u0014()^:\u0002\rqJg.\u001b;?)\t\u0019E\t\u0005\u00027\u0001!)AH\u0001a\u0001}\u0005!\u0001o\\:u)\t9U\n\u0005\u0002I\u00176\t\u0011JC\u0001K\u0003\u0015\u00198-\u00197b\u0013\ta\u0015J\u0001\u0003V]&$\b\"\u0002(\u0004\u0001\u0004I\u0014!B3wK:$\u0018\u0001D8o\u001fRDWM]#wK:$HCA$R\u0011\u0015qE\u00011\u0001S!\tY3+\u0003\u0002UY\t\u00112\u000b]1sW2K7\u000f^3oKJ,e/\u001a8u\u0003-!w\u000eU8ti\u00163XM\u001c;\u0015\u0007\u001d;\u0016\fC\u0003Y\u000b\u0001\u0007Q'\u0001\u0005mSN$XM\\3s\u0011\u0015qU\u00011\u0001:\u0003\u0015\u0019H/\u0019:u)\u00059\u0015\u0001B:u_B\u0014Qd\u0016:baB,Gm\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s\u000bZ,g\u000e^\n\u0006\u0011}\u0013&-\u001a\t\u0003\u0011\u0002L!!Y%\u0003\r\u0005s\u0017PU3g!\tA5-\u0003\u0002e\u0013\n9\u0001K]8ek\u000e$\bC\u00014o\u001d\t9GN\u0004\u0002iW6\t\u0011N\u0003\u0002k{\u00051AH]8pizJ\u0011AS\u0005\u0003[&\u000bq\u0001]1dW\u0006<W-\u0003\u0002pa\na1+\u001a:jC2L'0\u00192mK*\u0011Q.S\u0001\u0017gR\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014XI^3oiV\t\u0011(A\ftiJ,\u0017-\\5oO2K7\u000f^3oKJ,e/\u001a8uAQ\u0011Qo\u001e\t\u0003m\"i\u0011\u0001\u0001\u0005\u0006c.\u0001\r!O\u0001\tY><WI^3oiV\t!\u0010\u0005\u0002Iw&\u0011A0\u0013\u0002\b\u0005>|G.Z1o\u0003\u0011\u0019w\u000e]=\u0015\u0005U|\bbB9\u000e!\u0003\u0005\r!O\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t)AK\u0002:\u0003\u000fY#!!\u0003\u0011\t\u0005-\u0011QC\u0007\u0003\u0003\u001bQA!a\u0004\u0002\u0012\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003'I\u0015AC1o]>$\u0018\r^5p]&!\u0011qCA\u0007\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005u\u0001\u0003BA\u0010\u0003Si!!!\t\u000b\t\u0005\r\u0012QE\u0001\u0005Y\u0006twM\u0003\u0002\u0002(\u0005!!.\u0019<b\u0013\u0011\tY#!\t\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\t\u0004E\u0002I\u0003gI1!!\u000eJ\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tY$!\u0011\u0011\u0007!\u000bi$C\u0002\u0002@%\u00131!\u00118z\u0011%\t\u0019%EA\u0001\u0002\u0004\t\t$A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003\u0013\u0002b!a\u0013\u0002R\u0005mRBAA'\u0015\r\ty%S\u0001\u000bG>dG.Z2uS>t\u0017\u0002BA*\u0003\u001b\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019!0!\u0017\t\u0013\u0005\r3#!AA\u0002\u0005m\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\b\u0002`!I\u00111\t\u000b\u0002\u0002\u0003\u0007\u0011\u0011G\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011G\u0001\ti>\u001cFO]5oOR\u0011\u0011QD\u0001\u0007KF,\u0018\r\\:\u0015\u0007i\fi\u0007C\u0005\u0002D]\t\t\u00111\u0001\u0002<\u0005irK]1qa\u0016$7\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002w3M)\u0011$!\u001e\u0002\u0002B1\u0011qOA?sUl!!!\u001f\u000b\u0007\u0005m\u0014*A\u0004sk:$\u0018.\\3\n\t\u0005}\u0014\u0011\u0010\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BAB\u0003\u0013k!!!\"\u000b\t\u0005\u001d\u0015QE\u0001\u0003S>L1a\\AC)\t\t\t(A\u0003baBd\u0017\u0010F\u0002v\u0003#CQ!\u001d\u000fA\u0002e\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0018\u0006u\u0005\u0003\u0002%\u0002\u001afJ1!a'J\u0005\u0019y\u0005\u000f^5p]\"A\u0011qT\u000f\u0002\u0002\u0003\u0007Q/A\u0002yIA\u0002"
)
public class StreamingListenerBus extends SparkListener implements ListenerBus {
   private volatile WrappedStreamingListenerEvent$ WrappedStreamingListenerEvent$module;
   private final LiveListenerBus sparkListenerBus;
   private CopyOnWriteArrayList org$apache$spark$util$ListenerBus$$listenersPlusTimers;
   private SparkEnv org$apache$spark$util$ListenerBus$$env;
   private boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled;
   private long org$apache$spark$util$ListenerBus$$logSlowEventThreshold;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

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

   public boolean isIgnorableException(final Throwable e) {
      return ListenerBus.isIgnorableException$(this, e);
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

   private WrappedStreamingListenerEvent$ WrappedStreamingListenerEvent() {
      if (this.WrappedStreamingListenerEvent$module == null) {
         this.WrappedStreamingListenerEvent$lzycompute$1();
      }

      return this.WrappedStreamingListenerEvent$module;
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

   public void post(final StreamingListenerEvent event) {
      this.sparkListenerBus.post(new WrappedStreamingListenerEvent(event));
   }

   public void onOtherEvent(final SparkListenerEvent event) {
      if (event instanceof WrappedStreamingListenerEvent var4 && ((WrappedStreamingListenerEvent)event).org$apache$spark$streaming$scheduler$StreamingListenerBus$WrappedStreamingListenerEvent$$$outer() == this) {
         StreamingListenerEvent e = var4.streamingListenerEvent();
         this.postToAll(e);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void doPostEvent(final StreamingListener listener, final StreamingListenerEvent event) {
      if (event instanceof StreamingListenerReceiverStarted var5) {
         listener.onReceiverStarted(var5);
         BoxedUnit var22 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerReceiverError var6) {
         listener.onReceiverError(var6);
         BoxedUnit var21 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerReceiverStopped var7) {
         listener.onReceiverStopped(var7);
         BoxedUnit var20 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerBatchSubmitted var8) {
         listener.onBatchSubmitted(var8);
         BoxedUnit var19 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerBatchStarted var9) {
         listener.onBatchStarted(var9);
         BoxedUnit var18 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerBatchCompleted var10) {
         listener.onBatchCompleted(var10);
         BoxedUnit var17 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerOutputOperationStarted var11) {
         listener.onOutputOperationStarted(var11);
         BoxedUnit var16 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerOutputOperationCompleted var12) {
         listener.onOutputOperationCompleted(var12);
         BoxedUnit var15 = BoxedUnit.UNIT;
      } else if (event instanceof StreamingListenerStreamingStarted var13) {
         listener.onStreamingStarted(var13);
         BoxedUnit var14 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void start() {
      this.sparkListenerBus.addToStatusQueue(this);
   }

   public void stop() {
      this.sparkListenerBus.removeListener(this);
   }

   private final void WrappedStreamingListenerEvent$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.WrappedStreamingListenerEvent$module == null) {
            this.WrappedStreamingListenerEvent$module = new WrappedStreamingListenerEvent$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public StreamingListenerBus(final LiveListenerBus sparkListenerBus) {
      this.sparkListenerBus = sparkListenerBus;
      Logging.$init$(this);
      ListenerBus.$init$(this);
      Statics.releaseFence();
   }

   private class WrappedStreamingListenerEvent implements SparkListenerEvent, Product, Serializable {
      private final StreamingListenerEvent streamingListenerEvent;
      // $FF: synthetic field
      public final StreamingListenerBus $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public StreamingListenerEvent streamingListenerEvent() {
         return this.streamingListenerEvent;
      }

      public boolean logEvent() {
         return false;
      }

      public WrappedStreamingListenerEvent copy(final StreamingListenerEvent streamingListenerEvent) {
         return this.org$apache$spark$streaming$scheduler$StreamingListenerBus$WrappedStreamingListenerEvent$$$outer().new WrappedStreamingListenerEvent(streamingListenerEvent);
      }

      public StreamingListenerEvent copy$default$1() {
         return this.streamingListenerEvent();
      }

      public String productPrefix() {
         return "WrappedStreamingListenerEvent";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.streamingListenerEvent();
            }
            default -> {
               return Statics.ioobe(x$1);
            }
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof WrappedStreamingListenerEvent;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "streamingListenerEvent";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label52: {
               if (x$1 instanceof WrappedStreamingListenerEvent && ((WrappedStreamingListenerEvent)x$1).org$apache$spark$streaming$scheduler$StreamingListenerBus$WrappedStreamingListenerEvent$$$outer() == this.org$apache$spark$streaming$scheduler$StreamingListenerBus$WrappedStreamingListenerEvent$$$outer()) {
                  label42: {
                     WrappedStreamingListenerEvent var4 = (WrappedStreamingListenerEvent)x$1;
                     StreamingListenerEvent var10000 = this.streamingListenerEvent();
                     StreamingListenerEvent var5 = var4.streamingListenerEvent();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label42;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label42;
                     }

                     if (var4.canEqual(this)) {
                        break label52;
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      // $FF: synthetic method
      public StreamingListenerBus org$apache$spark$streaming$scheduler$StreamingListenerBus$WrappedStreamingListenerEvent$$$outer() {
         return this.$outer;
      }

      public WrappedStreamingListenerEvent(final StreamingListenerEvent streamingListenerEvent) {
         this.streamingListenerEvent = streamingListenerEvent;
         if (StreamingListenerBus.this == null) {
            throw null;
         } else {
            this.$outer = StreamingListenerBus.this;
            super();
            SparkListenerEvent.$init$(this);
            Product.$init$(this);
         }
      }
   }

   private class WrappedStreamingListenerEvent$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final StreamingListenerBus $outer;

      public final String toString() {
         return "WrappedStreamingListenerEvent";
      }

      public WrappedStreamingListenerEvent apply(final StreamingListenerEvent streamingListenerEvent) {
         return this.$outer.new WrappedStreamingListenerEvent(streamingListenerEvent);
      }

      public Option unapply(final WrappedStreamingListenerEvent x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.streamingListenerEvent()));
      }

      public WrappedStreamingListenerEvent$() {
         if (StreamingListenerBus.this == null) {
            throw null;
         } else {
            this.$outer = StreamingListenerBus.this;
            super();
         }
      }
   }
}
