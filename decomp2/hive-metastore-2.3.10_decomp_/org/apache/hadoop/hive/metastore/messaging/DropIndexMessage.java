package org.apache.hadoop.hive.metastore.messaging;

public abstract class DropIndexMessage extends EventMessage {
   public abstract String getIndexName();

   public abstract String getOrigTableName();

   public abstract String getIndexTableName();

   protected DropIndexMessage() {
      super(EventMessage.EventType.DROP_INDEX);
   }

   public EventMessage checkValid() {
      if (this.getIndexName() == null) {
         throw new IllegalStateException("Index name unset.");
      } else if (this.getOrigTableName() == null) {
         throw new IllegalStateException("Index original table name unset.");
      } else {
         return super.checkValid();
      }
   }
}
