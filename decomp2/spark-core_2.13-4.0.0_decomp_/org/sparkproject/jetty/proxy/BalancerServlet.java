package org.sparkproject.jetty.proxy;

import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.util.URIUtil;

public class BalancerServlet extends ProxyServlet {
   private static final String BALANCER_MEMBER_PREFIX = "balancerMember.";
   private static final List FORBIDDEN_CONFIG_PARAMETERS;
   private static final List REVERSE_PROXY_HEADERS;
   private static final String JSESSIONID = "jsessionid";
   private static final String JSESSIONID_URL_PREFIX = "jsessionid=";
   private final List _balancerMembers = new ArrayList();
   private final AtomicLong counter = new AtomicLong();
   private boolean _stickySessions;
   private boolean _proxyPassReverse;

   public void init() throws ServletException {
      this.validateConfig();
      super.init();
      this.initStickySessions();
      this.initBalancers();
      this.initProxyPassReverse();
   }

   private void validateConfig() throws ServletException {
      for(String initParameterName : Collections.list(this.getServletConfig().getInitParameterNames())) {
         if (FORBIDDEN_CONFIG_PARAMETERS.contains(initParameterName)) {
            throw new UnavailableException(initParameterName + " not supported in " + this.getClass().getName());
         }
      }

   }

   private void initStickySessions() {
      this._stickySessions = Boolean.parseBoolean(this.getServletConfig().getInitParameter("stickySessions"));
   }

   private void initBalancers() throws ServletException {
      Set<BalancerMember> members = new HashSet();

      for(String balancerName : this.getBalancerNames()) {
         String memberProxyToParam = "balancerMember." + balancerName + ".proxyTo";
         String proxyTo = this.getServletConfig().getInitParameter(memberProxyToParam);
         if (proxyTo == null || proxyTo.trim().length() == 0) {
            throw new UnavailableException(memberProxyToParam + " parameter is empty.");
         }

         members.add(new BalancerMember(balancerName, proxyTo));
      }

      this._balancerMembers.addAll(members);
   }

   private void initProxyPassReverse() {
      this._proxyPassReverse = Boolean.parseBoolean(this.getServletConfig().getInitParameter("proxyPassReverse"));
   }

   private Set getBalancerNames() throws ServletException {
      Set<String> names = new HashSet();

      for(String initParameterName : Collections.list(this.getServletConfig().getInitParameterNames())) {
         if (initParameterName.startsWith("balancerMember.")) {
            int endOfNameIndex = initParameterName.lastIndexOf(".");
            if (endOfNameIndex <= "balancerMember.".length()) {
               throw new UnavailableException(initParameterName + " parameter does not provide a balancer member name");
            }

            names.add(initParameterName.substring("balancerMember.".length(), endOfNameIndex));
         }
      }

      return names;
   }

   protected String rewriteTarget(HttpServletRequest request) {
      BalancerMember balancerMember = this.selectBalancerMember(request);
      if (this._log.isDebugEnabled()) {
         this._log.debug("Selected {}", balancerMember);
      }

      String path = request.getRequestURI();
      String query = request.getQueryString();
      if (query != null) {
         path = path + "?" + query;
      }

      String var10000 = balancerMember.getProxyTo();
      return URI.create(var10000 + "/" + path).normalize().toString();
   }

   private BalancerMember selectBalancerMember(HttpServletRequest request) {
      if (this._stickySessions) {
         String name = this.getBalancerMemberNameFromSessionId(request);
         if (name != null) {
            BalancerMember balancerMember = this.findBalancerMemberByName(name);
            if (balancerMember != null) {
               return balancerMember;
            }
         }
      }

      int index = (int)(this.counter.getAndIncrement() % (long)this._balancerMembers.size());
      return (BalancerMember)this._balancerMembers.get(index);
   }

   private BalancerMember findBalancerMemberByName(String name) {
      for(BalancerMember balancerMember : this._balancerMembers) {
         if (balancerMember.getName().equals(name)) {
            return balancerMember;
         }
      }

      return null;
   }

   private String getBalancerMemberNameFromSessionId(HttpServletRequest request) {
      String name = this.getBalancerMemberNameFromSessionCookie(request);
      if (name == null) {
         name = this.getBalancerMemberNameFromURL(request);
      }

      return name;
   }

   private String getBalancerMemberNameFromSessionCookie(HttpServletRequest request) {
      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
         for(Cookie cookie : cookies) {
            if ("jsessionid".equalsIgnoreCase(cookie.getName())) {
               return this.extractBalancerMemberNameFromSessionId(cookie.getValue());
            }
         }
      }

      return null;
   }

   private String getBalancerMemberNameFromURL(HttpServletRequest request) {
      String requestURI = request.getRequestURI();
      int idx = requestURI.lastIndexOf(";");
      if (idx > 0) {
         String requestURISuffix = requestURI.substring(idx + 1);
         if (requestURISuffix.startsWith("jsessionid=")) {
            return this.extractBalancerMemberNameFromSessionId(requestURISuffix.substring("jsessionid=".length()));
         }
      }

      return null;
   }

   private String extractBalancerMemberNameFromSessionId(String sessionId) {
      int idx = sessionId.lastIndexOf(".");
      if (idx > 0) {
         String sessionIdSuffix = sessionId.substring(idx + 1);
         return sessionIdSuffix.length() > 0 ? sessionIdSuffix : null;
      } else {
         return null;
      }
   }

   protected String filterServerResponseHeader(HttpServletRequest request, Response serverResponse, String headerName, String headerValue) {
      if (this._proxyPassReverse && REVERSE_PROXY_HEADERS.contains(headerName)) {
         URI locationURI = URI.create(headerValue).normalize();
         if (locationURI.isAbsolute() && this.isBackendLocation(locationURI)) {
            StringBuilder newURI = URIUtil.newURIBuilder(request.getScheme(), request.getServerName(), request.getServerPort());
            String component = locationURI.getRawPath();
            if (component != null) {
               newURI.append(component);
            }

            component = locationURI.getRawQuery();
            if (component != null) {
               newURI.append('?').append(component);
            }

            component = locationURI.getRawFragment();
            if (component != null) {
               newURI.append('#').append(component);
            }

            return URI.create(newURI.toString()).normalize().toString();
         }
      }

      return headerValue;
   }

   private boolean isBackendLocation(URI locationURI) {
      for(BalancerMember balancerMember : this._balancerMembers) {
         URI backendURI = balancerMember.getBackendURI();
         if (backendURI.getHost().equals(locationURI.getHost()) && backendURI.getScheme().equals(locationURI.getScheme()) && backendURI.getPort() == locationURI.getPort()) {
            return true;
         }
      }

      return false;
   }

   public boolean validateDestination(String host, int port) {
      return true;
   }

   static {
      List<String> params = new LinkedList();
      params.add("hostHeader");
      params.add("whiteList");
      params.add("blackList");
      FORBIDDEN_CONFIG_PARAMETERS = Collections.unmodifiableList(params);
      params = new LinkedList();
      params.add("Location");
      params.add("Content-Location");
      params.add("URI");
      REVERSE_PROXY_HEADERS = Collections.unmodifiableList(params);
   }

   private static class BalancerMember {
      private final String _name;
      private final String _proxyTo;
      private final URI _backendURI;

      public BalancerMember(String name, String proxyTo) {
         this._name = name;
         this._proxyTo = proxyTo;
         this._backendURI = URI.create(this._proxyTo).normalize();
      }

      public String getName() {
         return this._name;
      }

      public String getProxyTo() {
         return this._proxyTo;
      }

      public URI getBackendURI() {
         return this._backendURI;
      }

      public String toString() {
         return String.format("%s[name=%s,proxyTo=%s]", this.getClass().getSimpleName(), this._name, this._proxyTo);
      }

      public int hashCode() {
         return this._name.hashCode();
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj == null) {
            return false;
         } else if (this.getClass() != obj.getClass()) {
            return false;
         } else {
            BalancerMember that = (BalancerMember)obj;
            return this._name.equals(that._name);
         }
      }
   }
}
