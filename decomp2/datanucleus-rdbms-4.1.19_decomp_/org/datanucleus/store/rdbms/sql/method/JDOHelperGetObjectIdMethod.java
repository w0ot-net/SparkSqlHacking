package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableIdMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceIdMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.sql.expression.IllegalExpressionOperationException;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.ObjectExpression;
import org.datanucleus.store.rdbms.sql.expression.ObjectLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;

public class JDOHelperGetObjectIdMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (args != null && args.size() != 0) {
         SQLExpression expr = (SQLExpression)args.get(0);
         if (expr == null) {
            return new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null);
         } else if (expr instanceof SQLLiteral) {
            RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
            ApiAdapter api = storeMgr.getApiAdapter();
            Object id = api.getIdForObject(((SQLLiteral)expr).getValue());
            if (id == null) {
               return new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null);
            } else {
               JavaTypeMapping m = this.getMappingForClass(id.getClass());
               return new ObjectLiteral(this.stmt, m, id, (String)null);
            }
         } else if (ObjectExpression.class.isAssignableFrom(expr.getClass())) {
            if (expr.getJavaTypeMapping() instanceof PersistableMapping) {
               JavaTypeMapping mapping = new PersistableIdMapping((PersistableMapping)expr.getJavaTypeMapping());
               return new ObjectExpression(this.stmt, expr.getSQLTable(), mapping);
            } else if (expr.getJavaTypeMapping() instanceof ReferenceMapping) {
               JavaTypeMapping mapping = new ReferenceIdMapping((ReferenceMapping)expr.getJavaTypeMapping());
               return new ObjectExpression(this.stmt, expr.getSQLTable(), mapping);
            } else {
               return expr;
            }
         } else {
            throw new IllegalExpressionOperationException("JDOHelper.getObjectId", expr);
         }
      } else {
         throw new NucleusUserException("Cannot invoke JDOHelper.getObjectId without an argument");
      }
   }
}
