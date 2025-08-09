package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.sql.compile.ExpressionClassBuilderInterface;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.Optimizable;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.PropertyUtil;
import org.apache.derby.shared.common.error.StandardException;

abstract class BaseJoinStrategy implements JoinStrategy {
   public boolean bulkFetchOK() {
      return true;
   }

   public boolean ignoreBulkFetch() {
      return false;
   }

   void fillInScanArgs1(TransactionController var1, MethodBuilder var2, Optimizable var3, OptimizablePredicateList var4, ExpressionClassBuilderInterface var5, int var6) throws StandardException {
      boolean var7 = var4.sameStartStopPosition();
      ExpressionClassBuilder var8 = (ExpressionClassBuilder)var5;
      long var9 = var3.getTrulyTheBestAccessPath().getConglomerateDescriptor().getConglomerateNumber();
      StaticCompiledOpenConglomInfo var11 = var1.getStaticCompiledConglomInfo(var9);
      var8.pushThisAsActivation(var2);
      var2.push(var9);
      var2.push(var8.addItem(var11));
      var2.push(var6);
      var2.push(var3.getResultSetNumber());
      var4.generateStartKey(var8, var2, var3);
      var2.push(var4.startOperator(var3));
      if (!var7) {
         var4.generateStopKey(var8, var2, var3);
      } else {
         var2.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      }

      var2.push(var4.stopOperator(var3));
      var2.push(var7);
      var4.generateQualifiers(var8, var2, var3, true);
      var2.upCast("org.apache.derby.iapi.store.access.Qualifier[][]");
   }

   final void fillInScanArgs2(MethodBuilder var1, Optimizable var2, int var3, int var4, int var5, int var6, boolean var7, int var8) throws StandardException {
      var1.push(var2.getBaseTableName());
      if (var2.getProperties() != null) {
         var1.push(PropertyUtil.sortProperties(var2.getProperties()));
      } else {
         var1.pushNull("java.lang.String");
      }

      ConglomerateDescriptor var9 = var2.getTrulyTheBestAccessPath().getConglomerateDescriptor();
      if (var9.isConstraint()) {
         DataDictionary var10 = var2.getDataDictionary();
         TableDescriptor var11 = var2.getTableDescriptor();
         ConstraintDescriptor var12 = var10.getConstraintDescriptor(var11, var9.getUUID());
         var1.push(var12.getConstraintName());
      } else if (var9.isIndex()) {
         var1.push(var9.getConglomerateName());
      } else {
         var1.pushNull("java.lang.String");
      }

      var1.push(var9.isConstraint());
      var1.push(var2.forUpdate());
      var1.push(var4);
      var1.push(var5);
      var1.push(var6);
      var1.push(var7);
      var1.push(var8);
      if (var3 > 0) {
         var1.push(var3);
         var1.push(var2.hasLargeObjectColumns());
      }

      if (this.validForOutermostTable()) {
         var1.push(var2.isOneRowScan());
      }

      var1.push(var2.getTrulyTheBestAccessPath().getCostEstimate().rowCount());
      var1.push(var2.getTrulyTheBestAccessPath().getCostEstimate().getEstimatedCost());
   }

   public boolean isHashJoin() {
      return false;
   }

   protected boolean validForOutermostTable() {
      return false;
   }
}
