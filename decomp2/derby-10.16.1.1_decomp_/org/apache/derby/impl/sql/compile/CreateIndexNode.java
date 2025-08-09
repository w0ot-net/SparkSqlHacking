package org.apache.derby.impl.sql.compile;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class CreateIndexNode extends DDLStatementNode {
   private boolean unique;
   private Properties properties;
   private String indexType;
   private TableName indexName;
   private TableName tableName;
   private List columnNameList;
   private String[] columnNames;
   private boolean[] isAscending;
   private int[] boundColumnIDs;
   private TableDescriptor td;

   CreateIndexNode(boolean var1, String var2, TableName var3, TableName var4, List var5, Properties var6, ContextManager var7) throws StandardException {
      super(var3, var7);
      this.unique = var1;
      this.indexType = var2;
      this.indexName = var3;
      this.tableName = var4;
      this.columnNameList = var5;
      this.properties = var6;
   }

   public String toString() {
      return "";
   }

   String statementToString() {
      return "CREATE INDEX";
   }

   public void bindStatement() throws StandardException {
      this.getSchemaDescriptor();
      this.td = this.getTableDescriptor(this.tableName);
      if (this.td.getTableType() == 3) {
         throw StandardException.newException("42995", new Object[0]);
      } else if (this.td.getTotalNumberOfIndexes() > 32767) {
         throw StandardException.newException("42Z9F", new Object[]{String.valueOf(this.td.getTotalNumberOfIndexes()), this.tableName, String.valueOf(32767)});
      } else {
         this.verifyAndGetUniqueNames();
         int var1 = this.columnNames.length;
         this.boundColumnIDs = new int[var1];

         for(int var2 = 0; var2 < var1; ++var2) {
            ColumnDescriptor var3 = this.td.getColumnDescriptor(this.columnNames[var2]);
            if (var3 == null) {
               throw StandardException.newException("42X14", new Object[]{this.columnNames[var2], this.tableName});
            }

            this.boundColumnIDs[var2] = var3.getPosition();
            if (!var3.getType().getTypeId().orderable(this.getClassFactory())) {
               throw StandardException.newException("X0X67.S", new Object[]{var3.getType().getTypeId().getSQLTypeName()});
            }
         }

         if (var1 > 16) {
            throw StandardException.newException("54008", new Object[0]);
         } else {
            this.getCompilerContext().createDependency(this.td);
         }
      }
   }

   public boolean referencesSessionSchema() throws StandardException {
      return isSessionSchema(this.td.getSchemaName());
   }

   public ConstantAction makeConstantAction() throws StandardException {
      SchemaDescriptor var1 = this.getSchemaDescriptor();
      int var2 = this.columnNames.length;
      int var3 = 0;

      for(int var4 = 0; var4 < var2; ++var4) {
         ColumnDescriptor var5 = this.td.getColumnDescriptor(this.columnNames[var4]);
         DataTypeDescriptor var6 = var5.getType();
         var3 += var6.getTypeId().getApproximateLengthInBytes(var6);
      }

      if (var3 > 1024 && (this.properties == null || this.properties.get("derby.storage.pageSize") == null) && PropertyUtil.getServiceProperty(this.getLanguageConnectionContext().getTransactionCompile(), "derby.storage.pageSize") == null) {
         if (this.properties == null) {
            this.properties = new Properties();
         }

         this.properties.put("derby.storage.pageSize", "32768");
      }

      return this.getGenericConstantActionFactory().getCreateIndexConstantAction(false, this.unique, false, false, false, -1, this.indexType, var1.getSchemaName(), this.indexName.getTableName(), this.tableName.getTableName(), this.td.getUUID(), this.columnNames, this.isAscending, false, (UUID)null, this.properties);
   }

   private void verifyAndGetUniqueNames() throws StandardException {
      int var1 = this.columnNameList.size();
      HashSet var2 = new HashSet(var1 + 2, 0.999F);
      this.columnNames = new String[var1];
      this.isAscending = new boolean[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         this.columnNames[var3] = (String)this.columnNameList.get(var3);
         if (this.columnNames[var3].endsWith(" ")) {
            this.columnNames[var3] = this.columnNames[var3].substring(0, this.columnNames[var3].length() - 1);
            this.isAscending[var3] = false;
         } else {
            this.isAscending[var3] = true;
         }

         boolean var4 = !var2.add(this.columnNames[var3]);
         if (var4) {
            throw StandardException.newException("42X66", new Object[]{this.columnNames[var3]});
         }
      }

   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.indexName != null) {
         this.indexName = (TableName)this.indexName.accept(var1);
      }

      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }
}
