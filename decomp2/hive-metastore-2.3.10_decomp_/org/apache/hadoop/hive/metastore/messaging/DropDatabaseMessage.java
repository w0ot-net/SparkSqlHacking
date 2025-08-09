package org.apache.hadoop.hive.metastore.messaging;

public abstract class DropDatabaseMessage extends EventMessage {
   protected DropDatabaseMessage() {
      super(EventMessage.EventType.DROP_DATABASE);
   }
}
