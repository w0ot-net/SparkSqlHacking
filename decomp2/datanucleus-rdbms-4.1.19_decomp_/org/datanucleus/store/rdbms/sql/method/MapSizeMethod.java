package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.expression.MapLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class MapSizeMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060015", new Object[]{"size", "MapExpression"}));
      } else if (expr instanceof MapLiteral) {
         Map map = (Map)((MapLiteral)expr).getValue();
         return this.exprFactory.newLiteral(this.stmt, this.exprFactory.getMappingForType(Integer.TYPE, false), map.size());
      } else {
         AbstractMemberMetaData ownerMmd = expr.getJavaTypeMapping().getMemberMetaData();
         RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
         JavaTypeMapping ownerMapping = null;
         Table mapTbl = null;
         if (ownerMmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
            mapTbl = storeMgr.getTable(ownerMmd);
            ownerMapping = ((JoinTable)mapTbl).getOwnerMapping();
         } else if (ownerMmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
            AbstractClassMetaData valueCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(ownerMmd.getMap().getValueType(), this.clr);
            mapTbl = storeMgr.getDatastoreClass(ownerMmd.getMap().getValueType(), this.clr);
            if (ownerMmd.getMappedBy() != null) {
               ownerMapping = mapTbl.getMemberMapping(valueCmd.getMetaDataForMember(ownerMmd.getMappedBy()));
            } else {
               ownerMapping = ((DatastoreClass)mapTbl).getExternalMapping(ownerMmd, 5);
            }
         } else {
            if (ownerMmd.getMap().getMapType() != MapType.MAP_TYPE_VALUE_IN_KEY) {
               throw new NucleusException("Invalid map for " + expr + " in size() call");
            }

            AbstractClassMetaData keyCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(ownerMmd.getMap().getKeyType(), this.clr);
            mapTbl = storeMgr.getDatastoreClass(ownerMmd.getMap().getKeyType(), this.clr);
            if (ownerMmd.getMappedBy() != null) {
               ownerMapping = mapTbl.getMemberMapping(keyCmd.getMetaDataForMember(ownerMmd.getMappedBy()));
            } else {
               ownerMapping = ((DatastoreClass)mapTbl).getExternalMapping(ownerMmd, 5);
            }
         }

         SQLStatement subStmt = new SQLStatement(this.stmt, storeMgr, mapTbl, (DatastoreIdentifier)null, (String)null);
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
