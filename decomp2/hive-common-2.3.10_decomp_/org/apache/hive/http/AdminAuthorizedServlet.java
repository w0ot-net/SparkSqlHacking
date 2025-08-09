package org.apache.hive.http;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.servlet.DefaultServlet;

public class AdminAuthorizedServlet extends DefaultServlet {
   private static final long serialVersionUID = 1L;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (HttpServer.hasAdministratorAccess(this.getServletContext(), request, response)) {
         super.doGet(request, response);
      }

   }
}
