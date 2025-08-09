package org.sparkproject.jetty.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.BadMessageException;
import org.sparkproject.jetty.http.ComplianceViolation;
import org.sparkproject.jetty.http.HostPortHttpField;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpGenerator;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpParser;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.HttpVersion;
import org.sparkproject.jetty.http.MetaData;
import org.sparkproject.jetty.io.Connection;
import org.sparkproject.jetty.io.EndPoint;
import org.sparkproject.jetty.io.EofException;
import org.sparkproject.jetty.util.NanoTime;

public class HttpChannelOverHttp extends HttpChannel implements HttpParser.RequestHandler, ComplianceViolation.Listener {
   private static final Logger LOG = LoggerFactory.getLogger(HttpChannelOverHttp.class);
   private static final HttpField PREAMBLE_UPGRADE_H2C;
   private static final HttpInput.Content EOF;
   private final HttpConnection _httpConnection;
   private final RequestBuilder _requestBuilder = new RequestBuilder();
   private MetaData.Request _metadata;
   private HttpField _connection;
   private HttpField _upgrade = null;
   private boolean _delayedForContent;
   private boolean _unknownExpectation = false;
   private boolean _expect100Continue = false;
   private boolean _expect102Processing = false;
   private List _complianceViolations;
   private HttpFields.Mutable _trailers;
   private HttpInput.Content _content;
   private boolean _servletUpgrade;

   public HttpChannelOverHttp(HttpConnection httpConnection, Connector connector, HttpConfiguration config, EndPoint endPoint, HttpTransport transport) {
      super(connector, config, endPoint, transport);
      this._httpConnection = httpConnection;
   }

   public void abort(Throwable failure) {
      super.abort(failure);
      this._httpConnection.getGenerator().setPersistent(false);
   }

   public boolean needContent() {
      if (this._content != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("needContent has content immediately available: {}", this._content);
         }

         return true;
      } else {
         this.parseAndFillForContent();
         if (this._content != null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("needContent has content after parseAndFillForContent: {}", this._content);
            }

            return true;
         } else {
            if (LOG.isDebugEnabled()) {
               LOG.debug("needContent has no content");
            }

            this._httpConnection.asyncReadFillInterested();
            return false;
         }
      }
   }

   public HttpInput.Content produceContent() {
      if (this._content == null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("produceContent has no content, parsing and filling");
         }

         this.parseAndFillForContent();
      }

      HttpInput.Content result = this._content;
      if (result != null && !result.isSpecial()) {
         this._content = result.isEof() ? EOF : null;
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("produceContent produced {}", result);
      }

      return result;
   }

   private void parseAndFillForContent() {
      try {
         this._httpConnection.parseAndFillForContent();
      } catch (Throwable x) {
         this._content = new HttpInput.ErrorContent(x);
      }

   }

   public boolean failAllContent(Throwable failure) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("failing all content with {} {}", failure, this);
      }

      if (this._content != null) {
         if (this._content.isSpecial()) {
            return this._content.isEof();
         }

         this._content.failed(failure);
         this._content = this._content.isEof() ? EOF : null;
         if (this._content == EOF) {
            return true;
         }
      }

      HttpInput.Content c;
      do {
         c = this.produceContent();
         if (c == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("failed all content, EOF was not reached");
            }

            return false;
         }

         if (c.isSpecial()) {
            this._content = c;
            boolean atEof = c.isEof();
            if (LOG.isDebugEnabled()) {
               LOG.debug("failed all content, EOF = {}", atEof);
            }

            return atEof;
         }

         c.skip(c.remaining());
         c.failed(failure);
      } while(!c.isEof());

      this._content = EOF;
      return true;
   }

   public void badMessage(BadMessageException failure) {
      this._httpConnection.getGenerator().setPersistent(false);

      try {
         if (this._metadata == null) {
            this._metadata = this._requestBuilder.build();
         }

         this.onRequest(this._metadata);
         this.markEarlyEOF();
      } catch (Exception e) {
         LOG.trace("IGNORED", e);
      }

      this.onBadMessage(failure);
   }

   public boolean content(ByteBuffer buffer) {
      HttpInput.Content content = this._httpConnection.newContent(buffer);
      if (this._content != null) {
         if (!this._content.isSpecial()) {
            String var10002 = String.valueOf(this._content);
            throw new AssertionError("Cannot overwrite exiting content " + var10002 + " with " + String.valueOf(content));
         }

         content.failed(this._content.getError());
      } else {
         this._content = content;
         this.onContent(this._content);
         this._delayedForContent = false;
      }

      return true;
   }

   public boolean contentComplete() {
      boolean handle = this.onContentComplete() || this._delayedForContent;
      this._delayedForContent = false;
      return handle;
   }

   public void continue100(int available) throws IOException {
      if (this.isExpecting100Continue()) {
         this._expect100Continue = false;
         if (available == 0) {
            if (this.getResponse().isCommitted()) {
               throw new IOException("Committed before 100 Continues");
            }

            boolean committed = this.sendResponse(HttpGenerator.CONTINUE_100_INFO, (ByteBuffer)null, false);
            if (!committed) {
               throw new IOException("Concurrent commit while trying to send 100-Continue");
            }
         }
      }

   }

   public void earlyEOF() {
      this._httpConnection.getGenerator().setPersistent(false);
      if (this._metadata == null) {
         this._httpConnection.close();
      } else {
         this.markEarlyEOF();
         if (this._delayedForContent) {
            this._delayedForContent = false;
            this.handle();
         }
      }

   }

   private void markEarlyEOF() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("received early EOF, content = {}", this._content);
      }

      if (this._servletUpgrade) {
         if (this._content != null) {
            this._content.succeeded();
         }

         this._content = EOF;
      } else {
         EofException failure = new EofException("Early EOF");
         if (this._content != null) {
            this._content.failed(failure);
         }

         this._content = new HttpInput.ErrorContent(failure);
      }

   }

   protected boolean eof() {
      if (LOG.isDebugEnabled()) {
         LOG.debug("received EOF, content = {}", this._content);
      }

      if (this._content == null) {
         this._content = EOF;
      } else {
         HttpInput.Content c = this._content;
         this._content = new HttpInput.WrappingContent(c, true);
      }

      return false;
   }

   public boolean failed(Throwable x) {
      if (LOG.isDebugEnabled()) {
         LOG.debug("failed {}, content = {}", x, this._content);
      }

      Throwable error = null;
      if (this._content != null && this._content.isSpecial()) {
         error = this._content.getError();
      }

      if (error != null && error != x) {
         error.addSuppressed(x);
      } else {
         if (this._content != null) {
            this._content.failed(x);
         }

         this._content = new HttpInput.ErrorContent(x);
      }

      return this.getRequest().getHttpInput().onContentProducible();
   }

   public EndPoint getTunnellingEndPoint() {
      return this.getEndPoint();
   }

   public boolean headerComplete() {
      this._requestBuilder.beginNanoTime(this._httpConnection.getBeginNanoTime());
      this._metadata = this._requestBuilder.build();
      this.onRequest(this._metadata);
      if (this._complianceViolations != null && !this._complianceViolations.isEmpty()) {
         this.getRequest().setAttribute("org.sparkproject.jetty.http.compliance.violations", this._complianceViolations);
         this._complianceViolations = null;
      }

      boolean persistent;
      switch (this._metadata.getHttpVersion()) {
         case HTTP_0_9:
            persistent = false;
            break;
         case HTTP_1_0:
            if (this.getHttpConfiguration().isPersistentConnectionsEnabled()) {
               if (this._connection != null) {
                  if (this._connection.contains(HttpHeaderValue.KEEP_ALIVE.asString())) {
                     persistent = true;
                  } else {
                     persistent = this._requestBuilder.getFields().contains(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString());
                  }
               } else {
                  persistent = false;
               }
            } else {
               persistent = false;
            }

            if (!persistent) {
               persistent = HttpMethod.CONNECT.is(this._metadata.getMethod());
            }

            if (persistent) {
               this.getResponse().getHttpFields().add(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE);
            }
            break;
         case HTTP_1_1:
            if (this._unknownExpectation) {
               this.badMessage(new BadMessageException(417));
               return false;
            }

            if (this.getHttpConfiguration().isPersistentConnectionsEnabled()) {
               if (this._connection != null) {
                  if (this._connection.contains(HttpHeaderValue.CLOSE.asString())) {
                     persistent = false;
                  } else {
                     persistent = !this._requestBuilder.getFields().contains(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString());
                  }
               } else {
                  persistent = true;
               }
            } else {
               persistent = false;
            }

            if (!persistent) {
               persistent = HttpMethod.CONNECT.is(this._metadata.getMethod());
            }

            if (!persistent) {
               this.getResponse().getHttpFields().add(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE);
            }

            if (this._upgrade != null && this.upgrade()) {
               return true;
            }
            break;
         case HTTP_2:
            this._upgrade = PREAMBLE_UPGRADE_H2C;
            if (HttpMethod.PRI.is(this._metadata.getMethod()) && "*".equals(this._metadata.getURI().getPath()) && this._requestBuilder.getFields().size() == 0 && this.upgrade()) {
               return true;
            }

            this.badMessage(new BadMessageException(426));
            this._httpConnection.getParser().close();
            return false;
         default:
            throw new IllegalStateException("unsupported version " + String.valueOf(this._metadata.getHttpVersion()));
      }

      if (!persistent) {
         this._httpConnection.getGenerator().setPersistent(false);
      }

      this._delayedForContent = this.getHttpConfiguration().isDelayDispatchUntilContent() && (this._httpConnection.getParser().getContentLength() > 0L || this._httpConnection.getParser().isChunking()) && !this.isExpecting100Continue() && !this.isCommitted() && this._httpConnection.isRequestBufferEmpty();
      return !this._delayedForContent;
   }

   public boolean isExpecting100Continue() {
      return this._expect100Continue;
   }

   public boolean isExpecting102Processing() {
      return this._expect102Processing;
   }

   public boolean isTunnellingSupported() {
      return true;
   }

   public boolean isUseOutputDirectByteBuffers() {
      return this._httpConnection.isUseOutputDirectByteBuffers();
   }

   public boolean messageComplete() {
      if (this._trailers != null) {
         this.onTrailers(this._trailers);
      }

      return this.onRequestComplete();
   }

   public void onComplianceViolation(ComplianceViolation.Mode mode, ComplianceViolation violation, String details) {
      if (this._httpConnection.isRecordHttpComplianceViolations()) {
         if (this._complianceViolations == null) {
            this._complianceViolations = new ArrayList();
         }

         String record = String.format("%s (see %s) in mode %s for %s in %s", violation.getDescription(), violation.getURL(), mode, details, this.getHttpTransport());
         this._complianceViolations.add(record);
         if (LOG.isDebugEnabled()) {
            LOG.debug(record);
         }
      }

   }

   public void parsedHeader(HttpField field) {
      HttpHeader header = field.getHeader();
      String value = field.getValue();
      if (header != null) {
         switch (header) {
            case CONNECTION:
               this._connection = field;
               break;
            case HOST:
               if (!(field instanceof HostPortHttpField) && value != null && !value.isEmpty()) {
                  field = new HostPortHttpField(value);
               }
               break;
            case EXPECT:
               if (!HttpHeaderValue.parseCsvIndex(value, (t) -> {
                  switch (t) {
                     case CONTINUE:
                        this._expect100Continue = true;
                        return true;
                     case PROCESSING:
                        this._expect102Processing = true;
                        return true;
                     default:
                        return false;
                  }
               }, (s) -> false)) {
                  this._unknownExpectation = true;
                  this._expect100Continue = false;
                  this._expect102Processing = false;
               }
               break;
            case UPGRADE:
               this._upgrade = field;
         }
      }

      this._requestBuilder.getFields().add(field);
   }

   public void parsedTrailer(HttpField field) {
      if (this._trailers == null) {
         this._trailers = HttpFields.build();
      }

      this._trailers.add(field);
   }

   public void recycle() {
      super.recycle();
      this._unknownExpectation = false;
      this._expect100Continue = false;
      this._expect102Processing = false;
      this._connection = null;
      this._upgrade = null;
      this._trailers = null;
      this._metadata = null;
      if (this._content != null && !this._content.isSpecial()) {
         throw new AssertionError("unconsumed content: " + String.valueOf(this._content));
      } else {
         this._content = null;
         this._servletUpgrade = false;
      }
   }

   public void servletUpgrade() {
      if (this._content == null || this._content.isSpecial() && this._content.isEof()) {
         this._content = null;
         this._servletUpgrade = true;
         this._httpConnection.getParser().servletUpgrade();
      } else {
         throw new IllegalStateException("Cannot perform servlet upgrade with unconsumed content");
      }
   }

   public void startRequest(String method, String uri, HttpVersion version) {
      this._requestBuilder.request(method, uri, version);
      this._unknownExpectation = false;
      this._expect100Continue = false;
      this._expect102Processing = false;
   }

   protected boolean checkAndPrepareUpgrade() {
      return false;
   }

   protected void handleException(Throwable x) {
      this._httpConnection.getGenerator().setPersistent(false);
      super.handleException(x);
   }

   private boolean upgrade() throws BadMessageException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("upgrade {} {}", this, this._upgrade);
      }

      boolean isUpgradedH2C = this._upgrade == PREAMBLE_UPGRADE_H2C;
      if (isUpgradedH2C || this._connection != null && this._connection.contains("upgrade")) {
         Stream var10000 = this.getConnector().getConnectionFactories().stream().filter((f) -> f instanceof ConnectionFactory.Upgrading);
         Objects.requireNonNull(ConnectionFactory.Upgrading.class);
         ConnectionFactory.Upgrading factory = (ConnectionFactory.Upgrading)var10000.map(ConnectionFactory.Upgrading.class::cast).filter((f) -> f.getProtocols().contains(this._upgrade.getValue())).findAny().orElse((Object)null);
         if (factory == null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("No factory for {} in {}", this._upgrade, this.getConnector());
            }

            return false;
         } else {
            HttpFields.Mutable response101 = HttpFields.build();
            Connection upgradeConnection = factory.upgradeConnection(this.getConnector(), this.getEndPoint(), this._metadata, response101);
            if (upgradeConnection == null) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Upgrade ignored for {} by {}", this._upgrade, factory);
               }

               return false;
            } else {
               try {
                  if (!isUpgradedH2C) {
                     this.sendResponse(new MetaData.Response(HttpVersion.HTTP_1_1, 101, response101, 0L), (ByteBuffer)null, true);
                  }
               } catch (IOException e) {
                  throw new BadMessageException(500, (String)null, e);
               }

               if (LOG.isDebugEnabled()) {
                  LOG.debug("Upgrade from {} to {}", this.getEndPoint().getConnection(), upgradeConnection);
               }

               this.getRequest().setAttribute(HttpTransport.UPGRADE_CONNECTION_ATTRIBUTE, upgradeConnection);
               this.getHttpTransport().onCompleted();
               return true;
            }
         }
      } else {
         throw new BadMessageException(400);
      }
   }

   boolean onIdleTimeout(Throwable timeout) {
      if (this._delayedForContent) {
         this._delayedForContent = false;
         this.doOnIdleTimeout(timeout);
         this.execute(this);
         return false;
      } else {
         return true;
      }
   }

   private void doOnIdleTimeout(Throwable x) {
      boolean neverDispatched = this.getState().isIdle();
      boolean waitingForContent = this._content == null || this._content.remaining() == 0;
      if ((waitingForContent || neverDispatched) && (this._content == null || !this._content.isSpecial())) {
         x.addSuppressed(new Throwable("HttpInput idle timeout"));
         this._content = new HttpInput.ErrorContent(x);
      }

   }

   static {
      PREAMBLE_UPGRADE_H2C = new HttpField(HttpHeader.UPGRADE, "h2c");
      EOF = new HttpInput.EofContent();
   }

   private static class RequestBuilder {
      private final HttpFields.Mutable _fieldsBuilder = HttpFields.build();
      private final HttpURI.Mutable _uriBuilder = HttpURI.build();
      private String _method;
      private HttpVersion _version;
      private long _beginNanoTime = Long.MIN_VALUE;

      public String method() {
         return this._method;
      }

      public void request(String method, String uri, HttpVersion version) {
         this._method = method;
         this._uriBuilder.uri(method, uri);
         this._version = version;
         this._fieldsBuilder.clear();
      }

      public void beginNanoTime(long nanoTime) {
         if (nanoTime == Long.MIN_VALUE) {
            ++nanoTime;
         }

         this._beginNanoTime = nanoTime;
      }

      public HttpFields.Mutable getFields() {
         return this._fieldsBuilder;
      }

      public MetaData.Request build() {
         long nanoTime = this._beginNanoTime == Long.MIN_VALUE ? NanoTime.now() : this._beginNanoTime;
         return new MetaData.Request(nanoTime, this._method, this._uriBuilder, this._version, this._fieldsBuilder);
      }

      public HttpVersion version() {
         return this._version;
      }
   }
}
