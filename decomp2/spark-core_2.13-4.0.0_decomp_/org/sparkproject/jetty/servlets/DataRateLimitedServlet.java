package org.sparkproject.jetty.servlets;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.sparkproject.jetty.server.HttpOutput;
import org.sparkproject.jetty.util.ProcessorUtils;

/** @deprecated */
@Deprecated
public class DataRateLimitedServlet extends HttpServlet {
   private static final long serialVersionUID = -4771757707068097025L;
   private int buffersize = 8192;
   private long pauseNS;
   ScheduledThreadPoolExecutor scheduler;
   private final ConcurrentHashMap cache;

   public DataRateLimitedServlet() {
      this.pauseNS = TimeUnit.MILLISECONDS.toNanos(100L);
      this.cache = new ConcurrentHashMap();
   }

   public void init() throws ServletException {
      String tmp = this.getInitParameter("buffersize");
      if (tmp != null) {
         this.buffersize = Integer.parseInt(tmp);
      }

      tmp = this.getInitParameter("pause");
      if (tmp != null) {
         this.pauseNS = TimeUnit.MILLISECONDS.toNanos((long)Integer.parseInt(tmp));
      }

      tmp = this.getInitParameter("pool");
      int pool = tmp == null ? ProcessorUtils.availableProcessors() : Integer.parseInt(tmp);
      this.scheduler = new ScheduledThreadPoolExecutor(pool);
   }

   public void destroy() {
      this.scheduler.shutdown();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String info = request.getPathInfo();
      if (info.endsWith("/")) {
         response.sendError(503, "directories not supported");
      } else {
         String contentType = this.getServletContext().getMimeType(info);
         response.setContentType(contentType == null ? "application/x-data" : contentType);
         String path = request.getPathTranslated();
         ServletOutputStream out = response.getOutputStream();
         if (path != null && out instanceof HttpOutput) {
            File file = new File(path);
            if (file.exists() && file.canRead()) {
               response.setContentLengthLong(file.length());
               ByteBuffer mapped = (ByteBuffer)this.cache.get(path);
               if (mapped == null) {
                  RandomAccessFile raf = new RandomAccessFile(file, "r");

                  try {
                     ByteBuffer buf = raf.getChannel().map(MapMode.READ_ONLY, 0L, raf.length());
                     mapped = (ByteBuffer)this.cache.putIfAbsent(path, buf);
                     if (mapped == null) {
                        mapped = buf;
                     }
                  } catch (Throwable var13) {
                     try {
                        raf.close();
                     } catch (Throwable var12) {
                        var13.addSuppressed(var12);
                     }

                     throw var13;
                  }

                  raf.close();
               }

               AsyncContext async = request.startAsync();
               out.setWriteListener(new JettyDataStream(mapped, async, out));
               return;
            }
         }

         InputStream content = this.getServletContext().getResourceAsStream(info);
         if (content == null) {
            response.sendError(404);
         } else {
            out.setWriteListener(new StandardDataStream(content, request.startAsync(), out));
         }
      }
   }

   private final class StandardDataStream implements WriteListener, Runnable {
      private final InputStream content;
      private final AsyncContext async;
      private final ServletOutputStream out;

      private StandardDataStream(InputStream content, AsyncContext async, ServletOutputStream out) {
         this.content = content;
         this.async = async;
         this.out = out;
      }

      public void onWritePossible() throws IOException {
         if (this.out.isReady()) {
            byte[] buffer = new byte[DataRateLimitedServlet.this.buffersize];
            int len = this.content.read(buffer);
            if (len < 0) {
               this.async.complete();
               return;
            }

            this.out.write(buffer, 0, len);
            DataRateLimitedServlet.this.scheduler.schedule(this, DataRateLimitedServlet.this.pauseNS, TimeUnit.NANOSECONDS);
         }

      }

      public void run() {
         try {
            this.onWritePossible();
         } catch (Exception e) {
            this.onError(e);
         }

      }

      public void onError(Throwable t) {
         DataRateLimitedServlet.this.getServletContext().log("Async Error", t);
         this.async.complete();
      }
   }

   private final class JettyDataStream implements WriteListener, Runnable {
      private final ByteBuffer content;
      private final int limit;
      private final AsyncContext async;
      private final HttpOutput out;

      private JettyDataStream(ByteBuffer content, AsyncContext async, ServletOutputStream out) {
         this.content = content.asReadOnlyBuffer();
         this.limit = this.content.limit();
         this.async = async;
         this.out = (HttpOutput)out;
      }

      public void onWritePossible() throws IOException {
         if (this.out.isReady()) {
            int l = this.content.position() + DataRateLimitedServlet.this.buffersize;
            if (l > this.limit) {
               l = this.limit;
            }

            this.content.limit(l);
            if (!this.content.hasRemaining()) {
               this.async.complete();
               return;
            }

            this.out.write(this.content);
            DataRateLimitedServlet.this.scheduler.schedule(this, DataRateLimitedServlet.this.pauseNS, TimeUnit.NANOSECONDS);
         }

      }

      public void run() {
         try {
            this.onWritePossible();
         } catch (Exception e) {
            this.onError(e);
         }

      }

      public void onError(Throwable t) {
         DataRateLimitedServlet.this.getServletContext().log("Async Error", t);
         this.async.complete();
      }
   }
}
