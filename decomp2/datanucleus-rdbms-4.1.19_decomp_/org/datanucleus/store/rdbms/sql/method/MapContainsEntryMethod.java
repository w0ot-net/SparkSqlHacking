package org.datanucleus.store.rdbms.sql.method;

import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.BooleanLiteral;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.MapExpression;
import org.datanucleus.store.rdbms.sql.expression.MapLiteral;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class MapContainsEntryMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() == 2) {
         MapExpression mapExpr = (MapExpression)expr;
         SQLExpression keyExpr = (SQLExpression)args.get(0);
         SQLExpression valExpr = (SQLExpression)args.get(1);
         if (keyExpr.isParameter()) {
            AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
            if (mmd != null && mmd.getMap() != null) {
               Class keyCls = this.stmt.getQueryGenerator().getClassLoaderResolver().classForName(mmd.getMap().getKeyType());
               this.stmt.getQueryGenerator().bindParameter(keyExpr.getParameterName(), keyCls);
            }
         }

         if (valExpr.isParameter()) {
            AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
            if (mmd != null && mmd.getMap() != null) {
               Class valCls = this.stmt.getQueryGenerator().getClassLoaderResolver().classForName(mmd.getMap().getValueType());
               this.stmt.getQueryGenerator().bindParameter(valExpr.getParameterName(), valCls);
            }
         }

         if (mapExpr instanceof MapLiteral) {
            MapLiteral lit = (MapLiteral)mapExpr;
            Map map = (Map)lit.getValue();
            return (SQLExpression)(map != null && map.size() != 0 ? lit.getValueLiteral().invoke("contains", args) : new BooleanLiteral(this.stmt, expr.getJavaTypeMapping(), Boolean.FALSE));
         } else if (this.stmt.getQueryGenerator().getCompilationComponent() == CompilationComponent.FILTER) {
            boolean needsSubquery = this.getNeedsSubquery();
            if (needsSubquery) {
               NucleusLogger.QUERY.debug("MapContainsEntry on " + mapExpr + "(" + keyExpr + "," + valExpr + ") using SUBQUERY");
               return this.containsAsSubquery(mapExpr, keyExpr, valExpr);
            } else {
               NucleusLogger.QUERY.debug("MapContainsEntry on " + mapExpr + "(" + keyExpr + "," + valExpr + ") using INNERJOIN");
               return this.containsAsInnerJoin(mapExpr, keyExpr, valExpr);
            }
         } else {
            return this.containsAsSubquery(mapExpr, keyExpr, valExpr);
         }
      } else {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"containsValue", "MapExpression", 2}));
      }
   }

   protected boolean getNeedsSubquery() {
      boolean needsSubquery = false;
      Boolean hasOR = (Boolean)this.stmt.getQueryGenerator().getProperty("Filter.OR");
      if (hasOR != null && hasOR) {
         needsSubquery = true;
      }

      Boolean hasNOT = (Boolean)this.stmt.getQueryGenerator().getProperty("Filter.NOT");
      if (hasNOT != null && hasNOT) {
         needsSubquery = true;
      }

      return needsSubquery;
   }

   protected SQLExpression containsAsInnerJoin(MapExpression mapExpr, SQLExpression keyExpr, SQLExpression valExpr) {
      boolean keyIsUnbound = keyExpr instanceof UnboundExpression;
      String keyVarName = null;
      if (keyIsUnbound) {
         keyVarName = ((UnboundExpression)keyExpr).getVariableName();
         NucleusLogger.QUERY.debug(">> Map.containsEntry binding unbound variable " + keyVarName + " using INNER JOIN");
      }

      boolean valIsUnbound = valExpr instanceof UnboundExpression;
      String valVarName = null;
      if (valIsUnbound) {
         valVarName = ((UnboundExpression)valExpr).getVariableName();
         NucleusLogger.QUERY.debug(">> Map.containsEntry binding unbound variable " + valVarName + " using INNER JOIN");
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
      AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
      if (mmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
         MapTable mapTbl = (MapTable)storeMgr.getTable(mmd);
         SQLTable joinSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), mapTbl, (String)null, mapTbl.getOwnerMapping(), (Object[])null, (String)null);
         if (valCmd != null) {
            DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
            SQLTable valSqlTbl = this.stmt.innerJoin(joinSqlTbl, mapTbl.getValueMapping(), valTbl, (String)null, valTbl.getIdMapping(), (Object[])null, (String)null);
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
            if (valIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(valVarName, valCmd, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         } else {
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getValueMapping());
            if (valIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(valVarName, (AbstractClassMetaData)null, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         }

         if (keyCmd != null) {
            DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
            SQLTable keySqlTbl = this.stmt.innerJoin(joinSqlTbl, mapTbl.getKeyMapping(), keyTbl, (String)null, keyTbl.getIdMapping(), (Object[])null, (String)null);
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
            if (keyIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         } else {
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getKeyMapping());
            if (keyIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(keyVarName, (AbstractClassMetaData)null, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = valTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = valTbl.getExternalMapping(mmd, 5);
         }

         SQLTable valSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), valTbl, (String)null, ownerMapping, (Object[])null, (String)null);
         SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
         if (valIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(valVarName, valCmd, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
         } else {
            this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
         }

         if (keyCmd != null) {
            AbstractMemberMetaData valKeyMmd = valCmd.getMetaDataForMember(mmd.getKeyMetaData().getMappedBy());
            DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
            SQLTable keySqlTbl = this.stmt.innerJoin(valSqlTbl, valTbl.getMemberMapping(valKeyMmd), keyTbl, (String)null, keyTbl.getIdMapping(), (Object[])null, (String)null);
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
            if (keyIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         } else {
            AbstractMemberMetaData valKeyMmd = valCmd.getMetaDataForMember(mmd.getKeyMetaData().getMappedBy());
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getMemberMapping(valKeyMmd));
            if (keyIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
         DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
         AbstractMemberMetaData keyValMmd = keyCmd.getMetaDataForMember(mmd.getValueMetaData().getMappedBy());
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = keyTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = keyTbl.getExternalMapping(mmd, 5);
         }

         SQLTable keySqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), keyTbl, (String)null, ownerMapping, (Object[])null, (String)null);
         SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
         if (keyIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
         } else {
            this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }

         if (valCmd != null) {
            DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
            SQLTable valSqlTbl = this.stmt.innerJoin(keySqlTbl, keyTbl.getMemberMapping(keyValMmd), valTbl, (String)null, valTbl.getIdMapping(), (Object[])null, (String)null);
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
            if (valIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(valVarName, valCmd, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         } else {
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getMemberMapping(keyValMmd));
            if (valIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(valVarName, (AbstractClassMetaData)null, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
            } else {
               this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         }
      }

      JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
      return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, true));
   }

   protected SQLExpression containsAsSubquery(MapExpression mapExpr, SQLExpression keyExpr, SQLExpression valExpr) {
      boolean keyIsUnbound = keyExpr instanceof UnboundExpression;
      String keyVarName = null;
      if (keyIsUnbound) {
         keyVarName = ((UnboundExpression)keyExpr).getVariableName();
         NucleusLogger.QUERY.debug(">> Map.containsEntry binding unbound variable " + keyVarName + " using SUBQUERY");
      }

      boolean valIsUnbound = valExpr instanceof UnboundExpression;
      String valVarName = null;
      if (valIsUnbound) {
         valVarName = ((UnboundExpression)valExpr).getVariableName();
         NucleusLogger.QUERY.debug(">> Map.containsEntry binding unbound variable " + valVarName + " using SUBQUERY");
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
      AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
      MapTable joinTbl = (MapTable)storeMgr.getTable(mmd);
      SQLStatement subStmt = null;
      if (mmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
         subStmt = new SQLStatement(this.stmt, storeMgr, joinTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
         subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
         JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
         SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
         SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getValueMapping());
         if (valIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(valVarName, valCmd, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
         } else {
            subStmt.whereAnd(valIdExpr.eq(valExpr), true);
         }

         SQLExpression keyIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getKeyMapping());
         if (keyIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
         } else {
            subStmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         DatastoreClass valTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
         AbstractMemberMetaData valKeyMmd = valCmd.getMetaDataForMember(mmd.getKeyMetaData().getMappedBy());
         subStmt = new SQLStatement(this.stmt, storeMgr, valTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
         subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = valTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = valTbl.getExternalMapping(mmd, 5);
         }

         SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
         SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getIdMapping());
         if (valIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(valVarName, valCmd, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
         } else {
            subStmt.whereAnd(valIdExpr.eq(valExpr), true);
         }

         SQLExpression keyIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getMemberMapping(valKeyMmd));
         if (keyIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
         } else {
            subStmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
         DatastoreClass keyTbl = storeMgr.getDatastoreClass(mmd.getMap().getKeyType(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = keyTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = keyTbl.getExternalMapping(mmd, 5);
         }

         AbstractMemberMetaData keyValMmd = keyCmd.getMetaDataForMember(mmd.getValueMetaData().getMappedBy());
         subStmt = new SQLStatement(this.stmt, storeMgr, keyTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
         subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
         SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
         SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getMemberMapping(keyValMmd));
         if (valIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(valVarName, valCmd, valIdExpr.getSQLTable(), valIdExpr.getJavaTypeMapping());
         } else {
            subStmt.whereAnd(valIdExpr.eq(valExpr), true);
         }

         JavaTypeMapping keyMapping = keyTbl.getIdMapping();
         SQLExpression keyIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyMapping);
         if (keyIsUnbound) {
            this.stmt.getQueryGenerator().bindVariable(keyVarName, keyCmd, keyIdExpr.getSQLTable(), keyIdExpr.getJavaTypeMapping());
         } else {
            subStmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      }

      return new BooleanSubqueryExpression(this.stmt, "EXISTS", subStmt);
   }
}
