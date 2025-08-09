package org.glassfish.jersey.client;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.client.ResponseProcessingException;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import org.glassfish.jersey.client.internal.routing.ClientResponseMediaTypeDeterminer;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.model.internal.RankedComparator;
import org.glassfish.jersey.model.internal.RankedComparator.Order;
import org.glassfish.jersey.process.internal.AbstractChainableStage;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.process.internal.Stage.Continuation;

class ClientFilteringStages {
   private ClientFilteringStages() {
   }

   static ChainableStage createRequestFilteringStage(InjectionManager injectionManager) {
      RankedComparator<ClientRequestFilter> comparator = new RankedComparator(Order.ASCENDING);
      Iterable<ClientRequestFilter> requestFilters = Providers.getAllProviders(injectionManager, ClientRequestFilter.class, comparator);
      return requestFilters.iterator().hasNext() ? new RequestFilteringStage(requestFilters) : null;
   }

   static ChainableStage createRequestFilteringStage(ClientRequestFilter firstFilter, InjectionManager injectionManager) {
      RankedComparator<ClientRequestFilter> comparator = new RankedComparator(Order.ASCENDING);
      Iterable<ClientRequestFilter> requestFilters = Providers.getAllProviders(injectionManager, ClientRequestFilter.class, comparator);
      if (firstFilter != null && !requestFilters.iterator().hasNext()) {
         return new RequestFilteringStage(Collections.singletonList(firstFilter));
      } else {
         return firstFilter != null && requestFilters.iterator().hasNext() ? new RequestFilteringStage(prependFilter(firstFilter, requestFilters)) : null;
      }
   }

   static ChainableStage createResponseFilteringStage(InjectionManager injectionManager) {
      RankedComparator<ClientResponseFilter> comparator = new RankedComparator(Order.DESCENDING);
      Iterable<ClientResponseFilter> responseFilters = Providers.getAllProviders(injectionManager, ClientResponseFilter.class, comparator);
      return responseFilters.iterator().hasNext() ? new ResponseFilterStage(responseFilters) : null;
   }

   private static Iterable prependFilter(final Object filter, final Iterable filters) {
      return new Iterable() {
         public Iterator iterator() {
            return new Iterator() {
               final Iterator filterIterator = filters.iterator();
               boolean wasInterceptorFilterNext = false;

               public boolean hasNext() {
                  return !this.wasInterceptorFilterNext || this.filterIterator.hasNext();
               }

               public Object next() {
                  if (this.wasInterceptorFilterNext) {
                     return this.filterIterator.next();
                  } else {
                     this.wasInterceptorFilterNext = true;
                     return filter;
                  }
               }
            };
         }
      };
   }

   private static final class RequestFilteringStage extends AbstractChainableStage {
      private final Iterable requestFilters;

      private RequestFilteringStage(Iterable requestFilters) {
         this.requestFilters = requestFilters;
      }

      public Stage.Continuation apply(ClientRequest requestContext) {
         for(ClientRequestFilter filter : this.requestFilters) {
            try {
               filter.filter(requestContext);
               Response abortResponse = requestContext.getAbortResponse();
               if (abortResponse != null) {
                  ClientResponseMediaTypeDeterminer determiner = new ClientResponseMediaTypeDeterminer(requestContext.getWorkers());
                  determiner.setResponseMediaTypeIfNotSet(abortResponse, requestContext.getConfiguration());
                  throw new AbortException(new ClientResponse(requestContext, abortResponse));
               }
            } catch (IOException ex) {
               throw new ProcessingException(ex);
            }
         }

         return Continuation.of(requestContext, this.getDefaultNext());
      }
   }

   private static class ResponseFilterStage extends AbstractChainableStage {
      private final Iterable filters;

      private ResponseFilterStage(Iterable filters) {
         this.filters = filters;
      }

      public Stage.Continuation apply(ClientResponse responseContext) {
         try {
            for(ClientResponseFilter filter : this.filters) {
               filter.filter(responseContext.getRequestContext(), responseContext);
            }
         } catch (IOException ex) {
            InboundJaxrsResponse response = new InboundJaxrsResponse(responseContext, (RequestScope)null);
            throw new ResponseProcessingException(response, ex);
         }

         return Continuation.of(responseContext, this.getDefaultNext());
      }
   }
}
