package org.apache.hadoop.hive.metastore.hbase;

import co.cask.tephra.TransactionContext;
import co.cask.tephra.TransactionFailureException;
import co.cask.tephra.TransactionManager;
import co.cask.tephra.TransactionSystemClient;
import co.cask.tephra.distributed.ThreadLocalClientProvider;
import co.cask.tephra.distributed.TransactionServiceClient;
import co.cask.tephra.hbase10.TransactionAwareHTable;
import co.cask.tephra.hbase10.coprocessor.TransactionProcessor;
import co.cask.tephra.inmemory.InMemoryTxSystemClient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.twill.discovery.InMemoryDiscoveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TephraHBaseConnection extends VanillaHBaseConnection {
   private static final Logger LOG = LoggerFactory.getLogger(TephraHBaseConnection.class.getName());
   private Map txnTables = new HashMap();
   private TransactionContext txn;
   private TransactionSystemClient txnClient;

   TephraHBaseConnection() {
   }

   public void connect() throws IOException {
      super.connect();
      if (HiveConf.getBoolVar(this.conf, ConfVars.HIVE_IN_TEST)) {
         LOG.debug("Using an in memory client transaction system for testing");
         TransactionManager txnMgr = new TransactionManager(this.conf);

         try {
            this.startAndWait(txnMgr);
         } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("txnMgr start failed", e);
         }

         this.txnClient = new InMemoryTxSystemClient(txnMgr);
      } else {
         LOG.debug("Using real client transaction system for production");
         this.txnClient = new TransactionServiceClient(this.conf, new ThreadLocalClientProvider(this.conf, new InMemoryDiscoveryService()));
      }

      for(String tableName : HBaseReadWrite.tableNames) {
         this.txnTables.put(tableName, new TransactionAwareHTable(super.getHBaseTable(tableName, true)));
      }

      this.txn = new TransactionContext(this.txnClient, this.txnTables.values());
   }

   public void beginTransaction() throws IOException {
      try {
         this.txn.start();
         LOG.debug("Started txn in tephra");
      } catch (TransactionFailureException e) {
         throw new IOException(e);
      }
   }

   public void commitTransaction() throws IOException {
      try {
         this.txn.finish();
         LOG.debug("Finished txn in tephra");
      } catch (TransactionFailureException e) {
         throw new IOException(e);
      }
   }

   public void rollbackTransaction() throws IOException {
      try {
         this.txn.abort();
         LOG.debug("Aborted txn in tephra");
      } catch (TransactionFailureException e) {
         throw new IOException(e);
      }
   }

   public void flush(HTableInterface htab) throws IOException {
   }

   protected HTableDescriptor buildDescriptor(String tableName, List columnFamilies) throws IOException {
      HTableDescriptor tableDesc = super.buildDescriptor(tableName, columnFamilies);
      tableDesc.addCoprocessor(TransactionProcessor.class.getName());
      return tableDesc;
   }

   public HTableInterface getHBaseTable(String tableName, boolean force) throws IOException {
      return (TransactionAwareHTable)this.txnTables.get(tableName);
   }

   private void startAndWait(TransactionManager txnMgr) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
      try {
         Method startAndWaitMethod = txnMgr.getClass().getMethod("startAndWait");
         startAndWaitMethod.invoke(txnMgr);
      } catch (NoSuchMethodException var5) {
         Method startAsyncMethod = txnMgr.getClass().getMethod("startAsync");
         Method awaitRunningMethod = txnMgr.getClass().getMethod("awaitRunning");
         startAsyncMethod.invoke(txnMgr);
         awaitRunningMethod.invoke(txnMgr);
      }

   }
}
