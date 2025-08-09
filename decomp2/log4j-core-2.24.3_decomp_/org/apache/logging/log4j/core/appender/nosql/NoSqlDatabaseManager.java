package org.apache.logging.log4j.core.appender.nosql;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.appender.db.AbstractDatabaseManager;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.core.util.Closer;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

public final class NoSqlDatabaseManager extends AbstractDatabaseManager {
   private static final NoSQLDatabaseManagerFactory FACTORY = new NoSQLDatabaseManagerFactory();
   private final NoSqlProvider provider;
   private NoSqlConnection connection;
   private final KeyValuePair[] additionalFields;

   /** @deprecated */
   @Deprecated
   public static NoSqlDatabaseManager getNoSqlDatabaseManager(final String name, final int bufferSize, final NoSqlProvider provider) {
      return (NoSqlDatabaseManager)AbstractDatabaseManager.getManager(name, new FactoryData((Configuration)null, bufferSize, provider, (KeyValuePair[])null), FACTORY);
   }

   public static NoSqlDatabaseManager getNoSqlDatabaseManager(final String name, final int bufferSize, final NoSqlProvider provider, final KeyValuePair[] additionalFields, final Configuration configuration) {
      return (NoSqlDatabaseManager)AbstractDatabaseManager.getManager(name, new FactoryData(configuration, bufferSize, provider, additionalFields), FACTORY);
   }

   private NoSqlDatabaseManager(final String name, final int bufferSize, final NoSqlProvider provider, final KeyValuePair[] additionalFields, final Configuration configuration) {
      super(name, bufferSize, (Layout)null, configuration);
      this.provider = provider;
      this.additionalFields = additionalFields;
   }

   private NoSqlObject buildMarkerEntity(final Marker marker) {
      NoSqlObject<W> entity = this.connection.createObject();
      entity.set("name", (Object)marker.getName());
      Marker[] parents = marker.getParents();
      if (parents != null) {
         NoSqlObject<W>[] parentEntities = new NoSqlObject[parents.length];

         for(int i = 0; i < parents.length; ++i) {
            parentEntities[i] = this.buildMarkerEntity(parents[i]);
         }

         entity.set("parents", parentEntities);
      }

      return entity;
   }

   protected boolean commitAndClose() {
      return true;
   }

   protected void connectAndStart() {
      try {
         this.connection = this.provider.getConnection();
      } catch (Exception e) {
         throw new AppenderLoggingException("Failed to get connection from NoSQL connection provider.", e);
      }
   }

   private NoSqlObject[] convertStackTrace(final StackTraceElement[] stackTrace) {
      NoSqlObject<W>[] stackTraceEntities = this.connection.createList(stackTrace.length);

      for(int i = 0; i < stackTrace.length; ++i) {
         stackTraceEntities[i] = this.convertStackTraceElement(stackTrace[i]);
      }

      return stackTraceEntities;
   }

   private NoSqlObject convertStackTraceElement(final StackTraceElement element) {
      NoSqlObject<W> elementEntity = this.connection.createObject();
      elementEntity.set("className", (Object)element.getClassName());
      elementEntity.set("methodName", (Object)element.getMethodName());
      elementEntity.set("fileName", (Object)element.getFileName());
      elementEntity.set("lineNumber", (Object)element.getLineNumber());
      return elementEntity;
   }

   private void setAdditionalFields(final NoSqlObject entity) {
      if (this.additionalFields != null) {
         NoSqlObject<W> object = this.connection.createObject();
         StrSubstitutor strSubstitutor = this.getStrSubstitutor();
         Stream.of(this.additionalFields).forEach((f) -> object.set(f.getKey(), (Object)(strSubstitutor != null ? strSubstitutor.replace(f.getValue()) : f.getValue())));
         entity.set("additionalFields", object);
      }

   }

   private void setFields(final LogEvent event, final NoSqlObject entity) {
      entity.set("level", (Object)event.getLevel());
      entity.set("loggerName", (Object)event.getLoggerName());
      entity.set("message", (Object)(event.getMessage() == null ? null : event.getMessage().getFormattedMessage()));
      StackTraceElement source = event.getSource();
      if (source == null) {
         entity.set("source", (Object)null);
      } else {
         entity.set("source", this.convertStackTraceElement(source));
      }

      Marker marker = event.getMarker();
      if (marker == null) {
         entity.set("marker", (Object)null);
      } else {
         entity.set("marker", this.buildMarkerEntity(marker));
      }

      entity.set("threadId", (Object)event.getThreadId());
      entity.set("threadName", (Object)event.getThreadName());
      entity.set("threadPriority", (Object)event.getThreadPriority());
      entity.set("millis", (Object)event.getTimeMillis());
      entity.set("date", (Object)(new Date(event.getTimeMillis())));
      Throwable thrown = event.getThrown();
      if (thrown == null) {
         entity.set("thrown", (Object)null);
      } else {
         NoSqlObject<W> originalExceptionEntity = this.connection.createObject();
         NoSqlObject<W> exceptionEntity = originalExceptionEntity;
         originalExceptionEntity.set("type", (Object)thrown.getClass().getName());
         originalExceptionEntity.set("message", (Object)thrown.getMessage());
         originalExceptionEntity.set("stackTrace", this.convertStackTrace(thrown.getStackTrace()));

         while(thrown.getCause() != null) {
            thrown = thrown.getCause();
            NoSqlObject<W> causingExceptionEntity = this.connection.createObject();
            causingExceptionEntity.set("type", (Object)thrown.getClass().getName());
            causingExceptionEntity.set("message", (Object)thrown.getMessage());
            causingExceptionEntity.set("stackTrace", this.convertStackTrace(thrown.getStackTrace()));
            exceptionEntity.set("cause", causingExceptionEntity);
            exceptionEntity = causingExceptionEntity;
         }

         entity.set("thrown", originalExceptionEntity);
      }

      ReadOnlyStringMap contextMap = event.getContextData();
      if (contextMap == null) {
         entity.set("contextMap", (Object)null);
      } else {
         NoSqlObject<W> contextMapEntity = this.connection.createObject();
         contextMap.forEach((key, val) -> contextMapEntity.set(key, val));
         entity.set("contextMap", contextMapEntity);
      }

      ThreadContext.ContextStack contextStack = event.getContextStack();
      if (contextStack == null) {
         entity.set("contextStack", (Object)null);
      } else {
         entity.set("contextStack", contextStack.asList().toArray());
      }

   }

   private void setFields(final MapMessage mapMessage, final NoSqlObject noSqlObject) {
      mapMessage.forEach((key, value) -> noSqlObject.set(key, value));
   }

   protected boolean shutdownInternal() {
      return Closer.closeSilently(this.connection);
   }

   protected void startupInternal() {
   }

   protected void writeInternal(final LogEvent event, final Serializable serializable) {
      if (this.isRunning() && this.connection != null && !this.connection.isClosed()) {
         NoSqlObject<W> entity = this.connection.createObject();
         if (serializable instanceof MapMessage) {
            this.setFields((MapMessage)serializable, entity);
         } else {
            this.setFields(event, entity);
         }

         this.setAdditionalFields(entity);
         this.connection.insertObject(entity);
      } else {
         throw new AppenderLoggingException("Cannot write logging event; NoSQL manager not connected to the database.");
      }
   }

   private static final class FactoryData extends AbstractDatabaseManager.AbstractFactoryData {
      private final NoSqlProvider provider;
      private final KeyValuePair[] additionalFields;

      protected FactoryData(final Configuration configuration, final int bufferSize, final NoSqlProvider provider, final KeyValuePair[] additionalFields) {
         super(configuration, bufferSize, (Layout)null);
         this.provider = (NoSqlProvider)Objects.requireNonNull(provider, "provider");
         this.additionalFields = additionalFields;
      }
   }

   private static final class NoSQLDatabaseManagerFactory implements ManagerFactory {
      private NoSQLDatabaseManagerFactory() {
      }

      public NoSqlDatabaseManager createManager(final String name, final FactoryData data) {
         Objects.requireNonNull(data, "data");
         return new NoSqlDatabaseManager(name, data.getBufferSize(), data.provider, data.additionalFields, data.getConfiguration());
      }
   }
}
