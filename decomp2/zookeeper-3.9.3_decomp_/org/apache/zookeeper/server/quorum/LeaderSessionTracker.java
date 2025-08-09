package org.apache.zookeeper.server.quorum;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.SessionTracker;
import org.apache.zookeeper.server.SessionTrackerImpl;
import org.apache.zookeeper.server.ZooKeeperServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderSessionTracker extends UpgradeableSessionTracker {
   private static final Logger LOG = LoggerFactory.getLogger(LeaderSessionTracker.class);
   private final SessionTrackerImpl globalSessionTracker;
   private final long serverId;

   public LeaderSessionTracker(SessionTracker.SessionExpirer expirer, ConcurrentMap sessionsWithTimeouts, int tickTime, long id, boolean localSessionsEnabled, ZooKeeperServerListener listener) {
      this.globalSessionTracker = new SessionTrackerImpl(expirer, sessionsWithTimeouts, tickTime, id, listener);
      this.localSessionsEnabled = localSessionsEnabled;
      if (this.localSessionsEnabled) {
         this.createLocalSessionTracker(expirer, tickTime, id, listener);
      }

      this.serverId = id;
   }

   public void removeSession(long sessionId) {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.removeSession(sessionId);
      }

      this.globalSessionTracker.removeSession(sessionId);
   }

   public void start() {
      this.globalSessionTracker.start();
      if (this.localSessionTracker != null) {
         this.localSessionTracker.start();
      }

   }

   public void shutdown() {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.shutdown();
      }

      this.globalSessionTracker.shutdown();
   }

   public boolean isGlobalSession(long sessionId) {
      return this.globalSessionTracker.isTrackingSession(sessionId);
   }

   public boolean trackSession(long sessionId, int sessionTimeout) {
      boolean tracked = this.globalSessionTracker.trackSession(sessionId, sessionTimeout);
      if (this.localSessionsEnabled && tracked) {
         LOG.info("Tracking global session 0x{}", Long.toHexString(sessionId));
      }

      return tracked;
   }

   public synchronized boolean commitSession(long sessionId, int sessionTimeout) {
      boolean added = this.globalSessionTracker.commitSession(sessionId, sessionTimeout);
      if (added) {
         LOG.info("Committing global session 0x{}", Long.toHexString(sessionId));
      }

      if (this.localSessionsEnabled) {
         this.removeLocalSession(sessionId);
         this.finishedUpgrading(sessionId);
      }

      return added;
   }

   public boolean touchSession(long sessionId, int sessionTimeout) {
      return this.localSessionTracker != null && this.localSessionTracker.touchSession(sessionId, sessionTimeout) ? true : this.globalSessionTracker.touchSession(sessionId, sessionTimeout);
   }

   public long createSession(int sessionTimeout) {
      return this.localSessionsEnabled ? this.localSessionTracker.createSession(sessionTimeout) : this.globalSessionTracker.createSession(sessionTimeout);
   }

   public static long getServerIdFromSessionId(long sessionId) {
      return sessionId >> 56;
   }

   public void checkSession(long sessionId, Object owner) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException, KeeperException.UnknownSessionException {
      if (this.localSessionTracker != null) {
         try {
            this.localSessionTracker.checkSession(sessionId, owner);
            if (!this.isGlobalSession(sessionId)) {
               return;
            }
         } catch (KeeperException.UnknownSessionException var5) {
         }
      }

      try {
         this.globalSessionTracker.checkSession(sessionId, owner);
      } catch (KeeperException.UnknownSessionException var6) {
         if (!this.localSessionsEnabled || getServerIdFromSessionId(sessionId) == this.serverId) {
            throw new KeeperException.SessionExpiredException();
         }
      }
   }

   public void checkGlobalSession(long sessionId, Object owner) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException {
      try {
         this.globalSessionTracker.checkSession(sessionId, owner);
      } catch (KeeperException.UnknownSessionException var5) {
         throw new KeeperException.SessionExpiredException();
      }
   }

   public void setOwner(long sessionId, Object owner) throws KeeperException.SessionExpiredException {
      if (this.localSessionTracker != null) {
         try {
            this.localSessionTracker.setOwner(sessionId, owner);
            return;
         } catch (KeeperException.SessionExpiredException var5) {
         }
      }

      this.globalSessionTracker.setOwner(sessionId, owner);
   }

   public void dumpSessions(PrintWriter pwriter) {
      if (this.localSessionTracker != null) {
         pwriter.print("Local ");
         this.localSessionTracker.dumpSessions(pwriter);
         pwriter.print("Global ");
      }

      this.globalSessionTracker.dumpSessions(pwriter);
   }

   public void setSessionClosing(long sessionId) {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.setSessionClosing(sessionId);
      }

      this.globalSessionTracker.setSessionClosing(sessionId);
   }

   public Map getSessionExpiryMap() {
      Map<Long, Set<Long>> sessionExpiryMap;
      if (this.localSessionTracker != null) {
         sessionExpiryMap = this.localSessionTracker.getSessionExpiryMap();
      } else {
         sessionExpiryMap = new TreeMap();
      }

      sessionExpiryMap.putAll(this.globalSessionTracker.getSessionExpiryMap());
      return sessionExpiryMap;
   }

   public Set globalSessions() {
      return this.globalSessionTracker.globalSessions();
   }
}
