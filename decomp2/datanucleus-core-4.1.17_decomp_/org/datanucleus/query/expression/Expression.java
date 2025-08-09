package org.datanucleus.query.expression;

import java.io.Serializable;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public abstract class Expression implements Serializable {
   private static final long serialVersionUID = -847871617806099111L;
   protected Expression parent;
   protected Operator op;
   protected Expression left;
   protected Expression right;
   protected Symbol symbol;
   protected String alias;
   public static final DyadicOperator OP_OR = new DyadicOperator("OR", 0, true);
   public static final DyadicOperator OP_AND = new DyadicOperator("AND", 1, true);
   public static final MonadicOperator OP_NOT = new MonadicOperator("NOT ", 2);
   public static final DyadicOperator OP_EQ = new DyadicOperator("=", 3, false);
   public static final DyadicOperator OP_NOTEQ = new DyadicOperator("<>", 3, false);
   public static final DyadicOperator OP_LT = new DyadicOperator("<", 3, false);
   public static final DyadicOperator OP_LTEQ = new DyadicOperator("<=", 3, false);
   public static final DyadicOperator OP_GT = new DyadicOperator(">", 3, false);
   public static final DyadicOperator OP_GTEQ = new DyadicOperator(">=", 3, false);
   public static final DyadicOperator OP_LIKE = new DyadicOperator("LIKE", 3, false);
   public static final DyadicOperator OP_IS = new DyadicOperator("IS", 3, false);
   public static final DyadicOperator OP_ISNOT = new DyadicOperator("IS NOT", 3, false);
   public static final DyadicOperator OP_CAST = new DyadicOperator("CAST", 3, false);
   public static final DyadicOperator OP_IN = new DyadicOperator("IN", 3, false);
   public static final DyadicOperator OP_NOTIN = new DyadicOperator("NOT IN", 3, false);
   public static final DyadicOperator OP_BIT_OR = new DyadicOperator("|", 3, false);
   public static final DyadicOperator OP_BIT_XOR = new DyadicOperator("^", 3, false);
   public static final DyadicOperator OP_BIT_AND = new DyadicOperator("&", 3, false);
   public static final DyadicOperator OP_ADD = new DyadicOperator("+", 4, true);
   public static final DyadicOperator OP_SUB = new DyadicOperator("-", 4, false);
   public static final DyadicOperator OP_CONCAT = new DyadicOperator("||", 4, true);
   public static final DyadicOperator OP_MUL = new DyadicOperator("*", 5, true);
   public static final DyadicOperator OP_DIV = new DyadicOperator("/", 5, false);
   public static final DyadicOperator OP_MOD = new DyadicOperator("%", 5, false);
   public static final MonadicOperator OP_NEG = new MonadicOperator("-", 6);
   public static final MonadicOperator OP_COM = new MonadicOperator("~", 6);
   public static final MonadicOperator OP_DISTINCT = new MonadicOperator("DISTINCT", 6);

   protected Expression() {
   }

   protected Expression(MonadicOperator op, Expression operand) {
      this.op = op;
      this.left = operand;
      if (this.left != null) {
         this.left.parent = this;
      }

   }

   protected Expression(Expression operand1, DyadicOperator op, Expression operand2) {
      this.op = op;
      this.left = operand1;
      this.right = operand2;
      if (this.left != null) {
         this.left.parent = this;
      }

      if (this.right != null) {
         this.right.parent = this;
      }

   }

   public Expression getParent() {
      return this.parent;
   }

   public void setLeft(Expression expr) {
      this.left = expr;
   }

   public void setRight(Expression expr) {
      this.right = expr;
   }

   public Operator getOperator() {
      return this.op;
   }

   public Expression getLeft() {
      return this.left;
   }

   public Expression getRight() {
      return this.right;
   }

   public Symbol getSymbol() {
      return this.symbol;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public String getAlias() {
      return this.alias;
   }

   public Object evaluate(ExpressionEvaluator eval) {
      return eval.evaluate(this);
   }

   public abstract Symbol bind(SymbolTable var1);

   public static class Operator implements Serializable {
      private static final long serialVersionUID = -5417485338482984402L;
      protected final String symbol;
      protected final int precedence;

      public Operator(String symbol, int precedence) {
         this.symbol = symbol;
         this.precedence = precedence;
      }

      public String toString() {
         return this.symbol;
      }
   }

   public static class MonadicOperator extends Operator {
      private static final long serialVersionUID = 1663447359955939741L;

      public MonadicOperator(String symbol, int precedence) {
         super(symbol, precedence);
      }

      public boolean isHigherThan(Operator op) {
         if (op == null) {
            return false;
         } else {
            return this.precedence > op.precedence;
         }
      }
   }

   public static class DyadicOperator extends Operator {
      private static final long serialVersionUID = -2975478176127144417L;
      private final boolean isAssociative;

      public DyadicOperator(String symbol, int precedence, boolean isAssociative) {
         super(" " + symbol + " ", precedence);
         this.isAssociative = isAssociative;
      }

      public boolean isHigherThanLeftSide(Operator op) {
         if (op == null) {
            return false;
         } else {
            return this.precedence > op.precedence;
         }
      }

      public boolean isHigherThanRightSide(Operator op) {
         if (op == null) {
            return false;
         } else if (this.precedence == op.precedence) {
            return !this.isAssociative;
         } else {
            return this.precedence > op.precedence;
         }
      }
   }
}
