package org.sparkproject.jetty.security.authentication;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.security.ServerAuthException;
import org.sparkproject.jetty.security.UserAuthentication;
import org.sparkproject.jetty.server.Authentication;
import org.sparkproject.jetty.server.UserIdentity;

public class BasicAuthenticator extends LoginAuthenticator {
   private Charset _charset;

   public Charset getCharset() {
      return this._charset;
   }

   public void setCharset(Charset charset) {
      this._charset = charset;
   }

   public String getAuthMethod() {
      return "BASIC";
   }

   public Authentication validateRequest(ServletRequest req, ServletResponse res, boolean mandatory) throws ServerAuthException {
      HttpServletRequest request = (HttpServletRequest)req;
      HttpServletResponse response = (HttpServletResponse)res;
      String credentials = request.getHeader(HttpHeader.AUTHORIZATION.asString());

      try {
         if (!mandatory) {
            return new DeferredAuthentication(this);
         } else {
            if (credentials != null) {
               int space = credentials.indexOf(32);
               if (space > 0) {
                  String method = credentials.substring(0, space);
                  if ("basic".equalsIgnoreCase(method)) {
                     credentials = credentials.substring(space + 1);
                     Charset charset = this.getCharset();
                     if (charset == null) {
                        charset = StandardCharsets.ISO_8859_1;
                     }

                     credentials = new String(Base64.getDecoder().decode(credentials), charset);
                     int i = credentials.indexOf(58);
                     if (i > 0) {
                        String username = credentials.substring(0, i);
                        String password = credentials.substring(i + 1);
                        UserIdentity user = this.login(username, password, request);
                        if (user != null) {
                           return new UserAuthentication(this.getAuthMethod(), user);
                        }
                     }
                  }
               }
            }

            if (DeferredAuthentication.isDeferred(response)) {
               return Authentication.UNAUTHENTICATED;
            } else {
               String value = "basic realm=\"" + this._loginService.getName() + "\"";
               Charset charset = this.getCharset();
               if (charset != null) {
                  value = value + ", charset=\"" + charset.name() + "\"";
               }

               response.setHeader(HttpHeader.WWW_AUTHENTICATE.asString(), value);
               response.sendError(401);
               return Authentication.SEND_CONTINUE;
            }
         }
      } catch (IOException e) {
         throw new ServerAuthException(e);
      }
   }

   public boolean secureResponse(ServletRequest req, ServletResponse res, boolean mandatory, Authentication.User validatedUser) throws ServerAuthException {
      return true;
   }
}
