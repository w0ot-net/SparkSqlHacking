package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.server.AbstractConnector;
import org.sparkproject.jetty.server.Connector;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.util.DateCache;
import org.sparkproject.jetty.util.RolloverFileOutputStream;

public class DebugHandler extends HandlerWrapper implements Connection.Listener {
   private DateCache _date;
   private OutputStream _out;
   private PrintStream _print;

   public DebugHandler() {
      this._date = new DateCache("HH:mm:ss", Locale.US);
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Response base_response = baseRequest.getResponse();
      Thread thread = Thread.currentThread();
      String old_name = thread.getName();
      boolean retry = false;
      String name = (String)request.getAttribute("org.sparkproject.jetty.thread.name");
      if (name == null) {
         name = old_name + ":" + baseRequest.getOriginalURI();
      } else {
         retry = true;
      }

      String ex = null;

      try {
         if (retry) {
            this.print(name, "RESUME");
         } else {
            String var10002 = baseRequest.getRemoteAddr();
            this.print(name, "REQUEST " + var10002 + " " + request.getMethod() + " " + baseRequest.getHeader("Cookie") + "; " + baseRequest.getHeader("User-Agent"));
         }

         thread.setName(name);
         this.getHandler().handle(target, baseRequest, request, response);
      } catch (IOException ioe) {
         ex = ioe.toString();
         throw ioe;
      } catch (ServletException servletEx) {
         String var10000 = servletEx.toString();
         ex = var10000 + ":" + String.valueOf(servletEx.getCause());
         throw servletEx;
      } catch (RuntimeException rte) {
         ex = rte.toString();
         throw rte;
      } catch (Error e) {
         ex = e.toString();
         throw e;
      } finally {
         thread.setName(old_name);
         if (baseRequest.getHttpChannelState().isAsyncStarted()) {
            request.setAttribute("org.sparkproject.jetty.thread.name", name);
            this.print(name, "ASYNC");
         } else {
            int var23 = base_response.getStatus();
            this.print(name, "RESPONSE " + var23 + (ex == null ? "" : "/" + ex) + " " + base_response.getContentType());
         }

      }

   }

   private void print(String name, String message) {
      long now = System.currentTimeMillis();
      String d = this._date.format(now);
      int ms = (int)(now % 1000L);
      this._print.println(d + (ms > 99 ? "." : (ms > 9 ? ".0" : ".00")) + ms + ":" + name + " " + message);
   }

   protected void doStart() throws Exception {
      if (this._out == null) {
         this._out = new RolloverFileOutputStream("./logs/yyyy_mm_dd.debug.log", true);
      }

      this._print = new PrintStream(this._out);

      for(Connector connector : this.getServer().getConnectors()) {
         if (connector instanceof AbstractConnector) {
            ((AbstractConnector)connector).addBean(this, false);
         }
      }

      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
      this._print.close();

      for(Connector connector : this.getServer().getConnectors()) {
         if (connector instanceof AbstractConnector) {
            ((AbstractConnector)connector).removeBean(this);
         }
      }

   }

   public OutputStream getOutputStream() {
      return this._out;
   }

   public void setOutputStream(OutputStream out) {
      this._out = out;
   }

   public void onOpened(Connection connection) {
      this.print(Thread.currentThread().getName(), "OPENED " + connection.toString());
   }

   public void onClosed(Connection connection) {
      this.print(Thread.currentThread().getName(), "CLOSED " + connection.toString());
   }
}
