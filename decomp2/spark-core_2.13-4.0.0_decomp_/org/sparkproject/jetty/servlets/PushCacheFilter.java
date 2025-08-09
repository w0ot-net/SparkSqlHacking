package org.sparkproject.jetty.servlets;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.PushBuilder;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;

/** @deprecated */
@Deprecated
@ManagedObject("Push cache based on the HTTP 'Referer' header")
public class PushCacheFilter implements Filter {
   private static final Logger LOG = LoggerFactory.getLogger(PushCacheFilter.class);
   private final Set _ports = new HashSet();
   private final Set _hosts = new HashSet();
   private final ConcurrentMap _cache = new ConcurrentHashMap();
   private long _associatePeriod = 4000L;
   private int _maxAssociations = 16;
   private long _renew = NanoTime.now();
   private boolean _useQueryInKey;

   public PushCacheFilter() {
      LOG.warn(PushCacheFilter.class.getSimpleName() + " is an example class not suitable for production.");
   }

   public void init(FilterConfig config) throws ServletException {
      String associatePeriod = config.getInitParameter("associatePeriod");
      if (associatePeriod != null) {
         this._associatePeriod = Long.parseLong(associatePeriod);
      }

      String maxAssociations = config.getInitParameter("maxAssociations");
      if (maxAssociations != null) {
         this._maxAssociations = Integer.parseInt(maxAssociations);
      }

      String hosts = config.getInitParameter("hosts");
      if (hosts != null) {
         Collections.addAll(this._hosts, StringUtil.csvSplit(hosts));
      }

      String ports = config.getInitParameter("ports");
      if (ports != null) {
         for(String p : StringUtil.csvSplit(ports)) {
            this._ports.add(Integer.parseInt(p));
         }
      }

      this._useQueryInKey = Boolean.parseBoolean(config.getInitParameter("useQueryInKey"));
      config.getServletContext().setAttribute(config.getFilterName(), this);
      if (LOG.isDebugEnabled()) {
         LOG.debug("period={} max={} hosts={} ports={}", new Object[]{this._associatePeriod, this._maxAssociations, this._hosts, this._ports});
      }

   }

   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest request = (HttpServletRequest)req;
      PushBuilder pushBuilder = request.newPushBuilder();
      if (HttpVersion.fromString(request.getProtocol()).getVersion() >= 20 && HttpMethod.GET.is(request.getMethod()) && pushBuilder != null) {
         long now = NanoTime.now();
         boolean conditional = false;
         String referrer = null;

         for(String headerName : Collections.list(request.getHeaderNames())) {
            if (HttpHeader.IF_MATCH.is(headerName) || HttpHeader.IF_MODIFIED_SINCE.is(headerName) || HttpHeader.IF_NONE_MATCH.is(headerName) || HttpHeader.IF_UNMODIFIED_SINCE.is(headerName)) {
               conditional = true;
               break;
            }

            if (HttpHeader.REFERER.is(headerName)) {
               referrer = request.getHeader(headerName);
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("{} {} referrer={} conditional={}", new Object[]{request.getMethod(), request.getRequestURI(), referrer, conditional});
         }

         String path = request.getRequestURI();
         String query = request.getQueryString();
         if (this._useQueryInKey && query != null) {
            path = path + "?" + query;
         }

         if (referrer != null) {
            HttpURI referrerURI = HttpURI.from(referrer);
            String host = referrerURI.getHost();
            int port = referrerURI.getPort();
            if (port <= 0) {
               String scheme = referrerURI.getScheme();
               if (scheme != null) {
                  port = HttpScheme.HTTPS.is(scheme) ? 443 : 80;
               } else {
                  port = request.isSecure() ? 443 : 80;
               }
            }

            boolean referredFromHere = !this._hosts.isEmpty() ? this._hosts.contains(host) : host.equals(request.getServerName());
            referredFromHere &= !this._ports.isEmpty() ? this._ports.contains(port) : port == request.getServerPort();
            if (referredFromHere) {
               if (HttpMethod.GET.is(request.getMethod())) {
                  String referrerPath = this._useQueryInKey ? referrerURI.getPathQuery() : referrerURI.getPath();
                  if (referrerPath == null) {
                     referrerPath = "/";
                  }

                  if (referrerPath.startsWith(request.getContextPath() + "/")) {
                     if (!referrerPath.equals(path)) {
                        PrimaryResource primaryResource = (PrimaryResource)this._cache.get(referrerPath);
                        if (primaryResource != null) {
                           long primaryNanoTime = primaryResource._nanoTime.get();
                           if (primaryNanoTime != 0L) {
                              if (NanoTime.millisElapsed(primaryNanoTime, now) < this._associatePeriod) {
                                 Set<String> associated = primaryResource._associated;
                                 if (associated.size() <= this._maxAssociations) {
                                    if (associated.add(path) && LOG.isDebugEnabled()) {
                                       LOG.debug("Associated {} to {}", path, referrerPath);
                                    }
                                 } else if (LOG.isDebugEnabled()) {
                                    LOG.debug("Not associated {} to {}, exceeded max associations of {}", new Object[]{path, referrerPath, this._maxAssociations});
                                 }
                              } else if (LOG.isDebugEnabled()) {
                                 LOG.debug("Not associated {} to {}, outside associate period of {}ms", new Object[]{path, referrerPath, this._associatePeriod});
                              }
                           }
                        }
                     } else if (LOG.isDebugEnabled()) {
                        LOG.debug("Not associated {} to {}, referring to self", path, referrerPath);
                     }
                  } else if (LOG.isDebugEnabled()) {
                     LOG.debug("Not associated {} to {}, different context", path, referrerPath);
                  }
               }
            } else if (LOG.isDebugEnabled()) {
               LOG.debug("External referrer {}", referrer);
            }
         }

         PrimaryResource primaryResource = (PrimaryResource)this._cache.get(path);
         if (primaryResource == null) {
            PrimaryResource r = new PrimaryResource();
            PrimaryResource var25 = (PrimaryResource)this._cache.putIfAbsent(path, r);
            primaryResource = var25 == null ? r : var25;
            primaryResource._nanoTime.compareAndSet(0L, now);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Cached primary resource {}", path);
            }
         } else {
            long last = primaryResource._nanoTime.get();
            if (NanoTime.isBefore(last, this._renew) && primaryResource._nanoTime.compareAndSet(last, now)) {
               primaryResource._associated.clear();
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Clear associated resources for {}", path);
               }
            }
         }

         if (!conditional && !primaryResource._associated.isEmpty()) {
            Queue<PrimaryResource> queue = new ArrayDeque();
            queue.offer(primaryResource);

            while(!queue.isEmpty()) {
               PrimaryResource parent = (PrimaryResource)queue.poll();

               for(String childPath : parent._associated) {
                  PrimaryResource child = (PrimaryResource)this._cache.get(childPath);
                  if (child != null) {
                     queue.offer(child);
                  }

                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Pushing {} for {}", childPath, path);
                  }

                  pushBuilder.path(childPath).push();
               }
            }
         }

         chain.doFilter(request, resp);
      } else {
         chain.doFilter(req, resp);
      }
   }

   public void destroy() {
      this.clearPushCache();
   }

   @ManagedAttribute("The push cache contents")
   public Map getPushCache() {
      Map<String, String> result = new HashMap();

      for(Map.Entry entry : this._cache.entrySet()) {
         PrimaryResource resource = (PrimaryResource)entry.getValue();
         String value = String.format("size=%d: %s", resource._associated.size(), new TreeSet(resource._associated));
         result.put((String)entry.getKey(), value);
      }

      return result;
   }

   @ManagedOperation(
      value = "Renews the push cache contents",
      impact = "ACTION"
   )
   public void renewPushCache() {
      this._renew = NanoTime.now();
   }

   @ManagedOperation(
      value = "Clears the push cache contents",
      impact = "ACTION"
   )
   public void clearPushCache() {
      this._cache.clear();
   }

   private static class PrimaryResource {
      private final Set _associated = Collections.newSetFromMap(new ConcurrentHashMap());
      private final AtomicLong _nanoTime = new AtomicLong();
   }
}
