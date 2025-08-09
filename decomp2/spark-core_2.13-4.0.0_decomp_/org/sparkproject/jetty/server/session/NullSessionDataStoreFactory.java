package org.sparkproject.jetty.server.session;

public class NullSessionDataStoreFactory extends AbstractSessionDataStoreFactory {
   public SessionDataStore getSessionDataStore(SessionHandler handler) throws Exception {
      return new NullSessionDataStore();
   }
}
