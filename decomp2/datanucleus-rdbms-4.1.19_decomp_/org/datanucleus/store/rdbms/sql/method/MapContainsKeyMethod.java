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

public class MapContainsKeyMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 1) {
         MapExpression mapExpr = (MapExpression)expr;
         SQLExpression keyExpr = (SQLExpression)args.get(0);
         if (keyExpr.isParameter()) {
            AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
            if (mmd != null && mmd.getMap() != null) {
               Class keyCls = this.stmt.getQueryGenerator().getClassLoaderResolver().classForName(mmd.getMap().getKeyType());
               this.stmt.getQueryGenerator().bindParameter(keyExpr.getParameterName(), keyCls);
            }
         }

         if (!(expr instanceof MapLiteral)) {
            if (this.stmt.getQueryGenerator().getCompilationComponent() == CompilationComponent.FILTER) {
               boolean needsSubquery = this.getNeedsSubquery();
               if (needsSubquery) {
                  NucleusLogger.QUERY.debug("map.containsKey on " + mapExpr + "(" + keyExpr + ") using SUBQUERY");
                  return this.containsAsSubquery(mapExpr, keyExpr);
               } else {
                  NucleusLogger.QUERY.debug("map.containsKey on " + mapExpr + "(" + keyExpr + ") using INNERJOIN");
                  return this.containsAsInnerJoin(mapExpr, keyExpr);
               }
            } else {
               return this.containsAsSubquery(mapExpr, keyExpr);
            }
         } else {
            MapLiteral lit = (MapLiteral)expr;
            Map map = (Map)lit.getValue();
            JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
            if (map != null && map.size() != 0) {
               boolean useInExpression = false;
               List<SQLExpression> mapKeyExprs = lit.getKeyLiteral().getKeyExpressions();
               if (mapKeyExprs != null && !mapKeyExprs.isEmpty()) {
                  boolean incompatible = true;
                  Class elemtype = this.clr.classForName(keyExpr.getJavaTypeMapping().getType());

                  for(SQLExpression mapKeyExpr : mapKeyExprs) {
                     Class mapKeyType = this.clr.classForName(mapKeyExpr.getJavaTypeMapping().getType());
                     if (this.keyTypeCompatible(elemtype, mapKeyType)) {
                        incompatible = false;
                        break;
                     }
                  }

                  if (incompatible) {
                     return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, false));
                  }

                  SQLExpression mapKeyExpr = (SQLExpression)mapKeyExprs.get(0);
                  if (mapKeyExpr instanceof StringExpression || mapKeyExpr instanceof NumericExpression || mapKeyExpr instanceof TemporalExpression || mapKeyExpr instanceof CharacterExpression || mapKeyExpr instanceof ByteExpression || mapKeyExpr instanceof EnumExpression) {
                     useInExpression = true;
                  }
               }

               if (useInExpression) {
                  SQLExpression[] exprs = mapKeyExprs != null ? (SQLExpression[])mapKeyExprs.toArray(new SQLExpression[mapKeyExprs.size()]) : null;
                  return new InExpression(keyExpr, exprs);
               } else {
                  MapLiteral.MapKeyLiteral mapKeyLiteral = lit.getKeyLiteral();
                  BooleanExpression bExpr = null;
                  List<SQLExpression> elementExprs = mapKeyLiteral.getKeyExpressions();

                  for(int i = 0; i < elementExprs.size(); ++i) {
                     if (bExpr == null) {
                        bExpr = ((SQLExpression)elementExprs.get(i)).eq(keyExpr);
                     } else {
                        bExpr = bExpr.ior(((SQLExpression)elementExprs.get(i)).eq(keyExpr));
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
         throw new NucleusException(Localiser.msg("060016", new Object[]{"containsKey", "MapExpression", 1}));
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

   protected SQLExpression containsAsInnerJoin(MapExpression mapExpr, SQLExpression keyExpr) {
      boolean keyIsUnbound = keyExpr instanceof UnboundExpression;
      String varName = null;
      String keyAlias = null;
      if (keyIsUnbound) {
         varName = ((UnboundExpression)keyExpr).getVariableName();
         NucleusLogger.QUERY.debug("map.containsKey(" + keyExpr + ") binding unbound variable " + varName + " using INNER JOIN");
      } else if (!this.stmt.getQueryGenerator().hasExplicitJoins()) {
         SQLJoin.JoinType joinType = this.stmt.getJoinTypeForTable(keyExpr.getSQLTable());
         if (joinType == SQLJoin.JoinType.CROSS_JOIN) {
            keyAlias = this.stmt.removeCrossJoin(keyExpr.getSQLTable());
            keyIsUnbound = true;
            NucleusLogger.QUERY.debug("map.containsKey(" + keyExpr + ") was previously bound as CROSS JOIN but changing to INNER JOIN");
         }
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
      if (mmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
         MapTable mapTbl = (MapTable)storeMgr.getTable(mmd);
         SQLTable joinSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), mapTbl, (String)null, mapTbl.getOwnerMapping(), (Object[])null, (String)null);
         if (keyCmd != null) {
            if (keyIsUnbound) {
               DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
               SQLTable keySqlTbl = this.stmt.innerJoin(joinSqlTbl, mapTbl.getKeyMapping(), keyTbl, keyAlias, keyTbl.getIdMapping(), (Object[])null, (String)null);
               keyExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, keyCmd, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
            } else {
               SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getKeyMapping());
               this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         } else if (keyIsUnbound) {
            keyExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getKeyMapping());
            this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
         } else {
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, mapTbl.getKeyMapping());
            this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
         DatastoreClass valTbl = storeMgr.getDatastoreClass(valCmd.getFullClassName(), this.clr);
         AbstractMemberMetaData valKeyMmd = valCmd.getMetaDataForMember(mmd.getKeyMetaData().getMappedBy());
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = valTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = valTbl.getExternalMapping(mmd, 5);
         }

         SQLTable valSqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), valTbl, (String)null, ownerMapping, (Object[])null, (String)null);
         if (keyCmd != null) {
            DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
            SQLTable keySqlTbl = this.stmt.innerJoin(valSqlTbl, valTbl.getMemberMapping(valKeyMmd), keyTbl, keyAlias, keyTbl.getIdMapping(), (Object[])null, (String)null);
            if (keyIsUnbound) {
               keyExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, keyCmd, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
            } else {
               SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
               this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         } else if (keyIsUnbound) {
            keyExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getMemberMapping(valKeyMmd));
            this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
         } else {
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, valSqlTbl, valTbl.getMemberMapping(valKeyMmd));
            this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
         DatastoreClass keyTbl = storeMgr.getDatastoreClass(keyCmd.getFullClassName(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = keyTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = keyTbl.getExternalMapping(mmd, 5);
         }

         SQLTable keySqlTbl = this.stmt.innerJoin(mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping(), keyTbl, keyAlias, ownerMapping, (Object[])null, (String)null);
         if (keyIsUnbound) {
            keyExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
            this.stmt.getQueryGenerator().bindVariable(varName, keyCmd, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
         } else {
            SQLExpression keyIdExpr = this.exprFactory.newExpression(this.stmt, keySqlTbl, keyTbl.getIdMapping());
            this.stmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      }

      JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
      return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, true));
   }

   protected SQLExpression containsAsSubquery(MapExpression mapExpr, SQLExpression keyExpr) {
      boolean keyIsUnbound = keyExpr instanceof UnboundExpression;
      String varName = null;
      if (keyIsUnbound) {
         varName = ((UnboundExpression)keyExpr).getVariableName();
         NucleusLogger.QUERY.debug("map.containsKey binding unbound variable " + varName + " using SUBQUERY");
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      MetaDataManager mmgr = storeMgr.getMetaDataManager();
      AbstractMemberMetaData mmd = mapExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData keyCmd = mmd.getMap().getKeyClassMetaData(this.clr, mmgr);
      MapTable joinTbl = (MapTable)storeMgr.getTable(mmd);
      SQLStatement subStmt = null;
      if (mmd.getMap().getMapType() == MapType.MAP_TYPE_JOIN) {
         if (keyCmd == null) {
            subStmt = new SQLStatement(this.stmt, storeMgr, joinTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (keyIsUnbound) {
               keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getKeyMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
            } else {
               SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getKeyMapping());
               subStmt.whereAnd(elemIdExpr.eq(keyExpr), true);
            }
         } else {
            DatastoreClass keyTbl = storeMgr.getDatastoreClass(mmd.getMap().getKeyType(), this.clr);
            subStmt = new SQLStatement(this.stmt, storeMgr, keyTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLTable joinSqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), keyTbl.getIdMapping(), joinTbl, (String)null, joinTbl.getKeyMapping(), (Object[])null, (String)null);
            JavaTypeMapping ownerMapping = joinTbl.getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, joinSqlTbl, ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (keyIsUnbound) {
               keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, keyCmd, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
            } else {
               SQLExpression keyIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getIdMapping());
               subStmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_KEY_IN_VALUE) {
         AbstractClassMetaData valCmd = mmd.getMap().getValueClassMetaData(this.clr, mmgr);
         DatastoreClass valTbl = storeMgr.getDatastoreClass(mmd.getMap().getValueType(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = valTbl.getMemberMapping(valCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = valTbl.getExternalMapping(mmd, 5);
         }

         AbstractMemberMetaData valKeyMmd = valCmd.getMetaDataForMember(mmd.getKeyMetaData().getMappedBy());
         if (keyCmd == null) {
            subStmt = new SQLStatement(this.stmt, storeMgr, valTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (keyIsUnbound) {
               keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), valTbl.getMemberMapping(valKeyMmd));
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
            } else {
               JavaTypeMapping keyMapping = valTbl.getMemberMapping(valKeyMmd);
               SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyMapping);
               subStmt.whereAnd(elemIdExpr.eq(keyExpr), true);
            }
         } else {
            DatastoreClass keyTbl = storeMgr.getDatastoreClass(mmd.getMap().getKeyType(), this.clr);
            subStmt = new SQLStatement(this.stmt, storeMgr, keyTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLTable valSqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), keyTbl.getIdMapping(), valTbl, (String)null, valTbl.getMemberMapping(valKeyMmd), (Object[])null, (String)null);
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, valSqlTbl, ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            if (keyIsUnbound) {
               keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getIdMapping());
               this.stmt.getQueryGenerator().bindVariable(varName, keyCmd, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
            } else {
               SQLExpression keyIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getIdMapping());
               subStmt.whereAnd(keyIdExpr.eq(keyExpr), true);
            }
         }
      } else if (mmd.getMap().getMapType() == MapType.MAP_TYPE_VALUE_IN_KEY) {
         DatastoreClass keyTbl = storeMgr.getDatastoreClass(mmd.getMap().getKeyType(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = keyTbl.getMemberMapping(keyCmd.getMetaDataForMember(mmd.getMappedBy()));
         } else {
            ownerMapping = keyTbl.getExternalMapping(mmd, 5);
         }

         subStmt = new SQLStatement(this.stmt, storeMgr, keyTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
         subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
         SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, mapExpr.getSQLTable(), mapExpr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
         if (keyIsUnbound) {
            keyExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyTbl.getIdMapping());
            this.stmt.getQueryGenerator().bindVariable(varName, keyCmd, keyExpr.getSQLTable(), keyExpr.getJavaTypeMapping());
         } else {
            JavaTypeMapping keyMapping = keyTbl.getIdMapping();
            SQLExpression keyIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), keyMapping);
            subStmt.whereAnd(keyIdExpr.eq(keyExpr), true);
         }
      }

      return new BooleanSubqueryExpression(this.stmt, "EXISTS", subStmt);
   }

   protected boolean keyTypeCompatible(Class keyType, Class mapKeyType) {
      if (!keyType.isPrimitive() && mapKeyType.isPrimitive() && !mapKeyType.isAssignableFrom(keyType) && !keyType.isAssignableFrom(mapKeyType)) {
         return false;
      } else if (keyType.isPrimitive()) {
         if (keyType == Boolean.TYPE && mapKeyType == Boolean.class) {
            return true;
         } else if (keyType == Byte.TYPE && mapKeyType == Byte.class) {
            return true;
         } else if (keyType == Character.TYPE && mapKeyType == Character.class) {
            return true;
         } else if (keyType == Double.TYPE && mapKeyType == Double.class) {
            return true;
         } else if (keyType == Float.TYPE && mapKeyType == Float.class) {
            return true;
         } else if (keyType == Integer.TYPE && mapKeyType == Integer.class) {
            return true;
         } else if (keyType == Long.TYPE && mapKeyType == Long.class) {
            return true;
         } else {
            return keyType == Short.TYPE && mapKeyType == Short.class;
         }
      } else {
         return true;
      }
   }
}
