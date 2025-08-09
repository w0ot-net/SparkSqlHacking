package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public class DefaultNode extends ValueNode {
   private String columnName;
   private String defaultText;
   private ValueNode defaultTree;

   DefaultNode(ContextManager var1) {
      super(var1);
   }

   DefaultNode(ValueNode var1, String var2, ContextManager var3) {
      super(var3);
      this.defaultTree = var1;
      this.defaultText = var2;
   }

   DefaultNode(String var1, ContextManager var2) {
      super(var2);
      this.columnName = var1;
   }

   public String getDefaultText() {
      return this.defaultText;
   }

   ValueNode getDefaultTree() {
      return this.defaultTree;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      TableDescriptor var5 = ((FromBaseTable)var1.elementAt(0)).getTableDescriptor();
      ColumnDescriptor var4 = var5.getColumnDescriptor(this.columnName);
      DefaultInfoImpl var6 = (DefaultInfoImpl)var4.getDefaultInfo();
      if (var6 != null) {
         String var7 = var6.getDefaultText();
         ValueNode var8 = parseDefault(var7, this.getLanguageConnectionContext(), this.getCompilerContext());
         DefaultDescriptor var9 = var4.getDefaultDescriptor(this.getDataDictionary());
         this.getCompilerContext().createDependency(var9);
         return var8.bindExpression(var1, var2, var3);
      } else {
         return new UntypedNullConstantNode(this.getContextManager());
      }
   }

   public static ValueNode parseDefault(String var0, LanguageConnectionContext var1, CompilerContext var2) throws StandardException {
      String var5 = "VALUES " + var0;
      CompilerContext var6 = var1.pushCompilerContext();
      Parser var3 = var6.getParser();
      Visitable var7 = var3.parseStatement(var5);
      ValueNode var4 = ((ResultColumn)((CursorNode)var7).getResultSetNode().getResultColumns().elementAt(0)).getExpression();
      var1.popCompilerContext(var6);
      return var4;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
   }

   boolean isEquivalent(ValueNode var1) {
      return false;
   }
}
