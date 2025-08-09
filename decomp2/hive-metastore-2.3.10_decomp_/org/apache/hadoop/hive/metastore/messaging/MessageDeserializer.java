package org.apache.hadoop.hive.metastore.messaging;

public abstract class MessageDeserializer {
   public EventMessage getEventMessage(String eventTypeString, String messageBody) {
      switch (EventMessage.EventType.valueOf(eventTypeString)) {
         case CREATE_DATABASE:
            return this.getCreateDatabaseMessage(messageBody);
         case DROP_DATABASE:
            return this.getDropDatabaseMessage(messageBody);
         case CREATE_TABLE:
            return this.getCreateTableMessage(messageBody);
         case ALTER_TABLE:
            return this.getAlterTableMessage(messageBody);
         case DROP_TABLE:
            return this.getDropTableMessage(messageBody);
         case ADD_PARTITION:
            return this.getAddPartitionMessage(messageBody);
         case ALTER_PARTITION:
            return this.getAlterPartitionMessage(messageBody);
         case DROP_PARTITION:
            return this.getDropPartitionMessage(messageBody);
         case CREATE_FUNCTION:
            return this.getCreateFunctionMessage(messageBody);
         case DROP_FUNCTION:
            return this.getDropFunctionMessage(messageBody);
         case CREATE_INDEX:
            return this.getCreateIndexMessage(messageBody);
         case DROP_INDEX:
            return this.getDropIndexMessage(messageBody);
         case ALTER_INDEX:
            return this.getAlterIndexMessage(messageBody);
         case INSERT:
            return this.getInsertMessage(messageBody);
         default:
            throw new IllegalArgumentException("Unsupported event-type: " + eventTypeString);
      }
   }

   public abstract CreateDatabaseMessage getCreateDatabaseMessage(String var1);

   public abstract DropDatabaseMessage getDropDatabaseMessage(String var1);

   public abstract CreateTableMessage getCreateTableMessage(String var1);

   public abstract AlterTableMessage getAlterTableMessage(String var1);

   public abstract DropTableMessage getDropTableMessage(String var1);

   public abstract AddPartitionMessage getAddPartitionMessage(String var1);

   public abstract AlterPartitionMessage getAlterPartitionMessage(String var1);

   public abstract DropPartitionMessage getDropPartitionMessage(String var1);

   public abstract CreateFunctionMessage getCreateFunctionMessage(String var1);

   public abstract DropFunctionMessage getDropFunctionMessage(String var1);

   public abstract CreateIndexMessage getCreateIndexMessage(String var1);

   public abstract DropIndexMessage getDropIndexMessage(String var1);

   public abstract AlterIndexMessage getAlterIndexMessage(String var1);

   public abstract InsertMessage getInsertMessage(String var1);

   protected MessageDeserializer() {
   }
}
