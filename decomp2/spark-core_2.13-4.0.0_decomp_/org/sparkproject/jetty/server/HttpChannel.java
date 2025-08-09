package org.sparkproject.jetty.server;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpGenerator;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.QuietException;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ErrorHandler;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.SharedBlockingCallback;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.thread.Invocable;
import org.sparkproject.jetty.util.thread.Scheduler;

public abstract class HttpChannel implements Runnable, HttpOutput.Interceptor {
   public static Listener NOOP_LISTENER = new Listener() {
   };
   private static final Logger LOG = LoggerFactory.getLogger(HttpChannel.class);
   private final AtomicLong _requests = new AtomicLong();
   private final Connector _connector;
   private final Executor _executor;
   private final HttpConfiguration _configuration;
   private final EndPoint _endPoint;
   private final HttpTransport _transport;
   private final HttpChannelState _state;
   private final Request _request;
   private final Response _response;
   private final Listener _combinedListener;
   private final Dispatchable _requestDispatcher;
   private final Dispatchable _asyncDispatcher;
   /** @deprecated */
   @Deprecated
   private final List _transientListeners = new ArrayList();
   private MetaData.Response _committedMetaData;
   private RequestLog _requestLog;
   private long _oldIdleTimeout;
   private long _written;

   public HttpChannel(Connector connector, HttpConfiguration configuration, EndPoint endPoint, HttpTransport transport) {
      this._connector = connector;
      this._configuration = (HttpConfiguration)Objects.requireNonNull(configuration);
      this._endPoint = endPoint;
      this._transport = transport;
      this._state = new HttpChannelState(this);
      this._request = new Request(this, this.newHttpInput(this._state));
      this._response = new Response(this, this.newHttpOutput());
      this._executor = connector.getServer().getThreadPool();
      this._requestLog = connector.getServer().getRequestLog();
      this._combinedListener = connector instanceof AbstractConnector ? ((AbstractConnector)connector).getHttpChannelListeners() : NOOP_LISTENER;
      this._requestDispatcher = new RequestDispatchable();
      this._asyncDispatcher = new AsyncDispatchable();
      if (LOG.isDebugEnabled()) {
         LOG.debug("new {} -> {},{},{}", new Object[]{this, this._endPoint, this._endPoint == null ? null : this._endPoint.getConnection(), this._state});
      }

   }

   public boolean isSendError() {
      return this._state.isSendError();
   }

   protected String formatAddrOrHost(String addr) {
      return HostPort.normalizeHost(addr);
   }

   private HttpInput newHttpInput(HttpChannelState state) {
      return new HttpInput(state);
   }

   public abstract boolean needContent();

   public abstract HttpInput.Content produceContent();

   public abstract boolean failAllContent(Throwable var1);

   public abstract boolean failed(Throwable var1);

   protected abstract boolean eof();

   protected HttpOutput newHttpOutput() {
      return new HttpOutput(this);
   }

   public HttpChannelState getState() {
      return this._state;
   }

   /** @deprecated */
   @Deprecated
   public boolean addListener(Listener listener) {
      return this._transientListeners.add(listener);
   }

   /** @deprecated */
   @Deprecated
   public boolean removeListener(Listener listener) {
      return this._transientListeners.remove(listener);
   }

   /** @deprecated */
   @Deprecated
   public List getTransientListeners() {
      return this._transientListeners;
   }

   public long getBytesWritten() {
      return this._written;
   }

   public long getRequests() {
      return this._requests.get();
   }

   public Connector getConnector() {
      return this._connector;
   }

   public HttpTransport getHttpTransport() {
      return this._transport;
   }

   public RequestLog getRequestLog() {
      return this._requestLog;
   }

   public void setRequestLog(RequestLog requestLog) {
      this._requestLog = requestLog;
   }

   public void addRequestLog(RequestLog requestLog) {
      if (this._requestLog == null) {
         this._requestLog = requestLog;
      } else if (this._requestLog instanceof RequestLogCollection) {
         ((RequestLogCollection)this._requestLog).add(requestLog);
      } else {
         this._requestLog = new RequestLogCollection(new RequestLog[]{this._requestLog, requestLog});
      }

   }

   public MetaData.Response getCommittedMetaData() {
      return this._committedMetaData;
   }

   public long getIdleTimeout() {
      return this._endPoint.getIdleTimeout();
   }

   public void setIdleTimeout(long timeoutMs) {
      this._endPoint.setIdleTimeout(timeoutMs);
   }

   public ByteBufferPool getByteBufferPool() {
      return this._connector.getByteBufferPool();
   }

   public HttpConfiguration getHttpConfiguration() {
      return this._configuration;
   }

   public Server getServer() {
      return this._connector.getServer();
   }

   public Request getRequest() {
      return this._request;
   }

   public Response getResponse() {
      return this._response;
   }

   public Connection getConnection() {
      return this._endPoint.getConnection();
   }

   public EndPoint getEndPoint() {
      return this._endPoint;
   }

   public String getLocalName() {
      HttpConfiguration httpConfiguration = this.getHttpConfiguration();
      if (httpConfiguration != null) {
         SocketAddress localAddress = httpConfiguration.getLocalAddress();
         if (localAddress instanceof InetSocketAddress) {
            return ((InetSocketAddress)localAddress).getHostName();
         }
      }

      InetSocketAddress local = this.getLocalAddress();
      return local != null ? local.getHostString() : null;
   }

   public int getLocalPort() {
      HttpConfiguration httpConfiguration = this.getHttpConfiguration();
      if (httpConfiguration != null) {
         SocketAddress localAddress = httpConfiguration.getLocalAddress();
         if (localAddress instanceof InetSocketAddress) {
            return ((InetSocketAddress)localAddress).getPort();
         }
      }

      InetSocketAddress local = this.getLocalAddress();
      return local == null ? 0 : local.getPort();
   }

   public InetSocketAddress getLocalAddress() {
      HttpConfiguration httpConfiguration = this.getHttpConfiguration();
      if (httpConfiguration != null) {
         SocketAddress localAddress = httpConfiguration.getLocalAddress();
         if (localAddress instanceof InetSocketAddress) {
            return (InetSocketAddress)localAddress;
         }
      }

      SocketAddress local = this._endPoint.getLocalSocketAddress();
      return local instanceof InetSocketAddress ? (InetSocketAddress)local : null;
   }

   public InetSocketAddress getRemoteAddress() {
      SocketAddress remote = this._endPoint.getRemoteSocketAddress();
      return remote instanceof InetSocketAddress ? (InetSocketAddress)remote : null;
   }

   public HostPort getServerAuthority() {
      HttpConfiguration httpConfiguration = this.getHttpConfiguration();
      return httpConfiguration != null ? httpConfiguration.getServerAuthority() : null;
   }

   public void continue100(int available) throws IOException {
      throw new UnsupportedOperationException();
   }

   public void recycle() {
      this._request.recycle();
      this._response.recycle();
      this._committedMetaData = null;
      this._requestLog = this._connector == null ? null : this._connector.getServer().getRequestLog();
      this._written = 0L;
      this._oldIdleTimeout = 0L;
      this._transientListeners.clear();
   }

   public void run() {
      this.handle();
   }

   public boolean handle() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("handle {} {} ", this._request.getHttpURI(), this);
      }

      HttpChannelState.Action action = this._state.handling();

      while(true) {
         label298: {
            if (!this.getServer().isStopped()) {
               try {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("action {} {}", action, this);
                  }

                  switch (action) {
                     case TERMINATED:
                        this.onCompleted();
                     case WAIT:
                        break;
                     case DISPATCH:
                        if (!this._request.hasMetaData()) {
                           throw new IllegalStateException("state=" + String.valueOf(this._state));
                        }

                        this.dispatch(DispatcherType.REQUEST, this._requestDispatcher);
                        break label298;
                     case ASYNC_DISPATCH:
                        this.dispatch(DispatcherType.ASYNC, this._asyncDispatcher);
                        break label298;
                     case ASYNC_TIMEOUT:
                        this._state.onTimeout();
                        break label298;
                     case SEND_ERROR:
                        try {
                           try {
                              this._response.resetContent();
                              Integer code = (Integer)this._request.getAttribute("jakarta.servlet.error.status_code");
                              if (code == null) {
                                 code = 500;
                              }

                              this._response.setStatus(code);
                              this.ensureConsumeAllOrNotPersistent();
                              ContextHandler.Context context = (ContextHandler.Context)this._request.getAttribute("org.sparkproject.jetty.server.error_context");
                              ErrorHandler errorHandler = ErrorHandler.getErrorHandler(this.getServer(), context == null ? null : context.getContextHandler());
                              if (!HttpStatus.hasNoBody(this._response.getStatus()) && errorHandler != null && errorHandler.errorPageForMethod(this._request.getMethod())) {
                                 this.dispatch(DispatcherType.ERROR, new ErrorDispatchable(errorHandler));
                              } else {
                                 this.sendResponseAndComplete();
                              }
                           } catch (Throwable var11) {
                              if (LOG.isDebugEnabled()) {
                                 LOG.debug("Could not perform ERROR dispatch, aborting", var11);
                              }

                              if (this._state.isResponseCommitted()) {
                                 this.abort(var11);
                              } else {
                                 try {
                                    this._response.resetContent();
                                    this.sendResponseAndComplete();
                                 } catch (Throwable t) {
                                    if (var11 != t) {
                                       var11.addSuppressed(t);
                                    }

                                    this.abort(var11);
                                 }
                              }
                           }
                           break label298;
                        } finally {
                           this._request.removeAttribute("org.sparkproject.jetty.server.error_context");
                        }
                     case ASYNC_ERROR:
                        throw this._state.getAsyncContextEvent().getThrowable();
                     case READ_CALLBACK:
                        ContextHandler handler = this._state.getContextHandler();
                        if (handler != null) {
                           handler.handle(this._request, this._request.getHttpInput());
                        } else {
                           this._request.getHttpInput().run();
                        }
                        break label298;
                     case WRITE_CALLBACK:
                        ContextHandler handler = this._state.getContextHandler();
                        if (handler != null) {
                           handler.handle(this._request, this._response.getHttpOutput());
                        } else {
                           this._response.getHttpOutput().run();
                        }
                        break label298;
                     case COMPLETE:
                        if (!this._response.isCommitted()) {
                           if (!this._request.isHandled() && !this._response.getHttpOutput().isClosed()) {
                              this._response.sendError(404);
                              break label298;
                           }

                           if (this._response.getStatus() >= 200) {
                              this.ensureConsumeAllOrNotPersistent();
                           }
                        }

                        if ((this._request.isHead() || this._response.getStatus() == 304 || this._response.isContentComplete(this._response.getHttpOutput().getWritten()) || !this.sendErrorOrAbort("Insufficient content written")) && !this.checkAndPrepareUpgrade()) {
                           Response var10000 = this._response;
                           Invocable.InvocationType var10001 = Invocable.InvocationType.NON_BLOCKING;
                           Runnable var10002 = () -> this._state.completed((Throwable)null);
                           HttpChannelState var10003 = this._state;
                           Objects.requireNonNull(var10003);
                           var10000.completeOutput(Callback.from(var10001, var10002, var10003::completed));
                        }
                        break label298;
                     default:
                        throw new IllegalStateException(this.toString());
                  }
               } catch (Throwable failure) {
                  if ("org.sparkproject.jetty.continuation.ContinuationThrowable".equals(failure.getClass().getName())) {
                     LOG.trace("IGNORED", failure);
                  } else {
                     this.handleException(failure);
                  }
                  break label298;
               }
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("!handle {} {}", action, this);
            }

            boolean suspended = action == HttpChannelState.Action.WAIT;
            return !suspended;
         }

         action = this._state.unhandle();
      }
   }

   public void ensureConsumeAllOrNotPersistent() {
      switch (this._request.getHttpVersion()) {
         case HTTP_1_0:
            if (this._request.getHttpInput().consumeAll()) {
               return;
            }

            this._response.getHttpFields().computeField((HttpHeader)HttpHeader.CONNECTION, (h, fields) -> {
               if (fields != null && !fields.isEmpty()) {
                  String v = (String)fields.stream().flatMap((field) -> Stream.of(field.getValues()).filter((s) -> !HttpHeaderValue.KEEP_ALIVE.is(s))).collect(Collectors.joining(", "));
                  return StringUtil.isEmpty(v) ? null : new HttpField(HttpHeader.CONNECTION, v);
               } else {
                  return null;
               }
            });
            break;
         case HTTP_1_1:
            if (this._request.getHttpInput().consumeAll()) {
               return;
            }

            this._response.getHttpFields().computeField((HttpHeader)HttpHeader.CONNECTION, (h, fields) -> {
               if (fields != null && !fields.isEmpty()) {
                  if (fields.stream().anyMatch((fx) -> fx.contains(HttpHeaderValue.CLOSE.asString()))) {
                     if (fields.size() == 1) {
                        HttpField f = (HttpField)fields.get(0);
                        if (HttpConnection.CONNECTION_CLOSE.equals(f)) {
                           return f;
                        }
                     }

                     return new HttpField(HttpHeader.CONNECTION, (String)fields.stream().flatMap((field) -> Stream.of(field.getValues()).filter((s) -> !HttpHeaderValue.KEEP_ALIVE.is(s))).collect(Collectors.joining(", ")));
                  } else {
                     return new HttpField(HttpHeader.CONNECTION, (String)Stream.concat(fields.stream().flatMap((field) -> Stream.of(field.getValues()).filter((s) -> !HttpHeaderValue.KEEP_ALIVE.is(s))), Stream.of(HttpHeaderValue.CLOSE.asString())).collect(Collectors.joining(", ")));
                  }
               } else {
                  return HttpConnection.CONNECTION_CLOSE;
               }
            });
      }

   }

   public boolean sendErrorOrAbort(String message) {
      try {
         if (this.isCommitted()) {
            this.abort(new IOException(message));
            return false;
         } else {
            this._response.sendError(500, message);
            return true;
         }
      } catch (Throwable x) {
         LOG.trace("IGNORED", x);
         this.abort(x);
         return false;
      }
   }

   private void dispatch(DispatcherType type, Dispatchable dispatchable) throws IOException, ServletException {
      try {
         this._request.setHandled(false);
         this._response.reopen();
         this._request.setDispatcherType(type);
         this._combinedListener.onBeforeDispatch(this._request);
         dispatchable.dispatch();
      } catch (Throwable x) {
         this._combinedListener.onDispatchFailure(this._request, x);
         throw x;
      } finally {
         this._combinedListener.onAfterDispatch(this._request);
         this._request.setDispatcherType((DispatcherType)null);
      }

   }

   protected void handleException(Throwable failure) {
      Throwable quiet = this.unwrap(failure, QuietException.class);
      Throwable noStack = this.unwrap(failure, BadMessageException.class, IOException.class, TimeoutException.class);
      if (quiet == null && this.getServer().isRunning()) {
         if (noStack != null) {
            if (LOG.isDebugEnabled()) {
               LOG.warn("handleException {}", this._request.getRequestURI(), failure);
            } else {
               LOG.warn("handleException {} {}", this._request.getRequestURI(), noStack.toString());
            }
         } else {
            LOG.warn(this._request.getRequestURI(), failure);
         }
      } else if (LOG.isDebugEnabled()) {
         LOG.debug(this._request.getRequestURI(), failure);
      }

      if (this.isCommitted()) {
         this.abort(failure);
      } else {
         try {
            this._state.onError(failure);
         } catch (IllegalStateException var5) {
            this.abort(failure);
         }
      }

   }

   protected Throwable unwrap(Throwable failure, Class... targets) {
      while(failure != null) {
         for(Class x : targets) {
            if (x.isInstance(failure)) {
               return failure;
            }
         }

         failure = failure.getCause();
      }

      return null;
   }

   public void sendResponseAndComplete() {
      try {
         this._request.setHandled(true);
         this._state.completing();
         ByteBuffer var10002 = this._response.getHttpOutput().getBuffer();
         Runnable var10004 = () -> this._state.completed((Throwable)null);
         HttpChannelState var10005 = this._state;
         Objects.requireNonNull(var10005);
         this.sendResponse((MetaData.Response)null, var10002, true, Callback.from(var10004, var10005::completed));
      } catch (Throwable x) {
         this.abort(x);
      }

   }

   public boolean isExpecting100Continue() {
      return false;
   }

   public boolean isExpecting102Processing() {
      return false;
   }

   public String toString() {
      long timeStamp = this._request.getTimeStamp();
      return String.format("%s@%x{s=%s,r=%s,c=%b/%b,a=%s,uri=%s,age=%d}", this.getClass().getSimpleName(), this.hashCode(), this._state, this._requests, this.isRequestCompleted(), this.isResponseCompleted(), this._state.getState(), this._request.getHttpURI(), timeStamp == 0L ? 0L : System.currentTimeMillis() - timeStamp);
   }

   public void onRequest(MetaData.Request request) {
      this._requests.incrementAndGet();
      this._request.setTimeStamp(System.currentTimeMillis());
      HttpFields.Mutable fields = this._response.getHttpFields();
      if (this._configuration.getSendDateHeader() && !fields.contains(HttpHeader.DATE)) {
         fields.put(this._connector.getServer().getDateField());
      }

      long idleTO = this._configuration.getIdleTimeout();
      this._oldIdleTimeout = this.getIdleTimeout();
      if (idleTO >= 0L && this._oldIdleTimeout != idleTO) {
         this.setIdleTimeout(idleTO);
      }

      this._request.setMetaData(request);
      this._combinedListener.onRequestBegin(this._request);
      if (LOG.isDebugEnabled()) {
         LOG.debug("REQUEST for {} on {}{}{} {} {}{}{}", new Object[]{request.getURIString(), this, System.lineSeparator(), request.getMethod(), request.getURIString(), request.getHttpVersion(), System.lineSeparator(), request.getFields()});
      }

   }

   public boolean onContent(HttpInput.Content content) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onContent {} {}", this, content);
      }

      this._combinedListener.onRequestContent(this._request, content.getByteBuffer());
      return false;
   }

   public boolean onContentComplete() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onContentComplete {}", this);
      }

      this._combinedListener.onRequestContentEnd(this._request);
      return false;
   }

   public void onTrailers(HttpFields trailers) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onTrailers {} {}", this, trailers);
      }

      this._request.setTrailerHttpFields(trailers);
      this._combinedListener.onRequestTrailers(this._request);
   }

   public boolean onRequestComplete() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onRequestComplete {}", this);
      }

      boolean result = this.eof();
      this._combinedListener.onRequestEnd(this._request);
      return result;
   }

   protected boolean checkAndPrepareUpgrade() {
      return false;
   }

   public void onCompleted() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("onCompleted for {} written={}", this.getRequest().getRequestURI(), this.getBytesWritten());
      }

      long idleTO = this._configuration.getIdleTimeout();
      if (idleTO >= 0L && this.getIdleTimeout() != this._oldIdleTimeout) {
         this.setIdleTimeout(this._oldIdleTimeout);
      }

      this._request.onCompleted();
      this._combinedListener.onComplete(this._request);
      this._transport.onCompleted();
   }

   public void onBadMessage(BadMessageException failure) {
      int status = failure.getCode();
      String reason = failure.getReason();
      if (status < 400 || status > 599) {
         failure = new BadMessageException(400, reason, failure);
      }

      this._combinedListener.onRequestFailure(this._request, failure);

      HttpChannelState.Action action;
      try {
         action = this._state.handling();
      } catch (Throwable e) {
         this.abort(e);
         throw failure;
      }

      try {
         if (action == HttpChannelState.Action.DISPATCH) {
            ByteBuffer content = null;
            HttpFields.Mutable fields = HttpFields.build();
            ErrorHandler handler = (ErrorHandler)this.getServer().getBean(ErrorHandler.class);
            if (handler != null) {
               content = handler.badMessageError(status, reason, fields);
            }

            this.sendResponse(new MetaData.Response(HttpVersion.HTTP_1_1, status, (String)null, fields, (long)BufferUtil.length(content)), content, true);
         }
      } catch (IOException e) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to send bad message response", e);
         }
      } finally {
         try {
            this.onCompleted();
         } catch (Throwable e) {
            LOG.debug("Unable to complete bad message", e);
            this.abort(e);
         }

      }

   }

   protected boolean sendResponse(MetaData.Response response, ByteBuffer content, boolean complete, Callback callback) {
      boolean committing = this._state.commitResponse();
      if (LOG.isDebugEnabled()) {
         LOG.debug("sendResponse info={} content={} complete={} committing={} callback={}", new Object[]{response, BufferUtil.toDetailString(content), complete, committing, callback});
      }

      if (committing) {
         this._combinedListener.onResponseBegin(this._request);
         if (response == null) {
            response = this._response.newResponseMetaData();
         }

         this.commit(response);
         this._request.onResponseCommit();
         int status = response.getStatus();
         Callback committed = (Callback)(HttpStatus.isInformational(status) ? new Send1XXCallback(callback) : new SendCallback(callback, content, true, complete));
         this._transport.send(this._request.getMetaData(), response, content, complete, committed);
      } else if (response == null) {
         this._transport.send(this._request.getMetaData(), (MetaData.Response)null, content, complete, new SendCallback(callback, content, false, complete));
      } else {
         callback.failed(new IllegalStateException("committed"));
      }

      return committing;
   }

   public boolean sendResponse(MetaData.Response info, ByteBuffer content, boolean complete) throws IOException {
      try {
         SharedBlockingCallback.Blocker blocker = this._response.getHttpOutput().acquireWriteBlockingCallback();

         boolean var6;
         try {
            boolean committing = this.sendResponse(info, content, complete, blocker);
            blocker.block();
            var6 = committing;
         } catch (Throwable var8) {
            if (blocker != null) {
               try {
                  blocker.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (blocker != null) {
            blocker.close();
         }

         return var6;
      } catch (Throwable var9) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Unable to send response", var9);
         }

         this.abort(var9);
         throw var9;
      }
   }

   protected void commit(MetaData.Response info) {
      this._committedMetaData = info;
      if (LOG.isDebugEnabled()) {
         LOG.debug("COMMIT for {} on {}{}{} {} {}{}{}", new Object[]{this.getRequest().getRequestURI(), this, System.lineSeparator(), info.getStatus(), info.getReason(), info.getHttpVersion(), System.lineSeparator(), info.getFields()});
      }

   }

   public boolean isCommitted() {
      return this._state.isResponseCommitted();
   }

   public boolean isRequestCompleted() {
      return this._state.isCompleted();
   }

   public boolean isResponseCompleted() {
      return this._state.isResponseCompleted();
   }

   public boolean isPersistent() {
      return this._endPoint.isOpen();
   }

   public void write(ByteBuffer content, boolean complete, Callback callback) {
      this.sendResponse((MetaData.Response)null, content, complete, callback);
   }

   public void resetBuffer() {
      if (this.isCommitted()) {
         throw new IllegalStateException("Committed");
      }
   }

   public HttpOutput.Interceptor getNextInterceptor() {
      return null;
   }

   protected void execute(Runnable task) {
      this._executor.execute(task);
   }

   public Scheduler getScheduler() {
      return this._connector.getScheduler();
   }

   public boolean isUseInputDirectByteBuffers() {
      return this.getHttpConfiguration().isUseInputDirectByteBuffers();
   }

   public boolean isUseOutputDirectByteBuffers() {
      return this.getHttpConfiguration().isUseOutputDirectByteBuffers();
   }

   public void abort(Throwable failure) {
      if (this._state.abortResponse()) {
         this._combinedListener.onResponseFailure(this._request, failure);
         this._transport.abort(failure);
      }

   }

   public boolean isTunnellingSupported() {
      return false;
   }

   public EndPoint getTunnellingEndPoint() {
      throw new UnsupportedOperationException("Tunnelling not supported");
   }

   private void notifyEvent1(Function function, Request request) {
      for(Listener listener : this._transientListeners) {
         try {
            ((Consumer)function.apply(listener)).accept(request);
         } catch (Throwable x) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failure invoking listener {}", listener, x);
            }
         }
      }

   }

   private void notifyEvent2(Function function, Request request, ByteBuffer content) {
      for(Listener listener : this._transientListeners) {
         ByteBuffer view = content.slice();

         try {
            ((BiConsumer)function.apply(listener)).accept(request, view);
         } catch (Throwable x) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failure invoking listener {}", listener, x);
            }
         }
      }

   }

   private void notifyEvent2(Function function, Request request, Throwable failure) {
      for(Listener listener : this._transientListeners) {
         try {
            ((BiConsumer)function.apply(listener)).accept(request, failure);
         } catch (Throwable x) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failure invoking listener {}", listener, x);
            }
         }
      }

   }

   public interface Listener extends EventListener {
      default void onRequestBegin(Request request) {
      }

      default void onBeforeDispatch(Request request) {
      }

      default void onDispatchFailure(Request request, Throwable failure) {
      }

      default void onAfterDispatch(Request request) {
      }

      default void onRequestContent(Request request, ByteBuffer content) {
      }

      default void onRequestContentEnd(Request request) {
      }

      default void onRequestTrailers(Request request) {
      }

      default void onRequestEnd(Request request) {
      }

      default void onRequestFailure(Request request, Throwable failure) {
      }

      default void onResponseBegin(Request request) {
      }

      default void onResponseCommit(Request request) {
      }

      default void onResponseContent(Request request, ByteBuffer content) {
      }

      default void onResponseEnd(Request request) {
      }

      default void onResponseFailure(Request request, Throwable failure) {
      }

      default void onComplete(Request request) {
      }
   }

   private class SendCallback extends Callback.Nested {
      private final ByteBuffer _content;
      private final int _length;
      private final boolean _commit;
      private final boolean _complete;

      private SendCallback(Callback callback, ByteBuffer content, boolean commit, boolean complete) {
         super(callback);
         this._content = content == null ? BufferUtil.EMPTY_BUFFER : content.slice();
         this._length = this._content.remaining();
         this._commit = commit;
         this._complete = complete;
      }

      public void succeeded() {
         HttpChannel var10000 = HttpChannel.this;
         var10000._written += (long)this._length;
         if (this._commit) {
            HttpChannel.this._combinedListener.onResponseCommit(HttpChannel.this._request);
         }

         if (this._length > 0) {
            HttpChannel.this._combinedListener.onResponseContent(HttpChannel.this._request, this._content);
         }

         if (this._complete && HttpChannel.this._state.completeResponse()) {
            HttpChannel.this._combinedListener.onResponseEnd(HttpChannel.this._request);
         }

         super.succeeded();
      }

      public void failed(final Throwable x) {
         if (HttpChannel.LOG.isDebugEnabled()) {
            HttpChannel.LOG.debug("Commit failed", x);
         }

         if (x instanceof BadMessageException) {
            HttpChannel.this._transport.send(HttpChannel.this._request.getMetaData(), HttpGenerator.RESPONSE_500_INFO, (ByteBuffer)null, true, new Callback.Nested(this) {
               public void succeeded() {
                  HttpChannel.this._response.getHttpOutput().completed((Throwable)null);
                  super.failed(x);
               }

               public void failed(Throwable th) {
                  HttpChannel.this.abort(x);
                  super.failed(x);
               }
            });
         } else {
            HttpChannel.this.abort(x);
            super.failed(x);
         }

      }
   }

   private class Send1XXCallback extends SendCallback {
      private Send1XXCallback(Callback callback) {
         super(callback, (ByteBuffer)null, false, false);
      }

      public void succeeded() {
         if (HttpChannel.this._state.partialResponse()) {
            super.succeeded();
         } else {
            super.failed(new IllegalStateException());
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public static class TransientListeners implements Listener {
      public void onRequestBegin(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onRequestBegin;
         }, request);
      }

      public void onBeforeDispatch(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onBeforeDispatch;
         }, request);
      }

      public void onDispatchFailure(Request request, Throwable failure) {
         request.getHttpChannel().notifyEvent2((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onDispatchFailure;
         }, request, (Throwable)failure);
      }

      public void onAfterDispatch(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onAfterDispatch;
         }, request);
      }

      public void onRequestContent(Request request, ByteBuffer content) {
         request.getHttpChannel().notifyEvent2((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onRequestContent;
         }, request, (ByteBuffer)content);
      }

      public void onRequestContentEnd(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onRequestContentEnd;
         }, request);
      }

      public void onRequestTrailers(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onRequestTrailers;
         }, request);
      }

      public void onRequestEnd(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onRequestEnd;
         }, request);
      }

      public void onRequestFailure(Request request, Throwable failure) {
         request.getHttpChannel().notifyEvent2((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onRequestFailure;
         }, request, (Throwable)failure);
      }

      public void onResponseBegin(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onResponseBegin;
         }, request);
      }

      public void onResponseCommit(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onResponseCommit;
         }, request);
      }

      public void onResponseContent(Request request, ByteBuffer content) {
         request.getHttpChannel().notifyEvent2((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onResponseContent;
         }, request, (ByteBuffer)content);
      }

      public void onResponseEnd(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onResponseEnd;
         }, request);
      }

      public void onResponseFailure(Request request, Throwable failure) {
         request.getHttpChannel().notifyEvent2((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onResponseFailure;
         }, request, (Throwable)failure);
      }

      public void onComplete(Request request) {
         request.getHttpChannel().notifyEvent1((listener) -> {
            Objects.requireNonNull(listener);
            return listener::onComplete;
         }, request);
      }
   }

   private class RequestDispatchable implements Dispatchable {
      public void dispatch() throws IOException, ServletException {
         for(HttpConfiguration.Customizer customizer : HttpChannel.this._configuration.getCustomizers()) {
            customizer.customize(HttpChannel.this.getConnector(), HttpChannel.this._configuration, HttpChannel.this._request);
            if (HttpChannel.this._request.isHandled()) {
               return;
            }
         }

         HttpChannel.this.getServer().handle(HttpChannel.this);
      }
   }

   private class AsyncDispatchable implements Dispatchable {
      public void dispatch() throws IOException, ServletException {
         HttpChannel.this.getServer().handleAsync(HttpChannel.this);
      }
   }

   private class ErrorDispatchable implements Dispatchable {
      private final ErrorHandler _errorHandler;

      public ErrorDispatchable(ErrorHandler errorHandler) {
         this._errorHandler = errorHandler;
      }

      public void dispatch() throws IOException, ServletException {
         this._errorHandler.handle((String)null, HttpChannel.this._request, HttpChannel.this._request, HttpChannel.this._response);
         HttpChannel.this._request.setHandled(true);
      }
   }

   interface Dispatchable {
      void dispatch() throws IOException, ServletException;
   }
}
