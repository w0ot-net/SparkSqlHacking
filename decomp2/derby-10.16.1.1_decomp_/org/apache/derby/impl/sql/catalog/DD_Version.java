package org.apache.derby.impl.sql.catalog;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.Properties;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.info.ProductVersionHolder;

public class DD_Version implements Formatable {
   private transient DataDictionaryImpl bootingDictionary;
   int majorVersionNumber;
   private int minorVersionNumber;

   public DD_Version() {
   }

   DD_Version(DataDictionaryImpl var1, int var2) {
      this.majorVersionNumber = var2;
      this.minorVersionNumber = this.getJBMSMinorVersionNumber();
      this.bootingDictionary = var1;
   }

   public String toString() {
      return majorToString(this.majorVersionNumber);
   }

   private static String majorToString(int var0) {
      switch (var0) {
         case 80 -> {
            return "5.0";
         }
         case 90 -> {
            return "5.1";
         }
         case 100 -> {
            return "5.2";
         }
         case 110 -> {
            return "8.1";
         }
         case 120 -> {
            return "10.0";
         }
         case 130 -> {
            return "10.1";
         }
         case 140 -> {
            return "10.2";
         }
         case 150 -> {
            return "10.3";
         }
         case 160 -> {
            return "10.4";
         }
         case 170 -> {
            return "10.5";
         }
         case 180 -> {
            return "10.6";
         }
         case 190 -> {
            return "10.7";
         }
         case 200 -> {
            return "10.8";
         }
         case 210 -> {
            return "10.9";
         }
         case 220 -> {
            return "10.10";
         }
         case 230 -> {
            return "10.11";
         }
         case 240 -> {
            return "10.12";
         }
         case 250 -> {
            return "10.13";
         }
         case 260 -> {
            return "10.14";
         }
         case 270 -> {
            return "10.15";
         }
         case 280 -> {
            return "10.16";
         }
         default -> {
            return null;
         }
      }
   }

   void upgradeIfNeeded(DD_Version var1, TransactionController var2, Properties var3) throws StandardException {
      if (var1.majorVersionNumber > this.majorVersionNumber) {
         throw StandardException.newException("XCL20.S", new Object[]{var1, this});
      } else {
         boolean var4 = false;
         boolean var5 = false;
         boolean var6 = false;
         boolean var7 = this.bootingDictionary.af.isReadOnly();
         if (var1.majorVersionNumber == this.majorVersionNumber) {
            if (var1.minorVersionNumber == this.minorVersionNumber) {
               return;
            }

            var4 = true;
         } else if (isFullUpgrade(var3, var1.toString())) {
            var5 = true;
         } else {
            var6 = true;
         }

         var2.commit();
         if (var5) {
            String var8 = IdUtil.getUserNameFromURLProps(var3);
            this.doFullUpgrade(var2, var1.majorVersionNumber, IdUtil.getUserAuthorizationId(var8));
            this.bootingDictionary.af.createReadMeFiles();
         }

         if (!var4 && !var7) {
            DD_Version var10 = (DD_Version)var2.getProperty("derby.softDataDictionaryVersion");
            int var9 = 0;
            if (var10 != null) {
               var9 = var10.majorVersionNumber;
            }

            if (var9 < this.majorVersionNumber) {
               this.applySafeChanges(var2, var1.majorVersionNumber, var9);
            }
         }

         this.handleMinorRevisionChange(var2, var1, var6);
         var2.commit();
      }
   }

   private void applySafeChanges(TransactionController var1, int var2, int var3) throws StandardException {
      if (var3 <= 140 && var2 <= 140) {
         this.modifySysTableNullability(var1, 11);
         this.modifySysTableNullability(var1, 8);
      }

      var1.setProperty("derby.softDataDictionaryVersion", this, true);
   }

   private void doFullUpgrade(TransactionController var1, int var2, String var3) throws StandardException {
      if (var2 < 120) {
         throw StandardException.newException("XCW00.D", new Object[]{majorToString(var2), this});
      } else {
         this.bootingDictionary.updateMetadataSPSes(var1);
         HashSet var4 = new HashSet();
         if (var2 <= 150) {
            this.bootingDictionary.upgradeMakeCatalog(var1, 19);
         }

         if (var2 <= 130) {
            this.bootingDictionary.upgradeMakeCatalog(var1, 16);
            this.bootingDictionary.upgradeMakeCatalog(var1, 17);
            this.bootingDictionary.upgradeMakeCatalog(var1, 18);
         }

         if (var2 == 120) {
            this.bootingDictionary.create_10_1_system_procedures(var1, var4, this.bootingDictionary.getSystemUtilSchemaDescriptor().getUUID());
         }

         if (var2 <= 170) {
            this.bootingDictionary.create_10_6_system_procedures(var1, var4);
            this.bootingDictionary.upgradeMakeCatalog(var1, 20);
            this.bootingDictionary.upgradeMakeCatalog(var1, 21);
         }

         if (var2 <= 130) {
            this.bootingDictionary.create_10_2_system_procedures(var1, var4, this.bootingDictionary.getSystemUtilSchemaDescriptor().getUUID());
            this.bootingDictionary.updateSystemSchemaAuthorization(var3, var1);
            var4.add("SYSCS_INPLACE_COMPRESS_TABLE");
            var4.add("SYSCS_GET_RUNTIMESTATISTICS");
            var4.add("SYSCS_SET_RUNTIMESTATISTICS");
            var4.add("SYSCS_COMPRESS_TABLE");
            var4.add("SYSCS_SET_STATISTICS_TIMING");
         }

         if (var2 <= 140) {
            this.bootingDictionary.create_10_3_system_procedures(var1, var4);
         }

         if (var2 <= 160) {
            this.bootingDictionary.create_10_5_system_procedures(var1, var4);
         }

         if (var2 > 140 && var2 < 180) {
            this.bootingDictionary.upgradeCLOBGETSUBSTRING_10_6(var1);
         }

         if (var2 > 130 && var2 < 180) {
            this.bootingDictionary.upgradeSYSROUTINEPERMS_10_6(var1);
         }

         if (var2 <= 200) {
            this.bootingDictionary.create_10_9_system_procedures(var1, var4);
            this.bootingDictionary.upgradeMakeCatalog(var1, 22);
            this.bootingDictionary.upgradeJarStorage(var1);
         }

         if (var2 <= 210) {
            this.bootingDictionary.create_10_10_system_procedures(var1, var4);
         }

         if (var2 <= 220) {
            this.bootingDictionary.upgrade_addColumns(this.bootingDictionary.getNonCoreTIByNumber(13).getCatalogRowFactory(), new int[]{18}, var1);
            this.bootingDictionary.create_10_11_system_procedures(var1, var4);
            this.bootingDictionary.createIdentitySequences(var1);
         }

         if (var2 <= 230) {
            this.bootingDictionary.create_10_12_system_procedures(var1, var4);
         }

         if (var2 <= 240) {
            this.bootingDictionary.create_10_13_system_procedures(var1, var4);
         }

         if (var2 <= 250) {
            this.bootingDictionary.upgrade_SYSCOLUMNS_AUTOINCCYCLE(var1);
         }

         this.bootingDictionary.grantPublicAccessToSystemRoutines(var4, var1, var3);
      }
   }

   private void handleMinorRevisionChange(TransactionController var1, DD_Version var2, boolean var3) throws StandardException {
      boolean var4 = this.bootingDictionary.af.isReadOnly();
      if (!var4) {
         this.bootingDictionary.clearSPSPlans();
         if (var2.majorVersionNumber >= 170) {
            this.bootingDictionary.updateMetadataSPSes(var1);
         }

         if (var3) {
            var2.minorVersionNumber = 1;
         } else {
            var2.majorVersionNumber = this.majorVersionNumber;
            var2.minorVersionNumber = this.minorVersionNumber;
         }

         var1.setProperty("DataDictionaryVersion", var2, true);
      } else {
         this.bootingDictionary.setReadOnlyUpgrade();
      }

      this.bootingDictionary.clearCaches();
   }

   protected void dropSystemCatalogDescription(TransactionController var1, TableDescriptor var2) throws StandardException {
      this.bootingDictionary.dropAllColumnDescriptors(var2.getUUID(), var1);
      this.bootingDictionary.dropAllConglomerateDescriptors(var2, var1);
      this.bootingDictionary.dropTableDescriptor(var2, var2.getSchemaDescriptor(), var1);
      this.bootingDictionary.clearCaches();
   }

   protected void dropSystemCatalog(TransactionController var1, CatalogRowFactory var2) throws StandardException {
      SchemaDescriptor var3 = this.bootingDictionary.getSystemSchemaDescriptor();
      TableDescriptor var4 = this.bootingDictionary.getTableDescriptor(var2.getCatalogName(), var3, var1);
      ConglomerateDescriptor[] var5 = var4.getConglomerateDescriptors();

      for(int var6 = 0; var6 < var5.length; ++var6) {
         var1.dropConglomerate(var5[var6].getConglomerateNumber());
      }

      this.dropSystemCatalogDescription(var1, var4);
   }

   public int getTypeFormatId() {
      return this.majorVersionNumber == 90 ? 402 : 401;
   }

   public final void readExternal(ObjectInput var1) throws IOException {
      this.majorVersionNumber = var1.readInt();
      this.minorVersionNumber = var1.readInt();
   }

   public final void writeExternal(ObjectOutput var1) throws IOException {
      var1.writeInt(this.majorVersionNumber);
      var1.writeInt(this.minorVersionNumber);
   }

   private int getJBMSMinorVersionNumber() {
      ProductVersionHolder var1 = DataDictionaryImpl.getMonitor().getEngineVersion();
      return var1.getMinorVersion() * 100 + var1.getMaintVersion() + (var1.isBeta() ? 0 : 1) + 2;
   }

   private void modifySysTableNullability(TransactionController var1, int var2) throws StandardException {
      TabInfoImpl var3 = this.bootingDictionary.getNonCoreTIByNumber(var2);
      CatalogRowFactory var4 = var3.getCatalogRowFactory();
      if (var2 == 11) {
         this.bootingDictionary.upgradeFixSystemColumnDefinition(var4, 8, var1);
      } else if (var2 == 8) {
         this.bootingDictionary.upgradeFixSystemColumnDefinition(var4, 4, var1);
      }

   }

   boolean checkVersion(int var1, String var2) throws StandardException {
      if (this.majorVersionNumber < var1) {
         if (var2 != null) {
            throw StandardException.newException("XCL47.S", new Object[]{var2, majorToString(this.majorVersionNumber), majorToString(var1)});
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   private static boolean isFullUpgrade(Properties var0, String var1) throws StandardException {
      return Monitor.isFullUpgrade(var0, var1);
   }
}
