package org.apache.derby.impl.sql.execute;

import java.util.Enumeration;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.StatementUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.KeyHasher;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowSource;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.shared.common.error.StandardException;

public class ReferencedKeyRIChecker extends GenericRIChecker {
   private ScanController refKeyIndexScan = null;
   private final DataValueDescriptor[] refKey;
   private BackingStoreHashtable deletedKeys;

   ReferencedKeyRIChecker(LanguageConnectionContext var1, TransactionController var2, FKInfo var3) throws StandardException {
      super(var1, var2, var3);
      this.refKey = new DataValueDescriptor[this.numColumns];
      this.deletedKeys = null;
   }

   void doCheck(Activation var1, ExecRow var2, boolean var3, int var4) throws StandardException {
      if (!this.isAnyFieldNull(var2)) {
         if (this.fkInfo.refConstraintIsDeferrable && this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(var1), this.fkInfo.refConstraintID)) {
            if (var3) {
               this.rememberKey(var2);
               return;
            }

            if (this.isDuplicated(var2, var4)) {
               return;
            }
         }

         for(int var6 = 0; var6 < this.fkInfo.fkConglomNumbers.length; ++var6) {
            if (!var3 || this.fkInfo.raRules[var6] == 1) {
               ScanController var5 = this.getScanController(this.fkInfo.fkConglomNumbers[var6], this.fkScocis[var6], this.fkDcocis[var6], var2);
               if (var5.next()) {
                  this.close();
                  UUID var7 = this.fkInfo.fkIds[var6];
                  if (!this.fkInfo.deferrable[var6] || this.fkInfo.raRules[var6] == 1 || !this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(var1), var7)) {
                     StandardException var8 = StandardException.newException("23503", new Object[]{this.fkInfo.fkConstraintNames[var6], this.fkInfo.tableName, StatementUtil.typeName(this.fkInfo.stmtType), RowUtil.toString(var2, this.fkInfo.colArray)});
                     throw var8;
                  }

                  this.deferredRowsHashTable = DeferredConstraintsMemory.rememberFKViolation(this.lcc, this.deferredRowsHashTable, this.fkInfo.fkIds[var6], this.indexQualifierRow.getRowArray(), this.fkInfo.schemaName, this.fkInfo.tableName);
               }

               var5.next();
            }
         }

      }
   }

   private void rememberKey(ExecRow var1) throws StandardException {
      if (this.deletedKeys == null) {
         this.identityMap = new int[this.numColumns];

         for(int var2 = 0; var2 < this.numColumns; this.identityMap[var2] = var2++) {
         }

         this.deletedKeys = new BackingStoreHashtable(this.tc, (RowSource)null, this.identityMap, true, -1L, -1L, -1, -1.0F, false, false);
      }

      DataValueDescriptor[] var5 = var1.getRowArray();

      for(int var3 = 0; var3 < this.numColumns; ++var3) {
         this.refKey[var3] = var5[this.fkInfo.colArray[var3] - 1];
      }

      Object var6 = KeyHasher.buildHashKey(this.refKey, this.identityMap);
      DataValueDescriptor[] var4 = (DataValueDescriptor[])this.deletedKeys.remove(var6);
      if (var4 == null) {
         var4 = new DataValueDescriptor[this.numColumns + 1];
         System.arraycopy(this.refKey, 0, var4, 0, this.numColumns);
         var4[this.numColumns] = new SQLLongint(1L);
      } else {
         var4[this.numColumns] = new SQLLongint(((SQLLongint)var4[this.numColumns]).getLong() + 1L);
      }

      this.deletedKeys.putRow(false, var4, (RowLocation)null);
   }

   public void postCheck() throws StandardException {
      if (this.fkInfo.refConstraintIsDeferrable) {
         int var1 = -1;

         for(int var2 = 0; var2 < this.fkInfo.fkConglomNumbers.length; ++var2) {
            if (this.fkInfo.raRules[var2] == 1) {
               var1 = var2;
               break;
            }
         }

         if (var1 != -1) {
            if (this.deletedKeys != null) {
               Enumeration var9 = this.deletedKeys.elements();

               while(var9.hasMoreElements()) {
                  DataValueDescriptor[] var3 = (DataValueDescriptor[])var9.nextElement();
                  DataValueDescriptor[] var4 = new DataValueDescriptor[var3.length - 1];
                  System.arraycopy(var3, 0, var4, 0, var4.length);
                  long var5 = var3[var3.length - 1].getLong() + 1L;
                  if (!this.isDuplicated(var4, var5)) {
                     int[] var7 = new int[this.numColumns];

                     for(int var8 = 0; var8 < this.numColumns; ++var8) {
                        var7[var8] = var8 + 1;
                     }

                     StandardException var10 = StandardException.newException("23503", new Object[]{this.fkInfo.fkConstraintNames[var1], this.fkInfo.tableName, StatementUtil.typeName(this.fkInfo.stmtType), RowUtil.toString((Object[])var3, var7)});
                     throw var10;
                  }
               }
            }

         }
      }
   }

   private boolean isDuplicated(ExecRow var1, int var2) throws StandardException {
      DataValueDescriptor[] var3 = var1.getRowArray();

      for(int var4 = 0; var4 < this.numColumns; ++var4) {
         this.refKey[var4] = var3[this.fkInfo.colArray[var4] - 1];
      }

      return this.isDuplicated(this.refKey, (long)var2);
   }

   private boolean isDuplicated(DataValueDescriptor[] var1, long var2) throws StandardException {
      if (this.refKeyIndexScan == null) {
         this.refKeyIndexScan = this.tc.openScan(this.fkInfo.refConglomNumber, false, 0, 6, 4, (FormatableBitSet)null, var1, 1, (Qualifier[][])null, var1, -1);
      } else {
         this.refKeyIndexScan.reopenScan(var1, 1, (Qualifier[][])null, var1, -1);
      }

      boolean var4;
      for(var4 = this.refKeyIndexScan.next(); --var2 > 0L && var4; var4 = this.refKeyIndexScan.next()) {
      }

      return var2 == 0L && var4;
   }

   void close() throws StandardException {
      if (this.refKeyIndexScan != null) {
         this.refKeyIndexScan.close();
         this.refKeyIndexScan = null;
      }

      if (this.deletedKeys != null) {
         this.deletedKeys.close();
         this.deletedKeys = null;
      }

      this.identityMap = null;
      super.close();
   }
}
