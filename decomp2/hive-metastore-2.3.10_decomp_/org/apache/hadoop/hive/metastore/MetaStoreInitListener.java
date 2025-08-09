package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.api.MetaException;

public abstract class MetaStoreInitListener implements Configurable {
   private Configuration conf;

   public MetaStoreInitListener(Configuration config) {
      this.conf = config;
   }

   public abstract void onInit(MetaStoreInitContext var1) throws MetaException;

   public Configuration getConf() {
      return this.conf;
   }

   public void setConf(Configuration config) {
      this.conf = config;
   }
}
