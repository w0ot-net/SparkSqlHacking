package org.apache.logging.log4j.core.async;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.PropertiesUtil;

public class AsyncQueueFullPolicyFactory {
   static final String PROPERTY_NAME_ASYNC_EVENT_ROUTER = "log4j2.AsyncQueueFullPolicy";
   static final String PROPERTY_VALUE_DEFAULT_ASYNC_EVENT_ROUTER = "Default";
   static final String PROPERTY_VALUE_DISCARDING_ASYNC_EVENT_ROUTER = "Discard";
   static final String PROPERTY_NAME_DISCARDING_THRESHOLD_LEVEL = "log4j2.DiscardThreshold";
   private static final Logger LOGGER = StatusLogger.getLogger();

   public static AsyncQueueFullPolicy create() {
      String router = PropertiesUtil.getProperties().getStringProperty("log4j2.AsyncQueueFullPolicy");
      if (router != null && !isRouterSelected(router, DefaultAsyncQueueFullPolicy.class, "Default")) {
         return isRouterSelected(router, DiscardingAsyncQueueFullPolicy.class, "Discard") ? createDiscardingAsyncQueueFullPolicy() : createCustomRouter(router);
      } else {
         return new DefaultAsyncQueueFullPolicy();
      }
   }

   private static boolean isRouterSelected(final String propertyValue, final Class policy, final String shortPropertyValue) {
      return propertyValue != null && (shortPropertyValue.equalsIgnoreCase(propertyValue) || policy.getName().equals(propertyValue) || policy.getSimpleName().equals(propertyValue));
   }

   private static AsyncQueueFullPolicy createCustomRouter(final String router) {
      try {
         LOGGER.debug("Creating custom AsyncQueueFullPolicy '{}'", router);
         return (AsyncQueueFullPolicy)LoaderUtil.newCheckedInstanceOf(router, AsyncQueueFullPolicy.class);
      } catch (Exception ex) {
         LOGGER.debug("Using DefaultAsyncQueueFullPolicy. Could not create custom AsyncQueueFullPolicy '{}': {}", router, ex.getMessage(), ex);
         return new DefaultAsyncQueueFullPolicy();
      }
   }

   private static AsyncQueueFullPolicy createDiscardingAsyncQueueFullPolicy() {
      PropertiesUtil util = PropertiesUtil.getProperties();
      String level = util.getStringProperty("log4j2.DiscardThreshold", Level.INFO.name());
      Level thresholdLevel = Level.toLevel(level, Level.INFO);
      LOGGER.debug("Creating custom DiscardingAsyncQueueFullPolicy(discardThreshold:{})", thresholdLevel);
      return new DiscardingAsyncQueueFullPolicy(thresholdLevel);
   }
}
