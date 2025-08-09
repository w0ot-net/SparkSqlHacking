package org.datanucleus;

import java.util.Map;
import java.util.Set;
import org.datanucleus.cache.Level2Cache;
import org.datanucleus.identity.IdentityManager;
import org.datanucleus.management.FactoryStatistics;
import org.datanucleus.management.jmx.ManagementManager;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.state.ObjectProviderFactory;
import org.datanucleus.store.autostart.AutoStartMechanism;
import org.datanucleus.transaction.TransactionManager;
import org.datanucleus.transaction.jta.JTASyncRegistry;

public interface PersistenceNucleusContext extends StoreNucleusContext {
   AutoStartMechanism getAutoStartMechanism();

   ObjectProviderFactory getObjectProviderFactory();

   ExecutionContextPool getExecutionContextPool();

   ExecutionContext getExecutionContext(Object var1, Map var2);

   IdentityManager getIdentityManager();

   boolean statisticsEnabled();

   ManagementManager getJMXManager();

   FactoryStatistics getStatistics();

   ImplementationCreator getImplementationCreator();

   TransactionManager getTransactionManager();

   javax.transaction.TransactionManager getJtaTransactionManager();

   JTASyncRegistry getJtaSyncRegistry();

   CallbackHandler getValidationHandler(ExecutionContext var1);

   boolean hasLevel2Cache();

   Level2Cache getLevel2Cache();

   ExecutionContext.LifecycleListener[] getExecutionContextListeners();

   void addExecutionContextListener(ExecutionContext.LifecycleListener var1);

   void removeExecutionContextListener(ExecutionContext.LifecycleListener var1);

   void setJcaMode(boolean var1);

   boolean isJcaMode();

   FetchGroupManager getFetchGroupManager();

   void addInternalFetchGroup(FetchGroup var1);

   void removeInternalFetchGroup(FetchGroup var1);

   FetchGroup createInternalFetchGroup(Class var1, String var2);

   FetchGroup getInternalFetchGroup(Class var1, String var2, boolean var3);

   Set getFetchGroupsWithName(String var1);

   boolean isClassWithIdentityCacheable(Object var1);

   boolean isClassCacheable(AbstractClassMetaData var1);

   boolean isFederated();
}
