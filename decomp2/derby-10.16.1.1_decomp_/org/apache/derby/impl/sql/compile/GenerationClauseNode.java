package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.shared.common.error.StandardException;

class GenerationClauseNode extends ValueNode {
   private ValueNode _generationExpression;
   private String _expressionText;
   private ValueNode _boundExpression;
   private ProviderList _apl;

   GenerationClauseNode(ValueNode var1, String var2, ContextManager var3) {
      super(var3);
      this._generationExpression = var1;
      this._expressionText = var2;
   }

   public String getExpressionText() {
      return this._expressionText;
   }

   void setAuxiliaryProviderList(ProviderList var1) {
      this._apl = var1;
   }

   ProviderList getAuxiliaryProviderList() {
      return this._apl;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this._boundExpression = this._generationExpression.bindExpression(var1, var2, var3);
      return this._boundExpression;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         GenerationClauseNode var2 = (GenerationClauseNode)var1;
         return this._generationExpression.isEquivalent(var2._generationExpression);
      }
   }

   public List findReferencedColumns() throws StandardException {
      CollectNodesVisitor var1 = new CollectNodesVisitor(ColumnReference.class);
      this._generationExpression.accept(var1);
      return var1.getList();
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this._generationExpression != null) {
         this._generationExpression = (ValueNode)this._generationExpression.accept(var1);
      }

      if (this._boundExpression != null) {
         this._boundExpression = (ValueNode)this._boundExpression.accept(var1);
      }

   }

   public String toString() {
      String var10000 = this._expressionText;
      return "expressionText: GENERATED ALWAYS AS ( " + var10000 + " )\n" + super.toString();
   }

   void printSubNodes(int var1) {
   }
}
