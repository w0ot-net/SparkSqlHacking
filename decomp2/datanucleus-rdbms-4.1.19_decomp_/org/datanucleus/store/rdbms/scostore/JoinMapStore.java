package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.query.expression.Expression;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.query.StatementParameterMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.BooleanExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.JoinTable;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.SetStore;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class JoinMapStore extends AbstractMapStore {
   private String putStmt;
   private String updateStmt;
   private String removeStmt;
   private String clearStmt;
   private String getStmtLocked = null;
   private String getStmtUnlocked = null;
   private StatementClassMapping getMappingDef = null;
   private StatementParameterMapping getMappingParams = null;
   private SetStore keySetStore = null;
   private CollectionStore valueSetStore = null;
   private SetStore entrySetStore = null;
   protected final JavaTypeMapping adapterMapping;

   public JoinMapStore(MapTable mapTable, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.mapTable = mapTable;
      this.setOwner(mapTable.getOwnerMemberMetaData());
      this.ownerMapping = mapTable.getOwnerMapping();
      this.keyMapping = mapTable.getKeyMapping();
      this.valueMapping = mapTable.getValueMapping();
      this.adapterMapping = mapTable.getOrderMapping();
      this.keyType = mapTable.getKeyType();
      this.keysAreEmbedded = mapTable.isEmbeddedKey();
      this.keysAreSerialised = mapTable.isSerialisedKey();
      this.valueType = mapTable.getValueType();
      this.valuesAreEmbedded = mapTable.isEmbeddedValue();
      this.valuesAreSerialised = mapTable.isSerialisedValue();
      Class key_class = clr.classForName(this.keyType);
      this.kmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(key_class, clr);
      Class value_class = clr.classForName(this.valueType);
      if (ClassUtils.isReferenceType(value_class)) {
         NucleusLogger.PERSISTENCE.warn(Localiser.msg("056066", new Object[]{this.ownerMemberMetaData.getFullFieldName(), value_class.getName()}));
         this.vmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForImplementationOfReference(value_class, (Object)null, clr);
         if (this.vmd != null) {
            this.valueType = value_class.getName();
            this.valueTable = this.storeMgr.getDatastoreClass(this.vmd.getFullClassName(), clr);
         }
      } else {
         this.vmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(value_class, clr);
         if (this.vmd != null) {
            this.valueType = this.vmd.getFullClassName();
            if (this.valuesAreEmbedded) {
               this.valueTable = null;
            } else {
               this.valueTable = this.storeMgr.getDatastoreClass(this.valueType, clr);
            }
         }
      }

      this.initialise();
      this.putStmt = this.getPutStmt();
      this.updateStmt = this.getUpdateStmt();
      this.removeStmt = this.getRemoveStmt();
      this.clearStmt = this.getClearStmt();
   }

   public void putAll(ObjectProvider op, Map m) {
      if (m != null && m.size() != 0) {
         HashSet puts = new HashSet();
         HashSet updates = new HashSet();

         for(Map.Entry e : m.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            this.validateKeyForWriting(op, key);
            this.validateValueForWriting(op, value);

            try {
               Object oldValue = this.getValue(op, key);
               if (oldValue != value) {
                  updates.add(e);
               }
            } catch (NoSuchElementException var23) {
               puts.add(e);
            }
         }

         boolean batched = this.allowsBatching();
         if (puts.size() > 0) {
            try {
               ExecutionContext ec = op.getExecutionContext();
               ManagedConnection mconn = this.storeMgr.getConnection(ec);

               try {
                  Iterator iter = puts.iterator();

                  while(iter.hasNext()) {
                     Map.Entry entry = (Map.Entry)iter.next();
                     this.internalPut(op, mconn, batched, entry.getKey(), entry.getValue(), !iter.hasNext());
                  }
               } finally {
                  mconn.release();
               }
            } catch (MappedDatastoreException e) {
               throw new NucleusDataStoreException(Localiser.msg("056016", new Object[]{e.getMessage()}), e);
            }
         }

         if (updates.size() > 0) {
            try {
               ExecutionContext ec = op.getExecutionContext();
               ManagedConnection mconn = this.storeMgr.getConnection(ec);

               try {
                  Iterator iter = updates.iterator();

                  while(iter.hasNext()) {
                     Map.Entry entry = (Map.Entry)iter.next();
                     this.internalUpdate(op, mconn, batched, entry.getKey(), entry.getValue(), !iter.hasNext());
                  }
               } finally {
                  mconn.release();
               }
            } catch (MappedDatastoreException mde) {
               throw new NucleusDataStoreException(Localiser.msg("056016", new Object[]{mde.getMessage()}), mde);
            }
         }

      }
   }

   public Object put(ObjectProvider op, Object key, Object value) {
      this.validateKeyForWriting(op, key);
      this.validateValueForWriting(op, value);
      boolean exists = false;

      V oldValue;
      try {
         oldValue = (V)this.getValue(op, key);
         exists = true;
      } catch (NoSuchElementException var14) {
         oldValue = (V)null;
         exists = false;
      }

      if (oldValue != value) {
         try {
            ExecutionContext ec = op.getExecutionContext();
            ManagedConnection mconn = this.storeMgr.getConnection(ec);

            try {
               if (exists) {
                  this.internalUpdate(op, mconn, false, key, value, true);
               } else {
                  this.internalPut(op, mconn, false, key, value, true);
               }
            } finally {
               mconn.release();
            }
         } catch (MappedDatastoreException e) {
            throw new NucleusDataStoreException(Localiser.msg("056016", new Object[]{e.getMessage()}), e);
         }
      }

      MapMetaData mapmd = this.ownerMemberMetaData.getMap();
      if (mapmd.isDependentValue() && !mapmd.isEmbeddedValue() && oldValue != null && !this.containsValue(op, oldValue)) {
         op.getExecutionContext().deleteObjectInternal(oldValue);
      }

      return oldValue;
   }

   public Object remove(ObjectProvider op, Object key) {
      if (!this.validateKeyForReading(op, key)) {
         return null;
      } else {
         V oldValue;
         boolean exists;
         try {
            oldValue = (V)this.getValue(op, key);
            exists = true;
         } catch (NoSuchElementException var8) {
            oldValue = (V)null;
            exists = false;
         }

         ExecutionContext ec = op.getExecutionContext();
         if (exists) {
            this.removeInternal(op, key);
         }

         MapMetaData mapmd = this.ownerMemberMetaData.getMap();
         ApiAdapter api = ec.getApiAdapter();
         if (mapmd.isDependentKey() && !mapmd.isEmbeddedKey() && api.isPersistable(key)) {
            ec.deleteObjectInternal(key);
         }

         if (mapmd.isDependentValue() && !mapmd.isEmbeddedValue() && api.isPersistable(oldValue) && !this.containsValue(op, oldValue)) {
            ec.deleteObjectInternal(oldValue);
         }

         return oldValue;
      }
   }

   public Object remove(ObjectProvider op, Object key, Object oldValue) {
      if (!this.validateKeyForReading(op, key)) {
         return null;
      } else {
         ExecutionContext ec = op.getExecutionContext();
         this.removeInternal(op, key);
         MapMetaData mapmd = this.ownerMemberMetaData.getMap();
         ApiAdapter api = ec.getApiAdapter();
         if (mapmd.isDependentKey() && !mapmd.isEmbeddedKey() && api.isPersistable(key)) {
            ec.deleteObjectInternal(key);
         }

         if (mapmd.isDependentValue() && !mapmd.isEmbeddedValue() && api.isPersistable(oldValue) && !this.containsValue(op, oldValue)) {
            ec.deleteObjectInternal(oldValue);
         }

         return oldValue;
      }
   }

   public void clear(ObjectProvider ownerOP) {
      Collection dependentElements = null;
      if (this.ownerMemberMetaData.getMap().isDependentKey() || this.ownerMemberMetaData.getMap().isDependentValue()) {
         dependentElements = new HashSet();
         ApiAdapter api = ownerOP.getExecutionContext().getApiAdapter();
         Iterator iter = this.entrySetStore().iterator(ownerOP);

         while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            MapMetaData mapmd = this.ownerMemberMetaData.getMap();
            if (api.isPersistable(entry.getKey()) && mapmd.isDependentKey() && !mapmd.isEmbeddedKey()) {
               dependentElements.add(entry.getKey());
            }

            if (api.isPersistable(entry.getValue()) && mapmd.isDependentValue() && !mapmd.isEmbeddedValue()) {
               dependentElements.add(entry.getValue());
            }
         }
      }

      this.clearInternal(ownerOP);
      if (dependentElements != null && dependentElements.size() > 0) {
         ownerOP.getExecutionContext().deleteObjects(dependentElements.toArray());
      }

   }

   public synchronized SetStore keySetStore() {
      if (this.keySetStore == null) {
         this.keySetStore = new MapKeySetStore((MapTable)this.mapTable, this, this.clr);
      }

      return this.keySetStore;
   }

   public synchronized CollectionStore valueCollectionStore() {
      if (this.valueSetStore == null) {
         this.valueSetStore = new MapValueCollectionStore((MapTable)this.mapTable, this, this.clr);
      }

      return this.valueSetStore;
   }

   public synchronized SetStore entrySetStore() {
      if (this.entrySetStore == null) {
         this.entrySetStore = new MapEntrySetStore((MapTable)this.mapTable, this, this.clr);
      }

      return this.entrySetStore;
   }

   public JavaTypeMapping getAdapterMapping() {
      return this.adapterMapping;
   }

   private String getPutStmt() {
      StringBuilder stmt = new StringBuilder("INSERT INTO ");
      stmt.append(this.mapTable.toString());
      stmt.append(" (");

      for(int i = 0; i < this.valueMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.valueMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         stmt.append(",");
         stmt.append(this.ownerMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      if (this.adapterMapping != null) {
         for(int i = 0; i < this.adapterMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(this.adapterMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         }
      }

      for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
         stmt.append(",");
         stmt.append(this.keyMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(") VALUES (");

      for(int i = 0; i < this.valueMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(((AbstractDatastoreMapping)this.valueMapping.getDatastoreMapping(i)).getInsertionInputParameter());
      }

      for(int i = 0; i < this.ownerMapping.getNumberOfDatastoreMappings(); ++i) {
         stmt.append(",");
         stmt.append(((AbstractDatastoreMapping)this.ownerMapping.getDatastoreMapping(i)).getInsertionInputParameter());
      }

      if (this.adapterMapping != null) {
         for(int i = 0; i < this.adapterMapping.getNumberOfDatastoreMappings(); ++i) {
            stmt.append(",");
            stmt.append(((AbstractDatastoreMapping)this.adapterMapping.getDatastoreMapping(i)).getInsertionInputParameter());
         }
      }

      for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
         stmt.append(",");
         stmt.append(((AbstractDatastoreMapping)this.keyMapping.getDatastoreMapping(i)).getInsertionInputParameter());
      }

      stmt.append(") ");
      return stmt.toString();
   }

   private String getUpdateStmt() {
      StringBuilder stmt = new StringBuilder("UPDATE ");
      stmt.append(this.mapTable.toString());
      stmt.append(" SET ");

      for(int i = 0; i < this.valueMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.valueMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
         stmt.append(" = ");
         stmt.append(((AbstractDatastoreMapping)this.valueMapping.getDatastoreMapping(i)).getUpdateInputParameter());
      }

      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.keyMapping, (String)null, false);
      return stmt.toString();
   }

   private String getRemoveStmt() {
      StringBuilder stmt = new StringBuilder("DELETE FROM ");
      stmt.append(this.mapTable.toString());
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.keyMapping, (String)null, false);
      return stmt.toString();
   }

   private String getClearStmt() {
      StringBuilder stmt = new StringBuilder("DELETE FROM ");
      stmt.append(this.mapTable.toString());
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      return stmt.toString();
   }

   protected Object getValue(ObjectProvider ownerOP, Object key) throws NoSuchElementException {
      if (!this.validateKeyForReading(ownerOP, key)) {
         return null;
      } else {
         ExecutionContext ec = ownerOP.getExecutionContext();
         if (this.getStmtLocked == null) {
            synchronized(this) {
               SQLStatement sqlStmt = this.getSQLStatementForGet(ownerOP);
               this.getStmtUnlocked = sqlStmt.getSelectStatement().toSQL();
               sqlStmt.addExtension("lock-for-update", true);
               this.getStmtLocked = sqlStmt.getSelectStatement().toSQL();
            }
         }

         Transaction tx = ec.getTransaction();
         String stmt = tx.getSerializeRead() != null && tx.getSerializeRead() ? this.getStmtLocked : this.getStmtUnlocked;
         Object value = null;

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
               StatementMappingIndex ownerIdx = this.getMappingParams.getMappingForParameter("owner");
               int numParams = ownerIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), ownerOP.getObject());
               }

               StatementMappingIndex keyIdx = this.getMappingParams.getMappingForParameter("key");
               numParams = keyIdx.getNumberOfParameterOccurrences();

               for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
                  keyIdx.getMapping().setObject(ec, ps, keyIdx.getParameterPositionsForOccurrence(paramInstance), key);
               }

               try {
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

                  try {
                     boolean found = rs.next();
                     if (!found) {
                        throw new NoSuchElementException();
                     }

                     if (!this.valuesAreEmbedded && !this.valuesAreSerialised) {
                        if (this.valueMapping instanceof ReferenceMapping) {
                           int[] param = new int[this.valueMapping.getNumberOfDatastoreMappings()];

                           for(int i = 0; i < param.length; ++i) {
                              param[i] = i + 1;
                           }

                           value = this.valueMapping.getObject(ec, rs, param);
                        } else {
                           ResultObjectFactory rof = new PersistentClassROF(this.storeMgr, this.vmd, this.getMappingDef, false, (FetchPlan)null, this.clr.classForName(this.valueType));
                           value = rof.getObject(ec, rs);
                        }
                     } else {
                        int[] param = new int[this.valueMapping.getNumberOfDatastoreMappings()];

                        for(int i = 0; i < param.length; ++i) {
                           param[i] = i + 1;
                        }

                        if (!(this.valueMapping instanceof SerialisedPCMapping) && !(this.valueMapping instanceof SerialisedReferenceMapping) && !(this.valueMapping instanceof EmbeddedKeyPCMapping)) {
                           value = this.valueMapping.getObject(ec, rs, param);
                        } else {
                           int ownerFieldNumber = ((JoinTable)this.mapTable).getOwnerMemberMetaData().getAbsoluteFieldNumber();
                           value = this.valueMapping.getObject(ec, rs, param, ownerOP, ownerFieldNumber);
                        }
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

            return value;
         } catch (SQLException e) {
            throw new NucleusDataStoreException(Localiser.msg("056014", new Object[]{stmt}), e);
         }
      }
   }

   protected SQLStatement getSQLStatementForGet(ObjectProvider ownerOP) {
      SQLStatement sqlStmt = null;
      ClassLoaderResolver clr = ownerOP.getExecutionContext().getClassLoaderResolver();
      Class valueCls = clr.classForName(this.valueType);
      if (!this.valuesAreEmbedded && !this.valuesAreSerialised) {
         this.getMappingDef = new StatementClassMapping();
         if (!this.vmd.getFullClassName().equals(valueCls.getName())) {
            valueCls = clr.classForName(this.vmd.getFullClassName());
         }

         UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null, this.mapTable, (DatastoreIdentifier)null, this.valueMapping);
         stmtGen.setOption("selectNucleusType");
         this.getMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
         sqlStmt = stmtGen.getStatement();
         SQLTable valueSqlTbl = sqlStmt.getTable(this.valueTable, sqlStmt.getPrimaryTable().getGroupName());
         if (valueSqlTbl == null) {
            for(String valueSubclassName : this.storeMgr.getSubClassesForClass(this.valueType, true, clr)) {
               DatastoreClass valueTbl = this.storeMgr.getDatastoreClass(valueSubclassName, clr);
               if (valueTbl != null) {
                  valueSqlTbl = sqlStmt.getTable(valueTbl, sqlStmt.getPrimaryTable().getGroupName());
                  if (valueSqlTbl != null) {
                     break;
                  }
               }
            }
         }

         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.getMappingDef, ownerOP.getExecutionContext().getFetchPlan(), valueSqlTbl, this.vmd, 0);
      } else {
         sqlStmt = new SQLStatement(this.storeMgr, this.mapTable, (DatastoreIdentifier)null, (String)null);
         sqlStmt.setClassLoaderResolver(clr);
         sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.valueMapping, (String)null);
      }

      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      SQLTable ownerSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.ownerMapping);
      SQLExpression ownerExpr = exprFactory.newExpression(sqlStmt, ownerSqlTbl, this.ownerMapping);
      SQLExpression ownerVal = exprFactory.newLiteralParameter(sqlStmt, this.ownerMapping, (Object)null, "OWNER");
      sqlStmt.whereAnd(ownerExpr.eq(ownerVal), true);
      if (this.keyMapping instanceof SerialisedMapping) {
         SQLExpression keyExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.keyMapping);
         SQLExpression keyVal = exprFactory.newLiteralParameter(sqlStmt, this.keyMapping, (Object)null, "KEY");
         sqlStmt.whereAnd(new BooleanExpression(keyExpr, Expression.OP_LIKE, keyVal), true);
      } else {
         SQLExpression keyExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.keyMapping);
         SQLExpression keyVal = exprFactory.newLiteralParameter(sqlStmt, this.keyMapping, (Object)null, "KEY");
         sqlStmt.whereAnd(keyExpr.eq(keyVal), true);
      }

      int inputParamNum = 1;
      StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
      StatementMappingIndex keyIdx = new StatementMappingIndex(this.keyMapping);
      if (sqlStmt.getNumberOfUnions() > 0) {
         for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
            int[] ownerPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < ownerPositions.length; ++k) {
               ownerPositions[k] = inputParamNum++;
            }

            ownerIdx.addParameterOccurrence(ownerPositions);
            int[] keyPositions = new int[this.keyMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < keyPositions.length; ++k) {
               keyPositions[k] = inputParamNum++;
            }

            keyIdx.addParameterOccurrence(keyPositions);
         }
      } else {
         int[] ownerPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < ownerPositions.length; ++k) {
            ownerPositions[k] = inputParamNum++;
         }

         ownerIdx.addParameterOccurrence(ownerPositions);
         int[] keyPositions = new int[this.keyMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < keyPositions.length; ++k) {
            keyPositions[k] = inputParamNum++;
         }

         keyIdx.addParameterOccurrence(keyPositions);
      }

      this.getMappingParams = new StatementParameterMapping();
      this.getMappingParams.addMappingForParameter("owner", ownerIdx);
      this.getMappingParams.addMappingForParameter("key", keyIdx);
      return sqlStmt;
   }

   protected void clearInternal(ObjectProvider ownerOP) {
      try {
         ExecutionContext ec = ownerOP.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, this.clearStmt, false);

            try {
               int jdbcPosition = 1;
               BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
               sqlControl.executeStatementUpdate(ec, mconn, this.clearStmt, ps, true);
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056013", new Object[]{this.clearStmt}), e);
      }
   }

   protected void removeInternal(ObjectProvider op, Object key) {
      ExecutionContext ec = op.getExecutionContext();

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, this.removeStmt, false);

            try {
               int jdbcPosition = 1;
               jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               BackingStoreHelper.populateKeyInStatement(ec, ps, key, jdbcPosition, this.keyMapping);
               sqlControl.executeStatementUpdate(ec, mconn, this.removeStmt, ps, true);
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056012", new Object[]{this.removeStmt}), e);
      }
   }

   protected void internalUpdate(ObjectProvider ownerOP, ManagedConnection conn, boolean batched, Object key, Object value, boolean executeNow) throws MappedDatastoreException {
      ExecutionContext ec = ownerOP.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         PreparedStatement ps = sqlControl.getStatementForUpdate(conn, this.updateStmt, false);

         try {
            int jdbcPosition = 1;
            if (this.valueMapping != null) {
               jdbcPosition = BackingStoreHelper.populateValueInStatement(ec, ps, value, jdbcPosition, this.valueMapping);
            } else {
               jdbcPosition = BackingStoreHelper.populateEmbeddedValueFieldsInStatement(ownerOP, value, ps, jdbcPosition, (JoinTable)this.mapTable, this);
            }

            jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
            BackingStoreHelper.populateKeyInStatement(ec, ps, key, jdbcPosition, this.keyMapping);
            if (batched) {
               ps.addBatch();
            } else {
               sqlControl.executeStatementUpdate(ec, conn, this.updateStmt, ps, true);
            }
         } finally {
            sqlControl.closeStatement(conn, ps);
         }

      } catch (SQLException e) {
         throw new MappedDatastoreException(this.getUpdateStmt(), e);
      }
   }

   protected int[] internalPut(ObjectProvider ownerOP, ManagedConnection conn, boolean batched, Object key, Object value, boolean executeNow) throws MappedDatastoreException {
      ExecutionContext ec = ownerOP.getExecutionContext();
      SQLController sqlControl = this.storeMgr.getSQLController();

      try {
         PreparedStatement ps = sqlControl.getStatementForUpdate(conn, this.putStmt, false);

         int[] var20;
         try {
            int jdbcPosition = 1;
            if (this.valueMapping != null) {
               jdbcPosition = BackingStoreHelper.populateValueInStatement(ec, ps, value, jdbcPosition, this.valueMapping);
            } else {
               jdbcPosition = BackingStoreHelper.populateEmbeddedValueFieldsInStatement(ownerOP, value, ps, jdbcPosition, (JoinTable)this.mapTable, this);
            }

            jdbcPosition = BackingStoreHelper.populateOwnerInStatement(ownerOP, ec, ps, jdbcPosition, this);
            if (this.adapterMapping != null) {
               long nextIDAdapter = (long)this.getNextIDForAdapterColumn(ownerOP);
               this.adapterMapping.setObject(ec, ps, MappingHelper.getMappingIndices(jdbcPosition, this.adapterMapping), nextIDAdapter);
               jdbcPosition += this.adapterMapping.getNumberOfDatastoreMappings();
            }

            BackingStoreHelper.populateKeyInStatement(ec, ps, key, jdbcPosition, this.keyMapping);
            var20 = sqlControl.executeStatementUpdate(ec, conn, this.putStmt, ps, true);
         } finally {
            sqlControl.closeStatement(conn, ps);
         }

         return var20;
      } catch (SQLException e) {
         throw new MappedDatastoreException(this.getPutStmt(), e);
      }
   }

   private int getNextIDForAdapterColumn(ObjectProvider op) {
      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         int nextID;
         try {
            String stmt = this.getMaxAdapterColumnIdStmt();
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);

            try {
               int jdbcPosition = 1;
               BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!rs.next()) {
                     nextID = 1;
                  } else {
                     nextID = rs.getInt(1) + 1;
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

         return nextID;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056020", new Object[]{this.getMaxAdapterColumnIdStmt()}), e);
      }
   }

   private String getMaxAdapterColumnIdStmt() {
      StringBuilder stmt = new StringBuilder("SELECT MAX(" + this.adapterMapping.getDatastoreMapping(0).getColumn().getIdentifier().toString() + ")");
      stmt.append(" FROM ");
      stmt.append(this.mapTable.toString());
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      return stmt.toString();
   }
}
