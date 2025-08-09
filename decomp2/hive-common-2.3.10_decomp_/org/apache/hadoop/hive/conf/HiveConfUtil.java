package org.apache.hadoop.hive.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.apache.hive.common.util.HiveStringUtils;

@InterfaceAudience.Private
public class HiveConfUtil {
   private static final String CLASS_NAME = HiveConfUtil.class.getName();
   private static final Log LOG;

   public static boolean isEmbeddedMetaStore(String msUri) {
      return msUri == null ? true : msUri.trim().isEmpty();
   }

   public static StringBuilder dumpConfig(HiveConf conf) {
      StringBuilder sb = new StringBuilder("START========\"HiveConf()\"========\n");
      sb.append("hiveDefaultUrl=").append(conf.getHiveDefaultLocation()).append('\n');
      sb.append("hiveSiteURL=").append(HiveConf.getHiveSiteLocation()).append('\n');
      sb.append("hiveServer2SiteUrl=").append(HiveConf.getHiveServer2SiteLocation()).append('\n');
      sb.append("hivemetastoreSiteUrl=").append(HiveConf.getMetastoreSiteLocation()).append('\n');
      dumpConfig(conf, sb);
      return sb.append("END========\"new HiveConf()\"========\n");
   }

   public static Set getHiddenSet(Configuration configuration) {
      Set<String> hiddenSet = new HashSet();
      String hiddenListStr = HiveConf.getVar(configuration, HiveConf.ConfVars.HIVE_CONF_HIDDEN_LIST);
      if (hiddenListStr != null) {
         for(String entry : hiddenListStr.split(",")) {
            hiddenSet.add(entry.trim());
         }
      }

      return hiddenSet;
   }

   public static void stripConfigurations(Configuration conf, Set hiddenSet) {
      for(String name : hiddenSet) {
         if (conf.get(name) != null) {
            conf.set(name, "");
         }
      }

   }

   public static void dumpConfig(Configuration originalConf, StringBuilder sb) {
      Set<String> hiddenSet = getHiddenSet(originalConf);
      sb.append("Values omitted for security reason if present: ").append(hiddenSet).append("\n");
      Configuration conf = new Configuration(originalConf);
      stripConfigurations(conf, hiddenSet);
      Iterator<Map.Entry<String, String>> configIter = conf.iterator();
      List<Map.Entry<String, String>> configVals = new ArrayList();

      while(configIter.hasNext()) {
         configVals.add(configIter.next());
      }

      Collections.sort(configVals, new Comparator() {
         public int compare(Map.Entry ent, Map.Entry ent2) {
            return ((String)ent.getKey()).compareTo((String)ent2.getKey());
         }
      });

      for(Map.Entry entry : configVals) {
         if (((String)entry.getKey()).toLowerCase().contains("path")) {
            StringTokenizer st = new StringTokenizer(conf.get((String)entry.getKey()), File.pathSeparator);
            sb.append((String)entry.getKey()).append("=\n");

            while(st.hasMoreTokens()) {
               sb.append("    ").append(st.nextToken()).append(File.pathSeparator).append('\n');
            }
         } else {
            sb.append((String)entry.getKey()).append('=').append(conf.get((String)entry.getKey())).append('\n');
         }
      }

   }

   public static void updateJobCredentialProviders(Configuration jobConf) {
      if (jobConf != null) {
         String jobKeyStoreLocation = jobConf.get(HiveConf.ConfVars.HIVE_SERVER2_JOB_CREDENTIAL_PROVIDER_PATH.varname);
         String oldKeyStoreLocation = jobConf.get("hadoop.security.credential.provider.path");
         if (StringUtils.isNotBlank(jobKeyStoreLocation)) {
            jobConf.set("hadoop.security.credential.provider.path", jobKeyStoreLocation);
            LOG.debug("Setting job conf credstore location to " + jobKeyStoreLocation + " previous location was " + oldKeyStoreLocation);
         }

         String credStorepassword = getJobCredentialProviderPassword(jobConf);
         if (credStorepassword != null) {
            String execEngine = jobConf.get(HiveConf.ConfVars.HIVE_EXECUTION_ENGINE.varname);
            if ("mr".equalsIgnoreCase(execEngine)) {
               addKeyValuePair(jobConf, "mapreduce.map.env", "HADOOP_CREDSTORE_PASSWORD", credStorepassword);
               addKeyValuePair(jobConf, "mapreduce.reduce.env", "HADOOP_CREDSTORE_PASSWORD", credStorepassword);
               addKeyValuePair(jobConf, "yarn.app.mapreduce.am.admin.user.env", "HADOOP_CREDSTORE_PASSWORD", credStorepassword);
            }
         }

      }
   }

   public static String getJobCredentialProviderPassword(Configuration conf) {
      String jobKeyStoreLocation = conf.get(HiveConf.ConfVars.HIVE_SERVER2_JOB_CREDENTIAL_PROVIDER_PATH.varname);
      String password = null;
      if (StringUtils.isNotBlank(jobKeyStoreLocation)) {
         password = System.getenv("HIVE_JOB_CREDSTORE_PASSWORD");
         if (StringUtils.isNotBlank(password)) {
            return password;
         }
      }

      password = System.getenv("HADOOP_CREDSTORE_PASSWORD");
      return StringUtils.isNotBlank(password) ? password : null;
   }

   private static void addKeyValuePair(Configuration jobConf, String property, String keyName, String newKeyValue) {
      String existingValue = jobConf.get(property);
      if (existingValue == null) {
         jobConf.set(property, keyName + "=" + newKeyValue);
      } else {
         String propertyValue = HiveStringUtils.insertValue(keyName, newKeyValue, existingValue);
         jobConf.set(property, propertyValue);
      }
   }

   static {
      LOG = LogFactory.getLog(CLASS_NAME);
   }
}
