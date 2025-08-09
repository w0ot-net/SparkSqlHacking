package org.apache.hadoop.hive.conf;

public class Constants {
   public static final String LLAP_LOGGER_NAME_QUERY_ROUTING = "query-routing";
   public static final String LLAP_LOGGER_NAME_CONSOLE = "console";
   public static final String LLAP_LOGGER_NAME_RFA = "RFA";
   public static final String DRUID_HIVE_STORAGE_HANDLER_ID = "org.apache.hadoop.hive.druid.DruidStorageHandler";
   public static final String DRUID_HIVE_OUTPUT_FORMAT = "org.apache.hadoop.hive.druid.io.DruidOutputFormat";
   public static final String DRUID_DATA_SOURCE = "druid.datasource";
   public static final String DRUID_SEGMENT_GRANULARITY = "druid.segment.granularity";
   public static final String DRUID_QUERY_GRANULARITY = "druid.query.granularity";
   public static final String DRUID_TIMESTAMP_GRANULARITY_COL_NAME = "__time_granularity";
   public static final String DRUID_QUERY_JSON = "druid.query.json";
   public static final String DRUID_QUERY_TYPE = "druid.query.type";
   public static final String DRUID_QUERY_FETCH = "druid.query.fetch";
   public static final String DRUID_SEGMENT_DIRECTORY = "druid.storage.storageDirectory";
   public static final String DRUID_SEGMENT_VERSION = "druid.segment.version";
   public static final String DRUID_JOB_WORKING_DIRECTORY = "druid.job.workingDirectory";
   public static final String HIVE_SERVER2_JOB_CREDSTORE_PASSWORD_ENVVAR = "HIVE_JOB_CREDSTORE_PASSWORD";
   public static final String HADOOP_CREDENTIAL_PASSWORD_ENVVAR = "HADOOP_CREDSTORE_PASSWORD";
   public static final String HADOOP_CREDENTIAL_PROVIDER_PATH_CONFIG = "hadoop.security.credential.provider.path";
}
