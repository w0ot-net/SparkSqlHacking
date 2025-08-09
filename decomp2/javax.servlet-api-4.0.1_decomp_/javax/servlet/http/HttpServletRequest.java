package javax.servlet.http;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

public interface HttpServletRequest extends ServletRequest {
   String BASIC_AUTH = "BASIC";
   String FORM_AUTH = "FORM";
   String CLIENT_CERT_AUTH = "CLIENT_CERT";
   String DIGEST_AUTH = "DIGEST";

   String getAuthType();

   Cookie[] getCookies();

   long getDateHeader(String var1);

   String getHeader(String var1);

   Enumeration getHeaders(String var1);

   Enumeration getHeaderNames();

   int getIntHeader(String var1);

   default HttpServletMapping getHttpServletMapping() {
      return new HttpServletMapping() {
         public String getMatchValue() {
            return "";
         }

         public String getPattern() {
            return "";
         }

         public String getServletName() {
            return "";
         }

         public MappingMatch getMappingMatch() {
            return null;
         }

         public String toString() {
            return "MappingImpl{matchValue=" + this.getMatchValue() + ", pattern=" + this.getPattern() + ", servletName=" + this.getServletName() + ", mappingMatch=" + this.getMappingMatch() + "} HttpServletRequest {" + HttpServletRequest.this.toString() + '}';
         }
      };
   }

   String getMethod();

   String getPathInfo();

   String getPathTranslated();

   default PushBuilder newPushBuilder() {
      return null;
   }

   String getContextPath();

   String getQueryString();

   String getRemoteUser();

   boolean isUserInRole(String var1);

   Principal getUserPrincipal();

   String getRequestedSessionId();

   String getRequestURI();

   StringBuffer getRequestURL();

   String getServletPath();

   HttpSession getSession(boolean var1);

   HttpSession getSession();

   String changeSessionId();

   boolean isRequestedSessionIdValid();

   boolean isRequestedSessionIdFromCookie();

   boolean isRequestedSessionIdFromURL();

   /** @deprecated */
   @Deprecated
   boolean isRequestedSessionIdFromUrl();

   boolean authenticate(HttpServletResponse var1) throws IOException, ServletException;

   void login(String var1, String var2) throws ServletException;

   void logout() throws ServletException;

   Collection getParts() throws IOException, ServletException;

   Part getPart(String var1) throws IOException, ServletException;

   HttpUpgradeHandler upgrade(Class var1) throws IOException, ServletException;

   default Map getTrailerFields() {
      return Collections.emptyMap();
   }

   default boolean isTrailerFieldsReady() {
      return true;
   }
}
