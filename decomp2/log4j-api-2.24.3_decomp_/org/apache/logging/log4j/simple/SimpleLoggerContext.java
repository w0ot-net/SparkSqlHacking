package org.apache.logging.log4j.simple;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.PrintStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.jspecify.annotations.Nullable;

public class SimpleLoggerContext implements LoggerContext {
   static final SimpleLoggerContext INSTANCE = new SimpleLoggerContext();
   protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
   protected static final String SYSTEM_PREFIX = "org.apache.logging.log4j.simplelog.";
   private static final MessageFactory DEFAULT_MESSAGE_FACTORY;
   private final PropertiesUtil props;
   private final boolean showLogName;
   private final boolean showShortName;
   private final boolean showDateTime;
   private final boolean showContextMap;
   private final String dateTimeFormat;
   private final Level defaultLevel;
   private final PrintStream stream;
   private final LoggerRegistry loggerRegistry = new LoggerRegistry();

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_OUT"},
      justification = "Opens a file retrieved from configuration (Log4j properties)"
   )
   public SimpleLoggerContext() {
      SimpleProvider.Config config = SimpleProvider.Config.INSTANCE;
      this.props = config.props;
      this.showContextMap = config.showContextMap;
      this.showLogName = config.showLogName;
      this.showShortName = config.showShortName;
      this.showDateTime = config.showDateTime;
      this.defaultLevel = config.defaultLevel;
      this.dateTimeFormat = config.dateTimeFormat;
      this.stream = config.stream;
   }

   public Object getExternalContext() {
      return null;
   }

   public ExtendedLogger getLogger(final String name) {
      return this.getLogger(name, DEFAULT_MESSAGE_FACTORY);
   }

   public ExtendedLogger getLogger(final String name, final @Nullable MessageFactory messageFactory) {
      MessageFactory effectiveMessageFactory = messageFactory != null ? messageFactory : DEFAULT_MESSAGE_FACTORY;
      ExtendedLogger oldLogger = this.loggerRegistry.getLogger(name, effectiveMessageFactory);
      if (oldLogger != null) {
         return oldLogger;
      } else {
         ExtendedLogger newLogger = this.createLogger(name, effectiveMessageFactory);
         this.loggerRegistry.putIfAbsent(name, effectiveMessageFactory, newLogger);
         return this.loggerRegistry.getLogger(name, effectiveMessageFactory);
      }
   }

   private ExtendedLogger createLogger(final String name, final @Nullable MessageFactory messageFactory) {
      return new SimpleLogger(name, this.defaultLevel, this.showLogName, this.showShortName, this.showDateTime, this.showContextMap, this.dateTimeFormat, messageFactory, this.props, this.stream);
   }

   public LoggerRegistry getLoggerRegistry() {
      return this.loggerRegistry;
   }

   public boolean hasLogger(final String name) {
      return this.loggerRegistry.hasLogger(name, DEFAULT_MESSAGE_FACTORY);
   }

   public boolean hasLogger(final String name, final Class messageFactoryClass) {
      return this.loggerRegistry.hasLogger(name, messageFactoryClass);
   }

   public boolean hasLogger(final String name, final @Nullable MessageFactory messageFactory) {
      MessageFactory effectiveMessageFactory = messageFactory != null ? messageFactory : DEFAULT_MESSAGE_FACTORY;
      return this.loggerRegistry.hasLogger(name, effectiveMessageFactory);
   }

   static {
      DEFAULT_MESSAGE_FACTORY = ParameterizedMessageFactory.INSTANCE;
   }
}
