package org.apache.spark.internal.plugin;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkEnv;
import org.apache.spark.TaskFailedReason;
import org.apache.spark.api.plugin.ExecutorPlugin;
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
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y4A\u0001D\u0007\u00051!A\u0011\u0005\u0001B\u0001B\u0003%!\u0005\u0003\u0005'\u0001\t\u0005\t\u0015!\u0003(\u0011!\u0011\u0005A!A!\u0002\u0013\u0019\u0005\"B*\u0001\t\u0003!\u0006bB-\u0001\u0005\u0004%IA\u0017\u0005\u0007G\u0002\u0001\u000b\u0011B.\t\u000b\u0011\u0004A\u0011I3\t\u000b-\u0004A\u0011\t7\t\u000b5\u0004A\u0011\t7\t\u000b9\u0004A\u0011\t7\t\u000b=\u0004A\u0011\t9\u0003/\u0015CXmY;u_J\u0004F.^4j]\u000e{g\u000e^1j]\u0016\u0014(B\u0001\b\u0010\u0003\u0019\u0001H.^4j]*\u0011\u0001#E\u0001\tS:$XM\u001d8bY*\u0011!cE\u0001\u0006gB\f'o\u001b\u0006\u0003)U\ta!\u00199bG\",'\"\u0001\f\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001IR\u0004\u0005\u0002\u001b75\tQ\"\u0003\u0002\u001d\u001b\ty\u0001\u000b\\;hS:\u001cuN\u001c;bS:,'\u000f\u0005\u0002\u001f?5\tq\"\u0003\u0002!\u001f\t9Aj\\4hS:<\u0017aA3omB\u00111\u0005J\u0007\u0002#%\u0011Q%\u0005\u0002\t'B\f'o[#om\u0006I!/Z:pkJ\u001cWm\u001d\t\u0005Q5zC(D\u0001*\u0015\tQ3&\u0001\u0003vi&d'\"\u0001\u0017\u0002\t)\fg/Y\u0005\u0003]%\u00121!T1q!\t\u0001\u0014H\u0004\u00022oA\u0011!'N\u0007\u0002g)\u0011AgF\u0001\u0007yI|w\u000e\u001e \u000b\u0003Y\nQa]2bY\u0006L!\u0001O\u001b\u0002\rA\u0013X\rZ3g\u0013\tQ4H\u0001\u0004TiJLgn\u001a\u0006\u0003qU\u0002\"!\u0010!\u000e\u0003yR!aP\t\u0002\u0011I,7o\\;sG\u0016L!!\u0011 \u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0002\u000fAdWoZ5ogB\u0019A)\u0013'\u000f\u0005\u0015;eB\u0001\u001aG\u0013\u00051\u0014B\u0001%6\u0003\u001d\u0001\u0018mY6bO\u0016L!AS&\u0003\u0007M+\u0017O\u0003\u0002IkA\u0011Q*U\u0007\u0002\u001d*\u0011ab\u0014\u0006\u0003!F\t1!\u00199j\u0013\t\u0011fJA\u0006Ta\u0006\u00148\u000e\u00157vO&t\u0017A\u0002\u001fj]&$h\b\u0006\u0003V-^C\u0006C\u0001\u000e\u0001\u0011\u0015\tC\u00011\u0001#\u0011\u00151C\u00011\u0001(\u0011\u0015\u0011E\u00011\u0001D\u0003=)\u00070Z2vi>\u0014\b\u000b\\;hS:\u001cX#A.\u0011\u0007\u0011KE\f\u0005\u0003^=>\u0002W\"A\u001b\n\u0005}+$A\u0002+va2,'\u0007\u0005\u0002NC&\u0011!M\u0014\u0002\u000f\u000bb,7-\u001e;peBcWoZ5o\u0003A)\u00070Z2vi>\u0014\b\u000b\\;hS:\u001c\b%A\bsK\u001eL7\u000f^3s\u001b\u0016$(/[2t)\t1\u0017\u000e\u0005\u0002^O&\u0011\u0001.\u000e\u0002\u0005+:LG\u000fC\u0003k\u000f\u0001\u0007q&A\u0003baBLE-\u0001\u0005tQV$Hm\\<o)\u00051\u0017aC8o)\u0006\u001c8n\u0015;beR\fqb\u001c8UCN\\7+^2dK\u0016$W\rZ\u0001\r_:$\u0016m]6GC&dW\r\u001a\u000b\u0003MFDQA]\u0006A\u0002M\fQBZ1jYV\u0014XMU3bg>t\u0007CA\u0012u\u0013\t)\u0018C\u0001\tUCN\\g)Y5mK\u0012\u0014V-Y:p]\u0002"
)
public class ExecutorPluginContainer extends PluginContainer implements Logging {
   private final SparkEnv env;
   private final Map resources;
   private final Seq executorPlugins;
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

   private Seq executorPlugins() {
      return this.executorPlugins;
   }

   public void registerMetrics(final String appId) {
      throw new IllegalStateException("Should not be called for the executor container.");
   }

   public void shutdown() {
      this.executorPlugins().foreach((x0$1) -> {
         $anonfun$shutdown$4(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void onTaskStart() {
      this.executorPlugins().foreach((x0$1) -> {
         $anonfun$onTaskStart$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void onTaskSucceeded() {
      this.executorPlugins().foreach((x0$1) -> {
         $anonfun$onTaskSucceeded$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void onTaskFailed(final TaskFailedReason failureReason) {
      this.executorPlugins().foreach((x0$1) -> {
         $anonfun$onTaskFailed$1(this, failureReason, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executorPlugins$2(final String prefix$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(prefix$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$shutdown$4(final ExecutorPluginContainer $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         ExecutorPlugin plugin = (ExecutorPlugin)x0$1._2();

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

   // $FF: synthetic method
   public static final void $anonfun$onTaskStart$1(final ExecutorPluginContainer $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         ExecutorPlugin plugin = (ExecutorPlugin)x0$1._2();

         try {
            plugin.onTaskStart();
            BoxedUnit var8 = BoxedUnit.UNIT;
         } catch (Throwable var7) {
            $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while calling onTaskStart on"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" plugin ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)}))))), var7);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskSucceeded$1(final ExecutorPluginContainer $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         ExecutorPlugin plugin = (ExecutorPlugin)x0$1._2();

         try {
            plugin.onTaskSucceeded();
            BoxedUnit var8 = BoxedUnit.UNIT;
         } catch (Throwable var7) {
            $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while calling onTaskSucceeded on"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" plugin ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)}))))), var7);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onTaskFailed$1(final ExecutorPluginContainer $this, final TaskFailedReason failureReason$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         ExecutorPlugin plugin = (ExecutorPlugin)x0$1._2();

         try {
            plugin.onTaskFailed(failureReason$1);
            BoxedUnit var9 = BoxedUnit.UNIT;
         } catch (Throwable var8) {
            $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while calling onTaskFailed on"})))).log(scala.collection.immutable.Nil..MODULE$).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" plugin ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)}))))), var8);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x0$1);
      }
   }

   public ExecutorPluginContainer(final SparkEnv env, final Map resources, final Seq plugins) {
      this.env = env;
      this.resources = resources;
      Logging.$init$(this);
      Tuple2[] allExtraConf = env.conf().getAllWithPrefix(PluginContainer$.MODULE$.EXTRA_CONF_PREFIX());
      this.executorPlugins = (Seq)plugins.flatMap((p) -> {
         ExecutorPlugin executorPlugin = p.executorPlugin();
         if (executorPlugin != null) {
            String name = p.getClass().getName();
            String prefix = name + ".";
            Map extraConf = scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])allExtraConf), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$executorPlugins$2(prefix, x0$1)))), (x0$2) -> {
               if (x0$2 != null) {
                  String k = (String)x0$2._1();
                  String v = (String)x0$2._2();
                  return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k.substring(prefix.length())), v);
               } else {
                  throw new MatchError(x0$2);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl())).asJava();
            PluginContextImpl ctx = new PluginContextImpl(name, this.env.rpcEnv(), this.env.metricsSystem(), this.env.conf(), this.env.executorId(), this.resources);
            executorPlugin.init(ctx, extraConf);
            ctx.registerMetrics();
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialized executor component for plugin ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)})))));
            return new Some(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(p.getClass().getName()), executorPlugin));
         } else {
            return scala.None..MODULE$;
         }
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
