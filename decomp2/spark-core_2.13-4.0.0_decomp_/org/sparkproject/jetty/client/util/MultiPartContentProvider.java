package org.sparkproject.jetty.client.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.client.AsyncContentProvider;
import org.sparkproject.jetty.client.Synchronizable;
import org.sparkproject.jetty.client.api.ContentProvider;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.io.RuntimeIOException;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.NanoTime;

/** @deprecated */
@Deprecated
public class MultiPartContentProvider extends AbstractTypedContentProvider implements AsyncContentProvider, Closeable {
   private static final Logger LOG = LoggerFactory.getLogger(MultiPartContentProvider.class);
   private static final byte[] COLON_SPACE_BYTES = new byte[]{58, 32};
   private static final byte[] CR_LF_BYTES = new byte[]{13, 10};
   private final List parts;
   private final ByteBuffer firstBoundary;
   private final ByteBuffer middleBoundary;
   private final ByteBuffer onlyBoundary;
   private final ByteBuffer lastBoundary;
   private final AtomicBoolean closed;
   private AsyncContentProvider.Listener listener;
   private long length;

   public MultiPartContentProvider() {
      this(makeBoundary());
   }

   public MultiPartContentProvider(String boundary) {
      super("multipart/form-data; boundary=" + boundary);
      this.parts = new ArrayList();
      this.closed = new AtomicBoolean();
      this.length = -1L;
      String firstBoundaryLine = "--" + boundary + "\r\n";
      this.firstBoundary = ByteBuffer.wrap(firstBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      String middleBoundaryLine = "\r\n" + firstBoundaryLine;
      this.middleBoundary = ByteBuffer.wrap(middleBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      String onlyBoundaryLine = "--" + boundary + "--\r\n";
      this.onlyBoundary = ByteBuffer.wrap(onlyBoundaryLine.getBytes(StandardCharsets.US_ASCII));
      String lastBoundaryLine = "\r\n" + onlyBoundaryLine;
      this.lastBoundary = ByteBuffer.wrap(lastBoundaryLine.getBytes(StandardCharsets.US_ASCII));
   }

   private static String makeBoundary() {
      StringBuilder builder = new StringBuilder("JettyHttpClientBoundary");
      builder.append(Long.toString((long)System.identityHashCode(builder), 36));
      builder.append(Long.toString((long)System.identityHashCode(Thread.currentThread()), 36));
      builder.append(Long.toString(NanoTime.now(), 36));
      return builder.toString();
   }

   public void addFieldPart(String name, ContentProvider content, HttpFields fields) {
      this.addPart(new Part(name, (String)null, "text/plain", content, fields));
   }

   public void addFilePart(String name, String fileName, ContentProvider content, HttpFields fields) {
      this.addPart(new Part(name, fileName, "application/octet-stream", content, fields));
   }

   private void addPart(Part part) {
      this.parts.add(part);
      if (LOG.isDebugEnabled()) {
         LOG.debug("Added {}", part);
      }

   }

   public void setListener(AsyncContentProvider.Listener listener) {
      this.listener = listener;
      if (this.closed.get()) {
         this.length = this.calculateLength();
      }

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

   public long getLength() {
      return this.length;
   }

   public Iterator iterator() {
      return new MultiPartIterator();
   }

   public void close() {
      this.closed.compareAndSet(false, true);
   }

   private static class Part {
      private final String name;
      private final String fileName;
      private final String contentType;
      private final ContentProvider content;
      private final HttpFields fields;
      private final ByteBuffer headers;
      private final long length;

      private Part(String name, String fileName, String contentType, ContentProvider content, HttpFields fields) {
         this.name = name;
         this.fileName = fileName;
         this.contentType = contentType;
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
               if (this.content instanceof ContentProvider.Typed) {
                  contentType = ((ContentProvider.Typed)this.content).getContentType();
               } else {
                  contentType = this.contentType;
               }
            }

            contentType = "Content-Type: " + contentType + "\r\n";
            if (this.fields != null && this.fields.size() != 0) {
               ByteArrayOutputStream buffer = new ByteArrayOutputStream((this.fields.size() + 1) * contentDisposition.length());
               buffer.write(contentDisposition.getBytes(StandardCharsets.UTF_8));
               buffer.write(contentType.getBytes(StandardCharsets.UTF_8));

               for(HttpField field : this.fields) {
                  if (!HttpHeader.CONTENT_TYPE.equals(field.getHeader())) {
                     buffer.write(field.getName().getBytes(StandardCharsets.US_ASCII));
                     buffer.write(MultiPartContentProvider.COLON_SPACE_BYTES);
                     String value = field.getValue();
                     if (value != null) {
                        buffer.write(value.getBytes(StandardCharsets.UTF_8));
                     }

                     buffer.write(MultiPartContentProvider.CR_LF_BYTES);
                  }
               }

               buffer.write(MultiPartContentProvider.CR_LF_BYTES);
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

   private class MultiPartIterator implements Iterator, Synchronizable, Callback, Closeable {
      private Iterator iterator;
      private int index;
      private State state;

      private MultiPartIterator() {
         this.state = MultiPartContentProvider.State.FIRST_BOUNDARY;
      }

      public boolean hasNext() {
         return this.state != MultiPartContentProvider.State.COMPLETE;
      }

      public ByteBuffer next() {
         while(true) {
            switch (this.state.ordinal()) {
               case 0:
                  if (MultiPartContentProvider.this.parts.isEmpty()) {
                     this.state = MultiPartContentProvider.State.COMPLETE;
                     return MultiPartContentProvider.this.onlyBoundary.slice();
                  }

                  this.state = MultiPartContentProvider.State.HEADERS;
                  return MultiPartContentProvider.this.firstBoundary.slice();
               case 1:
                  Part part = (Part)MultiPartContentProvider.this.parts.get(this.index);
                  ContentProvider content = part.content;
                  if (content instanceof AsyncContentProvider) {
                     ((AsyncContentProvider)content).setListener(MultiPartContentProvider.this.listener);
                  }

                  this.iterator = content.iterator();
                  this.state = MultiPartContentProvider.State.CONTENT;
                  return part.headers.slice();
               case 2:
                  if (this.iterator.hasNext()) {
                     return (ByteBuffer)this.iterator.next();
                  }

                  ++this.index;
                  if (this.index < MultiPartContentProvider.this.parts.size()) {
                     this.state = MultiPartContentProvider.State.MIDDLE_BOUNDARY;
                     if (this.iterator instanceof Closeable) {
                        IO.close((Closeable)this.iterator);
                     }
                     break;
                  }

                  this.state = MultiPartContentProvider.State.LAST_BOUNDARY;
                  break;
               case 3:
                  this.state = MultiPartContentProvider.State.HEADERS;
                  return MultiPartContentProvider.this.middleBoundary.slice();
               case 4:
                  this.state = MultiPartContentProvider.State.COMPLETE;
                  return MultiPartContentProvider.this.lastBoundary.slice();
               case 5:
                  throw new NoSuchElementException();
               default:
                  throw new IllegalStateException(this.state.toString());
            }
         }
      }

      public Object getLock() {
         return this.iterator instanceof Synchronizable ? ((Synchronizable)this.iterator).getLock() : this;
      }

      public void succeeded() {
         if (this.state == MultiPartContentProvider.State.CONTENT && this.iterator instanceof Callback) {
            ((Callback)this.iterator).succeeded();
         }

      }

      public void failed(Throwable x) {
         if (this.state == MultiPartContentProvider.State.CONTENT && this.iterator instanceof Callback) {
            ((Callback)this.iterator).failed(x);
         }

      }

      public void close() throws IOException {
         if (this.iterator instanceof Closeable) {
            ((Closeable)this.iterator).close();
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
