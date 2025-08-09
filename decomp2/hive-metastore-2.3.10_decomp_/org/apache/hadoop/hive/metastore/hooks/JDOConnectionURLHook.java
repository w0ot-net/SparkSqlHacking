package org.apache.hadoop.hive.metastore.hooks;

import org.apache.hadoop.conf.Configuration;

public interface JDOConnectionURLHook {
   String getJdoConnectionUrl(Configuration var1) throws Exception;

   void notifyBadConnectionUrl(String var1);
}
