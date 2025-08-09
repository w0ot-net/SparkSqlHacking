package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.LocalField;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.loader.ClassInspector;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.JSQLType;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

abstract class JavaValueNode extends QueryTreeNode {
   private boolean mustCastToPrimitive;
   protected boolean forCallStatement;
   private boolean valueReturnedToSQLDomain;
   private boolean returnValueDiscarded;
   protected JSQLType jsqlType;
   private LocalField receiverField;
   private int collationType;

   JavaValueNode(ContextManager var1) {
      super(var1);
   }

   DataTypeDescriptor getDataType() throws StandardException {
      return DataTypeDescriptor.getSQLDataTypeDescriptor(this.getJavaTypeName());
   }

   final boolean isPrimitiveType() throws StandardException {
      JSQLType var1 = this.getJSQLType();
      if (var1 == null) {
         return false;
      } else {
         return var1.getCategory() == 2;
      }
   }

   String getJavaTypeName() throws StandardException {
      JSQLType var1 = this.getJSQLType();
      if (var1 == null) {
         return "";
      } else {
         switch (var1.getCategory()) {
            case 1 -> {
               return var1.getJavaClassName();
            }
            case 2 -> {
               return JSQLType.getPrimitiveName(var1.getPrimitiveKind());
            }
            default -> {
               return "";
            }
         }
      }
   }

   final void setJavaTypeName(String var1) {
      this.jsqlType = new JSQLType(var1);
   }

   String getPrimitiveTypeName() throws StandardException {
      JSQLType var1 = this.getJSQLType();
      if (var1 == null) {
         return "";
      } else {
         switch (var1.getCategory()) {
            case 2 -> {
               return JSQLType.getPrimitiveName(var1.getPrimitiveKind());
            }
            default -> {
               return "";
            }
         }
      }
   }

   final void castToPrimitive(boolean var1) {
      this.mustCastToPrimitive = var1;
   }

   final boolean mustCastToPrimitive() {
      return this.mustCastToPrimitive;
   }

   JSQLType getJSQLType() throws StandardException {
      return this.jsqlType;
   }

   static TypeId mapToTypeID(JSQLType var0) throws StandardException {
      DataTypeDescriptor var1 = var0.getSQLType();
      return var1 == null ? null : var1.getTypeId();
   }

   final void markForCallStatement() {
      this.forCallStatement = true;
   }

   abstract JavaValueNode remapColumnReferencesToExpressions() throws StandardException;

   abstract boolean categorize(JBitSet var1, boolean var2) throws StandardException;

   abstract JavaValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException;

   abstract void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException;

   Object getConstantValueAsObject() throws StandardException {
      return null;
   }

   final void generate(ActivationClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.generateExpression(var1, var2);
   }

   boolean generateReceiver(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      return false;
   }

   int getOrderableVariantType() throws StandardException {
      return 0;
   }

   abstract void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException;

   final boolean generateReceiver(ExpressionClassBuilder var1, MethodBuilder var2, JavaValueNode var3) throws StandardException {
      if (!this.valueReturnedToSQLDomain() && ClassInspector.primitiveType(this.getJavaTypeName())) {
         return false;
      } else {
         String var4 = var3.getJavaTypeName();
         this.receiverField = var1.newFieldDeclaration(2, var4);
         var3.generateExpression(var1, var2);
         var2.putField(this.receiverField);
         return true;
      }
   }

   final void getReceiverExpression(ExpressionClassBuilder var1, MethodBuilder var2, JavaValueNode var3) throws StandardException {
      if (this.receiverField != null) {
         var2.getField(this.receiverField);
      } else {
         var3.generateExpression(var1, var2);
      }

   }

   void returnValueToSQLDomain() {
      this.valueReturnedToSQLDomain = true;
   }

   boolean valueReturnedToSQLDomain() {
      return this.valueReturnedToSQLDomain;
   }

   void markReturnValueDiscarded() {
      this.returnValueDiscarded = true;
   }

   boolean returnValueDiscarded() {
      return this.returnValueDiscarded;
   }

   void checkReliability(ValueNode var1) throws StandardException {
      var1.checkReliability(4, "42Z00.U");
   }

   int getCollationType() {
      return this.collationType;
   }

   void setCollationType(int var1) {
      this.collationType = var1;
   }
}
