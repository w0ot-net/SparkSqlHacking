package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class DateGetSecond3Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!(expr instanceof TemporalExpression)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"getSecond()", expr}));
      } else {
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         JavaTypeMapping mapping = storeMgr.getMappingManager().getMapping(String.class);
         SQLExpression day = this.exprFactory.newLiteral(this.stmt, mapping, "second");
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(day);
         funcArgs.add(expr);
         NumericExpression secondExpr = new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "date_part", funcArgs);
         List castArgs = new ArrayList();
         castArgs.add(secondExpr);
         return new NumericExpression(this.stmt, this.getMappingForClass(Integer.class), "CAST", castArgs, Arrays.asList("INTEGER"));
      }
   }
}
