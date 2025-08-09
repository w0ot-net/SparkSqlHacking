package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Index;

public class DropIndexEvent extends ListenerEvent {
   private final Index index;

   public DropIndexEvent(Index index, boolean status, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.index = index;
   }

   public Index getIndex() {
      return this.index;
   }
}
