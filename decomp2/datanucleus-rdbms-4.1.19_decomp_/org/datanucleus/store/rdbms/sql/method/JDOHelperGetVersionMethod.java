package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.sql.expression.IllegalExpressionOperationException;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.ObjectExpression;
import org.datanucleus.store.rdbms.sql.expression.ObjectLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;

public class JDOHelperGetVersionMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (args != null && args.size() != 0) {
         SQLExpression expr = (SQLExpression)args.get(0);
         if (expr == null) {
            throw new NucleusUserException("Cannot invoke JDOHelper.getVersion on null expression");
         } else if (expr instanceof SQLLiteral) {
            RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
            ApiAdapter api = storeMgr.getApiAdapter();
            Object obj = ((SQLLiteral)expr).getValue();
            if (obj != null && api.isPersistable(obj)) {
               Object ver = this.stmt.getRDBMSManager().getApiAdapter().getVersionForObject(obj);
               JavaTypeMapping m = this.getMappingForClass(ver.getClass());
               return new ObjectLiteral(this.stmt, m, ver, (String)null);
            } else {
               return new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null);
            }
         } else if (ObjectExpression.class.isAssignableFrom(expr.getClass())) {
            if (((ObjectExpression)expr).getJavaTypeMapping() instanceof PersistableMapping) {
               JavaTypeMapping mapping = ((ObjectExpression)expr).getJavaTypeMapping();
               DatastoreClass table = (DatastoreClass)expr.getSQLTable().getTable();
               if (table.getIdMapping() == mapping) {
                  mapping = table.getVersionMapping(true);
                  if (mapping == null) {
                     throw new NucleusUserException("Cannot use JDOHelper.getVersion on object that has no version information");
                  } else {
                     return (SQLExpression)(table.getVersionMetaData().getVersionStrategy() == VersionStrategy.VERSION_NUMBER ? new NumericExpression(this.stmt, expr.getSQLTable(), mapping) : new TemporalExpression(this.stmt, expr.getSQLTable(), mapping));
                  }
               } else {
                  throw new NucleusUserException("Dont currently support JDOHelper.getVersion(ObjectExpression) for expr=" + expr + " on table=" + expr.getSQLTable());
               }
            } else {
               return expr;
            }
         } else {
            throw new IllegalExpressionOperationException("JDOHelper.getVersion", expr);
         }
      } else {
         throw new NucleusUserException("Cannot invoke JDOHelper.getVersion without an argument");
      }
   }
}
