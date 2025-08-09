package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.IntegerLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ParameterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.util.Localiser;

public class StringLength4Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!expr.isParameter() && expr instanceof StringLiteral) {
         JavaTypeMapping m = this.exprFactory.getMappingForType(Integer.TYPE, false);
         String val = (String)((StringLiteral)expr).getValue();
         return new IntegerLiteral(this.stmt, m, val.length(), (String)null);
      } else if (!(expr instanceof StringExpression) && !(expr instanceof ParameterLiteral)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"length", expr}));
      } else {
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(expr);
         return new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "LEN", funcArgs);
      }
   }
}
