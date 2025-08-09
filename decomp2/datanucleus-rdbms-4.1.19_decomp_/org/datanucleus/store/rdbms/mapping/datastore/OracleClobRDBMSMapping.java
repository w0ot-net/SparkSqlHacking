package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import oracle.sql.CLOB;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.exceptions.ColumnDefinitionException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.util.Localiser;

public class OracleClobRDBMSMapping extends ClobRDBMSMapping {
   public OracleClobRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(mapping, storeMgr, col);
      this.column = col;
      this.initialize();
   }

   private void initialize() {
      this.initTypeInfo();
      if (this.column != null && !this.column.isUnlimitedLength()) {
         throw new ColumnDefinitionException("Invalid length specified for CLOB column " + this.column + ", must be 'unlimited'");
      }
   }

   public String getInsertionInputParameter() {
      return "EMPTY_CLOB()";
   }

   public boolean includeInFetchStatement() {
      return true;
   }

   public String getUpdateInputParameter() {
      return "EMPTY_CLOB()";
   }

   public boolean insertValuesOnInsert() {
      return false;
   }

   public String getString(ResultSet rs, int param) {
      String value = null;

      try {
         char[] cbuf = null;
         Clob clob = rs.getClob(param);
         if (clob != null) {
            StringBuilder sbuf = new StringBuilder();
            Reader reader = clob.getCharacterStream();

            try {
               int BUFF_SIZE = 4096;
               cbuf = new char[4096];

               for(int charsRead = reader.read(cbuf); -1 != charsRead; charsRead = reader.read(cbuf)) {
                  sbuf.append(cbuf, 0, charsRead);
                  Arrays.fill(cbuf, '\u0000');
               }
            } catch (IOException e) {
               throw new NucleusDataStoreException("Error reading Oracle CLOB object: param = " + param, e);
            } finally {
               try {
                  reader.close();
               } catch (IOException e) {
                  throw new NucleusDataStoreException("Error reading Oracle CLOB object: param = " + param, e);
               }
            }

            value = sbuf.toString();
            if (value.length() == 0) {
               value = null;
            } else if (value.equals(this.getDatastoreAdapter().getSurrogateForEmptyStrings())) {
               value = "";
            }
         }

         return value;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("055001", new Object[]{"String", "" + param}), e);
      }
   }

   public Object getObject(ResultSet rs, int param) {
      return this.getString(rs, param);
   }

   public static void updateClobColumn(ObjectProvider op, Table table, DatastoreMapping mapping, String value) {
      ExecutionContext ec = op.getExecutionContext();
      RDBMSStoreManager storeMgr = table.getStoreManager();
      DatastoreClass classTable = (DatastoreClass)table;
      SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
      SQLStatement sqlStmt = new SQLStatement(storeMgr, table, (DatastoreIdentifier)null, (String)null);
      sqlStmt.setClassLoaderResolver(ec.getClassLoaderResolver());
      sqlStmt.addExtension("lock-for-update", true);
      SQLTable blobSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), mapping.getJavaTypeMapping());
      sqlStmt.select(blobSqlTbl, (Column)mapping.getColumn(), (String)null);
      StatementClassMapping mappingDefinition = new StatementClassMapping();
      AbstractClassMetaData cmd = op.getClassMetaData();
      int inputParamNum = 1;
      if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         JavaTypeMapping datastoreIdMapping = classTable.getDatastoreIdMapping();
         SQLExpression expr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), datastoreIdMapping);
         SQLExpression val = exprFactory.newLiteralParameter(sqlStmt, datastoreIdMapping, (Object)null, "ID");
         sqlStmt.whereAnd(expr.eq(val), true);
         StatementMappingIndex datastoreIdx = mappingDefinition.getMappingForMemberPosition(-1);
         if (datastoreIdx == null) {
            datastoreIdx = new StatementMappingIndex(datastoreIdMapping);
            mappingDefinition.addMappingForMember(-1, datastoreIdx);
         }

         datastoreIdx.addParameterOccurrence(new int[]{inputParamNum});
      } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         int[] pkNums = cmd.getPKMemberPositions();

         for(int i = 0; i < pkNums.length; ++i) {
            AbstractMemberMetaData mmd = cmd.getMetaDataForManagedMemberAtAbsolutePosition(pkNums[i]);
            JavaTypeMapping pkMapping = classTable.getMemberMapping(mmd);
            SQLExpression expr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), pkMapping);
            SQLExpression val = exprFactory.newLiteralParameter(sqlStmt, pkMapping, (Object)null, "PK" + i);
            sqlStmt.whereAnd(expr.eq(val), true);
            StatementMappingIndex pkIdx = mappingDefinition.getMappingForMemberPosition(pkNums[i]);
            if (pkIdx == null) {
               pkIdx = new StatementMappingIndex(pkMapping);
               mappingDefinition.addMappingForMember(pkNums[i], pkIdx);
            }

            int[] inputParams = new int[pkMapping.getNumberOfDatastoreMappings()];

            for(int j = 0; j < pkMapping.getNumberOfDatastoreMappings(); ++j) {
               inputParams[j] = inputParamNum++;
            }

            pkIdx.addParameterOccurrence(inputParams);
         }
      }

      String textStmt = sqlStmt.getSelectStatement().toSQL();
      if (op.isEmbedded()) {
         ObjectProvider[] embeddedOwners = ec.getOwnersForEmbeddedObjectProvider(op);
         if (embeddedOwners != null) {
            op = embeddedOwners[0];
         }
      }

      try {
         ManagedConnection mconn = storeMgr.getConnection(ec);
         SQLController sqlControl = storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, textStmt);

            try {
               if (cmd.getIdentityType() == IdentityType.DATASTORE) {
                  StatementMappingIndex datastoreIdx = mappingDefinition.getMappingForMemberPosition(-1);

                  for(int i = 0; i < datastoreIdx.getNumberOfParameterOccurrences(); ++i) {
                     classTable.getDatastoreIdMapping().setObject(ec, ps, datastoreIdx.getParameterPositionsForOccurrence(i), op.getInternalObjectId());
                  }
               } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                  op.provideFields(cmd.getPKMemberPositions(), storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition));
               }

               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, textStmt, ps);

               try {
                  if (!rs.next()) {
                     throw new NucleusObjectNotFoundException("No such database row", op.getInternalObjectId());
                  }

                  DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
                  int jdbcMajorVersion = dba.getDriverMajorVersion();
                  if (dba.getDatastoreDriverName().equalsIgnoreCase("Oracle JDBC driver") && jdbcMajorVersion < 10) {
                     CLOB clob = (CLOB)rs.getClob(1);
                     if (clob != null) {
                        clob.putString(1L, value);
                     }
                  } else {
                     Clob clob = rs.getClob(1);
                     if (clob != null) {
                        clob.setString(1L, value);
                     }
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

      } catch (SQLException e) {
         throw new NucleusDataStoreException("Update of CLOB value failed: " + textStmt, e);
      }
   }
}
