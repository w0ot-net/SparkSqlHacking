package org.apache.derby.impl.sql.compile;

import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.RequiredRowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.shared.common.error.StandardException;

class IndexToBaseRowNode extends FromTable {
   protected FromBaseTable source;
   protected ConglomerateDescriptor baseCD;
   protected boolean cursorTargetTable;
   protected PredicateList restrictionList;
   protected boolean forUpdate;
   private FormatableBitSet heapReferencedCols;
   private FormatableBitSet indexReferencedCols;
   private FormatableBitSet allReferencedCols;
   private FormatableBitSet heapOnlyReferencedCols;

   IndexToBaseRowNode(FromBaseTable var1, ConglomerateDescriptor var2, ResultColumnList var3, boolean var4, FormatableBitSet var5, FormatableBitSet var6, PredicateList var7, boolean var8, Properties var9, ContextManager var10) {
      super((String)null, var9, var10);
      this.source = var1;
      this.baseCD = var2;
      this.setResultColumns(var3);
      this.cursorTargetTable = var4;
      this.restrictionList = var7;
      this.forUpdate = var8;
      this.heapReferencedCols = var5;
      this.indexReferencedCols = var6;
      if (this.indexReferencedCols == null) {
         this.allReferencedCols = this.heapReferencedCols;
         this.heapOnlyReferencedCols = this.heapReferencedCols;
      } else {
         this.allReferencedCols = new FormatableBitSet(this.heapReferencedCols);
         this.allReferencedCols.or(this.indexReferencedCols);
         this.heapOnlyReferencedCols = new FormatableBitSet(this.allReferencedCols);
         this.heapOnlyReferencedCols.xor(this.indexReferencedCols);
      }

   }

   public boolean forUpdate() {
      return this.source.forUpdate();
   }

   public AccessPath getTrulyTheBestAccessPath() {
      return this.source.getTrulyTheBestAccessPath();
   }

   CostEstimate getCostEstimate() {
      return this.source.getTrulyTheBestAccessPath().getCostEstimate();
   }

   CostEstimate getFinalCostEstimate() {
      return this.source.getFinalCostEstimate();
   }

   boolean isOrderedOn(ColumnReference[] var1, boolean var2, List var3) throws StandardException {
      return this.source.isOrderedOn(var1, var2, var3);
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      ValueNode var3 = null;
      this.assignResultSetNumber();
      this.setCostEstimate(this.getFinalCostEstimate());
      if (this.restrictionList != null) {
         var3 = this.restrictionList.restorePredicates();
         this.restrictionList = null;
      }

      int var4 = -1;
      if (this.heapReferencedCols != null) {
         var4 = var1.addItem(this.heapReferencedCols);
      }

      int var5 = -1;
      if (this.allReferencedCols != null) {
         var5 = var1.addItem(this.allReferencedCols);
      }

      int var6 = -1;
      if (this.heapOnlyReferencedCols != null) {
         var6 = var1.addItem(this.heapOnlyReferencedCols);
      }

      int var7 = var1.addItem(new ReferencedColumnsDescriptorImpl(this.getIndexColMapping()));
      long var8 = this.baseCD.getConglomerateNumber();
      StaticCompiledOpenConglomInfo var10 = this.getLanguageConnectionContext().getTransactionCompile().getStaticCompiledConglomInfo(var8);
      var1.pushGetResultSetFactoryExpression(var2);
      var2.push(var8);
      var2.push(var1.addItem(var10));
      this.source.generate(var1, var2);
      var2.upCast("org.apache.derby.iapi.sql.execute.NoPutResultSet");
      boolean var11 = this.indexReferencedCols != null && this.indexReferencedCols.getNumBitsSet() != 0;
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate(this.heapReferencedCols, var11)));
      var2.push(this.getResultSetNumber());
      var2.push(this.source.getBaseTableName());
      var2.push(var4);
      var2.push(var5);
      var2.push(var6);
      var2.push(var7);
      if (var3 == null) {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      } else {
         MethodBuilder var12 = var1.newUserExprFun();
         var3.generate(var1, var12);
         var12.methodReturn();
         var12.complete();
         var1.pushMethodReference(var2, var12);
      }

      var2.push(this.forUpdate);
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.push(this.source.getTableDescriptor().getNumberOfColumns());
      var2.callMethod((short)185, (String)null, "getIndexRowToBaseRowResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 15);
      if (this.cursorTargetTable) {
         var1.rememberCursorTarget(var2);
      }

   }

   boolean isOneRowResultSet() throws StandardException {
      return this.source.isOneRowResultSet();
   }

   boolean isNotExists() {
      return this.source.isNotExists();
   }

   void decrementLevel(int var1) {
      this.source.decrementLevel(var1);
   }

   int updateTargetLockMode() {
      return this.source.updateTargetLockMode();
   }

   void adjustForSortElimination() {
      this.source.disableBulkFetch();
   }

   void adjustForSortElimination(RequiredRowOrdering var1) throws StandardException {
      this.adjustForSortElimination();
      this.source.adjustForSortElimination(var1);
   }

   private int[] getIndexColMapping() {
      int var1 = this.getResultColumns().size();
      int[] var2 = new int[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         ResultColumn var4 = (ResultColumn)this.getResultColumns().elementAt(var3);
         if (this.indexReferencedCols != null && var4.getExpression() instanceof VirtualColumnNode) {
            VirtualColumnNode var5 = (VirtualColumnNode)var4.getExpression();
            var2[var3] = var5.getSourceColumn().getVirtualColumnId() - 1;
         } else {
            var2[var3] = -1;
         }
      }

      return var2;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.source != null) {
         this.source = (FromBaseTable)this.source.accept(var1);
      }

   }
}
