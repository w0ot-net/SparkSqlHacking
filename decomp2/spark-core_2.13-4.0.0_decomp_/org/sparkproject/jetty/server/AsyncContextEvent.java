package org.sparkproject.jetty.server;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.thread.Scheduler;

public class AsyncContextEvent extends AsyncEvent implements Runnable {
   private final ContextHandler.Context _context;
   private final AsyncContextState _asyncContext;
   private final HttpURI _baseURI;
   private final HttpChannelState _state;
   private ServletContext _dispatchContext;
   private String _dispatchPath;
   private volatile Scheduler.Task _timeoutTask;
   private Throwable _throwable;

   public AsyncContextEvent(ContextHandler.Context context, AsyncContextState asyncContext, HttpChannelState state, Request baseRequest, ServletRequest request, ServletResponse response) {
      this(context, asyncContext, state, baseRequest, request, response, (HttpURI)null);
   }

   public AsyncContextEvent(ContextHandler.Context context, AsyncContextState asyncContext, HttpChannelState state, Request baseRequest, ServletRequest request, ServletResponse response, HttpURI baseURI) {
      super((AsyncContext)null, request, response, (Throwable)null);
      this._context = context;
      this._asyncContext = asyncContext;
      this._state = state;
      this._baseURI = baseURI;
      baseRequest.setAsyncAttributes();
   }

   public HttpURI getBaseURI() {
      return this._baseURI;
   }

   public ServletContext getSuspendedContext() {
      return this._context;
   }

   public ContextHandler.Context getContext() {
      return this._context;
   }

   public ServletContext getDispatchContext() {
      return this._dispatchContext;
   }

   public ServletContext getServletContext() {
      return (ServletContext)(this._dispatchContext == null ? this._context : this._dispatchContext);
   }

   public void setTimeoutTask(Scheduler.Task task) {
      this._timeoutTask = task;
   }

   public boolean hasTimeoutTask() {
      return this._timeoutTask != null;
   }

   public void cancelTimeoutTask() {
      Scheduler.Task task = this._timeoutTask;
      this._timeoutTask = null;
      if (task != null) {
         task.cancel();
      }

   }

   public AsyncContext getAsyncContext() {
      return this._asyncContext;
   }

   public Throwable getThrowable() {
      return this._throwable;
   }

   public void setDispatchContext(ServletContext context) {
      this._dispatchContext = context;
   }

   public String getDispatchPath() {
      return this._dispatchPath;
   }

   public void setDispatchPath(String path) {
      this._dispatchPath = path;
   }

   public void completed() {
      this._timeoutTask = null;
      this._asyncContext.reset();
   }

   public HttpChannelState getHttpChannelState() {
      return this._state;
   }

   public void run() {
      Scheduler.Task task = this._timeoutTask;
      this._timeoutTask = null;
      if (task != null) {
         this._state.timeout();
      }

   }

   public void addThrowable(Throwable e) {
      if (this._throwable == null) {
         this._throwable = e;
      } else if (e != this._throwable) {
         this._throwable.addSuppressed(e);
      }

   }
}
