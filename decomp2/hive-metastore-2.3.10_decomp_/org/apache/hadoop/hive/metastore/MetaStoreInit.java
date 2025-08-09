package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.JavaUtils;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.hooks.JDOConnectionURLHook;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaStoreInit {
   private static final Logger LOG = LoggerFactory.getLogger(MetaStoreInit.class);

   static boolean updateConnectionURL(HiveConf originalConf, Configuration activeConf, String badUrl, MetaStoreInitData updateData) throws MetaException {
      String connectUrl = null;
      String currentUrl = getConnectionURL(activeConf);

      try {
         initConnectionUrlHook(originalConf, updateData);
         if (updateData.urlHook != null) {
            if (badUrl != null) {
               updateData.urlHook.notifyBadConnectionUrl(badUrl);
            }

            connectUrl = updateData.urlHook.getJdoConnectionUrl(originalConf);
         }
      } catch (Exception e) {
         LOG.error("Exception while getting connection URL from the hook: " + e);
      }

      if (connectUrl != null && !connectUrl.equals(currentUrl)) {
         LOG.error(String.format("Overriding %s with %s", ConfVars.METASTORECONNECTURLKEY.toString(), connectUrl));
         activeConf.set(ConfVars.METASTORECONNECTURLKEY.toString(), connectUrl);
         return true;
      } else {
         return false;
      }
   }

   static String getConnectionURL(Configuration conf) {
      return conf.get(ConfVars.METASTORECONNECTURLKEY.toString(), "");
   }

   private static synchronized void initConnectionUrlHook(HiveConf hiveConf, MetaStoreInitData updateData) throws ClassNotFoundException {
      String className = hiveConf.get(ConfVars.METASTORECONNECTURLHOOK.toString(), "").trim();
      if (className.equals("")) {
         updateData.urlHookClassName = "";
         updateData.urlHook = null;
      } else {
         boolean urlHookChanged = !updateData.urlHookClassName.equals(className);
         if (updateData.urlHook == null || urlHookChanged) {
            updateData.urlHookClassName = className.trim();
            Class<?> urlHookClass = Class.forName(updateData.urlHookClassName, true, JavaUtils.getClassLoader());
            updateData.urlHook = (JDOConnectionURLHook)ReflectionUtils.newInstance(urlHookClass, (Configuration)null);
         }

      }
   }

   static class MetaStoreInitData {
      JDOConnectionURLHook urlHook = null;
      String urlHookClassName = "";
   }
}
