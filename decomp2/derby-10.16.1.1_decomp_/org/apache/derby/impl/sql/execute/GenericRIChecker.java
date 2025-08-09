package org.apache.derby.impl.sql.execute;

import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public abstract class GenericRIChecker {
   protected FKInfo fkInfo;
   protected DynamicCompiledOpenConglomInfo[] fkDcocis;
   protected StaticCompiledOpenConglomInfo[] fkScocis;
   protected DynamicCompiledOpenConglomInfo refDcoci;
   protected StaticCompiledOpenConglomInfo refScoci;
   protected TransactionController tc;
   protected LanguageConnectionContext lcc;
   protected BackingStoreHashtable deferredRowsHashTable;
   private final Hashtable scanControllers;
   protected final int numColumns;
   protected int[] identityMap;
   final IndexRow indexQualifierRow;

   GenericRIChecker(LanguageConnectionContext var1, TransactionController var2, FKInfo var3) throws StandardException {
      this.lcc = var1;
      this.fkInfo = var3;
      this.tc = var2;
      this.scanControllers = new Hashtable();
      this.numColumns = this.fkInfo.colArray.length;
      this.indexQualifierRow = new IndexRow(this.numColumns);
      this.fkDcocis = new DynamicCompiledOpenConglomInfo[this.fkInfo.fkConglomNumbers.length];
      this.fkScocis = new StaticCompiledOpenConglomInfo[this.fkInfo.fkConglomNumbers.length];

      for(int var4 = 0; var4 < this.fkInfo.fkConglomNumbers.length; ++var4) {
         this.fkDcocis[var4] = var2.getDynamicCompiledConglomInfo(this.fkInfo.fkConglomNumbers[var4]);
         this.fkScocis[var4] = var2.getStaticCompiledConglomInfo(this.fkInfo.fkConglomNumbers[var4]);
      }

      this.refDcoci = var2.getDynamicCompiledConglomInfo(this.fkInfo.refConglomNumber);
      this.refScoci = var2.getStaticCompiledConglomInfo(this.fkInfo.refConglomNumber);
   }

   abstract void doCheck(Activation var1, ExecRow var2, boolean var3, int var4) throws StandardException;

   protected ScanController getScanController(long var1, StaticCompiledOpenConglomInfo var3, DynamicCompiledOpenConglomInfo var4, ExecRow var5) throws StandardException {
      int var6 = this.getRICheckIsolationLevel();
      Long var8 = var1;
      ScanController var7;
      if ((var7 = (ScanController)this.scanControllers.get(var8)) == null) {
         this.setupQualifierRow(var5);
         var7 = this.tc.openCompiledScan(false, 0, 6, var6, (FormatableBitSet)null, this.indexQualifierRow.getRowArray(), 1, (Qualifier[][])null, this.indexQualifierRow.getRowArray(), -1, var3, var4);
         this.scanControllers.put(var8, var7);
      } else {
         this.setupQualifierRow(var5);
         var7.reopenScan(this.indexQualifierRow.getRowArray(), 1, (Qualifier[][])null, this.indexQualifierRow.getRowArray(), -1);
      }

      return var7;
   }

   private void setupQualifierRow(ExecRow var1) {
      DataValueDescriptor[] var2 = this.indexQualifierRow.getRowArray();
      DataValueDescriptor[] var3 = var1.getRowArray();

      for(int var4 = 0; var4 < this.numColumns; ++var4) {
         var2[var4] = var3[this.fkInfo.colArray[var4] - 1];
      }

   }

   boolean isAnyFieldNull(ExecRow var1) {
      DataValueDescriptor[] var2 = var1.getRowArray();

      for(int var3 = 0; var3 < this.numColumns; ++var3) {
         DataValueDescriptor var4 = var2[this.fkInfo.colArray[var3] - 1];
         if (var4.isNull()) {
            return true;
         }
      }

      return false;
   }

   void close() throws StandardException {
      Enumeration var1 = this.scanControllers.elements();

      while(var1.hasMoreElements()) {
         ScanController var2 = (ScanController)var1.nextElement();
         var2.close();
      }

      this.scanControllers.clear();
   }

   int getRICheckIsolationLevel() {
      return 3;
   }
}
