package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.OptimizablePredicateList;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.compile.RowOrdering;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.CursorActivation;
import org.apache.derby.iapi.sql.execute.ExecCursorTableReference;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public final class CurrentOfNode extends FromTable {
   private String cursorName;
   private ExecPreparedStatement preStmt;
   private TableName exposedTableName;
   private TableName baseTableName;
   private CostEstimate singleScanCostEstimate;
   private FromBaseTable dummyTargetTable;

   CurrentOfNode(String var1, String var2, Properties var3, ContextManager var4) {
      super(var1, var3, var4);
      this.cursorName = var2;
   }

   static CurrentOfNode makeForMerge(String var0, FromBaseTable var1, ContextManager var2) {
      CurrentOfNode var3 = new CurrentOfNode((String)null, var0, (Properties)null, var2);
      var3.dummyTargetTable = var1;
      return var3;
   }

   public CostEstimate estimateCost(OptimizablePredicateList var1, ConglomerateDescriptor var2, CostEstimate var3, Optimizer var4, RowOrdering var5) throws StandardException {
      if (this.singleScanCostEstimate == null) {
         this.singleScanCostEstimate = this.getOptimizerFactory().getCostEstimate();
      }

      this.singleScanCostEstimate.setCost((double)0.0F, (double)1.0F, (double)1.0F);
      this.getBestAccessPath().setCostEstimate(this.singleScanCostEstimate);
      this.getBestSortAvoidancePath().setCostEstimate(this.singleScanCostEstimate);
      return this.singleScanCostEstimate;
   }

   ResultSetNode bindNonVTITables(DataDictionary var1, FromList var2) throws StandardException {
      this.preStmt = this.getCursorStatement();
      if (this.preStmt == null) {
         throw StandardException.newException("42X30", new Object[]{this.cursorName});
      } else {
         this.preStmt.rePrepare(this.getLanguageConnectionContext());
         if (this.preStmt.getUpdateMode() != 2) {
            String var13 = this.cursorName == null ? "" : this.cursorName;
            throw StandardException.newException("42X23", new Object[]{var13});
         } else {
            ExecCursorTableReference var3 = this.preStmt.getTargetTable();
            String var4 = var3.getSchemaName();
            this.exposedTableName = this.makeTableName((String)null, var3.getExposedName());
            this.baseTableName = this.makeTableName(var4, var3.getBaseName());
            SchemaDescriptor var5 = this.getSchemaDescriptor(var3.getSchemaName());
            if (var5 == null) {
               throw StandardException.newException("42Y07", new Object[]{var3.getSchemaName()});
            } else {
               TableDescriptor var6 = this.getTableDescriptor(var3.getBaseName(), var5);
               if (var6 == null) {
                  throw StandardException.newException("42X05", new Object[]{var3.getBaseName()});
               } else {
                  this.setResultColumns(new ResultColumnList(this.getContextManager()));
                  ColumnDescriptorList var7 = var6.getColumnDescriptorList();
                  int var8 = var7.size();

                  for(int var9 = 0; var9 < var8; ++var9) {
                     ColumnDescriptor var10 = var7.elementAt(var9);
                     BaseColumnNode var11 = new BaseColumnNode(var10.getColumnName(), this.exposedTableName, var10.getType(), this.getContextManager());
                     ResultColumn var12 = new ResultColumn(var10, var11, this.getContextManager());
                     this.getResultColumns().addResultColumn(var12);
                  }

                  if (this.tableNumber == -1) {
                     this.tableNumber = this.getCompilerContext().getNextTableNumber();
                  }

                  return this;
               }
            }
         }
      }
   }

   void bindExpressions(FromList var1) {
   }

   ResultColumn getMatchingColumn(ColumnReference var1) throws StandardException {
      if (this.dummyTargetTable != null) {
         return this.dummyTargetTable.getMatchingColumn(var1);
      } else {
         ResultColumn var2 = null;
         TableName var3 = var1.getQualifiedTableName();
         if (var3 != null && var3.getSchemaName() == null && this.correlationName == null) {
            var3.bind();
         }

         if (this.baseTableName != null && this.baseTableName.getSchemaName() == null && this.correlationName == null) {
            this.baseTableName.bind();
         }

         if (var3 == null || var3.getFullTableName().equals(this.baseTableName.getFullTableName()) || this.correlationName != null && this.correlationName.equals(var3.getTableName())) {
            var2 = this.getResultColumns().getResultColumn(var1.getColumnName());
            boolean var4;
            if (var2 == null) {
               var4 = true;
            } else {
               var1.setTableNumber(this.tableNumber);
               var1.setColumnNumber(var2.getColumnPosition());
               var4 = var2.updatableByCursor() && !this.preStmt.isUpdateColumn(var1.getColumnName());
            }

            if (var4) {
               String var5 = this.cursorName == null ? "" : this.cursorName;
               throw StandardException.newException("42X31", new Object[]{var1.getColumnName(), var5});
            }
         }

         return var2;
      }
   }

   ResultSetNode preprocess(int var1, GroupByList var2, FromList var3) throws StandardException {
      this.setReferencedTableMap(new JBitSet(var1));
      return this;
   }

   ResultSetNode optimize(DataDictionary var1, PredicateList var2, double var3) throws StandardException {
      this.bestCostEstimate = this.getOptimizerFactory().getCostEstimate();
      this.bestCostEstimate.setCost((double)0.0F, var3, var3);
      return this;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.assignResultSetNumber();
      var2.pushThis();
      var1.pushGetResultSetFactoryExpression(var2);
      var2.push(this.cursorName);
      var1.pushThisAsActivation(var2);
      var2.push(this.getResultSetNumber());
      var2.callMethod((short)185, (String)null, "getCurrentOfResultSet", "org.apache.derby.iapi.sql.execute.NoPutResultSet", 3);
      var2.cast("org.apache.derby.iapi.sql.execute.CursorResultSet");
      var2.putField((String)null, var1.getRowLocationScanResultSetName(), "org.apache.derby.iapi.sql.execute.CursorResultSet");
      var2.cast("org.apache.derby.iapi.sql.execute.NoPutResultSet");
      MethodBuilder var3 = var1.startResetMethod();
      var3.pushThis();
      var3.push(this.cursorName);
      var3.push(this.preStmt.getObjectName());
      var3.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "checkPositionedStatement", "void", 2);
      var3.methodReturn();
      var3.complete();
   }

   void printSubNodes(int var1) {
   }

   public String toString() {
      return "";
   }

   String getExposedName() {
      return this.dummyTargetTable != null ? this.dummyTargetTable.getExposedName() : this.exposedTableName.getFullTableName();
   }

   public int updateTargetLockMode() {
      return 6;
   }

   TableName getExposedTableName() {
      return this.exposedTableName;
   }

   TableName getBaseCursorTargetTableName() {
      return this.baseTableName;
   }

   String getCursorName() {
      return this.cursorName;
   }

   ExecPreparedStatement getCursorStatement() {
      CursorActivation var1 = this.getLanguageConnectionContext().lookupCursorActivation(this.cursorName);
      return var1 == null ? null : var1.getPreparedStatement();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.exposedTableName != null) {
         this.exposedTableName = (TableName)this.exposedTableName.accept(var1);
      }

      if (this.baseTableName != null) {
         this.baseTableName = (TableName)this.baseTableName.accept(var1);
      }

   }
}
