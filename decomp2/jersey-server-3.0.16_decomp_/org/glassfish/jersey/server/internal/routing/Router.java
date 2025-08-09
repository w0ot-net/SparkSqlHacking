package org.glassfish.jersey.server.internal.routing;

import java.util.Collections;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;

interface Router {
   Continuation apply(RequestProcessingContext var1);

   public static final class Continuation {
      private final RequestProcessingContext requestProcessingContext;
      private final Iterable next;

      static Continuation of(RequestProcessingContext result) {
         return new Continuation(result, (Iterable)null);
      }

      static Continuation of(RequestProcessingContext result, Iterable next) {
         return new Continuation(result, next);
      }

      static Continuation of(RequestProcessingContext request, Router next) {
         return new Continuation(request, Collections.singletonList(next));
      }

      private Continuation(RequestProcessingContext request, Iterable next) {
         this.requestProcessingContext = request;
         this.next = (Iterable)(next == null ? Collections.emptyList() : next);
      }

      RequestProcessingContext requestContext() {
         return this.requestProcessingContext;
      }

      Iterable next() {
         return this.next;
      }
   }
}
