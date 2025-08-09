package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.SqlXmlUtil;

abstract class OperatorNode extends ValueNode {
   OperatorNode(ContextManager var1) {
      super(var1);
   }

   static void pushSqlXmlUtil(ExpressionClassBuilder var0, MethodBuilder var1, String var2, String var3) {
      LocalField var4 = var0.newFieldDeclaration(18, SqlXmlUtil.class.getName());
      MethodBuilder var5 = var0.getConstructor();
      var5.pushNewStart(SqlXmlUtil.class.getName());
      var5.pushNewComplete(0);
      var5.putField(var4);
      if (var2 == null) {
         var5.pop();
      } else {
         var5.push(var2);
         var5.push(var3);
         var5.callMethod((short)182, SqlXmlUtil.class.getName(), "compileXQExpr", "void", 2);
      }

      var1.getField(var4);
   }
}
