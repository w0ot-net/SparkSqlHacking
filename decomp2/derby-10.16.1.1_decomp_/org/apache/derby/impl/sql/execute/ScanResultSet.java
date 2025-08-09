package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.w3c.dom.Element;

abstract class ScanResultSet extends NoPutResultSetImpl {
   private final boolean tableLocked;
   private final boolean unspecifiedIsolationLevel;
   private final int suppliedLockMode;
   private boolean isolationLevelNeedsUpdate;
   int lockMode;
   int isolationLevel;
   final ExecRowBuilder resultRowBuilder;
   final ExecRow candidate;
   protected FormatableBitSet accessedCols;
   protected boolean fetchRowLocations = false;
   public String tableName;
   public String indexName;

   ScanResultSet(Activation var1, int var2, int var3, int var4, boolean var5, int var6, int var7, double var8, double var10) throws StandardException {
      super(var1, var2, var8, var10);
      this.tableLocked = var5;
      this.suppliedLockMode = var4;
      if (var6 == 0) {
         this.unspecifiedIsolationLevel = true;
         var6 = this.getLanguageConnectionContext().getCurrentIsolationLevel();
      } else {
         this.unspecifiedIsolationLevel = false;
      }

      this.lockMode = this.getLockMode(var6);
      this.isolationLevel = this.translateLanguageIsolationLevel(var6);
      ExecPreparedStatement var12 = var1.getPreparedStatement();
      this.resultRowBuilder = (ExecRowBuilder)var12.getSavedObject(var3);
      this.candidate = this.resultRowBuilder.build(var1.getExecutionFactory());
      this.accessedCols = var7 != -1 ? (FormatableBitSet)var12.getSavedObject(var7) : null;
   }

   void initIsolationLevel() {
      if (this.isolationLevelNeedsUpdate) {
         int var1 = this.getLanguageConnectionContext().getCurrentIsolationLevel();
         this.lockMode = this.getLockMode(var1);
         this.isolationLevel = this.translateLanguageIsolationLevel(var1);
         this.isolationLevelNeedsUpdate = false;
      }

   }

   private int getLockMode(int var1) {
      return !this.tableLocked && var1 != 4 ? 6 : this.suppliedLockMode;
   }

   protected void setRowLocationsState() throws StandardException {
      this.fetchRowLocations = this.indexName == null && this.candidate.nColumns() > 0 && this.candidate.getColumn(this.candidate.nColumns()) instanceof RowLocation;
   }

   private int translateLanguageIsolationLevel(int var1) {
      switch (var1) {
         case 1:
            return 1;
         case 2:
            if (!this.canGetInstantaneousLocks()) {
               return 2;
            }

            return 3;
         case 3:
            return 4;
         case 4:
            return 5;
         default:
            return 0;
      }
   }

   abstract boolean canGetInstantaneousLocks();

   public int getScanIsolationLevel() {
      return this.isolationLevel;
   }

   public void close() throws StandardException {
      this.isolationLevelNeedsUpdate = this.unspecifiedIsolationLevel;
      this.candidate.resetRowArray();
      super.close();
   }

   public Element toXML(Element var1, String var2) throws Exception {
      Element var3 = super.toXML(var1, var2);
      if (this.tableName != null) {
         var3.setAttribute("tableName", this.tableName);
      }

      if (this.indexName != null) {
         var3.setAttribute("indexName", this.indexName);
      }

      return var3;
   }
}
