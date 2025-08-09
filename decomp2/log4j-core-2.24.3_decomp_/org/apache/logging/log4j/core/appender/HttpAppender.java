package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.net.ssl.SslConfiguration;

@Plugin(
   name = "Http",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class HttpAppender extends AbstractAppender {
   private final HttpManager manager;

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private HttpAppender(final String name, final Layout layout, final Filter filter, final boolean ignoreExceptions, final HttpManager manager, final Property[] properties) {
      super(name, filter, layout, ignoreExceptions, properties);
      Objects.requireNonNull(layout, "layout");
      this.manager = (HttpManager)Objects.requireNonNull(manager, "manager");
   }

   public void start() {
      super.start();
      this.manager.startup();
   }

   public void append(final LogEvent event) {
      try {
         this.manager.send(this.getLayout(), event);
      } catch (Exception e) {
         this.error("Unable to send HTTP in appender [" + this.getName() + "]", event, e);
      }

   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      boolean stopped = super.stop(timeout, timeUnit, false);
      stopped &= this.manager.stop(timeout, timeUnit);
      this.setStopped();
      return stopped;
   }

   public String toString() {
      return "HttpAppender{name=" + this.getName() + ", state=" + this.getState() + '}';
   }

   public static class Builder extends AbstractAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      @Required(
         message = "No URL provided for HttpAppender"
      )
      private URL url;
      @PluginBuilderAttribute
      private String method = "POST";
      @PluginBuilderAttribute
      private int connectTimeoutMillis = 0;
      @PluginBuilderAttribute
      private int readTimeoutMillis = 0;
      @PluginElement("Headers")
      private Property[] headers;
      @PluginElement("SslConfiguration")
      private SslConfiguration sslConfiguration;
      @PluginBuilderAttribute
      private boolean verifyHostname = true;

      public HttpAppender build() {
         HttpManager httpManager = new HttpURLConnectionManager(this.getConfiguration(), this.getConfiguration().getLoggerContext(), this.getName(), this.url, this.method, this.connectTimeoutMillis, this.readTimeoutMillis, this.headers, this.sslConfiguration, this.verifyHostname);
         return new HttpAppender(this.getName(), this.getLayout(), this.getFilter(), this.isIgnoreExceptions(), httpManager, this.getPropertyArray());
      }

      public URL getUrl() {
         return this.url;
      }

      public String getMethod() {
         return this.method;
      }

      public int getConnectTimeoutMillis() {
         return this.connectTimeoutMillis;
      }

      public int getReadTimeoutMillis() {
         return this.readTimeoutMillis;
      }

      public Property[] getHeaders() {
         return this.headers;
      }

      public SslConfiguration getSslConfiguration() {
         return this.sslConfiguration;
      }

      public boolean isVerifyHostname() {
         return this.verifyHostname;
      }

      public Builder setUrl(final URL url) {
         this.url = url;
         return (Builder)this.asBuilder();
      }

      public Builder setMethod(final String method) {
         this.method = method;
         return (Builder)this.asBuilder();
      }

      public Builder setConnectTimeoutMillis(final int connectTimeoutMillis) {
         this.connectTimeoutMillis = connectTimeoutMillis;
         return (Builder)this.asBuilder();
      }

      public Builder setReadTimeoutMillis(final int readTimeoutMillis) {
         this.readTimeoutMillis = readTimeoutMillis;
         return (Builder)this.asBuilder();
      }

      public Builder setHeaders(final Property[] headers) {
         this.headers = headers;
         return (Builder)this.asBuilder();
      }

      public Builder setSslConfiguration(final SslConfiguration sslConfiguration) {
         this.sslConfiguration = sslConfiguration;
         return (Builder)this.asBuilder();
      }

      public Builder setVerifyHostname(final boolean verifyHostname) {
         this.verifyHostname = verifyHostname;
         return (Builder)this.asBuilder();
      }
   }
}
