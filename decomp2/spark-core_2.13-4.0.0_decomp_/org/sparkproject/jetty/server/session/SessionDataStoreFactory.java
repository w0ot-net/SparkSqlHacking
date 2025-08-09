package org.sparkproject.jetty.server.session;

public interface SessionDataStoreFactory {
   SessionDataStore getSessionDataStore(SessionHandler var1) throws Exception;
}
