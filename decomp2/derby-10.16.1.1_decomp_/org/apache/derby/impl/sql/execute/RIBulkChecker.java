package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

public class RIBulkChecker {
   private static final int EQUAL = 0;
   private static final int GREATER_THAN = 1;
   private static final int LESS_THAN = -1;
   private final long fkCID;
   private final long pkCID;
   private final String schemaName;
   private final String tableName;
   private final UUID constraintId;
   private BackingStoreHashtable deferredRowsHashTable;
   private final LanguageConnectionContext lcc;
   private final boolean deferred;
   private GroupFetchScanController referencedKeyScan;
   private DataValueDescriptor[][] referencedKeyRowArray;
   private GroupFetchScanController foreignKeyScan;
   private DataValueDescriptor[][] foreignKeyRowArray;
   private ConglomerateController unreferencedCC;
   private int failedCounter;
   private boolean quitOnFirstFailure;
   private int numColumns;
   private int currRefRowIndex;
   private int currFKRowIndex;
   private int lastRefRowIndex;
   private int lastFKRowIndex;
   private ExecRow firstRowToFail;

   public RIBulkChecker(Activation var1, GroupFetchScanController var2, GroupFetchScanController var3, ExecRow var4, boolean var5, ConglomerateController var6, ExecRow var7, String var8, String var9, UUID var10, boolean var11, long var12, long var14) throws StandardException {
      this.referencedKeyScan = var2;
      this.foreignKeyScan = var3;
      this.quitOnFirstFailure = var5;
      this.unreferencedCC = var6;
      this.firstRowToFail = var7;
      this.constraintId = var10;
      this.fkCID = var12;
      this.pkCID = var14;
      this.schemaName = var8;
      this.tableName = var9;
      this.foreignKeyRowArray = new DataValueDescriptor[16][];
      this.foreignKeyRowArray[0] = var4.getRowArrayClone();
      this.referencedKeyRowArray = new DataValueDescriptor[16][];
      this.referencedKeyRowArray[0] = var4.getRowArrayClone();
      this.failedCounter = 0;
      this.numColumns = var4.getRowArray().length - 1;
      this.currFKRowIndex = -1;
      this.currRefRowIndex = -1;
      this.lcc = var1.getLanguageConnectionContext();
      this.deferred = var11 && this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(var1), var10);
   }

   public int doCheck() throws StandardException {
      DataValueDescriptor[] var2 = this.getNextRef();

      DataValueDescriptor[] var1;
      while((var1 = this.getNextFK()) != null) {
         if (!this.anyNull(var1) && var2 == null) {
            do {
               this.failure(var1);
               if (this.quitOnFirstFailure) {
                  return this.failedCounter;
               }
            } while((var1 = this.getNextFK()) != null);

            return this.failedCounter;
         }

         int var3;
         while((var3 = this.greaterThan(var1, var2)) == 1) {
            if ((var2 = this.getNextRef()) == null) {
               do {
                  this.failure(var1);
                  if (this.quitOnFirstFailure) {
                     return this.failedCounter;
                  }
               } while((var1 = this.getNextFK()) != null);

               return this.failedCounter;
            }
         }

         if (var3 != 0) {
            this.failure(var1);
            if (this.quitOnFirstFailure) {
               return this.failedCounter;
            }
         }
      }

      return this.failedCounter;
   }

   private DataValueDescriptor[] getNextFK() throws StandardException {
      if (this.currFKRowIndex > this.lastFKRowIndex || this.currFKRowIndex == -1) {
         int var1 = this.foreignKeyScan.fetchNextGroup(this.foreignKeyRowArray, (RowLocation[])null);
         if (var1 == 0) {
            this.currFKRowIndex = -1;
            return null;
         }

         this.lastFKRowIndex = var1 - 1;
         this.currFKRowIndex = 0;
      }

      return this.foreignKeyRowArray[this.currFKRowIndex++];
   }

   private DataValueDescriptor[] getNextRef() throws StandardException {
      if (this.currRefRowIndex > this.lastRefRowIndex || this.currRefRowIndex == -1) {
         int var1 = this.referencedKeyScan.fetchNextGroup(this.referencedKeyRowArray, (RowLocation[])null);
         if (var1 == 0) {
            this.currRefRowIndex = -1;
            return null;
         }

         this.lastRefRowIndex = var1 - 1;
         this.currRefRowIndex = 0;
      }

      return this.referencedKeyRowArray[this.currRefRowIndex++];
   }

   private void failure(DataValueDescriptor[] var1) throws StandardException {
      if (this.deferred) {
         this.deferredRowsHashTable = DeferredConstraintsMemory.rememberFKViolation(this.lcc, this.deferredRowsHashTable, this.constraintId, var1, this.schemaName, this.tableName);
      } else {
         if (this.failedCounter == 0 && this.firstRowToFail != null) {
            this.firstRowToFail.setRowArray(var1);
            this.firstRowToFail.setRowArray(this.firstRowToFail.getRowArrayClone());
         }

         ++this.failedCounter;
         if (this.unreferencedCC != null) {
            this.unreferencedCC.insert(var1);
         }
      }

   }

   private boolean anyNull(DataValueDescriptor[] var1) throws StandardException {
      for(int var3 = 0; var3 < this.numColumns; ++var3) {
         DataValueDescriptor var2 = var1[var3];
         if (var2.isNull()) {
            return true;
         }
      }

      return false;
   }

   private int greaterThan(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      if (this.anyNull(var1)) {
         return 0;
      } else {
         for(int var6 = 0; var6 < this.numColumns; ++var6) {
            DataValueDescriptor var3 = var1[var6];
            DataValueDescriptor var4 = var2[var6];
            int var5 = var3.compare(var4);
            if (var5 > 0) {
               return 1;
            }

            if (var5 < 0) {
               return -1;
            }
         }

         return 0;
      }
   }
}
