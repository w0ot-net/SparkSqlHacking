package org.datanucleus.query.inmemory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.ExpressionEvaluator;
import org.datanucleus.util.NucleusLogger;

public class SetExpression {
   String alias = "this";
   Iterator itemIterator;

   public SetExpression(Collection items, String alias) {
      this.itemIterator = items != null ? items.iterator() : null;
      this.alias = alias;
   }

   public Object count(Expression expr, ExpressionEvaluator eval) {
      if (this.itemIterator == null) {
         return 0;
      } else {
         int i;
         for(i = 0; this.itemIterator.hasNext(); ++i) {
            this.itemIterator.next();
         }

         return (long)i;
      }
   }

   public Object min(Expression paramExpr, ExpressionEvaluator eval, Map state) {
      if (this.itemIterator == null) {
         return paramExpr.getSymbol() != null && Number.class.isAssignableFrom(paramExpr.getSymbol().getValueType()) ? 0 : null;
      } else {
         int i = 0;

         Object val;
         for(val = null; this.itemIterator.hasNext(); ++i) {
            state.put(this.alias, this.itemIterator.next());
            Object result = paramExpr.evaluate(eval);
            AggregateExpression memexpr = null;
            if (i == 0) {
               val = result;
            }

            if (result instanceof Float) {
               memexpr = new FloatAggregateExpression((Float)result);
            } else if (result instanceof Double) {
               memexpr = new DoubleAggregateExpression((Double)result);
            } else if (result instanceof Long) {
               memexpr = new LongAggregateExpression((Long)result);
            } else if (result instanceof Integer) {
               memexpr = new IntegerAggregateExpression((Integer)result);
            } else if (result instanceof Short) {
               memexpr = new ShortAggregateExpression((Short)result);
            } else if (result instanceof BigInteger) {
               memexpr = new BigIntegerAggregateExpression((BigInteger)result);
            } else if (result instanceof BigDecimal) {
               memexpr = new BigDecimalAggregateExpression((BigDecimal)result);
            } else if (result instanceof Date) {
               memexpr = new DateAggregateExpression((Date)result);
            } else {
               if (!(result instanceof String)) {
                  if (result instanceof InMemoryFailure) {
                     return result;
                  }

                  throw new NucleusException("Evaluation of min() on object of type " + result.getClass().getName() + " - not supported");
               }

               memexpr = new StringAggregateExpression((String)result);
            }

            if (Boolean.TRUE.equals(memexpr.lt(val))) {
               val = result;
            }
         }

         return val;
      }
   }

   public Object max(Expression paramExpr, ExpressionEvaluator eval, Map state) {
      if (this.itemIterator == null) {
         return paramExpr.getSymbol() != null && Number.class.isAssignableFrom(paramExpr.getSymbol().getValueType()) ? 0 : null;
      } else {
         int i = 0;

         Object val;
         for(val = null; this.itemIterator.hasNext(); ++i) {
            state.put(this.alias, this.itemIterator.next());
            Object result = paramExpr.evaluate(eval);
            AggregateExpression memexpr = null;
            if (i == 0) {
               val = result;
            }

            if (result instanceof Float) {
               memexpr = new FloatAggregateExpression((Float)result);
            } else if (result instanceof Double) {
               memexpr = new DoubleAggregateExpression((Double)result);
            } else if (result instanceof Long) {
               memexpr = new LongAggregateExpression((Long)result);
            } else if (result instanceof Integer) {
               memexpr = new IntegerAggregateExpression((Integer)result);
            } else if (result instanceof Short) {
               memexpr = new ShortAggregateExpression((Short)result);
            } else if (result instanceof BigInteger) {
               memexpr = new BigIntegerAggregateExpression((BigInteger)result);
            } else if (result instanceof BigDecimal) {
               memexpr = new BigDecimalAggregateExpression((BigDecimal)result);
            } else if (result instanceof Date) {
               memexpr = new DateAggregateExpression((Date)result);
            } else {
               if (!(result instanceof String)) {
                  if (result instanceof InMemoryFailure) {
                     return result;
                  }

                  throw new NucleusException("Evaluation of max() on object of type " + result.getClass().getName() + " - not supported");
               }

               memexpr = new StringAggregateExpression((String)result);
            }

            if (Boolean.TRUE.equals(memexpr.gt(val))) {
               val = result;
            }
         }

         return val;
      }
   }

   public Object sum(Expression paramExpr, ExpressionEvaluator eval, Map state) {
      if (this.itemIterator == null) {
         return paramExpr.getSymbol() != null && Number.class.isAssignableFrom(paramExpr.getSymbol().getValueType()) ? 0 : null;
      } else {
         Object val;
         Object var7;
         for(val = null; this.itemIterator.hasNext(); val = ((AggregateExpression)var7).add(val)) {
            state.put(this.alias, this.itemIterator.next());
            Object result = paramExpr.evaluate(eval);
            AggregateExpression memexpr = null;
            if (result instanceof Float) {
               if (val == null) {
                  val = (double)0.0F;
               }

               var7 = new DoubleAggregateExpression(((Float)result).doubleValue());
            } else if (result instanceof Double) {
               if (val == null) {
                  val = (double)0.0F;
               }

               var7 = new DoubleAggregateExpression((Double)result);
            } else if (result instanceof Long) {
               if (val == null) {
                  val = 0L;
               }

               var7 = new LongAggregateExpression((Long)result);
            } else if (result instanceof Integer) {
               if (val == null) {
                  val = 0L;
               }

               var7 = new LongAggregateExpression(((Integer)result).longValue());
            } else if (result instanceof Short) {
               if (val == null) {
                  val = 0L;
               }

               var7 = new LongAggregateExpression(((Short)result).longValue());
            } else if (result instanceof BigInteger) {
               if (val == null) {
                  val = BigInteger.ZERO;
               }

               var7 = new BigIntegerAggregateExpression((BigInteger)result);
            } else if (result instanceof Date) {
               if (val == null) {
                  val = new Date(0L);
               }

               var7 = new DateAggregateExpression((Date)result);
            } else {
               if (!(result instanceof BigDecimal)) {
                  if (result instanceof InMemoryFailure) {
                     return result;
                  }

                  throw new NucleusException("Evaluation of sum() on object of type " + result.getClass().getName() + " - not supported");
               }

               if (val == null) {
                  val = BigDecimal.ZERO;
               }

               var7 = new BigDecimalAggregateExpression((BigDecimal)result);
            }
         }

         return val;
      }
   }

   public Object avg(Expression paramExpr, ExpressionEvaluator eval, Map state) {
      if (this.itemIterator != null && this.itemIterator.hasNext()) {
         int i = 0;
         Object val = null;

         AggregateExpression memexpr;
         for(memexpr = null; this.itemIterator.hasNext(); ++i) {
            state.put(this.alias, this.itemIterator.next());
            Object result = paramExpr.evaluate(eval);
            if (result instanceof Float) {
               if (val == null) {
                  val = new Double((double)0.0F);
               }

               memexpr = new DoubleAggregateExpression(((Float)result).doubleValue());
            } else if (result instanceof Double) {
               if (val == null) {
                  val = new Double((double)0.0F);
               }

               memexpr = new DoubleAggregateExpression((Double)result);
            } else if (result instanceof Long) {
               if (val == null) {
                  val = (double)0.0F;
               }

               memexpr = new DoubleAggregateExpression(((Long)result).doubleValue());
            } else if (result instanceof Integer) {
               if (val == null) {
                  val = (double)0.0F;
               }

               memexpr = new DoubleAggregateExpression(((Integer)result).doubleValue());
            } else if (result instanceof Short) {
               if (val == null) {
                  val = (double)0.0F;
               }

               memexpr = new DoubleAggregateExpression(((Short)result).doubleValue());
            } else if (result instanceof BigInteger) {
               if (val == null) {
                  val = (double)0.0F;
               }

               memexpr = new DoubleAggregateExpression(((BigInteger)result).doubleValue());
            } else {
               if (!(result instanceof BigDecimal)) {
                  if (result instanceof InMemoryFailure) {
                     return result;
                  }

                  throw new NucleusException("Evaluation of avg() on object of type " + result.getClass().getName() + " - not supported");
               }

               if (val == null) {
                  val = (double)0.0F;
               }

               memexpr = new DoubleAggregateExpression(((BigDecimal)result).doubleValue());
            }

            val = memexpr.add(val);
         }

         Object divisor = null;
         if (val instanceof Float) {
            memexpr = new FloatAggregateExpression((Float)val);
            divisor = new Float((float)i);
         } else if (val instanceof Double) {
            memexpr = new DoubleAggregateExpression((Double)val);
            divisor = new Double((double)i);
         } else if (val instanceof Long) {
            memexpr = new LongAggregateExpression((Long)val);
            divisor = (long)i;
         } else if (val instanceof Integer) {
            memexpr = new IntegerAggregateExpression((Integer)val);
            divisor = i;
         } else if (val instanceof Short) {
            memexpr = new ShortAggregateExpression((Short)val);
            divisor = (short)i;
         } else if (val instanceof BigInteger) {
            memexpr = new BigIntegerAggregateExpression((BigInteger)val);
            divisor = BigInteger.valueOf((long)i);
         } else if (val instanceof BigDecimal) {
            memexpr = new BigDecimalAggregateExpression((BigDecimal)val);
            divisor = BigDecimal.valueOf((long)i);
         }

         if (memexpr == null) {
            NucleusLogger.QUERY.error("In-memory failure in attempt to get avg of null. Not supported. Perhaps something went wrong earlier in the query evaluation?");
            return new InMemoryFailure();
         } else {
            return memexpr.div(divisor);
         }
      } else {
         return paramExpr.getSymbol() != null && Number.class.isAssignableFrom(paramExpr.getSymbol().getValueType()) ? (double)0.0F : null;
      }
   }
}
