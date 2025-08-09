package org.datanucleus.query.compiler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.query.JPQLQueryHelper;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.query.expression.InvokeExpression;
import org.datanucleus.query.expression.PrimaryExpression;
import org.datanucleus.query.symbol.PropertySymbol;
import org.datanucleus.query.symbol.Symbol;
import org.datanucleus.query.symbol.SymbolTable;
import org.datanucleus.util.Imports;

public class JPQLCompiler extends JavaQueryCompiler {
   public JPQLCompiler(MetaDataManager metaDataManager, ClassLoaderResolver clr, String from, Class candidateClass, Collection candidates, String filter, Imports imports, String ordering, String result, String grouping, String having, String params, String update) {
      super(metaDataManager, clr, from, candidateClass, candidates, filter, imports, ordering, result, grouping, having, params, (String)null, update);
      this.from = from;
      this.caseSensitiveAliases = false;
   }

   public QueryCompilation compile(Map parameters, Map subqueryMap) {
      Map parseOptions = new HashMap();
      if (this.options != null && this.options.containsKey("jpql.strict")) {
         parseOptions.put("jpql.strict", this.options.get("jpql.strict"));
      }

      this.parser = new JPQLParser(parseOptions);
      this.symtbl = new SymbolTable();
      this.symtbl.setSymbolResolver(this);
      if (this.parentCompiler != null) {
         this.symtbl.setParentSymbolTable(this.parentCompiler.symtbl);
      }

      if (subqueryMap != null && !subqueryMap.isEmpty()) {
         for(String subqueryName : subqueryMap.keySet()) {
            Symbol sym = new PropertySymbol(subqueryName);
            sym.setType(2);
            this.symtbl.addSymbol(sym);
         }
      }

      Expression[] exprFrom = this.compileFrom();
      this.compileCandidatesParametersVariables(parameters);
      Expression exprFilter = this.compileFilter();
      Expression[] exprOrdering = this.compileOrdering();
      Expression[] exprResult = this.compileResult();
      Expression[] exprGrouping = this.compileGrouping();
      Expression exprHaving = this.compileHaving();
      Expression[] exprUpdate = this.compileUpdate();
      if (exprResult != null && exprResult.length == 1 && exprResult[0] instanceof PrimaryExpression) {
         String resultExprId = ((PrimaryExpression)exprResult[0]).getId();
         if (resultExprId.equalsIgnoreCase(this.candidateAlias)) {
            exprResult = null;
         }
      }

      if (exprResult != null) {
         for(int i = 0; i < exprResult.length; ++i) {
            if (exprResult[i] instanceof InvokeExpression) {
               InvokeExpression invokeExpr = (InvokeExpression)exprResult[i];
               if (isMethodNameAggregate(invokeExpr.getOperation())) {
                  List<Expression> args = invokeExpr.getArguments();
                  if (args == null || args.size() != 1) {
                     throw new NucleusUserException("JPQL query has result clause using aggregate (" + invokeExpr.getOperation() + ") but this needs 1 argument");
                  }
               }
            }
         }
      }

      QueryCompilation compilation = new QueryCompilation(this.candidateClass, this.candidateAlias, this.symtbl, exprResult, exprFrom, exprFilter, exprGrouping, exprHaving, exprOrdering, exprUpdate);
      compilation.setQueryLanguage(this.getLanguage());
      return compilation;
   }

   public boolean supportsImplicitVariables() {
      return false;
   }

   public boolean caseSensitiveSymbolNames() {
      return false;
   }

   public String getLanguage() {
      return "JPQL";
   }

   protected boolean isKeyword(String name) {
      return JPQLQueryHelper.isKeyword(name);
   }

   private static boolean isMethodNameAggregate(String methodName) {
      return methodName.equalsIgnoreCase("avg") || methodName.equalsIgnoreCase("count") || methodName.equalsIgnoreCase("sum") || methodName.equalsIgnoreCase("min") || methodName.equalsIgnoreCase("max");
   }
}
