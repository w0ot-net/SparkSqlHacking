package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class ExtractOperatorNode extends UnaryOperatorNode {
   private static final String[] fieldName = new String[]{"YEAR", "MONTH", "DAY", "HOUR", "MINUTE", "SECOND"};
   private static final String[] fieldMethod = new String[]{"getYear", "getMonth", "getDate", "getHours", "getMinutes", "getSeconds"};
   private int extractField;

   ExtractOperatorNode(int var1, ValueNode var2, ContextManager var3) throws StandardException {
      super(var2, "EXTRACT " + fieldName[var1], fieldMethod[var1], var3);
      this.extractField = var1;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      TypeId var5 = this.operand.getTypeId();
      int var4 = var5.getJDBCTypeId();
      if (var5.isStringTypeId()) {
         TypeCompiler var6 = this.operand.getTypeCompiler();
         int var7 = this.extractField < 3 ? 91 : 92;
         this.operand = new CastNode(this.operand, DataTypeDescriptor.getBuiltInDataTypeDescriptor(var7, true, var6.getCastToCharWidth(this.operand.getTypeServices())), this.getContextManager());
         ((CastNode)this.operand).bindCastNodeOnly();
         var5 = this.operand.getTypeId();
         var4 = var5.getJDBCTypeId();
      }

      if (var4 != 91 && var4 != 92 && var4 != 93) {
         Object[] var9 = new Object[2];
         String var11 = fieldName[this.extractField];
         var9[0] = "EXTRACT " + var11;
         var9[1] = var5.getSQLTypeName();
         throw StandardException.newException("42X25", var9);
      } else if (var4 == 91 && this.extractField > 2) {
         Object[] var8 = new Object[2];
         String var10 = fieldName[this.extractField];
         var8[0] = "EXTRACT " + var10;
         var8[1] = var5.getSQLTypeName();
         throw StandardException.newException("42X25", var8);
      } else if (var4 == 92 && this.extractField < 3) {
         Object[] var10001 = new Object[2];
         String var10004 = fieldName[this.extractField];
         var10001[0] = "EXTRACT " + var10004;
         var10001[1] = var5.getSQLTypeName();
         throw StandardException.newException("42X25", var10001);
      } else {
         if (var4 == 93 && this.extractField == 5) {
            this.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(8), this.operand.getTypeServices().isNullable()));
         } else {
            this.setType(new DataTypeDescriptor(TypeId.INTEGER_ID, this.operand.getTypeServices().isNullable()));
         }

         return this;
      }
   }

   public String toString() {
      return "";
   }
}
