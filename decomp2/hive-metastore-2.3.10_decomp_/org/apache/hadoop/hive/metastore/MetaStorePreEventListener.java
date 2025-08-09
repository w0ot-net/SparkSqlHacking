package org.apache.hadoop.hive.metastore;

import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.api.InvalidOperationException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.events.PreEventContext;

public abstract class MetaStorePreEventListener implements Configurable {
   private Configuration conf;

   public MetaStorePreEventListener(Configuration config) {
      this.conf = config;
   }

   public abstract void onEvent(PreEventContext var1) throws MetaException, NoSuchObjectException, InvalidOperationException;

   public Configuration getConf() {
      return this.conf;
   }

   public void setConf(Configuration config) {
      this.conf = config;
   }
}
