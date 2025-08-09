package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class DateGetHour2Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!(expr instanceof TemporalExpression)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"getHour", expr}));
      } else {
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         JavaTypeMapping mapping = storeMgr.getMappingManager().getMapping(String.class);
         SQLExpression hh = this.exprFactory.newLiteral(this.stmt, mapping, "HH24");
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(expr);
         funcArgs.add(hh);
         ArrayList funcArgs2 = new ArrayList();
         funcArgs2.add(new StringExpression(this.stmt, mapping, "TO_CHAR", funcArgs));
         return new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "TO_NUMBER", funcArgs2);
      }
   }
}
