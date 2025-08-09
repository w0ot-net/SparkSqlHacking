package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

public final class GetCurrentConnectionNode extends JavaValueNode {
   GetCurrentConnectionNode(ContextManager var1) {
      super(var1);
      this.setJavaTypeName("java.sql.Connection");
   }

   JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      return this;
   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
   }

   boolean categorize(JBitSet var1, boolean var2) {
      return false;
   }

   JavaValueNode remapColumnReferencesToExpressions() {
      return this;
   }

   void bindParameter() {
   }

   int getOrderableVariantType() {
      return 2;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var2.pushThis();
      var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getCurrentConnection", this.getJavaTypeName(), 0);
   }

   void checkReliability(ValueNode var1) throws StandardException {
      var1.checkReliability("getCurrentConnection()", 2);
   }
}
