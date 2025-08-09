package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAliases;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidHost;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidPort;
import org.apache.logging.log4j.core.net.AbstractSocketManager;
import org.apache.logging.log4j.core.net.Advertiser;
import org.apache.logging.log4j.core.net.DatagramSocketManager;
import org.apache.logging.log4j.core.net.Protocol;
import org.apache.logging.log4j.core.net.SocketOptions;
import org.apache.logging.log4j.core.net.SslSocketManager;
import org.apache.logging.log4j.core.net.TcpSocketManager;
import org.apache.logging.log4j.core.net.ssl.SslConfiguration;
import org.apache.logging.log4j.core.util.Booleans;

@Plugin(
   name = "Socket",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public class SocketAppender extends AbstractOutputStreamAppender {
   private final Object advertisement;
   private final Advertiser advertiser;

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   protected SocketAppender(final String name, final Layout layout, final Filter filter, final AbstractSocketManager manager, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser, final Property[] properties) {
      super(name, layout, filter, ignoreExceptions, immediateFlush, properties, manager);
      if (advertiser != null) {
         Map<String, String> configuration = new HashMap(layout.getContentFormat());
         configuration.putAll(manager.getContentFormat());
         configuration.put("contentType", layout.getContentType());
         configuration.put("name", name);
         this.advertisement = advertiser.advertise(configuration);
      } else {
         this.advertisement = null;
      }

      this.advertiser = advertiser;
   }

   /** @deprecated */
   @Deprecated
   protected SocketAppender(final String name, final Layout layout, final Filter filter, final AbstractSocketManager manager, final boolean ignoreExceptions, final boolean immediateFlush, final Advertiser advertiser) {
      this(name, layout, filter, manager, ignoreExceptions, immediateFlush, advertiser, Property.EMPTY_ARRAY);
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      super.stop(timeout, timeUnit, false);
      if (this.advertiser != null) {
         this.advertiser.unadvertise(this.advertisement);
      }

      this.setStopped();
      return true;
   }

   /** @deprecated */
   @Deprecated
   @PluginFactory
   public static SocketAppender createAppender(final String host, final int port, final Protocol protocol, final SslConfiguration sslConfig, final int connectTimeoutMillis, final int reconnectDelayMillis, final boolean immediateFail, final String name, final boolean immediateFlush, final boolean ignoreExceptions, final Layout layout, final Filter filter, final boolean advertise, final Configuration configuration) {
      return ((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)newBuilder().setAdvertise(advertise)).setConfiguration(configuration)).setConnectTimeoutMillis(connectTimeoutMillis)).setFilter(filter)).setHost(host)).setIgnoreExceptions(ignoreExceptions)).setImmediateFail(immediateFail)).setLayout(layout)).setName(name)).setPort(port)).setProtocol(protocol)).setReconnectDelayMillis(reconnectDelayMillis)).setSslConfiguration(sslConfig)).build();
   }

   /** @deprecated */
   @Deprecated
   public static SocketAppender createAppender(final String host, final String portNum, final String protocolIn, final SslConfiguration sslConfig, final int connectTimeoutMillis, final String delayMillis, final String immediateFail, final String name, final String immediateFlush, final String ignore, final Layout layout, final Filter filter, final String advertise, final Configuration config) {
      boolean isFlush = Booleans.parseBoolean(immediateFlush, true);
      boolean isAdvertise = Boolean.parseBoolean(advertise);
      boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
      boolean fail = Booleans.parseBoolean(immediateFail, true);
      int reconnectDelayMillis = AbstractAppender.parseInt(delayMillis, 0);
      int port = AbstractAppender.parseInt(portNum, 0);
      Protocol p = protocolIn == null ? Protocol.UDP : Protocol.valueOf(protocolIn);
      return createAppender(host, port, p, sslConfig, connectTimeoutMillis, reconnectDelayMillis, fail, name, isFlush, ignoreExceptions, layout, filter, isAdvertise, config);
   }

   /** @deprecated */
   @Deprecated
   protected static AbstractSocketManager createSocketManager(final String name, final Protocol protocol, final String host, final int port, final int connectTimeoutMillis, final SslConfiguration sslConfig, final int reconnectDelayMillis, final boolean immediateFail, final Layout layout, final int bufferSize) {
      return createSocketManager(name, protocol, host, port, connectTimeoutMillis, sslConfig, reconnectDelayMillis, immediateFail, layout, bufferSize, (SocketOptions)null);
   }

   protected static AbstractSocketManager createSocketManager(final String name, Protocol protocol, final String host, final int port, final int connectTimeoutMillis, final SslConfiguration sslConfig, final int reconnectDelayMillis, final boolean immediateFail, final Layout layout, final int bufferSize, final SocketOptions socketOptions) {
      if (protocol == Protocol.TCP && sslConfig != null) {
         protocol = Protocol.SSL;
      }

      if (protocol != Protocol.SSL && sslConfig != null) {
         LOGGER.info("Appender {} ignoring SSL configuration for {} protocol", name, protocol);
      }

      switch (protocol) {
         case TCP:
            return TcpSocketManager.getSocketManager(host, port, connectTimeoutMillis, reconnectDelayMillis, immediateFail, layout, bufferSize, socketOptions);
         case UDP:
            return DatagramSocketManager.getSocketManager(host, port, layout, bufferSize);
         case SSL:
            return SslSocketManager.getSocketManager(sslConfig, host, port, connectTimeoutMillis, reconnectDelayMillis, immediateFail, layout, bufferSize, socketOptions);
         default:
            throw new IllegalArgumentException(protocol.toString());
      }
   }

   protected void directEncodeEvent(final LogEvent event) {
      this.writeByteArrayToManager(event);
   }

   public abstract static class AbstractBuilder extends AbstractOutputStreamAppender.Builder {
      @PluginBuilderAttribute
      private boolean advertise;
      @PluginBuilderAttribute
      private int connectTimeoutMillis;
      @PluginBuilderAttribute
      @ValidHost
      private String host = "localhost";
      @PluginBuilderAttribute
      private boolean immediateFail = true;
      @PluginBuilderAttribute
      @ValidPort
      private int port;
      @PluginBuilderAttribute
      private Protocol protocol;
      @PluginBuilderAttribute
      @PluginAliases({"reconnectDelay", "reconnectionDelay", "delayMillis", "reconnectionDelayMillis"})
      private int reconnectDelayMillis;
      @PluginElement("SocketOptions")
      private SocketOptions socketOptions;
      @PluginElement("SslConfiguration")
      @PluginAliases({"SslConfig"})
      private SslConfiguration sslConfiguration;

      public AbstractBuilder() {
         this.protocol = Protocol.TCP;
      }

      public boolean getAdvertise() {
         return this.advertise;
      }

      public int getConnectTimeoutMillis() {
         return this.connectTimeoutMillis;
      }

      public String getHost() {
         return this.host;
      }

      public int getPort() {
         return this.port;
      }

      public Protocol getProtocol() {
         return this.protocol;
      }

      public SslConfiguration getSslConfiguration() {
         return this.sslConfiguration;
      }

      public boolean getImmediateFail() {
         return this.immediateFail;
      }

      public AbstractBuilder setAdvertise(final boolean advertise) {
         this.advertise = advertise;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setConnectTimeoutMillis(final int connectTimeoutMillis) {
         this.connectTimeoutMillis = connectTimeoutMillis;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setHost(final String host) {
         this.host = host;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setImmediateFail(final boolean immediateFail) {
         this.immediateFail = immediateFail;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setPort(final int port) {
         this.port = port;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setProtocol(final Protocol protocol) {
         this.protocol = protocol;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setReconnectDelayMillis(final int reconnectDelayMillis) {
         this.reconnectDelayMillis = reconnectDelayMillis;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setSocketOptions(final SocketOptions socketOptions) {
         this.socketOptions = socketOptions;
         return (AbstractBuilder)this.asBuilder();
      }

      public AbstractBuilder setSslConfiguration(final SslConfiguration sslConfiguration) {
         this.sslConfiguration = sslConfiguration;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withAdvertise(final boolean advertise) {
         this.advertise = advertise;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withConnectTimeoutMillis(final int connectTimeoutMillis) {
         this.connectTimeoutMillis = connectTimeoutMillis;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withHost(final String host) {
         this.host = host;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withImmediateFail(final boolean immediateFail) {
         this.immediateFail = immediateFail;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withPort(final int port) {
         this.port = port;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withProtocol(final Protocol protocol) {
         this.protocol = protocol;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withReconnectDelayMillis(final int reconnectDelayMillis) {
         this.reconnectDelayMillis = reconnectDelayMillis;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withSocketOptions(final SocketOptions socketOptions) {
         this.socketOptions = socketOptions;
         return (AbstractBuilder)this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public AbstractBuilder withSslConfiguration(final SslConfiguration sslConfiguration) {
         this.sslConfiguration = sslConfiguration;
         return (AbstractBuilder)this.asBuilder();
      }

      public int getReconnectDelayMillis() {
         return this.reconnectDelayMillis;
      }

      public SocketOptions getSocketOptions() {
         return this.socketOptions;
      }
   }

   public static class Builder extends AbstractBuilder implements org.apache.logging.log4j.core.util.Builder {
      public SocketAppender build() {
         boolean immediateFlush = this.isImmediateFlush();
         boolean bufferedIo = this.isBufferedIo();
         Layout<? extends Serializable> layout = this.getLayout();
         if (layout == null) {
            SocketAppender.LOGGER.error("No layout provided for SocketAppender");
            return null;
         } else {
            String name = this.getName();
            if (name == null) {
               SocketAppender.LOGGER.error("No name provided for SocketAppender");
               return null;
            } else {
               Protocol protocol = this.getProtocol();
               Protocol actualProtocol = protocol != null ? protocol : Protocol.TCP;
               if (actualProtocol == Protocol.UDP) {
                  immediateFlush = true;
               }

               AbstractSocketManager manager = SocketAppender.createSocketManager(name, actualProtocol, this.getHost(), this.getPort(), this.getConnectTimeoutMillis(), this.getSslConfiguration(), this.getReconnectDelayMillis(), this.getImmediateFail(), layout, this.getBufferSize(), this.getSocketOptions());
               return new SocketAppender(name, layout, this.getFilter(), manager, this.isIgnoreExceptions(), !bufferedIo || immediateFlush, this.getAdvertise() ? this.getConfiguration().getAdvertiser() : null, this.getPropertyArray());
            }
         }
      }
   }
}
