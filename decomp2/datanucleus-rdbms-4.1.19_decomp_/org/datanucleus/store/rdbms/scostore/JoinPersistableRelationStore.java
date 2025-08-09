package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.adapter.DatastoreAdapter;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.PersistableJoinTable;
import org.datanucleus.store.scostore.PersistableRelationStore;

public class JoinPersistableRelationStore implements PersistableRelationStore {
   protected RDBMSStoreManager storeMgr;
   protected DatastoreAdapter dba;
   protected JavaTypeMapping ownerMapping;
   protected AbstractMemberMetaData ownerMemberMetaData;
   protected PersistableJoinTable joinTable;
   protected ClassLoaderResolver clr;
   protected String addStmt;
   protected String updateStmt;
   protected String removeStmt;

   public JoinPersistableRelationStore(AbstractMemberMetaData mmd, PersistableJoinTable joinTable, ClassLoaderResolver clr) {
      this.storeMgr = joinTable.getStoreManager();
      this.dba = this.storeMgr.getDatastoreAdapter();
      this.ownerMemberMetaData = mmd;
      this.joinTable = joinTable;
      this.clr = clr;
   }

   public StoreManager getStoreManager() {
      return this.storeMgr;
   }

   public AbstractMemberMetaData getOwnerMemberMetaData() {
      return this.ownerMemberMetaData;
   }

   public boolean add(ObjectProvider sm1, ObjectProvider sm2) {
      String addStmt = this.getAddStmt();
      ExecutionContext ec = sm1.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, addStmt, false);

         boolean var10;
         try {
            int jdbcPosition = 1;
            jdbcPosition = populateOwnerInStatement(sm1, ec, ps, jdbcPosition, this.joinTable);
            BackingStoreHelper.populateElementInStatement(ec, ps, sm2.getObject(), jdbcPosition, this.joinTable.getRelatedMapping());
            int[] nums = sqlControl.executeStatementUpdate(ec, mconn, addStmt, ps, true);
            var10 = nums != null && nums.length == 1 && nums[0] == 1;
         } finally {
            sqlControl.closeStatement(mconn, ps);
            mconn.release();
         }

         return var10;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown inserting row into persistable relation join table", sqle);
      }
   }

   public boolean remove(ObjectProvider op) {
      String removeStmt = this.getRemoveStmt();
      ExecutionContext ec = op.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, removeStmt, false);

         boolean var9;
         try {
            int jdbcPosition = 1;
            populateOwnerInStatement(op, ec, ps, jdbcPosition, this.joinTable);
            int[] nums = sqlControl.executeStatementUpdate(ec, mconn, removeStmt, ps, true);
            var9 = nums != null && nums.length == 1 && nums[0] == 1;
         } finally {
            sqlControl.closeStatement(mconn, ps);
            mconn.release();
         }

         return var9;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown deleting row from persistable relation join table", sqle);
      }
   }

   public boolean update(ObjectProvider sm1, ObjectProvider sm2) {
      String updateStmt = this.getUpdateStmt();
      ExecutionContext ec = sm1.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, updateStmt, false);

         boolean var10;
         try {
            int jdbcPosition = 1;
            jdbcPosition = BackingStoreHelper.populateElementInStatement(ec, ps, sm2.getObject(), jdbcPosition, this.joinTable.getRelatedMapping());
            populateOwnerInStatement(sm1, ec, ps, jdbcPosition, this.joinTable);
            int[] nums = sqlControl.executeStatementUpdate(ec, mconn, updateStmt, ps, true);
            var10 = nums != null && nums.length == 1 && nums[0] == 1;
         } finally {
            sqlControl.closeStatement(mconn, ps);
            mconn.release();
         }

         return var10;
      } catch (SQLException sqle) {
         throw new NucleusDataStoreException("Exception thrown updating row into persistable relation join table", sqle);
      }
   }

   protected String getAddStmt() {
      if (this.addStmt == null) {
         JavaTypeMapping ownerMapping = this.joinTable.getOwnerMapping();
         JavaTypeMapping relatedMapping = this.joinTable.getRelatedMapping();
         StringBuilder stmt = new StringBuilder("INSERT INTO ");
         stmt.append(this.joinTable.toString());
         stmt.append(" (");

         for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               stmt.append(",");
            }

            stmt.append(ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         }

         for(int i = 0; i < relatedMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(relatedMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         }

         stmt.append(") VALUES (");

         for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               stmt.append(",");
            }

            stmt.append(((AbstractDatastoreMapping)ownerMapping.getDatastoreMapping(i)).getInsertionInputParameter());
         }

         for(int i = 0; i < relatedMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(((AbstractDatastoreMapping)relatedMapping.getDatastoreMapping(0)).getInsertionInputParameter());
         }

         stmt.append(") ");
         this.addStmt = stmt.toString();
      }

      return this.addStmt;
   }

   protected String getUpdateStmt() {
      if (this.updateStmt == null) {
         JavaTypeMapping ownerMapping = this.joinTable.getOwnerMapping();
         JavaTypeMapping relatedMapping = this.joinTable.getRelatedMapping();
         StringBuilder stmt = new StringBuilder("UPDATE ");
         stmt.append(this.joinTable.toString());
         stmt.append(" SET ");

         for(int i = 0; i < relatedMapping.getNumberOfDatastoreMappings(); ++i) {
            if (i > 0) {
               stmt.append(",");
            }

            stmt.append(relatedMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
            stmt.append("=");
            stmt.append(((AbstractDatastoreMapping)ownerMapping.getDatastoreMapping(i)).getInsertionInputParameter());
         }

         stmt.append(" WHERE ");
         BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
         this.updateStmt = stmt.toString();
      }

      return this.updateStmt;
   }

   protected String getRemoveStmt() {
      if (this.removeStmt == null) {
         JavaTypeMapping ownerMapping = this.joinTable.getOwnerMapping();
         StringBuilder stmt = new StringBuilder("DELETE FROM ");
         stmt.append(this.joinTable.toString());
         stmt.append(" WHERE ");
         BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
         this.removeStmt = stmt.toString();
      }

      return this.removeStmt;
   }

   public static int populateOwnerInStatement(ObjectProvider sm, ExecutionContext ec, PreparedStatement ps, int jdbcPosition, PersistableJoinTable joinTable) {
      if (!joinTable.getStoreManager().insertValuesOnInsert(joinTable.getOwnerMapping().getDatastoreMapping(0))) {
         return jdbcPosition;
      } else {
         if (joinTable.getOwnerMemberMetaData() != null) {
            joinTable.getOwnerMapping().setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, joinTable.getOwnerMapping()), sm.getObject(), sm, joinTable.getOwnerMemberMetaData().getAbsoluteFieldNumber());
         } else {
            joinTable.getOwnerMapping().setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, joinTable.getOwnerMapping()), sm.getObject());
         }

         return jdbcPosition + joinTable.getOwnerMapping().getNumberOfDatastoreMappings();
      }
   }
}
