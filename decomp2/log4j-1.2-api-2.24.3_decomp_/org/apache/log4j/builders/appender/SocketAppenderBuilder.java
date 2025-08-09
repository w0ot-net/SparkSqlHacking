package org.apache.log4j.builders.appender;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.log4j.Appender;
import org.apache.log4j.bridge.AppenderWrapper;
import org.apache.log4j.bridge.LayoutAdapter;
import org.apache.log4j.builders.AbstractBuilder;
import org.apache.log4j.config.Log4j1Configuration;
import org.apache.log4j.config.PropertiesConfiguration;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.xml.XmlConfiguration;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.appender.SocketAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.status.StatusLogger;
import org.w3c.dom.Element;

@Plugin(
   name = "org.apache.log4j.net.SocketAppender",
   category = "Log4j Builder"
)
public class SocketAppenderBuilder extends AbstractBuilder implements AppenderBuilder {
   private static final String HOST_PARAM = "RemoteHost";
   private static final String PORT_PARAM = "Port";
   private static final String RECONNECTION_DELAY_PARAM = "ReconnectionDelay";
   private static final int DEFAULT_PORT = 4560;
   private static final int DEFAULT_RECONNECTION_DELAY = 30000;
   public static final Logger LOGGER = StatusLogger.getLogger();

   public SocketAppenderBuilder() {
   }

   public SocketAppenderBuilder(final String prefix, final Properties props) {
      super(prefix, props);
   }

   private Appender createAppender(final String name, final String host, final int port, final org.apache.log4j.Layout layout, final Filter filter, final String level, final boolean immediateFlush, final int reconnectDelayMillis, final Log4j1Configuration configuration) {
      Layout<?> actualLayout = LayoutAdapter.adapt(layout);
      org.apache.logging.log4j.core.Filter actualFilter = buildFilters(level, filter);
      return AppenderWrapper.adapt(((SocketAppender.Builder)((SocketAppender.Builder)((SocketAppender.Builder)((SocketAppender.Builder)((SocketAppender.Builder)((SocketAppender.Builder)((SocketAppender.Builder)((SocketAppender.Builder)SocketAppender.newBuilder().setHost(host)).setPort(port)).setReconnectDelayMillis(reconnectDelayMillis)).setName(name)).setLayout(actualLayout)).setFilter(actualFilter)).setConfiguration(configuration)).setImmediateFlush(immediateFlush)).build());
   }

   public Appender parseAppender(final Element appenderElement, final XmlConfiguration config) {
      String name = this.getNameAttribute(appenderElement);
      AtomicReference<String> host = new AtomicReference("localhost");
      AtomicInteger port = new AtomicInteger(4560);
      AtomicInteger reconnectDelay = new AtomicInteger(30000);
      AtomicReference<org.apache.log4j.Layout> layout = new AtomicReference();
      AtomicReference<Filter> filter = new AtomicReference();
      AtomicReference<String> level = new AtomicReference();
      AtomicBoolean immediateFlush = new AtomicBoolean(true);
      XmlConfiguration.forEachElement(appenderElement.getChildNodes(), (currentElement) -> {
         switch (currentElement.getTagName()) {
            case "layout":
               layout.set(config.parseLayout(currentElement));
               break;
            case "filter":
               config.addFilter(filter, currentElement);
               break;
            case "param":
               switch (this.getNameAttributeKey(currentElement)) {
                  case "RemoteHost":
                     this.set("RemoteHost", currentElement, host);
                     break;
                  case "Port":
                     this.set("Port", currentElement, port);
                     break;
                  case "ReconnectionDelay":
                     this.set("ReconnectionDelay", currentElement, reconnectDelay);
                     break;
                  case "Threshold":
                     this.set("Threshold", currentElement, level);
                     break;
                  case "ImmediateFlush":
                     this.set("ImmediateFlush", currentElement, immediateFlush);
               }
         }

      });
      return this.createAppender(name, (String)host.get(), port.get(), (org.apache.log4j.Layout)layout.get(), (Filter)filter.get(), (String)level.get(), immediateFlush.get(), reconnectDelay.get(), config);
   }

   public Appender parseAppender(final String name, final String appenderPrefix, final String layoutPrefix, final String filterPrefix, final Properties props, final PropertiesConfiguration configuration) {
      return this.createAppender(name, this.getProperty("RemoteHost"), this.getIntegerProperty("Port", 4560), configuration.parseLayout(layoutPrefix, name, props), configuration.parseAppenderFilters(props, filterPrefix, name), this.getProperty("Threshold"), this.getBooleanProperty("ImmediateFlush"), this.getIntegerProperty("ReconnectionDelay", 30000), configuration);
   }
}
