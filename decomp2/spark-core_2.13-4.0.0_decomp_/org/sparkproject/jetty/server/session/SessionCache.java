package org.sparkproject.jetty.server.session;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import org.sparkproject.jetty.util.component.LifeCycle;

public interface SessionCache extends LifeCycle {
   int NEVER_EVICT = -1;
   int EVICT_ON_SESSION_EXIT = 0;
   int EVICT_ON_INACTIVITY = 1;

   void initialize(SessionContext var1);

   void shutdown();

   SessionHandler getSessionHandler();

   Session newSession(HttpServletRequest var1, String var2, long var3, long var5);

   Session newSession(SessionData var1);

   Session renewSessionId(String var1, String var2, String var3, String var4) throws Exception;

   void add(String var1, Session var2) throws Exception;

   Session get(String var1) throws Exception;

   /** @deprecated */
   @Deprecated
   void put(String var1, Session var2) throws Exception;

   void release(String var1, Session var2) throws Exception;

   void commit(Session var1) throws Exception;

   boolean contains(String var1) throws Exception;

   boolean exists(String var1) throws Exception;

   Session delete(String var1) throws Exception;

   Set checkExpiration(Set var1);

   void checkInactiveSession(Session var1);

   void setSessionDataStore(SessionDataStore var1);

   SessionDataStore getSessionDataStore();

   void setEvictionPolicy(int var1);

   int getEvictionPolicy();

   void setSaveOnInactiveEviction(boolean var1);

   boolean isSaveOnInactiveEviction();

   void setSaveOnCreate(boolean var1);

   boolean isSaveOnCreate();

   void setRemoveUnloadableSessions(boolean var1);

   boolean isRemoveUnloadableSessions();

   void setFlushOnResponseCommit(boolean var1);

   boolean isFlushOnResponseCommit();

   void setInvalidateOnShutdown(boolean var1);

   boolean isInvalidateOnShutdown();
}
