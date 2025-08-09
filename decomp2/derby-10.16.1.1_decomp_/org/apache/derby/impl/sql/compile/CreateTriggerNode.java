package org.apache.derby.impl.sql.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class CreateTriggerNode extends DDLStatementNode {
   private TableName triggerName;
   private TableName tableName;
   private int triggerEventMask;
   private ResultColumnList triggerCols;
   private boolean isBefore;
   private boolean isRow;
   private boolean isEnabled;
   private List refClause;
   private ValueNode whenClause;
   private String whenText;
   private StatementNode actionNode;
   private String actionText;
   private String originalWhenText;
   private String originalActionText;
   private ProviderInfo[] providerInfo;
   private SchemaDescriptor triggerSchemaDescriptor;
   private SchemaDescriptor compSchemaDescriptor;
   private int[] referencedColInts;
   private int[] referencedColsInTriggerAction;
   private TableDescriptor triggerTableDescriptor;
   private String oldTableName;
   private String newTableName;
   private boolean oldTableInReferencingClause;
   private boolean newTableInReferencingClause;
   private final ArrayList actionTransformations = new ArrayList();
   private final ArrayList whenClauseTransformations = new ArrayList();
   private static final Comparator OFFSET_COMPARATOR = new Comparator() {
      public int compare(FromBaseTable var1, FromBaseTable var2) {
         return var1.getTableNameField().getBeginOffset() - var2.getTableNameField().getBeginOffset();
      }
   };

   CreateTriggerNode(TableName var1, TableName var2, int var3, ResultColumnList var4, boolean var5, boolean var6, boolean var7, List var8, ValueNode var9, String var10, StatementNode var11, String var12, ContextManager var13) throws StandardException {
      super(var1, var13);
      this.triggerName = var1;
      this.tableName = var2;
      this.triggerEventMask = var3;
      this.triggerCols = var4;
      this.isBefore = var5;
      this.isRow = var6;
      this.isEnabled = var7;
      this.refClause = var8;
      this.whenClause = var9;
      this.originalWhenText = var10;
      this.whenText = var10 == null ? null : var10.trim();
      this.actionNode = var11;
      this.originalActionText = var12;
      this.actionText = var12 == null ? null : var12.trim();
      this.implicitCreateSchema = true;
   }

   String statementToString() {
      return "CREATE TRIGGER";
   }

   void printSubNodes(int var1) {
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      DataDictionary var2 = this.getDataDictionary();
      LanguageConnectionContext var3 = this.getLanguageConnectionContext();
      this.compSchemaDescriptor = var3.getDefaultSchema();
      this.triggerSchemaDescriptor = this.getSchemaDescriptor();
      this.triggerTableDescriptor = this.getTableDescriptor(this.tableName);
      if (this.isSessionSchema(this.triggerTableDescriptor.getSchemaDescriptor())) {
         throw StandardException.newException("XCL51.S", new Object[0]);
      } else {
         if (this.isPrivilegeCollectionRequired()) {
            var1.pushCurrentPrivType(5);
            var1.addRequiredTablePriv(this.triggerTableDescriptor);
            var1.popCurrentPrivType();
         }

         boolean var4 = this.bindReferencesClause(var2);
         SortedSet var5 = this.actionNode.getOffsetOrderedNodes(TableName.class);
         SortedSet var6 = this.whenClause != null ? this.whenClause.getOffsetOrderedNodes(TableName.class) : null;
         ProviderList var7 = var1.getCurrentAuxiliaryProviderList();
         ProviderList var8 = new ProviderList();
         var3.pushTriggerTable(this.triggerTableDescriptor);

         try {
            var1.setCurrentAuxiliaryProviderList(var8);
            if (var4) {
               var1.setReliability(0);
            }

            if (this.isBefore) {
               var1.setReliability(2048);
            }

            this.actionNode.bindStatement();
            if (this.whenClause != null) {
               ContextManager var9 = this.getContextManager();
               this.whenClause = this.whenClause.bindExpression(new FromList(var9), new SubqueryList(var9), new ArrayList(0));
               this.whenClause.checkIsBoolean();
            }
         } finally {
            var3.popTriggerTable(this.triggerTableDescriptor);
            var1.setCurrentAuxiliaryProviderList(var7);
         }

         this.qualifyNames(var5, var6);
         var1.createDependency(this.triggerTableDescriptor);
         if (this.triggerCols != null && this.triggerCols.size() != 0) {
            HashSet var15 = new HashSet();

            for(ResultColumn var11 : this.triggerCols) {
               if (!var15.add(var11.getName())) {
                  throw StandardException.newException("42Y40", new Object[]{var11.getName(), this.triggerName});
               }

               ColumnDescriptor var12 = this.triggerTableDescriptor.getColumnDescriptor(var11.getName());
               if (var12 == null) {
                  throw StandardException.newException("42X14", new Object[]{var11.getName(), this.tableName});
               }
            }
         }

         if (this.referencesSessionSchema()) {
            throw StandardException.newException("XCL51.S", new Object[0]);
         } else {
            DependencyManager var16 = var2.getDependencyManager();
            this.providerInfo = var16.getPersistentProviderInfos(var8);
            var16.clearColumnInfoInProviders(var8);
         }
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return isSessionSchema(this.triggerTableDescriptor.getSchemaName()) || this.actionNode.referencesSessionSchema() || this.whenClause != null && this.whenClause.referencesSessionSchema();
   }

   private boolean bindReferencesClause(DataDictionary var1) throws StandardException {
      this.validateReferencesClause(var1);
      if (this.isBefore) {
         this.forbidActionsOnGenCols();
      }

      String var3 = null;
      if (this.triggerCols != null && this.triggerCols.size() != 0) {
         this.referencedColInts = new int[this.triggerCols.size()];

         for(int var4 = 0; var4 < this.triggerCols.size(); ++var4) {
            ResultColumn var5 = (ResultColumn)this.triggerCols.elementAt(var4);
            ColumnDescriptor var6 = this.triggerTableDescriptor.getColumnDescriptor(var5.getName());
            if (var6 == null) {
               throw StandardException.newException("42X14", new Object[]{var5.getName(), this.tableName});
            }

            this.referencedColInts[var4] = var6.getPosition();
         }

         Arrays.sort(this.referencedColInts);
      }

      String var2;
      if (this.isRow) {
         this.referencedColsInTriggerAction = new int[this.triggerTableDescriptor.getNumberOfColumns()];
         Arrays.fill(this.referencedColsInTriggerAction, -1);
         int[] var7 = this.getDataDictionary().examineTriggerNodeAndCols(this.actionNode, this.oldTableName, this.newTableName, this.originalActionText, this.referencedColInts, this.referencedColsInTriggerAction, this.actionNode.getBeginOffset(), this.triggerTableDescriptor, this.triggerEventMask, true, this.actionTransformations);
         if (this.whenClause != null) {
            var7 = this.getDataDictionary().examineTriggerNodeAndCols(this.whenClause, this.oldTableName, this.newTableName, this.originalActionText, this.referencedColInts, this.referencedColsInTriggerAction, this.actionNode.getBeginOffset(), this.triggerTableDescriptor, this.triggerEventMask, true, this.actionTransformations);
         }

         var2 = this.getDataDictionary().getTriggerActionString(this.actionNode, this.oldTableName, this.newTableName, this.originalActionText, this.referencedColInts, this.referencedColsInTriggerAction, this.actionNode.getBeginOffset(), this.triggerTableDescriptor, this.triggerEventMask, true, this.actionTransformations, var7);
         if (this.whenClause != null) {
            var3 = this.getDataDictionary().getTriggerActionString(this.whenClause, this.oldTableName, this.newTableName, this.originalWhenText, this.referencedColInts, this.referencedColsInTriggerAction, this.whenClause.getBeginOffset(), this.triggerTableDescriptor, this.triggerEventMask, true, this.whenClauseTransformations, var7);
         }

         this.referencedColsInTriggerAction = this.justTheRequiredColumns(this.referencedColsInTriggerAction);
      } else {
         var2 = this.transformStatementTriggerText(this.actionNode, this.originalActionText, this.actionTransformations);
         if (this.whenClause != null) {
            var3 = this.transformStatementTriggerText(this.whenClause, this.originalWhenText, this.whenClauseTransformations);
         }
      }

      if (this.referencedColsInTriggerAction != null) {
         Arrays.sort(this.referencedColsInTriggerAction);
      }

      boolean var8 = false;
      if (!var2.equals(this.actionText)) {
         var8 = true;
         this.actionText = var2;
         this.actionNode = this.parseStatement(this.actionText, true);
      }

      if (this.whenClause != null && !var3.equals(this.whenText)) {
         var8 = true;
         this.whenText = var3;
         this.whenClause = this.parseSearchCondition(this.whenText, true);
      }

      return var8;
   }

   private void qualifyNames(SortedSet var1, SortedSet var2) throws StandardException {
      StringBuilder var3 = new StringBuilder();
      StringBuilder var4 = new StringBuilder();
      this.qualifyNames(this.actionNode, var1, this.originalActionText, this.actionText, this.actionTransformations, var3, var4);
      this.originalActionText = var3.toString();
      this.actionText = var4.toString();
      if (this.whenClause != null) {
         var3.setLength(0);
         var4.setLength(0);
         this.qualifyNames(this.whenClause, var2, this.originalWhenText, this.whenText, this.whenClauseTransformations, var3, var4);
         this.originalWhenText = var3.toString();
         this.whenText = var4.toString();
      }

   }

   private void qualifyNames(QueryTreeNode var1, SortedSet var2, String var3, String var4, List var5, StringBuilder var6, StringBuilder var7) throws StandardException {
      int var8 = 0;
      int var9 = 0;

      for(TableName var11 : var2) {
         String var12 = var11.getFullSQLName();
         int var13 = var11.getBeginOffset() - var1.getBeginOffset();
         int var14 = var11.getEndOffset() + 1 - var11.getBeginOffset();
         var7.append(var4, var9, var13);
         var7.append(var12);
         var9 = var13 + var14;
         Integer var15 = getOriginalPosition(var5, var13);
         if (var15 != null) {
            var6.append(var3, var8, var15);
            var6.append(var12);
            var8 = var15 + var14;
         }
      }

      var7.append(var4, var9, var4.length());
      var6.append(var3, var8, var3.length());
   }

   private static Integer getOriginalPosition(List var0, int var1) {
      for(int var2 = var0.size() - 1; var2 >= 0; --var2) {
         int[] var3 = (int[])var0.get(var2);
         if (var1 >= var3[2]) {
            if (var1 < var3[3]) {
               return null;
            }

            return var3[1] + (var1 - var3[3]);
         }
      }

      return var1;
   }

   private int[] justTheRequiredColumns(int[] var1) {
      int var2 = 0;
      int var3 = this.triggerTableDescriptor.getNumberOfColumns();

      for(int var4 = 0; var4 < var3; ++var4) {
         if (var1[var4] != -1) {
            ++var2;
         }
      }

      if (var2 > 0) {
         int[] var7 = new int[var2];
         int var5 = 0;

         for(int var6 = 0; var6 < var3; ++var6) {
            if (var1[var6] != -1) {
               var7[var5++] = var1[var6];
            }
         }

         return var7;
      } else {
         return null;
      }
   }

   private String transformStatementTriggerText(QueryTreeNode var1, String var2, List var3) throws StandardException {
      int var4 = var1.getBeginOffset();
      int var5 = 0;
      StringBuilder var6 = new StringBuilder();

      for(FromBaseTable var8 : this.getTransitionTables(var1)) {
         String var9 = var8.getBaseTableName();
         int var10 = var8.getTableNameField().getBeginOffset();
         int var11 = var8.getTableNameField().getEndOffset();
         int var12 = var11 - var4 + 1;
         this.checkInvalidTriggerReference(var9);
         var6.append(var2, var5, var10 - var4);
         int var13 = var6.length();
         var6.append(var9.equals(this.oldTableName) ? "new org.apache.derby.catalog.TriggerOldTransitionRows() " : "new org.apache.derby.catalog.TriggerNewTransitionRows() ");
         if (var8.getCorrelationName() == null) {
            var6.append(var9).append(' ');
         }

         var3.add(new int[]{var10 - var4, var12, var13, var6.length()});
         var5 = var12;
      }

      var6.append(var2, var5, var2.length());
      return var6.toString();
   }

   private SortedSet getTransitionTables(Visitable var1) throws StandardException {
      CollectNodesVisitor var2 = new CollectNodesVisitor(FromBaseTable.class);
      var1.accept(var2);
      TreeSet var3 = new TreeSet(OFFSET_COMPARATOR);

      for(FromBaseTable var5 : var2.getList()) {
         if (this.isTransitionTable(var5)) {
            int var6 = var5.getTableNameField().getBeginOffset();
            if (var6 != -1) {
               var3.add(var5);
            }
         }
      }

      return var3;
   }

   private boolean isTransitionTable(FromBaseTable var1) {
      if (!var1.getOrigTableName().hasSchema()) {
         String var2 = var1.getBaseTableName();
         if (var2 != null) {
            return var2.equals(this.oldTableName) || var2.equals(this.newTableName);
         }
      }

      return false;
   }

   private void forbidActionsOnGenCols() throws StandardException {
      ColumnDescriptorList var1 = this.triggerTableDescriptor.getGeneratedColumns();
      int var2 = var1.size();
      if (var2 != 0) {
         CollectNodesVisitor var3 = new CollectNodesVisitor(ColumnReference.class);
         this.actionNode.accept(var3);
         if (this.whenClause != null) {
            this.whenClause.accept(var3);
         }

         for(ColumnReference var5 : var3.getList()) {
            String var6 = var5.getColumnName();
            String var7 = var5.getTableName();

            for(int var8 = 0; var8 < var2; ++var8) {
               String var9 = var1.elementAt(var8).getColumnName();
               if (var9.equals(var6) && this.equals(this.newTableName, var7)) {
                  throw StandardException.newException("42XAA", new Object[]{var9});
               }
            }
         }

      }
   }

   private boolean equals(String var1, String var2) {
      if (var1 == null) {
         return var2 == null;
      } else {
         return var1.equals(var2);
      }
   }

   private void checkInvalidTriggerReference(String var1) throws StandardException {
      if (var1.equals(this.oldTableName) && (this.triggerEventMask & 4) == 4) {
         throw StandardException.newException("42Y92", new Object[]{"INSERT", "new"});
      } else if (var1.equals(this.newTableName) && (this.triggerEventMask & 2) == 2) {
         throw StandardException.newException("42Y92", new Object[]{"DELETE", "old"});
      }
   }

   private void validateReferencesClause(DataDictionary var1) throws StandardException {
      if (this.refClause != null && !this.refClause.isEmpty()) {
         for(TriggerReferencingStruct var3 : this.refClause) {
            if (this.isRow && !var3.isRow) {
               throw StandardException.newException("42Y92", new Object[]{"ROW", "row"});
            }

            if (!this.isRow && var3.isRow) {
               throw StandardException.newException("42Y92", new Object[]{"STATEMENT", "table"});
            }

            if (var3.isNew) {
               if (this.newTableInReferencingClause) {
                  throw StandardException.newException("42Y93", new Object[0]);
               }

               if ((this.triggerEventMask & 2) == 2) {
                  throw StandardException.newException("42Y92", new Object[]{"DELETE", "old"});
               }

               this.newTableName = var3.identifier;
               this.newTableInReferencingClause = true;
            } else {
               if (this.oldTableInReferencingClause) {
                  throw StandardException.newException("42Y93", new Object[0]);
               }

               if ((this.triggerEventMask & 4) == 4) {
                  throw StandardException.newException("42Y92", new Object[]{"INSERT", "new"});
               }

               this.oldTableName = var3.identifier;
               this.oldTableInReferencingClause = true;
            }

            if (this.isBefore && !var3.isRow) {
               throw StandardException.newException("42Y92", new Object[]{"BEFORE", "row"});
            }
         }

      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      String var1 = this.oldTableInReferencingClause ? this.oldTableName : null;
      String var2 = this.newTableInReferencingClause ? this.newTableName : null;
      return this.getGenericConstantActionFactory().getCreateTriggerConstantAction(this.triggerSchemaDescriptor.getSchemaName(), this.getRelativeName(), this.triggerEventMask, this.isBefore, this.isRow, this.isEnabled, this.triggerTableDescriptor, (UUID)null, this.whenText, (UUID)null, this.actionText, this.compSchemaDescriptor.getUUID(), this.referencedColInts, this.referencedColsInTriggerAction, this.originalWhenText, this.originalActionText, this.oldTableInReferencingClause, this.newTableInReferencingClause, var1, var2, this.providerInfo);
   }

   public String toString() {
      return "";
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.triggerName != null) {
         this.triggerName = (TableName)this.triggerName.accept(var1);
      }

      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }
}
