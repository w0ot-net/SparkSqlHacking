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

public class MapValueMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() > 0) {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"mapValue", "MapExpression", 1}));
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

            if (mapmd.getValueClassMetaData(this.clr, mmgr) != null && !mapmd.isEmbeddedValue()) {
               DatastoreClass valTable = storeMgr.getDatastoreClass(mapmd.getValueType(), this.clr);
               SQLTable valueSqlTbl = this.stmt.getTable(mapJoinAlias + "_VALUE");
               if (valueSqlTbl == null) {
                  valueSqlTbl = this.stmt.innerJoin(joinSqlTbl, joinTbl.getValueMapping(), valTable, mapJoinAlias + "_VALUE", valTable.getIdMapping(), (Object[])null, (String)null);
               }

               return this.exprFactory.newExpression(this.stmt, valueSqlTbl, valTable.getIdMapping());
            }

            return this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getValueMapping());
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
            return this.exprFactory.newExpression(this.stmt, valSqlTbl, valTable.getIdMapping());
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
            AbstractMemberMetaData valKeyMmd = mapmd.getKeyClassMetaData(this.clr, mmgr).getMetaDataForMember(mmd.getValueMetaData().getMappedBy());
            JavaTypeMapping valueMapping = keyTable.getMemberMapping(valKeyMmd);
            return this.exprFactory.newExpression(this.stmt, keySqlTbl, valueMapping);
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
      JavaTypeMapping valMapping = null;
      Table mapTbl = null;
      if (mapmd.getMapType() == MapType.MAP_TYPE_JOIN) {
         mapTbl = storeMgr.getTable(mmd);
         ownerMapping = ((MapTable)mapTbl).getOwnerMapping();
         valMapping = ((MapTable)mapTbl).getValueMapping();
      } else if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         AbstractClassMetaData valCmd = mapmd.getValueClassMetaData(this.clr, mmgr);
         mapTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
         if (mmd.getMappedBy() != null) {
            ownerMapping = mapTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = ((DatastoreClass)mapTbl).getExternalMapping(mmd, 5);
         }

         valMapping = mapTbl.getIdMapping();
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

         String valFieldName = mmd.getValueMetaData().getMappedBy();
         AbstractMemberMetaData keyValMmd = keyCmd.getMetaDataForMember(valFieldName);
         valMapping = mapTbl.getMemberMapping(keyValMmd);
      }

      SQLStatement subStmt = new SQLStatement(this.stmt, storeMgr, mapTbl, (DatastoreIdentifier)null, (String)null);
      subStmt.setClassLoaderResolver(this.clr);
      SQLExpression valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valMapping);
      subStmt.select(valExpr, (String)null);
      SQLExpression elementOwnerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
      SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
      subStmt.whereAnd(elementOwnerExpr.eq(ownerIdExpr), true);
      SubqueryExpression subExpr = new SubqueryExpression(this.stmt, subStmt);
      subExpr.setJavaTypeMapping(valMapping);
      return subExpr;
   }
}
