package org.apache.logging.log4j.core.selector;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import javax.naming.NamingException;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.impl.ContextAnchor;
import org.apache.logging.log4j.core.net.JndiManager;
import org.apache.logging.log4j.status.StatusLogger;

public class JndiContextSelector implements NamedContextSelector {
   private static final LoggerContext CONTEXT = new LoggerContext("Default");
   private static final ConcurrentMap CONTEXT_MAP = new ConcurrentHashMap();
   private static final StatusLogger LOGGER = StatusLogger.getLogger();

   public JndiContextSelector() {
      if (!JndiManager.isJndiContextSelectorEnabled()) {
         throw new IllegalStateException("JNDI must be enabled by setting log4j2.enableJndiContextSelector=true");
      }
   }

   public void shutdown(final String fqcn, final ClassLoader loader, final boolean currentContext, final boolean allContexts) {
      LoggerContext ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      if (ctx == null) {
         String loggingContextName = getContextName();
         if (loggingContextName != null) {
            ctx = (LoggerContext)CONTEXT_MAP.get(loggingContextName);
         }
      }

      if (ctx != null) {
         ctx.stop(50L, TimeUnit.MILLISECONDS);
      }

   }

   public boolean hasContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      LoggerContext ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      if (ctx == null) {
         String loggingContextName = getContextName();
         if (loggingContextName == null) {
            return false;
         }

         ctx = (LoggerContext)CONTEXT_MAP.get(loggingContextName);
      }

      return ctx != null && ctx.isStarted();
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      return this.getContext(fqcn, loader, currentContext, (URI)null);
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext, final URI configLocation) {
      LoggerContext lc = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      if (lc != null) {
         return lc;
      } else {
         String loggingContextName = getContextName();
         return loggingContextName == null ? CONTEXT : this.locateContext(loggingContextName, (Object)null, configLocation);
      }
   }

   private static String getContextName() {
      String loggingContextName = null;

      try {
         JndiManager jndiManager = JndiManager.getDefaultManager();

         try {
            loggingContextName = (String)jndiManager.lookup("java:comp/env/log4j/context-name");
         } catch (Throwable var5) {
            if (jndiManager != null) {
               try {
                  jndiManager.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (jndiManager != null) {
            jndiManager.close();
         }
      } catch (NamingException ne) {
         LOGGER.error("Unable to lookup {}", "java:comp/env/log4j/context-name", ne);
      }

      return loggingContextName;
   }

   public LoggerContext locateContext(final String name, final Object externalContext, final URI configLocation) {
      if (name == null) {
         LOGGER.error("A context name is required to locate a LoggerContext");
         return null;
      } else {
         if (!CONTEXT_MAP.containsKey(name)) {
            LoggerContext ctx = new LoggerContext(name, externalContext, configLocation);
            CONTEXT_MAP.putIfAbsent(name, ctx);
         }

         return (LoggerContext)CONTEXT_MAP.get(name);
      }
   }

   public void removeContext(final LoggerContext context) {
      for(Map.Entry entry : CONTEXT_MAP.entrySet()) {
         if (((LoggerContext)entry.getValue()).equals(context)) {
            CONTEXT_MAP.remove(entry.getKey());
         }
      }

   }

   public boolean isClassLoaderDependent() {
      return false;
   }

   public LoggerContext removeContext(final String name) {
      return (LoggerContext)CONTEXT_MAP.remove(name);
   }

   public List getLoggerContexts() {
      return Collections.unmodifiableList(new ArrayList(CONTEXT_MAP.values()));
   }
}
