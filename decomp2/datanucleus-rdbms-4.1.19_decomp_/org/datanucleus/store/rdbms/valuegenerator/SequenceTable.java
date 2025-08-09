package org.datanucleus.store.rdbms.valuegenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.JdbcType;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.identifier.IdentifierFactory;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.TableImpl;
import org.datanucleus.store.valuegenerator.ValueGenerationException;
import org.datanucleus.util.Localiser;

public class SequenceTable extends TableImpl {
   private JavaTypeMapping sequenceNameMapping = null;
   private JavaTypeMapping nextValMapping = null;
   private String insertStmt = null;
   private String incrementByStmt = null;
   private String deleteStmt = null;
   private String deleteAllStmt = null;
   private String fetchAllStmt = null;
   private String fetchStmt = null;
   private String sequenceNameColumnName;
   private String nextValColumnName;

   public SequenceTable(DatastoreIdentifier identifier, RDBMSStoreManager storeMgr, String seqNameColName, String nextValColName) {
      super(identifier, storeMgr);
      this.sequenceNameColumnName = seqNameColName;
      this.nextValColumnName = nextValColName;
   }

   public void initialize(ClassLoaderResolver clr) {
      this.assertIsUninitialized();
      IdentifierFactory idFactory = this.storeMgr.getIdentifierFactory();
      this.sequenceNameMapping = this.storeMgr.getMappingManager().getMapping(String.class);
      Column colSequenceName = this.addColumn(String.class.getName(), idFactory.newColumnIdentifier(this.sequenceNameColumnName), this.sequenceNameMapping, (ColumnMetaData)null);
      colSequenceName.setPrimaryKey();
      colSequenceName.getColumnMetaData().setLength(Integer.valueOf("255"));
      colSequenceName.getColumnMetaData().setJdbcType(JdbcType.VARCHAR);
      this.getStoreManager().getMappingManager().createDatastoreMapping(this.sequenceNameMapping, colSequenceName, String.class.getName());
      this.nextValMapping = this.storeMgr.getMappingManager().getMapping(Long.class);
      Column colNextVal = this.addColumn(Long.class.getName(), idFactory.newColumnIdentifier(this.nextValColumnName), this.nextValMapping, (ColumnMetaData)null);
      this.getStoreManager().getMappingManager().createDatastoreMapping(this.nextValMapping, colNextVal, Long.class.getName());
      this.insertStmt = "INSERT INTO " + this.identifier.getFullyQualifiedName(false) + " (" + colSequenceName.getIdentifier() + "," + colNextVal.getIdentifier() + ") VALUES (?,?)";
      this.incrementByStmt = "UPDATE " + this.identifier.getFullyQualifiedName(false) + " SET " + colNextVal.getIdentifier() + "=(" + colNextVal.getIdentifier() + "+?) WHERE " + colSequenceName.getIdentifier() + "=?";
      this.deleteStmt = "DELETE FROM " + this.identifier.getFullyQualifiedName(false) + " WHERE " + colSequenceName.getIdentifier() + "=?";
      this.deleteAllStmt = "DELETE FROM " + this.identifier.getFullyQualifiedName(false);
      this.fetchStmt = "SELECT " + colNextVal.getIdentifier() + " FROM " + this.identifier.getFullyQualifiedName(false) + " WHERE " + colSequenceName.getIdentifier() + "=?";
      if (this.dba.supportsOption("LockWithSelectForUpdate")) {
         this.fetchStmt = this.fetchStmt + " FOR UPDATE";
      }

      this.fetchAllStmt = "SELECT " + colNextVal.getIdentifier() + "," + colSequenceName.getIdentifier() + " FROM " + this.identifier.getFullyQualifiedName(false) + " ORDER BY " + colSequenceName.getIdentifier();
      this.storeMgr.registerTableInitialized(this);
      this.state = 2;
   }

   public JavaTypeMapping getIdMapping() {
      throw (new NucleusException("Attempt to get ID mapping of Sequence table!")).setFatal();
   }

   public HashSet getFetchAllSequences(ManagedConnection conn) throws SQLException {
      HashSet sequenceNames = new HashSet();
      PreparedStatement ps = null;
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ps = sqlControl.getStatementForQuery(conn, this.fetchAllStmt);
         ResultSet rs = sqlControl.executeStatementQuery((ExecutionContext)null, conn, this.fetchAllStmt, ps);

         try {
            while(rs.next()) {
               sequenceNames.add(rs.getString(2));
            }
         } finally {
            rs.close();
         }
      } finally {
         if (ps != null) {
            sqlControl.closeStatement(conn, ps);
         }

      }

      return sequenceNames;
   }

   public Long getNextVal(String sequenceName, ManagedConnection conn, int incrementBy, DatastoreIdentifier tableIdentifier, String columnName, int initialValue) throws SQLException {
      PreparedStatement ps = null;
      Long nextVal = null;
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ps = sqlControl.getStatementForQuery(conn, this.fetchStmt);
         this.sequenceNameMapping.setString((ExecutionContext)null, ps, new int[]{1}, sequenceName);
         ResultSet rs = sqlControl.executeStatementQuery((ExecutionContext)null, conn, this.fetchStmt, ps);

         try {
            if (!rs.next()) {
               boolean addedSequence = false;
               if (initialValue >= 0) {
                  this.addSequence(sequenceName, (long)(incrementBy + initialValue), conn);
                  nextVal = (long)initialValue;
               } else {
                  if (columnName != null && tableIdentifier != null) {
                     PreparedStatement ps2 = null;
                     ResultSet rs2 = null;

                     try {
                        String fetchInitStmt = "SELECT MAX(" + columnName + ") FROM " + tableIdentifier.getFullyQualifiedName(false);
                        ps2 = sqlControl.getStatementForQuery(conn, fetchInitStmt);
                        rs2 = sqlControl.executeStatementQuery((ExecutionContext)null, conn, fetchInitStmt, ps2);
                        if (rs2.next()) {
                           long val = rs2.getLong(1);
                           this.addSequence(sequenceName, (long)(incrementBy + 1) + val, conn);
                           nextVal = 1L + val;
                           addedSequence = true;
                        }
                     } catch (Exception var35) {
                     } finally {
                        if (rs2 != null) {
                           rs2.close();
                        }

                        if (ps2 != null) {
                           sqlControl.closeStatement(conn, ps2);
                        }

                     }
                  }

                  if (!addedSequence) {
                     this.addSequence(sequenceName, (long)(incrementBy + 0), conn);
                     nextVal = (long)initialValue;
                  }
               }
            } else {
               nextVal = rs.getLong(1);
               this.incrementSequence(sequenceName, (long)incrementBy, conn);
            }
         } finally {
            rs.close();
         }
      } catch (SQLException e) {
         throw new ValueGenerationException(Localiser.msg("061001", new Object[]{e.getMessage()}), e);
      } finally {
         if (ps != null) {
            sqlControl.closeStatement(conn, ps);
         }

      }

      return nextVal;
   }

   private void incrementSequence(String sequenceName, long incrementBy, ManagedConnection conn) throws SQLException {
      PreparedStatement ps = null;
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ps = sqlControl.getStatementForUpdate(conn, this.incrementByStmt, false);
         this.nextValMapping.setLong((ExecutionContext)null, ps, new int[]{1}, incrementBy);
         this.sequenceNameMapping.setString((ExecutionContext)null, ps, new int[]{2}, sequenceName);
         sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.incrementByStmt, ps, true);
      } finally {
         if (ps != null) {
            sqlControl.closeStatement(conn, ps);
         }

      }

   }

   private void addSequence(String sequenceName, Long nextVal, ManagedConnection conn) throws SQLException {
      PreparedStatement ps = null;
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ps = sqlControl.getStatementForUpdate(conn, this.insertStmt, false);
         this.sequenceNameMapping.setString((ExecutionContext)null, ps, new int[]{1}, sequenceName);
         this.nextValMapping.setLong((ExecutionContext)null, ps, new int[]{2}, nextVal);
         sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.insertStmt, ps, true);
      } catch (SQLException e) {
         e.printStackTrace();
         throw e;
      } finally {
         if (ps != null) {
            sqlControl.closeStatement(conn, ps);
         }

      }

   }

   public void deleteSequence(String sequenceName, ManagedConnection conn) throws SQLException {
      PreparedStatement ps = null;
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ps = sqlControl.getStatementForUpdate(conn, this.deleteStmt, false);
         ps.setString(1, sequenceName);
         sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.deleteStmt, ps, true);
      } finally {
         if (ps != null) {
            sqlControl.closeStatement(conn, ps);
         }

      }

   }

   public void deleteAllSequences(ManagedConnection conn) throws SQLException {
      PreparedStatement ps = null;
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ps = sqlControl.getStatementForUpdate(conn, this.deleteAllStmt, false);
         sqlControl.executeStatementUpdate((ExecutionContext)null, conn, this.deleteAllStmt, ps, true);
      } finally {
         if (ps != null) {
            sqlControl.closeStatement(conn, ps);
         }

      }

   }

   public JavaTypeMapping getMemberMapping(AbstractMemberMetaData mmd) {
      return null;
   }
}
