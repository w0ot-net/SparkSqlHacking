package org.apache.zookeeper.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionTrackerImpl extends ZooKeeperCriticalThread implements SessionTracker {
   private static final Logger LOG = LoggerFactory.getLogger(SessionTrackerImpl.class);
   protected final ConcurrentHashMap sessionsById = new ConcurrentHashMap();
   private final ExpiryQueue sessionExpiryQueue;
   protected final ConcurrentMap sessionsWithTimeout;
   private final AtomicLong nextSessionId = new AtomicLong();
   private final SessionTracker.SessionExpirer expirer;
   volatile boolean running = true;

   public static long initializeNextSessionId(long id) {
      long nextSid = Time.currentElapsedTime() << 24 >>> 8;
      nextSid |= id << 56;
      if (nextSid == Long.MIN_VALUE) {
         ++nextSid;
      }

      return nextSid;
   }

   public SessionTrackerImpl(SessionTracker.SessionExpirer expirer, ConcurrentMap sessionsWithTimeout, int tickTime, long serverId, ZooKeeperServerListener listener) {
      super("SessionTracker", listener);
      this.expirer = expirer;
      this.sessionExpiryQueue = new ExpiryQueue(tickTime);
      this.sessionsWithTimeout = sessionsWithTimeout;
      this.nextSessionId.set(initializeNextSessionId(serverId));

      for(Map.Entry e : sessionsWithTimeout.entrySet()) {
         this.trackSession((Long)e.getKey(), (Integer)e.getValue());
      }

      EphemeralType.validateServerId(serverId);
   }

   public void dumpSessions(PrintWriter pwriter) {
      pwriter.print("Session ");
      this.sessionExpiryQueue.dump(pwriter);
   }

   public synchronized Map getSessionExpiryMap() {
      Map<Long, Set<SessionImpl>> expiryMap = this.sessionExpiryQueue.getExpiryMap();
      Map<Long, Set<Long>> sessionExpiryMap = new TreeMap();

      for(Map.Entry e : expiryMap.entrySet()) {
         Set<Long> ids = new HashSet();
         sessionExpiryMap.put((Long)e.getKey(), ids);

         for(SessionImpl s : (Set)e.getValue()) {
            ids.add(s.sessionId);
         }
      }

      return sessionExpiryMap;
   }

   public String toString() {
      StringWriter sw = new StringWriter();
      PrintWriter pwriter = new PrintWriter(sw);
      this.dumpSessions(pwriter);
      pwriter.flush();
      pwriter.close();
      return sw.toString();
   }

   public void run() {
      try {
         while(this.running) {
            long waitTime = this.sessionExpiryQueue.getWaitTime();
            if (waitTime > 0L) {
               Thread.sleep(waitTime);
            } else {
               for(SessionImpl s : this.sessionExpiryQueue.poll()) {
                  ServerMetrics.getMetrics().STALE_SESSIONS_EXPIRED.add(1L);
                  this.setSessionClosing(s.sessionId);
                  this.expirer.expire(s);
               }
            }
         }
      } catch (InterruptedException e) {
         this.handleException(this.getName(), e);
      }

      LOG.info("SessionTrackerImpl exited loop!");
   }

   public synchronized boolean touchSession(long sessionId, int timeout) {
      SessionImpl s = (SessionImpl)this.sessionsById.get(sessionId);
      if (s == null) {
         this.logTraceTouchInvalidSession(sessionId, timeout);
         return false;
      } else if (s.isClosing()) {
         this.logTraceTouchClosingSession(sessionId, timeout);
         return false;
      } else {
         this.updateSessionExpiry(s, timeout);
         return true;
      }
   }

   private void updateSessionExpiry(SessionImpl s, int timeout) {
      this.logTraceTouchSession(s.sessionId, timeout, "");
      this.sessionExpiryQueue.update(s, timeout);
   }

   private void logTraceTouchSession(long sessionId, int timeout, String sessionStatus) {
      if (LOG.isTraceEnabled()) {
         String msg = MessageFormat.format("SessionTrackerImpl --- Touch {0}session: 0x{1} with timeout {2}", sessionStatus, Long.toHexString(sessionId), Integer.toString(timeout));
         ZooTrace.logTraceMessage(LOG, 8L, msg);
      }

   }

   private void logTraceTouchInvalidSession(long sessionId, int timeout) {
      this.logTraceTouchSession(sessionId, timeout, "invalid ");
   }

   private void logTraceTouchClosingSession(long sessionId, int timeout) {
      this.logTraceTouchSession(sessionId, timeout, "closing ");
   }

   public int getSessionTimeout(long sessionId) {
      return (Integer)this.sessionsWithTimeout.get(sessionId);
   }

   public synchronized void setSessionClosing(long sessionId) {
      if (LOG.isTraceEnabled()) {
         LOG.trace("Session closing: 0x{}", Long.toHexString(sessionId));
      }

      SessionImpl s = (SessionImpl)this.sessionsById.get(sessionId);
      if (s != null) {
         s.isClosing = true;
      }
   }

   public synchronized void removeSession(long sessionId) {
      LOG.debug("Removing session 0x{}", Long.toHexString(sessionId));
      SessionImpl s = (SessionImpl)this.sessionsById.remove(sessionId);
      this.sessionsWithTimeout.remove(sessionId);
      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, 32L, "SessionTrackerImpl --- Removing session 0x" + Long.toHexString(sessionId));
      }

      if (s != null) {
         this.sessionExpiryQueue.remove(s);
      }

   }

   public void shutdown() {
      LOG.info("Shutting down");
      this.running = false;
      if (LOG.isTraceEnabled()) {
         ZooTrace.logTraceMessage(LOG, ZooTrace.getTextTraceLevel(), "Shutdown SessionTrackerImpl!");
      }

   }

   public long createSession(int sessionTimeout) {
      long sessionId = this.nextSessionId.getAndIncrement();
      this.trackSession(sessionId, sessionTimeout);
      return sessionId;
   }

   public synchronized boolean trackSession(long id, int sessionTimeout) {
      boolean added = false;
      SessionImpl session = (SessionImpl)this.sessionsById.get(id);
      if (session == null) {
         session = new SessionImpl(id, sessionTimeout);
      }

      SessionImpl existedSession = (SessionImpl)this.sessionsById.putIfAbsent(id, session);
      if (existedSession != null) {
         session = existedSession;
      } else {
         added = true;
         LOG.debug("Adding session 0x{}", Long.toHexString(id));
      }

      if (LOG.isTraceEnabled()) {
         String actionStr = added ? "Adding" : "Existing";
         ZooTrace.logTraceMessage(LOG, 32L, "SessionTrackerImpl --- " + actionStr + " session 0x" + Long.toHexString(id) + " " + sessionTimeout);
      }

      this.updateSessionExpiry(session, sessionTimeout);
      return added;
   }

   public synchronized boolean commitSession(long id, int sessionTimeout) {
      return this.sessionsWithTimeout.put(id, sessionTimeout) == null;
   }

   public boolean isTrackingSession(long sessionId) {
      return this.sessionsById.containsKey(sessionId);
   }

   public synchronized void checkSession(long sessionId, Object owner) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException, KeeperException.UnknownSessionException {
      LOG.debug("Checking session 0x{}", Long.toHexString(sessionId));
      SessionImpl session = (SessionImpl)this.sessionsById.get(sessionId);
      if (session == null) {
         throw new KeeperException.UnknownSessionException();
      } else if (session.isClosing()) {
         throw new KeeperException.SessionExpiredException();
      } else {
         if (session.owner == null) {
            session.owner = owner;
         } else if (session.owner != owner) {
            throw new KeeperException.SessionMovedException();
         }

      }
   }

   public synchronized void setOwner(long id, Object owner) throws KeeperException.SessionExpiredException {
      SessionImpl session = (SessionImpl)this.sessionsById.get(id);
      if (session != null && !session.isClosing()) {
         session.owner = owner;
      } else {
         throw new KeeperException.SessionExpiredException();
      }
   }

   public void checkGlobalSession(long sessionId, Object owner) throws KeeperException.SessionExpiredException, KeeperException.SessionMovedException {
      try {
         this.checkSession(sessionId, owner);
      } catch (KeeperException.UnknownSessionException var5) {
         throw new KeeperException.SessionExpiredException();
      }
   }

   public long getLocalSessionCount() {
      return 0L;
   }

   public boolean isLocalSessionsEnabled() {
      return false;
   }

   public Set globalSessions() {
      return this.sessionsById.keySet();
   }

   public Set localSessions() {
      return Collections.emptySet();
   }

   public static class SessionImpl implements SessionTracker.Session {
      final long sessionId;
      final int timeout;
      boolean isClosing;
      Object owner;

      SessionImpl(long sessionId, int timeout) {
         this.sessionId = sessionId;
         this.timeout = timeout;
         this.isClosing = false;
      }

      public long getSessionId() {
         return this.sessionId;
      }

      public int getTimeout() {
         return this.timeout;
      }

      public boolean isClosing() {
         return this.isClosing;
      }

      public String toString() {
         return "0x" + Long.toHexString(this.sessionId);
      }
   }
}
