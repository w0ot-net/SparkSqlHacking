package org.sparkproject.jetty.client.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.util.BufferUtil;

public abstract class BufferingResponseListener extends Response.Listener.Adapter {
   private final int maxLength;
   private ByteBuffer buffer;
   private String mediaType;
   private String encoding;

   public BufferingResponseListener() {
      this(2097152);
   }

   public BufferingResponseListener(int maxLength) {
      if (maxLength < 0) {
         throw new IllegalArgumentException("Invalid max length " + maxLength);
      } else {
         this.maxLength = maxLength;
      }
   }

   public void onHeaders(Response response) {
      super.onHeaders(response);
      Request request = response.getRequest();
      HttpFields headers = response.getHeaders();
      long length = headers.getLongField(HttpHeader.CONTENT_LENGTH);
      if (HttpMethod.HEAD.is(request.getMethod())) {
         length = 0L;
      }

      if (length > (long)this.maxLength) {
         response.abort(new IllegalArgumentException("Buffering capacity " + this.maxLength + " exceeded"));
      } else {
         String contentType = headers.get(HttpHeader.CONTENT_TYPE);
         if (contentType != null) {
            String media = contentType;
            String charset = "charset=";
            int index = contentType.toLowerCase(Locale.ENGLISH).indexOf(charset);
            if (index > 0) {
               media = contentType.substring(0, index);
               String encoding = contentType.substring(index + charset.length());
               int semicolon = encoding.indexOf(59);
               if (semicolon > 0) {
                  encoding = encoding.substring(0, semicolon).trim();
               }

               int lastIndex = encoding.length() - 1;
               if (encoding.charAt(0) == '"' && encoding.charAt(lastIndex) == '"') {
                  encoding = encoding.substring(1, lastIndex).trim();
               }

               this.encoding = encoding;
            }

            int semicolon = media.indexOf(59);
            if (semicolon > 0) {
               media = media.substring(0, semicolon).trim();
            }

            this.mediaType = media;
         }

      }
   }

   public void onContent(Response response, ByteBuffer content) {
      int length = content.remaining();
      if (length > BufferUtil.space(this.buffer)) {
         int remaining = this.buffer == null ? 0 : this.buffer.remaining();
         if (remaining + length > this.maxLength) {
            response.abort(new IllegalArgumentException("Buffering capacity " + this.maxLength + " exceeded"));
         }

         int requiredCapacity = this.buffer == null ? length : this.buffer.capacity() + length;
         int newCapacity = Math.min(Integer.highestOneBit(requiredCapacity) << 1, this.maxLength);
         this.buffer = BufferUtil.ensureCapacity(this.buffer, newCapacity);
      }

      BufferUtil.append(this.buffer, content);
   }

   public abstract void onComplete(Result var1);

   public String getMediaType() {
      return this.mediaType;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public byte[] getContent() {
      return this.buffer == null ? new byte[0] : BufferUtil.toArray(this.buffer);
   }

   public String getContentAsString() {
      String encoding = this.encoding;
      return encoding == null ? this.getContentAsString(StandardCharsets.UTF_8) : this.getContentAsString(encoding);
   }

   public String getContentAsString(String encoding) {
      return this.buffer == null ? null : BufferUtil.toString(this.buffer, Charset.forName(encoding));
   }

   public String getContentAsString(Charset encoding) {
      return this.buffer == null ? null : BufferUtil.toString(this.buffer, encoding);
   }

   public InputStream getContentAsInputStream() {
      return this.buffer == null ? new ByteArrayInputStream(new byte[0]) : new ByteArrayInputStream(this.buffer.array(), this.buffer.arrayOffset(), this.buffer.remaining());
   }
}
