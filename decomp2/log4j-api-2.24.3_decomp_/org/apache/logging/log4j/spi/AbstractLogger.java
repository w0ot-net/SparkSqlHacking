package org.apache.logging.log4j.spi;

import java.io.Serializable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.internal.DefaultLogBuilder;
import org.apache.logging.log4j.message.DefaultFlowMessageFactory;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.LambdaUtil;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Supplier;

public abstract class AbstractLogger implements ExtendedLogger, LocationAwareLogger, Serializable {
   public static final Marker FLOW_MARKER = MarkerManager.getMarker("FLOW");
   public static final Marker ENTRY_MARKER;
   public static final Marker EXIT_MARKER;
   public static final Marker EXCEPTION_MARKER;
   public static final Marker THROWING_MARKER;
   public static final Marker CATCHING_MARKER;
   public static final Class DEFAULT_MESSAGE_FACTORY_CLASS;
   public static final Class DEFAULT_FLOW_MESSAGE_FACTORY_CLASS;
   private static final long serialVersionUID = 2L;
   private static final String FQCN;
   private static final String THROWING = "Throwing";
   private static final String CATCHING = "Catching";
   protected final String name;
   private final MessageFactory2 messageFactory;
   private final FlowMessageFactory flowMessageFactory;
   private static final ThreadLocal recursionDepthHolder;
   private static final ThreadLocal logBuilder;

   public AbstractLogger() {
      this((String)null, (MessageFactory)null, (FlowMessageFactory)null);
   }

   public AbstractLogger(final String name) {
      this(name, (MessageFactory)null, (FlowMessageFactory)null);
   }

   public AbstractLogger(final String name, final MessageFactory messageFactory) {
      this(name, messageFactory, (FlowMessageFactory)null);
   }

   protected AbstractLogger(final String name, final MessageFactory messageFactory, final FlowMessageFactory flowMessageFactory) {
      if (name != null) {
         this.name = name;
      } else {
         Class<? extends AbstractLogger> clazz = this.getClass();
         String canonicalName = clazz.getCanonicalName();
         this.name = canonicalName != null ? canonicalName : clazz.getName();
      }

      this.messageFactory = (MessageFactory2)(messageFactory != null ? adaptMessageFactory(messageFactory) : ParameterizedMessageFactory.INSTANCE);
      this.flowMessageFactory = flowMessageFactory != null ? flowMessageFactory : DefaultFlowMessageFactory.INSTANCE;
   }

   private static MessageFactory2 adaptMessageFactory(final MessageFactory result) {
      return (MessageFactory2)(result instanceof MessageFactory2 ? (MessageFactory2)result : new MessageFactory2Adapter(result));
   }

   public static void checkMessageFactory(final ExtendedLogger logger, final MessageFactory messageFactory) {
      String name = logger.getName();
      MessageFactory loggerMessageFactory = logger.getMessageFactory();
      if (messageFactory != null && !loggerMessageFactory.equals(messageFactory)) {
         StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with the message factory {}, which may create log events with unexpected formatting.", name, loggerMessageFactory, messageFactory);
      } else if (messageFactory == null && !loggerMessageFactory.getClass().equals(DEFAULT_MESSAGE_FACTORY_CLASS)) {
         StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with a null message factory (defaults to {}), which may create log events with unexpected formatting.", name, loggerMessageFactory, DEFAULT_MESSAGE_FACTORY_CLASS.getName());
      }

   }

   public void catching(final Level level, final Throwable throwable) {
      this.catching(FQCN, level, throwable);
   }

   protected void catching(final String fqcn, final Level level, final Throwable throwable) {
      if (this.isEnabled(level, CATCHING_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, level, CATCHING_MARKER, this.catchingMsg(throwable), throwable);
      }

   }

   public void catching(final Throwable throwable) {
      if (this.isEnabled(Level.ERROR, CATCHING_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(FQCN, Level.ERROR, CATCHING_MARKER, this.catchingMsg(throwable), throwable);
      }

   }

   protected Message catchingMsg(final Throwable throwable) {
      return this.messageFactory.newMessage("Catching");
   }

   public void debug(final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, (CharSequence)message, (Throwable)null);
   }

   public void debug(final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
   }

   public void debug(final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, message != null ? message.getThrowable() : null);
   }

   public void debug(final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
   }

   public void debug(final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, (Object)message, (Throwable)null);
   }

   public void debug(final Marker marker, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
   }

   public void debug(final Marker marker, final String message) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, (Throwable)null);
   }

   public void debug(final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, params);
   }

   public void debug(final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, throwable);
   }

   public void debug(final Message message) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void debug(final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void debug(final CharSequence message) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void debug(final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void debug(final Object message) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (Object)message, (Throwable)null);
   }

   public void debug(final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void debug(final String message) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void debug(final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (String)message, (Object[])params);
   }

   public void debug(final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void debug(final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void debug(final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void debug(final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, (Throwable)null);
   }

   public void debug(final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, paramSuppliers);
   }

   public void debug(final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, throwable);
   }

   public void debug(final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void debug(final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, (Throwable)null);
   }

   public void debug(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, throwable);
   }

   public void debug(final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void debug(final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void debug(final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void debug(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.DEBUG, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void debug(final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, (String)message, (Object)p0);
   }

   public void debug(final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void debug(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.DEBUG, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   protected EntryMessage enter(final String fqcn, final String format, final Supplier... paramSuppliers) {
      EntryMessage entryMsg = null;
      if (this.isEnabled(Level.TRACE, ENTRY_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, entryMsg = this.flowMessageFactory.newEntryMessage(format, LambdaUtil.getAll(paramSuppliers)), (Throwable)null);
      }

      return entryMsg;
   }

   /** @deprecated */
   @Deprecated
   protected EntryMessage enter(final String fqcn, final String format, final MessageSupplier... paramSuppliers) {
      EntryMessage entryMsg = null;
      if (this.isEnabled(Level.TRACE, ENTRY_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, entryMsg = this.entryMsg(format, paramSuppliers), (Throwable)null);
      }

      return entryMsg;
   }

   protected EntryMessage enter(final String fqcn, final String format, final Object... params) {
      EntryMessage entryMsg = null;
      if (this.isEnabled(Level.TRACE, ENTRY_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, entryMsg = this.flowMessageFactory.newEntryMessage(format, params), (Throwable)null);
      }

      return entryMsg;
   }

   /** @deprecated */
   @Deprecated
   protected EntryMessage enter(final String fqcn, final MessageSupplier messageSupplier) {
      EntryMessage message = null;
      if (this.isEnabled(Level.TRACE, ENTRY_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, message = this.flowMessageFactory.newEntryMessage(messageSupplier.get()), (Throwable)null);
      }

      return message;
   }

   protected EntryMessage enter(final String fqcn, final Message message) {
      EntryMessage flowMessage = null;
      if (this.isEnabled(Level.TRACE, ENTRY_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, flowMessage = this.flowMessageFactory.newEntryMessage(message), (Throwable)null);
      }

      return flowMessage;
   }

   /** @deprecated */
   @Deprecated
   public void entry() {
      this.entry(FQCN, (Object[])null);
   }

   /** @deprecated */
   @Deprecated
   public void entry(final Object... params) {
      this.entry(FQCN, params);
   }

   protected void entry(final String fqcn, final Object... params) {
      if (this.isEnabled(Level.TRACE, ENTRY_MARKER, (Object)null, (Throwable)null)) {
         if (params == null) {
            this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, this.entryMsg((String)null, (Supplier[])((Supplier[])null)), (Throwable)null);
         } else {
            this.logMessageSafely(fqcn, Level.TRACE, ENTRY_MARKER, this.entryMsg((String)null, (Object[])params), (Throwable)null);
         }
      }

   }

   protected EntryMessage entryMsg(final String format, final Object... params) {
      return this.flowMessageFactory.newEntryMessage(format, params);
   }

   protected EntryMessage entryMsg(final String format, final MessageSupplier... paramSuppliers) {
      int count = paramSuppliers == null ? 0 : paramSuppliers.length;
      Object[] params = new Object[count];

      for(int i = 0; i < count; ++i) {
         params[i] = paramSuppliers[i].get();
      }

      return this.entryMsg(format, params);
   }

   protected EntryMessage entryMsg(final String format, final Supplier... paramSuppliers) {
      return this.entryMsg(format, LambdaUtil.getAll(paramSuppliers));
   }

   public void error(final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, message != null ? message.getThrowable() : null);
   }

   public void error(final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
   }

   public void error(final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, (CharSequence)message, (Throwable)null);
   }

   public void error(final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
   }

   public void error(final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, (Object)message, (Throwable)null);
   }

   public void error(final Marker marker, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
   }

   public void error(final Marker marker, final String message) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, (Throwable)null);
   }

   public void error(final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, params);
   }

   public void error(final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, throwable);
   }

   public void error(final Message message) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void error(final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void error(final CharSequence message) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void error(final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void error(final Object message) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (Object)message, (Throwable)null);
   }

   public void error(final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void error(final String message) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void error(final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (String)message, (Object[])params);
   }

   public void error(final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void error(final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void error(final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void error(final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, (Throwable)null);
   }

   public void error(final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, paramSuppliers);
   }

   public void error(final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, throwable);
   }

   public void error(final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void error(final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, (Throwable)null);
   }

   public void error(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, throwable);
   }

   public void error(final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void error(final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void error(final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void error(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.ERROR, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void error(final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, (String)message, (Object)p0);
   }

   public void error(final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void error(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.ERROR, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   /** @deprecated */
   @Deprecated
   public void exit() {
      this.exit(FQCN, (Object)null);
   }

   /** @deprecated */
   @Deprecated
   public Object exit(final Object result) {
      return this.exit(FQCN, result);
   }

   protected Object exit(final String fqcn, final Object result) {
      if (this.isEnabled(Level.TRACE, EXIT_MARKER, (CharSequence)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage((String)null, (Object)result), (Throwable)null);
      }

      return result;
   }

   protected Object exit(final String fqcn, final String format, final Object result) {
      if (this.isEnabled(Level.TRACE, EXIT_MARKER, (CharSequence)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(format, result), (Throwable)null);
      }

      return result;
   }

   protected Message exitMsg(final String format, final Object result) {
      return this.flowMessageFactory.newExitMessage(format, result);
   }

   public void fatal(final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, message != null ? message.getThrowable() : null);
   }

   public void fatal(final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
   }

   public void fatal(final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, (CharSequence)message, (Throwable)null);
   }

   public void fatal(final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
   }

   public void fatal(final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, (Object)message, (Throwable)null);
   }

   public void fatal(final Marker marker, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
   }

   public void fatal(final Marker marker, final String message) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, (Throwable)null);
   }

   public void fatal(final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, params);
   }

   public void fatal(final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, throwable);
   }

   public void fatal(final Message message) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void fatal(final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void fatal(final CharSequence message) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void fatal(final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void fatal(final Object message) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (Object)message, (Throwable)null);
   }

   public void fatal(final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void fatal(final String message) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void fatal(final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (String)message, (Object[])params);
   }

   public void fatal(final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void fatal(final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void fatal(final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void fatal(final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, (Throwable)null);
   }

   public void fatal(final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, paramSuppliers);
   }

   public void fatal(final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, throwable);
   }

   public void fatal(final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void fatal(final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, (Throwable)null);
   }

   public void fatal(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, throwable);
   }

   public void fatal(final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void fatal(final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void fatal(final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void fatal(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.FATAL, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void fatal(final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, (String)message, (Object)p0);
   }

   public void fatal(final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void fatal(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.FATAL, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public MessageFactory getMessageFactory() {
      return this.messageFactory;
   }

   public FlowMessageFactory getFlowMessageFactory() {
      return this.flowMessageFactory;
   }

   public String getName() {
      return this.name;
   }

   public void info(final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, message != null ? message.getThrowable() : null);
   }

   public void info(final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
   }

   public void info(final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, Level.INFO, marker, (CharSequence)message, (Throwable)null);
   }

   public void info(final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
   }

   public void info(final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, Level.INFO, marker, (Object)message, (Throwable)null);
   }

   public void info(final Marker marker, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
   }

   public void info(final Marker marker, final String message) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, (Throwable)null);
   }

   public void info(final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, params);
   }

   public void info(final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, throwable);
   }

   public void info(final Message message) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void info(final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void info(final CharSequence message) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void info(final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void info(final Object message) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (Object)message, (Throwable)null);
   }

   public void info(final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void info(final String message) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void info(final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (String)message, (Object[])params);
   }

   public void info(final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void info(final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void info(final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void info(final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, (Throwable)null);
   }

   public void info(final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, paramSuppliers);
   }

   public void info(final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, throwable);
   }

   public void info(final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void info(final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, (Throwable)null);
   }

   public void info(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, throwable);
   }

   public void info(final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void info(final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void info(final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void info(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.INFO, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void info(final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, (String)message, (Object)p0);
   }

   public void info(final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void info(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.INFO, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public boolean isDebugEnabled() {
      return this.isEnabled(Level.DEBUG, (Marker)null, (String)null);
   }

   public boolean isDebugEnabled(final Marker marker) {
      return this.isEnabled(Level.DEBUG, marker, (Object)null, (Throwable)null);
   }

   public boolean isEnabled(final Level level) {
      return this.isEnabled(level, (Marker)null, (Object)null, (Throwable)null);
   }

   public boolean isEnabled(final Level level, final Marker marker) {
      return this.isEnabled(level, marker, (Object)null, (Throwable)null);
   }

   public boolean isErrorEnabled() {
      return this.isEnabled(Level.ERROR, (Marker)null, (Object)null, (Throwable)null);
   }

   public boolean isErrorEnabled(final Marker marker) {
      return this.isEnabled(Level.ERROR, marker, (Object)null, (Throwable)null);
   }

   public boolean isFatalEnabled() {
      return this.isEnabled(Level.FATAL, (Marker)null, (Object)null, (Throwable)null);
   }

   public boolean isFatalEnabled(final Marker marker) {
      return this.isEnabled(Level.FATAL, marker, (Object)null, (Throwable)null);
   }

   public boolean isInfoEnabled() {
      return this.isEnabled(Level.INFO, (Marker)null, (Object)null, (Throwable)null);
   }

   public boolean isInfoEnabled(final Marker marker) {
      return this.isEnabled(Level.INFO, marker, (Object)null, (Throwable)null);
   }

   public boolean isTraceEnabled() {
      return this.isEnabled(Level.TRACE, (Marker)null, (Object)null, (Throwable)null);
   }

   public boolean isTraceEnabled(final Marker marker) {
      return this.isEnabled(Level.TRACE, marker, (Object)null, (Throwable)null);
   }

   public boolean isWarnEnabled() {
      return this.isEnabled(Level.WARN, (Marker)null, (Object)null, (Throwable)null);
   }

   public boolean isWarnEnabled(final Marker marker) {
      return this.isEnabled(Level.WARN, marker, (Object)null, (Throwable)null);
   }

   public void log(final Level level, final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, level, marker, message, message != null ? message.getThrowable() : null);
   }

   public void log(final Level level, final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, marker, message, throwable);
   }

   public void log(final Level level, final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, level, marker, message, (Throwable)null);
   }

   public void log(final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
      if (this.isEnabled(level, marker, message, throwable)) {
         this.logMessage(FQCN, level, marker, message, throwable);
      }

   }

   public void log(final Level level, final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, level, marker, message, (Throwable)null);
   }

   public void log(final Level level, final Marker marker, final Object message, final Throwable throwable) {
      if (this.isEnabled(level, marker, message, throwable)) {
         this.logMessage(FQCN, level, marker, message, throwable);
      }

   }

   public void log(final Level level, final Marker marker, final String message) {
      this.logIfEnabled(FQCN, level, marker, message, (Throwable)null);
   }

   public void log(final Level level, final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, level, marker, message, params);
   }

   public void log(final Level level, final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, marker, message, throwable);
   }

   public void log(final Level level, final Message message) {
      this.logIfEnabled(FQCN, level, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void log(final Level level, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void log(final Level level, final CharSequence message) {
      this.logIfEnabled(FQCN, level, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void log(final Level level, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void log(final Level level, final Object message) {
      this.logIfEnabled(FQCN, level, (Marker)null, (Object)message, (Throwable)null);
   }

   public void log(final Level level, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void log(final Level level, final String message) {
      this.logIfEnabled(FQCN, level, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void log(final Level level, final String message, final Object... params) {
      this.logIfEnabled(FQCN, level, (Marker)null, (String)message, (Object[])params);
   }

   public void log(final Level level, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void log(final Level level, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, level, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void log(final Level level, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void log(final Level level, final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, level, marker, messageSupplier, (Throwable)null);
   }

   public void log(final Level level, final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, level, marker, message, paramSuppliers);
   }

   public void log(final Level level, final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, marker, messageSupplier, throwable);
   }

   public void log(final Level level, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, level, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void log(final Level level, final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, level, marker, messageSupplier, (Throwable)null);
   }

   public void log(final Level level, final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, marker, messageSupplier, throwable);
   }

   public void log(final Level level, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, level, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void log(final Level level, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, level, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, level, marker, message, p0);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void log(final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void log(final Level level, final String message, final Object p0) {
      this.logIfEnabled(FQCN, level, (Marker)null, (String)message, (Object)p0);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void log(final Level level, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, level, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
      if (this.isEnabled(level, marker, message, throwable)) {
         this.logMessageSafely(fqcn, level, marker, message, throwable);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      if (this.isEnabled(level, marker, messageSupplier, throwable)) {
         this.logMessage(fqcn, level, marker, messageSupplier, throwable);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final Object message, final Throwable throwable) {
      if (this.isEnabled(level, marker, message, throwable)) {
         this.logMessage(fqcn, level, marker, message, throwable);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
      if (this.isEnabled(level, marker, message, throwable)) {
         this.logMessage(fqcn, level, marker, message, throwable);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      if (this.isEnabled(level, marker, messageSupplier, throwable)) {
         this.logMessage(fqcn, level, marker, messageSupplier, throwable);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message) {
      if (this.isEnabled(level, marker, message)) {
         this.logMessage(fqcn, level, marker, message);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Supplier... paramSuppliers) {
      if (this.isEnabled(level, marker, message)) {
         this.logMessage(fqcn, level, marker, message, paramSuppliers);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object... params) {
      if (this.isEnabled(level, marker, message, params)) {
         this.logMessage(fqcn, level, marker, message, params);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0) {
      if (this.isEnabled(level, marker, message, p0)) {
         this.logMessage(fqcn, level, marker, message, p0);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
      if (this.isEnabled(level, marker, message, p0, p1)) {
         this.logMessage(fqcn, level, marker, message, p0, p1);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      if (this.isEnabled(level, marker, message, p0, p1, p2)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3, p4)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      if (this.isEnabled(level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9)) {
         this.logMessage(fqcn, level, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
      }

   }

   public void logIfEnabled(final String fqcn, final Level level, final Marker marker, final String message, final Throwable throwable) {
      if (this.isEnabled(level, marker, message, throwable)) {
         this.logMessage(fqcn, level, marker, message, throwable);
      }

   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logMessageSafely(fqcn, level, marker, this.messageFactory.newMessage(message), throwable);
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final Object message, final Throwable throwable) {
      this.logMessageSafely(fqcn, level, marker, this.messageFactory.newMessage(message), throwable);
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      Message message = LambdaUtil.get(messageSupplier);
      Throwable effectiveThrowable = throwable == null && message != null ? message.getThrowable() : throwable;
      this.logMessageSafely(fqcn, level, marker, message, effectiveThrowable);
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      Message message = LambdaUtil.getMessage(messageSupplier, this.messageFactory);
      Throwable effectiveThrowable = throwable == null && message != null ? message.getThrowable() : throwable;
      this.logMessageSafely(fqcn, level, marker, message, effectiveThrowable);
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Throwable throwable) {
      this.logMessageSafely(fqcn, level, marker, this.messageFactory.newMessage(message), throwable);
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message) {
      Message msg = this.messageFactory.newMessage(message);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object... params) {
      Message msg = this.messageFactory.newMessage(message, params);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0) {
      Message msg = this.messageFactory.newMessage(message, p0);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1) {
      Message msg = this.messageFactory.newMessage(message, p0, p1);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6, p7);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      Message msg = this.messageFactory.newMessage(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   protected void logMessage(final String fqcn, final Level level, final Marker marker, final String message, final Supplier... paramSuppliers) {
      Message msg = this.messageFactory.newMessage(message, LambdaUtil.getAll(paramSuppliers));
      this.logMessageSafely(fqcn, level, marker, msg, msg.getThrowable());
   }

   public void logMessage(final Level level, final Marker marker, final String fqcn, final StackTraceElement location, final Message message, final Throwable throwable) {
      try {
         incrementRecursionDepth();
         this.log(level, marker, fqcn, location, message, throwable);
      } catch (Throwable ex) {
         this.handleLogMessageException(ex, fqcn, message);
      } finally {
         decrementRecursionDepth();
         ReusableMessageFactory.release(message);
      }

   }

   protected void log(final Level level, final Marker marker, final String fqcn, final StackTraceElement location, final Message message, final Throwable throwable) {
      this.logMessage(fqcn, level, marker, (Message)message, (Throwable)throwable);
   }

   public void printf(final Level level, final Marker marker, final String format, final Object... params) {
      if (this.isEnabled(level, marker, format, params)) {
         Message message = new StringFormattedMessage(format, params);
         this.logMessageSafely(FQCN, level, marker, message, message.getThrowable());
      }

   }

   public void printf(final Level level, final String format, final Object... params) {
      if (this.isEnabled(level, (Marker)null, format, params)) {
         Message message = new StringFormattedMessage(format, params);
         this.logMessageSafely(FQCN, level, (Marker)null, message, message.getThrowable());
      }

   }

   @PerformanceSensitive
   private void logMessageSafely(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
      try {
         this.logMessageTrackRecursion(fqcn, level, marker, message, throwable);
      } finally {
         ReusableMessageFactory.release(message);
      }

   }

   @PerformanceSensitive
   private void logMessageTrackRecursion(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable throwable) {
      try {
         incrementRecursionDepth();
         this.tryLogMessage(fqcn, this.getLocation(fqcn), level, marker, message, throwable);
      } finally {
         decrementRecursionDepth();
      }

   }

   private static int[] getRecursionDepthHolder() {
      int[] result = (int[])recursionDepthHolder.get();
      if (result == null) {
         result = new int[1];
         recursionDepthHolder.set(result);
      }

      return result;
   }

   private static void incrementRecursionDepth() {
      int var10002 = getRecursionDepthHolder()[0]++;
   }

   private static void decrementRecursionDepth() {
      int newDepth = --getRecursionDepthHolder()[0];
      if (newDepth < 0) {
         throw new IllegalStateException("Recursion depth became negative: " + newDepth);
      }
   }

   public static int getRecursionDepth() {
      return getRecursionDepthHolder()[0];
   }

   @PerformanceSensitive
   private void tryLogMessage(final String fqcn, final StackTraceElement location, final Level level, final Marker marker, final Message message, final Throwable throwable) {
      try {
         this.log(level, marker, fqcn, location, message, throwable);
      } catch (Throwable t) {
         this.handleLogMessageException(t, fqcn, message);
      }

   }

   @PerformanceSensitive
   private StackTraceElement getLocation(final String fqcn) {
      return this.requiresLocation() ? StackLocatorUtil.calcLocation(fqcn) : null;
   }

   private void handleLogMessageException(final Throwable throwable, final String fqcn, final Message message) {
      if (throwable instanceof LoggingException) {
         throw (LoggingException)throwable;
      } else {
         StatusLogger.getLogger().warn("{} caught {} logging {}: {}", fqcn, throwable.getClass().getName(), message.getClass().getSimpleName(), message.getFormat(), throwable);
      }
   }

   public Throwable throwing(final Throwable throwable) {
      return this.throwing(FQCN, Level.ERROR, throwable);
   }

   public Throwable throwing(final Level level, final Throwable throwable) {
      return this.throwing(FQCN, level, throwable);
   }

   protected Throwable throwing(final String fqcn, final Level level, final Throwable throwable) {
      if (this.isEnabled(level, THROWING_MARKER, (Object)null, (Throwable)null)) {
         this.logMessageSafely(fqcn, level, THROWING_MARKER, this.throwingMsg(throwable), throwable);
      }

      return throwable;
   }

   protected Message throwingMsg(final Throwable throwable) {
      return this.messageFactory.newMessage("Throwing");
   }

   public void trace(final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, message != null ? message.getThrowable() : null);
   }

   public void trace(final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
   }

   public void trace(final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, (CharSequence)message, (Throwable)null);
   }

   public void trace(final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
   }

   public void trace(final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, (Object)message, (Throwable)null);
   }

   public void trace(final Marker marker, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
   }

   public void trace(final Marker marker, final String message) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, (Throwable)null);
   }

   public void trace(final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, params);
   }

   public void trace(final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, throwable);
   }

   public void trace(final Message message) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void trace(final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void trace(final CharSequence message) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void trace(final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void trace(final Object message) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (Object)message, (Throwable)null);
   }

   public void trace(final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void trace(final String message) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void trace(final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (String)message, (Object[])params);
   }

   public void trace(final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void trace(final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void trace(final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void trace(final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, (Throwable)null);
   }

   public void trace(final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, paramSuppliers);
   }

   public void trace(final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, throwable);
   }

   public void trace(final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void trace(final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, (Throwable)null);
   }

   public void trace(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, throwable);
   }

   public void trace(final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void trace(final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void trace(final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void trace(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.TRACE, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void trace(final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, (String)message, (Object)p0);
   }

   public void trace(final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void trace(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.TRACE, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public EntryMessage traceEntry() {
      return this.enter(FQCN, (String)null, (Object[])((Object[])null));
   }

   public EntryMessage traceEntry(final String format, final Object... params) {
      return this.enter(FQCN, format, params);
   }

   public EntryMessage traceEntry(final Supplier... paramSuppliers) {
      return this.enter(FQCN, (String)null, (Supplier[])paramSuppliers);
   }

   public EntryMessage traceEntry(final String format, final Supplier... paramSuppliers) {
      return this.enter(FQCN, format, paramSuppliers);
   }

   public EntryMessage traceEntry(final Message message) {
      return this.enter(FQCN, message);
   }

   public void traceExit() {
      this.exit(FQCN, (String)null, (Object)null);
   }

   public Object traceExit(final Object result) {
      return this.exit(FQCN, (String)null, result);
   }

   public Object traceExit(final String format, final Object result) {
      return this.exit(FQCN, format, result);
   }

   public void traceExit(final EntryMessage message) {
      if (message != null && this.isEnabled(Level.TRACE, EXIT_MARKER, message, (Throwable)null)) {
         this.logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(message), (Throwable)null);
      }

   }

   public Object traceExit(final EntryMessage message, final Object result) {
      if (message != null && this.isEnabled(Level.TRACE, EXIT_MARKER, message, (Throwable)null)) {
         this.logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(result, message), (Throwable)null);
      }

      return result;
   }

   public Object traceExit(final Message message, final Object result) {
      if (message != null && this.isEnabled(Level.TRACE, EXIT_MARKER, message, (Throwable)null)) {
         this.logMessageSafely(FQCN, Level.TRACE, EXIT_MARKER, this.flowMessageFactory.newExitMessage(result, message), (Throwable)null);
      }

      return result;
   }

   public void warn(final Marker marker, final Message message) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, message != null ? message.getThrowable() : null);
   }

   public void warn(final Marker marker, final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
   }

   public void warn(final Marker marker, final CharSequence message) {
      this.logIfEnabled(FQCN, Level.WARN, marker, (CharSequence)message, (Throwable)null);
   }

   public void warn(final Marker marker, final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
   }

   public void warn(final Marker marker, final Object message) {
      this.logIfEnabled(FQCN, Level.WARN, marker, (Object)message, (Throwable)null);
   }

   public void warn(final Marker marker, final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
   }

   public void warn(final Marker marker, final String message) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, (Throwable)null);
   }

   public void warn(final Marker marker, final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, params);
   }

   public void warn(final Marker marker, final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, throwable);
   }

   public void warn(final Message message) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (Message)message, (Throwable)(message != null ? message.getThrowable() : null));
   }

   public void warn(final Message message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (Message)message, (Throwable)throwable);
   }

   public void warn(final CharSequence message) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (CharSequence)message, (Throwable)null);
   }

   public void warn(final CharSequence message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (CharSequence)message, (Throwable)throwable);
   }

   public void warn(final Object message) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (Object)message, (Throwable)null);
   }

   public void warn(final Object message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (Object)message, (Throwable)throwable);
   }

   public void warn(final String message) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (String)message, (Throwable)((Throwable)null));
   }

   public void warn(final String message, final Object... params) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (String)message, (Object[])params);
   }

   public void warn(final String message, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (String)message, (Throwable)throwable);
   }

   public void warn(final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (Supplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void warn(final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (Supplier)messageSupplier, (Throwable)throwable);
   }

   public void warn(final Marker marker, final Supplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, (Throwable)null);
   }

   public void warn(final Marker marker, final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, paramSuppliers);
   }

   public void warn(final Marker marker, final Supplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, throwable);
   }

   public void warn(final String message, final Supplier... paramSuppliers) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (String)message, (Supplier[])paramSuppliers);
   }

   public void warn(final Marker marker, final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, (Throwable)null);
   }

   public void warn(final Marker marker, final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, throwable);
   }

   public void warn(final MessageSupplier messageSupplier) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)((Throwable)null));
   }

   public void warn(final MessageSupplier messageSupplier, final Throwable throwable) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (MessageSupplier)messageSupplier, (Throwable)throwable);
   }

   public void warn(final Marker marker, final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void warn(final Marker marker, final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.WARN, marker, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public void warn(final String message, final Object p0) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, (String)message, (Object)p0);
   }

   public void warn(final String message, final Object p0, final Object p1) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3, p4);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3, p4, p5);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public void warn(final String message, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      this.logIfEnabled(FQCN, Level.WARN, (Marker)null, message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   protected boolean requiresLocation() {
      return false;
   }

   public LogBuilder atTrace() {
      return this.atLevel(Level.TRACE);
   }

   public LogBuilder atDebug() {
      return this.atLevel(Level.DEBUG);
   }

   public LogBuilder atInfo() {
      return this.atLevel(Level.INFO);
   }

   public LogBuilder atWarn() {
      return this.atLevel(Level.WARN);
   }

   public LogBuilder atError() {
      return this.atLevel(Level.ERROR);
   }

   public LogBuilder atFatal() {
      return this.atLevel(Level.FATAL);
   }

   public LogBuilder always() {
      return this.getLogBuilder(Level.OFF);
   }

   public LogBuilder atLevel(final Level level) {
      return this.isEnabled(level) ? this.getLogBuilder(level) : LogBuilder.NOOP;
   }

   protected LogBuilder getLogBuilder(final Level level) {
      if (Constants.ENABLE_THREADLOCALS) {
         DefaultLogBuilder builder = (DefaultLogBuilder)logBuilder.get();
         if (!builder.isInUse()) {
            return builder.reset(this, level);
         }
      }

      return new DefaultLogBuilder(this, level);
   }

   static {
      ENTRY_MARKER = MarkerManager.getMarker("ENTER").setParents(FLOW_MARKER);
      EXIT_MARKER = MarkerManager.getMarker("EXIT").setParents(FLOW_MARKER);
      EXCEPTION_MARKER = MarkerManager.getMarker("EXCEPTION");
      THROWING_MARKER = MarkerManager.getMarker("THROWING").setParents(EXCEPTION_MARKER);
      CATCHING_MARKER = MarkerManager.getMarker("CATCHING").setParents(EXCEPTION_MARKER);
      DEFAULT_MESSAGE_FACTORY_CLASS = ParameterizedMessageFactory.class;
      DEFAULT_FLOW_MESSAGE_FACTORY_CLASS = DefaultFlowMessageFactory.class;
      FQCN = AbstractLogger.class.getName();
      recursionDepthHolder = new ThreadLocal();
      logBuilder = ThreadLocal.withInitial(DefaultLogBuilder::new);
   }
}
