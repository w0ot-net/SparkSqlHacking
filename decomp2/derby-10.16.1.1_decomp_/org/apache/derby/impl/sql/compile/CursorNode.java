package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.impl.sql.CursorInfo;
import org.apache.derby.impl.sql.CursorTableReference;
import org.apache.derby.shared.common.error.StandardException;

public class CursorNode extends DMLStatementNode {
   static final int UNSPECIFIED = 0;
   public static final int READ_ONLY = 1;
   static final int UPDATE = 2;
   private String name;
   private OrderByList orderByList;
   private ValueNode offset;
   private ValueNode fetchFirst;
   private boolean hasJDBClimitClause;
   private String statementType;
   private int updateMode;
   private boolean needTarget;
   private List updatableColumns;
   private FromTable updateTable;
   private ArrayList statsToUpdate;
   private boolean checkIndexStats;
   private int indexOfSessionTableNamesInSavedObjects = -1;
   private boolean forMergeStatement;

   CursorNode(String var1, ResultSetNode var2, String var3, OrderByList var4, ValueNode var5, ValueNode var6, boolean var7, int var8, String[] var9, boolean var10, ContextManager var11) {
      super(var2, var11);
      this.name = var3;
      this.statementType = var1;
      this.orderByList = var4;
      this.offset = var5;
      this.fetchFirst = var6;
      this.hasJDBClimitClause = var7;
      this.updateMode = var8;
      this.updatableColumns = var9 == null ? null : Arrays.asList(var9);
      this.forMergeStatement = var10;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return this.statementType;
   }

   private static String updateModeString(int var0) {
      return "";
   }

   void printSubNodes(int var1) {
   }

   public void bindStatement() throws StandardException {
      boolean var1 = this.getCompilerContext().skipTypePrivileges(true);
      DataDictionary var2 = this.getDataDictionary();
      this.checkIndexStats = var2.getIndexStatsRefresher(true) != null;
      if (this.orderByList != null) {
         this.orderByList.pullUpOrderByColumns(this.resultSet);
      }

      this.getCompilerContext().pushCurrentPrivType(this.getPrivType());

      try {
         FromList var3 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
         this.resultSet.rejectParameters();
         super.bind(var2);
         this.resultSet.bindResultColumns(var3);
         this.resultSet.bindUntypedNullsToResultColumns((ResultColumnList)null);
         if (!this.forMergeStatement) {
            this.resultSet.rejectXMLValues();
         }
      } finally {
         this.getCompilerContext().popCurrentPrivType();
      }

      this.collectTablesWithPossiblyStaleStats();
      if (this.orderByList != null) {
         this.orderByList.bindOrderByColumns(this.resultSet);
      }

      bindOffsetFetch(this.offset, this.fetchFirst);
      if (this.updateMode == 2) {
         int var7 = this.determineUpdateMode(var2);
         if (this.updateMode != var7) {
            throw StandardException.newException("42Y90", new Object[0]);
         }
      }

      if (this.updateMode == 0) {
         if (this.getLanguageConnectionContext().getStatementContext().isForReadOnly()) {
            this.updateMode = 1;
         } else {
            this.updateMode = this.determineUpdateMode(var2);
         }
      }

      if (this.updateMode == 1) {
         this.updatableColumns = null;
      }

      if (this.updateMode == 2) {
         this.bindUpdateColumns(this.updateTable);
         if (this.updateTable != null) {
            this.updateTable.markUpdatableByCursor(this.updatableColumns);
            this.resultSet.getResultColumns().markColumnsInSelectListUpdatableByCursor(this.updatableColumns);
         }
      }

      this.resultSet.renameGeneratedResultNames();
      if (this.getLanguageConnectionContext().checkIfAnyDeclaredGlobalTempTablesForThisConnection()) {
         ArrayList var8 = this.getSessionSchemaTableNamesForCursor();
         if (var8 != null) {
            this.indexOfSessionTableNamesInSavedObjects = this.getCompilerContext().addSavedObject(var8);
         }
      }

      this.getCompilerContext().skipTypePrivileges(var1);
   }

   private void collectTablesWithPossiblyStaleStats() throws StandardException {
      if (this.checkIndexStats) {
         FromList var1 = this.resultSet.getFromList();

         for(int var2 = 0; var2 < var1.size(); ++var2) {
            FromTable var3 = (FromTable)var1.elementAt(var2);
            if (var3.isBaseTable()) {
               TableDescriptor var4 = var3.getTableDescriptor();
               if (var4.getTableType() == 0) {
                  if (this.statsToUpdate == null) {
                     this.statsToUpdate = new ArrayList();
                  }

                  this.statsToUpdate.add(var4);
               }
            }
         }

      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.resultSet.referencesSessionSchema();
   }

   protected ArrayList getSessionSchemaTableNamesForCursor() throws StandardException {
      FromList var1 = this.resultSet.getFromList();
      int var2 = var1.size();
      ArrayList var4 = null;

      for(int var5 = 0; var5 < var2; ++var5) {
         FromTable var3 = (FromTable)var1.elementAt(var5);
         if (var3 instanceof FromBaseTable && this.isSessionSchema(var3.getTableDescriptor().getSchemaDescriptor())) {
            if (var4 == null) {
               var4 = new ArrayList();
            }

            var4.add(var3.getTableName().getTableName());
         }
      }

      return var4;
   }

   private int determineUpdateMode(DataDictionary var1) throws StandardException {
      if (this.updateMode == 1) {
         return 1;
      } else if (this.orderByList != null) {
         return 1;
      } else if (!this.resultSet.isUpdatableCursor(var1)) {
         return 1;
      } else {
         this.updateTable = this.resultSet.getCursorTargetTable();
         if (this.updateTable.markAsCursorTargetTable()) {
            this.needTarget = true;
         }

         return 2;
      }
   }

   public void optimizeStatement() throws StandardException {
      this.resultSet.pushQueryExpressionSuffix();
      if (this.orderByList != null) {
         if (this.orderByList.size() > 1) {
            this.orderByList.removeDupColumns();
         }

         this.resultSet.pushOrderByList(this.orderByList);
         this.orderByList = null;
      }

      this.resultSet.pushOffsetFetchFirst(this.offset, this.fetchFirst, this.hasJDBClimitClause);
      this.offset = null;
      this.fetchFirst = null;
      super.optimizeStatement();
   }

   int activationKind() {
      return 4;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.indexOfSessionTableNamesInSavedObjects != -1) {
         MethodBuilder var3 = var1.getConstructor();
         var3.pushThis();
         var3.push(this.indexOfSessionTableNamesInSavedObjects);
         var3.putField("org.apache.derby.impl.sql.execute.BaseActivation", "indexOfSessionTableNamesInSavedObjects", "int");
         var3.endStatement();
      }

      this.generateParameterValueSet(var1);
      this.resultSet.markStatementResultSet();
      this.resultSet.generate(var1, var2);
      if (this.needTarget) {
         var1.rememberCursor(var2);
         var1.addCursorPositionCode();
      }

   }

   String getUpdateBaseTableName() {
      return this.updateTable == null ? null : this.updateTable.getBaseTableName();
   }

   String getUpdateExposedTableName() throws StandardException {
      return this.updateTable == null ? null : this.updateTable.getExposedName();
   }

   String getUpdateSchemaName() throws StandardException {
      return this.updateTable == null ? null : ((FromBaseTable)this.updateTable).getTableNameField().getSchemaName();
   }

   int getUpdateMode() {
      return this.updateMode;
   }

   public boolean needsSavepoint() {
      return false;
   }

   public Object getCursorInfo() throws StandardException {
      return !this.needTarget ? null : new CursorInfo(this.updateMode, new CursorTableReference(this.getUpdateExposedTableName(), this.getUpdateBaseTableName(), this.getUpdateSchemaName()), this.updatableColumns);
   }

   private void bindUpdateColumns(FromTable var1) throws StandardException {
      int var2 = this.updatableColumns.size();
      ResultColumnList var5 = this.resultSet.getResultColumns();

      for(int var6 = 0; var6 < var2; ++var6) {
         String var4 = (String)this.updatableColumns.get(var6);
         TableDescriptor var3 = var1.getTableDescriptor();
         if (var3.getColumnDescriptor(var4) == null) {
            throw StandardException.newException("42X04", new Object[]{var4});
         }

         for(ResultColumn var8 : var5) {
            if (var8.getSourceTableName() != null && var8.getExpression() != null && var8.getExpression().getColumnName().equals(var4) && !var8.getName().equals(var4)) {
               throw StandardException.newException("42X42", new Object[]{var4});
            }
         }
      }

   }

   String getXML() {
      return null;
   }

   public TableDescriptor[] updateIndexStatisticsFor() throws StandardException {
      if (this.checkIndexStats && this.statsToUpdate != null) {
         for(int var1 = this.statsToUpdate.size() - 1; var1 >= 0; --var1) {
            TableDescriptor var2 = (TableDescriptor)this.statsToUpdate.get(var1);
            if (var2.getAndClearIndexStatsIsUpToDate()) {
               this.statsToUpdate.remove(var1);
            }
         }

         if (this.statsToUpdate.isEmpty()) {
            return EMPTY_TD_LIST;
         } else {
            TableDescriptor[] var3 = new TableDescriptor[this.statsToUpdate.size()];
            this.statsToUpdate.toArray(var3);
            this.statsToUpdate.clear();
            return var3;
         }
      } else {
         return EMPTY_TD_LIST;
      }
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.orderByList != null) {
         this.orderByList.acceptChildren(var1);
      }

      if (this.offset != null) {
         this.offset.acceptChildren(var1);
      }

      if (this.fetchFirst != null) {
         this.fetchFirst.acceptChildren(var1);
      }

   }
}
