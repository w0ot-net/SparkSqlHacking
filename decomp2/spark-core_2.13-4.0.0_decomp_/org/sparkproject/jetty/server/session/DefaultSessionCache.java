package org.sparkproject.jetty.server.session;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.statistic.CounterStatistic;

@ManagedObject
public class DefaultSessionCache extends AbstractSessionCache {
   private static final Logger LOG = LoggerFactory.getLogger(DefaultSessionCache.class);
   private final ConcurrentMap _sessions;
   private final CounterStatistic _stats;

   public DefaultSessionCache(SessionHandler manager) {
      this(manager, new ConcurrentHashMap());
   }

   public DefaultSessionCache(SessionHandler manager, ConcurrentMap sessions) {
      super(manager);
      this._stats = new CounterStatistic();
      this._sessions = (ConcurrentMap)Objects.requireNonNull(sessions, "Session Map may not be null");
   }

   @ManagedAttribute(
      value = "current sessions in cache",
      readonly = true
   )
   public long getSessionsCurrent() {
      return this._stats.getCurrent();
   }

   @ManagedAttribute(
      value = "max sessions in cache",
      readonly = true
   )
   public long getSessionsMax() {
      return this._stats.getMax();
   }

   @ManagedAttribute(
      value = "total sessions in cache",
      readonly = true
   )
   public long getSessionsTotal() {
      return this._stats.getTotal();
   }

   @ManagedOperation(
      value = "reset statistics",
      impact = "ACTION"
   )
   public void resetStats() {
      this._stats.reset();
   }

   public Session doGet(String id) {
      return id == null ? null : (Session)this._sessions.get(id);
   }

   public Session doPutIfAbsent(String id, Session session) {
      Session s = (Session)this._sessions.putIfAbsent(id, session);
      if (s == null) {
         this._stats.increment();
      }

      return s;
   }

   protected Session doComputeIfAbsent(String id, Function mappingFunction) {
      return (Session)this._sessions.computeIfAbsent(id, (k) -> {
         Session s = (Session)mappingFunction.apply(k);
         if (s != null) {
            this._stats.increment();
         }

         return s;
      });
   }

   public Session doDelete(String id) {
      Session s = (Session)this._sessions.remove(id);
      if (s != null) {
         this._stats.decrement();
      }

      return s;
   }

   public void shutdown() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Shutdown sessions, invalidating = {}", this.isInvalidateOnShutdown());
      }

      int loop = 100;

      while(!this._sessions.isEmpty() && loop-- > 0) {
         for(Session session : this._sessions.values()) {
            if (this.isInvalidateOnShutdown()) {
               try {
                  session.invalidate();
               } catch (Exception e) {
                  LOG.trace("IGNORED", e);
               }
            } else {
               if (this._sessionDataStore.isPassivating()) {
                  session.willPassivate();
               }

               try {
                  this._sessionDataStore.store(session.getId(), session.getSessionData());
               } catch (Exception e) {
                  LOG.warn("Unable to store {}", session, e);
               }

               this.doDelete(session.getId());
               session.setResident(false);
            }
         }
      }

   }

   public Session newSession(HttpServletRequest request, SessionData data) {
      return new Session(this.getSessionHandler(), request, data);
   }

   public Session newSession(SessionData data) {
      return new Session(this.getSessionHandler(), data);
   }

   public boolean doReplace(String id, Session oldValue, Session newValue) {
      return this._sessions.replace(id, oldValue, newValue);
   }
}
