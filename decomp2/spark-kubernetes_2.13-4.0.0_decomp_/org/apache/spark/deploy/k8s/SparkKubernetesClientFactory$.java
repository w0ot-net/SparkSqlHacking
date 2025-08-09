package org.apache.spark.deploy.k8s;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.ConfigFluent;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkConf;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.slf4j.Logger;
import org.sparkproject.guava.base.Charsets;
import org.sparkproject.guava.io.Files;
import scala.Enumeration;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@Stable
@DeveloperApi
public final class SparkKubernetesClientFactory$ implements Logging {
   public static final SparkKubernetesClientFactory$ MODULE$ = new SparkKubernetesClientFactory$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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

   public KubernetesClient createKubernetesClient(final String master, final Option namespace, final String kubernetesAuthConfPrefix, final Enumeration.Value clientType, final SparkConf sparkConf, final Option defaultServiceAccountCaCert) {
      String oauthTokenFileConf = kubernetesAuthConfPrefix + "." + Config$.MODULE$.OAUTH_TOKEN_FILE_CONF_SUFFIX();
      String oauthTokenConf = kubernetesAuthConfPrefix + "." + Config$.MODULE$.OAUTH_TOKEN_CONF_SUFFIX();
      Option oauthTokenFile = sparkConf.getOption(oauthTokenFileConf).map((x$1) -> new File(x$1));
      Option oauthTokenValue = sparkConf.getOption(oauthTokenConf);
      KubernetesUtils$.MODULE$.requireNandDefined(oauthTokenFile, oauthTokenValue, "Cannot specify OAuth token through both a file " + oauthTokenFileConf + " and a value " + oauthTokenConf + ".");
      Option caCertFile = sparkConf.getOption(kubernetesAuthConfPrefix + "." + Config$.MODULE$.CA_CERT_FILE_CONF_SUFFIX()).orElse(() -> defaultServiceAccountCaCert.map((x$2) -> x$2.getAbsolutePath()));
      Option clientKeyFile = sparkConf.getOption(kubernetesAuthConfPrefix + "." + Config$.MODULE$.CLIENT_KEY_FILE_CONF_SUFFIX());
      Option clientCertFile = sparkConf.getOption(kubernetesAuthConfPrefix + "." + Config$.MODULE$.CLIENT_CERT_FILE_CONF_SUFFIX());
      Option kubeContext = ((Option)sparkConf.get(Config$.MODULE$.KUBERNETES_CONTEXT())).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$createKubernetesClient$4(x$3)));
      this.logInfo(.MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Auto-configuring K8S client using "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.K8S_CONTEXT..MODULE$, kubeContext.map((x$4) -> "context " + x$4).getOrElse(() -> "current context"))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" from users K8S config file"})))).log(scala.collection.immutable.Nil..MODULE$))));
      if (Utils.getSystemPropertyOrEnvVar("kubernetes.request.retry.backoffLimit") == null) {
         System.setProperty("kubernetes.request.retry.backoffLimit", "3");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      io.fabric8.kubernetes.client.Config config = SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.OptionConfigurableConfigBuilder(SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.OptionConfigurableConfigBuilder(SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.OptionConfigurableConfigBuilder(SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.OptionConfigurableConfigBuilder(SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.OptionConfigurableConfigBuilder(SparkKubernetesClientFactory.OptionConfigurableConfigBuilder$.MODULE$.withOption$extension(this.OptionConfigurableConfigBuilder((ConfigBuilder)((ConfigFluent)(new ConfigBuilder(io.fabric8.kubernetes.client.Config.autoConfigure((String)kubeContext.orNull(scala..less.colon.less..MODULE$.refl())))).withApiVersion("v1").withMasterUrl(master)).withRequestTimeout(SparkKubernetesClientFactory.ClientType$.MODULE$.convert(clientType).requestTimeout(sparkConf)).withConnectionTimeout(SparkKubernetesClientFactory.ClientType$.MODULE$.convert(clientType).connectionTimeout(sparkConf)).withTrustCerts(BoxesRunTime.unboxToBoolean(sparkConf.get(Config$.MODULE$.KUBERNETES_TRUST_CERTIFICATES())))), oauthTokenValue, (token, configBuilder) -> (ConfigBuilder)configBuilder.withOauthToken(token))), oauthTokenFile, (file, configBuilder) -> (ConfigBuilder)configBuilder.withOauthToken(Files.asCharSource(file, Charsets.UTF_8).read()))), caCertFile, (file, configBuilder) -> (ConfigBuilder)configBuilder.withCaCertFile(file))), clientKeyFile, (file, configBuilder) -> (ConfigBuilder)configBuilder.withClientKeyFile(file))), clientCertFile, (file, configBuilder) -> (ConfigBuilder)configBuilder.withClientCertFile(file))), namespace, (ns, configBuilder) -> (ConfigBuilder)configBuilder.withNamespace(ns)).build();
      this.logDebug((Function0)(() -> "Kubernetes client config: " + (new ObjectMapper()).writerWithDefaultPrettyPrinter().writeValueAsString(config)));
      return (new KubernetesClientBuilder()).withConfig(config).build();
   }

   private ConfigBuilder OptionConfigurableConfigBuilder(final ConfigBuilder configBuilder) {
      return configBuilder;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createKubernetesClient$4(final String x$3) {
      return scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$3));
   }

   private SparkKubernetesClientFactory$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
