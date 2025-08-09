package org.apache.zookeeper.server.quorum;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.SessionTracker;
import org.apache.zookeeper.server.SessionTrackerImpl;
import org.apache.zookeeper.server.ZooKeeperServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LearnerSessionTracker extends UpgradeableSessionTracker {
   private static final Logger LOG = LoggerFactory.getLogger(LearnerSessionTracker.class);
   private final SessionTracker.SessionExpirer expirer;
   private final AtomicReference touchTable = new AtomicReference();
   private final long serverId;
   private final AtomicLong nextSessionId = new AtomicLong();
   private final ConcurrentMap globalSessionsWithTimeouts;

   public LearnerSessionTracker(SessionTracker.SessionExpirer expirer, ConcurrentMap sessionsWithTimeouts, int tickTime, long id, boolean localSessionsEnabled, ZooKeeperServerListener listener) {
      this.expirer = expirer;
      this.touchTable.set(new ConcurrentHashMap());
      this.globalSessionsWithTimeouts = sessionsWithTimeouts;
      this.serverId = id;
      this.nextSessionId.set(SessionTrackerImpl.initializeNextSessionId(this.serverId));
      this.localSessionsEnabled = localSessionsEnabled;
      if (this.localSessionsEnabled) {
         this.createLocalSessionTracker(expirer, tickTime, id, listener);
      }

   }

   public void removeSession(long sessionId) {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.removeSession(sessionId);
      }

      this.globalSessionsWithTimeouts.remove(sessionId);
      ((Map)this.touchTable.get()).remove(sessionId);
   }

   public void start() {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.start();
      }

   }

   public void shutdown() {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.shutdown();
      }

   }

   public boolean isGlobalSession(long sessionId) {
      return this.globalSessionsWithTimeouts.containsKey(sessionId);
   }

   public boolean trackSession(long sessionId, int sessionTimeout) {
      return false;
   }

   public synchronized boolean commitSession(long sessionId, int sessionTimeout) {
      boolean added = this.globalSessionsWithTimeouts.put(sessionId, sessionTimeout) == null;
      if (added) {
         LOG.info("Committing global session 0x{}", Long.toHexString(sessionId));
      }

      if (this.localSessionsEnabled) {
         this.removeLocalSession(sessionId);
         this.finishedUpgrading(sessionId);
      }

      ((Map)this.touchTable.get()).put(sessionId, sessionTimeout);
      return added;
   }

   public boolean touchSession(long sessionId, int sessionTimeout) {
      if (this.localSessionsEnabled) {
         if (this.localSessionTracker.touchSession(sessionId, sessionTimeout)) {
            return true;
         }

         if (!this.isGlobalSession(sessionId) && !this.isUpgradingSession(sessionId)) {
            return false;
         }
      }

      ((Map)this.touchTable.get()).put(sessionId, sessionTimeout);
      return true;
   }

   public Map snapshot() {
      return (Map)this.touchTable.getAndSet(new ConcurrentHashMap());
   }

   public long createSession(int sessionTimeout) {
      return this.localSessionsEnabled ? this.localSessionTracker.createSession(sessionTimeout) : this.nextSessionId.getAndIncrement();
   }

   public void checkSession(long sessionId, Object owner) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException {
      if (this.localSessionTracker != null) {
         try {
            this.localSessionTracker.checkSession(sessionId, owner);
            return;
         } catch (KeeperException.UnknownSessionException var5) {
            if (!this.isGlobalSession(sessionId)) {
               throw new KeeperException.SessionExpiredException();
            }
         }
      }

   }

   public void setOwner(long sessionId, Object owner) throws KeeperException.SessionExpiredException {
      if (this.localSessionTracker != null) {
         try {
            this.localSessionTracker.setOwner(sessionId, owner);
            return;
         } catch (KeeperException.SessionExpiredException e) {
            if (!this.isGlobalSession(sessionId)) {
               throw e;
            }
         }
      }

   }

   public void dumpSessions(PrintWriter pwriter) {
      if (this.localSessionTracker != null) {
         pwriter.print("Local ");
         this.localSessionTracker.dumpSessions(pwriter);
      }

      pwriter.print("Global Sessions(");
      pwriter.print(this.globalSessionsWithTimeouts.size());
      pwriter.println("):");

      for(long sessionId : new TreeSet(this.globalSessionsWithTimeouts.keySet())) {
         pwriter.print("0x");
         pwriter.print(Long.toHexString(sessionId));
         pwriter.print("\t");
         pwriter.print(this.globalSessionsWithTimeouts.get(sessionId));
         pwriter.println("ms");
      }

   }

   public void setSessionClosing(long sessionId) {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.setSessionClosing(sessionId);
      }

   }

   public Map getSessionExpiryMap() {
      return new HashMap();
   }

   public Set globalSessions() {
      return this.globalSessionsWithTimeouts.keySet();
   }
}
