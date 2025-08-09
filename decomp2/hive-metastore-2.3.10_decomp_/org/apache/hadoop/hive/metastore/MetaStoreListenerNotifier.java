package org.apache.hadoop.hive.metastore;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.hive.common.classification.InterfaceAudience.Private;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.events.AddIndexEvent;
import org.apache.hadoop.hive.metastore.events.AddPartitionEvent;
import org.apache.hadoop.hive.metastore.events.AlterIndexEvent;
import org.apache.hadoop.hive.metastore.events.AlterPartitionEvent;
import org.apache.hadoop.hive.metastore.events.AlterTableEvent;
import org.apache.hadoop.hive.metastore.events.CreateDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.CreateFunctionEvent;
import org.apache.hadoop.hive.metastore.events.CreateTableEvent;
import org.apache.hadoop.hive.metastore.events.DropDatabaseEvent;
import org.apache.hadoop.hive.metastore.events.DropFunctionEvent;
import org.apache.hadoop.hive.metastore.events.DropIndexEvent;
import org.apache.hadoop.hive.metastore.events.DropPartitionEvent;
import org.apache.hadoop.hive.metastore.events.DropTableEvent;
import org.apache.hadoop.hive.metastore.events.InsertEvent;
import org.apache.hadoop.hive.metastore.events.ListenerEvent;
import org.apache.hadoop.hive.metastore.messaging.EventMessage;

@Private
public class MetaStoreListenerNotifier {
   private static Map notificationEvents;

   public static Map notifyEvent(List listeners, EventMessage.EventType eventType, ListenerEvent event) throws MetaException {
      Preconditions.checkNotNull(listeners, "Listeners must not be null.");
      Preconditions.checkNotNull(event, "The event must not be null.");

      for(MetaStoreEventListener listener : listeners) {
         ((EventNotifier)notificationEvents.get(eventType)).notify(listener, event);
      }

      return event.getParameters();
   }

   public static Map notifyEvent(List listeners, EventMessage.EventType eventType, ListenerEvent event, EnvironmentContext environmentContext) throws MetaException {
      Preconditions.checkNotNull(event, "The event must not be null.");
      event.setEnvironmentContext(environmentContext);
      return notifyEvent(listeners, eventType, event);
   }

   public static Map notifyEvent(List listeners, EventMessage.EventType eventType, ListenerEvent event, EnvironmentContext environmentContext, Map parameters, RawStore ms) throws MetaException {
      Preconditions.checkNotNull(event, "The event must not be null.");
      event.putParameters(parameters);
      if (ms != null) {
         event.putParameter("HIVE_METASTORE_TRANSACTION_ACTIVE", Boolean.toString(ms.isActiveTransaction()));
      }

      return notifyEvent(listeners, eventType, event, environmentContext);
   }

   static {
      notificationEvents = Maps.newHashMap(ImmutableMap.builder().put(EventMessage.EventType.CREATE_DATABASE, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onCreateDatabase((CreateDatabaseEvent)event);
         }
      }).put(EventMessage.EventType.DROP_DATABASE, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onDropDatabase((DropDatabaseEvent)event);
         }
      }).put(EventMessage.EventType.CREATE_TABLE, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onCreateTable((CreateTableEvent)event);
         }
      }).put(EventMessage.EventType.DROP_TABLE, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onDropTable((DropTableEvent)event);
         }
      }).put(EventMessage.EventType.ADD_PARTITION, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onAddPartition((AddPartitionEvent)event);
         }
      }).put(EventMessage.EventType.DROP_PARTITION, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onDropPartition((DropPartitionEvent)event);
         }
      }).put(EventMessage.EventType.ALTER_TABLE, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onAlterTable((AlterTableEvent)event);
         }
      }).put(EventMessage.EventType.ALTER_PARTITION, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onAlterPartition((AlterPartitionEvent)event);
         }
      }).put(EventMessage.EventType.INSERT, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onInsert((InsertEvent)event);
         }
      }).put(EventMessage.EventType.CREATE_FUNCTION, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onCreateFunction((CreateFunctionEvent)event);
         }
      }).put(EventMessage.EventType.DROP_FUNCTION, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onDropFunction((DropFunctionEvent)event);
         }
      }).put(EventMessage.EventType.CREATE_INDEX, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onAddIndex((AddIndexEvent)event);
         }
      }).put(EventMessage.EventType.DROP_INDEX, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onDropIndex((DropIndexEvent)event);
         }
      }).put(EventMessage.EventType.ALTER_INDEX, new EventNotifier() {
         public void notify(MetaStoreEventListener listener, ListenerEvent event) throws MetaException {
            listener.onAlterIndex((AlterIndexEvent)event);
         }
      }).build());
   }

   private interface EventNotifier {
      void notify(MetaStoreEventListener var1, ListenerEvent var2) throws MetaException;
   }
}
