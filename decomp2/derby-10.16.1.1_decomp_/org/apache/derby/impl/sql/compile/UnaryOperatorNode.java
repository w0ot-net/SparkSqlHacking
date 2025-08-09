package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class UnaryOperatorNode extends OperatorNode {
   String operator;
   String methodName;
   String resultInterfaceType;
   String receiverInterfaceType;
   ValueNode operand;
   static final int K_XMLPARSE = 0;
   static final int K_XMLSERIALIZE = 1;
   static final int K_BASE = 2;
   final int kind;
   static final String[] UnaryOperators = new String[]{"xmlparse", "xmlserialize"};
   static final String[] UnaryMethodNames = new String[]{"XMLParse", "XMLSerialize"};
   static final String[] UnaryResultTypes = new String[]{"org.apache.derby.iapi.types.XMLDataValue", "org.apache.derby.iapi.types.StringDataValue"};
   static final String[] UnaryArgTypes = new String[]{"org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.XMLDataValue"};
   private DataTypeDescriptor targetType;
   private boolean preserveWhitespace;

   UnaryOperatorNode(ValueNode var1, String var2, String var3, ContextManager var4) throws StandardException {
      super(var4);
      this.operand = var1;
      this.operator = var2;
      this.methodName = var3;
      this.kind = 2;
   }

   UnaryOperatorNode(ValueNode var1, ContextManager var2) {
      super(var2);
      this.operand = var1;
      this.kind = 2;
   }

   UnaryOperatorNode(ValueNode var1, int var2, DataTypeDescriptor var3, boolean var4, ContextManager var5) {
      super(var5);
      this.operand = var1;
      this.kind = var2;
      this.operator = UnaryOperators[this.kind];
      this.methodName = UnaryMethodNames[this.kind];
      this.resultInterfaceType = UnaryResultTypes[this.kind];
      this.receiverInterfaceType = UnaryArgTypes[this.kind];
      if (var2 == 1) {
         this.targetType = var3;
      } else if (var2 == 0) {
         this.preserveWhitespace = var4;
      }

   }

   void setOperator(String var1) {
      this.operator = var1;
   }

   String getOperatorString() {
      return this.operator;
   }

   void setMethodName(String var1) {
      this.methodName = var1;
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ValueNode getOperand() {
      return this.operand;
   }

   ParameterNode getParameterOperand() throws StandardException {
      if (!this.requiresTypeFromContext()) {
         return null;
      } else {
         UnaryOperatorNode var1;
         for(var1 = this; !(var1.getOperand() instanceof ParameterNode); var1 = (UnaryOperatorNode)var1.getOperand()) {
         }

         return (ParameterNode)var1.getOperand();
      }
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.bindOperand(var1, var2, var3);
      if (this.kind == 0) {
         this.bindXMLParse();
      } else if (this.kind == 1) {
         this.bindXMLSerialize();
      }

      return this;
   }

   protected void bindOperand(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.operand = this.operand.bindExpression(var1, var2, var3);
      if (this.operand.requiresTypeFromContext()) {
         this.bindParameter();
         if (this.operand.getTypeServices() == null) {
            return;
         }
      }

      if (!(this.operand instanceof UntypedNullConstantNode) && this.operand.getTypeId().userType() && !(this instanceof IsNullNode)) {
         this.operand = this.operand.genSQLJavaSQLTree();
      }

   }

   private void bindXMLParse() throws StandardException {
      TypeId var1 = this.operand.getTypeId();
      if (var1 != null) {
         switch (var1.getJDBCTypeId()) {
            case -1:
            case 1:
            case 12:
            case 2005:
               break;
            default:
               throw StandardException.newException("42X25", new Object[]{this.methodName, var1.getSQLTypeName()});
         }
      }

      this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(2009));
   }

   private void bindXMLSerialize() throws StandardException {
      TypeId var1 = this.operand.getTypeId();
      if (var1 != null && !var1.isXMLTypeId()) {
         throw StandardException.newException("42X25", new Object[]{this.methodName, var1.getSQLTypeName()});
      } else {
         TypeId var2 = this.targetType.getTypeId();
         switch (var2.getJDBCTypeId()) {
            case -1:
            case 1:
            case 12:
            case 2005:
               this.setType(this.targetType);
               this.setCollationUsingCompilationSchema();
               return;
            default:
               throw StandardException.newException("42Z73", new Object[]{var2.getSQLTypeName()});
         }
      }
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      if (this.operand != null) {
         this.operand = this.operand.preprocess(var1, var2, var3, var4);
      }

      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return this.operand == null ? false : this.operand.categorize(var1, var2);
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      if (this.operand != null) {
         this.operand = this.operand.remapColumnReferencesToExpressions();
      }

      return this;
   }

   boolean isConstantExpression() {
      return this.operand == null ? true : this.operand.isConstantExpression();
   }

   boolean constantExpression(PredicateList var1) {
      return this.operand == null ? true : this.operand.constantExpression(var1);
   }

   void bindParameter() throws StandardException {
      if (this.kind == 0) {
         throw StandardException.newException("42Z79", new Object[0]);
      } else if (this.kind == 1) {
         throw StandardException.newException("42Z70", new Object[0]);
      } else if (this.operand.getTypeServices() == null) {
         throw StandardException.newException("42X36", new Object[]{this.operator});
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      String var3 = this.kind == 2 ? this.getTypeCompiler().interfaceName() : this.resultInterfaceType;
      boolean var4 = !this.getTypeId().isBooleanTypeId();
      String var5 = this.getReceiverInterfaceName();
      this.operand.generateExpression(var1, var2);
      var2.cast(var5);
      if (var4) {
         LocalField var6 = var1.newFieldDeclaration(2, var3);
         var2.getField(var6);
         int var7 = 1;
         var7 += this.addXmlOpMethodParams(var1, var2, var6);
         var2.callMethod((short)185, (String)null, this.methodName, var3, var7);
         var2.putField(var6);
      } else {
         var2.callMethod((short)185, (String)null, this.methodName, var3, 0);
      }

   }

   String getReceiverInterfaceName() throws StandardException {
      return this.kind != 2 ? this.receiverInterfaceType : this.operand.getTypeCompiler().interfaceName();
   }

   protected int getOrderableVariantType() throws StandardException {
      return this.operand != null ? this.operand.getOrderableVariantType() : 3;
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.operand != null) {
         this.operand = (ValueNode)this.operand.accept(var1);
      }

   }

   int addXmlOpMethodParams(ExpressionClassBuilder var1, MethodBuilder var2, LocalField var3) throws StandardException {
      if (this.kind != 0 && this.kind != 1) {
         return 0;
      } else if (this.kind == 1) {
         var2.push(this.targetType.getJDBCTypeId());
         var2.push(this.targetType.getMaximumWidth());
         var2.push(this.getSchemaDescriptor((String)null, false).getCollationType());
         return 3;
      } else {
         MethodBuilder var4 = var1.getConstructor();
         var1.generateNull(var4, this.getTypeCompiler(), this.getTypeServices().getCollationType());
         var4.setField(var3);
         var2.swap();
         var2.push(this.preserveWhitespace);
         pushSqlXmlUtil(var1, var2, (String)null, (String)null);
         return 2;
      }
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         UnaryOperatorNode var2 = (UnaryOperatorNode)var1;
         return this.operator.equals(var2.operator) && (this.operand == var2.operand || this.operand != null && this.operand.isEquivalent(var2.operand));
      }
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((UnaryOperatorNode)var1).kind == this.kind;
   }
}
