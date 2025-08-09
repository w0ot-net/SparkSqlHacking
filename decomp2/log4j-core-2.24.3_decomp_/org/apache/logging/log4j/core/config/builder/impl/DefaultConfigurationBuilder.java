package org.apache.logging.log4j.core.config.builder.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationException;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.AppenderRefComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.Component;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.CustomLevelComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.FilterComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.KeyValuePairComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.PropertyComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ScriptComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ScriptFileComponentBuilder;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.core.util.Throwables;

public class DefaultConfigurationBuilder implements ConfigurationBuilder {
   private static final String INDENT = "  ";
   private final Component root;
   private Component loggers;
   private Component appenders;
   private Component filters;
   private Component properties;
   private Component customLevels;
   private Component scripts;
   private final Class clazz;
   private ConfigurationSource source;
   private int monitorInterval;
   private Level level;
   private String destination;
   private String packages;
   private String shutdownFlag;
   private long shutdownTimeoutMillis;
   private String advertiser;
   private LoggerContext loggerContext;
   private String name;

   @SuppressFBWarnings(
      value = {"XXE_DTD_TRANSFORM_FACTORY", "XXE_XSLT_TRANSFORM_FACTORY"},
      justification = "This method only uses internally generated data."
   )
   public static void formatXml(final Source source, final Result result) throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString("  ".length()));
      transformer.setOutputProperty("indent", "yes");
      transformer.transform(source, result);
   }

   public DefaultConfigurationBuilder() {
      this(BuiltConfiguration.class);
      this.root.addAttribute("name", "Built");
   }

   public DefaultConfigurationBuilder(final Class clazz) {
      this.root = new Component();
      if (clazz == null) {
         throw new IllegalArgumentException("A Configuration class must be provided");
      } else {
         this.clazz = clazz;
         List<Component> components = this.root.getComponents();
         this.properties = new Component("Properties");
         components.add(this.properties);
         this.scripts = new Component("Scripts");
         components.add(this.scripts);
         this.customLevels = new Component("CustomLevels");
         components.add(this.customLevels);
         this.filters = new Component("Filters");
         components.add(this.filters);
         this.appenders = new Component("Appenders");
         components.add(this.appenders);
         this.loggers = new Component("Loggers");
         components.add(this.loggers);
      }
   }

   protected ConfigurationBuilder add(final Component parent, final ComponentBuilder builder) {
      parent.getComponents().add((Component)builder.build());
      return this;
   }

   public ConfigurationBuilder add(final AppenderComponentBuilder builder) {
      return this.add(this.appenders, builder);
   }

   public ConfigurationBuilder add(final CustomLevelComponentBuilder builder) {
      return this.add(this.customLevels, builder);
   }

   public ConfigurationBuilder add(final FilterComponentBuilder builder) {
      return this.add(this.filters, builder);
   }

   public ConfigurationBuilder add(final ScriptComponentBuilder builder) {
      return this.add(this.scripts, builder);
   }

   public ConfigurationBuilder add(final ScriptFileComponentBuilder builder) {
      return this.add(this.scripts, builder);
   }

   public ConfigurationBuilder add(final LoggerComponentBuilder builder) {
      return this.add(this.loggers, builder);
   }

   public ConfigurationBuilder add(final RootLoggerComponentBuilder builder) {
      for(Component c : this.loggers.getComponents()) {
         if (c.getPluginType().equals("root")) {
            throw new ConfigurationException("Root Logger was previously defined");
         }
      }

      return this.add(this.loggers, builder);
   }

   public ConfigurationBuilder addProperty(final String key, final String value) {
      this.properties.addComponent((Component)this.newComponent(key, "Property", value).build());
      return this;
   }

   public BuiltConfiguration build() {
      return this.build(true);
   }

   public BuiltConfiguration build(final boolean initialize) {
      T configuration;
      try {
         if (this.source == null) {
            this.source = ConfigurationSource.NULL_SOURCE;
         }

         Constructor<T> constructor = this.clazz.getConstructor(LoggerContext.class, ConfigurationSource.class, Component.class);
         configuration = (T)((BuiltConfiguration)constructor.newInstance(this.loggerContext, this.source, this.root));
         configuration.getRootNode().getAttributes().putAll(this.root.getAttributes());
         if (this.name != null) {
            configuration.setName(this.name);
         }

         if (this.level != null) {
            configuration.getStatusConfiguration().withStatus(this.level);
         }

         if (this.destination != null) {
            configuration.getStatusConfiguration().withDestination(this.destination);
         }

         if (this.packages != null) {
            configuration.setPluginPackages(this.packages);
         }

         if (this.shutdownFlag != null) {
            configuration.setShutdownHook(this.shutdownFlag);
         }

         if (this.shutdownTimeoutMillis > 0L) {
            configuration.setShutdownTimeoutMillis(this.shutdownTimeoutMillis);
         }

         if (this.advertiser != null) {
            configuration.createAdvertiser(this.advertiser, this.source);
         }

         configuration.setMonitorInterval(this.monitorInterval);
      } catch (Exception ex) {
         throw new IllegalArgumentException("Invalid Configuration class specified", ex);
      }

      configuration.getStatusConfiguration().initialize();
      if (initialize) {
         configuration.initialize();
      }

      return configuration;
   }

   private String formatXml(final String xml) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
      StringWriter writer = new StringWriter();
      formatXml(new StreamSource(new StringReader(xml)), new StreamResult(writer));
      return writer.toString();
   }

   public void writeXmlConfiguration(final OutputStream output) throws IOException {
      try {
         XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
         this.writeXmlConfiguration(xmlWriter);
         xmlWriter.close();
      } catch (XMLStreamException e) {
         if (e.getNestedException() instanceof IOException) {
            throw (IOException)e.getNestedException();
         }

         Throwables.rethrow(e);
      }

   }

   public String toXmlConfiguration() {
      StringWriter writer = new StringWriter();

      try {
         XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
         this.writeXmlConfiguration(xmlWriter);
         xmlWriter.close();
         return this.formatXml(writer.toString());
      } catch (TransformerException | XMLStreamException e) {
         Throwables.rethrow(e);
         return writer.toString();
      }
   }

   private void writeXmlConfiguration(final XMLStreamWriter xmlWriter) throws XMLStreamException {
      xmlWriter.writeStartDocument();
      xmlWriter.writeStartElement("Configuration");
      if (this.name != null) {
         xmlWriter.writeAttribute("name", this.name);
      }

      if (this.level != null) {
         xmlWriter.writeAttribute("status", this.level.name());
      }

      if (this.destination != null) {
         xmlWriter.writeAttribute("dest", this.destination);
      }

      if (this.packages != null) {
         xmlWriter.writeAttribute("packages", this.packages);
      }

      if (this.shutdownFlag != null) {
         xmlWriter.writeAttribute("shutdownHook", this.shutdownFlag);
      }

      if (this.shutdownTimeoutMillis > 0L) {
         xmlWriter.writeAttribute("shutdownTimeout", String.valueOf(this.shutdownTimeoutMillis));
      }

      if (this.advertiser != null) {
         xmlWriter.writeAttribute("advertiser", this.advertiser);
      }

      if (this.monitorInterval > 0) {
         xmlWriter.writeAttribute("monitorInterval", String.valueOf(this.monitorInterval));
      }

      this.writeXmlSection(xmlWriter, this.properties);
      this.writeXmlSection(xmlWriter, this.scripts);
      this.writeXmlSection(xmlWriter, this.customLevels);
      if (this.filters.getComponents().size() == 1) {
         this.writeXmlComponent(xmlWriter, (Component)this.filters.getComponents().get(0));
      } else if (this.filters.getComponents().size() > 1) {
         this.writeXmlSection(xmlWriter, this.filters);
      }

      this.writeXmlSection(xmlWriter, this.appenders);
      this.writeXmlSection(xmlWriter, this.loggers);
      xmlWriter.writeEndElement();
      xmlWriter.writeEndDocument();
   }

   private void writeXmlSection(final XMLStreamWriter xmlWriter, final Component component) throws XMLStreamException {
      if (!component.getAttributes().isEmpty() || !component.getComponents().isEmpty() || component.getValue() != null) {
         this.writeXmlComponent(xmlWriter, component);
      }

   }

   private void writeXmlComponent(final XMLStreamWriter xmlWriter, final Component component) throws XMLStreamException {
      if (component.getComponents().isEmpty() && component.getValue() == null) {
         xmlWriter.writeEmptyElement(component.getPluginType());
         this.writeXmlAttributes(xmlWriter, component);
      } else {
         xmlWriter.writeStartElement(component.getPluginType());
         this.writeXmlAttributes(xmlWriter, component);

         for(Component subComponent : component.getComponents()) {
            this.writeXmlComponent(xmlWriter, subComponent);
         }

         if (component.getValue() != null) {
            xmlWriter.writeCharacters(component.getValue());
         }

         xmlWriter.writeEndElement();
      }

   }

   private void writeXmlAttributes(final XMLStreamWriter xmlWriter, final Component component) throws XMLStreamException {
      for(Map.Entry attribute : component.getAttributes().entrySet()) {
         xmlWriter.writeAttribute((String)attribute.getKey(), (String)attribute.getValue());
      }

   }

   public ScriptComponentBuilder newScript(final String name, final String language, final String text) {
      return new DefaultScriptComponentBuilder(this, name, language, text);
   }

   public ScriptFileComponentBuilder newScriptFile(final String path) {
      return new DefaultScriptFileComponentBuilder(this, path, path);
   }

   public ScriptFileComponentBuilder newScriptFile(final String name, final String path) {
      return new DefaultScriptFileComponentBuilder(this, name, path);
   }

   public AppenderComponentBuilder newAppender(final String name, final String type) {
      return new DefaultAppenderComponentBuilder(this, name, type);
   }

   public AppenderRefComponentBuilder newAppenderRef(final String ref) {
      return new DefaultAppenderRefComponentBuilder(this, ref);
   }

   public LoggerComponentBuilder newAsyncLogger(final String name) {
      return new DefaultLoggerComponentBuilder(this, name, (String)null, "AsyncLogger");
   }

   public LoggerComponentBuilder newAsyncLogger(final String name, final boolean includeLocation) {
      return new DefaultLoggerComponentBuilder(this, name, (String)null, "AsyncLogger", includeLocation);
   }

   public LoggerComponentBuilder newAsyncLogger(final String name, final Level level) {
      return new DefaultLoggerComponentBuilder(this, name, level.toString(), "AsyncLogger");
   }

   public LoggerComponentBuilder newAsyncLogger(final String name, final Level level, final boolean includeLocation) {
      return new DefaultLoggerComponentBuilder(this, name, level.toString(), "AsyncLogger", includeLocation);
   }

   public LoggerComponentBuilder newAsyncLogger(final String name, final String level) {
      return new DefaultLoggerComponentBuilder(this, name, level, "AsyncLogger");
   }

   public LoggerComponentBuilder newAsyncLogger(final String name, final String level, final boolean includeLocation) {
      return new DefaultLoggerComponentBuilder(this, name, level, "AsyncLogger", includeLocation);
   }

   public RootLoggerComponentBuilder newAsyncRootLogger() {
      return new DefaultRootLoggerComponentBuilder(this, "AsyncRoot");
   }

   public RootLoggerComponentBuilder newAsyncRootLogger(final boolean includeLocation) {
      return new DefaultRootLoggerComponentBuilder(this, (String)null, "AsyncRoot", includeLocation);
   }

   public RootLoggerComponentBuilder newAsyncRootLogger(final Level level) {
      return new DefaultRootLoggerComponentBuilder(this, level.toString(), "AsyncRoot");
   }

   public RootLoggerComponentBuilder newAsyncRootLogger(final Level level, final boolean includeLocation) {
      return new DefaultRootLoggerComponentBuilder(this, level.toString(), "AsyncRoot", includeLocation);
   }

   public RootLoggerComponentBuilder newAsyncRootLogger(final String level) {
      return new DefaultRootLoggerComponentBuilder(this, level, "AsyncRoot");
   }

   public RootLoggerComponentBuilder newAsyncRootLogger(final String level, final boolean includeLocation) {
      return new DefaultRootLoggerComponentBuilder(this, level, "AsyncRoot", includeLocation);
   }

   public ComponentBuilder newComponent(final String type) {
      return new DefaultComponentBuilder(this, type);
   }

   public ComponentBuilder newComponent(final String name, final String type) {
      return new DefaultComponentBuilder(this, name, type);
   }

   public ComponentBuilder newComponent(final String name, final String type, final String value) {
      return new DefaultComponentBuilder(this, name, type, value);
   }

   public PropertyComponentBuilder newProperty(final String name, final String value) {
      return new DefaultPropertyComponentBuilder(this, name, value);
   }

   public KeyValuePairComponentBuilder newKeyValuePair(final String key, final String value) {
      return new DefaultKeyValuePairComponentBuilder(this, key, value);
   }

   public CustomLevelComponentBuilder newCustomLevel(final String name, final int level) {
      return new DefaultCustomLevelComponentBuilder(this, name, level);
   }

   public FilterComponentBuilder newFilter(final String type, final Filter.Result onMatch, final Filter.Result onMismatch) {
      return new DefaultFilterComponentBuilder(this, type, onMatch.name(), onMismatch.name());
   }

   public FilterComponentBuilder newFilter(final String type, final String onMatch, final String onMismatch) {
      return new DefaultFilterComponentBuilder(this, type, onMatch, onMismatch);
   }

   public LayoutComponentBuilder newLayout(final String type) {
      return new DefaultLayoutComponentBuilder(this, type);
   }

   public LoggerComponentBuilder newLogger(final String name) {
      return new DefaultLoggerComponentBuilder(this, name, (String)null);
   }

   public LoggerComponentBuilder newLogger(final String name, final boolean includeLocation) {
      return new DefaultLoggerComponentBuilder(this, name, (String)null, includeLocation);
   }

   public LoggerComponentBuilder newLogger(final String name, final Level level) {
      return new DefaultLoggerComponentBuilder(this, name, level.toString());
   }

   public LoggerComponentBuilder newLogger(final String name, final Level level, final boolean includeLocation) {
      return new DefaultLoggerComponentBuilder(this, name, level.toString(), includeLocation);
   }

   public LoggerComponentBuilder newLogger(final String name, final String level) {
      return new DefaultLoggerComponentBuilder(this, name, level);
   }

   public LoggerComponentBuilder newLogger(final String name, final String level, final boolean includeLocation) {
      return new DefaultLoggerComponentBuilder(this, name, level, includeLocation);
   }

   public RootLoggerComponentBuilder newRootLogger() {
      return new DefaultRootLoggerComponentBuilder(this, (String)null);
   }

   public RootLoggerComponentBuilder newRootLogger(final boolean includeLocation) {
      return new DefaultRootLoggerComponentBuilder(this, (String)null, includeLocation);
   }

   public RootLoggerComponentBuilder newRootLogger(final Level level) {
      return new DefaultRootLoggerComponentBuilder(this, level.toString());
   }

   public RootLoggerComponentBuilder newRootLogger(final Level level, final boolean includeLocation) {
      return new DefaultRootLoggerComponentBuilder(this, level.toString(), includeLocation);
   }

   public RootLoggerComponentBuilder newRootLogger(final String level) {
      return new DefaultRootLoggerComponentBuilder(this, level);
   }

   public RootLoggerComponentBuilder newRootLogger(final String level, final boolean includeLocation) {
      return new DefaultRootLoggerComponentBuilder(this, level, includeLocation);
   }

   public ConfigurationBuilder setAdvertiser(final String advertiser) {
      this.advertiser = advertiser;
      return this;
   }

   public ConfigurationBuilder setConfigurationName(final String name) {
      this.name = name;
      return this;
   }

   public ConfigurationBuilder setConfigurationSource(final ConfigurationSource configurationSource) {
      this.source = configurationSource;
      return this;
   }

   public ConfigurationBuilder setMonitorInterval(final String intervalSeconds) {
      this.monitorInterval = Integers.parseInt(intervalSeconds);
      return this;
   }

   public ConfigurationBuilder setPackages(final String packages) {
      this.packages = packages;
      return this;
   }

   public ConfigurationBuilder setShutdownHook(final String flag) {
      this.shutdownFlag = flag;
      return this;
   }

   public ConfigurationBuilder setShutdownTimeout(final long timeout, final TimeUnit timeUnit) {
      this.shutdownTimeoutMillis = timeUnit.toMillis(timeout);
      return this;
   }

   public ConfigurationBuilder setStatusLevel(final Level level) {
      this.level = level;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public ConfigurationBuilder setVerbosity(final String verbosity) {
      return this;
   }

   public ConfigurationBuilder setDestination(final String destination) {
      this.destination = destination;
      return this;
   }

   public void setLoggerContext(final LoggerContext loggerContext) {
      this.loggerContext = loggerContext;
   }

   public ConfigurationBuilder addRootProperty(final String key, final String value) {
      this.root.getAttributes().put(key, value);
      return this;
   }
}
