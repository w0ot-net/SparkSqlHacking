package org.sparkproject.jetty.server.session;

public class DefaultSessionCacheFactory extends AbstractSessionCacheFactory {
   public SessionCache newSessionCache(SessionHandler handler) {
      return new DefaultSessionCache(handler);
   }
}
