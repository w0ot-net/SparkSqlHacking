package org.glassfish.jersey.server.internal.monitoring;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.DestroyListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.glassfish.jersey.uri.UriTemplate;

@Priority(1100)
public final class MonitoringEventListener implements ApplicationEventListener {
   private static final Logger LOGGER = Logger.getLogger(MonitoringEventListener.class.getName());
   private static final int EVENT_QUEUE_SIZE = 500000;
   @Inject
   private InjectionManager injectionManager;
   private final Queue requestQueuedItems = new ArrayBlockingQueue(500000);
   private final Queue responseStatuses = new ArrayBlockingQueue(500000);
   private final Queue exceptionMapperEvents = new ArrayBlockingQueue(500000);
   private volatile MonitoringStatisticsProcessor monitoringStatisticsProcessor;
   private final AtomicBoolean processorFailed = new AtomicBoolean(false);

   public ReqEventListener onRequest(RequestEvent requestEvent) {
      switch (requestEvent.getType()) {
         case START:
            return new ReqEventListener();
         default:
            return null;
      }
   }

   public void onEvent(ApplicationEvent event) {
      ApplicationEvent.Type type = event.getType();
      switch (type) {
         case INITIALIZATION_START:
         default:
            break;
         case RELOAD_FINISHED:
         case INITIALIZATION_FINISHED:
            this.monitoringStatisticsProcessor = new MonitoringStatisticsProcessor(this.injectionManager, this);
            this.processorFailed.set(false);
            this.monitoringStatisticsProcessor.startMonitoringWorker();
            break;
         case DESTROY_FINISHED:
            if (this.monitoringStatisticsProcessor != null) {
               try {
                  this.monitoringStatisticsProcessor.shutDown();
               } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                  throw new ProcessingException(LocalizationMessages.ERROR_MONITORING_SHUTDOWN_INTERRUPTED(), e);
               }
            }

            for(DestroyListener listener : this.injectionManager.getAllInstances(DestroyListener.class)) {
               try {
                  listener.onDestroy();
               } catch (Exception e) {
                  LOGGER.log(Level.WARNING, LocalizationMessages.ERROR_MONITORING_STATISTICS_LISTENER_DESTROY(listener.getClass()), e);
               }
            }
      }

   }

   private boolean offer(Queue queue, Object event) {
      return !this.processorFailed.get() ? queue.offer(event) : true;
   }

   void processorFailed() {
      this.processorFailed.set(true);
   }

   Queue getExceptionMapperEvents() {
      return this.exceptionMapperEvents;
   }

   Queue getRequestQueuedItems() {
      return this.requestQueuedItems;
   }

   Queue getResponseStatuses() {
      return this.responseStatuses;
   }

   static class TimeStats {
      private final long duration;
      private final long startTime;

      private TimeStats(long startTime, long requestDuration) {
         this.duration = requestDuration;
         this.startTime = startTime;
      }

      long getDuration() {
         return this.duration;
      }

      long getStartTime() {
         return this.startTime;
      }
   }

   static class MethodStats extends TimeStats {
      private final ResourceMethod method;

      private MethodStats(ResourceMethod method, long startTime, long requestDuration) {
         super(startTime, requestDuration, null);
         this.method = method;
      }

      ResourceMethod getMethod() {
         return this.method;
      }
   }

   static class RequestStats {
      private final TimeStats requestStats;
      private final MethodStats methodStats;
      private final String requestUri;

      private RequestStats(TimeStats requestStats, MethodStats methodStats, String requestUri) {
         this.requestStats = requestStats;
         this.methodStats = methodStats;
         this.requestUri = requestUri;
      }

      TimeStats getRequestStats() {
         return this.requestStats;
      }

      MethodStats getMethodStats() {
         return this.methodStats;
      }

      String getRequestUri() {
         return this.requestUri;
      }
   }

   private class ReqEventListener implements RequestEventListener {
      private final long requestTimeStart = System.currentTimeMillis();
      private volatile long methodTimeStart;
      private volatile MethodStats methodStats;

      public ReqEventListener() {
      }

      public void onEvent(RequestEvent event) {
         long now = System.currentTimeMillis();
         switch (event.getType()) {
            case RESOURCE_METHOD_START:
               this.methodTimeStart = now;
               break;
            case RESOURCE_METHOD_FINISHED:
               ResourceMethod method = event.getUriInfo().getMatchedResourceMethod();
               this.methodStats = new MethodStats(method, this.methodTimeStart, now - this.methodTimeStart);
               break;
            case EXCEPTION_MAPPING_FINISHED:
               if (!MonitoringEventListener.this.offer(MonitoringEventListener.this.exceptionMapperEvents, event)) {
                  MonitoringEventListener.LOGGER.warning(LocalizationMessages.ERROR_MONITORING_QUEUE_MAPPER());
               }
               break;
            case FINISHED:
               if (event.isResponseWritten() && !MonitoringEventListener.this.offer(MonitoringEventListener.this.responseStatuses, event.getContainerResponse().getStatus())) {
                  MonitoringEventListener.LOGGER.warning(LocalizationMessages.ERROR_MONITORING_QUEUE_RESPONSE());
               }

               StringBuilder sb = new StringBuilder();

               for(UriTemplate uriTemplate : (List)event.getUriInfo().getMatchedTemplates().stream().collect(Collectors.collectingAndThen(Collectors.toList(), (uriTemplates) -> {
                  Collections.reverse(uriTemplates);
                  return uriTemplates;
               }))) {
                  sb.append(uriTemplate.getTemplate());
                  if (!uriTemplate.endsWithSlash()) {
                     sb.append("/");
                  }

                  sb.setLength(sb.length() - 1);
               }

               if (!MonitoringEventListener.this.offer(MonitoringEventListener.this.requestQueuedItems, new RequestStats(new TimeStats(this.requestTimeStart, now - this.requestTimeStart), this.methodStats, sb.toString()))) {
                  MonitoringEventListener.LOGGER.warning(LocalizationMessages.ERROR_MONITORING_QUEUE_REQUEST());
               }
         }

      }
   }
}
