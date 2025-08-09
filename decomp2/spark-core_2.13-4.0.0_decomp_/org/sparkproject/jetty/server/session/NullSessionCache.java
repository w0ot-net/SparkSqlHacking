package org.sparkproject.jetty.server.session;

import jakarta.servlet.http.HttpServletRequest;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullSessionCache extends AbstractSessionCache {
   private static final Logger LOG = LoggerFactory.getLogger(NullSessionCache.class);

   public NullSessionCache(SessionHandler handler) {
      super(handler);
      super.setEvictionPolicy(0);
   }

   public void shutdown() {
   }

   public Session newSession(SessionData data) {
      return new Session(this.getSessionHandler(), data);
   }

   public Session newSession(HttpServletRequest request, SessionData data) {
      return new Session(this.getSessionHandler(), request, data);
   }

   public Session doGet(String id) {
      return null;
   }

   public Session doPutIfAbsent(String id, Session session) {
      return null;
   }

   public boolean doReplace(String id, Session oldValue, Session newValue) {
      return true;
   }

   public Session doDelete(String id) {
      return null;
   }

   public void setEvictionPolicy(int evictionTimeout) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Ignoring eviction setting: {}", evictionTimeout);
      }

   }

   protected Session doComputeIfAbsent(String id, Function mappingFunction) {
      return (Session)mappingFunction.apply(id);
   }
}
