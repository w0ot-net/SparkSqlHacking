package org.apache.spark.internal.config;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.spark.network.util.ByteUnit;
import scala.collection.immutable.Set;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;

public final class UI$ {
   public static final UI$ MODULE$ = new UI$();
   private static final ConfigEntry UI_SHOW_CONSOLE_PROGRESS = (new ConfigBuilder("spark.ui.showConsoleProgress")).doc("When true, show the progress bar in the console.").version("1.2.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry UI_CONSOLE_PROGRESS_UPDATE_INTERVAL;
   private static final ConfigEntry UI_ENABLED;
   private static final ConfigEntry UI_PORT;
   private static final ConfigEntry UI_FILTERS;
   private static final OptionalConfigEntry UI_ALLOW_FRAMING_FROM;
   private static final ConfigEntry UI_REVERSE_PROXY;
   private static final OptionalConfigEntry UI_REVERSE_PROXY_URL;
   private static final ConfigEntry UI_KILL_ENABLED;
   private static final ConfigEntry UI_THREAD_DUMPS_ENABLED;
   private static final ConfigEntry UI_FLAMEGRAPH_ENABLED;
   private static final ConfigEntry UI_HEAP_HISTOGRAM_ENABLED;
   private static final ConfigEntry UI_PROMETHEUS_ENABLED;
   private static final ConfigEntry UI_X_XSS_PROTECTION;
   private static final ConfigEntry UI_X_CONTENT_TYPE_OPTIONS;
   private static final OptionalConfigEntry UI_STRICT_TRANSPORT_SECURITY;
   private static final ConfigEntry UI_REQUEST_HEADER_SIZE;
   private static final ConfigEntry UI_TIMELINE_ENABLED;
   private static final ConfigEntry UI_TIMELINE_TASKS_MAXIMUM;
   private static final ConfigEntry UI_TIMELINE_JOBS_MAXIMUM;
   private static final ConfigEntry UI_TIMELINE_STAGES_MAXIMUM;
   private static final ConfigEntry UI_TIMELINE_EXECUTORS_MAXIMUM;
   private static final ConfigEntry ACLS_ENABLE;
   private static final ConfigEntry UI_VIEW_ACLS;
   private static final ConfigEntry UI_VIEW_ACLS_GROUPS;
   private static final ConfigEntry ADMIN_ACLS;
   private static final ConfigEntry ADMIN_ACLS_GROUPS;
   private static final ConfigEntry MODIFY_ACLS;
   private static final ConfigEntry MODIFY_ACLS_GROUPS;
   private static final ConfigEntry USER_GROUPS_MAPPING;
   private static final OptionalConfigEntry PROXY_REDIRECT_URI;
   private static final OptionalConfigEntry CUSTOM_EXECUTOR_LOG_URL;
   private static final ConfigEntry MASTER_UI_DECOMMISSION_ALLOW_MODE;
   private static final OptionalConfigEntry MASTER_UI_TITLE;
   private static final ConfigEntry MASTER_UI_VISIBLE_ENV_VAR_PREFIXES;
   private static final ConfigEntry UI_SQL_GROUP_SUB_EXECUTION_ENABLED;
   private static final ConfigEntry UI_JETTY_STOP_TIMEOUT;

   static {
      UI_CONSOLE_PROGRESS_UPDATE_INTERVAL = (new ConfigBuilder("spark.ui.consoleProgress.update.interval")).version("2.1.0").timeConf(TimeUnit.MILLISECONDS).createWithDefault(BoxesRunTime.boxToLong(200L));
      UI_ENABLED = (new ConfigBuilder("spark.ui.enabled")).doc("Whether to run the web UI for the Spark application.").version("1.1.1").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_PORT = (new ConfigBuilder("spark.ui.port")).doc("Port for your application's dashboard, which shows memory and workload data.").version("0.7.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(4040));
      UI_FILTERS = (new ConfigBuilder("spark.ui.filters")).doc("Comma separated list of filter class names to apply to the Spark Web UI.").version("1.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      UI_ALLOW_FRAMING_FROM = (new ConfigBuilder("spark.ui.allowFramingFrom")).version("1.6.0").stringConf().createOptional();
      UI_REVERSE_PROXY = (new ConfigBuilder("spark.ui.reverseProxy")).doc("Enable running Spark Master as reverse proxy for worker and application UIs. In this mode, Spark master will reverse proxy the worker and application UIs to enable access without requiring direct access to their hosts. Use it with caution, as worker and application UI will not be accessible directly, you will only be able to access themthrough spark master/proxy public URL. This setting affects all the workers and application UIs running in the cluster and must be set on all the workers, drivers  and masters.").version("2.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      UI_REVERSE_PROXY_URL = (new ConfigBuilder("spark.ui.reverseProxyUrl")).doc("This is the URL where your proxy is running. This URL is for proxy which is running in front of Spark Master. This is useful when running proxy for authentication e.g. OAuth proxy. Make sure this is a complete URL including scheme (http/https) and port to reach your proxy.").version("2.1.0").stringConf().checkValue((s) -> BoxesRunTime.boxToBoolean($anonfun$UI_REVERSE_PROXY_URL$1(s)), "Cannot use the keyword 'proxy' or 'history' in reverse proxy URL. Spark UI relies on both keywords for getting REST API endpoints from URIs.").createOptional();
      UI_KILL_ENABLED = (new ConfigBuilder("spark.ui.killEnabled")).doc("Allows jobs and stages to be killed from the web UI.").version("1.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_THREAD_DUMPS_ENABLED = (new ConfigBuilder("spark.ui.threadDumpsEnabled")).doc("Whether to show a link for executor thread dumps in Stages and Executor pages.").version("1.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_FLAMEGRAPH_ENABLED = (new ConfigBuilder("spark.ui.threadDump.flamegraphEnabled")).doc("Whether to render the Flamegraph for executor thread dumps").version("4.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_HEAP_HISTOGRAM_ENABLED = (new ConfigBuilder("spark.ui.heapHistogramEnabled")).doc("Whether to show a link for executor heap histogram in Executor page.").version("3.5.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_PROMETHEUS_ENABLED = (new ConfigBuilder("spark.ui.prometheus.enabled")).internal().doc("Expose executor metrics at /metrics/executors/prometheus. For master/worker/driver metrics, you need to configure `conf/metrics.properties`.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_X_XSS_PROTECTION = (new ConfigBuilder("spark.ui.xXssProtection")).doc("Value for HTTP X-XSS-Protection response header").version("2.3.0").stringConf().createWithDefaultString("1; mode=block");
      UI_X_CONTENT_TYPE_OPTIONS = (new ConfigBuilder("spark.ui.xContentTypeOptions.enabled")).doc("Set to 'true' for setting X-Content-Type-Options HTTP response header to 'nosniff'").version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_STRICT_TRANSPORT_SECURITY = (new ConfigBuilder("spark.ui.strictTransportSecurity")).doc("Value for HTTP Strict Transport Security Response Header").version("2.3.0").stringConf().createOptional();
      UI_REQUEST_HEADER_SIZE = (new ConfigBuilder("spark.ui.requestHeaderSize")).doc("Value for HTTP request header size in bytes.").version("2.2.3").bytesConf(ByteUnit.BYTE).createWithDefaultString("8k");
      UI_TIMELINE_ENABLED = (new ConfigBuilder("spark.ui.timelineEnabled")).doc("Whether to display event timeline data on UI pages.").version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_TIMELINE_TASKS_MAXIMUM = (new ConfigBuilder("spark.ui.timeline.tasks.maximum")).version("1.4.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1000));
      UI_TIMELINE_JOBS_MAXIMUM = (new ConfigBuilder("spark.ui.timeline.jobs.maximum")).version("3.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(500));
      UI_TIMELINE_STAGES_MAXIMUM = (new ConfigBuilder("spark.ui.timeline.stages.maximum")).version("3.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(500));
      UI_TIMELINE_EXECUTORS_MAXIMUM = (new ConfigBuilder("spark.ui.timeline.executors.maximum")).version("3.2.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(250));
      ACLS_ENABLE = (new ConfigBuilder("spark.acls.enable")).version("1.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      UI_VIEW_ACLS = (new ConfigBuilder("spark.ui.view.acls")).version("1.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      UI_VIEW_ACLS_GROUPS = (new ConfigBuilder("spark.ui.view.acls.groups")).version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      ADMIN_ACLS = (new ConfigBuilder("spark.admin.acls")).version("1.1.0").stringConf().toSequence().createWithDefault(.MODULE$);
      ADMIN_ACLS_GROUPS = (new ConfigBuilder("spark.admin.acls.groups")).version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      MODIFY_ACLS = (new ConfigBuilder("spark.modify.acls")).version("1.1.0").stringConf().toSequence().createWithDefault(.MODULE$);
      MODIFY_ACLS_GROUPS = (new ConfigBuilder("spark.modify.acls.groups")).version("2.0.0").stringConf().toSequence().createWithDefault(.MODULE$);
      USER_GROUPS_MAPPING = (new ConfigBuilder("spark.user.groups.mapping")).version("2.0.0").stringConf().createWithDefault("org.apache.spark.security.ShellBasedGroupsMappingProvider");
      PROXY_REDIRECT_URI = (new ConfigBuilder("spark.ui.proxyRedirectUri")).doc("Proxy address to use when responding with HTTP redirects.").version("3.0.0").stringConf().createOptional();
      CUSTOM_EXECUTOR_LOG_URL = (new ConfigBuilder("spark.ui.custom.executor.log.url")).doc("Specifies custom spark executor log url for supporting external log service instead of using cluster managers' application log urls in the Spark UI. Spark will support some path variables via patterns which can vary on cluster manager. Please check the documentation for your cluster manager to see which patterns are supported, if any. This configuration replaces original log urls in event log, which will be also effective when accessing the application on history server. The new log urls must be permanent, otherwise you might have dead link for executor log urls.").version("3.0.0").stringConf().createOptional();
      MASTER_UI_DECOMMISSION_ALLOW_MODE = (new ConfigBuilder("spark.master.ui.decommission.allow.mode")).doc("Specifies the behavior of the Master Web UI's /workers/kill endpoint. Possible choices are: `LOCAL` means allow this endpoint from IP's that are local to the machine running the Master, `DENY` means to completely disable this endpoint, `ALLOW` means to allow calling this endpoint from any IP.").internal().version("3.1.0").stringConf().transform((x$1) -> x$1.toUpperCase(Locale.ROOT)).checkValues((Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ALLOW", "LOCAL", "DENY"})))).createWithDefault("LOCAL");
      MASTER_UI_TITLE = (new ConfigBuilder("spark.master.ui.title")).version("4.0.0").doc("Specifies the title of the Master UI page. If unset, `Spark Master at <MasterURL>` is used by default.").stringConf().createOptional();
      MASTER_UI_VISIBLE_ENV_VAR_PREFIXES = (new ConfigBuilder("spark.master.ui.visibleEnvVarPrefixes")).doc("Comma-separated list of key-prefix strings to show environment variables").version("4.0.0").stringConf().toSequence().createWithDefault(scala.package..MODULE$.Seq().empty());
      UI_SQL_GROUP_SUB_EXECUTION_ENABLED = (new ConfigBuilder("spark.ui.groupSQLSubExecutionEnabled")).doc("Whether to group sub executions together in SQL UI when they belong to the same root execution").version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      UI_JETTY_STOP_TIMEOUT = (new ConfigBuilder("spark.ui.jettyStopTimeout")).internal().doc("Timeout for Jetty servers started in UIs, such as SparkUI, HistoryUI, etc, to stop.").version("4.0.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("30s");
   }

   public ConfigEntry UI_SHOW_CONSOLE_PROGRESS() {
      return UI_SHOW_CONSOLE_PROGRESS;
   }

   public ConfigEntry UI_CONSOLE_PROGRESS_UPDATE_INTERVAL() {
      return UI_CONSOLE_PROGRESS_UPDATE_INTERVAL;
   }

   public ConfigEntry UI_ENABLED() {
      return UI_ENABLED;
   }

   public ConfigEntry UI_PORT() {
      return UI_PORT;
   }

   public ConfigEntry UI_FILTERS() {
      return UI_FILTERS;
   }

   public OptionalConfigEntry UI_ALLOW_FRAMING_FROM() {
      return UI_ALLOW_FRAMING_FROM;
   }

   public ConfigEntry UI_REVERSE_PROXY() {
      return UI_REVERSE_PROXY;
   }

   public OptionalConfigEntry UI_REVERSE_PROXY_URL() {
      return UI_REVERSE_PROXY_URL;
   }

   public ConfigEntry UI_KILL_ENABLED() {
      return UI_KILL_ENABLED;
   }

   public ConfigEntry UI_THREAD_DUMPS_ENABLED() {
      return UI_THREAD_DUMPS_ENABLED;
   }

   public ConfigEntry UI_FLAMEGRAPH_ENABLED() {
      return UI_FLAMEGRAPH_ENABLED;
   }

   public ConfigEntry UI_HEAP_HISTOGRAM_ENABLED() {
      return UI_HEAP_HISTOGRAM_ENABLED;
   }

   public ConfigEntry UI_PROMETHEUS_ENABLED() {
      return UI_PROMETHEUS_ENABLED;
   }

   public ConfigEntry UI_X_XSS_PROTECTION() {
      return UI_X_XSS_PROTECTION;
   }

   public ConfigEntry UI_X_CONTENT_TYPE_OPTIONS() {
      return UI_X_CONTENT_TYPE_OPTIONS;
   }

   public OptionalConfigEntry UI_STRICT_TRANSPORT_SECURITY() {
      return UI_STRICT_TRANSPORT_SECURITY;
   }

   public ConfigEntry UI_REQUEST_HEADER_SIZE() {
      return UI_REQUEST_HEADER_SIZE;
   }

   public ConfigEntry UI_TIMELINE_ENABLED() {
      return UI_TIMELINE_ENABLED;
   }

   public ConfigEntry UI_TIMELINE_TASKS_MAXIMUM() {
      return UI_TIMELINE_TASKS_MAXIMUM;
   }

   public ConfigEntry UI_TIMELINE_JOBS_MAXIMUM() {
      return UI_TIMELINE_JOBS_MAXIMUM;
   }

   public ConfigEntry UI_TIMELINE_STAGES_MAXIMUM() {
      return UI_TIMELINE_STAGES_MAXIMUM;
   }

   public ConfigEntry UI_TIMELINE_EXECUTORS_MAXIMUM() {
      return UI_TIMELINE_EXECUTORS_MAXIMUM;
   }

   public ConfigEntry ACLS_ENABLE() {
      return ACLS_ENABLE;
   }

   public ConfigEntry UI_VIEW_ACLS() {
      return UI_VIEW_ACLS;
   }

   public ConfigEntry UI_VIEW_ACLS_GROUPS() {
      return UI_VIEW_ACLS_GROUPS;
   }

   public ConfigEntry ADMIN_ACLS() {
      return ADMIN_ACLS;
   }

   public ConfigEntry ADMIN_ACLS_GROUPS() {
      return ADMIN_ACLS_GROUPS;
   }

   public ConfigEntry MODIFY_ACLS() {
      return MODIFY_ACLS;
   }

   public ConfigEntry MODIFY_ACLS_GROUPS() {
      return MODIFY_ACLS_GROUPS;
   }

   public ConfigEntry USER_GROUPS_MAPPING() {
      return USER_GROUPS_MAPPING;
   }

   public OptionalConfigEntry PROXY_REDIRECT_URI() {
      return PROXY_REDIRECT_URI;
   }

   public OptionalConfigEntry CUSTOM_EXECUTOR_LOG_URL() {
      return CUSTOM_EXECUTOR_LOG_URL;
   }

   public ConfigEntry MASTER_UI_DECOMMISSION_ALLOW_MODE() {
      return MASTER_UI_DECOMMISSION_ALLOW_MODE;
   }

   public OptionalConfigEntry MASTER_UI_TITLE() {
      return MASTER_UI_TITLE;
   }

   public ConfigEntry MASTER_UI_VISIBLE_ENV_VAR_PREFIXES() {
      return MASTER_UI_VISIBLE_ENV_VAR_PREFIXES;
   }

   public ConfigEntry UI_SQL_GROUP_SUB_EXECUTION_ENABLED() {
      return UI_SQL_GROUP_SUB_EXECUTION_ENABLED;
   }

   public ConfigEntry UI_JETTY_STOP_TIMEOUT() {
      return UI_JETTY_STOP_TIMEOUT;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$UI_REVERSE_PROXY_URL$1(final String s) {
      String[] words = s.split("/");
      return !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])words), "proxy") && !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])words), "history");
   }

   private UI$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
