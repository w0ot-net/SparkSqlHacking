package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.AggregateNumericExpression;
import org.datanucleus.store.rdbms.sql.expression.NumericSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class AvgFunction extends SimpleNumericAggregateMethod {
   protected String getFunctionName() {
      return "AVG";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr != null) {
         throw new NucleusException(Localiser.msg("060002", new Object[]{this.getFunctionName(), expr}));
      } else if (args != null && args.size() == 1) {
         Class returnType = Double.class;
         if (this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.RESULT && this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.HAVING) {
            SQLExpression argExpr = (SQLExpression)args.get(0);
            SQLStatement subStmt = new SQLStatement(this.stmt, this.stmt.getRDBMSManager(), argExpr.getSQLTable().getTable(), argExpr.getSQLTable().getAlias(), (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping mapping = this.stmt.getRDBMSManager().getMappingManager().getMappingWithDatastoreMapping(String.class, false, false, this.clr);
            SQLExpression aggExpr = this.getAggregateExpression(args, mapping);
            subStmt.select(aggExpr, (String)null);
            JavaTypeMapping subqMapping = this.exprFactory.getMappingForType(returnType, false);
            SQLExpression subqExpr = new NumericSubqueryExpression(this.stmt, subStmt);
            subqExpr.setJavaTypeMapping(subqMapping);
            return subqExpr;
         } else {
            JavaTypeMapping m = this.getMappingForClass(returnType);
            return this.getAggregateExpression(args, m);
         }
      } else {
         throw new NucleusException(this.getFunctionName() + " is only supported with a single argument");
      }
   }

   protected SQLExpression getAggregateExpression(List args, JavaTypeMapping m) {
      return new AggregateNumericExpression(this.stmt, m, this.getFunctionName(), args);
   }

   protected Class getClassForMapping() {
      return Double.TYPE;
   }
}
