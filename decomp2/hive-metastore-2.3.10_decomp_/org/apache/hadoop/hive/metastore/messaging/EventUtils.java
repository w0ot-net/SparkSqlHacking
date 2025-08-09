package org.apache.hadoop.hive.metastore.messaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.NotificationEvent;
import org.apache.thrift.TException;

public class EventUtils {
   public static IMetaStoreClient.NotificationFilter getDbTblNotificationFilter(final String dbName, final String tableName) {
      return new IMetaStoreClient.NotificationFilter() {
         public boolean accept(NotificationEvent event) {
            if (event == null) {
               return false;
            } else if (dbName == null) {
               return true;
            } else {
               return dbName.equalsIgnoreCase(event.getDbName()) && (tableName == null || tableName.equalsIgnoreCase(event.getTableName()));
            }
         }
      };
   }

   public static IMetaStoreClient.NotificationFilter restrictByMessageFormat(final String messageFormat) {
      return new IMetaStoreClient.NotificationFilter() {
         public boolean accept(NotificationEvent event) {
            if (event == null) {
               return false;
            } else if (messageFormat == null) {
               return true;
            } else {
               return messageFormat.equalsIgnoreCase(event.getMessageFormat());
            }
         }
      };
   }

   public static IMetaStoreClient.NotificationFilter getEventBoundaryFilter(final Long eventFrom, final Long eventTo) {
      return new IMetaStoreClient.NotificationFilter() {
         public boolean accept(NotificationEvent event) {
            return event != null && event.getEventId() >= eventFrom && event.getEventId() <= eventTo;
         }
      };
   }

   public static IMetaStoreClient.NotificationFilter andFilter(final IMetaStoreClient.NotificationFilter... filters) {
      return new IMetaStoreClient.NotificationFilter() {
         public boolean accept(NotificationEvent event) {
            for(IMetaStoreClient.NotificationFilter filter : filters) {
               if (!filter.accept(event)) {
                  return false;
               }
            }

            return true;
         }
      };
   }

   public static class MSClientNotificationFetcher implements NotificationFetcher {
      private IMetaStoreClient msc = null;
      private Integer batchSize = null;

      public MSClientNotificationFetcher(IMetaStoreClient msc) {
         this.msc = msc;
      }

      public int getBatchSize() throws IOException {
         if (this.batchSize == null) {
            try {
               this.batchSize = Integer.parseInt(this.msc.getConfigValue(ConfVars.METASTORE_BATCH_RETRIEVE_MAX.varname, "50"));
            } catch (TException e) {
               throw new IOException(e);
            }
         }

         return this.batchSize;
      }

      public long getCurrentNotificationEventId() throws IOException {
         try {
            return this.msc.getCurrentNotificationEventId().getEventId();
         } catch (TException e) {
            throw new IOException(e);
         }
      }

      public List getNextNotificationEvents(long pos, IMetaStoreClient.NotificationFilter filter) throws IOException {
         try {
            return this.msc.getNextNotification(pos, this.getBatchSize(), filter).getEvents();
         } catch (TException e) {
            throw new IOException(e);
         }
      }
   }

   public static class NotificationEventIterator implements Iterator {
      private NotificationFetcher nfetcher;
      private IMetaStoreClient.NotificationFilter filter;
      private int maxEvents;
      private Iterator batchIter = null;
      private List batch = null;
      private long pos;
      private long maxPos;
      private int eventCount;

      public NotificationEventIterator(NotificationFetcher nfetcher, long eventFrom, int maxEvents, String dbName, String tableName) throws IOException {
         this.init(nfetcher, eventFrom, maxEvents, EventUtils.getDbTblNotificationFilter(dbName, tableName));
      }

      public NotificationEventIterator(NotificationFetcher nfetcher, long eventFrom, int maxEvents, IMetaStoreClient.NotificationFilter filter) throws IOException {
         this.init(nfetcher, eventFrom, maxEvents, filter);
      }

      private void init(NotificationFetcher nfetcher, long eventFrom, int maxEvents, IMetaStoreClient.NotificationFilter filter) throws IOException {
         this.nfetcher = nfetcher;
         this.filter = filter;
         this.pos = eventFrom;
         if (maxEvents < 1) {
            this.maxEvents = Integer.MAX_VALUE;
         } else {
            this.maxEvents = maxEvents;
         }

         this.eventCount = 0;
         this.maxPos = nfetcher.getCurrentNotificationEventId();
      }

      private void fetchNextBatch() throws IOException {
         this.batch = this.nfetcher.getNextNotificationEvents(this.pos, this.filter);

         for(int batchSize = this.nfetcher.getBatchSize(); (this.batch == null || this.batch.isEmpty()) && this.pos < this.maxPos; this.batch = this.nfetcher.getNextNotificationEvents(this.pos, this.filter)) {
            this.pos += (long)batchSize;
         }

         if (this.batch == null) {
            this.batch = new ArrayList();
         }

         this.batchIter = this.batch.iterator();
      }

      public boolean hasNext() {
         if (this.eventCount >= this.maxEvents) {
            return false;
         } else if (this.batchIter != null && this.batchIter.hasNext()) {
            return true;
         } else {
            try {
               this.fetchNextBatch();
            } catch (IOException e) {
               throw new RuntimeException(e);
            }

            return !this.batch.isEmpty();
         }
      }

      public NotificationEvent next() {
         ++this.eventCount;
         NotificationEvent ev = (NotificationEvent)this.batchIter.next();
         this.pos = ev.getEventId();
         return ev;
      }

      public void remove() {
         throw new UnsupportedOperationException("remove() not supported on NotificationEventIterator");
      }
   }

   public interface NotificationFetcher {
      int getBatchSize() throws IOException;

      long getCurrentNotificationEventId() throws IOException;

      List getNextNotificationEvents(long var1, IMetaStoreClient.NotificationFilter var3) throws IOException;
   }
}
