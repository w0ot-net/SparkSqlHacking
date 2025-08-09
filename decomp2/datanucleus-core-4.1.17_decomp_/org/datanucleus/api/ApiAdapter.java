package org.datanucleus.api;

import java.io.Serializable;
import java.util.Map;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.ExecutionContext;
import org.datanucleus.enhancement.Persistable;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.metadata.MetaDataManager;
import org.datanucleus.state.LifeCycleState;

public interface ApiAdapter extends Serializable {
   String getName();

   boolean isMemberDefaultPersistent(Class var1);

   boolean isManaged(Object var1);

   ExecutionContext getExecutionContext(Object var1);

   LifeCycleState getLifeCycleState(int var1);

   boolean isPersistent(Object var1);

   boolean isNew(Object var1);

   boolean isDirty(Object var1);

   boolean isDeleted(Object var1);

   boolean isDetached(Object var1);

   boolean isTransactional(Object var1);

   boolean isPersistable(Object var1);

   boolean isPersistable(Class var1);

   boolean isDetachable(Object var1);

   String getObjectState(Object var1);

   void makeDirty(Object var1, String var2);

   Object getIdForObject(Object var1);

   Object getVersionForObject(Object var1);

   boolean isValidPrimaryKeyClass(Class var1, AbstractClassMetaData var2, ClassLoaderResolver var3, int var4, MetaDataManager var5);

   void copyKeyFieldsFromIdToObject(Object var1, Persistable.ObjectIdFieldConsumer var2, Object var3);

   boolean allowPersistOfDeletedObject();

   boolean allowDeleteOfNonPersistentObject();

   boolean allowReadFieldOfDeletedObject();

   boolean clearLoadedFlagsOnDeleteObject();

   boolean getDefaultCascadePersistForField();

   boolean getDefaultCascadeUpdateForField();

   boolean getDefaultCascadeDeleteForField();

   boolean getDefaultCascadeRefreshForField();

   boolean getDefaultDFGForPersistableField();

   Map getDefaultFactoryProperties();

   RuntimeException getApiExceptionForNucleusException(NucleusException var1);

   RuntimeException getUserExceptionForException(String var1, Exception var2);

   RuntimeException getDataStoreExceptionForException(String var1, Exception var2);
}
