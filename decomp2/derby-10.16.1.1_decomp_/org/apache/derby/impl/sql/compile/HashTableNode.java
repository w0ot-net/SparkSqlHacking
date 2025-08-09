package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.io.FormatableIntHolder;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class HashTableNode extends SingleChildResultSetNode {
   PredicateList searchPredicateList;
   PredicateList joinPredicateList;
   SubqueryList pSubqueryList;
   SubqueryList rSubqueryList;

   HashTableNode(ResultSetNode var1, Properties var2, ResultColumnList var3, PredicateList var4, PredicateList var5, AccessPathImpl var6, CostEstimate var7, SubqueryList var8, SubqueryList var9, int[] var10, ContextManager var11) {
      super(var1, var2, var11);
      this.setResultColumns(var3);
      this.searchPredicateList = var4;
      this.joinPredicateList = var5;
      this.trulyTheBestAccessPath = var6;
      this.setCostEstimate(var7);
      this.pSubqueryList = var8;
      this.rSubqueryList = var9;
      this.setHashKeyColumns(var10);
   }

   public Optimizable modifyAccessPath(JBitSet var1, Optimizer var2) throws StandardException {
      return this;
   }

   void printSubNodes(int var1) {
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.childResult instanceof FromVTI) {
         ((FromVTI)this.childResult).computeProjectionAndRestriction(this.searchPredicateList);
      }

      this.generateMinion(var1, var2, false);
   }

   void generateResultSet(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateMinion(var1, var2, true);
   }

   private void generateMinion(ExpressionClassBuilder var1, MethodBuilder var2, boolean var3) throws StandardException {
      ValueNode var5 = null;
      Object var6 = null;
      this.verifyProperties(this.getDataDictionary());
      if (this.searchPredicateList != null) {
         this.searchPredicateList.removeRedundantPredicates();
         var5 = this.searchPredicateList.restorePredicates();
         this.searchPredicateList = null;
      }

      ResultColumnList.ColumnMapping var7 = this.getResultColumns().mapSourceColumns();
      int[] var8 = var7.mapArray;
      int var9 = var1.addItem(new ReferencedColumnsDescriptorImpl(var8));
      FormatableIntHolder[] var10 = FormatableIntHolder.getFormatableIntHolders(this.hashKeyColumns());
      FormatableArrayHolder var11 = new FormatableArrayHolder(var10);
      int var12 = var1.addItem(var11);
      var1.pushGetResultSetFactoryExpression(var2);
      if (var3) {
         this.childResult.generateResultSet(var1, var2);
      } else {
         this.childResult.generate((ActivationClassBuilder)var1, var2);
      }

      this.assignResultSetNumber();
      if (this.pSubqueryList != null && this.pSubqueryList.size() > 0) {
         this.pSubqueryList.setPointOfAttachment(this.getResultSetNumber());
      }

      if (this.rSubqueryList != null && this.rSubqueryList.size() > 0) {
         this.rSubqueryList.setPointOfAttachment(this.getResultSetNumber());
      }

      this.setCostEstimate(this.childResult.getFinalCostEstimate());
      if (var5 == null) {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      } else {
         MethodBuilder var4 = var1.newUserExprFun();
         var5.generateExpression(var1, var4);
         var4.methodReturn();
         var4.complete();
         var1.pushMethodReference(var2, var4);
      }

      this.joinPredicateList.generateQualifiers(var1, var2, (Optimizable)this.childResult, false);
      if (this.reflectionNeededForProjection()) {
         this.getResultColumns().generateCore(var1, var2, false);
      } else {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      }

      var2.push(this.getResultSetNumber());
      var2.push(var9);
      var2.push(this.getResultColumns().reusableResult());
      var2.push(var12);
      var2.push(false);
      var2.push(-1L);
      var2.push(this.initialCapacity);
      var2.push(this.loadFactor);
      var2.push(this.getCostEstimate().singleScanRowCount());
      var2.push(this.getCostEstimate().getEstimatedCost());
      var2.callMethod((short)185, (String)null, "getHashTableResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 14);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.searchPredicateList != null) {
         this.searchPredicateList = (PredicateList)this.searchPredicateList.accept(var1);
      }

      if (this.joinPredicateList != null) {
         this.joinPredicateList = (PredicateList)this.joinPredicateList.accept(var1);
      }

   }
}
