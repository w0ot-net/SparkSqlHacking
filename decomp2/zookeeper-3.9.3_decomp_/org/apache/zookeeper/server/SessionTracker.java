package org.apache.zookeeper.server;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import org.apache.zookeeper.KeeperException;

public interface SessionTracker {
   long createSession(int var1);

   boolean trackSession(long var1, int var3);

   boolean commitSession(long var1, int var3);

   boolean touchSession(long var1, int var3);

   void setSessionClosing(long var1);

   void shutdown();

   void removeSession(long var1);

   boolean isTrackingSession(long var1);

   void checkSession(long var1, Object var3) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException, KeeperException.UnknownSessionException;

   void checkGlobalSession(long var1, Object var3) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException;

   void setOwner(long var1, Object var3) throws KeeperException.SessionExpiredException;

   void dumpSessions(PrintWriter var1);

   Map getSessionExpiryMap();

   long getLocalSessionCount();

   boolean isLocalSessionsEnabled();

   Set globalSessions();

   Set localSessions();

   public interface Session {
      long getSessionId();

      int getTimeout();

      boolean isClosing();
   }

   public interface SessionExpirer {
      void expire(Session var1);

      long getServerId();
   }
}
