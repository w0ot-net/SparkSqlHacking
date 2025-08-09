package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class SpecialFunctionNode extends ValueNode {
   String sqlName;
   static final int K_IDENTITY_VAL = 0;
   static final int K_CURRENT_ISOLATION = 1;
   static final int K_CURRENT_SCHEMA = 2;
   static final int K_USER = 3;
   static final int K_CURRENT_USER = 4;
   static final int K_SESSION_USER = 5;
   static final int K_SYSTEM_USER = 6;
   static final int K_CURRENT_ROLE = 7;
   final int kind;
   private String methodName;
   private String methodType;

   SpecialFunctionNode(int var1, ContextManager var2) {
      super(var2);
      this.kind = var1;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      DataTypeDescriptor var4;
      switch (this.kind) {
         case 0:
            this.sqlName = "IDENTITY_VAL_LOCAL";
            this.methodName = "getIdentityValue";
            this.methodType = "java.lang.Long";
            var4 = DataTypeDescriptor.getSQLDataTypeDescriptor("java.math.BigDecimal", 31, 0, true, 31);
            break;
         case 1:
            this.sqlName = "CURRENT ISOLATION";
            this.methodName = "getCurrentIsolationLevelStr";
            this.methodType = "java.lang.String";
            var4 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(1, 2);
            break;
         case 2:
            this.sqlName = "CURRENT SCHEMA";
            this.methodName = "getCurrentSchemaName";
            this.methodType = "java.lang.String";
            var4 = DataDictionary.TYPE_SYSTEM_IDENTIFIER;
            break;
         case 3:
         case 4:
         case 6:
            switch (this.kind) {
               case 3:
                  this.sqlName = "USER";
                  break;
               case 4:
                  this.sqlName = "CURRENT_USER";
               case 5:
               default:
                  break;
               case 6:
                  this.sqlName = "SYSTEM_USER";
            }

            this.methodName = "getCurrentUserId";
            this.methodType = "java.lang.String";
            var4 = DataDictionary.TYPE_SYSTEM_IDENTIFIER;
            break;
         case 5:
            this.methodName = "getSessionUserId";
            this.methodType = "java.lang.String";
            this.sqlName = "SESSION_USER";
            var4 = DataDictionary.TYPE_SYSTEM_IDENTIFIER;
            break;
         case 7:
            this.sqlName = "CURRENT_ROLE";
            this.methodName = "getCurrentRoleIdDelimited";
            this.methodType = "java.lang.String";
            var4 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, true, 258);
            break;
         default:
            var4 = null;
      }

      this.checkReliability(this.sqlName, 64);
      this.setType(var4);
      return this;
   }

   protected int getOrderableVariantType() {
      return 2;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var2.pushThis();
      var2.callMethod((short)185, "org.apache.derby.iapi.sql.Activation", "getLanguageConnectionContext", "org.apache.derby.iapi.sql.conn.LanguageConnectionContext", 0);
      int var3 = 0;
      if (this.methodName.equals("getCurrentRoleIdDelimited") || this.methodName.equals("getCurrentSchemaName") || this.methodName.equals("getCurrentUserId")) {
         var1.pushThisAsActivation(var2);
         ++var3;
      }

      var2.callMethod((short)185, (String)null, this.methodName, this.methodType, var3);
      String var4 = this.getTypeCompiler().interfaceName();
      LocalField var5 = var1.newFieldDeclaration(2, var4);
      var1.generateDataValue(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType(), var5);
   }

   public String toString() {
      return "";
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((SpecialFunctionNode)var1).kind == this.kind;
   }

   boolean isEquivalent(ValueNode var1) {
      if (this.isSameNodeKind(var1)) {
         SpecialFunctionNode var2 = (SpecialFunctionNode)var1;
         return this.methodName.equals(var2.methodName);
      } else {
         return false;
      }
   }
}
