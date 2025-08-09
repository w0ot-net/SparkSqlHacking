package org.apache.derby.impl.sql.compile;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.SQLDate;
import org.apache.derby.iapi.types.SQLTime;
import org.apache.derby.iapi.types.SQLTimestamp;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class UserTypeConstantNode extends ConstantNode {
   Object val;

   UserTypeConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var1, true, -1, var2);
   }

   UserTypeConstantNode(Date var1, ContextManager var2) throws StandardException {
      super(TypeId.getBuiltInTypeId(91), var1 == null, 10, var2);
      this.setValue(new SQLDate(var1));
      this.val = var1;
   }

   UserTypeConstantNode(Time var1, ContextManager var2) throws StandardException {
      super(TypeId.getBuiltInTypeId(92), var1 == null, 8, var2);
      this.setValue(new SQLTime(var1));
      this.val = var1;
   }

   UserTypeConstantNode(Timestamp var1, ContextManager var2) throws StandardException {
      super(TypeId.getBuiltInTypeId(93), var1 == null, 29, var2);
      this.setValue(new SQLTimestamp(var1));
      this.val = var1;
   }

   UserTypeConstantNode(DataValueDescriptor var1, ContextManager var2) throws StandardException {
      super(getTypeId(var1), var1 == null, getWidth(var1), var2);
      this.setValue(var1);
      this.val = var1;
   }

   private static TypeId getTypeId(DataValueDescriptor var0) {
      if (var0 != null) {
         switch (var0.getTypeFormatId()) {
            case 31 -> {
               return TypeId.getBuiltInTypeId(93);
            }
            case 298 -> {
               return TypeId.getBuiltInTypeId(91);
            }
            case 299 -> {
               return TypeId.getBuiltInTypeId(92);
            }
            default -> {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private static int getWidth(DataValueDescriptor var0) {
      if (var0 != null) {
         switch (var0.getTypeFormatId()) {
            case 31 -> {
               return 29;
            }
            case 298 -> {
               return 10;
            }
            case 299 -> {
               return 8;
            }
            default -> {
               return 0;
            }
         }
      } else {
         return 0;
      }
   }

   public Object getObjectValue() {
      return this.val;
   }

   boolean isNull() {
      return this.val == null;
   }

   Object getConstantValueAsObject() {
      return this.val;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      TypeCompiler var3 = this.getTypeCompiler();
      String var4 = var3.interfaceName();
      if (this.val == null) {
         var1.generateNull(var2, var3, this.getTypeServices().getCollationType());
      } else {
         String var5 = this.getTypeId().getCorrespondingJavaTypeName();
         var2.push(this.val.toString());
         var2.callMethod((short)184, var5, "valueOf", var5, 1);
         LocalField var6 = var1.newFieldDeclaration(2, var4);
         var1.generateDataValue(var2, var3, this.getTypeServices().getCollationType(), var6);
      }

   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
   }
}
