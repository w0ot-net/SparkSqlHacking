package org.datanucleus.store.rdbms.request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.DiscriminatorMetaData;
import org.datanucleus.metadata.DiscriminatorStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.MetaDataUtils;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.state.ActivityState;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.VersionHelper;
import org.datanucleus.store.connection.ManagedConnection;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.rdbms.RDBMSStoreManager;
import org.datanucleus.store.rdbms.SQLController;
import org.datanucleus.store.rdbms.identifier.DatastoreIdentifier;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.MappingConsumer;
import org.datanucleus.store.rdbms.mapping.StatementClassMapping;
import org.datanucleus.store.rdbms.mapping.StatementMappingIndex;
import org.datanucleus.store.rdbms.mapping.datastore.AbstractDatastoreMapping;
import org.datanucleus.store.rdbms.mapping.java.JavaTypeMapping;
import org.datanucleus.store.rdbms.mapping.java.PersistableMapping;
import org.datanucleus.store.rdbms.mapping.java.ReferenceMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.SecondaryTable;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class InsertRequest extends Request {
   private static final int IDPARAMNUMBER = 1;
   private final MappingCallbacks[] callbacks;
   private final int[] insertFieldNumbers;
   private final int[] pkFieldNumbers;
   private final int[] reachableFieldNumbers;
   private final int[] relationFieldNumbers;
   private final String insertStmt;
   private boolean hasIdentityColumn = false;
   private StatementMappingIndex[] stmtMappings;
   private StatementMappingIndex[] retrievedStmtMappings;
   private StatementMappingIndex versionStmtMapping;
   private StatementMappingIndex discriminatorStmtMapping;
   private StatementMappingIndex multitenancyStmtMapping;
   private StatementMappingIndex[] externalFKStmtMappings;
   private StatementMappingIndex[] externalFKDiscrimStmtMappings;
   private StatementMappingIndex[] externalOrderStmtMappings;
   private boolean batch = false;

   public InsertRequest(DatastoreClass table, AbstractClassMetaData cmd, ClassLoaderResolver clr) {
      super(table);
      InsertMappingConsumer consumer = new InsertMappingConsumer(clr, cmd, 1);
      table.provideDatastoreIdMappings(consumer);
      table.provideNonPrimaryKeyMappings(consumer);
      table.providePrimaryKeyMappings(consumer);
      table.provideVersionMappings(consumer);
      table.provideDiscriminatorMappings(consumer);
      table.provideMultitenancyMapping(consumer);
      table.provideExternalMappings(consumer, 5);
      table.provideExternalMappings(consumer, 6);
      table.provideExternalMappings(consumer, 4);
      table.provideUnmappedColumns(consumer);
      this.callbacks = (MappingCallbacks[])consumer.getMappingCallbacks().toArray(new MappingCallbacks[consumer.getMappingCallbacks().size()]);
      this.stmtMappings = consumer.getStatementMappings();
      this.versionStmtMapping = consumer.getVersionStatementMapping();
      this.discriminatorStmtMapping = consumer.getDiscriminatorStatementMapping();
      this.multitenancyStmtMapping = consumer.getMultitenancyStatementMapping();
      this.externalFKStmtMappings = consumer.getExternalFKStatementMapping();
      this.externalFKDiscrimStmtMappings = consumer.getExternalFKDiscrimStatementMapping();
      this.externalOrderStmtMappings = consumer.getExternalOrderStatementMapping();
      this.pkFieldNumbers = consumer.getPrimaryKeyFieldNumbers();
      if (table.getIdentityType() == IdentityType.APPLICATION && this.pkFieldNumbers.length < 1 && !this.hasIdentityColumn) {
         throw (new NucleusException(Localiser.msg("052200", new Object[]{cmd.getFullClassName()}))).setFatal();
      } else {
         this.insertFieldNumbers = consumer.getInsertFieldNumbers();
         this.retrievedStmtMappings = consumer.getReachableStatementMappings();
         this.reachableFieldNumbers = consumer.getReachableFieldNumbers();
         this.relationFieldNumbers = consumer.getRelationFieldNumbers();
         this.insertStmt = consumer.getInsertStmt();
         if (!this.hasIdentityColumn && !cmd.hasRelations(clr, table.getStoreManager().getMetaDataManager()) && this.externalFKStmtMappings == null) {
            this.batch = true;
         }

      }
   }

   public void execute(ObjectProvider op) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("052207", new Object[]{op.getObjectAsPrintable(), this.table}));
      }

      try {
         VersionMetaData vermd = this.table.getVersionMetaData();
         ExecutionContext ec = op.getExecutionContext();
         RDBMSStoreManager storeMgr = this.table.getStoreManager();
         if (vermd != null && vermd.getFieldName() != null) {
            AbstractMemberMetaData verfmd = ((AbstractClassMetaData)vermd.getParent()).getMetaDataForMember(vermd.getFieldName());
            Object currentVersion = op.getVersion();
            if (currentVersion instanceof Number) {
               currentVersion = ((Number)currentVersion).longValue();
            }

            Object nextOptimisticVersion = VersionHelper.getNextVersion(this.table.getVersionMetaData().getVersionStrategy(), currentVersion);
            if (verfmd.getType() == Integer.class || verfmd.getType() == Integer.TYPE) {
               nextOptimisticVersion = ((Long)nextOptimisticVersion).intValue();
            }

            op.replaceField(verfmd.getAbsoluteFieldNumber(), nextOptimisticVersion);
         }

         op.changeActivityState(ActivityState.INSERTING);
         SQLController sqlControl = storeMgr.getSQLController();
         ManagedConnection mconn = storeMgr.getConnection(ec);

         try {
            PreparedStatement ps = sqlControl.getStatementForUpdate(mconn, this.insertStmt, this.batch, this.hasIdentityColumn && storeMgr.getDatastoreAdapter().supportsOption("GetGeneratedKeysStatement"));

            try {
               StatementClassMapping mappingDefinition = new StatementClassMapping();
               StatementMappingIndex[] idxs = this.stmtMappings;

               for(int i = 0; i < idxs.length; ++i) {
                  if (idxs[i] != null) {
                     mappingDefinition.addMappingForMember(i, idxs[i]);
                  }
               }

               if (this.table.getIdentityType() == IdentityType.DATASTORE) {
                  if (!this.table.isObjectIdDatastoreAttributed() || !this.table.isBaseDatastoreClass()) {
                     int[] paramNumber = new int[]{1};
                     this.table.getDatastoreIdMapping().setObject(ec, ps, paramNumber, op.getInternalObjectId());
                  }
               } else if (this.table.getIdentityType() == IdentityType.APPLICATION) {
                  op.provideFields(this.pkFieldNumbers, storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition));
               }

               if (this.insertFieldNumbers.length > 0) {
                  int numberOfFieldsToProvide = 0;

                  for(int i = 0; i < this.insertFieldNumbers.length; ++i) {
                     if (this.insertFieldNumbers[i] < op.getClassMetaData().getMemberCount()) {
                        ++numberOfFieldsToProvide;
                     }
                  }

                  int j = 0;
                  int[] fieldNums = new int[numberOfFieldsToProvide];

                  for(int i = 0; i < this.insertFieldNumbers.length; ++i) {
                     if (this.insertFieldNumbers[i] < op.getClassMetaData().getMemberCount()) {
                        fieldNums[j++] = this.insertFieldNumbers[i];
                     }
                  }

                  op.provideFields(fieldNums, storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition));
               }

               if (this.table.getVersionMapping(false) != null) {
                  Object currentVersion = op.getVersion();
                  Object nextOptimisticVersion = VersionHelper.getNextVersion(this.table.getVersionMetaData().getVersionStrategy(), currentVersion);

                  for(int k = 0; k < this.versionStmtMapping.getNumberOfParameterOccurrences(); ++k) {
                     this.table.getVersionMapping(false).setObject(ec, ps, this.versionStmtMapping.getParameterPositionsForOccurrence(k), nextOptimisticVersion);
                  }

                  op.setTransactionalVersion(nextOptimisticVersion);
               } else if (vermd != null && vermd.getFieldName() != null) {
                  Object currentVersion = op.getVersion();
                  Object nextOptimisticVersion = VersionHelper.getNextVersion(this.table.getVersionMetaData().getVersionStrategy(), currentVersion);
                  op.setTransactionalVersion(nextOptimisticVersion);
               }

               if (this.multitenancyStmtMapping != null) {
                  this.table.getMultitenancyMapping().setObject(ec, ps, this.multitenancyStmtMapping.getParameterPositionsForOccurrence(0), storeMgr.getStringProperty("datanucleus.TenantID"));
               }

               if (this.table.getDiscriminatorMapping(false) != null) {
                  DiscriminatorMetaData dismd = this.table.getDiscriminatorMetaData();
                  if (dismd.getStrategy() == DiscriminatorStrategy.CLASS_NAME) {
                     for(int k = 0; k < this.discriminatorStmtMapping.getNumberOfParameterOccurrences(); ++k) {
                        this.table.getDiscriminatorMapping(false).setObject(ec, ps, this.discriminatorStmtMapping.getParameterPositionsForOccurrence(k), op.getObject().getClass().getName());
                     }
                  } else if (dismd.getStrategy() == DiscriminatorStrategy.VALUE_MAP) {
                     dismd = op.getClassMetaData().getInheritanceMetaData().getDiscriminatorMetaData();

                     for(int k = 0; k < this.discriminatorStmtMapping.getNumberOfParameterOccurrences(); ++k) {
                        this.table.getDiscriminatorMapping(false).setObject(ec, ps, this.discriminatorStmtMapping.getParameterPositionsForOccurrence(k), dismd.getValue());
                     }
                  }
               }

               if (this.externalFKStmtMappings != null) {
                  for(int i = 0; i < this.externalFKStmtMappings.length; ++i) {
                     Object fkValue = op.getAssociatedValue(this.externalFKStmtMappings[i].getMapping());
                     if (fkValue != null) {
                        AbstractMemberMetaData ownerFmd = this.table.getMetaDataForExternalMapping(this.externalFKStmtMappings[i].getMapping(), 5);

                        for(int k = 0; k < this.externalFKStmtMappings[i].getNumberOfParameterOccurrences(); ++k) {
                           this.externalFKStmtMappings[i].getMapping().setObject(ec, ps, this.externalFKStmtMappings[i].getParameterPositionsForOccurrence(k), fkValue, (ObjectProvider)null, ownerFmd.getAbsoluteFieldNumber());
                        }
                     } else {
                        for(int k = 0; k < this.externalFKStmtMappings[i].getNumberOfParameterOccurrences(); ++k) {
                           this.externalFKStmtMappings[i].getMapping().setObject(ec, ps, this.externalFKStmtMappings[i].getParameterPositionsForOccurrence(k), (Object)null);
                        }
                     }
                  }
               }

               if (this.externalFKDiscrimStmtMappings != null) {
                  for(int i = 0; i < this.externalFKDiscrimStmtMappings.length; ++i) {
                     Object discrimValue = op.getAssociatedValue(this.externalFKDiscrimStmtMappings[i].getMapping());

                     for(int k = 0; k < this.externalFKDiscrimStmtMappings[i].getNumberOfParameterOccurrences(); ++k) {
                        this.externalFKDiscrimStmtMappings[i].getMapping().setObject(ec, ps, this.externalFKDiscrimStmtMappings[i].getParameterPositionsForOccurrence(k), discrimValue);
                     }
                  }
               }

               if (this.externalOrderStmtMappings != null) {
                  for(int i = 0; i < this.externalOrderStmtMappings.length; ++i) {
                     Object orderValue = op.getAssociatedValue(this.externalOrderStmtMappings[i].getMapping());
                     if (orderValue == null) {
                        orderValue = -1;
                     }

                     for(int k = 0; k < this.externalOrderStmtMappings[i].getNumberOfParameterOccurrences(); ++k) {
                        this.externalOrderStmtMappings[i].getMapping().setObject(ec, ps, this.externalOrderStmtMappings[i].getParameterPositionsForOccurrence(k), orderValue);
                     }
                  }
               }

               sqlControl.executeStatementUpdate(ec, mconn, this.insertStmt, ps, !this.batch);
               if (this.hasIdentityColumn) {
                  Object newId = this.getInsertedDatastoreIdentity(ec, sqlControl, op, mconn, ps);
                  if (NucleusLogger.DATASTORE_PERSIST.isDebugEnabled()) {
                     NucleusLogger.DATASTORE_PERSIST.debug(Localiser.msg("052206", new Object[]{op.getObjectAsPrintable(), newId}));
                  }

                  op.setPostStoreNewObjectId(newId);
               }

               for(int i = 0; i < this.callbacks.length; ++i) {
                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("052222", new Object[]{op.getObjectAsPrintable(), ((JavaTypeMapping)this.callbacks[i]).getMemberMetaData().getFullFieldName()}));
                  }

                  this.callbacks[i].insertPostProcessing(op);
               }

               storeMgr.setObjectIsInsertedToLevel(op, this.table);

               for(int i = 0; i < this.relationFieldNumbers.length; ++i) {
                  Object value = op.provideField(this.relationFieldNumbers[i]);
                  if (value != null && ec.getApiAdapter().isDetached(value)) {
                     Object valueAttached = ec.persistObjectInternal(value, (ObjectProvider)null, -1, 0);
                     op.replaceField(this.relationFieldNumbers[i], valueAttached);
                  }
               }

               if (this.reachableFieldNumbers.length > 0) {
                  int numberOfReachableFields = 0;

                  for(int i = 0; i < this.reachableFieldNumbers.length; ++i) {
                     if (this.reachableFieldNumbers[i] < op.getClassMetaData().getMemberCount()) {
                        ++numberOfReachableFields;
                     }
                  }

                  int[] fieldNums = new int[numberOfReachableFields];
                  int j = 0;

                  for(int i = 0; i < this.reachableFieldNumbers.length; ++i) {
                     if (this.reachableFieldNumbers[i] < op.getClassMetaData().getMemberCount()) {
                        fieldNums[j++] = this.reachableFieldNumbers[i];
                     }
                  }

                  mappingDefinition = new StatementClassMapping();
                  idxs = this.retrievedStmtMappings;

                  for(int i = 0; i < idxs.length; ++i) {
                     if (idxs[i] != null) {
                        mappingDefinition.addMappingForMember(i, idxs[i]);
                     }
                  }

                  NucleusLogger.PERSISTENCE.debug("Performing reachability on fields " + StringUtils.intArrayToString(fieldNums));
                  op.provideFields(fieldNums, storeMgr.getFieldManagerForStatementGeneration(op, ps, mappingDefinition));
               }
            } finally {
               sqlControl.closeStatement(mconn, ps);
            }
         } finally {
            mconn.release();
         }
      } catch (SQLException var27) {
         SQLException e = var27;
         String msg = Localiser.msg("052208", new Object[]{op.getObjectAsPrintable(), this.insertStmt, var27.getMessage()});
         NucleusLogger.DATASTORE_PERSIST.warn(msg);
         List exceptions = new ArrayList();
         exceptions.add(var27);

         while((e = e.getNextException()) != null) {
            exceptions.add(e);
         }

         throw new NucleusDataStoreException(msg, (Throwable[])exceptions.toArray(new Throwable[exceptions.size()]));
      }

      for(int i = 0; i < this.callbacks.length; ++i) {
         try {
            if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
               NucleusLogger.PERSISTENCE.debug(Localiser.msg("052209", new Object[]{op.getObjectAsPrintable(), ((JavaTypeMapping)this.callbacks[i]).getMemberMetaData().getFullFieldName()}));
            }

            this.callbacks[i].postInsert(op);
         } catch (NotYetFlushedException e) {
            op.updateFieldAfterInsert(e.getPersistable(), ((JavaTypeMapping)this.callbacks[i]).getMemberMetaData().getAbsoluteFieldNumber());
         }
      }

   }

   private Object getInsertedDatastoreIdentity(ExecutionContext ec, SQLController sqlControl, ObjectProvider op, ManagedConnection mconn, PreparedStatement ps) throws SQLException {
      Object datastoreId = null;
      RDBMSStoreManager storeMgr = this.table.getStoreManager();
      if (storeMgr.getDatastoreAdapter().supportsOption("GetGeneratedKeysStatement")) {
         ResultSet rs = null;

         try {
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
               datastoreId = rs.getObject(1);
            }
         } catch (Throwable var20) {
         } finally {
            if (rs != null) {
               rs.close();
            }

         }
      }

      if (datastoreId == null) {
         String columnName = null;
         JavaTypeMapping idMapping = this.table.getIdMapping();
         if (idMapping != null) {
            for(int i = 0; i < idMapping.getNumberOfDatastoreMappings(); ++i) {
               Column col = idMapping.getDatastoreMapping(i).getColumn();
               if (col.isIdentity()) {
                  columnName = col.getIdentifier().toString();
                  break;
               }
            }
         }

         String autoIncStmt = storeMgr.getDatastoreAdapter().getAutoIncrementStmt(this.table, columnName);
         PreparedStatement psAutoIncrement = sqlControl.getStatementForQuery(mconn, autoIncStmt);
         ResultSet rs = null;

         try {
            rs = sqlControl.executeStatementQuery(ec, mconn, autoIncStmt, psAutoIncrement);
            if (rs.next()) {
               datastoreId = rs.getObject(1);
            }
         } finally {
            if (rs != null) {
               rs.close();
            }

            if (psAutoIncrement != null) {
               psAutoIncrement.close();
            }

         }
      }

      if (datastoreId == null) {
         throw new NucleusDataStoreException(Localiser.msg("052205", new Object[]{this.table}));
      } else {
         return datastoreId;
      }
   }

   private class InsertMappingConsumer implements MappingConsumer {
      List insertFields = new ArrayList();
      List pkFields = new ArrayList();
      List reachableFields = new ArrayList();
      List relationFields = new ArrayList();
      StringBuilder columnNames = new StringBuilder();
      StringBuilder columnValues = new StringBuilder();
      Map assignedColumns = new HashMap();
      List mc = new ArrayList();
      boolean initialized = false;
      int paramIndex;
      private StatementMappingIndex[] statementMappings;
      private StatementMappingIndex[] retrievedStatementMappings;
      private StatementMappingIndex versionStatementMapping;
      private StatementMappingIndex discriminatorStatementMapping;
      private StatementMappingIndex multitenancyStatementMapping;
      private StatementMappingIndex[] externalFKStmtExprIndex;
      private StatementMappingIndex[] externalFKDiscrimStmtExprIndex;
      private StatementMappingIndex[] externalOrderStmtExprIndex;
      private final ClassLoaderResolver clr;
      private final AbstractClassMetaData cmd;

      public InsertMappingConsumer(ClassLoaderResolver clr, AbstractClassMetaData cmd, int initialParamIndex) {
         this.clr = clr;
         this.cmd = cmd;
         this.paramIndex = initialParamIndex;
      }

      public void preConsumeMapping(int highestFieldNumber) {
         if (!this.initialized) {
            this.statementMappings = new StatementMappingIndex[highestFieldNumber];
            this.retrievedStatementMappings = new StatementMappingIndex[highestFieldNumber];
            this.initialized = true;
         }

      }

      public void consumeMapping(JavaTypeMapping m, AbstractMemberMetaData mmd) {
         if (mmd.getAbstractClassMetaData().isSameOrAncestorOf(this.cmd)) {
            if (m.includeInInsertStatement()) {
               if (m.getNumberOfDatastoreMappings() == 0 && (m instanceof PersistableMapping || m instanceof ReferenceMapping)) {
                  this.retrievedStatementMappings[mmd.getAbsoluteFieldNumber()] = new StatementMappingIndex(m);
                  RelationType relationType = mmd.getRelationType(this.clr);
                  if (relationType == RelationType.ONE_TO_ONE_BI) {
                     if (mmd.getMappedBy() != null) {
                        this.reachableFields.add(mmd.getAbsoluteFieldNumber());
                     }
                  } else if (relationType == RelationType.MANY_TO_ONE_BI) {
                     AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
                     if (mmd.getJoinMetaData() != null || relatedMmds[0].getJoinMetaData() != null) {
                        this.reachableFields.add(mmd.getAbsoluteFieldNumber());
                     }
                  }
               } else {
                  if (mmd.hasExtension("insertable") && mmd.getValueForExtension("insertable").equalsIgnoreCase("false")) {
                     return;
                  }

                  ColumnMetaData[] colmds = mmd.getColumnMetaData();
                  if (colmds != null && colmds.length > 0) {
                     for(int i = 0; i < colmds.length; ++i) {
                        if (!colmds[i].getInsertable()) {
                           return;
                        }
                     }
                  }

                  RelationType relationType = mmd.getRelationType(this.clr);
                  if (relationType == RelationType.ONE_TO_ONE_BI) {
                     if (mmd.getMappedBy() == null) {
                     }
                  } else if (relationType == RelationType.MANY_TO_ONE_BI) {
                     AbstractMemberMetaData[] relatedMmds = mmd.getRelatedMemberMetaData(this.clr);
                     if (mmd.getJoinMetaData() == null && relatedMmds[0].getJoinMetaData() == null) {
                        this.relationFields.add(mmd.getAbsoluteFieldNumber());
                     }
                  }

                  this.statementMappings[mmd.getAbsoluteFieldNumber()] = new StatementMappingIndex(m);
                  int[] parametersIndex = new int[m.getNumberOfDatastoreMappings()];

                  for(int j = 0; j < parametersIndex.length; ++j) {
                     Column c = m.getDatastoreMapping(j).getColumn();
                     DatastoreIdentifier columnId = c.getIdentifier();
                     boolean columnExists = this.assignedColumns.containsKey(columnId.toString());
                     if (columnExists) {
                        parametersIndex[j] = (Integer)this.assignedColumns.get(c.getIdentifier().toString());
                     }

                     if (InsertRequest.this.table instanceof SecondaryTable || !InsertRequest.this.table.isBaseDatastoreClass() || !InsertRequest.this.table.getStoreManager().isStrategyDatastoreAttributed(this.cmd, mmd.getAbsoluteFieldNumber()) && !c.isIdentity()) {
                        if (!columnExists) {
                           if (this.columnNames.length() > 0) {
                              this.columnNames.append(',');
                              this.columnValues.append(',');
                           }

                           this.columnNames.append(columnId);
                           this.columnValues.append(((AbstractDatastoreMapping)m.getDatastoreMapping(j)).getInsertionInputParameter());
                        }

                        if (((AbstractDatastoreMapping)m.getDatastoreMapping(j)).insertValuesOnInsert()) {
                           Integer abs_field_num = mmd.getAbsoluteFieldNumber();
                           if (mmd.isPrimaryKey()) {
                              if (!this.pkFields.contains(abs_field_num)) {
                                 this.pkFields.add(abs_field_num);
                              }
                           } else if (!this.insertFields.contains(abs_field_num)) {
                              this.insertFields.add(abs_field_num);
                           }

                           if (columnExists) {
                              parametersIndex[j] = (Integer)this.assignedColumns.get(c.getIdentifier().toString());
                           } else {
                              parametersIndex[j] = this.paramIndex++;
                           }
                        }

                        if (!columnExists) {
                           this.assignedColumns.put(c.getIdentifier().toString(), mmd.getAbsoluteFieldNumber());
                        }
                     } else {
                        InsertRequest.this.hasIdentityColumn = true;
                     }
                  }

                  this.statementMappings[mmd.getAbsoluteFieldNumber()].addParameterOccurrence(parametersIndex);
               }
            }

            if (m instanceof MappingCallbacks) {
               this.mc.add(m);
            }

         }
      }

      public void consumeMapping(JavaTypeMapping m, int mappingType) {
         if (mappingType == 1) {
            if (InsertRequest.this.table.getVersionMapping(false) != null) {
               String val = ((AbstractDatastoreMapping)InsertRequest.this.table.getVersionMapping(false).getDatastoreMapping(0)).getUpdateInputParameter();
               if (this.columnNames.length() > 0) {
                  this.columnNames.append(',');
                  this.columnValues.append(',');
               }

               this.columnNames.append(InsertRequest.this.table.getVersionMapping(false).getDatastoreMapping(0).getColumn().getIdentifier());
               this.columnValues.append(val);
               this.versionStatementMapping = new StatementMappingIndex(InsertRequest.this.table.getVersionMapping(false));
               int[] param = new int[]{this.paramIndex++};
               this.versionStatementMapping.addParameterOccurrence(param);
            } else {
               this.versionStatementMapping = null;
            }
         } else if (mappingType == 3) {
            if (InsertRequest.this.table.getDiscriminatorMapping(false) != null) {
               String val = ((AbstractDatastoreMapping)InsertRequest.this.table.getDiscriminatorMapping(false).getDatastoreMapping(0)).getUpdateInputParameter();
               if (this.columnNames.length() > 0) {
                  this.columnNames.append(',');
                  this.columnValues.append(',');
               }

               this.columnNames.append(InsertRequest.this.table.getDiscriminatorMapping(false).getDatastoreMapping(0).getColumn().getIdentifier());
               this.columnValues.append(val);
               this.discriminatorStatementMapping = new StatementMappingIndex(InsertRequest.this.table.getDiscriminatorMapping(false));
               int[] param = new int[]{this.paramIndex++};
               this.discriminatorStatementMapping.addParameterOccurrence(param);
            } else {
               this.discriminatorStatementMapping = null;
            }
         } else if (mappingType == 2) {
            if (InsertRequest.this.table.getIdentityType() == IdentityType.DATASTORE) {
               if (InsertRequest.this.table.isObjectIdDatastoreAttributed() && InsertRequest.this.table.isBaseDatastoreClass()) {
                  InsertRequest.this.hasIdentityColumn = true;
               } else {
                  Iterator iterator = InsertRequest.this.key.getColumns().iterator();
                  if (this.columnNames.length() > 0) {
                     this.columnNames.append(',');
                     this.columnValues.append(',');
                  }

                  this.columnNames.append(((Column)iterator.next()).getIdentifier().toString());
                  this.columnValues.append("?");
                  ++this.paramIndex;
               }
            }
         } else if (mappingType == 5) {
            this.externalFKStmtExprIndex = this.processExternalMapping(m, this.statementMappings, this.externalFKStmtExprIndex);
         } else if (mappingType == 6) {
            this.externalFKDiscrimStmtExprIndex = this.processExternalMapping(m, this.statementMappings, this.externalFKDiscrimStmtExprIndex);
         } else if (mappingType == 4) {
            this.externalOrderStmtExprIndex = this.processExternalMapping(m, this.statementMappings, this.externalOrderStmtExprIndex);
         } else if (mappingType == 7) {
            JavaTypeMapping tenantMapping = InsertRequest.this.table.getMultitenancyMapping();
            String val = ((AbstractDatastoreMapping)tenantMapping.getDatastoreMapping(0)).getUpdateInputParameter();
            if (this.columnNames.length() > 0) {
               this.columnNames.append(',');
               this.columnValues.append(',');
            }

            this.columnNames.append(tenantMapping.getDatastoreMapping(0).getColumn().getIdentifier());
            this.columnValues.append(val);
            this.multitenancyStatementMapping = new StatementMappingIndex(tenantMapping);
            int[] param = new int[]{this.paramIndex++};
            this.multitenancyStatementMapping.addParameterOccurrence(param);
         }

      }

      public void consumeUnmappedColumn(Column col) {
         if (this.columnNames.length() > 0) {
            this.columnNames.append(',');
            this.columnValues.append(',');
         }

         this.columnNames.append(col.getIdentifier());
         ColumnMetaData colmd = col.getColumnMetaData();
         String value = colmd.getInsertValue();
         if (value != null && value.equalsIgnoreCase("#NULL")) {
            value = null;
         }

         if (MetaDataUtils.isJdbcTypeString(colmd.getJdbcType())) {
            if (value != null) {
               value = "'" + value + "'";
            } else if (!col.isNullable()) {
               value = "''";
            }
         }

         this.columnValues.append(value);
      }

      private StatementMappingIndex[] processExternalMapping(JavaTypeMapping mapping, StatementMappingIndex[] fieldStmtExprIndex, StatementMappingIndex[] stmtExprIndex) {
         for(int i = 0; i < fieldStmtExprIndex.length; ++i) {
            if (fieldStmtExprIndex[i] != null && fieldStmtExprIndex[i].getMapping() == mapping) {
               return stmtExprIndex;
            }
         }

         int pos = 0;
         if (stmtExprIndex == null) {
            stmtExprIndex = new StatementMappingIndex[1];
            pos = 0;
         } else {
            for(int i = 0; i < stmtExprIndex.length; ++i) {
               if (stmtExprIndex[i].getMapping() == mapping) {
                  return stmtExprIndex;
               }
            }

            StatementMappingIndex[] tmpStmtExprIndex = stmtExprIndex;
            stmtExprIndex = new StatementMappingIndex[stmtExprIndex.length + 1];

            for(int i = 0; i < tmpStmtExprIndex.length; ++i) {
               stmtExprIndex[i] = tmpStmtExprIndex[i];
            }

            pos = tmpStmtExprIndex.length;
         }

         stmtExprIndex[pos] = new StatementMappingIndex(mapping);
         int[] param = new int[mapping.getNumberOfDatastoreMappings()];

         for(int i = 0; i < mapping.getNumberOfDatastoreMappings(); ++i) {
            if (this.columnNames.length() > 0) {
               this.columnNames.append(',');
               this.columnValues.append(',');
            }

            this.columnNames.append(mapping.getDatastoreMapping(i).getColumn().getIdentifier());
            this.columnValues.append(((AbstractDatastoreMapping)mapping.getDatastoreMapping(i)).getUpdateInputParameter());
            param[i] = this.paramIndex++;
         }

         stmtExprIndex[pos].addParameterOccurrence(param);
         return stmtExprIndex;
      }

      public List getMappingCallbacks() {
         return this.mc;
      }

      public int[] getInsertFieldNumbers() {
         int[] fieldNumbers = new int[this.insertFields.size()];

         for(int i = 0; i < this.insertFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.insertFields.get(i);
         }

         return fieldNumbers;
      }

      public int[] getPrimaryKeyFieldNumbers() {
         int[] fieldNumbers = new int[this.pkFields.size()];

         for(int i = 0; i < this.pkFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.pkFields.get(i);
         }

         return fieldNumbers;
      }

      public int[] getReachableFieldNumbers() {
         int[] fieldNumbers = new int[this.reachableFields.size()];

         for(int i = 0; i < this.reachableFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.reachableFields.get(i);
         }

         return fieldNumbers;
      }

      public int[] getRelationFieldNumbers() {
         int[] fieldNumbers = new int[this.relationFields.size()];

         for(int i = 0; i < this.relationFields.size(); ++i) {
            fieldNumbers[i] = (Integer)this.relationFields.get(i);
         }

         return fieldNumbers;
      }

      public StatementMappingIndex[] getStatementMappings() {
         return this.statementMappings;
      }

      public StatementMappingIndex[] getReachableStatementMappings() {
         return this.retrievedStatementMappings;
      }

      public StatementMappingIndex getVersionStatementMapping() {
         return this.versionStatementMapping;
      }

      public StatementMappingIndex getDiscriminatorStatementMapping() {
         return this.discriminatorStatementMapping;
      }

      public StatementMappingIndex getMultitenancyStatementMapping() {
         return this.multitenancyStatementMapping;
      }

      public StatementMappingIndex[] getExternalFKStatementMapping() {
         return this.externalFKStmtExprIndex;
      }

      public StatementMappingIndex[] getExternalFKDiscrimStatementMapping() {
         return this.externalFKDiscrimStmtExprIndex;
      }

      public StatementMappingIndex[] getExternalOrderStatementMapping() {
         return this.externalOrderStmtExprIndex;
      }

      public String getInsertStmt() {
         return this.columnNames.length() > 0 && this.columnValues.length() > 0 ? "INSERT INTO " + InsertRequest.this.table.toString() + " (" + this.columnNames + ") VALUES (" + this.columnValues + ")" : InsertRequest.this.table.getStoreManager().getDatastoreAdapter().getInsertStatementForNoColumns(InsertRequest.this.table);
      }
   }
}
