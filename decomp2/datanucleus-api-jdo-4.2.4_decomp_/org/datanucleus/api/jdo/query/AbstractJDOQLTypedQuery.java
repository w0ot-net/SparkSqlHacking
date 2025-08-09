package org.datanucleus.api.jdo.query;

import java.util.Iterator;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.query.OrderExpression.OrderDirection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.jdo.JDOPersistenceManager;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.query.compiler.JDOQLSymbolResolver;
import org.datanucleus.query.compiler.QueryCompilation;
import org.datanucleus.query.expression.DyadicExpression;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.Literal;
import org.datanucleus.query.expression.OrderExpression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.expression.VariableExpression;
import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.SymbolTable;

public abstract class AbstractJDOQLTypedQuery {
   protected QueryType type;
   protected Class candidateCls;
   protected boolean subclasses;
   protected String candidateAlias;
   protected List updateExprs;
   protected List updateVals;
   protected List result;
   protected Boolean resultDistinct;
   protected boolean unique;
   protected Class resultClass;
   protected BooleanExpressionImpl filter;
   protected List grouping;
   protected ExpressionImpl having;
   protected List ordering;
   protected ExpressionImpl rangeLowerExpr;
   protected ExpressionImpl rangeUpperExpr;
   protected PersistenceManager pm;
   protected ExecutionContext ec;
   protected QueryCompilation compilation;
   protected String queryString;

   public AbstractJDOQLTypedQuery(PersistenceManager pm, Class cls, String alias) {
      this.type = AbstractJDOQLTypedQuery.QueryType.SELECT;
      this.subclasses = true;
      this.candidateAlias = null;
      this.resultDistinct = null;
      this.unique = false;
      this.resultClass = null;
      this.compilation = null;
      this.queryString = null;
      this.pm = pm;
      this.ec = ((JDOPersistenceManager)pm).getExecutionContext();
      this.candidateCls = cls;
      this.candidateAlias = alias;
   }

   protected void discardCompiled() {
      this.compilation = null;
      this.queryString = null;
   }

   protected QueryCompilation compile(MetaDataManager mmgr, ClassLoaderResolver clr) {
      SymbolTable symtbl = new SymbolTable();
      symtbl.setSymbolResolver(new JDOQLSymbolResolver(mmgr, clr, symtbl, this.candidateCls, this.candidateAlias));
      symtbl.addSymbol(new PropertySymbol(this.candidateAlias, this.candidateCls));
      Expression[] resultExprs = null;
      if (this.result != null && !this.result.isEmpty()) {
         resultExprs = new Expression[this.result.size()];
         Iterator iter = this.result.iterator();

         Expression resultExpr;
         for(int i = 0; iter.hasNext(); resultExprs[i++] = resultExpr) {
            ExpressionImpl result = (ExpressionImpl)iter.next();
            resultExpr = result.getQueryExpression();
            resultExpr.bind(symtbl);
         }

         if (resultExprs.length == 1 && resultExprs[0] instanceof PrimaryExpression) {
            String resultExprId = ((PrimaryExpression)resultExprs[0]).getId();
            if (resultExprId.equalsIgnoreCase(this.candidateAlias)) {
               resultExprs = null;
            }
         }
      }

      Expression filterExpr = null;
      if (this.filter != null) {
         filterExpr = this.filter.getQueryExpression();
         if (filterExpr != null) {
            filterExpr.bind(symtbl);
         }
      }

      Expression[] groupingExprs = null;
      if (this.grouping != null && !this.grouping.isEmpty()) {
         groupingExprs = new Expression[this.grouping.size()];
         Iterator iter = this.grouping.iterator();

         Expression groupingExpr;
         for(int i = 0; iter.hasNext(); groupingExprs[i++] = groupingExpr) {
            ExpressionImpl grp = (ExpressionImpl)iter.next();
            groupingExpr = grp.getQueryExpression();
            groupingExpr.bind(symtbl);
         }
      }

      Expression havingExpr = null;
      if (this.having != null) {
         havingExpr = this.having.getQueryExpression();
         havingExpr.bind(symtbl);
      }

      Expression[] orderExprs = null;
      if (this.ordering != null && !this.ordering.isEmpty()) {
         orderExprs = new Expression[this.ordering.size()];
         Iterator<OrderExpressionImpl> iter = this.ordering.iterator();

         OrderExpression orderExpr;
         for(int i = 0; iter.hasNext(); orderExprs[i++] = orderExpr) {
            OrderExpressionImpl order = (OrderExpressionImpl)iter.next();
            orderExpr = new OrderExpression(((ExpressionImpl)order.getExpression()).getQueryExpression(), order.getDirection() == OrderDirection.ASC ? "ascending" : "descending");
            orderExpr.bind(symtbl);
         }
      }

      Expression[] updateExprs = null;
      if (this.updateExprs != null) {
         Iterator<ExpressionImpl> expIter = this.updateExprs.iterator();
         Iterator<ExpressionImpl> valIter = this.updateVals.iterator();
         updateExprs = new Expression[this.updateExprs.size()];

         ExpressionImpl updateExpr;
         ExpressionImpl updateVal;
         for(int i = 0; expIter.hasNext(); updateExprs[i++] = new DyadicExpression(updateExpr.getQueryExpression(), Expression.OP_EQ, updateVal.getQueryExpression())) {
            updateExpr = (ExpressionImpl)expIter.next();
            updateVal = (ExpressionImpl)valIter.next();
         }
      }

      this.compilation = new QueryCompilation(this.candidateCls, this.candidateAlias, symtbl, resultExprs, (Expression[])null, filterExpr, groupingExprs, havingExpr, orderExprs, updateExprs);
      this.compilation.setQueryLanguage("JDOQL");
      return this.compilation;
   }

   public QueryCompilation getCompilation() {
      if (this.compilation == null) {
         this.compilation = this.compile(this.ec.getMetaDataManager(), this.ec.getClassLoaderResolver());
      }

      return this.compilation;
   }

   public String toString() {
      if (this.queryString == null) {
         StringBuilder str = null;
         if (this.type == AbstractJDOQLTypedQuery.QueryType.BULK_UPDATE) {
            str = new StringBuilder("UPDATE");
         } else if (this.type == AbstractJDOQLTypedQuery.QueryType.BULK_DELETE) {
            str = new StringBuilder("DELETE");
         } else {
            str = new StringBuilder("SELECT");
         }

         if (this.type == AbstractJDOQLTypedQuery.QueryType.SELECT) {
            if (this.unique) {
               str.append(" UNIQUE");
            }

            if (this.result != null && !this.result.isEmpty()) {
               if (this.resultDistinct != null && this.resultDistinct) {
                  str.append(" DISTINCT");
               }

               str.append(" ");
               Iterator<ExpressionImpl> iter = this.result.iterator();

               while(iter.hasNext()) {
                  ExpressionImpl resultExpr = (ExpressionImpl)iter.next();
                  str.append(this.getJDOQLForExpression(resultExpr.getQueryExpression()));
                  if (iter.hasNext()) {
                     str.append(",");
                  }
               }
            }

            if (this.resultClass != null) {
               str.append(" INTO ").append(this.resultClass.getName());
            }
         }

         if (this.type != AbstractJDOQLTypedQuery.QueryType.SELECT && this.type != AbstractJDOQLTypedQuery.QueryType.BULK_DELETE) {
            str.append(" " + this.candidateCls.getName());
         } else {
            str.append(" FROM ").append(this.candidateCls.getName());
         }

         if (this instanceof JDOQLTypedSubqueryImpl) {
            str.append(" " + this.candidateAlias);
         }

         if (!this.subclasses) {
            str.append(" EXCLUDE SUBCLASSES");
         }

         if (this.type == AbstractJDOQLTypedQuery.QueryType.BULK_UPDATE) {
            str.append(" SET");
            Iterator<ExpressionImpl> exprIter = this.updateExprs.iterator();
            Iterator<ExpressionImpl> valIter = this.updateVals.iterator();

            while(exprIter.hasNext()) {
               ExpressionImpl expr = (ExpressionImpl)exprIter.next();
               ExpressionImpl val = (ExpressionImpl)valIter.next();
               str.append(" ").append(this.getJDOQLForExpression(expr.getQueryExpression()));
               str.append(" = ").append(this.getJDOQLForExpression(val.getQueryExpression()));
               if (exprIter.hasNext()) {
                  str.append(",");
               }
            }
         }

         if (this.filter != null) {
            str.append(" WHERE ");
            str.append(this.getJDOQLForExpression(this.filter.getQueryExpression()));
         }

         if (this.type == AbstractJDOQLTypedQuery.QueryType.SELECT) {
            if (this.grouping != null && !this.grouping.isEmpty()) {
               str.append(" GROUP BY ");
               Iterator<ExpressionImpl> iter = this.grouping.iterator();

               while(iter.hasNext()) {
                  ExpressionImpl groupExpr = (ExpressionImpl)iter.next();
                  str.append(this.getJDOQLForExpression(groupExpr.getQueryExpression()));
                  if (iter.hasNext()) {
                     str.append(",");
                  }
               }
            }

            if (this.having != null) {
               str.append(" HAVING ");
               str.append(this.getJDOQLForExpression(this.having.getQueryExpression()));
            }

            if (this.ordering != null && !this.ordering.isEmpty()) {
               str.append(" ORDER BY ");
               Iterator<OrderExpressionImpl> iter = this.ordering.iterator();

               while(iter.hasNext()) {
                  OrderExpressionImpl orderExpr = (OrderExpressionImpl)iter.next();
                  str.append(this.getJDOQLForExpression(((ExpressionImpl)orderExpr.getExpression()).getQueryExpression()));
                  str.append(" " + (orderExpr.getDirection() == OrderDirection.ASC ? "ASCENDING" : "DESCENDING"));
                  if (iter.hasNext()) {
                     str.append(",");
                  }
               }
            }

            if (this.rangeLowerExpr != null && this.rangeUpperExpr != null) {
               str.append(" RANGE ");
               str.append(this.getJDOQLForExpression(this.rangeLowerExpr.getQueryExpression()));
               str.append(",");
               str.append(this.getJDOQLForExpression(this.rangeUpperExpr.getQueryExpression()));
            }
         }

         this.queryString = str.toString();
      }

      return this.queryString;
   }

   public String getJDOQLForExpression(Expression expr) {
      if (expr instanceof DyadicExpression) {
         DyadicExpression dyExpr = (DyadicExpression)expr;
         Expression left = dyExpr.getLeft();
         Expression right = dyExpr.getRight();
         StringBuilder str = new StringBuilder("(");
         if (dyExpr.getOperator() == Expression.OP_DISTINCT) {
            str.append("DISTINCT ");
         }

         if (left != null) {
            str.append(this.getJDOQLForExpression(left));
         }

         if (dyExpr.getOperator() == Expression.OP_AND) {
            str.append(" && ");
         } else if (dyExpr.getOperator() == Expression.OP_OR) {
            str.append(" || ");
         } else if (dyExpr.getOperator() == Expression.OP_ADD) {
            str.append(" + ");
         } else if (dyExpr.getOperator() == Expression.OP_SUB) {
            str.append(" - ");
         } else if (dyExpr.getOperator() == Expression.OP_MUL) {
            str.append(" * ");
         } else if (dyExpr.getOperator() == Expression.OP_DIV) {
            str.append(" / ");
         } else if (dyExpr.getOperator() == Expression.OP_EQ) {
            str.append(" == ");
         } else if (dyExpr.getOperator() == Expression.OP_GT) {
            str.append(" > ");
         } else if (dyExpr.getOperator() == Expression.OP_LT) {
            str.append(" < ");
         } else if (dyExpr.getOperator() == Expression.OP_GTEQ) {
            str.append(" >= ");
         } else if (dyExpr.getOperator() == Expression.OP_LTEQ) {
            str.append(" <= ");
         } else if (dyExpr.getOperator() == Expression.OP_NOTEQ) {
            str.append(" != ");
         } else if (dyExpr.getOperator() != Expression.OP_DISTINCT) {
            throw new UnsupportedOperationException("Dont currently support operator " + dyExpr.getOperator() + " in JDOQL conversion");
         }

         if (right != null) {
            str.append(this.getJDOQLForExpression(right));
         }

         str.append(")");
         return str.toString();
      } else if (expr instanceof PrimaryExpression) {
         PrimaryExpression primExpr = (PrimaryExpression)expr;
         return primExpr.getLeft() != null ? this.getJDOQLForExpression(primExpr.getLeft()) + "." + primExpr.getId() : primExpr.getId();
      } else if (expr instanceof ParameterExpression) {
         ParameterExpression paramExpr = (ParameterExpression)expr;
         return paramExpr.getId() != null ? ":" + paramExpr.getId() : "?" + paramExpr.getPosition();
      } else if (expr instanceof VariableExpression) {
         VariableExpression varExpr = (VariableExpression)expr;
         return varExpr.getId();
      } else if (expr instanceof InvokeExpression) {
         InvokeExpression invExpr = (InvokeExpression)expr;
         StringBuilder str = new StringBuilder();
         if (invExpr.getLeft() != null) {
            str.append(this.getJDOQLForExpression(invExpr.getLeft())).append(".");
         }

         str.append(invExpr.getOperation());
         str.append("(");
         List<Expression> args = invExpr.getArguments();
         if (args != null) {
            Iterator<Expression> iter = args.iterator();

            while(iter.hasNext()) {
               str.append(this.getJDOQLForExpression((Expression)iter.next()));
               if (iter.hasNext()) {
                  str.append(",");
               }
            }
         }

         str.append(")");
         return str.toString();
      } else if (expr instanceof Literal) {
         Literal litExpr = (Literal)expr;
         Object value = litExpr.getLiteral();
         if (!(value instanceof String) && !(value instanceof Character)) {
            if (value instanceof Boolean) {
               return (Boolean)value ? "TRUE" : "FALSE";
            } else {
               return litExpr.getLiteral() == null ? "null" : litExpr.getLiteral().toString();
            }
         } else {
            return "'" + value.toString() + "'";
         }
      } else if (expr instanceof VariableExpression) {
         VariableExpression varExpr = (VariableExpression)expr;
         return varExpr.getId();
      } else {
         throw new UnsupportedOperationException("Dont currently support " + expr.getClass().getName() + " in JDOQLHelper");
      }
   }

   static enum QueryType {
      SELECT,
      BULK_UPDATE,
      BULK_DELETE;
   }
}
