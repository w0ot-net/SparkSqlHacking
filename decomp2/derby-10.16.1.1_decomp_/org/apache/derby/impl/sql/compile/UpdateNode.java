package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.TagFilter;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.dictionary.CheckConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptorList;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.store.access.StaticCompiledOpenConglomInfo;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class UpdateNode extends DMLModGeneratedColumnsStatementNode {
   int[] changedColumnIds;
   boolean deferred;
   ValueNode checkConstraints;
   protected FromTable targetTable;
   protected FormatableBitSet readColsBitSet;
   protected boolean positionedUpdate;
   static final String COLUMNNAME = "###RowLocationToUpdate";

   UpdateNode(TableName var1, ResultSetNode var2, MatchingClauseNode var3, ContextManager var4) {
      super(var2, var3, var4);
      this.targetTableName = var1;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "UPDATE";
   }

   void printSubNodes(int var1) {
   }

   public void bindStatement() throws StandardException {
      this.getCompilerContext().pushCurrentPrivType(0);
      FromList var1 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      TableName var2 = null;
      CurrentOfNode var3 = null;
      ResultColumnList var4 = null;
      DataDictionary var5 = this.getDataDictionary();
      if (this.targetTableName != null) {
         TableName var6 = this.resolveTableToSynonym(this.targetTableName);
         if (var6 != null) {
            this.synonymTableName = this.targetTableName;
            this.targetTableName = var6;
         }
      }

      if (this.inMatchingClause()) {
         this.tagOriginalResultSetColumns();
      }

      ArrayList var22 = this.getExplicitlySetColumns();
      List var7 = this.collectAllCastNodes();
      this.tagPrivilegedNodes();
      TagFilter var8 = new TagFilter("updatePrivs");
      this.getCompilerContext().addPrivilegeFilter(var8);
      this.bindTables(var5);
      SelectNode var9 = (SelectNode)this.resultSet;
      this.targetTable = (FromTable)var9.fromList.elementAt(0);
      if (this.targetTable instanceof CurrentOfNode) {
         this.positionedUpdate = true;
         var3 = (CurrentOfNode)this.targetTable;
         var2 = var3.getBaseCursorTargetTableName();
      }

      if (this.targetTable instanceof FromVTI) {
         this.targetVTI = (FromVTI)this.targetTable;
         this.targetVTI.setTarget();
      } else if (this.targetTableName == null) {
         this.targetTableName = var2;
      } else if (var2 != null && !this.targetTableName.equals(var2)) {
         throw StandardException.newException("42X29", new Object[]{this.targetTableName, var3.getCursorName()});
      }

      this.verifyTargetTable();
      this.addUpdatePriv(var22);
      ColumnDescriptorList var10 = new ColumnDescriptorList();
      ColumnDescriptorList var11 = new ColumnDescriptorList();
      this.addGeneratedColumns(this.targetTableDescriptor, this.resultSet, var11, var10);
      this.resultSet.getResultColumns().markUpdated();
      if (this.synonymTableName != null) {
         this.normalizeSynonymColumns(this.resultSet.getResultColumns(), this.targetTable);
      }

      this.normalizeCorrelatedColumns(this.resultSet.getResultColumns(), this.targetTable);
      this.resultSet.bindResultColumns(this.targetTableDescriptor, this.targetVTI, this.resultSet.getResultColumns(), this, var1);
      this.forbidGenerationOverrides(this.resultSet.getResultColumns(), var10);
      if (var5.checkVersion(230, (String)null)) {
         this.resultSet.getResultColumns().replaceOrForbidDefaults(this.targetTableDescriptor, this.resultSet.getResultColumns(), true);
         this.resultSet.getResultColumns().checkForInvalidDefaults();
         this.resultSet.getResultColumns().forbidOverrides(this.resultSet.getResultColumns());
      } else {
         LanguageConnectionContext var12 = this.getLanguageConnectionContext();
         if (!var12.getAutoincrementUpdate()) {
            this.resultSet.getResultColumns().forbidOverrides((ResultColumnList)null);
         }
      }

      boolean var23 = false;
      if (this.targetTable instanceof FromBaseTable) {
         ((FromBaseTable)this.targetTable).markUpdated(this.resultSet.getResultColumns());
      } else if (!(this.targetTable instanceof FromVTI) && !(this.targetTable instanceof FromSubquery)) {
         ExecPreparedStatement var13 = var3.getCursorStatement();
         if (!var13.hasUpdateColumns()) {
            this.getResultColumnList();
            var4 = this.resultSet.getResultColumns().expandToAll(this.targetTableDescriptor, this.targetTable.getTableName());
            this.getAffectedIndexes(this.targetTableDescriptor, (ResultColumnList)null, (FormatableBitSet)null);
            var23 = true;
         } else {
            this.resultSet.getResultColumns().checkColumnUpdateability(var13, var3.getCursorName());
         }
      } else {
         this.resultColumnList = this.resultSet.getResultColumns();
      }

      this.changedColumnIds = this.getChangedColumnIds(this.resultSet.getResultColumns());
      boolean var24 = this.targetVTI == null || this.inMatchingClause();
      if (!var23 && var24) {
         this.getCompilerContext().pushCurrentPrivType(-1);

         try {
            this.readColsBitSet = new FormatableBitSet();
            FromBaseTable var14 = this.getResultColumnList(this.resultSet.getResultColumns());
            var4 = this.resultSet.getResultColumns().copyListAndObjects();
            this.readColsBitSet = this.getReadMap(var5, this.targetTableDescriptor, var4, var11);
            var4 = var14.addColsToList(var4, this.readColsBitSet);
            this.resultColumnList = var14.addColsToList(this.resultColumnList, this.readColsBitSet);
            int var15 = 1;

            int var16;
            for(var16 = this.targetTableDescriptor.getMaxColumnID(); var15 <= var16 && this.readColsBitSet.get(var15); ++var15) {
            }

            if (var15 > var16) {
               this.readColsBitSet = null;
            }
         } finally {
            this.getCompilerContext().popCurrentPrivType();
         }
      }

      Object var25;
      if (var24) {
         this.resultColumnList.appendResultColumns(var4, false);
         var25 = new CurrentRowLocationNode(this.getContextManager());
      } else {
         var25 = new NumericConstantNode(TypeId.getBuiltInTypeId(4), 0, this.getContextManager());
      }

      ResultColumn var26 = new ResultColumn("###RowLocationToUpdate", (ValueNode)var25, this.getContextManager());
      var26.markGenerated();
      this.resultColumnList.addResultColumn(var26);
      this.checkTableNameAndScrubResultColumns(this.resultColumnList);
      this.resultSet.setResultColumns(this.resultColumnList);
      if (this.inMatchingClause()) {
         this.associateAddedColumns();
      }

      SelectNode.checkNoWindowFunctions(this.resultSet, "<update source>");
      super.bindExpressions();
      this.resultSet.getResultColumns().bindUntypedNullsToResultColumns(this.resultColumnList);
      var26.bindResultColumnToExpression();
      this.resultColumnList.checkStorableExpressions();
      if (!this.resultColumnList.columnTypesAndLengthsMatch()) {
         this.resultSet = new NormalizeResultSetNode(this.resultSet, this.resultColumnList, (Properties)null, true, this.getContextManager());
         if (this.hasCheckConstraints(var5, this.targetTableDescriptor) || this.hasGenerationClauses(this.targetTableDescriptor)) {
            int var27 = var4.size();
            var4 = new ResultColumnList(this.getContextManager());
            ResultColumnList var17 = this.resultSet.getResultColumns();

            for(int var18 = 0; var18 < var27; ++var18) {
               var4.addElement((ResultColumn)var17.elementAt(var18 + var27));
            }
         }
      }

      if (null != this.targetVTI && !this.inMatchingClause()) {
         this.deferred = VTIDeferModPolicy.deferIt(2, this.targetVTI, this.resultColumnList.getColumnNames(), var9.getWhereClause());
      } else {
         boolean var28 = this.getAllRelevantTriggers(var5, this.targetTableDescriptor, this.changedColumnIds, true).size() > 0;
         ResultColumnList var30 = var28 ? this.resultColumnList : var4;
         this.parseAndBindGenerationClauses(var5, this.targetTableDescriptor, var4, this.resultColumnList, true, this.resultSet);
         this.checkConstraints = this.bindConstraints(var5, this.getOptimizerFactory(), this.targetTableDescriptor, (Dependent)null, var30, this.changedColumnIds, this.readColsBitSet, true, new boolean[1]);
         if (this.resultSet.subqueryReferencesTarget(this.targetTableDescriptor.getName(), true) || this.requiresDeferredProcessing()) {
            this.deferred = true;
         }

         TransactionController var32 = this.getLanguageConnectionContext().getTransactionCompile();
         this.autoincRowLocation = var5.computeAutoincRowLocations(var32, this.targetTableDescriptor);
      }

      this.identitySequenceUUIDString = this.getUUIDofSequenceGenerator();
      this.getCompilerContext().popCurrentPrivType();
      this.getCompilerContext().removePrivilegeFilter(var8);

      for(CastNode var31 : var7) {
         this.addUDTUsagePriv(var31);
      }

   }

   int getPrivType() {
      return 1;
   }

   private ArrayList getExplicitlySetColumns() throws StandardException {
      ArrayList var1 = new ArrayList();
      ResultColumnList var2 = this.resultSet.getResultColumns();

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         var1.add(((ResultColumn)var2.elementAt(var3)).getName());
      }

      return var1;
   }

   private void associateAddedColumns() throws StandardException {
      for(ColumnReference var2 : this.collectAllResultSetColumns()) {
         if (!var2.taggedWith("origUpdateCol")) {
            var2.setMergeTableID(2);
         }
      }

   }

   private void tagOriginalResultSetColumns() throws StandardException {
      for(ColumnReference var2 : this.collectAllResultSetColumns()) {
         var2.addTag("origUpdateCol");
      }

   }

   private List collectAllResultSetColumns() throws StandardException {
      CollectNodesVisitor var1 = new CollectNodesVisitor(ColumnReference.class);
      this.resultSet.getResultColumns().accept(var1);
      return var1.getList();
   }

   private List collectAllCastNodes() throws StandardException {
      CollectNodesVisitor var1 = new CollectNodesVisitor(CastNode.class);
      ValueNode var2 = ((SelectNode)this.resultSet).whereClause;
      if (var2 != null) {
         var2.accept(var1);
      }

      ResultColumnList var3 = this.resultSet.getResultColumns();

      for(int var4 = 0; var4 < var3.size(); ++var4) {
         ((ResultColumn)var3.elementAt(var4)).getExpression().accept(var1);
      }

      return var1.getList();
   }

   private void tagPrivilegedNodes() throws StandardException {
      ArrayList var1 = new ArrayList();
      SelectNode var2 = (SelectNode)this.resultSet;
      var1.add(this);
      ValueNode var3 = var2.whereClause;
      if (var3 != null) {
         this.collectPrivilegedNodes(var1, var3);
      }

      ResultColumnList var4 = this.resultSet.getResultColumns();

      for(int var5 = 0; var5 < var4.size(); ++var5) {
         this.collectPrivilegedNodes(var1, ((ResultColumn)var4.elementAt(var5)).getExpression());
      }

      for(QueryTreeNode var6 : var1) {
         var6.addTag("updatePrivs");
      }

   }

   private void collectPrivilegedNodes(ArrayList var1, QueryTreeNode var2) throws StandardException {
      CollectNodesVisitor var3 = new CollectNodesVisitor(ColumnReference.class);
      var2.accept(var3);
      var1.addAll(var3.getList());
      CollectNodesVisitor var4 = new CollectNodesVisitor(StaticMethodCallNode.class);
      var2.accept(var4);
      var1.addAll(var4.getList());
      CollectNodesVisitor var5 = new CollectNodesVisitor(FromBaseTable.class);
      var2.accept(var5);
      var1.addAll(var5.getList());
   }

   private void addUpdatePriv(ArrayList var1) throws StandardException {
      if (this.isPrivilegeCollectionRequired()) {
         CompilerContext var2 = this.getCompilerContext();
         var2.pushCurrentPrivType(1);

         try {
            for(String var4 : var1) {
               ColumnDescriptor var5 = this.targetTableDescriptor.getColumnDescriptor(var4);
               var2.addRequiredColumnPriv(var5);
            }
         } finally {
            var2.popCurrentPrivType();
         }

      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.resultSet.referencesSessionSchema();
   }

   public ConstantAction makeConstantAction() throws StandardException {
      if (!this.deferred && !this.inMatchingClause()) {
         ConglomerateDescriptor var1 = this.targetTable.getTrulyTheBestAccessPath().getConglomerateDescriptor();
         if (var1 != null && var1.isIndex()) {
            int[] var2 = var1.getIndexDescriptor().baseColumnPositions();
            if (this.resultSet.getResultColumns().updateOverlaps(var2)) {
               this.deferred = true;
            }
         }
      }

      if (null == this.targetTableDescriptor) {
         return this.getGenericConstantActionFactory().getUpdatableVTIConstantAction(2, this.deferred, this.changedColumnIds);
      } else {
         int var7 = this.inMatchingClause() ? 6 : this.resultSet.updateTargetLockMode();
         long var8 = this.targetTableDescriptor.getHeapConglomerateId();
         TransactionController var4 = this.getLanguageConnectionContext().getTransactionCompile();
         StaticCompiledOpenConglomInfo[] var5 = new StaticCompiledOpenConglomInfo[this.indexConglomerateNumbers.length];

         for(int var6 = 0; var6 < var5.length; ++var6) {
            var5[var6] = var4.getStaticCompiledConglomInfo(this.indexConglomerateNumbers[var6]);
         }

         if (this.targetTableDescriptor.getLockGranularity() == 'T') {
            var7 = 7;
         }

         return this.getGenericConstantActionFactory().getUpdateConstantAction(this.targetTableDescriptor, var4.getStaticCompiledConglomInfo(var8), this.indicesToMaintain, this.indexConglomerateNumbers, var5, this.indexNames, this.deferred, this.targetTableDescriptor.getUUID(), var7, false, this.changedColumnIds, (int[])null, (Object)null, this.getFKInfo(), this.getTriggerInfo(), this.readColsBitSet == null ? (FormatableBitSet)null : new FormatableBitSet(this.readColsBitSet), getReadColMap(this.targetTableDescriptor.getNumberOfColumns(), this.readColsBitSet), this.resultColumnList.getStreamStorableColIds(this.targetTableDescriptor.getNumberOfColumns()), this.readColsBitSet == null ? this.targetTableDescriptor.getNumberOfColumns() : this.readColsBitSet.getNumBitsSet(), this.positionedUpdate, this.resultSet.isOneRowResultSet(), this.autoincRowLocation, this.inMatchingClause(), this.identitySequenceUUIDString);
      }
   }

   protected void setDeferredForUpdateOfIndexColumn() {
      if (!this.deferred) {
         ConglomerateDescriptor var1 = this.targetTable.getTrulyTheBestAccessPath().getConglomerateDescriptor();
         if (var1 != null && var1.isIndex()) {
            int[] var2 = var1.getIndexDescriptor().baseColumnPositions();
            if (this.resultSet.getResultColumns().updateOverlaps(var2)) {
               this.deferred = true;
            }
         }
      }

   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateCodeForTemporaryTable(var1);
      if (!this.isDependentTable) {
         this.generateParameterValueSet(var1);
      }

      var1.newFieldDeclaration(2, "org.apache.derby.iapi.sql.execute.CursorResultSet", var1.newRowLocationScanResultSetName());
      var1.pushGetResultSetFactoryExpression(var2);
      if (this.inMatchingClause()) {
         this.matchingClause.generateResultSetField(var1, var2);
      } else {
         this.resultSet.generate(var1, var2);
      }

      if (null != this.targetVTI && !this.inMatchingClause()) {
         this.targetVTI.assignCostEstimate(this.resultSet.getNewCostEstimate());
         var2.callMethod((short)185, (String)null, "getUpdateVTIResultSet", "org.apache.derby.iapi.sql.ResultSet", 1);
      } else {
         this.generateGenerationClauses(this.resultColumnList, this.resultSet.getResultSetNumber(), true, var1, var2);
         this.generateCheckConstraints(this.checkConstraints, var1, var2);
         if (this.isDependentTable) {
            var2.push(var1.addItem(this.makeConstantAction()));
            var2.push(var1.addItem(this.makeResultDescription()));
            var2.callMethod((short)185, (String)null, "getDeleteCascadeUpdateResultSet", "org.apache.derby.iapi.sql.ResultSet", 5);
         } else {
            var2.callMethod((short)185, (String)null, "getUpdateResultSet", "org.apache.derby.iapi.sql.ResultSet", 3);
         }
      }

   }

   protected final int getStatementType() {
      return 3;
   }

   FormatableBitSet getReadMap(DataDictionary var1, TableDescriptor var2, ResultColumnList var3, ColumnDescriptorList var4) throws StandardException {
      boolean[] var5 = new boolean[]{this.requiresDeferredProcessing()};
      ArrayList var6 = new ArrayList();
      this.relevantCdl = new ConstraintDescriptorList();
      this.relevantTriggers = new TriggerDescriptorList();
      FormatableBitSet var7 = getUpdateReadMap(var1, var2, var3, var6, this.relevantCdl, this.relevantTriggers, var5, var4);
      this.markAffectedIndexes(var6);
      this.adjustDeferredFlag(var5[0]);
      return var7;
   }

   private int[] getChangedColumnIds(ResultColumnList var1) {
      return var1 == null ? (int[])null : var1.sortMe();
   }

   static FormatableBitSet getUpdateReadMap(DataDictionary var0, TableDescriptor var1, ResultColumnList var2, List var3, ConstraintDescriptorList var4, TriggerDescriptorList var5, boolean[] var6, ColumnDescriptorList var7) throws StandardException {
      int var8 = var1.getMaxColumnID();
      FormatableBitSet var9 = new FormatableBitSet(var8 + 1);
      int[] var10 = var2.sortMe();

      for(int var11 = 0; var11 < var10.length; ++var11) {
         var9.set(var10[var11]);
      }

      DMLModStatementNode.getXAffectedIndexes(var1, var2, var9, var3);
      var1.getAllRelevantConstraints(3, var10, var6, var4);
      int var19 = var4.size();

      for(int var12 = 0; var12 < var19; ++var12) {
         ConstraintDescriptor var13 = var4.elementAt(var12);
         if (var13.getConstraintType() == 4) {
            int[] var14 = ((CheckConstraintDescriptor)var13).getReferencedColumns();

            for(int var15 = 0; var15 < var14.length; ++var15) {
               var9.set(var14[var15]);
            }
         }
      }

      addGeneratedColumnPrecursors(var1, var7, var9);
      var1.getAllRelevantTriggers(3, var10, var5);
      if (var5.size() > 0) {
         var6[0] = true;
         boolean var20 = false;
         boolean var21 = var0.checkVersion(210, (String)null);

         for(TriggerDescriptor var24 : var5) {
            if (!var21) {
               if (var24.getReferencingNew() || var24.getReferencingOld()) {
                  var20 = true;
                  break;
               }
            } else {
               int[] var16 = var24.getReferencedColsInTriggerAction();
               int[] var17 = var24.getReferencedCols();
               if (var17 != null && var17.length != 0) {
                  if (var16 != null && var16.length != 0) {
                     for(int var26 = 0; var26 < var17.length; ++var26) {
                        var9.set(var17[var26]);
                     }

                     for(int var27 = 0; var27 < var16.length; ++var27) {
                        var9.set(var16[var27]);
                     }
                     continue;
                  }

                  if (!var24.getReferencingNew() && !var24.getReferencingOld()) {
                     for(int var25 = 0; var25 < var17.length; ++var25) {
                        var9.set(var17[var25]);
                     }
                     continue;
                  }

                  var20 = true;
                  break;
               }

               for(int var18 = 0; var18 < var8; ++var18) {
                  var9.set(var18 + 1);
               }
               break;
            }
         }

         if (var20) {
            for(int var23 = 1; var23 <= var8; ++var23) {
               var9.set(var23);
            }
         }
      }

      return var9;
   }

   private static void addGeneratedColumnPrecursors(TableDescriptor var0, ColumnDescriptorList var1, FormatableBitSet var2) throws StandardException {
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ColumnDescriptor var5 = var1.elementAt(var4);
         String[] var6 = var5.getDefaultInfo().getReferencedColumnNames();
         int[] var7 = var0.getColumnIDs(var6);
         int var8 = var7.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            var2.set(var7[var9]);
         }
      }

   }

   private void addGeneratedColumns(TableDescriptor var1, ResultSetNode var2, ColumnDescriptorList var3, ColumnDescriptorList var4) throws StandardException {
      ResultColumnList var5 = var2.getResultColumns();
      ColumnDescriptorList var6 = var1.getGeneratedColumns();
      HashSet var7 = new HashSet();
      UUID var8 = var1.getObjectID();

      for(ResultColumn var10 : var5) {
         var7.add(var10.getName());
      }

      for(ColumnDescriptor var21 : var6) {
         DefaultInfo var11 = var21.getDefaultInfo();
         String[] var12 = var11.getReferencedColumnNames();
         int var13 = var12.length;
         if (var7.contains(var21.getColumnName())) {
            var3.add(var8, var21);
         }

         for(String var17 : var12) {
            if (var7.contains(var17)) {
               var3.add(var8, var21);
               if (!var7.contains(var21.getColumnName())) {
                  var4.add(var8, var21);
                  UntypedNullConstantNode var18 = new UntypedNullConstantNode(this.getContextManager());
                  ResultColumn var19 = new ResultColumn(var21.getType(), var18, this.getContextManager());
                  var19.setColumnDescriptor(var1, var21);
                  var19.setName(var21.getColumnName());
                  var5.addResultColumn(var19);
               }
               break;
            }
         }
      }

   }

   private void normalizeCorrelatedColumns(ResultColumnList var1, FromTable var2) throws StandardException {
      String var3 = var2.getCorrelationName();
      if (var3 != null) {
         TableName var4;
         if (var2 instanceof CurrentOfNode) {
            var4 = ((CurrentOfNode)var2).getBaseCursorTargetTableName();
         } else {
            var4 = this.makeTableName((String)null, var2.getBaseTableName());
         }

         for(ResultColumn var6 : var1) {
            ColumnReference var7 = var6.getReference();
            if (var7 != null && var3.equals(var7.getTableName())) {
               var7.setQualifiedTableName(var4);
            }
         }

      }
   }

   private void checkTableNameAndScrubResultColumns(ResultColumnList var1) throws StandardException {
      for(ResultColumn var3 : var1) {
         boolean var4 = false;
         if (var3.getTableName() != null && !this.inMatchingClause()) {
            for(ResultSetNode var6 : ((SelectNode)this.resultSet).fromList) {
               FromTable var7 = (FromTable)var6;
               String var8;
               if (var7 instanceof CurrentOfNode) {
                  var8 = ((CurrentOfNode)var7).getBaseCursorTargetTableName().getTableName();
               } else {
                  var8 = var7.getBaseTableName();
               }

               if (var3.getTableName().equals(var8)) {
                  var4 = true;
                  break;
               }
            }

            if (!var4) {
               Object[] var10001 = new Object[1];
               String var10004 = var3.getTableName();
               var10001[0] = var10004 + "." + var3.getName();
               throw StandardException.newException("42X04", var10001);
            }
         }

         var3.clearTableName();
      }

   }

   private void normalizeSynonymColumns(ResultColumnList var1, FromTable var2) throws StandardException {
      if (var2.getCorrelationName() == null) {
         TableName var3;
         if (var2 instanceof CurrentOfNode) {
            var3 = ((CurrentOfNode)var2).getBaseCursorTargetTableName();
         } else {
            var3 = this.makeTableName((String)null, var2.getBaseTableName());
         }

         super.normalizeSynonymColumns(var1, var3);
      }
   }

   private void forbidGenerationOverrides(ResultColumnList var1, ColumnDescriptorList var2) throws StandardException {
      int var3 = var1.size();
      ResultColumnList var4 = this.resultSet.getResultColumns();

      for(int var5 = 0; var5 < var3; ++var5) {
         ResultColumn var6 = (ResultColumn)var1.elementAt(var5);
         if (!var6.wasDefaultColumn() && var6.hasGenerationClause()) {
            ValueNode var7 = ((ResultColumn)var4.elementAt(var5)).getExpression();
            if (!(var7 instanceof DefaultNode)) {
               boolean var8 = false;
               String var9 = var6.getTableColumnDescriptor().getColumnName();
               int var10 = var2.size();

               for(int var11 = 0; var11 < var10; ++var11) {
                  String var12 = var2.elementAt(var11).getColumnName();
                  if (var9.equals(var12)) {
                     var8 = true;
                     break;
                  }
               }

               if (!var8) {
                  throw StandardException.newException("42XA3", new Object[]{var6.getName()});
               }
            }
         }
      }

   }
}
