package io.vertx.ext.web.client.impl.cache;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.RequestOptions;
import io.vertx.ext.web.client.CachingWebClientOptions;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.impl.HttpContext;
import io.vertx.ext.web.client.spi.CacheStore;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheInterceptor implements Handler {
   private static final String IS_CACHE_DISPATCH = "cache.dispatch";
   private static final String RESPONSE_TO_REVALIDATE = "cache.response_to_revalidate";
   private static final String IS_CACHE_REVALIDATION = "cache.revalidation";
   private final CacheStore publicCacheStore;
   private final CachingWebClientOptions options;
   private final Map variationsRegistry;

   public CacheInterceptor(CacheStore store, CachingWebClientOptions options) {
      this.publicCacheStore = store;
      this.options = options;
      this.variationsRegistry = new ConcurrentHashMap();
   }

   public void handle(HttpContext context) {
      if (context.get("cache.revalidation") == Boolean.TRUE) {
         switch (context.phase()) {
            case DISPATCH_RESPONSE:
               this.processResponse(context, (CachedHttpResponse)null).onComplete((ar) -> {
               });
               break;
            default:
               context.next();
         }
      } else {
         switch (context.phase()) {
            case DISPATCH_RESPONSE:
               this.handleDispatchResponse(context);
               break;
            case CREATE_REQUEST:
               this.handleCreateRequest(context);
               break;
            default:
               context.next();
         }
      }

   }

   private void handleCreateRequest(HttpContext context) {
      RequestOptions request = context.requestOptions();
      Vary variation;
      if (this.options.getCachedMethods().contains(request.getMethod()) && (variation = this.selectVariation(request)) != null) {
         Promise<CachedHttpResponse> promise = Promise.promise();
         CacheKey key = new CacheKey(request, variation);
         if (context.privateCacheStore() != null) {
            context.privateCacheStore().get(key).onSuccess((cached) -> {
               if (cached == null) {
                  this.publicCacheStore.get(key).onComplete(promise);
               } else {
                  promise.complete(cached);
               }

            });
         } else {
            this.publicCacheStore.get(key).onComplete(promise);
         }

         promise.future().map((cached) -> this.respondFromCache(context, cached)).onComplete((ar) -> {
            if (ar.succeeded() && ((Optional)ar.result()).isPresent()) {
               context.set("cache.dispatch", true);
               context.dispatchResponse((HttpResponse)((Optional)ar.result()).get());
            } else {
               context.next();
            }

         });
      } else {
         context.next();
      }
   }

   private void handleDispatchResponse(HttpContext context) {
      if (context.get("cache.dispatch") == Boolean.TRUE) {
         context.next();
      } else {
         CachedHttpResponse responseToValidate = (CachedHttpResponse)context.get("cache.response_to_revalidate");
         if (responseToValidate != null) {
            this.processRevalidationResponse(context, responseToValidate).onComplete((ar) -> {
               if (ar.succeeded()) {
                  context.response((HttpResponse)ar.result());
               }

               context.next();
            });
         } else {
            this.processResponse(context, (CachedHttpResponse)null).onComplete((ar) -> context.next());
         }

      }
   }

   private Vary selectVariation(RequestOptions request) {
      CacheVariationsKey key = new CacheVariationsKey(request);

      for(Vary variation : (Set)this.variationsRegistry.getOrDefault(key, Collections.emptySet())) {
         if (variation.matchesRequest(request)) {
            return variation;
         }
      }

      return null;
   }

   private Future processResponse(HttpContext context, CachedHttpResponse cachedResponse) {
      HttpResponse<Buffer> response = context.response();
      if (this.options.getCachedStatusCodes().contains(response.statusCode())) {
         return this.cacheResponse(context, response).map(response);
      } else {
         return cachedResponse != null && cachedResponse.useStaleIfError() ? Future.succeededFuture(cachedResponse.rehydrate()) : Future.succeededFuture(response);
      }
   }

   private Optional respondFromCache(HttpContext context, CachedHttpResponse response) {
      if (response == null) {
         return Optional.empty();
      } else {
         HttpResponse<Buffer> result = response.rehydrate();
         result.headers().set(HttpHeaders.AGE, Long.toString(response.age()));
         if (response.getCacheControl().noCache()) {
            this.markForRevalidation(context, response);
            return Optional.empty();
         } else if (response.isFresh()) {
            return Optional.of(result);
         } else if (response.useStaleWhileRevalidate()) {
            HttpContext<Buffer> duplicate = context.duplicate();
            duplicate.set("cache.revalidation", true);
            duplicate.prepareRequest(context.request(), context.contentType(), context.body());
            return Optional.of(result);
         } else {
            this.markForRevalidation(context, response);
            return Optional.empty();
         }
      }
   }

   private void markForRevalidation(HttpContext context, CachedHttpResponse response) {
      String etag = response.getCacheControl().getEtag();
      if (etag != null) {
         context.requestOptions().putHeader(HttpHeaders.IF_NONE_MATCH, etag);
      }

      context.set("cache.response_to_revalidate", response);
   }

   private Future processRevalidationResponse(HttpContext context, CachedHttpResponse cachedResponse) {
      return context.response().statusCode() == 304 ? this.cacheResponse(context, cachedResponse.rehydrate()) : this.processResponse(context, cachedResponse);
   }

   private Future cacheResponse(HttpContext context, HttpResponse response) {
      HttpRequest<?> request = context.request();
      CacheControl cacheControl = CacheControl.parse(response.headers());
      if (!cacheControl.isCacheable()) {
         return Future.succeededFuture(response);
      } else if (cacheControl.isPrivate() && context.privateCacheStore() == null) {
         return Future.succeededFuture(response);
      } else if (cacheControl.isVarying() && !this.options.isVaryCachingEnabled()) {
         return Future.succeededFuture(response);
      } else {
         CacheVariationsKey variationsKey = new CacheVariationsKey(context.requestOptions());
         Vary variation = new Vary(request.headers(), response.headers());
         this.registerVariation(variationsKey, variation);
         CacheKey key = new CacheKey(context.requestOptions(), variation);
         CachedHttpResponse cachedResponse = CachedHttpResponse.wrap(response, cacheControl);
         return cacheControl.isPrivate() ? context.privateCacheStore().set(key, cachedResponse).map(response) : this.publicCacheStore.set(key, cachedResponse).map(response);
      }
   }

   private void registerVariation(CacheVariationsKey variationsKey, Vary variation) {
      Set<Vary> existing = (Set)this.variationsRegistry.getOrDefault(variationsKey, Collections.emptySet());
      Set<Vary> updated = new HashSet(existing);
      updated.add(variation);
      this.variationsRegistry.put(variationsKey, updated);
   }
}
