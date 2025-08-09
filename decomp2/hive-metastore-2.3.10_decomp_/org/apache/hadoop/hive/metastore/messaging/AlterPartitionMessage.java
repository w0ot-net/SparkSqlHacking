package org.apache.hadoop.hive.metastore.messaging;

import java.util.Map;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Table;

public abstract class AlterPartitionMessage extends EventMessage {
   protected AlterPartitionMessage() {
      super(EventMessage.EventType.ALTER_PARTITION);
   }

   public abstract String getTable();

   public abstract Map getKeyValues();

   public abstract Table getTableObj() throws Exception;

   public abstract Partition getPtnObjBefore() throws Exception;

   public abstract Partition getPtnObjAfter() throws Exception;

   public EventMessage checkValid() {
      if (this.getTable() == null) {
         throw new IllegalStateException("Table name unset.");
      } else if (this.getKeyValues() == null) {
         throw new IllegalStateException("Partition values unset");
      } else {
         try {
            if (this.getTableObj() == null) {
               throw new IllegalStateException("Table object not set.");
            }

            if (this.getPtnObjAfter() == null) {
               throw new IllegalStateException("Partition object(after) not set.");
            }

            if (this.getPtnObjBefore() == null) {
               throw new IllegalStateException("Partition object(before) not set.");
            }
         } catch (Exception e) {
            if (!(e instanceof IllegalStateException)) {
               throw new IllegalStateException("Event not set up correctly", e);
            }

            throw (IllegalStateException)e;
         }

         return super.checkValid();
      }
   }
}
