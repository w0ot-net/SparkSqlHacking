package io.netty.handler.codec.http2;

import io.netty.util.internal.ObjectUtil;

public abstract class AbstractHttp2ConnectionHandlerBuilder {
   private static final Http2HeadersEncoder.SensitivityDetector DEFAULT_HEADER_SENSITIVITY_DETECTOR;
   private static final int DEFAULT_MAX_RST_FRAMES_PER_CONNECTION_FOR_SERVER = 200;
   private Http2Settings initialSettings = Http2Settings.defaultSettings();
   private Http2FrameListener frameListener;
   private long gracefulShutdownTimeoutMillis;
   private boolean decoupleCloseAndGoAway;
   private boolean flushPreface;
   private Boolean isServer;
   private Integer maxReservedStreams;
   private Http2Connection connection;
   private Http2ConnectionDecoder decoder;
   private Http2ConnectionEncoder encoder;
   private Boolean validateHeaders;
   private Http2FrameLogger frameLogger;
   private Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector;
   private Boolean encoderEnforceMaxConcurrentStreams;
   private Boolean encoderIgnoreMaxHeaderListSize;
   private Http2PromisedRequestVerifier promisedRequestVerifier;
   private boolean autoAckSettingsFrame;
   private boolean autoAckPingFrame;
   private int maxQueuedControlFrames;
   private int maxConsecutiveEmptyFrames;
   private Integer maxRstFramesPerWindow;
   private int secondsPerWindow;

   public AbstractHttp2ConnectionHandlerBuilder() {
      this.gracefulShutdownTimeoutMillis = Http2CodecUtil.DEFAULT_GRACEFUL_SHUTDOWN_TIMEOUT_MILLIS;
      this.flushPreface = true;
      this.promisedRequestVerifier = Http2PromisedRequestVerifier.ALWAYS_VERIFY;
      this.autoAckSettingsFrame = true;
      this.autoAckPingFrame = true;
      this.maxQueuedControlFrames = 10000;
      this.maxConsecutiveEmptyFrames = 2;
      this.secondsPerWindow = 30;
   }

   protected Http2Settings initialSettings() {
      return this.initialSettings;
   }

   protected AbstractHttp2ConnectionHandlerBuilder initialSettings(Http2Settings settings) {
      this.initialSettings = (Http2Settings)ObjectUtil.checkNotNull(settings, "settings");
      return this.self();
   }

   protected Http2FrameListener frameListener() {
      return this.frameListener;
   }

   protected AbstractHttp2ConnectionHandlerBuilder frameListener(Http2FrameListener frameListener) {
      this.frameListener = (Http2FrameListener)ObjectUtil.checkNotNull(frameListener, "frameListener");
      return this.self();
   }

   protected long gracefulShutdownTimeoutMillis() {
      return this.gracefulShutdownTimeoutMillis;
   }

   protected AbstractHttp2ConnectionHandlerBuilder gracefulShutdownTimeoutMillis(long gracefulShutdownTimeoutMillis) {
      if (gracefulShutdownTimeoutMillis < -1L) {
         throw new IllegalArgumentException("gracefulShutdownTimeoutMillis: " + gracefulShutdownTimeoutMillis + " (expected: -1 for indefinite or >= 0)");
      } else {
         this.gracefulShutdownTimeoutMillis = gracefulShutdownTimeoutMillis;
         return this.self();
      }
   }

   protected boolean isServer() {
      return this.isServer != null ? this.isServer : true;
   }

   protected AbstractHttp2ConnectionHandlerBuilder server(boolean isServer) {
      enforceConstraint("server", "connection", this.connection);
      enforceConstraint("server", "codec", this.decoder);
      enforceConstraint("server", "codec", this.encoder);
      this.isServer = isServer;
      return this.self();
   }

   protected int maxReservedStreams() {
      return this.maxReservedStreams != null ? this.maxReservedStreams : 100;
   }

   protected AbstractHttp2ConnectionHandlerBuilder maxReservedStreams(int maxReservedStreams) {
      enforceConstraint("server", "connection", this.connection);
      enforceConstraint("server", "codec", this.decoder);
      enforceConstraint("server", "codec", this.encoder);
      this.maxReservedStreams = ObjectUtil.checkPositiveOrZero(maxReservedStreams, "maxReservedStreams");
      return this.self();
   }

   protected Http2Connection connection() {
      return this.connection;
   }

   protected AbstractHttp2ConnectionHandlerBuilder connection(Http2Connection connection) {
      enforceConstraint("connection", "maxReservedStreams", this.maxReservedStreams);
      enforceConstraint("connection", "server", this.isServer);
      enforceConstraint("connection", "codec", this.decoder);
      enforceConstraint("connection", "codec", this.encoder);
      this.connection = (Http2Connection)ObjectUtil.checkNotNull(connection, "connection");
      return this.self();
   }

   protected Http2ConnectionDecoder decoder() {
      return this.decoder;
   }

   protected Http2ConnectionEncoder encoder() {
      return this.encoder;
   }

   protected AbstractHttp2ConnectionHandlerBuilder codec(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder) {
      enforceConstraint("codec", "server", this.isServer);
      enforceConstraint("codec", "maxReservedStreams", this.maxReservedStreams);
      enforceConstraint("codec", "connection", this.connection);
      enforceConstraint("codec", "frameLogger", this.frameLogger);
      enforceConstraint("codec", "validateHeaders", this.validateHeaders);
      enforceConstraint("codec", "headerSensitivityDetector", this.headerSensitivityDetector);
      enforceConstraint("codec", "encoderEnforceMaxConcurrentStreams", this.encoderEnforceMaxConcurrentStreams);
      ObjectUtil.checkNotNull(decoder, "decoder");
      ObjectUtil.checkNotNull(encoder, "encoder");
      if (decoder.connection() != encoder.connection()) {
         throw new IllegalArgumentException("The specified encoder and decoder have different connections.");
      } else {
         this.decoder = decoder;
         this.encoder = encoder;
         return this.self();
      }
   }

   protected boolean isValidateHeaders() {
      return this.validateHeaders != null ? this.validateHeaders : true;
   }

   protected AbstractHttp2ConnectionHandlerBuilder validateHeaders(boolean validateHeaders) {
      this.enforceNonCodecConstraints("validateHeaders");
      this.validateHeaders = validateHeaders;
      return this.self();
   }

   protected Http2FrameLogger frameLogger() {
      return this.frameLogger;
   }

   protected AbstractHttp2ConnectionHandlerBuilder frameLogger(Http2FrameLogger frameLogger) {
      this.enforceNonCodecConstraints("frameLogger");
      this.frameLogger = (Http2FrameLogger)ObjectUtil.checkNotNull(frameLogger, "frameLogger");
      return this.self();
   }

   protected boolean encoderEnforceMaxConcurrentStreams() {
      return this.encoderEnforceMaxConcurrentStreams != null ? this.encoderEnforceMaxConcurrentStreams : false;
   }

   protected AbstractHttp2ConnectionHandlerBuilder encoderEnforceMaxConcurrentStreams(boolean encoderEnforceMaxConcurrentStreams) {
      this.enforceNonCodecConstraints("encoderEnforceMaxConcurrentStreams");
      this.encoderEnforceMaxConcurrentStreams = encoderEnforceMaxConcurrentStreams;
      return this.self();
   }

   protected int encoderEnforceMaxQueuedControlFrames() {
      return this.maxQueuedControlFrames;
   }

   protected AbstractHttp2ConnectionHandlerBuilder encoderEnforceMaxQueuedControlFrames(int maxQueuedControlFrames) {
      this.enforceNonCodecConstraints("encoderEnforceMaxQueuedControlFrames");
      this.maxQueuedControlFrames = ObjectUtil.checkPositiveOrZero(maxQueuedControlFrames, "maxQueuedControlFrames");
      return this.self();
   }

   protected Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector() {
      return this.headerSensitivityDetector != null ? this.headerSensitivityDetector : DEFAULT_HEADER_SENSITIVITY_DETECTOR;
   }

   protected AbstractHttp2ConnectionHandlerBuilder headerSensitivityDetector(Http2HeadersEncoder.SensitivityDetector headerSensitivityDetector) {
      this.enforceNonCodecConstraints("headerSensitivityDetector");
      this.headerSensitivityDetector = (Http2HeadersEncoder.SensitivityDetector)ObjectUtil.checkNotNull(headerSensitivityDetector, "headerSensitivityDetector");
      return this.self();
   }

   protected AbstractHttp2ConnectionHandlerBuilder encoderIgnoreMaxHeaderListSize(boolean ignoreMaxHeaderListSize) {
      this.enforceNonCodecConstraints("encoderIgnoreMaxHeaderListSize");
      this.encoderIgnoreMaxHeaderListSize = ignoreMaxHeaderListSize;
      return this.self();
   }

   /** @deprecated */
   @Deprecated
   protected AbstractHttp2ConnectionHandlerBuilder initialHuffmanDecodeCapacity(int initialHuffmanDecodeCapacity) {
      return this.self();
   }

   protected AbstractHttp2ConnectionHandlerBuilder promisedRequestVerifier(Http2PromisedRequestVerifier promisedRequestVerifier) {
      this.enforceNonCodecConstraints("promisedRequestVerifier");
      this.promisedRequestVerifier = (Http2PromisedRequestVerifier)ObjectUtil.checkNotNull(promisedRequestVerifier, "promisedRequestVerifier");
      return this.self();
   }

   protected Http2PromisedRequestVerifier promisedRequestVerifier() {
      return this.promisedRequestVerifier;
   }

   protected int decoderEnforceMaxConsecutiveEmptyDataFrames() {
      return this.maxConsecutiveEmptyFrames;
   }

   protected AbstractHttp2ConnectionHandlerBuilder decoderEnforceMaxConsecutiveEmptyDataFrames(int maxConsecutiveEmptyFrames) {
      this.enforceNonCodecConstraints("maxConsecutiveEmptyFrames");
      this.maxConsecutiveEmptyFrames = ObjectUtil.checkPositiveOrZero(maxConsecutiveEmptyFrames, "maxConsecutiveEmptyFrames");
      return this.self();
   }

   protected AbstractHttp2ConnectionHandlerBuilder decoderEnforceMaxRstFramesPerWindow(int maxRstFramesPerWindow, int secondsPerWindow) {
      this.enforceNonCodecConstraints("decoderEnforceMaxRstFramesPerWindow");
      this.maxRstFramesPerWindow = ObjectUtil.checkPositiveOrZero(maxRstFramesPerWindow, "maxRstFramesPerWindow");
      this.secondsPerWindow = ObjectUtil.checkPositiveOrZero(secondsPerWindow, "secondsPerWindow");
      return this.self();
   }

   protected AbstractHttp2ConnectionHandlerBuilder autoAckSettingsFrame(boolean autoAckSettings) {
      this.enforceNonCodecConstraints("autoAckSettingsFrame");
      this.autoAckSettingsFrame = autoAckSettings;
      return this.self();
   }

   protected boolean isAutoAckSettingsFrame() {
      return this.autoAckSettingsFrame;
   }

   protected AbstractHttp2ConnectionHandlerBuilder autoAckPingFrame(boolean autoAckPingFrame) {
      this.enforceNonCodecConstraints("autoAckPingFrame");
      this.autoAckPingFrame = autoAckPingFrame;
      return this.self();
   }

   protected boolean isAutoAckPingFrame() {
      return this.autoAckPingFrame;
   }

   protected AbstractHttp2ConnectionHandlerBuilder decoupleCloseAndGoAway(boolean decoupleCloseAndGoAway) {
      this.decoupleCloseAndGoAway = decoupleCloseAndGoAway;
      return this.self();
   }

   protected boolean decoupleCloseAndGoAway() {
      return this.decoupleCloseAndGoAway;
   }

   protected AbstractHttp2ConnectionHandlerBuilder flushPreface(boolean flushPreface) {
      this.flushPreface = flushPreface;
      return this.self();
   }

   protected boolean flushPreface() {
      return this.flushPreface;
   }

   protected Http2ConnectionHandler build() {
      if (this.encoder != null) {
         assert this.decoder != null;

         return this.buildFromCodec(this.decoder, this.encoder);
      } else {
         Http2Connection connection = this.connection;
         if (connection == null) {
            connection = new DefaultHttp2Connection(this.isServer(), this.maxReservedStreams());
         }

         return this.buildFromConnection(connection);
      }
   }

   private Http2ConnectionHandler buildFromConnection(Http2Connection connection) {
      Long maxHeaderListSize = this.initialSettings.maxHeaderListSize();
      Http2FrameReader reader = new DefaultHttp2FrameReader(new DefaultHttp2HeadersDecoder(this.isValidateHeaders(), maxHeaderListSize == null ? 8192L : maxHeaderListSize, -1));
      Http2FrameWriter writer = this.encoderIgnoreMaxHeaderListSize == null ? new DefaultHttp2FrameWriter(this.headerSensitivityDetector()) : new DefaultHttp2FrameWriter(this.headerSensitivityDetector(), this.encoderIgnoreMaxHeaderListSize);
      if (this.frameLogger != null) {
         reader = new Http2InboundFrameLogger(reader, this.frameLogger);
         writer = new Http2OutboundFrameLogger(writer, this.frameLogger);
      }

      Http2ConnectionEncoder encoder = new DefaultHttp2ConnectionEncoder(connection, writer);
      boolean encoderEnforceMaxConcurrentStreams = this.encoderEnforceMaxConcurrentStreams();
      if (this.maxQueuedControlFrames != 0) {
         encoder = new Http2ControlFrameLimitEncoder(encoder, this.maxQueuedControlFrames);
      }

      if (encoderEnforceMaxConcurrentStreams) {
         if (connection.isServer()) {
            encoder.close();
            reader.close();
            throw new IllegalArgumentException("encoderEnforceMaxConcurrentStreams: " + encoderEnforceMaxConcurrentStreams + " not supported for server");
         }

         encoder = new StreamBufferingEncoder(encoder);
      }

      DefaultHttp2ConnectionDecoder decoder = new DefaultHttp2ConnectionDecoder(connection, encoder, reader, this.promisedRequestVerifier(), this.isAutoAckSettingsFrame(), this.isAutoAckPingFrame(), this.isValidateHeaders());
      return this.buildFromCodec(decoder, encoder);
   }

   private Http2ConnectionHandler buildFromCodec(Http2ConnectionDecoder decoder, Http2ConnectionEncoder encoder) {
      int maxConsecutiveEmptyDataFrames = this.decoderEnforceMaxConsecutiveEmptyDataFrames();
      if (maxConsecutiveEmptyDataFrames > 0) {
         decoder = new Http2EmptyDataFrameConnectionDecoder(decoder, maxConsecutiveEmptyDataFrames);
      }

      int maxRstFrames;
      if (this.maxRstFramesPerWindow == null) {
         if (this.isServer()) {
            maxRstFrames = 200;
         } else {
            maxRstFrames = 0;
         }
      } else {
         maxRstFrames = this.maxRstFramesPerWindow;
      }

      if (maxRstFrames > 0 && this.secondsPerWindow > 0) {
         decoder = new Http2MaxRstFrameDecoder(decoder, maxRstFrames, this.secondsPerWindow);
      }

      T handler;
      try {
         handler = (T)this.build(decoder, encoder, this.initialSettings);
      } catch (Throwable t) {
         encoder.close();
         decoder.close();
         throw new IllegalStateException("failed to build an Http2ConnectionHandler", t);
      }

      handler.gracefulShutdownTimeoutMillis(this.gracefulShutdownTimeoutMillis);
      if (handler.decoder().frameListener() == null) {
         handler.decoder().frameListener(this.frameListener);
      }

      return handler;
   }

   protected abstract Http2ConnectionHandler build(Http2ConnectionDecoder var1, Http2ConnectionEncoder var2, Http2Settings var3) throws Exception;

   protected final AbstractHttp2ConnectionHandlerBuilder self() {
      return this;
   }

   private void enforceNonCodecConstraints(String rejected) {
      enforceConstraint(rejected, "server/connection", this.decoder);
      enforceConstraint(rejected, "server/connection", this.encoder);
   }

   private static void enforceConstraint(String methodName, String rejectorName, Object value) {
      if (value != null) {
         throw new IllegalStateException(methodName + "() cannot be called because " + rejectorName + "() has been called already.");
      }
   }

   static {
      DEFAULT_HEADER_SENSITIVITY_DETECTOR = Http2HeadersEncoder.NEVER_SENSITIVE;
   }
}
