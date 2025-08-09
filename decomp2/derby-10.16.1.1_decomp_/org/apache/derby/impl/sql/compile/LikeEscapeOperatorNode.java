package org.apache.derby.impl.sql.compile;

import java.util.Arrays;
import java.util.List;
import org.apache.derby.iapi.services.compiler.MethodBuilder;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.Like;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.shared.common.error.StandardException;

public final class LikeEscapeOperatorNode extends TernaryOperatorNode {
   boolean addedEquals;
   String escape;

   LikeEscapeOperatorNode(ValueNode var1, ValueNode var2, ValueNode var3, ContextManager var4) {
      super(var1, var2, var3, 3, var4);
   }

   ValueNode bindExpression(FromList var1, SubqueryList var2, List var3) throws StandardException {
      super.bindExpression(var1, var2, var3);
      String var4 = null;
      if (!this.leftOperand.requiresTypeFromContext() && !this.leftOperand.getTypeId().isStringTypeId()) {
         throw StandardException.newException("42884", new Object[]{"LIKE", "FUNCTION"});
      } else if (this.rightOperand != null && !this.rightOperand.requiresTypeFromContext() && !this.rightOperand.getTypeId().isStringTypeId()) {
         throw StandardException.newException("42884", new Object[]{"LIKE", "FUNCTION"});
      } else {
         if (this.receiver.requiresTypeFromContext()) {
            this.receiver.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), true));
            if (!this.leftOperand.requiresTypeFromContext()) {
               this.receiver.setCollationInfo(this.leftOperand.getTypeServices());
            } else if (this.rightOperand != null && !this.rightOperand.requiresTypeFromContext()) {
               this.receiver.setCollationInfo(this.rightOperand.getTypeServices());
            } else {
               this.receiver.setCollationUsingCompilationSchema();
            }
         }

         if (this.leftOperand.requiresTypeFromContext()) {
            if (this.receiver.getTypeId().isStringTypeId()) {
               this.leftOperand.setType(this.receiver.getTypeServices());
            } else {
               this.leftOperand.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), true));
            }

            this.leftOperand.setCollationInfo(this.receiver.getTypeServices());
         }

         if (this.rightOperand != null && this.rightOperand.requiresTypeFromContext()) {
            if (this.receiver.getTypeId().isStringTypeId()) {
               this.rightOperand.setType(this.receiver.getTypeServices());
            } else {
               this.rightOperand.setType(new DataTypeDescriptor(TypeId.getBuiltInTypeId(12), true));
            }

            this.rightOperand.setCollationInfo(this.receiver.getTypeServices());
         }

         this.bindToBuiltIn();
         if (!this.receiver.getTypeId().isStringTypeId()) {
            throw StandardException.newException("42884", new Object[]{"LIKE", "FUNCTION"});
         } else {
            if (!this.leftOperand.getTypeId().isStringTypeId()) {
               this.leftOperand = this.castArgToString(this.leftOperand);
            }

            if (this.rightOperand != null) {
               this.rightOperand = this.castArgToString(this.rightOperand);
            }

            boolean var5 = this.leftOperand instanceof CharConstantNode;
            if (var5) {
               var4 = ((CharConstantNode)this.leftOperand).getString();
            }

            boolean var6 = this.rightOperand instanceof CharConstantNode;
            if (var6) {
               this.escape = ((CharConstantNode)this.rightOperand).getString();
               if (this.escape.length() != 1) {
                  throw StandardException.newException("22019", new Object[]{this.escape});
               }
            } else if (this.rightOperand == null) {
               var6 = true;
            }

            if (!this.receiver.getTypeServices().compareCollationInfo(this.leftOperand.getTypeServices())) {
               throw StandardException.newException("42ZA2", new Object[]{this.receiver.getTypeServices().getSQLstring(), this.receiver.getTypeServices().getCollationName(), this.leftOperand.getTypeServices().getSQLstring(), this.leftOperand.getTypeServices().getCollationName()});
            } else {
               if (this.receiver instanceof ColumnReference && var5 && var6 && Like.isOptimizable(var4)) {
                  String var7 = null;
                  if (this.escape != null) {
                     var7 = Like.stripEscapesNoPatternChars(var4, this.escape.charAt(0));
                  } else if (var4.indexOf(95) == -1 && var4.indexOf(37) == -1) {
                     var7 = var4;
                  }

                  if (var7 != null) {
                     ValueNode var8 = this.receiver.getClone();
                     this.addedEquals = true;
                     BinaryComparisonOperatorNode var9 = new BinaryRelationalOperatorNode(0, var8, new CharConstantNode(var7, this.getContextManager()), false, this.getContextManager());
                     var9.setForQueryRewrite(true);
                     var9 = (BinaryComparisonOperatorNode)var9.bindExpression(var1, var2, var3);
                     AndNode var10 = new AndNode(this, var9, this.getContextManager());
                     this.finishBindExpr();
                     var10.postBindFixup();
                     return var10;
                  }
               }

               this.finishBindExpr();
               return this;
            }
         }
      }
   }

   private void finishBindExpr() throws StandardException {
      this.bindComparisonOperator();
      boolean var1 = this.receiver.getTypeServices().isNullable() || this.leftOperand.getTypeServices().isNullable();
      if (this.rightOperand != null) {
         var1 |= this.rightOperand.getTypeServices().isNullable();
      }

      this.setType(new DataTypeDescriptor(TypeId.BOOLEAN_ID, var1));
   }

   public void bindComparisonOperator() throws StandardException {
      TypeId var1 = this.receiver.getTypeId();
      TypeId var2 = this.leftOperand.getTypeId();
      if (!var1.isStringTypeId()) {
         throw StandardException.newException("42X53", new Object[]{var1.getSQLTypeName()});
      } else if (!var2.isStringTypeId()) {
         throw StandardException.newException("42X53", new Object[]{var2.getSQLTypeName()});
      } else if (this.rightOperand != null && !this.rightOperand.getTypeId().isStringTypeId()) {
         throw StandardException.newException("42X53", new Object[]{this.rightOperand.getTypeId().getSQLTypeName()});
      }
   }

   ValueNode preprocess(int var1, FromList var2, SubqueryList var3, PredicateList var4) throws StandardException {
      boolean var5 = false;
      String var6 = null;
      String var7 = null;
      super.preprocess(var1, var2, var3, var4);
      if (this.receiver.getTypeId().getSQLTypeName().equals("CLOB")) {
         return this;
      } else if (this.addedEquals) {
         return this;
      } else if (!(this.leftOperand instanceof CharConstantNode) && !this.leftOperand.requiresTypeFromContext()) {
         return this;
      } else if (!(this.receiver instanceof ColumnReference)) {
         return this;
      } else if (this.receiver.getTypeServices().getCollationType() != 0) {
         return this;
      } else {
         if (this.leftOperand instanceof CharConstantNode) {
            String var8 = ((CharConstantNode)this.leftOperand).getString();
            if (!Like.isOptimizable(var8)) {
               return this;
            }

            int var9 = this.receiver.getTypeServices().getMaximumWidth();
            if (var9 > 32700) {
               return this;
            }

            var6 = Like.greaterEqualString(var8, this.escape, var9);
            var7 = Like.lessThanString(var8, this.escape, var9);
            var5 = !Like.isLikeComparisonNeeded(var8);
         }

         AndNode var12 = null;
         BooleanConstantNode var14 = new BooleanConstantNode(true, this.getContextManager());
         if (var7 != null || this.leftOperand.requiresTypeFromContext()) {
            Object var10;
            if (this.leftOperand.requiresTypeFromContext()) {
               var10 = this.setupOptimizeStringFromParameter(this.leftOperand, this.rightOperand, "lessThanStringFromParameter", this.receiver.getTypeServices().getMaximumWidth());
            } else {
               var10 = new CharConstantNode(var7, this.getContextManager());
            }

            BinaryRelationalOperatorNode var11 = new BinaryRelationalOperatorNode(4, this.receiver.getClone(), (ValueNode)var10, false, this.getContextManager());
            ((BinaryComparisonOperatorNode)var11).setForQueryRewrite(true);
            ((BinaryComparisonOperatorNode)var11).bindComparisonOperator();
            ((BinaryComparisonOperatorNode)var11).setBetweenSelectivity();
            var12 = new AndNode(var11, var14, this.getContextManager());
            var12.postBindFixup();
         }

         Object var15;
         if (this.leftOperand.requiresTypeFromContext()) {
            var15 = this.setupOptimizeStringFromParameter(this.leftOperand, this.rightOperand, "greaterEqualStringFromParameter", this.receiver.getTypeServices().getMaximumWidth());
         } else {
            var15 = new CharConstantNode(var6, this.getContextManager());
         }

         BinaryRelationalOperatorNode var16 = new BinaryRelationalOperatorNode(1, this.receiver.getClone(), (ValueNode)var15, false, this.getContextManager());
         ((BinaryComparisonOperatorNode)var16).setForQueryRewrite(true);
         ((BinaryComparisonOperatorNode)var16).bindComparisonOperator();
         ((BinaryComparisonOperatorNode)var16).setBetweenSelectivity();
         if (var12 == null) {
            var12 = new AndNode(var16, var14, this.getContextManager());
         } else {
            var12 = new AndNode(var16, var12, this.getContextManager());
         }

         var12.postBindFixup();
         if (!var5) {
            var12 = new AndNode(this, var12, this.getContextManager());
            var12.postBindFixup();
         }

         this.setTransformed();
         return var12;
      }
   }

   void generateExpression(ExpressionClassBuilder var1, MethodBuilder var2) throws StandardException {
      this.receiver.generateExpression(var1, var2);
      this.receiverInterfaceType = this.receiver.getTypeCompiler().interfaceName();
      var2.upCast(this.receiverInterfaceType);
      this.leftOperand.generateExpression(var1, var2);
      var2.upCast(this.leftInterfaceType);
      if (this.rightOperand != null) {
         this.rightOperand.generateExpression(var1, var2);
         var2.upCast(this.rightInterfaceType);
      }

      var2.callMethod((short)185, (String)null, this.methodName, this.resultInterfaceType, this.rightOperand == null ? 1 : 2);
   }

   private ValueNode setupOptimizeStringFromParameter(ValueNode var1, ValueNode var2, String var3, int var4) throws StandardException {
      if (var2 != null) {
         var3 = var3 + "WithEsc";
      }

      StaticMethodCallNode var5 = new StaticMethodCallNode(var3, "org.apache.derby.iapi.types.Like", this.getContextManager());
      var5.internalCall = true;
      NumericConstantNode var6 = new NumericConstantNode(TypeId.getBuiltInTypeId(4), var4, this.getContextManager());
      ValueNode[] var7 = var2 == null ? new ValueNode[]{var1, var6} : new ValueNode[]{var1, var2, var6};
      var5.addParms(Arrays.asList(var7));
      ValueNode var8 = new JavaToSQLValueNode(var5, this.getContextManager());
      var8 = var8.bindExpression((FromList)null, (SubqueryList)null, (List)null);
      CastNode var9 = new CastNode(var8, var1.getTypeServices(), this.getContextManager());
      var9.bindCastNodeOnly();
      return var9;
   }
}
