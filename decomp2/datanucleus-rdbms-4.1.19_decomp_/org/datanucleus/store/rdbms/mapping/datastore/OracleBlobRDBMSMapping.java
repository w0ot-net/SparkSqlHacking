package org.datanucleus.store.rdbms.mapping.datastore;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.BitSet;
import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.BLOB;
import org.datanucleus.ClassNameConstants;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
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
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.TypeConversionHelper;

public class OracleBlobRDBMSMapping extends AbstractDatastoreMapping {
   public OracleBlobRDBMSMapping(JavaTypeMapping mapping, RDBMSStoreManager storeMgr, Column col) {
      super(storeMgr, mapping);
      this.column = col;
      this.initialize();
   }

   protected OracleBlobRDBMSMapping(RDBMSStoreManager storeMgr, JavaTypeMapping mapping) {
      super(storeMgr, mapping);
   }

   private void initialize() {
      this.initTypeInfo();
   }

   public String getInsertionInputParameter() {
      return "EMPTY_BLOB()";
   }

   public boolean insertValuesOnInsert() {
      return false;
   }

   public Object getObject(ResultSet rs, int param) {
      Object obj = null;

      try {
         Blob blob = rs.getBlob(param);
         if (!rs.wasNull()) {
            byte[] bytes = blob.getBytes(1L, (int)blob.length());
            if (bytes.length < 1) {
               return null;
            }

            try {
               if (this.getJavaTypeMapping().isSerialised()) {
                  BlobImpl b = new BlobImpl(bytes);
                  obj = b.getObject();
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.BOOLEAN_ARRAY)) {
                  obj = TypeConversionHelper.getBooleanArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.BYTE_ARRAY)) {
                  obj = bytes;
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.CHAR_ARRAY)) {
                  obj = TypeConversionHelper.getCharArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_STRING)) {
                  obj = new String(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.DOUBLE_ARRAY)) {
                  obj = TypeConversionHelper.getDoubleArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.FLOAT_ARRAY)) {
                  obj = TypeConversionHelper.getFloatArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.INT_ARRAY)) {
                  obj = TypeConversionHelper.getIntArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.LONG_ARRAY)) {
                  obj = TypeConversionHelper.getLongArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.SHORT_ARRAY)) {
                  obj = TypeConversionHelper.getShortArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_BOOLEAN_ARRAY)) {
                  obj = TypeConversionHelper.getBooleanObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_BYTE_ARRAY)) {
                  obj = TypeConversionHelper.getByteObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_CHARACTER_ARRAY)) {
                  obj = TypeConversionHelper.getCharObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_DOUBLE_ARRAY)) {
                  obj = TypeConversionHelper.getDoubleObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_FLOAT_ARRAY)) {
                  obj = TypeConversionHelper.getFloatObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_INTEGER_ARRAY)) {
                  obj = TypeConversionHelper.getIntObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_LONG_ARRAY)) {
                  obj = TypeConversionHelper.getLongObjectArrayFromByteArray(bytes);
               } else if (this.getJavaTypeMapping().getType().equals(ClassNameConstants.JAVA_LANG_SHORT_ARRAY)) {
                  obj = TypeConversionHelper.getShortObjectArrayFromByteArray(bytes);
               } else {
                  if (this.getJavaTypeMapping().getType().equals(BigDecimal[].class.getName())) {
                     return TypeConversionHelper.getBigDecimalArrayFromByteArray(bytes);
                  }

                  if (this.getJavaTypeMapping().getType().equals(BigInteger[].class.getName())) {
                     return TypeConversionHelper.getBigIntegerArrayFromByteArray(bytes);
                  }

                  if (this.getJavaTypeMapping().getType().equals(BitSet.class.getName())) {
                     return TypeConversionHelper.getBitSetFromBooleanArray(TypeConversionHelper.getBooleanArrayFromByteArray(bytes));
                  }

                  obj = (new ObjectInputStream(new ByteArrayInputStream(bytes))).readObject();
               }
            } catch (StreamCorruptedException e) {
               String msg = "StreamCorruptedException: object is corrupted";
               NucleusLogger.DATASTORE.error(msg);
               throw (new NucleusUserException(msg, e)).setFatal();
            } catch (IOException e) {
               String msg = "IOException: error when reading object";
               NucleusLogger.DATASTORE.error(msg);
               throw (new NucleusUserException(msg, e)).setFatal();
            } catch (ClassNotFoundException e) {
               String msg = "ClassNotFoundException: error when creating object";
               NucleusLogger.DATASTORE.error(msg);
               throw (new NucleusUserException(msg, e)).setFatal();
            }
         }

         return obj;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException(Localiser.msg("055002", new Object[]{"Object", "" + param, this.column, sqle.getMessage()}), sqle);
      }
   }

   public String getString(ResultSet resultSet, int exprIndex) {
      return (String)this.getObject(resultSet, exprIndex);
   }

   public int getJDBCType() {
      return 2004;
   }

   public String getUpdateInputParameter() {
      return "EMPTY_BLOB()";
   }

   public boolean includeInSQLFetchStatement() {
      return true;
   }

   public static void updateBlobColumn(ObjectProvider sm, Table table, DatastoreMapping mapping, byte[] bytes) {
      ExecutionContext ec = sm.getExecutionContext();
      RDBMSStoreManager storeMgr = table.getStoreManager();
      DatastoreClass classTable = (DatastoreClass)table;
      SQLExpressionFactory exprFactory = storeMgr.getSQLExpressionFactory();
      SQLStatement sqlStmt = new SQLStatement(storeMgr, table, (DatastoreIdentifier)null, (String)null);
      sqlStmt.setClassLoaderResolver(ec.getClassLoaderResolver());
      sqlStmt.addExtension("lock-for-update", true);
      SQLTable blobSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), mapping.getJavaTypeMapping());
      sqlStmt.select(blobSqlTbl, (Column)mapping.getColumn(), (String)null);
      StatementClassMapping mappingDefinition = new StatementClassMapping();
      AbstractClassMetaData cmd = sm.getClassMetaData();
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
      if (sm.isEmbedded()) {
         ObjectProvider[] embeddedOwners = ec.getOwnersForEmbeddedObjectProvider(sm);
         if (embeddedOwners != null) {
            sm = embeddedOwners[0];
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
                     classTable.getDatastoreIdMapping().setObject(ec, ps, datastoreIdx.getParameterPositionsForOccurrence(i), sm.getInternalObjectId());
                  }
               } else if (cmd.getIdentityType() == IdentityType.APPLICATION) {
                  sm.provideFields(cmd.getPKMemberPositions(), storeMgr.getFieldManagerForStatementGeneration(sm, ps, mappingDefinition));
               }

               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, textStmt, ps);

               try {
                  if (!rs.next()) {
                     throw new NucleusObjectNotFoundException("No such database row", sm.getInternalObjectId());
                  }

                  DatastoreAdapter dba = storeMgr.getDatastoreAdapter();
                  int jdbcMajorVersion = dba.getDriverMajorVersion();
                  if (dba.getDatastoreDriverName().equalsIgnoreCase("Oracle JDBC driver") && jdbcMajorVersion < 10) {
                     BLOB blob = null;
                     if (jdbcMajorVersion <= 8) {
                        OracleResultSet ors = (OracleResultSet)rs;
                        blob = ors.getBLOB(1);
                     } else {
                        blob = (BLOB)rs.getBlob(1);
                     }

                     if (blob != null) {
                        blob.putBytes(1L, bytes);
                     }
                  } else {
                     Blob blob = rs.getBlob(1);
                     if (blob != null) {
                        blob.setBytes(1L, bytes);
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
         throw new NucleusDataStoreException("Update of BLOB value failed: " + textStmt, e);
      }
   }
}
