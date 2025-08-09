package org.apache.avro.ipc;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class HttpTransceiver extends Transceiver {
   static final String CONTENT_TYPE = "avro/binary";
   private URL url;
   private Proxy proxy;
   private HttpURLConnection connection;
   private int timeout;

   public HttpTransceiver(URL url) {
      this.url = url;
   }

   public HttpTransceiver(URL url, Proxy proxy) {
      this(url);
      this.proxy = proxy;
   }

   public void setTimeout(int timeout) {
      this.timeout = timeout;
   }

   public String getRemoteName() {
      return this.url.toString();
   }

   public synchronized List readBuffers() throws IOException {
      InputStream in = this.connection.getInputStream();

      List var2;
      try {
         var2 = readBuffers(in);
      } catch (Throwable var5) {
         if (in != null) {
            try {
               in.close();
            } catch (Throwable var4) {
               var5.addSuppressed(var4);
            }
         }

         throw var5;
      }

      if (in != null) {
         in.close();
      }

      return var2;
   }

   public synchronized void writeBuffers(List buffers) throws IOException {
      if (this.proxy == null) {
         this.connection = (HttpURLConnection)this.url.openConnection();
      } else {
         this.connection = (HttpURLConnection)this.url.openConnection(this.proxy);
      }

      this.connection.setRequestMethod("POST");
      this.connection.setRequestProperty("Content-Type", "avro/binary");
      this.connection.setRequestProperty("Content-Length", Integer.toString(getLength(buffers)));
      this.connection.setDoOutput(true);
      this.connection.setReadTimeout(this.timeout);
      this.connection.setConnectTimeout(this.timeout);
      OutputStream out = this.connection.getOutputStream();

      try {
         writeBuffers(buffers, out);
      } catch (Throwable var6) {
         if (out != null) {
            try {
               out.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }
         }

         throw var6;
      }

      if (out != null) {
         out.close();
      }

   }

   static int getLength(List buffers) {
      int length = 0;

      for(ByteBuffer buffer : buffers) {
         length += 4;
         length += buffer.remaining();
      }

      length += 4;
      return length;
   }

   static List readBuffers(InputStream in) throws IOException {
      List<ByteBuffer> buffers = new ArrayList();

      while(true) {
         int length = (in.read() << 24) + (in.read() << 16) + (in.read() << 8) + in.read();
         if (length == 0) {
            return buffers;
         }

         ByteBuffer buffer = ByteBuffer.allocate(length);

         while(buffer.hasRemaining()) {
            int p = buffer.position();
            int i = in.read(buffer.array(), p, buffer.remaining());
            if (i < 0) {
               throw new EOFException("Unexpected EOF");
            }

            ((Buffer)buffer).position(p + i);
         }

         ((Buffer)buffer).flip();
         buffers.add(buffer);
      }
   }

   static void writeBuffers(List buffers, OutputStream out) throws IOException {
      for(ByteBuffer buffer : buffers) {
         writeLength(buffer.limit(), out);
         out.write(buffer.array(), buffer.position(), buffer.remaining());
         ((Buffer)buffer).position(buffer.limit());
      }

      writeLength(0, out);
   }

   private static void writeLength(int length, OutputStream out) throws IOException {
      out.write(255 & length >>> 24);
      out.write(255 & length >>> 16);
      out.write(255 & length >>> 8);
      out.write(255 & length);
   }
}
