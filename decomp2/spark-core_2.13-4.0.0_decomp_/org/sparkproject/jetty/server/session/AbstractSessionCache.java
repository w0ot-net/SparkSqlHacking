package org.sparkproject.jetty.server.session;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject
public abstract class AbstractSessionCache extends ContainerLifeCycle implements SessionCache {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractSessionCache.class);
   protected SessionDataStore _sessionDataStore;
   protected final SessionHandler _handler;
   protected SessionContext _context;
   protected int _evictionPolicy = -1;
   protected boolean _saveOnCreate = false;
   protected boolean _saveOnInactiveEviction;
   protected boolean _removeUnloadableSessions;
   protected boolean _flushOnResponseCommit;
   protected boolean _invalidateOnShutdown;

   public abstract Session newSession(SessionData var1);

   public abstract Session newSession(HttpServletRequest var1, SessionData var2);

   protected abstract Session doGet(String var1);

   protected abstract Session doPutIfAbsent(String var1, Session var2);

   protected abstract Session doComputeIfAbsent(String var1, Function var2);

   protected abstract boolean doReplace(String var1, Session var2, Session var3);

   public abstract Session doDelete(String var1);

   public AbstractSessionCache(SessionHandler handler) {
      this._handler = handler;
   }

   public SessionHandler getSessionHandler() {
      return this._handler;
   }

   public void initialize(SessionContext context) {
      if (this.isStarted()) {
         throw new IllegalStateException("Context set after session store started");
      } else {
         this._context = context;
      }
   }

   protected void doStart() throws Exception {
      if (this._sessionDataStore == null) {
         throw new IllegalStateException("No session data store configured");
      } else if (this._handler == null) {
         throw new IllegalStateException("No session manager");
      } else if (this._context == null) {
         throw new IllegalStateException("No ContextId");
      } else {
         this._sessionDataStore.initialize(this._context);
         super.doStart();
      }
   }

   protected void doStop() throws Exception {
      this._sessionDataStore.stop();
      super.doStop();
   }

   public SessionDataStore getSessionDataStore() {
      return this._sessionDataStore;
   }

   public void setSessionDataStore(SessionDataStore sessionStore) {
      this.updateBean(this._sessionDataStore, sessionStore);
      this._sessionDataStore = sessionStore;
   }

   @ManagedAttribute(
      value = "session eviction policy",
      readonly = true
   )
   public int getEvictionPolicy() {
      return this._evictionPolicy;
   }

   public void setEvictionPolicy(int evictionTimeout) {
      this._evictionPolicy = evictionTimeout;
   }

   @ManagedAttribute(
      value = "immediately save new sessions",
      readonly = true
   )
   public boolean isSaveOnCreate() {
      return this._saveOnCreate;
   }

   public void setSaveOnCreate(boolean saveOnCreate) {
      this._saveOnCreate = saveOnCreate;
   }

   @ManagedAttribute(
      value = "delete unreadable stored sessions",
      readonly = true
   )
   public boolean isRemoveUnloadableSessions() {
      return this._removeUnloadableSessions;
   }

   public void setRemoveUnloadableSessions(boolean removeUnloadableSessions) {
      this._removeUnloadableSessions = removeUnloadableSessions;
   }

   public void setFlushOnResponseCommit(boolean flushOnResponseCommit) {
      this._flushOnResponseCommit = flushOnResponseCommit;
   }

   public boolean isFlushOnResponseCommit() {
      return this._flushOnResponseCommit;
   }

   public Session get(String id) throws Exception {
      return this.getAndEnter(id, true);
   }

   protected Session getAndEnter(String id, boolean enter) throws Exception {
      Session session = null;
      AtomicReference<Exception> exception = new AtomicReference();
      session = this.doComputeIfAbsent(id, (k) -> {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Session {} not found locally in {}, attempting to load", id, this);
         }

         try {
            Session s = this.loadSession(k);
            if (s != null) {
               try (AutoLock lock = s.lock()) {
                  s.setResident(true);
               }
            } else if (LOG.isDebugEnabled()) {
               LOG.debug("Session {} not loaded by store", id);
            }

            return s;
         } catch (Exception e) {
            exception.set(e);
            return null;
         }
      });
      Exception ex = (Exception)exception.get();
      if (ex != null) {
         throw ex;
      } else {
         if (session != null) {
            try (AutoLock lock = session.lock()) {
               if (!session.isResident()) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Non-resident session {} in cache", id);
                  }

                  return null;
               }

               if (enter) {
                  session.use();
               }
            }
         }

         return session;
      }
   }

   private Session loadSession(String id) throws Exception {
      SessionData data = null;
      Session session = null;
      if (this._sessionDataStore == null) {
         return null;
      } else {
         try {
            data = this._sessionDataStore.load(id);
            if (data == null) {
               return null;
            } else {
               data.setLastNode(this._context.getWorkerName());
               session = this.newSession(data);
               return session;
            }
         } catch (UnreadableSessionDataException e) {
            if (this.isRemoveUnloadableSessions()) {
               this._sessionDataStore.delete(id);
            }

            throw e;
         }
      }
   }

   public void add(String id, Session session) throws Exception {
      if (id != null && session != null) {
         try (AutoLock lock = session.lock()) {
            if (session.getSessionHandler() == null) {
               throw new IllegalStateException("Session " + id + " is not managed");
            }

            if (!session.isValid()) {
               throw new IllegalStateException("Session " + id + " is not valid");
            }

            if (this.doPutIfAbsent(id, session) != null) {
               throw new IllegalStateException("Session " + id + " already in cache");
            }

            session.setResident(true);
            session.use();
         }

      } else {
         throw new IllegalArgumentException("Add key=" + id + " session=" + (session == null ? "null" : session.getId()));
      }
   }

   public void commit(Session session) throws Exception {
      if (session != null) {
         try (AutoLock lock = session.lock()) {
            if (session.isValid() && session.getSessionData().isDirty() && this._flushOnResponseCommit) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Flush session {} on response commit", session);
               }

               if (!this._sessionDataStore.isPassivating()) {
                  this._sessionDataStore.store(session.getId(), session.getSessionData());
               } else {
                  session.willPassivate();
                  this._sessionDataStore.store(session.getId(), session.getSessionData());
                  session.didActivate();
               }
            }
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public void put(String id, Session session) throws Exception {
      this.release(id, session);
   }

   public void release(String id, Session session) throws Exception {
      if (id != null && session != null) {
         try (AutoLock lock = session.lock()) {
            if (session.getSessionHandler() == null) {
               throw new IllegalStateException("Session " + id + " is not managed");
            }

            if (session.isInvalid()) {
               return;
            }

            session.complete();
            if (session.getRequests() <= 0L) {
               session.setIdChanged(false);
               if (!this._sessionDataStore.isPassivating()) {
                  this._sessionDataStore.store(id, session.getSessionData());
                  if (this.getEvictionPolicy() == 0) {
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Eviction on request exit id={}", id);
                     }

                     this.doDelete(session.getId());
                     session.setResident(false);
                  } else {
                     session.setResident(true);
                     this.doPutIfAbsent(id, session);
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Non passivating SessionDataStore, session in SessionCache only id={}", id);
                     }
                  }
               } else {
                  session.willPassivate();
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Session passivating id={}", id);
                  }

                  this._sessionDataStore.store(id, session.getSessionData());
                  if (this.getEvictionPolicy() == 0) {
                     this.doDelete(id);
                     session.setResident(false);
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Evicted on request exit id={}", id);
                     }
                  } else {
                     session.didActivate();
                     session.setResident(true);
                     this.doPutIfAbsent(id, session);
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Session reactivated id={}", id);
                     }
                  }
               }
            } else {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Req count={} for id={}", session.getRequests(), id);
               }

               session.setResident(true);
               this.doPutIfAbsent(id, session);
            }
         }

      } else {
         throw new IllegalArgumentException("Put key=" + id + " session=" + (session == null ? "null" : session.getId()));
      }
   }

   public boolean exists(String id) throws Exception {
      Session s = this.doGet(id);
      if (s != null) {
         try (AutoLock lock = s.lock()) {
            return s.isValid();
         }
      } else {
         return this._sessionDataStore.exists(id);
      }
   }

   public boolean contains(String id) throws Exception {
      return this.doGet(id) != null;
   }

   public Session delete(String id) throws Exception {
      Session session = this.getAndEnter(id, false);
      if (this._sessionDataStore != null) {
         boolean dsdel = this._sessionDataStore.delete(id);
         if (LOG.isDebugEnabled()) {
            LOG.debug("Session id={} deleted in session data store {}", id, dsdel);
         }
      }

      if (session != null) {
         session.setResident(false);
      }

      return this.doDelete(id);
   }

   public Set checkExpiration(Set candidates) {
      if (!this.isStarted()) {
         return Collections.emptySet();
      } else {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} checking expiration on {}", this, candidates);
         }

         Set<String> allCandidates = this._sessionDataStore.getExpired(candidates);
         Set<String> sessionsInUse = new HashSet();
         if (allCandidates != null) {
            for(String c : allCandidates) {
               Session s = this.doGet(c);
               if (s != null && s.getRequests() > 0L) {
                  sessionsInUse.add(c);
               }
            }

            try {
               allCandidates.removeAll(sessionsInUse);
            } catch (UnsupportedOperationException var7) {
               Set<String> tmp = new HashSet(allCandidates);
               tmp.removeAll(sessionsInUse);
               allCandidates = tmp;
            }
         }

         return allCandidates;
      }
   }

   public void checkInactiveSession(Session session) {
      if (session != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Checking for idle {}", session.getId());
         }

         try (AutoLock s = session.lock()) {
            if (this.getEvictionPolicy() > 0 && session.isIdleLongerThan(this.getEvictionPolicy()) && session.isValid() && session.isResident() && session.getRequests() <= 0L) {
               try {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Evicting idle session {}", session.getId());
                  }

                  if (this.isSaveOnInactiveEviction() && this._sessionDataStore != null) {
                     if (this._sessionDataStore.isPassivating()) {
                        session.willPassivate();
                     }

                     session.getSessionData().setDirty(true);
                     this._sessionDataStore.store(session.getId(), session.getSessionData());
                  }

                  this.doDelete(session.getId());
                  session.setResident(false);
               } catch (Exception e) {
                  LOG.warn("Passivation of idle session {} failed", session.getId(), e);
               }
            }
         }

      }
   }

   public Session renewSessionId(String oldId, String newId, String oldExtendedId, String newExtendedId) throws Exception {
      if (StringUtil.isBlank(oldId)) {
         throw new IllegalArgumentException("Old session id is null");
      } else if (StringUtil.isBlank(newId)) {
         throw new IllegalArgumentException("New session id is null");
      } else {
         Session session = this.getAndEnter(oldId, true);
         this.renewSessionId(session, newId, newExtendedId);
         return session;
      }
   }

   protected void renewSessionId(Session session, String newId, String newExtendedId) throws Exception {
      if (session != null) {
         try (AutoLock lock = session.lock()) {
            String oldId = session.getId();
            session.checkValidForWrite();
            session.getSessionData().setId(newId);
            session.getSessionData().setLastSaved(0L);
            session.getSessionData().setDirty(true);
            session.setExtendedId(newExtendedId);
            session.setIdChanged(true);
            this.doPutIfAbsent(newId, session);
            this.doDelete(oldId);
            if (this._sessionDataStore != null) {
               this._sessionDataStore.delete(oldId);
               this._sessionDataStore.store(newId, session.getSessionData());
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Session id={} swapped for new id={}", oldId, newId);
            }
         }

      }
   }

   public void setSaveOnInactiveEviction(boolean saveOnEvict) {
      this._saveOnInactiveEviction = saveOnEvict;
   }

   public void setInvalidateOnShutdown(boolean invalidateOnShutdown) {
      this._invalidateOnShutdown = invalidateOnShutdown;
   }

   public boolean isInvalidateOnShutdown() {
      return this._invalidateOnShutdown;
   }

   @ManagedAttribute(
      value = "save sessions before evicting from cache",
      readonly = true
   )
   public boolean isSaveOnInactiveEviction() {
      return this._saveOnInactiveEviction;
   }

   public Session newSession(HttpServletRequest request, String id, long time, long maxInactiveMs) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Creating new session id={}", id);
      }

      Session session = this.newSession(request, this._sessionDataStore.newSessionData(id, time, time, time, maxInactiveMs));
      session.getSessionData().setLastNode(this._context.getWorkerName());

      try {
         if (this.isSaveOnCreate() && this._sessionDataStore != null) {
            this._sessionDataStore.store(id, session.getSessionData());
         }
      } catch (Exception e) {
         LOG.warn("Save of new session {} failed", id, e);
      }

      return session;
   }

   public String toString() {
      return String.format("%s@%x[evict=%d,removeUnloadable=%b,saveOnCreate=%b,saveOnInactiveEvict=%b]", this.getClass().getName(), this.hashCode(), this._evictionPolicy, this._removeUnloadableSessions, this._saveOnCreate, this._saveOnInactiveEviction);
   }
}
