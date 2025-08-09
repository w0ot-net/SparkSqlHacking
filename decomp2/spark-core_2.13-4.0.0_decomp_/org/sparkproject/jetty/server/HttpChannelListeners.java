package org.sparkproject.jetty.server;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpChannelListeners implements HttpChannel.Listener {
   static final Logger LOG = LoggerFactory.getLogger(HttpChannel.class);
   public static HttpChannel.Listener NOOP = new HttpChannel.Listener() {
   };
   private final NotifyRequest onRequestBegin;
   private final NotifyRequest onBeforeDispatch;
   private final NotifyFailure onDispatchFailure;
   private final NotifyRequest onAfterDispatch;
   private final NotifyContent onRequestContent;
   private final NotifyRequest onRequestContentEnd;
   private final NotifyRequest onRequestTrailers;
   private final NotifyRequest onRequestEnd;
   private final NotifyFailure onRequestFailure;
   private final NotifyRequest onResponseBegin;
   private final NotifyRequest onResponseCommit;
   private final NotifyContent onResponseContent;
   private final NotifyRequest onResponseEnd;
   private final NotifyFailure onResponseFailure;
   private final NotifyRequest onComplete;

   public HttpChannelListeners(Collection listeners) {
      try {
         NotifyRequest onRequestBegin = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyRequest onBeforeDispatch = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyFailure onDispatchFailure = HttpChannelListeners.NotifyFailure.NOOP;
         NotifyRequest onAfterDispatch = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyContent onRequestContent = HttpChannelListeners.NotifyContent.NOOP;
         NotifyRequest onRequestContentEnd = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyRequest onRequestTrailers = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyRequest onRequestEnd = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyFailure onRequestFailure = HttpChannelListeners.NotifyFailure.NOOP;
         NotifyRequest onResponseBegin = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyRequest onResponseCommit = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyContent onResponseContent = HttpChannelListeners.NotifyContent.NOOP;
         NotifyRequest onResponseEnd = HttpChannelListeners.NotifyRequest.NOOP;
         NotifyFailure onResponseFailure = HttpChannelListeners.NotifyFailure.NOOP;
         NotifyRequest onComplete = HttpChannelListeners.NotifyRequest.NOOP;

         for(HttpChannel.Listener listener : listeners) {
            if (!listener.getClass().getMethod("onRequestBegin", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onRequestBegin = combine(onRequestBegin, listener::onRequestBegin);
            }

            if (!listener.getClass().getMethod("onBeforeDispatch", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onBeforeDispatch = combine(onBeforeDispatch, listener::onBeforeDispatch);
            }

            if (!listener.getClass().getMethod("onDispatchFailure", Request.class, Throwable.class).isDefault()) {
               Objects.requireNonNull(listener);
               onDispatchFailure = combine(onDispatchFailure, listener::onDispatchFailure);
            }

            if (!listener.getClass().getMethod("onAfterDispatch", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onAfterDispatch = combine(onAfterDispatch, listener::onAfterDispatch);
            }

            if (!listener.getClass().getMethod("onRequestContent", Request.class, ByteBuffer.class).isDefault()) {
               Objects.requireNonNull(listener);
               onRequestContent = combine(onRequestContent, listener::onRequestContent);
            }

            if (!listener.getClass().getMethod("onRequestContentEnd", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onRequestContentEnd = combine(onRequestContentEnd, listener::onRequestContentEnd);
            }

            if (!listener.getClass().getMethod("onRequestTrailers", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onRequestTrailers = combine(onRequestTrailers, listener::onRequestTrailers);
            }

            if (!listener.getClass().getMethod("onRequestEnd", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onRequestEnd = combine(onRequestEnd, listener::onRequestEnd);
            }

            if (!listener.getClass().getMethod("onRequestFailure", Request.class, Throwable.class).isDefault()) {
               Objects.requireNonNull(listener);
               onRequestFailure = combine(onRequestFailure, listener::onRequestFailure);
            }

            if (!listener.getClass().getMethod("onResponseBegin", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onResponseBegin = combine(onResponseBegin, listener::onResponseBegin);
            }

            if (!listener.getClass().getMethod("onResponseCommit", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onResponseCommit = combine(onResponseCommit, listener::onResponseCommit);
            }

            if (!listener.getClass().getMethod("onResponseContent", Request.class, ByteBuffer.class).isDefault()) {
               Objects.requireNonNull(listener);
               onResponseContent = combine(onResponseContent, listener::onResponseContent);
            }

            if (!listener.getClass().getMethod("onResponseEnd", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onResponseEnd = combine(onResponseEnd, listener::onResponseEnd);
            }

            if (!listener.getClass().getMethod("onResponseFailure", Request.class, Throwable.class).isDefault()) {
               Objects.requireNonNull(listener);
               onResponseFailure = combine(onResponseFailure, listener::onResponseFailure);
            }

            if (!listener.getClass().getMethod("onComplete", Request.class).isDefault()) {
               Objects.requireNonNull(listener);
               onComplete = combine(onComplete, listener::onComplete);
            }
         }

         this.onRequestBegin = onRequestBegin;
         this.onBeforeDispatch = onBeforeDispatch;
         this.onDispatchFailure = onDispatchFailure;
         this.onAfterDispatch = onAfterDispatch;
         this.onRequestContent = onRequestContent;
         this.onRequestContentEnd = onRequestContentEnd;
         this.onRequestTrailers = onRequestTrailers;
         this.onRequestEnd = onRequestEnd;
         this.onRequestFailure = onRequestFailure;
         this.onResponseBegin = onResponseBegin;
         this.onResponseCommit = onResponseCommit;
         this.onResponseContent = onResponseContent;
         this.onResponseEnd = onResponseEnd;
         this.onResponseFailure = onResponseFailure;
         this.onComplete = onComplete;
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public void onRequestBegin(Request request) {
      this.onRequestBegin.onRequest(request);
   }

   public void onBeforeDispatch(Request request) {
      this.onBeforeDispatch.onRequest(request);
   }

   public void onDispatchFailure(Request request, Throwable failure) {
      this.onDispatchFailure.onFailure(request, failure);
   }

   public void onAfterDispatch(Request request) {
      this.onAfterDispatch.onRequest(request);
   }

   public void onRequestContent(Request request, ByteBuffer content) {
      this.onRequestContent.onContent(request, content);
   }

   public void onRequestContentEnd(Request request) {
      this.onRequestContentEnd.onRequest(request);
   }

   public void onRequestTrailers(Request request) {
      this.onRequestTrailers.onRequest(request);
   }

   public void onRequestEnd(Request request) {
      this.onRequestEnd.onRequest(request);
   }

   public void onRequestFailure(Request request, Throwable failure) {
      this.onRequestFailure.onFailure(request, failure);
   }

   public void onResponseBegin(Request request) {
      this.onResponseBegin.onRequest(request);
   }

   public void onResponseCommit(Request request) {
      this.onResponseCommit.onRequest(request);
   }

   public void onResponseContent(Request request, ByteBuffer content) {
      this.onResponseContent.onContent(request, content);
   }

   public void onResponseEnd(Request request) {
      this.onResponseEnd.onRequest(request);
   }

   public void onResponseFailure(Request request, Throwable failure) {
      this.onResponseFailure.onFailure(request, failure);
   }

   public void onComplete(Request request) {
      this.onComplete.onRequest(request);
   }

   private static NotifyRequest combine(NotifyRequest first, NotifyRequest second) {
      if (first == HttpChannelListeners.NotifyRequest.NOOP) {
         return second;
      } else {
         return second == HttpChannelListeners.NotifyRequest.NOOP ? first : (request) -> {
            first.onRequest(request);
            second.onRequest(request);
         };
      }
   }

   private static NotifyFailure combine(NotifyFailure first, NotifyFailure second) {
      if (first == HttpChannelListeners.NotifyFailure.NOOP) {
         return second;
      } else {
         return second == HttpChannelListeners.NotifyFailure.NOOP ? first : (request, throwable) -> {
            first.onFailure(request, throwable);
            second.onFailure(request, throwable);
         };
      }
   }

   private static NotifyContent combine(NotifyContent first, NotifyContent second) {
      if (first == HttpChannelListeners.NotifyContent.NOOP) {
         return (request, content) -> second.onContent(request, content.slice());
      } else {
         return second == HttpChannelListeners.NotifyContent.NOOP ? (request, content) -> first.onContent(request, content.slice()) : (request, content) -> {
            content = content.slice();
            first.onContent(request, content);
            second.onContent(request, content);
         };
      }
   }

   private interface NotifyRequest {
      NotifyRequest NOOP = (request) -> {
      };

      void onRequest(Request var1);
   }

   private interface NotifyFailure {
      NotifyFailure NOOP = (request, failure) -> {
      };

      void onFailure(Request var1, Throwable var2);
   }

   private interface NotifyContent {
      NotifyContent NOOP = (request, content) -> {
      };

      void onContent(Request var1, ByteBuffer var2);
   }
}
