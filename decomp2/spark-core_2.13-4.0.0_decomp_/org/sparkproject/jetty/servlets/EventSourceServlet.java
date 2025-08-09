package org.sparkproject.jetty.servlets;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.sparkproject.jetty.util.thread.AutoLock;

public abstract class EventSourceServlet extends HttpServlet {
   private static final byte[] CRLF = new byte[]{13, 10};
   private static final byte[] EVENT_FIELD;
   private static final byte[] DATA_FIELD;
   private static final byte[] COMMENT_FIELD;
   private ScheduledExecutorService scheduler;
   private int heartBeatPeriod = 10;

   public void init() throws ServletException {
      String heartBeatPeriodParam = this.getServletConfig().getInitParameter("heartBeatPeriod");
      if (heartBeatPeriodParam != null) {
         this.heartBeatPeriod = Integer.parseInt(heartBeatPeriodParam);
      }

      this.scheduler = Executors.newSingleThreadScheduledExecutor();
   }

   public void destroy() {
      if (this.scheduler != null) {
         this.scheduler.shutdown();
      }

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Enumeration<String> acceptValues = request.getHeaders("Accept");

      while(acceptValues.hasMoreElements()) {
         String accept = (String)acceptValues.nextElement();
         if (accept.equals("text/event-stream")) {
            EventSource eventSource = this.newEventSource(request);
            if (eventSource == null) {
               response.sendError(503);
            } else {
               this.respond(request, response);
               AsyncContext async = request.startAsync();
               async.setTimeout(0L);
               EventSourceEmitter emitter = new EventSourceEmitter(eventSource, async);
               emitter.scheduleHeartBeat();
               this.open(eventSource, emitter);
            }

            return;
         }
      }

      super.doGet(request, response);
   }

   protected abstract EventSource newEventSource(HttpServletRequest var1);

   protected void respond(HttpServletRequest request, HttpServletResponse response) throws IOException {
      response.setStatus(200);
      response.setCharacterEncoding(StandardCharsets.UTF_8.name());
      response.setContentType("text/event-stream");
      response.addHeader("Connection", "close");
      response.flushBuffer();
   }

   protected void open(EventSource eventSource, EventSource.Emitter emitter) throws IOException {
      eventSource.onOpen(emitter);
   }

   static {
      EVENT_FIELD = "event: ".getBytes(StandardCharsets.UTF_8);
      DATA_FIELD = "data: ".getBytes(StandardCharsets.UTF_8);
      COMMENT_FIELD = ": ".getBytes(StandardCharsets.UTF_8);
   }

   protected class EventSourceEmitter implements EventSource.Emitter, Runnable {
      private final AutoLock lock = new AutoLock();
      private final EventSource eventSource;
      private final AsyncContext async;
      private final ServletOutputStream output;
      private Future heartBeat;
      private boolean closed;

      public EventSourceEmitter(EventSource eventSource, AsyncContext async) throws IOException {
         this.eventSource = eventSource;
         this.async = async;
         this.output = async.getResponse().getOutputStream();
      }

      public void event(String name, String data) throws IOException {
         try (AutoLock l = this.lock.lock()) {
            this.output.write(EventSourceServlet.EVENT_FIELD);
            this.output.write(name.getBytes(StandardCharsets.UTF_8));
            this.output.write(EventSourceServlet.CRLF);
            this.data(data);
         }

      }

      public void data(String data) throws IOException {
         try (AutoLock l = this.lock.lock()) {
            BufferedReader reader = new BufferedReader(new StringReader(data));

            String line;
            while((line = reader.readLine()) != null) {
               this.output.write(EventSourceServlet.DATA_FIELD);
               this.output.write(line.getBytes(StandardCharsets.UTF_8));
               this.output.write(EventSourceServlet.CRLF);
            }

            this.output.write(EventSourceServlet.CRLF);
            this.flush();
         }

      }

      public void comment(String comment) throws IOException {
         try (AutoLock l = this.lock.lock()) {
            this.output.write(EventSourceServlet.COMMENT_FIELD);
            this.output.write(comment.getBytes(StandardCharsets.UTF_8));
            this.output.write(EventSourceServlet.CRLF);
            this.output.write(EventSourceServlet.CRLF);
            this.flush();
         }

      }

      public void run() {
         try {
            try (AutoLock l = this.lock.lock()) {
               this.output.write(13);
               this.flush();
               this.output.write(10);
               this.flush();
            }

            this.scheduleHeartBeat();
         } catch (IOException var6) {
            this.close();
            this.eventSource.onClose();
         }

      }

      protected void flush() throws IOException {
         this.async.getResponse().flushBuffer();
      }

      public void close() {
         try (AutoLock l = this.lock.lock()) {
            this.closed = true;
            this.heartBeat.cancel(false);
         }

         this.async.complete();
      }

      private void scheduleHeartBeat() {
         try (AutoLock l = this.lock.lock()) {
            if (!this.closed) {
               this.heartBeat = EventSourceServlet.this.scheduler.schedule(this, (long)EventSourceServlet.this.heartBeatPeriod, TimeUnit.SECONDS);
            }
         }

      }
   }
}
