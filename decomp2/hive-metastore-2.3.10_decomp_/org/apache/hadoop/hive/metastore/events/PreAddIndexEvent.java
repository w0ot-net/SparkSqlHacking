package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Index;

public class PreAddIndexEvent extends PreEventContext {
   private final Index table;

   public PreAddIndexEvent(Index table, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.ADD_INDEX, handler);
      this.table = table;
   }

   public Index getIndex() {
      return this.table;
   }
}
