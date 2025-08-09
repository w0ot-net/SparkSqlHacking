package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class CurrentDatetimeOperatorNode extends ValueNode {
   static final int CURRENT_DATE = 0;
   static final int CURRENT_TIME = 1;
   static final int CURRENT_TIMESTAMP = 2;
   private static final int[] jdbcTypeId = new int[]{91, 92, 93};
   private static final String[] methodName = new String[]{"CURRENT DATE", "CURRENT TIME", "CURRENT TIMSTAMP"};
   private int whichType;

   CurrentDatetimeOperatorNode(int var1, ContextManager var2) {
      super(var2);
      this.whichType = var1;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.checkReliability(methodName[this.whichType], 1);
      this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(jdbcTypeId[this.whichType], false));
      return this;
   }

   protected int getOrderableVariantType() {
      return 2;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      switch (this.whichType) {
         case 0 -> var1.getCurrentDateExpression(var2);
         case 1 -> var1.getCurrentTimeExpression(var2);
         case 2 -> var1.getCurrentTimestampExpression(var2);
      }

      var1.generateDataValue(var2, this.getTypeCompiler(), this.getTypeServices().getCollationType(), (LocalField)null);
   }

   public String toString() {
      return "";
   }

   boolean isEquivalent(ValueNode var1) {
      if (this.isSameNodeKind(var1)) {
         CurrentDatetimeOperatorNode var2 = (CurrentDatetimeOperatorNode)var1;
         return var2.whichType == this.whichType;
      } else {
         return false;
      }
   }
}
