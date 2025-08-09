package org.apache.spark.internal.plugin;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.api.plugin.DriverPlugin;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.rpc.IsolatedThreadSafeRpcEndpoint;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005a3Aa\u0002\u0005\u0005'!AA\u0005\u0001B\u0001B\u0003%Q\u0005\u0003\u0005;\u0001\t\u0015\r\u0011\"\u0011<\u0011!y\u0004A!A!\u0002\u0013a\u0004\"\u0002!\u0001\t\u0003\t\u0005\"\u0002$\u0001\t\u0003:\u0005\"B)\u0001\t\u0003\u0012&A\u0004)mk\u001eLg.\u00128ea>Lg\u000e\u001e\u0006\u0003\u0013)\ta\u0001\u001d7vO&t'BA\u0006\r\u0003!Ig\u000e^3s]\u0006d'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001M!\u0001\u0001\u0006\u000e!!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00111DH\u0007\u00029)\u0011Q\u0004D\u0001\u0004eB\u001c\u0017BA\u0010\u001d\u0005uI5o\u001c7bi\u0016$G\u000b\u001b:fC\u0012\u001c\u0016MZ3Sa\u000e,e\u000e\u001a9pS:$\bCA\u0011#\u001b\u0005Q\u0011BA\u0012\u000b\u0005\u001daunZ4j]\u001e\fq\u0001\u001d7vO&t7\u000f\u0005\u0003'[A\u001adBA\u0014,!\tAc#D\u0001*\u0015\tQ##\u0001\u0004=e>|GOP\u0005\u0003YY\ta\u0001\u0015:fI\u00164\u0017B\u0001\u00180\u0005\ri\u0015\r\u001d\u0006\u0003YY\u0001\"AJ\u0019\n\u0005Iz#AB*ue&tw\r\u0005\u00025q5\tQG\u0003\u0002\nm)\u0011q\u0007D\u0001\u0004CBL\u0017BA\u001d6\u00051!%/\u001b<feBcWoZ5o\u0003\u0019\u0011\boY#omV\tA\b\u0005\u0002\u001c{%\u0011a\b\b\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wA\u00051A(\u001b8jiz\"2A\u0011#F!\t\u0019\u0005!D\u0001\t\u0011\u0015!C\u00011\u0001&\u0011\u0015QD\u00011\u0001=\u0003\u001d\u0011XmY3jm\u0016,\u0012\u0001\u0013\t\u0005+%[e*\u0003\u0002K-\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u0002\u0016\u0019&\u0011QJ\u0006\u0002\u0004\u0003:L\bCA\u000bP\u0013\t\u0001fC\u0001\u0003V]&$\u0018a\u0004:fG\u0016Lg/Z!oIJ+\u0007\u000f\\=\u0015\u0005!\u001b\u0006\"\u0002+\u0007\u0001\u0004)\u0016aB2p]R,\u0007\u0010\u001e\t\u00037YK!a\u0016\u000f\u0003\u001dI\u00038mQ1mY\u000e{g\u000e^3yi\u0002"
)
public class PluginEndpoint implements IsolatedThreadSafeRpcEndpoint, Logging {
   public final Map org$apache$spark$internal$plugin$PluginEndpoint$$plugins;
   private final RpcEnv rpcEnv;
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public final int threadCount() {
      return IsolatedThreadSafeRpcEndpoint.threadCount$(this);
   }

   public final RpcEndpointRef self() {
      return RpcEndpoint.self$(this);
   }

   public void onError(final Throwable cause) {
      RpcEndpoint.onError$(this, cause);
   }

   public void onConnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onConnected$(this, remoteAddress);
   }

   public void onDisconnected(final RpcAddress remoteAddress) {
      RpcEndpoint.onDisconnected$(this, remoteAddress);
   }

   public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
      RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
   }

   public void onStart() {
      RpcEndpoint.onStart$(this);
   }

   public void onStop() {
      RpcEndpoint.onStop$(this);
   }

   public final void stop() {
      RpcEndpoint.stop$(this);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   public PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final PluginEndpoint $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof PluginMessage var6) {
               String pluginName = var6.pluginName();
               Object message = var6.message();
               Option var9 = this.$outer.org$apache$spark$internal$plugin$PluginEndpoint$$plugins.get(pluginName);
               if (var9 instanceof Some var10) {
                  DriverPlugin plugin = (DriverPlugin)var10.value();

                  try {
                     Object reply = plugin.receive(message);
                     if (reply != null) {
                        this.$outer.logWarning(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Plugin ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PLUGIN_NAME..MODULE$, pluginName)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"returned reply for one-way message of type "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, message.getClass().getName())}))))));
                        BoxedUnit var15 = BoxedUnit.UNIT;
                     } else {
                        BoxedUnit var16 = BoxedUnit.UNIT;
                     }
                  } catch (Exception var14) {
                     this.$outer.logWarning((LogEntry).MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error in plugin ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PLUGIN_NAME..MODULE$, pluginName)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"when handling message of type "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, message.getClass().getName())}))))), var14);
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (scala.None..MODULE$.equals(var9)) {
                  throw new IllegalArgumentException("Received message for unknown plugin " + pluginName + ".");
               } else {
                  throw new MatchError(var9);
               }
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            return x1 instanceof PluginMessage;
         }

         public {
            if (PluginEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = PluginEndpoint.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final PluginEndpoint $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (x1 instanceof PluginMessage var6) {
               String pluginName = var6.pluginName();
               Object message = var6.message();
               Option var9 = this.$outer.org$apache$spark$internal$plugin$PluginEndpoint$$plugins.get(pluginName);
               if (var9 instanceof Some var10) {
                  DriverPlugin plugin = (DriverPlugin)var10.value();
                  this.context$1.reply(plugin.receive(message));
                  BoxedUnit var10000 = BoxedUnit.UNIT;
                  return BoxedUnit.UNIT;
               } else if (scala.None..MODULE$.equals(var9)) {
                  throw new IllegalArgumentException("Received message for unknown plugin " + pluginName + ".");
               } else {
                  throw new MatchError(var9);
               }
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            return x1 instanceof PluginMessage;
         }

         public {
            if (PluginEndpoint.this == null) {
               throw null;
            } else {
               this.$outer = PluginEndpoint.this;
               this.context$1 = context$1;
            }
         }
      };
   }

   public PluginEndpoint(final Map plugins, final RpcEnv rpcEnv) {
      this.org$apache$spark$internal$plugin$PluginEndpoint$$plugins = plugins;
      this.rpcEnv = rpcEnv;
      RpcEndpoint.$init$(this);
      IsolatedThreadSafeRpcEndpoint.$init$(this);
      Logging.$init$(this);
   }
}
