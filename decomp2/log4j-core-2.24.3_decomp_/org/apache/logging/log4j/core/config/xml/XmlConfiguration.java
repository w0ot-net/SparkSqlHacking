package org.apache.logging.log4j.core.config.xml;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.Reconfigurable;
import org.apache.logging.log4j.core.config.plugins.util.PluginType;
import org.apache.logging.log4j.core.config.status.StatusConfiguration;
import org.apache.logging.log4j.core.util.Closer;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.core.util.Loader;
import org.apache.logging.log4j.core.util.Patterns;
import org.apache.logging.log4j.core.util.Throwables;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlConfiguration extends AbstractConfiguration implements Reconfigurable {
   private static final String XINCLUDE_FIXUP_LANGUAGE = "http://apache.org/xml/features/xinclude/fixup-language";
   private static final String XINCLUDE_FIXUP_BASE_URIS = "http://apache.org/xml/features/xinclude/fixup-base-uris";
   private final List status = new ArrayList();
   private Element rootElement;
   private boolean strict;
   private String schemaResource;

   @SuppressFBWarnings(
      value = {"XXE_DOCUMENT"},
      justification = "The `newDocumentBuilder` method disables DTD processing."
   )
   public XmlConfiguration(final LoggerContext loggerContext, final ConfigurationSource configSource) {
      super(loggerContext, configSource);
      byte[] buffer = null;

      try {
         InputStream configStream = configSource.getInputStream();

         try {
            buffer = toByteArray(configStream);
         } finally {
            Closer.closeSilently(configStream);
         }

         InputSource source = new InputSource(new ByteArrayInputStream(buffer));
         source.setSystemId(configSource.getLocation());
         DocumentBuilder documentBuilder = newDocumentBuilder(true);

         Document document;
         try {
            document = documentBuilder.parse(source);
         } catch (Exception e) {
            Throwable throwable = Throwables.getRootCause(e);
            if (!(throwable instanceof UnsupportedOperationException)) {
               throw e;
            }

            LOGGER.warn("The DocumentBuilder {} does not support an operation: {}.Trying again without XInclude...", documentBuilder, e);
            document = newDocumentBuilder(false).parse(source);
         }

         this.rootElement = document.getDocumentElement();
         Map<String, String> attrs = this.processAttributes(this.rootNode, this.rootElement);
         StatusConfiguration statusConfig = (new StatusConfiguration()).withStatus(this.getDefaultStatus());
         int monitorIntervalSeconds = 0;

         for(Map.Entry entry : attrs.entrySet()) {
            String key = (String)entry.getKey();
            String value = this.getConfigurationStrSubstitutor().replace((String)entry.getValue());
            if ("status".equalsIgnoreCase(key)) {
               statusConfig.withStatus(value);
            } else if ("dest".equalsIgnoreCase(key)) {
               statusConfig.withDestination(value);
            } else if ("shutdownHook".equalsIgnoreCase(key)) {
               this.isShutdownHookEnabled = !"disable".equalsIgnoreCase(value);
            } else if ("shutdownTimeout".equalsIgnoreCase(key)) {
               this.shutdownTimeoutMillis = Long.parseLong(value);
            } else if ("packages".equalsIgnoreCase(key)) {
               this.pluginPackages.addAll(Arrays.asList(value.split(Patterns.COMMA_SEPARATOR)));
            } else if ("name".equalsIgnoreCase(key)) {
               this.setName(value);
            } else if ("strict".equalsIgnoreCase(key)) {
               this.strict = Boolean.parseBoolean(value);
            } else if ("schema".equalsIgnoreCase(key)) {
               this.schemaResource = value;
            } else if ("monitorInterval".equalsIgnoreCase(key)) {
               monitorIntervalSeconds = Integers.parseInt(value);
            } else if ("advertiser".equalsIgnoreCase(key)) {
               this.createAdvertiser(value, configSource, buffer, "text/xml");
            }
         }

         this.initializeWatchers(this, configSource, monitorIntervalSeconds);
         statusConfig.initialize();
      } catch (IOException | ParserConfigurationException | SAXException e) {
         LOGGER.error("Error parsing " + configSource.getLocation(), e);
      }

      if (this.strict && this.schemaResource != null && buffer != null) {
         try {
            InputStream is = Loader.getResourceAsStream(this.schemaResource, XmlConfiguration.class.getClassLoader());

            try {
               if (is != null) {
                  Source src = new StreamSource(is, this.schemaResource);
                  SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
                  Schema schema = null;

                  try {
                     schema = factory.newSchema(src);
                  } catch (SAXException ex) {
                     LOGGER.error("Error parsing Log4j schema", ex);
                  }

                  if (schema != null) {
                     Validator validator = schema.newValidator();

                     try {
                        validator.validate(new StreamSource(new ByteArrayInputStream(buffer)));
                     } catch (IOException ioe) {
                        LOGGER.error("Error reading configuration for validation", ioe);
                     } catch (SAXException ex) {
                        LOGGER.error("Error validating configuration", ex);
                     }
                  }
               }
            } catch (Throwable var29) {
               if (is != null) {
                  try {
                     is.close();
                  } catch (Throwable var24) {
                     var29.addSuppressed(var24);
                  }
               }

               throw var29;
            }

            if (is != null) {
               is.close();
            }
         } catch (Exception ex) {
            LOGGER.error("Unable to access schema {}", this.schemaResource, ex);
         }
      }

      if (this.getName() == null) {
         this.setName(configSource.getLocation());
      }

   }

   static DocumentBuilder newDocumentBuilder(final boolean xIncludeAware) throws ParserConfigurationException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      disableDtdProcessing(factory);
      if (xIncludeAware) {
         enableXInclude(factory);
      }

      return factory.newDocumentBuilder();
   }

   private static void disableDtdProcessing(final DocumentBuilderFactory factory) {
      factory.setValidating(false);
      factory.setExpandEntityReferences(false);
      setFeature(factory, "http://xml.org/sax/features/external-general-entities", false);
      setFeature(factory, "http://xml.org/sax/features/external-parameter-entities", false);
      setFeature(factory, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
   }

   private static void setFeature(final DocumentBuilderFactory factory, final String featureName, final boolean value) {
      try {
         factory.setFeature(featureName, value);
      } catch (ParserConfigurationException e) {
         LOGGER.warn("The DocumentBuilderFactory [{}] does not support the feature [{}]: {}", factory, featureName, e);
      } catch (AbstractMethodError err) {
         LOGGER.warn("The DocumentBuilderFactory [{}] is out of date and does not support setFeature: {}", factory, err);
      }

   }

   private static void enableXInclude(final DocumentBuilderFactory factory) {
      try {
         factory.setXIncludeAware(true);
         factory.newDocumentBuilder();
      } catch (ParserConfigurationException | UnsupportedOperationException e) {
         factory.setXIncludeAware(false);
         LOGGER.warn("The DocumentBuilderFactory [{}] does not support XInclude: {}", factory, e);
      } catch (NoSuchMethodError | AbstractMethodError err) {
         LOGGER.warn("The DocumentBuilderFactory [{}] is out of date and does not support XInclude: {}", factory, err);
      }

      setFeature(factory, "http://apache.org/xml/features/xinclude/fixup-base-uris", true);
      setFeature(factory, "http://apache.org/xml/features/xinclude/fixup-language", true);
   }

   public void setup() {
      if (this.rootElement == null) {
         LOGGER.error("No logging configuration");
      } else {
         this.constructHierarchy(this.rootNode, this.rootElement);
         if (this.status.isEmpty()) {
            this.rootElement = null;
         } else {
            for(Status s : this.status) {
               LOGGER.error("Error processing element {} ({}): {}", s.name, s.element, s.errorType);
            }

         }
      }
   }

   public Configuration reconfigure() {
      try {
         ConfigurationSource source = this.getConfigurationSource().resetInputStream();
         if (source == null) {
            return null;
         } else {
            XmlConfiguration config = new XmlConfiguration(this.getLoggerContext(), source);
            return config.rootElement == null ? null : config;
         }
      } catch (IOException ex) {
         LOGGER.error("Cannot locate file {}", this.getConfigurationSource(), ex);
         return null;
      }
   }

   private void constructHierarchy(final Node node, final Element element) {
      this.processAttributes(node, element);
      StringBuilder buffer = new StringBuilder();
      NodeList list = element.getChildNodes();
      List<Node> children = node.getChildren();

      for(int i = 0; i < list.getLength(); ++i) {
         org.w3c.dom.Node w3cNode = list.item(i);
         if (w3cNode instanceof Element) {
            Element child = (Element)w3cNode;
            String name = this.getType(child);
            PluginType<?> type = this.pluginManager.getPluginType(name);
            Node childNode = new Node(node, name, type);
            this.constructHierarchy(childNode, child);
            if (type == null) {
               String value = childNode.getValue();
               if (!childNode.hasChildren() && value != null) {
                  node.getAttributes().put(name, value);
               } else {
                  this.status.add(new Status(name, element, XmlConfiguration.ErrorType.CLASS_NOT_FOUND));
               }
            } else {
               children.add(childNode);
            }
         } else if (w3cNode instanceof Text) {
            Text data = (Text)w3cNode;
            buffer.append(data.getData());
         }
      }

      String text = buffer.toString().trim();
      if (!text.isEmpty() || !node.hasChildren() && !node.isRoot()) {
         node.setValue(text);
      }

   }

   private String getType(final Element element) {
      if (this.strict) {
         NamedNodeMap attrs = element.getAttributes();

         for(int i = 0; i < attrs.getLength(); ++i) {
            org.w3c.dom.Node w3cNode = attrs.item(i);
            if (w3cNode instanceof Attr) {
               Attr attr = (Attr)w3cNode;
               if (attr.getName().equalsIgnoreCase("type")) {
                  String type = attr.getValue();
                  attrs.removeNamedItem(attr.getName());
                  return type;
               }
            }
         }
      }

      return element.getTagName();
   }

   private Map processAttributes(final Node node, final Element element) {
      NamedNodeMap attrs = element.getAttributes();
      Map<String, String> attributes = node.getAttributes();

      for(int i = 0; i < attrs.getLength(); ++i) {
         org.w3c.dom.Node w3cNode = attrs.item(i);
         if (w3cNode instanceof Attr) {
            Attr attr = (Attr)w3cNode;
            if (!attr.getName().equals("xml:base")) {
               attributes.put(attr.getName(), attr.getValue());
            }
         }
      }

      return attributes;
   }

   public String toString() {
      return this.getClass().getSimpleName() + "[location=" + this.getConfigurationSource() + ", lastModified=" + Instant.ofEpochMilli(this.getConfigurationSource().getLastModified()) + "]";
   }

   private static enum ErrorType {
      CLASS_NOT_FOUND;

      // $FF: synthetic method
      private static ErrorType[] $values() {
         return new ErrorType[]{CLASS_NOT_FOUND};
      }
   }

   private static class Status {
      private final Element element;
      private final String name;
      private final ErrorType errorType;

      public Status(final String name, final Element element, final ErrorType errorType) {
         this.name = name;
         this.element = element;
         this.errorType = errorType;
      }

      public String toString() {
         return "Status [name=" + this.name + ", element=" + this.element + ", errorType=" + this.errorType + "]";
      }
   }
}
