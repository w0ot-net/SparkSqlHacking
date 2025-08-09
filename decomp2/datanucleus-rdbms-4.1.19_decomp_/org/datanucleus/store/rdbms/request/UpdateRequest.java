package org.datanucleus.store.rdbms.request;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.VersionHelper;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.fieldmanager.OldValueParameterSetter;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class UpdateRequest extends Request {
   private final String updateStmt;
   private final String updateStmtOptimistic;
   private final MappingCallbacks[] callbacks;
   private StatementMappingDefinition stmtMappingDefinition;
   private final int[] updateFieldNumbers;
   private final int[] whereFieldNumbers;
   protected AbstractClassMetaData cmd = null;
   protected VersionMetaData versionMetaData = null;
   protected boolean versionChecks = false;

   public UpdateRequest(DatastoreClass table, AbstractMemberMetaData[] reqFieldMetaData, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      super(table);
      this.cmd = cmd;
      this.versionMetaData = table.getVersionMetaData();
      if (this.versionMetaData != null && this.versionMetaData.getVersionStrategy() != VersionStrategy.NONE) {
         this.versionChecks = true;
      }

      this.stmtMappingDefinition = new StatementMappingDefinition();
      UpdateMappingConsumer consumer = new UpdateMappingConsumer(cmd);
      if (this.versionMetaData != null) {
         if (this.versionMetaData.getFieldName() != null) {
            int numUpdateFields = reqFieldMetaData.length;
            boolean includesVersion = false;

            for(int i = 0; i < reqFieldMetaData.length; ++i) {
               if (reqFieldMetaData[i].getName().equals(this.versionMetaData.getFieldName())) {
                  includesVersion = true;
                  break;
               }
            }

            if (!includesVersion) {
               ++numUpdateFields;
            }

            AbstractMemberMetaData[] updateFmds = new AbstractMemberMetaData[numUpdateFields];

            for(int i = 0; i < reqFieldMetaData.length; ++i) {
               updateFmds[i] = reqFieldMetaData[i];
            }

            if (!includesVersion) {
               updateFmds[updateFmds.length - 1] = cmd.getMetaDataForMember(this.versionMetaData.getFieldName());
            }

            table.provideMappingsForMembers(consumer, updateFmds, false);
         } else {
            table.provideMappingsForMembers(consumer, reqFieldMetaData, false);
            table.provideVersionMappings(consumer);
         }
      } else {
         table.provideMappingsForMembers(consumer, reqFieldMetaData, false);
      }

      consumer.setWhereClauseConsumption(true);
      if (cmd.getIdentityType() == IdentityType.APPLICATION) {
         table.providePrimaryKeyMappings(consumer);
      } else if (cmd.getIdentityType() == IdentityType.DATASTORE) {
         table.provideDatastoreIdMappings(consumer);
      } else {
         AbstractMemberMetaData[] mmds = cmd.getManagedMembers();
         table.provideMappingsForMembers(consumer, mmds, false);
      }

      this.updateStmt = consumer.getStatement();
      if (this.versionMetaData != null) {
         if (this.versionMetaData.getFieldName() != null) {
            AbstractMemberMetaData[] updateFmds = new AbstractMemberMetaData[1];
            updateFmds[0] = cmd.getMetaDataForMember(this.versionMetaData.getFieldName());
            table.provideMappingsForMembers(consumer, updateFmds, false);
         } else {
            table.provideVersionMappings(consumer);
         }
      }

      this.updateStmtOptimistic = consumer.getStatement();
      this.callbacks = (MappingCallbacks[])consumer.getMappingCallbacks().toArray(new MappingCallbacks[consumer.getMappingCallbacks().size()]);
      this.whereFieldNumbers = consumer.getWhereFieldNumbers();
      this.updateFieldNumbers = consumer.getUpdateFieldNumbers();
   }

   public void execute(ObjectProvider op) {
      String stmt = null;
      ExecutionContext ec = op.getExecutionContext();
      boolean optimisticChecks = this.versionMetaData != null && ec.getTransaction().getOptimistic() && this.versionChecks;
      if (optimisticChecks) {
         stmt = this.updateStmtOptimistic;
      } else {
         stmt = this.updateStmt;
      }

      if (stmt != null) {
         if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
            StringBuilder fieldStr = new StringBuilder();

            for(int i = 0; i < this.updateFieldNumbers.length; ++i) {
               if (fieldStr.length() > 0) {
                  fieldStr.append(",");
               }

               fieldStr.append(this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(this.updateFieldNumbers[i]).getName());
            }

            if (this.versionMetaData != null && this.versionMetaData.getFieldName() == null) {
               if (fieldStr.length() > 0) {
                  fieldStr.append(",");
               }

               fieldStr.append("[VERSION]");
            }

            NucleusLogger.PERSISTENCE.debug(Localiser.msg("052214", new Object[]{op.getObjectAsPrintable(), fieldStr.toString(), this.table}));
         }

         RDBMSStoreManager storeMgr = this.table.getStoreManager();
         boolean batch = false;

         try {
            ManagedConnection mconn = storeMgr.getConnection(ec);
            SQLController sqlControl = storeMgr.getSQLController();

            try {
               PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, stmt, batch);

               try {
                  Object currentVersion = op.getTransactionalVersion();
                  Object nextVersion = null;
                  if (this.versionMetaData != null) {
                     if (this.versionMetaData.getFieldName() != null) {
                        AbstractMemberMetaData verfmd = this.cmd.getMetaDataForMember(this.table.getVersionMetaData().getFieldName());
                        if (currentVersion instanceof Number) {
                           currentVersion = ((Number)currentVersion).longValue();
                        }

                        nextVersion = VersionHelper.getNextVersion(this.versionMetaData.getVersionStrategy(), currentVersion);
                        if (verfmd.getType() == Integer.class || verfmd.getType() == Integer.TYPE) {
                           nextVersion = ((Long)nextVersion).intValue();
                        }

                        op.replaceField(verfmd.getAbsoluteFieldNumber(), nextVersion);
                     } else {
                        nextVersion = VersionHelper.getNextVersion(this.versionMetaData.getVersionStrategy(), currentVersion);
                     }

                     op.setTransactionalVersion(nextVersion);
                  }

                  if (this.updateFieldNumbers != null) {
                     StatementClassMapping mappingDefinition = new StatementClassMapping();
                     StatementMappingIndex[] idxs = this.stmtMappingDefinition.getUpdateFields();

                     for(int i = 0; i < idxs.length; ++i) {
                        if (idxs[i] != null) {
                           mappingDefinition.addMappingForMember(i, idxs[i]);
                        }
                     }

                     op.provideFields(this.updateFieldNumbers, storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition));
                  }

                  if (this.versionMetaData != null && this.versionMetaData.getFieldName() == null) {
                     StatementMappingIndex mapIdx = this.stmtMappingDefinition.getUpdateVersion();

                     for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
                        this.table.getVersionMapping(false).setObject(ec, ps, mapIdx.getParameterPositionsForOccurrence(i), nextVersion);
                     }
                  }

                  if (this.table.getIdentityType() == IdentityType.DATASTORE) {
                     StatementMappingIndex mapIdx = this.stmtMappingDefinition.getWhereDatastoreId();

                     for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
                        this.table.getDatastoreIdMapping().setObject(ec, ps, mapIdx.getParameterPositionsForOccurrence(i), op.getInternalObjectId());
                     }
                  } else {
                     StatementClassMapping mappingDefinition = new StatementClassMapping();
                     StatementMappingIndex[] idxs = this.stmtMappingDefinition.getWhereFields();

                     for(int i = 0; i < idxs.length; ++i) {
                        if (idxs[i] != null) {
                           mappingDefinition.addMappingForMember(i, idxs[i]);
                        }
                     }

                     FieldManager fm = null;
                     Object var50;
                     if (this.cmd.getIdentityType() == IdentityType.NONDURABLE) {
                        var50 = new OldValueParameterSetter(op, ps, mappingDefinition);
                     } else {
                        var50 = storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition);
                     }

                     op.provideFields(this.whereFieldNumbers, (FieldManager)var50);
                  }

                  if (optimisticChecks) {
                     if (currentVersion == null) {
                        String msg = Localiser.msg("052201", new Object[]{op.getInternalObjectId(), this.table});
                        NucleusLogger.PERSISTENCE.error(msg);
                        throw new NucleusException(msg);
                     }

                     StatementMappingIndex mapIdx = this.stmtMappingDefinition.getWhereVersion();

                     for(int i = 0; i < mapIdx.getNumberOfParameterOccurrences(); ++i) {
                        mapIdx.getMapping().setObject(ec, ps, mapIdx.getParameterPositionsForOccurrence(i), currentVersion);
                     }
                  }

                  int[] rcs = sqlControl.executeStatementUpdate(ec, mconn, stmt, ps, !batch);
                  if (rcs[0] == 0 && optimisticChecks) {
                     String msg = Localiser.msg("052203", new Object[]{op.getObjectAsPrintable(), op.getInternalObjectId(), "" + currentVersion});
                     NucleusLogger.PERSISTENCE.error(msg);
                     throw new NucleusOptimisticException(msg, op.getObject());
                  }
               } finally {
                  sqlControl.closeStatement(mconn, ps);
               }
            } finally {
               mconn.release();
            }
         } catch (SQLException var28) {
            SQLException e = var28;
            String msg = Localiser.msg("052215", new Object[]{op.getObjectAsPrintable(), stmt, StringUtils.getStringFromStackTrace(var28)});
            NucleusLogger.DATASTORE_PERSIST.error(msg);
            List exceptions = new ArrayList();
            exceptions.add(var28);

            while((e = e.getNextException()) != null) {
               exceptions.add(e);
            }

            throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
         }
      }

      for(int i = 0; i < this.callbacks.length; ++i) {
         try {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("052216", new Object[]{op.getObjectAsPrintable(), ((JavaTypeMapping)this.callbacks[i]).getMemberMetaData().getFullFieldName()}));
            }

            this.callbacks[i].postUpdate(op);
         } catch (NotYetFlushedException e) {
            op.updateFieldAfterInsert(e.getPersistable(), ((JavaTypeMapping)this.callbacks[i]).getMemberMetaData().getAbsoluteFieldNumber());
         }
      }

   }

   private class UpdateMappingConsumer implements MappingConsumer {
      boolean initialized = false;
      int paramIndex = 1;
      List updateFields = new ArrayList();
      List whereFields = new ArrayList();
      List mc = new ArrayList();
      StringBuilder columnAssignments = new StringBuilder();
      Map assignedColumns = new HashMap();
      StringBuilder where = new StringBuilder();
      private final AbstractClassMetaData cmd;
      private boolean whereClauseConsumption = false;

      public UpdateMappingConsumer(AbstractClassMetaData cmd) {
         this.cmd = cmd;
      }

      public void setWhereClauseConsumption(boolean whereClause) {
         this.whereClauseConsumption = whereClause;
      }

      public void preConsumeMapping(int highest) {
         if (!this.initialized) {
            UpdateRequest.this.stmtMappingDefinition.setWhereFields(new StatementMappingIndex[highest]);
            UpdateRequest.this.stmtMappingDefinition.setUpdateFields(new StatementMappingIndex[highest]);
            this.initialized = true;
         }

      }

      public void consumeMapping(JavaTypeMapping m, AbstractMemberMetaData fmd) {
         if (fmd.getAbstractClassMetaData().isSameOrAncestorOf(this.cmd)) {
            if (m.includeInUpdateStatement()) {
               if (fmd.hasExtension("updateable") && fmd.getValueForExtension("updateable").equalsIgnoreCase("false")) {
                  return;
               }

               ColumnMetaData[] colmds = fmd.getColumnMetaData();
               if (colmds != null && colmds.length > 0) {
                  for(int i = 0; i < colmds.length; ++i) {
                     if (!colmds[i].getUpdateable()) {
                        return;
                     }
                  }
               }

               Integer abs_field_num = fmd.getAbsoluteFieldNumber();
               int[] parametersIndex = new int[m.getNumberOfDatastoreMappings()];
               StatementMappingIndex sei = new StatementMappingIndex(m);
               sei.addParameterOccurrence(parametersIndex);
               if (this.whereClauseConsumption) {
                  VersionMetaData vermd = this.cmd.getVersionMetaDataForTable();
                  if (!UpdateRequest.this.table.managesClass(this.cmd.getFullClassName())) {
                     vermd = this.cmd.getBaseAbstractClassMetaData().getVersionMetaDataForClass();
                  }

                  if (vermd != null && vermd.getFieldName() != null && fmd.getName().equals(vermd.getFieldName())) {
                     UpdateRequest.this.stmtMappingDefinition.setWhereVersion(sei);
                     parametersIndex[0] = this.paramIndex++;
                     if (this.where.length() > 0) {
                        this.where.append(" AND ");
                     }

                     this.where.append(m.getDatastoreMapping(0).getColumn().getIdentifier());
                     this.where.append("=");
                     this.where.append(((AbstractDatastoreMapping)m.getDatastoreMapping(0)).getUpdateInputParameter());
                  } else {
                     UpdateRequest.this.stmtMappingDefinition.getWhereFields()[fmd.getAbsoluteFieldNumber()] = sei;

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
               } else {
                  UpdateRequest.this.stmtMappingDefinition.getUpdateFields()[fmd.getAbsoluteFieldNumber()] = sei;

                  for(int j = 0; j < parametersIndex.length; ++j) {
                     Column c = m.getDatastoreMapping(j).getColumn();
                     DatastoreIdentifier columnId = c.getIdentifier();
                     boolean columnExists = this.assignedColumns.containsKey(columnId.toString());
                     if (columnExists) {
                        parametersIndex[j] = (Integer)this.assignedColumns.get(columnId.toString());
                     }

                     String param = ((AbstractDatastoreMapping)m.getDatastoreMapping(j)).getUpdateInputParameter();
                     if (!columnExists) {
                        if (this.columnAssignments.length() > 0) {
                           this.columnAssignments.append(",");
                        }

                        this.columnAssignments.append(columnId).append("=").append(param);
                     }

                     if (param.indexOf("?") > -1) {
                        if (!this.updateFields.contains(abs_field_num)) {
                           this.updateFields.add(abs_field_num);
                        }

                        parametersIndex[j] = this.paramIndex++;
                     }

                     if (!columnExists) {
                        this.assignedColumns.put(columnId.toString(), fmd.getAbsoluteFieldNumber());
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
         if (mappingType == 1) {
            String inputParam = ((AbstractDatastoreMapping)m.getDatastoreMapping(0)).getUpdateInputParameter();
            if (this.whereClauseConsumption) {
               if (this.where.length() > 0) {
                  this.where.append(" AND ");
               }

               this.where.append(m.getDatastoreMapping(0).getColumn().getIdentifier());
               this.where.append("=");
               this.where.append(inputParam);
               StatementMappingIndex versStmtIdx = new StatementMappingIndex(m);
               versStmtIdx.addParameterOccurrence(new int[]{this.paramIndex++});
               UpdateRequest.this.stmtMappingDefinition.setWhereVersion(versStmtIdx);
            } else {
               String condition = m.getDatastoreMapping(0).getColumn().getIdentifier() + "=" + inputParam;
               if (this.columnAssignments.length() > 0) {
                  this.columnAssignments.append(", ");
               }

               this.columnAssignments.append(condition);
               StatementMappingIndex versStmtIdx = new StatementMappingIndex(m);
               versStmtIdx.addParameterOccurrence(new int[]{this.paramIndex++});
               UpdateRequest.this.stmtMappingDefinition.setUpdateVersion(versStmtIdx);
            }
         } else if (mappingType == 2) {
            if (this.where.length() > 0) {
               this.where.append(" AND ");
            }

            this.where.append(((Column)UpdateRequest.this.key.getColumns().get(0)).getIdentifier());
            this.where.append("=");
            this.where.append(((AbstractDatastoreMapping)m.getDatastoreMapping(0)).getUpdateInputParameter());
            StatementMappingIndex datastoreIdIdx = new StatementMappingIndex(m);
            datastoreIdIdx.addParameterOccurrence(new int[]{this.paramIndex++});
            UpdateRequest.this.stmtMappingDefinition.setWhereDatastoreId(datastoreIdIdx);
         }

      }

      public void consumeUnmappedColumn(Column col) {
      }

      public List getMappingCallbacks() {
         return this.mc;
      }

      public int[] getUpdateFieldNumbers() {
         int[] fieldNumbers = new int[this.updateFields.size()];

         for(int i = 0; i < this.updateFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.updateFields.get(i);
         }

         return fieldNumbers;
      }

      public int[] getWhereFieldNumbers() {
         int[] fieldNumbers = new int[this.whereFields.size()];

         for(int i = 0; i < this.whereFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.whereFields.get(i);
         }

         return fieldNumbers;
      }

      public String getStatement() {
         return this.columnAssignments.length() < 1 ? null : "UPDATE " + UpdateRequest.this.table.toString() + " SET " + this.columnAssignments + " WHERE " + this.where;
      }
   }
}
