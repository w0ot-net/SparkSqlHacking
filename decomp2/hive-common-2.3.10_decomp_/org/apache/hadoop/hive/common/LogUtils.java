package org.apache.hadoop.hive.common;

import com.google.common.annotations.VisibleForTesting;
import java.io.File;
import java.net.URL;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
   private static final String HIVE_L4J = "hive-log4j2.properties";
   private static final String HIVE_EXEC_L4J = "hive-exec-log4j2.properties";
   private static final Logger l4j = LoggerFactory.getLogger(LogUtils.class);
   private static final String KEY_TO_MASK_WITH = "password";
   private static final String MASKED_VALUE = "###_MASKED_###";

   public static String initHiveLog4j() throws LogInitializationException {
      return initHiveLog4jCommon(HiveConf.ConfVars.HIVE_LOG4J_FILE);
   }

   public static String initHiveExecLog4j() throws LogInitializationException {
      return initHiveLog4jCommon(HiveConf.ConfVars.HIVE_EXEC_LOG4J_FILE);
   }

   private static String initHiveLog4jCommon(HiveConf.ConfVars confVarName) throws LogInitializationException {
      HiveConf conf = new HiveConf();
      return initHiveLog4jCommon(conf, confVarName);
   }

   @VisibleForTesting
   public static String initHiveLog4jCommon(HiveConf conf, HiveConf.ConfVars confVarName) throws LogInitializationException {
      if (HiveConf.getVar(conf, confVarName).equals("")) {
         return initHiveLog4jDefault(conf, "", confVarName);
      } else {
         String log4jFileName = HiveConf.getVar(conf, confVarName);
         File log4jConfigFile = new File(log4jFileName);
         boolean fileExists = log4jConfigFile.exists();
         if (!fileExists) {
            return initHiveLog4jDefault(conf, "Not able to find conf file: " + log4jConfigFile, confVarName);
         } else {
            if (confVarName == HiveConf.ConfVars.HIVE_EXEC_LOG4J_FILE) {
               String queryId = HiveConf.getVar(conf, HiveConf.ConfVars.HIVEQUERYID);
               if (queryId == null || (queryId = queryId.trim()).isEmpty()) {
                  queryId = "unknown-" + System.currentTimeMillis();
               }

               System.setProperty(HiveConf.ConfVars.HIVEQUERYID.toString(), queryId);
            }

            boolean async = checkAndSetAsyncLogging(conf);
            Configurator.initialize((String)null, log4jFileName);
            logConfigLocation(conf);
            return "Logging initialized using configuration in " + log4jConfigFile + " Async: " + async;
         }
      }
   }

   public static boolean checkAndSetAsyncLogging(Configuration conf) {
      boolean asyncLogging = HiveConf.getBoolVar(conf, HiveConf.ConfVars.HIVE_ASYNC_LOG_ENABLED);
      if (asyncLogging) {
         System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
         LogManager.setFactory(new Log4jContextFactory());
      }

      return asyncLogging;
   }

   private static String initHiveLog4jDefault(HiveConf conf, String logMessage, HiveConf.ConfVars confVarName) throws LogInitializationException {
      URL hive_l4j = null;
      switch (confVarName) {
         case HIVE_EXEC_LOG4J_FILE:
            hive_l4j = LogUtils.class.getClassLoader().getResource("hive-exec-log4j2.properties");
            if (hive_l4j == null) {
               hive_l4j = LogUtils.class.getClassLoader().getResource("hive-log4j2.properties");
            }

            System.setProperty(HiveConf.ConfVars.HIVEQUERYID.toString(), HiveConf.getVar(conf, HiveConf.ConfVars.HIVEQUERYID));
            break;
         case HIVE_LOG4J_FILE:
            hive_l4j = LogUtils.class.getClassLoader().getResource("hive-log4j2.properties");
      }

      if (hive_l4j != null) {
         boolean async = checkAndSetAsyncLogging(conf);
         Configurator.initialize((String)null, hive_l4j.toString());
         logConfigLocation(conf);
         return logMessage + "\nLogging initialized using configuration in " + hive_l4j + " Async: " + async;
      } else {
         throw new LogInitializationException(logMessage + "Unable to initialize logging using " + "hive-log4j2.properties" + ", not found on CLASSPATH!");
      }
   }

   private static void logConfigLocation(HiveConf conf) throws LogInitializationException {
      if (conf.getHiveDefaultLocation() != null) {
         l4j.warn("DEPRECATED: Ignoring hive-default.xml found on the CLASSPATH at " + conf.getHiveDefaultLocation().getPath());
      }

      if (HiveConf.getHiveSiteLocation() == null) {
         l4j.warn("hive-site.xml not found on CLASSPATH");
      } else {
         l4j.debug("Using hive-site.xml found on CLASSPATH at " + HiveConf.getHiveSiteLocation().getPath());
      }

   }

   public static String maskIfPassword(String key, String value) {
      return key != null && value != null && key.toLowerCase().indexOf("password") != -1 ? "###_MASKED_###" : value;
   }

   public static class LogInitializationException extends Exception {
      public LogInitializationException(String msg) {
         super(msg);
      }
   }
}
