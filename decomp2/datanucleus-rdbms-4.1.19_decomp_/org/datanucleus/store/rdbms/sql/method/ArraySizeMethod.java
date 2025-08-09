package org.datanucleus.store.rdbms.sql.method;

import java.lang.reflect.Array;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.ArrayLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class ArraySizeMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"size/length", "ArrayExpression"}));
      } else if (expr instanceof ArrayLiteral) {
         return this.exprFactory.newLiteral(this.stmt, this.exprFactory.getMappingForType(Integer.TYPE, false), Array.getLength(((ArrayLiteral)expr).getValue()));
      } else {
         AbstractMemberMetaData ownerMmd = expr.getJavaTypeMapping().getMemberMetaData();
         String elementType = ownerMmd.getArray().getElementType();
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         JavaTypeMapping ownerMapping = null;
         Table arrayTbl = null;
         if (ownerMmd.getMappedBy() != null) {
            AbstractMemberMetaData elementMmd = ownerMmd.getRelatedMemberMetaData(this.clr)[0];
            if (ownerMmd.getJoinMetaData() == null && elementMmd.getJoinMetaData() == null) {
               arrayTbl = storeMgr.getDatastoreClass(elementType, this.clr);
               ownerMapping = arrayTbl.getMemberMapping(elementMmd);
            } else {
               arrayTbl = storeMgr.getTable(ownerMmd);
               ownerMapping = ((JoinTable)arrayTbl).getOwnerMapping();
            }
         } else if (ownerMmd.getJoinMetaData() != null) {
            arrayTbl = storeMgr.getTable(ownerMmd);
            ownerMapping = ((JoinTable)arrayTbl).getOwnerMapping();
         } else {
            arrayTbl = storeMgr.getDatastoreClass(elementType, this.clr);
            ownerMapping = ((DatastoreClass)arrayTbl).getExternalMapping(ownerMmd, 5);
         }

         SQLStatement subStmt = new SQLStatement(this.stmt, storeMgr, arrayTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping mapping = storeMgr.getMappingManager().getMappingWithDatastoreMapping(String.class, false, false, this.clr);
         SQLExpression countExpr = this.exprFactory.newLiteral(subStmt, mapping, "COUNT(*)");
         ((StringLiteral)countExpr).generateStatementWithoutQuotes();
         subStmt.select(countExpr, (String)null);
         SQLExpression elementOwnerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, expr.getSQLTable(), expr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(elementOwnerExpr.eq(ownerIdExpr), true);
         JavaTypeMapping subqMapping = this.exprFactory.getMappingForType(Integer.class, false);
         SQLExpression subqExpr = new NumericSubqueryExpression(this.stmt, subStmt);
         subqExpr.setJavaTypeMapping(subqMapping);
         return subqExpr;
      }
   }
}
