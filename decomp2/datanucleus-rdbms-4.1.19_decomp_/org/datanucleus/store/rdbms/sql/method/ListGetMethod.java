package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.CollectionExpression;
import org.datanucleus.store.rdbms.sql.expression.CollectionLiteral;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.SubqueryExpression;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class ListGetMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 1) {
         CollectionExpression listExpr = (CollectionExpression)expr;
         AbstractMemberMetaData mmd = listExpr.getJavaTypeMapping().getMemberMetaData();
         if (!List.class.isAssignableFrom(mmd.getType())) {
            throw new UnsupportedOperationException("Query contains " + expr + ".get(int) yet the field is not a List!");
         } else if (mmd.getOrderMetaData() != null && !mmd.getOrderMetaData().isIndexedList()) {
            throw new UnsupportedOperationException("Query contains " + expr + ".get(int) yet the field is not an 'indexed' List!");
         } else {
            SQLExpression idxExpr = (SQLExpression)args.get(0);
            if (idxExpr instanceof SQLLiteral) {
               if (!(((SQLLiteral)idxExpr).getValue() instanceof Number)) {
                  throw new UnsupportedOperationException("Query contains " + expr + ".get(int) yet the index is not a numeric literal so not yet supported");
               } else if (listExpr instanceof CollectionLiteral && idxExpr instanceof SQLLiteral) {
                  CollectionLiteral lit = (CollectionLiteral)expr;
                  return (SQLExpression)(lit.getValue() == null ? new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null) : lit.invoke("get", args));
               } else if (this.stmt.getQueryGenerator().getCompilationComponent() == CompilationComponent.FILTER) {
                  return this.getAsInnerJoin(listExpr, idxExpr);
               } else if (this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.ORDERING && this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.RESULT && this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.HAVING) {
                  throw new NucleusException("List.get() is not supported for " + listExpr + " with argument " + idxExpr + " for query component " + this.stmt.getQueryGenerator().getCompilationComponent());
               } else {
                  return this.getAsSubquery(listExpr, idxExpr);
               }
            } else {
               throw new UnsupportedOperationException("Query contains " + expr + ".get(int) yet the index is not a numeric literal so not yet supported");
            }
         }
      } else {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"get", "CollectionExpression", 1}));
      }
   }

   protected SQLExpression getAsSubquery(CollectionExpression listExpr, SQLExpression idxExpr) {
      AbstractMemberMetaData mmd = listExpr.getJavaTypeMapping().getMemberMetaData();
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      JavaTypeMapping ownerMapping = null;
      JavaTypeMapping indexMapping = null;
      JavaTypeMapping elemMapping = null;
      Table listTbl = null;
      if (mmd != null) {
         AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
         if (mmd.getJoinMetaData() == null && (relatedMmds == null || relatedMmds[0].getJoinMetaData() == null)) {
            DatastoreClass elemTbl = storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
            listTbl = elemTbl;
            if (relatedMmds != null) {
               ownerMapping = elemTbl.getMemberMapping(relatedMmds[0]);
               indexMapping = elemTbl.getExternalMapping(mmd, 4);
               elemMapping = elemTbl.getIdMapping();
            } else {
               ownerMapping = elemTbl.getExternalMapping(mmd, 5);
               indexMapping = elemTbl.getExternalMapping(mmd, 4);
               elemMapping = elemTbl.getIdMapping();
            }
         } else {
            listTbl = storeMgr.getTable(mmd);
            ownerMapping = ((CollectionTable)listTbl).getOwnerMapping();
            indexMapping = ((CollectionTable)listTbl).getOrderMapping();
            elemMapping = ((CollectionTable)listTbl).getElementMapping();
         }
      }

      SQLStatement subStmt = new SQLStatement(this.stmt, storeMgr, listTbl, (DatastoreIdentifier)null, (String)null);
      subStmt.setClassLoaderResolver(this.clr);
      SQLExpression valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), elemMapping);
      subStmt.select(valExpr, (String)null);
      SQLExpression elementOwnerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
      SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, listExpr.getSQLTable(), listExpr.getSQLTable().getTable().getIdMapping());
      subStmt.whereAnd(elementOwnerExpr.eq(ownerIdExpr), true);
      SQLExpression keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), indexMapping);
      subStmt.whereAnd(keyExpr.eq(idxExpr), true);
      SubqueryExpression subExpr = new SubqueryExpression(this.stmt, subStmt);
      subExpr.setJavaTypeMapping(elemMapping);
      return subExpr;
   }

   protected SQLExpression getAsInnerJoin(CollectionExpression listExpr, SQLExpression idxExpr) {
      JavaTypeMapping m = listExpr.getJavaTypeMapping();
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      AbstractMemberMetaData mmd = m.getMemberMetaData();
      AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
      if (mmd.getJoinMetaData() == null && (relatedMmds == null || relatedMmds[0].getJoinMetaData() == null)) {
         DatastoreClass elementTbl = storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
         JavaTypeMapping targetMapping = null;
         JavaTypeMapping orderMapping = null;
         if (relatedMmds != null) {
            targetMapping = elementTbl.getMemberMapping(relatedMmds[0]);
            orderMapping = elementTbl.getExternalMapping(mmd, 4);
         } else {
            targetMapping = elementTbl.getExternalMapping(mmd, 5);
            orderMapping = elementTbl.getExternalMapping(mmd, 4);
         }

         SQLTable elemSqlTbl = this.stmt.innerJoin(listExpr.getSQLTable(), listExpr.getSQLTable().getTable().getIdMapping(), elementTbl, (String)null, targetMapping, (Object[])null, (String)null);
         SQLExpression idxSqlExpr = this.exprFactory.newExpression(this.stmt, elemSqlTbl, orderMapping);
         this.stmt.whereAnd(idxSqlExpr.eq(idxExpr), true);
         return this.exprFactory.newExpression(this.stmt, elemSqlTbl, elementTbl.getIdMapping());
      } else {
         CollectionTable joinTbl = (CollectionTable)storeMgr.getTable(mmd);
         SQLTable joinSqlTbl = this.stmt.innerJoin(listExpr.getSQLTable(), listExpr.getSQLTable().getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null);
         SQLExpression idxSqlExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getOrderMapping());
         this.stmt.whereAnd(idxSqlExpr.eq(idxExpr), true);
         SQLExpression valueExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getElementMapping());
         return valueExpr;
      }
   }
}
