package org.apache.spark;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple19;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class SSLOptions$ implements Logging, Serializable {
   public static final SSLOptions$ MODULE$ = new SSLOptions$();
   private static final String SPARK_RPC_SSL_KEY_PASSWORD_CONF;
   private static final String SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF;
   private static final String SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF;
   private static final String SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF;
   private static final Seq SPARK_RPC_SSL_PASSWORD_FIELDS;
   private static final String ENV_RPC_SSL_KEY_PASSWORD;
   private static final String ENV_RPC_SSL_PRIVATE_KEY_PASSWORD;
   private static final String ENV_RPC_SSL_KEY_STORE_PASSWORD;
   private static final String ENV_RPC_SSL_TRUST_STORE_PASSWORD;
   private static final Seq SPARK_RPC_SSL_PASSWORD_ENVS;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      SPARK_RPC_SSL_KEY_PASSWORD_CONF = "spark.ssl.rpc.keyPassword";
      SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF = "spark.ssl.rpc.privateKeyPassword";
      SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF = "spark.ssl.rpc.keyStorePassword";
      SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF = "spark.ssl.rpc.trustStorePassword";
      SPARK_RPC_SSL_PASSWORD_FIELDS = new .colon.colon(MODULE$.SPARK_RPC_SSL_KEY_PASSWORD_CONF(), new .colon.colon(MODULE$.SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF(), new .colon.colon(MODULE$.SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF(), new .colon.colon(MODULE$.SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF(), scala.collection.immutable.Nil..MODULE$))));
      ENV_RPC_SSL_KEY_PASSWORD = "_SPARK_SSL_RPC_KEY_PASSWORD";
      ENV_RPC_SSL_PRIVATE_KEY_PASSWORD = "_SPARK_SSL_RPC_PRIVATE_KEY_PASSWORD";
      ENV_RPC_SSL_KEY_STORE_PASSWORD = "_SPARK_SSL_RPC_KEY_STORE_PASSWORD";
      ENV_RPC_SSL_TRUST_STORE_PASSWORD = "_SPARK_SSL_RPC_TRUST_STORE_PASSWORD";
      SPARK_RPC_SSL_PASSWORD_ENVS = new .colon.colon(MODULE$.ENV_RPC_SSL_KEY_PASSWORD(), new .colon.colon(MODULE$.ENV_RPC_SSL_PRIVATE_KEY_PASSWORD(), new .colon.colon(MODULE$.ENV_RPC_SSL_KEY_STORE_PASSWORD(), new .colon.colon(MODULE$.ENV_RPC_SSL_TRUST_STORE_PASSWORD(), scala.collection.immutable.Nil..MODULE$))));
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
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Option $lessinit$greater$default$1() {
      return scala.None..MODULE$;
   }

   public boolean $lessinit$greater$default$2() {
      return false;
   }

   public Option $lessinit$greater$default$3() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$4() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$6() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$7() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$8() {
      return scala.None..MODULE$;
   }

   public boolean $lessinit$greater$default$9() {
      return false;
   }

   public Option $lessinit$greater$default$10() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$11() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$12() {
      return scala.None..MODULE$;
   }

   public Option $lessinit$greater$default$13() {
      return scala.None..MODULE$;
   }

   public boolean $lessinit$greater$default$14() {
      return false;
   }

   public int $lessinit$greater$default$15() {
      return 10000;
   }

   public boolean $lessinit$greater$default$16() {
      return false;
   }

   public Option $lessinit$greater$default$17() {
      return scala.None..MODULE$;
   }

   public Set $lessinit$greater$default$18() {
      return scala.Predef..MODULE$.Set().empty();
   }

   public Option $lessinit$greater$default$19() {
      return scala.None..MODULE$;
   }

   public SSLOptions parse(final SparkConf conf, final Configuration hadoopConf, final String ns, final Option defaults) {
      boolean var10000;
      label21: {
         label20: {
            String var6 = "spark.ssl.rpc";
            if (ns == null) {
               if (var6 == null) {
                  break label20;
               }
            } else if (ns.equals(var6)) {
               break label20;
            }

            var10000 = defaults.exists((x$18) -> BoxesRunTime.boxToBoolean($anonfun$parse$1(x$18)));
            break label21;
         }

         var10000 = false;
      }

      boolean enabledDefault = var10000;
      boolean enabled = conf.getBoolean(ns + ".enabled", enabledDefault);
      if (!enabled) {
         return new SSLOptions(this.$lessinit$greater$default$1(), this.$lessinit$greater$default$2(), this.$lessinit$greater$default$3(), this.$lessinit$greater$default$4(), this.$lessinit$greater$default$5(), this.$lessinit$greater$default$6(), this.$lessinit$greater$default$7(), this.$lessinit$greater$default$8(), this.$lessinit$greater$default$9(), this.$lessinit$greater$default$10(), this.$lessinit$greater$default$11(), this.$lessinit$greater$default$12(), this.$lessinit$greater$default$13(), this.$lessinit$greater$default$14(), this.$lessinit$greater$default$15(), this.$lessinit$greater$default$16(), this.$lessinit$greater$default$17(), this.$lessinit$greater$default$18(), this.$lessinit$greater$default$19());
      } else {
         Option port = conf.getWithSubstitution(ns + ".port").map((x$19) -> BoxesRunTime.boxToInteger($anonfun$parse$2(x$19)));
         port.foreach((JFunction1.mcVI.sp)(p) -> scala.Predef..MODULE$.require(p >= 0, () -> "Port number must be a non-negative value."));
         Option keyStore = conf.getWithSubstitution(ns + ".keyStore").map((x$20) -> new File(x$20)).orElse(() -> defaults.flatMap((x$21) -> x$21.keyStore()));
         Option keyStorePassword = conf.getWithSubstitution(ns + ".keyStorePassword").orElse(() -> scala.Option..MODULE$.apply(hadoopConf.getPassword(ns + ".keyStorePassword")).map((x$22) -> new String(x$22))).orElse(() -> scala.Option..MODULE$.apply(conf.getenv(MODULE$.ENV_RPC_SSL_KEY_STORE_PASSWORD())).filter((x$23) -> BoxesRunTime.boxToBoolean($anonfun$parse$11(x$23)))).orElse(() -> defaults.flatMap((x$24) -> x$24.keyStorePassword()));
         Option privateKey = conf.getOption(ns + ".privateKey").map((x$25) -> new File(x$25)).orElse(() -> defaults.flatMap((x$26) -> x$26.privateKey()));
         Option privateKeyPassword = conf.getWithSubstitution(ns + ".privateKeyPassword").orElse(() -> scala.Option..MODULE$.apply(conf.getenv(MODULE$.ENV_RPC_SSL_PRIVATE_KEY_PASSWORD())).filter((x$27) -> BoxesRunTime.boxToBoolean($anonfun$parse$18(x$27)))).orElse(() -> defaults.flatMap((x$28) -> x$28.privateKeyPassword()));
         Option keyPassword = conf.getWithSubstitution(ns + ".keyPassword").orElse(() -> scala.Option..MODULE$.apply(hadoopConf.getPassword(ns + ".keyPassword")).map((x$29) -> new String(x$29))).orElse(() -> scala.Option..MODULE$.apply(conf.getenv(MODULE$.ENV_RPC_SSL_KEY_PASSWORD())).filter((x$30) -> BoxesRunTime.boxToBoolean($anonfun$parse$24(x$30)))).orElse(() -> defaults.flatMap((x$31) -> x$31.keyPassword()));
         Option keyStoreType = conf.getWithSubstitution(ns + ".keyStoreType").orElse(() -> defaults.flatMap((x$32) -> x$32.keyStoreType()));
         Option certChain = conf.getOption(ns + ".certChain").map((x$33) -> new File(x$33)).orElse(() -> defaults.flatMap((x$34) -> x$34.certChain()));
         boolean needClientAuth = conf.getBoolean(ns + ".needClientAuth", defaults.exists((x$35) -> BoxesRunTime.boxToBoolean($anonfun$parse$32(x$35))));
         Option trustStore = conf.getWithSubstitution(ns + ".trustStore").map((x$36) -> new File(x$36)).orElse(() -> defaults.flatMap((x$37) -> x$37.trustStore()));
         Option trustStorePassword = conf.getWithSubstitution(ns + ".trustStorePassword").orElse(() -> scala.Option..MODULE$.apply(hadoopConf.getPassword(ns + ".trustStorePassword")).map((x$38) -> new String(x$38))).orElse(() -> scala.Option..MODULE$.apply(conf.getenv(MODULE$.ENV_RPC_SSL_TRUST_STORE_PASSWORD())).filter((x$39) -> BoxesRunTime.boxToBoolean($anonfun$parse$39(x$39)))).orElse(() -> defaults.flatMap((x$40) -> x$40.trustStorePassword()));
         Option trustStoreType = conf.getWithSubstitution(ns + ".trustStoreType").orElse(() -> defaults.flatMap((x$41) -> x$41.trustStoreType()));
         boolean trustStoreReloadingEnabled = conf.getBoolean(ns + ".trustStoreReloadingEnabled", defaults.exists((x$42) -> BoxesRunTime.boxToBoolean($anonfun$parse$44(x$42))));
         int trustStoreReloadIntervalMs = conf.getInt(ns + ".trustStoreReloadIntervalMs", BoxesRunTime.unboxToInt(defaults.map((x$43) -> BoxesRunTime.boxToInteger($anonfun$parse$45(x$43))).getOrElse((JFunction0.mcI.sp)() -> 10000)));
         boolean openSslEnabled = conf.getBoolean(ns + ".openSslEnabled", defaults.exists((x$44) -> BoxesRunTime.boxToBoolean($anonfun$parse$47(x$44))));
         Option protocol = conf.getWithSubstitution(ns + ".protocol").orElse(() -> defaults.flatMap((x$45) -> x$45.protocol()));
         Set enabledAlgorithms = (Set)conf.getWithSubstitution(ns + ".enabledAlgorithms").map((x$46) -> scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$46.split(",")), (x$47) -> x$47.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class))), (x$48) -> BoxesRunTime.boxToBoolean($anonfun$parse$52(x$48)))).toSet()).orElse(() -> defaults.map((x$49) -> x$49.enabledAlgorithms())).getOrElse(() -> scala.Predef..MODULE$.Set().empty());
         return new SSLOptions(new Some(ns), enabled, port, keyStore, keyStorePassword, privateKey, keyPassword, keyStoreType, needClientAuth, certChain, trustStore, trustStorePassword, trustStoreType, trustStoreReloadingEnabled, trustStoreReloadIntervalMs, openSslEnabled, protocol, enabledAlgorithms, privateKeyPassword);
      }
   }

   public Option parse$default$4() {
      return scala.None..MODULE$;
   }

   public String SPARK_RPC_SSL_KEY_PASSWORD_CONF() {
      return SPARK_RPC_SSL_KEY_PASSWORD_CONF;
   }

   public String SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF() {
      return SPARK_RPC_SSL_PRIVATE_KEY_PASSWORD_CONF;
   }

   public String SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF() {
      return SPARK_RPC_SSL_KEY_STORE_PASSWORD_CONF;
   }

   public String SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF() {
      return SPARK_RPC_SSL_TRUST_STORE_PASSWORD_CONF;
   }

   public Seq SPARK_RPC_SSL_PASSWORD_FIELDS() {
      return SPARK_RPC_SSL_PASSWORD_FIELDS;
   }

   public String ENV_RPC_SSL_KEY_PASSWORD() {
      return ENV_RPC_SSL_KEY_PASSWORD;
   }

   public String ENV_RPC_SSL_PRIVATE_KEY_PASSWORD() {
      return ENV_RPC_SSL_PRIVATE_KEY_PASSWORD;
   }

   public String ENV_RPC_SSL_KEY_STORE_PASSWORD() {
      return ENV_RPC_SSL_KEY_STORE_PASSWORD;
   }

   public String ENV_RPC_SSL_TRUST_STORE_PASSWORD() {
      return ENV_RPC_SSL_TRUST_STORE_PASSWORD;
   }

   public Seq SPARK_RPC_SSL_PASSWORD_ENVS() {
      return SPARK_RPC_SSL_PASSWORD_ENVS;
   }

   public SSLOptions apply(final Option namespace, final boolean enabled, final Option port, final Option keyStore, final Option keyStorePassword, final Option privateKey, final Option keyPassword, final Option keyStoreType, final boolean needClientAuth, final Option certChain, final Option trustStore, final Option trustStorePassword, final Option trustStoreType, final boolean trustStoreReloadingEnabled, final int trustStoreReloadIntervalMs, final boolean openSslEnabled, final Option protocol, final Set enabledAlgorithms, final Option privateKeyPassword) {
      return new SSLOptions(namespace, enabled, port, keyStore, keyStorePassword, privateKey, keyPassword, keyStoreType, needClientAuth, certChain, trustStore, trustStorePassword, trustStoreType, trustStoreReloadingEnabled, trustStoreReloadIntervalMs, openSslEnabled, protocol, enabledAlgorithms, privateKeyPassword);
   }

   public Option apply$default$1() {
      return scala.None..MODULE$;
   }

   public Option apply$default$10() {
      return scala.None..MODULE$;
   }

   public Option apply$default$11() {
      return scala.None..MODULE$;
   }

   public Option apply$default$12() {
      return scala.None..MODULE$;
   }

   public Option apply$default$13() {
      return scala.None..MODULE$;
   }

   public boolean apply$default$14() {
      return false;
   }

   public int apply$default$15() {
      return 10000;
   }

   public boolean apply$default$16() {
      return false;
   }

   public Option apply$default$17() {
      return scala.None..MODULE$;
   }

   public Set apply$default$18() {
      return scala.Predef..MODULE$.Set().empty();
   }

   public Option apply$default$19() {
      return scala.None..MODULE$;
   }

   public boolean apply$default$2() {
      return false;
   }

   public Option apply$default$3() {
      return scala.None..MODULE$;
   }

   public Option apply$default$4() {
      return scala.None..MODULE$;
   }

   public Option apply$default$5() {
      return scala.None..MODULE$;
   }

   public Option apply$default$6() {
      return scala.None..MODULE$;
   }

   public Option apply$default$7() {
      return scala.None..MODULE$;
   }

   public Option apply$default$8() {
      return scala.None..MODULE$;
   }

   public boolean apply$default$9() {
      return false;
   }

   public Option unapply(final SSLOptions x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple19(x$0.namespace(), BoxesRunTime.boxToBoolean(x$0.enabled()), x$0.port(), x$0.keyStore(), x$0.keyStorePassword(), x$0.privateKey(), x$0.keyPassword(), x$0.keyStoreType(), BoxesRunTime.boxToBoolean(x$0.needClientAuth()), x$0.certChain(), x$0.trustStore(), x$0.trustStorePassword(), x$0.trustStoreType(), BoxesRunTime.boxToBoolean(x$0.trustStoreReloadingEnabled()), BoxesRunTime.boxToInteger(x$0.trustStoreReloadIntervalMs()), BoxesRunTime.boxToBoolean(x$0.openSslEnabled()), x$0.protocol(), x$0.enabledAlgorithms(), x$0.privateKeyPassword())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SSLOptions$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$1(final SSLOptions x$18) {
      return x$18.enabled();
   }

   // $FF: synthetic method
   public static final int $anonfun$parse$2(final String x$19) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$19));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$11(final String x$23) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$23.trim()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$18(final String x$27) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$27.trim()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$24(final String x$30) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$30.trim()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$32(final SSLOptions x$35) {
      return x$35.needClientAuth();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$39(final String x$39) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$39.trim()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$44(final SSLOptions x$42) {
      return x$42.trustStoreReloadingEnabled();
   }

   // $FF: synthetic method
   public static final int $anonfun$parse$45(final SSLOptions x$43) {
      return x$43.trustStoreReloadIntervalMs();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$47(final SSLOptions x$44) {
      return x$44.openSslEnabled();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$parse$52(final String x$48) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$48));
   }

   private SSLOptions$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
