package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.MapLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.util.Localiser;

public class MapIsEmptyMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"isEmpty", "MapExpression"}));
      } else if (!(expr instanceof MapLiteral)) {
         SQLExpression sizeExpr = this.exprFactory.invokeMethod(this.stmt, Map.class.getName(), "size", expr, args);
         JavaTypeMapping mapping = this.exprFactory.getMappingForType(Integer.class, true);
         SQLExpression zeroExpr = this.exprFactory.newLiteral(this.stmt, mapping, 0);
         return sizeExpr.eq(zeroExpr);
      } else {
         Map map = (Map)((MapLiteral)expr).getValue();
         boolean isEmpty = map == null || map.size() == 0;
         JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, false);
         return new BooleanLiteral(this.stmt, m, isEmpty ? Boolean.TRUE : Boolean.FALSE);
      }
   }
}
