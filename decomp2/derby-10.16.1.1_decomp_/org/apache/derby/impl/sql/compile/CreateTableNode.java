package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.impl.sql.execute.ColumnInfo;
import org.apache.derby.impl.sql.execute.CreateConstraintConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class CreateTableNode extends DDLStatementNode {
   private char lockGranularity;
   private boolean onCommitDeleteRows;
   private boolean onRollbackDeleteRows;
   private Properties properties;
   private TableElementList tableElementList;
   protected int tableType;
   private ResultColumnList resultColumns;
   private ResultSetNode queryExpression;

   CreateTableNode(TableName var1, TableElementList var2, Properties var3, char var4, ContextManager var5) throws StandardException {
      super(var1, var5);
      this.tableType = 0;
      this.lockGranularity = var4;
      this.implicitCreateSchema = true;
      this.tableElementList = var2;
      this.properties = var3;
   }

   CreateTableNode(TableName var1, TableElementList var2, Properties var3, boolean var4, boolean var5, ContextManager var6) throws StandardException {
      super(tempTableSchemaNameCheck(var1), var6);
      this.tableType = 3;
      this.onCommitDeleteRows = var4;
      this.onRollbackDeleteRows = var5;
      this.tableElementList = var2;
      this.properties = var3;
   }

   CreateTableNode(TableName var1, ResultColumnList var2, ResultSetNode var3, ContextManager var4) throws StandardException {
      super(var1, var4);
      this.tableType = 0;
      this.lockGranularity = 'R';
      this.implicitCreateSchema = true;
      this.resultColumns = var2;
      this.queryExpression = var3;
   }

   private static TableName tempTableSchemaNameCheck(TableName var0) throws StandardException {
      if (var0 != null) {
         if (var0.getSchemaName() == null) {
            var0.setSchemaName("SESSION");
         } else if (!isSessionSchema(var0.getSchemaName())) {
            throw StandardException.newException("428EK", new Object[0]);
         }
      }

      return var0;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   String statementToString() {
      return this.tableType == 3 ? "DECLARE GLOBAL TEMPORARY TABLE" : "CREATE TABLE";
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      SchemaDescriptor var7 = this.getSchemaDescriptor(this.tableType != 3, true);
      if (this.queryExpression != null) {
         FromList var8 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
         CompilerContext var9 = this.getCompilerContext();
         ProviderList var10 = var9.getCurrentAuxiliaryProviderList();
         ProviderList var11 = new ProviderList();

         try {
            var9.setCurrentAuxiliaryProviderList(var11);
            var9.pushCurrentPrivType(0);
            this.queryExpression = this.queryExpression.bindNonVTITables(var1, var8);
            this.queryExpression = this.queryExpression.bindVTITables(var8);
            this.queryExpression.bindExpressions(var8);
            this.queryExpression.bindResultColumns(var8);
            this.queryExpression.bindUntypedNullsToResultColumns((ResultColumnList)null);
         } finally {
            var9.popCurrentPrivType();
            var9.setCurrentAuxiliaryProviderList(var10);
         }

         ResultColumnList var12 = this.queryExpression.getResultColumns();
         if (this.resultColumns != null) {
            if (this.resultColumns.size() != var12.visibleSize()) {
               throw StandardException.newException("42X70", new Object[]{this.getFullName()});
            }

            var12.copyResultColumnNames(this.resultColumns);
         }

         int var13 = var7.getCollationType();
         this.tableElementList = new TableElementList(this.getContextManager());

         for(ResultColumn var15 : var12) {
            if (!var15.isGenerated()) {
               if (var15.isNameGenerated()) {
                  throw StandardException.newException("42909", new Object[0]);
               }

               DataTypeDescriptor var16 = var15.getExpression().getTypeServices();
               if (var16 != null && !var16.isUserCreatableType()) {
                  throw StandardException.newException("42X71", new Object[]{var16.getFullSQLTypeName(), var15.getName()});
               }

               if (var16.getTypeId().isStringTypeId() && var16.getCollationType() != var13) {
                  throw StandardException.newException("42ZA3", new Object[]{var16.getCollationName(), DataTypeDescriptor.getCollationName(var13)});
               }

               ColumnDefinitionNode var17 = new ColumnDefinitionNode(var15.getName(), (ValueNode)null, var15.getType(), (long[])null, this.getContextManager());
               this.tableElementList.addTableElement(var17);
            }
         }
      } else {
         this.tableElementList.setCollationTypesOnCharacterStringColumns(this.getSchemaDescriptor(this.tableType != 3, true));
      }

      this.tableElementList.validate(this, var1, (TableDescriptor)null);
      if (this.tableElementList.countNumberOfColumns() > 1012) {
         throw StandardException.newException("54011", new Object[]{String.valueOf(this.tableElementList.countNumberOfColumns()), this.getRelativeName(), String.valueOf(1012)});
      } else {
         int var2 = this.tableElementList.countConstraints(2);
         if (var2 > 1) {
            throw StandardException.newException("42X90", new Object[]{this.getRelativeName()});
         } else {
            int var3 = this.tableElementList.countConstraints(4);
            int var4 = this.tableElementList.countConstraints(6);
            int var5 = this.tableElementList.countConstraints(3);
            int var6 = this.tableElementList.countGenerationClauses();
            if (this.tableType != 3 || var2 <= 0 && var3 <= 0 && var4 <= 0 && var5 <= 0) {
               if (var2 + var4 + var5 > 32767) {
                  throw StandardException.newException("42Z9F", new Object[]{String.valueOf(var2 + var4 + var5), this.getRelativeName(), String.valueOf(32767)});
               } else {
                  if (var3 > 0 || var6 > 0 || var4 > 0) {
                     FromList var20 = this.makeFromList((DataDictionary)null, this.tableElementList, true);
                     FormatableBitSet var21 = new FormatableBitSet();
                     if (var6 > 0) {
                        this.tableElementList.bindAndValidateGenerationClauses(var7, var20, var21, (TableDescriptor)null);
                     }

                     if (var3 > 0) {
                        this.tableElementList.bindAndValidateCheckConstraints(var20);
                     }

                     if (var4 > 0) {
                        this.tableElementList.validateForeignKeysOnGenerationClauses(var20, var21);
                     }
                  }

                  if (var2 > 0) {
                     this.tableElementList.validatePrimaryKeyNullability();
                  }

               }
            } else {
               throw StandardException.newException("42995", new Object[0]);
            }
         }
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return this.isSessionSchema(this.getSchemaDescriptor(this.tableType != 3, true));
   }

   public ConstantAction makeConstantAction() throws StandardException {
      TableElementList var1 = this.tableElementList;
      ColumnInfo[] var2 = new ColumnInfo[var1.countNumberOfColumns()];
      int var3 = var1.genColumnInfos(var2);
      CreateConstraintConstantAction[] var4 = null;
      SchemaDescriptor var5 = this.getSchemaDescriptor(this.tableType != 3, true);
      if (var3 > 0) {
         var4 = new CreateConstraintConstantAction[var3];
         var1.genConstraintActions(true, var4, this.getRelativeName(), var5, this.getDataDictionary());
      }

      boolean var6 = false;
      int var7 = 0;

      for(int var8 = 0; var8 < var2.length; ++var8) {
         DataTypeDescriptor var9 = var2[var8].getDataType();
         if (var9.getTypeId().isLongConcatableTypeId()) {
            var6 = true;
            break;
         }

         var7 += var9.getTypeId().getApproximateLengthInBytes(var9);
      }

      if ((var6 || var7 > 4096) && (this.properties == null || this.properties.get("derby.storage.pageSize") == null) && PropertyUtil.getServiceProperty(this.getLanguageConnectionContext().getTransactionCompile(), "derby.storage.pageSize") == null) {
         if (this.properties == null) {
            this.properties = new Properties();
         }

         this.properties.put("derby.storage.pageSize", "32768");
      }

      return this.getGenericConstantActionFactory().getCreateTableConstantAction(var5.getSchemaName(), this.getRelativeName(), this.tableType, var2, var4, this.properties, this.lockGranularity, this.onCommitDeleteRows, this.onRollbackDeleteRows);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.tableElementList != null) {
         this.tableElementList.accept(var1);
      }

   }
}
