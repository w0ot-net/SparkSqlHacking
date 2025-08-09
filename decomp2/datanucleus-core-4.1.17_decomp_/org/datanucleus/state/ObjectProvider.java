package org.datanucleus.state;

import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.Transaction;
import org.datanucleus.cache.CachedPC;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.fieldmanager.FieldManager;

public interface ObjectProvider {
   String ORIGINAL_FIELD_VALUE_KEY_PREFIX = "FIELD_VALUE.ORIGINAL.";
   short PC = 0;
   short EMBEDDED_PC = 1;
   short EMBEDDED_COLLECTION_ELEMENT_PC = 2;
   short EMBEDDED_MAP_KEY_PC = 3;
   short EMBEDDED_MAP_VALUE_PC = 4;

   void connect(ExecutionContext var1, AbstractClassMetaData var2);

   void disconnect();

   void initialiseForHollow(Object var1, FieldValues var2, Class var3);

   /** @deprecated */
   void initialiseForHollowAppId(FieldValues var1, Class var2);

   void initialiseForHollowPreConstructed(Object var1, Object var2);

   void initialiseForPersistentClean(Object var1, Object var2);

   void initialiseForEmbedded(Object var1, boolean var2);

   void initialiseForPersistentNew(Object var1, FieldValues var2);

   void initialiseForTransactionalTransient(Object var1);

   void initialiseForDetached(Object var1, Object var2, Object var3);

   void initialiseForPNewToBeDeleted(Object var1);

   void initialiseForCachedPC(CachedPC var1, Object var2);

   AbstractClassMetaData getClassMetaData();

   ExecutionContext getExecutionContext();

   StoreManager getStoreManager();

   Object getObject();

   String getObjectAsPrintable();

   Object getInternalObjectId();

   Object getExternalObjectId();

   LifeCycleState getLifecycleState();

   void replaceField(int var1, Object var2);

   void replaceFieldMakeDirty(int var1, Object var2);

   void replaceFieldValue(int var1, Object var2);

   void replaceFields(int[] var1, FieldManager var2);

   void replaceFields(int[] var1, FieldManager var2, boolean var3);

   void replaceNonLoadedFields(int[] var1, FieldManager var2);

   void replaceAllLoadedSCOFieldsWithWrappers();

   void replaceAllLoadedSCOFieldsWithValues();

   void provideFields(int[] var1, FieldManager var2);

   Object provideField(int var1);

   void setAssociatedValue(Object var1, Object var2);

   Object getAssociatedValue(Object var1);

   void removeAssociatedValue(Object var1);

   int[] getDirtyFieldNumbers();

   String[] getDirtyFieldNames();

   boolean[] getDirtyFields();

   void makeDirty(int var1);

   boolean isEmbedded();

   void updateOwnerFieldInEmbeddedField(int var1, Object var2);

   void copyFieldsFromObject(Object var1, int[] var2);

   void setPcObjectType(short var1);

   void setStoringPC();

   void unsetStoringPC();

   boolean isFlushedToDatastore();

   boolean isFlushedNew();

   void setFlushedNew(boolean var1);

   void flush();

   void setFlushing(boolean var1);

   void markAsFlushed();

   void locate();

   boolean isWaitingToBeFlushedToDatastore();

   void changeActivityState(ActivityState var1);

   boolean isInserting();

   boolean isDeleting();

   boolean becomingDeleted();

   boolean isDeleted();

   void loadFieldValues(FieldValues var1);

   Object getReferencedPC();

   void loadField(int var1);

   void loadFieldsInFetchPlan(FetchPlanState var1);

   void loadFieldFromDatastore(int var1);

   void loadUnloadedFieldsInFetchPlan();

   void loadUnloadedFieldsOfClassInFetchPlan(FetchPlan var1);

   void loadUnloadedRelationFields();

   void loadUnloadedFields();

   void unloadNonFetchPlanFields();

   void refreshLoadedFields();

   void clearSavedFields();

   void refreshFieldsInFetchPlan();

   void clearNonPrimaryKeyFields();

   void restoreFields();

   void saveFields();

   void clearFields();

   void registerTransactional();

   boolean isRestoreValues();

   void clearLoadedFlags();

   void unloadField(String var1);

   boolean[] getLoadedFields();

   int[] getLoadedFieldNumbers();

   String[] getLoadedFieldNames();

   boolean isLoaded(int var1);

   boolean getAllFieldsLoaded();

   boolean isFieldLoaded(int var1);

   void updateFieldAfterInsert(Object var1, int var2);

   void setPostStoreNewObjectId(Object var1);

   void replaceManagedPC(Object var1);

   void setTransactionalVersion(Object var1);

   Object getTransactionalVersion();

   void setVersion(Object var1);

   Object getVersion();

   void lock(short var1);

   void unlock();

   short getLockMode();

   void evictFromTransaction();

   void enlistInTransaction();

   void makeTransactional();

   void makeNontransactional();

   void makeTransient(FetchPlanState var1);

   void makePersistent();

   void makePersistentTransactionalTransient();

   void deletePersistent();

   Object attachCopy(Object var1, boolean var2);

   void attach(boolean var1);

   void attach(Object var1);

   Object detachCopy(FetchPlanState var1);

   void detach(FetchPlanState var1);

   void validate();

   void markForInheritanceValidation();

   void evict();

   void refresh();

   void retrieve(boolean var1);

   void preBegin(Transaction var1);

   void postCommit(Transaction var1);

   void preRollback(Transaction var1);

   void resetDetachState();

   void retrieveDetachState(ObjectProvider var1);

   /** @deprecated */
   void checkInheritance(FieldValues var1);
}
