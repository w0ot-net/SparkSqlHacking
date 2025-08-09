package org.apache.logging.log4j.core.config.plugins.visitors;

import java.lang.annotation.Annotation;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.PluginVisitorStrategy;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;

public final class PluginVisitors {
   private static final Logger LOGGER = StatusLogger.getLogger();

   private PluginVisitors() {
   }

   public static PluginVisitor findVisitor(final Class annotation) {
      PluginVisitorStrategy strategy = (PluginVisitorStrategy)annotation.getAnnotation(PluginVisitorStrategy.class);
      if (strategy == null) {
         return null;
      } else {
         try {
            return (PluginVisitor)LoaderUtil.newInstanceOf(strategy.value());
         } catch (Exception e) {
            LOGGER.error("Error loading PluginVisitor [{}] for annotation [{}].", strategy.value(), annotation, e);
            return null;
         }
      }
   }
}
