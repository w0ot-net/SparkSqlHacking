package org.apache.hadoop.hive.metastore.messaging;

import org.apache.hadoop.hive.metastore.api.Table;

public abstract class AlterTableMessage extends EventMessage {
   protected AlterTableMessage() {
      super(EventMessage.EventType.ALTER_TABLE);
   }

   public abstract String getTable();

   public abstract Table getTableObjBefore() throws Exception;

   public abstract Table getTableObjAfter() throws Exception;

   public EventMessage checkValid() {
      if (this.getTable() == null) {
         throw new IllegalStateException("Table name unset.");
      } else {
         try {
            if (this.getTableObjAfter() == null) {
               throw new IllegalStateException("Table object(after) not set.");
            }

            if (this.getTableObjBefore() == null) {
               throw new IllegalStateException("Table object(before) not set.");
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
