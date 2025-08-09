package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.URIUtil;

public class MovedContextHandler extends ContextHandler {
   final Redirector _redirector;
   String _newContextURL;
   boolean _discardPathInfo;
   boolean _discardQuery;
   boolean _permanent;
   String _expires;

   public MovedContextHandler() {
      this._redirector = new Redirector();
      this.setHandler(this._redirector);
      this.setAllowNullPathInfo(true);
   }

   public MovedContextHandler(HandlerContainer parent, String contextPath, String newContextURL) {
      super(parent, contextPath);
      this._newContextURL = newContextURL;
      this._redirector = new Redirector();
      this.setHandler(this._redirector);
   }

   public boolean isDiscardPathInfo() {
      return this._discardPathInfo;
   }

   public void setDiscardPathInfo(boolean discardPathInfo) {
      this._discardPathInfo = discardPathInfo;
   }

   public String getNewContextURL() {
      return this._newContextURL;
   }

   public void setNewContextURL(String newContextURL) {
      this._newContextURL = newContextURL;
   }

   public boolean isPermanent() {
      return this._permanent;
   }

   public void setPermanent(boolean permanent) {
      this._permanent = permanent;
   }

   public boolean isDiscardQuery() {
      return this._discardQuery;
   }

   public void setDiscardQuery(boolean discardQuery) {
      this._discardQuery = discardQuery;
   }

   public String getExpires() {
      return this._expires;
   }

   public void setExpires(String expires) {
      this._expires = expires;
   }

   private class Redirector extends AbstractHandler {
      public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         if (MovedContextHandler.this._newContextURL != null) {
            String path = MovedContextHandler.this._newContextURL;
            if (!MovedContextHandler.this._discardPathInfo && request.getPathInfo() != null) {
               path = URIUtil.addPaths(path, request.getPathInfo());
            }

            StringBuilder location = URIUtil.hasScheme(path) ? new StringBuilder() : baseRequest.getRootURL();
            location.append(path);
            if (!MovedContextHandler.this._discardQuery && request.getQueryString() != null) {
               location.append('?');
               String q = request.getQueryString();
               q = q.replaceAll("\r\n?&=", "!");
               location.append(q);
            }

            response.setHeader(HttpHeader.LOCATION.asString(), location.toString());
            if (MovedContextHandler.this._expires != null) {
               response.setHeader(HttpHeader.EXPIRES.asString(), MovedContextHandler.this._expires);
            }

            response.setStatus(MovedContextHandler.this._permanent ? 301 : 302);
            response.setContentLength(0);
            baseRequest.setHandled(true);
         }
      }
   }
}
