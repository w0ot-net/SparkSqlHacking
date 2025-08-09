package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedValuePCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public abstract class AbstractMapStore extends BaseContainerStore implements MapStore {
   protected boolean iterateUsingDiscriminator = false;
   protected Table mapTable;
   protected DatastoreClass valueTable;
   protected AbstractClassMetaData kmd;
   protected AbstractClassMetaData vmd;
   protected JavaTypeMapping keyMapping;
   protected JavaTypeMapping valueMapping;
   protected String keyType;
   protected String valueType;
   protected boolean keysAreEmbedded;
   protected boolean keysAreSerialised;
   protected boolean valuesAreEmbedded;
   protected boolean valuesAreSerialised;
   private String containsValueStmt;

   public AbstractMapStore(RDBMSStoreManager storeMgr, ClassLoaderResolver clr) {
      super(storeMgr, clr);
   }

   protected void initialise() {
      this.containsValueStmt = this.getContainsValueStmt(this.getOwnerMapping(), this.getValueMapping(), this.getMapTable());
   }

   public boolean keysAreEmbedded() {
      return this.keysAreEmbedded;
   }

   public boolean keysAreSerialised() {
      return this.keysAreSerialised;
   }

   public boolean valuesAreEmbedded() {
      return this.valuesAreEmbedded;
   }

   public boolean valuesAreSerialised() {
      return this.valuesAreSerialised;
   }

   public boolean containsKey(ObjectProvider op, Object key) {
      if (key == null) {
         return false;
      } else {
         try {
            this.getValue(op, key);
            return true;
         } catch (NoSuchElementException var4) {
            return false;
         }
      }
   }

   public boolean containsValue(ObjectProvider op, Object value) {
      if (value == null) {
         return false;
      } else if (!this.validateValueForReading(op, value)) {
         return false;
      } else {
         boolean exists = false;

         try {
            ExecutionContext ec = op.getExecutionContext();
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, this.containsValueStmt);

               try {
                  int jdbcPosition = 1;
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                  BackingStoreHelper.populateValueInStatement(ec, ps, value, jdbcPosition, this.getValueMapping());
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, this.containsValueStmt, ps);

                  try {
                     if (rs.next()) {
                        exists = true;
                     }

                     JDBCUtils.logWarnings(rs);
                  } finally {
                     rs.close();
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }

            return exists;
         } catch (SQLException e) {
            NucleusLogger.DATASTORE_RETRIEVE.warn("Exception during backing store select", e);
            throw new NucleusDataStoreException(Localiser.msg("056019", new Object[]{this.containsValueStmt}), e);
         }
      }
   }

   public Object get(ObjectProvider op, Object key) {
      try {
         return this.getValue(op, key);
      } catch (NoSuchElementException var4) {
         return null;
      }
   }

   public void putAll(ObjectProvider op, Map m) {
      for(Map.Entry e : m.entrySet()) {
         this.put(op, e.getKey(), e.getValue());
      }

   }

   protected void validateKeyType(ClassLoaderResolver clr, Object key) {
      if (key == null && !this.allowNulls) {
         throw new NullPointerException(Localiser.msg("056062"));
      } else if (key != null && !clr.isAssignableFrom(this.keyType, key.getClass())) {
         throw new ClassCastException(Localiser.msg("056064", new Object[]{key.getClass().getName(), this.keyType}));
      }
   }

   protected void validateValueType(ClassLoaderResolver clr, Object value) {
      if (value == null && !this.allowNulls) {
         throw new NullPointerException(Localiser.msg("056063"));
      } else if (value != null && !clr.isAssignableFrom(this.valueType, value.getClass())) {
         throw new ClassCastException(Localiser.msg("056065", new Object[]{value.getClass().getName(), this.valueType}));
      }
   }

   protected boolean validateKeyForReading(ObjectProvider op, Object key) {
      this.validateKeyType(op.getExecutionContext().getClassLoaderResolver(), key);
      if (!this.keysAreEmbedded && !this.keysAreSerialised) {
         ExecutionContext ec = op.getExecutionContext();
         if (key != null && (!ec.getApiAdapter().isPersistent(key) || ec != ec.getApiAdapter().getExecutionContext(key)) && !ec.getApiAdapter().isDetached(key)) {
            return false;
         }
      }

      return true;
   }

   protected boolean validateValueForReading(ObjectProvider op, Object value) {
      this.validateValueType(op.getExecutionContext().getClassLoaderResolver(), value);
      if (!this.valuesAreEmbedded && !this.valuesAreSerialised) {
         ExecutionContext ec = op.getExecutionContext();
         if (value != null && (!ec.getApiAdapter().isPersistent(value) || ec != ec.getApiAdapter().getExecutionContext(value)) && !ec.getApiAdapter().isDetached(value)) {
            return false;
         }
      }

      return true;
   }

   protected void validateKeyForWriting(ObjectProvider ownerOP, Object key) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      this.validateKeyType(ec.getClassLoaderResolver(), key);
      if (!this.keysAreEmbedded && !this.keysAreSerialised) {
         SCOUtils.validateObjectForWriting(ec, key, (FieldValues)null);
      }

   }

   protected void validateValueForWriting(ObjectProvider ownerOP, Object value) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      this.validateValueType(ec.getClassLoaderResolver(), value);
      if (!this.valuesAreEmbedded && !this.valuesAreSerialised) {
         SCOUtils.validateObjectForWriting(ec, value, (FieldValues)null);
      }

   }

   protected abstract Object getValue(ObjectProvider var1, Object var2) throws NoSuchElementException;

   public boolean updateEmbeddedKey(ObjectProvider op, Object key, int fieldNumber, Object newValue) {
      boolean modified = false;
      if (this.keyMapping != null && this.keyMapping instanceof EmbeddedKeyPCMapping) {
         String fieldName = this.vmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).getName();
         if (fieldName == null) {
            return false;
         }

         JavaTypeMapping fieldMapping = ((EmbeddedKeyPCMapping)this.keyMapping).getJavaTypeMapping(fieldName);
         if (fieldMapping == null) {
            return false;
         }

         modified = this.updatedEmbeddedKey(op, key, fieldNumber, newValue, fieldMapping);
      }

      return modified;
   }

   public boolean updateEmbeddedValue(ObjectProvider op, Object value, int fieldNumber, Object newValue) {
      boolean modified = false;
      if (this.valueMapping != null && this.valueMapping instanceof EmbeddedValuePCMapping) {
         String fieldName = this.vmd.getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber).getName();
         if (fieldName == null) {
            return false;
         }

         JavaTypeMapping fieldMapping = ((EmbeddedValuePCMapping)this.valueMapping).getJavaTypeMapping(fieldName);
         if (fieldMapping == null) {
            return false;
         }

         modified = this.updateEmbeddedValue(op, value, fieldNumber, newValue, fieldMapping);
      }

      return modified;
   }

   public JavaTypeMapping getValueMapping() {
      return this.valueMapping;
   }

   public JavaTypeMapping getKeyMapping() {
      return this.keyMapping;
   }

   public boolean isValuesAreEmbedded() {
      return this.valuesAreEmbedded;
   }

   public boolean isValuesAreSerialised() {
      return this.valuesAreSerialised;
   }

   public Table getMapTable() {
      return this.mapTable;
   }

   public AbstractClassMetaData getKmd() {
      return this.kmd;
   }

   public AbstractClassMetaData getVmd() {
      return this.vmd;
   }

   private String getContainsValueStmt(JavaTypeMapping ownerMapping, JavaTypeMapping valueMapping, Table mapTable) {
      StringBuilder stmt = new StringBuilder("SELECT ");

      for(int i = 0; i < ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(" FROM ");
      stmt.append(mapTable.toString());
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForMapping(stmt, valueMapping, (String)null, false);
      return stmt.toString();
   }

   public boolean updateEmbeddedValue(ObjectProvider op, Object value, int fieldNumber, Object newValue, JavaTypeMapping fieldMapping) {
      String stmt = this.getUpdateEmbeddedValueStmt(fieldMapping, this.getOwnerMapping(), this.getValueMapping(), this.getMapTable());

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         boolean modified;
         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

            try {
               int jdbcPosition = 1;
               fieldMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, fieldMapping), newValue);
               jdbcPosition += fieldMapping.getNumberOfDatastoreMappings();
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               BackingStoreHelper.populateEmbeddedValueFieldsInStatement(op, value, ps, jdbcPosition, (JoinTable)this.getMapTable(), this);
               sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, true);
               modified = true;
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return modified;
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_PERSIST.warn("Exception in backing store update", e);
         throw new NucleusDataStoreException(Localiser.msg("056011", new Object[]{stmt}), e);
      }
   }

   protected String getUpdateEmbeddedKeyStmt(JavaTypeMapping fieldMapping, JavaTypeMapping ownerMapping, JavaTypeMapping keyMapping, Table mapTable) {
      StringBuilder stmt = new StringBuilder("UPDATE ");
      stmt.append(mapTable.toString());
      stmt.append(" SET ");

      for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(fieldMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" = ");
         stmt.append(((AbstractDatastoreMapping)fieldMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
      EmbeddedKeyPCMapping embeddedMapping = (EmbeddedKeyPCMapping)keyMapping;

      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping m = embeddedMapping.getJavaTypeMapping(i);
         if (m != null) {
            for(int j = 0; j < m.getNumberOfDatastoreMappings(); ++j) {
               stmt.append(" AND ");
               stmt.append(m.getDatastoreMapping(j).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)m.getDatastoreMapping(j)).getUpdateInputParameter());
            }
         }
      }

      return stmt.toString();
   }

   protected String getUpdateEmbeddedValueStmt(JavaTypeMapping fieldMapping, JavaTypeMapping ownerMapping, JavaTypeMapping valueMapping, Table mapTable) {
      StringBuilder stmt = new StringBuilder("UPDATE ");
      stmt.append(mapTable.toString());
      stmt.append(" SET ");

      for(int i = 0; i < fieldMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(fieldMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" = ");
         stmt.append(((AbstractDatastoreMapping)fieldMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, ownerMapping, (String)null, true);
      EmbeddedValuePCMapping embeddedMapping = (EmbeddedValuePCMapping)valueMapping;

      for(int i = 0; i < embeddedMapping.getNumberOfJavaTypeMappings(); ++i) {
         JavaTypeMapping m = embeddedMapping.getJavaTypeMapping(i);
         if (m != null) {
            for(int j = 0; j < m.getNumberOfDatastoreMappings(); ++j) {
               stmt.append(" AND ");
               stmt.append(m.getDatastoreMapping(j).getColumn().getIdentifier().toString());
               stmt.append(" = ");
               stmt.append(((AbstractDatastoreMapping)m.getDatastoreMapping(j)).getUpdateInputParameter());
            }
         }
      }

      return stmt.toString();
   }

   public boolean updatedEmbeddedKey(ObjectProvider op, Object key, int fieldNumber, Object newValue, JavaTypeMapping fieldMapping) {
      String stmt = this.getUpdateEmbeddedKeyStmt(fieldMapping, this.getOwnerMapping(), this.getKeyMapping(), this.getMapTable());

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         boolean modified;
         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, false);

            try {
               int jdbcPosition = 1;
               fieldMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, fieldMapping), key);
               jdbcPosition += fieldMapping.getNumberOfDatastoreMappings();
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               BackingStoreHelper.populateEmbeddedKeyFieldsInStatement(op, key, ps, jdbcPosition, (JoinTable)this.getMapTable(), this);
               sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, true);
               modified = true;
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return modified;
      } catch (SQLException e) {
         NucleusLogger.DATASTORE_PERSIST.warn("Exception during backing store update", e);
         throw new NucleusDataStoreException(Localiser.msg("056010", new Object[]{stmt}), e);
      }
   }
}
