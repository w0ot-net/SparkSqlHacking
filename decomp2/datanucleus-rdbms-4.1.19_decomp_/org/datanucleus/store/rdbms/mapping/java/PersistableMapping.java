package org.datanucleus.store.rdbms.mapping.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.NucleusContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusObjectNotFoundException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.identity.IdentityUtils;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.ColumnMetaData;
import org.datanucleus.metadata.FieldMetaData;
import org.datanucleus.metadata.FieldRole;
import org.datanucleus.metadata.IdentityStrategy;
import org.datanucleus.metadata.IdentityType;
import org.datanucleus.metadata.InheritanceStrategy;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.metadata.VersionMetaData;
import org.datanucleus.metadata.VersionStrategy;
import org.datanucleus.state.AppIdObjectIdFieldConsumer;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.exceptions.NotYetFlushedException;
import org.datanucleus.store.exceptions.ReachableObjectNotCascadedException;
import org.datanucleus.store.fieldmanager.FieldManager;
import org.datanucleus.store.fieldmanager.SingleValueFieldManager;
import org.datanucleus.store.rdbms.mapping.AppIDObjectIdFieldManager;
import org.datanucleus.store.rdbms.mapping.CorrespondentColumnsMapper;
import org.datanucleus.store.rdbms.mapping.MappingCallbacks;
import org.datanucleus.store.rdbms.mapping.MappingHelper;
import org.datanucleus.store.rdbms.mapping.MappingManager;
import org.datanucleus.store.rdbms.mapping.datastore.DatastoreMapping;
import org.datanucleus.store.rdbms.table.Column;
import org.datanucleus.store.rdbms.table.DatastoreClass;
import org.datanucleus.store.rdbms.table.Table;
import org.datanucleus.store.scostore.PersistableRelationStore;
import org.datanucleus.store.types.SCOCollection;
import org.datanucleus.util.ClassUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class PersistableMapping extends MultiMapping implements MappingCallbacks {
   protected AbstractClassMetaData cmd;

   public Class getJavaType() {
      return null;
   }

   public void initialize(AbstractMemberMetaData mmd, Table table, ClassLoaderResolver clr) {
      super.initialize(mmd, table, clr);
      this.prepareDatastoreMapping(clr);
   }

   protected void prepareDatastoreMapping(ClassLoaderResolver clr) {
      if (this.roleForMember != FieldRole.ROLE_COLLECTION_ELEMENT && this.roleForMember != FieldRole.ROLE_ARRAY_ELEMENT && this.roleForMember != FieldRole.ROLE_MAP_KEY && this.roleForMember != FieldRole.ROLE_MAP_VALUE) {
         AbstractClassMetaData refCmd = this.storeMgr.getNucleusContext().getMetaDataManager().getMetaDataForClass(this.mmd.getType(), clr);
         JavaTypeMapping referenceMapping = null;
         if (refCmd == null) {
            throw new NucleusUserException("You have a field " + this.mmd.getFullFieldName() + " that has type " + this.mmd.getTypeName() + " but this type has no known metadata. Your mapping is incorrect");
         }

         if (refCmd.getInheritanceMetaData() != null && refCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.SUBCLASS_TABLE) {
            AbstractClassMetaData[] cmds = this.storeMgr.getClassesManagingTableForClass(refCmd, clr);
            if (cmds == null || cmds.length <= 0) {
               return;
            }

            if (cmds.length > 1) {
               NucleusLogger.PERSISTENCE.warn("Field " + this.mmd.getFullFieldName() + " represents either a 1-1 relation, or a N-1 relation where the other end uses \"subclass-table\" inheritance strategy and more than 1 subclasses with a table. This is not fully supported");
            }

            referenceMapping = this.storeMgr.getDatastoreClass(cmds[0].getFullClassName(), clr).getIdMapping();
         } else if (refCmd.getInheritanceMetaData() != null && refCmd.getInheritanceMetaData().getStrategy() == InheritanceStrategy.COMPLETE_TABLE) {
            DatastoreClass refTable = null;
            if (refCmd instanceof ClassMetaData && !((ClassMetaData)refCmd).isAbstract()) {
               refTable = this.storeMgr.getDatastoreClass(refCmd.getFullClassName(), clr);
            } else {
               Collection<String> refSubclasses = this.storeMgr.getSubClassesForClass(refCmd.getFullClassName(), true, clr);
               if (refSubclasses != null && !refSubclasses.isEmpty()) {
                  String refSubclassName = (String)refSubclasses.iterator().next();
                  refTable = this.storeMgr.getDatastoreClass(refSubclassName, clr);
                  if (refSubclasses.size() > 1) {
                     NucleusLogger.DATASTORE_SCHEMA.info("Field " + this.mmd.getFullFieldName() + " is a 1-1/N-1 relation and the other side had multiple possible classes to which to create a foreign-key. Using first possible (" + refSubclassName + ")");
                  }
               }
            }

            if (refTable == null) {
               throw new NucleusUserException("Field " + this.mmd.getFullFieldName() + " represents either a 1-1 relation, or a N-1 relation where the other end uses \"complete-table\" inheritance strategy and either no table was found, or multiple possible tables!");
            }

            referenceMapping = refTable.getIdMapping();
         } else {
            referenceMapping = this.storeMgr.getDatastoreClass(this.mmd.getType().getName(), clr).getIdMapping();
         }

         CorrespondentColumnsMapper correspondentColumnsMapping = new CorrespondentColumnsMapper(this.mmd, referenceMapping, true);
         RelationType relationType = this.mmd.getRelationType(clr);
         boolean createDatastoreMappings = true;
         if (relationType == RelationType.MANY_TO_ONE_BI) {
            AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
            createDatastoreMappings = relatedMmds[0].getJoinMetaData() == null;
         } else if (relationType == RelationType.ONE_TO_ONE_BI) {
            createDatastoreMappings = this.mmd.getMappedBy() == null;
         }

         if (relationType == RelationType.MANY_TO_ONE_UNI) {
            this.storeMgr.newJoinTable(this.mmd, clr);
         } else {
            for(int i = 0; i < referenceMapping.getNumberOfDatastoreMappings(); ++i) {
               DatastoreMapping refDatastoreMapping = referenceMapping.getDatastoreMapping(i);
               JavaTypeMapping mapping = this.storeMgr.getMappingManager().getMapping(refDatastoreMapping.getJavaTypeMapping().getJavaType());
               this.addJavaTypeMapping(mapping);
               if (createDatastoreMappings) {
                  ColumnMetaData colmd = correspondentColumnsMapping.getColumnMetaDataByIdentifier(refDatastoreMapping.getColumn().getIdentifier());
                  if (colmd == null) {
                     throw (new NucleusUserException(Localiser.msg("041038", new Object[]{refDatastoreMapping.getColumn().getIdentifier(), this.toString()}))).setFatal();
                  }

                  MappingManager mmgr = this.storeMgr.getMappingManager();
                  Column col = mmgr.createColumn(this.mmd, this.table, mapping, colmd, refDatastoreMapping.getColumn(), clr);
                  DatastoreMapping datastoreMapping = mmgr.createDatastoreMapping(mapping, col, refDatastoreMapping.getJavaTypeMapping().getJavaTypeForDatastoreMapping(i));
                  this.addDatastoreMapping(datastoreMapping);
               } else {
                  mapping.setReferenceMapping(referenceMapping);
               }
            }
         }
      }

   }

   public Object getValueForDatastoreMapping(NucleusContext nucleusCtx, int index, Object value) {
      ExecutionContext ec = nucleusCtx.getApiAdapter().getExecutionContext(value);
      if (this.cmd == null) {
         this.cmd = nucleusCtx.getMetaDataManager().getMetaDataForClass(this.getType(), ec != null ? ec.getClassLoaderResolver() : nucleusCtx.getClassLoaderResolver((ClassLoader)null));
      }

      if (this.cmd.getIdentityType() == IdentityType.APPLICATION) {
         AbstractMemberMetaData mmd = this.cmd.getMetaDataForManagedMemberAtAbsolutePosition(this.cmd.getPKMemberPositions()[index]);
         ObjectProvider op = null;
         if (ec != null) {
            op = ec.findObjectProvider(value);
         }

         if (op == null) {
            return mmd instanceof FieldMetaData ? ClassUtils.getValueOfFieldByReflection(value, mmd.getName()) : ClassUtils.getValueOfMethodByReflection(value, ClassUtils.getJavaBeanGetterName(mmd.getName(), false), new Object[0]);
         } else {
            if (!mmd.isPrimaryKey()) {
               op.isLoaded(mmd.getAbsoluteFieldNumber());
            }

            FieldManager fm = new SingleValueFieldManager();
            op.provideFields(new int[]{mmd.getAbsoluteFieldNumber()}, fm);
            return fm.fetchObjectField(mmd.getAbsoluteFieldNumber());
         }
      } else if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
         Object id = nucleusCtx.getApiAdapter().getIdForObject(value);
         return id != null ? IdentityUtils.getTargetKeyForDatastoreIdentity(id) : null;
      } else {
         return null;
      }
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] param, Object value) {
      this.setObject(ec, ps, param, value, (ObjectProvider)null, -1);
   }

   public void setObject(ExecutionContext ec, PreparedStatement ps, int[] param, Object value, ObjectProvider ownerOP, int ownerFieldNumber) {
      if (value == null) {
         this.setObjectAsNull(ec, ps, param);
      } else {
         this.setObjectAsValue(ec, ps, param, value, ownerOP, ownerFieldNumber);
      }

   }

   private void setObjectAsNull(ExecutionContext ec, PreparedStatement ps, int[] param) {
      int n = 0;

      for(int i = 0; i < this.javaTypeMappings.length; ++i) {
         JavaTypeMapping mapping = this.javaTypeMappings[i];
         if (mapping.getNumberOfDatastoreMappings() > 0) {
            int[] posMapping = new int[mapping.getNumberOfDatastoreMappings()];

            for(int j = 0; j < posMapping.length; ++j) {
               posMapping[j] = param[n++];
            }

            mapping.setObject(ec, ps, posMapping, (Object)null);
         }
      }

   }

   private boolean hasDatastoreAttributedPrimaryKeyValues(MetaDataManager mdm, StoreManager srm, ClassLoaderResolver clr) {
      boolean hasDatastoreAttributedPrimaryKeyValues = false;
      if (this.mmd != null && this.roleForMember != FieldRole.ROLE_ARRAY_ELEMENT && this.roleForMember != FieldRole.ROLE_COLLECTION_ELEMENT && this.roleForMember != FieldRole.ROLE_MAP_KEY && this.roleForMember != FieldRole.ROLE_MAP_VALUE) {
         AbstractClassMetaData acmd = mdm.getMetaDataForClass(this.mmd.getType(), clr);
         if (acmd.getIdentityType() == IdentityType.APPLICATION) {
            for(int i = 0; i < acmd.getPKMemberPositions().length; ++i) {
               IdentityStrategy strategy = acmd.getMetaDataForManagedMemberAtAbsolutePosition(acmd.getPKMemberPositions()[i]).getValueStrategy();
               if (strategy != null) {
                  hasDatastoreAttributedPrimaryKeyValues |= srm.isStrategyDatastoreAttributed(acmd, acmd.getPKMemberPositions()[i]);
               }
            }
         }
      }

      return hasDatastoreAttributedPrimaryKeyValues;
   }

   private void setObjectAsValue(ExecutionContext ec, PreparedStatement ps, int[] param, Object value, ObjectProvider ownerOP, int ownerFieldNumber) {
      ApiAdapter api = ec.getApiAdapter();
      if (!api.isPersistable(value)) {
         throw (new NucleusException(Localiser.msg("041016", new Object[]{value.getClass(), value}))).setFatal();
      } else {
         ObjectProvider valueOP = ec.findObjectProvider(value);

         try {
            ClassLoaderResolver clr = ec.getClassLoaderResolver();
            boolean hasDatastoreAttributedPrimaryKeyValues = this.hasDatastoreAttributedPrimaryKeyValues(ec.getMetaDataManager(), this.storeMgr, clr);
            boolean inserted = false;
            if (ownerFieldNumber >= 0) {
               inserted = this.storeMgr.isObjectInserted(valueOP, ownerFieldNumber);
            } else if (this.mmd == null) {
               inserted = this.storeMgr.isObjectInserted(valueOP, this.type);
            }

            if (valueOP != null) {
               if (ec.getApiAdapter().isDetached(value) && valueOP.getReferencedPC() != null && ownerOP != null && this.mmd != null) {
                  ownerOP.replaceFieldMakeDirty(ownerFieldNumber, valueOP.getReferencedPC());
               }

               if (valueOP.isWaitingToBeFlushedToDatastore()) {
                  try {
                     valueOP.flush();
                  } catch (NotYetFlushedException var30) {
                     ownerOP.updateFieldAfterInsert(value, ownerFieldNumber);
                     this.setObjectAsNull(ec, ps, param);
                     return;
                  }
               }
            } else if (ec.getApiAdapter().isDetached(value)) {
               Object attachedValue = ec.persistObjectInternal(value, (ObjectProvider)null, -1, 0);
               if (attachedValue != value && ownerOP != null) {
                  ownerOP.replaceFieldMakeDirty(ownerFieldNumber, attachedValue);
                  value = attachedValue;
               }

               valueOP = ec.findObjectProvider(value);
            }

            if (inserted || !ec.isInserting(value) || !hasDatastoreAttributedPrimaryKeyValues && this.mmd != null && this.mmd.isPrimaryKey() || !hasDatastoreAttributedPrimaryKeyValues && ownerOP == valueOP && api.getIdForObject(value) != null) {
               Object id = api.getIdForObject(value);
               boolean requiresPersisting = false;
               if (ec.getApiAdapter().isDetached(value) && ownerOP != null) {
                  if (ownerOP.isInserting()) {
                     if (!ec.getBooleanProperty("datanucleus.attachSameDatastore") && ec.getObjectFromCache(api.getIdForObject(value)) == null) {
                        try {
                           Object obj = ec.findObject(api.getIdForObject(value), true, false, value.getClass().getName());
                           if (obj != null) {
                              ObjectProvider objOP = ec.findObjectProvider(obj);
                              if (objOP != null) {
                                 ec.evictFromTransaction(objOP);
                              }

                              ec.removeObjectFromLevel1Cache(api.getIdForObject(value));
                           }
                        } catch (NucleusObjectNotFoundException var29) {
                           requiresPersisting = true;
                        }
                     }
                  } else {
                     requiresPersisting = true;
                  }
               } else if (id == null) {
                  requiresPersisting = true;
               } else {
                  ExecutionContext pcEC = ec.getApiAdapter().getExecutionContext(value);
                  if (pcEC != null && ec != pcEC) {
                     throw new NucleusUserException(Localiser.msg("041015"), id);
                  }
               }

               if (requiresPersisting) {
                  if (this.mmd != null && !this.mmd.isCascadePersist() && !ec.getApiAdapter().isDetached(value)) {
                     if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug(Localiser.msg("007006", new Object[]{this.mmd.getFullFieldName()}));
                     }

                     throw new ReachableObjectNotCascadedException(this.mmd.getFullFieldName(), value);
                  }

                  if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                     NucleusLogger.PERSISTENCE.debug(Localiser.msg("007007", new Object[]{this.mmd != null ? this.mmd.getFullFieldName() : null}));
                  }

                  try {
                     Object pcNew = ec.persistObjectInternal(value, (ObjectProvider)null, -1, 0);
                     if (hasDatastoreAttributedPrimaryKeyValues) {
                        ec.flushInternal(false);
                     }

                     id = api.getIdForObject(pcNew);
                     if (ec.getApiAdapter().isDetached(value) && ownerOP != null) {
                        ownerOP.replaceFieldMakeDirty(ownerFieldNumber, pcNew);
                        RelationType relationType = this.mmd.getRelationType(clr);
                        if (relationType == RelationType.MANY_TO_ONE_BI) {
                           if (NucleusLogger.PERSISTENCE.isInfoEnabled()) {
                              NucleusLogger.PERSISTENCE.info("PCMapping.setObject : object " + ownerOP.getInternalObjectId() + " has field " + ownerFieldNumber + " that is 1-N bidirectional. Have just attached the N side so should really update the reference in the 1 side collection to refer to this attached object. Not yet implemented");
                           }
                        } else if (relationType == RelationType.ONE_TO_ONE_BI) {
                           AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
                           ObjectProvider relatedOP = ec.findObjectProvider(pcNew);
                           relatedOP.replaceFieldMakeDirty(relatedMmds[0].getAbsoluteFieldNumber(), ownerOP.getObject());
                        }
                     }
                  } catch (NotYetFlushedException var28) {
                     this.setObjectAsNull(ec, ps, param);
                     throw new NotYetFlushedException(value);
                  }
               }

               if (valueOP != null) {
                  valueOP.setStoringPC();
               }

               if (this.getNumberOfDatastoreMappings() > 0) {
                  if (IdentityUtils.isDatastoreIdentity(id)) {
                     Object idKey = IdentityUtils.getTargetKeyForDatastoreIdentity(id);

                     try {
                        this.getDatastoreMapping(0).setObject(ps, param[0], idKey);
                     } catch (Exception var27) {
                        this.getDatastoreMapping(0).setObject(ps, param[0], idKey.toString());
                     }

                  } else {
                     boolean fieldsSet = false;
                     if (IdentityUtils.isSingleFieldIdentity(id) && this.javaTypeMappings.length > 1) {
                        Object key = IdentityUtils.getTargetKeyForSingleFieldIdentity(id);
                        AbstractClassMetaData keyCmd = ec.getMetaDataManager().getMetaDataForClass(key.getClass(), clr);
                        if (keyCmd != null && keyCmd.getIdentityType() == IdentityType.NONDURABLE) {
                           ObjectProvider keyOP = ec.findObjectProvider(key);
                           int[] fieldNums = keyCmd.getAllMemberPositions();
                           FieldManager fm = new AppIDObjectIdFieldManager(param, ec, ps, this.javaTypeMappings);

                           for(int i = 0; i < fieldNums.length; ++i) {
                              keyOP.provideFields(new int[]{fieldNums[i]}, fm);
                           }

                           fieldsSet = true;
                        }
                     }

                     if (!fieldsSet) {
                        FieldManager fm = new AppIDObjectIdFieldManager(param, ec, ps, this.javaTypeMappings);
                        api.copyKeyFieldsFromIdToObject(value, new AppIdObjectIdFieldConsumer(api, fm), id);
                     }
                  }
               }
            } else {
               if (valueOP != null) {
                  valueOP.setStoringPC();
               }

               if (this.getNumberOfDatastoreMappings() > 0) {
                  this.setObjectAsNull(ec, ps, param);
                  throw new NotYetFlushedException(value);
               }
            }
         } finally {
            if (valueOP != null) {
               valueOP.unsetStoringPC();
            }

         }
      }
   }

   public Object getObject(ExecutionContext ec, ResultSet rs, int[] resultIndexes) {
      if (this.storeMgr.getResultValueAtPosition(rs, this, resultIndexes[0]) == null) {
         return null;
      } else {
         if (this.cmd == null) {
            this.cmd = ec.getMetaDataManager().getMetaDataForClass(this.getType(), ec.getClassLoaderResolver());
         }

         Object pc = null;
         if (this.cmd.getIdentityType() == IdentityType.DATASTORE) {
            pc = MappingHelper.getObjectForDatastoreIdentity(ec, this, rs, resultIndexes, this.cmd);
         } else {
            if (this.cmd.getIdentityType() != IdentityType.APPLICATION) {
               return null;
            }

            pc = MappingHelper.getObjectForApplicationIdentity(ec, this, rs, resultIndexes, this.cmd);
         }

         ObjectProvider pcOP = ec.findObjectProvider(pc);
         if (pcOP != null) {
            VersionMetaData vermd = this.cmd.getVersionMetaDataForTable();
            if (vermd != null && vermd.getVersionStrategy() != VersionStrategy.NONE && ec.getTransaction().getOptimistic() && pcOP.getVersion() == null) {
               pcOP.loadUnloadedFieldsInFetchPlan();
            }
         }

         return pc;
      }
   }

   public void postFetch(ObjectProvider op) {
   }

   public void insertPostProcessing(ObjectProvider op) {
   }

   public void postInsert(ObjectProvider op) {
      Object pc = op.provideField(this.mmd.getAbsoluteFieldNumber());
      if (pc != null) {
         ClassLoaderResolver clr = op.getExecutionContext().getClassLoaderResolver();
         AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
         RelationType relationType = this.mmd.getRelationType(clr);
         if (relationType == RelationType.ONE_TO_ONE_BI) {
            ObjectProvider otherOP = op.getExecutionContext().findObjectProvider(pc);
            AbstractMemberMetaData relatedMmd = this.mmd.getRelatedMemberMetaDataForObject(clr, op.getObject(), pc);
            if (relatedMmd == null) {
               throw new NucleusUserException("You have a field " + this.mmd.getFullFieldName() + " that is 1-1 bidir yet cannot find the equivalent field at the other side. Why is that?");
            }

            Object relatedValue = otherOP.provideField(relatedMmd.getAbsoluteFieldNumber());
            if (relatedValue == null) {
               if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                  NucleusLogger.PERSISTENCE.debug(Localiser.msg("041018", new Object[]{op.getObjectAsPrintable(), this.mmd.getFullFieldName(), StringUtils.toJVMIDString(pc), relatedMmd.getFullFieldName()}));
               }

               otherOP.replaceField(relatedMmd.getAbsoluteFieldNumber(), op.getObject());
            } else if (relatedValue != op.getObject()) {
               throw new NucleusUserException(Localiser.msg("041020", new Object[]{op.getObjectAsPrintable(), this.mmd.getFullFieldName(), StringUtils.toJVMIDString(pc), StringUtils.toJVMIDString(relatedValue)}));
            }
         } else if (relationType == RelationType.MANY_TO_ONE_BI && relatedMmds[0].hasCollection()) {
            ObjectProvider otherOP = op.getExecutionContext().findObjectProvider(pc);
            if (otherOP != null) {
               Collection relatedColl = (Collection)otherOP.provideField(relatedMmds[0].getAbsoluteFieldNumber());
               if (relatedColl != null && !(relatedColl instanceof SCOCollection)) {
                  boolean contained = relatedColl.contains(op.getObject());
                  if (!contained) {
                     NucleusLogger.PERSISTENCE.info(Localiser.msg("041022", new Object[]{op.getObjectAsPrintable(), this.mmd.getFullFieldName(), StringUtils.toJVMIDString(pc), relatedMmds[0].getFullFieldName()}));
                  }
               }
            }
         } else if (relationType == RelationType.MANY_TO_ONE_UNI) {
            ObjectProvider otherOP = op.getExecutionContext().findObjectProvider(pc);
            if (otherOP == null) {
               Object other = op.getExecutionContext().persistObjectInternal(pc, (ObjectProvider)null, -1, 0);
               otherOP = op.getExecutionContext().findObjectProvider(other);
            }

            PersistableRelationStore store = (PersistableRelationStore)this.storeMgr.getBackingStoreForField(clr, this.mmd, this.mmd.getType());
            store.add(op, otherOP);
         }

      }
   }

   public void postUpdate(ObjectProvider op) {
      Object pc = op.provideField(this.mmd.getAbsoluteFieldNumber());
      ClassLoaderResolver clr = op.getExecutionContext().getClassLoaderResolver();
      RelationType relationType = this.mmd.getRelationType(clr);
      if (pc == null) {
         if (relationType == RelationType.MANY_TO_ONE_UNI) {
            PersistableRelationStore store = (PersistableRelationStore)this.storeMgr.getBackingStoreForField(clr, this.mmd, this.mmd.getType());
            store.remove(op);
         }

      } else {
         ObjectProvider otherOP = op.getExecutionContext().findObjectProvider(pc);
         if (otherOP == null && (relationType == RelationType.ONE_TO_ONE_BI || relationType == RelationType.MANY_TO_ONE_BI || relationType == RelationType.MANY_TO_ONE_UNI)) {
            Object other = op.getExecutionContext().persistObjectInternal(pc, (ObjectProvider)null, -1, 0);
            otherOP = op.getExecutionContext().findObjectProvider(other);
         }

         if (relationType == RelationType.MANY_TO_ONE_UNI) {
            PersistableRelationStore store = (PersistableRelationStore)this.storeMgr.getBackingStoreForField(clr, this.mmd, this.mmd.getType());
            store.update(op, otherOP);
         }

      }
   }

   public void preDelete(ObjectProvider op) {
      int fieldNumber = this.mmd.getAbsoluteFieldNumber();
      if (!op.isFieldLoaded(fieldNumber)) {
         try {
            op.loadField(fieldNumber);
         } catch (NucleusObjectNotFoundException var15) {
            return;
         }
      }

      Object pc = op.provideField(fieldNumber);
      if (pc != null) {
         ExecutionContext ec = op.getExecutionContext();
         ClassLoaderResolver clr = ec.getClassLoaderResolver();
         RelationType relationType = this.mmd.getRelationType(clr);
         if (relationType == RelationType.MANY_TO_ONE_UNI) {
            PersistableRelationStore store = (PersistableRelationStore)this.storeMgr.getBackingStoreForField(clr, this.mmd, this.mmd.getType());
            store.remove(op);
         }

         boolean dependent = this.mmd.isDependent();
         if (this.mmd.isCascadeRemoveOrphans()) {
            dependent = true;
         }

         AbstractMemberMetaData[] relatedMmds = this.mmd.getRelatedMemberMetaData(clr);
         boolean hasFK = false;
         if (!dependent) {
            if (this.mmd.getForeignKeyMetaData() != null) {
               hasFK = true;
            }

            if (relatedMmds != null && relatedMmds[0].getForeignKeyMetaData() != null) {
               hasFK = true;
            }

            if (ec.getStringProperty("datanucleus.deletionPolicy").equals("JDO2")) {
               hasFK = false;
            }
         }

         if (relationType == RelationType.ONE_TO_ONE_UNI || relationType == RelationType.ONE_TO_ONE_BI && this.mmd.getMappedBy() == null) {
            if (dependent) {
               boolean relatedObjectDeleted = ec.getApiAdapter().isDeleted(pc);
               if (this.isNullable() && !relatedObjectDeleted) {
                  op.replaceFieldMakeDirty(fieldNumber, (Object)null);
                  this.storeMgr.getPersistenceHandler().updateObject(op, new int[]{fieldNumber});
                  if (!relatedObjectDeleted) {
                     ec.deleteObjectInternal(pc);
                  }
               } else {
                  NucleusLogger.DATASTORE_PERSIST.warn("Delete of " + StringUtils.toJVMIDString(op.getObject()) + " needs delete of related object at " + this.mmd.getFullFieldName() + " but cannot delete it direct since FK is here");
               }
            } else {
               AbstractMemberMetaData relatedMmd = this.mmd.getRelatedMemberMetaDataForObject(clr, op.getObject(), pc);
               if (relatedMmd != null) {
                  ObjectProvider otherOP = ec.findObjectProvider(pc);
                  if (otherOP != null) {
                     Object currentValue = otherOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (currentValue != null) {
                        if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                           NucleusLogger.PERSISTENCE.debug(Localiser.msg("041019", new Object[]{StringUtils.toJVMIDString(pc), relatedMmd.getFullFieldName(), op.getObjectAsPrintable()}));
                        }

                        otherOP.replaceFieldMakeDirty(relatedMmd.getAbsoluteFieldNumber(), (Object)null);
                        if (ec.getManageRelations()) {
                           otherOP.getExecutionContext().getRelationshipManager(otherOP).relationChange(relatedMmd.getAbsoluteFieldNumber(), op.getObject(), (Object)null);
                        }
                     }
                  }
               }
            }
         } else if (relationType == RelationType.ONE_TO_ONE_BI && this.mmd.getMappedBy() != null) {
            DatastoreClass relatedTable = this.storeMgr.getDatastoreClass(relatedMmds[0].getClassName(), clr);
            JavaTypeMapping relatedMapping = relatedTable.getMemberMapping(relatedMmds[0]);
            boolean isNullable = relatedMapping.isNullable();
            ObjectProvider otherOP = ec.findObjectProvider(pc);
            if (dependent) {
               if (isNullable) {
                  otherOP.replaceFieldMakeDirty(relatedMmds[0].getAbsoluteFieldNumber(), (Object)null);
                  this.storeMgr.getPersistenceHandler().updateObject(otherOP, new int[]{relatedMmds[0].getAbsoluteFieldNumber()});
               }

               ec.deleteObjectInternal(pc);
            } else if (!hasFK && this.isNullable()) {
               Object currentRelatedValue = otherOP.provideField(relatedMmds[0].getAbsoluteFieldNumber());
               if (currentRelatedValue != null) {
                  otherOP.replaceFieldMakeDirty(relatedMmds[0].getAbsoluteFieldNumber(), (Object)null);
                  this.storeMgr.getPersistenceHandler().updateObject(otherOP, new int[]{relatedMmds[0].getAbsoluteFieldNumber()});
                  if (ec.getManageRelations()) {
                     otherOP.getExecutionContext().getRelationshipManager(otherOP).relationChange(relatedMmds[0].getAbsoluteFieldNumber(), op.getObject(), (Object)null);
                  }
               }
            }
         } else if (relationType == RelationType.MANY_TO_ONE_BI) {
            ObjectProvider otherOP = ec.findObjectProvider(pc);
            if (relatedMmds[0].getJoinMetaData() == null) {
               if (!otherOP.isDeleting()) {
                  if (dependent) {
                     if (this.isNullable()) {
                        op.replaceFieldMakeDirty(fieldNumber, (Object)null);
                        this.storeMgr.getPersistenceHandler().updateObject(op, new int[]{fieldNumber});
                     }

                     if (!ec.getApiAdapter().isDeleted(pc)) {
                        ec.deleteObjectInternal(pc);
                     }
                  } else if (relatedMmds[0].hasCollection()) {
                     if (!ec.getApiAdapter().isDeleted(otherOP.getObject()) && !otherOP.isDeleting()) {
                        ec.markDirty(otherOP, false);
                        otherOP.isLoaded(relatedMmds[0].getAbsoluteFieldNumber());
                        Collection otherColl = (Collection)otherOP.provideField(relatedMmds[0].getAbsoluteFieldNumber());
                        if (otherColl != null) {
                           if (ec.getManageRelations()) {
                              otherOP.getExecutionContext().getRelationshipManager(otherOP).relationRemove(relatedMmds[0].getAbsoluteFieldNumber(), op.getObject());
                           }

                           NucleusLogger.PERSISTENCE.debug("ManagedRelationships : delete of object causes removal from collection at " + relatedMmds[0].getFullFieldName());
                           otherColl.remove(op.getObject());
                        }
                     }
                  } else if (relatedMmds[0].hasMap()) {
                  }
               }
            } else if (dependent) {
               ec.deleteObjectInternal(pc);
            } else if (relatedMmds[0].hasCollection()) {
               if (!ec.getApiAdapter().isDeleted(otherOP.getObject()) && !otherOP.isDeleting()) {
                  ec.markDirty(otherOP, false);
                  otherOP.isLoaded(relatedMmds[0].getAbsoluteFieldNumber());
                  Collection otherColl = (Collection)otherOP.provideField(relatedMmds[0].getAbsoluteFieldNumber());
                  if (otherColl != null) {
                     NucleusLogger.PERSISTENCE.debug("ManagedRelationships : delete of object causes removal from collection at " + relatedMmds[0].getFullFieldName());
                     otherColl.remove(op.getObject());
                  }
               }
            } else if (relatedMmds[0].hasMap()) {
            }
         } else if (relationType == RelationType.MANY_TO_ONE_UNI && dependent) {
            ec.deleteObjectInternal(pc);
         }

      }
   }
}
