package org.datanucleus.query.evaluator;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.CaseExpression;
import org.datanucleus.query.expression.CreatorExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.ExpressionEvaluator;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.SubqueryExpression;
import org.datanucleus.query.expression.VariableExpression;

public class AbstractExpressionEvaluator implements ExpressionEvaluator {
   public Object evaluate(Expression expr) {
      return this.compileOrAndExpression(expr);
   }

   protected Object compileOrAndExpression(Expression expr) {
      if (expr.getOperator() == Expression.OP_OR) {
         return this.processOrExpression(expr);
      } else if (expr.getOperator() == Expression.OP_AND) {
         return this.processAndExpression(expr);
      } else if (expr.getOperator() == Expression.OP_BIT_AND) {
         return this.processBitAndExpression(expr);
      } else if (expr.getOperator() == Expression.OP_BIT_OR) {
         return this.processBitOrExpression(expr);
      } else {
         return expr.getOperator() == Expression.OP_BIT_XOR ? this.processBitXorExpression(expr) : this.compileRelationalExpression(expr);
      }
   }

   protected Object compileRelationalExpression(Expression expr) {
      if (expr.getOperator() == Expression.OP_EQ) {
         return this.processEqExpression(expr);
      } else if (expr.getOperator() == Expression.OP_NOTEQ) {
         return this.processNoteqExpression(expr);
      } else if (expr.getOperator() == Expression.OP_LIKE) {
         return this.processLikeExpression(expr);
      } else if (expr.getOperator() == Expression.OP_GTEQ) {
         return this.processGteqExpression(expr);
      } else if (expr.getOperator() == Expression.OP_LTEQ) {
         return this.processLteqExpression(expr);
      } else if (expr.getOperator() == Expression.OP_GT) {
         return this.processGtExpression(expr);
      } else if (expr.getOperator() == Expression.OP_LT) {
         return this.processLtExpression(expr);
      } else if (expr.getOperator() == Expression.OP_IS) {
         return this.processIsExpression(expr);
      } else if (expr.getOperator() == Expression.OP_ISNOT) {
         return this.processIsnotExpression(expr);
      } else if (expr.getOperator() == Expression.OP_CAST) {
         return this.processCastExpression(expr);
      } else if (expr.getOperator() == Expression.OP_IN) {
         return this.processInExpression(expr);
      } else {
         return expr.getOperator() == Expression.OP_NOTIN ? this.processNotInExpression(expr) : this.compileAdditiveMultiplicativeExpression(expr);
      }
   }

   protected Object compileAdditiveMultiplicativeExpression(Expression expr) {
      if (expr.getOperator() == Expression.OP_ADD) {
         return this.processAddExpression(expr);
      } else if (expr.getOperator() == Expression.OP_SUB) {
         return this.processSubExpression(expr);
      } else if (expr.getOperator() == Expression.OP_MUL) {
         return this.processMulExpression(expr);
      } else if (expr.getOperator() == Expression.OP_DIV) {
         return this.processDivExpression(expr);
      } else {
         return expr.getOperator() == Expression.OP_MOD ? this.processModExpression(expr) : this.compileUnaryExpression(expr);
      }
   }

   protected Object compileUnaryExpression(Expression expr) {
      if (expr.getOperator() == Expression.OP_NEG) {
         return this.processNegExpression(expr);
      } else if (expr.getOperator() == Expression.OP_COM) {
         return this.processComExpression(expr);
      } else if (expr.getOperator() == Expression.OP_NOT) {
         return this.processNotExpression(expr);
      } else {
         return expr.getOperator() == Expression.OP_DISTINCT ? this.processDistinctExpression(expr) : this.compilePrimaryExpression(expr);
      }
   }

   protected Object compilePrimaryExpression(Expression expr) {
      if (expr instanceof CreatorExpression) {
         return this.processCreatorExpression((CreatorExpression)expr);
      } else if (expr instanceof PrimaryExpression) {
         return this.processPrimaryExpression((PrimaryExpression)expr);
      } else if (expr instanceof ParameterExpression) {
         return this.processParameterExpression((ParameterExpression)expr);
      } else if (expr instanceof VariableExpression) {
         return this.processVariableExpression((VariableExpression)expr);
      } else if (expr instanceof SubqueryExpression) {
         return this.processSubqueryExpression((SubqueryExpression)expr);
      } else if (expr instanceof CaseExpression) {
         return this.processCaseExpression((CaseExpression)expr);
      } else if (expr instanceof InvokeExpression) {
         return this.processInvokeExpression((InvokeExpression)expr);
      } else {
         return expr instanceof Literal ? this.processLiteral((Literal)expr) : null;
      }
   }

   protected Object processOrExpression(Expression expr) {
      throw new NucleusException("Operation OR is not supported by this mapper");
   }

   protected Object processAndExpression(Expression expr) {
      throw new NucleusException("Operation AND is not supported by this mapper");
   }

   protected Object processBitOrExpression(Expression expr) {
      throw new NucleusException("Operation BITWISE OR is not supported by this mapper");
   }

   protected Object processBitAndExpression(Expression expr) {
      throw new NucleusException("Operation BITWISE AND is not supported by this mapper");
   }

   protected Object processBitXorExpression(Expression expr) {
      throw new NucleusException("Operation BITWISE XOR is not supported by this mapper");
   }

   protected Object processEqExpression(Expression expr) {
      throw new NucleusException("Operation EQ is not supported by this mapper");
   }

   protected Object processNoteqExpression(Expression expr) {
      throw new NucleusException("Operation NOTEQ is not supported by this mapper");
   }

   protected Object processLikeExpression(Expression expr) {
      throw new NucleusException("Operation LIKE is not supported by this mapper");
   }

   protected Object processGtExpression(Expression expr) {
      throw new NucleusException("Operation GT is not supported by this mapper");
   }

   protected Object processLtExpression(Expression expr) {
      throw new NucleusException("Operation LT is not supported by this mapper");
   }

   protected Object processGteqExpression(Expression expr) {
      throw new NucleusException("Operation GTEQ is not supported by this mapper");
   }

   protected Object processLteqExpression(Expression expr) {
      throw new NucleusException("Operation LTEQ is not supported by this mapper");
   }

   protected Object processIsExpression(Expression expr) {
      throw new NucleusException("Operation IS (instanceof) is not supported by this mapper");
   }

   protected Object processIsnotExpression(Expression expr) {
      throw new NucleusException("Operation ISNOT (!instanceof) is not supported by this mapper");
   }

   protected Object processInExpression(Expression expr) {
      throw new NucleusException("Operation IN is not supported by this mapper");
   }

   protected Object processNotInExpression(Expression expr) {
      throw new NucleusException("Operation NOT IN is not supported by this mapper");
   }

   protected Object processAddExpression(Expression expr) {
      throw new NucleusException("Operation ADD is not supported by this mapper");
   }

   protected Object processSubExpression(Expression expr) {
      throw new NucleusException("Operation SUB is not supported by this mapper");
   }

   protected Object processMulExpression(Expression expr) {
      throw new NucleusException("Operation MUL is not supported by this mapper");
   }

   protected Object processDivExpression(Expression expr) {
      throw new NucleusException("Operation DIV is not supported by this mapper");
   }

   protected Object processModExpression(Expression expr) {
      throw new NucleusException("Operation MOD is not supported by this mapper");
   }

   protected Object processNegExpression(Expression expr) {
      throw new NucleusException("Operation NEG is not supported by this mapper");
   }

   protected Object processComExpression(Expression expr) {
      throw new NucleusException("Operation COM is not supported by this mapper");
   }

   protected Object processNotExpression(Expression expr) {
      throw new NucleusException("Operation NOT is not supported by this mapper");
   }

   protected Object processDistinctExpression(Expression expr) {
      throw new NucleusException("Operation DISTINCT is not supported by this mapper");
   }

   protected Object processCreatorExpression(CreatorExpression expr) {
      throw new NucleusException("Creator expression is not supported by this mapper");
   }

   protected Object processPrimaryExpression(PrimaryExpression expr) {
      throw new NucleusException("Primary expression is not supported by this mapper");
   }

   protected Object processParameterExpression(ParameterExpression expr) {
      throw new NucleusException("Parameter expression is not supported by this mapper");
   }

   protected Object processVariableExpression(VariableExpression expr) {
      throw new NucleusException("Variable expression is not supported by this mapper");
   }

   protected Object processSubqueryExpression(SubqueryExpression expr) {
      throw new NucleusException("Subquery expression is not supported by this mapper");
   }

   protected Object processInvokeExpression(InvokeExpression expr) {
      throw new NucleusException("Invoke expression is not supported by this mapper");
   }

   protected Object processCastExpression(Expression expr) {
      throw new NucleusException("Cast expression is not supported by this mapper");
   }

   protected Object processCaseExpression(CaseExpression expr) {
      throw new NucleusException("Case expression is not supported by this mapper");
   }

   protected Object processLiteral(Literal expr) {
      throw new NucleusException("Literals are not supported by this mapper");
   }
}
