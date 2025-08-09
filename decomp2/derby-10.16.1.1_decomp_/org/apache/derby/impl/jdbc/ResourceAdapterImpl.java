package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import javax.transaction.xa.XAException;
import org.apache.derby.iapi.jdbc.ResourceAdapter;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.xa.XAResourceManager;
import org.apache.derby.iapi.store.access.xa.XAXactId;
import org.apache.derby.shared.common.error.StandardException;

public class ResourceAdapterImpl implements ResourceAdapter, ModuleControl {
   private boolean active;
   private XAResourceManager rm;
   private Hashtable connectionTable;

   public void boot(boolean var1, Properties var2) throws StandardException {
      this.connectionTable = new Hashtable();
      AccessFactory var3 = (AccessFactory)findServiceModule(this, "org.apache.derby.iapi.store.access.AccessFactory");
      this.rm = (XAResourceManager)var3.getXAResourceManager();
      this.active = true;
   }

   public void stop() {
      this.active = false;
      Enumeration var1 = this.connectionTable.elements();

      while(var1.hasMoreElements()) {
         XATransactionState var2 = (XATransactionState)var1.nextElement();

         try {
            var2.conn.close();
         } catch (SQLException var4) {
         }
      }

      this.active = false;
   }

   public boolean isActive() {
      return this.active;
   }

   public synchronized Object findConnection(XAXactId var1) {
      return this.connectionTable.get(var1);
   }

   public synchronized boolean addConnection(XAXactId var1, Object var2) {
      if (this.connectionTable.get(var1) != null) {
         return false;
      } else {
         this.connectionTable.put(var1, (XATransactionState)var2);
         return true;
      }
   }

   public synchronized Object removeConnection(XAXactId var1) {
      return this.connectionTable.remove(var1);
   }

   public void cancelXATransaction(XAXactId var1, String var2) throws XAException {
      XATransactionState var3 = (XATransactionState)this.findConnection(var1);
      if (var3 != null) {
         var3.cancel(var2);
      }

   }

   public XAResourceManager getXAResourceManager() {
      return this.rm;
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
