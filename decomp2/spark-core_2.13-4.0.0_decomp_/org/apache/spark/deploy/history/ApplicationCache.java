package org.apache.spark.deploy.history;

import com.codahale.metrics.Timer;
import jakarta.servlet.DispatcherType;
import java.lang.invoke.SerializedLambda;
import java.util.EnumSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import org.sparkproject.guava.cache.RemovalListener;
import org.sparkproject.guava.cache.RemovalNotification;
import org.sparkproject.guava.util.concurrent.UncheckedExecutionException;
import org.sparkproject.jetty.servlet.FilterHolder;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Option.;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\tUb!B\u000e\u001d\u0001q1\u0003\u0002C\u001a\u0001\u0005\u000b\u0007I\u0011A\u001b\t\u0011i\u0002!\u0011!Q\u0001\nYB\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u0001\u0002\u0011\t\u0011)A\u0005{!A\u0011\t\u0001BC\u0002\u0013\u0005!\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003D\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d\u0001\u0006A1A\u0005\nECa!\u0019\u0001!\u0002\u0013\u0011\u0006b\u00022\u0001\u0005\u0004%Ia\u0019\u0005\u0007g\u0002\u0001\u000b\u0011\u00023\t\u000fQ\u0004!\u0019!C\u0005k\"1\u0011\u0010\u0001Q\u0001\nYD\u0011\"a\u0002\u0001\u0005\u0004%I!!\u0003\t\u0011\u0005E\u0001\u0001)A\u0005\u0003\u0017A\u0011\"a\u0005\u0001\u0005\u0004%\t!!\u0006\t\u0011\u0005u\u0001\u0001)A\u0005\u0003/Aq!a\b\u0001\t\u0003\t\t\u0003C\u0005\u0002H\u0001\t\n\u0011\"\u0001\u0002J!9\u0011q\f\u0001\u0005\u0002\u0005\u0005\u0004bBAL\u0001\u0011\u0005\u0011\u0011\u0014\u0005\b\u0003C\u0003A\u0011BAR\u0011\u001d\tI\r\u0001C\u0005\u0003\u0017DqAa\u0005\u0001\t\u0003\u0012)\u0002C\u0004\u0003\u0018\u0001!IA!\u0007\t\u000f\t=\u0002\u0001\"\u0001\u00032\t\u0001\u0012\t\u001d9mS\u000e\fG/[8o\u0007\u0006\u001c\u0007.\u001a\u0006\u0003;y\tq\u0001[5ti>\u0014\u0018P\u0003\u0002 A\u00051A-\u001a9m_fT!!\t\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\r\"\u0013AB1qC\u000eDWMC\u0001&\u0003\ry'oZ\n\u0004\u0001\u001dj\u0003C\u0001\u0015,\u001b\u0005I#\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051J#AB!osJ+g\r\u0005\u0002/c5\tqF\u0003\u00021A\u0005A\u0011N\u001c;fe:\fG.\u0003\u00023_\t9Aj\\4hS:<\u0017AC8qKJ\fG/[8og\u000e\u0001Q#\u0001\u001c\u0011\u0005]BT\"\u0001\u000f\n\u0005eb\"AG!qa2L7-\u0019;j_:\u001c\u0015m\u00195f\u001fB,'/\u0019;j_:\u001c\u0018aC8qKJ\fG/[8og\u0002\nAC]3uC&tW\rZ!qa2L7-\u0019;j_:\u001cX#A\u001f\u0011\u0005!r\u0014BA *\u0005\rIe\u000e^\u0001\u0016e\u0016$\u0018-\u001b8fI\u0006\u0003\b\u000f\\5dCRLwN\\:!\u0003\u0015\u0019Gn\\2l+\u0005\u0019\u0005C\u0001#H\u001b\u0005)%B\u0001$!\u0003\u0011)H/\u001b7\n\u0005!+%!B\"m_\u000e\\\u0017AB2m_\u000e\\\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005\u00196su\n\u0005\u00028\u0001!)1g\u0002a\u0001m!)1h\u0002a\u0001{!)\u0011i\u0002a\u0001\u0007\u0006QAn\\1eK\u0012\f\u0005\u000f]:\u0016\u0003I\u0003BaU-\\=6\tAK\u0003\u0002V-\u0006Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005\u0019;&\"\u0001-\u0002\t)\fg/Y\u0005\u00035R\u0013\u0011cQ8oGV\u0014(/\u001a8u\u0011\u0006\u001c\b.T1q!\t9D,\u0003\u0002^9\tA1)Y2iK.+\u0017\u0010\u0005\u0002T?&\u0011\u0001\r\u0016\u0002\u000f\u0007>,h\u000e\u001e#po:d\u0015\r^2i\u0003-aw.\u00193fI\u0006\u0003\bo\u001d\u0011\u0002\u0013\u0005\u0004\b\u000fT8bI\u0016\u0014X#\u00013\u0011\t\u0015t7\f]\u0007\u0002M*\u0011q\r[\u0001\u0006G\u0006\u001c\u0007.\u001a\u0006\u0003S*\faaY8n[>t'BA6m\u0003\u00199wn\\4mK*\tQ.A\u0002d_6L!a\u001c4\u0003\u0017\r\u000b7\r[3M_\u0006$WM\u001d\t\u0003oEL!A\u001d\u000f\u0003\u0015\r\u000b7\r[3F]R\u0014\u00180\u0001\u0006baBdu.\u00193fe\u0002\nqB]3n_Z\fG\u000eT5ti\u0016tWM]\u000b\u0002mJ!qO_A\u0001\r\u0011AX\u0002\u0001<\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0002!I,Wn\u001c<bY2K7\u000f^3oKJ\u0004\u0003CA>\u007f\u001b\u0005a(BA?X\u0003\u0011a\u0017M\\4\n\u0005}d(AB(cU\u0016\u001cG\u000fE\u0003f\u0003\u0007Y\u0006/C\u0002\u0002\u0006\u0019\u0014qBU3n_Z\fG\u000eT5ti\u0016tWM]\u0001\tCB\u00048)Y2iKV\u0011\u00111\u0002\t\u0006K\u000651\f]\u0005\u0004\u0003\u001f1'\u0001\u0004'pC\u0012LgnZ\"bG\",\u0017!C1qa\u000e\u000b7\r[3!\u0003\u001diW\r\u001e:jGN,\"!a\u0006\u0011\u0007]\nI\"C\u0002\u0002\u001cq\u0011AbQ1dQ\u0016lU\r\u001e:jGN\f\u0001\"\\3ue&\u001c7\u000fI\u0001\u0004O\u0016$H#\u00029\u0002$\u0005u\u0002bBA\u0013%\u0001\u0007\u0011qE\u0001\u0006CB\u0004\u0018\n\u001a\t\u0005\u0003S\t9D\u0004\u0003\u0002,\u0005M\u0002cAA\u0017S5\u0011\u0011q\u0006\u0006\u0004\u0003c!\u0014A\u0002\u001fs_>$h(C\u0002\u00026%\na\u0001\u0015:fI\u00164\u0017\u0002BA\u001d\u0003w\u0011aa\u0015;sS:<'bAA\u001bS!I\u0011q\b\n\u0011\u0002\u0003\u0007\u0011\u0011I\u0001\nCR$X-\u001c9u\u0013\u0012\u0004R\u0001KA\"\u0003OI1!!\u0012*\u0005\u0019y\u0005\u000f^5p]\u0006iq-\u001a;%I\u00164\u0017-\u001e7uII*\"!a\u0013+\t\u0005\u0005\u0013QJ\u0016\u0003\u0003\u001f\u0002B!!\u0015\u0002\\5\u0011\u00111\u000b\u0006\u0005\u0003+\n9&A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011L\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002^\u0005M#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006Yq/\u001b;i'B\f'o[+J+\u0011\t\u0019'a\u001b\u0015\r\u0005\u0015\u00141SAK)\u0011\t9'! \u0011\t\u0005%\u00141\u000e\u0007\u0001\t\u001d\ti\u0007\u0006b\u0001\u0003_\u0012\u0011\u0001V\t\u0005\u0003c\n9\bE\u0002)\u0003gJ1!!\u001e*\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001KA=\u0013\r\tY(\u000b\u0002\u0004\u0003:L\bbBA@)\u0001\u0007\u0011\u0011Q\u0001\u0003M:\u0004r\u0001KAB\u0003\u000f\u000b9'C\u0002\u0002\u0006&\u0012\u0011BR;oGRLwN\\\u0019\u0011\t\u0005%\u0015qR\u0007\u0003\u0003\u0017S1!!$!\u0003\t)\u0018.\u0003\u0003\u0002\u0012\u0006-%aB*qCJ\\W+\u0013\u0005\b\u0003K!\u0002\u0019AA\u0014\u0011\u001d\ty\u0004\u0006a\u0001\u0003\u0003\nAa]5{KR\u0011\u00111\u0014\t\u0004Q\u0005u\u0015bAAPS\t!Aj\u001c8h\u0003\u0011!\u0018.\\3\u0016\t\u0005\u0015\u00161\u0016\u000b\u0005\u0003O\u000b9\f\u0006\u0003\u0002*\u00065\u0006\u0003BA5\u0003W#q!!\u001c\u0017\u0005\u0004\ty\u0007\u0003\u0005\u00020Z!\t\u0019AAY\u0003\u00051\u0007#\u0002\u0015\u00024\u0006%\u0016bAA[S\tAAHY=oC6,g\bC\u0004\u0002:Z\u0001\r!a/\u0002\u0003Q\u0004B!!0\u0002F6\u0011\u0011q\u0018\u0006\u0005\u0003'\t\tMC\u0002\u0002D2\f\u0001bY8eC\"\fG.Z\u0005\u0005\u0003\u000f\fyLA\u0003US6,'/\u0001\u000bm_\u0006$\u0017\t\u001d9mS\u000e\fG/[8o\u000b:$(/\u001f\u000b\u0006a\u00065\u0017q\u001a\u0005\b\u0003K9\u0002\u0019AA\u0014\u0011\u001d\tyd\u0006a\u0001\u0003\u0003BSaFAj\u0003W\u0004R\u0001KAk\u00033L1!a6*\u0005\u0019!\bN]8xgB!\u00111\\As\u001d\u0011\ti.!9\u000f\t\u00055\u0012q\\\u0005\u0002U%\u0019\u00111]\u0015\u0002\u000fA\f7m[1hK&!\u0011q]Au\u0005YqunU;dQ\u0016cW-\\3oi\u0016C8-\u001a9uS>t'bAArSE:a$a\n\u0002n\nE\u0011'C\u0012\u0002p\u0006U(qAA|+\u0011\t\t0a=\u0016\u0005\u0005\u001dBaBA7i\t\u0007\u0011Q`\u0005\u0005\u0003o\fI0A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%\r\u0006\u0004\u0003wL\u0013A\u0002;ie><8/\u0005\u0003\u0002r\u0005}\b\u0003\u0002B\u0001\u0005\u0007q1\u0001KAq\u0013\u0011\u0011)!!;\u0003\u0013QC'o\\<bE2,\u0017'C\u0012\u0003\n\t-!QBA~\u001d\rA#1B\u0005\u0004\u0003wL\u0013'\u0002\u0012)S\t=!!B:dC2\f\u0017g\u0001\u0014\u0002Z\u0006AAo\\*ue&tw\r\u0006\u0002\u0002(\u0005q!/Z4jgR,'OR5mi\u0016\u0014HC\u0002B\u000e\u0005C\u0011)\u0003E\u0002)\u0005;I1Aa\b*\u0005\u0011)f.\u001b;\t\r\t\r\u0012\u00041\u0001\\\u0003\rYW-\u001f\u0005\b\u0005OI\u0002\u0019\u0001B\u0015\u0003!aw.\u00193fIVK\u0005cA\u001c\u0003,%\u0019!Q\u0006\u000f\u0003\u00171{\u0017\rZ3e\u0003B\u0004X+S\u0001\u000bS:4\u0018\r\\5eCR,G\u0003\u0002B\u000e\u0005gAaAa\t\u001b\u0001\u0004Y\u0006"
)
public class ApplicationCache implements Logging {
   private final ApplicationCacheOperations operations;
   private final int retainedApplications;
   private final Clock clock;
   private final ConcurrentHashMap org$apache$spark$deploy$history$ApplicationCache$$loadedApps;
   private final CacheLoader appLoader;
   private final RemovalListener removalListener;
   private final LoadingCache appCache;
   private final CacheMetrics metrics;
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

   public ApplicationCacheOperations operations() {
      return this.operations;
   }

   public int retainedApplications() {
      return this.retainedApplications;
   }

   public Clock clock() {
      return this.clock;
   }

   public ConcurrentHashMap org$apache$spark$deploy$history$ApplicationCache$$loadedApps() {
      return this.org$apache$spark$deploy$history$ApplicationCache$$loadedApps;
   }

   private CacheLoader appLoader() {
      return this.appLoader;
   }

   private RemovalListener removalListener() {
      return this.removalListener;
   }

   private LoadingCache appCache() {
      return this.appCache;
   }

   public CacheMetrics metrics() {
      return this.metrics;
   }

   public CacheEntry get(final String appId, final Option attemptId) {
      try {
         return (CacheEntry)this.appCache().get(new CacheKey(appId, attemptId));
      } catch (Throwable var7) {
         if (var7 instanceof ExecutionException ? true : var7 instanceof UncheckedExecutionException) {
            throw (Throwable).MODULE$.apply(var7.getCause()).getOrElse(() -> var7);
         } else {
            throw var7;
         }
      }
   }

   public Option get$default$2() {
      return scala.None..MODULE$;
   }

   public Object withSparkUI(final String appId, final Option attemptId, final Function1 fn) {
      CacheEntry entry = this.get(appId, attemptId);
      entry.loadedUI().lock().readLock().lock();

      Object var10000;
      try {
         while(!entry.loadedUI().valid()) {
            entry.loadedUI().lock().readLock().unlock();
            entry = null;

            try {
               this.invalidate(new CacheKey(appId, attemptId));
               entry = this.get(appId, attemptId);
               this.metrics().loadCount().inc();
            } finally {
               if (entry != null) {
                  entry.loadedUI().lock().readLock().lock();
               }

            }
         }

         fn.apply(entry.loadedUI().ui());
      } finally {
         var10000 = entry;
         if (entry != null) {
            var10000 = entry.loadedUI().lock().readLock();
            ((ReentrantReadWriteLock.ReadLock)var10000).unlock();
         }

      }

      return var10000;
   }

   public long size() {
      return this.appCache().size();
   }

   private Object time(final Timer t, final Function0 f) {
      Timer.Context timeCtx = t.time();

      Object var10000;
      try {
         var10000 = f.apply();
      } finally {
         timeCtx.close();
      }

      return var10000;
   }

   public CacheEntry org$apache$spark$deploy$history$ApplicationCache$$loadApplicationEntry(final String appId, final Option attemptId) throws NoSuchElementException {
      LazyRef application$lzy = new LazyRef();
      this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Loading application Entry "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.application$1(application$lzy, appId, attemptId))));
      this.metrics().loadCount().inc();
      LoadedAppUI loadedUI = (LoadedAppUI)this.time(this.metrics().loadTimer(), () -> {
         this.metrics().lookupCount().inc();
         Option var5 = this.operations().getAppUI(appId, attemptId);
         if (var5 instanceof Some var6) {
            LoadedAppUI loadedUI = (LoadedAppUI)var6.value();
            this.logDebug(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Loaded application "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.application$1(application$lzy, appId, attemptId))));
            return loadedUI;
         } else if (scala.None..MODULE$.equals(var5)) {
            this.metrics().lookupFailureCount().inc();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to load application attempt "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.application$1(application$lzy, appId, attemptId))));
            throw new NoSuchElementException("no application with application Id '" + appId + "'" + attemptId.map((id) -> " attemptId '" + id + "'").getOrElse(() -> " and no attempt Id"));
         } else {
            throw new MatchError(var5);
         }
      });

      try {
         boolean completed = loadedUI.ui().getApplicationInfoList().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$loadApplicationEntry$7(x$1)));
         if (!completed) {
            this.registerFilter(new CacheKey(appId, attemptId), loadedUI);
         }

         this.operations().attachSparkUI(appId, attemptId, loadedUI.ui(), completed);
         return new CacheEntry(loadedUI, completed);
      } catch (Exception var7) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to initialize application UI for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, this.application$1(application$lzy, appId, attemptId))})))), var7);
         this.operations().detachSparkUI(appId, attemptId, loadedUI.ui());
         throw var7;
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("ApplicationCache( retainedApplications= " + this.retainedApplications() + ")");
      sb.append("; time= " + this.clock().getTimeMillis());
      sb.append("; entry count= " + this.appCache().size() + "\n");
      sb.append("----\n");
      scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(this.appCache().asMap()).asScala().foreach((x0$1) -> {
         if (x0$1 != null) {
            CacheKey key = (CacheKey)x0$1._1();
            CacheEntry entry = (CacheEntry)x0$1._2();
            return sb.append("  " + key + " -> " + entry + "\n");
         } else {
            throw new MatchError(x0$1);
         }
      });
      sb.append("----\n");
      sb.append(this.metrics());
      sb.append("----\n");
      return sb.toString();
   }

   private void registerFilter(final CacheKey key, final LoadedAppUI loadedUI) {
      scala.Predef..MODULE$.require(loadedUI != null);
      EnumSet enumDispatcher = EnumSet.of(DispatcherType.ASYNC, DispatcherType.REQUEST);
      ApplicationCacheCheckFilter filter = new ApplicationCacheCheckFilter(key, loadedUI, this);
      FilterHolder holder = new FilterHolder(filter);
      scala.Predef..MODULE$.require(loadedUI.ui().getHandlers() != null, () -> "null handlers");
      loadedUI.ui().getHandlers().foreach((handler) -> {
         $anonfun$registerFilter$2(holder, enumDispatcher, handler);
         return BoxedUnit.UNIT;
      });
   }

   public void invalidate(final CacheKey key) {
      this.appCache().invalidate(key);
   }

   // $FF: synthetic method
   private final MessageWithContext application$lzycompute$1(final LazyRef application$lzy$1, final String appId$1, final Option attemptId$1) {
      synchronized(application$lzy$1){}

      MessageWithContext var5;
      try {
         var5 = application$lzy$1.initialized() ? (MessageWithContext)application$lzy$1.value() : (MessageWithContext)application$lzy$1.initialize(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "/", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ID..MODULE$, appId$1), new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, .MODULE$.option2Iterable(attemptId$1).mkString())}))));
      } catch (Throwable var7) {
         throw var7;
      }

      return var5;
   }

   private final MessageWithContext application$1(final LazyRef application$lzy$1, final String appId$1, final Option attemptId$1) {
      return application$lzy$1.initialized() ? (MessageWithContext)application$lzy$1.value() : this.application$lzycompute$1(application$lzy$1, appId$1, attemptId$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadApplicationEntry$7(final ApplicationInfo x$1) {
      return ((ApplicationAttemptInfo)x$1.attempts().last()).completed();
   }

   // $FF: synthetic method
   public static final void $anonfun$registerFilter$2(final FilterHolder holder$1, final EnumSet enumDispatcher$1, final ServletContextHandler handler) {
      handler.addFilter(holder$1, "/*", enumDispatcher$1);
   }

   public ApplicationCache(final ApplicationCacheOperations operations, final int retainedApplications, final Clock clock) {
      this.operations = operations;
      this.retainedApplications = retainedApplications;
      this.clock = clock;
      Logging.$init$(this);
      this.org$apache$spark$deploy$history$ApplicationCache$$loadedApps = new ConcurrentHashMap();
      this.appLoader = new CacheLoader() {
         // $FF: synthetic field
         private final ApplicationCache $outer;

         public CacheEntry load(final CacheKey key) {
            CountDownLatch removalLatch = (CountDownLatch)this.$outer.org$apache$spark$deploy$history$ApplicationCache$$loadedApps().get(key);
            if (removalLatch != null) {
               removalLatch.await();
            }

            CacheEntry entry = this.$outer.org$apache$spark$deploy$history$ApplicationCache$$loadApplicationEntry(key.appId(), key.attemptId());
            this.$outer.org$apache$spark$deploy$history$ApplicationCache$$loadedApps().put(key, new CountDownLatch(1));
            return entry;
         }

         public {
            if (ApplicationCache.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationCache.this;
            }
         }
      };
      this.removalListener = new RemovalListener() {
         // $FF: synthetic field
         private final ApplicationCache $outer;

         public void onRemoval(final RemovalNotification rm) {
            try {
               this.$outer.metrics().evictionCount().inc();
               CacheKey key = (CacheKey)rm.getKey();
               this.$outer.logDebug((Function0)(() -> "Evicting entry " + key));
               this.$outer.operations().detachSparkUI(key.appId(), key.attemptId(), ((CacheEntry)rm.getValue()).loadedUI().ui());
            } finally {
               ((CountDownLatch)this.$outer.org$apache$spark$deploy$history$ApplicationCache$$loadedApps().remove(rm.getKey())).countDown();
            }

         }

         public {
            if (ApplicationCache.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationCache.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      this.appCache = CacheBuilder.newBuilder().maximumSize((long)retainedApplications).removalListener(this.removalListener()).build(this.appLoader());
      this.metrics = new CacheMetrics("history.cache");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
