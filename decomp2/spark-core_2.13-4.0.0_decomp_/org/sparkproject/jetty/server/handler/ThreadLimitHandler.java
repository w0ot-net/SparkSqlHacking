package org.sparkproject.jetty.server.handler;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HostPortHttpField;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.QuotedCSV;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.IncludeExcludeSet;
import org.sparkproject.jetty.util.InetAddressSet;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.annotation.Name;
import org.sparkproject.jetty.util.thread.AutoLock;

public class ThreadLimitHandler extends HandlerWrapper {
   private static final Logger LOG = LoggerFactory.getLogger(ThreadLimitHandler.class);
   private static final String REMOTE = "o.e.j.s.h.TLH.REMOTE";
   private static final String PERMIT = "o.e.j.s.h.TLH.PASS";
   private final boolean _rfc7239;
   private final String _forwardedHeader;
   private final IncludeExcludeSet _includeExcludeSet;
   private final ConcurrentHashMap _remotes;
   private volatile boolean _enabled;
   private int _threadLimit;

   public ThreadLimitHandler() {
      this((String)null, false);
   }

   public ThreadLimitHandler(@Name("forwardedHeader") String forwardedHeader) {
      this(forwardedHeader, HttpHeader.FORWARDED.is(forwardedHeader));
   }

   public ThreadLimitHandler(@Name("forwardedHeader") String forwardedHeader, @Name("rfc7239") boolean rfc7239) {
      this._includeExcludeSet = new IncludeExcludeSet(InetAddressSet.class);
      this._remotes = new ConcurrentHashMap();
      this._threadLimit = 10;
      this._rfc7239 = rfc7239;
      this._forwardedHeader = forwardedHeader;
      this._enabled = true;
   }

   protected void doStart() throws Exception {
      super.doStart();
      LOG.info(String.format("ThreadLimitHandler enable=%b limit=%d include=%s", this._enabled, this._threadLimit, this._includeExcludeSet));
   }

   @ManagedAttribute("true if this handler is enabled")
   public boolean isEnabled() {
      return this._enabled;
   }

   public void setEnabled(boolean enabled) {
      this._enabled = enabled;
      LOG.info(String.format("ThreadLimitHandler enable=%b limit=%d include=%s", this._enabled, this._threadLimit, this._includeExcludeSet));
   }

   @ManagedAttribute("The maximum threads that can be dispatched per remote IP")
   public int getThreadLimit() {
      return this._threadLimit;
   }

   protected int getThreadLimit(String ip) {
      if (!this._includeExcludeSet.isEmpty()) {
         try {
            if (!this._includeExcludeSet.test(InetAddress.getByName(ip))) {
               LOG.debug("excluded {}", ip);
               return 0;
            }
         } catch (Exception e) {
            LOG.trace("IGNORED", e);
         }
      }

      return this._threadLimit;
   }

   public void setThreadLimit(int threadLimit) {
      if (threadLimit <= 0) {
         throw new IllegalArgumentException("limit must be >0");
      } else {
         this._threadLimit = threadLimit;
      }
   }

   @ManagedOperation("Include IP in thread limits")
   public void include(String inetAddressPattern) {
      this._includeExcludeSet.include((Object)inetAddressPattern);
   }

   @ManagedOperation("Exclude IP from thread limits")
   public void exclude(String inetAddressPattern) {
      this._includeExcludeSet.exclude((Object)inetAddressPattern);
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (!this._enabled) {
         super.handle(target, baseRequest, request, response);
      } else {
         final Remote remote = this.getRemote(baseRequest);
         if (remote == null) {
            super.handle(target, baseRequest, request, response);
         } else {
            request.getServletContext().addListener(new ServletRequestListener() {
               public void requestDestroyed(ServletRequestEvent sre) {
                  ThreadLimitHandler.this._remotes.computeIfPresent(remote._ip, (k, v) -> v._referenceCounter.release() ? null : v);
               }
            });
            Closeable permit = (Closeable)baseRequest.getAttribute("o.e.j.s.h.TLH.PASS");

            try {
               if (permit != null) {
                  baseRequest.removeAttribute("o.e.j.s.h.TLH.PASS");
               } else {
                  CompletableFuture<Closeable> futurePermit = remote.acquire();
                  if (!futurePermit.isDone()) {
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("Threadlimited {} {}", remote, target);
                     }

                     AsyncContext async = baseRequest.startAsync();
                     async.setTimeout(0L);
                     futurePermit.thenAccept((c) -> {
                        baseRequest.setAttribute("o.e.j.s.h.TLH.PASS", c);
                        async.dispatch();
                     });
                     return;
                  }

                  permit = (Closeable)futurePermit.get();
               }

               super.handle(target, baseRequest, request, response);
               return;
            } catch (ExecutionException | InterruptedException e) {
               throw new ServletException(e);
            } finally {
               if (permit != null) {
                  permit.close();
               }

            }
         }
      }

   }

   private Remote getRemote(Request baseRequest) {
      Remote remote = (Remote)baseRequest.getAttribute("o.e.j.s.h.TLH.REMOTE");
      if (remote != null) {
         return remote;
      } else {
         String ip = this.getRemoteIP(baseRequest);
         LOG.debug("ip={}", ip);
         if (ip == null) {
            return null;
         } else {
            int limit = this.getThreadLimit(ip);
            if (limit <= 0) {
               return null;
            } else {
               remote = (Remote)this._remotes.compute(ip, (k, v) -> {
                  if (v != null) {
                     v._referenceCounter.retain();
                     return v;
                  } else {
                     return new Remote(k, limit);
                  }
               });
               baseRequest.setAttribute("o.e.j.s.h.TLH.REMOTE", remote);
               return remote;
            }
         }
      }
   }

   protected String getRemoteIP(Request baseRequest) {
      if (this._forwardedHeader != null && !this._forwardedHeader.isEmpty()) {
         String remote = this._rfc7239 ? this.getForwarded(baseRequest) : this.getXForwardedFor(baseRequest);
         if (remote != null && !remote.isEmpty()) {
            return remote;
         }
      }

      InetSocketAddress inetAddr = baseRequest.getHttpChannel().getRemoteAddress();
      return inetAddr != null && inetAddr.getAddress() != null ? inetAddr.getAddress().getHostAddress() : null;
   }

   private String getForwarded(Request request) {
      RFC7239 rfc7239 = new RFC7239();

      for(HttpField field : request.getHttpFields()) {
         if (this._forwardedHeader.equalsIgnoreCase(field.getName())) {
            rfc7239.addValue(field.getValue());
         }
      }

      if (rfc7239.getFor() != null) {
         return (new HostPortHttpField(rfc7239.getFor())).getHost();
      } else {
         return null;
      }
   }

   private String getXForwardedFor(Request request) {
      String forwardedFor = null;

      for(HttpField field : request.getHttpFields()) {
         if (this._forwardedHeader.equalsIgnoreCase(field.getName())) {
            forwardedFor = field.getValue();
         }
      }

      if (forwardedFor != null && !forwardedFor.isEmpty()) {
         int comma = forwardedFor.lastIndexOf(44);
         return comma >= 0 ? forwardedFor.substring(comma + 1).trim() : forwardedFor;
      } else {
         return null;
      }
   }

   int getRemoteCount() {
      return this._remotes.size();
   }

   private static final class Remote implements Closeable {
      private final String _ip;
      private final int _limit;
      private final AutoLock _lock = new AutoLock();
      private final ReferenceCounter _referenceCounter = new ReferenceCounter();
      private int _permits;
      private Deque _queue = new ArrayDeque();
      private final CompletableFuture _permitted = CompletableFuture.completedFuture(this);

      public Remote(String ip, int limit) {
         this._ip = ip;
         this._limit = limit;
      }

      public CompletableFuture acquire() {
         try (AutoLock lock = this._lock.lock()) {
            if (this._permits < this._limit) {
               ++this._permits;
               return this._permitted;
            } else {
               CompletableFuture<Closeable> pass = new CompletableFuture();
               this._queue.addLast(pass);
               return pass;
            }
         }
      }

      public void close() {
         try (AutoLock lock = this._lock.lock()) {
            --this._permits;

            while(true) {
               CompletableFuture<Closeable> permit = (CompletableFuture)this._queue.pollFirst();
               if (permit == null) {
                  break;
               }

               if (permit.complete(this)) {
                  ++this._permits;
                  break;
               }
            }
         }

      }

      public String toString() {
         try (AutoLock lock = this._lock.lock()) {
            return String.format("R[ip=%s,p=%d,l=%d,q=%d]", this._ip, this._permits, this._limit, this._queue.size());
         }
      }
   }

   private static final class RFC7239 extends QuotedCSV {
      String _for;

      private RFC7239() {
         super(false);
      }

      String getFor() {
         return this._for;
      }

      protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue) {
         if (valueLength == 0 && paramValue > paramName) {
            String name = StringUtil.asciiToLowerCase(buffer.substring(paramName, paramValue - 1));
            if ("for".equalsIgnoreCase(name)) {
               String value = buffer.substring(paramValue);
               if ("unknown".equalsIgnoreCase(value)) {
                  this._for = null;
               } else {
                  this._for = value;
               }
            }
         }

      }
   }

   private static class ReferenceCounter {
      private final AtomicInteger references = new AtomicInteger(1);

      public void retain() {
         if (this.references.getAndUpdate((c) -> c == 0 ? 0 : c + 1) == 0) {
            throw new IllegalStateException("released " + String.valueOf(this));
         }
      }

      public boolean release() {
         int ref = this.references.updateAndGet((c) -> {
            if (c == 0) {
               throw new IllegalStateException("already released " + String.valueOf(this));
            } else {
               return c - 1;
            }
         });
         return ref == 0;
      }
   }
}
