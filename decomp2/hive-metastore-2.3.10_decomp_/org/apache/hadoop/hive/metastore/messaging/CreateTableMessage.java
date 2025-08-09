package org.apache.hadoop.hive.metastore.messaging;

import org.apache.hadoop.hive.metastore.api.Table;

public abstract class CreateTableMessage extends EventMessage {
   protected CreateTableMessage() {
      super(EventMessage.EventType.CREATE_TABLE);
   }

   public abstract String getTable();

   public abstract Table getTableObj() throws Exception;

   public abstract Iterable getFiles();

   public EventMessage checkValid() {
      if (this.getTable() == null) {
         throw new IllegalStateException("Table name unset.");
      } else {
         return super.checkValid();
      }
   }
}
