package org.glassfish.jersey.server;

import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.message.internal.TracingLogger;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedProvider;
import org.glassfish.jersey.model.internal.RankedComparator.Order;
import org.glassfish.jersey.process.internal.AbstractChainableStage;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.process.internal.Stages;
import org.glassfish.jersey.process.internal.Stage.Continuation;
import org.glassfish.jersey.server.internal.ServerTraceEvent;
import org.glassfish.jersey.server.internal.process.Endpoint;
import org.glassfish.jersey.server.internal.process.MappableException;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;
import org.glassfish.jersey.server.monitoring.RequestEvent;

class ContainerFilteringStage extends AbstractChainableStage {
   private final Iterable requestFilters;
   private final Iterable responseFilters;

   ContainerFilteringStage(Iterable requestFilters, Iterable responseFilters) {
      this.requestFilters = requestFilters;
      this.responseFilters = responseFilters;
   }

   public Stage.Continuation apply(RequestProcessingContext context) {
      boolean postMatching = this.responseFilters == null;
      ContainerRequest request = context.request();
      TracingLogger tracingLogger = TracingLogger.getInstance(request);
      Iterable<ContainerRequestFilter> sortedRequestFilters;
      if (postMatching) {
         ArrayList<Iterable<RankedProvider<ContainerRequestFilter>>> rankedProviders = new ArrayList(2);
         rankedProviders.add(this.requestFilters);
         rankedProviders.add(request.getRequestFilters());
         sortedRequestFilters = Providers.mergeAndSortRankedProviders(new RankedComparator(), rankedProviders);
         context.monitoringEventBuilder().setContainerRequestFilters(sortedRequestFilters);
         context.triggerEvent(RequestEvent.Type.REQUEST_MATCHED);
      } else {
         context.push((ChainableStage)(new ResponseFilterStage(context, this.responseFilters, tracingLogger)));
         sortedRequestFilters = Providers.sortRankedProviders(new RankedComparator(), this.requestFilters);
      }

      TracingLogger.Event summaryEvent = postMatching ? ServerTraceEvent.REQUEST_FILTER_SUMMARY : ServerTraceEvent.PRE_MATCH_SUMMARY;
      long timestamp = tracingLogger.timestamp(summaryEvent);
      int processedCount = 0;

      Stage.Continuation var16;
      try {
         TracingLogger.Event filterEvent = postMatching ? ServerTraceEvent.REQUEST_FILTER : ServerTraceEvent.PRE_MATCH;
         Iterator var11 = sortedRequestFilters.iterator();

         final Response abortResponse;
         do {
            if (!var11.hasNext()) {
               return Continuation.of(context, this.getDefaultNext());
            }

            ContainerRequestFilter filter = (ContainerRequestFilter)var11.next();
            long filterTimestamp = tracingLogger.timestamp(filterEvent);

            try {
               filter.filter(request);
            } catch (Exception exception) {
               throw new MappableException(exception);
            } finally {
               ++processedCount;
               tracingLogger.logDuration(filterEvent, filterTimestamp, new Object[]{filter});
            }

            abortResponse = request.getAbortResponse();
         } while(abortResponse == null);

         var16 = Continuation.of(context, Stages.asStage(new Endpoint() {
            public ContainerResponse apply(RequestProcessingContext requestContext) {
               return new ContainerResponse(requestContext.request(), abortResponse);
            }
         }));
      } finally {
         if (postMatching) {
            context.triggerEvent(RequestEvent.Type.REQUEST_FILTERED);
         }

         tracingLogger.logDuration(summaryEvent, timestamp, new Object[]{processedCount});
      }

      return var16;
   }

   private static class ResponseFilterStage extends AbstractChainableStage {
      private final RequestProcessingContext processingContext;
      private final Iterable filters;
      private final TracingLogger tracingLogger;

      private ResponseFilterStage(RequestProcessingContext processingContext, Iterable filters, TracingLogger tracingLogger) {
         this.processingContext = processingContext;
         this.filters = filters;
         this.tracingLogger = tracingLogger;
      }

      public Stage.Continuation apply(ContainerResponse responseContext) {
         ArrayList<Iterable<RankedProvider<ContainerResponseFilter>>> rankedProviders = new ArrayList(2);
         rankedProviders.add(this.filters);
         rankedProviders.add(responseContext.getRequestContext().getResponseFilters());
         Iterable<ContainerResponseFilter> sortedResponseFilters = Providers.mergeAndSortRankedProviders(new RankedComparator(Order.DESCENDING), rankedProviders);
         ContainerRequest request = responseContext.getRequestContext();
         this.processingContext.monitoringEventBuilder().setContainerResponseFilters(sortedResponseFilters);
         this.processingContext.triggerEvent(RequestEvent.Type.RESP_FILTERS_START);
         long timestamp = this.tracingLogger.timestamp(ServerTraceEvent.RESPONSE_FILTER_SUMMARY);
         int processedCount = 0;

         try {
            for(ContainerResponseFilter filter : sortedResponseFilters) {
               long filterTimestamp = this.tracingLogger.timestamp(ServerTraceEvent.RESPONSE_FILTER);

               try {
                  filter.filter(request, responseContext);
               } catch (Exception ex) {
                  throw new MappableException(ex);
               } finally {
                  ++processedCount;
                  this.tracingLogger.logDuration(ServerTraceEvent.RESPONSE_FILTER, filterTimestamp, new Object[]{filter});
               }
            }
         } finally {
            this.processingContext.triggerEvent(RequestEvent.Type.RESP_FILTERS_FINISHED);
            this.tracingLogger.logDuration(ServerTraceEvent.RESPONSE_FILTER_SUMMARY, timestamp, new Object[]{processedCount});
         }

         return Continuation.of(responseContext, this.getDefaultNext());
      }
   }
}
