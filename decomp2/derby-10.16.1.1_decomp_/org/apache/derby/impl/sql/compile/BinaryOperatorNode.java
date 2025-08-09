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

class BinaryOperatorNode extends OperatorNode {
   String operator;
   String methodName;
   ValueNode receiver;
   static final int PLUS = 1;
   static final int MINUS = 2;
   static final int TIMES = 3;
   static final int DIVIDE = 4;
   static final int CONCATENATE = 5;
   static final int EQ = 6;
   static final int NE = 7;
   static final int GT = 8;
   static final int GE = 9;
   static final int LT = 10;
   static final int LE = 11;
   static final int AND = 12;
   static final int OR = 13;
   static final int LIKE = 14;
   ValueNode leftOperand;
   ValueNode rightOperand;
   String leftInterfaceType;
   String rightInterfaceType;
   String resultInterfaceType;
   static final int K_XMLEXISTS = 0;
   static final int K_XMLQUERY = 1;
   static final int K_BASE = 2;
   final int kind;
   static final String[] BinaryOperators = new String[]{"xmlexists", "xmlquery"};
   static final String[] BinaryMethodNames = new String[]{"XMLExists", "XMLQuery"};
   static final String[] BinaryResultTypes = new String[]{"org.apache.derby.iapi.types.BooleanDataValue", "org.apache.derby.iapi.types.XMLDataValue"};
   static final String[][] BinaryArgTypes = new String[][]{{"org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.XMLDataValue"}, {"org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.XMLDataValue"}};
   private String xmlQuery;

   BinaryOperatorNode(ContextManager var1) {
      super(var1);
      this.kind = 2;
   }

   BinaryOperatorNode(ValueNode var1, ValueNode var2, String var3, String var4, String var5, String var6, ContextManager var7) {
      super(var7);
      this.leftOperand = var1;
      this.rightOperand = var2;
      this.operator = var3;
      this.methodName = var4;
      this.leftInterfaceType = var5;
      this.rightInterfaceType = var6;
      this.kind = 2;
   }

   BinaryOperatorNode(ValueNode var1, ValueNode var2, String var3, String var4, ContextManager var5) {
      super(var5);
      this.leftOperand = var1;
      this.rightOperand = var2;
      this.leftInterfaceType = var3;
      this.rightInterfaceType = var4;
      this.kind = 2;
   }

   BinaryOperatorNode(ValueNode var1, ValueNode var2, int var3, ContextManager var4) {
      super(var4);
      this.leftOperand = var1;
      this.rightOperand = var2;
      this.kind = var3;
      this.operator = BinaryOperators[this.kind];
      this.methodName = BinaryMethodNames[this.kind];
      this.leftInterfaceType = BinaryArgTypes[this.kind][0];
      this.rightInterfaceType = BinaryArgTypes[this.kind][1];
      this.resultInterfaceType = BinaryResultTypes[this.kind];
   }

   public String toString() {
      return "";
   }

   void setOperator(String var1) {
      this.operator = var1;
   }

   void setMethodName(String var1) {
      this.methodName = var1;
   }

   void setLeftRightInterfaceType(String var1) {
      this.leftInterfaceType = var1;
      this.rightInterfaceType = var1;
   }

   void printSubNodes(int var1) {
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.leftOperand = this.leftOperand.bindExpression(var1, var2, var3);
      this.rightOperand = this.rightOperand.bindExpression(var1, var2, var3);
      if (this.kind != 0 && this.kind != 1) {
         if (this.leftOperand.requiresTypeFromContext()) {
            if (this.rightOperand.requiresTypeFromContext()) {
               throw StandardException.newException("42X35", new Object[]{this.operator});
            }

            this.leftOperand.setType(this.rightOperand.getTypeServices());
         }

         if (this.rightOperand.requiresTypeFromContext()) {
            this.rightOperand.setType(this.leftOperand.getTypeServices());
         }

         return this.genSQLJavaSQLTree();
      } else {
         return this.bindXMLQuery();
      }
   }

   public ValueNode bindXMLQuery() throws StandardException {
      TypeId var1 = this.rightOperand.getTypeId();
      if (!(this.leftOperand instanceof CharConstantNode)) {
         throw StandardException.newException("42Z75", new Object[0]);
      } else {
         this.xmlQuery = ((CharConstantNode)this.leftOperand).getString();
         if (var1 != null && !var1.isXMLTypeId()) {
            throw StandardException.newException("42Z77", new Object[]{var1.getSQLTypeName()});
         } else if (this.rightOperand.requiresTypeFromContext()) {
            throw StandardException.newException("42Z70", new Object[0]);
         } else {
            if (this.kind == 0) {
               this.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, true));
            } else {
               this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(2009));
            }

            return this.genSQLJavaSQLTree();
         }
      }
   }

   ValueNode genSQLJavaSQLTree() throws StandardException {
      TypeId var1 = this.leftOperand.getTypeId();
      if (var1.userType()) {
         this.leftOperand = this.leftOperand.genSQLJavaSQLTree();
      }

      TypeId var2 = this.rightOperand.getTypeId();
      if (var2.userType()) {
         this.rightOperand = this.rightOperand.genSQLJavaSQLTree();
      }

      return this;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.leftOperand = this.leftOperand.preprocess(var1, var2, var3, var4);
      this.rightOperand = this.rightOperand.preprocess(var1, var2, var3, var4);
      return this;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this instanceof BinaryRelationalOperatorNode) {
         InListOperatorNode var3 = ((BinaryRelationalOperatorNode)this).getInListOp();
         if (var3 != null) {
            var3.generateExpression(var1, var2);
            return;
         }
      }

      boolean var6 = this.kind == 1 || this.kind == 0;
      String var4;
      int var5;
      if (this.leftOperand.getTypeId().typePrecedence() > this.rightOperand.getTypeId().typePrecedence()) {
         this.receiver = this.leftOperand;
         var4 = this.kind == 2 ? this.getReceiverInterfaceName() : this.leftInterfaceType;
         this.leftOperand.generateExpression(var1, var2);
         var2.cast(var4);
         var2.dup();
         var2.cast(this.leftInterfaceType);
         this.rightOperand.generateExpression(var1, var2);
         var2.cast(this.rightInterfaceType);
         var5 = 2;
      } else {
         this.receiver = this.rightOperand;
         var4 = this.kind == 2 ? this.getReceiverInterfaceName() : this.rightInterfaceType;
         this.rightOperand.generateExpression(var1, var2);
         var2.cast(var4);
         if (var6) {
            var5 = 1;
            pushSqlXmlUtil(var1, var2, this.xmlQuery, this.operator);
         } else {
            var5 = 2;
            var2.dup();
            var2.cast(this.rightInterfaceType);
            this.leftOperand.generateExpression(var1, var2);
            var2.cast(this.leftInterfaceType);
            var2.swap();
         }
      }

      String var9 = this.kind == 2 ? this.getTypeCompiler().interfaceName() : this.resultInterfaceType;
      LocalField var7 = this.getTypeId().isBooleanTypeId() ? null : var1.newFieldDeclaration(2, var9);
      if (var7 != null) {
         var2.getField(var7);
         ++var5;
         int var8;
         if (this.getTypeServices() != null && ((var8 = this.getTypeServices().getJDBCTypeId()) == 3 || var8 == 2) && this.operator.equals("/")) {
            var2.push(this.getTypeServices().getScale());
            ++var5;
         }
      }

      var2.callMethod((short)185, var4, this.methodName, var9, var5);
      if (var7 != null) {
         if (this.getTypeId().variableLength() && this.getTypeId().isNumericTypeId()) {
            var2.dup();
            var2.push(this.getTypeServices().getPrecision());
            var2.push(this.getTypeServices().getScale());
            var2.push(true);
            var2.callMethod((short)185, "org.apache.derby.iapi.types.VariableSizeDataValue", "setWidth", "void", 3);
         }

         var2.putField(var7);
      }

   }

   void setLeftOperand(ValueNode var1) {
      this.leftOperand = var1;
   }

   ValueNode getLeftOperand() {
      return this.leftOperand;
   }

   void setRightOperand(ValueNode var1) {
      this.rightOperand = var1;
   }

   ValueNode getRightOperand() {
      return this.rightOperand;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      boolean var3 = this.leftOperand.categorize(var1, var2);
      var3 = this.rightOperand.categorize(var1, var2) && var3;
      return var3;
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.leftOperand = this.leftOperand.remapColumnReferencesToExpressions();
      this.rightOperand = this.rightOperand.remapColumnReferencesToExpressions();
      return this;
   }

   boolean isConstantExpression() {
      return this.leftOperand.isConstantExpression() && this.rightOperand.isConstantExpression();
   }

   boolean constantExpression(PredicateList var1) {
      return this.leftOperand.constantExpression(var1) && this.rightOperand.constantExpression(var1);
   }

   String getReceiverInterfaceName() throws StandardException {
      return this.receiver.getTypeCompiler().interfaceName();
   }

   protected int getOrderableVariantType() throws StandardException {
      int var1 = this.leftOperand.getOrderableVariantType();
      int var2 = this.rightOperand.getOrderableVariantType();
      return Math.min(var1, var2);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.leftOperand != null) {
         this.leftOperand = (ValueNode)this.leftOperand.accept(var1);
      }

      if (this.rightOperand != null) {
         this.rightOperand = (ValueNode)this.rightOperand.accept(var1);
      }

   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((BinaryOperatorNode)var1).kind == this.kind;
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         BinaryOperatorNode var2 = (BinaryOperatorNode)var1;
         return this.methodName.equals(var2.methodName) && this.leftOperand.isEquivalent(var2.leftOperand) && this.rightOperand.isEquivalent(var2.rightOperand);
      }
   }
}
