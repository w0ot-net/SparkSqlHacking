package io.fabric8.kubernetes.client.http;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StandardHttpRequest extends StandardHttpHeaders implements HttpRequest {
   public static final String METHOD_POST = "POST";
   public static final String METHOD_PUT = "PUT";
   private final UUID id;
   private final URI uri;
   private final String method;
   private final String contentType;
   private final String bodyString;
   private final BodyContent body;
   private final boolean expectContinue;
   private final Duration timeout;
   private final boolean forStreaming;

   public StandardHttpRequest(Map headers, URI uri, String method, String bodyString) {
      this(headers, uri, method, bodyString, (BodyContent)null, false, (String)null, (Duration)null, false);
   }

   StandardHttpRequest(Map headers, URI uri, String method, String bodyString, BodyContent body, boolean expectContinue, String contentType, Duration timeout, boolean forStreaming) {
      super(headers);
      this.id = Utils.generateId();
      this.uri = uri;
      this.method = method;
      this.bodyString = bodyString;
      this.body = body;
      this.expectContinue = expectContinue;
      this.contentType = contentType;
      this.timeout = timeout;
      this.forStreaming = forStreaming;
   }

   public UUID id() {
      return this.id;
   }

   public URI uri() {
      return this.uri;
   }

   public String method() {
      return this.method;
   }

   public String bodyString() {
      return this.bodyString;
   }

   public BodyContent body() {
      return this.body;
   }

   public boolean isExpectContinue() {
      return this.expectContinue;
   }

   public String getContentType() {
      return this.contentType;
   }

   public Builder newBuilder() {
      return new Builder(this);
   }

   public boolean isForStreaming() {
      return this.forStreaming;
   }

   public Duration getTimeout() {
      return this.timeout;
   }

   public static class StringBodyContent implements BodyContent {
      private String content;

      public StringBodyContent(String content) {
         this.content = content;
      }

      public String getContent() {
         return this.content;
      }
   }

   public static class ByteArrayBodyContent implements BodyContent {
      private byte[] content;

      public ByteArrayBodyContent(byte[] bytes) {
         this.content = bytes;
      }

      public byte[] getContent() {
         return this.content;
      }
   }

   public static class InputStreamBodyContent implements BodyContent {
      private long length;
      private InputStream content;

      public InputStreamBodyContent(InputStream stream, long length) {
         this.length = length;
         this.content = stream;
      }

      public InputStream getContent() {
         return this.content;
      }

      public long getLength() {
         return this.length;
      }
   }

   public static final class Builder extends AbstractBasicBuilder implements HttpRequest.Builder {
      private String method = "GET";
      private BodyContent body;
      private String bodyAsString;
      private boolean expectContinue;
      private String contentType;
      protected Duration timeout;
      protected boolean forStreaming;

      public Builder() {
      }

      public Builder(StandardHttpRequest original) {
         super.uri(original.uri());
         super.setHeaders(original.headers());
         this.method = original.method;
         this.bodyAsString = original.bodyString;
         this.body = original.body;
         this.expectContinue = original.expectContinue;
         this.contentType = original.contentType;
         this.timeout = original.timeout;
         this.forStreaming = original.forStreaming;
      }

      public StandardHttpRequest build() {
         return new StandardHttpRequest(this.getHeaders(), (URI)Objects.requireNonNull(this.getUri()), this.method, this.bodyAsString, this.body, this.expectContinue, this.contentType, this.timeout, this.forStreaming);
      }

      public HttpRequest.Builder timeout(long timeout, TimeUnit unit) {
         this.timeout = Duration.ofNanos(unit.toNanos(timeout));
         return this;
      }

      public HttpRequest.Builder forStreaming() {
         this.forStreaming = true;
         return this;
      }

      public HttpRequest.Builder uri(String uri) {
         return (HttpRequest.Builder)super.uri(URI.create(uri));
      }

      public HttpRequest.Builder url(URL url) {
         try {
            return (HttpRequest.Builder)super.uri(url.toURI());
         } catch (URISyntaxException e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }

      public HttpRequest.Builder post(String contentType, byte[] writeValueAsBytes) {
         this.method = "POST";
         this.contentType = contentType;
         this.body = new ByteArrayBodyContent(writeValueAsBytes);
         return this;
      }

      public HttpRequest.Builder method(String method, String contentType, String body) {
         this.method = method;
         this.contentType = contentType;
         this.bodyAsString = body;
         if (body != null) {
            this.body = new StringBodyContent(body);
         }

         return this;
      }

      public HttpRequest.Builder method(String method, String contentType, InputStream stream, long length) {
         this.method = method;
         this.contentType = contentType;
         this.body = new InputStreamBodyContent(stream, length);
         return this;
      }

      public HttpRequest.Builder expectContinue() {
         this.expectContinue = true;
         return this;
      }
   }

   public interface BodyContent {
   }
}
