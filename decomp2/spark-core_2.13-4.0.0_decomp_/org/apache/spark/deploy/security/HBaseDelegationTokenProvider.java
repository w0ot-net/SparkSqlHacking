package org.apache.spark.deploy.security;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.token.Token;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.security.HadoopDelegationTokenProvider;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.runtime.package.;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194Qa\u0002\u0005\u0001\u0011IAQ\u0001\n\u0001\u0005\u0002\u0019BQ!\u000b\u0001\u0005B)BQA\u000e\u0001\u0005B]BQ!\u0016\u0001\u0005\nYCQ\u0001\u0018\u0001\u0005BuCQa\u0019\u0001\u0005\n\u0011\u0014A\u0004\u0013\"bg\u0016$U\r\\3hCRLwN\u001c+pW\u0016t\u0007K]8wS\u0012,'O\u0003\u0002\n\u0015\u0005A1/Z2ve&$\u0018P\u0003\u0002\f\u0019\u00051A-\u001a9m_fT!!\u0004\b\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0001\u0012AB1qC\u000eDWMC\u0001\u0012\u0003\ry'oZ\n\u0005\u0001MIb\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035qi\u0011a\u0007\u0006\u0003\u00131I!!H\u000e\u0003;!\u000bGm\\8q\t\u0016dWmZ1uS>tGk\\6f]B\u0013xN^5eKJ\u0004\"a\b\u0012\u000e\u0003\u0001R!!\t\u0007\u0002\u0011%tG/\u001a:oC2L!a\t\u0011\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001(!\tA\u0003!D\u0001\t\u0003-\u0019XM\u001d<jG\u0016t\u0015-\\3\u0016\u0003-\u0002\"\u0001L\u001a\u000f\u00055\n\u0004C\u0001\u0018\u0016\u001b\u0005y#B\u0001\u0019&\u0003\u0019a$o\\8u}%\u0011!'F\u0001\u0007!J,G-\u001a4\n\u0005Q*$AB*ue&twM\u0003\u00023+\u00051rN\u0019;bS:$U\r\\3hCRLwN\u001c+pW\u0016t7\u000f\u0006\u00039}!s\u0005c\u0001\u000b:w%\u0011!(\u0006\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005Qa\u0014BA\u001f\u0016\u0005\u0011auN\\4\t\u000b}\u001a\u0001\u0019\u0001!\u0002\u0015!\fGm\\8q\u0007>tg\r\u0005\u0002B\r6\t!I\u0003\u0002D\t\u0006!1m\u001c8g\u0015\t)e\"\u0001\u0004iC\u0012|w\u000e]\u0005\u0003\u000f\n\u0013QbQ8oM&<WO]1uS>t\u0007\"B%\u0004\u0001\u0004Q\u0015!C:qCJ\\7i\u001c8g!\tYE*D\u0001\r\u0013\tiEBA\u0005Ta\u0006\u00148nQ8oM\")qj\u0001a\u0001!\u0006)1M]3egB\u0011\u0011kU\u0007\u0002%*\u0011\u0011\u0002R\u0005\u0003)J\u00131b\u0011:fI\u0016tG/[1mg\u0006\u0019sN\u0019;bS:$U\r\\3hCRLwN\u001c+pW\u0016t7oV5uQ\"\u0013\u0015m]3D_:tGcA,[7B\u0011A\u0003W\u0005\u00033V\u0011A!\u00168ji\")q\b\u0002a\u0001\u0001\")q\n\u0002a\u0001!\u0006AB-\u001a7fO\u0006$\u0018n\u001c8U_.,gn\u001d*fcVL'/\u001a3\u0015\u0007y\u000b'\r\u0005\u0002\u0015?&\u0011\u0001-\u0006\u0002\b\u0005>|G.Z1o\u0011\u0015IU\u00011\u0001K\u0011\u0015yT\u00011\u0001A\u0003%A'-Y:f\u0007>tg\r\u0006\u0002AK\")1I\u0002a\u0001\u0001\u0002"
)
public class HBaseDelegationTokenProvider implements HadoopDelegationTokenProvider, Logging {
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

   public String serviceName() {
      return "hbase";
   }

   public Option obtainDelegationTokens(final Configuration hadoopConf, final SparkConf sparkConf, final Credentials creds) {
      try {
         JavaUniverse.JavaMirror mirror = .MODULE$.universe().runtimeMirror(Utils$.MODULE$.getContextOrSparkClassLoader());
         Method obtainToken = mirror.classLoader().loadClass("org.apache.hadoop.hbase.security.token.TokenUtil").getMethod("obtainToken", Configuration.class);
         this.logDebug((Function0)(() -> "Attempting to fetch HBase security token."));
         Token token = (Token)obtainToken.invoke((Object)null, this.hbaseConf(hadoopConf));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Get token from HBase: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN..MODULE$, token.toString())})))));
         creds.addToken(token.getService(), token);
      } catch (Throwable var11) {
         if (var11 == null || !scala.util.control.NonFatal..MODULE$.apply(var11)) {
            throw var11;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> Utils$.MODULE$.createFailedToGetTokenMessage(this.serviceName(), var11).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Retrying to fetch "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"HBase security token with ", " connection parameter."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, this.serviceName())}))))));
         this.obtainDelegationTokensWithHBaseConn(hadoopConf, creds);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return scala.None..MODULE$;
   }

   private void obtainDelegationTokensWithHBaseConn(final Configuration hadoopConf, final Credentials creds) {
      Closeable hbaseConnection = null;

      try {
         JavaUniverse.JavaMirror mirror = .MODULE$.universe().runtimeMirror(Utils$.MODULE$.getContextOrSparkClassLoader());
         Method connectionFactoryClass = mirror.classLoader().loadClass("org.apache.hadoop.hbase.client.ConnectionFactory").getMethod("createConnection", Configuration.class);
         hbaseConnection = (Closeable)connectionFactoryClass.invoke((Object)null, this.hbaseConf(hadoopConf));
         Class connectionParamTypeClassRef = mirror.classLoader().loadClass("org.apache.hadoop.hbase.client.Connection");
         Method obtainTokenMethod = mirror.classLoader().loadClass("org.apache.hadoop.hbase.security.token.TokenUtil").getMethod("obtainToken", connectionParamTypeClassRef);
         this.logDebug((Function0)(() -> "Attempting to fetch HBase security token."));
         Token token = (Token)obtainTokenMethod.invoke((Object)null, hbaseConnection);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Get token from HBase: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOKEN..MODULE$, token.toString())})))));
         creds.addToken(token.getService(), token);
      } catch (Throwable var16) {
         if (var16 == null || !scala.util.control.NonFatal..MODULE$.apply(var16)) {
            throw var16;
         }

         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> Utils$.MODULE$.createFailedToGetTokenMessage(this.serviceName(), var16)));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } finally {
         if (hbaseConnection != null) {
            hbaseConnection.close();
         }

      }

   }

   public boolean delegationTokensRequired(final SparkConf sparkConf, final Configuration hadoopConf) {
      boolean var4;
      label23: {
         String var10000 = this.hbaseConf(hadoopConf).get("hbase.security.authentication");
         String var3 = "kerberos";
         if (var10000 == null) {
            if (var3 == null) {
               break label23;
            }
         } else if (var10000.equals(var3)) {
            break label23;
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   private Configuration hbaseConf(final Configuration conf) {
      Configuration var10000;
      try {
         JavaUniverse.JavaMirror mirror = .MODULE$.universe().runtimeMirror(Utils$.MODULE$.getContextOrSparkClassLoader());
         Method confCreate = mirror.classLoader().loadClass("org.apache.hadoop.hbase.HBaseConfiguration").getMethod("create", Configuration.class);
         var10000 = (Configuration)confCreate.invoke((Object)null, conf);
      } catch (Throwable var8) {
         if (var8 == null || !scala.util.control.NonFatal..MODULE$.apply(var8)) {
            throw var8;
         }

         this.logDebug((Function0)(() -> "Unable to load HBaseConfiguration."), var8);
         var10000 = conf;
      }

      return var10000;
   }

   public HBaseDelegationTokenProvider() {
      Logging.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
