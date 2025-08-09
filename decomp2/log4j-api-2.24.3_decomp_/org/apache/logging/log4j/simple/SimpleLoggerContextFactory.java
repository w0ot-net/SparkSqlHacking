package org.apache.logging.log4j.simple;

import java.net.URI;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

public class SimpleLoggerContextFactory implements LoggerContextFactory {
   public static final SimpleLoggerContextFactory INSTANCE = new SimpleLoggerContextFactory();

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext, final boolean currentContext) {
      return SimpleLoggerContext.INSTANCE;
   }

   public LoggerContext getContext(final String fqcn, final ClassLoader loader, final Object externalContext, final boolean currentContext, final URI configLocation, final String name) {
      return SimpleLoggerContext.INSTANCE;
   }

   public void removeContext(final LoggerContext removeContext) {
   }

   public boolean isClassLoaderDependent() {
      return false;
   }
}
