package org.datanucleus.query.compiler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.ParameterExpression;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;

public class QueryCompilation implements Serializable {
   private static final long serialVersionUID = 2976142726587145777L;
   protected String queryLanguage;
   protected Class candidateClass;
   protected String candidateAlias = "this";
   protected boolean returnsSingleRow = false;
   protected SymbolTable symtbl;
   protected boolean resultDistinct = false;
   protected Expression[] exprResult;
   protected Expression[] exprFrom;
   protected Expression[] exprUpdate;
   protected Expression exprFilter = null;
   protected Expression[] exprGrouping;
   protected Expression exprHaving;
   protected Expression[] exprOrdering;
   protected Map subqueryCompilations = null;

   public QueryCompilation(Class candidateCls, String candidateAlias, SymbolTable symtbl, Expression[] results, Expression[] froms, Expression filter, Expression[] groupings, Expression having, Expression[] orderings, Expression[] updates) {
      this.candidateClass = candidateCls;
      this.candidateAlias = candidateAlias;
      this.symtbl = symtbl;
      this.exprResult = results;
      this.exprFrom = froms;
      this.exprFilter = filter;
      this.exprGrouping = groupings;
      this.exprHaving = having;
      this.exprOrdering = orderings;
      this.exprUpdate = updates;
   }

   public void setQueryLanguage(String lang) {
      this.queryLanguage = lang;
   }

   public String getQueryLanguage() {
      return this.queryLanguage;
   }

   public void setResultDistinct() {
      this.resultDistinct = true;
   }

   public boolean getResultDistinct() {
      return this.resultDistinct;
   }

   public void setReturnsSingleRow() {
      this.returnsSingleRow = true;
   }

   public void addSubqueryCompilation(String alias, QueryCompilation compilation) {
      if (this.subqueryCompilations == null) {
         this.subqueryCompilations = new HashMap();
      }

      this.subqueryCompilations.put(alias, compilation);
   }

   public QueryCompilation getCompilationForSubquery(String alias) {
      return this.subqueryCompilations != null ? (QueryCompilation)this.subqueryCompilations.get(alias) : null;
   }

   public String[] getSubqueryAliases() {
      if (this.subqueryCompilations != null && !this.subqueryCompilations.isEmpty()) {
         String[] aliases = new String[this.subqueryCompilations.size()];
         Iterator<String> iter = this.subqueryCompilations.keySet().iterator();

         for(int i = 0; iter.hasNext(); aliases[i++] = (String)iter.next()) {
         }

         return aliases;
      } else {
         return null;
      }
   }

   public boolean returnsSingleRow() {
      return this.returnsSingleRow;
   }

   public Class[] getResultTypes() {
      if (this.exprResult != null && this.exprResult.length > 0) {
         Class[] results = new Class[this.exprResult.length];

         for(int i = 0; i < this.exprResult.length; ++i) {
            Symbol colSym = this.exprResult[i].getSymbol();
            results[i] = colSym.getValueType();
         }

         return results;
      } else {
         return new Class[]{this.candidateClass};
      }
   }

   public Class getCandidateClass() {
      return this.candidateClass;
   }

   public String getCandidateAlias() {
      return this.candidateAlias;
   }

   public SymbolTable getSymbolTable() {
      return this.symtbl;
   }

   public Expression[] getExprResult() {
      return this.exprResult;
   }

   public Expression[] getExprFrom() {
      return this.exprFrom;
   }

   public Expression[] getExprUpdate() {
      return this.exprUpdate;
   }

   public Expression getExprFilter() {
      return this.exprFilter;
   }

   public void setExprFilter(Expression filter) {
      this.exprFilter = filter;
   }

   public Expression[] getExprGrouping() {
      return this.exprGrouping;
   }

   public Expression getExprHaving() {
      return this.exprHaving;
   }

   public void setExprHaving(Expression having) {
      this.exprHaving = having;
   }

   public Expression[] getExprOrdering() {
      return this.exprOrdering;
   }

   public ParameterExpression getParameterExpressionForPosition(int pos) {
      ParameterExpression paramExpr = null;
      if (this.exprResult != null) {
         for(int i = 0; i < this.exprResult.length; ++i) {
            paramExpr = QueryUtils.getParameterExpressionForPosition(this.exprResult[i], pos);
            if (paramExpr != null) {
               return paramExpr;
            }
         }
      }

      if (this.exprFilter != null) {
         paramExpr = QueryUtils.getParameterExpressionForPosition(this.exprFilter, pos);
         if (paramExpr != null) {
            return paramExpr;
         }
      }

      if (this.exprGrouping != null) {
         for(int i = 0; i < this.exprGrouping.length; ++i) {
            paramExpr = QueryUtils.getParameterExpressionForPosition(this.exprGrouping[i], pos);
            if (paramExpr != null) {
               return paramExpr;
            }
         }
      }

      if (this.exprHaving != null) {
         paramExpr = QueryUtils.getParameterExpressionForPosition(this.exprHaving, pos);
         if (paramExpr != null) {
            return paramExpr;
         }
      }

      if (this.exprOrdering != null) {
         for(int i = 0; i < this.exprOrdering.length; ++i) {
            paramExpr = QueryUtils.getParameterExpressionForPosition(this.exprOrdering[i], pos);
            if (paramExpr != null) {
               return paramExpr;
            }
         }
      }

      return null;
   }

   public String toString() {
      StringBuilder str = new StringBuilder("QueryCompilation:\n");
      str.append(this.debugString("  "));
      return str.toString();
   }

   public String debugString(String indent) {
      StringBuilder str = new StringBuilder();
      if (this.exprResult != null) {
         str.append(indent).append("[result:");
         if (this.resultDistinct) {
            str.append(" DISTINCT ");
         }

         for(int i = 0; i < this.exprResult.length; ++i) {
            if (i > 0) {
               str.append(",");
            }

            str.append(this.exprResult[i]);
         }

         str.append("]\n");
      }

      if (this.exprFrom != null) {
         str.append(indent).append("[from:");

         for(int i = 0; i < this.exprFrom.length; ++i) {
            if (i > 0) {
               str.append(",");
            }

            str.append(this.exprFrom[i]);
         }

         str.append("]\n");
      }

      if (this.exprUpdate != null) {
         str.append(indent).append("[update:");

         for(int i = 0; i < this.exprUpdate.length; ++i) {
            if (i > 0) {
               str.append(",");
            }

            str.append(this.exprUpdate[i]);
         }

         str.append("]\n");
      }

      if (this.exprFilter != null) {
         str.append(indent).append("[filter:").append(this.exprFilter).append("]\n");
      }

      if (this.exprGrouping != null) {
         str.append(indent).append("[grouping:");

         for(int i = 0; i < this.exprGrouping.length; ++i) {
            if (i > 0) {
               str.append(",");
            }

            str.append(this.exprGrouping[i]);
         }

         str.append("]\n");
      }

      if (this.exprHaving != null) {
         str.append(indent).append("[having:").append(this.exprHaving).append("]\n");
      }

      if (this.exprOrdering != null) {
         str.append(indent).append("[ordering:");

         for(int i = 0; i < this.exprOrdering.length; ++i) {
            if (i > 0) {
               str.append(",");
            }

            str.append(this.exprOrdering[i]);
         }

         str.append("]\n");
      }

      str.append(indent).append("[symbols: ");
      Iterator<String> symIter = this.symtbl.getSymbolNames().iterator();

      while(symIter.hasNext()) {
         String symName = (String)symIter.next();
         Symbol sym = this.symtbl.getSymbol(symName);
         if (sym.getValueType() != null) {
            str.append(symName + " type=" + sym.getValueType().getName());
         } else {
            str.append(symName + " type=unknown");
         }

         if (symIter.hasNext()) {
            str.append(", ");
         }
      }

      str.append("]");
      if (this.subqueryCompilations != null) {
         str.append("\n");
         Iterator subqIter = this.subqueryCompilations.entrySet().iterator();

         while(subqIter.hasNext()) {
            Map.Entry<String, QueryCompilation> entry = (Map.Entry)subqIter.next();
            str.append(indent).append("[subquery: " + (String)entry.getKey() + "\n");
            str.append(((QueryCompilation)entry.getValue()).debugString(indent + "  ")).append("]");
            if (subqIter.hasNext()) {
               str.append("\n");
            }
         }
      }

      return str.toString();
   }
}
