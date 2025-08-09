package org.datanucleus.query.inmemory;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.evaluator.AbstractExpressionEvaluator;
import org.datanucleus.query.expression.ArrayExpression;
import org.datanucleus.query.expression.CaseExpression;
import org.datanucleus.query.expression.CreatorExpression;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.query.QueryManager;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Imports;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class InMemoryExpressionEvaluator extends AbstractExpressionEvaluator {
   String queryLanguage = null;
   Deque stack = new LinkedList();
   Map parameterValues;
   Map variableValues;
   Map state;
   Imports imports;
   ExecutionContext ec;
   ClassLoaderResolver clr;
   QueryManager queryMgr;
   final String candidateAlias;

   public InMemoryExpressionEvaluator(ExecutionContext ec, Map params, Map state, Imports imports, ClassLoaderResolver clr, String candidateAlias, String queryLang) {
      this.ec = ec;
      this.queryMgr = ec.getStoreManager().getQueryManager();
      this.parameterValues = (Map)(params != null ? params : new HashMap());
      this.state = state;
      this.imports = imports;
      this.clr = clr;
      this.candidateAlias = candidateAlias;
      this.queryLanguage = queryLang;
   }

   public Map getParameterValues() {
      return this.parameterValues;
   }

   public String getQueryLanguage() {
      return this.queryLanguage;
   }

   protected Object processAndExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         this.stack.push(left == Boolean.TRUE && right == Boolean.TRUE ? Boolean.TRUE : Boolean.FALSE);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processOrExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      this.stack.push(left != Boolean.TRUE && right != Boolean.TRUE ? Boolean.FALSE : Boolean.TRUE);
      return this.stack.peek();
   }

   protected Object processBitOrExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (left instanceof Boolean && right instanceof Boolean) {
         this.stack.push(left);
         this.stack.push(right);
         return this.processOrExpression(expr);
      } else if (right instanceof Number && left instanceof Number) {
         Number result = ((Number)left).intValue() | ((Number)right).intValue();
         this.stack.push(result);
         return result;
      } else {
         return super.processBitOrExpression(expr);
      }
   }

   protected Object processBitAndExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (left instanceof Boolean && right instanceof Boolean) {
         this.stack.push(left);
         this.stack.push(right);
         return this.processAndExpression(expr);
      } else if (right instanceof Number && left instanceof Number) {
         Number result = ((Number)left).intValue() & ((Number)right).intValue();
         this.stack.push(result);
         return result;
      } else {
         return super.processBitAndExpression(expr);
      }
   }

   protected Object processBitXorExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (left instanceof Boolean && right instanceof Boolean) {
         this.stack.push(left);
         this.stack.push(right);
         return this.processOrExpression(expr);
      } else if (right instanceof Number && left instanceof Number) {
         Number result = ((Number)left).intValue() ^ ((Number)right).intValue();
         this.stack.push(result);
         return result;
      } else {
         return super.processBitXorExpression(expr);
      }
   }

   protected Object processEqExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         Boolean result = QueryUtils.compareExpressionValues(left, right, expr.getOperator()) ? Boolean.TRUE : Boolean.FALSE;
         this.stack.push(result);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processLikeExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         if (!(left instanceof String)) {
            throw new NucleusUserException("LIKE expression can only be used on a String expression, but found on " + left.getClass().getName());
         } else if (right instanceof String) {
            Boolean result = ((String)left).matches((String)right) ? Boolean.TRUE : Boolean.FALSE;
            this.stack.push(result);
            return result;
         } else {
            throw new NucleusUserException("Dont currently support expression on right of LIKE to be other than String but was " + right.getClass().getName());
         }
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processNoteqExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         Boolean result = QueryUtils.compareExpressionValues(left, right, expr.getOperator()) ? Boolean.TRUE : Boolean.FALSE;
         this.stack.push(result);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processGteqExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         Boolean result = QueryUtils.compareExpressionValues(left, right, expr.getOperator()) ? Boolean.TRUE : Boolean.FALSE;
         this.stack.push(result);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processGtExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         Boolean result = QueryUtils.compareExpressionValues(left, right, expr.getOperator()) ? Boolean.TRUE : Boolean.FALSE;
         this.stack.push(result);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processIsExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         if (!(right instanceof Class)) {
            throw new NucleusException("Attempt to invoke instanceof with argument of type " + right.getClass().getName() + " has to be Class");
         } else {
            try {
               Boolean result = ((Class)right).isAssignableFrom(left.getClass()) ? Boolean.TRUE : Boolean.FALSE;
               this.stack.push(result);
               return result;
            } catch (ClassNotResolvedException var5) {
               throw new NucleusException("Attempt to invoke instanceof with " + right + " yet class was not found!");
            }
         }
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processIsnotExpression(Expression expr) {
      this.processIsExpression(expr);
      Boolean val = (Boolean)this.stack.pop();
      val = !val;
      this.stack.push(val);
      return val;
   }

   protected Object processCastExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         throw new NucleusException("CAST not yet supported in in-memory evaluator");
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processLteqExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         Boolean result = QueryUtils.compareExpressionValues(left, right, expr.getOperator()) ? Boolean.TRUE : Boolean.FALSE;
         this.stack.push(result);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processLtExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      if (!(left instanceof InMemoryFailure) && !(right instanceof InMemoryFailure)) {
         Boolean result = QueryUtils.compareExpressionValues(left, right, expr.getOperator()) ? Boolean.TRUE : Boolean.FALSE;
         this.stack.push(result);
         return this.stack.peek();
      } else {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      }
   }

   protected Object processAddExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      Object value = null;
      if (right instanceof String && left instanceof String) {
         value = "" + left + right;
      } else if (right instanceof Number && left instanceof Number) {
         value = (new BigDecimal(left.toString())).add(new BigDecimal(right.toString()));
      } else {
         if (!(left instanceof String)) {
            throw new NucleusException("Performing ADD operation on " + left + " and " + right + " is not supported");
         }

         value = "" + left + right;
      }

      this.stack.push(value);
      return this.stack.peek();
   }

   protected Object processSubExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      Object value = (new BigDecimal(left.toString())).subtract(new BigDecimal(right.toString()));
      this.stack.push(value);
      return this.stack.peek();
   }

   protected Object processDivExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      double firstValue = (new BigDecimal(left.toString())).doubleValue();
      double secondValue = (new BigDecimal(right.toString())).doubleValue();
      BigDecimal value = new BigDecimal(firstValue / secondValue);
      this.stack.push(value);
      return this.stack.peek();
   }

   protected Object processModExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      BigDecimal firstValue = new BigDecimal(left.toString());
      BigDecimal divisor = new BigDecimal(right.toString());
      Object value = firstValue.subtract(firstValue.divideToIntegralValue(divisor).multiply(divisor));
      this.stack.push(value);
      return this.stack.peek();
   }

   protected Object processMulExpression(Expression expr) {
      Object right = this.stack.pop();
      Object left = this.stack.pop();
      Object value = (new BigDecimal(left.toString())).multiply(new BigDecimal(right.toString()));
      this.stack.push(value);
      return this.stack.peek();
   }

   protected Object processNegExpression(Expression expr) {
      Number val = null;
      if (expr instanceof DyadicExpression) {
         DyadicExpression dyExpr = (DyadicExpression)expr;
         if (dyExpr.getLeft() instanceof PrimaryExpression) {
            val = (Number)this.getValueForPrimaryExpression((PrimaryExpression)expr.getLeft());
         } else {
            if (!(dyExpr.getLeft() instanceof ParameterExpression)) {
               throw new NucleusException("No current support for negation of dyadic expression on type " + dyExpr.getLeft().getClass().getName());
            }

            val = (Number)QueryUtils.getValueForParameterExpression(this.parameterValues, (ParameterExpression)expr.getLeft());
         }

         if (val instanceof Integer) {
            this.stack.push(-val.intValue());
            return this.stack.peek();
         } else if (val instanceof Long) {
            this.stack.push(-val.longValue());
            return this.stack.peek();
         } else if (val instanceof Short) {
            this.stack.push((short)(-val.shortValue()));
            return this.stack.peek();
         } else if (val instanceof BigInteger) {
            this.stack.push(BigInteger.valueOf(-val.longValue()));
            return this.stack.peek();
         } else if (val instanceof Double) {
            this.stack.push(-val.doubleValue());
            return this.stack.peek();
         } else if (val instanceof Float) {
            this.stack.push(-val.floatValue());
            return this.stack.peek();
         } else if (val instanceof BigDecimal) {
            this.stack.push(new BigDecimal(-val.doubleValue()));
            return this.stack.peek();
         } else {
            throw new NucleusException("Attempt to negate value of type " + val + " not supported");
         }
      } else if (expr instanceof Literal) {
         throw new NucleusException("No current support for negation of expression of type Literal");
      } else {
         throw new NucleusException("No current support for negation of expression of type " + expr.getClass().getName());
      }
   }

   protected Object processComExpression(Expression expr) {
      PrimaryExpression primExpr = (PrimaryExpression)expr.getLeft();
      Object primVal = this.getValueForPrimaryExpression(primExpr);
      int val = -1;
      if (primVal instanceof Number) {
         val = ((Number)primVal).intValue();
      }

      Integer result = ~val;
      this.stack.push(result);
      return this.stack.peek();
   }

   protected Object processNotExpression(Expression expr) {
      Object left = this.stack.pop();
      if (left instanceof InMemoryFailure) {
         this.stack.push(Boolean.FALSE);
         return this.stack.peek();
      } else {
         Boolean leftExpr = (Boolean)left;
         Boolean result = leftExpr ? Boolean.FALSE : Boolean.TRUE;
         this.stack.push(result);
         return this.stack.peek();
      }
   }

   protected Object processCreatorExpression(CreatorExpression expr) {
      List params = new ArrayList();

      for(int i = 0; i < expr.getArguments().size(); ++i) {
         params.add(((Expression)expr.getArguments().get(i)).evaluate(this));
      }

      Class cls = this.imports.resolveClassDeclaration(expr.getId(), this.clr, (ClassLoader)null);
      Object value = QueryUtils.createResultObjectUsingArgumentedConstructor(cls, params.toArray(), (Class[])null);
      this.stack.push(value);
      return value;
   }

   protected Object processInvokeExpression(InvokeExpression expr) {
      Object result = this.getValueForInvokeExpression(expr);
      this.stack.push(result);
      return result;
   }

   protected Object processLiteral(Literal expr) {
      Object value = expr.getLiteral();
      this.stack.push(value);
      return value;
   }

   protected Object processVariableExpression(VariableExpression expr) {
      if (expr.getLeft() == null && this.state.containsKey(expr.getId())) {
         Object value = this.state.get(expr.getId());
         if (value == null) {
            NucleusLogger.QUERY.warn("Variable expression " + expr.getId() + " doesnt have its value set yet. Unsupported query structure");
            value = new InMemoryFailure();
         }

         this.stack.push(value);
         return value;
      } else {
         try {
            Object varExprValue = this.getValueForVariableExpression(expr);
            return varExprValue;
         } catch (VariableNotSetException var3) {
            return super.processVariableExpression(expr);
         }
      }
   }

   protected Object processParameterExpression(ParameterExpression expr) {
      Object value = QueryUtils.getValueForParameterExpression(this.parameterValues, expr);
      this.stack.push(value);
      return value;
   }

   protected Object processPrimaryExpression(PrimaryExpression expr) {
      Object paramValue = this.parameterValues != null ? this.parameterValues.get(expr.getId()) : null;
      if (expr.getLeft() == null && paramValue != null) {
         this.stack.push(paramValue);
         return paramValue;
      } else {
         Object value = this.getValueForPrimaryExpression(expr);
         this.stack.push(value);
         return value;
      }
   }

   protected Object processCaseExpression(CaseExpression expr) {
      for(CaseExpression.ExpressionPair pair : expr.getConditions()) {
         Expression whenExpr = pair.getWhenExpression();
         Expression actionExpr = pair.getActionExpression();
         Object keyResult = whenExpr.evaluate(this);
         if (!(keyResult instanceof Boolean)) {
            NucleusLogger.QUERY.error("Case expression " + expr + " clause " + whenExpr + " did not return boolean");
            Object value = new InMemoryFailure();
            this.stack.push(value);
            return value;
         }

         if ((Boolean)keyResult) {
            Object value = actionExpr.evaluate(this);
            this.stack.push(value);
            return value;
         }
      }

      Object value = expr.getElseExpression().evaluate(this);
      this.stack.push(value);
      return value;
   }

   public Object getValueForInvokeExpression(InvokeExpression invokeExpr) {
      String method = invokeExpr.getOperation();
      if (invokeExpr.getLeft() != null) {
         if (invokeExpr.getLeft() instanceof ParameterExpression) {
            Object invokedValue = QueryUtils.getValueForParameterExpression(this.parameterValues, (ParameterExpression)invokeExpr.getLeft());
            Class invokedType = invokedValue != null ? invokedValue.getClass() : invokeExpr.getLeft().getSymbol().getValueType();
            InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod(invokedType, method);
            if (methodEval != null) {
               return methodEval.evaluate(invokeExpr, invokedValue, this);
            } else {
               NucleusLogger.QUERY.warn("Query contains call to method " + invokedValue.getClass().getName() + "." + method + " yet no support is available for this");
               return new InMemoryFailure();
            }
         } else if (invokeExpr.getLeft() instanceof PrimaryExpression) {
            Object invokedValue = this.getValueForPrimaryExpression((PrimaryExpression)invokeExpr.getLeft());
            if (invokedValue instanceof InMemoryFailure) {
               return invokedValue;
            } else {
               Class invokedType = invokedValue != null ? invokedValue.getClass() : invokeExpr.getLeft().getSymbol().getValueType();
               InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod(invokedType, method);
               if (methodEval != null) {
                  return methodEval.evaluate(invokeExpr, invokedValue, this);
               } else {
                  NucleusLogger.QUERY.warn("Query contains call to method " + invokedType.getName() + "." + method + " yet no support is available for this");
                  return new InMemoryFailure();
               }
            }
         } else if (invokeExpr.getLeft() instanceof InvokeExpression) {
            Object invokedValue = this.getValueForInvokeExpression((InvokeExpression)invokeExpr.getLeft());
            Class invokedType = invokedValue != null ? invokedValue.getClass() : (invokeExpr.getLeft().getSymbol() != null ? invokeExpr.getLeft().getSymbol().getValueType() : null);
            if (invokedType == null) {
               return new InMemoryFailure();
            } else {
               InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod(invokedType, method);
               if (methodEval != null) {
                  return methodEval.evaluate(invokeExpr, invokedValue, this);
               } else {
                  NucleusLogger.QUERY.warn("Query contains call to method " + invokedType.getName() + "." + method + " yet no support is available for this");
                  return new InMemoryFailure();
               }
            }
         } else if (invokeExpr.getLeft() instanceof VariableExpression) {
            Object invokedValue = this.getValueForVariableExpression((VariableExpression)invokeExpr.getLeft());
            Class invokedType = invokedValue != null ? invokedValue.getClass() : invokeExpr.getLeft().getSymbol().getValueType();
            InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod(invokedType, method);
            if (methodEval != null) {
               return methodEval.evaluate(invokeExpr, invokedValue, this);
            } else {
               NucleusLogger.QUERY.warn("Query contains call to method " + invokedType.getName() + "." + method + " yet no support is available for this");
               return new InMemoryFailure();
            }
         } else if (invokeExpr.getLeft() instanceof Literal) {
            Object invokedValue = ((Literal)invokeExpr.getLeft()).getLiteral();
            Class invokedType = invokedValue != null ? invokedValue.getClass() : invokeExpr.getLeft().getSymbol().getValueType();
            InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod(invokedType, method);
            if (methodEval != null) {
               return methodEval.evaluate(invokeExpr, invokedValue, this);
            } else {
               NucleusLogger.QUERY.warn("Query contains call to method " + invokedType.getName() + "." + method + " yet no support is available for this");
               return new InMemoryFailure();
            }
         } else if (invokeExpr.getLeft() instanceof ArrayExpression) {
            Object invokedValue = this.getValueForArrayExpression((ArrayExpression)invokeExpr.getLeft());
            Class invokedType = invokedValue.getClass();
            InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod(invokedType, method);
            if (methodEval != null) {
               return methodEval.evaluate(invokeExpr, invokedValue, this);
            } else {
               NucleusLogger.QUERY.warn("Query contains call to method " + invokedType.getName() + "." + method + " yet no support is available for this");
               return new InMemoryFailure();
            }
         } else {
            NucleusLogger.QUERY.warn("No support is available for in-memory evaluation of methods invoked on expressions of type " + invokeExpr.getLeft().getClass().getName());
            return new InMemoryFailure();
         }
      } else if (method.toLowerCase().equals("count")) {
         Collection coll = (Collection)this.state.get("DATANUCLEUS_RESULTS_SET");
         SetExpression setexpr = new SetExpression(coll, this.candidateAlias);
         Expression paramExpr = (Expression)invokeExpr.getArguments().get(0);
         if (paramExpr.getOperator() == Expression.OP_DISTINCT) {
            Collection processable = new HashSet(coll);
         }

         int stackSizeOrig = this.stack.size();
         Object returnVal = setexpr.count(paramExpr, this);

         while(this.stack.size() > stackSizeOrig) {
            this.stack.pop();
         }

         return returnVal;
      } else if (method.toLowerCase().equals("sum")) {
         Collection coll = (Collection)this.state.get("DATANUCLEUS_RESULTS_SET");
         SetExpression setexpr = new SetExpression(coll, this.candidateAlias);
         Expression paramExpr = (Expression)invokeExpr.getArguments().get(0);
         if (paramExpr.getOperator() == Expression.OP_DISTINCT) {
            Collection processable = new HashSet(coll);
         }

         int stackSizeOrig = this.stack.size();
         Object returnVal = setexpr.sum(paramExpr, this, this.state);

         while(this.stack.size() > stackSizeOrig) {
            this.stack.pop();
         }

         return returnVal;
      } else if (method.toLowerCase().equals("avg")) {
         Collection coll = (Collection)this.state.get("DATANUCLEUS_RESULTS_SET");
         SetExpression setexpr = new SetExpression(coll, this.candidateAlias);
         Expression paramExpr = (Expression)invokeExpr.getArguments().get(0);
         if (paramExpr.getOperator() == Expression.OP_DISTINCT) {
            Collection processable = new HashSet(coll);
         }

         int stackSizeOrig = this.stack.size();
         Object returnVal = setexpr.avg(paramExpr, this, this.state);

         while(this.stack.size() > stackSizeOrig) {
            this.stack.pop();
         }

         return returnVal;
      } else if (method.toLowerCase().equals("min")) {
         Collection coll = (Collection)this.state.get("DATANUCLEUS_RESULTS_SET");
         SetExpression setexpr = new SetExpression(coll, this.candidateAlias);
         Expression paramExpr = (Expression)invokeExpr.getArguments().get(0);
         if (paramExpr.getOperator() == Expression.OP_DISTINCT) {
            Collection processable = new HashSet(coll);
         }

         int stackSizeOrig = this.stack.size();
         Object returnVal = setexpr.min(paramExpr, this, this.state);

         while(this.stack.size() > stackSizeOrig) {
            this.stack.pop();
         }

         return returnVal;
      } else if (!method.toLowerCase().equals("max")) {
         InvocationEvaluator methodEval = this.queryMgr.getInMemoryEvaluatorForMethod((Class)null, method);
         if (methodEval != null) {
            return methodEval.evaluate(invokeExpr, (Object)null, this);
         } else {
            NucleusLogger.QUERY.warn("Query contains call to static method " + method + " yet no support is available for in-memory evaluation of this");
            return new InMemoryFailure();
         }
      } else {
         Collection coll = (Collection)this.state.get("DATANUCLEUS_RESULTS_SET");
         SetExpression setexpr = new SetExpression(coll, this.candidateAlias);
         Expression paramExpr = (Expression)invokeExpr.getArguments().get(0);
         if (paramExpr.getOperator() == Expression.OP_DISTINCT) {
            Collection processable = new HashSet(coll);
         }

         int stackSizeOrig = this.stack.size();
         Object returnVal = setexpr.max(paramExpr, this, this.state);

         while(this.stack.size() > stackSizeOrig) {
            this.stack.pop();
         }

         return returnVal;
      }
   }

   private Object getValueForArrayExpression(ArrayExpression arrayExpr) {
      Object value = new Object[arrayExpr.getArraySize()];

      for(int i = 0; i < Array.getLength(value); ++i) {
         Expression elem = arrayExpr.getElement(i);
         if (elem instanceof Literal) {
            Array.set(value, i, ((Literal)elem).getLiteral());
         } else if (elem instanceof PrimaryExpression) {
            Array.set(value, i, this.getValueForPrimaryExpression((PrimaryExpression)elem));
         } else {
            if (!(elem instanceof ParameterExpression)) {
               NucleusLogger.QUERY.warn("No support is available for array expression with element of type " + elem.getClass().getName());
               return new InMemoryFailure();
            }

            Array.set(value, i, QueryUtils.getValueForParameterExpression(this.parameterValues, (ParameterExpression)elem));
         }
      }

      return value;
   }

   public int getIntegerForLiteral(Literal lit) {
      Object val = lit.getLiteral();
      if (val instanceof BigDecimal) {
         return ((BigDecimal)val).intValue();
      } else if (val instanceof BigInteger) {
         return ((BigInteger)val).intValue();
      } else if (val instanceof Long) {
         return ((Long)val).intValue();
      } else if (val instanceof Integer) {
         return (Integer)val;
      } else if (val instanceof Short) {
         return ((Short)val).intValue();
      } else {
         throw new NucleusException("Attempt to convert literal with value " + val + " (" + val.getClass().getName() + ") into an int failed");
      }
   }

   public Object getValueForPrimaryExpression(PrimaryExpression primExpr) {
      Object value = null;
      if (primExpr.getLeft() != null) {
         if (primExpr.getLeft() instanceof DyadicExpression) {
            DyadicExpression dyExpr = (DyadicExpression)primExpr.getLeft();
            if (dyExpr.getOperator() != Expression.OP_CAST) {
               NucleusLogger.QUERY.error("Dont currently support PrimaryExpression starting with DyadicExpression of " + dyExpr);
               return new InMemoryFailure();
            }

            Expression castLeftExpr = dyExpr.getLeft();
            if (castLeftExpr instanceof PrimaryExpression) {
               value = this.getValueForPrimaryExpression((PrimaryExpression)castLeftExpr);
               String castClassName = (String)((Literal)dyExpr.getRight()).getLiteral();
               if (value != null) {
                  Class castClass = this.imports.resolveClassDeclaration(castClassName, this.clr, (ClassLoader)null);
                  if (!castClass.isAssignableFrom(value.getClass())) {
                     NucleusLogger.QUERY.warn("Candidate for query results in attempt to cast " + StringUtils.toJVMIDString(value) + " to " + castClass.getName() + " which is impossible!");
                     return new InMemoryFailure();
                  }
               }
            } else {
               if (!(castLeftExpr instanceof VariableExpression)) {
                  NucleusLogger.QUERY.warn("Dont currently support CastExpression of " + castLeftExpr);
                  return new InMemoryFailure();
               }

               value = this.getValueForVariableExpression((VariableExpression)castLeftExpr);
               String castClassName = (String)((Literal)dyExpr.getRight()).getLiteral();
               if (value != null) {
                  Class castClass = this.imports.resolveClassDeclaration(castClassName, this.clr, (ClassLoader)null);
                  if (!castClass.isAssignableFrom(value.getClass())) {
                     NucleusLogger.QUERY.warn("Candidate for query results in attempt to cast " + StringUtils.toJVMIDString(value) + " to " + castClass.getName() + " which is impossible!");
                     return new InMemoryFailure();
                  }
               }
            }
         } else if (primExpr.getLeft() instanceof ParameterExpression) {
            value = QueryUtils.getValueForParameterExpression(this.parameterValues, (ParameterExpression)primExpr.getLeft());
         } else {
            if (!(primExpr.getLeft() instanceof VariableExpression)) {
               NucleusLogger.QUERY.warn("Dont currently support PrimaryExpression with left-side of " + primExpr.getLeft());
               return new InMemoryFailure();
            }

            VariableExpression varExpr = (VariableExpression)primExpr.getLeft();

            try {
               value = this.getValueForVariableExpression(varExpr);
            } catch (VariableNotSetException var9) {
               NucleusLogger.QUERY.error("Attempt to access variable " + varExpr.getId() + " as part of primaryExpression " + primExpr + " : variable is not yet set!");
               return new InMemoryFailure();
            }
         }
      }

      int firstTupleToProcess = 0;
      if (value == null) {
         if (this.state.containsKey(primExpr.getTuples().get(0))) {
            value = this.state.get(primExpr.getTuples().get(0));
            firstTupleToProcess = 1;
         } else if (this.state.containsKey(this.candidateAlias)) {
            value = this.state.get(this.candidateAlias);
         }
      }

      for(int i = firstTupleToProcess; i < primExpr.getTuples().size(); ++i) {
         String fieldName = (String)primExpr.getTuples().get(i);
         if (!fieldName.equals(this.candidateAlias)) {
            boolean getValueByReflection = true;
            if (this.ec.getApiAdapter().isPersistent(value)) {
               ObjectProvider valueOP = this.ec.findObjectProvider(value);
               if (valueOP != null) {
                  AbstractMemberMetaData mmd = valueOP.getClassMetaData().getMetaDataForMember(fieldName);
                  if (mmd == null) {
                     NucleusLogger.QUERY.error("Cannot find " + fieldName + " member of " + valueOP.getClassMetaData().getFullClassName());
                     return new InMemoryFailure();
                  }

                  if (mmd.getAbsoluteFieldNumber() >= 0) {
                     valueOP.isLoaded(mmd.getAbsoluteFieldNumber());
                     value = valueOP.provideField(mmd.getAbsoluteFieldNumber());
                     getValueByReflection = false;
                  }
               }
            }

            if (getValueByReflection) {
               value = ClassUtils.getValueOfFieldByReflection(value, fieldName);
            }
         }
      }

      return value;
   }

   public void setVariableValue(String id, Object value) {
      if (this.variableValues == null) {
         this.variableValues = new HashMap();
      }

      this.variableValues.put(id, value);
   }

   public void removeVariableValue(String id) {
      this.variableValues.remove(id);
   }

   public Object getValueForVariableExpression(VariableExpression varExpr) {
      if (this.variableValues != null && this.variableValues.containsKey(varExpr.getId())) {
         return this.variableValues.get(varExpr.getId());
      } else {
         throw new VariableNotSetException(varExpr);
      }
   }

   public Map getVariableExpressionValues() {
      return this.variableValues;
   }
}
