package org.apache.hadoop.hive.metastore.messaging;

import org.apache.hadoop.hive.metastore.api.Function;

public abstract class CreateFunctionMessage extends EventMessage {
   protected CreateFunctionMessage() {
      super(EventMessage.EventType.CREATE_FUNCTION);
   }

   public abstract Function getFunctionObj() throws Exception;

   public EventMessage checkValid() {
      try {
         if (this.getFunctionObj() == null) {
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
