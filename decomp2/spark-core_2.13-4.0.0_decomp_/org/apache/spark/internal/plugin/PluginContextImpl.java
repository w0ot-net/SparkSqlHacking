package org.apache.spark.internal.plugin;

import com.codahale.metrics.MetricRegistry;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.api.plugin.PluginContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.metrics.source.Source;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.util.RpcUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mc\u0001B\r\u001b\t\u0015B\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006IA\u000f\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\u0011\"Aa\n\u0001B\u0001B\u0003%q\n\u0003\u0005V\u0001\t\u0015\r\u0011\"\u0011W\u0011!Y\u0006A!A!\u0002\u00139\u0006\u0002\u0003/\u0001\u0005\u000b\u0007I\u0011I/\t\u0011y\u0003!\u0011!Q\u0001\niB\u0001b\u0018\u0001\u0003\u0006\u0004%\t\u0005\u0019\u0005\t[\u0002\u0011\t\u0011)A\u0005C\")a\u000e\u0001C\u0001_\")\u0001\u0010\u0001C!s\"9!\u0010\u0001b\u0001\n\u0013Y\bbBA\u0006\u0001\u0001\u0006I\u0001 \u0005\u000b\u0003\u001b\u0001\u0001R1A\u0005\n\u0005=\u0001bBA\f\u0001\u0011\u0005\u0013\u0011\u0004\u0005\b\u00037\u0001A\u0011IA\u000f\u0011\u001d\t\t\u0004\u0001C!\u0003gAq!a\u000e\u0001\t\u0003\tID\u0002\u0004\u0002<\u0001\u0001\u0011Q\b\u0005\n\u0003\u0017\u001a\"Q1A\u0005BuC\u0011\"!\u0014\u0014\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u0013\u0005]1C!b\u0001\n\u0003Z\b\"CA('\t\u0005\t\u0015!\u0003}\u0011\u0019q7\u0003\"\u0001\u0002R\t\t\u0002\u000b\\;hS:\u001cuN\u001c;fqRLU\u000e\u001d7\u000b\u0005ma\u0012A\u00029mk\u001eLgN\u0003\u0002\u001e=\u0005A\u0011N\u001c;fe:\fGN\u0003\u0002 A\u0005)1\u000f]1sW*\u0011\u0011EI\u0001\u0007CB\f7\r[3\u000b\u0003\r\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0014/kA\u0011q\u0005L\u0007\u0002Q)\u0011\u0011FK\u0001\u0005Y\u0006twMC\u0001,\u0003\u0011Q\u0017M^1\n\u00055B#AB(cU\u0016\u001cG\u000f\u0005\u00020g5\t\u0001G\u0003\u0002\u001cc)\u0011!GH\u0001\u0004CBL\u0017B\u0001\u001b1\u00055\u0001F.^4j]\u000e{g\u000e^3yiB\u0011agN\u0007\u00029%\u0011\u0001\b\b\u0002\b\u0019><w-\u001b8h\u0003)\u0001H.^4j]:\u000bW.\u001a\t\u0003w\u0011s!\u0001\u0010\"\u0011\u0005u\u0002U\"\u0001 \u000b\u0005}\"\u0013A\u0002\u001fs_>$hHC\u0001B\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0005)\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u0013aa\u0015;sS:<'BA\"A\u0003\u0019\u0011\boY#omB\u0011\u0011\nT\u0007\u0002\u0015*\u00111JH\u0001\u0004eB\u001c\u0017BA'K\u0005\u0019\u0011\u0006oY#om\u0006iQ.\u001a;sS\u000e\u001c8+_:uK6\u0004\"\u0001U*\u000e\u0003ES!A\u0015\u0010\u0002\u000f5,GO]5dg&\u0011A+\u0015\u0002\u000e\u001b\u0016$(/[2t'f\u001cH/Z7\u0002\t\r|gNZ\u000b\u0002/B\u0011\u0001,W\u0007\u0002=%\u0011!L\b\u0002\n'B\f'o[\"p]\u001a\fQaY8oM\u0002\n!\"\u001a=fGV$xN]%E+\u0005Q\u0014aC3yK\u000e,Ho\u001c:J\t\u0002\n\u0011B]3t_V\u00148-Z:\u0016\u0003\u0005\u0004BAY3;O6\t1M\u0003\u0002eU\u0005!Q\u000f^5m\u0013\t17MA\u0002NCB\u0004\"\u0001[6\u000e\u0003%T!A\u001b\u0010\u0002\u0011I,7o\\;sG\u0016L!\u0001\\5\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0002\u0015I,7o\\;sG\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\baJ\u001cH/\u001e<x!\t\t\b!D\u0001\u001b\u0011\u0015I$\u00021\u0001;\u0011\u00159%\u00021\u0001I\u0011\u0015q%\u00021\u0001P\u0011\u0015)&\u00021\u0001X\u0011\u0015a&\u00021\u0001;\u0011\u0015y&\u00021\u0001b\u0003!Awn\u001d;oC6,G#\u0001\u001e\u0002\u0011I,w-[:uef,\u0012\u0001 \t\u0004{\u0006\u001dQ\"\u0001@\u000b\u0005I{(\u0002BA\u0001\u0003\u0007\t\u0001bY8eC\"\fG.\u001a\u0006\u0003\u0003\u000b\t1aY8n\u0013\r\tIA \u0002\u000f\u001b\u0016$(/[2SK\u001eL7\u000f\u001e:z\u0003%\u0011XmZ5tiJL\b%\u0001\bee&4XM]#oIB|\u0017N\u001c;\u0016\u0005\u0005E\u0001cA%\u0002\u0014%\u0019\u0011Q\u0003&\u0003\u001dI\u00038-\u00128ea>Lg\u000e\u001e*fM\u0006qQ.\u001a;sS\u000e\u0014VmZ5tiJLH#\u0001?\u0002\tM,g\u000e\u001a\u000b\u0005\u0003?\t9\u0003\u0005\u0003\u0002\"\u0005\rR\"\u0001!\n\u0007\u0005\u0015\u0002I\u0001\u0003V]&$\bbBA\u0015!\u0001\u0007\u00111F\u0001\b[\u0016\u001c8/Y4f!\u0011\t\t#!\f\n\u0007\u0005=\u0002I\u0001\u0004B]f\u0014VMZ\u0001\u0004CN\\G\u0003BA\u0016\u0003kAq!!\u000b\u0012\u0001\u0004\tY#A\bsK\u001eL7\u000f^3s\u001b\u0016$(/[2t)\t\tyBA\nQYV<\u0017N\\'fiJL7m]*pkJ\u001cWmE\u0003\u0014\u0003W\ty\u0004\u0005\u0003\u0002B\u0005\u001dSBAA\"\u0015\r\t)%U\u0001\u0007g>,(oY3\n\t\u0005%\u00131\t\u0002\u0007'>,(oY3\u0002\u0015M|WO]2f\u001d\u0006lW-A\u0006t_V\u00148-\u001a(b[\u0016\u0004\u0013aD7fiJL7MU3hSN$(/\u001f\u0011\u0015\r\u0005M\u0013qKA-!\r\t)fE\u0007\u0002\u0001!1\u00111\n\rA\u0002iBa!a\u0006\u0019\u0001\u0004a\b"
)
public class PluginContextImpl implements PluginContext, Logging {
   private RpcEndpointRef driverEndpoint;
   private final String pluginName;
   private final RpcEnv rpcEnv;
   private final MetricsSystem metricsSystem;
   private final SparkConf conf;
   private final String executorID;
   private final Map resources;
   private final MetricRegistry registry;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

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

   public SparkConf conf() {
      return this.conf;
   }

   public String executorID() {
      return this.executorID;
   }

   public Map resources() {
      return this.resources;
   }

   public String hostname() {
      return this.rpcEnv.address().hostPort().split(":")[0];
   }

   private MetricRegistry registry() {
      return this.registry;
   }

   private RpcEndpointRef driverEndpoint$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.driverEndpoint = this.liftedTree1$1();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.driverEndpoint;
   }

   private RpcEndpointRef driverEndpoint() {
      return !this.bitmap$0 ? this.driverEndpoint$lzycompute() : this.driverEndpoint;
   }

   public MetricRegistry metricRegistry() {
      return this.registry();
   }

   public void send(final Object message) {
      if (this.driverEndpoint() == null) {
         throw new IllegalStateException("Driver endpoint is not known.");
      } else {
         this.driverEndpoint().send(new PluginMessage(this.pluginName, message));
      }
   }

   public Object ask(final Object message) {
      try {
         if (this.driverEndpoint() != null) {
            return this.driverEndpoint().askSync(new PluginMessage(this.pluginName, message), .MODULE$.AnyRef());
         } else {
            throw new IllegalStateException("Driver endpoint is not known.");
         }
      } catch (Throwable var6) {
         if (var6 instanceof SparkException var5) {
            if (var5.getCause() != null) {
               throw var5.getCause();
            }
         }

         throw var6;
      }
   }

   public void registerMetrics() {
      if (!this.registry().getMetrics().isEmpty()) {
         PluginMetricsSource src = new PluginMetricsSource("plugin." + this.pluginName, this.registry());
         this.metricsSystem.registerSource(src);
      }
   }

   // $FF: synthetic method
   private final RpcEndpointRef liftedTree1$1() {
      RpcEndpointRef var10000;
      try {
         var10000 = RpcUtils$.MODULE$.makeDriverRef(PluginEndpoint.class.getName(), this.conf(), this.rpcEnv);
      } catch (Exception var2) {
         this.logWarning((Function0)(() -> "Failed to create driver plugin endpoint ref."), var2);
         var10000 = null;
      }

      return var10000;
   }

   public PluginContextImpl(final String pluginName, final RpcEnv rpcEnv, final MetricsSystem metricsSystem, final SparkConf conf, final String executorID, final Map resources) {
      this.pluginName = pluginName;
      this.rpcEnv = rpcEnv;
      this.metricsSystem = metricsSystem;
      this.conf = conf;
      this.executorID = executorID;
      this.resources = resources;
      Logging.$init$(this);
      this.registry = new MetricRegistry();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class PluginMetricsSource implements Source {
      private final String sourceName;
      private final MetricRegistry metricRegistry;
      // $FF: synthetic field
      public final PluginContextImpl $outer;

      public String sourceName() {
         return this.sourceName;
      }

      public MetricRegistry metricRegistry() {
         return this.metricRegistry;
      }

      // $FF: synthetic method
      public PluginContextImpl org$apache$spark$internal$plugin$PluginContextImpl$PluginMetricsSource$$$outer() {
         return this.$outer;
      }

      public PluginMetricsSource(final String sourceName, final MetricRegistry metricRegistry) {
         this.sourceName = sourceName;
         this.metricRegistry = metricRegistry;
         if (PluginContextImpl.this == null) {
            throw null;
         } else {
            this.$outer = PluginContextImpl.this;
            super();
         }
      }
   }
}
