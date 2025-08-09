package org.apache.log4j.legacy.core;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import org.apache.log4j.bridge.AppenderAdapter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.spi.LoggerContext;

public final class CategoryUtil {
   private static Logger asCore(final org.apache.logging.log4j.Logger logger) {
      return (Logger)logger;
   }

   private static Object get(final org.apache.logging.log4j.Logger logger, final Supplier run, final Object defaultValue) {
      return isCore(logger) ? run.get() : defaultValue;
   }

   public static Map getAppenders(final org.apache.logging.log4j.Logger logger) {
      return (Map)get(logger, () -> getDirectAppenders(logger), Collections.emptyMap());
   }

   private static Map getDirectAppenders(final org.apache.logging.log4j.Logger logger) {
      return (Map)getExactLoggerConfig(logger).map(LoggerConfig::getAppenders).orElse(Collections.emptyMap());
   }

   private static Optional getExactLoggerConfig(final org.apache.logging.log4j.Logger logger) {
      return Optional.of(asCore(logger).get()).filter((lc) -> logger.getName().equals(lc.getName()));
   }

   public static Iterator getFilters(final org.apache.logging.log4j.Logger logger) {
      Logger var10001 = asCore(logger);
      Objects.requireNonNull(var10001);
      return (Iterator)get(logger, var10001::getFilters, (Object)null);
   }

   public static LoggerContext getLoggerContext(final org.apache.logging.log4j.Logger logger) {
      Logger var10001 = asCore(logger);
      Objects.requireNonNull(var10001);
      return (LoggerContext)get(logger, var10001::getContext, (Object)null);
   }

   public static org.apache.logging.log4j.Logger getParent(final org.apache.logging.log4j.Logger logger) {
      Logger var10001 = asCore(logger);
      Objects.requireNonNull(var10001);
      return (org.apache.logging.log4j.Logger)get(logger, var10001::getParent, (Object)null);
   }

   public static boolean isAdditive(final org.apache.logging.log4j.Logger logger) {
      Logger var10001 = asCore(logger);
      Objects.requireNonNull(var10001);
      return (Boolean)get(logger, var10001::isAdditive, false);
   }

   private static boolean isCore(final org.apache.logging.log4j.Logger logger) {
      return logger instanceof Logger;
   }

   public static void setAdditivity(final org.apache.logging.log4j.Logger logger, final boolean additive) {
      if (isCore(logger)) {
         asCore(logger).setAdditive(additive);
      }

   }

   public static void setLevel(final org.apache.logging.log4j.Logger logger, final Level level) {
      if (isCore(logger)) {
         Configurator.setLevel(asCore(logger), level);
      }

   }

   public static Level getExplicitLevel(final org.apache.logging.log4j.Logger logger) {
      return isCore(logger) ? getExplicitLevel(asCore(logger)) : logger.getLevel();
   }

   private static Level getExplicitLevel(final Logger logger) {
      LoggerConfig config = logger.get();
      return config.getName().equals(logger.getName()) ? config.getExplicitLevel() : null;
   }

   public static void addAppender(final org.apache.logging.log4j.Logger logger, final Appender appender) {
      if (appender instanceof AppenderAdapter.Adapter) {
         appender.start();
      }

      asCore(logger).addAppender(appender);
   }

   public static void log(final org.apache.logging.log4j.Logger logger, final LogEvent event) {
      getExactLoggerConfig(logger).ifPresent((lc) -> lc.log(event));
   }

   private CategoryUtil() {
   }
}
