package org.glassfish.jersey.logging;

import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.CommonProperties;

public class LoggingFeature implements Feature {
   public static final String DEFAULT_LOGGER_NAME = LoggingFeature.class.getName();
   public static final String DEFAULT_LOGGER_LEVEL;
   public static final int DEFAULT_MAX_ENTITY_SIZE = 8192;
   public static final Verbosity DEFAULT_VERBOSITY;
   public static final String DEFAULT_SEPARATOR = "\n";
   public static final String DEFAULT_REDACT_HEADERS = "Authorization";
   private static final String LOGGER_NAME_POSTFIX = ".logger.name";
   private static final String LOGGER_LEVEL_POSTFIX = ".logger.level";
   private static final String VERBOSITY_POSTFIX = ".verbosity";
   private static final String MAX_ENTITY_POSTFIX = ".entity.maxSize";
   private static final String SEPARATOR_POSTFIX = ".separator";
   private static final String REDACT_HEADERS_POSTFIX = ".headers.redact";
   private static final String LOGGING_FEATURE_COMMON_PREFIX = "jersey.config.logging";
   public static final String LOGGING_FEATURE_LOGGER_NAME = "jersey.config.logging.logger.name";
   public static final String LOGGING_FEATURE_LOGGER_LEVEL = "jersey.config.logging.logger.level";
   public static final String LOGGING_FEATURE_VERBOSITY = "jersey.config.logging.verbosity";
   public static final String LOGGING_FEATURE_MAX_ENTITY_SIZE = "jersey.config.logging.entity.maxSize";
   public static final String LOGGING_FEATURE_SEPARATOR = "jersey.config.logging.separator";
   public static final String LOGGING_FEATURE_REDACT_HEADERS = "jersey.config.logging.headers.redact";
   private static final String LOGGING_FEATURE_SERVER_PREFIX = "jersey.config.server.logging";
   public static final String LOGGING_FEATURE_LOGGER_NAME_SERVER = "jersey.config.server.logging.logger.name";
   public static final String LOGGING_FEATURE_LOGGER_LEVEL_SERVER = "jersey.config.server.logging.logger.level";
   public static final String LOGGING_FEATURE_VERBOSITY_SERVER = "jersey.config.server.logging.verbosity";
   public static final String LOGGING_FEATURE_MAX_ENTITY_SIZE_SERVER = "jersey.config.server.logging.entity.maxSize";
   public static final String LOGGING_FEATURE_SEPARATOR_SERVER = "jersey.config.server.logging.separator";
   public static final String LOGGING_FEATURE_REDACT_HEADERS_SERVER = "jersey.config.server.logging.headers.redact";
   private static final String LOGGING_FEATURE_CLIENT_PREFIX = "jersey.config.client.logging";
   public static final String LOGGING_FEATURE_LOGGER_NAME_CLIENT = "jersey.config.client.logging.logger.name";
   public static final String LOGGING_FEATURE_LOGGER_LEVEL_CLIENT = "jersey.config.client.logging.logger.level";
   public static final String LOGGING_FEATURE_VERBOSITY_CLIENT = "jersey.config.client.logging.verbosity";
   public static final String LOGGING_FEATURE_MAX_ENTITY_SIZE_CLIENT = "jersey.config.client.logging.entity.maxSize";
   public static final String LOGGING_FEATURE_SEPARATOR_CLIENT = "jersey.config.client.logging.separator";
   public static final String LOGGING_FEATURE_REDACT_HEADERS_CLIENT = "jersey.config.client.logging.headers.redact";
   private final LoggingFeatureBuilder builder;

   public LoggingFeature() {
      this((Logger)null, (Level)null, (Verbosity)null, (Integer)null);
   }

   public LoggingFeature(Logger logger) {
      this(logger, (Level)null, (Verbosity)null, (Integer)null);
   }

   public LoggingFeature(Logger logger, Verbosity verbosity) {
      this(logger, (Level)null, verbosity, (Integer)null);
   }

   public LoggingFeature(Logger logger, Integer maxEntitySize) {
      this(logger, (Level)null, DEFAULT_VERBOSITY, maxEntitySize);
   }

   public LoggingFeature(Logger logger, Level level, Verbosity verbosity, Integer maxEntitySize) {
      this(builder().withLogger(logger).level(level).verbosity(verbosity).maxEntitySize(maxEntitySize));
   }

   public LoggingFeature(LoggingFeatureBuilder builder) {
      this.builder = builder;
   }

   public boolean configure(FeatureContext context) {
      boolean enabled = context.getConfiguration().getRuntimeType() != null;
      if (enabled) {
         context.register(this.createLoggingFilter(context, context.getConfiguration().getRuntimeType()));
      }

      return enabled;
   }

   public static LoggingFeatureBuilder builder() {
      return new LoggingFeatureBuilder();
   }

   private LoggingInterceptor createLoggingFilter(FeatureContext context, RuntimeType runtimeType) {
      LoggingFeatureBuilder loggingBuilder = configureBuilderParameters(this.builder, context, runtimeType);
      return (LoggingInterceptor)(runtimeType == RuntimeType.SERVER ? new ServerLoggingFilter(loggingBuilder) : new ClientLoggingFilter(loggingBuilder));
   }

   private static LoggingFeatureBuilder configureBuilderParameters(LoggingFeatureBuilder builder, FeatureContext context, RuntimeType runtimeType) {
      Map<String, ?> properties = context.getConfiguration().getProperties();
      String filterLoggerName = (String)CommonProperties.getValue(properties, runtimeType == RuntimeType.SERVER ? "jersey.config.server.logging.logger.name" : "jersey.config.client.logging.logger.name", CommonProperties.getValue(properties, "jersey.config.logging.logger.name", (Object)DEFAULT_LOGGER_NAME));
      String filterLevel = (String)CommonProperties.getValue(properties, runtimeType == RuntimeType.SERVER ? "jersey.config.server.logging.logger.level" : "jersey.config.client.logging.logger.level", CommonProperties.getValue(properties, "jersey.config.logging.logger.level", (Object)DEFAULT_LOGGER_LEVEL));
      String filterSeparator = (String)CommonProperties.getValue(properties, runtimeType == RuntimeType.SERVER ? "jersey.config.server.logging.separator" : "jersey.config.client.logging.separator", CommonProperties.getValue(properties, "jersey.config.logging.separator", (Object)"\n"));
      Verbosity filterVerbosity = (Verbosity)CommonProperties.getValue(properties, runtimeType == RuntimeType.SERVER ? "jersey.config.server.logging.verbosity" : "jersey.config.client.logging.verbosity", CommonProperties.getValue(properties, "jersey.config.logging.verbosity", (Object)DEFAULT_VERBOSITY));
      int filterMaxEntitySize = (Integer)CommonProperties.getValue(properties, runtimeType == RuntimeType.SERVER ? "jersey.config.server.logging.entity.maxSize" : "jersey.config.client.logging.entity.maxSize", CommonProperties.getValue(properties, "jersey.config.logging.entity.maxSize", (int)8192));
      String redactHeaders = (String)CommonProperties.getValue(properties, runtimeType == RuntimeType.SERVER ? "jersey.config.server.logging.headers.redact" : "jersey.config.client.logging.headers.redact", CommonProperties.getValue(properties, "jersey.config.logging.headers.redact", (Object)"Authorization"));
      Level loggerLevel = Level.parse(filterLevel);
      builder.filterLogger = builder.filterLogger == null ? Logger.getLogger(filterLoggerName) : builder.filterLogger;
      builder.verbosity = builder.verbosity == null ? filterVerbosity : builder.verbosity;
      builder.maxEntitySize = builder.maxEntitySize == null ? filterMaxEntitySize : builder.maxEntitySize;
      builder.level = builder.level == null ? loggerLevel : builder.level;
      builder.separator = builder.separator == null ? filterSeparator : builder.separator;
      builder.redactHeaders = (Collection)(builder.redactHeaders == null ? Arrays.asList(redactHeaders.split(";")) : builder.redactHeaders);
      return builder;
   }

   static {
      DEFAULT_LOGGER_LEVEL = Level.FINE.getName();
      DEFAULT_VERBOSITY = LoggingFeature.Verbosity.PAYLOAD_TEXT;
   }

   public static enum Verbosity {
      HEADERS_ONLY,
      PAYLOAD_TEXT,
      PAYLOAD_ANY;
   }

   public static class LoggingFeatureBuilder {
      Logger filterLogger;
      Verbosity verbosity;
      Integer maxEntitySize;
      Level level;
      String separator;
      Collection redactHeaders;

      public LoggingFeatureBuilder withLogger(Logger logger) {
         this.filterLogger = logger;
         return this;
      }

      public LoggingFeatureBuilder verbosity(Verbosity verbosity) {
         this.verbosity = verbosity;
         return this;
      }

      public LoggingFeatureBuilder maxEntitySize(Integer maxEntitySize) {
         this.maxEntitySize = maxEntitySize;
         return this;
      }

      public LoggingFeatureBuilder level(Level level) {
         this.level = level;
         return this;
      }

      public LoggingFeatureBuilder separator(String separator) {
         this.separator = separator;
         return this;
      }

      public LoggingFeatureBuilder redactHeaders(Collection redactHeaders) {
         this.redactHeaders = redactHeaders;
         return this;
      }

      public LoggingFeature build() {
         return new LoggingFeature(this);
      }
   }
}
