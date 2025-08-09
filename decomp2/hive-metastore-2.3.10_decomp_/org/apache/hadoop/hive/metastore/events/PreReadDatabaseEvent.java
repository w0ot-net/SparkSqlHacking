package org.apache.hadoop.hive.metastore.events;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Database;

public class PreReadDatabaseEvent extends PreEventContext {
   private final Database db;

   public PreReadDatabaseEvent(Database db, HiveMetaStore.HMSHandler handler) {
      super(PreEventContext.PreEventType.READ_DATABASE, handler);
      this.db = db;
   }

   public Database getDatabase() {
      return this.db;
   }
}
