package org.apache.hadoop.hive.metastore.messaging;

import org.apache.hadoop.hive.metastore.api.Index;

public abstract class CreateIndexMessage extends EventMessage {
   protected CreateIndexMessage() {
      super(EventMessage.EventType.CREATE_INDEX);
   }

   public abstract Index getIndexObj() throws Exception;

   public EventMessage checkValid() {
      try {
         if (this.getIndexObj() == null) {
            throw new IllegalStateException("Function object unset.");
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
