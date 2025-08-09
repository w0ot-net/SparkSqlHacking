package org.apache.spark.deploy.k8s;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.ConfigBuilder;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.internal.config.TypedConfigBuilder;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Predef;
import scala.StringContext;
import scala.Predef.;
import scala.collection.StringOps;
import scala.collection.immutable.Set;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

public final class Config$ implements Logging {
   public static final Config$ MODULE$ = new Config$();
   private static final ConfigEntry DECOMMISSION_SCRIPT;
   private static final OptionalConfigEntry KUBERNETES_CONTEXT;
   private static final ConfigEntry KUBERNETES_DRIVER_MASTER_URL;
   private static final ConfigEntry KUBERNETES_DRIVER_SERVICE_DELETE_ON_TERMINATION;
   private static final ConfigEntry KUBERNETES_USE_LEGACY_PVC_ACCESS_MODE;
   private static final ConfigEntry KUBERNETES_DRIVER_SERVICE_IP_FAMILY_POLICY;
   private static final ConfigEntry KUBERNETES_DRIVER_SERVICE_IP_FAMILIES;
   private static final ConfigEntry KUBERNETES_DRIVER_OWN_PVC;
   private static final ConfigEntry KUBERNETES_DRIVER_REUSE_PVC;
   private static final ConfigEntry KUBERNETES_DRIVER_WAIT_TO_REUSE_PVC;
   private static final ConfigEntry KUBERNETES_NAMESPACE;
   private static final OptionalConfigEntry CONTAINER_IMAGE;
   private static final ConfigEntry DRIVER_CONTAINER_IMAGE;
   private static final ConfigEntry EXECUTOR_CONTAINER_IMAGE;
   private static final ConfigEntry CONTAINER_IMAGE_PULL_POLICY;
   private static final ConfigEntry IMAGE_PULL_SECRETS;
   private static final ConfigEntry CONFIG_MAP_MAXSIZE;
   private static final ConfigEntry EXECUTOR_ROLL_INTERVAL;
   private static final ConfigEntry EXECUTOR_ROLL_POLICY;
   private static final ConfigEntry MINIMUM_TASKS_PER_EXECUTOR_BEFORE_ROLLING;
   private static final String KUBERNETES_AUTH_DRIVER_CONF_PREFIX;
   private static final String KUBERNETES_AUTH_EXECUTOR_CONF_PREFIX;
   private static final String KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX;
   private static final String KUBERNETES_AUTH_CLIENT_MODE_PREFIX;
   private static final String OAUTH_TOKEN_CONF_SUFFIX;
   private static final String OAUTH_TOKEN_FILE_CONF_SUFFIX;
   private static final String CLIENT_KEY_FILE_CONF_SUFFIX;
   private static final String CLIENT_CERT_FILE_CONF_SUFFIX;
   private static final String CA_CERT_FILE_CONF_SUFFIX;
   private static final ConfigEntry SUBMISSION_CLIENT_REQUEST_TIMEOUT;
   private static final ConfigEntry SUBMISSION_CLIENT_CONNECTION_TIMEOUT;
   private static final ConfigEntry DRIVER_CLIENT_REQUEST_TIMEOUT;
   private static final ConfigEntry DRIVER_CLIENT_CONNECTION_TIMEOUT;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_SERVICE_ACCOUNT_NAME;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_SERVICE_ACCOUNT_NAME;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_LIMIT_CORES;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_REQUEST_CORES;
   private static final ConfigEntry KUBERNETES_DRIVER_SUBMIT_CHECK;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_LIMIT_CORES;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_SCHEDULER_NAME;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_SCHEDULER_NAME;
   private static final OptionalConfigEntry KUBERNETES_SCHEDULER_NAME;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_REQUEST_CORES;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_POD_NAME;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_POD_NAME_PREFIX;
   private static final String dns1123LabelFmt;
   private static final Pattern podConfValidator;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_POD_NAME_PREFIX;
   private static final ConfigEntry KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP;
   private static final ConfigEntry KUBERNETES_DRIVER_POD_FEATURE_STEPS;
   private static final ConfigEntry KUBERNETES_EXECUTOR_POD_FEATURE_STEPS;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_DECOMMISSION_LABEL;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_DECOMMISSION_LABEL_VALUE;
   private static final ConfigEntry KUBERNETES_ALLOCATION_PODS_ALLOCATOR;
   private static final ConfigEntry KUBERNETES_ALLOCATION_BATCH_SIZE;
   private static final ConfigEntry KUBERNETES_ALLOCATION_BATCH_DELAY;
   private static final ConfigEntry KUBERNETES_ALLOCATION_DRIVER_READINESS_TIMEOUT;
   private static final ConfigEntry KUBERNETES_ALLOCATION_EXECUTOR_TIMEOUT;
   private static final ConfigEntry WAIT_FOR_APP_COMPLETION;
   private static final ConfigEntry REPORT_INTERVAL;
   private static final ConfigEntry KUBERNETES_EXECUTOR_ENABLE_API_POLLING;
   private static final ConfigEntry KUBERNETES_EXECUTOR_ENABLE_API_WATCHER;
   private static final ConfigEntry KUBERNETES_EXECUTOR_API_POLLING_INTERVAL;
   private static final ConfigEntry KUBERNETES_EXECUTOR_API_POLLING_WITH_RESOURCE_VERSION;
   private static final ConfigEntry KUBERNETES_EXECUTOR_EVENT_PROCESSING_INTERVAL;
   private static final ConfigEntry MEMORY_OVERHEAD_FACTOR;
   private static final OptionalConfigEntry PYSPARK_MAJOR_PYTHON_VERSION;
   private static final OptionalConfigEntry KUBERNETES_KERBEROS_KRB5_FILE;
   private static final OptionalConfigEntry KUBERNETES_KERBEROS_KRB5_CONFIG_MAP;
   private static final OptionalConfigEntry KUBERNETES_HADOOP_CONF_CONFIG_MAP;
   private static final OptionalConfigEntry KUBERNETES_KERBEROS_DT_SECRET_NAME;
   private static final OptionalConfigEntry KUBERNETES_KERBEROS_DT_SECRET_ITEM_KEY;
   private static final OptionalConfigEntry APP_RESOURCE_TYPE;
   private static final ConfigEntry KUBERNETES_LOCAL_DIRS_TMPFS;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_PODTEMPLATE_FILE;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_PODTEMPLATE_FILE;
   private static final OptionalConfigEntry KUBERNETES_DRIVER_PODTEMPLATE_CONTAINER_NAME;
   private static final OptionalConfigEntry KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME;
   private static final String KUBERNETES_AUTH_SUBMISSION_CONF_PREFIX;
   private static final ConfigEntry KUBERNETES_TRUST_CERTIFICATES;
   private static final String KUBERNETES_NODE_SELECTOR_PREFIX;
   private static final String KUBERNETES_DRIVER_NODE_SELECTOR_PREFIX;
   private static final String KUBERNETES_EXECUTOR_NODE_SELECTOR_PREFIX;
   private static final ConfigEntry KUBERNETES_DELETE_EXECUTORS;
   private static final ConfigEntry KUBERNETES_DYN_ALLOC_KILL_GRACE_PERIOD;
   private static final OptionalConfigEntry KUBERNETES_SUBMIT_GRACE_PERIOD;
   private static final OptionalConfigEntry KUBERNETES_FILE_UPLOAD_PATH;
   private static final ConfigEntry KUBERNETES_EXECUTOR_CHECK_ALL_CONTAINERS;
   private static final ConfigEntry KUBERNETES_EXECUTOR_MISSING_POD_DETECT_DELTA;
   private static final ConfigEntry KUBERNETES_MAX_PENDING_PODS;
   private static final ConfigEntry KUBERNETES_EXECUTOR_SNAPSHOTS_SUBSCRIBERS_GRACE_PERIOD;
   private static final String KUBERNETES_DRIVER_LABEL_PREFIX;
   private static final String KUBERNETES_DRIVER_ANNOTATION_PREFIX;
   private static final String KUBERNETES_DRIVER_SERVICE_LABEL_PREFIX;
   private static final String KUBERNETES_DRIVER_SERVICE_ANNOTATION_PREFIX;
   private static final String KUBERNETES_DRIVER_SECRETS_PREFIX;
   private static final String KUBERNETES_DRIVER_SECRET_KEY_REF_PREFIX;
   private static final String KUBERNETES_DRIVER_VOLUMES_PREFIX;
   private static final String KUBERNETES_EXECUTOR_LABEL_PREFIX;
   private static final String KUBERNETES_EXECUTOR_ANNOTATION_PREFIX;
   private static final String KUBERNETES_EXECUTOR_SECRETS_PREFIX;
   private static final String KUBERNETES_EXECUTOR_SECRET_KEY_REF_PREFIX;
   private static final String KUBERNETES_EXECUTOR_VOLUMES_PREFIX;
   private static final String KUBERNETES_VOLUMES_HOSTPATH_TYPE;
   private static final String KUBERNETES_VOLUMES_PVC_TYPE;
   private static final String KUBERNETES_VOLUMES_EMPTYDIR_TYPE;
   private static final String KUBERNETES_VOLUMES_NFS_TYPE;
   private static final String KUBERNETES_VOLUMES_MOUNT_PATH_KEY;
   private static final String KUBERNETES_VOLUMES_MOUNT_SUBPATH_KEY;
   private static final String KUBERNETES_VOLUMES_MOUNT_SUBPATHEXPR_KEY;
   private static final String KUBERNETES_VOLUMES_MOUNT_READONLY_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_PATH_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_TYPE_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_CLAIM_NAME_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_CLAIM_STORAGE_CLASS_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_MEDIUM_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_SIZE_LIMIT_KEY;
   private static final String KUBERNETES_VOLUMES_OPTIONS_SERVER_KEY;
   private static final String KUBERNETES_VOLUMES_LABEL_KEY;
   private static final String KUBERNETES_VOLUMES_ANNOTATION_KEY;
   private static final String KUBERNETES_DRIVER_ENV_PREFIX;
   private static final int KUBERNETES_DNS_SUBDOMAIN_NAME_MAX_LENGTH;
   private static final int KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      DECOMMISSION_SCRIPT = (new ConfigBuilder("spark.kubernetes.decommission.script")).doc("The location of the script to use for graceful decommissioning").version("3.2.0").stringConf().createWithDefault("/opt/decom.sh");
      KUBERNETES_CONTEXT = (new ConfigBuilder("spark.kubernetes.context")).doc("The desired context from your K8S config file used to configure the K8S client for interacting with the cluster.  Useful if your config file has multiple clusters or user identities defined.  The client library used locates the config file via the KUBECONFIG environment variable or by defaulting to .kube/config under your home directory.  If not specified then your current context is used.  You can always override specific aspects of the config file provided configuration using other Spark on K8S configuration options.").version("3.0.0").stringConf().createOptional();
      KUBERNETES_DRIVER_MASTER_URL = (new ConfigBuilder("spark.kubernetes.driver.master")).doc("The internal Kubernetes master (API server) address to be used for driver to request executors or 'local[*]' for driver-only mode.").version("3.0.0").stringConf().createWithDefault(Constants$.MODULE$.KUBERNETES_MASTER_INTERNAL_URL());
      KUBERNETES_DRIVER_SERVICE_DELETE_ON_TERMINATION = (new ConfigBuilder("spark.kubernetes.driver.service.deleteOnTermination")).doc("If true, driver service will be deleted on Spark application termination. If false, it will be cleaned up when the driver pod is deleted.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KUBERNETES_USE_LEGACY_PVC_ACCESS_MODE = (new ConfigBuilder("spark.kubernetes.legacy.useReadWriteOnceAccessMode")).internal().doc("If true, use ReadWriteOnce instead of ReadWriteOncePod as persistence volume access mode.").version("3.4.3").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_DRIVER_SERVICE_IP_FAMILY_POLICY = (new ConfigBuilder("spark.kubernetes.driver.service.ipFamilyPolicy")).doc("K8s IP Family Policy for Driver Service").version("3.4.0").stringConf().checkValues((Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SingleStack", "PreferDualStack", "RequireDualStack"})))).createWithDefault("SingleStack");
      KUBERNETES_DRIVER_SERVICE_IP_FAMILIES = (new ConfigBuilder("spark.kubernetes.driver.service.ipFamilies")).doc("A list of IP families for K8s Driver Service").version("3.4.0").stringConf().checkValues((Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"IPv4", "IPv6", "IPv4,IPv6", "IPv6,IPv4"})))).createWithDefault("IPv4");
      KUBERNETES_DRIVER_OWN_PVC = (new ConfigBuilder("spark.kubernetes.driver.ownPersistentVolumeClaim")).doc("If true, driver pod becomes the owner of on-demand persistent volume claims instead of the executor pods").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KUBERNETES_DRIVER_REUSE_PVC = (new ConfigBuilder("spark.kubernetes.driver.reusePersistentVolumeClaim")).doc("If true, driver pod tries to reuse driver-owned on-demand persistent volume claims of the deleted executor pods if exists. This can be useful to reduce executor pod creation delay by skipping persistent volume creations. Note that a pod in `Terminating` pod status is not a deleted pod by definition and its resources including persistent volume claims are not reusable yet. Spark will create new persistent volume claims when there exists no reusable one. In other words, the total number of persistent volume claims can be larger than the number of running executors sometimes. This config requires " + MODULE$.KUBERNETES_DRIVER_OWN_PVC().key() + "=true.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      ConfigBuilder var10000 = new ConfigBuilder("spark.kubernetes.driver.waitToReusePersistentVolumeClaim");
      String var10001 = MODULE$.KUBERNETES_DRIVER_OWN_PVC().key();
      KUBERNETES_DRIVER_WAIT_TO_REUSE_PVC = var10000.doc("If true, driver pod counts the number of created on-demand persistent volume claims and wait if the number is greater than or equal to the total number of volumes which the Spark job is able to have. This config requires both " + var10001 + "=true and " + MODULE$.KUBERNETES_DRIVER_REUSE_PVC().key() + "=true.").version("3.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_NAMESPACE = (new ConfigBuilder("spark.kubernetes.namespace")).doc("The namespace that will be used for running the driver and executor pods.").version("2.3.0").stringConf().createWithDefault("default");
      CONTAINER_IMAGE = (new ConfigBuilder("spark.kubernetes.container.image")).doc("Container image to use for Spark containers. Individual container types (e.g. driver or executor) can also be configured to use different images if desired, by setting the container type-specific image name.").version("2.3.0").stringConf().createOptional();
      DRIVER_CONTAINER_IMAGE = (new ConfigBuilder("spark.kubernetes.driver.container.image")).doc("Container image to use for the driver.").version("2.3.0").fallbackConf(MODULE$.CONTAINER_IMAGE());
      EXECUTOR_CONTAINER_IMAGE = (new ConfigBuilder("spark.kubernetes.executor.container.image")).doc("Container image to use for the executors.").version("2.3.0").fallbackConf(MODULE$.CONTAINER_IMAGE());
      CONTAINER_IMAGE_PULL_POLICY = (new ConfigBuilder("spark.kubernetes.container.image.pullPolicy")).doc("Kubernetes image pull policy. Valid values are Always, Never, and IfNotPresent.").version("2.3.0").stringConf().checkValues((Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Always", "Never", "IfNotPresent"})))).createWithDefault("IfNotPresent");
      IMAGE_PULL_SECRETS = (new ConfigBuilder("spark.kubernetes.container.image.pullSecrets")).doc("Comma separated list of the Kubernetes secrets used to access private image registries.").version("2.4.0").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      CONFIG_MAP_MAXSIZE = (new ConfigBuilder("spark.kubernetes.configMap.maxSize")).doc("Max size limit for a config map. This is configurable as per https://etcd.io/docs/v3.4.0/dev-guide/limit/ on k8s server end.").version("3.1.0").longConf().checkValue((JFunction1.mcZJ.sp)(x$1) -> x$1 <= 1048576L, "Must have at most 1048576 bytes").createWithDefault(BoxesRunTime.boxToLong(1048576L));
      EXECUTOR_ROLL_INTERVAL = (new ConfigBuilder("spark.kubernetes.executor.rollInterval")).doc("Interval between executor roll operations. To disable, set 0 (default)").version("3.3.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(x$2) -> x$2 >= 0L, "Interval should be non-negative").createWithDefault(BoxesRunTime.boxToLong(0L));
      EXECUTOR_ROLL_POLICY = (new ConfigBuilder("spark.kubernetes.executor.rollPolicy")).doc("Executor roll policy: Valid values are ID, ADD_TIME, TOTAL_GC_TIME, TOTAL_DURATION, AVERAGE_DURATION, FAILED_TASKS, PEAK_JVM_ONHEAP_MEMORY, PEAK_JVM_OFFHEAP_MEMORY, OUTLIER (default), and OUTLIER_NO_FALLBACK. When executor roll happens, Spark uses this policy to choose an executor and decommission it. The built-in policies are based on executor summary.ID policy chooses an executor with the smallest executor ID. ADD_TIME policy chooses an executor with the smallest add-time. TOTAL_GC_TIME policy chooses an executor with the biggest total task GC time. TOTAL_DURATION policy chooses an executor with the biggest total task time. AVERAGE_DURATION policy chooses an executor with the biggest average task time. FAILED_TASKS policy chooses an executor with the most number of failed tasks. PEAK_JVM_ONHEAP_MEMORY policy chooses an executor with the biggest peak JVM on-heap memory. PEAK_JVM_OFFHEAP_MEMORY policy chooses an executor with the biggest peak JVM off-heap memory. TOTAL_SHUFFLE_WRITE policy chooses an executor with the biggest total shuffle write. DISK_USED policy chooses an executor with the biggest used disk size. OUTLIER policy chooses an executor with outstanding statistics which is bigger thanat least two standard deviation from the mean in average task time, total task time, total task GC time, and the number of failed tasks if exists. If there is no outlier it works like TOTAL_DURATION policy. OUTLIER_NO_FALLBACK policy picks an outlier using the OUTLIER policy above. If there is no outlier then no executor will be rolled.").version("3.3.0").stringConf().transform((x$3) -> x$3.toUpperCase(Locale.ROOT)).checkValues(Config.ExecutorRollPolicy$.MODULE$.values().map((x$4) -> x$4.toString(), scala.math.Ordering.String..MODULE$)).createWithDefault(Config.ExecutorRollPolicy$.MODULE$.OUTLIER().toString());
      MINIMUM_TASKS_PER_EXECUTOR_BEFORE_ROLLING = (new ConfigBuilder("spark.kubernetes.executor.minTasksPerExecutorBeforeRolling")).doc("The minimum number of tasks per executor before rolling. Spark will not roll executors whose total number of tasks is smaller than this configuration. The default value is zero.").version("3.3.0").intConf().checkValue((JFunction1.mcZI.sp)(x$5) -> x$5 >= 0, "The minimum number of tasks should be non-negative.").createWithDefault(BoxesRunTime.boxToInteger(0));
      KUBERNETES_AUTH_DRIVER_CONF_PREFIX = "spark.kubernetes.authenticate.driver";
      KUBERNETES_AUTH_EXECUTOR_CONF_PREFIX = "spark.kubernetes.authenticate.executor";
      KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX = "spark.kubernetes.authenticate.driver.mounted";
      KUBERNETES_AUTH_CLIENT_MODE_PREFIX = "spark.kubernetes.authenticate";
      OAUTH_TOKEN_CONF_SUFFIX = "oauthToken";
      OAUTH_TOKEN_FILE_CONF_SUFFIX = "oauthTokenFile";
      CLIENT_KEY_FILE_CONF_SUFFIX = "clientKeyFile";
      CLIENT_CERT_FILE_CONF_SUFFIX = "clientCertFile";
      CA_CERT_FILE_CONF_SUFFIX = "caCertFile";
      SUBMISSION_CLIENT_REQUEST_TIMEOUT = (new ConfigBuilder("spark.kubernetes.submission.requestTimeout")).doc("request timeout to be used in milliseconds for starting the driver").version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(10000));
      SUBMISSION_CLIENT_CONNECTION_TIMEOUT = (new ConfigBuilder("spark.kubernetes.submission.connectionTimeout")).doc("connection timeout to be used in milliseconds for starting the driver").version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(10000));
      DRIVER_CLIENT_REQUEST_TIMEOUT = (new ConfigBuilder("spark.kubernetes.driver.requestTimeout")).doc("request timeout to be used in milliseconds for driver to request executors").version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(10000));
      DRIVER_CLIENT_CONNECTION_TIMEOUT = (new ConfigBuilder("spark.kubernetes.driver.connectionTimeout")).doc("connection timeout to be used in milliseconds for driver to request executors").version("3.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(10000));
      KUBERNETES_DRIVER_SERVICE_ACCOUNT_NAME = (new ConfigBuilder(MODULE$.KUBERNETES_AUTH_DRIVER_CONF_PREFIX() + ".serviceAccountName")).doc("Service account that is used when running the driver pod. The driver pod uses this service account when requesting executor pods from the API server. If specific credentials are given for the driver pod to use, the driver will favor using those credentials instead.").version("2.3.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_SERVICE_ACCOUNT_NAME = (new ConfigBuilder(MODULE$.KUBERNETES_AUTH_EXECUTOR_CONF_PREFIX() + ".serviceAccountName")).doc("Service account that is used when running the executor pod.If this parameter is not setup, the fallback logic will use the driver's service account.").version("3.1.0").stringConf().createOptional();
      KUBERNETES_DRIVER_LIMIT_CORES = (new ConfigBuilder("spark.kubernetes.driver.limit.cores")).doc("Specify the hard cpu limit for the driver pod").version("2.3.0").stringConf().createOptional();
      KUBERNETES_DRIVER_REQUEST_CORES = (new ConfigBuilder("spark.kubernetes.driver.request.cores")).doc("Specify the cpu request for the driver pod").version("3.0.0").stringConf().createOptional();
      KUBERNETES_DRIVER_SUBMIT_CHECK = (new ConfigBuilder("spark.kubernetes.submitInDriver")).internal().version("2.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_EXECUTOR_LIMIT_CORES = (new ConfigBuilder("spark.kubernetes.executor.limit.cores")).doc("Specify the hard cpu limit for each executor pod").version("2.3.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_SCHEDULER_NAME = (new ConfigBuilder("spark.kubernetes.executor.scheduler.name")).doc("Specify the scheduler name for each executor pod").version("3.0.0").stringConf().createOptional();
      KUBERNETES_DRIVER_SCHEDULER_NAME = (new ConfigBuilder("spark.kubernetes.driver.scheduler.name")).doc("Specify the scheduler name for driver pod").version("3.3.0").stringConf().createOptional();
      var10000 = new ConfigBuilder("spark.kubernetes.scheduler.name");
      var10001 = MODULE$.KUBERNETES_DRIVER_SCHEDULER_NAME().key();
      KUBERNETES_SCHEDULER_NAME = var10000.doc("Specify the scheduler name for driver and executor pods. If `" + var10001 + "` or `" + MODULE$.KUBERNETES_EXECUTOR_SCHEDULER_NAME().key() + "` is set, will override this.").version("3.3.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_REQUEST_CORES = (new ConfigBuilder("spark.kubernetes.executor.request.cores")).doc("Specify the cpu request for each executor pod").version("2.4.0").stringConf().createOptional();
      KUBERNETES_DRIVER_POD_NAME = (new ConfigBuilder("spark.kubernetes.driver.pod.name")).doc("Name of the driver pod.").version("2.3.0").stringConf().createOptional();
      KUBERNETES_DRIVER_POD_NAME_PREFIX = (new ConfigBuilder("spark.kubernetes.driver.resourceNamePrefix")).internal().version("3.0.0").stringConf().createOptional();
      dns1123LabelFmt = "[a-z0-9]([-a-z0-9]*[a-z0-9])?";
      StringOps var1 = scala.collection.StringOps..MODULE$;
      Predef var5 = .MODULE$;
      String var10002 = MODULE$.dns1123LabelFmt();
      podConfValidator = var1.r$extension(var5.augmentString("^" + var10002 + "(\\." + MODULE$.dns1123LabelFmt() + ")*$")).pattern();
      KUBERNETES_EXECUTOR_POD_NAME_PREFIX = (new ConfigBuilder("spark.kubernetes.executor.podNamePrefix")).doc("Prefix to use in front of the executor pod names. It must conform the rules defined by the Kubernetes <a href=\"https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#dns-subdomain-names\">DNS Subdomain Names</a>. The prefix will be used to generate executor pod names in the form of <code>$podNamePrefix-exec-$id</code>, where the `id` is a positive int value, so the length of the `podNamePrefix` needs to be <= 237(= 253 - 10 - 6).").version("2.3.0").stringConf().checkValue((prefix) -> BoxesRunTime.boxToBoolean($anonfun$KUBERNETES_EXECUTOR_POD_NAME_PREFIX$1(prefix)), "must conform https://kubernetes.io/docs/concepts/overview/working-with-objects/names/#dns-subdomain-names and the value length <= 237").createOptional();
      KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP = (new ConfigBuilder("spark.kubernetes.executor.disableConfigMap")).doc("If true, disable ConfigMap creation for executors.").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_DRIVER_POD_FEATURE_STEPS = (new ConfigBuilder("spark.kubernetes.driver.pod.featureSteps")).doc("Class names of an extra driver pod feature step implementing KubernetesFeatureConfigStep. This is a developer API. Comma separated. Runs after all of Spark internal feature steps. Since 3.3.0, your driver feature step can implement `KubernetesDriverCustomFeatureConfigStep` where the driver config is also available.").version("3.2.0").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      KUBERNETES_EXECUTOR_POD_FEATURE_STEPS = (new ConfigBuilder("spark.kubernetes.executor.pod.featureSteps")).doc("Class name of an extra executor pod feature step implementing KubernetesFeatureConfigStep. This is a developer API. Comma separated. Runs after all of Spark internal feature steps. Since 3.3.0, your executor feature step can implement `KubernetesExecutorCustomFeatureConfigStep` where the executor config is also available.").version("3.2.0").stringConf().toSequence().createWithDefault(scala.collection.immutable.Nil..MODULE$);
      KUBERNETES_EXECUTOR_DECOMMISSION_LABEL = (new ConfigBuilder("spark.kubernetes.executor.decommissionLabel")).doc("Label to apply to a pod which is being decommissioned. Designed for use with pod disruption budgets and similar mechanism such as pod-deletion-cost.").version("3.3.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_DECOMMISSION_LABEL_VALUE = (new ConfigBuilder("spark.kubernetes.executor.decommissionLabelValue")).doc("Label value to apply to a pod which is being decommissioned. Designed for use with pod disruption budgets and similar mechanism such as pod-deletion-cost.").version("3.3.0").stringConf().createOptional();
      KUBERNETES_ALLOCATION_PODS_ALLOCATOR = (new ConfigBuilder("spark.kubernetes.allocation.pods.allocator")).doc("Allocator to use for pods. Possible values are direct (the default) and statefulset , or a full class name of a class implementing AbstractPodsAllocator. Future version may add Job or replicaset. This is a developer API and may change or be removed at anytime.").version("3.3.0").stringConf().createWithDefault("direct");
      KUBERNETES_ALLOCATION_BATCH_SIZE = (new ConfigBuilder("spark.kubernetes.allocation.batch.size")).doc("Number of pods to launch at once in each round of executor allocation.").version("2.3.0").intConf().checkValue((JFunction1.mcZI.sp)(value) -> value > 0, "Allocation batch size should be a positive integer").createWithDefault(BoxesRunTime.boxToInteger(10));
      KUBERNETES_ALLOCATION_BATCH_DELAY = (new ConfigBuilder("spark.kubernetes.allocation.batch.delay")).doc("Time to wait between each round of executor allocation.").version("2.3.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(value) -> value > 100L, "Allocation batch delay must be greater than 0.1s.").createWithDefaultString("1s");
      KUBERNETES_ALLOCATION_DRIVER_READINESS_TIMEOUT = (new ConfigBuilder("spark.kubernetes.allocation.driver.readinessTimeout")).doc("Time to wait for driver pod to get ready before creating executor pods. This wait only happens on application start. If timeout happens, executor pods will still be created.").version("3.1.3").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(value) -> value > 0L, "Allocation driver readiness timeout must be a positive time value.").createWithDefaultString("1s");
      KUBERNETES_ALLOCATION_EXECUTOR_TIMEOUT = (new ConfigBuilder("spark.kubernetes.allocation.executor.timeout")).doc("Time to wait before a newly created executor POD request, which does not reached the POD pending state yet, considered timedout and will be deleted.").version("3.1.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(value) -> value > 0L, "Allocation executor timeout must be a positive time value.").createWithDefaultString("600s");
      WAIT_FOR_APP_COMPLETION = (new ConfigBuilder("spark.kubernetes.submission.waitAppCompletion")).doc("In cluster mode, whether to wait for the application to finish before exiting the launcher process.").version("2.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      REPORT_INTERVAL = (new ConfigBuilder("spark.kubernetes.report.interval")).doc("Interval between reports of the current app status in cluster mode.").version("2.3.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(interval) -> interval > 0L, "Logging interval must be a positive time value.").createWithDefaultString("1s");
      KUBERNETES_EXECUTOR_ENABLE_API_POLLING = (new ConfigBuilder("spark.kubernetes.executor.enableApiPolling")).doc("If Spark should poll Kubernetes for executor pod status. You should leave this enabled unless you're encountering issues with your etcd.").version("3.4.0").internal().booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KUBERNETES_EXECUTOR_ENABLE_API_WATCHER = (new ConfigBuilder("spark.kubernetes.executor.enableApiWatcher")).doc("If Spark should create watchers for executor pod status. You should leave this enabled unless you're encountering issues with your etcd.").version("3.4.0").internal().booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KUBERNETES_EXECUTOR_API_POLLING_INTERVAL = (new ConfigBuilder("spark.kubernetes.executor.apiPollingInterval")).doc("Interval between polls against the Kubernetes API server to inspect the state of executors.").version("2.4.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(interval) -> interval > 0L, "API server polling interval must be a positive time value.").createWithDefaultString("30s");
      KUBERNETES_EXECUTOR_API_POLLING_WITH_RESOURCE_VERSION = (new ConfigBuilder("spark.kubernetes.executor.enablePollingWithResourceVersion")).doc("If true, `resourceVersion` is set with `0` during invoking pod listing APIs in order to allow API Server-side caching. This should be used carefully.").version("3.3.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_EXECUTOR_EVENT_PROCESSING_INTERVAL = (new ConfigBuilder("spark.kubernetes.executor.eventProcessingInterval")).doc("Interval between successive inspection of executor events sent from the Kubernetes API.").version("2.4.0").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(interval) -> interval > 0L, "Event processing interval must be a positive time value.").createWithDefaultString("1s");
      MEMORY_OVERHEAD_FACTOR = (new ConfigBuilder("spark.kubernetes.memoryOverheadFactor")).doc("This sets the Memory Overhead Factor that will allocate memory to non-JVM jobs which in the case of JVM tasks will default to 0.10 and 0.40 for non-JVM jobs").version("2.4.0").doubleConf().checkValue((JFunction1.mcZD.sp)(mem_overhead) -> mem_overhead >= (double)0, "Ensure that memory overhead is non-negative").createWithDefault(BoxesRunTime.boxToDouble(0.1));
      ConfigBuilder var2 = new ConfigBuilder("spark.kubernetes.pyspark.pythonVersion");
      String var6 = org.apache.spark.internal.config.package..MODULE$.PYSPARK_PYTHON().key();
      TypedConfigBuilder var3 = var2.doc("(Deprecated since Spark 3.1, please set '" + var6 + "' and '" + org.apache.spark.internal.config.package..MODULE$.PYSPARK_DRIVER_PYTHON().key() + "' configurations or " + Constants$.MODULE$.ENV_PYSPARK_PYTHON() + " and " + Constants$.MODULE$.ENV_PYSPARK_DRIVER_PYTHON() + " environment variables instead.)").version("2.4.0").stringConf();
      Function1 var7 = (x$6) -> BoxesRunTime.boxToBoolean($anonfun$PYSPARK_MAJOR_PYTHON_VERSION$1(x$6));
      var10002 = org.apache.spark.internal.config.package..MODULE$.PYSPARK_PYTHON().key();
      PYSPARK_MAJOR_PYTHON_VERSION = var3.checkValue(var7, "Python 2 was dropped from Spark 3.1, and only 3 is allowed in this configuration. Note that this configuration was deprecated in Spark 3.1. Please set '" + var10002 + "' and '" + org.apache.spark.internal.config.package..MODULE$.PYSPARK_DRIVER_PYTHON().key() + "' configurations or " + Constants$.MODULE$.ENV_PYSPARK_PYTHON() + " and " + Constants$.MODULE$.ENV_PYSPARK_DRIVER_PYTHON() + " environment variables instead.").createOptional();
      KUBERNETES_KERBEROS_KRB5_FILE = (new ConfigBuilder("spark.kubernetes.kerberos.krb5.path")).doc("Specify the local location of the krb5.conf file to be mounted on the driver and executors for Kerberos. Note: The KDC defined needs to be visible from inside the containers ").version("3.0.0").stringConf().createOptional();
      KUBERNETES_KERBEROS_KRB5_CONFIG_MAP = (new ConfigBuilder("spark.kubernetes.kerberos.krb5.configMapName")).doc("Specify the name of the ConfigMap, containing the krb5.conf file, to be mounted on the driver and executors for Kerberos. Note: The KDC definedneeds to be visible from inside the containers ").version("3.0.0").stringConf().createOptional();
      KUBERNETES_HADOOP_CONF_CONFIG_MAP = (new ConfigBuilder("spark.kubernetes.hadoop.configMapName")).doc("Specify the name of the ConfigMap, containing the HADOOP_CONF_DIR files, to be mounted on the driver and executors for custom Hadoop configuration.").version("3.0.0").stringConf().createOptional();
      KUBERNETES_KERBEROS_DT_SECRET_NAME = (new ConfigBuilder("spark.kubernetes.kerberos.tokenSecret.name")).doc("Specify the name of the secret where your existing delegation tokens are stored. This removes the need for the job user to provide any keytab for launching a job").version("3.0.0").stringConf().createOptional();
      KUBERNETES_KERBEROS_DT_SECRET_ITEM_KEY = (new ConfigBuilder("spark.kubernetes.kerberos.tokenSecret.itemKey")).doc("Specify the item key of the data where your existing delegation tokens are stored. This removes the need for the job user to provide any keytab for launching a job").version("3.0.0").stringConf().createOptional();
      APP_RESOURCE_TYPE = (new ConfigBuilder("spark.kubernetes.resource.type")).internal().doc("This sets the resource type internally").version("2.4.1").stringConf().checkValues((Set).MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{Constants$.MODULE$.APP_RESOURCE_TYPE_JAVA(), Constants$.MODULE$.APP_RESOURCE_TYPE_PYTHON(), Constants$.MODULE$.APP_RESOURCE_TYPE_R()})))).createOptional();
      KUBERNETES_LOCAL_DIRS_TMPFS = (new ConfigBuilder("spark.kubernetes.local.dirs.tmpfs")).doc("If set to true then emptyDir volumes created to back SPARK_LOCAL_DIRS will have their medium set to Memory so that they will be created as tmpfs (i.e. RAM) backed volumes. This may improve performance but scratch space usage will count towards your pods memory limit so you may wish to request more memory.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_DRIVER_PODTEMPLATE_FILE = (new ConfigBuilder("spark.kubernetes.driver.podTemplateFile")).doc("File containing a template pod spec for the driver").version("3.0.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_PODTEMPLATE_FILE = (new ConfigBuilder("spark.kubernetes.executor.podTemplateFile")).doc("File containing a template pod spec for executors").version("3.0.0").stringConf().createOptional();
      KUBERNETES_DRIVER_PODTEMPLATE_CONTAINER_NAME = (new ConfigBuilder("spark.kubernetes.driver.podTemplateContainerName")).doc("container name to be used as a basis for the driver in the given pod template").version("3.0.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME = (new ConfigBuilder("spark.kubernetes.executor.podTemplateContainerName")).doc("container name to be used as a basis for executors in the given pod template").version("3.0.0").stringConf().createOptional();
      KUBERNETES_AUTH_SUBMISSION_CONF_PREFIX = "spark.kubernetes.authenticate.submission";
      KUBERNETES_TRUST_CERTIFICATES = (new ConfigBuilder("spark.kubernetes.trust.certificates")).doc("If set to true then client can submit to kubernetes cluster only with token").version("3.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
      KUBERNETES_NODE_SELECTOR_PREFIX = "spark.kubernetes.node.selector.";
      KUBERNETES_DRIVER_NODE_SELECTOR_PREFIX = "spark.kubernetes.driver.node.selector.";
      KUBERNETES_EXECUTOR_NODE_SELECTOR_PREFIX = "spark.kubernetes.executor.node.selector.";
      KUBERNETES_DELETE_EXECUTORS = (new ConfigBuilder("spark.kubernetes.executor.deleteOnTermination")).doc("If set to false then executor pods will not be deleted in case of failure or normal termination.").version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KUBERNETES_DYN_ALLOC_KILL_GRACE_PERIOD = (new ConfigBuilder("spark.kubernetes.dynamicAllocation.deleteGracePeriod")).doc("How long to wait for executors to shut down gracefully before a forceful kill.").version("3.0.0").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString("5s");
      KUBERNETES_SUBMIT_GRACE_PERIOD = (new ConfigBuilder("spark.kubernetes.appKillPodDeletionGracePeriod")).doc("Time to wait for graceful deletion of Spark pods when spark-submit is used for killing an application.").version("3.0.0").timeConf(TimeUnit.SECONDS).createOptional();
      KUBERNETES_FILE_UPLOAD_PATH = (new ConfigBuilder("spark.kubernetes.file.upload.path")).doc("Hadoop compatible file system path where files from the local file system will be uploaded to in cluster mode.").version("3.0.0").stringConf().createOptional();
      KUBERNETES_EXECUTOR_CHECK_ALL_CONTAINERS = (new ConfigBuilder("spark.kubernetes.executor.checkAllContainers")).doc("If set to true, all containers in the executor pod will be checked when reportingexecutor status.").version("3.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KUBERNETES_EXECUTOR_MISSING_POD_DETECT_DELTA = (new ConfigBuilder("spark.kubernetes.executor.missingPodDetectDelta")).doc("When a registered executor's POD is missing from the Kubernetes API server's polled list of PODs then this delta time is taken as the accepted time difference between the registration time and the time of the polling. After this time the POD is considered missing from the cluster and the executor will be removed.").version("3.1.1").timeConf(TimeUnit.MILLISECONDS).checkValue((JFunction1.mcZJ.sp)(delay) -> delay > 0L, "delay must be a positive time value").createWithDefaultString("30s");
      KUBERNETES_MAX_PENDING_PODS = (new ConfigBuilder("spark.kubernetes.allocation.maxPendingPods")).doc("Maximum number of pending PODs allowed during executor allocation for this application. Those newly requested executors which are unknown by Kubernetes yet are also counted into this limit as they will change into pending PODs by time. This limit is independent from the resource profiles as it limits the sum of all allocation for all the used resource profiles.").version("3.2.0").intConf().checkValue((JFunction1.mcZI.sp)(value) -> value > 0, "Maximum number of pending pods should be a positive integer").createWithDefault(BoxesRunTime.boxToInteger(Integer.MAX_VALUE));
      KUBERNETES_EXECUTOR_SNAPSHOTS_SUBSCRIBERS_GRACE_PERIOD = (new ConfigBuilder("spark.kubernetes.executorSnapshotsSubscribersShutdownGracePeriod")).doc("Time to wait for graceful shutdown kubernetes-executor-snapshots-subscribers thread pool. Since it may be called by ShutdownHookManager, where timeout is controlled by hadoop configuration `hadoop.service.shutdown.timeout` (default is 30s). As the whole Spark shutdown procedure shares the above timeout, this value should be short than that to prevent blocking the following shutdown procedures.").version("3.4.0").timeConf(TimeUnit.SECONDS).checkValue((JFunction1.mcZJ.sp)(value) -> value > 0L, "Gracefully shutdown period must be a positive time value").createWithDefaultString("20s");
      KUBERNETES_DRIVER_LABEL_PREFIX = "spark.kubernetes.driver.label.";
      KUBERNETES_DRIVER_ANNOTATION_PREFIX = "spark.kubernetes.driver.annotation.";
      KUBERNETES_DRIVER_SERVICE_LABEL_PREFIX = "spark.kubernetes.driver.service.label.";
      KUBERNETES_DRIVER_SERVICE_ANNOTATION_PREFIX = "spark.kubernetes.driver.service.annotation.";
      KUBERNETES_DRIVER_SECRETS_PREFIX = "spark.kubernetes.driver.secrets.";
      KUBERNETES_DRIVER_SECRET_KEY_REF_PREFIX = "spark.kubernetes.driver.secretKeyRef.";
      KUBERNETES_DRIVER_VOLUMES_PREFIX = "spark.kubernetes.driver.volumes.";
      KUBERNETES_EXECUTOR_LABEL_PREFIX = "spark.kubernetes.executor.label.";
      KUBERNETES_EXECUTOR_ANNOTATION_PREFIX = "spark.kubernetes.executor.annotation.";
      KUBERNETES_EXECUTOR_SECRETS_PREFIX = "spark.kubernetes.executor.secrets.";
      KUBERNETES_EXECUTOR_SECRET_KEY_REF_PREFIX = "spark.kubernetes.executor.secretKeyRef.";
      KUBERNETES_EXECUTOR_VOLUMES_PREFIX = "spark.kubernetes.executor.volumes.";
      KUBERNETES_VOLUMES_HOSTPATH_TYPE = "hostPath";
      KUBERNETES_VOLUMES_PVC_TYPE = "persistentVolumeClaim";
      KUBERNETES_VOLUMES_EMPTYDIR_TYPE = "emptyDir";
      KUBERNETES_VOLUMES_NFS_TYPE = "nfs";
      KUBERNETES_VOLUMES_MOUNT_PATH_KEY = "mount.path";
      KUBERNETES_VOLUMES_MOUNT_SUBPATH_KEY = "mount.subPath";
      KUBERNETES_VOLUMES_MOUNT_SUBPATHEXPR_KEY = "mount.subPathExpr";
      KUBERNETES_VOLUMES_MOUNT_READONLY_KEY = "mount.readOnly";
      KUBERNETES_VOLUMES_OPTIONS_PATH_KEY = "options.path";
      KUBERNETES_VOLUMES_OPTIONS_TYPE_KEY = "options.type";
      KUBERNETES_VOLUMES_OPTIONS_CLAIM_NAME_KEY = "options.claimName";
      KUBERNETES_VOLUMES_OPTIONS_CLAIM_STORAGE_CLASS_KEY = "options.storageClass";
      KUBERNETES_VOLUMES_OPTIONS_MEDIUM_KEY = "options.medium";
      KUBERNETES_VOLUMES_OPTIONS_SIZE_LIMIT_KEY = "options.sizeLimit";
      KUBERNETES_VOLUMES_OPTIONS_SERVER_KEY = "options.server";
      KUBERNETES_VOLUMES_LABEL_KEY = "label.";
      KUBERNETES_VOLUMES_ANNOTATION_KEY = "annotation.";
      KUBERNETES_DRIVER_ENV_PREFIX = "spark.kubernetes.driverEnv.";
      KUBERNETES_DNS_SUBDOMAIN_NAME_MAX_LENGTH = 253;
      KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH = 63;
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

   public ConfigEntry DECOMMISSION_SCRIPT() {
      return DECOMMISSION_SCRIPT;
   }

   public OptionalConfigEntry KUBERNETES_CONTEXT() {
      return KUBERNETES_CONTEXT;
   }

   public ConfigEntry KUBERNETES_DRIVER_MASTER_URL() {
      return KUBERNETES_DRIVER_MASTER_URL;
   }

   public ConfigEntry KUBERNETES_DRIVER_SERVICE_DELETE_ON_TERMINATION() {
      return KUBERNETES_DRIVER_SERVICE_DELETE_ON_TERMINATION;
   }

   public ConfigEntry KUBERNETES_USE_LEGACY_PVC_ACCESS_MODE() {
      return KUBERNETES_USE_LEGACY_PVC_ACCESS_MODE;
   }

   public ConfigEntry KUBERNETES_DRIVER_SERVICE_IP_FAMILY_POLICY() {
      return KUBERNETES_DRIVER_SERVICE_IP_FAMILY_POLICY;
   }

   public ConfigEntry KUBERNETES_DRIVER_SERVICE_IP_FAMILIES() {
      return KUBERNETES_DRIVER_SERVICE_IP_FAMILIES;
   }

   public ConfigEntry KUBERNETES_DRIVER_OWN_PVC() {
      return KUBERNETES_DRIVER_OWN_PVC;
   }

   public ConfigEntry KUBERNETES_DRIVER_REUSE_PVC() {
      return KUBERNETES_DRIVER_REUSE_PVC;
   }

   public ConfigEntry KUBERNETES_DRIVER_WAIT_TO_REUSE_PVC() {
      return KUBERNETES_DRIVER_WAIT_TO_REUSE_PVC;
   }

   public ConfigEntry KUBERNETES_NAMESPACE() {
      return KUBERNETES_NAMESPACE;
   }

   public OptionalConfigEntry CONTAINER_IMAGE() {
      return CONTAINER_IMAGE;
   }

   public ConfigEntry DRIVER_CONTAINER_IMAGE() {
      return DRIVER_CONTAINER_IMAGE;
   }

   public ConfigEntry EXECUTOR_CONTAINER_IMAGE() {
      return EXECUTOR_CONTAINER_IMAGE;
   }

   public ConfigEntry CONTAINER_IMAGE_PULL_POLICY() {
      return CONTAINER_IMAGE_PULL_POLICY;
   }

   public ConfigEntry IMAGE_PULL_SECRETS() {
      return IMAGE_PULL_SECRETS;
   }

   public ConfigEntry CONFIG_MAP_MAXSIZE() {
      return CONFIG_MAP_MAXSIZE;
   }

   public ConfigEntry EXECUTOR_ROLL_INTERVAL() {
      return EXECUTOR_ROLL_INTERVAL;
   }

   public ConfigEntry EXECUTOR_ROLL_POLICY() {
      return EXECUTOR_ROLL_POLICY;
   }

   public ConfigEntry MINIMUM_TASKS_PER_EXECUTOR_BEFORE_ROLLING() {
      return MINIMUM_TASKS_PER_EXECUTOR_BEFORE_ROLLING;
   }

   public String KUBERNETES_AUTH_DRIVER_CONF_PREFIX() {
      return KUBERNETES_AUTH_DRIVER_CONF_PREFIX;
   }

   public String KUBERNETES_AUTH_EXECUTOR_CONF_PREFIX() {
      return KUBERNETES_AUTH_EXECUTOR_CONF_PREFIX;
   }

   public String KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX() {
      return KUBERNETES_AUTH_DRIVER_MOUNTED_CONF_PREFIX;
   }

   public String KUBERNETES_AUTH_CLIENT_MODE_PREFIX() {
      return KUBERNETES_AUTH_CLIENT_MODE_PREFIX;
   }

   public String OAUTH_TOKEN_CONF_SUFFIX() {
      return OAUTH_TOKEN_CONF_SUFFIX;
   }

   public String OAUTH_TOKEN_FILE_CONF_SUFFIX() {
      return OAUTH_TOKEN_FILE_CONF_SUFFIX;
   }

   public String CLIENT_KEY_FILE_CONF_SUFFIX() {
      return CLIENT_KEY_FILE_CONF_SUFFIX;
   }

   public String CLIENT_CERT_FILE_CONF_SUFFIX() {
      return CLIENT_CERT_FILE_CONF_SUFFIX;
   }

   public String CA_CERT_FILE_CONF_SUFFIX() {
      return CA_CERT_FILE_CONF_SUFFIX;
   }

   public ConfigEntry SUBMISSION_CLIENT_REQUEST_TIMEOUT() {
      return SUBMISSION_CLIENT_REQUEST_TIMEOUT;
   }

   public ConfigEntry SUBMISSION_CLIENT_CONNECTION_TIMEOUT() {
      return SUBMISSION_CLIENT_CONNECTION_TIMEOUT;
   }

   public ConfigEntry DRIVER_CLIENT_REQUEST_TIMEOUT() {
      return DRIVER_CLIENT_REQUEST_TIMEOUT;
   }

   public ConfigEntry DRIVER_CLIENT_CONNECTION_TIMEOUT() {
      return DRIVER_CLIENT_CONNECTION_TIMEOUT;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_SERVICE_ACCOUNT_NAME() {
      return KUBERNETES_DRIVER_SERVICE_ACCOUNT_NAME;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_SERVICE_ACCOUNT_NAME() {
      return KUBERNETES_EXECUTOR_SERVICE_ACCOUNT_NAME;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_LIMIT_CORES() {
      return KUBERNETES_DRIVER_LIMIT_CORES;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_REQUEST_CORES() {
      return KUBERNETES_DRIVER_REQUEST_CORES;
   }

   public ConfigEntry KUBERNETES_DRIVER_SUBMIT_CHECK() {
      return KUBERNETES_DRIVER_SUBMIT_CHECK;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_LIMIT_CORES() {
      return KUBERNETES_EXECUTOR_LIMIT_CORES;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_SCHEDULER_NAME() {
      return KUBERNETES_EXECUTOR_SCHEDULER_NAME;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_SCHEDULER_NAME() {
      return KUBERNETES_DRIVER_SCHEDULER_NAME;
   }

   public OptionalConfigEntry KUBERNETES_SCHEDULER_NAME() {
      return KUBERNETES_SCHEDULER_NAME;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_REQUEST_CORES() {
      return KUBERNETES_EXECUTOR_REQUEST_CORES;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_POD_NAME() {
      return KUBERNETES_DRIVER_POD_NAME;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_POD_NAME_PREFIX() {
      return KUBERNETES_DRIVER_POD_NAME_PREFIX;
   }

   private String dns1123LabelFmt() {
      return dns1123LabelFmt;
   }

   private Pattern podConfValidator() {
      return podConfValidator;
   }

   private boolean isValidExecutorPodNamePrefix(final String prefix) {
      int reservedLen = Integer.toString(Integer.MAX_VALUE).length() + 6;
      boolean validLength = prefix.length() + reservedLen <= this.KUBERNETES_DNS_SUBDOMAIN_NAME_MAX_LENGTH();
      return validLength && this.podConfValidator().matcher(prefix).matches();
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_POD_NAME_PREFIX() {
      return KUBERNETES_EXECUTOR_POD_NAME_PREFIX;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP() {
      return KUBERNETES_EXECUTOR_DISABLE_CONFIGMAP;
   }

   public ConfigEntry KUBERNETES_DRIVER_POD_FEATURE_STEPS() {
      return KUBERNETES_DRIVER_POD_FEATURE_STEPS;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_POD_FEATURE_STEPS() {
      return KUBERNETES_EXECUTOR_POD_FEATURE_STEPS;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_DECOMMISSION_LABEL() {
      return KUBERNETES_EXECUTOR_DECOMMISSION_LABEL;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_DECOMMISSION_LABEL_VALUE() {
      return KUBERNETES_EXECUTOR_DECOMMISSION_LABEL_VALUE;
   }

   public ConfigEntry KUBERNETES_ALLOCATION_PODS_ALLOCATOR() {
      return KUBERNETES_ALLOCATION_PODS_ALLOCATOR;
   }

   public ConfigEntry KUBERNETES_ALLOCATION_BATCH_SIZE() {
      return KUBERNETES_ALLOCATION_BATCH_SIZE;
   }

   public ConfigEntry KUBERNETES_ALLOCATION_BATCH_DELAY() {
      return KUBERNETES_ALLOCATION_BATCH_DELAY;
   }

   public ConfigEntry KUBERNETES_ALLOCATION_DRIVER_READINESS_TIMEOUT() {
      return KUBERNETES_ALLOCATION_DRIVER_READINESS_TIMEOUT;
   }

   public ConfigEntry KUBERNETES_ALLOCATION_EXECUTOR_TIMEOUT() {
      return KUBERNETES_ALLOCATION_EXECUTOR_TIMEOUT;
   }

   public ConfigEntry WAIT_FOR_APP_COMPLETION() {
      return WAIT_FOR_APP_COMPLETION;
   }

   public ConfigEntry REPORT_INTERVAL() {
      return REPORT_INTERVAL;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_ENABLE_API_POLLING() {
      return KUBERNETES_EXECUTOR_ENABLE_API_POLLING;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_ENABLE_API_WATCHER() {
      return KUBERNETES_EXECUTOR_ENABLE_API_WATCHER;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_API_POLLING_INTERVAL() {
      return KUBERNETES_EXECUTOR_API_POLLING_INTERVAL;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_API_POLLING_WITH_RESOURCE_VERSION() {
      return KUBERNETES_EXECUTOR_API_POLLING_WITH_RESOURCE_VERSION;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_EVENT_PROCESSING_INTERVAL() {
      return KUBERNETES_EXECUTOR_EVENT_PROCESSING_INTERVAL;
   }

   public ConfigEntry MEMORY_OVERHEAD_FACTOR() {
      return MEMORY_OVERHEAD_FACTOR;
   }

   public OptionalConfigEntry PYSPARK_MAJOR_PYTHON_VERSION() {
      return PYSPARK_MAJOR_PYTHON_VERSION;
   }

   public OptionalConfigEntry KUBERNETES_KERBEROS_KRB5_FILE() {
      return KUBERNETES_KERBEROS_KRB5_FILE;
   }

   public OptionalConfigEntry KUBERNETES_KERBEROS_KRB5_CONFIG_MAP() {
      return KUBERNETES_KERBEROS_KRB5_CONFIG_MAP;
   }

   public OptionalConfigEntry KUBERNETES_HADOOP_CONF_CONFIG_MAP() {
      return KUBERNETES_HADOOP_CONF_CONFIG_MAP;
   }

   public OptionalConfigEntry KUBERNETES_KERBEROS_DT_SECRET_NAME() {
      return KUBERNETES_KERBEROS_DT_SECRET_NAME;
   }

   public OptionalConfigEntry KUBERNETES_KERBEROS_DT_SECRET_ITEM_KEY() {
      return KUBERNETES_KERBEROS_DT_SECRET_ITEM_KEY;
   }

   public OptionalConfigEntry APP_RESOURCE_TYPE() {
      return APP_RESOURCE_TYPE;
   }

   public ConfigEntry KUBERNETES_LOCAL_DIRS_TMPFS() {
      return KUBERNETES_LOCAL_DIRS_TMPFS;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_PODTEMPLATE_FILE() {
      return KUBERNETES_DRIVER_PODTEMPLATE_FILE;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_PODTEMPLATE_FILE() {
      return KUBERNETES_EXECUTOR_PODTEMPLATE_FILE;
   }

   public OptionalConfigEntry KUBERNETES_DRIVER_PODTEMPLATE_CONTAINER_NAME() {
      return KUBERNETES_DRIVER_PODTEMPLATE_CONTAINER_NAME;
   }

   public OptionalConfigEntry KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME() {
      return KUBERNETES_EXECUTOR_PODTEMPLATE_CONTAINER_NAME;
   }

   public String KUBERNETES_AUTH_SUBMISSION_CONF_PREFIX() {
      return KUBERNETES_AUTH_SUBMISSION_CONF_PREFIX;
   }

   public ConfigEntry KUBERNETES_TRUST_CERTIFICATES() {
      return KUBERNETES_TRUST_CERTIFICATES;
   }

   public String KUBERNETES_NODE_SELECTOR_PREFIX() {
      return KUBERNETES_NODE_SELECTOR_PREFIX;
   }

   public String KUBERNETES_DRIVER_NODE_SELECTOR_PREFIX() {
      return KUBERNETES_DRIVER_NODE_SELECTOR_PREFIX;
   }

   public String KUBERNETES_EXECUTOR_NODE_SELECTOR_PREFIX() {
      return KUBERNETES_EXECUTOR_NODE_SELECTOR_PREFIX;
   }

   public ConfigEntry KUBERNETES_DELETE_EXECUTORS() {
      return KUBERNETES_DELETE_EXECUTORS;
   }

   public ConfigEntry KUBERNETES_DYN_ALLOC_KILL_GRACE_PERIOD() {
      return KUBERNETES_DYN_ALLOC_KILL_GRACE_PERIOD;
   }

   public OptionalConfigEntry KUBERNETES_SUBMIT_GRACE_PERIOD() {
      return KUBERNETES_SUBMIT_GRACE_PERIOD;
   }

   public OptionalConfigEntry KUBERNETES_FILE_UPLOAD_PATH() {
      return KUBERNETES_FILE_UPLOAD_PATH;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_CHECK_ALL_CONTAINERS() {
      return KUBERNETES_EXECUTOR_CHECK_ALL_CONTAINERS;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_MISSING_POD_DETECT_DELTA() {
      return KUBERNETES_EXECUTOR_MISSING_POD_DETECT_DELTA;
   }

   public ConfigEntry KUBERNETES_MAX_PENDING_PODS() {
      return KUBERNETES_MAX_PENDING_PODS;
   }

   public ConfigEntry KUBERNETES_EXECUTOR_SNAPSHOTS_SUBSCRIBERS_GRACE_PERIOD() {
      return KUBERNETES_EXECUTOR_SNAPSHOTS_SUBSCRIBERS_GRACE_PERIOD;
   }

   public String KUBERNETES_DRIVER_LABEL_PREFIX() {
      return KUBERNETES_DRIVER_LABEL_PREFIX;
   }

   public String KUBERNETES_DRIVER_ANNOTATION_PREFIX() {
      return KUBERNETES_DRIVER_ANNOTATION_PREFIX;
   }

   public String KUBERNETES_DRIVER_SERVICE_LABEL_PREFIX() {
      return KUBERNETES_DRIVER_SERVICE_LABEL_PREFIX;
   }

   public String KUBERNETES_DRIVER_SERVICE_ANNOTATION_PREFIX() {
      return KUBERNETES_DRIVER_SERVICE_ANNOTATION_PREFIX;
   }

   public String KUBERNETES_DRIVER_SECRETS_PREFIX() {
      return KUBERNETES_DRIVER_SECRETS_PREFIX;
   }

   public String KUBERNETES_DRIVER_SECRET_KEY_REF_PREFIX() {
      return KUBERNETES_DRIVER_SECRET_KEY_REF_PREFIX;
   }

   public String KUBERNETES_DRIVER_VOLUMES_PREFIX() {
      return KUBERNETES_DRIVER_VOLUMES_PREFIX;
   }

   public String KUBERNETES_EXECUTOR_LABEL_PREFIX() {
      return KUBERNETES_EXECUTOR_LABEL_PREFIX;
   }

   public String KUBERNETES_EXECUTOR_ANNOTATION_PREFIX() {
      return KUBERNETES_EXECUTOR_ANNOTATION_PREFIX;
   }

   public String KUBERNETES_EXECUTOR_SECRETS_PREFIX() {
      return KUBERNETES_EXECUTOR_SECRETS_PREFIX;
   }

   public String KUBERNETES_EXECUTOR_SECRET_KEY_REF_PREFIX() {
      return KUBERNETES_EXECUTOR_SECRET_KEY_REF_PREFIX;
   }

   public String KUBERNETES_EXECUTOR_VOLUMES_PREFIX() {
      return KUBERNETES_EXECUTOR_VOLUMES_PREFIX;
   }

   public String KUBERNETES_VOLUMES_HOSTPATH_TYPE() {
      return KUBERNETES_VOLUMES_HOSTPATH_TYPE;
   }

   public String KUBERNETES_VOLUMES_PVC_TYPE() {
      return KUBERNETES_VOLUMES_PVC_TYPE;
   }

   public String KUBERNETES_VOLUMES_EMPTYDIR_TYPE() {
      return KUBERNETES_VOLUMES_EMPTYDIR_TYPE;
   }

   public String KUBERNETES_VOLUMES_NFS_TYPE() {
      return KUBERNETES_VOLUMES_NFS_TYPE;
   }

   public String KUBERNETES_VOLUMES_MOUNT_PATH_KEY() {
      return KUBERNETES_VOLUMES_MOUNT_PATH_KEY;
   }

   public String KUBERNETES_VOLUMES_MOUNT_SUBPATH_KEY() {
      return KUBERNETES_VOLUMES_MOUNT_SUBPATH_KEY;
   }

   public String KUBERNETES_VOLUMES_MOUNT_SUBPATHEXPR_KEY() {
      return KUBERNETES_VOLUMES_MOUNT_SUBPATHEXPR_KEY;
   }

   public String KUBERNETES_VOLUMES_MOUNT_READONLY_KEY() {
      return KUBERNETES_VOLUMES_MOUNT_READONLY_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_PATH_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_PATH_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_TYPE_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_TYPE_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_CLAIM_NAME_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_CLAIM_NAME_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_CLAIM_STORAGE_CLASS_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_CLAIM_STORAGE_CLASS_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_MEDIUM_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_MEDIUM_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_SIZE_LIMIT_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_SIZE_LIMIT_KEY;
   }

   public String KUBERNETES_VOLUMES_OPTIONS_SERVER_KEY() {
      return KUBERNETES_VOLUMES_OPTIONS_SERVER_KEY;
   }

   public String KUBERNETES_VOLUMES_LABEL_KEY() {
      return KUBERNETES_VOLUMES_LABEL_KEY;
   }

   public String KUBERNETES_VOLUMES_ANNOTATION_KEY() {
      return KUBERNETES_VOLUMES_ANNOTATION_KEY;
   }

   public String KUBERNETES_DRIVER_ENV_PREFIX() {
      return KUBERNETES_DRIVER_ENV_PREFIX;
   }

   public int KUBERNETES_DNS_SUBDOMAIN_NAME_MAX_LENGTH() {
      return KUBERNETES_DNS_SUBDOMAIN_NAME_MAX_LENGTH;
   }

   public int KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH() {
      return KUBERNETES_DNS_LABEL_NAME_MAX_LENGTH;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$KUBERNETES_EXECUTOR_POD_NAME_PREFIX$1(final String prefix) {
      return MODULE$.isValidExecutorPodNamePrefix(prefix);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$PYSPARK_MAJOR_PYTHON_VERSION$1(final String x$6) {
      return "3".equals(x$6);
   }

   private Config$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
