package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.shared.common.error.StandardException;

public abstract class WindowFunctionNode extends UnaryOperatorNode {
   private WindowNode window;
   private ResultColumn generatedRC;
   private ColumnReference generatedRef;

   WindowFunctionNode(ValueNode var1, String var2, WindowNode var3, ContextManager var4) throws StandardException {
      super(var1, var2, (String)null, var4);
      this.window = var3;
   }

   public boolean isConstantExpression() {
      return false;
   }

   boolean constantExpression(PredicateList var1) {
      return false;
   }

   WindowNode getWindow() {
      return this.window;
   }

   void setWindow(WindowDefinitionNode var1) {
      this.window = var1;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      if (this.window instanceof WindowReferenceNode) {
         WindowDefinitionNode var4 = this.definedWindow(var1.getWindows(), this.window.getName());
         if (var4 == null) {
            throw StandardException.newException("42ZC0", new Object[]{this.window.getName()});
         }

         this.window = var4;
      }

      return this;
   }

   private WindowDefinitionNode definedWindow(WindowList var1, String var2) {
      for(WindowDefinitionNode var4 : var1) {
         if (var4.getName().equals(var2)) {
            return var4;
         }
      }

      return null;
   }

   public void printSubNodes(int var1) {
   }

   ValueNode replaceCallsWithColumnReferences(ResultColumnList var1, int var2) throws StandardException {
      if (this.generatedRef == null) {
         CompilerContext var4 = this.getCompilerContext();
         String var3 = "SQLCol" + var4.getNextColumnNumber();
         this.generatedRC = new ResultColumn(var3, this, this.getContextManager());
         this.generatedRC.markGenerated();
         this.generatedRef = new ColumnReference(this.generatedRC.getName(), (TableName)null, this.getContextManager());
         this.generatedRef.setSource(this.generatedRC);
         this.generatedRef.setNestingLevel(0);
         this.generatedRef.setSourceLevel(0);
         if (var2 != -1) {
            this.generatedRef.setTableNumber(var2);
         }

         var1.addResultColumn(this.generatedRC);
         this.generatedRef.markGeneratedToReplaceWindowFunctionCall();
      } else {
         var1.addResultColumn(this.generatedRC);
      }

      return this.generatedRef;
   }

   ColumnReference getGeneratedRef() {
      return this.generatedRef;
   }

   ValueNode getNewNullResultExpression() throws StandardException {
      return this.getNullNode(this.getTypeServices());
   }
}
