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
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.MapMetaData;
import org.datanucleus.metadata.MapMetaData.MapType;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.exceptions.MappedDatastoreException;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
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
import org.datanucleus.store.rdbms.sql.UnionStatementGenerator;
import org.datanucleus.store.rdbms.sql.expression.SQLExpression;
import org.datanucleus.store.rdbms.sql.expression.SQLExpressionFactory;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.MapTable;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;

class MapKeySetStore extends AbstractSetStore {
   protected final MapStore mapStore;
   private String iteratorStmtLocked = null;
   private String iteratorStmtUnlocked = null;
   private StatementClassMapping iteratorMappingDef = null;
   private StatementParameterMapping iteratorMappingParams = null;

   MapKeySetStore(MapTable mapTable, JoinMapStore mapStore, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.mapStore = mapStore;
      this.containerTable = mapTable;
      this.ownerMemberMetaData = mapTable.getOwnerMemberMetaData();
      this.ownerMapping = mapTable.getOwnerMapping();
      this.elementMapping = mapTable.getKeyMapping();
      this.initialize(clr);
   }

   MapKeySetStore(DatastoreClass mapTable, FKMapStore mapStore, ClassLoaderResolver clr) {
      super(mapTable.getStoreManager(), clr);
      this.mapStore = mapStore;
      this.containerTable = mapTable;
      this.ownerMemberMetaData = mapStore.getOwnerMemberMetaData();
      this.ownerMapping = mapStore.getOwnerMapping();
      this.elementMapping = mapStore.getKeyMapping();
      this.initialize(clr);
   }

   private void initialize(ClassLoaderResolver clr) {
      this.elementType = this.elementMapping.getType();
      this.elementsAreEmbedded = this.isEmbeddedMapping(this.elementMapping);
      this.elementsAreSerialised = this.isEmbeddedMapping(this.elementMapping);
      Class elementCls = clr.classForName(this.elementType);
      if (ClassUtils.isReferenceType(elementCls)) {
         this.emd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForImplementationOfReference(elementCls, (Object)null, clr);
      } else {
         this.emd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(elementCls, clr);
      }

      if (this.emd != null) {
         this.elementType = this.emd.getFullClassName();
         this.elementInfo = this.getElementInformationForClass();
      }

   }

   public boolean add(ObjectProvider op, Object key, int size) {
      throw new UnsupportedOperationException("Cannot add to a map through its key set");
   }

   public boolean addAll(ObjectProvider op, Collection keys, int size) {
      throw new UnsupportedOperationException("Cannot add to a map through its key set");
   }

   public boolean remove(ObjectProvider op, Object key, int size, boolean allowDependentField) {
      throw new UnsupportedOperationException("Cannot remove from a map through its key set");
   }

   public boolean removeAll(ObjectProvider op, Collection keys, int size) {
      throw new UnsupportedOperationException("Cannot remove from a map through its key set");
   }

   public void clear(ObjectProvider op) {
      throw new UnsupportedOperationException("Cannot clear a map through its key set");
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
      Class keyCls = clr.classForName(this.elementType);
      SQLTable containerSqlTbl = null;
      MapMetaData.MapType mapType = this.getOwnerMemberMetaData().getMap().getMapType();
      if (this.emd != null && this.emd.getDiscriminatorStrategyForTable() != null && this.emd.getDiscriminatorStrategyForTable() != DiscriminatorStrategy.NONE) {
         if (!ClassUtils.isReferenceType(keyCls)) {
            sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, clr.classForName(this.elementInfo[0].getClassName()), true, (DatastoreIdentifier)null, (String)null)).getStatement();
         } else {
            String[] clsNames = this.storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(this.elementType, clr);
            Class[] cls = new Class[clsNames.length];

            for(int j = 0; j < clsNames.length; ++j) {
               cls[j] = clr.classForName(clsNames[j]);
            }

            sqlStmt = (new DiscriminatorStatementGenerator(this.storeMgr, clr, cls, true, (DatastoreIdentifier)null, (String)null)).getStatement();
         }

         containerSqlTbl = sqlStmt.getPrimaryTable();
         this.iterateUsingDiscriminator = true;
         if (mapType == MapType.MAP_TYPE_VALUE_IN_KEY) {
            containerSqlTbl = sqlStmt.getPrimaryTable();
            this.iteratorMappingDef = new StatementClassMapping();
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
         } else {
            JavaTypeMapping keyIdMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
            containerSqlTbl = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), keyIdMapping, this.containerTable, (String)null, this.elementMapping, (Object[])null, (String)null);
            this.iteratorMappingDef = new StatementClassMapping();
            SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
         }
      } else if (mapType == MapType.MAP_TYPE_VALUE_IN_KEY) {
         this.iteratorMappingDef = new StatementClassMapping();
         UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, keyCls, true, (DatastoreIdentifier)null, (String)null);
         stmtGen.setOption("selectNucleusType");
         this.iteratorMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
         sqlStmt = stmtGen.getStatement();
         containerSqlTbl = sqlStmt.getPrimaryTable();
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
      } else if (this.emd != null) {
         this.iteratorMappingDef = new StatementClassMapping();
         UnionStatementGenerator stmtGen = new UnionStatementGenerator(this.storeMgr, clr, keyCls, true, (DatastoreIdentifier)null, (String)null);
         stmtGen.setOption("selectNucleusType");
         this.iteratorMappingDef.setNucleusTypeColumnName("NUCLEUS_TYPE");
         sqlStmt = stmtGen.getStatement();
         JavaTypeMapping keyIdMapping = sqlStmt.getPrimaryTable().getTable().getIdMapping();
         containerSqlTbl = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), keyIdMapping, this.containerTable, (String)null, this.elementMapping, (Object[])null, (String)null);
         SQLStatementHelper.selectFetchPlanOfSourceClassInStatement(sqlStmt, this.iteratorMappingDef, ownerOP.getExecutionContext().getFetchPlan(), sqlStmt.getPrimaryTable(), this.emd, 0);
      } else {
         sqlStmt = new SQLStatement(this.storeMgr, this.containerTable, (DatastoreIdentifier)null, (String)null);
         sqlStmt.setClassLoaderResolver(clr);
         containerSqlTbl = sqlStmt.getPrimaryTable();
         SQLTable elemSqlTblForKey = containerSqlTbl;
         if (this.elementMapping.getTable() != containerSqlTbl.getTable()) {
            elemSqlTblForKey = sqlStmt.getTableForDatastoreContainer(this.elementMapping.getTable());
            if (elemSqlTblForKey == null) {
               elemSqlTblForKey = sqlStmt.innerJoin(sqlStmt.getPrimaryTable(), sqlStmt.getPrimaryTable().getTable().getIdMapping(), this.elementMapping.getTable(), (String)null, this.elementMapping.getTable().getIdMapping(), (Object[])null, (String)null);
            }
         }

         sqlStmt.select(elemSqlTblForKey, (JavaTypeMapping)this.elementMapping, (String)null);
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
