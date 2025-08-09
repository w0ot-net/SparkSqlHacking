package org.apache.spark.resource;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Optional;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.api.resource.ResourceDiscoveryPlugin;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00113Aa\u0001\u0003\u0001\u001b!)1\u0005\u0001C\u0001I!)q\u0005\u0001C!Q\ti\"+Z:pkJ\u001cW\rR5tG>4XM]=TGJL\u0007\u000f\u001e)mk\u001eLgN\u0003\u0002\u0006\r\u0005A!/Z:pkJ\u001cWM\u0003\u0002\b\u0011\u0005)1\u000f]1sW*\u0011\u0011BC\u0001\u0007CB\f7\r[3\u000b\u0003-\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\b\u0017;A\u0011q\u0002F\u0007\u0002!)\u0011\u0011CE\u0001\u0005Y\u0006twMC\u0001\u0014\u0003\u0011Q\u0017M^1\n\u0005U\u0001\"AB(cU\u0016\u001cG\u000f\u0005\u0002\u001875\t\u0001D\u0003\u0002\u00063)\u0011!DB\u0001\u0004CBL\u0017B\u0001\u000f\u0019\u0005]\u0011Vm]8ve\u000e,G)[:d_Z,'/\u001f)mk\u001eLg\u000e\u0005\u0002\u001fC5\tqD\u0003\u0002!\r\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002#?\t9Aj\\4hS:<\u0017A\u0002\u001fj]&$h\bF\u0001&!\t1\u0003!D\u0001\u0005\u0003A!\u0017n]2pm\u0016\u0014(+Z:pkJ\u001cW\rF\u0002*e]\u00022AK\u00170\u001b\u0005Y#B\u0001\u0017\u0013\u0003\u0011)H/\u001b7\n\u00059Z#\u0001C(qi&|g.\u00197\u0011\u0005\u0019\u0002\u0014BA\u0019\u0005\u0005M\u0011Vm]8ve\u000e,\u0017J\u001c4pe6\fG/[8o\u0011\u0015\u0019$\u00011\u00015\u0003\u001d\u0011X-];fgR\u0004\"AJ\u001b\n\u0005Y\"!a\u0004*fg>,(oY3SKF,Xm\u001d;\t\u000ba\u0012\u0001\u0019A\u001d\u0002\u0013M\u0004\u0018M]6D_:4\u0007C\u0001\u001e<\u001b\u00051\u0011B\u0001\u001f\u0007\u0005%\u0019\u0006/\u0019:l\u0007>tg\r\u000b\u0002\u0001}A\u0011qHQ\u0007\u0002\u0001*\u0011\u0011IB\u0001\u000bC:tw\u000e^1uS>t\u0017BA\"A\u00051!UM^3m_B,'/\u00119j\u0001"
)
public class ResourceDiscoveryScriptPlugin implements ResourceDiscoveryPlugin, Logging {
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

   public Optional discoverResource(final ResourceRequest request, final SparkConf sparkConf) {
      Optional script = request.discoveryScript();
      String resourceName = request.id().resourceName();
      if (script.isPresent()) {
         File scriptFile = new File((String)script.get());
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Discovering resources for ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_NAME..MODULE$, resourceName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" with script: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, scriptFile)}))))));
         if (scriptFile.exists()) {
            String output = Utils$.MODULE$.executeAndGetOutput(new scala.collection.immutable..colon.colon((String)script.get(), scala.collection.immutable.Nil..MODULE$), new File("."), Utils$.MODULE$.executeAndGetOutput$default$3(), Utils$.MODULE$.executeAndGetOutput$default$4());
            ResourceInformation result = ResourceInformation$.MODULE$.parseJson(output);
            if (!result.name().equals(resourceName)) {
               Object var10002 = script.get();
               throw new SparkException("Error running the resource discovery script " + var10002 + ": script returned resource name " + result.name() + " and we were expecting " + resourceName + ".");
            } else {
               return Optional.of(result);
            }
         } else {
            throw new SparkException("Resource script: " + scriptFile + " to discover " + resourceName + " doesn't exist!");
         }
      } else {
         throw new SparkException("User is expecting to use resource: " + resourceName + ", but didn't specify a discovery script!");
      }
   }

   public ResourceDiscoveryScriptPlugin() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
