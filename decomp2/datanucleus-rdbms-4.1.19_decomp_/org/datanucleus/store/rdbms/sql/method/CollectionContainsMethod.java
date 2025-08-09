package org.datanucleus.store.rdbms.sql.method;

import java.util.Collection;
import java.util.List;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.query.compiler.CompilationComponent;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.sql.SQLJoin;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.ByteExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.CollectionExpression;
import org.datanucleus.store.rdbms.sql.expression.CollectionLiteral;
import org.datanucleus.store.rdbms.sql.expression.EnumExpression;
import org.datanucleus.store.rdbms.sql.expression.IllegalExpressionOperationException;
import org.datanucleus.store.rdbms.sql.expression.InExpression;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;
import org.datanucleus.store.rdbms.table.CollectionTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class CollectionContainsMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() != 0 && args.size() <= 1) {
         CollectionExpression collExpr = (CollectionExpression)expr;
         AbstractMemberMetaData mmd = collExpr.getJavaTypeMapping().getMemberMetaData();
         SQLExpression elemExpr = (SQLExpression)args.get(0);
         if (elemExpr.isParameter() && mmd != null && mmd.getCollection() != null) {
            Class elementCls = this.stmt.getQueryGenerator().getClassLoaderResolver().classForName(mmd.getCollection().getElementType());
            this.stmt.getQueryGenerator().bindParameter(elemExpr.getParameterName(), elementCls);
         }

         if (collExpr instanceof CollectionLiteral) {
            CollectionLiteral lit = (CollectionLiteral)collExpr;
            Collection coll = (Collection)lit.getValue();
            JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
            if (coll != null && !coll.isEmpty()) {
               if (collExpr.isParameter()) {
                  this.stmt.getQueryGenerator().useParameterExpressionAsLiteral((CollectionLiteral)collExpr);
               }

               boolean useInExpression = false;
               List<SQLExpression> collElementExprs = lit.getElementExpressions();
               if (collElementExprs != null && !collElementExprs.isEmpty()) {
                  boolean incompatible = true;
                  Class elemtype = this.clr.classForName(elemExpr.getJavaTypeMapping().getType());

                  for(SQLExpression collElementExpr : collElementExprs) {
                     Class collElemType = this.clr.classForName(collElementExpr.getJavaTypeMapping().getType());
                     if (this.elementTypeCompatible(elemtype, collElemType)) {
                        incompatible = false;
                        break;
                     }
                  }

                  if (incompatible) {
                     return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, false));
                  }

                  SQLExpression collElementExpr = (SQLExpression)collElementExprs.get(0);
                  if (collElementExpr instanceof StringExpression || collElementExpr instanceof NumericExpression || collElementExpr instanceof TemporalExpression || collElementExpr instanceof CharacterExpression || collElementExpr instanceof ByteExpression || collElementExpr instanceof EnumExpression) {
                     useInExpression = true;
                  }
               }

               if (useInExpression) {
                  SQLExpression[] exprs = collElementExprs != null ? (SQLExpression[])collElementExprs.toArray(new SQLExpression[collElementExprs.size()]) : null;
                  return new InExpression(elemExpr, exprs);
               } else {
                  BooleanExpression bExpr = null;
                  if (collElementExprs != null) {
                     for(int i = 0; i < collElementExprs.size(); ++i) {
                        if (bExpr == null) {
                           bExpr = ((SQLExpression)collElementExprs.get(i)).eq(elemExpr);
                        } else {
                           bExpr = bExpr.ior(((SQLExpression)collElementExprs.get(i)).eq(elemExpr));
                        }
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
         } else if (mmd == null) {
            throw new NucleusUserException("Cannot perform Collection.contains when the field metadata is not provided");
         } else if (mmd.isSerialized()) {
            throw new NucleusUserException("Cannot perform Collection.contains when the collection is being serialised");
         } else {
            ApiAdapter api = this.stmt.getRDBMSManager().getApiAdapter();
            Class elementType = this.clr.classForName(mmd.getCollection().getElementType());
            if (!api.isPersistable(elementType) && mmd.getJoinMetaData() == null) {
               throw new NucleusUserException("Cannot perform Collection.contains when the collection<Non-Persistable> is not in a join table");
            } else if (this.stmt.getQueryGenerator().getCompilationComponent() != CompilationComponent.FILTER) {
               return this.containsAsSubquery(collExpr, elemExpr);
            } else {
               boolean useSubquery = this.getNeedsSubquery(collExpr, elemExpr);
               if (elemExpr instanceof UnboundExpression) {
                  String varName = ((UnboundExpression)elemExpr).getVariableName();
                  String extensionName = "datanucleus.query.jdoql." + varName + ".join";
                  String extensionValue = (String)this.stmt.getQueryGenerator().getValueForExtension(extensionName);
                  if (extensionValue != null && extensionValue.equalsIgnoreCase("SUBQUERY")) {
                     useSubquery = true;
                  } else if (extensionValue != null && extensionValue.equalsIgnoreCase("INNERJOIN")) {
                     useSubquery = false;
                  }
               }

               return useSubquery ? this.containsAsSubquery(collExpr, elemExpr) : this.containsAsInnerJoin(collExpr, elemExpr);
            }
         }
      } else {
         throw new NucleusException(Localiser.msg("060016", new Object[]{"contains", "CollectionExpression", 1}));
      }
   }

   protected boolean getNeedsSubquery(SQLExpression collExpr, SQLExpression elemExpr) {
      if (elemExpr instanceof UnboundExpression) {
         NucleusLogger.QUERY.debug(">> collection.contains collExpr=" + collExpr + " elemExpr=" + elemExpr + " elem.variable=" + ((UnboundExpression)elemExpr).getVariableName() + " need to implement check on whether there is a !coll or an OR using just this variable");
      }

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

   protected SQLExpression containsAsInnerJoin(CollectionExpression collExpr, SQLExpression elemExpr) {
      boolean elemIsUnbound = elemExpr instanceof UnboundExpression;
      String varName = null;
      String elemAlias = null;
      String elemType = null;
      if (elemIsUnbound) {
         varName = ((UnboundExpression)elemExpr).getVariableName();
         NucleusLogger.QUERY.debug("collection.contains(" + elemExpr + ") binding unbound variable " + varName + " using INNER JOIN");
      } else if (!this.stmt.getQueryGenerator().hasExplicitJoins()) {
         SQLJoin.JoinType joinType = this.stmt.getJoinTypeForTable(elemExpr.getSQLTable());
         if (joinType == SQLJoin.JoinType.CROSS_JOIN) {
            elemAlias = this.stmt.removeCrossJoin(elemExpr.getSQLTable());
            elemIsUnbound = true;
            elemType = elemExpr.getJavaTypeMapping().getType();
            NucleusLogger.QUERY.debug("collection.contains(" + elemExpr + ") was previously bound as CROSS JOIN but changing to INNER JOIN");
         }
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      AbstractMemberMetaData mmd = collExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData elemCmd = mmd.getCollection().getElementClassMetaData(this.clr, storeMgr.getMetaDataManager());
      CollectionTable joinTbl = (CollectionTable)storeMgr.getTable(mmd);
      if (elemIsUnbound) {
         Class varType = this.stmt.getQueryGenerator().getTypeOfVariable(varName);
         if (varType != null) {
            elemType = varType.getName();
            elemCmd = storeMgr.getMetaDataManager().getMetaDataForClass(elemType, this.clr);
         }
      }

      if (elemType == null) {
         elemType = mmd.getCollection().getElementType();
      }

      if (joinTbl != null) {
         if (elemCmd == null) {
            SQLTable joinSqlTbl = this.stmt.innerJoin(collExpr.getSQLTable(), collExpr.getSQLTable().getTable().getIdMapping(), joinTbl, elemAlias, joinTbl.getOwnerMapping(), (Object[])null, (String)null);
            SQLExpression elemIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getElementMapping());
            if (elemIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
            } else {
               this.addRestrictionOnElement(this.stmt, elemIdExpr, elemExpr);
            }
         } else {
            SQLTable joinSqlTbl = this.stmt.innerJoin(collExpr.getSQLTable(), collExpr.getSQLTable().getTable().getIdMapping(), joinTbl, (String)null, joinTbl.getOwnerMapping(), (Object[])null, (String)null);
            if (!mmd.getCollection().isEmbeddedElement()) {
               DatastoreClass elemTbl = storeMgr.getDatastoreClass(elemType, this.clr);
               SQLTable elemSqlTbl = null;
               if (joinTbl.getElementMapping() instanceof ReferenceMapping && ((ReferenceMapping)joinTbl.getElementMapping()).getMappingStrategy() == 0) {
                  JavaTypeMapping elemMapping = null;
                  JavaTypeMapping[] elemImplMappings = ((ReferenceMapping)joinTbl.getElementMapping()).getJavaTypeMapping();

                  for(int i = 0; i < elemImplMappings.length; ++i) {
                     if (elemImplMappings[i].getType().equals(elemCmd.getFullClassName())) {
                        elemMapping = elemImplMappings[i];
                        break;
                     }
                  }

                  elemSqlTbl = this.stmt.innerJoin(joinSqlTbl, elemMapping, joinTbl.getElementMapping(), elemTbl, elemAlias, elemTbl.getIdMapping(), (JavaTypeMapping)null, (Object[])null, (String)null);
               } else {
                  elemSqlTbl = this.stmt.innerJoin(joinSqlTbl, joinTbl.getElementMapping(), elemTbl, elemAlias, elemTbl.getIdMapping(), (Object[])null, (String)null);
               }

               SQLExpression elemIdExpr = this.exprFactory.newExpression(this.stmt, elemSqlTbl, elemTbl.getIdMapping());
               if (elemIsUnbound) {
                  this.stmt.getQueryGenerator().bindVariable(varName, elemCmd, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
               } else {
                  this.addRestrictionOnElement(this.stmt, elemIdExpr, elemExpr);
               }
            } else {
               SQLExpression elemIdExpr = this.exprFactory.newExpression(this.stmt, joinSqlTbl, joinTbl.getElementMapping());
               if (elemIsUnbound) {
                  this.stmt.getQueryGenerator().bindVariable(varName, elemCmd, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
               } else {
                  this.addRestrictionOnElement(this.stmt, elemIdExpr, elemExpr);
               }
            }
         }
      } else {
         DatastoreClass elemTbl = storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = elemTbl.getMemberMapping(mmd.getRelatedMemberMetaData(this.clr)[0]);
         } else {
            ownerMapping = elemTbl.getExternalMapping(mmd, 5);
         }

         SQLTable elemSqlTbl = this.stmt.innerJoin(collExpr.getSQLTable(), collExpr.getSQLTable().getTable().getIdMapping(), elemTbl, elemAlias, ownerMapping, (Object[])null, (String)null);
         if (elemIsUnbound) {
            SQLExpression elemIdExpr = null;
            if (!elemType.equals(mmd.getCollection().getElementType())) {
               DatastoreClass varTbl = storeMgr.getDatastoreClass(elemType, this.clr);
               SQLTable varSqlTbl = this.stmt.innerJoin(elemSqlTbl, elemTbl.getIdMapping(), varTbl, (String)null, varTbl.getIdMapping(), (Object[])null, (String)null);
               elemIdExpr = this.exprFactory.newExpression(this.stmt, varSqlTbl, varTbl.getIdMapping());
            } else {
               elemIdExpr = this.exprFactory.newExpression(this.stmt, elemSqlTbl, elemTbl.getIdMapping());
            }

            this.stmt.getQueryGenerator().bindVariable(varName, elemCmd, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
         } else {
            SQLExpression elemIdExpr = this.exprFactory.newExpression(this.stmt, elemSqlTbl, elemTbl.getIdMapping());
            this.addRestrictionOnElement(this.stmt, elemIdExpr, elemExpr);
         }
      }

      JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
      return this.exprFactory.newLiteral(this.stmt, m, true).eq(this.exprFactory.newLiteral(this.stmt, m, true));
   }

   protected SQLExpression containsAsSubquery(CollectionExpression collExpr, SQLExpression elemExpr) {
      boolean elemIsUnbound = elemExpr instanceof UnboundExpression;
      String varName = null;
      if (elemIsUnbound) {
         varName = ((UnboundExpression)elemExpr).getVariableName();
         NucleusLogger.QUERY.debug("collection.contains(" + elemExpr + ") binding unbound variable " + varName + " using SUBQUERY");
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      AbstractMemberMetaData mmd = collExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData elemCmd = mmd.getCollection().getElementClassMetaData(this.clr, storeMgr.getMetaDataManager());
      CollectionTable joinTbl = (CollectionTable)storeMgr.getTable(mmd);
      String elemType = mmd.getCollection().getElementType();
      if (elemIsUnbound) {
         Class varType = this.stmt.getQueryGenerator().getTypeOfVariable(varName);
         if (varType != null) {
            elemType = varType.getName();
            elemCmd = storeMgr.getMetaDataManager().getMetaDataForClass(elemType, this.clr);
         }
      }

      SQLStatement subStmt = null;
      if (joinTbl != null) {
         if (elemCmd == null) {
            subStmt = new SQLStatement(this.stmt, storeMgr, joinTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, collExpr.getSQLTable(), collExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getElementMapping());
            if (elemIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
            } else {
               this.addRestrictionOnElement(subStmt, elemIdExpr, elemExpr);
            }
         } else {
            DatastoreClass elemTbl = storeMgr.getDatastoreClass(elemType, this.clr);
            subStmt = new SQLStatement(this.stmt, storeMgr, elemTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLTable joinSqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), elemTbl.getIdMapping(), joinTbl, (String)null, joinTbl.getElementMapping(), (Object[])null, (String)null);
            JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, joinSqlTbl, ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, collExpr.getSQLTable(), collExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), elemTbl.getIdMapping());
            if (elemIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(varName, elemCmd, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
            } else {
               this.addRestrictionOnElement(subStmt, elemIdExpr, elemExpr);
            }
         }
      } else {
         DatastoreClass elemTbl = storeMgr.getDatastoreClass(mmd.getCollection().getElementType(), this.clr);
         subStmt = new SQLStatement(this.stmt, storeMgr, elemTbl, (DatastoreIdentifier)null, (String)null);
         subStmt.setClassLoaderResolver(this.clr);
         JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
         subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
         JavaTypeMapping ownerMapping = null;
         if (mmd.getMappedBy() != null) {
            ownerMapping = elemTbl.getMemberMapping(mmd.getRelatedMemberMetaData(this.clr)[0]);
         } else {
            ownerMapping = elemTbl.getExternalMapping(mmd, 5);
         }

         SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
         SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, collExpr.getSQLTable(), collExpr.getSQLTable().getTable().getIdMapping());
         subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
         if (elemIsUnbound) {
            SQLExpression elemIdExpr = null;
            if (!elemType.equals(mmd.getCollection().getElementType())) {
               DatastoreClass varTbl = storeMgr.getDatastoreClass(elemType, this.clr);
               SQLTable varSqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), elemTbl.getIdMapping(), varTbl, (String)null, varTbl.getIdMapping(), (Object[])null, (String)null);
               elemIdExpr = this.exprFactory.newExpression(subStmt, varSqlTbl, varTbl.getIdMapping());
            } else {
               elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), elemTbl.getIdMapping());
            }

            this.stmt.getQueryGenerator().bindVariable(varName, elemCmd, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
         } else {
            SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), elemTbl.getIdMapping());
            this.addRestrictionOnElement(subStmt, elemIdExpr, elemExpr);
         }
      }

      return new BooleanSubqueryExpression(this.stmt, "EXISTS", subStmt);
   }

   protected void addRestrictionOnElement(SQLStatement stmt, SQLExpression elemIdExpr, SQLExpression elemExpr) {
      try {
         stmt.whereAnd(elemIdExpr.eq(elemExpr), true);
      } catch (IllegalExpressionOperationException ieoe) {
         NucleusLogger.QUERY.warn("Collection.contains element expression supplied is inconsistent with element type of this collection : " + ieoe.getMessage());
         JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
         BooleanExpression notContainedExpr = this.exprFactory.newLiteral(stmt, m, false).eq(this.exprFactory.newLiteral(stmt, m, true));
         stmt.whereAnd(notContainedExpr, true);
      }

   }

   protected boolean elementTypeCompatible(Class elementType, Class collectionElementType) {
      if (!elementType.isPrimitive() && collectionElementType.isPrimitive() && !collectionElementType.isAssignableFrom(elementType) && !elementType.isAssignableFrom(collectionElementType)) {
         return false;
      } else if (elementType.isPrimitive()) {
         if (elementType == Boolean.TYPE && collectionElementType == Boolean.class) {
            return true;
         } else if (elementType == Byte.TYPE && collectionElementType == Byte.class) {
            return true;
         } else if (elementType == Character.TYPE && collectionElementType == Character.class) {
            return true;
         } else if (elementType == Double.TYPE && collectionElementType == Double.class) {
            return true;
         } else if (elementType == Float.TYPE && collectionElementType == Float.class) {
            return true;
         } else if (elementType == Integer.TYPE && collectionElementType == Integer.class) {
            return true;
         } else if (elementType == Long.TYPE && collectionElementType == Long.class) {
            return true;
         } else {
            return elementType == Short.TYPE && collectionElementType == Short.class;
         }
      } else {
         return true;
      }
   }
}
