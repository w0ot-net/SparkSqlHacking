package org.sparkproject.jetty.client.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.RuntimeIOException;
import org.sparkproject.jetty.util.Callback;

public class MultiPartRequestContent extends AbstractRequestContent implements Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(MultiPartRequestContent.class);
   private static final byte[] COLON_SPACE_BYTES = new byte[]{58, 32};
   private static final byte[] CR_LF_BYTES = new byte[]{13, 10};
   private final List parts;
   private final ByteBuffer firstBoundary;
   private final ByteBuffer middleBoundary;
   private final ByteBuffer onlyBoundary;
   private final ByteBuffer lastBoundary;
   private long length;
   private boolean closed;
   private Request.Content.Subscription subscription;

   private static String makeBoundary() {
      Random random = new Random();
      StringBuilder builder = new StringBuilder("JettyHttpClientBoundary");
      int length = builder.length();

      while(builder.length() < length + 16) {
         long rnd = random.nextLong();
         builder.append(Long.toString(rnd < 0L ? -rnd : rnd, 36));
      }

      builder.setLength(length + 16);
      return builder.toString();
   }

   public MultiPartRequestContent() {
      this(makeBoundary());
   }

   public MultiPartRequestContent(String boundary) {
      super("multipart/form-data; boundary=" + boundary);
      this.parts = new ArrayList();
      String firstBoundaryLine = "--" + boundary + "\r\n";
      this.firstBoundary = ByteBuffer.wrap(firstBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      String middleBoundaryLine = "\r\n" + firstBoundaryLine;
      this.middleBoundary = ByteBuffer.wrap(middleBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      String onlyBoundaryLine = "--" + boundary + "--\r\n";
      this.onlyBoundary = ByteBuffer.wrap(onlyBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      String lastBoundaryLine = "\r\n" + onlyBoundaryLine;
      this.lastBoundary = ByteBuffer.wrap(lastBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      this.length = -1L;
   }

   public long getLength() {
      return this.length;
   }

   protected Request.Content.Subscription newSubscription(Request.Content.Consumer consumer, boolean emitInitialContent) {
      if (!this.closed) {
         throw new IllegalStateException("MultiPartRequestContent must be closed before sending the request");
      } else if (this.subscription != null) {
         throw new IllegalStateException("Multiple subscriptions not supported on " + String.valueOf(this));
      } else {
         this.length = this.calculateLength();
         return this.subscription = new SubscriptionImpl(consumer, emitInitialContent);
      }
   }

   public void fail(Throwable failure) {
      this.parts.stream().map((part) -> part.content).forEach((content) -> content.fail(failure));
   }

   public void addFieldPart(String name, Request.Content content, HttpFields fields) {
      this.addPart(new Part(name, (String)null, content, fields));
   }

   public void addFilePart(String name, String fileName, Request.Content content, HttpFields fields) {
      this.addPart(new Part(name, fileName, content, fields));
   }

   private void addPart(Part part) {
      this.parts.add(part);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Added {}", part);
      }

   }

   public void close() {
      this.closed = true;
   }

   private long calculateLength() {
      if (this.parts.isEmpty()) {
         return (long)this.onlyBoundary.remaining();
      } else {
         long result = 0L;

         for(int i = 0; i < this.parts.size(); ++i) {
            result += i == 0 ? (long)this.firstBoundary.remaining() : (long)this.middleBoundary.remaining();
            Part part = (Part)this.parts.get(i);
            long partLength = part.length;
            result += partLength;
            if (partLength < 0L) {
               result = -1L;
               break;
            }
         }

         if (result > 0L) {
            result += (long)this.lastBoundary.remaining();
         }

         return result;
      }
   }

   private static class Part {
      private final String name;
      private final String fileName;
      private final Request.Content content;
      private final HttpFields fields;
      private final ByteBuffer headers;
      private final long length;

      private Part(String name, String fileName, Request.Content content, HttpFields fields) {
         this.name = name;
         this.fileName = fileName;
         this.content = content;
         this.fields = fields;
         this.headers = this.headers();
         this.length = content.getLength() < 0L ? -1L : (long)this.headers.remaining() + content.getLength();
      }

      private ByteBuffer headers() {
         try {
            String contentDisposition = "Content-Disposition: form-data; name=\"" + this.name + "\"";
            if (this.fileName != null) {
               contentDisposition = contentDisposition + "; filename=\"" + this.fileName + "\"";
            }

            contentDisposition = contentDisposition + "\r\n";
            String contentType = this.fields == null ? null : this.fields.get(HttpHeader.CONTENT_TYPE);
            if (contentType == null) {
               contentType = this.content.getContentType();
            }

            contentType = "Content-Type: " + contentType + "\r\n";
            if (this.fields != null && this.fields.size() != 0) {
               ByteArrayOutputStream buffer = new ByteArrayOutputStream((this.fields.size() + 1) * contentDisposition.length());
               buffer.write(contentDisposition.getBytes(StandardCharsets.UTF_8));
               buffer.write(contentType.getBytes(StandardCharsets.UTF_8));

               for(HttpField field : this.fields) {
                  if (!HttpHeader.CONTENT_TYPE.equals(field.getHeader())) {
                     buffer.write(field.getName().getBytes(StandardCharsets.US_ASCII));
                     buffer.write(MultiPartRequestContent.COLON_SPACE_BYTES);
                     String value = field.getValue();
                     if (value != null) {
                        buffer.write(value.getBytes(StandardCharsets.UTF_8));
                     }

                     buffer.write(MultiPartRequestContent.CR_LF_BYTES);
                  }
               }

               buffer.write(MultiPartRequestContent.CR_LF_BYTES);
               return ByteBuffer.wrap(buffer.toByteArray());
            } else {
               String headers = contentDisposition + contentType;
               headers = headers + "\r\n";
               return ByteBuffer.wrap(headers.getBytes(StandardCharsets.UTF_8));
            }
         } catch (IOException x) {
            throw new RuntimeIOException(x);
         }
      }

      public String toString() {
         return String.format("%s@%x[name=%s,fileName=%s,length=%d,headers=%s]", this.getClass().getSimpleName(), this.hashCode(), this.name, this.fileName, this.content.getLength(), this.fields);
      }
   }

   private class SubscriptionImpl extends AbstractRequestContent.AbstractSubscription implements Request.Content.Consumer {
      private State state;
      private int index;
      private Request.Content.Subscription subscription;

      private SubscriptionImpl(Request.Content.Consumer consumer, boolean emitInitialContent) {
         super(consumer, emitInitialContent);
         this.state = MultiPartRequestContent.State.FIRST_BOUNDARY;
      }

      protected boolean produceContent(AbstractRequestContent.Producer producer) throws IOException {
         boolean last = false;
         ByteBuffer buffer;
         switch (this.state.ordinal()) {
            case 0:
               if (MultiPartRequestContent.this.parts.isEmpty()) {
                  this.state = MultiPartRequestContent.State.COMPLETE;
                  buffer = MultiPartRequestContent.this.onlyBoundary.slice();
                  last = true;
               } else {
                  this.state = MultiPartRequestContent.State.HEADERS;
                  buffer = MultiPartRequestContent.this.firstBoundary.slice();
               }
               break;
            case 1:
               Part part = (Part)MultiPartRequestContent.this.parts.get(this.index);
               Request.Content content = part.content;
               this.subscription = content.subscribe(this, true);
               this.state = MultiPartRequestContent.State.CONTENT;
               buffer = part.headers.slice();
               break;
            case 2:
               buffer = null;
               this.subscription.demand();
               break;
            case 3:
               this.state = MultiPartRequestContent.State.HEADERS;
               buffer = MultiPartRequestContent.this.middleBoundary.slice();
               break;
            case 4:
               this.state = MultiPartRequestContent.State.COMPLETE;
               buffer = MultiPartRequestContent.this.lastBoundary.slice();
               last = true;
               break;
            case 5:
               throw new EOFException("Demand after last content");
            default:
               throw new IllegalStateException("Invalid state " + String.valueOf(this.state));
         }

         return producer.produce(buffer, last, Callback.NOOP);
      }

      public void onContent(ByteBuffer buffer, boolean last, Callback callback) {
         if (last) {
            ++this.index;
            if (this.index < MultiPartRequestContent.this.parts.size()) {
               this.state = MultiPartRequestContent.State.MIDDLE_BOUNDARY;
            } else {
               this.state = MultiPartRequestContent.State.LAST_BOUNDARY;
            }
         }

         this.notifyContent(buffer, false, callback);
      }

      public void onFailure(Throwable failure) {
         if (this.subscription != null) {
            this.subscription.fail(failure);
         }

      }
   }

   private static enum State {
      FIRST_BOUNDARY,
      HEADERS,
      CONTENT,
      MIDDLE_BOUNDARY,
      LAST_BOUNDARY,
      COMPLETE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{FIRST_BOUNDARY, HEADERS, CONTENT, MIDDLE_BOUNDARY, LAST_BOUNDARY, COMPLETE};
      }
   }
}
