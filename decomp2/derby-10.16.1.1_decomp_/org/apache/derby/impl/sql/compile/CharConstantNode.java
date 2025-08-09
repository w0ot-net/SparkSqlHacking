package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.StringDataValue;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class CharConstantNode extends ConstantNode {
   static final int K_CHAR = 0;
   static final int K_VARCHAR = 1;
   static final int K_LONGVARCHAR = 2;
   static final int K_CLOB = 3;
   final int kind;

   CharConstantNode(String var1, ContextManager var2) throws StandardException {
      super(TypeId.CHAR_ID, var1 == null, var1 != null ? var1.length() : 0, var2);
      this.setValue(this.getDataValueFactory().getCharDataValue(var1));
      this.kind = 0;
   }

   CharConstantNode(TypeId var1, ContextManager var2) throws StandardException {
      super(var1, true, 0, var2);
      this.kind = 0;
   }

   CharConstantNode(int var1, TypeId var2, ContextManager var3) throws StandardException {
      super(var2, true, 0, var3);
      this.kind = var1;
   }

   CharConstantNode(String var1, int var2, ContextManager var3) throws StandardException {
      super(TypeId.CHAR_ID, var1 == null, var2, var3);
      this.kind = 0;
      if (var1.length() > var2) {
         throw StandardException.newException("22001", new Object[]{"CHAR", var1, String.valueOf(var2)});
      } else {
         while(var1.length() < var2) {
            var1 = var1 + " ";
         }

         this.setValue(this.getDataValueFactory().getCharDataValue(var1));
      }
   }

   String getString() throws StandardException {
      return this.value.getString();
   }

   Object getConstantValueAsObject() throws StandardException {
      return this.value.getString();
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.setCollationUsingCompilationSchema();
      this.value = ((StringDataValue)this.value).getValue(this.getLanguageConnectionContext().getDataValueFactory().getCharacterCollator(this.getTypeServices().getCollationType()));
      return this;
   }

   void generateConstant(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      var2.push(this.getString());
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((CharConstantNode)var1).kind == this.kind;
   }
}
