package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.JSQLType;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public class ParameterNode extends ValueNode {
   private int parameterNumber;
   private DataTypeDescriptor[] userParameterTypes;
   private DataValueDescriptor defaultValue;
   private JSQLType jsqlType;
   private int orderableVariantType = 2;
   private ValueNode returnOutputParameter;
   private ValueNode valToGenerate;

   ParameterNode(int var1, DataValueDescriptor var2, ContextManager var3) {
      super(var3);
      this.parameterNumber = var1;
      this.defaultValue = var2;
   }

   int getParameterNumber() {
      return this.parameterNumber;
   }

   void setDescriptors(DataTypeDescriptor[] var1) {
      this.userParameterTypes = var1;
   }

   void setType(DataTypeDescriptor var1) throws StandardException {
      var1 = var1.getNullabilityType(true);
      if (this.userParameterTypes != null) {
         this.userParameterTypes[this.parameterNumber] = var1;
      }

      super.setType(var1);
      if (this.getJSQLType() == null) {
         this.setJSQLType(new JSQLType(var1));
      }

   }

   void setReturnOutputParam(ValueNode var1) {
      this.returnOutputParameter = var1;
   }

   boolean isReturnOutputParam() {
      return this.returnOutputParameter != null;
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.checkReliability("?", 8);
      return this;
   }

   boolean isConstantExpression() {
      return true;
   }

   boolean constantExpression(PredicateList var1) {
      return true;
   }

   protected int getOrderableVariantType() {
      return this.orderableVariantType;
   }

   void setOrderableVariantType(int var1) {
      this.orderableVariantType = var1;
   }

   public void setJSQLType(JSQLType var1) {
      this.jsqlType = var1;
   }

   public JSQLType getJSQLType() {
      return this.jsqlType;
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      if (this.valToGenerate != null) {
         this.valToGenerate.generateExpression(var1, var2);
      } else {
         DataTypeDescriptor var3 = this.getTypeServices();
         if (var3 != null && var3.getTypeId().isXMLTypeId()) {
            throw StandardException.newException("42Z70", new Object[0]);
         } else {
            var2.pushThis();
            var2.push(this.parameterNumber);
            var2.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "getParameter", "org.apache.derby.iapi.types.DataValueDescriptor", 1);
            switch (var3.getJDBCTypeId()) {
               case -4:
               case -3:
               case -2:
               case 2004:
                  var2.dup();
                  var2.push(var3.getMaximumWidth());
                  var2.callMethod((short)185, (String)null, "checkHostVariable", "void", 1);
               default:
                  var2.cast(this.getTypeCompiler().interfaceName());
            }
         }
      }
   }

   TypeId getTypeId() throws StandardException {
      return this.returnOutputParameter != null ? this.returnOutputParameter.getTypeId() : super.getTypeId();
   }

   static void generateParameterValueSet(ExpressionClassBuilder var0, int var1, List var2) throws StandardException {
      if (var1 > 0) {
         MethodBuilder var3 = var0.getConstructor();
         boolean var4 = ((ParameterNode)var2.get(0)).isReturnOutputParam();
         var3.pushThis();
         var3.push(var1);
         var3.push(var4);
         var3.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "setParameterValueSet", "void", 2);
         MethodBuilder var5 = var0.getExecuteMethod();
         var5.pushThis();
         var5.callMethod((short)182, "org.apache.derby.impl.sql.execute.BaseActivation", "throwIfMissingParms", "void", 0);
      }

   }

   DataValueDescriptor getDefaultValue() {
      return this.defaultValue;
   }

   boolean requiresTypeFromContext() {
      return true;
   }

   boolean isParameterNode() {
      return true;
   }

   boolean isEquivalent(ValueNode var1) {
      return false;
   }

   protected void setValueToGenerate(ValueNode var1) {
      this.valToGenerate = var1;
   }
}
