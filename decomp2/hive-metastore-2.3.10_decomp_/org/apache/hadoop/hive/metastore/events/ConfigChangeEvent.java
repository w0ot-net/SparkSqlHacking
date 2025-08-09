package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;

public class ConfigChangeEvent extends ListenerEvent {
   private final String key;
   private final String oldValue;
   private final String newValue;

   public ConfigChangeEvent(HiveMetaStore.HMSHandler handler, String key, String oldValue, String newValue) {
      super(true, handler);
      this.key = key;
      this.oldValue = oldValue;
      this.newValue = newValue;
   }

   public String getKey() {
      return this.key;
   }

   public String getOldValue() {
      return this.oldValue;
   }

   public String getNewValue() {
      return this.newValue;
   }
}
