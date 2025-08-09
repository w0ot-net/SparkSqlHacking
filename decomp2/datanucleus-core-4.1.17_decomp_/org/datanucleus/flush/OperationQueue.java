package org.datanucleus.flush;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.scostore.CollectionStore;
import org.datanucleus.store.scostore.ListStore;
import org.datanucleus.store.scostore.MapStore;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.SCOUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class OperationQueue {
   protected List queuedOperations = new ArrayList();

   public synchronized void enqueue(Operation oper) {
      ObjectProvider op = oper.getObjectProvider();
      if (oper instanceof SCOOperation && op.isWaitingToBeFlushedToDatastore()) {
         NucleusLogger.GENERAL.info(">> OperationQueue : not adding operation since owner not yet flushed - " + oper);
      } else {
         this.queuedOperations.add(oper);
      }
   }

   public synchronized void log() {
      NucleusLogger.GENERAL.debug(">> OperationQueue :" + (this.queuedOperations.isEmpty() ? " Empty" : "" + this.queuedOperations.size() + " operations"));

      for(Operation oper : this.queuedOperations) {
         NucleusLogger.GENERAL.debug(">> " + oper);
      }

   }

   public void clear() {
      this.queuedOperations.clear();
   }

   public List getOperations() {
      return Collections.unmodifiableList(this.queuedOperations);
   }

   public void removeOperations(List removedOps) {
      this.queuedOperations.removeAll(removedOps);
   }

   public synchronized void performAll() {
      for(Operation op : this.queuedOperations) {
         op.perform();
      }

      this.queuedOperations.clear();
   }

   public synchronized void performAll(Store store, ObjectProvider op) {
      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("023005", op.getObjectAsPrintable(), store.getOwnerMemberMetaData().getFullFieldName()));
      }

      List<Operation> flushOperations = new ArrayList();
      ListIterator<Operation> operIter = this.queuedOperations.listIterator();

      while(operIter.hasNext()) {
         Operation oper = (Operation)operIter.next();
         if (oper.getObjectProvider() == op && oper instanceof SCOOperation && ((SCOOperation)oper).getStore() == store) {
            flushOperations.add(oper);
            operIter.remove();
         }
      }

      ListIterator<Operation> flushOperIter = flushOperations.listIterator();

      while(flushOperIter.hasNext()) {
         Operation oper = (Operation)flushOperIter.next();
         if (store instanceof CollectionStore) {
            if (!(store instanceof ListStore)) {
               if (isAddFollowedByRemoveOnSameSCO(store, op, oper, flushOperIter)) {
                  flushOperIter.next();
               } else if (isRemoveFollowedByAddOnSameSCO(store, op, oper, flushOperIter)) {
                  flushOperIter.next();
               } else {
                  oper.perform();
               }
            } else {
               oper.perform();
            }
         } else if (store instanceof MapStore) {
            if (isPutFollowedByRemoveOnSameSCO(store, op, oper, flushOperIter)) {
               flushOperIter.next();
            } else {
               oper.perform();
            }
         } else {
            oper.perform();
         }
      }

   }

   public void clearPersistDeleteUpdateOperations() {
      if (this.queuedOperations != null) {
         Iterator<Operation> opsIter = this.queuedOperations.iterator();

         while(opsIter.hasNext()) {
            Operation op = (Operation)opsIter.next();
            if (op instanceof PersistOperation || op instanceof DeleteOperation || op instanceof UpdateMemberOperation) {
               opsIter.remove();
            }
         }
      }

   }

   public void processOperationsForNoBackingStoreSCOs(ExecutionContext ec) {
      if (this.queuedOperations != null && !ec.getStoreManager().usesBackedSCOWrappers()) {
         List<Operation> opersToIgnore = new ArrayList();
         List<Object> objectsToCascadeDelete = null;

         for(Operation oper : this.queuedOperations) {
            if (oper instanceof CollectionRemoveOperation) {
               CollectionRemoveOperation collRemoveOper = (CollectionRemoveOperation)oper;
               if (collRemoveOper.getStore() == null && SCOUtils.hasDependentElement(collRemoveOper.getMemberMetaData())) {
                  boolean needsRemoving = true;
                  if (this.queuedOperations.size() > 1) {
                     for(Operation subOper : this.queuedOperations) {
                        if (subOper instanceof CollectionAddOperation) {
                           CollectionAddOperation collAddOper = (CollectionAddOperation)subOper;
                           if (collRemoveOper.getValue().equals(collAddOper.getValue())) {
                              needsRemoving = false;
                              break;
                           }
                        } else if (subOper instanceof PersistOperation) {
                           PersistOperation persOp = (PersistOperation)subOper;
                           if (persOp.getObjectProvider().getObject().getClass().equals(collRemoveOper.getObjectProvider().getObject().getClass())) {
                              Collection persColl = (Collection)persOp.getObjectProvider().provideField(collRemoveOper.getMemberMetaData().getAbsoluteFieldNumber());
                              if (persColl != null && persColl.contains(collRemoveOper.getValue())) {
                                 needsRemoving = false;
                                 break;
                              }
                           }
                        }
                     }
                  }

                  if (needsRemoving) {
                     if (objectsToCascadeDelete == null) {
                        objectsToCascadeDelete = new ArrayList();
                     }

                     NucleusLogger.GENERAL.info(">> Flush collection element needs cascade delete " + collRemoveOper.getValue());
                     objectsToCascadeDelete.add(collRemoveOper.getValue());
                  }
               }
            } else if (oper instanceof MapRemoveOperation) {
               MapRemoveOperation mapRemoveOper = (MapRemoveOperation)oper;
               if (mapRemoveOper.getStore() == null) {
                  if (SCOUtils.hasDependentKey(mapRemoveOper.getMemberMetaData())) {
                     boolean keyNeedsRemoving = true;
                     if (this.queuedOperations.size() > 1) {
                        for(Operation subOper : this.queuedOperations) {
                           if (subOper instanceof MapPutOperation) {
                              MapPutOperation mapPutOper = (MapPutOperation)subOper;
                              if (mapRemoveOper.getKey().equals(mapPutOper.getKey())) {
                                 keyNeedsRemoving = false;
                                 break;
                              }
                           } else if (subOper instanceof PersistOperation) {
                              PersistOperation persOper = (PersistOperation)subOper;
                              if (persOper.getObjectProvider().getObject().getClass().equals(mapRemoveOper.getObjectProvider().getObject().getClass())) {
                                 Map persMap = (Map)persOper.getObjectProvider().provideField(mapRemoveOper.getMemberMetaData().getAbsoluteFieldNumber());
                                 if (persMap != null && persMap.containsKey(mapRemoveOper.getKey())) {
                                    keyNeedsRemoving = false;
                                    break;
                                 }
                              }
                           }
                        }
                     }

                     if (keyNeedsRemoving) {
                        if (objectsToCascadeDelete == null) {
                           objectsToCascadeDelete = new ArrayList();
                        }

                        objectsToCascadeDelete.add(mapRemoveOper.getKey());
                     }
                  } else if (SCOUtils.hasDependentValue(mapRemoveOper.getMemberMetaData())) {
                     boolean valNeedsRemoving = true;
                     if (this.queuedOperations.size() > 1) {
                        for(Operation subOper : this.queuedOperations) {
                           if (subOper instanceof MapPutOperation) {
                              MapPutOperation mapPutOper = (MapPutOperation)subOper;
                              if (mapRemoveOper.getValue().equals(mapPutOper.getValue())) {
                                 valNeedsRemoving = false;
                                 break;
                              }
                           } else if (subOper instanceof PersistOperation) {
                              PersistOperation persOper = (PersistOperation)subOper;
                              if (persOper.getObjectProvider().getObject().getClass().equals(mapRemoveOper.getObjectProvider().getObject().getClass())) {
                                 Map persMap = (Map)persOper.getObjectProvider().provideField(mapRemoveOper.getMemberMetaData().getAbsoluteFieldNumber());
                                 if (persMap != null && persMap.containsValue(mapRemoveOper.getValue())) {
                                    valNeedsRemoving = false;
                                    break;
                                 }
                              }
                           }
                        }
                     }

                     if (valNeedsRemoving) {
                        if (objectsToCascadeDelete == null) {
                           objectsToCascadeDelete = new ArrayList();
                        }

                        objectsToCascadeDelete.add(mapRemoveOper.getValue());
                     }
                  }
               }
            }

            if (oper instanceof SCOOperation) {
               opersToIgnore.add(oper);
            }
         }

         this.queuedOperations.removeAll(opersToIgnore);
         if (objectsToCascadeDelete != null) {
            for(Object deleteObj : objectsToCascadeDelete) {
               NucleusLogger.PERSISTENCE.debug("Initiating cascade delete of " + deleteObj);
               ec.deleteObjectInternal(deleteObj);
            }
         }
      }

   }

   protected static boolean isAddFollowedByRemoveOnSameSCO(Store store, ObjectProvider op, Operation currentOper, ListIterator listIter) {
      if (CollectionAddOperation.class.isInstance(currentOper)) {
         boolean addThenRemove = false;
         if (listIter.hasNext()) {
            Operation operNext = (Operation)listIter.next();
            if (CollectionRemoveOperation.class.isInstance(operNext)) {
               Object value = ((CollectionAddOperation)CollectionAddOperation.class.cast(currentOper)).getValue();
               if (value == ((CollectionRemoveOperation)CollectionRemoveOperation.class.cast(operNext)).getValue()) {
                  addThenRemove = true;
                  NucleusLogger.PERSISTENCE.info("Member " + store.getOwnerMemberMetaData().getFullFieldName() + " of " + StringUtils.toJVMIDString(op.getObject()) + " had an add then a remove of element " + StringUtils.toJVMIDString(value) + " - operations ignored");
               }
            }

            listIter.previous();
         }

         return addThenRemove;
      } else {
         return false;
      }
   }

   protected static boolean isRemoveFollowedByAddOnSameSCO(Store store, ObjectProvider op, Operation currentOper, ListIterator listIter) {
      if (CollectionRemoveOperation.class.isInstance(currentOper)) {
         boolean removeThenAdd = false;
         if (listIter.hasNext()) {
            Operation opNext = (Operation)listIter.next();
            if (CollectionAddOperation.class.isInstance(opNext)) {
               Object value = ((CollectionRemoveOperation)CollectionRemoveOperation.class.cast(currentOper)).getValue();
               if (value == ((CollectionAddOperation)CollectionAddOperation.class.cast(opNext)).getValue()) {
                  removeThenAdd = true;
                  NucleusLogger.PERSISTENCE.info("Member" + store.getOwnerMemberMetaData().getFullFieldName() + " of " + StringUtils.toJVMIDString(op.getObject()) + " had a remove then add of element " + StringUtils.toJVMIDString(value) + " - operations ignored");
               }
            }

            listIter.previous();
         }

         return removeThenAdd;
      } else {
         return false;
      }
   }

   protected static boolean isPutFollowedByRemoveOnSameSCO(Store store, ObjectProvider op, Operation currentOper, ListIterator listIter) {
      if (MapPutOperation.class.isInstance(currentOper)) {
         boolean putThenRemove = false;
         if (listIter.hasNext()) {
            Operation operNext = (Operation)listIter.next();
            if (MapRemoveOperation.class.isInstance(operNext)) {
               Object key = ((MapPutOperation)MapPutOperation.class.cast(currentOper)).getKey();
               if (key == ((MapRemoveOperation)MapRemoveOperation.class.cast(operNext)).getKey()) {
                  putThenRemove = true;
                  NucleusLogger.PERSISTENCE.info("Member " + store.getOwnerMemberMetaData().getFullFieldName() + " of " + StringUtils.toJVMIDString(op.getObject()) + " had a put then a remove of key " + StringUtils.toJVMIDString(key) + " - operations ignored");
               }
            }

            listIter.previous();
         }

         return putThenRemove;
      } else {
         return false;
      }
   }
}
