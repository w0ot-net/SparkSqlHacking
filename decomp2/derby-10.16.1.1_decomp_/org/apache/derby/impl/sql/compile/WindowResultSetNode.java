package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.shared.common.error.StandardException;

class WindowResultSetNode extends SingleChildResultSetNode {
   FromTable parent;
   List windowFuncCalls;
   WindowDefinitionNode wdn;

   WindowResultSetNode(ResultSetNode var1, WindowDefinitionNode var2, List var3, int var4, ContextManager var5) throws StandardException {
      super(var1, (Properties)null, var5);
      this.wdn = var2;
      this.windowFuncCalls = var3;
      this.setLevel(var4);
      this.parent = this;
      ResultColumnList var6 = this.childResult.getResultColumns().copyListAndObjects();
      this.setResultColumns(this.childResult.getResultColumns());
      this.childResult.setResultColumns(var6);
      this.addNewPRNode();
      this.addNewColumns();
   }

   private void addNewPRNode() throws StandardException {
      ResultColumnList var1 = new ResultColumnList(this.getContextManager());

      for(ResultColumn var3 : this.getResultColumns()) {
         if (!var3.isGenerated()) {
            var1.addElement(var3);
         }
      }

      var1.copyOrderBySelect(this.getResultColumns());
      this.parent = new ProjectRestrictNode(this, var1, (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, (Properties)null, this.getContextManager());
      this.childResult.setResultColumns(new ResultColumnList(this.getContextManager()));
      this.setResultColumns(new ResultColumnList(this.getContextManager()));
      CollectNodesVisitor var13 = new CollectNodesVisitor(ColumnReference.class);
      this.parent.getResultColumns().accept(var13);
      ArrayList var14 = new ArrayList();

      for(ColumnReference var5 : var13.getList()) {
         if (!this.colRefAlreadySeen(var14, var5)) {
            var14.add(var5);
         }
      }

      CollectNodesVisitor var15 = new CollectNodesVisitor(VirtualColumnNode.class);
      this.parent.getResultColumns().accept(var15);
      var14.addAll(var15.getList());
      ResultColumnList var16 = this.childResult.getResultColumns();
      ResultColumnList var6 = this.getResultColumns();

      for(ValueNode var8 : var14) {
         ResultColumn var9 = new ResultColumn("##UnWindowingColumn", var8, this.getContextManager());
         var16.addElement(var9);
         var9.markGenerated();
         var9.bindResultColumnToExpression();
         var9.setVirtualColumnId(var16.size());
         ResultColumn var10 = new ResultColumn("##UnWindowingColumn", var8, this.getContextManager());
         var6.addElement(var10);
         var10.markGenerated();
         var10.bindResultColumnToExpression();
         var10.setVirtualColumnId(var6.size());
         VirtualColumnNode var11 = new VirtualColumnNode(this, var10, var6.size(), this.getContextManager());
         SubstituteExpressionVisitor var12 = new SubstituteExpressionVisitor(var8, var11, (Class)null);
         this.parent.getResultColumns().accept(var12);
      }

   }

   private boolean colRefAlreadySeen(List var1, ColumnReference var2) throws StandardException {
      for(ValueNode var4 : var1) {
         ColumnReference var5 = (ColumnReference)var4;
         if (var5.isEquivalent(var2)) {
            return true;
         }
      }

      return false;
   }

   private void addNewColumns() throws StandardException {
      ResultColumnList var1 = this.childResult.getResultColumns();
      ResultColumnList var2 = this.getResultColumns();
      ReplaceWindowFuncCallsWithCRVisitor var3 = new ReplaceWindowFuncCallsWithCRVisitor(new ResultColumnList(this.getContextManager()), ((FromTable)this.childResult).getTableNumber(), ResultSetNode.class);
      this.parent.getResultColumns().accept(var3);

      for(WindowFunctionNode var5 : this.windowFuncCalls) {
         WindowDefinitionNode var6 = (WindowDefinitionNode)var5.getWindow();
         if (var6 == this.wdn) {
            ResultColumn var7 = new ResultColumn("##winFuncResult", var5.getNewNullResultExpression(), this.getContextManager());
            var7.markGenerated();
            var7.bindResultColumnToExpression();
            var1.addElement(var7);
            var7.setVirtualColumnId(var1.size());
            ColumnReference var8 = new ColumnReference(var7.getName(), (TableName)null, this.getContextManager());
            var8.setSource(var7);
            var8.setNestingLevel(this.getLevel());
            var8.setSourceLevel(this.getLevel());
            var8.markGeneratedToReplaceWindowFunctionCall();
            ResultColumn var9 = new ResultColumn(var7.getColumnName(), var8, this.getContextManager());
            var9.markGenerated();
            var9.bindResultColumnToExpression();
            var2.addElement(var9);
            var9.setVirtualColumnId(var2.size());
            var8 = var5.getGeneratedRef();
            if (var8 != null) {
               var8.setSource(var9);
            }
         }
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      var1.pushGetResultSetFactoryExpression(var2);
      int var3 = this.getResultColumns().size();
      FormatableBitSet var4 = new FormatableBitSet(var3);

      for(int var5 = var3 - 1; var5 >= 0; --var5) {
         ResultColumn var6 = (ResultColumn)this.getResultColumns().elementAt(var5);
         ValueNode var7 = var6.getExpression();
         if (!var6.isGenerated() || !(var7 instanceof ColumnReference) || !((ColumnReference)var7).getGeneratedToReplaceWindowFunctionCall()) {
            var4.set(var5);
         }
      }

      int var8 = var1.addItem(var4);
      var1.pushThisAsActivation(var2);
      this.childResult.generate(var1, var2);
      var2.upCast("org.apache.derby.iapi.sql.execute.NoPutResultSet");
      var2.push(var1.addItem(this.getResultColumns().buildRowTemplate()));
      var2.push(this.getResultSetNumber());
      var2.push(var8);
      var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      var2.push(this.getCostEstimate().rowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getWindowResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 8);
   }

   final FromTable getParent() {
      return this.parent;
   }

   public void printSubNodes(int var1) {
   }
}
