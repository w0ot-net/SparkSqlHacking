package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.table.ClassTable;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class IndexFunction extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression ignore, List args) {
      if (ignore == null) {
         if (args != null && args.size() == 2) {
            SQLExpression elemSqlExpr = (SQLExpression)args.get(0);
            SQLExpression collSqlExpr = (SQLExpression)args.get(1);
            AbstractMemberMetaData mmd = collSqlExpr.getJavaTypeMapping().getMemberMetaData();
            if (!mmd.hasCollection()) {
               throw new NucleusException("INDEX expression for field " + mmd.getFullFieldName() + " does not represent a collection!");
            } else if (!mmd.getOrderMetaData().isIndexedList()) {
               throw new NucleusException("INDEX expression for field " + mmd.getFullFieldName() + " does not represent an indexed list!");
            } else {
               JavaTypeMapping orderMapping = null;
               SQLTable orderTable = null;
               Table joinTbl = this.stmt.getRDBMSManager().getTable(mmd);
               if (joinTbl != null) {
                  CollectionTable collTable = (CollectionTable)joinTbl;
                  orderTable = this.stmt.getTableForDatastoreContainer(collTable);
                  orderMapping = collTable.getOrderMapping();
               } else {
                  orderTable = elemSqlExpr.getSQLTable();
                  orderMapping = ((ClassTable)elemSqlExpr.getSQLTable().getTable()).getExternalMapping(mmd, 4);
               }

               return new NumericExpression(this.stmt, orderTable, orderMapping);
            }
         } else {
            throw new NucleusException("INDEX can only be used with 2 arguments - the element expression, and the collection expression");
         }
      } else {
         throw new NucleusException(Localiser.msg("060002", new Object[]{"INDEX", ignore}));
      }
   }
}
