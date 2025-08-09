package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.sql.expression.CharacterLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.util.Localiser;

public class StringToUpperMethod extends SimpleStringMethod {
   protected String getFunctionName() {
      return "UPPER";
   }

   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && !args.isEmpty()) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"toUpperCase", "StringExpression"}));
      } else {
         if (!expr.isParameter()) {
            if (expr instanceof StringLiteral) {
               String val = (String)((StringLiteral)expr).getValue();
               if (val != null) {
                  val = val.toUpperCase();
               }

               return new StringLiteral(this.stmt, expr.getJavaTypeMapping(), val, (String)null);
            }

            if (expr instanceof CharacterLiteral) {
               String val = (String)((CharacterLiteral)expr).getValue();
               if (val != null) {
                  val = val.toUpperCase();
               }

               return new CharacterLiteral(this.stmt, expr.getJavaTypeMapping(), val, (String)null);
            }
         }

         return super.getExpression(expr, (List)null);
      }
   }
}
