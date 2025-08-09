package org.datanucleus.store.rdbms.sql.method;

import java.lang.reflect.Array;
import java.util.List;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.ArrayExpression;
import org.datanucleus.store.rdbms.sql.expression.ArrayLiteral;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.BooleanSubqueryExpression;
import org.datanucleus.store.rdbms.sql.expression.ByteExpression;
import org.datanucleus.store.rdbms.sql.expression.CharacterExpression;
import org.datanucleus.store.rdbms.sql.expression.EnumExpression;
import org.datanucleus.store.rdbms.sql.expression.InExpression;
import org.datanucleus.store.rdbms.sql.expression.NumericExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.StringExpression;
import org.datanucleus.store.rdbms.sql.expression.TemporalExpression;
import org.datanucleus.store.rdbms.sql.expression.UnboundExpression;
import org.datanucleus.store.rdbms.table.ArrayTable;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.util.NucleusLogger;

public class ArrayContainsMethod extends AbstractSQLMethod {
   public SQLExpression getExpression(SQLExpression expr, List args) {
      if (args != null && args.size() == 1) {
         ArrayExpression arrExpr = (ArrayExpression)expr;
         SQLExpression elemExpr = (SQLExpression)args.get(0);
         if (elemExpr.isParameter()) {
            AbstractMemberMetaData mmd = arrExpr.getJavaTypeMapping().getMemberMetaData();
            if (mmd != null) {
               this.stmt.getQueryGenerator().bindParameter(elemExpr.getParameterName(), mmd.getType().getComponentType());
            }
         }

         if (expr instanceof ArrayLiteral) {
            if (elemExpr instanceof UnboundExpression) {
               Class elemCls = this.clr.classForName(arrExpr.getJavaTypeMapping().getType()).getComponentType();
               elemExpr = this.stmt.getQueryGenerator().bindVariable((UnboundExpression)elemExpr, elemCls);
            }

            ArrayLiteral lit = (ArrayLiteral)expr;
            Object array = lit.getValue();
            JavaTypeMapping m = this.exprFactory.getMappingForType(Boolean.TYPE, true);
            if (array != null && Array.getLength(array) != 0) {
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
                  List<SQLExpression> elementExprs = lit.getElementExpressions();

                  for(int i = 0; i < elementExprs.size(); ++i) {
                     SQLExpression arrElemExpr = (SQLExpression)elementExprs.get(i);
                     if (bExpr == null) {
                        bExpr = arrElemExpr.eq(elemExpr);
                     } else {
                        bExpr = bExpr.ior(arrElemExpr.eq(elemExpr));
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
         } else if (arrExpr.getElementExpressions() != null) {
            if (elemExpr instanceof UnboundExpression) {
               Class elemCls = this.clr.classForName(arrExpr.getJavaTypeMapping().getType()).getComponentType();
               elemExpr = this.stmt.getQueryGenerator().bindVariable((UnboundExpression)elemExpr, elemCls);
            }

            BooleanExpression bExpr = null;
            List<SQLExpression> elementExprs = arrExpr.getElementExpressions();

            for(int i = 0; i < elementExprs.size(); ++i) {
               SQLExpression arrElemExpr = (SQLExpression)elementExprs.get(i);
               if (bExpr == null) {
                  bExpr = arrElemExpr.eq(elemExpr);
               } else {
                  bExpr = bExpr.ior(arrElemExpr.eq(elemExpr));
               }
            }

            if (bExpr != null) {
               bExpr.encloseInParentheses();
            }

            return bExpr;
         } else {
            return this.containsAsSubquery(arrExpr, elemExpr);
         }
      } else {
         throw new NucleusException("Incorrect arguments for Array.contains(SQLExpression)");
      }
   }

   protected SQLExpression containsAsSubquery(ArrayExpression arrExpr, SQLExpression elemExpr) {
      boolean elemIsUnbound = elemExpr instanceof UnboundExpression;
      String varName = null;
      if (elemIsUnbound) {
         varName = ((UnboundExpression)elemExpr).getVariableName();
         NucleusLogger.QUERY.debug(">> Array.contains binding unbound variable " + varName + " using SUBQUERY");
      }

      RDBMSStoreManager storeMgr = this.stmt.getRDBMSManager();
      AbstractMemberMetaData mmd = arrExpr.getJavaTypeMapping().getMemberMetaData();
      AbstractClassMetaData elemCmd = mmd.getArray().getElementClassMetaData(this.clr, storeMgr.getMetaDataManager());
      ArrayTable joinTbl = (ArrayTable)storeMgr.getTable(mmd);
      SQLStatement subStmt = null;
      if (joinTbl != null) {
         if (elemCmd == null) {
            subStmt = new SQLStatement(this.stmt, storeMgr, joinTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, arrExpr.getSQLTable(), arrExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), joinTbl.getElementMapping());
            if (elemIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(varName, (AbstractClassMetaData)null, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
            } else {
               subStmt.whereAnd(elemIdExpr.eq(elemExpr), true);
            }
         } else {
            DatastoreClass elemTbl = storeMgr.getDatastoreClass(mmd.getArray().getElementType(), this.clr);
            subStmt = new SQLStatement(this.stmt, storeMgr, elemTbl, (DatastoreIdentifier)null, (String)null);
            subStmt.setClassLoaderResolver(this.clr);
            JavaTypeMapping oneMapping = storeMgr.getMappingManager().getMapping(Integer.class);
            subStmt.select(this.exprFactory.newLiteral(subStmt, oneMapping, 1), (String)null);
            SQLTable joinSqlTbl = subStmt.innerJoin(subStmt.getPrimaryTable(), elemTbl.getIdMapping(), joinTbl, (String)null, joinTbl.getElementMapping(), (Object[])null, (String)null);
            JavaTypeMapping ownerMapping = ((JoinTable)joinTbl).getOwnerMapping();
            SQLExpression ownerExpr = this.exprFactory.newExpression(subStmt, joinSqlTbl, ownerMapping);
            SQLExpression ownerIdExpr = this.exprFactory.newExpression(this.stmt, arrExpr.getSQLTable(), arrExpr.getSQLTable().getTable().getIdMapping());
            subStmt.whereAnd(ownerExpr.eq(ownerIdExpr), true);
            SQLExpression elemIdExpr = this.exprFactory.newExpression(subStmt, subStmt.getPrimaryTable(), elemTbl.getIdMapping());
            if (elemIsUnbound) {
               this.stmt.getQueryGenerator().bindVariable(varName, elemCmd, elemIdExpr.getSQLTable(), elemIdExpr.getJavaTypeMapping());
            } else {
               subStmt.whereAnd(elemIdExpr.eq(elemExpr), true);
            }
         }

         return new BooleanSubqueryExpression(this.stmt, "EXISTS", subStmt);
      } else {
         throw new NucleusException("Dont support evaluation of ARRAY.contains when no join table is used");
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
