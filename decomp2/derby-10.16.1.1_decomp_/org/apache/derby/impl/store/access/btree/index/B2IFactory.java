package org.apache.derby.impl.store.access.btree.index;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.ConglomerateFactory;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.store.access.btree.ControlRow;
import org.apache.derby.shared.common.error.StandardException;

public class B2IFactory implements ConglomerateFactory, ModuleControl {
   private static final String IMPLEMENTATIONID = "BTREE";
   private static final String FORMATUUIDSTRING = "C6CEEEF0-DAD3-11d0-BB01-0060973F0942";
   private UUID formatUUID;

   public Properties defaultProperties() {
      return new Properties();
   }

   public boolean supportsImplementation(String var1) {
      return var1.equals("BTREE");
   }

   public String primaryImplementationType() {
      return "BTREE";
   }

   public boolean supportsFormat(UUID var1) {
      return var1.equals(this.formatUUID);
   }

   public UUID primaryFormat() {
      return this.formatUUID;
   }

   public int getConglomerateFactoryId() {
      return 1;
   }

   public Conglomerate createConglomerate(TransactionManager var1, int var2, long var3, DataValueDescriptor[] var5, ColumnOrdering[] var6, int[] var7, Properties var8, int var9) throws StandardException {
      Object var10 = null;
      if ((var9 & 1) != 0 && var1.getAccessManager().isReadOnly()) {
         var10 = new B2I();
      } else if (var1.checkVersion(10, 4, (String)null)) {
         var10 = new B2I();
      } else if (var1.checkVersion(10, 3, (String)null)) {
         var10 = new B2I_10_3();
      } else {
         var10 = new B2I_v10_2();
      }

      ((B2I)var10).create(var1, var2, var3, var5, var6, var7, var8, var9);
      return (Conglomerate)var10;
   }

   public Conglomerate readConglomerate(TransactionManager var1, ContainerKey var2) throws StandardException {
      Conglomerate var3 = null;
      ContainerHandle var4 = null;
      ControlRow var5 = null;

      try {
         var4 = var1.getRawStoreXact().openContainer(var2, (LockingPolicy)null, 8);
         if (var4 == null) {
            throw StandardException.newException("XSAI2.S", new Object[]{var2.getContainerId()});
         }

         var5 = ControlRow.get(var4, 1L);
         var3 = var5.getConglom(470);
      } finally {
         if (var5 != null) {
            var5.release();
         }

         if (var4 != null) {
            var4.close();
         }

      }

      return var3;
   }

   public void insertUndoNotify(AccessFactory var1, Transaction var2, PageKey var3) throws StandardException {
   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("derby.access.Conglomerate.type");
      return var2 == null ? false : this.supportsImplementation(var2);
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      UUIDFactory var3 = getMonitor().getUUIDFactory();
      this.formatUUID = var3.recreateUUID("C6CEEEF0-DAD3-11d0-BB01-0060973F0942");
   }

   public void stop() {
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
