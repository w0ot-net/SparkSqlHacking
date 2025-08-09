package org.datanucleus.query.expression;

import java.lang.reflect.Field;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class DyadicExpression extends Expression {
   private static final long serialVersionUID = -5200201785041579804L;

   public DyadicExpression(Expression.MonadicOperator op, Expression operand) {
      super(op, operand);
   }

   public DyadicExpression(Expression operand1, Expression.DyadicOperator op, Expression operand2) {
      super(operand1, op, operand2);
   }

   public Object evaluate(ExpressionEvaluator eval) {
      this.left.evaluate(eval);
      if (this.right != null) {
         this.right.evaluate(eval);
      }

      return super.evaluate(eval);
   }

   public Symbol bind(SymbolTable symtbl) {
      if (this.left != null) {
         try {
            this.left.bind(symtbl);
         } catch (PrimaryExpressionIsClassLiteralException peil) {
            this.left = peil.getLiteral();
            this.left.bind(symtbl);
         } catch (PrimaryExpressionIsClassStaticFieldException peil) {
            Field fld = peil.getLiteralField();

            try {
               Object value = fld.get((Object)null);
               this.left = new Literal(value);
               this.left.bind(symtbl);
            } catch (Exception e) {
               throw new NucleusUserException("Error processing static field " + fld.getName(), e);
            }
         } catch (PrimaryExpressionIsVariableException pive) {
            this.left = pive.getVariableExpression();
            this.left.bind(symtbl);
         } catch (PrimaryExpressionIsInvokeException piie) {
            this.left = piie.getInvokeExpression();
            this.left.bind(symtbl);
         }
      }

      if (this.right != null) {
         try {
            this.right.bind(symtbl);
         } catch (PrimaryExpressionIsClassLiteralException peil) {
            this.right = peil.getLiteral();
            this.right.bind(symtbl);
         } catch (PrimaryExpressionIsClassStaticFieldException peil) {
            Field fld = peil.getLiteralField();

            try {
               Object value = fld.get((Object)null);
               this.right = new Literal(value);
               this.right.bind(symtbl);
            } catch (Exception e) {
               throw new NucleusUserException("Error processing static field " + fld.getName(), e);
            }
         } catch (PrimaryExpressionIsVariableException pive) {
            this.right = pive.getVariableExpression();
            this.right.bind(symtbl);
         } catch (PrimaryExpressionIsInvokeException piie) {
            this.right = piie.getInvokeExpression();
            this.right.bind(symtbl);
         }
      }

      if (this.left != null && this.left instanceof VariableExpression) {
         Symbol leftSym = this.left.getSymbol();
         if (leftSym != null && leftSym.getValueType() == null && this.right instanceof Literal && ((Literal)this.right).getLiteral() != null) {
            leftSym.setValueType(((Literal)this.right).getLiteral().getClass());
         }
      }

      if (this.right != null) {
         Symbol rightSym = this.right.getSymbol();
         if (rightSym != null && rightSym.getValueType() == null && this.left instanceof Literal && ((Literal)this.left).getLiteral() != null) {
            rightSym.setValueType(((Literal)this.left).getLiteral().getClass());
         }
      }

      if (this.op == Expression.OP_EQ || this.op == Expression.OP_NOTEQ || this.op == Expression.OP_GT || this.op == Expression.OP_GTEQ || this.op == Expression.OP_LT || this.op == Expression.OP_LTEQ) {
         Class leftType = this.left.getSymbol() != null ? this.left.getSymbol().getValueType() : null;
         Class rightType = this.right.getSymbol() != null ? this.right.getSymbol().getValueType() : null;
         if (this.left instanceof ParameterExpression && leftType == null && rightType != null) {
            this.left.getSymbol().setValueType(rightType);
         } else if (this.right instanceof ParameterExpression && rightType == null && leftType != null) {
            this.right.getSymbol().setValueType(leftType);
         }

         if (this.left.getSymbol() != null) {
            this.left.getSymbol().getValueType();
         } else {
            Object var10000 = null;
         }

         if (this.right.getSymbol() != null) {
            this.right.getSymbol().getValueType();
         } else {
            Object var20 = null;
         }
      }

      return null;
   }

   public String toString() {
      return "DyadicExpression{" + this.getLeft() + " " + this.getOperator() + " " + this.getRight() + "}";
   }
}
