package org.apache.hadoop.hive.metastore.messaging;

public abstract class CreateDatabaseMessage extends EventMessage {
   protected CreateDatabaseMessage() {
      super(EventMessage.EventType.CREATE_DATABASE);
   }
}
