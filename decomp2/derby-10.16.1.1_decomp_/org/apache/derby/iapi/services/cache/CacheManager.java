package org.apache.derby.iapi.services.cache;

import java.util.Collection;
import org.apache.derby.iapi.services.daemon.DaemonService;
import org.apache.derby.iapi.util.Matchable;
import org.apache.derby.shared.common.error.StandardException;

public interface CacheManager {
   Cacheable find(Object var1) throws StandardException;

   Cacheable findCached(Object var1) throws StandardException;

   Cacheable create(Object var1, Object var2) throws StandardException;

   void release(Cacheable var1);

   void remove(Cacheable var1) throws StandardException;

   void cleanAll() throws StandardException;

   void clean(Matchable var1) throws StandardException;

   void ageOut();

   void shutdown() throws StandardException;

   void useDaemonService(DaemonService var1);

   boolean discard(Matchable var1);

   Collection values();

   void registerMBean(String var1) throws StandardException;

   void deregisterMBean();
}
