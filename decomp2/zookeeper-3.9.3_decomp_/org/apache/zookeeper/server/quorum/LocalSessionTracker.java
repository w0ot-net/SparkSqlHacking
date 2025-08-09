package org.apache.zookeeper.server.quorum;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import org.apache.zookeeper.server.SessionTracker;
import org.apache.zookeeper.server.SessionTrackerImpl;
import org.apache.zookeeper.server.ZooKeeperServerListener;

public class LocalSessionTracker extends SessionTrackerImpl {
   public LocalSessionTracker(SessionTracker.SessionExpirer expirer, ConcurrentMap sessionsWithTimeouts, int tickTime, long id, ZooKeeperServerListener listener) {
      super(expirer, sessionsWithTimeouts, tickTime, id, listener);
   }

   public boolean isLocalSession(long sessionId) {
      return this.isTrackingSession(sessionId);
   }

   public boolean isGlobalSession(long sessionId) {
      return false;
   }

   public long createSession(int sessionTimeout) {
      long sessionId = super.createSession(sessionTimeout);
      this.commitSession(sessionId, sessionTimeout);
      return sessionId;
   }

   public Set localSessions() {
      return this.sessionsWithTimeout.keySet();
   }
}
