package org.sparkproject.jetty.server;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletResponseWrapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ServletResponseHttpWrapper extends ServletResponseWrapper implements HttpServletResponse {
   public ServletResponseHttpWrapper(ServletResponse response) {
      super(response);
   }

   public void addCookie(Cookie cookie) {
   }

   public boolean containsHeader(String name) {
      return false;
   }

   public String encodeURL(String url) {
      return null;
   }

   public String encodeRedirectURL(String url) {
      return null;
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public String encodeUrl(String url) {
      return null;
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public String encodeRedirectUrl(String url) {
      return null;
   }

   public void sendError(int sc, String msg) throws IOException {
   }

   public void sendError(int sc) throws IOException {
   }

   public void sendRedirect(String location) throws IOException {
   }

   public void setDateHeader(String name, long date) {
   }

   public void addDateHeader(String name, long date) {
   }

   public void setHeader(String name, String value) {
   }

   public void addHeader(String name, String value) {
   }

   public void setIntHeader(String name, int value) {
   }

   public void addIntHeader(String name, int value) {
   }

   public void setStatus(int sc) {
   }

   /** @deprecated */
   @Deprecated(
      since = "Servlet API 2.1"
   )
   public void setStatus(int sc, String sm) {
   }

   public String getHeader(String name) {
      return null;
   }

   public Collection getHeaderNames() {
      return null;
   }

   public Collection getHeaders(String name) {
      return null;
   }

   public int getStatus() {
      return 0;
   }
}
