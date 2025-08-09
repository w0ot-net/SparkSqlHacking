package org.sparkproject.jetty.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestWrapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;

public class ServletRequestHttpWrapper extends ServletRequestWrapper implements HttpServletRequest {
   public ServletRequestHttpWrapper(ServletRequest request) {
      super(request);
   }

   public String getAuthType() {
      return null;
   }

   public Cookie[] getCookies() {
      return null;
   }

   public long getDateHeader(String name) {
      return 0L;
   }

   public String getHeader(String name) {
      return null;
   }

   public Enumeration getHeaders(String name) {
      return null;
   }

   public Enumeration getHeaderNames() {
      return null;
   }

   public int getIntHeader(String name) {
      return 0;
   }

   public String getMethod() {
      return null;
   }

   public String getPathInfo() {
      return null;
   }

   public String getPathTranslated() {
      return null;
   }

   public String getContextPath() {
      return null;
   }

   public String getQueryString() {
      return null;
   }

   public String getRemoteUser() {
      return null;
   }

   public boolean isUserInRole(String role) {
      return false;
   }

   public Principal getUserPrincipal() {
      return null;
   }

   public String getRequestedSessionId() {
      return null;
   }

   public String getRequestURI() {
      return null;
   }

   public StringBuffer getRequestURL() {
      return null;
   }

   public String getServletPath() {
      return null;
   }

   public HttpSession getSession(boolean create) {
      return null;
   }

   public HttpSession getSession() {
      return null;
   }

   public boolean isRequestedSessionIdValid() {
      return false;
   }

   public boolean isRequestedSessionIdFromCookie() {
      return false;
   }

   public boolean isRequestedSessionIdFromURL() {
      return false;
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public boolean isRequestedSessionIdFromUrl() {
      return false;
   }

   public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
      return false;
   }

   public Part getPart(String name) throws IOException, ServletException {
      return null;
   }

   public Collection getParts() throws IOException, ServletException {
      return null;
   }

   public void login(String username, String password) throws ServletException {
   }

   public void logout() throws ServletException {
   }

   public String changeSessionId() {
      return null;
   }

   public HttpUpgradeHandler upgrade(Class handlerClass) throws IOException, ServletException {
      return null;
   }
}
