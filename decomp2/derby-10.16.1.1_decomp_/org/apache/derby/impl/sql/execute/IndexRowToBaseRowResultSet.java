package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.DynamicCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.impl.sql.GenericPreparedStatement;
import org.apache.derby.shared.common.error.StandardException;

class IndexRowToBaseRowResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public NoPutResultSet source;
   private GeneratedMethod restriction;
   public FormatableBitSet accessedHeapCols;
   private FormatableBitSet accessedAllCols;
   public String indexName;
   private int[] indexCols;
   private DynamicCompiledOpenConglomInfo dcoci;
   private StaticCompiledOpenConglomInfo scoci;
   private ConglomerateController baseCC;
   private boolean closeBaseCCHere;
   private boolean forUpdate;
   private DataValueDescriptor[] rowArray;
   RowLocation baseRowLocation;
   boolean copiedFromSource;
   public long restrictionTime;
   private int _baseColumnCount;
   private boolean _includeRowLocation;
   private FormatableBitSet _heapColsWithoutRowLocation;

   IndexRowToBaseRowResultSet(long var1, int var3, Activation var4, NoPutResultSet var5, int var6, int var7, String var8, int var9, int var10, int var11, int var12, GeneratedMethod var13, boolean var14, double var15, double var17, int var19) throws StandardException {
      super(var4, var7, var15, var17);
      GenericPreparedStatement var20 = (GenericPreparedStatement)var4.getPreparedStatement();
      this.scoci = (StaticCompiledOpenConglomInfo)var20.getSavedObject(var3);
      TransactionController var21 = this.activation.getTransactionController();
      this.dcoci = var21.getDynamicCompiledConglomInfo(var1);
      this.source = var5;
      this.indexName = var8;
      this.forUpdate = var14;
      this.restriction = var13;
      this._baseColumnCount = var19;
      if (var9 != -1) {
         this.accessedHeapCols = (FormatableBitSet)var20.getSavedObject(var9);
      }

      if (var10 != -1) {
         this.accessedAllCols = (FormatableBitSet)var20.getSavedObject(var10);
      }

      this.indexCols = ((ReferencedColumnsDescriptorImpl)var20.getSavedObject(var12)).getReferencedColumnPositions();
      ExecRow var22 = ((ExecRowBuilder)var20.getSavedObject(var6)).build(var4.getExecutionFactory());
      this.getCompactRow(var22, this.accessedAllCols, false);
      if (this.accessedHeapCols == null) {
         this.rowArray = var22.getRowArray();
      } else {
         DataValueDescriptor[] var23 = var22.getRowArray();
         FormatableBitSet var24 = (FormatableBitSet)var20.getSavedObject(var11);
         int var25 = var24.getLength();
         this.rowArray = new DataValueDescriptor[var25];
         int var26 = Math.min(var23.length, var25);

         for(int var27 = 0; var27 < var26; ++var27) {
            if (var23[var27] != null && var24.isSet(var27)) {
               this.rowArray[var27] = var23[var27];
            }
         }
      }

      this._includeRowLocation = this._baseColumnCount < this.accessedHeapCols.getLength();
      if (this._includeRowLocation) {
         this._heapColsWithoutRowLocation = (FormatableBitSet)this.accessedHeapCols.clone();
         this._heapColsWithoutRowLocation.clear(this.accessedHeapCols.getLength() - 1);
      }

      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      boolean var1 = false;
      this.beginTime = this.getCurrentTimeMillis();
      this.source.openCore();
      if (this.source.requiresRelocking()) {
         var1 = true;
      }

      TransactionController var2 = this.activation.getTransactionController();
      int var3;
      if (this.forUpdate) {
         var3 = 4;
      } else {
         var3 = 0;
      }

      int var4 = this.source.getScanIsolationLevel();
      if (!var1) {
         var3 |= 8192;
      }

      if (this.forUpdate) {
         this.baseCC = this.activation.getHeapConglomerateController();
      }

      if (this.baseCC == null) {
         this.baseCC = var2.openCompiledConglomerate(this.activation.getResultSetHoldability(), var3, 6, var4, this.scoci, this.dcoci);
         this.closeBaseCCHere = true;
      }

      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.source.reopenCore();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         ExecRow var2 = null;
         boolean var3 = false;
         long var5 = 0L;
         this.beginTime = this.getCurrentTimeMillis();
         if (!this.isOpen) {
            throw StandardException.newException("XCL16.S", new Object[]{"next"});
         } else {
            do {
               var1 = this.source.getNextRowCore();
               if (var1 != null) {
                  this.baseRowLocation = (RowLocation)var1.getColumn(var1.nColumns());
                  boolean var7 = this.baseCC.fetch(this.baseRowLocation, this.rowArray, this._includeRowLocation ? this._heapColsWithoutRowLocation : this.accessedHeapCols);
                  if (var7) {
                     if (!this.copiedFromSource) {
                        this.copiedFromSource = true;

                        for(int var8 = 0; var8 < this.indexCols.length; ++var8) {
                           if (this.indexCols[var8] != -1) {
                              this.compactRow.setColumn(var8 + 1, var1.getColumn(this.indexCols[var8] + 1));
                           }
                        }
                     }

                     this.setCurrentRow(this.compactRow);
                     DataValueDescriptor var4 = (DataValueDescriptor)(this.restriction == null ? null : this.restriction.invoke(this.activation));
                     this.restrictionTime += this.getElapsedMillis(var5);
                     var3 = var4 == null || !var4.isNull() && var4.getBoolean();
                  }

                  if (var3 && var7) {
                     this.currentRow = this.compactRow;
                  } else {
                     ++this.rowsFiltered;
                     this.clearCurrentRow();
                     this.baseRowLocation = null;
                  }

                  if (this._includeRowLocation) {
                     this.currentRow.setColumn(this.currentRow.nColumns(), this.baseRowLocation);
                  }

                  ++this.rowsSeen;
                  var2 = this.currentRow;
               } else {
                  this.clearCurrentRow();
                  this.baseRowLocation = null;
                  var2 = null;
               }
            } while(var1 != null && !var3);

            this.nextTime += this.getElapsedMillis(this.beginTime);
            return var2;
         }
      }
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         if (this.closeBaseCCHere && this.baseCC != null) {
            this.baseCC.close();
         }

         this.baseCC = null;
         this.source.close();
         super.close();
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public RowLocation getRowLocation() throws StandardException {
      return this.baseRowLocation;
   }

   public void positionScanAtRowLocation(RowLocation var1) throws StandardException {
      this.baseRowLocation = var1;
      this.source.positionScanAtRowLocation(var1);
   }

   public ExecRow getCurrentRow() throws StandardException {
      ExecRow var1 = null;
      if (this.currentRow == null) {
         return null;
      } else {
         var1 = this.activation.getExecutionFactory().getValueRow(this.indexCols.length);
         var1.setRowArray(this.rowArray);
         boolean var2 = this.baseCC.fetch(this.baseRowLocation, this.rowArray, (FormatableBitSet)null);
         if (var2) {
            this.setCurrentRow(var1);
         } else {
            this.clearCurrentRow();
         }

         return this.currentRow;
      }
   }

   public boolean isForUpdate() {
      return this.source.isForUpdate();
   }
}
