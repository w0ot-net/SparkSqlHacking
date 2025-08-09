package org.apache.spark.scheduler;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Timer;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.util.ListenerBus;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t\ra\u0001B\u00193\tmB\u0001\u0002\u0014\u0001\u0003\u0006\u0004%\t!\u0014\u0005\t3\u0002\u0011\t\u0011)A\u0005\u001d\"A!\f\u0001B\u0001B\u0003%1\f\u0003\u0005`\u0001\t\u0005\t\u0015!\u0003a\u0011!\u0019\u0007A!A!\u0002\u0013!\u0007\"B4\u0001\t\u0003A\u0007B\u00028\u0001\t\u0003\u0011t\u000eC\u0004t\u0001\t\u0007I\u0011\u0002;\t\u000f\u0005\u0015\u0001\u0001)A\u0005k\"I\u0011q\u0001\u0001C\u0002\u0013%\u0011\u0011\u0002\u0005\t\u0003/\u0001\u0001\u0015!\u0003\u0002\f!I\u0011\u0011\u0004\u0001C\u0002\u0013%\u0011\u0011\u0002\u0005\t\u00037\u0001\u0001\u0015!\u0003\u0002\f!I\u0011Q\u0004\u0001A\u0002\u0013%\u0011q\u0004\u0005\n\u0003O\u0001\u0001\u0019!C\u0005\u0003SA\u0001\"!\u000e\u0001A\u0003&\u0011\u0011\u0005\u0005\n\u0003\u007f\u0001!\u0019!C\u0005\u0003\u0013A\u0001\"!\u0011\u0001A\u0003%\u00111\u0002\u0005\n\u0003\u0007\u0002!\u0019!C\u0005\u0003\u000bB\u0001\"!\u0014\u0001A\u0003%\u0011q\t\u0005\n\u0003\u001f\u0002\u0001\u0019!C\u0005\u0003#B\u0011\"!\u0017\u0001\u0001\u0004%I!a\u0017\t\u0011\u0005}\u0003\u0001)Q\u0005\u0003'B\u0011\"!\u0019\u0001\u0005\u0004%I!!\u0012\t\u0011\u0005\r\u0004\u0001)A\u0005\u0003\u000fB\u0011\"!\u001a\u0001\u0005\u0004%I!!\u0012\t\u0011\u0005\u001d\u0004\u0001)A\u0005\u0003\u000fB\u0011\"!\u001b\u0001\u0005\u0004%I!a\u001b\t\u0011\u0005}\u0004\u0001)A\u0005\u0003[B\u0011\"!!\u0001\u0005\u0004%I!a!\t\u0011\u0005-\u0005\u0001)A\u0005\u0003\u000bC\u0011\"!$\u0001\u0005\u0004%I!a$\t\u0011\u0005u\u0005\u0001)A\u0005\u0003#Cq!a(\u0001\t\u0013\t\t\u000bC\u0004\u0002$\u0002!\t&!*\t\u0011\u0005]\u0006\u0001\"\u00013\u0003sC\u0001\"!0\u0001\t\u0003\u0011\u0014\u0011\u0015\u0005\b\u0003\u007f\u0003A\u0011AAa\u0011\u001d\t9\r\u0001C\u0001\u0003\u0013Dq!!6\u0001\t\u0003\n9\u000e\u0003\b\u0002\\\u0002\u0001\n1!A\u0001\n\u0013\ti.!9\b\u000f\u0005-(\u0007#\u0003\u0002n\u001a1\u0011G\rE\u0005\u0003_DaaZ\u0016\u0005\u0002\u0005E\b\"CAzW\t\u0007I\u0011AA{\u0011!\tip\u000bQ\u0001\n\u0005]\b\u0002CA\u0000W\t\u0007I\u0011A8\t\u000f\t\u00051\u0006)A\u0005a\ny\u0011i]=oG\u00163XM\u001c;Rk\u0016,XM\u0003\u00024i\u0005I1o\u00195fIVdWM\u001d\u0006\u0003kY\nQa\u001d9be.T!a\u000e\u001d\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0014aA8sO\u000e\u00011\u0003\u0002\u0001=\u0005\u001a\u0003\"!\u0010!\u000e\u0003yR\u0011aP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0003z\u0012a!\u00118z%\u00164\u0007CA\"E\u001b\u0005\u0011\u0014BA#3\u0005A\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\n+8\u000f\u0005\u0002H\u00156\t\u0001J\u0003\u0002Ji\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002L\u0011\n9Aj\\4hS:<\u0017\u0001\u00028b[\u0016,\u0012A\u0014\t\u0003\u001fZs!\u0001\u0015+\u0011\u0005EsT\"\u0001*\u000b\u0005MS\u0014A\u0002\u001fs_>$h(\u0003\u0002V}\u00051\u0001K]3eK\u001aL!a\u0016-\u0003\rM#(/\u001b8h\u0015\t)f(A\u0003oC6,\u0007%\u0001\u0003d_:4\u0007C\u0001/^\u001b\u0005!\u0014B\u000105\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0004nKR\u0014\u0018nY:\u0011\u0005\r\u000b\u0017B\u000123\u0005Ya\u0015N^3MSN$XM\\3s\u0005V\u001cX*\u001a;sS\u000e\u001c\u0018a\u00012vgB\u00111)Z\u0005\u0003MJ\u0012q\u0002T5wK2K7\u000f^3oKJ\u0014Uo]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b%T7\u000e\\7\u0011\u0005\r\u0003\u0001\"\u0002'\u0007\u0001\u0004q\u0005\"\u0002.\u0007\u0001\u0004Y\u0006\"B0\u0007\u0001\u0004\u0001\u0007\"B2\u0007\u0001\u0004!\u0017\u0001C2ba\u0006\u001c\u0017\u000e^=\u0016\u0003A\u0004\"!P9\n\u0005It$aA%oi\u0006QQM^3oiF+X-^3\u0016\u0003U\u00042A^?\u0000\u001b\u00059(B\u0001=z\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0003un\fA!\u001e;jY*\tA0\u0001\u0003kCZ\f\u0017B\u0001@x\u0005Ma\u0015N\\6fI\ncwnY6j]\u001e\fV/Z;f!\r\u0019\u0015\u0011A\u0005\u0004\u0003\u0007\u0011$AE*qCJ\\G*[:uK:,'/\u0012<f]R\f1\"\u001a<f]R\fV/Z;fA\u0005QQM^3oi\u000e{WO\u001c;\u0016\u0005\u0005-\u0001\u0003BA\u0007\u0003'i!!a\u0004\u000b\u0007\u0005Eq/\u0001\u0004bi>l\u0017nY\u0005\u0005\u0003+\tyA\u0001\u0006Bi>l\u0017n\u0019'p]\u001e\f1\"\u001a<f]R\u001cu.\u001e8uA\u0005!BM]8qa\u0016$WI^3oiN\u001cu.\u001e8uKJ\fQ\u0003\u001a:paB,G-\u0012<f]R\u001c8i\\;oi\u0016\u0014\b%\u0001\rmCN$HI]8qa\u0016$WI^3oiN\u001cu.\u001e8uKJ,\"!!\t\u0011\u0007u\n\u0019#C\u0002\u0002&y\u0012A\u0001T8oO\u0006aB.Y:u\tJ|\u0007\u000f]3e\u000bZ,g\u000e^:D_VtG/\u001a:`I\u0015\fH\u0003BA\u0016\u0003c\u00012!PA\u0017\u0013\r\tyC\u0010\u0002\u0005+:LG\u000fC\u0005\u00024=\t\t\u00111\u0001\u0002\"\u0005\u0019\u0001\u0010J\u0019\u000231\f7\u000f\u001e#s_B\u0004X\rZ#wK:$8oQ8v]R,'\u000f\t\u0015\u0004!\u0005e\u0002cA\u001f\u0002<%\u0019\u0011Q\b \u0003\u0011Y|G.\u0019;jY\u0016\f1\u0003\\1tiJ+\u0007o\u001c:u)&lWm\u001d;b[B\fA\u0003\\1tiJ+\u0007o\u001c:u)&lWm\u001d;b[B\u0004\u0013a\u00047pO\u0012\u0013x\u000e\u001d9fI\u00163XM\u001c;\u0016\u0005\u0005\u001d\u0003\u0003BA\u0007\u0003\u0013JA!a\u0013\u0002\u0010\ti\u0011\t^8nS\u000e\u0014un\u001c7fC:\f\u0001\u0003\\8h\tJ|\u0007\u000f]3e\u000bZ,g\u000e\u001e\u0011\u0002\u0005M\u001cWCAA*!\ra\u0016QK\u0005\u0004\u0003/\"$\u0001D*qCJ\\7i\u001c8uKb$\u0018AB:d?\u0012*\u0017\u000f\u0006\u0003\u0002,\u0005u\u0003\"CA\u001a-\u0005\u0005\t\u0019AA*\u0003\r\u00198\rI\u0001\bgR\f'\u000f^3e\u0003!\u0019H/\u0019:uK\u0012\u0004\u0013aB:u_B\u0004X\rZ\u0001\tgR|\u0007\u000f]3eA\u0005iAM]8qa\u0016$WI^3oiN,\"!!\u001c\u0011\t\u0005=\u00141P\u0007\u0003\u0003cR1aXA:\u0015\u0011\t)(a\u001e\u0002\u0011\r|G-\u00195bY\u0016T!!!\u001f\u0002\u0007\r|W.\u0003\u0003\u0002~\u0005E$aB\"pk:$XM]\u0001\u000fIJ|\u0007\u000f]3e\u000bZ,g\u000e^:!\u00039\u0001(o\\2fgNLgn\u001a+j[\u0016,\"!!\"\u0011\t\u0005=\u0014qQ\u0005\u0005\u0003\u0013\u000b\tHA\u0003US6,'/A\bqe>\u001cWm]:j]\u001e$\u0016.\\3!\u00039!\u0017n\u001d9bi\u000eDG\u000b\u001b:fC\u0012,\"!!%\u0011\t\u0005M\u0015\u0011T\u0007\u0003\u0003+S1!a&|\u0003\u0011a\u0017M\\4\n\t\u0005m\u0015Q\u0013\u0002\u0007)\"\u0014X-\u00193\u0002\u001f\u0011L7\u000f]1uG\"$\u0006N]3bI\u0002\n\u0001\u0002Z5ta\u0006$8\r\u001b\u000b\u0003\u0003W\t\u0001bZ3u)&lWM\u001d\u000b\u0005\u0003O\u000bi\u000bE\u0003>\u0003S\u000b))C\u0002\u0002,z\u0012aa\u00149uS>t\u0007bBAXG\u0001\u0007\u0011\u0011W\u0001\tY&\u001cH/\u001a8feB\u00191)a-\n\u0007\u0005U&G\u0001\fTa\u0006\u00148\u000eT5ti\u0016tWM]%oi\u0016\u0014h-Y2f\u0003\u0015\u0019H/\u0019:u)\u0011\tY#a/\t\u000f\u0005=C\u00051\u0001\u0002T\u0005!1\u000f^8q\u0003\u0011\u0001xn\u001d;\u0015\t\u0005-\u00121\u0019\u0005\u0007\u0003\u000b4\u0003\u0019A@\u0002\u000b\u00154XM\u001c;\u0002\u001d]\f\u0017\u000e^+oi&dW)\u001c9usR!\u00111ZAi!\ri\u0014QZ\u0005\u0004\u0003\u001ft$a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003'<\u0003\u0019AA\u0011\u0003!!W-\u00193mS:,\u0017!\u0006:f[>4X\rT5ti\u0016tWM](o\u000bJ\u0014xN\u001d\u000b\u0005\u0003W\tI\u000eC\u0004\u00020\"\u0002\r!!-\u0002\u001fM,\b/\u001a:%a>\u001cH\u000fV8BY2$B!a\u000b\u0002`\"1\u0011QY\u0015A\u0002}LA!a9\u0002f\u0006I\u0001o\\:u)>\fE\u000e\\\u0005\u0005\u0003O\fIOA\u0006MSN$XM\\3s\u0005V\u001c(B\u0001>5\u0003=\t5/\u001f8d\u000bZ,g\u000e^)vKV,\u0007CA\",'\tYC\b\u0006\u0002\u0002n\u0006Y\u0001kT%T\u001f:{\u0006+\u0013'M+\t\t9P\u0005\u0003\u0002zrzhABA~]\u0001\t9P\u0001\u0007=e\u00164\u0017N\\3nK:$h(\u0001\u0007Q\u001f&\u001bvJT0Q\u00132c\u0005%\u0001\tM\u001f\u001e;\u0015JT$`\u0013:#VI\u0015,B\u0019\u0006\tBjT$H\u0013:;u,\u0013(U\u000bJ3\u0016\t\u0014\u0011"
)
public class AsyncEventQueue implements SparkListenerBus {
   private final String name;
   private final SparkConf conf;
   private final LiveListenerBusMetrics metrics;
   private final LiveListenerBus bus;
   private final LinkedBlockingQueue org$apache$spark$scheduler$AsyncEventQueue$$eventQueue;
   private final AtomicLong eventCount;
   private final AtomicLong droppedEventsCounter;
   private volatile long lastDroppedEventsCounter;
   private final AtomicLong lastReportTimestamp;
   private final AtomicBoolean logDroppedEvent;
   private SparkContext org$apache$spark$scheduler$AsyncEventQueue$$sc;
   private final AtomicBoolean started;
   private final AtomicBoolean stopped;
   private final Counter droppedEvents;
   private final Timer processingTime;
   private final Thread dispatchThread;
   private CopyOnWriteArrayList org$apache$spark$util$ListenerBus$$listenersPlusTimers;
   private SparkEnv org$apache$spark$util$ListenerBus$$env;
   private boolean org$apache$spark$util$ListenerBus$$logSlowEventEnabled;
   private long org$apache$spark$util$ListenerBus$$logSlowEventThreshold;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile byte bitmap$0;

   public static int LOGGING_INTERVAL() {
      return AsyncEventQueue$.MODULE$.LOGGING_INTERVAL();
   }

   public static SparkListenerEvent POISON_PILL() {
      return AsyncEventQueue$.MODULE$.POISON_PILL();
   }

   public void doPostEvent(final SparkListenerInterface listener, final SparkListenerEvent event) {
      SparkListenerBus.doPostEvent$(this, listener, event);
   }

   public List listeners() {
      return ListenerBus.listeners$(this);
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

   // $FF: synthetic method
   private void super$postToAll(final SparkListenerEvent event) {
      ListenerBus.postToAll$(this, event);
   }

   public String name() {
      return this.name;
   }

   public int capacity() {
      SparkConf var10000 = this.conf;
      String var10001 = org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_EVENT_QUEUE_PREFIX();
      int queueSize = var10000.getInt(var10001 + "." + this.name() + ".capacity", BoxesRunTime.unboxToInt(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_EVENT_QUEUE_CAPACITY())));
      .MODULE$.assert(queueSize > 0, () -> {
         String var10000 = this.name();
         return "capacity for event queue " + var10000 + " must be greater than 0, but " + queueSize + " is configured.";
      });
      return queueSize;
   }

   public LinkedBlockingQueue org$apache$spark$scheduler$AsyncEventQueue$$eventQueue() {
      return this.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue;
   }

   private AtomicLong eventCount() {
      return this.eventCount;
   }

   private AtomicLong droppedEventsCounter() {
      return this.droppedEventsCounter;
   }

   private long lastDroppedEventsCounter() {
      return this.lastDroppedEventsCounter;
   }

   private void lastDroppedEventsCounter_$eq(final long x$1) {
      this.lastDroppedEventsCounter = x$1;
   }

   private AtomicLong lastReportTimestamp() {
      return this.lastReportTimestamp;
   }

   private AtomicBoolean logDroppedEvent() {
      return this.logDroppedEvent;
   }

   public SparkContext org$apache$spark$scheduler$AsyncEventQueue$$sc() {
      return this.org$apache$spark$scheduler$AsyncEventQueue$$sc;
   }

   private void sc_$eq(final SparkContext x$1) {
      this.org$apache$spark$scheduler$AsyncEventQueue$$sc = x$1;
   }

   private AtomicBoolean started() {
      return this.started;
   }

   private AtomicBoolean stopped() {
      return this.stopped;
   }

   private Counter droppedEvents() {
      return this.droppedEvents;
   }

   private Timer processingTime() {
      return this.processingTime;
   }

   private Thread dispatchThread() {
      return this.dispatchThread;
   }

   public void org$apache$spark$scheduler$AsyncEventQueue$$dispatch() {
      LiveListenerBus$.MODULE$.withinListenerThread().withValue(BoxesRunTime.boxToBoolean(true), (JFunction0.mcJ.sp)() -> {
         SparkListenerEvent next = (SparkListenerEvent)this.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue().take();

         while(true) {
            SparkListenerEvent var2 = AsyncEventQueue$.MODULE$.POISON_PILL();
            if (next == null) {
               if (var2 == null) {
                  break;
               }
            } else if (next.equals(var2)) {
               break;
            }

            Timer.Context ctx = this.processingTime().time();

            try {
               this.super$postToAll(next);
            } finally {
               ctx.stop();
            }

            this.eventCount().decrementAndGet();
            next = (SparkListenerEvent)this.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue().take();
         }

         return this.eventCount().decrementAndGet();
      });
   }

   public Option getTimer(final SparkListenerInterface listener) {
      return this.metrics.getTimerForListenerClass(listener.getClass().asSubclass(SparkListenerInterface.class));
   }

   public void start(final SparkContext sc) {
      if (this.started().compareAndSet(false, true)) {
         this.sc_$eq(sc);
         this.dispatchThread().start();
      } else {
         throw new IllegalStateException(this.name() + " already started!");
      }
   }

   public void stop() {
      if (!this.started().get()) {
         throw new IllegalStateException("Attempted to stop " + this.name() + " that has not yet started!");
      } else {
         if (this.stopped().compareAndSet(false, true)) {
            this.eventCount().incrementAndGet();
            this.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue().put(AsyncEventQueue$.MODULE$.POISON_PILL());
         }

         label20: {
            Thread var10000 = Thread.currentThread();
            Thread var1 = this.dispatchThread();
            if (var10000 == null) {
               if (var1 != null) {
                  break label20;
               }
            } else if (!var10000.equals(var1)) {
               break label20;
            }

            return;
         }

         long exitTimeoutMs = BoxesRunTime.unboxToLong(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.LISTENER_BUS_EXIT_TIMEOUT()));
         this.dispatchThread().join(exitTimeoutMs);
      }
   }

   public void post(final SparkListenerEvent event) {
      if (!this.stopped().get()) {
         this.eventCount().incrementAndGet();
         if (!this.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue().offer(event)) {
            this.eventCount().decrementAndGet();
            this.droppedEvents().inc();
            this.droppedEventsCounter().incrementAndGet();
            if (this.logDroppedEvent().compareAndSet(false, true)) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropping event from queue ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT_QUEUE..MODULE$, this.name())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"This likely means one of the listeners is too slow and cannot keep up with "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the rate at which tasks are being started by the scheduler."})))).log(scala.collection.immutable.Nil..MODULE$))));
            }

            this.logTrace((Function0)(() -> "Dropping event " + event));
            long droppedEventsCount = this.droppedEventsCounter().get();
            long droppedCountIncreased = droppedEventsCount - this.lastDroppedEventsCounter();
            long lastReportTime = this.lastReportTimestamp().get();
            long curTime = System.currentTimeMillis();
            if (droppedCountIncreased > 0L && curTime - lastReportTime >= (long)AsyncEventQueue$.MODULE$.LOGGING_INTERVAL()) {
               if (this.lastReportTimestamp().compareAndSet(lastReportTime, curTime)) {
                  Date previous = new Date(lastReportTime);
                  this.lastDroppedEventsCounter_$eq(droppedEventsCount);
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropped ", " events from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EVENTS..MODULE$, BoxesRunTime.boxToLong(droppedCountIncreased))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " since "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EVENT_NAME..MODULE$, this.name())})))).$plus(lastReportTime == 0L ? this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the application started"})))).log(scala.collection.immutable.Nil..MODULE$) : this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME..MODULE$, previous)}))))));
               }
            }
         }
      }
   }

   public boolean waitUntilEmpty(final long deadline) {
      while(this.eventCount().get() != 0L) {
         if (System.currentTimeMillis() > deadline) {
            return false;
         }

         Thread.sleep(10L);
      }

      return true;
   }

   public void removeListenerOnError(final SparkListenerInterface listener) {
      this.bus.removeListener(listener);
   }

   public AsyncEventQueue(final String name, final SparkConf conf, final LiveListenerBusMetrics metrics, final LiveListenerBus bus) {
      this.name = name;
      this.conf = conf;
      this.metrics = metrics;
      this.bus = bus;
      Logging.$init$(this);
      ListenerBus.$init$(this);
      SparkListenerBus.$init$(this);
      this.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue = new LinkedBlockingQueue(this.capacity());
      this.eventCount = new AtomicLong();
      this.droppedEventsCounter = new AtomicLong(0L);
      this.lastDroppedEventsCounter = 0L;
      this.lastReportTimestamp = new AtomicLong(0L);
      this.logDroppedEvent = new AtomicBoolean(false);
      this.org$apache$spark$scheduler$AsyncEventQueue$$sc = null;
      this.started = new AtomicBoolean(false);
      this.stopped = new AtomicBoolean(false);
      this.droppedEvents = metrics.metricRegistry().counter("queue." + name + ".numDroppedEvents");
      this.processingTime = metrics.metricRegistry().timer("queue." + name + ".listenerProcessingTime");
      metrics.metricRegistry().remove("queue." + name + ".size");
      metrics.metricRegistry().register("queue." + name + ".size", new Gauge() {
         // $FF: synthetic field
         private final AsyncEventQueue $outer;

         public int getValue() {
            return this.$outer.org$apache$spark$scheduler$AsyncEventQueue$$eventQueue().size();
         }

         public {
            if (AsyncEventQueue.this == null) {
               throw null;
            } else {
               this.$outer = AsyncEventQueue.this;
            }
         }
      });
      this.dispatchThread = new Thread() {
         // $FF: synthetic field
         private final AsyncEventQueue $outer;

         public void run() {
            Utils$.MODULE$.tryOrStopSparkContext(this.$outer.org$apache$spark$scheduler$AsyncEventQueue$$sc(), (JFunction0.mcV.sp)() -> this.$outer.org$apache$spark$scheduler$AsyncEventQueue$$dispatch());
         }

         public {
            if (AsyncEventQueue.this == null) {
               throw null;
            } else {
               this.$outer = AsyncEventQueue.this;
               this.setDaemon(true);
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
