package org.datanucleus.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.RelationType;
import org.datanucleus.store.types.SCOCollection;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class RelationshipManagerImpl implements RelationshipManager {
   final ObjectProvider ownerOP;
   final ExecutionContext ec;
   final Object pc;
   final Map fieldChanges;

   public RelationshipManagerImpl(ObjectProvider op) {
      this.ownerOP = op;
      this.ec = op.getExecutionContext();
      this.pc = op.getObject();
      this.fieldChanges = new HashMap();
   }

   public void clearFields() {
      this.fieldChanges.clear();
   }

   public void relationChange(int fieldNumber, Object oldValue, Object newValue) {
      if (!this.ec.isManagingRelations()) {
         Integer fieldKey = fieldNumber;
         List<RelationChange> changes = (List)this.fieldChanges.get(fieldKey);
         if (changes == null) {
            changes = new ArrayList();
            this.fieldChanges.put(fieldKey, changes);
         }

         AbstractMemberMetaData mmd = this.ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         RelationType relationType = mmd.getRelationType(this.ec.getClassLoaderResolver());
         if (relationType != RelationType.ONE_TO_ONE_BI && relationType != RelationType.MANY_TO_ONE_BI) {
            if ((relationType == RelationType.ONE_TO_MANY_BI || relationType == RelationType.MANY_TO_MANY_BI) && mmd.hasCollection()) {
               if (oldValue == null) {
                  if (newValue != null) {
                     Iterator iter = ((Collection)newValue).iterator();

                     while(iter.hasNext()) {
                        changes.add(new RelationChange(RelationshipManagerImpl.ChangeType.ADD_OBJECT, iter.next()));
                     }
                  }
               } else if (newValue == null) {
                  AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(this.ec.getClassLoaderResolver())[0];

                  for(Object element : (Collection)oldValue) {
                     if (this.ownerOP.getLifecycleState().isDeleted) {
                        this.ec.removeObjectFromLevel2Cache(this.ec.getApiAdapter().getIdForObject(element));
                        ObjectProvider elementOP = this.ec.findObjectProvider(element);
                        if (relationType == RelationType.ONE_TO_MANY_BI) {
                           this.ec.getRelationshipManager(elementOP).relationChange(relatedMmd.getAbsoluteFieldNumber(), this.ownerOP.getObject(), (Object)null);
                        } else if (relationType == RelationType.MANY_TO_MANY_BI) {
                           this.ec.getRelationshipManager(elementOP).relationRemove(relatedMmd.getAbsoluteFieldNumber(), this.ownerOP.getObject());
                        }
                     } else {
                        changes.add(new RelationChange(RelationshipManagerImpl.ChangeType.REMOVE_OBJECT, element));
                     }
                  }
               } else {
                  for(Object newElem : (Collection)newValue) {
                     Iterator oldIter = ((Collection)oldValue).iterator();
                     boolean alreadyExists = false;

                     while(oldIter.hasNext()) {
                        Object oldElem = oldIter.next();
                        if (newElem == oldElem) {
                           alreadyExists = true;
                           break;
                        }
                     }

                     if (!alreadyExists) {
                        ObjectProvider elemOP = this.ec.findObjectProvider(newElem);
                        if (elemOP != null) {
                           AbstractMemberMetaData elemMmd = mmd.getRelatedMemberMetaData(this.ec.getClassLoaderResolver())[0];
                           Object oldOwner = elemOP.provideField(elemMmd.getAbsoluteFieldNumber());
                           if (!elemOP.isFieldLoaded(elemMmd.getAbsoluteFieldNumber())) {
                              elemOP.loadField(elemMmd.getAbsoluteFieldNumber());
                           }

                           if (oldOwner != null) {
                              ObjectProvider oldOwnerOP = this.ec.findObjectProvider(oldOwner);
                              if (oldOwnerOP != null) {
                                 this.ec.getRelationshipManager(oldOwnerOP).relationRemove(fieldNumber, newElem);
                              }
                           }
                        }

                        this.relationAdd(fieldNumber, newElem);
                     }
                  }

                  for(Object oldElem : (Collection)oldValue) {
                     Iterator var18 = ((Collection)newValue).iterator();
                     boolean stillExists = false;

                     while(var18.hasNext()) {
                        Object newElem = var18.next();
                        if (oldElem == newElem) {
                           stillExists = true;
                           break;
                        }
                     }

                     if (!stillExists) {
                        this.relationRemove(fieldNumber, oldElem);
                     }
                  }
               }
            }

         } else {
            if (changes.isEmpty()) {
               changes.add(new RelationChange(RelationshipManagerImpl.ChangeType.CHANGE_OBJECT, newValue, oldValue));
            }

         }
      }
   }

   public void relationAdd(int fieldNumber, Object val) {
      if (!this.ec.isManagingRelations()) {
         AbstractMemberMetaData mmd = this.ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         RelationType relationType = mmd.getRelationType(this.ec.getClassLoaderResolver());
         if (relationType == RelationType.ONE_TO_MANY_BI || relationType == RelationType.MANY_TO_MANY_BI) {
            ObjectProvider elemOP = this.ec.findObjectProvider(val);
            if (elemOP != null) {
               AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(this.ec.getClassLoaderResolver())[0];
               if (elemOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                  Object currentOwnerId = this.ec.getApiAdapter().getIdForObject(elemOP.provideField(relatedMmd.getAbsoluteFieldNumber()));
                  this.ec.removeObjectFromLevel2Cache(currentOwnerId);
               }
            }

            Integer fieldKey = fieldNumber;
            List<RelationChange> changeList = (List)this.fieldChanges.get(fieldKey);
            if (changeList == null) {
               changeList = new ArrayList();
               this.fieldChanges.put(fieldKey, changeList);
            }

            this.ec.removeObjectFromLevel2Cache(this.ec.getApiAdapter().getIdForObject(val));
            changeList.add(new RelationChange(RelationshipManagerImpl.ChangeType.ADD_OBJECT, val));
         }
      }
   }

   public void relationRemove(int fieldNumber, Object val) {
      if (!this.ec.isManagingRelations()) {
         AbstractMemberMetaData mmd = this.ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         RelationType relationType = mmd.getRelationType(this.ec.getClassLoaderResolver());
         if (relationType == RelationType.ONE_TO_MANY_BI || relationType == RelationType.MANY_TO_MANY_BI) {
            Integer fieldKey = fieldNumber;
            List<RelationChange> changeList = (List)this.fieldChanges.get(fieldKey);
            if (changeList == null) {
               changeList = new ArrayList();
               this.fieldChanges.put(fieldKey, changeList);
            }

            this.ec.removeObjectFromLevel2Cache(this.ec.getApiAdapter().getIdForObject(val));
            changeList.add(new RelationChange(RelationshipManagerImpl.ChangeType.REMOVE_OBJECT, val));
         }
      }
   }

   public boolean managesField(int fieldNumber) {
      return this.fieldChanges.containsKey(fieldNumber);
   }

   public void checkConsistency() {
      for(Map.Entry entry : this.fieldChanges.entrySet()) {
         int fieldNumber = (Integer)entry.getKey();
         List<RelationChange> changes = (List)entry.getValue();
         AbstractMemberMetaData mmd = this.ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
         RelationType relationType = mmd.getRelationType(clr);
         if (relationType == RelationType.ONE_TO_ONE_BI) {
            this.checkOneToOneBidirectionalRelation(mmd, clr, this.ec, changes);
         } else if (relationType == RelationType.MANY_TO_ONE_BI) {
            this.checkManyToOneBidirectionalRelation(mmd, clr, this.ec, changes);
         } else if (relationType == RelationType.ONE_TO_MANY_BI) {
            this.checkOneToManyBidirectionalRelation(mmd, clr, this.ec, changes);
         } else if (relationType == RelationType.MANY_TO_MANY_BI) {
            this.checkManyToManyBidirectionalRelation(mmd, clr, this.ec, changes);
         }
      }

   }

   public void process() {
      for(Map.Entry entry : this.fieldChanges.entrySet()) {
         int fieldNumber = (Integer)entry.getKey();
         List<RelationChange> changes = (List)entry.getValue();
         AbstractMemberMetaData mmd = this.ownerOP.getClassMetaData().getMetaDataForManagedMemberAtAbsolutePosition(fieldNumber);
         ClassLoaderResolver clr = this.ec.getClassLoaderResolver();
         RelationType relationType = mmd.getRelationType(clr);
         if (relationType == RelationType.ONE_TO_ONE_BI) {
            this.processOneToOneBidirectionalRelation(mmd, clr, this.ec, changes);
         } else if (relationType == RelationType.MANY_TO_ONE_BI) {
            this.processManyToOneBidirectionalRelation(mmd, clr, this.ec, changes);
         } else if (relationType == RelationType.ONE_TO_MANY_BI) {
            this.processOneToManyBidirectionalRelation(mmd, clr, this.ec, changes);
         } else if (relationType == RelationType.MANY_TO_MANY_BI) {
            this.processManyToManyBidirectionalRelation(mmd, clr, this.ec, changes);
         }
      }

   }

   protected void checkOneToOneBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
      for(RelationChange change : changes) {
         if (change.type == RelationshipManagerImpl.ChangeType.CHANGE_OBJECT) {
            Object newValue = this.ownerOP.provideField(mmd.getAbsoluteFieldNumber());
            if (newValue != null) {
               AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaDataForObject(clr, this.pc, newValue);
               ObjectProvider newOP = ec.findObjectProvider(newValue);
               if (newOP != null && relatedMmd != null) {
                  if (!newOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                     newOP.loadField(relatedMmd.getAbsoluteFieldNumber());
                  }

                  Object newValueFieldValue = newOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                  if (newValueFieldValue != this.pc) {
                     RelationshipManager newRelMgr = ec.getRelationshipManager(newOP);
                     if (newRelMgr != null && newRelMgr.managesField(relatedMmd.getAbsoluteFieldNumber())) {
                        if (newValueFieldValue == null) {
                           String msg = Localiser.msg("013003", StringUtils.toJVMIDString(this.pc), mmd.getName(), StringUtils.toJVMIDString(newValue), relatedMmd.getName());
                           NucleusLogger.PERSISTENCE.error(msg);
                           throw new NucleusUserException(msg);
                        }

                        String msg = Localiser.msg("013002", StringUtils.toJVMIDString(this.pc), mmd.getName(), StringUtils.toJVMIDString(newValue), relatedMmd.getName(), StringUtils.toJVMIDString(newValueFieldValue));
                        NucleusLogger.PERSISTENCE.error(msg);
                        throw new NucleusUserException(msg);
                     }
                  }
               }
            }
         }
      }

   }

   protected void checkOneToManyBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
      for(RelationChange change : changes) {
         if (change.type == RelationshipManagerImpl.ChangeType.ADD_OBJECT) {
            if (ec.getApiAdapter().isDeleted(change.value)) {
               throw new NucleusUserException(Localiser.msg("013008", StringUtils.toJVMIDString(this.pc), mmd.getName(), StringUtils.toJVMIDString(change.value)));
            }

            AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(clr)[0];
            ObjectProvider newElementOP = ec.findObjectProvider(change.value);
            if (newElementOP != null && newElementOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
               RelationshipManager newElementRelMgr = ec.getRelationshipManager(newElementOP);
               if (newElementRelMgr != null && newElementRelMgr.managesField(relatedMmd.getAbsoluteFieldNumber())) {
                  Object newValueFieldValue = newElementOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                  if (newValueFieldValue != this.pc && newValueFieldValue != null) {
                     ApiAdapter api = ec.getApiAdapter();
                     Object id1 = api.getIdForObject(this.pc);
                     Object id2 = api.getIdForObject(newValueFieldValue);
                     if (id1 == null || id2 == null || !id1.equals(id2)) {
                        throw new NucleusUserException(Localiser.msg("013009", StringUtils.toJVMIDString(this.pc), mmd.getName(), StringUtils.toJVMIDString(change.value), StringUtils.toJVMIDString(newValueFieldValue)));
                     }
                  }
               }
            }
         } else if (change.type == RelationshipManagerImpl.ChangeType.REMOVE_OBJECT && !ec.getApiAdapter().isDeleted(change.value)) {
            AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(clr)[0];
            ObjectProvider newElementOP = ec.findObjectProvider(change.value);
            if (newElementOP != null && newElementOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
               RelationshipManager newElementRelMgr = ec.getRelationshipManager(newElementOP);
               if (newElementRelMgr != null && newElementRelMgr.managesField(relatedMmd.getAbsoluteFieldNumber())) {
                  Object newValueFieldValue = newElementOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                  if (newValueFieldValue == this.pc) {
                     throw new NucleusUserException(Localiser.msg("013010", StringUtils.toJVMIDString(this.pc), mmd.getName(), StringUtils.toJVMIDString(change.value)));
                  }
               }
            }
         }
      }

   }

   protected void checkManyToOneBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
   }

   protected void checkManyToManyBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
   }

   protected void processOneToOneBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
      for(RelationChange change : changes) {
         if (change.type == RelationshipManagerImpl.ChangeType.CHANGE_OBJECT) {
            Object oldValue = change.oldValue;
            Object newValue = this.ownerOP.provideField(mmd.getAbsoluteFieldNumber());
            if (oldValue != null) {
               AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaDataForObject(clr, this.pc, oldValue);
               ObjectProvider oldOP = ec.findObjectProvider(oldValue);
               if (oldOP != null) {
                  boolean oldIsDeleted = ec.getApiAdapter().isDeleted(oldOP.getObject());
                  if (!oldIsDeleted) {
                     if (!oldOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                        oldOP.loadField(relatedMmd.getAbsoluteFieldNumber());
                     }

                     Object oldValueFieldValue = oldOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (oldValueFieldValue != null && oldValueFieldValue == this.pc) {
                        if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                           NucleusLogger.PERSISTENCE.debug(Localiser.msg("013004", StringUtils.toJVMIDString(oldValue), relatedMmd.getFullFieldName(), StringUtils.toJVMIDString(this.pc), StringUtils.toJVMIDString(newValue)));
                        }

                        oldOP.replaceFieldValue(relatedMmd.getAbsoluteFieldNumber(), (Object)null);
                     }
                  }
               }
            }

            if (newValue != null) {
               AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaDataForObject(clr, this.pc, newValue);
               ObjectProvider newOP = ec.findObjectProvider(newValue, true);
               if (newOP != null && relatedMmd != null) {
                  if (!newOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                     newOP.loadField(relatedMmd.getAbsoluteFieldNumber());
                  }

                  Object newValueFieldValue = newOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                  if (newValueFieldValue == null) {
                     if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug(Localiser.msg("013005", StringUtils.toJVMIDString(newValue), relatedMmd.getFullFieldName(), StringUtils.toJVMIDString(this.pc)));
                     }

                     newOP.replaceFieldValue(relatedMmd.getAbsoluteFieldNumber(), this.pc);
                  } else if (newValueFieldValue != this.pc) {
                     ObjectProvider newValueFieldOP = ec.findObjectProvider(newValueFieldValue);
                     if (newValueFieldOP != null) {
                        if (!newValueFieldOP.isFieldLoaded(mmd.getAbsoluteFieldNumber())) {
                           newValueFieldOP.loadField(mmd.getAbsoluteFieldNumber());
                        }

                        if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                           NucleusLogger.PERSISTENCE.debug(Localiser.msg("013004", StringUtils.toJVMIDString(newValueFieldValue), mmd.getFullFieldName(), StringUtils.toJVMIDString(newValue), StringUtils.toJVMIDString(this.pc)));
                        }

                        newValueFieldOP.replaceFieldValue(mmd.getAbsoluteFieldNumber(), (Object)null);
                     }

                     if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                        NucleusLogger.PERSISTENCE.debug(Localiser.msg("013005", StringUtils.toJVMIDString(newValue), relatedMmd.getFullFieldName(), StringUtils.toJVMIDString(this.pc)));
                     }

                     newOP.replaceFieldValue(relatedMmd.getAbsoluteFieldNumber(), this.pc);
                  }
               }
            }
         }
      }

   }

   protected void processOneToManyBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
      for(RelationChange change : changes) {
         if (change.type == RelationshipManagerImpl.ChangeType.ADD_OBJECT || change.type == RelationshipManagerImpl.ChangeType.REMOVE_OBJECT) {
            ObjectProvider op = ec.findObjectProvider(change.value);
            if (op == null && ec.getApiAdapter().isDetached(change.value)) {
               Object attached = ec.getAttachedObjectForId(ec.getApiAdapter().getIdForObject(change.value));
               if (attached != null) {
                  op = ec.findObjectProvider(attached);
               }
            }

            if (op != null) {
               if (change.type == RelationshipManagerImpl.ChangeType.ADD_OBJECT) {
                  AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(clr)[0];
                  if (op.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                     Object currentVal = op.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (currentVal != this.ownerOP.getObject()) {
                        op.replaceFieldValue(relatedMmd.getAbsoluteFieldNumber(), this.ownerOP.getObject());
                     }
                  } else {
                     ec.removeObjectFromLevel2Cache(op.getInternalObjectId());
                  }
               } else if (change.type == RelationshipManagerImpl.ChangeType.REMOVE_OBJECT) {
                  AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(clr)[0];
                  if (op.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                     Object currentVal = op.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (currentVal == this.ownerOP.getObject()) {
                        op.replaceFieldValue(relatedMmd.getAbsoluteFieldNumber(), (Object)null);
                     }
                  } else {
                     ec.removeObjectFromLevel2Cache(op.getInternalObjectId());
                  }
               }
            }
         }
      }

   }

   protected void processManyToOneBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
      for(RelationChange change : changes) {
         if (change.type == RelationshipManagerImpl.ChangeType.CHANGE_OBJECT) {
            Object oldValue = change.oldValue;
            Object newValue = this.ownerOP.provideField(mmd.getAbsoluteFieldNumber());
            if (oldValue != null) {
               AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaDataForObject(clr, this.pc, oldValue);
               ObjectProvider oldOP = ec.findObjectProvider(oldValue);
               if (oldOP != null && relatedMmd != null && oldOP.getLoadedFields()[relatedMmd.getAbsoluteFieldNumber()]) {
                  if (oldOP.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                     Object oldContainerValue = oldOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (oldContainerValue instanceof Collection) {
                        Collection oldColl = (Collection)oldContainerValue;
                        if (oldColl.contains(this.pc)) {
                           if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                              NucleusLogger.PERSISTENCE.debug(Localiser.msg("013006", StringUtils.toJVMIDString(this.pc), mmd.getFullFieldName(), relatedMmd.getFullFieldName(), StringUtils.toJVMIDString(oldValue)));
                           }

                           if (oldColl instanceof SCOCollection) {
                              ((SCOCollection)oldColl).remove(this.pc, false);
                           } else {
                              oldColl.remove(this.pc);
                           }
                        }
                     }
                  }
               } else if (oldOP != null) {
                  ec.removeObjectFromLevel2Cache(oldOP.getInternalObjectId());
               }
            }

            if (newValue != null) {
               AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaDataForObject(clr, this.pc, newValue);
               ObjectProvider newOP = ec.findObjectProvider(newValue);
               if (newOP != null && relatedMmd != null && newOP.getLoadedFields()[relatedMmd.getAbsoluteFieldNumber()]) {
                  Object newContainerValue = newOP.provideField(relatedMmd.getAbsoluteFieldNumber());
                  if (newContainerValue instanceof Collection) {
                     Collection newColl = (Collection)newContainerValue;
                     if (!newColl.contains(this.pc)) {
                        if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
                           NucleusLogger.PERSISTENCE.debug(Localiser.msg("013007", StringUtils.toJVMIDString(this.pc), mmd.getFullFieldName(), relatedMmd.getFullFieldName(), StringUtils.toJVMIDString(newValue)));
                        }

                        newColl.add(this.pc);
                     }
                  }
               } else {
                  ec.removeObjectFromLevel2Cache(ec.getApiAdapter().getIdForObject(newValue));
               }
            }
         }
      }

   }

   protected void processManyToManyBidirectionalRelation(AbstractMemberMetaData mmd, ClassLoaderResolver clr, ExecutionContext ec, List changes) {
      for(RelationChange change : changes) {
         if (change.type == RelationshipManagerImpl.ChangeType.ADD_OBJECT || change.type == RelationshipManagerImpl.ChangeType.REMOVE_OBJECT) {
            ObjectProvider op = ec.findObjectProvider(change.value);
            if (op == null && ec.getApiAdapter().isDetached(change.value)) {
               Object attached = ec.getAttachedObjectForId(ec.getApiAdapter().getIdForObject(change.value));
               if (attached != null) {
                  op = ec.findObjectProvider(attached);
               }
            }

            if (op != null) {
               if (change.type == RelationshipManagerImpl.ChangeType.ADD_OBJECT) {
                  AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(clr)[0];
                  ec.removeObjectFromLevel2Cache(op.getInternalObjectId());
                  ec.removeObjectFromLevel2Cache(this.ownerOP.getInternalObjectId());
                  if (this.ownerOP.isFieldLoaded(mmd.getAbsoluteFieldNumber()) && !this.ownerOP.getLifecycleState().isDeleted) {
                     Collection currentVal = (Collection)this.ownerOP.provideField(mmd.getAbsoluteFieldNumber());
                     if (currentVal != null && !currentVal.contains(op.getObject())) {
                        currentVal.add(op.getObject());
                     }
                  }

                  if (op.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber())) {
                     Collection currentVal = (Collection)op.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (currentVal != null && !currentVal.contains(this.ownerOP.getObject())) {
                        currentVal.add(this.ownerOP.getObject());
                     }
                  }
               } else if (change.type == RelationshipManagerImpl.ChangeType.REMOVE_OBJECT) {
                  AbstractMemberMetaData relatedMmd = mmd.getRelatedMemberMetaData(clr)[0];
                  ec.removeObjectFromLevel2Cache(op.getInternalObjectId());
                  ec.removeObjectFromLevel2Cache(this.ownerOP.getInternalObjectId());
                  if (this.ownerOP.isFieldLoaded(mmd.getAbsoluteFieldNumber()) && !this.ownerOP.getLifecycleState().isDeleted) {
                     Collection currentVal = (Collection)this.ownerOP.provideField(mmd.getAbsoluteFieldNumber());
                     if (!op.getLifecycleState().isDeleted && currentVal != null && currentVal.contains(op.getObject())) {
                        currentVal.remove(op.getObject());
                     } else {
                        this.ownerOP.unloadField(mmd.getName());
                     }
                  }

                  if (op.isFieldLoaded(relatedMmd.getAbsoluteFieldNumber()) && !op.getLifecycleState().isDeleted) {
                     Collection currentVal = (Collection)op.provideField(relatedMmd.getAbsoluteFieldNumber());
                     if (currentVal != null && currentVal.contains(this.ownerOP.getObject())) {
                        currentVal.remove(this.ownerOP.getObject());
                     }
                  }
               }
            }
         }
      }

   }

   private static enum ChangeType {
      ADD_OBJECT,
      REMOVE_OBJECT,
      CHANGE_OBJECT;
   }

   private static class RelationChange {
      ChangeType type;
      Object value;
      Object oldValue;

      public RelationChange(ChangeType type, Object val) {
         this.type = type;
         this.value = val;
      }

      public RelationChange(ChangeType type, Object newVal, Object oldVal) {
         this.type = type;
         this.value = newVal;
         this.oldValue = oldVal;
      }

      public String toString() {
         return this.oldValue != null ? "RelationChange type=" + this.type + " value=" + StringUtils.toJVMIDString(this.oldValue) + " -> " + StringUtils.toJVMIDString(this.value) : "RelationChange type=" + this.type + " value=" + StringUtils.toJVMIDString(this.value);
      }
   }
}
