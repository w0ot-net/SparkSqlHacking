package org.apache.derby.impl.sql.compile;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

abstract class DDLStatementNode extends StatementNode {
   public static final int UNKNOWN_TYPE = 0;
   public static final int ADD_TYPE = 1;
   public static final int DROP_TYPE = 2;
   public static final int MODIFY_TYPE = 3;
   public static final int LOCKING_TYPE = 4;
   public static final int UPDATE_STATISTICS = 5;
   public static final int DROP_STATISTICS = 6;
   private TableName tableName;
   private boolean initOk;
   boolean implicitCreateSchema;

   DDLStatementNode(TableName var1, ContextManager var2) {
      super(var2);
      this.tableName = var1;
      this.initOk = true;
   }

   DDLStatementNode(ContextManager var1) {
      super(var1);
   }

   protected void initAndCheck(Object var1) throws StandardException {
      this.tableName = (TableName)var1;
      this.initOk = true;
   }

   public boolean isAtomic() {
      return true;
   }

   String getRelativeName() {
      return this.tableName.getTableName();
   }

   String getFullName() {
      return this.tableName.getFullTableName();
   }

   public final TableName getObjectName() {
      return this.tableName;
   }

   public String toString() {
      return "";
   }

   int activationKind() {
      return 5;
   }

   final void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getDDLResultSet", "org.apache.derby.iapi.sql.ResultSet", 1);
   }

   protected final SchemaDescriptor getSchemaDescriptor() throws StandardException {
      return this.getSchemaDescriptor(true, true);
   }

   protected final SchemaDescriptor getSchemaDescriptor(boolean var1, boolean var2) throws StandardException {
      String var3 = this.tableName.getSchemaName();
      boolean var4 = !this.implicitCreateSchema;
      SchemaDescriptor var5 = this.getSchemaDescriptor(var3, var4);
      CompilerContext var6 = this.getCompilerContext();
      if (var5 == null) {
         if (var3.startsWith("SYS")) {
            throw StandardException.newException("42X62", new Object[]{this.statementToString(), var3});
         }

         var5 = new SchemaDescriptor(this.getDataDictionary(), var3, (String)null, (UUID)null, false);
         if (this.isPrivilegeCollectionRequired()) {
            var6.addRequiredSchemaPriv(var3, (String)null, 16);
         }
      }

      if (var1 && this.isPrivilegeCollectionRequired()) {
         var6.addRequiredSchemaPriv(var5.getSchemaName(), (String)null, 17);
      }

      if (var2 && var5.isSystemSchema()) {
         throw StandardException.newException("42X62", new Object[]{this.statementToString(), var5});
      } else {
         return var5;
      }
   }

   protected final TableDescriptor getTableDescriptor() throws StandardException {
      return this.getTableDescriptor(this.tableName);
   }

   protected final TableDescriptor getTableDescriptor(boolean var1) throws StandardException {
      TableDescriptor var2 = this.justGetDescriptor(this.tableName);
      var2 = this.checkTableDescriptor(var2, var1);
      return var2;
   }

   protected final TableDescriptor getTableDescriptor(UUID var1) throws StandardException {
      TableDescriptor var2 = this.getDataDictionary().getTableDescriptor(var1);
      var2 = this.checkTableDescriptor(var2, true);
      return var2;
   }

   protected final TableDescriptor getTableDescriptor(TableName var1) throws StandardException {
      TableDescriptor var2 = this.justGetDescriptor(var1);
      var2 = this.checkTableDescriptor(var2, true);
      return var2;
   }

   private TableDescriptor justGetDescriptor(TableName var1) throws StandardException {
      String var2 = var1.getSchemaName();
      SchemaDescriptor var3 = this.getSchemaDescriptor(var2);
      TableDescriptor var4 = this.getTableDescriptor(var1.getTableName(), var3);
      if (var4 == null) {
         throw StandardException.newException("42Y55", new Object[]{this.statementToString(), var1});
      } else {
         return var4;
      }
   }

   private TableDescriptor checkTableDescriptor(TableDescriptor var1, boolean var2) throws StandardException {
      String var3 = null;
      switch (var1.getTableType()) {
         case 0:
            return this.lockTableForCompilation(var1);
         case 1:
            if (!var2) {
               return var1;
            }

            var3 = "X0Y56.S";
            break;
         case 2:
            var3 = "42Y62";
            break;
         case 3:
            return var1;
         case 4:
         default:
            break;
         case 5:
            var3 = "X0Y56.S";
      }

      throw StandardException.newException(var3, new Object[]{this.statementToString(), var1.getQualifiedName()});
   }

   FromList makeFromList(DataDictionary var1, TableElementList var2, boolean var3) throws StandardException {
      TableName var4 = this.getObjectName();
      if (var4.getSchemaName() == null) {
         var4.setSchemaName(this.getSchemaDescriptor().getSchemaName());
      }

      FromList var5 = new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager());
      FromBaseTable var6 = new FromBaseTable(var4, (String)null, (ResultColumnList)null, (Properties)null, this.getContextManager());
      if (var3) {
         var6.setTableNumber(0);
         var5.addFromTable(var6);
         var6.setResultColumns(new ResultColumnList(this.getContextManager()));
      } else {
         var5.addFromTable(var6);
         var5.bindTables(var1, new FromList(this.getOptimizerFactory().doJoinOrderOptimization(), this.getContextManager()));
      }

      var2.appendNewColumnsToRCL(var6);
      return var5;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.tableName != null) {
         this.tableName = (TableName)this.tableName.accept(var1);
      }

   }
}
