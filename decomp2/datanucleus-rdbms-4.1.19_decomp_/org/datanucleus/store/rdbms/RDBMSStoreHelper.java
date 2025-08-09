package org.datanucleus.store.rdbms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.InheritanceMetaData;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableIdMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.query.RDBMSQueryUtils;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.NucleusLogger;

public class RDBMSStoreHelper {
   public static String getClassNameForIdUsingDiscriminator(RDBMSStoreManager storeMgr, ExecutionContext ec, Object id, AbstractClassMetaData cmd) {
      if (cmd != null && id != null) {
         SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         DatastoreClass primaryTable = storeMgr.getDatastoreClass(cmd.getFullClassName(), clr);
         DiscriminatorStatementGenerator stmtGen = new DiscriminatorStatementGenerator(storeMgr, clr, clr.classForName(cmd.getFullClassName()), true, (DatastoreIdentifier)null, (String)null);
         stmtGen.setOption("restrictDiscriminator");
         SQLStatement sqlStmt = stmtGen.getStatement();
         JavaTypeMapping discrimMapping = primaryTable.getDiscriminatorMapping(true);
         SQLTable discrimSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), discrimMapping);
         sqlStmt.select(discrimSqlTbl, (JavaTypeMapping)discrimMapping, (String)null);
         JavaTypeMapping idMapping = primaryTable.getIdMapping();
         JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
         SQLExpression sqlFldExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), idMapping);
         SQLExpression sqlFldVal = exprFactory.newLiteralParameter(sqlStmt, idParamMapping, id, "ID");
         sqlStmt.whereAnd(sqlFldExpr.eq(sqlFldVal), true);

         try {
            ManagedConnection mconn = storeMgr.getConnection(ec);
            SQLController sqlControl = storeMgr.getSQLController();
            if (ec.getSerializeReadForClass(cmd.getFullClassName())) {
               sqlStmt.addExtension("lock-for-update", true);
            }

            String var21;
            try {
               PreparedStatement ps = SQLStatementHelper.getPreparedStatementForSQLStatement(sqlStmt, ec, mconn, (String)null, (String)null);
               String statement = sqlStmt.getSelectStatement().toSQL();

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, statement, ps);

                  try {
                     if (rs == null || !rs.next()) {
                        return null;
                     }

                     DiscriminatorMetaData dismd = discrimMapping.getTable().getDiscriminatorMetaData();
                     var21 = RDBMSQueryUtils.getClassNameFromDiscriminatorResultSetRow(discrimMapping, dismd, rs, ec);
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return var21;
         } catch (SQLException sqe) {
            NucleusLogger.DATASTORE.error("Exception thrown on querying of discriminator for id", sqe);
            throw new NucleusDataStoreException(sqe.toString(), sqe);
         }
      } else {
         return null;
      }
   }

   public static String getClassNameForIdUsingUnion(RDBMSStoreManager storeMgr, ExecutionContext ec, Object id, List rootCmds) {
      if (rootCmds != null && !rootCmds.isEmpty() && id != null) {
         SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         Iterator<AbstractClassMetaData> rootCmdIter = rootCmds.iterator();
         AbstractClassMetaData sampleCmd = null;
         SQLStatement sqlStmtMain = null;

         JavaTypeMapping idParamMapping;
         while(rootCmdIter.hasNext()) {
            AbstractClassMetaData rootCmd = (AbstractClassMetaData)rootCmdIter.next();
            DatastoreClass rootTbl = storeMgr.getDatastoreClass(rootCmd.getFullClassName(), clr);
            InheritanceMetaData rootInhmd = rootCmd.getBaseAbstractClassMetaData().getInheritanceMetaData();
            if (rootInhmd.getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
               if (rootTbl != null) {
                  UnionStatementGenerator stmtGen = new UnionStatementGenerator(storeMgr, clr, clr.classForName(rootCmd.getFullClassName()), false, (DatastoreIdentifier)null, (String)null);
                  stmtGen.setOption("selectNucleusType");
                  if (sqlStmtMain == null) {
                     sampleCmd = rootCmd;
                     sqlStmtMain = stmtGen.getStatement();
                     JavaTypeMapping idMapping = sqlStmtMain.getPrimaryTable().getTable().getIdMapping();
                     idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
                     SQLExpression fieldExpr = exprFactory.newExpression(sqlStmtMain, sqlStmtMain.getPrimaryTable(), idMapping);
                     SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmtMain, idParamMapping, id, "ID");
                     sqlStmtMain.whereAnd(fieldExpr.eq(fieldVal), true);
                  } else {
                     SQLStatement sqlStmt = stmtGen.getStatement();
                     idParamMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
                     JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idParamMapping);
                     SQLExpression fieldExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), idParamMapping);
                     SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmt, idParamMapping, id, "ID");
                     sqlStmt.whereAnd(fieldExpr.eq(fieldVal), true);
                     sqlStmtMain.union(sqlStmt);
                  }
               }

               for(idParamMapping : storeMgr.getSubClassesForClass(rootCmd.getFullClassName(), true, clr)) {
                  AbstractClassMetaData rootSubclassCmd = storeMgr.getMetaDataManager().getMetaDataForClass(idParamMapping, clr);
                  DatastoreClass rootSubclassTbl = storeMgr.getDatastoreClass(rootSubclassCmd.getFullClassName(), clr);
                  if (rootSubclassTbl != null) {
                     UnionStatementGenerator stmtGen = new UnionStatementGenerator(storeMgr, clr, clr.classForName(rootSubclassCmd.getFullClassName()), false, (DatastoreIdentifier)null, (String)null);
                     stmtGen.setOption("selectNucleusType");
                     if (sqlStmtMain == null) {
                        sampleCmd = rootSubclassCmd;
                        sqlStmtMain = stmtGen.getStatement();
                        JavaTypeMapping idMapping = sqlStmtMain.getPrimaryTable().getTable().getIdMapping();
                        JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
                        SQLExpression fieldExpr = exprFactory.newExpression(sqlStmtMain, sqlStmtMain.getPrimaryTable(), idMapping);
                        SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmtMain, idParamMapping, id, "ID");
                        sqlStmtMain.whereAnd(fieldExpr.eq(fieldVal), true);
                     } else {
                        SQLStatement sqlStmt = stmtGen.getStatement();
                        JavaTypeMapping idMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
                        JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
                        SQLExpression fieldExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), idMapping);
                        SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmt, idParamMapping, id, "ID");
                        sqlStmt.whereAnd(fieldExpr.eq(fieldVal), true);
                        sqlStmtMain.union(sqlStmt);
                     }
                  }
               }
            } else if (rootTbl == null) {
               AbstractClassMetaData[] subcmds = storeMgr.getClassesManagingTableForClass(rootCmd, clr);
               if (subcmds != null && subcmds.length != 0) {
                  for(int i = 0; i < subcmds.length; ++i) {
                     idParamMapping = new UnionStatementGenerator(storeMgr, clr, clr.classForName(subcmds[i].getFullClassName()), true, (DatastoreIdentifier)null, (String)null);
                     ((UnionStatementGenerator)idParamMapping).setOption("selectNucleusType");
                     if (sqlStmtMain == null) {
                        sampleCmd = subcmds[i];
                        sqlStmtMain = ((UnionStatementGenerator)idParamMapping).getStatement();
                        JavaTypeMapping idMapping = sqlStmtMain.getPrimaryTable().getTable().getIdMapping();
                        JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
                        SQLExpression fieldExpr = exprFactory.newExpression(sqlStmtMain, sqlStmtMain.getPrimaryTable(), idMapping);
                        SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmtMain, idParamMapping, id, "ID");
                        sqlStmtMain.whereAnd(fieldExpr.eq(fieldVal), true);
                     } else {
                        SQLStatement sqlStmt = ((UnionStatementGenerator)idParamMapping).getStatement();
                        JavaTypeMapping idMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
                        JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
                        SQLExpression fieldExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), idMapping);
                        SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmt, idParamMapping, id, "ID");
                        sqlStmt.whereAnd(fieldExpr.eq(fieldVal), true);
                        sqlStmtMain.union(sqlStmt);
                     }
                  }
               }
            } else {
               UnionStatementGenerator stmtGen = new UnionStatementGenerator(storeMgr, clr, clr.classForName(rootCmd.getFullClassName()), true, (DatastoreIdentifier)null, (String)null);
               stmtGen.setOption("selectNucleusType");
               if (sqlStmtMain == null) {
                  sampleCmd = rootCmd;
                  sqlStmtMain = stmtGen.getStatement();
                  JavaTypeMapping idMapping = sqlStmtMain.getPrimaryTable().getTable().getIdMapping();
                  idParamMapping = new PersistableIdMapping((PersistableMapping)idMapping);
                  SQLExpression fieldExpr = exprFactory.newExpression(sqlStmtMain, sqlStmtMain.getPrimaryTable(), idMapping);
                  SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmtMain, idParamMapping, id, "ID");
                  sqlStmtMain.whereAnd(fieldExpr.eq(fieldVal), true);
               } else {
                  SQLStatement sqlStmt = stmtGen.getStatement();
                  idParamMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
                  JavaTypeMapping idParamMapping = new PersistableIdMapping((PersistableMapping)idParamMapping);
                  SQLExpression fieldExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), idParamMapping);
                  SQLExpression fieldVal = exprFactory.newLiteralParameter(sqlStmt, idParamMapping, id, "ID");
                  sqlStmt.whereAnd(fieldExpr.eq(fieldVal), true);
                  sqlStmtMain.union(sqlStmt);
               }
            }
         }

         try {
            ManagedConnection mconn = storeMgr.getConnection(ec);
            SQLController sqlControl = storeMgr.getSQLController();
            if (ec.getSerializeReadForClass(sampleCmd.getFullClassName())) {
               sqlStmtMain.addExtension("lock-for-update", true);
            }

            try {
               PreparedStatement ps = SQLStatementHelper.getPreparedStatementForSQLStatement(sqlStmtMain, ec, mconn, (String)null, (String)null);
               String statement = sqlStmtMain.getSelectStatement().toSQL();

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, statement, ps);

                  try {
                     if (rs == null) {
                        return null;
                     } else {
                        while(rs.next()) {
                           try {
                              idParamMapping = rs.getString("NUCLEUS_TYPE").trim();
                              return idParamMapping;
                           } catch (SQLException var41) {
                              idParamMapping = var41;
                           }
                        }

                        return null;
                     }
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }
         } catch (SQLException sqe) {
            NucleusLogger.DATASTORE.error("Exception with UNION statement", sqe);
            throw new NucleusDataStoreException(sqe.toString());
         }
      } else {
         return null;
      }
   }
}
