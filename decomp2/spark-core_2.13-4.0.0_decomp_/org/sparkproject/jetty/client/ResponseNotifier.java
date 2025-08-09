package org.sparkproject.jetty.client;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.LongConsumer;
import java.util.function.ObjLongConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.ContentResponse;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.CountingCallback;

public class ResponseNotifier {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseNotifier.class);

   public void notifyBegin(List listeners, Response response) {
      for(Response.ResponseListener listener : listeners) {
         if (listener instanceof Response.BeginListener) {
            this.notifyBegin((Response.BeginListener)listener, response);
         }
      }

   }

   private void notifyBegin(Response.BeginListener listener, Response response) {
      try {
         listener.onBegin(response);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public boolean notifyHeader(List listeners, Response response, HttpField field) {
      boolean result = true;

      for(Response.ResponseListener listener : listeners) {
         if (listener instanceof Response.HeaderListener) {
            result &= this.notifyHeader((Response.HeaderListener)listener, response, field);
         }
      }

      return result;
   }

   private boolean notifyHeader(Response.HeaderListener listener, Response response, HttpField field) {
      try {
         return listener.onHeader(response, field);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
         return false;
      }
   }

   public void notifyHeaders(List listeners, Response response) {
      for(Response.ResponseListener listener : listeners) {
         if (listener instanceof Response.HeadersListener) {
            this.notifyHeaders((Response.HeadersListener)listener, response);
         }
      }

   }

   private void notifyHeaders(Response.HeadersListener listener, Response response) {
      try {
         listener.onHeaders(response);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyBeforeContent(Response response, ObjLongConsumer demand, List contentListeners) {
      for(Response.DemandedContentListener listener : contentListeners) {
         this.notifyBeforeContent((Response.DemandedContentListener)listener, (Response)response, (LongConsumer)((d) -> demand.accept(listener, d)));
      }

   }

   private void notifyBeforeContent(Response.DemandedContentListener listener, Response response, LongConsumer demand) {
      try {
         listener.onBeforeContent(response, demand);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyContent(Response response, ObjLongConsumer demand, ByteBuffer buffer, Callback callback, List contentListeners) {
      int count = contentListeners.size();
      if (count == 0) {
         callback.succeeded();
         demand.accept((Object)null, 1L);
      } else if (count == 1) {
         Response.DemandedContentListener listener = (Response.DemandedContentListener)contentListeners.get(0);
         this.notifyContent((Response.DemandedContentListener)listener, (Response)response, (LongConsumer)((d) -> demand.accept(listener, d)), (ByteBuffer)buffer.slice(), (Callback)callback);
      } else {
         callback = new CountingCallback(callback, count);

         for(Response.DemandedContentListener listener : contentListeners) {
            this.notifyContent((Response.DemandedContentListener)listener, (Response)response, (LongConsumer)((d) -> demand.accept(listener, d)), (ByteBuffer)buffer.slice(), (Callback)callback);
         }
      }

   }

   private void notifyContent(Response.DemandedContentListener listener, Response response, LongConsumer demand, ByteBuffer buffer, Callback callback) {
      try {
         listener.onContent(response, demand, buffer, callback);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifySuccess(List listeners, Response response) {
      for(Response.ResponseListener listener : listeners) {
         if (listener instanceof Response.SuccessListener) {
            this.notifySuccess((Response.SuccessListener)listener, response);
         }
      }

   }

   private void notifySuccess(Response.SuccessListener listener, Response response) {
      try {
         listener.onSuccess(response);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyFailure(List listeners, Response response, Throwable failure) {
      for(Response.ResponseListener listener : listeners) {
         if (listener instanceof Response.FailureListener) {
            this.notifyFailure((Response.FailureListener)listener, response, failure);
         }
      }

   }

   private void notifyFailure(Response.FailureListener listener, Response response, Throwable failure) {
      try {
         listener.onFailure(response, failure);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyComplete(List listeners, Result result) {
      for(Response.ResponseListener listener : listeners) {
         if (listener instanceof Response.CompleteListener) {
            this.notifyComplete((Response.CompleteListener)listener, result);
         }
      }

   }

   private void notifyComplete(Response.CompleteListener listener, Result result) {
      try {
         listener.onComplete(result);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void forwardSuccess(List listeners, Response response) {
      this.forwardEvents(listeners, response);
      this.notifySuccess(listeners, response);
   }

   public void forwardSuccessComplete(List listeners, Request request, Response response) {
      this.forwardSuccess(listeners, response);
      this.notifyComplete(listeners, new Result(request, response));
   }

   public void forwardFailure(List listeners, Response response, Throwable failure) {
      this.forwardEvents(listeners, response);
      this.notifyFailure(listeners, response, failure);
   }

   private void forwardEvents(List listeners, Response response) {
      this.notifyBegin(listeners, response);
      Iterator<HttpField> iterator = response.getHeaders().iterator();

      while(iterator.hasNext()) {
         HttpField field = (HttpField)iterator.next();
         if (!this.notifyHeader(listeners, response, field)) {
            iterator.remove();
         }
      }

      this.notifyHeaders(listeners, response);
      if (response instanceof ContentResponse) {
         byte[] content = ((ContentResponse)response).getContent();
         if (content != null && content.length > 0) {
            Stream var10000 = listeners.stream();
            Objects.requireNonNull(Response.DemandedContentListener.class);
            var10000 = var10000.filter(Response.DemandedContentListener.class::isInstance);
            Objects.requireNonNull(Response.DemandedContentListener.class);
            List<Response.DemandedContentListener> contentListeners = (List)var10000.map(Response.DemandedContentListener.class::cast).collect(Collectors.toList());
            ObjLongConsumer<Object> demand = (context, value) -> {
            };
            this.notifyBeforeContent(response, demand, contentListeners);
            this.notifyContent(response, demand, ByteBuffer.wrap(content), Callback.NOOP, contentListeners);
         }
      }

   }

   public void forwardFailureComplete(List listeners, Request request, Throwable requestFailure, Response response, Throwable responseFailure) {
      this.forwardFailure(listeners, response, responseFailure);
      this.notifyComplete(listeners, new Result(request, requestFailure, response, responseFailure));
   }
}
