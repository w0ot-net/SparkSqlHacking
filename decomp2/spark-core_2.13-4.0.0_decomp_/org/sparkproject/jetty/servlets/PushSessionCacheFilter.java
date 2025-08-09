package org.sparkproject.jetty.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.PushBuilder;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.util.NanoTime;

/** @deprecated */
@Deprecated
public class PushSessionCacheFilter implements Filter {
   private static final String RESPONSE_ATTR = "PushSessionCacheFilter.response";
   private static final String TARGET_ATTR = "PushSessionCacheFilter.target";
   private static final String TIMESTAMP_ATTR = "PushSessionCacheFilter.timestamp";
   private static final Logger LOG = LoggerFactory.getLogger(PushSessionCacheFilter.class);
   private final ConcurrentMap _cache = new ConcurrentHashMap();
   private long _associateDelay = 5000L;

   public PushSessionCacheFilter() {
      LOG.warn(PushSessionCacheFilter.class.getSimpleName() + " is an example class not suitable for production.");
   }

   public void init(FilterConfig config) throws ServletException {
      if (config.getInitParameter("associateDelay") != null) {
         this._associateDelay = Long.parseLong(config.getInitParameter("associateDelay"));
      }

      config.getServletContext().addListener(new ServletRequestListener() {
         public void requestDestroyed(ServletRequestEvent sre) {
            HttpServletRequest request = (HttpServletRequest)sre.getServletRequest();
            Target target = (Target)request.getAttribute("PushSessionCacheFilter.target");
            if (target != null) {
               HttpServletResponse response = (HttpServletResponse)request.getAttribute("PushSessionCacheFilter.response");
               target._etag = response.getHeader(HttpHeader.ETAG.asString());
               target._lastModified = response.getHeader(HttpHeader.LAST_MODIFIED.asString());
               if (PushSessionCacheFilter.LOG.isDebugEnabled()) {
                  PushSessionCacheFilter.LOG.debug("Served {} for {}", response.getStatus(), request.getRequestURI());
               }

               String referer = request.getHeader(HttpHeader.REFERER.asString());
               if (referer != null) {
                  HttpURI refererUri = HttpURI.from(referer);
                  if (request.getServerName().equals(refererUri.getHost())) {
                     Target refererTarget = (Target)PushSessionCacheFilter.this._cache.get(refererUri.getPath());
                     if (refererTarget != null) {
                        HttpSession session = request.getSession();
                        ConcurrentHashMap<String, Long> timestamps = (ConcurrentHashMap)session.getAttribute("PushSessionCacheFilter.timestamp");
                        Long last = (Long)timestamps.get(refererTarget._path);
                        if (last != null && NanoTime.millisSince(last) < PushSessionCacheFilter.this._associateDelay && refererTarget._associated.putIfAbsent(target._path, target) == null && PushSessionCacheFilter.LOG.isDebugEnabled()) {
                           PushSessionCacheFilter.LOG.debug("ASSOCIATE {}->{}", refererTarget._path, target._path);
                        }
                     }
                  }
               }

            }
         }

         public void requestInitialized(ServletRequestEvent sre) {
         }
      });
   }

   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      req.setAttribute("PushSessionCacheFilter.response", resp);
      HttpServletRequest request = (HttpServletRequest)req;
      String uri = request.getRequestURI();
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} {}", request.getMethod(), uri);
      }

      HttpSession session = request.getSession(true);
      Target target = (Target)this._cache.get(uri);
      if (target == null) {
         Target t = new Target(uri);
         target = (Target)this._cache.putIfAbsent(uri, t);
         target = target == null ? t : target;
      }

      request.setAttribute("PushSessionCacheFilter.target", target);
      ConcurrentHashMap<String, Long> timestamps = (ConcurrentHashMap)session.getAttribute("PushSessionCacheFilter.timestamp");
      if (timestamps == null) {
         timestamps = new ConcurrentHashMap();
         session.setAttribute("PushSessionCacheFilter.timestamp", timestamps);
      }

      timestamps.put(uri, NanoTime.now());
      PushBuilder builder = request.newPushBuilder();
      if (builder != null && !target._associated.isEmpty()) {
         boolean conditional = request.getHeader(HttpHeader.IF_NONE_MATCH.asString()) != null || request.getHeader(HttpHeader.IF_MODIFIED_SINCE.asString()) != null;
         Queue<Target> queue = new ArrayDeque();
         queue.offer(target);

         while(!queue.isEmpty()) {
            Target parent = (Target)queue.poll();
            builder.addHeader("X-Pusher", PushSessionCacheFilter.class.toString());

            for(Target child : parent._associated.values()) {
               queue.offer(child);
               String path = child._path;
               if (LOG.isDebugEnabled()) {
                  LOG.debug("PUSH {} <- {}", path, uri);
               }

               builder.path(path).setHeader(HttpHeader.IF_NONE_MATCH.asString(), conditional ? child._etag : null).setHeader(HttpHeader.IF_MODIFIED_SINCE.asString(), conditional ? child._lastModified : null);
            }
         }
      }

      chain.doFilter(req, resp);
   }

   public void destroy() {
      this._cache.clear();
   }

   private static class Target {
      private final String _path;
      private final ConcurrentMap _associated = new ConcurrentHashMap();
      private volatile String _etag;
      private volatile String _lastModified;

      private Target(String path) {
         this._path = path;
      }

      public String toString() {
         return String.format("Target{p=%s,e=%s,m=%s,a=%d}", this._path, this._etag, this._lastModified, this._associated.size());
      }
   }
}
