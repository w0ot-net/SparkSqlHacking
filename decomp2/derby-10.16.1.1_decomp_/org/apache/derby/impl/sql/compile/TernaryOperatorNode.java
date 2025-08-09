package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class TernaryOperatorNode extends OperatorNode {
   static final int K_TRIM = 0;
   static final int K_LOCATE = 1;
   static final int K_SUBSTRING = 2;
   static final int K_LIKE = 3;
   static final int K_TIMESTAMPADD = 4;
   static final int K_TIMESTAMPDIFF = 5;
   final int kind;
   String operator;
   String methodName;
   ValueNode receiver;
   ValueNode leftOperand;
   ValueNode rightOperand;
   String resultInterfaceType;
   String receiverInterfaceType;
   String leftInterfaceType;
   String rightInterfaceType;
   int trimType;
   static final String[] TernaryOperators = new String[]{"trim", "LOCATE", "substring", "like", "TIMESTAMPADD", "TIMESTAMPDIFF"};
   static final String[] TernaryMethodNames = new String[]{"ansiTrim", "locate", "substring", "like", "timestampAdd", "timestampDiff"};
   static final String[] TernaryResultType = new String[]{"org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.NumberDataValue", "org.apache.derby.iapi.types.ConcatableDataValue", "org.apache.derby.iapi.types.BooleanDataValue", "org.apache.derby.iapi.types.DateTimeDataValue", "org.apache.derby.iapi.types.NumberDataValue"};
   static final String[][] TernaryArgType = new String[][]{{"org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.StringDataValue", "java.lang.Integer"}, {"org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.StringDataValue", "org.apache.derby.iapi.types.NumberDataValue"}, {"org.apache.derby.iapi.types.ConcatableDataValue", "org.apache.derby.iapi.types.NumberDataValue", "org.apache.derby.iapi.types.NumberDataValue"}, {"org.apache.derby.iapi.types.DataValueDescriptor", "org.apache.derby.iapi.types.DataValueDescriptor", "org.apache.derby.iapi.types.DataValueDescriptor"}, {"org.apache.derby.iapi.types.DateTimeDataValue", "java.lang.Integer", "org.apache.derby.iapi.types.NumberDataValue"}, {"org.apache.derby.iapi.types.DateTimeDataValue", "java.lang.Integer", "org.apache.derby.iapi.types.DateTimeDataValue"}};

   TernaryOperatorNode(ValueNode var1, ValueNode var2, ValueNode var3, int var4, ContextManager var5) {
      super(var5);
      this.kind = var4;
      this.constructorMinion(var1, var2, var3, -1);
   }

   TernaryOperatorNode(ValueNode var1, ValueNode var2, ValueNode var3, int var4, int var5, ContextManager var6) {
      super(var6);
      this.kind = var4;
      this.constructorMinion(var1, var2, var3, var5);
   }

   private void constructorMinion(ValueNode var1, ValueNode var2, ValueNode var3, int var4) {
      this.receiver = var1;
      this.leftOperand = var2;
      this.rightOperand = var3;
      this.operator = TernaryOperators[this.kind];
      this.methodName = TernaryMethodNames[this.kind];
      this.resultInterfaceType = TernaryResultType[this.kind];
      this.receiverInterfaceType = TernaryArgType[this.kind][0];
      this.leftInterfaceType = TernaryArgType[this.kind][1];
      this.rightInterfaceType = TernaryArgType[this.kind][2];
      if (var4 != -1) {
         this.trimType = var4;
      }

   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.receiver = this.receiver.bindExpression(var1, var2, var3);
      this.leftOperand = this.leftOperand.bindExpression(var1, var2, var3);
      if (this.rightOperand != null) {
         this.rightOperand = this.rightOperand.bindExpression(var1, var2, var3);
      }

      if (this.kind == 0) {
         this.trimBind();
      } else if (this.kind == 1) {
         this.locateBind();
      } else if (this.kind == 2) {
         this.substrBind();
      } else if (this.kind == 4) {
         this.timestampAddBind();
      } else if (this.kind == 5) {
         this.timestampDiffBind();
      }

      return this;
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.receiver = this.receiver.preprocess(var1, var2, var3, var4);
      this.leftOperand = this.leftOperand.preprocess(var1, var2, var3, var4);
      if (this.rightOperand != null) {
         this.rightOperand = this.rightOperand.preprocess(var1, var2, var3, var4);
      }

      return this;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      byte var3 = 0;
      String var4 = null;
      LocalField var5 = var1.newFieldDeclaration(2, this.resultInterfaceType);
      this.receiver.generateExpression(var1, var2);
      if (this.kind == 0) {
         var2.push(this.trimType);
         this.leftOperand.generateExpression(var1, var2);
         var2.cast(this.leftInterfaceType);
         var2.getField(var5);
         var3 = 3;
         var4 = this.receiverInterfaceType;
      } else if (this.kind == 1) {
         this.leftOperand.generateExpression(var1, var2);
         var2.upCast(this.leftInterfaceType);
         this.rightOperand.generateExpression(var1, var2);
         var2.upCast(this.rightInterfaceType);
         var2.getField(var5);
         var3 = 3;
      } else if (this.kind == 2) {
         this.leftOperand.generateExpression(var1, var2);
         var2.upCast(this.leftInterfaceType);
         if (this.rightOperand != null) {
            this.rightOperand.generateExpression(var1, var2);
            var2.upCast(this.rightInterfaceType);
         } else {
            var2.pushNull(this.rightInterfaceType);
         }

         var2.getField(var5);
         var2.push(this.receiver.getTypeServices().getMaximumWidth());
         var3 = 4;
         var4 = this.receiverInterfaceType;
      } else if (this.kind == 4 || this.kind == 5) {
         Object var6 = this.leftOperand.getConstantValueAsObject();
         var2.push((Integer)var6);
         this.rightOperand.generateExpression(var1, var2);
         var2.upCast(TernaryArgType[this.kind][2]);
         var1.getCurrentDateExpression(var2);
         var2.getField(var5);
         var3 = 4;
         var4 = this.receiverInterfaceType;
      }

      var2.callMethod((short)185, var4, this.methodName, this.resultInterfaceType, var3);
      var2.putField(var5);
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
      boolean var3 = this.receiver.categorize(var1, var2);
      var3 = this.leftOperand.categorize(var1, var2) && var3;
      if (this.rightOperand != null) {
         var3 = this.rightOperand.categorize(var1, var2) && var3;
      }

      return var3;
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.receiver = this.receiver.remapColumnReferencesToExpressions();
      this.leftOperand = this.leftOperand.remapColumnReferencesToExpressions();
      if (this.rightOperand != null) {
         this.rightOperand = this.rightOperand.remapColumnReferencesToExpressions();
      }

      return this;
   }

   boolean isConstantExpression() {
      return this.receiver.isConstantExpression() && this.leftOperand.isConstantExpression() && (this.rightOperand == null || this.rightOperand.isConstantExpression());
   }

   boolean constantExpression(PredicateList var1) {
      return this.receiver.constantExpression(var1) && this.leftOperand.constantExpression(var1) && (this.rightOperand == null || this.rightOperand.constantExpression(var1));
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.receiver != null) {
         this.receiver = (ValueNode)this.receiver.accept(var1);
      }

      if (this.leftOperand != null) {
         this.leftOperand = (ValueNode)this.leftOperand.accept(var1);
      }

      if (this.rightOperand != null) {
         this.rightOperand = (ValueNode)this.rightOperand.accept(var1);
      }

   }

   private ValueNode trimBind() throws StandardException {
      TypeId var2 = TypeId.getBuiltInTypeId(12);
      if (this.receiver.requiresTypeFromContext()) {
         this.receiver.setType(this.getVarcharDescriptor());
         if (!this.leftOperand.requiresTypeFromContext()) {
            this.receiver.setCollationInfo(this.leftOperand.getTypeServices());
         } else {
            this.receiver.setCollationUsingCompilationSchema();
         }
      }

      if (this.leftOperand.requiresTypeFromContext()) {
         this.leftOperand.setType(this.getVarcharDescriptor());
         this.leftOperand.setCollationInfo(this.receiver.getTypeServices());
      }

      this.bindToBuiltIn();
      TypeId var1 = this.receiver.getTypeId();
      if (var1.userType()) {
         this.throwBadType("trim", var1.getSQLTypeName());
      }

      this.receiver = this.castArgToString(this.receiver);
      if (var1.getTypeFormatId() == 444) {
         var2 = var1;
      }

      TypeId var3 = this.leftOperand.getTypeId();
      if (var3.userType()) {
         this.throwBadType("trim", var3.getSQLTypeName());
      }

      this.leftOperand = this.castArgToString(this.leftOperand);
      this.setResultType(var2);
      this.setCollationInfo(this.receiver.getTypeServices());
      return this;
   }

   private void setResultType(TypeId var1) throws StandardException {
      this.setType(new DataTypeDescriptor(var1, true, this.receiver.getTypeServices().getMaximumWidth()));
   }

   ValueNode locateBind() throws StandardException {
      if (this.receiver.requiresTypeFromContext()) {
         if (this.leftOperand.requiresTypeFromContext()) {
            this.receiver.setType(this.getVarcharDescriptor());
            this.receiver.setCollationUsingCompilationSchema();
         } else if (this.leftOperand.getTypeId().isStringTypeId()) {
            this.receiver.setType(this.leftOperand.getTypeServices());
         }
      }

      if (this.leftOperand.requiresTypeFromContext()) {
         if (this.receiver.requiresTypeFromContext()) {
            this.leftOperand.setType(this.getVarcharDescriptor());
         } else if (this.receiver.getTypeId().isStringTypeId()) {
            this.leftOperand.setType(this.receiver.getTypeServices());
         }

         this.leftOperand.setCollationInfo(this.receiver.getTypeServices());
      }

      if (this.rightOperand.requiresTypeFromContext()) {
         this.rightOperand.setType(new DataTypeDescriptor(TypeId.INTEGER_ID, true));
      }

      this.bindToBuiltIn();
      TypeId var2 = this.leftOperand.getTypeId();
      TypeId var3 = this.rightOperand.getTypeId();
      TypeId var1 = this.receiver.getTypeId();
      if (var1.isStringTypeId() && var2.isStringTypeId() && var3.getJDBCTypeId() == 4) {
         this.setType(new DataTypeDescriptor(TypeId.INTEGER_ID, this.receiver.getTypeServices().isNullable()));
         return this;
      } else {
         throw StandardException.newException("42884", new Object[]{"LOCATE", "FUNCTION"});
      }
   }

   protected ValueNode castArgToString(ValueNode var1) throws StandardException {
      TypeCompiler var2 = var1.getTypeCompiler();
      if (!var1.getTypeId().isStringTypeId()) {
         DataTypeDescriptor var3 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, true, var2.getCastToCharWidth(var1.getTypeServices()));
         CastNode var4 = new CastNode(var1, var3, this.getContextManager());
         ((ValueNode)var4).setCollationUsingCompilationSchema();
         ((CastNode)var4).bindCastNodeOnly();
         return var4;
      } else {
         return var1;
      }
   }

   ValueNode substrBind() throws StandardException {
      TypeId var2 = TypeId.getBuiltInTypeId(12);
      if (this.receiver.requiresTypeFromContext()) {
         this.receiver.setType(this.getVarcharDescriptor());
         this.receiver.setCollationUsingCompilationSchema();
      }

      if (this.leftOperand.requiresTypeFromContext()) {
         this.leftOperand.setType(new DataTypeDescriptor(TypeId.INTEGER_ID, true));
      }

      if (this.rightOperand != null && this.rightOperand.requiresTypeFromContext()) {
         this.rightOperand.setType(new DataTypeDescriptor(TypeId.INTEGER_ID, true));
      }

      this.bindToBuiltIn();
      if (this.leftOperand.getTypeId().isNumericTypeId() && (this.rightOperand == null || this.rightOperand.getTypeId().isNumericTypeId())) {
         TypeId var1 = this.receiver.getTypeId();
         switch (var1.getJDBCTypeId()) {
            default:
               this.throwBadType("SUBSTR", var1.getSQLTypeName());
            case -1:
            case 1:
            case 12:
            case 2005:
               if (var1.getTypeFormatId() == 444) {
                  var2 = var1;
               }

               int var3 = this.receiver.getTypeServices().getMaximumWidth();
               if (this.rightOperand != null && this.rightOperand instanceof ConstantNode && ((ConstantNode)this.rightOperand).getValue().getInt() < var3) {
                  var3 = ((ConstantNode)this.rightOperand).getValue().getInt();
               }

               this.setType(new DataTypeDescriptor(var2, true, var3));
               this.setCollationInfo(this.receiver.getTypeServices());
               return this;
         }
      } else {
         throw StandardException.newException("42884", new Object[]{"SUBSTR", "FUNCTION"});
      }
   }

   private ValueNode timestampAddBind() throws StandardException {
      if (!this.bindParameter(this.rightOperand, 4)) {
         int var1 = this.rightOperand.getTypeId().getJDBCTypeId();
         if (var1 != -6 && var1 != 5 && var1 != 4 && var1 != -5) {
            throw StandardException.newException("42X45", new Object[]{this.rightOperand.getTypeId().getSQLTypeName(), 2, this.operator});
         }
      }

      this.bindDateTimeArg(this.receiver, 3);
      this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(93));
      return this;
   }

   private ValueNode timestampDiffBind() throws StandardException {
      this.bindDateTimeArg(this.rightOperand, 2);
      this.bindDateTimeArg(this.receiver, 3);
      this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(-5));
      return this;
   }

   private void bindDateTimeArg(ValueNode var1, int var2) throws StandardException {
      if (!this.bindParameter(var1, 93) && !var1.getTypeId().isDateTimeTimeStampTypeId()) {
         throw StandardException.newException("42X45", new Object[]{var1.getTypeId().getSQLTypeName(), var2, this.operator});
      }
   }

   private boolean bindParameter(ValueNode var1, int var2) throws StandardException {
      if (var1.requiresTypeFromContext() && var1.getTypeId() == null) {
         var1.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(var2), true));
         return true;
      } else {
         return false;
      }
   }

   ValueNode getReceiver() {
      return this.receiver;
   }

   private void throwBadType(String var1, String var2) throws StandardException {
      throw StandardException.newException("42X25", new Object[]{var1, var2});
   }

   protected void bindToBuiltIn() throws StandardException {
      if (this.receiver.getTypeId().userType()) {
         this.receiver = this.receiver.genSQLJavaSQLTree();
      }

      if (this.leftOperand.getTypeId().userType()) {
         this.leftOperand = this.leftOperand.genSQLJavaSQLTree();
      }

      if (this.rightOperand != null && this.rightOperand.getTypeId().userType()) {
         this.rightOperand = this.rightOperand.genSQLJavaSQLTree();
      }

   }

   private DataTypeDescriptor getVarcharDescriptor() {
      return new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), true);
   }

   boolean isSameNodeKind(ValueNode var1) {
      return super.isSameNodeKind(var1) && ((TernaryOperatorNode)var1).kind == this.kind;
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         TernaryOperatorNode var2 = (TernaryOperatorNode)var1;
         return var2.methodName.equals(this.methodName) && var2.receiver.isEquivalent(this.receiver) && var2.leftOperand.isEquivalent(this.leftOperand) && (this.rightOperand == null && var2.rightOperand == null || var2.rightOperand != null && var2.rightOperand.isEquivalent(this.rightOperand));
      }
   }
}
