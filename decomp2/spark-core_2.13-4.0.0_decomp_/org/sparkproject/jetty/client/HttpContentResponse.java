package org.sparkproject.jetty.client;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import org.sparkproject.jetty.client.api.ContentResponse;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpVersion;

public class HttpContentResponse implements ContentResponse {
   private final Response response;
   private final byte[] content;
   private final String mediaType;
   private final String encoding;

   public HttpContentResponse(Response response, byte[] content, String mediaType, String encoding) {
      this.response = response;
      this.content = content;
      this.mediaType = mediaType;
      this.encoding = encoding;
   }

   public Request getRequest() {
      return this.response.getRequest();
   }

   public List getListeners(Class listenerClass) {
      return this.response.getListeners(listenerClass);
   }

   public HttpVersion getVersion() {
      return this.response.getVersion();
   }

   public int getStatus() {
      return this.response.getStatus();
   }

   public String getReason() {
      return this.response.getReason();
   }

   public HttpFields getHeaders() {
      return this.response.getHeaders();
   }

   public boolean abort(Throwable cause) {
      return this.response.abort(cause);
   }

   public String getMediaType() {
      return this.mediaType;
   }

   public String getEncoding() {
      return this.encoding;
   }

   public byte[] getContent() {
      return this.content;
   }

   public String getContentAsString() {
      String encoding = this.encoding;
      if (encoding == null) {
         return new String(this.getContent(), StandardCharsets.UTF_8);
      } else {
         try {
            return new String(this.getContent(), encoding);
         } catch (UnsupportedEncodingException var3) {
            throw new UnsupportedCharsetException(encoding);
         }
      }
   }

   public String toString() {
      return String.format("%s[%s %d %s - %d bytes]", HttpContentResponse.class.getSimpleName(), this.getVersion(), this.getStatus(), this.getReason(), this.getContent().length);
   }
}
