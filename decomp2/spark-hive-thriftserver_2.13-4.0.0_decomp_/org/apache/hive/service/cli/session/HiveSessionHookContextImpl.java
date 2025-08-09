package org.apache.hive.service.cli.session;

import org.apache.hadoop.hive.conf.HiveConf;

public class HiveSessionHookContextImpl implements HiveSessionHookContext {
   private final HiveSession hiveSession;

   HiveSessionHookContextImpl(HiveSession hiveSession) {
      this.hiveSession = hiveSession;
   }

   public HiveConf getSessionConf() {
      return this.hiveSession.getHiveConf();
   }

   public String getSessionUser() {
      return this.hiveSession.getUserName();
   }

   public String getSessionHandle() {
      return this.hiveSession.getSessionHandle().toString();
   }
}
