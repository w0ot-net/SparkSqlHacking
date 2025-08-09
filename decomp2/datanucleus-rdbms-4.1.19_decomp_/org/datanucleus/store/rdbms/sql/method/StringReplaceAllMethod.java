package org.datanucleus.store.rdbms.sql.method;

import java.util.ArrayList;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.util.Localiser;

public class StringReplaceAllMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() == 2) {
         SQLExpression strExpr1 = (SQLExpression)args.get(0);
         SQLExpression strExpr2 = (SQLExpression)args.get(1);
         if (!(strExpr1 instanceof StringExpression) && !(strExpr1 instanceof CharacterExpression)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"replaceAll", "StringExpression", 1, "StringExpression/CharacterExpression"}));
         } else if (!(strExpr2 instanceof StringExpression) && !(strExpr2 instanceof CharacterExpression)) {
            throw new NucleusException(Localiser.msg("060003", new Object[]{"replaceAll", "StringExpression", 2, "StringExpression/CharacterExpression"}));
         } else {
            List<SQLExpression> newArgs = new ArrayList(3);
            newArgs.add(expr);
            newArgs.add(strExpr1);
            newArgs.add(strExpr2);
            JavaTypeMapping mapping = this.exprFactory.getMappingForType(String.class, false);
            return new StringExpression(this.stmt, mapping, "replace", newArgs);
         }
      } else {
         throw new NucleusException(Localiser.msg("060003", new Object[]{"replaceAll", "StringExpression", 2, "StringExpression/CharacterExpression"}));
      }
   }
}
