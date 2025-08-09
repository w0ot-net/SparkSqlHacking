package org.sparkproject.jetty.server;

import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.UnavailableException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.io.QuietException;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Scheduler;

public class HttpChannelState {
   private static final Logger LOG = LoggerFactory.getLogger(HttpChannelState.class);
   private static final long DEFAULT_TIMEOUT = Long.getLong("org.sparkproject.jetty.server.HttpChannelState.DEFAULT_TIMEOUT", 30000L);
   private final AutoLock _lock = new AutoLock();
   private final HttpChannel _channel;
   private List _asyncListeners;
   private State _state;
   private RequestState _requestState;
   private OutputState _outputState;
   private InputState _inputState;
   private boolean _initial;
   private boolean _sendError;
   private boolean _asyncWritePossible;
   private long _timeoutMs;
   private AsyncContextEvent _event;
   private Thread _onTimeoutThread;

   protected HttpChannelState(HttpChannel channel) {
      this._state = HttpChannelState.State.IDLE;
      this._requestState = HttpChannelState.RequestState.BLOCKING;
      this._outputState = HttpChannelState.OutputState.OPEN;
      this._inputState = HttpChannelState.InputState.IDLE;
      this._initial = true;
      this._timeoutMs = DEFAULT_TIMEOUT;
      this._channel = channel;
   }

   AutoLock lock() {
      return this._lock.lock();
   }

   public State getState() {
      try (AutoLock l = this.lock()) {
         return this._state;
      }
   }

   public void addListener(AsyncListener listener) {
      try (AutoLock l = this.lock()) {
         if (this._asyncListeners == null) {
            this._asyncListeners = new ArrayList();
         }

         this._asyncListeners.add(listener);
      }

   }

   public boolean hasListener(AsyncListener listener) {
      try (AutoLock ignored = this.lock()) {
         if (this._asyncListeners == null) {
            return false;
         } else {
            for(AsyncListener l : this._asyncListeners) {
               if (l == listener) {
                  return true;
               }

               if (l instanceof AsyncContextState.WrappedAsyncListener && ((AsyncContextState.WrappedAsyncListener)l).getListener() == listener) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   public boolean isSendError() {
      try (AutoLock l = this.lock()) {
         return this._sendError;
      }
   }

   public void setTimeout(long ms) {
      try (AutoLock l = this.lock()) {
         this._timeoutMs = ms;
      }

   }

   public long getTimeout() {
      try (AutoLock l = this.lock()) {
         return this._timeoutMs;
      }
   }

   public AsyncContextEvent getAsyncContextEvent() {
      try (AutoLock l = this.lock()) {
         return this._event;
      }
   }

   public String toString() {
      try (AutoLock l = this.lock()) {
         return this.toStringLocked();
      }
   }

   private String toStringLocked() {
      return String.format("%s@%x{%s}", this.getClass().getSimpleName(), this.hashCode(), this.getStatusStringLocked());
   }

   private String getStatusStringLocked() {
      return String.format("s=%s rs=%s os=%s is=%s awp=%b se=%b i=%b al=%d", this._state, this._requestState, this._outputState, this._inputState, this._asyncWritePossible, this._sendError, this._initial, this._asyncListeners == null ? 0 : this._asyncListeners.size());
   }

   public String getStatusString() {
      try (AutoLock l = this.lock()) {
         return this.getStatusStringLocked();
      }
   }

   public boolean commitResponse() {
      try (AutoLock l = this.lock()) {
         switch (this._outputState.ordinal()) {
            case 0:
               this._outputState = HttpChannelState.OutputState.COMMITTED;
               return true;
            default:
               return false;
         }
      }
   }

   public boolean partialResponse() {
      try (AutoLock l = this.lock()) {
         switch (this._outputState.ordinal()) {
            case 1:
               this._outputState = HttpChannelState.OutputState.OPEN;
               return true;
            default:
               return false;
         }
      }
   }

   public boolean completeResponse() {
      try (AutoLock l = this.lock()) {
         switch (this._outputState.ordinal()) {
            case 0:
            case 1:
               this._outputState = HttpChannelState.OutputState.COMPLETED;
               return true;
            default:
               return false;
         }
      }
   }

   public boolean isResponseCommitted() {
      try (AutoLock l = this.lock()) {
         switch (this._outputState.ordinal()) {
            case 0:
               return false;
            default:
               return true;
         }
      }
   }

   public boolean isResponseCompleted() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._outputState == HttpChannelState.OutputState.COMPLETED;
      }

      return var2;
   }

   public boolean abortResponse() {
      try (AutoLock l = this.lock()) {
         switch (this._outputState.ordinal()) {
            case 0:
               this._channel.getResponse().setStatus(500);
               this._outputState = HttpChannelState.OutputState.ABORTED;
               return true;
            case 3:
               return false;
            default:
               this._outputState = HttpChannelState.OutputState.ABORTED;
               return true;
         }
      }
   }

   public Action handling() {
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("handling {}", this.toStringLocked());
         }

         switch (this._state.ordinal()) {
            case 0:
               if (this._requestState != HttpChannelState.RequestState.BLOCKING) {
                  throw new IllegalStateException(this.getStatusStringLocked());
               } else {
                  this._initial = true;
                  this._state = HttpChannelState.State.HANDLING;
                  return HttpChannelState.Action.DISPATCH;
               }
            case 3:
               if (this._event != null && this._event.getThrowable() != null && !this._sendError) {
                  this._state = HttpChannelState.State.HANDLING;
                  return HttpChannelState.Action.ASYNC_ERROR;
               }

               Action action = this.nextAction(true);
               if (LOG.isDebugEnabled()) {
                  LOG.debug("nextAction(true) {} {}", action, this.toStringLocked());
               }

               return action;
            default:
               throw new IllegalStateException(this.getStatusStringLocked());
         }
      }
   }

   protected Action unhandle() {
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("unhandle {}", this.toStringLocked());
         }

         if (this._state != HttpChannelState.State.HANDLING) {
            throw new IllegalStateException(this.getStatusStringLocked());
         } else {
            this._initial = false;
            Action action = this.nextAction(false);
            if (LOG.isDebugEnabled()) {
               LOG.debug("nextAction(false) {} {}", action, this.toStringLocked());
            }

            return action;
         }
      }
   }

   private Action nextAction(boolean handling) {
      this._state = HttpChannelState.State.HANDLING;
      if (this._sendError) {
         switch (this._requestState.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 5:
            case 6:
               this._requestState = HttpChannelState.RequestState.BLOCKING;
               this._sendError = false;
               return HttpChannelState.Action.SEND_ERROR;
            case 3:
            case 4:
         }
      }

      switch (this._requestState.ordinal()) {
         case 0:
            if (handling) {
               throw new IllegalStateException(this.getStatusStringLocked());
            }

            this._requestState = HttpChannelState.RequestState.COMPLETING;
            return HttpChannelState.Action.COMPLETE;
         case 1:
            switch (this._inputState.ordinal()) {
               case 0:
               case 1:
                  if (this._asyncWritePossible) {
                     this._asyncWritePossible = false;
                     return HttpChannelState.Action.WRITE_CALLBACK;
                  }

                  Scheduler scheduler = this._channel.getScheduler();
                  if (scheduler != null && this._timeoutMs > 0L && !this._event.hasTimeoutTask()) {
                     this._event.setTimeoutTask(scheduler.schedule(this._event, this._timeoutMs, TimeUnit.MILLISECONDS));
                  }

                  this._state = HttpChannelState.State.WAITING;
                  return HttpChannelState.Action.WAIT;
               case 2:
                  this._inputState = HttpChannelState.InputState.IDLE;
                  return HttpChannelState.Action.READ_CALLBACK;
               default:
                  throw new IllegalStateException(this.getStatusStringLocked());
            }
         case 2:
            this._requestState = HttpChannelState.RequestState.BLOCKING;
            return HttpChannelState.Action.ASYNC_DISPATCH;
         case 3:
            this._requestState = HttpChannelState.RequestState.EXPIRING;
            return HttpChannelState.Action.ASYNC_TIMEOUT;
         case 4:
            if (handling) {
               throw new IllegalStateException(this.getStatusStringLocked());
            }

            this.sendError(500, "AsyncContext timeout");
            this._requestState = HttpChannelState.RequestState.BLOCKING;
            this._sendError = false;
            return HttpChannelState.Action.SEND_ERROR;
         case 5:
            this._requestState = HttpChannelState.RequestState.COMPLETING;
            return HttpChannelState.Action.COMPLETE;
         case 6:
            this._state = HttpChannelState.State.WAITING;
            return HttpChannelState.Action.WAIT;
         case 7:
            this._state = HttpChannelState.State.IDLE;
            return HttpChannelState.Action.TERMINATED;
         default:
            throw new IllegalStateException(this.getStatusStringLocked());
      }
   }

   public void startAsync(final AsyncContextEvent event) {
      final List<AsyncListener> lastAsyncListeners;
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("startAsync {}", this.toStringLocked());
         }

         if (this._state != HttpChannelState.State.HANDLING || this._requestState != HttpChannelState.RequestState.BLOCKING) {
            throw new IllegalStateException(this.getStatusStringLocked());
         }

         this._requestState = HttpChannelState.RequestState.ASYNC;
         this._event = event;
         lastAsyncListeners = this._asyncListeners;
         this._asyncListeners = null;
      }

      if (lastAsyncListeners != null) {
         Runnable callback = new Runnable() {
            public void run() {
               for(AsyncListener listener : lastAsyncListeners) {
                  try {
                     listener.onStartAsync(event);
                  } catch (Throwable e) {
                     HttpChannelState.LOG.warn("Async dispatch error", e);
                  }
               }

            }

            public String toString() {
               return "startAsync";
            }
         };
         this.runInContext(event, callback);
      }

   }

   public void dispatch(ServletContext context, String path) {
      boolean dispatch = false;

      AsyncContextEvent event;
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("dispatch {} -> {}", this.toStringLocked(), path);
         }

         switch (this._requestState.ordinal()) {
            case 1:
               break;
            case 4:
               if (Thread.currentThread() != this._onTimeoutThread) {
                  throw new IllegalStateException(this.getStatusStringLocked());
               }
               break;
            default:
               throw new IllegalStateException(this.getStatusStringLocked());
         }

         if (context != null) {
            this._event.setDispatchContext(context);
         }

         if (path != null) {
            this._event.setDispatchPath(path);
         }

         if (this._requestState == HttpChannelState.RequestState.ASYNC && this._state == HttpChannelState.State.WAITING) {
            this._state = HttpChannelState.State.WOKEN;
            dispatch = true;
         }

         this._requestState = HttpChannelState.RequestState.DISPATCH;
         event = this._event;
      }

      this.cancelTimeout(event);
      if (dispatch) {
         this.scheduleDispatch();
      }

   }

   protected void timeout() {
      boolean dispatch = false;

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Timeout {}", this.toStringLocked());
         }

         if (this._requestState != HttpChannelState.RequestState.ASYNC) {
            return;
         }

         this._requestState = HttpChannelState.RequestState.EXPIRE;
         if (this._state == HttpChannelState.State.WAITING) {
            this._state = HttpChannelState.State.WOKEN;
            dispatch = true;
         }
      }

      if (dispatch) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Dispatch after async timeout {}", this);
         }

         this.scheduleDispatch();
      }

   }

   protected void onTimeout() {
      final List<AsyncListener> listeners;
      final AsyncContextEvent event;
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onTimeout {}", this.toStringLocked());
         }

         if (this._requestState != HttpChannelState.RequestState.EXPIRING || this._state != HttpChannelState.State.HANDLING) {
            throw new IllegalStateException(this.toStringLocked());
         }

         event = this._event;
         listeners = this._asyncListeners;
         this._onTimeoutThread = Thread.currentThread();
      }

      try {
         if (listeners != null) {
            Runnable task = new Runnable() {
               public void run() {
                  for(AsyncListener listener : listeners) {
                     try {
                        listener.onTimeout(event);
                     } catch (Throwable x) {
                        if (HttpChannelState.LOG.isDebugEnabled()) {
                           HttpChannelState.LOG.debug("{} while invoking onTimeout listener {}", new Object[]{x.toString(), listener, x});
                        } else {
                           HttpChannelState.LOG.warn("{} while invoking onTimeout listener {}", x.toString(), listener);
                        }
                     }
                  }

               }

               public String toString() {
                  return "onTimeout";
               }
            };
            this.runInContext(event, task);
         }
      } finally {
         try (AutoLock var7 = this.lock()) {
            this._onTimeoutThread = null;
         }

      }

   }

   public void complete() {
      boolean handle = false;

      AsyncContextEvent event;
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("complete {}", this.toStringLocked());
         }

         event = this._event;
         switch (this._requestState.ordinal()) {
            case 1:
               this._requestState = this._sendError ? HttpChannelState.RequestState.BLOCKING : HttpChannelState.RequestState.COMPLETE;
               break;
            case 2:
            case 3:
            default:
               throw new IllegalStateException(this.getStatusStringLocked());
            case 4:
               if (Thread.currentThread() != this._onTimeoutThread) {
                  throw new IllegalStateException(this.getStatusStringLocked());
               }

               this._requestState = this._sendError ? HttpChannelState.RequestState.BLOCKING : HttpChannelState.RequestState.COMPLETE;
               break;
            case 5:
               return;
         }

         if (this._state == HttpChannelState.State.WAITING) {
            handle = true;
            this._state = HttpChannelState.State.WOKEN;
         }
      }

      this.cancelTimeout(event);
      if (handle) {
         this.runInContext(event, this._channel);
      }

   }

   public void asyncError(Throwable failure) {
      AsyncContextEvent event = null;

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("asyncError {}", this.toStringLocked(), failure);
         }

         if (this._state == HttpChannelState.State.WAITING && this._requestState == HttpChannelState.RequestState.ASYNC) {
            this._state = HttpChannelState.State.WOKEN;
            this._event.addThrowable(failure);
            event = this._event;
         } else {
            if (!(failure instanceof QuietException)) {
               LOG.warn(failure.toString());
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Async error", failure);
            }
         }
      }

      if (event != null) {
         this.cancelTimeout(event);
         this.runInContext(event, this._channel);
      }

   }

   protected void onError(Throwable th) {
      AsyncContextEvent asyncEvent;
      List<AsyncListener> asyncListeners;
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("thrownException {}", this.getStatusStringLocked(), th);
         }

         if (this._state != HttpChannelState.State.HANDLING) {
            throw new IllegalStateException(this.getStatusStringLocked());
         }

         if (this._sendError) {
            LOG.warn("unhandled due to prior sendError", th);
            return;
         }

         switch (this._requestState.ordinal()) {
            case 0:
               this.sendError(th);
               return;
            case 1:
            case 2:
            case 5:
               if (this._asyncListeners == null || this._asyncListeners.isEmpty()) {
                  this.sendError(th);
                  return;
               }

               asyncEvent = this._event;
               asyncEvent.addThrowable(th);
               asyncListeners = this._asyncListeners;
               break;
            case 3:
            case 4:
            default:
               LOG.warn("unhandled in state {}", this._requestState, new IllegalStateException(th));
               return;
         }
      }

      this.runInContext(asyncEvent, () -> {
         for(AsyncListener listener : asyncListeners) {
            try {
               listener.onError(asyncEvent);
            } catch (Throwable x) {
               if (LOG.isDebugEnabled()) {
                  LOG.warn("{} while invoking onError listener {}", new Object[]{x.toString(), listener, x});
               } else {
                  LOG.warn("{} while invoking onError listener {}", x.toString(), listener);
               }
            }
         }

      });

      try (AutoLock l = this.lock()) {
         if (this._requestState == HttpChannelState.RequestState.ASYNC && !this._sendError) {
            this.sendError(th);
         } else if (this._requestState != HttpChannelState.RequestState.COMPLETE) {
            LOG.warn("unhandled in state {}", this._requestState, new IllegalStateException(th));
         }
      }

   }

   private void sendError(Throwable th) {
      Request request = this._channel.getRequest();
      Throwable cause = this._channel.unwrap(th, BadMessageException.class, UnavailableException.class);
      int code;
      String message;
      if (cause == null) {
         code = 500;
         message = th.toString();
      } else if (cause instanceof BadMessageException) {
         BadMessageException bme = (BadMessageException)cause;
         code = bme.getCode();
         message = bme.getReason();
      } else if (cause instanceof UnavailableException) {
         message = cause.toString();
         if (((UnavailableException)cause).isPermanent()) {
            code = 404;
         } else {
            code = 503;
         }
      } else {
         code = 500;
         message = null;
      }

      this.sendError(code, message);
      request.setAttribute("jakarta.servlet.error.exception", th);
      request.setAttribute("jakarta.servlet.error.exception_type", th.getClass());
      this._requestState = HttpChannelState.RequestState.BLOCKING;
   }

   public void sendError(int code, String message) {
      Request request = this._channel.getRequest();
      Response response = this._channel.getResponse();
      if (message == null) {
         message = HttpStatus.getMessage(code);
      }

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("sendError {}", this.toStringLocked());
         }

         if (this._outputState != HttpChannelState.OutputState.OPEN) {
            throw new IllegalStateException(this._outputState.toString());
         }

         switch (this._state.ordinal()) {
            case 1:
            case 2:
            case 3:
               response.setStatus(code);
               response.errorClose();
               request.setAttribute("org.sparkproject.jetty.server.error_context", request.getErrorContext());
               request.setAttribute("jakarta.servlet.error.request_uri", request.getRequestURI());
               request.setAttribute("jakarta.servlet.error.servlet_name", request.getServletName());
               request.setAttribute("jakarta.servlet.error.status_code", code);
               request.setAttribute("jakarta.servlet.error.message", message);
               this._sendError = true;
               if (this._event != null) {
                  Throwable cause = (Throwable)request.getAttribute("jakarta.servlet.error.exception");
                  if (cause != null) {
                     this._event.addThrowable(cause);
                  }
               }
               break;
            default:
               throw new IllegalStateException(this.getStatusStringLocked());
         }
      }

   }

   protected void completing() {
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("completing {}", this.toStringLocked());
         }

         switch (this._requestState.ordinal()) {
            case 7:
               throw new IllegalStateException(this.getStatusStringLocked());
            default:
               this._requestState = HttpChannelState.RequestState.COMPLETING;
         }
      }

   }

   protected void completed(Throwable failure) {
      boolean handle = false;

      List<AsyncListener> aListeners;
      AsyncContextEvent event;
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("completed {}", this.toStringLocked());
         }

         if (this._requestState != HttpChannelState.RequestState.COMPLETING) {
            throw new IllegalStateException(this.getStatusStringLocked());
         }

         if (this._event == null) {
            this._requestState = HttpChannelState.RequestState.COMPLETED;
            aListeners = null;
            event = null;
            if (this._state == HttpChannelState.State.WAITING) {
               this._state = HttpChannelState.State.WOKEN;
               handle = true;
            }
         } else {
            aListeners = this._asyncListeners;
            event = this._event;
         }
      }

      this._channel.getResponse().getHttpOutput().completed(failure);
      if (event != null) {
         this.cancelTimeout(event);
         if (aListeners != null) {
            this.runInContext(event, () -> {
               for(AsyncListener listener : aListeners) {
                  try {
                     listener.onComplete(event);
                  } catch (Throwable x) {
                     if (LOG.isDebugEnabled()) {
                        LOG.warn("{} while invoking onComplete listener {}", new Object[]{x.toString(), listener, x});
                     } else {
                        LOG.warn("{} while invoking onComplete listener {}", x.toString(), listener);
                     }
                  }
               }

            });
         }

         event.completed();

         try (AutoLock l = this.lock()) {
            this._requestState = HttpChannelState.RequestState.COMPLETED;
            if (this._state == HttpChannelState.State.WAITING) {
               this._state = HttpChannelState.State.WOKEN;
               handle = true;
            }
         }
      }

      if (handle) {
         this._channel.handle();
      }

   }

   protected void recycle() {
      this.cancelTimeout();

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("recycle {}", this.toStringLocked());
         }

         switch (this._state.ordinal()) {
            case 1:
               throw new IllegalStateException(this.getStatusStringLocked());
            case 4:
               return;
            default:
               this._asyncListeners = null;
               this._state = HttpChannelState.State.IDLE;
               this._requestState = HttpChannelState.RequestState.BLOCKING;
               this._outputState = HttpChannelState.OutputState.OPEN;
               this._initial = true;
               this._inputState = HttpChannelState.InputState.IDLE;
               this._asyncWritePossible = false;
               this._timeoutMs = DEFAULT_TIMEOUT;
               this._event = null;
         }
      }

   }

   public void upgrade() {
      this.cancelTimeout();

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("upgrade {}", this.toStringLocked());
         }

         switch (this._state.ordinal()) {
            case 0:
               this._asyncListeners = null;
               this._state = HttpChannelState.State.UPGRADED;
               this._requestState = HttpChannelState.RequestState.BLOCKING;
               this._initial = true;
               this._inputState = HttpChannelState.InputState.IDLE;
               this._asyncWritePossible = false;
               this._timeoutMs = DEFAULT_TIMEOUT;
               this._event = null;
               return;
            default:
               throw new IllegalStateException(this.getStatusStringLocked());
         }
      }
   }

   protected void scheduleDispatch() {
      this._channel.execute(this._channel);
   }

   protected void cancelTimeout() {
      this.cancelTimeout(this.getAsyncContextEvent());
   }

   protected void cancelTimeout(AsyncContextEvent event) {
      if (event != null) {
         event.cancelTimeoutTask();
      }

   }

   public boolean isIdle() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._state == HttpChannelState.State.IDLE;
      }

      return var2;
   }

   public boolean isExpired() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._requestState == HttpChannelState.RequestState.EXPIRE || this._requestState == HttpChannelState.RequestState.EXPIRING;
      }

      return var2;
   }

   public boolean isInitial() {
      try (AutoLock l = this.lock()) {
         return this._initial;
      }
   }

   public boolean isSuspended() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._state == HttpChannelState.State.WAITING || this._state == HttpChannelState.State.HANDLING && this._requestState == HttpChannelState.RequestState.ASYNC;
      }

      return var2;
   }

   boolean isCompleted() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._requestState == HttpChannelState.RequestState.COMPLETED;
      }

      return var2;
   }

   public boolean isAsyncStarted() {
      try (AutoLock l = this.lock()) {
         if (this._state == HttpChannelState.State.HANDLING) {
            boolean var6 = this._requestState != HttpChannelState.RequestState.BLOCKING;
            return var6;
         } else {
            return this._requestState == HttpChannelState.RequestState.ASYNC || this._requestState == HttpChannelState.RequestState.EXPIRING;
         }
      }
   }

   public boolean isAsync() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = !this._initial || this._requestState != HttpChannelState.RequestState.BLOCKING;
      }

      return var2;
   }

   public Request getBaseRequest() {
      return this._channel.getRequest();
   }

   public HttpChannel getHttpChannel() {
      return this._channel;
   }

   public ContextHandler getContextHandler() {
      return this.getContextHandler(this.getAsyncContextEvent());
   }

   ContextHandler getContextHandler(AsyncContextEvent event) {
      if (event != null) {
         ContextHandler.Context context = (ContextHandler.Context)event.getServletContext();
         if (context != null) {
            return context.getContextHandler();
         }
      }

      return null;
   }

   public ServletResponse getServletResponse() {
      return this.getServletResponse(this.getAsyncContextEvent());
   }

   public ServletResponse getServletResponse(AsyncContextEvent event) {
      return (ServletResponse)(event != null && event.getSuppliedResponse() != null ? event.getSuppliedResponse() : this._channel.getResponse());
   }

   void runInContext(AsyncContextEvent event, Runnable runnable) {
      ContextHandler contextHandler = this.getContextHandler(event);
      if (contextHandler == null) {
         runnable.run();
      } else {
         contextHandler.handle(this._channel.getRequest(), runnable);
      }

   }

   public Object getAttribute(String name) {
      return this._channel.getRequest().getAttribute(name);
   }

   public void removeAttribute(String name) {
      this._channel.getRequest().removeAttribute(name);
   }

   public void setAttribute(String name, Object attribute) {
      this._channel.getRequest().setAttribute(name, attribute);
   }

   public boolean onReadReady() {
      boolean woken = false;

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onReadReady {}", this.toStringLocked());
         }

         switch (this._inputState.ordinal()) {
            case 0:
            case 1:
               this._inputState = HttpChannelState.InputState.READY;
               if (this._state == HttpChannelState.State.WAITING) {
                  woken = true;
                  this._state = HttpChannelState.State.WOKEN;
               }
               break;
            case 2:
               this._inputState = HttpChannelState.InputState.READY;
               break;
            default:
               throw new IllegalStateException(this.toStringLocked());
         }
      }

      return woken;
   }

   public boolean onReadEof() {
      boolean woken = false;

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onReadEof {}", this.toStringLocked());
         }

         switch (this._inputState.ordinal()) {
            case 0:
            case 1:
            case 2:
               this._inputState = HttpChannelState.InputState.READY;
               if (this._state == HttpChannelState.State.WAITING) {
                  woken = true;
                  this._state = HttpChannelState.State.WOKEN;
               }
               break;
            default:
               throw new IllegalStateException(this.toStringLocked());
         }
      }

      return woken;
   }

   public void onContentAdded() {
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onContentAdded {}", this.toStringLocked());
         }

         switch (this._inputState.ordinal()) {
            case 0:
            case 1:
            case 2:
               this._inputState = HttpChannelState.InputState.READY;
               return;
            default:
               throw new IllegalStateException(this.toStringLocked());
         }
      }
   }

   public void onReadIdle() {
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onReadIdle {}", this.toStringLocked());
         }

         switch (this._inputState.ordinal()) {
            case 0:
            case 1:
            case 2:
               this._inputState = HttpChannelState.InputState.IDLE;
               return;
            default:
               throw new IllegalStateException(this.toStringLocked());
         }
      }
   }

   public void onReadUnready() {
      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onReadUnready {}", this.toStringLocked());
         }

         switch (this._inputState.ordinal()) {
            case 0:
            case 1:
            case 2:
               this._inputState = HttpChannelState.InputState.UNREADY;
               return;
            default:
               throw new IllegalStateException(this.toStringLocked());
         }
      }
   }

   public boolean isInputUnready() {
      boolean var2;
      try (AutoLock l = this.lock()) {
         var2 = this._inputState == HttpChannelState.InputState.UNREADY;
      }

      return var2;
   }

   public boolean onWritePossible() {
      boolean wake = false;

      try (AutoLock l = this.lock()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("onWritePossible {}", this.toStringLocked());
         }

         this._asyncWritePossible = true;
         if (this._state == HttpChannelState.State.WAITING) {
            this._state = HttpChannelState.State.WOKEN;
            return true;
         }
      }
   }

   public static enum State {
      IDLE,
      HANDLING,
      WAITING,
      WOKEN,
      UPGRADED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{IDLE, HANDLING, WAITING, WOKEN, UPGRADED};
      }
   }

   private static enum RequestState {
      BLOCKING,
      ASYNC,
      DISPATCH,
      EXPIRE,
      EXPIRING,
      COMPLETE,
      COMPLETING,
      COMPLETED;

      // $FF: synthetic method
      private static RequestState[] $values() {
         return new RequestState[]{BLOCKING, ASYNC, DISPATCH, EXPIRE, EXPIRING, COMPLETE, COMPLETING, COMPLETED};
      }
   }

   private static enum InputState {
      IDLE,
      UNREADY,
      READY;

      // $FF: synthetic method
      private static InputState[] $values() {
         return new InputState[]{IDLE, UNREADY, READY};
      }
   }

   private static enum OutputState {
      OPEN,
      COMMITTED,
      COMPLETED,
      ABORTED;

      // $FF: synthetic method
      private static OutputState[] $values() {
         return new OutputState[]{OPEN, COMMITTED, COMPLETED, ABORTED};
      }
   }

   public static enum Action {
      DISPATCH,
      ASYNC_DISPATCH,
      SEND_ERROR,
      ASYNC_ERROR,
      ASYNC_TIMEOUT,
      WRITE_CALLBACK,
      READ_CALLBACK,
      COMPLETE,
      TERMINATED,
      WAIT;

      // $FF: synthetic method
      private static Action[] $values() {
         return new Action[]{DISPATCH, ASYNC_DISPATCH, SEND_ERROR, ASYNC_ERROR, ASYNC_TIMEOUT, WRITE_CALLBACK, READ_CALLBACK, COMPLETE, TERMINATED, WAIT};
      }
   }
}
