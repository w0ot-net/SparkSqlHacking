package org.datanucleus.store.rdbms.sql.method;

import java.util.Collection;
import java.util.List;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.CollectionLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class CollectionSizeMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"size", "CollectionExpression"}));
      } else if (expr instanceof CollectionLiteral) {
         Collection coll = (Collection)((CollectionLiteral)expr).getValue();
         return this.exprFactory.newLiteral(this.stmt, this.exprFactory.getMappingForType(Integer.TYPE, false), coll.size());
      } else {
         AbstractMemberMetaData mmd = expr.getJavaTypeMapping().getMemberMetaData();
         if (mmd.isSerialized()) {
            throw new NucleusUserException("Cannot perform Collection.size when the collection is being serialised");
         } else {
            ApiAdapter api = this.stmt.getRDBMSManager().getApiAdapter();
            Class elementCls = this.clr.classForName(mmd.getCollection().getElementType());
            if (!api.isPersistable(elementCls) && mmd.getJoinMetaData() == null) {
               throw new NucleusUserException("Cannot perform Collection.size when the collection<Non-Persistable> is not in a join table");
            } else {
               String elementType = mmd.getCollection().getElementType();
               RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
               JavaTypeMapping ownerMapping = null;
               Table collectionTbl = null;
               if (mmd.getMappedBy() != null) {
                  AbstractMemberMetaData elementMmd = mmd.getRelatedMemberMetaData(this.clr)[0];
                  if (mmd.getJoinMetaData() == null && elementMmd.getJoinMetaData() == null) {
                     collectionTbl = storeMgr.getDatastoreClass(elementType, this.clr);
                     ownerMapping = collectionTbl.getMemberMapping(elementMmd);
                  } else {
                     collectionTbl = storeMgr.getTable(mmd);
                     ownerMapping = ((JoinTable)collectionTbl).getOwnerMapping();
                  }
               } else if (mmd.getJoinMetaData() != null) {
                  collectionTbl = storeMgr.getTable(mmd);
                  ownerMapping = ((JoinTable)collectionTbl).getOwnerMapping();
               } else {
                  collectionTbl = storeMgr.getDatastoreClass(elementType, this.clr);
                  ownerMapping = ((DatastoreClass)collectionTbl).getExternalMapping(mmd, 5);
               }

               SQLStatement subStmt = new SQLStatement(this.stmt, storeMgr, collectionTbl, (DatastoreIdentifier)null, (String)null);
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
   }
}
