package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptorList;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.impl.sql.execute.FKInfo;
import org.apache.derby.impl.sql.execute.TriggerInfo;
import org.apache.derby.shared.common.error.StandardException;

abstract class DMLModStatementNode extends DMLStatementNode {
   protected FromVTI targetVTI;
   protected TableName targetTableName;
   protected ResultColumnList resultColumnList;
   protected int lockMode;
   protected FKInfo[] fkInfo;
   protected TriggerInfo triggerInfo;
   TableDescriptor targetTableDescriptor;
   public IndexRowGenerator[] indicesToMaintain;
   public long[] indexConglomerateNumbers;
   public String[] indexNames;
   protected ConstraintDescriptorList relevantCdl;
   protected TriggerDescriptorList relevantTriggers;
   private boolean requiresDeferredProcessing;
   private int statementType;
   private boolean bound;
   private ValueNode checkConstraints;
   protected String[] fkSchemaNames;
   protected String[] fkTableNames;
   protected int[] fkRefActions;
   protected ColumnDescriptorList[] fkColDescriptors;
   protected long[] fkIndexConglomNumbers;
   protected boolean isDependentTable;
   protected int[][] fkColArrays;
   protected TableName synonymTableName;
   protected MatchingClauseNode matchingClause;
   Set dependentTables;

   DMLModStatementNode(ResultSetNode var1, MatchingClauseNode var2, ContextManager var3) {
      super(var1, var3);
      this.matchingClause = var2;
      this.statementType = this.getStatementType();
   }

   DMLModStatementNode(ResultSetNode var1, MatchingClauseNode var2, int var3, ContextManager var4) {
      super(var1, var4);
      this.matchingClause = var2;
      this.statementType = var3;
   }

   public boolean inMatchingClause() {
      return this.matchingClause != null;
   }

   void setTarget(QueryTreeNode var1) {
      if (var1 instanceof TableName) {
         this.targetTableName = (TableName)var1;
      } else {
         this.targetVTI = (FromVTI)var1;
         this.targetVTI.setTarget();
      }

   }

   protected void generateCodeForTemporaryTable(ActivationClassBuilder var1) throws StandardException {
      if (this.targetTableDescriptor != null && this.targetTableDescriptor.getTableType() == 3 && this.targetTableDescriptor.isOnRollbackDeleteRows()) {
         MethodBuilder var2 = var1.getExecuteMethod();
         var2.pushThis();
         var2.callMethod((short)185, "org.apache.derby.iapi.sql.Activation", "getLanguageConnectionContext", "org.apache.derby.iapi.sql.conn.LanguageConnectionContext", 0);
         var2.push(this.targetTableDescriptor.getName());
         var2.callMethod((short)185, (String)null, "markTempTableAsModifiedInUnitOfWork", "void", 1);
         var2.endStatement();
      }

   }

   void verifyTargetTable() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      if (this.targetTableName != null) {
         SchemaDescriptor var2 = this.getSchemaDescriptor(this.targetTableName.getSchemaName());
         this.targetTableDescriptor = this.getTableDescriptor(this.targetTableName.getTableName(), var2);
         if (this.targetTableDescriptor == null) {
            TableName var3 = this.resolveTableToSynonym(this.targetTableName);
            if (var3 == null) {
               throw StandardException.newException("42X05", new Object[]{this.targetTableName});
            }

            this.synonymTableName = this.targetTableName;
            this.targetTableName = var3;
            var2 = this.getSchemaDescriptor(this.targetTableName.getSchemaName());
            this.targetTableDescriptor = this.getTableDescriptor(var3.getTableName(), var2);
            if (this.targetTableDescriptor == null) {
               throw StandardException.newException("42X05", new Object[]{this.targetTableName});
            }
         }

         this.targetTableName.setSchemaName(var2.getSchemaName());
         switch (this.targetTableDescriptor.getTableType()) {
            case 1:
            case 5:
               throw StandardException.newException("42Y25", new Object[]{this.targetTableName});
            case 2:
               throw StandardException.newException("42Y24", new Object[]{this.targetTableName});
            case 3:
            case 4:
            default:
               this.targetTableDescriptor = this.lockTableForCompilation(this.targetTableDescriptor);
               this.getCompilerContext().createDependency(this.targetTableDescriptor);
         }
      } else {
         FromList var4 = new FromList(this.getContextManager());
         this.targetVTI = (FromVTI)this.targetVTI.bindNonVTITables(var1, var4);
         this.targetVTI = (FromVTI)this.targetVTI.bindVTITables(var4);
      }

   }

   public boolean isAtomic() {
      return true;
   }

   SchemaDescriptor getSchemaDescriptor() throws StandardException {
      SchemaDescriptor var1 = this.getSchemaDescriptor(this.targetTableName.getSchemaName());
      return var1;
   }

   static int[] getReadColMap(int var0, FormatableBitSet var1) {
      if (var1 == null) {
         return null;
      } else {
         int var2 = 0;
         int[] var3 = new int[var0];
         int var4 = var1.size();

         for(int var5 = 0; var5 < var3.length; ++var5) {
            if (var4 > var5 && var1.get(var5 + 1)) {
               var3[var5] = var2++;
            } else {
               var3[var5] = -1;
            }
         }

         return var3;
      }
   }

   protected void getResultColumnList() throws StandardException {
      if (this.targetVTI == null) {
         this.getResultColumnList((ResultColumnList)null);
      } else {
         this.resultColumnList = this.targetVTI.getResultColumns();
      }

   }

   protected FromBaseTable getResultColumnList(ResultColumnList var1) throws StandardException {
      FromBaseTable var2 = new FromBaseTable(this.synonymTableName != null ? this.synonymTableName : this.targetTableName, (String)null, (ResultColumnList)null, (Properties)null, this.getContextManager());
      if (this.inMatchingClause()) {
         var2.setMergeTableID(2);
      }

      var2.bindNonVTITables(this.getDataDictionary(), new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()));
      this.getResultColumnList(var2, var1);
      return var2;
   }

   private void getResultColumnList(FromBaseTable var1, ResultColumnList var2) throws StandardException {
      if (var2 == null) {
         this.resultColumnList = var1.getAllResultColumns((TableName)null);
         this.resultColumnList.bindResultColumnsByPosition(this.targetTableDescriptor);
      } else {
         this.resultColumnList = var1.getResultColumnsForList((TableName)null, var2, var1.getTableNameField());
         this.resultColumnList.bindResultColumnsByName(this.targetTableDescriptor, this);
      }

   }

   void parseAndBindGenerationClauses(DataDictionary var1, TableDescriptor var2, ResultColumnList var3, ResultColumnList var4, boolean var5, ResultSetNode var6) throws StandardException {
      CompilerContext var7 = this.getCompilerContext();
      int var8 = var4.size();

      for(int var9 = 0; var9 < var8; ++var9) {
         ResultColumn var10 = (ResultColumn)var4.elementAt(var9);
         if ((!var5 || var10.updated()) && var10.hasGenerationClause()) {
            ColumnDescriptor var11 = var10.getTableColumnDescriptor();
            DataTypeDescriptor var12 = var11.getType();
            DefaultInfo var13 = var11.getDefaultInfo();
            ValueNode var14 = this.parseGenerationClause(var13.getDefaultText(), var2);
            var14 = new CastNode(var14, var12, this.getContextManager());
            ((CastNode)var14).setAssignmentSemantics();
            var7.pushCompilationSchema(this.getSchemaDescriptor(var13.getOriginalCurrentSchema(), false));

            try {
               bindRowScopedExpression(this.getOptimizerFactory(), this.getContextManager(), var2, var3, var14);
            } finally {
               var7.popCompilationSchema();
            }

            ResultColumn var15 = new ResultColumn(var14.getTypeServices(), var14, this.getContextManager());
            var15.setVirtualColumnId(var9 + 1);
            var15.setColumnDescriptor(var2, var11);
            var4.setElementAt(var15, var9);
            if (var5) {
               for(int var16 = 0; var16 < var3.size(); ++var16) {
                  if (var10 == var3.elementAt(var16)) {
                     var15.setName(var10.getName());
                     var15.setResultSetNumber(var6.getResultSetNumber());
                     var3.setElementAt(var15, var16);
                  }
               }
            }
         }
      }

   }

   public ValueNode parseGenerationClause(String var1, TableDescriptor var2) throws StandardException {
      LanguageConnectionContext var5 = this.getLanguageConnectionContext();
      String var6 = "SELECT " + var1 + " FROM " + var2.getQualifiedName();
      CompilerContext var7 = var5.pushCompilerContext();
      Parser var3 = var7.getParser();
      Visitable var8 = var3.parseStatement(var6);
      ValueNode var4 = ((ResultColumn)((CursorNode)var8).getResultSetNode().getResultColumns().elementAt(0)).getExpression();
      var5.popCompilerContext(var7);
      return var4;
   }

   ValueNode bindConstraints(DataDictionary var1, OptimizerFactory var2, TableDescriptor var3, Dependent var4, ResultColumnList var5, int[] var6, FormatableBitSet var7, boolean var8, boolean[] var9) throws StandardException {
      this.bound = true;
      if (this.targetVTI != null) {
         return null;
      } else {
         CompilerContext var10 = this.getCompilerContext();
         var10.pushCurrentPrivType(-1);

         try {
            this.getAllRelevantConstraints(var1, var3, var6);
            this.createConstraintDependencies(var1, this.relevantCdl, var4);
            this.generateFKInfo(this.relevantCdl, var1, var3, var7);
            this.getAllRelevantTriggers(var1, var3, var6, var8);
            this.createTriggerDependencies(this.relevantTriggers, var4);
            this.generateTriggerInfo(this.relevantTriggers);
            this.checkConstraints = this.generateCheckTree(this.relevantCdl, var3, var9);
            if (this.checkConstraints != null) {
               SchemaDescriptor var11 = var3.getSchemaDescriptor();
               var10.pushCompilationSchema(var11);

               try {
                  bindRowScopedExpression(var2, this.getContextManager(), var3, var5, this.checkConstraints);
               } finally {
                  var10.popCompilationSchema();
               }
            }
         } finally {
            var10.popCurrentPrivType();
         }

         return this.checkConstraints;
      }
   }

   static void bindRowScopedExpression(OptimizerFactory var0, ContextManager var1, TableDescriptor var2, ResultColumnList var3, ValueNode var4) throws StandardException {
      TableName var5 = makeTableName(var1, var2.getSchemaName(), var2.getName());
      FromList var6 = new FromList(var0.doJoinOrderOptimization(), var1);
      FromBaseTable var7 = new FromBaseTable(var5, (String)null, var3, (Properties)null, var1);
      var7.setTableNumber(0);
      var6.addFromTable(var7);
      var4.bindExpression(var6, (SubqueryList)null, (List)null);
   }

   protected boolean hasCheckConstraints(DataDictionary var1, TableDescriptor var2) throws StandardException {
      ConstraintDescriptorList var3 = var1.getConstraintDescriptors(var2);
      if (var3 == null) {
         return false;
      } else {
         ConstraintDescriptorList var4 = var3.getSubList(4);
         return var4.size() > 0;
      }
   }

   protected boolean hasGenerationClauses(TableDescriptor var1) throws StandardException {
      ColumnDescriptorList var2 = var1.getGeneratedColumns();
      return var2.size() > 0;
   }

   private ValueNode generateCheckTree(ConstraintDescriptorList var1, TableDescriptor var2, boolean[] var3) throws StandardException {
      ConstraintDescriptorList var4 = var1.getSubList(4);
      int var5 = var4.size();
      Object var6 = null;

      for(ConstraintDescriptor var8 : var4) {
         if (var8.deferrable()) {
            var3[0] = true;
            break;
         }
      }

      for(int var12 = 0; var12 < var5; ++var12) {
         ConstraintDescriptor var13 = var4.elementAt(var12);
         String var9 = var13.getConstraintText();
         ValueNode var10 = this.parseCheckConstraint(var9, var2);
         TestConstraintNode var11 = new TestConstraintNode(var10, "23513", var2.getQualifiedName(), var13, this.getContextManager());
         if (var6 == null) {
            var6 = var11;
         } else if (var3[0]) {
            var6 = new AndNoShortCircuitNode(var11, (ValueNode)var6, this.getContextManager());
         } else {
            var6 = new AndNode(var11, (ValueNode)var6, this.getContextManager());
         }
      }

      return (ValueNode)var6;
   }

   private void generateFKInfo(ConstraintDescriptorList var1, DataDictionary var2, TableDescriptor var3, FormatableBitSet var4) throws StandardException {
      ArrayList var5 = new ArrayList();
      ConstraintDescriptorList var13 = var2.getActiveConstraintDescriptors(var1);
      int[] var14 = this.getRowMap(var4, var3);
      ArrayList var18 = new ArrayList(1);
      ArrayList var19 = new ArrayList(1);
      ArrayList var20 = new ArrayList(1);
      ArrayList var21 = new ArrayList(1);
      ArrayList var22 = new ArrayList(1);
      ArrayList var23 = new ArrayList(1);
      int var24 = var13.size();

      for(int var25 = 0; var25 < var24; ++var25) {
         ConstraintDescriptor var26 = var13.elementAt(var25);
         byte var6;
         UUID[] var7;
         long[] var8;
         String[] var9;
         ReferencedKeyConstraintDescriptor var11;
         boolean[] var12;
         int[] var15;
         boolean[] var16;
         UUID[] var17;
         if (var26 instanceof ForeignKeyConstraintDescriptor) {
            var6 = 1;
            var11 = ((ForeignKeyConstraintDescriptor)var26).getReferencedConstraint();
            var7 = new UUID[1];
            var16 = new boolean[1];
            var17 = new UUID[1];
            var8 = new long[1];
            var9 = new String[1];
            var12 = new boolean[1];
            var15 = new int[1];
            this.fkSetupArrays(var2, (ForeignKeyConstraintDescriptor)var26, 0, var7, var8, var9, var12, var15, var16, var17);
            var9[0] = var26.getConstraintName();
         } else {
            if (!(var26 instanceof ReferencedKeyConstraintDescriptor)) {
               continue;
            }

            var11 = (ReferencedKeyConstraintDescriptor)var26;
            var6 = 2;
            ConstraintDescriptorList var10 = var2.getActiveConstraintDescriptors(((ReferencedKeyConstraintDescriptor)var26).getForeignKeyConstraints(1));
            int var27 = var10.size();
            if (var27 == 0) {
               continue;
            }

            var7 = new UUID[var27];
            var16 = new boolean[var27];
            var17 = new UUID[var27];
            var9 = new String[var27];
            var8 = new long[var27];
            var12 = new boolean[var27];
            var15 = new int[var27];
            int[] var32 = this.remapReferencedColumns(var26, var14);

            for(int var33 = 0; var33 < var27; ++var33) {
               ForeignKeyConstraintDescriptor var34 = (ForeignKeyConstraintDescriptor)var10.elementAt(var33);
               this.fkSetupArrays(var2, var34, var33, var7, var8, var9, var12, var15, var16, var17);
               if (var15[var33] == 0 || var15[var33] == 3) {
                  TableDescriptor var28 = var34.getTableDescriptor();
                  var18.add(var28.getSchemaName());
                  var19.add(var28.getName());
                  var21.add(var15[var33]);
                  int[] var30 = var34.getReferencedColumns();
                  ColumnDescriptorList var29 = var28.getColumnDescriptorList();
                  ColumnDescriptorList var35 = new ColumnDescriptorList();

                  for(int var36 = 0; var36 < var30.length; ++var36) {
                     ColumnDescriptor var31 = var29.elementAt(var30[var36] - 1);
                     var35.add(var31);
                  }

                  var22.add(var35);
                  var20.add(var8[var33]);
                  var23.add(var32);
               }
            }
         }

         TableDescriptor var39 = var11.getTableDescriptor();
         UUID var40 = var11.getIndexId();
         ConglomerateDescriptor var41 = var39.getConglomerateDescriptor(var40);
         TableDescriptor var42 = var26.getTableDescriptor();
         var5.add(new FKInfo(var9, var26.getSchemaDescriptor().getSchemaName(), var42.getName(), this.statementType, var6, var40, var41.getConglomerateNumber(), var11.getUUID(), var11.deferrable(), var7, var8, var12, this.remapReferencedColumns(var26, var14), var2.getRowLocationTemplate(this.getLanguageConnectionContext(), var42), var15, var16, var17));
      }

      if (!var5.isEmpty()) {
         this.fkInfo = (FKInfo[])var5.toArray(new FKInfo[var5.size()]);
      }

      int var37 = var21.size();
      if (var37 > 0) {
         this.fkTableNames = new String[var37];
         this.fkSchemaNames = new String[var37];
         this.fkRefActions = new int[var37];
         this.fkColDescriptors = new ColumnDescriptorList[var37];
         this.fkIndexConglomNumbers = new long[var37];
         this.fkColArrays = new int[var37][];

         for(int var38 = 0; var38 < var37; ++var38) {
            this.fkTableNames[var38] = (String)var19.get(var38);
            this.fkSchemaNames[var38] = (String)var18.get(var38);
            this.fkRefActions[var38] = (Integer)var21.get(var38);
            this.fkColDescriptors[var38] = (ColumnDescriptorList)var22.get(var38);
            this.fkIndexConglomNumbers[var38] = (Long)var20.get(var38);
            this.fkColArrays[var38] = (int[])var23.get(var38);
         }
      }

   }

   private void fkSetupArrays(DataDictionary var1, ForeignKeyConstraintDescriptor var2, int var3, UUID[] var4, long[] var5, String[] var6, boolean[] var7, int[] var8, boolean[] var9, UUID[] var10) throws StandardException {
      var6[var3] = var2.getConstraintName();
      var4[var3] = var2.getIndexId();
      var9[var3] = var2.deferrable();
      var10[var3] = var2.getUUID();
      var5[var3] = var2.getIndexConglomerateDescriptor(var1).getConglomerateNumber();
      var7[var3] = var2.isSelfReferencingFK();
      if (this.statementType == 4) {
         var8[var3] = var2.getRaDeleteRule();
      } else if (this.statementType == 3) {
         var8[var3] = var2.getRaUpdateRule();
      }

   }

   private void generateTriggerInfo(TriggerDescriptorList var1) {
      if (var1 != null && !var1.isEmpty()) {
         this.triggerInfo = new TriggerInfo(var1);
      }

   }

   FKInfo[] getFKInfo() {
      return this.fkInfo;
   }

   TriggerInfo getTriggerInfo() {
      return this.triggerInfo;
   }

   ValueNode getCheckConstraints() {
      return this.checkConstraints;
   }

   private void createTriggerDependencies(TriggerDescriptorList var1, Dependent var2) throws StandardException {
      CompilerContext var3 = this.getCompilerContext();

      for(TriggerDescriptor var5 : var1) {
         if (var2 == null) {
            var3.createDependency(var5);
         } else {
            var3.createDependency(var2, var5);
         }
      }

   }

   protected TriggerDescriptorList getAllRelevantTriggers(DataDictionary var1, TableDescriptor var2, int[] var3, boolean var4) throws StandardException {
      if (this.relevantTriggers != null) {
         return this.relevantTriggers;
      } else {
         this.relevantTriggers = new TriggerDescriptorList();
         if (!var4) {
            return this.relevantTriggers;
         } else {
            var2.getAllRelevantTriggers(this.statementType, var3, this.relevantTriggers);
            this.adjustDeferredFlag(this.relevantTriggers.size() > 0);
            return this.relevantTriggers;
         }
      }
   }

   protected void adjustDeferredFlag(boolean var1) {
      if (!this.requiresDeferredProcessing) {
         this.requiresDeferredProcessing = var1;
      }

   }

   private void createConstraintDependencies(DataDictionary var1, ConstraintDescriptorList var2, Dependent var3) throws StandardException {
      CompilerContext var4 = this.getCompilerContext();
      int var5 = var2.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         ConstraintDescriptor var7 = var2.elementAt(var6);
         if (var3 == null) {
            var4.createDependency(var7);
         } else {
            var4.createDependency(var3, var7);
         }

         if (var7 instanceof ReferencedKeyConstraintDescriptor) {
            ConstraintDescriptorList var12 = var1.getActiveConstraintDescriptors(((ReferencedKeyConstraintDescriptor)var7).getForeignKeyConstraints(1));
            int var9 = var12.size();

            for(int var10 = 0; var10 < var9; ++var10) {
               ConstraintDescriptor var11 = var12.elementAt(var10);
               if (var3 == null) {
                  var4.createDependency(var11);
                  var4.createDependency(var11.getTableDescriptor());
               } else {
                  var4.createDependency(var3, var11);
                  var4.createDependency(var3, var11.getTableDescriptor());
               }
            }
         } else if (var7 instanceof ForeignKeyConstraintDescriptor) {
            ForeignKeyConstraintDescriptor var8 = (ForeignKeyConstraintDescriptor)var7;
            if (var3 == null) {
               var4.createDependency(var8.getReferencedConstraint().getTableDescriptor());
            } else {
               var4.createDependency(var3, var8.getReferencedConstraint().getTableDescriptor());
            }
         }
      }

   }

   protected ConstraintDescriptorList getAllRelevantConstraints(DataDictionary var1, TableDescriptor var2, int[] var3) throws StandardException {
      if (this.relevantCdl != null) {
         return this.relevantCdl;
      } else {
         boolean[] var4 = new boolean[1];
         this.relevantCdl = new ConstraintDescriptorList();
         var4[0] = this.requiresDeferredProcessing;
         var2.getAllRelevantConstraints(this.statementType, var3, var4, this.relevantCdl);
         this.adjustDeferredFlag(var4[0]);
         return this.relevantCdl;
      }
   }

   boolean requiresDeferredProcessing() {
      return this.requiresDeferredProcessing;
   }

   public ValueNode parseCheckConstraint(String var1, TableDescriptor var2) throws StandardException {
      LanguageConnectionContext var5 = this.getLanguageConnectionContext();
      String var10000 = var2.getQualifiedName();
      String var6 = "SELECT * FROM " + var10000 + " WHERE " + var1;
      CompilerContext var7 = var5.pushCompilerContext();
      Parser var3 = var7.getParser();
      Visitable var8 = var3.parseStatement(var6);
      ValueNode var4 = ((SelectNode)((CursorNode)var8).getResultSetNode()).getWhereClause();
      var5.popCompilerContext(var7);
      return var4;
   }

   public void generateCheckConstraints(ValueNode var1, ExpressionClassBuilder var2, MethodBuilder var3) throws StandardException {
      if (var1 == null) {
         var3.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      } else {
         MethodBuilder var4 = this.generateCheckConstraints(var1, var2);
         var2.pushMethodReference(var3, var4);
      }

   }

   public MethodBuilder generateCheckConstraints(ValueNode var1, ExpressionClassBuilder var2) throws StandardException {
      MethodBuilder var3 = var2.newUserExprFun();
      var1.generateExpression(var2, var3);
      var3.methodReturn();
      var3.complete();
      return var3;
   }

   public void generateGenerationClauses(ResultColumnList var1, int var2, boolean var3, ExpressionClassBuilder var4, MethodBuilder var5) throws StandardException {
      boolean var6 = false;

      for(ResultColumn var8 : var1) {
         if (var8.hasGenerationClause()) {
            var6 = true;
            break;
         }
      }

      if (!var6) {
         var5.pushNull("org.apache.derby.iapi.services.loader.GeneratedMethod");
      } else {
         MethodBuilder var9 = this.generateGenerationClauses(var1, var2, var3, var4);
         var4.pushMethodReference(var5, var9);
      }

   }

   private MethodBuilder generateGenerationClauses(ResultColumnList var1, int var2, boolean var3, ExpressionClassBuilder var4) throws StandardException {
      MethodBuilder var5 = var4.newUserExprFun();
      var5.pushThis();
      var5.push(var2);
      var5.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getCurrentRow", "org.apache.derby.iapi.sql.Row", 1);
      int var6 = var1.size();
      int var7 = 0;
      if (var3) {
         var7 = var6 - 1;
         var7 /= 2;
      }

      for(int var8 = var7; var8 < var6; ++var8) {
         ResultColumn var9 = (ResultColumn)var1.elementAt(var8);
         if (var9.hasGenerationClause()) {
            var5.dup();
            var5.push(var8 + 1);
            if (this.inMatchingClause()) {
               CollectNodesVisitor var10 = new CollectNodesVisitor(ColumnReference.class);
               var9.accept(var10);

               for(ColumnReference var12 : var10.getList()) {
                  var12.getSource().setResultSetNumber(var2);
               }
            }

            var9.generateExpression(var4, var5);
            var5.cast("org.apache.derby.iapi.types.DataValueDescriptor");
            var5.callMethod((short)185, "org.apache.derby.iapi.sql.Row", "setColumn", "void", 2);
         }
      }

      var5.methodReturn();
      var5.complete();
      return var5;
   }

   public void optimizeStatement() throws StandardException {
      if (!this.inMatchingClause()) {
         super.optimizeStatement();
      } else if (this instanceof UpdateNode) {
         this.resultSet = this.resultSet.preprocess(this.getCompilerContext().getNumTables(), (GroupByList)null, (FromList)null);
      }

      this.lockMode = 6;
   }

   protected void getAffectedIndexes(TableDescriptor var1, ResultColumnList var2, FormatableBitSet var3) throws StandardException {
      ArrayList var4 = new ArrayList();
      getXAffectedIndexes(var1, var2, var3, var4);
      this.markAffectedIndexes(var4);
   }

   static void getXAffectedIndexes(TableDescriptor var0, ResultColumnList var1, FormatableBitSet var2, List var3) throws StandardException {
      ConglomerateDescriptor[] var4 = var0.getConglomerateDescriptors();
      long[] var5 = new long[var4.length - 1];
      int var6 = 0;

      for(int var7 = 0; var7 < var4.length; ++var7) {
         ConglomerateDescriptor var8 = var4[var7];
         if (var8.isIndex() && (var1 == null || var1.updateOverlaps(var8.getIndexDescriptor().baseColumnPositions()))) {
            if (var3 != null) {
               int var9;
               for(var9 = 0; var9 < var6 && var5[var9] != var8.getConglomerateNumber(); ++var9) {
               }

               if (var9 == var6) {
                  var5[var6++] = var8.getConglomerateNumber();
                  var3.add(var8);
               }
            }

            IndexRowGenerator var12 = var8.getIndexDescriptor();
            int[] var10 = var12.baseColumnPositions();
            if (var2 != null) {
               for(int var11 = 0; var11 < var10.length; ++var11) {
                  var2.set(var10[var11]);
               }
            }
         }
      }

   }

   protected void markAffectedIndexes(List var1) throws StandardException {
      int var3 = var1.size();
      CompilerContext var4 = this.getCompilerContext();
      this.indicesToMaintain = new IndexRowGenerator[var3];
      this.indexConglomerateNumbers = new long[var3];
      this.indexNames = new String[var3];

      for(int var5 = 0; var5 < var3; ++var5) {
         ConglomerateDescriptor var2 = (ConglomerateDescriptor)var1.get(var5);
         this.indicesToMaintain[var5] = var2.getIndexDescriptor();
         this.indexConglomerateNumbers[var5] = var2.getConglomerateNumber();
         this.indexNames[var5] = var2.isConstraint() ? null : var2.getConglomerateName();
         var4.createDependency(var2);
      }

   }

   String statementToString() {
      return "DML MOD";
   }

   private int[] remapReferencedColumns(ConstraintDescriptor var1, int[] var2) {
      int[] var3 = var1.getReferencedColumns();
      if (var2 == null) {
         return var3;
      } else {
         int[] var4 = new int[var3.length];

         for(int var5 = 0; var5 < var3.length; ++var5) {
            var4[var5] = var2[var3[var5]];
         }

         return var4;
      }
   }

   private int[] getRowMap(FormatableBitSet var1, TableDescriptor var2) throws StandardException {
      if (var1 == null) {
         return (int[])null;
      } else {
         int var3 = var2.getMaxColumnID();
         int[] var4 = new int[var3 + 1];
         int var5 = 1;

         for(int var6 = 1; var6 <= var3; ++var6) {
            if (var1.get(var6)) {
               var4[var6] = var5++;
            }
         }

         return var4;
      }
   }

   void setRefActionInfo(long var1, int[] var3, String var4, boolean var5) {
      this.resultSet.setRefActionInfo(var1, var3, var4, var5);
   }

   void normalizeSynonymColumns(ResultColumnList var1, TableName var2) throws StandardException {
      if (this.synonymTableName != null) {
         String var3 = this.synonymTableName.getTableName();

         for(ResultColumn var5 : var1) {
            ColumnReference var6 = var5.getReference();
            if (var6 != null) {
               String var7 = var6.getTableName();
               if (var7 != null) {
                  if (!var3.equals(var7)) {
                     throw StandardException.newException("42X55", new Object[]{var3, var7});
                  }

                  var6.setQualifiedTableName(var2);
               }
            }
         }

      }
   }

   void printSubNodes(int var1) {
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.targetTableName != null) {
         this.targetTableName = (TableName)this.targetTableName.accept(var1);
      }

      if (this.synonymTableName != null) {
         this.synonymTableName = (TableName)this.synonymTableName.accept(var1);
      }

   }
}
