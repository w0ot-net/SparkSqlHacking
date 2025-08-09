package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Index;

public class PreAlterIndexEvent extends PreEventContext {
   private final Index newIndex;
   private final Index oldIndex;

   public PreAlterIndexEvent(Index oldIndex, Index newIndex, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.ALTER_INDEX, handler);
      this.oldIndex = oldIndex;
      this.newIndex = newIndex;
   }

   public Index getOldIndex() {
      return this.oldIndex;
   }

   public Index getNewIndex() {
      return this.newIndex;
   }
}
