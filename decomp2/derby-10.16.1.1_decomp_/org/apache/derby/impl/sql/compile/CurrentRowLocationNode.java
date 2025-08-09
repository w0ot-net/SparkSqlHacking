package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class CurrentRowLocationNode extends ValueNode {
   CurrentRowLocationNode(ContextManager var1) {
      super(var1);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId("REF"), false));
      return this;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      MethodBuilder var3 = var1.newGeneratedFun("org.apache.derby.iapi.types.DataValueDescriptor", 4);
      LocalField var4 = var1.newFieldDeclaration(2, "org.apache.derby.iapi.types.RefDataValue");
      var3.pushThis();
      var3.getField((String)null, var1.getRowLocationScanResultSetName(), "org.apache.derby.iapi.sql.execute.CursorResultSet");
      var3.callMethod((short)185, (String)null, "getRowLocation", "org.apache.derby.iapi.types.RowLocation", 0);
      var1.generateDataValue(var3, this.getTypeCompiler(), this.getTypeServices().getCollationType(), var4);
      var3.putField(var4);
      var3.methodReturn();
      var3.complete();
      var2.pushThis();
      var2.callMethod((short)182, (String)null, var3.getName(), "org.apache.derby.iapi.types.DataValueDescriptor", 0);
   }

   boolean isEquivalent(ValueNode var1) {
      return false;
   }
}
