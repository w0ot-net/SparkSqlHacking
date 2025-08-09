package org.apache.derby.impl.sql.compile;

import java.util.List;
import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.ResultColumnDescriptor;
import org.apache.derby.iapi.sql.ResultDescription;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

abstract class DMLStatementNode extends StatementNode {
   ResultSetNode resultSet;

   DMLStatementNode(ResultSetNode var1, ContextManager var2) {
      super(var2);
      this.resultSet = var1;
   }

   void printSubNodes(int var1) {
   }

   ResultSetNode getResultSetNode() {
      return this.resultSet;
   }

   QueryTreeNode bind(DataDictionary var1) throws StandardException {
      this.getCompilerContext().pushCurrentPrivType(this.getPrivType());

      try {
         this.bindTables(var1);
         this.bindExpressions();
      } finally {
         this.getCompilerContext().popCurrentPrivType();
      }

      return this;
   }

   QueryTreeNode bindResultSetsWithTables(DataDictionary var1) throws StandardException {
      this.bindTables(var1);
      this.bindExpressionsWithTables();
      return this;
   }

   protected void bindTables(DataDictionary var1) throws StandardException {
      boolean var2 = this.getOptimizerFactory().doJoinOrderOptimization();
      ContextManager var3 = this.getContextManager();
      this.resultSet = this.resultSet.bindNonVTITables(var1, new FromList(var2, var3));
      this.resultSet = this.resultSet.bindVTITables(new FromList(var2, var3));
   }

   protected void bindExpressions() throws StandardException {
      FromList var1 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      this.resultSet.bindExpressions(var1);
   }

   protected void bindExpressionsWithTables() throws StandardException {
      FromList var1 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      this.resultSet.bindExpressionsWithTables(var1);
   }

   int activationKind() {
      List var1 = this.getCompilerContext().getParameterList();
      return var1 != null && !var1.isEmpty() ? 2 : 1;
   }

   public void optimizeStatement() throws StandardException {
      this.resultSet = this.resultSet.preprocess(this.getCompilerContext().getNumTables(), (GroupByList)null, (FromList)null);
      this.accept(new ConstantExpressionVisitor());
      this.resultSet = this.resultSet.optimize(this.getDataDictionary(), (PredicateList)null, (double)1.0F);
      this.resultSet = this.resultSet.modifyAccessPaths();
      if (this instanceof CursorNode) {
         ResultSetNode var3 = this.resultSet;
         ResultColumnList var1 = this.resultSet.getResultColumns();
         ResultColumnList var2 = var1.copyListAndObjects();
         this.resultSet.setResultColumns(var2);
         var1.genVirtualColumnNodes(this.resultSet, var2);
         this.resultSet = new ScrollInsensitiveResultSetNode(this.resultSet, var1, (Properties)null, this.getContextManager());
         if (var3.getReferencedTableMap() != null) {
            this.resultSet.setReferencedTableMap((JBitSet)var3.getReferencedTableMap().clone());
         }
      }

   }

   public ResultDescription makeResultDescription() {
      ResultColumnDescriptor[] var1 = this.resultSet.makeResultDescriptors();
      String var2 = this.statementToString();
      return this.getExecutionFactory().getResultDescription(var1, var2);
   }

   void generateParameterValueSet(ActivationClassBuilder var1) throws StandardException {
      List var2 = this.getCompilerContext().getParameterList();
      int var3 = var2 == null ? 0 : var2.size();
      if (var3 > 0) {
         ParameterNode.generateParameterValueSet(var1, var3, var2);
      }
   }

   public boolean isAtomic() throws StandardException {
      HasNodeVisitor var1 = new HasNodeVisitor(FromBaseTable.class, StaticMethodCallNode.class);
      this.accept(var1);
      return var1.hasNode();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.resultSet != null) {
         this.resultSet = (ResultSetNode)this.resultSet.accept(var1);
      }

   }

   int getPrivType() {
      return 0;
   }
}
