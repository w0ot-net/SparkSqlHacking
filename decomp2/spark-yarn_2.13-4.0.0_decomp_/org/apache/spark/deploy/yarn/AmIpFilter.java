package org.apache.spark.deploy.yarn;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.classification.VisibleForTesting;
import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Time;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

@Public
public class AmIpFilter implements Filter {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(AmIpFilter.class);
   /** @deprecated */
   @Deprecated
   public static final String PROXY_HOST = "PROXY_HOST";
   /** @deprecated */
   @Deprecated
   public static final String PROXY_URI_BASE = "PROXY_URI_BASE";
   public static final String PROXY_HOSTS = "PROXY_HOSTS";
   public static final String PROXY_HOSTS_DELIMITER = ",";
   public static final String PROXY_URI_BASES = "PROXY_URI_BASES";
   public static final String PROXY_URI_BASES_DELIMITER = ",";
   private static final String PROXY_PATH = "/proxy";
   private static final String RM_HA_URLS = "RM_HA_URLS";
   public static final String PROXY_USER_COOKIE_NAME = "proxy-user";
   private static long updateInterval;
   private String[] proxyHosts;
   private Set proxyAddresses = null;
   private long lastUpdate;
   @VisibleForTesting
   Map proxyUriBases;
   String[] rmUrls = null;

   public void init(FilterConfig conf) throws ServletException {
      if (conf.getInitParameter("PROXY_HOST") != null && conf.getInitParameter("PROXY_URI_BASE") != null) {
         this.proxyHosts = new String[]{conf.getInitParameter("PROXY_HOST")};
         this.proxyUriBases = new HashMap(1);
         this.proxyUriBases.put("dummy", conf.getInitParameter("PROXY_URI_BASE"));
      } else {
         this.proxyHosts = conf.getInitParameter("PROXY_HOSTS").split(",");
         String[] proxyUriBasesArr = conf.getInitParameter("PROXY_URI_BASES").split(",");
         this.proxyUriBases = new HashMap(proxyUriBasesArr.length);

         for(String proxyUriBase : proxyUriBasesArr) {
            try {
               URL url = (new URI(proxyUriBase)).toURL();
               this.proxyUriBases.put(url.getHost() + ":" + url.getPort(), proxyUriBase);
            } catch (URISyntaxException | MalformedURLException e) {
               LOG.warn(proxyUriBase + " does not appear to be a valid URL", e);
            }
         }
      }

      if (conf.getInitParameter("RM_HA_URLS") != null) {
         this.rmUrls = conf.getInitParameter("RM_HA_URLS").split(",");
      }

   }

   protected Set getProxyAddresses() throws ServletException {
      long now = Time.monotonicNow();
      synchronized(this) {
         if (this.proxyAddresses == null || this.lastUpdate + updateInterval <= now) {
            this.proxyAddresses = new HashSet();

            for(String proxyHost : this.proxyHosts) {
               try {
                  for(InetAddress add : InetAddress.getAllByName(proxyHost)) {
                     LOG.debug("proxy address is: {}", add.getHostAddress());
                     this.proxyAddresses.add(add.getHostAddress());
                  }

                  this.lastUpdate = now;
               } catch (UnknownHostException e) {
                  LOG.warn("Could not locate " + proxyHost + " - skipping", e);
               }
            }

            if (this.proxyAddresses.isEmpty()) {
               throw new ServletException("Could not locate any of the proxy hosts");
            }
         }

         return this.proxyAddresses;
      }
   }

   public void destroy() {
   }

   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      ProxyUtils.rejectNonHttpRequests(req);
      HttpServletRequest httpReq = (HttpServletRequest)req;
      HttpServletResponse httpResp = (HttpServletResponse)resp;
      LOG.debug("Remote address for request is: {}", httpReq.getRemoteAddr());
      if (!this.getProxyAddresses().contains(httpReq.getRemoteAddr())) {
         StringBuilder redirect = new StringBuilder(this.findRedirectUrl());
         redirect.append(httpReq.getRequestURI());
         int insertPoint = redirect.indexOf("/proxy");
         if (insertPoint >= 0) {
            insertPoint += "/proxy".length();
            redirect.insert(insertPoint, "/redirect");
         }

         String queryString = httpReq.getQueryString();
         if (queryString != null && !queryString.isEmpty()) {
            redirect.append("?");
            redirect.append(queryString);
         }

         ProxyUtils.sendRedirect(httpReq, httpResp, redirect.toString());
      } else {
         String user = null;
         if (httpReq.getCookies() != null) {
            for(Cookie c : httpReq.getCookies()) {
               if ("proxy-user".equals(c.getName())) {
                  user = c.getValue();
                  break;
               }
            }
         }

         if (user == null) {
            LOG.debug("Could not find {} cookie, so user will not be set", "proxy-user");
            chain.doFilter(req, resp);
         } else {
            AmIpPrincipal principal = new AmIpPrincipal(user);
            ServletRequest requestWrapper = new AmIpServletRequestWrapper(httpReq, principal);
            chain.doFilter(requestWrapper, resp);
         }
      }

   }

   @VisibleForTesting
   public String findRedirectUrl() throws ServletException {
      String addr = null;
      if (this.proxyUriBases.size() == 1) {
         addr = (String)this.proxyUriBases.values().iterator().next();
      } else if (this.rmUrls != null) {
         for(String url : this.rmUrls) {
            String host = (String)this.proxyUriBases.get(url);
            if (this.isValidUrl(host)) {
               addr = host;
               break;
            }
         }
      }

      if (addr == null) {
         throw new ServletException("Could not determine the proxy server for redirection");
      } else {
         return addr;
      }
   }

   @VisibleForTesting
   public boolean isValidUrl(String url) {
      boolean isValid = false;

      try {
         HttpURLConnection conn = (HttpURLConnection)(new URI(url)).toURL().openConnection();
         conn.connect();
         isValid = conn.getResponseCode() == 200;
         if (!isValid && UserGroupInformation.isSecurityEnabled()) {
            isValid = conn.getResponseCode() == 401 || conn.getResponseCode() == 403;
            return isValid;
         }
      } catch (Exception e) {
         LOG.warn("Failed to connect to " + url + ": " + e.toString());
      }

      return isValid;
   }

   @VisibleForTesting
   protected static void setUpdateInterval(long updateInterval) {
      AmIpFilter.updateInterval = updateInterval;
   }

   static {
      updateInterval = TimeUnit.MINUTES.toMillis(5L);
   }
}
