package org.datanucleus.store.rdbms.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.scostore.IteratorStatement;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementParameter;

public class RDBMSQueryCompilation {
   List statementCompilations = new ArrayList(1);
   StatementClassMapping resultsDefinitionForClass = null;
   StatementResultMapping resultsDefinition = null;
   List inputParameters;
   Map inputParameterNameByPosition;
   Map scoIteratorStatementByMemberName;
   boolean precompilable = true;

   public int getNumberOfStatements() {
      return this.statementCompilations.size();
   }

   public void clearStatements() {
      this.statementCompilations.clear();
   }

   public void addStatement(SQLStatement stmt, String sql, boolean useInCount) {
      this.statementCompilations.add(new StatementCompilation(stmt, sql, useInCount));
   }

   public List getStatementCompilations() {
      return this.statementCompilations;
   }

   public void setSQL(String sql) {
      this.clearStatements();
      this.addStatement((SQLStatement)null, sql, true);
   }

   public String getSQL() {
      return this.statementCompilations.isEmpty() ? null : ((StatementCompilation)this.statementCompilations.get(0)).getSQL();
   }

   public void setPrecompilable(boolean precompilable) {
      this.precompilable = precompilable;
   }

   public boolean isPrecompilable() {
      return this.precompilable;
   }

   public void setResultDefinitionForClass(StatementClassMapping def) {
      this.resultsDefinitionForClass = def;
   }

   public StatementClassMapping getResultDefinitionForClass() {
      return this.resultsDefinitionForClass;
   }

   public void setResultDefinition(StatementResultMapping def) {
      this.resultsDefinition = def;
   }

   public StatementResultMapping getResultDefinition() {
      return this.resultsDefinition;
   }

   public void setStatementParameters(List params) {
      this.inputParameters = params;
   }

   public List getStatementParameters() {
      return this.inputParameters;
   }

   public void setParameterNameByPosition(Map paramNameByPos) {
      this.inputParameterNameByPosition = paramNameByPos;
   }

   public Map getParameterNameByPosition() {
      return this.inputParameterNameByPosition;
   }

   public void setSCOIteratorStatement(String memberName, IteratorStatement iterStmt) {
      if (this.scoIteratorStatementByMemberName == null) {
         this.scoIteratorStatementByMemberName = new HashMap();
      }

      this.scoIteratorStatementByMemberName.put(memberName, iterStmt);
   }

   public Map getSCOIteratorStatements() {
      return this.scoIteratorStatementByMemberName;
   }

   public class StatementCompilation {
      SQLStatement stmt;
      String sql;
      boolean useInCount = true;

      public StatementCompilation(SQLStatement stmt, String sql, boolean useInCount) {
         this.stmt = stmt;
         this.sql = sql;
         this.useInCount = useInCount;
      }

      public SQLStatement getStatement() {
         return this.stmt;
      }

      public String getSQL() {
         return this.sql;
      }

      public boolean useInCount() {
         return this.useInCount;
      }

      public void setSQL(String sql) {
         this.sql = sql;
      }
   }
}
