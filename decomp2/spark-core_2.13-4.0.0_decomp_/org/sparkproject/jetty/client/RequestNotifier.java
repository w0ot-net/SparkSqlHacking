package org.sparkproject.jetty.client;

import java.nio.ByteBuffer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;

public class RequestNotifier {
   private static final Logger LOG = LoggerFactory.getLogger(ResponseNotifier.class);
   private final HttpClient client;

   public RequestNotifier(HttpClient client) {
      this.client = client;
   }

   public void notifyQueued(Request request) {
      List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

      for(int i = 0; i < requestListeners.size(); ++i) {
         Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
         if (listener instanceof Request.QueuedListener) {
            this.notifyQueued((Request.QueuedListener)listener, request);
         }
      }

      List<Request.Listener> listeners = this.client.getRequestListeners();

      for(int i = 0; i < listeners.size(); ++i) {
         Request.Listener listener = (Request.Listener)listeners.get(i);
         this.notifyQueued(listener, request);
      }

   }

   private void notifyQueued(Request.QueuedListener listener, Request request) {
      try {
         listener.onQueued(request);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyBegin(Request request) {
      List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

      for(int i = 0; i < requestListeners.size(); ++i) {
         Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
         if (listener instanceof Request.BeginListener) {
            this.notifyBegin((Request.BeginListener)listener, request);
         }
      }

      List<Request.Listener> listeners = this.client.getRequestListeners();

      for(int i = 0; i < listeners.size(); ++i) {
         Request.Listener listener = (Request.Listener)listeners.get(i);
         this.notifyBegin(listener, request);
      }

   }

   private void notifyBegin(Request.BeginListener listener, Request request) {
      try {
         listener.onBegin(request);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyHeaders(Request request) {
      List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

      for(int i = 0; i < requestListeners.size(); ++i) {
         Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
         if (listener instanceof Request.HeadersListener) {
            this.notifyHeaders((Request.HeadersListener)listener, request);
         }
      }

      List<Request.Listener> listeners = this.client.getRequestListeners();

      for(int i = 0; i < listeners.size(); ++i) {
         Request.Listener listener = (Request.Listener)listeners.get(i);
         this.notifyHeaders(listener, request);
      }

   }

   private void notifyHeaders(Request.HeadersListener listener, Request request) {
      try {
         listener.onHeaders(request);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyCommit(Request request) {
      List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

      for(int i = 0; i < requestListeners.size(); ++i) {
         Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
         if (listener instanceof Request.CommitListener) {
            this.notifyCommit((Request.CommitListener)listener, request);
         }
      }

      List<Request.Listener> listeners = this.client.getRequestListeners();

      for(int i = 0; i < listeners.size(); ++i) {
         Request.Listener listener = (Request.Listener)listeners.get(i);
         this.notifyCommit(listener, request);
      }

   }

   private void notifyCommit(Request.CommitListener listener, Request request) {
      try {
         listener.onCommit(request);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyContent(Request request, ByteBuffer content) {
      if (content.hasRemaining()) {
         content = content.slice();
         List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

         for(int i = 0; i < requestListeners.size(); ++i) {
            Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
            if (listener instanceof Request.ContentListener) {
               content.clear();
               this.notifyContent((Request.ContentListener)listener, request, content);
            }
         }

         List<Request.Listener> listeners = this.client.getRequestListeners();

         for(int i = 0; i < listeners.size(); ++i) {
            Request.Listener listener = (Request.Listener)listeners.get(i);
            content.clear();
            this.notifyContent(listener, request, content);
         }

      }
   }

   private void notifyContent(Request.ContentListener listener, Request request, ByteBuffer content) {
      try {
         listener.onContent(request, content);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifySuccess(Request request) {
      List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

      for(int i = 0; i < requestListeners.size(); ++i) {
         Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
         if (listener instanceof Request.SuccessListener) {
            this.notifySuccess((Request.SuccessListener)listener, request);
         }
      }

      List<Request.Listener> listeners = this.client.getRequestListeners();

      for(int i = 0; i < listeners.size(); ++i) {
         Request.Listener listener = (Request.Listener)listeners.get(i);
         this.notifySuccess(listener, request);
      }

   }

   private void notifySuccess(Request.SuccessListener listener, Request request) {
      try {
         listener.onSuccess(request);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }

   public void notifyFailure(Request request, Throwable failure) {
      List<Request.RequestListener> requestListeners = request.getRequestListeners((Class)null);

      for(int i = 0; i < requestListeners.size(); ++i) {
         Request.RequestListener listener = (Request.RequestListener)requestListeners.get(i);
         if (listener instanceof Request.FailureListener) {
            this.notifyFailure((Request.FailureListener)listener, request, failure);
         }
      }

      List<Request.Listener> listeners = this.client.getRequestListeners();

      for(int i = 0; i < listeners.size(); ++i) {
         Request.Listener listener = (Request.Listener)listeners.get(i);
         this.notifyFailure(listener, request, failure);
      }

   }

   private void notifyFailure(Request.FailureListener listener, Request request, Throwable failure) {
      try {
         listener.onFailure(request, failure);
      } catch (Throwable x) {
         LOG.info("Exception while notifying listener {}", listener, x);
      }

   }
}
