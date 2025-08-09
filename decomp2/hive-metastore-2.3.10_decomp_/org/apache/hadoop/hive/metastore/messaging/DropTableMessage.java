package org.apache.hadoop.hive.metastore.messaging;

public abstract class DropTableMessage extends EventMessage {
   protected DropTableMessage() {
      super(EventMessage.EventType.DROP_TABLE);
   }

   public abstract String getTable();

   public EventMessage checkValid() {
      if (this.getTable() == null) {
         throw new IllegalStateException("Table name unset.");
      } else {
         return super.checkValid();
      }
   }
}
