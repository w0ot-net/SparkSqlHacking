package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.ExpressionUtils;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class DateGetMonth3Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!(expr instanceof TemporalExpression)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"getMonth()", expr}));
      } else {
         SQLExpression one = ExpressionUtils.getLiteralForOne(this.stmt);
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         JavaTypeMapping mapping2 = storeMgr.getMappingManager().getMapping(String.class);
         SQLExpression mm = this.exprFactory.newLiteral(this.stmt, mapping2, "month");
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(mm);
         funcArgs.add(expr);
         NumericExpression numExpr = new NumericExpression(new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "date_part", funcArgs), Expression.OP_SUB, one);
         numExpr.encloseInParentheses();
         return numExpr;
      }
   }
}
