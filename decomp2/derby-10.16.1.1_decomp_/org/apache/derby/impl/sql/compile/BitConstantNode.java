package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.BitDataValue;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class BitConstantNode extends ConstantNode {
   BitConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var1, true, 0, var2);
   }

   BitConstantNode(String var1, int var2, ContextManager var3) throws StandardException {
      super(TypeId.getBuiltInTypeId(-2), false, var2, var3);
      byte[] var4 = StringUtil.fromHexString(var1, 0, var1.length());
      BitDataValue var5 = this.getDataValueFactory().getBitDataValue(var4);
      var5.setWidth(var2, 0, false);
      this.setValue(var5);
   }

   Object getConstantValueAsObject() throws StandardException {
      return this.value.getBytes();
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      byte[] var3 = this.value.getBytes();
      String var4 = StringUtil.toHexString(var3, 0, var3.length);
      var2.push(var4);
      var2.push((int)0);
      var2.push(var4.length());
      var2.callMethod((short)184, "org.apache.derby.iapi.util.StringUtil", "fromHexString", "byte[]", 3);
   }
}
