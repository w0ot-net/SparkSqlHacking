package org.apache.hadoop.hive.metastore.messaging;

public abstract class DropFunctionMessage extends EventMessage {
   public abstract String getFunctionName();

   protected DropFunctionMessage() {
      super(EventMessage.EventType.DROP_FUNCTION);
   }

   public EventMessage checkValid() {
      if (this.getFunctionName() == null) {
         throw new IllegalStateException("Function name unset.");
      } else {
         return super.checkValid();
      }
   }
}
