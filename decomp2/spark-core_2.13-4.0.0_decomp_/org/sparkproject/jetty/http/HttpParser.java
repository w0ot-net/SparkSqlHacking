package org.sparkproject.jetty.http;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.Utf8StringBuilder;

public class HttpParser {
   public static final Logger LOG = LoggerFactory.getLogger(HttpParser.class);
   public static final int INITIAL_URI_LENGTH = 256;
   private static final int MAX_CHUNK_LENGTH = 134217711;
   public static final Index CACHE;
   private static final Index.Mutable NO_CACHE;
   private static final EnumSet __idleStates;
   private static final EnumSet __completeStates;
   private static final EnumSet __terminatedStates;
   private final boolean debugEnabled;
   private final HttpHandler _handler;
   private final RequestHandler _requestHandler;
   private final ResponseHandler _responseHandler;
   private final ComplianceViolation.Listener _complianceListener;
   private final int _maxHeaderBytes;
   private final HttpCompliance _complianceMode;
   private final Utf8StringBuilder _uri;
   private final FieldCache _fieldCache;
   private HttpField _field;
   private HttpHeader _header;
   private String _headerString;
   private String _valueString;
   private int _responseStatus;
   private int _headerBytes;
   private String _parsedHost;
   private boolean _headerComplete;
   private volatile State _state;
   private volatile FieldState _fieldState;
   private volatile boolean _eof;
   private HttpMethod _method;
   private String _methodString;
   private HttpVersion _version;
   private HttpTokens.EndOfContent _endOfContent;
   private boolean _hasContentLength;
   private boolean _hasTransferEncoding;
   private long _contentLength;
   private long _contentPosition;
   private int _chunkLength;
   private int _chunkPosition;
   private boolean _headResponse;
   private boolean _cr;
   private ByteBuffer _contentChunk;
   private int _length;
   private final StringBuilder _string;
   private long _beginNanoTime;

   private static HttpCompliance compliance() {
      return HttpCompliance.RFC7230;
   }

   public HttpParser(RequestHandler handler) {
      this((RequestHandler)handler, -1, compliance());
   }

   public HttpParser(ResponseHandler handler) {
      this((ResponseHandler)handler, -1, compliance());
   }

   public HttpParser(RequestHandler handler, int maxHeaderBytes) {
      this(handler, maxHeaderBytes, compliance());
   }

   public HttpParser(ResponseHandler handler, int maxHeaderBytes) {
      this(handler, maxHeaderBytes, compliance());
   }

   public HttpParser(RequestHandler handler, HttpCompliance compliance) {
      this((RequestHandler)handler, -1, compliance);
   }

   public HttpParser(RequestHandler handler, int maxHeaderBytes, HttpCompliance compliance) {
      this(handler, (ResponseHandler)null, maxHeaderBytes, compliance == null ? compliance() : compliance);
   }

   public HttpParser(ResponseHandler handler, int maxHeaderBytes, HttpCompliance compliance) {
      this((RequestHandler)null, handler, maxHeaderBytes, compliance == null ? compliance() : compliance);
   }

   private HttpParser(RequestHandler requestHandler, ResponseHandler responseHandler, int maxHeaderBytes, HttpCompliance compliance) {
      this.debugEnabled = LOG.isDebugEnabled();
      this._uri = new Utf8StringBuilder(256);
      this._fieldCache = new FieldCache();
      this._state = HttpParser.State.START;
      this._fieldState = HttpParser.FieldState.FIELD;
      this._contentLength = -1L;
      this._string = new StringBuilder();
      this._beginNanoTime = Long.MIN_VALUE;
      this._handler = (HttpHandler)(requestHandler != null ? requestHandler : responseHandler);
      this._requestHandler = requestHandler;
      this._responseHandler = responseHandler;
      this._maxHeaderBytes = maxHeaderBytes;
      this._complianceMode = compliance;
      this._complianceListener = (ComplianceViolation.Listener)(this._handler instanceof ComplianceViolation.Listener ? this._handler : null);
   }

   public long getBeginNanoTime() {
      return this._beginNanoTime;
   }

   public HttpHandler getHandler() {
      return this._handler;
   }

   public int getHeaderCacheSize() {
      return this._fieldCache.getCapacity();
   }

   public void setHeaderCacheSize(int headerCacheSize) {
      this._fieldCache.setCapacity(headerCacheSize);
   }

   public boolean isHeaderCacheCaseSensitive() {
      return this._fieldCache.isCaseSensitive();
   }

   public void setHeaderCacheCaseSensitive(boolean headerCacheCaseSensitive) {
      this._fieldCache.setCaseSensitive(headerCacheCaseSensitive);
   }

   protected void checkViolation(HttpCompliance.Violation violation) throws BadMessageException {
      if (violation.isAllowedBy(this._complianceMode)) {
         this.reportComplianceViolation(violation, violation.getDescription());
      } else {
         throw new BadMessageException(400, violation.getDescription());
      }
   }

   protected void reportComplianceViolation(HttpCompliance.Violation violation) {
      this.reportComplianceViolation(violation, violation.getDescription());
   }

   protected void reportComplianceViolation(HttpCompliance.Violation violation, String reason) {
      if (this._complianceListener != null) {
         this._complianceListener.onComplianceViolation(this._complianceMode, violation, reason);
      }

   }

   protected String caseInsensitiveHeader(String orig, String normative) {
      if (HttpCompliance.Violation.CASE_SENSITIVE_FIELD_NAME.isAllowedBy(this._complianceMode)) {
         return normative;
      } else {
         if (!orig.equals(normative)) {
            this.reportComplianceViolation(HttpCompliance.Violation.CASE_SENSITIVE_FIELD_NAME, orig);
         }

         return orig;
      }
   }

   public long getContentLength() {
      return this._contentLength;
   }

   public long getContentRead() {
      return this._contentPosition;
   }

   public int getHeaderLength() {
      return this._headerBytes;
   }

   public void setHeadResponse(boolean head) {
      this._headResponse = head;
   }

   protected void setResponseStatus(int status) {
      this._responseStatus = status;
   }

   public State getState() {
      return this._state;
   }

   public boolean inContentState() {
      return this._state.ordinal() >= HttpParser.State.CONTENT.ordinal() && this._state.ordinal() < HttpParser.State.END.ordinal();
   }

   public boolean inHeaderState() {
      return this._state.ordinal() < HttpParser.State.CONTENT.ordinal();
   }

   public boolean isChunking() {
      return this._endOfContent == HttpTokens.EndOfContent.CHUNKED_CONTENT;
   }

   public boolean isStart() {
      return this.isState(HttpParser.State.START);
   }

   public boolean isClose() {
      return this.isState(HttpParser.State.CLOSE);
   }

   public boolean isClosed() {
      return this.isState(HttpParser.State.CLOSED);
   }

   public boolean isIdle() {
      return __idleStates.contains(this._state);
   }

   public boolean isComplete() {
      return __completeStates.contains(this._state);
   }

   public boolean isTerminated() {
      return __terminatedStates.contains(this._state);
   }

   public boolean isState(State state) {
      return this._state == state;
   }

   private HttpTokens.Token next(ByteBuffer buffer) {
      byte ch = buffer.get();
      HttpTokens.Token t = HttpTokens.getToken(ch);
      switch (t.getType()) {
         case CNTL:
            throw new IllegalCharacterException(this._state, t, buffer);
         case LF:
            this._cr = false;
            break;
         case CR:
            if (this._cr) {
               throw new BadMessageException("Bad EOL");
            }

            this._cr = true;
            if (!buffer.hasRemaining()) {
               return null;
            }

            if (this._maxHeaderBytes > 0 && (this._state == HttpParser.State.HEADER || this._state == HttpParser.State.TRAILER)) {
               ++this._headerBytes;
            }

            return this.next(buffer);
         case ALPHA:
         case DIGIT:
         case TCHAR:
         case VCHAR:
         case HTAB:
         case SPACE:
         case OTEXT:
         case COLON:
            if (this._cr) {
               throw new BadMessageException("Bad EOL");
            }
      }

      return t;
   }

   private void quickStart(ByteBuffer buffer) {
      if (this._requestHandler != null) {
         this._method = HttpMethod.lookAheadGet(buffer);
         if (this._method != null) {
            this._methodString = this._method.asString();
            buffer.position(buffer.position() + this._methodString.length() + 1);
            this.setState(HttpParser.State.SPACE1);
            return;
         }
      } else if (this._responseHandler != null) {
         this._version = HttpVersion.lookAheadGet(buffer);
         if (this._version != null) {
            buffer.position(buffer.position() + this._version.asString().length() + 1);
            this.setState(HttpParser.State.SPACE1);
            return;
         }
      }

      while(this._state == HttpParser.State.START && buffer.hasRemaining()) {
         HttpTokens.Token t = this.next(buffer);
         if (t == null) {
            break;
         }

         switch (t.getType()) {
            case ALPHA:
            case DIGIT:
            case TCHAR:
            case VCHAR:
               this._string.setLength(0);
               this._string.append(t.getChar());
               this.setState(this._requestHandler != null ? HttpParser.State.METHOD : HttpParser.State.RESPONSE_VERSION);
               return;
            case HTAB:
            case SPACE:
            case OTEXT:
               throw new IllegalCharacterException(this._state, t, buffer);
            default:
               if (this._maxHeaderBytes > 0 && ++this._headerBytes > this._maxHeaderBytes) {
                  LOG.warn("padding is too large >{}", this._maxHeaderBytes);
                  throw new BadMessageException(400);
               }
         }
      }

   }

   private void setString(String s) {
      this._string.setLength(0);
      this._string.append(s);
      this._length = s.length();
   }

   private String takeString() {
      this._string.setLength(this._length);
      String s = this._string.toString();
      this._string.setLength(0);
      this._length = -1;
      return s;
   }

   private boolean handleHeaderContentMessage() {
      boolean handleHeader = this._handler.headerComplete();
      this._headerComplete = true;
      if (handleHeader) {
         return true;
      } else {
         this.setState(HttpParser.State.CONTENT_END);
         return this.handleContentMessage();
      }
   }

   private boolean handleContentMessage() {
      boolean handleContent = this._handler.contentComplete();
      if (handleContent) {
         return true;
      } else {
         this.setState(HttpParser.State.END);
         return this._handler.messageComplete();
      }
   }

   private boolean parseLine(ByteBuffer buffer) {
      boolean handle = false;

      while(true) {
         if (this._state.ordinal() < HttpParser.State.HEADER.ordinal() && buffer.hasRemaining() && !handle) {
            HttpTokens.Token t = this.next(buffer);
            if (t != null) {
               if (this._maxHeaderBytes > 0 && ++this._headerBytes > this._maxHeaderBytes) {
                  if (this._state == HttpParser.State.URI) {
                     LOG.warn("URI is too large >{}", this._maxHeaderBytes);
                     throw new BadMessageException(414);
                  }

                  if (this._requestHandler != null) {
                     LOG.warn("request is too large >{}", this._maxHeaderBytes);
                  } else {
                     LOG.warn("response is too large >{}", this._maxHeaderBytes);
                  }

                  throw new BadMessageException(431);
               }

               switch (this._state.ordinal()) {
                  case 1:
                     switch (t.getType()) {
                        case LF:
                           throw new BadMessageException("No URI");
                        case CR:
                        case VCHAR:
                        case HTAB:
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                           this._string.append(t.getChar());
                           continue;
                        case SPACE:
                           this._length = this._string.length();
                           this._methodString = this.takeString();
                           if (HttpCompliance.Violation.CASE_INSENSITIVE_METHOD.isAllowedBy(this._complianceMode)) {
                              HttpMethod method = (HttpMethod)HttpMethod.INSENSITIVE_CACHE.get(this._methodString);
                              if (method != null) {
                                 if (!method.asString().equals(this._methodString)) {
                                    this.reportComplianceViolation(HttpCompliance.Violation.CASE_INSENSITIVE_METHOD, this._methodString);
                                 }

                                 this._methodString = method.asString();
                              }
                           } else {
                              HttpMethod method = (HttpMethod)HttpMethod.CACHE.get(this._methodString);
                              if (method != null) {
                                 this._methodString = method.asString();
                              }
                           }

                           this.setState(HttpParser.State.SPACE1);
                           continue;
                     }
                  case 2:
                     switch (t.getType()) {
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                        case VCHAR:
                        case COLON:
                           this._string.append(t.getChar());
                           continue;
                        case HTAB:
                        case OTEXT:
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                        case SPACE:
                           this._length = this._string.length();
                           String version = this.takeString();
                           this._version = (HttpVersion)HttpVersion.CACHE.get(version);
                           this.checkVersion();
                           this.setState(HttpParser.State.SPACE1);
                           continue;
                     }
                  case 3:
                     switch (t.getType()) {
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                        case VCHAR:
                        case COLON:
                           if (this._responseHandler != null) {
                              if (t.getType() != HttpTokens.Type.DIGIT) {
                                 throw new IllegalCharacterException(this._state, t, buffer);
                              }

                              this.setState(HttpParser.State.STATUS);
                              this.setResponseStatus(t.getByte() - 48);
                              continue;
                           }

                           this._uri.reset();
                           this.setState(HttpParser.State.URI);
                           if (!buffer.hasArray()) {
                              this._uri.append(t.getByte());
                              continue;
                           }

                           byte[] array = buffer.array();
                           int p = buffer.arrayOffset() + buffer.position();
                           int l = buffer.arrayOffset() + buffer.limit();

                           int i;
                           for(i = p; i < l && array[i] > 32; ++i) {
                           }

                           int len = i - p;
                           this._headerBytes += len;
                           if (this._maxHeaderBytes > 0 && ++this._headerBytes > this._maxHeaderBytes) {
                              LOG.warn("URI is too large >{}", this._maxHeaderBytes);
                              throw new BadMessageException(414);
                           }

                           this._uri.append(array, p - 1, len + 1);
                           buffer.position(i - buffer.arrayOffset());
                           continue;
                        case HTAB:
                        case OTEXT:
                        default:
                           throw new BadMessageException(400, this._requestHandler != null ? "No URI" : "No Status");
                        case SPACE:
                           continue;
                     }
                  case 4:
                     switch (t.getType()) {
                        case LF:
                           this._fieldCache.prepare();
                           this.setState(HttpParser.State.HEADER);
                           this._responseHandler.startResponse(this._version, this._responseStatus, (String)null);
                           continue;
                        case DIGIT:
                           this._responseStatus = this._responseStatus * 10 + (t.getByte() - 48);
                           if (this._responseStatus >= 1000) {
                              throw new BadMessageException("Bad status");
                           }
                           continue;
                        case SPACE:
                           this.setState(HttpParser.State.SPACE2);
                           continue;
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                     }
                  case 5:
                     switch (t.getType()) {
                        case LF:
                           if (!HttpCompliance.Violation.HTTP_0_9.isAllowedBy(this._complianceMode)) {
                              throw new BadMessageException(505, "HTTP/0.9 not supported");
                           }

                           this.reportComplianceViolation(HttpCompliance.Violation.HTTP_0_9, HttpCompliance.Violation.HTTP_0_9.getDescription());
                           this._requestHandler.startRequest(this._methodString, this._uri.toString(), HttpVersion.HTTP_0_9);
                           this.setState(HttpParser.State.CONTENT);
                           this._endOfContent = HttpTokens.EndOfContent.NO_CONTENT;
                           BufferUtil.clear(buffer);
                           handle = this.handleHeaderContentMessage();
                           continue;
                        case CR:
                        case HTAB:
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                        case VCHAR:
                        case OTEXT:
                        case COLON:
                           this._uri.append(t.getByte());
                           continue;
                        case SPACE:
                           this.setState(HttpParser.State.SPACE2);
                           continue;
                     }
                  case 6:
                     switch (t.getType()) {
                        case LF:
                           if (this._responseHandler != null) {
                              this._fieldCache.prepare();
                              this.setState(HttpParser.State.HEADER);
                              this._responseHandler.startResponse(this._version, this._responseStatus, (String)null);
                           } else {
                              this.checkViolation(HttpCompliance.Violation.HTTP_0_9);
                              this._requestHandler.startRequest(this._methodString, this._uri.toString(), HttpVersion.HTTP_0_9);
                              this.setState(HttpParser.State.CONTENT);
                              this._endOfContent = HttpTokens.EndOfContent.NO_CONTENT;
                              BufferUtil.clear(buffer);
                              handle = this.handleHeaderContentMessage();
                           }
                           continue;
                        case CR:
                        case HTAB:
                        case OTEXT:
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                        case VCHAR:
                        case COLON:
                           this._string.setLength(0);
                           this._string.append(t.getChar());
                           if (this._responseHandler != null) {
                              this._length = 1;
                              this.setState(HttpParser.State.REASON);
                           } else {
                              this.setState(HttpParser.State.REQUEST_VERSION);
                              HttpVersion version;
                              if (buffer.position() > 0 && buffer.hasArray()) {
                                 version = HttpVersion.lookAheadGet(buffer.array(), buffer.arrayOffset() + buffer.position() - 1, buffer.arrayOffset() + buffer.limit());
                              } else {
                                 version = (HttpVersion)HttpVersion.CACHE.getBest((ByteBuffer)buffer, 0, buffer.remaining());
                              }

                              if (version != null) {
                                 int pos = buffer.position() + version.asString().length() - 1;
                                 if (pos < buffer.limit()) {
                                    byte n = buffer.get(pos);
                                    if (n == 13) {
                                       this._cr = true;
                                       this._version = version;
                                       this.checkVersion();
                                       this._string.setLength(0);
                                       buffer.position(pos + 1);
                                    } else if (n == 10) {
                                       this._version = version;
                                       this.checkVersion();
                                       this._string.setLength(0);
                                       buffer.position(pos);
                                    }
                                 }
                              }
                           }
                        case SPACE:
                           continue;
                     }
                  case 7:
                     switch (t.getType()) {
                        case LF:
                           if (this._version == null) {
                              this._length = this._string.length();
                              this._version = (HttpVersion)HttpVersion.CACHE.get(this.takeString());
                           }

                           this.checkVersion();
                           this._fieldCache.prepare();
                           this.setState(HttpParser.State.HEADER);
                           this._requestHandler.startRequest(this._methodString, this._uri.toString(), this._version);
                           continue;
                        case CR:
                        case HTAB:
                        case SPACE:
                        case OTEXT:
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                        case VCHAR:
                        case COLON:
                           this._string.append(t.getChar());
                           continue;
                     }
                  case 8:
                     switch (t.getType()) {
                        case LF:
                           String reason = this.takeString();
                           this._fieldCache.prepare();
                           this.setState(HttpParser.State.HEADER);
                           this._responseHandler.startResponse(this._version, this._responseStatus, reason);
                           continue;
                        case CR:
                        default:
                           throw new IllegalCharacterException(this._state, t, buffer);
                        case ALPHA:
                        case DIGIT:
                        case TCHAR:
                        case VCHAR:
                        case OTEXT:
                        case COLON:
                           this._string.append(t.getChar());
                           this._length = this._string.length();
                           continue;
                        case HTAB:
                        case SPACE:
                           this._string.append(t.getChar());
                           continue;
                     }
                  default:
                     throw new IllegalStateException(this._state.toString());
               }
            }
         }

         return handle;
      }
   }

   private void checkVersion() {
      if (this._version == null) {
         throw new BadMessageException(505, "Unknown Version");
      } else if (this._version.getVersion() < 10 || this._version.getVersion() > 20) {
         throw new BadMessageException(505, "Unsupported Version");
      }
   }

   private void parsedHeader() {
      if (this._headerString != null || this._valueString != null) {
         if (this._header != null) {
            boolean addToFieldCache = false;
            switch (this._header) {
               case CONTENT_LENGTH:
                  if (this._hasTransferEncoding) {
                     this.checkViolation(HttpCompliance.Violation.TRANSFER_ENCODING_WITH_CONTENT_LENGTH);
                  }

                  long contentLength = this.convertContentLength(this._valueString);
                  if (this._hasContentLength) {
                     this.checkViolation(HttpCompliance.Violation.MULTIPLE_CONTENT_LENGTHS);
                     if (contentLength != this._contentLength) {
                        throw new BadMessageException(400, HttpCompliance.Violation.MULTIPLE_CONTENT_LENGTHS.getDescription());
                     }
                  }

                  this._hasContentLength = true;
                  if (this._endOfContent != HttpTokens.EndOfContent.CHUNKED_CONTENT) {
                     this._contentLength = contentLength;
                     this._endOfContent = HttpTokens.EndOfContent.CONTENT_LENGTH;
                  }
                  break;
               case TRANSFER_ENCODING:
                  this._hasTransferEncoding = true;
                  if (this._hasContentLength) {
                     this.checkViolation(HttpCompliance.Violation.TRANSFER_ENCODING_WITH_CONTENT_LENGTH);
                  }

                  if (this._endOfContent == HttpTokens.EndOfContent.CHUNKED_CONTENT) {
                     throw new BadMessageException(400, "Bad Transfer-Encoding, chunked not last");
                  }

                  if (HttpHeaderValue.CHUNKED.is(this._valueString)) {
                     this._endOfContent = HttpTokens.EndOfContent.CHUNKED_CONTENT;
                     this._contentLength = -1L;
                  } else {
                     List<String> values = (new QuotedCSV(new String[]{this._valueString})).getValues();
                     int chunked = -1;
                     int len = values.size();

                     for(int i = 0; i < len; ++i) {
                        if (HttpHeaderValue.CHUNKED.is((String)values.get(i))) {
                           if (chunked != -1) {
                              throw new BadMessageException(400, "Bad Transfer-Encoding, multiple chunked tokens");
                           }

                           chunked = i;
                           this._endOfContent = HttpTokens.EndOfContent.CHUNKED_CONTENT;
                           this._contentLength = -1L;
                        } else if (this._endOfContent == HttpTokens.EndOfContent.CHUNKED_CONTENT) {
                           throw new BadMessageException(400, "Bad Transfer-Encoding, chunked not last");
                        }
                     }
                  }
                  break;
               case HOST:
                  if (this._parsedHost != null) {
                     if (LOG.isWarnEnabled()) {
                        LOG.warn("Encountered multiple `Host` headers.  Previous `Host` header already seen as `{}`, new `Host` header has appeared as `{}`", this._parsedHost, this._valueString);
                     }

                     this.checkViolation(HttpCompliance.Violation.DUPLICATE_HOST_HEADERS);
                  }

                  this._parsedHost = this._valueString;
                  if (!(this._field instanceof HostPortHttpField) && this._valueString != null && !this._valueString.isEmpty()) {
                     if (HttpCompliance.Violation.UNSAFE_HOST_HEADER.isAllowedBy(this._complianceMode)) {
                        this._field = new HostPortHttpField(this._header, HttpCompliance.Violation.CASE_SENSITIVE_FIELD_NAME.isAllowedBy(this._complianceMode) ? this._headerString : this._header.asString(), HostPort.unsafe(this._valueString));
                     } else {
                        this._field = new HostPortHttpField(this._header, HttpCompliance.Violation.CASE_SENSITIVE_FIELD_NAME.isAllowedBy(this._complianceMode) ? this._headerString : this._header.asString(), this._valueString);
                     }

                     addToFieldCache = this._fieldCache.isEnabled();
                  }
                  break;
               case CONNECTION:
                  if (this._field == null) {
                     this._field = new HttpField(this._header, this.caseInsensitiveHeader(this._headerString, this._header.asString()), this._valueString);
                  }

                  if (this.getHeaderCacheSize() > 0 && this._field.contains(HttpHeaderValue.CLOSE.asString())) {
                     this._fieldCache.setCapacity(-1);
                  }
                  break;
               case AUTHORIZATION:
               case ACCEPT:
               case ACCEPT_CHARSET:
               case ACCEPT_ENCODING:
               case ACCEPT_LANGUAGE:
               case COOKIE:
               case CACHE_CONTROL:
               case USER_AGENT:
                  addToFieldCache = this._field == null && this._fieldCache.cacheable(this._header, this._valueString);
            }

            if (addToFieldCache) {
               if (this._field == null) {
                  this._field = new HttpField(this._header, this.caseInsensitiveHeader(this._headerString, this._header.asString()), this._valueString);
               }

               this._fieldCache.add(this._field);
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("parsedHeader({}) header={}, headerString=[{}], valueString=[{}]", new Object[]{this._field, this._header, this._headerString, this._valueString});
         }

         this._handler.parsedHeader(this._field != null ? this._field : new HttpField(this._header, this._headerString, this._valueString));
      }

      this._headerString = this._valueString = null;
      this._header = null;
      this._field = null;
   }

   private void parsedTrailer() {
      if (this._headerString != null || this._valueString != null) {
         this._handler.parsedTrailer(this._field != null ? this._field : new HttpField(this._header, this._headerString, this._valueString));
      }

      this._headerString = this._valueString = null;
      this._header = null;
      this._field = null;
   }

   private long convertContentLength(String valueString) {
      if (valueString != null && valueString.length() != 0) {
         long value = 0L;
         int length = valueString.length();

         for(int i = 0; i < length; ++i) {
            char c = valueString.charAt(i);
            if (c < '0' || c > '9') {
               throw new BadMessageException("Invalid Content-Length Value", new NumberFormatException());
            }

            value = Math.addExact(Math.multiplyExact(value, 10), (long)(c - 48));
         }

         return value;
      } else {
         throw new BadMessageException("Invalid Content-Length Value", new NumberFormatException());
      }
   }

   protected boolean parseFields(ByteBuffer buffer) {
      while((this._state == HttpParser.State.HEADER || this._state == HttpParser.State.TRAILER) && buffer.hasRemaining()) {
         HttpTokens.Token t = this.next(buffer);
         if (t != null) {
            if (this._maxHeaderBytes > 0 && ++this._headerBytes > this._maxHeaderBytes) {
               boolean header = this._state == HttpParser.State.HEADER;
               LOG.warn("{} is too large {}>{}", new Object[]{header ? "Header" : "Trailer", this._headerBytes, this._maxHeaderBytes});
               throw new BadMessageException(header ? 431 : 413);
            }

            switch (this._fieldState.ordinal()) {
               case 0:
                  switch (t.getType()) {
                     case LF:
                        if (this._state == HttpParser.State.HEADER) {
                           this.parsedHeader();
                        } else {
                           this.parsedTrailer();
                        }

                        this._contentPosition = 0L;
                        if (this._state == HttpParser.State.TRAILER) {
                           this.setState(HttpParser.State.END);
                           return this._handler.messageComplete();
                        }

                        if (!this._hasTransferEncoding || this._endOfContent == HttpTokens.EndOfContent.CHUNKED_CONTENT || this._responseHandler != null && this._endOfContent == HttpTokens.EndOfContent.EOF_CONTENT) {
                           if (this._parsedHost == null && this._version == HttpVersion.HTTP_1_1 && this._requestHandler != null) {
                              throw new BadMessageException(400, "No Host");
                           }

                           if (this._responseHandler == null || this._responseStatus != 304 && this._responseStatus != 204 && this._responseStatus >= 200) {
                              if (this._endOfContent == HttpTokens.EndOfContent.UNKNOWN_CONTENT) {
                                 if (this._responseStatus != 0 && this._responseStatus != 304 && this._responseStatus != 204 && this._responseStatus >= 200) {
                                    this._endOfContent = HttpTokens.EndOfContent.EOF_CONTENT;
                                 } else {
                                    this._endOfContent = HttpTokens.EndOfContent.NO_CONTENT;
                                 }
                              }
                           } else {
                              this._endOfContent = HttpTokens.EndOfContent.NO_CONTENT;
                           }

                           switch (this._endOfContent) {
                              case EOF_CONTENT:
                                 this.setState(HttpParser.State.EOF_CONTENT);
                                 boolean handle = this._handler.headerComplete();
                                 this._headerComplete = true;
                                 return handle;
                              case CHUNKED_CONTENT:
                                 this.setState(HttpParser.State.CHUNKED_CONTENT);
                                 boolean handle = this._handler.headerComplete();
                                 this._headerComplete = true;
                                 return handle;
                              default:
                                 this.setState(HttpParser.State.CONTENT);
                                 boolean handle = this._handler.headerComplete();
                                 this._headerComplete = true;
                                 return handle;
                           }
                        }

                        throw new BadMessageException(400, "Bad Transfer-Encoding, chunked not last");
                     case CR:
                     case VCHAR:
                     case OTEXT:
                     default:
                        throw new IllegalCharacterException(this._state, t, buffer);
                     case ALPHA:
                     case DIGIT:
                     case TCHAR:
                        if (this._state == HttpParser.State.HEADER) {
                           this.parsedHeader();
                        } else {
                           this.parsedTrailer();
                        }

                        if (buffer.hasRemaining()) {
                           HttpField cachedField = this._fieldCache.getBest(buffer, -1, buffer.remaining());
                           if (cachedField == null) {
                              cachedField = (HttpField)CACHE.getBest((ByteBuffer)buffer, -1, buffer.remaining());
                           }

                           if (cachedField != null) {
                              String n = cachedField.getName();
                              String v = cachedField.getValue();
                              if (HttpCompliance.Violation.CASE_SENSITIVE_FIELD_NAME.isAllowedBy(this._complianceMode)) {
                                 String en = BufferUtil.toString(buffer, buffer.position() - 1, n.length(), StandardCharsets.US_ASCII);
                                 if (!n.equals(en)) {
                                    this.reportComplianceViolation(HttpCompliance.Violation.CASE_SENSITIVE_FIELD_NAME, en);
                                    n = en;
                                    cachedField = new HttpField(cachedField.getHeader(), en, v);
                                 }
                              }

                              if (v != null && this.isHeaderCacheCaseSensitive()) {
                                 String ev = BufferUtil.toString(buffer, buffer.position() + n.length() + 1, v.length(), StandardCharsets.ISO_8859_1);
                                 if (!v.equals(ev)) {
                                    v = ev;
                                    cachedField = new HttpField(cachedField.getHeader(), n, ev);
                                 }
                              }

                              this._header = cachedField.getHeader();
                              this._headerString = n;
                              if (v == null) {
                                 this.setState(HttpParser.FieldState.VALUE);
                                 this._string.setLength(0);
                                 this._length = 0;
                                 buffer.position(buffer.position() + n.length() + 1);
                                 continue;
                              }

                              int pos = buffer.position() + n.length() + v.length() + 1;
                              byte peek = buffer.get(pos);
                              if (peek != 13 && peek != 10) {
                                 this.setState(HttpParser.FieldState.IN_VALUE);
                                 this.setString(v);
                                 buffer.position(pos);
                                 continue;
                              }

                              this._field = cachedField;
                              this._valueString = v;
                              this.setState(HttpParser.FieldState.IN_VALUE);
                              if (peek == 13) {
                                 this._cr = true;
                                 buffer.position(pos + 1);
                                 continue;
                              }

                              buffer.position(pos);
                              continue;
                           }
                        }

                        this.setState(HttpParser.FieldState.IN_NAME);
                        this._string.setLength(0);
                        this._string.append(t.getChar());
                        this._length = 1;
                        continue;
                     case HTAB:
                     case SPACE:
                     case COLON:
                        this.checkViolation(HttpCompliance.Violation.MULTILINE_FIELD_VALUE);
                        if (StringUtil.isEmpty(this._valueString)) {
                           this._string.setLength(0);
                           this._length = 0;
                        } else {
                           this.setString(this._valueString);
                           this._string.append(' ');
                           ++this._length;
                           this._valueString = null;
                        }

                        this.setState(HttpParser.FieldState.VALUE);
                        continue;
                  }
               case 1:
                  switch (t.getType()) {
                     case LF:
                        this._headerString = this.takeString();
                        this._header = (HttpHeader)HttpHeader.CACHE.get(this._headerString);
                        this._string.setLength(0);
                        this._valueString = "";
                        this._length = -1;
                        if (HttpCompliance.Violation.NO_COLON_AFTER_FIELD_NAME.isAllowedBy(this._complianceMode)) {
                           this.reportComplianceViolation(HttpCompliance.Violation.NO_COLON_AFTER_FIELD_NAME, "Field " + this._headerString);
                           this.setState(HttpParser.FieldState.FIELD);
                           continue;
                        }

                        throw new IllegalCharacterException(this._state, t, buffer);
                     case CR:
                     case VCHAR:
                     case OTEXT:
                     default:
                        throw new IllegalCharacterException(this._state, t, buffer);
                     case ALPHA:
                     case DIGIT:
                     case TCHAR:
                        this._string.append(t.getChar());
                        this._length = this._string.length();
                        continue;
                     case HTAB:
                     case SPACE:
                        if (HttpCompliance.Violation.WHITESPACE_AFTER_FIELD_NAME.isAllowedBy(this._complianceMode)) {
                           this._headerString = this.takeString();
                           this.reportComplianceViolation(HttpCompliance.Violation.WHITESPACE_AFTER_FIELD_NAME, "Space after " + this._headerString);
                           this._header = (HttpHeader)HttpHeader.CACHE.get(this._headerString);
                           this._length = -1;
                           this.setState(HttpParser.FieldState.WS_AFTER_NAME);
                           continue;
                        }

                        throw new IllegalCharacterException(this._state, t, buffer);
                     case COLON:
                        this._headerString = this.takeString();
                        this._header = (HttpHeader)HttpHeader.CACHE.get(this._headerString);
                        this._length = -1;
                        this.setState(HttpParser.FieldState.VALUE);
                        continue;
                  }
               case 2:
                  switch (t.getType()) {
                     case LF:
                        this._string.setLength(0);
                        this._valueString = "";
                        this._length = -1;
                        this.setState(HttpParser.FieldState.FIELD);
                        continue;
                     case CR:
                     default:
                        throw new IllegalCharacterException(this._state, t, buffer);
                     case ALPHA:
                     case DIGIT:
                     case TCHAR:
                     case VCHAR:
                     case OTEXT:
                     case COLON:
                        this._string.append(t.getChar());
                        this._length = this._string.length();
                        this.setState(HttpParser.FieldState.IN_VALUE);
                     case HTAB:
                     case SPACE:
                        continue;
                  }
               case 3:
                  switch (t.getType()) {
                     case LF:
                        if (this._length > 0) {
                           this._valueString = this.takeString();
                           this._length = -1;
                        }

                        this.setState(HttpParser.FieldState.FIELD);
                        continue;
                     case CR:
                     default:
                        throw new IllegalCharacterException(this._state, t, buffer);
                     case ALPHA:
                     case DIGIT:
                     case TCHAR:
                     case VCHAR:
                     case OTEXT:
                     case COLON:
                        this._string.append(t.getChar());
                        this._length = this._string.length();
                        continue;
                     case HTAB:
                     case SPACE:
                        this._string.append(t.getChar());
                        continue;
                  }
               case 4:
                  switch (t.getType()) {
                     case LF:
                        if (HttpCompliance.Violation.NO_COLON_AFTER_FIELD_NAME.isAllowedBy(this._complianceMode)) {
                           this.reportComplianceViolation(HttpCompliance.Violation.NO_COLON_AFTER_FIELD_NAME, "Field " + this._headerString);
                           this.setState(HttpParser.FieldState.FIELD);
                           continue;
                        }

                        throw new IllegalCharacterException(this._state, t, buffer);
                     case CR:
                     case ALPHA:
                     case DIGIT:
                     case TCHAR:
                     case VCHAR:
                     case OTEXT:
                     default:
                        throw new IllegalCharacterException(this._state, t, buffer);
                     case HTAB:
                     case SPACE:
                        continue;
                     case COLON:
                        this.setState(HttpParser.FieldState.VALUE);
                        continue;
                  }
               default:
                  throw new IllegalStateException(this._state.toString());
            }
         }
      }

      return false;
   }

   public boolean parseNext(ByteBuffer buffer) {
      if (this.debugEnabled) {
         LOG.debug("parseNext s={} {}", this._state, BufferUtil.toDetailString(buffer));
      }

      try {
         if (this._state == HttpParser.State.START) {
            this._version = null;
            this._method = null;
            this._methodString = null;
            this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
            this._header = null;
            if (buffer.hasRemaining()) {
               this._beginNanoTime = NanoTime.now();
            }

            this.quickStart(buffer);
         }

         if (this._state.ordinal() < HttpParser.State.HEADER.ordinal() && this.parseLine(buffer)) {
            return true;
         }

         if (this._state == HttpParser.State.HEADER && this.parseFields(buffer)) {
            return true;
         }

         if (this._state.ordinal() >= HttpParser.State.CONTENT.ordinal() && this._state.ordinal() < HttpParser.State.TRAILER.ordinal()) {
            if (this._responseStatus > 0 && this._headResponse) {
               if (this._state != HttpParser.State.CONTENT_END) {
                  this.setState(HttpParser.State.CONTENT_END);
                  return this.handleContentMessage();
               }

               this.setState(HttpParser.State.END);
               return this._handler.messageComplete();
            }

            if (this.parseContent(buffer)) {
               return true;
            }
         }

         if (this._state == HttpParser.State.TRAILER && this.parseFields(buffer)) {
            return true;
         }

         if (this._state != HttpParser.State.END) {
            if (this.isTerminated()) {
               BufferUtil.clear(buffer);
            }
         } else {
            int whiteSpace;
            for(whiteSpace = 0; buffer.remaining() > 0; ++whiteSpace) {
               byte b = buffer.get(buffer.position());
               if (b != 13 && b != 10) {
                  break;
               }

               buffer.get();
            }

            if (this.debugEnabled && whiteSpace > 0) {
               LOG.debug("Discarded {} CR or LF characters", whiteSpace);
            }
         }

         if (this.isAtEOF() && !buffer.hasRemaining()) {
            switch (this._state.ordinal()) {
               case 0:
               case 11:
               case 13:
               case 14:
               case 15:
               case 16:
                  this.setState(HttpParser.State.CLOSED);
                  this._handler.earlyEOF();
                  break;
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 17:
               default:
                  if (this.debugEnabled) {
                     LOG.debug("{} EOF in {}", this, this._state);
                  }

                  this.setState(HttpParser.State.CLOSED);
                  this._handler.badMessage(new BadMessageException(400));
                  break;
               case 12:
               case 18:
                  if (this._fieldState == HttpParser.FieldState.FIELD) {
                     this.setState(HttpParser.State.CONTENT_END);
                     boolean handle = this.handleContentMessage();
                     if (handle && this._state == HttpParser.State.CONTENT_END) {
                        return true;
                     }

                     this.setState(HttpParser.State.CLOSED);
                     return handle;
                  }

                  this.setState(HttpParser.State.CLOSED);
                  this._handler.earlyEOF();
                  break;
               case 19:
               case 20:
                  this.setState(HttpParser.State.CLOSED);
               case 21:
            }
         }
      } catch (BadMessageException x) {
         BufferUtil.clear(buffer);
         this.badMessage(x);
      } catch (Throwable x) {
         BufferUtil.clear(buffer);
         this.badMessage(new BadMessageException(400, this._requestHandler != null ? "Bad Request" : "Bad Response", x));
      }

      return false;
   }

   protected void badMessage(BadMessageException x) {
      if (this.debugEnabled) {
         LOG.debug("Parse exception: {} for {}", new Object[]{this, this._handler, x});
      }

      this.setState(HttpParser.State.CLOSE);
      if (this._headerComplete) {
         this._handler.earlyEOF();
      } else {
         this._handler.badMessage(x);
      }

   }

   protected boolean parseContent(ByteBuffer buffer) {
      int remaining = buffer.remaining();
      if (remaining == 0) {
         switch (this._state.ordinal()) {
            case 11:
               long content = this._contentLength - this._contentPosition;
               if (this._endOfContent == HttpTokens.EndOfContent.NO_CONTENT || content == 0L) {
                  this.setState(HttpParser.State.CONTENT_END);
                  return this.handleContentMessage();
               }
               break;
            case 17:
               this.setState(this._endOfContent == HttpTokens.EndOfContent.EOF_CONTENT ? HttpParser.State.CLOSED : HttpParser.State.END);
               return this._handler.messageComplete();
            default:
               return false;
         }
      }

      for(; this._state.ordinal() < HttpParser.State.TRAILER.ordinal() && remaining > 0; remaining = buffer.remaining()) {
         switch (this._state.ordinal()) {
            case 11:
               long content = this._contentLength - this._contentPosition;
               if (this._endOfContent == HttpTokens.EndOfContent.NO_CONTENT || content == 0L) {
                  this.setState(HttpParser.State.CONTENT_END);
                  return this.handleContentMessage();
               }

               this._contentChunk = buffer.asReadOnlyBuffer();
               if (this._contentLength > -1L && (long)remaining > content) {
                  this._contentChunk.limit(this._contentChunk.position() + (int)content);
               }

               this._contentPosition += (long)this._contentChunk.remaining();
               buffer.position(buffer.position() + this._contentChunk.remaining());
               if (this._handler.content(this._contentChunk)) {
                  return true;
               }

               if (this._contentPosition == this._contentLength) {
                  this.setState(HttpParser.State.CONTENT_END);
                  return this.handleContentMessage();
               }
               break;
            case 12:
               this._contentChunk = buffer.asReadOnlyBuffer();
               this._contentPosition += (long)remaining;
               buffer.position(buffer.position() + remaining);
               if (this._handler.content(this._contentChunk)) {
                  return true;
               }
               break;
            case 13:
               HttpTokens.Token t = this.next(buffer);
               if (t != null) {
                  switch (t.getType()) {
                     case LF:
                        break;
                     case CR:
                     default:
                        throw new IllegalCharacterException(this._state, t, buffer);
                     case ALPHA:
                        if (!t.isHexDigit()) {
                           throw new IllegalCharacterException(this._state, t, buffer);
                        }

                        this._chunkLength = t.getHexDigit();
                        this._chunkPosition = 0;
                        this.setState(HttpParser.State.CHUNK_SIZE);
                        break;
                     case DIGIT:
                        this._chunkLength = t.getHexDigit();
                        this._chunkPosition = 0;
                        this.setState(HttpParser.State.CHUNK_SIZE);
                  }
               }
               break;
            case 14:
               HttpTokens.Token t = this.next(buffer);
               if (t != null) {
                  switch (t.getType()) {
                     case LF:
                        if (this._chunkLength == 0) {
                           this.setState(HttpParser.State.TRAILER);
                           if (this._handler.contentComplete()) {
                              return true;
                           }
                        } else {
                           this.setState(HttpParser.State.CHUNK);
                        }
                        break;
                     case SPACE:
                        this.setState(HttpParser.State.CHUNK_PARAMS);
                        break;
                     default:
                        if (t.isHexDigit()) {
                           if (this._chunkLength > 134217711) {
                              throw new BadMessageException(413);
                           }

                           this._chunkLength = this._chunkLength * 16 + t.getHexDigit();
                        } else {
                           this.setState(HttpParser.State.CHUNK_PARAMS);
                        }
                  }
               }
               break;
            case 15:
               HttpTokens.Token t = this.next(buffer);
               if (t != null) {
                  switch (t.getType()) {
                     case LF:
                        if (this._chunkLength == 0) {
                           this.setState(HttpParser.State.TRAILER);
                           if (this._handler.contentComplete()) {
                              return true;
                           }
                        } else {
                           this.setState(HttpParser.State.CHUNK);
                        }
                  }
               }
               break;
            case 16:
               int chunk = this._chunkLength - this._chunkPosition;
               if (chunk == 0) {
                  this.setState(HttpParser.State.CHUNKED_CONTENT);
               } else {
                  this._contentChunk = buffer.asReadOnlyBuffer();
                  if (remaining > chunk) {
                     this._contentChunk.limit(this._contentChunk.position() + chunk);
                  }

                  chunk = this._contentChunk.remaining();
                  this._contentPosition += (long)chunk;
                  this._chunkPosition += chunk;
                  buffer.position(buffer.position() + chunk);
                  if (this._handler.content(this._contentChunk)) {
                     return true;
                  }
               }
               break;
            case 17:
               this.setState(this._endOfContent == HttpTokens.EndOfContent.EOF_CONTENT ? HttpParser.State.CLOSED : HttpParser.State.END);
               return this._handler.messageComplete();
         }
      }

      return false;
   }

   public boolean isAtEOF() {
      return this._eof;
   }

   public void atEOF() {
      if (this.debugEnabled) {
         LOG.debug("atEOF {}", this);
      }

      this._eof = true;
   }

   public void close() {
      if (this.debugEnabled) {
         LOG.debug("close {}", this);
      }

      this.setState(HttpParser.State.CLOSE);
   }

   public void reset() {
      if (this.debugEnabled) {
         LOG.debug("reset {}", this);
      }

      if (this._state != HttpParser.State.CLOSE && this._state != HttpParser.State.CLOSED) {
         this.setState(HttpParser.State.START);
         this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
         this._contentLength = -1L;
         this._hasContentLength = false;
         this._hasTransferEncoding = false;
         this._contentPosition = 0L;
         this._responseStatus = 0;
         this._contentChunk = null;
         this._headerBytes = 0;
         this._parsedHost = null;
         this._headerComplete = false;
      }
   }

   public void servletUpgrade() {
      this.setState(HttpParser.State.CONTENT);
      this._endOfContent = HttpTokens.EndOfContent.UNKNOWN_CONTENT;
      this._contentLength = -1L;
   }

   protected void setState(State state) {
      if (this.debugEnabled) {
         LOG.debug("{} --> {}", this._state, state);
      }

      this._state = state;
   }

   protected void setState(FieldState state) {
      if (this.debugEnabled) {
         LOG.debug("{}:{} --> {}", new Object[]{this._state, this._field != null ? this._field : (this._headerString != null ? this._headerString : this._string), state});
      }

      this._fieldState = state;
   }

   public Index getFieldCache() {
      this._fieldCache.prepare();
      return this._fieldCache.getCache();
   }

   public String toString() {
      return String.format("%s{s=%s,%d of %d}", this.getClass().getSimpleName(), this._state, this.getContentRead(), this.getContentLength());
   }

   static {
      CACHE = (new Index.Builder()).caseSensitive(false).with(new HttpField(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE)).with(new HttpField(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE)).with(new HttpField(HttpHeader.CONNECTION, HttpHeaderValue.UPGRADE)).with(new HttpField(HttpHeader.ACCEPT_ENCODING, "gzip")).with(new HttpField(HttpHeader.ACCEPT_ENCODING, "gzip, deflate")).with(new HttpField(HttpHeader.ACCEPT_ENCODING, "gzip, deflate, br")).with(new HttpField(HttpHeader.ACCEPT_LANGUAGE, "en-US,enq=0.5")).with(new HttpField(HttpHeader.ACCEPT_LANGUAGE, "en-GB,en-USq=0.8,enq=0.6")).with(new HttpField(HttpHeader.ACCEPT_LANGUAGE, "en-AU,enq=0.9,it-ITq=0.8,itq=0.7,en-GBq=0.6,en-USq=0.5")).with(new HttpField(HttpHeader.ACCEPT_CHARSET, "ISO-8859-1,utf-8q=0.7,*q=0.3")).with(new HttpField(HttpHeader.ACCEPT, "*/*")).with(new HttpField(HttpHeader.ACCEPT, "image/png,image/*q=0.8,*/*q=0.5")).with(new HttpField(HttpHeader.ACCEPT, "text/html,application/xhtml+xml,application/xmlq=0.9,*/*q=0.8")).with(new HttpField(HttpHeader.ACCEPT, "text/html,application/xhtml+xml,application/xmlq=0.9,image/webp,image/apng,*/*q=0.8")).with(new HttpField(HttpHeader.ACCEPT_RANGES, HttpHeaderValue.BYTES)).with(new HttpField(HttpHeader.PRAGMA, "no-cache")).with(new HttpField(HttpHeader.CACHE_CONTROL, "private, no-cache, no-cache=Set-Cookie, proxy-revalidate")).with(new HttpField(HttpHeader.CACHE_CONTROL, "no-cache")).with(new HttpField(HttpHeader.CACHE_CONTROL, "max-age=0")).with(new HttpField(HttpHeader.CONTENT_LENGTH, "0")).with(new HttpField(HttpHeader.CONTENT_ENCODING, "gzip")).with(new HttpField(HttpHeader.CONTENT_ENCODING, "deflate")).with(new HttpField(HttpHeader.TRANSFER_ENCODING, "chunked")).with(new HttpField(HttpHeader.EXPIRES, "Fri, 01 Jan 1990 00:00:00 GMT")).withAll(() -> {
         Map<String, HttpField> map = new LinkedHashMap();

         for(String type : new String[]{"text/plain", "text/html", "text/xml", "text/json", "application/json", "application/x-www-form-urlencoded"}) {
            HttpField field = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, type);
            map.put(field.toString(), field);

            for(String charset : new String[]{"utf-8", "iso-8859-1"}) {
               PreEncodedHttpField field1 = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, type + ";charset=" + charset);
               map.put(field1.toString(), field1);
               PreEncodedHttpField field2 = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, type + "; charset=" + charset);
               map.put(field2.toString(), field2);
               PreEncodedHttpField field3 = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, type + ";charset=" + charset.toUpperCase(Locale.ENGLISH));
               map.put(field3.toString(), field3);
               PreEncodedHttpField field4 = new PreEncodedHttpField(HttpHeader.CONTENT_TYPE, type + "; charset=" + charset.toUpperCase(Locale.ENGLISH));
               map.put(field4.toString(), field4);
            }
         }

         return map;
      }).withAll(() -> {
         Map<String, HttpField> map = new LinkedHashMap();

         for(HttpHeader h : HttpHeader.values()) {
            HttpField httpField = new HttpField(h, (String)null);
            map.put(httpField.toString(), httpField);
         }

         return map;
      }).build();
      NO_CACHE = (new Index.Builder()).caseSensitive(false).mutable().maxCapacity(0).build();
      __idleStates = EnumSet.of(HttpParser.State.START, HttpParser.State.END, HttpParser.State.CLOSE, HttpParser.State.CLOSED);
      __completeStates = EnumSet.of(HttpParser.State.END, HttpParser.State.CLOSE, HttpParser.State.CLOSED);
      __terminatedStates = EnumSet.of(HttpParser.State.CLOSE, HttpParser.State.CLOSED);
   }

   public static enum FieldState {
      FIELD,
      IN_NAME,
      VALUE,
      IN_VALUE,
      WS_AFTER_NAME;

      // $FF: synthetic method
      private static FieldState[] $values() {
         return new FieldState[]{FIELD, IN_NAME, VALUE, IN_VALUE, WS_AFTER_NAME};
      }
   }

   public static enum State {
      START,
      METHOD,
      RESPONSE_VERSION,
      SPACE1,
      STATUS,
      URI,
      SPACE2,
      REQUEST_VERSION,
      REASON,
      PROXY,
      HEADER,
      CONTENT,
      EOF_CONTENT,
      CHUNKED_CONTENT,
      CHUNK_SIZE,
      CHUNK_PARAMS,
      CHUNK,
      CONTENT_END,
      TRAILER,
      END,
      CLOSE,
      CLOSED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{START, METHOD, RESPONSE_VERSION, SPACE1, STATUS, URI, SPACE2, REQUEST_VERSION, REASON, PROXY, HEADER, CONTENT, EOF_CONTENT, CHUNKED_CONTENT, CHUNK_SIZE, CHUNK_PARAMS, CHUNK, CONTENT_END, TRAILER, END, CLOSE, CLOSED};
      }
   }

   public interface HttpHandler {
      boolean content(ByteBuffer var1);

      boolean headerComplete();

      boolean contentComplete();

      boolean messageComplete();

      void parsedHeader(HttpField var1);

      default void parsedTrailer(HttpField field) {
      }

      void earlyEOF();

      default void badMessage(BadMessageException failure) {
      }
   }

   private static class IllegalCharacterException extends BadMessageException {
      private IllegalCharacterException(State state, HttpTokens.Token token, ByteBuffer buffer) {
         super(400, String.format("Illegal character %s", token));
         if (HttpParser.LOG.isDebugEnabled()) {
            HttpParser.LOG.debug(String.format("Illegal character %s in state=%s for buffer %s", token, state, BufferUtil.toDetailString(buffer)));
         }

      }
   }

   private static class FieldCache {
      private int _size = 1024;
      private Index.Mutable _cache;
      private List _cacheableFields;
      private boolean _caseSensitive;

      public int getCapacity() {
         return this._size;
      }

      public void setCapacity(int size) {
         this._size = size;
         if (this._size <= 0) {
            this._cache = HttpParser.NO_CACHE;
         } else {
            this._cache = null;
         }

      }

      public boolean isCaseSensitive() {
         return this._caseSensitive;
      }

      public void setCaseSensitive(boolean caseSensitive) {
         this._caseSensitive = caseSensitive;
      }

      public boolean isEnabled() {
         return this._cache != HttpParser.NO_CACHE;
      }

      public Index getCache() {
         return this._cache;
      }

      public HttpField getBest(ByteBuffer buffer, int i, int remaining) {
         Index.Mutable<HttpField> cache = this._cache;
         return cache == null ? null : (HttpField)this._cache.getBest(buffer, i, remaining);
      }

      public void add(HttpField field) {
         if (this._cache == null) {
            if (this._cacheableFields == null) {
               this._cacheableFields = new ArrayList();
            }

            this._cacheableFields.add(field);
         } else if (!this._cache.put(field)) {
            this._cache.clear();
            this._cache.put(field);
         }

      }

      public boolean cacheable(HttpHeader header, String valueString) {
         return this.isEnabled() && header != null && valueString.length() <= this._size;
      }

      private void prepare() {
         if (this._cache == null && this._cacheableFields != null) {
            this._cache = Index.buildMutableVisibleAsciiAlphabet(this._caseSensitive, this._size);

            for(HttpField f : this._cacheableFields) {
               if (!this._cache.put(f)) {
                  break;
               }
            }

            this._cacheableFields.clear();
            this._cacheableFields = null;
         }

      }
   }

   public interface RequestHandler extends HttpHandler {
      void startRequest(String var1, String var2, HttpVersion var3);
   }

   public interface ResponseHandler extends HttpHandler {
      void startResponse(HttpVersion var1, int var2, String var3);
   }
}
