package org.datanucleus.store.rdbms.scostore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.JDBCUtils;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.query.PersistentClassROF;
import org.datanucleus.store.rdbms.query.ResultObjectFactory;
import org.datanucleus.store.rdbms.query.StatementParameterMapping;
import org.datanucleus.store.rdbms.sql.DiscriminatorStatementGenerator;
import org.datanucleus.store.rdbms.sql.SQLStatement;
import org.datanucleus.store.rdbms.sql.SQLStatementHelper;
import org.datanucleus.store.rdbms.sql.SQLTable;
import org.datanucleus.store.rdbms.sql.StatementGenerator;
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;

class MapValueCollectionStore extends AbstractCollectionStore {
   protected final MapStore mapStore;
   protected final JavaTypeMapping keyMapping;
   private String findKeyStmt;
   private String iteratorStmtLocked = null;
   private String iteratorStmtUnlocked = null;
   private StatementClassMapping iteratorMappingDef = null;
   private StatementParameterMapping iteratorMappingParams = null;

   MapValueCollectionStore(MapTable mapTable, JoinMapStore mapStore, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.containerTable = mapTable;
      this.mapStore = mapStore;
      this.ownerMapping = mapTable.getOwnerMapping();
      this.keyMapping = mapTable.getKeyMapping();
      this.elementMapping = mapTable.getValueMapping();
      this.elementType = this.elementMapping.getType();
      this.ownerMemberMetaData = mapTable.getOwnerMemberMetaData();
      this.initialize(clr);
   }

   MapValueCollectionStore(DatastoreClass mapTable, FKMapStore mapStore, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.containerTable = mapTable;
      this.mapStore = mapStore;
      this.ownerMapping = mapStore.getOwnerMapping();
      this.keyMapping = null;
      this.elementMapping = mapStore.getValueMapping();
      this.ownerMemberMetaData = mapStore.getOwnerMemberMetaData();
      this.initialize(clr);
   }

   private void initialize(ClassLoaderResolver clr) {
      this.elementType = this.elementMapping.getType();
      this.elementsAreEmbedded = this.isEmbeddedMapping(this.elementMapping);
      this.elementsAreSerialised = this.isEmbeddedMapping(this.elementMapping);
      Class valueCls = clr.classForName(this.elementType);
      if (ClassUtils.isReferenceType(valueCls)) {
         this.emd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForImplementationOfReference(valueCls, (Object)null, clr);
      } else {
         this.emd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(valueCls, clr);
      }

      if (this.emd != null) {
         this.elementType = this.emd.getFullClassName();
         this.elementInfo = this.getElementInformationForClass();
      }

      if (this.keyMapping != null) {
         this.findKeyStmt = this.getFindKeyStmt();
      } else {
         this.findKeyStmt = null;
      }

   }

   public boolean add(ObjectProvider op, Object value, int size) {
      throw new UnsupportedOperationException("Cannot add to a map through its values collection");
   }

   public boolean addAll(ObjectProvider op, Collection values, int size) {
      throw new UnsupportedOperationException("Cannot add to a map through its values collection");
   }

   public boolean remove(ObjectProvider op, Object value, int size, boolean allowDependentField) {
      return !this.validateElementForReading(op, value) ? false : this.remove(op, value);
   }

   public boolean removeAll(ObjectProvider op, Collection values, int size) {
      throw new NucleusUserException("Cannot remove values from a map through its values collection");
   }

   public void clear(ObjectProvider op) {
      throw new NucleusUserException("Cannot clear a map through its values collection");
   }

   protected boolean remove(ObjectProvider op, Object value) {
      if (this.findKeyStmt == null) {
         throw new UnsupportedOperationException("Cannot remove from a map through its values collection");
      } else {
         Object key = null;
         boolean keyExists = false;
         ExecutionContext ec = op.getExecutionContext();

         try {
            ManagedConnection mconn = this.storeMgr.getConnection(ec);
            SQLController sqlControl = this.storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForQuery(mconn, this.findKeyStmt);

               try {
                  int jdbcPosition = 1;
                  jdbcPosition = BackingStoreHelper.populateOwnerInStatement(op, ec, ps, jdbcPosition, this);
                  BackingStoreHelper.populateElementInStatement(ec, ps, value, jdbcPosition, this.elementMapping);
                  ResultSet rs = sqlControl.executeStatementQuery(ec, mconn, this.findKeyStmt, ps);

                  try {
                     if (rs.next()) {
                        key = this.keyMapping.getObject(ec, rs, MappingHelper.getMappingIndices(1, this.keyMapping));
                        keyExists = true;
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
         } catch (SQLException e) {
            throw new NucleusDataStoreException("Request failed to check if set contains an element: " + this.findKeyStmt, e);
         }

         if (keyExists) {
            this.mapStore.remove(op, key);
            return true;
         } else {
            return false;
         }
      }
   }

   private String getFindKeyStmt() {
      StringBuilder stmt = new StringBuilder("SELECT ");

      for(int i = 0; i < this.keyMapping.getNumberOfDatastoreMappings(); ++i) {
         if (i > 0) {
            stmt.append(",");
         }

         stmt.append(this.keyMapping.getDatastoreMapping(i).getColumn().getIdentifier().toString());
      }

      stmt.append(" FROM ");
      stmt.append(this.containerTable.toString());
      stmt.append(" WHERE ");
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.ownerMapping, (String)null, true);
      BackingStoreHelper.appendWhereClauseForMapping(stmt, this.elementMapping, (String)null, false);
      return stmt.toString();
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

         CollectionStoreIterator var12;
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
                  ResultObjectFactory rof = null;
                  if (!this.elementsAreEmbedded && !this.elementsAreSerialised) {
                     ResultObjectFactory var42 = new PersistentClassROF(this.storeMgr, this.emd, this.iteratorMappingDef, false, (FetchPlan)null, this.clr.classForName(this.elementType));
                     var12 = new CollectionStoreIterator(ownerOP, rs, var42, this);
                     return var12;
                  }

                  var12 = new CollectionStoreIterator(ownerOP, rs, (ResultObjectFactory)null, this);
               } finally {
                  rs.close();
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

         return var12;
      } catch (SQLException e) {
         throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
      } catch (MappedDatastoreException e) {
         throw new NucleusDataStoreException(Localiser.msg("056006", new Object[]{stmt}), e);
      }
   }

   protected SQLStatement getSQLStatementForIterator(ObjectProvider ownerOP) {
      SQLStatement sqlStmt = null;
      ClassLoaderResolver clr = ownerOP.getExecutionContext().getClassLoaderResolver();
      Class valueCls = clr.classForName(this.elementType);
      SQLTable containerSqlTbl = null;
      MapMetaData.MapType mapType = this.getOwnerMemberMetaData().getMap().getMapType();
      if (this.emd != null && this.emd.getDiscriminatorStrategyForTable() != null && this.emd.getDiscriminatorStrategyForTable() != DiscriminatorStrategy.NONE) {
         if (!ClassUtils.isReferenceType(valueCls)) {
            StatementGenerator stmtGen = new DiscriminatorStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null);
            sqlStmt = stmtGen.getStatement();
         } else {
            String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(this.elementType, clr);
            Class[] cls = new Class[clsNames.length];

            for(int j = 0; j < clsNames.length; ++j) {
               cls[j] = clr.classForName(clsNames[j]);
            }

            StatementGenerator stmtGen = new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null);
            sqlStmt = stmtGen.getStatement();
         }

         this.iterateUsingDiscriminator = true;
         if (mapType == MapType.MAP_TYPE_VALUE_IN_KEY) {
            JavaTypeMapping valueIdMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
            containerSqlTbl = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), valueIdMapping, this.containerTable, (String)null, this.elementMapping, (Object[])null, (String)null);
            this.iteratorMappingDef = new StatementClassMapping();
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
         } else if (mapType == MapType.MAP_TYPE_KEY_IN_VALUE) {
            containerSqlTbl = sqlStmt.getPrimaryTable();
            this.iteratorMappingDef = new StatementClassMapping();
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
         } else {
            JavaTypeMapping valueIdMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
            containerSqlTbl = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), valueIdMapping, this.containerTable, (String)null, this.elementMapping, (Object[])null, (String)null);
            this.iteratorMappingDef = new StatementClassMapping();
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
         }
      } else if (mapType == MapType.MAP_TYPE_VALUE_IN_KEY) {
         if (this.emd != null) {
            this.iteratorMappingDef = new StatementClassMapping();
            UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null);
            stmtGen.setOption("selectNucleusType");
            this.iteratorMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
            sqlStmt = stmtGen.getStatement();
            JavaTypeMapping valueIdMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
            containerSqlTbl = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), valueIdMapping, this.containerTable, (String)null, this.elementMapping, (Object[])null, (String)null);
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
         } else {
            sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
            sqlStmt.setClassLoaderResolver(clr);
            containerSqlTbl = sqlStmt.getPrimaryTable();
            SQLTable elemSqlTblForValue = containerSqlTbl;
            if (this.elementMapping.getTable() != containerSqlTbl.getTable()) {
               elemSqlTblForValue = sqlStmt.getTableForDatastoreContainer(this.elementMapping.getTable());
               if (elemSqlTblForValue == null) {
                  elemSqlTblForValue = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), sqlStmt.getPrimaryTable().getTable().getIdMapping(), this.elementMapping.getTable(), (String)null, this.elementMapping.getTable().getIdMapping(), (Object[])null, (String)null);
               }
            }

            sqlStmt.select(elemSqlTblForValue, (JavaTypeMapping)this.elementMapping, (String)null);
         }
      } else if (mapType == MapType.MAP_TYPE_KEY_IN_VALUE) {
         this.iteratorMappingDef = new StatementClassMapping();
         UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null);
         stmtGen.setOption("selectNucleusType");
         this.iteratorMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
         sqlStmt = stmtGen.getStatement();
         containerSqlTbl = sqlStmt.getPrimaryTable();
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
      } else if (this.emd != null) {
         this.iteratorMappingDef = new StatementClassMapping();
         UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, valueCls, true, (DatastoreIdentifier)null, (String)null);
         stmtGen.setOption("selectNucleusType");
         this.iteratorMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
         sqlStmt = stmtGen.getStatement();
         JavaTypeMapping valueIdMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
         containerSqlTbl = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), valueIdMapping, this.containerTable, (String)null, this.elementMapping, (Object[])null, (String)null);
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
      } else {
         sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
         containerSqlTbl = sqlStmt.getPrimaryTable();
         sqlStmt.select(sqlStmt.getPrimaryTable(), (JavaTypeMapping)this.elementMapping, (String)null);
      }

      SQLExpressionFactory exprFactory = this.storeMgr.getSQLExpressionFactory();
      SQLTable ownerSqlTbl = SQLStatementHelper.getSQLTableForMappingOfTable(sqlStmt, containerSqlTbl, this.ownerMapping);
      SQLExpression ownerExpr = exprFactory.newExpression(sqlStmt, ownerSqlTbl, this.ownerMapping);
      SQLExpression ownerVal = exprFactory.newLiteralParameter(sqlStmt, this.ownerMapping, (Object)null, "OWNER");
      sqlStmt.whereAnd(ownerExpr.eq(ownerVal), true);
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
}
