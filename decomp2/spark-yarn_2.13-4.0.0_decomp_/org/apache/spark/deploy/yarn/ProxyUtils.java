package org.apache.spark.deploy.yarn;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.EnumSet;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet;
import org.apache.hadoop.yarn.webapp.hamlet2.HamletSpec;
import org.apache.hadoop.yarn.webapp.hamlet2.HamletImpl.EOpt;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;

public class ProxyUtils {
   private static final SparkLogger LOG = SparkLoggerFactory.getLogger(ProxyUtils.class);
   public static final String E_HTTP_HTTPS_ONLY = "This filter only works for HTTP/HTTPS";
   public static final String LOCATION = "Location";

   public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String target) throws IOException {
      LOG.debug("Redirecting {} {} to {}", new Object[]{request.getMethod(), request.getRequestURI(), target});
      String location = response.encodeRedirectURL(target);
      response.setStatus(302);
      response.setHeader("Location", location);
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      Page p = new Page(writer);
      ((Hamlet.HTML)((Hamlet.BODY)((Hamlet.HTML)p.html().head().title("Moved").__()).body().h1("Moved").div().__(new Object[]{"Content has moved "}).a(location, "here").__()).__()).__();
      writer.close();
   }

   public static void notFound(HttpServletResponse resp, String message) throws IOException {
      resp.setStatus(404);
      resp.setContentType("text/html; charset=UTF-8");
      Page p = new Page(resp.getWriter());
      p.html().h1(message).__();
   }

   public static void rejectNonHttpRequests(ServletRequest req) throws ServletException {
      if (!(req instanceof HttpServletRequest)) {
         throw new ServletException("This filter only works for HTTP/HTTPS");
      }
   }

   public static class __ implements HamletSpec.__ {
   }

   public static class Page extends Hamlet {
      Page(PrintWriter out) {
         super(out, 0, false);
      }

      public Hamlet.HTML html() {
         return new Hamlet.HTML(this, "html", (HamletSpec.__)null, EnumSet.of(EOpt.ENDTAG));
      }
   }
}
