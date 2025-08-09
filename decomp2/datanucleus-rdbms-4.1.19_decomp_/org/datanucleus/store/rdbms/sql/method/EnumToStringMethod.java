package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.expression.EnumExpression;
import org.datanucleus.store.rdbms.sql.expression.EnumLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.util.Localiser;

public class EnumToStringMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (expr instanceof EnumLiteral) {
         Enum val = (Enum)((EnumLiteral)expr).getValue();
         return new StringLiteral(this.stmt, this.exprFactory.getMappingForType(String.class, false), val.toString(), (String)null);
      } else if (expr instanceof EnumExpression) {
         EnumExpression enumExpr = (EnumExpression)expr;
         JavaTypeMapping m = enumExpr.getJavaTypeMapping();
         if (m.getJavaTypeForDatastoreMapping(0).equals(ClassNameConstants.JAVA_LANG_STRING)) {
            return enumExpr.getDelegate();
         } else {
            throw new NucleusException("EnumExpression.toString is not supported when the enum is stored as a numeric");
         }
      } else {
         throw new NucleusException(Localiser.msg("060001", new Object[]{"ordinal", expr}));
      }
   }
}
