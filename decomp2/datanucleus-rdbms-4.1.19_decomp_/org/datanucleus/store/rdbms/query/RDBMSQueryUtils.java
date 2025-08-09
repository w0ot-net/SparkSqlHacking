package org.datanucleus.store.rdbms.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.query.QueryUtils;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.StatementGenerator;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.StringLiteral;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class RDBMSQueryUtils extends QueryUtils {
   public static String getClassNameFromDiscriminatorResultSetRow(JavaTypeMapping discrimMapping, DiscriminatorMetaData dismd, ResultSet rs, ExecutionContext ec) {
      String rowClassName = null;
      if (discrimMapping != null && dismd.getStrategy() != DiscriminatorStrategy.NONE) {
         try {
            String discriminatorColName = discrimMapping.getDatastoreMapping(0).getColumn().getIdentifier().getName();
            String discriminatorValue = rs.getString(discriminatorColName);
            rowClassName = ec.getMetaDataManager().getClassNameFromDiscriminatorValue(discriminatorValue, dismd);
         } catch (SQLException var7) {
         }
      }

      return rowClassName;
   }

   public static String getResultSetTypeForQuery(Query query) {
      String rsTypeString = query.getExecutionContext().getNucleusContext().getConfiguration().getStringProperty("datanucleus.rdbms.query.resultSetType");
      Object rsTypeExt = query.getExtension("datanucleus.rdbms.query.resultSetType");
      if (rsTypeExt != null) {
         rsTypeString = (String)rsTypeExt;
      }

      return rsTypeString;
   }

   public static String getResultSetConcurrencyForQuery(Query query) {
      String rsConcurrencyString = query.getExecutionContext().getNucleusContext().getConfiguration().getStringProperty("datanucleus.rdbms.query.resultSetConcurrency");
      Object rsConcurrencyExt = query.getExtension("datanucleus.rdbms.query.resultSetConcurrency");
      if (rsConcurrencyExt != null) {
         rsConcurrencyString = (String)rsConcurrencyExt;
      }

      return rsConcurrencyString;
   }

   public static boolean useUpdateLockForQuery(Query query) {
      if (query.getSerializeRead() != null) {
         return !query.getExecutionContext().getTransaction().isActive() ? false : query.getSerializeRead();
      } else {
         return query.getExecutionContext().getSerializeReadForClass(query.getCandidateClassName());
      }
   }

   public static PreparedStatement getPreparedStatementForQuery(ManagedConnection conn, String queryStmt, Query query) throws SQLException {
      String rsTypeString = getResultSetTypeForQuery(query);
      if (rsTypeString != null && !rsTypeString.equals("scroll-sensitive") && !rsTypeString.equals("forward-only") && !rsTypeString.equals("scroll-insensitive")) {
         throw new NucleusUserException(Localiser.msg("052510"));
      } else {
         if (rsTypeString != null) {
            DatastoreAdapter dba = ((RDBMSStoreManager)query.getStoreManager()).getDatastoreAdapter();
            if (rsTypeString.equals("scroll-sensitive") && !dba.supportsOption("ResultSetTypeScrollSens")) {
               NucleusLogger.DATASTORE_RETRIEVE.info("Query requested to run with result-set type of " + rsTypeString + " yet not supported by adapter. Using forward-only");
               rsTypeString = "forward-only";
            } else if (rsTypeString.equals("scroll-insensitive") && !dba.supportsOption("ResultSetTypeScrollInsens")) {
               NucleusLogger.DATASTORE_RETRIEVE.info("Query requested to run with result-set type of " + rsTypeString + " yet not supported by adapter. Using forward-only");
               rsTypeString = "forward-only";
            } else if (rsTypeString.equals("forward-only") && !dba.supportsOption("ResultSetTypeForwardOnly")) {
               NucleusLogger.DATASTORE_RETRIEVE.info("Query requested to run with result-set type of " + rsTypeString + " yet not supported by adapter. Using scroll-sensitive");
               rsTypeString = "scroll-sensitive";
            }
         }

         String rsConcurrencyString = getResultSetConcurrencyForQuery(query);
         if (rsConcurrencyString != null && !rsConcurrencyString.equals("read-only") && !rsConcurrencyString.equals("updateable")) {
            throw new NucleusUserException(Localiser.msg("052511"));
         } else {
            SQLController sqlControl = ((RDBMSStoreManager)query.getStoreManager()).getSQLController();
            PreparedStatement ps = sqlControl.getStatementForQuery(conn, queryStmt, rsTypeString, rsConcurrencyString);
            return ps;
         }
      }
   }

   public static void prepareStatementForExecution(PreparedStatement ps, Query query, boolean applyTimeout) throws SQLException {
      NucleusContext nucleusCtx = query.getExecutionContext().getNucleusContext();
      RDBMSStoreManager storeMgr = (RDBMSStoreManager)query.getStoreManager();
      Configuration conf = nucleusCtx.getConfiguration();
      if (applyTimeout) {
         Integer timeout = query.getDatastoreReadTimeoutMillis();
         if (timeout != null && timeout > 0) {
            ps.setQueryTimeout(timeout / 1000);
         }
      }

      int fetchSize = 0;
      if (query.getFetchPlan().getFetchSize() > 0) {
         fetchSize = query.getFetchPlan().getFetchSize();
      }

      if (storeMgr.getDatastoreAdapter().supportsQueryFetchSize(fetchSize)) {
         ps.setFetchSize(fetchSize);
      }

      String fetchDir = conf.getStringProperty("datanucleus.rdbms.query.fetchDirection");
      Object fetchDirExt = query.getExtension("datanucleus.rdbms.query.fetchDirection");
      if (fetchDirExt != null) {
         fetchDir = (String)fetchDirExt;
         if (!fetchDir.equals("forward") && !fetchDir.equals("reverse") && !fetchDir.equals("unknown")) {
            throw new NucleusUserException(Localiser.msg("052512"));
         }
      }

      if (fetchDir.equals("reverse")) {
         ps.setFetchDirection(1001);
      } else if (fetchDir.equals("unknown")) {
         ps.setFetchDirection(1002);
      }

      long toExclNo = query.getRangeToExcl();
      if (toExclNo != 0L && toExclNo != Long.MAX_VALUE) {
         if (toExclNo > 2147483647L) {
            ps.setMaxRows(Integer.MAX_VALUE);
         } else {
            ps.setMaxRows((int)toExclNo);
         }
      }

   }

   public static SQLStatement getStatementForCandidates(RDBMSStoreManager storeMgr, SQLStatement parentStmt, AbstractClassMetaData cmd, StatementClassMapping clsMapping, ExecutionContext ec, Class candidateCls, boolean subclasses, String result, String candidateAlias, String candidateTableGroupName) {
      SQLStatement stmt = null;
      DatastoreIdentifier candidateAliasId = null;
      if (candidateAlias != null) {
         candidateAliasId = storeMgr.getIdentifierFactory().newTableIdentifier(candidateAlias);
      }

      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      List<DatastoreClass> candidateTables = new ArrayList();
      if (cmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
         DatastoreClass candidateTable = storeMgr.getDatastoreClass(cmd.getFullClassName(), clr);
         if (candidateTable != null) {
            candidateTables.add(candidateTable);
         }

         if (subclasses) {
            Collection<String> subclassNames = storeMgr.getSubClassesForClass(cmd.getFullClassName(), subclasses, clr);
            if (subclassNames != null) {
               for(String subclassName : subclassNames) {
                  DatastoreClass tbl = storeMgr.getDatastoreClass(subclassName, clr);
                  if (tbl != null) {
                     candidateTables.add(tbl);
                  }
               }
            }
         }

         Iterator<DatastoreClass> iter = candidateTables.iterator();
         int maxClassNameLength = cmd.getFullClassName().length();

         while(iter.hasNext()) {
            DatastoreClass cls = (DatastoreClass)iter.next();
            String className = cls.getType();
            if (className.length() > maxClassNameLength) {
               maxClassNameLength = className.length();
            }
         }

         for(DatastoreClass cls : candidateTables) {
            SQLStatement tblStmt = new SQLStatement(parentStmt, storeMgr, cls, candidateAliasId, candidateTableGroupName);
            tblStmt.setClassLoaderResolver(clr);
            tblStmt.setCandidateClassName(cls.getType());
            JavaTypeMapping m = storeMgr.getMappingManager().getMapping(String.class);
            String nuctypeName = cls.getType();
            if (maxClassNameLength > nuctypeName.length()) {
               nuctypeName = StringUtils.leftAlignedPaddedString(nuctypeName, maxClassNameLength);
            }

            StringLiteral lit = new StringLiteral(tblStmt, m, nuctypeName, (String)null);
            tblStmt.select(lit, "NUCLEUS_TYPE");
            if (stmt == null) {
               stmt = tblStmt;
            } else {
               stmt.union(tblStmt);
            }
         }

         if (clsMapping != null) {
            clsMapping.setNucleusTypeColumnName("NUCLEUS_TYPE");
         }
      } else {
         List<Class> candidateClasses = new ArrayList();
         if (ClassUtils.isReferenceType(candidateCls)) {
            String[] clsNames = storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(candidateCls.getName(), clr);

            for(int i = 0; i < clsNames.length; ++i) {
               Class cls = clr.classForName(clsNames[i]);
               DatastoreClass table = storeMgr.getDatastoreClass(clsNames[i], clr);
               candidateClasses.add(cls);
               candidateTables.add(table);
               AbstractClassMetaData implCmd = storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(cls, clr);
               if (implCmd.getIdentityType() != cmd.getIdentityType()) {
                  throw new NucleusUserException("You are querying an interface (" + cmd.getFullClassName() + ") yet one of its implementations (" + implCmd.getFullClassName() + ")  uses a different identity type!");
               }

               if (cmd.getIdentityType() == IdentityType.APPLICATION && cmd.getPKMemberPositions().length != implCmd.getPKMemberPositions().length) {
                  throw new NucleusUserException("You are querying an interface (" + cmd.getFullClassName() + ") yet one of its implementations (" + implCmd.getFullClassName() + ")  has a different number of PK members!");
               }
            }
         } else {
            DatastoreClass candidateTable = storeMgr.getDatastoreClass(cmd.getFullClassName(), clr);
            if (candidateTable != null) {
               candidateClasses.add(candidateCls);
               candidateTables.add(candidateTable);
            } else {
               AbstractClassMetaData[] cmds = storeMgr.getClassesManagingTableForClass(cmd, clr);
               if (cmds == null || cmds.length <= 0) {
                  throw new UnsupportedOperationException("No tables for query of " + cmd.getFullClassName());
               }

               for(int i = 0; i < cmds.length; ++i) {
                  DatastoreClass table = storeMgr.getDatastoreClass(cmds[i].getFullClassName(), clr);
                  Class cls = clr.classForName(cmds[i].getFullClassName());
                  candidateClasses.add(cls);
                  candidateTables.add(table);
               }
            }
         }

         for(int i = 0; i < candidateTables.size(); ++i) {
            DatastoreClass tbl = (DatastoreClass)candidateTables.get(i);
            Class cls = (Class)candidateClasses.get(i);
            StatementGenerator stmtGen = null;
            if (tbl.getDiscriminatorMapping(true) == null && !QueryUtils.resultHasOnlyAggregates(result)) {
               stmtGen = new UnionStatementGenerator(storeMgr, clr, cls, subclasses, candidateAliasId, candidateTableGroupName);
               if (result == null) {
                  stmtGen.setOption("selectNucleusType");
                  clsMapping.setNucleusTypeColumnName("NUCLEUS_TYPE");
               }
            } else {
               stmtGen = new DiscriminatorStatementGenerator(storeMgr, clr, cls, subclasses, candidateAliasId, candidateTableGroupName);
               stmtGen.setOption("restrictDiscriminator");
            }

            stmtGen.setParentStatement(parentStmt);
            SQLStatement tblStmt = stmtGen.getStatement();
            if (stmt == null) {
               stmt = tblStmt;
            } else {
               stmt.union(tblStmt);
            }
         }
      }

      return stmt;
   }

   public static ResultObjectFactory getResultObjectFactoryForNoCandidateClass(RDBMSStoreManager storeMgr, ResultSet rs, Class resultClass) {
      Class requiredResultClass = resultClass;
      int numberOfColumns = 0;
      String[] resultFieldNames = null;

      try {
         ResultSetMetaData rsmd = rs.getMetaData();
         numberOfColumns = rsmd.getColumnCount();
         if (requiredResultClass == null) {
            if (numberOfColumns == 1) {
               requiredResultClass = Object.class;
            } else {
               requiredResultClass = Object[].class;
            }
         }

         resultFieldNames = new String[numberOfColumns];

         for(int i = 0; i < numberOfColumns; ++i) {
            String colName = rsmd.getColumnName(i + 1);
            String colLabel = rsmd.getColumnLabel(i + 1);
            if (StringUtils.isWhitespace(colLabel)) {
               resultFieldNames[i] = colName;
            } else {
               resultFieldNames[i] = colLabel;
            }
         }
      } catch (SQLException var10) {
      }

      return new ResultClassROF(storeMgr, requiredResultClass, resultFieldNames);
   }
}
