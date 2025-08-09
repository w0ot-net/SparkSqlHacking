package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.JBitSet;
import org.apache.derby.shared.common.error.StandardException;

class ValueNodeList extends QueryTreeNodeVector {
   ValueNodeList(ContextManager var1) {
      super(ValueNode.class, var1);
   }

   void addValueNode(ValueNode var1) throws StandardException {
      this.addElement(var1);
   }

   void bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ValueNode var6 = (ValueNode)this.elementAt(var5);
         var6 = var6.bindExpression(var1, var2, var3);
         this.setElementAt(var6, var5);
      }

   }

   void genSQLJavaSQLTrees() throws StandardException {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         ValueNode var3 = (ValueNode)this.elementAt(var2);
         if (var3.getTypeId().userType()) {
            this.setElementAt(var3.genSQLJavaSQLTree(), var2);
         }
      }

   }

   DataTypeDescriptor getDominantTypeServices() throws StandardException {
      DataTypeDescriptor var1 = null;
      int var2 = -1;
      int var3 = -1;
      boolean var4 = false;

      for(int var5 = 0; var5 < this.size(); ++var5) {
         ValueNode var6 = (ValueNode)this.elementAt(var5);
         if (!var6.requiresTypeFromContext() || var6.getTypeServices() != null) {
            DataTypeDescriptor var7 = var6.getTypeServices();
            if (var7.getTypeId().isStringTypeId()) {
               if (var2 == -1) {
                  var2 = var7.getCollationDerivation();
                  var3 = var7.getCollationType();
               } else if (!var4) {
                  if (var2 != var7.getCollationDerivation()) {
                     var4 = true;
                  } else if (var3 != var7.getCollationType()) {
                     var4 = true;
                  }
               }
            }

            if (var1 == null) {
               var1 = var7;
            } else {
               var1 = var1.getDominantType(var7, this.getClassFactory());
            }
         }
      }

      if (var2 != -1 && var4) {
         var1 = var1.getCollatedType(var1.getCollationType(), 0);
      }

      return var1;
   }

   DataTypeDescriptor getTypeServices() throws StandardException {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         ValueNode var3 = (ValueNode)this.elementAt(var2);
         DataTypeDescriptor var4 = var3.getTypeServices();
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   boolean allSamePrecendence(int var1) {
      boolean var2 = true;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ValueNode var5 = (ValueNode)this.elementAt(var4);
         DataTypeDescriptor var6 = var5.getTypeServices();
         if (var6 == null) {
            return false;
         }

         if (var1 != var6.getTypeId().typePrecedence()) {
            return false;
         }
      }

      return var2;
   }

   void compatible(ValueNode var1) throws StandardException {
      TypeId var2 = var1.getTypeId();
      TypeCompiler var3 = var1.getTypeCompiler();

      for(ValueNode var5 : this) {
         if (!var5.requiresTypeFromContext() && !var3.compatible(var5.getTypeId())) {
            throw StandardException.newException("42815.S.171", new Object[]{var2.getSQLTypeName(), var5.getTypeId().getSQLTypeName()});
         }
      }

   }

   void comparable(ValueNode var1) throws StandardException {
      int var2 = this.size();

      for(int var4 = 0; var4 < var2; ++var4) {
         ValueNode var3 = (ValueNode)this.elementAt(var4);
         if (!var1.getTypeServices().comparable(var3.getTypeServices(), false, this.getClassFactory())) {
            throw StandardException.newException("42818", new Object[]{var1.getTypeServices().getSQLTypeNameWithCollation(), var3.getTypeServices().getSQLTypeNameWithCollation()});
         }
      }

   }

   boolean isNullable() throws StandardException {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         if (((ValueNode)this.elementAt(var2)).getTypeServices().isNullable()) {
            return true;
         }
      }

      return false;
   }

   boolean containsParameterNode() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         if (((ValueNode)this.elementAt(var2)).requiresTypeFromContext()) {
            return true;
         }
      }

      return false;
   }

   boolean containsAllParameterNodes() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         if (!((ValueNode)this.elementAt(var2)).requiresTypeFromContext()) {
            return false;
         }
      }

      return true;
   }

   boolean containsAllConstantNodes() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         if (!(this.elementAt(var2) instanceof ConstantNode)) {
            return false;
         }
      }

      return true;
   }

   boolean containsOnlyConstantAndParamNodes() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         ValueNode var3 = (ValueNode)this.elementAt(var2);
         if (!var3.requiresTypeFromContext() && !(var3 instanceof ConstantNode)) {
            return false;
         }
      }

      return true;
   }

   void sortInAscendingOrder(DataValueDescriptor var1) throws StandardException {
      int var2 = this.size();
      boolean var3 = true;

      while(var3) {
         var3 = false;

         for(int var4 = 1; var4 < var2; ++var4) {
            ConstantNode var5 = (ConstantNode)this.elementAt(var4);
            DataValueDescriptor var6 = var5.getValue();
            ConstantNode var7 = (ConstantNode)this.elementAt(var4 - 1);
            DataValueDescriptor var8 = var7.getValue();
            if (var1 == null && var8.compare(var6) > 0 || var1 != null && var1.greaterThan(var8, var6).equals(true)) {
               this.setElementAt(var5, var4 - 1);
               this.setElementAt(var7, var4);
               var3 = true;
            }
         }
      }

   }

   void eliminateNots(boolean var1) throws StandardException {
      for(int var2 = 0; var2 < this.size(); ++var2) {
         this.setElementAt(((ValueNode)this.elementAt(var2)).eliminateNots(var1), var2);
      }

   }

   void setParameterDescriptor(DataTypeDescriptor var1) throws StandardException {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         ValueNode var4 = (ValueNode)this.elementAt(var3);
         if (var4.requiresTypeFromContext()) {
            var4.setType(var1);
         }
      }

   }

   void preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      int var5 = this.size();

      for(int var6 = 0; var6 < var5; ++var6) {
         ValueNode var7 = ((ValueNode)this.elementAt(var6)).preprocess(var1, var2, var3, var4);
         this.setElementAt(var7, var6);
      }

   }

   ValueNodeList remapColumnReferencesToExpressions() throws StandardException {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         this.setElementAt(((ValueNode)this.elementAt(var2)).remapColumnReferencesToExpressions(), var2);
      }

      return this;
   }

   boolean isEquivalent(ValueNodeList var1) throws StandardException {
      if (this.size() != var1.size()) {
         return false;
      } else {
         for(int var2 = 0; var2 < this.size(); ++var2) {
            ValueNode var3 = (ValueNode)this.elementAt(var2);
            ValueNode var4 = (ValueNode)var1.elementAt(var2);
            if (!var3.isEquivalent(var4)) {
               return false;
            }
         }

         return true;
      }
   }

   boolean isConstantExpression() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         boolean var3 = ((ValueNode)this.elementAt(var2)).isConstantExpression();
         if (!var3) {
            return var3;
         }
      }

      return true;
   }

   boolean constantExpression(PredicateList var1) {
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         boolean var4 = ((ValueNode)this.elementAt(var3)).constantExpression(var1);
         if (!var4) {
            return var4;
         }
      }

      return true;
   }

   boolean categorize(JBitSet var1, boolean var2) throws StandardException {
      boolean var3 = true;
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         var3 = ((ValueNode)this.elementAt(var5)).categorize(var1, var2) && var3;
      }

      return var3;
   }

   protected int getOrderableVariantType() throws StandardException {
      int var1 = 3;
      int var2 = this.size();

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = ((ValueNode)this.elementAt(var3)).getOrderableVariantType();
         var1 = Math.min(var1, var4);
      }

      return var1;
   }
}
