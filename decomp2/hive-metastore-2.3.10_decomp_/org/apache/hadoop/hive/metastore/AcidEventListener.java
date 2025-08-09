package org.apache.hadoop.hive.metastore;

import java.util.Iterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.HiveObjectType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Table;
import org.apache.hadoop.hive.metastore.events.DropDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.DropPartitionEvent;
import org.apache.hadoop.hive.metastore.events.DropTableEvent;
import org.apache.hadoop.hive.metastore.txn.TxnStore;
import org.apache.hadoop.hive.metastore.txn.TxnUtils;

public class AcidEventListener extends MetaStoreEventListener {
   private TxnStore txnHandler;
   private HiveConf hiveConf;

   public AcidEventListener(Configuration configuration) {
      super(configuration);
      this.hiveConf = (HiveConf)configuration;
   }

   public void onDropDatabase(DropDatabaseEvent dbEvent) throws MetaException {
      this.txnHandler = this.getTxnHandler();
      this.txnHandler.cleanupRecords(HiveObjectType.DATABASE, dbEvent.getDatabase(), (Table)null, (Iterator)null);
   }

   public void onDropTable(DropTableEvent tableEvent) throws MetaException {
      if (TxnUtils.isAcidTable(tableEvent.getTable())) {
         this.txnHandler = this.getTxnHandler();
         this.txnHandler.cleanupRecords(HiveObjectType.TABLE, (Database)null, tableEvent.getTable(), (Iterator)null);
      }

   }

   public void onDropPartition(DropPartitionEvent partitionEvent) throws MetaException {
      if (TxnUtils.isAcidTable(partitionEvent.getTable())) {
         this.txnHandler = this.getTxnHandler();
         this.txnHandler.cleanupRecords(HiveObjectType.PARTITION, (Database)null, partitionEvent.getTable(), partitionEvent.getPartitionIterator());
      }

   }

   private TxnStore getTxnHandler() {
      boolean hackOn = HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVE_IN_TEST) || HiveConf.getBoolVar(this.hiveConf, ConfVars.HIVE_IN_TEZ_TEST);
      String origTxnMgr = null;
      boolean origConcurrency = false;
      if (hackOn) {
         origTxnMgr = this.hiveConf.getVar(ConfVars.HIVE_TXN_MANAGER);
         origConcurrency = this.hiveConf.getBoolVar(ConfVars.HIVE_SUPPORT_CONCURRENCY);
      }

      this.txnHandler = TxnUtils.getTxnStore(this.hiveConf);
      if (hackOn) {
         this.hiveConf.setVar(ConfVars.HIVE_TXN_MANAGER, origTxnMgr);
         this.hiveConf.setBoolVar(ConfVars.HIVE_SUPPORT_CONCURRENCY, origConcurrency);
      }

      return this.txnHandler;
   }
}
