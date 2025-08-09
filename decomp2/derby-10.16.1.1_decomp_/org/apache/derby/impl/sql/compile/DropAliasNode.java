package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class DropAliasNode extends DDLStatementNode {
   private char aliasType;
   private char nameSpace;

   DropAliasNode(TableName var1, char var2, ContextManager var3) throws StandardException {
      super(var1, var3);
      this.aliasType = var2;
      switch (this.aliasType) {
         case 'A' -> this.nameSpace = 'A';
         case 'F' -> this.nameSpace = 'F';
         case 'G' -> this.nameSpace = 'G';
         case 'P' -> this.nameSpace = 'P';
         case 'S' -> this.nameSpace = 'S';
      }

   }

   public char getAliasType() {
      return this.aliasType;
   }

   String statementToString() {
      return "DROP ".concat(aliasTypeName(this.aliasType));
   }

   public void bindStatement() throws StandardException {
      DataDictionary var1 = this.getDataDictionary();
      String var2 = this.getRelativeName();
      AliasDescriptor var3 = null;
      SchemaDescriptor var4 = this.getSchemaDescriptor();
      if (var4.getUUID() != null) {
         var3 = var1.getAliasDescriptor(var4.getUUID().toString(), var2, this.nameSpace);
      }

      if (var3 == null) {
         throw StandardException.newException("42Y55", new Object[]{this.statementToString(), var2});
      } else if (var3.getSystemAlias()) {
         throw StandardException.newException("42Y71", new Object[]{var2});
      } else {
         this.getCompilerContext().createDependency(var3);
      }
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getDropAliasConstantAction(this.getSchemaDescriptor(), this.getRelativeName(), this.nameSpace);
   }

   private static String aliasTypeName(char var0) {
      String var1 = null;
      switch (var0) {
         case 'A' -> var1 = "TYPE";
         case 'F' -> var1 = "FUNCTION";
         case 'G' -> var1 = "DERBY AGGREGATE";
         case 'P' -> var1 = "PROCEDURE";
         case 'S' -> var1 = "SYNONYM";
      }

      return var1;
   }
}
