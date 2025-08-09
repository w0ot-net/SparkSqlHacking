package org.apache.derby.impl.sql.compile;

import org.apache.derby.catalog.DefaultInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.OptimizerFactory;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.impl.sql.execute.ColumnInfo;
import org.apache.derby.shared.common.error.StandardException;

class CreateViewNode extends DDLStatementNode {
   private ResultColumnList resultColumns;
   private ResultSetNode queryExpression;
   private String qeText;
   private int checkOption;
   private ProviderInfo[] providerInfos;
   private ColumnInfo[] colInfos;
   private OrderByList orderByList;
   private ValueNode offset;
   private ValueNode fetchFirst;
   private boolean hasJDBClimitClause;

   CreateViewNode(TableName var1, ResultColumnList var2, ResultSetNode var3, int var4, String var5, OrderByList var6, ValueNode var7, ValueNode var8, boolean var9, ContextManager var10) throws StandardException {
      super(var1, var10);
      this.resultColumns = var2;
      this.queryExpression = var3;
      this.checkOption = var4;
      this.qeText = var5.trim();
      this.orderByList = var6;
      this.offset = var7;
      this.fetchFirst = var8;
      this.hasJDBClimitClause = var9;
      this.implicitCreateSchema = true;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "CREATE VIEW";
   }

   void printSubNodes(int var1) {
   }

   public void bindStatement() throws StandardException {
      CompilerContext var1 = this.getCompilerContext();
      DataDictionary var2 = this.getDataDictionary();
      this.providerInfos = this.bindViewDefinition(var2, var1, this.getLanguageConnectionContext(), this.getOptimizerFactory(), this.queryExpression, this.getContextManager());
      ResultColumnList var3 = this.queryExpression.getResultColumns();
      if (this.resultColumns != null) {
         if (this.resultColumns.size() != var3.visibleSize()) {
            throw StandardException.newException("42X56", new Object[]{this.getFullName()});
         }

         var3.copyResultColumnNames(this.resultColumns);
      }

      String var4 = var3.verifyUniqueNames(this.resultColumns == null);
      if (var4 != null) {
         throw StandardException.newException("42Y13", new Object[]{var4});
      } else if (this.queryExpression.getResultColumns().size() > 5000) {
         throw StandardException.newException("54011", new Object[]{String.valueOf(this.queryExpression.getResultColumns().size()), this.getRelativeName(), String.valueOf(5000)});
      } else {
         this.colInfos = new ColumnInfo[this.queryExpression.getResultColumns().visibleSize()];
         this.genColumnInfos(this.colInfos);
      }
   }

   private ProviderInfo[] bindViewDefinition(DataDictionary var1, CompilerContext var2, LanguageConnectionContext var3, OptimizerFactory var4, ResultSetNode var5, ContextManager var6) throws StandardException {
      FromList var7 = new FromList(var4.doJoinOrderOptimization(), var6);
      ProviderList var8 = var2.getCurrentAuxiliaryProviderList();
      ProviderList var9 = new ProviderList();

      try {
         var2.setCurrentAuxiliaryProviderList(var9);
         var2.pushCurrentPrivType(0);
         var5 = var5.bindNonVTITables(var1, var7);
         var5 = var5.bindVTITables(var7);
         var5.bindExpressions(var7);
         if (var5 instanceof SelectNode && var5.referencesSessionSchema()) {
            throw StandardException.newException("XCL51.S", new Object[0]);
         }

         var5.bindResultColumns(var7);
         var5.bindUntypedNullsToResultColumns((ResultColumnList)null);
      } finally {
         var2.popCurrentPrivType();
         var2.setCurrentAuxiliaryProviderList(var8);
      }

      DependencyManager var10 = var1.getDependencyManager();
      ProviderInfo[] var11 = var10.getPersistentProviderInfos(var9);
      var10.clearColumnInfoInProviders(var9);
      return var11;
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.queryExpression.referencesSessionSchema();
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getCreateViewConstantAction(this.getSchemaDescriptor().getSchemaName(), this.getRelativeName(), 2, this.qeText, this.checkOption, this.colInfos, this.providerInfos, (UUID)null);
   }

   private void genColumnInfos(ColumnInfo[] var1) {
      ResultColumnList var2 = this.queryExpression.getResultColumns();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         ResultColumn var4 = (ResultColumn)var2.elementAt(var3);
         var1[var3] = new ColumnInfo(var4.getName(), var4.getType(), (DataValueDescriptor)null, (DefaultInfo)null, (ProviderInfo[])null, (UUID)null, (UUID)null, 0, 0L, 0L, false, 0L);
      }

   }

   ResultSetNode getParsedQueryExpression() {
      return this.queryExpression;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.queryExpression != null) {
         this.queryExpression = (ResultSetNode)this.queryExpression.accept(var1);
      }

   }

   public OrderByList getOrderByList() {
      return this.orderByList;
   }

   public ValueNode getOffset() {
      return this.offset;
   }

   public ValueNode getFetchFirst() {
      return this.fetchFirst;
   }

   public boolean hasJDBClimitClause() {
      return this.hasJDBClimitClause;
   }
}
