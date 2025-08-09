package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.util.Localiser;

public class DateGetHour4Method extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (!(expr instanceof TemporalExpression)) {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"getHour()", expr}));
      } else {
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         JavaTypeMapping mapping = storeMgr.getMappingManager().getMapping(String.class);
         SQLExpression hh = this.exprFactory.newLiteral(this.stmt, mapping, "hh");
         ((StringLiteral)hh).generateStatementWithoutQuotes();
         ArrayList funcArgs = new ArrayList();
         funcArgs.add(hh);
         List castArgs = new ArrayList();
         castArgs.add(expr);
         funcArgs.add(new TemporalExpression(this.stmt, this.getMappingForClass(Date.class), "CAST", castArgs, Arrays.asList("DATETIME")));
         return new NumericExpression(this.stmt, this.getMappingForClass(Integer.TYPE), "DATEPART", funcArgs);
      }
   }
}
