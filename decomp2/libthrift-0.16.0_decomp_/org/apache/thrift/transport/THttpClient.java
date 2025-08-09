package org.apache.thrift.transport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.thrift.TConfiguration;

public class THttpClient extends TEndpointTransport {
   private URL url_ = null;
   private final ByteArrayOutputStream requestBuffer_ = new ByteArrayOutputStream();
   private InputStream inputStream_ = null;
   private int connectTimeout_ = 0;
   private int readTimeout_ = 0;
   private Map customHeaders_ = null;
   private final HttpHost host;
   private final HttpClient client;

   public THttpClient(TConfiguration config, String url) throws TTransportException {
      super(config);

      try {
         this.url_ = new URL(url);
         this.client = null;
         this.host = null;
      } catch (IOException iox) {
         throw new TTransportException(iox);
      }
   }

   public THttpClient(String url) throws TTransportException {
      super(new TConfiguration());

      try {
         this.url_ = new URL(url);
         this.client = null;
         this.host = null;
      } catch (IOException iox) {
         throw new TTransportException(iox);
      }
   }

   public THttpClient(TConfiguration config, String url, HttpClient client) throws TTransportException {
      super(config);

      try {
         this.url_ = new URL(url);
         this.client = client;
         this.host = new HttpHost(this.url_.getHost(), -1 == this.url_.getPort() ? this.url_.getDefaultPort() : this.url_.getPort(), this.url_.getProtocol());
      } catch (IOException iox) {
         throw new TTransportException(iox);
      }
   }

   public THttpClient(String url, HttpClient client) throws TTransportException {
      super(new TConfiguration());

      try {
         this.url_ = new URL(url);
         this.client = client;
         this.host = new HttpHost(this.url_.getHost(), -1 == this.url_.getPort() ? this.url_.getDefaultPort() : this.url_.getPort(), this.url_.getProtocol());
      } catch (IOException iox) {
         throw new TTransportException(iox);
      }
   }

   public void setConnectTimeout(int timeout) {
      this.connectTimeout_ = timeout;
      if (null != this.client) {
         this.client.getParams().setParameter("http.connection.timeout", this.connectTimeout_);
      }

   }

   public void setReadTimeout(int timeout) {
      this.readTimeout_ = timeout;
      if (null != this.client) {
         this.client.getParams().setParameter("http.socket.timeout", this.readTimeout_);
      }

   }

   public void setCustomHeaders(Map headers) {
      this.customHeaders_ = headers;
   }

   public void setCustomHeader(String key, String value) {
      if (this.customHeaders_ == null) {
         this.customHeaders_ = new HashMap();
      }

      this.customHeaders_.put(key, value);
   }

   public void open() {
   }

   public void close() {
      if (null != this.inputStream_) {
         try {
            this.inputStream_.close();
         } catch (IOException var2) {
         }

         this.inputStream_ = null;
      }

   }

   public boolean isOpen() {
      return true;
   }

   public int read(byte[] buf, int off, int len) throws TTransportException {
      if (this.inputStream_ == null) {
         throw new TTransportException("Response buffer is empty, no request.");
      } else {
         this.checkReadBytesAvailable((long)len);

         try {
            int ret = this.inputStream_.read(buf, off, len);
            if (ret == -1) {
               throw new TTransportException("No more data available.");
            } else {
               this.countConsumedMessageBytes((long)ret);
               return ret;
            }
         } catch (IOException iox) {
            throw new TTransportException(iox);
         }
      }
   }

   public void write(byte[] buf, int off, int len) {
      this.requestBuffer_.write(buf, off, len);
   }

   private static void consume(HttpEntity entity) throws IOException {
      if (entity != null) {
         if (entity.isStreaming()) {
            InputStream instream = entity.getContent();
            if (instream != null) {
               instream.close();
            }
         }

      }
   }

   private void flushUsingHttpClient() throws TTransportException {
      if (null == this.client) {
         throw new TTransportException("Null HttpClient, aborting.");
      } else {
         byte[] data = this.requestBuffer_.toByteArray();
         this.requestBuffer_.reset();
         HttpPost post = null;
         InputStream is = null;

         try {
            post = new HttpPost(this.url_.getFile());
            post.setHeader("Content-Type", "application/x-thrift");
            post.setHeader("Accept", "application/x-thrift");
            post.setHeader("User-Agent", "Java/THttpClient/HC");
            if (null != this.customHeaders_) {
               for(Map.Entry header : this.customHeaders_.entrySet()) {
                  post.setHeader((String)header.getKey(), (String)header.getValue());
               }
            }

            post.setEntity(new ByteArrayEntity(data));
            HttpResponse response = this.client.execute(this.host, post);
            int responseCode = response.getStatusLine().getStatusCode();
            is = response.getEntity().getContent();
            if (responseCode != 200) {
               throw new TTransportException("HTTP Response code: " + responseCode);
            }

            byte[] buf = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;

            do {
               len = is.read(buf);
               if (len > 0) {
                  baos.write(buf, 0, len);
               }
            } while(-1 != len);

            try {
               consume(response.getEntity());
            } catch (IOException var18) {
            }

            this.inputStream_ = new ByteArrayInputStream(baos.toByteArray());
         } catch (IOException ioe) {
            if (null != post) {
               post.abort();
            }

            throw new TTransportException(ioe);
         } finally {
            this.resetConsumedMessageSize(-1L);
            if (null != is) {
               try {
                  is.close();
               } catch (IOException ioe) {
                  throw new TTransportException(ioe);
               }
            }

            if (post != null) {
               post.releaseConnection();
            }

         }

      }
   }

   public void flush() throws TTransportException {
      if (null != this.client) {
         this.flushUsingHttpClient();
      } else {
         byte[] data = this.requestBuffer_.toByteArray();
         this.requestBuffer_.reset();

         try {
            HttpURLConnection connection = (HttpURLConnection)this.url_.openConnection();
            if (this.connectTimeout_ > 0) {
               connection.setConnectTimeout(this.connectTimeout_);
            }

            if (this.readTimeout_ > 0) {
               connection.setReadTimeout(this.readTimeout_);
            }

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-thrift");
            connection.setRequestProperty("Accept", "application/x-thrift");
            connection.setRequestProperty("User-Agent", "Java/THttpClient");
            if (this.customHeaders_ != null) {
               for(Map.Entry header : this.customHeaders_.entrySet()) {
                  connection.setRequestProperty((String)header.getKey(), (String)header.getValue());
               }
            }

            connection.setDoOutput(true);
            connection.connect();
            connection.getOutputStream().write(data);
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
               throw new TTransportException("HTTP Response code: " + responseCode);
            }

            this.inputStream_ = connection.getInputStream();
         } catch (IOException iox) {
            throw new TTransportException(iox);
         } finally {
            this.resetConsumedMessageSize(-1L);
         }

      }
   }

   public static class Factory extends TTransportFactory {
      private final String url;
      private final HttpClient client;

      public Factory(String url) {
         this.url = url;
         this.client = null;
      }

      public Factory(String url, HttpClient client) {
         this.url = url;
         this.client = client;
      }

      public TTransport getTransport(TTransport trans) {
         try {
            return null != this.client ? new THttpClient(trans.getConfiguration(), this.url, this.client) : new THttpClient(trans.getConfiguration(), this.url);
         } catch (TTransportException var3) {
            return null;
         }
      }
   }
}
