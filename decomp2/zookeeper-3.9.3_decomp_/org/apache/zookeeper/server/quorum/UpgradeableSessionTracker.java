package org.apache.zookeeper.server.quorum;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.SessionTracker;
import org.apache.zookeeper.server.ZooKeeperServerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UpgradeableSessionTracker implements SessionTracker {
   private static final Logger LOG = LoggerFactory.getLogger(UpgradeableSessionTracker.class);
   private ConcurrentMap localSessionsWithTimeouts;
   private ConcurrentMap upgradingSessions;
   protected LocalSessionTracker localSessionTracker;
   protected boolean localSessionsEnabled;

   public void start() {
   }

   public void createLocalSessionTracker(SessionTracker.SessionExpirer expirer, int tickTime, long id, ZooKeeperServerListener listener) {
      this.localSessionsWithTimeouts = new ConcurrentHashMap();
      this.localSessionTracker = new LocalSessionTracker(expirer, this.localSessionsWithTimeouts, tickTime, id, listener);
      this.upgradingSessions = new ConcurrentHashMap();
   }

   public boolean isTrackingSession(long sessionId) {
      return this.isLocalSession(sessionId) || this.isGlobalSession(sessionId);
   }

   public boolean isLocalSession(long sessionId) {
      return this.localSessionTracker != null && this.localSessionTracker.isTrackingSession(sessionId);
   }

   public boolean isLocalSessionsEnabled() {
      return this.localSessionsEnabled;
   }

   public boolean isUpgradingSession(long sessionId) {
      return this.upgradingSessions != null && this.upgradingSessions.containsKey(sessionId);
   }

   public void finishedUpgrading(long sessionId) {
      if (this.upgradingSessions != null) {
         this.upgradingSessions.remove(sessionId);
      }

   }

   public abstract boolean isGlobalSession(long var1);

   public int upgradeSession(long sessionId) {
      if (this.localSessionsWithTimeouts == null) {
         return -1;
      } else {
         Integer timeout = (Integer)this.localSessionsWithTimeouts.remove(sessionId);
         if (timeout != null) {
            LOG.info("Upgrading session 0x{}", Long.toHexString(sessionId));
            this.trackSession(sessionId, timeout);
            this.upgradingSessions.put(sessionId, timeout);
            this.localSessionTracker.removeSession(sessionId);
            return timeout;
         } else {
            return -1;
         }
      }
   }

   protected void removeLocalSession(long sessionId) {
      if (this.localSessionTracker != null) {
         this.localSessionTracker.removeSession(sessionId);
      }
   }

   public void checkGlobalSession(long sessionId, Object owner) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException {
      throw new UnsupportedOperationException();
   }

   public long getLocalSessionCount() {
      return this.localSessionsWithTimeouts == null ? 0L : (long)this.localSessionsWithTimeouts.size();
   }

   public Set localSessions() {
      return this.localSessionTracker == null ? Collections.emptySet() : this.localSessionTracker.localSessions();
   }
}
