package org.apache.spark.deploy.k8s;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Stable;

@Stable
@DeveloperApi
public final class Constants$ {
   public static final Constants$ MODULE$ = new Constants$();
   private static final String SPARK_VERSION_LABEL = "spark-version";
   private static final String SPARK_APP_ID_LABEL = "spark-app-selector";
   private static final String SPARK_APP_NAME_LABEL = "spark-app-name";
   private static final String SPARK_EXECUTOR_ID_LABEL = "spark-exec-id";
   private static final String SPARK_RESOURCE_PROFILE_ID_LABEL = "spark-exec-resourceprofile-id";
   private static final String SPARK_ROLE_LABEL = "spark-role";
   private static final String SPARK_POD_DRIVER_ROLE = "driver";
   private static final String SPARK_POD_EXECUTOR_ROLE = "executor";
   private static final String SPARK_EXECUTOR_INACTIVE_LABEL = "spark-exec-inactive";
   private static final String DRIVER_CREDENTIALS_SECRETS_BASE_DIR = "/mnt/secrets/spark-kubernetes-credentials";
   private static final String DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME = "ca-cert";
   private static final String DRIVER_CREDENTIALS_CA_CERT_PATH;
   private static final String DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME;
   private static final String DRIVER_CREDENTIALS_CLIENT_KEY_PATH;
   private static final String DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME;
   private static final String DRIVER_CREDENTIALS_CLIENT_CERT_PATH;
   private static final String DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME;
   private static final String DRIVER_CREDENTIALS_OAUTH_TOKEN_PATH;
   private static final String DRIVER_CREDENTIALS_SECRET_VOLUME_NAME;
   private static final int DEFAULT_DRIVER_PORT;
   private static final int DEFAULT_BLOCKMANAGER_PORT;
   private static final String DRIVER_PORT_NAME;
   private static final String BLOCK_MANAGER_PORT_NAME;
   private static final String UI_PORT_NAME;
   private static final String ENV_DRIVER_POD_IP;
   private static final String ENV_DRIVER_URL;
   private static final String ENV_EXECUTOR_CORES;
   private static final String ENV_EXECUTOR_MEMORY;
   private static final String ENV_EXECUTOR_DIRS;
   private static final String ENV_APPLICATION_ID;
   private static final String ENV_EXECUTOR_ID;
   private static final String ENV_EXECUTOR_POD_IP;
   private static final String ENV_EXECUTOR_POD_NAME;
   private static final String ENV_EXECUTOR_ATTRIBUTE_APP_ID;
   private static final String ENV_EXECUTOR_ATTRIBUTE_EXECUTOR_ID;
   private static final String ENV_JAVA_OPT_PREFIX;
   private static final String ENV_CLASSPATH;
   private static final String ENV_DRIVER_BIND_ADDRESS;
   private static final String ENV_SPARK_CONF_DIR;
   private static final String ENV_SPARK_USER;
   private static final String ENV_RESOURCE_PROFILE_ID;
   private static final String SPARK_CONF_VOLUME_DRIVER;
   private static final String SPARK_CONF_VOLUME_EXEC;
   private static final String SPARK_CONF_DIR_INTERNAL;
   private static final String SPARK_CONF_FILE_NAME;
   private static final String SPARK_CONF_PATH;
   private static final String ENV_HADOOP_TOKEN_FILE_LOCATION;
   private static final String ENV_PYSPARK_PYTHON;
   private static final String ENV_PYSPARK_DRIVER_PYTHON;
   private static final String EXECUTOR_POD_SPEC_TEMPLATE_FILE_NAME;
   private static final String EXECUTOR_POD_SPEC_TEMPLATE_MOUNTPATH;
   private static final String POD_TEMPLATE_VOLUME;
   private static final String POD_TEMPLATE_CONFIGMAP;
   private static final String POD_TEMPLATE_KEY;
   private static final String KUBERNETES_MASTER_INTERNAL_URL;
   private static final String DEFAULT_DRIVER_CONTAINER_NAME;
   private static final String DEFAULT_EXECUTOR_CONTAINER_NAME;
   private static final double NON_JVM_MEMORY_OVERHEAD_FACTOR;
   private static final String HADOOP_CONF_VOLUME;
   private static final String KRB_FILE_VOLUME;
   private static final String HADOOP_CONF_DIR_PATH;
   private static final String KRB_FILE_DIR_PATH;
   private static final String ENV_HADOOP_CONF_DIR;
   private static final String HADOOP_CONFIG_MAP_NAME;
   private static final String KERBEROS_DT_SECRET_NAME;
   private static final String KERBEROS_DT_SECRET_KEY;
   private static final String KERBEROS_SECRET_KEY;
   private static final String KERBEROS_KEYTAB_VOLUME;
   private static final String KERBEROS_KEYTAB_MOUNT_POINT;
   private static final String SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR;
   private static final String SPARK_APP_HADOOP_SECRET_VOLUME_NAME;
   private static final String APP_RESOURCE_TYPE_JAVA;
   private static final String APP_RESOURCE_TYPE_PYTHON;
   private static final String APP_RESOURCE_TYPE_R;

   static {
      String var10000 = MODULE$.DRIVER_CREDENTIALS_SECRETS_BASE_DIR();
      DRIVER_CREDENTIALS_CA_CERT_PATH = var10000 + "/" + MODULE$.DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME();
      DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME = "client-key";
      var10000 = MODULE$.DRIVER_CREDENTIALS_SECRETS_BASE_DIR();
      DRIVER_CREDENTIALS_CLIENT_KEY_PATH = var10000 + "/" + MODULE$.DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME();
      DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME = "client-cert";
      var10000 = MODULE$.DRIVER_CREDENTIALS_SECRETS_BASE_DIR();
      DRIVER_CREDENTIALS_CLIENT_CERT_PATH = var10000 + "/" + MODULE$.DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME();
      DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME = "oauth-token";
      var10000 = MODULE$.DRIVER_CREDENTIALS_SECRETS_BASE_DIR();
      DRIVER_CREDENTIALS_OAUTH_TOKEN_PATH = var10000 + "/" + MODULE$.DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME();
      DRIVER_CREDENTIALS_SECRET_VOLUME_NAME = "kubernetes-credentials";
      DEFAULT_DRIVER_PORT = 7078;
      DEFAULT_BLOCKMANAGER_PORT = 7079;
      DRIVER_PORT_NAME = "driver-rpc-port";
      BLOCK_MANAGER_PORT_NAME = "blockmanager";
      UI_PORT_NAME = "spark-ui";
      ENV_DRIVER_POD_IP = "SPARK_DRIVER_POD_IP";
      ENV_DRIVER_URL = "SPARK_DRIVER_URL";
      ENV_EXECUTOR_CORES = "SPARK_EXECUTOR_CORES";
      ENV_EXECUTOR_MEMORY = "SPARK_EXECUTOR_MEMORY";
      ENV_EXECUTOR_DIRS = "SPARK_EXECUTOR_DIRS";
      ENV_APPLICATION_ID = "SPARK_APPLICATION_ID";
      ENV_EXECUTOR_ID = "SPARK_EXECUTOR_ID";
      ENV_EXECUTOR_POD_IP = "SPARK_EXECUTOR_POD_IP";
      ENV_EXECUTOR_POD_NAME = "SPARK_EXECUTOR_POD_NAME";
      ENV_EXECUTOR_ATTRIBUTE_APP_ID = "SPARK_EXECUTOR_ATTRIBUTE_APP_ID";
      ENV_EXECUTOR_ATTRIBUTE_EXECUTOR_ID = "SPARK_EXECUTOR_ATTRIBUTE_EXECUTOR_ID";
      ENV_JAVA_OPT_PREFIX = "SPARK_JAVA_OPT_";
      ENV_CLASSPATH = "SPARK_CLASSPATH";
      ENV_DRIVER_BIND_ADDRESS = "SPARK_DRIVER_BIND_ADDRESS";
      ENV_SPARK_CONF_DIR = "SPARK_CONF_DIR";
      ENV_SPARK_USER = "SPARK_USER";
      ENV_RESOURCE_PROFILE_ID = "SPARK_RESOURCE_PROFILE_ID";
      SPARK_CONF_VOLUME_DRIVER = "spark-conf-volume-driver";
      SPARK_CONF_VOLUME_EXEC = "spark-conf-volume-exec";
      SPARK_CONF_DIR_INTERNAL = "/opt/spark/conf";
      SPARK_CONF_FILE_NAME = "spark.properties";
      var10000 = MODULE$.SPARK_CONF_DIR_INTERNAL();
      SPARK_CONF_PATH = var10000 + "/" + MODULE$.SPARK_CONF_FILE_NAME();
      ENV_HADOOP_TOKEN_FILE_LOCATION = "HADOOP_TOKEN_FILE_LOCATION";
      ENV_PYSPARK_PYTHON = "PYSPARK_PYTHON";
      ENV_PYSPARK_DRIVER_PYTHON = "PYSPARK_DRIVER_PYTHON";
      EXECUTOR_POD_SPEC_TEMPLATE_FILE_NAME = "pod-spec-template.yml";
      EXECUTOR_POD_SPEC_TEMPLATE_MOUNTPATH = "/opt/spark/pod-template";
      POD_TEMPLATE_VOLUME = "pod-template-volume";
      POD_TEMPLATE_CONFIGMAP = "driver-podspec-conf-map";
      POD_TEMPLATE_KEY = "podspec-configmap-key";
      KUBERNETES_MASTER_INTERNAL_URL = "https://kubernetes.default.svc";
      DEFAULT_DRIVER_CONTAINER_NAME = "spark-kubernetes-driver";
      DEFAULT_EXECUTOR_CONTAINER_NAME = "spark-kubernetes-executor";
      NON_JVM_MEMORY_OVERHEAD_FACTOR = 0.4;
      HADOOP_CONF_VOLUME = "hadoop-properties";
      KRB_FILE_VOLUME = "krb5-file";
      HADOOP_CONF_DIR_PATH = "/opt/hadoop/conf";
      KRB_FILE_DIR_PATH = "/etc";
      ENV_HADOOP_CONF_DIR = "HADOOP_CONF_DIR";
      HADOOP_CONFIG_MAP_NAME = "spark.kubernetes.executor.hadoopConfigMapName";
      KERBEROS_DT_SECRET_NAME = "spark.kubernetes.kerberos.dt-secret-name";
      KERBEROS_DT_SECRET_KEY = "spark.kubernetes.kerberos.dt-secret-key";
      KERBEROS_SECRET_KEY = "hadoop-tokens";
      KERBEROS_KEYTAB_VOLUME = "kerberos-keytab";
      KERBEROS_KEYTAB_MOUNT_POINT = "/mnt/secrets/kerberos-keytab";
      SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR = "/mnt/secrets/hadoop-credentials";
      SPARK_APP_HADOOP_SECRET_VOLUME_NAME = "hadoop-secret";
      APP_RESOURCE_TYPE_JAVA = "java";
      APP_RESOURCE_TYPE_PYTHON = "python";
      APP_RESOURCE_TYPE_R = "r";
   }

   public String SPARK_VERSION_LABEL() {
      return SPARK_VERSION_LABEL;
   }

   public String SPARK_APP_ID_LABEL() {
      return SPARK_APP_ID_LABEL;
   }

   public String SPARK_APP_NAME_LABEL() {
      return SPARK_APP_NAME_LABEL;
   }

   public String SPARK_EXECUTOR_ID_LABEL() {
      return SPARK_EXECUTOR_ID_LABEL;
   }

   public String SPARK_RESOURCE_PROFILE_ID_LABEL() {
      return SPARK_RESOURCE_PROFILE_ID_LABEL;
   }

   public String SPARK_ROLE_LABEL() {
      return SPARK_ROLE_LABEL;
   }

   public String SPARK_POD_DRIVER_ROLE() {
      return SPARK_POD_DRIVER_ROLE;
   }

   public String SPARK_POD_EXECUTOR_ROLE() {
      return SPARK_POD_EXECUTOR_ROLE;
   }

   public String SPARK_EXECUTOR_INACTIVE_LABEL() {
      return SPARK_EXECUTOR_INACTIVE_LABEL;
   }

   public String DRIVER_CREDENTIALS_SECRETS_BASE_DIR() {
      return DRIVER_CREDENTIALS_SECRETS_BASE_DIR;
   }

   public String DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME() {
      return DRIVER_CREDENTIALS_CA_CERT_SECRET_NAME;
   }

   public String DRIVER_CREDENTIALS_CA_CERT_PATH() {
      return DRIVER_CREDENTIALS_CA_CERT_PATH;
   }

   public String DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME() {
      return DRIVER_CREDENTIALS_CLIENT_KEY_SECRET_NAME;
   }

   public String DRIVER_CREDENTIALS_CLIENT_KEY_PATH() {
      return DRIVER_CREDENTIALS_CLIENT_KEY_PATH;
   }

   public String DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME() {
      return DRIVER_CREDENTIALS_CLIENT_CERT_SECRET_NAME;
   }

   public String DRIVER_CREDENTIALS_CLIENT_CERT_PATH() {
      return DRIVER_CREDENTIALS_CLIENT_CERT_PATH;
   }

   public String DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME() {
      return DRIVER_CREDENTIALS_OAUTH_TOKEN_SECRET_NAME;
   }

   public String DRIVER_CREDENTIALS_OAUTH_TOKEN_PATH() {
      return DRIVER_CREDENTIALS_OAUTH_TOKEN_PATH;
   }

   public String DRIVER_CREDENTIALS_SECRET_VOLUME_NAME() {
      return DRIVER_CREDENTIALS_SECRET_VOLUME_NAME;
   }

   public int DEFAULT_DRIVER_PORT() {
      return DEFAULT_DRIVER_PORT;
   }

   public int DEFAULT_BLOCKMANAGER_PORT() {
      return DEFAULT_BLOCKMANAGER_PORT;
   }

   public String DRIVER_PORT_NAME() {
      return DRIVER_PORT_NAME;
   }

   public String BLOCK_MANAGER_PORT_NAME() {
      return BLOCK_MANAGER_PORT_NAME;
   }

   public String UI_PORT_NAME() {
      return UI_PORT_NAME;
   }

   public String ENV_DRIVER_POD_IP() {
      return ENV_DRIVER_POD_IP;
   }

   public String ENV_DRIVER_URL() {
      return ENV_DRIVER_URL;
   }

   public String ENV_EXECUTOR_CORES() {
      return ENV_EXECUTOR_CORES;
   }

   public String ENV_EXECUTOR_MEMORY() {
      return ENV_EXECUTOR_MEMORY;
   }

   public String ENV_EXECUTOR_DIRS() {
      return ENV_EXECUTOR_DIRS;
   }

   public String ENV_APPLICATION_ID() {
      return ENV_APPLICATION_ID;
   }

   public String ENV_EXECUTOR_ID() {
      return ENV_EXECUTOR_ID;
   }

   public String ENV_EXECUTOR_POD_IP() {
      return ENV_EXECUTOR_POD_IP;
   }

   public String ENV_EXECUTOR_POD_NAME() {
      return ENV_EXECUTOR_POD_NAME;
   }

   public String ENV_EXECUTOR_ATTRIBUTE_APP_ID() {
      return ENV_EXECUTOR_ATTRIBUTE_APP_ID;
   }

   public String ENV_EXECUTOR_ATTRIBUTE_EXECUTOR_ID() {
      return ENV_EXECUTOR_ATTRIBUTE_EXECUTOR_ID;
   }

   public String ENV_JAVA_OPT_PREFIX() {
      return ENV_JAVA_OPT_PREFIX;
   }

   public String ENV_CLASSPATH() {
      return ENV_CLASSPATH;
   }

   public String ENV_DRIVER_BIND_ADDRESS() {
      return ENV_DRIVER_BIND_ADDRESS;
   }

   public String ENV_SPARK_CONF_DIR() {
      return ENV_SPARK_CONF_DIR;
   }

   public String ENV_SPARK_USER() {
      return ENV_SPARK_USER;
   }

   public String ENV_RESOURCE_PROFILE_ID() {
      return ENV_RESOURCE_PROFILE_ID;
   }

   public String SPARK_CONF_VOLUME_DRIVER() {
      return SPARK_CONF_VOLUME_DRIVER;
   }

   public String SPARK_CONF_VOLUME_EXEC() {
      return SPARK_CONF_VOLUME_EXEC;
   }

   public String SPARK_CONF_DIR_INTERNAL() {
      return SPARK_CONF_DIR_INTERNAL;
   }

   public String SPARK_CONF_FILE_NAME() {
      return SPARK_CONF_FILE_NAME;
   }

   public String SPARK_CONF_PATH() {
      return SPARK_CONF_PATH;
   }

   public String ENV_HADOOP_TOKEN_FILE_LOCATION() {
      return ENV_HADOOP_TOKEN_FILE_LOCATION;
   }

   public String ENV_PYSPARK_PYTHON() {
      return ENV_PYSPARK_PYTHON;
   }

   public String ENV_PYSPARK_DRIVER_PYTHON() {
      return ENV_PYSPARK_DRIVER_PYTHON;
   }

   public String EXECUTOR_POD_SPEC_TEMPLATE_FILE_NAME() {
      return EXECUTOR_POD_SPEC_TEMPLATE_FILE_NAME;
   }

   public String EXECUTOR_POD_SPEC_TEMPLATE_MOUNTPATH() {
      return EXECUTOR_POD_SPEC_TEMPLATE_MOUNTPATH;
   }

   public String POD_TEMPLATE_VOLUME() {
      return POD_TEMPLATE_VOLUME;
   }

   public String POD_TEMPLATE_CONFIGMAP() {
      return POD_TEMPLATE_CONFIGMAP;
   }

   public String POD_TEMPLATE_KEY() {
      return POD_TEMPLATE_KEY;
   }

   public String KUBERNETES_MASTER_INTERNAL_URL() {
      return KUBERNETES_MASTER_INTERNAL_URL;
   }

   public String DEFAULT_DRIVER_CONTAINER_NAME() {
      return DEFAULT_DRIVER_CONTAINER_NAME;
   }

   public String DEFAULT_EXECUTOR_CONTAINER_NAME() {
      return DEFAULT_EXECUTOR_CONTAINER_NAME;
   }

   public double NON_JVM_MEMORY_OVERHEAD_FACTOR() {
      return NON_JVM_MEMORY_OVERHEAD_FACTOR;
   }

   public String HADOOP_CONF_VOLUME() {
      return HADOOP_CONF_VOLUME;
   }

   public String KRB_FILE_VOLUME() {
      return KRB_FILE_VOLUME;
   }

   public String HADOOP_CONF_DIR_PATH() {
      return HADOOP_CONF_DIR_PATH;
   }

   public String KRB_FILE_DIR_PATH() {
      return KRB_FILE_DIR_PATH;
   }

   public String ENV_HADOOP_CONF_DIR() {
      return ENV_HADOOP_CONF_DIR;
   }

   public String HADOOP_CONFIG_MAP_NAME() {
      return HADOOP_CONFIG_MAP_NAME;
   }

   public String KERBEROS_DT_SECRET_NAME() {
      return KERBEROS_DT_SECRET_NAME;
   }

   public String KERBEROS_DT_SECRET_KEY() {
      return KERBEROS_DT_SECRET_KEY;
   }

   public String KERBEROS_SECRET_KEY() {
      return KERBEROS_SECRET_KEY;
   }

   public String KERBEROS_KEYTAB_VOLUME() {
      return KERBEROS_KEYTAB_VOLUME;
   }

   public String KERBEROS_KEYTAB_MOUNT_POINT() {
      return KERBEROS_KEYTAB_MOUNT_POINT;
   }

   public String SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR() {
      return SPARK_APP_HADOOP_CREDENTIALS_BASE_DIR;
   }

   public String SPARK_APP_HADOOP_SECRET_VOLUME_NAME() {
      return SPARK_APP_HADOOP_SECRET_VOLUME_NAME;
   }

   public String APP_RESOURCE_TYPE_JAVA() {
      return APP_RESOURCE_TYPE_JAVA;
   }

   public String APP_RESOURCE_TYPE_PYTHON() {
      return APP_RESOURCE_TYPE_PYTHON;
   }

   public String APP_RESOURCE_TYPE_R() {
      return APP_RESOURCE_TYPE_R;
   }

   private Constants$() {
   }
}
