package org.apache.logging.log4j.core.async;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.impl.ContextAnchor;
import org.apache.logging.log4j.core.selector.ContextSelector;

public class BasicAsyncLoggerContextSelector implements ContextSelector {
   private static final AsyncLoggerContext CONTEXT = new AsyncLoggerContext("AsyncDefault");

   public void shutdown(final String fqcn, final ClassLoader loader, final boolean currentContext, final boolean allContexts) {
      LoggerContext ctx = this.getContext(fqcn, loader, currentContext);
      if (ctx != null && ctx.isStarted()) {
         ctx.stop(50L, TimeUnit.MILLISECONDS);
      }

   }

   public boolean hasContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      LoggerContext ctx = this.getContext(fqcn, loader, currentContext);
      return ctx != null && ctx.isStarted();
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext) {
      LoggerContext ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      return (LoggerContext)(ctx != null ? ctx : CONTEXT);
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final boolean currentContext, final URI configLocation) {
      LoggerContext ctx = (LoggerContext)ContextAnchor.THREAD_CONTEXT.get();
      return (LoggerContext)(ctx != null ? ctx : CONTEXT);
   }

   public void removeContext(final LoggerContext context) {
   }

   public boolean isClassLoaderDependent() {
      return false;
   }

   public List getLoggerContexts() {
      return Collections.singletonList(CONTEXT);
   }
}
