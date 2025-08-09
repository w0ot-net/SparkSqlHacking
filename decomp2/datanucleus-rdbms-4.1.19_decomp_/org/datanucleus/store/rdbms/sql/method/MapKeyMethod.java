package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.MapExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SubqueryExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class MapKeyMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"mapKey", "MapExpression", 0}));
      } else {
         MapExpression mapExpr = (MapExpression)expr;
         return this.getAsJoin(mapExpr);
      }
   }

   protected SQLExpression getAsJoin(MapExpression mapExpr) {
      JavaTypeMapping m = mapExpr.getJavaTypeMapping();
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = m.getMemberMetaData();
      this.stmt = mapExpr.getSQLTable().getSQLStatement();
      if (mmd != null) {
         MapMetaData mapmd = mmd.getMap();
         SQLTable mapSqlTbl = mapExpr.getSQLTable();
         String mapJoinAlias = mapExpr.getAliasForMapTable();
         if (mapJoinAlias == null) {
            mapJoinAlias = (mapExpr.getSQLTable().getAlias().toString() + "_" + mmd.getName()).toUpperCase();
         }

         if (mapmd.getMapType() == MapType.MAP_TYPE_JOIN) {
            MapTable joinTbl = (MapTable)storeMgr.getTable(mmd);
            SQLTable joinSqlTbl = this.stmt.getTable(mapJoinAlias);
            if (joinSqlTbl == null) {
               joinSqlTbl = this.stmt.innerJoin(mapSqlTbl, mapSqlTbl.getTable().getIdMapping(), joinTbl, mapJoinAlias, joinTbl.getOwnerMapping(), (Object[])null, (String)null);
            }

            if (mapmd.getKeyClassMetaData(this.clr, mmgr) != null && !mapmd.isEmbeddedKey()) {
               DatastoreClass keyTable = storeMgr.getDatastoreClass(mapmd.getKeyType(), this.clr);
               SQLTable keySqlTbl = this.stmt.getTable(mapJoinAlias + "_KEY");
               if (keySqlTbl == null) {
                  keySqlTbl = this.stmt.innerJoin(joinSqlTbl, joinTbl.getKeyMapping(), keyTable, mapJoinAlias + "_KEY", keyTable.getIdMapping(), (Object[])null, (String)null);
               }

               return this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTable.getIdMapping());
            }

            return this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getKeyMapping());
         }

         if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
            DatastoreClass valTable = storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
            AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
            JavaTypeMapping mapTblOwnerMapping;
            if (mmd.getMappedBy() != null) {
               mapTblOwnerMapping = valTable.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
            } else {
               mapTblOwnerMapping = valTable.getExternalMapping(mmd, 5);
            }

            SQLTable valSqlTbl = this.stmt.innerJoin(mapSqlTbl, mapSqlTbl.getTable().getIdMapping(), valTable, mapJoinAlias, mapTblOwnerMapping, (Object[])null, (String)null);
            AbstractMemberMetaData valKeyMmd = mapmd.getValueClassMetaData(this.clr, mmgr).getMetaDataForMember(mmd.getKeyMetaData().getMappedBy());
            JavaTypeMapping keyMapping = valTable.getMemberMapping(valKeyMmd);
            return this.exprFactory.newExpression(this.stmt, valSqlTbl, keyMapping);
         }

         if (mapmd.getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
            DatastoreClass keyTable = storeMgr.getDatastoreClass(mapmd.getKeyType(), this.clr);
            AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
            JavaTypeMapping mapTblOwnerMapping;
            if (mmd.getMappedBy() != null) {
               mapTblOwnerMapping = keyTable.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
            } else {
               mapTblOwnerMapping = keyTable.getExternalMapping(mmd, 5);
            }

            SQLTable keySqlTbl = this.stmt.innerJoin(mapSqlTbl, mapSqlTbl.getTable().getIdMapping(), keyTable, mapJoinAlias, mapTblOwnerMapping, (Object[])null, (String)null);
            return this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTable.getIdMapping());
         }
      }

      throw new NucleusException("KEY(map) for the filter is not supported for " + mapExpr + ". Why not contribute support for it?");
   }

   protected SQLExpression getAsSubquery(MapExpression mapExpr) {
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      MapMetaData mapmd = mmd.getMap();
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      JavaTypeMapping ownerMapping = null;
      JavaTypeMapping keyMapping = null;
      Table mapTbl = null;
      if (mapmd.getMapType() == MapType.MAP_TYPE_JOIN) {
         mapTbl = storeMgr.getTable(mmd);
         ownerMapping = ((MapTable)mapTbl).getOwnerMapping();
         keyMapping = ((MapTable)mapTbl).getKeyMapping();
      } else if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         AbstractClassMetaData valCmd = mapmd.getValueClassMetaData(this.clr, mmgr);
         mapTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
         if (mmd.getMappedBy() != null) {
            ownerMapping = mapTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = ((DatastoreClass)mapTbl).getExternalMapping(mmd, 5);
         }

         String keyFieldName = mmd.getKeyMetaData().getMappedBy();
         AbstractMemberMetaData valKeyMmd = valCmd.getMetaDataForMember(keyFieldName);
         keyMapping = mapTbl.getMemberMapping(valKeyMmd);
      } else {
         if (mapmd.getMapType() != MapType.MAP_TYPE_VALUE_IN_KEY) {
            throw new NucleusException("Invalid map for " + mapExpr + " in get() call");
         }

         AbstractClassMetaData keyCmd = mapmd.getKeyClassMetaData(this.clr, mmgr);
         mapTbl = storeMgr.getDatastoreClass(mmd.getMap().getKeyType(), this.clr);
         if (mmd.getMappedBy() != null) {
            ownerMapping = mapTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = ((DatastoreClass)mapTbl).getExternalMapping(mmd, 5);
         }

         keyMapping = mapTbl.getIdMapping();
      }

      SQLStatement subStmt = new SQLStatement(this.stmt, storeMgr, mapTbl, (DatastoreIdentifier)null, (String)null);
      subStmt.setClassLoaderResolver(this.clr);
      SQLExpression keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyMapping);
      subStmt.select(keyExpr, (String)null);
      SQLExpression elementOwnerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
      SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
      subStmt.whereAnd(elementOwnerExpr.eq(ownerIdExpr), true);
      SubqueryExpression subExpr = new SubqueryExpression(this.stmt, subStmt);
      subExpr.setJavaTypeMapping(keyMapping);
      return subExpr;
   }
}
