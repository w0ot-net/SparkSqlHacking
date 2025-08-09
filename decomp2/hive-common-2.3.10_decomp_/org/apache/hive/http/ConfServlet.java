package org.apache.hive.http;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.hadoop.conf.Configuration;

public class ConfServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String FORMAT_JSON = "json";
   private static final String FORMAT_XML = "xml";
   private static final String FORMAT_PARAM = "format";

   private Configuration getConfFromContext() {
      Configuration conf = (Configuration)this.getServletContext().getAttribute("hive.conf");

      assert conf != null;

      return conf;
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (HttpServer.isInstrumentationAccessAllowed(this.getServletContext(), request, response)) {
         String format = request.getParameter("format");
         if (null == format) {
            format = "xml";
         }

         if ("xml".equals(format)) {
            response.setContentType("text/xml; charset=utf-8");
         } else if ("json".equals(format)) {
            response.setContentType("application/json; charset=utf-8");
         }

         Writer out = response.getWriter();

         try {
            writeResponse(this.getConfFromContext(), out, format);
         } catch (BadFormatException bfe) {
            response.sendError(400, bfe.getMessage());
         }

         out.close();
      }
   }

   static void writeResponse(Configuration conf, Writer out, String format) throws IOException, BadFormatException {
      if ("json".equals(format)) {
         Configuration.dumpConfiguration(conf, out);
      } else {
         if (!"xml".equals(format)) {
            throw new BadFormatException("Bad format: " + format);
         }

         conf.writeXml(out);
      }

   }

   public static class BadFormatException extends Exception {
      private static final long serialVersionUID = 1L;

      public BadFormatException(String msg) {
         super(msg);
      }
   }
}
