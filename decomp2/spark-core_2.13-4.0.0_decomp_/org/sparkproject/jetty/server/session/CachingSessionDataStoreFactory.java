package org.sparkproject.jetty.server.session;

public class CachingSessionDataStoreFactory extends AbstractSessionDataStoreFactory {
   protected SessionDataStoreFactory _sessionStoreFactory;
   protected SessionDataMapFactory _mapFactory;

   public SessionDataMapFactory getMapFactory() {
      return this._mapFactory;
   }

   public void setSessionDataMapFactory(SessionDataMapFactory mapFactory) {
      this._mapFactory = mapFactory;
   }

   public void setSessionStoreFactory(SessionDataStoreFactory factory) {
      this._sessionStoreFactory = factory;
   }

   public SessionDataStore getSessionDataStore(SessionHandler handler) throws Exception {
      return new CachingSessionDataStore(this._mapFactory.getSessionDataMap(), this._sessionStoreFactory.getSessionDataStore(handler));
   }
}
