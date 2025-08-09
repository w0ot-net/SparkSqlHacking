package org.apache.hadoop.hive.metastore.messaging;

import java.util.List;
import org.apache.hadoop.hive.metastore.api.Table;

public abstract class DropPartitionMessage extends EventMessage {
   protected DropPartitionMessage() {
      super(EventMessage.EventType.DROP_PARTITION);
   }

   public abstract String getTable();

   public abstract Table getTableObj() throws Exception;

   public abstract List getPartitions();

   public EventMessage checkValid() {
      if (this.getTable() == null) {
         throw new IllegalStateException("Table name unset.");
      } else if (this.getPartitions() == null) {
         throw new IllegalStateException("Partition-list unset.");
      } else {
         return super.checkValid();
      }
   }
}
