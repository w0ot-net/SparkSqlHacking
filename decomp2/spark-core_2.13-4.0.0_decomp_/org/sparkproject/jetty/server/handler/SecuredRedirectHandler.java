package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.server.HttpChannel;
import org.sparkproject.jetty.server.HttpConfiguration;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.URIUtil;

public class SecuredRedirectHandler extends HandlerWrapper {
   private final int _redirectCode;

   public SecuredRedirectHandler() {
      this(302);
   }

   public SecuredRedirectHandler(int code) {
      if (!HttpStatus.isRedirection(code)) {
         throw new IllegalArgumentException("Not a 3xx redirect code");
      } else {
         this._redirectCode = code;
      }
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      HttpChannel channel = baseRequest.getHttpChannel();
      if (!baseRequest.isSecure() && channel != null) {
         baseRequest.setHandled(true);
         HttpConfiguration httpConfig = channel.getHttpConfiguration();
         if (httpConfig == null) {
            response.sendError(403, "Missing HttpConfiguration");
         } else {
            int securePort = httpConfig.getSecurePort();
            if (securePort > 0) {
               String secureScheme = httpConfig.getSecureScheme();
               String url = URIUtil.newURI(secureScheme, baseRequest.getServerName(), securePort, baseRequest.getRequestURI(), baseRequest.getQueryString());
               response.setContentLength(0);
               baseRequest.getResponse().sendRedirect(this._redirectCode, url, true);
            } else {
               response.sendError(403, "HttpConfiguration.securePort not configured");
            }

         }
      } else {
         super.handle(target, baseRequest, request, response);
      }
   }
}
