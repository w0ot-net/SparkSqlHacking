package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedKeyPCMapping;
import org.datanucleus.store.rdbms.mapping.java.EmbeddedValuePCMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedPCMapping;
import org.datanucleus.store.rdbms.mapping.java.SerialisedReferenceMapping;
import org.datanucleus.store.rdbms.query.StatementParameterMapping;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.SetStore;

class MapEntrySetStore extends BaseContainerStore implements SetStore {
   protected Table mapTable;
   protected MapStore mapStore;
   protected JavaTypeMapping keyMapping;
   protected JavaTypeMapping valueMapping;
   private String sizeStmt;
   private String iteratorStmtLocked = null;
   private String iteratorStmtUnlocked = null;
   private StatementParameterMapping iteratorMappingParams = null;
   private int[] iteratorKeyResultCols = null;
   private int[] iteratorValueResultCols = null;

   MapEntrySetStore(MapTable mapTable, JoinMapStore mapStore, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.mapTable = mapTable;
      this.mapStore = mapStore;
      this.ownerMapping = mapTable.getOwnerMapping();
      this.keyMapping = mapTable.getKeyMapping();
      this.valueMapping = mapTable.getValueMapping();
      this.ownerMemberMetaData = mapTable.getOwnerMemberMetaData();
   }

   MapEntrySetStore(DatastoreClass mapTable, FKMapStore mapStore, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.mapTable = mapTable;
      this.mapStore = mapStore;
      this.ownerMapping = mapStore.getOwnerMapping();
      this.keyMapping = mapStore.getKeyMapping();
      this.valueMapping = mapStore.getValueMapping();
      this.ownerMemberMetaData = mapStore.getOwnerMemberMetaData();
   }

   public boolean hasOrderMapping() {
      return false;
   }

   public MapStore getMapStore() {
      return this.mapStore;
   }

   public JavaTypeMapping getOwnerMapping() {
      return this.ownerMapping;
   }

   public JavaTypeMapping getKeyMapping() {
      return this.keyMapping;
   }

   public JavaTypeMapping getValueMapping() {
      return this.valueMapping;
   }

   public boolean updateEmbeddedElement(ObjectProvider sm, Map.Entry element, int fieldNumber, Object value) {
      return false;
   }

   protected boolean validateElementType(Object element) {
      return element instanceof Map.Entry;
   }

   public void update(ObjectProvider op, Collection coll) {
      this.clear(op);
      this.addAll(op, coll, 0);
   }

   public boolean contains(ObjectProvider op, Object element) {
      if (!this.validateElementType(element)) {
         return false;
      } else {
         Map.Entry entry = (Map.Entry)element;
         return this.mapStore.containsKey(op, entry.getKey());
      }
   }

   public boolean add(ObjectProvider op, Map.Entry entry, int size) {
      throw new UnsupportedOperationException("Cannot add to a map through its entry set");
   }

   public boolean addAll(ObjectProvider sm, Collection entries, int size) {
      throw new UnsupportedOperationException("Cannot add to a map through its entry set");
   }

   public boolean remove(ObjectProvider op, Object element, int size, boolean allowDependentField) {
      if (!this.validateElementType(element)) {
         return false;
      } else {
         Map.Entry entry = (Map.Entry)element;
         Object removed = this.mapStore.remove(op, entry.getKey());
         return removed == null ? entry.getValue() == null : removed.equals(entry.getValue());
      }
   }

   public boolean removeAll(ObjectProvider op, Collection elements, int size) {
      if (elements != null && elements.size() != 0) {
         Iterator iter = elements.iterator();

         boolean modified;
         Map.Entry entry;
         Object removed;
         for(modified = false; iter.hasNext(); modified = removed == null ? entry.getValue() == null : removed.equals(entry.getValue())) {
            Object element = iter.next();
            entry = (Map.Entry)element;
            removed = this.mapStore.remove(op, entry.getKey());
         }

         return modified;
      } else {
         return false;
      }
   }

   public void clear(ObjectProvider op) {
      this.mapStore.clear(op);
   }

   public int size(ObjectProvider op) {
      String stmt = this.getSizeStmt();

      try {
         ExecutionContext ec = op.getExecutionContext();
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         int numRows;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);

            try {
               int jdbcPosition = 1;
               BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  if (!rs.next()) {
                     throw new NucleusDataStoreException("Size request returned no result row: " + stmt);
                  }

                  numRows = rs.getInt(1);
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

         return numRows;
      } catch (SQLException e) {
         throw new NucleusDataStoreException("Size request failed: " + stmt, e);
      }
   }

   private String getSizeStmt() {
      if (this.sizeStmt == null) {
         StringBuilder stmt = new StringBuilder("SELECT COUNT(*) FROM ");
         stmt.append(this.mapTable.toString());
         stmt.append(" WHERE ");
         BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
         if (this.keyMapping != null) {
            for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
               stmt.append(" AND ");
               stmt.append(this.keyMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
               stmt.append(" IS NOT NULL");
            }
         }

         this.sizeStmt = stmt.toString();
      }

      return this.sizeStmt;
   }

   public Iterator iterator(ObjectProvider ownerOP) {
      ExecutionContext ec = ownerOP.getExecutionContext();
      if (this.iteratorStmtLocked == null) {
         synchronized(this) {
            SQLStatement sqlStmt = this.getSQLStatementForIterator(ownerOP);
            this.iteratorStmtUnlocked = sqlStmt.getSelectStatement().toSQL();
            sqlStmt.addExtension("lock-for-update", true);
            this.iteratorStmtLocked = sqlStmt.getSelectStatement().toSQL();
         }
      }

      Transaction tx = ec.getTransaction();
      String stmt = tx.getSerializeRead() != null && tx.getSerializeRead() ? this.iteratorStmtLocked : this.iteratorStmtUnlocked;

      try {
         ManagedConnection mconn = this.storeMgr.getConnection(ec);
         SQLController sqlControl = this.storeMgr.getSQLController();

         SetIterator var11;
         try {
            PreparedStatement ps = sqlControl.getStatementForQuery(mconn, stmt);
            StatementMappingIndex ownerIdx = this.iteratorMappingParams.getMappingForParameter("owner");
            int numParams = ownerIdx.getNumberOfParameterOccurrences();

            for(int paramInstance = 0; paramInstance < numParams; ++paramInstance) {
               ownerIdx.getMapping().setObject(ec, ps, ownerIdx.getParameterPositionsForOccurrence(paramInstance), ownerOP.getObject());
            }

            try {
               ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, stmt, ps);

               try {
                  var11 = new SetIterator(ownerOP, this, this.ownerMemberMetaData, rs, this.iteratorKeyResultCols, this.iteratorValueResultCols) {
                     protected boolean next(Object rs) throws MappedDatastoreException {
                        try {
                           return ((ResultSet)rs).next();
                        } catch (SQLException e) {
                           throw new MappedDatastoreException("SQLException", e);
                        }
                     }
                  };
               } finally {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return var11;
      } catch (SQLException e) {
         throw new NucleusDataStoreException("Iteration request failed: " + stmt, e);
      } catch (MappedDatastoreException e) {
         throw new NucleusDataStoreException("Iteration request failed: " + stmt, e);
      }
   }

   protected SQLStatement getSQLStatementForIterator(ObjectProvider ownerOP) {
      SQLStatement sqlStmt = new SQLStatement(this.storeMgr, this.mapTable, (DatastoreIdentifier)null, (String)null);
      sqlStmt.setClassLoaderResolver(this.clr);
      MapMetaData.MapType mapType = this.getOwnerMemberMetaData().getMap().getMapType();
      if (mapType != MapType.MAP_TYPE_JOIN && mapType != MapType.MAP_TYPE_KEY_IN_VALUE && mapType == MapType.MAP_TYPE_VALUE_IN_KEY) {
      }

      SQLTable entrySqlTblForKey = sqlStmt.getPrimaryTable();
      if (this.keyMapping.getTable() != this.mapTable) {
         entrySqlTblForKey = sqlStmt.getTableForDatastoreContainer(this.keyMapping.getTable());
         if (entrySqlTblForKey == null) {
            entrySqlTblForKey = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), sqlStmt.getPrimaryTable().getTable().getIdMapping(), this.keyMapping.getTable(), (String)null, this.keyMapping.getTable().getIdMapping(), (Object[])null, (String)null);
         }
      }

      this.iteratorKeyResultCols = sqlStmt.select(entrySqlTblForKey, (JavaTypeMapping)this.keyMapping, (String)null);
      SQLTable entrySqlTblForVal = sqlStmt.getPrimaryTable();
      if (this.valueMapping.getTable() != this.mapTable) {
         entrySqlTblForVal = sqlStmt.getTableForDatastoreContainer(this.valueMapping.getTable());
         if (entrySqlTblForVal == null) {
            entrySqlTblForVal = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), sqlStmt.getPrimaryTable().getTable().getIdMapping(), this.valueMapping.getTable(), (String)null, this.valueMapping.getTable().getIdMapping(), (Object[])null, (String)null);
         }
      }

      this.iteratorValueResultCols = sqlStmt.select(entrySqlTblForVal, (JavaTypeMapping)this.valueMapping, (String)null);
      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      SQLTable ownerSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, sqlStmt.getPrimaryTable(), this.ownerMapping);
      SQLExpression ownerExpr = exprFactory.newExpression(sqlStmt, ownerSqlTbl, this.ownerMapping);
      SQLExpression ownerVal = exprFactory.newLiteralParameter(sqlStmt, this.ownerMapping, (Object)null, "OWNER");
      sqlStmt.whereAnd(ownerExpr.eq(ownerVal), true);
      SQLExpression keyExpr = exprFactory.newExpression(sqlStmt, sqlStmt.getPrimaryTable(), this.keyMapping);
      SQLExpression nullExpr = exprFactory.newLiteral(sqlStmt, (JavaTypeMapping)null, (Object)null);
      sqlStmt.whereAnd(keyExpr.ne(nullExpr), true);
      int inputParamNum = 1;
      StatementMappingIndex ownerIdx = new StatementMappingIndex(this.ownerMapping);
      if (sqlStmt.getNumberOfUnions() > 0) {
         for(int j = 0; j < sqlStmt.getNumberOfUnions() + 1; ++j) {
            int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

            for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
               paramPositions[k] = inputParamNum++;
            }

            ownerIdx.addParameterOccurrence(paramPositions);
         }
      } else {
         int[] paramPositions = new int[this.ownerMapping.getNumberOfDatastoreMappings()];

         for(int k = 0; k < this.ownerMapping.getNumberOfDatastoreMappings(); ++k) {
            paramPositions[k] = inputParamNum++;
         }

         ownerIdx.addParameterOccurrence(paramPositions);
      }

      this.iteratorMappingParams = new StatementParameterMapping();
      this.iteratorMappingParams.addMappingForParameter("owner", ownerIdx);
      return sqlStmt;
   }

   public abstract static class SetIterator implements Iterator {
      private final ObjectProvider op;
      private final Iterator delegate;
      private Map.Entry lastElement = null;
      private final MapEntrySetStore setStore;

      protected SetIterator(ObjectProvider op, MapEntrySetStore setStore, AbstractMemberMetaData ownerMmd, ResultSet rs, int[] keyResultCols, int[] valueResultCols) throws MappedDatastoreException {
         this.op = op;
         this.setStore = setStore;
         ExecutionContext ec = op.getExecutionContext();

         ArrayList results;
         Object key;
         Object value;
         for(results = new ArrayList(); this.next(rs); results.add(new EntryImpl(op, key, value, setStore.getMapStore()))) {
            key = null;
            value = null;
            int ownerFieldNum = ownerMmd != null ? ownerMmd.getAbsoluteFieldNumber() : -1;
            JavaTypeMapping keyMapping = setStore.getKeyMapping();
            if (!(keyMapping instanceof EmbeddedKeyPCMapping) && !(keyMapping instanceof SerialisedPCMapping) && !(keyMapping instanceof SerialisedReferenceMapping)) {
               key = keyMapping.getObject(ec, rs, keyResultCols);
            } else {
               key = keyMapping.getObject(ec, rs, keyResultCols, op, ownerFieldNum);
            }

            JavaTypeMapping valueMapping = setStore.getValueMapping();
            if (!(valueMapping instanceof EmbeddedValuePCMapping) && !(valueMapping instanceof SerialisedPCMapping) && !(valueMapping instanceof SerialisedReferenceMapping)) {
               value = valueMapping.getObject(ec, rs, valueResultCols);
            } else {
               value = valueMapping.getObject(ec, rs, valueResultCols, op, ownerFieldNum);
            }
         }

         this.delegate = results.iterator();
      }

      public boolean hasNext() {
         return this.delegate.hasNext();
      }

      public Object next() {
         this.lastElement = (Map.Entry)this.delegate.next();
         return this.lastElement;
      }

      public synchronized void remove() {
         if (this.lastElement == null) {
            throw new IllegalStateException("No entry to remove");
         } else {
            this.setStore.getMapStore().remove(this.op, this.lastElement.getKey());
            this.delegate.remove();
            this.lastElement = null;
         }
      }

      protected abstract boolean next(Object var1) throws MappedDatastoreException;
   }

   private static class EntryImpl implements Map.Entry {
      private final ObjectProvider ownerOP;
      private final Object key;
      private final Object value;
      private final MapStore mapStore;

      public EntryImpl(ObjectProvider op, Object key, Object value, MapStore mapStore) {
         this.ownerOP = op;
         this.key = key;
         this.value = value;
         this.mapStore = mapStore;
      }

      public int hashCode() {
         return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
      }

      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof Map.Entry)) {
            return false;
         } else {
            boolean var10000;
            label43: {
               label29: {
                  Map.Entry e = (Map.Entry)o;
                  if (this.key == null) {
                     if (e.getKey() != null) {
                        break label29;
                     }
                  } else if (!this.key.equals(e.getKey())) {
                     break label29;
                  }

                  if (this.value == null) {
                     if (e.getValue() == null) {
                        break label43;
                     }
                  } else if (this.value.equals(e.getValue())) {
                     break label43;
                  }
               }

               var10000 = false;
               return var10000;
            }

            var10000 = true;
            return var10000;
         }
      }

      public Object getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         return this.mapStore.put(this.ownerOP, this.key, value);
      }
   }
}
