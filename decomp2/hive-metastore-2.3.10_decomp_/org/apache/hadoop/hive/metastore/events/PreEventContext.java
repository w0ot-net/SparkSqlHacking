package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;

public abstract class PreEventContext {
   private final PreEventType eventType;
   private final HiveMetaStore.HMSHandler handler;

   public PreEventContext(PreEventType eventType, HiveMetaStore.HMSHandler handler) {
      this.eventType = eventType;
      this.handler = handler;
   }

   public PreEventType getEventType() {
      return this.eventType;
   }

   public HiveMetaStore.HMSHandler getHandler() {
      return this.handler;
   }

   public static enum PreEventType {
      CREATE_TABLE,
      DROP_TABLE,
      ALTER_TABLE,
      ADD_PARTITION,
      DROP_PARTITION,
      ALTER_PARTITION,
      CREATE_DATABASE,
      DROP_DATABASE,
      LOAD_PARTITION_DONE,
      AUTHORIZATION_API_CALL,
      READ_TABLE,
      READ_DATABASE,
      ADD_INDEX,
      ALTER_INDEX,
      DROP_INDEX;
   }
}
