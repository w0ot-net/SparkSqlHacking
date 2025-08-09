package org.sparkproject.jetty.http;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.StringUtil;

public class HttpGenerator {
   private static final Logger LOG = LoggerFactory.getLogger(HttpGenerator.class);
   public static final boolean __STRICT = Boolean.getBoolean("org.sparkproject.jetty.http.HttpGenerator.STRICT");
   private static final byte[] __colon_space = new byte[]{58, 32};
   public static final MetaData.Response CONTINUE_100_INFO;
   public static final MetaData.Response PROGRESS_102_INFO;
   public static final MetaData.Response RESPONSE_500_INFO;
   public static final int CHUNK_SIZE = 12;
   private State _state;
   private HttpTokens.EndOfContent _endOfContent;
   private MetaData _info;
   private long _contentPrepared;
   private boolean _noContentResponse;
   private Boolean _persistent;
   private final int _send;
   private static final int SEND_SERVER = 1;
   private static final int SEND_XPOWEREDBY = 2;
   private static final Index ASSUMED_CONTENT_METHODS;
   private boolean _needCRLF;
   private static final byte[] ZERO_CHUNK;
   private static final byte[] LAST_CHUNK;
   private static final byte[] CONTENT_LENGTH_0;
   private static final byte[] CONNECTION_CLOSE;
   private static final byte[] HTTP_1_1_SPACE;
   private static final byte[] TRANSFER_ENCODING_CHUNKED;
   private static final byte[][] SEND;
   private static final PreparedResponse[] __preprepared;

   public static void setJettyVersion(String serverVersion) {
      SEND[1] = StringUtil.getBytes("Server: " + serverVersion + "\r\n");
      SEND[2] = StringUtil.getBytes("X-Powered-By: " + serverVersion + "\r\n");
      SEND[3] = StringUtil.getBytes("Server: " + serverVersion + "\r\nX-Powered-By: " + serverVersion + "\r\n");
   }

   public HttpGenerator() {
      this(false, false);
   }

   public HttpGenerator(boolean sendServerVersion, boolean sendXPoweredBy) {
      this._state = HttpGenerator.State.START;
      this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
      this._contentPrepared = 0L;
      this._noContentResponse = false;
      this._persistent = null;
      this._needCRLF = false;
      this._send = (sendServerVersion ? 1 : 0) | (sendXPoweredBy ? 2 : 0);
   }

   public void reset() {
      this._state = HttpGenerator.State.START;
      this._info = null;
      this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
      this._noContentResponse = false;
      this._persistent = null;
      this._contentPrepared = 0L;
      this._needCRLF = false;
   }

   public State getState() {
      return this._state;
   }

   public boolean isState(State state) {
      return this._state == state;
   }

   public boolean isIdle() {
      return this._state == HttpGenerator.State.START;
   }

   public boolean isEnd() {
      return this._state == HttpGenerator.State.END;
   }

   public boolean isCommitted() {
      return this._state.ordinal() >= HttpGenerator.State.COMMITTED.ordinal();
   }

   public boolean isChunking() {
      return this._endOfContent == HttpTokens.EndOfContent.CHUNKED_CONTENT;
   }

   public boolean isNoContent() {
      return this._noContentResponse;
   }

   public void setPersistent(boolean persistent) {
      this._persistent = persistent;
   }

   public boolean isPersistent() {
      return Boolean.TRUE.equals(this._persistent);
   }

   public boolean isWritten() {
      return this._contentPrepared > 0L;
   }

   public long getContentPrepared() {
      return this._contentPrepared;
   }

   public void abort() {
      this._persistent = false;
      this._state = HttpGenerator.State.END;
      this._endOfContent = null;
   }

   public Result generateRequest(MetaData.Request info, ByteBuffer header, ByteBuffer chunk, ByteBuffer content, boolean last) throws IOException {
      switch (this._state.ordinal()) {
         case 0:
            if (info == null) {
               return HttpGenerator.Result.NEED_INFO;
            } else {
               this._info = info;
               if (header == null) {
                  return HttpGenerator.Result.NEED_HEADER;
               } else {
                  int pos = BufferUtil.flipToFill(header);

                  Result var19;
                  try {
                     this.generateRequestLine(info, header);
                     if (info.getHttpVersion() == HttpVersion.HTTP_0_9) {
                        throw new BadMessageException(500, "HTTP/0.9 not supported");
                     }

                     this.generateHeaders(header, content, last);
                     boolean expect100 = info.getFields().contains(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString());
                     if (expect100) {
                        this._state = HttpGenerator.State.COMMITTED;
                     } else {
                        int len = BufferUtil.length(content);
                        if (len > 0) {
                           this._contentPrepared += (long)len;
                           if (this.isChunking()) {
                              this.prepareChunk(header, len);
                           }
                        }

                        this._state = last ? HttpGenerator.State.COMPLETING : HttpGenerator.State.COMMITTED;
                     }

                     var19 = HttpGenerator.Result.FLUSH;
                  } catch (BadMessageException e) {
                     throw e;
                  } catch (BufferOverflowException e) {
                     LOG.trace("IGNORED", e);
                     var19 = HttpGenerator.Result.HEADER_OVERFLOW;
                     return var19;
                  } catch (Exception e) {
                     throw new BadMessageException(500, e.getMessage(), e);
                  } finally {
                     BufferUtil.flipToFlush(header, pos);
                  }

                  return var19;
               }
            }
         case 1:
            return this.committed(chunk, content, last);
         case 2:
            return this.completing(chunk, content);
         case 3:
         default:
            throw new IllegalStateException();
         case 4:
            if (BufferUtil.hasContent(content)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("discarding content in COMPLETING");
               }

               BufferUtil.clear(content);
            }

            return HttpGenerator.Result.DONE;
      }
   }

   private Result committed(ByteBuffer chunk, ByteBuffer content, boolean last) {
      int len = BufferUtil.length(content);
      if (len > 0) {
         if (this.isChunking()) {
            if (chunk == null) {
               return HttpGenerator.Result.NEED_CHUNK;
            }

            BufferUtil.clearToFill(chunk);
            this.prepareChunk(chunk, len);
            BufferUtil.flipToFlush(chunk, 0);
         }

         this._contentPrepared += (long)len;
      }

      if (last) {
         this._state = HttpGenerator.State.COMPLETING;
         return len > 0 ? HttpGenerator.Result.FLUSH : HttpGenerator.Result.CONTINUE;
      } else {
         return len > 0 ? HttpGenerator.Result.FLUSH : HttpGenerator.Result.DONE;
      }
   }

   private Result completing(ByteBuffer chunk, ByteBuffer content) {
      if (BufferUtil.hasContent(content)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("discarding content in COMPLETING");
         }

         BufferUtil.clear(content);
      }

      if (!this.isChunking()) {
         this._state = HttpGenerator.State.END;
         return Boolean.TRUE.equals(this._persistent) ? HttpGenerator.Result.DONE : HttpGenerator.Result.SHUTDOWN_OUT;
      } else {
         if (this._info.getTrailerSupplier() != null) {
            if (chunk == null || chunk.capacity() <= 12) {
               return HttpGenerator.Result.NEED_CHUNK_TRAILER;
            }

            HttpFields trailers = (HttpFields)this._info.getTrailerSupplier().get();
            if (trailers != null) {
               BufferUtil.clearToFill(chunk);
               this.generateTrailers(chunk, trailers);
               BufferUtil.flipToFlush(chunk, 0);
               this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
               return HttpGenerator.Result.FLUSH;
            }
         }

         if (chunk == null) {
            return HttpGenerator.Result.NEED_CHUNK;
         } else {
            BufferUtil.clearToFill(chunk);
            this.prepareChunk(chunk, 0);
            BufferUtil.flipToFlush(chunk, 0);
            this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
            return HttpGenerator.Result.FLUSH;
         }
      }
   }

   public Result generateResponse(MetaData.Response info, boolean head, ByteBuffer header, ByteBuffer chunk, ByteBuffer content, boolean last) throws IOException {
      switch (this._state.ordinal()) {
         case 0:
            if (info == null) {
               return HttpGenerator.Result.NEED_INFO;
            } else {
               this._info = info;
               HttpVersion version = info.getHttpVersion();
               if (version == null) {
                  throw new BadMessageException(500, "No version");
               } else if (version == HttpVersion.HTTP_0_9) {
                  this._persistent = false;
                  this._endOfContent = HttpTokens.EndOfContent.EOF_CONTENT;
                  if (BufferUtil.hasContent(content)) {
                     this._contentPrepared += (long)content.remaining();
                  }

                  this._state = last ? HttpGenerator.State.COMPLETING : HttpGenerator.State.COMMITTED;
                  return HttpGenerator.Result.FLUSH;
               } else if (header == null) {
                  return HttpGenerator.Result.NEED_HEADER;
               } else {
                  int pos = BufferUtil.flipToFill(header);

                  try {
                     this.generateResponseLine(info, header);
                     int status = info.getStatus();
                     if (HttpStatus.isInformational(status)) {
                        this._noContentResponse = true;
                        switch (status) {
                           case 101:
                              break;
                           case 103:
                              this.generateHeaders(header, content, last);
                              this._state = HttpGenerator.State.COMPLETING_1XX;
                              Result var20 = HttpGenerator.Result.FLUSH;
                              return var20;
                           default:
                              header.put(HttpTokens.CRLF);
                              this._state = HttpGenerator.State.COMPLETING_1XX;
                              Result var21 = HttpGenerator.Result.FLUSH;
                              return var21;
                        }
                     } else if (status == 204 || status == 304) {
                        this._noContentResponse = true;
                     }

                     this.generateHeaders(header, content, last);
                     int len = BufferUtil.length(content);
                     if (len > 0) {
                        this._contentPrepared += (long)len;
                        if (this.isChunking() && !head) {
                           this.prepareChunk(header, len);
                        }
                     }

                     this._state = last ? HttpGenerator.State.COMPLETING : HttpGenerator.State.COMMITTED;
                     return HttpGenerator.Result.FLUSH;
                  } catch (BadMessageException e) {
                     throw e;
                  } catch (BufferOverflowException e) {
                     LOG.trace("IGNORED", e);
                     Result len = HttpGenerator.Result.HEADER_OVERFLOW;
                     return len;
                  } catch (Exception e) {
                     throw new BadMessageException(500, e.getMessage(), e);
                  } finally {
                     BufferUtil.flipToFlush(header, pos);
                  }
               }
            }
         case 1:
            return this.committed(chunk, content, last);
         case 2:
            return this.completing(chunk, content);
         case 3:
            this.reset();
            return HttpGenerator.Result.DONE;
         case 4:
            if (BufferUtil.hasContent(content)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("discarding content in COMPLETING");
               }

               BufferUtil.clear(content);
            }

            return HttpGenerator.Result.DONE;
         default:
            throw new IllegalStateException();
      }
   }

   public void servletUpgrade() {
      this._noContentResponse = false;
      this._state = HttpGenerator.State.COMMITTED;
   }

   private void prepareChunk(ByteBuffer chunk, int remaining) {
      if (this._needCRLF) {
         BufferUtil.putCRLF(chunk);
      }

      if (remaining > 0) {
         BufferUtil.putHexInt(chunk, remaining);
         BufferUtil.putCRLF(chunk);
         this._needCRLF = true;
      } else {
         chunk.put(LAST_CHUNK);
         this._needCRLF = false;
      }

   }

   private void generateTrailers(ByteBuffer buffer, HttpFields trailer) {
      if (this._needCRLF) {
         BufferUtil.putCRLF(buffer);
      }

      buffer.put(ZERO_CHUNK);
      int n = trailer.size();

      for(int f = 0; f < n; ++f) {
         HttpField field = trailer.getField(f);
         putTo(field, buffer);
      }

      BufferUtil.putCRLF(buffer);
   }

   private void generateRequestLine(MetaData.Request request, ByteBuffer header) {
      header.put(StringUtil.getBytes(request.getMethod()));
      header.put((byte)32);
      header.put(StringUtil.getBytes(request.getURIString()));
      header.put((byte)32);
      header.put(request.getHttpVersion().toBytes());
      header.put(HttpTokens.CRLF);
   }

   private void generateResponseLine(MetaData.Response response, ByteBuffer header) {
      int status = response.getStatus();
      PreparedResponse preprepared = status < __preprepared.length ? __preprepared[status] : null;
      String reason = response.getReason();
      if (preprepared != null) {
         if (reason == null) {
            header.put(preprepared._responseLine);
         } else {
            header.put(preprepared._schemeCode);
            header.put(this.getReasonBytes(reason));
            header.put(HttpTokens.CRLF);
         }
      } else {
         header.put(HTTP_1_1_SPACE);
         header.put((byte)(48 + status / 100));
         header.put((byte)(48 + status % 100 / 10));
         header.put((byte)(48 + status % 10));
         header.put((byte)32);
         if (reason == null) {
            header.put((byte)(48 + status / 100));
            header.put((byte)(48 + status % 100 / 10));
            header.put((byte)(48 + status % 10));
         } else {
            header.put(this.getReasonBytes(reason));
         }

         header.put(HttpTokens.CRLF);
      }

   }

   private byte[] getReasonBytes(String reason) {
      if (reason.length() > 1024) {
         reason = reason.substring(0, 1024);
      }

      byte[] bytes = StringUtil.getBytes(reason);
      int i = bytes.length;

      while(i-- > 0) {
         if (bytes[i] == 13 || bytes[i] == 10) {
            bytes[i] = 63;
         }
      }

      return bytes;
   }

   private void generateHeaders(ByteBuffer header, ByteBuffer content, boolean last) {
      MetaData.Request request = this._info instanceof MetaData.Request ? (MetaData.Request)this._info : null;
      MetaData.Response response = this._info instanceof MetaData.Response ? (MetaData.Response)this._info : null;
      if (LOG.isDebugEnabled()) {
         LOG.debug("generateHeaders {} last={} content={}", new Object[]{this._info, last, BufferUtil.toDetailString(content)});
         LOG.debug(this._info.getFields().toString());
      }

      int send = this._send;
      HttpField transferEncoding = null;
      boolean http11 = this._info.getHttpVersion() == HttpVersion.HTTP_1_1;
      boolean close = false;
      boolean chunkedHint = this._info.getTrailerSupplier() != null;
      boolean contentType = false;
      long contentLength = this._info.getContentLength();
      boolean contentLengthField = false;
      HttpFields fields = this._info.getFields();
      if (fields != null) {
         int n = fields.size();

         for(int f = 0; f < n; ++f) {
            HttpField field = fields.getField(f);
            HttpHeader h = field.getHeader();
            if (h == null) {
               putTo(field, header);
            } else {
               switch (h) {
                  case CONTENT_LENGTH:
                     if (contentLength < 0L) {
                        contentLength = field.getLongValue();
                     } else if (contentLength != field.getLongValue()) {
                        throw new BadMessageException(500, String.format("Incorrect Content-Length %d!=%d", contentLength, field.getLongValue()));
                     }

                     contentLengthField = true;
                     break;
                  case CONTENT_TYPE:
                     contentType = true;
                     putTo(field, header);
                     break;
                  case TRANSFER_ENCODING:
                     if (http11) {
                        transferEncoding = field;
                        chunkedHint = field.contains(HttpHeaderValue.CHUNKED.asString());
                     }
                     break;
                  case CONNECTION:
                     boolean keepAlive = field.contains(HttpHeaderValue.KEEP_ALIVE.asString());
                     if (keepAlive && this._info.getHttpVersion() == HttpVersion.HTTP_1_0 && this._persistent == null) {
                        this._persistent = true;
                     }

                     if (field.contains(HttpHeaderValue.CLOSE.asString())) {
                        close = true;
                        this._persistent = false;
                     }

                     if (keepAlive && this._persistent == Boolean.FALSE) {
                        field = new HttpField(HttpHeader.CONNECTION, (String)Stream.of(field.getValues()).filter((s) -> !HttpHeaderValue.KEEP_ALIVE.is(s)).collect(Collectors.joining(", ")));
                     }

                     putTo(field, header);
                     break;
                  case SERVER:
                     send &= -2;
                     putTo(field, header);
                     break;
                  default:
                     putTo(field, header);
               }
            }
         }
      }

      if (last && contentLength < 0L && this._info.getTrailerSupplier() == null) {
         contentLength = this._contentPrepared + (long)BufferUtil.length(content);
      }

      boolean assumedContentRequest = request != null && ASSUMED_CONTENT_METHODS.get(request.getMethod()) != null;
      boolean assumedContent = assumedContentRequest || contentType || chunkedHint;
      boolean noContentRequest = request != null && contentLength <= 0L && !assumedContent;
      if (this._persistent == null) {
         this._persistent = http11 || request != null && HttpMethod.CONNECT.is(request.getMethod());
      }

      if (!this._noContentResponse && !noContentRequest) {
         if (!http11 || !chunkedHint && (contentLength >= 0L || !this._persistent && !assumedContentRequest)) {
            if (contentLength < 0L || request == null && !this._persistent) {
               if (response == null) {
                  throw new BadMessageException(500, "Unknown content length for request");
               }

               this._endOfContent = HttpTokens.EndOfContent.EOF_CONTENT;
               this._persistent = false;
               if (contentLength >= 0L && (contentLength > 0L || assumedContent || contentLengthField)) {
                  putContentLength(header, contentLength);
               }

               if (http11 && !close) {
                  header.put(CONNECTION_CLOSE);
               }
            } else {
               this._endOfContent = HttpTokens.EndOfContent.CONTENT_LENGTH;
               putContentLength(header, contentLength);
            }
         } else {
            this._endOfContent = HttpTokens.EndOfContent.CHUNKED_CONTENT;
            if (transferEncoding == null) {
               header.put(TRANSFER_ENCODING_CHUNKED);
            } else if (transferEncoding.toString().endsWith(HttpHeaderValue.CHUNKED.toString())) {
               putTo(transferEncoding, header);
               transferEncoding = null;
            } else {
               if (chunkedHint) {
                  throw new BadMessageException(500, "Bad Transfer-Encoding");
               }

               putTo(new HttpField(HttpHeader.TRANSFER_ENCODING, transferEncoding.getValue() + ",chunked"), header);
               transferEncoding = null;
            }
         }
      } else {
         this._endOfContent = HttpTokens.EndOfContent.NO_CONTENT;
         if (this._contentPrepared > 0L) {
            throw new BadMessageException(500, "Content for no content response");
         }

         if (contentLengthField) {
            if (response != null && response.getStatus() == 304) {
               putContentLength(header, contentLength);
            } else if (contentLength > 0L) {
               if (this._contentPrepared != 0L || !last) {
                  throw new BadMessageException(500, "Content for no content response");
               }

               content.clear();
            }
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug(this._endOfContent.toString());
      }

      if (transferEncoding != null) {
         if (chunkedHint) {
            String v = transferEncoding.getValue();
            int c = v.lastIndexOf(44);
            if (c > 0 && v.lastIndexOf(HttpHeaderValue.CHUNKED.toString(), c) > c) {
               putTo(new HttpField(HttpHeader.TRANSFER_ENCODING, v.substring(0, c).trim()), header);
            }
         } else {
            putTo(transferEncoding, header);
         }
      }

      int status = response != null ? response.getStatus() : -1;
      if (status > 199) {
         header.put(SEND[send]);
      }

      header.put(HttpTokens.CRLF);
   }

   private static void putContentLength(ByteBuffer header, long contentLength) {
      if (contentLength == 0L) {
         header.put(CONTENT_LENGTH_0);
      } else {
         header.put(HttpHeader.CONTENT_LENGTH.getBytesColonSpace());
         BufferUtil.putDecLong(header, contentLength);
         header.put(HttpTokens.CRLF);
      }

   }

   public static byte[] getReasonBuffer(int code) {
      PreparedResponse status = code < __preprepared.length ? __preprepared[code] : null;
      return status != null ? status._reason : null;
   }

   public String toString() {
      return String.format("%s@%x{s=%s}", this.getClass().getSimpleName(), this.hashCode(), this._state);
   }

   private static void putSanitisedName(String s, ByteBuffer buffer) {
      int l = s.length();

      for(int i = 0; i < l; ++i) {
         char c = s.charAt(i);
         if (c >= 0 && c <= 255 && c != '\r' && c != '\n' && c != ':') {
            buffer.put((byte)(255 & c));
         } else {
            buffer.put((byte)63);
         }
      }

   }

   private static void putSanitisedValue(String s, ByteBuffer buffer) {
      int l = s.length();

      for(int i = 0; i < l; ++i) {
         char c = s.charAt(i);
         if (c >= 0 && c <= 255 && c != '\r' && c != '\n') {
            buffer.put((byte)(255 & c));
         } else {
            buffer.put((byte)32);
         }
      }

   }

   public static void putTo(HttpField field, ByteBuffer bufferInFillMode) {
      if (field instanceof PreEncodedHttpField) {
         ((PreEncodedHttpField)field).putTo(bufferInFillMode, HttpVersion.HTTP_1_0);
      } else {
         HttpHeader header = field.getHeader();
         if (header != null) {
            bufferInFillMode.put(header.getBytesColonSpace());
            putSanitisedValue(field.getValue(), bufferInFillMode);
         } else {
            putSanitisedName(field.getName(), bufferInFillMode);
            bufferInFillMode.put(__colon_space);
            putSanitisedValue(field.getValue(), bufferInFillMode);
         }

         BufferUtil.putCRLF(bufferInFillMode);
      }

   }

   public static void putTo(HttpFields.Mutable fields, ByteBuffer bufferInFillMode) {
      for(HttpField field : fields) {
         if (field != null) {
            putTo(field, bufferInFillMode);
         }
      }

      BufferUtil.putCRLF(bufferInFillMode);
   }

   static {
      CONTINUE_100_INFO = new MetaData.Response(HttpVersion.HTTP_1_1, 100, (String)null, (HttpFields)null, -1L);
      PROGRESS_102_INFO = new MetaData.Response(HttpVersion.HTTP_1_1, 102, (String)null, (HttpFields)null, -1L);
      RESPONSE_500_INFO = new MetaData.Response(HttpVersion.HTTP_1_1, 500, (String)null, HttpFields.build().put(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE), 0L);
      ASSUMED_CONTENT_METHODS = (new Index.Builder()).caseSensitive(false).with(HttpMethod.POST.asString(), Boolean.TRUE).with(HttpMethod.PUT.asString(), Boolean.TRUE).build();
      ZERO_CHUNK = new byte[]{48, 13, 10};
      LAST_CHUNK = new byte[]{48, 13, 10, 13, 10};
      CONTENT_LENGTH_0 = StringUtil.getBytes("Content-Length: 0\r\n");
      CONNECTION_CLOSE = StringUtil.getBytes("Connection: close\r\n");
      HTTP_1_1_SPACE = StringUtil.getBytes(String.valueOf(HttpVersion.HTTP_1_1) + " ");
      TRANSFER_ENCODING_CHUNKED = StringUtil.getBytes("Transfer-Encoding: chunked\r\n");
      SEND = new byte[][]{new byte[0], StringUtil.getBytes("Server: Jetty(10.x.x)\r\n"), StringUtil.getBytes("X-Powered-By: Jetty(10.x.x)\r\n"), StringUtil.getBytes("Server: Jetty(10.x.x)\r\nX-Powered-By: Jetty(10.x.x)\r\n")};
      __preprepared = new PreparedResponse[512];
      int versionLength = HttpVersion.HTTP_1_1.toString().length();

      for(int i = 0; i < __preprepared.length; ++i) {
         HttpStatus.Code code = HttpStatus.getCode(i);
         if (code != null) {
            String reason = code.getMessage();
            byte[] line = new byte[versionLength + 5 + reason.length() + 2];
            HttpVersion.HTTP_1_1.toBuffer().get(line, 0, versionLength);
            line[versionLength + 0] = 32;
            line[versionLength + 1] = (byte)(48 + i / 100);
            line[versionLength + 2] = (byte)(48 + i % 100 / 10);
            line[versionLength + 3] = (byte)(48 + i % 10);
            line[versionLength + 4] = 32;

            for(int j = 0; j < reason.length(); ++j) {
               line[versionLength + 5 + j] = (byte)reason.charAt(j);
            }

            line[versionLength + 5 + reason.length()] = 13;
            line[versionLength + 6 + reason.length()] = 10;
            __preprepared[i] = new PreparedResponse();
            __preprepared[i]._schemeCode = Arrays.copyOfRange(line, 0, versionLength + 5);
            __preprepared[i]._reason = Arrays.copyOfRange(line, versionLength + 5, line.length - 2);
            __preprepared[i]._responseLine = line;
         }
      }

   }

   public static enum State {
      START,
      COMMITTED,
      COMPLETING,
      COMPLETING_1XX,
      END;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{START, COMMITTED, COMPLETING, COMPLETING_1XX, END};
      }
   }

   public static enum Result {
      NEED_CHUNK,
      NEED_INFO,
      NEED_HEADER,
      HEADER_OVERFLOW,
      NEED_CHUNK_TRAILER,
      FLUSH,
      CONTINUE,
      SHUTDOWN_OUT,
      DONE;

      // $FF: synthetic method
      private static Result[] $values() {
         return new Result[]{NEED_CHUNK, NEED_INFO, NEED_HEADER, HEADER_OVERFLOW, NEED_CHUNK_TRAILER, FLUSH, CONTINUE, SHUTDOWN_OUT, DONE};
      }
   }

   private static class PreparedResponse {
      byte[] _reason;
      byte[] _schemeCode;
      byte[] _responseLine;
   }
}
