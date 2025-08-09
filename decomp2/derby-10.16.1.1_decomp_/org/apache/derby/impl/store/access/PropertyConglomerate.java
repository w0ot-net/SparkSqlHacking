package org.apache.derby.impl.store.access;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.FormatableHashtable;
import org.apache.derby.iapi.services.locks.CompatibilitySpace;
import org.apache.derby.iapi.services.locks.LockFactory;
import org.apache.derby.iapi.services.locks.LockOwner;
import org.apache.derby.iapi.services.locks.ShExQual;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyFactory;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.RawStoreFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

class PropertyConglomerate {
   protected long propertiesConglomId;
   protected Properties serviceProperties;
   private LockFactory lf;
   private Dictionary cachedSet;
   private CacheLock cachedLock;
   private PropertyFactory pf;

   PropertyConglomerate(TransactionController var1, boolean var2, Properties var3, PropertyFactory var4) throws StandardException {
      this.pf = var4;
      if (!var2) {
         String var5 = var3.getProperty("derby.storage.propertiesId");
         if (var5 == null) {
            var2 = true;
         } else {
            try {
               this.propertiesConglomId = Long.valueOf(var5);
            } catch (NumberFormatException var7) {
               throw Monitor.exceptionStartingModule(var7);
            }
         }
      }

      if (var2) {
         DataValueDescriptor[] var8 = this.makeNewTemplate();
         Properties var6 = new Properties();
         var6.put("derby.storage.pageSize", "2048");
         var6.put("derby.storage.pageReservedSpace", "0");
         this.propertiesConglomId = var1.createConglomerate("heap", var8, (ColumnOrdering[])null, (int[])null, var6, 0);
         var3.put("derby.storage.propertiesId", Long.toString(this.propertiesConglomId));
      }

      this.serviceProperties = var3;
      this.lf = ((RAMTransaction)var1).getAccessManager().getLockFactory();
      this.cachedLock = new CacheLock(this);
      PC_XenaVersion var9 = new PC_XenaVersion();
      if (var2) {
         this.setProperty(var1, "PropertyConglomerateVersion", var9, true);
      } else {
         var9.upgradeIfNeeded(var1, this, var3);
      }

      this.getCachedDbProperties(var1);
   }

   private DataValueDescriptor[] makeNewTemplate(String var1, Serializable var2) {
      DataValueDescriptor[] var3 = new DataValueDescriptor[]{new UTF(var1), new UserType(var2)};
      return var3;
   }

   private DataValueDescriptor[] makeNewTemplate() {
      DataValueDescriptor[] var1 = new DataValueDescriptor[]{new UTF(), new UserType()};
      return var1;
   }

   private ScanController openScan(TransactionController var1, String var2, int var3) throws StandardException {
      Qualifier[][] var4 = null;
      if (var2 != null) {
         var4 = new Qualifier[][]{new Qualifier[1]};
         var4[0][0] = new UTFQualifier(0, var2);
      }

      ScanController var5 = var1.openScan(this.propertiesConglomId, false, var3, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, var4, (DataValueDescriptor[])null, 0);
      return var5;
   }

   void setPropertyDefault(TransactionController var1, String var2, Serializable var3) throws StandardException {
      this.lockProperties(var1);
      Object var4 = null;
      Serializable var9;
      if (this.propertyDefaultIsVisible(var1, var2)) {
         var9 = this.validateApplyAndMap(var1, var2, var3, false);
      } else {
         synchronized(this) {
            Hashtable var6 = new Hashtable();
            this.getProperties(var1, var6, false, true);
            this.validate(var2, var3, var6);
            var9 = this.map(var2, var3, var6);
         }
      }

      this.savePropertyDefault(var1, var2, var9);
   }

   boolean propertyDefaultIsVisible(TransactionController var1, String var2) throws StandardException {
      this.lockProperties(var1);
      return this.readProperty(var1, var2) == null;
   }

   void saveProperty(TransactionController var1, String var2, Serializable var3) throws StandardException {
      if (!this.saveServiceProperty(var2, var3)) {
         ScanController var4 = this.openScan(var1, var2, 4);
         DataValueDescriptor[] var5 = this.makeNewTemplate();
         if (var4.fetchNext(var5)) {
            if (var3 == null) {
               var4.delete();
            } else {
               var5[1] = new UserType(var3);
               var4.replace(var5, (FormatableBitSet)null);
            }

            var4.close();
         } else {
            var4.close();
            Object var7 = null;
            if (var3 != null) {
               var5 = this.makeNewTemplate(var2, var3);
               ConglomerateController var6 = var1.openConglomerate(this.propertiesConglomId, false, 4, 7, 5);
               var6.insert(var5);
               var6.close();
            }
         }

      }
   }

   private boolean saveServiceProperty(String var1, Serializable var2) {
      if (PropertyUtil.isServiceProperty(var1)) {
         if (var2 != null) {
            this.serviceProperties.put(var1, var2);
         } else {
            this.serviceProperties.remove(var1);
         }

         return true;
      } else {
         return false;
      }
   }

   void savePropertyDefault(TransactionController var1, String var2, Serializable var3) throws StandardException {
      if (!this.saveServiceProperty(var2, var3)) {
         FormatableHashtable var4 = (FormatableHashtable)this.readProperty(var1, "derby.defaultPropertyName");
         if (var4 == null) {
            var4 = new FormatableHashtable();
         }

         if (var3 == null) {
            var4.remove(var2);
         } else {
            var4.put(var2, var3);
         }

         if (var4.size() == 0) {
            var4 = null;
         }

         this.saveProperty(var1, "derby.defaultPropertyName", var4);
      }
   }

   private Serializable validateApplyAndMap(TransactionController var1, String var2, Serializable var3, boolean var4) throws StandardException {
      Hashtable var5 = new Hashtable();
      this.getProperties(var1, var5, false, false);
      Serializable var6 = this.pf.doValidateApplyAndMap(var1, var2, var3, var5, var4);
      if (var2.equals("logDevice")) {
         throw StandardException.newException("XSRS8.S", new Object[0]);
      } else {
         return var6 == null ? var3 : var6;
      }
   }

   private Serializable map(String var1, Serializable var2, Dictionary var3) throws StandardException {
      return this.pf.doMap(var1, var2, var3);
   }

   private void validate(String var1, Serializable var2, Dictionary var3) throws StandardException {
      this.pf.validateSingleProperty(var1, var2, var3);
   }

   private boolean bootPasswordChange(TransactionController var1, String var2, Serializable var3) throws StandardException {
      if (var2.equals("bootPassword")) {
         AccessFactory var4 = ((TransactionManager)var1).getAccessManager();
         RawStoreFactory var5 = (RawStoreFactory)findServiceModule(var4, "org.apache.derby.iapi.store.raw.RawStoreFactory");
         this.serviceProperties.remove("bootPassword");
         var3 = var5.changeBootPassword(this.serviceProperties, var3);
         this.serviceProperties.put("encryptedBootPassword", var3);
         return true;
      } else {
         return false;
      }
   }

   void setProperty(TransactionController var1, String var2, Serializable var3, boolean var4) throws StandardException {
      this.lockProperties(var1);
      Serializable var5 = var3;
      if (var3 == null) {
         var5 = this.getPropertyDefault(var1, var2);
      }

      Serializable var6 = this.validateApplyAndMap(var1, var2, var5, var4);
      if (!this.bootPasswordChange(var1, var2, var3)) {
         if (var3 == null) {
            this.saveProperty(var1, var2, (Serializable)null);
         } else {
            this.saveProperty(var1, var2, var6);
         }

      }
   }

   private Serializable readProperty(TransactionController var1, String var2) throws StandardException {
      ScanController var3 = this.openScan(var1, var2, 0);
      DataValueDescriptor[] var4 = this.makeNewTemplate();
      boolean var5 = var3.fetchNext(var4);
      var3.close();
      return !var5 ? null : (Serializable)((UserType)var4[1]).getObject();
   }

   private Serializable getCachedProperty(TransactionController var1, String var2) throws StandardException {
      Dictionary var3 = this.getCachedDbProperties(var1);
      return var3.get(var2) != null ? (Serializable)var3.get(var2) : this.getCachedPropertyDefault(var1, var2, var3);
   }

   private Serializable getCachedPropertyDefault(TransactionController var1, String var2, Dictionary var3) throws StandardException {
      if (var3 == null) {
         var3 = this.getCachedDbProperties(var1);
      }

      Dictionary var4 = (Dictionary)var3.get("derby.defaultPropertyName");
      return var4 == null ? null : (Serializable)var4.get(var2);
   }

   Serializable getProperty(TransactionController var1, String var2) throws StandardException {
      if (PropertyUtil.isServiceProperty(var2)) {
         return this.serviceProperties.getProperty(var2);
      } else if (this.iHoldTheUpdateLock(var1)) {
         Serializable var3 = this.readProperty(var1, var2);
         return var3 != null ? var3 : this.getPropertyDefault(var1, var2);
      } else {
         return this.getCachedProperty(var1, var2);
      }
   }

   Serializable getPropertyDefault(TransactionController var1, String var2) throws StandardException {
      if (this.iHoldTheUpdateLock(var1)) {
         Dictionary var3 = (Dictionary)this.readProperty(var1, "derby.defaultPropertyName");
         return var3 == null ? null : (Serializable)var3.get(var2);
      } else {
         return this.getCachedPropertyDefault(var1, var2, (Dictionary)null);
      }
   }

   private Dictionary copyValues(Dictionary var1, Dictionary var2, boolean var3) {
      if (var2 == null) {
         return var1;
      } else {
         Enumeration var4 = var2.keys();

         while(var4.hasMoreElements()) {
            Object var5 = var4.nextElement();
            Object var6 = var2.get(var5);
            if (var6 instanceof String || !var3) {
               var1.put(var5, var6);
            }
         }

         return var1;
      }
   }

   Properties getProperties(TransactionController var1) throws StandardException {
      Properties var2 = new Properties();
      this.getProperties(var1, var2, true, false);
      return var2;
   }

   public void getProperties(TransactionController var1, Dictionary var2, boolean var3, boolean var4) throws StandardException {
      Dictionary var5;
      if (this.iHoldTheUpdateLock(var1)) {
         var5 = this.readDbProperties(var1);
      } else {
         var5 = this.getCachedDbProperties(var1);
      }

      FormatableHashtable var6 = (FormatableHashtable)var5.get("derby.defaultPropertyName");
      this.copyValues(var2, var6, var3);
      if (!var4) {
         this.copyValues(var2, var5, var3);
      }

   }

   void resetCache() {
      this.cachedSet = null;
   }

   private Dictionary readDbProperties(TransactionController var1) throws StandardException {
      Hashtable var2 = new Hashtable();
      ScanController var3 = this.openScan(var1, (String)null, 0);
      DataValueDescriptor[] var4 = this.makeNewTemplate();

      while(var3.fetchNext(var4)) {
         Object var5 = ((UserType)var4[0]).getObject();
         Object var6 = ((UserType)var4[1]).getObject();
         ((Dictionary)var2).put((String)var5, var6);
      }

      var3.close();
      String[] var8 = PropertyUtil.getServicePropertyList();

      for(int var9 = 0; var9 < var8.length; ++var9) {
         String var7 = this.serviceProperties.getProperty(var8[var9]);
         if (var7 != null) {
            ((Dictionary)var2).put(var8[var9], var7);
         }
      }

      return var2;
   }

   private Dictionary getCachedDbProperties(TransactionController var1) throws StandardException {
      Dictionary var2 = this.cachedSet;
      if (var2 == null) {
         var2 = this.readDbProperties(var1);
         this.cachedSet = var2;
      }

      return var2;
   }

   void lockProperties(TransactionController var1) throws StandardException {
      CompatibilitySpace var2 = var1.getLockSpace();
      LockOwner var3 = var2.getOwner();
      this.lf.lockObject(var2, var3, this.cachedLock, ShExQual.EX, -2);
   }

   private boolean iHoldTheUpdateLock(TransactionController var1) throws StandardException {
      CompatibilitySpace var2 = var1.getLockSpace();
      LockOwner var3 = var2.getOwner();
      return this.lf.isLockHeld(var2, var3, this.cachedLock, ShExQual.EX);
   }

   private static Object findServiceModule(Object var0, String var1) throws StandardException {
      return Monitor.findServiceModule(var0, var1);
   }
}
