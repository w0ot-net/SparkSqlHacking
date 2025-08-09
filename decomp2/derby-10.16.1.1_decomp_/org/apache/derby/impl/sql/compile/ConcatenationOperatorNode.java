package org.apache.derby.impl.sql.compile;

import java.util.List;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.TypeCompiler;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.StringDataValue;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

class ConcatenationOperatorNode extends BinaryOperatorNode {
   ConcatenationOperatorNode(ValueNode var1, ValueNode var2, ContextManager var3) {
      super(var1, var2, "||", "concatenate", "org.apache.derby.iapi.types.ConcatableDataValue", "org.apache.derby.iapi.types.ConcatableDataValue", var3);
   }

   ValueNode evaluateConstantExpressions() throws StandardException {
      if (this.leftOperand instanceof CharConstantNode && this.rightOperand instanceof CharConstantNode) {
         CharConstantNode var1 = (CharConstantNode)this.leftOperand;
         CharConstantNode var2 = (CharConstantNode)this.rightOperand;
         StringDataValue var3 = (StringDataValue)var1.getValue();
         StringDataValue var4 = (StringDataValue)var2.getValue();
         StringDataValue var5 = (StringDataValue)this.getTypeServices().getNull();
         var5.concatenate(var3, var4, var5);
         return new CharConstantNode(var5.getString(), this.getContextManager());
      } else {
         return this;
      }
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      this.leftOperand = this.leftOperand.bindExpression(var1, var2, var3);
      this.rightOperand = this.rightOperand.bindExpression(var1, var2, var3);
      if (this.leftOperand.requiresTypeFromContext()) {
         if (this.rightOperand.requiresTypeFromContext()) {
            throw StandardException.newException("42X35", new Object[]{this.operator});
         }

         TypeId var4;
         if (this.rightOperand.getTypeId().isBitTypeId()) {
            if (this.rightOperand.getTypeId().isBlobTypeId()) {
               var4 = TypeId.getBuiltInTypeId(2004);
            } else {
               var4 = TypeId.getBuiltInTypeId(-3);
            }
         } else if (this.rightOperand.getTypeId().isClobTypeId()) {
            var4 = TypeId.getBuiltInTypeId(2005);
         } else {
            var4 = TypeId.getBuiltInTypeId(12);
         }

         this.leftOperand.setType(new DataTypeDescriptor(var4, true));
         if (this.rightOperand.getTypeId().isStringTypeId()) {
            this.leftOperand.setCollationInfo(this.rightOperand.getTypeServices());
         }
      }

      if (this.rightOperand.requiresTypeFromContext()) {
         TypeId var6;
         if (this.leftOperand.getTypeId().isBitTypeId()) {
            if (this.leftOperand.getTypeId().isBlobTypeId()) {
               var6 = TypeId.getBuiltInTypeId(2004);
            } else {
               var6 = TypeId.getBuiltInTypeId(-3);
            }
         } else if (this.leftOperand.getTypeId().isClobTypeId()) {
            var6 = TypeId.getBuiltInTypeId(2005);
         } else {
            var6 = TypeId.getBuiltInTypeId(12);
         }

         this.rightOperand.setType(new DataTypeDescriptor(var6, true));
         if (this.leftOperand.getTypeId().isStringTypeId()) {
            this.rightOperand.setCollationInfo(this.leftOperand.getTypeServices());
         }
      }

      if (this.leftOperand.getTypeId().userType()) {
         this.leftOperand = this.leftOperand.genSQLJavaSQLTree();
      }

      if (this.rightOperand.getTypeId().userType()) {
         this.rightOperand = this.rightOperand.genSQLJavaSQLTree();
      }

      TypeCompiler var7 = this.leftOperand.getTypeCompiler();
      if (!this.leftOperand.getTypeId().isStringTypeId() && !this.leftOperand.getTypeId().isBitTypeId()) {
         DataTypeDescriptor var5 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, true, var7.getCastToCharWidth(this.leftOperand.getTypeServices()));
         this.leftOperand = new CastNode(this.leftOperand, var5, this.getContextManager());
         this.leftOperand.setCollationUsingCompilationSchema();
         ((CastNode)this.leftOperand).bindCastNodeOnly();
      }

      var7 = this.rightOperand.getTypeCompiler();
      if (!this.rightOperand.getTypeId().isStringTypeId() && !this.rightOperand.getTypeId().isBitTypeId()) {
         DataTypeDescriptor var10 = DataTypeDescriptor.getBuiltInDataTypeDescriptor(12, true, var7.getCastToCharWidth(this.rightOperand.getTypeServices()));
         this.rightOperand = new CastNode(this.rightOperand, var10, this.getContextManager());
         this.rightOperand.setCollationUsingCompilationSchema();
         ((CastNode)this.rightOperand).bindCastNodeOnly();
      }

      var7 = this.leftOperand.getTypeCompiler();
      this.setType(this.resolveConcatOperation(this.leftOperand.getTypeServices(), this.rightOperand.getTypeServices()));
      this.setLeftRightInterfaceType(var7.interfaceName());
      return this.evaluateConstantExpressions();
   }

   private DataTypeDescriptor resolveConcatOperation(DataTypeDescriptor var1, DataTypeDescriptor var2) throws StandardException {
      TypeId var3 = var1.getTypeId();
      TypeId var4 = var2.getTypeId();
      if (var3.isConcatableTypeId() && var4.isConcatableTypeId() && (!var4.isBitTypeId() || !var3.isStringTypeId()) && (!var3.isBitTypeId() || !var4.isStringTypeId())) {
         String var5 = var3.typePrecedence() >= var4.typePrecedence() ? var1.getTypeName() : var2.getTypeName();
         int var6 = var1.getMaximumWidth() + var2.getMaximumWidth();
         if (var3.getJDBCTypeId() != 1 && var3.getJDBCTypeId() != -2) {
            if (var3.getJDBCTypeId() == 12) {
               switch (var4.getJDBCTypeId()) {
                  case 1:
                  case 12:
                     if (var6 > 4000) {
                        var5 = "LONG VARCHAR";
                     }
                     break;
                  case 2005:
                     var6 = clobBlobHandling(var2, var1);
               }
            } else if (var3.getJDBCTypeId() == -3) {
               switch (var4.getJDBCTypeId()) {
                  case -3:
                  case -2:
                     if (var6 > 4000) {
                        var5 = "LONG VARCHAR FOR BIT DATA";
                     }
                     break;
                  case 2004:
                     var6 = clobBlobHandling(var2, var1);
               }
            } else if (var3.getJDBCTypeId() != 2005 && var3.getJDBCTypeId() != 2004) {
               if (var4.getJDBCTypeId() == 2005 || var4.getJDBCTypeId() == 2004) {
                  var6 = clobBlobHandling(var2, var1);
               }
            } else {
               var6 = clobBlobHandling(var1, var2);
            }
         } else {
            switch (var4.getJDBCTypeId()) {
               case -3:
               case 12:
                  if (var6 > 4000) {
                     if (var4.getJDBCTypeId() == 12) {
                        var5 = "LONG VARCHAR";
                     } else {
                        var5 = "LONG VARCHAR FOR BIT DATA";
                     }
                  }
                  break;
               case -2:
               case 1:
                  if (var6 > 254) {
                     if (var4.getJDBCTypeId() == 1) {
                        var5 = "VARCHAR";
                     } else {
                        var5 = "VARCHAR () FOR BIT DATA";
                     }
                  }
                  break;
               case 2004:
               case 2005:
                  var6 = clobBlobHandling(var2, var1);
            }
         }

         if (var5.equals("LONG VARCHAR")) {
            var6 = 32700;
         } else if (var5.equals("LONG VARCHAR FOR BIT DATA")) {
            var6 = 32700;
         }

         boolean var7 = var1.isNullable() || var2.isNullable();
         DataTypeDescriptor var8 = new DataTypeDescriptor(TypeId.getBuiltInTypeId(var5), var7, var6);
         if (var1.getCollationDerivation() == var2.getCollationDerivation() && var1.getCollationType() == var2.getCollationType()) {
            var8 = var8.getCollatedType(var1.getCollationType(), var1.getCollationDerivation());
         } else {
            var8 = var8.getCollatedType(var8.getCollationDerivation(), 0);
         }

         return var8;
      } else {
         throw StandardException.newException("42884", new Object[]{"||", "FUNCTION"});
      }
   }

   private static int clobBlobHandling(DataTypeDescriptor var0, DataTypeDescriptor var1) throws StandardException {
      int var2;
      if (var1.getTypeId().getJDBCTypeId() != -1 && var1.getTypeId().getJDBCTypeId() != -4) {
         var2 = var0.getMaximumWidth() + var1.getMaximumWidth();
      } else {
         var2 = var0.getMaximumWidth() + '耀';
      }

      return var2 < 1 ? Integer.MAX_VALUE : var2;
   }
}
