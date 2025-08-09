package org.sparkproject.jetty.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.StringUtil;

public class HttpTester {
   public static Input from(ByteBuffer data) {
      return new Input(data.slice()) {
         public int fillBuffer() throws IOException {
            this._eof = true;
            return -1;
         }
      };
   }

   public static Input from(final InputStream in) {
      return new Input() {
         public int fillBuffer() throws IOException {
            BufferUtil.compact(this._buffer);
            int len = in.read(this._buffer.array(), this._buffer.arrayOffset() + this._buffer.limit(), BufferUtil.space(this._buffer));
            if (len < 0) {
               this._eof = true;
            } else {
               this._buffer.limit(this._buffer.limit() + len);
            }

            return len;
         }
      };
   }

   public static Input from(final ReadableByteChannel in) {
      return new Input() {
         public int fillBuffer() throws IOException {
            BufferUtil.compact(this._buffer);
            int pos = BufferUtil.flipToFill(this._buffer);
            int len = in.read(this._buffer);
            if (len < 0) {
               this._eof = true;
            }

            BufferUtil.flipToFlush(this._buffer, pos);
            return len;
         }
      };
   }

   private HttpTester() {
   }

   public static Request newRequest() {
      Request r = new Request();
      r.setMethod(HttpMethod.GET.asString());
      r.setURI("/");
      r.setVersion(HttpVersion.HTTP_1_1);
      return r;
   }

   public static Request parseRequest(String request) {
      Request r = new Request();
      HttpParser parser = new HttpParser(r);
      parser.parseNext(BufferUtil.toBuffer(request));
      return r;
   }

   public static Request parseRequest(ByteBuffer request) {
      Request r = new Request();
      HttpParser parser = new HttpParser(r);
      parser.parseNext(request);
      return r;
   }

   public static Request parseRequest(InputStream inputStream) throws IOException {
      return parseRequest(from(inputStream));
   }

   public static Request parseRequest(ReadableByteChannel channel) throws IOException {
      return parseRequest(from(channel));
   }

   public static Request parseRequest(Input input) throws IOException {
      HttpParser parser = input.takeHttpParser();
      Request request;
      if (parser != null) {
         request = (Request)parser.getHandler();
      } else {
         request = newRequest();
         parser = new HttpParser(request);
      }

      parse(input, parser);
      if (request.isComplete()) {
         return request;
      } else {
         input.setHttpParser(parser);
         return null;
      }
   }

   public static Response parseResponse(String response) {
      Response r = new Response();
      HttpParser parser = new HttpParser(r);
      parser.parseNext(BufferUtil.toBuffer(response));
      return r;
   }

   public static Response parseResponse(ByteBuffer response) {
      Response r = new Response();
      HttpParser parser = new HttpParser(r);
      parser.parseNext(response);
      return r;
   }

   public static Response parseResponse(InputStream responseStream) throws IOException {
      Response r = new Response();
      HttpParser parser = new HttpParser(r);
      byte[] array = new byte[1];
      ByteBuffer buffer = ByteBuffer.wrap(array);
      buffer.limit(1);

      int l;
      do {
         buffer.position(1);
         l = responseStream.read(array);
         if (l < 0) {
            parser.atEOF();
         } else {
            buffer.position(0);
         }

         if (parser.parseNext(buffer)) {
            return r;
         }
      } while(l >= 0);

      return null;
   }

   public static Response parseResponse(Input in) throws IOException {
      HttpParser parser = in.takeHttpParser();
      Response r;
      if (parser == null) {
         r = new Response();
         parser = new HttpParser(r);
      } else {
         r = (Response)parser.getHandler();
      }

      parse(in, parser);
      if (r.isComplete()) {
         return r;
      } else {
         in.setHttpParser(parser);
         return null;
      }
   }

   public static void parseResponse(Input in, Response response) throws IOException {
      HttpParser parser = in.takeHttpParser();
      if (parser == null) {
         parser = new HttpParser(response);
      }

      parse(in, parser);
      if (!response.isComplete()) {
         in.setHttpParser(parser);
      }

   }

   private static void parse(Input in, HttpParser parser) throws IOException {
      ByteBuffer buffer = in.getBuffer();

      while(!BufferUtil.hasContent(buffer) || !parser.parseNext(buffer)) {
         int len = in.fillBuffer();
         if (len == 0) {
            break;
         }

         if (len <= 0) {
            parser.atEOF();
            parser.parseNext(buffer);
            break;
         }
      }

   }

   public abstract static class Input {
      protected final ByteBuffer _buffer;
      protected boolean _eof;
      protected HttpParser _parser;

      public Input() {
         this(BufferUtil.allocate(8192));
      }

      Input(ByteBuffer buffer) {
         this._eof = false;
         this._buffer = buffer;
      }

      public ByteBuffer getBuffer() {
         return this._buffer;
      }

      public void setHttpParser(HttpParser parser) {
         this._parser = parser;
      }

      public HttpParser getHttpParser() {
         return this._parser;
      }

      public HttpParser takeHttpParser() {
         HttpParser p = this._parser;
         this._parser = null;
         return p;
      }

      public boolean isEOF() {
         return BufferUtil.isEmpty(this._buffer) && this._eof;
      }

      public abstract int fillBuffer() throws IOException;
   }

   public abstract static class Message extends HttpFields.Mutable implements HttpParser.HttpHandler {
      boolean _earlyEOF;
      boolean _complete = false;
      ByteArrayOutputStream _content;
      HttpVersion _version;

      public Message() {
         this._version = HttpVersion.HTTP_1_0;
      }

      public boolean isComplete() {
         return this._complete;
      }

      public HttpVersion getVersion() {
         return this._version;
      }

      public void setVersion(String version) {
         this.setVersion((HttpVersion)HttpVersion.CACHE.get(version));
      }

      public void setVersion(HttpVersion version) {
         this._version = version;
      }

      public void setContent(byte[] bytes) {
         try {
            this._content = new ByteArrayOutputStream();
            this._content.write(bytes);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public void setContent(String content) {
         try {
            this._content = new ByteArrayOutputStream();
            this._content.write(StringUtil.getBytes(content));
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public void setContent(ByteBuffer content) {
         try {
            this._content = new ByteArrayOutputStream();
            this._content.write(BufferUtil.toArray(content));
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public byte[] getContentBytes() {
         return this._content == null ? null : this._content.toByteArray();
      }

      public String getContent() {
         if (this._content == null) {
            return null;
         } else {
            byte[] bytes = this._content.toByteArray();
            String contentType = this.get(HttpHeader.CONTENT_TYPE);
            String encoding = MimeTypes.getCharsetFromContentType(contentType);
            Charset charset = encoding == null ? StandardCharsets.UTF_8 : Charset.forName(encoding);
            return new String(bytes, charset);
         }
      }

      public void parsedHeader(HttpField field) {
         this.add(field.getName(), field.getValue());
      }

      public boolean contentComplete() {
         return false;
      }

      public boolean messageComplete() {
         this._complete = true;
         return true;
      }

      public boolean headerComplete() {
         this._content = new ByteArrayOutputStream();
         return false;
      }

      public void earlyEOF() {
         this._earlyEOF = true;
      }

      public boolean isEarlyEOF() {
         return this._earlyEOF;
      }

      public boolean content(ByteBuffer ref) {
         try {
            this._content.write(BufferUtil.toArray(ref));
            return false;
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public void badMessage(BadMessageException failure) {
         throw failure;
      }

      public ByteBuffer generate() {
         try {
            HttpGenerator generator = new HttpGenerator();
            MetaData info = this.getInfo();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteBuffer header = null;
            ByteBuffer chunk = null;
            ByteBuffer content = this._content == null ? null : ByteBuffer.wrap(this._content.toByteArray());

            while(true) {
               if (!generator.isEnd()) {
                  HttpGenerator.Result result = info instanceof MetaData.Request ? generator.generateRequest((MetaData.Request)info, header, chunk, content, true) : generator.generateResponse((MetaData.Response)info, false, header, chunk, content, true);
                  switch (result) {
                     case NEED_HEADER:
                        header = BufferUtil.allocate(8192);
                        continue;
                     case HEADER_OVERFLOW:
                        if (header.capacity() >= 32768) {
                           throw new BadMessageException(500, "Header too large");
                        }

                        header = BufferUtil.allocate(32768);
                        continue;
                     case NEED_CHUNK:
                        chunk = BufferUtil.allocate(12);
                        continue;
                     case NEED_CHUNK_TRAILER:
                        chunk = BufferUtil.allocate(8192);
                        continue;
                     case NEED_INFO:
                        throw new IllegalStateException();
                     case FLUSH:
                        if (BufferUtil.hasContent(header)) {
                           out.write(BufferUtil.toArray(header));
                           BufferUtil.clear(header);
                        }

                        if (BufferUtil.hasContent(chunk)) {
                           out.write(BufferUtil.toArray(chunk));
                           BufferUtil.clear(chunk);
                        }

                        if (BufferUtil.hasContent(content)) {
                           out.write(BufferUtil.toArray(content));
                           BufferUtil.clear(content);
                        }
                        continue;
                     case SHUTDOWN_OUT:
                        break;
                     default:
                        continue;
                  }
               }

               return ByteBuffer.wrap(out.toByteArray());
            }
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }

      public abstract MetaData getInfo();
   }

   public static class Request extends Message implements HttpParser.RequestHandler {
      private String _method;
      private String _uri;

      public void startRequest(String method, String uri, HttpVersion version) {
         this._method = method;
         this._uri = uri;
         this._version = version;
      }

      public String getMethod() {
         return this._method;
      }

      public String getUri() {
         return this._uri;
      }

      public void setMethod(String method) {
         this._method = method;
      }

      public void setURI(String uri) {
         this._uri = uri;
      }

      public MetaData.Request getInfo() {
         return new MetaData.Request(this._method, HttpURI.from(this._uri), this._version, this, this._content == null ? 0L : (long)this._content.size());
      }

      public String toString() {
         return String.format("%s %s %s\n%s\n", this._method, this._uri, this._version, super.toString());
      }

      public void setHeader(String name, String value) {
         this.put(name, value);
      }
   }

   public static class Response extends Message implements HttpParser.ResponseHandler {
      private int _status;
      private String _reason;

      public void startResponse(HttpVersion version, int status, String reason) {
         this._version = version;
         this._status = status;
         this._reason = reason;
      }

      public int getStatus() {
         return this._status;
      }

      public String getReason() {
         return this._reason;
      }

      public MetaData.Response getInfo() {
         return new MetaData.Response(this._version, this._status, this._reason, this, this._content == null ? -1L : (long)this._content.size());
      }

      public String toString() {
         return String.format("%s %s %s\n%s\n", this._version, this._status, this._reason, super.toString());
      }
   }
}
