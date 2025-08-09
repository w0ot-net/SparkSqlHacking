package org.datanucleus.store.connection;

import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.Transaction;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public abstract class AbstractConnectionFactory implements ConnectionFactory {
   protected StoreManager storeMgr;
   protected String resourceType;
   protected String resourceName;
   public static final String RESOURCE_NAME_TX = "tx";
   public static final String RESOURCE_NAME_NONTX = "nontx";

   public AbstractConnectionFactory(StoreManager storeMgr, String resourceName) {
      this.storeMgr = storeMgr;
      this.resourceName = resourceName;
      if (resourceName == null) {
         NucleusLogger.CONNECTION.warn("Attempt to create ConnectionFactory with NULL resourceName for connection factory with storeManager=" + StringUtils.toJVMIDString(storeMgr));
      } else if (resourceName.equals("tx")) {
         this.resourceType = storeMgr.getStringProperty("datanucleus.connection.resourceType");
      } else {
         this.resourceType = storeMgr.getStringProperty("datanucleus.connection2.resourceType");
      }

   }

   public String getResourceName() {
      return this.resourceName;
   }

   public String getResourceType() {
      return this.resourceType;
   }

   public ManagedConnection getConnection(ExecutionContext ec, Transaction txn, Map options) {
      ManagedConnection mconn = this.storeMgr.getConnectionManager().allocateConnection(this, ec, txn, options);
      ((AbstractManagedConnection)mconn).incrementUseCount();
      return mconn;
   }

   public void close() {
   }

   public String toString() {
      return "ConnectionFactory:" + this.resourceName + "[" + StringUtils.toJVMIDString(this) + "]";
   }
}
