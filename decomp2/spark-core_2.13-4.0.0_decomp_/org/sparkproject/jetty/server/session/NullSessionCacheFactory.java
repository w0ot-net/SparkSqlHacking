package org.sparkproject.jetty.server.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullSessionCacheFactory extends AbstractSessionCacheFactory {
   private static final Logger LOG = LoggerFactory.getLogger(NullSessionCacheFactory.class);

   public int getEvictionPolicy() {
      return 0;
   }

   public void setEvictionPolicy(int evictionPolicy) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Ignoring eviction policy setting for NullSessionCaches");
      }

   }

   public boolean isSaveOnInactiveEvict() {
      return false;
   }

   public void setSaveOnInactiveEvict(boolean saveOnInactiveEvict) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Ignoring eviction policy setting for NullSessionCaches");
      }

   }

   public boolean isInvalidateOnShutdown() {
      return false;
   }

   public void setInvalidateOnShutdown(boolean invalidateOnShutdown) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Ignoring invalidateOnShutdown setting for NullSessionCaches");
      }

   }

   public SessionCache newSessionCache(SessionHandler handler) {
      return new NullSessionCache(handler);
   }
}
