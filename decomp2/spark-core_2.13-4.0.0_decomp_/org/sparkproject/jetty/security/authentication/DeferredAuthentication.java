package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.IdentityService;
import org.sparkproject.jetty.security.LoggedOutAuthentication;
import org.sparkproject.jetty.security.LoginService;
import org.sparkproject.jetty.security.SecurityHandler;
import org.sparkproject.jetty.security.ServerAuthException;
import org.sparkproject.jetty.security.UserAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.util.IO;

public class DeferredAuthentication implements Authentication.Deferred {
   private static final Logger LOG = LoggerFactory.getLogger(DeferredAuthentication.class);
   protected final LoginAuthenticator _authenticator;
   private Object _previousAssociation;
   static final HttpServletResponse __deferredResponse = new HttpServletResponse() {
      public void addCookie(Cookie cookie) {
      }

      public void addDateHeader(String name, long date) {
      }

      public void addHeader(String name, String value) {
      }

      public void addIntHeader(String name, int value) {
      }

      public boolean containsHeader(String name) {
         return false;
      }

      public String encodeRedirectURL(String url) {
         return null;
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.1"
      )
      public String encodeRedirectUrl(String url) {
         return null;
      }

      public String encodeURL(String url) {
         return null;
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.1"
      )
      public String encodeUrl(String url) {
         return null;
      }

      public void sendError(int sc) throws IOException {
      }

      public void sendError(int sc, String msg) throws IOException {
      }

      public void sendRedirect(String location) throws IOException {
      }

      public void setDateHeader(String name, long date) {
      }

      public void setHeader(String name, String value) {
      }

      public void setIntHeader(String name, int value) {
      }

      public void setStatus(int sc) {
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.1"
      )
      public void setStatus(int sc, String sm) {
      }

      public void flushBuffer() throws IOException {
      }

      public int getBufferSize() {
         return 1024;
      }

      public String getCharacterEncoding() {
         return null;
      }

      public String getContentType() {
         return null;
      }

      public Locale getLocale() {
         return null;
      }

      public ServletOutputStream getOutputStream() throws IOException {
         return DeferredAuthentication.__nullOut;
      }

      public PrintWriter getWriter() throws IOException {
         return IO.getNullPrintWriter();
      }

      public boolean isCommitted() {
         return true;
      }

      public void reset() {
      }

      public void resetBuffer() {
      }

      public void setBufferSize(int size) {
      }

      public void setCharacterEncoding(String charset) {
      }

      public void setContentLength(int len) {
      }

      public void setContentLengthLong(long len) {
      }

      public void setContentType(String type) {
      }

      public void setLocale(Locale loc) {
      }

      public Collection getHeaderNames() {
         return Collections.emptyList();
      }

      public String getHeader(String arg0) {
         return null;
      }

      public Collection getHeaders(String arg0) {
         return Collections.emptyList();
      }

      public int getStatus() {
         return 0;
      }
   };
   private static ServletOutputStream __nullOut = new ServletOutputStream() {
      public void write(int b) throws IOException {
      }

      public void print(String s) throws IOException {
      }

      public void println(String s) throws IOException {
      }

      public void setWriteListener(WriteListener writeListener) {
      }

      public boolean isReady() {
         return false;
      }
   };

   public DeferredAuthentication(LoginAuthenticator authenticator) {
      if (authenticator == null) {
         throw new NullPointerException("No Authenticator");
      } else {
         this._authenticator = authenticator;
      }
   }

   public Authentication authenticate(ServletRequest request) {
      try {
         Authentication authentication = this._authenticator.validateRequest(request, __deferredResponse, true);
         if (authentication != null && authentication instanceof Authentication.User && !(authentication instanceof Authentication.ResponseSent)) {
            LoginService loginService = this._authenticator.getLoginService();
            IdentityService identityService = loginService.getIdentityService();
            if (identityService != null) {
               this._previousAssociation = identityService.associate(((Authentication.User)authentication).getUserIdentity());
            }

            return authentication;
         }
      } catch (ServerAuthException e) {
         LOG.debug("Unable to authenticate {}", request, e);
      }

      return this;
   }

   public Authentication authenticate(ServletRequest request, ServletResponse response) {
      try {
         LoginService loginService = this._authenticator.getLoginService();
         IdentityService identityService = loginService.getIdentityService();
         Authentication authentication = this._authenticator.validateRequest(request, response, true);
         if (authentication instanceof Authentication.User && identityService != null) {
            this._previousAssociation = identityService.associate(((Authentication.User)authentication).getUserIdentity());
         }

         return authentication;
      } catch (ServerAuthException e) {
         LOG.debug("Unable to authenticate {}", request, e);
         return this;
      }
   }

   public Authentication login(String username, Object password, ServletRequest request) {
      if (username == null) {
         return null;
      } else {
         UserIdentity identity = this._authenticator.login(username, password, request);
         if (identity != null) {
            IdentityService identityService = this._authenticator.getLoginService().getIdentityService();
            UserAuthentication authentication = new UserAuthentication("API", identity);
            if (identityService != null) {
               this._previousAssociation = identityService.associate(identity);
            }

            return authentication;
         } else {
            return null;
         }
      }
   }

   public Authentication logout(ServletRequest request) {
      SecurityHandler security = SecurityHandler.getCurrentSecurityHandler();
      if (security != null) {
         security.logout((Authentication.User)null);
         this._authenticator.logout(request);
         return new LoggedOutAuthentication(this._authenticator);
      } else {
         return Authentication.UNAUTHENTICATED;
      }
   }

   public Object getPreviousAssociation() {
      return this._previousAssociation;
   }

   public static boolean isDeferred(HttpServletResponse response) {
      return response == __deferredResponse;
   }
}
