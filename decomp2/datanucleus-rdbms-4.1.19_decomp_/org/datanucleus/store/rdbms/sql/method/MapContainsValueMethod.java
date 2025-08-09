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
import org.datanucleus.store.rdbms.sql.SQLJoin;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.ByteExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.EnumExpression;
import org.datanucleus.store.rdbms.sql.expression.InExpression;
import org.datanucleus.store.rdbms.sql.expression.MapExpression;
import org.datanucleus.store.rdbms.sql.expression.MapLiteral;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class MapContainsValueMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 1) {
         MapExpression mapExpr = (MapExpression)expr;
         SQLExpression valExpr = (SQLExpression)args.get(0);
         if (valExpr.isParameter()) {
            AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
            if (mmd != null && mmd.getMap() != null) {
               Class valCls = this.stmt.getQueryGenerator().getClassLoaderResolver().classForName(mmd.getMap().getValueType());
               this.stmt.getQueryGenerator().bindParameter(valExpr.getParameterName(), valCls);
            }
         }

         if (!(mapExpr instanceof MapLiteral)) {
            if (this.stmt.getQueryGenerator().getCompilationComponent() == CompilationComponent.FILTER) {
               boolean needsSubquery = this.getNeedsSubquery();
               if (needsSubquery) {
                  NucleusLogger.QUERY.debug("map.containsValue on " + mapExpr + "(" + valExpr + ") using SUBQUERY");
                  return this.containsAsSubquery(mapExpr, valExpr);
               } else {
                  NucleusLogger.QUERY.debug("map.containsValue on " + mapExpr + "(" + valExpr + ") using INNERJOIN");
                  return this.containsAsInnerJoin(mapExpr, valExpr);
               }
            } else {
               return this.containsAsSubquery(mapExpr, valExpr);
            }
         } else {
            MapLiteral lit = (MapLiteral)mapExpr;
            Map map = (Map)lit.getValue();
            JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
            if (map != null && map.size() != 0) {
               boolean useInExpression = false;
               List<SQLExpression> mapValExprs = lit.getValueLiteral().getValueExpressions();
               if (mapValExprs != null && !mapValExprs.isEmpty()) {
                  boolean incompatible = true;
                  Class elemtype = this.clr.classForName(valExpr.getJavaTypeMapping().getType());

                  for(SQLExpression mapKeyExpr : mapValExprs) {
                     Class mapKeyType = this.clr.classForName(mapKeyExpr.getJavaTypeMapping().getType());
                     if (this.valueTypeCompatible(elemtype, mapKeyType)) {
                        incompatible = false;
                        break;
                     }
                  }

                  if (incompatible) {
                     return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, false));
                  }

                  SQLExpression mapKeyExpr = (SQLExpression)mapValExprs.get(0);
                  if (mapKeyExpr instanceof StringExpression || mapKeyExpr instanceof NumericExpression || mapKeyExpr instanceof TemporalExpression || mapKeyExpr instanceof CharacterExpression || mapKeyExpr instanceof ByteExpression || mapKeyExpr instanceof EnumExpression) {
                     useInExpression = true;
                  }
               }

               if (useInExpression) {
                  SQLExpression[] exprs = mapValExprs != null ? (SQLExpression[])mapValExprs.toArray(new SQLExpression[mapValExprs.size()]) : null;
                  return new InExpression(valExpr, exprs);
               } else {
                  MapLiteral.MapValueLiteral mapValueLiteral = lit.getValueLiteral();
                  BooleanExpression bExpr = null;
                  List<SQLExpression> elementExprs = mapValueLiteral.getValueExpressions();

                  for(int i = 0; i < elementExprs.size(); ++i) {
                     if (bExpr == null) {
                        bExpr = ((SQLExpression)elementExprs.get(i)).eq(valExpr);
                     } else {
                        bExpr = bExpr.ior(((SQLExpression)elementExprs.get(i)).eq(valExpr));
                     }
                  }

                  if (bExpr != null) {
                     bExpr.encloseInParentheses();
                  }

                  return bExpr;
               }
            } else {
               return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, false));
            }
         }
      } else {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"containsValue", "MapExpression", 1}));
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

   protected SQLExpression containsAsInnerJoin(MapExpression mapExpr, SQLExpression valExpr) {
      boolean valIsUnbound = valExpr instanceof UnboundExpression;
      String varName = null;
      String valAlias = null;
      if (valIsUnbound) {
         varName = ((UnboundExpression)valExpr).getVariableName();
         NucleusLogger.QUERY.debug("map.containsValue(" + valExpr + ") binding unbound variable " + varName + " using INNER JOIN");
      } else if (!this.stmt.getQueryGenerator().hasExplicitJoins()) {
         SQLJoin.JoinType joinType = this.stmt.getJoinTypeForTable(valExpr.getSQLTable());
         if (joinType == SQLJoin.JoinType.CROSS_JOIN) {
            valAlias = this.stmt.removeCrossJoin(valExpr.getSQLTable());
            valIsUnbound = true;
            NucleusLogger.QUERY.debug("map.containsValue(" + valExpr + ") was previously bound as CROSS JOIN but changing to INNER JOIN");
         }
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
      if (mmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
         MapTable mapTbl = (MapTable)storeMgr.getTable(mmd);
         SQLTable joinSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), mapTbl, (String)null, mapTbl.getOwnerMapping(), (Object[])null, (String)null);
         if (valCmd != null) {
            if (valIsUnbound) {
               DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
               SQLTable valSqlTbl = this.stmt.innerJoin(joinSqlTbl, mapTbl.getValueMapping(), valTbl, valAlias, valTbl.getIdMapping(), (Object[])null, (String)null);
               valExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valSqlTbl.getTable().getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, valCmd, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
            } else {
               SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getValueMapping());
               this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         } else if (valIsUnbound) {
            valExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getValueMapping());
            this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
         } else {
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getValueMapping());
            this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = valTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = valTbl.getExternalMapping(mmd, 5);
         }

         SQLTable valSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), valTbl, valAlias, ownerMapping, (Object[])null, (String)null);
         if (valIsUnbound) {
            valExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
            this.stmt.getQueryGenerator().bindVariable(varName, valCmd, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
         } else {
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
            this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
         AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
         DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
         AbstractMemberMetaData keyValMmd = keyCmd.getMetaDataForMember(mmd.getValueMetaData().getMappedBy());
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = keyTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = keyTbl.getExternalMapping(mmd, 5);
         }

         SQLTable keySqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), keyTbl, (String)null, ownerMapping, (Object[])null, (String)null);
         if (valCmd != null) {
            DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
            SQLTable valSqlTbl = this.stmt.innerJoin(keySqlTbl, keyTbl.getMemberMapping(keyValMmd), valTbl, valAlias, valTbl.getIdMapping(), (Object[])null, (String)null);
            if (valIsUnbound) {
               valExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, valCmd, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
            } else {
               SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getIdMapping());
               this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         } else if (valIsUnbound) {
            valExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getMemberMapping(keyValMmd));
            this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
         } else {
            SQLExpression valIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getMemberMapping(keyValMmd));
            this.stmt.whereAnd(valIdExpr.eq(valExpr), true);
         }
      }

      JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
      return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, true));
   }

   protected SQLExpression containsAsSubquery(MapExpression mapExpr, SQLExpression valExpr) {
      boolean valIsUnbound = valExpr instanceof UnboundExpression;
      String varName = null;
      if (valIsUnbound) {
         varName = ((UnboundExpression)valExpr).getVariableName();
         NucleusLogger.QUERY.debug("map.containsValue binding unbound variable " + varName + " using SUBQUERY");
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
      MapTable joinTbl = (MapTable)storeMgr.getTable(mmd);
      SQLStatement subStmt = null;
      if (mmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
         if (valCmd == null) {
            subStmt = new SQLStatement(this.stmt, storeMgr, joinTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (valIsUnbound) {
               valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getValueMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
            } else {
               SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getValueMapping());
               subStmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         } else {
            DatastoreClass valTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
            subStmt = new SQLStatement(this.stmt, storeMgr, valTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLTable joinSqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), valTbl.getIdMapping(), joinTbl, (String)null, joinTbl.getValueMapping(), (Object[])null, (String)null);
            JavaTypeMapping ownerMapping = joinTbl.getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, joinSqlTbl, ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (valIsUnbound) {
               valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, valCmd, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
            } else {
               SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getIdMapping());
               subStmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         DatastoreClass valTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = valTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = valTbl.getExternalMapping(mmd, 5);
         }

         subStmt = new SQLStatement(this.stmt, storeMgr, valTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
         subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
         SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
         if (valIsUnbound) {
            valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getIdMapping());
            this.stmt.getQueryGenerator().bindVariable(varName, valCmd, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
         } else {
            JavaTypeMapping valMapping = valTbl.getIdMapping();
            SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valMapping);
            subStmt.whereAnd(valIdExpr.eq(valExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
         AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
         DatastoreClass keyTbl = storeMgr.getDatastoreClass(mmd.getMap().getKeyType(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = keyTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = keyTbl.getExternalMapping(mmd, 5);
         }

         AbstractMemberMetaData keyValMmd = keyCmd.getMetaDataForMember(mmd.getValueMetaData().getMappedBy());
         if (valCmd == null) {
            subStmt = new SQLStatement(this.stmt, storeMgr, keyTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (valIsUnbound) {
               valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getMemberMapping(keyValMmd));
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
            } else {
               JavaTypeMapping valMapping = keyTbl.getMemberMapping(keyValMmd);
               SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valMapping);
               subStmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         } else {
            DatastoreClass valTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
            subStmt = new SQLStatement(this.stmt, storeMgr, valTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLTable keySqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), valTbl.getIdMapping(), keyTbl, (String)null, keyTbl.getMemberMapping(keyValMmd), (Object[])null, (String)null);
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, keySqlTbl, ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (valIsUnbound) {
               valExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, valCmd, valExpr.getSQLTable(), valExpr.getJavaTypeMapping());
            } else {
               SQLExpression valIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getIdMapping());
               subStmt.whereAnd(valIdExpr.eq(valExpr), true);
            }
         }
      }

      return new BooleanSubqueryExpression(this.stmt, "EXISTS", subStmt);
   }

   protected boolean valueTypeCompatible(Class valType, Class mapValType) {
      if (!valType.isPrimitive() && mapValType.isPrimitive() && !mapValType.isAssignableFrom(valType) && !valType.isAssignableFrom(mapValType)) {
         return false;
      } else if (valType.isPrimitive()) {
         if (valType == Boolean.TYPE && mapValType == Boolean.class) {
            return true;
         } else if (valType == Byte.TYPE && mapValType == Byte.class) {
            return true;
         } else if (valType == Character.TYPE && mapValType == Character.class) {
            return true;
         } else if (valType == Double.TYPE && mapValType == Double.class) {
            return true;
         } else if (valType == Float.TYPE && mapValType == Float.class) {
            return true;
         } else if (valType == Integer.TYPE && mapValType == Integer.class) {
            return true;
         } else if (valType == Long.TYPE && mapValType == Long.class) {
            return true;
         } else {
            return valType == Short.TYPE && mapValType == Short.class;
         }
      } else {
         return true;
      }
   }
}
