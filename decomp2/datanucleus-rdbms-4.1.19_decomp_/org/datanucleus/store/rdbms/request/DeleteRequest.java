package org.datanucleus.store.rdbms.request;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ForeignKeyMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InterfaceMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class DeleteRequest extends Request {
   private final MappingCallbacks[] callbacks;
   private final String deleteStmt;
   private final String deleteStmtOptimistic;
   private StatementMappingDefinition mappingStatementIndex;
   private StatementMappingIndex multitenancyStatementMapping;
   private final int[] whereFieldNumbers;
   private final AbstractMemberMetaData[] oneToOneNonOwnerFields;
   protected AbstractClassMetaData cmd = null;
   protected VersionMetaData versionMetaData = null;
   protected boolean versionChecks = false;

   public DeleteRequest(DatastoreClass table, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      super(table);
      this.cmd = cmd;
      this.versionMetaData = table.getVersionMetaData();
      if (this.versionMetaData != null && this.versionMetaData.getVersionStrategy() != VersionStrategy.NONE) {
         this.versionChecks = true;
      }

      this.mappingStatementIndex = new StatementMappingDefinition();
      DeleteMappingConsumer consumer = new DeleteMappingConsumer(clr, cmd);
      table.provideNonPrimaryKeyMappings(consumer);
      consumer.setWhereClauseConsumption();
      if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         table.providePrimaryKeyMappings(consumer);
      } else if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         table.provideDatastoreIdMappings(consumer);
      } else {
         AbstractMemberMetaData[] mmds = cmd.getManagedMembers();
         table.provideMappingsForMembers(consumer, mmds, false);
      }

      table.provideMultitenancyMapping(consumer);
      this.deleteStmt = consumer.getStatement();
      if (this.versionMetaData != null) {
         if (this.versionMetaData.getFieldName() != null) {
            AbstractMemberMetaData[] versionFmds = new AbstractMemberMetaData[1];
            versionFmds[0] = cmd.getMetaDataForMember(this.versionMetaData.getFieldName());
            table.provideMappingsForMembers(consumer, versionFmds, false);
         } else {
            table.provideVersionMappings(consumer);
         }
      }

      this.deleteStmtOptimistic = consumer.getStatement();
      this.whereFieldNumbers = consumer.getWhereFieldNumbers();
      this.callbacks = (MappingCallbacks[])consumer.getMappingCallBacks().toArray(new MappingCallbacks[consumer.getMappingCallBacks().size()]);
      this.oneToOneNonOwnerFields = consumer.getOneToOneNonOwnerFields();
   }

   public void execute(ObjectProvider op) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("052210", new Object[]{op.getObjectAsPrintable(), this.table}));
      }

      ClassLoaderResolver clr = op.getExecutionContext().getClassLoaderResolver();
      HashSet relatedObjectsToDelete = null;

      for(int i = 0; i < this.callbacks.length; ++i) {
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            NucleusLogger.PERSISTENCE.debug(Localiser.msg("052212", new Object[]{op.getObjectAsPrintable(), ((JavaTypeMapping)this.callbacks[i]).getMemberMetaData().getFullFieldName()}));
         }

         this.callbacks[i].preDelete(op);
         JavaTypeMapping mapping = (JavaTypeMapping)this.callbacks[i];
         AbstractMemberMetaData mmd = mapping.getMemberMetaData();
         RelationType relationType = mmd.getRelationType(clr);
         if (mmd.isDependent() && (relationType == RelationType.ONE_TO_ONE_UNI || relationType == RelationType.ONE_TO_ONE_BI && mmd.getMappedBy() == null)) {
            try {
               op.isLoaded(mmd.getAbsoluteFieldNumber());
               Object relatedPc = op.provideField(mmd.getAbsoluteFieldNumber());
               boolean relatedObjectDeleted = op.getExecutionContext().getApiAdapter().isDeleted(relatedPc);
               if (!relatedObjectDeleted) {
                  if (relatedObjectsToDelete == null) {
                     relatedObjectsToDelete = new HashSet();
                  }

                  relatedObjectsToDelete.add(relatedPc);
               }
            } catch (Exception var26) {
            }
         }
      }

      if (this.oneToOneNonOwnerFields != null && this.oneToOneNonOwnerFields.length > 0) {
         for(int i = 0; i < this.oneToOneNonOwnerFields.length; ++i) {
            AbstractMemberMetaData relatedFmd = this.oneToOneNonOwnerFields[i];
            this.updateOneToOneBidirectionalOwnerObjectForField(op, relatedFmd);
         }
      }

      String stmt = null;
      ExecutionContext ec = op.getExecutionContext();
      RDBMSStoreManager storeMgr = this.table.getStoreManager();
      boolean optimisticChecks = this.versionMetaData != null && ec.getTransaction().getOptimistic() && this.versionChecks;
      if (optimisticChecks) {
         stmt = this.deleteStmtOptimistic;
      } else {
         stmt = this.deleteStmt;
      }

      try {
         ManagedConnection mconn = storeMgr.getConnection(ec);
         SQLController sqlControl = storeMgr.getSQLController();

         try {
            boolean batch = true;
            if (optimisticChecks || !ec.getTransaction().isActive()) {
               batch = false;
            }

            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, batch);

            try {
               if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
                  StatementMappingIndex mapIdx = this.mappingStatementIndex.getWhereDatastoreId();

                  for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
                     this.table.getDatastoreIdMapping().setObject(ec, ps, mapIdx.getParameterPositionsForOccurrence(i), op.getInternalObjectId());
                  }
               } else {
                  StatementClassMapping mappingDefinition = new StatementClassMapping();
                  StatementMappingIndex[] idxs = this.mappingStatementIndex.getWhereFields();

                  for(int i = 0; i < idxs.length; ++i) {
                     if (idxs[i] != null) {
                        mappingDefinition.addMappingForMember(i, idxs[i]);
                     }
                  }

                  op.provideFields(this.whereFieldNumbers, storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition));
               }

               if (this.multitenancyStatementMapping != null) {
                  this.table.getMultitenancyMapping().setObject(ec, ps, this.multitenancyStatementMapping.getParameterPositionsForOccurrence(0), storeMgr.getStringProperty("datanucleus.TenantID"));
               }

               if (optimisticChecks) {
                  JavaTypeMapping verMapping = this.mappingStatementIndex.getWhereVersion().getMapping();
                  Object currentVersion = op.getTransactionalVersion();
                  if (currentVersion == null) {
                     String msg = Localiser.msg("052202", new Object[]{op.getInternalObjectId(), this.table});
                     NucleusLogger.PERSISTENCE.error(msg);
                     throw new NucleusException(msg);
                  }

                  StatementMappingIndex mapIdx = this.mappingStatementIndex.getWhereVersion();

                  for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
                     verMapping.setObject(ec, ps, mapIdx.getParameterPositionsForOccurrence(i), currentVersion);
                  }
               }

               int[] rcs = sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, !batch);
               if (optimisticChecks && rcs[0] == 0) {
                  String msg = Localiser.msg("052203", new Object[]{op.getObjectAsPrintable(), op.getInternalObjectId(), "" + op.getTransactionalVersion()});
                  NucleusLogger.DATASTORE.error(msg);
                  throw new NucleusOptimisticException(msg, op.getObject());
               }

               if (relatedObjectsToDelete != null && !relatedObjectsToDelete.isEmpty()) {
                  for(Object relatedObject : relatedObjectsToDelete) {
                     ec.deleteObjectInternal(relatedObject);
                  }
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }

      } catch (SQLException var29) {
         SQLException e = var29;
         String msg = Localiser.msg("052211", new Object[]{op.getObjectAsPrintable(), stmt, var29.getMessage()});
         NucleusLogger.DATASTORE_PERSIST.warn(msg);
         List exceptions = new ArrayList();
         exceptions.add(var29);

         while((e = e.getNextException()) != null) {
            exceptions.add(e);
         }

         throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
      }
   }

   private void updateOneToOneBidirectionalOwnerObjectForField(ObjectProvider op, AbstractMemberMetaData fmd) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("052217", new Object[]{op.getObjectAsPrintable(), fmd.getFullFieldName()}));
      }

      RDBMSStoreManager storeMgr = this.table.getStoreManager();
      ExecutionContext ec = op.getExecutionContext();
      ClassLoaderResolver clr = ec.getClassLoaderResolver();
      AbstractMemberMetaData[] relatedMmds = fmd.getRelatedMemberMetaData(clr);
      boolean checkFK = true;
      if (ec.getStringProperty("datanucleus.deletionPolicy").equals("JDO2")) {
         checkFK = false;
      }

      if (checkFK) {
         for(int i = 0; i < relatedMmds.length; ++i) {
            ForeignKeyMetaData relFkmd = relatedMmds[i].getForeignKeyMetaData();
            if (relFkmd != null && relFkmd.getDeleteAction() != null) {
               return;
            }
         }
      }

      String fullClassName = ((AbstractClassMetaData)relatedMmds[0].getParent()).getFullClassName();
      String[] classes;
      if ((AbstractClassMetaData)relatedMmds[0].getParent() instanceof InterfaceMetaData) {
         classes = storeMgr.getNucleusContext().getMetaDataManager().getClassesImplementingInterface(fullClassName, clr);
      } else {
         classes = new String[]{fullClassName};
      }

      Set datastoreClasses = new HashSet();

      for(int i = 0; i < classes.length; ++i) {
         datastoreClasses.add(storeMgr.getDatastoreClass(classes[i], clr));
      }

      for(DatastoreClass refTable : datastoreClasses) {
         JavaTypeMapping refMapping = refTable.getMemberMapping(fmd.getMappedBy());
         if (refMapping.isNullable()) {
            StringBuilder clearLinkStmt = new StringBuilder("UPDATE " + refTable.toString() + " SET ");

            for(int j = 0; j < refMapping.getNumberOfDatastoreMappings(); ++j) {
               if (j > 0) {
                  clearLinkStmt.append(",");
               }

               clearLinkStmt.append(refMapping.getDatastoreMapping(j).getColumn().getIdentifier());
               clearLinkStmt.append("=NULL");
            }

            clearLinkStmt.append(" WHERE ");

            for(int j = 0; j < refMapping.getNumberOfDatastoreMappings(); ++j) {
               if (j > 0) {
                  clearLinkStmt.append(" AND ");
               }

               clearLinkStmt.append(refMapping.getDatastoreMapping(j).getColumn().getIdentifier());
               clearLinkStmt.append("=?");
            }

            try {
               ManagedConnection mconn = storeMgr.getConnection(ec);
               SQLController sqlControl = storeMgr.getSQLController();

               try {
                  PreparedStatement ps = null;

                  try {
                     ps = sqlControl.getStatementForUpdate(mconn, clearLinkStmt.toString(), false);
                     refMapping.setObject(ec, ps, MappingHelper.getMappingIndices(1, refMapping), op.getObject());
                     sqlControl.executeStatementUpdate(ec, mconn, clearLinkStmt.toString(), ps, true);
                  } finally {
                     if (ps != null) {
                        sqlControl.closeStatement(mconn, ps);
                     }

                  }
               } finally {
                  mconn.release();
               }
            } catch (Exception e) {
               throw new NucleusDataStoreException("Update request failed", e);
            }
         }
      }

   }

   private class DeleteMappingConsumer implements MappingConsumer {
      boolean initialized = false;
      StringBuilder where = new StringBuilder();
      int paramIndex = 1;
      private List whereFields = new ArrayList();
      private List oneToOneNonOwnerFields = new ArrayList();
      private List mc = new ArrayList();
      private final ClassLoaderResolver clr;
      private final AbstractClassMetaData cmd;
      private boolean whereClauseConsumption = false;

      public DeleteMappingConsumer(ClassLoaderResolver clr, AbstractClassMetaData cmd) {
         this.clr = clr;
         this.cmd = cmd;
         this.paramIndex = 1;
      }

      public void setWhereClauseConsumption() {
         this.whereClauseConsumption = true;
      }

      public void preConsumeMapping(int highest) {
         if (!this.initialized) {
            DeleteRequest.this.mappingStatementIndex.setWhereFields(new StatementMappingIndex[highest]);
            DeleteRequest.this.mappingStatementIndex.setUpdateFields(new StatementMappingIndex[highest]);
            this.initialized = true;
         }

      }

      public void consumeMapping(JavaTypeMapping m, AbstractMemberMetaData mmd) {
         if (mmd.getAbstractClassMetaData().isSameOrAncestorOf(this.cmd)) {
            if (m.includeInUpdateStatement()) {
               if (this.whereClauseConsumption) {
                  VersionMetaData vermd = this.cmd.getVersionMetaDataForTable();
                  if (!DeleteRequest.this.table.managesClass(this.cmd.getFullClassName())) {
                     vermd = this.cmd.getBaseAbstractClassMetaData().getVersionMetaDataForClass();
                  }

                  if (vermd != null && vermd.getFieldName() != null && mmd.getName().equals(vermd.getFieldName())) {
                     StatementMappingIndex sei = new StatementMappingIndex(m);
                     DeleteRequest.this.mappingStatementIndex.setWhereVersion(sei);
                     int[] parametersIndex = new int[]{this.paramIndex++};
                     sei.addParameterOccurrence(parametersIndex);
                     if (this.where.length() > 0) {
                        this.where.append(" AND ");
                     }

                     this.where.append(m.getDatastoreMapping(0).getColumn().getIdentifier());
                     this.where.append("=");
                     this.where.append(((AbstractDatastoreMapping)m.getDatastoreMapping(0)).getUpdateInputParameter());
                  } else {
                     Integer abs_field_num = mmd.getAbsoluteFieldNumber();
                     int[] parametersIndex = new int[m.getNumberOfDatastoreMappings()];
                     StatementMappingIndex sei = new StatementMappingIndex(m);
                     sei.addParameterOccurrence(parametersIndex);
                     DeleteRequest.this.mappingStatementIndex.getWhereFields()[mmd.getAbsoluteFieldNumber()] = sei;

                     for(int j = 0; j < parametersIndex.length; ++j) {
                        if (this.where.length() > 0) {
                           this.where.append(" AND ");
                        }

                        this.where.append(m.getDatastoreMapping(j).getColumn().getIdentifier());
                        this.where.append("=");
                        this.where.append(((AbstractDatastoreMapping)m.getDatastoreMapping(j)).getUpdateInputParameter());
                        if (!this.whereFields.contains(abs_field_num)) {
                           this.whereFields.add(abs_field_num);
                        }

                        parametersIndex[j] = this.paramIndex++;
                     }
                  }
               }

               if ((m instanceof PersistableMapping || m instanceof ReferenceMapping) && m.getNumberOfDatastoreMappings() == 0) {
                  RelationType relationType = mmd.getRelationType(this.clr);
                  if (relationType == RelationType.ONE_TO_ONE_BI) {
                     if (mmd.getMappedBy() != null) {
                        this.oneToOneNonOwnerFields.add(mmd);
                     }
                  } else if (relationType == RelationType.MANY_TO_ONE_BI) {
                     AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
                     if (mmd.getJoinMetaData() == null && relatedMmds[0].getJoinMetaData() != null) {
                     }
                  }
               }
            }

            if (m instanceof MappingCallbacks) {
               this.mc.add(m);
            }

         }
      }

      public void consumeMapping(JavaTypeMapping m, int mappingType) {
         if (mappingType == 2) {
            if (this.where.length() > 0) {
               this.where.append(" AND ");
            }

            this.where.append(m.getDatastoreMapping(0).getColumn().getIdentifier().toString());
            this.where.append("=");
            this.where.append(((AbstractDatastoreMapping)m.getDatastoreMapping(0)).getUpdateInputParameter());
            StatementMappingIndex datastoreMappingIdx = new StatementMappingIndex(m);
            DeleteRequest.this.mappingStatementIndex.setWhereDatastoreId(datastoreMappingIdx);
            int[] param = new int[]{this.paramIndex++};
            datastoreMappingIdx.addParameterOccurrence(param);
         } else if (mappingType == 1) {
            if (this.where.length() > 0) {
               this.where.append(" AND ");
            }

            this.where.append(m.getDatastoreMapping(0).getColumn().getIdentifier());
            this.where.append("=");
            this.where.append(((AbstractDatastoreMapping)m.getDatastoreMapping(0)).getUpdateInputParameter());
            StatementMappingIndex versStmtIdx = new StatementMappingIndex(m);
            DeleteRequest.this.mappingStatementIndex.setWhereVersion(versStmtIdx);
            int[] param = new int[]{this.paramIndex++};
            versStmtIdx.addParameterOccurrence(param);
         } else if (mappingType == 7) {
            JavaTypeMapping tenantMapping = DeleteRequest.this.table.getMultitenancyMapping();
            if (this.where.length() > 0) {
               this.where.append(" AND ");
            }

            this.where.append(tenantMapping.getDatastoreMapping(0).getColumn().getIdentifier().toString());
            this.where.append("=");
            this.where.append(((AbstractDatastoreMapping)tenantMapping.getDatastoreMapping(0)).getUpdateInputParameter());
            DeleteRequest.this.multitenancyStatementMapping = new StatementMappingIndex(tenantMapping);
            int[] param = new int[]{this.paramIndex++};
            DeleteRequest.this.multitenancyStatementMapping.addParameterOccurrence(param);
         }

      }

      public void consumeUnmappedColumn(Column col) {
      }

      public int[] getWhereFieldNumbers() {
         int[] fieldNumbers = new int[this.whereFields.size()];

         for(int i = 0; i < this.whereFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.whereFields.get(i);
         }

         return fieldNumbers;
      }

      public AbstractMemberMetaData[] getOneToOneNonOwnerFields() {
         AbstractMemberMetaData[] fmds = new AbstractMemberMetaData[this.oneToOneNonOwnerFields.size()];

         for(int i = 0; i < this.oneToOneNonOwnerFields.size(); ++i) {
            fmds[i] = (AbstractMemberMetaData)this.oneToOneNonOwnerFields.get(i);
         }

         return fmds;
      }

      public List getMappingCallBacks() {
         return this.mc;
      }

      public String getStatement() {
         return "DELETE FROM " + DeleteRequest.this.table.toString() + " WHERE " + this.where;
      }
   }
}
