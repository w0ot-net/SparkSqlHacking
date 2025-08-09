package org.apache.hadoop.hive.metastore.events;

import java.util.Map;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.api.Table;

public class LoadPartitionDoneEvent extends ListenerEvent {
   private final Table table;
   private final Map partSpec;

   public LoadPartitionDoneEvent(boolean status, Table table, Map partSpec, HiveMetaStore.HMSHandler handler) {
      super(status, handler);
      this.table = table;
      this.partSpec = partSpec;
   }

   public Table getTable() {
      return this.table;
   }

   public Map getPartitionName() {
      return this.partSpec;
   }
}
