package org.sparkproject.jetty.server.session;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionContext;
import jakarta.servlet.http.HttpSessionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.io.CyclicTimeout;
import org.sparkproject.jetty.util.thread.AutoLock;

public class Session implements SessionHandler.SessionIf {
   private static final Logger LOG = LoggerFactory.getLogger(Session.class);
   public static final String SESSION_CREATED_SECURE = "org.sparkproject.jetty.security.sessionCreatedSecure";
   protected final SessionData _sessionData;
   protected final SessionHandler _handler;
   protected String _extendedId;
   protected long _requests;
   protected boolean _idChanged;
   protected boolean _newSession;
   protected State _state;
   protected AutoLock _lock;
   protected Condition _stateChangeCompleted;
   protected boolean _resident;
   protected final SessionInactivityTimer _sessionInactivityTimer;

   public Session(SessionHandler handler, HttpServletRequest request, SessionData data) {
      this._state = Session.State.VALID;
      this._lock = new AutoLock();
      this._stateChangeCompleted = this._lock.newCondition();
      this._resident = false;
      this._handler = handler;
      this._sessionData = data;
      this._newSession = true;
      this._sessionData.setDirty(true);
      this._sessionInactivityTimer = new SessionInactivityTimer();
   }

   public Session(SessionHandler handler, SessionData data) {
      this._state = Session.State.VALID;
      this._lock = new AutoLock();
      this._stateChangeCompleted = this._lock.newCondition();
      this._resident = false;
      this._handler = handler;
      this._sessionData = data;
      this._sessionInactivityTimer = new SessionInactivityTimer();
   }

   public long getRequests() {
      try (AutoLock l = this._lock.lock()) {
         return this._requests;
      }
   }

   public void setExtendedId(String extendedId) {
      this._extendedId = extendedId;
   }

   protected void cookieSet() {
      try (AutoLock l = this._lock.lock()) {
         this._sessionData.setCookieSet(this._sessionData.getAccessed());
      }

   }

   protected void use() {
      try (AutoLock l = this._lock.lock()) {
         ++this._requests;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Session {} in use, stopping timer, active requests={}", this.getId(), this._requests);
         }

         this._sessionInactivityTimer.cancel();
      }

   }

   protected boolean access(long time) {
      try (AutoLock l = this._lock.lock()) {
         if (this.isValid() && this.isResident()) {
            this._newSession = false;
            long lastAccessed = this._sessionData.getAccessed();
            this._sessionData.setAccessed(time);
            this._sessionData.setLastAccessed(lastAccessed);
            this._sessionData.calcAndSetExpiry(time);
            if (this.isExpiredAt(time)) {
               this.invalidate();
               return false;
            } else {
               return true;
            }
         } else {
            return false;
         }
      }
   }

   protected void complete() {
      try (AutoLock l = this._lock.lock()) {
         --this._requests;
         if (LOG.isDebugEnabled()) {
            LOG.debug("Session {} complete, active requests={}", this.getId(), this._requests);
         }

         if (this._requests == 0L) {
            long now = System.currentTimeMillis();
            this._sessionData.calcAndSetExpiry(now);
            this._sessionInactivityTimer.schedule(this.calculateInactivityTimeout(now));
         }
      }

   }

   protected boolean isExpiredAt(long time) {
      try (AutoLock l = this._lock.lock()) {
         return this._sessionData.isExpiredAt(time);
      }
   }

   protected boolean isIdleLongerThan(int sec) {
      long now = System.currentTimeMillis();

      boolean var5;
      try (AutoLock l = this._lock.lock()) {
         var5 = this._sessionData.getAccessed() + (long)(sec * 1000) <= now;
      }

      return var5;
   }

   protected void callSessionAttributeListeners(String name, Object newValue, Object oldValue) {
      if (newValue == null || !newValue.equals(oldValue)) {
         if (oldValue != null) {
            this.unbindValue(name, oldValue);
         }

         if (newValue != null) {
            this.bindValue(name, newValue);
         }

         if (this._handler == null) {
            throw new IllegalStateException("No session manager for session " + this._sessionData.getId());
         }

         this._handler.doSessionAttributeListeners(this, name, oldValue, newValue);
      }

   }

   public void unbindValue(String name, Object value) {
      if (value instanceof HttpSessionBindingListener) {
         ((HttpSessionBindingListener)value).valueUnbound(new HttpSessionBindingEvent(this, name));
      }

   }

   public void bindValue(String name, Object value) {
      if (value instanceof HttpSessionBindingListener) {
         ((HttpSessionBindingListener)value).valueBound(new HttpSessionBindingEvent(this, name));
      }

   }

   public void didActivate() {
      boolean dirty = this.getSessionData().isDirty();

      try {
         HttpSessionEvent event = new HttpSessionEvent(this);

         for(String name : this._sessionData.getKeys()) {
            Object value = this._sessionData.getAttribute(name);
            if (value instanceof HttpSessionActivationListener) {
               HttpSessionActivationListener listener = (HttpSessionActivationListener)value;
               listener.sessionDidActivate(event);
            }
         }
      } finally {
         this.getSessionData().setDirty(dirty);
      }

   }

   public void willPassivate() {
      HttpSessionEvent event = new HttpSessionEvent(this);

      for(String name : this._sessionData.getKeys()) {
         Object value = this._sessionData.getAttribute(name);
         if (value instanceof HttpSessionActivationListener) {
            HttpSessionActivationListener listener = (HttpSessionActivationListener)value;
            listener.sessionWillPassivate(event);
         }
      }

   }

   public boolean isValid() {
      boolean var2;
      try (AutoLock l = this._lock.lock()) {
         var2 = this._state == Session.State.VALID;
      }

      return var2;
   }

   public boolean isInvalid() {
      boolean var2;
      try (AutoLock l = this._lock.lock()) {
         var2 = this._state == Session.State.INVALID || this._state == Session.State.INVALIDATING;
      }

      return var2;
   }

   public long getCookieSetTime() {
      try (AutoLock l = this._lock.lock()) {
         return this._sessionData.getCookieSet();
      }
   }

   public long getCreationTime() throws IllegalStateException {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         return this._sessionData.getCreated();
      }
   }

   public String getId() {
      try (AutoLock l = this._lock.lock()) {
         return this._sessionData.getId();
      }
   }

   public String getExtendedId() {
      return this._extendedId;
   }

   public String getContextPath() {
      return this._sessionData.getContextPath();
   }

   public String getVHost() {
      return this._sessionData.getVhost();
   }

   public long getLastAccessedTime() {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         return this._sessionData.getLastAccessed();
      }
   }

   public ServletContext getServletContext() {
      if (this._handler == null) {
         throw new IllegalStateException("No session manager for session " + this._sessionData.getId());
      } else {
         return this._handler._context;
      }
   }

   public void setMaxInactiveInterval(int secs) {
      try (AutoLock l = this._lock.lock()) {
         this._sessionData.setMaxInactiveMs((long)secs * 1000L);
         this._sessionData.calcAndSetExpiry();
         this._sessionData.setDirty(true);
         if (LOG.isDebugEnabled()) {
            if (secs <= 0) {
               LOG.debug("Session {} is now immortal (maxInactiveInterval={})", this._sessionData.getId(), secs);
            } else {
               LOG.debug("Session {} maxInactiveInterval={}", this._sessionData.getId(), secs);
            }
         }
      }

   }

   public long calculateInactivityTimeout(long now) {
      long time = 0L;

      try (AutoLock l = this._lock.lock()) {
         long remaining = this._sessionData.getExpiry() - now;
         long maxInactive = this._sessionData.getMaxInactiveMs();
         int evictionPolicy = this.getSessionHandler().getSessionCache().getEvictionPolicy();
         if (maxInactive <= 0L) {
            if (evictionPolicy < 1) {
               time = -1L;
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Session {} is immortal && no inactivity eviction", this.getId());
               }
            } else {
               time = TimeUnit.SECONDS.toMillis((long)evictionPolicy);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Session {} is immortal; evict after {} sec inactivity", this.getId(), evictionPolicy);
               }
            }
         } else if (evictionPolicy == -1) {
            time = remaining > 0L ? remaining : 0L;
            if (LOG.isDebugEnabled()) {
               LOG.debug("Session {} no eviction", this.getId());
            }
         } else if (evictionPolicy == 0) {
            time = -1L;
            if (LOG.isDebugEnabled()) {
               LOG.debug("Session {} evict on exit", this.getId());
            }
         } else {
            time = remaining > 0L ? Math.min(maxInactive, TimeUnit.SECONDS.toMillis((long)evictionPolicy)) : 0L;
            if (LOG.isDebugEnabled()) {
               LOG.debug("Session {} timer set to lesser of maxInactive={} and inactivityEvict={}", new Object[]{this.getId(), maxInactive, evictionPolicy});
            }
         }
      }

      return time;
   }

   public int getMaxInactiveInterval() {
      int var4;
      try (AutoLock l = this._lock.lock()) {
         long maxInactiveMs = this._sessionData.getMaxInactiveMs();
         var4 = (int)(maxInactiveMs < 0L ? -1L : maxInactiveMs / 1000L);
      }

      return var4;
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public HttpSessionContext getSessionContext() {
      this.checkValidForRead();
      return SessionHandler.__nullSessionContext;
   }

   public SessionHandler getSessionHandler() {
      return this._handler;
   }

   protected void checkValidForWrite() throws IllegalStateException {
      if (this._state == Session.State.INVALID) {
         String var10002 = this._sessionData.getId();
         throw new IllegalStateException("Not valid for write: id=" + var10002 + " created=" + this._sessionData.getCreated() + " accessed=" + this._sessionData.getAccessed() + " lastaccessed=" + this._sessionData.getLastAccessed() + " maxInactiveMs=" + this._sessionData.getMaxInactiveMs() + " expiry=" + this._sessionData.getExpiry());
      } else if (this._state != Session.State.INVALIDATING) {
         if (!this.isResident()) {
            throw new IllegalStateException("Not valid for write: id=" + this._sessionData.getId() + " not resident");
         }
      }
   }

   protected void checkValidForRead() throws IllegalStateException {
      if (this._state == Session.State.INVALID) {
         String var10002 = this._sessionData.getId();
         throw new IllegalStateException("Invalid for read: id=" + var10002 + " created=" + this._sessionData.getCreated() + " accessed=" + this._sessionData.getAccessed() + " lastaccessed=" + this._sessionData.getLastAccessed() + " maxInactiveMs=" + this._sessionData.getMaxInactiveMs() + " expiry=" + this._sessionData.getExpiry());
      } else if (this._state != Session.State.INVALIDATING) {
         if (!this.isResident()) {
            throw new IllegalStateException("Invalid for read: id=" + this._sessionData.getId() + " not resident");
         }
      }
   }

   public Object getAttribute(String name) {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         return this._sessionData.getAttribute(name);
      }
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.2"
   )
   public Object getValue(String name) {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         return this._sessionData.getAttribute(name);
      }
   }

   public Enumeration getAttributeNames() {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         final Iterator<String> itor = this._sessionData.getKeys().iterator();
         return new Enumeration() {
            public boolean hasMoreElements() {
               return itor.hasNext();
            }

            public String nextElement() {
               return (String)itor.next();
            }
         };
      }
   }

   public int getAttributes() {
      return this._sessionData.getKeys().size();
   }

   public Set getNames() {
      return Collections.unmodifiableSet(this._sessionData.getKeys());
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.2"
   )
   public String[] getValueNames() throws IllegalStateException {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         Iterator<String> itor = this._sessionData.getKeys().iterator();
         if (!itor.hasNext()) {
            return new String[0];
         } else {
            ArrayList<String> names = new ArrayList();

            while(itor.hasNext()) {
               names.add((String)itor.next());
            }

            return (String[])names.toArray(new String[names.size()]);
         }
      }
   }

   public void setAttribute(String name, Object value) {
      Object old = null;

      try (AutoLock l = this._lock.lock()) {
         this.checkValidForWrite();
         old = this._sessionData.setAttribute(name, value);
      }

      if (value != null || old != null) {
         this.callSessionAttributeListeners(name, value, old);
      }
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.2"
   )
   public void putValue(String name, Object value) {
      this.setAttribute(name, value);
   }

   public void removeAttribute(String name) {
      this.setAttribute(name, (Object)null);
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public void removeValue(String name) {
      this.setAttribute(name, (Object)null);
   }

   public void renewId(HttpServletRequest request) {
      if (this._handler == null) {
         throw new IllegalStateException("No session manager for session " + this._sessionData.getId());
      } else {
         String id = null;
         String extendedId = null;

         try (AutoLock l = this._lock.lock()) {
            label77:
            while(true) {
               switch (this._state.ordinal()) {
                  case 0:
                     this._state = Session.State.CHANGING;
                     id = this._sessionData.getId();
                     extendedId = this.getExtendedId();
                     break label77;
                  case 1:
                  case 2:
                     throw new IllegalStateException();
                  case 3:
                     try {
                        this._stateChangeCompleted.await();
                        break;
                     } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                     }
                  default:
                     throw new IllegalStateException();
               }
            }
         }

         String newId = this._handler._sessionIdManager.renewSessionId(id, extendedId, request);

         try (AutoLock l = this._lock.lock()) {
            switch (this._state.ordinal()) {
               case 1:
               case 2:
                  throw new IllegalStateException("Session invalid");
               case 3:
                  if (id.equals(newId)) {
                     throw new IllegalStateException("Unable to change session id");
                  }

                  this._sessionData.setId(newId);
                  this.setExtendedId(this._handler._sessionIdManager.getExtendedId(newId, request));
                  this.setIdChanged(true);
                  this._state = Session.State.VALID;
                  this._stateChangeCompleted.signalAll();
                  return;
               default:
                  throw new IllegalStateException();
            }
         }
      }
   }

   public void invalidate() {
      if (this._handler == null) {
         throw new IllegalStateException("No session manager for session " + this._sessionData.getId());
      } else {
         boolean result = this.beginInvalidate();

         try {
            if (result) {
               try {
                  this._handler.callSessionDestroyedListeners(this);
               } catch (Exception e) {
                  LOG.warn("Error during Session destroy listener", e);
               } finally {
                  this.finishInvalidate();
                  this._handler.getSessionIdManager().invalidateAll(this._sessionData.getId());
               }
            }
         } catch (Exception e) {
            LOG.warn("Unable to invalidate Session {}", this, e);
         }

      }
   }

   public AutoLock lock() {
      return this._lock.lock();
   }

   protected boolean beginInvalidate() {
      boolean result = false;
      AutoLock l = this._lock.lock();

      while(true) {
         try {
            switch (this._state.ordinal()) {
               case 0:
                  result = true;
                  this._state = Session.State.INVALIDATING;
                  break;
               case 1:
                  throw new IllegalStateException();
               case 2:
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Session {} already being invalidated", this._sessionData.getId());
                  }
                  break;
               case 3:
                  try {
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Session {} waiting for id change to complete", this._sessionData.getId());
                     }

                     this._stateChangeCompleted.await();
                     continue;
                  } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                  }
               default:
                  throw new IllegalStateException();
            }
         } catch (Throwable var7) {
            if (l != null) {
               try {
                  l.close();
               } catch (Throwable var5) {
                  var7.addSuppressed(var5);
               }
            }

            throw var7;
         }

         if (l != null) {
            l.close();
         }

         return result;
      }
   }

   protected void finishInvalidate() throws IllegalStateException {
      try (AutoLock l = this._lock.lock()) {
         try {
            if (LOG.isDebugEnabled()) {
               LOG.debug("invalidate {}", this._sessionData.getId());
            }

            if (this._state == Session.State.VALID || this._state == Session.State.INVALIDATING) {
               Set<String> keys = null;

               do {
                  keys = this._sessionData.getKeys();

                  for(String key : keys) {
                     Object old = this._sessionData.setAttribute(key, (Object)null);
                     if (old != null) {
                        this.callSessionAttributeListeners(key, (Object)null, old);
                     }
                  }
               } while(!keys.isEmpty());
            }
         } finally {
            this._state = Session.State.INVALID;
            this._handler.recordSessionTime(this);
            this._stateChangeCompleted.signalAll();
         }
      }

   }

   public boolean isNew() throws IllegalStateException {
      try (AutoLock l = this._lock.lock()) {
         this.checkValidForRead();
         return this._newSession;
      }
   }

   public void setIdChanged(boolean changed) {
      try (AutoLock l = this._lock.lock()) {
         this._idChanged = changed;
      }

   }

   public boolean isIdChanged() {
      try (AutoLock l = this._lock.lock()) {
         return this._idChanged;
      }
   }

   public Session getSession() {
      return this;
   }

   protected SessionData getSessionData() {
      return this._sessionData;
   }

   public void setResident(boolean resident) {
      this._resident = resident;
      if (!this._resident) {
         this._sessionInactivityTimer.destroy();
      }

   }

   public boolean isResident() {
      return this._resident;
   }

   public String toString() {
      try (AutoLock l = this._lock.lock()) {
         return String.format("%s@%x{id=%s,x=%s,req=%d,res=%b}", this.getClass().getSimpleName(), this.hashCode(), this._sessionData.getId(), this._extendedId, this._requests, this._resident);
      }
   }

   public static enum State {
      VALID,
      INVALID,
      INVALIDATING,
      CHANGING;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{VALID, INVALID, INVALIDATING, CHANGING};
      }
   }

   public static enum IdState {
      SET,
      CHANGING;

      // $FF: synthetic method
      private static IdState[] $values() {
         return new IdState[]{SET, CHANGING};
      }
   }

   public class SessionInactivityTimer {
      protected final CyclicTimeout _timer = new CyclicTimeout(Session.this.getSessionHandler().getScheduler()) {
         public void onTimeoutExpired() {
            if (Session.LOG.isDebugEnabled()) {
               Session.LOG.debug("Timer expired for session {}", Session.this.getId());
            }

            long now = System.currentTimeMillis();
            Session.this.getSessionHandler().sessionInactivityTimerExpired(Session.this, now);

            try (AutoLock l = Session.this.lock()) {
               if (Session.this.isResident() && Session.this.getRequests() <= 0L && Session.this.isValid() && !Session.this.isExpiredAt(now)) {
                  SessionInactivityTimer.this.schedule(Session.this.calculateInactivityTimeout(now));
               }
            }

         }
      };

      public void schedule(long time) {
         if (time >= 0L) {
            if (Session.LOG.isDebugEnabled()) {
               Session.LOG.debug("(Re)starting timer for session {} at {}ms", Session.this.getId(), time);
            }

            this._timer.schedule(time, TimeUnit.MILLISECONDS);
         } else if (Session.LOG.isDebugEnabled()) {
            Session.LOG.debug("Not starting timer for session {}", Session.this.getId());
         }

      }

      public void cancel() {
         this._timer.cancel();
         if (Session.LOG.isDebugEnabled()) {
            Session.LOG.debug("Cancelled timer for session {}", Session.this.getId());
         }

      }

      public void destroy() {
         this._timer.destroy();
         if (Session.LOG.isDebugEnabled()) {
            Session.LOG.debug("Destroyed timer for session {}", Session.this.getId());
         }

      }
   }
}
