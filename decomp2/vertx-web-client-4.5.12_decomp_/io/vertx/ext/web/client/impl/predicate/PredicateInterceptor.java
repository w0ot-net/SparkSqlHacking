package io.vertx.ext.web.client.impl.predicate;

import io.vertx.core.Handler;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.impl.NoStackTraceThrowable;
import io.vertx.ext.web.client.impl.ClientPhase;
import io.vertx.ext.web.client.impl.HttpContext;
import io.vertx.ext.web.client.impl.HttpRequestImpl;
import io.vertx.ext.web.client.impl.HttpResponseImpl;
import io.vertx.ext.web.client.predicate.ErrorConverter;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import java.util.ArrayList;

public class PredicateInterceptor implements Handler {
   public void handle(HttpContext httpContext) {
      if (httpContext.phase() == ClientPhase.RECEIVE_RESPONSE) {
         HttpRequestImpl request = (HttpRequestImpl)httpContext.request();
         HttpClientResponse resp = httpContext.clientResponse();

         for(ResponsePredicate expectation : request.expectations()) {
            ResponsePredicateResultImpl predicateResult;
            try {
               predicateResult = (ResponsePredicateResultImpl)expectation.apply(this.responseCopy(resp, httpContext, (Object)null));
            } catch (Exception e) {
               httpContext.fail(e);
               return;
            }

            if (!predicateResult.succeeded()) {
               ErrorConverter errorConverter = expectation.errorConverter();
               if (!errorConverter.requiresBody()) {
                  predicateResult.setHttpResponse(this.responseCopy(resp, httpContext, (Object)null));
                  this.failOnPredicate(httpContext, errorConverter, predicateResult);
               } else {
                  resp.bodyHandler((buffer) -> {
                     predicateResult.setHttpResponse(this.responseCopy(resp, httpContext, buffer));
                     this.failOnPredicate(httpContext, errorConverter, predicateResult);
                  });
                  resp.resume();
               }

               return;
            }
         }
      }

      httpContext.next();
   }

   private HttpResponseImpl responseCopy(HttpClientResponse resp, HttpContext httpContext, Object value) {
      return new HttpResponseImpl(resp.version(), resp.statusCode(), resp.statusMessage(), MultiMap.caseInsensitiveMultiMap().addAll(resp.headers()), (MultiMap)null, new ArrayList(resp.cookies()), value, httpContext.getRedirectedLocations());
   }

   private void failOnPredicate(HttpContext ctx, ErrorConverter converter, ResponsePredicateResultImpl predicateResult) {
      Throwable result;
      try {
         result = converter.apply(predicateResult);
      } catch (Exception e) {
         result = e;
      }

      if (result != null) {
         ctx.fail(result);
      } else {
         ctx.fail(new NoStackTraceThrowable("Invalid HTTP response"));
      }

   }
}
