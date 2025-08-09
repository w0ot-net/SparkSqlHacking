package org.sparkproject.jetty.client;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Authentication;
import org.sparkproject.jetty.client.api.AuthenticationStore;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.util.BytesRequestContent;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.CyclicTimeouts;
import org.sparkproject.jetty.util.Attachable;
import org.sparkproject.jetty.util.HttpCookieStore;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.thread.AutoLock;
import org.sparkproject.jetty.util.thread.Scheduler;

public abstract class HttpConnection implements IConnection, Attachable {
   private static final Logger LOG = LoggerFactory.getLogger(HttpConnection.class);
   private final AutoLock lock = new AutoLock();
   private final HttpDestination destination;
   private final RequestTimeouts requestTimeouts;
   private Object attachment;
   private int idleTimeoutGuard;
   private long idleTimeoutNanoTime;

   protected HttpConnection(HttpDestination destination) {
      this.destination = destination;
      this.requestTimeouts = new RequestTimeouts(destination.getHttpClient().getScheduler());
      this.idleTimeoutNanoTime = NanoTime.now();
   }

   public HttpClient getHttpClient() {
      return this.destination.getHttpClient();
   }

   public HttpDestination getHttpDestination() {
      return this.destination;
   }

   protected abstract Iterator getHttpChannels();

   public void send(Request request, Response.CompleteListener listener) {
      HttpRequest httpRequest = (HttpRequest)request;
      ArrayList<Response.ResponseListener> listeners = new ArrayList(httpRequest.getResponseListeners());
      httpRequest.sent();
      if (listener != null) {
         listeners.add(listener);
      }

      HttpExchange exchange = new HttpExchange(this.getHttpDestination(), httpRequest, listeners);
      SendFailure result = this.send(exchange);
      if (result != null) {
         httpRequest.abort(result.failure);
      }

   }

   protected SendFailure send(HttpChannel channel, HttpExchange exchange) {
      boolean send;
      try (AutoLock l = this.lock.lock()) {
         send = this.idleTimeoutGuard >= 0;
         if (send) {
            ++this.idleTimeoutGuard;
         }
      }

      if (send) {
         HttpRequest request = exchange.getRequest();
         SendFailure result;
         if (channel.associate(exchange)) {
            request.sent();
            this.requestTimeouts.schedule(channel);
            channel.send();
            result = null;
         } else {
            channel.release();
            result = new SendFailure(new HttpRequestException("Could not associate request to connection", request), false);
         }

         try (AutoLock l = this.lock.lock()) {
            --this.idleTimeoutGuard;
            this.idleTimeoutNanoTime = NanoTime.now();
         }

         return result;
      } else {
         return new SendFailure(new TimeoutException(), true);
      }
   }

   protected void normalizeRequest(HttpRequest request) {
      boolean normalized = request.normalized();
      if (LOG.isDebugEnabled()) {
         LOG.debug("Normalizing {} {}", !normalized, request);
      }

      if (!normalized) {
         String path = request.getPath();
         if (path.trim().length() == 0) {
            path = "/";
            request.path(path);
         }

         boolean http1 = request.getVersion().getVersion() <= 11;
         boolean applyProxyAuthentication = false;
         ProxyConfiguration.Proxy proxy = this.destination.getProxy();
         if (proxy instanceof HttpProxy) {
            boolean tunnelled = ((HttpProxy)proxy).requiresTunnel(this.destination.getOrigin());
            if (http1 && !tunnelled) {
               URI uri = request.getURI();
               if (uri != null) {
                  request.path(uri.toString());
               }
            }

            applyProxyAuthentication = !tunnelled;
         }

         HttpFields headers = request.getHeaders();
         if (http1 && !headers.contains(HttpHeader.HOST.asString())) {
            URI uri = request.getURI();
            if (uri != null) {
               request.addHeader(new HttpField(HttpHeader.HOST, uri.getAuthority()));
            } else {
               request.addHeader(this.getHttpDestination().getHostField());
            }
         }

         Request.Content content = request.getBody();
         if (content == null) {
            request.body(new BytesRequestContent(new byte[0][]));
         } else {
            if (!headers.contains(HttpHeader.CONTENT_TYPE)) {
               String contentType = content.getContentType();
               if (contentType == null) {
                  contentType = this.getHttpClient().getDefaultRequestContentType();
               }

               if (contentType != null) {
                  HttpField field = new HttpField(HttpHeader.CONTENT_TYPE, contentType);
                  request.addHeader(field);
               }
            }

            long contentLength = content.getLength();
            if (contentLength >= 0L && !headers.contains(HttpHeader.CONTENT_LENGTH)) {
               request.addHeader(new HttpField.LongValueHttpField(HttpHeader.CONTENT_LENGTH, contentLength));
            }
         }

         StringBuilder cookies = this.convertCookies(request.getCookies(), (StringBuilder)null);
         CookieStore cookieStore = this.getHttpClient().getCookieStore();
         if (cookieStore != null && cookieStore.getClass() != HttpCookieStore.Empty.class) {
            URI uri = request.getURI();
            if (uri != null) {
               cookies = this.convertCookies(HttpCookieStore.matchPath(uri, cookieStore.get(uri)), cookies);
            }
         }

         if (cookies != null) {
            HttpField cookieField = new HttpField(HttpHeader.COOKIE, cookies.toString());
            request.addHeader(cookieField);
         }

         if (applyProxyAuthentication) {
            this.applyProxyAuthentication(request, proxy);
         }

         this.applyRequestAuthentication(request);
      }
   }

   private StringBuilder convertCookies(List cookies, StringBuilder builder) {
      for(HttpCookie cookie : cookies) {
         if (builder == null) {
            builder = new StringBuilder();
         }

         if (builder.length() > 0) {
            builder.append("; ");
         }

         builder.append(cookie.getName()).append("=").append(cookie.getValue());
      }

      return builder;
   }

   private void applyRequestAuthentication(Request request) {
      AuthenticationStore authenticationStore = this.getHttpClient().getAuthenticationStore();
      if (authenticationStore.hasAuthenticationResults()) {
         URI uri = request.getURI();
         if (uri != null) {
            Authentication.Result result = authenticationStore.findAuthenticationResult(uri);
            if (result != null) {
               result.apply(request);
            }
         }
      }

   }

   private void applyProxyAuthentication(Request request, ProxyConfiguration.Proxy proxy) {
      if (proxy != null) {
         Authentication.Result result = this.getHttpClient().getAuthenticationStore().findAuthenticationResult(proxy.getURI());
         if (result != null) {
            result.apply(request);
         }
      }

   }

   public boolean onIdleTimeout(long idleTimeout, Throwable failure) {
      try (AutoLock l = this.lock.lock()) {
         if (this.idleTimeoutGuard == 0) {
            long elapsed = NanoTime.millisSince(this.idleTimeoutNanoTime);
            boolean idle = elapsed > idleTimeout / 2L;
            if (idle) {
               this.idleTimeoutGuard = -1;
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Idle timeout {}/{}ms - {}", new Object[]{elapsed, idleTimeout, this});
            }

            return idle;
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Idle timeout skipped - {}", this);
            }

            return false;
         }
      }
   }

   public void setAttachment(Object obj) {
      this.attachment = obj;
   }

   public Object getAttachment() {
      return this.attachment;
   }

   public void destroy() {
      this.requestTimeouts.destroy();
   }

   public String toString() {
      return String.format("%s@%h", this.getClass().getSimpleName(), this);
   }

   private class RequestTimeouts extends CyclicTimeouts {
      private RequestTimeouts(Scheduler scheduler) {
         super(scheduler);
      }

      protected Iterator iterator() {
         return HttpConnection.this.getHttpChannels();
      }

      protected boolean onExpired(HttpChannel channel) {
         HttpExchange exchange = channel.getHttpExchange();
         if (exchange != null) {
            HttpRequest request = exchange.getRequest();
            request.abort(new TimeoutException("Total timeout " + request.getConversation().getTimeout() + " ms elapsed"));
         }

         return false;
      }
   }
}
