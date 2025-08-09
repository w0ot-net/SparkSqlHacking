package org.sparkproject.jetty.server.session;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;

public class CachingSessionDataStore extends ContainerLifeCycle implements SessionDataStore {
   private static final Logger LOG = LoggerFactory.getLogger(CachingSessionDataStore.class);
   protected SessionDataStore _store;
   protected SessionDataMap _cache;

   public CachingSessionDataStore(SessionDataMap cache, SessionDataStore store) {
      this._cache = cache;
      this.addBean(this._cache, true);
      this._store = store;
      this.addBean(this._store, true);
   }

   public SessionDataStore getSessionStore() {
      return this._store;
   }

   public SessionDataMap getSessionDataMap() {
      return this._cache;
   }

   public SessionData load(String id) throws Exception {
      SessionData d = null;

      try {
         d = this._cache.load(id);
      } catch (Exception e) {
         LOG.warn("Unable to load id {}", id, e);
      }

      if (d != null) {
         return d;
      } else {
         d = this._store.load(id);
         return d;
      }
   }

   public boolean delete(String id) throws Exception {
      boolean deleted = this._store.delete(id);
      this._cache.delete(id);
      return deleted;
   }

   public Set getExpired(Set candidates) {
      return this._store.getExpired(candidates);
   }

   public void store(String id, SessionData data) throws Exception {
      long lastSaved = data.getLastSaved();
      this._store.store(id, data);
      if (data.getLastSaved() != lastSaved) {
         this._cache.store(id, data);
      }

   }

   protected void doStart() throws Exception {
      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
   }

   public boolean isPassivating() {
      return this._store.isPassivating();
   }

   public boolean exists(String id) throws Exception {
      try {
         SessionData data = this._cache.load(id);
         if (data != null) {
            return true;
         }
      } catch (Exception e) {
         LOG.warn("Unable test exists on {}", id, e);
      }

      return this._store.exists(id);
   }

   public void initialize(SessionContext context) throws Exception {
      this._store.initialize(context);
      this._cache.initialize(context);
   }

   public SessionData newSessionData(String id, long created, long accessed, long lastAccessed, long maxInactiveMs) {
      return this._store.newSessionData(id, created, accessed, lastAccessed, maxInactiveMs);
   }
}
