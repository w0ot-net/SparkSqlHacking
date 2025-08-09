package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.MapExpression;
import org.datanucleus.store.rdbms.sql.expression.MapLiteral;
import org.datanucleus.store.rdbms.sql.expression.NullLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLLiteral;
import org.datanucleus.store.rdbms.sql.expression.SubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class MapGetMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 1) {
         MapExpression mapExpr = (MapExpression)expr;
         SQLExpression keyValExpr = (SQLExpression)args.get(0);
         if (keyValExpr instanceof UnboundExpression) {
            throw new NucleusException("Dont currently support binding of unbound variables using Map.get");
         } else if (mapExpr instanceof MapLiteral && keyValExpr instanceof SQLLiteral) {
            MapLiteral lit = (MapLiteral)expr;
            return (SQLExpression)(lit.getValue() == null ? new NullLiteral(this.stmt, (JavaTypeMapping)null, (Object)null, (String)null) : lit.getKeyLiteral().invoke("get", args));
         } else if (mapExpr instanceof MapLiteral) {
            throw new NucleusUserException("We do not support MapLiteral.get(SQLExpression) since SQL doesnt allow such constructs");
         } else if (this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.FILTER && this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.ORDERING) {
            if (this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.RESULT && this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.HAVING) {
               throw new NucleusException("Map.get() is not supported for " + mapExpr + " with argument " + keyValExpr + " for query component " + this.stmt.getQueryGenerator().getCompilationComponent());
            } else {
               return this.getAsSubquery(mapExpr, keyValExpr);
            }
         } else {
            return this.getAsInnerJoin(mapExpr, keyValExpr);
         }
      } else {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"get", "MapExpression", 1}));
      }
   }

   protected SQLExpression getAsSubquery(MapExpression mapExpr, SQLExpression keyValExpr) {
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      MapMetaData mapmd = mmd.getMap();
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      JavaTypeMapping ownerMapping = null;
      JavaTypeMapping keyMapping = null;
      JavaTypeMapping valMapping = null;
      Table mapTbl = null;
      if (mapmd.getMapType() == MapType.MAP_TYPE_JOIN) {
         mapTbl = storeMgr.getTable(mmd);
         ownerMapping = ((MapTable)mapTbl).getOwnerMapping();
         keyMapping = ((MapTable)mapTbl).getKeyMapping();
         valMapping = ((MapTable)mapTbl).getValueMapping();
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

         keyMapping = mapTbl.getIdMapping();
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
      SQLExpression keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyMapping);
      subStmt.whereAnd(keyExpr.eq(keyValExpr), true);
      SubqueryExpression subExpr = new SubqueryExpression(this.stmt, subStmt);
      subExpr.setJavaTypeMapping(valMapping);
      return subExpr;
   }

   protected SQLExpression getAsInnerJoin(MapExpression mapExpr, SQLExpression keyValExpr) {
      JavaTypeMapping m = mapExpr.getJavaTypeMapping();
      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = m.getMemberMetaData();
      if (mmd != null) {
         MapMetaData mapmd = mmd.getMap();
         if (mapmd.getMapType() == MapType.MAP_TYPE_JOIN) {
            MapTable joinTbl = (MapTable)this.stmt.getRDBMSManager().getTable(mmd);
            SQLTable joinSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null);
            SQLExpression keyExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getKeyMapping());
            this.stmt.whereAnd(keyExpr.eq(keyValExpr), true);
            if (mapmd.getValueClassMetaData(this.clr, mmgr) != null) {
               DatastoreClass valTable = this.stmt.getRDBMSManager().getDatastoreClass(mapmd.getValueType(), this.clr);
               SQLTable valueSqlTbl = this.stmt.innerJoin(joinSqlTbl, joinTbl.getValueMapping(), valTable, (String)null, valTable.getIdMapping(), (Object[])null, (String)null);
               return this.exprFactory.newExpression(this.stmt, valueSqlTbl, valTable.getIdMapping());
            }

            SQLExpression valueExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getValueMapping());
            return valueExpr;
         }

         if (mapmd.getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
            DatastoreClass valTable = this.stmt.getRDBMSManager().getDatastoreClass(mapmd.getValueType(), this.clr);
            AbstractClassMetaData valCmd = mapmd.getValueClassMetaData(this.clr, mmgr);
            JavaTypeMapping mapTblOwnerMapping;
            if (mmd.getMappedBy() != null) {
               mapTblOwnerMapping = valTable.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
            } else {
               mapTblOwnerMapping = valTable.getExternalMapping(mmd, 5);
            }

            SQLTable valSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), valTable, (String)null, mapTblOwnerMapping, (Object[])null, (String)null);
            JavaTypeMapping keyMapping = valTable.getMemberMapping(valCmd.getMetaDataForMember(mmd.getKeyMetaData().getMappedBy()));
            SQLExpression keyExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, keyMapping);
            this.stmt.whereAnd(keyExpr.eq(keyValExpr), true);
            SQLExpression valueExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTable.getIdMapping());
            return valueExpr;
         }

         if (mapmd.getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
            DatastoreClass keyTable = this.stmt.getRDBMSManager().getDatastoreClass(mapmd.getKeyType(), this.clr);
            AbstractClassMetaData keyCmd = mapmd.getKeyClassMetaData(this.clr, mmgr);
            JavaTypeMapping mapTblOwnerMapping;
            if (mmd.getMappedBy() != null) {
               mapTblOwnerMapping = keyTable.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
            } else {
               mapTblOwnerMapping = keyTable.getExternalMapping(mmd, 5);
            }

            SQLTable keySqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), keyTable, (String)null, mapTblOwnerMapping, (Object[])null, (String)null);
            SQLExpression keyExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTable.getIdMapping());
            this.stmt.whereAnd(keyExpr.eq(keyValExpr), true);
            JavaTypeMapping valueMapping = keyTable.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getValueMetaData().getMappedBy()));
            SQLExpression valueExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, valueMapping);
            return valueExpr;
         }
      }

      throw new NucleusException("Map.get() for the filter is not supported for " + mapExpr + " with an argument of " + keyValExpr + ". Why not contribute support for it?");
   }
}
