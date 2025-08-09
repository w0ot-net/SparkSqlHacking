package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataTypeUtilities;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.NumberDataType;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.StandardException;

class CastNode extends ValueNode {
   ValueNode castOperand;
   private int targetCharType;
   TypeId sourceCTI = null;
   private boolean forDataTypeFunction = false;
   private DataTypeDescriptor targetUDT;
   private boolean externallyGeneratedCastNode = false;
   private boolean assignmentSemantics = false;
   private TableName udtTargetName;

   CastNode(ValueNode var1, DataTypeDescriptor var2, ContextManager var3) throws StandardException {
      super(var3);
      this.castOperand = var1;
      if (var2.getTypeId().isUserDefinedTypeId()) {
         this.targetUDT = var2;
      } else {
         this.setType(var2);
      }

   }

   CastNode(ValueNode var1, int var2, int var3, ContextManager var4) throws StandardException {
      super(var4);
      this.castOperand = var1;
      this.targetCharType = var2;
      if (var3 >= 0) {
         this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(this.targetCharType, var3));
      }
   }

   public String toString() {
      return "";
   }

   void printSubNodes(int var1) {
   }

   protected int getOrderableVariantType() throws StandardException {
      return this.castOperand.getOrderableVariantType();
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.castOperand = this.castOperand.bindExpression(var1, var2, var3);
      if (this.targetUDT != null) {
         this.setType(this.targetUDT);
      }

      if (this.getTypeServices() == null) {
         DataTypeDescriptor var4 = this.castOperand.getTypeServices();
         int var5 = -1;
         TypeId var6 = var4.getTypeId();
         if (var6.isNumericTypeId()) {
            var5 = var4.getPrecision() + 1;
            if (var4.getScale() > 0) {
               ++var5;
            }
         } else if (var6.isStringTypeId()) {
            var5 = var4.getMaximumWidth();
            if (this.targetCharType == 1) {
               var5 = Math.min(var5, 254);
            } else if (this.targetCharType == 12) {
               var5 = Math.min(var5, 32672);
            }
         } else {
            TypeId var7 = var4.getTypeId();
            if (var5 < 0) {
               var5 = DataTypeUtilities.getColumnDisplaySize(var7.getJDBCTypeId(), -1);
            }
         }

         if (var5 < 0) {
            var5 = 1;
         }

         this.setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(this.targetCharType, var5));
      }

      if (this.castOperand instanceof UntypedNullConstantNode) {
         this.castOperand.setType(this.getTypeServices());
      }

      this.bindCastNodeOnly();
      if (this.castOperand instanceof ConstantNode && !(this.castOperand instanceof UntypedNullConstantNode)) {
         Object var9 = this;
         int var10 = this.sourceCTI.getJDBCTypeId();
         int var11 = this.getTypeId().getJDBCTypeId();
         switch (var10) {
            case -7:
            case 16:
               if (var11 != -7 && var11 != 16) {
                  if (var11 == 1) {
                     BooleanConstantNode var13 = (BooleanConstantNode)this.castOperand;
                     String var8 = var13.getValueAsString();
                     var9 = new CharConstantNode(var8, this.getTypeServices().getMaximumWidth(), this.getContextManager());
                  }
               } else {
                  var9 = this.castOperand;
               }
               break;
            case 1:
               var9 = this.getCastFromCharConstant(var11);
               break;
            case 3:
               if (var11 == 3 || var11 == 2) {
                  break;
               }
            case -6:
            case -5:
            case 4:
            case 5:
            case 7:
            case 8:
               var9 = this.getCastFromNumericType(((ConstantNode)this.castOperand).getValue(), var11);
               break;
            case 91:
            case 92:
            case 93:
               if (var11 == 1) {
                  String var12 = ((UserTypeConstantNode)this.castOperand).getObjectValue().toString();
                  var9 = new CharConstantNode(var12, this.getTypeServices().getMaximumWidth(), this.getContextManager());
               }
         }

         return (ValueNode)var9;
      } else {
         return this;
      }
   }

   void bindCastNodeOnly() throws StandardException {
      if (this.targetUDT != null) {
         this.setType(this.targetUDT);
      }

      this.sourceCTI = this.castOperand.getTypeId();
      if (this.externallyGeneratedCastNode && this.getTypeId().isStringTypeId()) {
         this.setCollationUsingCompilationSchema();
      }

      if (this.getTypeId().userType()) {
         this.setType(this.bindUserType(this.getTypeServices()));
         String var1 = this.getTypeId().getCorrespondingJavaTypeName();
         this.verifyClassExist(var1);
      }

      if (this.udtTargetName != null) {
         this.udtTargetName.setSchemaName(this.getTypeId().getBaseTypeId().getSchemaName());
      }

      if (this.castOperand.requiresTypeFromContext()) {
         this.castOperand.setType(this.getTypeServices());
      } else if (!(this.castOperand instanceof UntypedNullConstantNode)) {
         TypeCompiler var2 = this.castOperand.getTypeCompiler();
         if (!var2.convertible(this.getTypeId(), this.forDataTypeFunction)) {
            throw StandardException.newException("42846", new Object[]{this.sourceCTI.getSQLTypeName(), this.getTypeId().getSQLTypeName()});
         }
      }

      if (this.castOperand.getTypeServices().getTypeId().isStringTypeId() && this.getTypeId().isBooleanTypeId()) {
         this.setNullability(true);
      } else {
         this.setNullability(this.castOperand.getTypeServices().isNullable());
      }

      if (this.targetUDT != null) {
         this.addUDTUsagePriv(this);
      }

   }

   private ValueNode getCastFromCharConstant(int var1) throws StandardException {
      String var2 = ((CharConstantNode)this.castOperand).getString();
      String var3 = StringUtil.SQLToUpperCase(var2.trim());
      switch (var1) {
         case -7:
         case 16:
            if (var3.equals("TRUE")) {
               return new BooleanConstantNode(true, this.getContextManager());
            } else if (var3.equals("FALSE")) {
               return new BooleanConstantNode(false, this.getContextManager());
            } else {
               if (var3.equals("UNKNOWN")) {
                  return new BooleanConstantNode(this.getContextManager());
               }

               throw StandardException.newException("22018", new Object[]{"boolean"});
            }
         case -6:
         case -5:
         case 4:
         case 5:
            try {
               return this.getCastFromIntegralType((long)Double.parseDouble(var3), var1);
            } catch (NumberFormatException var10) {
               String var11 = TypeId.getBuiltInTypeId(var1).getSQLTypeName();
               throw StandardException.newException("22018", new Object[]{var11});
            }
         case 7:
            Float var5;
            try {
               var5 = Float.valueOf(var3);
            } catch (NumberFormatException var9) {
               throw StandardException.newException("22018", new Object[]{"float"});
            }

            return new NumericConstantNode(TypeId.getBuiltInTypeId(7), var5, this.getContextManager());
         case 8:
            Double var6;
            try {
               var6 = Double.parseDouble(var3);
            } catch (NumberFormatException var8) {
               throw StandardException.newException("22018", new Object[]{"double"});
            }

            return new NumericConstantNode(TypeId.getBuiltInTypeId(8), var6, this.getContextManager());
         case 91:
            return new UserTypeConstantNode(this.getDataValueFactory().getDateValue(var3, false), this.getContextManager());
         case 92:
            return new UserTypeConstantNode(this.getDataValueFactory().getTimeValue(var3, false), this.getContextManager());
         case 93:
            return new UserTypeConstantNode(this.getDataValueFactory().getTimestampValue(var3, false), this.getContextManager());
         default:
            return this;
      }
   }

   private ValueNode getCastFromIntegralType(long var1, int var3) throws StandardException {
      switch (var3) {
         case -6:
            if (var1 >= -128L && var1 <= 127L) {
               return new NumericConstantNode(TypeId.getBuiltInTypeId(-6), (byte)((int)var1), this.getContextManager());
            }

            throw StandardException.newException("22003", new Object[]{"TINYINT"});
         case -5:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var3), var1, this.getContextManager());
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 2:
         case 3:
         case 6:
         default:
            return this;
         case 1:
            return new CharConstantNode(Long.toString(var1), this.getTypeServices().getMaximumWidth(), this.getContextManager());
         case 4:
            if (var1 >= -2147483648L && var1 <= 2147483647L) {
               return new NumericConstantNode(TypeId.getBuiltInTypeId(var3), (int)var1, this.getContextManager());
            }

            throw StandardException.newException("22003", new Object[]{"INTEGER"});
         case 5:
            if (var1 >= -32768L && var1 <= 32767L) {
               return new NumericConstantNode(TypeId.getBuiltInTypeId(var3), (short)((int)var1), this.getContextManager());
            }

            throw StandardException.newException("22003", new Object[]{"SHORT"});
         case 7:
            if ((float)Math.abs(var1) > Float.MAX_VALUE) {
               throw StandardException.newException("22003", new Object[]{"REAL"});
            }

            return new NumericConstantNode(TypeId.getBuiltInTypeId(var3), (float)var1, this.getContextManager());
         case 8:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var3), (double)var1, this.getContextManager());
      }
   }

   private ValueNode getCastFromNumericType(DataValueDescriptor var1, int var2) throws StandardException {
      switch (var2) {
         case -6:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var2), var1.getByte(), this.getContextManager());
         case -5:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var2), var1.getLong(), this.getContextManager());
         case -4:
         case -3:
         case -2:
         case -1:
         case 0:
         case 2:
         case 3:
         case 6:
         default:
            return this;
         case 1:
            return new CharConstantNode(var1.getString(), this.getTypeServices().getMaximumWidth(), this.getContextManager());
         case 4:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var2), var1.getInt(), this.getContextManager());
         case 5:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var2), var1.getShort(), this.getContextManager());
         case 7:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var2), NumberDataType.normalizeREAL(var1.getDouble()), this.getContextManager());
         case 8:
            return new NumericConstantNode(TypeId.getBuiltInTypeId(var2), var1.getDouble(), this.getContextManager());
      }
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      this.castOperand = this.castOperand.preprocess(var1, var2, var3, var4);
      return this;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      return this.castOperand.categorize(var1, var2);
   }

   ValueNode remapColumnReferencesToExpressions() throws StandardException {
      this.castOperand = this.castOperand.remapColumnReferencesToExpressions();
      return this;
   }

   boolean isConstantExpression() {
      return this.castOperand.isConstantExpression();
   }

   boolean constantExpression(PredicateList var1) {
      return this.castOperand.constantExpression(var1);
   }

   Object getConstantValueAsObject() throws StandardException {
      Object var1 = this.castOperand.getConstantValueAsObject();
      if (var1 == null) {
         return null;
      } else {
         return this.sourceCTI.getCorrespondingJavaTypeName().equals(this.getTypeId().getCorrespondingJavaTypeName()) ? var1 : null;
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.castOperand.generateExpression(var1, var2);
      if (!(this.castOperand instanceof UntypedNullConstantNode)) {
         if (this.castOperand.requiresTypeFromContext()) {
            this.sourceCTI = this.getTypeId();
         }

         this.genDataValueConversion(var1, var2);
      }
   }

   private void genDataValueConversion(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      MethodBuilder var3 = var1.getConstructor();
      String var4 = this.getTypeCompiler().interfaceName();
      LocalField var5 = var1.newFieldDeclaration(2, var4);
      var1.generateNull(var3, this.getTypeCompiler(this.getTypeId()), this.getTypeServices().getCollationType());
      var3.setField(var5);
      if (!this.sourceCTI.userType() && !this.getTypeId().userType()) {
         var2.getField(var5);
         var2.swap();
         var2.upCast("org.apache.derby.iapi.types.DataValueDescriptor");
         var2.callMethod((short)185, "org.apache.derby.iapi.types.DataValueDescriptor", "setValue", "void", 1);
      } else {
         var2.callMethod((short)185, "org.apache.derby.iapi.types.DataValueDescriptor", "getObject", "java.lang.Object", 0);
         var2.getField(var5);
         var2.swap();
         String var6 = this.getTypeId().getCorrespondingJavaTypeName();
         var2.dup();
         var2.isInstanceOf(var6);
         var2.push(var6);
         var2.callMethod((short)185, "org.apache.derby.iapi.types.DataValueDescriptor", "setObjectForCast", "void", 3);
      }

      var2.getField(var5);
      if (this.getTypeId().variableLength()) {
         boolean var7 = this.getTypeId().isNumericTypeId();
         var2.dup();
         var2.push(var7 ? this.getTypeServices().getPrecision() : this.getTypeServices().getMaximumWidth());
         var2.push(this.getTypeServices().getScale());
         var2.push(!this.sourceCTI.variableLength() || var7 || this.assignmentSemantics);
         var2.callMethod((short)185, "org.apache.derby.iapi.types.VariableSizeDataValue", "setWidth", "void", 3);
      }

   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.castOperand != null) {
         this.castOperand = (ValueNode)this.castOperand.accept(var1);
      }

      if (this.udtTargetName != null) {
         this.udtTargetName = (TableName)this.udtTargetName.accept(var1);
      }

   }

   void setForExternallyGeneratedCASTnode() {
      this.externallyGeneratedCastNode = true;
   }

   void setForDataTypeFunction(boolean var1) {
      this.forDataTypeFunction = var1;
   }

   void setAssignmentSemantics() {
      this.assignmentSemantics = true;
   }

   boolean isEquivalent(ValueNode var1) throws StandardException {
      if (!this.isSameNodeKind(var1)) {
         return false;
      } else {
         CastNode var2 = (CastNode)var1;
         return this.getTypeServices().equals(var2.getTypeServices()) && this.castOperand.isEquivalent(var2.castOperand);
      }
   }

   void setTargetUDTName(TableName var1) {
      this.udtTargetName = var1;
   }
}
