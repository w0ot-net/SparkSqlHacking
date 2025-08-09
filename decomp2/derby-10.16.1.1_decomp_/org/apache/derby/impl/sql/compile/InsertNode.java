package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.IgnoreFilter;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexLister;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.ExecRowBuilder;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.impl.sql.execute.FKInfo;
import org.apache.derby.shared.common.error.StandardException;

public final class InsertNode extends DMLModGeneratedColumnsStatementNode {
   private ResultColumnList targetColumnList;
   private boolean deferred;
   public ValueNode checkConstraints;
   public boolean hasDeferrableCheckConstraints;
   public Properties targetProperties;
   public FKInfo fkInfo;
   protected boolean bulkInsert;
   private boolean bulkInsertReplace;
   private OrderByList orderByList;
   private ValueNode offset;
   private ValueNode fetchFirst;
   private boolean hasJDBClimitClause;

   InsertNode(QueryTreeNode var1, ResultColumnList var2, ResultSetNode var3, MatchingClauseNode var4, Properties var5, OrderByList var6, ValueNode var7, ValueNode var8, boolean var9, ContextManager var10) {
      super(var3, var4, getStatementType(var5), var10);
      this.setTarget(var1);
      this.targetColumnList = var2;
      this.targetProperties = var5;
      this.orderByList = var6;
      this.offset = var7;
      this.fetchFirst = var8;
      this.hasJDBClimitClause = var9;
      this.getResultSetNode().setInsertSource();
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "INSERT";
   }

   void printSubNodes(int var1) {
   }

   public void bindStatement() throws StandardException {
      this.getCompilerContext().pushCurrentPrivType(0);
      FromList var1 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      DataDictionary var2 = this.getDataDictionary();
      super.bindResultSetsWithTables(var2);
      this.verifyTargetTable();
      if (this.targetProperties != null) {
         this.verifyTargetProperties(var2);
      }

      IgnoreFilter var3 = new IgnoreFilter();
      this.getCompilerContext().addPrivilegeFilter(var3);
      this.getResultColumnList();
      if (this.targetColumnList != null) {
         if (this.synonymTableName != null) {
            this.normalizeSynonymColumns(this.targetColumnList, this.targetTableName);
         }

         this.getCompilerContext().pushCurrentPrivType(this.getPrivType());
         if (this.targetTableDescriptor != null) {
            this.targetColumnList.bindResultColumnsByName(this.targetTableDescriptor, this);
         } else {
            this.targetColumnList.bindResultColumnsByName(this.targetVTI.getResultColumns(), this.targetVTI, this);
         }

         this.getCompilerContext().popCurrentPrivType();
      }

      this.getCompilerContext().removePrivilegeFilter(var3);
      boolean var4 = this.resultSet instanceof UnionNode && ((UnionNode)this.resultSet).tableConstructor() || this.resultSet instanceof RowResultSetNode;
      ResultColumnList var5 = this.resultSet.getResultColumns();
      boolean var6 = false;

      for(int var7 = 0; var7 < var5.size(); ++var7) {
         ResultColumn var8 = var5.getResultColumn(var7 + 1);
         if (var8.wasDefaultColumn()) {
            var6 = true;
         }
      }

      this.resultSet.replaceOrForbidDefaults(this.targetTableDescriptor, this.targetColumnList, var4);
      super.bindExpressions();
      if (this.isPrivilegeCollectionRequired()) {
         this.getCompilerContext().pushCurrentPrivType(this.getPrivType());
         this.getCompilerContext().addRequiredTablePriv(this.targetTableDescriptor);
         this.getCompilerContext().popCurrentPrivType();
      }

      this.getCompilerContext().addPrivilegeFilter(var3);
      if (this.targetColumnList != null) {
         if (this.resultSet.getResultColumns().visibleSize() > this.targetColumnList.size()) {
            throw StandardException.newException("42802", new Object[0]);
         }

         this.resultSet.bindUntypedNullsToResultColumns(this.targetColumnList);
         this.resultSet.setTableConstructorTypes(this.targetColumnList);
      } else {
         if (this.resultSet.getResultColumns().visibleSize() > this.resultColumnList.size()) {
            throw StandardException.newException("42802", new Object[0]);
         }

         this.resultSet.bindUntypedNullsToResultColumns(this.resultColumnList);
         this.resultSet.setTableConstructorTypes(this.resultColumnList);
      }

      this.resultSet.bindResultColumns(var1);
      int var15 = this.resultSet.getResultColumns().visibleSize();
      DataDictionary var16 = this.getDataDictionary();
      if (this.targetColumnList != null) {
         if (this.targetColumnList.size() != var15) {
            throw StandardException.newException("42802", new Object[0]);
         }
      } else if (this.targetTableDescriptor != null && this.targetTableDescriptor.getNumberOfColumns() != var15) {
         throw StandardException.newException("42802", new Object[0]);
      }

      boolean var9 = true;
      int var10 = this.resultColumnList.size();
      int[] var11 = new int[var10];

      for(int var12 = 0; var12 < var11.length; ++var12) {
         var11[var12] = -1;
      }

      if (this.targetColumnList != null) {
         int var17 = this.targetColumnList.size();

         int var14;
         for(int var13 = 0; var13 < var17; var11[var14 - 1] = var13++) {
            var14 = ((ResultColumn)this.targetColumnList.elementAt(var13)).getColumnDescriptor().getPosition();
            if (var13 != var14 - 1) {
               var9 = false;
            }
         }
      } else {
         for(int var18 = 0; var18 < this.resultSet.getResultColumns().visibleSize(); var11[var18] = var18++) {
         }
      }

      if (this.orderByList != null) {
         this.orderByList.pullUpOrderByColumns(this.resultSet);
         super.bindExpressions();
         this.orderByList.bindOrderByColumns(this.resultSet);
      }

      bindOffsetFetch(this.offset, this.fetchFirst);
      this.resultSet = this.enhanceAndCheckForAutoincrement(this.resultSet, var9, var11, var6);
      this.resultColumnList.checkStorableExpressions(this.resultSet.getResultColumns());
      if (!this.resultColumnList.columnTypesAndLengthsMatch(this.resultSet.getResultColumns())) {
         this.resultSet = new NormalizeResultSetNode(this.resultSet, this.resultColumnList, (Properties)null, false, this.getContextManager());
      }

      if (this.targetTableDescriptor != null) {
         ResultColumnList var19 = this.resultSet.getResultColumns();
         var19.copyResultColumnNames(this.resultColumnList);
         this.parseAndBindGenerationClauses(var2, this.targetTableDescriptor, var19, this.resultColumnList, false, (ResultSetNode)null);
         boolean[] var20 = new boolean[]{false};
         this.checkConstraints = this.bindConstraints(var2, this.getOptimizerFactory(), this.targetTableDescriptor, (Dependent)null, var19, (int[])null, (FormatableBitSet)null, true, var20);
         this.hasDeferrableCheckConstraints = var20[0];
         if (this.resultSet.referencesTarget(this.targetTableDescriptor.getName(), true) || this.requiresDeferredProcessing()) {
            this.deferred = true;
            if (this.bulkInsertReplace && this.resultSet.referencesTarget(this.targetTableDescriptor.getName(), true)) {
               throw StandardException.newException("42Y38", new Object[]{this.targetTableDescriptor.getQualifiedName()});
            }
         }

         this.getAffectedIndexes(this.targetTableDescriptor);
         TransactionController var21 = this.getLanguageConnectionContext().getTransactionCompile();
         this.autoincRowLocation = var16.computeAutoincRowLocations(var21, this.targetTableDescriptor);
      } else {
         this.deferred = VTIDeferModPolicy.deferIt(1, this.targetVTI, (String[])null, this.resultSet);
      }

      this.identitySequenceUUIDString = this.getUUIDofSequenceGenerator();
      this.getCompilerContext().removePrivilegeFilter(var3);
      this.getCompilerContext().popCurrentPrivType();
   }

   ResultSetNode enhanceAndCheckForAutoincrement(ResultSetNode var1, boolean var2, int[] var3, boolean var4) throws StandardException {
      var1 = var1.enhanceRCLForInsert(this, var2, var3);
      if (!(var1 instanceof UnionNode) || !((UnionNode)var1).tableConstructor()) {
         this.resultColumnList.forbidOverrides(var1.getResultColumns(), var4);
      }

      return var1;
   }

   int getPrivType() {
      return 3;
   }

   public boolean referencesSessionSchema() throws StandardException {
      boolean var1 = false;
      if (this.targetTableDescriptor != null) {
         var1 = this.isSessionSchema(this.targetTableDescriptor.getSchemaDescriptor());
      }

      if (!var1) {
         var1 = this.resultSet.referencesSessionSchema();
      }

      return var1;
   }

   private void verifyTargetProperties(DataDictionary var1) throws StandardException {
      String var2 = this.targetProperties.getProperty("insertMode");
      if (var2 != null) {
         String var3 = StringUtil.SQLToUpperCase(var2);
         if (!var3.equals("BULKINSERT") && !var3.equals("REPLACE")) {
            throw StandardException.newException("42X60", new Object[]{var2, this.targetTableName});
         }

         if (!this.verifyBulkInsert(var1, var3)) {
            this.targetProperties.remove("insertMode");
         } else {
            this.bulkInsert = true;
            if (var3.equals("REPLACE")) {
               this.bulkInsertReplace = true;
            }

            String var4 = this.targetProperties.getProperty("bulkFetch");
            if (var4 != null) {
               int var5 = this.getIntProperty(var4, "bulkFetch");
               if (var5 <= 0) {
                  throw StandardException.newException("42Y64", new Object[]{String.valueOf(var5)});
               }
            }
         }
      }

   }

   private boolean verifyBulkInsert(DataDictionary var1, String var2) throws StandardException {
      return true;
   }

   public ConstantAction makeConstantAction() throws StandardException {
      if (this.targetTableDescriptor == null) {
         return this.getGenericConstantActionFactory().getUpdatableVTIConstantAction(1, this.deferred);
      } else {
         long var1 = this.targetTableDescriptor.getHeapConglomerateId();
         TransactionController var3 = this.getLanguageConnectionContext().getTransactionCompile();
         int var4 = this.targetTableDescriptor != null ? this.indexConglomerateNumbers.length : 0;
         StaticCompiledOpenConglomInfo[] var5 = new StaticCompiledOpenConglomInfo[var4];

         for(int var6 = 0; var6 < var4; ++var6) {
            var5[var6] = var3.getStaticCompiledConglomInfo(this.indexConglomerateNumbers[var6]);
         }

         if (this.bulkInsert || this.targetTableDescriptor.getLockGranularity() == 'T') {
            this.lockMode = 7;
         }

         return this.getGenericConstantActionFactory().getInsertConstantAction(this.targetTableDescriptor, var1, var3.getStaticCompiledConglomInfo(var1), this.indicesToMaintain, this.indexConglomerateNumbers, var5, this.indexNames, this.deferred, false, this.hasDeferrableCheckConstraints, this.targetTableDescriptor.getUUID(), this.lockMode, (Object)null, (Object)null, this.targetProperties, this.getFKInfo(), this.getTriggerInfo(), this.resultColumnList.getStreamStorableColIds(this.targetTableDescriptor.getNumberOfColumns()), this.getIndexedCols(), (UUID)null, (Object[])null, (Object[])null, this.resultSet.isOneRowResultSet(), this.autoincRowLocation, this.inMatchingClause(), this.identitySequenceUUIDString);
      }
   }

   boolean[] getIndexedCols() throws StandardException {
      boolean[] var1 = new boolean[this.targetTableDescriptor.getNumberOfColumns()];

      for(int var2 = 0; var2 < this.indicesToMaintain.length; ++var2) {
         int[] var3 = this.indicesToMaintain[var2].getIndexDescriptor().baseColumnPositions();

         for(int var4 = 0; var4 < var3.length; ++var4) {
            var1[var3[var4] - 1] = true;
         }
      }

      return var1;
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
      super.optimizeStatement();
      HasTableFunctionVisitor var1 = new HasTableFunctionVisitor();
      this.accept(var1);
      if (var1.hasNode() && !this.isSessionSchema(this.targetTableDescriptor.getSchemaDescriptor())) {
         this.requestBulkInsert();
      }

   }

   private void requestBulkInsert() {
      if (this.targetProperties == null) {
         this.targetProperties = new Properties();
      }

      String var1 = "insertMode";
      String var2 = "bulkInsert";
      if (this.targetProperties.getProperty(var1) == null) {
         this.targetProperties.put(var1, var2);
      }

      this.bulkInsert = true;
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateCodeForTemporaryTable(var1);
      this.generateParameterValueSet(var1);
      if (this.targetTableDescriptor != null) {
         var1.pushGetResultSetFactoryExpression(var2);
         if (this.inMatchingClause()) {
            this.matchingClause.generateResultSetField(var1, var2);
         } else {
            this.resultSet.generate(var1, var2);
         }

         this.generateGenerationClauses(this.resultColumnList, this.resultSet.getResultSetNumber(), false, var1, var2);
         this.generateCheckConstraints(this.checkConstraints, var1, var2);
         if (!this.bulkInsert) {
            var2.push((int)-1);
         } else {
            ColumnDescriptorList var3 = this.targetTableDescriptor.getColumnDescriptorList();
            ExecRowBuilder var4 = new ExecRowBuilder(var3.size(), false);

            for(int var5 = 0; var5 < var3.size(); ++var5) {
               ColumnDescriptor var6 = (ColumnDescriptor)var3.get(var5);
               var4.setColumn(var5 + 1, var6.getType());
            }

            var2.push(var1.addItem(var4));
         }

         if (this.targetTableName.getSchemaName() == null) {
            var2.pushNull("java.lang.String");
         } else {
            var2.push(this.targetTableName.getSchemaName());
         }

         var2.push(this.targetTableName.getTableName());
         var2.callMethod((short)185, (String)null, "getInsertResultSet", "org.apache.derby.iapi.sql.ResultSet", 6);
      } else {
         this.targetVTI.assignCostEstimate(this.resultSet.getNewCostEstimate());
         var1.pushGetResultSetFactoryExpression(var2);
         this.resultSet.generate(var1, var2);
         this.targetVTI.generate(var1, var2);
         var2.callMethod((short)185, (String)null, "getInsertVTIResultSet", "org.apache.derby.iapi.sql.ResultSet", 2);
      }

   }

   protected final int getStatementType() {
      return 1;
   }

   static int getStatementType(Properties var0) {
      byte var1 = 1;
      String var2 = var0 == null ? null : var0.getProperty("insertMode");
      if (var2 != null) {
         String var3 = StringUtil.SQLToUpperCase(var2);
         if (var3.equals("REPLACE")) {
            var1 = 2;
         }
      }

      return var1;
   }

   private void getAffectedIndexes(TableDescriptor var1) throws StandardException {
      IndexLister var2 = var1.getIndexLister();
      this.indicesToMaintain = var2.getDistinctIndexRowGenerators();
      this.indexConglomerateNumbers = var2.getDistinctIndexConglomerateNumbers();
      this.indexNames = var2.getDistinctIndexNames();
      ConglomerateDescriptor[] var3 = var1.getConglomerateDescriptors();
      CompilerContext var4 = this.getCompilerContext();

      for(int var5 = 0; var5 < var3.length; ++var5) {
         var4.createDependency(var3[var5]);
      }

   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.targetColumnList != null) {
         this.targetColumnList.accept(var1);
      }

   }
}
