package org.apache.derby.impl.store.access.heap;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.ModuleFactory;
import org.apache.derby.iapi.services.monitor.ModuleSupportable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.conglomerate.Conglomerate;
import org.apache.derby.iapi.store.access.conglomerate.ConglomerateFactory;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.ContainerHandle;
import org.apache.derby.iapi.store.raw.ContainerKey;
import org.apache.derby.iapi.store.raw.FetchDescriptor;
import org.apache.derby.iapi.store.raw.LockingPolicy;
import org.apache.derby.iapi.store.raw.Page;
import org.apache.derby.iapi.store.raw.PageKey;
import org.apache.derby.iapi.store.raw.RecordHandle;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class HeapConglomerateFactory implements ConglomerateFactory, ModuleControl, ModuleSupportable {
   private static final String IMPLEMENTATIONID = "heap";
   private static final String FORMATUUIDSTRING = "D2976090-D9F5-11d0-B54D-00A024BF8878";
   private UUID formatUUID;

   public Properties defaultProperties() {
      return new Properties();
   }

   public boolean supportsImplementation(String var1) {
      return var1.equals("heap");
   }

   public String primaryImplementationType() {
      return "heap";
   }

   public boolean supportsFormat(UUID var1) {
      return var1.equals(this.formatUUID);
   }

   public UUID primaryFormat() {
      return this.formatUUID;
   }

   public int getConglomerateFactoryId() {
      return 0;
   }

   public Conglomerate createConglomerate(TransactionManager var1, int var2, long var3, DataValueDescriptor[] var5, ColumnOrdering[] var6, int[] var7, Properties var8, int var9) throws StandardException {
      Object var10 = null;
      if ((var9 & 1) != 0 && var1.getAccessManager().isReadOnly()) {
         var10 = new Heap();
      } else if (var1.checkVersion(10, 3, (String)null)) {
         var10 = new Heap();
      } else {
         var10 = new Heap_v10_2();
      }

      ((Heap)var10).create(var1.getRawStoreXact(), var2, var3, var5, var6, var7, var8, ((Heap)var10).getTypeFormatId(), var9);
      return (Conglomerate)var10;
   }

   public Conglomerate readConglomerate(TransactionManager var1, ContainerKey var2) throws StandardException {
      ContainerHandle var3 = null;
      Page var4 = null;
      DataValueDescriptor[] var5 = new DataValueDescriptor[1];

      try {
         var3 = var1.getRawStoreXact().openContainer(var2, (LockingPolicy)null, 0);
         if (var3 == null) {
            throw StandardException.newException("XSAI2.S", new Object[]{var2.getContainerId()});
         }

         var5[0] = new Heap();
         var4 = var3.getPage(1L);
         var4.fetchFromSlot((RecordHandle)null, 0, var5, (FetchDescriptor)null, true);
      } finally {
         if (var4 != null) {
            var4.unlatch();
         }

         if (var3 != null) {
            var3.close();
         }

      }

      return (Conglomerate)var5[0];
   }

   public void insertUndoNotify(AccessFactory var1, Transaction var2, PageKey var3) throws StandardException {
      var2.addPostAbortWork(new HeapPostCommit(var1, var3));
   }

   public boolean canSupport(Properties var1) {
      String var2 = var1.getProperty("derby.access.Conglomerate.type");
      return var2 == null ? false : this.supportsImplementation(var2);
   }

   public void boot(boolean var1, Properties var2) throws StandardException {
      UUIDFactory var3 = getMonitor().getUUIDFactory();
      this.formatUUID = var3.recreateUUID("D2976090-D9F5-11d0-B54D-00A024BF8878");
   }

   public void stop() {
   }

   private static ModuleFactory getMonitor() {
      return Monitor.getMonitor();
   }
}
