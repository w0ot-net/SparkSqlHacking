package org.apache.spark.internal.plugin;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005e4A\u0001D\u0007\u00051!A\u0011\u0005\u0001B\u0001B\u0003%!\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0011\u0005A!A!\u0002\u0013\u0019\u0005\"B*\u0001\t\u0003!\u0006bB-\u0001\u0005\u0004%IA\u0017\u0005\u0007M\u0002\u0001\u000b\u0011B.\t\u000b\u001d\u0004A\u0011\t5\t\u000b9\u0004A\u0011I8\t\u000bA\u0004A\u0011I8\t\u000bE\u0004A\u0011I8\t\u000bI\u0004A\u0011I:\u0003+\u0011\u0013\u0018N^3s!2,x-\u001b8D_:$\u0018-\u001b8fe*\u0011abD\u0001\u0007a2,x-\u001b8\u000b\u0005A\t\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e\u001c\u0001aE\u0002\u00013u\u0001\"AG\u000e\u000e\u00035I!\u0001H\u0007\u0003\u001fAcWoZ5o\u0007>tG/Y5oKJ\u0004\"AH\u0010\u000e\u0003=I!\u0001I\b\u0003\u000f1{wmZ5oO\u0006\u00111o\u0019\t\u0003G\u0011j\u0011!E\u0005\u0003KE\u0011Ab\u00159be.\u001cuN\u001c;fqR\f\u0011B]3t_V\u00148-Z:\u0011\t!js\u0006P\u0007\u0002S)\u0011!fK\u0001\u0005kRLGNC\u0001-\u0003\u0011Q\u0017M^1\n\u00059J#aA'baB\u0011\u0001'\u000f\b\u0003c]\u0002\"AM\u001b\u000e\u0003MR!\u0001N\f\u0002\rq\u0012xn\u001c;?\u0015\u00051\u0014!B:dC2\f\u0017B\u0001\u001d6\u0003\u0019\u0001&/\u001a3fM&\u0011!h\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005a*\u0004CA\u001fA\u001b\u0005q$BA \u0012\u0003!\u0011Xm]8ve\u000e,\u0017BA!?\u0005M\u0011Vm]8ve\u000e,\u0017J\u001c4pe6\fG/[8o\u0003\u001d\u0001H.^4j]N\u00042\u0001R%M\u001d\t)uI\u0004\u00023\r&\ta'\u0003\u0002Ik\u00059\u0001/Y2lC\u001e,\u0017B\u0001&L\u0005\r\u0019V-\u001d\u0006\u0003\u0011V\u0002\"!T)\u000e\u00039S!AD(\u000b\u0005A\u000b\u0012aA1qS&\u0011!K\u0014\u0002\f'B\f'o\u001b)mk\u001eLg.\u0001\u0004=S:LGO\u0010\u000b\u0005+Z;\u0006\f\u0005\u0002\u001b\u0001!)\u0011\u0005\u0002a\u0001E!)a\u0005\u0002a\u0001O!)!\t\u0002a\u0001\u0007\u0006iAM]5wKJ\u0004F.^4j]N,\u0012a\u0017\t\u0004\t&c\u0006#B/__\u0001\u001cW\"A\u001b\n\u0005}+$A\u0002+va2,7\u0007\u0005\u0002NC&\u0011!M\u0014\u0002\r\tJLg/\u001a:QYV<\u0017N\u001c\t\u00035\u0011L!!Z\u0007\u0003#AcWoZ5o\u0007>tG/\u001a=u\u00136\u0004H.\u0001\bee&4XM\u001d)mk\u001eLgn\u001d\u0011\u0002\u001fI,w-[:uKJlU\r\u001e:jGN$\"!\u001b7\u0011\u0005uS\u0017BA66\u0005\u0011)f.\u001b;\t\u000b5<\u0001\u0019A\u0018\u0002\u000b\u0005\u0004\b/\u00133\u0002\u0011MDW\u000f\u001e3po:$\u0012![\u0001\f_:$\u0016m]6Ti\u0006\u0014H/A\bp]R\u000b7o[*vG\u000e,W\rZ3e\u00031yg\u000eV1tW\u001a\u000b\u0017\u000e\\3e)\tIG\u000fC\u0003v\u0017\u0001\u0007a/A\u0007gC&dWO]3SK\u0006\u001cxN\u001c\t\u0003G]L!\u0001_\t\u0003!Q\u000b7o\u001b$bS2,GMU3bg>t\u0007"
)
public class DriverPluginContainer extends PluginContainer implements Logging {
   private final SparkContext sc;
   private final Map resources;
   private final Seq driverPlugins;
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

   private Seq driverPlugins() {
      return this.driverPlugins;
   }

   public void registerMetrics(final String appId) {
      this.driverPlugins().foreach((x0$1) -> {
         $anonfun$registerMetrics$1(appId, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void shutdown() {
      this.driverPlugins().foreach((x0$1) -> {
         $anonfun$shutdown$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void onTaskStart() {
      throw new IllegalStateException("Should not be called for the driver container.");
   }

   public void onTaskSucceeded() {
      throw new IllegalStateException("Should not be called for the driver container.");
   }

   public void onTaskFailed(final TaskFailedReason failureReason) {
      throw new IllegalStateException("Should not be called for the driver container.");
   }

   // $FF: synthetic method
   public static final void $anonfun$registerMetrics$1(final String appId$1, final Tuple3 x0$1) {
      if (x0$1 != null) {
         DriverPlugin plugin = (DriverPlugin)x0$1._2();
         PluginContextImpl ctx = (PluginContextImpl)x0$1._3();
         plugin.registerMetrics(appId$1, ctx);
         ctx.registerMetrics();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$shutdown$1(final DriverPluginContainer $this, final Tuple3 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         DriverPlugin plugin = (DriverPlugin)x0$1._2();

         try {
            $this.logDebug((Function0)(() -> "Stopping plugin " + name + "."));
            plugin.shutdown();
            BoxedUnit var8 = BoxedUnit.UNIT;
         } catch (Throwable var7) {
            $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while shutting down plugin ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)})))), var7);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   public DriverPluginContainer(final SparkContext sc, final Map resources, final Seq plugins) {
      this.sc = sc;
      this.resources = resources;
      Logging.$init$(this);
      this.driverPlugins = (Seq)plugins.flatMap((p) -> {
         DriverPlugin driverPlugin = p.driverPlugin();
         if (driverPlugin != null) {
            String name = p.getClass().getName();
            PluginContextImpl ctx = new PluginContextImpl(name, this.sc.env().rpcEnv(), this.sc.env().metricsSystem(), this.sc.conf(), this.sc.env().executorId(), this.resources);
            Map extraConf = driverPlugin.init(this.sc, ctx);
            if (extraConf != null) {
               scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(extraConf).asScala().foreach((x0$1) -> {
                  if (x0$1 != null) {
                     String k = (String)x0$1._1();
                     String v = (String)x0$1._2();
                     return this.sc.conf().set(PluginContainer$.MODULE$.EXTRA_CONF_PREFIX() + name + "." + k, v);
                  } else {
                     throw new MatchError(x0$1);
                  }
               });
            }

            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialized driver component for plugin ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)})))));
            return new Some(new Tuple3(p.getClass().getName(), driverPlugin, ctx));
         } else {
            return scala.None..MODULE$;
         }
      });
      if (this.driverPlugins().nonEmpty()) {
         scala.collection.immutable.Map pluginsByName = ((IterableOnceOps)this.driverPlugins().map((x0$2) -> {
            if (x0$2 != null) {
               String name = (String)x0$2._1();
               DriverPlugin plugin = (DriverPlugin)x0$2._2();
               return new Tuple2(name, plugin);
            } else {
               throw new MatchError(x0$2);
            }
         })).toMap(scala..less.colon.less..MODULE$.refl());
         sc.env().rpcEnv().setupEndpoint(PluginEndpoint.class.getName(), new PluginEndpoint(pluginsByName, sc.env().rpcEnv()));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
