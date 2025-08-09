package org.datanucleus;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import org.datanucleus.api.ApiAdapter;
import org.datanucleus.cache.Level1Cache;
import org.datanucleus.enhancement.ExecutionContextReference;
import org.datanucleus.flush.Operation;
import org.datanucleus.flush.OperationQueue;
import org.datanucleus.management.ManagerStatistics;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.state.FetchPlanState;
import org.datanucleus.state.LockManager;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.state.RelationshipManager;
import org.datanucleus.store.Extent;
import org.datanucleus.store.FieldValues;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.scostore.Store;
import org.datanucleus.store.types.TypeManager;

public interface ExecutionContext extends ExecutionContextReference {
   String OPTION_USERNAME = "user";
   String OPTION_PASSWORD = "password";
   String OPTION_JTA_AUTOJOIN = "jta_autojoin";

   void initialise(Object var1, Map var2);

   Object getOwner();

   Level1Cache getLevel1Cache();

   Transaction getTransaction();

   StoreManager getStoreManager();

   MetaDataManager getMetaDataManager();

   PersistenceNucleusContext getNucleusContext();

   ApiAdapter getApiAdapter();

   FetchPlan getFetchPlan();

   ClassLoaderResolver getClassLoaderResolver();

   LockManager getLockManager();

   ManagerStatistics getStatistics();

   void setProperties(Map var1);

   void setProperty(String var1, Object var2);

   Object getProperty(String var1);

   Boolean getBooleanProperty(String var1);

   Integer getIntProperty(String var1);

   String getStringProperty(String var1);

   Map getProperties();

   Set getSupportedProperties();

   TypeManager getTypeManager();

   void close();

   boolean isClosed();

   ObjectProvider findObjectProvider(Object var1);

   ObjectProvider findObjectProvider(Object var1, boolean var2);

   ObjectProvider findObjectProviderForEmbedded(Object var1, ObjectProvider var2, AbstractMemberMetaData var3);

   ObjectProvider findObjectProviderOfOwnerForAttachingObject(Object var1);

   void hereIsObjectProvider(ObjectProvider var1, Object var2);

   void addObjectProvider(ObjectProvider var1);

   void removeObjectProvider(ObjectProvider var1);

   void evictObject(Object var1);

   void evictObjects(Class var1, boolean var2);

   void evictAllObjects();

   void retrieveObject(Object var1, boolean var2);

   Object persistObject(Object var1, boolean var2);

   Object[] persistObjects(Object[] var1);

   Object persistObjectInternal(Object var1, FieldValues var2, ObjectProvider var3, int var4, int var5);

   Object persistObjectInternal(Object var1, ObjectProvider var2, int var3, int var4);

   Object persistObjectInternal(Object var1, FieldValues var2, int var3);

   void makeObjectTransient(Object var1, FetchPlanState var2);

   void makeObjectTransactional(Object var1);

   void makeObjectNontransactional(Object var1);

   boolean exists(Object var1);

   Set getManagedObjects();

   Set getManagedObjects(Class[] var1);

   Set getManagedObjects(String[] var1);

   Set getManagedObjects(String[] var1, Class[] var2);

   void deleteObject(Object var1);

   void deleteObjects(Object[] var1);

   void deleteObjectInternal(Object var1);

   void detachObject(Object var1, FetchPlanState var2);

   Object detachObjectCopy(Object var1, FetchPlanState var2);

   void detachAll();

   void attachObject(ObjectProvider var1, Object var2, boolean var3);

   Object attachObjectCopy(ObjectProvider var1, Object var2, boolean var3);

   Object getAttachedObjectForId(Object var1);

   void refreshObject(Object var1);

   void refreshAllObjects();

   void enlistInTransaction(ObjectProvider var1);

   boolean isEnlistedInTransaction(Object var1);

   void evictFromTransaction(ObjectProvider var1);

   void markDirty(ObjectProvider var1, boolean var2);

   void clearDirty(ObjectProvider var1);

   void clearDirty();

   boolean isDelayDatastoreOperationsEnabled();

   void processNontransactionalUpdate();

   Object findObject(Class var1, Object var2);

   Object findObject(Object var1, boolean var2);

   Object findObject(Object var1, boolean var2, boolean var3, String var4);

   Object findObject(Object var1, FieldValues var2, Class var3, boolean var4, boolean var5);

   Object[] findObjects(Object[] var1, boolean var2);

   Extent getExtent(Class var1, boolean var2);

   void putObjectIntoLevel1Cache(ObjectProvider var1);

   Object getObjectFromCache(Object var1);

   Object[] getObjectsFromCache(Object[] var1);

   void removeObjectFromLevel1Cache(Object var1);

   void removeObjectFromLevel2Cache(Object var1);

   void markFieldsForUpdateInLevel2Cache(Object var1, boolean[] var2);

   boolean hasIdentityInCache(Object var1);

   Object newObjectId(Class var1, Object var2);

   Object newObjectId(String var1, Object var2);

   boolean getSerializeReadForClass(String var1);

   void assertClassPersistable(Class var1);

   boolean hasPersistenceInformationForClass(Class var1);

   boolean isInserting(Object var1);

   boolean isFlushing();

   boolean isRunningDetachAllOnCommit();

   void flush();

   void flushInternal(boolean var1);

   OperationQueue getOperationQueue();

   boolean operationQueueIsActive();

   void addOperationToQueue(Operation var1);

   void flushOperationsForBackingStore(Store var1, ObjectProvider var2);

   List getObjectsToBeFlushed();

   boolean getMultithreaded();

   boolean getManageRelations();

   RelationshipManager getRelationshipManager(ObjectProvider var1);

   boolean isManagingRelations();

   CallbackHandler getCallbackHandler();

   FetchGroup getInternalFetchGroup(Class var1, String var2);

   void addInternalFetchGroup(FetchGroup var1);

   Set getFetchGroupsWithName(String var1);

   Lock getLock();

   Object newInstance(Class var1);

   boolean isObjectModifiedInTransaction(Object var1);

   void replaceObjectId(Object var1, Object var2, Object var3);

   Object getAttachDetachReferencedObject(ObjectProvider var1);

   void setAttachDetachReferencedObject(ObjectProvider var1, Object var2);

   EmbeddedOwnerRelation registerEmbeddedRelation(ObjectProvider var1, int var2, ObjectProvider var3);

   void deregisterEmbeddedRelation(EmbeddedOwnerRelation var1);

   List getEmbeddedInformationForOwner(ObjectProvider var1);

   List getOwnerInformationForEmbedded(ObjectProvider var1);

   ObjectProvider[] getOwnersForEmbeddedObjectProvider(ObjectProvider var1);

   void removeEmbeddedOwnerRelation(ObjectProvider var1, int var2, ObjectProvider var3);

   void setObjectProviderAssociatedValue(ObjectProvider var1, Object var2, Object var3);

   Object getObjectProviderAssociatedValue(ObjectProvider var1, Object var2);

   void removeObjectProviderAssociatedValue(ObjectProvider var1, Object var2);

   boolean containsObjectProviderAssociatedValue(ObjectProvider var1, Object var2);

   void registerExecutionContextListener(ExecutionContextListener var1);

   void deregisterExecutionContextListener(ExecutionContextListener var1);

   void closeCallbackHandler();

   public static class EmbeddedOwnerRelation {
      protected ObjectProvider ownerOP;
      protected int ownerFieldNum;
      protected ObjectProvider embOP;

      public EmbeddedOwnerRelation(ObjectProvider ownerOP, int ownerFieldNum, ObjectProvider embOP) {
         this.ownerOP = ownerOP;
         this.ownerFieldNum = ownerFieldNum;
         this.embOP = embOP;
      }

      public ObjectProvider getOwnerOP() {
         return this.ownerOP;
      }

      public ObjectProvider getEmbeddedOP() {
         return this.embOP;
      }

      public int getOwnerFieldNum() {
         return this.ownerFieldNum;
      }
   }

   public interface LifecycleListener {
      void preClose(ExecutionContext var1);
   }
}
