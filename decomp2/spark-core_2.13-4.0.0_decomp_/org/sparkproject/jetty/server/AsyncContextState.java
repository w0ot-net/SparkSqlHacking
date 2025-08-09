package org.sparkproject.jetty.server;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.server.handler.ContextHandler;

public class AsyncContextState implements AsyncContext {
   private final HttpChannel _channel;
   volatile HttpChannelState _state;

   public AsyncContextState(HttpChannelState state) {
      this._state = state;
      this._channel = this._state.getHttpChannel();
   }

   public HttpChannel getHttpChannel() {
      return this._channel;
   }

   HttpChannelState state() {
      HttpChannelState state = this._state;
      if (state == null) {
         throw new IllegalStateException("AsyncContext completed and/or Request lifecycle recycled");
      } else {
         return state;
      }
   }

   public void addListener(AsyncListener listener, ServletRequest request, ServletResponse response) {
      AsyncListener wrap = new WrappedAsyncListener(listener, request, response);
      this.state().addListener(wrap);
   }

   public void addListener(AsyncListener listener) {
      this.state().addListener(listener);
   }

   public void complete() {
      this.state().complete();
   }

   public AsyncListener createListener(Class clazz) throws ServletException {
      ContextHandler contextHandler = this.state().getContextHandler();
      if (contextHandler != null) {
         return (AsyncListener)contextHandler.getServletContext().createInstance(clazz);
      } else {
         try {
            return (AsyncListener)clazz.getDeclaredConstructor().newInstance();
         } catch (Exception e) {
            throw new ServletException(e);
         }
      }
   }

   public void dispatch() {
      this.state().dispatch((ServletContext)null, (String)null);
   }

   public void dispatch(String path) {
      this.state().dispatch((ServletContext)null, path);
   }

   public void dispatch(ServletContext context, String path) {
      this.state().dispatch(context, path);
   }

   public ServletRequest getRequest() {
      return this.state().getAsyncContextEvent().getSuppliedRequest();
   }

   public ServletResponse getResponse() {
      return this.state().getAsyncContextEvent().getSuppliedResponse();
   }

   public long getTimeout() {
      return this.state().getTimeout();
   }

   public boolean hasOriginalRequestAndResponse() {
      HttpChannel channel = this.state().getHttpChannel();
      return channel.getRequest() == this.getRequest() && channel.getResponse() == this.getResponse();
   }

   public void setTimeout(long arg0) {
      this.state().setTimeout(arg0);
   }

   public void start(final Runnable task) {
      final HttpChannel channel = this.state().getHttpChannel();
      channel.execute(new Runnable() {
         public void run() {
            ContextHandler.Context context = AsyncContextState.this.state().getAsyncContextEvent().getContext();
            if (context == null) {
               task.run();
            } else {
               context.getContextHandler().handle(channel.getRequest(), task);
            }

         }
      });
   }

   public void reset() {
      this._state = null;
   }

   public HttpChannelState getHttpChannelState() {
      return this.state();
   }

   public static class WrappedAsyncListener implements AsyncListener {
      private final AsyncListener _listener;
      private final ServletRequest _request;
      private final ServletResponse _response;

      public WrappedAsyncListener(AsyncListener listener, ServletRequest request, ServletResponse response) {
         this._listener = listener;
         this._request = request;
         this._response = response;
      }

      public AsyncListener getListener() {
         return this._listener;
      }

      public void onTimeout(AsyncEvent event) throws IOException {
         this._listener.onTimeout(new AsyncEvent(event.getAsyncContext(), this._request, this._response, event.getThrowable()));
      }

      public void onStartAsync(AsyncEvent event) throws IOException {
         this._listener.onStartAsync(new AsyncEvent(event.getAsyncContext(), this._request, this._response, event.getThrowable()));
      }

      public void onError(AsyncEvent event) throws IOException {
         this._listener.onError(new AsyncEvent(event.getAsyncContext(), this._request, this._response, event.getThrowable()));
      }

      public void onComplete(AsyncEvent event) throws IOException {
         this._listener.onComplete(new AsyncEvent(event.getAsyncContext(), this._request, this._response, event.getThrowable()));
      }
   }
}
