package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class SetRoleNode extends MiscellaneousStatementNode {
   private String name;
   private int type;

   SetRoleNode(String var1, int var2, ContextManager var3) throws StandardException {
      super(var3);
      this.name = var1;
      this.type = var2;
   }

   public String toString() {
      return "";
   }

   public String statementToString() {
      return "SET ROLE";
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getSetRoleConstantAction(this.name, this.type);
   }

   void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.type == 1) {
         this.generateParameterValueSet(var1);
      }

      var1.pushGetResultSetFactoryExpression(var2);
      var1.pushThisAsActivation(var2);
      var2.callMethod((short)185, (String)null, "getMiscResultSet", "org.apache.derby.iapi.sql.ResultSet", 1);
   }

   private void generateParameterValueSet(ActivationClassBuilder var1) throws StandardException {
      List var2 = this.getCompilerContext().getParameterList();
      ParameterNode.generateParameterValueSet(var1, 1, var2);
   }

   int activationKind() {
      return this.type == 1 ? 2 : 0;
   }

   public boolean isAtomic() {
      return false;
   }
}
