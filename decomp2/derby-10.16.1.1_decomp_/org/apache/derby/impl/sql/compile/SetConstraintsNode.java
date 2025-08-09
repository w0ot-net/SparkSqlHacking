package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.shared.common.error.StandardException;

class SetConstraintsNode extends MiscellaneousStatementNode {
   private final List constraints;
   private final boolean deferred;

   SetConstraintsNode(List var1, boolean var2, ContextManager var3) {
      super(var3);
      this.constraints = var1;
      this.deferred = var2;
   }

   public String toString() {
      return "";
   }

   String formatList(List var1) {
      return "";
   }

   public String statementToString() {
      return "SET CONSTRAINTS";
   }

   public ConstantAction makeConstantAction() throws StandardException {
      return this.getGenericConstantActionFactory().getSetConstraintsConstantAction(this.constraints, this.deferred);
   }

   public void bindStatement() throws StandardException {
      if (this.constraints != null) {
         for(TableName var2 : this.constraints) {
            var2.bind();
         }
      }

   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.constraints != null) {
         for(int var2 = 0; var2 < this.constraints.size(); ++var2) {
            this.constraints.set(var2, (TableName)((TableName)this.constraints.get(var2)).accept(var1));
         }
      }

   }
}
