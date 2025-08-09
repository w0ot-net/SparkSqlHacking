package org.apache.derby.impl.sql.execute;

import java.util.Enumeration;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.sql.execute.RowChanger;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLRef;
import org.apache.derby.shared.common.error.StandardException;

class ProjectRestrictResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public long restrictionTime;
   public long projectionTime;
   final NoPutResultSet source;
   public GeneratedMethod constantRestriction;
   public GeneratedMethod restriction;
   public boolean doesProjection;
   private GeneratedMethod projection;
   private int[] projectMapping;
   private boolean[] cloneMap;
   private boolean runTimeStatsOn;
   private ExecRow mappedResultRow;
   public boolean reuseResult;
   private boolean shortCircuitOpen;
   private ExecRow projRow;
   private final boolean validatingCheckConstraint;
   private final UUID validatingBaseTableUUID;
   Enumeration rowLocations;

   ProjectRestrictResultSet(NoPutResultSet var1, Activation var2, GeneratedMethod var3, GeneratedMethod var4, int var5, GeneratedMethod var6, int var7, int var8, boolean var9, boolean var10, boolean var11, UUID var12, double var13, double var15) throws StandardException {
      super(var2, var5, var13, var15);
      this.source = var1;
      this.restriction = var3;
      this.projection = var4;
      this.constantRestriction = var6;
      this.projectMapping = ((ReferencedColumnsDescriptorImpl)var2.getPreparedStatement().getSavedObject(var7)).getReferencedColumnPositions();
      this.reuseResult = var9;
      this.doesProjection = var10;
      this.validatingCheckConstraint = var11;
      this.validatingBaseTableUUID = var12;
      if (this.projection == null) {
         this.mappedResultRow = this.activation.getExecutionFactory().getValueRow(this.projectMapping.length);
      }

      this.cloneMap = (boolean[])var2.getPreparedStatement().getSavedObject(var8);
      this.runTimeStatsOn = this.getLanguageConnectionContext().getRunTimeStatisticsMode();
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      boolean var1 = true;
      this.beginTime = this.getCurrentTimeMillis();
      if (this.constantRestriction != null) {
         DataValueDescriptor var2 = (DataValueDescriptor)this.constantRestriction.invoke(this.activation);
         var1 = var2 == null || !var2.isNull() && var2.getBoolean();
      }

      if (this.validatingCheckConstraint) {
         this.rowLocations = DeferredConstraintsMemory.getDeferredCheckConstraintLocations(this.activation, this.validatingBaseTableUUID);
      }

      if (var1) {
         this.source.openCore();
      } else {
         this.shortCircuitOpen = true;
      }

      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      boolean var1 = true;
      this.beginTime = this.getCurrentTimeMillis();
      if (this.constantRestriction != null) {
         DataValueDescriptor var2 = (DataValueDescriptor)this.constantRestriction.invoke(this.activation);
         var1 = var2 == null || !var2.isNull() && var2.getBoolean();
      }

      if (var1) {
         this.source.reopenCore();
      } else {
         this.shortCircuitOpen = true;
      }

      this.isOpen = true;
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         Object var1 = null;
         ExecRow var2 = null;
         boolean var3 = false;
         long var5 = 0L;
         if (this.shortCircuitOpen) {
            return var2;
         } else {
            this.beginTime = this.getCurrentTimeMillis();

            do {
               if (this.validatingCheckConstraint) {
                  for(var9 = null; this.rowLocations.hasMoreElements() && var9 == null; var9 = this.source.getNextRowCore()) {
                     DataValueDescriptor[] var7 = (DataValueDescriptor[])this.rowLocations.nextElement();
                     RowLocation var8 = (RowLocation)((SQLRef)var7[0]).getObject();
                     ((ValidateCheckConstraintResultSet)this.source).positionScanAtRowLocation(var8);
                  }
               } else {
                  var9 = this.source.getNextRowCore();
               }

               if (var9 != null) {
                  var5 = this.getCurrentTimeMillis();
                  if (this.restriction == null) {
                     var3 = true;
                  } else {
                     this.setCurrentRow(var9);
                     DataValueDescriptor var4 = (DataValueDescriptor)this.restriction.invoke(this.activation);
                     this.restrictionTime += this.getElapsedMillis(var5);
                     var3 = !var4.isNull() && var4.getBoolean();
                     if (!var3) {
                        ++this.rowsFiltered;
                     }
                  }

                  ++this.rowsSeen;
               }
            } while(var9 != null && !var3);

            if (var9 != null) {
               var5 = this.getCurrentTimeMillis();
               var2 = this.doProjection(var9);
               this.projectionTime += this.getElapsedMillis(var5);
            } else {
               this.clearCurrentRow();
            }

            this.currentRow = var2;
            if (this.runTimeStatsOn) {
               if (!this.isTopResultSet) {
                  StatementContext var12 = this.activation.getLanguageConnectionContext().getStatementContext();
                  this.subqueryTrackingArray = var12.getSubqueryTrackingArray();
               }

               this.nextTime += this.getElapsedMillis(this.beginTime);
            }

            return var2;
         }
      }
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public void close() throws StandardException {
      if (this.shortCircuitOpen) {
         this.shortCircuitOpen = false;
         this.source.close();
         super.close();
      } else {
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            this.clearCurrentRow();
            this.source.close();
            super.close();
         }

         this.closeTime += this.getElapsedMillis(this.beginTime);
      }
   }

   public void finish() throws StandardException {
      this.source.finish();
      this.finishAndRTS();
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.source).getRowLocation();
   }

   public ExecRow getCurrentRow() throws StandardException {
      Object var1 = null;
      ExecRow var2 = null;
      boolean var3 = false;
      if (this.currentRow == null) {
         return null;
      } else {
         ExecRow var5 = ((CursorResultSet)this.source).getCurrentRow();
         if (var5 != null) {
            this.setCurrentRow(var5);
            DataValueDescriptor var4 = (DataValueDescriptor)(this.restriction == null ? null : this.restriction.invoke(this.activation));
            var3 = var4 == null || !var4.isNull() && var4.getBoolean();
         }

         if (var5 != null && var3) {
            var2 = this.doProjection(var5);
         }

         this.currentRow = var2;
         if (var2 == null) {
            this.clearCurrentRow();
         }

         return this.currentRow;
      }
   }

   private ExecRow doProjection(ExecRow var1) throws StandardException {
      if (this.reuseResult && this.projRow != null) {
         this.setCurrentRow(this.projRow);
         return this.projRow;
      } else {
         ExecRow var2;
         if (this.projection != null) {
            var2 = (ExecRow)this.projection.invoke(this.activation);
         } else {
            var2 = this.mappedResultRow;
         }

         for(int var3 = 0; var3 < this.projectMapping.length; ++var3) {
            if (this.projectMapping[var3] != -1) {
               DataValueDescriptor var4 = var1.getColumn(this.projectMapping[var3]);
               if (this.cloneMap[var3] && var4.hasStream()) {
                  var4 = var4.cloneValue(false);
               }

               var2.setColumn(var3 + 1, var4);
            }
         }

         this.setCurrentRow(var2);
         if (this.reuseResult) {
            this.projRow = var2;
         }

         return var2;
      }
   }

   public ExecRow doBaseRowProjection(ExecRow var1) throws StandardException {
      ExecRow var2;
      if (this.source instanceof ProjectRestrictResultSet) {
         ProjectRestrictResultSet var3 = (ProjectRestrictResultSet)this.source;
         var2 = var3.doBaseRowProjection(var1);
      } else {
         var2 = var1.getNewNullRow();
         var2.setRowArray(var1.getRowArray());
      }

      return this.doProjection(var2);
   }

   public int[] getBaseProjectMapping() {
      int[] var1;
      if (this.source instanceof ProjectRestrictResultSet) {
         var1 = new int[this.projectMapping.length];
         ProjectRestrictResultSet var2 = (ProjectRestrictResultSet)this.source;
         int[] var3 = var2.getBaseProjectMapping();

         for(int var4 = 0; var4 < this.projectMapping.length; ++var4) {
            if (this.projectMapping[var4] > 0) {
               var1[var4] = var3[this.projectMapping[var4] - 1];
            }
         }
      } else {
         var1 = this.projectMapping;
      }

      return var1;
   }

   public boolean isForUpdate() {
      return this.source.isForUpdate();
   }

   public void updateRow(ExecRow var1, RowChanger var2) throws StandardException {
      this.source.updateRow(var1, var2);
   }

   public void markRowAsDeleted() throws StandardException {
      this.source.markRowAsDeleted();
   }
}
