package org.apache.hadoop.hive.metastore;

import java.util.AbstractMap;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;

public abstract class MetaStoreEndFunctionListener implements Configurable {
   private Configuration conf;

   public MetaStoreEndFunctionListener(Configuration config) {
      this.conf = config;
   }

   public abstract void onEndFunction(String var1, MetaStoreEndFunctionContext var2);

   public void exportCounters(AbstractMap counters) {
   }

   public Configuration getConf() {
      return this.conf;
   }

   public void setConf(Configuration config) {
      this.conf = config;
   }
}
