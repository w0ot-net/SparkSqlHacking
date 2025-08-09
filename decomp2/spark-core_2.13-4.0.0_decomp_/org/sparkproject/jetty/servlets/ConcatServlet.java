package org.sparkproject.jetty.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.sparkproject.jetty.util.URIUtil;

/** @deprecated */
@Deprecated
public class ConcatServlet extends HttpServlet {
   private boolean _development;
   private long _lastModified;

   public void init() throws ServletException {
      this._lastModified = System.currentTimeMillis();
      this._development = Boolean.parseBoolean(this.getInitParameter("development"));
   }

   protected long getLastModified(HttpServletRequest req) {
      return this._development ? -1L : this._lastModified;
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String query = request.getQueryString();
      if (query == null) {
         response.sendError(204);
      } else {
         List<RequestDispatcher> dispatchers = new ArrayList();
         String[] parts = query.split("\\&");
         String type = null;
         String[] var7 = parts;
         int var8 = parts.length;
         int var9 = 0;

         while(true) {
            if (var9 < var8) {
               String part = var7[var9];
               String path = URIUtil.canonicalPath(URIUtil.decodePath(part));
               if (path == null) {
                  response.sendError(404);
                  return;
               }

               if (!this.startsWith(path, "/WEB-INF/") && !this.startsWith(path, "/META-INF/")) {
                  String t = this.getServletContext().getMimeType(path);
                  if (t != null) {
                     if (type == null) {
                        type = t;
                     } else if (!type.equals(t)) {
                        response.sendError(415);
                        return;
                     }
                  }

                  RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(part);
                  if (dispatcher != null) {
                     dispatchers.add(dispatcher);
                  }

                  ++var9;
                  continue;
               }

               response.sendError(404);
               return;
            }

            if (type != null) {
               response.setContentType(type);
            }

            for(RequestDispatcher dispatcher : dispatchers) {
               dispatcher.include(request, response);
            }

            return;
         }
      }
   }

   private boolean startsWith(String path, String prefix) {
      return prefix.regionMatches(true, 0, path, 0, prefix.length());
   }
}
