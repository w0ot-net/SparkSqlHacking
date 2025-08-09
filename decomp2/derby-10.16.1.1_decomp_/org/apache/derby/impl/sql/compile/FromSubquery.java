package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class FromSubquery extends FromTable {
   ResultSetNode subquery;
   private OrderByList orderByList;
   private ValueNode offset;
   private ValueNode fetchFirst;
   private boolean hasJDBClimitClause;
   private SchemaDescriptor origCompilationSchema = null;

   FromSubquery(ResultSetNode var1, OrderByList var2, ValueNode var3, ValueNode var4, boolean var5, String var6, ResultColumnList var7, Properties var8, ContextManager var9) {
      super(var6, var8, var9);
      this.subquery = var1;
      this.orderByList = var2;
      this.offset = var3;
      this.fetchFirst = var4;
      this.hasJDBClimitClause = var5;
      this.setResultColumns(var7);
   }

   void printSubNodes(int var1) {
   }

   ResultSetNode getSubquery() {
      return this.subquery;
   }

   FromTable getFromTableByName(String var1, String var2, boolean var3) throws StandardException {
      if (var2 != null && this.origTableName != null && !var2.equals(this.origTableName.schemaName)) {
         return null;
      } else {
         return this.getExposedName().equals(var1) ? this : null;
      }
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      if (this.tableNumber == -1) {
         this.tableNumber = this.getCompilerContext().getNextTableNumber();
      }

      this.subquery = this.subquery.bindNonVTITables(var1, var2);
      return this;
   }

   ResultSetNode bindVTITables(FromList var1) throws StandardException {
      this.subquery = this.subquery.bindVTITables(var1);
      return this;
   }

   void rejectParameters() throws StandardException {
      this.subquery.rejectParameters();
   }

   void bindExpressions(FromList var1) throws StandardException {
      FromList var2 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      ResultColumnList var3 = this.getResultColumns();
      if (this.orderByList != null) {
         this.orderByList.pullUpOrderByColumns(this.subquery);
      }

      FromList var5 = var2;
      CompilerContext var6 = this.getCompilerContext();
      if (this.origCompilationSchema != null) {
         var6.pushCompilationSchema(this.origCompilationSchema);
      }

      CollectNodesVisitor var7 = new CollectNodesVisitor(FromVTI.class);
      this.subquery.accept(var7);

      for(FromVTI var9 : var7.getList()) {
         var9.addOuterFromList(var1);
      }

      try {
         this.subquery.bindExpressions(var5);
         this.subquery.bindResultColumns(var5);
      } finally {
         if (this.origCompilationSchema != null) {
            var6.popCompilationSchema();
         }

      }

      if (this.orderByList != null) {
         this.orderByList.bindOrderByColumns(this.subquery);
      }

      bindOffsetFetch(this.offset, this.fetchFirst);
      ResultColumnList var4 = this.subquery.getResultColumns();
      if (this.getResultColumns() != null && this.getResultColumns().getCountMismatchAllowed() && this.getResultColumns().size() < var4.size()) {
         for(int var13 = var4.size() - 1; var13 >= this.getResultColumns().size(); --var13) {
            var4.removeElementAt(var13);
         }
      }

      ResultColumnList var14 = var4.copyListAndObjects();
      var14.genVirtualColumnNodes(this.subquery, this.subquery.getResultColumns());
      this.setResultColumns(var14);
      if (var3 != null) {
         this.getResultColumns().propagateDCLInfo(var3, this.correlationName);
      }

   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      ResultColumn var2 = null;
      String var3 = var1.getTableName();
      if (var1.getGeneratedToReplaceAggregate()) {
         var2 = this.getResultColumns().getResultColumn(var1.getColumnName());
      } else if (var3 == null || var3.equals(this.correlationName)) {
         var2 = this.getResultColumns().getAtMostOneResultColumn(var1, this.correlationName, false);
      }

      if (var2 != null) {
         var1.setTableNumber(this.tableNumber);
         var1.setColumnNumber(var2.getColumnPosition());
      }

      return var2;
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.subquery.pushQueryExpressionSuffix();
      if (this.orderByList != null) {
         if (this.orderByList.size() > 1) {
            this.orderByList.removeDupColumns();
         }

         this.subquery.pushOrderByList(this.orderByList);
         this.orderByList = null;
      }

      this.subquery.pushOffsetFetchFirst(this.offset, this.fetchFirst, this.hasJDBClimitClause);
      this.subquery = this.subquery.preprocess(var1, var2, var3);
      if ((var2 == null || var2.size() == 0) && this.tableProperties == null && this.subquery.flattenableInFromSubquery(var3)) {
         this.setReferencedTableMap(this.subquery.getReferencedTableMap());
         return this;
      } else {
         return this.extractSubquery(var1);
      }
   }

   ResultSetNode extractSubquery(int var1) throws StandardException {
      ProjectRestrictNode var3 = new ProjectRestrictNode(this.subquery, this.getResultColumns(), (ValueNode)null, (PredicateList)null, (SubqueryList)null, (SubqueryList)null, this.tableProperties, this.getContextManager());
      JBitSet var2 = new JBitSet(var1);
      var2.set(this.tableNumber);
      ((ResultSetNode)var3).setReferencedTableMap(var2);
      ((FromTable)var3).setTableNumber(this.tableNumber);
      return var3;
   }

   FromList flatten(ResultColumnList var1, PredicateList var2, SubqueryList var3, GroupByList var4, ValueNode var5) throws StandardException {
      FromList var6 = null;
      this.getResultColumns().setRedundant();
      this.subquery.getResultColumns().setRedundant();
      if (this.subquery instanceof SelectNode) {
         SelectNode var7 = (SelectNode)this.subquery;
         var6 = var7.getFromList();
         if (var7.getWherePredicates().size() > 0) {
            var2.destructiveAppend(var7.getWherePredicates());
         }

         if (var7.getWhereSubquerys().size() > 0) {
            var3.destructiveAppend(var7.getWhereSubquerys());
         }
      } else if (!(this.subquery instanceof RowResultSetNode)) {
      }

      var1.remapColumnReferencesToExpressions();
      var2.remapColumnReferencesToExpressions();
      if (var4 != null) {
         var4.remapColumnReferencesToExpressions();
      }

      if (var5 != null) {
         var5.remapColumnReferencesToExpressions();
      }

      return var6;
   }

   String getExposedName() {
      return this.correlationName;
   }

   ResultColumnList getAllResultColumns(TableName var1) throws StandardException {
      TableName var3;
      if (var1 != null) {
         var3 = this.makeTableName(var1.getSchemaName(), this.correlationName);
      } else {
         var3 = this.makeTableName((String)null, this.correlationName);
      }

      if (var1 != null && !var1.equals(var3)) {
         return null;
      } else {
         TableName var2 = this.makeTableName((String)null, this.correlationName);
         ResultColumnList var4 = new ResultColumnList(this.getContextManager());
         int var5 = this.getResultColumns().visibleSize();

         for(int var6 = 0; var6 < var5; ++var6) {
            ResultColumn var7 = (ResultColumn)this.getResultColumns().elementAt(var6);
            if (!var7.isGenerated()) {
               String var9 = var7.getName();
               boolean var10 = var7.isNameGenerated();
               ColumnReference var8 = new ColumnReference(var9, var2, this.getContextManager());
               var7 = new ResultColumn(var9, var8, this.getContextManager());
               var7.setNameGenerated(var10);
               var4.addResultColumn(var7);
            }
         }

         return var4;
      }
   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      return this.subquery.referencesTarget(var1, var2);
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.subquery.referencesSessionSchema();
   }

   void bindUntypedNullsToResultColumns(ResultColumnList var1) throws StandardException {
      this.subquery.bindUntypedNullsToResultColumns(var1);
   }

   void decrementLevel(int var1) {
      super.decrementLevel(var1);
      this.subquery.decrementLevel(var1);
   }

   void setOrigCompilationSchema(SchemaDescriptor var1) {
      this.origCompilationSchema = var1;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      this.subquery.accept(var1);
      if (this.orderByList != null) {
         this.orderByList.accept(var1);
      }

      if (this.offset != null) {
         this.offset.accept(var1);
      }

      if (this.fetchFirst != null) {
         this.fetchFirst.accept(var1);
      }

   }
}
