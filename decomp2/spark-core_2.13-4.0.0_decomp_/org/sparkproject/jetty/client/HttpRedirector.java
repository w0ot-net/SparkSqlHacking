package org.sparkproject.jetty.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.client.util.BufferingResponseListener;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.util.NanoTime;

public class HttpRedirector {
   private static final Logger LOG = LoggerFactory.getLogger(HttpRedirector.class);
   private static final String SCHEME_REGEXP = "(^https?)";
   private static final String AUTHORITY_REGEXP = "([^/?#]+)";
   private static final String DESTINATION_REGEXP = "((^https?)://([^/?#]+))?";
   private static final String PATH_REGEXP = "([^?#]*)";
   private static final String QUERY_REGEXP = "([^#]*)";
   private static final String FRAGMENT_REGEXP = "(.*)";
   private static final Pattern URI_PATTERN = Pattern.compile("((^https?)://([^/?#]+))?([^?#]*)([^#]*)(.*)");
   private static final String ATTRIBUTE = HttpRedirector.class.getName() + ".redirects";
   private final HttpClient client;
   private final ResponseNotifier notifier;

   public HttpRedirector(HttpClient client) {
      this.client = client;
      this.notifier = new ResponseNotifier();
   }

   public boolean isRedirect(Response response) {
      switch (response.getStatus()) {
         case 301:
         case 302:
         case 303:
         case 307:
         case 308:
            return true;
         case 304:
         case 305:
         case 306:
         default:
            return false;
      }
   }

   public Result redirect(Request request, Response response) throws InterruptedException, ExecutionException {
      final AtomicReference<Result> resultRef = new AtomicReference();
      final CountDownLatch latch = new CountDownLatch(1);
      Request redirect = this.redirect(request, response, new BufferingResponseListener() {
         public void onComplete(Result result) {
            resultRef.set(new Result(result.getRequest(), result.getRequestFailure(), new HttpContentResponse(result.getResponse(), this.getContent(), this.getMediaType(), this.getEncoding()), result.getResponseFailure()));
            latch.countDown();
         }
      });

      try {
         latch.await();
         Result result = (Result)resultRef.get();
         if (result.isFailed()) {
            throw new ExecutionException(result.getFailure());
         } else {
            return result;
         }
      } catch (InterruptedException x) {
         redirect.abort(x);
         throw x;
      }
   }

   public Request redirect(Request request, Response response, Response.CompleteListener listener) {
      if (this.isRedirect(response)) {
         String location = response.getHeaders().get("Location");
         URI newURI = this.extractRedirectURI(response);
         if (newURI != null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Redirecting to {} (Location: {})", newURI, location);
            }

            return this.redirect(request, response, listener, newURI);
         } else {
            this.fail(request, (Response)response, (Throwable)(new HttpResponseException("Invalid 'Location' header: " + location, response)));
            return null;
         }
      } else {
         this.fail(request, (Response)response, (Throwable)(new HttpResponseException("Cannot redirect: " + String.valueOf(response), response)));
         return null;
      }
   }

   private Request redirect(Request request, Response response, Response.CompleteListener listener, URI newURI) {
      if (!newURI.isAbsolute()) {
         URI requestURI = request.getURI();
         if (requestURI == null) {
            String var10000 = request.getScheme();
            String uri = var10000 + "://" + request.getHost();
            int port = request.getPort();
            if (port > 0) {
               uri = uri + ":" + port;
            }

            requestURI = URI.create(uri);
         }

         newURI = requestURI.resolve(newURI);
      }

      int status = response.getStatus();
      switch (status) {
         case 301:
            String method = request.getMethod();
            if (!HttpMethod.GET.is(method) && !HttpMethod.HEAD.is(method) && !HttpMethod.PUT.is(method)) {
               if (HttpMethod.POST.is(method)) {
                  return this.redirect(request, response, listener, newURI, HttpMethod.GET.asString());
               }

               this.fail(request, (Response)response, (Throwable)(new HttpResponseException("HTTP protocol violation: received 301 for non GET/HEAD/POST/PUT request", response)));
               return null;
            }

            return this.redirect(request, response, listener, newURI, method);
         case 302:
            String method = request.getMethod();
            if (!HttpMethod.HEAD.is(method) && !HttpMethod.PUT.is(method)) {
               return this.redirect(request, response, listener, newURI, HttpMethod.GET.asString());
            }

            return this.redirect(request, response, listener, newURI, method);
         case 303:
            String method = request.getMethod();
            if (HttpMethod.HEAD.is(method)) {
               return this.redirect(request, response, listener, newURI, method);
            }

            return this.redirect(request, response, listener, newURI, HttpMethod.GET.asString());
         case 304:
         case 305:
         case 306:
         default:
            this.fail(request, (Response)response, (Throwable)(new HttpResponseException("Unhandled HTTP status code " + status, response)));
            return null;
         case 307:
         case 308:
            return this.redirect(request, response, listener, newURI, request.getMethod());
      }
   }

   private Request redirect(Request request, Response response, Response.CompleteListener listener, URI location, String method) {
      HttpRequest httpRequest = (HttpRequest)request;
      HttpConversation conversation = httpRequest.getConversation();
      Integer redirects = (Integer)conversation.getAttribute(ATTRIBUTE);
      if (redirects == null) {
         redirects = 0;
      }

      int maxRedirects = this.client.getMaxRedirects();
      if (maxRedirects >= 0 && redirects >= maxRedirects) {
         this.fail(request, (Response)response, (Throwable)(new HttpResponseException("Max redirects exceeded " + redirects, response)));
         return null;
      } else {
         redirects = redirects + 1;
         conversation.setAttribute(ATTRIBUTE, redirects);
         return this.sendRedirect(httpRequest, response, listener, location, method);
      }
   }

   public URI extractRedirectURI(Response response) {
      String location = response.getHeaders().get("location");
      return location != null ? this.sanitize(location) : null;
   }

   private URI sanitize(String location) {
      try {
         return new URI(location);
      } catch (URISyntaxException var11) {
         Matcher matcher = URI_PATTERN.matcher(location);
         if (matcher.matches()) {
            String scheme = matcher.group(2);
            String authority = matcher.group(3);
            String path = matcher.group(4);
            String query = matcher.group(5);
            if (query.length() == 0) {
               query = null;
            }

            String fragment = matcher.group(6);
            if (fragment.length() == 0) {
               fragment = null;
            }

            try {
               return new URI(scheme, authority, path, query, fragment);
            } catch (URISyntaxException var10) {
            }
         }

         return null;
      }
   }

   private Request sendRedirect(HttpRequest httpRequest, Response response, Response.CompleteListener listener, URI location, String method) {
      try {
         Request redirect = this.client.copyRequest(httpRequest, location);
         redirect.method(method);
         if (HttpMethod.GET.is(method)) {
            redirect.body((Request.Content)null);
            redirect.headers((headers) -> {
               headers.remove(HttpHeader.CONTENT_LENGTH);
               headers.remove(HttpHeader.CONTENT_TYPE);
            });
         } else if (HttpMethod.CONNECT.is(method)) {
            redirect.path(httpRequest.getPath());
         }

         Request.Content body = redirect.getBody();
         if (body != null && !body.isReproducible()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Could not redirect to {}, request body is not reproducible", location);
            }

            HttpConversation conversation = httpRequest.getConversation();
            conversation.updateResponseListeners((Response.ResponseListener)null);
            this.notifier.forwardSuccessComplete(conversation.getResponseListeners(), httpRequest, response);
            return null;
         } else {
            long timeoutNanoTime = httpRequest.getTimeoutNanoTime();
            if (timeoutNanoTime < Long.MAX_VALUE) {
               long newTimeout = NanoTime.until(timeoutNanoTime);
               if (newTimeout <= 0L) {
                  TimeoutException failure = new TimeoutException("Total timeout " + httpRequest.getConversation().getTimeout() + " ms elapsed");
                  this.fail(httpRequest, (Throwable)failure, (Response)response);
                  return null;
               }

               redirect.timeout(newTimeout, TimeUnit.NANOSECONDS);
            }

            redirect.onRequestBegin((request) -> {
               Throwable cause = httpRequest.getAbortCause();
               if (cause != null) {
                  request.abort(cause);
               }

            });
            redirect.send(listener);
            return redirect;
         }
      } catch (Throwable x) {
         this.fail(httpRequest, (Throwable)x, (Response)response);
         return null;
      }
   }

   protected void fail(Request request, Response response, Throwable failure) {
      this.fail(request, (Throwable)null, response, failure);
   }

   protected void fail(Request request, Throwable failure, Response response) {
      this.fail(request, failure, response, failure);
   }

   private void fail(Request request, Throwable requestFailure, Response response, Throwable responseFailure) {
      HttpConversation conversation = ((HttpRequest)request).getConversation();
      conversation.updateResponseListeners((Response.ResponseListener)null);
      List<Response.ResponseListener> listeners = conversation.getResponseListeners();
      this.notifier.notifyFailure(listeners, response, responseFailure);
      this.notifier.notifyComplete(listeners, new Result(request, requestFailure, response, responseFailure));
   }
}
