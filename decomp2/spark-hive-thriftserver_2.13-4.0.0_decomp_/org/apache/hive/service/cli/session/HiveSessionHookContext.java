package org.apache.hive.service.cli.session;

import org.apache.hadoop.hive.conf.HiveConf;

public interface HiveSessionHookContext {
   HiveConf getSessionConf();

   String getSessionUser();

   String getSessionHandle();
}
