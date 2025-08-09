package org.apache.hadoop.hive.metastore.messaging;

import java.util.Map;

public abstract class InsertMessage extends EventMessage {
   protected InsertMessage() {
      super(EventMessage.EventType.INSERT);
   }

   public abstract String getTable();

   public abstract Map getPartitionKeyValues();

   public abstract Iterable getFiles();

   public EventMessage checkValid() {
      if (this.getTable() == null) {
         throw new IllegalStateException("Table name unset.");
      } else {
         return super.checkValid();
      }
   }
}
